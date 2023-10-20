package com.lilithsthrone.game.character.npc.fields;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.RandomNPC;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesSpawnRarity;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePokerTable;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.6
 * @version 0.3.5.5
 * @author Innoxia
 */
public class TavernGambler extends RandomNPC {
	
	private DicePokerTable table;
	
	public TavernGambler(NPCGenerationFlag... generationFlags) {
		this(false, DicePokerTable.COPPER, generationFlags);
	}
	
	public TavernGambler(boolean isImported) {
		this(isImported, DicePokerTable.COPPER);
	}
	
	public TavernGambler(DicePokerTable table, NPCGenerationFlag... generationFlags) {
		this(false, table, generationFlags);
	}
	
	public TavernGambler(boolean isImported, DicePokerTable table, NPCGenerationFlag... generationFlags) {
		super(isImported, false, generationFlags);
		
		if (isImported) {
			return;
		}
		
		// Pre-setup
		this.table = table;
		setLevel(Util.random.nextInt(4) + 5);
		
		Map<AbstractSubspecies, Integer> subspeciesMap = new HashMap<>();
		for (AbstractSubspecies s : Subspecies.getAllSubspecies()) {
			if (s.getSubspeciesOverridePriority()>0) { // Do not spawn demonic races, elementals, or youko
				continue;
			}
			Map<AbstractSubspecies, SubspeciesSpawnRarity> subMap = Subspecies.getWorldSpecies(
					WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_alley"),
					PlaceType.getPlaceTypeFromId("innoxia_fields_elis_tavern_alley_dice_poker"), false);
			if (subMap.containsKey(s)) {
				AbstractSubspecies.addToSubspeciesMap((int) (10000*subMap.get(s).getChanceMultiplier()), this.getGenderIdentity(), s, subspeciesMap);
			}
		}
		
		// Setup
		this.setupGambler(subspeciesMap,
				null,
				null,
				false,
				false,
				generationFlags);
		
		// Post-setup
		this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.TWO_NEUTRAL);
		inventory.setMoney(750 + Util.random.nextInt(750));
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element properties = super.saveAsXML(parentElement, doc);
		
		Element tableElement = doc.createElement("table");
		properties.appendChild(tableElement);
		
		XMLUtil.addAttribute(doc, tableElement, "value", table.toString());
		
		return properties;
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		try {
			Element npcSpecificElement = (Element) parentElement.getElementsByTagName("table").item(0);
			this.setTable(DicePokerTable.valueOf(npcSpecificElement.getAttribute("value")));
		} catch(Exception ex) {
		}
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.CASUAL, settings);
	}
	
	public DicePokerTable getTable() {
		return table;
	}

	public void setTable(DicePokerTable table) {
		this.table = table;
	}
	
	@Override
	public String getDescription() {
		switch (table) {
			case COPPER:
				return (UtilText.parse(this, "[npc.Name] is a relative novice at dice poker, and chooses to play in the 'copper' section of the Crossed Blades poker hall."));
			case SILVER:
				return (UtilText.parse(this, "[npc.Name] has quite a lot of experience at dice poker, and chooses to play in the 'silver' section of the Crossed Blades poker hall."));
			case GOLD:
				return (UtilText.parse(this, "[npc.Name] is an expert at dice poker, and chooses to play in the 'gold' section of the Crossed Blades poker hall."));
		}
		return "";
	}
}
