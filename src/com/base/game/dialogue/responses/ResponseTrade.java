package com.base.game.dialogue.responses;

import com.base.game.character.npc.NPC;
import com.base.main.Main;

public class ResponseTrade extends Response {
	
	private NPC tradePartner;

	public ResponseTrade(String title, String tooltipText, NPC tradePartner) {
		super(title, tooltipText, null);
		
		this.tradePartner=tradePartner;
	}
	
	public void openTrade() {
		Main.mainController.openInventory(tradePartner);
	}
	

	@Override
	public boolean disabledOnNullDialogue(){
		return false;
	}
}