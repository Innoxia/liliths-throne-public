package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.submission.BatCavernDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.3
 * @version 0.3.5.5
 * @author Innoxia
 */
public class BatCavernSlimeAttacker extends NPC {

	public BatCavernSlimeAttacker() {
		this(Gender.getGenderFromUserPreferences(false, false), false);
	}
	
	public BatCavernSlimeAttacker(Gender gender) {
		this(gender, false);
	}
	
	public BatCavernSlimeAttacker(boolean isImported) {
		this(Gender.F_V_B_FEMALE, isImported);
	}
	
	public BatCavernSlimeAttacker(Gender gender, boolean isImported) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3, gender, Subspecies.HUMAN, RaceStage.HUMAN,
				new CharacterInventory(10), WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERN_DARK, false);

		if(!isImported) {
			this.setLocation(Main.game.getPlayer(), true);
			
			// Set random level from 8 to 12:
			setLevel(8 + Util.random.nextInt(5));
			
			// RACE & NAME:
			
			this.setBody(gender, Subspecies.SLIME, RaceStage.GREATER, false);
			
			setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(gender));
	
			setName(Name.getRandomTriplet(this.getRace()));
			this.setPlayerKnowsName(false);
			
			// PERSONALITY & BACKGROUND:
			
			Main.game.getCharacterUtils().setHistoryAndPersonality(this, true);
			
			// ADDING FETISHES:
			
			Main.game.getCharacterUtils().addFetishes(this);
			
			// BODY RANDOMISATION:
			
			Main.game.getCharacterUtils().randomiseBody(this, true);
			
			this.setBodyMaterial(BodyMaterial.SLIME);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(50 + Util.random.nextInt(getLevel()*10) + 1);
			Main.game.getCharacterUtils().generateItemsInInventory(this);
	
			equipClothing(EquipClothingSetting.getAllClothingSettings());
			Main.game.getCharacterUtils().applyMakeup(this, true);
			
			// Set starting attributes based on the character's race
			initPerkTreeAndBackgroundPerks();
			this.setStartingCombatMoves();
			loadImages();
			
			this.useItem(Main.game.getItemGen().generateItem(ItemType.MUSHROOM), this, false);

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
		this.incrementMoney((int) (this.getInventory().getNonEquippedValue() * 0.5f));
		this.clearNonEquippedInventory(false);
		Main.game.getCharacterUtils().generateItemsInInventory(this);

		Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.MUGGER, settings); //TODO need slime-specific?
	}
	
	@Override
	public void hourlyUpdate() {
		if(!this.isSlave() && !Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())) {
			this.useItem(Main.game.getItemGen().generateItem(ItemType.MUSHROOM), this, false);
		}
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getDescription() {
		if(this.isSlave()) {
			return (UtilText.parse(this,
					"[npc.NamePos] days of getting high on mushrooms and attacking innocent travellers in the Bat Caverns are now over."
							+ " Having been enslaved as punishment for [npc.her] lawless behaviour, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property."));
		} else if(Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())){
			return (UtilText.parse(this,
					"[npc.NamePos] days of getting high on mushrooms and attacking innocent travellers in the Bat Caverns are now over."
					+ " Having befriended [npc.herHim], you invited [npc.name] to move in with you and helped [npc.herHim] to start a new life."));
		} else {
			return (UtilText.parse(this,
					"[npc.Name] is a resident of the bat caverns, and loves nothing more than getting high on mushrooms, attacking innocent travellers, and having sex."));
		}
	}
	
	@Override
	public void endSex() {
		if(!isSlave()) {
			setPendingClothingDressing(true);
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
		return BatCavernDialogue.CAVERN_ATTACK;
	}

	// Combat:

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", BatCavernDialogue.AFTER_COMBAT_VICTORY);
		} else {
			return new Response ("", "", BatCavernDialogue.AFTER_COMBAT_DEFEAT);
		}
	}
}
