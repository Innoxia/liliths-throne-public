package com.lilithsthrone.game.inventory.clothing;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import com.lilithsthrone.game.character.body.tags.ArmTypeTag;
import com.lilithsthrone.game.character.body.types.FootType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.AbstractSetBonus;
import com.lilithsthrone.game.inventory.ColourReplacement;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.SetBonus;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Pattern;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.ColourListPresets;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.84
 * @version 0.3.8.2
 * @author Innoxia, BlazingMagpie@gmail.com (or ping BlazingMagpie in Discord), Pimgd
 */
public abstract class AbstractClothingType extends AbstractCoreType {
	
	public static final Colour DEFAULT_COLOUR_VALUE = PresetColour.CLOTHING_BLACK;
	
	private String determiner;
	private String name;
	private String namePlural;
	private String description;
	private String pathName;
	private Map<InventorySlot, String> pathNameEquipped;
	private String authorDescription;
	
	private boolean appendColourName;
	private boolean plural;
	private boolean isMod;
	private boolean isColourDerivedFromPattern;
	private int baseValue;
	private float physicalResistance;
	private int femininityMinimum;
	private int femininityMaximum;
	private Femininity femininityRestriction;
	private List<InventorySlot> equipSlots;
	
	// Penetration variables:
	private int penetrationSelfLength;
	private int penetrationSelfGirth;
	private Set<PenetrationModifier> penetrationSelfModifiers;
	
	private int penetrationOtherLength;
	private int penetrationOtherGirth;
	private Set<PenetrationModifier> penetrationOtherModifiers;
	
	// Orifice variables:
	private int orificeSelfDepth;
	private float orificeSelfCapacity;
	private int orificeSelfElasticity;
	private int orificeSelfPlasticity;
	private int orificeSelfWetness;
	private Set<OrificeModifier> orificeSelfModifiers;
	
	private int orificeOtherDepth;
	private float orificeOtherCapacity;
	private int orificeOtherElasticity;
	private int orificeOtherPlasticity;
	private int orificeOtherWetness;
	private Set<OrificeModifier> orificeOtherModifiers;

	// Enchantments:
	@SuppressWarnings("unused")
	private int enchantmentLimit; // Removed as part of 0.3.3.7's update to add enchantment capacity mechanics.
	protected List<ItemEffect> effects;

	// Images:
	private Map<String, String> SVGStringMap;
	private Map<String, String> SVGStringEquippedMap;
	
	// Pattern data:
	private boolean isPatternAvailable;
	private boolean isPatternAvailableInitCompleted;
	
	// Access and block stuff:
	private Map<InventorySlot, List<BlockedParts>> blockedPartsMap;
	private Map<InventorySlot, List<InventorySlot>> incompatibleSlotsMap;
	
	private AbstractSetBonus clothingSet;
	private Rarity rarity;
	private List<ColourReplacement> colourReplacements;
	/** Key is the colour index which should copy another colour upon weapon generation. Value is the colour index which should be copied. */
	public Map<Integer, Integer> copyGenerationColours;

	// Patterns:
	private float patternChance;
	private List<Pattern> defaultPatterns;
	private List<ColourReplacement> patternColourReplacements;

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
			AbstractSetBonus clothingSet,
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
			AbstractSetBonus clothingSet,
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
		
		this.appendColourName = true;
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

		copyGenerationColours = new HashMap<>();
		
		setUpColours(true,
				availablePrimaryColours,
				availablePrimaryDyeColours,
				true,
				availableSecondaryColours,
				availableSecondaryDyeColours,
				true,
				availableTertiaryColours,
				availableTertiaryDyeColours);
		
		patternChance = 0;
		defaultPatterns = new ArrayList<>(Pattern.getAllDefaultPatterns().values());
		
		setUpPatternColours(null, null, null, null, null, null);
		populateEmptyPatternColours();

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
			
			if(!coreAttributes.getMandatoryFirstOf("name").getAttribute("appendColourName").isEmpty()) {
				this.appendColourName  =  Boolean.valueOf(coreAttributes.getMandatoryFirstOf("name").getAttribute("appendColourName"));
			} else {
				this.appendColourName = true;
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
			for(Element itemTagsElement : coreAttributes.getAllOf("itemTags")) {
				if(itemTagsElement.getAttribute("slot").isEmpty()) {
					for(InventorySlot slot : this.equipSlots) {
						this.itemTags.putIfAbsent(slot, new ArrayList<>());
						this.itemTags.get(slot).addAll(Util.toEnumList(itemTagsElement.getAllOf("tag"), ItemTag.class));
					}
					for(Element e : itemTagsElement.getAllOf("tag")) { // Support for modded clothing which used DILDO tags before v0.3.7's switch to more precise variables:
						boolean found = false;
						switch(e.getTextContent()) {
							case "DILDO_TINY":
								this.penetrationOtherLength = 8;
								found = true;
								break;
							case "DILDO_AVERAGE":
								this.penetrationOtherLength = 15;
								found = true;
								break;
							case "DILDO_LARGE":
								this.penetrationOtherLength = 25;
								found = true;
								break;
							case "DILDO_HUGE":
								this.penetrationOtherLength = 35;
								found = true;
								break;
							case "DILDO_ENORMOUS":
								this.penetrationOtherLength = 45;
								found = true;
								break;
							case "DILDO_GIGANTIC":
								this.penetrationOtherLength = 55;
								found = true;
								break;
							case "DILDO_STALLION":
								this.penetrationOtherLength = 81;
								found = true;
								break;
						}
						if(found) {
							this.penetrationSelfGirth = 2;
							for(InventorySlot slot : this.equipSlots) {
								this.itemTags.get(slot).add(ItemTag.DILDO_OTHER);
							}
						}
					}
					
				} else {
					InventorySlot relatedSlot = InventorySlot.valueOf(itemTagsElement.getAttribute("slot"));
					this.itemTags.putIfAbsent(relatedSlot, new ArrayList<>());
					this.itemTags.get(relatedSlot).addAll(Util.toEnumList(itemTagsElement.getAllOf("tag"), ItemTag.class));
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
				.map(Element::getTextContent).map(SetBonus::getSetBonusFromId)
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
					if(values.isEmpty()) { //TODO this is causing issues in enchanting for some reason...
						List<Colour> colours = new ArrayList<>();
						for(Element element : colorsElement.getAllOf("colour")) {
							colours.add(PresetColour.getColourFromId(element.getTextContent()));
						}
						return colours;
//						return colorsElement.getAllOf("colour").stream()
//								.map(Element::getTextContent).map(PresetColour::getColourFromId)
//								.collect(Collectors.toList());
//						return ColourListPresets.getColourListFromId("ALL_WITH_METALS");
					} else {
						return ColourListPresets.getColourListFromId(values);
					}
					
				} catch (Exception e) {
					System.err.println("AAAAAAAAAAAAA");
					printHelpfulErrorForEnumValueMismatches(e);
					throw new IllegalStateException("Colour tag reading failure: "+colorsElement.getTagName()+" " + e.getMessage(), e);
				}
			};

			copyGenerationColours = new HashMap<>();
			
			boolean primaryRecolourAllowed = true;
			if(coreAttributes.getOptionalFirstOf("primaryColours").isPresent() && !coreAttributes.getMandatoryFirstOf("primaryColours").getAttribute("recolouringAllowed").isEmpty()) {
				primaryRecolourAllowed = Boolean.valueOf(coreAttributes.getMandatoryFirstOf("primaryColours").getAttribute("recolouringAllowed"));
			}
			if(coreAttributes.getOptionalFirstOf("primaryColours").isPresent() && !coreAttributes.getMandatoryFirstOf("primaryColours").getAttribute("copyColourIndex").isEmpty()) {
				copyGenerationColours.put(0, Integer.valueOf(coreAttributes.getMandatoryFirstOf("primaryColours").getAttribute("copyColourIndex")));
			}
			List<Colour> importedPrimaryColours = coreAttributes.getOptionalFirstOf("primaryColours")
				.map(getColoursFromElement::apply)
				.orElseGet(ArrayList::new);
			List<Colour> importedPrimaryColoursDye = coreAttributes.getOptionalFirstOf("primaryColoursDye")
				.map(getColoursFromElement::apply)
				.orElseGet(ArrayList::new);

			boolean secondaryRecolourAllowed = true;
			if(coreAttributes.getOptionalFirstOf("secondaryColours").isPresent() && !coreAttributes.getMandatoryFirstOf("secondaryColours").getAttribute("recolouringAllowed").isEmpty()) {
				secondaryRecolourAllowed = Boolean.valueOf(coreAttributes.getMandatoryFirstOf("secondaryColours").getAttribute("recolouringAllowed"));
			}
			if(coreAttributes.getOptionalFirstOf("secondaryColours").isPresent() && !coreAttributes.getMandatoryFirstOf("secondaryColours").getAttribute("copyColourIndex").isEmpty()) {
				copyGenerationColours.put(1, Integer.valueOf(coreAttributes.getMandatoryFirstOf("secondaryColours").getAttribute("copyColourIndex")));
			}
			List<Colour> importedSecondaryColours = coreAttributes.getOptionalFirstOf("secondaryColours")
				.map(getColoursFromElement::apply)
				.orElseGet(ArrayList::new);
			List<Colour> importedSecondaryColoursDye = coreAttributes.getOptionalFirstOf("secondaryColoursDye")
				.map(getColoursFromElement::apply)
				.orElseGet(ArrayList::new);

			boolean tertiaryRecolourAllowed = true;
			if(coreAttributes.getOptionalFirstOf("tertiaryColours").isPresent() && !coreAttributes.getMandatoryFirstOf("tertiaryColours").getAttribute("recolouringAllowed").isEmpty()) {
				tertiaryRecolourAllowed = Boolean.valueOf(coreAttributes.getMandatoryFirstOf("tertiaryColours").getAttribute("recolouringAllowed"));
			}
			if(coreAttributes.getOptionalFirstOf("tertiaryColours").isPresent() && !coreAttributes.getMandatoryFirstOf("tertiaryColours").getAttribute("copyColourIndex").isEmpty()) {
				copyGenerationColours.put(2, Integer.valueOf(coreAttributes.getMandatoryFirstOf("tertiaryColours").getAttribute("copyColourIndex")));
			}
			List<Colour> importedTertiaryColours = coreAttributes.getOptionalFirstOf("tertiaryColours")
				.map(getColoursFromElement::apply)
				.orElseGet(ArrayList::new);
			List<Colour> importedTertiaryColoursDye = coreAttributes.getOptionalFirstOf("tertiaryColoursDye")
				.map(getColoursFromElement::apply)
				.orElseGet(ArrayList::new);

			setUpColours(primaryRecolourAllowed,
				importedPrimaryColours,
				importedPrimaryColoursDye,
				secondaryRecolourAllowed,
				importedSecondaryColours,
				importedSecondaryColoursDye,
				tertiaryRecolourAllowed,
				importedTertiaryColours,
				importedTertiaryColoursDye);

			if(coreAttributes.getOptionalFirstOf("customColours").isPresent()) {
				Element customColoursElement = coreAttributes.getMandatoryFirstOf("customColours");
				for(Element e : customColoursElement.getAllOf("customColour")) {
					try {
						List<String> replacementStrings = new ArrayList<>();
						for(int i=0;;i++) {
							if(e.getAttribute("c"+i).isEmpty()) {
								break;
							} else {
								replacementStrings.add(e.getAttribute("c"+i));
							}
						}
						
						boolean recolourAllowed = true;
						if(!e.getAttribute("recolouringAllowed").isEmpty()) {
							recolourAllowed = Boolean.valueOf(e.getAttribute("recolouringAllowed"));
						}
						if(!e.getAttribute("copyColourIndex").isEmpty()) {
							copyGenerationColours.put(this.colourReplacements.size(), Integer.valueOf(e.getAttribute("copyColourIndex")));
						}
						
						List<Colour> defaultColours = getColoursFromElement
							.apply(e.getMandatoryFirstOf("defaultColours"));
						List<Colour> extraColours = getColoursFromElement
							.apply(e.getMandatoryFirstOf("extraColours"));	
						
						this.colourReplacements.add(new ColourReplacement(recolourAllowed, replacementStrings, defaultColours, extraColours));
						
					} catch(Exception ex) {
						System.err.println("Error in loading customColours from clothing: "+this.getName());
					}
				}
			}
			
			if(this.colourReplacements.isEmpty()) {
				throw new Exception("AbstractClothingType (" + clothingXMLFile.getName() + "): colourReplacements is empty!");
			}
			
			if(coreAttributes.getOptionalFirstOf("defaultPatterns").isPresent()) {
				String patternChanceStr = coreAttributes.getMandatoryFirstOf("defaultPatterns").getAttribute("patternChance");
				patternChance = patternChanceStr.length() > 0 ? Float.valueOf(patternChanceStr) : 0;
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
									.map(Element::getTextContent).map(PresetColour::getColourFromId)
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

			if(coreAttributes.getOptionalFirstOf("customPatternColours").isPresent()) {
				Element customColoursElement = coreAttributes.getMandatoryFirstOf("customPatternColours");
				for(Element e : customColoursElement.getAllOf("customColour")) {
					try {
						List<String> replacementStrings = new ArrayList<>();
						for(int i=0;;i++) {
							if(e.getAttribute("c"+i).isEmpty()) {
								break;
							} else {
								replacementStrings.add(e.getAttribute("c"+i));
							}
						}
						
						List<Colour> defaultColours = getColoursFromElement
							.apply(e.getMandatoryFirstOf("defaultColours"));
						List<Colour> extraColours = getColoursFromElement
							.apply(e.getMandatoryFirstOf("extraColours"));	
						
						this.patternColourReplacements.add(new ColourReplacement(true, replacementStrings, defaultColours, extraColours));
						
					} catch(Exception ex) {
						System.err.println("Error in loading customColours from clothing: "+this.getName());
					}
				}
			}
			
			populateEmptyPatternColours();
			
			
			// Self sex attributes:
			Element sexSelfAttributes = null;
			try {
				sexSelfAttributes = clothingElement.getMandatoryFirstOf("sexAttributesSelf");
				
				// Self penetration values:
				if(sexSelfAttributes.getOptionalFirstOf("penetration").isPresent()) {
					Element penetrationAttributes = sexSelfAttributes.getMandatoryFirstOf("penetration");
					if(penetrationAttributes.getInnerElement().hasChildNodes()) {
						for(InventorySlot slot : this.equipSlots) {
							this.itemTags.get(slot).add(ItemTag.DILDO_SELF);
						}
					}
					
					if(penetrationAttributes.getOptionalFirstOf("length").isPresent()) {
						this.penetrationSelfLength = Integer.valueOf(penetrationAttributes.getMandatoryFirstOf("length").getTextContent());
					}
					if(penetrationAttributes.getOptionalFirstOf("girth").isPresent()) {
						this.penetrationSelfGirth = Integer.valueOf(penetrationAttributes.getMandatoryFirstOf("girth").getTextContent());
					}
					if(penetrationAttributes.getOptionalFirstOf("modifiers").isPresent()) {
						this.penetrationSelfModifiers = penetrationAttributes
								.getMandatoryFirstOf("modifiers")
								.getAllOf("mod").stream()
								.map( e -> PenetrationModifier.valueOf(e.getTextContent()))
								.collect(Collectors.toSet());
					}
				}
				// Self orifice values:
				if(sexSelfAttributes.getOptionalFirstOf("orifice").isPresent()) {
					Element orificeAttributes = sexSelfAttributes.getMandatoryFirstOf("orifice");
					if(orificeAttributes.getInnerElement().hasChildNodes()) {
						for(InventorySlot slot : this.equipSlots) {
							this.itemTags.get(slot).add(ItemTag.ONAHOLE_SELF);
						}
					}
					
					if(orificeAttributes.getOptionalFirstOf("depth").isPresent()) {
						this.orificeSelfDepth = Integer.valueOf(orificeAttributes.getMandatoryFirstOf("depth").getTextContent());
					}
					if(orificeAttributes.getOptionalFirstOf("capacity").isPresent()) {
						this.orificeSelfCapacity = Float.valueOf(orificeAttributes.getMandatoryFirstOf("capacity").getTextContent());
					}
					if(orificeAttributes.getOptionalFirstOf("elasticity").isPresent()) {
						this.orificeSelfElasticity = Integer.valueOf(orificeAttributes.getMandatoryFirstOf("elasticity").getTextContent());
					}
					if(orificeAttributes.getOptionalFirstOf("plasticity").isPresent()) {
						this.orificeSelfPlasticity = Integer.valueOf(orificeAttributes.getMandatoryFirstOf("plasticity").getTextContent());
					}
					if(orificeAttributes.getOptionalFirstOf("wetness").isPresent()) {
						this.orificeSelfWetness = Integer.valueOf(orificeAttributes.getMandatoryFirstOf("wetness").getTextContent());
					}
					if(orificeAttributes.getOptionalFirstOf("modifiers").isPresent()) {
						this.orificeSelfModifiers = orificeAttributes
								.getMandatoryFirstOf("modifiers")
								.getAllOf("mod").stream()
								.map( e -> OrificeModifier.valueOf(e.getTextContent()))
								.collect(Collectors.toSet());
					}
				}
			} catch (XMLMissingTagException ex) {
			}

			// Other sex attributes:
			Element otherSexAttributes = null;
			try {
				otherSexAttributes = clothingElement.getMandatoryFirstOf("sexAttributesOther");
				// Other penetration values:
				if(otherSexAttributes.getOptionalFirstOf("penetration").isPresent()) {
					Element penetrationAttributes = otherSexAttributes.getMandatoryFirstOf("penetration");
					if(penetrationAttributes.getInnerElement().hasChildNodes()) {
						for(InventorySlot slot : this.equipSlots) {
							this.itemTags.get(slot).add(ItemTag.DILDO_OTHER);
						}
					}
					
					if(penetrationAttributes.getOptionalFirstOf("length").isPresent()) {
						this.penetrationOtherLength = Integer.valueOf(penetrationAttributes.getMandatoryFirstOf("length").getTextContent());
					}
					if(penetrationAttributes.getOptionalFirstOf("girth").isPresent()) {
						this.penetrationOtherGirth = Integer.valueOf(penetrationAttributes.getMandatoryFirstOf("girth").getTextContent());
					}
					if(penetrationAttributes.getOptionalFirstOf("modifiers").isPresent()) {
						this.penetrationOtherModifiers = penetrationAttributes
								.getMandatoryFirstOf("modifiers")
								.getAllOf("mod").stream()
								.map( e -> PenetrationModifier.valueOf(e.getTextContent()))
								.collect(Collectors.toSet());
					}
				}
				// Other orifice values:
				if(otherSexAttributes.getOptionalFirstOf("orifice").isPresent()) {
					Element orificeAttributes = otherSexAttributes.getMandatoryFirstOf("orifice");
					if(orificeAttributes.getInnerElement().hasChildNodes()) {
						for(InventorySlot slot : this.equipSlots) {
							this.itemTags.get(slot).add(ItemTag.ONAHOLE_OTHER);
						}
					}
					
					if(orificeAttributes.getOptionalFirstOf("depth").isPresent()) {
						this.orificeOtherDepth = Integer.valueOf(orificeAttributes.getMandatoryFirstOf("depth").getTextContent());
					}
					if(orificeAttributes.getOptionalFirstOf("capacity").isPresent()) {
						this.orificeOtherCapacity = Float.valueOf(orificeAttributes.getMandatoryFirstOf("capacity").getTextContent());
					}
					if(orificeAttributes.getOptionalFirstOf("elasticity").isPresent()) {
						this.orificeOtherElasticity = Integer.valueOf(orificeAttributes.getMandatoryFirstOf("elasticity").getTextContent());
					}
					if(orificeAttributes.getOptionalFirstOf("plasticity").isPresent()) {
						this.orificeOtherPlasticity = Integer.valueOf(orificeAttributes.getMandatoryFirstOf("plasticity").getTextContent());
					}
					if(orificeAttributes.getOptionalFirstOf("wetness").isPresent()) {
						this.orificeOtherWetness = Integer.valueOf(orificeAttributes.getMandatoryFirstOf("wetness").getTextContent());
					}
					if(orificeAttributes.getOptionalFirstOf("modifiers").isPresent()) {
						this.orificeOtherModifiers = orificeAttributes
								.getMandatoryFirstOf("modifiers")
								.getAllOf("mod").stream()
								.map( e -> OrificeModifier.valueOf(e.getTextContent()))
								.collect(Collectors.toSet());
					}
				}
			} catch (XMLMissingTagException ex) {
			}
			
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
	
	private void setUpColours(
			boolean primaryRecolouringAllowed,
			List<Colour> availablePrimaryColours,
			List<Colour> availablePrimaryDyeColours,
			boolean secondaryRecolouringAllowed,
			List<Colour> availableSecondaryColours,
			List<Colour> availableSecondaryDyeColours,
			boolean tertiaryRecolouringAllowed,
			List<Colour> availableTertiaryColours,
			List<Colour> availableTertiaryDyeColours) {
		colourReplacements = new ArrayList<>();
		if((availablePrimaryColours!=null && !availablePrimaryColours.isEmpty()) || (availablePrimaryDyeColours!=null && !availablePrimaryDyeColours.isEmpty())) {
			colourReplacements.add(new ColourReplacement(primaryRecolouringAllowed, ColourReplacement.DEFAULT_PRIMARY_REPLACEMENTS, availablePrimaryColours, availablePrimaryDyeColours));
		}
		if((availableSecondaryColours!=null && !availableSecondaryColours.isEmpty()) || (availableSecondaryDyeColours!=null && !availableSecondaryDyeColours.isEmpty())) {
			colourReplacements.add(new ColourReplacement(secondaryRecolouringAllowed, ColourReplacement.DEFAULT_SECONDARY_REPLACEMENTS, availableSecondaryColours, availableSecondaryDyeColours));
		}
		if((availableTertiaryColours!=null && !availableTertiaryColours.isEmpty()) || (availableTertiaryDyeColours!=null && !availableTertiaryDyeColours.isEmpty())) {
			colourReplacements.add(new ColourReplacement(tertiaryRecolouringAllowed, ColourReplacement.DEFAULT_TERTIARY_REPLACEMENTS, availableTertiaryColours, availableTertiaryDyeColours));
		}
	}
	
	private void setUpPatternColours(List<Colour> availablePatternPrimaryColours,
			List<Colour> availablePatternPrimaryDyeColours,
			List<Colour> availablePatternSecondaryColours,
			List<Colour> availablePatternSecondaryDyeColours,
			List<Colour> availablePatternTertiaryColours,
			List<Colour> availablePatternTertiaryDyeColours) {
		patternColourReplacements = new ArrayList<>();
		if((availablePatternPrimaryColours!=null && !availablePatternPrimaryColours.isEmpty()) || (availablePatternPrimaryDyeColours!=null && !availablePatternPrimaryDyeColours.isEmpty())) {
			patternColourReplacements.add(new ColourReplacement(true, ColourReplacement.DEFAULT_PATTERN_PRIMARY_REPLACEMENTS, availablePatternPrimaryColours, availablePatternPrimaryDyeColours));
		}
		if((availablePatternSecondaryColours!=null && !availablePatternSecondaryColours.isEmpty()) || (availablePatternSecondaryDyeColours!=null && !availablePatternSecondaryDyeColours.isEmpty())) {
			patternColourReplacements.add(new ColourReplacement(true, ColourReplacement.DEFAULT_PATTERN_SECONDARY_REPLACEMENTS, availablePatternSecondaryColours, availablePatternSecondaryDyeColours));
		}
		if((availablePatternTertiaryColours!=null && !availablePatternTertiaryColours.isEmpty()) || (availablePatternTertiaryDyeColours!=null && !availablePatternTertiaryDyeColours.isEmpty())) {
			patternColourReplacements.add(new ColourReplacement(true, ColourReplacement.DEFAULT_PATTERN_TERTIARY_REPLACEMENTS, availablePatternTertiaryColours, availablePatternTertiaryDyeColours));
		}
	}
	
	/**
	 * Make sure that there are at least three pattern replacement colours.
	 */
	private void populateEmptyPatternColours() {
		if(patternColourReplacements.size()==0) {
			patternColourReplacements.add(new ColourReplacement(true, ColourReplacement.DEFAULT_PATTERN_PRIMARY_REPLACEMENTS, ColourListPresets.ALL, null));
		}
		if(patternColourReplacements.size()==1) {
			patternColourReplacements.add(new ColourReplacement(true, ColourReplacement.DEFAULT_PATTERN_SECONDARY_REPLACEMENTS, ColourListPresets.ALL, null));
		}
		if(patternColourReplacements.size()==2) {
			patternColourReplacements.add(new ColourReplacement(true, ColourReplacement.DEFAULT_PATTERN_TERTIARY_REPLACEMENTS, ColourListPresets.ALL, null));
		}
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
	

	
	public String getId() {
		return ClothingType.getIdFromClothingType(this);
	}

	public int getBaseValue() {
		return baseValue;
	}

	static Map<AbstractSetBonus, List<AbstractClothingType>> clothingSetMap = new HashMap<>();

	public static List<AbstractClothingType> getClothingInSet(AbstractSetBonus set) {
		if (clothingSetMap.get(set) != null) {
			return clothingSetMap.get(set);
		}

		List<AbstractClothingType> setOfClothing = new ArrayList<>();

		for (AbstractClothingType c : ClothingType.getAllClothing()) {
			if (c.getClothingSet() == set) {
				setOfClothing.add(c);
			}
		}

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
		
		if(clothingOwner==null || clothingEquipper==null || !Main.game.isStarted()) {
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
				return UtilText.parse(clothingOwner, "[npc.Name] [npc.verb(unequip)] [npc.her] "+clothing.getName()+".");
				
			} else {
				if(rough) {
					if(displacementDescriptions.get(slotToUnequipFrom)!=null
							&& displacementDescriptions.get(slotToUnequipFrom).containsKey(DisplacementType.REMOVE_OR_EQUIP)
							&& displacementDescriptions.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.DISPLACEMENT_ROUGH)) {
						return UtilText.parse(clothingRemover, clothingOwner, displacementDescriptions.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.DISPLACEMENT_ROUGH));
					}
					return UtilText.parse(clothingRemover, clothingOwner, "[npc.Name] roughly [npc.verb(unequip)] [npc2.namePos] "+clothing.getName()+".");
					
				} else {
					if(displacementDescriptions.get(slotToUnequipFrom)!=null
							&& displacementDescriptions.get(slotToUnequipFrom).containsKey(DisplacementType.REMOVE_OR_EQUIP)
							&& displacementDescriptions.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).containsKey(DisplacementDescriptionType.DISPLACEMENT)) {
						return UtilText.parse(clothingRemover, clothingOwner, displacementDescriptions.get(slotToUnequipFrom).get(DisplacementType.REMOVE_OR_EQUIP).get(DisplacementDescriptionType.DISPLACEMENT));
					}
					return UtilText.parse(clothingRemover, clothingOwner, "[npc.Name] [npc.verb(get)] [npc2.name] to unequip [npc2.her] "+clothing.getName()+".");
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
					return "You unequip the "+clothing.getName()+".";
					
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
				return UtilText.parse(clothingOwner, "[npc.Name] "+(clothingRemover.isPlayer()?dt.getDescription():dt.getDescriptionThirdPerson())+" [npc.her] "+this.getName()+".");
				
			} else {
				if(rough) {
					if(displacementDescriptions.get(slotClothingIsEquippedTo)!=null
							&& displacementDescriptions.get(slotClothingIsEquippedTo).containsKey(dt)
							&& displacementDescriptions.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.DISPLACEMENT_ROUGH)) {
						return UtilText.parse(clothingRemover, clothingOwner, displacementDescriptions.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.DISPLACEMENT_ROUGH));
					}
					return UtilText.parse(clothingRemover, clothingOwner, "[npc.Name] roughly "+(clothingRemover.isPlayer()?dt.getDescription():dt.getDescriptionThirdPerson())+" [npc2.namePos] "+this.getName()+".");
					
				} else {
					if(displacementDescriptions.get(slotClothingIsEquippedTo)!=null
							&& displacementDescriptions.get(slotClothingIsEquippedTo).containsKey(dt)
							&& displacementDescriptions.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.DISPLACEMENT)) {
						return UtilText.parse(clothingRemover, clothingOwner, displacementDescriptions.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.DISPLACEMENT));
					}
					return UtilText.parse(clothingRemover, clothingOwner, "[npc.Name] "+(clothingRemover.isPlayer()?dt.getDescription():dt.getDescriptionThirdPerson())+" [npc2.namePos] "+this.getName()+".");
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
					return "You "+dt.getDescription()+" your "+this.getName()+".";
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
				return UtilText.parse(clothingOwner, "[npc.Name] "+(clothingRemover.isPlayer()?dt.getOppositeDescription():dt.getOppositeDescriptionThirdPerson())+" [npc.her] "+this.getName()+".");
				
			} else {
				if(rough) {
					if(displacementDescriptions.get(slotClothingIsEquippedTo)!=null
							&& displacementDescriptions.get(slotClothingIsEquippedTo).containsKey(dt)
							&& displacementDescriptions.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.REPLACEMENT_ROUGH)) {
						return UtilText.parse(clothingRemover, clothingOwner, displacementDescriptions.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.REPLACEMENT_ROUGH));
					}
					return UtilText.parse(clothingRemover, clothingOwner, "[npc.Name] "+(clothingRemover.isPlayer()?dt.getOppositeDescription():dt.getOppositeDescriptionThirdPerson())+" [npc2.namePos] "+this.getName()+".");
					
				} else {
					if(displacementDescriptions.get(slotClothingIsEquippedTo)!=null
							&& displacementDescriptions.get(slotClothingIsEquippedTo).containsKey(dt)
							&& displacementDescriptions.get(slotClothingIsEquippedTo).get(dt).containsKey(DisplacementDescriptionType.REPLACEMENT)) {
						return UtilText.parse(clothingRemover, clothingOwner, displacementDescriptions.get(slotClothingIsEquippedTo).get(dt).get(DisplacementDescriptionType.REPLACEMENT));
					}
					return UtilText.parse(clothingRemover, clothingOwner, "[npc.Name] roughly "+(clothingRemover.isPlayer()?dt.getOppositeDescription():dt.getOppositeDescriptionThirdPerson())+" [npc2.namePos] "+this.getName()+".");
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
					return "You "+dt.getOppositeDescription()+" your "+this.getName()+".";
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

	public Value<Boolean, String> isAbleToBeBeEquipped(GameCharacter clothingOwner, InventorySlot slot) {
		BodyPartClothingBlock block = slot.getBodyPartClothingBlock(clothingOwner);
		List<ItemTag> tags = this.getItemTags(slot);
		
		if(!this.getEquipSlots().contains(slot)) {
			return new Value<>(false, UtilText.parse("[style.colourBad(The "+this.getName()+" cannot be equipped into this slot!)]"));
		}
		if (block != null && Collections.disjoint(block.getRequiredTags(), tags)) {
			return new Value<>(false, UtilText.parse("[style.colourBad(" + UtilText.parse(clothingOwner, block.getDescription()) + ")]"));
		}
		if(tags.contains(ItemTag.FITS_ARACHNID_BODY) && clothingOwner.getLegConfiguration()!=LegConfiguration.ARACHNID) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(isPlural()?"are":"is")+" only suitable for arachnid bodies, and as such, [npc.name] cannot wear "+(isPlural()?"them":"it")+"."));
		}
		if(tags.contains(ItemTag.FITS_CEPHALOPOD_BODY) && clothingOwner.getLegConfiguration()!=LegConfiguration.CEPHALOPOD) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(isPlural()?"are":"is")+" only suitable for cephalopod bodies, and as such, [npc.name] cannot wear "+(isPlural()?"them":"it")+"."));
		}
		if(tags.contains(ItemTag.FITS_ARM_WINGS_EXCLUSIVE) && !clothingOwner.getArmTypeTags().contains(ArmTypeTag.WINGS)) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(isPlural()?"are":"is")+" only suitable for arm-wings, and as such, [npc.name] cannot wear "+(isPlural()?"them":"it")+"."));
		}
		if(tags.contains(ItemTag.FITS_FEATHERED_ARM_WINGS_EXCLUSIVE) && !clothingOwner.getArmTypeTags().contains(ArmTypeTag.WINGS_FEATHERED)) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(isPlural()?"are":"is")+" only suitable for feathered arm-wings, and as such, [npc.name] cannot wear "+(isPlural()?"them":"it")+"."));
		}
		if(tags.contains(ItemTag.FITS_LEATHERY_ARM_WINGS_EXCLUSIVE) && !clothingOwner.getArmTypeTags().contains(ArmTypeTag.WINGS_LEATHERY)) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(isPlural()?"are":"is")+" only suitable for leathery arm-wings, and as such, [npc.name] cannot wear "+(isPlural()?"them":"it")+"."));
		}
		if(tags.contains(ItemTag.FITS_HOOFS_EXCLUSIVE) && clothingOwner.getLegType().getFootType()!=FootType.HOOFS) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(isPlural()?"are":"is")+" only suitable for hoofs, and as such, [npc.name] cannot wear "+(isPlural()?"them":"it")+"."));
		}
		if(tags.contains(ItemTag.FITS_LONG_TAIL_BODY) && clothingOwner.getLegConfiguration()!=LegConfiguration.TAIL_LONG) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(isPlural()?"are":"is")+" only suitable for long-tailed bodies, and as such, [npc.name] cannot wear "+(isPlural()?"them":"it")+"."));
		}
		if(tags.contains(ItemTag.FITS_TAIL_BODY) && clothingOwner.getLegConfiguration()!=LegConfiguration.TAIL) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(isPlural()?"are":"is")+" only suitable for tailed bodies, and as such, [npc.name] cannot wear "+(isPlural()?"them":"it")+"."));
		}
		if(tags.contains(ItemTag.FITS_TALONS_EXCLUSIVE) && clothingOwner.getLegType().getFootType()!=FootType.TALONS) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(isPlural()?"are":"is")+" only suitable for talons, and as such, [npc.name] cannot wear "+(isPlural()?"them":"it")+"."));
		}
		if(tags.contains(ItemTag.FITS_TAUR_BODY) && clothingOwner.getLegConfiguration()!=LegConfiguration.TAUR) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(isPlural()?"are":"is")+" only suitable for taur bodies, and as such, [npc.name] cannot wear "+(isPlural()?"them":"it")+"."));
		}
		if(clothingOwner.hasPenisIgnoreDildo() && tags.contains(ItemTag.REQUIRES_NO_PENIS)) {
			return new Value<>(false, UtilText.parse(clothingOwner, "[npc.NameHasFull] a penis, which is blocking [npc.herHim] from wearing the "+this.getName()+"!"));
		}
		if(!clothingOwner.hasPenisIgnoreDildo() && tags.contains(ItemTag.REQUIRES_PENIS)) {
			return new Value<>(false, UtilText.parse(clothingOwner, "[npc.Name] [npc.do]n't have a penis, so [npc.she] can't wear the "+this.getName()+"!"));
		}
		if(clothingOwner.hasVagina() && tags.contains(ItemTag.REQUIRES_NO_VAGINA)) {
			return new Value<>(false, UtilText.parse(clothingOwner, "[npc.NameHasFull] a vagina, which is blocking [npc.herHim] from wearing the "+this.getName()+"!"));
		}
		if(!clothingOwner.hasVagina() && tags.contains(ItemTag.REQUIRES_VAGINA)) {
			return new Value<>(false, UtilText.parse(clothingOwner, "[npc.Name] [npc.do]n't have a vagina, so [npc.she] can't wear the "+this.getName()+"!"));
		}
		if(!clothingOwner.isBreastFuckableNipplePenetration() && tags.contains(ItemTag.REQUIRES_FUCKABLE_NIPPLES)) {
			return new Value<>(false, UtilText.parse(clothingOwner, "[npc.NamePos] nipples are not fuckable, so [npc.she] can't wear the "+this.getName()+"!"));
		}
		if(clothingOwner.getBody().getBodyMaterial().isRequiresPiercing()) {
			if(slot==InventorySlot.PIERCING_EAR && !clothingOwner.isPiercedEar()){
				return new Value<>(false, UtilText.parse(clothingOwner, "[npc.NamePos] ears are not pierced, so [npc.she] can't wear the "+this.getName()+"!"));
		
			} else if(slot==InventorySlot.PIERCING_LIP && !clothingOwner.isPiercedLip()){
				return new Value<>(false, UtilText.parse(clothingOwner, "[npc.NamePos] lips are not pierced, so [npc.she] can't wear the "+this.getName()+"!"));
				
			} else if(slot==InventorySlot.PIERCING_NIPPLE && !clothingOwner.isPiercedNipple()){
				return new Value<>(false, UtilText.parse(clothingOwner, "[npc.NamePos] nipples are not pierced, so [npc.she] can't wear the "+this.getName()+"!"));
				
			} else if(slot==InventorySlot.PIERCING_NOSE && !clothingOwner.isPiercedNose()){
				return new Value<>(false, UtilText.parse(clothingOwner, "[npc.NamePos] nose is not pierced, so [npc.she] can't wear the "+this.getName()+"!"));
				
			} else if(slot==InventorySlot.PIERCING_PENIS && !clothingOwner.isPiercedPenis()){
				return new Value<>(false, UtilText.parse(clothingOwner, "[npc.NamePos] penis is not pierced, so [npc.she] can't wear the "+this.getName()+"!"));
				
			} else if(slot==InventorySlot.PIERCING_STOMACH && !clothingOwner.isPiercedNavel()){
				return new Value<>(false, UtilText.parse(clothingOwner, "[npc.NamePos] navel is not pierced, so [npc.she] can't wear the "+this.getName()+"!"));
				
			} else if(slot==InventorySlot.PIERCING_TONGUE && !clothingOwner.isPiercedTongue()){
				return new Value<>(false, UtilText.parse(clothingOwner, "[npc.NamePos] tongue is not pierced, so [npc.she] can't wear the "+this.getName()+"!"));
				
			} else if(slot==InventorySlot.PIERCING_VAGINA && !clothingOwner.isPiercedVagina()){
				return new Value<>(false, UtilText.parse(clothingOwner, "[npc.NamePos] vagina is not pierced, so [npc.she] can't wear the "+this.getName()+"!"));
			}
		}
		if(slot==InventorySlot.PIERCING_PENIS && !clothingOwner.hasPenisIgnoreDildo()){
			return new Value<>(false, UtilText.parse(clothingOwner, "[npc.Name] [npc.do] not have a penis, so [npc.she] can't wear the "+this.getName()+"!"));
			
		} else if(slot==InventorySlot.PIERCING_VAGINA && !clothingOwner.hasVagina()){
			return new Value<>(false, UtilText.parse(clothingOwner, "[npc.Name] [npc.do] not have a vagina, so [npc.she] can't wear the "+this.getName()+"!"));
		}
		
		if (slot == InventorySlot.WINGS && clothingOwner.getWingType()==WingType.NONE) {
			return new Value<>(false, UtilText.parse(clothingOwner, "[npc.Name] [npc.do] not have any wings, so [npc.she] can't wear the "+this.getName()+"!"));
		}
		if (slot == InventorySlot.HORNS && clothingOwner.getHornType().equals(HornType.NONE)) {
			return new Value<>(false, UtilText.parse(clothingOwner, "[npc.Name] [npc.do] not have any horns, so [npc.she] can't wear the "+this.getName()+"!"));
		}
		if (slot == InventorySlot.TAIL && clothingOwner.getTailType()==TailType.NONE) {
			return new Value<>(false, UtilText.parse(clothingOwner, "[npc.Name] [npc.do] not have a tail, so [npc.she] can't wear the "+this.getName()+"!"));
		}
		return new Value<>(true, "");
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

	public boolean isAppendColourName() {
		return appendColourName;
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
	
	public AbstractSetBonus getClothingSet() {
		return clothingSet;
	}

	public ColourReplacement getColourReplacement(int index) {
		if(index>colourReplacements.size()-1) {
			return null;
		}
		return colourReplacements.get(index);
	}
	
	public List<ColourReplacement> getColourReplacements() {
		return colourReplacements;
	}
	
	private static String generateIdentifier(InventorySlot slotEquippedTo, List<Colour> colours, String pattern, List<Colour> patternColours) {
		StringBuilder sb = new StringBuilder(slotEquippedTo.toString());
		for(Colour c : colours) {
			sb.append(c.getId());
		}
		if(pattern==null) {
			sb.append("none");
		} else {
			sb.append(pattern);
		}
		for(Colour c : patternColours) {
			sb.append(c.getId());
		}
		return sb.toString();
	}
	
	private void addSVGStringMapping(InventorySlot slotEquippedTo, List<Colour> colours, String pattern, List<Colour> patternColours, String svgString) {
		SVGStringMap.put(generateIdentifier(slotEquippedTo, colours, pattern, patternColours), svgString);
	}
	
	private void addSVGStringEquippedMapping(InventorySlot slotEquippedTo, List<Colour> colours, String pattern, List<Colour> patternColours, String svgString) {
		SVGStringEquippedMap.put(generateIdentifier(slotEquippedTo, colours, pattern, patternColours), svgString);
	}
	
	private String getSVGStringFromMap(InventorySlot slotEquippedTo, List<Colour> colours, String pattern, List<Colour> patternColours) {
		return SVGStringMap.get(generateIdentifier(slotEquippedTo, colours, pattern, patternColours));
	}
	
	private String getSVGStringFromEquippedMap(InventorySlot slotEquippedTo, List<Colour> colours, String pattern, List<Colour> patternColours) {
		return SVGStringEquippedMap.get(generateIdentifier(slotEquippedTo, colours, pattern, patternColours));
	}

	public String getSVGImage() {
		List<Colour> colours = new ArrayList<>();
		
		for(ColourReplacement cr : this.getColourReplacements()) {
			colours.add(cr.getFirstOfDefaultColours());
		}
		
		return getSVGImage(null, this.getEquipSlots().get(0), colours, false, null, new ArrayList<>());
	}

	public String getSVGImageRandomColour(boolean randomPrimary, boolean randomSecondary, boolean randomTertiary) {
		List<Colour> colours = new ArrayList<>();
		
		for(int i=0; i<this.getColourReplacements().size(); i++) {
			ColourReplacement cr = this.getColourReplacements().get(i);
			if((i==0 && randomPrimary)
					|| (i==1 && randomPrimary)
					|| (i==2 && randomTertiary)) {
				colours.add(cr.getRandomOfDefaultColours());
				
			} else {
				colours.add(cr.getFirstOfDefaultColours());
			}
		}
		
		return getSVGImage(null, this.getEquipSlots().get(0), colours, false, null, new ArrayList<>());
	}
	
	/**
	 * @param colours This needs to have at least one entry.
	 */
	public String getSVGImage(InventorySlot slotEquippedTo,
			List<Colour> colours,
			String pattern,
			List<Colour> patternColours) {
		return getSVGImage(null, slotEquippedTo, colours, false, pattern, patternColours);
	}
	
	/**
	 * @param character The character this clothing is equipped to.
	 * @param colours This needs to have at least one entry.
	 */
	public String getSVGEquippedImage(GameCharacter character,
			InventorySlot slotEquippedTo,
			List<Colour> colours,
			String pattern,
			List<Colour> patternColours) {
		return getSVGImage(character, slotEquippedTo, colours, true, pattern, patternColours);
	}
	
	private String getSVGImage(GameCharacter character,
			InventorySlot slotEquippedTo,
			List<Colour> colours,
			boolean equippedVariant,
			String pattern,
			List<Colour> patternColours) {
		if(this.equals(ClothingType.HIPS_CONDOMS)) {
			if(!colours.isEmpty()) {
				try {
					InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/belt_used_condoms_base_back.svg");
					String s = "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;padding:0;margin:0'>"+Util.inputStreamToString(is)+"</div>";
					is.close();
					s = getSVGWithHandledPattern(s, pattern, patternColours);
					s = SvgUtil.colourReplacement(this.getId(), colours, this.getColourReplacements(), s);

					if(!equippedVariant) {
						is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/belt_used_condoms_base_front.svg");
						s += "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;padding:0;margin:0'>" + Util.inputStreamToString(is) + "</div>";
						s = getSVGWithHandledPattern(s, pattern, patternColours);
						s = SvgUtil.colourReplacement(this.getId(), colours, this.getColourReplacements(), s);
						is.close();
						
						addSVGStringEquippedMapping(slotEquippedTo, colours, pattern, patternColours, s);
						
						return s;
						
					} else {
						List<Colour> condomColours = new ArrayList<>();
						// Draw all backs:
						for(AbstractItem item : character.getAllItemsInInventory().keySet()) {
							if(item.getItemType().equals(ItemType.CONDOM_USED)) {
								for(int count=0; count<character.getItemCount(item); count++) {
									if(condomColours.size()<8) {
										condomColours.add(item.getColour(0));
										
										is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/belt_used_condoms_"+condomColours.size()+"_back.svg");
										s += "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;padding:0;margin:0'>" + Util.inputStreamToString(is) + "</div>";
										s = getSVGWithHandledPattern(s, pattern, patternColours);
										s = SvgUtil.colourReplacement(this.getId(),
												Util.newArrayListOfValues(item.getColour(0)),
												Util.newArrayListOfValues(new ColourReplacement(true, ColourReplacement.DEFAULT_PRIMARY_REPLACEMENTS, ColourListPresets.ALL, null)),
												s);
										is.close();
									}
								}
							}
						}
						
						is.close();
						is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/belt_used_condoms_base_front.svg");
						s += "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;padding:0;margin:0'>" + Util.inputStreamToString(is) + "</div>";
						s = getSVGWithHandledPattern(s, pattern, patternColours);
						s = SvgUtil.colourReplacement(this.getId(), colours, this.getColourReplacements(), s);
						is.close();
						
						int i = 1;
						for(Colour c : condomColours) {
							is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/belt_used_condoms_"+i+"_front.svg");
							s += "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;padding:0;margin:0'>" + Util.inputStreamToString(is) + "</div>";
							s = getSVGWithHandledPattern(s, pattern, patternColours);
							s = SvgUtil.colourReplacement(this.getId(),
									Util.newArrayListOfValues(c),
									Util.newArrayListOfValues(new ColourReplacement(true, ColourReplacement.DEFAULT_PRIMARY_REPLACEMENTS, ColourListPresets.ALL, null)),
									s);
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
				String stringFromMap = getSVGStringFromEquippedMap(slotEquippedTo, colours, pattern, patternColours);
				if (stringFromMap!=null && !this.equals(ClothingType.WRIST_WOMENS_WATCH) && !this.equals(ClothingType.WRIST_MENS_WATCH)) {
					return stringFromMap;
					
				} else {
					if(!colours.isEmpty()) {
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
							
							s = getSVGWithHandledPattern(s, pattern, patternColours);
							s = SvgUtil.colourReplacement(this.getId(), colours, this.getColourReplacements(), s);
							
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

							addSVGStringEquippedMapping(slotEquippedTo, colours, pattern, patternColours, s);
							
							return s;
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				
			} else {
				String stringFromMap = getSVGStringFromMap(slotEquippedTo, colours, pattern, patternColours);
				if (stringFromMap!=null && !this.equals(ClothingType.WRIST_WOMENS_WATCH) && !this.equals(ClothingType.WRIST_MENS_WATCH)) {
					return stringFromMap;
					
				} else {
					if(!colours.isEmpty()) {
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
							
							s = getSVGWithHandledPattern(s, pattern, patternColours);
							s = SvgUtil.colourReplacement(this.getId(), colours, this.getColourReplacements(), s);
							
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
							
							addSVGStringMapping(slotEquippedTo, colours, pattern, patternColours, s);
		
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
	
	public boolean isPatternAvailable() {
		if(!isPatternAvailable && !isPatternAvailableInitCompleted) {
			isPatternAvailable = this.getSVGImage().contains("patternLayer");
			isPatternAvailableInitCompleted = true;
		}
		return this.isPatternAvailable;
	}

	private String getSVGWithHandledPattern(String s, String pattern, List<Colour> patternColours) {
		isPatternAvailableInitCompleted = true;
		if(!s.contains("patternLayer")) { // Making sure that the pattern layer exists.
			return s;
		}
		if(!this.isPatternAvailable) {
			this.isPatternAvailable = true;
		}
		
		return SvgUtil.getSVGWithHandledPattern("Clothing causing error: "+this.getName(), s, pattern, patternColours, this.getPatternColourReplacements());
	}

	public float getPatternChance() {
		return patternChance;
	}

	public List<Pattern> getDefaultPatterns() {
		return defaultPatterns;
	}

	public ColourReplacement getPatternColourReplacement(int index) {
		if(index>patternColourReplacements.size()-1) {
			return null;
		}
		return patternColourReplacements.get(index);
	}
	
	public List<ColourReplacement> getPatternColourReplacements() {
		return patternColourReplacements;
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
	
	// Sex attributes:
	
	public int getPenetrationSelfLength() {
		return penetrationSelfLength;
	}

	public int getPenetrationSelfGirth() {
		return penetrationSelfGirth;
	}

	public Set<PenetrationModifier> getPenetrationSelfModifiers() {
		if(penetrationSelfModifiers==null) {
			return new HashSet<>();
		}
		return penetrationSelfModifiers;
	}

	public int getPenetrationOtherLength() {
		return penetrationOtherLength;
	}

	public int getPenetrationOtherGirth() {
		return penetrationOtherGirth;
	}

	public Set<PenetrationModifier> getPenetrationOtherModifiers() {
		if(penetrationOtherModifiers==null) {
			return new HashSet<>();
		}
		return penetrationOtherModifiers;
	}

	public int getOrificeSelfDepth() {
		return orificeSelfDepth;
	}

	public float getOrificeSelfCapacity() {
		return orificeSelfCapacity;
	}

	public int getOrificeSelfElasticity() {
		return orificeSelfElasticity;
	}

	public int getOrificeSelfPlasticity() {
		return orificeSelfPlasticity;
	}

	public int getOrificeSelfWetness() {
		return orificeSelfWetness;
	}

	public Set<OrificeModifier> getOrificeSelfModifiers() {
		if(orificeSelfModifiers==null) {
			return new HashSet<>();
		}
		return orificeSelfModifiers;
	}

	public int getOrificeOtherDepth() {
		return orificeOtherDepth;
	}

	public float getOrificeOtherCapacity() {
		return orificeOtherCapacity;
	}

	public int getOrificeOtherElasticity() {
		return orificeOtherElasticity;
	}

	public int getOrificeOtherPlasticity() {
		return orificeOtherPlasticity;
	}

	public int getOrificeOtherWetness() {
		return orificeOtherWetness;
	}

	public Set<OrificeModifier> getOrificeOtherModifiers() {
		if(orificeOtherModifiers==null) {
			return new HashSet<>();
		}
		return orificeOtherModifiers;
	}
}
