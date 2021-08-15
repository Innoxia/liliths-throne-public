package com.lilithsthrone.game.character.npc.misc;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.7
 * @version 0.3.7
 * @author Innoxia
 */
public class BasicSlave extends NPC {

	public BasicSlave() {
		this(Gender.F_V_B_FEMALE, false);
	}
	
	public BasicSlave(Gender gender) {
		this(gender, false);
	}
	
	public BasicSlave(boolean isImported) {
		this(Gender.F_V_B_FEMALE, isImported);
	}
	
	public BasicSlave(Gender gender, boolean isImported) {
		super(isImported,
				new NameTriplet("Slave"), "",
				"",
				21, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(27),
				3,
				null, null, null,
				new CharacterInventory(0),
				WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL,
				false);

		if(!isImported) {
			this.setBody(gender, Subspecies.HUMAN, RaceStage.HUMAN, false);
			
			setSexualOrientation(SexualOrientation.AMBIPHILIC);

			this.setPlayerKnowsName(true);
			this.setSurname("");
			
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 0);
			
			// PERSONALITY & BACKGROUND:
			
			this.setHistory(Occupation.NPC_SLAVE);

			this.clearFetishDesires();
			this.clearFetishes();
			this.clearPersonalityTraits();
			this.clearTattoosAndScars();
			
			this.setObedience(100);
			this.setAffection(Main.game.getPlayer(), 100);
			
			
			// BODY RANDOMISATION:
			
			this.setStartingBody(true);
			
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(0);
			equipClothing(EquipClothingSetting.getAllClothingSettings());
			
			
			// MISC.:
			
			initHealthAndManaToMax();
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		if(this.body!=null) {
			this.setAssVirgin(true);
			this.setFaceVirgin(true);
			this.setNippleCrotchVirgin(true);
			this.setNippleVirgin(true);
			this.setPenisVirgin(true);
			this.setUrethraVirgin(true);
			this.setVaginaVirgin(true);
			this.setVaginaUrethraVirgin(true);
		}
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_STEEL, false), true, this);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getDescription() {
		if(this.isSlave()) {
			return UtilText.parse(this, "Having run afoul of the law, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property.");
			
		} else {
			return UtilText.parse(this, "After a period of being your slave, [npc.nameIsFull] now your trusted friend.");
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
		return null;
	}
}
