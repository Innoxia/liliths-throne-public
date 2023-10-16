package com.lilithsthrone.game.character.npc.dominion;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.RandomNPC;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.8
 * @version 0.4
 * @author Innoxia
 */
public class DominionClubNPC extends RandomNPC {

	public DominionClubNPC(NPCGenerationFlag... generationFlags) {
		this(false, Gender.getGenderFromUserPreferences(false, false), Subspecies.HUMAN, RaceStage.HUMAN, generationFlags);
	}
	
	public DominionClubNPC(boolean isImported, Gender gender, AbstractSubspecies subspecies, RaceStage raceStage, NPCGenerationFlag... generationFlags) {
		super(isImported, false, generationFlags);
		
		if(isImported) {
			return;
		}
		
		// Pre-setup
		this.setLevel(Util.random.nextInt(5) + 5);
		this.setGenderIdentity(gender);
		
		// Setup
		this.setupNPC(subspecies,
				raceStage,
				null,
				false,
				false,
				false,
				false,
				false,
				false,
				false,
				generationFlags);

		// Post-setup
		this.setHomeLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
		this.setPlayerKnowsName(true);
		this.setDescription("[npc.Name] is a resident of Dominion, who you met in one of the clubs in the city's Nightlife district.");
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(this.getHomeWorldLocation()==WorldType.DOMINION) {
			this.setHomeLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
		}
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.CLUBBING, settings);
	}
}