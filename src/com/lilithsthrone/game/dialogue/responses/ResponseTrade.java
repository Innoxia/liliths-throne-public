package com.lilithsthrone.game.dialogue.responses;

import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.main.Main;

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
	
	@Override
	public boolean isTradeHighlight() {
		return true;
	}
}