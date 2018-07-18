package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.75
 * @version 0.2.1
 * @author Innoxia
 */
public class Scarlett extends NPC {

	private static final long serialVersionUID = 1L;

	public Scarlett() {
		this(false);
	}
	
	public Scarlett(boolean isImported) {
		super(new NameTriplet("Scarlett"),
				"Scarlett is the owner of the rather unoriginally named establishment 'Scarlett's shop'."
						+ " Rude, loud, and quick to anger, Scarlett isn't a very pleasant person to have to deal with.",
				23, Month.NOVEMBER, 14,
				5, Gender.M_P_MALE, RacialBody.HARPY, RaceStage.LESSER,
				new CharacterInventory(30), WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);

		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.AVERAGE)));
		
		if(!isImported) {
			this.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_HARPY, Colour.EYE_RED));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, Colour.COVERING_BLACK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.FEATHERS, Colour.COVERING_BLACK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT), true);
	
			this.setAssVirgin(true);
			this.setFaceVirgin(true);
			this.setBreastSize(CupSize.AA.getMeasurement());
			
			this.setHeight(155);
			
			this.setPiercedEar(true);
			
			this.addFetish(Fetish.FETISH_ANAL_GIVING);
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_SADIST);
			
			getDressed();
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(!this.isSlave()) {
			this.addFetish(Fetish.FETISH_ANAL_GIVING);
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_SADIST);
			
			this.resetInventory();
			getDressed();
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_HARPY, Colour.EYE_RED));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, Colour.COVERING_BLACK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.FEATHERS, Colour.COVERING_BLACK), true);
		}
		
	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	public void getDressed() {
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_PANTIES, Colour.CLOTHING_PINK_LIGHT, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_TIGHT_TROUSERS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.EYES_GLASSES, Colour.CLOTHING_BLACK_STEEL, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_SLEEVELESS_TURTLENECK, Colour.CLOTHING_RED_DARK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_BASIC_RING, Colour.CLOTHING_SILVER, false), true, this);
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public String getDescription() {
		if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE)) {
			return UtilText.parse(this,
					"Once the owner of a shop in Slaver Alley, Scarlett is now a slave [npc.herself]."
					+ " Rude, loud, and quick to anger, [npc.she] isn't a very pleasant person to have to deal with.");
			
		} else {
			return UtilText.parse(this,
					"Scarlett is the owner of the rather unoriginally named establishment 'Scarlett's shop'."
							+ " Rude, loud, and quick to anger, [npc.she] isn't a very pleasant person to have to deal with.");
		}
	}
	
	@Override
	public String getSpeechColour() {
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			return "#FA0060";
		} else {
			return "#FF94BD";
		}
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