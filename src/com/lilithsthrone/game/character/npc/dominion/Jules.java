package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
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
import com.lilithsthrone.main.Main;
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
public class Jules extends NPC {

	private static final long serialVersionUID = 1L;

	public Jules() {
		this(false);
	}
	
	public Jules(boolean isImported) {
		super(new NameTriplet("Jules", "Jules", "Julia"),
				"Jules is the zebra-boy bouncer for the nightclub 'The Watering Hole'.",
				33, Month.FEBRUARY, 21,
				10,
				Gender.M_P_MALE,
				RacialBody.HORSE_MORPH,
				RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.NIGHTLIFE_CLUB,
				PlaceType.WATERING_HOLE_ENTRANCE,
				true);

		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.LOW)));
		
		if(!isImported) {
			this.setBody(Gender.M_P_MALE, Subspecies.HORSE_MORPH_ZEBRA, RaceStage.GREATER);
			this.setFemininity(5);
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHairStyle(HairStyle.MOHAWK);
			this.setHairLength(3);
	
			this.setPenisSize(PenisSize.FOUR_HUGE.getMedianValue());
			this.setPenisVirgin(false);
			
			this.setHeight(190);
			
			this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
			this.setBodySize(BodySize.THREE_LARGE.getMedianValue());
			
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_CROTCHLESS_BRIEFS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_WORK_BOOTS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_JEANS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_SHORT_SLEEVE_SHIRT, Colour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_MENS_WATCH, Colour.CLOTHING_SILVER, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.EYES_AVIATORS, Colour.CLOTHING_BLACK_STEEL, false), true, this);
			
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);

			this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.ONE_DISLIKE);

			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 40);
			this.setAttribute(Attribute.MAJOR_ARCANE, 0);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 60);
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
	public void endSex() {
		setPendingClothingDressing(true);
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

	// Sex:

	public SexType getForeplayPreference() {
		if(Sex.getSexPositionSlot(Main.game.getJules())==SexPositionSlot.KNEELING_RECEIVING_ORAL && this.hasPenis()) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
		}
		
		return super.getForeplayPreference();
	}
	
	public SexType getMainSexPreference() {
		if(Sex.getSexPositionSlot(Main.game.getJules())==SexPositionSlot.KNEELING_RECEIVING_ORAL && this.hasPenis()) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
		}

		return super.getMainSexPreference();
	}
}
