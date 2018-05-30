package com.lilithsthrone.game.inventory.clothing;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.lilithsthrone.controller.xmlParsing.*;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.ColourListPresets;
import com.lilithsthrone.utils.Util;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @since 0.1.84
 * @version 0.2.4
 * @author Innoxia
 */
public abstract class AbstractClothingType extends AbstractCoreType {

	protected static final long serialVersionUID = 1L;
	
	private String determiner, name, namePlural, description, pathName, pathNameEquipped;

	private boolean plural;
	private boolean isMod;
	private int baseValue;
	private int physicalResistance;
	private int femininityMinimum;
	private int femininityMaximum;
	private Femininity femininityRestriction;
	private InventorySlot slot;

	// Enchantments:
	private int enchantmentLimit;
	protected List<ItemEffect> effects;

	
	// Images:
	private Map<Colour, Map<Colour, Map<Colour, String>>> SVGStringMap;
	private Map<Colour, Map<Colour, Map<Colour, String>>> SVGStringEquippedMap;
	
	
	// Access and block stuff:
	private List<BlockedParts> blockedPartsList;
	private List<InventorySlot> incompatibleSlots;

	private ClothingSet clothingSet;
	private Rarity rarity;
	private List<Colour> availablePrimaryColours;
	private List<Colour> availablePrimaryDyeColours;
	private List<Colour> allAvailablePrimaryColours;
	
	private List<Colour> availableSecondaryColours;
	private List<Colour> availableSecondaryDyeColours;
	private List<Colour> allAvailableSecondaryColours;
	
	private List<Colour> availableTertiaryColours;
	private List<Colour> availableTertiaryDyeColours;
	private List<Colour> allAvailableTertiaryColours;

	private List<DisplacementType> displacementTypesAvailableWithoutNONE;
	
	private List<ItemTag> itemTags;

	private Map<DisplacementType, Map<DisplacementDescriptionType, String>> displacementDescriptionsPlayer;
	private Map<DisplacementType, Map<DisplacementDescriptionType, String>> displacementDescriptionsNPC;
	
	protected AbstractClothingType(
			int baseValue,
			String determiner,
			boolean plural,
			String name,
			String namePlural,
			String description,
			int physicalResistance,
			Femininity femininityRestriction,
			InventorySlot slot,
			Rarity rarity,
			ClothingSet clothingSet,
			String pathName,
			List<ItemEffect> effects,
			List<BlockedParts> blockedPartsList,
			List<InventorySlot> incompatibleSlots,
			List<Colour> availablePrimaryColours,
			List<Colour> availablePrimaryDyeColours,
			List<Colour> availableSecondaryColours,
			List<Colour> availableSecondaryDyeColours,
			List<Colour> availableTertiaryColours,
			List<Colour> availableTertiaryDyeColours,
			List<ItemTag> itemTags) {
		this(baseValue,
				determiner,
				plural,
				name,
				namePlural,
				description,
				physicalResistance,
				femininityRestriction,
				slot,
				rarity,
				clothingSet,
				pathName,
				null,
				effects,
				blockedPartsList,
				incompatibleSlots,
				availablePrimaryColours,
				availablePrimaryDyeColours,
				availableSecondaryColours,
				availableSecondaryDyeColours,
				availableTertiaryColours,
				availableTertiaryDyeColours,
				itemTags);
	}
	
	public AbstractClothingType(
			int baseValue,
			String determiner,
			boolean plural,
			String name,
			String namePlural,
			String description,
			int physicalResistance,
			Femininity femininityRestriction,
			InventorySlot slot,
			Rarity rarity,
			ClothingSet clothingSet,
			String pathName,
			String pathNameEquipped,
			List<ItemEffect> effects,
			List<BlockedParts> blockedPartsList,
			List<InventorySlot> incompatibleSlots,
			List<Colour> availablePrimaryColours,
			List<Colour> availablePrimaryDyeColours,
			List<Colour> availableSecondaryColours,
			List<Colour> availableSecondaryDyeColours,
			List<Colour> availableTertiaryColours,
			List<Colour> availableTertiaryDyeColours,
			List<ItemTag> itemTags) {
		
		this.baseValue = baseValue;
		
		this.isMod = false;
		
		this.determiner = determiner;
		this.plural = plural;
		this.name = name;
		this.namePlural = namePlural;
		this.description = description;

		this.physicalResistance = physicalResistance;
		
		setUpFemininity(femininityRestriction);
		
		this.femininityRestriction = femininityRestriction;

		this.slot = slot;
		this.rarity = rarity;
		this.clothingSet = clothingSet;

		this.pathName = pathName;
		this.pathNameEquipped = pathNameEquipped;

		enchantmentLimit = -1;
		
		// Attribute modifiers:
		if (effects != null) {
			this.effects = new ArrayList<>(effects);
		} else {
			this.effects = new ArrayList<>();
		}
		
		// Blocked Parts:
		if (blockedPartsList != null) {
			this.blockedPartsList = blockedPartsList;
		} else {
			this.blockedPartsList = new ArrayList<>();
		}
		
		// MAke sure that clothing can't conceal itself (in case I made an error in defining concealed slots):
		for(BlockedParts bp : this.blockedPartsList) {
			bp.concealedSlots.removeIf((concealedSlot) -> concealedSlot == this.slot);
		}
		
		// Incompatible slots:
		if (incompatibleSlots != null) {
			this.incompatibleSlots = incompatibleSlots;
		} else {
			this.incompatibleSlots = new ArrayList<>();
		}
		
		setUpColours(availablePrimaryColours,
				availablePrimaryDyeColours,
				availableSecondaryColours,
				availableSecondaryDyeColours,
				availableTertiaryColours,
				availableTertiaryDyeColours);
		
		if(itemTags==null) {
			this.itemTags = new ArrayList<>();
		} else {
			this.itemTags = itemTags;
		}

		
		finalSetUp();
	}
	
	public AbstractClothingType(File clothingXMLFile) throws XMLLoadException { // Be sure to catch this exception correctly - if it's thrown mod is invalid and should not be continued to load
	    try{
		Element clothingElement = Element.getDocumentRootElement(clothingXMLFile); // Loads the document and returns the root element - in clothing mods it's <clothing>
		Element coreAttributes = clothingElement.getMandatoryFirstOf("coreAtributes"); // Assuming this element appears just once, get an element by tag. If there's no such element, throw exception and halt loading this mod - it is invalid and continuing may/will cause severe bugs

		this.effects = coreAttributes
		    .getMandatoryFirstOf("effects") 
		    .getAllOf("effect") // Get all child elements with this tag (checking only contents of parent element) and return them as List<Element>
		    .stream() // Convert this list to Stream<Element>, which lets us do some nifty operations on every element at once
		    .map( e -> ItemEffect.loadFromXML(e.getInnerElement(),e.getDocument())) // Take every element and do something with them, return a Stream of results after this action. Here we load item effects and get Stream<ItemEffect>
		    .collect(Collectors.toList()); // Collect stream back into a list, but this time we get List<ItemEffect> we need! 

		this.blockedPartsList = coreAttributes
		    .getMandatoryFirstOf("blockedPartsList")
		    .getAllOf("blockedParts").stream()
		    .map( e -> BlockedParts.loadFromXML(e.getInnerElement(), e.getDocument()))
		    .collect(Collectors.toList());

		this.incompatibleSlots = coreAttributes
		    .getMandatoryFirstOf("incompatibleSlots")
		    .getAllOf("slot").stream()
		    .map(Element::getTextContent).map(InventorySlot::valueOf)
		    .collect(Collectors.toList());

		this.itemTags = coreAttributes
		    .getMandatoryFirstOf("itemTags")
		    .getAllOf("tag").stream()
		    .map(Element::getTextContent).map(ItemTag::valueOf)
		    .collect(Collectors.toList());

		this.displacementDescriptionsPlayer = new HashMap<>();
		this.displacementDescriptionsNPC = new HashMap<>();

		Consumer<String> loadItemMoveDescriptions = repositionDescListTag -> { 
		    clothingElement.getAllOf(repositionDescListTag).stream() // <clothing>, not <coreAttributes>!
			.forEach( descriptionList ->{
			    DisplacementType type = DisplacementType.valueOf(descriptionList.getAttribute("type"));
			    displacementDescriptionsPlayer.putIfAbsent(type, new HashMap<>());
			    displacementDescriptionsNPC.putIfAbsent(type, new HashMap<>());
			    Map<DisplacementDescriptionType, String> playerMap = displacementDescriptionsPlayer.get(type);
			    Map<DisplacementDescriptionType, String> npcMap = displacementDescriptionsNPC.get(type);

			    Function<Map<DisplacementDescriptionType, String>, Consumer<String>> putTagContentTo = mapToPutIn -> tagName ->{ // try to get element by tag, if it exists, put in specified map
				descriptionList.getOptionalFirstOf(tagName)
				    .ifPresent(desc -> 
					mapToPutIn.put(DisplacementDescriptionType.byTagsPath(repositionDescListTag+" "+desc.getTagName()), desc.getTextContent()));
			    };					
			    Consumer<String> toPlayerMap = putTagContentTo.apply(playerMap);
			    Consumer<String> toNPCMap = putTagContentTo.apply(npcMap);

			    toPlayerMap.accept("playerNPC");
			    toPlayerMap.accept("playerNPCRough");
			    toPlayerMap.accept("playerSelf");

			    toNPCMap.accept("NPCPlayer");
			    toNPCMap.accept("NPCPlayerRough");
			    toNPCMap.accept("NPCSelf");

			    toNPCMap.accept("NPCOtherNPC");
			    toNPCMap.accept("NPCOtherNPCRough");
			});
		};

		loadItemMoveDescriptions.accept("replacementText");
		loadItemMoveDescriptions.accept("displacementText");			

		this.isMod = true;

		this.name =        coreAttributes.getMandatoryFirstOf("name").getTextContent();
		this.namePlural =  coreAttributes.getMandatoryFirstOf("namePlural").getTextContent();
		this.description = coreAttributes.getMandatoryFirstOf("description").getTextContent();
		this.determiner =  coreAttributes.getMandatoryFirstOf("determiner").getTextContent();

		this.plural =             Boolean.valueOf(coreAttributes.getMandatoryFirstOf("namePlural").getAttribute("pluralByDefault"));
		this.baseValue =          Integer.valueOf(coreAttributes.getMandatoryFirstOf("value").getTextContent());
		this.physicalResistance = Integer.valueOf(coreAttributes.getMandatoryFirstOf("physicalResistance").getTextContent());	
		this.slot =         InventorySlot.valueOf(coreAttributes.getMandatoryFirstOf("slot").getTextContent());
		this.rarity =              Rarity.valueOf(coreAttributes.getMandatoryFirstOf("rarity").getTextContent());

		this.femininityRestriction = Femininity.valueOf(coreAttributes.getMandatoryFirstOf("femininity").getTextContent());
		setUpFemininity(this.femininityRestriction);

		Predicate<Element> filterEmptyElements = element -> !element.getTextContent().isEmpty(); //helper function to filter out empty elements. Could put lambda directly in as argument, but this is quicker (with decent IDE).

		this.enchantmentLimit = coreAttributes.getOptionalFirstOf("enchantmentLimit") // three possible cases
		    .filter(filterEmptyElements) // <enchantmentLimit> or <enchantmentLimit></enchantmentLimit> - text content is "" - trying to convert to Integer throws  - filter it out so default value gets assigned
		    .map(Element::getTextContent).map(Integer::valueOf) //<enchantmentLimit>x</enchantmentLimit>, x being Integer		
		    .orElse(-1);// empty element or no value in element, assign default value;

		this.clothingSet = coreAttributes.getOptionalFirstOf("clothingSet")
		    .filter(filterEmptyElements)
		    .map(Element::getTextContent).map(ClothingSet::valueOf)
		    .orElse(null);

		this.pathName = clothingXMLFile.getParentFile().getAbsolutePath() + "/" 
			+ coreAttributes.getMandatoryFirstOf("imageName").getTextContent();

		this.pathNameEquipped = coreAttributes.getOptionalFirstOf("imageEquippedName")
		    .filter(filterEmptyElements)
		    .map(Element::getTextContent)
		    .orElse(null);

		Function< Element, List<Colour> > getColoursFromElement = (colorsElement) -> { //Helper function to get the colors depending on if it's a specified group or a list of individual colors
		    if(colorsElement.getAttribute("values").isEmpty()){
			return colorsElement.getAllOf("colour").stream()
			    .map(Element::getTextContent).map(Colour::valueOf)
			    .collect(Collectors.toList());
		    }
		    else{
			return ColourListPresets.valueOf(colorsElement.getAttribute("values"))
			    .getPresetColourList();
		    }
		};

		List<Colour> importedPrimaryColours = getColoursFromElement
		    .apply(coreAttributes.getMandatoryFirstOf("primaryColours"));	
		List<Colour> importedPrimaryColoursDye = getColoursFromElement
		    .apply(coreAttributes.getMandatoryFirstOf("primaryColoursDye"));		

		List<Colour> importedSecondaryColours = coreAttributes.getOptionalFirstOf("secondaryColours")
		    .map(getColoursFromElement::apply)
		    .orElse(new ArrayList<>()); // ArrayList::new doesn't work here						
		List<Colour> importedSecondaryColoursDye = coreAttributes.getOptionalFirstOf("secondaryColoursDye")
		    .map(getColoursFromElement::apply)
		    .orElse(new ArrayList<>());

		List<Colour> importedTertiaryColours = coreAttributes.getOptionalFirstOf("tertiaryColours")
		    .map(getColoursFromElement::apply)
		    .orElse(new ArrayList<>());		
		List<Colour> importedTertiaryColoursDye = coreAttributes.getOptionalFirstOf("tertiaryColoursDye")
		    .map(getColoursFromElement::apply)
		    .orElse(new ArrayList<>());

		setUpColours(
		    importedPrimaryColours,
		    importedPrimaryColoursDye,
		    importedSecondaryColours,
		    importedSecondaryColoursDye,
		    importedTertiaryColours,
		    importedTertiaryColoursDye
		);

		finalSetUp();
	    }
	    catch(XMLMissingTagException ex){
		throw new XMLLoadException(ex);
	    }
	    catch(Exception e){
		System.out.println(e);
		throw new XMLLoadException(e);
	    }
	}
	
	private void setUpFemininity(Femininity femininity) {
		if (femininity == null || femininity == Femininity.ANDROGYNOUS) {
			femininityMinimum = 0;
			femininityMaximum = 100;
			
		} else if (femininity == Femininity.FEMININE) {
			femininityMinimum = Femininity.ANDROGYNOUS.getMinimumFemininity();
			femininityMaximum = 100;
			
		} else if (femininity == Femininity.MASCULINE) {
			femininityMinimum = 0;
			femininityMaximum = Femininity.ANDROGYNOUS.getMaximumFemininity();
		}
	}
	
	private void setUpColours(List<Colour> availablePrimaryColours,
			List<Colour> availablePrimaryDyeColours,
			List<Colour> availableSecondaryColours,
			List<Colour> availableSecondaryDyeColours,
			List<Colour> availableTertiaryColours,
			List<Colour> availableTertiaryDyeColours) {
		
		this.availablePrimaryColours = new ArrayList<>();
		if (availablePrimaryColours == null) {
			this.availablePrimaryColours.add(Colour.CLOTHING_BLACK);
		} else {
			this.availablePrimaryColours.addAll(availablePrimaryColours);
		}

		Set<Colour> colourSet = new HashSet<>();
		
		this.availablePrimaryDyeColours = new ArrayList<>();
		if (availablePrimaryDyeColours != null) {
			this.availablePrimaryDyeColours.addAll(availablePrimaryDyeColours);
		}
		
		this.allAvailablePrimaryColours = new ArrayList<>();
		colourSet.addAll(availablePrimaryColours);
		if(availablePrimaryDyeColours!=null) {
			colourSet.addAll(availablePrimaryDyeColours);
		}
		this.allAvailablePrimaryColours.addAll(colourSet);
		this.allAvailablePrimaryColours.sort((c1, c2) -> c1.compareTo(c2));
		
		this.availableSecondaryColours = new ArrayList<>();
		if (availableSecondaryColours != null) {
			this.availableSecondaryColours.addAll(availableSecondaryColours);
		}
		
		this.availableSecondaryDyeColours = new ArrayList<>();
		if (availableSecondaryDyeColours != null) {
			this.availableSecondaryDyeColours.addAll(availableSecondaryDyeColours);
		}

		colourSet.clear();
		this.allAvailableSecondaryColours = new ArrayList<>();
		if(availableSecondaryColours!=null) {
			colourSet.addAll(availableSecondaryColours);
		}
		if(availableSecondaryDyeColours!=null) {
			colourSet.addAll(availableSecondaryDyeColours);
		}
		this.allAvailableSecondaryColours.addAll(colourSet);
		this.allAvailableSecondaryColours.sort((c1, c2) -> c1.compareTo(c2));

		
		this.availableTertiaryColours = new ArrayList<>();
		if (availableTertiaryColours != null) {
			this.availableTertiaryColours.addAll(availableTertiaryColours);
		}
		
		this.availableTertiaryDyeColours = new ArrayList<>();
		if (availableTertiaryDyeColours != null) {
			this.availableTertiaryDyeColours.addAll(availableTertiaryDyeColours);
		}

		colourSet.clear();
		this.allAvailableTertiaryColours = new ArrayList<>();
		if(availableTertiaryColours!=null) {
			colourSet.addAll(availableTertiaryColours);
		}
		if(availableTertiaryDyeColours!=null) {
			colourSet.addAll(availableTertiaryDyeColours);
		}
		this.allAvailableTertiaryColours.addAll(colourSet);
		this.allAvailableTertiaryColours.sort((c1, c2) -> c1.compareTo(c2));
	}
	
	private void finalSetUp() {
		displacementTypesAvailableWithoutNONE = new ArrayList<>();
		for (BlockedParts bp : this.blockedPartsList) {
			if (bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
				displacementTypesAvailableWithoutNONE.add(bp.displacementType);
			}
		}
		Collections.sort(displacementTypesAvailableWithoutNONE);

		SVGStringMap = new HashMap<>();
		SVGStringEquippedMap = new HashMap<>();
	}
	
	@Override
	public boolean equals (Object o) { // I know it doesn't include everything, but this should be enough to check for equality.
		if(super.equals(o)){
			if(o instanceof AbstractClothingType){
				if(((AbstractClothingType)o).getName().equals(getName())
						&& ((AbstractClothingType)o).getPathName().equals(getPathName())
						&& ((AbstractClothingType)o).getPhysicalResistance() == getPhysicalResistance()
						&& ((AbstractClothingType)o).getFemininityMaximum() == getFemininityMaximum()
						&& ((AbstractClothingType)o).getFemininityMinimum() == getFemininityMinimum()
						&& ((AbstractClothingType)o).getFemininityRestriction() == getFemininityRestriction()
						&& ((AbstractClothingType)o).getSlot() == getSlot()
						&& ((AbstractClothingType)o).getEffects().equals(getEffects())
						&& ((AbstractClothingType)o).getClothingSet() == getClothingSet()
						&& ((AbstractClothingType)o).getRarity() == getRarity()
						){
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() { // I know it doesn't include everything, but this should be enough to check for equality.
		int result = super.hashCode();
		result = 31 * result + getName().hashCode();
		result = 31 * result + getPathName().hashCode();
		result = 31 * result + getPhysicalResistance();
		result = 31 * result + getFemininityMaximum();
		result = 31 * result + getFemininityMinimum();
		if(getFemininityRestriction()!=null)
			result = 31 * result + getFemininityRestriction().hashCode();
		result = 31 * result + getEffects().hashCode();
		if(getClothingSet()!=null)
			result = 31 * result + getClothingSet().hashCode();
		result = 31 * result + getRarity().hashCode();
		return result;
	}
	
	public static AbstractClothing generateClothing(AbstractClothingType clothingType, Colour primaryColour, Colour secondaryColour, Colour tertiaryColour, boolean allowRandomEnchantment) {
		Colour c1 = primaryColour;
		Colour c2 = secondaryColour;
		Colour c3 = tertiaryColour;

		if (clothingType.getAllAvailablePrimaryColours() != null) {
			if (!clothingType.getAllAvailablePrimaryColours().contains(primaryColour)) {
				c1 = clothingType.getAllAvailablePrimaryColours().get(Util.random.nextInt(clothingType.getAllAvailablePrimaryColours().size()));
			}
		}
		
		if (secondaryColour == null) {
			if(clothingType.getAvailableSecondaryColours().isEmpty()) {
				c2 = Colour.CLOTHING_BLACK;
			} else {
				c2 = clothingType.getAvailableSecondaryColours().get(Util.random.nextInt(clothingType.getAvailableSecondaryColours().size()));
			}
		} else if(!clothingType.getAllAvailableSecondaryColours().contains(secondaryColour)) {
			if(clothingType.getAllAvailableSecondaryColours().isEmpty()) {
				c2 = Colour.CLOTHING_BLACK;
			} else {
				c2 = clothingType.getAllAvailableSecondaryColours().get(Util.random.nextInt(clothingType.getAllAvailableSecondaryColours().size()));
			}
		}
		
		if (tertiaryColour == null) {
			if(clothingType.getAvailableTertiaryColours().isEmpty()) {
				c3 = Colour.CLOTHING_BLACK;
			} else {
				c3 = clothingType.getAvailableTertiaryColours().get(Util.random.nextInt(clothingType.getAvailableTertiaryColours().size()));
			}
			
		} else if(!clothingType.getAllAvailableTertiaryColours().contains(tertiaryColour)) {
			if(clothingType.getAllAvailableTertiaryColours().isEmpty()) {
				c3 = Colour.CLOTHING_BLACK;
			} else {
				c3 = clothingType.getAllAvailableTertiaryColours().get(Util.random.nextInt(clothingType.getAllAvailableTertiaryColours().size()));
			}
		}
		
		return new AbstractClothing(clothingType, c1, c2, c3, allowRandomEnchantment) {
			private static final long serialVersionUID = 1L;
		};
	}

	public static AbstractClothing generateClothing(AbstractClothingType clothingType, Colour colourShade, boolean allowRandomEnchantment) {
		return AbstractClothingType.generateClothing(clothingType, colourShade, null, null, allowRandomEnchantment);
	}

	/**
	 * Allows random enchantment. Uses random colour.
	 */
	public static AbstractClothing generateClothing(AbstractClothingType clothingType) {
		return AbstractClothingType.generateClothing(clothingType, clothingType.getAvailablePrimaryColours().get(Util.random.nextInt(clothingType.getAvailablePrimaryColours().size())), true);
	}

	/**
	 * Uses random colour.
	 */
	public static AbstractClothing generateClothing(AbstractClothingType clothingType, boolean allowRandomEnchantment) {
		return AbstractClothingType.generateClothing(clothingType, clothingType.getAvailablePrimaryColours().get(Util.random.nextInt(clothingType.getAvailablePrimaryColours().size())), allowRandomEnchantment);
	}
	
	
	/**
	 * Generates clothing with the provided enchantments.
	 */
	public static AbstractClothing generateClothing(AbstractClothingType clothingType, Colour primaryColour, Colour secondaryColour, Colour tertiaryColour, List<ItemEffect> effects) {
		Colour c1 = primaryColour;
		Colour c2 = secondaryColour;
		Colour c3 = tertiaryColour;

		if (clothingType.getAllAvailablePrimaryColours() != null) {
			if (!clothingType.getAllAvailablePrimaryColours().contains(primaryColour)) {
				c1 = clothingType.getAllAvailablePrimaryColours().get(Util.random.nextInt(clothingType.getAllAvailablePrimaryColours().size()));
			}
		}
		
		if (secondaryColour == null || !clothingType.getAllAvailableSecondaryColours().contains(secondaryColour)) {
			if(clothingType.getAvailableSecondaryColours().isEmpty()) {
				c2 = Colour.CLOTHING_BLACK;
			} else {
				c2 = clothingType.getAvailableSecondaryColours().get(Util.random.nextInt(clothingType.getAvailableSecondaryColours().size()));
			}
		}
		
		if (tertiaryColour == null || !clothingType.getAllAvailableTertiaryColours().contains(tertiaryColour)) {
			if(clothingType.getAvailableTertiaryColours().isEmpty()) {
				c3 = Colour.CLOTHING_BLACK;
			} else {
				c3 = clothingType.getAvailableTertiaryColours().get(Util.random.nextInt(clothingType.getAvailableTertiaryColours().size()));
			}
		}
		
		return new AbstractClothing(clothingType, c1, c2, c3, effects) {
			private static final long serialVersionUID = 1L;
		};
	}
	
	/**
	 * Generates clothing with the provided enchantments.
	 */
	public static AbstractClothing generateClothing(AbstractClothingType clothingType, Colour colour, List<ItemEffect> effects) {
		return generateClothing(clothingType, colour, null, null, effects);
	}
	
	/**
	 * Uses random colour.
	 */
	public static AbstractClothing generateClothing(AbstractClothingType clothingType, List<ItemEffect> effects) {
		return AbstractClothingType.generateClothing(clothingType, clothingType.getAvailablePrimaryColours().get(Util.random.nextInt(clothingType.getAvailablePrimaryColours().size())), effects);
	}
	
	/**
	 * Generates clothing with a random enchantment.
	 */
	public static AbstractClothing generateClothingWithEnchantment(AbstractClothingType clothingType, Colour colour) {
		List<ItemEffect> effects = new ArrayList<>();

		TFModifier rndMod = TFModifier.getClothingAttributeList().get(Util.random.nextInt(TFModifier.getClothingAttributeList().size()));
		effects.add(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, rndMod, TFPotency.getRandomWeightedPositivePotency(), 0));
		
		return generateClothing(clothingType, colour, effects);
	}
	
	/**
	 * Uses random colour.
	 */
	public static AbstractClothing generateClothingWithEnchantment(AbstractClothingType clothingType) {
		return AbstractClothingType.generateClothingWithEnchantment(clothingType, clothingType.getAvailablePrimaryColours().get(Util.random.nextInt(clothingType.getAvailablePrimaryColours().size())));
	}
	
	public String getId() {
		return ClothingType.getIdFromClothingType(this);
	}

	public int getBaseValue() {
		return baseValue;
	}

	static Map<ClothingSet, List<AbstractClothingType>> clothingSetMap = new EnumMap<>(ClothingSet.class);

	public static List<AbstractClothingType> getClothingInSet(ClothingSet set) {
		if (clothingSetMap.get(set) != null)
			return clothingSetMap.get(set);

		List<AbstractClothingType> setOfClothing = new ArrayList<>();

		for (AbstractClothingType c : ClothingType.getAllClothing())
			if (c.getClothingSet() == set)
				setOfClothing.add(c);

		clothingSetMap.put(set, setOfClothing);

		return setOfClothing;
	}
	
	public static String getEquipDescriptions(GameCharacter clothingOwner, GameCharacter clothingEquipper, boolean rough,
			String playerEquipping,
			String playerEquippingNpc,
			String playerEquippingNpcRough,
			String npcEquipping,
			String npcEquippingPlayer,
			String npcEquippingPlayerRough,
			String npcEquippingOtherNPC,
			String npcEquippingOtherNPCRough) {
		
		if (clothingEquipper.isPlayer()) {
			if(clothingOwner.isPlayer()) {
				return UtilText.parse(clothingOwner, playerEquipping);
			} else {
				if(rough && playerEquippingNpcRough!=null) {
					return UtilText.parse(clothingOwner, playerEquippingNpcRough);
				} else {
					return UtilText.parse(clothingOwner, playerEquippingNpc);
				}
			}
			
		} else {
			if (clothingOwner.isPlayer()) {
				if(rough && npcEquippingPlayerRough!=null) {
					return UtilText.parse(clothingEquipper, npcEquippingPlayerRough);
				} else {
					return UtilText.parse(clothingEquipper, npcEquippingPlayer);
				}
			} else {
				if(clothingOwner.equals(clothingEquipper)) {
					return UtilText.parse(clothingOwner, npcEquipping);
				} else {
					if(rough && npcEquippingOtherNPCRough!=null) {
						return UtilText.parse(clothingEquipper, clothingOwner, npcEquippingOtherNPCRough);
					} else if(npcEquippingOtherNPC!=null){
						return UtilText.parse(clothingEquipper, clothingOwner, npcEquippingOtherNPC);
					} else {
						return UtilText.parse(clothingEquipper, clothingOwner, "[npc1.Name] manipulates [npc2.name]'s clothing.");
					}
				}
			}
		}
	}
	
	public String equipText(GameCharacter clothingOwner, GameCharacter clothingEquipper, boolean rough, AbstractClothing clothing, boolean applyEffects) {
		if (clothingEquipper.isPlayer()) {
			if(clothingOwner.isPlayer()) {
				if(displacementDescriptionsPlayer!=null
						&& displacementDescriptionsPlayer.containsKey(DisplacementType.REMOVE_OR_EQUIP)
						&& displacementDescriptionsPlayer.get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.REPLACEMENT_SELF)) {
					return displacementDescriptionsPlayer.get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.REPLACEMENT_SELF);
				}
				return "You equip the "+clothing.getName()+"";
				
			} else {
				if(rough) {
					if(displacementDescriptionsPlayer!=null
							&& displacementDescriptionsPlayer.containsKey(DisplacementType.REMOVE_OR_EQUIP)
							&& displacementDescriptionsPlayer.get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.REPLACEMENT_ROUGH)) {
						return UtilText.parse(clothingOwner, displacementDescriptionsPlayer.get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.REPLACEMENT_ROUGH));
					}
					return UtilText.parse(clothingOwner, "You roughly force "+clothing.getName(true)+" onto [npc.name].");
					
				} else {
					if(displacementDescriptionsPlayer!=null
							&& displacementDescriptionsPlayer.containsKey(DisplacementType.REMOVE_OR_EQUIP)
							&& displacementDescriptionsPlayer.get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.REPLACEMENT)) {
						return UtilText.parse(clothingOwner, displacementDescriptionsPlayer.get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.REPLACEMENT));
					}
					return UtilText.parse(clothingOwner, "You put "+clothing.getName(true)+" on [npc.name].");
				}
			}
			
		} else {
			if (clothingOwner.isPlayer()) {
				if(rough) {
					if(displacementDescriptionsNPC!=null
							&& displacementDescriptionsNPC.containsKey(DisplacementType.REMOVE_OR_EQUIP)
							&& displacementDescriptionsNPC.get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.REPLACEMENT_ROUGH)) {
						return UtilText.parse(clothingEquipper, displacementDescriptionsNPC.get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.REPLACEMENT_ROUGH));
					}
					return UtilText.parse(clothingEquipper, "[npc.Name] roughly forces "+clothing.getName(true)+" onto you.");
					
				} else {
					if(displacementDescriptionsNPC!=null
							&& displacementDescriptionsNPC.containsKey(DisplacementType.REMOVE_OR_EQUIP)
							&& displacementDescriptionsNPC.get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.REPLACEMENT)) {
						return UtilText.parse(clothingEquipper, displacementDescriptionsNPC.get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.REPLACEMENT));
					}
					return UtilText.parse(clothingEquipper, "[npc.Name] puts "+clothing.getName(true)+" on you.");
				}
			} else {
				if(clothingOwner.equals(clothingEquipper)) {
					if(displacementDescriptionsNPC!=null
							&& displacementDescriptionsNPC.containsKey(DisplacementType.REMOVE_OR_EQUIP)
							&& displacementDescriptionsNPC.get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.REPLACEMENT_SELF)) {
						return UtilText.parse(clothingOwner, displacementDescriptionsNPC.get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.REPLACEMENT_SELF));
					}
					return UtilText.parse(clothingOwner, "[npc.Name] equips "+clothing.getName(true)+".");
					
				} else {
					if(rough) {
						if(displacementDescriptionsNPC!=null
								&& displacementDescriptionsNPC.containsKey(DisplacementType.REMOVE_OR_EQUIP)
								&& displacementDescriptionsNPC.get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.NPC_ON_NPC_REPLACEMENT_ROUGH)) {
							return UtilText.parse(clothingEquipper, clothingOwner,displacementDescriptionsNPC.get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.NPC_ON_NPC_REPLACEMENT_ROUGH));
						}
						return UtilText.parse(clothingEquipper, clothingOwner, "[npc.Name] roughly forces [npc2.name] to equip "+clothing.getName(true)+".");
						
					} else {
						if(displacementDescriptionsNPC!=null
								&& displacementDescriptionsNPC.containsKey(DisplacementType.REMOVE_OR_EQUIP)
								&& displacementDescriptionsNPC.get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.NPC_ON_NPC_REPLACEMENT)) {
							return UtilText.parse(clothingEquipper, clothingOwner,displacementDescriptionsNPC.get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.NPC_ON_NPC_REPLACEMENT));
						}
						return UtilText.parse(clothingEquipper, clothingOwner, "[npc.Name] gets [npc2.name] to equip "+clothing.getName(true)+".");
					}
				}
			}
		}
	}

	public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
		if (clothingRemover.isPlayer()) {
			if(clothingOwner.isPlayer()) {
				if(displacementDescriptionsPlayer!=null
						&& displacementDescriptionsPlayer.containsKey(DisplacementType.REMOVE_OR_EQUIP)
						&& displacementDescriptionsPlayer.get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.DISPLACEMENT_SELF)) {
					return displacementDescriptionsPlayer.get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.DISPLACEMENT_SELF);
				}
				return "You unequip the "+clothing.getName()+"";
			} else {
				if(rough) {
					if(displacementDescriptionsPlayer!=null
							&& displacementDescriptionsPlayer.containsKey(DisplacementType.REMOVE_OR_EQUIP)
							&& displacementDescriptionsPlayer.get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.DISPLACEMENT_ROUGH)) {
						return UtilText.parse(clothingOwner, displacementDescriptionsPlayer.get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.DISPLACEMENT_ROUGH));
					}
					return UtilText.parse(clothingOwner, "You roughly take off [npc.name]'s "+clothing.getName()+".");
				} else {
					if(displacementDescriptionsPlayer!=null
							&& displacementDescriptionsPlayer.containsKey(DisplacementType.REMOVE_OR_EQUIP)
							&& displacementDescriptionsPlayer.get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.DISPLACEMENT)) {
						return UtilText.parse(clothingOwner, displacementDescriptionsPlayer.get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.DISPLACEMENT));
					}
					return UtilText.parse(clothingOwner, "You take off [npc.name]'s "+clothing.getName()+".");
				}
			}
			
		} else {
			if (clothingOwner.isPlayer()) {
				if(rough) {
					if(displacementDescriptionsNPC!=null
							&& displacementDescriptionsNPC.containsKey(DisplacementType.REMOVE_OR_EQUIP)
							&& displacementDescriptionsNPC.get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.DISPLACEMENT_ROUGH)) {
						return UtilText.parse(clothingRemover, displacementDescriptionsNPC.get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.DISPLACEMENT_ROUGH));
					}
					return UtilText.parse(clothingRemover, "[npc.Name] roughly takes off your "+clothing.getName()+".");
				} else {
					if(displacementDescriptionsNPC!=null
							&& displacementDescriptionsNPC.containsKey(DisplacementType.REMOVE_OR_EQUIP)
							&& displacementDescriptionsNPC.get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.DISPLACEMENT)) {
						return UtilText.parse(clothingRemover, displacementDescriptionsNPC.get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.DISPLACEMENT));
					}
					return UtilText.parse(clothingRemover, "[npc.Name] takes off your "+clothing.getName()+".");
				}
			} else {
				if(clothingOwner.equals(clothingRemover)) {
					if(displacementDescriptionsNPC!=null
							&& displacementDescriptionsNPC.containsKey(DisplacementType.REMOVE_OR_EQUIP)
							&& displacementDescriptionsNPC.get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.DISPLACEMENT_SELF)) {
						return UtilText.parse(clothingOwner, displacementDescriptionsNPC.get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.DISPLACEMENT_SELF));
					}
					return UtilText.parse(clothingOwner, "[npc.Name] unequips [npc.her] "+clothing.getName()+".");
					
				} else {
					if(rough) {
						if(displacementDescriptionsNPC!=null
								&& displacementDescriptionsNPC.containsKey(DisplacementType.REMOVE_OR_EQUIP)
								&& displacementDescriptionsNPC.get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.NPC_ON_NPC_DISPLACEMENT_ROUGH)) {
							return UtilText.parse(clothingRemover, clothingOwner,displacementDescriptionsNPC.get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.NPC_ON_NPC_DISPLACEMENT_ROUGH));
						}
						return UtilText.parse(clothingRemover, clothingOwner, "[npc.Name] roughly forces [npc2.name] to equip "+clothing.getName(true)+".");
						
					} else {
						if(displacementDescriptionsNPC!=null
								&& displacementDescriptionsNPC.containsKey(DisplacementType.REMOVE_OR_EQUIP)
								&& displacementDescriptionsNPC.get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.NPC_ON_NPC_DISPLACEMENT)) {
							return UtilText.parse(clothingRemover, clothingOwner,displacementDescriptionsNPC.get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.NPC_ON_NPC_DISPLACEMENT));
						}
						return UtilText.parse(clothingRemover, clothingOwner, "[npc.Name] gets [npc2.name] to equip "+clothing.getName(true)+".");
					}
				}
			}
		}
	}

	public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
		if (clothingRemover.isPlayer()) {
			if(clothingOwner.isPlayer()) {
				if(displacementDescriptionsPlayer!=null
						&& displacementDescriptionsPlayer.containsKey(dt)
						&& displacementDescriptionsPlayer.get(dt).containsKey(DisplacementDescriptionType.DISPLACEMENT_SELF)) {
					return displacementDescriptionsPlayer.get(dt).get(DisplacementDescriptionType.DISPLACEMENT_SELF);
				}
				return "You "+dt.getDescription()+" your "+this.getName()+"";
			} else {
				if(rough) {
					if(displacementDescriptionsPlayer!=null
							&& displacementDescriptionsPlayer.containsKey(dt)
							&& displacementDescriptionsPlayer.get(dt).containsKey(DisplacementDescriptionType.DISPLACEMENT_ROUGH)) {
						return UtilText.parse(clothingOwner, displacementDescriptionsPlayer.get(dt).get(DisplacementDescriptionType.DISPLACEMENT_ROUGH));
					}
					return UtilText.parse(clothingOwner, "You roughly "+dt.getDescription()+" [npc.name]'s "+this.getName()+".");
				} else {
					if(displacementDescriptionsPlayer!=null
							&& displacementDescriptionsPlayer.containsKey(dt)
							&& displacementDescriptionsPlayer.get(dt).containsKey(DisplacementDescriptionType.DISPLACEMENT)) {
						return UtilText.parse(clothingOwner, displacementDescriptionsPlayer.get(dt).get(DisplacementDescriptionType.DISPLACEMENT));
					}
					return UtilText.parse(clothingOwner, "You "+dt.getDescription()+" [npc.name]'s "+this.getName()+".");
				}
			}
			
		} else {
			if (clothingOwner.isPlayer()) {
				if(rough) {
					if(displacementDescriptionsNPC!=null
							&& displacementDescriptionsNPC.containsKey(dt)
							&& displacementDescriptionsNPC.get(dt).containsKey(DisplacementDescriptionType.DISPLACEMENT_ROUGH)) {
						return UtilText.parse(clothingRemover, displacementDescriptionsNPC.get(dt).get(DisplacementDescriptionType.DISPLACEMENT_ROUGH));
					}
					return UtilText.parse(clothingRemover, "[npc.Name] roughly "+dt.getDescriptionThirdPerson()+" your "+this.getName()+".");
					
				} else {
					if(displacementDescriptionsNPC!=null
							&& displacementDescriptionsNPC.containsKey(dt)
							&& displacementDescriptionsNPC.get(dt).containsKey(DisplacementDescriptionType.DISPLACEMENT)) {
						return UtilText.parse(clothingRemover, displacementDescriptionsNPC.get(dt).get(DisplacementDescriptionType.DISPLACEMENT));
					}
					return UtilText.parse(clothingRemover, "[npc.Name] "+dt.getDescriptionThirdPerson()+" your "+this.getName()+".");
				}
			} else {
				if(clothingOwner.equals(clothingRemover)) {
					if(displacementDescriptionsNPC!=null
							&& displacementDescriptionsNPC.containsKey(dt)
							&& displacementDescriptionsNPC.get(dt).containsKey(DisplacementDescriptionType.DISPLACEMENT_SELF)) {
						return UtilText.parse(clothingRemover, displacementDescriptionsNPC.get(dt).get(DisplacementDescriptionType.DISPLACEMENT_SELF));
					}
					return UtilText.parse(clothingOwner, "[npc.Name] "+dt.getDescriptionThirdPerson()+" [npc.her] "+this.getName()+".");
					
				} else {
					if(rough) {
						if(displacementDescriptionsNPC!=null
								&& displacementDescriptionsNPC.containsKey(dt)
								&& displacementDescriptionsNPC.get(dt).containsKey(DisplacementDescriptionType.NPC_ON_NPC_DISPLACEMENT_ROUGH)) {
							return UtilText.parse(clothingRemover, clothingOwner,displacementDescriptionsNPC.get(dt).get(DisplacementDescriptionType.NPC_ON_NPC_DISPLACEMENT_ROUGH));
						}
						return UtilText.parse(clothingRemover, clothingOwner, "[npc.Name] roughly "+dt.getDescriptionThirdPerson()+" [npc2.name]'s "+this.getName()+".");
						
					} else {
						if(displacementDescriptionsNPC!=null
								&& displacementDescriptionsNPC.containsKey(dt)
								&& displacementDescriptionsNPC.get(dt).containsKey(DisplacementDescriptionType.NPC_ON_NPC_DISPLACEMENT)) {
							return UtilText.parse(clothingRemover, clothingOwner,displacementDescriptionsNPC.get(dt).get(DisplacementDescriptionType.NPC_ON_NPC_DISPLACEMENT));
						}
						return UtilText.parse(clothingRemover, clothingOwner, "[npc.Name] "+dt.getDescriptionThirdPerson()+" [npc2.name]'s "+this.getName()+".");
					}
				}
			}
		}
	}

	public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
		if (clothingRemover.isPlayer()) {
			if(clothingOwner.isPlayer()) {
				if(displacementDescriptionsPlayer!=null
						&& displacementDescriptionsPlayer.containsKey(dt)
						&& displacementDescriptionsPlayer.get(dt).containsKey(DisplacementDescriptionType.REPLACEMENT_SELF)) {
					return displacementDescriptionsPlayer.get(dt).get(DisplacementDescriptionType.REPLACEMENT_SELF);
				}
				return "You "+dt.getOppositeDescription()+" your "+this.getName()+"";
			} else {
				if(rough) {
					if(displacementDescriptionsPlayer!=null
							&& displacementDescriptionsPlayer.containsKey(dt)
							&& displacementDescriptionsPlayer.get(dt).containsKey(DisplacementDescriptionType.REPLACEMENT_ROUGH)) {
						return UtilText.parse(clothingOwner, displacementDescriptionsPlayer.get(dt).get(DisplacementDescriptionType.REPLACEMENT_ROUGH));
					}
					return UtilText.parse(clothingOwner, "You roughly "+dt.getOppositeDescription()+" [npc.name]'s "+this.getName()+".");
				} else {
					if(displacementDescriptionsPlayer!=null
							&& displacementDescriptionsPlayer.containsKey(dt)
							&& displacementDescriptionsPlayer.get(dt).containsKey(DisplacementDescriptionType.REPLACEMENT)) {
						return UtilText.parse(clothingOwner, displacementDescriptionsPlayer.get(dt).get(DisplacementDescriptionType.REPLACEMENT));
					}
					return UtilText.parse(clothingOwner, "You "+dt.getOppositeDescription()+" [npc.name]'s "+this.getName()+".");
				}
			}
			
		} else {
			if (clothingOwner.isPlayer()) {
				if(rough) {
					if(displacementDescriptionsNPC!=null
							&& displacementDescriptionsNPC.containsKey(dt)
							&& displacementDescriptionsNPC.get(dt).containsKey(DisplacementDescriptionType.REPLACEMENT_ROUGH)) {
						return UtilText.parse(clothingRemover, displacementDescriptionsNPC.get(dt).get(DisplacementDescriptionType.REPLACEMENT_ROUGH));
					}
					return UtilText.parse(clothingRemover, "[npc.Name] roughly "+dt.getOppositeDescriptionThirdPerson()+" your "+this.getName()+".");
					
				} else {
					if(displacementDescriptionsNPC!=null
							&& displacementDescriptionsNPC.containsKey(dt)
							&& displacementDescriptionsNPC.get(dt).containsKey(DisplacementDescriptionType.REPLACEMENT)) {
						return UtilText.parse(clothingRemover, displacementDescriptionsNPC.get(dt).get(DisplacementDescriptionType.REPLACEMENT));
					}
					return UtilText.parse(clothingRemover, "[npc.Name] "+dt.getOppositeDescriptionThirdPerson()+" your "+this.getName()+".");
				}
			} else {
				if(clothingOwner.equals(clothingRemover)) {
					if(displacementDescriptionsNPC!=null
							&& displacementDescriptionsNPC.containsKey(dt)
							&& displacementDescriptionsNPC.get(dt).containsKey(DisplacementDescriptionType.REPLACEMENT_SELF)) {
						return UtilText.parse(clothingRemover, displacementDescriptionsNPC.get(dt).get(DisplacementDescriptionType.REPLACEMENT_SELF));
					}
					return UtilText.parse(clothingOwner, "[npc.Name] "+dt.getOppositeDescriptionThirdPerson()+" [npc.her] "+this.getName()+".");
					
				} else {
					if(rough) {
						if(displacementDescriptionsNPC!=null
								&& displacementDescriptionsNPC.containsKey(dt)
								&& displacementDescriptionsNPC.get(dt).containsKey(DisplacementDescriptionType.NPC_ON_NPC_REPLACEMENT_ROUGH)) {
							return UtilText.parse(clothingRemover, clothingOwner,displacementDescriptionsNPC.get(dt).get(DisplacementDescriptionType.NPC_ON_NPC_REPLACEMENT_ROUGH));
						}
						return UtilText.parse(clothingRemover, clothingOwner, "[npc.Name] roughly "+dt.getOppositeDescriptionThirdPerson()+" [npc2.name]'s "+this.getName()+".");
						
					} else {
						if(displacementDescriptionsNPC!=null
								&& displacementDescriptionsNPC.containsKey(dt)
								&& displacementDescriptionsNPC.get(dt).containsKey(DisplacementDescriptionType.NPC_ON_NPC_REPLACEMENT)) {
							return UtilText.parse(clothingRemover, clothingOwner,displacementDescriptionsNPC.get(dt).get(DisplacementDescriptionType.NPC_ON_NPC_REPLACEMENT));
						}
						return UtilText.parse(clothingRemover, clothingOwner, "[npc.Name] "+dt.getOppositeDescriptionThirdPerson()+" [npc2.name]'s "+this.getName()+".");
					}
				}
			}
		}
	}
	
	public boolean isCanBeEquipped(GameCharacter clothingOwner) {
		// This might not be the most efficient way of making this method, but I found it to be easily-readable:
		if(clothingOwner.hasPenisIgnoreDildo() && this.getItemTags().contains(ItemTag.REQUIRES_NO_PENIS)) {
			return false;
		}
		if(!clothingOwner.hasPenisIgnoreDildo() && this.getItemTags().contains(ItemTag.REQUIRES_PENIS)) {
			return false;
		}
		if(clothingOwner.hasVagina() && this.getItemTags().contains(ItemTag.REQUIRES_NO_VAGINA)) {
			return false;
		}
		if(!clothingOwner.hasVagina() && this.getItemTags().contains(ItemTag.REQUIRES_VAGINA)) {
			return false;
		}
		return true;
	}	

	public String getCannotBeEquippedText(GameCharacter clothingOwner) {
		if(clothingOwner.hasPenisIgnoreDildo() && this.getItemTags().contains(ItemTag.REQUIRES_NO_PENIS)) {
			if(clothingOwner.isPlayer()) {
				return "You have a penis, which is blocking you from wearing the "+this.getName()+"!";
			} else {
				return UtilText.parse(clothingOwner,
						"[npc.Name] has a penis, which is blocking [npc.herHim] from wearing the "+this.getName()+"!");
			}
		}
		
		if(!clothingOwner.hasPenisIgnoreDildo() && this.getItemTags().contains(ItemTag.REQUIRES_PENIS)) {
			if(clothingOwner.isPlayer()) {
				return "You don't have a penis, so you can't wear the "+this.getName()+"!";
			} else {
				return UtilText.parse(clothingOwner,
						"[npc.Name] doesn't have a penis, so [npc.she] can't wear the "+this.getName()+"!");
			}
		}
		
		if(clothingOwner.hasVagina() && this.getItemTags().contains(ItemTag.REQUIRES_NO_VAGINA)) {
			if(clothingOwner.isPlayer()) {
				return "You have a vagina, which is blocking you from wearing the "+this.getName()+"!";
			} else {
				return UtilText.parse(clothingOwner,
						"[npc.Name] has a vagina, which is blocking [npc.herHim] from wearing the "+this.getName()+"!");
			}
		}
		
		if(!clothingOwner.hasVagina() && this.getItemTags().contains(ItemTag.REQUIRES_VAGINA)) {
			if(clothingOwner.isPlayer()) {
				return "You don't have a vagina, so you can't wear the "+this.getName()+"!";
			} else {
				return UtilText.parse(clothingOwner,
						"[npc.Name] doesn't have a vagina, so [npc.she] can't wear the "+this.getName()+"!");
			}
		}
		
		if(clothingOwner.isPlayer()) {
			return "You cannot wear the "+this.getName()+"!";
		} else {
			return UtilText.parse(clothingOwner,
					"[npc.Name] cannot wear the "+this.getName()+"!");
		}
	}
	
	public boolean isMufflesSpeech() {
		return false;
	}
	
	public boolean isHindersLegMovement() {
		return false;
	}
	
	public boolean isHindersArmMovement() {
		return false;
	}
	
	// Getters & setters:
	
	public boolean isDiscardedOnUnequip() {
		return false;
	}
	
	public boolean isAbleToBeEquippedDuringSex() {
		return false;
	}
	
	public String getDeterminer() {
		return determiner;
	}

	public Boolean isPlural() {
		return plural;
	}

	public String getName() {
		if(isPlural()) {
			return namePlural;
		}
		return name;
	}

	public String getNamePlural() {
		return namePlural;
	}

	public String getDescription() {
		return description;
	}

	public int getFemininityMinimum() {
		return femininityMinimum;
	}

	public int getFemininityMaximum() {
		return femininityMaximum;
	}

	public Femininity getFemininityRestriction() {
		return femininityRestriction;
	}

	public InventorySlot getSlot() {
		return slot;
	}

	public List<BlockedParts> getBlockedPartsList() {
		return blockedPartsList;
	}

	public List<InventorySlot> getIncompatibleSlots() {
		return incompatibleSlots;
	}
	
	public List<DisplacementType> getBlockedPartsKeysAsListWithoutNONE() {
		return displacementTypesAvailableWithoutNONE;
	}
	
	public String getPathName() {
		return pathName;
	}

	public String getPathNameEquipped() {
		return pathNameEquipped;
	}

	public int getzLayer() {
		return slot.getZLayer();
	}

	public ClothingSet getClothingSet() {
		return clothingSet;
	}

	public List<Colour> getAvailablePrimaryColours() {
		return availablePrimaryColours;
	}
	
	public List<Colour> getAvailablePrimaryDyeColours() {
		return availablePrimaryDyeColours;
	}
	
	public List<Colour> getAllAvailablePrimaryColours() {
		return allAvailablePrimaryColours;
	}

	public List<Colour> getAvailableSecondaryColours() {
		return availableSecondaryColours;
	}

	public List<Colour> getAvailableSecondaryDyeColours() {
		return availableSecondaryDyeColours;
	}
	
	public List<Colour> getAllAvailableSecondaryColours() {
		return allAvailableSecondaryColours;
	}
	
	public List<Colour> getAvailableTertiaryColours() {
		return availableTertiaryColours;
	}

	public List<Colour> getAvailableTertiaryDyeColours() {
		return availableTertiaryDyeColours;
	}
	
	public List<Colour> getAllAvailableTertiaryColours() {
		return allAvailableTertiaryColours;
	}
	
//	public Map<Colour, String> getSVGStringMap() {
//		return SVGStringMap;
//	}
//	
//	public Map<Colour, String> getSVGStringEquippedMap() {
//		return SVGStringEquippedMap;
//	}
	
	private void addSVGStringMapping(Colour colour, Colour colourSecondary, Colour colourTertiary, String s) {
		if(SVGStringMap.get(colour)==null) {
			SVGStringMap.put(colour, new HashMap<>());
			SVGStringMap.get(colour).put(colourSecondary, new HashMap<>());
			
		} else if(SVGStringMap.get(colour).get(colourSecondary)==null) {
			SVGStringMap.get(colour).put(colourSecondary, new HashMap<>());
		}
		
		SVGStringMap.get(colour).get(colourSecondary).put(colourTertiary, s);
	}
	
	private void addSVGStringEquippedMapping(Colour colour, Colour colourSecondary, Colour colourTertiary, String s) {
		if(SVGStringEquippedMap.get(colour)==null) {
			SVGStringEquippedMap.put(colour, new HashMap<>());
			SVGStringEquippedMap.get(colour).put(colourSecondary, new HashMap<>());
			
		} else if(SVGStringEquippedMap.get(colour).get(colourSecondary)==null) {
			SVGStringEquippedMap.get(colour).put(colourSecondary, new HashMap<>());
		}
		
		SVGStringEquippedMap.get(colour).get(colourSecondary).put(colourTertiary, s);
	}
	
	
	private String getSVGStringFromMap(Colour colour, Colour colourSecondary, Colour colourTertiary) {
		if(SVGStringMap.get(colour)==null) {
			return null;
		} else {
			if(SVGStringMap.get(colour).get(colourSecondary)==null) {
				return null;
			} else {
				return SVGStringMap.get(colour).get(colourSecondary).get(colourTertiary);
			}
		}
	}
	
	private String getSVGStringFromEquippedMap(Colour colour, Colour colourSecondary, Colour colourTertiary) {
		if(SVGStringEquippedMap.get(colour)==null) {
			return null;
		} else {
			if(SVGStringEquippedMap.get(colour).get(colourSecondary)==null) {
				return null;
			} else {
				return SVGStringEquippedMap.get(colour).get(colourSecondary).get(colourTertiary);
			}
		}
	}

	public String getSVGImage() {
		Colour pColour = Colour.CLOTHING_BLACK;
		if(this.getAllAvailablePrimaryColours()!=null && !this.getAllAvailablePrimaryColours().isEmpty()) {
			pColour = this.getAllAvailablePrimaryColours().get(0);
		}
		Colour sColour = Colour.CLOTHING_BLACK;
		if(this.getAllAvailableSecondaryColours()!=null && !this.getAllAvailableSecondaryColours().isEmpty()) {
			sColour = this.getAllAvailableSecondaryColours().get(0);
		}
		Colour tColour = Colour.CLOTHING_BLACK;
		if(this.getAllAvailableTertiaryColours()!=null && !this.getAllAvailableTertiaryColours().isEmpty()) {
			tColour = this.getAllAvailableTertiaryColours().get(0);
		}
		
		return getSVGImage(null, pColour, sColour, tColour, false);
	}
	
	/**
	 * @param colour You need to pass a colour in here.
	 * @param colourSecondary This can be null.
	 * @param colourTertiary This can be null.
	 */
	public String getSVGImage(Colour colour, Colour colourSecondary, Colour colourTertiary) {
		return getSVGImage(null, colour, colourSecondary, colourTertiary, false);
	}
	
	/**
	 * @param character The character this clothing is equipped to.
	 * @param colour You need to pass a colour in here.
	 * @param colourSecondary This can be null.
	 * @param colourTertiary This can be null.
	 */
	public String getSVGEquippedImage(GameCharacter character, Colour colour, Colour colourSecondary, Colour colourTertiary) {
		return getSVGImage(character, colour, colourSecondary, colourTertiary, true);
	}
	
	private String getSVGImage(GameCharacter character, Colour colour, Colour colourSecondary, Colour colourTertiary, boolean equippedVariant) {
		if (!allAvailablePrimaryColours.contains(colour)) {
			return "";
		}
		
		if(this.equals(ClothingType.HIPS_CONDOMS)) {
			if (getAllAvailablePrimaryColours().contains(colour)) {
				try {
					InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/belt_used_condoms_base_back.svg");
					String s = "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;padding:0;margin:0'>"+Util.inputStreamToString(is)+"</div>";
					is.close();
					s = Util.colourReplacement(this.getId(), colour, colourSecondary, colourTertiary, s);

					if(!equippedVariant) {
						is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/belt_used_condoms_base_front.svg");
						s += "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;padding:0;margin:0'>" + Util.inputStreamToString(is) + "</div>";
						s = Util.colourReplacement(this.getId(), colour, colourSecondary, colourTertiary, s);
						is.close();
						
						addSVGStringEquippedMapping(colour, colourSecondary, colourTertiary, s);
						
						return s;
						
					} else {
						List<Colour> condomColours = new ArrayList<>();
						// Draw all backs:
						for(AbstractItem item : character.getAllItemsInInventory()) {
							if(item.getItemType().equals(ItemType.CONDOM_USED)) {
								if(condomColours.size()<8) {
									condomColours.add(item.getColour());
									
									is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/belt_used_condoms_"+condomColours.size()+"_back.svg");
									s += "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;padding:0;margin:0'>" + Util.inputStreamToString(is) + "</div>";
									s = Util.colourReplacement(this.getId(), item.getColour(), null, null, s);
									is.close();
								}
							}
						}
						
						is.close();
						is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/belt_used_condoms_base_front.svg");
						s += "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;padding:0;margin:0'>" + Util.inputStreamToString(is) + "</div>";
						s = Util.colourReplacement(this.getId(), colour, colourSecondary, colourTertiary, s);
						is.close();
						
						int i = 1;
						for(Colour c : condomColours) {
							is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/belt_used_condoms_"+i+"_front.svg");
							s += "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;padding:0;margin:0'>" + Util.inputStreamToString(is) + "</div>";
							s = Util.colourReplacement(this.getId(), c, null, null, s);
							is.close();
							i++;
						}
						
						return s;
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		} else {
			if(equippedVariant && pathNameEquipped!=null) {
				String stringFromMap = getSVGStringFromEquippedMap(colour, colourSecondary, colourTertiary);
				if (stringFromMap!=null && !this.equals(ClothingType.WRIST_WOMENS_WATCH) && !this.equals(ClothingType.WRIST_MENS_WATCH)) {
					return stringFromMap;
					
				} else {
					if (getAllAvailablePrimaryColours().contains(colour)) {
						try {
							
							InputStream is;
							String s;
							if(isMod) {
								List<String> lines = Files.readAllLines(Paths.get(pathNameEquipped));
								StringBuilder sb = new StringBuilder();
								for(String line : lines) {
									sb.append(line);
								}
								s = sb.toString();
							} else {
								is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/" + pathNameEquipped + ".svg");
								s = Util.inputStreamToString(is);
								is.close();
							}
							
							s = Util.colourReplacement(this.getId(), colour, colourSecondary, colourTertiary, s);
							
							// Add minute and hour hands to women's and men's watches:
							s += (this.equals(ClothingType.WRIST_WOMENS_WATCH)
									? "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;-webkit-transform: rotate(" + ((Main.game.getMinutesPassed() % (60 * 24)) / 2f) + "deg);'>"
										+ SVGImages.SVG_IMAGE_PROVIDER.getWomensWatchHourHand() + "</div>"
										+ "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;-webkit-transform: rotate(" + (Main.game.getMinutesPassed() % (60)) * 6 + "deg);'>"
										+ SVGImages.SVG_IMAGE_PROVIDER.getWomensWatchMinuteHand() + "</div>"
									: "")
									+ (this.equals(ClothingType.WRIST_MENS_WATCH)
										? "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;-webkit-transform: rotate(" + ((Main.game.getMinutesPassed() % (60 * 24)) / 2f) + "deg);'>"
											+ SVGImages.SVG_IMAGE_PROVIDER.getMensWatchHourHand() + "</div>"
											+ "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;-webkit-transform: rotate(" + (Main.game.getMinutesPassed() % (60)) * 6
											+ "deg);'>" + SVGImages.SVG_IMAGE_PROVIDER.getMensWatchMinuteHand() + "</div>"
										: "");

							addSVGStringEquippedMapping(colour, colourSecondary, colourTertiary, s);
							
							return s;
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				
			} else {
				String stringFromMap = getSVGStringFromMap(colour, colourSecondary, colourTertiary);
				if (stringFromMap!=null && !this.equals(ClothingType.WRIST_WOMENS_WATCH) && !this.equals(ClothingType.WRIST_MENS_WATCH)) {
					return stringFromMap;
					
				} else {
					if (getAllAvailablePrimaryColours().contains(colour)) {
						try {
							
							InputStream is;
							String s;
							if(isMod) {
								List<String> lines = Files.readAllLines(Paths.get(pathName));
								StringBuilder sb = new StringBuilder();
								for(String line : lines) {
									sb.append(line);
								}
								s = sb.toString();
							} else {
								is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/" + pathName + ".svg");
								s = Util.inputStreamToString(is);
								is.close();
							}
							
							s = Util.colourReplacement(this.getId(), colour, colourSecondary, colourTertiary, s);
							
							// Add minute and hour hands to women's and men's watches:
							s += (this.equals(ClothingType.WRIST_WOMENS_WATCH)
									? "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;-webkit-transform: rotate(" + ((Main.game.getMinutesPassed() % (60 * 24)) / 2f) + "deg);'>"
										+ SVGImages.SVG_IMAGE_PROVIDER.getWomensWatchHourHand() + "</div>"
										+ "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;-webkit-transform: rotate(" + (Main.game.getMinutesPassed() % (60)) * 6 + "deg);'>"
										+ SVGImages.SVG_IMAGE_PROVIDER.getWomensWatchMinuteHand() + "</div>"
									: "")
									+ (this.equals(ClothingType.WRIST_MENS_WATCH)
										? "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;-webkit-transform: rotate(" + ((Main.game.getMinutesPassed() % (60 * 24)) / 2f) + "deg);'>"
											+ SVGImages.SVG_IMAGE_PROVIDER.getMensWatchHourHand() + "</div>"
											+ "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;-webkit-transform: rotate(" + (Main.game.getMinutesPassed() % (60)) * 6
											+ "deg);'>" + SVGImages.SVG_IMAGE_PROVIDER.getMensWatchMinuteHand() + "</div>"
										: "");
							
							addSVGStringMapping(colour, colourSecondary, colourTertiary, s);
							
		
							return s;
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return "";
	}

	public Rarity getRarity() {
		return rarity;
	}

	public List<ItemEffect> getEffects() {
		return effects;
	}

	public int getPhysicalResistance() {
		return physicalResistance;
	}

	public void setPhysicalResistance(int physicalResistance) {
		this.physicalResistance = physicalResistance;
	}
	

	// Enchantments:
	
	public int getEnchantmentLimit() {
		if(enchantmentLimit==-1) {
			int base = (getClothingSet()==null?5:10);
			return base + getIncompatibleSlots().size()*base;
		} else {
			return enchantmentLimit;
		}
	}
	
	public AbstractItemEffectType getEnchantmentEffect() {
		return ItemEffectType.CLOTHING;
	}
	
	public TFEssence getRelatedEssence() {
		return TFEssence.ARCANE;
	}
	
	public AbstractClothingType getEnchantmentItemType(List<ItemEffect> effects) {
		return this;
	}

	public List<ItemTag> getItemTags() {
		return itemTags;
	}
}
