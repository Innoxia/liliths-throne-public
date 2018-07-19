package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
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
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public class Axel extends NPC {

	public Axel() {
		this(false);
	}
	
	public Axel(boolean isImported) {
		super(new NameTriplet("Axel"),
				"The buff albino alligator-boy, Axel, is the owner and manager of Submission's Gambling Den."
					+ " Despite his large and menacing figure, he's very kind and understanding, and always tries his best to satisfy his customers.",
				36, Month.JANUARY, 10,
				15, Gender.M_P_MALE, RacialBody.ALLIGATOR_MORPH, RaceStage.GREATER,
				new CharacterInventory(30), WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE, true);

		if(!isImported) {
			setSexualOrientation(SexualOrientation.ANDROPHILIC);
			
			this.setPlayerKnowsName(true);
			
			// PERSONALITY & BACKGROUND:

			this.setPersonality(Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.AVERAGE)));
			
			// ADDING FETISHES:
			
			this.addFetish(Fetish.FETISH_ANAL_GIVING);

			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_CUM_STUD, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_CUM_ADDICT, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE);
			
			// BODY:
			
			this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
			this.setBodySize(BodySize.THREE_LARGE.getMedianValue());
			this.setHeight(199);
			
			this.setHairLength(0);
			
			this.setSkinCovering(new Covering(BodyCoveringType.ALLIGATOR_SCALES, Colour.COVERING_WHITE), true);
			this.setSkinCovering(new Covering(BodyCoveringType.BODY_HAIR_SCALES_ALLIGATOR, Colour.COVERING_WHITE), true);
			this.setSkinCovering(new Covering(BodyCoveringType.EYE_ALLIGATOR_MORPH, Colour.EYE_AQUA), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HAIR_SCALES_ALLIGATOR, Colour.COVERING_WHITE), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.COVERING_WHITE), true);
			this.setSkinCovering(new Covering(BodyCoveringType.PENIS, Colour.COVERING_WHITE), true);
			
			this.setPenisGirth(PenisGirth.FOUR_FAT.getValue());
			this.setPenisSize(PenisSize.FOUR_HUGE.getMedianValue());
			this.setPenisCumStorage(CumProduction.FOUR_LARGE.getMedianValue());
			this.fillCumToMaxStorage();
			
			// INVENTORY:
			
			resetInventory();
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			
			equipClothing(true, false);
			
			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 65);
			this.setAttribute(Attribute.MAJOR_ARCANE, 0);
			
			setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
			setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		}
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
	public void equipClothing(boolean replaceUnsuitableClothing, boolean onlyAddCoreClothing) {
		this.unequipAllClothingIntoVoid();
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_JOCKSTRAP, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_JEANS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_WORK_BOOTS, Colour.CLOTHING_TAN, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_SHORT_SLEEVE_SHIRT, Colour.CLOTHING_PINK_LIGHT, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_MENS_WATCH, Colour.CLOTHING_BLACK_STEEL, false), true, this);
		
	}

	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}

}
