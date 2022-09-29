package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.ReindeerOverseerDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.95
 * @version 0.3.5.5
 * @author Innoxia
 */
public class ReindeerOverseer extends NPC {
	
	public ReindeerOverseer() {
		this(Gender.getGenderFromUserPreferences(false, false), false);
	}
	
	public ReindeerOverseer(Gender gender) {
		this(gender, false);
	}
	
	public ReindeerOverseer(boolean isImported) {
		this(Gender.F_V_B_FEMALE, isImported);
	}
	
	public ReindeerOverseer(Gender gender, boolean isImported) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				10,
				null, null, null,
				new CharacterInventory(10), WorldType.DOMINION, PlaceType.DOMINION_STREET, false);

		if(!isImported) {
			
			this.setRandomLocation(WorldType.DOMINION, PlaceType.DOMINION_STREET, true);
			
			// RACE & NAME:
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			AbstractSubspecies subspecies = Subspecies.REINDEER_MORPH;
				
			if(gender.isFeminine()) {
				RaceStage stage = Main.game.getCharacterUtils().getRaceStageFromPreferences(Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(subspecies), gender, subspecies);
				setBody(gender, subspecies, stage, true);
				
			} else {
				RaceStage stage = Main.game.getCharacterUtils().getRaceStageFromPreferences(Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(subspecies), gender, subspecies);
				setBody(gender, subspecies, stage, true);
			}

			setName(Name.getRandomTriplet(subspecies));
			this.setPlayerKnowsName(false);
			
			// PERSONALITY & BACKGROUND:
			
			this.setHistory(Occupation.REINDEER_OVERSEER);
			
			// ADDING FETISHES:
			
			Main.game.getCharacterUtils().addFetishes(this);
			
			// BODY RANDOMISATION:
			
			Main.game.getCharacterUtils().randomiseBody(this, true);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);

			equipClothing(EquipClothingSetting.getAllClothingSettings());
			Main.game.getCharacterUtils().applyMakeup(this, true);
			
			dailyUpdate(); // Give items for sale.

			initHealthAndManaToMax();
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.resetPerksMap(true);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 3),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Not needed
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.JOB_LABOUR, settings);
//		super.equipClothing(settings);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getDescription() {
		return (UtilText.parse(this,
				"[npc.Name] is an overseer of one of the many groups of reindeer-morphs which are working throughout Dominion to keep the streets shovelled clear of snow."));
	}
	
	@Override
	public void dailyUpdate() {
		if(!this.isSlave() && !Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())) {
			if(Main.game.getCurrentWeather()!=Weather.SNOW && Main.game.getSeason()!=Season.WINTER) {
				Main.game.getDialogueFlags().values.remove(DialogueFlagValue.hasSnowedThisWinter);
				if(this.getLocation()!=Main.game.getPlayer().getLocation()) {
					this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, true);
				}
				
			} else {
				clearNonEquippedInventory(false);
				
				if(this.getLocationPlace().getPlaceType().equals(PlaceType.DOMINION_STREET) && !this.getLocation().equals(Main.game.getPlayer().getLocation())) {
					this.moveToAdjacentMatchingCellType(true);
					Main.game.getDialogueFlags().dailyReindeerReset(this.getId());
				}
				
				for (int i = 0; i < 10 + (Util.random.nextInt(6)); i++) {
					this.addItem(Main.game.getItemGen().generateItem(ItemType.PRESENT), false);
				}
				
				for (AbstractItemType item : ItemType.getAllItems()) {
					if(item!=null && item.getItemTags().contains(ItemTag.REINDEER_GIFT)) {
						for (int i = 0; i < 3 + (Util.random.nextInt(6)); i++) {
							this.addItem(Main.game.getItemGen().generateItem(item), false);
						}
					}
				}
				
				for (AbstractClothingType clothing : ClothingType.getAllClothing()) {
					if(clothing!=null && clothing.getDefaultItemTags().contains(ItemTag.REINDEER_GIFT)) {
						for (int i = 0; i < 1 + (Util.random.nextInt(2)); i++) {
							this.addClothing(Main.game.getItemGen().generateClothing(clothing), false);
						}
					}
				}
			}
		}

	}
	
	// Trading:
	
	@Override
	public String getTraderDescription() {
		return UtilText.parse(this,
				"<p>"
					+ "[npc.speech(I'm not really interested in buying anything from you,)]"
					+ " [npc.name] explains, leading you over to a nearby cart which is stacked high with boxes,"
					+ " [npc.speech(but everything here is for sale."
						+ " We passed through the Shinrin highlands on our way to Dominion this year, so I've got some of the youko's traditional clothing on offer!)]"
				+ "</p>");
	}

	@Override
	public boolean isTrader() {
		return true;
	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		return false;
	}
	
	
	// Sex:
	
	@Override
	public void endSex() {
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
		return ReindeerOverseerDialogue.ENCOUNTER_START;
	}

	
}
