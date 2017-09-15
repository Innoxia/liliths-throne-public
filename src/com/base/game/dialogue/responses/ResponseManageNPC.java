package com.base.game.dialogue.responses;

import com.base.game.character.npc.NPC;
import com.base.game.dialogue.utils.NPCInventoryInteraction;
import com.base.main.Main;

public class ResponseManageNPC extends Response {
	
	private NPC npcToManage;

	public ResponseManageNPC(String title, String tooltipText, NPC npcToManage) {
		super(title, tooltipText, null);
		
		this.npcToManage=npcToManage;
	}
	
	public void openNPCManagement() {
		Main.mainController.openInventory(npcToManage, NPCInventoryInteraction.FULL_MANAGEMENT);
	}
	

	@Override
	public boolean disabledOnNullDialogue(){
		return false;
	}
}