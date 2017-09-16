package com.base.game.dialogue.responses;

import com.base.game.character.npc.NPC;
import com.base.game.dialogue.utils.InventoryInteraction;
import com.base.main.Main;

/**
 * @since 0.1.7?
 * @version 0.1.85
 * @author Innoxia
 */
public class ResponseTrade extends Response {
	
	private NPC tradePartner;

	public ResponseTrade(String title, String tooltipText, NPC tradePartner) {
		super(title, tooltipText, null);
		
		this.tradePartner=tradePartner;
	}
	
	public void openTrade() {
		Main.mainController.openInventory(tradePartner, InventoryInteraction.TRADING);
	}
	

	@Override
	public boolean disabledOnNullDialogue(){
		return false;
	}
}