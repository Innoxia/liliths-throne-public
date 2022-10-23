package com.lilithsthrone.game.character.npc.misc;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.time.Month;
import java.util.*;
import java.util.function.Predicate;

/**
 * @since 0.4.5.5
 * @version 0.4.5.5
 * @author AceXp
 */
public class moddedCharacter extends NPC {

	protected boolean isTrader;

	public void setTrader(boolean trader) {
		isTrader = trader;
	}

	protected Set<ItemTag> itemTagsToBuy;

	public void setItemTagsToBuy(ItemTag[] itemTagsToBuy) {
		Collections.addAll(this.itemTagsToBuy, itemTagsToBuy);
	}

	protected boolean isAbleToBeImpregnated;

	public void setAbleToBeImpregnated(boolean ableToBeImpregnated) {
		isAbleToBeImpregnated = ableToBeImpregnated;
	}

	protected boolean isClothingStealable;

	public void setClothingStealable(boolean clothingStealable) {
		isClothingStealable = clothingStealable;
	}

	public moddedCharacter() {
		this(Gender.getGenderFromUserPreferences(false, false), WorldType.EMPTY, new Vector2i(0, 0), false);
	}

	public moddedCharacter(boolean isImported) {
		this(Gender.getGenderFromUserPreferences(false, false), WorldType.EMPTY, new Vector2i(0, 0), isImported);
	}

	public moddedCharacter(Gender gender, AbstractWorldType worldLocation, Vector2i location, boolean isImported) {
		this(gender, worldLocation, location, isImported, null);
	}

	public moddedCharacter(Gender gender, AbstractWorldType worldLocation, AbstractPlaceType placeType, boolean isImported, Predicate<AbstractSubspecies> subspeciesRemovalFilter) {
		this(gender, worldLocation, Main.game.getWorlds().get(worldLocation).getCell(placeType).getLocation(), isImported, subspeciesRemovalFilter);
	}

	public moddedCharacter(Gender gender, AbstractWorldType worldLocation, Vector2i location, boolean isImported, Predicate<AbstractSubspecies> subspeciesRemovalFilter) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3,
				null, null, null,
				new CharacterInventory(10), WorldType.DOMINION, PlaceType.DOMINION_BACK_ALLEYS, false);

		if(!isImported) {
			this.setLocation(worldLocation, location, false);
			
			setLevel(Util.random.nextInt(5) + 5);
			
			// RACE & NAME:
			
			Map<AbstractSubspecies, Integer> availableRaces = new HashMap<>();
			List<AbstractSubspecies> availableSubspecies = new ArrayList<>(Subspecies.getAllSubspecies());
			
			if(subspeciesRemovalFilter!=null) {
				availableSubspecies.removeIf(subspeciesRemovalFilter);
			}
			
			for(AbstractSubspecies s : availableSubspecies) {
				if(s.getSubspeciesOverridePriority()>0) { // Do not spawn demonic races, elementals, or youko
					continue;
				}
				if(s==Subspecies.REINDEER_MORPH
						&& Main.game.getSeason()==Season.WINTER
						&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter)) {
					AbstractSubspecies.addToSubspeciesMap(50, gender, s, availableRaces);
				}
				
				if(Subspecies.getWorldSpecies(WorldType.DOMINION, null, false).containsKey(s)) {
					AbstractSubspecies.addToSubspeciesMap((int) (1000*Subspecies.getWorldSpecies(WorldType.DOMINION, null, false).get(s).getChanceMultiplier()), gender, s, availableRaces);
				} else if(Subspecies.getWorldSpecies(WorldType.SUBMISSION, null, false).containsKey(s)) {
					AbstractSubspecies.addToSubspeciesMap((int) (1000*Subspecies.getWorldSpecies(WorldType.SUBMISSION, null, false).get(s).getChanceMultiplier()), gender, s, availableRaces);
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces, true, subspeciesRemovalFilter==null);
			
			setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(gender));
	
			setName(Name.getRandomTriplet(this.getSubspecies()));
			this.setPlayerKnowsName(false);
			setDescription(UtilText.parse(this,
					"[npc.Name] is a [npc.fullRace]."));

			// Set starting attributes based on the character's race
			initPerkTreeAndBackgroundPerks();
			this.setStartingCombatMoves();
			loadImages();

			initHealthAndManaToMax();

			this.isTrader = false;
			this.itemTagsToBuy = new HashSet<>();
			this.isAbleToBeImpregnated = false;
			this.isClothingStealable = false;
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);

		this.isTrader = false;
		this.itemTagsToBuy = new HashSet<>();
		this.isAbleToBeImpregnated = false;
		this.isClothingStealable = false;
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Not needed
	}

	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	@Override
	public boolean isAbleToBeImpregnated() {
		return this.isAbleToBeImpregnated;
	}

	@Override
	public boolean isClothingStealable() {
		return this.isClothingStealable;
	}

	@Override
	public boolean isTrader() {
		return this.isTrader;
	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		//disjoint returns true if the two specified collections have no elements in common.
		return !(Collections.disjoint(item.getItemTags(), itemTagsToBuy));
	}
}