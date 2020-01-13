package com.lilithsthrone.game.character.npc.submission;
import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
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
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
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
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
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
			this.setName( new NameTriplet("Axel", "Lexa", "Lexa"));
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.resetPerksMap(true);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.setAttribute(Attribute.MAJOR_CORRUPTION, 15);
		
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
		this.setSkinCovering(new Covering(BodyCoveringType.EYE_ALLIGATOR_MORPH, Colour.EYE_AQUA), true);
		this.setSkinCovering(new Covering(BodyCoveringType.ALLIGATOR_SCALES, Colour.COVERING_WHITE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, Colour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, Colour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, Colour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, Colour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.MOUTH, Colour.SKIN_IVORY, Colour.ORIFICE_INTERIOR), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_SCALES_ALLIGATOR, Colour.COVERING_GREY), false);
		this.setHairLength(0);
		this.setHairStyle(HairStyle.NONE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_SCALES_ALLIGATOR, Colour.COVERING_GREY), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_RED));
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, Colour.COVERING_RED));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, Colour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, Colour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, Colour.COVERING_PURPLE));
		
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
		this.setPenisGirth(PenisGirth.FOUR_FAT);
		this.setPenisSize(PenisSize.FOUR_HUGE);
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
		this.setHeight(177);
		this.setFemininity(50);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:
		this.setSkinCovering(new Covering(BodyCoveringType.EYE_ALLIGATOR_MORPH, Colour.EYE_AQUA), true);
		this.setSkinCovering(new Covering(BodyCoveringType.ALLIGATOR_SCALES, Colour.COVERING_WHITE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, Colour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, Colour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, Colour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, Colour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.MOUTH, Colour.SKIN_IVORY, Colour.ORIFICE_INTERIOR), false);
		
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_SCALES_ALLIGATOR, Colour.COVERING_GREY), false);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.LOOSE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_SCALES_ALLIGATOR, Colour.COVERING_GREY), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.TWO_FULL);
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
		this.setPenisGirth(PenisGirth.TWO_AVERAGE);
		this.setPenisSize(PenisSize.TWO_AVERAGE.getMedianValue());
		this.setTesticleSize(TesticleSize.TWO_AVERAGE);
		this.setPenisCumStorage(CumProduction.TWO_SMALL_AMOUNT.getMedianValue());
		this.fillCumToMaxStorage();
	}
	
	public void applyFeminisationCosmetics() {
		this.setHairStyle(HairStyle.DREADLOCKS);
		
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_CLEAR));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, Colour.COVERING_CLEAR));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
	}

	public void applyFeminisation(PenisGirth penisGirth, PenisSize penisSize, TesticleSize testicleSize, CumProduction cumProduction) {
		this.setHeight(170);
		this.setFemininity(80);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
		
		// Coverings:
		this.setSkinCovering(new Covering(BodyCoveringType.EYE_ALLIGATOR_MORPH, Colour.EYE_AQUA), true);
		this.setSkinCovering(new Covering(BodyCoveringType.ALLIGATOR_SCALES, Colour.COVERING_WHITE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, Colour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, Colour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, Colour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, Colour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.MOUTH, Colour.SKIN_IVORY, Colour.ORIFICE_INTERIOR), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_SCALES_ALLIGATOR, Colour.COVERING_GREY), false);
		this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
		this.setHairStyle(HairStyle.DREADLOCKS);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_SCALES_ALLIGATOR, Colour.COVERING_GREY), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.THREE_PLUMP);
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
	
	public void applyCage(boolean equip) {
		AbstractClothing cage = this.getClothingInSlot(InventorySlot.PENIS);
		
		if(cage==null && !equip) {
			throw new IllegalArgumentException("Axel applyCage(): Error code 1");
		}
		if(cage!=null && equip) {
			throw new IllegalArgumentException("Axel applyCage(): Error code 2");
		}
		
		if(equip) {
			AbstractClothing newCage = AbstractClothingType.generateClothing("innoxia_bdsm_chastity_cage", Colour.CLOTHING_PINK_LIGHT, Colour.CLOTHING_ROSE_GOLD, Colour.CLOTHING_STEEL, false);
			newCage.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MAJOR_DRAIN, 0));
			this.equipClothingFromNowhere(newCage, true, this);
			
		} else {
			this.forceUnequipClothingIntoVoid(this, cage);
		}
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {

		this.unequipAllClothingIntoVoid(true, true);
		
		inventory.setMoney(650);
		
		if(!this.isFeminine()) {
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_JOCKSTRAP, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_leg_jeans", Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_sock_socks", Colour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_foot_work_boots", Colour.CLOTHING_TAN, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_SHORT_SLEEVE_SHIRT, Colour.CLOTHING_PINK_LIGHT, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_MENS_WATCH, Colour.CLOTHING_BLACK_STEEL, false), true, this);
			
		} else {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelClothingFeminine)) {
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_LACY_PANTIES, Colour.CLOTHING_PINK_LIGHT, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_LACY_PLUNGE_BRA, Colour.CLOTHING_PINK_LIGHT, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_sock_thighhigh_socks", Colour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_foot_heels", Colour.CLOTHING_BLACK, Colour.CLOTHING_BLACK_JET, Colour.CLOTHING_TAN, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_head_headband_bow", Colour.CLOTHING_WHITE, Colour.CLOTHING_GREY, Colour.CLOTHING_PINK_LIGHT, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_neck_heart_necklace", Colour.CLOTHING_SILVER, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_SKATER_DRESS, Colour.CLOTHING_PINK_LIGHT, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_WOMENS_WATCH, Colour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_finger_ring", Colour.CLOTHING_SILVER, false), true, this);
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelClothingWhore)) {
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_head_headband", Colour.CLOTHING_BLACK, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.EYES_AVIATORS, Colour.CLOTHING_SILVER, Colour.CLOTHING_PURPLE, null, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_neck_heart_necklace", Colour.CLOTHING_GOLD, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_OVER_WOMENS_LEATHER_JACKET, Colour.CLOTHING_BLACK, false), true, this);
				this.isAbleToBeDisplaced(this.getClothingInSlot(InventorySlot.TORSO_OVER), DisplacementType.UNZIPS, true, true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_FISHNET_TOP, Colour.CLOTHING_PURPLE_VERY_DARK, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_sock_fishnets", Colour.CLOTHING_PURPLE_VERY_DARK, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NIPPLE_TAPE_CROSSES, Colour.CLOTHING_BLACK, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_hand_fishnet_gloves", Colour.CLOTHING_PURPLE_VERY_DARK, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_BANGLE, Colour.CLOTHING_GOLD, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_finger_gemstone_ring", Colour.CLOTHING_PLATINUM, Colour.CLOTHING_PURPLE, null, false), true, this);
//				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.HIPS_CONDOMS, Colour.CLOTHING_BLACK, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_leg_micro_skirt_pleated", Colour.CLOTHING_PURPLE_VERY_DARK, Colour.CLOTHING_BLACK, null, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_groin_lacy_thong", Colour.CLOTHING_BLACK, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_foot_thigh_high_boots", Colour.CLOTHING_BLACK, Colour.CLOTHING_BLACK_JET, Colour.CLOTHING_GREY, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_ankle_anklet", Colour.CLOTHING_GOLD, Colour.CLOTHING_GOLD, null, false), true, this);
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelClothingMaid)) {
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_LACY_PANTIES, Colour.CLOTHING_PINK_LIGHT, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_LACY_PLUNGE_BRA, Colour.CLOTHING_PINK_LIGHT, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_neck_heart_necklace", Colour.CLOTHING_SILVER, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_HEADPIECE, Colour.CLOTHING_PINK_LIGHT, Colour.CLOTHING_WHITE, null, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_DRESS, Colour.CLOTHING_PINK_LIGHT, Colour.CLOTHING_WHITE, null, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_STOCKINGS, Colour.CLOTHING_PINK_LIGHT, Colour.CLOTHING_WHITE, null, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_HEELS, Colour.CLOTHING_PINK_LIGHT, Colour.CLOTHING_WHITE, Colour.CLOTHING_SILVER, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_SLEEVES, Colour.CLOTHING_PINK_LIGHT, Colour.CLOTHING_WHITE, null, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_WOMENS_WATCH, Colour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_finger_ring", Colour.CLOTHING_SILVER, false), true, this);
				
			} else {
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_PANTIES, Colour.CLOTHING_BLACK, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_leg_tight_jeans", Colour.CLOTHING_BLACK, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_sock_socks", Colour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_foot_low_top_skater_shoes", Colour.CLOTHING_PURPLE_VERY_DARK, Colour.CLOTHING_WHITE, Colour.CLOTHING_STEEL, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_SHORT_SLEEVE_SHIRT, Colour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_neck_tie", Colour.CLOTHING_BLACK, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_head_headband", Colour.CLOTHING_BLACK, false), true, this);
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
