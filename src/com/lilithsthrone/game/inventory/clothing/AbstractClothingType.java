package com.lilithsthrone.game.inventory.clothing;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemEffect;
import com.lilithsthrone.game.inventory.item.ItemEffectType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.84
 * @version 0.2.0
 * @author Innoxia
 */
public abstract class AbstractClothingType extends AbstractCoreType {

	protected static final long serialVersionUID = 1L;
	
	private String determiner, name, namePlural, description, pathName, pathNameEquipped;

	private boolean plural;
	private int baseValue;
	private int physicalResistance;
	private int femininityMinimum;
	private int femininityMaximum;
	private Femininity femininityRestriction;
	private InventorySlot slot;

	// Access and block stuff:
	private List<BlockedParts> blockedPartsList;
	private List<InventorySlot> incompatibleSlots;

	private Map<Colour, Map<Colour, Map<Colour, String>>> SVGStringMap;
	private Map<Colour, Map<Colour, Map<Colour, String>>> SVGStringEquippedMap;

	protected List<ItemEffect> effects;
	
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
			List<Colour> availableTertiaryDyeColours) {
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
				availableTertiaryColours, availableTertiaryDyeColours);
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
			List<Colour> availableTertiaryDyeColours) {
		
		this.baseValue = baseValue;
		
		this.determiner = determiner;
		this.plural = plural;
		this.name = name;
		this.namePlural = namePlural;
		this.description = description;

		this.physicalResistance = physicalResistance;

		this.femininityRestriction = femininityRestriction;

		if (femininityRestriction == null) {
			femininityMinimum = 0;
			femininityMaximum = 100;
		} else if (femininityRestriction == Femininity.FEMININE) {
			femininityMinimum = Femininity.ANDROGYNOUS.getMinimumFemininity();
			femininityMaximum = 100;
		} else if (femininityRestriction == Femininity.ANDROGYNOUS) {
			femininityMinimum = Femininity.ANDROGYNOUS.getMinimumFemininity();
			femininityMaximum = Femininity.ANDROGYNOUS.getMaximumFemininity();
		} else if (femininityRestriction == Femininity.MASCULINE) {
			femininityMinimum = 0;
			femininityMaximum = Femininity.ANDROGYNOUS.getMaximumFemininity();
		}

		this.slot = slot;
		this.rarity = rarity;
		this.clothingSet = clothingSet;

		this.pathName = pathName;
		this.pathNameEquipped = pathNameEquipped;

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
		

		SVGStringMap = new HashMap<>();
		SVGStringEquippedMap = new HashMap<>();

		displacementTypesAvailableWithoutNONE = new ArrayList<>();
		for (BlockedParts bp : this.blockedPartsList) {
			if (bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
				displacementTypesAvailableWithoutNONE.add(bp.displacementType);
			}
		}
		Collections.sort(displacementTypesAvailableWithoutNONE);
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
			String playerEquipping, String playerEquippingNpc, String playerEquippingNpcRough, String npcEquipping, String npcEquippingPlayer, String npcEquippingPlayerRough) {
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
				return UtilText.parse(clothingOwner, npcEquipping);
			}
		}
	}
	
	public String equipText(GameCharacter clothingOwner, GameCharacter clothingEquipper, boolean rough, AbstractClothing clothing, boolean applyEffects) {
		if (clothingEquipper.isPlayer()) {
			if(clothingOwner.isPlayer()) {
				return "You equip the "+clothing.getName()+"";
			} else {
				if(rough) {
					return UtilText.parse(clothingOwner, "You roughly force "+clothing.getName(true)+" onto [npc.name].");
				} else {
					return UtilText.parse(clothingOwner, "You put "+clothing.getName(true)+" on [npc.name].");
				}
			}
			
		} else {
			if (clothingOwner.isPlayer()) {
				if(rough) {
					return UtilText.parse(clothingEquipper, "[npc.Name] roughly forces "+clothing.getName(true)+" onto you.");
				} else {
					return UtilText.parse(clothingEquipper, "[npc.Name] puts "+clothing.getName(true)+" on you.");
				}
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] equips "+clothing.getName(true)+".");
			}
		}
	}

	public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
		if (clothingRemover.isPlayer()) {
			if(clothingOwner.isPlayer()) {
				return "You unequip the "+clothing.getName()+"";
			} else {
				if(rough) {
					return UtilText.parse(clothingOwner, "You roughly take off [npc.name]'s "+clothing.getName()+".");
				} else {
					return UtilText.parse(clothingOwner, "You take off [npc.name]'s "+clothing.getName()+".");
				}
			}
			
		} else {
			if (clothingOwner.isPlayer()) {
				if(rough) {
					return UtilText.parse(clothingRemover, "[npc.Name] roughly takes off your "+clothing.getName()+".");
				} else {
					return UtilText.parse(clothingRemover, "[npc.Name] takes off your "+clothing.getName()+".");
				}
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] unequips [npc.her] "+clothing.getName()+".");
			}
		}
	}

	public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
		if (clothingRemover.isPlayer()) {
			if(clothingOwner.isPlayer()) {
				return "You "+dt.getDescription()+" your "+this.getName()+"";
			} else {
				if(rough) {
					return UtilText.parse(clothingOwner, "You roughly "+dt.getDescription()+" [npc.name]'s "+this.getName()+".");
				} else {
					return UtilText.parse(clothingOwner, "You "+dt.getDescription()+" [npc.name]'s "+this.getName()+".");
				}
			}
			
		} else {
			if (clothingOwner.isPlayer()) {
				if(rough) {
					return UtilText.parse(clothingRemover, "[npc.Name] roughly "+dt.getDescriptionThirdPerson()+" your "+this.getName()+".");
				} else {
					return UtilText.parse(clothingRemover, "[npc.Name] "+dt.getDescriptionThirdPerson()+" your "+this.getName()+".");
				}
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] "+dt.getDescriptionThirdPerson()+" [npc.her] "+this.getName()+".");
			}
		}
	}

	public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
		if (clothingRemover.isPlayer()) {
			if(clothingOwner.isPlayer()) {
				return "You "+dt.getOppositeDescription()+" your "+this.getName()+"";
			} else {
				if(rough) {
					return UtilText.parse(clothingOwner, "You roughly "+dt.getOppositeDescription()+" [npc.name]'s "+this.getName()+".");
				} else {
					return UtilText.parse(clothingOwner, "You "+dt.getOppositeDescription()+" [npc.name]'s "+this.getName()+".");
				}
			}
			
		} else {
			if (clothingOwner.isPlayer()) {
				if(rough) {
					return UtilText.parse(clothingRemover, "[npc.Name] roughly "+dt.getOppositeDescriptionThirdPerson()+" your "+this.getName()+".");
				} else {
					return UtilText.parse(clothingRemover, "[npc.Name] "+dt.getOppositeDescriptionThirdPerson()+" your "+this.getName()+".");
				}
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] "+dt.getOppositeDescriptionThirdPerson()+" [npc.her] "+this.getName()+".");
			}
		}
	}
	
	public boolean isCanBeEquipped(GameCharacter clothingOwner) {
		return true;
	}	

	public String getCannotBeEquippedText(GameCharacter characterClothingOwner) {
		return "";
	}	
	
	/**
	 * @return True if this clothing is blocking the CoverableArea from sight.
	 */
	public boolean isBlocksFromSight() {
		return true;
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
							InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/" + pathNameEquipped + ".svg");
							String s = Util.inputStreamToString(is);
							
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
							
							is.close();
		
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
							InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/" + pathName + ".svg");
							String s = Util.inputStreamToString(is);
							
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
							
							is.close();
		
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
		int base = (getClothingSet()==null?5:10);
		return base + getIncompatibleSlots().size()*base;
	}
	
	public ItemEffectType getEnchantmentEffect() {
		return ItemEffectType.CLOTHING;
	}
	
	public TFEssence getRelatedEssence() {
		return TFEssence.ARCANE;
	}
	
	public AbstractClothingType getEnchantmentItemType(List<ItemEffect> effects) {
		return this;
	}

	public int getBreastSizeAdjustment() {
		return 0;
	}

	public int getPenisSizeAdjustment() {
		return 0;
	}
}
