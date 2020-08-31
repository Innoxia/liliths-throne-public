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
	private int priority;
	private boolean defaultSticker;
	private int zLayer;
	private Colour colourDisabled;
	private Colour colourSelected;
	
	private String namePrefix;
	private int namePrefixPriority;
	
	private String namePostfix;
	private int namePostfixPriority;

	private String description;
	private boolean descriptionFullReplacement;
	private int descriptionPriority;

	private Map<InventorySlot, String> svgPaths;

	private List<ItemTag> tagsApplied;
	private List<ItemTag> tagsRemoved;

	private boolean showDisabledButton;
	private String unavailabilityText;
	private String availabilityText;
	
	
	public Sticker(String id, int priority, boolean defaultSticker, int zLayer,
			Colour colourDisabled, Colour colourSelected,
			String namePrefix, int namePrefixPriority,
			String namePostfix, int namePostfixPriority,
			String description, boolean descriptionFullReplacement, int descriptionPriority,
			Map<InventorySlot, String> svgPaths,
			List<ItemTag> tagsApplied,
			List<ItemTag> tagsRemoved,
			boolean showDisabledButton, String unavailabilityText, String availabilityText) {
		this.id = id;
		this.priority = priority;
		this.defaultSticker = defaultSticker;
		this.colourDisabled = colourDisabled;
		this.colourSelected = colourSelected;
		this.zLayer = zLayer;
		
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

	public int getPriority() {
		return priority;
	}
	
	public boolean isDefaultSticker() {
		return defaultSticker;
	}

	public int getzLayer() {
		return zLayer;
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

	public Map<InventorySlot, String> getSvgPaths() {
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
