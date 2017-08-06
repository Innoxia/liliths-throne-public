package com.base.world.places;

import java.io.IOException;
import java.io.InputStream;

import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.encounters.Encounter;
import com.base.game.dialogue.places.dominion.shoppingArcade.RalphsSnacks;
import com.base.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.base.game.dialogue.places.dominion.shoppingArcade.ArcaneArts;
import com.base.game.dialogue.places.dominion.shoppingArcade.ClothingEmporium;
import com.base.game.dialogue.places.dominion.shoppingArcade.PixsPlayground;
import com.base.game.dialogue.places.dominion.shoppingArcade.ShoppingArcadeDialogue;
import com.base.utils.BaseColour;
import com.base.utils.Bearing;
import com.base.utils.Util;
import com.base.world.EntranceType;
import com.base.world.WorldType;

/**
 * @since 0.1.78
 * @version 0.1.82
 * @author Innoxia
 */
public enum ShoppingArcade implements PlaceInterface {
	
	// Standard tiles:
	ARCADE("Arcade", null, BaseColour.BLACK, ShoppingArcadeDialogue.ARCADE, null, true, false),

	// Places:
	GENERIC_SHOP("Shop", "dominion/shoppingArcade/genericShop", BaseColour.BLACK, ShoppingArcadeDialogue.GENERIC_SHOP, null, true, false),
	
	RALPHS_SHOP_ITEMS("Ralph's Snacks", "dominion/shoppingArcade/ralphShop", BaseColour.TEAL, RalphsSnacks.EXTERIOR, null, true, false),
	
	NYANS_SHOP_CLOTHING("Nyan's Clothing Emporium", "dominion/shoppingArcade/nyanShop", BaseColour.ROSE, ClothingEmporium.EXTERIOR, null, true, false),
	
	VICKYS_SHOP_WEAPONS("Arcane Arts", "dominion/shoppingArcade/vickyShop", BaseColour.MAGENTA, ArcaneArts.EXTERIOR, null, true, false),

	KATES_SHOP_BEAUTY("Succubi's Secrets", "dominion/shoppingArcade/kateShop", BaseColour.PINK, SuccubisSecrets.EXTERIOR, null, true, false),

	PIXS_GYM("City Gym", "dominion/shoppingArcade/gym", BaseColour.GOLD, PixsPlayground.GYM_EXTERIOR, null, true, false),

	// Exits & entrances:
	ARCADE_ENTRANCE("Exit", "dominion/shoppingArcade/entranceGate", BaseColour.RED, ShoppingArcadeDialogue.ENTRY, null, true, false){
		@Override
		public WorldType getLinkedWorldType() {
			return WorldType.DOMINION;
		}

		@Override
		public PlaceInterface getLinkedPlaceInterface() {
			return Dominion.CITY_SHOPPING_ARCADE;
		}	
	};

	
	private String name, SVGString;
	private BaseColour colour;
	private DialogueNodeOld dialogue;
	private Encounter encounterType;
	private boolean populated, dangerous;

	ShoppingArcade(String name,
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
				InputStream is = this.getClass().getResourceAsStream("/com/base/res/map/" + SVGPath + ".svg");
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
