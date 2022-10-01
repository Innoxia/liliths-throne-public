package com.lilithsthrone.game.character.npc.dominion;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
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
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
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
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class NyanMum extends NPC {
	
	public NyanMum() {
		this(false);
	}
	
	public NyanMum(boolean isImported) {
		super(isImported, new NameTriplet("Leotie"), "Rey",
				"Although she's the mother of Nyan, Leotie is the polar opposite of her daughter."
						+ " Extremely confident and assertive, she's one of the most successful businesswomen in all of Lilith's Realm."
						+ " Considering Nyan to be her most beloved treasure, Leotie's over-protective nature has undoubtedly contributed to her daughter's timid nature.",
				47, Month.APRIL, 12,
				15,
				Gender.F_V_B_FEMALE,
				Subspecies.getSubspeciesFromId("innoxia_cat_subspecies_cougar"),
				RaceStage.LESSER,
				new CharacterInventory(10),
				WorldType.NYANS_APARTMENT,
				PlaceType.NYAN_APARTMENT_SPARE_BEDROOM,
				true);
		if(!isImported) {
			this.setPlayerKnowsName(false);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.15")) {
			this.setBirthday(LocalDateTime.of(this.getBirthday().getYear(), Month.APRIL, 12, 12, 0));
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.16")) {
			this.setPlayerKnowsName(false);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.17")) {
			this.setStartingBody(true);
			this.equipClothing();
			this.setAgeAppearanceDifference(0);
			this.setBirthday(LocalDateTime.of(Main.game.getStartingDate().getYear()-(46-MINIMUM_AGE), Month.APRIL, 12, 12, 0));
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_SLUT);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 5),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:
		
		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.BRAVE,
					PersonalityTrait.SELFISH,
					PersonalityTrait.LEWD);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_BUSINESS_OWNER);
			
			this.clearFetishes();
			
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_BREASTS_SELF);
	
			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.ZERO_HATE);
		}
		
		// Body:
		
		// Core:
		this.setHeight(176);
		this.setFemininity(90);
		this.setMuscle(65);
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());

		// Coverings:
		
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HUMAN, PresetColour.EYE_YELLOW));
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_FELINE, PresetColour.EYE_YELLOW));
		this.setSkinCovering(new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, CoveringModifier.SHORT, PresetColour.COVERING_TAN, false, PresetColour.COVERING_TAN, false), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, PresetColour.SKIN_TANNED, false, PresetColour.SKIN_TANNED, false), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, PresetColour.SKIN_DARK, false, PresetColour.ORIFICE_INTERIOR, false), true);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, CoveringPattern.ORIFICE_NIPPLE, PresetColour.SKIN_DARK, false, PresetColour.ORIFICE_INTERIOR, false), true);
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, PresetColour.SKIN_DARK, false, PresetColour.ORIFICE_INTERIOR, false), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_FELINE_FUR, CoveringPattern.OMBRE, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_BROWN, false), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.WAVY);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BROWN_DARK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_FELINE_FUR, PresetColour.COVERING_BROWN_DARK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.TWO_MANICURED);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_CLEAR));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_CLEAR));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PINK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.FIVE_ROOMY, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.E.getMeasurement());
		this.setBreastShape(BreastShape.WIDE);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FIVE_VERY_WIDE);
		this.setAssCapacity(Capacity.TWO_TIGHT, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.FOUR_LIMBER.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		// No penis
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.THREE_LARGE);
		this.setVaginaSquirter(false);
		this.setVaginaCapacity(Capacity.FOUR_LOOSE, true);
		this.setVaginaWetness(Wetness.TWO_MOIST);
		this.setVaginaElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
		this.setVaginaPlasticity(OrificePlasticity.FOUR_ACCOMMODATING.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_CLEAR));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_CLEAR));
		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_NONE));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_NONE));
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_hair_accessories_hair_bobby_pins", PresetColour.CLOTHING_GOLD, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_crotchless_thong", PresetColour.CLOTHING_PURPLE_VERY_DARK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_strapless_bra", PresetColour.CLOTHING_PURPLE_VERY_DARK, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.TORSO_BODYCONZIP_DRESS, PresetColour.CLOTHING_PURPLE_ROYAL, PresetColour.CLOTHING_GOLD, null, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.TORSO_OVER_OPEN_CARDIGAN, PresetColour.CLOTHING_TAN, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_TAN, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_platform_boots", PresetColour.CLOTHING_PURPLE_ROYAL, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WOMENS_WATCH, PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_GOLD, false), true, this);
		
		AbstractClothing ring = Main.game.getItemGen().generateClothing("innoxia_finger_gemstone_ring", PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_WHITE, null, false);
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MINOR_BOOST, 0));
		this.equipClothingFromNowhere(ring, true, this);
		
		AbstractClothing necklace = Main.game.getItemGen().generateClothing("innoxia_neck_gemstone_necklace", PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_WHITE, null, false);
		necklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		necklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		necklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		necklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MINOR_BOOST, 0));
		this.equipClothingFromNowhere(necklace, true, this);
		
		this.setPiercedEar(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_pearl_studs", PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_GOLD, false), true, this);
	}
	
	public void wearCasual() {
		this.unequipAllClothingIntoVoid(true, true);

		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_NONE));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_NONE));
		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_NONE));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_NONE));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_NONE));
		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_NONE));
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_thong", PresetColour.CLOTHING_RED_BURGUNDY, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.CHEST_FULLCUP_BRA, PresetColour.CLOTHING_RED_BURGUNDY, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_asymmetrical_skirt", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.TORSO_SLEEVELESS_TURTLENECK, PresetColour.CLOTHING_KHAKI, false), true, this);
	}

	public void wearLingerie(boolean kinky) {
		this.unequipAllClothingIntoVoid(true, true);

		if(kinky) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_crotchless_thong", PresetColour.CLOTHING_PURPLE_ROYAL, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.CHEST_OPEN_CUP_BRA, PresetColour.CLOTHING_PURPLE_ROYAL, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_bell_collar", PresetColour.CLOTHING_PURPLE_ROYAL, PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_SILVER, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_tail_ribbon_tail_ribbon", PresetColour.CLOTHING_BLACK, false), true, this);
			
		} else {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_thong", PresetColour.CLOTHING_PURPLE_ROYAL, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_PURPLE_ROYAL, false), true, this);
		}
	}

	@Override
	public String getArtworkFolderName() {
		if(this.isVisiblyPregnant()) {
			return "NyanMumPregnant";
		}
		return "NyanMum";
	}
	
	@Override
	public String getSpeechColour() {
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			return super.getSpeechColour();
		}
		return "#caa1ea";
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}

	@Override
	public void changeFurryLevel() {
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

//	@Override
//	public void endPregnancy(boolean withBirth) {
//		List<String> offspringIds = new ArrayList<>();
//		if(withBirth) {
//			offspringIds = pregnantLitter.getOffspring();
//		}
//		super.endPregnancy(withBirth);
//
//		if(withBirth) {
//			// Leotie's children can't be encountered as she finds them jobs elsewhere in the Realm:
//			for(String npc : offspringIds) {
//				Main.game.removeNPC(npc);
//			}
//		}
//	}
}
