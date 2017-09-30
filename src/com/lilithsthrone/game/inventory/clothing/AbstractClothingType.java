package com.lilithsthrone.game.inventory.clothing;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.84
 * @version 0.1.84
 * @author Innoxia
 */
public abstract class AbstractClothingType extends AbstractCoreType implements Serializable {

	protected static final long serialVersionUID = 1L;
	
	private String determiner, name, namePlural, description, pathName;

	private boolean plural;
	private int physicalResistance, femininityMinimum, femininityMaximum;
	private Femininity femininityRestriction;
	private InventorySlot slot;

	// Access and block stuff:
	private List<BlockedParts> blockedPartsList;
	private List<InventorySlot> incompatibleSlots;

	private Map<Attribute, Integer> attributeModifiers;

	private Map<Colour, String> SVGStringMap;

	private ClothingSet clothingSet;
	private Rarity rarity;
	private List<Colour> availableColours;

	private List<DisplacementType> displacementTypesAvailableWithoutNONE;

	public AbstractClothingType(
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
			Map<Attribute, Integer> attributeModifiers,
			List<BlockedParts> blockedPartsList, List<InventorySlot> incompatibleSlots,
			List<Colour> availableColours) {

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

		// Attribute modifiers:
		if (attributeModifiers != null) {
			this.attributeModifiers = attributeModifiers;
		} else {
			this.attributeModifiers = new EnumMap<>(Attribute.class);
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
		
		this.availableColours = new ArrayList<>();
		if (availableColours == null) {
			this.availableColours.add(Colour.CLOTHING_BLACK);
		} else {
			this.availableColours.addAll(availableColours);
		}

		SVGStringMap = new EnumMap<>(Colour.class);

		displacementTypesAvailableWithoutNONE = new ArrayList<>();
		for (BlockedParts bp : blockedPartsList) {
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
						&& ((AbstractClothingType)o).getAttributeModifiers().equals(getAttributeModifiers())
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
		result = 31 * result + getAttributeModifiers().hashCode();
		if(getClothingSet()!=null)
			result = 31 * result + getClothingSet().hashCode();
		result = 31 * result + getRarity().hashCode();
		return result;
	}
	
	public static AbstractClothing generateClothing(AbstractClothingType clothingType, Colour colourShade, boolean allowRandomEnchantment) {
		Colour c = colourShade;

		if (clothingType.getAvailableColours() != null) {
			if (!clothingType.getAvailableColours().contains(colourShade)) {
				c = clothingType.getAvailableColours().get(Util.random.nextInt(clothingType.getAvailableColours().size()));
			}
		}
		
		return new AbstractClothing(clothingType, c, allowRandomEnchantment) {
			private static final long serialVersionUID = 1L;
		};
	}

	/**
	 * Allows random enchantment. Uses random colour.
	 */
	public static AbstractClothing generateClothing(AbstractClothingType clothingType) {
		return AbstractClothingType.generateClothing(clothingType, clothingType.getAvailableColours().get(Util.random.nextInt(clothingType.getAvailableColours().size())), true);
	}

	/**
	 * Uses random colour.
	 */
	public static AbstractClothing generateClothing(AbstractClothingType clothingType, boolean allowRandomEnchantment) {
		return AbstractClothingType.generateClothing(clothingType, clothingType.getAvailableColours().get(Util.random.nextInt(clothingType.getAvailableColours().size())), allowRandomEnchantment);
	}
	
	/**
	 * Generates clothing with the provided enchantments.
	 */
	public static AbstractClothing generateClothing(AbstractClothingType clothingType, Colour colour, Map<Attribute, Integer> enchantmentMap) {
		return new AbstractClothing(clothingType, colour, enchantmentMap) {
			private static final long serialVersionUID = 1L;
		};
	}
	/**
	 * Uses random colour.
	 */
	public static AbstractClothing generateClothing(AbstractClothingType clothingType, Map<Attribute, Integer> enchantmentMap) {
		return AbstractClothingType.generateClothing(clothingType, clothingType.getAvailableColours().get(Util.random.nextInt(clothingType.getAvailableColours().size())), enchantmentMap);
	}
	
	/**
	 * Generates clothing with a random enchantment.
	 */
	public static AbstractClothing generateClothingWithEnchantment(AbstractClothingType clothingType, Colour colour) {
		Map<Attribute, Integer> enchantments = new EnumMap<>(Attribute.class);
		Attribute rndAtt = Attribute.attributeBonusesForEnchanting.get(Util.random.nextInt(Attribute.attributeBonusesForEnchanting.size()));
		
		enchantments.put(rndAtt, Util.random.nextInt(5)+1);
		
		return new AbstractClothing(clothingType, colour, enchantments) {
			private static final long serialVersionUID = 1L;
		};
	}
	/**
	 * Uses random colour.
	 */
	public static AbstractClothing generateClothingWithEnchantment(AbstractClothingType clothingType) {
		return AbstractClothingType.generateClothingWithEnchantment(clothingType, clothingType.getAvailableColours().get(Util.random.nextInt(clothingType.getAvailableColours().size())));
	}
	
	public String getId() {
		return ClothingType.clothingToIdMap.get(this);
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
	
	protected String getEquipDescriptions(GameCharacter clothingOwner, GameCharacter clothingEquipper, boolean rough,
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
					return UtilText.parse(clothingRemover, "[npc.Name] roughly "+dt.getDescription()+" your "+this.getName()+".");
				} else {
					return UtilText.parse(clothingRemover, "[npc.Name] "+dt.getDescription()+" your "+this.getName()+".");
				}
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] "+dt.getDescription()+" [npc.her] "+this.getName()+".");
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
					return UtilText.parse(clothingRemover, "[npc.Name] roughly "+dt.getOppositeDescription()+" your "+this.getName()+".");
				} else {
					return UtilText.parse(clothingRemover, "[npc.Name] "+dt.getOppositeDescription()+" your "+this.getName()+".");
				}
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] "+dt.getOppositeDescription()+" [npc.her] "+this.getName()+".");
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

	public int getzLayer() {
		return slot.getZLayer();
	}

	public ClothingSet getClothingSet() {
		return clothingSet;
	}

	public List<Colour> getAvailableColours() {
		return availableColours;
	}

	public Map<Colour, String> getSVGStringMap() {
		return SVGStringMap;
	}

	public String getSVGImage(Colour colour) {
		if (!availableColours.contains(colour)) {
			return "";
		}
		
//		if (SVGStringMap.containsKey(colour))  {
//			return SVGStringMap.get(colour);
//			
//		}else {
//			if (availableColours.contains(colour)) {
//				try {
//					InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/" + pathName + ".svg");
//					String s = Util.inputStreamToString(is);
//
//					for (int i = 0; i <= 14; i++)
//						s = s.replaceAll("linearGradient" + i, this.hashCode() + colour.toString() + "linearGradient" + i);
//					s = s.replaceAll("#ff2a2a", colour.getShades()[0]);
//					s = s.replaceAll("#ff5555", colour.getShades()[1]);
//					s = s.replaceAll("#ff8080", colour.getShades()[2]);
//					s = s.replaceAll("#ffaaaa", colour.getShades()[3]);
//					s = s.replaceAll("#ffd5d5", colour.getShades()[4]);
//					
//					SVGStringMap.put(colour, s);
//
//					is.close();
//
//					return s;
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		
//		return "";
//		
//		//TODO
		
		if (SVGStringMap.containsKey(colour) && this != ClothingType.WRIST_WOMENS_WATCH && this != ClothingType.WRIST_MENS_WATCH) {
			return SVGStringMap.get(colour);
			
		}else {
			if (availableColours.contains(colour)) {
				try {
					InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/clothing/" + pathName + ".svg");
					String s = Util.inputStreamToString(is);

					for (int i = 0; i <= 14; i++)
						s = s.replaceAll("linearGradient" + i, this.hashCode() + colour.toString() + "linearGradient" + i);
					s = s.replaceAll("#ff2a2a", colour.getShades()[0]);
					s = s.replaceAll("#ff5555", colour.getShades()[1]);
					s = s.replaceAll("#ff8080", colour.getShades()[2]);
					s = s.replaceAll("#ffaaaa", colour.getShades()[3]);
					s = s.replaceAll("#ffd5d5", colour.getShades()[4]);
					
					// Add minute and hour hands to women's and men's watches:
					s += (this == ClothingType.WRIST_WOMENS_WATCH ? "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;-webkit-transform: rotate(" + ((Main.game.getMinutesPassed() % (60 * 24)) / 2f) + "deg);'>"
							+ SVGImages.SVG_IMAGE_PROVIDER.getWomensWatchHourHand() + "</div>" + "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;-webkit-transform: rotate(" + (Main.game.getMinutesPassed() % (60)) * 6 + "deg);'>"
							+ SVGImages.SVG_IMAGE_PROVIDER.getWomensWatchMinuteHand() + "</div>" : "")
							+ (this == ClothingType.WRIST_MENS_WATCH ? "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;-webkit-transform: rotate(" + ((Main.game.getMinutesPassed() % (60 * 24)) / 2f) + "deg);'>"
									+ SVGImages.SVG_IMAGE_PROVIDER.getMensWatchHourHand() + "</div>" + "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;-webkit-transform: rotate(" + (Main.game.getMinutesPassed() % (60)) * 6
									+ "deg);'>" + SVGImages.SVG_IMAGE_PROVIDER.getMensWatchMinuteHand() + "</div>" : "");
					
					SVGStringMap.put(colour, s);

					is.close();

					return s;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return "";
	}

	public Rarity getRarity() {
		return rarity;
	}

	public Map<Attribute, Integer> getAttributeModifiers() {
		return attributeModifiers;
	}

	public int getPhysicalResistance() {
		return physicalResistance;
	}

	public void setPhysicalResistance(int physicalResistance) {
		this.physicalResistance = physicalResistance;
	}
}
