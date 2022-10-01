package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.6
 * @version 0.3.5.5
 * @author Innoxia
 */
public class Axel extends NPC {

	public Axel() {
		this(false);
	}
	
	public Axel(boolean isImported) {
		super(isImported, new NameTriplet("Axel", "Lexa", "Lexa"), "Stack",
				"The buff albino alligator-boy, Axel, is the owner and manager of Submission's Gambling Den."
					+ " Despite his large and menacing figure, he's very kind and understanding, and always tries his best to satisfy his customers.",
				36, Month.JANUARY, 10,
				15, Gender.M_P_MALE, Subspecies.ALLIGATOR_MORPH, RaceStage.GREATER,
				new CharacterInventory(30), WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE, true);
		
		if(!isImported) {
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 15);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.7")) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelSissified)) {
				this.applySissification();
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelFeminised)) {
				this.applyFeminisation(this.getPenisGirth(), this.getPenisSize(), this.getTesticleSize(), CumProduction.getCumProductionFromInt(this.getPenisRawCumProductionRegenerationValue()));
				
			} else {
				setStartingBody(true);
			}
			this.setName(new NameTriplet("Axel", "Lexa", "Lexa"));
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.resetPerksMap(true);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 3),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:
		if(setPersona) {
			this.clearPersonalityTraits();
			this.clearFetishes();
			this.clearFetishDesires();
			
			this.setPersonalityTraits(
					PersonalityTrait.KIND,
					PersonalityTrait.COWARDLY);
			
			this.setSexualOrientation(SexualOrientation.ANDROPHILIC);
			
			this.setHistory(Occupation.NPC_CASINO_OWNER);
	
			this.addFetish(Fetish.FETISH_ANAL_GIVING);
	
			this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_CUM_STUD, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_CUM_ADDICT, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE);
		}
		
		
		// Body:

		// Core:
		this.setHeight(211);
		this.setFemininity(0);
		this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		this.setBodySize(BodySize.THREE_LARGE.getMedianValue());
		
		// Coverings:
		this.setSkinCovering(new Covering(BodyCoveringType.EYE_ALLIGATOR_MORPH, PresetColour.EYE_AQUA), true);
		this.setSkinCovering(new Covering(BodyCoveringType.ALLIGATOR_SCALES, PresetColour.COVERING_WHITE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.MOUTH, PresetColour.SKIN_IVORY, PresetColour.ORIFICE_INTERIOR), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_SCALES_ALLIGATOR, PresetColour.COVERING_GREY), false);
		this.setHairLength(0);
		this.setHairStyle(HairStyle.NONE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_SCALES_ALLIGATOR, PresetColour.COVERING_GREY), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED));
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PURPLE));
		
		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.ONE_AVERAGE);
		this.setFaceCapacity(Capacity.TWO_TIGHT, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.FLAT.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.ZERO_TINY);
		this.setAreolaeSize(AreolaeSize.ZERO_TINY);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.TWO_SMALL);
		this.setHipSize(HipSize.TWO_NARROW);
		this.setAssCapacity(Capacity.TWO_TIGHT, true);
		this.setAssWetness(Wetness.ONE_SLIGHTLY_MOIST);
		this.setAssElasticity(OrificeElasticity.ONE_RIGID.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.FIVE_THICK);
		this.setPenisSize(PenisLength.FOUR_HUGE);
		this.setTesticleSize(TesticleSize.FOUR_HUGE);
		this.setPenisCumStorage(CumProduction.FOUR_LARGE.getMedianValue());
		this.fillCumToMaxStorage();
		// Leave cum as normal value
		
		// Vagina:
		// No vagina
		
		// Feet:
		// Foot shape
	}
	
	public void applySissification() {
		this.clearFetishes();
		this.addFetish(Fetish.FETISH_SUBMISSIVE);
		this.addFetish(Fetish.FETISH_ANAL_RECEIVING);

		this.clearFetishDesires();
		this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.THREE_LIKE);
		this.setFetishDesire(Fetish.FETISH_CUM_STUD, FetishDesire.THREE_LIKE);
		this.setFetishDesire(Fetish.FETISH_CUM_ADDICT, FetishDesire.THREE_LIKE);
		this.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE);
		
		
		this.setHeight(177);
		this.setFemininity(50);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:
		this.setSkinCovering(new Covering(BodyCoveringType.EYE_ALLIGATOR_MORPH, PresetColour.EYE_AQUA), true);
		this.setSkinCovering(new Covering(BodyCoveringType.ALLIGATOR_SCALES, PresetColour.COVERING_WHITE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.MOUTH, PresetColour.SKIN_IVORY, PresetColour.ORIFICE_INTERIOR), false);
		
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_SCALES_ALLIGATOR, PresetColour.COVERING_GREY), false);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.LOOSE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_SCALES_ALLIGATOR, PresetColour.COVERING_GREY), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.ONE_AVERAGE);
		this.setFaceCapacity(Capacity.TWO_TIGHT, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.TRAINING_AAA.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.TWO_BIG);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.THREE_NORMAL);
		this.setHipSize(HipSize.THREE_GIRLY);
//		this.setAssCapacity(Capacity.TWO_TIGHT, true);
		this.setAssWetness(Wetness.TWO_MOIST);
		this.setAssElasticity(OrificeElasticity.ONE_RIGID.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.THREE_AVERAGE);
		this.setPenisSize(PenisLength.TWO_AVERAGE.getMedianValue());
		this.setTesticleSize(TesticleSize.TWO_AVERAGE);
		this.setPenisCumStorage(CumProduction.TWO_SMALL_AMOUNT.getMedianValue());
		this.fillCumToMaxStorage();
	}
	
	public void applyFeminisationCosmetics() {
		this.setHairStyle(HairStyle.DREADLOCKS);
		
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_CLEAR));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_CLEAR));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
	}

	public void applyFeminisation(PenetrationGirth penisGirth, PenisLength penisSize, TesticleSize testicleSize, CumProduction cumProduction) {
		this.setHeight(170);
		this.setFemininity(80);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
		
		// Coverings:
		this.setSkinCovering(new Covering(BodyCoveringType.EYE_ALLIGATOR_MORPH, PresetColour.EYE_AQUA), true);
		this.setSkinCovering(new Covering(BodyCoveringType.ALLIGATOR_SCALES, PresetColour.COVERING_WHITE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.MOUTH, PresetColour.SKIN_IVORY, PresetColour.ORIFICE_INTERIOR), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_SCALES_ALLIGATOR, PresetColour.COVERING_GREY), false);
		this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
		this.setHairStyle(HairStyle.LOOSE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_SCALES_ALLIGATOR, PresetColour.COVERING_GREY), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.ONE_AVERAGE);
//		this.setFaceCapacity(Capacity.ZERO_IMPENETRABLE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.C.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FOUR_WOMANLY);
//		this.setAssCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setAssWetness(Wetness.THREE_WET);
		this.setAssElasticity(OrificeElasticity.ONE_RIGID.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(penisGirth.getValue());
		this.setPenisSize(penisSize.getMedianValue());
		this.setTesticleSize(testicleSize.getValue());
		this.setPenisCumStorage(cumProduction.getMedianValue());
		this.fillCumToMaxStorage();
	}
	
	public void applyCage(boolean equip, GameCharacter equipper) {
		AbstractClothing cage = this.getClothingInSlot(InventorySlot.PENIS);
		
		if(cage==null && !equip) {
			throw new IllegalArgumentException("Axel applyCage(): Error code 1");
		}
		if(cage!=null && equip) {
			throw new IllegalArgumentException("Axel applyCage(): Error code 2");
		}
		
		if(equip) {
			AbstractClothing newCage = Main.game.getItemGen().generateClothing("innoxia_bdsm_chastity_cage", PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_ROSE_GOLD, PresetColour.CLOTHING_STEEL, false);
			newCage.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MAJOR_DRAIN, 0));
			this.equipClothingFromNowhere(newCage, true, equipper);
			
		} else {
			this.forceUnequipClothingIntoVoid(equipper, cage);
		}
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		boolean cage = this.getClothingInSlot(InventorySlot.PENIS)!=null;
		
		this.unequipAllClothingIntoVoid(true, true);
		
		inventory.setMoney(650);
		
		if(cage) {
			this.applyCage(true, Main.game.getPlayer());
		}
		
		if((!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelSissified) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelFeminised))
				|| this.getWorldLocation()==WorldType.RAT_WARRENS) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_jockstrap", PresetColour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_jeans", PresetColour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_work_boots", PresetColour.CLOTHING_TAN, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_short_sleeved_shirt", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_MENS_WATCH, PresetColour.CLOTHING_BLACK_STEEL, false), true, this);
			
		} else {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelClothingFeminine)) {
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_panties", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_thighhigh_socks", PresetColour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_heels", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK_JET, PresetColour.CLOTHING_TAN, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_head_headband_bow", PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_GREY, PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_heart_necklace", PresetColour.CLOTHING_SILVER, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.TORSO_SKATER_DRESS, PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WOMENS_WATCH, PresetColour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_ring", PresetColour.CLOTHING_SILVER, false), true, this);
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelClothingWhore)) {
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_head_headband", PresetColour.CLOTHING_BLACK, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_eye_aviators", PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_PURPLE, null, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_heart_necklace", PresetColour.CLOTHING_GOLD, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torsoOver_womens_leather_jacket", PresetColour.CLOTHING_BLACK, false), true, this);
				this.isAbleToBeDisplaced(this.getClothingInSlot(InventorySlot.TORSO_OVER), DisplacementType.UNZIPS, true, true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.TORSO_FISHNET_TOP, PresetColour.CLOTHING_PURPLE_VERY_DARK, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_fishnets", PresetColour.CLOTHING_PURPLE_VERY_DARK, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.NIPPLE_TAPE_CROSSES, PresetColour.CLOTHING_BLACK, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_hand_fishnet_gloves", PresetColour.CLOTHING_PURPLE_VERY_DARK, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_BANGLE, PresetColour.CLOTHING_GOLD, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_gemstone_ring", PresetColour.CLOTHING_PLATINUM, PresetColour.CLOTHING_PURPLE, null, false), true, this);
//				this.equipClothingFromNowhere(Main.game.getItemGeneration().generateClothing(ClothingType.HIPS_CONDOMS, PresetColour.CLOTHING_BLACK, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_micro_skirt_pleated", PresetColour.CLOTHING_PURPLE_VERY_DARK, PresetColour.CLOTHING_BLACK, null, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_thong", PresetColour.CLOTHING_BLACK, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_thigh_high_boots", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK_JET, PresetColour.CLOTHING_GREY, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_ankle_anklet", PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_GOLD, null, false), true, this);
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelClothingMaid)) {
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_panties", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_heart_necklace", PresetColour.CLOTHING_SILVER, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.MAID_HEADPIECE, PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_WHITE, null, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.MAID_DRESS, PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_WHITE, null, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.MAID_STOCKINGS, PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_WHITE, null, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.MAID_HEELS, PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_SILVER, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.MAID_SLEEVES, PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_WHITE, null, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WOMENS_WATCH, PresetColour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_ring", PresetColour.CLOTHING_SILVER, false), true, this);
				
			} else {
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_panties", PresetColour.CLOTHING_BLACK, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_tight_jeans", PresetColour.CLOTHING_BLACK, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_low_top_skater_shoes", PresetColour.CLOTHING_PURPLE_VERY_DARK, PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_STEEL, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_short_sleeved_shirt", PresetColour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_tie", PresetColour.CLOTHING_BLACK, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_head_headband", PresetColour.CLOTHING_BLACK, false), true, this);
			}
		}

	}
	
	@Override
	public boolean isUnique() {
		return true;
	}

	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

}
