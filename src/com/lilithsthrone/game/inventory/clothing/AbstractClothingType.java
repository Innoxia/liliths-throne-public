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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.item.ItemEffect;
import com.lilithsthrone.game.inventory.item.ItemEffectType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.ColourListPresets;
import com.lilithsthrone.utils.Util;

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
	
	public AbstractClothingType(File clothingXMLFile) {

		if (clothingXMLFile.exists()) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(clothingXMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element clothingElement = (Element) doc.getElementsByTagName("clothing").item(0);
				
				Element coreAttributes = (Element) clothingElement.getElementsByTagName("coreAtributes").item(0);
				
				List<ItemEffect> defaultEffects = new ArrayList<>();
				try {
					Element effectsElement = (Element)coreAttributes.getElementsByTagName("effects").item(0);
					for(int i=0; i<effectsElement.getElementsByTagName("effect").getLength(); i++){
						Element e = ((Element)effectsElement.getElementsByTagName("effect").item(i));
						defaultEffects.add(ItemEffect.loadFromXML(e, doc));
					}
				} catch(Exception ex) {
					System.err.println("AbstractClothingType loading failed. Cause: 'effects' element unable to be parsed.");
				}
				
				List<BlockedParts> defaultBlockedParts = new ArrayList<>();
				try {
					Element blockedPartsElement = (Element)coreAttributes.getElementsByTagName("blockedPartsList").item(0);
					for(int i=0; i<blockedPartsElement.getElementsByTagName("blockedParts").getLength(); i++){
						Element e = ((Element)blockedPartsElement.getElementsByTagName("blockedParts").item(i));
						defaultBlockedParts.add(BlockedParts.loadFromXML(e, doc));
					}
				} catch(Exception ex) {
					System.err.println("AbstractClothingType loading failed. Cause: 'blockedPartsList' element unable to be parsed.");
				}
				
				List<InventorySlot> defaultIncompatibleSlots = new ArrayList<>();
				Element incompatibleSlotsElement = (Element)coreAttributes.getElementsByTagName("incompatibleSlots").item(0);
				try {
					for(int i=0; i<incompatibleSlotsElement.getElementsByTagName("slot").getLength(); i++){
						Element e = ((Element)incompatibleSlotsElement.getElementsByTagName("slot").item(i));
						defaultIncompatibleSlots.add(InventorySlot.valueOf(e.getTextContent()));
					}
				} catch(Exception ex) {
					System.err.println("AbstractClothingType loading failed. Cause: 'incompatibleSlots' element unable to be parsed.");
				}
				
				List<ItemTag> defaultItemTags = new ArrayList<>();
				Element itemTagsElement = (Element)coreAttributes.getElementsByTagName("itemTags").item(0);
				try {
					for(int i=0; i<itemTagsElement.getElementsByTagName("tag").getLength(); i++){
						Element e = ((Element)itemTagsElement.getElementsByTagName("tag").item(i));
						defaultItemTags.add(ItemTag.valueOf(e.getTextContent()));
					}
				} catch(Exception ex) {
					System.err.println("AbstractClothingType loading failed. Cause: 'itemTags' element unable to be parsed.");
				}
				
				//TODO
				displacementDescriptionsPlayer = new HashMap<>();
				displacementDescriptionsNPC = new HashMap<>();

				if(clothingElement.getElementsByTagName("replacementText").getLength() > 0) {
					for(int i=0; i<clothingElement.getElementsByTagName("replacementText").getLength(); i++){
						try {
							Element equipTextElement = (Element)clothingElement.getElementsByTagName("replacementText").item(i);
							DisplacementType type = DisplacementType.valueOf(equipTextElement.getAttribute("type"));
						
							displacementDescriptionsPlayer.putIfAbsent(type, new HashMap<>());
							displacementDescriptionsNPC.putIfAbsent(type, new HashMap<>());
							try {
								displacementDescriptionsPlayer.get(type).put(DisplacementDescriptionType.REPLACEMENT, equipTextElement.getElementsByTagName("playerNPC").item(0).getTextContent());
							} catch(Exception ex) {
							}
							try {
								displacementDescriptionsPlayer.get(type).put(DisplacementDescriptionType.REPLACEMENT_ROUGH, equipTextElement.getElementsByTagName("playerNPCRough").item(0).getTextContent());
							} catch(Exception ex) {
							}
							try {
								displacementDescriptionsPlayer.get(type).put(DisplacementDescriptionType.REPLACEMENT_SELF, equipTextElement.getElementsByTagName("playerSelf").item(0).getTextContent());
							} catch(Exception ex) {
							}
							try {
								displacementDescriptionsNPC.get(type).put(DisplacementDescriptionType.REPLACEMENT, equipTextElement.getElementsByTagName("NPCPlayer").item(0).getTextContent());
							} catch(Exception ex) {
							}
							try {
								displacementDescriptionsNPC.get(type).put(DisplacementDescriptionType.REPLACEMENT_ROUGH, equipTextElement.getElementsByTagName("NPCPlayerRough").item(0).getTextContent());
							} catch(Exception ex) {
							}
							try {
								displacementDescriptionsNPC.get(type).put(DisplacementDescriptionType.REPLACEMENT_SELF, equipTextElement.getElementsByTagName("NPCSelf").item(0).getTextContent());
							} catch(Exception ex) {
							}
							try {
								displacementDescriptionsNPC.get(type).put(DisplacementDescriptionType.NPC_ON_NPC_REPLACEMENT, equipTextElement.getElementsByTagName("NPCOtherNPC").item(0).getTextContent());
							} catch(Exception ex) {
							}
							try {
								displacementDescriptionsNPC.get(type).put(DisplacementDescriptionType.NPC_ON_NPC_REPLACEMENT_ROUGH, equipTextElement.getElementsByTagName("NPCOtherNPCRough").item(0).getTextContent());
							} catch(Exception ex) {
							}
							
						} catch(Exception ex) {
							System.err.println("AbstractClothingType loading failed. Cause: 'replacementText' element unable to be parsed.");
						}
					}
				}
				
				if(clothingElement.getElementsByTagName("displacementText").getLength() > 0) {
					for(int i=0; i<clothingElement.getElementsByTagName("displacementText").getLength(); i++){
						try {
							Element equipTextElement = (Element)clothingElement.getElementsByTagName("displacementText").item(i);
							DisplacementType type = DisplacementType.valueOf(equipTextElement.getAttribute("type"));
						
							displacementDescriptionsPlayer.putIfAbsent(type, new HashMap<>());
							displacementDescriptionsNPC.putIfAbsent(type, new HashMap<>());
							try {
								displacementDescriptionsPlayer.get(type).put(DisplacementDescriptionType.DISPLACEMENT, equipTextElement.getElementsByTagName("playerNPC").item(0).getTextContent());
							} catch(Exception ex) {
							}
							try {
								displacementDescriptionsPlayer.get(type).put(DisplacementDescriptionType.DISPLACEMENT_ROUGH, equipTextElement.getElementsByTagName("playerNPCRough").item(0).getTextContent());
							} catch(Exception ex) {
							}
							try {
								displacementDescriptionsPlayer.get(type).put(DisplacementDescriptionType.DISPLACEMENT_SELF, equipTextElement.getElementsByTagName("playerSelf").item(0).getTextContent());
							} catch(Exception ex) {
							}
							try {
								displacementDescriptionsNPC.get(type).put(DisplacementDescriptionType.DISPLACEMENT, equipTextElement.getElementsByTagName("NPCPlayer").item(0).getTextContent());
							} catch(Exception ex) {
							}
							try {
								displacementDescriptionsNPC.get(type).put(DisplacementDescriptionType.DISPLACEMENT_ROUGH, equipTextElement.getElementsByTagName("NPCPlayerRough").item(0).getTextContent());
							} catch(Exception ex) {
							}
							try {
								displacementDescriptionsNPC.get(type).put(DisplacementDescriptionType.DISPLACEMENT_SELF, equipTextElement.getElementsByTagName("NPCSelf").item(0).getTextContent());
							} catch(Exception ex) {
							}
							try {
								displacementDescriptionsNPC.get(type).put(DisplacementDescriptionType.NPC_ON_NPC_DISPLACEMENT, equipTextElement.getElementsByTagName("NPCOtherNPC").item(0).getTextContent());
							} catch(Exception ex) {
							}
							try {
								displacementDescriptionsNPC.get(type).put(DisplacementDescriptionType.NPC_ON_NPC_DISPLACEMENT_ROUGH, equipTextElement.getElementsByTagName("NPCOtherNPCRough").item(0).getTextContent());
							} catch(Exception ex) {
							}
							
						} catch(Exception ex) {
							System.err.println("AbstractClothingType loading failed. Cause: 'displacementText' element unable to be parsed.");
						}
					}
				}
				
				
				this.isMod = true;
				
				this.baseValue = Integer.valueOf(coreAttributes.getElementsByTagName("value").item(0).getTextContent());
				this.determiner = coreAttributes.getElementsByTagName("determiner").item(0).getTextContent();
				this.plural = Boolean.valueOf(((Element)coreAttributes.getElementsByTagName("namePlural").item(0)).getAttribute("pluralByDefault"));
				this.name = coreAttributes.getElementsByTagName("name").item(0).getTextContent();
				this.namePlural = coreAttributes.getElementsByTagName("namePlural").item(0).getTextContent();
				this.description = coreAttributes.getElementsByTagName("description").item(0).getTextContent();
				this.physicalResistance = Integer.valueOf(coreAttributes.getElementsByTagName("physicalResistance").item(0).getTextContent());
				setUpFemininity(Femininity.valueOf(coreAttributes.getElementsByTagName("femininity").item(0).getTextContent()));
				this.slot = InventorySlot.valueOf(coreAttributes.getElementsByTagName("slot").item(0).getTextContent());
				this.rarity = Rarity.valueOf(coreAttributes.getElementsByTagName("rarity").item(0).getTextContent());
				
				enchantmentLimit = -1;
				try {
					enchantmentLimit = Integer.valueOf(coreAttributes.getElementsByTagName("enchantmentLimit").item(0).getTextContent());
				} catch(Exception ex) {
				}
				
				this.clothingSet = !coreAttributes.getElementsByTagName("clothingSet").item(0).hasChildNodes()
									? null
									: ClothingSet.valueOf(coreAttributes.getElementsByTagName("clothingSet").item(0).getTextContent());
				
				this.pathName = clothingXMLFile.getParentFile().getAbsolutePath() + "/" + coreAttributes.getElementsByTagName("imageName").item(0).getTextContent();
				
				this.pathNameEquipped = !coreAttributes.getElementsByTagName("imageEquippedName").item(0).hasChildNodes()
									? null
									: coreAttributes.getElementsByTagName("imageEquippedName").item(0).getTextContent();
				
				this.effects = defaultEffects;

				this.blockedPartsList = defaultBlockedParts;
				
				this.incompatibleSlots = defaultIncompatibleSlots;
				
				List<Colour> importedPrimaryColours = new ArrayList<>();
				try {
					if(((Element)coreAttributes.getElementsByTagName("primaryColours").item(0)).getAttribute("values").isEmpty()) {
						Element primaryColoursElement = ((Element)coreAttributes.getElementsByTagName("primaryColours").item(0));
						if(primaryColoursElement.getElementsByTagName("colour").getLength() > 0) {
							for(int i=0; i<primaryColoursElement.getElementsByTagName("colour").getLength(); i++){
								importedPrimaryColours.add(Colour.valueOf(((Element)coreAttributes.getElementsByTagName("primaryColours").item(0)).getTextContent()));
							}
						}
					} else {
						importedPrimaryColours = ColourListPresets.valueOf(((Element)coreAttributes.getElementsByTagName("primaryColours").item(0)).getAttribute("values")).getPresetColourList();
					}
				} catch(Exception ex) {
					System.err.println("AbstractClothingType loading failed. Cause: 'primaryColours' element unable to be parsed.");
				}

				List<Colour> importedPrimaryColoursDye = new ArrayList<>();
				try {
					if(((Element)coreAttributes.getElementsByTagName("primaryColoursDye").item(0)).getAttribute("values").isEmpty()) {
						Element primaryColoursElement = ((Element)coreAttributes.getElementsByTagName("primaryColoursDye").item(0));
						if(primaryColoursElement.getElementsByTagName("colour").getLength() > 0) {
							for(int i=0; i<primaryColoursElement.getElementsByTagName("colour").getLength(); i++){
								importedPrimaryColoursDye.add(Colour.valueOf(((Element)coreAttributes.getElementsByTagName("primaryColoursDye").item(0)).getTextContent()));
							}
						}
					} else {
						importedPrimaryColoursDye = ColourListPresets.valueOf(((Element)coreAttributes.getElementsByTagName("primaryColoursDye").item(0)).getAttribute("values")).getPresetColourList();
					}
				} catch(Exception ex) {
					System.err.println("AbstractClothingType loading failed. Cause: 'primaryColoursDye' element unable to be parsed.");
				}

				List<Colour> importedSecondaryColours = new ArrayList<>();
				try {
					if(((Element)coreAttributes.getElementsByTagName("secondaryColours").item(0)).getAttribute("values").isEmpty()) {
						Element secondaryColoursElement = ((Element)coreAttributes.getElementsByTagName("secondaryColours").item(0));
						if(secondaryColoursElement.getElementsByTagName("colour").getLength() > 0) {
							for(int i=0; i<secondaryColoursElement.getElementsByTagName("colour").getLength(); i++){
								importedSecondaryColours.add(Colour.valueOf(((Element)coreAttributes.getElementsByTagName("secondaryColours").item(0)).getTextContent()));
							}
						}
					} else {
						importedSecondaryColours = ColourListPresets.valueOf(((Element)coreAttributes.getElementsByTagName("secondaryColours").item(0)).getAttribute("values")).getPresetColourList();
					}
				} catch(Exception ex) {
					System.err.println("AbstractClothingType loading failed. Cause: 'secondaryColours' element unable to be parsed.");
				}

				List<Colour> importedSecondaryColoursDye = new ArrayList<>();
				try {
					if(((Element)coreAttributes.getElementsByTagName("secondaryColoursDye").item(0)).getAttribute("values").isEmpty()) {
						Element secondaryColoursElement = ((Element)coreAttributes.getElementsByTagName("secondaryColoursDye").item(0));
						if(secondaryColoursElement.getElementsByTagName("colour").getLength() > 0) {
							for(int i=0; i<secondaryColoursElement.getElementsByTagName("colour").getLength(); i++){
								importedSecondaryColoursDye.add(Colour.valueOf(((Element)coreAttributes.getElementsByTagName("secondaryColoursDye").item(0)).getTextContent()));
							}
						}
					} else {
						importedSecondaryColoursDye = ColourListPresets.valueOf(((Element)coreAttributes.getElementsByTagName("secondaryColoursDye").item(0)).getAttribute("values")).getPresetColourList();
					}
				} catch(Exception ex) {
					System.err.println("AbstractClothingType loading failed. Cause: 'secondaryColoursDye' element unable to be parsed.");
				}

				List<Colour> importedTertiaryColours = new ArrayList<>();
				try {
					if(((Element)coreAttributes.getElementsByTagName("tertiaryColours").item(0)).getAttribute("values").isEmpty()) {
						Element tertiaryColoursElement = ((Element)coreAttributes.getElementsByTagName("tertiaryColours").item(0));
						if(tertiaryColoursElement.getElementsByTagName("colour").getLength() > 0) {
							for(int i=0; i<tertiaryColoursElement.getElementsByTagName("colour").getLength(); i++){
								importedTertiaryColours.add(Colour.valueOf(((Element)coreAttributes.getElementsByTagName("tertiaryColours").item(0)).getTextContent()));
							}
						}
					} else {
						importedTertiaryColours = ColourListPresets.valueOf(((Element)coreAttributes.getElementsByTagName("tertiaryColours").item(0)).getAttribute("values")).getPresetColourList();
					}
				} catch(Exception ex) {
					System.err.println("AbstractClothingType loading failed. Cause: 'tertiaryColours' element unable to be parsed.");
				}

				List<Colour> importedTertiaryColoursDye = new ArrayList<>();
				try {
					if(((Element)coreAttributes.getElementsByTagName("tertiaryColoursDye").item(0)).getAttribute("values").isEmpty()) {
						Element tertiaryColoursElement = ((Element)coreAttributes.getElementsByTagName("tertiaryColoursDye").item(0));
						if(tertiaryColoursElement.getElementsByTagName("colour").getLength() > 0) {
							for(int i=0; i<tertiaryColoursElement.getElementsByTagName("colour").getLength(); i++){
								importedTertiaryColoursDye.add(Colour.valueOf(((Element)coreAttributes.getElementsByTagName("colour").item(i)).getTextContent()));
							}
						}
					} else {
						importedTertiaryColoursDye = ColourListPresets.valueOf(((Element)coreAttributes.getElementsByTagName("tertiaryColoursDye").item(0)).getAttribute("values")).getPresetColourList();
					}
				} catch(Exception ex) {
					System.err.println("AbstractClothingType loading failed. Cause: 'tertiaryColoursDye' element unable to be parsed.");
				}
				
				setUpColours(
						importedPrimaryColours,
						importedPrimaryColoursDye,
						importedSecondaryColours,
						importedSecondaryColoursDye,
						importedTertiaryColours,
						importedTertiaryColoursDye);
				
				this.itemTags = defaultItemTags;
				
				finalSetUp();

			} catch(Exception ex) {
				System.err.println("ClothingType was unable to be loaded from file!");
			}
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
		return true;
	}	

	public String getCannotBeEquippedText(GameCharacter characterClothingOwner) {
		return "";
	}
	
	public boolean isMufflesSpeech() {
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
