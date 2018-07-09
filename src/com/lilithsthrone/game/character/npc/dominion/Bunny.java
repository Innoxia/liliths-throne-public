package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.fetishes.Fetish;
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
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.2
 * @version 0.2.2
 * @author Innoxia
 */
public class Bunny extends NPC {

	private static final long serialVersionUID = 1L;

	public Bunny() {
		this(false);
	}
	
	public Bunny(boolean isImported) {
		super(new NameTriplet("Bunny"),
				"Bunny is one of the two prostitutes Angel has working for her."
						+ " Just like her twin sister, Loppy, Bunny is a rabbit-morph, and seems to genuinely love her line of work.",
				19, Month.FEBRUARY, 13,
				10, Gender.F_V_B_FEMALE, RacialBody.RABBIT_MORPH, RaceStage.PARTIAL,
				new CharacterInventory(30), WorldType.ANGELS_KISS_FIRST_FLOOR, PlaceType.ANGELS_KISS_BEDROOM_BUNNY, true);

		this.setAttribute(Attribute.MAJOR_PHYSIQUE, 15f);
		this.setAttribute(Attribute.MAJOR_ARCANE, 0f);
		this.setAttribute(Attribute.MAJOR_CORRUPTION, 75f);

		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH)));
		
		if(!isImported) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_HUMAN, Colour.EYE_GREEN));
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_RABBIT, Colour.EYE_GREEN));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, Colour.COVERING_BROWN), true);
			this.setHairCovering(new Covering(BodyCoveringType.RABBIT_FUR, Colour.COVERING_BROWN), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT), true);
			
			this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, Colour.COVERING_RED));
			this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, Colour.COVERING_PINK));
			this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, Colour.COVERING_RED));
			
			this.setFemininity(85);
			
			this.setVaginaVirgin(false);
			this.setAssVirgin(false);
			this.setFaceVirgin(false);
			this.setBreastSize(CupSize.E.getMeasurement());

			this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
			this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());

			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
			this.addFetish(Fetish.FETISH_ORAL_GIVING);
			
			this.setHeight(168);
			
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.STOMACH_LOWBACK_BODY, Colour.CLOTHING_PINK_LIGHT, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_TIGHTS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_STILETTO_HEELS, Colour.CLOTHING_PINK_LIGHT, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NECK_COLLAR_BOWTIE, Colour.CLOTHING_PINK_LIGHT, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_SUIT_CUFFS, false), true, this);
			
			this.setPiercedEar(true);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_BASIC_RING, Colour.CLOTHING_SILVER, false), true, this);
			
			dailyReset();
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_RABBIT, Colour.EYE_GREEN));
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public void dailyReset() {
		this.useItem(AbstractItemType.generateItem(ItemType.PROMISCUITY_PILL), this, false);
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
		this.returnToHome();
	}
}