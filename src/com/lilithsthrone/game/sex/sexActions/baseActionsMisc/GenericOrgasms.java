package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Torso;
import com.lilithsthrone.game.character.body.Wing;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayasRoom;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.CondomFailure;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.OrgasmEncourageBehaviour;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.69
 * @version 0.3.7
 * @author Innoxia
 */
public class GenericOrgasms {
	
	public static boolean isTakingCock(GameCharacter character, GameCharacter penetrator) {
		return !Collections.disjoint(
				Main.sex.getOngoingSexAreas(penetrator, SexAreaPenetration.PENIS, character),
				Util.newArrayListOfValues(
						SexAreaOrifice.VAGINA,
						SexAreaOrifice.ANUS,
						SexAreaOrifice.NIPPLE,
						SexAreaOrifice.MOUTH,
						SexAreaOrifice.SPINNERET,
						SexAreaOrifice.URETHRA_PENIS,
						SexAreaOrifice.URETHRA_VAGINA,
						SexAreaOrifice.BREAST,
						SexAreaPenetration.FOOT));
	}
	
	private static boolean isTakingCockInOrifice(GameCharacter character, GameCharacter penetrator, List<SexAreaOrifice> orificesToCheck) {
		return !Collections.disjoint(
				Main.sex.getOngoingSexAreas(penetrator, SexAreaPenetration.PENIS, character),
				orificesToCheck);
	}
	
	private static boolean isRealPenisFuckingCharacter(GameCharacter character, GameCharacter penetrator) {
		return Main.sex.getCharacterOngoingSexArea(penetrator, SexAreaPenetration.PENIS).contains(character)
				&& penetrator.hasPenisIgnoreDildo();
	}

	public static boolean isCumTargetRequirementsMet(OrgasmCumTarget cumTarget) {
		OrgasmCumTarget preferredPulloutTarget = Main.sex.getInitialSexManager().getCharacterPullOutOrgasmCumTarget(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()));
		
		if(!Main.sex.getAvailableCumTargets(Main.sex.getCharacterPerformingAction()).contains(cumTarget)
				|| (Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotGeneric.MISC_WATCHING && cumTarget.isRequiresPartner())
				|| !Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
				|| !Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.PENIS)
				|| Main.sex.getCharacterPerformingAction().isWearingCondom()
				|| (!Main.sex.getCharacterPerformingAction().isPlayer() && Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<0)
				|| (preferredPulloutTarget!=null && preferredPulloutTarget!=cumTarget)) {
			return false;
		}
		
		return true;
	}
	
	private static String getPositionPreparation(GameCharacter characterOrgasming, GameCharacter characterTargeted) {
		if(characterTargeted!=null) {
			String orgasmText = Main.sex.getSexPositionSlot(characterOrgasming).getOrgasmDescription(characterOrgasming, characterTargeted);
			return UtilText.parse(characterOrgasming, characterTargeted, orgasmText);
			
		} else {
			String orgasmText = Main.sex.getSexPositionSlot(characterOrgasming).getOrgasmDescription(characterOrgasming, Main.sex.getTargetedPartner(characterOrgasming));
			return UtilText.parse(characterOrgasming, Main.sex.getTargetedPartner(characterOrgasming), orgasmText);
		}
	}
	
	private static String getAhegaoDescription(GameCharacter characterOrgasming, GameCharacter characterTargeted) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p>");
		if(characterOrgasming.isPlayer()) {
			sb.append("As [npc.name] [npc.verb(reach)] [npc.her] climax, [npc.she] can't help but [npc.verb(let)] out an extremely loud, shuddering [npc.moan]."
					+ " Within moments, your mind has gone blank, and as you lose [npc.herself] to the overwhelming strength of [npc.her] orgasm, you unwittingly [npc.verb(find)] [npc.herself] making an extremely lewd facial expression."
					+ " As [npc.her] [npc.tongue+] lolls out of [npc.her] mouth, [npc.she] [npc.verb(feel)] [npc.her] cheeks flushing, and can't stop [npc.herself] from rolling [npc.her] [npc.eyes] upwards."
					+ " With this expression of exaggerated ecstasy temporarily stuck on [npc.her] face, [npc.name] [npc.verb(let)] out yet another desperate [npc.moan] and [npc.verb(prepare)] to experience the full force of [npc.her] incoming orgasm.");
			
		} else {
			sb.append("As [npc.name] [npc.verb(reach)] [npc.her] climax, [npc.she] [npc.verb(let)] out an extremely loud, shuddering [npc.moan]."
					+ " Within moments, [npc.sheHas] lost [npc.herself] to the overwhelming strength of [npc.her] orgasm, and [npc.she] unwittingly [npc.verb(find)] [npc.herself] making an extremely lewd facial expression."
					+ " As [npc.her] [npc.tongue+] lolls out of [npc.her] mouth, [npc.she] [npc.verb(feel)] [npc.her] cheeks flushing, and can't stop [npc.herself] from rolling [npc.her] [npc.eyes] upwards."
					+ " With this expression of exaggerated ecstasy temporarily stuck on [npc.her] face, [npc.name] [npc.verb(let)] out yet another desperate [npc.moan] and [npc.verb(prepare)] to experience the full force of [npc.her] incoming orgasm.");
		}
		sb.append("</p>");
		
		return UtilText.parse(characterOrgasming, characterTargeted, sb.toString());
	}
	
	private static StringBuilder genericOrgasmSB = new StringBuilder();
	
	private static String getGenericPenisOrgasmDescription(SexActionInterface sexAction, GameCharacter characterOrgasming, GameCharacter characterTargeted, OrgasmCumTarget cumTarget, CondomFailure condomFailure, boolean isSecondaryCreampieTarget) {
		genericOrgasmSB.setLength(0);

		SexAreaInterface contactingArea = null;
		if(!Main.sex.getAllOngoingSexAreas(characterOrgasming, SexAreaPenetration.PENIS).isEmpty()) {
			contactingArea = Main.sex.getAllOngoingSexAreas(characterOrgasming, SexAreaPenetration.PENIS).get(0);
		}
		
		if(!isSecondaryCreampieTarget) {
			if(!characterOrgasming.equals(characterTargeted)) { // Do not append this part if the target is the same person as the performer
				if(Main.sex.getCreampieLockedBy().containsKey(characterOrgasming)) {
					GameCharacter lockingCharacter = Main.sex.getCreampieLockedBy().get(characterOrgasming).getKey();
					Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(characterOrgasming).getValue();
					if(bodypart == Torso.class) {
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
				}
			}
			
		} else {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(characterOrgasming, SexAreaPenetration.PENIS).get(0);
			
			switch((SexAreaOrifice)contactingArea) {
				case ARMPITS:
				case ASS:
				case BREAST:
				case BREAST_CROTCH:
				case MOUTH:
				case NIPPLE:
				case NIPPLE_CROTCH:
				case THIGHS:
				case URETHRA_PENIS:
				case URETHRA_VAGINA:
				case SPINNERET:
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
			for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
				switch(mod) {
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
					case TENTACLED:
						if(characterOrgasming.hasPenisModifier(mod)) {
							modifiers.add(" little tentacles lining [npc.namePos] [npc.cock] start frantically wriggling, and [npc.she] [npc.verb(feel)] [npc.her] [npc.balls+] tightening as [npc.she] [npc.verb(start)] to cum.");
						}
						break;
					case BARBED:
					case BLUNT:
					case PREHENSILE:
					case RIBBED:
					case SHEATHED:
					case TAPERED:
					case VEINY:
					case OVIPOSITOR:
						break;
				}
			}

			List<GameCharacter> ongoingProstateStimulators = new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(characterOrgasming, SexAreaOrifice.ANUS, SexAreaPenetration.FINGER));
			if(characterOrgasming.hasVagina()) {
				ongoingProstateStimulators = new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(characterOrgasming, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER));
			}
			if(!modifiers.isEmpty()) {
				if(!ongoingProstateStimulators.isEmpty()) {
					if(ongoingProstateStimulators.get(0).equals(characterOrgasming)) {
						genericOrgasmSB.append(UtilText.parse(ongoingProstateStimulators.get(0),
								" At this moment, [npc.name] [npc.verb(curl)] [npc.her] [npc.fingers+] up inside [npc.her] "+(characterOrgasming.hasVagina()?"[npc.pussy+]":"[npc.asshole+]")+","
									+ " before rapidly stroking and massaging [npc.her] prostate in an attempt to milk as much [npc.cum] out of [npc.herself] as possible."
									+ " Immediately, [npc.her] body reacts to this added stimulation, and the "));
					} else {
						genericOrgasmSB.append(UtilText.parse(ongoingProstateStimulators.get(0), characterOrgasming,
								" At this moment, [npc.name] [npc.verb(curl)] [npc.her] [npc.fingers+] up inside [npc2.namePos] "+(characterOrgasming.hasVagina()?"[npc2.pussy+]":"[npc2.asshole+]")+","
									+ " before rapidly stroking and massaging [npc2.her] prostate in an attempt to milk as much [npc2.cum] out of [npc2.herHim] as possible."
									+ " Immediately, [npc2.her] body reacts to this added stimulation, and the "));
					}
				} else {
					genericOrgasmSB.append(" The");
				}
				genericOrgasmSB.append(modifiers.get(Util.random.nextInt(modifiers.size())));
				
			} else {
				if(!ongoingProstateStimulators.isEmpty()) {
					if(ongoingProstateStimulators.get(0).equals(characterOrgasming)) {
						genericOrgasmSB.append(UtilText.parse(ongoingProstateStimulators.get(0),
								" At this moment, [npc.name] [npc.verb(curl)] [npc.her] [npc.fingers+] up inside [npc.her] "+(characterOrgasming.hasVagina()?"[npc.pussy+]":"[npc.asshole+]")+","
										+ " before rapidly stroking and massaging [npc.her] prostate in an attempt to milk as much [npc.cum] out of [npc.herself] as possible."
										+ " Immediately, [npc.her] body reacts to this added stimulation, and with a twitch of [npc.her] [npc.cock+],"
											+ " [npc.she] [npc.verb(feel)] [npc.her] [npc.balls+] tightening as [npc.she] [npc.verb(start)] to cum."));
					} else {
						genericOrgasmSB.append(UtilText.parse(ongoingProstateStimulators.get(0), characterOrgasming,
								" At this moment, [npc.name] [npc.verb(curl)] [npc.her] [npc.fingers+] up inside [npc2.namePos] "+(characterOrgasming.hasVagina()?"[npc2.pussy+]":"[npc2.asshole+]")+","
									+ " before rapidly stroking and massaging [npc2.her] prostate in an attempt to milk as much [npc2.cum] out of [npc2.herHim] as possible."
									+ " Immediately, [npc2.her] body reacts to this added stimulation, and with a twitch of [npc2.her] [npc2.cock+],"
										+ " [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.balls+] tightening as [npc2.she] [npc2.verb(start)] to cum."));
					}
				} else {
					genericOrgasmSB.append(" [npc.NamePos] [npc.cock+] twitches, and [npc.she] [npc.verb(feel)] [npc.her] [npc.balls+] tightening as [npc.she] [npc.verb(start)] to cum.");
				}
			}

			if(characterTargeted!=null) {
				genericOrgasmSB.append("<br/>");
				
				if(contactingArea.isOrifice()) {
					switch((SexAreaOrifice) contactingArea) {
						case ARMPITS:
							genericOrgasmSB.append("[npc.Name] [npc.verb(pull)] [npc.her] [npc.cock+] away from [npc2.namePos] [npc2.armpit+]");
							break;
						case ANUS:
							genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out of [npc2.namePos] [npc2.asshole+]");
							break;
						case ASS:
							genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out from between [npc2.namePos] ass cheeks");
							break;
						case BREAST:
							genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out from between [npc2.namePos] [npc2.breasts+]");
							break;
						case BREAST_CROTCH:
							genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out from between [npc2.namePos] [npc2.crotchBoobs+]");
							break;
						case MOUTH:
							if(!Main.sex.getCreampieLockedBy().containsKey(characterOrgasming)) {
								GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(characterOrgasming);
								genericOrgasmSB.append(UtilText.parse(characterOrgasming, primary, "[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out of [npc2.namePos] mouth"));
							} else {
								genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out of [npc2.namePos] mouth");
							}
							break;
						case NIPPLE:
							genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out of [npc2.namePos] [npc2.nipple+]");
							break;
						case NIPPLE_CROTCH:
							genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out of [npc2.namePos] [npc2.crotchNipple+]");
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
						case SPINNERET:
							genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out of [npc2.namePos] [npc2.spinneret+]");
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
							case OVIPOSITOR:
								genericOrgasmSB.append(", before reaching down and sliding [npc.her] [npc.hand] up and down over [npc.her] [npc.cock+].");
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
							genericOrgasmSB.append(
									UtilText.returnStringAtRandom(
											" [npc.Name] [npc.verb(let)] out [npc.a_moan+] and [npc.verb(buck)] [npc.her] [npc.hips+] forwards as [npc2.name] [npc2.verb(continue)] to stroke [npc.her] [npc.cock+].",
											" Letting out [npc.a_moan+], [npc.name] [npc.verb(buck)] [npc.her] [npc.hips+] forwards as [npc2.name] [npc2.verb(continue)] giving [npc.herHim] a handjob through [npc.her] orgasm.",
											" Bucking [npc.her] [npc.hips] forwards, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] [npc2.verb(continue)] stroking [npc.her] [npc.cock+] through [npc.her] orgasm."));
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
			
			List<GameCharacter> ongoingProstateStimulators = new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(characterOrgasming, SexAreaOrifice.ANUS, SexAreaPenetration.FINGER));
			if(characterOrgasming.hasVagina()) {
				ongoingProstateStimulators = new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(characterOrgasming, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER));
			}
			if(!ongoingProstateStimulators.isEmpty()) {
				if(ongoingProstateStimulators.get(0).equals(characterOrgasming)) {
					genericOrgasmSB.append(UtilText.parse(ongoingProstateStimulators.get(0),
							" At this moment, [npc.name] [npc.verb(curl)] [npc.her] [npc.fingers+] up inside [npc.her] "+(characterOrgasming.hasVagina()?"[npc.pussy+]":"[npc.asshole+]")+","
									+ " before rapidly stroking and massaging [npc.her] prostate in an attempt to milk as much [npc.cum] out of [npc.herself] as possible."));
				} else {
					genericOrgasmSB.append(UtilText.parse(ongoingProstateStimulators.get(0), characterOrgasming,
							" At this moment, [npc.name] [npc.verb(curl)] [npc.her] [npc.fingers+] up inside [npc2.namePos] "+(characterOrgasming.hasVagina()?"[npc2.pussy+]":"[npc2.asshole+]")+","
								+ " before rapidly stroking and massaging [npc2.her] prostate in an attempt to milk as much [npc2.cum] out of [npc2.herHim] as possible."));
				}
			}
			
			if(contactingArea.isOrifice()) {
				switch((SexAreaOrifice)contactingArea) {
					case ANUS:
					case NIPPLE:
					case NIPPLE_CROTCH:
					case VAGINA:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
					case SPINNERET:
						// ...
						String orificeName =
							(contactingArea == SexAreaOrifice.SPINNERET
								?"spinneret"
								:(contactingArea == SexAreaOrifice.VAGINA
									?"[npc2.pussy]"
									:(contactingArea == SexAreaOrifice.ANUS
										?"[npc2.asshole]"
										:(contactingArea == SexAreaOrifice.NIPPLE
												?"[npc2.nipple(true)]"
												:(contactingArea == SexAreaOrifice.NIPPLE_CROTCH
														?"[npc2.crotchNipple]"
																:"urethra")))));
						String orificeNamePlusDescriptor =
								(contactingArea == SexAreaOrifice.SPINNERET
									?"web-spinning orifice"
									:(contactingArea == SexAreaOrifice.VAGINA
										?"[npc2.pussy+]"
										:(contactingArea == SexAreaOrifice.ANUS
											?"[npc2.asshole+]"
											:(contactingArea == SexAreaOrifice.NIPPLE
													?"[npc2.nipple+]"
													:(contactingArea == SexAreaOrifice.NIPPLE_CROTCH
															?"[npc2.crotchNipple+]"
																	:"urethra")))));
						
						if(characterOrgasming.hasPenisModifier(PenetrationModifier.KNOTTED)) {
							if(cumTarget==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE) {
								if(!isSecondaryCreampieTarget) {
									GameCharacter secondaryTarget = getSecondaryCreampieTarget(characterTargeted, (SexAreaOrifice) contactingArea);
									if(!Main.game.isPenetrationLimitationsEnabled() || characterOrgasming.isFullPenetrationTooShort(SexAreaPenetration.PENIS, characterTargeted, (SexAreaOrifice)contactingArea)) {
										genericOrgasmSB.append(" Pushing forwards, [npc.name] [npc.verb(hilt)] [npc.her] [npc.cock+] fully inside of [npc2.namePos] "+orificeNamePlusDescriptor+".");
										if(Objects.equals(Main.sex.getCharacterKnotting(characterOrgasming), secondaryTarget)) {
											genericOrgasmSB.append(" Wanting to save [npc.her] rapidly-swelling knot for "+(UtilText.parse(characterOrgasming, secondaryTarget, "[npc2.namePos] "+orificeNamePlusDescriptor))+","
															+ " [npc.she] [npc.verb(hold)] back from pushing it inside of [npc2.name], and [npc.verb(make)] do with just grinding it against [npc2.her] "+orificeName+".");
										} else {
											genericOrgasmSB.append(" Not wanting to get locked inside of [npc2.herHim], [npc.she] [npc.verb(hold)] back from pushing [npc.her] rapidly-swelling knot inside of [npc2.namePos] "+orificeName+".");
										}
										
									} else if(characterOrgasming.isWantingToFullyPenetrate(characterTargeted)) {
										genericOrgasmSB.append(" Pushing forwards, [npc.name] [npc.verb(feel)] [npc.her] [npc.cock+] bottoming out in [npc2.namePos] "+orificeNamePlusDescriptor+".");
										if(Objects.equals(Main.sex.getCharacterKnotting(characterOrgasming), secondaryTarget)) {
											genericOrgasmSB.append(" Desperate to sink [npc.her] rapidly-swelling knot into something, [npc.name] [npc.verb(look)] over at "
													+(UtilText.parse(characterOrgasming, secondaryTarget, "[npc2.namePos] "+orificeNamePlusDescriptor))+" and [npc.verb(let)] out an excited [npc.moan].");
										} else {
											genericOrgasmSB.append(" [npc.She] [npc.verb(let)] out a disappointed [npc.moan] as [npc.she] [npc.verb(realise)] that [npc.her] rapidly-swelling knot isn't going to fit inside of [npc2.namePos] "+orificeName+".");
										}
										
									} else {
										genericOrgasmSB.append(" Not wanting to cause [npc2.her] any discomfort, [npc.name] [npc.verb(hold)] back from fully hilting [npc.her] [npc.cock] in [npc2.namePos] "+orificeNamePlusDescriptor+".");
									}
									
								} else {
									if(Objects.equals(Main.sex.getCharacterKnotting(characterOrgasming), characterTargeted)) {
										genericOrgasmSB.append(" Pushing forwards, [npc.name] [npc.verb(ram)] the now-fully swollen knot at the base of [npc.her] [npc.cock+] against [npc2.namePos] "+orificeNamePlusDescriptor+"."
												+ " By now it's so engorged that it seems almost impossible to push it inside, but with a determined [npc.moan], [npc.name] violently [npc.verb(thrust)] forwards,"
													+ " and with an accompanying cry from [npc2.name], [npc.she] [npc.verb(manage)] to force [npc.her] fat knot into [npc2.her] "+orificeNamePlusDescriptor+".");
									} else {
										genericOrgasmSB.append(" Not wanting to get locked inside of [npc2.herHim], [npc.she] [npc.verb(hold)] back from pushing [npc.her] rapidly-swelling knot inside of [npc2.namePos] "+orificeName+".");
									}
								}
								
							} else {
								if(Objects.equals(Main.sex.getCharacterKnotting(characterOrgasming), characterTargeted)) {
									genericOrgasmSB.append(" Pushing forwards, [npc.name] [npc.verb(ram)] the knot at the base of [npc.her] [npc.cock+] against [npc2.namePos] "+orificeNamePlusDescriptor+"."
											+ " It's already started to swell up so much that [npc.she] [npc.do]n't manage to get it inside on the first thrust,"
												+ " but after pulling back and slamming [npc.her] [npc.hips] forwards, [npc.she] [npc.verb(manage)] to push the thick knot into [npc2.her] "+orificeNamePlusDescriptor+".");
								} else {
									genericOrgasmSB.append(" Not wanting to get locked inside of [npc2.herHim], [npc.she] [npc.verb(hold)] back from pushing [npc.her] rapidly-swelling knot inside of [npc2.namePos] "+orificeName+".");
								}
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
						for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
							switch(mod) {
								case BARBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc.Name] [npc.verb(continue)] to make small, thrusting movements,"
												+ " raking [npc.her] barbs back against the inner walls of [npc2.namePos] "+orificeName+" and causing [npc2.herHim] to let out [npc2.a_moan+].");
									}
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
								case RIBBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										if(characterOrgasming.isPlayer()) {
											modifiers.add(" You feel your ribbed [npc.cock] bumping against the inner walls of [npc2.her] "+orificeName+", which causes [npc2.herHim] to let out [npc2.a_moan+].");
										} else {
											modifiers.add(" The ribbed length of [npc.namePos] [npc.cock] bumps against the inner walls of [npc2.namePos] "+orificeName+", which causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
									}
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
								case BLUNT:
								case KNOTTED:
								case PREHENSILE:
								case SHEATHED:
								case TAPERED:
								case VEINY:
								case OVIPOSITOR:
									break;
							}
						}
						
						if(!modifiers.isEmpty()) {
							genericOrgasmSB.append(modifiers.get(Util.random.nextInt(modifiers.size())));
						}
						
						if(characterOrgasming.hasPenisModifier(PenetrationModifier.KNOTTED)) {
							if(cumTarget!=OrgasmCumTarget.INSIDE_SWITCH_DOUBLE || isSecondaryCreampieTarget) {
								if(Objects.equals(Main.sex.getCharacterKnotting(characterOrgasming), characterTargeted)) {
									genericOrgasmSB.append(" Keeping [npc.her] [npc.hips] pushed tightly against [npc2.namePos] "+orificeName+", [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.her] knot swells up to its full size."
											+ " [npc.She] then [npc.verb(buck)] back a little, and [npc2.name] [npc2.verb(let)] out a startled cry as [npc2.sheIs] pulled along with [npc.herHim];"
												+ " evidence that [npc.her] [npc.cock] is now firmly locked inside [npc2.her] "+orificeNamePlusDescriptor+".");
								}
							}
						}
						break;

					case ARMPITS:
						genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] thrusting [npc.her] [npc.cock+] up against [npc2.namePos] armpit, letting out [npc.a_moan+] as [npc.she] [npc.verb(feel)] it start to twitch.");
						
						for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
							switch(mod) {
								case BARBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc.Her] movements cause the barbs lining the sides of [npc.her] [npc.cock] to rake against [npc2.namePos] [npc2.arm+], causing [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case FLARED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc.Her] flared head swells up and lewdly rubs against [npc2.namePos] [npc2.arm], which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case KNOTTED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc.Her] fat knot swells up, and with each thrust, bumps wildly against [npc2.namePos] [npc2.arm+], which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case PREHENSILE:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" Harnessing the ability of [npc.her] prehensile cock, [npc.name] bends it around [npc2.namePos] [npc2.arm+] on each thrust,"
												+ " which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case RIBBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc.Her] ribbed shaft repeatedly bumps against [npc2.namePos] [npc2.arm+] on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case TENTACLED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" The little tentacles lining [npc.her] shaft wriggle against and massage [npc2.namePos] [npc2.arm+] on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case BLUNT:
								case SHEATHED:
								case TAPERED:
								case VEINY:
								case OVIPOSITOR:
									break;
							}
						}
						break;
						
					case ASS:
						genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] thrusting [npc.her] [npc.cock+] between [npc2.namePos] [npc2.assSize] ass cheeks, letting out [npc.a_moan+] as [npc.she] [npc.verb(feel)] it start to twitch.");
						
						for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
							switch(mod) {
								case BARBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc.Her] movements cause the barbs lining the sides of [npc.her] [npc.cock] to rake against [npc2.namePos] [npc2.ass], causing [npc2.herHim] to let out [npc2.a_moan+].");
									}
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
								case TENTACLED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" The little tentacles lining [npc.her] shaft wriggle against and massage [npc2.namePos] [npc2.asshole] on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case BLUNT:
								case SHEATHED:
								case TAPERED:
								case VEINY:
								case OVIPOSITOR:
									break;
							}
						}
						break;
						
					case BREAST:
						if(characterTargeted.hasBreasts()) {
							genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] thrusting [npc.her] [npc.cock+] between [npc2.namePos] [npc2.breasts+], letting out [npc.a_moan+] as [npc.she] [npc.verb(feel)] it start to twitch.");
							
							for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
								switch(mod) {
									case BARBED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" [npc.Her] movements cause the barbs lining [npc.her] [npc.cock] to rake against the sides of [npc2.namePos] breasts, causing [npc2.herHim] to let out [npc2.a_moan+].");
										}
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
									case TENTACLED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" The little tentacles lining [npc.her] shaft wriggle against and massage [npc2.namePos] [npc2.breasts+] on every thrust,"
													+ " which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case BLUNT:
									case SHEATHED:
									case TAPERED:
									case VEINY:
									case OVIPOSITOR:
										break;
								}
							}
							
						} else {
							genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] grinding [npc.her] [npc.cock+] against [npc2.namePos] flat chest, letting out [npc.a_moan+] as [npc.she] [npc.verb(feel)] it start to twitch.");
							
							for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
								switch(mod) {
									case BARBED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" [npc.Her] movements cause the barbs lining [npc.her] [npc.cock] to rake sharply over [npc2.namePos] torso, causing [npc2.herHim] to let out [npc2.a_moan+].");
										}
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
									case TENTACLED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" The little tentacles lining [npc.her] shaft wriggle against and massage [npc2.namePos] torso on every thrust,"
													+ " which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
										break;
									case BLUNT:
									case SHEATHED:
									case TAPERED:
									case VEINY:
									case OVIPOSITOR:
										break;
								}
							}
						}
						break;
						
					case BREAST_CROTCH:
						genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] thrusting [npc.her] [npc.cock+] against [npc2.namePos] [npc2.crotchBoobs+], letting out [npc.a_moan+] as [npc.she] [npc.verb(feel)] it start to twitch.");
						
						for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
							switch(mod) {
								case BARBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc.Her] movements cause the barbs lining [npc.her] [npc.cock] to rake against the sides of [npc2.namePos] [npc2.crotchBoobs], causing [npc2.herHim] to let out [npc2.a_moan+].");
									}
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
								case TENTACLED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" The little tentacles lining [npc.her] shaft wriggle against and massage [npc2.namePos] [npc2.crotchBoobs+] on every thrust,"
												+ " which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case BLUNT:
								case SHEATHED:
								case TAPERED:
								case VEINY:
								case OVIPOSITOR:
									break;
							}
						}
						break;
						
					case MOUTH:
						GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(characterOrgasming);
						if(!Main.sex.getCreampieLockedBy().containsKey(characterOrgasming) && !characterTargeted.equals(primary)) {
							genericOrgasmSB.append(UtilText.parse(Util.newArrayListOfValues(characterOrgasming, characterTargeted, primary),
									" Not wanting [npc3.herHim] to be the one to bear the brunt of [npc.her] orgasm, [npc.name] [npc.verb(draw)] [npc.her] [npc.cock+] from out of [npc3.namePos] throat."));
									
							if(characterOrgasming.hasPenisModifier(PenetrationModifier.KNOTTED)) {
								if(Objects.equals(Main.sex.getCharacterKnotting(characterOrgasming), characterTargeted)) {
									genericOrgasmSB.append(UtilText.parse(Util.newArrayListOfValues(characterOrgasming, characterTargeted, primary),
											" Moving the [npc.cockHead+] over to point directly at [npc2.namePos] mouth, [npc.she] [npc.verb(thrust)] forwards and "
												+ " [npc.verb(hilt)] [npc.her] [npc.cock+] down [npc2.namePos] throat, ramming [npc.her] rapidly-growing knot against [npc2.her] [npc2.lips+] in the process."
											+ " It's already started to swell up so much that [npc.she] [npc.do]n't manage to get it into [npc2.her] [npc2.mouth] on the first thrust,"
												+ " but after pulling back and slamming [npc.her] [npc.hips] forwards once again, [npc.she] [npc.verb(succeed)] in pushing the thick knot past [npc2.her] [npc2.lips]."
											+ "<br/>"
											+ "The moment [npc.she] [npc.verb(feel)] it pop inside, [npc.name] [npc.verb(let)] out [npc.a_moan+], and as [npc.she] presses [npc.her] groin firmly against [npc2.namePos] [npc2.face+],"
												+ " [npc.her] knot finishes fully expanding and firmly locks [npc.her] [npc.cock+] down [npc2.namePos] throat."));
									
								} else {
									if(!Main.game.isPenetrationLimitationsEnabled() || characterOrgasming.isFullPenetrationTooShort(SexAreaPenetration.PENIS, characterTargeted, (SexAreaOrifice)contactingArea)) {
										genericOrgasmSB.append(UtilText.parse(characterOrgasming, characterTargeted,
												" Moving the [npc.cockHead+] over to point directly at [npc2.namePos] mouth, [npc.she] [npc.verb(thrust)] forwards until [npc.her] rapidly-growing knot is rammed against [npc2.her] [npc2.lips+]."
												+ " Not wanting to get locked down [npc2.her] throat, [npc.name] [npc.verb(hold)] back from pushing the thick knot past [npc2.her] [npc2.lips]."));
										
									} else if(characterOrgasming.isWantingToFullyPenetrate(characterTargeted)) {
										genericOrgasmSB.append(" Moving the [npc.cockHead+] over to point directly at [npc2.namePos] mouth, [npc.she] [npc.verb(thrust)] forwards,"
												+ " only to feel that [npc2.her] throat isn't deep enough to accommodate the full length of [npc.her] [npc.cock+], preventing [npc.her] rapidly-swelling knot from being pushed into [npc2.her] mouth.");
										
									} else {
										genericOrgasmSB.append(" Moving the [npc.cockHead+] over to point directly at [npc2.namePos] mouth, [npc.she] [npc.verb(thrust)] forwards,"
												+ " but as [npc.she] [npc.do]n't want to cause [npc2.herHim] any discomfort, [npc.she] [npc.verb(hold)] back from fully hilting [npc.her] [npc.cock] down [npc2.her] throat.");
									}
								}
								
							} else {
								if(!Main.game.isPenetrationLimitationsEnabled() || characterOrgasming.isFullPenetrationTooShort(SexAreaPenetration.PENIS, characterTargeted, (SexAreaOrifice)contactingArea)) {
									genericOrgasmSB.append(" Moving the [npc.cockHead+] over to point directly at [npc2.namePos] mouth, [npc.she] [npc.verb(thrust)] forwards, fully hilting [npc.her] [npc.cock+] deep down [npc2.her] throat."
											+ " Grinding the base up against [npc2.her] [npc2.lips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] [npc.her] [npc.cock+] start to twitch inside of [npc2.herHim].");
								} else {
									genericOrgasmSB.append(" Moving the [npc.cockHead+] over to point directly at [npc2.namePos] mouth, [npc.she] [npc.verb(thrust)] forwards, ramming [npc.her] twitching [npc.cock] deep down [npc2.namePos] throat.");
								}
							}
							
						} else {
							if(characterOrgasming.hasPenisModifier(PenetrationModifier.KNOTTED)) {
								if(Objects.equals(Main.sex.getCharacterKnotting(characterOrgasming), characterTargeted)) {
									genericOrgasmSB.append(UtilText.parse(Util.newArrayListOfValues(characterOrgasming, characterTargeted, primary),
											" Letting out [npc.a_moan+], [npc.name] [npc.verb(thrust)] forwards and [npc.verb(hilt)] [npc.her] [npc.cock+] down [npc2.namePos] throat,"
													+ " ramming [npc.her] rapidly-growing knot against [npc2.her] [npc2.lips+] in the process."
											+ " It's already started to swell up so much that [npc.she] [npc.do]n't manage to get it into [npc2.her] [npc2.mouth] on the first thrust,"
												+ " but after pulling back and slamming [npc.her] [npc.hips] forwards once again, [npc.she] [npc.verb(succeed)] in pushing the thick knot past [npc2.her] [npc2.lips]."
											+ "<br/>"
											+ "The moment [npc.she] [npc.verb(feel)] it pop inside, [npc.name] [npc.verb(let)] out [npc.a_moan+], and as [npc.she] presses [npc.her] groin firmly against [npc2.namePos] [npc2.face+],"
												+ " [npc.her] knot finishes fully expanding and firmly locks [npc.her] [npc.cock+] down [npc2.namePos] throat."));
									
								} else {
									if(!Main.game.isPenetrationLimitationsEnabled() || characterOrgasming.isFullPenetrationTooShort(SexAreaPenetration.PENIS, characterTargeted, (SexAreaOrifice)contactingArea)) {
										genericOrgasmSB.append(UtilText.parse(characterOrgasming, characterTargeted,
												" Letting out [npc.a_moan+], [npc.name] [npc.verb(thrust)] forwards until [npc.her] rapidly-growing knot is rammed against [npc2.her] [npc2.lips+]."
												+ " Not wanting to get locked down [npc2.her] throat, [npc.name] [npc.verb(hold)] back from pushing the thick knot past [npc2.her] [npc2.lips]."));
										
									} else if(characterOrgasming.isWantingToFullyPenetrate(characterTargeted)) {
										genericOrgasmSB.append(" Letting out [npc.a_moan+], [npc.name] [npc.verb(thrust)] forwards,"
												+ " only to feel that [npc2.her] throat isn't deep enough to accommodate the full length of [npc.her] [npc.cock+], preventing [npc.her] rapidly-swelling knot from being pushed into [npc2.her] mouth.");
										
									} else {
										genericOrgasmSB.append(" Letting out [npc.a_moan+], [npc.name] [npc.verb(thrust)] forwards,"
												+ " but as [npc.she] [npc.do]n't want to cause [npc2.herHim] any discomfort, [npc.she] [npc.verb(hold)] back from fully hilting [npc.her] [npc.cock] down [npc2.her] throat.");
									}
								}
									
							} else {
								if(!Main.game.isPenetrationLimitationsEnabled() || characterOrgasming.isFullPenetrationTooShort(SexAreaPenetration.PENIS, characterTargeted, (SexAreaOrifice)contactingArea)) {
									genericOrgasmSB.append(" Fully hilting [npc.her] [npc.cock+] deep down [npc2.namePos] throat, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] it starts to twitch inside of [npc2.herHim].");
								} else {
									genericOrgasmSB.append(" Ramming [npc.her] [npc.cock+] deep down [npc2.namePos] throat, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] it starts to twitch inside of [npc2.herHim].");
								}
							}
						}
						
						modifiers.clear();
						for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
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
								case BLUNT:
								case KNOTTED:
								case PREHENSILE:
								case SHEATHED:
								case TAPERED:
								case VEINY:
								case OVIPOSITOR:
									break;
							}
						}
						
						if(!modifiers.isEmpty()) {
							genericOrgasmSB.append(modifiers.get(Util.random.nextInt(modifiers.size())));
						}
						break;
						
					case THIGHS:
						genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] thrusting [npc.her] [npc.cock+] between [npc2.namePos] thighs, letting out [npc.a_moan+] as [npc.she] [npc.verb(feel)] it start to twitch.");
						
						for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
							switch(mod) {
								case BARBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" [npc.Her] movements cause the barbs lining the sides of [npc.her] [npc.cock] to rake against [npc2.namePos] [npc2.legs+], causing [npc2.herHim] to let out [npc2.a_moan+].");
									}
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
								case TENTACLED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										modifiers.add(" The little tentacles lining [npc.her] shaft wriggle against and massage [npc2.namePos] [npc2.legs+] on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
									}
									break;
								case BLUNT:
								case SHEATHED:
								case TAPERED:
								case VEINY:
								case OVIPOSITOR:
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
						if(characterOrgasming.equals(characterTargeted)) {
							genericOrgasmSB.append(" [npc.She] [npc.verb(continue)] pumping [npc.her] [npc.cock+] with [npc.her] [npc.hand], letting out [npc.a_moan+] as [npc.she] [npc.verb(feel)] it start to twitch.");
							
						} else {
							genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] thrusting [npc.her] [npc.cock+] into [npc2.namePos] [npc2.hand], letting out [npc.a_moan+] as [npc.she] [npc.verb(feel)] it start to twitch.");
						}
						
						for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
							switch(mod) {
								case BARBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										if(characterOrgasming.equals(characterTargeted)) {
											modifiers.add(" [npc.Her] movements cause the barbs lining the sides of [npc.her] [npc.cock] to rake against [npc.her] [npc.fingers+], causing [npc.herHim] to let out [npc.a_moan+].");
										} else {
											modifiers.add(" [npc.Her] movements cause the barbs lining the sides of [npc.her] [npc.cock] to rake against [npc2.namePos] [npc2.fingers+], causing [npc2.herHim] to let out [npc2.a_moan+].");
										}
									}
									break;
								case FLARED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										if(characterOrgasming.equals(characterTargeted)) {
											modifiers.add(" [npc.Her] flared head swells up, causing [npc.her] [npc.fingers+] to be parted ever wider, which in turn causes [npc.herHim] to let out [npc.a_moan+].");
										} else {
											modifiers.add(" [npc.Her] flared head swells up, causing [npc2.namePos] [npc2.fingers+] to be parted ever wider, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
									}
									break;
								case KNOTTED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										if(characterOrgasming.equals(characterTargeted)) {
											modifiers.add(" [npc.Her] fat knot swells up, and with each thrust, bumps wildly against [npc.her] [npc.fingers+], which in turn causes [npc.herHim] to let out [npc.a_moan+].");
										} else {
											modifiers.add(" [npc.Her] fat knot swells up, and with each thrust, bumps wildly against [npc2.namePos] [npc2.fingers+], which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
									}
									break;
								case PREHENSILE:
									if(characterOrgasming.hasPenisModifier(mod)) {
										if(characterOrgasming.equals(characterTargeted)) {
											modifiers.add(" Harnessing the ability of [npc.her] prehensile cock, [npc.name] [npc.verb(bend)] it around [npc.her] [npc.fingers+] on each thrust,"
													+ " which in turn causes [npc.herHim] to let out [npc.a_moan+].");
										} else {
											modifiers.add(" Harnessing the ability of [npc.her] prehensile cock, [npc.name] [npc.verb(bend)] it around [npc2.namePos] [npc2.fingers+] on each thrust,"
													+ " which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
									}
									break;
								case RIBBED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										if(characterOrgasming.equals(characterTargeted)) {
											modifiers.add(" [npc.Her] ribbed shaft repeatedly bumps against [npc.her] [npc.fingers+] on every thrust, which in turn causes [npc.herHim] to let out [npc.a_moan+].");
										} else {
											modifiers.add(" [npc.Her] ribbed shaft repeatedly bumps against [npc2.namePos] [npc2.fingers+] on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
									}
									break;
								case TENTACLED:
									if(characterOrgasming.hasPenisModifier(mod)) {
										if(characterOrgasming.equals(characterTargeted)) {
											modifiers.add(" The little tentacles lining [npc.her] shaft wriggle against and massage [npc.her] [npc.fingers+] on every thrust, which in turn causes [npc.herHim] to let out [npc.a_moan+].");
										} else {
											modifiers.add(" The little tentacles lining [npc.her] shaft wriggle against and massage [npc2.namePos] [npc2.fingers+] on every thrust, which in turn causes [npc2.herHim] to let out [npc2.a_moan+].");
										}
									}
									break;
								case BLUNT:
								case SHEATHED:
								case TAPERED:
								case VEINY:
								case OVIPOSITOR:
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
						if(Main.sex.isDoubleFootJob(characterTargeted)) {
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
			genericOrgasmSB.append(cumTargetDescription(sexAction, characterOrgasming, characterTargeted, cumTarget, condomFailure, isSecondaryCreampieTarget));
		}
		if(sexAction==GENERIC_ORGASM_OVIPOSITOR_PENIS_EGG_LAYING && characterOrgasming.equals(Main.sex.getCharacterLayingEggs())) {
			genericOrgasmSB.append(eggLayingTargetDescription(SexAreaPenetration.PENIS, characterOrgasming, characterTargeted, condomFailure));
		}
		
		if(characterOrgasming.hasPenisModifier(PenetrationModifier.KNOTTED)
				&& (cumTarget==OrgasmCumTarget.INSIDE || (cumTarget==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE && isSecondaryCreampieTarget))
				&& Objects.equals(Main.sex.getCharacterKnotting(characterOrgasming), characterTargeted)
				&& contactingArea.isOrifice()
				&& ((SexAreaOrifice)contactingArea).isInternalOrifice()) {
			genericOrgasmSB.append("<br/>"
					+ "Even after [npc.namePos] [npc.balls+] have pumped their entire load into [npc2.name], [npc.her] knot remains swollen, locking"
					+ "#IF(npc2.isPlayer() || npc.isPlayer())"
						+ " the two of you together."
					+ "#ELSE"
						+ " [npc.herHim] and [npc.her] partner together."
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
	
	private static String cumTargetDescription(SexActionInterface sexAction, GameCharacter characterOrgasming, GameCharacter target, OrgasmCumTarget targetArea, CondomFailure condomFailure, boolean isSecondaryCreampieTarget) {
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
					case EGG_LAYING:
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

		
		List<CoverableArea> areasCummedOn = new ArrayList<>();
		if(target!=null) {
			areasCummedOn = sexAction.getAreasCummedOn(characterOrgasming, target);
		}
		
		List<AbstractClothing> targetAreaClothingCummedOn = new ArrayList<>();
		if(areasCummedOn!=null) {
			for(CoverableArea ca : areasCummedOn) {
				if(!target.isCoverableAreaExposed(ca)) {
					if(targetArea.isRequiresPartner()) {
						targetAreaClothingCummedOn.addAll(getClothingCummedOn(target, ca));
					} else {
						targetAreaClothingCummedOn.addAll(getClothingCummedOn(characterOrgasming, ca));
					}
				}
			}
			targetAreaClothingCummedOn = new ArrayList<>(new HashSet<>(targetAreaClothingCummedOn)); // Remove duplicates
		}
		
		switch(targetArea) {
			case ARMPITS:
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append(" all over [npc2.namePos] [npc2.armpit+]."
								+ " [npc.Name] [npc.verb(grin)] as [npc.her] [npc.cum+] splatters onto [npc2.namePos] arm,"
									+ " and [npc2.she] can't help but let out [npc2.a_moan] as [npc2.she] [npc2.verb(feel)] it run down over [npc2.her] [npc2.arm+].");
					return UtilText.parse(characterOrgasming, target, sb.toString());
				}
			case ASS:
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append(" all over [npc2.namePos] [npc2.ass+]."
								+ " [npc.Name] [npc.verb(grin)] as [npc.her] [npc.cum+] splatters onto [npc2.namePos] naked backside,"
									+ " and [npc2.she] can't help but let out [npc2.a_moan] as [npc2.she] [npc2.verb(feel)] it");
					if(target.getGenitalArrangement()==GenitalArrangement.CLOACA) {
						sb.append(" run down over [npc2.her] [npc2.assSkin+].");
					} else if(target.getGenitalArrangement()==GenitalArrangement.CLOACA_BEHIND) {
						sb.append(" run down over [npc2.her] [npc2.assSkin+] and rear-facing cloaca.");
					} else {
						sb.append(" run down over [npc2.her] [npc2.asshole+].");
					}
					return UtilText.parse(characterOrgasming, target, sb.toString());
				}
			case BACK:
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
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
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
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
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
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
				return " all over the floor.";
			case STOMACH:
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
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
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					String groinText = "groin.";
					if(target.hasPenisIgnoreDildo()) {
						if(target.hasVagina()) {
							if(target.getGenitalArrangement()==GenitalArrangement.CLOACA) {
								groinText = " [npc2.cock], [npc2.pussy], and [npc2.asshole].";
							} else if(target.getGenitalArrangement()==GenitalArrangement.NORMAL) {
								groinText = " [npc2.cock] and [npc2.pussy].";
							}
						} else {
							if(target.getGenitalArrangement()==GenitalArrangement.CLOACA) {
								groinText = " [npc2.cock] and [npc2.asshole].";
							} else if(target.getGenitalArrangement()==GenitalArrangement.NORMAL) {
								groinText = " [npc2.cock+].";
							}
						}
						
					} else if(target.hasVagina()) {
						if(target.getGenitalArrangement()==GenitalArrangement.CLOACA) {
							groinText = " [npc2.pussy] and [npc2.asshole].";
						} else if(target.getGenitalArrangement()==GenitalArrangement.NORMAL) {
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
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					List<String> areas = new ArrayList<>();
					areas.add("head");
					if(target.hasHair()) {
						areas.add("[npc2.hair(true)]");
					}
					if(target.hasHorns()) {
						areas.add("[npc2.horns]");
					}
					return UtilText.parse(characterOrgasming, target,
							" all over [npc2.namePos] "+Util.stringsToStringList(areas, false)+"."
							+ " [npc.Name] [npc.verb(grin)] as [npc.she] [npc.verb(watch)] [npc.her] [npc.cum+] splatter onto [npc2.herHim].");
				}
			case LEGS:
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
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
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
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
				return " all up the [pc.wall].";
				
			case SELF_GROIN:
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, areasCummedOn, targetAreaClothingCummedOn);
						
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
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, areasCummedOn, targetAreaClothingCummedOn);
					
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
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, areasCummedOn, targetAreaClothingCummedOn);
					
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
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, areasCummedOn, targetAreaClothingCummedOn);
					
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
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, areasCummedOn, targetAreaClothingCummedOn);
					
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
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, areasCummedOn, targetAreaClothingCummedOn);
					
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

			case SELF_HANDS:
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, areasCummedOn, targetAreaClothingCummedOn);
				} else {
					return UtilText.parse(characterOrgasming,
							" all over [npc.namePos] [npc.hands].");
				}
				
			case LILAYA_PANTIES:
				LilayasRoom.lilayasPanties.setDirty(null, true);
				return UtilText.parse(characterOrgasming,
						" directly into Lilaya's panties."
						+ " You can't help but let out [pc.a_moan+] as you watch your [pc.cum+] pool in the soft fabric,"
							+ " and you give your [pc.cock+] a few extra strokes as you imagine your demonic [lilaya.relation(pc)] blushing as she slides the cum-saturated underwear up over her hot pussy.");
		}
		
		// Continued description for cumming inside:
		
		SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(characterOrgasming, SexAreaPenetration.PENIS).get(0);
		
		if(areaContacted.isOrifice()) {
			switch((SexAreaOrifice)areaContacted) {
				case ARMPITS:
					if (!targetAreaClothingCummedOn.isEmpty()) {
						return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
						
					} else {
						cumTargetSB.append(" all over [npc2.namePos] [npc2.armpit+] and [npc2.arm+(true)].");
	
						switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								cumTargetSB.append(" After a few seconds, [npc2.name] [npc2.verb(realise)] that [npc.nameIs] not even close to stopping, and after just a moment more,"
										+ " [npc2.her] [npc2.arm+(true)] are absolutely drenched in [npc.cum+].");
								break;
							default:
								break;
						}
					}
					break;
				
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
					if (!targetAreaClothingCummedOn.isEmpty()) {
						return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
						
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
					if (!targetAreaClothingCummedOn.isEmpty()) {
						return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
						
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
					if (!targetAreaClothingCummedOn.isEmpty()) {
						return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
						
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
							case COFFEE:
								cumTargetSB.append(" The strong, bitter taste of [npc.namePos] coffee-flavoured");
								break;
							case TEA:
								cumTargetSB.append(" The taste of [npc.namePos] tea-flavoured");
								break;
							case MAPLE:
								cumTargetSB.append(" The sweet taste of [npc.namePos] maple-flavoured");
								break;
							case CINNAMON:
								cumTargetSB.append(" The taste of [npc.namePos] cinnamon-flavoured");
								break;
							case LEMON:
								cumTargetSB.append(" The sour taste of [npc.namePos] lemon-flavoured");
								break;
							case ORANGE:
								cumTargetSB.append(" The citrus taste of [npc.namePos] orange-flavoured");
								break;
							case GRAPE:
								cumTargetSB.append(" The taste of [npc.namePos] grape-flavoured");
								break;
							case MELON:
								cumTargetSB.append(" The taste of [npc.namePos] melon-flavoured");
								break;
							case COCONUT:
								cumTargetSB.append(" The taste of [npc.namePos] coconut-flavoured");
								break;
							case BLUEBERRY:
								cumTargetSB.append(" The taste of [npc.namePos] blueberry-flavoured");
								break;
						}
						cumTargetSB.append(" cum rises up to hit your [npc2.tongue], and you");
						if(target.hasFetish(Fetish.FETISH_CUM_ADDICT) || Main.sex.getCharactersRequestingCreampie().contains(target) || Main.sex.getCharactersRequestingKnot().contains(target)) {
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
								case ALCOHOLIC_WEAK:
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
							cumTargetSB.append(" After a few seconds, [npc2.name] [npc2.verb(realise)] that [npc.nameIs] not even close to stopping,"
												+ " and [npc2.she] [npc2.verb(let)] out a desperate, gargled [npc2.moan] as [npc.namePos] [npc.cum+] backs up and starts drooling out of the corners of [npc2.her] mouth."
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
					if (!targetAreaClothingCummedOn.isEmpty()) {
						return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
						
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
					
				case URETHRA_PENIS: case URETHRA_VAGINA:
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

				case SPINNERET:
					cumTargetSB.append(" deep into [npc2.namePos] spinneret.");
					switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
						case SIX_EXTREME: case SEVEN_MONSTROUS:
							cumTargetSB.append(" After a few seconds, [npc2.name] [npc2.verb(realise)] that [npc.nameIs] not even close to stopping, and as [npc.her]"
									+" [npc.cum+] backs up and starts drooling out of [npc2.her] web-spinning orifice, [npc2.she] [npc2.verb(let)] out [npc2.a_moan+]."
											+ " [npc.Name] [npc.verb(keep)] [npc.her] [npc.cock] hilted inside of [npc2.herHim], [npc.moaning+] as [npc.she] [npc.verb(wait)] for [npc.her] [npc.balls] to run dry.");
							break;
						default:
							break;
					}
					if(Main.getProperties().hasValue(PropertyValue.inflationContent)) {
						float cumAmount = target.getTotalFluidInArea((SexAreaOrifice) areaContacted) + characterOrgasming.getPenisRawOrgasmCumQuantity();
						cumTargetSB.append(getSpinneretInflationText(characterOrgasming, target, cumAmount));
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
					if(characterOrgasming.equals(target)) {
						if (!targetAreaClothingCummedOn.isEmpty()) {
							return getClothingCummedOnText(characterOrgasming, areasCummedOn, targetAreaClothingCummedOn);
						} else {
							cumTargetSB.append(" all over [npc.her] [npc.fingers+].");
							
							switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
								case SIX_EXTREME:
								case SEVEN_MONSTROUS:
									cumTargetSB.append(" After a few seconds, [npc.her] [npc.hands+] are absolutely drenched in [npc.cum+].");
									break;
								default:
									break;
							}
						}
					} else if (!targetAreaClothingCummedOn.isEmpty()) {
						return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
						
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
					if (!targetAreaClothingCummedOn.isEmpty()) {
						return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
						
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
	
	private static String eggLayingTargetDescription(SexAreaPenetration penetratingArea, GameCharacter characterOrgasming, GameCharacter target, CondomFailure condomFailure) {
		StringBuilder sb = new StringBuilder();
		SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(characterOrgasming, penetratingArea).get(0);
		
		sb.append("</br>");
		sb.append("</br>");
		
		boolean condomBreaks = condomFailure==CondomFailure.EGG_LAYING;
		int eggCount = characterOrgasming.getPregnantLitter().getTotalLitterCount();
		String penetrationAreaText = "[npc.cock]";
		String penetrationAreaPlusText = "[npc.cock+]";
		
		if(penetratingArea==SexAreaPenetration.CLIT) {
			penetrationAreaText = "[npc.clit]";
			penetrationAreaPlusText = "[npc.clit+]";
		}
		
		if(areaContacted.isOrifice()) {
			switch((SexAreaOrifice)areaContacted) {
				case ARMPITS:
				case ASS:
				case BREAST:
				case BREAST_CROTCH:
				case SPINNERET:
				case THIGHS:
				case URETHRA_PENIS:
				case URETHRA_VAGINA:
					break;
				case ANUS:
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("Not yet finished, [npc.name] [npc.verb(let)] out [npc.a_moan+] and [npc.verb(slam)] [npc.her] "+penetrationAreaPlusText+" as deep as possible into [npc2.namePos] [npc2.asshole].");
							sb.append(" Roughly grinding [npc.her] [npc.hips] into [npc2.namePos] [npc2.assCloaca], [npc.she] [npc.verb(sneer)],");
							if(eggCount==1) {
								sb.append(" [npc.speech(You're going to take my egg, [npc2.bitch]!)]");
							} else {
								sb.append(" [npc.speech(I'm going to stuff you full of eggs, [npc2.bitch]!)]");
							}
							break;
						default:
							sb.append("Not yet finished, [npc.name] [npc.verb(let)] out [npc.a_moan+] and [npc.verb(push)] [npc.her] "+penetrationAreaPlusText+" as deep as possible into [npc2.namePos] [npc2.asshole].");
							sb.append(" Eagerly grinding [npc.her] [npc.hips] into [npc2.namePos] [npc2.assCloaca], [npc.she] excitedly [npc.verb(exclaim)],");
							if(eggCount==1) {
								sb.append(" [npc.speech(I'm going to put my egg in you!)]");
							} else {
								sb.append(" [npc.speech(I'm going to stuff you with eggs!)]");
							}
							break;
					}
					sb.append("</br>");
					sb.append("With that, [npc2.name] suddenly [npc2.verb(feel)] the unmistakable bulbous lump of an egg travelling down the length of [npc.namePos] "+penetrationAreaText+","
							+ " and [npc2.she] can't help but let out an alarmed [npc2.moan] as it squeezes out of the end"+(condomBreaks?", breaking [npc.namePos] condom in the process,":"")+" and is safely laid deep in [npc2.her] stomach.");
					if(eggCount>1) {
						sb.append(" Still with "+(Util.intToString(eggCount-1))+" egg"+(eggCount>2?"s":"")+" left to lay, [npc.name] [npc.verb(keep)] [npc.her] "+penetrationAreaPlusText+" hilted deep in [npc2.namePos] [npc2.asshole+]"
								+ " and [npc.verb(continue)] to let out a series of deeply satisfied [npc.moans] as [npc.she] [npc.verb(turn)] [npc2.her] stomach into an egg-incubation chamber.");
					}
					sb.append("</br>");
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("With one last [npc.moan], [npc.name] roughly [npc.verb(pull)] back, before dominantly rubbing [npc.her] [npc.hand] over [npc2.namePos] swollen belly and growling,");
							sb.append(" [npc.speech(Make sure to take good care of my "+(eggCount>1?"children":"child")+", [npc2.bitch]!)]");
							break;
						default:
							sb.append("With one last [npc.moan], [npc.name] [npc.verb(pull)] back, before lovingly rubbing [npc.her] [npc.hand] over [npc2.namePos] swollen belly and sighing,");
							sb.append(" [npc.speech(Make sure to take good care of my "+(eggCount>1?"children":"child")+"!)]");
							break;
					}
					break;
				case MOUTH:
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("Not yet finished, [npc.name] [npc.verb(let)] out [npc.a_moan+] and [npc.verb(slam)] [npc.her] "+penetrationAreaPlusText+" as deep as possible down [npc2.namePos] throat.");
							sb.append(" Roughly grinding [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], [npc.she] [npc.verb(sneer)],");
							if(eggCount==1) {
								sb.append(" [npc.speech(You're going to take my egg, [npc2.bitch]!)]");
							} else {
								sb.append(" [npc.speech(I'm going to stuff you full of eggs, [npc2.bitch]!)]");
							}
							break;
						default:
							sb.append("Not yet finished, [npc.name] [npc.verb(let)] out [npc.a_moan+] and [npc.verb(push)] [npc.her] "+penetrationAreaPlusText+" as deep as possible down [npc2.namePos] throat.");
							sb.append(" Eagerly grinding [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], [npc.she] excitedly [npc.verb(exclaim)],");
							if(eggCount==1) {
								sb.append(" [npc.speech(I'm going to put my egg in you!)]");
							} else {
								sb.append(" [npc.speech(I'm going to stuff you with eggs!)]");
							}
							break;
					}
					sb.append("</br>");
					sb.append("With that, [npc2.name] suddenly [npc2.verb(feel)] the unmistakable bulbous lump of an egg travelling down the length of [npc.namePos] "+penetrationAreaText+","
							+ " and [npc2.she] can't help but let out an alarmed, muffled [npc2.moan] as it squeezes out of the end"+(condomBreaks?", breaking [npc.namePos] condom in the process,":"")+" and is safely laid deep in [npc2.her] stomach.");
					if(eggCount>1) {
						sb.append(" Still with "+(Util.intToString(eggCount-1))+" egg"+(eggCount>2?"s":"")+" left to lay, [npc.name] [npc.verb(keep)] [npc.her] "+penetrationAreaPlusText+" hilted deep down [npc2.namePos] throat"
								+ " and [npc.verb(continue)] to let out a series of deeply satisfied [npc.moans] as [npc.she] [npc.verb(turn)] [npc2.her] stomach into an egg-incubation chamber.");
					}
					sb.append("</br>");
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("With one last [npc.moan], [npc.name] roughly [npc.verb(pull)] back, before dominantly rubbing [npc.her] [npc.hand] over [npc2.namePos] swollen belly and growling,");
							sb.append(" [npc.speech(Make sure to take good care of my "+(eggCount>1?"children":"child")+", [npc2.bitch]!)]");
							break;
						default:
							sb.append("With one last [npc.moan], [npc.name] [npc.verb(pull)] back, before lovingly rubbing [npc.her] [npc.hand] over [npc2.namePos] swollen belly and sighing,");
							sb.append(" [npc.speech(Make sure to take good care of my "+(eggCount>1?"children":"child")+"!)]");
							break;
					}
					break;
				case NIPPLE:
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("Not yet finished, [npc.name] [npc.verb(let)] out [npc.a_moan+] and [npc.verb(slam)] [npc.her] "+penetrationAreaPlusText+" as deep as possible into [npc2.namePos] [npc2.nipple(true)].");
							sb.append(" Roughly grinding [npc.her] [npc.hips] into [npc2.namePos] [npc2.breasts], [npc.she] [npc.verb(sneer)],");
							if(eggCount==1) {
								sb.append(" [npc.speech(You're going to take my egg, [npc2.bitch]!)]");
							} else {
								sb.append(" [npc.speech(I'm going to stuff you full of eggs, [npc2.bitch]!)]");
							}
							break;
						default:
							sb.append("Not yet finished, [npc.name] [npc.verb(let)] out [npc.a_moan+] and [npc.verb(push)] [npc.her] "+penetrationAreaPlusText+" as deep as possible into [npc2.namePos] [npc2.nipple(true)].");
							sb.append(" Eagerly grinding [npc.her] [npc.hips] into [npc2.namePos] [npc2.breasts], [npc.she] excitedly [npc.verb(exclaim)],");
							if(eggCount==1) {
								sb.append(" [npc.speech(I'm going to put my egg in you!)]");
							} else {
								sb.append(" [npc.speech(I'm going to stuff you with eggs!)]");
							}
							break;
					}
					sb.append("</br>");
					sb.append("With that, [npc2.name] suddenly [npc2.verb(feel)] the unmistakable bulbous lump of an egg travelling down the length of [npc.namePos] "+penetrationAreaText+","
							+ " and [npc2.she] can't help but let out an alarmed [npc2.moan] as it squeezes out of the end"
							+(condomBreaks?", breaking [npc.namePos] condom in the process,":"")+" and is safely laid deep within [npc2.her] fuckable breast.");
					if(eggCount>1) {
						sb.append(" Still with "+(Util.intToString(eggCount-1))+" egg"+(eggCount>2?"s":"")+" left to lay, [npc.name] [npc.verb(pull)] out and then immediately [npc.verb(penetrate)] [npc2.namePos] other [npc2.nipple(true)]."
								+ " Continuing to let out a series of deeply satisfied [npc.moans], [npc.she] quickly [npc.verb(turn)] [npc2.namePos] [npc2.breasts] into egg-incubation chambers.");
					}
					sb.append("</br>");
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("With one last [npc.moan], [npc.name] roughly [npc.verb(pull)] back, before dominantly rubbing [npc.her] [npc.hand] over [npc2.namePos] swollen [npc2.breasts] and growling,");
							sb.append(" [npc.speech(Make sure to take good care of my "+(eggCount>1?"children":"child")+", [npc2.bitch]!)]");
							break;
						default:
							sb.append("With one last [npc.moan], [npc.name] [npc.verb(pull)] back, before lovingly rubbing [npc.her] [npc.hand] over [npc2.namePos] swollen [npc2.breasts] and sighing,");
							sb.append(" [npc.speech(Make sure to take good care of my "+(eggCount>1?"children":"child")+"!)]");
							break;
					}
					break;
				case NIPPLE_CROTCH:
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("Not yet finished, [npc.name] [npc.verb(let)] out [npc.a_moan+] and [npc.verb(slam)] [npc.her] "+penetrationAreaPlusText+" as deep as possible into [npc2.namePos] [npc2.nippleCrotch(true)].");
							sb.append(" Roughly grinding [npc.her] [npc.hips] into [npc2.namePos] [npc2.crotchBoobs], [npc.she] [npc.verb(sneer)],");
							if(eggCount==1) {
								sb.append(" [npc.speech(You're going to take my egg, [npc2.bitch]!)]");
							} else {
								sb.append(" [npc.speech(I'm going to stuff you full of eggs, [npc2.bitch]!)]");
							}
							break;
						default:
							sb.append("Not yet finished, [npc.name] [npc.verb(let)] out [npc.a_moan+] and [npc.verb(push)] [npc.her] "+penetrationAreaPlusText+" as deep as possible into [npc2.namePos] [npc2.nippleCrotch(true)].");
							sb.append(" Eagerly grinding [npc.her] [npc.hips] into [npc2.namePos] [npc2.crotchBoobs], [npc.she] excitedly [npc.verb(exclaim)],");
							if(eggCount==1) {
								sb.append(" [npc.speech(I'm going to put my egg in you!)]");
							} else {
								sb.append(" [npc.speech(I'm going to stuff you with eggs!)]");
							}
							break;
					}
					sb.append("</br>");
					sb.append("With that, [npc2.name] suddenly [npc2.verb(feel)] the unmistakable bulbous lump of an egg travelling down the length of [npc.namePos] "+penetrationAreaText+","
							+ " and [npc2.she] can't help but let out an alarmed [npc2.moan] as it squeezes out of the end"
							+(condomBreaks?", breaking [npc.namePos] condom in the process,":"")+" and is safely laid deep within [npc2.her] fuckable [npc2.crotchBoobs].");
					if(eggCount>1) {
						sb.append(" Still with "+(Util.intToString(eggCount-1))+" egg"+(eggCount>2?"s":"")+" left to lay, [npc.name] [npc.verb(pull)] out and then immediately [npc.verb(penetrate)] [npc2.namePos] other [npc2.nippleCrotch(true)]."
								+ " Continuing to let out a series of deeply satisfied [npc.moans], [npc.she] quickly [npc.verb(turn)] [npc2.namePos] [npc2.crotchBoobs] into egg-incubation chambers.");
					}
					sb.append("</br>");
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("With one last [npc.moan], [npc.name] roughly [npc.verb(pull)] back, before dominantly rubbing [npc.her] [npc.hand] over [npc2.namePos] swollen [npc2.crotchBoobs] and growling,");
							sb.append(" [npc.speech(Make sure to take good care of my "+(eggCount>1?"children":"child")+", [npc2.bitch]!)]");
							break;
						default:
							sb.append("With one last [npc.moan], [npc.name] [npc.verb(pull)] back, before lovingly rubbing [npc.her] [npc.hand] over [npc2.namePos] swollen [npc2.crotchBoobs] and sighing,");
							sb.append(" [npc.speech(Make sure to take good care of my "+(eggCount>1?"children":"child")+"!)]");
							break;
					}
					break;
				case VAGINA:
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("Not yet finished, [npc.name] [npc.verb(let)] out [npc.a_moan+] and [npc.verb(slam)] [npc.her] "+penetrationAreaPlusText+" as deep as possible into [npc2.namePos] [npc2.pussy].");
							sb.append(" Roughly grinding [npc.her] [npc.hips] into [npc2.namePos] groin, [npc.she] [npc.verb(sneer)],");
							if(eggCount==1) {
								sb.append(" [npc.speech(You're going to take my egg, [npc2.bitch]!)]");
							} else {
								sb.append(" [npc.speech(I'm going to stuff you full of eggs, [npc2.bitch]!)]");
							}
							break;
						default:
							sb.append("Not yet finished, [npc.name] [npc.verb(let)] out [npc.a_moan+] and [npc.verb(push)] [npc.her] "+penetrationAreaPlusText+" as deep as possible into [npc2.namePos] [npc2.pussy].");
							sb.append(" Eagerly grinding [npc.her] [npc.hips] into [npc2.namePos] groin, [npc.she] excitedly [npc.verb(exclaim)],");
							if(eggCount==1) {
								sb.append(" [npc.speech(I'm going to put my egg in you!)]");
							} else {
								sb.append(" [npc.speech(I'm going to stuff you with eggs!)]");
							}
							break;
					}
					sb.append("</br>");
					sb.append("With that, [npc2.name] suddenly [npc2.verb(feel)] the unmistakable bulbous lump of an egg travelling down the length of [npc.namePos] "+penetrationAreaText+","
							+ " and [npc2.she] can't help but let out an alarmed [npc2.moan] as it squeezes out of the end"+(condomBreaks?", breaking [npc.namePos] condom in the process,":"")+" and is safely laid deep in [npc2.her] womb.");
					if(eggCount>1) {
						sb.append(" Still with "+(Util.intToString(eggCount-1))+" egg"+(eggCount>2?"s":"")+" left to lay, [npc.name] [npc.verb(keep)] [npc.her] "+penetrationAreaPlusText+" hilted deep in [npc2.namePos] [npc2.pussy+]"
								+ " and [npc.verb(continue)] to let out a series of deeply satisfied [npc.moans] as [npc.she] [npc.verb(turn)] [npc2.her] womb into an egg-incubation chamber.");
					}
					sb.append("</br>");
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("Finally, with one last [npc.moan], [npc.name] roughly [npc.verb(pull)] back, before dominantly rubbing [npc.her] [npc.hand] over [npc2.namePos] swollen belly and growling,");
							sb.append(" [npc.speech(Make sure to take good care of my "+(eggCount>1?"children":"child")+", [npc2.bitch]!)]");
							break;
						default:
							sb.append("Finally, with one last [npc.moan], [npc.name] [npc.verb(pull)] back, before lovingly rubbing [npc.her] [npc.hand] over [npc2.namePos] swollen belly and sighing,");
							sb.append(" [npc.speech(Make sure to take good care of my "+(eggCount>1?"children":"child")+"!)]");
							break;
					}
					break;
			}
		}
		
		return UtilText.parse(characterOrgasming, target, sb.toString());
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
		List<InventorySlot> slotsTargeted = new ArrayList<>(area.getAssociatedInventorySlots(target));
		
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
	
	private static String getClothingCummedOnText(GameCharacter characterOrgasming, GameCharacter target, List<CoverableArea> areas, List<AbstractClothing> clothing) {
		List<InventorySlot> nakedAreas = new ArrayList<>();
		for(CoverableArea area : areas) {
			nakedAreas.addAll(getNakedAreasCummedOn(target, area));
		}
		nakedAreas = new ArrayList<>(new HashSet<>(nakedAreas));
		
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

	private static String getClothingCummedOnText(GameCharacter characterOrgasming, List<CoverableArea> areas, List<AbstractClothing> clothing) {
		List<InventorySlot> nakedAreas = new ArrayList<>();
		for(CoverableArea area : areas) {
			nakedAreas.addAll(getNakedAreasCummedOn(characterOrgasming, area));
		}
		nakedAreas = new ArrayList<>(new HashSet<>(nakedAreas));

		if(nakedAreas.isEmpty()) {
			return UtilText.parse(characterOrgasming,
					" all over [npc.her] "+Util.clothesToStringList(clothing, false)+"."
							+ " [npc.She] [npc.verb(let)] out [npc.a_moan+] as [npc.her] [npc.cum+] splatters onto [npc.her] clothing, making a mess of [npc.her] outfit.");
		} else {
			return UtilText.parse(characterOrgasming,
					" all over [npc.her] "+Util.clothesToStringList(clothing, false)+", as well as [npc.her] exposed "+Util.inventorySlotsToParsedStringList(nakedAreas, characterOrgasming)+"."
							+ " [npc.She] [npc.verb(let)] out [npc.a_moan+] as [npc.her] [npc.cum+] splatters onto [npc.her] clothing, making a mess of [npc.her] outfit.");
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
	
	private static String getSpinneretInflationText(GameCharacter characterOrgasming, GameCharacter target, float cumAmount) {
		if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
			return ("<br/>[npc2.NamePos] "+(target.hasTailSpinneret()?"[npc2.tail]":"abdomen")+" swells out to a massive, over-inflated size as it distends from the sheer amount of cum that [npc.nameHas] pumped inside of [npc2.her] spinneret.");
			
		} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
			return ("<br/>[npc2.NamePos] "+(target.hasTailSpinneret()?"[npc2.tail]":"abdomen")+" swells out as it distends from the huge amount of cum that [npc.nameHas] pumped inside of [npc2.her] spinneret.");
			
		} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
			return ("<br/>[npc2.NamePos] "+(target.hasTailSpinneret()?"[npc2.tail]":"abdomen")+" swells out a little as it distends from the sheer amount of cum that [npc.nameHas] pumped inside of [npc2.her] spinneret.");
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
	
	private static String getGenericVaginaOrgasmDescription(SexActionInterface sexAction, GameCharacter characterOrgasming, OrgasmCumTarget targetArea) {
		genericOrgasmSB.setLength(0);
		
		genericOrgasmSB.append("A desperate, shuddering heat suddenly crashes up from [npc.namePos] [npc.pussy+], and [npc.she] [npc.verb(let)] out a manic squeal as a blinding wave of pure ecstasy washes over [npc.herHim].");
		
		GameCharacter characterPenetrating = null;
		if(Main.sex.getCharacterOngoingSexArea(characterOrgasming, SexAreaOrifice.VAGINA).size()>0) {
			characterPenetrating = Main.sex.getCharacterOngoingSexArea(characterOrgasming, SexAreaOrifice.VAGINA).get(0);
		}
		SexAreaPenetration penetration = Main.sex.getFirstOngoingSexAreaPenetration(characterOrgasming, SexAreaOrifice.VAGINA);
		
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
				SexAreaPenetration analPenetrator = Main.sex.getFirstOngoingSexAreaPenetration(characterOrgasming, SexAreaOrifice.ANUS);
				if(characterOrgasming.getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()
						&& analPenetrator!=null
						&& analPenetrator.isTakesVirginity()) {
					GameCharacter characterPenetratingAss = Main.sex.getOngoingCharactersUsingAreas(characterOrgasming, SexAreaOrifice.ANUS, analPenetrator).iterator().next();
					genericOrgasmSB.append(UtilText.parse(characterOrgasming, characterPenetratingAss,
							" [npc.NamePos] [npc.pussy+] clenches down hard, and despite the fact that it's not being penetrated,"
									+ " [npc.namePos] pleasure isn't in any way lessened due to the fact that [npc.sheIs] "
									+(Main.sex.getSexPace(characterOrgasming)==SexPace.SUB_RESISTING?"focusing primarily on":"loving")
									+" the feeling of [npc2.namePos] "+analPenetrator.getName(characterPenetratingAss)+" in [npc.her] ass."));
				} else {
					genericOrgasmSB.append(" [npc.NamePos] [npc.pussy+] clenches down hard, and the wave of disappointment upon finding itself empty almost overwhelms the pleasure that radiates up through [npc.her] groin.");
				}
			}
		}
		
		if(targetArea == OrgasmCumTarget.LILAYA_PANTIES && !Main.game.getPlayer().hasPenisIgnoreDildo()) {
			genericOrgasmSB.append(" As you squeal and pant, you bring Lilaya's panties up to your face, and breathe in your demonic [lilaya.relation(pc)]'s musky, perfume-laced scent as you imagine her pussy pressing against the soft fabric.");
		}
		
		if(characterOrgasming.isVaginaSquirter()) {
			List<String> ejaculateDescriptors = new ArrayList<>();
			for(FluidModifier mod : FluidModifier.values()) {
				if(characterOrgasming.hasGirlcumModifier(mod)) {
					ejaculateDescriptors.add(mod.getName());
				}
			}
			ejaculateDescriptors.add("wet");
			genericOrgasmSB.append("<br/>As [npc.namePos] [npc.pussy+] uncontrollably spasms and quivers with delight, it suddenly squirts out a huge amount of hot, "+Util.randomItemFrom(ejaculateDescriptors)+" female ejaculate.");
			
			if(targetArea == OrgasmCumTarget.LILAYA_PANTIES) {
				genericOrgasmSB.append("<br/>You quickly drop Lilaya's panties down between your legs, squirting directly into her underwear as you let out [pc.a_moan+].");
				LilayasRoom.lilayasPanties.setDirty(null, true);
				
			} else {
				AbstractClothing vaginaClothing = Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.VAGINA);
				if(vaginaClothing!=null) {
					if(!vaginaClothing.getItemTags().contains(ItemTag.PLUGS_VAGINA)
							&& !vaginaClothing.getItemTags().contains(ItemTag.SEALS_VAGINA)) {
						genericOrgasmSB.append(" [npc.She] [npc.verb(let)] out a deep sigh as [npc.she] [npc.verb(feel)] [npc.her] "
							+vaginaClothing.getName()+" "+(vaginaClothing.getClothingType().isPlural()?"are":"is")+" quickly getting dirtied by [npc.her] fluids.");
						
					} else {
						genericOrgasmSB.append(" As [npc.her] "+vaginaClothing.getName()+" "+(vaginaClothing.getClothingType().isPlural()?"are":"is")+" sealing [npc.her] [npc.pussy], nothing gets dirtied by [npc.her] fluids.");
					}
					vaginaClothing.setDirty(Main.sex.getCharacterPerformingAction(), true);
					
				} else {
					Set<GameCharacter> charactersEatingOut = new HashSet<>(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
					charactersEatingOut.addAll(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaOrifice.MOUTH));
					
					for(GameCharacter character : charactersEatingOut) { // Should only be one character
						genericOrgasmSB.append(UtilText.parse(characterOrgasming, character,
								" As [npc2.nameIs] eating [npc.herHim] out, [npc.namePos] fluids squirt out both into [npc2.her] mouth, as well as all over [npc2.her] [npc2.face]."));
					}
				}
			}
		}
		
		if(sexAction==GENERIC_ORGASM_OVIPOSITOR_CLIT_EGG_LAYING && characterOrgasming.equals(Main.sex.getCharacterLayingEggs())) {
			genericOrgasmSB.append(eggLayingTargetDescription(SexAreaPenetration.CLIT, characterOrgasming, Main.sex.getCharactersHavingOngoingActionWith(characterOrgasming, SexAreaPenetration.CLIT).get(0), null));
		}
		
		genericOrgasmSB.append("<br/><br/>With a deeply-satisfied sigh, [npc.namePos] feminine climax starts to fade, and [npc.she] [npc.verb(take)] a few deep gasps of air as [npc.she] [npc.verb(seek)] to catch [npc.her] breath.");
		
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
		
		if(!Main.sex.getAllOngoingSexAreas(characterOrgasming, SexAreaPenetration.PENIS).isEmpty()) {
			characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(characterOrgasming, SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(characterOrgasming, SexAreaPenetration.PENIS).get(0);
			
			List<GameCharacter> charactersPenetrated = new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, areaContacted));
			if(charactersPenetrated.contains(Main.sex.getTargetedPartner(characterOrgasming))) {
				characterPenetrated = Main.sex.getTargetedPartner(characterOrgasming);
			}
		}
		
		descriptionSB.append("<p>"
								+getPositionPreparation(characterOrgasming, characterPenetrated!=null?characterPenetrated:Main.sex.getTargetedPartner(characterOrgasming))
							+"</p>");
		
		if(characterOrgasming.hasTraitActivated(Perk.AHEGAO)) {
			descriptionSB.append(getAhegaoDescription(characterOrgasming, Main.sex.getTargetedPartner(characterOrgasming)));
		}
		
		if(characterOrgasming.hasPenisIgnoreDildo()) {
			descriptionSB.append("<p>"
									+getGenericPenisOrgasmDescription(sexAction, characterOrgasming, characterPenetrated, target, sexAction.getCondomFailure(characterOrgasming, characterPenetrated), false)
								+"</p>");
			
			if(target==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE) {
				GameCharacter secondaryTarget = getSecondaryCreampieTarget(characterPenetrated, (SexAreaOrifice) Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0));
				
				descriptionSB.append("<p>"
										+getGenericPenisOrgasmDescription(sexAction, characterOrgasming, secondaryTarget, target, sexAction.getCondomFailure(characterOrgasming, characterPenetrated), true)
									+"</p>");
			}
		}
		
		if(sexAction==GENERIC_ORGASM_OVIPOSITOR_CLIT_EGG_LAYING) {
			characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(characterOrgasming, SexAreaPenetration.CLIT).get(0);
			genericOrgasmSB.append("<p>"
										+ eggLayingTargetDescription(SexAreaPenetration.CLIT, characterOrgasming, characterPenetrated, null)
									+"</p>");
		}
		
		if(characterOrgasming.hasVagina()) {
			descriptionSB.append("<p>"
									+getGenericVaginaOrgasmDescription(sexAction, characterOrgasming, target)
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
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			return Main.game.getPlayer().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.game.getPlayer()).get(0), false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Main.game.getPlayer().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.game.getPlayer()).get(0), true).applyEffects();
			if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Main.game.getPlayer().getPenisOrgasmCumQuantity() != CumProduction.ZERO_NONE) {
				Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(Main.game.getPlayer(), true);
			}
		}
		
		@Override
		public boolean endsSex() {
			return Main.game.getPlayer().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.game.getPlayer()).get(0), false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_CREAMPIE = new SexAction(
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
			return Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, getCharacterToBeCreampied()).get(0);
		}
		@Override
		public boolean isBaseRequirementsMet() {
			if(!Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()) {
				return false;
			}
			
			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return false;
			}
			
			SexAreaInterface areaContacted = getAreaToBeCreampied();
			
			boolean isPenetratingSuitableOrifice  = false;
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ASS:
					case THIGHS:
						return false;
					case ARMPITS:
					case BREAST:
					case BREAST_CROTCH:
					case NIPPLE:
					case NIPPLE_CROTCH:
					case ANUS:
					case MOUTH:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
					case VAGINA:
					case SPINNERET:
						isPenetratingSuitableOrifice = true;
						break;
				}
			} else {
				switch((SexAreaPenetration)areaContacted) {
					case CLIT:
					case PENIS:
					case TAIL:
					case TENTACLE:
					case TONGUE:
						break;
					case FINGER:
					case FOOT:
						isPenetratingSuitableOrifice = true;
						break;
				}
			}
			
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			
			// Will not use if obeying pull out requests:
			if((Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())!=OrgasmBehaviour.CREAMPIE
					&& !Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction()) // Only allow this action to be blocked if no forced creampie.
					&& Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())>0)
				|| Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.PULL_OUT) {
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
			if(getAreaToBeCreampied()==SexAreaOrifice.VAGINA
					&& Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative()
					&& !Main.sex.getCharacterTargetedForSexAction(this).isVisiblyPregnant()) {
				return SexActionPriority.LOW;
			}
			if((Math.random()<0.66f
					|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_STUD).isPositive()
					|| Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<0
					|| (getAreaToBeCreampied()==SexAreaOrifice.VAGINA && Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)))
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
			
			GameCharacter characterPenetrated = getCharacterToBeCreampied();
			SexAreaInterface areaContacted = getAreaToBeCreampied();
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
						return "Armpit climax";
					case ANUS:
						return "Anal creampie";
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
						return "Nipple creampie";
					case THIGHS:
						return "Thigh-sex climax";
					case URETHRA_PENIS: case URETHRA_VAGINA:
						return "Urethra creampie";
					case VAGINA:
						return "Creampie";
					case SPINNERET:
						return "Spinneret creampie";
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
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter character = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
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
			
			GameCharacter characterPenetrated = getCharacterToBeCreampied();
			SexAreaInterface areaContacted = getAreaToBeCreampied();
			String returnString = "You've reached your climax, and can't hold back your orgasm any longer. Creampie [npc2.name].";
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
						returnString = "Keep on rubbing your [npc.cock+] over [npc2.namePos] [npc2.armpit+] as you orgasm, spurting your [npc.cum+] all over [npc2.her] arm and pit.";
						break;
					case ANUS:
						if(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
							if(Main.game.isPenetrationLimitationsEnabled() && Main.sex.getCharacterPerformingAction().isFullPenetrationTooLongToFit(SexAreaPenetration.PENIS, characterPenetrated, (SexAreaOrifice)areaContacted, false)) {
								returnString = "[style.italicsBad([npc2.NamePos] asshole is not deep enough for you to insert your knot)],"
										+ " and so you'll just have to make do with pushing your [npc.cock+] deep into [npc2.her] butt before filling [npc2.herHim] with your [npc.cum+].";
							} else {
								returnString = "Hold back from inserting your knot, and just push your [npc.cock+] deep inside [npc2.namePos] [npc2.asshole+] before filling [npc2.herHim] with your [npc.cum+].";
							}
							
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
						if(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
							if(Main.game.isPenetrationLimitationsEnabled() && Main.sex.getCharacterPerformingAction().isFullPenetrationTooLongToFit(SexAreaPenetration.PENIS, characterPenetrated, (SexAreaOrifice)areaContacted, false)) {
								returnString = "[style.italicsBad([npc2.NamePos] pussy is not deep enough for you to insert your knot)],"
										+ " and so you'll just have to make do with pushing your [npc.cock+] deep into [npc2.her] cunt before filling"
										+(characterPenetrated.isPregnant() || characterPenetrated.hasIncubationLitter(SexAreaOrifice.VAGINA)
												?" [npc2.herHim]"
												:" [npc2.her] womb")
										+ " with your [npc.cum+].";
							} else {
								returnString = "Hold back from inserting your knot, and just push your [npc.cock+] deep inside [npc2.namePos] [npc2.pussy+] before filling"
										+(characterPenetrated.isPregnant() || characterPenetrated.hasIncubationLitter(SexAreaOrifice.VAGINA)
												?" [npc2.herHim]"
												:" [npc2.her] womb")
										+ " with your [npc.cum+].";
							}
							
						} else {
							returnString = "Push your [npc.cock+] deep inside [npc2.namePos] [npc2.pussy+], before filling [npc2.her]"
										+(characterPenetrated.isPregnant() || characterPenetrated.hasIncubationLitter(SexAreaOrifice.VAGINA)
												?" [npc2.pussy]"
												:" womb")
										+ " with your [npc.cum+].";
						}
						break;
					case SPINNERET:
						returnString = "Push your [npc.cock+] deep into [npc2.namePos] spinneret, before filling [npc2.herHim] with your [npc.cum+].";
						break;
				}
			} else {
				switch((SexAreaPenetration)areaContacted) {
					case CLIT:
						break;
					case FINGER:
						if(Main.sex.getCharacterPerformingAction().equals(characterPenetrated)) {
							returnString = "Carry on stroking your cock, before spurting your [npc.cum+] all over your [npc.hand+].";
						} else {
							returnString = "Carry on focusing on the pleasure that you're getting from [npc2.name] giving you a handjob, before pulling back slightly and spurting your [npc.cum+] all over [npc2.her] [npc2.hand+].";
						}
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
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterPerformingAction(), null); // Need this before effects, as effects can set locking (such as in Lyssieth's demon TF scenes)
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).applyEffects();
		}
		
		@Override
		public String applyEndEffects(){
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).applyEndEffects();
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
				SexAreaInterface areaContacted = getAreaToBeCreampied();
				
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
						case ARMPITS:
							return Util.newArrayListOfValues(
									CoverableArea.ARMPITS);
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
						case SPINNERET:
							break;
					}
				}
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).isEndsSex()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)//TODO should be moved out into preganncy roulette character method
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY);
		}
	};
	
	public static final SexAction GENERIC_ORGASM_KNOTTING = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private GameCharacter getCharacterToBeKnotted() {
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
		private SexAreaInterface getAreaToBeKnotted() {
			return Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, getCharacterToBeKnotted()).get(0);
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(!Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
					|| !Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
				return false;
			}
			
			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()
					|| Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, getCharacterToBeKnotted()).isEmpty()) {
				return false;
			}

			SexAreaInterface areaContacted = getAreaToBeKnotted();
			GameCharacter characterPenetrated = getCharacterToBeKnotted();
			
			if(!areaContacted.isOrifice() || !((SexAreaOrifice)areaContacted).isInternalOrifice()) {
				return false;
			}
			
			// Cannot use if orifice is not deep enough
			// (Does not take into account willingness to fully penetrate, as the orgasming character is assumed to want to knot even if not normally willing to deeply penetrate.)
			if(Main.game.isPenetrationLimitationsEnabled()
					&& Main.sex.getCharacterPerformingAction().isFullPenetrationTooLongToFit(SexAreaPenetration.PENIS, characterPenetrated, (SexAreaOrifice)areaContacted, false)) {
				return false;
			}
			
			// Will not use if obeying pull out requests:
			if((Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())!=OrgasmBehaviour.CREAMPIE
					&& !Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction()) // Only allow this action to be blocked if no forced creampie.
					&& Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())>0)
				|| Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.PULL_OUT) {
				return false;
			}
			
			return true;
		}
		
		@Override
		public SexActionPriority getPriority() { // Has same priority as normal creampie:
			if(Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.CREAMPIE) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(getAreaToBeKnotted()==SexAreaOrifice.VAGINA
					&& Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative()
					&& !Main.sex.getCharacterTargetedForSexAction(this).isVisiblyPregnant()) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f
					|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_STUD).isPositive()
					|| Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<0
					|| (getAreaToBeKnotted()==SexAreaOrifice.VAGINA && Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION))) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
					return "Forced creampie (Knot!)";
					
				} else if(bodypart == Arm.class) {
					return "Hug-locked (Knot!)";
					
				} else if(bodypart == Leg.class) {
					return "Leg-locked (Knot!)";
					
				} else if(bodypart == Tail.class) {
					return "Tail-locked (Knot!)";
					
				} else if(bodypart == Wing.class) {
					return "Wing-locked (Knot!)";
					
				} else if(bodypart == Tentacle.class) {
					return "Tentacle-locked (Knot!)";
				}
			}
			
			return UtilText.parse(getCharacterToBeKnotted(), "Knot [npc.herHim]");
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter character = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
					return UtilText.parse(character,
							"[npc.NameIsFull] using [npc.her] advantageous position to force you to cum inside of [npc.herHim]!"
							+ " Give [npc.herHim] what [npc.she] wants and knot [npc.herHim]!");
					
				} else if(bodypart == Arm.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.arms+] around your lower body, thereby forcing you to cum inside of [npc.herHim]!"
							+ " Give [npc.herHim] what [npc.she] wants and knot [npc.herHim]!");
					
				} else if(bodypart == Leg.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.legs+] around your lower body, thereby forcing you to cum inside of [npc.herHim]!"
							+ " Give [npc.herHim] what [npc.she] wants and knot [npc.herHim]!");
					
				} else if(bodypart == Tail.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] "+(character.getTailCount()>1?"[npc.tails+]":"[npc.tail]")+" around your lower body, thereby forcing you to cum inside of [npc.herHim]!"
							+ " Give [npc.herHim] what [npc.she] wants and knot [npc.herHim]!");
					
				} else if(bodypart == Wing.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.wingSize] [npc.wings] around your body, thereby forcing you to cum inside of [npc.herHim]!"
							+ " Give [npc.herHim] what [npc.she] wants and knot [npc.herHim]!");
					
				} else if(bodypart == Tentacle.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.tentacles+] around your lower body, thereby forcing you to cum inside of [npc.herHim]!"
							+ " Give [npc.herHim] what [npc.she] wants and knot [npc.herHim]!");
				}
			}
			
			GameCharacter characterPenetrated = getCharacterToBeKnotted();
			SexAreaInterface areaContacted = getAreaToBeKnotted();
			String returnString = "You've reached your climax, and can't hold back your orgasm any longer. Push your knot inside of [npc2.namePos] "+areaContacted.getName(characterPenetrated)+" and fill [npc2.herHim] with your [npc.cum+].";
			
			switch((SexAreaOrifice)areaContacted) {
				case ANUS:
					returnString = "Push your cock fully into [npc2.namePos] [npc2.asshole+] before your knot swells up, which will lock you inside of [npc2.herHim] as you fill [npc2.herHim] with your [npc.cum+].";
					break;
				case MOUTH:
					returnString = "Push your cock fully down [npc2.namePos] throat before your knot swells up, which will lock you inside of [npc2.herHim] as you fill [npc2.her] stomach with your [npc.cum+].";
					break;
				case NIPPLE:
					returnString = "Push your cock fully into [npc2.namePos] [npc2.nipple+] before your knot swells up, which will lock you inside of [npc2.herHim] as you fill [npc2.her] breast with your [npc.cum+].";
					break;
				case NIPPLE_CROTCH:
					returnString = "Push your cock fully into [npc2.namePos] [npc2.crotchNipple+] before your knot swells up, which will lock you inside of [npc2.herHim] as you fill [npc2.her] [npc2.crotchBoob] with your [npc.cum+].";
					break;
				case URETHRA_PENIS:
					returnString = "Push your cock fully into the urethra of [npc2.namePos] [npc2.cock+] before your knot swells up, which will lock you inside of [npc2.herHim] as you fill [npc2.her] bladder with your [npc.cum+].";
					break;
				case URETHRA_VAGINA:
					returnString = "Push your cock fully into the urethra of [npc2.namePos] [npc2.pussy+] before your knot swells up, which will lock you inside of [npc2.herHim] as you fill [npc2.her] bladder with your [npc.cum+].";
					break;
				case VAGINA:
					returnString = "Push your cock fully into [npc2.namePos] [npc2.pussy+] before your knot swells up,"
							+ " which will lock you inside of [npc2.herHim] as you fill [npc2.her]"
										+(characterPenetrated.isPregnant() || characterPenetrated.hasIncubationLitter(SexAreaOrifice.VAGINA)
												?" [npc2.pussy]"
												:" womb")
										+ " with your [npc.cum+].";
					break;
				default:
					return "Knotting error! (Code 1)";
			}
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, false).getDescription();
		}
		
		@Override
		public String applyPreParsingEffects() {
			Main.sex.addCharactersKnottedTogether(Main.sex.getCharacterPerformingAction(), getCharacterToBeKnotted()); // Added so that the generic orgasm description parses it as a knotting action.
			return "";
		}
		
		@Override
		public void applyEffects() {
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterPerformingAction(), null); // Need this before effects, as effects can set locking (such as in Lyssieth's demon TF scenes)
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).applyEffects();
		}
		
		@Override
		public String applyEndEffects() {
			// Moved to start of Main.sex.endSexTurn(), so that there's time to stretch.
//			Main.sex.removeCharactersKnottedTogether(Main.sex.getCharacterPerformingAction()); // Remove as the generic orgasm description has already been parsed.
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).applyEndEffects();
			return "";
		}
		
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			SexAreaInterface areaContacted = getAreaToBeKnotted();
			GameCharacter characterPenetrated = getCharacterToBeKnotted();
			
			if(cumTarget.equals(characterPenetrated)) {
				return Util.newArrayListOfValues(areaContacted);
					
			} else {
				return null;
			}
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).isEndsSex()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY);
		}
	};
	
	public static final SexAction GENERIC_ORGASM_OVIPOSITOR_PENIS_EGG_LAYING_BLOCKED = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		private GameCharacter getCharacterToBeEgged() {
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
		private SexAreaInterface getAreaToBeEgged() {
			return Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, getCharacterToBeEgged()).get(0);
		}
		@Override
		public boolean isBaseRequirementsMet() {
			if(!Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
					|| !Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.OVIPOSITOR)
					|| !Main.sex.getCharacterPerformingAction().hasVagina()
					|| !Main.sex.getCharacterPerformingAction().isVaginaEggLayer()
					|| !Main.sex.getCharacterPerformingAction().isPregnant()) {
				return false;
			}
			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			return !Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()
					&& !GENERIC_ORGASM_OVIPOSITOR_PENIS_EGG_LAYING.isBaseRequirementsMet();
		}
		@Override
		public String getActionTitle() {
			return "Egg-laying";
		}
		@Override
		public String getActionDescription() {
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()) {
				return UtilText.parse(getCharacterToBeEgged(),
						"[npc.Name] cannot be impregnated, so you cannot lay eggs in [npc.herHim]!");
			}

			if(Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return UtilText.parse(getCharacterToBeEgged(),
						"Epona will consider egg-laying to be cheating, and so you're not able to do this!");
			}
			
			SexAreaInterface areaContacted = getAreaToBeEgged();
			
			if(!areaContacted.isOrifice()) {
				switch((SexAreaPenetration)areaContacted) {
					case FINGER:
						return UtilText.parse(getCharacterToBeEgged(),
								"You cannot lay eggs while receiving a handjob from [npc.name]!");
					case FOOT:
						return UtilText.parse(getCharacterToBeEgged(),
								"You cannot lay eggs while receiving a [npc.footjob] from [npc.name]!");
					case TAIL:
						return UtilText.parse(getCharacterToBeEgged(),
								"You cannot lay eggs while receiving a tailjob from [npc.name]!");
					case TENTACLE:
						return UtilText.parse(getCharacterToBeEgged(),
								"You cannot lay eggs while receiving a tentaclejob from [npc.name]!");
					case CLIT:
					case PENIS:
					case TONGUE:
						break;
				}
			} else {
				return UtilText.parse(getCharacterToBeEgged(),
						(((SexAreaOrifice) areaContacted).isInternalOrifice()
							?"You cannot lay eggs in [npc.namePos] "
							:"You cannot lay eggs while fucking [npc.namePos] ")
						+areaContacted.getName(getCharacterToBeEgged(), true)+"!");
			}
			return UtilText.parse(getCharacterToBeEgged(),
					"You cannot lay eggs while fucking [npc.namePos] "+areaContacted.getName(getCharacterToBeEgged(), true)+"!");
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public Response toResponse() {
			if(!isBaseRequirementsMet()) {
				return null;
			}
			return convertToNullResponse();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_OVIPOSITOR_PENIS_EGG_LAYING = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		private GameCharacter getCharacterToBeEgged() {
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
		private SexAreaInterface getAreaToBeEgged() {
			return Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, getCharacterToBeEgged()).get(0);
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor penis, an egg-laying vagina, and for the eggs to be fertilised
			if(!Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
					|| !Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.OVIPOSITOR)
					|| !Main.sex.getCharacterPerformingAction().hasVagina()
					|| !Main.sex.getCharacterPerformingAction().isVaginaEggLayer()
					|| !Main.sex.getCharacterPerformingAction().isPregnant()) {
				return false;
			}
			
			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			
			SexAreaInterface areaContacted = getAreaToBeEgged();
			if(!areaContacted.isOrifice()) {
				return false;
			}
			
			boolean isPenetratingSuitableOrifice  = false;
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
					case ASS:
					case THIGHS:
					case BREAST:
					case BREAST_CROTCH:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
						return false;
					case NIPPLE:
						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null
							&& getCharacterToBeEgged().isBreastAbleToIncubateEggs();
						break;
					case NIPPLE_CROTCH:
						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null
							&& getCharacterToBeEgged().isBreastCrotchAbleToIncubateEggs();
						break;
					case ANUS:
					case MOUTH:
						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
						break;
					case SPINNERET:
						// Spinneret transformation restrictions are too complex to handle, so just prevent ability to lay eggs in it.
//						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
						return false;
					case VAGINA:
						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && !getCharacterToBeEgged().isPregnant();
						break;
				}
			}
			
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			
			// Will not use if obeying pull out requests:
			if((Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())!=OrgasmBehaviour.CREAMPIE
					&& !Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction()) // Only allow this action to be blocked if no forced creampie.
					&& Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())>0)
				|| Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.PULL_OUT) {
				return false;
			}
			
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.CREAMPIE) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative()) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f
					|| Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<0
					|| Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
					return "Forced egg-laying!";
					
				} else if(bodypart == Arm.class) {
					return "Hug-locked (egg-laying)!";
					
				} else if(bodypart == Leg.class) {
					return "Leg-locked (egg-laying)!";
					
				} else if(bodypart == Tail.class) {
					return "Tail-locked (egg-laying)!";
					
				} else if(bodypart == Wing.class) {
					return "Wing-locked (egg-laying)!";
					
				} else if(bodypart == Tentacle.class) {
					return "Tentacle-locked (egg-laying)!";
				}
			}
			
			switch((SexAreaOrifice)getAreaToBeEgged()) {
				case ANUS:
					return "Anal egg-laying";
				case MOUTH:
					return "Deepthroat egg-laying";
				case NIPPLE: case NIPPLE_CROTCH:
					return "Nipple egg-laying";
				case VAGINA:
					return "Egg-laying";
				case SPINNERET:
					return "Spinneret egg-laying";
				case ARMPITS:
				case BREAST:
				case ASS:
				case BREAST_CROTCH:
				case THIGHS:
				case URETHRA_PENIS:
				case URETHRA_VAGINA:
					return "";
			}
			return "Egg-laying";
		}
		@Override
		public String getActionDescription() {
			String returnString = "You've reached your climax, and can't hold back your orgasm any longer!";
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter character = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
					returnString = UtilText.parse(character,
							"[npc.NameIsFull] using [npc.her] advantageous position to force you to cum inside of [npc.herHim]!");
					
				} else if(bodypart == Arm.class) {
					returnString = UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.arms+] around your lower body, thereby forcing you to cum inside of [npc.herHim]!");
					
				} else if(bodypart == Leg.class) {
					returnString = UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.legs+] around your lower body, thereby forcing you to cum inside of [npc.herHim]!");
					
				} else if(bodypart == Tail.class) {
					returnString = UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] "+(character.getTailCount()>1?"[npc.tails+]":"[npc.tail]")+" around your lower body, thereby forcing you to cum inside of [npc.herHim]!");
					
				} else if(bodypart == Wing.class) {
					returnString = UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.wingSize] [npc.wings] around your body, thereby forcing you to cum inside of [npc.herHim]!");
					
				} else if(bodypart == Tentacle.class) {
					returnString = UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.tentacles+] around your lower body, thereby forcing you to cum inside of [npc.herHim]!");
				}
			}
			
			GameCharacter characterPenetrated = getCharacterToBeEgged();
			SexAreaInterface areaContacted = getAreaToBeEgged();
			
			switch((SexAreaOrifice)areaContacted) {
				case ANUS:
					returnString += " Use this opportunity to cum deep in [npc2.namePos] [npc2.asshole] and lay your eggs in [npc2.her] stomach.";
					break;
				case MOUTH:
					returnString += " Use this opportunity to cum deep down [npc2.namePos] throat and lay your eggs in [npc2.her] stomach.";
					break;
				case NIPPLE:
					returnString += " Use this opportunity to cum in [npc2.namePos] [npc2.nipple+] and lay your eggs in [npc2.her] [npc2.breasts].";
					break;
				case NIPPLE_CROTCH:
					returnString += " Use this opportunity to cum in [npc2.namePos] [npc2.crotchNipple+] and lay your eggs in [npc2.her] [npc2.crotchBoobs].";
					break;
				case VAGINA:
					returnString += " Use this opportunity to cum deep in [npc2.namePos] [npc2.pussy] and lay your eggs in [npc2.her] womb.";
					break;
				case SPINNERET:
					returnString += " Use this opportunity to cum deep in [npc2.namePos] [npc2.spinneret] and lay your eggs inside of [npc2.herHim].";
					break;
				case ARMPITS:
				case ASS:
				case BREAST:
				case BREAST_CROTCH:
				case THIGHS:
				case URETHRA_PENIS:
				case URETHRA_VAGINA:
					break;
			}
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}
		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, false).getDescription();
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public void applyEffects() {
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterPerformingAction(), null); // Need this before effects, as effects can set locking (such as in Lyssieth's demon TF scenes)
			
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			String areaEgged = getAreaToBeEgged().getName(getCharacterToBeEgged(), true);
			if(getAreaToBeEgged()==SexAreaOrifice.ANUS || getAreaToBeEgged()==SexAreaOrifice.MOUTH) {
				areaEgged = "stomach";
			} else if(getAreaToBeEgged()==SexAreaOrifice.VAGINA) {
				areaEgged = "womb";
			}
			Main.game.getTextEndStringBuilder().append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight([npc.Name] [npc.has] had "+Util.intToString(eggCount)+" egg"+(eggCount>1?"s":"")+" implanted in [npc.her] "+areaEgged+"!)]")
					+ "</p>");
			
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).applyEffects();
		}
		@Override
		public String applyEndEffects(){//TODO end effect stuff
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).applyEndEffects();
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			GameCharacter characterPenetrated = getCharacterToBeEgged();
			SexAreaInterface areaContacted = getAreaToBeEgged();
			if(cumTarget.equals(characterPenetrated)) {
				return Util.newArrayListOfValues(areaContacted);
				
			} else {
				return null;
			}
		}
		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(cumProvider))) {
				switch((SexAreaOrifice)getAreaToBeEgged()) {
					case ARMPITS:
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
					case SPINNERET:
						break;
				}
			}
			return null; 
		}
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_OVIPOSITOR_CLIT_EGG_LAYING_BLOCKED = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		private GameCharacter getCharacterToBeEgged() {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT).get(0);
			
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter lockingCharacter = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT).contains(lockingCharacter)) {
					characterPenetrated = lockingCharacter;
				}
				
			} else { // If not locked, can choose who to cum inside:
				List<GameCharacter> charactersPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
				if(charactersPenetrated.contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					characterPenetrated = Main.sex.getCharacterTargetedForSexAction(this);
				}
			}
			
			return characterPenetrated;
		}
		private SexAreaInterface getAreaToBeEgged() {
			return Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT, getCharacterToBeEgged()).get(0);
		}
		@Override
		public boolean isBaseRequirementsMet() {
			if(Main.sex.getCharacterPerformingAction().getVaginaClitorisSize()==ClitorisSize.ZERO_AVERAGE
					|| !Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.OVIPOSITOR)
					|| !Main.sex.getCharacterPerformingAction().hasVagina()
					|| !Main.sex.getCharacterPerformingAction().isVaginaEggLayer()
					|| !Main.sex.getCharacterPerformingAction().isPregnant()) {
				return false;
			}
			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT).isEmpty()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			return !Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT).isEmpty()
					&& !GENERIC_ORGASM_OVIPOSITOR_CLIT_EGG_LAYING.isBaseRequirementsMet();
		}
		@Override
		public String getActionTitle() {
			return "Egg-laying (clit)";
		}
		@Override
		public String getActionDescription() {
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()) {
				return UtilText.parse(getCharacterToBeEgged(),
						"[npc.Name] cannot be impregnated, so you cannot lay eggs in [npc.herHim]!");
			}

			if(Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return UtilText.parse(getCharacterToBeEgged(),
						"Epona will consider egg-laying to be cheating, and so you're not able to do this!");
			}
			
			SexAreaInterface areaContacted = getAreaToBeEgged();
			
			if(!areaContacted.isOrifice()) {
				switch((SexAreaPenetration)areaContacted) {
					case FINGER:
						return UtilText.parse(getCharacterToBeEgged(),
								"You cannot lay eggs while receiving a handjob from [npc.name]!");
					case FOOT:
						return UtilText.parse(getCharacterToBeEgged(),
								"You cannot lay eggs while receiving a [npc.footjob] from [npc.name]!");
					case TAIL:
						return UtilText.parse(getCharacterToBeEgged(),
								"You cannot lay eggs while receiving a tailjob from [npc.name]!");
					case TENTACLE:
						return UtilText.parse(getCharacterToBeEgged(),
								"You cannot lay eggs while receiving a tentaclejob from [npc.name]!");
					case CLIT:
					case PENIS:
					case TONGUE:
						break;
				}
			} else {
				return UtilText.parse(getCharacterToBeEgged(),
						(((SexAreaOrifice) areaContacted).isInternalOrifice()
							?"You cannot lay eggs in [npc.namePos] "
							:"You cannot lay eggs while fucking [npc.namePos] ")
						+areaContacted.getName(getCharacterToBeEgged(), true)+"!");
			}
			return UtilText.parse(getCharacterToBeEgged(),
					"You cannot lay eggs while fucking [npc.namePos] "+areaContacted.getName(getCharacterToBeEgged(), true)+"!");
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public Response toResponse() {
			if(!isBaseRequirementsMet()) {
				return null;
			}
			return convertToNullResponse();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_OVIPOSITOR_CLIT_EGG_LAYING = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		private GameCharacter getCharacterToBeEgged() {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT).get(0);
			
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter lockingCharacter = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT).contains(lockingCharacter)) {
					characterPenetrated = lockingCharacter;
				}
				
			} else { // If not locked, can choose who to cum inside:
				List<GameCharacter> charactersPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
				if(charactersPenetrated.contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					characterPenetrated = Main.sex.getCharacterTargetedForSexAction(this);
				}
			}
			
			return characterPenetrated;
		}
		private SexAreaInterface getAreaToBeEgged() {
			return Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT, getCharacterToBeEgged()).get(0);
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor clit, an egg-laying vagina, and for the eggs to be fertilised
			if(Main.sex.getCharacterPerformingAction().getVaginaClitorisSize()==ClitorisSize.ZERO_AVERAGE
					|| !Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.OVIPOSITOR)
					|| !Main.sex.getCharacterPerformingAction().hasVagina()
					|| !Main.sex.getCharacterPerformingAction().isVaginaEggLayer()
					|| !Main.sex.getCharacterPerformingAction().isPregnant()) {
				return false;
			}
			
			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT).isEmpty()) {
				return false;
			}

			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			
			SexAreaInterface areaContacted = getAreaToBeEgged();
			if(!areaContacted.isOrifice()) {
				return false;
			}
			
			boolean isPenetratingSuitableOrifice  = false;
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
					case ASS:
					case THIGHS:
					case BREAST:
					case BREAST_CROTCH:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
						return false;
					case NIPPLE:
						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null
							&& getCharacterToBeEgged().isBreastAbleToIncubateEggs();
						break;
					case NIPPLE_CROTCH:
						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null
							&& getCharacterToBeEgged().isBreastCrotchAbleToIncubateEggs();
						break;
					case ANUS:
					case MOUTH:
						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
						break;
					case SPINNERET:
						// Spinneret transformation restrictions are too complex to handle, so just prevent ability to lay eggs in it.
//						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
						return false;
					case VAGINA:
						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && !getCharacterToBeEgged().isPregnant();
						break;
				}
			}
			
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			
			// Will not use if obeying pull out requests:
			if((Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())!=OrgasmBehaviour.CREAMPIE
					&& !Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction()) // Only allow this action to be blocked if no forced creampie.
					&& Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())>0)
				|| Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.PULL_OUT) {
				return false;
			}
			
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.CREAMPIE) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative()) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f
					|| Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<0
					|| Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			switch((SexAreaOrifice)getAreaToBeEgged()) {
				case ANUS:
					return "Anal egg-laying (clit)";
				case MOUTH:
					return "Deepthroat egg-laying (clit)";
				case NIPPLE: case NIPPLE_CROTCH:
					return "Nipple egg-laying (clit)";
				case VAGINA:
					return "Egg-laying (clit)";
				case SPINNERET:
					return "Spinneret egg-laying (clit)";
				case ARMPITS:
				case BREAST:
				case ASS:
				case BREAST_CROTCH:
				case THIGHS:
				case URETHRA_PENIS:
				case URETHRA_VAGINA:
					return "";
			}
			return "Egg-laying (clit)";
		}
		@Override
		public String getActionDescription() {
			String returnString = "You've reached your climax, and can't hold back your orgasm any longer!";
			GameCharacter characterPenetrated = getCharacterToBeEgged();
			SexAreaInterface areaContacted = getAreaToBeEgged();
			
			switch((SexAreaOrifice)areaContacted) {
				case ANUS:
					returnString += " Use this opportunity to push your [npc.clit] deep in [npc2.namePos] [npc2.asshole] and lay your eggs in [npc2.her] stomach.";
					break;
				case MOUTH:
					returnString += " Use this opportunity to push your [npc.clit] deep down [npc2.namePos] throat and lay your eggs in [npc2.her] stomach.";
					break;
				case NIPPLE:
					returnString += " Use this opportunity to push your [npc.clit] deep into [npc2.namePos] [npc2.nipple+] and lay your eggs in [npc2.her] [npc2.breasts].";
					break;
				case NIPPLE_CROTCH:
					returnString += " Use this opportunity to push your [npc.clit] deep into [npc2.namePos] [npc2.crotchNipple+] and lay your eggs in [npc2.her] [npc2.crotchBoobs].";
					break;
				case VAGINA:
					returnString += " Use this opportunity to push your [npc.clit] deep into [npc2.namePos] [npc2.pussy] and lay your eggs in [npc2.her] womb.";
					break;
				case SPINNERET:
					returnString += " Use this opportunity to push your [npc.clit] deep into [npc2.namePos] [npc2.spinneret] and lay your eggs inside of [npc2.herHim].";
					break;
				case ARMPITS:
				case ASS:
				case BREAST:
				case BREAST_CROTCH:
				case THIGHS:
				case URETHRA_PENIS:
				case URETHRA_VAGINA:
					break;
			}
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}
		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, false).getDescription();
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public void applyEffects() {
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterPerformingAction(), null); // Need this before effects, as effects can set locking (such as in Lyssieth's demon TF scenes)
			
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			String areaEgged = getAreaToBeEgged().getName(getCharacterToBeEgged(), true);
			if(getAreaToBeEgged()==SexAreaOrifice.ANUS || getAreaToBeEgged()==SexAreaOrifice.MOUTH) {
				areaEgged = "stomach";
			} else if(getAreaToBeEgged()==SexAreaOrifice.VAGINA) {
				areaEgged = "womb";
			}
			Main.game.getTextEndStringBuilder().append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight([npc.Name] [npc.has] had "+Util.intToString(eggCount)+" egg"+(eggCount>1?"s":"")+" implanted in [npc.her] "+areaEgged+"!)]")
					+ "</p>");
			
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).applyEffects();
		}
		@Override
		public String applyEndEffects() {
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).applyEndEffects();
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).isEndsSex();
		}
	};
	
	
	private static Map<GameCharacter, SexSlot> getSuitableSecondaryCreampieTargets(GameCharacter targetedCharacter) {
		Map<GameCharacter, SexSlot> suitableSecondaryCharacters = null;
		if(Main.sex.isDom(Main.sex.getCharacterPerformingAction())) {
			suitableSecondaryCharacters = new HashMap<>(Main.sex.getSubmissiveParticipants(false));
		} else {
			suitableSecondaryCharacters = new HashMap<>(Main.sex.getDominantParticipants(false));
		}
		suitableSecondaryCharacters.remove(targetedCharacter);
		
		return suitableSecondaryCharacters;
	}
	
	private static GameCharacter getSecondaryCreampieTarget(GameCharacter targetedCharacter, SexAreaOrifice areaContacted) {
		Map<GameCharacter, SexSlot> suitableSecondaryCharacters = getSuitableSecondaryCreampieTargets(targetedCharacter);
		
		GameCharacter secondaryTarget = null;
		
		for(Entry<GameCharacter, SexSlot> entry : suitableSecondaryCharacters.entrySet()) {
//			System.out.println(entry.getKey().getName(true)+" check");
			if((areaContacted!=SexAreaOrifice.VAGINA || entry.getKey().hasVagina()) && Main.sex.isOrificeFree(entry.getKey(), areaContacted)) {
//				System.out.println(entry.getKey().getName(true)+" free");
				try {
					if(Main.sex.getPosition().getSexInteractions(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()), Main.sex.getSexPositionSlot(entry.getKey())).getInteractions().get(SexAreaPenetration.PENIS).contains(areaContacted)) {
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
			if(!Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()) {
				return false;
			}
			if(Main.sex.getCharacterPerformingAction().getPenisCumStorage()==CumProduction.ZERO_NONE) {
				return false;
			}
			Map<GameCharacter, SexSlot> suitableSecondaryCharacters = getSuitableSecondaryCreampieTargets(Main.sex.getCharacterTargetedForSexAction(this));
			if(suitableSecondaryCharacters.isEmpty()) {
				return false;
			}
			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return false;
			}
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			if(areaContacted.isPenetration()) {
				return false;
			}
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			if(secondaryTarget==null) {
				return false;
			}
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
					case ASS:
					case THIGHS:
					case BREAST:
					case BREAST_CROTCH:
					case NIPPLE:
					case NIPPLE_CROTCH:
					case MOUTH:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
					case SPINNERET:
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
			if((Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())!=OrgasmBehaviour.CREAMPIE
					&& !Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())>0)
				|| Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.PULL_OUT
				|| Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) { // Cannot double creampie if someone is forcing creampie.
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
			if(Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0)==SexAreaOrifice.VAGINA
					&& Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative()
					&& (!Main.sex.getCharacterTargetedForSexAction(this).isVisiblyPregnant() || !getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).isVisiblyPregnant())) {
				return SexActionPriority.LOW;
			}
			if((Math.random()<0.5f
					|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_STUD).isPositive()
					|| Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<0
					|| (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0)==SexAreaOrifice.VAGINA
							&& Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)))
				&& !knotRequestObeyed) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		
		@Override
		public String getActionTitle() {
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
					case ASS:
					case BREAST:
					case BREAST_CROTCH:
					case MOUTH:
					case NIPPLE:
					case NIPPLE_CROTCH:
					case THIGHS:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
					case SPINNERET:
						break;
					case ANUS:
						return "Double anal creampie";
					case VAGINA:
						return "Double creampie";
				}
			}
			return "Double creampie";
		}

		@Override
		public String getActionDescription() {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryCharacterPenetrated = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			
			String returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
					"You've reached your climax, and can't hold back your orgasm any longer. Give [npc.name] half of your creampie, before quickly pulling out and finishing inside of [npc2.name].");
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
					case ASS:
					case BREAST:
					case BREAST_CROTCH:
					case MOUTH:
					case NIPPLE:
					case NIPPLE_CROTCH:
					case THIGHS:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
					case SPINNERET:
						break;
					case ANUS:
						if(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
							if(Main.game.isPenetrationLimitationsEnabled() && Main.sex.getCharacterPerformingAction().isFullPenetrationTooLongToFit(SexAreaPenetration.PENIS, characterPenetrated, (SexAreaOrifice)areaContacted, false)) {
								returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
										"[style.italicsBad([npc2.NamePos] asshole is not deep enough for you to insert your knot)],"
										+ " and so you'll just have to make do with pushing your [npc.cock+] deep into [npc2.her] butt and filling [npc2.herHim] with your cum after giving [npc.name] half of your load.");
								
							} else {
								returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
										"Keep your knot out of [npc.namePos] [npc.asshole] as you give [npc.herHim] half of your load,"
												+ " before quickly pulling out and similarly avoiding getting locked inside of [npc2.name] as you finish inside of [npc2.her] [npc2.asshole].");
							}
							
						} else {
							returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
									"You've reached your climax, and can't hold back your orgasm any longer. Spurt half of your load into [npc.namePos] [npc.asshole], before quickly pulling out and finishing inside of [npc2.namePos] [npc2.asshole+].");
						}
						break;
					case VAGINA:
						if(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
							if(Main.game.isPenetrationLimitationsEnabled() && Main.sex.getCharacterPerformingAction().isFullPenetrationTooLongToFit(SexAreaPenetration.PENIS, characterPenetrated, (SexAreaOrifice)areaContacted, false)) {
								returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
										"[style.italicsBad([npc2.NamePos] pussy is not deep enough for you to insert your knot)],"
										+ " and so you'll just have to make do with pushing your [npc.cock+] deep into [npc2.her] cunt and filling [npc2.herHim] with your cum after giving [npc.name] half of your load.");
								
							} else {
								returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
										"Keep your knot out of [npc.namePos] [npc.pussy] as you give [npc.herHim] half of your load,"
												+ " before quickly pulling out and similarly avoiding getting locked inside of [npc2.name] as you finish inside of [npc2.her] [npc2.pussy].");
							}
							
						} else {
							returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
									"You've reached your climax, and can't hold back your orgasm any longer. Spurt half of your load into [npc.namePos] [npc.pussy+], before quickly pulling out and finishing inside of [npc2.namePos] [npc2.pussy+].");
						}
						break;
				}
			}
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterPerformingAction(), null); // Need this before effects, as effects can set locking (such as in Lyssieth's demon TF scenes)
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, true).applyEffects();
		}
		
		@Override
		public String applyEndEffects(){
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);

			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, true).applyEndEffects();
			
			Main.sex.stopOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), areaContacted);
			Main.sex.applyOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, secondaryTarget, areaContacted, true);
			
			if(areaContacted==SexAreaOrifice.VAGINA) {
				return Main.sex.applyPenetrationEffects(PenisVagina.PENIS_FUCKING_START, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, secondaryTarget, SexAreaOrifice.VAGINA);
			} else {
				return Main.sex.applyPenetrationEffects(PenisAnus.PENIS_FUCKING_START, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, secondaryTarget, SexAreaOrifice.ANUS);
			}
		}
		
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			
			if(cumTarget.equals(characterPenetrated) || cumTarget.equals(secondaryTarget)) {
				return Util.newArrayListOfValues(areaContacted);
					
			} else {
				return null;
			}
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction())
					&& (cumTarget.equals(Main.sex.getTargetedPartner(cumProvider)) || cumTarget.equals(secondaryTarget))) {
	
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
						case ARMPITS:
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
						case SPINNERET:
							break;
					}
				}
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, true).isEndsSex()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY);
		}
	};
	
	public static final SexAction GENERIC_ORGASM_DOUBLE_KNOTTING = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(!Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
					|| !Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
				return false;
			}
			
			if(Main.sex.getCharacterPerformingAction().getPenisCumStorage()==CumProduction.ZERO_NONE) {
				return false;
			}
			
			Map<GameCharacter, SexSlot> suitableSecondaryCharacters = getSuitableSecondaryCreampieTargets(Main.sex.getCharacterTargetedForSexAction(this));
			if(suitableSecondaryCharacters.isEmpty()) {
				return false;
			}
			
			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return false;
			}
			
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			if(!areaContacted.isOrifice()) {
				return false;
			}

			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			if(secondaryTarget==null) {
				return false;
			}
			
			switch((SexAreaOrifice)areaContacted) {
				case ARMPITS:
				case ASS:
				case THIGHS:
				case BREAST:
				case BREAST_CROTCH:
				case NIPPLE:
				case NIPPLE_CROTCH:
				case MOUTH:
				case URETHRA_PENIS:
				case URETHRA_VAGINA:
				case SPINNERET:
					return false;
				case ANUS:
				case VAGINA:
					if(!secondaryTarget.isOrificeTypeExposed((SexAreaOrifice) areaContacted)
							|| !((SexAreaOrifice) areaContacted).isFree(secondaryTarget)) {
						return false;
					}
					break;
			}
			
			// Cannot use if orifice is not deep enough
			// (Does not take into account willingness to fully penetrate, as the orgasming character is assumed to want to knot even if not normally willing to deeply penetrate.)
			if(Main.game.isPenetrationLimitationsEnabled()
					&& Main.sex.getCharacterPerformingAction().isFullPenetrationTooLongToFit(SexAreaPenetration.PENIS, secondaryTarget, (SexAreaOrifice)areaContacted, false)) {
				return false;
			}
			
			// Will not use if obeying the player and player asked for pull out:
			if((Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())!=OrgasmBehaviour.CREAMPIE
					&& !Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())>0)
				|| Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.PULL_OUT
				|| Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) { // Cannot double creampie if someone is forcing creampie.
				return false;
			}
			
			return true;
		}
		
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.CREAMPIE) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0)==SexAreaOrifice.VAGINA
					&& Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative()
					&& (!Main.sex.getCharacterTargetedForSexAction(this).isVisiblyPregnant() || !getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).isVisiblyPregnant())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.5f
					|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_STUD).isPositive()
					|| Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<0
					|| (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0)==SexAreaOrifice.VAGINA
							&& Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION))) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		
		@Override
		public String getActionTitle() {
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
					case ASS:
					case BREAST:
					case BREAST_CROTCH:
					case MOUTH:
					case NIPPLE:
					case NIPPLE_CROTCH:
					case THIGHS:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
					case SPINNERET:
						break;
					case ANUS:
						return "Double anal knotting";
					case VAGINA:
						return "Double knotting";
				}
			}
			return "Double knotting";
		}

		@Override
		public String getActionDescription() {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryCharacterPenetrated = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			
			String returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
					"You've reached your climax, and can't hold back your orgasm any longer. Give [npc.name] half of your creampie, before quickly pulling out and knotting [npc2.name] as you finish inside of [npc2.herHim].");
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
					case ASS:
					case BREAST:
					case BREAST_CROTCH:
					case MOUTH:
					case NIPPLE:
					case NIPPLE_CROTCH:
					case THIGHS:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
					case SPINNERET:
						break;
					case ANUS:
						returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
								"Keep your knot out of [npc.namePos] [npc.asshole] as you give [npc.herHim] half of your load,"
										+ " before quickly pulling out and ramming your knot into [npc2.namePos] [npc2.asshole], before finishing inside of [npc2.herHim].");
						break;
					case VAGINA:
						returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
								"Keep your knot out of [npc.namePos] [npc.pussy+] as you give [npc.herHim] half of your load,"
										+ " before quickly pulling out and ramming your knot into [npc2.namePos] [npc2.pussy+], before finishing inside of [npc2.herHim].");
						break;
				}
			}
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, false).getDescription();
		}

		@Override
		public String applyPreParsingEffects() {
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			Main.sex.addCharactersKnottedTogether(Main.sex.getCharacterPerformingAction(), secondaryTarget); // Added so that the generic orgasm description parses it as a knotting action.
			return "";
		}
		
		@Override
		public void applyEffects() {
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterPerformingAction(), null); // Need this before effects, as effects can set locking (such as in Lyssieth's demon TF scenes)
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, true).applyEffects();
		}
		
		@Override
		public String applyEndEffects(){
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);

			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, true).applyEndEffects();
			
			Main.sex.stopOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), areaContacted);
			Main.sex.applyOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, secondaryTarget, areaContacted, true);
			
			return Main.sex.applyPenetrationEffects(PenisVagina.PENIS_FUCKING_START, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, secondaryTarget, SexAreaOrifice.VAGINA);
		}
		
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			
			if(cumTarget.equals(characterPenetrated) || cumTarget.equals(secondaryTarget)) {
				return Util.newArrayListOfValues(areaContacted);
					
			} else {
				return null;
			}
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction())
					&& (cumTarget.equals(Main.sex.getTargetedPartner(cumProvider)) || cumTarget.equals(secondaryTarget))) {
	
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
						case ARMPITS:
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
						case SPINNERET:
							break;
					}
				}
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, true).isEndsSex()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY);
		}
	};
	
	public static void applyGenericPullOutEffects(SexActionInterface sexAction, OrgasmCumTarget orgasmCumTarget) {
		if(sexAction!=null && orgasmCumTarget!=null) { // null check for external SexActions using this method via Sex.java
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(sexAction, orgasmCumTarget, true).applyEffects();
		}
		GameCharacter characterPenetrated = null;
		SexAreaInterface areaContacted = null;
		if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
			characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			Main.sex.stopOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, characterPenetrated, areaContacted);
		}
	}
	
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
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FLOOR, false).getDescription();
		}

		@Override
		public SexActionPriority getPriority() {
			// Seemed a little random to have this behaviour...
//			if(!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_STUD).isNegative()) {
//				return SexActionPriority.LOW; // Prefer cumming on someone if they don't dislike cumming.
//			}
			return super.getPriority();
		}
		
		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.FLOOR);
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FLOOR, false).isEndsSex();
		}
	};
	
	
	public static final SexAction GENERIC_ORGASM_WALL = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(OrgasmCumTarget.WALL);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob onto [pc.wall]";
				}
				return "Pull out ([pc.wall])";
			}
			return "Cum on [pc.wall]";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto the [pc.wall].";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.WALL, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.WALL);
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.WALL, false).isEndsSex();
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
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.ASS, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.ASS);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA) {
					return Util.newArrayListOfValues(
							CoverableArea.ASS);
				} else if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA_BEHIND) {
					return Util.newArrayListOfValues(
							CoverableArea.ASS,
							CoverableArea.ANUS,
							CoverableArea.PENIS,
							CoverableArea.VAGINA);
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
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.ASS, false).isEndsSex();
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
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.GROIN);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA) {
					return Util.newArrayListOfValues(
							CoverableArea.ANUS,
							CoverableArea.PENIS,
							CoverableArea.VAGINA);
					
				} else if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA_BEHIND) {
					return Util.newArrayListOfValues(
							CoverableArea.THIGHS);
					
				} else if(cumTarget.getGenitalArrangement()==GenitalArrangement.NORMAL) {
					return Util.newArrayListOfValues(
							CoverableArea.PENIS,
							CoverableArea.VAGINA);
				}
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, false).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_SELF_GROIN = new SexAction(GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(OrgasmCumTarget.SELF_GROIN);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob (own groin)";
				}
				return "Pull out (own groin)";
			}
			return "Cum on own groin";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto your groin.";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_GROIN, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.SELF_GROIN);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA) {
					return Util.newArrayListOfValues(
							CoverableArea.ANUS,
							CoverableArea.PENIS,
							CoverableArea.VAGINA);
				} else if(cumTarget.getGenitalArrangement()==GenitalArrangement.NORMAL) {
					return Util.newArrayListOfValues(
							CoverableArea.PENIS,
							CoverableArea.VAGINA);
				}
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_GROIN, false).isEndsSex();
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
			String breasts = "breasts";
			if(!Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				breasts = "chest";
			}
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob onto "+breasts;
				}
				return "Pull out ("+breasts+")";
			}
			return "Cum on "+breasts;
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc2.namePos] [npc2.breasts].";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.BREASTS, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.BREASTS);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction())
					&& cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.NIPPLES,
						CoverableArea.BREASTS);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.BREASTS, false).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_SELF_BREASTS = new SexAction(GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(OrgasmCumTarget.SELF_BREASTS);
		}
		
		@Override
		public String getActionTitle() {
			String breasts = "breasts";
			if(!Main.sex.getCharacterPerformingAction().hasBreasts()) {
				breasts = "chest";
			}
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob (own "+breasts+")";
				}
				return "Pull out (own "+breasts+")";
			}
			return "Cum on own "+breasts;
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto your [npc.breasts+].";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_BREASTS, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.SELF_BREASTS);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				return Util.newArrayListOfValues(
						CoverableArea.NIPPLES,
						CoverableArea.BREASTS);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_BREASTS, false).isEndsSex();
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
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FACE, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.FACE);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.MOUTH);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FACE, false).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_SELF_FACE = new SexAction(GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(OrgasmCumTarget.SELF_FACE);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob (own face)";
				}
				return "Pull out (own face)";
			}
			return "Cum on own face";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto your [npc.face+].";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_FACE, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.SELF_FACE);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				return Util.newArrayListOfValues(
						CoverableArea.MOUTH);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_FACE, false).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_SELF_HANDS = new SexAction(GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(OrgasmCumTarget.SELF_HANDS);
		}

		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob (own hands)";
				}
				return "Pull out (own hands)";
			}
			return "Cum on own hands";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto your [npc.hands].";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_HANDS, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.SELF_HANDS);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				return Util.newArrayListOfValues(
						CoverableArea.HANDS);
			}
			return null;
		}

		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_HANDS, false).isEndsSex();
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
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob into [npc2.hair(true)]";
				}
				return "Pull out ([npc2.hair(true)])";
			}
			return "Cum in [npc2.hair(true)]";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum into [npc2.namePos] [npc2.hair(true)].";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.HAIR, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.HAIR);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.HAIR);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.HAIR, false).isEndsSex();
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
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.STOMACH, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.STOMACH);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.STOMACH);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.STOMACH, false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_SELF_STOMACH = new SexAction(GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(OrgasmCumTarget.SELF_STOMACH);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob (own stomach)";
				}
				return "Pull out (own stomach)";
			}
			return "Cum on own stomach";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto your stomach.";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_STOMACH, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.SELF_STOMACH);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				return Util.newArrayListOfValues(
						CoverableArea.STOMACH);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_STOMACH, false).isEndsSex();
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
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.LEGS, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.LEGS);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.LEGS);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.LEGS, false).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_SELF_LEGS = new SexAction(GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(OrgasmCumTarget.SELF_LEGS);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob (own [npc.legs])";
				}
				return "Pull out (own [npc.legs])";
			}
			return "Cum on own [npc.legs]";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto your [npc.legs+].";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_LEGS, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.SELF_LEGS);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				return Util.newArrayListOfValues(
						CoverableArea.LEGS,
						CoverableArea.THIGHS);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_LEGS, false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_FEET = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()).hasFeet() && isCumTargetRequirementsMet(OrgasmCumTarget.FEET);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FEET, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.FEET);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.FEET);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FEET, false).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_SELF_FEET = new SexAction(GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasFeet() && isCumTargetRequirementsMet(OrgasmCumTarget.SELF_FEET);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob (own [npc.feet])";
				}
				return "Pull out (own [npc.feet])";
			}
			return "Cum on own [npc.feet]";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto your [npc.feet+].";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_FEET, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.SELF_FEET);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				return Util.newArrayListOfValues(
						CoverableArea.FEET);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_FEET, false).isEndsSex();
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
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
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
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.BACK, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.BACK);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.BACK);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.BACK, false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_ARMPITS = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.isArmpitContentEnabled() && isCumTargetRequirementsMet(OrgasmCumTarget.ARMPITS);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob onto armpit";
				}
				return "Pull out (armpit)";
			}
			return "Cum on armpit";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc2.namePos] armpit.";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.ARMPITS, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.ARMPITS);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.ARMPITS);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.ARMPITS, false).isEndsSex();
		}
	};
	
	
	
	// PREPARATIONS:
	
	public static final SexAction GENERIC_PREPARATION_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !isTakingCock(Main.game.getPlayer(), Main.sex.getCharacterTargetedForSexAction(this));
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
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
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
					if(Main.sex.isSpectator(Main.sex.getCharacterPerformingAction())) {
						description = "Not liking what [npc.sheIs] seeing, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(realise)] that [npc2.nameIs] about to orgasm.";
					} else {
						description = "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] desperately [npc.verb(try)] to pull away from [npc2.name] before [npc2.she] [npc2.verb(orgasm)].";
					}
					break;
			}
			
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterOrgasming(), description);}
	};
	
	private static boolean isAreaFuckedByTarget(SexAction sexAction, GameCharacter characterFucked, SexAreaInterface areaFucked) {
		return Main.sex.getAllOngoingSexAreas(characterFucked, areaFucked).contains(SexAreaPenetration.PENIS)
				&& Main.sex.getCharacterOngoingSexArea(characterFucked, areaFucked).contains(Main.sex.getCharacterTargetedForSexAction(sexAction));
	}
	
	private static boolean isSpecialCreampieLockConditionMet(SexAction sexAction, GameCharacter characterProvidingCreampie, GameCharacter characterReceivingCreampie, SexAreaInterface areaFucked) {
		//Do not allow if sex manager has special pull out conditions:
		if(Main.sex.getInitialSexManager().getCharacterOrgasmBehaviour(characterProvidingCreampie)==OrgasmBehaviour.PULL_OUT) {
			return false;
		}
		if(!Main.sex.getInitialSexManager().isForceCreampieAllowed(characterProvidingCreampie, characterReceivingCreampie)) {
			return false;
		}
		if(Main.sex.isCharacterImmobilised(characterReceivingCreampie)) {
			return false;
		}
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
			if(!isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				return "Keep fucking";
				
			} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
				return "Request cum";

			} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
				return "Request cum on [npc.breasts]";
				
			} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
				return "Request cum on [npc.feet]";
				
			} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
				return "Request cum on [npc.armpit]";
				
			} else {
				return "Request creampie";
			}
		}

		@Override
		public String getActionDescription() {
			if(!isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Ask [npc2.herHim] to keep fucking you with [npc2.her] dildo as [npc2.she] climaxes.";
				
			} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
				return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Ask [npc2.herHim] to fill your stomach with [npc2.her] cum.";

			} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
				return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Ask [npc2.herHim] to cum all over your [pc.breasts+].";
				
			} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
				return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Ask [npc2.herHim] to cum all over your [pc.feet+].";
				
			} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
				return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Ask [npc2.herHim] to cum all over your [pc.armpit+].";
				
			} else {
				return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Ask [npc2.herHim] to fill you with [npc2.her] cum.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return isTakingCock(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING);
		}

		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive()
					&& !Main.sex.getCharacterPerformingAction().isVisiblyPregnant())
				|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isPositive()) {
				return SexActionPriority.NORMAL;
				
			} else {
				return SexActionPriority.LOW;
			}
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				if(!isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
					sb.append("Although not able to speak, [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to keep");
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						sb.append(" pounding [npc.her] [npc.pussy+] as [npc2.she] [npc2.verb(orgasm)].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						sb.append(" pounding [npc.her] [npc.asshole+] as [npc2.she] [npc2.verb(orgasm)].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						sb.append(" pounding [npc.her] [npc.nipple+] as [npc2.she] [npc2.verb(orgasm)].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						sb.append(" pounding [npc.her] [npc.spinneret+] as [npc2.she] [npc2.verb(orgasm)].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						sb.append(" fucking [npc.her] [npc.breasts+] as [npc2.she] [npc2.verb(orgasm)].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						sb.append(" fucking [npc.her] [npc.armpit+] as [npc2.she] [npc2.verb(orgasm)].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						sb.append(" fucking [npc.her] [npc.feet+] as [npc2.she] [npc2.verb(orgasm)].");
						
					} else {
						sb.append(" fucking [npc.herHim] as [npc2.she] [npc2.verb(orgasm)].");
					}
	
				} else {
					sb.append("Although not able to speak, [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)]");
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						sb.append(" [npc2.name] to cum in [npc.her] [npc.pussy+].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						sb.append(" [npc2.name] to cum in [npc.her] [npc.asshole+].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						sb.append(" [npc2.name] to cum in [npc.her] [npc.nipple+].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						sb.append(" [npc2.name] to cum in [npc.her] [npc.spinneret+].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						sb.append(" [npc2.name] to cum all over [npc.her] [npc.breasts+].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						sb.append(" [npc2.name] to cum all over [npc.her] [npc.feet+].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						sb.append(" [npc2.name] to cum all over [npc.her] [npc.armpit+].");
						
					} else {
						sb.append(" [npc2.namePos] cum.");
					}
				}
				
			} else {
				if(!isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
					sb.append("Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence and [npc.verb(cry)] out for [npc2.name] to keep");
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						sb.append(" pounding [npc.her] [npc.pussy+] as [npc2.she] [npc2.verb(orgasm)], [npc.speech(Fuck! Yes! Keep fucking me!)]");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						sb.append(" pounding [npc.her] [npc.asshole+] as [npc2.she] [npc2.verb(orgasm)], [npc.speech(Fuck! Yes! Keep fucking my ass!)]");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						sb.append(" pounding [npc.her] [npc.nipple+] as [npc2.she] [npc2.verb(orgasm)], [npc.speech(Fuck! Yes! Keep fucking my [npc.nipple(true)]!)]");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						sb.append(" pounding [npc.her] [npc.spinneret+] as [npc2.she] [npc2.verb(orgasm)], [npc.speech(Fuck! Yes! Keep fucking my spinneret!)]");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						sb.append(" fucking [npc.her] [npc.breasts+] as [npc2.she] [npc2.verb(orgasm)], [npc.speech(Fuck! Yes! Keep fucking my tits!)]");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						sb.append(" fucking [npc.her] [npc.feet+] as [npc2.she] [npc2.verb(orgasm)], [npc.speech(Fuck! Yes! Keep fucking my [npc.feet]!)]");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						sb.append(" fucking [npc.her] [npc.armpit+] as [npc2.she] [npc2.verb(orgasm)], [npc.speech(Fuck! Yes! Keep fucking my [npc.armpit]!)]");
						
					} else {
						sb.append(" fucking [npc.herHim] as [npc2.she] [npc2.verb(orgasm)], [npc.speech(Fuck! Yes! Keep fucking me!)]");
					}
	
				} else if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
					boolean petName = false;
					if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}

					sb.append("Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence and [npc.verb(cry)] out for [npc2.name] to cum,");
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						sb.append(" [npc.speech(Finish inside of me if you want"+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						sb.append(" [npc.speech(Finish inside of me if you want"+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						sb.append(" [npc.speech(Finish inside of me if you want"+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						sb.append(" [npc.speech(Finish inside of me if you want"+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]");
	
					} else {
						sb.append(" [npc.speech(Yes! Cum for me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]");
					}
					
				} else {
					boolean petName = false;
					if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}

					sb.append("Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out for [npc2.namePos] cum,");
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						sb.append((Main.sex.getCharacterPerformingAction().isVisiblyPregnant()
										?" [npc.speech(Fuck! Cum in me"+(petName?", [#npc.getPetName(npc2)]":"")+"! I need your cum!)]"
										:" [npc.speech(Breed me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Cum in me! I need your cum!)]"));
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						sb.append(" [npc.speech(Fuck! Cum in me"+(petName?", [#npc.getPetName(npc2)]":"")+"! I need your cum!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						sb.append(" [npc.speech(Fuck! Cum in me"+(petName?", [#npc.getPetName(npc2)]":"")+"! I need your cum!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						sb.append(" [npc.speech(Fuck! Cum in me"+(petName?", [#npc.getPetName(npc2)]":"")+"! I need your cum!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						sb.append(" [npc.speech(Yes! Cum for me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Cover my tits with your cum!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						sb.append(" [npc.speech(Fuck! Yes! Cum all over my [npc.feet]"+(petName?", [#npc.getPetName(npc2)]":"")+"!)]");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
						sb.append(" [npc.speech(Cum for me"+(petName?", [#npc.getPetName(npc2)]":"")+"! I want to taste your cum!)]");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						sb.append(" [npc.speech(Fuck! Yes! Cum all over my [npc.armpit]"+(petName?", [#npc.getPetName(npc2)]":"")+"!)]");
						
					} else {
						sb.append(" [npc.speech(Cum for me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Don't pull out!)]");
					}
				}
			}
			
			if(!Main.sex.getCharacterTargetedForSexAction(this).isPlayer() && !Main.sex.isSpectator(Main.sex.getCharacterPerformingAction())) {
				if(Main.sex.getCharacterTargetedForSexAction(this).isSpeechMuffled()) {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+"Grinning as [npc.name] [npc.verb(ask)] this, [npc2.name] [npc2.verb(let)] out a positive-sounding [npc2.moan] in order to let [npc.name] know that that's exactly what [npc2.sheHasFull] planned.");
						
					} else {
						sb.append("<br/><br/>"
								+"Furrowing [npc2.her] eyebrows as [npc.name] [npc.verb(ask)] this,"
								+ " [npc2.name] [npc2.verb(let)] out a negative-sounding [npc2.moan] in order to let [npc.name] know that [npc2.sheIs] not interested in listening to anything [npc.sheHasFull] to say.");
					}
					
				} else {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"Grinning as [npc.name] [npc.verb(ask)] this, [npc2.name] quickly replies in the positive, ",
									"Letting out [npc.a_moan+] after hearing what it is [npc.nameIs] asking of [npc2.herHim], [npc2.name] responds, "));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(Sure, if that's what you want!)]",
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
			
			return sb.toString();
		}

		@Override
		public void applyEffects() {
			Main.sex.getCharactersRequestingCreampie().add(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			List<Fetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if ((Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE).contains(Main.sex.getCharacterPerformingAction()))
					|| (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST).contains(SexAreaPenetration.PENIS)
							&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST).contains(Main.sex.getCharacterPerformingAction()))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_BREASTS_SELF);
				} else {
					fetishes.add(Fetish.FETISH_BREASTS_OTHERS);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_FOOT_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_FOOT_RECEIVING);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ARMPIT_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_ARMPIT_RECEIVING);
				}
			}
			return fetishes;
		}
	};
	

	public static final SexAction GENERIC_PREPARATION_ASK_FOR_KNOT = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Request knot";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Ask [npc2.herHim] to knot you and fill you with [npc2.her] cum.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return isTakingCock(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisModifier(PenetrationModifier.KNOTTED)
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)
					&& !Collections.disjoint(
							Util.newArrayListOfValues(
									SexAreaOrifice.VAGINA, SexAreaOrifice.ANUS, SexAreaOrifice.MOUTH, SexAreaOrifice.SPINNERET, SexAreaOrifice.NIPPLE, SexAreaOrifice.NIPPLE_CROTCH, SexAreaOrifice.URETHRA_PENIS, SexAreaOrifice.URETHRA_VAGINA),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()));
		}

		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive()
					&& !Main.sex.getCharacterPerformingAction().isVisiblyPregnant())
				|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isPositive()) {
				return SexActionPriority.NORMAL;
				
			} else {
				return SexActionPriority.LOW;
			}
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append("Although not able to speak, [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to knot [npc.herHim] and cum deep inside");
				
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					sb.append(" [npc.her] [npc.pussy+].");
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					sb.append(" [npc.her] [npc.asshole+].");
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
					sb.append(" [npc.her] [npc.nipple+].");
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE_CROTCH)) {
					sb.append(" [npc.her] [npc.nipple+].");
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					sb.append(" [npc.her] [npc.spinneret+].");
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					sb.append(" [npc.her] [npc.urethraPenis+].");
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					sb.append(" [npc.her] [npc.urethraVagina+].");
					
				} else {
					sb.append(" of [npc.herHim]");
				}
				
			} else {
				if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
					boolean petName = false;
					if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}

					sb.append("Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence and [npc.verb(cry)] out for [npc2.name] to knot [npc.herHim] and cum deep inside");
					
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						sb.append(" [npc.her] [npc.pussy+], [npc.speech(Knot me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside my pussy and fill up that condom!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						sb.append(" [npc.her] [npc.asshole+], [npc.speech(Knot me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside my ass and fill up that condom!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						sb.append(" [npc.her] [npc.nipple+], [npc.speech(Knot me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside my nipple and fill up that condom!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE_CROTCH)) {
						sb.append(" [npc.her] [npc.nippleCrotch+], [npc.speech(Knot me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside me and fill up that condom!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						sb.append(" [npc.her] [npc.spinneret+], [npc.speech(Knot me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside me and fill up that condom!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
						sb.append(" [npc.her] [npc.urethraPenis+], [npc.speech(Knot me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside me and fill up that condom!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
						sb.append(" [npc.her] [npc.urethraVagina+], [npc.speech(Knot me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside me and fill up that condom!)]");
	
					} else {
						sb.append(" of [npc.herHim], [npc.speech(Knot me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside me and fill up that condom!)]");
					}
					
				} else {
					boolean petName = false;
					if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}
					
					sb.append("Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence and [npc.verb(cry)] out for [npc2.name] to knot [npc.herHim] and cum deep inside");
					
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						sb.append(" [npc.her] [npc.pussy+],"
									+(Main.sex.getCharacterPerformingAction().isVisiblyPregnant()
											?" [npc.speech(Knot me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside of me and fill my pussy with your cum!)]"
											:" [npc.speech(Knot me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Fill my pussy with your cum and breed me!)]"));
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						sb.append(" [npc.her] [npc.asshole+], [npc.speech(Knot me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside my ass!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						sb.append(" [npc.her] [npc.nipple+], [npc.speech(Knot me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside my nipple!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE_CROTCH)) {
						sb.append(" [npc.her] [npc.nippleCrotch+], [npc.speech(Knot me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside me!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						sb.append(" [npc.her] [npc.spinneret+], [npc.speech(Knot me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside me!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
						sb.append(" [npc.her] [npc.urethraPenis+], [npc.speech(Knot me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside me!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
						sb.append(" [npc.her] [npc.urethraVagina+], [npc.speech(Knot me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside me!)]");
	
					} else {
						sb.append(" of [npc.herHim], [npc.speech(Knot me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside me!)]");
					}
				}
			}
			
			if(!Main.sex.getCharacterTargetedForSexAction(this).isPlayer() && !Main.sex.isSpectator(Main.sex.getCharacterPerformingAction())) {
				if(Main.sex.getCharacterTargetedForSexAction(this).isSpeechMuffled()) {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+"Grinning as [npc.name] [npc.verb(ask)] this, [npc2.name] [npc2.verb(let)] out a positive-sounding [npc2.moan] in order to let [npc.name] know that that's exactly what [npc2.sheHasFull] planned.");
						
					} else {
						sb.append("<br/><br/>"
								+"Furrowing [npc2.her] eyebrows as [npc.name] [npc.verb(ask)] this,"
								+ " [npc2.name] [npc2.verb(let)] out a negative-sounding [npc2.moan] in order to let [npc.name] know that [npc2.sheIs] not interested in listening to anything [npc.sheHasFull] to say.");
					}
					
				} else {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"Grinning as [npc.name] [npc.verb(ask)] this, [npc2.name] quickly replies in the positive, ",
									"Letting out [npc.a_moan+] after hearing what it is [npc.nameIs] asking of [npc2.herHim], [npc2.name] responds, "));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(Sure, if that's what you want!)]",
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
			
			return sb.toString();
		}

		@Override
		public void applyEffects() {
			Main.sex.getCharactersRequestingKnot().add(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			List<Fetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if ((Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE).contains(Main.sex.getCharacterPerformingAction()))
					|| (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST).contains(SexAreaPenetration.PENIS)
							&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST).contains(Main.sex.getCharacterPerformingAction()))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_BREASTS_SELF);
				} else {
					fetishes.add(Fetish.FETISH_BREASTS_OTHERS);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_FOOT_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_FOOT_RECEIVING);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ARMPIT_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_ARMPIT_RECEIVING);
				}
			}
			return fetishes;
		}
	};
	
	private static void applyBasePenisOrgasmRequestsReset() {
		Main.sex.getCharactersRequestingCreampie().remove(Main.sex.getCharacterPerformingAction());
		Main.sex.getCharactersRequestingKnot().remove(Main.sex.getCharacterPerformingAction());
		Main.sex.getCharactersRequestingPullout().remove(Main.sex.getCharacterPerformingAction());
	}
	
	private static SexActionPriority getBaseForceCreampiePriority(SexActionInterface sexAction) {
		if((Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
				&& Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(sexAction)))) {
			if(!Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive()) {
					return SexActionPriority.HIGH;
				}
				if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
					return SexActionPriority.LOW;
				}
			}
		}
		
		if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isPositive()) {
			return SexActionPriority.HIGH;
			
		} else {
			return SexActionPriority.LOW;
		}
	}
	
	private static String getForcedCreampieSpeech(SexAction sexAction) {
		boolean knowsName = (!Main.sex.getCharacterPerformingAction().isPlayer() && Main.sex.getCharacterPerformingAction().isPlayerKnowsName())
							|| (!Main.sex.getCharacterTargetedForSexAction(sexAction).isPlayer() && Main.sex.getCharacterTargetedForSexAction(sexAction).isPlayerKnowsName());
		
		if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(sexAction))) {
			if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
				if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
					return (Main.sex.getCharacterPerformingAction().isSpeechMuffled()
								?" With [npc.her] mouth being blocked, [npc.sheIs] only able to make a very muffled [npc.moan] as [npc.she] [npc.verb(prepare)] to receive [npc.her] creampie."
								:" With a hysterical squeal, [npc.she] [npc.verb(cry)] out, [npc.speechNoExtraEffects(~Aah!~ Now I've got you! Cum in me"+(knowsName?", [npc2.name]":"")+"! ~Ooh!~ ~Yes!~ Give me your babies!)]");
				}
				return (Main.sex.getCharacterPerformingAction().isSpeechMuffled()
								?" With [npc.her] mouth being blocked, [npc.sheIs] only able to make a very muffled [npc.moan] as [npc.she] [npc.verb(prepare)] to receive [npc.her] creampie."
								:" With a desperate [npc.moan], [npc.she] [npc.verb(cry)] out, [npc.speechNoExtraEffects(~Ooh!~ ~Yes!~ Cum in my pussy"+(knowsName?", [npc2.name]":"")+"! ~Aah!~ Give me a nice big creampie!)]");

			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
				return (Main.sex.getCharacterPerformingAction().isSpeechMuffled()
								?" With [npc.her] mouth being blocked, [npc.sheIs] only able to make a very muffled [npc.moan] as [npc.she] [npc.verb(prepare)] to receive [npc.her] creampie."
								:" With a desperate [npc.moan], [npc.she] [npc.verb(cry)] out, [npc.speechNoExtraEffects(~Ooh!~ ~Yes!~ Cum in my ass"+(knowsName?", [npc2.name]":"")+"! ~Aah!~ Give me a nice big creampie!)]");
				
			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
				return (Main.sex.getCharacterPerformingAction().isSpeechMuffled()
						?" With [npc.her] mouth being blocked, [npc.sheIs] only able to make a very muffled [npc.moan] as [npc.she] [npc.verb(prepare)] to receive [npc.her] creampie."
						:" With a desperate [npc.moan], [npc.she] [npc.verb(cry)] out, [npc.speechNoExtraEffects(~Ooh!~ ~Yes!~ Cum in my spinneret"+(knowsName?", [npc2.name]":"")+"! ~Aah!~ Give me a nice big creampie!)]");
		
			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
				return (Main.sex.getCharacterPerformingAction().isSpeechMuffled()
								?" With [npc.her] mouth being blocked, [npc.sheIs] only able to make a very muffled [npc.moan] as [npc.she] [npc.verb(prepare)] to receive [npc.her] creampie."
								:" With a desperate [npc.moan], [npc.she] [npc.verb(cry)] out, [npc.speechNoExtraEffects(~Ooh!~ ~Yes!~ Cum in me"+(knowsName?", [npc2.name]":"")+"! ~Aah!~ Fill my balls with your cum!)]");
				
			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
				return (Main.sex.getCharacterPerformingAction().isSpeechMuffled()
								?" With [npc.her] mouth being blocked, [npc.sheIs] only able to make a very muffled [npc.moan] as [npc.she] [npc.verb(prepare)] to receive [npc.her] creampie."
								:" With a desperate [npc.moan], [npc.she] [npc.verb(cry)] out, [npc.speechNoExtraEffects(~Ooh!~ ~Yes!~ Cum in me"+(knowsName?", [npc2.name]":"")+"! ~Aah!~ Give me a nice big creampie!)]");
				
			}
			
		} else { // Dildo:
			if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
				return (Main.sex.getCharacterPerformingAction().isSpeechMuffled()
								?" With [npc.her] mouth being blocked, [npc.sheIs] only able to make a very muffled [npc.moan] as [npc.she] [npc.verb(feel)] [npc.herself] being filled by [npc2.namePos] toy."
								:" With a desperate [npc.moan], [npc.she] [npc.verb(cry)] out, [npc.speechNoExtraEffects(~Ooh!~ ~Yes!~ Fill my pussy with that toy"+(knowsName?", [npc2.name]":"")+"!)]");

			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
				return (Main.sex.getCharacterPerformingAction().isSpeechMuffled()
								?" With [npc.her] mouth being blocked, [npc.sheIs] only able to make a very muffled [npc.moan] as [npc.she] [npc.verb(feel)] [npc.herself] being filled by [npc2.namePos] toy."
								:" With a desperate [npc.moan], [npc.she] [npc.verb(cry)] out, [npc.speechNoExtraEffects(~Ooh!~ ~Yes!~ Fill my ass with that toy"+(knowsName?", [npc2.name]":"")+"!)]");
				
			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
				return (Main.sex.getCharacterPerformingAction().isSpeechMuffled()
						?" With [npc.her] mouth being blocked, [npc.sheIs] only able to make a very muffled [npc.moan] as [npc.she] [npc.verb(feel)] [npc.herself] being filled by [npc2.namePos] toy."
						:" With a desperate [npc.moan], [npc.she] [npc.verb(cry)] out, [npc.speechNoExtraEffects(~Ooh!~ ~Yes!~ Fill my spinneret with that toy"+(knowsName?", [npc2.name]":"")+"!)]");
		
			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
				return (Main.sex.getCharacterPerformingAction().isSpeechMuffled()
								?" With [npc.her] mouth being blocked, [npc.sheIs] only able to make a very muffled [npc.moan] as [npc.she] [npc.verb(feel)] [npc.herself] being filled by [npc2.namePos] toy."
								:" With a desperate [npc.moan], [npc.she] [npc.verb(cry)] out, [npc.speechNoExtraEffects(~Ooh!~ ~Yes!~ Fill my cock with that toy"+(knowsName?", [npc2.name]":"")+"!)]");
				
			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
				return (Main.sex.getCharacterPerformingAction().isSpeechMuffled()
								?" With [npc.her] mouth being blocked, [npc.sheIs] only able to make a very muffled [npc.moan] as [npc.she] [npc.verb(feel)] [npc.herself] being filled by [npc2.namePos] toy."
								:" With a desperate [npc.moan], [npc.she] [npc.verb(cry)] out, [npc.speechNoExtraEffects(~Ooh!~ ~Yes!~ Fill me with that toy"+(knowsName?", [npc2.name]":"")+"!)]");
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
			return (isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericGroinForceCreampieAreas)
						|| isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericFaceForceCreampieAreas))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()
					&& isSpecialCreampieLockConditionMet(
							this,
							Main.sex.getCharacterTargetedForSexAction(this),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0))
					&& Main.sex.getPosition().isForcedCreampieEnabled(
							Torso.class,
							(SexAreaOrifice) Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getCharacterTargetedForSexAction(this))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING);
		}

		@Override
		public SexActionPriority getPriority() {
			return getBaseForceCreampiePriority(this);
		}

		@Override
		public String getDescription() {
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
						return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
								+ getForcedCreampieSpeech(this);
					}
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.her] [npc2.cock+] deep into [npc.her] cock's urethra."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.her] [npc2.cock+] deep into [npc.her] pussy's urethra."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.her] [npc2.cock+] deep into [npc.her] spinneret."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.her] [npc2.cock+] deep down [npc.her] throat."
							+ " With a desperate, gurgling [npc.moan], [npc.she] [npc.verb(prepare)] to swallow all of the cum that's about to be pumped inside of [npc.herHim].";
				}
				return "Error: Forced creampie not accounted for. Please let Innoxia know!";
				
			} else { // Dildo:
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.her] [npc2.cock+] deep into [npc.her] cock's urethra."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.her] [npc2.cock+] deep into [npc.her] pussy's urethra."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.her] [npc2.cock+] deep into [npc.her] spinneret."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.her] [npc2.cock+] deep down [npc.her] throat.";
				}
				return "Error: Forced creampie area not accounted for. Please let Innoxia know!";
			}
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterTargetedForSexAction(this), new Value<>(Main.sex.getCharacterPerformingAction(), Torso.class));
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			List<Fetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
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
			return (isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericGroinForceCreampieAreas)
						|| isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericFaceForceCreampieAreas))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()
					&& !Main.sex.getCharacterPerformingAction().isArmMovementHindered()
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& isSpecialCreampieLockConditionMet(
							this,
							Main.sex.getCharacterTargetedForSexAction(this),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0))
					&& Main.sex.getPosition().isForcedCreampieEnabled(
							Arm.class,
							(SexAreaOrifice) Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getCharacterTargetedForSexAction(this))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING);
		}

		@Override
		public SexActionPriority getPriority() {
			return getBaseForceCreampiePriority(this);
		}

		@Override
		public String getDescription() {
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
						return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
								+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
								+ getForcedCreampieSpeech(this);
					}
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
								+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
								+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
								+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+]."
								+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
								+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] cock's urethra."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
							+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] pussy's urethra."
						+ getForcedCreampieSpeech(this);
				
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
							+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] spinneret."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
								+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep down [npc.her] throat."
							+ " With a desperate, gurgling [npc.moan], [npc.she] [npc.verb(prepare)] to swallow all of the cum that's about to be pumped inside of [npc.herHim].";
				}
				return "Error: Hug-lock area not accounted for. Please let Innoxia know!";
				
			} else { // Dildo:
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
							+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
							+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
							+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] cock's urethra."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
							+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] pussy's urethra."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
							+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] spinneret."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
								+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep down [npc.her] throat.";
				}
				return "Error: Hug-lock area not accounted for. Please let Innoxia know!";
			}
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterTargetedForSexAction(this), new Value<>(Main.sex.getCharacterPerformingAction(), Arm.class));
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			List<Fetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
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
			return (isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericGroinForceCreampieAreas)
						|| isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericFaceForceCreampieAreas))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()
					&& !Main.sex.getCharacterPerformingAction().isLegMovementHindered()
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)>=2
					&& isSpecialCreampieLockConditionMet(
							this,
							Main.sex.getCharacterTargetedForSexAction(this),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0))
					&& Main.sex.getPosition().isForcedCreampieEnabled(
							Leg.class,
							(SexAreaOrifice) Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getCharacterTargetedForSexAction(this))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING);
		}

		@Override
		public SexActionPriority getPriority() {
			return getBaseForceCreampiePriority(this);
		}

		@Override
		public String getDescription() {
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
						return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back,"
								+ " forcing [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
								+ getForcedCreampieSpeech(this);
					}
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back,"
								+ " forcing [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
								+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back,"
								+ " forcing [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+]."
								+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back,"
								+ " forcing [npc2.her] [npc2.cock+] deep into [npc.her] cock's urethra."
								+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back,"
								+ " forcing [npc2.her] [npc2.cock+] deep into [npc.her] pussy's urethra."
								+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back,"
							+ " forcing [npc2.her] [npc2.cock+] deep into [npc.her] spinneret."
							+ getForcedCreampieSpeech(this);
				
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) { // This shouldn't really ever be encountered:
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back, before letting out a desperate, muffled [npc.moan].";
				}
				return "Error: Leg-lock area not accounted for. Please let Innoxia know!";
				
			} else { // Dildo:
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to orgasm, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back,"
								+ " forcing [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to orgasm, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back,"
								+ " forcing [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to orgasm, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back,"
								+ " forcing [npc2.her] [npc2.cock+] deep into [npc.her] cock's urethra."
								+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to orgasm, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back,"
								+ " forcing [npc2.her] [npc2.cock+] deep into [npc.her] pussy's urethra."
								+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to orgasm, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back,"
							+ " forcing [npc2.her] [npc2.cock+] deep into [npc.her] spinneret."
							+ getForcedCreampieSpeech(this);
				
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) { // This shouldn't really ever be encountered:
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to orgasm, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back, before letting out a desperate, muffled [npc.moan].";
				}
				return "Error: Leg-lock area not accounted for. Please let Innoxia know!";
			}
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterTargetedForSexAction(this), new Value<>(Main.sex.getCharacterPerformingAction(), Leg.class));
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			List<Fetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
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
			return (isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericGroinForceCreampieAreas)
						|| isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericFaceForceCreampieAreas))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()
					&& Main.sex.getCharacterPerformingAction().hasTail()
					&& Main.sex.getCharacterPerformingAction().getTailType().isPrehensile()
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TAIL)>=1
					&& isSpecialCreampieLockConditionMet(
							this,
							Main.sex.getCharacterTargetedForSexAction(this),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0))
					&& Main.sex.getPosition().isForcedCreampieEnabled(
							Tail.class,
							(SexAreaOrifice) Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getCharacterTargetedForSexAction(this))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING);
		}

		@Override
		public SexActionPriority getPriority() {
			return getBaseForceCreampiePriority(this);
		}

		@Override
		public String getDescription() {
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
						return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
								+ getForcedCreampieSpeech(this);
					}
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] cock's urethra."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] pussy's urethra."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] spinneret."
						+ getForcedCreampieSpeech(this);
				
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep down [npc.her] throat."
							+ " With a desperate, gurgling [npc.moan], [npc.she] [npc.verb(prepare)] to swallow all of the cum that's about to be pumped inside of [npc.herHim].";
				}
				return "Error: Tail-lock area not accounted for. Please let Innoxia know!";
				
			} else { // Dildo:
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] cock's urethra."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] pussy's urethra."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] spinneret."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep down [npc.her] throat.";
				}
				return "Error: Tail-lock area not accounted for. Please let Innoxia know!";
			}
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterTargetedForSexAction(this), new Value<>(Main.sex.getCharacterPerformingAction(), Tail.class));
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			List<Fetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
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
			return (isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericGroinForceCreampieAreas)
						|| isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericFaceForceCreampieAreas))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()
					&& Main.sex.getCharacterPerformingAction().hasWings()
					&& Main.sex.getCharacterPerformingAction().getWingSize().getValue()>=WingSize.THREE_LARGE.getValue()
					&& isSpecialCreampieLockConditionMet(
							this,
							Main.sex.getCharacterTargetedForSexAction(this),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0))
					&& Main.sex.getPosition().isForcedCreampieEnabled(
							Wing.class,
							(SexAreaOrifice) Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getCharacterTargetedForSexAction(this))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING);
		}

		@Override
		public SexActionPriority getPriority() {
			return getBaseForceCreampiePriority(this);
		}

		@Override
		public String getDescription() {
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
						return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
								+ getForcedCreampieSpeech(this);
					}
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] cock's urethra."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] pussy's urethra."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] spinneret."
						+ getForcedCreampieSpeech(this);
				
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep down [npc.her] throat."
							+ " With a desperate, gurgling [npc.moan], [npc.she] [npc.verb(prepare)] to swallow all of the cum that's about to be pumped inside of [npc.herHim].";
				}
				return "Error: Tail-lock area not accounted for. Please let Innoxia know!";
				
			} else { // Dildo:
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] cock's urethra."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] pussy's urethra."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] spinneret."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep down [npc.her] throat.";
				}
				return "Error: Tail-lock area not accounted for. Please let Innoxia know!";
			}
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterTargetedForSexAction(this), new Value<>(Main.sex.getCharacterPerformingAction(), Wing.class));
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			List<Fetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
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
			return (isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericGroinForceCreampieAreas)
						|| isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericFaceForceCreampieAreas))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()
					&& Main.sex.getCharacterPerformingAction().hasTentacle()
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TENTACLE)>=1
					&& isSpecialCreampieLockConditionMet(
							this,
							Main.sex.getCharacterTargetedForSexAction(this),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0))
					&& Main.sex.getPosition().isForcedCreampieEnabled(
							Tentacle.class,
							(SexAreaOrifice) Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getCharacterTargetedForSexAction(this))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING);
		}

		@Override
		public SexActionPriority getPriority() {
			return getBaseForceCreampiePriority(this);
		}

		@Override
		public String getDescription() {
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
						return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
								+ getForcedCreampieSpeech(this);
					}
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] cock's urethra."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] pussy's urethra."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] spinneret."
						+ getForcedCreampieSpeech(this);
				
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep down [npc.her] throat."
							+ " With a desperate, gurgling [npc.moan], [npc.she] [npc.verb(prepare)] to swallow all of the cum that's about to be pumped inside of [npc.herHim].";
				}
				return "Error: Tail-lock area not accounted for. Please let Innoxia know!";
				
			} else { // Dildo:
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] cock's urethra."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] pussy's urethra."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] spinneret."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
								+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep down [npc.her] throat.";
				}
				return "Error: Tail-lock area not accounted for. Please let Innoxia know!";
			}
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterTargetedForSexAction(this), new Value<>(Main.sex.getCharacterPerformingAction(), Tentacle.class));
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			List<Fetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
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
			if(!isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Ask [npc2.herHim] to pull [npc2.her] dildo out of you as [npc2.she] [npc2.verb(orgasm)].";
			}
			return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Ask [npc2.herHim] to pull out before [npc2.she] cums.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			if(Main.sex.getCharacterPerformingAction().isPlayer()) {
				return isTakingCock(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))
						&& !Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
						&& !Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY);
				
			} else {
				if(GENERIC_PREPARATION_ASK_FOR_CREAMPIE.isBaseRequirementsMet()
						&& GENERIC_PREPARATION_ASK_FOR_CREAMPIE.getPriority().getValue()>=this.getPriority().getValue()) {
					return false; // Do not ask for pullout if they have ask for creampie available as well.
				}
				
				return isTakingCock(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))
						&& (Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.SUB_RESISTING
							|| ((Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
									?!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isPositive()
									:true)
							&& (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
									?!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive()
									:true)));
			}
		}

		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))
					&& (Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
						&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo())
					&& !Main.sex.getCharacterPerformingAction().isVisiblyPregnant())
				|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isNegative()) {
				return SexActionPriority.HIGH;
				
			} else {
				return SexActionPriority.LOW;
			}
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.getCharactersRequestingPullout().put(Main.sex.getCharacterPerformingAction(), null);
		}

		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				if(!isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull out of [npc.her] [npc.pussy+].";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull out of [npc.her] [npc.asshole+].";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull out of [npc.her] [npc.nipple+].";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull out of [npc.her] [npc.spinneret+].";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to get away from [npc.her] [npc.breasts+].";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to get away from [npc.her] [npc.feet+].";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to get away from [npc.her] [npc.armpit+].";
						
					} else {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull out of [npc.herHim] as [npc2.she] [npc2.verb(orgasm)].";
					}
	
				} else {
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull out of [npc.her] [npc.pussy+] before [npc2.she] [npc2.verb(cum)].";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull out of [npc.her] [npc.asshole+] before [npc2.she] [npc2.verb(cum)].";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull out of [npc.her] [npc.nipple+] before [npc2.she] [npc2.verb(cum)].";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull out of [npc.her] [npc.spinneret+] before [npc2.she] [npc2.verb(cum)].";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull away from [npc.her] [npc.breasts+] before [npc2.she] [npc2.verb(cum)].";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull away from [npc.her] [npc.feet+] before [npc2.she] [npc2.verb(cum)].";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						return "Although not able to speak,"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull away from [npc.her] [npc.armpit+] before [npc2.she] [npc2.verb(cum)].";
						
					} else {
						return "Although not able to speak, [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.do]n't [npc.verb(want)] [npc2.namePos] cum.";
					}
				}
				
			} else {
				if(!isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)
							|| isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+"[npc.speech(Pull out! Get that dildo out of me!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+"[npc.speech(Pull out! Get that dildo out of me!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+"[npc.speech(Pull out! Get that dildo out of me!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+"[npc.speech(Pull out! Get that dildo out of me!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+"[npc.speech(Pull out! Get that dildo out of me!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+"[npc.speech(Pull out! Get that dildo away from me!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+"[npc.speech(Get that dildo away from me!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+"[npc.speech(Get that dildo away from me!)]";
						
					} else {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to cry out around [npc2.namePos] [npc2.cock], "
								+ "[npc.speech(Pull out! Get that dildo out of my mouth!)]";
					}
					
				} else if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Pull out! I don't want to risk that condom breaking in my pussy!)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Pull out! I don't want to risk that condom breaking!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Pull out! I don't want to risk that condom breaking!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Pull out! I don't want that condom breaking in my ass!)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Pull out! I don't want that condom breaking in my nipple!)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Pull out! I don't want that condom breaking in my spinneret!)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Get back! I don't want that condom breaking all over my tits!)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Get back! I don't want that condom breaking all over my [npc.feet]!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Get back! I don't want that condom breaking all over my [npc.armpit]!)]";
						
					} else {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to cry out around [npc2.namePos] [npc2.cock], "
								+ "[npc.speech(Pull out! Please!)]";
					}
					
				} else {
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+(Main.sex.getCharacterPerformingAction().isVisiblyPregnant()
										|| Main.sex.getCharacterPerformingAction().hasStatusEffect(StatusEffect.MENOPAUSE)
//										|| !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
									?"[npc.speech(Pull out! I don't want you to cum in me!)]"
									:"[npc.speech(Pull out! I don't want to get pregnant!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Pull out! Don't cum in me, please!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Pull out! Don't cum in me, please!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Pull out! Don't cum in my ass, please!)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Pull out! Don't cum in my nipple! Don't do it!)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Pull out! Don't cum in my spinneret! Don't do it!)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Get back! Don't cum on my tits! Don't you dare!)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Get back! Don't cum on my [npc.feet]! Don't you dare!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(Get back! Don't cum on my [npc.armpit]! Don't you dare!)]";
						
					} else {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to cry out around [npc2.namePos] [npc2.cock], "
								+ "[npc.speech(Pull out! I don't want to taste your cum!)]";
					}
				}
			}
		}
	};

	public static final SexAction GENERIC_PREPARATION_ASK_FOR_FACIAL = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Request facial";
		}
		@Override
		public String getActionDescription() {
			return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Ask [npc2.herHim] to finish all over your face.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()) {
				return false;
			}
			boolean cockFaceInteractionAvailable = false;
			try {
				cockFaceInteractionAvailable = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getAvailableCumTargets().contains(OrgasmCumTarget.FACE);
			} catch(Exception ex) {
				// No available penis-mouth actions, so can't reach face
			}
			if(!cockFaceInteractionAvailable) { // Check for reverse if not found:
				try {
					cockFaceInteractionAvailable = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
							.get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
							.getProvidedCumTargets().contains(OrgasmCumTarget.FACE);
				} catch(Exception ex) {
				}
			}
			if(!cockFaceInteractionAvailable || Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
				return false;
			}
			
			if(Main.sex.getCharacterPerformingAction().isPlayer()) {
				return true;
				
			} else { // Do not ask for pullout if they are resisting or want to ask for a creampie.
				if((GENERIC_PREPARATION_ASK_FOR_CREAMPIE.isBaseRequirementsMet() && GENERIC_PREPARATION_ASK_FOR_CREAMPIE.getPriority().getValue()>=this.getPriority().getValue())) {
					return false;
				}
				
				return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING && !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isNegative();
			}
		}
		// Just let them use standard behaviour weighting for this one
		@Override
		public SexActionPriority getPriority() {
			if(!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isPositive()) {
				return SexActionPriority.LOW;
			} else {
				return super.getPriority();
			}
		}
		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.getCharactersRequestingPullout().put(Main.sex.getCharacterPerformingAction(), OrgasmCumTarget.FACE);
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Although not able to speak, [npc.name] [npc.verb(manage)] to use a series of pleading whines and gestures in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to finish on [npc.her] face.";
				
			} else {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)
						|| isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)
						|| isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)
						|| isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)
						|| isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)
						|| isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
							+"[npc.speech(Pull out! Finish on my face!)]";

				} else {
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
							+ "[npc.speech(I want you to finish on my face!)]";
				}
			}
		}
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_STUD);
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
			return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Don't make any requests of [npc2.herHim], and see what [npc2.sheHasFull] in store for you.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return isTakingCock(Main.game.getPlayer(), Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
		}

		@Override
		public String getDescription() {
			if(!isRealPenisFuckingCharacter(Main.game.getPlayer(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "You continue [npc.moaning+] as [npc2.namePos] [npc2.cock+] continues to slam in and out of your [npc.pussy+], and you wonder if [npc2.sheIs] going to pull out or carry on fucking you through [npc2.her] orgasm.";

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "You continue [npc.moaning+] as [npc2.namePos] [npc2.cock+] continues to slam in and out of your [npc.asshole+], and you wonder if [npc2.sheIs] going to pull out or carry on fucking you through [npc2.her] orgasm.";

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
					return "You continue [npc.moaning+] as [npc2.namePos] [npc2.cock+] continues to slam in and out of your [npc.nipple+], and you wonder if [npc2.sheIs] going to pull out or carry on fucking you through [npc2.her] orgasm.";

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "You continue [npc.moaning+] as [npc2.namePos] [npc2.cock+] continues to slam in and out of your [npc.spinneret+], and you wonder if [npc2.sheIs] going to pull out or carry on fucking you through [npc2.her] orgasm.";

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
					if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
						return "You continue [npc.moaning+] as [npc2.namePos] [npc2.cock+] continues to slide up and down between your [npc.breasts+],"
								+ " and you wonder if [npc2.sheIs] going to carry on fucking your [npc.breasts+] through [npc2.her] orgasm.";
					} else {
						return "You continue [npc.moaning+] as [npc2.namePos] [npc2.cock+] continues to slide up and down over your torso,"
								+ " and you wonder if [npc2.sheIs] going to back away or carry on fucking your flat chest through [npc2.her] orgasm.";
					}
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
					return "You continue [npc.moaning+] as you give [npc.name] [pc.a_footjob], and you wonder if [npc2.sheIs] going to back away or carry on fucking your [npc.feet] through [npc2.her] orgasm.";
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
					return "You continue [npc.moaning+] as [npc.name] fucks your [pc.armpit+], and you wonder if [npc2.sheIs] going to back away or carry on fucking your pit through [npc2.her] orgasm.";
					
				} else {
					return "You continue [npc.moaning+] as [npc2.name] continues to fuck you with [npc2.her] [npc2.cock+], and you wonder if [npc2.sheIs] going to pull out, or carry on fucking you through [npc2.her] orgasm.";
				}
				
			} else {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "You continue [npc.moaning+] as you feel [npc2.namePos] [npc2.cock+] start to twitch inside your [npc.pussy+], and you wonder if [npc2.sheIs] going to pull out or"
							+(Main.game.getPlayer().isVisiblyPregnant()
									?" give you a fresh creampie..."
									:" fill your womb with [npc2.her] [npc2.cum+]...");

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "You continue [npc.moaning+] as you feel [npc2.namePos] [npc2.cock+] start to twitch inside your [npc.asshole+], and you wonder if [npc2.sheIs] going to pull out or give you a fresh anal creampie...";

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
					return "You continue [npc.moaning+] as you feel [npc2.namePos] [npc2.cock+] start to twitch inside your [npc.nipple+], and you wonder if [npc2.sheIs] going to pull out or give you a fresh nipple creampie...";

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "You continue [npc.moaning+] as you feel [npc2.namePos] [npc2.cock+] start to twitch inside your [npc.spinneret+], and you wonder if [npc2.sheIs] going to pull out or give you a fresh spinneret creampie...";

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
					if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
						return "You continue [npc.moaning+] as [npc2.namePos] [npc2.cock+] continues to slide up and down between your [npc.breasts+], and you wonder if [npc2.sheIs] going to pull out or cum all over your [npc.breasts+].";
					} else {
						return "You continue [npc.moaning+] as [npc2.namePos] [npc2.cock+] continues to slide up and down over your torso, and you wonder if [npc2.sheIs] going to back away or cum all over your flat chest.";
					}
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
					return "You continue [npc.moaning+] as you give [npc.name] [pc.a_footjob], and you wonder if [npc2.sheIs] going to pull back or cum all over your [npc.feet].";
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
					return "You continue [npc.moaning+] as [npc.name] fucks your [pc.armpit+], and you wonder if [npc2.sheIs] going to pull back or cum all over your pit.";
					
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
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotGeneric.MISC_WATCHING
					|| Main.sex.getInitialSexManager().isHidden(Main.sex.getCharacterPerformingAction())
					|| Main.sex.getInitialSexManager().isHidden(Main.sex.getCharacterTargetedForSexAction(this))) {
				return false;
			}
			if(Main.sex.isDom(Main.sex.getCharacterPerformingAction()) && !Main.sex.isCharacterDeniedOrgasm(Main.sex.getCharacterTargetedForSexAction(this))) {
				if(Main.sex.getCharacterPerformingAction().isPlayer()) {
					return true;
					
				} else {
					return !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this)) // Doms will not deny other doms.
							&& Main.sex.isReadyToOrgasm(Main.sex.getCharacterTargetedForSexAction(this)) // check if really orgasming
							&& Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_DENIAL); // Only allow denial fetishists to use this action
				}
			}
			return false;
		}
		
		@Override
		public SexActionPriority getPriority() {
//			if(Main.sex.getCharacterPerformingAction().isPlayer() || !Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_DENIAL)) {
//				return SexActionPriority.LOW;
//			}
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
			
			if(Main.sex.getCharacterTargetedForSexAction(this).isSpeechMuffled()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
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
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
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
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
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

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					if(Main.sex.getCharacterTargetedForSexAction(this).isPlayer()) {
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
					if(Main.sex.getCharacterTargetedForSexAction(this).isSpeechMuffled()) {
						if(Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_DENIAL_SELF).isPositive()) {
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" Being enamoured with the concept of being denied so close to [npc2.her] climax, [npc2.name] [npc2.verb(let)] out a lewd cry as [npc2.sheIs] forced to calm down,"
											+ " and as [npc2.she] [npc2.verb(withdraw)] from the edge of [npc2.her] orgasm, [npc2.her] erotic exclamation turns into a particularly desperate, muffled [npc2.moan]."));
							
						} else {
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" Feeling incredibly frustrated at being denied when so close to [npc2.her] climax, [npc2.name] [npc2.verb(let)] out a frantic, muffled cry as [npc2.sheIs] forced to calm down,"
											+ " making it clear that [npc2.she] desperately [npc2.verb(want)] to orgasm."));
						}
						
					} else {
						if(Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_DENIAL_SELF).isPositive()) {
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
			Main.sex.addCharacterDeniedOrgasm(Main.sex.getCharacterTargetedForSexAction(this));
			
			Main.sex.incrementNumberOfDeniedOrgasms(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), 1);
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
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
			List<GameCharacter> characters = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
			if(characters.isEmpty()) {
				return null;
			}
			return characters.get(0);
		}
		
		@Override
		public String getActionTitle() {
			if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
				return "Encourage fucking";
				
			} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH).contains(getCharacterBeingFucked())) {
				return "Encourage deepthroat";

			} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
				return "Encourage cum on [npc.breasts]";
				
			} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
				return "Encourage cum on [npc.feet]";
				
			} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
				return "Encourage cum on [npc.armpit]";
				
			} else {
				return "Encourage creampie";
			}
		}

		@Override
		public String getActionDescription() {
			if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
				return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
						"You can tell that [npc2.name] is fast approaching [npc2.her] orgasm. Encourage [npc2.herHim] to keep fucking [npc.name] with [npc2.her] dildo as [npc2.she] climaxes.");

			} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH).contains(getCharacterBeingFucked())) {
				return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
						"You can tell that [npc2.name] is fast approaching [npc2.her] orgasm. Encourage [npc2.herHim] to fill [npc.namePos] stomach with [npc2.her] cum.");

			} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
				return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
						"You can tell that [npc2.name] is fast approaching [npc2.her] orgasm. Encourage [npc2.herHim] to cum all over [npc.namePos] [npc.breasts+].");

			} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
				return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
						"You can tell that [npc2.name] is fast approaching [npc2.her] orgasm. Encourage [npc2.herHim] to cum all over [npc.namePos] [npc.feet+].");
				
			} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
				return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
						"You can tell that [npc2.name] is fast approaching [npc2.her] orgasm. Encourage [npc2.herHim] to cum all over [npc.namePos] [npc.armpit+].");
				
			} else {
				return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
						"You can tell that [npc2.name] is fast approaching [npc2.her] orgasm. Encourage [npc2.herHim] to fill [npc.name] with [npc2.her] cum.");
			}
		}

		@Override
		public SexActionPriority getPriority() {
			OrgasmEncourageBehaviour behaviour = Main.sex.getSexManager().getCharacterOrgasmEncourageBehaviour(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked());
			switch(behaviour) {
				case CREAMPIE:
					return SexActionPriority.UNIQUE_MAX;
				case DEFAULT:
				case KNOT:
					break;
				case NO_ENCOURAGE:
				case PULL_OUT:
					return SexActionPriority.LOW;
			}
			return super.getPriority();
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(getCharacterBeingFucked()!=null
					&& !Main.sex.getInitialSexManager().isHidden(Main.sex.getCharacterPerformingAction())
					&& !isTakingCock(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)) //TODO?
					&& !Collections.disjoint(
							Util.newArrayListOfValues(
									SexAreaOrifice.VAGINA, SexAreaOrifice.ANUS, SexAreaOrifice.MOUTH, SexAreaOrifice.SPINNERET, SexAreaOrifice.BREAST, SexAreaPenetration.FOOT,
									SexAreaOrifice.ARMPITS, SexAreaOrifice.NIPPLE, SexAreaOrifice.NIPPLE_CROTCH, SexAreaOrifice.URETHRA_PENIS, SexAreaOrifice.URETHRA_VAGINA),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, getCharacterBeingFucked()))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)) {
				OrgasmEncourageBehaviour behaviour = Main.sex.getSexManager().getCharacterOrgasmEncourageBehaviour(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked());
				return behaviour != OrgasmEncourageBehaviour.NO_ENCOURAGE;
			}
			return false;
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			String targetHerHim = "[npc3.herHim]";
			String targetHer = "[npc3.her]";
			if(getCharacterBeingFucked().isPlayer()) {
				targetHerHim = "[pc.herHim]"; // Otherwise it gets parsed as 'you'
				targetHer = "[pc.her]"; // Otherwise it gets parsed as 'you'
			}
			
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
					if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to keep pounding [npc3.namePos] [npc3.pussy+] as [npc2.she] [npc2.verb(orgasm)].");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to keep pounding [npc3.namePos] [npc3.asshole+] as [npc2.she] [npc2.verb(orgasm)].");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to keep pounding [npc3.namePos] [npc3.nipple+] as [npc2.she] [npc2.verb(orgasm)].");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to keep fucking [npc3.namePos] [npc3.spinneret+] as [npc2.she] [npc2.verb(orgasm)].");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to keep fucking [npc3.namePos] [npc3.breasts+] as [npc2.she] [npc2.verb(orgasm)].");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to keep fucking [npc3.namePos] [npc3.feet+] as [npc2.she] [npc2.verb(orgasm)].");
						
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to keep fucking [npc3.namePos] [npc3.armpit+] as [npc2.she] [npc2.verb(orgasm)].");
						
					} else {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to keep fucking [npc3.name] as [npc2.she] [npc2.verb(orgasm)].");
					}
	
				} else {
					if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to cum inside [npc3.namePos] [npc3.pussy+].");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to cum inside [npc3.namePos] [npc3.asshole+].");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to cum inside [npc3.namePos] [npc3.nipple+].");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to cum inside [npc3.namePos] [npc3.spinneret+].");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to cum all over [npc3.namePos] [npc3.breasts+].");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to cum all over [npc3.namePos] [npc3.feet+].");
						
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to cum all over [npc3.namePos] [npc3.armpit+].");
						
					} else {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to cum inside of [npc3.name].");
					}
				}
				
			} else {
				if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
					if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.pussy+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Yes! That's right! Don't stop fucking "+targetHerHim+"!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.asshole+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Yes! That's right! Don't stop fucking "+targetHer+" [npc3.ass]!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.nipple+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Yes! That's right! Don't stop fucking "+targetHer+" [npc3.breasts]!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.spinneret+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Yes! That's right! Don't stop fucking "+targetHer+" [npc3.spinneret]!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.breasts+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Yes! That's right! Don't stop fucking "+targetHer+" tits!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.feet+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Yes! That's right! Don't stop fucking "+targetHer+" [npc3.feet]!)]");
						
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.armpit+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Yes! That's right! Don't stop fucking "+targetHer+" pit!)]");
						
					} else {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.name] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Yes! That's right! Don't stop fucking "+targetHerHim+"!)]");
					}
	
				} else if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
					boolean petName = false;
					if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}
					
					if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.pussy+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Finish inside of "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.asshole+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Finish inside of "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.nipple+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Finish inside of "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.spinneret+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Finish inside of "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.breasts+] as [npc2.she] [npc2.verb(orgasm)],"
								+ " [npc.speech(That's it"+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.feet+] as [npc2.she] [npc2.verb(orgasm)],"
								+ " [npc.speech(That's it"+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]");
						
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.armpit+] as [npc2.she] [npc2.verb(orgasm)],"
								+ " [npc.speech(That's it"+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]");
						
					} else {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.name] as [npc2.she] [npc2.verb(orgasm)],"
								+ " [npc.speech(That's it"+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]");
					}
					
				} else {
					boolean petName = false;
					if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}
	
					if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.pussy+] as [npc2.she] [npc2.verb(orgasm)],"
								+(getCharacterBeingFucked().isVisiblyPregnant()
										?" [npc.speech(Fuck! Cum in "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Fill "+targetHer+" pussy with your cum!)]"
										:" [npc.speech(Breed "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Fill "+targetHer+" pussy with your cum and knock "+targetHerHim+" up!)]"));
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.asshole+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Fuck! Cum in "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Fill "+targetHer+" ass with your cum!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.nipple+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Fuck! Cum in "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Fill "+targetHer+" nipple with your cum!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.spinneret+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Fuck! Cum in "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Fill "+targetHer+" spinneret with your cum!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.breasts+] as [npc2.she] [npc2.verb(orgasm)],"
								+ " [npc.speech(Yes! Cum for "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Cover "+targetHer+" tits with your cum!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.feet+] as [npc2.she] [npc2.verb(orgasm)],"
								+ " [npc.speech(Yes! Cum for "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Cover "+targetHer+" [npc3.feet] with your cum!)]");
						
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.armpit+] as [npc2.she] [npc2.verb(orgasm)],"
								+ " [npc.speech(Yes! Cum for "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Cover "+targetHer+" pit with your cum!)]");
						
					} else {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.name] as [npc2.she] [npc2.verb(orgasm)],"
								+ " [npc.speech(Yes! Cum for "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Don't pull out!)]");
					}
				}
			}
			
			if(!Main.sex.getCharacterTargetedForSexAction(this).isPlayer() && !Main.sex.isSpectator(Main.sex.getCharacterPerformingAction())) {
				if(Main.sex.getCharacterTargetedForSexAction(this).isSpeechMuffled()) {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+"Grinning as [npc.name] [npc.verb(ask)] this, [npc2.name] [npc2.verb(let)] out a positive-sounding [npc2.moan] in order to let [npc.name] know that that's exactly what [npc2.sheHasFull] planned.");
						
					} else {
						sb.append("<br/><br/>"
								+"Furrowing [npc2.her] eyebrows as [npc.name] [npc.verb(ask)] this,"
								+ " [npc2.name] [npc2.verb(let)] out a negative-sounding [npc2.moan] in order to let [npc.name] know that [npc2.sheIs] not interested in listening to anything [npc.sheHasFull] to say.");
					}
					
				} else {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
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
					Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked()),
					sb.toString());
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.getCharactersRequestingCreampie().add(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			List<Fetish> fetishes = new ArrayList<>();
			fetishes.add(Fetish.FETISH_VOYEURIST);
			return fetishes;
		}
	};
	

	public static final SexAction GENERIC_PREPARATION_ENCOURAGE_KNOT = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		private GameCharacter getCharacterBeingFucked() {
			List<GameCharacter> characters = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
			if(characters.isEmpty()) {
				return null;
			}
			return characters.get(0);
		}
		
		@Override
		public String getActionTitle() {
			return "Encourage knotting";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH).contains(getCharacterBeingFucked())) {
				return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
						"You can tell that [npc2.name] is fast approaching [npc2.her] orgasm. Encourage [npc2.herHim] to push [npc2.her] knot into [npc.namePos] mouth and fill [npc.her] stomach with [npc2.her] cum.");

			} else {
				return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
						"You can tell that [npc2.name] is fast approaching [npc2.her] orgasm. Encourage [npc2.herHim] to push [npc2.her] knot into [npc.name] and fill [npc.name] with [npc2.her] cum.");
			}
		}

		@Override
		public SexActionPriority getPriority() {
			OrgasmEncourageBehaviour behaviour = Main.sex.getSexManager().getCharacterOrgasmEncourageBehaviour(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked());
			switch(behaviour) {
				case KNOT:
					return SexActionPriority.UNIQUE_MAX;
				case DEFAULT:
				case CREAMPIE:
					break;
				case NO_ENCOURAGE:
				case PULL_OUT:
					return SexActionPriority.LOW;
			}
			return super.getPriority();
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(getCharacterBeingFucked()!=null
					&& !Main.sex.getInitialSexManager().isHidden(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisModifier(PenetrationModifier.KNOTTED)
					&& !isTakingCock(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)) //TODO?
					&& !Collections.disjoint(
							Util.newArrayListOfValues(
									SexAreaOrifice.VAGINA, SexAreaOrifice.ANUS, SexAreaOrifice.MOUTH, SexAreaOrifice.SPINNERET, SexAreaOrifice.NIPPLE, SexAreaOrifice.NIPPLE_CROTCH, SexAreaOrifice.URETHRA_PENIS, SexAreaOrifice.URETHRA_VAGINA),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, getCharacterBeingFucked()))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)) {
				OrgasmEncourageBehaviour behaviour = Main.sex.getSexManager().getCharacterOrgasmEncourageBehaviour(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked());
				return behaviour != OrgasmEncourageBehaviour.NO_ENCOURAGE;
			}
			return false;
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			String targetHerHim = "[npc3.herHim]";
			String targetHer = "[npc3.her]";
			if(getCharacterBeingFucked().isPlayer()) {
				targetHerHim = "[pc.herHim]"; // Otherwise it gets parsed as 'you'
				targetHer = "[pc.her]"; // Otherwise it gets parsed as 'you'
			}
			
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
						+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to knot [npc3.name] and cum deep inside");
				if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
					sb.append(" [npc3.her] [npc3.pussy+].");

				} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
					sb.append(" [npc3.her] [npc3.asshole+].");

				} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
					sb.append(" [npc3.her] [npc3.nipple+].");

				} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH).contains(getCharacterBeingFucked())) {
					sb.append(" [npc3.her] [npc3.nippleCrotch+].");

				} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
					sb.append(" [npc3.her] [npc3.spinneret+].");

				} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS).contains(getCharacterBeingFucked())) {
					sb.append(" [npc3.her] [npc3.urethraPenis+].");

				} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA).contains(getCharacterBeingFucked())) {
					sb.append(" [npc3.her] [npc3.urethraVagina+].");

				} else {
					sb.append(" of [npc3.herHim].");
				}
				
			} else {
				if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
					boolean petName = false;
					if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}

					sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to ram [npc2.her] knot into");
					
					if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
						sb.append(" [npc3.namePos] [npc3.pussy+] and cum deep inside of [npc3.herHim], [npc.speech(Knot "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside of "+targetHerHim+" and fill up that condom!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
						sb.append(" [npc3.namePos] [npc3.asshole+] and cum deep inside of [npc3.herHim], [npc.speech(Knot "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside of "+targetHerHim+" and fill up that condom!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
						sb.append(" [npc3.namePos] [npc3.nipple+] and cum deep inside of [npc3.herHim], [npc.speech(Knot "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside of "+targetHerHim+" and fill up that condom!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH).contains(getCharacterBeingFucked())) {
						sb.append(" [npc3.namePos] [npc3.nippleCrotch+] and cum deep inside of [npc3.herHim], [npc.speech(Knot "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside of "+targetHerHim+" and fill up that condom!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
						sb.append(" [npc3.namePos] [npc3.spinneret+] and cum deep inside of [npc3.herHim], [npc.speech(Knot "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside of "+targetHerHim+" and fill up that condom!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS).contains(getCharacterBeingFucked())) {
						sb.append(" [npc3.namePos] [npc3.urethraPenis+] and cum deep inside of [npc3.herHim], [npc.speech(Knot "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside of "+targetHerHim+" and fill up that condom!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA).contains(getCharacterBeingFucked())) {
						sb.append(" [npc3.namePos] [npc3.urethraVagina+] and cum deep inside of [npc3.herHim], [npc.speech(Knot "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside of "+targetHerHim+" and fill up that condom!)]");
	
					} else {
						sb.append(" [npc3.name] and cum deep inside of [npc3.herHim], [npc.speech(Knot "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside of "+targetHerHim+" and fill up that condom!)]");
					}
					
				} else {
					boolean petName = false;
					if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}

					sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to ram [npc2.her] knot into");
					
					if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
						sb.append(" [npc3.namePos] [npc3.pussy+] and cum deep inside of [npc3.herHim],"
									+(getCharacterBeingFucked().isVisiblyPregnant()
										?" [npc.speech(Knot "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside of "+targetHerHim+" and stuff "+targetHer+" pussy with your cum!)]"
										:" [npc.speech(Knot "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Stuff "+targetHer+" pussy with your cum and breed "+targetHerHim+"!)]"));
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
						sb.append(" [npc3.namePos] [npc3.asshole+] and cum deep inside of [npc3.herHim],"
									+ " [npc.speech(Knot "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside of "+targetHerHim+" and stuff "+targetHer+" ass with your cum!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
						sb.append(" [npc3.namePos] [npc3.nipple+] and cum deep inside of [npc3.herHim],"
									+ " [npc.speech(Knot "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside of "+targetHerHim+" and stuff "+targetHer+" nipple with your cum!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH).contains(getCharacterBeingFucked())) {
						sb.append(" [npc3.namePos] [npc3.nippleCrotch+] and cum deep inside of [npc3.herHim],"
									+ " [npc.speech(Knot "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside of "+targetHerHim+" and stuff "+targetHer+" nipple with your cum!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
						sb.append(" [npc3.namePos] [npc3.spinneret+] and cum deep inside of [npc3.herHim],"
									+ " [npc.speech(Knot "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside of "+targetHerHim+" and stuff "+targetHer+" spinneret with your cum!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS).contains(getCharacterBeingFucked())) {
						sb.append(" [npc3.namePos] [npc3.urethraPenis+] and cum deep inside of [npc3.herHim],"
									+ " [npc.speech(Knot "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside of "+targetHerHim+" and fill "+targetHer+" balls with your cum!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA).contains(getCharacterBeingFucked())) {
						sb.append(" [npc3.namePos] [npc3.urethraVagina+] and cum deep inside of [npc3.herHim],"
									+ " [npc.speech(Knot "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside of "+targetHerHim+" and fill "+targetHer+" bladder with your cum!)]");
	
					} else {
						sb.append(" [npc3.name] and cum deep inside of [npc3.herHim],"
									+ " [npc.speech(Knot "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside of "+targetHerHim+" and stuff "+targetHerHim+" with your cum!)]");
					}
				}
			}
			
			if(!Main.sex.getCharacterTargetedForSexAction(this).isPlayer() && !Main.sex.isSpectator(Main.sex.getCharacterPerformingAction())) {
				if(Main.sex.getCharacterTargetedForSexAction(this).isSpeechMuffled()) {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+"Grinning as [npc.name] [npc.verb(ask)] this, [npc2.name] [npc2.verb(let)] out a positive-sounding [npc2.moan] in order to let [npc.name] know that that's exactly what [npc2.sheHasFull] planned.");
						
					} else {
						sb.append("<br/><br/>"
								+"Furrowing [npc2.her] eyebrows as [npc.name] [npc.verb(ask)] this,"
								+ " [npc2.name] [npc2.verb(let)] out a negative-sounding [npc2.moan] in order to let [npc.name] know that [npc2.sheIs] not interested in listening to anything [npc.sheHasFull] to say.");
					}
					
				} else {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
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
					Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked()),
					sb.toString());
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.getCharactersRequestingKnot().add(Main.sex.getCharacterPerformingAction());
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
			List<GameCharacter> characters = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
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
			if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
				return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
						"You can tell that [npc2.name] is fast approaching [npc2.her] orgasm. Encourage [npc2.herHim] to pull [npc2.her] dildo out of [npc.name] as [npc2.she] [npc2.verb(orgasm)].");
			}
			return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
					"You can tell that [npc2.name] is fast approaching [npc2.her] orgasm. Encourage [npc2.herHim] to pull out of [npc.name] as [npc2.she] [npc2.verb(orgasm)].");
		}

		@Override
		public SexActionPriority getPriority() {
			OrgasmEncourageBehaviour behaviour = Main.sex.getSexManager().getCharacterOrgasmEncourageBehaviour(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked());
			switch(behaviour) {
				case DEFAULT:
					break;
				case NO_ENCOURAGE:
				case CREAMPIE:
				case KNOT:
					return SexActionPriority.LOW;
				case PULL_OUT:
					return SexActionPriority.UNIQUE_MAX;
			}
			return super.getPriority();
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(getCharacterBeingFucked()!=null
					&& !Main.sex.getInitialSexManager().isHidden(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())
					&& !isTakingCock(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))
					&& !Collections.disjoint(
							Util.newArrayListOfValues(SexAreaOrifice.VAGINA, SexAreaOrifice.ANUS, SexAreaOrifice.MOUTH, SexAreaOrifice.SPINNERET, SexAreaOrifice.BREAST, SexAreaPenetration.FOOT, SexAreaOrifice.ARMPITS),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, getCharacterBeingFucked()))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)) {
				OrgasmEncourageBehaviour behaviour = Main.sex.getSexManager().getCharacterOrgasmEncourageBehaviour(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked());
				return behaviour != OrgasmEncourageBehaviour.NO_ENCOURAGE;
			}
			return false;
		}

//		@Override
//		public SexActionPriority getPriority() {
//			if((Main.sex.getAllContactingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
//					&& Main.sex.getCharacterContactingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))
//					&& (Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
//						&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo())
//					&& !Main.sex.getCharacterPerformingAction().isVisiblyPregnant())
//				|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isNegative()) {
//				return SexActionPriority.HIGH;
//			} else {
//				return SexActionPriority.LOW;
//			}
//		}
		
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			String targetHerHim = "[npc3.herHim]";
			String targetHer = "[npc3.her]";
			String targetSheHas = "[npc3.SheHas]";
			if(getCharacterBeingFucked().isPlayer()) {
				targetHerHim = "[pc.herHim]"; // Otherwise it gets parsed as 'you'
				targetHer = "[pc.her]"; // Otherwise it gets parsed as 'you'
				targetSheHas = "[pc.SheHas]"; // Otherwise it gets parsed as 'you'
			}
			
			
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
					if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.namePos] [npc3.pussy+] through [npc2.her] orgasm, [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.namePos] [npc3.asshole+] through [npc2.her] orgasm, [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.namePos] [npc3.nipple+] through [npc2.her] orgasm, [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.namePos] [npc3.spinneret+] through [npc2.her] orgasm, [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.namePos] [npc3.breasts+] through [npc2.her] orgasm, [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.namePos] [npc3.feet+] through [npc2.her] orgasm, [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull away.");
						
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.namePos] [npc3.armpit+] through [npc2.her] orgasm, [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull away.");
						
					} else {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.name] through [npc2.her] orgasm, [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
					}
	
				} else {
					if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to cum inside [npc3.namePos] [npc3.pussy+], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to cum inside [npc3.namePos] [npc3.asshole+], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to cum inside [npc3.namePos] [npc3.nipple+], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to cum inside [npc3.namePos] [npc3.spinneret+], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to cum all over [npc3.namePos] [npc3.breasts+], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to cum all over [npc3.namePos] [npc3.feet+], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
						
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to cum all over [npc3.namePos] [npc3.armpit+], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull away.");
						
					} else {
						sb.append("Not wanting [npc2.name] to cum inside [npc3.name], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
								+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
					}
				}
				
			} else {
				if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
					if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.name] through [npc2.her] orgasm, [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech("+targetSheHas+" had enough! Pull out of "+targetHer+" pussy when you're going to cum!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.name] through [npc2.her] orgasm, [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech("+targetSheHas+" had enough! Pull out of "+targetHer+" ass when you're going to cum!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.name] through [npc2.her] orgasm, [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech("+targetSheHas+" had enough! Pull out of "+targetHer+" nipple when you're going to cum!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.name] through [npc2.her] orgasm, [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech("+targetSheHas+" had enough! Pull out of "+targetHer+" spinneret when you're going to cum!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.name] through [npc2.her] orgasm, [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech("+targetSheHas+" had enough! Get away from "+targetHer+" tits when you're going to cum!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.name] through [npc2.her] orgasm, [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech("+targetSheHas+" had enough! Get away from "+targetHer+" feet when you're going to cum!)]");
						
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.name] through [npc2.her] orgasm, [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech("+targetSheHas+" had enough! Get away from "+targetHer+" pit when you're going to cum!)]");
						
					} else {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.name] through [npc2.her] orgasm, [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech("+targetSheHas+" had enough! Pull away from "+targetHerHim+" when you're going to cum!)]");
					}
	
				} else if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
					boolean petName = false;
					if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}
					
					if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to continue fucking [npc3.name] through [npc2.her] orgasm, [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech(Pull out of "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! I want to see your condom inflating!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.asshole+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Pull out of "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! I want to see your condom inflating!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.nipple+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Pull out of "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! I want to see your condom inflating!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.spinneret+] as [npc2.she] [npc2.verb(orgasm)],"
								+" [npc.speech(Pull out of "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! I want to see your condom inflating!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.breasts+] as [npc2.she] [npc2.verb(orgasm)],"
								+ " [npc.speech(Pull back"+(petName?", [#npc.getPetName(npc2)]":"")+"! I want to see your condom inflating!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.feet+] as [npc2.she] [npc2.verb(orgasm)],"
								+ " [npc.speech(Pull back"+(petName?", [#npc.getPetName(npc2)]":"")+"! I want to see your condom inflating!)]");
						
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.armpit+] as [npc2.she] [npc2.verb(orgasm)],"
								+ " [npc.speech(Pull back"+(petName?", [#npc.getPetName(npc2)]":"")+"! I want to see your condom inflating!)]");
						
					} else {
						sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.name] as [npc2.she] [npc2.verb(orgasm)],"
								+ " [npc.speech(Pull back"+(petName?", [#npc.getPetName(npc2)]":"")+"! I want to see your condom inflating!)]");
					}
					
				} else {
					boolean petName = false;
					if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}
	
					if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to cum inside of [npc3.name], [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+(getCharacterBeingFucked().isVisiblyPregnant()
										?" [npc.speech(I don't want you cumming inside of "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Pull out of "+targetHer+" pussy!)]"
										:" [npc.speech(I don't want "+targetHerHim+" getting pregnant"+(petName?", [#npc.getPetName(npc2)]":"")+"! Pull out of "+targetHer+" pussy before you cum!)]"));
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to cum inside of [npc3.name], [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech(I don't want you cumming inside of "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Pull out of "+targetHer+" ass!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to cum inside of [npc3.name], [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech(I don't want you cumming inside of "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Pull out of "+targetHer+" nipple!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to cum inside of [npc3.name], [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech(I don't want you cumming inside of "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Pull out of "+targetHer+" spinneret!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to cum all over of [npc3.name], [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech(I don't want you cumming on "+targetHer+" tits"+(petName?", [#npc.getPetName(npc2)]":"")+"! Pull away from "+targetHerHim+"!)]");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to cum all over of [npc3.name], [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech(I don't want you cumming on "+targetHer+" [npc3.feet]"+(petName?", [#npc.getPetName(npc2)]":"")+"! Pull away from "+targetHerHim+"!)]");
						
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
						sb.append("Not wanting [npc2.name] to cum all over of [npc3.name], [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech(I don't want you cumming on "+targetHer+" pit"+(petName?", [#npc.getPetName(npc2)]":"")+"! Pull away from "+targetHerHim+"!)]");
						
					} else {
						sb.append("Not wanting [npc2.name] to cum all over of [npc3.name], [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
								+" [npc.speech(I don't want you cumming on "+targetHer+""+(petName?", [#npc.getPetName(npc2)]":"")+"! Pull away from "+targetHerHim+"!)]");
					}
				}
			}
			
			if(!Main.sex.getCharacterTargetedForSexAction(this).isPlayer() && !Main.sex.isSpectator(Main.sex.getCharacterPerformingAction())) {
				if(Main.sex.getCharacterTargetedForSexAction(this).isSpeechMuffled()) {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+"Grinning as [npc.name] [npc.verb(ask)] this, [npc2.name] [npc2.verb(let)] out a positive-sounding [npc2.moan] in order to let [npc.name] know that that's exactly what [npc2.sheHasFull] planned.");
						
					} else {
						sb.append("<br/><br/>"
								+"Furrowing [npc2.her] eyebrows as [npc.name] [npc.verb(ask)] this,"
								+ " [npc2.name] [npc2.verb(let)] out a negative-sounding [npc2.moan] in order to let [npc.name] know that [npc2.sheIs] not interested in listening to anything [npc.sheHasFull] to say.");
					}
					
				} else {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
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
					Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked()),
					sb.toString());
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.getCharactersRequestingPullout().put(Main.sex.getCharacterPerformingAction(), null);
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
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.LOW;
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.sex.getCharacterPerformingAction()).get(0), false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.sex.getCharacterPerformingAction()).get(0), true).applyEffects();
			if (!Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.sex.getCharacterPerformingAction().isWearingCondom()
					&& Main.sex.getCharacterPerformingAction().getPenisOrgasmCumQuantity() != CumProduction.ZERO_NONE) {
				Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(Main.sex.getCharacterPerformingAction(), true);
			}
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.sex.getCharacterPerformingAction()).get(0), false).isEndsSex();
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
			return Main.sex.isCharacterDeniedOrgasm(Main.sex.getCharacterPerformingAction());
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
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom("[npc.Name] [npc.verb(let)] out a distressed, muffled cry as [npc2.name] [npc2.verb(release)] [npc.herHim], making it clear that [npc.sheIs] having a bad time.");
					default:
						return UtilText.returnStringAtRandom("[npc.Name] [npc.verb(let)] out a dismayed, muffled cry as [npc2.name] [npc2.verb(release)] [npc.herHim], making it clear that [npc.sheIs] desperate to be allowed to orgasm.");
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom("[npc.speech(You've had your fun! Now leave me alone!)] [npc.name] [npc.verb(scream)] as [npc2.name] [npc2.verb(release)] [npc.herHim]. [npc.speech(D-Don't make me go through that again!)]");
					default:
						return UtilText.returnStringAtRandom("[npc.speech(No! I was so close!)] [npc.name] [npc.verb(wail)] in dismay as [npc2.name] [npc2.verb(release)] [npc.herHim]. [npc.speech(Let me cum next time!)]");
				}
			}
		}
		
		@Override
		public void applyEffects() {
			Main.sex.removeCharacterDeniedOrgasm(Main.sex.getCharacterPerformingAction());
			
			SexFlags.playerPreparedForCharactersOrgasm.remove(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL_SELF);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
			}
		}
	};

}
