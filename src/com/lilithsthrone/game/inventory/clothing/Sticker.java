package com.lilithsthrone.game.inventory.clothing;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.utils.colours.Colour;

/**
 * @since 0.3.9.5
 * @version 0.3.9.5
 * @author Innoxia
 */
public class Sticker {

	private String id;
	private String name;
	private int priority;
	private boolean defaultSticker;
	private Colour colourDisabled;
	private Colour colourSelected;
	
	private String namePrefix;
	private int namePrefixPriority;
	
	private String namePostfix;
	private int namePostfixPriority;

	private String description;
	private boolean descriptionFullReplacement;
	private int descriptionPriority;

	private Map<InventorySlot, Map<Integer, String>> svgPaths;

	private List<ItemTag> tagsApplied;
	private List<ItemTag> tagsRemoved;

	private boolean showDisabledButton;
	private String unavailabilityText;
	private String availabilityText;
	
	
	public Sticker(String id, String name, int priority, boolean defaultSticker,
			Colour colourDisabled, Colour colourSelected,
			String namePrefix, int namePrefixPriority,
			String namePostfix, int namePostfixPriority,
			String description, boolean descriptionFullReplacement, int descriptionPriority,
			Map<InventorySlot, Map<Integer, String>> svgPaths,
			List<ItemTag> tagsApplied,
			List<ItemTag> tagsRemoved,
			boolean showDisabledButton, String unavailabilityText, String availabilityText) {
		this.id = id.replaceAll("'", "").replaceAll("\"", "").toLowerCase();
		this.name = name;
		this.priority = priority;
		this.defaultSticker = defaultSticker;
		this.colourDisabled = colourDisabled;
		this.colourSelected = colourSelected;
		
		this.namePrefix = namePrefix;
		this.namePrefixPriority = namePrefixPriority;
		this.namePostfix = namePostfix;
		this.namePostfixPriority = namePostfixPriority;
		
		this.description = description;
		this.descriptionFullReplacement = descriptionFullReplacement;
		this.descriptionPriority = descriptionPriority;
		
		this.svgPaths = svgPaths;
		
		this.tagsApplied = tagsApplied;
		this.tagsRemoved = tagsRemoved;
		
		this.showDisabledButton = showDisabledButton;
		this.unavailabilityText = unavailabilityText;
		this.availabilityText = availabilityText;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public boolean isDefaultSticker() {
		return defaultSticker;
	}

	public Colour getColourDisabled() {
		return colourDisabled;
	}

	public Colour getColourSelected() {
		return colourSelected;
	}

	public String getNamePrefix() {
		return namePrefix;
	}

	public int getNamePrefixPriority() {
		return namePrefixPriority;
	}

	public String getNamePostfix() {
		return namePostfix;
	}

	public int getNamePostfixPriority() {
		return namePostfixPriority;
	}

	public String getDescription() {
		return description;
	}

	public boolean isDescriptionFullReplacement() {
		return descriptionFullReplacement;
	}

	public int getDescriptionPriority() {
		return descriptionPriority;
	}

	/**
	 * Mapping InventorySlot as the equipped slot to a Map of Integers (representing zLayers) mapped to svg Strings.
	 */
	public Map<InventorySlot, Map<Integer, String>> getSvgPaths() {
		return svgPaths;
	}

	public List<ItemTag> getTagsApplied() {
		return tagsApplied;
	}

	public List<ItemTag> getTagsRemoved() {
		return tagsRemoved;
	}

	public boolean isShowDisabledButton() {
		return showDisabledButton;
	}

	public String getUnavailabilityText() {
		return unavailabilityText;
	}

	public String getAvailabilityText() {
		return availabilityText;
	}
}
