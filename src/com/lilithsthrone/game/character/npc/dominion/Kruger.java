package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
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
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.8
 * @version 0.2.9
 * @author Innoxia
 */
public class Kruger extends NPC {

	public Kruger() {
		this(false);
	}
	
	public Kruger(boolean isImported) {
		super(new NameTriplet("Kruger", "Kruger", "Kruger"),
				"Kruger is the owner of the nightclub, 'The Watering Hole'.",
				42, Month.JUNE, 12,
				20,
				Gender.M_P_MALE,
				RacialBody.CAT_MORPH,
				RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.NIGHTLIFE_CLUB,
				PlaceType.WATERING_HOLE_VIP_AREA,
				true);

		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH)));
		
		if(!isImported) {
			this.setBody(Gender.M_P_MALE, Subspecies.CAT_MORPH_LION, RaceStage.GREATER);
			this.setFemininity(0);
			this.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			
			this.setSkinCovering(new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, CoveringModifier.SHORT, Colour.COVERING_TAN, false, Colour.COVERING_BLACK, false), true);
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_FELINE_FUR, CoveringPattern.NONE, Colour.COVERING_BLACK, false, Colour.COVERING_BLACK, false), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, Colour.SKIN_EBONY, false, Colour.SKIN_EBONY, false), true);
			
			this.setHairStyle(HairStyle.LOOSE);
			this.setHairLength(8);
	
			this.setPenisSize(PenisSize.THREE_LARGE.getMedianValue());
			this.setPenisVirgin(false);
			
			this.setHeight(188);
			
			this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
			this.setBodySize(BodySize.THREE_LARGE.getMedianValue());
			
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_BRIEFS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_MENS_SMART_SHOES, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_TROUSERS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_OXFORD_SHIRT, Colour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_OVER_SUIT_JACKET, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_MENS_WATCH, Colour.CLOTHING_GOLD, false), true, this);
			
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_SADIST);
			this.addFetish(Fetish.FETISH_VAGINAL_GIVING);

			this.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.ZERO_HATE);

			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 60);
			this.setAttribute(Attribute.MAJOR_ARCANE, 0);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 80);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		this.setSexualOrientation(SexualOrientation.GYNEPHILIC);
		
		this.setSkinCovering(new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, CoveringModifier.SHORT, Colour.COVERING_TAN, false, Colour.COVERING_BLACK, false), true);
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_FELINE_FUR, CoveringPattern.NONE, Colour.COVERING_BLACK, false, Colour.COVERING_BLACK, false), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, Colour.SKIN_EBONY, false, Colour.SKIN_EBONY, false), true);
	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public void changeFurryLevel() {
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}
	

	@Override
	public boolean getSexBehaviourDeniesRequests(SexType sexTypeRequest) {
		return true;
	}
	
	public Set<SexPositionSlot> getSexPositionPreferences() {
		sexPositionPreferences.clear();
		
		if(Sex.isInForeplay()) {
			sexPositionPreferences.add(SexPositionSlot.CHAIR_ORAL_SITTING);
		} else {
			sexPositionPreferences.add(SexPositionSlot.CHAIR_BOTTOM);
		}
		
		return sexPositionPreferences;
	}
	
	public SexType getForeplayPreference() {
		return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
	}
	
}
