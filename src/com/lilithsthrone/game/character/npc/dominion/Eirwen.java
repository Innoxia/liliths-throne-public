package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
// import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
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
// import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
//import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
//import com.lilithsthrone.game.inventory.ItemTag;
//import com.lilithsthrone.game.inventory.Rarity;
//import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.11
 * @version 0.2.11
 * @author Max Nobody
 */
public class Eirwen extends NPC {

	public Eirwen() {
		this(false);
	}
	
	public Eirwen(boolean isImported) {
		super(isImported, new NameTriplet("Eirwen"), "Gleinn",
				"Eirwen is the head slave for Wallace at the Servants' Hall."
						+ "Always calm and composed, she maintains a haughty pose at all time.",
				28, Month.MARCH, 4,
				10,
				Gender.F_V_B_FEMALE,
				Subspecies.FOX_MORPH, RaceStage.GREATER, new CharacterInventory(10),
				WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_SERVANTS_HALL, true);

		if(!isImported) {
			
			
			
			dailyReset();
		}
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 15);
			this.setAttribute(Attribute.MAJOR_ARCANE, 28);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 50);
	
			this.setPersonality(Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.AVERAGE)));
	
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_SLAVE);
	
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_MASOCHIST);
			this.addFetish(Fetish.FETISH_EXHIBITIONIST);
			
			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.FOUR_LOVE);
			this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_BREASTS_SELF, FetishDesire.THREE_LIKE);
		}
		
		// Body:

		// Core:
		this.setHeight(175);
		this.setFemininity(75);
		this.setMuscle(Muscle.ONE_LIGHTLY_MUSCLED.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_FOX_MORPH, Colour.EYE_GREEN));
		this.setSkinCovering(new Covering(BodyCoveringType.FOX_FUR, Colour.COVERING_SILVER), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_FOX_FUR, Colour.COVERING_WHITE), true);
		this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
		this.setHairStyle(HairStyle.PONYTAIL);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_FOX_FUR, Colour.COVERING_WHITE), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.ONE_AVERAGE);
		this.setFaceCapacity(Capacity.TWO_TIGHT, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.D.getMeasurement());
		this.setBreastShape(BreastShape.POINTY);
		this.setNippleSize(NippleSize.ONE_SMALL);
		this.setAreolaeSize(AreolaeSize.ONE_SMALL);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.TWO_SMALL);
		this.setHipSize(HipSize.FOUR_WOMANLY);
		this.setAssCapacity(Capacity.FOUR_LOOSE, true);
		this.setAssWetness(Wetness.TWO_MOIST);
		this.setAssElasticity(OrificeElasticity.ONE_RIGID.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		// No penis
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.TWO_AVERAGE.getValue());
		this.setVaginaSquirter(false);
		this.setVaginaCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		this.setVaginaWetness(Wetness.THREE_WET);
		this.setVaginaElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setVaginaPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override 
	public void equipClothing(boolean replaceUnsuitableClothing, boolean addWeapons, boolean addScarsAndTattoos, boolean addAccessories) {
		this.unequipAllClothingIntoVoid(true);

		this.setMoney(10);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NECK_SLAVE_COLLAR, Colour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_CROTCHLESS_THONG, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_OPEN_CUP_BRA, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_HEELS, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FINGER_RING, Colour.CLOTHING_GOLD, false), true, this);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
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

	@Override
	public void endSex() {
	}

}
