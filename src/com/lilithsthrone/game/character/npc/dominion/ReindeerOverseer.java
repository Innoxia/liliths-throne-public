package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.History;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.ReindeerOverseerDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.95
 * @version 0.2.4
 * @author Innoxia
 */
public class ReindeerOverseer extends NPC {

	private static final long serialVersionUID = 1L;
	
	public ReindeerOverseer() {
		this(Gender.F_V_B_FEMALE, false);
	}
	
	public ReindeerOverseer(Gender gender) {
		this(gender, false);
	}
	
	public ReindeerOverseer(boolean isImported) {
		this(Gender.F_V_B_FEMALE, isImported);
	}
	
	public ReindeerOverseer(Gender gender, boolean isImported) {
		super(null, "",
				Util.random.nextInt(21)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				10, gender, RacialBody.REINDEER_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.DOMINION, PlaceType.DOMINION_STREET, false);

		if(!isImported) {
			
			this.setRandomLocation(WorldType.DOMINION, PlaceType.DOMINION_STREET, true);
			
			// RACE & NAME:
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			Subspecies species = Subspecies.REINDEER_MORPH;
				
			if(gender.isFeminine()) {
				switch(Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(species)) {
					case HUMAN: case MINIMUM:
						setBodyFromPreferences(1, gender, species);
						break;
					case REDUCED:
						setBodyFromPreferences(2, gender, species);
						break;
					case NORMAL:
						setBodyFromPreferences(3, gender, species);
						break;
					case MAXIMUM:
						setBody(gender, species, RaceStage.GREATER);
						break;
				}
			} else {
				switch(Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(species)) {
					case HUMAN: case MINIMUM:
						setBodyFromPreferences(1, gender, species);
						break;
					case REDUCED:
						setBodyFromPreferences(2, gender, species);
						break;
					case NORMAL:
						setBodyFromPreferences(3, gender, species);
						break;
					case MAXIMUM:
						setBody(gender, species, RaceStage.GREATER);
						break;
				}
			}

			setName(Name.getRandomTriplet(species.getRace()));
			this.setPlayerKnowsName(false);
			
			// PERSONALITY & BACKGROUND:
			
			this.setHistory(History.REINDEER_OVERSEER);
			
			// ADDING FETISHES:
			
			CharacterUtils.addFetishes(this);
			
			// BODY RANDOMISATION:
			
			CharacterUtils.randomiseBody(this);
			
			// INVENTORY:
			
			resetInventory();
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			
			CharacterUtils.equipClothing(this, true, false);
			CharacterUtils.applyMakeup(this, true);
			
			dailyReset(); // Give items for sale.
			
			setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
			setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
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
	public void dailyReset() {
		clearNonEquippedInventory();
		
		for (int i = 0; i < 10 + (Util.random.nextInt(6)); i++) {
			this.addItem(AbstractItemType.generateItem(ItemType.PRESENT), false);
		}
		
		for (AbstractItemType item : ItemType.allItems) {
			if(item!=null && item.getItemTags().contains(ItemTag.REINDEER_GIFT)) {
				for (int i = 0; i < 3 + (Util.random.nextInt(6)); i++) {
					this.addItem(AbstractItemType.generateItem(item), false);
				}
			}
		}
		
		for (AbstractClothingType clothing : ClothingType.getAllClothing()) {
			if(clothing!=null && clothing.getItemTags().contains(ItemTag.REINDEER_GIFT)) {
				for (int i = 0; i < 1 + (Util.random.nextInt(2)); i++) {
					this.addClothing(AbstractClothingType.generateClothing(clothing), false);
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
						+ " We passed through the Kitsune's forest on our way to Dominion this year, so I've got some of their traditional clothes here too!)]"
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
	public DialogueNodeOld getEncounterDialogue() {
		return ReindeerOverseerDialogue.ENCOUNTER_START;
	}

	
}
