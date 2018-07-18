package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Innoxia
 */
public class Kalahari extends NPC {

	private static final long serialVersionUID = 1L;

	public Kalahari() {
		this(false);
	}
	
	public Kalahari(boolean isImported) {
		super(new NameTriplet("Kalahari", "Kalahari", "Kalahari"),
				"Kalahari is a barmaid working at the nightclub, 'The Watering Hole'.",
				24, Month.JUNE, 12,
				20,
				Gender.F_V_B_FEMALE,
				RacialBody.CAT_MORPH,
				RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.NIGHTLIFE_CLUB,
				PlaceType.WATERING_HOLE_BAR,
				true);

		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.AVERAGE)));
		
		if(!isImported) {
			this.setBody(Gender.F_V_B_FEMALE, Subspecies.CAT_MORPH_LION, RaceStage.GREATER);
			this.setFemininity(85);
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setSkinCovering(new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, CoveringModifier.SHORT, Colour.COVERING_TAN, false, Colour.COVERING_BLACK, false), true);
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_FELINE_FUR, CoveringPattern.NONE, Colour.COVERING_BLACK, false, Colour.COVERING_BLACK, false), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, Colour.SKIN_EBONY, false, Colour.SKIN_EBONY, false), true);
			
			this.setHairStyle(HairStyle.WAVY);
			this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
	
			this.setFaceVirgin(false);
			this.setAssVirgin(false);
			this.setVaginaVirgin(false);
			
			this.setHeight(179);
			
			this.setBreastSize(CupSize.C.getMeasurement());
			this.setBreastShape(BreastShape.PERKY);
			
			this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
			this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_THONG, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_TUBE_TOP, Colour.CLOTHING_TAN, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_MICRO_SKIRT_PLEATED, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_PLATFORM_BOOTS, Colour.CLOTHING_TAN, false), true, this);
			
			this.setPiercedEar(true);			
			this.setPiercedNose(true);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_HOOPS, Colour.CLOTHING_GOLD, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NOSE_BASIC_RING, Colour.CLOTHING_GOLD, false), true, this);
			
			this.addFetish(Fetish.FETISH_SUBMISSIVE);

			this.setFetishDesire(Fetish.FETISH_BREASTS_SELF, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.ONE_DISLIKE);

			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 25);
			this.setAttribute(Attribute.MAJOR_ARCANE, 0);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 50);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		this.setSkinCovering(new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, CoveringModifier.SHORT, Colour.COVERING_TAN, false, Colour.COVERING_BLACK, false), true);
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_FELINE_FUR, CoveringPattern.NONE, Colour.COVERING_BLACK, false, Colour.COVERING_BLACK, false), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, Colour.SKIN_EBONY, false, Colour.SKIN_EBONY, false), true);
		
		this.setBreastShape(BreastShape.PERKY);

		this.setAttribute(Attribute.MAJOR_PHYSIQUE, 25);
		this.setAttribute(Attribute.MAJOR_ARCANE, 0);
		this.setAttribute(Attribute.MAJOR_CORRUPTION, 50);
	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public void endSex() {
		this.replaceAllClothing();
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}

	@Override
	public void changeFurryLevel() {
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}

}
