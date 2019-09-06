package com.lilithsthrone.game.inventory.clothing;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.controller.xmlParsing.XMLLoadException;
import com.lilithsthrone.controller.xmlParsing.XMLMissingTagException;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.FootType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
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
import com.lilithsthrone.rendering.Pattern;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.ColourListPresets;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.84
 * @version 0.3.4
 * @author Innoxia, BlazingMagpie@gmail.com (or ping BlazingMagpie in Discord), Pimgd
 */
public abstract class AbstractClothingType extends AbstractCoreType {
	
	public static final Colour DEFAULT_COLOUR_VALUE = Colour.CLOTHING_BLACK;
	
	private String determiner;
	private String name;
	private String namePlural;
	private String description;
	private String pathName;
	private Map<InventorySlot, String> pathNameEquipped;
	private String authorDescription;
	
	private boolean plural;
	private boolean isMod;
	private boolean isColourDerivedFromPattern;
	private int baseValue;
	private float physicalResistance;
	private int femininityMinimum;
	private int femininityMaximum;
	private Femininity femininityRestriction;
	private List<InventorySlot> equipSlots;

	// Enchantments:
	@SuppressWarnings("unused")
	private int enchantmentLimit; // Removed as part of 0.3.3.7's update to add enchantment capacity mechanics.
	protected List<ItemEffect> effects;

	
	// Images:
	private Map<InventorySlot, Map<Colour, Map<Colour, Map<Colour, Map<String, Map<Colour, Map<Colour, Map<Colour, String>>>>>>>> SVGStringMap;
	private Map<InventorySlot, Map<Colour, Map<Colour, Map<Colour, Map<String, Map<Colour, Map<Colour, Map<Colour, String>>>>>>>> SVGStringEquippedMap;
	
	// Pattern data:
	private boolean isPatternAvailable;
	private boolean isPatternAvailableInitCompleted;
	
	// Access and block stuff:
	private Map<InventorySlot, List<BlockedParts>> blockedPartsMap;
	private Map<InventorySlot, List<InventorySlot>> incompatibleSlotsMap;

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

	// Patterns:
	private float patternChance;
	private List<Pattern> defaultPatterns;
	
	private List<Colour> availablePatternPrimaryColours;
	private List<Colour> availablePatternPrimaryDyeColours;
	private List<Colour> allAvailablePatternPrimaryColours;
	
	private List<Colour> availablePatternSecondaryColours;
	private List<Colour> availablePatternSecondaryDyeColours;
	private List<Colour> allAvailablePatternSecondaryColours;
	
	private List<Colour> availablePatternTertiaryColours;
	private List<Colour> availablePatternTertiaryDyeColours;
	private List<Colour> allAvailablePatternTertiaryColours;

	// Other:
	private Map<InventorySlot, List<DisplacementType>> displacementTypesAvailableWithoutNONE;
	
	private Map<InventorySlot, List<ItemTag>> itemTags;

	@Deprecated
	private Map<InventorySlot, Map<DisplacementType, Map<DisplacementDescriptionType, String>>> displacementDescriptionsPlayer;
	@Deprecated
	private Map<InventorySlot, Map<DisplacementType, Map<DisplacementDescriptionType, String>>> displacementDescriptionsNPC;
	
	private Map<InventorySlot, Map<DisplacementType, Map<DisplacementDescriptionType, String>>> displacementDescriptions;

//	@Deprecated
	protected AbstractClothingType(
			int baseValue,
			String determiner,
			boolean plural,
			String name,
			String namePlural,
			String description,
			float physicalResistance,
			Femininity femininityRestriction,
			InventorySlot equipSlot,
			Rarity rarity,
			ClothingSet clothingSet,
			String pathName,
			List<ItemEffect> effects,
			List<BlockedParts> blockedPartsList,
			List<InventorySlot> incompatibleSlotsList,
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
				Util.newArrayListOfValues(equipSlot),
				rarity,
				clothingSet,
				pathName,
				null,
				effects,
				Util.newHashMapOfValues(new Value<>(equipSlot, blockedPartsList==null?new ArrayList<>():blockedPartsList)),
				Util.newHashMapOfValues(new Value<>(equipSlot, incompatibleSlotsList==null?new ArrayList<>():incompatibleSlotsList)),
				availablePrimaryColours,
				availablePrimaryDyeColours,
				availableSecondaryColours,
				availableSecondaryDyeColours,
				availableTertiaryColours,
				availableTertiaryDyeColours,
				Util.newHashMapOfValues(new Value<>(equipSlot, itemTags==null?new ArrayList<>():itemTags)));
	}
	
//	protected AbstractClothingType(
//			int baseValue,
//			String determiner,
//			boolean plural,
//			String name,
//			String namePlural,
//			String description,
//			float physicalResistance,
//			Femininity femininityRestriction,
//			List<InventorySlot> equipSlots,
//			Rarity rarity,
//			ClothingSet clothingSet,
//			String pathName,
//			List<ItemEffect> effects,
//			Map<InventorySlot, List<BlockedParts>> blockedPartsMap,
//			Map<InventorySlot, List<InventorySlot>> incompatibleSlotsMap,
//			List<Colour> availablePrimaryColours,
//			List<Colour> availablePrimaryDyeColours,
//			List<Colour> availableSecondaryColours,
//			List<Colour> availableSecondaryDyeColours,
//			List<Colour> availableTertiaryColours,
//			List<Colour> availableTertiaryDyeColours,
//			Map<InventorySlot, List<ItemTag>> itemTags) {
//		this(baseValue,
//				determiner,
//				plural,
//				name,
//				namePlural,
//				description,
//				physicalResistance,
//				femininityRestriction,
//				equipSlots,
//				rarity,
//				clothingSet,
//				pathName,
//				null,
//				effects,
//				blockedPartsMap,
//				incompatibleSlotsMap,
//				availablePrimaryColours,
//				availablePrimaryDyeColours,
//				availableSecondaryColours,
//				availableSecondaryDyeColours,
//				availableTertiaryColours,
//				availableTertiaryDyeColours,
//				itemTags);
//	}
	
	public AbstractClothingType(
			int baseValue,
			String determiner,
			boolean plural,
			String name,
			String namePlural,
			String description,
			float physicalResistance,
			Femininity femininityRestriction,
			List<InventorySlot> equipSlots,
			Rarity rarity,
			ClothingSet clothingSet,
			String pathName,
			Map<InventorySlot, String> pathNameEquipped,
			List<ItemEffect> effects,
			Map<InventorySlot, List<BlockedParts>> blockedPartsMap,
			Map<InventorySlot, List<InventorySlot>> incompatibleSlotsMap,
			List<Colour> availablePrimaryColours,
			List<Colour> availablePrimaryDyeColours,
			List<Colour> availableSecondaryColours,
			List<Colour> availableSecondaryDyeColours,
			List<Colour> availableTertiaryColours,
			List<Colour> availableTertiaryDyeColours,
			Map<InventorySlot, List<ItemTag>> itemTags) {
		
		this.baseValue = baseValue;
		
		this.isMod = false;
		isColourDerivedFromPattern = false;
		
		this.determiner = determiner;
		this.plural = plural;
		this.name = name;
		this.namePlural = namePlural;
		this.description = description;

		this.physicalResistance = physicalResistance;
		
		setUpFemininity(femininityRestriction);
		
		this.femininityRestriction = femininityRestriction;

		this.equipSlots = equipSlots;
		this.rarity = rarity;
		this.clothingSet = clothingSet;

		this.pathName = pathName;
		this.pathNameEquipped = pathNameEquipped;
		
		this.isPatternAvailable = false;
		this.isPatternAvailableInitCompleted = false;

		enchantmentLimit = -1;
		
		// Attribute modifiers:
		if (effects != null) {
			this.effects = new ArrayList<>(effects);
		} else {
			this.effects = new ArrayList<>();
		}
		
		// Blocked Parts:
		if (blockedPartsMap != null) {
			this.blockedPartsMap = blockedPartsMap;
		} else {
			this.blockedPartsMap = new HashMap<>();
			for(InventorySlot slot : equipSlots) {
				this.blockedPartsMap.put(slot, new ArrayList<>());
			}
		}
		
		// Make sure that clothing can't conceal itself (in case I made an error in defining concealed slots):
		for(Entry<InventorySlot, List<BlockedParts>> entry : this.blockedPartsMap.entrySet()) {
			for(BlockedParts bp : entry.getValue()) {
				bp.concealedSlots.removeIf((concealedSlot) -> concealedSlot == entry.getKey());
			}
		}
		
		// Incompatible slots:
		if (incompatibleSlotsMap != null) {
			this.incompatibleSlotsMap = incompatibleSlotsMap;
		} else {
			this.incompatibleSlotsMap = new HashMap<>();
			for(InventorySlot slot : equipSlots) {
				this.incompatibleSlotsMap.put(slot, new ArrayList<>());
			}
		}
		
		setUpColours(availablePrimaryColours,
				availablePrimaryDyeColours,
				availableSecondaryColours,
				availableSecondaryDyeColours,
				availableTertiaryColours,
				availableTertiaryDyeColours);
		
		patternChance = 0;
		defaultPatterns = new ArrayList<>(Pattern.getAllDefaultPatterns().values());
		setUpPatternColours(null, null, null, null, null, null);

		this.itemTags = new HashMap<>();
		if(itemTags!=null) {
			this.itemTags = itemTags;
		} else {
			for(InventorySlot slot : this.equipSlots) {
				this.itemTags.put(slot, new ArrayList<>());
			}
		}
		
		this.authorDescription = ""; // Do not give attribution to Innoxia's items.

		finalSetUp();
	}
	

	@SuppressWarnings("deprecation")
	public AbstractClothingType(File clothingXMLFile, String author) throws XMLLoadException { // Be sure to catch this exception correctly - if it's thrown mod is invalid and should not be continued to load
		boolean debug = false;//clothingXMLFile.getName().contains("distressed");
		
		try{
			Element clothingElement = Element.getDocumentRootElement(clothingXMLFile); // Loads the document and returns the root element - in clothing mods it's <clothing>
			Element coreAttributes = null;
			try {
				coreAttributes = clothingElement.getMandatoryFirstOf("coreAtributes");
			} catch (XMLMissingTagException ex) {
				coreAttributes = clothingElement.getMandatoryFirstOf("coreAttributes");
			}

			if(debug) {
				System.out.println("1");
			}
			
			this.isMod = true;
			
			this.name =        coreAttributes.getMandatoryFirstOf("name").getTextContent();
			this.namePlural =  coreAttributes.getMandatoryFirstOf("namePlural").getTextContent();
			this.description = coreAttributes.getMandatoryFirstOf("description").getTextContent();
			this.determiner =  coreAttributes.getMandatoryFirstOf("determiner").getTextContent();
			
			if(debug) {
				System.out.println("2");
			}
			
			this.plural =             Boolean.valueOf(coreAttributes.getMandatoryFirstOf("namePlural").getAttribute("pluralByDefault"));
			this.baseValue =          Integer.valueOf(coreAttributes.getMandatoryFirstOf("value").getTextContent());
			this.physicalResistance = Float.valueOf(coreAttributes.getMandatoryFirstOf("physicalResistance").getTextContent());	
			this.rarity =             Rarity.valueOf(coreAttributes.getMandatoryFirstOf("rarity").getTextContent());
			
			try {
				this.equipSlots = coreAttributes
						.getMandatoryFirstOf("equipSlots")
						.getAllOf("slot").stream()
						.map( e -> InventorySlot.valueOf(e.getTextContent()))
						.collect(Collectors.toList());
				
			} catch(Exception ex) { // Old version single slot support:
				InventorySlot slot = InventorySlot.valueOf(coreAttributes.getMandatoryFirstOf("slot").getTextContent());
				this.equipSlots = Util.newArrayListOfValues(slot);
			}

			if(coreAttributes.getOptionalFirstOf("clothingAuthorTag").isPresent()) {
				this.authorDescription = coreAttributes.getMandatoryFirstOf("clothingAuthorTag").getTextContent();
			} else if(coreAttributes.getOptionalFirstOf("authorTag").isPresent()) {
				this.authorDescription = coreAttributes.getMandatoryFirstOf("authorTag").getTextContent();
			} else if(!author.equalsIgnoreCase("innoxia")){
				this.authorDescription = "A tag discreetly attached to the "+(plural?namePlural:name)+" informs you that "+(plural?"they were":"it was")+" made by a certain '"+Util.capitaliseSentence(author)+"'.";
			} else {
				this.authorDescription = "";
			}
			
			this.femininityRestriction = Femininity.valueOf(coreAttributes.getMandatoryFirstOf("femininity").getTextContent());
			setUpFemininity(this.femininityRestriction);

			if(debug) {
				System.out.println("3");
			}
			
			this.effects = coreAttributes
				.getMandatoryFirstOf("effects") 
				.getAllOf("effect") // Get all child elements with this tag (checking only contents of parent element) and return them as List<Element>
				.stream() // Convert this list to Stream<Element>, which lets us do some nifty operations on every element at once
				.map( e -> ItemEffect.loadFromXML(e.getInnerElement(), e.getDocument())) // Take every element and do something with them, return a Stream of results after this action. Here we load item effects and get Stream<ItemEffect>
				.filter(Objects::nonNull) // Ensure that we only add non-null effects
				.collect(Collectors.toList()); // Collect stream back into a list, but this time we get List<ItemEffect> we need! 
			
			
			try {
				this.blockedPartsMap = new HashMap<>();
				
				for(Element blockedPartsElement : coreAttributes.getAllOf("blockedPartsList")) {
					InventorySlot relatedSlot = InventorySlot.valueOf(blockedPartsElement.getAttribute("slot"));
					List<BlockedParts> blockedPartsList = blockedPartsElement
							.getAllOf("blockedParts").stream()
							.map( e -> BlockedParts.loadFromXML(e.getInnerElement(), e.getDocument(), clothingXMLFile.getAbsolutePath()))
							.collect(Collectors.toList());
					
					this.blockedPartsMap.put(relatedSlot, blockedPartsList);
				}
				
			} catch(Exception ex) { // Old version single slot support:
				List<BlockedParts> blockedPartsList = coreAttributes
						.getMandatoryFirstOf("blockedPartsList")
						.getAllOf("blockedParts").stream()
						.map( e -> BlockedParts.loadFromXML(e.getInnerElement(), e.getDocument(), clothingXMLFile.getAbsolutePath()))
						.collect(Collectors.toList());
					
					this.blockedPartsMap = Util.newHashMapOfValues(new Value<>(this.getEquipSlots().get(0), blockedPartsList));
			}

			try {
				this.incompatibleSlotsMap = new HashMap<>();
				for(Element incompatibleSlotsElement : coreAttributes.getAllOf("incompatibleSlots")) {
					InventorySlot relatedSlot = InventorySlot.valueOf(incompatibleSlotsElement.getAttribute("slot"));
					List<InventorySlot> incompatibleSlotsList = incompatibleSlotsElement
							.getAllOf("slot").stream()
							.map(Element::getTextContent).map(InventorySlot::valueOf)
							.collect(Collectors.toList());
					
					this.incompatibleSlotsMap.put(relatedSlot, incompatibleSlotsList);
				}
			
			} catch(Exception ex) { // Old version single slot support:
				List<InventorySlot> incompatibleSlotsList = coreAttributes
						.getMandatoryFirstOf("incompatibleSlots")
						.getAllOf("slot").stream()
						.map(Element::getTextContent).map(InventorySlot::valueOf)
						.collect(Collectors.toList());

					this.incompatibleSlotsMap = Util.newHashMapOfValues(new Value<>(this.getEquipSlots().get(0), incompatibleSlotsList));
			}
			

			this.itemTags = new HashMap<>();
			for(Element itemTagsElement : coreAttributes.getAllOf("itemTags")) { //TODO check
				if(itemTagsElement.getAttribute("slot").isEmpty()) {
					for(InventorySlot slot : this.equipSlots) {
						this.itemTags.putIfAbsent(slot, new ArrayList<>());
						this.itemTags.get(slot).addAll(
								itemTagsElement.getAllOf("tag").stream()
									.map(Element::getTextContent).map(ItemTag::valueOf)
									.collect(Collectors.toList()));
						
					}
					
				} else {
					InventorySlot relatedSlot = InventorySlot.valueOf(itemTagsElement.getAttribute("slot"));
					this.itemTags.put(
							relatedSlot,
							itemTagsElement.getAllOf("tag").stream()
								.map(Element::getTextContent).map(ItemTag::valueOf)
								.collect(Collectors.toList()));
				}
			}

			if(debug) {
				System.out.println("4");
			}
			
			this.displacementDescriptionsPlayer = new HashMap<>();
			this.displacementDescriptionsNPC = new HashMap<>();
			this.displacementDescriptions = new HashMap<>();

			Consumer<String> loadItemMoveDescriptions = repositionDescListTag -> {
				clothingElement.getAllOf(repositionDescListTag).stream() // <clothing>, not <coreAttributes>!
				.forEach( descriptionList ->{
					DisplacementType type = DisplacementType.valueOf(descriptionList.getAttribute("type"));
					InventorySlot slot = null;
					try {
						slot = InventorySlot.valueOf(descriptionList.getAttribute("slot"));
					} catch(Exception ex) {
						slot = this.getEquipSlots().get(0);
					}

					displacementDescriptionsPlayer.putIfAbsent(slot, new HashMap<>());
					displacementDescriptionsNPC.putIfAbsent(slot, new HashMap<>());
					displacementDescriptions.putIfAbsent(slot, new HashMap<>());
					
					displacementDescriptionsPlayer.get(slot).putIfAbsent(type, new HashMap<>());
					displacementDescriptionsNPC.get(slot).putIfAbsent(type, new HashMap<>());
					displacementDescriptions.get(slot).putIfAbsent(type, new HashMap<>());
					
					Map<DisplacementDescriptionType, String> playerMap = displacementDescriptionsPlayer.get(slot).get(type);
					Map<DisplacementDescriptionType, String> npcMap = displacementDescriptionsNPC.get(slot).get(type);
					Map<DisplacementDescriptionType, String> universalMap = displacementDescriptions.get(slot).get(type);

					Function<Map<DisplacementDescriptionType, String>, Consumer<String>> putTagContentTo = mapToPutIn -> tagName ->{ // try to get element by tag, if it exists, put in specified map
					descriptionList.getOptionalFirstOf(tagName)
						.ifPresent(desc -> 
						mapToPutIn.put(DisplacementDescriptionType.byTagsPath(repositionDescListTag+" "+desc.getTagName()), desc.getTextContent()));
					};
					Consumer<String> toPlayerMap = putTagContentTo.apply(playerMap);
					Consumer<String> toNPCMap = putTagContentTo.apply(npcMap);
					Consumer<String> toUniversalMap = putTagContentTo.apply(universalMap);
					//TODO this is messing up
					toPlayerMap.accept("playerNPC");
					toPlayerMap.accept("playerNPCRough");
					toPlayerMap.accept("playerSelf");

					toNPCMap.accept("NPCPlayer");
					toNPCMap.accept("NPCPlayerRough");
					toNPCMap.accept("NPCSelf");

					toNPCMap.accept("NPCOtherNPC");
					toNPCMap.accept("NPCOtherNPCRough");
					
					toUniversalMap.accept("self");
					toUniversalMap.accept("other");
					toUniversalMap.accept("otherRough");

					if(displacementDescriptionsPlayer.get(slot).get(type).isEmpty()) {
						displacementDescriptionsPlayer.get(slot).remove(type);
					}
					if(displacementDescriptionsNPC.get(slot).get(type).isEmpty()) {
						displacementDescriptionsNPC.get(slot).remove(type);
					}
					if(displacementDescriptions.get(slot).get(type).isEmpty()) {
						displacementDescriptions.get(slot).remove(type);
					}
				});
				
			};

			loadItemMoveDescriptions.accept("replacementText");
			loadItemMoveDescriptions.accept("displacementText");


			if(debug) {
				System.out.println("5");
			}
			
			Predicate<Element> filterEmptyElements = element -> !element.getTextContent().isEmpty(); //helper function to filter out empty elements.

			this.enchantmentLimit = coreAttributes.getOptionalFirstOf("enchantmentLimit") // three possible cases
				.filter(filterEmptyElements) // <enchantmentLimit> or <enchantmentLimit></enchantmentLimit> - text content is "" - trying to convert to Integer throws  - filter it out so default value gets assigned
				.map(Element::getTextContent).map(Integer::valueOf) //<enchantmentLimit>x</enchantmentLimit>, x being Integer		
				.orElse(-1);// empty element or no value in element, assign default value;

			this.clothingSet = coreAttributes.getOptionalFirstOf("clothingSet")
				.filter(filterEmptyElements)
				.map(Element::getTextContent).map(ClothingSet::valueOf)
				.orElse(null);

			
			
			this.pathName = clothingXMLFile.getParentFile().getAbsolutePath() + "/"+ coreAttributes.getMandatoryFirstOf("imageName").getTextContent();
			
			this.pathNameEquipped = new HashMap<>();
			try {
				for(Element imageNameElement : coreAttributes.getAllOf("imageEquippedName")) {
					InventorySlot relatedSlot = InventorySlot.valueOf(imageNameElement.getAttribute("slot"));
					
					this.pathNameEquipped.put(relatedSlot, clothingXMLFile.getParentFile().getAbsolutePath() + "/"+ imageNameElement.getTextContent());
				}
				
			} catch(Exception ex) { // Old version single slot support:
				String path = coreAttributes.getOptionalFirstOf("imageEquippedName")
						.filter(filterEmptyElements)
						.map(e -> clothingXMLFile.getParentFile().getAbsolutePath() + "/" + e.getTextContent())
						.filter(s -> !s.equals(this.pathName)) // if imageEquippedName is the same as imageName, we don't need to load it twice
						.orElse(null);
				this.pathNameEquipped.put(this.getEquipSlots().get(0), path);
			}
			
			
			Function< Element, List<Colour> > getColoursFromElement = (colorsElement) -> { //Helper function to get the colors depending on if it's a specified group or a list of individual colors
				String values = colorsElement.getAttribute("values");
				try {
					if(values.isEmpty()) {
						return colorsElement.getAllOf("colour").stream()
								.map(Element::getTextContent).map(Colour::valueOf)
								.collect(Collectors.toList());
					} else {
						return ColourListPresets.getColourListFromId(values);
					}
				} catch (Exception e) {
					printHelpfulErrorForEnumValueMismatches(e);
					throw new IllegalStateException("Colour tag reading failure: "+colorsElement.getTagName()+" " + e.getMessage(), e);
				}
			};

			List<Colour> importedPrimaryColours = getColoursFromElement
				.apply(coreAttributes.getMandatoryFirstOf("primaryColours"));	
			List<Colour> importedPrimaryColoursDye = getColoursFromElement
				.apply(coreAttributes.getMandatoryFirstOf("primaryColoursDye"));		

			List<Colour> importedSecondaryColours = coreAttributes.getOptionalFirstOf("secondaryColours")
				.map(getColoursFromElement::apply)
				.orElseGet(ArrayList::new);
			List<Colour> importedSecondaryColoursDye = coreAttributes.getOptionalFirstOf("secondaryColoursDye")
				.map(getColoursFromElement::apply)
				.orElseGet(ArrayList::new);

			List<Colour> importedTertiaryColours = coreAttributes.getOptionalFirstOf("tertiaryColours")
				.map(getColoursFromElement::apply)
				.orElseGet(ArrayList::new);
			List<Colour> importedTertiaryColoursDye = coreAttributes.getOptionalFirstOf("tertiaryColoursDye")
				.map(getColoursFromElement::apply)
				.orElseGet(ArrayList::new);

			setUpColours(
				importedPrimaryColours,
				importedPrimaryColoursDye,
				importedSecondaryColours,
				importedSecondaryColoursDye,
				importedTertiaryColours,
				importedTertiaryColoursDye
			);
			
			 if(coreAttributes.getOptionalFirstOf("defaultPatterns").isPresent()) {
				 patternChance = Float.valueOf(coreAttributes.getMandatoryFirstOf("defaultPatterns").getAttribute("patternChance"));
				 isColourDerivedFromPattern = Boolean.valueOf(coreAttributes.getMandatoryFirstOf("defaultPatterns").getAttribute("colourNameDerivedFromPattern"));
				 
			 } else {
				 patternChance = 0;
				 isColourDerivedFromPattern = false;
			 }
			
			Function<Element, List<Pattern> > getPatternsFromElement = (patternsElement) -> { //Helper function to get the patterns
				try {
					return patternsElement.getAllOf("pattern").stream()
							.map(Element::getTextContent).map(Pattern.getAllPatterns()::get)
							.collect(Collectors.toList());
				} catch (Exception e) {
					printHelpfulErrorForEnumValueMismatches(e);
					throw new IllegalStateException("Pattern tag reading failure: "+patternsElement.getTagName()+" " + e.getMessage(), e);
				}
			};

			Function<Element, List<Colour> > getPatternColoursFromElement = (colorsElement) -> { //Helper function to get the colors depending on if it's a specified group or a list of individual colors
				String values = colorsElement.getAttribute("values");
				try {
					if(values.isEmpty()) {
						if(colorsElement.getOptionalFirstOf("colour").isPresent()) {
							return colorsElement.getAllOf("colour").stream()
									.map(Element::getTextContent).map(Colour::valueOf)
									.collect(Collectors.toList());
						} else {
							return new ArrayList<>(ColourListPresets.ALL);
						}
					} else {
						return new ArrayList<>(ColourListPresets.ALL);
					}
				} catch (Exception e) {
					printHelpfulErrorForEnumValueMismatches(e);
					throw new IllegalStateException("Colour tag reading failure: "+colorsElement.getTagName()+" " + e.getMessage(), e);
				}
			};
			
			defaultPatterns = coreAttributes.getOptionalFirstOf("defaultPatterns")
					.map(getPatternsFromElement::apply)
					.orElse(new ArrayList<>(Pattern.getAllDefaultPatterns().values()));
					
			setUpPatternColours(
					coreAttributes.getOptionalFirstOf("patternPrimaryColours")
						.map(getPatternColoursFromElement::apply)
						.orElse(new ArrayList<>(ColourListPresets.ALL)),
					coreAttributes.getOptionalFirstOf("patternPrimaryColoursDye")
						.map(getPatternColoursFromElement::apply)
						.orElse(null),
					coreAttributes.getOptionalFirstOf("patternSecondaryColours")
						.map(getPatternColoursFromElement::apply)
						.orElse(new ArrayList<>(ColourListPresets.ALL)),
					coreAttributes.getOptionalFirstOf("patternSecondaryColoursDye")
						.map(getPatternColoursFromElement::apply)
						.orElse(null),
					coreAttributes.getOptionalFirstOf("patternTertiaryColours")
						.map(getPatternColoursFromElement::apply)
						.orElse(new ArrayList<>(ColourListPresets.ALL)),
					coreAttributes.getOptionalFirstOf("patternTertiaryColoursDye")
						.map(getPatternColoursFromElement::apply)
						.orElse(null));
			
			finalSetUp();
		}
		catch(XMLMissingTagException ex){
			throw new XMLLoadException(ex, clothingXMLFile);
		}
		catch(Exception e){
			System.out.println(e);
			throw new XMLLoadException(e, clothingXMLFile);
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
			this.availablePrimaryColours.add(DEFAULT_COLOUR_VALUE);
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
	
	private void setUpPatternColours(List<Colour> availablePatternPrimaryColours,
			List<Colour> availablePatternPrimaryDyeColours,
			List<Colour> availablePatternSecondaryColours,
			List<Colour> availablePatternSecondaryDyeColours,
			List<Colour> availablePatternTertiaryColours,
			List<Colour> availablePatternTertiaryDyeColours) {
		
		this.availablePatternPrimaryColours = new ArrayList<>();
		if (availablePatternPrimaryColours != null) {
			this.availablePatternPrimaryColours.addAll(availablePatternPrimaryColours);
		} else {
			this.availablePatternPrimaryColours.addAll(ColourListPresets.ALL);
		}

		Set<Colour> colourSet = new HashSet<>();
		
		this.availablePatternPrimaryDyeColours = new ArrayList<>();
		if (availablePatternPrimaryDyeColours != null) {
			this.availablePatternPrimaryDyeColours.addAll(availablePatternPrimaryDyeColours);
		}
		
		this.allAvailablePatternPrimaryColours = new ArrayList<>();
		colourSet.addAll(this.availablePatternPrimaryColours);
		if(availablePatternPrimaryDyeColours!=null) {
			colourSet.addAll(availablePatternPrimaryDyeColours);
		}
		this.allAvailablePatternPrimaryColours.addAll(colourSet);
		this.allAvailablePatternPrimaryColours.sort((c1, c2) -> c1.compareTo(c2));
		
		this.availablePatternSecondaryColours = new ArrayList<>();
		if (availablePatternSecondaryColours != null) {
			this.availablePatternSecondaryColours.addAll(availablePatternSecondaryColours);
		} else {
			this.availablePatternSecondaryColours.addAll(ColourListPresets.ALL);
		}
		
		this.availablePatternSecondaryDyeColours = new ArrayList<>();
		if (availablePatternSecondaryDyeColours != null) {
			this.availablePatternSecondaryDyeColours.addAll(availablePatternSecondaryDyeColours);
		}

		colourSet.clear();
		this.allAvailablePatternSecondaryColours = new ArrayList<>();
//		if(availablePatternSecondaryColours!=null) {
//			colourSet.addAll(availablePatternSecondaryColours);
//		}
//		if(availablePatternSecondaryDyeColours!=null) {
//			colourSet.addAll(availablePatternSecondaryDyeColours);
//		}
//		this.allAvailablePatternSecondaryColours.addAll(colourSet);
		this.allAvailablePatternSecondaryColours.addAll(this.availablePatternSecondaryColours);
		this.allAvailablePatternSecondaryColours.addAll(this.availablePatternSecondaryDyeColours);
		this.allAvailablePatternSecondaryColours.sort((c1, c2) -> c1.compareTo(c2));

		
		this.availablePatternTertiaryColours = new ArrayList<>();
		if (availablePatternTertiaryColours != null) {
			this.availablePatternTertiaryColours.addAll(availablePatternTertiaryColours);
		} else {
			this.availablePatternTertiaryColours.addAll(ColourListPresets.ALL);
		}
		
		this.availablePatternTertiaryDyeColours = new ArrayList<>();
		if (availablePatternTertiaryDyeColours != null) {
			this.availablePatternTertiaryDyeColours.addAll(availablePatternTertiaryDyeColours);
		}

		colourSet.clear();
		this.allAvailablePatternTertiaryColours = new ArrayList<>();
		if(availablePatternTertiaryColours!=null) {
			colourSet.addAll(availablePatternTertiaryColours);
		}
		if(availablePatternTertiaryDyeColours!=null) {
			colourSet.addAll(availablePatternTertiaryDyeColours);
		}
		this.allAvailablePatternTertiaryColours.addAll(colourSet);
		this.allAvailablePatternTertiaryColours.sort((c1, c2) -> c1.compareTo(c2));
	}

	@SuppressWarnings("rawtypes")
	private static void printHelpfulErrorForEnumValueMismatches(Exception ex) {
		Map<Class, Set<String>> possibleEnumValues = new HashMap<>();
		possibleEnumValues.put(ColourListPresets.class, ColourListPresets.getIdToColourListMap().keySet());
		String exMessage = ex.getMessage();
		if (exMessage.startsWith("No ColourListPreset constant")){
			for (Entry<Class, Set<String>> possibleMatch : possibleEnumValues.entrySet()) {
				if (exMessage.contains(possibleMatch.getKey().getCanonicalName())) {
					StringJoiner valueLister = new StringJoiner(",");
					Arrays.asList(possibleMatch.getValue()).forEach(enumValue -> valueLister.add(enumValue.toString()));
					System.err.println("Possible values for "+possibleMatch.getKey().getSimpleName()+" are " + valueLister.toString());
				}
			}
		}
	}
	
	private void finalSetUp() {
		displacementTypesAvailableWithoutNONE = new HashMap<>();
		for (Entry<InventorySlot, List<BlockedParts>> entry : this.blockedPartsMap.entrySet()) {
			displacementTypesAvailableWithoutNONE.put(entry.getKey(), new ArrayList<>());
			for(BlockedParts bp : entry.getValue()) {
				if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
					displacementTypesAvailableWithoutNONE.get(entry.getKey()).add(bp.displacementType);
				}
			}
			Collections.sort(displacementTypesAvailableWithoutNONE.get(entry.getKey()));
		}

		SVGStringMap = new HashMap<>();
		SVGStringEquippedMap = new HashMap<>();
		
		// Add blocked parts due to sealing or plugging:
		for(Entry<InventorySlot, List<ItemTag>> entry : this.itemTags.entrySet()) { //TODO check
			for(ItemTag tag : entry.getValue()) {
				switch(tag) {
					case PLUGS_ANUS:
					case SEALS_ANUS:
						if(this.blockedPartsMap.containsKey(InventorySlot.ANUS)) {
							for(BlockedParts bp : this.blockedPartsMap.get(InventorySlot.ANUS)) {
								if(bp.displacementType==DisplacementType.REMOVE_OR_EQUIP && !bp.blockedBodyParts.contains(CoverableArea.ANUS)) {
									bp.blockedBodyParts.add(CoverableArea.ANUS);
								}
							}
						}
						break;
					case PLUGS_NIPPLES:
					case SEALS_NIPPLES:
						if(this.blockedPartsMap.containsKey(InventorySlot.NIPPLE)) {
							for(BlockedParts bp : this.blockedPartsMap.get(InventorySlot.NIPPLE)) {
								if(bp.displacementType==DisplacementType.REMOVE_OR_EQUIP && !bp.blockedBodyParts.contains(CoverableArea.NIPPLES)) {
									bp.blockedBodyParts.add(CoverableArea.NIPPLES);
								}
							}
						}
						break;
					case PLUGS_VAGINA:
					case SEALS_VAGINA:
						if(this.blockedPartsMap.containsKey(InventorySlot.VAGINA)) {
							for(BlockedParts bp : this.blockedPartsMap.get(InventorySlot.VAGINA)) {
								if(bp.displacementType==DisplacementType.REMOVE_OR_EQUIP && !bp.blockedBodyParts.contains(CoverableArea.VAGINA)) {
									bp.blockedBodyParts.add(CoverableArea.VAGINA);
								}
							}
						}
						break;
					default:
						break;
				}
			}
		}
		
		// Causes crash if done from here for some reason.
		//this.isPatternAvailable = this.getSVGImage().contains("id=\"patternLayer\"");
	}
	
	@Override
	public boolean equals(Object o) { // I know it doesn't include everything, but this should be enough to check for equality.
		if(super.equals(o)){
			if(o instanceof AbstractClothingType){
				if(((AbstractClothingType)o).getName().equals(getName())
						&& ((AbstractClothingType)o).getPathName().equals(getPathName())
						&& ((AbstractClothingType)o).getPhysicalResistance() == getPhysicalResistance()
						&& ((AbstractClothingType)o).getFemininityMaximum() == getFemininityMaximum()
						&& ((AbstractClothingType)o).getFemininityMinimum() == getFemininityMinimum()
						&& ((AbstractClothingType)o).getFemininityRestriction() == getFemininityRestriction()
						&& ((AbstractClothingType)o).getEquipSlots().equals(getEquipSlots())
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
		result = 31 * result + Float.floatToIntBits(getPhysicalResistance());
		result = 31 * result + getFemininityMaximum();
		result = 31 * result + getFemininityMinimum();
		if(getFemininityRestriction()!=null) {
			result = 31 * result + getFemininityRestriction().hashCode();
		}
		result = 31 * result + getEquipSlots().hashCode();
		result = 31 * result + getEffects().hashCode();
		if(getClothingSet()!=null) {
			result = 31 * result + getClothingSet().hashCode();
		}
		result = 31 * result + getRarity().hashCode();
		return result;
	}
	

	public static AbstractClothing generateClothing(String clothingTypeId, Colour primaryColour, Colour secondaryColour, Colour tertiaryColour, boolean allowRandomEnchantment) {
		return AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId(clothingTypeId), primaryColour, secondaryColour, tertiaryColour, allowRandomEnchantment);
	}
	
	public static AbstractClothing generateClothing(AbstractClothingType clothingType, Colour primaryColour, Colour secondaryColour, Colour tertiaryColour, boolean allowRandomEnchantment) {
		Colour c1 = primaryColour;
		Colour c2 = secondaryColour;
		Colour c3 = tertiaryColour;

		if (primaryColour == null) {
			if(clothingType.getAvailablePrimaryColours().isEmpty()) {
				c1 = DEFAULT_COLOUR_VALUE;
			} else {
				c1 = Util.randomItemFrom(clothingType.getAvailablePrimaryColours());
			}
		} else if (clothingType.getAllAvailablePrimaryColours() != null) {
			if (!clothingType.getAllAvailablePrimaryColours().contains(primaryColour)) {
				c1 = Util.randomItemFrom(clothingType.getAllAvailablePrimaryColours());
			}
		}
		
		if (secondaryColour == null) {
			if(clothingType.getAvailableSecondaryColours().isEmpty()) {
				c2 = DEFAULT_COLOUR_VALUE;
			} else {
				List<Colour> secondariesExclusive = new ArrayList<>(clothingType.getAvailableSecondaryColours());
				if(secondariesExclusive.size()>1) {
					secondariesExclusive.remove(c1);
				}
				c2 = Util.randomItemFrom(secondariesExclusive);
			}
		} else if(!clothingType.getAllAvailableSecondaryColours().contains(secondaryColour)) {
			if(clothingType.getAllAvailableSecondaryColours().isEmpty()) {
				c2 = DEFAULT_COLOUR_VALUE;
			} else {
				List<Colour> secondariesExclusive = new ArrayList<>(clothingType.getAllAvailableSecondaryColours());
				if(secondariesExclusive.size()>1) {
					secondariesExclusive.remove(c1);
				}
				c2 = Util.randomItemFrom(secondariesExclusive);
			}
		}
		
		if (tertiaryColour == null) {
			if(clothingType.getAvailableTertiaryColours().isEmpty()) {
				c3 = DEFAULT_COLOUR_VALUE;
			} else {
				List<Colour> tertiariesExclusive = new ArrayList<>(clothingType.getAvailableTertiaryColours());
				if(tertiariesExclusive.size()>2) {
					tertiariesExclusive.remove(c1);
					tertiariesExclusive.remove(c2);
				}
				c3 = Util.randomItemFrom(tertiariesExclusive);
			}
			
		} else if(!clothingType.getAllAvailableTertiaryColours().contains(tertiaryColour)) {
			if(clothingType.getAllAvailableTertiaryColours().isEmpty()) {
				c3 = DEFAULT_COLOUR_VALUE;
			} else {
				List<Colour> tertiariesExclusive = new ArrayList<>(clothingType.getAllAvailableTertiaryColours());
				if(tertiariesExclusive.size()>2) {
					tertiariesExclusive.remove(c1);
					tertiariesExclusive.remove(c2);
				}
				c3 = Util.randomItemFrom(tertiariesExclusive);
			}
		}
		
		return new AbstractClothing(clothingType, c1, c2, c3, allowRandomEnchantment) {};
	}

	public static AbstractClothing generateClothing(AbstractClothingType clothingType, Colour colourShade, boolean allowRandomEnchantment) {
		return AbstractClothingType.generateClothing(clothingType, colourShade, null, null, allowRandomEnchantment);
	}

	public static AbstractClothing generateClothing(String clothingTypeId, Colour colourShade, boolean allowRandomEnchantment) {
		return AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId(clothingTypeId), colourShade, null, null, allowRandomEnchantment);
	}

	/** Allows random enchantment. Uses random colour.*/
	public static AbstractClothing generateClothing(AbstractClothingType clothingType) {
		return AbstractClothingType.generateClothing(clothingType, clothingType.getAvailablePrimaryColours().get(Util.random.nextInt(clothingType.getAvailablePrimaryColours().size())), true);
	}

	/** Uses random colour.*/
	public static AbstractClothing generateClothing(AbstractClothingType clothingType, boolean allowRandomEnchantment) {
		return AbstractClothingType.generateClothing(clothingType, clothingType.getAvailablePrimaryColours().get(Util.random.nextInt(clothingType.getAvailablePrimaryColours().size())), allowRandomEnchantment);
	}

	/** Uses random colour.*/
	public static AbstractClothing generateClothing(String clothingTypeId, boolean allowRandomEnchantment) {
		AbstractClothingType type = ClothingType.getClothingTypeFromId(clothingTypeId);
		return AbstractClothingType.generateClothing(type, type.getAvailablePrimaryColours().get(Util.random.nextInt(type.getAvailablePrimaryColours().size())), allowRandomEnchantment);
	}
	
	
	/**
	 * Generates clothing with the provided enchantments.
	 */
	public static AbstractClothing generateClothing(AbstractClothingType clothingType, Colour primaryColour, Colour secondaryColour, Colour tertiaryColour, List<ItemEffect> effects) {
		Colour c1 = primaryColour;
		Colour c2 = secondaryColour;
		Colour c3 = tertiaryColour;

		if (primaryColour == null) {
			if(clothingType.getAvailablePrimaryColours().isEmpty()) {
				c1 = DEFAULT_COLOUR_VALUE;
			} else {
				c1 = Util.randomItemFrom(clothingType.getAvailablePrimaryColours());
			}
		} else if (clothingType.getAllAvailablePrimaryColours() != null) {
			if (!clothingType.getAllAvailablePrimaryColours().contains(primaryColour)) {
				c1 = Util.randomItemFrom(clothingType.getAllAvailablePrimaryColours());
			}
		}
		
		if (secondaryColour == null) {
			if(clothingType.getAvailableSecondaryColours().isEmpty()) {
				c2 = DEFAULT_COLOUR_VALUE;
			} else {
				List<Colour> secondariesExclusive = new ArrayList<>(clothingType.getAvailableSecondaryColours());
				if(secondariesExclusive.size()>1) {
					secondariesExclusive.remove(c1);
				}
				c2 = Util.randomItemFrom(secondariesExclusive);
			}
		} else if(!clothingType.getAllAvailableSecondaryColours().contains(secondaryColour)) {
			if(clothingType.getAllAvailableSecondaryColours().isEmpty()) {
				c2 = DEFAULT_COLOUR_VALUE;
			} else {
				List<Colour> secondariesExclusive = new ArrayList<>(clothingType.getAllAvailableSecondaryColours());
				if(secondariesExclusive.size()>1) {
					secondariesExclusive.remove(c1);
				}
				c2 = Util.randomItemFrom(secondariesExclusive);
			}
		}
		
		if (tertiaryColour == null) {
			if(clothingType.getAvailableTertiaryColours().isEmpty()) {
				c3 = DEFAULT_COLOUR_VALUE;
			} else {
				List<Colour> tertiariesExclusive = new ArrayList<>(clothingType.getAvailableTertiaryColours());
				if(tertiariesExclusive.size()>2) {
					tertiariesExclusive.remove(c1);
					tertiariesExclusive.remove(c2);
				}
				c3 = Util.randomItemFrom(tertiariesExclusive);
			}
			
		} else if(!clothingType.getAllAvailableTertiaryColours().contains(tertiaryColour)) {
			if(clothingType.getAllAvailableTertiaryColours().isEmpty()) {
				c3 = DEFAULT_COLOUR_VALUE;
			} else {
				List<Colour> tertiariesExclusive = new ArrayList<>(clothingType.getAllAvailableTertiaryColours());
				if(tertiariesExclusive.size()>2) {
					tertiariesExclusive.remove(c1);
					tertiariesExclusive.remove(c2);
				}
				c3 = Util.randomItemFrom(tertiariesExclusive);
			}
		}
		
		return new AbstractClothing(clothingType, c1, c2, c3, effects) {};
	}
	
	/**
	 * Generates clothing with the provided enchantments.
	 */
	public static AbstractClothing generateClothing(AbstractClothingType clothingType, Colour colour, List<ItemEffect> effects) {
		return generateClothing(clothingType, colour, null, null, effects);
	}
	
	public static AbstractClothing generateClothing(String clothingTypeId, Colour colour, List<ItemEffect> effects) {
		return generateClothing(ClothingType.getClothingTypeFromId(clothingTypeId), colour, null, null, effects);
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

	public static AbstractClothing generateClothingWithNegativeEnchantment(AbstractClothingType clothingType, Colour colour) {
		List<ItemEffect> effects = new ArrayList<>();

		TFModifier rndMod = TFModifier.getClothingAttributeList().get(Util.random.nextInt(TFModifier.getClothingAttributeList().size()));
		effects.add(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, rndMod, TFPotency.getRandomWeightedNegativePotency(), 0));
		
		return generateClothing(clothingType, colour, effects);
	}
	
	public static AbstractClothing generateClothingWithNegativeEnchantment(AbstractClothingType clothingType) {
		return AbstractClothingType.generateClothingWithNegativeEnchantment(clothingType, clothingType.getAvailablePrimaryColours().get(Util.random.nextInt(clothingType.getAvailablePrimaryColours().size())));
	}
	
	public static AbstractClothing generateRareClothing(AbstractClothingType type) {
		List<ItemEffect> effects = new ArrayList<>();
		
		List<TFModifier> attributeMods = new ArrayList<>(TFModifier.getClothingAttributeList());
		
		TFModifier rndMod = attributeMods.get(Util.random.nextInt(attributeMods.size()));
		attributeMods.remove(rndMod);
		TFModifier rndMod2 = attributeMods.get(Util.random.nextInt(attributeMods.size()));
		
		effects.add(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, rndMod, TFPotency.MAJOR_BOOST, 0));
		effects.add(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, rndMod2, TFPotency.MAJOR_BOOST, 0));
		
		return AbstractClothingType.generateClothing(
				type,
				type.getAvailablePrimaryColours().get(Util.random.nextInt(type.getAvailablePrimaryColours().size())),
				effects);
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
						return UtilText.parse(clothingEquipper, clothingOwner, "[npc1.Name] manipulates [npc2.namePos] clothing.");
					}
				}
			}
		}
	}
	
	public String equipText(GameCharacter clothingOwner, GameCharacter clothingEquipper, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
		if(this.isCondom(slotToEquipInto) && applyEffects) {
			if(InventoryDialogue.getInventoryNPC()!=null) {
				return ((NPC) InventoryDialogue.getInventoryNPC()).getCondomEquipEffects(clothingEquipper, clothingOwner, rough);
			}
		}
		
		if(clothingOwner==null || clothingEquipper==null) {
			return "";
		}
		
		if(displacementDescriptions!=null && displacementDescriptions.get(slotToEquipInto)!=null && !displacementDescriptions.get(slotToEquipInto).isEmpty()) {
			if(clothingOwner.equals(clothingEquipper)) {
				if(displacementDescriptions.get(slotToEquipInto)!=null
						&& displacementDescriptions.get(slotToEquipInto).containsKey(DisplacementType.REMOVE_OR_EQUIP)
						&& displacementDescriptions.get(slotToEquipInto).get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.REPLACEMENT_SELF)) {
					return UtilText.parse(clothingOwner, displacementDescriptions.get(slotToEquipInto).get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.REPLACEMENT_SELF));
				}
				return UtilText.parse(clothingOwner, "[npc.Name] [npc.verb(equip)] the "+clothing.getName()+"."); //TODO
				
			} else {
				if(rough) {
					if(displacementDescriptions.get(slotToEquipInto)!=null
							&& displacementDescriptions.get(slotToEquipInto).containsKey(DisplacementType.REMOVE_OR_EQUIP)
							&& displacementDescriptions.get(slotToEquipInto).get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.REPLACEMENT_ROUGH)) {
						return UtilText.parse(clothingEquipper, clothingOwner, displacementDescriptions.get(slotToEquipInto).get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.REPLACEMENT_ROUGH));
					}
					return UtilText.parse(clothingEquipper, clothingOwner, "[npc.Name] [npc.verb(get)] [npc2.name] to equip the "+clothing.getName()+".");
					
				} else {
					if(displacementDescriptions.get(slotToEquipInto)!=null
							&& displacementDescriptions.get(slotToEquipInto).containsKey(DisplacementType.REMOVE_OR_EQUIP)
							&& displacementDescriptions.get(slotToEquipInto).get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.REPLACEMENT)) {
						return UtilText.parse(clothingEquipper, clothingOwner, displacementDescriptions.get(slotToEquipInto).get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.REPLACEMENT));
					}
					return UtilText.parse(clothingEquipper, clothingOwner, "[npc.Name] roughly [npc.verb(force)] [npc2.name] to equip the "+clothing.getName()+".");
				}
			}
			
		} else {
			try {
				if (clothingEquipper.isPlayer()) {
					if(clothingOwner.isPlayer()) {
						if(displacementDescriptionsPlayer!=null
								&& displacementDescriptionsPlayer.get(slotToEquipInto)!=null
								&& displacementDescriptionsPlayer.get(slotToEquipInto).containsKey(DisplacementType.REMOVE_OR_EQUIP)
								&& displacementDescriptionsPlayer.get(slotToEquipInto).get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.REPLACEMENT_SELF)) {
							return displacementDescriptionsPlayer.get(slotToEquipInto).get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.REPLACEMENT_SELF);
						}
						return "You equip the "+clothing.getName()+".";
						
					} else {
						if(rough) {
							if(displacementDescriptionsPlayer!=null
									&& displacementDescriptionsPlayer.get(slotToEquipInto)!=null
									&& displacementDescriptionsPlayer.get(slotToEquipInto).containsKey(DisplacementType.REMOVE_OR_EQUIP)
									&& displacementDescriptionsPlayer.get(slotToEquipInto).get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.REPLACEMENT_ROUGH)) {
								return UtilText.parse(clothingOwner, displacementDescriptionsPlayer.get(slotToEquipInto).get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.REPLACEMENT_ROUGH));
							}
							return UtilText.parse(clothingOwner, "You roughly force "+clothing.getName(true)+" onto [npc.name].");
							
						} else {
							if(displacementDescriptionsPlayer!=null
									&& displacementDescriptionsPlayer.get(slotToEquipInto)!=null
									&& displacementDescriptionsPlayer.get(slotToEquipInto).containsKey(DisplacementType.REMOVE_OR_EQUIP)
									&& displacementDescriptionsPlayer.get(slotToEquipInto).get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.REPLACEMENT)) {
								return UtilText.parse(clothingOwner, displacementDescriptionsPlayer.get(slotToEquipInto).get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.REPLACEMENT));
							}
							return UtilText.parse(clothingOwner, "You put "+clothing.getName(true)+" on [npc.name].");
						}
					}
					
				} else {
					if (clothingOwner.isPlayer()) {
						if(rough) {
							if(displacementDescriptionsNPC!=null
									&& displacementDescriptionsNPC.get(slotToEquipInto)!=null
									&& displacementDescriptionsNPC.get(slotToEquipInto).containsKey(DisplacementType.REMOVE_OR_EQUIP)
									&& displacementDescriptionsNPC.get(slotToEquipInto).get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.REPLACEMENT_ROUGH)) {
								return UtilText.parse(clothingEquipper, displacementDescriptionsNPC.get(slotToEquipInto).get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.REPLACEMENT_ROUGH));
							}
							return UtilText.parse(clothingEquipper, "[npc.Name] roughly forces "+clothing.getName(true)+" onto you.");
							
						} else {
							if(displacementDescriptionsNPC!=null
									&& displacementDescriptionsNPC.get(slotToEquipInto)!=null
									&& displacementDescriptionsNPC.get(slotToEquipInto).containsKey(DisplacementType.REMOVE_OR_EQUIP)
									&& displacementDescriptionsNPC.get(slotToEquipInto).get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.REPLACEMENT)) {
								return UtilText.parse(clothingEquipper, displacementDescriptionsNPC.get(slotToEquipInto).get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.REPLACEMENT));
							}
							return UtilText.parse(clothingEquipper, "[npc.Name] puts "+clothing.getName(true)+" on you.");
						}
					} else {
						if(clothingOwner.equals(clothingEquipper)) {
							if(displacementDescriptionsNPC!=null
									&& displacementDescriptionsNPC.get(slotToEquipInto)!=null
									&& displacementDescriptionsNPC.get(slotToEquipInto).containsKey(DisplacementType.REMOVE_OR_EQUIP)
									&& displacementDescriptionsNPC.get(slotToEquipInto).get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.REPLACEMENT_SELF)) {
								return UtilText.parse(clothingOwner, displacementDescriptionsNPC.get(slotToEquipInto).get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.REPLACEMENT_SELF));
							}
							return UtilText.parse(clothingOwner, "[npc.Name] equips "+clothing.getName(true)+".");
							
						} else {
							if(rough) {
								if(displacementDescriptionsNPC!=null
										&& displacementDescriptionsNPC.get(slotToEquipInto)!=null
										&& displacementDescriptionsNPC.get(slotToEquipInto).containsKey(DisplacementType.REMOVE_OR_EQUIP)
										&& displacementDescriptionsNPC.get(slotToEquipInto).get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.NPC_ON_NPC_REPLACEMENT_ROUGH)) {
									return UtilText.parse(clothingEquipper, clothingOwner,displacementDescriptionsNPC.get(slotToEquipInto).get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.NPC_ON_NPC_REPLACEMENT_ROUGH));
								}
								return UtilText.parse(clothingEquipper, clothingOwner, "[npc.Name] roughly forces [npc2.name] to equip "+clothing.getName(true)+".");
								
							} else {
								if(displacementDescriptionsNPC!=null
										&& displacementDescriptionsNPC.get(slotToEquipInto)!=null
										&& displacementDescriptionsNPC.get(slotToEquipInto).containsKey(DisplacementType.REMOVE_OR_EQUIP)
										&& displacementDescriptionsNPC.get(slotToEquipInto).get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.NPC_ON_NPC_REPLACEMENT)) {
									return UtilText.parse(clothingEquipper, clothingOwner,displacementDescriptionsNPC.get(slotToEquipInto).get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.NPC_ON_NPC_REPLACEMENT));
								}
								return UtilText.parse(clothingEquipper, clothingOwner, "[npc.Name] gets [npc2.name] to equip "+clothing.getName(true)+".");
							}
						}
					}
				}
			} catch(Exception ex) {
				System.err.println(this.getName()+" failed to generate equip text.");
				ex.printStackTrace();
				return "";
			}
		}
	}

	public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToUnequipFrom, boolean rough, AbstractClothing clothing, boolean applyEffects) {
		if(clothingOwner==null || clothingRemover==null) {
			return "";
		}
		
		if(displacementDescriptions!=null && !displacementDescriptions.isEmpty()) {
			if(clothingOwner.equals(clothingRemover)) {
				if(displacementDescriptions.get(slotToUnequipFrom)!=null
						&& displacementDescriptions.get(slotToUnequipFrom).containsKey(DisplacementType.REMOVE_OR_EQUIP)
						&& displacementDescriptions.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.DISPLACEMENT_SELF)) {
					return UtilText.parse(clothingOwner, displacementDescriptions.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.DISPLACEMENT_SELF));
				}
				return UtilText.parse(clothingOwner, "[npc.name] [npc.verb(unequip)] [npc.her] "+clothing.getName());
				
			} else {
				if(rough) {
					if(displacementDescriptions.get(slotToUnequipFrom)!=null
							&& displacementDescriptions.get(slotToUnequipFrom).containsKey(DisplacementType.REMOVE_OR_EQUIP)
							&& displacementDescriptions.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.DISPLACEMENT_ROUGH)) {
						return UtilText.parse(clothingRemover, clothingOwner, displacementDescriptions.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.DISPLACEMENT_ROUGH));
					}
					return UtilText.parse(clothingRemover, clothingOwner, "[npc.name] roughly [npc.verb(unequip)] [npc2.namePos] "+clothing.getName());
					
				} else {
					if(displacementDescriptions.get(slotToUnequipFrom)!=null
							&& displacementDescriptions.get(slotToUnequipFrom).containsKey(DisplacementType.REMOVE_OR_EQUIP)
							&& displacementDescriptions.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.DISPLACEMENT)) {
						return UtilText.parse(clothingRemover, clothingOwner, displacementDescriptions.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.DISPLACEMENT));
					}
					return UtilText.parse(clothingRemover, clothingOwner, "[npc.name] [npc.verb(get)] [npc2.name] to unequip [npc2.her] "+clothing.getName());
				}
			}
			
		} else {
			if (clothingRemover.isPlayer()) {
				if(clothingOwner.isPlayer()) {
					if(displacementDescriptionsPlayer!=null
							&& displacementDescriptionsPlayer.get(slotToUnequipFrom)!=null
							&& displacementDescriptionsPlayer.get(slotToUnequipFrom).containsKey(DisplacementType.REMOVE_OR_EQUIP)
							&& displacementDescriptionsPlayer.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.DISPLACEMENT_SELF)) {
						return displacementDescriptionsPlayer.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.DISPLACEMENT_SELF);
					}
					return "You unequip the "+clothing.getName()+"";
				} else {
					if(rough) {
						if(displacementDescriptionsPlayer!=null
								&& displacementDescriptionsPlayer.get(slotToUnequipFrom)!=null
								&& displacementDescriptionsPlayer.get(slotToUnequipFrom).containsKey(DisplacementType.REMOVE_OR_EQUIP)
								&& displacementDescriptionsPlayer.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.DISPLACEMENT_ROUGH)) {
							return UtilText.parse(clothingOwner, displacementDescriptionsPlayer.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.DISPLACEMENT_ROUGH));
						}
						return UtilText.parse(clothingOwner, "You roughly take off [npc.namePos] "+clothing.getName()+".");
					} else {
						if(displacementDescriptionsPlayer!=null
								&& displacementDescriptionsPlayer.get(slotToUnequipFrom)!=null
								&& displacementDescriptionsPlayer.get(slotToUnequipFrom).containsKey(DisplacementType.REMOVE_OR_EQUIP)
								&& displacementDescriptionsPlayer.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.DISPLACEMENT)) {
							return UtilText.parse(clothingOwner, displacementDescriptionsPlayer.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.DISPLACEMENT));
						}
						return UtilText.parse(clothingOwner, "You take off [npc.namePos] "+clothing.getName()+".");
					}
				}
				
			} else {
				if (clothingOwner.isPlayer()) {
					if(rough) {
						if(displacementDescriptionsNPC!=null
								&& displacementDescriptionsNPC.get(slotToUnequipFrom)!=null
								&& displacementDescriptionsNPC.get(slotToUnequipFrom).containsKey(DisplacementType.REMOVE_OR_EQUIP)
								&& displacementDescriptionsNPC.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.DISPLACEMENT_ROUGH)) {
							return UtilText.parse(clothingRemover, displacementDescriptionsNPC.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.DISPLACEMENT_ROUGH));
						}
						return UtilText.parse(clothingRemover, "[npc.Name] roughly takes off your "+clothing.getName()+".");
					} else {
						if(displacementDescriptionsNPC!=null
								&& displacementDescriptionsNPC.get(slotToUnequipFrom)!=null
								&& displacementDescriptionsNPC.get(slotToUnequipFrom).containsKey(DisplacementType.REMOVE_OR_EQUIP)
								&& displacementDescriptionsNPC.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.DISPLACEMENT)) {
							return UtilText.parse(clothingRemover, displacementDescriptionsNPC.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.DISPLACEMENT));
						}
						return UtilText.parse(clothingRemover, "[npc.Name] takes off your "+clothing.getName()+".");
					}
				} else {
					if(clothingOwner.equals(clothingRemover)) {
						if(displacementDescriptionsNPC!=null
								&& displacementDescriptionsNPC.get(slotToUnequipFrom)!=null
								&& displacementDescriptionsNPC.get(slotToUnequipFrom).containsKey(DisplacementType.REMOVE_OR_EQUIP)
								&& displacementDescriptionsNPC.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.DISPLACEMENT_SELF)) {
							return UtilText.parse(clothingOwner, displacementDescriptionsNPC.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.DISPLACEMENT_SELF));
						}
						return UtilText.parse(clothingOwner, "[npc.Name] unequips [npc.her] "+clothing.getName()+".");
						
					} else {
						if(rough) {
							if(displacementDescriptionsNPC!=null
									&& displacementDescriptionsNPC.get(slotToUnequipFrom)!=null
									&& displacementDescriptionsNPC.get(slotToUnequipFrom).containsKey(DisplacementType.REMOVE_OR_EQUIP)
									&& displacementDescriptionsNPC.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.NPC_ON_NPC_DISPLACEMENT_ROUGH)) {
								return UtilText.parse(clothingRemover, clothingOwner,displacementDescriptionsNPC.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.NPC_ON_NPC_DISPLACEMENT_ROUGH));
							}
							return UtilText.parse(clothingRemover, clothingOwner, "[npc.Name] roughly forces [npc2.name] to equip "+clothing.getName(true)+".");
							
						} else {
							if(displacementDescriptionsNPC!=null
									&& displacementDescriptionsNPC.get(slotToUnequipFrom)!=null
									&& displacementDescriptionsNPC.get(slotToUnequipFrom).containsKey(DisplacementType.REMOVE_OR_EQUIP)
									&& displacementDescriptionsNPC.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.NPC_ON_NPC_DISPLACEMENT)) {
								return UtilText.parse(clothingRemover, clothingOwner,displacementDescriptionsNPC.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.NPC_ON_NPC_DISPLACEMENT));
							}
							return UtilText.parse(clothingRemover, clothingOwner, "[npc.Name] gets [npc2.name] to equip "+clothing.getName(true)+".");
						}
					}
				}
			}
		}
	}

	public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotClothingIsEquippedTo, DisplacementType dt, boolean rough) {
		if(clothingOwner==null || clothingRemover==null) {
			return "";
		}
		
		if(displacementDescriptions!=null && !displacementDescriptions.isEmpty()) {
			if(clothingOwner.equals(clothingRemover)) {
				if(displacementDescriptions.get(slotClothingIsEquippedTo)!=null
						&& displacementDescriptions.get(slotClothingIsEquippedTo).containsKey(dt)
						&& displacementDescriptions.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.DISPLACEMENT_SELF)) {
					return UtilText.parse(clothingOwner, displacementDescriptions.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.DISPLACEMENT_SELF));
				}
				return UtilText.parse(clothingOwner, "[npc.name] "+(clothingRemover.isPlayer()?dt.getDescription():dt.getDescriptionThirdPerson())+" [npc.her] "+this.getName());
				
			} else {
				if(rough) {
					if(displacementDescriptions.get(slotClothingIsEquippedTo)!=null
							&& displacementDescriptions.get(slotClothingIsEquippedTo).containsKey(dt)
							&& displacementDescriptions.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.DISPLACEMENT)) {
						return UtilText.parse(clothingRemover, clothingOwner, displacementDescriptions.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.DISPLACEMENT));
					}
					return UtilText.parse(clothingRemover, clothingOwner, "[npc.name] "+(clothingRemover.isPlayer()?dt.getDescription():dt.getDescriptionThirdPerson())+" [npc2.namePos] "+this.getName());
					
				} else {
					if(displacementDescriptions.get(slotClothingIsEquippedTo)!=null
							&& displacementDescriptions.get(slotClothingIsEquippedTo).containsKey(dt)
							&& displacementDescriptions.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.DISPLACEMENT_ROUGH)) {
						return UtilText.parse(clothingRemover, clothingOwner, displacementDescriptions.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.DISPLACEMENT_ROUGH));
					}
					return UtilText.parse(clothingRemover, clothingOwner, "[npc.name] roughly "+(clothingRemover.isPlayer()?dt.getDescription():dt.getDescriptionThirdPerson())+" [npc2.namePos] "+this.getName());
				}
			}
			
		} else {
			if (clothingRemover.isPlayer()) {
				if(clothingOwner.isPlayer()) {
					if(displacementDescriptionsPlayer!=null
							&& displacementDescriptionsPlayer.get(slotClothingIsEquippedTo)!=null
							&& displacementDescriptionsPlayer.get(slotClothingIsEquippedTo).containsKey(dt)
							&& displacementDescriptionsPlayer.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.DISPLACEMENT_SELF)) {
						return displacementDescriptionsPlayer.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.DISPLACEMENT_SELF);
					}
					return "You "+dt.getDescription()+" your "+this.getName()+"";
				} else {
					if(rough) {
						if(displacementDescriptionsPlayer!=null
								&& displacementDescriptionsPlayer.get(slotClothingIsEquippedTo)!=null
								&& displacementDescriptionsPlayer.get(slotClothingIsEquippedTo).containsKey(dt)
								&& displacementDescriptionsPlayer.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.DISPLACEMENT_ROUGH)) {
							return UtilText.parse(clothingOwner, displacementDescriptionsPlayer.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.DISPLACEMENT_ROUGH));
						}
						return UtilText.parse(clothingOwner, "You roughly "+dt.getDescription()+" [npc.namePos] "+this.getName()+".");
					} else {
						if(displacementDescriptionsPlayer!=null
								&& displacementDescriptionsPlayer.get(slotClothingIsEquippedTo)!=null
								&& displacementDescriptionsPlayer.get(slotClothingIsEquippedTo).containsKey(dt)
								&& displacementDescriptionsPlayer.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.DISPLACEMENT)) {
							return UtilText.parse(clothingOwner, displacementDescriptionsPlayer.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.DISPLACEMENT));
						}
						return UtilText.parse(clothingOwner, "You "+dt.getDescription()+" [npc.namePos] "+this.getName()+".");
					}
				}
				
			} else {
				if (clothingOwner.isPlayer()) {
					if(rough) {
						if(displacementDescriptionsNPC!=null
								&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo)!=null
								&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo).containsKey(dt)
								&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.DISPLACEMENT_ROUGH)) {
							return UtilText.parse(clothingRemover, displacementDescriptionsNPC.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.DISPLACEMENT_ROUGH));
						}
						return UtilText.parse(clothingRemover, "[npc.Name] roughly "+dt.getDescriptionThirdPerson()+" your "+this.getName()+".");
						
					} else {
						if(displacementDescriptionsNPC!=null
								&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo)!=null
								&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo).containsKey(dt)
								&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.DISPLACEMENT)) {
							return UtilText.parse(clothingRemover, displacementDescriptionsNPC.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.DISPLACEMENT));
						}
						return UtilText.parse(clothingRemover, "[npc.Name] "+dt.getDescriptionThirdPerson()+" your "+this.getName()+".");
					}
				} else {
					if(clothingOwner.equals(clothingRemover)) {
						if(displacementDescriptionsNPC!=null
								&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo)!=null
								&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo).containsKey(dt)
								&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.DISPLACEMENT_SELF)) {
							return UtilText.parse(clothingRemover, displacementDescriptionsNPC.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.DISPLACEMENT_SELF));
						}
						return UtilText.parse(clothingOwner, "[npc.Name] "+dt.getDescriptionThirdPerson()+" [npc.her] "+this.getName()+".");
						
					} else {
						if(rough) {
							if(displacementDescriptionsNPC!=null
									&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo)!=null
									&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo).containsKey(dt)
									&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.NPC_ON_NPC_DISPLACEMENT_ROUGH)) {
								return UtilText.parse(clothingRemover, clothingOwner,displacementDescriptionsNPC.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.NPC_ON_NPC_DISPLACEMENT_ROUGH));
							}
							return UtilText.parse(clothingRemover, clothingOwner, "[npc.Name] roughly "+dt.getDescriptionThirdPerson()+" [npc2.namePos] "+this.getName()+".");
							
						} else {
							if(displacementDescriptionsNPC!=null
									&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo)!=null
									&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo).containsKey(dt)
									&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.NPC_ON_NPC_DISPLACEMENT)) {
								return UtilText.parse(clothingRemover, clothingOwner,displacementDescriptionsNPC.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.NPC_ON_NPC_DISPLACEMENT));
							}
							return UtilText.parse(clothingRemover, clothingOwner, "[npc.Name] "+dt.getDescriptionThirdPerson()+" [npc2.namePos] "+this.getName()+".");
						}
					}
				}
			}
		}
	}

	public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotClothingIsEquippedTo, DisplacementType dt, boolean rough) {
		if(clothingOwner==null || clothingRemover==null) {
			return "";
		}
		
		if(displacementDescriptions!=null && !displacementDescriptions.isEmpty()) {
			if(clothingOwner.equals(clothingRemover)) {
				if(displacementDescriptions.get(slotClothingIsEquippedTo)!=null
						&& displacementDescriptions.get(slotClothingIsEquippedTo).containsKey(dt)
						&& displacementDescriptions.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.REPLACEMENT_SELF)) {
					return UtilText.parse(clothingOwner, displacementDescriptions.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.REPLACEMENT_SELF));
				}
				return UtilText.parse(clothingOwner, "[npc.name] "+(clothingRemover.isPlayer()?dt.getOppositeDescription():dt.getOppositeDescriptionThirdPerson())+" [npc.her] "+this.getName());
				
			} else {
				if(rough) {
					if(displacementDescriptions.get(slotClothingIsEquippedTo)!=null
							&& displacementDescriptions.get(slotClothingIsEquippedTo).containsKey(dt)
							&& displacementDescriptions.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.REPLACEMENT_ROUGH)) {
						return UtilText.parse(clothingRemover, clothingOwner, displacementDescriptions.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.REPLACEMENT_ROUGH));
					}
					return UtilText.parse(clothingRemover, clothingOwner, "[npc.name] "+(clothingRemover.isPlayer()?dt.getOppositeDescription():dt.getOppositeDescriptionThirdPerson())+" [npc2.namePos] "+this.getName());
					
				} else {
					if(displacementDescriptions.get(slotClothingIsEquippedTo)!=null
							&& displacementDescriptions.get(slotClothingIsEquippedTo).containsKey(dt)
							&& displacementDescriptions.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.REPLACEMENT)) {
						return UtilText.parse(clothingRemover, clothingOwner, displacementDescriptions.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.REPLACEMENT));
					}
					return UtilText.parse(clothingRemover, clothingOwner, "[npc.name] roughly "+(clothingRemover.isPlayer()?dt.getOppositeDescription():dt.getOppositeDescriptionThirdPerson())+" [npc2.namePos] "+this.getName());
				}
			}
			
		} else {
			if (clothingRemover.isPlayer()) {
				if(clothingOwner.isPlayer()) {
					if(displacementDescriptionsPlayer!=null
							&& displacementDescriptionsPlayer.get(slotClothingIsEquippedTo)!=null
							&& displacementDescriptionsPlayer.get(slotClothingIsEquippedTo).containsKey(dt)
							&& displacementDescriptionsPlayer.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.REPLACEMENT_SELF)) {
						return displacementDescriptionsPlayer.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.REPLACEMENT_SELF);
					}
					return "You "+dt.getOppositeDescription()+" your "+this.getName()+"";
				} else {
					if(rough) {
						if(displacementDescriptionsPlayer!=null
								&& displacementDescriptionsPlayer.get(slotClothingIsEquippedTo)!=null
								&& displacementDescriptionsPlayer.get(slotClothingIsEquippedTo).containsKey(dt)
								&& displacementDescriptionsPlayer.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.REPLACEMENT_ROUGH)) {
							return UtilText.parse(clothingOwner, displacementDescriptionsPlayer.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.REPLACEMENT_ROUGH));
						}
						return UtilText.parse(clothingOwner, "You roughly "+dt.getOppositeDescription()+" [npc.namePos] "+this.getName()+".");
					} else {
						if(displacementDescriptionsPlayer!=null
								&& displacementDescriptionsPlayer.get(slotClothingIsEquippedTo)!=null
								&& displacementDescriptionsPlayer.get(slotClothingIsEquippedTo).containsKey(dt)
								&& displacementDescriptionsPlayer.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.REPLACEMENT)) {
							return UtilText.parse(clothingOwner, displacementDescriptionsPlayer.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.REPLACEMENT));
						}
						return UtilText.parse(clothingOwner, "You "+dt.getOppositeDescription()+" [npc.namePos] "+this.getName()+".");
					}
				}
				
			} else {
				if (clothingOwner.isPlayer()) {
					if(rough) {
						if(displacementDescriptionsNPC!=null
								&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo)!=null
								&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo).containsKey(dt)
								&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.REPLACEMENT_ROUGH)) {
							return UtilText.parse(clothingRemover, displacementDescriptionsNPC.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.REPLACEMENT_ROUGH));
						}
						return UtilText.parse(clothingRemover, "[npc.Name] roughly "+dt.getOppositeDescriptionThirdPerson()+" your "+this.getName()+".");
						
					} else {
						if(displacementDescriptionsNPC!=null
								&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo)!=null
								&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo).containsKey(dt)
								&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.REPLACEMENT)) {
							return UtilText.parse(clothingRemover, displacementDescriptionsNPC.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.REPLACEMENT));
						}
						return UtilText.parse(clothingRemover, "[npc.Name] "+dt.getOppositeDescriptionThirdPerson()+" your "+this.getName()+".");
					}
				} else {
					if(clothingOwner.equals(clothingRemover)) {
						if(displacementDescriptionsNPC!=null
								&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo)!=null
								&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo).containsKey(dt)
								&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.REPLACEMENT_SELF)) {
							return UtilText.parse(clothingRemover, displacementDescriptionsNPC.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.REPLACEMENT_SELF));
						}
						return UtilText.parse(clothingOwner, "[npc.Name] "+dt.getOppositeDescriptionThirdPerson()+" [npc.her] "+this.getName()+".");
						
					} else {
						if(rough) {
							if(displacementDescriptionsNPC!=null
									&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo)!=null
									&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo).containsKey(dt)
									&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.NPC_ON_NPC_REPLACEMENT_ROUGH)) {
								return UtilText.parse(clothingRemover, clothingOwner,displacementDescriptionsNPC.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.NPC_ON_NPC_REPLACEMENT_ROUGH));
							}
							return UtilText.parse(clothingRemover, clothingOwner, "[npc.Name] roughly "+dt.getOppositeDescriptionThirdPerson()+" [npc2.namePos] "+this.getName()+".");
							
						} else {
							if(displacementDescriptionsNPC!=null
									&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo)!=null
									&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo).containsKey(dt)
									&& displacementDescriptionsNPC.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.NPC_ON_NPC_REPLACEMENT)) {
								return UtilText.parse(clothingRemover, clothingOwner,displacementDescriptionsNPC.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.NPC_ON_NPC_REPLACEMENT));
							}
							return UtilText.parse(clothingRemover, clothingOwner, "[npc.Name] "+dt.getOppositeDescriptionThirdPerson()+" [npc2.namePos] "+this.getName()+".");
						}
					}
				}
			}
		}
	}
	
	public boolean isCanBeEquipped(GameCharacter clothingOwner, InventorySlot slot) {
		// This is definitely not the most efficient way of making this method, but I found it to be the most easily readable:
		if(!this.getEquipSlots().contains(slot)) {
			return false;
		}
		
		List<ItemTag> tags = this.getItemTags(slot);
		BodyPartClothingBlock block = slot.getBodyPartClothingBlock(clothingOwner);
		
		if (block != null && Collections.disjoint(block.getRequiredTags(), tags)) {
			return false;
		}
		if(tags.contains(ItemTag.FITS_ARACHNID_BODY) && clothingOwner.getLegConfiguration()!=LegConfiguration.ARACHNID) {
			return false;
		}
		if(tags.contains(ItemTag.FITS_CEPHALOPOD_BODY) && clothingOwner.getLegConfiguration()!=LegConfiguration.CEPHALOPOD) {
			return false;
		}
		if(tags.contains(ItemTag.FITS_HARPY_WINGS_EXCLUSIVE) && clothingOwner.getArmType()!=ArmType.HARPY) {
			return false;
		}
		if(tags.contains(ItemTag.FITS_HOOFS_EXCLUSIVE) && clothingOwner.getLegType().getFootType()!=FootType.HOOFS) {
			return false;
		}
		if(tags.contains(ItemTag.FITS_LONG_TAIL_BODY) && clothingOwner.getLegConfiguration()!=LegConfiguration.TAIL_LONG) {
			return false;
		}
		if(tags.contains(ItemTag.FITS_TAIL_BODY) && clothingOwner.getLegConfiguration()!=LegConfiguration.TAIL) {
			return false;
		}
		if(tags.contains(ItemTag.FITS_TALONS_EXCLUSIVE) && clothingOwner.getLegType().getFootType()!=FootType.TALONS) {
			return false;
		}
		if(tags.contains(ItemTag.FITS_TAUR_BODY) && clothingOwner.getLegConfiguration()!=LegConfiguration.TAUR) {
			return false;
		}
		if(clothingOwner.hasPenisIgnoreDildo() && tags.contains(ItemTag.REQUIRES_NO_PENIS)) {
			return false;
		}
		if(!clothingOwner.hasPenisIgnoreDildo() && tags.contains(ItemTag.REQUIRES_PENIS)) {
			return false;
		}
		if(clothingOwner.hasVagina() && tags.contains(ItemTag.REQUIRES_NO_VAGINA)) {
			return false;
		}
		if(!clothingOwner.hasVagina() && tags.contains(ItemTag.REQUIRES_VAGINA)) {
			return false;
		}
		if(!clothingOwner.isBreastFuckableNipplePenetration() && tags.contains(ItemTag.REQUIRES_FUCKABLE_NIPPLES)) {
			return false;
		}
		if(clothingOwner.getBody().getBodyMaterial().isRequiresPiercing()) {
			if(slot==InventorySlot.PIERCING_EAR && !clothingOwner.isPiercedEar()){
				return false;
		
			} else if(slot==InventorySlot.PIERCING_LIP && !clothingOwner.isPiercedLip()){
				return false;
				
			} else if(slot==InventorySlot.PIERCING_NIPPLE && !clothingOwner.isPiercedNipple()){
				return false;
				
			} else if(slot==InventorySlot.PIERCING_NOSE && !clothingOwner.isPiercedNose()){
				return false;
				
			} else if(slot==InventorySlot.PIERCING_PENIS && !clothingOwner.isPiercedPenis()){
				return false;
				
			} else if(slot==InventorySlot.PIERCING_STOMACH && !clothingOwner.isPiercedNavel()){
				return false;
				
			} else if(slot==InventorySlot.PIERCING_TONGUE && !clothingOwner.isPiercedTongue()){
				return false;
				
			} else if(slot==InventorySlot.PIERCING_VAGINA && !clothingOwner.isPiercedVagina()){
				return false;
			}
		}
		if(slot==InventorySlot.PIERCING_PENIS && !clothingOwner.hasPenisIgnoreDildo()){
			return false;
			
		} else if(slot==InventorySlot.PIERCING_VAGINA && !clothingOwner.hasVagina()){
			return false;
		}
		
		if (slot == InventorySlot.WINGS && clothingOwner.getWingType()==WingType.NONE) {
			return false;
		}
		if (slot == InventorySlot.HORNS && clothingOwner.getHornType().equals(HornType.NONE)) {
			return false;
		}
		if (slot == InventorySlot.TAIL && clothingOwner.getTailType()==TailType.NONE) {
			return false;
		}
		return true;
	}	

	public String getCannotBeEquippedText(GameCharacter clothingOwner, InventorySlot slot) { // ...
		BodyPartClothingBlock block = slot.getBodyPartClothingBlock(clothingOwner);
		List<ItemTag> tags = this.getItemTags(slot);
		
		if(!this.getEquipSlots().contains(slot)) {
			return  UtilText.parse("[style.colourBad(The "+this.getName()+" cannot be equipped into this slot!)]");
		}
		if (block != null && Collections.disjoint(block.getRequiredTags(), tags)) {
			return UtilText.parse("[style.colourBad(" + UtilText.parse(clothingOwner, block.getDescription()) + ")]");
		}
		if(tags.contains(ItemTag.FITS_ARACHNID_BODY) && clothingOwner.getLegConfiguration()!=LegConfiguration.ARACHNID) {
			return UtilText.parse(clothingOwner,"The "+this.getName()+" "+(isPlural()?"are":"is")+" only suitable for arachnid bodies, and as such, [npc.name] cannot wear "+(isPlural()?"them":"it")+".");
		}
		if(tags.contains(ItemTag.FITS_CEPHALOPOD_BODY) && clothingOwner.getLegConfiguration()!=LegConfiguration.CEPHALOPOD) {
			return UtilText.parse(clothingOwner,"The "+this.getName()+" "+(isPlural()?"are":"is")+" only suitable for cephalopod bodies, and as such, [npc.name] cannot wear "+(isPlural()?"them":"it")+".");
		}
		if(tags.contains(ItemTag.FITS_HARPY_WINGS_EXCLUSIVE) && clothingOwner.getArmType()!=ArmType.HARPY) {
			return UtilText.parse(clothingOwner,"The "+this.getName()+" "+(isPlural()?"are":"is")+" only suitable for arm-wings, and as such, [npc.name] cannot wear "+(isPlural()?"them":"it")+".");
		}
		if(tags.contains(ItemTag.FITS_HOOFS_EXCLUSIVE) && clothingOwner.getLegType().getFootType()!=FootType.HOOFS) {
			return UtilText.parse(clothingOwner,"The "+this.getName()+" "+(isPlural()?"are":"is")+" only suitable for hoofs, and as such, [npc.name] cannot wear "+(isPlural()?"them":"it")+".");
		}
		if(tags.contains(ItemTag.FITS_LONG_TAIL_BODY) && clothingOwner.getLegConfiguration()!=LegConfiguration.TAIL_LONG) {
			return UtilText.parse(clothingOwner,"The "+this.getName()+" "+(isPlural()?"are":"is")+" only suitable for long-tailed bodies, and as such, [npc.name] cannot wear "+(isPlural()?"them":"it")+".");
		}
		if(tags.contains(ItemTag.FITS_TAIL_BODY) && clothingOwner.getLegConfiguration()!=LegConfiguration.TAIL) {
			return UtilText.parse(clothingOwner,"The "+this.getName()+" "+(isPlural()?"are":"is")+" only suitable for tailed bodies, and as such, [npc.name] cannot wear "+(isPlural()?"them":"it")+".");
		}
		if(tags.contains(ItemTag.FITS_TALONS_EXCLUSIVE) && clothingOwner.getLegType().getFootType()!=FootType.TALONS) {
			return UtilText.parse(clothingOwner,"The "+this.getName()+" "+(isPlural()?"are":"is")+" only suitable for talons, and as such, [npc.name] cannot wear "+(isPlural()?"them":"it")+".");
		}
		if(tags.contains(ItemTag.FITS_TAUR_BODY) && clothingOwner.getLegConfiguration()!=LegConfiguration.TAUR) {
			return UtilText.parse(clothingOwner,"The "+this.getName()+" "+(isPlural()?"are":"is")+" only suitable for taur bodies, and as such, [npc.name] cannot wear "+(isPlural()?"them":"it")+".");
		}
		if(clothingOwner.hasPenisIgnoreDildo() && tags.contains(ItemTag.REQUIRES_NO_PENIS)) {
			return UtilText.parse(clothingOwner, "[npc.NameHasFull] a penis, which is blocking [npc.herHim] from wearing the "+this.getName()+"!");
		}
		if(!clothingOwner.hasPenisIgnoreDildo() && tags.contains(ItemTag.REQUIRES_PENIS)) {
			return UtilText.parse(clothingOwner, "[npc.Name] [npc.do]n't have a penis, so [npc.she] can't wear the "+this.getName()+"!");
		}
		if(clothingOwner.hasVagina() && tags.contains(ItemTag.REQUIRES_NO_VAGINA)) {
			return UtilText.parse(clothingOwner, "[npc.NameHasFull] a vagina, which is blocking [npc.herHim] from wearing the "+this.getName()+"!");
		}
		if(!clothingOwner.hasVagina() && tags.contains(ItemTag.REQUIRES_VAGINA)) {
			return UtilText.parse(clothingOwner, "[npc.Name] [npc.do]n't have a vagina, so [npc.she] can't wear the "+this.getName()+"!");
		}
		if(!clothingOwner.isBreastFuckableNipplePenetration() && tags.contains(ItemTag.REQUIRES_FUCKABLE_NIPPLES)) {
			return UtilText.parse(clothingOwner, "[npc.NamePos] nipples are not fuckable, so [npc.she] can't wear the "+this.getName()+"!");
		}
		if(clothingOwner.getBody().getBodyMaterial().isRequiresPiercing()) {
			if(slot==InventorySlot.PIERCING_EAR && !clothingOwner.isPiercedEar()){
				return UtilText.parse(clothingOwner, "[npc.NamePos] ears are not pierced, so [npc.she] can't wear the "+this.getName()+"!");
		
			} else if(slot==InventorySlot.PIERCING_LIP && !clothingOwner.isPiercedLip()){
				return UtilText.parse(clothingOwner, "[npc.NamePos] lips are not pierced, so [npc.she] can't wear the "+this.getName()+"!");
				
			} else if(slot==InventorySlot.PIERCING_NIPPLE && !clothingOwner.isPiercedNipple()){
				return UtilText.parse(clothingOwner, "[npc.NamePos] nipples are not pierced, so [npc.she] can't wear the "+this.getName()+"!");
				
			} else if(slot==InventorySlot.PIERCING_NOSE && !clothingOwner.isPiercedNose()){
				return UtilText.parse(clothingOwner, "[npc.NamePos] nose is not pierced, so [npc.she] can't wear the "+this.getName()+"!");
				
			} else if(slot==InventorySlot.PIERCING_PENIS && !clothingOwner.isPiercedPenis()){
				return UtilText.parse(clothingOwner, "[npc.NamePos] penis is not pierced, so [npc.she] can't wear the "+this.getName()+"!");
				
			} else if(slot==InventorySlot.PIERCING_STOMACH && !clothingOwner.isPiercedNavel()){
				return UtilText.parse(clothingOwner, "[npc.NamePos] navel is not pierced, so [npc.she] can't wear the "+this.getName()+"!");
				
			} else if(slot==InventorySlot.PIERCING_TONGUE && !clothingOwner.isPiercedTongue()){
				return UtilText.parse(clothingOwner, "[npc.NamePos] tongue is not pierced, so [npc.she] can't wear the "+this.getName()+"!");
				
			} else if(slot==InventorySlot.PIERCING_VAGINA && !clothingOwner.isPiercedVagina()){
				return UtilText.parse(clothingOwner, "[npc.NamePos] vagina is not pierced, so [npc.she] can't wear the "+this.getName()+"!");
			}
		}
		if(slot==InventorySlot.PIERCING_PENIS && !clothingOwner.hasPenisIgnoreDildo()){
			return UtilText.parse(clothingOwner, "[npc.Name] [npc.do] not have a penis, so [npc.she] can't wear the "+this.getName()+"!");
			
		} else if(slot==InventorySlot.PIERCING_VAGINA && !clothingOwner.hasVagina()){
			return UtilText.parse(clothingOwner, "[npc.Name] [npc.do] not have a vagina, so [npc.she] can't wear the "+this.getName()+"!");
		}
		
		if (slot == InventorySlot.WINGS && clothingOwner.getWingType()==WingType.NONE) {
			return UtilText.parse(clothingOwner, "[npc.Name] [npc.do] not have any wings, so [npc.she] can't wear the "+this.getName()+"!");
		}
		if (slot == InventorySlot.HORNS && clothingOwner.getHornType().equals(HornType.NONE)) {
			return UtilText.parse(clothingOwner, "[npc.Name] [npc.do] not have any horns, so [npc.she] can't wear the "+this.getName()+"!");
		}
		if (slot == InventorySlot.TAIL && clothingOwner.getTailType()==TailType.NONE) {
			return UtilText.parse(clothingOwner, "[npc.Name] [npc.do] not have a tail, so [npc.she] can't wear the "+this.getName()+"!");
		}
		return UtilText.parse(clothingOwner, "[npc.Name] cannot wear the "+this.getName()+"!");
	}
	
	public boolean isMufflesSpeech(InventorySlot slotEquippedTo) {
		return getItemTags(slotEquippedTo).contains(ItemTag.MUFFLES_SPEECH);
	}
	
	public boolean isHindersLegMovement(InventorySlot slotEquippedTo) {
		return getItemTags(slotEquippedTo).contains(ItemTag.HINDERS_LEG_MOVEMENT);
	}
	
	public boolean isHindersArmMovement(InventorySlot slotEquippedTo) {
		return getItemTags(slotEquippedTo).contains(ItemTag.HINDERS_ARM_MOVEMENT);
	}
	
	// Getters & setters:
	
	public boolean isDiscardedOnUnequip(InventorySlot slotEquippedTo) {
		return getItemTags(slotEquippedTo).contains(ItemTag.DISCARDED_WHEN_UNEQUIPPED);
	}
	
	public boolean isAbleToBeEquippedDuringSex(InventorySlot slotEquippedTo) {
		return getItemTags(slotEquippedTo).contains(ItemTag.ENABLE_SEX_EQUIP);
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

	public String getAuthorDescription() {
		return authorDescription;
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

	@Deprecated
	public InventorySlot getSlot() {
		return equipSlots.get(0);
	}
	
	public List<InventorySlot> getEquipSlots() {
		return equipSlots;
	}

	public List<BlockedParts> getBlockedPartsMap(GameCharacter character, InventorySlot slotEquippedTo) {
		List<ItemTag> tags = this.getItemTags(slotEquippedTo);
		
		if(character!=null) {
			boolean replaceCrotchBoobAccess = false;
			boolean replaceGroinAccess = false;
			switch(character.getLegConfiguration()) {
				case BIPEDAL:
				case TAIL:
				case TAIL_LONG:
				case CEPHALOPOD:
					// These are all in such a position that normal clothing conceals as normal
					break;
				case ARACHNID:
					if(!tags.contains(ItemTag.FITS_ARACHNID_BODY)) { // Arachnid-specific clothing is configured to be correct.
						// Arachnid crotch boobs are on the front, so that conceals as normal. Genitalia are not concealed.
						replaceGroinAccess = true;
					}
					break;
				case TAUR:
					if(!tags.contains(ItemTag.FITS_TAUR_BODY)) { // Taur-specific clothing is configured to be correct.
						replaceCrotchBoobAccess = true;
						replaceGroinAccess = true;
					}
					break;
			}
			if(replaceGroinAccess
					&& slotEquippedTo!=InventorySlot.ANUS
					&& slotEquippedTo!=InventorySlot.PENIS
					&& slotEquippedTo!=InventorySlot.VAGINA
					&& slotEquippedTo!=InventorySlot.PIERCING_PENIS
					&& slotEquippedTo!=InventorySlot.PIERCING_VAGINA) { // Clothing in groin slots should always be fine, so don't replace their values.
				boolean cAccess = replaceCrotchBoobAccess;
				List<BlockedParts> modifiedBlockedParts = new ArrayList<>();
				for(BlockedParts blockedparts : this.blockedPartsMap.get(slotEquippedTo)) {
					BlockedParts copy = new BlockedParts(blockedparts);
					
					copy.blockedBodyParts = copy.blockedBodyParts.stream().filter(
						bp ->
							bp!=CoverableArea.ANUS && bp!=CoverableArea.ASS
							&& bp!=CoverableArea.FEET && bp!=CoverableArea.LEGS
							&& bp!=CoverableArea.MOUND && bp!=CoverableArea.PENIS
							&& bp!=CoverableArea.TESTICLES && bp!=CoverableArea.THIGHS
							&& bp!=CoverableArea.VAGINA && (!cAccess || (bp!=CoverableArea.BREASTS_CROTCH && bp!=CoverableArea.NIPPLES_CROTCH))
						).collect(Collectors.toList());
					
					copy.clothingAccessRequired = copy.clothingAccessRequired.stream().filter(
						ca ->
							ca!=ClothingAccess.ANUS && ca!=ClothingAccess.GROIN
							&& ca!=ClothingAccess.CALVES && ca!=ClothingAccess.FEET
							&& ca!=ClothingAccess.LEGS_UP_TO_GROIN && ca!=ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL
							&& ca!=ClothingAccess.WAIST
						).collect(Collectors.toList());
					
					copy.clothingAccessBlocked = copy.clothingAccessBlocked.stream().filter(
						ca ->
							ca!=ClothingAccess.ANUS && ca!=ClothingAccess.GROIN
							&& ca!=ClothingAccess.CALVES && ca!=ClothingAccess.FEET
							&& ca!=ClothingAccess.LEGS_UP_TO_GROIN && ca!=ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL
							&& ca!=ClothingAccess.WAIST
						).collect(Collectors.toList());
					
					copy.concealedSlots = copy.concealedSlots.stream().filter(
						cs ->
							cs!=InventorySlot.ANKLE && cs!=InventorySlot.ANUS
							&& cs!=InventorySlot.FOOT && cs!=InventorySlot.GROIN
							&& cs!=InventorySlot.LEG && cs!=InventorySlot.PENIS
							&& cs!=InventorySlot.PIERCING_PENIS && cs!=InventorySlot.PIERCING_VAGINA
							&& cs!=InventorySlot.SOCK && cs!=InventorySlot.TAIL
							&& cs!=InventorySlot.VAGINA // There is no slot for crotch boobs, and is handled in CharacterInventory.isCoverableAreaExposed()
						).collect(Collectors.toList());
					
					modifiedBlockedParts.add(copy);
				}
				return modifiedBlockedParts;
			}
		}
		return this.blockedPartsMap.get(slotEquippedTo);
	}
	
	@Deprecated
	public boolean isConcealsSlot(GameCharacter character, InventorySlot slotToCheck) {
		return isConcealsSlot(character, this.getEquipSlots().get(0), slotToCheck);
	}
	
	public boolean isConcealsSlot(GameCharacter character, InventorySlot slotEquippedTo, InventorySlot slotToCheck) {
		for(BlockedParts blockedPart : this.getBlockedPartsMap(character, slotEquippedTo)) {
			if(blockedPart.concealedSlots.contains(slotToCheck) && !this.getItemTags(slotEquippedTo).contains(ItemTag.TRANSPARENT)) {
				return true;
			}
		}
		return false;
	}
	
	@Deprecated
	public boolean isConcealsCoverableArea(GameCharacter character, CoverableArea area) {
		return isConcealsCoverableArea(character, this.getEquipSlots().get(0), area);
	}
	
	public boolean isConcealsCoverableArea(GameCharacter character, InventorySlot slotEquippedTo, CoverableArea area) {
		for(BlockedParts blockedPart : this.getBlockedPartsMap(character, slotEquippedTo)) {
			if(blockedPart.blockedBodyParts.contains(area) && !this.getItemTags(slotEquippedTo).contains(ItemTag.TRANSPARENT)) {
				return true;
			}
		}
		return false;
	}

	public List<InventorySlot> getIncompatibleSlots(GameCharacter character, InventorySlot slotEquippedTo) { //TODO
		if(character!=null) {
			boolean replace = false;
			switch(character.getLegConfiguration()) {
				case BIPEDAL:
				case TAIL:
				case TAIL_LONG:
				case CEPHALOPOD:
					// These are all in such a position that normal clothing conceals as normal
					break;
				case ARACHNID:
					if(!this.getItemTags(slotEquippedTo).contains(ItemTag.FITS_ARACHNID_BODY)) { // Arachnid-specific clothing is configured to be correct.
						replace = true;
					}
					break;
				case TAUR:
					if(!this.getItemTags(slotEquippedTo).contains(ItemTag.FITS_TAUR_BODY)) { // Taur-specific clothing is configured to be correct.
						replace = true;
					}
					break;
			}
			if(replace) {
				List<InventorySlot> modifiedIncompatibleSlots = new ArrayList<>(incompatibleSlotsMap.get(slotEquippedTo));
				
				if(InventorySlot.getHumanoidSlots().contains(slotEquippedTo)) {
					modifiedIncompatibleSlots.removeIf(slot -> !InventorySlot.getHumanoidSlots().contains(slot));
				} else {
					modifiedIncompatibleSlots.removeIf(slot -> InventorySlot.getHumanoidSlots().contains(slot));
				}
				
				return modifiedIncompatibleSlots;
			}
		}
		return incompatibleSlotsMap.get(slotEquippedTo);
	}
	
	public List<DisplacementType> getBlockedPartsKeysAsListWithoutNONE(GameCharacter character, InventorySlot slotEquippedTo) {
		if(character!=null) {
			boolean replaceCrotchBoobAccess = false;
			boolean replaceGroinAccess = false;
			switch(character.getLegConfiguration()) {
				case BIPEDAL:
				case TAIL:
				case TAIL_LONG:
				case CEPHALOPOD:
					// These are all in such a position that normal clothing conceals as normal
					break;
				case ARACHNID:
					if(!this.getItemTags(slotEquippedTo).contains(ItemTag.FITS_ARACHNID_BODY)) { // Arachnid-specific clothing is configured to be correct.
						// Arachnid crotch boobs are on the front, so that conceals as normal. Genitalia are not concealed.
						replaceGroinAccess = true;
					}
					break;
				case TAUR:
					if(!this.getItemTags(slotEquippedTo).contains(ItemTag.FITS_TAUR_BODY)) { // Taur-specific clothing is configured to be correct.
						replaceCrotchBoobAccess = true;
						replaceGroinAccess = true;
					}
					break;
			}
			if(replaceGroinAccess
					&& slotEquippedTo!=InventorySlot.ANUS
					&& slotEquippedTo!=InventorySlot.PENIS
					&& slotEquippedTo!=InventorySlot.VAGINA
					&& slotEquippedTo!=InventorySlot.PIERCING_PENIS
					&& slotEquippedTo!=InventorySlot.PIERCING_VAGINA) { // Clothing in groin slots should always be fine, so don't replace their values.
				boolean cAccess = replaceCrotchBoobAccess;
				List<BlockedParts> modifiedBlockedParts = new ArrayList<>();
				for(BlockedParts blockedparts : blockedPartsMap.get(slotEquippedTo)) {
					BlockedParts copy = new BlockedParts(blockedparts);
					
					copy.blockedBodyParts = copy.blockedBodyParts.stream().filter(
						bp ->
							bp!=CoverableArea.ANUS && bp!=CoverableArea.ASS
							&& bp!=CoverableArea.FEET && bp!=CoverableArea.LEGS
							&& bp!=CoverableArea.MOUND && bp!=CoverableArea.PENIS
							&& bp!=CoverableArea.TESTICLES && bp!=CoverableArea.THIGHS
							&& bp!=CoverableArea.VAGINA && (!cAccess || (bp!=CoverableArea.BREASTS_CROTCH && bp!=CoverableArea.NIPPLES_CROTCH))
						).collect(Collectors.toList());
					
					copy.clothingAccessRequired = copy.clothingAccessRequired.stream().filter(
						ca ->
							ca!=ClothingAccess.ANUS && ca!=ClothingAccess.GROIN
							&& ca!=ClothingAccess.CALVES && ca!=ClothingAccess.FEET
							&& ca!=ClothingAccess.LEGS_UP_TO_GROIN && ca!=ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL
							&& ca!=ClothingAccess.WAIST
						).collect(Collectors.toList());
					
					copy.clothingAccessBlocked = copy.clothingAccessBlocked.stream().filter(
						ca ->
							ca!=ClothingAccess.ANUS && ca!=ClothingAccess.GROIN
							&& ca!=ClothingAccess.CALVES && ca!=ClothingAccess.FEET
							&& ca!=ClothingAccess.LEGS_UP_TO_GROIN && ca!=ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL
							&& ca!=ClothingAccess.WAIST
						).collect(Collectors.toList());
					
					copy.concealedSlots = copy.concealedSlots.stream().filter(
						cs ->
							cs!=InventorySlot.ANKLE && cs!=InventorySlot.ANUS
							&& cs!=InventorySlot.FOOT && cs!=InventorySlot.GROIN
							&& cs!=InventorySlot.LEG && cs!=InventorySlot.PENIS
							&& cs!=InventorySlot.PIERCING_PENIS && cs!=InventorySlot.PIERCING_VAGINA
							&& cs!=InventorySlot.SOCK && cs!=InventorySlot.TAIL
							&& cs!=InventorySlot.VAGINA // There is no slot for crotch boobs, and is handled in CharacterInventory.isCoverableAreaExposed()
						).collect(Collectors.toList());
					
					modifiedBlockedParts.add(copy);
				}
				List<DisplacementType> moddedDisplacementTypesAvailableWithoutNONE = new ArrayList<>();
				for (BlockedParts bp : modifiedBlockedParts) {
					if (bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
						moddedDisplacementTypesAvailableWithoutNONE.add(bp.displacementType);
					}
				}
				return moddedDisplacementTypesAvailableWithoutNONE;
			}
		}
		return displacementTypesAvailableWithoutNONE.get(slotEquippedTo);
	}
	
	public String getPathName() {
		return pathName;
	}

	public String getPathNameEquipped(InventorySlot invSlot) {
		return pathNameEquipped.get(invSlot);
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
	
	private void addSVGStringMapping(InventorySlot slotEquippedTo, Colour colour, Colour colourSecondary, Colour colourTertiary, String pattern, Colour patternColourPrimary, Colour patternColourSecondary, Colour patternColourTertiary, String s) {
		if(pattern == null) {
			pattern = "none"; // The map does not contain null as a key.
		}
		
		initMapIfAbsent(SVGStringMap, slotEquippedTo, colour, colourSecondary, colourTertiary, pattern, patternColourPrimary, patternColourSecondary, patternColourTertiary);
		
		SVGStringMap.get(slotEquippedTo).get(colour).get(colourSecondary).get(colourTertiary).get(pattern).get(patternColourPrimary).get(patternColourSecondary).put(patternColourTertiary, s);
	}
	
	private void initMapIfAbsent(Map<InventorySlot, Map<Colour, Map<Colour, Map<Colour, Map<String, Map<Colour, Map<Colour, Map<Colour, String>>>>>>>> map, 
			InventorySlot slotEquippedTo, Colour colour, Colour colourSecondary, Colour colourTertiary, String pattern, Colour patternColourPrimary, Colour patternColourSecondary, Colour patternColourTertiary) {
		
		map.putIfAbsent(slotEquippedTo, new HashMap<>());
		map.get(slotEquippedTo).putIfAbsent(colour, new HashMap<>());
		map.get(slotEquippedTo).get(colour).putIfAbsent(colourSecondary, new HashMap<>());
		map.get(slotEquippedTo).get(colour).get(colourSecondary).putIfAbsent(colourTertiary, new HashMap<>());
		map.get(slotEquippedTo).get(colour).get(colourSecondary).get(colourTertiary).putIfAbsent(pattern, new HashMap<>());
		map.get(slotEquippedTo).get(colour).get(colourSecondary).get(colourTertiary).get(pattern).putIfAbsent(patternColourPrimary, new HashMap<>());
		map.get(slotEquippedTo).get(colour).get(colourSecondary).get(colourTertiary).get(pattern).get(patternColourPrimary).putIfAbsent(patternColourSecondary, new HashMap<>());
	}
	
	private void addSVGStringEquippedMapping(InventorySlot slotEquippedTo, Colour colour, Colour colourSecondary, Colour colourTertiary, String pattern, Colour patternColourPrimary, Colour patternColourSecondary, Colour patternColourTertiary, String s) {
		if(pattern == null) {
			pattern = "none"; // The map does not contain null as a key.
		}

		initMapIfAbsent(SVGStringEquippedMap, slotEquippedTo, colour, colourSecondary, colourTertiary, pattern, patternColourPrimary, patternColourSecondary, patternColourTertiary);
		
		SVGStringEquippedMap.get(slotEquippedTo).get(colour).get(colourSecondary).get(colourTertiary).get(pattern).get(patternColourPrimary).get(patternColourSecondary).put(patternColourTertiary, s);
	}
	
	
	private String getSVGStringFromMap(InventorySlot slotEquippedTo, Colour colour, Colour colourSecondary, Colour colourTertiary, String pattern, Colour patternColourPrimary, Colour patternColourSecondary, Colour patternColourTertiary) {
		if(pattern == null) {
			pattern = "none"; // The map does not contain null as a key.
		}
		initMapIfAbsent(SVGStringMap, slotEquippedTo, colour, colourSecondary, colourTertiary, pattern, patternColourPrimary, patternColourSecondary, patternColourTertiary);
		return SVGStringMap.get(slotEquippedTo).get(colour).get(colourSecondary).get(colourTertiary).get(pattern).get(patternColourPrimary).get(patternColourSecondary).get(patternColourTertiary);
	}
	
	private String getSVGStringFromEquippedMap(InventorySlot slotEquippedTo, Colour colour, Colour colourSecondary, Colour colourTertiary, String pattern, Colour patternColourPrimary, Colour patternColourSecondary, Colour patternColourTertiary) {
		if(pattern == null) {
			pattern = "none"; // The map does not contain null as a key.
		}
		initMapIfAbsent(SVGStringEquippedMap, slotEquippedTo, colour, colourSecondary, colourTertiary, pattern, patternColourPrimary, patternColourSecondary, patternColourTertiary);
		return SVGStringEquippedMap.get(slotEquippedTo).get(colour).get(colourSecondary).get(colourTertiary).get(pattern).get(patternColourPrimary).get(patternColourSecondary).get(patternColourTertiary);
	}

	public String getSVGImage() {
		Colour pColour = DEFAULT_COLOUR_VALUE;
		if(this.getAllAvailablePrimaryColours()!=null && !this.getAllAvailablePrimaryColours().isEmpty()) {
			pColour = this.getAllAvailablePrimaryColours().get(0);
		}
		Colour sColour = DEFAULT_COLOUR_VALUE;
		if(this.getAllAvailableSecondaryColours()!=null && !this.getAllAvailableSecondaryColours().isEmpty()) {
			sColour = this.getAllAvailableSecondaryColours().get(0);
		}
		Colour tColour = DEFAULT_COLOUR_VALUE;
		if(this.getAllAvailableTertiaryColours()!=null && !this.getAllAvailableTertiaryColours().isEmpty()) {
			tColour = this.getAllAvailableTertiaryColours().get(0);
		}
		
		return getSVGImage(null, this.getEquipSlots().get(0), pColour, sColour, tColour, false, null, null, null, null);
	}
	
	/**
	 * @param colour You need to pass a colour in here.
	 * @param colourSecondary This can be null.
	 * @param colourTertiary This can be null.
	 */
	public String getSVGImage(InventorySlot slotEquippedTo, Colour colour, Colour colourSecondary, Colour colourTertiary, String pattern, Colour patternColour, Colour patternSecondaryColour, Colour patternTertiaryColour) {
		return getSVGImage(null, slotEquippedTo, colour, colourSecondary, colourTertiary, false, pattern, patternColour, patternSecondaryColour, patternTertiaryColour);
	}
	
	/**
	 * @param character The character this clothing is equipped to.
	 * @param colour You need to pass a colour in here.
	 * @param colourSecondary This can be null.
	 * @param colourTertiary This can be null.
	 */
	public String getSVGEquippedImage(GameCharacter character, InventorySlot slotEquippedTo, Colour colour, Colour colourSecondary, Colour colourTertiary, String pattern, Colour patternColour, Colour patternSecondaryColour, Colour patternTertiaryColour) {
		return getSVGImage(character, slotEquippedTo, colour, colourSecondary, colourTertiary, true, pattern, patternColour, patternSecondaryColour, patternTertiaryColour);
	}
	
	private String getSVGImage(GameCharacter character, InventorySlot slotEquippedTo, Colour colour, Colour colourSecondary, Colour colourTertiary, boolean equippedVariant, String pattern, Colour patternColour, Colour patternSecondaryColour, Colour patternTertiaryColour) {
		if (!allAvailablePrimaryColours.contains(colour)) {
			return "";
		}
		
		if(this.equals(ClothingType.HIPS_CONDOMS)) {
			if (getAllAvailablePrimaryColours().contains(colour)) {
				try {
					InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/belt_used_condoms_base_back.svg");
					String s = "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;padding:0;margin:0'>"+Util.inputStreamToString(is)+"</div>";
					is.close();
					s = getSVGWithHandledPattern(s, pattern, patternColour, patternSecondaryColour, patternTertiaryColour);
					s = SvgUtil.colourReplacement(this.getId(), colour, colourSecondary, colourTertiary, s);

					if(!equippedVariant) {
						is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/belt_used_condoms_base_front.svg");
						s += "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;padding:0;margin:0'>" + Util.inputStreamToString(is) + "</div>";
						s = getSVGWithHandledPattern(s, pattern, patternColour, patternSecondaryColour, patternTertiaryColour);
						s = SvgUtil.colourReplacement(this.getId(), colour, colourSecondary, colourTertiary, s);
						is.close();
						
						addSVGStringEquippedMapping(slotEquippedTo, colour, colourSecondary, colourTertiary, pattern, patternColour, patternSecondaryColour, patternTertiaryColour, s);
						
						return s;
						
					} else {
						List<Colour> condomColours = new ArrayList<>();
						// Draw all backs:
						for(AbstractItem item : character.getAllItemsInInventory().keySet()) {
							if(item.getItemType().equals(ItemType.CONDOM_USED)) {
								for(int count=0; count<character.getItemCount(item); count++) {
									if(condomColours.size()<8) {
										condomColours.add(item.getColour());
										
										is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/belt_used_condoms_"+condomColours.size()+"_back.svg");
										s += "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;padding:0;margin:0'>" + Util.inputStreamToString(is) + "</div>";
										s = getSVGWithHandledPattern(s, pattern, patternColour, patternSecondaryColour, patternTertiaryColour);
										s = SvgUtil.colourReplacement(this.getId(), item.getColour(), null, null, s);
										is.close();
									}
								}
							}
						}
						
						is.close();
						is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/belt_used_condoms_base_front.svg");
						s += "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;padding:0;margin:0'>" + Util.inputStreamToString(is) + "</div>";
						s = getSVGWithHandledPattern(s, pattern, patternColour, patternSecondaryColour, patternTertiaryColour);
						s = SvgUtil.colourReplacement(this.getId(), colour, colourSecondary, colourTertiary, s);
						is.close();
						
						int i = 1;
						for(Colour c : condomColours) {
							is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/belt_used_condoms_"+i+"_front.svg");
							s += "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;padding:0;margin:0'>" + Util.inputStreamToString(is) + "</div>";
							s = getSVGWithHandledPattern(s, pattern, patternColour, patternSecondaryColour, patternTertiaryColour);
							s = SvgUtil.colourReplacement(this.getId(), c, null, null, s);
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
			if(equippedVariant && pathNameEquipped!=null && pathNameEquipped.get(slotEquippedTo)!=null) {
				String stringFromMap = getSVGStringFromEquippedMap(slotEquippedTo, colour, colourSecondary, colourTertiary, pattern, patternColour, patternSecondaryColour, patternTertiaryColour);
				if (stringFromMap!=null && !this.equals(ClothingType.WRIST_WOMENS_WATCH) && !this.equals(ClothingType.WRIST_MENS_WATCH)) {
					return stringFromMap;
					
				} else {
					if (getAllAvailablePrimaryColours().contains(colour)) {
						try {
							InputStream is;
							String s;
							if(isMod) {
								List<String> lines = Files.readAllLines(Paths.get(pathNameEquipped.get(slotEquippedTo)));
								StringBuilder sb = new StringBuilder();
								for(String line : lines) {
									sb.append(line);
								}
								s = sb.toString();
							} else {
								is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/" + pathNameEquipped.get(slotEquippedTo) + ".svg");
								s = Util.inputStreamToString(is);
								is.close();
							}
							
							s = getSVGWithHandledPattern(s, pattern, patternColour, patternSecondaryColour, patternTertiaryColour);
							
							s = SvgUtil.colourReplacement(this.getId(), colour, colourSecondary, colourTertiary, s);
							
							// Add minute and hour hands to women's and men's watches:
							s += (this.equals(ClothingType.WRIST_WOMENS_WATCH)
									? "<div style='width:100%;height:100%;position:absolute;left:0;top:0;-webkit-transform: rotate(" + ((Main.game.getMinutesPassed() % (60 * 24)) / 2f) + "deg);'>"
										+ SVGImages.SVG_IMAGE_PROVIDER.getWomensWatchHourHand() + "</div>"
										+ "<div style='width:100%;height:100%;position:absolute;left:0;top:0;-webkit-transform: rotate(" + (Main.game.getMinutesPassed() % (60)) * 6 + "deg);'>"
										+ SVGImages.SVG_IMAGE_PROVIDER.getWomensWatchMinuteHand() + "</div>"
									: "")
									+ (this.equals(ClothingType.WRIST_MENS_WATCH)
										? "<div style='width:100%;height:100%;position:absolute;left:0;top:0;-webkit-transform: rotate(" + ((Main.game.getMinutesPassed() % (60 * 24)) / 2f) + "deg);'>"
											+ SVGImages.SVG_IMAGE_PROVIDER.getMensWatchHourHand() + "</div>"
											+ "<div style='width:100%;height:100%;position:absolute;left:0;top:0;-webkit-transform: rotate(" + (Main.game.getMinutesPassed() % (60)) * 6
											+ "deg);'>" + SVGImages.SVG_IMAGE_PROVIDER.getMensWatchMinuteHand() + "</div>"
										: "");

							addSVGStringEquippedMapping(slotEquippedTo, colour, colourSecondary, colourTertiary, pattern, patternColour, patternSecondaryColour, patternTertiaryColour, s);
							
							return s;
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				
			} else {
				String stringFromMap = getSVGStringFromMap(slotEquippedTo, colour, colourSecondary, colourTertiary, pattern, patternColour, patternSecondaryColour, patternTertiaryColour);
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
								is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/" + pathName + ".svg");
								s = Util.inputStreamToString(is);
								is.close();
							}
							
							s = getSVGWithHandledPattern(s, pattern, patternColour, patternSecondaryColour, patternTertiaryColour);
							
							s = SvgUtil.colourReplacement(this.getId(), colour, colourSecondary, colourTertiary, s);
							
							// Add minute and hour hands to women's and men's watches:
							s += (this.equals(ClothingType.WRIST_WOMENS_WATCH)
									? "<div style='width:100%;height:100%;position:absolute;left:0;top:0;-webkit-transform: rotate(" + ((Main.game.getMinutesPassed() % (60 * 24)) / 2f) + "deg);'>"
										+ SVGImages.SVG_IMAGE_PROVIDER.getWomensWatchHourHand() + "</div>"
										+ "<div style='width:100%;height:100%;position:absolute;left:0;top:0;-webkit-transform: rotate(" + (Main.game.getMinutesPassed() % (60)) * 6 + "deg);'>"
										+ SVGImages.SVG_IMAGE_PROVIDER.getWomensWatchMinuteHand() + "</div>"
									: "")
									+ (this.equals(ClothingType.WRIST_MENS_WATCH)
										? "<div style='width:100%;height:100%;position:absolute;left:0;top:0;-webkit-transform: rotate(" + ((Main.game.getMinutesPassed() % (60 * 24)) / 2f) + "deg);'>"
											+ SVGImages.SVG_IMAGE_PROVIDER.getMensWatchHourHand() + "</div>"
											+ "<div style='width:100%;height:100%;position:absolute;left:0;top:0;-webkit-transform: rotate(" + (Main.game.getMinutesPassed() % (60)) * 6
											+ "deg);'>" + SVGImages.SVG_IMAGE_PROVIDER.getMensWatchMinuteHand() + "</div>"
										: "");
							
							addSVGStringMapping(slotEquippedTo, colour, colourSecondary, colourTertiary, pattern, patternColour, patternSecondaryColour, patternTertiaryColour, s);
		
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
	
	private String getSVGWithHandledPattern(String s, String pattern, Colour patternColour, Colour patternSecondaryColour, Colour patternTertiaryColour) {
		isPatternAvailableInitCompleted = true;
		if(!s.contains("patternLayer")) { // Making sure that the pattern layer exists.
			return s;
		}
		
		if(!this.isPatternAvailable) {
			this.isPatternAvailable = true;
		}
		
		if(pattern == null || pattern.equals("none")) {
			return s; // No pattern - no need to adjust anything.
		}
		
		String returnable;
		
		// Locating the "patternLayer".
		int patternLayerStartIndex = s.indexOf("patternLayer");
		int patternLayerEndIndex = s.indexOf("</g>", patternLayerStartIndex);
		
		// Setting up clip mask
		String newClipMask = "<clipPath id=\"internalPatternClip\">";
		
		int firstShapeStartIndex = s.indexOf("<path", patternLayerStartIndex);
		int lastShapeEndIndex = firstShapeStartIndex;
		
		boolean continueSetUp = true;
		
		while(continueSetUp){
			int currentShapeStartIndex = s.indexOf("<path", lastShapeEndIndex);
			int currentShapeEndIndex = s.indexOf("/>", currentShapeStartIndex);
			
			if(currentShapeEndIndex > patternLayerEndIndex || currentShapeEndIndex == -1 || currentShapeStartIndex == -1) {
				continueSetUp = false;
			} else {
				newClipMask = newClipMask + s.substring(currentShapeStartIndex, currentShapeEndIndex) + " />";
				lastShapeEndIndex = currentShapeEndIndex;
			}
		}
		
		newClipMask = newClipMask + "</clipPath>";
		
		//System.out.print(newClipMask);
		
		// Adding clip mask to the returned string.
		int defIndex = s.indexOf("<defs");
		int defEndIndex;
		if (defIndex > 0) {
			// Replace defs
			returnable = s.substring(0, defIndex) + "<defs>" + newClipMask;
			defEndIndex = s.indexOf("</defs>");
		} else {
			// Insert defs
			returnable = s.substring(0, s.indexOf('>')) + "><defs>" + newClipMask + "</defs>";
			defEndIndex = s.indexOf('>') + 1;
		}
		
		// Loading pattern
		String loadedPattern;
		try {
			loadedPattern = Pattern.getPattern(pattern).getSVGString(patternColour, patternSecondaryColour, patternTertiaryColour);
		} catch(Exception ex) {
			System.err.println("Error in pattern loading method getSVGWithHandledPattern(). Clothing causing error: "+this.getName());
			return s;
		}
		// Getting shapes from the pattern
		String newPattern = "";
		
		int firstPatternShapeStartIndex = loadedPattern.indexOf("<path");
		int firstRectStartIndex = loadedPattern.indexOf("<rect");
		if((firstRectStartIndex != -1 && firstRectStartIndex < firstPatternShapeStartIndex) || firstPatternShapeStartIndex == -1) {
			firstPatternShapeStartIndex = firstRectStartIndex;
		}
		int lastPatternShapeEndIndex = firstPatternShapeStartIndex;
		
		boolean continuePatternSetUp = true;
		
		while(continuePatternSetUp){
			int currentShapeStartIndex = loadedPattern.indexOf("<path", lastPatternShapeEndIndex);
			int currentRectStartIndex = loadedPattern.indexOf("<rect", lastPatternShapeEndIndex);
			if((currentRectStartIndex != -1 && currentRectStartIndex < currentShapeStartIndex) || currentShapeStartIndex == -1) {
				currentShapeStartIndex = currentRectStartIndex;
			}
			int currentShapeEndIndex = loadedPattern.indexOf("/>", currentShapeStartIndex);
			
			if(currentShapeEndIndex == -1 || currentShapeStartIndex == -1) {
				continuePatternSetUp = false;
			} else {
				newPattern = newPattern
						+ loadedPattern.substring(currentShapeStartIndex, currentShapeEndIndex)
						+ "clip-path=\"url(#internalPatternClip)\""
						+ "/>";
				lastPatternShapeEndIndex = currentShapeEndIndex;
			}
		}

		returnable = returnable + s.substring(defEndIndex, firstShapeStartIndex)
				+ newPattern
				+ s.substring(patternLayerEndIndex);
		
		//System.out.print(returnable);
		
		return returnable;
	}
	
	public boolean isPatternAvailable() {
		if(!isPatternAvailable && !isPatternAvailableInitCompleted) {
			isPatternAvailable = this.getSVGImage().contains("patternLayer");
			isPatternAvailableInitCompleted = true;
		}
		return this.isPatternAvailable;
	}

	public float getPatternChance() {
		return patternChance;
	}

	public List<Pattern> getDefaultPatterns() {
		return defaultPatterns;
	}

	public List<Colour> getAvailablePatternPrimaryColours() {
		return availablePatternPrimaryColours;
	}

	public List<Colour> getAvailablePatternSecondaryColours() {
		return availablePatternSecondaryColours;
	}

	public List<Colour> getAvailablePatternTertiaryColours() {
		return availablePatternTertiaryColours;
	}

	public List<Colour> getAvailablePatternPrimaryDyeColours() {
		return availablePatternPrimaryDyeColours;
	}

	public List<Colour> getAllAvailablePatternPrimaryColours() {
		return allAvailablePatternPrimaryColours;
	}

	public List<Colour> getAvailablePatternSecondaryDyeColours() {
		return availablePatternSecondaryDyeColours;
	}

	public List<Colour> getAllAvailablePatternSecondaryColours() {
		return allAvailablePatternSecondaryColours;
	}

	public List<Colour> getAvailablePatternTertiaryDyeColours() {
		return availablePatternTertiaryDyeColours;
	}

	public List<Colour> getAllAvailablePatternTertiaryColours() {
		return allAvailablePatternTertiaryColours;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public List<ItemEffect> getEffects() {
		return effects;
	}
	
	public boolean isAbleToBeSold() {
		return getRarity()!=Rarity.QUEST;
	}
	
	public boolean isAbleToBeDropped() {
		return getRarity()!=Rarity.QUEST;
	}

	public float getPhysicalResistance() {
		return physicalResistance;
	}

	// Enchantments:
	
	public int getEnchantmentLimit() {
		return 100;
//		if(enchantmentLimit==-1) {
//			int base = (getClothingSet()==null?5:10);
//			return base + getIncompatibleSlots(null).size()*base;
//		} else {
//			return enchantmentLimit;
//		}
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

//	public Map<InventorySlot, List<ItemTag>> getItemTags() {
//		return itemTags;
//	}

	public List<ItemTag> getItemTags(InventorySlot slotEquippedTo) {
		if(!itemTags.containsKey(slotEquippedTo)) {
			return new ArrayList<>();
		}
		return itemTags.get(slotEquippedTo);
	}

	public List<ItemTag> getDefaultItemTags() {
		return itemTags.get(this.equipSlots.get(0));
	}
	
	public boolean isSexToy(InventorySlot slotEquippedTo) {
		for(ItemTag tag : this.getItemTags(slotEquippedTo)) {
			if(tag.isSexToy()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isCondom(InventorySlot slotEquippedTo) {
		return getItemTags(slotEquippedTo).contains(ItemTag.CONDOM);
	}
	
	public boolean isTransparent(InventorySlot slotEquippedTo) {
		return getItemTags(slotEquippedTo).contains(ItemTag.TRANSPARENT);
	}

	public boolean isColourDerivedFromPattern() {
		return isColourDerivedFromPattern;
	}
}
