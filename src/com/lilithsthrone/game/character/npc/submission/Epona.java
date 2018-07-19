package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.effects.Perk;
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
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
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
public class Epona extends NPC {

	public Epona() {
		this(false);
	}
	
	public Epona(boolean isImported) {
		super(new NameTriplet("Epona"),
				"Epona is the horse-girl in charge of organising the Gambling Den's 'Pregnancy Roulette'."
						+ " Obsessed with breeding, she absolutely loves her job, and gets incredibly excited when a new round is about to start.",
				28, Month.MAY, 28,
				10, Gender.F_P_V_B_FUTANARI, RacialBody.HORSE_MORPH, RaceStage.GREATER,
				new CharacterInventory(30), WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE, true);

		if(!isImported) {
			initEpona();
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.6.5")) {
			this.setBody(Gender.F_P_V_B_FUTANARI, RacialBody.HORSE_MORPH, RaceStage.GREATER);
			initEpona();
		}
	}
	
	private void initEpona() {
		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH)));
		
		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);

		this.setPlayerKnowsName(true);
		
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HORSE_MORPH, Colour.EYE_HAZEL));
		this.setHairCovering(new Covering(BodyCoveringType.HORSE_HAIR, Colour.COVERING_DIRTY_BLONDE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.BODY_HAIR_HORSE_HAIR, Colour.COVERING_BROWN_DARK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HORSE_HAIR, Colour.COVERING_BROWN), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_OLIVE), true);

		this.addFetish(Fetish.FETISH_IMPREGNATION);
		this.addFetish(Fetish.FETISH_PREGNANCY);
		this.addFetish(Fetish.FETISH_VOYEURIST);
		
		this.setFetishDesire(Fetish.FETISH_CUM_ADDICT, FetishDesire.THREE_LIKE);
		this.setFetishDesire(Fetish.FETISH_CUM_STUD, FetishDesire.THREE_LIKE);
		this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
		this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);

		this.addPerk(Perk.FETISH_BROODMOTHER);
		this.addTrait(Perk.FETISH_BROODMOTHER);
		this.addPerk(Perk.FETISH_SEEDER);
		this.addTrait(Perk.FETISH_SEEDER);
		
		this.setFemininity(85);
		
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
		this.setHairStyle(HairStyle.BRAIDED);
		
		this.setAssVirgin(false);
		this.setAssSize(AssSize.FOUR_LARGE.getValue());
		this.setHipSize(HipSize.FIVE_VERY_WIDE.getValue());

		this.setBreastSize(CupSize.E.getMeasurement());
		this.setBreastShape(BreastShape.POINTY);
		
		this.setFaceVirgin(false);
		
		this.setVaginaVirgin(false);
		
		this.setHeight(180);
		
		this.setPiercedEar(true);
		
		this.equipClothing(true, false);
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public void equipClothing(boolean replaceUnsuitableClothing, boolean onlyAddCoreClothing) {
		this.unequipAllClothingIntoVoid();

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_BASIC_RING, Colour.CLOTHING_COPPER, false), true, this);

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_PLUNGE_BRA, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_SLEEVELESS_TURTLENECK, Colour.CLOTHING_GREY, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_PANTIES, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_SKIRT, Colour.CLOTHING_BLACK, false), true, this);
	}
	
	@Override
	public void hourlyUpdate() {
		this.useItem(AbstractItemType.generateItem(ItemType.VIXENS_VIRILITY), this, false);
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