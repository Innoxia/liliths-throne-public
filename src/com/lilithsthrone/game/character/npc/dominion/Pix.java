package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
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
 * @since 0.1.6x
 * @version 0.2.3
 * @author Innoxia
 */
public class Pix extends NPC {

	private static final long serialVersionUID = 1L;

	public Pix() {
		this(false);
	}
	
	public Pix(boolean isImported) {
		super(new NameTriplet("Pix"), "An extremely energetic border-collie-girl, who is the owner and manager of the Shopping Arcade's gym; 'Pix's Playground'.",
				29, Month.FEBRUARY, 21, 
				10, Gender.F_V_B_FEMALE, RacialBody.DOG_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_PIXS_GYM, true);

		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH)));
		
		if(!isImported) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_DOG_MORPH, Colour.EYE_GREEN));
			this.setSkinCovering(new Covering(BodyCoveringType.CANINE_FUR, CoveringPattern.MARKED, CoveringModifier.FLUFFY, Colour.COVERING_BLACK, false, Colour.COVERING_WHITE, false), true);
			this.setEarType(EarType.DOG_MORPH_FOLDED);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_OLIVE), true);
			
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_CANINE_FUR, Colour.COVERING_BROWN_DARK), true);
			this.setHairStyle(HairStyle.PONYTAIL);
			
			this.setVaginaVirgin(false);
			this.setVaginaCapacity(Capacity.FOUR_LOOSE.getMedianValue(), true);
			this.setBreastSize(CupSize.B.getMeasurement());
			this.setBreastRows(3);
	
			this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
			this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
	
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_BOYSHORTS, Colour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_SPORTS_BRA, Colour.CLOTHING_GREEN_LIME, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_SPORT_SHORTS, Colour.CLOTHING_BLUE_LIGHT, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_TRAINERS, Colour.CLOTHING_PINK_LIGHT, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.HEAD_SWEATBAND, Colour.CLOTHING_RED, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_WRISTBANDS, Colour.CLOTHING_RED, false), true, this);
			
			this.addFetish(Fetish.FETISH_DENIAL);
			this.addFetish(Fetish.FETISH_DOMINANT);

			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.ZERO_HATE);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);

		this.addFetish(Fetish.FETISH_DENIAL);
		this.addFetish(Fetish.FETISH_DOMINANT);
		
		this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.ZERO_HATE);
	
		this.unequipAllClothingIntoVoid();

		this.setSkinCovering(new Covering(BodyCoveringType.CANINE_FUR, CoveringPattern.MARKED, CoveringModifier.FLUFFY, Colour.COVERING_BLACK, false, Colour.COVERING_WHITE, false), true);
		this.setEarType(EarType.DOG_MORPH_FOLDED);
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DOG_MORPH, Colour.EYE_GREEN));
		this.setHairStyle(HairStyle.PONYTAIL);

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_BOYSHORTS, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_SPORTS_BRA, Colour.CLOTHING_GREEN, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_SPORT_SHORTS, Colour.CLOTHING_BLUE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_TRAINERS, Colour.CLOTHING_PINK_LIGHT, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.HEAD_SWEATBAND, Colour.CLOTHING_RED, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_WRISTBANDS, Colour.CLOTHING_RED, false), true, this);
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
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}

	@Override
	public void endSex() {
	}


}
