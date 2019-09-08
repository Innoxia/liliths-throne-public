package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Skin;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Wing;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayasRoom;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.CondomFailure;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.69
 * @version 0.3.1
 * @author Innoxia
 */
public class GenericOrgasms {
	
	private static boolean isTakingCock(GameCharacter character, GameCharacter penetrator) {
		return !Collections.disjoint(
				Sex.getContactingSexAreas(penetrator, SexAreaPenetration.PENIS, character),
				Util.newArrayListOfValues(SexAreaOrifice.VAGINA, SexAreaOrifice.ANUS, SexAreaOrifice.NIPPLE, SexAreaOrifice.MOUTH, SexAreaOrifice.URETHRA_PENIS, SexAreaOrifice.URETHRA_VAGINA, SexAreaOrifice.BREAST, SexAreaPenetration.FOOT));
	}
	
	private static boolean isTakingCockInOrifice(GameCharacter character, GameCharacter penetrator, List<SexAreaOrifice> orificesToCheck) {
		return !Collections.disjoint(
				Sex.getContactingSexAreas(penetrator, SexAreaPenetration.PENIS, character),
				orificesToCheck);
	}
	
	private static boolean isRealPenisFuckingCharacter(GameCharacter character, GameCharacter penetrator) {
		return (Sex.getCharactersUsingSexAreaOnCharacter(character, SexAreaPenetration.PENIS).contains(penetrator) && penetrator.hasPenisIgnoreDildo());
	}

	public static boolean isCumTargetRequirementsMet(OrgasmCumTarget cumTarget) {
		if(!Sex.getAvailableCumTargets(Sex.getCharacterPerformingAction()).contains(cumTarget)
				|| (Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexSlotGeneric.MISC_WATCHING && cumTarget.isRequiresPartner())
				|| !Sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
				|| !Sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.PENIS)
				|| Sex.getCharacterPerformingAction().isWearingCondom()
				|| (!Sex.getCharacterPerformingAction().isPlayer() && Sex.getRequestedPulloutWeighting(Sex.getCharacterPerformingAction())<0)) {
			return false;
		}
		
		return true;
	}
	
	private static String getPositionPreparation(GameCharacter characterOrgasming, GameCharacter characterTargeted) {
		if(characterTargeted!=null) {
			String orgasmText = Sex.getSexPositionSlot(characterOrgasming).getOrgasmDescription(characterOrgasming, characterTargeted);
			return UtilText.parse(characterOrgasming, characterTargeted, orgasmText);
			
		} else {
			String orgasmText = Sex.getSexPositionSlot(characterOrgasming).getOrgasmDescription(characterOrgasming, Sex.getTargetedPartner(characterOrgasming));
			return UtilText.parse(characterOrgasming, Sex.getTargetedPartner(characterOrgasming), orgasmText);
		}
	}
	
	private static StringBuilder genericOrgasmSB = new StringBuilder();
	
	private static String getGenericPenisOrgasmDescription(GameCharacter characterOrgasming, GameCharacter characterTargeted, OrgasmCumTarget cumTarget, CondomFailure condomFailure, boolean isSecondaryCreampieTarget) {
		genericOrgasmSB.setLength(0);

		SexAreaInterface contactingArea = null;
		if(!Sex.getAllContactingSexAreas(characterOrgasming, SexAreaPenetration.PENIS).isEmpty()) {
			contactingArea = Sex.getAllContactingSexAreas(characterOrgasming, SexAreaPenetration.PENIS).get(0);
		}
		
		if(!isSecondaryCreampieTarget) {
			if(Sex.getCreampieLockedBy()!=null) {
				GameCharacter lockingCharacter = Sex.getCreampieLockedBy().getKey();
				Class<? extends BodyPartInterface> bodypart = Sex.getCreampieLockedBy().getValue();
				if(bodypart == Skin.class) {
					genericOrgasmSB.append(UtilText.parse(characterOrgasming, lockingCharacter,
							"With [npc2.name] pressing [npc2.herself] tightly against [npc.herHim], [npc.nameIsFull] unable to pull out, and [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to orgasm."));
					
				} else if(bodypart == Arm.class) {
					genericOrgasmSB.append(UtilText.parse(characterOrgasming, lockingCharacter,
							"With [npc2.namePos] [npc2.arms] wrapped tightly around [npc.her] lower back, [npc.nameIsFull] unable to pull out, and [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to orgasm."));
					
				} else if(bodypart == Leg.class) {
					genericOrgasmSB.append(UtilText.parse(characterOrgasming, lockingCharacter,
							"With [npc2.namePos] [npc2.legs] locked tightly around [npc.her] lower back, [npc.nameIsFull] unable to pull out, and [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to orgasm."));
					
				} else if(bodypart == Tail.class) {
					genericOrgasmSB.append(UtilText.parse(characterOrgasming, lockingCharacter,
							"With [npc2.namePos] [npc2.tail] wrapped tightly around [npc.her] lower back, [npc.nameIsFull] unable to pull out, and [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to orgasm."));
					
				} else if(bodypart == Tail.class) {
					genericOrgasmSB.append(UtilText.parse(characterOrgasming, lockingCharacter,
							"With [npc2.namePos] [npc2.wingSize] [npc2.wings] wrapped tightly around [npc.her] body, [npc.nameIsFull] unable to pull out, and [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to orgasm."));
					
				} else if(bodypart == Tentacle.class) {
					genericOrgasmSB.append(UtilText.parse(characterOrgasming, lockingCharacter,
							"With [npc2.namePos] [npc2.tentacles] wrapped tightly around [npc.her] lower back, [npc.nameIsFull] unable to pull out, and [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to orgasm."));
				}
				
			} else {
				genericOrgasmSB.append(getPositionPreparation(characterOrgasming, characterTargeted));
			}
			
		} else {
			GameCharacter characterPenetrated = Sex.getCharactersHavingOngoingActionWith(characterOrgasming, SexAreaPenetration.PENIS).get(0);
			
			switch((SexAreaOrifice)contactingArea) {
				case ASS:
				case BREAST:
				case BREAST_CROTCH:
				case MOUTH:
				case NIPPLE:
				case NIPPLE_CROTCH:
				case THIGHS:
				case URETHRA_PENIS:
				case URETHRA_VAGINA:
					break;
				case ANUS:
					genericOrgasmSB.append(UtilText.parse(Util.newArrayListOfValues(characterOrgasming, characterPenetrated, characterTargeted),
							"With half of [npc.her] load being pumped into [npc2.namePos] [npc2.asshole+], and still in the middle of [npc.her] orgasm, [npc.name] suddenly [npc.verb(pull)] out,"
									+ " before quickly moving over to [npc3.name] and lining the [npc.cockHead+] of [npc.her] [npc.cock+] up to [npc3.her] [npc3.asshole+]."));
					break;
				case VAGINA:
					genericOrgasmSB.append(UtilText.parse(Util.newArrayListOfValues(characterOrgasming, characterPenetrated, characterTargeted),
							"With half of [npc.her] load being pumped into [npc2.namePos] [npc2.pussy+], and still in the middle of [npc.her] orgasm, [npc.name] suddenly [npc.verb(pull)] out,"
									+ " before quickly moving over to [npc3.name] and lining the [npc.cockHead+] of [npc.her] [npc.cock+] up to [npc3.her] [npc3.pussy+]."));
					break;
			}
		}
		
		if(characterTargeted==null || (cumTarget!=OrgasmCumTarget.INSIDE && cumTarget!=OrgasmCumTarget.INSIDE_SWITCH_DOUBLE)) {
			List<String> modifiers = new ArrayList<>();
			for(PenetrationModifier mod : PenetrationModifier.values()) {
				switch(mod) {
					case BARBED:
						break;
					case BLUNT:
						break;
					case FLARED:
						if(characterOrgasming.hasPenisModifier(mod)) {
							modifiers.add(" wide, flared head of [npc.namePos] [npc.cock] swells up, and [npc.she] [npc.verb(feel)] [npc.her] [npc.balls+] tightening as [npc.she] [npc.verb(start)] to cum.");
						}
						break;
					case KNOTTED:
						if(characterOrgasming.hasPenisModifier(mod)) {
							modifiers.add(" thick knot at the base of [npc.namePos] [npc.cock] swells up, and [npc.she] [npc.verb(feel)] [npc.her] [npc.balls+] tightening as [npc.she] [npc.verb(start)] to cum.");
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
							modifiers.add(" little tentacles lining [npc.namePos] [npc.cock] start frantically wriggling, and [npc.she] [npc.verb(feel)] [npc.her] [npc.balls+] tightening as [npc.she] [npc.verb(start)] to cum.");
						}
						break;
					case VEINY:
						break;
				}
			}

			List<GameCharacter> ongoingProstateStimulators = new ArrayList<>(Sex.getOngoingCharactersUsingAreas(characterOrgasming, SexAreaOrifice.ANUS, SexAreaPenetration.FINGER));
			if(characterOrgasming.hasVagina()) {
				ongoingProstateStimulators = new ArrayList<>(Sex.getOngoingCharactersUsingAreas(characterOrgasming, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER));
			}
			if(!modifiers.isEmpty()) {
				if(!ongoingProstateStimulators.isEmpty()) {
					genericOrgasmSB.append(UtilText.parse(ongoingProstateStimulators.get(0), characterOrgasming,
							" At this moment, [npc.namePos] [npc.verb(curl)] [npc.her] [npc.fingers+] up inside [npc2.namePos] "+(characterOrgasming.hasVagina()?"[npc2.pussy+]":"[npc2.asshole+]")+","
								+ " before rapidly stroking and massaging [npc2.her] prostate in an attempt to milk as much [npc2.cum] out of [npc2.herHim] as possible."
								+ " Immediately, [npc2.her] body reacts to this added stimulation, and the "));
				} else {
					genericOrgasmSB.append(" The");
				}
				genericOrgasmSB.append(modifiers.get(Util.random.nextInt(modifiers.size())));
				
			} else {
				if(!ongoingProstateStimulators.isEmpty()) {
					genericOrgasmSB.append(UtilText.parse(ongoingProstateStimulators.get(0), characterOrgasming,
							" At this moment, [npc.namePos] [npc.verb(curl)] [npc.her] [npc.fingers+] up inside [npc2.namePos] "+(characterOrgasming.hasVagina()?"[npc2.pussy+]":"[npc2.asshole+]")+","
								+ " before rapidly stroking and massaging [npc2.her] prostate in an attempt to milk as much [npc2.cum] out of [npc2.herHim] as possible."
								+ " Immediately, [npc2.her] body reacts to this added stimulation, and as [npc2.her] [npc2.cock+] starts twitching, [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.balls+] tightening as [npc2.she] [npc2.verb(start)] to cum."));
				} else {
					genericOrgasmSB.append(" [npc.NamePos] [npc.cock+] twitches, and [npc.she] [npc.verb(feel)] [npc.her] [npc.balls+] tightening as [npc.she] [npc.verb(start)] to cum.");
				}
			}

			if(characterTargeted!=null) {
				genericOrgasmSB.append("<br/>");
				
				if(contactingArea.isOrifice()) {
					switch((SexAreaOrifice) contactingArea) {
						case ANUS:
							genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out of [npc2.namePos] [npc2.asshole+]");
							break;
						case ASS:
							genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out from between [npc2.namePos] ass cheeks");
							break;
						case BREAST:
							genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out from between [npc2.namePos] [npc2.breasts+]");
							break;
						case MOUTH:
							if(Sex.getCreampieLockedBy()==null) {
								GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(characterOrgasming);
								genericOrgasmSB.append(UtilText.parse(characterOrgasming, primary, "[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out of [npc2.namePos] mouth"));
							} else {
								genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out of [npc2.namePos] mouth");
							}
							break;
						case NIPPLE:
							genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out of [npc2.namePos] [npc2.nipple+]");
							break;
						case THIGHS:
							genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out from between [npc2.namePos] thighs");
							break;
						case URETHRA_PENIS:
							genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out of [npc2.namePos] [npc2.penisUrethra+]");
							break;
						case URETHRA_VAGINA:
							genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out of [npc2.namePos] [npc2.vaginaUrethra+]");
							break;
						case VAGINA:
							genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out of [npc2.namePos] [npc2.pussy+]");
							break;
						default:
							break;
					}
				
					if(!characterOrgasming.getPenisModifiers().isEmpty()) {
						switch(characterOrgasming.getPenisModifiers().get(Util.random.nextInt(characterOrgasming.getPenisModifiers().size()))) {
							case BARBED:
								genericOrgasmSB.append(", before reaching down and sliding [npc.her] [npc.hand] up and down over [npc.her] sensitive little barbs.");
								break;
							case BLUNT:
								genericOrgasmSB.append(", before reaching down and starting to furiously masturbate; [npc.her] [npc.hand] running up the length of [npc.her] [npc.cock] to rub and tease [npc.her] blunt head.");
								break;
							case FLARED:
								genericOrgasmSB.append(", before reaching down and starting to furiously masturbate; [npc.her] [npc.hand] running up the length of [npc.her] [npc.cock] to rub and tease [npc.her] wide, flared head.");
								break;
							case KNOTTED:
								genericOrgasmSB.append(", before reaching down and starting to furiously masturbate; [npc.her] [npc.hand] sliding down the length of [npc.her] [npc.cock] to grip and rub at [npc.her] swollen knot.");
								break;
							case PREHENSILE:
								genericOrgasmSB.append(", before reaching down and starting to furiously masturbate;"
										+ " [npc.her] [npc.hand] sliding down the length of [npc.her] prehensile [npc.cock] as [npc.she] [npc.verb(curl)] it back against [npc.her] [npc.fingers].");
								break;
							case RIBBED:
								genericOrgasmSB.append(", before reaching down and sliding [npc.her] [npc.hand] up and down over the bumpy ribs that line [npc.her] [npc.cock].");
								break;
							case SHEATHED:
								genericOrgasmSB.append(", before reaching down and starting to furiously masturbate;"
											+ " [npc.her] [npc.hand] sliding down the length of [npc.her] [npc.cock] to bump against [npc.her] sheath, before rising back up to [npc.her] [npc.cockHead+].");
								break;
							case TAPERED:
								genericOrgasmSB.append(", before reaching down and starting to furiously masturbate; [npc.her] [npc.hand] running up the length of [npc.her] [npc.cock] to rub and tease [npc.her] tapered head.");
								break;
							case TENTACLED:
								genericOrgasmSB.append(", before reaching down and sliding [npc.her] [npc.hand] up and down over the squirming tentacles that line the sides of [npc.her] [npc.cock].");
								break;
							case VEINY:
								genericOrgasmSB.append(", before reaching down and sliding [npc.her] [npc.hand] up and down over [npc.her] veiny [npc.cock].");
								break;
						}
						
					} else {
						genericOrgasmSB.append(", before reaching down and starting to furiously masturbate; [npc.her] [npc.hand] running up the length of [npc.her] [npc.cock] to rub and tease [npc.her] [npc.cockHead].");
					}
					
				} else {
					switch((SexAreaPenetration) contactingArea) {
						case CLIT:
							genericOrgasmSB.append("[npc.Name] [npc.verb(take)] [npc.her] [npc.cock+] away from [npc2.namePos] [npc2.pussy+], before reaching down and starting to furiously masturbate;"
									+ " [npc.her] [npc.hand] running up the length of [npc.her] [npc.cock] to rub and tease [npc.her] [npc.cockHead].");
							break;
						case FINGER:
							genericOrgasmSB.append(" Bucking [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] [npc2.verb(continue)] stroking [npc.her] [npc.cock+].");
							break;
						case PENIS:
							break;
						case TAIL:
							break;
						case TENTACLE:
							break;
						case FOOT:
							genericOrgasmSB.append(" Bucking [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] [npc2.verb(continue)] stimulating [npc.her] [npc.cock+] with [npc2.her] [npc2.feet+].");
							break;
						case TONGUE:
							genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out of [npc2.namePos] mouth, before reaching down and starting to furiously masturbate;"
									+ " [npc.her] [npc.hand] running up the length of [npc.her] [npc.cock] to rub and tease [npc.her] [npc.cockHead].");
							break;
					}
				}
				
			}
			
			
		} else if(cumTarget==OrgasmCumTarget.INSIDE || cumTarget==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE) {
			List<String> modifiers = new ArrayList<>();
			
			List<GameCharacter> ongoingProstateStimulators = new ArrayList<>(Sex.getOngoingCharactersUsingAreas(characterOrgasming, SexAreaOrifice.ANUS, SexAreaPenetration.FINGER));
			if(characterOrgasming.hasVagina()) {
				ongoingProstateStimulators = new ArrayList<>(Sex.getOngoingCharactersUsingAreas(characterOrgasming, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER));
			}
			if(!ongoingProstateStimulators.isEmpty()) {
				genericOrgasmSB.append(UtilText.parse(ongoingProstateStimulators.get(0), characterOrgasming,
						" At this moment, [npc.namePos] [npc.verb(curl)] [npc.her] [npc.fingers+] up inside [npc2.namePos] "+(characterOrgasming.hasVagina()?"[npc2.pussy+]":"[npc2.asshole+]")+","
							+ " before rapidly stroking and massaging [npc2.her] prostate in an attempt to milk as much [npc2.cum] as possible out of [npc2.herHim]."));
			}
			
			if(contactingArea.isOrifice()) {
				switch((SexAreaOrifice)contactingArea) {
					case ANUS: case NIPPLE: case NIPPLE_CROTCH: case VAGINA: case URETHRA_PENIS: case URETHRA_VAGINA:
						// ...
						String orificeName =
							(contactingArea == SexAreaOrifice.VAGINA
								?"[npc2.pussy]"
								:(contactingArea == SexAreaOrifice.ANUS
									?"[npc2.asshole]"
									:(contactingArea == SexAreaOrifice.NIPPLE
											?"[npc2.nipple]"
											:(contactingArea == SexAreaOrifice.NIPPLE_CROTCH
													?"[npc2.crotchNipple]"
															:"urethra"))));
						String orificeNamePlusDescriptor =
								(contactingArea == SexAreaOrifice.VAGINA
									?"[npc2.pussy+]"
									:(contactingArea == SexAreaOrifice.ANUS
										?"[npc2.asshole+]"
										:(contactingArea == SexAreaOrifice.NIPPLE
												?"[npc2.nipple+]"
												:(contactingArea == SexAreaOrifice.NIPPLE_CROTCH
														?"[npc2.crotchNipple+]"
																:"urethra"))));
						//TODO check:
						if(characterOrgasming.hasPenisModifier(PenetrationModifier.KNOTTED)) {
							if(cumTarget==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE) {
								if(!isSecondaryCreampieTarget) {
									GameCharacter secondaryTarget = getSecondaryCreampieTarget(characterTargeted, (SexAreaOrifice) Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0));
									genericOrgasmSB.append(" Pushing forwards, [npc.name] [npc.verb(grind)] the knot at the base of [npc.her] [npc.cock+] against [npc2.namePos] "+orificeNamePlusDescriptor+"."
											+ " Wanting to save [npc.her] rapidly-swelling knot for "+(UtilText.parse(characterOrgasming, secondaryTarget, "[npc2.namePos] "+orificeNamePlusDescriptor))+","
													+ " [npc.she] [npc.verb(make)] sure to avoid getting locked inside of [npc2.namePos] "+orificeName+", and [npc.verb(make)] do with just hilting [npc.her] shaft inside of [npc2.herHim].");
								} else {
									genericOrgasmSB.append(" Pushing forwards, [npc.name] [npc.verb(ram)] the now-fully swollen knot at the base of [npc.her] [npc.cock+] against [npc2.namePos] "+orificeNamePlusDescriptor+"."
											+ " By now it's so engorged that it seems almost impossible to push it inside, but with a determined [npc.moan], [npc.name] violently [npc.verb(thrust)] forwards,"
												+ " and with an accompanying cry from [npc2.name], [npc.she] [npc.verb(manage)] to force [npc.her] fat knot into [npc2.namePos] "+orificeNamePlusDescriptor+".");
								}
								
							} else {
								genericOrgasmSB.append(" Pushing forwards, [npc.name] [npc.verb(ram)] the knot at the base of [npc.her] [npc.cock+] against [npc2.namePos] "+orificeNamePlusDescriptor+"."
										+ " It's already started to swell up so much that [npc.she] [npc.do]n't manage to get it inside on the first thrust,"
											+ " but, after pulling back and slamming [npc.her] [npc.hips] forwards, [npc.she] [npc.verb(manage)] to push the thick knot into [npc2.namePos] "+orificeNamePlusDescriptor+".");
							}
							
						} else {
							if(cumTarget==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE && isSecondaryCreampieTarget) { 
								genericOrgasmSB.append(" Thrusting [npc.her] [npc.cock+] into [npc2.namePos] "+orificeNamePlusDescriptor+","
										+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to finish orgasming inside of [npc2.herHim].");
							} else {
								genericOrgasmSB.append(" Ramming [npc.her] [npc.cock+] deep into [npc2.namePos] "+orificeNamePlusDescriptor+", [npc.name] [npc.verb(let)] out [npc.a_moan+] as it starts to twitch inside of [npc2.herHim].");
							}
						}
						
						modifiers.clear();
						for(PenetrationModifier mod : PenetrationModifier.values()) {
							switch(mod) {
								case BARBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc.Name] [npc.verb(continue)] to make small, thrusting movements,"
												+ " raking [npc.her] barbs back against the inner walls of [npc2.namePos] "+orificeName+" and causing [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case BLUNT:
									break;
								case FLARED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										if(characterOrgasming.isPlayer()) {
											modifiers.add(" You feel the wide, flared head of your [npc.cock] swell up, making a seal with which to trap your [npc.cum] deep within [npc2.her] "+orificeName+".");
										} else {
											modifiers.add(" The wide, flared head of [npc.namePos] [npc.cock] swells up, making a seal with which to trap [npc.her] [npc.cum] deep within [npc2.namePos] "+orificeName+".");
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
											modifiers.add(" You feel your ribbed [npc.cock] bumping against the inner walls of [npc2.her] "+orificeName+", which causes [npc2.herHim] to let out [npc2.a_moan+].");
										} else {
											modifiers.add(" The ribbed length of [npc.namePos] [npc.cock] bumps against the inner walls of [npc2.namePos] "+orificeName+", which causes [npc2.herHim] to let out [npc2.a_moan+].");
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
											modifiers.add(" You feel the wriggling tentacles lining your [npc.cock] start to massage the inner walls of [npc2.her] "+orificeName+", which causes [npc2.herHim] to let out [npc2.a_moan+].");
										} else {
											modifiers.add(" The wriggling tentacles lining [npc.namePos] [npc.cock] start to massage the inner walls of [npc2.namePos] "+orificeName+", which causes [npc2.herHim] to let out [npc2.a_moan+].");
											
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
							if(cumTarget!=OrgasmCumTarget.INSIDE_SWITCH_DOUBLE || isSecondaryCreampieTarget) {
								genericOrgasmSB.append(" Keeping [npc.her] [npc.hips] pushed tightly against [npc2.namePos] "+orificeName+", [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.her] knot swells up to its full size."
										+ " [npc.She] then [npc.verb(buck)] back a little, and [npc2.name] [npc2.verb(let)] out a startled cry as [npc2.sheIs] pulled along with [npc.herHim];"
											+ " evidence that [npc.her] [npc.cock] is now firmly locked inside [npc2.her] "+orificeNamePlusDescriptor+".");
							}
						}
						break;
						
					case ASS:
						genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] thrusting [npc.her] [npc.cock+] between [npc2.namePos] [npc2.assSize] ass cheeks, letting out [npc.a_moan+] as [npc.she] [npc.verb(feel)] it start to twitch.");
						
						for(PenetrationModifier mod : PenetrationModifier.values()) {
							switch(mod) {
								case BARBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc.Her] movements cause the barbs lining the sides of [npc.her] [npc.cock] to rake against [npc2.namePos] [npc2.ass], causing [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case BLUNT:
									break;
								case FLARED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc.Her] flared head swells up, causing [npc2.namePos] cheeks to be parted ever wider, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case KNOTTED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc.Her] fat knot swells up, and with each thrust, bumps wildly against [npc2.namePos] [npc2.asshole], which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case PREHENSILE:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" Harnessing the ability of [npc.her] prehensile cock, [npc.name] bends it down over [npc2.namePos] back on each thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case RIBBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc.Her] ribbed shaft repeatedly bumps against [npc2.namePos] [npc2.asshole] on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case SHEATHED:
									break;
								case TAPERED:
									break;
								case TENTACLED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" The little tentacles lining [npc.her] shaft wriggle against and massage [npc2.namePos] [npc2.asshole] on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
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
											modifiers.add(" [npc.Her] movements cause the barbs lining [npc.her] [npc.cock] to rake against the sides of [npc2.namePos] breasts, causing [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case BLUNT:
										break;
									case FLARED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" [npc.Her] flared head swells up, bumping wildly against [npc2.namePos] [npc2.breasts+] on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case KNOTTED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" [npc.Her] fat knot swells up, and with each thrust, bumps wildly against [npc2.namePos] [npc2.breasts+], which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case PREHENSILE:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Harnessing the ability of [npc.her] prehensile cock, [npc.name] curls it around alternating sides of [npc2.namePos] breasts,"
													+ " which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case RIBBED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" [npc.Her] ribbed shaft repeatedly bumps against [npc2.namePos] [npc2.breasts+] on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case SHEATHED:
										break;
									case TAPERED:
										break;
									case TENTACLED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" The little tentacles lining [npc.her] shaft wriggle against and massage [npc2.namePos] [npc2.breasts+] on every thrust,"
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
											modifiers.add(" [npc.Her] movements cause the barbs lining [npc.her] [npc.cock] to rake sharply over [npc2.namePos] torso, causing [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case BLUNT:
										break;
									case FLARED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" [npc.Her] flared head swells up, bumping wildly against [npc2.namePos] torso on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case KNOTTED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" [npc.Her] fat knot swells up, and with each thrust, bumps wildly against [npc2.namePos] torso, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case PREHENSILE:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Harnessing the ability of [npc.her] prehensile cock, [npc.name] curls it this way and that as [npc.she] grinds against [npc2.namePos] chest,"
													+ " which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case RIBBED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" [npc.Her] ribbed shaft repeatedly bumps over [npc2.namePos] torso on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case SHEATHED:
										break;
									case TAPERED:
										break;
									case TENTACLED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" The little tentacles lining [npc.her] shaft wriggle against and massage [npc2.namePos] torso on every thrust,"
													+ " which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case VEINY:
										break;
								}
							}
						}
						break;
						
					case BREAST_CROTCH:
						genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] thrusting [npc.her] [npc.cock+] against [npc2.namePos] [npc2.crotchBoobs+], letting out [npc.a_moan+] as [npc.she] [npc.verb(feel)] it start to twitch.");
						
						for(PenetrationModifier mod : PenetrationModifier.values()) {
							switch(mod) {
								case BARBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc.Her] movements cause the barbs lining [npc.her] [npc.cock] to rake against the sides of [npc2.namePos] [npc2.crotchBoobs], causing [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case BLUNT:
									break;
								case FLARED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc.Her] flared head swells up, bumping wildly against [npc2.namePos] [npc2.crotchBoobs+] on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case KNOTTED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc.Her] fat knot swells up, and with each thrust, bumps wildly against [npc2.namePos] [npc2.crotchBoobs+], which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case PREHENSILE:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" Harnessing the ability of [npc.her] prehensile cock, [npc.name] curls it around alternating sides of [npc2.namePos] [npc2.crotchBoobs],"
												+ " which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case RIBBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc.Her] ribbed shaft repeatedly bumps against [npc2.namePos] [npc2.crotchBoobs+] on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case SHEATHED:
									break;
								case TAPERED:
									break;
								case TENTACLED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" The little tentacles lining [npc.her] shaft wriggle against and massage [npc2.namePos] [npc2.crotchBoobs+] on every thrust,"
												+ " which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case VEINY:
									break;
							}
						}
						break;
						
					case MOUTH:
						GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(characterOrgasming);
						if(Sex.getCreampieLockedBy()==null && !characterTargeted.equals(primary)) {
							if(characterOrgasming.hasPenisModifier(PenetrationModifier.KNOTTED)) {
								genericOrgasmSB.append(UtilText.parse(Util.newArrayListOfValues(characterOrgasming, characterTargeted, primary),
										" Wanting [npc2.name] to be the one to bear the brunt of [npc.her] orgasm, [npc.name] [npc.verb(draw)] [npc.her] [npc.cock+] from out of [npc3.namePos] throat,"
												+ " before pushing it into [npc2.her] mouth, and then thrusting forwards until [npc.her] rapidly-growing knot is rammed against [npc2.her] [npc2.lips+]."
										+ " It's already started to swell up so much that [npc.she] [npc.do]n't manage to get it into [npc2.namePos] [npc2.mouth] on the first thrust,"
											+ " but, after pulling back and slamming [npc.her] [npc.hips] forwards, [npc.she] succeeds in pushing the thick knot past [npc2.her] [npc2.lips]."
										+ "<br/>"
										+ "The moment [npc.she] [npc.verb(feel)] it pop inside, [npc.name] [npc.verb(let)] out [npc.a_moan+], and as [npc.she] presses [npc.her] groin firmly against [npc2.namePos] [npc2.face+],"
											+ " [npc.her] knot fully expands and locks [npc.her] [npc.cock+] down [npc2.namePos] throat."));
									
							} else {
								genericOrgasmSB.append(UtilText.parse(Util.newArrayListOfValues(characterOrgasming, characterTargeted, primary),
										" Wanting [npc2.name] to be the one to bear the brunt of [npc.her] orgasm, [npc.name] [npc.verb(draw)] [npc.her] [npc.cock+] from out of [npc3.namePos] mouth, before ramming it deep down [npc2.her] throat."
										+ " With a delighted look on [npc.her] face, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] it starts to twitch inside of [npc2.herHim]."));
							}
							
						} else {
							if(characterOrgasming.hasPenisModifier(PenetrationModifier.KNOTTED)) {
								genericOrgasmSB.append(
										" [npc.Name] [npc.verb(push)] forwards, ramming the knot at the base of [npc.her] [npc.cock+] against [npc2.namePos] [npc2.lips+]."
										+ " It's already started to swell up so much that [npc.she] [npc.do]n't manage to get it into [npc2.namePos] [npc2.mouth] on the first thrust,"
											+ " but, after pulling back and slamming [npc.her] [npc.hips] forwards, [npc.she] succeeds in pushing the thick knot past [npc2.her] [npc2.lips]."
										+ "<br/>"
										+ "The moment [npc.she] [npc.verb(feel)] it pop inside, [npc.name] [npc.verb(let)] out [npc.a_moan+], and as [npc.she] presses [npc.her] groin firmly against [npc2.namePos] [npc2.face+],"
											+ " [npc.her] knot fully expands and locks [npc.her] [npc.cock+] down [npc2.namePos] throat.");
									
							} else {
								genericOrgasmSB.append(" Ramming [npc.her] [npc.cock+] deep down [npc2.namePos] throat, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] it starts to twitch inside of [npc2.herHim].");
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
												modifiers.add(" [npc.Name] continues to make small, thrusting movements, raking [npc.her] barbs back against the lining of your throat and causing you to let out a choking [npc2.moan].");
											} else {
												modifiers.add(" [npc.Name] continues to make small, thrusting movements,"
														+ " raking [npc.her] barbs back against the lining of [npc2.namePos] throat and causing [npc2.herHim] to let out a choking [npc2.moan].");
											}
										}
									}
									break;
								case BLUNT:
									break;
								case FLARED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										if(characterOrgasming.isPlayer()) {
											modifiers.add(" You feel the wide, flared head of your [npc.cock] swell up, making a seal with which to trap your [npc.cum] deep down [npc2.her] throat.");
										} else {
											if(characterTargeted.isPlayer()) {
												modifiers.add(" The wide, flared head of [npc.namePos] [npc.cock] swells up, making a seal with which to trap [npc.her] [npc.cum] deep down your throat.");
											} else {
												modifiers.add(" The wide, flared head of [npc.namePos] [npc.cock] swells up, making a seal with which to trap [npc.her] [npc.cum] deep down [npc2.namePos] throat.");
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
											modifiers.add(" You feel your ribbed [npc.cock] bumping against the lining of [npc2.her] throat, which causes [npc2.herHim] to let out a muffled [npc2.moan].");
										} else {
											if(characterTargeted.isPlayer()) {
												modifiers.add(" The ribbed length of [npc.namePos] [npc.cock] bumps against the lining of your throat, which causes you to let out a muffled [npc2.moan].");
											} else {
												modifiers.add(" The ribbed length of [npc.namePos] [npc.cock] bumps against the lining of [npc2.namePos] throat, which causes [npc2.herHim] to let out a muffled [npc2.moan].");
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
											modifiers.add(" You feel the wriggling tentacles lining your [npc.cock] start to massage the lining of [npc2.her] throat, which causes [npc2.herHim] to let out a muffled [npc2.moan].");
										} else {
											if(characterTargeted.isPlayer()) {
												modifiers.add(" The wriggling tentacles lining [npc.namePos] [npc.cock] start to massage the lining of your throat, which causes you to let out a muffled [npc2.moan].");
											} else {
												modifiers.add(" The wriggling tentacles lining [npc.namePos] [npc.cock] start to massage the lining of [npc2.namePos] throat, which causes [npc2.herHim] to let out a muffled [npc2.moan].");
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
								genericOrgasmSB.append(" Keeping your [npc.hips] pushed tightly against [npc2.namePos] [npc2.face], you let out [npc.a_moan+] as you feel your knot swell up to its full size."
										+ " Bucking back a little, you grin as [npc2.namePos] pulled along with you; evidence that your [npc.cock] is now firmly locked inside [npc2.her] [npc2.mouth].");
							} else {
								if(characterTargeted.isPlayer()) {
									genericOrgasmSB.append(" Keeping [npc.her] [npc.hips] pushed tightly against your [npc2.face], [npc.name] lets out [npc.a_moan+] as [npc.her] knot swells up to its full size."
											+ " [npc.She] then bucks back a little, and you let out a very muffled cry as you're pulled along with [npc.herHim];"
												+ " evidence that [npc.her] [npc.cock] is now firmly locked inside your [npc2.mouth].");
								} else {
									genericOrgasmSB.append(" After just a moment, [npc.her] knot has fully swollen in size, locking [npc.her] [npc.cock] inside [npc2.namePos] [npc2.mouth].");
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
										modifiers.add(" [npc.Her] movements cause the barbs lining the sides of [npc.her] [npc.cock] to rake against [npc2.namePos] [npc2.legs+], causing [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case BLUNT:
									break;
								case FLARED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc.Her] flared head swells up, causing [npc2.namePos] [npc2.legs+] to be parted ever wider, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case KNOTTED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc.Her] fat knot swells up, and with each thrust, bumps wildly against [npc2.namePos] [npc2.legs+], which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case PREHENSILE:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" Harnessing the ability of [npc.her] prehensile cock, [npc.name] bends it around [npc2.namePos] [npc2.legs+] on each thrust,"
												+ " which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case RIBBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc.Her] ribbed shaft repeatedly bumps against [npc2.namePos] [npc2.legs+] on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case SHEATHED:
									break;
								case TAPERED:
									break;
								case TENTACLED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" The little tentacles lining [npc.her] shaft wriggle against and massage [npc2.namePos] [npc2.legs+] on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
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
										modifiers.add(" [npc.Her] movements cause the barbs lining the sides of [npc.her] [npc.cock] to rake against [npc2.namePos] [npc2.fingers+], causing [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case BLUNT:
									break;
								case FLARED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc.Her] flared head swells up, causing [npc2.namePos] [npc2.fingers+] to be parted ever wider, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case KNOTTED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc.Her] fat knot swells up, and with each thrust, bumps wildly against [npc2.namePos] [npc2.fingers+], which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case PREHENSILE:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" Harnessing the ability of [npc.her] prehensile cock, [npc.name] bends it around [npc2.namePos] [npc2.fingers+] on each thrust,"
												+ " which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case RIBBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc.Her] ribbed shaft repeatedly bumps against [npc2.namePos] [npc2.fingers+] on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case SHEATHED:
									break;
								case TAPERED:
									break;
								case TENTACLED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" The little tentacles lining [npc.her] shaft wriggle against and massage [npc2.namePos] [npc2.fingers+] on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
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
			genericOrgasmSB.append(" As your [npc.balls+] tense up, ");
		} else {
			genericOrgasmSB.append(" As [npc.her] [npc.balls+] tense up, ");
		}
		genericOrgasmSB.append(getCumQuantityDescription(characterOrgasming));
		if(characterOrgasming.getPenisRawOrgasmCumQuantity()>0) {
			genericOrgasmSB.append(cumTargetDescription(characterOrgasming, characterTargeted, cumTarget, condomFailure, isSecondaryCreampieTarget));
		}
		
		if(characterOrgasming.hasPenisModifier(PenetrationModifier.KNOTTED)
				&& (cumTarget==OrgasmCumTarget.INSIDE || (cumTarget==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE && isSecondaryCreampieTarget))
				&& contactingArea.isOrifice()
				&& ((SexAreaOrifice)contactingArea).isInternalOrifice()) {
			genericOrgasmSB.append("<br/>"
					+ "Even after [npc.namePos] [npc.balls+] have pumped their entire load into [npc2.name], [npc.her] knot remains swollen, locking"
					+ "#IFnpc2.isPlayer()"
					+ "#THEN the two of you together."
					+ "#ELSE [npc.herHim] and [npc.her] partner together."
					+ "#ENDIF"
					+ " It takes a few minutes for it to start to deflate, and with a wet pop, [npc.sheIs] finally able to pull [npc.her] [npc.cock+] free.");
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
			targetName = "[npc.namePos]";
		}
		String cumQuantityDescription = targetName+" [npc.cum+] squirts";
		
		switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
			case ZERO_NONE:
				cumQuantityDescription = "not even a single drop of [npc.cum] is produced...";
				break;
			case ONE_TRICKLE:
				cumQuantityDescription = "a small trickle of [npc.cum+] squirts";
				break;
			case TWO_SMALL_AMOUNT:
				cumQuantityDescription = "a small amount of [npc.cum+] squirts";
				break;
			case THREE_AVERAGE:
				cumQuantityDescription = targetName+" [npc.cum+] squirts out";
				break;
			case FOUR_LARGE:
				cumQuantityDescription = targetName+" [npc.cum+] shoots out";
				break;
			case FIVE_HUGE:
				cumQuantityDescription = targetName+" [npc.cum+] shoots out";
				break;
			case SIX_EXTREME:
				cumQuantityDescription = targetName+" [npc.cum+] spurts out";
				break;
			case SEVEN_MONSTROUS:
				cumQuantityDescription = targetName+" [npc.cum+] spurts out";
				break;
		}
		return UtilText.parse(characterOrgasming, cumQuantityDescription);
	}
	
	private static String cumTargetDescription(GameCharacter characterOrgasming, GameCharacter target, OrgasmCumTarget targetArea, CondomFailure condomFailure, boolean isSecondaryCreampieTarget) {
		StringBuilder cumTargetSB = new StringBuilder();
		
		if(!isSecondaryCreampieTarget) {
			if(characterOrgasming.isWearingCondom()) {
				cumTargetSB.append(UtilText.parse(characterOrgasming, " into the condom that [npc.sheIs] wearing."));
				
				switch(condomFailure) {
					case CUM_OVERLOAD:
						cumTargetSB.append(UtilText.parse(characterOrgasming,
								" [npc.Her] orgasm proves to be too much for the rubbery sheath to endure, and, after swelling up and filling with [npc.her] huge amount of [npc.cum+], it suddenly bursts, expelling [npc.her] cum"));
						break;
					case MINERAL_OIL_CUM:
						cumTargetSB.append(UtilText.parse(characterOrgasming, target,
								" Although ordinarily strong enough to contain [npc.her] cum, the rubbery sheath's durability has been severely degraded by [npc2.namePos] mineral-oil-infused cum, and,"
										+ " after swelling up and filling with [npc.namePos] [npc.cum+], it suddenly bursts, expelling [npc.her] seed"));
						break;
					case MINERAL_OIL_GIRLCUM:
						cumTargetSB.append(UtilText.parse(characterOrgasming, target,
								" Although ordinarily strong enough to contain [npc.her] cum, the rubbery sheath's durability has been severely degraded by [npc2.namePos] mineral-oil-infused girlcum, and,"
										+ " after swelling up and filling with [npc.namePos] [npc.cum+], it suddenly bursts, expelling [npc.her] seed"));
						break;
					case MINERAL_OIL_MILK:
						cumTargetSB.append(UtilText.parse(characterOrgasming, target,
								" Although ordinarily strong enough to contain [npc.her] cum, the rubbery sheath's durability has been severely degraded by [npc2.namePos] mineral-oil-infused milk, and,"
										+ " after swelling up and filling with [npc.namePos] [npc.cum+], it suddenly bursts, expelling [npc.her] seed"));
						break;
					case MINERAL_OIL_SALIVA:
						cumTargetSB.append(UtilText.parse(characterOrgasming, target,
								" Although ordinarily strong enough to contain [npc.her] cum, the rubbery sheath's durability has been severely degraded by [npc2.namePos] mineral-oil-infused saliva, and,"
										+ " after swelling up and filling with [npc.namePos] [npc.cum+], it suddenly bursts, expelling [npc.her] seed"));
						break;
					case MINERAL_OIL_SELF_CUM:
						cumTargetSB.append(UtilText.parse(characterOrgasming, target,
								" Although ordinarily strong enough to contain the quantity of cum that [npc.name] produces, the rubbery sheath's durability is instantaneously degraded by [npc.her] mineral-oil-infused cum,"
										+ " and it suddenly bursts, expelling [npc.her] seed"));
						break;
					case SABOTAGED:
						cumTargetSB.append(UtilText.parse(characterOrgasming,
								" Having been surreptitiously sabotaged, the rubbery sheath's durability is severely compromised, and as it swells up and fills with [npc.namePos] [npc.cum+], it suddenly bursts, expelling [npc.her] seed"));
						break;
					case NONE:
						return cumTargetSB.toString();
				}
				
			}
		}
		
		if(!characterOrgasming.isCoverableAreaExposed(CoverableArea.PENIS)) {
			if(characterOrgasming.isPlayer()) {
				return "  into your [npc.lowClothing(PENIS)].";
			} else {
				return UtilText.parse(characterOrgasming, "  into [npc.her] [npc.lowClothing(PENIS)].");
			}
		}
		
		List<AbstractClothing> targetAreaClothingCummedOn;
		switch(targetArea) {
			case ASS:
				target = Sex.getTargetedPartner(characterOrgasming);
				targetAreaClothingCummedOn = getClothingCummedOn(target, CoverableArea.ASS);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, CoverableArea.ASS, targetAreaClothingCummedOn);
					
				} else {
					return UtilText.parse(characterOrgasming, target,
							" all over [npc2.namePos] [npc2.ass+]."
							+ " [npc.Name] [npc.verb(grin)] as [npc.her] [npc.cum+] splatters onto [npc2.namePos] naked backside,"
								+ " and [npc2.she] can't help but let out [npc2.a_moan] as [npc2.she] [npc2.verb(feel)] it running down over [npc2.her] "+(target.getGenitalArrangement()==GenitalArrangement.CLOACA?"[npc2.assSkin+]":"[npc2.asshole+]")+".");
				}
			case BACK:
				target = Sex.getTargetedPartner(characterOrgasming);
				targetAreaClothingCummedOn = getClothingCummedOn(target, CoverableArea.BACK);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, CoverableArea.BACK, targetAreaClothingCummedOn);
					
				} else {
					if(!target.isPlayer()) {
						if(characterOrgasming.isPlayer()) {
							return UtilText.parse(target,
									" all over [npc.namePos] back."
									+ " You grin as your [pc.cum+] splatters onto [npc.herHim], and [npc.she] can't help but let out [npc.a_moan] as [npc.she] feels it running down over [npc.her] [npc.skin].");
						} else {
							return UtilText.parse(characterOrgasming, target,
									" all over [npc2.namePos] back."
									+ " [npc.Name] grins as [npc.her] [npc.cum+] splatters onto [npc2.name],"
										+ " and [npc2.she] can't help but let out [npc2.a_moan] as [npc2.she] feels it running down over [npc2.her] [npc2.skin].");
							
						}
					} else {
						return UtilText.parse(characterOrgasming,
								" all over your back."
								+ " [npc.Name] grins as [npc.her] [npc.cum+] splatters onto you, and you can't help but let out [pc.a_moan] as you feel it running down over your [pc.skin].");
					}
				}
			case BREASTS:
				target = Sex.getTargetedPartner(characterOrgasming);
				targetAreaClothingCummedOn = getClothingCummedOn(target, CoverableArea.BREASTS);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, CoverableArea.BREASTS, targetAreaClothingCummedOn);
					
				} else {
					if(!target.isPlayer()) {
						if(characterOrgasming.isPlayer()) {
							return UtilText.parse(target,
									" all over [npc.namePos] [npc.breasts]."
									+ " You grin as your [pc.cum+] splatters onto [npc.herHim], and [npc.she] can't help but let out [npc.a_moan] as [npc.she] feels it running down over [npc.her] [npc.breastsSkin].");
						} else {
							return UtilText.parse(characterOrgasming, target,
									" all over [npc2.namePos] [npc2.breasts]."
									+ " [npc.Name] grins as [npc.her] [npc.cum+] splatters onto [npc2.name],"
										+ " and [npc2.she] can't help but let out [npc2.a_moan] as [npc2.she] feels it running down over [npc2.her] [npc2.breastsSkin].");
							
						}
					} else {
						return UtilText.parse(characterOrgasming,
								" all over your [pc.breasts]."
								+ " [npc.Name] grins as [npc.her] [npc.cum+] splatters onto you, and you can't help but let out [pc.a_moan] as you feel it running down over your [pc.breastsSkin].");
					}
				}
			case FACE:
				target = Sex.getTargetedPartner(characterOrgasming);
				targetAreaClothingCummedOn = getClothingCummedOn(target, CoverableArea.MOUTH);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, CoverableArea.MOUTH, targetAreaClothingCummedOn);
					
				} else {
					if(!target.isPlayer()) {
						if(characterOrgasming.isPlayer()) {
							return UtilText.parse(target,
									" all over [npc.namePos] [npc.face+]."
									+ " You grin as your [pc.cum+] splatters onto [npc.herHim], and [npc.she] can't help but let out [npc.a_moan] as [npc.she] feels it running down over [npc.her] [npc.faceSkin].");
						} else {
							return UtilText.parse(characterOrgasming, target,
									" all over [npc2.namePos] [npc2.face+]."
									+ " [npc.Name] grins as [npc.her] [npc.cum+] splatters onto [npc2.name],"
										+ " and [npc2.she] can't help but let out [npc2.a_moan] as [npc2.she] feels it running down over [npc2.her] [npc2.faceSkin].");
							
						}
					} else {
						return UtilText.parse(characterOrgasming,
								" all over your [pc.face+]."
								+ " [npc.Name] grins as [npc.her] [npc.cum+] splatters onto you, and you can't help but let out [pc.a_moan] as you feel it running down over your [pc.faceSkin].");
					}
				}
			case FLOOR:
				return " out all over the floor.";
			case STOMACH:
				target = Sex.getTargetedPartner(characterOrgasming);
				targetAreaClothingCummedOn = getClothingCummedOn(target, CoverableArea.STOMACH);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, CoverableArea.STOMACH, targetAreaClothingCummedOn);
					
				} else {
					if(!target.isPlayer()) {
						if(characterOrgasming.isPlayer()) {
							return UtilText.parse(target,
									" all over [npc.namePos] stomach."
									+ " You grin as your [pc.cum+] splatters onto [npc.herHim], and [npc.she] can't help but let out [npc.a_moan] as [npc.she] feels it running down over [npc.her] [npc.skin].");
						} else {
							return UtilText.parse(characterOrgasming, target,
									" all over [npc2.namePos] stomach."
									+ " [npc.Name] grins as [npc.her] [npc.cum+] splatters onto [npc2.name],"
										+ " and [npc2.she] can't help but let out [npc2.a_moan] as [npc2.she] feels it running down over [npc2.her] [npc2.skin].");
							
						}
					} else {
						return UtilText.parse(characterOrgasming,
								" all over your stomach."
								+ " [npc.Name] grins as [npc.her] [npc.cum+] splatters onto you, and you can't help but let out [pc.a_moan] as you feel it running down over your [pc.skin].");
					}
				}
			case GROIN:
				target = Sex.getTargetedPartner(characterOrgasming);
				targetAreaClothingCummedOn = getClothingCummedOn(target, CoverableArea.PENIS); // PENIS and VAGINA cover the same areas
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, CoverableArea.PENIS, targetAreaClothingCummedOn);
					
				} else {
					String groinText = "groin.";
					if(target.hasPenisIgnoreDildo()) {
						if(target.hasVagina()) {
							if(target.getGenitalArrangement()==GenitalArrangement.CLOACA) {
								groinText = " [npc2.cock], [npc2.pussy], and [npc2.asshole].";
							} else {
								groinText = " [npc2.cock] and [npc2.pussy].";
							}
						} else {
							if(target.getGenitalArrangement()==GenitalArrangement.CLOACA) {
								groinText = " [npc2.cock] and [npc2.asshole].";
							} else {
								groinText = " [npc2.cock+].";
							}
						}
					} else if(target.hasVagina()) {
						if(target.getGenitalArrangement()==GenitalArrangement.CLOACA) {
							groinText = " [npc2.pussy] and [npc2.asshole].";
						} else {
							groinText = " [npc2.pussy+].";
						}
					} else {
						groinText = " genderless mound.";
					}
					
					if(!target.isPlayer()) {
						if(characterOrgasming.isPlayer()) {
							return UtilText.parse(characterOrgasming, target,
									" all over [npc2.namePos] "+groinText
									+ " You grin as your [npc.cum+] splatters onto [npc2.herHim], and [npc2.she] can't help but let out [npc2.a_moan] as [npc2.she] feels it running down over [npc2.her] groin.");
						} else {
							return UtilText.parse(characterOrgasming, target,
									" all over [npc2.namePos] "+groinText
									+ " [npc.Name] grins as [npc.her] [npc.cum+] splatters onto [npc2.name],"
										+ " and [npc2.she] can't help but let out [npc2.a_moan] as [npc2.she] feels it running down over [npc2.her] groin.");
							
						}
					} else {
						return UtilText.parse(characterOrgasming, target,
								" all over your "+groinText
								+ " [npc.Name] grins as [npc.her] [npc.cum+] splatters onto you, and you can't help but let out [npc2.a_moan] as you feel it running down over your groin.");
					}
				}
				
			case INSIDE:
			case INSIDE_SWITCH_DOUBLE:
				break;
				
			case HAIR:
				target = Sex.getTargetedPartner(characterOrgasming);
				targetAreaClothingCummedOn = getClothingCummedOn(target, CoverableArea.HAIR);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, CoverableArea.HAIR, targetAreaClothingCummedOn);
					
				} else {
					if(!target.isPlayer()) {
						if(characterOrgasming.isPlayer()) {
							return UtilText.parse(target,
									" all over [npc.namePos] head"+(target.getHairRawLengthValue()>0?" and [npc.hair]":"")+"."
									+ " You grin as your [pc.cum+] splatters onto [npc.herHim].");
						} else {
							return UtilText.parse(characterOrgasming, target,
									" all over [npc2.namePos] head"+(target.getHairRawLengthValue()>0?" and [npc2.hair]":"")+"."
									+ " [npc.Name] grins as [npc.her] [npc.cum+] splatters onto [npc2.herHim].");
							
						}
					} else {
						return UtilText.parse(characterOrgasming,
								" all over your head"+(target.getHairRawLengthValue()>0?" and [pc.hair]":"")+"."
								+ " [npc.Name] grins as [npc.her] [npc.cum+] splatters onto you.");
					}
				}
			case LEGS:
				target = Sex.getTargetedPartner(characterOrgasming);
				targetAreaClothingCummedOn = getClothingCummedOn(target, CoverableArea.LEGS);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, CoverableArea.LEGS, targetAreaClothingCummedOn);
					
				} else {
					if(!target.isPlayer()) {
						if(characterOrgasming.isPlayer()) {
							return UtilText.parse(target,
									" all over [npc.namePos] [npc.legs]."
									+ " You grin as your [pc.cum+] splatters onto [npc.herHim], and [npc.she] can't help but let out [npc.a_moan] as [npc.she] feels it running down over [npc.her] [npc.legsSkin].");
						} else {
							return UtilText.parse(characterOrgasming, target,
									" all over [npc2.namePos] [npc2.legs]."
									+ " [npc.Name] grins as [npc.her] [npc.cum+] splatters onto [npc2.name],"
										+ " and [npc2.she] can't help but let out [npc2.a_moan] as [npc2.she] feels it running down over [npc2.her] [npc2.legsSkin].");
							
						}
					} else {
						return UtilText.parse(characterOrgasming,
								" all over your [pc.legs]."
								+ " [npc.Name] grins as [npc.her] [npc.cum+] splatters onto you, and you can't help but let out [pc.a_moan] as you feel it running down over your [pc.legsSkin].");
					}
				}
			case FEET:
				target = Sex.getTargetedPartner(characterOrgasming);
				targetAreaClothingCummedOn = getClothingCummedOn(target, CoverableArea.FEET);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, CoverableArea.FEET, targetAreaClothingCummedOn);
					
				} else {
					if(!target.isPlayer()) {
						if(characterOrgasming.isPlayer()) {
							return UtilText.parse(target,
									" all over [npc.namePos] [npc.feet+]."
									+ " You grin as your [pc.cum+] splatters onto [npc.herHim], and [npc.she] can't help but let out [npc.a_moan] as [npc.she] feels it running down over [npc.her] [npc.toes+].");
						} else {
							return UtilText.parse(characterOrgasming, target,
									" all over [npc2.namePos] [npc2.feet+]."
									+ " [npc.Name] grins as [npc.her] [npc.cum+] splatters onto [npc2.name],"
										+ " and [npc2.she] can't help but let out [npc2.a_moan] as [npc2.she] feels it running down over [npc2.her] [npc2.toes+].");
							
						}
					} else {
						return UtilText.parse(characterOrgasming,
								" all over your [pc.feet+]."
								+ " [npc.Name] grins as [npc.her] [npc.cum+] splatters onto you, and you can't help but let out [pc.a_moan] as you feel it running down over your [pc.toes+].");
					}
				}
			case WALL:
				return " all up the wall.";
				
			case SELF_GROIN:
				targetAreaClothingCummedOn = getClothingCummedOn(characterOrgasming, CoverableArea.PENIS);
				targetAreaClothingCummedOn.addAll(getClothingCummedOn(characterOrgasming, CoverableArea.VAGINA));
				targetAreaClothingCummedOn = new ArrayList<>(new HashSet<>(targetAreaClothingCummedOn));
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, targetAreaClothingCummedOn);
						
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
				targetAreaClothingCummedOn = getClothingCummedOn(characterOrgasming, CoverableArea.STOMACH);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, targetAreaClothingCummedOn);
					
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
				targetAreaClothingCummedOn = getClothingCummedOn(characterOrgasming, CoverableArea.LEGS);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, targetAreaClothingCummedOn);
					
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
				targetAreaClothingCummedOn = getClothingCummedOn(characterOrgasming, CoverableArea.FEET);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, targetAreaClothingCummedOn);
					
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
				targetAreaClothingCummedOn = getClothingCummedOn(characterOrgasming, CoverableArea.BREASTS);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, targetAreaClothingCummedOn);
					
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
				targetAreaClothingCummedOn = getClothingCummedOn(characterOrgasming, CoverableArea.MOUTH);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, targetAreaClothingCummedOn);
					
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
				LilayasRoom.lilayasPanties.setDirty(null, true);
				return UtilText.parse(characterOrgasming,
						" directly into Lilaya's panties."
						+ " You can't help but let out [pc.a_moan+] as you watch your [pc.cum+] pool in the soft fabric,"
							+ " and you give your [pc.cock+] a few extra strokes as you imagine your demonic [lilaya.relation(pc)] blushing as she slides the cum-saturated underwear up over her hot pussy.");
		}
		
		// Continued description for cumming inside:
		
		SexAreaInterface areaContacted = Sex.getAllContactingSexAreas(characterOrgasming, SexAreaPenetration.PENIS).get(0);

		if(areaContacted.isOrifice()) {
			switch((SexAreaOrifice)areaContacted) {
				case ANUS:
					cumTargetSB.append(" deep into [npc2.namePos] [npc2.asshole+].");
					switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
						case SIX_EXTREME: case SEVEN_MONSTROUS:
							cumTargetSB.append(" After a few seconds, [npc2.name] [npc2.verb(realise)] that [npc.nameIs] not even close to stopping, and as [npc.her]"
									+" [npc.cum+] backs up and starts drooling out of [npc2.her] [npc2.asshole], [npc2.she] [npc2.verb(let)] out [npc2.a_moan+]."
											+ (targetArea!=OrgasmCumTarget.INSIDE_SWITCH_DOUBLE || isSecondaryCreampieTarget
													?" [npc.Name] [npc.verb(keep)] [npc.her] [npc.cock] hilted deep in [npc2.her] ass, [npc.moaning+] as [npc.she] [npc.verb(wait)] for [npc.her] [npc.balls] to run dry."
													:""));
							break;
						default:
							break;
					}
					if(Main.getProperties().hasValue(PropertyValue.inflationContent) && !target.isVisiblyPregnant()) {
						float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.ANUS)
								+ (targetArea==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE
									?characterOrgasming.getPenisRawOrgasmCumQuantity()/2
									:characterOrgasming.getPenisRawOrgasmCumQuantity());
						cumTargetSB.append(getInflationText(characterOrgasming, target, cumAmount));
					}
					break;
					
				case ASS:
					targetAreaClothingCummedOn = getClothingCummedOn(target, CoverableArea.ASS);
					if (!targetAreaClothingCummedOn.isEmpty()) {
						return getClothingCummedOnText(characterOrgasming, target, CoverableArea.ASS, targetAreaClothingCummedOn);
						
					} else {
						cumTargetSB.append(" all over [npc2.namePos] back and [npc2.ass+].");

						switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								cumTargetSB.append(" After a few seconds, [npc2.name] [npc2.verb(realise)] that [npc.nameIs] not even close to stopping, and after just a moment more,"
										+ " [npc2.her] [npc2.ass+] is absolutely drenched in [npc.cum+].");
								break;
							default:
								break;
						}
					}
					break;
					
				case BREAST:
					targetAreaClothingCummedOn = getClothingCummedOn(target, CoverableArea.BREASTS);
					if (!targetAreaClothingCummedOn.isEmpty()) {
						return getClothingCummedOnText(characterOrgasming, target, CoverableArea.BREASTS, targetAreaClothingCummedOn);
						
					} else {
						if(target.hasBreasts()) {
							cumTargetSB.append(" all over [npc2.namePos] [npc2.breasts+] and face.");
						} else {
							cumTargetSB.append(" all over [npc2.namePos] flat chest and face.");
						}
						switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								cumTargetSB.append(" After a few seconds, [npc2.name] [npc2.verb(realise)] that [npc.nameIs] not even close to stopping, and after just a moment more,"
										+ " [npc2.her] torso is absolutely drenched in [npc.cum+].");
								break;
							default:
								break;
						}
					}
					break;
					
				case BREAST_CROTCH:
					targetAreaClothingCummedOn = getClothingCummedOn(target, CoverableArea.BREASTS_CROTCH);
					if (!targetAreaClothingCummedOn.isEmpty()) {
						return getClothingCummedOnText(characterOrgasming, target, CoverableArea.BREASTS_CROTCH, targetAreaClothingCummedOn);
						
					} else {
						cumTargetSB.append(" all over [npc2.namePos] [npc2.crotchBoobs+] and groin.");
						switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								cumTargetSB.append(" After a few seconds, [npc2.name] [npc2.verb(realise)] that [npc.nameIs] not even close to stopping, and after just a moment more,"
										+ " [npc2.her] crotch and stomach is absolutely drenched in [npc.cum+].");
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
								cumTargetSB.append(" The unusual taste of [npc.namePos] beer-flavoured");
								break;
							case CHOCOLATE:
								cumTargetSB.append(" The sweet taste of [npc.namePos] chocolate-flavoured");
								break;
							case CUM:
								cumTargetSB.append(" The salty taste of");
								break;
							case GIRL_CUM:
								cumTargetSB.append(" The unusual taste of [npc.namePos] sweet");
								break;
							case HONEY:
								cumTargetSB.append(" The sweet taste of [npc.namePos] honey-flavoured");
								break;
							case MILK:
								cumTargetSB.append(" The unusual taste of [npc.namePos] milk-flavoured");
								break;
							case MINT:
								cumTargetSB.append(" The taste of [npc.namePos] mint-flavoured");
								break;
							case PINEAPPLE:
								cumTargetSB.append(" The sweet taste of [npc.namePos] pineapple-flavoured");
								break;
							case BUBBLEGUM:
								cumTargetSB.append(" The fruity taste of [npc.namePos] bubblegum-flavoured");
								break;
							case STRAWBERRY:
								cumTargetSB.append(" The sweet taste of [npc.namePos] strawberry-flavoured");
								break;
							case VANILLA:
								cumTargetSB.append(" The taste of [npc.namePos] vanilla-flavoured");
								break;
							case CHERRY:
								cumTargetSB.append(" The sweet taste of [npc.namePos] cherry-flavoured");
								break;
						}
						cumTargetSB.append(" cum rises up to hit your [npc2.tongue], and you");
						if(target.hasFetish(Fetish.FETISH_CUM_ADDICT) || Sex.getCharactersRequestingCreampie().contains(target)) {
							cumTargetSB.append(" " + UtilText.returnStringAtRandom("greedily","hungrily"));
						} else {
							cumTargetSB.append("'re left with no other option but to");
						}
						cumTargetSB.append(" gulp down as much of the");
						if(!characterOrgasming.getCumModifiers().isEmpty()) {
							switch(characterOrgasming.getCumModifiers().get(Util.random.nextInt(characterOrgasming.getCumModifiers().size()))) { //TODO specials for ALCOHOLIC & HALLUCINOGENIC
								case ADDICTIVE:
									cumTargetSB.append(" delicious, highly-addictive [npc.cum] as you possibly can.");
									break;
								case ALCOHOLIC:
									cumTargetSB.append(" alcoholic [npc.cum] as you possibly can.");
									break;
								case BUBBLING:
									cumTargetSB.append(" bubbling, fizzy [npc.cum] as you possibly can.");
									break;
								case HALLUCINOGENIC:
									cumTargetSB.append(" psychoactive [npc.cum] as you possibly can.");
									break;
								case MUSKY:
									cumTargetSB.append(" musky [npc.cum] as you possibly can.");
									break;
								case SLIMY:
									cumTargetSB.append(" slimy [npc.cum] as you possibly can.");
									break;
								case STICKY:
									cumTargetSB.append(" sticky [npc.cum] as you possibly can.");
									break;
								case VISCOUS:
									cumTargetSB.append(" thick, viscous [npc.cum] as you possibly can.");
									break;
								case MINERAL_OIL:
									cumTargetSB.append(" tasty [npc.cum] as you possibly can.");
									break;
							}
						} else {
							cumTargetSB.append(" [npc.cum] as you possibly can.");
						}
						
					} else {
						cumTargetSB.append(" deep down [npc2.namePos] throat.");
					}
					switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
						case SIX_EXTREME: case SEVEN_MONSTROUS:
							cumTargetSB.append(" After a few seconds, [npc2.name] [npc2.verb(realise)] that [npc.nameIs] not even close to stopping, and as [npc.her]"
									+" [npc.cum+] backs up and starts drooling out of the corners of [npc2.her] mouth, [npc2.she] [npc2.verb(let)] out a desperate, muffled [npc2.moan]."
											+ " [npc.Name] [npc.verb(keep)] [npc.her] [npc.cock] hilted deep down [npc2.her] throat, [npc.moaning+] as [npc.she] [npc.verb(wait)] for [npc.her] [npc.balls] to run dry.");
							break;
						default:
							break;
					}
					if(Main.getProperties().hasValue(PropertyValue.inflationContent) && !target.isVisiblyPregnant()) {
						float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.MOUTH) + characterOrgasming.getPenisRawOrgasmCumQuantity();
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
						float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.NIPPLE) + characterOrgasming.getPenisRawOrgasmCumQuantity();
						cumTargetSB.append(getBreastInflationText(characterOrgasming, target, cumAmount));
					}
					break;
					
				case NIPPLE_CROTCH:
					cumTargetSB.append(" deep into [npc2.namePos] [npc2.crotchBoobs+].");
					
					if(Main.getProperties().hasValue(PropertyValue.inflationContent)) {
						float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.NIPPLE_CROTCH) + characterOrgasming.getPenisRawOrgasmCumQuantity();
						cumTargetSB.append(getBreastCrotchInflationText(characterOrgasming, target, cumAmount));
					}
					break;
					
				case THIGHS:
					targetAreaClothingCummedOn = getClothingCummedOn(target, CoverableArea.THIGHS);
					if (!targetAreaClothingCummedOn.isEmpty()) {
						return getClothingCummedOnText(characterOrgasming, target, CoverableArea.THIGHS, targetAreaClothingCummedOn);
						
					} else {
						cumTargetSB.append(" all over [npc2.namePos] thighs.");
						
						switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								cumTargetSB.append(" After a few seconds, [npc2.name] [npc2.verb(realise)] that [npc.nameIs] not even close to stopping, and after just a moment more,"
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
							cumTargetSB.append(" After a few seconds, [npc2.name] [npc2.verb(realise)] that [npc.nameIs] not even close to stopping, and as [npc.her]"
									+" [npc.cum+] backs up and starts drooling out of [npc2.her] urethra, [npc2.she] [npc2.verb(let)] out [npc2.a_moan+]."
											+ " [npc.Name] [npc.verb(keep)] [npc.her] [npc.cock] hilted inside of [npc2.herHim], [npc.moaning+] as [npc.she] [npc.verb(wait)] for [npc.her] [npc.balls] to run dry.");
							break;
						default:
							break;
					}
					if(Main.getProperties().hasValue(PropertyValue.inflationContent) && !target.isVisiblyPregnant()) {
						float cumAmount = target.getTotalFluidInArea((SexAreaOrifice) areaContacted) + characterOrgasming.getPenisRawOrgasmCumQuantity();
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
							cumTargetSB.append(" After a few seconds, [npc2.name] [npc2.verb(realise)] that [npc.nameIs] not even close to stopping, and as [npc.her]"
									+" [npc.cum+] backs up and starts drooling out of [npc2.her] [npc2.pussy], [npc2.she] [npc2.verb(let)] out [npc2.a_moan+]."
									+ (targetArea!=OrgasmCumTarget.INSIDE_SWITCH_DOUBLE || isSecondaryCreampieTarget
										?" [npc.Name] [npc.verb(keep)] [npc.her] [npc.cock] hilted deep in [npc2.her] [npc2.pussy], [npc.moaning+] as [npc.she] [npc.verb(wait)] for [npc.her] [npc.balls] to run dry."
										:""));
							break;
						default:
							break;
					}
					if(Main.getProperties().hasValue(PropertyValue.inflationContent) && !target.isVisiblyPregnant()) {
						float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.VAGINA)
								+ (targetArea==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE
									?characterOrgasming.getPenisRawOrgasmCumQuantity()/2
									:characterOrgasming.getPenisRawOrgasmCumQuantity());
						cumTargetSB.append(getInflationText(characterOrgasming, target, cumAmount));
					}
					break;
			}

			switch(target.getBodyMaterial()) {
				case AIR:
				case ARCANE:
				case WATER:
				case SLIME:
					cumTargetSB.append("<br/>"
							+ "As [npc2.namePos] body is made completely out of translucent "+target.getBodyMaterial().getName()+","
									+ " you're able to see the cloud of [npc.namePos] [npc.cum+] shooting up and dispersing inside of [npc2.herHim].");
					break;
				case FIRE:
				case FLESH:
				case ICE:
				case RUBBER:
				case STONE:
					break;
			}
			
		} else {
			switch((SexAreaPenetration)areaContacted) {
				case CLIT:
					break;
				case FINGER:
					targetAreaClothingCummedOn = getClothingCummedOn(target, CoverableArea.HANDS);
					if (!targetAreaClothingCummedOn.isEmpty()) {
						return getClothingCummedOnText(characterOrgasming, target, CoverableArea.HANDS, targetAreaClothingCummedOn);
						
					} else {
						cumTargetSB.append(" all over [npc2.namePos] [npc2.fingers+].");
						
						switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								cumTargetSB.append(" After a few seconds, [npc2.name] [npc2.verb(realise)] that [npc.nameIs] not even close to stopping, and after just a moment more,"
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
					targetAreaClothingCummedOn = getClothingCummedOn(target, CoverableArea.FEET);
					if (!targetAreaClothingCummedOn.isEmpty()) {
						return getClothingCummedOnText(characterOrgasming, target, CoverableArea.FEET, targetAreaClothingCummedOn);
						
					} else {
						cumTargetSB.append(" all over [npc2.namePos] [npc2.toes+].");
						
						switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								cumTargetSB.append(" After a few seconds, [npc2.name] [npc2.verb(realise)] that [npc.nameIs] not even close to stopping, and after just a moment more,"
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
		
		if(target!=null) {
			return UtilText.parse(characterOrgasming, target, cumTargetSB.toString());
			
		} else {
			return UtilText.parse(characterOrgasming, cumTargetSB.toString());
		}
	}
	
	private static List<AbstractClothing> getClothingCummedOn(GameCharacter target, CoverableArea area) {
		List<AbstractClothing> clothingList = new ArrayList<>();
		for(InventorySlot slot : area.getAssociatedInventorySlots(target)) {
			clothingList.addAll(target.getVisibleClothingConcealingSlot(slot));
		}
		return new ArrayList<>(new HashSet<>(clothingList));
	}

	private static List<InventorySlot> getNakedAreasCummedOn(GameCharacter target, CoverableArea area) {
		Set<InventorySlot> areaList = new HashSet<>();
		List<InventorySlot> slotsTargeted = area.getAssociatedInventorySlots(target);
		
		// Remove slots which don't make any sense to be cummed on:
		slotsTargeted.remove(InventorySlot.ANKLE); // This is covered by the SOCK slot
		slotsTargeted.remove(InventorySlot.TORSO_OVER); // This is covered by the TORSO_UNDER slot
		
		for(InventorySlot slot : slotsTargeted) {
			if(target.getVisibleClothingConcealingSlot(slot).isEmpty()) {
				areaList.add(slot);
			}
		}
		areaList.removeIf((covArea) -> !covArea.isPhysicallyAvailable(target));
		return new ArrayList<>(areaList);
	}
	
	private static String getClothingCummedOnText(GameCharacter characterOrgasming, GameCharacter target, CoverableArea area, List<AbstractClothing> clothing) {
		List<InventorySlot> nakedAreas = getNakedAreasCummedOn(target, area);
		
		if(nakedAreas.isEmpty()) {
			return UtilText.parse(characterOrgasming, target,
					" all over [npc2.namePos] "+Util.clothesToStringList(clothing, false)+"."
							+ " [npc.Name] [npc.verb(grin)] as [npc.her] [npc.cum+] splatters onto [npc2.namePos] clothing, making a mess of [npc2.her] outfit.");
		} else {
			return UtilText.parse(characterOrgasming, target,
					" all over [npc2.namePos] "+Util.clothesToStringList(clothing, false)+", as well as [npc2.her] exposed "+Util.inventorySlotsToParsedStringList(nakedAreas, target)+"."
							+ " [npc.Name] [npc.verb(grin)] as [npc.her] [npc.cum+] splatters onto [npc2.name], making a mess of [npc2.her] outfit.");
		}
	}

	private static String getClothingCummedOnText(GameCharacter characterOrgasming, List<AbstractClothing> clothing) {
			if(characterOrgasming.isPlayer()) {
				return UtilText.parse(characterOrgasming,
						" all over your "+Util.clothesToStringList(clothing, false)+"."
								+ " You let out [pc.a_moan+] as your [pc.cum+] splatters onto your clothing,"
								+ " making a mess of your outfit.");
			} else {
				return UtilText.parse(characterOrgasming,
						" all over [npc.her] "+Util.clothesToStringList(clothing, false)+"."
								+ " [npc.She] lets out [npc.a_moan+] as [npc.her] [npc.cum+] splatters onto"
								+ " [npc.her] clothing, making a mess of [npc.her] outfit.");
			}
	}
	
	private static String getInflationText(GameCharacter characterOrgasming, GameCharacter target, float cumAmount) {
		if(characterOrgasming.isPlayer()) {
			if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
				return ("<br/>You see [npc2.namePos] stomach swell out to a massive, over-inflated size as it distends from the colossal amount of cum that you've pumped inside of [npc2.herHim]."
						+ " Placing a [npc.hand] on [npc2.her] huge belly, you grin as you think to yourself that [npc2.she] now looks as though [npc2.sheIs] heavily pregnant.");
				
			} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
				return ("<br/>You see [npc2.namePos] stomach swell out as it distends from the huge amount of cum that you've pumped inside of [npc2.herHim]."
						+ " Placing a [npc.hand] on [npc2.her] belly, you grin as you think to yourself that [npc2.she] now looks as though [npc2.sheIs] pregnant.");
				
			} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
				return ("<br/>You see [npc2.namePos] stomach swell out a little as it distends from the sheer amount of cum that you've pumped inside of [npc2.herHim]."
						+ " Placing a [npc.hand] on [npc2.her] belly, you grin as you think to yourself that [npc2.she] now looks as though [npc2.sheIs] in the early stages of pregnancy.");
			}
		} else {
			if(target.isPlayer()) {
				if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
					return ("<br/>You feel your stomach swell out to a massive, over-inflated size as it distends from the colossal amount of cum that's been pumped inside of you."
							+ " Placing a [npc.hand] on your belly, [npc.name] grins as [npc.she] remarks on the fact that you now look as though you're heavily pregnant.");
					
				} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
					return ("<br/>You feel your stomach swell out a little as it distends from the huge amount of cum that's been pumped inside of you."
							+ " Placing a [npc.hand] on your belly, [npc.name] grins as [npc.she] remarks on the fact that you now look as though you're pregnant.");
					
				} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
					return ("<br/>You feel your stomach swell out a little as it distends from the sheer amount of cum that's been pumped inside of you."
							+ " Placing a [npc.hand] on your belly, [npc.name] grins as [npc.she] remarks on the fact that you now look as though you're in the early stages of pregnancy.");
				}
			} else {
				if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
					return ("<br/>[npc2.NamePos] stomach swells out to a massive, over-inflated size as it distends from the sheer amount of cum that [npc.namePos] pumped inside of [npc2.herHim]."
							+ " Placing a [npc.hand] on [npc2.her] belly, the [npc.race] grins as [npc.she] remarks on the fact that [npc2.name] now looks as though [npc2.sheIs] heavily pregnant.");
					
				} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
					return ("<br/>[npc2.NamePos] stomach swells out as it distends from the huge amount of cum that [npc.namePos] pumped inside of [npc2.herHim]."
							+ " Placing a [npc.hand] on [npc2.her] belly, the [npc.race] grins as [npc.she] remarks on the fact that [npc2.name] now looks as though [npc2.sheIs] pregnant.");
					
				} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
					return ("<br/>[npc2.NamePos] stomach swells out a little as it distends from the sheer amount of cum that [npc.namePos] pumped inside of [npc2.herHim]."
							+ " Placing a [npc.hand] on [npc2.her] belly, the [npc.race] grins as [npc.she] remarks on the fact that [npc2.name] now looks as though [npc2.sheIs] in the early stages of pregnancy.");
				}
			}
		}
		return "";
	}
	
	private static String getBreastInflationText(GameCharacter characterOrgasming, GameCharacter target, float cumAmount) {
		if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
			return ("<br/>[npc2.NamePos] [npc2.breasts] swell out to a massive, over-inflated size as they distend from the sheer amount of cum that [npc.nameHas] pumped inside of them.");
			
		} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
			return ("<br/>[npc2.NamePos] [npc2.breasts] swell out as they distend from the huge amount of cum that [npc.nameHas] pumped inside of them.");
			
		} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
			return ("<br/>[npc2.NamePos] [npc2.breasts] swell out a little as they distend from the sheer amount of cum that [npc.nameHas] pumped inside of them.");
		}
		return "";
	}
	
	private static String getBreastCrotchInflationText(GameCharacter characterOrgasming, GameCharacter target, float cumAmount) {
		if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
			return ("<br/>[npc2.NamePos] [npc2.crotchBoobs] swell out to a massive, over-inflated size as they distend from the sheer amount of cum that [npc.nameHas] pumped inside of them.");
			
		} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
			return ("<br/>[npc2.NamePos] [npc2.crotchBoobs] swell out as they distend from the huge amount of cum that [npc.nameHas] pumped inside of them.");
			
		} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
			return ("<br/>[npc2.NamePos] [npc2.crotchBoobs] swell out a little as they distend from the sheer amount of cum that [npc.nameHas] pumped inside of them.");
		}
		return "";
	}
	
	private static String getGenericVaginaOrgasmDescription(GameCharacter characterOrgasming, OrgasmCumTarget targetArea) {
		genericOrgasmSB.setLength(0);
		
		if(characterOrgasming.isPlayer()) {
			genericOrgasmSB.append("A desperate, shuddering heat suddenly crashes up from your [npc.pussy+], and you let out a manic squeal as a blinding wave of pure ecstasy washes over you.");
		} else {
			genericOrgasmSB.append("A desperate, shuddering heat suddenly crashes up from [npc.namePos] [npc.pussy+], and [npc.she] lets out a manic squeal as a blinding wave of pure ecstasy washes over [npc.herHim].");
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
							genericOrgasmSB.append(" You curl your fingers up deep inside your [npc.pussy+], and, while desperately stroking in a 'come-hither' motion,"
									+ " you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around your intruding digits.");
						} else {
							genericOrgasmSB.append(" [npc2.NamePos] fingers carry on pumping away at your [npc.pussy+], and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around the intruding digits.");
						}
					} else {
						if(characterPenetrating.isPlayer()) {
							genericOrgasmSB.append(" [npc.NamePos] vaginal muscles grip and squeeze around your intruding digits,"
									+ " and you continue to stroke and tease [npc.her] clit, drawing out a series of [npc.moans+] from between [npc.her] [npc.lips+].");
						} else if(characterOrgasming.equals(characterPenetrating)) {
							genericOrgasmSB.append(" [npc.NamePos] vaginal muscles grip and squeeze around [npc.her] intruding digits,"
									+ " and, driven on by the intense, pleasurable sensation, [npc.she] continues to stroke and tease [npc.her] clit, all the while letting out a series of [npc.moans+].");
						} else {
							genericOrgasmSB.append(" [npc.NamePos] vaginal muscles grip and squeeze around [npc2.namePos] intruding digits,"
									+ " and [npc2.she] continues to stroke and tease [npc.her] clit, drawing out a series of [npc.moans+] from between [npc.her] [npc.lips+].");
						}
					}
					break;
				case PENIS:
					if(characterOrgasming.isPlayer()) {
						if(characterPenetrating.isPlayer()) {
							genericOrgasmSB.append(" You carry on fucking yourself through your orgasm, letting out a series of high-pitched moans as your vaginal muscles grip and squeeze around your [npc.cock+].");
						} else {
							genericOrgasmSB.append(" [npc2.Name] carries on fucking your [npc.pussy+] through your orgasm, and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around [npc2.her] [npc2.penis+].");
						}
					} else {
						if(characterPenetrating.isPlayer()) {
							genericOrgasmSB.append(" You carry on fucking [npc.name] through [npc.her] orgasm, causing [npc.herHim] to let out a series of high-pitched moans as [npc.her] vaginal muscles grip and squeeze around your [npc2.cock+].");
						} else if(characterOrgasming.equals(characterPenetrating)) {
							genericOrgasmSB.append(" [npc.Name] carries on fucking [npc.her] own [npc.pussy+] through [npc.her] orgasm,"
									+ " causing [npc.herHim] to let out a series of high-pitched moans as [npc.her] vaginal muscles grip and squeeze around [npc.her] [npc.penis+].");
						} else {
							genericOrgasmSB.append(" [npc2.Name] carries on fucking [npc.namePos] [npc.pussy+] through [npc.her] orgasm,"
											+ " causing [npc.herHim] to let out a series of high-pitched moans as [npc.her] vaginal muscles grip and squeeze around [npc2.her] [npc2.penis+].");
						}
					}
					break;
				case TAIL:
					if(characterOrgasming.isPlayer()) {
						if(characterPenetrating.isPlayer()) {
							genericOrgasmSB.append(" You carry on tail-fucking yourself through your orgasm, letting out a series of high-pitched moans as your vaginal muscles grip and squeeze around the intruding object.");
						} else {
							genericOrgasmSB.append(" [npc2.Name] carries on tail-fucking your [npc.pussy+] through your orgasm, and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around the intruding object.");
						}
					} else {
						if(characterPenetrating.isPlayer()) {
							genericOrgasmSB.append(" You carry on tail-fucking [npc.name] through [npc.her] orgasm,"
											+ " causing [npc.herHim] to let out a series of high-pitched moans as [npc.her] vaginal muscles grip and squeeze around the intruding object.");
						} else if(characterOrgasming.equals(characterPenetrating)) {
							genericOrgasmSB.append(" [npc.Name] carries on tail-fucking [npc.her] own [npc.pussy+] through [npc.her] orgasm,"
									+ " causing [npc.herHim] to let out a series of high-pitched moans as [npc.her] vaginal muscles grip and squeeze around the intruding object.");
						} else {
							genericOrgasmSB.append(" [npc2.Name] carries on tail-fucking [npc.namePos] [npc.pussy+] through [npc.her] orgasm,"
											+ " causing [npc.herHim] to let out a series of high-pitched moans as [npc.her] vaginal muscles grip and squeeze around the intruding object.");
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
							genericOrgasmSB.append(" You carry on licking and kissing [npc.namePos] clit as [npc.she] orgasms,"
										+ " causing [npc.herHim] to let out a series of high-pitched moans as [npc.her] vaginal muscles quiver and contract at the overwhelming sensation.");
						} else if(characterOrgasming.equals(characterPenetrating)) {
							genericOrgasmSB.append(" [npc.Name] carries on licking and kissing [npc.her] own clit while [npc.she] orgasms,"
									+ " causing [npc.herHim] to let out a series of high-pitched moans as [npc.her] vaginal muscles quiver and contract at the overwhelming sensation.");
						} else {
							genericOrgasmSB.append(" [npc2.Name] carries on licking and kissing [npc.namePos] clit while [npc.she] orgasms,"
										+ " causing [npc.herHim] to let out a series of high-pitched moans as [npc.her] vaginal muscles quiver and contract at the overwhelming sensation.");
						}
					}
					break;
				case TENTACLE: //TODO
					break;
				case CLIT:
					if(characterOrgasming.isPlayer()) {
						if(characterPenetrating.isPlayer()) {
							genericOrgasmSB.append(" You carry on clit-fucking yourself through your orgasm, letting out a series of high-pitched moans as your vaginal muscles grip and squeeze around your [npc.clit+].");
						} else {
							genericOrgasmSB.append(" [npc2.Name] carries on clit-fucking your [npc.pussy+] through your orgasm, and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around [npc2.her] [npc2.clit+].");
						}
					} else {
						if(characterPenetrating.isPlayer()) {
							genericOrgasmSB.append(" You carry on clit-fucking [npc.name] through [npc.her] orgasm,"
									+ " causing [npc.herHim] to let out a series of high-pitched moans as [npc.her] vaginal muscles grip and squeeze around your [npc2.clit+].");
						} else if(characterOrgasming.equals(characterPenetrating)) {
							genericOrgasmSB.append(" [npc.Name] carries on clit-fucking [npc.her] own [npc.pussy+] through [npc.her] orgasm,"
									+ " causing [npc.herHim] to let out a series of high-pitched moans as [npc.her] vaginal muscles grip and squeeze around [npc.her] [npc.clit+].");
						} else {
							genericOrgasmSB.append(" [npc2.Name] carries on clit-fucking [npc.namePos] [npc.pussy+] through [npc.her] orgasm,"
											+ " causing [npc.herHim] to let out a series of high-pitched moans as [npc.her] vaginal muscles grip and squeeze around [npc2.her] [npc2.clit+].");
						}
					}
					break;
				case FOOT: //TODO
					break;
			}
			
		} else { // No penetration:
			boolean pluggedVagina = false;
			for(AbstractClothing c : characterOrgasming.getClothingCurrentlyEquipped()) {
				if(c.getItemTags().contains(ItemTag.PLUGS_VAGINA)) {
					pluggedVagina = true;
					genericOrgasmSB.append(" [npc.NamePos] [npc.pussy+] clenches down hard,"
							+ " causing [npc.herHim] to let out a series of high-pitched moans as [npc.her] vaginal muscles grip and squeeze around the "+c.getName()+" inserted into [npc.her] [npc.pussy].");
					break;
				}
			}
			if(!pluggedVagina) {
				genericOrgasmSB.append(" [npc.NamePos] [npc.pussy+] clenches down hard, and the wave of disappointment upon finding itself empty almost overwhelms the pleasure that radiates up through [npc.her] groin.");
			}
		}
		
		if(targetArea == OrgasmCumTarget.LILAYA_PANTIES && !Main.game.getPlayer().hasPenisIgnoreDildo()) {
			genericOrgasmSB.append(" As you squeal and pant, you bring Lilaya's panties up to your face, and breathe in your demonic [lilaya.relation(pc)]'s musky, perfume-laced scent as you imagine her pussy pressing against the soft fabric.");
		}
		
		if(characterOrgasming.isVaginaSquirter()) {
			genericOrgasmSB.append("<br/>As [npc.namePos] inner muscles spasm and quiver with delight, a huge spurt of female ejaculate squirts out from [npc.her] [npc.pussy+].");
			
			if(targetArea == OrgasmCumTarget.LILAYA_PANTIES) {
				genericOrgasmSB.append("<br/>You quickly drop Lilaya's panties down between your legs, squirting directly into her underwear as you let out [pc.a_moan+].");
				LilayasRoom.lilayasPanties.setDirty(null, true);
				
			} else {
				AbstractClothing vaginaClothing = Sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.VAGINA);
				if(vaginaClothing!=null) {
					if(!vaginaClothing.getItemTags().contains(ItemTag.PLUGS_VAGINA)
							&& !vaginaClothing.getItemTags().contains(ItemTag.SEALS_VAGINA)) {
						genericOrgasmSB.append(" [npc.She] [npc.verb(let)] out a deep sigh as [npc.she] [npc.verb(feel)] [npc.her] "
							+vaginaClothing.getName()+" "+(vaginaClothing.getClothingType().isPlural()?"are":"is")+" quickly getting dirtied by [npc.her] fluids.");
						
					} else {
						genericOrgasmSB.append(" As [npc.her] "+vaginaClothing.getName()+" "+(vaginaClothing.getClothingType().isPlural()?"are":"is")+" sealing [npc.her] [npc.pussy], nothing gets dirtied by [npc.her] fluids.");
					}
					vaginaClothing.setDirty(Sex.getCharacterPerformingAction(), true);
					
				} else {
					Set<GameCharacter> charactersEatingOut = new HashSet<>(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
					charactersEatingOut.addAll(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaOrifice.MOUTH));
					
					for(GameCharacter character : charactersEatingOut) { // Should only be one character
						genericOrgasmSB.append(UtilText.parse(characterOrgasming, character,
								" As [npc2.nameIsFull] eating [npc.herHim] out, [npc.namePos] fluids squirt out both into [npc2.her] mouth, as well as all over [npc2.her] [npc2.face]."));
					}
				}
			}
		}
		
		//TODO into underwear, or oral eating out
		
		genericOrgasmSB.append("<br/>With a deeply-satisfied sigh, [npc.namePos] feminine climax starts to fade, and [npc.she] [npc.verb(take)] a few deep gasps of air as [npc.she] [npc.verb(seek)] to catch [npc.her] breath.");
		
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
	
	public static String getGenericOrgasmDescription(SexActionInterface sexAction, GameCharacter characterOrgasming, OrgasmCumTarget target) {
		StringBuilder descriptionSB = new StringBuilder();

		GameCharacter characterPenetrated = null;
		
		if(!Sex.getAllContactingSexAreas(characterOrgasming, SexAreaPenetration.PENIS).isEmpty()) {
			characterPenetrated = Sex.getCharactersHavingOngoingActionWith(characterOrgasming, SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Sex.getAllContactingSexAreas(characterOrgasming, SexAreaPenetration.PENIS).get(0);
			
			List<GameCharacter> charactersPenetrated = new ArrayList<>(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, areaContacted));
			if(charactersPenetrated.contains(Sex.getTargetedPartner(characterOrgasming))) {
				characterPenetrated = Sex.getTargetedPartner(characterOrgasming);
			}
		}
		
		if(characterOrgasming.hasPenisIgnoreDildo()) {
			descriptionSB.append("<p>"
					+getGenericPenisOrgasmDescription(characterOrgasming, characterPenetrated, target, sexAction.getCondomFailure(characterOrgasming, characterPenetrated), false)
				+"</p>");
			
			if(target==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE) {
				GameCharacter secondaryTarget = getSecondaryCreampieTarget(characterPenetrated, (SexAreaOrifice) Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0));
				
				descriptionSB.append("<p>"
						+getGenericPenisOrgasmDescription(characterOrgasming, secondaryTarget, target, sexAction.getCondomFailure(characterOrgasming, characterPenetrated), true)
					+"</p>");
			}
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
			return Main.game.getPlayer().getSexActionOrgasmOverride(this, Sex.getAvailableCumTargets(Main.game.getPlayer()).get(0), false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Main.game.getPlayer().getSexActionOrgasmOverride(this, Sex.getAvailableCumTargets(Main.game.getPlayer()).get(0), true).applyEffects();
			if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Main.game.getPlayer().getPenisOrgasmCumQuantity() != CumProduction.ZERO_NONE) {
				Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(Main.game.getPlayer(), true);
			}
		}
		
		@Override
		public boolean endsSex() {
			return Main.game.getPlayer().getSexActionOrgasmOverride(this, Sex.getAvailableCumTargets(Main.game.getPlayer()).get(0), false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_CREAMPIE = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(!Sex.getCharacterPerformingAction().hasPenisIgnoreDildo()) {
				return false;
			}
			
			if(Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
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
					case BREAST_CROTCH:
					case NIPPLE:
					case NIPPLE_CROTCH:
					case ANUS:
					case MOUTH:
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
			
			// Will not use if obeying pull out requests:
			if((Sex.getSexManager().getCharacterOrgasmBehaviour(Sex.getCharacterPerformingAction())!=OrgasmBehaviour.CREAMPIE
					&& !Sex.getCharacterPerformingAction().isPlayer()
					&& Sex.getRequestedPulloutWeighting(Sex.getCharacterPerformingAction())>0)
				|| Sex.getSexManager().getCharacterOrgasmBehaviour(Sex.getCharacterPerformingAction())==OrgasmBehaviour.PULL_OUT) {
				return false;
			}
			
			return true;
		}
		
		@Override
		public SexActionPriority getPriority() {
			if(Sex.getSexManager().getCharacterOrgasmBehaviour(Sex.getCharacterPerformingAction())==OrgasmBehaviour.CREAMPIE) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Sex.getCreampieLockedBy()!=null) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Math.random()<0.66f
					|| Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_STUD).isPositive()
					|| Sex.getRequestedPulloutWeighting(Sex.getCharacterPerformingAction())<0
					|| (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0)==SexAreaOrifice.VAGINA
							&& Sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION))) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getCreampieLockedBy()!=null) {
				Class<? extends BodyPartInterface> bodypart = Sex.getCreampieLockedBy().getValue();
				if(bodypart == Skin.class) {
					return "Forced creampie!";
					
				} else if(bodypart == Arm.class) {
					return "Hug-locked!";
					
				} else if(bodypart == Leg.class) {
					return "Leg-locked!";
					
				} else if(bodypart == Tail.class) {
					return "Tail-locked!";
					
				} else if(bodypart == Wing.class) {
					return "Wing-locked!";
					
				} else if(bodypart == Tentacle.class) {
					return "Tentacle-locked!";
				}
			}
			
			GameCharacter characterPenetrated = Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ANUS:
						if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
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
					case BREAST_CROTCH:
						return "Paizuri climax";
					case MOUTH:
						return "Deepthroat";
					case NIPPLE: case NIPPLE_CROTCH:
						return "Nipple Creampie";
					case THIGHS:
						return "Thigh-sex Climax";
					case URETHRA_PENIS: case URETHRA_VAGINA:
						return "Urethra Creampie";
					case VAGINA:
						if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
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
			if(Sex.getCreampieLockedBy()!=null) {
				GameCharacter character = Sex.getCreampieLockedBy().getKey();
				Class<? extends BodyPartInterface> bodypart = Sex.getCreampieLockedBy().getValue();
				if(bodypart == Skin.class) {
					return UtilText.parse(character,
							"[npc.NameIsFull] using [npc.her] advantageous position to force you to cum inside of [npc.herHim]! As you're on the very brink of orgasm, you have no time to try and push [npc.herHim] away!");
					
				} else if(bodypart == Arm.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.arms+] around your lower body, thereby forcing you to cum inside of [npc.herHim]!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
					
				} else if(bodypart == Leg.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.legs+] around your lower body, thereby forcing you to cum inside of [npc.herHim]!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
					
				} else if(bodypart == Tail.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] "+(character.getTailCount()>1?"[npc.tails+]":"[npc.tail]")+" around your lower body, thereby forcing you to cum inside of [npc.herHim]!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
					
				} else if(bodypart == Wing.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.wingSize] [npc.wings] around your body, thereby forcing you to cum inside of [npc.herHim]!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
					
				} else if(bodypart == Tentacle.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.tentacles+] around your lower body, thereby forcing you to cum inside of [npc.herHim]!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
				}
			}
			
			GameCharacter characterPenetrated = Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			String returnString = "You've reached your climax, and can't hold back your orgasm any longer. Creampie [npc2.name].";
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ANUS:
						if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
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
					case BREAST_CROTCH:
						returnString = "Keep on fucking [npc2.namePos] [npc2.crotchBoobs] as you orgasm, spurting your [npc.cum+] all over [npc2.her] stomach, [npc2.crotchBoobs] and crotch.";
						break;
					case MOUTH:
						returnString = "Push your [npc.cock+] deep down [npc2.namePos] throat, before filling [npc2.her] stomach with your [npc.cum+].";
						break;
					case NIPPLE:
						returnString = "Push your [npc.cock+] deep into [npc2.namePos] [npc2.nipple+], before filling [npc2.her] breast with your [npc.cum+].";
						break;
					case NIPPLE_CROTCH:
						returnString = "Push your [npc.cock+] deep into [npc2.namePos] [npc2.crotchNipple+], before filling [npc2.her] [npc2.crotchBoob] with your [npc.cum+].";
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
						if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
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
		public String getDescription() {
			return Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Sex.setCreampieLockedBy(null); // Need this before effects, as effects can set locking (such as in Lyssieth's demon TF scenes)
			Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).applyEffects();
		}
		
		@Override
		public void applyEndEffects(){
			Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).applyEndEffects();
		}
		
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			SexAreaInterface areaContacted = Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter characterPenetrated = Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			
			if(Sex.getCreampieLockedBy()==null) { // If not locked, can choose who to cum inside:
				List<GameCharacter> charactersPenetrated = new ArrayList<>(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, areaContacted));
				if(charactersPenetrated.contains(Sex.getCharacterTargetedForSexAction(this))) {
					characterPenetrated = Sex.getCharacterTargetedForSexAction(this);
				}
			}
			
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
							if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA) {
								return Util.newArrayListOfValues(
										CoverableArea.ASS);
							} else {
								return Util.newArrayListOfValues(
										CoverableArea.ASS,
										CoverableArea.ANUS);
							}
						case BREAST:
							return Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.MOUTH);
						case BREAST_CROTCH:
							return Util.newArrayListOfValues(
									CoverableArea.BREASTS_CROTCH,
									CoverableArea.NIPPLES_CROTCH,
									CoverableArea.STOMACH,
									CoverableArea.PENIS,
									CoverableArea.VAGINA);
						case MOUTH:
							break;
						case NIPPLE:
							break;
						case NIPPLE_CROTCH:
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
					}
				}
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return  Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).isEndsSex()
					|| Sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY);
		}
	};
	
	private static Map<GameCharacter, SexSlot> getSuitableSecondaryCreampieTargets(GameCharacter targetedCharacter) {
		Map<GameCharacter, SexSlot> suitableSecondaryCharacters = null;
		if(Sex.isDom(Sex.getCharacterPerformingAction())) {
			suitableSecondaryCharacters = new HashMap<>(Sex.getSubmissiveParticipants(false));
		} else {
			suitableSecondaryCharacters = new HashMap<>(Sex.getDominantParticipants(false));
		}
		suitableSecondaryCharacters.remove(targetedCharacter);
		
		return suitableSecondaryCharacters;
	}
	
	private static GameCharacter getSecondaryCreampieTarget(GameCharacter targetedCharacter, SexAreaOrifice areaContacted) {
		Map<GameCharacter, SexSlot> suitableSecondaryCharacters = getSuitableSecondaryCreampieTargets(targetedCharacter);
		
		GameCharacter secondaryTarget = null;
		
		for(Entry<GameCharacter, SexSlot> entry : suitableSecondaryCharacters.entrySet()) {
//			System.out.println(entry.getKey().getName(true)+" check");
			if((areaContacted!=SexAreaOrifice.VAGINA || entry.getKey().hasVagina()) && Sex.isOrificeFree(entry.getKey(), areaContacted)) {
//				System.out.println(entry.getKey().getName(true)+" free");
				try {
					if(Sex.getPosition().getSexInteractions(Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()), Sex.getSexPositionSlot(entry.getKey())).getInteractions().get(SexAreaPenetration.PENIS).contains(areaContacted)) {
						secondaryTarget = entry.getKey();
						break;
					}
				} catch(Exception ex) {
					// getInteractions() map might not contain key SexAreaPenetration.PENIS
				}
			}
		}
		
		return secondaryTarget;
	}

	public static final SexAction GENERIC_ORGASM_DOUBLE_CREAMPIE = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(!Sex.getCharacterPerformingAction().hasPenisIgnoreDildo()) {
				return false;
			}
			
			if(Sex.getCharacterPerformingAction().getPenisCumStorage()==CumProduction.ZERO_NONE) {
				return false;
			}
			
			Map<GameCharacter, SexSlot> suitableSecondaryCharacters = getSuitableSecondaryCreampieTargets(Sex.getCharacterTargetedForSexAction(this));
			
			if(suitableSecondaryCharacters.isEmpty()) {
				return false;
			}
			
			if(Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return false;
			}
			
			SexAreaInterface areaContacted = Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			
			if(areaContacted.isPenetration()) {
				return false;
			}

			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			
			if(secondaryTarget==null) {
				return false;
			}
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ASS:
					case THIGHS:
					case BREAST:
					case BREAST_CROTCH:
					case NIPPLE:
					case NIPPLE_CROTCH:
					case MOUTH:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
						return false;
					case ANUS:
					case VAGINA:
						if(!secondaryTarget.isOrificeTypeExposed((SexAreaOrifice) areaContacted)
								|| !((SexAreaOrifice) areaContacted).isFree(secondaryTarget)) {
							return false;
						}
						break;
				}
			} else {
				return false;
			}
			
			// Will not use if obeying the player and player asked for pull out:
			if((Sex.getSexManager().getCharacterOrgasmBehaviour(Sex.getCharacterPerformingAction())!=OrgasmBehaviour.CREAMPIE
					&& !Sex.getCharacterPerformingAction().isPlayer()
					&& Sex.getRequestedPulloutWeighting(Sex.getCharacterPerformingAction())>0)
				|| Sex.getSexManager().getCharacterOrgasmBehaviour(Sex.getCharacterPerformingAction())==OrgasmBehaviour.PULL_OUT) {
				return false;
			}
			
			return true;
		}
		
		@Override
		public SexActionPriority getPriority() {
			if(Sex.getSexManager().getCharacterOrgasmBehaviour(Sex.getCharacterPerformingAction())==OrgasmBehaviour.CREAMPIE) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Math.random()<0.5f
					|| Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_STUD).isPositive()
					|| Sex.getRequestedPulloutWeighting(Sex.getCharacterPerformingAction())<0
					|| (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0)==SexAreaOrifice.VAGINA
							&& Sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION))) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		
		@Override
		public String getActionTitle() {
			SexAreaInterface areaContacted = Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ASS:
					case BREAST:
					case BREAST_CROTCH:
					case MOUTH:
					case NIPPLE: case NIPPLE_CROTCH:
					case THIGHS:
					case URETHRA_PENIS: case URETHRA_VAGINA:
						break;
					case ANUS:
						return "Double anal Creampie";
					case VAGINA:
						return "Double creampie";
				}
			}
			return "Double creampie";
		}

		@Override
		public String getActionDescription() {
			GameCharacter characterPenetrated = Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryCharacterPenetrated = getSecondaryCreampieTarget(Sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			
			String returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
					"You've reached your climax, and can't hold back your orgasm any longer. Give [npc.name] half of your creampie, before quickly pulling out and finishing inside of [npc2.name].");
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ASS:
					case BREAST:
					case BREAST_CROTCH:
					case MOUTH:
					case NIPPLE:
					case NIPPLE_CROTCH:
					case THIGHS:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
						break;
					case ANUS:
						if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
							returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
									"Keep your knot out of [npc.namePos] [npc.asshole] as you give [npc.herHim] half of your load,"
											+ " before quickly pulling out and ramming your knot into [npc2.namePos] [npc2.asshole], before finishing inside of [npc2.herHim].");
						} else {
							returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
									"You've reached your climax, and can't hold back your orgasm any longer. Spurt half of your load into [npc.namePos] [npc.asshole], before quickly pulling out and finishing inside of [npc2.namePos] [npc2.asshole+].");
						}
						break;
					case VAGINA:
						if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
							returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
									"Keep your knot out of [npc.namePos] [npc.pussy+] as you give [npc.herHim] half of your load,"
											+ " before quickly pulling out and ramming your knot into [npc2.namePos] [npc2.pussy+], before finishing inside of [npc2.herHim].");
						} else {
							returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
									"You've reached your climax, and can't hold back your orgasm any longer. Spurt half of your load into [npc.namePos] [npc.pussy+], before quickly pulling out and finishing inside of [npc2.namePos] [npc2.pussy+].");
						}
						break;
				}
			}
			return UtilText.parse(Sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}

		@Override
		public String getDescription() {
			return Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Sex.setCreampieLockedBy(null); // Need this before effects, as effects can set locking (such as in Lyssieth's demon TF scenes)
			Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, true).applyEffects();
		}
		
		@Override
		public void applyEndEffects(){
			SexAreaInterface areaContacted = Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);

			Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, true).applyEndEffects();
			
			Sex.stopOngoingAction(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Sex.getCharacterTargetedForSexAction(this), areaContacted);
			Sex.applyOngoingAction(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, secondaryTarget, areaContacted, true);
		}
		
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			GameCharacter characterPenetrated = Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			
			if(cumTarget.equals(characterPenetrated) || cumTarget.equals(secondaryTarget)) {
				return Util.newArrayListOfValues(areaContacted);
					
			} else {
				return null;
			}
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			SexAreaInterface areaContacted = Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			
			if(cumProvider.equals(Sex.getCharacterPerformingAction())
					&& (cumTarget.equals(Sex.getTargetedPartner(cumProvider)) || cumTarget.equals(secondaryTarget))) {
	
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
							if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA) {
								return Util.newArrayListOfValues(
										CoverableArea.ASS);
							} else {
								return Util.newArrayListOfValues(
										CoverableArea.ASS,
										CoverableArea.ANUS);
							}
						case BREAST:
							return Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.MOUTH);
						case BREAST_CROTCH:
							return Util.newArrayListOfValues(
									CoverableArea.BREASTS_CROTCH,
									CoverableArea.NIPPLES_CROTCH,
									CoverableArea.STOMACH,
									CoverableArea.PENIS,
									CoverableArea.VAGINA);
						case MOUTH:
							break;
						case NIPPLE:
							break;
						case NIPPLE_CROTCH:
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
					}
				}
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return  Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, true).isEndsSex()
					|| Sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY);
		}
	};
	
	
	public static final SexAction GENERIC_ORGASM_FLOOR = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(OrgasmCumTarget.FLOOR);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getOngoingCharactersUsingAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FLOOR, false).getDescription();
		}

		@Override
		public SexActionPriority getPriority() {
			if(!Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_STUD).isNegative()) {
				return SexActionPriority.LOW; // Prefer cumming on someone if they don't dislike cumming.
			}
			return super.getPriority();
		}
		
		@Override
		public void applyEffects() {
			Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FLOOR, true).applyEffects();
			GameCharacter characterPenetrated = null;
			SexAreaInterface areaContacted = null;
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				characterPenetrated = Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
				areaContacted = Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
				Sex.stopOngoingAction(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, characterPenetrated, areaContacted);
			}
		}
		
		@Override
		public boolean endsSex() {
			return Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FLOOR, true).isEndsSex();
		}
	};
	
	
	public static final SexAction GENERIC_ORGASM_WALL = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(OrgasmCumTarget.WALL);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getOngoingCharactersUsingAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.WALL, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.WALL, true).applyEffects();
			GENERIC_ORGASM_FLOOR.applyEffects();
		}
		
		@Override
		public boolean endsSex() {
			return  Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.WALL, true).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_ASS = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(OrgasmCumTarget.ASS);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getOngoingCharactersUsingAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.ASS, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.ASS, true).applyEffects();
			// Pull out:
			GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Sex.getCharacterPerformingAction()) && cumTarget.equals(Sex.getTargetedPartner(Sex.getCharacterPerformingAction()))) {
				if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA) {
					return Util.newArrayListOfValues(
							CoverableArea.ASS);
				} else {
					return Util.newArrayListOfValues(
							CoverableArea.ASS,
							CoverableArea.ANUS);
				}
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return  Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.ASS, true).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_GROIN = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(OrgasmCumTarget.GROIN);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getOngoingCharactersUsingAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, true).applyEffects();
			// Pull out:
			GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Sex.getCharacterPerformingAction()) && cumTarget.equals(Sex.getTargetedPartner(Sex.getCharacterPerformingAction()))) {
				if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA) {
					return Util.newArrayListOfValues(
							CoverableArea.ANUS,
							CoverableArea.PENIS,
							CoverableArea.VAGINA);
				} else {
					return Util.newArrayListOfValues(
							CoverableArea.PENIS,
							CoverableArea.VAGINA);
				}
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return  Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, true).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_SELF_GROIN = new SexAction(GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(OrgasmCumTarget.SELF_GROIN);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getOngoingCharactersUsingAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_GROIN, false).getDescription();
		}

		@Override
		public void applyEffects() {
			Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_GROIN, true).applyEffects();
			GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA) {
					return Util.newArrayListOfValues(
							CoverableArea.ANUS,
							CoverableArea.PENIS,
							CoverableArea.VAGINA);
				} else {
					return Util.newArrayListOfValues(
							CoverableArea.PENIS,
							CoverableArea.VAGINA);
				}
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return  Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_GROIN, true).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_BREASTS = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(OrgasmCumTarget.BREASTS);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getOngoingCharactersUsingAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.BREASTS, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.BREASTS, true).applyEffects();
			// Pull out:
			GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Sex.getCharacterPerformingAction()) && cumTarget.equals(Sex.getTargetedPartner(Sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(

						CoverableArea.NIPPLES,
						CoverableArea.BREASTS);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return  Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.BREASTS, true).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_SELF_BREASTS = new SexAction(GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(OrgasmCumTarget.SELF_BREASTS);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getOngoingCharactersUsingAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_BREASTS, false).getDescription();
		}

		@Override
		public void applyEffects() {
			Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_BREASTS, true).applyEffects();
			// Pull out:
			GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				return Util.newArrayListOfValues(
						CoverableArea.NIPPLES,
						CoverableArea.BREASTS);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return  Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_BREASTS, true).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_FACE = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(OrgasmCumTarget.FACE);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getOngoingCharactersUsingAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FACE, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FACE, true).applyEffects();
			// Pull out:
			GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Sex.getCharacterPerformingAction()) && cumTarget.equals(Sex.getTargetedPartner(Sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.MOUTH);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return  Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FACE, true).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_SELF_FACE = new SexAction(GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(OrgasmCumTarget.SELF_FACE);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getOngoingCharactersUsingAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_FACE, false).getDescription();
		}

		@Override
		public void applyEffects() {
			Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_FACE, true).applyEffects();
			// Pull out:
			GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				return Util.newArrayListOfValues(
						CoverableArea.MOUTH);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_FACE, true).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_HAIR = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(OrgasmCumTarget.HAIR);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getOngoingCharactersUsingAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.HAIR, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.HAIR, true).applyEffects();
			// Pull out:
			GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Sex.getCharacterPerformingAction()) && cumTarget.equals(Sex.getTargetedPartner(Sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.HAIR);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return  Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.HAIR, true).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_STOMACH = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(OrgasmCumTarget.STOMACH);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getOngoingCharactersUsingAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.STOMACH, false).getDescription();
		}

		@Override
		public void applyEffects() {
			Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.STOMACH, true).applyEffects();
			// Pull out:
			GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Sex.getCharacterPerformingAction()) && cumTarget.equals(Sex.getTargetedPartner(Sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.STOMACH);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return  Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.STOMACH, true).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_SELF_STOMACH = new SexAction(GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(OrgasmCumTarget.SELF_STOMACH);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getOngoingCharactersUsingAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_STOMACH, false).getDescription();
		}

		@Override
		public void applyEffects() {
			Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_STOMACH, true).applyEffects();
			// Pull out:
			GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				return Util.newArrayListOfValues(
						CoverableArea.STOMACH);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return  Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_STOMACH, true).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_LEGS = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(OrgasmCumTarget.LEGS);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getOngoingCharactersUsingAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.LEGS, false).getDescription();
		}

		@Override
		public void applyEffects() {
			Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.LEGS, true).applyEffects();
			// Pull out:
			GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Sex.getCharacterPerformingAction()) && cumTarget.equals(Sex.getTargetedPartner(Sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.LEGS);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return  Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.LEGS, true).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_SELF_LEGS = new SexAction(GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(OrgasmCumTarget.SELF_LEGS);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getOngoingCharactersUsingAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_LEGS, false).getDescription();
		}

		@Override
		public void applyEffects() {
			Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_LEGS, true).applyEffects();
			// Pull out:
			GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				return Util.newArrayListOfValues(
						CoverableArea.LEGS,
						CoverableArea.THIGHS);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return  Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_LEGS, true).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_FEET = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(OrgasmCumTarget.FEET);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getOngoingCharactersUsingAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FEET, false).getDescription();
		}

		@Override
		public void applyEffects() {
			Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FEET, true).applyEffects();
			// Pull out:
			GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Sex.getCharacterPerformingAction()) && cumTarget.equals(Sex.getTargetedPartner(Sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.FEET);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return  Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FEET, true).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_SELF_FEET = new SexAction(GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(OrgasmCumTarget.SELF_FEET);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getOngoingCharactersUsingAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_FEET, false).getDescription();
		}

		@Override
		public void applyEffects() {
			Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_FEET, true).applyEffects();
			// Pull out:
			GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				return Util.newArrayListOfValues(
						CoverableArea.FEET);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return  Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_FEET, true).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_BACK = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(OrgasmCumTarget.BACK);
		}
		
		@Override
		public String getActionTitle() {
			if(!Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Sex.getOngoingCharactersUsingAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.BACK, false).getDescription();
		}

		@Override
		public void applyEffects() {
			Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.BACK, true).applyEffects();
			// Pull out:
			GENERIC_ORGASM_FLOOR.applyEffects();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Sex.getCharacterPerformingAction()) && cumTarget.equals(Sex.getTargetedPartner(Sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.BACK);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return  Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.BACK, true).isEndsSex();
		}
	};
	
	
	
	// PLAYER:
	
	public static final SexAction GENERIC_PREPARATION_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !isTakingCock(Main.game.getPlayer(), Sex.getCharacterTargetedForSexAction(this));
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
			return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Prepare yourself for it.";
		}
		
		@Override
		public String getDescription() {
			String description = "";
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					description = "[npc.Name] [npc.verb(let)] out a soft [npc.moan] of encouragement as [npc.she] [npc.verb(prepare)] for [npc2.name] to reach [npc2.her] orgasm.";
					break;
				case DOM_NORMAL:
					description = "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] for [npc2.name] to reach [npc2.her] orgasm.";
					break;
				case DOM_ROUGH:
					description = "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] for [npc2.name] to reach [npc2.her] orgasm.";
					break;
				case SUB_EAGER:
					description = "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] for [npc2.name] to reach [npc2.her] orgasm.";
					break;
				case SUB_NORMAL:
					description = "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] for [npc2.name] to reach [npc2.her] orgasm.";
					break;
				case SUB_RESISTING:
					description = "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] desperately [npc.verb(try)] to pull away from [npc2.name] before [npc2.she] [npc2.verb(orgasm)].";
					break;
			}
			
			return UtilText.parse(Sex.getCharacterPerformingAction(), Sex.getCharacterOrgasming(), description);}
	};
	
	private static boolean isAreaFuckedByTarget(SexAction sexAction, GameCharacter characterFucked, SexAreaInterface areaFucked) {
		return Sex.getAllContactingSexAreas(characterFucked, areaFucked).contains(SexAreaPenetration.PENIS)
				&& Sex.getCharacterContactingSexArea(characterFucked, areaFucked).contains(Sex.getCharacterTargetedForSexAction(sexAction));
	}
	
	private static boolean isSpecialCreampieLockConditionMet(SexAction sexAction, GameCharacter characterProvidingCreampie, GameCharacter characterReceivingCreampie, SexAreaInterface areaFucked) {
		if(areaFucked==SexAreaOrifice.MOUTH) {
			return (PenisMouth.getPrimaryBlowjobPerformer(characterProvidingCreampie).equals(characterReceivingCreampie));
		}
		if(areaFucked==SexAreaOrifice.VAGINA) {
			if(!characterReceivingCreampie.isPlayer() && characterReceivingCreampie.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
				return false;
			}
		}
		return true;
	}
	
	public static final SexAction GENERIC_PREPARATION_ASK_FOR_CREAMPIE = new SexAction(
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
				
			} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
				return "Request cum";

			} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
				return "Request cum on [npc.breasts]";
				
			} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
				return "Request cum on [npc.feet]";
				
			} else {
				return "Request creampie";
			}
		}

		@Override
		public String getActionDescription() {
			if(!isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Ask [npc2.herHim] to keep fucking you with [npc2.her] dildo as [npc2.she] climaxes.";
				
			} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
				return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Ask [npc2.herHim] to fill your stomach with [npc2.her] cum.";

			} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
				return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Ask [npc2.herHim] to cum all over your [pc.breasts+].";
				
			} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
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
			if((Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Sex.getCharacterTargetedForSexAction(this))
					&& Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive()
					&& !Sex.getCharacterPerformingAction().isVisiblyPregnant())
				|| Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isPositive()) {
				return SexActionPriority.NORMAL;
			} else {
				return SexActionPriority.LOW;
			}
		}

		@Override
		public String getDescription() {
			if(Sex.getCharacterPerformingAction().isSpeechMuffled()) {
				if(!isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
					if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to keep pounding [npc.her] [npc.pussy+] as [npc2.she] [npc2.verb(orgasm)].";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to keep pounding [npc.her] [npc.asshole+] as [npc2.she] [npc2.verb(orgasm)].";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to keep pounding [npc.her] [npc.nipple+] as [npc2.she] [npc2.verb(orgasm)].";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to keep fucking [npc.her] [npc.breasts+] as [npc2.she] [npc2.verb(orgasm)].";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to keep fucking [npc.her] [npc.feet+] as [npc2.she] [npc2.verb(orgasm)].";
						
					} else {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to keep fucking [npc.herHim] as [npc2.she] [npc2.verb(orgasm)].";
					}
	
				} else {
					if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						return "Although not able to speak, [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to cum in [npc.her] [npc.pussy+].";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "Although not able to speak, [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to cum in [npc.her] [npc.asshole+].";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "Although not able to speak, [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to cum in [npc.her] [npc.nipple+].";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "Although not able to speak, [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to cum all over [npc.her] [npc.breasts+].";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "Although not able to speak, [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to cum all over [npc.her] [npc.feet+].";
						
					} else {
						return "Although not able to speak, [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.namePos] cum.";
					}
				}
				
			} else {
				if(!isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
					if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						return "Through [npc.her] desperate moans and lewd cries,"
									+ " [npc.name] somehow [npc.verb(manage)] to formulate a sentence and [npc.verb(cry)] out for [npc2.name] to keep pounding [npc.her] [npc.pussy+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Fuck! Yes! Keep fucking me!)]";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "Through [npc.her] desperate moans and lewd cries,"
								+ " [npc.name] somehow [npc.verb(manage)] to formulate a sentence and [npc.verb(cry)] out for [npc2.name] to keep pounding [npc.her] [npc.asshole+] as [npc2.she] [npc2.verb(orgasm)],"
							+" [npc.speech(Fuck! Yes! Keep fucking my ass!)]";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "Through [npc.her] desperate moans and lewd cries,"
								+ " [npc.name] somehow [npc.verb(manage)] to formulate a sentence and [npc.verb(cry)] out for [npc2.name] to keep pounding [npc.her] [npc.nipple+] as [npc2.she] [npc2.verb(orgasm)],"
							+" [npc.speech(Fuck! Yes! Keep fucking my [npc.nipple]!)]";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "Through [npc.her] desperate moans and lewd cries,"
								+ " [npc.name] somehow [npc.verb(manage)] to formulate a sentence and [npc.verb(cry)] out for [npc2.name] to keep fucking [npc.her] [npc.breasts+] as [npc2.she] [npc2.verb(orgasm)],"
							+" [npc.speech(Fuck! Yes! Keep fucking my tits!)]";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "Through [npc.her] desperate moans and lewd cries,"
								+ " [npc.name] somehow [npc.verb(manage)] to formulate a sentence and [npc.verb(cry)] out for [npc2.name] to keep fucking [npc.her] [npc.feet+] as [npc2.she] [npc2.verb(orgasm)],"
							+" [npc.speech(Fuck! Yes! Keep fucking my [npc.feet]!)]";
						
					} else {
						return "Through [npc.her] desperate moans and lewd cries,"
								+ " [npc.name] somehow [npc.verb(manage)] to formulate a sentence and [npc.verb(cry)] out for [npc2.name] to keep fucking [npc.herHim] as [npc2.she] [npc2.verb(orgasm)],"
							+" [npc.speech(Fuck! Yes! Keep fucking me!)]";
					}
	
				} else if(Sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
					boolean petName = false;
					if(!Sex.getCharacterPerformingAction().getPetName(Sex.getCharacterTargetedForSexAction(this)).equals(Sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}
					
					if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence and [npc.verb(cry)] out for [npc2.name] to cum,"
								+" [npc.speech(Finish inside of me if you want"+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]";
	
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence and [npc.verb(cry)] out for [npc2.name] to cum,"
								+ " [npc.speech(Finish inside of me if you want"+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]";
	
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence and [npc.verb(cry)] out for [npc2.name] to cum,"
								+ " [npc.speech(Finish inside of me if you want"+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]";
	
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence and [npc.verb(cry)] out for [npc2.name] to cum,"
								+ " [npc.speech(Yes! Cum for me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]";
	
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence and [npc.verb(cry)] out for [npc2.name] to cum,"
								+ " [npc.speech(Yes! Cum for me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]";
						
					} else {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence and [npc.verb(cry)] out for [npc2.name] to cum,"
								+ " [npc.speech(Yes! Cum for me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]";
					}
					
				} else {
					boolean petName = false;
					if(!Sex.getCharacterPerformingAction().getPetName(Sex.getCharacterTargetedForSexAction(this)).equals(Sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}
					
					if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out for [npc2.namePos] cum,"
								+(Sex.getCharacterPerformingAction().isVisiblyPregnant()
										?" [npc.speech(Fuck! Cum in me"+(petName?", [#npc.getPetName(npc2)]":"")+"! I need your cum!)]"
										:" [npc.speech(Breed me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Cum in me! I need your cum!)]");
	
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out for [npc2.namePos] cum,"
								+ " [npc.speech(Fuck! Cum in me"+(petName?", [#npc.getPetName(npc2)]":"")+"! I need your cum!)]";
	
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out for [npc2.namePos] cum,"
								+ " [npc.speech(Fuck! Cum in me"+(petName?", [#npc.getPetName(npc2)]":"")+"! I need your cum!)]";
	
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out for [npc2.namePos] cum,"
								+ " [npc.speech(Yes! Cum for me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Cover my tits with your cum!)]";
	
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out for [npc2.namePos] cum,"
								+" [npc.speech(Fuck! Yes! Cum all over my [npc.feet]"+(petName?", [#npc.getPetName(npc2)]":"")+"!)]";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out for [npc2.namePos] cum,"
								+ " [npc.speech(Cum for me"+(petName?", [#npc.getPetName(npc2)]":"")+"! I want to taste your cum!)]";
						
					} else {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out for [npc2.namePos] cum,"
								+ " [npc.speech(Cum for me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Don't pull out!)]";
					}
				}
			}
		}

		@Override
		public void applyEffects() {
			Sex.getCharactersRequestingCreampie().add(Sex.getCharacterPerformingAction());
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			List<Fetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Sex.getCharacterPerformingAction())) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if ((Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE).contains(Sex.getCharacterPerformingAction()))
					|| (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST).contains(SexAreaPenetration.PENIS)
							&& !Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST).contains(Sex.getCharacterPerformingAction()))) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_BREASTS_SELF);
				} else {
					fetishes.add(Fetish.FETISH_BREASTS_OTHERS);
				}
				
			} else if (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT).contains(Sex.getCharacterPerformingAction())) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_FOOT_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_FOOT_RECEIVING);
				}
			}
			return fetishes;
		}
	};
	
	private static SexActionPriority getBaseForceCreampiePriority(SexActionInterface sexAction) {
		if((Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
				&& Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Sex.getCharacterTargetedForSexAction(sexAction)))) {
			if(!Sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				if(Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive()) {
					return SexActionPriority.HIGH;
				}
				if(Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
					return SexActionPriority.LOW;
				}
			}
		}
		
		if(Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isPositive()) {
			return SexActionPriority.HIGH;
			
		} else {
			return SexActionPriority.LOW;
		}
	}
	
	private static String getForcedCreampieSpeech(SexAction sexAction) {
		boolean knowsName = Sex.getCharacterTargetedForSexAction(sexAction).isPlayer() || Sex.getCharacterTargetedForSexAction(sexAction).isPlayerKnowsName();
		
		if(isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(sexAction))) {
			if(isAreaFuckedByTarget(sexAction, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
				if(Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Sex.getCharacterPerformingAction().isVisiblyPregnant()) {
					return (Sex.getCharacterPerformingAction().isSpeechMuffled()
								?" With [npc.her] mouth being blocked, [npc.sheIs] only able to make a very muffled [npc.moan] as [npc.she] [npc.verb(prepare)] to receive [npc.her] creampie."
								:" With a hysterical squeal, [npc.she] [npc.verb(cry)] out, [npc.speechNoEffects(~Aah!~ Now I've got you! Cum in me"+(knowsName?", [npc2.name]":"")+"! ~Ooh!~ ~Yes!~ Give me your babies!)]");
				}
				return (Sex.getCharacterPerformingAction().isSpeechMuffled()
								?" With [npc.her] mouth being blocked, [npc.sheIs] only able to make a very muffled [npc.moan] as [npc.she] [npc.verb(prepare)] to receive [npc.her] creampie."
								:" With a desperate [npc.moan], [npc.she] [npc.verb(cry)] out, [npc.speechNoEffects(~Ooh!~ ~Yes!~ Cum in my pussy"+(knowsName?", [npc2.name]":"")+"! ~Aah!~ Give me a nice big creampie!)]");

			} else if(isAreaFuckedByTarget(sexAction, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
				return (Sex.getCharacterPerformingAction().isSpeechMuffled()
								?" With [npc.her] mouth being blocked, [npc.sheIs] only able to make a very muffled [npc.moan] as [npc.she] [npc.verb(prepare)] to receive [npc.her] creampie."
								:" With a desperate [npc.moan], [npc.she] [npc.verb(cry)] out, [npc.speechNoEffects(~Ooh!~ ~Yes!~ Cum in my ass"+(knowsName?", [npc2.name]":"")+"! ~Aah!~ Give me a nice big creampie!)]");
				
			} else if(isAreaFuckedByTarget(sexAction, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
				return (Sex.getCharacterPerformingAction().isSpeechMuffled()
								?" With [npc.her] mouth being blocked, [npc.sheIs] only able to make a very muffled [npc.moan] as [npc.she] [npc.verb(prepare)] to receive [npc.her] creampie."
								:" With a desperate [npc.moan], [npc.she] [npc.verb(cry)] out, [npc.speechNoEffects(~Ooh!~ ~Yes!~ Cum in me"+(knowsName?", [npc2.name]":"")+"! ~Aah!~ Fill my balls with your cum!)]");
				
			} else if(isAreaFuckedByTarget(sexAction, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
				return (Sex.getCharacterPerformingAction().isSpeechMuffled()
								?" With [npc.her] mouth being blocked, [npc.sheIs] only able to make a very muffled [npc.moan] as [npc.she] [npc.verb(prepare)] to receive [npc.her] creampie."
								:" With a desperate [npc.moan], [npc.she] [npc.verb(cry)] out, [npc.speechNoEffects(~Ooh!~ ~Yes!~ Cum in me"+(knowsName?", [npc2.name]":"")+"! ~Aah!~ Give me a nice big creampie!)]");
				
			}
			
		} else { // Dildo:
			if(isAreaFuckedByTarget(sexAction, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
				return (Sex.getCharacterPerformingAction().isSpeechMuffled()
								?" With [npc.her] mouth being blocked, [npc.sheIs] only able to make a very muffled [npc.moan] as [npc.she] [npc.verb(feel)] [npc.herself] being filled by [npc2.namePos] toy."
								:" With a desperate [npc.moan], [npc.she] [npc.verb(cry)] out, [npc.speechNoEffects(~Ooh!~ ~Yes!~ Fill my pussy with that toy"+(knowsName?", [npc2.name]":"")+"!)]");

			} else if(isAreaFuckedByTarget(sexAction, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
				return (Sex.getCharacterPerformingAction().isSpeechMuffled()
								?" With [npc.her] mouth being blocked, [npc.sheIs] only able to make a very muffled [npc.moan] as [npc.she] [npc.verb(feel)] [npc.herself] being filled by [npc2.namePos] toy."
								:" With a desperate [npc.moan], [npc.she] [npc.verb(cry)] out, [npc.speechNoEffects(~Ooh!~ ~Yes!~ Fill my ass with that toy"+(knowsName?", [npc2.name]":"")+"!)]");
				
			} else if(isAreaFuckedByTarget(sexAction, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
				return (Sex.getCharacterPerformingAction().isSpeechMuffled()
								?" With [npc.her] mouth being blocked, [npc.sheIs] only able to make a very muffled [npc.moan] as [npc.she] [npc.verb(feel)] [npc.herself] being filled by [npc2.namePos] toy."
								:" With a desperate [npc.moan], [npc.she] [npc.verb(cry)] out, [npc.speechNoEffects(~Ooh!~ ~Yes!~ Fill my cock with that toy"+(knowsName?", [npc2.name]":"")+"!)]");
				
			} else if(isAreaFuckedByTarget(sexAction, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
				return (Sex.getCharacterPerformingAction().isSpeechMuffled()
								?" With [npc.her] mouth being blocked, [npc.sheIs] only able to make a very muffled [npc.moan] as [npc.she] [npc.verb(feel)] [npc.herself] being filled by [npc2.namePos] toy."
								:" With a desperate [npc.moan], [npc.she] [npc.verb(cry)] out, [npc.speechNoEffects(~Ooh!~ ~Yes!~ Fill me with that toy"+(knowsName?", [npc2.name]":"")+"!)]");
			}
		}
		
		return "";
	}
	
	
	public static final SexAction GENERIC_PREPARATION_FORCE_CREAMPIE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Force creampie";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Use your advantageous position to force [npc2.herHim] to cum inside of you.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (isTakingCockInOrifice(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericGroinForceCreampieAreas)
						|| isTakingCockInOrifice(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericFaceForceCreampieAreas))
					&& Sex.getSexControl(Sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()
					&& isSpecialCreampieLockConditionMet(
							this,
							Sex.getCharacterTargetedForSexAction(this),
							Sex.getCharacterPerformingAction(),
							Sex.getContactingSexAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Sex.getCharacterPerformingAction()).get(0))
					&& Sex.getPosition().isForcedCreampieEnabled(
							Skin.class,
							(SexAreaOrifice) Sex.getContactingSexAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Sex.getCharacterPerformingAction()).get(0),
							Sex.getCharacterPerformingAction(),
							Sex.getCharacterTargetedForSexAction(this))
					&& (Sex.getCharacterPerformingAction().isPlayer() || Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING);
		}

		@Override
		public SexActionPriority getPriority() {
			return getBaseForceCreampiePriority(this);
		}

		@Override
		public String getDescription() {
			if(isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					if(Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Sex.getCharacterPerformingAction().isVisiblyPregnant()) {
						return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
								+ getForcedCreampieSpeech(this);
					}
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.her] [npc2.cock+] deep into [npc.her] cock's urethra."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.her] [npc2.cock+] deep into [npc.her] pussy's urethra."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.her] [npc2.cock+] deep down [npc.her] throat."
							+ " With a desperate, gurgling [npc.moan], [npc.she] [npc.verb(prepare)] to swallow all of the cum that's about to be pumped inside of [npc.herHim].";
				}
				return "Error: Forced creampie not accounted for. Please let Innoxia know!";
				
			} else { // Dildo:
				if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.her] [npc2.cock+] deep into [npc.her] cock's urethra."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.her] [npc2.cock+] deep into [npc.her] pussy's urethra."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.her] [npc2.cock+] deep down [npc.her] throat.";
				}
				return "Error: Forced creampie area not accounted for. Please let Innoxia know!";
			}
		}

		@Override
		public void applyEffects() {
			Sex.setCreampieLockedBy(new Value<>(Sex.getCharacterPerformingAction(), Skin.class));
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			List<Fetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Sex.getCharacterPerformingAction())) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(Sex.getCharacterPerformingAction())) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ORAL_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
				}
			}
			return fetishes;
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_HUG_LOCK = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Hug-lock";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Quickly wrap your [npc.arms] around [npc2.her] lower back, and, by tightly hugging [npc2.herHim] into you, force [npc2.herHim] to cum inside of you.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (isTakingCockInOrifice(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericGroinForceCreampieAreas)
						|| isTakingCockInOrifice(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericFaceForceCreampieAreas))
					&& Sex.getSexControl(Sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()
					&& !Sex.getCharacterPerformingAction().isArmMovementHindered()
					&& Sex.getPenetrationTypeFreeCount(Sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& isSpecialCreampieLockConditionMet(
							this,
							Sex.getCharacterTargetedForSexAction(this),
							Sex.getCharacterPerformingAction(),
							Sex.getContactingSexAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Sex.getCharacterPerformingAction()).get(0))
					&& Sex.getPosition().isForcedCreampieEnabled(
							Arm.class,
							(SexAreaOrifice) Sex.getContactingSexAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Sex.getCharacterPerformingAction()).get(0),
							Sex.getCharacterPerformingAction(),
							Sex.getCharacterTargetedForSexAction(this))
					&& (Sex.getCharacterPerformingAction().isPlayer() || Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING);
		}

		@Override
		public SexActionPriority getPriority() {
			return getBaseForceCreampiePriority(this);
		}

		@Override
		public String getDescription() {
			if(isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					if(Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Sex.getCharacterPerformingAction().isVisiblyPregnant()) {
						return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
								+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
								+ getForcedCreampieSpeech(this);
					}
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
								+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
								+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
								+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+]."
								+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
								+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] cock's urethra."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
								+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] pussy's urethra."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
								+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep down [npc.her] throat."
							+ " With a desperate, gurgling [npc.moan], [npc.she] [npc.verb(prepare)] to swallow all of the cum that's about to be pumped inside of [npc.herHim].";
				}
				return "Error: Hug-lock area not accounted for. Please let Innoxia know!";
				
			} else { // Dildo:
				if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
							+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
							+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
							+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] cock's urethra."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
							+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] pussy's urethra."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
								+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep down [npc.her] throat.";
				}
				return "Error: Hug-lock area not accounted for. Please let Innoxia know!";
			}
		}

		@Override
		public void applyEffects() {
			Sex.setCreampieLockedBy(new Value<>(Sex.getCharacterPerformingAction(), Arm.class));
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			List<Fetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Sex.getCharacterPerformingAction())) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(Sex.getCharacterPerformingAction())) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ORAL_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
				}
			}
			return fetishes;
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_LEG_LOCK = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Leg-lock";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Quickly wrap your [npc.legs] around [npc2.herHim] and force [npc2.herHim] to cum inside of you.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (isTakingCockInOrifice(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericGroinForceCreampieAreas)
						|| isTakingCockInOrifice(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericFaceForceCreampieAreas))
					&& Sex.getSexControl(Sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()
					&& !Sex.getCharacterPerformingAction().isLegMovementHindered()
					&& Sex.getPenetrationTypeFreeCount(Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)>=2
					&& isSpecialCreampieLockConditionMet(
							this,
							Sex.getCharacterTargetedForSexAction(this),
							Sex.getCharacterPerformingAction(),
							Sex.getContactingSexAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Sex.getCharacterPerformingAction()).get(0))
					&& Sex.getPosition().isForcedCreampieEnabled(
							Leg.class,
							(SexAreaOrifice) Sex.getContactingSexAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Sex.getCharacterPerformingAction()).get(0),
							Sex.getCharacterPerformingAction(),
							Sex.getCharacterTargetedForSexAction(this))
					&& (Sex.getCharacterPerformingAction().isPlayer() || Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING);
		}

		@Override
		public SexActionPriority getPriority() {
			return getBaseForceCreampiePriority(this);
		}

		@Override
		public String getDescription() {
			if(isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					if(Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Sex.getCharacterPerformingAction().isVisiblyPregnant()) {
						return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back,"
								+ " forcing [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
								+ getForcedCreampieSpeech(this);
					}
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back,"
								+ " forcing [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
								+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back,"
								+ " forcing [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+]."
								+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back,"
								+ " forcing [npc2.her] [npc2.cock+] deep into [npc.her] cock's urethra."
								+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back,"
								+ " forcing [npc2.her] [npc2.cock+] deep into [npc.her] pussy's urethra."
								+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) { // This shouldn't really ever be encountered:
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back, before letting out a desperate, muffled [npc.moan].";
				}
				return "Error: Leg-lock area not accounted for. Please let Innoxia know!";
				
			} else { // Dildo:
				if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to orgasm, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back,"
								+ " forcing [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to orgasm, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back,"
								+ " forcing [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to orgasm, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back,"
								+ " forcing [npc2.her] [npc2.cock+] deep into [npc.her] cock's urethra."
								+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to orgasm, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back,"
								+ " forcing [npc2.her] [npc2.cock+] deep into [npc.her] pussy's urethra."
								+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) { // This shouldn't really ever be encountered:
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to orgasm, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back, before letting out a desperate, muffled [npc.moan].";
				}
				return "Error: Leg-lock area not accounted for. Please let Innoxia know!";
			}
		}

		@Override
		public void applyEffects() {
			Sex.setCreampieLockedBy(new Value<>(Sex.getCharacterPerformingAction(), Leg.class));
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			List<Fetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Sex.getCharacterPerformingAction())) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
			} 
			return fetishes;
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_TAIL_LOCK = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Tail-lock";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm."
					+ " Quickly wrap your prehensile [npc.tail] around [npc2.her] lower back, and, by tightly pulling [npc2.herHim] into you, force [npc2.herHim] to cum inside of you.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (isTakingCockInOrifice(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericGroinForceCreampieAreas)
						|| isTakingCockInOrifice(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericFaceForceCreampieAreas))
					&& Sex.getSexControl(Sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()
					&& Sex.getCharacterPerformingAction().hasTail()
					&& Sex.getCharacterPerformingAction().getTailType().isPrehensile()
					&& Sex.getPenetrationTypeFreeCount(Sex.getCharacterPerformingAction(), SexAreaPenetration.TAIL)>=1
					&& isSpecialCreampieLockConditionMet(
							this,
							Sex.getCharacterTargetedForSexAction(this),
							Sex.getCharacterPerformingAction(),
							Sex.getContactingSexAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Sex.getCharacterPerformingAction()).get(0))
					&& Sex.getPosition().isForcedCreampieEnabled(
							Tail.class,
							(SexAreaOrifice) Sex.getContactingSexAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Sex.getCharacterPerformingAction()).get(0),
							Sex.getCharacterPerformingAction(),
							Sex.getCharacterTargetedForSexAction(this))
					&& (Sex.getCharacterPerformingAction().isPlayer() || Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING);
		}

		@Override
		public SexActionPriority getPriority() {
			return getBaseForceCreampiePriority(this);
		}

		@Override
		public String getDescription() {
			if(isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					if(Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Sex.getCharacterPerformingAction().isVisiblyPregnant()) {
						return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
								+ getForcedCreampieSpeech(this);
					}
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] cock's urethra."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] pussy's urethra."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep down [npc.her] throat."
							+ " With a desperate, gurgling [npc.moan], [npc.she] [npc.verb(prepare)] to swallow all of the cum that's about to be pumped inside of [npc.herHim].";
				}
				return "Error: Tail-lock area not accounted for. Please let Innoxia know!";
				
			} else { // Dildo:
				if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] cock's urethra."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] pussy's urethra."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep down [npc.her] throat.";
				}
				return "Error: Tail-lock area not accounted for. Please let Innoxia know!";
			}
		}

		@Override
		public void applyEffects() {
			Sex.setCreampieLockedBy(new Value<>(Sex.getCharacterPerformingAction(), Tail.class));
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			List<Fetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Sex.getCharacterPerformingAction())) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(Sex.getCharacterPerformingAction())) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ORAL_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
				}
			}
			return fetishes;
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_WING_LOCK = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Wing-lock";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm."
					+ " Quickly wrap your [npc.wingSize] [npc.wings] around [npc2.her] body, and, by tightly pulling [npc2.herHim] into you, force [npc2.herHim] to cum inside of you.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (isTakingCockInOrifice(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericGroinForceCreampieAreas)
						|| isTakingCockInOrifice(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericFaceForceCreampieAreas))
					&& Sex.getSexControl(Sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()
					&& Sex.getCharacterPerformingAction().hasWings()
					&& Sex.getCharacterPerformingAction().getWingSize().getValue()>=WingSize.THREE_LARGE.getValue()
					&& isSpecialCreampieLockConditionMet(
							this,
							Sex.getCharacterTargetedForSexAction(this),
							Sex.getCharacterPerformingAction(),
							Sex.getContactingSexAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Sex.getCharacterPerformingAction()).get(0))
					&& Sex.getPosition().isForcedCreampieEnabled(
							Wing.class,
							(SexAreaOrifice) Sex.getContactingSexAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Sex.getCharacterPerformingAction()).get(0),
							Sex.getCharacterPerformingAction(),
							Sex.getCharacterTargetedForSexAction(this))
					&& (Sex.getCharacterPerformingAction().isPlayer() || Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING);
		}

		@Override
		public SexActionPriority getPriority() {
			return getBaseForceCreampiePriority(this);
		}

		@Override
		public String getDescription() {
			if(isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					if(Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Sex.getCharacterPerformingAction().isVisiblyPregnant()) {
						return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
								+ getForcedCreampieSpeech(this);
					}
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] cock's urethra."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] pussy's urethra."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep down [npc.her] throat."
							+ " With a desperate, gurgling [npc.moan], [npc.she] [npc.verb(prepare)] to swallow all of the cum that's about to be pumped inside of [npc.herHim].";
				}
				return "Error: Tail-lock area not accounted for. Please let Innoxia know!";
				
			} else { // Dildo:
				if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] cock's urethra."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] pussy's urethra."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep down [npc.her] throat.";
				}
				return "Error: Tail-lock area not accounted for. Please let Innoxia know!";
			}
		}

		@Override
		public void applyEffects() {
			Sex.setCreampieLockedBy(new Value<>(Sex.getCharacterPerformingAction(), Wing.class));
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			List<Fetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Sex.getCharacterPerformingAction())) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(Sex.getCharacterPerformingAction())) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ORAL_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
				}
			}
			return fetishes;
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_TENTACLE_LOCK = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Tentacle-lock";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm."
					+ " Quickly wrap your [npc.tentacles] around [npc2.her] lower back, and, by tightly pulling [npc2.herHim] into you, force [npc2.herHim] to cum inside of you.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (isTakingCockInOrifice(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericGroinForceCreampieAreas)
						|| isTakingCockInOrifice(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericFaceForceCreampieAreas))
					&& Sex.getSexControl(Sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()
					&& Sex.getCharacterPerformingAction().hasTentacle()
					&& Sex.getPenetrationTypeFreeCount(Sex.getCharacterPerformingAction(), SexAreaPenetration.TENTACLE)>=1
					&& isSpecialCreampieLockConditionMet(
							this,
							Sex.getCharacterTargetedForSexAction(this),
							Sex.getCharacterPerformingAction(),
							Sex.getContactingSexAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Sex.getCharacterPerformingAction()).get(0))
					&& Sex.getPosition().isForcedCreampieEnabled(
							Tentacle.class,
							(SexAreaOrifice) Sex.getContactingSexAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Sex.getCharacterPerformingAction()).get(0),
							Sex.getCharacterPerformingAction(),
							Sex.getCharacterTargetedForSexAction(this))
					&& (Sex.getCharacterPerformingAction().isPlayer() || Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING);
		}

		@Override
		public SexActionPriority getPriority() {
			return getBaseForceCreampiePriority(this);
		}

		@Override
		public String getDescription() {
			if(isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					if(Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Sex.getCharacterPerformingAction().isVisiblyPregnant()) {
						return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
								+ getForcedCreampieSpeech(this);
					}
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] cock's urethra."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] pussy's urethra."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep down [npc.her] throat."
							+ " With a desperate, gurgling [npc.moan], [npc.she] [npc.verb(prepare)] to swallow all of the cum that's about to be pumped inside of [npc.herHim].";
				}
				return "Error: Tail-lock area not accounted for. Please let Innoxia know!";
				
			} else { // Dildo:
				if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] cock's urethra."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] pussy's urethra."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep down [npc.her] throat.";
				}
				return "Error: Tail-lock area not accounted for. Please let Innoxia know!";
			}
		}

		@Override
		public void applyEffects() {
			Sex.setCreampieLockedBy(new Value<>(Sex.getCharacterPerformingAction(), Tentacle.class));
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			List<Fetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Sex.getCharacterPerformingAction())) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(Sex.getCharacterPerformingAction())) {
				if(character.equals(Sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ORAL_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
				}
			}
			return fetishes;
		}
	};
	
	
	public static final SexAction GENERIC_PREPARATION_ASK_FOR_PULL_OUT = new SexAction(
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
				return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Ask [npc2.herHim] to pull [npc2.her] dildo out of you as [npc2.she] [npc2.verb(orgasm)].";
			}
			return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Ask [npc2.herHim] to pull out before [npc2.she] cums.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			if(Sex.getCharacterPerformingAction().isPlayer()) {
				return isTakingCock(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))
						&& !Sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
						&& !Sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY);
				
			} else {
				return isTakingCock(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))
						&& (Sex.getSexPace(Sex.getCharacterPerformingAction())==SexPace.SUB_RESISTING
							|| ((Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
									?!Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isPositive()
									:true)
							&& (Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
									?!Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive()
									:true)));
			}
		}

		@Override
		public SexActionPriority getPriority() {
			if((Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Sex.getCharacterTargetedForSexAction(this))
					&& (Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
						&& Sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo())
					&& !Sex.getCharacterPerformingAction().isVisiblyPregnant())
				|| Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isNegative()) {
				return SexActionPriority.HIGH;
			} else {
				return SexActionPriority.LOW;
			}
		}

		@Override
		public void applyEffects() {
			Sex.getCharactersRequestingPullout().add(Sex.getCharacterPerformingAction());
		}

		@Override
		public String getDescription() {
			if(Sex.getCharacterPerformingAction().isSpeechMuffled()) {
				if(!isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
					if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull out of [npc.her] [npc.pussy+].";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull out of [npc.her] [npc.asshole+].";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull out of [npc.her] [npc.nipple+].";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to get away from [npc.her] [npc.breasts+].";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to get away from [npc.her] [npc.feet+].";
						
					} else {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull out of [npc.herHim] as [npc2.she] [npc2.verb(orgasm)].";
					}
	
				} else {
					if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull out of [npc.her] [npc.pussy+] before [npc2.she] [npc2.verb(cum)].";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull out of [npc.her] [npc.asshole+] before [npc2.she] [npc2.verb(cum)].";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull out of [npc.her] [npc.nipple+] before [npc2.she] [npc2.verb(cum)].";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull away from [npc.her] [npc.breasts+] before [npc2.she] [npc2.verb(cum)].";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull away from [npc.her] [npc.feet+] before [npc2.she] [npc2.verb(cum)].";
						
					} else {
						return "Although not able to speak, [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.do]n't [npc.verb(want)] [npc2.namePos] cum.";
					}
				}
				
			} else {
				if(!isRealPenisFuckingCharacter(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
					if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)
							|| isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+"[npc.speech(Pull out! Get that dildo out of me!)]";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+"[npc.speech(Pull out! Get that dildo out of me!)]";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+"[npc.speech(Pull out! Get that dildo out of me!)]";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+"[npc.speech(Pull out! Get that dildo out of me!)]";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+"[npc.speech(Pull out! Get that dildo away from me!)]";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+"[npc.speech(Pull out! Get that dildo away from me!)]";
						
					} else {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to cry out around [npc2.namePos] [npc2.cock], "
								+ "[npc.speech(Pull out! Get that dildo out of my mouth!)]";
					}
					
				} else if(Sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
					if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Pull out! I don't want to risk that condom breaking in my pussy!)]";
	
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Pull out! I don't want to risk that condom breaking!)]";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Pull out! I don't want to risk that condom breaking!)]";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Pull out! I don't want that condom breaking in my ass!)]";
	
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Pull out! I don't want that condom breaking in my nipple!)]";
	
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Get back! I don't want that condom breaking all over my tits!)]";
	
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Get back! I don't want that condom breaking all over my [npc.feet]!)]";
						
					} else {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to cry out around [npc2.namePos] [npc2.cock], "
								+ "[npc.speech(Pull out! Please!)]";
					}
					
				} else {
					if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+(Sex.getCharacterPerformingAction().isVisiblyPregnant()
										|| Sex.getCharacterPerformingAction().hasStatusEffect(StatusEffect.MENOPAUSE)
										|| !Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
									?"[npc.speech(Pull out! I don't want you to cum in me!)]"
									:"[npc.speech(Pull out! I don't want to get pregnant!)]");
	
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Pull out! Don't cum in me, please!)]";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Pull out! Don't cum in me, please!)]";
						
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Pull out! Don't cum in my ass, please!)]";
	
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Pull out! Don't cum in my nipple! Don't do it!)]";
	
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Get back! Don't cum on my tits! Don't you dare!)]";
	
					} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Get back! Don't cum on my [npc.feet]! Don't you dare!)]";
						
					} else {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to cry out around [npc2.namePos] [npc2.cock], "
								+ "[npc.speech(Pull out! I don't want to taste your cum!)]";
					}
				}
			}
		}
	};
	
	public static final SexAction PLAYER_PREPARATION_ASK_FOR_NOTHING = new SexAction(
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
			Sex.getCharactersRequestingCreampie().remove(Sex.getCharacterPerformingAction());
			Sex.getCharactersRequestingPullout().remove(Sex.getCharacterPerformingAction());
		}

		@Override
		public String getDescription() {
			if(!isRealPenisFuckingCharacter(Main.game.getPlayer(), Sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "You continue [npc.moaning+] as [npc2.namePos] [npc2.cock+] continues to slam in and out of your [npc.pussy+], and you wonder if [npc2.sheIs] going to pull out or carry on fucking you through [npc2.her] orgasm.";

				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "You continue [npc.moaning+] as [npc2.namePos] [npc2.cock+] continues to slam in and out of your [npc.asshole+], and you wonder if [npc2.sheIs] going to pull out or carry on fucking you through [npc2.her] orgasm.";

				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
					return "You continue [npc.moaning+] as [npc2.namePos] [npc2.cock+] continues to slam in and out of your [npc.nipple+], and you wonder if [npc2.sheIs] going to pull out or carry on fucking you through [npc2.her] orgasm.";

				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
					if(Sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
						return "You continue [npc.moaning+] as [npc2.namePos] [npc2.cock+] continues to slide up and down between your [npc.breasts+],"
								+ " and you wonder if [npc2.sheIs] going to carry on fucking your [npc.breasts+] through [npc2.her] orgasm.";
					} else {
						return "You continue [npc.moaning+] as [npc2.namePos] [npc2.cock+] continues to slide up and down over your torso,"
								+ " and you wonder if [npc2.sheIs] going to back away or carry on fucking your flat chest through [npc2.her] orgasm.";
					}
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
					return "You continue [npc.moaning+] as you give [npc.name] [pc.a_footjob], and you wonder if [npc2.sheIs] going to back away or carry on fucking your [npc.feet] through [npc2.her] orgasm.";
					
				} else {
					return "You continue [npc.moaning+] as [npc2.name] continues to fuck you with [npc2.her] [npc2.cock+], and you wonder if [npc2.sheIs] going to pull out, or carry on fucking you through [npc2.her] orgasm.";
				}
				
			} else {
				if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "You continue [npc.moaning+] as you feel [npc2.namePos] [npc2.cock+] start to twitch inside your [npc.pussy+], and you wonder if [npc2.sheIs] going to pull out or"
							+(Main.game.getPlayer().isVisiblyPregnant()
									?" give you a fresh creampie..."
									:" fill your womb with [npc2.her] [npc2.cum+]...");

				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "You continue [npc.moaning+] as you feel [npc2.namePos] [npc2.cock+] start to twitch inside your [npc.asshole+], and you wonder if [npc2.sheIs] going to pull out or give you a fresh anal creampie...";

				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
					return "You continue [npc.moaning+] as you feel [npc2.namePos] [npc2.cock+] start to twitch inside your [npc.nipple+], and you wonder if [npc2.sheIs] going to pull out or give you a fresh nipple creampie...";

				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
					if(Sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
						return "You continue [npc.moaning+] as [npc2.namePos] [npc2.cock+] continues to slide up and down between your [npc.breasts+], and you wonder if [npc2.sheIs] going to pull out or cum all over your [npc.breasts+].";
					} else {
						return "You continue [npc.moaning+] as [npc2.namePos] [npc2.cock+] continues to slide up and down over your torso, and you wonder if [npc2.sheIs] going to back away or cum all over your flat chest.";
					}
					
				} else if(isAreaFuckedByTarget(this, Sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
					return "You continue [npc.moaning+] as you give [npc.name] [pc.a_footjob], and you wonder if [npc2.sheIs] going to pull out or cum all over your [npc.feet].";
					
				} else {
					return "You continue [npc.moaning+] as you feel [npc2.namePos] [npc2.cock+] start to twitch, and you wonder if [npc2.sheIs] going to pull out or fill you with [npc2.her] [npc2.cum+]...";
				}
			}
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_DENIAL = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.NEGATIVE_MAJOR,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexSlotGeneric.MISC_WATCHING) {
				return false;
			}
			if(Sex.isDom(Sex.getCharacterPerformingAction()) && !Sex.isCharacterDeniedOrgasm(Sex.getCharacterTargetedForSexAction(this))) {
				if(Sex.getCharacterPerformingAction().isPlayer()) {
					return true;
					
				} else {
					return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this)) // Doms will not deny other doms.
							&& Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_DENIAL).isPositive();
				}
			}
			return false;
		}
		
		@Override
		public SexActionPriority getPriority() {
			if(Sex.getCharacterPerformingAction().isPlayer() || !Sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_DENIAL)) {
				return SexActionPriority.LOW;
			}
			return SexActionPriority.HIGH;
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
		public String getDescription() {//TODO fetishes and player-specific descriptions
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getCharacterTargetedForSexAction(this).isSpeechMuffled()) {
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"From the increased intensity of [npc2.namePos] desperate, muffled whines and pants, it's clear to [npc.name] that [npc2.sheIs] about to orgasm.",
								"[npc2.Name] [npc2.verb(let)] out a particularly lewd, muffled whine, making it clear to [npc.name] that [npc2.sheIs] about to orgasm."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"From the increased intensity of [npc2.namePos] excited, muffled [npc2.moans], it's clear to [npc.name] that [npc2.sheIs] about to orgasm.",
								"[npc2.Name] [npc2.verb(let)] out a particularly erotic, muffled [npc2.moan], making it clear to [npc.name] that [npc2.sheIs] about to orgasm."));
						break;
				}
				
			} else {
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name] [npc2.verb(start)] desperately whining and panting, before blurting out, [npc2.speech(No! You're going to make me cum!)]",
								"[npc2.Name] [npc2.verb(let)] out a particularly lewd whine, before shuddering and crying out, [npc2.speech(Not like this! I'm going to cum! No!)]",
								"Letting out a surprisingly erotic scream, [npc2.name] [npc2.verb(exclaim)], [npc2.speech(Stop it! No! I'm... I'm going to cum!)]"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name] [npc2.verb(start)] desperately panting and [npc2.moaning], before blurting out, [npc2.speech(Yes! You're going to make me cum!)]",
								"[npc2.Name] [npc2.verb(let)] out a particularly erotic [npc2.moan], before excitedly exclaiming, [npc2.speech(Yes! I'm going to cum! Yes, give it to me!)]",
								"Letting out an incredibly erotic [npc2.moan], [npc2.name] [npc2.verb(exclaim)], [npc2.speech(Just like that! Yes! I'm... I'm going to cum!)]"));
						break;
				}
			}
			
			UtilText.nodeContentSB.append("<br/><br/>");
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Upon hearing this, [npc.name] quickly [npc.verb(take)] a firm grip of [npc2.namePos] [npc2.arms], before holding [npc2.herHim] in place and preventing [npc2.herHim] from stimulating [npc2.herself].",
							"As [npc.she] [npc.verb(hear)] this, [npc.name] quickly [npc.verb(grab)] hold of [npc2.namePos] [npc2.arms],"
									+ " before using [npc.her] leverage to hold [npc2.herHim] still and stop [npc2.herHim] from reaching [npc2.her] climax.",
							"Hearing that [npc2.sheIs] about to orgasm, [npc.name] quickly [npc.verb(grab)] hold of [npc2.namePos] [npc2.arms], before holding [npc2.herHim] still and preventing [npc2.herHim] from reaching [npc2.her] climax."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Upon hearing this, [npc.name] quickly [npc.verb(take)] a forceful grip of [npc2.namePos] [npc2.arms], before roughly holding [npc2.herHim] in place and preventing [npc2.herHim] from stimulating [npc2.herself].",
							"As [npc.she] [npc.verb(hear)] this, [npc.name] roughly [npc.verb(grab)] hold of [npc2.namePos] [npc2.arms],"
									+ " before using [npc.her] leverage to force [npc2.herHim] to stay still in order to stop [npc2.herHim] from reaching [npc2.her] climax.",
							"Hearing that [npc2.sheIs] about to orgasm, [npc.name] forcefully [npc.verb(grab)] hold of [npc2.namePos] [npc2.arms], before roughly holding [npc2.herHim] still and preventing [npc2.herHim] from reaching [npc2.her] climax."));
					break;
				default:
					break;
			}

			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					if(Sex.getCharacterTargetedForSexAction(this).isPlayer()) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You find yourself feeling a little relieved as you realise that you're not going be forced into orgasming,"
										+ " and simply continue to sob and plead to be left alone as [npc.name] [npc.verb(force)] you to calm down."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(seem)] a little relieved that [npc2.sheIs] not going be forced into orgasming,"
										+ " and simply [npc2.verb(continue)] to sob and plead to be left alone as [npc.name] [npc.verb(force)] [npc2.herHim] to calm down."));
					}
					break;
				default:
					if(Sex.getCharacterTargetedForSexAction(this).isSpeechMuffled()) {
						if(Sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_DENIAL_SELF).isPositive()) {
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" Being enamoured with the concept of being denied so close to [npc2.her] climax, [npc2.name] [npc2.verb(let)] out a lewd cry as [npc2.sheIs] forced to calm down,"
											+ " and as [npc2.she] [npc2.verb(withdraw)] from the edge of [npc2.her] orgasm, [npc2.her] erotic exclamation turns into a particularly desperate, muffled [npc2.moan]."));
							
						} else {
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" Feeling incredibly frustrated at being denied when so close to [npc2.her] climax, [npc2.name] [npc2.verb(let)] out a frantic, muffled cry as [npc2.sheIs] forced to calm down,"
											+ " making it clear that [npc2.she] desperately [npc2.verb(want)] to orgasm."));
						}
						
					} else {
						if(Sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_DENIAL_SELF).isPositive()) {
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" Being enamoured with the concept of being denied so close to [npc2.her] climax, [npc2.name] [npc2.verb(let)] out a lewd cry as [npc2.sheIs] forced to calm down,"
											+ " and as [npc2.she] [npc2.verb(withdraw)] from the edge of [npc2.her] orgasm, [npc2.she] [npc2.moansVerb], "));
	
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.speech(Oh yes... That feels good... I'll only cum when you allow me to, [npc.name]...)]",
									"[npc2.speech(Yes... I'm yours to deny as you wish, [npc.name]...)]"));
							
						} else {
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" Feeling incredibly frustrated at being denied when so close to [npc2.her] climax, [npc2.name] [npc2.verb(let)] out a frantic cry as [npc2.sheIs] forced to calm down, "));
	
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.speech(No! I was so close! Let me cum already!)]",
									"[npc2.speech(Let me cum! No! I was so close!)]"));
						}
					}
					break;
			}
			
			UtilText.nodeContentSB.append("<p style='text-align:center'>"
						+ "<i>[npc2.NamePos] orgasm was [style.boldBad(denied)]!</i>"
					+ "</p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public void applyEffects() {
			Sex.addCharacterDeniedOrgasm(Sex.getCharacterTargetedForSexAction(this));
			
			Sex.incrementNumberOfDeniedOrgasms(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), 1);
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
	
	public static final SexAction GENERIC_PREPARATION_ENCOURAGE_CREAMPIE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		private GameCharacter getCharacterBeingFucked() {
			List<GameCharacter> characters = Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
			if(characters.isEmpty()) {
				return null;
			}
			return characters.get(0);
		}
		
		@Override
		public String getActionTitle() {
			if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Sex.getCharacterTargetedForSexAction(this))) {
				return "Encourage fucking";
				
			} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH).contains(getCharacterBeingFucked())) {
				return "Encourage deepthroat";

			} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
				return "Encourage cum on [npc.breasts]";
				
			} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
				return "Encourage cum on [npc.feet]";
				
			} else {
				return "Encourage creampie";
			}
		}

		@Override
		public String getActionDescription() {
			if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Sex.getCharacterTargetedForSexAction(this))) {
				return UtilText.parse(getCharacterBeingFucked(), Sex.getCharacterTargetedForSexAction(this),
						"You can tell that [npc2.name] is fast approaching [npc2.her] orgasm. Encourage [npc2.herHim] to keep fucking [npc.name] with [npc2.her] dildo as [npc2.she] climaxes.");

			} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH).contains(getCharacterBeingFucked())) {
				return UtilText.parse(getCharacterBeingFucked(), Sex.getCharacterTargetedForSexAction(this),
						"You can tell that [npc2.name] is fast approaching [npc2.her] orgasm. Encourage [npc2.herHim] to fill [npc.namePos] stomach with [npc2.her] cum.");

			} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
				return UtilText.parse(getCharacterBeingFucked(), Sex.getCharacterTargetedForSexAction(this),
						"You can tell that [npc2.name] is fast approaching [npc2.her] orgasm. Encourage [npc2.herHim] to cum all over [npc.namePos] [npc.breasts+].");

			} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
				return UtilText.parse(getCharacterBeingFucked(), Sex.getCharacterTargetedForSexAction(this),
						"You can tell that [npc2.name] is fast approaching [npc2.her] orgasm. Encourage [npc2.herHim] to cum all over [npc.namePos] [npc.feet+].");
				
			} else {
				return UtilText.parse(getCharacterBeingFucked(), Sex.getCharacterTargetedForSexAction(this),
						"You can tell that [npc2.name] is fast approaching [npc2.her] orgasm. Encourage [npc2.herHim] to fill [npc.name] with [npc2.her] cum.");
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return getCharacterBeingFucked()!=null
					&& !isTakingCock(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))
					&& !Collections.disjoint(
							Util.newArrayListOfValues(SexAreaOrifice.VAGINA, SexAreaOrifice.ANUS, SexAreaOrifice.MOUTH, SexAreaOrifice.BREAST, SexAreaPenetration.FOOT),
							Sex.getContactingSexAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, getCharacterBeingFucked()))
					&& (Sex.getCharacterPerformingAction().isPlayer() || Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING);
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			if(Sex.getCharacterPerformingAction().isSpeechMuffled()) {
				if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Sex.getCharacterTargetedForSexAction(this))) {
					if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to keep pounding [npc3.namePos] [npc3.pussy+] as [npc2.she] [npc2.verb(orgasm)].");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to keep pounding [npc3.namePos] [npc3.asshole+] as [npc2.she] [npc2.verb(orgasm)].");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to keep pounding [npc3.namePos] [npc3.nipple+] as [npc2.she] [npc2.verb(orgasm)].");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to keep fucking [npc3.namePos] [npc3.breasts+] as [npc2.she] [npc2.verb(orgasm)].");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to keep fucking [npc3.namePos] [npc3.feet+] as [npc2.she] [npc2.verb(orgasm)].");
						
					} else {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to keep fucking [npc3.name] as [npc2.she] [npc2.verb(orgasm)].");
					}
	
				} else {
					if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to cum inside [npc3.namePos] [npc3.pussy+].");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to cum inside [npc3.namePos] [npc3.asshole+].");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to cum inside [npc3.namePos] [npc3.nipple+].");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to cum all over [npc3.namePos] [npc3.breasts+].");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to cum all over [npc3.namePos] [npc3.feet+].");
						
					} else {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to cum inside of [npc3.name].");
					}
				}
				
			} else {
				if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Sex.getCharacterTargetedForSexAction(this))) {
					if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.pussy+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Yes! That's right! Don't stop fucking [npc3.herHim]!)]");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.asshole+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Yes! That's right! Don't stop fucking [npc3.her] [npc3.ass]!)]");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.nipple+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Yes! That's right! Don't stop fucking [npc3.her] [npc3.breasts]!)]");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.breasts+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Yes! That's right! Don't stop fucking [npc3.her] tits!)]");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.feet+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Yes! That's right! Don't stop fucking [npc3.her] [npc3.feet]!)]");
						
					} else {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.name] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Yes! That's right! Don't stop fucking [npc3.herHim]!)]");
					}
	
				} else if(Sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
					boolean petName = false;
					if(!Sex.getCharacterPerformingAction().getPetName(Sex.getCharacterTargetedForSexAction(this)).equals(Sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}
					
					if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.pussy+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Finish inside of [npc3.herHim]"+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.asshole+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Finish inside of [npc3.herHim]"+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.nipple+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Finish inside of [npc3.herHim]"+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.breasts+] as [npc2.she] [npc2.verb(orgasm)],"
								+ " [npc.speech(That's it"+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.feet+] as [npc2.she] [npc2.verb(orgasm)],"
								+ " [npc.speech(That's it"+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]");
						
					} else {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.name] as [npc2.she] [npc2.verb(orgasm)],"
								+ " [npc.speech(That's it"+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]");
					}
					
				} else {
					boolean petName = false;
					if(!Sex.getCharacterPerformingAction().getPetName(Sex.getCharacterTargetedForSexAction(this)).equals(Sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}
	
					if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.pussy+] as [npc2.she] [npc2.verb(orgasm)],"
								+(getCharacterBeingFucked().isVisiblyPregnant()
										?" [npc.speech(Fuck! Cum in [npc3.herHim]"+(petName?", [#npc.getPetName(npc2)]":"")+"! Fill [npc3.her] pussy with your cum!)]"
										:" [npc.speech(Breed [npc3.herHim]"+(petName?", [#npc.getPetName(npc2)]":"")+"! Fill [npc3.her] pussy with your cum and knock [npc3.herHim] up!)]"));
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.asshole+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Fuck! Cum in [npc3.herHim]"+(petName?", [#npc.getPetName(npc2)]":"")+"! Fill [npc3.her] ass with your cum!)]");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.nipple+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Fuck! Cum in [npc3.herHim]"+(petName?", [#npc.getPetName(npc2)]":"")+"! Fill [npc3.her] nipple with your cum!)]");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.breasts+] as [npc2.she] [npc2.verb(orgasm)],"
								+ " [npc.speech(Yes! Cum for [npc3.herHim]"+(petName?", [#npc.getPetName(npc2)]":"")+"! Cover [npc3.her] tits with your cum!)]");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.feet+] as [npc2.she] [npc2.verb(orgasm)],"
								+ " [npc.speech(Yes! Cum for [npc3.herHim]"+(petName?", [#npc.getPetName(npc2)]":"")+"! Cover [npc3.her] [npc3.feet] with your cum!)]");
						
					} else {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.name] as [npc2.she] [npc2.verb(orgasm)],"
								+ " [npc.speech(Yes! Cum for [npc3.herHim]"+(petName?", [#npc.getPetName(npc2)]":"")+"! Don't pull out!)]");
					}
				}
			}
			
			if(!Sex.getCharacterTargetedForSexAction(this).isPlayer() && !Sex.isSpectator(Sex.getCharacterPerformingAction())) {
				if(Sex.getCharacterTargetedForSexAction(this).isSpeechMuffled()) {
					if(Sex.isCharacterObeyingTarget(Sex.getCharacterTargetedForSexAction(this), Sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+"Grinning as [npc.name] [npc.verb(ask)] this, [npc2.name] [npc2.verb(let)] out a positive-sounding [npc2.moan] in order to let [npc.name] know that that's exactly what [npc2.sheHasFull] planned.");
						
					} else {
						sb.append("<br/><br/>"
								+"Furrowing [npc2.her] eyebrows as [npc.name] [npc.verb(ask)] this,"
								+ " [npc2.name] [npc2.verb(let)] out a negative-sounding [npc2.moan] in order to let [npc.name] know that [npc2.sheIs] not interested in listening to anything [npc.sheHasFull] to say.");
					}
					
				} else {
					if(Sex.isCharacterObeyingTarget(Sex.getCharacterTargetedForSexAction(this), Sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"Grinning as [npc.name] [npc.verb(ask)] this, [npc2.name] quickly replies in the positive, ",
									"Letting out [npc.a_moan+] after hearing what it is [npc.nameIs] asking of [npc2.herHim], [npc2.name] responds, "));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(Sure, if that's what you want to see!)]",
								"[npc2.speech(Sounds good to me!)]",
								"[npc2.speech(Sure thing! I'm going to enjoy this!)]"));
						
					} else {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"Furrowing [npc2.her] eyebrows as [npc.name] [npc.verb(ask)] this, [npc2.name] quickly [npc2.verb(shut)] [npc.herHim] down, ",
									"Clearly not liking the fact that [npc.nameIs] asking something of [npc2.herHim], [npc2.name] responds, "));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(I'll do whatever I feel like doing!)]",
								"[npc2.speech(I'm not listening to you!)]",
								"[npc2.speech(I'll do what I want!)]"));
					}
				}
			}
			
			return UtilText.parse(
					Util.newArrayListOfValues(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked()),
					sb.toString());
		}

		@Override
		public void applyEffects() {
			Sex.getCharactersRequestingCreampie().add(Sex.getCharacterPerformingAction());
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			List<Fetish> fetishes = new ArrayList<>();
			fetishes.add(Fetish.FETISH_VOYEURIST);
			return fetishes;
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_ENCOURAGE_PULL_OUT = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private GameCharacter getCharacterBeingFucked() {
			List<GameCharacter> characters = Sex.getCharactersHavingOngoingActionWith(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
			if(characters.isEmpty()) {
				return null;
			}
			return characters.get(0);
		}
		
		@Override
		public String getActionTitle() {
			return "Encourage pullout";
		}

		@Override
		public String getActionDescription() {
			if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Sex.getCharacterTargetedForSexAction(this))) {
				return UtilText.parse(getCharacterBeingFucked(), Sex.getCharacterTargetedForSexAction(this),
						"You can tell that [npc2.name] is fast approaching [npc2.her] orgasm. Encourage [npc2.herHim] to pull [npc2.her] dildo out of [npc.name] as [npc2.she] [npc2.verb(orgasm)].");
			}
			return UtilText.parse(getCharacterBeingFucked(), Sex.getCharacterTargetedForSexAction(this),
					"You can tell that [npc2.name] is fast approaching [npc2.her] orgasm. Encourage [npc2.herHim] to pull out of [npc.name] as [npc2.she] [npc2.verb(orgasm)].");
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return getCharacterBeingFucked()!=null
					&& Sex.getCreampieLockedBy()==null
					&& !isTakingCock(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))
					&& !Collections.disjoint(
							Util.newArrayListOfValues(SexAreaOrifice.VAGINA, SexAreaOrifice.ANUS, SexAreaOrifice.MOUTH, SexAreaOrifice.BREAST, SexAreaPenetration.FOOT),
							Sex.getContactingSexAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, getCharacterBeingFucked()))
					&& (Sex.getCharacterPerformingAction().isPlayer() || Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING);
		}

//		@Override
//		public SexActionPriority getPriority() {
//			if((Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
//					&& Sex.getCharacterContactingSexArea(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Sex.getCharacterTargetedForSexAction(this))
//					&& (Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
//						&& Sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo())
//					&& !Sex.getCharacterPerformingAction().isVisiblyPregnant())
//				|| Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isNegative()) {
//				return SexActionPriority.HIGH;
//			} else {
//				return SexActionPriority.LOW;
//			}
//		}
		
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			if(Sex.getCharacterPerformingAction().isSpeechMuffled()) {
				if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Sex.getCharacterTargetedForSexAction(this))) {
					if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.namePos] [npc3.pussy+] through [npc2.her] orgasm, [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.namePos] [npc3.asshole+] through [npc2.her] orgasm, [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.namePos] [npc3.nipple+] through [npc2.her] orgasm, [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.namePos] [npc3.breasts+] through [npc2.her] orgasm, [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.namePos] [npc3.feet+] through [npc2.her] orgasm, [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
						
					} else {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.name] through [npc2.her] orgasm, [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
					}
	
				} else {
					if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to cum inside [npc3.namePos] [npc3.pussy+], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to cum inside [npc3.namePos] [npc3.asshole+], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to cum inside [npc3.namePos] [npc3.nipple+], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to cum all over [npc3.namePos] [npc3.breasts+], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to cum all over [npc3.namePos] [npc3.feet+], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
						
					} else {
						sb.append("Not wanting [npc2.name] to cum inside [npc3.name], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
					}
				}
				
			} else {
				if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Sex.getCharacterTargetedForSexAction(this))) {
					if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.name] through [npc2.her] orgasm, [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech([npc3.SheHas] had enough! Pull out of her pussy when you're going to cum!)]");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.name] through [npc2.her] orgasm, [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech([npc3.SheHas] had enough! Pull out of [npc3.her] ass when you're going to cum!)]");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.name] through [npc2.her] orgasm, [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech([npc3.SheHas] had enough! Pull out of [npc3.her] nipple when you're going to cum!)]");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.name] through [npc2.her] orgasm, [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech([npc3.SheHas] had enough! Get away from [npc3.her] tits when you're going to cum!)]");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.name] through [npc2.her] orgasm, [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech([npc3.SheHas] had enough! Get away from [npc3.her] feet when you're going to cum!)]");
						
					} else {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.name] through [npc2.her] orgasm, [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech([npc3.SheHas] had enough! Pull away from [npc3.herHim] when you're going to cum!)]");
					}
	
				} else if(Sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
					boolean petName = false;
					if(!Sex.getCharacterPerformingAction().getPetName(Sex.getCharacterTargetedForSexAction(this)).equals(Sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}
					
					if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.name] through [npc2.her] orgasm, [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech(Pull out of [npc3.herHim]"+(petName?", [#npc.getPetName(npc2)]":"")+"! I want to see your condom inflating!)]");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.asshole+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Pull out of [npc3.herHim]"+(petName?", [#npc.getPetName(npc2)]":"")+"! I want to see your condom inflating!)]");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.nipple+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Pull out of [npc3.herHim]"+(petName?", [#npc.getPetName(npc2)]":"")+"! I want to see your condom inflating!)]");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.breasts+] as [npc2.she] [npc2.verb(orgasm)],"
								+ " [npc.speech(Pull back"+(petName?", [#npc.getPetName(npc2)]":"")+"! I want to see your condom inflating!)]");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.feet+] as [npc2.she] [npc2.verb(orgasm)],"
								+ " [npc.speech(Pull back"+(petName?", [#npc.getPetName(npc2)]":"")+"! I want to see your condom inflating!)]");
						
					} else {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.name] as [npc2.she] [npc2.verb(orgasm)],"
								+ " [npc.speech(Pull back"+(petName?", [#npc.getPetName(npc2)]":"")+"! I want to see your condom inflating!)]");
					}
					
				} else {
					boolean petName = false;
					if(!Sex.getCharacterPerformingAction().getPetName(Sex.getCharacterTargetedForSexAction(this)).equals(Sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}
	
					if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to cum inside of [npc3.name], [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+(getCharacterBeingFucked().isVisiblyPregnant()
										?" [npc.speech(I don't want you cumming inside of [npc3.herHim]"+(petName?", [#npc.getPetName(npc2)]":"")+"! Pull out of [npc3.her] pussy!)]"
										:" [npc.speech(I don't want [npc3.herHim] getting pregnant"+(petName?", [#npc.getPetName(npc2)]":"")+"! Pull out of [npc3.her] pussy before you cum!)]"));
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to cum inside of [npc3.name], [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech(I don't want you cumming inside of [npc3.herHim]"+(petName?", [#npc.getPetName(npc2)]":"")+"! Pull out of [npc3.her] ass!)]");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to cum inside of [npc3.name], [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech(I don't want you cumming inside of [npc3.herHim]"+(petName?", [#npc.getPetName(npc2)]":"")+"! Pull out of [npc3.her] nipple!)]");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to cum all over of [npc3.name], [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech(I don't want you cumming on [npc3.herHim] tits"+(petName?", [#npc.getPetName(npc2)]":"")+"! Pull away from [npc3.herHim]!)]");
	
					} else if(Sex.getOngoingCharactersUsingAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to cum all over of [npc3.name], [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech(I don't want you cumming on [npc3.herHim] [npc3.feet]"+(petName?", [#npc.getPetName(npc2)]":"")+"! Pull away from [npc3.herHim]!)]");
						
					} else {
						sb.append("Not wanting [npc2.name] to cum all over of [npc3.name], [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech(I don't want you cumming on [npc3.herHim]"+(petName?", [#npc.getPetName(npc2)]":"")+"! Pull away from [npc3.herHim]!)]");
					}
				}
			}
			
			if(!Sex.getCharacterTargetedForSexAction(this).isPlayer() && !Sex.isSpectator(Sex.getCharacterPerformingAction())) {
				if(Sex.getCharacterTargetedForSexAction(this).isSpeechMuffled()) {
					if(Sex.isCharacterObeyingTarget(Sex.getCharacterTargetedForSexAction(this), Sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+"Grinning as [npc.name] [npc.verb(ask)] this, [npc2.name] [npc2.verb(let)] out a positive-sounding [npc2.moan] in order to let [npc.name] know that that's exactly what [npc2.sheHasFull] planned.");
						
					} else {
						sb.append("<br/><br/>"
								+"Furrowing [npc2.her] eyebrows as [npc.name] [npc.verb(ask)] this,"
								+ " [npc2.name] [npc2.verb(let)] out a negative-sounding [npc2.moan] in order to let [npc.name] know that [npc2.sheIs] not interested in listening to anything [npc.sheHasFull] to say.");
					}
					
				} else {
					if(Sex.isCharacterObeyingTarget(Sex.getCharacterTargetedForSexAction(this), Sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"Grinning as [npc.name] [npc.verb(ask)] this, [npc2.name] quickly replies in the positive, ",
									"Letting out [npc.a_moan+] after hearing what it is [npc.nameIs] asking of [npc2.herHim], [npc2.name] responds, "));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(Sure, if that's what you want to see!)]",
								"[npc2.speech(Sounds good to me!)]",
								"[npc2.speech(Sure thing! I'm going to enjoy this!)]"));
						
					} else {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"Furrowing [npc2.her] eyebrows as [npc.name] [npc.verb(ask)] this, [npc2.name] quickly [npc2.verb(shut)] [npc.herHim] down, ",
									"Clearly not liking the fact that [npc.nameIs] asking something of [npc2.herHim], [npc2.name] responds, "));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(I'll do whatever I feel like doing!)]",
								"[npc2.speech(I'm not listening to you!)]",
								"[npc2.speech(I'll do what I want!)]"));
					}
				}
			}
			
			return UtilText.parse(
					Util.newArrayListOfValues(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked()),
					sb.toString());
		}

		@Override
		public void applyEffects() {
			Sex.getCharactersRequestingPullout().add(Sex.getCharacterPerformingAction());
		}
	};
	
	
	// PARTNER
	
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
			return Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, Sex.getAvailableCumTargets(Sex.getCharacterPerformingAction()).get(0), false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, Sex.getAvailableCumTargets(Sex.getCharacterPerformingAction()).get(0), true).applyEffects();
			if (!Sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Sex.getCharacterPerformingAction().isWearingCondom()
					&& Sex.getCharacterPerformingAction().getPenisOrgasmCumQuantity() != CumProduction.ZERO_NONE) {
				Sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(Sex.getCharacterPerformingAction(), true);
			}
		}
		
		@Override
		public boolean endsSex() {
			return Sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, Sex.getAvailableCumTargets(Sex.getCharacterPerformingAction()).get(0), false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_DENIED = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.NEGATIVE_MAJOR,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isCharacterDeniedOrgasm(Sex.getCharacterPerformingAction());
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
		public String getDescription() {//TODO improve and take into account fetishes
			if(Sex.getCharacterPerformingAction().isSpeechMuffled()) {
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom("[npc.Name] [npc.verb(let)] out a distressed, muffled cry as [npc2.name] [npc2.verb(release)] [npc.herHim], making it clear that [npc.sheIs] having a bad time.");
					default:
						return UtilText.returnStringAtRandom("[npc.Name] [npc.verb(let)] out a dismayed, muffled cry as [npc2.name] [npc2.verb(release)] [npc.herHim], making it clear that [npc.sheIs] desperate to be allowed to orgasm.");
				}
				
			} else {
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom("[npc.speech(You've had your fun! Now leave me alone!)] [npc.name] [npc.verb(scream)] as [npc2.name] [npc2.verb(release)] [npc.herHim]. [npc.speech(D-Don't make me go through that again!)]");
					default:
						return UtilText.returnStringAtRandom("[npc.speech(No! I was so close!)] [npc.name] [npc.verb(wail)] in dismay as [npc2.name] [npc2.verb(release)] [npc.herHim]. [npc.speech(Let me cum next time!)]");
				}
			}
		}
		
		@Override
		public void applyEffects() {
			Sex.removeCharacterDeniedOrgasm(Sex.getCharacterPerformingAction());
			
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
