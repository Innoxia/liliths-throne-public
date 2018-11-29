package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.12
 * @version 0.2.12
 * @author Innoxia
 */
public class Lyssieth extends NPC {

	public Lyssieth() {
		this(false);
	}
	
	public Lyssieth(boolean isImported) { //TODO All of this is placeholder. Just needed for 0.2.12.5 so that DS can be correctly set as slave.
		super(isImported,
				new NameTriplet("Lyssieth"),
				"One of the seven elder Lilin, Lyssieth is one of the most powerful beings in existence.",
				5734, Month.OCTOBER, 13, //TODO
				500, //TODO
				Gender.F_V_B_FEMALE, Subspecies.DEMON, RaceStage.GREATER, //TODO Lilin race
				new CharacterInventory(10),
				WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_THRONE_ROOM,
				true);

		if(!isImported) {
			this.setPlayerKnowsName(true);
			//TODO spells
		}
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 100);
			this.setAttribute(Attribute.MAJOR_ARCANE, 100);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 100);
			
			this.setPersonality(Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.AVERAGE)));
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_MUGGER); //TODO Elder Lilin
			
			this.clearFetishes();
			
			//TODO fetishes
			
		}
		
		//TODO body
		
	}
	
	@Override
	public void equipClothing(boolean replaceUnsuitableClothing, boolean addWeapons, boolean addScarsAndTattoos, boolean addAccessories) {
		this.unequipAllClothingIntoVoid(true);
		
		//TODO
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
	public void endSex() {
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}

}