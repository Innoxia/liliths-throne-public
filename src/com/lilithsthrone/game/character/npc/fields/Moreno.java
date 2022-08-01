package com.lilithsthrone.game.character.npc.fields;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
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
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.1
 * @version 0.4.1
 * @author Innoxia
 */
public class Moreno extends NPC {
	
	public Moreno() {
		this(false);
	}
	
	public Moreno(boolean isImported) {
		super(isImported, new NameTriplet("Moreno"), "Caballo",
				"On every day but Wednesday and Saturday, Moreno operates the strength tester game in the centre of Elis's Farmer's Market.",
				31, Month.AUGUST, 28,
				25, Gender.M_P_MALE, Subspecies.HORSE_MORPH, RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.getWorldTypeFromId("innoxia_fields_elis_market"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_market_centre"),
				true);

		this.setGenericName("muscular horse-boy");
		
		if(!isImported) {
			this.setPlayerKnowsName(false);
		}
		
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.1.1")) {
			this.setStartingBody(true);
			this.equipClothing();
		}
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
					PersonalityTrait.KIND);
			
			this.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			
			this.setHistory(Occupation.NPC_STORE_OWNER);

			this.clearFetishDesires();
			this.clearFetishes();
			
//			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_CUM_STUD);
			
			this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE);
		}
		
		// Body:

		// Core:
		this.setHeight(200);
		this.setFemininity(5);
		this.setMuscle(95);
		this.setBodySize(BodySize.THREE_LARGE.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HORSE_MORPH, PresetColour.EYE_BLUE_DARK));
		this.setSkinCovering(new Covering(BodyCoveringType.HORSE_HAIR, PresetColour.COVERING_BLACK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_EBONY), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HORSE_HAIR, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH);
		this.setHairStyle(HairStyle.NONE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HORSE_HAIR, PresetColour.COVERING_BLACK), false);
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
		this.setFaceVirgin(false);
		// Leave as default:
//		this.setLipSize(LipSize.ONE_AVERAGE);
//		this.setFaceCapacity(Capacity.ZERO_IMPENETRABLE, true);
		// Throat settings and modifiers
//		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		// Leave as default:
//		this.setNippleVirgin(true);
//		this.setBreastSize(CupSize.FLAT.getMeasurement());
//		this.setBreastShape(BreastShape.ROUND);
//		this.setNippleSize(NippleSize.ZERO_TINY);
//		this.setAreolaeSize(AreolaeSize.ZERO_TINY);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		// Leave as default:
//		this.setAssSize(AssSize.TWO_SMALL);
//		this.setHipSize(HipSize.TWO_NARROW);
//		this.setAssCapacity(Capacity.ZERO_IMPENETRABLE, true);
//		this.setAssWetness(Wetness.ZERO_DRY);
//		this.setAssElasticity(OrificeElasticity.ONE_RIGID.getValue());
//		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.FIVE_THICK);
		this.setPenisSize(32);
		this.setTesticleSize(TesticleSize.FOUR_HUGE);
		this.setPenisCumStorage(500);
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
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_briefs", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_sport_shorts", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("TonyJC_tank_top", PresetColour.CLOTHING_BLUE_NAVY, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_head_sweatband", PresetColour.CLOTHING_GREY, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WRISTBANDS, PresetColour.CLOTHING_GREY, false), true, this);
	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		return "#808fff";
	}

	@Override
	public void turnUpdate() {
		if(!Main.game.getCharactersPresent().contains(this)) {
			if(Main.game.isHourBetween(8, 19) && Main.game.getDayOfWeek()!=DayOfWeek.SATURDAY && Main.game.getDayOfWeek()!=DayOfWeek.WEDNESDAY) {
				this.returnToHome(); // Centre of farmer's market
				
			} else {
				this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			}
		}
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
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
}
