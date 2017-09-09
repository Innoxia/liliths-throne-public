package com.base.game.inventory.clothing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.base.game.character.GameCharacter;
import com.base.game.character.attributes.Attribute;
import com.base.game.character.body.types.PenisType;
import com.base.game.character.body.types.VaginaType;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.AbstractCoreItem;
import com.base.game.inventory.InventorySlot;
import com.base.game.inventory.Rarity;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.66
 * @author Innoxia
 */
public abstract class AbstractClothing extends AbstractCoreItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private AbstractClothingType clothingType;

	private boolean sealed, cummedIn, enchantmentKnown, badEnchantment;
	private List<DisplacementType> displacedList;

	private Attribute coreEnchantment;

	public AbstractClothing(AbstractClothingType clothingType, Colour colour, boolean allowRandomEnchantment) {
		super(clothingType.getName(), clothingType.getPathName(), clothingType.getAvailableColours().contains(colour) ? colour : clothingType.getAvailableColours().get(Util.random.nextInt(clothingType.getAvailableColours().size())),
				clothingType.getRarity(), clothingType.getAttributeModifiers());

		this.clothingType = clothingType;
		
		sealed = false;
		cummedIn = false;
		enchantmentKnown = true;

		displacedList = new ArrayList<>();

		if (attributeModifiers.isEmpty() && allowRandomEnchantment && getClothingType().getRarity() == Rarity.COMMON) {
			int chance = Util.random.nextInt(100) + 1;
			Attribute rndAtt = Attribute.attributeBonusesForEnchanting.get(Util.random.nextInt(Attribute.attributeBonusesForEnchanting.size()));
			
			int maximumEnchantStrength = 5 + (5 * getClothingType().getIncompatibleSlots().size());
			
			if (chance <= 20) {
				attributeModifiers.put(rndAtt, -(Util.random.nextInt(maximumEnchantStrength)+1));
				sealed=true;
				coreEnchantment = rndAtt;
				badEnchantment = true;
				enchantmentKnown = false;
				rarity = Rarity.JINXED;
				
			} else if (chance >= 70) {
				if(chance>=90) {
					Attribute rndAtt2 = Attribute.attributeBonusesForEnchanting.get(Util.random.nextInt(Attribute.attributeBonusesForEnchanting.size()));
					while(rndAtt2==rndAtt) {
						rndAtt2 = Attribute.attributeBonusesForEnchanting.get(Util.random.nextInt(Attribute.attributeBonusesForEnchanting.size()));
					}
					
					rarity = Rarity.RARE;
					attributeModifiers.put(rndAtt, Util.random.nextInt(maximumEnchantStrength)+1);
					attributeModifiers.put(rndAtt2, Util.random.nextInt(maximumEnchantStrength)+1);
					
				} else {
					rarity = Rarity.UNCOMMON;
					attributeModifiers.put(rndAtt, Util.random.nextInt(maximumEnchantStrength)+1);
				}
				coreEnchantment = rndAtt;
				badEnchantment = false;
				enchantmentKnown = false;
			}

		} else if (!attributeModifiers.isEmpty()) {
			for (Entry<Attribute, Integer> e : attributeModifiers.entrySet()) {
				coreEnchantment = e.getKey();
				badEnchantment = e.getValue() < 0;
				break;
			}
		}
	}
	
	public AbstractClothing(AbstractClothingType clothingType, Colour colour, Map<Attribute, Integer> enchantmentMap) {
		super(clothingType.getName(), clothingType.getPathName(), clothingType.getAvailableColours().contains(colour) ? colour : clothingType.getAvailableColours().get(Util.random.nextInt(clothingType.getAvailableColours().size())),
				clothingType.getRarity(), clothingType.getAttributeModifiers());

		this.clothingType = clothingType;

		sealed = false;
		cummedIn = false;
		enchantmentKnown = true;

		displacedList = new ArrayList<>();

		attributeModifiers = enchantmentMap;

		badEnchantment = false;
		rarity = Rarity.UNCOMMON;
		if(enchantmentMap!=null) {
			if(enchantmentMap.keySet().size()>1) {
				rarity = Rarity.RARE;
			}
		}
		enchantmentKnown = false;
		
		int highestEnchantment = 0;
		for(Entry<Attribute, Integer> entry : attributeModifiers.entrySet()){
			if(entry.getValue()<0){
				badEnchantment = true;
				rarity = Rarity.JINXED;
			}
			if(entry.getValue()>highestEnchantment){
				coreEnchantment = entry.getKey();
				highestEnchantment = entry.getValue();
			}
		}
	}
	
	
	@Override
	public boolean equals (Object o) {
		if(super.equals(o)){
			if(o instanceof AbstractClothing){
				if(((AbstractClothing)o).getClothingType().equals(getClothingType())
						&& ((AbstractClothing)o).isSealed()==sealed
						&& ((AbstractClothing)o).isDirty()==cummedIn
						&& ((AbstractClothing)o).isEnchantmentKnown()==enchantmentKnown
						&& ((AbstractClothing)o).isBadEnchantment()==badEnchantment
						&& ((AbstractClothing)o).getCoreEnchantment()==coreEnchantment
						&& ((AbstractClothing)o).getDisplacedList().equals(displacedList)){
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + getClothingType().hashCode();
		result = 31 * result + (sealed ? 1 : 0);
		result = 31 * result + (cummedIn ? 1 : 0);
		result = 31 * result + (enchantmentKnown ? 1 : 0);
		result = 31 * result + (badEnchantment ? 1 : 0);
		if(coreEnchantment != null)
			result = 31 * result + coreEnchantment.hashCode();
		result = 31 * result + displacedList.hashCode();
		return result;
	}
	
	/**
	 * @return the name of a css class to use as a displayed rarity in inventory screens
	 */
	@Override
	public String getDisplayRarity() {
		if (!enchantmentKnown) {
			return "unknown";
		}
		return super.getDisplayRarity();
	}

	private StringBuilder descriptionSB;

	@Override
	public String getDescription() {
		descriptionSB = new StringBuilder("<p>" + getClothingType().getDescription() + "</p>");

		// Physical resistance
		descriptionSB.append("<p>" + (getClothingType().isPlural() ? "They" : "It") + " provide" + (getClothingType().isPlural() ? "" : "s") + " <b>" + getClothingType().getPhysicalResistance() + "</b> <b style='color: "
				+ Attribute.RESISTANCE_PHYSICAL.getColour().toWebHexString() + ";'> " + Attribute.RESISTANCE_PHYSICAL.getName() + "</b>");

		if (!attributeModifiers.isEmpty()) {
			if (enchantmentKnown) {
				int i = 0;
				for (Entry<Attribute, Integer> e : attributeModifiers.entrySet()) {
					if (i + 1 == attributeModifiers.size())
						descriptionSB.append(" and ");
					else
						descriptionSB.append(", ");

					descriptionSB.append(" <b>" + e.getValue() + "</b> <b style='color: " + e.getKey().getColour().toWebHexString() + ";'> " + e.getKey().getName() + "</b>");
					i++;
				}
			} else {
				descriptionSB.append(" and " + (getClothingType().isPlural() ? "have" : "has") + " an <b style='color: " + Colour.RARITY_UNKNOWN.toWebHexString() + ";'>unknown enchantment</b>");
			}
		}
		descriptionSB.append(".</p>");

		if (enchantmentKnown) {
			descriptionSB.append("<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "have" : "has") + " a value of <b style='color: " + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol()
					+ "</b> <b>" + getValue() + "</b>.");
		} else {
			descriptionSB.append("</br>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "have" : "has") + " an <b>unknown value</b>!");
		}
		descriptionSB.append("</p>");

		if (getClothingType().getClothingSet() != null)
			descriptionSB.append("<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "are" : "is") + " part of the <b style='color:" + Colour.RARITY_EPIC.toWebHexString() + ";'>"
					+ getClothingType().getClothingSet().getName() + "</b> set." + "</p>");

		return descriptionSB.toString();
	}

	public AbstractClothingType getClothingType() {
		return clothingType;
	}

	// For figuring out value, use 5*physical resistance +
	// Common: -
	// Rare: 20 + 5*attribute
	// Epic: 60 + 5*attribute + 5*set bonus attribute
	// Legendary: 100 + 5*attribute + 5*secondAttribute + 5*set bonus attribute
	@Override
	public int getValue() {
		int runningTotal = 1;

		switch (rarity) {
			case JINXED:
				return 1;
			case COMMON:
				runningTotal = 10;
				break;
			case UNCOMMON:
				runningTotal = 20;
				break;
			case RARE:
				runningTotal = 30;
				break;
			case EPIC:
				runningTotal = 60;
				break;
			case LEGENDARY:
				runningTotal = 100;
				break;
		}

		if (colourShade == Colour.CLOTHING_PLATINUM) {
			runningTotal *= 4;
			
		} else if (colourShade == Colour.CLOTHING_GOLD) {
			runningTotal *= 3;
			
		} else if (colourShade == Colour.CLOTHING_ROSE_GOLD) {
			runningTotal *= 2.5;
			
		} else if (colourShade == Colour.CLOTHING_SILVER) {
			runningTotal *= 2;
		}
		
		if(rarity!=Rarity.EPIC && rarity!=Rarity.LEGENDARY) {
			if (attributeModifiers != null) {
				for (Integer i : attributeModifiers.values()) {
					runningTotal += i * 5;
				}
			}
			
			if (getClothingType().getClothingSet() != null) {
				if (getClothingType().getClothingSet().getAssociatedStatusEffect().getAttributeModifiers(Main.game.getPlayer()) != null) {
					for (Float f : getClothingType().getClothingSet().getAssociatedStatusEffect().getAttributeModifiers(Main.game.getPlayer()).values()) {
						runningTotal += f * 5;
					}
				}
			}
		}
		
		if (runningTotal <= 0)
			runningTotal = 1;

		return runningTotal;
	}
	
	@Override
	public int getPrice(float modifier) {
		if (!enchantmentKnown) {
			return 5;
		}
		return super.getPrice(modifier);
	}

	/**
	 * @param withDeterminer
	 *            True if you want the determiner to prefix the name
	 * @return A string in the format "blue shirt" or "a blue shirt"
	 */
	public String getName(boolean withDeterminer) {
		return (withDeterminer ? (getClothingType().isPlural() ? getClothingType().getDeterminer() + " " : (Util.isVowel(getColour().getName().charAt(0)) ? "an " : "a ")) : "") + getColour().getName() + " " + name;
	}

	/**
	 * @param withRarityColour
	 *            If true, the name will be coloured to its rarity.
	 * @return A string in the format "Blue cap of frostbite" or
	 *         "Gold circlet of anti-magic"
	 */
	public String getDisplayName(boolean withRarityColour) {
		if (!enchantmentKnown)
			return Util.capitaliseSentence(getColour().getName()) + " " + (withRarityColour ? (" <span style='color: " + Colour.RARITY_UNKNOWN.toWebHexString() + ";'>" + name + "</span>") : name);
		else
			return Util.capitaliseSentence(getColour().getName()) + " " + (withRarityColour ? (" <span style='color: " + rarity.getColour().toWebHexString() + ";'>" + name + "</span>") : name);
	}

	@Override
	public String getSVGString() {
		return getClothingType().getSVGImage(colourShade);
	}

	/**
	 * Applies any extra effects this clothing causes when being equipped.
	 * 
	 * @return A description of this clothing being equipped.
	 */
	public String onEquipApplyEffects(GameCharacter clothingOwner, GameCharacter clothingEquipper, boolean rough) {
		if (!enchantmentKnown) {
			enchantmentKnown = true;
			
			pointlessSB.setLength(0);
				if (badEnchantment) {
					clothingOwner.incrementAttribute(Attribute.CORRUPTION, 1);
					pointlessSB.append(
							getClothingType().equipText(clothingOwner, clothingEquipper, rough, this, true)
							+ "<p style='text-align:center;'>"
									+ "<b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Jinx revealed:</b> <b style='color:"+ coreEnchantment.getColour().toWebHexString() + ";'>"
										+ Util.capitaliseSentence(coreEnchantment.getNegativeEnchantment()) + "</b>");
					
					for(Entry<Attribute, Integer> att : attributeModifiers.entrySet()) {
						pointlessSB.append("</br><b>(" + att.getValue()+"</b> <b style='color:"+att.getKey().getColour().toWebHexString()+";'>"+ Util.capitaliseSentence(att.getKey().getName()) + "</b><b>)</b>");
					}
					
					pointlessSB.append("</br>"
							+ "<b>You gain +1</b> <b style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString()+ ";'>core</b> <b style='color:" + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + ";'>corruption</b> <b>from discovering their jinx...</b>"
							+ "</p>");
					
				} else {
					pointlessSB.append(
							getClothingType().equipText(clothingOwner, clothingEquipper, rough, this, true)
							+ "<p style='text-align:center;'>"
									+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Enchantment revealed:</b> <b style='color:"+ coreEnchantment.getColour().toWebHexString() + ";'>"
										+ Util.capitaliseSentence(coreEnchantment.getPositiveEnchantment()) + "</b>");
					
					for(Entry<Attribute, Integer> att : attributeModifiers.entrySet()) {
						pointlessSB.append("</br><b>(+" + att.getValue()+"</b> <b style='color:"+att.getKey().getColour().toWebHexString()+";'>"+ Util.capitaliseSentence(att.getKey().getName()) + "</b><b>)</b>");
					}
					
					pointlessSB.append("</p>");
				}
			
			return pointlessSB.toString();
			
		} else {
			return getClothingType().equipText(clothingOwner, clothingEquipper, rough, this, true);
		}
	}

	/**
	 * @return A description of this clothing being equipped.
	 */
	public String onEquipText(GameCharacter clothingOwner, GameCharacter clothingEquipper, boolean rough) {
		return getClothingType().equipText(clothingOwner, clothingEquipper, rough, this, false);
	}

	/**
	 * Applies any extra effects this clothing causes when being unequipped.
	 * 
	 * @return A description of this clothing being unequipped.
	 */
	public String onUnequipApplyEffects(GameCharacter clothingOwner, GameCharacter clothingEquipper, boolean rough) {
		return getClothingType().unequipText(clothingOwner, clothingEquipper, rough, this, true);
	}

	/**
	 * @return A description of this clothing being unequipped.
	 */
	public String onUnequipText(GameCharacter clothingOwner, GameCharacter clothingEquipper, boolean rough) {
		return getClothingType().unequipText(clothingOwner, clothingEquipper, rough, this, false);
	}

	private static List<String> incompatibleClothing = new ArrayList<>();

	/**
	 * Returns a formatted description of if this clothing is sealed, cummedIn,
	 * too feminine/masculine and what slots it is blocking.
	 */
	public String clothingExtraInformation(GameCharacter equippedToCharacter) {

		if (equippedToCharacter == null) { // The clothing is not currently
											// equipped by anyone:

			incompatibleClothing.clear();
			if (!getClothingType().getIncompatibleSlots().isEmpty()) {
				for (InventorySlot invSlot : getClothingType().getIncompatibleSlots())
					if (Main.game.getPlayer().getClothingInSlot(invSlot) != null)
						incompatibleClothing.add(Main.game.getPlayer().getClothingInSlot(invSlot).getClothingType().getName());
			}
			for (AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped())
				for (InventorySlot invSlot : c.getClothingType().getIncompatibleSlots())
					if (getClothingType().getSlot() == invSlot)
						incompatibleClothing.add(c.getClothingType().getName());

			return (!getClothingType().getIncompatibleSlots().isEmpty() ? "<p>Equipping " + (getClothingType().isPlural() ? "them" : "it") + " will <b style='color: " + Colour.GENERIC_BAD.toWebHexString() + ";'>block</b> your "
					+ Util.inventorySlotsToStringList(getClothingType().getIncompatibleSlots()) + ".</p>" : "")

					+ (sealed && enchantmentKnown
							? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " will <b style='color: " + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + ";'>jinx</b> " + (getClothingType().isPlural() ? "themselves" : "itself") + " onto you!</p>" : "")

					+ (!enchantmentKnown ? "<p>You can either take " + (getClothingType().isPlural() ? "them" : "it") + " to a suitable vendor, or equip " + (getClothingType().isPlural() ? "them" : "it") + " now to identify the <b style='color: "
							+ Colour.RARITY_UNKNOWN.toWebHexString() + ";'>unknown enchantment</b>!</p>" : "")

					+ (cummedIn ? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "have" : "has") + " been <b style='color: " + Colour.CUMMED.toWebHexString() + ";'>covered in sexual fluids</b>!</p>" : "")

					+ (getClothingType().getFemininityMaximum() < Main.game.getPlayer().getFemininity()
							? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "are" : "is") + " <b style='color: " + Colour.MASCULINE.toWebHexString() + ";'>too masculine</b> for you.</p>" : "")

					+ (getClothingType().getFemininityMinimum() > Main.game.getPlayer().getFemininity()
							? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "are" : "is") + " <b style='color: " + Colour.FEMININE.toWebHexString() + ";'>too feminine</b> for you.</p>" : "")

					+ (incompatibleClothing.isEmpty() ? ""
							: "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "are" : "is") + " <b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>incompatible</b> with your "
									+ Util.stringsToStringList(incompatibleClothing, false) + ".</p>");

		} else if (equippedToCharacter.isPlayer()) { // Character is player:
			
			return (!getClothingType().getIncompatibleSlots().isEmpty() ? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "are" : "is") + " <b style='color: " + Colour.GENERIC_BAD.toWebHexString()
					+ ";'>blocking</b> your " + Util.inventorySlotsToStringList(getClothingType().getIncompatibleSlots()) + "!</p>" : "")

					+ (sealed ? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "are" : "is") + " <b style='color: " + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + ";'>jinxed</b> and can't be removed!</p>" : "")

					+ (cummedIn ? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "have" : "has") + " been <b style='color: " + Colour.CUMMED.toWebHexString() + ";'>covered in sexual fluids</b>!</p>" : "")

					+ (getClothingType().getFemininityMaximum() < equippedToCharacter.getFemininity()
							? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "are" : "is") + " <b style='color: " + Colour.MASCULINE.toWebHexString() + ";'>too masculine</b> for you.</p>" : "")

					+ (getClothingType().getFemininityMinimum() > equippedToCharacter.getFemininity()
							? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "are" : "is") + " <b style='color: " + Colour.FEMININE.toWebHexString() + ";'>too feminine</b> for you.</p>" : "")

					+ (!displacedList.isEmpty() ? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "have been" : "has been") + " <b style='color: " + Colour.FEMININE.toWebHexString() + ";'>"
							+ Util.displacementTypesToStringList(displacedList) + "</b>!</p>" : "");

		} else { // Character is an NPC:

			return UtilText.genderParsing(equippedToCharacter,

					(!getClothingType().getIncompatibleSlots().isEmpty() ? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "are" : "is") + " <b style='color: " + Colour.GENERIC_BAD.toWebHexString()
							+ ";'>blocking</b> <her> " + Util.inventorySlotsToStringList(getClothingType().getIncompatibleSlots()) + "!</p>" : "")

							+ (sealed
									? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "are" : "is") + " <b style='color: " + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + ";'>jinxed</b> and can't be removed!</p>"
									: "")

							+ (cummedIn ? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "have" : "has") + " been <b style='color: " + Colour.CUMMED.toWebHexString() + ";'>covered in sexual fluids</b>!</p>" : "")

							+ (getClothingType().getFemininityMaximum() < equippedToCharacter.getFemininity()
									? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "are" : "is") + " <b style='color: " + Colour.MASCULINE.toWebHexString() + ";'>too masculine</b> for <herPro>.</p>" : "")

							+ (getClothingType().getFemininityMinimum() > equippedToCharacter.getFemininity()
									? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "are" : "is") + " <b style='color: " + Colour.FEMININE.toWebHexString() + ";'>too feminine</b> for <herPro>.</p>" : "")

							+ (!displacedList.isEmpty() ? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "have been" : "has been") + " <b style='color: " + Colour.FEMININE.toWebHexString() + ";'>"
									+ Util.displacementTypesToStringList(displacedList) + "</b>!</p>" : ""));
		}
	}
	
	public String getDisplacementBlockingDescriptions(GameCharacter equippedToCharacter){
		descriptionSB = new StringBuilder("<p><b>Displacement types:</b>");
		for(BlockedParts bp : getClothingType().getBlockedPartsList()){
			descriptionSB.append("</br><b>"+Util.capitaliseSentence(bp.displacementType.getDescription())+":</b> ");
			if(equippedToCharacter.isAbleToBeDisplaced(this, bp.displacementType, false, false, equippedToCharacter))
				descriptionSB.append("<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Available</b>");
			else
				descriptionSB.append("<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Blocked</b> by "+equippedToCharacter.getBlockingClothing().getName()+"");
		}
		descriptionSB.append("</p>");
		
		return descriptionSB.toString();
	}

	public List<String> getExtraDescriptions(GameCharacter equippedToCharacter) {
		List<String> descriptionsList = new ArrayList<>();

		if (equippedToCharacter == null) { // The clothing is not currently
											// equipped by anyone:

			incompatibleClothing.clear();
			if (!getClothingType().getIncompatibleSlots().isEmpty()) {
				for (InventorySlot invSlot : getClothingType().getIncompatibleSlots())
					if (Main.game.getPlayer().getClothingInSlot(invSlot) != null)
						incompatibleClothing.add(Main.game.getPlayer().getClothingInSlot(invSlot).getClothingType().getName());
			}
			for (AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped())
				for (InventorySlot invSlot : c.getClothingType().getIncompatibleSlots())
					if (getClothingType().getSlot() == invSlot)
						incompatibleClothing.add(c.getClothingType().getName());

			if (!getClothingType().getIncompatibleSlots().isEmpty()) {
				// descriptionsList.add("-<b style='color:
				// "+Colour.GENERIC_BAD.toWebHexString()+";'>Equipping
				// blocks</b>");
				for (InventorySlot slot : getClothingType().getIncompatibleSlots())
					descriptionsList.add("<b style='color: " + Colour.GENERIC_BAD.toWebHexString() + ";'>Blocks " + Util.capitaliseSentence(slot.getName()) + "</b>");
			}

			if (sealed && enchantmentKnown) {
				descriptionsList.add("<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Jinxed</b>");
			}

			if (cummedIn) {
				descriptionsList.add("<b style='color: " + Colour.CUMMED.toWebHexString() + ";'>Dirty</b>");
			}

			if (getClothingType().getFemininityMaximum() < Main.game.getPlayer().getFemininity()) {
				descriptionsList.add("<b style='color: " + Colour.MASCULINE.toWebHexString() + ";'>Too masculine</b>");
			}

			if (getClothingType().getFemininityMinimum() > Main.game.getPlayer().getFemininity()) {
				descriptionsList.add("<b style='color: " + Colour.FEMININE.toWebHexString() + ";'>Too feminine</b>");
			}

			if (!incompatibleClothing.isEmpty()) {
				descriptionsList.add("<b style='color: " + Colour.GENERIC_BAD.toWebHexString() + ";'>Incompatible with:</b>");
				descriptionsList.addAll(incompatibleClothing);
			}

		} else { // Being worn:

			if (!getClothingType().getIncompatibleSlots().isEmpty()) {
				// descriptionsList.add("-<b style='color:
				// "+Colour.GENERIC_BAD.toWebHexString()+";'>Blocking</b>");
				for (InventorySlot slot : getClothingType().getIncompatibleSlots())
					descriptionsList.add("<b style='color: " + Colour.GENERIC_BAD.toWebHexString() + ";'>Blocking " + Util.capitaliseSentence(slot.getName()) + "</b>");
			}

			if (sealed && enchantmentKnown) {
				descriptionsList.add("<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Jinxed</b>");
			}

			if (cummedIn) {
				descriptionsList.add("<b style='color: " + Colour.CUMMED.toWebHexString() + ";'>Dirty</b>");
			}

			if (getClothingType().getFemininityMaximum() < equippedToCharacter.getFemininity()) {
				descriptionsList.add("<b style='color: " + Colour.MASCULINE.toWebHexString() + ";'>Too masculine</b>");
			}

			if (getClothingType().getFemininityMinimum() > equippedToCharacter.getFemininity()) {
				descriptionsList.add("<b style='color: " + Colour.FEMININE.toWebHexString() + ";'>Too feminine</b>");
			}

			if (!displacedList.isEmpty()) {
				// descriptionsList.add("-<b style='color:
				// "+Colour.GENERIC_BAD.toWebHexString()+";'>Displaced</b>");
				for (DisplacementType dt : displacedList)
					descriptionsList.add("<b style='color: " + Colour.GENERIC_BAD.toWebHexString() + ";'>" + Util.capitaliseSentence(dt.getDescriptionPast()) + "</b>");
			}

		}

		return descriptionsList;
	}

	/**
	 * @return A list of blocked body parts. e.g. "Penis, Anus and Vagina" or
	 *         "Nipples"
	 */
	public String getClothingBlockingDescription(DisplacementType dt, GameCharacter owner, String preFix, String postFix) {
		Set<CoverableArea> coveredAreas = new HashSet<>();// EnumSet.noneOf(CoverableArea.class);

		if (dt == null) {
			for (BlockedParts bp : this.getClothingType().getBlockedPartsList())
				if (!this.getDisplacedList().contains(bp.displacementType))
					coveredAreas.addAll(bp.blockedBodyParts);
		} else {

			for (BlockedParts bp : this.getClothingType().getBlockedPartsList())
				if (bp.displacementType == dt)
					coveredAreas.addAll(bp.blockedBodyParts);
		}

		if (owner.getVaginaType() == VaginaType.NONE)
			coveredAreas.remove(CoverableArea.VAGINA);
		if (owner.getPenisType() == PenisType.NONE)
			coveredAreas.remove(CoverableArea.PENIS);

		if (!coveredAreas.isEmpty())
			return preFix + Util.setToStringListCoverableArea(coveredAreas) + postFix;
		else
			return "";
	}

	public void removeBadEnchantment() {
		this.badEnchantment = false;
		this.rarity = Rarity.COMMON;
		this.coreEnchantment = null;
	}

	public boolean isSealed() {
		return sealed;
	}

	public void setSealed(boolean sealed) {
		this.sealed = sealed;
	}

	public boolean isDirty() {
		return cummedIn;
	}

	public void setDirty(boolean cummedIn) {
		this.cummedIn = cummedIn;
		if(Main.game.getPlayer().getClothingCurrentlyEquipped().contains(this))
			Main.game.getPlayer().updateInventoryListeners();
	}

	public List<DisplacementType> getDisplacedList() {
		return displacedList;
	}

	public boolean isEnchantmentKnown() {
		return enchantmentKnown;
	}

	private StringBuilder pointlessSB = new StringBuilder();
	public String setEnchantmentKnown(boolean enchantmentKnown) {
		pointlessSB.setLength(0);
		this.enchantmentKnown = enchantmentKnown;
		
		if(enchantmentKnown && !attributeModifiers.isEmpty()){
			if (badEnchantment) {
				pointlessSB.append(
						"<p style='text-align:center;'>"
								+ "<b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Jinx revealed:</b> <b style='color:"+ coreEnchantment.getColour().toWebHexString() + ";'>"
									+ Util.capitaliseSentence(coreEnchantment.getNegativeEnchantment()) + "</b>");
				
				for(Entry<Attribute, Integer> att : attributeModifiers.entrySet()) {
					pointlessSB.append("</br><b>(" + att.getValue()+"</b> <b style='color:"+att.getKey().getColour().toWebHexString()+";'>"+ Util.capitaliseSentence(att.getKey().getName()) + "</b><b>)</b>");
				}
				
				pointlessSB.append("</p>");
				
			} else {
				pointlessSB.append(
						"<p style='text-align:center;'>"
								+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Enchantment revealed:</b> <b style='color:"+ coreEnchantment.getColour().toWebHexString() + ";'>"
									+ Util.capitaliseSentence(coreEnchantment.getPositiveEnchantment()) + "</b>");
				
				for(Entry<Attribute, Integer> att : attributeModifiers.entrySet()) {
					pointlessSB.append("</br><b>(+" + att.getValue()+"</b> <b style='color:"+att.getKey().getColour().toWebHexString()+";'>"+ Util.capitaliseSentence(att.getKey().getName()) + "</b><b>)</b>");
				}
				
				pointlessSB.append("</p>");
			}
			
		} else {
			return "";
		}
		
		return pointlessSB.toString();
	}

	public Attribute getCoreEnchantment() {
		return coreEnchantment;
	}

	public boolean isBadEnchantment() {
		return badEnchantment;
	}

}
