package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.1
 * @version 0.4.1
 * @author Innoxia
 */
public class FieldsBandit extends NPC {

	public FieldsBandit() {
		this(Gender.getGenderFromUserPreferences(false, false), false);
	}
	
	public FieldsBandit(Gender gender) {
		this(gender, false);
	}
	
	public FieldsBandit(boolean isImported) {
		this(Gender.getGenderFromUserPreferences(false, false), isImported);
	}
	
	/**
	 * You must manually place this NPC in a location after creation!
	 */
	public FieldsBandit(Gender gender, boolean isImported, NPCGenerationFlag... generationFlags) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				5,
				null, null, null,
				new CharacterInventory(10), WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false,
				generationFlags);

		if(!isImported) {
			// Set random level from 10 to 20:
			setLevel(10 + Util.random.nextInt(11));
			
			// RACE & NAME:
			
			Map<AbstractSubspecies, Integer> availableRaces = new HashMap<>();
			AbstractPlaceType placeType = Main.game.getPlayer().getLocationPlace().getPlaceType();
			for(AbstractSubspecies s : Subspecies.getAllSubspecies()) {
				if(s.getSubspeciesOverridePriority()>0) { // Do not spawn demonic races, elementals, or youko
					continue;
				}
				if(Subspecies.getWorldSpecies(WorldType.WORLD_MAP, placeType, false).containsKey(s)) {
					AbstractSubspecies.addToSubspeciesMap((int) (10000 * Subspecies.getWorldSpecies(WorldType.WORLD_MAP, placeType, false).get(s).getChanceMultiplier()), gender, s, availableRaces);
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces, true, true);
			
			if(Math.random()<Main.getProperties().halfDemonSpawnRate/100f && this.getSubspecies()!=Subspecies.SLIME) { // Don't convert slimes, as their getFleshSubspecies() can be of any non-Fields subspecies
				this.setBody(Main.game.getCharacterUtils().generateHalfDemonBody(this, gender, this.getBody().getFleshSubspecies(), true), true);
			}
			
			if(Math.random()<Main.getProperties().taurSpawnRate/100f
					&& this.getLegConfiguration()!=LegConfiguration.QUADRUPEDAL) { // Do not reset this character's taur body if they spawned as a taur (as otherwise subspecies-specific settings get overridden by global taur settings)
				// Check for race's leg type as taur, otherwise NPCs which spawn with human legs won't be affected by taur conversion rate:
				if(this.getRace().getRacialBody().getLegType().isLegConfigurationAvailable(LegConfiguration.QUADRUPEDAL)) {
					this.setLegType(this.getRace().getRacialBody().getLegType());
					Main.game.getCharacterUtils().applyTaurConversion(this);
				}
			}
			
			setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(gender));
			
			setName(Name.getRandomTriplet(this.getSubspecies()));
			this.setPlayerKnowsName(false);
			
			// PERSONALITY & BACKGROUND:
			
			Main.game.getCharacterUtils().setHistoryAndPersonality(this, true);
			this.setHistory(Occupation.NPC_MUGGER);
			setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(gender));
			
			// ADDING FETISHES:
			
			Main.game.getCharacterUtils().addFetishes(this);
			
			// BODY RANDOMISATION:
			
			Main.game.getCharacterUtils().randomiseBody(this, true);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);
			
			if(!Arrays.asList(generationFlags).contains(NPCGenerationFlag.NO_CLOTHING_EQUIP)) {
				this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			}
			Main.game.getCharacterUtils().applyMakeup(this, true);
			Main.game.getCharacterUtils().applyTattoos(this, true);
			
			// Set starting perks based on the character's race
			initPerkTreeAndBackgroundPerks();
			this.setStartingCombatMoves();
			loadImages();

			initHealthAndManaToMax();
		}

		this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE, true);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Not needed
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.incrementMoney((int) (this.getInventory().getNonEquippedValue() * 2.5f));
		this.clearNonEquippedInventory(false);
		Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);
		String outfitId = "innoxia_genericMugger_dominion_masculine";
		if(this.isFeminine()) {
			outfitId = "innoxia_genericMugger_dominion_feminine";
		}
		Main.game.getCharacterUtils().equipClothingFromOutfitId(this, outfitId, settings);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getDescription() {
		if(this.isSlave()) {
			return UtilText.parse(this,
					"[npc.NamePos] days of roaming the Foloi Fields and preying upon innocent travellers are now over. Having run afoul of the law, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property.");
			
		} else if(Main.game.getPlayer()!=null && Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())) {
			return UtilText.parse(this, "When you first met [npc.name], [npc.she] was a bandit who roamed the Foloi Fields looking for travellers to prey upon. After a period of being your slave, [npc.sheIs] now your trusted friend.");
			
		} else {
			return UtilText.parse(this, "[npc.Name] is a bandit who roams the Foloi Fields looking for travellers to prey upon.");
		}
	}

	@Override
	public boolean isClothingStealable() {
		return true;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null; // Not used in encounter generation
	}

	// Combat:

	@Override
	public void applyEscapeCombatEffects() {
		Main.game.banishNPC(this);
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		return null; // Post-combat responses are handled in the dialogue itself
	}

	
	// Misc.:
	
	public int getPaymentDemand() {
		return (Math.max(2500, Math.min(Main.game.getPlayer().getMoney()/10, 10000))/500) * 500; // Round to nearest 500
	}
}
