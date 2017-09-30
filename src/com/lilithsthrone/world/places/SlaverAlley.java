package com.lilithsthrone.world.places;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.lilithsthrone.game.character.Quest;
import com.lilithsthrone.game.character.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.ScarlettsShop;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaverAlleyDialogue;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.BaseColour;
import com.lilithsthrone.utils.Bearing;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.world.EntranceType;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.1.75
 * @version 0.1.83
 * @author Innoxia
 */
public enum SlaverAlley implements PlaceInterface {
	
	// Standard tiles:
	ALLEY("Alleyway", null, BaseColour.BLACK, SlaverAlleyDialogue.ALLEYWAY, null, true, false),

	// Places:
	MARKET_STALL("Slaver's stall", "dominion/slaverAlley/marketStall", BaseColour.BLACK, SlaverAlleyDialogue.MARKET_STALL, null, true, false),
	
	AUCTIONING_BLOCK("Auctioning block", "dominion/slaverAlley/auctionBlock", BaseColour.GOLD, SlaverAlleyDialogue.AUCTION_BLOCK, null, true, false),

	SLAVERY_ADMINISTRATION("Slavery Administration", "dominion/slaverAlley/slaveryAdministration", BaseColour.PURPLE, SlaverAlleyDialogue.SLAVERY_ADMINISTRATION_EXTERIOR, null, true, false){
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(new ListValue<>(PlaceUpgrade.SLAVERY_ADMINISTRATION_CELLS));
		}
	},
	
	SCARLETTS_SHOP("Scarlett's shop", "dominion/slaverAlley/scarlettsStall", BaseColour.CRIMSON, ScarlettsShop.SCARLETTS_SHOP_EXTERIOR, null, true, false){
		@Override
		public DialogueNodeOld getDialogue(boolean withRandomEncounter) {
			if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE)) { // Scarlett owns the shop:
				return ScarlettsShop.SCARLETTS_SHOP_EXTERIOR;
				
			} else { // Alexa owns the shop:
				return ScarlettsShop.ALEXAS_SHOP_EXTERIOR;
			}
		}	
	},

	// Exits & entrances:
	ALLEY_ENTRANCE("Gateway", "dominion/slaverAlley/exit", BaseColour.RED, SlaverAlleyDialogue.GATEWAY, null, true, false){
		@Override
		public WorldType getLinkedWorldType() {
			return WorldType.DOMINION;
		}

		@Override
		public PlaceInterface getLinkedPlaceInterface() {
			return Dominion.CITY_SLAVER_ALLEY;
		}	
	};

	
	private String name, SVGString;
	private BaseColour colour;
	private DialogueNodeOld dialogue;
	private Encounter encounterType;
	private boolean populated, dangerous;

	SlaverAlley(String name,
			String SVGPath,
			BaseColour colour,
			DialogueNodeOld dialogue,
			Encounter encounterType,
			boolean populated,
			boolean dangerous) {
		
		this.name = name;
		this.colour = colour;
		this.dialogue = dialogue;
		this.encounterType = encounterType;
		this.populated = populated;
		this.dangerous = dangerous;

		if(SVGPath!=null) {
			try {
				InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/map/" + SVGPath + ".svg");
				String s = Util.inputStreamToString(is);
				
				if(colour!=null) {
					s = s.replaceAll("#ff2a2a", this.colour.getShades()[0]);
					s = s.replaceAll("#ff5555", this.colour.getShades()[1]);
					s = s.replaceAll("#ff8080", this.colour.getShades()[2]);
					s = s.replaceAll("#ffaaaa", this.colour.getShades()[3]);
					s = s.replaceAll("#ffd5d5", this.colour.getShades()[4]);
				}
				SVGString = s;
	
				is.close();
	
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			SVGString = null;
		}
	}

	public String getName() {
		return name;
	}

	public BaseColour getColour() {
		return colour;
	}

	public DialogueNodeOld getDialogue(boolean withRandomEncounter) {
		if (encounterType != null && withRandomEncounter) {
			DialogueNodeOld dn = encounterType.getRandomEncounter();
			if (dn != null)
				return dn;
		}

		return dialogue;
	}

	public boolean isPopulated() {
		return populated;
	}

	public boolean isDangerous() {
		return dangerous;
	}

	public boolean isStormImmune() {
		return true;
	}
	
	public boolean isItemsDisappear() {
		return true;
	}

	public String getSVGString() {
		return SVGString;
	}
	
	
	// For determining where this place should be placed:
	
	public Bearing getBearing() {
		return null;
	}
	
	public WorldType getParentWorldType() {
		return null;
	}
	
	public PlaceInterface getParentPlaceInterface() {
		return null;
	}
	
	public EntranceType getParentAlignment() {
		return null;
	}
	
	
	// For porting to another world:
	
	public WorldType getLinkedWorldType() {
		return null;
	}
	
	public PlaceInterface getLinkedPlaceInterface() {
		return null;
	}
}
