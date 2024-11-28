package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * Not fully implemented. Added only to act as a mother NPC for the numerous daughter of Lunette.
 * 
 * @since 0.4.9.13
 * @version 0.4.9.13
 * @author Innoxia
 */
public class Lunette extends NPC {

	public Lunette() {
		this(false);
	}
	
	public Lunette(boolean isImported) {
		super(isImported,
				new NameTriplet("Lunette"), "Lilithmartuilani",
				"One of the seven elder Lilin, Lunette is one of the most powerful beings in existence.",
				7641, Month.JANUARY, 13,
				1000,
				Gender.F_V_B_FEMALE, Subspecies.DEMON, RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL,
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
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 1)));
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.SELFISH,
					PersonalityTrait.BRAVE,
					PersonalityTrait.LEWD);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_ELDER_LILIN);
			
			this.clearFetishes();
			
			//TODO
		}
		
		this.setBody(Gender.F_V_B_FEMALE, Subspecies.CENTAUR, RaceStage.GREATER, false);
		
		
		// Body:
		this.setSubspeciesOverride(Subspecies.ELDER_LILIN);
		this.setAgeAppearanceAbsolute(40);
		
		//TODO
		
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.resetInventory(true);
		
		//TODO
	}

	@Override
	public Colour getSpeechGlowColour() {
		if(this.getTorsoType().getRace()==Race.DEMON) {
			return PresetColour.BASE_RED;
		}
		return null;
	}
	
	@Override
	public String getSpeechColour() {
		//TODO
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			return "#71009E";
		}
		if(this.getTorsoType().getRace()==Race.DEMON) {
			return "#FF99F8";
		}
		return "#E194FF";
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	protected Set<GameCharacter> getChildren() {
		Set<GameCharacter> children = super.getChildren();
		
		children.add(Main.game.getNpc(Lunexis.class));
		
		if(Main.game.getDialogueFlags().hasFlag("innoxia_child_of_lunette")) {
			children.add(Main.game.getPlayer());
		}
		
		return children;
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	@Override
	public void changeFurryLevel() {
		// TODO Auto-generated method stub
		
	}
}
