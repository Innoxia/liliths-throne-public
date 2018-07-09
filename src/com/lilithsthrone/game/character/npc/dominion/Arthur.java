package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
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
 * @since 0.1.95
 * @version 0.2.7
 * @author Innoxia
 */
public class Arthur extends NPC {

	private static final long serialVersionUID = 1L;

	public Arthur() {
		this(false);
	}
	
	public Arthur(boolean isImported) {
		super(new NameTriplet("Arthur"),
				"With messy brown hair, pale skin, and a thin frame, the Arthur of this world looks exactly the same as the one you've always known."
						+ " Just as he was in your world, this Arthur used to be a colleague of Lilaya's, before Lilaya kicked him out."
						+ "<br/>"
						+ "Arthur is recognised as one of, if not the best arcane researcher in all of Dominion."
						+ " Despite this, however, he's unable to harness the arcane himself.",
				41, Month.DECEMBER, 9,
				10,
				Gender.M_P_MALE,
				RacialBody.HUMAN, RaceStage.HUMAN, new CharacterInventory(10),
				WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_OFFICE, true);

		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH)));
		
		if(!isImported) {
			this.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			
			this.setSkinCovering(new Covering(BodyCoveringType.HAIR_HUMAN, Colour.COVERING_BROWN_DARK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_PALE), true);
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_HUMAN, Colour.EYE_BLUE));
			this.setHairStyle(HairStyle.MESSY);
			this.setHeight(183); // 6 foot
			
			// Thin:
			this.setMuscle(Muscle.ONE_LIGHTLY_MUSCLED.getMedianValue());
			this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
			
			this.setPenisVirgin(false);
			
			this.setMoney(0);
			
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_BOXERS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_SHORT_SLEEVE_SHIRT, Colour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SCIENTIST_TORSO_OVER_LAB_COAT, Colour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_TROUSERS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SCIENTIST_EYES_SAFETY_GOGGLES, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_WORK_BOOTS, Colour.CLOTHING_TAN, false), true, this);
			
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}

	@Override
	public String getDescription() {
		return "With messy brown hair, pale skin, and a thin frame, the Arthur of this world looks exactly the same as the one you've always known."
				+ " Just as he was in your world, this Arthur used to be a colleague of Lilaya's, before Lilaya kicked him out."
				+ "<br/>"
				+ "Arthur is recognised as one of, if not the best arcane researcher in all of Dominion."
				+ " Despite this, however, he's unable to harness the arcane himself.";
	}
	
	@Override
	public boolean isUnique() {
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
	public boolean isTrader() {
		return true; // TODO Wait, what?
	}

	@Override
	public void endSex() {
	}

}