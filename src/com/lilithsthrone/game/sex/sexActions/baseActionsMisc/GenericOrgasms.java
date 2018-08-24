package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayasRoom;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.69
 * @version 0.2.10
 * @author Innoxia
 */
public class GenericOrgasms {
	
	private static boolean isTakingCock(GameCharacter character, GameCharacter penetrator) {
		return !Collections.disjoint(
				Sex.getContactingSexAreas(penetrator, SexAreaPenetration.PENIS, character),
				Util.newArrayListOfValues(SexAreaOrifice.VAGINA, SexAreaOrifice.ANUS, SexAreaOrifice.NIPPLE, SexAreaOrifice.MOUTH, SexAreaOrifice.URETHRA_PENIS, SexAreaOrifice.URETHRA_VAGINA, SexAreaOrifice.BREAST, SexAreaPenetration.FOOT));
	}
	
	private static boolean isRealPenisFuckingCharacter(GameCharacter character, GameCharacter penetrator) {
		return (Sex.getCharactersUsingSexAreaOnCharacter(character, SexAreaPenetration.PENIS).contains(penetrator) && penetrator.hasPenisIgnoreDildo());
	}
	
	private static boolean isActiveNPCObeyingPlayer() {
		if(Sex.isMasturbation()
				|| Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.MISC_WATCHING
				|| Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.MISC_WATCHING) {
			return false;
		}
		
		return (Sex.getActivePartner().isSlave()
					&& Sex.getActivePartner().getObedienceValue()>=ObedienceLevel.POSITIVE_ONE_AGREEABLE.getMinimumValue())
				|| (Sex.isDom(Main.game.getPlayer())
						|| Sex.isSubHasEqualControl())
				|| Sex.getActivePartner().hasFetish(Fetish.FETISH_SUBMISSIVE);
	}

	private static boolean isGenericPartnerCumTargetRequirementsMet() {
		if(Sex.isMasturbation()
				|| Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.MISC_WATCHING) {
			return false;
		}
		
		if(!Sex.getActivePartner().hasPenisIgnoreDildo())
			return false;
		if(!Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.PENIS))
			return false;
		if(Sex.getActivePartner().isWearingCondom())
			return false;
		if(isActiveNPCObeyingPlayer()
				&& SexFlags.playerRequestedCreampie)
			return false;
		return true;
	}
	
	private static String getPositionPreparation(GameCharacter characterOrgasming, GameCharacter characterTargeted) {
		String orgasmText = "";
		switch(Sex.getSexPositionSlot(characterOrgasming)) {
			case MISC_WATCHING:
				orgasmText = "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax, and, while watching the lewd display before [npc.herHim], [npc.she] [npc.verb(get)] ready to orgasm.";
				break;
			case MISSIONARY_KNEELING_BETWEEN_LEGS:
				orgasmText = "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax, and, leaning down on top of [npc2.name], [npc.she] [npc.verb(gaze)] lustfully down into [npc2.her] [npc2.eyes+].";
				break;
			case MISSIONARY_ON_BACK:
				orgasmText = "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax, and,"
						+ " gazing up at [npc2.namePos] face with lust in [npc.her] [npc.eyes], [npc.she] [npc.verb(spread)] [npc.her] [npc.legs] even wider for [npc2.herHim].";
				break;
			case BACK_TO_WALL_AGAINST_WALL:
				orgasmText = "Leaning back, [npc.name] [npc.verb(brace)] [npc.herself] against the wall as [npc.she] [npc.verb(feel)] [npc.herself] reaching [npc.her] climax.";
				break;
			case BACK_TO_WALL_FACING_TARGET:
				orgasmText = "Realising that [npc1.sheIs] about to reach [npc1.her] climax, [npc1.name] [npc1.verb(step)] forwards, letting out [npc1.a_moan+] as [npc1.she] "
							+ "[npc1.verb(press)] [npc2.name] back against the wall.";
				break;
			case CHAIR_BOTTOM:
				orgasmText = "Wrapping [npc1.her] [npc1.arms+] around [npc2.name], [npc1.name] [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(pull)] [npc2.herHim] down into [npc.her] lap.";
				break;
			case CHAIR_TOP:
				orgasmText = "With [npc1.a_moan+], [npc1.name] [npc1.verb(sink)] down into [npc2.namePos] lap.";
				break;
			case COWGIRL_ON_BACK:
				orgasmText = "[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(take)] hold of [npc2.namePos] waist, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case COWGIRL_RIDING:
				orgasmText = "[npc1.Name] [npc1.verb(look)] down at [npc2.name] and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to climax.";
				break;
			case DOGGY_BEHIND: case PET_MOUNTING_HUMPING:
				orgasmText = "[npc1.Name] [npc1.verb(take)] hold of [npc2.namePos] waist, pulling [npc2.herHim] back into [npc1.her] groin and letting out [npc1.a_moan+].";
				break;
			case DOGGY_BEHIND_ORAL:
				orgasmText = "[npc1.Name] [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case DOGGY_INFRONT: case PET_ORAL_COCKED_LEG:
				orgasmText = "[npc1.Name] [npc1.verb(shuffle)] forwards, pressing [npc1.her] groin up against [npc2.namePos] [npc2.face] and letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case DOGGY_INFRONT_ANAL:
				orgasmText = "[npc1.Name] [npc1.verb(shuffle)] backwards, pressing [npc1.her] [npc1.ass+] up against [npc2.namePos] [npc2.face+] and letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case DOGGY_ON_ALL_FOURS: case DOGGY_ON_ALL_FOURS_SECOND: case PET_MOUNTING_ON_ALL_FOURS: case PET_ORAL_ON_ALL_FOURS:
				orgasmText = "[npc1.Name] [npc1.verb(brace)] [npc1.herself] on all fours, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case FACE_TO_WALL_AGAINST_WALL: case FACE_TO_WALL_AGAINST_WALL_SHOWER_PIX:
				orgasmText = "[npc1.Name] [npc1.verb(brace)] [npc1.herself] against the wall in front of [npc2.herHim], letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case FACE_TO_WALL_FACING_TARGET: case FACE_TO_WALL_FACING_TARGET_SHOWER_PIX:
				orgasmText = "[npc1.Name] [npc1.verb(press)] [npc1.herself] into [npc2.namePos] back, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case HAND_SEX_DOM_ROSE: case HAND_SEX_SUB_ROSE:
				orgasmText = "[npc1.Name] [npc1.verb(look)] into [npc2.namePos] [npc2.eyes+] and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(reach)] [npc1.her] climax.";
				break;
			case KNEELING_PERFORMING_ORAL: case KNEELING_PERFORMING_ORAL_CULTIST: case KNEELING_PERFORMING_ORAL_RALPH: case CHAIR_KNEELING:
				orgasmText = "[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(place)] a [npc1.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case KNEELING_RECEIVING_ORAL: case KNEELING_RECEIVING_ORAL_CULTIST: case KNEELING_RECEIVING_ORAL_RALPH: case CHAIR_ORAL_SITTING:
				orgasmText = "[npc1.Name] [npc1.verb(reach)] down and [npc1.verb(place)] a [npc1.hand] on [npc2.namePos] head, before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(reach)] [npc1.her] climax.";
				break;
			case MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS:
				orgasmText = "[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(place)] a [npc1.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case MISSIONARY_ALTAR_LYING_ON_ALTAR:
				orgasmText = "[npc1.Name] [npc1.verb(look)] up into [npc2.namePos] [npc2.eyes] and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS:
				orgasmText = "[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(place)] a [npc1.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc.her] climax.";
				break;
			case MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR:
				orgasmText = "[npc1.Name] [npc1.verb(look)] up into [npc2.namePos] [npc2.eyes] and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS: case MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS:
				orgasmText = "[npc1.Name] [npc1.verb(look)] down into [npc2.namePos] [npc2.eyes] and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case MISSIONARY_DESK_DOM_VICKY:
				orgasmText = "[npc1.Name] [npc1.verb(look)] down into [npc2.namePos] [npc2.eyes] and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case MISSIONARY_DESK_SUB_VICKY:
				orgasmText = "[npc1.Name] [npc1.verb(look)] up into [npc2.namePos] [npc2.eyes] and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case SIXTY_NINE_BOTTOM:
				orgasmText = "[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(grab)] [npc2.namePos] waist, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case SIXTY_NINE_TOP:
				orgasmText = "[npc1.Name] [npc1.verb(drop)] [npc1.her] [npc1.face+] down into [npc2.namePos] groin, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case STANDING_DOMINANT:
				orgasmText = "[npc1.Name] [npc1.verb(reach)] around and [npc1.verb(grab)] [npc2.namePos] [npc2.ass+], pulling [npc2.herHim] into [npc1.herHim] and letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case STANDING_SUBMISSIVE:
				orgasmText = "[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(grab)] [npc2.namePos] shoulders, leaning into [npc2.herHim] and letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case MASTURBATING_KNEELING:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You bite your lip and let out [pc.a_moan+] as you prepare to reach your climax.";
				} else {
					orgasmText = "[npc.Name] bites [npc.her] lip and lets out out [npc.a_moan+] as [npc.she] prepares to reach [npc.her] climax.";
				}
				break;
			case STOCKS_FUCKING:
				orgasmText = "[npc1.Name] [npc1.verb(reach)] down and [npc1.verb(grab)] [npc2.namePos] waist, pulling [npc2.herHim] back into [npc1.herHim] and letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case STOCKS_LOCKED_IN_STOCKS:
				orgasmText = "Unable to move, [npc1.name] [npc1.verb(wriggle)] around in the stocks and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case STOCKS_PERFORMING_ORAL:
				orgasmText = "[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(place)] a [npc1.hand] on one of [npc2.namePos] [npc2.legs], letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case STOCKS_RECEIVING_ORAL:
				orgasmText = "[npc1.Name] [npc1.verb(reach)] down and [npc1.verb(place)] a [npc1.hand] on [npc2.namePos] head, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case MILKING_STALL_FUCKING:
				orgasmText = "[npc1.Name] [npc1.verb(reach)] down and [npc1.verb(grab)] [npc2.namePos] waist, pulling [npc2.herHim] back into [npc1.herHim] and letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case MILKING_STALL_LOCKED_IN_MILKING_STALL:
				orgasmText = "Unable to move, [npc1.name] [npc1.verb(wriggle)] around in the stocks and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case MILKING_STALL_PERFORMING_ORAL:
				orgasmText = "[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(place)] a [npc1.hand] on one of [npc2.namePos] [npc2.legs], letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case MILKING_STALL_RECEIVING_ORAL:
				orgasmText = "[npc1.Name] [npc1.verb(reach)] down and [npc1.verb(place)] a [npc1.hand] on [npc2.namePos] head, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case FACE_SITTING_ON_BACK:
				orgasmText = "With [npc2.name] still sitting on [npc.her] face, [npc.name] [npc.verb(let)] out a muffled [npc.moan] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case FACE_SITTING_ON_FACE:
				orgasmText = "[npc.Name] [npc.verb(let)] [npc.her] [npc.legs+] give out from under [npc.herHim], collapsing down on [npc2.namePos] face as [npc.she] prepares to reach [npc.her] climax.";
				break;
			case BREEDING_STALL_BACK:
				orgasmText = "[npc1.Name] [npc1.verb(spread)] [npc1.her] [npc1.legs] and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case BREEDING_STALL_FRONT:
				orgasmText = "[npc1.Name] [npc1.verb(spread)] [npc1.her] [npc1.legs] and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case BREEDING_STALL_FUCKING:
				orgasmText = "[npc1.Name] [npc1.verb(reach)] down and [npc1.verb(grab)] [npc2.namePos] waist, before stepping forwards and driving [npc1.her] [npc1.cock] deep into [npc2.namePos] [npc2.pussy+]."
						+ " Letting out [npc1.a_moan+], [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax, and pump [npc2.namePos] womb full of [npc1.cum+].";
				break;
			case GLORY_HOLE_FUCKED:
				orgasmText = "[npc1.Name] [npc1.verb(push)] [npc.her] [npc.hips] back against the toilet stall's wall and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case GLORY_HOLE_FUCKING:
				orgasmText = "[npc1.Name] [npc1.verb(thrust)] [npc.her] [npc.hips] against the glory hole and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case GLORY_HOLE_KNEELING:
				orgasmText = "[npc1.Name] [npc1.verb(look)] back and forth between the two glory holes, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case GLORY_HOLE_RECEIVING_ORAL_ONE:
				orgasmText = "[npc1.Name] [npc1.verb(thrust)] [npc.her] [npc.hips] against the glory hole and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
			case GLORY_HOLE_RECEIVING_ORAL_TWO:
				orgasmText = "[npc1.Name] [npc1.verb(thrust)] [npc.her] [npc.hips] against the glory hole and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.";
				break;
		}

		if(characterTargeted!=null) {
			return UtilText.parse(characterOrgasming, characterTargeted, orgasmText);
		} else {
			return UtilText.parse(characterOrgasming, Sex.getTargetedPartner(characterOrgasming), orgasmText);
		}
	}
	
	private static StringBuilder genericOrgasmSB = new StringBuilder();
	
	private static String getGenericPenisOrgasmDescription(GameCharacter characterOrgasming, GameCharacter characterTargeted, OrgasmCumTarget cumTarget) {
		genericOrgasmSB.setLength(0);
		
		genericOrgasmSB.append(getPositionPreparation(characterOrgasming, characterTargeted));
		
		SexAreaInterface contactingArea = null;
		if(!Sex.getAllContactingSexAreas(characterOrgasming, SexAreaPenetration.PENIS).isEmpty()) {
			contactingArea = Sex.getAllContactingSexAreas(characterOrgasming, SexAreaPenetration.PENIS).get(0);
		}
		
		if(characterTargeted==null || cumTarget!=OrgasmCumTarget.INSIDE) {
			
			List<String> modifiers = new ArrayList<>();
			for(PenetrationModifier mod : PenetrationModifier.values()) {
				switch(mod) {
					case BARBED:
						break;
					case BLUNT:
						break;
					case FLARED:
						if(characterOrgasming.hasPenisModifier(mod)) {
							if(characterOrgasming.isPlayer()) {
								modifiers.add(" The wide, flared head of your [npc1.cock] swells up, and you feel your [npc1.balls+] tightening as you start to cum.");
							} else {
								modifiers.add(" The wide, flared head of [npc.namePos] [npc.cock] swells up, and [npc.she] feels [npc.her] [npc.balls+] tightening as [npc.she] starts to cum.");
							}
						}
						break;
					case KNOTTED:
						if(characterOrgasming.hasPenisModifier(mod)) {
							if(characterOrgasming.isPlayer()) {
								modifiers.add(" The thick knot at the base of your [npc1.cock] swells up, and you feel your [npc1.balls+] tightening as you start to cum.");
							} else {
								modifiers.add(" The thick knot at the base of [npc.namePos] [npc.cock] swells up, and [npc.she] feels [npc.her] [npc.balls+] tightening as [npc.she] starts to cum.");
							}
						}
						break;
					case PREHENSILE:
						break;
					case RIBBED:
						break;
					case SHEATHED:
						break;
					case TAPERED:
						break;
					case TENTACLED:
						if(characterOrgasming.hasPenisModifier(mod)) {
							if(characterOrgasming.isPlayer()) {
								modifiers.add(" The little tentacles lining your [npc1.cock] start frantically wriggling, and you feel your [npc1.balls+] tightening as you start to cum.");
							} else {
								modifiers.add(" The little tentacles lining [npc.namePos] [npc.cock] start frantically wriggling, and [npc.she] feels [npc.her] [npc.balls+] tightening as [npc.she] starts to cum.");
							}
						}
						break;
					case VEINY:
						break;
				}
			}
			
			if(!modifiers.isEmpty()) {
				genericOrgasmSB.append(modifiers.get(Util.random.nextInt(modifiers.size())));
			} else {
				if(characterOrgasming.isPlayer()) {
					genericOrgasmSB.append(" Your [npc1.cock+] twitches, and you feel your [npc1.balls+] tightening as you start to cum.");
				} else {
					genericOrgasmSB.append(" [npc.NamePos] [npc1.cock+] twitches, and [npc.she] feels [npc.her] [npc.balls+] tightening as [npc.she] starts to cum.");
				}
			}

			if(characterTargeted!=null) {
				genericOrgasmSB.append("<br/>");
				
				if(contactingArea.isOrifice()) {
					switch((SexAreaOrifice) contactingArea) {
						case ANUS:
							genericOrgasmSB.append("[npc1.Name] [npc.verb(slide)] [npc.her] [npc1.cock+] out of [npc2.namePos] [npc2.asshole+]");
							break;
						case ASS:
							genericOrgasmSB.append("[npc1.Name] [npc.verb(slide)] [npc.her] [npc1.cock+] out from between [npc2.namePos] ass cheeks");
							break;
						case BREAST:
							genericOrgasmSB.append("[npc1.Name] [npc.verb(slide)] [npc.her] [npc1.cock+] out from between [npc2.namePos] [npc2.breasts+]");
							break;
						case MOUTH:
							genericOrgasmSB.append("[npc1.Name] [npc.verb(slide)] [npc.her] [npc1.cock+] out of [npc2.namePos] mouth");
							break;
						case NIPPLE:
							genericOrgasmSB.append("[npc1.Name] [npc.verb(slide)] [npc.her] [npc1.cock+] out of [npc2.namePos] [npc2.nipple+]");
							break;
						case THIGHS:
							genericOrgasmSB.append("[npc1.Name] [npc.verb(slide)] [npc.her] [npc1.cock+] out from between [npc2.namePos] thighs");
							break;
						case URETHRA_PENIS:
							genericOrgasmSB.append("[npc1.Name] [npc.verb(slide)] [npc.her] [npc1.cock+] out of [npc2.namePos] [npc2.penisUrethra+]");
							break;
						case URETHRA_VAGINA:
							genericOrgasmSB.append("[npc1.Name] [npc.verb(slide)] [npc.her] [npc1.cock+] out of [npc2.namePos] [npc2.vaginaUrethra+]");
							break;
						case VAGINA:
							genericOrgasmSB.append("[npc1.Name] [npc.verb(slide)] [npc.her] [npc1.cock+] out of [npc2.namePos] [npc2.pussy+]");
							break;
						default:
							break;
					}
				
					if(!characterOrgasming.getPenisModifiers().isEmpty()) {
						switch(characterOrgasming.getPenisModifiers().get(Util.random.nextInt(characterOrgasming.getPenisModifiers().size()))) {
							case BARBED:
								genericOrgasmSB.append(", before reaching down and sliding [npc1.her] [npc1.hand] up and down over [npc1.her] sensitive little barbs.");
								break;
							case BLUNT:
								genericOrgasmSB.append(", before reaching down and starting to furiously masturbate; [npc1.her] [npc1.hand] running up the length of [npc1.her] [npc1.cock] to rub and tease [npc1.her] blunt head.");
								break;
							case FLARED:
								genericOrgasmSB.append(", before reaching down and starting to furiously masturbate; [npc1.her] [npc1.hand] running up the length of [npc1.her] [npc1.cock] to rub and tease [npc1.her] wide, flared head.");
								break;
							case KNOTTED:
								genericOrgasmSB.append(", before reaching down and starting to furiously masturbate; [npc1.her] [npc1.hand] sliding down the length of [npc1.her] [npc1.cock] to grip and rub at [npc1.her] swollen knot.");
								break;
							case PREHENSILE:
								genericOrgasmSB.append(", before reaching down and starting to furiously masturbate;"
										+ " [npc1.her] [npc1.hand] sliding down the length of [npc1.her] prehensile [npc1.cock] as [npc1.she] [npc.verb(curl)] it back against [npc1.her] [npc1.fingers].");
								break;
							case RIBBED:
								genericOrgasmSB.append(", before reaching down and sliding [npc1.her] [npc1.hand] up and down over the bumpy ribs that line [npc1.her] [npc1.cock].");
								break;
							case SHEATHED:
								genericOrgasmSB.append(", before reaching down and starting to furiously masturbate;"
											+ " [npc1.her] [npc1.hand] sliding down the length of [npc1.her] [npc1.cock] to bump against [npc1.her] sheath, before rising back up to [npc1.her] [npc1.cockHead+].");
								break;
							case TAPERED:
								genericOrgasmSB.append(", before reaching down and starting to furiously masturbate; [npc1.her] [npc1.hand] running up the length of [npc1.her] [npc1.cock] to rub and tease [npc1.her] tapered head.");
								break;
							case TENTACLED:
								genericOrgasmSB.append(", before reaching down and sliding [npc1.her] [npc1.hand] up and down over the squirming tentacles that line the sides of [npc1.her] [npc1.cock].");
								break;
							case VEINY:
								genericOrgasmSB.append(", before reaching down and sliding [npc1.her] [npc1.hand] up and down over [npc1.her] veiny [npc1.cock].");
								break;
						}
						
					} else {
						genericOrgasmSB.append(", before reaching down and starting to furiously masturbate; [npc1.her] [npc1.hand] running up the length of [npc1.her] [npc1.cock] to rub and tease [npc1.her] [npc1.cockHead].");
					}
					
				} else {
					switch((SexAreaPenetration) contactingArea) {
						case CLIT:
							genericOrgasmSB.append("[npc1.Name] [npc1.verb(take)] [npc1.her] [npc1.cock+] away from [npc2.namePos] [npc2.pussy+], before reaching down and starting to furiously masturbate;"
									+ " [npc1.her] [npc1.hand] running up the length of [npc1.her] [npc1.cock] to rub and tease [npc1.her] [npc1.cockHead].");
							break;
						case FINGER:
							genericOrgasmSB.append(" Bucking [npc.her] [npc1.hips], [npc.name] [npc.verb(let)] out [npc1.a_moan+] as [npc2.name] [npc2.verb(continue)] stroking [npc1.her] [npc1.cock+].");
							break;
						case PENIS:
							break;
						case TAIL:
							break;
						case TENTACLE:
							break;
						case FOOT:
							genericOrgasmSB.append(" Bucking [npc.her] [npc1.hips], [npc.name] [npc.verb(let)] out [npc1.a_moan+] as [npc2.name] [npc2.verb(continue)] stimulating [npc1.her] [npc1.cock+] with [npc2.her] [npc2.feet+].");
							break;
						case TONGUE:
							genericOrgasmSB.append("[npc1.Name] [npc1.verb(slide)] [npc1.her] [npc1.cock+] out of [npc2.namePos] mouth, before reaching down and starting to furiously masturbate;"
									+ " [npc1.her] [npc1.hand] running up the length of [npc1.her] [npc1.cock] to rub and tease [npc1.her] [npc1.cockHead].");
							break;
					}
				}
				
			}
			
			
		} else if(cumTarget==OrgasmCumTarget.INSIDE) {

			List<String> modifiers = new ArrayList<>();

			if(contactingArea.isOrifice()) {
				switch((SexAreaOrifice)contactingArea) {
					case ANUS: case NIPPLE: case VAGINA: case URETHRA_PENIS: case URETHRA_VAGINA:
						String orificeName = (contactingArea == SexAreaOrifice.VAGINA
								?"[npc2.pussy]"
								:(contactingArea == SexAreaOrifice.ANUS
									?"[npc2.asshole]"
									:(contactingArea == SexAreaOrifice.NIPPLE
											?"[npc2.nipple]"
											:"urethra")));
						String orificeNamePlusDescriptor = (contactingArea == SexAreaOrifice.VAGINA
								?"[npc2.pussy+]"
								:(contactingArea == SexAreaOrifice.ANUS
									?"[npc2.asshole+]"
									:(contactingArea == SexAreaOrifice.NIPPLE
											?"[npc2.nipple+]"
											:"urethra")));
						
						if(characterOrgasming.hasPenisModifier(PenetrationModifier.KNOTTED)) {
							if(characterOrgasming.isPlayer()) {
								genericOrgasmSB.append(" Pushing forwards, you ram the knot at the base of your [npc1.cock+] against [npc2.namePos] "+orificeNamePlusDescriptor+"."
										+ " It's already started to swell up so much that you don't manage to get it inside on the first thrust,"
											+ " but, after pulling back and slamming your [pc.hips] forwards, you manage to push the thick knot into [npc2.her] "+orificeNamePlusDescriptor+".");
							} else {
								genericOrgasmSB.append(" [npc1.Name] pushes forwards, and [npc2.name] [npc2.verb(feel)] the knot at the base of [npc1.her] [npc1.cock+] ramming against [npc2.her] "+orificeNamePlusDescriptor+"."
										+ " It's already started to swell up so much that [npc.she] doesn't manage to get it inside on the first thrust,"
											+ " but, after pulling back and slamming [npc1.her] [npc1.hips] forwards, [npc1.she] manages to push the thick knot into [npc2.namePos] "+orificeNamePlusDescriptor+".");
							}
							
						} else {
							genericOrgasmSB.append(" Ramming [npc1.her] [npc1.cock+] deep into [npc2.namePos] "+orificeNamePlusDescriptor+", [npc1.name] [npc.verb(let)] out [npc1.a_moan+] as it starts to twitch inside of [npc2.herHim].");
						}
						
						modifiers.clear();
						for(PenetrationModifier mod : PenetrationModifier.values()) {
							switch(mod) {
								case BARBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc1.Name] [npc.verb(continue)] to make small, thrusting movements,"
												+ " raking [npc1.her] barbs back against the inner walls of [npc2.namePos] "+orificeName+" and causing [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case BLUNT:
									break;
								case FLARED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										if(characterOrgasming.isPlayer()) {
											modifiers.add(" You feel the wide, flared head of your [npc1.cock] swell up, making a seal with which to trap your [npc1.cum] deep within [npc2.her] "+orificeName+".");
										} else {
											modifiers.add(" The wide, flared head of [npc1.namePos] [npc1.cock] swells up, making a seal with which to trap [npc1.her] [npc1.cum] deep within [npc2.namePos] "+orificeName+".");
										}
									}
									break;
								case KNOTTED:
									break;
								case PREHENSILE:
									break;
								case RIBBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										if(characterOrgasming.isPlayer()) {
											modifiers.add(" You feel your ribbed [npc1.cock] bumping against the inner walls of [npc2.her] "+orificeName+", which causes [npc2.herHim] to let out [npc2.a_moan+].");
										} else {
											modifiers.add(" The ribbed length of [npc1.namePos] [npc1.cock] bumps against the inner walls of [npc2.namePos] "+orificeName+", which causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
									}
									break;
								case SHEATHED:
									break;
								case TAPERED:
									break;
								case TENTACLED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										if(characterOrgasming.isPlayer()) {
											modifiers.add(" You feel the wriggling tentacles lining your [npc1.cock] start to massage the inner walls of [npc2.her] "+orificeName+", which causes [npc2.herHim] to let out [npc2.a_moan+].");
										} else {
											modifiers.add(" The wriggling tentacles lining [npc1.namePos] [npc1.cock] start to massage the inner walls of [npc2.namePos] "+orificeName+", which causes [npc2.herHim] to let out [npc2.a_moan+].");
											
										}
									}
									break;
								case VEINY:
									break;
							}
						}
						
						if(!modifiers.isEmpty()) {
							genericOrgasmSB.append(modifiers.get(Util.random.nextInt(modifiers.size())));
						}
						
						if(characterOrgasming.hasPenisModifier(PenetrationModifier.KNOTTED)) {
							if(characterOrgasming.isPlayer()) {
								genericOrgasmSB.append(" Keeping your [npc1.hips] pushed tightly against [npc2.namePos] "+orificeName+", you let out [npc1.a_moan+] as you feel your knot swell up to its full size."
										+ " Bucking back a little, you grin as you feel [npc2.name] pulled along with you; evidence that your [npc1.cock] is now firmly locked inside [npc2.her] "+orificeNamePlusDescriptor+".");
							} else {
								genericOrgasmSB.append(" Keeping [npc1.her] [npc1.hips] pushed tightly against [npc2.namePos] "+orificeName+", [npc1.name] [npc.verb(let)] out [npc1.a_moan+] as [npc1.her] knot swells up to its full size."
										+ " [npc.She] then [npc.verb(buck)] back a little, and [npc2.name] [npc2.verb(let)] out a startled cry as [npc2.sheIs] pulled along with [npc.herHim];"
											+ " evidence that [npc1.her] [npc1.cock] is now firmly locked inside [npc2.her] "+orificeNamePlusDescriptor+".");
								
							}
						}
						break;
						
					case ASS:
						genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] thrusting [npc.her] [npc.cock+] between [npc2.namePos] [npc2.assSize] ass cheeks, letting out [npc.a_moan+] as [npc.she] [npc.verb(feel)] it start to twitch.");
						
						for(PenetrationModifier mod : PenetrationModifier.values()) {
							switch(mod) {
								case BARBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc1.Her] movements cause the barbs lining the sides of [npc.her] [npc.cock] to rake against [npc2.namePos] [npc2.ass], causing [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case BLUNT:
									break;
								case FLARED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc1.Her] flared head swells up, causing [npc2.namePos] cheeks to be parted ever wider, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case KNOTTED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc1.Her] fat knot swells up, and with each thrust, bumps wildly against [npc2.namePos] [npc2.asshole], which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case PREHENSILE:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" Harnessing the ability of [npc1.her] prehensile cock, [npc.name] bends it down over [npc2.namePos] back on each thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case RIBBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc1.Her] ribbed shaft repeatedly bumps against [npc2.namePos] [npc2.asshole] on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case SHEATHED:
									break;
								case TAPERED:
									break;
								case TENTACLED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" The little tentacles lining [npc1.her] shaft wriggle against and massage [npc2.namePos] [npc2.asshole] on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case VEINY:
									break;
							}
						}
						break;
						
					case BREAST:
						if(characterTargeted.hasBreasts()) {
							genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] thrusting [npc.her] [npc.cock+] between [npc2.namePos] [npc2.breasts+], letting out [npc.a_moan+] as [npc.she] [npc.verb(feel)] it start to twitch.");
							
							for(PenetrationModifier mod : PenetrationModifier.values()) {
								switch(mod) {
									case BARBED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" [npc1.Her] movements cause the barbs lining [npc.her] [npc.cock] to rake against the sides of [npc2.namePos] breasts, causing [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case BLUNT:
										break;
									case FLARED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" [npc1.Her] flared head swells up, bumping wildly against [npc2.namePos] [npc2.breasts+] on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case KNOTTED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" [npc1.Her] fat knot swells up, and with each thrust, bumps wildly against [npc2.namePos] [npc2.breasts+], which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case PREHENSILE:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Harnessing the ability of [npc1.her] prehensile cock, [npc.name] curls it around alternating sides of [npc2.namePos] breasts,"
													+ " which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case RIBBED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" [npc1.Her] ribbed shaft repeatedly bumps against [npc2.namePos] [npc2.breasts+] on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case SHEATHED:
										break;
									case TAPERED:
										break;
									case TENTACLED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" The little tentacles lining [npc1.her] shaft wriggle against and massage [npc2.namePos] [npc2.breasts+] on every thrust,"
													+ " which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case VEINY:
										break;
								}
							}
							
						} else {
							genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] grinding [npc.her] [npc.cock+] against [npc2.namePos] flat chest, letting out [npc.a_moan+] as [npc.she] [npc.verb(feel)] it start to twitch.");
							
							for(PenetrationModifier mod : PenetrationModifier.values()) {
								switch(mod) {
									case BARBED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" [npc1.Her] movements cause the barbs lining [npc.her] [npc.cock] to rake sharply over [npc2.namePos] torso, causing [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case BLUNT:
										break;
									case FLARED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" [npc1.Her] flared head swells up, bumping wildly against [npc2.namePos] torso on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case KNOTTED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" [npc1.Her] fat knot swells up, and with each thrust, bumps wildly against [npc2.namePos] torso, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case PREHENSILE:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Harnessing the ability of [npc1.her] prehensile cock, [npc.name] curls it this way and that as [npc.she] grinds against [npc2.namePos] chest,"
													+ " which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case RIBBED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" [npc1.Her] ribbed shaft repeatedly bumps over [npc2.namePos] torso on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case SHEATHED:
										break;
									case TAPERED:
										break;
									case TENTACLED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" The little tentacles lining [npc1.her] shaft wriggle against and massage [npc2.namePos] torso on every thrust,"
													+ " which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case VEINY:
										break;
								}
							}
						}
						break;
						
					case MOUTH:
						if(characterOrgasming.hasPenisModifier(PenetrationModifier.KNOTTED)) {
							if(characterOrgasming.isPlayer()) {
								genericOrgasmSB.append(" Pushing forwards, you ram the knot at the base of your [npc1.cock+] against [npc2.namePos] [npc2.face+]."
										+ " It's already started to swell up so much that you don't manage to get it past [npc2.her] [npc2.lips+] on the first thrust,"
											+ " but, after pulling back and slamming your [pc.hips] forwards, you manage to push the thick knot into [npc2.her] [npc2.mouth].");
							} else {
								if(characterTargeted.isPlayer()) {
									genericOrgasmSB.append(" [npc1.Name] pushes forwards, ramming the knot at the base of [npc1.her] [npc1.cock+] into [npc2.namePos] [npc2.face+]."
											+ " It's already started to swell up so much that [npc.she] doesn't manage to get it past your [npc2.lips+] on the first thrust,"
												+ " but, after pulling back and slamming [npc1.her] [npc1.hips] forwards, [npc1.she] manages to push the thick knot into your [npc2.mouth].");
								} else {
									genericOrgasmSB.append(" Ramming the knot at the base of [npc1.her] [npc1.cock+] into [npc2.namePos] mouth, [npc1.name] lets out [npc1.a_moan+] as it starts to swell up inside of [npc2.herHim].");
								}
							}
							
							if(characterOrgasming.isPlayer()) {
								genericOrgasmSB.append(" Ramming the knot at the base of your [npc1.cock+] into [npc2.namePos] mouth, you let out [npc1.a_moan+] as you feel it start to swell up.");
							} else {
								if(characterTargeted.isPlayer()) {
									genericOrgasmSB.append(" Ramming the knot at the base of [npc1.her] [npc1.cock+] into your mouth, [npc1.name] lets out [npc1.a_moan+] as it starts to swell up inside of you.");
								} else {
									genericOrgasmSB.append(" Ramming the knot at the base of [npc1.her] [npc1.cock+] into [npc2.namePos] mouth, [npc1.name] lets out [npc1.a_moan+] as it starts to swell up inside of [npc2.herHim].");
								}
							}
							
						} else {
							if(characterOrgasming.isPlayer()) {
								genericOrgasmSB.append(" Ramming your [npc1.cock+] deep down [npc2.namePos] throat, you let out [npc1.a_moan+] as you feel your [npc1.cock+] start to twitch.");
							} else {
								if(characterTargeted.isPlayer()) {
									genericOrgasmSB.append(" Ramming [npc1.her] [npc1.cock+] deep down your throat, [npc1.name] lets out [npc1.a_moan+] as it starts to twitch inside of you.");
								} else {
									genericOrgasmSB.append(" Ramming [npc1.her] [npc1.cock+] deep down [npc2.namePos] throat, [npc1.name] lets out [npc1.a_moan+] as it starts to twitch inside of [npc2.herHim].");
								}
							}
						}
						
						modifiers.clear();
						for(PenetrationModifier mod : PenetrationModifier.values()) {
							switch(mod) {
								case BARBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										if(characterOrgasming.isPlayer()) {
											modifiers.add(" You continue to make small, thrusting movements, raking your barbs back against the lining of [npc2.her] throat and causing [npc2.herHim] to let out a choking [npc2.moan].");
										} else {
											if(characterTargeted.isPlayer()) {
												modifiers.add(" [npc1.Name] continues to make small, thrusting movements, raking [npc1.her] barbs back against the lining of your throat and causing you to let out a choking [npc2.moan].");
											} else {
												modifiers.add(" [npc1.Name] continues to make small, thrusting movements,"
														+ " raking [npc1.her] barbs back against the lining of [npc2.namePos] throat and causing [npc2.herHim] to let out a choking [npc2.moan].");
											}
										}
									}
									break;
								case BLUNT:
									break;
								case FLARED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										if(characterOrgasming.isPlayer()) {
											modifiers.add(" You feel the wide, flared head of your [npc1.cock] swell up, making a seal with which to trap your [npc1.cum] deep down [npc2.her] throat.");
										} else {
											if(characterTargeted.isPlayer()) {
												modifiers.add(" The wide, flared head of [npc1.namePos] [npc1.cock] swells up, making a seal with which to trap [npc1.her] [npc1.cum] deep down your throat.");
											} else {
												modifiers.add(" The wide, flared head of [npc1.namePos] [npc1.cock] swells up, making a seal with which to trap [npc1.her] [npc1.cum] deep down [npc2.namePos] throat.");
											}
										}
									}
									break;
								case KNOTTED:
									break;
								case PREHENSILE:
									break;
								case RIBBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										if(characterOrgasming.isPlayer()) {
											modifiers.add(" You feel your ribbed [npc1.cock] bumping against the lining of [npc2.her] throat, which causes [npc2.herHim] to let out a muffled [npc2.moan].");
										} else {
											if(characterTargeted.isPlayer()) {
												modifiers.add(" The ribbed length of [npc1.namePos] [npc1.cock] bumps against the lining of your throat, which causes you to let out a muffled [npc2.moan].");
											} else {
												modifiers.add(" The ribbed length of [npc1.namePos] [npc1.cock] bumps against the lining of [npc2.namePos] throat, which causes [npc2.herHim] to let out a muffled [npc2.moan].");
											}
										}
									}
									break;
								case SHEATHED:
									break;
								case TAPERED:
									break;
								case TENTACLED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										if(characterOrgasming.isPlayer()) {
											modifiers.add(" You feel the wriggling tentacles lining your [npc1.cock] start to massage the lining of [npc2.her] throat, which causes [npc2.herHim] to let out a muffled [npc2.moan].");
										} else {
											if(characterTargeted.isPlayer()) {
												modifiers.add(" The wriggling tentacles lining [npc1.namePos] [npc1.cock] start to massage the lining of your throat, which causes you to let out a muffled [npc2.moan].");
											} else {
												modifiers.add(" The wriggling tentacles lining [npc1.namePos] [npc1.cock] start to massage the lining of [npc2.namePos] throat, which causes [npc2.herHim] to let out a muffled [npc2.moan].");
											}
										}
									}
									break;
								case VEINY:
									break;
							}
						}
						
						if(!modifiers.isEmpty()) {
							genericOrgasmSB.append(modifiers.get(Util.random.nextInt(modifiers.size())));
						}
						
						if(characterOrgasming.hasPenisModifier(PenetrationModifier.KNOTTED)) {
							if(characterOrgasming.isPlayer()) {
								genericOrgasmSB.append(" Keeping your [npc1.hips] pushed tightly against [npc2.namePos] [npc2.face], you let out [npc1.a_moan+] as you feel your knot swell up to its full size."
										+ " Bucking back a little, you grin as [npc2.namePos] pulled along with you; evidence that your [npc1.cock] is now firmly locked inside [npc2.her] [npc2.mouth].");
							} else {
								if(characterTargeted.isPlayer()) {
									genericOrgasmSB.append(" Keeping [npc1.her] [npc1.hips] pushed tightly against your [npc2.face], [npc1.name] lets out [npc1.a_moan+] as [npc1.her] knot swells up to its full size."
											+ " [npc.She] then bucks back a little, and you let out a very muffled cry as you're pulled along with [npc1.herHim];"
												+ " evidence that [npc1.her] [npc1.cock] is now firmly locked inside your [npc2.mouth].");
								} else {
									genericOrgasmSB.append(" After just a moment, [npc1.her] knot has fully swollen in size, locking [npc1.her] [npc1.cock] inside [npc2.namePos] [npc2.mouth].");
								}
							}
						}
						break;
						
					case THIGHS:
						genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] thrusting [npc.her] [npc.cock+] between [npc2.namePos] thighs, letting out [npc.a_moan+] as [npc.she] [npc.verb(feel)] it start to twitch.");
						
						for(PenetrationModifier mod : PenetrationModifier.values()) {
							switch(mod) {
								case BARBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc1.Her] movements cause the barbs lining the sides of [npc.her] [npc.cock] to rake against [npc2.namePos] [npc2.legs+], causing [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case BLUNT:
									break;
								case FLARED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc1.Her] flared head swells up, causing [npc2.namePos] [npc2.legs+] to be parted ever wider, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case KNOTTED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc1.Her] fat knot swells up, and with each thrust, bumps wildly against [npc2.namePos] [npc2.legs+], which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case PREHENSILE:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" Harnessing the ability of [npc1.her] prehensile cock, [npc.name] bends it around [npc2.namePos] [npc2.legs+] on each thrust,"
												+ " which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case RIBBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc1.Her] ribbed shaft repeatedly bumps against [npc2.namePos] [npc2.legs+] on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case SHEATHED:
									break;
								case TAPERED:
									break;
								case TENTACLED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" The little tentacles lining [npc1.her] shaft wriggle against and massage [npc2.namePos] [npc2.legs+] on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case VEINY:
									break;
							}
						}
						break;
				}
				
			} else {
				switch((SexAreaPenetration)contactingArea) {
					case CLIT:
						break;
					case FINGER:
						genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] thrusting [npc.her] [npc.cock+] into [npc2.namePos] [npc2.hand], letting out [npc.a_moan+] as [npc.she] [npc.verb(feel)] it start to twitch.");
						
						for(PenetrationModifier mod : PenetrationModifier.values()) {
							switch(mod) {
								case BARBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc1.Her] movements cause the barbs lining the sides of [npc.her] [npc.cock] to rake against [npc2.namePos] [npc2.fingers+], causing [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case BLUNT:
									break;
								case FLARED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc1.Her] flared head swells up, causing [npc2.namePos] [npc2.fingers+] to be parted ever wider, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case KNOTTED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc1.Her] fat knot swells up, and with each thrust, bumps wildly against [npc2.namePos] [npc2.fingers+], which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case PREHENSILE:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" Harnessing the ability of [npc1.her] prehensile cock, [npc.name] bends it around [npc2.namePos] [npc2.fingers+] on each thrust,"
												+ " which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case RIBBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc1.Her] ribbed shaft repeatedly bumps against [npc2.namePos] [npc2.fingers+] on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case SHEATHED:
									break;
								case TAPERED:
									break;
								case TENTACLED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" The little tentacles lining [npc1.her] shaft wriggle against and massage [npc2.namePos] [npc2.fingers+] on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case VEINY:
									break;
							}
						}
						break;
					case PENIS:
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case FOOT://TODO modifiers
						if(Sex.isDoubleFootJob(characterTargeted)) {
							genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] thrusting [npc.her] [npc.cock+] between [npc2.namePos] [npc2.feet+], letting out [npc.a_moan+] as [npc.she] [npc.verb(feel)] it start to twitch.");
						} else {
							genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] rubbing [npc.her] [npc.cock+] against [npc2.namePos] [npc2.foot+], letting out [npc.a_moan+] as [npc.she] [npc.verb(feel)] it start to twitch.");
						}
						break;
					case TONGUE:
						break;
				}
			}
		}

		if(characterOrgasming.isPlayer()) {
			genericOrgasmSB.append(" As your [npc1.balls+] tense up, ");
		} else {
			genericOrgasmSB.append(" As [npc1.her] [npc1.balls+] tense up, ");
		}
		genericOrgasmSB.append(getCumQuantityDescription(characterOrgasming));
		if(characterOrgasming.getPenisRawOrgasmCumQuantity()>0) {
			genericOrgasmSB.append(cumTargetDescription(characterOrgasming, characterTargeted, cumTarget));
		}
		
		if(characterOrgasming.hasPenisModifier(PenetrationModifier.KNOTTED)
				&& cumTarget==OrgasmCumTarget.INSIDE
				&& contactingArea.isOrifice()
				&& ((SexAreaOrifice)contactingArea).isInternalOrifice()) {
			genericOrgasmSB.append("<br/>"
					+ "Even after [npc1.namePos] [npc1.balls+] have pumped their entire load into [npc2.name], [npc1.her] knot remains swollen, locking"
					+ "#IFnpc2.isPlayer()"
					+ "#THEN the two of you together."
					+ "#ELSE [npc1.herHim] and [npc1.her] partner together."
					+ "#ENDIF"
					+ " It takes a few minutes for it to start to deflate, and with a wet pop, [npc1.sheIs] finally able to pull [npc1.her] [npc1.cock+] free.");
		}
		
		if(characterTargeted!=null) {
			return UtilText.parse(characterOrgasming, characterTargeted, genericOrgasmSB.toString());
		} else {
			return UtilText.parse(characterOrgasming, genericOrgasmSB.toString());
		}
	}
	
	private static String getCumQuantityDescription(GameCharacter characterOrgasming) {
		
		String targetName = "your";
		if(!characterOrgasming.isPlayer()) {
			targetName = "[npc1.namePos]";
		}
		String cumQuantityDescription = targetName+" [npc1.cum+] squirts";
		
		switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
			case ZERO_NONE:
				cumQuantityDescription = "not even a single drop of [npc1.cum] is produced...";
				break;
			case ONE_TRICKLE:
				cumQuantityDescription = "a small trickle of [npc1.cum+] squirts";
				break;
			case TWO_SMALL_AMOUNT:
				cumQuantityDescription = "a small amount of [npc1.cum+] squirts";
				break;
			case THREE_AVERAGE:
				cumQuantityDescription = targetName+" [npc1.cum+] squirts out";
				break;
			case FOUR_LARGE:
				cumQuantityDescription = targetName+" [npc1.cum+] shoots out";
				break;
			case FIVE_HUGE:
				cumQuantityDescription = targetName+" [npc1.cum+] shoots out";
				break;
			case SIX_EXTREME:
				cumQuantityDescription = targetName+" [npc1.cum+] spurts out";
				break;
			case SEVEN_MONSTROUS:
				cumQuantityDescription = targetName+" [npc1.cum+] spurts out";
				break;
		}
		return UtilText.parse(characterOrgasming, cumQuantityDescription);
	}
	
	private static String cumTargetDescription(GameCharacter characterOrgasming, GameCharacter target, OrgasmCumTarget targetArea) {
		StringBuilder cumTargetSB = new StringBuilder();
		
		if(characterOrgasming.isWearingCondom()) {
			return UtilText.parse(characterOrgasming, " into the condom that [npc1.sheIs] wearing.");
			
		} else if (!characterOrgasming.isCoverableAreaExposed(CoverableArea.PENIS)) {
			if(characterOrgasming.isPlayer()) {
				return "  into your [npc1.lowClothing(penis)].";
			} else {
				return UtilText.parse(characterOrgasming, "  into [npc1.her] [npc1.lowClothing(penis)].");
			}
			
		} else {
			
			switch(targetArea) {
				case ASS:
					target = Sex.getTargetedPartner(characterOrgasming);
					if (target.getHighestZLayerCoverableArea(CoverableArea.ASS)!=null) {
						return getClothingCummedOnText(characterOrgasming, target, CoverableArea.ASS);
					} else {
						return UtilText.parse(characterOrgasming, target,
								" all over [npc2.namePos] [npc2.ass+]."
								+ " [npc1.Name] [npc1.verb(grin)] as [npc1.her] [npc1.cum+] splatters onto [npc2.namePos] naked backside,"
									+ " and [npc2.she] can't help but let out [npc2.a_moan] as [npc2.she] [npc2.verb(feel)] it running down over [npc2.her] [npc2.asshole+].");
					}
				case BACK:
					target = Sex.getTargetedPartner(characterOrgasming);
					if (target.getHighestZLayerCoverableArea(CoverableArea.BACK)!=null) {
						return getClothingCummedOnText(characterOrgasming, target, CoverableArea.BACK);
					} else {
						if(!target.isPlayer()) {
							if(characterOrgasming.isPlayer()) {
								return UtilText.parse(target,
										" all over [npc.namePos] back."
										+ " You grin as your [pc.cum+] splatters onto [npc.herHim], and [npc.she] can't help but let out [npc.a_moan] as [npc.she] feels it running down over [npc.her] [npc.skin].");
							} else {
								return UtilText.parse(characterOrgasming, target,
										" all over [npc2.namePos] back."
										+ " [npc1.Name] grins as [npc1.her] [npc1.cum+] splatters onto [npc2.name],"
											+ " and [npc2.she] can't help but let out [npc2.a_moan] as [npc2.she] feels it running down over [npc2.her] [npc2.skin].");
								
							}
						} else {
							return UtilText.parse(characterOrgasming,
									" all over your back."
									+ " [npc1.Name] grins as [npc1.her] [npc1.cum+] splatters onto you, and you can't help but let out [pc.a_moan] as you feel it running down over your [pc.skin].");
						}
					}
				case BREASTS:
					target = Sex.getTargetedPartner(characterOrgasming);
					if (target.getHighestZLayerCoverableArea(CoverableArea.BREASTS)!=null) {
						return getClothingCummedOnText(characterOrgasming, target, CoverableArea.BREASTS);
					} else {
						if(!target.isPlayer()) {
							if(characterOrgasming.isPlayer()) {
								return UtilText.parse(target,
										" all over [npc.namePos] [npc.breasts]."
										+ " You grin as your [pc.cum+] splatters onto [npc.herHim], and [npc.she] can't help but let out [npc.a_moan] as [npc.she] feels it running down over [npc.her] [npc.breastsSkin].");
							} else {
								return UtilText.parse(characterOrgasming, target,
										" all over [npc2.namePos] [npc2.breasts]."
										+ " [npc1.Name] grins as [npc1.her] [npc1.cum+] splatters onto [npc2.name],"
											+ " and [npc2.she] can't help but let out [npc2.a_moan] as [npc2.she] feels it running down over [npc2.her] [npc2.breastsSkin].");
								
							}
						} else {
							return UtilText.parse(characterOrgasming,
									" all over your [pc.breasts]."
									+ " [npc1.Name] grins as [npc1.her] [npc1.cum+] splatters onto you, and you can't help but let out [pc.a_moan] as you feel it running down over your [pc.breastsSkin].");
						}
					}
				case FACE:
					target = Sex.getTargetedPartner(characterOrgasming);
					if (target.getHighestZLayerCoverableArea(CoverableArea.MOUTH)!=null) {
						return getClothingCummedOnText(characterOrgasming, target, CoverableArea.MOUTH);
					} else {
						if(!target.isPlayer()) {
							if(characterOrgasming.isPlayer()) {
								return UtilText.parse(target,
										" all over [npc.namePos] [npc.face+]."
										+ " You grin as your [pc.cum+] splatters onto [npc.herHim], and [npc.she] can't help but let out [npc.a_moan] as [npc.she] feels it running down over [npc.her] [npc.faceSkin].");
							} else {
								return UtilText.parse(characterOrgasming, target,
										" all over [npc2.namePos] [npc2.face+]."
										+ " [npc1.Name] grins as [npc1.her] [npc1.cum+] splatters onto [npc2.name],"
											+ " and [npc2.she] can't help but let out [npc2.a_moan] as [npc2.she] feels it running down over [npc2.her] [npc2.faceSkin].");
								
							}
						} else {
							return UtilText.parse(characterOrgasming,
									" all over your [pc.face+]."
									+ " [npc1.Name] grins as [npc1.her] [npc1.cum+] splatters onto you, and you can't help but let out [pc.a_moan] as you feel it running down over your [pc.faceSkin].");
						}
					}
				case FLOOR:
					return " out all over the floor.";
				case STOMACH:
					target = Sex.getTargetedPartner(characterOrgasming);
					if (target.getHighestZLayerCoverableArea(CoverableArea.STOMACH)!=null) {
						return getClothingCummedOnText(characterOrgasming, target, CoverableArea.STOMACH);
					} else {
						if(!target.isPlayer()) {
							if(characterOrgasming.isPlayer()) {
								return UtilText.parse(target,
										" all over [npc.namePos] stomach."
										+ " You grin as your [pc.cum+] splatters onto [npc.herHim], and [npc.she] can't help but let out [npc.a_moan] as [npc.she] feels it running down over [npc.her] [npc.skin].");
							} else {
								return UtilText.parse(characterOrgasming, target,
										" all over [npc2.namePos] stomach."
										+ " [npc1.Name] grins as [npc1.her] [npc1.cum+] splatters onto [npc2.name],"
											+ " and [npc2.she] can't help but let out [npc2.a_moan] as [npc2.she] feels it running down over [npc2.her] [npc2.skin].");
								
							}
						} else {
							return UtilText.parse(characterOrgasming,
									" all over your stomach."
									+ " [npc1.Name] grins as [npc1.her] [npc1.cum+] splatters onto you, and you can't help but let out [pc.a_moan] as you feel it running down over your [pc.skin].");
						}
					}
				case GROIN:
					target = Sex.getTargetedPartner(characterOrgasming);
					if (target.getHighestZLayerCoverableArea(CoverableArea.PENIS)!=null) {
						return getClothingCummedOnText(characterOrgasming, target, CoverableArea.PENIS);
						
					} else if (target.getHighestZLayerCoverableArea(CoverableArea.VAGINA)!=null) {
						return getClothingCummedOnText(characterOrgasming, target, CoverableArea.VAGINA);
						
					} else {
						String groinText = "groin.";
						if(target.hasPenisIgnoreDildo()) {
							if(target.hasVagina()) {
								groinText = " [npc2.cock] and [npc2.pussy].";
							} else {
								groinText = " [npc2.cock+].";
							}
						} else if(target.hasVagina()) {
							groinText = " [npc2.pussy+].";
						} else {
							groinText = " genderless mound.";
						}
						
						if(!target.isPlayer()) {
							if(characterOrgasming.isPlayer()) {
								return UtilText.parse(characterOrgasming, target,
										" all over [npc2.namePos] "+groinText
										+ " You grin as your [npc1.cum+] splatters onto [npc2.herHim], and [npc2.she] can't help but let out [npc2.a_moan] as [npc2.she] feels it running down over [npc2.her] groin.");
							} else {
								return UtilText.parse(characterOrgasming, target,
										" all over [npc2.namePos] "+groinText
										+ " [npc1.Name] grins as [npc1.her] [npc1.cum+] splatters onto [npc2.name],"
											+ " and [npc2.she] can't help but let out [npc2.a_moan] as [npc2.she] feels it running down over [npc2.her] groin.");
								
							}
						} else {
							return UtilText.parse(characterOrgasming, target,
									" all over your "+groinText
									+ " [npc1.Name] grins as [npc1.her] [npc1.cum+] splatters onto you, and you can't help but let out [npc2.a_moan] as you feel it running down over your groin.");
						}
					}
					
				case INSIDE:
					break;
					
				case HAIR:
					target = Sex.getTargetedPartner(characterOrgasming);
					if (target.getHighestZLayerCoverableArea(CoverableArea.HAIR)!=null) {
						return getClothingCummedOnText(characterOrgasming, target, CoverableArea.HAIR);
					} else {
						if(!target.isPlayer()) {
							if(characterOrgasming.isPlayer()) {
								return UtilText.parse(target,
										" all over [npc.namePos] head"+(target.getHairRawLengthValue()>0?" and [npc.hair]":"")+"."
										+ " You grin as your [pc.cum+] splatters onto [npc.herHim].");
							} else {
								return UtilText.parse(characterOrgasming, target,
										" all over [npc2.namePos] head"+(target.getHairRawLengthValue()>0?" and [npc2.hair]":"")+"."
										+ " [npc1.Name] grins as [npc1.her] [npc1.cum+] splatters onto [npc2.herHim].");
								
							}
						} else {
							return UtilText.parse(characterOrgasming,
									" all over your head"+(target.getHairRawLengthValue()>0?" and [pc.hair]":"")+"."
									+ " [npc1.Name] grins as [npc1.her] [npc1.cum+] splatters onto you.");
						}
					}
				case LEGS:
					target = Sex.getTargetedPartner(characterOrgasming);
					if (target.getHighestZLayerCoverableArea(CoverableArea.LEGS)!=null) {
						return getClothingCummedOnText(characterOrgasming, target, CoverableArea.LEGS);
					} else {
						if(!target.isPlayer()) {
							if(characterOrgasming.isPlayer()) {
								return UtilText.parse(target,
										" all over [npc.namePos] [npc.legs]."
										+ " You grin as your [pc.cum+] splatters onto [npc.herHim], and [npc.she] can't help but let out [npc.a_moan] as [npc.she] feels it running down over [npc.her] [npc.legsSkin].");
							} else {
								return UtilText.parse(characterOrgasming, target,
										" all over [npc2.namePos] [npc2.legs]."
										+ " [npc1.Name] grins as [npc1.her] [npc1.cum+] splatters onto [npc2.name],"
											+ " and [npc2.she] can't help but let out [npc2.a_moan] as [npc2.she] feels it running down over [npc2.her] [npc2.legsSkin].");
								
							}
						} else {
							return UtilText.parse(characterOrgasming,
									" all over your [pc.legs]."
									+ " [npc1.Name] grins as [npc1.her] [npc1.cum+] splatters onto you, and you can't help but let out [pc.a_moan] as you feel it running down over your [pc.legsSkin].");
						}
					}
				case FEET:
					target = Sex.getTargetedPartner(characterOrgasming);
					if (target.getHighestZLayerCoverableArea(CoverableArea.FEET)!=null) {
						return getClothingCummedOnText(characterOrgasming, target, CoverableArea.FEET);
					} else {
						if(!target.isPlayer()) {
							if(characterOrgasming.isPlayer()) {
								return UtilText.parse(target,
										" all over [npc.namePos] [npc.feet+]."
										+ " You grin as your [pc.cum+] splatters onto [npc.herHim], and [npc.she] can't help but let out [npc.a_moan] as [npc.she] feels it running down over [npc.her] [npc.toes+].");
							} else {
								return UtilText.parse(characterOrgasming, target,
										" all over [npc2.namePos] [npc2.feet+]."
										+ " [npc1.Name] grins as [npc1.her] [npc1.cum+] splatters onto [npc2.name],"
											+ " and [npc2.she] can't help but let out [npc2.a_moan] as [npc2.she] feels it running down over [npc2.her] [npc2.toes+].");
								
							}
						} else {
							return UtilText.parse(characterOrgasming,
									" all over your [pc.feet+]."
									+ " [npc1.Name] grins as [npc1.her] [npc1.cum+] splatters onto you, and you can't help but let out [pc.a_moan] as you feel it running down over your [pc.toes+].");
						}
					}
				case WALL:
					return " all up the wall.";
					
				case SELF_GROIN:
					if (characterOrgasming.getHighestZLayerCoverableArea(CoverableArea.PENIS)!=null) {
						return getClothingCummedOnText(characterOrgasming, CoverableArea.PENIS);
						
					} else if (characterOrgasming.getHighestZLayerCoverableArea(CoverableArea.VAGINA)!=null) {
						return getClothingCummedOnText(characterOrgasming, CoverableArea.VAGINA);
							
					} else {
						String groinText = "groin.";
						if(characterOrgasming.hasPenisIgnoreDildo()) {
							if(characterOrgasming.hasVagina()) {
								groinText = " [npc.cock] and [npc.pussy].";
							} else {
								groinText = " [npc.cock+].";
							}
						} else if(characterOrgasming.hasVagina()) {
							groinText = " [npc.pussy+].";
						} else {
							groinText = " genderless mound.";
						}
						
						if(!characterOrgasming.isPlayer()) {
							return UtilText.parse(characterOrgasming, target,
									" all over [npc.her] "+groinText
									+ " [npc.She] grins as [npc.her] [npc.cum+] splatters onto [npc.herHim], and [npc.she] can't help but let out [npc.a_moan] as [npc.she] feels it running down over [npc.her] [npc.skin].");
							
						} else {
							return UtilText.parse(characterOrgasming, target,
									" all over your "+groinText
									+ " You grin as your [pc.cum+] splatters onto you, and you can't help but let out [pc.a_moan] as you feel it running down over your [pc.skin].");
						}
					}
					
				case SELF_STOMACH:
					if (characterOrgasming.getHighestZLayerCoverableArea(CoverableArea.STOMACH)!=null) {
						return getClothingCummedOnText(characterOrgasming, CoverableArea.STOMACH);
					} else {
						if(characterOrgasming.isPlayer()) {
							return UtilText.parse(characterOrgasming,
									" all over your stomach. You can't help but let out [pc.a_moan] as you feel it running"
											+ " down over your [pc.skin].");
						} else {
							return UtilText.parse(characterOrgasming,
									" all over [npc.her] stomach. [npc.She] can't help but let out [npc.a_moan] as"
											+ " [npc.she] feels it running down over [npc.her] [npc.skin].");
							
						}
					}
					
				case SELF_LEGS:
					if (characterOrgasming.getHighestZLayerCoverableArea(CoverableArea.LEGS)!=null) {
						return getClothingCummedOnText(characterOrgasming, CoverableArea.LEGS);
					} else {
						if(characterOrgasming.isPlayer()) {
							return UtilText.parse(characterOrgasming,
									" all over your legs. You can't help but let out [pc.a_moan] as you feel it running"
											+ " down over your [pc.skin].");
						} else {
							return UtilText.parse(characterOrgasming,
									" all over [npc.her] legs. [npc.She] can't help but let out [npc.a_moan] as"
											+ " [npc.she] feels it running down over [npc.her] [npc.skin].");
							
						}
					}

				case SELF_FEET:
					if (characterOrgasming.getHighestZLayerCoverableArea(CoverableArea.FEET)!=null) {
						return getClothingCummedOnText(characterOrgasming, CoverableArea.FEET);
					} else {
						if(characterOrgasming.isPlayer()) {
							return UtilText.parse(characterOrgasming,
									" all over your [pc.feet+]. You can't help but let out [pc.a_moan] as you feel it running"
											+ " down over your [pc.toes+].");
						} else {
							return UtilText.parse(characterOrgasming,
									" all over [npc.her] [npc.feet+]. [npc.She] can't help but let out [npc.a_moan] as"
											+ " [npc.she] feels it running down over [npc.her] [npc.toes+].");
							
						}
					}
					
				case SELF_BREASTS:
					if (characterOrgasming.getHighestZLayerCoverableArea(CoverableArea.BREASTS)!=null) {
						return getClothingCummedOnText(characterOrgasming, CoverableArea.BREASTS);
					} else {
						if(characterOrgasming.isPlayer()) {
							return UtilText.parse(characterOrgasming,
									" all over your [npc.breasts]. You can't help but let out [npc.a_moan] as you feel"
											+ " it running down over your [npc.breastsSkin].");
						} else {
							return UtilText.parse(characterOrgasming,
									" all over [npc.namePos] [npc.breasts]. [npc.She] can't help but let out [npc.a_moan]"
											+ " as [npc.she] feels it running down over [npc.her] [npc.breastsSkin].");
							
						}
					}
				case SELF_FACE:
					if (characterOrgasming.getHighestZLayerCoverableArea(CoverableArea.MOUTH)!=null) {
						return getClothingCummedOnText(characterOrgasming, CoverableArea.MOUTH);
					} else {
						if(characterOrgasming.isPlayer()) {
							return UtilText.parse(characterOrgasming,
									" all over your [pc.face+]. You can't help but let out [pc.a_moan] as you feel it"
											+ " running down over your [pc.faceSkin].");
						} else {
							return UtilText.parse(characterOrgasming,
									" all over [npc.namePos] [npc.face+]. [npc.She] can't help but let out [npc.a_moan]"
											+ " as [npc.she] feels it running down over [npc.her] [npc.faceSkin].");
							
						}
					}
					
				case LILAYA_PANTIES:
					LilayasRoom.lilayasPanties.setDirty(true);
					return UtilText.parse(characterOrgasming,
							" directly into Lilaya's panties."
							+ " You can't help but let out [pc.a_moan+] as you watch your [pc.cum+] pool in the soft fabric,"
								+ " and you give your [pc.cock+] a few extra strokes as you imagine your demonic aunt blushing as she slides the cum-saturated underwear up over her hot pussy.");
			}
			
			// Continued description for cumming inside:
			
			SexAreaInterface areaContacted = Sex.getAllContactingSexAreas(characterOrgasming, SexAreaPenetration.PENIS).get(0);

			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ANUS:
						if(target.isPlayer()) {
							cumTargetSB.append(" deep into your [npc2.asshole+].");
						} else {
							cumTargetSB.append(" deep into [npc2.namePos] [npc2.asshole+].");
						}
						switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								if(characterOrgasming.isPlayer()) {
									cumTargetSB.append(" After a few seconds, [npc2.name] realises that you're not even close to stopping, and as your "
											+"[pc.cum+] backs up and starts drooling out of [npc2.her] [npc2.asshole], [npc2.she] lets out [npc2.a_moan+]."
													+ " You keep your [npc1.cock] hilted deep in [npc2.her] ass, [npc1.moaning+] as you wait for your [npc1.balls] to run dry.");
								} else {
									cumTargetSB.append(" After a few seconds, you realise that [npc1.nameIs] not even close to stopping, and as you feel [npc1.her] "
											+"[npc.cum+] backing up and drooling out of your [npc2.asshole], you let out [pc.a_moan+]."
											+ " [npc1.Name] keeps [npc1.her] [npc1.cock] hilted deep in your ass, [npc1.moaning+] as [npc.she] waits for [npc1.her] [npc1.balls] to run dry.");
								}
								break;
							default:
								break;
						}
						if(Main.getProperties().hasValue(PropertyValue.inflationContent) && !target.isVisiblyPregnant()) {
							int cumAmount = target.getTotalFluidInArea(SexAreaOrifice.ANUS) + characterOrgasming.getPenisRawOrgasmCumQuantity();
							cumTargetSB.append(getInflationText(characterOrgasming, target, cumAmount));
						}
						break;
						
					case ASS:
						if (target.getHighestZLayerCoverableArea(CoverableArea.ASS)!=null) {
							return getClothingCummedOnText(characterOrgasming, target, CoverableArea.ASS);
						} else {
							cumTargetSB.append(" all over [npc2.namePos] back and [npc2.ass+].");
	
							switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
								case SIX_EXTREME: case SEVEN_MONSTROUS:
									cumTargetSB.append(" After a few seconds, [npc2.name] [npc2.verb(realise)] that [npc1.nameIs] not even close to stopping, and after just a moment more,"
											+ " [npc2.her] [npc2.ass+] is absolutely drenched in [npc.cum+].");
									break;
								default:
									break;
							}
						}
						break;
						
					case BREAST:
						if (target.getHighestZLayerCoverableArea(CoverableArea.BREASTS)!=null) {
							return getClothingCummedOnText(characterOrgasming, target, CoverableArea.BREASTS);
						} else {
							if(target.hasBreasts()) {
								cumTargetSB.append(" all over [npc2.namePos] [npc2.breasts+] and face.");
							} else {
								cumTargetSB.append(" all over [npc2.namePos] flat chest and face.");
							}
							switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
								case SIX_EXTREME: case SEVEN_MONSTROUS:
									cumTargetSB.append(" After a few seconds, [npc2.name] [npc2.verb(realise)] that [npc1.nameIs] not even close to stopping, and after just a moment more,"
											+ " [npc2.her] torso is absolutely drenched in [npc.cum+].");
									break;
								default:
									break;
							}
						}
						break;
						
					case MOUTH:
						if(target.isPlayer()) {
							cumTargetSB.append(" deep down your throat, and you find yourself making muffled whining noises as you feel the [npc.cum+] sliding down into your stomach.");
							switch(characterOrgasming.getCumFlavour()) {
								case BEER:
									cumTargetSB.append(" The unusual taste of [npc1.namePos] beer-flavoured");
									break;
								case CHOCOLATE:
									cumTargetSB.append(" The unusual taste of [npc1.namePos] chocolate-flavoured");
									break;
								case CUM:
									cumTargetSB.append(" The salty taste of");
									break;
								case GIRL_CUM:
									cumTargetSB.append(" The unusual taste of [npc1.namePos] sweet");
									break;
								case HONEY:
									cumTargetSB.append(" The unusual taste of [npc1.namePos] honey-flavoured");
									break;
								case MILK:
									cumTargetSB.append(" The unusual taste of [npc1.namePos] milk-flavoured");
									break;
								case MINT:
									cumTargetSB.append(" The unusual taste of [npc1.namePos] mint-flavoured");
									break;
								case PINEAPPLE:
									cumTargetSB.append(" The unusual taste of [npc1.namePos] pineapple-flavoured");
									break;
								case SLIME:
									cumTargetSB.append(" The unusual taste of [npc1.namePos] sweet");
									break;
								case STRAWBERRY:
									cumTargetSB.append(" The unusual taste of [npc1.namePos] strawberry-flavoured");
									break;
								case VANILLA:
									cumTargetSB.append(" The unusual taste of [npc1.namePos] vanilla-flavoured");
									break;
							}
							cumTargetSB.append(" cum rises up to hit your [npc2.tongue], and you");
							if(target.hasFetish(Fetish.FETISH_CUM_ADDICT) || SexFlags.playerRequestedCreampie)
							{
								cumTargetSB.append(" " + UtilText.returnStringAtRandom("greedily","hungrily"));
							}
							else
							{
								cumTargetSB.append("'re left with no other option but to");
							}
							cumTargetSB.append(" gulp down as much of the");
							if(!characterOrgasming.getCumModifiers().isEmpty()) {
								switch(characterOrgasming.getCumModifiers().get(Util.random.nextInt(characterOrgasming.getCumModifiers().size()))) { //TODO specials for ALCOHOLIC & HALLUCINOGENIC
									case ADDICTIVE:
										cumTargetSB.append(" delicious, highly-addictive [npc1.cum] as you possibly can.");
										break;
									case ALCOHOLIC:
										cumTargetSB.append(" alcoholic [npc1.cum] as you possibly can.");
										break;
									case BUBBLING:
										cumTargetSB.append(" bubbling, fizzy [npc1.cum] as you possibly can.");
										break;
									case HALLUCINOGENIC:
										cumTargetSB.append(" psychoactive [npc1.cum] as you possibly can.");
										break;
									case MUSKY:
										cumTargetSB.append(" musky [npc1.cum] as you possibly can.");
										break;
									case SLIMY:
										cumTargetSB.append(" slimy [npc1.cum] as you possibly can.");
										break;
									case STICKY:
										cumTargetSB.append(" sticky [npc1.cum] as you possibly can.");
										break;
									case VISCOUS:
										cumTargetSB.append(" thick, viscous [npc1.cum] as you possibly can.");
										break;
								}
							} else {
								cumTargetSB.append(" [npc1.cum] as you possibly can.");
							}
							
						} else {
							cumTargetSB.append(" deep down [npc2.namePos] throat.");
						}
						switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								if(characterOrgasming.isPlayer()) {
									cumTargetSB.append(" After a few seconds, [npc2.name] realises that you're not even close to stopping, and as your "
											+"[pc.cum+] backs up and starts drooling out the corners of [npc2.her] mouth, [npc2.she] lets out a desperate, muffled [npc2.moan]."
													+ " You keep your [npc1.cock] hilted deep down [npc2.her] throat, [npc1.moaning+] as you wait for your [npc1.balls] to run dry.");
								} else {
									cumTargetSB.append(" After a few seconds, you realise that [npc1.namePos] not even close to stopping, and as you feel [npc1.her] "
											+"[npc.cum+] backing up and drooling out the corners of your mouth, you let out a desperate, muffled [pc.moan]."
											+ " [npc1.Name] keeps [npc1.her] [npc1.cock] hilted deep down your throat, [npc1.moaning+] as [npc.she] waits for [npc1.her] [npc1.balls] to run dry.");
								}
								break;
							default:
								break;
						}
						if(Main.getProperties().hasValue(PropertyValue.inflationContent) && !target.isVisiblyPregnant()) {
							int cumAmount = target.getTotalFluidInArea(SexAreaOrifice.MOUTH) + characterOrgasming.getPenisRawOrgasmCumQuantity();
							cumTargetSB.append(getInflationText(characterOrgasming, target, cumAmount));
						}
						break;
						
					case NIPPLE:
						if(target.isPlayer()) {
							cumTargetSB.append(" deep into your [pc.breasts+], and you find yourself whining and moaning as you feel the [npc.cum+] deep inside of your [pc.breasts+].");
						} else {
							cumTargetSB.append(" deep into [npc2.namePos] [npc2.breasts+].");
						}
						if(Main.getProperties().hasValue(PropertyValue.inflationContent)) {
							int cumAmount = target.getTotalFluidInArea(SexAreaOrifice.NIPPLE) + characterOrgasming.getPenisRawOrgasmCumQuantity();
							cumTargetSB.append(getBreastInflationText(characterOrgasming, target, cumAmount));
						}
						break;
						
					case THIGHS:
						if (target.getHighestZLayerCoverableArea(CoverableArea.THIGHS)!=null) {
							return getClothingCummedOnText(characterOrgasming, target, CoverableArea.THIGHS);
						} else {
							cumTargetSB.append(" all over [npc2.namePos] thighs.");
							
							switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
								case SIX_EXTREME: case SEVEN_MONSTROUS:
									cumTargetSB.append(" After a few seconds, [npc2.name] [npc2.verb(realise)] that [npc1.nameIs] not even close to stopping, and after just a moment more,"
											+ " [npc2.her] [npc2.legs+] are absolutely drenched in [npc.cum+].");
									break;
								default:
							}
						}
						break;
						
					case URETHRA_PENIS: case URETHRA_VAGINA: //TODO
						if(target.isPlayer()) {
							cumTargetSB.append(" deep into your urethra.");
						} else {
							cumTargetSB.append(" deep into [npc2.namePos] urethra.");
						}
						switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								if(characterOrgasming.isPlayer()) {
									cumTargetSB.append(" After a few seconds, [npc2.name] realises that you're not even close to stopping, and as your "
											+"[pc.cum+] backs up and starts drooling out of [npc2.her] urethra, [npc2.she] lets out [npc2.a_moan+]."
													+ " You keep your [npc1.cock] hilted deep inside of [npc2.herHim], [npc1.moaning+] as you wait for your [npc1.balls] to run dry.");
								} else {
									cumTargetSB.append(" After a few seconds, you realise that [npc1.namePos] not even close to stopping, and as you feel [npc1.her] "
											+"[npc.cum+] backing up and drooling out of your urethra, you let out [pc.a_moan+]."
											+ " [npc1.Name] keeps [npc1.her] [npc1.cock] hilted deep inside of you, [npc1.moaning+] as [npc.she] waits for [npc1.her] [npc1.balls] to run dry.");
								}
								break;
							default:
								break;
						}
						if(Main.getProperties().hasValue(PropertyValue.inflationContent) && !target.isVisiblyPregnant()) {
							int cumAmount = target.getTotalFluidInArea((SexAreaOrifice) areaContacted) + characterOrgasming.getPenisRawOrgasmCumQuantity();
							cumTargetSB.append(getInflationText(characterOrgasming, target, cumAmount));
						}
						break;
						
					case VAGINA:
						if(target.isPlayer()) {
							if(!target.isVisiblyPregnant()) {
								cumTargetSB.append(" deep into your waiting womb, and you find yourself whining and moaning as you wonder if the [npc.cum+] will get you pregnant.");
							} else {
								cumTargetSB.append(" deep into your hungry [pc.pussy], and you find yourself whining and moaning as you feel the [npc.cum+] deep inside of you.");
							}
						} else {
							if(!target.isVisiblyPregnant()) {
								cumTargetSB.append(" deep into [npc2.namePos] waiting womb.");
							} else {
								cumTargetSB.append(" deep into [npc2.namePos] [npc2.pussy+].");
							}
						}
						switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								if(characterOrgasming.isPlayer()) {
									cumTargetSB.append(" After a few seconds, [npc2.name] realises that you're not even close to stopping, and as your [pc.cum+] backs up and starts drooling out of [npc2.her] [npc2.pussy],"
												+ " [npc2.she] squirms about and lets out a series of [npc2.moans+]."
												+ " You keep your [npc1.cock] hilted deep in [npc2.her] [npc2.pussy], [npc1.moaning+] as you wait for your [npc1.balls] to run dry.");
								} else {
									cumTargetSB.append(" After a few seconds, you realise that [npc1.namePos] not even close to stopping, and as you feel [npc1.her] "
											+"[npc1.cum+] backing up and drooling out of your [npc2.pussy], you let out [pc.a_moan+]."
											+ " [npc1.Name] keeps [npc1.her] [npc1.cock] hilted deep in your [npc2.pussy], [npc1.moaning+] as [npc.she] waits for [npc1.her] [npc1.balls] to run dry.");
								}
								break;
							default:
								break;
						}
						if(Main.getProperties().hasValue(PropertyValue.inflationContent) && !target.isVisiblyPregnant()) {
							int cumAmount = target.getTotalFluidInArea(SexAreaOrifice.VAGINA) + characterOrgasming.getPenisRawOrgasmCumQuantity();
							cumTargetSB.append(getInflationText(characterOrgasming, target, cumAmount));
						}
						break;
				}
			} else {
				switch((SexAreaPenetration)areaContacted) {
					case CLIT:
						break;
					case FINGER:
						if (target.getHighestZLayerCoverableArea(CoverableArea.HANDS)!=null) {
							return getClothingCummedOnText(characterOrgasming, target, CoverableArea.HANDS);
						} else {
							cumTargetSB.append(" all over [npc2.namePos] [npc2.fingers+].");
							
							switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
								case SIX_EXTREME: case SEVEN_MONSTROUS:
									cumTargetSB.append(" After a few seconds, [npc2.name] [npc2.verb(realise)] that [npc1.nameIs] not even close to stopping, and after just a moment more,"
											+ " [npc2.her] [npc2.hands+] are absolutely drenched in [npc.cum+].");
									break;
								default:
									break;
							}
						}
						break;
					case PENIS:
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case FOOT:
						if (target.getHighestZLayerCoverableArea(CoverableArea.FEET)!=null) {
							return getClothingCummedOnText(characterOrgasming, target, CoverableArea.FEET);
						} else {
							cumTargetSB.append(" all over [npc2.namePos] [npc2.toes+].");
							
							switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
								case SIX_EXTREME: case SEVEN_MONSTROUS:
									cumTargetSB.append(" After a few seconds, [npc2.name] [npc2.verb(realise)] that [npc1.nameIs] not even close to stopping, and after just a moment more,"
											+ " [npc2.her] [npc2.feet+] are absolutely drenched in [npc.cum+].");
									break;
								default:
									break;
							}
						}
						break;
					case TONGUE:
						break;
				}
			}
		}
		
		if(target!=null) {
			switch(target.getBodyMaterial()) {
				case AIR:
				case ARCANE:
				case WATER:
				case SLIME:
					cumTargetSB.append("<br/>"
							+ "As [npc2.namePos] body is made completely out of translucent "+target.getBodyMaterial().getName()+","
									+ " you're able to see the cloud of [npc.namePos] [npc1.cum+] shooting up and dispersing inside of [npc2.herHim].");
					break;
				case FIRE:
				case FLESH:
				case ICE:
				case RUBBER:
				case STONE:
					break;
			}
			return UtilText.parse(characterOrgasming, target, cumTargetSB.toString());
			
		} else {
			return UtilText.parse(characterOrgasming, cumTargetSB.toString());
		}
	}
	
	private static String getClothingCummedOnText(GameCharacter characterOrgasming, GameCharacter target, CoverableArea area) {
		if(!target.isPlayer()) {
			if(characterOrgasming.isPlayer()) {
				return UtilText.parse(target,
						" all over [npc.namePos] "+target.getHighestZLayerCoverableArea(area).getName()+"."
								+ " You grin as your [pc.cum+] splatters onto [npc.her] clothing, making a mess of [npc.her] outfit.");
			} else {
				return UtilText.parse(characterOrgasming, target,
						" all over [npc2.namePos] "+target.getHighestZLayerCoverableArea(area).getName()+"."
								+ " [npc1.Name] grins as [npc1.her] [npc1.cum+] splatters onto [npc2.namePos] clothing, making a mess of [npc2.her] outfit.");
			}
		} else {
			return UtilText.parse(characterOrgasming,
					" all over your "+target.getHighestZLayerCoverableArea(area).getName()+"."
					+ " [npc1.Name] grins as [npc1.her] [npc1.cum+] splatters onto your clothing, making a mess of your outfit.");
		}
	}

	private static String getClothingCummedOnText(GameCharacter characterOrgasming, CoverableArea area) {
			if(characterOrgasming.isPlayer()) {
				return UtilText.parse(characterOrgasming,
						" all over your "+characterOrgasming.getHighestZLayerCoverableArea(area).getName()+"."
								+ " You let out [pc.a_moan+] as your [pc.cum+] splatters onto your clothing,"
								+ " making a mess of your outfit.");
			} else {
				return UtilText.parse(characterOrgasming,
						" all over [npc.her] "+characterOrgasming.getHighestZLayerCoverableArea(area).getName()+"."
								+ " [npc.She] lets out [npc.a_moan+] as [npc.her] [npc.cum+] splatters onto"
								+ " [npc.her] clothing, making a mess of [npc.her] outfit.");
			}
	}
	
	private static String getInflationText(GameCharacter characterOrgasming, GameCharacter target, int cumAmount) {
		if(characterOrgasming.isPlayer()) {
			if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
				return ("<br/>You see [npc2.namePos] stomach swell out to a massive, over-inflated size as it distends from the colossal amount of cum that you've pumped inside of [npc2.herHim]."
						+ " Placing a [npc1.hand] on [npc2.her] huge belly, you grin as you think to yourself that [npc2.she] now looks as though [npc2.sheIs] heavily pregnant.");
				
			} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
				return ("<br/>You see [npc2.namePos] stomach swell out as it distends from the huge amount of cum that you've pumped inside of [npc2.herHim]."
						+ " Placing a [npc1.hand] on [npc2.her] belly, you grin as you think to yourself that [npc2.she] now looks as though [npc2.sheIs] pregnant.");
				
			} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
				return ("<br/>You see [npc2.namePos] stomach swell out a little as it distends from the sheer amount of cum that you've pumped inside of [npc2.herHim]."
						+ " Placing a [npc1.hand] on [npc2.her] belly, you grin as you think to yourself that [npc2.she] now looks as though [npc2.sheIs] in the early stages of pregnancy.");
			}
		} else {
			if(target.isPlayer()) {
				if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
					return ("<br/>You feel your stomach swell out to a massive, over-inflated size as it distends from the colossal amount of cum that's been pumped inside of you."
							+ " Placing a [npc1.hand] on your belly, [npc1.name] grins as [npc1.she] remarks on the fact that you now look as though you're heavily pregnant.");
					
				} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
					return ("<br/>You feel your stomach swell out a little as it distends from the huge amount of cum that's been pumped inside of you."
							+ " Placing a [npc1.hand] on your belly, [npc1.name] grins as [npc1.she] remarks on the fact that you now look as though you're pregnant.");
					
				} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
					return ("<br/>You feel your stomach swell out a little as it distends from the sheer amount of cum that's been pumped inside of you."
							+ " Placing a [npc1.hand] on your belly, [npc1.name] grins as [npc1.she] remarks on the fact that you now look as though you're in the early stages of pregnancy.");
				}
			} else {
				if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
					return ("<br/>[npc2.NamePos] stomach swells out to a massive, over-inflated size as it distends from the sheer amount of cum that [npc1.namePos] pumped inside of [npc2.herHim]."
							+ " Placing a [npc1.hand] on [npc2.her] belly, the [npc1.race] grins as [npc1.she] remarks on the fact that [npc2.name] now looks as though [npc2.sheIs] heavily pregnant.");
					
				} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
					return ("<br/>[npc2.NamePos] stomach swells out as it distends from the huge amount of cum that [npc1.namePos] pumped inside of [npc2.herHim]."
							+ " Placing a [npc1.hand] on [npc2.her] belly, the [npc1.race] grins as [npc1.she] remarks on the fact that [npc2.name] now looks as though [npc2.sheIs] pregnant.");
					
				} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
					return ("<br/>[npc2.NamePos] stomach swells out a little as it distends from the sheer amount of cum that [npc1.namePos] pumped inside of [npc2.herHim]."
							+ " Placing a [npc1.hand] on [npc2.her] belly, the [npc1.race] grins as [npc1.she] remarks on the fact that [npc2.name] now looks as though [npc2.sheIs] in the early stages of pregnancy.");
				}
			}
		}
		return "";
	}
	
	private static String getBreastInflationText(GameCharacter characterOrgasming, GameCharacter target, int cumAmount) {
		if(characterOrgasming.isPlayer()) {
			if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
				return ("<br/>You see [npc2.namePos] [npc2.breasts] swell out to a massive, over-inflated size as they distends from the colossal amount of cum that you've pumped inside of them.");
				
			} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
				return ("<br/>You see [npc2.namePos] [npc2.breasts] swell out a little as they distend from the huge amount of cum that you've pumped inside of them.");
				
			} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
				return ("<br/>You see [npc2.namePos] [npc2.breasts] swell out a little as they distend from the sheer amount of cum that you've pumped inside of them.");
			}
		} else {
			if(target.isPlayer()) {
				if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
					return ("<br/>You feel your [npc2.breasts] swell out to a massive, over-inflated size as they distend from the colossal amount of cum that's been pumped inside of them.");
					
				} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
					return ("<br/>You feel your [npc2.breasts] swell out a little as they distend from the huge amount of cum that's been pumped inside of them.");
					
				} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
					return ("<br/>You feel your [npc2.breasts] swell out a little as they distend from the sheer amount of cum that's been pumped inside of them.");
				}
			} else {
				if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
					return ("<br/>[npc2.NamePos] [npc2.breasts] swell out to a massive, over-inflated size as they distend from the sheer amount of cum that [npc1.namePos] pumped inside of them.");
					
				} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
					return ("<br/>[npc2.NamePos] [npc2.breasts] swell out as they distend from the huge amount of cum that [npc1.namePos] pumped inside of them.");
					
				} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
					return ("<br/>[npc2.NamePos] [npc2.breasts] swell out a little as they distend from the sheer amount of cum that [npc1.namePos] pumped inside of them.");
				}
			}
		}
		return "";
	}
	
	private static String getGenericVaginaOrgasmDescription(GameCharacter characterOrgasming, OrgasmCumTarget targetArea) {
		genericOrgasmSB.setLength(0);
		
		if(characterOrgasming.isPlayer()) {
			genericOrgasmSB.append("A desperate, shuddering heat suddenly crashes up from your [npc1.pussy+], and you let out a manic squeal as a blinding wave of pure ecstasy washes over you.");
		} else {
			genericOrgasmSB.append("A desperate, shuddering heat suddenly crashes up from [npc1.namePos] [npc1.pussy+], and [npc1.she] lets out a manic squeal as a blinding wave of pure ecstasy washes over [npc1.herHim].");
		}
		
		GameCharacter characterPenetrating = null;
		if(Sex.getCharacterContactingSexArea(characterOrgasming, SexAreaOrifice.VAGINA).size()>0) {
			characterPenetrating = Sex.getCharacterContactingSexArea(characterOrgasming, SexAreaOrifice.VAGINA).get(0);
		}
		SexAreaPenetration penetration = Sex.getFirstContactingSexAreaPenetration(characterOrgasming, SexAreaOrifice.VAGINA);
		
		if(characterPenetrating!=null && penetration!=null) {
			switch(penetration) {
				case FINGER:
					if(characterOrgasming.isPlayer()) {
						if(characterPenetrating.isPlayer()) {
							genericOrgasmSB.append(" You curl your fingers up deep inside your [npc1.pussy+], and, while desperately stroking in a 'come-hither' motion,"
									+ " you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around your intruding digits.");
						} else {
							genericOrgasmSB.append(" [npc2.NamePos] fingers carry on pumping away at your [npc1.pussy+], and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around the intruding digits.");
						}
					} else {
						if(characterPenetrating.isPlayer()) {
							genericOrgasmSB.append(" [npc1.NamePos] vaginal muscles grip and squeeze around your intruding digits,"
									+ " and you continue to stroke and tease [npc1.her] clit, drawing out a series of [npc1.moans+] from between [npc1.her] [npc1.lips+].");
						} else {
							genericOrgasmSB.append(" [npc1.NamePos] vaginal muscles grip and squeeze around [npc2.namePos] intruding digits,"
									+ " and [npc2.she] continues to stroke and tease [npc1.her] clit, drawing out a series of [npc1.moans+] from between [npc1.her] [npc1.lips+].");
						}
					}
					break;
				case PENIS:
					if(characterOrgasming.isPlayer()) {
						if(characterPenetrating.isPlayer()) {
							genericOrgasmSB.append(" You carry on fucking yourself through your orgasm, letting out a series of high-pitched moans as your vaginal muscles grip and squeeze around your [npc1.cock+].");
						} else {
							genericOrgasmSB.append(" [npc2.Name] carries on fucking your [npc1.pussy+] through your orgasm, and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around [npc2.her] [npc2.penis+].");
						}
					} else {
						if(characterPenetrating.isPlayer()) {
							genericOrgasmSB.append(" You carry on fucking [npc1.name] through [npc1.her] orgasm, causing [npc1.herHim] to let out a series of high-pitched moans as [npc1.her] vaginal muscles grip and squeeze around your [npc2.cock+].");
						} else {
							genericOrgasmSB.append(" [npc2.Name] carries on fucking [npc1.namePos] [npc1.pussy+] through [npc1.her] orgasm,"
											+ " causing [npc1.herHim] to let out a series of high-pitched moans as [npc1.her] vaginal muscles grip and squeeze around [npc2.her] [npc2.penis+].");
						}
					}
					break;
				case TAIL:
					if(characterOrgasming.isPlayer()) {
						if(characterPenetrating.isPlayer()) {
							genericOrgasmSB.append(" You carry on tail-fucking yourself through your orgasm, letting out a series of high-pitched moans as your vaginal muscles grip and squeeze around the intruding object.");
						} else {
							genericOrgasmSB.append(" [npc2.Name] carries on tail-fucking your [npc1.pussy+] through your orgasm, and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around the intruding object.");
						}
					} else {
						if(characterPenetrating.isPlayer()) {
							genericOrgasmSB.append(" You carry on tail-fucking [npc1.name] through [npc1.her] orgasm,"
											+ " causing [npc1.herHim] to let out a series of high-pitched moans as [npc1.her] vaginal muscles grip and squeeze around the intruding object.");
						} else {
							genericOrgasmSB.append(" [npc2.Name] carries on tail-fucking [npc1.namePos] [npc1.pussy+] through [npc1.her] orgasm,"
											+ " causing [npc1.herHim] to let out a series of high-pitched moans as [npc1.her] vaginal muscles grip and squeeze around the intruding object.");
						}
					}
					break;
				case TONGUE:
					if(characterOrgasming.isPlayer()) {
						if(characterPenetrating.isPlayer()) {
							genericOrgasmSB.append(" You carry on licking and kissing your clit as you orgasm, letting out a series of high-pitched moans as you feel your vaginal muscles quiver and contract at the overwhelming sensation.");
						} else {
							genericOrgasmSB.append(" [npc2.Name] carries on licking and kissing at your clit while you orgasm,"
										+ " causing you to let out a series of high-pitched moans as you feel your vaginal muscles quiver and contract at the overwhelming sensation.");
						}
					} else {
						if(characterPenetrating.isPlayer()) {
							genericOrgasmSB.append(" You carry on licking and kissing [npc1.namePos] clit as [npc1.she] orgasms,"
										+ " causing [npc1.herHim] to let out a series of high-pitched moans as [npc1.her] vaginal muscles quiver and contract at the overwhelming sensation.");
						} else {
							genericOrgasmSB.append(" [npc2.Name] carries on licking and kissing at [npc1.namePos] clit while [npc1.she] orgasms,"
										+ " causing [npc1.herHim] to let out a series of high-pitched moans as [npc1.her] vaginal muscles quiver and contract at the overwhelming sensation.");
						}
					}
					break;
				case TENTACLE: //TODO
					break;
				case CLIT:
					if(characterOrgasming.isPlayer()) {
						if(characterPenetrating.isPlayer()) {
							genericOrgasmSB.append(" You carry on clit-fucking yourself through your orgasm, letting out a series of high-pitched moans as your vaginal muscles grip and squeeze around your [npc1.clit+].");
						} else {
							genericOrgasmSB.append(" [npc2.Name] carries on clit-fucking your [npc1.pussy+] through your orgasm, and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around [npc2.her] [npc2.clit+].");
						}
					} else {
						if(characterPenetrating.isPlayer()) {
							genericOrgasmSB.append(" You carry on clit-fucking [npc1.name] through [npc1.her] orgasm,"
									+ " causing [npc1.herHim] to let out a series of high-pitched moans as [npc1.her] vaginal muscles grip and squeeze around your [npc2.clit+].");
						} else {
							genericOrgasmSB.append(" [npc2.Name] carries on clit-fucking [npc1.namePos] [npc1.pussy+] through [npc1.her] orgasm,"
											+ " causing [npc1.herHim] to let out a series of high-pitched moans as [npc1.her] vaginal muscles grip and squeeze around [npc2.her] [npc2.clit+].");
						}
					}
					break;
				case FOOT: //TODO
					break;
			}
			
		} else { // No penetration:
			if(characterOrgasming.isPlayer()) {
				genericOrgasmSB.append(" Your [npc1.pussy+] clenches down hard, and the wave of disappointment upon finding itself empty almost overwhelms the pleasure that radiates up through your groin.");
			} else {
				genericOrgasmSB.append(" [npc1.NamePos] [npc1.pussy+] clenches down hard, and the wave of disappointment upon finding itself empty almost overwhelms the pleasure that radiates up through [npc.her] groin.");
			}
		}
		
		if(targetArea == OrgasmCumTarget.LILAYA_PANTIES && !Main.game.getPlayer().hasPenisIgnoreDildo()) {
			genericOrgasmSB.append(" As you squeal and pant, you bring Lilaya's panties up to your face, and breathe in your demonic aunt's musky, perfume-laced scent as you imagine her masturbating into the soft fabric.");
		}
		
		if(characterOrgasming.isVaginaSquirter()) {
			if(characterOrgasming.isPlayer()) {
				genericOrgasmSB.append(" As your inner muscles spasm and quiver with delight, a huge spurt of female ejaculate squirts out from your [npc1.pussy+].");
				if(targetArea == OrgasmCumTarget.LILAYA_PANTIES) {
					genericOrgasmSB.append(" You quickly drop Lilaya's panties down between your legs, squirting directly into her underwear as you let out [pc.a_moan+].");
					LilayasRoom.lilayasPanties.setDirty(true);
				}
			} else {
				genericOrgasmSB.append(" As [npc1.namePos] inner muscles spasm and quiver with delight, a huge spurt of female ejaculate squirts out from [npc1.her] [npc1.pussy+].");
			}
		}
		
		if(characterOrgasming.isPlayer()) {	
			genericOrgasmSB.append(" With a deeply-satisfied sigh, your feminine climax starts to fade, and you take a few deep gasps of air as you seek to catch your breath.");
		} else {
			genericOrgasmSB.append(" With a deeply-satisfied sigh, [npc1.namePos] feminine climax starts to fade, and [npc1.she] takes a few deep gasps of air as [npc1.she] seeks to catch [npc1.her] breath.");
		}
		
		if(characterPenetrating!=null) {
			return UtilText.parse(characterOrgasming, characterPenetrating, genericOrgasmSB.toString());
		} else {
			return UtilText.parse(characterOrgasming, genericOrgasmSB.toString());
		}
	}
	
	private static String getGenericMoundOrgasm(GameCharacter characterOrgasming) {
		if(characterOrgasming.isPlayer()) {
			return "With an ear-splitting scream and trembling legs, a crashing wave of pure ecstasy suddenly washes through you."
					+ " The muscles within your genderless mound start to spasm and contract, and you're soon left as a panting, moaning wreck as an intense pseudo-orgasm crashes over you.";
			
		} else {
			return UtilText.parse(characterOrgasming,
					"With an ear-splitting scream and trembling legs, a crashing wave of pure ecstasy suddenly washes through [npc.name]."
							+ " The muscles within [npc.her] genderless mound start to spasm and contract, and [npc.sheIs] soon left as a panting, moaning wreck as [npc.her] intense pseudo-orgasm crashes over [npc.herHim].");
		}
		
	}

	public static String getGenericOrgasmDescription(GameCharacter characterOrgasming, OrgasmCumTarget target) {
		StringBuilder descriptionSB = new StringBuilder();

		GameCharacter characterPenetrated = null;
		
		if(!Sex.getAllContactingSexAreas(characterOrgasming, SexAreaPenetration.PENIS).isEmpty()) {
			characterPenetrated = Sex.getCharactersHavingOngoingActionWith(characterOrgasming, SexAreaPenetration.PENIS).get(0);
		}
		
		if(characterOrgasming.hasPenisIgnoreDildo()) {
			descriptionSB.append("<p>"
									+getGenericPenisOrgasmDescription(characterOrgasming, characterPenetrated, target)
								+"</p>");
		}
		
		if(characterOrgasming.hasVagina()) {
			descriptionSB.append("<p>"
									+getGenericVaginaOrgasmDescription(characterOrgasming, target)
								+"</p>");
		}
		
		if(!characterOrgasming.hasPenisIgnoreDildo() && !characterOrgasming.hasVagina()) {
			descriptionSB.append("<p>"
									+getGenericMoundOrgasm(characterOrgasming)
								+"</p>");
		}
		
		return descriptionSB.toString();
	}
	
	// Doesn't have penis (or penis is not exposed), and isn't being vaginally penetrated:
	public static final SexAction PLAYER_GENERIC_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "Orgasm";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (!Main.game.getPlayer().hasPenisIgnoreDildo() || !Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS) || Main.game.getPlayer().isWearingCondom())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().get(0));
		}
		
		@Override
		public void applyEffects() {
			if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Main.game.getPlayer().getPenisOrgasmCumQuantity() != CumProduction.ZERO_NONE) {
				Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(true);
			}
		}
	};
	
	public static final SexAction PLAYER_GENERIC_ORGASM_CREAMPIE = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			GameCharacter characterPenetrated = Sex.getCharactersHavingOngoingActionWith(Main.game.getPlayer(), SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Sex.getAllContactingSexAreas(Main.game.getPlayer(), SexAreaPenetration.PENIS).get(0);
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ANUS:
						if(Main.game.getPlayer().hasPenisModifier(PenetrationModifier.KNOTTED)) {
							return UtilText.parse(characterPenetrated, "Knot [npc.herHim]");
						} else {
							return "Anal Creampie";
						}
					case ASS:
						return "Hotdogging climax";
					case BREAST:
						if(characterPenetrated.hasBreasts()) {
							return "Paizuri climax";
						} else {
							return "Naizuri climax";
						}
					case MOUTH:
						break;
					case NIPPLE:
						return "Nipple Creampie";
					case THIGHS:
						return "Thigh-sex Climax";
					case URETHRA_PENIS: case URETHRA_VAGINA:
						return "Urethra Creampie";
					case VAGINA:
						if(Main.game.getPlayer().hasPenisModifier(PenetrationModifier.KNOTTED)) {
							return UtilText.parse(characterPenetrated, "Knot [npc.herHim]");
						} else {
							return "Creampie";
						}
				}
			} else {
				switch((SexAreaPenetration)areaContacted) {
					case CLIT:
						break;
					case FINGER:
						return "Handjob Climax";
					case PENIS:
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case FOOT:
						return "Footjob Climax";
					case TONGUE:
						break;
				}
			}
			return "Creampie";
		}

		@Override
		public String getActionDescription() {
			GameCharacter characterPenetrated = Sex.getCharactersHavingOngoingActionWith(Main.game.getPlayer(), SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Sex.getAllContactingSexAreas(Main.game.getPlayer(), SexAreaPenetration.PENIS).get(0);
			String returnString = "You've reached your climax, and can't hold back your orgasm any longer. Creampie [npc2.name].";
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ANUS:
						if(Main.game.getPlayer().hasPenisModifier(PenetrationModifier.KNOTTED)) {
							returnString = "Push your [npc.cock+] deep inside [npc2.namePos] [npc2.asshole+], knotting [npc.herHim] before filling [npc2.herHim] with your [npc.cum+].";
						} else {
							returnString = "Push your [npc.cock+] deep inside [npc2.namePos] [npc2.asshole+], before filling [npc2.herHim] with your [npc.cum+].";
						}
						break;
					case ASS:
						returnString = "Keep on hotdogging [npc2.name] as you orgasm, spurting your [npc.cum+] all over [npc2.her] back and [npc2.assSize] ass.";
						break;
					case BREAST:
						if(characterPenetrated.hasBreasts()) {
							returnString = "Keep on fucking [npc2.namePos] breasts as you orgasm, spurting your [npc.cum+] all over [npc2.her] tits and [npc2.face].";
						} else {
							returnString = "Keep on grinding against [npc2.namePos] flat chest as you orgasm, spurting your [npc.cum+] all over [npc2.her] torso and [npc2.face].";
						}
						break;
					case MOUTH:
						returnString = "Push your [npc.cock+] deep down [npc2.namePos] throat, before filling [npc2.her] stomach with your [npc.cum+].";
						break;
					case NIPPLE:
						returnString = "Push your [npc.cock+] deep into [npc2.namePos] [npc2.nipple+], before filling [npc2.her] breast with your [npc.cum+].";
						break;
					case THIGHS:
						returnString = "Keep on fucking [npc2.namePos] thighs as you start to orgasm, before pulling back and spurting your [npc.cum+] all over [npc2.her] [npc2.legs].";
						break;
					case URETHRA_PENIS:
						returnString = "Push your [npc.cock+] deep into the urethra of [npc2.namePos] [npc2.cock+], before filling [npc2.her] bladder with your [npc.cum+].";
						break;
					case URETHRA_VAGINA:
						returnString = "Push your [npc.cock+] deep into the urethra of [npc2.namePos] [npc2.pussy+], before filling [npc2.her] bladder with your [npc.cum+].";
						break;
					case VAGINA:
						if(Main.game.getPlayer().hasPenisModifier(PenetrationModifier.KNOTTED)) {
							returnString = "Push your [npc.cock+] deep inside [npc2.namePos] [npc2.pussy+], knotting [npc.herHim] before filling [npc2.her] #IFnpc2.isPregnant()#THEN[npc2.pussy]#ELSEwomb#ENDIF with your [npc.cum+].";
						} else {
							returnString = "Push your [npc.cock+] deep inside [npc2.namePos] [npc2.pussy+], before filling [npc2.her] #IFnpc2.isPregnant()#THEN[npc2.pussy]#ELSEwomb#ENDIF with your [npc.cum+].";
						}
						break;
				}
			} else {
				switch((SexAreaPenetration)areaContacted) {
					case CLIT:
						break;
					case FINGER:
						returnString = "Carry on focusing on the pleasure that you're getting from [npc2.name] giving you a handjob, before pulling back slightly and spurting your [npc.cum+] all over [npc2.her] [npc2.hand+].";
						break;
					case PENIS:
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case FOOT:
						returnString = "Carry on focusing on the pleasure that you're getting from [npc2.namePos] [npc2.footjob], before spurting your [npc.cum+] all over [npc2.her] [npc2.feet+].";
						break;
					case TONGUE:
						break;
				}
			}
			return UtilText.parse(Sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}

		@Override
		public boolean isBaseRequirementsMet() {
			if(Main.game.getPlayer().getPenisType()==PenisType.DILDO
					|| !Sex.getCharacterPerformingAction().isPlayer()
					|| Sex.getCharactersHavingOngoingActionWith(Main.game.getPlayer(), SexAreaPenetration.PENIS).isEmpty()) {
				return false;
			}
			
			SexAreaInterface areaContacted = Sex.getAllContactingSexAreas(Main.game.getPlayer(), SexAreaPenetration.PENIS).get(0);

			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ASS:
					case BREAST:
					case THIGHS:
					case ANUS:
					case MOUTH:
					case NIPPLE:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
					case VAGINA:
						return true;
				}
			} else {
				switch((SexAreaPenetration)areaContacted) {
					case CLIT:
						break;
					case FINGER:
						return true;
					case FOOT:
						return true;
					case PENIS:
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						break;
				}
			}
			
			return false;
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.INSIDE);
		}
		
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			GameCharacter characterPenetrated = Sex.getCharactersHavingOngoingActionWith(Main.game.getPlayer(), SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Sex.getAllContactingSexAreas(Main.game.getPlayer(), SexAreaPenetration.PENIS).get(0);
			
			if(cumTarget.equals(characterPenetrated)) {
				return Util.newArrayListOfValues(areaContacted);
					
			} else {
				return null;
			}
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Sex.getCharacterPerformingAction()) && cumTarget.equals(Sex.getTargetedPartner(cumProvider))) {
				SexAreaInterface areaContacted = Sex.getAllContactingSexAreas(cumProvider, SexAreaPenetration.PENIS).get(0);
	
				if(!areaContacted.isOrifice()) {
					switch((SexAreaPenetration)areaContacted) {
						case CLIT:
							break;
						case FINGER:
							return Util.newArrayListOfValues(
									CoverableArea.HANDS);
						case FOOT:
							return Util.newArrayListOfValues(
									CoverableArea.FEET);
						case PENIS:
							break;
						case TAIL:
							break;
						case TENTACLE:
							break;
						case TONGUE:
							break;
					}
				} else {
					switch((SexAreaOrifice)areaContacted) {
						case ANUS:
							break;
						case ASS:
							return Util.newArrayListOfValues(
									CoverableArea.ASS,
									CoverableArea.ANUS);
						case BREAST:
							return Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.MOUTH);
						case MOUTH:
							break;
						case NIPPLE:
							break;
						case THIGHS:
							return Util.newArrayListOfValues(
									CoverableArea.LEGS);
						case URETHRA_PENIS:
							break;
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							break;
						default:
							break;
					}
				}
			}
			return null; 
		}
	};
	
	public static final SexAction PLAYER_GENERIC_ORGASM_FLOOR = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenisIgnoreDildo()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.FLOOR)
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Main.game.getPlayer(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob onto floor";
				}
				return "Pull out (floor)";
			}
			return "Cum on floor";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto the floor.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.FLOOR);
		}
		
		@Override
		public void applyEffects() {
			GameCharacter characterPenetrated = null;
			SexAreaInterface areaContacted = null;
			if(!Sex.getCharactersHavingOngoingActionWith(Main.game.getPlayer(), SexAreaPenetration.PENIS).isEmpty()) {
				characterPenetrated = Sex.getCharactersHavingOngoingActionWith(Main.game.getPlayer(), SexAreaPenetration.PENIS).get(0);
				areaContacted = Sex.getAllContactingSexAreas(Main.game.getPlayer(), SexAreaPenetration.PENIS).get(0);
				Sex.stopOngoingAction(Main.game.getPlayer(), SexAreaPenetration.PENIS, characterPenetrated, areaContacted);
			}
		}
	};
	
	
	public static final SexAction PLAYER_GENERIC_ORGASM_WALL = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenisIgnoreDildo()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.WALL)
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Main.game.getPlayer(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob onto wall";
				}
				return "Pull out (wall)";
			}
			return "Cum on wall";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto the wall.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.WALL);
		}
		
		@Override
		public void applyEffects() {
			PLAYER_GENERIC_ORGASM_FLOOR.applyEffects();
		}
	};
	
	public static final SexAction PLAYER_GENERIC_ORGASM_ASS = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenisIgnoreDildo()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.ASS)
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Main.game.getPlayer(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob onto ass";
				}
				return "Pull out (ass)";
			}
			return "Cum on ass";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc2.namePos] [npc2.ass+].";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.ASS);
		}
		
		@Override
		public void applyEffects() {
			// Pull out:
			PLAYER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.isPlayer() && cumTarget.equals(Sex.getTargetedPartner(Main.game.getPlayer()))) {
				return Util.newArrayListOfValues(
						CoverableArea.ASS,
						CoverableArea.ANUS);
			}
			return null; 
		}
	};
	
	public static final SexAction PLAYER_GENERIC_ORGASM_GROIN = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenisIgnoreDildo()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.GROIN)
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Main.game.getPlayer(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob onto groin";
				}
				return "Pull out (groin)";
			}
			return "Cum on groin";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc2.namePos] groin.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.GROIN);
		}
		
		@Override
		public void applyEffects() {
			// Pull out:
			PLAYER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.isPlayer() && cumTarget.equals(Sex.getTargetedPartner(Main.game.getPlayer()))) {
				return Util.newArrayListOfValues(
						CoverableArea.PENIS,
						CoverableArea.VAGINA);
			}
			return null; 
		}
	};

	public static final SexAction PLAYER_GENERIC_ORGASM_SELF_GROIN = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenisIgnoreDildo()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.SELF_GROIN)
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Main.game.getPlayer(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob onto own groin";
				}
				return "Pull out (own groin)";
			}
			return "Cum on your groin";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto your groin.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.SELF_GROIN);
		}

		@Override
		public void applyEffects() {
			// Pull out:
			PLAYER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.isPlayer() && cumTarget.isPlayer()) {
				return Util.newArrayListOfValues(
						CoverableArea.PENIS,
						CoverableArea.VAGINA);
			}
			return null; 
		}
	};
	
	public static final SexAction PLAYER_GENERIC_ORGASM_BREASTS = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenisIgnoreDildo()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.BREASTS)
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Main.game.getPlayer(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob onto [npc2.breasts]";
				}
				return "Pull out ([npc2.breasts])";
			}
			return "Cum on [npc2.breasts]";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc2.namePos] [npc2.breasts].";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.BREASTS);
		}
		
		@Override
		public void applyEffects() {
			// Pull out:
			PLAYER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.isPlayer() && cumTarget.equals(Sex.getTargetedPartner(Main.game.getPlayer()))) {
				return Util.newArrayListOfValues(
						CoverableArea.BREASTS);
			}
			return null; 
		}
	};

	public static final SexAction PLAYER_GENERIC_ORGASM_SELF_BREASTS = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenisIgnoreDildo()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.SELF_BREASTS)
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Main.game.getPlayer(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob onto own [npc.breasts]";
				}
				return "Pull out (own [npc.breasts])";
			}
			return "Cum on your [npc.breasts]";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto your [npc.breasts+].";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.SELF_BREASTS);
		}

		@Override
		public void applyEffects() {
			// Pull out:
			PLAYER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.isPlayer() && cumTarget.isPlayer()) {
				return Util.newArrayListOfValues(
						CoverableArea.BREASTS);
			}
			return null; 
		}
	};
	
	public static final SexAction PLAYER_GENERIC_ORGASM_FACE = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenisIgnoreDildo()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.FACE)
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Main.game.getPlayer(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob onto face";
				}
				return "Pull out (facial)";
			}
			return "Facial";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc2.namePos] face.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.FACE);
		}
		
		@Override
		public void applyEffects() {
			// Pull out:
			PLAYER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.isPlayer() && cumTarget.equals(Sex.getTargetedPartner(Main.game.getPlayer()))) {
				return Util.newArrayListOfValues(
						CoverableArea.MOUTH);
			}
			return null; 
		}
	};

	public static final SexAction PLAYER_GENERIC_ORGASM_SELF_FACE = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenisIgnoreDildo()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.SELF_FACE)
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Main.game.getPlayer(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob onto own face";
				}
				return "Pull out (own face)";
			}
			return "Cum on your face";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto your [npc.face+].";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.SELF_FACE);
		}

		@Override
		public void applyEffects() {
			// Pull out:
			PLAYER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.isPlayer() && cumTarget.isPlayer()) {
				return Util.newArrayListOfValues(
						CoverableArea.MOUTH);
			}
			return null; 
		}
	};
	
	public static final SexAction PLAYER_GENERIC_ORGASM_HAIR = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenisIgnoreDildo()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.HAIR)
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Main.game.getPlayer(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob into [npc2.hair]";
				}
				return "Pull out ([npc2.hair])";
			}
			return "Cum in [npc2.hair]";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum into [npc2.namePos] [npc2.hair].";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.HAIR);
		}
		
		@Override
		public void applyEffects() {
			// Pull out:
			PLAYER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.isPlayer() && cumTarget.equals(Sex.getTargetedPartner(Main.game.getPlayer()))) {
				return Util.newArrayListOfValues(
						CoverableArea.HAIR);
			}
			return null; 
		}
	};
	
	public static final SexAction PLAYER_GENERIC_ORGASM_STOMACH = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenisIgnoreDildo()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.STOMACH)
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Main.game.getPlayer(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob onto stomach";
				}
				return "Pull out (stomach)";
			}
			return "Cum on stomach";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc2.namePos] stomach.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.STOMACH);
		}

		@Override
		public void applyEffects() {
			// Pull out:
			PLAYER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.isPlayer() && cumTarget.equals(Sex.getTargetedPartner(Main.game.getPlayer()))) {
				return Util.newArrayListOfValues(
						CoverableArea.STOMACH);
			}
			return null; 
		}
	};
	
	public static final SexAction PLAYER_GENERIC_ORGASM_SELF_STOMACH = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenisIgnoreDildo()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.SELF_STOMACH)
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Main.game.getPlayer(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob onto own stomach";
				}
				return "Pull out (own stomach)";
			}
			return "Cum on your stomach";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto your stomach.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.SELF_STOMACH);
		}

		@Override
		public void applyEffects() {
			// Pull out:
			PLAYER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.isPlayer() && cumTarget.isPlayer()) {
				return Util.newArrayListOfValues(
						CoverableArea.STOMACH);
			}
			return null; 
		}
	};
	
	public static final SexAction PLAYER_GENERIC_ORGASM_LEGS = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenisIgnoreDildo()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.LEGS)
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Main.game.getPlayer(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob onto [npc2.legs]";
				}
				return "Pull out ([npc2.legs])";
			}
			return "Cum on [npc2.legs]";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc2.namePos] [npc2.legs].";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.LEGS);
		}

		@Override
		public void applyEffects() {
			// Pull out:
			PLAYER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.isPlayer() && cumTarget.equals(Sex.getTargetedPartner(Main.game.getPlayer()))) {
				return Util.newArrayListOfValues(
						CoverableArea.LEGS);
			}
			return null; 
		}
	};

	public static final SexAction PLAYER_GENERIC_ORGASM_SELF_LEGS = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenisIgnoreDildo()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.SELF_LEGS)
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Main.game.getPlayer(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob onto own [npc.legs]";
				}
				return "Pull out (own [npc.legs])";
			}
			return "Cum on your [npc.legs]";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto your [npc.legs+].";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.SELF_LEGS);
		}

		@Override
		public void applyEffects() {
			// Pull out:
			PLAYER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.isPlayer() && cumTarget.isPlayer()) {
				return Util.newArrayListOfValues(
						CoverableArea.LEGS,
						CoverableArea.THIGHS);
			}
			return null; 
		}
	};
	
	public static final SexAction PLAYER_GENERIC_ORGASM_FEET = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenisIgnoreDildo()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.FEET)
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Main.game.getPlayer(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob onto [npc2.feet]";
				}
				return "Pull out ([npc2.feet])";
			}
			return "Cum on [npc2.feet]";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc2.namePos] [npc2.feet+].";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.FEET);
		}

		@Override
		public void applyEffects() {
			// Pull out:
			PLAYER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.isPlayer() && cumTarget.equals(Sex.getTargetedPartner(Main.game.getPlayer()))) {
				return Util.newArrayListOfValues(
						CoverableArea.FEET);
			}
			return null; 
		}
	};

	public static final SexAction PLAYER_GENERIC_ORGASM_SELF_FEET = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenisIgnoreDildo()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.SELF_FEET)
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Main.game.getPlayer(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob onto own [npc.feet]";
				}
				return "Pull out (own [npc.feet])";
			}
			return "Cum on your [npc.feet]";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto your [npc.feet+].";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.SELF_FEET);
		}

		@Override
		public void applyEffects() {
			// Pull out:
			PLAYER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.isPlayer() && cumTarget.isPlayer()) {
				return Util.newArrayListOfValues(
						CoverableArea.FEET);
			}
			return null; 
		}
	};
	
	public static final SexAction PLAYER_GENERIC_ORGASM_BACK = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenisIgnoreDildo()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.BACK)
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Main.game.getPlayer(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob onto back";
				}
				return "Pull out (back)";
			}
			return "Cum on back";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc2.namePos] back.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.BACK);
		}

		@Override
		public void applyEffects() {
			// Pull out:
			PLAYER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.isPlayer() && cumTarget.equals(Sex.getTargetedPartner(Main.game.getPlayer()))) {
				return Util.newArrayListOfValues(
						CoverableArea.BACK);
			}
			return null; 
		}
	};
	
	
	
	// PLAYER:
	
	public static final SexAction PLAYER_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !isTakingCock(Main.game.getPlayer(), Sex.getCharacterTargetedForSexAction(this))
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Prepare";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Prepare yourself for it.";
		}
		
		@Override
		public String getDescription() {
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					return "You let out a soft [npc.moan] of encouragement as you prepare for [npc2.name] to reach [npc2.her] orgasm.";
				case DOM_NORMAL:
					return "You let out [npc.a_moan+] of encouragement as you prepare for [npc2.name] to reach [npc2.her] orgasm.";
				case DOM_ROUGH:
					return "You let out [npc.a_moan+] of encouragement as you prepare for [npc2.name] to reach [npc2.her] orgasm.";
				case SUB_EAGER:
					return "You let out [npc.a_moan+] of encouragement as you prepare for [npc2.name] to reach [npc2.her] orgasm.";
				case SUB_NORMAL:
					return "You let out [npc.a_moan] of encouragement as you prepare for [npc2.name] to reach [npc2.her] orgasm.";
				case SUB_RESISTING:
					return "You let out [npc.a_moan+] as you desperately try to pull away from [npc2.name] before [npc2.she] orgasms.";
			}
			
			return "";
		}
	};
	
	private static boolean isAreaFucked(GameCharacter characterFucked, SexAreaInterface areaFucked) {
		return Sex.getAllContactingSexAreas(characterFucked, areaFucked).contains(SexAreaPenetration.PENIS)
				&& !Sex.getCharacterContactingSexArea(characterFucked, areaFucked).contains(characterFucked);
	}
	
	public static final SexAction UNIVERSAL_ASK_FOR_CREAMPIE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(!isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				return "Keep fucking";
				
			} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
				return "Request cum";

			} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
				return "Request cum on [npc.breasts]";
				
			} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
				return "Request cum on [npc.feet]";
				
			} else {
				return "Request creampie";
			}
		}

		@Override
		public String getActionDescription() {
			if(!isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Ask [npc2.herHim] to keep fucking you with [npc2.her] dildo as [npc2.she] climaxes.";
				
			} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
				return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Ask [npc2.herHim] to fill your stomach with [npc2.her] cum.";

			} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
				return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Ask [npc2.herHim] to cum all over your [pc.breasts+].";
				
			} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
				return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Ask [npc2.herHim] to cum all over your [pc.feet+].";
				
			} else {
				return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Ask [npc2.herHim] to fill you with [npc2.her] cum.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return isTakingCock(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))
					&& (Sex.getCharacterPerformingAction().isPlayer() || Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING);
		}

		@Override
		public SexActionPriority getPriority() {
			if(Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Sex.getCharacterTargetedForSexAction(this))
					&& Sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_PREGNANCY)) {
				return SexActionPriority.NORMAL;
			} else {
				return SexActionPriority.LOW;
			}
		}

		@Override
		public String getDescription() {
			if(!isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "Through [npc.her] desperate moans and lewd cries,"
								+ " [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc.her] [npc.pussy+] as [npc2.she] orgasms,"
							+" [npc.speech(Fuck! Yes! Keep fucking me!)]";
					
				} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "Through [npc.her] desperate moans and lewd cries,"
							+ " [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc.her] [npc.asshole+] as [npc2.she] orgasms,"
						+" [npc.speech(Fuck! Yes! Keep fucking my ass!)]";
					
				} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
					return "Through [npc.her] desperate moans and lewd cries,"
							+ " [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc.her] [npc.nipple+] as [npc2.she] orgasms,"
						+" [npc.speech(Fuck! Yes! Keep fucking my [npc.nipple]!)]";
					
				} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
					return "Through [npc.her] desperate moans and lewd cries,"
							+ " [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out for [npc2.name] to keep fucking [npc.her] [npc.breasts+] as [npc2.she] orgasms,"
						+" [npc.speech(Fuck! Yes! Keep fucking my tits!)]";
					
				} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
					return "Through [npc.her] desperate moans and lewd cries,"
							+ " [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out for [npc2.name] to keep fucking [npc.her] [npc.feet+] as [npc2.she] orgasms,"
						+" [npc.speech(Fuck! Yes! Keep fucking my [npc.feet]!)]";
					
				} else {
					return "Through [npc.her] desperate moans and lewd cries,"
							+ " [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out for [npc2.name] to keep fucking [npc.herHim] as [npc2.she] orgasms,"
						+" [npc.speech(Fuck! Yes! Keep fucking me!)]";
				}
				
			} else {
				if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out for [npc2.namePos] cum,"
							+(Sex.getCharacterPerformingAction().isVisiblyPregnant()
									?" [npc.speech(Fuck! Cum in me! I need your cum!)]"
									:" [npc.speech(Breed me! Cum in me! I need your cum!)]");

				} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out for [npc2.namePos] cum,"
							+ " [npc.speech(Fuck! Cum in me! I need your cum!)]";

				} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out for [npc2.namePos] cum,"
							+ " [npc.speech(Fuck! Cum in me! I need your cum!)]";

				} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out for [npc2.namePos] cum,"
							+ " [npc.speech(Yes! Cum for me! Cover my tits with your cum!)]";

				} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out for [npc2.namePos] cum,"
							+" [npc.speech(Fuck! Yes! Cum all over my [npc.feet]!)]";
					
				} else {
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out for [npc2.namePos] cum,"
							+ " [npc.speech(Cum for me! I want to taste your cum!)]";
				}
			}
		}

		@Override
		public void applyEffects() {
			if(Sex.getCharacterPerformingAction().isPlayer()) {
				SexFlags.playerRequestedCreampie = true;
				SexFlags.playerRequestedPullOut = false;
			} else {
				SexFlags.partnerRequestedCreampie = true;
				SexFlags.partnerRequestedPullOut = false;
			}
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if (Sex.getAllContactingSexAreas(character, SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(character, SexAreaOrifice.VAGINA).contains(character)) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					return Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT, Fetish.FETISH_PREGNANCY);
				} else {
					return Util.newArrayListOfValues(Fetish.FETISH_CUM_STUD, Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Sex.getAllContactingSexAreas(character, SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(character, SexAreaOrifice.ANUS).contains(character)) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					return Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT, Fetish.FETISH_ANAL_RECEIVING);
				} else {
					return Util.newArrayListOfValues(Fetish.FETISH_CUM_STUD, Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if ((Sex.getAllContactingSexAreas(character, SexAreaOrifice.NIPPLE).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(character, SexAreaOrifice.NIPPLE).contains(character))
					|| (Sex.getAllContactingSexAreas(character, SexAreaOrifice.BREAST).contains(SexAreaPenetration.PENIS)
							&& !Sex.getCharacterContactingSexArea(character, SexAreaOrifice.BREAST).contains(character))) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					return Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT, Fetish.FETISH_BREASTS_SELF);
				} else {
					return Util.newArrayListOfValues(Fetish.FETISH_CUM_STUD, Fetish.FETISH_BREASTS_OTHERS);
				}
				
			} else if (Sex.getAllContactingSexAreas(character, SexAreaPenetration.FOOT).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(character, SexAreaPenetration.FOOT).contains(character)) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					return Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT, Fetish.FETISH_FOOT_GIVING);
				} else {
					return Util.newArrayListOfValues(Fetish.FETISH_CUM_STUD, Fetish.FETISH_FOOT_RECEIVING);
				}
				
			} else {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					return Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT);
				} else {
					return Util.newArrayListOfValues(Fetish.FETISH_CUM_STUD);
				}
			}
		}
	};
	
	public static final SexAction UNIVERSAL_ASK_FOR_PULL_OUT = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Request pullout";
		}

		@Override
		public String getActionDescription() {
			if(!isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Ask [npc2.herHim] to pull [npc2.her] dildo out of you as [npc2.she] orgasms.";
			}
			return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Ask [npc2.herHim] to pull out before [npc2.she] cums.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			if(Sex.getCharacterPerformingAction().isPlayer()) {
				return isTakingCock(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))
						&& Sex.getCharacterPerformingAction().getLocationPlace().getPlaceType()!=PlaceType.GAMBLING_DEN_FUTA_PREGNANCY
						&& Sex.getCharacterPerformingAction().getLocationPlace().getPlaceType()!=PlaceType.GAMBLING_DEN_PREGNANCY;
				
			} else {
				return isTakingCock(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))
						&& (Sex.getSexPace(Sex.getCharacterPerformingAction())==SexPace.SUB_RESISTING
							|| ((Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
								?!Sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_CUM_ADDICT)
								:true)
							&& (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
								?(!Sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_PREGNANCY))
								:true)));
			}
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.LOW;
		}

		@Override
		public void applyEffects() {
			if(Sex.getCharacterPerformingAction().isPlayer()) {
				SexFlags.playerRequestedCreampie = false;
				SexFlags.playerRequestedPullOut = true;
				
			} else {
				SexFlags.partnerRequestedCreampie = false;
				SexFlags.partnerRequestedPullOut = true;
			}
		}

		@Override
		public String getDescription() {
			if(!isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
							+"[npc.speech(Pull out! Get that dildo out of me!)]";
					
				} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
							+"[npc.speech(Pull out! Get that dildo out of me!)]";
					
				} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
							+"[npc.speech(Pull out! Get that dildo out of me!)]";
					
				} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
							+"[npc.speech(Pull out! Get that dildo out of me!)]";
					
				} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
							+"[npc.speech(Pull out! Get that dildo out of me!)]";
					
				} else {
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to cry out around [npc2.namePos] [npc2.cock], "
							+ "[npc.speech(Pull out! Get that dildo out of my mouth!)]";
				}
				
			} else {
				if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
							+(Sex.getCharacterPerformingAction().isVisiblyPregnant()
									?"[npc.speech(Pull out! I don't want you to cum in me!)]"
									:"[npc.speech(Pull out! I don't want to get pregnant!)]");

				} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
							+ "[npc.speech(Pull out! Don't cum in my ass, please!)]";

				} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
							+ "[npc.speech(Pull out! Don't cum in my nipple! Don't do it!)]";

				} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
							+ "[npc.speech(Get back! Don't cum on my tits! Don't you dare!)]";

				} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
							+ "[npc.speech(Get back! Don't cum on my [npc.feet]! Don't you dare!)]";
					
				} else {
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to cry out around [npc2.namePos] [npc2.cock], "
							+ "[npc.speech(Pull out! I don't want to taste your cum!)]";
				}
			}
		}
	};
	
	public static final SexAction PLAYER_ASK_FOR_NOTHING = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "No request";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Don't make any requests of [npc2.herHim], and see what [npc2.she] has in store for you.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return isTakingCock(Main.game.getPlayer(), Sex.getCharacterTargetedForSexAction(this))
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public void applyEffects() {
			SexFlags.playerRequestedCreampie = false;
			SexFlags.playerRequestedPullOut = false;
		}

		@Override
		public String getDescription() {
			if(!isRealPenisFuckingCharacter(Main.game.getPlayer(), Sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "You continue [npc.moaning+] as [npc2.namePos] [npc2.cock+] continues to slam in and out of your [npc.pussy+], and you wonder if [npc2.sheIs] going to pull out or carry on fucking you through [npc2.her] orgasm.";

				} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "You continue [npc.moaning+] as [npc2.namePos] [npc2.cock+] continues to slam in and out of your [npc.asshole+], and you wonder if [npc2.sheIs] going to pull out or carry on fucking you through [npc2.her] orgasm.";

				} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
					return "You continue [npc.moaning+] as [npc2.namePos] [npc2.cock+] continues to slam in and out of your [npc.nipple+], and you wonder if [npc2.sheIs] going to pull out or carry on fucking you through [npc2.her] orgasm.";

				} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
					if(Sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
						return "You continue [npc.moaning+] as [npc2.namePos] [npc2.cock+] continues to slide up and down between your [npc.breasts+],"
								+ " and you wonder if [npc2.sheIs] going to carry on fucking your [npc.breasts+] through [npc2.her] orgasm.";
					} else {
						return "You continue [npc.moaning+] as [npc2.namePos] [npc2.cock+] continues to slide up and down over your torso,"
								+ " and you wonder if [npc2.sheIs] going to back away or carry on fucking your flat chest through [npc2.her] orgasm.";
					}
					
				} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
					return "You continue [npc.moaning+] as you give [npc.name] [pc.a_footjob], and you wonder if [npc2.sheIs] going to back away or carry on fucking your [npc.feet] through [npc2.her] orgasm.";
					
				} else {
					return "You continue [npc.moaning+] as [npc2.name] continues to fuck you with [npc2.her] [npc2.cock+], and you wonder if [npc2.sheIs] going to pull out, or carry on fucking you through [npc2.her] orgasm.";
				}
				
			} else {
				if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "You continue [npc.moaning+] as you feel [npc2.namePos] [npc2.cock+] start to twitch inside your [npc.pussy+], and you wonder if [npc2.sheIs] going to pull out or"
							+(Main.game.getPlayer().isVisiblyPregnant()
									?" give you a fresh creampie..."
									:" fill your womb with [npc2.her] [npc2.cum+]...");

				} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "You continue [npc.moaning+] as you feel [npc2.namePos] [npc2.cock+] start to twitch inside your [npc.asshole+], and you wonder if [npc2.sheIs] going to pull out or give you a fresh anal creampie...";

				} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
					return "You continue [npc.moaning+] as you feel [npc2.namePos] [npc2.cock+] start to twitch inside your [npc.nipple+], and you wonder if [npc2.sheIs] going to pull out or give you a fresh nipple creampie...";

				} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
					if(Sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
						return "You continue [npc.moaning+] as [npc2.namePos] [npc2.cock+] continues to slide up and down between your [npc.breasts+], and you wonder if [npc2.sheIs] going to pull out or cum all over your [npc.breasts+].";
					} else {
						return "You continue [npc.moaning+] as [npc2.namePos] [npc2.cock+] continues to slide up and down over your torso, and you wonder if [npc2.sheIs] going to back away or cum all over your flat chest.";
					}
					
				} else if(isAreaFucked(Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
					return "You continue [npc.moaning+] as you give [npc.name] [pc.a_footjob], and you wonder if [npc2.sheIs] going to pull out or cum all over your [npc.feet].";
					
				} else {
					return "You continue [npc.moaning+] as you feel [npc2.namePos] [npc2.cock+] start to twitch, and you wonder if [npc2.sheIs] going to pull out or fill you with [npc2.her] [npc2.cum+]...";
				}
			}
		}
	};
	
	public static final SexAction PLAYER_PREPARE_DENY = new SexAction( //TODO
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.NEGATIVE_MAJOR,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Deny";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Don't let [npc2.herHim] have it.";
		}

		@Override
		public String getDescription() {
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					return UtilText.returnStringAtRandom(
							"[npc2.Name] starts desperately whining and panting, before blurting out, [npc2.speech(N-No! Y-You're going to make me cum!)]"
							+ "<br/>"
							+ "Upon hearing this, you quickly grab hold of [npc2.her] [npc2.arms], and, keeping [npc2.herHim] held in place, prevent [npc2.herHim] from stimulating [npc2.herself]."
							+ " [npc2.Name] seems a little relieved that you're not going to force [npc2.herHim] to orgasm, and,"
								+ " ignoring [npc2.her] continued pleas for you to leave [npc2.herHim] alone, you wait until [npc2.sheHas] calmed down before finally loosening your grip.");
				default:
					return UtilText.returnStringAtRandom(
							"[npc2.Name] starts desperately panting and [npc2.moaning], before blurting out, [npc2.speech(Yes! I'm going to cum!)]"
							+ "<br/>"
							+ "Upon hearing this, you quickly grab hold of [npc2.her] [npc2.arms], and, keeping [npc2.herHim] held in place, prevent [npc2.herHim] from stimulating [npc2.herself]."
							+ " Ignoring [npc2.her] protests and cries for you to let [npc2.herHim] orgasm, you continue holding [npc2.herHim] quite still, only loosening your grip once [npc2.sheHas] calmed down.");
			}
		}
		
		@Override
		public void applyEffects() {
			SexFlags.playerDeniedPartner = true;
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.equals(Sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL_SELF);
			}
		}
	};
	
	// PARTNER
	
	public static final SexAction PARTNER_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !isTakingCock(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.LOW;
		}
		
		@Override
		public String getActionTitle() {
			return "Prepare";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [pc.name] is fast approaching [pc.her] orgasm. Prepare yourself for it.";
		}
		
		@Override
		public String getDescription() {
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					return "[npc.Name] lets out a soft [npc.moan] of encouragement as [npc.she] prepares for [npc2.name] to reach [npc2.her] orgasm.";
				case DOM_NORMAL:
					return "[npc.Name] lets out [npc.a_moan+] of encouragement as [npc.she] prepares for [npc2.name] to reach [npc2.her] orgasm.";
				case DOM_ROUGH:
					return "[npc.Name] lets out [npc.a_moan+] of encouragement as [npc.she] prepares for [npc2.name] to reach [npc2.her] orgasm.";
				case SUB_EAGER:
					return "[npc.Name] lets out [npc.a_moan+] of encouragement as [npc.she] prepares for [npc2.name] to reach [npc2.her] orgasm.";
				case SUB_NORMAL:
					return "[npc.Name] lets out [npc.a_moan+] of encouragement as [npc.she] prepares for [npc2.name] to reach [npc2.her] orgasm.";
				case SUB_RESISTING:
					return "[npc.Name] lets out [npc.a_moan+] as [npc.she] desperately tries to pull away from [npc2.name] before [npc2.she] [npc2.verb(orgasm)].";
			}
			
			return "";
		}
	};
	
	// Doesn't have penis (or penis is not exposed), and isn't being vaginally penetrated:
	public static final SexAction PARTNER_GENERIC_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "Orgasm";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.LOW;
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Sex.getCharacterPerformingAction(), Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).getAvailableCumTargets().get(0));
		}
		
		@Override
		public void applyEffects() {
			if (!Sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Sex.getCharacterPerformingAction().isWearingCondom()
					&& Sex.getCharacterPerformingAction().getPenisOrgasmCumQuantity() != CumProduction.ZERO_NONE) {
				Sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(true);
			}
		}
	};
	
	public static final SexAction PARTNER_GENERIC_ORGASM_CREAMPIE = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public SexActionPriority getPriority() {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.GAMBLING_DEN_FUTA_PREGNANCY
					|| Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.GAMBLING_DEN_PREGNANCY
					|| (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0)==SexAreaOrifice.VAGINA && Sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION))) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Math.random()<0.66f) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		
		@Override
		public boolean endsSex() {
			return Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.GAMBLING_DEN_FUTA_PREGNANCY
					|| Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.GAMBLING_DEN_PREGNANCY;
		}
		
		@Override
		public String getActionTitle() {
			GameCharacter characterPenetrated = Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);

			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ANUS:
						if(Main.game.getPlayer().hasPenisModifier(PenetrationModifier.KNOTTED)) {
							return UtilText.parse(characterPenetrated, "Knot [npc.herHim]");
						} else {
							return "Anal Creampie";
						}
					case ASS:
						return "Hotdogging Climax";
					case BREAST:
						break;
					case MOUTH:
						break;
					case NIPPLE:
						return "Nipple Creampie";
					case THIGHS:
						return "Thigh-sex Climax";
					case URETHRA_PENIS: case URETHRA_VAGINA:
						return "Urethra Creampie";
					case VAGINA:
						if(Main.game.getPlayer().hasPenisModifier(PenetrationModifier.KNOTTED)) {
							return UtilText.parse(characterPenetrated, "Knot [npc.herHim]");
						} else {
							return "Creampie";
						}
				}
			} else {
				switch((SexAreaPenetration)areaContacted) {
					case CLIT:
						break;
					case FINGER:
						return "Handjob Climax";
					case PENIS:
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case FOOT:
						return "Footjob Climax";
					case TONGUE:
						break;
				}
			}
			
			return "Creampie";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			if(Sex.getCharacterPerformingAction().isPlayer()
					|| Sex.getCharacterPerformingAction().getPenisType()==PenisType.DILDO
					|| Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return false;
			}
			
			SexAreaInterface areaContacted = Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			
			boolean isPenetratingSuitableOrifice  = false;
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ASS:
					case THIGHS:
						return false;
					case BREAST:
					case ANUS:
					case MOUTH:
					case NIPPLE:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
					case VAGINA:
						isPenetratingSuitableOrifice = true;
						break;
				}
			} else {
				switch((SexAreaPenetration)areaContacted) {
				case CLIT:
					break;
				case FINGER:
					return true;
				case FOOT:
					return true;
				case PENIS:
					break;
				case TAIL:
					break;
				case TENTACLE:
					break;
				case TONGUE:
					break;
			}
			}
			
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			
			// Will not use if obeying the player and player asked for pull out:
			if(isActiveNPCObeyingPlayer() && SexFlags.playerRequestedPullOut) {
				return false;
			}
			
			return true;
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Sex.getCharacterPerformingAction(), OrgasmCumTarget.INSIDE);
		}
		
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			GameCharacter characterPenetrated = Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			
			if(!cumProvider.isPlayer() && cumTarget.equals(characterPenetrated)) {
				return Util.newArrayListOfValues(areaContacted);
					
			} else {
				return null;
			}
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Sex.getCharacterPerformingAction()) && cumTarget.equals(Sex.getTargetedPartner(cumProvider))) {
				SexAreaInterface areaContacted = Sex.getAllContactingSexAreas(cumProvider, SexAreaPenetration.PENIS).get(0);
	
				if(!areaContacted.isOrifice()) {
					switch((SexAreaPenetration)areaContacted) {
						case CLIT:
							break;
						case FINGER:
							return Util.newArrayListOfValues(
									CoverableArea.HANDS);
						case FOOT:
							return Util.newArrayListOfValues(
									CoverableArea.FEET);
						case PENIS:
							break;
						case TAIL:
							break;
						case TENTACLE:
							break;
						case TONGUE:
							break;
					}
				} else {
					switch((SexAreaOrifice)areaContacted) {
					case ANUS:
						break;
					case ASS:
						return Util.newArrayListOfValues(
								CoverableArea.ASS,
								CoverableArea.ANUS);
					case BREAST:
						return Util.newArrayListOfValues(
								CoverableArea.BREASTS,
								CoverableArea.MOUTH);
					case MOUTH:
						break;
					case NIPPLE:
						break;
					case THIGHS:
						return Util.newArrayListOfValues(
								CoverableArea.LEGS);
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						break;
					default:
						break;
				}
			}
			}
			return null; 
		}
	};
	
	public static final SexAction PARTNER_GENERIC_ORGASM_FLOOR = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isGenericPartnerCumTargetRequirementsMet()
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).getAvailableCumTargets().contains(OrgasmCumTarget.FLOOR)
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return "Pull out (floor)";
			}
			return "Cum on floor";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto the floor.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Sex.getCharacterPerformingAction(), OrgasmCumTarget.FLOOR);
		}
		
		@Override
		public void applyEffects() {
			GameCharacter characterPenetrated = null;
			SexAreaInterface areaContacted = null;
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				characterPenetrated = Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
				areaContacted = Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
				Sex.stopOngoingAction(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, characterPenetrated, areaContacted);
			}
		}
	};
	
	
	public static final SexAction PARTNER_GENERIC_ORGASM_WALL = new SexAction(PARTNER_GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isGenericPartnerCumTargetRequirementsMet()
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).getAvailableCumTargets().contains(OrgasmCumTarget.WALL)
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return "Pull out (wall)";
			}
			return "Cum on wall";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto the wall.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Sex.getCharacterPerformingAction(), OrgasmCumTarget.WALL);
		}
		
		@Override
		public void applyEffects() {
			PARTNER_GENERIC_ORGASM_FLOOR.applyEffects();
		}
	};
	
	public static final SexAction PARTNER_GENERIC_ORGASM_ASS = new SexAction(PARTNER_GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isGenericPartnerCumTargetRequirementsMet()
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).getAvailableCumTargets().contains(OrgasmCumTarget.ASS)
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return "Pull out (ass)";
			}
			return "Cum on ass";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc2.namePos] [npc2.ass+].";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Sex.getCharacterPerformingAction(), OrgasmCumTarget.ASS);
		}
		
		@Override
		public void applyEffects() {
			// Pull out:
			PARTNER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(!cumProvider.isPlayer() && cumTarget.equals(Sex.getTargetedPartner(Sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.ASS,
						CoverableArea.ANUS);
			}
			return null; 
		}
	};
	
	public static final SexAction PARTNER_GENERIC_ORGASM_GROIN = new SexAction(PARTNER_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return isGenericPartnerCumTargetRequirementsMet()
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).getAvailableCumTargets().contains(OrgasmCumTarget.GROIN)
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return "Pull out (groin)";
			}
			return "Cum on groin";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc2.namePos] groin.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Sex.getCharacterPerformingAction(), OrgasmCumTarget.GROIN);
		}
		
		@Override
		public void applyEffects() {
			// Pull out:
			PARTNER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(!cumProvider.isPlayer() && cumTarget.equals(Sex.getTargetedPartner(Sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.PENIS,
						CoverableArea.VAGINA);
			}
			return null; 
		}
	};

	public static final SexAction PARTNER_GENERIC_ORGASM_SELF_GROIN = new SexAction(PARTNER_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return isGenericPartnerCumTargetRequirementsMet()
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).getAvailableCumTargets().contains(OrgasmCumTarget.SELF_GROIN)
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return "Pull out (own groin)";
			}
			return "Cum on your groin";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto your groin.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Sex.getCharacterPerformingAction(), OrgasmCumTarget.SELF_GROIN);
		}

		@Override
		public void applyEffects() {
			// Pull out:
			PARTNER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider == Sex.getCharacterPerformingAction()
					&& cumTarget == Sex.getCharacterPerformingAction()) {
				return Util.newArrayListOfValues(
						CoverableArea.VAGINA,
						CoverableArea.PENIS);
			}
			return null;
		}
	};
	
	public static final SexAction PARTNER_GENERIC_ORGASM_BREASTS = new SexAction(PARTNER_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return isGenericPartnerCumTargetRequirementsMet()
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).getAvailableCumTargets().contains(OrgasmCumTarget.BREASTS)
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return "Pull out (chest)";
			}
			return "Cum on chest";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc2.namePos] chest.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Sex.getCharacterPerformingAction(), OrgasmCumTarget.BREASTS);
		}
		
		@Override
		public void applyEffects() {
			// Pull out:
			PARTNER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(!cumProvider.isPlayer() && cumTarget.equals(Sex.getTargetedPartner(Sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.BREASTS);
			}
			return null; 
		}
	};

	public static final SexAction PARTNER_GENERIC_ORGASM_SELF_BREASTS = new SexAction(PARTNER_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return isGenericPartnerCumTargetRequirementsMet()
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).getAvailableCumTargets().contains(OrgasmCumTarget.SELF_BREASTS)
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return "Pull out (own [npc.breasts])";
			}
			return "Cum on your [npc.breasts]";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto your [npc.breasts+].";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Sex.getCharacterPerformingAction(), OrgasmCumTarget.SELF_BREASTS);
		}

		@Override
		public void applyEffects() {
			// Pull out:
			PARTNER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider == Sex.getCharacterPerformingAction()
					&& cumTarget == Sex.getCharacterPerformingAction()) {
				return Util.newArrayListOfValues(
						CoverableArea.BREASTS);
			}
			return null;
		}
	};
	
	public static final SexAction PARTNER_GENERIC_ORGASM_FACE = new SexAction(PARTNER_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return isGenericPartnerCumTargetRequirementsMet()
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).getAvailableCumTargets().contains(OrgasmCumTarget.FACE)
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return "Pull out (facial)";
			}
			return "Facial";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc2.namePos] face.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Sex.getCharacterPerformingAction(), OrgasmCumTarget.FACE);
		}
		
		@Override
		public void applyEffects() {
			// Pull out:
			PARTNER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(!cumProvider.isPlayer() && cumTarget.equals(Sex.getTargetedPartner(Sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.MOUTH);
			}
			return null; 
		}
	};

	public static final SexAction PARTNER_GENERIC_ORGASM_SELF_FACE = new SexAction(PARTNER_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return isGenericPartnerCumTargetRequirementsMet()
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).getAvailableCumTargets().contains(OrgasmCumTarget.SELF_FACE)
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return "Pull out (own [npc.face])";
			}
			return "Cum on your [npc.face]";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto your [npc.face+].";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Sex.getCharacterPerformingAction(), OrgasmCumTarget.SELF_FACE);
		}

		@Override
		public void applyEffects() {
			// Pull out:
			PARTNER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider == Sex.getCharacterPerformingAction()
					&& cumTarget == Sex.getCharacterPerformingAction()) {
				return Util.newArrayListOfValues(
						CoverableArea.MOUTH);
			}
			return null;
		}
	};
	
	public static final SexAction PARTNER_GENERIC_ORGASM_HAIR = new SexAction(PARTNER_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return isGenericPartnerCumTargetRequirementsMet()
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).getAvailableCumTargets().contains(OrgasmCumTarget.HAIR)
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return "Pull out (hair)";
			}
			return "Cum in [npc2.hair]";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum into [npc2.namePos] [npc2.hair].";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Sex.getCharacterPerformingAction(), OrgasmCumTarget.HAIR);
		}
		
		@Override
		public void applyEffects() {
			// Pull out:
			PARTNER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(!cumProvider.isPlayer() && cumTarget.equals(Sex.getTargetedPartner(Sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.HAIR);
			}
			return null; 
		}
	};
	
	public static final SexAction PARTNER_GENERIC_ORGASM_STOMACH = new SexAction(PARTNER_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return isGenericPartnerCumTargetRequirementsMet()
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).getAvailableCumTargets().contains(OrgasmCumTarget.STOMACH)
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return "Pull out (stomach)";
			}
			return "Cum on stomach";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc2.namePos] stomach.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Sex.getCharacterPerformingAction(), OrgasmCumTarget.STOMACH);
		}

		@Override
		public void applyEffects() {
			// Pull out:
			PARTNER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(!cumProvider.isPlayer() && cumTarget.equals(Sex.getTargetedPartner(Sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.STOMACH);
			}
			return null; 
		}
	};
	
	public static final SexAction PARTNER_GENERIC_ORGASM_SELF_STOMACH = new SexAction(PARTNER_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return isGenericPartnerCumTargetRequirementsMet()
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).getAvailableCumTargets().contains(OrgasmCumTarget.SELF_STOMACH)
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return "Pull out (own stomach)";
			}
			return "Cum on your stomach";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto your stomach.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Sex.getCharacterPerformingAction(), OrgasmCumTarget.SELF_STOMACH);
		}

		@Override
		public void applyEffects() {
			// Pull out:
			PARTNER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider == Sex.getCharacterPerformingAction()
					&& cumTarget == Sex.getCharacterPerformingAction()) {
				return Util.newArrayListOfValues(
						CoverableArea.STOMACH);
			}
			return null;
		}
	};
	
	public static final SexAction PARTNER_GENERIC_ORGASM_LEGS = new SexAction(PARTNER_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return isGenericPartnerCumTargetRequirementsMet()
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).getAvailableCumTargets().contains(OrgasmCumTarget.LEGS)
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return "Pull out (legs)";
			}
			return "Cum on [npc.legs]";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc2.namePos] [npc2.legs].";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Sex.getCharacterPerformingAction(), OrgasmCumTarget.LEGS);
		}

		@Override
		public void applyEffects() {
			// Pull out:
			PARTNER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(!cumProvider.isPlayer() && cumTarget.equals(Sex.getTargetedPartner(Sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.LEGS);
			}
			return null; 
		}
	};
	
	public static final SexAction PARTNER_GENERIC_ORGASM_SELF_LEGS = new SexAction(PARTNER_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return isGenericPartnerCumTargetRequirementsMet()
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).getAvailableCumTargets().contains(OrgasmCumTarget.SELF_LEGS)
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return "Pull out (own legs)";
			}
			return "Cum on your [npc.legs]";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto your [npc.legs+].";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Sex.getCharacterPerformingAction(), OrgasmCumTarget.SELF_LEGS);
		}

		@Override
		public void applyEffects() {
			// Pull out:
			PARTNER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider == Sex.getCharacterPerformingAction()
					&& cumTarget == Sex.getCharacterPerformingAction()) {
				return Util.newArrayListOfValues(
						CoverableArea.LEGS,
						CoverableArea.THIGHS);
			}
			return null;
		}
	};
	
	public static final SexAction PARTNER_GENERIC_ORGASM_FEET = new SexAction(PARTNER_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return isGenericPartnerCumTargetRequirementsMet()
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).getAvailableCumTargets().contains(OrgasmCumTarget.FEET)
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return "Pull out (feet)";
			}
			return "Cum on [npc.feet]";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc2.namePos] [npc2.feet].";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Sex.getCharacterPerformingAction(), OrgasmCumTarget.FEET);
		}

		@Override
		public void applyEffects() {
			// Pull out:
			PARTNER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(!cumProvider.isPlayer() && cumTarget.equals(Sex.getTargetedPartner(Sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.FEET);
			}
			return null; 
		}
	};
	
	public static final SexAction PARTNER_GENERIC_ORGASM_SELF_FEET = new SexAction(PARTNER_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return isGenericPartnerCumTargetRequirementsMet()
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).getAvailableCumTargets().contains(OrgasmCumTarget.SELF_FEET)
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return "Pull out (own feet)";
			}
			return "Cum on your [npc.feet]";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto your [npc.feet+].";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Sex.getCharacterPerformingAction(), OrgasmCumTarget.SELF_FEET);
		}

		@Override
		public void applyEffects() {
			// Pull out:
			PARTNER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider == Sex.getCharacterPerformingAction()
					&& cumTarget == Sex.getCharacterPerformingAction()) {
				return Util.newArrayListOfValues(
						CoverableArea.FEET);
			}
			return null;
		}
	};
	
	public static final SexAction PARTNER_GENERIC_ORGASM_BACK = new SexAction(PARTNER_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return isGenericPartnerCumTargetRequirementsMet()
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).getAvailableCumTargets().contains(OrgasmCumTarget.BACK)
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return "Pull out (back)";
			}
			return "Cum on back";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc2.namePos] back.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Sex.getCharacterPerformingAction(), OrgasmCumTarget.BACK);
		}

		@Override
		public void applyEffects() {
			// Pull out:
			PARTNER_GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(!cumProvider.isPlayer() && cumTarget.equals(Sex.getTargetedPartner(Sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.BACK);
			}
			return null; 
		}
	};
	
	public static final SexAction PARTNER_GENERIC_ORGASM_DENIED = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.NEGATIVE_MAJOR,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return SexFlags.playerDeniedPartner
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public String getActionTitle() {
			return "Denied!";
		}
		
		@Override
		public String getActionDescription() {
			return "You were denied at the last moment!";
		}
		
		@Override
		public String getDescription() {
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case SUB_RESISTING:
					return UtilText.returnStringAtRandom("[npc.speech(You've had your fun! Now leave me alone!)] [npc.name] screams as you release [npc.herHim]. [npc.speech(D-Don't make me go through that again!)]");
				default:
					return UtilText.returnStringAtRandom("[npc.speech(No! I was so close!)] [npc.name] wails in dismay as you release [npc.herHim]. [npc.speech(Let me cum next time!)]");
			}
		}
		
		@Override
		public void applyEffects() {
			SexFlags.playerDeniedPartner = false;
			SexFlags.playerPreparedForCharactersOrgasm.remove(Sex.getCharacterPerformingAction());
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.equals(Sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL_SELF);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
			}
		}
	};

}
