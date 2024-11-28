package com.lilithsthrone.world.places;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.world.Cell;

/**
 * @since 0.3.9
 * @version 0.3.9
 * @author Innoxia
 */
public abstract class AbstractPlaceUpgrade {

	private boolean isCoreRoomUpgrade;
	
	protected String name;
	protected String descriptionForPurchase;
	protected String descriptionAfterPurchase;
	protected String roomDescription;
	
	private int installCost;
	private int removalCost;
	private int upkeep;
	private int capacity;
	
	private Colour colour;
	
	private float affectionGain;
	private float obedienceGain;
	
	private List<AbstractPlaceUpgrade> prerequisites;
	
	public AbstractPlaceUpgrade(boolean isCoreRoomUpgrade,
			Colour colour,
			String name,
			String descriptionForPurchase,
			String descriptionAfterPurchase,
			String roomDescription,
			int installCost,
			int removalCost,
			int upkeep,
			int capacity,
			float affectionGain,
			float obedienceGain,
			List<AbstractPlaceUpgrade> prerequisites) {
		
		this.isCoreRoomUpgrade = isCoreRoomUpgrade;
		this.colour = colour;
		this.name = name;
		this.descriptionForPurchase = descriptionForPurchase;
		this.descriptionAfterPurchase = descriptionAfterPurchase;
		this.roomDescription = roomDescription;
		
		this.installCost = installCost;
		this.removalCost = removalCost;
		this.upkeep = upkeep;
		this.capacity = capacity;
		
		this.affectionGain = affectionGain;
		
		this.obedienceGain = obedienceGain;
		
		if(prerequisites==null) {
			this.prerequisites = new ArrayList<>();
			
		} else {
			this.prerequisites = prerequisites;
		}
	}

	/**
	 * @param cell The cell to check for this upgrade's availability.
	 * @return A value representing availability and reasoning of availability of this upgrade. If the key is false, and the value is an empty string, then this upgrade is not added to any of the available upgrade lists which are displayed in-game.
	 */
	protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
		return new Value<>(true, "");
	}
	
	/**
	 * @param cell The cell to check for this upgrade's availability.
	 * @return A value representing availability and reasoning of availability of this upgrade. If the key is false, and the value is an empty string, then this upgrade is not added to any of the available upgrade lists which are displayed in-game.
	 */
	public Value<Boolean, String> getAvailability(Cell cell) {
		if(!Main.game.getPlayer().isHasSlaverLicense() && this.isSlaverUpgrade()) {
			return new Value<>(false, "You are unable to purchase this upgrade without a slaver license!");
		}
		
		return getExtraConditionalAvailability(cell);
	}

	/**
	 * @param cell The cell to check for this upgrade's availability.
	 * @return A value representing availability of removal and reasoning of availability removal of this upgrade.
	 */
	public Value<Boolean, String> getRemovalAvailability(Cell cell) {
		if(this.isCoreRoomUpgrade()) {
			return new Value<>(false, "You cannot directly remove core upgrades. Instead, you'll have to purchase a different core modification in order to remove the current one.");
		}
		return new Value<>(true, "");
	}
	
	public boolean isSlaverUpgrade() {
		return true;
	}
	
	public boolean isCoreRoomUpgrade() {
		return isCoreRoomUpgrade;
	}
	
	public ImmobilisationType getImmobilisationType() {
		return null;
	}

	public Colour getColour() {
		return colour;
	}

	public String getName() {
		return name;
	}

	public String getRoomDescription(Cell c) {
		return roomDescription;
	}

	public String getDescriptionForPurchase() {
		return descriptionForPurchase;
	}

	public String getDescriptionAfterPurchase() {
		return descriptionAfterPurchase;
	}

	/**
	 * @return An SVG that should be used for the cell's tile when this upgrade is in place. Returns null by default.
	 */
	public String getSVGOverride() {
		return null;
	}
	
	public int getInstallCost() {
		if(Main.game.getPlayer().hasTrait(Perk.JOB_PLAYER_CONSTRUCTION_WORKER, true)) {
			return installCost/2;
		}
		return installCost;
	}

	public int getRemovalCost() {
		if(Main.game.getPlayer().hasTrait(Perk.JOB_PLAYER_CONSTRUCTION_WORKER, true)) {
			if(removalCost>0) {
				return removalCost;
			}
			return Math.max(-(installCost/2), removalCost);
		}
		return removalCost;
	}

	public int getUpkeep() {
		return upkeep;
	}

	public int getCapacity() {
		return capacity;
	}

	public float getHourlyAffectionGain() {
		return affectionGain;
	}

	public float getHourlyObedienceGain() {
		return obedienceGain;
	}

	public List<AbstractPlaceUpgrade> getPrerequisites() {
		return prerequisites;
	}
	
	public boolean isPrerequisitesMet(GenericPlace place) {
		return place.getPlaceUpgrades().containsAll(prerequisites);
	}
	
	public void applyInstallationEffects(Cell c) {
	}

	public void applyRemovalEffects(Cell c) {
	}

	/**
	 * @param c The cell which is being given this upgrade.
	 * @return null by default. Override to return a DialogueNode if the installation of this upgrade should show the player a special scene.
	 */
	public DialogueNode getInstallationDialogue(Cell c) {
		return null;
	}

	/**
	 * 
	 * @return null by default. Override to return a DialogueNode if this upgrade should completely replace the room text instead of showing the usual room dialogue + upgrade descriptions.
	 *  The returned DialogueNode will only have its getContent() method called.
	 */
	public DialogueNode getRoomDialogue(Cell c) {
		return null;
	}
}
