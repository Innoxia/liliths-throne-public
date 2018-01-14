package com.lilithsthrone.game.character.npc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.NameTriplet;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
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
 * @version 0.1.90
 * @author Innoxia
 */
public class SlaveImport extends NPC {

	private static final long serialVersionUID = 1L;

	public SlaveImport() {
		super(new NameTriplet("Slave"), "Generic slave.",
				1, Gender.F_V_B_FEMALE, RacialBody.HUMAN, RaceStage.HUMAN,
				new CharacterInventory(0), WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE, false);
	}
	
	@Override
	public SlaveImport loadFromXML(Element parentElement, Document doc) {
		SlaveImport npc = new SlaveImport();
		
		loadNPCVariablesFromXML(npc, null, parentElement, doc);
//		npc.setId(Main.game.getNextNPCId(SlaveImport.class));
//		npc.resetInventory();
//		npc.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NECK_SLAVE_COLLAR), true, npc);
		
		return npc;
	}
	
	public void applyNewlyImportedSlaveVariables() {
		// If the slave has only just been imported:
//		if(this.getOwnerId().isEmpty()) {
			Main.game.getFinch().addSlave(this);
			this.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_AUCTIONING_BLOCK, true);
			
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
	
	@Override
	public String getCombatDescription() {
		return null;
	}
	@Override
	public String getAttackDescription(Attack attackType, boolean isHit) {
		return null;
	}
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		return null;
	}
	@Override
	public Attack attackType() {
		return null;
	}
	@Override
	public int getExperienceFromVictory() {
		return 0;
	}

}