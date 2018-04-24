package com.lilithsthrone.game.character.npc.misc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.90
 * @version 0.1.98
 * @author Innoxia
 */
public class SlaveImport extends NPC {

	private static final long serialVersionUID = 1L;

	public SlaveImport() {
		this(false);
	}
	
	public SlaveImport(boolean isImported) {
		super(new NameTriplet("Slave"), "Generic slave.",
				1, Gender.F_V_B_FEMALE, RacialBody.HUMAN, RaceStage.HUMAN,
				new CharacterInventory(0), WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE, false);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, CharacterImportSetting.NO_PREGNANCY);
		
		if(!this.getId().endsWith("SlaveImport")) {
			this.setId(Main.game.getNextNPCId(SlaveImport.class));
		}
	}
	
	public void applyNewlyImportedSlaveVariables() {
		// If the slave has only just been imported:
//		if(this.getOwnerId().isEmpty()) {
			Main.game.getFinch().addSlave(this);
			this.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_AUCTIONING_BLOCK, true);
			
			this.endPregnancy(false);
			
			this.clearNonEquippedInventory();
			if(this.getClothingInSlot(InventorySlot.NECK)!=null) {
				this.getClothingInSlot(InventorySlot.NECK).setSealed(false);
				this.unequipClothingIntoInventory(this.getClothingInSlot(InventorySlot.NECK), true, this);
			}
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NECK_SLAVE_COLLAR), true, this);
			this.getClothingInSlot(InventorySlot.NECK).setSealed(true);
			
			
			this.clearAffectionMap();
			this.setObedience((float) Math.round((-25+(Math.random()*50))));
			
			this.getSlavesOwned().clear();
			
			this.setPlayerKnowsName(true);
//		}
	}
	
	@Override
	public String getDescription() {
		return UtilText.parse(this, "As a slave, [npc.name] is no more than someone's property. The first time you saw [npc.herHim], [npc.she] was being sold off at auction in Slaver Alley.");
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}

	@Override
	public void endSex(boolean applyEffects) {
	}

}