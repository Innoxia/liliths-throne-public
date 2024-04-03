package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
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
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Pattern;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.3
 * @version 0.4.3
 * @author Innoxia
 */
public class Belle extends NPC {

	public Belle() {
		this(false);
	}
	
	public Belle(boolean isImported) {
		super(isImported, new NameTriplet("Belle"), "Lamb",
				"A young, white-woolen sheep-girl, who works as a masseuse and prostitute at Elis's spa; 'Woolly Heaven'.",
				19, Month.MARCH, 3, 
				10,
				Gender.F_V_B_FEMALE, Subspecies.getSubspeciesFromId("innoxia_sheep_subspecies_sheep"), RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE,
				true);
		if(!isImported) {
			this.hourlyUpdate(0);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_SLUT);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 0),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.LEWD);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_PROSTITUTE);

			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_MASOCHIST);
			this.addFetish(Fetish.FETISH_EXHIBITIONIST);
			
			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_BREASTS_SELF, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.THREE_LIKE);
			
			this.setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.ONE_DISLIKE);
		}
		
		// Body:
		
		// Core:
		this.setHeight(170);
		this.setFemininity(90);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());

		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_sheep_eye"), CoveringPattern.EYE_IRISES, PresetColour.EYE_BLUE_LIGHT, false, PresetColour.EYE_BLUE_LIGHT, false));
		this.setSkinCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_sheep_wool"), CoveringPattern.NONE, CoveringModifier.FLUFFY, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false), true);
		this.setSkinCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_sheep_wool_face"), CoveringPattern.NONE, CoveringModifier.SMOOTH, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_PALE), true);

		this.setHairCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_sheep_hair"), CoveringPattern.NONE, CoveringModifier.FLUFFY, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false), false);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.WAVY);
		
		this.setHairCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_sheep_body_hair"), PresetColour.COVERING_WHITE), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_PINK_LIGHT));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_PINK_LIGHT));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_PINK_LIGHT));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PINK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.B.getMeasurement());
		this.setBreastShape(BreastShape.PERKY);
		this.setNippleSize(NippleSize.THREE_LARGE.getValue());
		this.setAreolaeSize(AreolaeSize.THREE_LARGE.getValue());
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.THREE_NORMAL);
		this.setHipSize(HipSize.THREE_GIRLY);
		this.setAssCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.FOUR_LIMBER.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ZERO_TINY);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.TWO_TIGHT, true);
		this.setVaginaWetness(Wetness.THREE_WET);
		this.setVaginaElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
		this.setVaginaPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		
		// Feet:
//		this.setFootStructure(FootStructure.PLANTIGRADE);
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		
		AbstractClothing choker = Main.game.getItemGen().generateClothing("innoxia_bdsm_choker", PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_STEEL, null, false);
		choker.setSticker("top_txt", "worthless");
		choker.setSticker("btm_txt", "cunt");
		this.equipClothingFromNowhere(choker, true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_head_headband_bow", PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_GREY, PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("TonyJC_tie_up_crop_top", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
		AbstractClothing cropTop = this.getClothingInSlot(InventorySlot.TORSO_UNDER);
		this.isAbleToBeDisplaced(cropTop, DisplacementType.UNTIE, true, true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_wrist_bracelets", PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_STEEL, null, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_wrist_bracelets", PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_STEEL, null, false), InventorySlot.ANKLE, true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_micro_skirt_pleated", PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_BLACK, null, false), true, this);
		
		AbstractClothing thong = Main.game.getItemGen().generateClothing("innoxia_groin_crotchless_thong", PresetColour.CLOTHING_PINK_LIGHT, false);
		thong.setPattern(Pattern.getPatternIdByName("polka_dots_small"));
		thong.setPatternColour(0, PresetColour.CLOTHING_WHITE);
		thong.setPatternColour(1, PresetColour.CLOTHING_PINK);
		this.equipClothingFromNowhere(thong, true, this);

		this.setPiercedEar(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_hoops", PresetColour.CLOTHING_SILVER, false), true, this);
		this.setPiercedNose(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ring", PresetColour.CLOTHING_SILVER, false), true, this);
		this.setPiercedTongue(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell", PresetColour.CLOTHING_SILVER, false), true, this);
		this.setPiercedLip(true);
		this.setPiercedNavel(true);
		this.setPiercedNipples(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_piercings_piercing_nipple_rings", PresetColour.CLOTHING_SILVER, false), true, this);
		this.setPiercedVagina(true);
	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return false;
	}

	@Override
	public void hourlyUpdate(int hour) {
		if(!Main.game.isInSex()) {
			this.useItem(Main.game.getItemGen().generateItem("innoxia_pills_sterility"), this, false);
		}
	}
	
	@Override
	public String getSpeechColour() {
		return PresetColour.BASE_PINK_SALMON.toWebHexString();
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	@Override
	public void endSex() {
		this.applyWash(true, true, null, 0);
		this.equipClothing();
	}
}
