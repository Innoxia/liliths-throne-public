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
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
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
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class Flash extends NPC {

	public Flash() {
		this(false);
	}
	
	public Flash(boolean isImported) {
		super(isImported, new NameTriplet("Flash"), "Hwit",
				"The owner of 'The Red Dragon' tavern, Flash has a commanding presence and doesn't tolerate any ruckus in his establishment.",
				42, Month.DECEMBER, 2,
				10,
				Gender.M_P_MALE,
				Subspecies.getSubspeciesFromId("innoxia_badger_subspecies_badger"),
				RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_f1"),
				PlaceType.getPlaceTypeFromId("innoxia_fields_elis_tavern_f1_room_owner"),
				true);
		if(!isImported) {
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_HEALTH_FANATIC);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 0),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.BRAVE);
			
			this.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			
			this.setHistory(Occupation.NPC_TAVERN_OWNER);
			
			this.addFetish(Fetish.FETISH_DENIAL);
			this.addFetish(Fetish.FETISH_VAGINAL_GIVING);
	
			this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_BREASTS_OTHERS, FetishDesire.THREE_LIKE);

			this.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_DEFLOWERING, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.ZERO_HATE);
		}
		
		// Body:

		// Core:
		this.setHeight(180);
		this.setFemininity(5);
		this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		this.setBodySize(BodySize.THREE_LARGE.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering("innoxia_badger_eye", PresetColour.EYE_GREY_GREEN));
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_LIGHT), true);
		this.setSkinCovering(new Covering("innoxia_badger_fur", CoveringPattern.STRIPED, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_WHITE, false), true);
		this.setSkinCovering(new Covering("innoxia_badger_fur_face", CoveringPattern.STRIPED, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_WHITE, false), true);
		
		this.setHairCovering(new Covering("innoxia_badger_hair", CoveringPattern.STRIPED, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_WHITE, false), false);
		this.setHairLength(0);
		this.setHairStyle(HairStyle.NONE);

		this.setHairCovering(new Covering("innoxia_badger_body_hair", PresetColour.COVERING_BLACK), false);
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
		this.setFaceCapacity(Capacity.ZERO_IMPENETRABLE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.FLAT.getMeasurement());
		this.setBreastShape(BreastShape.SIDE_SET);
		this.setNippleSize(NippleSize.ZERO_TINY);
		this.setAreolaeSize(AreolaeSize.ZERO_TINY);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.TWO_SMALL);
		this.setHipSize(HipSize.TWO_NARROW);
		this.setAssCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.ONE_RIGID.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.THREE_AVERAGE);
		this.setPenisSize(18);
		this.setTesticleSize(TesticleSize.TWO_AVERAGE);
		this.setPenisCumStorage(40);
		this.fillCumToMaxStorage();
		// Leave cum as normal value
		
		// Vagina:
		// No vagina
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_boxers", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_short_sleeved_shirt", PresetColour.CLOTHING_WHITE, false), true, this);

		Colour trousersColour = Util.randomItemFrom(new Colour[] {
				PresetColour.CLOTHING_GREY_DARK,
				PresetColour.CLOTHING_DESATURATED_BROWN_DARK
		});
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_trousers", trousersColour, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_hips_leather_belt", PresetColour.CLOTHING_DESATURATED_BROWN_DARK, false), true, this);
		
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_MENS_WATCH, PresetColour.CLOTHING_STEEL, PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_STEEL, false), true, this);
		
		if(Main.game.getSeason()==Season.WINTER) {
			Colour sweaterColour = Util.randomItemFrom(new Colour[] {
					PresetColour.CLOTHING_GREEN_DRAB,
					PresetColour.CLOTHING_BLUE_NAVY,
					PresetColour.CLOTHING_BLUE_GREY
			});
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.TORSO_RIBBED_SWEATER, sweaterColour, false), true, this);
		}
	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public void endSex() {
		this.applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return false;
	}

	@Override
	public void changeFurryLevel() {
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	@Override
	public void hourlyUpdate() {
		if(Main.game.getHourOfDay()>=8 && Main.game.getHourOfDay()<=23 && !Main.game.getCharactersPresent().contains(this)) {
			this.setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_f0"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_tavern_f0_bar"));
		} else {
			this.setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_f1"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_tavern_f1_room_owner"), true);
		}
	}
}
