package com.lilithsthrone.game.character.npc.dominion;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
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
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
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
 * @since 0.1.79
 * @version 0.2.4
 * @author Kumiko
 */
public class Pazu extends NPC {

	private static final long serialVersionUID = 1L;

	public Pazu() {
		this(false);
	}
	
	public Pazu(boolean isImported) {
		super(new NameTriplet("Pazu"),
				"Pazu is a harpy matriarch, and a particularly gorgeous one at that. She is new to the job and needs your help in whipping her flock into shape.",
				/* TODO (Once quest advances)
				 *  Pazu is a harpy matriarch, and a particularly gorgeous one at that. Despite this, he is actually a male harpy, a fact that he keeps hidden from everyone else for obvious reasons.
				 *  He has a friendly relationship with you, so you can visit his nest at any time*
				 * TODO (Once lover)
				 *  Pazu is a beautiful male harpy, and also your boyfriend. Despite being an ex-matriarch, he can act rather shy and bashful, and is still rather naïve.
				 *  He adores with all his heart, but due to this, he's not keen on sharing you with anybody else.
				 * TODO ( If he opens his candy shop and you're not his lover)
				 *  Pazu is a beautiful male harpy, and the owner of a candy shop. He used to be a harpy matriarch, but left the oppressing nests in search of a simpler life.
				 *  (if he opens the shop and is still your lover, his description is the same but with, "He also owns a candy shop in the shopping arcade." at the end)
				 */
				1, Gender.M_P_MALE, RacialBody.HARPY, RaceStage.LESSER,
				new CharacterInventory(1), WorldType.JUNGLE, PlaceType.JUNGLE_CLUB, true); //TODO He's in the jungle for now ^^
		
		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.LOW)));

		if(!isImported) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_HARPY, Colour.EYE_PINK));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, Colour.FEATHERS_LILAC), true);
			this.setSkinCovering(new Covering(BodyCoveringType.FEATHERS, Colour.FEATHERS_LILAC), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT), true);
	
			this.setAssVirgin(true);
			this.setFaceVirgin(true);
			
			this.setBreastSize(CupSize.FLAT.getMeasurement());
			
			this.setPenisSize(7);
			
			this.setHeight(185);
			
			this.setFemininity(80);
			
			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 4);
			this.setAttribute(Attribute.MAJOR_ARCANE, 45);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 5);
	
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.addFetish(Fetish.FETISH_ORAL_GIVING);
	
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_SHORTS, Colour.CLOTHING_WHITE, false), true, this);
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
	public String getSpeechColour() {
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			return "#7000FA";
		} else {
			return "#C18FFF";
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
	public void endSex(boolean applyEffects) {
	}


}