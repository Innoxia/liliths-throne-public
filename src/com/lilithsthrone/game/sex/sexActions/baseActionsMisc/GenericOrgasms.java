package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.PenisModifier;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.69
 * @version 0.1.88
 * @author Innoxia
 */
public class GenericOrgasms {
	
	private static StringBuilder descriptionSB = new StringBuilder();
	
	private static boolean isTakingCock(GameCharacter character) {
		return ((Sex.getPenetrationTypeInOrifice(character, OrificeType.VAGINA) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(character, OrificeType.VAGINA).equals(character))
				|| (Sex.getPenetrationTypeInOrifice(character, OrificeType.ANUS) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(character, OrificeType.ANUS).equals(character))
				|| (Sex.getPenetrationTypeInOrifice(character, OrificeType.NIPPLE) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(character, OrificeType.NIPPLE).equals(character))
				|| (Sex.getPenetrationTypeInOrifice(character, OrificeType.MOUTH) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(character, OrificeType.MOUTH).equals(character)));
		
	}

	
	private static String getPositionPreparation(GameCharacter characterOrgasming) {
		String orgasmText = "";
		switch(Sex.getSexPositionSlot(characterOrgasming)) {
			case BACK_TO_WALL_AGAINST_WALL:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "Feeling yourself reaching your climax, you let out [pc.a_moan+] as you lean back and brace yourself against the wall.";
				} else {
					orgasmText = "Leaning back, [npc.name] braces [npc.herself] against the wall as [npc.she] feels [npc.herself] reaching [npc.her] climax.";
				}
				break;
			case BACK_TO_WALL_FACING_TARGET:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "Realising that you're about to reach your climax, you step forwards, letting out [pc.a_moan+] as you press [npc.name] back against the wall.";
				} else {
					orgasmText = "Realising that [npc.she]'s about to reach your climax, you step forwards, letting out [pc.a_moan+] as you press [npc.name] back against the wall.";
				}
				break;
			case CHAIR_BOTTOM: case CHAIR_BOTTOM_LILAYA:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "Wrapping your [pc.arms+] around [npc.name], you let out [pc.a_moan+] as you pull [npc.herHim] down into your lap.";
				} else {
					orgasmText = "Wrapping [npc.her] [npc.arms+] around you, [npc.name] lets out [npc.a_moan+] as [npc.she] pulls you down into [npc.her] lap.";
				}
				break;
			case CHAIR_TOP: case CHAIR_TOP_LILAYA:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "With [pc.a_moan+], you sink down into [npc.name]'s lap.";
				} else {
					orgasmText = "With [npc.a_moan+], [npc.name] sinks down into your lap.";
				}
				break;
			case COWGIRL_ON_BACK:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You reach up and take hold of [npc.name]'s waist, letting out [pc.a_moan+] as you prepare to reach your climax.";
				} else {
					orgasmText = "[npc.Name] reaches up and take holds of your waist, letting out [npc.a_moan+] as [npc.she] prepares to reach [npc.her] climax.";
				}
				break;
			case COWGIRL_RIDING:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You look down at [npc.name] and let out [pc.a_moan+] as you prepare to climax.";
				} else {
					orgasmText = "[npc.Name] looks down at you and lets out [npc.a_moan+] as [npc.she] prepares to climax.";
				}
				break;
			case DOGGY_BEHIND: case DOGGY_BEHIND_AMBER:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You take hold of [npc.name]'s waist, pulling [npc.herHim] back into your groin and letting out [pc.a_moan+] as you reach your climax.";
				} else {
					orgasmText = "[npc.Name] takes hold of your waist, pulling you back into [npc.her] groin and letting out [npc.a_moan+] as [npc.she] reaches [npc.her] climax.";
				}
				break;
			case DOGGY_BEHIND_ORAL:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You let out [pc.a_moan+] as you prepare to reach your climax.";
				} else {
					orgasmText = "[npc.Name] lets out [npc.a_moan+] as [npc.she] prepares to reach [npc.her] climax.";
				}
				break;
			case DOGGY_INFRONT:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You shuffle forwards, pressing your groin up against [npc.name]'s [npc.face+] and letting out [pc.a_moan+] as you prepare to reach your climax.";
				} else {
					orgasmText = "[npc.Name] shuffles forwards, pressing [npc.her] groin up against your [pc.face+] and letting out [npc.a_moan+] as [npc.she] prepares to reach [npc.her] climax.";
				}
				break;
			case DOGGY_INFRONT_ANAL:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You shuffle backwards, pressing your [pc.ass+] up against [npc.name]'s [npc.face+] and letting out [pc.a_moan+] as you prepare to reach your climax.";
				} else {
					orgasmText = "[npc.Name] shuffles backwards, pressing [npc.her] [npc.ass+] up against your [pc.face+] and letting out [npc.a_moan+] as [npc.she] prepares to reach [npc.her] climax.";
				}
				break;
			case DOGGY_ON_ALL_FOURS: case DOGGY_ON_ALL_FOURS_AMBER:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You brace yourself on all fours, letting out [pc.a_moan+] as you prepare to reach your climax.";
				} else {
					orgasmText = "[npc.Name] braces [npc.herself] on all fours, letting out [npc.a_moan+] as [npc.she] prepares to reach [npc.her] climax.";
				}
				break;
			case FACE_TO_WALL_AGAINST_WALL: case FACE_TO_WALL_AGAINST_WALL_SHOWER_PIX:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You brace yourself against the wall in front of you, letting out [pc.a_moan+] as you prepare to reach your climax.";
				} else {
					orgasmText = "[npc.Name] braces [npc.herself] against the wall in front of [npc.herHim], letting out [npc.a_moan+] as [npc.she] prepares to reach [npc.her] climax.";
				}
				break;
			case FACE_TO_WALL_FACING_TARGET: case FACE_TO_WALL_FACING_TARGET_SHOWER_PIX:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You press yourself into [npc.name]'s back, letting out [pc.a_moan+] as you prepare to reach your climax.";
				} else {
					orgasmText = "[npc.Name] presses [npc.herself] into your back, letting out [npc.a_moan+] as [npc.she] prepares to reach [npc.her] climax.";
				}
				break;
			case HAND_SEX_DOM_ROSE:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You look into [npc.name]'s [npc.eyes+] and let out [pc.a_moan+] as you reach your climax.";
				} else {
					orgasmText = "[npc.Name] looks into your [npc.eyes+] and lets out [npc.a_moan+] as [npc.she] reaches [npc.her] climax.";
				}
				break;
			case HAND_SEX_SUB_ROSE:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You look into [npc.name]'s [npc.eyes+] and let out [pc.a_moan+] as you reach your climax.";
				} else {
					orgasmText = "[npc.Name] looks into your [npc.eyes+] and lets out [npc.a_moan+] as [npc.she] reaches [npc.her] climax.";
				}
				break;
			case KNEELING_PERFORMING_ORAL: case KNEELING_PERFORMING_ORAL_CULTIST: case KNEELING_PERFORMING_ORAL_RALPH: case KNEELING_PERFORMING_ORAL_ZARANIX:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You reach up and place a [pc.hand] on one of [npc.name]'s [npc.legs], before letting out [pc.a_moan+] as you prepare to reach your climax.";
				} else {
					orgasmText = "[npc.Name] reaches up and places a [npc.hand] on one of your [pc.legs], before letting out [npc.a_moan+] as [npc.she] prepares to reach [npc.her] climax.";
				}
				break;
			case KNEELING_RECEIVING_ORAL: case KNEELING_RECEIVING_ORAL_CULTIST: case KNEELING_RECEIVING_ORAL_RALPH: case KNEELING_RECEIVING_ORAL_ZARANIX:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You reach down and place a [pc.hand] on [npc.name]'s head, before letting out [pc.a_moan+] as you reach your climax.";
				} else {
					orgasmText = "[npc.Name] reaches down and places a [npc.hand] on your head, before letting out [npc.a_moan+] as [npc.she] reaches [npc.her] climax.";
				}
				break;
			case MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You reach up and place a [pc.hand] on one of [npc.name]'s [npc.legs], before letting out [pc.a_moan+] as you prepare to reach your climax.";
				} else {
					orgasmText = "[npc.Name] reaches up and places a [npc.hand] on one of your [npc.legs], before letting out [npc.a_moan+] as [npc.she] prepares to reach [npc.her] climax.";
				}
				break;
			case MISSIONARY_ALTAR_LYING_ON_ALTAR:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You look up into [npc.name]'s [npc.eyes] and let out [pc.a_moan+] as you prepare to reach your climax.";
				} else {
					orgasmText = "[npc.Name] looks up into your [npc.eyes] and lets out [npc.a_moan+] as [npc.she] prepares to reach [npc.her] climax.";
				}
				break;
			case MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You reach up and place a [pc.hand] on one of [npc.name]'s [npc.legs], before letting out [pc.a_moan+] as you prepare to reach your climax.";
				} else {
					orgasmText = "[npc.Name] reaches up and place a [npc.hand] on one of your [npc.legs], before letting out [npc.a_moan+] as [npc.she] prepares to reach [npc.her] climax.";
				}
				break;
			case MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You look up into [npc.name]'s [npc.eyes] and let out [pc.a_moan+] as you prepare to reach your climax.";
				} else {
					orgasmText = "[npc.Name] looks up into your [npc.eyes] and lets out [npc.a_moan+] as [npc.she] prepares to reach [npc.her] climax.";
				}
				break;
			case MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS: case MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You look down into [npc.name]'s [npc.eyes] and let out [pc.a_moan+] as you prepare to reach your climax.";
				} else {
					orgasmText = "[npc.Name] looks down into your [npc.eyes] and lets out [npc.a_moan+] as [npc.she] prepares to reach [npc.her] climax.";
				}
				break;
			case MISSIONARY_DESK_DOM_VICKY:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You look down into [npc.name]'s [npc.eyes] and let out [pc.a_moan+] as you prepare to reach your climax.";
				} else {
					orgasmText = "[npc.Name] looks down into your [npc.eyes] and lets out [npc.a_moan+] as [npc.she] prepares to reach [npc.her] climax.";
				}
				break;
			case MISSIONARY_DESK_SUB_VICKY:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You look up into [npc.name]'s [npc.eyes] and let out [pc.a_moan+] as you prepare to reach your climax.";
				} else {
					orgasmText = "[npc.Name] looks up into your [npc.eyes] and lets out [npc.a_moan+] as [npc.she] prepares to reach [npc.her] climax.";
				}
				break;
			case SIXTY_NINE_BOTTOM:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You reach up and grab [npc.name]'s waist, letting out [pc.a_moan+] as you prepare to reach your climax.";
				} else {
					orgasmText = "[npc.Name] reaches up and grabs your waist, letting out [npc.a_moan+] as [npc.she] prepares to reach [npc.her] climax.";
				}
				break;
			case SIXTY_NINE_TOP:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You drop your [pc.face+] down into [npc.name]'s groin, letting out [pc.a_moan+] as you prepare to reach your climax.";
				} else {
					orgasmText = "[npc.Name] drops [npc.her] [npc.face+] down into your groin, letting out [npc.a_moan+] as [npc.she] prepares to reach [npc.her] climax.";
				}
				break;
			case STANDING_DOMINANT:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You reach around and grab [npc.name]'s [npc.ass+], pulling [npc.herHim] into you and letting out [pc.a_moan+] as you prepare to reach your climax.";
				} else {
					orgasmText = "[npc.Name] reaches around and grabs your [npc.ass+], pulling you into [npc.herHim] and letting out [npc.a_moan+] as [npc.she] prepares to reach [npc.her] climax.";
				}
				break;
			case STANDING_SUBMISSIVE:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You reach up and grab [npc.name]'s shoulders, leaning into [npc.herHim] and letting out [pc.a_moan+] as you prepare to reach your climax.";
				} else {
					orgasmText = "[npc.Name] reaches up and grabs your shoulders, leaning into you and letting out [npc.a_moan+] as [npc.she] prepares to reach [npc.her] climax.";
				}
				break;
			case STOCKS_FUCKING:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You reach down and grab [npc.name]'s waist, pulling [npc.herHim] back into you and letting out [pc.a_moan+] as you prepare to reach your climax.";
				} else {
					orgasmText = "[npc.Name] reaches down and grabs your waist, pulling you back into [npc.herHim] and letting out [npc.a_moan+] as [npc.she] prepares to reach [npc.her] climax.";
				}
				break;
			case STOCKS_LOCKED_IN_STOCKS:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "Unable to move, you wriggle around in the stocks and let out [pc.a_moan+] as you prepare to reach your climax.";
				} else {
					orgasmText = "Unable to move, [npc.name] wriggles around in the stocks and lets out [npc.a_moan+] as [npc.she] prepares to reach [npc.her] climax.";
				}
				break;
			case STOCKS_PERFORMING_ORAL:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You reach up and place a [pc.hand] on one of [npc.name]'s [npc.legs], letting out [pc.a_moan+] as you prepare to reach your climax.";
				} else {
					orgasmText = "[npc.Name] reaches up and places a [npc.hand] on one of your [npc.legs], letting out [npc.a_moan+] as [npc.she] prepares to reach [npc.her] climax.";
				}
				break;
			case STOCKS_RECEIVING_ORAL:
				if(characterOrgasming.isPlayer()) {
					orgasmText = "You reach down and place a [pc.hand] on [npc.name]'s head, letting out [pc.a_moan+] as you prepare to reach your climax.";
				} else {
					orgasmText = "[npc.Name] reaches down and places a [npc.hand] on your head, letting out [npc.a_moan+] as [npc.she] prepares to reach [npc.her] climax.";
				}
				break;
		}
		
		return UtilText.parse(characterOrgasming.isPlayer()?Sex.getTargetedPartner(characterOrgasming):characterOrgasming, orgasmText);
	}
	
	private static StringBuilder genericOrgasmSB = new StringBuilder();
	private static String getGenericPenisOrgasmDescription(GameCharacter characterOrgasming, OrgasmCumTarget cumTarget) {
		genericOrgasmSB.setLength(0);
		
		genericOrgasmSB.append(getPositionPreparation(characterOrgasming));
		
		GameCharacter characterPenetrated = null;
		OrificeType orificePenetrated = null;
		if(!Sex.getCharactersBeingPenetratedBy(characterOrgasming, PenetrationType.PENIS).isEmpty()) {
			characterPenetrated = Sex.getCharactersBeingPenetratedBy(characterOrgasming, PenetrationType.PENIS).get(0);
			orificePenetrated = Sex.getOrificesBeingPenetratedBy(characterOrgasming, PenetrationType.PENIS, characterPenetrated).get(0);
		}
		
		if(characterPenetrated==null || cumTarget!=OrgasmCumTarget.INSIDE) {
			
			List<String> modifiers = new ArrayList<>();
			for(PenisModifier mod : PenisModifier.values()) {
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
								modifiers.add(" The wide, flared head of [npc.name]'s [npc.cock] swells up, and [npc.she] feels [npc.her] [npc.balls+] tightening as [npc.she] starts to cum.");
							}
						}
						break;
					case KNOTTED:
						if(characterOrgasming.hasPenisModifier(mod)) {
							if(characterOrgasming.isPlayer()) {
								modifiers.add(" The thick knot at the base of your [npc1.cock] swells up, and you feel your [npc1.balls+] tightening as you start to cum.");
							} else {
								modifiers.add(" The thick knot at the base of [npc.name]'s [npc.cock] swells up, and [npc.she] feels [npc.her] [npc.balls+] tightening as [npc.she] starts to cum.");
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
								modifiers.add(" The little tentacles lining [npc.name]'s [npc.cock] start frantically wriggling, and [npc.she] feels [npc.her] [npc.balls+] tightening as [npc.she] starts to cum.");
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
					genericOrgasmSB.append(" [npc.Name]'s [npc1.cock+] twitches, and [npc.she] feels [npc.her] [npc.balls+] tightening as [npc.she] starts to cum.");
				}
			}

			if(characterPenetrated!=null) {
				switch(orificePenetrated) {
					case ANUS:
						if(characterOrgasming.isPlayer()) {
							genericOrgasmSB.append(" You slide your [npc1.cock+] out of [npc2.name]'s [npc2.asshole+].");
						} else {
							if(characterPenetrated.isPlayer()) {
								genericOrgasmSB.append(" [npc1.Name] slides [npc.her] [npc1.cock+] out of your [npc2.asshole+].");
							} else {
								genericOrgasmSB.append(" [npc1.Name] slides [npc.her] [npc1.cock+] out of [npc2.name]'s [npc2.asshole+].");
							}
						}
						break;
					case ASS:
						if(characterOrgasming.isPlayer()) {
							genericOrgasmSB.append(" You slide your [npc1.cock+] out from between [npc2.name]'s ass cheeks.");
						} else {
							if(characterPenetrated.isPlayer()) {
								genericOrgasmSB.append(" [npc1.Name] slides [npc.her] [npc1.cock+] out from between your ass cheeks.");
							} else {
								genericOrgasmSB.append(" [npc1.Name] slides [npc.her] [npc1.cock+] out from between [npc2.name]'s ass cheeks.");
							}
						}
						break;
					case BREAST:
						if(characterOrgasming.isPlayer()) {
							genericOrgasmSB.append(" You slide your [npc1.cock+] out from between [npc2.name]'s [npc2.breasts+].");
						} else {
							if(characterPenetrated.isPlayer()) {
								genericOrgasmSB.append(" [npc1.Name] slides [npc.her] [npc1.cock+] out from between your [npc2.breasts+].");
							} else {
								genericOrgasmSB.append(" [npc1.Name] slides [npc.her] [npc1.cock+] out from between [npc2.name]'s [npc2.breasts+].");
							}
						}
						break;
					case MOUTH:
						if(characterOrgasming.isPlayer()) {
							genericOrgasmSB.append(" You slide your [npc1.cock+] out of [npc2.name]'s mouth.");
						} else {
							if(characterPenetrated.isPlayer()) {
								genericOrgasmSB.append(" [npc1.Name] slides [npc.her] [npc1.cock+] out of your mouth.");
							} else {
								genericOrgasmSB.append(" [npc1.Name] slides [npc.her] [npc1.cock+] out of [npc2.name]'s mouth.");
							}
						}
						break;
					case NIPPLE:
						if(characterOrgasming.isPlayer()) {
							genericOrgasmSB.append(" You slide your [npc1.cock+] out of [npc2.name]'s [npc2.nipple+].");
						} else {
							if(characterPenetrated.isPlayer()) {
								genericOrgasmSB.append(" [npc1.Name] slides [npc.her] [npc1.cock+] out of your [npc2.nipple+].");
							} else {
								genericOrgasmSB.append(" [npc1.Name] slides [npc.her] [npc1.cock+] out of [npc2.name]'s [npc2.nipple+].");
							}
						}
						break;
					case THIGHS:
						if(characterOrgasming.isPlayer()) {
							genericOrgasmSB.append(" You slide your [npc1.cock+] out from between [npc2.name]'s thighs.");
						} else {
							if(characterPenetrated.isPlayer()) {
								genericOrgasmSB.append(" [npc1.Name] slides [npc.her] [npc1.cock+] out from between your thighs.");
							} else {
								genericOrgasmSB.append(" [npc1.Name] slides [npc.her] [npc1.cock+] out from between [npc2.name]'s thighs.");
							}
						}
						break;
					case URETHRA:
						if(characterOrgasming.isPlayer()) {
							genericOrgasmSB.append(" You slide your [npc1.cock+] out of [npc2.name]'s [npc2.urethra+].");
						} else {
							if(characterPenetrated.isPlayer()) {
								genericOrgasmSB.append(" [npc1.Name] slides [npc.her] [npc1.cock+] out of your [npc2.urethra+].");
							} else {
								genericOrgasmSB.append(" [npc1.Name] slides [npc.her] [npc1.cock+] out of [npc2.name]'s [npc2.urethra+].");
							}
						}
						break;
					case VAGINA:
						if(characterOrgasming.isPlayer()) {
							genericOrgasmSB.append(" You slide your [npc1.cock+] out of [npc2.name]'s [npc2.pussy+].");
						} else {
							if(characterPenetrated.isPlayer()) {
								genericOrgasmSB.append(" [npc1.Name] slides [npc.her] [npc1.cock+] out of your [npc2.pussy+].");
							} else {
								genericOrgasmSB.append(" [npc1.Name] slides [npc.her] [npc1.cock+] out of [npc2.name]'s [npc2.pussy+].");
							}
						}
						break;
					default:
						break;
				}
			}
			
			
		} else if(cumTarget==OrgasmCumTarget.INSIDE) {

			List<String> modifiers = new ArrayList<>();
			
			switch(orificePenetrated) {
				case ANUS: case NIPPLE: case VAGINA:
					String orificeName = (orificePenetrated == OrificeType.VAGINA
							?"[npc2.pussy]"
							:(orificePenetrated == OrificeType.ANUS
								?"[npc2.asshole]"
								:"[npc2.nipple]"));
					String orificeNamePlusDescriptor = (orificePenetrated == OrificeType.VAGINA
							?"[npc2.pussy+]"
							:(orificePenetrated == OrificeType.ANUS
								?"[npc2.asshole+]"
								:"[npc2.nipple+]"));
					
					if(characterOrgasming.hasPenisModifier(PenisModifier.KNOTTED)) {
						if(characterOrgasming.isPlayer()) {
							genericOrgasmSB.append(" Ramming the knot at the base of your [npc1.cock+] into [npc2.name]'s "+orificeNamePlusDescriptor+", you let out [npc1.a_moan+] as you feel it start to swell up.");
						} else {
							if(characterPenetrated.isPlayer()) {
								genericOrgasmSB.append(" Ramming the knot at the base of [npc1.her] [npc1.cock+] into your "+orificeNamePlusDescriptor+", [npc1.name] lets out [npc1.a_moan+] as it starts to swell up inside of you.");
							} else {
								genericOrgasmSB.append(" Ramming the knot at the base of [npc1.her] [npc1.cock+] into [npc2.name]'s "+orificeNamePlusDescriptor+", [npc1.name] lets out [npc1.a_moan+] as it starts to swell up inside of [npc2.herHim].");
							}
						}
						
					} else {
						if(characterOrgasming.isPlayer()) {
							genericOrgasmSB.append(" Ramming your [npc1.cock+] deep into [npc2.name]'s "+orificeNamePlusDescriptor+", you let out [npc1.a_moan+] as you feel your [npc1.cock+] start to twitch.");
						} else {
							if(characterPenetrated.isPlayer()) {
								genericOrgasmSB.append(" Ramming [npc1.her] [npc1.cock+] deep into your "+orificeNamePlusDescriptor+", [npc1.name] lets out [npc1.a_moan+] as it starts to twitch inside of you.");
							} else {
								genericOrgasmSB.append(" Ramming [npc1.her] [npc1.cock+] deep into [npc2.name]'s "+orificeNamePlusDescriptor+", [npc1.name] lets out [npc1.a_moan+] as it starts to twitch inside of [npc2.herHim].");
							}
						}
					}
					
					modifiers.clear();
					for(PenisModifier mod : PenisModifier.values()) {
						switch(mod) {
							case BARBED:
								if(characterOrgasming.hasPenisModifier(mod)) {
									if(characterOrgasming.isPlayer()) {
										modifiers.add(" You continue to make small, thrusting movements, raking your barbs back against the inner walls of [npc2.her] "+orificeName+" and causing [npc2.herHim] to let out [npc2.a_moan+].");
									} else {
										if(characterPenetrated.isPlayer()) {
											modifiers.add(" [npc1.Name] continues to make small, thrusting movements, raking [npc1.her] barbs back against the inner walls of your "+orificeName+" and causing you to let out [npc2.a_moan+].");
										} else {
											modifiers.add(" [npc1.Name] continues to make small, thrusting movements, raking [npc1.her] barbs back against the inner walls of [npc2.name]'s "+orificeName+" and causing [npc2.herHim] to let out [npc2.a_moan+].");
										}
									}
								}
								break;
							case BLUNT:
								break;
							case FLARED:
								if(characterOrgasming.hasPenisModifier(mod)) {
									if(characterOrgasming.isPlayer()) {
										modifiers.add(" You feel the wide, flared head of your [npc1.cock] swell up, making a seal with which to trap your [npc1.cum] deep within [npc2.her] "+orificeName+".");
									} else {
										if(characterPenetrated.isPlayer()) {
											modifiers.add(" The wide, flared head of [npc1.name]'s [npc1.cock] swells up, making a seal with which to trap [npc1.her] [npc1.cum] deep within your "+orificeName+".");
										} else {
											modifiers.add(" The wide, flared head of [npc1.name]'s [npc1.cock] swells up, making a seal with which to trap [npc1.her] [npc1.cum] deep within [npc2.name]'s "+orificeName+".");
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
										modifiers.add(" You feel your ribbed [npc1.cock] bumping against the inner walls of [npc2.her] "+orificeName+", which causes [npc2.herHim] to let out [npc2.a_moan+].");
									} else {
										if(characterPenetrated.isPlayer()) {
											modifiers.add(" The ribbed length of [npc1.name]'s [npc1.cock] bumps against the inner walls of your "+orificeName+", which causes you to let out [npc2.a_moan+].");
										} else {
											modifiers.add(" The ribbed length of [npc1.name]'s [npc1.cock] bumps against the inner walls of [npc2.name]'s "+orificeName+", which causes [npc2.herHim] to let out [npc2.a_moan+].");
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
										modifiers.add(" You feel the wriggling tentacles lining your [npc1.cock] start to massage the inner walls of [npc2.her] "+orificeName+", which causes [npc2.herHim] to let out [npc2.a_moan+].");
									} else {
										if(characterPenetrated.isPlayer()) {
											modifiers.add(" The wriggling tentacles lining [npc1.name]'s [npc1.cock] start to massage the inner walls of your "+orificeName+", which causes you to let out [npc2.a_moan+].");
										} else {
											modifiers.add(" The wriggling tentacles lining [npc1.name]'s [npc1.cock] start to massage the inner walls of [npc2.name]'s "+orificeName+", which causes [npc2.herHim] to let out [npc2.a_moan+].");
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
					
					if(characterOrgasming.hasPenisModifier(PenisModifier.KNOTTED)) {
						if(characterOrgasming.isPlayer()) {
							genericOrgasmSB.append(" After just a moment, your knot has fully swollen in size, locking your [npc1.cock] inside [npc2.name]'s "+orificeName+".");
						} else {
							if(characterPenetrated.isPlayer()) {
								genericOrgasmSB.append(" After just a moment, [npc1.her] knot has fully swollen in size, locking [npc1.her] [npc1.cock] inside your "+orificeName+".");
							} else {
								genericOrgasmSB.append(" After just a moment, [npc1.her] knot has fully swollen in size, locking [npc1.her] [npc1.cock] inside [npc2.name]'s "+orificeName+".");
							}
						}
					}
					break;
					
				case ASS:
					break;
				case BREAST:
					break;
					
				case MOUTH:
					if(characterOrgasming.hasPenisModifier(PenisModifier.KNOTTED)) {
						if(characterOrgasming.isPlayer()) {
							genericOrgasmSB.append(" Ramming the knot at the base of your [npc1.cock+] into [npc2.name]'s mouth, you let out [npc1.a_moan+] as you feel it start to swell up.");
						} else {
							if(characterPenetrated.isPlayer()) {
								genericOrgasmSB.append(" Ramming the knot at the base of [npc1.her] [npc1.cock+] into your mouth, [npc1.name] lets out [npc1.a_moan+] as it starts to swell up inside of you.");
							} else {
								genericOrgasmSB.append(" Ramming the knot at the base of [npc1.her] [npc1.cock+] into [npc2.name]'s mouth, [npc1.name] lets out [npc1.a_moan+] as it starts to swell up inside of [npc2.herHim].");
							}
						}
						
					} else {
						if(characterOrgasming.isPlayer()) {
							genericOrgasmSB.append(" Ramming your [npc1.cock+] deep down [npc2.name]'s throat, you let out [npc1.a_moan+] as you feel your [npc1.cock+] start to twitch.");
						} else {
							if(characterPenetrated.isPlayer()) {
								genericOrgasmSB.append(" Ramming [npc1.her] [npc1.cock+] deep down your throat, [npc1.name] lets out [npc1.a_moan+] as it starts to twitch inside of you.");
							} else {
								genericOrgasmSB.append(" Ramming [npc1.her] [npc1.cock+] deep down [npc2.name]'s throat, [npc1.name] lets out [npc1.a_moan+] as it starts to twitch inside of [npc2.herHim].");
							}
						}
					}
					
					modifiers.clear();
					for(PenisModifier mod : PenisModifier.values()) {
						switch(mod) {
							case BARBED:
								if(characterOrgasming.hasPenisModifier(mod)) {
									if(characterOrgasming.isPlayer()) {
										modifiers.add(" You continue to make small, thrusting movements, raking your barbs back against the lining of [npc2.her] throat and causing [npc2.herHim] to let out a choking [npc2.moan].");
									} else {
										if(characterPenetrated.isPlayer()) {
											modifiers.add(" [npc1.Name] continues to make small, thrusting movements, raking [npc1.her] barbs back against the lining of your throat and causing you to let out a choking [npc2.moan].");
										} else {
											modifiers.add(" [npc1.Name] continues to make small, thrusting movements, raking [npc1.her] barbs back against the lining of [npc2.name]'s throat and causing [npc2.herHim] to let out a choking [npc2.moan].");
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
										if(characterPenetrated.isPlayer()) {
											modifiers.add(" The wide, flared head of [npc1.name]'s [npc1.cock] swells up, making a seal with which to trap [npc1.her] [npc1.cum] deep down your throat.");
										} else {
											modifiers.add(" The wide, flared head of [npc1.name]'s [npc1.cock] swells up, making a seal with which to trap [npc1.her] [npc1.cum] deep down [npc2.name]'s throat.");
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
										if(characterPenetrated.isPlayer()) {
											modifiers.add(" The ribbed length of [npc1.name]'s [npc1.cock] bumps against the lining of your throat, which causes you to let out a muffled [npc2.moan].");
										} else {
											modifiers.add(" The ribbed length of [npc1.name]'s [npc1.cock] bumps against the lining of [npc2.name]'s throat, which causes [npc2.herHim] to let out a muffled [npc2.moan].");
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
										if(characterPenetrated.isPlayer()) {
											modifiers.add(" The wriggling tentacles lining [npc1.name]'s [npc1.cock] start to massage the lining of your throat, which causes you to let out a muffled [npc2.moan].");
										} else {
											modifiers.add(" The wriggling tentacles lining [npc1.name]'s [npc1.cock] start to massage the lining of [npc2.name]'s throat, which causes [npc2.herHim] to let out a muffled [npc2.moan].");
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
					
					if(characterOrgasming.hasPenisModifier(PenisModifier.KNOTTED)) {
						if(characterOrgasming.isPlayer()) {
							genericOrgasmSB.append(" After just a moment, your knot has fully swollen in size, locking your [npc1.cock] inside [npc2.name]'s [npc2.mouth].");
						} else {
							if(characterPenetrated.isPlayer()) {
								genericOrgasmSB.append(" After just a moment, [npc1.her] knot has fully swollen in size, locking [npc1.her] [npc1.cock] inside your [npc2.mouth].");
							} else {
								genericOrgasmSB.append(" After just a moment, [npc1.her] knot has fully swollen in size, locking [npc1.her] [npc1.cock] inside [npc2.name]'s [npc2.mouth].");
							}
						}
					}
					break;
					
				case THIGHS:
					break;
				case URETHRA:
					break;
				default:
					break;
			
			}
		}

		if(characterOrgasming.isPlayer()) {
			genericOrgasmSB.append(" As your [npc1.balls+] tense up, ");
		} else {
			genericOrgasmSB.append(" As [npc1.her] [npc1.balls+] tense up, ");
		}
		genericOrgasmSB.append(getCumQuantityDescription(characterOrgasming));
		
		genericOrgasmSB.append(cumTargetDescription(characterOrgasming, characterPenetrated, cumTarget));
		
		// TODO reaction from partner
		
		if(characterPenetrated!=null) {
			return UtilText.parse(characterOrgasming, characterPenetrated, genericOrgasmSB.toString());
		} else {
			return UtilText.parse(characterOrgasming, genericOrgasmSB.toString());
		}
	}
	
	private static String getCumQuantityDescription(GameCharacter characterOrgasming) {
		
		String targetName = "#IFnpc1.isPlayer#THENyour#ELSE[npc1.name]'s#ENDIF";
		String cumQuantityDescription = targetName+" [npc1.cum+] squirts";
		
		switch (Main.game.getPlayer().getPenisCumProduction()) {
			case ZERO_NONE:
				cumQuantityDescription = "not even a single drop of [npc1.cum] leaks out";
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
			if(characterOrgasming.isPlayer()) {
				return " into the condom that you're wearing.";
			} else {
				return UtilText.parse(characterOrgasming, " into the condom that [npc1.she]'s wearing.");
			}
			
		} else if (!characterOrgasming.isCoverableAreaExposed(CoverableArea.PENIS)) {
			if(characterOrgasming.isPlayer()) {
				return "  into your [npc1.lowClothing(penis)].";
			} else {
				return UtilText.parse(characterOrgasming, "  into [npc1.her] [npc1.lowClothing(penis)].");
			}
			
		} else {
			boolean cumInside = false;
			
			switch(targetArea) { //TODO clothing detection
				case ASS:
					if (Sex.getTargetedPartner(characterOrgasming).getHighestZLayerCoverableArea(CoverableArea.ASS)!=null) {
						if(!Sex.getTargetedPartner(characterOrgasming).isPlayer()) {
							return UtilText.parse(Sex.getTargetedPartner(characterOrgasming), " all over [npc.name]'s "+Sex.getTargetedPartner(characterOrgasming).getHighestZLayerCoverableArea(CoverableArea.ASS).getName()+".");
						} else {
							return " all over your "+Sex.getTargetedPartner(characterOrgasming).getHighestZLayerCoverableArea(CoverableArea.ASS).getName()+".";
						}
					} else {
						if(!Sex.getTargetedPartner(characterOrgasming).isPlayer()) {
							return UtilText.parse(Sex.getTargetedPartner(characterOrgasming), " all over [npc.name]'s [npc.ass+].");
						} else {
							return " all over your [pc.ass+].";
						}
					}
				case BACK:
					if (Sex.getTargetedPartner(characterOrgasming).getHighestZLayerCoverableArea(CoverableArea.BACK)!=null) {
						if(!Sex.getTargetedPartner(characterOrgasming).isPlayer()) {
							return UtilText.parse(Sex.getTargetedPartner(characterOrgasming), " all over [npc.name]'s "+Sex.getTargetedPartner(characterOrgasming).getHighestZLayerCoverableArea(CoverableArea.BACK).getName()+".");
						} else {
							return " all over your "+Sex.getTargetedPartner(characterOrgasming).getHighestZLayerCoverableArea(CoverableArea.BACK).getName()+".";
						}
					} else {
						if(!Sex.getTargetedPartner(characterOrgasming).isPlayer()) {
							return UtilText.parse(Sex.getTargetedPartner(characterOrgasming), " all over [npc.name]'s back.");
						} else {
							return " all over your back.";
						}
					}
				case BREASTS:
					if (Sex.getTargetedPartner(characterOrgasming).getHighestZLayerCoverableArea(CoverableArea.BREASTS)!=null) {
						if(!Sex.getTargetedPartner(characterOrgasming).isPlayer()) {
							return UtilText.parse(Sex.getTargetedPartner(characterOrgasming), " all over [npc.name]'s "+Sex.getTargetedPartner(characterOrgasming).getHighestZLayerCoverableArea(CoverableArea.BREASTS).getName()+".");
						} else {
							return " all over your "+Sex.getTargetedPartner(characterOrgasming).getHighestZLayerCoverableArea(CoverableArea.BREASTS).getName()+".";
						}
					} else {
						if(!Sex.getTargetedPartner(characterOrgasming).isPlayer()) {
							return UtilText.parse(Sex.getTargetedPartner(characterOrgasming), " all over [npc.name]'s [npc.breasts+].");
						} else {
							return " all over your [pc.breasts+].";
						}
					}
				case FACE:
					if (Sex.getTargetedPartner(characterOrgasming).getHighestZLayerCoverableArea(CoverableArea.MOUTH)!=null) {
						if(!Sex.getTargetedPartner(characterOrgasming).isPlayer()) {
							return UtilText.parse(Sex.getTargetedPartner(characterOrgasming), " all over [npc.name]'s "+Sex.getTargetedPartner(characterOrgasming).getHighestZLayerCoverableArea(CoverableArea.MOUTH).getName()+".");
						} else {
							return " all over your "+Sex.getTargetedPartner(characterOrgasming).getHighestZLayerCoverableArea(CoverableArea.MOUTH).getName()+".";
						}
					} else {
						if(!Sex.getTargetedPartner(characterOrgasming).isPlayer()) {
							return UtilText.parse(Sex.getTargetedPartner(characterOrgasming), " all over [npc.name]'s face.");
						} else {
							return " all over your face.";
						}
					}
				case FLOOR:
					return " out all over the floor.";
				case STOMACH:
					if (Sex.getTargetedPartner(characterOrgasming).getHighestZLayerCoverableArea(CoverableArea.STOMACH)!=null) {
						if(!Sex.getTargetedPartner(characterOrgasming).isPlayer()) {
							return UtilText.parse(Sex.getTargetedPartner(characterOrgasming), " all over [npc.name]'s "+Sex.getTargetedPartner(characterOrgasming).getHighestZLayerCoverableArea(CoverableArea.STOMACH).getName()+".");
						} else {
							return " all over your "+Sex.getTargetedPartner(characterOrgasming).getHighestZLayerCoverableArea(CoverableArea.STOMACH).getName()+".";
						}
					} else {
						if(!Sex.getTargetedPartner(characterOrgasming).isPlayer()) {
							return UtilText.parse(Sex.getTargetedPartner(characterOrgasming), " all over [npc.name]'s "+(characterOrgasming.isVisiblyPregnant()?"pregnant belly":"stomach")+".");
						} else {
							return " all over your "+(characterOrgasming.isVisiblyPregnant()?"pregnant belly":"stomach")+".";
						}
					}
				case GROIN:
					target = Sex.getTargetedPartner(characterOrgasming);
					if(target.isPlayer()) {
						cumTargetSB.append(" all over your");
					} else {
						cumTargetSB.append(" all over [npc.her]");
					}
					if(target.hasPenis()) {
						if(target.hasVagina()) {
							cumTargetSB.append(" [npc.cock] and [npc.pussy].");
						} else {
							cumTargetSB.append(" [npc.cock+].");
						}
					} else if(target.hasVagina()) {
						cumTargetSB.append(" [npc.pussy+].");
					} else {
						cumTargetSB.append(" genderless mound.");
					}
					
					return UtilText.parse(target, cumTargetSB.toString());
					
				case INSIDE:
					cumInside = true;
					break;
				case HAIR:
					if (Sex.getTargetedPartner(characterOrgasming).getHighestZLayerCoverableArea(CoverableArea.HAIR)!=null) {
						if(!Sex.getTargetedPartner(characterOrgasming).isPlayer()) {
							return UtilText.parse(Sex.getTargetedPartner(characterOrgasming), " all over [npc.name]'s "+Sex.getTargetedPartner(characterOrgasming).getHighestZLayerCoverableArea(CoverableArea.HAIR).getName()+".");
						} else {
							return " all over your "+Sex.getTargetedPartner(characterOrgasming).getHighestZLayerCoverableArea(CoverableArea.HAIR).getName()+".";
						}
					} else {
						if(!Sex.getTargetedPartner(characterOrgasming).isPlayer()) {
							return UtilText.parse(Sex.getTargetedPartner(characterOrgasming), " all over [npc.name]'s [npc.head] and [npc.hair].");
						} else {
							return " all over your [pc.head] and [pc.hair].";
						}
					}
				case LEGS:
					if (Sex.getTargetedPartner(characterOrgasming).getHighestZLayerCoverableArea(CoverableArea.LEGS)!=null) {
						if(!Sex.getTargetedPartner(characterOrgasming).isPlayer()) {
							return UtilText.parse(Sex.getTargetedPartner(characterOrgasming), " all over [npc.name]'s "+Sex.getTargetedPartner(characterOrgasming).getHighestZLayerCoverableArea(CoverableArea.LEGS).getName()+".");
						} else {
							return " all over your "+Sex.getTargetedPartner(characterOrgasming).getHighestZLayerCoverableArea(CoverableArea.LEGS).getName()+".";
						}
					} else {
						if(!Sex.getTargetedPartner(characterOrgasming).isPlayer()) {
							return UtilText.parse(Sex.getTargetedPartner(characterOrgasming), " all over [npc.name]'s [npc.legs].");
						} else {
							return " all over your [pc.legs].";
						}
					}
				case WALL:
					return " all up the wall";
			}
			//TODO more detail
//			if(Main.game.getPlayer().hasPenisModifier(PenisModifier.KNOTTED)) {
//				knottedSpecial = " Your [pc.cock+] suddenly starts violently throbbing, and you know that you're about to cum."
//										+ " Grabbing your [pc.cock] in one [pc.hand], you point it at [npc.name]'s ass, furiously masturbating as your thick knot swells up.";
//			}
//			if(Main.game.getPlayer().hasPenisModifier(PenisModifier.BARBED)) {
//				barbedSpecial = " Your barbed [pc.cock] suddenly starts violently throbbing, and you know that you're about to cum."
//						+ " Grabbing your [pc.cock] in one hand, you point it at [npc.name]'s ass, furiously masturbating as your hand slides up and down over your sensitive little barbs.";
//			}
//			if(Main.game.getPlayer().hasPenisModifier(PenisModifier.FLARED)) {
//				flaredSpecial = " Your flared [pc.cock] suddenly starts violently throbbing, and you know that you're about to cum."
//						+ " Grabbing your [pc.cock] in one [pc.hand], you point it at [npc.name]'s ass, furiously masturbating as your flared head swells up.";
//			}
			
			if(!cumInside) {
				return UtilText.parse(characterOrgasming, Sex.getTargetedPartner(characterOrgasming), cumTargetSB.toString());
				
			} else { //TODO cum inflation
				
				OrificeType orificePenetrated = Sex.getOrificesBeingPenetratedBy(characterOrgasming, PenetrationType.PENIS, target).get(0);
				
				switch(orificePenetrated) {
					case ANUS:
						if(target.isPlayer()) {
							cumTargetSB.append(" deep into your [npc2.asshole+], and you find yourself whining and moaning as you feel the [npc1.cum+] deep inside of you.");
						} else {
							cumTargetSB.append(" deep into [npc2.name]'s [npc2.asshole+].");
						}
						switch (characterOrgasming.getPenisCumProduction()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								if(characterOrgasming.isPlayer()) {
									cumTargetSB.append(" After a few seconds, [npc.name] realises that you're not even close to stopping, and as your "
											+"[pc.cum+] backs up and starts drooling out of [npc.her] [npc.asshole], [npc.she] lets out a desperate groan."
													+ " It takes a while before you finally feel your balls running dry, by which time "
														+"[npc.name]'s stomach has quite visibly distended, and [npc.she] lets out a final, satisfied moan as [npc.she] feels the massive amount of "
																+"[pc.cum+] that you've deposited deep in [npc.her] ass.");
								}
								break;
							default:
								break;
						}
						break;
					case ASS:
						break;
					case BREAST:
						break;
					case MOUTH:
						if(target.isPlayer()) {
							cumTargetSB.append(" deep down your throat, and you find yourself making muffled whining noises as you feel the [npc.cum+] sliding down into your stomach.");
						} else {
							cumTargetSB.append(" deep down [npc.name]'s throat.");
						}
						switch (characterOrgasming.getPenisCumProduction()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								if(characterOrgasming.isPlayer()) {
									cumTargetSB.append(" After a few seconds, [npc.name] realises that you're not even close to stopping, and as your "
											+"[pc.cum+] backs up and starts drooling out the corners of [npc.her] mouth, [npc.she] lets out a desperate, muffled moan."
													+ " It takes a while before you finally feel your balls running dry, by which time [npc.name]'s stomach has quite visibly distended."
													+ " As you stop, [npc.she] finally gets a moment to draw in a deep, desperate breath, before looking down and groaning as [npc.she] feels the massive amount of "
																+"[pc.cum+] that you've deposited directly into [npc.her] stomach.");
								}
								break;
							default:
								break;
						}
						break;
					case NIPPLE:
						if(target.isPlayer()) {
							cumTargetSB.append(" deep into your [pc.breasts+], and you find yourself whining and moaning as you feel the [npc.cum+] deep inside of your [pc.breasts+].");
						} else {
							cumTargetSB.append(" deep into [npc.name]'s [npc.breasts+].");
						}
						break;
					case THIGHS:
						break;
					case URETHRA:
						break;
					case VAGINA:
						if(target.isPlayer()) {
							if(!target.isVisiblyPregnant()) {
								descriptionSB.append(" deep into your waiting womb, and you find yourself whining and moaning as you wonder if the [npc.cum+] will get you pregnant.");
							} else {
								descriptionSB.append(" deep into your hungry [pc.pussy], and you find yourself whining and moaning as you feel the [npc.cum+] deep inside of you.");
							}
						} else {
							if(!target.isVisiblyPregnant()) {
								cumTargetSB.append(" deep into [npc.name]'s waiting womb.");
							} else {
								cumTargetSB.append(" deep into [npc.name]'s [npc.pussy+].");
							}
						}
						switch (characterOrgasming.getPenisCumProduction()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								if(characterOrgasming.isPlayer()) {
									cumTargetSB.append(" After a few seconds, [npc.name] realises that you're not even close to stopping, and as your [pc.cum+] backs up and starts drooling out of [npc.her] [npc.pussy],"
												+ " [npc.she] squirms about and lets out a series of [npc.moans+]."
													+ " It takes a while before you finally feel your balls running dry, by which time "
														+(target.isVisiblyPregnant()
															?"your [pc.cum+] has formed a huge, slick puddle on the floor, and you smirk as you see that you've totally saturated [npc.name]'s [npc.pussy+] with your [pc.cum+]."
															:"[npc.name]'s stomach has quite visibly distended, and [npc.she] lets out a final, satisfied moan as [npc.she] feels the massive amount of [pc.cum+]"
																	+ " that you've deposited deep in [npc.her] womb."));
								}
								break;
							default:
								break;
						}
						break;
				}
				
			}
		}
		
		if(target!=null) {
			return UtilText.parse(characterOrgasming, target, cumTargetSB.toString());
		} else {
			return UtilText.parse(characterOrgasming, cumTargetSB.toString());
		}
	}
	
	private static String getGenericVaginaOrgasmDescription(GameCharacter characterOrgasming) {
		genericOrgasmSB.setLength(0);
		
		if(characterOrgasming.isPlayer()) {
			genericOrgasmSB.append("A desperate, shuddering heat suddenly crashes up from your [npc1.pussy+], and you let out a manic squeal as a blinding wave of pure ecstasy washes over you.");
		} else {
			genericOrgasmSB.append("A desperate, shuddering heat suddenly crashes up from [npc1.name]'s [npc1.pussy+], and [npc1.she] lets out a manic squeal as a blinding wave of pure ecstasy washes over [npc1.herHim].");
		}
		
		GameCharacter characterPenetrating = Sex.getPenetratingCharacterUsingOrifice(characterOrgasming, OrificeType.VAGINA);
		PenetrationType penetration = Sex.getPenetrationTypeInOrifice(characterOrgasming, OrificeType.VAGINA);
		
		if(characterPenetrating!=null) {
			switch(penetration) {
				case FINGER:
					if(characterOrgasming.isPlayer()) {
						if(characterPenetrating.isPlayer()) {
							genericOrgasmSB.append(" You curl your fingers up deep inside your [npc1.pussy+], and, while desperately stroking in a 'come-hither' motion,"
									+ " you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around your intruding digits.");
						} else {
							genericOrgasmSB.append(" [npc2.Name]'s fingers carry on pumping away at your [npc1.pussy+], and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around the intruding digits.");
						}
					} else {
						if(characterPenetrating.isPlayer()) {
							genericOrgasmSB.append(" [npc1.Name]'s vaginal muscles grip and squeeze around your intruding digits,"
									+ " and you continue to stroke and tease [npc1.her] clit, drawing out a series of [npc1.moans+] from between [npc1.her] [npc1.lips+].");
						} else {
							genericOrgasmSB.append(" [npc1.Name]'s vaginal muscles grip and squeeze around [npc2.name]'s intruding digits,"
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
							genericOrgasmSB.append(" [npc2.Name] carries on fucking [npc1.name]'s [npc1.pussy+] through [npc1.her] orgasm,"
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
							genericOrgasmSB.append(" [npc2.Name] carries on tail-fucking [npc1.name]'s [npc1.pussy+] through [npc1.her] orgasm,"
											+ " causing [npc1.herHim] to let out a series of high-pitched moans as [npc1.her] vaginal muscles grip and squeeze around the intruding object.");
						}
					}
					break;
				case TONGUE:
					if(characterOrgasming.isPlayer()) {
						if(characterPenetrating.isPlayer()) {
							genericOrgasmSB.append(" You carry on licking and kissing your clit as you orgasm, letting out a series of high-pitched moans as you feel your vaginal muscles quiver and contract at the overwhelming sensation.");
						} else {
							genericOrgasmSB.append(" [npc1.Name] carries on licking and kissing at your clit while you orgasm,"
										+ " causing you to let out a series of high-pitched moans as you feel your vaginal muscles quiver and contract at the overwhelming sensation.");
						}
					} else {
						if(characterPenetrating.isPlayer()) {
							genericOrgasmSB.append(" You carry on licking and kissing [npc1.name]'s clit as [npc1.she] orgasms,"
										+ " causing [npc1.herHim] to let out a series of high-pitched moans as [npc1.her] vaginal muscles quiver and contract at the overwhelming sensation.");
						} else {
							genericOrgasmSB.append(" [npc2.Name] carries on licking and kissing at [npc1.name]'s clit while [npc1.she] orgasms,"
										+ " causing [npc1.herHim] to let out a series of high-pitched moans as [npc1.her] vaginal muscles quiver and contract at the overwhelming sensation.");
						}
					}
					break;
				case TENTACLE:
					break;
			}
			
		} else { // No penetration:
			if(characterOrgasming.isPlayer()) {
				genericOrgasmSB.append(" Your [npc1.pussy+] clenches down hard, and the wave of disappointment upon finding itself empty almost overwhelms the pleasure that radiates up through your groin.");
			} else {
				genericOrgasmSB.append(" [npc1.Name]'s [npc1.pussy+] clenches down hard, and the wave of disappointment upon finding itself empty almost overwhelms the pleasure that radiates up through [npc.her] groin.");
			}
		}
		
		if(characterOrgasming.isPlayer()) {	
			genericOrgasmSB.append(" With a deeply-satisfied sigh, your feminine climax starts to fade, and you take a few deep gasps of air as you seek to catch your breath.");
		} else {
			genericOrgasmSB.append(" With a deeply-satisfied sigh, [npc1.name]'s feminine climax starts to fade, and [npc1.she] takes a few deep gasps of air as [npc1.she] seeks to catch [npc1.her] breath.");
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
							+ " The muscles within [npc.her] genderless mound start to spasm and contract, and [npc.she]'s soon left as a panting, moaning wreck as [npc.her] intense pseudo-orgasm crashes over [npc.herHim].");
		}
		
	}
	
	private static String getGenericOrgasmDescription(GameCharacter characterOrgasming, OrgasmCumTarget target) {
		descriptionSB.setLength(0);

		if(characterOrgasming.hasPenis()) {
			descriptionSB.append("<p>"
									+getGenericPenisOrgasmDescription(characterOrgasming, target)
								+"</p>");
		}
		
		if(characterOrgasming.hasVagina()) {
			descriptionSB.append("<p>"
									+getGenericVaginaOrgasmDescription(characterOrgasming)
								+"</p>");
		}
		
		if(!characterOrgasming.hasPenis() && !characterOrgasming.hasVagina()) {
			descriptionSB.append("<p>"
									+getGenericMoundOrgasm(characterOrgasming)
								+"</p>");
		}
		
		return descriptionSB.toString();
	}
	
	// Doesn't have penis (or penis is not exposed), and isn't being vaginally penetrated:
	public static final SexAction PLAYER_GENERIC_ORGASM = new SexAction(
			SexActionType.PLAYER_ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			null,
			SexParticipantType.MISC) {
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
			return (!Main.game.getPlayer().hasPenis() || !Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS) || Main.game.getPlayer().isWearingCondom());
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().get(0));
		}
		
		@Override
		public void applyEffects() {
			if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS) && !Main.game.getPlayer().isWearingCondom()) {
				Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(true);
			}
		}
	};
	
	public static final SexAction PLAYER_GENERIC_ORGASM_CREAMPIE = new SexAction(
			SexActionType.PLAYER_ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			null,
			SexParticipantType.MISC) {
		@Override
		public String getActionTitle() {
			GameCharacter characterPenetrated = Sex.getCharactersBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS).get(0);
			OrificeType orificePenetrated = Sex.getOrificesBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS, characterPenetrated).get(0);
			
			switch(orificePenetrated) {
				case ANUS:
					if(Main.game.getPlayer().hasPenisModifier(PenisModifier.KNOTTED)) {
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
				case URETHRA:
					break;
				case VAGINA:
					if(Main.game.getPlayer().hasPenisModifier(PenisModifier.KNOTTED)) {
						return UtilText.parse(characterPenetrated, "Knot [npc.herHim]");
					} else {
						return "Creampie";
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
			if(Sex.getCharactersBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS).isEmpty()) {
				return false;
			}
			GameCharacter characterPenetrated = Sex.getCharactersBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS).get(0);
			OrificeType orificePenetrated = Sex.getOrificesBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS, characterPenetrated).get(0);
			
			switch(orificePenetrated) {
				case ANUS:
					return true;
				case ASS:
					break;
				case BREAST:
					break;
				case MOUTH:
					return true;
				case NIPPLE:
					return true;
				case THIGHS:
					break;
				case URETHRA:
					break;
				case VAGINA:
					return true;
				
			}

			return false;
			
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.INSIDE);
		}
		
		@Override
		public List<OrificeType> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			GameCharacter characterPenetrated = Sex.getCharactersBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS).get(0);
			OrificeType orificePenetrated = Sex.getOrificesBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS, characterPenetrated).get(0);
			
			if(cumProvider.isPlayer() && cumTarget.equals(characterPenetrated)) {
				return Util.newArrayListOfValues(new ListValue<>(orificePenetrated));
					
			} else {
				return null;
			}
		}
	};
	
	public static final SexAction PLAYER_GENERIC_ORGASM_FLOOR = new SexAction(
			SexActionType.PLAYER_ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			null,
			SexParticipantType.MISC) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenis()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.FLOOR);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS).isEmpty()) {
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
	};
	
	
	public static final SexAction PLAYER_GENERIC_ORGASM_WALL = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenis()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.WALL);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS).isEmpty()) {
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
	};
	
	public static final SexAction PLAYER_GENERIC_ORGASM_ASS = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenis()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.ASS);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS).isEmpty()) {
				return "Pull out (ass)";
			}
			return "Cum on ass";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc.name]'s [npc.ass+].";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.ASS);
		}
		
		@Override
		public void applyEffects() {
			if (Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.ASS)!=null && !Main.game.getPlayer().isWearingCondom()) {
				Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.ASS).setDirty(true);
			} else {
				Sex.getTargetedPartner(Main.game.getPlayer()).addDirtySlot(InventorySlot.GROIN); //TODO
			}
		}
	};
	
	public static final SexAction PLAYER_GENERIC_ORGASM_GROIN = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenis()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.GROIN);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS).isEmpty()) {
				return "Pull out (groin)";
			}
			return "Cum on groin";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc.name]'s groin.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.GROIN);
		}
		
		@Override
		public void applyEffects() {
			if (Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.PENIS)!=null && !Main.game.getPlayer().isWearingCondom()) {
				Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.PENIS).setDirty(true);
			} else {
				Sex.getTargetedPartner(Main.game.getPlayer()).addDirtySlot(InventorySlot.GROIN);
			}
		}
	};
	
	public static final SexAction PLAYER_GENERIC_ORGASM_BREASTS = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenis()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.BREASTS);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS).isEmpty()) {
				return "Pull out (chest)";
			}
			return "Cum on chest";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc.name]'s chest.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.BREASTS);
		}
		
		@Override
		public void applyEffects() {
			if (Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.BREASTS)!=null && !Main.game.getPlayer().isWearingCondom()) {
				Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.BREASTS).setDirty(true);
			} else {
				Sex.getTargetedPartner(Main.game.getPlayer()).addDirtySlot(InventorySlot.CHEST);
			}
		}
	};
	
	public static final SexAction PLAYER_GENERIC_ORGASM_FACE = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenis()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.FACE);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS).isEmpty()) {
				return "Pull out (facial)";
			}
			return "Facial";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc.name]'s face.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.FACE);
		}
		
		@Override
		public void applyEffects() {
			if (Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.MOUTH)!=null && !Main.game.getPlayer().isWearingCondom()) {
				Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.MOUTH).setDirty(true);
			} else {
				Sex.getTargetedPartner(Main.game.getPlayer()).addDirtySlot(InventorySlot.MOUTH);
			}
		}
	};
	
	public static final SexAction PLAYER_GENERIC_ORGASM_HAIR = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenis()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.HAIR);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS).isEmpty()) {
				return "Pull out (hair)";
			}
			return "Cum in [npc.hair]";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum into [npc.name]'s [npc.hair].";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.HAIR);
		}
		
		@Override
		public void applyEffects() {
			if (Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.HAIR)!=null && !Main.game.getPlayer().isWearingCondom()) {
				Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.HAIR).setDirty(true);
			} else {
				Sex.getTargetedPartner(Main.game.getPlayer()).addDirtySlot(InventorySlot.HAIR);
			}
		}
	};
	
	public static final SexAction PLAYER_GENERIC_ORGASM_STOMACH = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenis()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.STOMACH);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS).isEmpty()) {
				return "Pull out (stomach)";
			}
			return "Cum on stomach";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc.name]'s stomach.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.STOMACH);
		}

		@Override
		public void applyEffects() {
			if (Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.STOMACH)!=null && !Main.game.getPlayer().isWearingCondom()) {
				Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.STOMACH).setDirty(true);
			} else {
				Sex.getTargetedPartner(Main.game.getPlayer()).addDirtySlot(InventorySlot.STOMACH);
			}
		}
	};
	
	public static final SexAction PLAYER_GENERIC_ORGASM_LEGS = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenis()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.LEGS);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS).isEmpty()) {
				return "Pull out (legs)";
			}
			return "Cum on [npc.legs]";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc.name]'s [npc.legs].";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.LEGS);
		}

		@Override
		public void applyEffects() {
			if (Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.LEGS)!=null && !Main.game.getPlayer().isWearingCondom()) {
				Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.LEGS).setDirty(true);
			} else {
				Sex.getTargetedPartner(Main.game.getPlayer()).addDirtySlot(InventorySlot.LEG);
			}
		}
	};
	
	public static final SexAction PLAYER_GENERIC_ORGASM_BACK = new SexAction(PLAYER_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenis()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.BACK);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS).isEmpty()) {
				return "Pull out (back)";
			}
			return "Cum on back";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc.name]'s back.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.BACK);
		}

		@Override
		public void applyEffects() {
			if (Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.BACK)!=null && !Main.game.getPlayer().isWearingCondom()) {
				Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.BACK).setDirty(true);
			} else {
				Sex.getTargetedPartner(Main.game.getPlayer()).addDirtySlot(InventorySlot.TORSO_UNDER);
			}
		}
	};
	
	
	
	// PLAYER:
	
	public static final SexAction PLAYER_PREPARE = new SexAction(
			SexActionType.PLAYER_PREPARE_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			null,
			SexParticipantType.MISC) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !isTakingCock(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Prepare";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [npc.name] is fast approaching [npc.her] orgasm. Prepare yourself for it.";
		}
		
		@Override
		public String getDescription() {
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					return "You let out a soft [pc.moan] of encouragement as you prepare for [npc.name] to reach [npc.her] orgasm.";
				case DOM_NORMAL:
					return "You let out [pc.a_moan+] of encouragement as you prepare for [npc.name] to reach [npc.her] orgasm.";
				case DOM_ROUGH:
					return "You let out [pc.a_moan+] of encouragement as you prepare for [npc.name] to reach [npc.her] orgasm.";
				case SUB_EAGER:
					return "You let out [pc.a_moan+] of encouragement as you prepare for [npc.name] to reach [npc.her] orgasm.";
				case SUB_NORMAL:
					return "You let out [pc.a_moan] of encouragement as you prepare for [npc.name] to reach [npc.her] orgasm.";
				case SUB_RESISTING:
					return "You let out [pc.a_moan+] as you desperately try to pull away from [npc.name] before [npc.she] orgasms.";
			}
			
			return "";
		}
	};
	
	public static final SexAction PLAYER_ASK_FOR_CREAMPIE = new SexAction(
			SexActionType.PLAYER_PREPARE_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			null,
			SexParticipantType.MISC) {
		@Override
		public String getActionTitle() {
			if((Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.MOUTH) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.MOUTH).isPlayer())
					|| (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.BREAST) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.BREAST).isPlayer())) {
				return "Request cum";
			} else {
				return "Request creampie";
			}
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [npc.name] is fast approaching [npc.her] orgasm. Ask [npc.herHim] to fill you with [npc.her] cum.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return isTakingCock(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			if(Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.VAGINA) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.VAGINA).isPlayer()) {
				return "Through your desperate moans and lewd cries, you somehow manage to formulate a sentence as you cry out for [npc.name]'s cum, "
						+(Main.game.getPlayer().isVisiblyPregnant()
								?"[pc.speech(Fuck! Cum in me! I need your cum!)]"
								:"[pc.speech(Breed me! Cum in me! I need your cum!)]");
				
			} else if(Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.ANUS) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.ANUS).isPlayer()) {
				return "Through your desperate moans and lewd cries, you somehow manage to formulate a sentence as you cry out for [npc.name]'s cum,"
						+ " [pc.speech(Fuck! Cum in me! I need your cum!)]";
				
			} else if(Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.NIPPLE) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.NIPPLE).isPlayer()) {
				return "Through your desperate moans and lewd cries, you somehow manage to formulate a sentence as you cry out for [npc.name]'s cum, "
						+ " [pc.speech(Fuck! Cum in me! I need your cum!)]";
				
			} else if(Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.BREAST) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.BREAST).isPlayer()) {
				return "Through your desperate moans and lewd cries, you somehow manage to formulate a sentence as you cry out for [npc.name]'s cum, "
						+ " [pc.speech(Yes! Cum for me! Cover my tits with your cum!)]";
				
			} else {
				return "Through your desperate moans and lewd cries, you somehow manage to formulate a sentence as you cry out for [npc.name]'s cum, "
						+ " [pc.speech(Cum for me! I want to taste your cum!)]";
			}
		}

		@Override
		public void applyEffects() {
			SexFlags.playerRequestedCreampie = true;
			SexFlags.playerRequestedPullOut = false;
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.VAGINA) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.VAGINA).isPlayer()) {
					return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_ADDICT), new ListValue<>(Fetish.FETISH_PREGNANCY));
					
				} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.ANUS) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.ANUS).isPlayer()) {
					return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_ADDICT), new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
					
				} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.NIPPLE) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.NIPPLE).isPlayer()) {
					return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_ADDICT), new ListValue<>(Fetish.FETISH_BREASTS_SELF));
					
				} else {
					return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_ADDICT));
				}
				
			} else {
				if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.VAGINA) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.VAGINA).isPlayer()) {
					return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_STUD), new ListValue<>(Fetish.FETISH_IMPREGNATION));
					
				} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.ANUS) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.ANUS).isPlayer()) {
					return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_STUD), new ListValue<>(Fetish.FETISH_ANAL_GIVING));
					
				} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.NIPPLE) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.NIPPLE).isPlayer()) {
					return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_STUD), new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
					
				} else {
					return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_STUD));
				}
			}
		}
	};
	
	public static final SexAction PLAYER_ASK_FOR_PULL_OUT = new SexAction(
			SexActionType.PLAYER_PREPARE_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			null,
			SexParticipantType.MISC) {
		
		@Override
		public String getActionTitle() {
			return "Request pullout";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [npc.name] is fast approaching [npc.her] orgasm. Ask [npc.herHim] to pull out before [npc.she] cums.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return isTakingCock(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.VAGINA) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.VAGINA).isPlayer()) {
				return "Through your desperate moans and lewd cries, you somehow manage to formulate a sentence as you cry out to [npc.name], "
						+(Main.game.getPlayer().isVisiblyPregnant()
								?"[pc.speech(Pull out! I don't want you to cum in me!)]"
								:"[pc.speech(Pull out! I don't want to get pregnant!)]");
				
			} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.ANUS) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.ANUS).isPlayer()) {
				return "Through your desperate moans and lewd cries, you somehow manage to formulate a sentence as you cry out to [npc.name], "
						+ "[pc.speech(Pull out! I don't want you to cum in me!)]";
				
			} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.NIPPLE) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.NIPPLE).isPlayer()) {
				return "Through your desperate moans and lewd cries, you somehow manage to formulate a sentence as you cry out to [npc.name], "
						+ "[pc.speech(Pull out! I don't want you to cum in me!)]";
				
			} else {
				return "Through your desperate moans and lewd cries, you somehow manage to cry out around [npc.name]'s [npc.cock], "
						+ "[pc.speech(Pull out! I don't want to taste your cum!)]";
			}
		}

		@Override
		public void applyEffects() {
			SexFlags.playerRequestedCreampie = false;
			SexFlags.playerRequestedPullOut = true;
		}
	};
	
	// PARTNER
	
	public static final SexAction PARTNER_PREPARE = new SexAction(
			SexActionType.PARTNER_PREPARE_PLAYER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			null,
			SexParticipantType.MISC) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !isTakingCock(Sex.getActivePartner());
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
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					return "[npc.Name] lets out a soft [npc.moan] of encouragement as [npc.she] prepares for you to reach your orgasm.";
				case DOM_NORMAL:
					return "[npc.Name] lets out [npc.a_moan+] of encouragement as [npc.she] prepares for you to reach your orgasm.";
				case DOM_ROUGH:
					return "[npc.Name] lets out [npc.a_moan+] of encouragement as [npc.she] prepares for you to reach your orgasm.";
				case SUB_EAGER:
					return "[npc.Name] lets out [npc.a_moan+] of encouragement as [npc.she] prepares for you to reach your orgasm.";
				case SUB_NORMAL:
					return "[npc.Name] lets out [npc.a_moan+] of encouragement as [npc.she] prepares for you to reach your orgasm.";
				case SUB_RESISTING:
					return "[npc.Name] lets out [npc.a_moan+] as [npc.she] desperately tries to pull away from you before you orgasm.";
			}
			
			return "";
		}
	};
	
	public static final SexAction PARTNER_ASK_FOR_CREAMPIE = new SexAction(
			SexActionType.PARTNER_PREPARE_PLAYER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			null,
			SexParticipantType.MISC) {
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isTakingCock(Sex.getActivePartner()) && Sex.getSexPace(Sex.getActivePartner())!=SexPace.SUB_RESISTING;
		}

		@Override
		public SexActionPriority getPriority() {
			if(Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.VAGINA)==PenetrationType.PENIS
					&& Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.VAGINA).isPlayer()
					&& Sex.getActivePartner().hasFetish(Fetish.FETISH_PREGNANCY)) {
				return SexActionPriority.NORMAL;
			} else {
				return SexActionPriority.LOW;
			}
		}

		@Override
		public String getDescription() {
			if (Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.VAGINA) == PenetrationType.PENIS && Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.VAGINA).isPlayer()) {
				return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out for your cum, "
						+(Sex.getActivePartner().isVisiblyPregnant()
								?"[npc.speech(Fuck! Cum in me! I need your cum!)]"
								:"[npc.speech(Breed me! Cum in me! I need your cum!)]");
				
			} else if (Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.ANUS) == PenetrationType.PENIS && Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.ANUS).isPlayer()) {
				return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out for your cum,"
						+ " [npc.speech(Fuck! Cum in me! I need your cum!)]";
				
			} else if (Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.NIPPLE) == PenetrationType.PENIS && Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.NIPPLE).isPlayer()) {
				return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out for your cum, "
						+ " [npc.speech(Fuck! Cum in me! I need your cum!)]";
				
			} else {
				return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out for your cum, "
						+ " [npc.speech(Cum for me! I want to taste your cum!)]";
			}
		}

		@Override
		public void applyEffects() {
			SexFlags.partnerRequestedCreampie = true;
			SexFlags.partnerRequestedPullOut = false;
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				if (Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.VAGINA) == PenetrationType.PENIS && Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.VAGINA).isPlayer()) {
					return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_STUD), new ListValue<>(Fetish.FETISH_IMPREGNATION));
					
				} else if (Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.ANUS) == PenetrationType.PENIS && Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.ANUS).isPlayer()) {
					return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_STUD), new ListValue<>(Fetish.FETISH_ANAL_GIVING));
					
				} else if (Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.NIPPLE) == PenetrationType.PENIS && Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.NIPPLE).isPlayer()) {
					return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_STUD), new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
					
				} else {
					return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_STUD));
				}
				
			} else {
				if (Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.VAGINA) == PenetrationType.PENIS && Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.VAGINA).isPlayer()) {
					return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_ADDICT), new ListValue<>(Fetish.FETISH_PREGNANCY));
					
				} else if (Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.ANUS) == PenetrationType.PENIS && Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.ANUS).isPlayer()) {
					return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_ADDICT), new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
					
				} else if (Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.NIPPLE) == PenetrationType.PENIS && Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.NIPPLE).isPlayer()) {
					return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_ADDICT), new ListValue<>(Fetish.FETISH_BREASTS_SELF));
					
				} else {
					return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_ADDICT));
				}
			}
		}
	};
	
	public static final SexAction PARTNER_ASK_FOR_PULL_OUT = new SexAction(
			SexActionType.PARTNER_PREPARE_PLAYER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			null,
			SexParticipantType.MISC) {
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return isTakingCock(Sex.getActivePartner())
					&& (Sex.getSexPace(Sex.getActivePartner())==SexPace.SUB_RESISTING
						|| ((Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.MOUTH)==PenetrationType.PENIS
							?!Sex.getActivePartner().hasFetish(Fetish.FETISH_CUM_ADDICT)
							:true)
						&& (Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.VAGINA)==PenetrationType.PENIS
							?(!Sex.getActivePartner().hasFetish(Fetish.FETISH_PREGNANCY) && !Sex.getActivePartner().hasFetish(Fetish.FETISH_BROODMOTHER))
							:true)));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.LOW;
		}
		
		@Override
		public String getDescription() {
			if (Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.VAGINA) == PenetrationType.PENIS && Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.VAGINA).isPlayer()) {
				return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out to you, "
						+(Sex.getActivePartner().isVisiblyPregnant()
								?"[npc.speech(Pull out! I don't want you to cum in me!)]"
								:"[npc.speech(Pull out! I don't want to get pregnant!)]");
				
			} else if (Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.ANUS) == PenetrationType.PENIS && Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.ANUS).isPlayer()) {
				return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out to you, "
						+ "[npc.speech(Pull out! I don't want you to cum in me!)]";
				
			} else if (Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.NIPPLE) == PenetrationType.PENIS && Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.NIPPLE).isPlayer()) {
				return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out to you, "
						+ "[npc.speech(Pull out! I don't want you to cum in me!)]";
				
			} else {
				return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out to you, "
						+ "[npc.speech(Pull out! I don't want to taste your cum!)]";
			}
		}

		@Override
		public void applyEffects() {
			SexFlags.partnerRequestedCreampie = false;
			SexFlags.partnerRequestedPullOut = true;
		}
	};
	
	public static final SexAction PARTNER_GENERIC_ORGASM_CUM_INSIDE = new SexAction(
			SexActionType.PARTNER_ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			null,
			SexParticipantType.MISC) {
		@Override
		public String getActionTitle() {
			return "Stay in position";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer.";
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.LOW; // Prefer to use scene-specific orgasms
		}

		@Override
		public boolean isBaseRequirementsMet() {
			if(Sex.getActivePartner() instanceof Cultist) { //TODO 
				return true;
			}
			
			boolean partnerListensToRequest = Sex.isDom(Main.game.getPlayer()) || Sex.isSubHasEqualControl();
			
			return !Sex.isAnyPenetrationHappening() || (partnerListensToRequest?(!SexFlags.playerRequestedPullOut):true);
		}

		@Override
		public String getDescription() {
			descriptionSB = new StringBuilder();
			
			descriptionSB.append("[npc.Name] lets out a lewd cry as [npc.she] reaches [npc.her] orgasm.");
			
			// PENIS ORGASM:
			
			if(Sex.getActivePartner().getPenisType()!=PenisType.NONE){

				descriptionSB.append("</br></br>");
				
				// Describe where penetration is occurring:
				if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.VAGINA) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.VAGINA).isPlayer()) {
					switch(Sex.getActivePartner().getPenisType()){
						case CANINE:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming the knot at the base of [npc.her] "
									+"[npc.cock+] into your [pc.pussy+]."
									+ " Your eyes go wide as you feel it swell up, and as you find yourself locked in position, you let out a shuddering moan.");
							break;
						case EQUINE:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming the wide, flared head of [npc.her] "
									+"[npc.penis] deep into your [pc.pussy+]."
									+ " Your eyes go wide as you feel it lewdly spreading your inner walls out, and as the base bumps against your pussy lips, you let out a shuddering moan.");
							break;
						case FELINE:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming [npc.her] "
									+"[npc.penis] deep into your [pc.pussy+]."
									+ " As the base bumps against your pussy lips, [npc.she] starts gently bucking back and forth, causing you to let out a series of pleasurable screams as [npc.her] barbs start ruthlessly raking your vaginal walls.");
							break;
						default:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming [npc.her] "
									+"[npc.penis] deep into your [pc.pussy+]."
									+ " Your eyes go wide as you feel the base bump against your pussy lips, and you can't help but let out a shuddering moan as you find yourself filled with cock.");
							break;
					}
					
				} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.ANUS) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.ANUS).isPlayer()) {
					switch(Sex.getActivePartner().getPenisType()){
						case CANINE:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming the knot at the base of [npc.her] "
									+"[npc.cock+] into your [pc.asshole+]."
									+ " Your eyes go wide as you feel it swell up, and as you find yourself locked in position, you let out a shuddering moan.");
							break;
						case EQUINE:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming the wide, flared head of [npc.her] "
									+"[npc.penis] deep into your [pc.asshole+]."
									+ " Your eyes go wide as you feel it lewdly spreading you out, and as the base bumps against your [pc.ass+], you let out a shuddering moan.");
							break;
						case FELINE:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming [npc.her] "
									+"[npc.penis] deep into your [pc.asshole+]."
									+ " As the base bumps against your [pc.ass], [npc.she] starts gently bucking back and forth,"
										+ " causing you to let out a series of pleasurable screams as [npc.her] barbs start ruthlessly raking the insides of your [pc.ass].");
							break;
						default:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming [npc.her] "
									+"[npc.penis] deep into your [pc.asshole+]."
									+ " Your eyes go wide as you feel the base bump against your [pc.ass+], and you can't help but let out a shuddering moan as you find yourself filled with cock.");
							break;
					}
					
				} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.MOUTH) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.MOUTH).isPlayer()) {
					switch(Sex.getActivePartner().getPenisType()){
						case CANINE:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming the knot at the base of [npc.her] "
									+"[npc.cock+] past your lips and into your mouth."
									+ " Your eyes go wide as you feel it swell up, and as you find yourself locked in position, you let out a shuddering moan.");
							break;
						case EQUINE:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming the wide, flared head of [npc.her] "
									+"[npc.penis] down over your tongue."
									+ " Your eyes go wide as you feel it lewdly spreading your throat out, and as the base bumps against your lips, you let out a muffled moan.");
							break;
						case FELINE:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming [npc.her] "
									+"[npc.penis] deep into your throat."
									+ " As the base bumps against your lips, [npc.she] starts gently bucking back and forth, causing you to let out a series of muffled cries as [npc.her] barbs"
									+ " start ruthlessly raking the insides of your throat.");
							break;
						default:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming [npc.her] "
									+"[npc.penis] deep down your throat."
									+ " Your eyes go wide as you feel the base bump against your lips, and you can't help but let out a muffled moan as you find yourself filled with cock.");
							break;
					}
					
				} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.NIPPLE) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.NIPPLE).isPlayer()) {
					switch(Sex.getActivePartner().getPenisType()){
						case CANINE:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming the knot at the base of [npc.her] "
									+"[npc.cock+] into your [pc.nipple+]."
									+ " Your eyes go wide as you feel it swell up, and as you find yourself locked in position, you let out a shuddering moan.");
							break;
						case EQUINE:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming the wide, flared head of [npc.her] "
									+"[npc.penis] deep into your [pc.nipple+]."
									+ " Your eyes go wide as you feel it lewdly spreading you out, and as the base bumps against your [pc.breasts+], you let out a shuddering moan.");
							break;
						case FELINE:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming [npc.her] [npc.penis] deep into your [pc.nipple+]."
									+ " As the base bumps against your [pc.breast+], [npc.she] starts gently bucking back and forth, causing you to let out a series of pleasurable screams"
										+ " as [npc.her] barbs start ruthlessly raking the insides of your [pc.breast].");
							break;
						default:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming [npc.her] "
									+"[npc.penis] deep into your [pc.nipple+]."
									+ " Your eyes go wide as you feel the base bump against your [pc.breasts+], and you can't help but let out a shuddering moan as you find yourself filled with cock.");
							break;
					}
					
				} else {
					switch(Sex.getActivePartner().getPenisType()){
						case CANINE:
							descriptionSB.append("[npc.Name] lets out a deep groan as the knot at the base of [npc.her] [npc.penis+] swells up as [npc.she] prepares to cum.");
							break;
						case EQUINE:
							descriptionSB.append("[npc.Name] lets out a deep groan as the wide head of [npc.her] [npc.penis+] swells up as [npc.she] prepares to cum.");
							break;
						case FELINE:
							descriptionSB.append("[npc.Name] lets out a deep groan as [npc.her] [npc.penis+] starts to twitch as [npc.she] prepares to cum.");
							break;
						default:
							descriptionSB.append("[npc.Name] lets out a deep groan as [npc.her] [npc.penis+] starts to twitch as [npc.she] prepares to cum.");
							break;
					}
				}
				
				// Describe cum amount:
				descriptionSB.append(" As [npc.her] "+Sex.getActivePartner().getTesticleSize().getDescriptor()+" balls tense up");
				switch (Sex.getActivePartner().getPenisCumProduction()) {
					case ZERO_NONE:
						descriptionSB.append(", you see that [npc.she]'s not able to produce even one drop of cum.");
						break;
					case ONE_TRICKLE:
						descriptionSB.append(", a small trickle of cum squirts");
						break;
					case TWO_SMALL_AMOUNT:
						descriptionSB.append(", a small amount of cum squirts");
						break;
					case THREE_AVERAGE:
						descriptionSB.append(", [npc.her] cum squirts");
						break;
					case FOUR_LARGE:
						descriptionSB.append(", [npc.her] cum shoots");
						break;
					case FIVE_HUGE:
						descriptionSB.append(", [npc.her] cum pours");
						break;
					case SIX_EXTREME:
						descriptionSB.append(", [npc.her] cum pours");
						break;
					case SEVEN_MONSTROUS:
						descriptionSB.append(", [npc.her] cum pours");
						break;
					default:
						break;
				}
				
				// Describe where cum is going:
				if(Sex.getActivePartner().getPenisCumProduction()!=CumProduction.ZERO_NONE){
					if(Sex.getActivePartner().isWearingCondom()) {
						descriptionSB.append("out into the condom that [npc.she]'s wearing.");
						
					} else if (!Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.PENIS)) {
						descriptionSB.append(" into [npc.her] [npc.lowClothing(penis)].");
						
					} else {
						if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.VAGINA) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.VAGINA).isPlayer()) {
							if(Main.game.getPlayer().isVisiblyPregnant())
								descriptionSB.append(" deep into your hungry [pc.pussy], and you find yourself whining and moaning as you feel the [npc.cum+] deep inside of you.");
							else
								descriptionSB.append(" deep into your waiting womb, and you find yourself whining and moaning as you wonder if the [npc.cum+] will get you pregnant.");
							
						} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.ANUS) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.ANUS).isPlayer()) {
							descriptionSB.append(" deep into your [pc.asshole+], and you find yourself whining and moaning as you feel the [npc.cum+] deep inside of you.");
							
						} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.MOUTH) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.MOUTH).isPlayer()) {
							descriptionSB.append(" deep down your throat, and you find yourself making muffled whining noises as you feel the [npc.cum+] sliding down into your stomach.");
							
						} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.NIPPLE) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.NIPPLE).isPlayer()) {
							descriptionSB.append(" deep into your [pc.breasts+], and you find yourself whining and moaning as you feel the [npc.cum+] deep inside of your [pc.breasts+].");
							
						} else {
							descriptionSB.append(" out all over the floor.");
						}
					}
				}
			}
			
			// VAGINA ORGASM:
			
			if (Sex.getActivePartner().getVaginaType()!=VaginaType.NONE) {

				descriptionSB.append("</br></br>");
				
				descriptionSB.append("[npc.She] suddenly lets out a manic squeal as [npc.her] orgasm starts rising up from [npc.her] [npc.pussy+].");
				
				if (Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.VAGINA) != null) {
					GameCharacter characterPenetrating = Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.VAGINA);
					switch(Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.VAGINA)) {
						case FINGER:
							if(characterPenetrating.isPlayer()) {
								descriptionSB.append(" You push your fingers deep inside [npc.her] [npc.pussy+]"
										+", and, while eagerly stroking in a 'come-hither' motion, you feel [npc.her] vaginal muscles grip and squeeze around your intruding digits as [npc.she] lets out a series of high-pitched moans.");
							} else {
								descriptionSB.append(" [npc.She] curls [npc.her] fingers up deep inside [npc.her] [npc.pussy+]"
										+", and [npc.she] lets out a series of high-pitched moans as [npc.her] vaginal muscles grip and squeeze around the intruding digits.");
							}
							break;
						case PENIS:
							if(characterPenetrating.isPlayer()) {
								descriptionSB.append(" You carry on fucking [npc.her] [npc.pussy+]"
										+" through [npc.her] orgasm, causing [npc.herHim] to let out a series of desperate, high-pitched moans as [npc.her] vaginal muscles grip and squeeze around your [pc.cock+].");
							} else {
								descriptionSB.append(" [npc.She] carries on fucking [npc.herHim]self through [npc.her] orgasm, and [npc.she] starts letting out a series of high-pitched moans as [npc.her] vaginal muscles grip and squeeze around [npc.her] "
										+"[npc.cock+].");
							}
							break;
						case TAIL:
							if(characterPenetrating.isPlayer()) {
								descriptionSB.append(" You carry on using your tail to fuck [npc.her] [npc.pussy+]"
										+" through [npc.her] orgasm, causing [npc.herHim] to let out a series of desperate, high-pitched moans as [npc.her] vaginal muscles grip and squeeze around the intruding object.");
							} else {
								descriptionSB.append(" [npc.She] carries on using [npc.her] tail to fuck [npc.herHim]self through [npc.her] orgasm,"
										+ " and [npc.she] starts letting out a series of high-pitched moans as [npc.her] vaginal muscles grip and squeeze around the intruding object.");
							}
							break;
						case TONGUE:
							if(characterPenetrating.isPlayer()) {
								descriptionSB.append(" You carry on eating [npc.herHim] out, licking and kissing at [npc.her] [npc.pussy+]"
										+" while [npc.she] orgasms, and you let out a muffled moan as you feel [npc.her] vaginal muscles grip and squeeze around your tongue.");
							} else {
								descriptionSB.append(" [npc.She] carries on thrusting [npc.her] tongue deep into [npc.her] [npc.pussy+]"
										+" while [npc.she] orgasms, causing [npc.herHim] to let out a series of desperate, high-pitched moans as [npc.her] vaginal muscles grip and squeeze around the intruding muscle.");
							}
							break;
						default:
							break;
					}
					
				} else { // No penetration:
					descriptionSB.append(" [npc.Her] [npc.pussy+]"
							+" clenches down hard, and although [npc.she] finds [npc.herHim]self empty, [npc.she] still lets out a desperate series of high-pitched moans as a deep pleasure radiates up through [npc.her] groin.");
				}

				descriptionSB.append(" With a final, ear-splitting scream of pure arousal, [npc.her] climax crashes over [npc.herHim], and after a few moments, leaves [npc.herHim] as a panting, thoroughly-satisfied wreck.");
			}
			
			// MOUND ORGASM:
			if (Sex.getActivePartner().getPenisType()==PenisType.NONE && Sex.getActivePartner().getVaginaType()==VaginaType.NONE) {
				
				descriptionSB.append("</br></br>");
				
				descriptionSB.append("With an ear-splitting scream and trembling legs, a crashing wave of pure ecstasy suddenly washes through [npc.herHim]."
						+ " The muscles within [npc.her] genderless mound start to spasm and contract, and [npc.she]'s soon left as a panting, moaning wreck as [npc.she] comes down from [npc.her] surprisingly intense climax.");
			}

			return UtilText.parse(Sex.getActivePartner(),
					descriptionSB.toString());
		}

		@Override
		public void applyEffects() {
		}
		
		@Override
		public List<OrificeType> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumTarget.isPlayer() && cumProvider.equals(Sex.getActivePartner())) {
				if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.VAGINA) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.VAGINA).isPlayer()) {
					return Util.newArrayListOfValues(new ListValue<>(OrificeType.VAGINA));
					
				} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.ANUS) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.ANUS).isPlayer()) {
					return Util.newArrayListOfValues(new ListValue<>(OrificeType.ANUS));
					
				} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.MOUTH) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.MOUTH).isPlayer()) {
					return Util.newArrayListOfValues(new ListValue<>(OrificeType.MOUTH));
					
				} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.NIPPLE) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.NIPPLE).isPlayer()) {
					return Util.newArrayListOfValues(new ListValue<>(OrificeType.NIPPLE));
					
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
		
		@Override
		public boolean endsSex() {
			return !Sex.isDom(Main.game.getPlayer()) && !Sex.isConsensual();
		}
	};
		
	public static final SexAction PARTNER_GENERIC_ORGASM_PULL_OUT = new SexAction(
			SexActionType.PARTNER_ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			null,
			SexParticipantType.MISC) {
		@Override
		public String getActionTitle() {
			return "Pull out";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer.";
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.LOW; // Prefer to use scene-specific orgasms
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean partnerListensToRequest = Sex.isDom(Main.game.getPlayer()) || Sex.isSubHasEqualControl();
			
			return Sex.isAnyPenetrationHappening() && (partnerListensToRequest?(!SexFlags.playerRequestedCreampie):true);
		}

		@Override
		public String getDescription() {
			descriptionSB = new StringBuilder();
			
			descriptionSB.append("[npc.Name] lets out a lewd cry as [npc.she] reaches [npc.her] orgasm.");
			
			if(Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.ANUS)!=null) {
				GameCharacter characterPenetrating = Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.ANUS);
				switch(Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.ANUS)){
					case FINGER:
						if(characterPenetrating.isPlayer()) {
							descriptionSB.append(" [npc.She] grabs your hand and pulls your fingers out of [npc.her] [npc.ass+].");
						} else {
							descriptionSB.append(" [npc.She] pulls [npc.her] fingers out of [npc.her] [npc.ass+].");
						}
						break;
					case PENIS:
						if(characterPenetrating.isPlayer()) {
							descriptionSB.append(" Grabbing your hips, [npc.she] pushes back, causing your [pc.cock+] to slip out of [npc.her] [npc.ass+].");
						} else {
							descriptionSB.append(" [npc.She] pulls [npc.her] [npc.penis+] out of [npc.her] [npc.ass+].");
						}
						break;
					case TAIL:
						if(characterPenetrating.isPlayer()) {
							descriptionSB.append(" Grabbing your tail, [npc.she] pulls it out of [npc.her] [npc.ass+].");
						} else {
							descriptionSB.append(" Grabbing [npc.her] tail, [npc.she] pulls it out of [npc.her] [npc.ass+].");
						}
						break;
					case TONGUE:
						if(characterPenetrating.isPlayer()) {
							descriptionSB.append(" Grabbing your head, [npc.she] pushes back, causing your tongue to slip out of [npc.her] [npc.ass+].");
						} else {
							descriptionSB.append(" [npc.She] slides [npc.her] tongue out of [npc.her] [npc.ass+].");
						}
						break;
					default:
						break;
				}
			}
			if(Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.MOUTH)!=null) {
				GameCharacter characterPenetrating = Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.MOUTH);
				switch(Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.MOUTH)){
					case FINGER:
						if(characterPenetrating.isPlayer()) {
							descriptionSB.append(" [npc.She] pulls your fingers out of [npc.her] mouth.");
						} else {
							descriptionSB.append(" [npc.She] pulls [npc.her] fingers out of [npc.her] mouth.");
						}
						break;
					case PENIS:
						if(characterPenetrating.isPlayer()) {
							descriptionSB.append(" Grabbing your hips, [npc.she] pushes back, causing your [pc.cock+] to slip out of [npc.her] mouth.");
						} else {
							descriptionSB.append(" [npc.She] pulls [npc.her] [npc.penis+] out of [npc.her] mouth.");
						}
						break;
					case TAIL:
						if(characterPenetrating.isPlayer()) {
							descriptionSB.append(" Grabbing your tail, [npc.she] pulls it out of [npc.her] mouth.");
						} else {
							descriptionSB.append(" Grabbing [npc.her] tail, [npc.she] pulls it out of [npc.her] mouth.");
						}
						break;
					case TONGUE:
						if(characterPenetrating.isPlayer()) {
							descriptionSB.append(" Grabbing your head, [npc.she] pushes back, causing your tongue to slip out of [npc.her] mouth.");
						} else {
							descriptionSB.append("");
						}
						break;
					default:
						break;
				}
			}
			
			descriptionSB.append("</br></br>");
			// PENIS ORGASM:
			
			if(Sex.getActivePartner().getPenisType()!=PenisType.NONE){
				
				// Describe where penetration is occurring:
				if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.VAGINA) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.VAGINA).isPlayer()) {
					switch(Sex.getActivePartner().getPenisType()){
						case CANINE:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your hips, and, just as [npc.her] knot starts to swell up, [npc.she] pulls back, sliding [npc.her] "
									+"[npc.cock+] out of your [pc.pussy+].");
							break;
						case EQUINE:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your hips, and, as [npc.she] pulls back, you feel the wide, flared head of [npc.her] "
									+"[npc.cock+] slide out of your [pc.pussy+].");
							break;
						case FELINE:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your hips, before pulling back and sliding [npc.her] "
									+"[npc.cock+] out of your [pc.pussy+]."
									+ " As [npc.she] withdraws [npc.her] spiny cock, you let out a desperate scream as you feel [npc.her] barbs roughly rake back along your vaginal walls.");
							break;
						default:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your hips, and, as [npc.she] pulls back, you feel [npc.her] "
									+"[npc.cock+] slide out of your [pc.pussy+].");
							break;
					}
					
				} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.ANUS) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.ANUS).isPlayer()) {
					switch(Sex.getActivePartner().getPenisType()){
						case CANINE:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your hips, and, just as [npc.her] knot starts to swell up, [npc.she] pulls back, sliding [npc.her] "
									+"[npc.cock+] out of your [pc.asshole+].");
							break;
						case EQUINE:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your hips, and, as [npc.she] pulls back, you feel the wide, flared head of [npc.her] "
									+"[npc.cock+] slide out of your [pc.asshole+].");
							break;
						case FELINE:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your hips, before pulling back and sliding [npc.her] "
									+"[npc.cock+] out of your [pc.asshole+]."
									+ " As [npc.she] withdraws [npc.her] spiny cock, you let out a desperate scream as you feel [npc.her] barbs roughly rake back along the inside of your [pc.ass+].");
							break;
						default:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your hips, and, as [npc.she] pulls back, you feel [npc.her] "
									+"[npc.cock+] slide out of your [pc.asshole+].");
							break;
					}
					
				} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.MOUTH) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.MOUTH).isPlayer()) {
					switch(Sex.getActivePartner().getPenisType()){
						case CANINE:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your head, and, just as [npc.her] knot starts to swell up, [npc.she] pulls back, sliding [npc.her] "
									+"[npc.cock+] out of your throat.");
							break;
						case EQUINE:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your head, and, as [npc.she] pulls back, you feel the wide, flared head of [npc.her] "
									+"[npc.cock+] slide out of your throat.");
							break;
						case FELINE:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your head, before pulling back and sliding [npc.her] "
									+"[npc.cock+] out of your throat."
									+ " As [npc.she] withdraws [npc.her] spiny cock, you let out a muffled squeal as you feel [npc.her] barbs roughly rake up along the sides of your throat.");
							break;
						default:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your head, and, as [npc.she] pulls back, you feel [npc.her] "
									+"[npc.cock+] slide out of your throat.");
							break;
					}
					
				} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.NIPPLE) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.NIPPLE).isPlayer()) {
					switch(Sex.getActivePartner().getPenisType()){
						case CANINE:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your [pc.breasts+], and, just as [npc.her] knot starts to swell up, [npc.she] pulls back, sliding [npc.her] "
									+"[npc.cock+] out of your [pc.nipple+].");
							break;
						case EQUINE:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your [pc.breasts+], and, as [npc.she] pulls back, you feel the wide, flared head of [npc.her] "
									+"[npc.cock+] slide out of your [pc.nipple+].");
							break;
						case FELINE:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your [pc.breasts+], before pulling back and sliding [npc.her] "
									+"[npc.cock+] out of your [pc.nipple+]."
									+ " As [npc.she] withdraws [npc.her] spiny cock, you let out a desperate scream as you feel [npc.her] barbs roughly rake back along the insides of your [pc.nipple+].");
							break;
						default:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your [pc.breasts+], and, as [npc.she] pulls back, you feel [npc.her] "
									+"[npc.cock+] slide out of your [pc.nipple+].");
							break;
					}
					
				} else {
					switch(Sex.getActivePartner().getPenisType()){
						case CANINE:
							descriptionSB.append("[npc.Name] lets out a deep groan as the knot at the base of [npc.her] [npc.penis+] swells up as [npc.she] prepares to cum.");
							break;
						case EQUINE:
							descriptionSB.append("[npc.Name] lets out a deep groan as the wide head of [npc.her] [npc.penis+] swells up as [npc.she] prepares to cum.");
							break;
						case FELINE:
							descriptionSB.append("[npc.Name] lets out a deep groan as [npc.her] [npc.penis+] starts to twitch as [npc.she] prepares to cum.");
							break;
						default:
							descriptionSB.append("[npc.Name] lets out a deep groan as [npc.her] [npc.penis+] starts to twitch as [npc.she] prepares to cum.");
							break;
					}
				}
				
				// Describe cum amount:
				descriptionSB.append(" As [npc.her] "+Sex.getActivePartner().getTesticleSize().getDescriptor()+" balls tense up");
				switch (Sex.getActivePartner().getPenisCumProduction()) {
					case ZERO_NONE:
						descriptionSB.append(", you see that [npc.she]'s not able to produce even one drop of cum.");
						break;
					case ONE_TRICKLE:
						descriptionSB.append(", a small trickle of [npc.cum+] squirts");
						break;
					case TWO_SMALL_AMOUNT:
						descriptionSB.append(", a small amount of [npc.cum+] squirts");
						break;
					case THREE_AVERAGE:
						descriptionSB.append(", [npc.her] [npc.cum+] squirts");
						break;
					case FOUR_LARGE:
						descriptionSB.append(", [npc.her] [npc.cum+] shoots");
						break;
					case FIVE_HUGE:
						descriptionSB.append(", [npc.her] [npc.cum+] pours");
						break;
					case SIX_EXTREME:
						descriptionSB.append(", [npc.her] [npc.cum+] pours");
						break;
					case SEVEN_MONSTROUS:
						descriptionSB.append(", [npc.her] [npc.cum+] pours");
						break;
					default:
						break;
				}
				
				// Describe where cum is going:
				if(Sex.getActivePartner().getPenisCumProduction()!=CumProduction.ZERO_NONE){
					if(Sex.getActivePartner().isWearingCondom()) {
						descriptionSB.append("out into the condom that [npc.she]'s wearing.");
						
					} else if (!Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.PENIS)) {
						descriptionSB.append(" into [npc.her] [npc.lowClothing(penis)].");
						
					} else {
						if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.VAGINA) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.VAGINA).isPlayer()) {
							descriptionSB.append(" out all over the entrance to your [pc.pussy+].");
							
						} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.ANUS) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.ANUS).isPlayer()) {
							descriptionSB.append(" out all over your [pc.asshole+].");
							
						} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.MOUTH) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.MOUTH).isPlayer()) {
							descriptionSB.append(" out all over your face, causing you to blink and flinch.");
							
						} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.NIPPLE) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.NIPPLE).isPlayer()) {
							descriptionSB.append(" out all over your [pc.breasts+].");
							
						} else {
							descriptionSB.append(" out all over the floor.");
						}
					}
				}
			}
			
			// VAGINA ORGASM:
			
			if (Sex.getActivePartner().getVaginaType()!=VaginaType.NONE) {
				
				if(Sex.getActivePartner().getPenisType()!=PenisType.NONE) {
					descriptionSB.append(" ");
				}
				
				descriptionSB.append("[npc.She] suddenly lets out a manic squeal as [npc.her] orgasm starts rising up from [npc.her] [npc.pussy+].");
				
				if (Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.VAGINA) != null) {
					GameCharacter characterPenetrating = Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.VAGINA);
					switch(Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.VAGINA)) {
						case FINGER:
							if(characterPenetrating.isPlayer()) {
								descriptionSB.append(" Grabbing your hand, [npc.she] pulls your fingers out of [npc.her] [npc.pussy+]"
										+", letting out a high-pitched moan as [npc.her] vaginal muscles grip and squeeze down on the sudden emptiness between [npc.her] legs.");
							} else {
								descriptionSB.append(" Pulling [npc.her] fingers out of [npc.her] [npc.pussy+]"
										+", [npc.she] lets out a high-pitched moan as [npc.her] vaginal muscles grip and squeeze down on the sudden emptiness between [npc.her] legs.");
							}
							break;
						case PENIS:
							if(characterPenetrating.isPlayer()) {
								descriptionSB.append(" Grabbing your hips, [npc.she] pulls back from your groin, letting your [pc.cock+] slide out of [npc.her] [npc.pussy+]"
										+", and letting out a high-pitched moan as [npc.her] vaginal muscles grip and squeeze down on the sudden emptiness between [npc.her] legs.");
							} else {
								descriptionSB.append(" Sliding [npc.her] [npc.penis+] out of [npc.her] [npc.pussy+]"
										+", [npc.she] lets out a high-pitched moan as [npc.her] vaginal muscles grip and squeeze down on the sudden emptiness between [npc.her] legs.");
							}
							break;
						case TAIL:
							if(characterPenetrating.isPlayer()) {
								descriptionSB.append(" Grabbing your tail, [npc.she] slides it out of [npc.her] [npc.pussy+]"
										+", letting out a high-pitched moan as [npc.her] vaginal muscles grip and squeeze down on the sudden emptiness between [npc.her] legs.");
							} else {
								descriptionSB.append(" Grabbing [npc.her] tail, [npc.she] slides it out of [npc.her] [npc.pussy+]"
										+", letting out a high-pitched moan as [npc.her] vaginal muscles grip and squeeze down on the sudden emptiness between [npc.her] legs.");
							}
							break;
						case TONGUE:
							if(characterPenetrating.isPlayer()) {
								descriptionSB.append(" Grabbing your head, [npc.she] pushes you back away from [npc.her] groin, causing your tongue to slip out from [npc.her] [npc.pussy+]."
										+ " [npc.She] then lets out a high-pitched moan as [npc.her] vaginal muscles grip and squeeze down on the sudden emptiness between [npc.her] legs.");
							} else {
								descriptionSB.append(" Sliding [npc.her] tongue out from [npc.her] [npc.pussy+]"
										+", [npc.she] lets out a high-pitched moan as [npc.her] vaginal muscles grip and squeeze down on the sudden emptiness between [npc.her] legs.");
							}
							break;
						default:
							break;
					}
					
				} else { // No penetration:
					descriptionSB.append(" [npc.Her] [npc.pussy+]"
							+" clenches down hard, and the wave of disappointment upon finding itself empty almost overwhelms the pleasure that radiates up through [npc.her] groin.");
				}
				
				descriptionSB.append(" With a final, ear-splitting scream of pure arousal, [npc.her] climax crashes over [npc.herHim], and after a few moments, leaves [npc.herHim] as a panting, thoroughly-satisfied wreck.");
			}
			
			// MOUND ORGASM:
			if (Sex.getActivePartner().getPenisType()==PenisType.NONE && Sex.getActivePartner().getVaginaType()==VaginaType.NONE) {
				descriptionSB.append("With an ear-splitting scream and trembling legs, a crashing wave of pure ecstasy suddenly washes through [npc.herHim]."
						+ " The muscles within [npc.her] genderless mound start to spasm and contract, and [npc.she]'s soon left as a panting, moaning wreck as [npc.she] comes down from [npc.her] surprisingly intense climax.");
			}

			return UtilText.parse(Sex.getActivePartner(),
					descriptionSB.toString());
		}

		@Override
		public void applyEffects() {
		}
		
		@Override
		public boolean endsSex() {
			return !Sex.isDom(Main.game.getPlayer()) && !Sex.isConsensual();
		}
	};
	
	
	// Mutual orgasms:
	
	public static final SexAction PLAYER_GENERIC_MUTUAL_ORGASM_CUM_INSIDE = new SexAction(
			SexActionType.MUTUAL_ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			null,
			SexParticipantType.MISC) {
		@Override
		public String getActionTitle() {
			return "Stay in position";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}

		@Override
		public String getDescription() {
			return PLAYER_GENERIC_ORGASM_CREAMPIE.getDescription() //TODO
					+"</br></br>"
					+PARTNER_GENERIC_ORGASM_CUM_INSIDE.getDescription();
		}

		@Override
		public void applyEffects() {
			if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS) && Main.game.getPlayer().getPenisType()!=PenisType.NONE && !Main.game.getPlayer().isWearingCondom())
				Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(true);
		}
		
		@Override
		public List<OrificeType> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.isPlayer() && cumTarget.equals(Sex.getActivePartner())) {
				if (Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.VAGINA) == PenetrationType.PENIS && Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.VAGINA).isPlayer()) {
					return Util.newArrayListOfValues(new ListValue<>(OrificeType.VAGINA));
					
				} else if (Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.ANUS) == PenetrationType.PENIS && Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.ANUS).isPlayer()) {
					return Util.newArrayListOfValues(new ListValue<>(OrificeType.ANUS));
					
				} else if (Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.MOUTH) == PenetrationType.PENIS && Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.MOUTH).isPlayer()) {
					return Util.newArrayListOfValues(new ListValue<>(OrificeType.MOUTH));
					
				} else if (Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.NIPPLE) == PenetrationType.PENIS && Sex.getPenetratingCharacterUsingOrifice(Sex.getActivePartner(), OrificeType.NIPPLE).isPlayer()) {
					return Util.newArrayListOfValues(new ListValue<>(OrificeType.NIPPLE));
					
				} else {
					return null;
				}
			} else if(cumTarget.isPlayer() && cumProvider.equals(Sex.getActivePartner())) {
				if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.VAGINA) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.VAGINA).isPlayer()) {
					return Util.newArrayListOfValues(new ListValue<>(OrificeType.VAGINA));
					
				} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.ANUS) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.ANUS).isPlayer()) {
					return Util.newArrayListOfValues(new ListValue<>(OrificeType.ANUS));
					
				} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.MOUTH) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.MOUTH).isPlayer()) {
					return Util.newArrayListOfValues(new ListValue<>(OrificeType.MOUTH));
					
				} else if (Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.NIPPLE) == PenetrationType.PENIS && !Sex.getPenetratingCharacterUsingOrifice(Main.game.getPlayer(), OrificeType.NIPPLE).isPlayer()) {
					return Util.newArrayListOfValues(new ListValue<>(OrificeType.NIPPLE));
					
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
		
		@Override
		public boolean endsSex() {
			return !Sex.isDom(Main.game.getPlayer()) && !Sex.isConsensual();
		}
	};
	
	public static final SexAction PLAYER_GENERIC_MUTUAL_ORGASM_PULL_OUT = new SexAction(
			SexActionType.MUTUAL_ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			null,
			SexParticipantType.MISC) {
		@Override
		public String getActionTitle() {
			return "Pull out";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.isDom(Main.game.getPlayer()) || Sex.isSubHasEqualControl()) && Sex.isAnyPenetrationHappening();
		}

		@Override
		public String getDescription() {
			return
//					(PLAYER_GENERIC_ORGASM_PULL_OUT.getDescription() TODO
//					+ "</br></br>"
//					+
					PARTNER_GENERIC_ORGASM_PULL_OUT.getDescription();
		}

		@Override
		public void applyEffects() {
			if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS) && Main.game.getPlayer().getPenisType()!=PenisType.NONE && !Main.game.getPlayer().isWearingCondom())
				Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(true);
		}
		
		@Override
		public boolean endsSex() {
			return !Sex.isDom(Main.game.getPlayer()) && !Sex.isConsensual();
		}
	};
	
	public static final SexAction MUTUAL_GENERIC_ORGASM_FLOOR = new SexAction(
			SexActionType.MUTUAL_ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			null,
			SexParticipantType.MISC) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenis()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.FLOOR);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS).isEmpty()) {
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
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.FLOOR)
					+ "</br></br>"
					+ PARTNER_GENERIC_ORGASM_PULL_OUT.getDescription();
		}
	};
	
	
	public static final SexAction MUTUAL_GENERIC_ORGASM_WALL = new SexAction(MUTUAL_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenis()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.WALL);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS).isEmpty()) {
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
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.WALL)
					+ "</br></br>"
					+ PARTNER_GENERIC_ORGASM_PULL_OUT.getDescription();
		}
	};
	
	public static final SexAction MUTUAL_GENERIC_ORGASM_ASS = new SexAction(MUTUAL_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenis()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.ASS);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS).isEmpty()) {
				return "Pull out (ass)";
			}
			return "Cum on ass";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc.name]'s [npc.ass+].";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.ASS)
					+ "</br></br>"
					+ PARTNER_GENERIC_ORGASM_PULL_OUT.getDescription();
		}
		
		@Override
		public void applyEffects() {
			if (Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.ASS)!=null && !Main.game.getPlayer().isWearingCondom()) {
				Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.ASS).setDirty(true);
			} else {
				Sex.getTargetedPartner(Main.game.getPlayer()).addDirtySlot(InventorySlot.GROIN); //TODO
			}
		}
	};
	
	public static final SexAction MUTUAL_GENERIC_ORGASM_GROIN = new SexAction(MUTUAL_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenis()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.GROIN);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS).isEmpty()) {
				return "Pull out (groin)";
			}
			return "Cum on groin";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc.name]'s groin.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.GROIN)
					+ "</br></br>"
					+ PARTNER_GENERIC_ORGASM_PULL_OUT.getDescription();
		}
		
		@Override
		public void applyEffects() {
			if (Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.PENIS)!=null && !Main.game.getPlayer().isWearingCondom()) {
				Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.PENIS).setDirty(true);
			} else {
				Sex.getTargetedPartner(Main.game.getPlayer()).addDirtySlot(InventorySlot.GROIN);
			}
		}
	};
	
	public static final SexAction MUTUAL_GENERIC_ORGASM_BREASTS = new SexAction(MUTUAL_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenis()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.BREASTS);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS).isEmpty()) {
				return "Pull out (chest)";
			}
			return "Cum on chest";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc.name]'s chest.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.BREASTS)
					+ "</br></br>"
					+ PARTNER_GENERIC_ORGASM_PULL_OUT.getDescription();
		}
		
		@Override
		public void applyEffects() {
			if (Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.BREASTS)!=null && !Main.game.getPlayer().isWearingCondom()) {
				Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.BREASTS).setDirty(true);
			} else {
				Sex.getTargetedPartner(Main.game.getPlayer()).addDirtySlot(InventorySlot.CHEST);
			}
		}
	};
	
	public static final SexAction MUTUAL_GENERIC_ORGASM_FACE = new SexAction(MUTUAL_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenis()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.FACE);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS).isEmpty()) {
				return "Pull out (facial)";
			}
			return "Facial";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc.name]'s face.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.FACE)
					+ "</br></br>"
					+ PARTNER_GENERIC_ORGASM_PULL_OUT.getDescription();
		}
		
		@Override
		public void applyEffects() {
			if (Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.MOUTH)!=null && !Main.game.getPlayer().isWearingCondom()) {
				Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.MOUTH).setDirty(true);
			} else {
				Sex.getTargetedPartner(Main.game.getPlayer()).addDirtySlot(InventorySlot.MOUTH);
			}
		}
	};
	
	public static final SexAction MUTUAL_GENERIC_ORGASM_HAIR = new SexAction(MUTUAL_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenis()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.HAIR);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS).isEmpty()) {
				return "Pull out (hair)";
			}
			return "Cum in [npc.hair]";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum into [npc.name]'s [npc.hair].";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.HAIR)
					+ "</br></br>"
					+ PARTNER_GENERIC_ORGASM_PULL_OUT.getDescription();
		}
		
		@Override
		public void applyEffects() {
			if (Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.HAIR)!=null && !Main.game.getPlayer().isWearingCondom()) {
				Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.HAIR).setDirty(true);
			} else {
				Sex.getTargetedPartner(Main.game.getPlayer()).addDirtySlot(InventorySlot.HAIR);
			}
		}
	};
	
	public static final SexAction MUTUAL_GENERIC_ORGASM_STOMACH = new SexAction(MUTUAL_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenis()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.STOMACH);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS).isEmpty()) {
				return "Pull out (stomach)";
			}
			return "Cum on stomach";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc.name]'s stomach.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.STOMACH)
					+ "</br></br>"
					+ PARTNER_GENERIC_ORGASM_PULL_OUT.getDescription();
		}

		@Override
		public void applyEffects() {
			if (Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.STOMACH)!=null && !Main.game.getPlayer().isWearingCondom()) {
				Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.STOMACH).setDirty(true);
			} else {
				Sex.getTargetedPartner(Main.game.getPlayer()).addDirtySlot(InventorySlot.STOMACH);
			}
		}
	};
	
	public static final SexAction MUTUAL_GENERIC_ORGASM_LEGS = new SexAction(MUTUAL_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenis()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.LEGS);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS).isEmpty()) {
				return "Pull out (legs)";
			}
			return "Cum on [npc.legs]";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc.name]'s [npc.legs].";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.LEGS)
					+ "</br></br>"
					+ PARTNER_GENERIC_ORGASM_PULL_OUT.getDescription();
		}

		@Override
		public void applyEffects() {
			if (Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.LEGS)!=null && !Main.game.getPlayer().isWearingCondom()) {
				Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.LEGS).setDirty(true);
			} else {
				Sex.getTargetedPartner(Main.game.getPlayer()).addDirtySlot(InventorySlot.LEG);
			}
		}
	};
	
	public static final SexAction MUTUAL_GENERIC_ORGASM_BACK = new SexAction(MUTUAL_GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenis()
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Sex.getSexPositionSlot(Main.game.getPlayer()).getAvailableCumTargets().contains(OrgasmCumTarget.BACK);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersBeingPenetratedBy(Main.game.getPlayer(), PenetrationType.PENIS).isEmpty()) {
				return "Pull out (back)";
			}
			return "Cum on back";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc.name]'s back.";
		}

		@Override
		public String getDescription() {
			return getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.BACK)
					+ "</br></br>"
					+ PARTNER_GENERIC_ORGASM_PULL_OUT.getDescription();
		}

		@Override
		public void applyEffects() {
			if (Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.BACK)!=null && !Main.game.getPlayer().isWearingCondom()) {
				Sex.getTargetedPartner(Main.game.getPlayer()).getHighestZLayerCoverableArea(CoverableArea.BACK).setDirty(true);
			} else {
				Sex.getTargetedPartner(Main.game.getPlayer()).addDirtySlot(InventorySlot.TORSO_UNDER);
			}
		}
	};
}
