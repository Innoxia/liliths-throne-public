package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.CombatBehaviour;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.9.4
 * @version 0.3.9.4
 * @author Innoxia
 */
public class EnforcerRequisitions extends NPC {

	public EnforcerRequisitions() {
		this(false);
	}
	
	public EnforcerRequisitions(boolean isImported) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				10,
				Gender.M_P_MALE, Subspecies.DOG_MORPH_GERMAN_SHEPHERD, RaceStage.GREATER,
				new CharacterInventory(10_000),
				WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS,
				false);

		if(!isImported) {
			setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setName(new NameTriplet("Lukas"));
			this.setGenericName("SWORD Enforcer");
			
			this.setPlayerKnowsName(false);
			
			this.setHistory(Occupation.NPC_ENFORCER_SWORD_SERGEANT);
			
			this.addFetish(Fetish.FETISH_DOMINANT);
			
			CharacterUtils.randomiseBody(this, true);
			
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			CharacterUtils.generateItemsInInventory(this);
			
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			
			CharacterUtils.applyMakeup(this, true);
			
			initPerkTreeAndBackgroundPerks(); // Set starting perks based on the character's race
			
			this.setCombatBehaviour(CombatBehaviour.ATTACK);
			
			this.setEssenceCount(100);
			
			initHealthAndManaToMax();
		}
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
		CharacterUtils.equipClothingFromOutfitType(this, OutfitType.JOB_SMART, settings);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getDescription() {
		return UtilText.parse(this, "This SWORD Enforcer is in charge of the requisitions area in the Enforcer HQ."); 
	}
	
	@Override
	public void endSex() {
	}

	@Override
	public boolean isClothingStealable() {
		return false;
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
	
	@Override
	public String getTraderDescription() {
		return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "REQUISITIONS_TRADE_DIALOGUE", this);
	}

	@Override
	public boolean isTrader() {
		return true;
	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		return false;
	}

}
