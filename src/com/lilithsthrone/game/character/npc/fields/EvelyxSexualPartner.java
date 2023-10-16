package com.lilithsthrone.game.character.npc.fields;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.RandomNPC;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.2
 * @version 0.4.2
 * @author Innoxia
 */
public class EvelyxSexualPartner extends RandomNPC {

	public EvelyxSexualPartner(NPCGenerationFlag... generationFlags) {
		this(false, generationFlags);
	}
	
	public EvelyxSexualPartner(boolean isImported, NPCGenerationFlag... generationFlags) {
		super(isImported, false, generationFlags);
		
		if (isImported) {
			return;
		}
		
		// Pre-setup
		this.setLevel(Util.random.nextInt(5) + 5);
		this.setGenderIdentity(Gender.getGenderFromUserPreferences(false, true));
		
		Map<AbstractSubspecies, Integer> subspeciesMap = new HashMap<>();
		AbstractPlaceType placeType = Main.game.getPlayer().getLocationPlace().getPlaceType();
		for(AbstractSubspecies s : Subspecies.getAllSubspecies()) {
			if(s.getSubspeciesOverridePriority()>0) { // Do not spawn demonic races, elementals, or youko
				continue;
			}
			if(Subspecies.getWorldSpecies(WorldType.WORLD_MAP, placeType, false).containsKey(s)) {
				AbstractSubspecies.addToSubspeciesMap((int) (10000 * Subspecies.getWorldSpecies(WorldType.WORLD_MAP, placeType, false).get(s).getChanceMultiplier()), getGenderIdentity(), s, subspeciesMap);
			}
		}
		
		// Setup
		this.setupNPC(subspeciesMap,
				null,
				null,
				true,
				true,
				true,
				false,
				false,
				false,
				false,
				generationFlags);
		
		// Post-setup
		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		this.setHomeLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
		this.setDescription("[npc.Name] is a resident of the Foloi Fields, who travels to Evelyx's Dairy to pay for sex with one of the farm's cows.");
		this.removePersonalityTrait(PersonalityTrait.MUTE); // Don't want mutes in cow sex as they have dialogue
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.CASUAL, settings);
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return false;
	}
	
	// Sex:
	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
	}

	@Override
	public SexType getMainSexPreference(GameCharacter target) {
		return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
	}
}