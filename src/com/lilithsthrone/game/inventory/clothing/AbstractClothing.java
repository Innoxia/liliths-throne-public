package com.lilithsthrone.game.inventory.clothing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.item.ItemEffect;
import com.lilithsthrone.game.inventory.item.ItemEffectType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;

import java.util.Set;

/**
 * @since 0.1.0
 * @version 0.2.0
 * @author Innoxia
 */
public abstract class AbstractClothing extends AbstractCoreItem implements Serializable, XMLSaving {

	private static final long serialVersionUID = 1L;

	private AbstractClothingType clothingType;
	protected List<ItemEffect> effects;
	
	private Colour secondaryColour, tertiaryColour;
	private boolean cummedIn, enchantmentKnown;
	private List<DisplacementType> displacedList;
	
	public AbstractClothing(AbstractClothingType clothingType, Colour colour, Colour secondaryColour, Colour tertiaryColour, boolean allowRandomEnchantment) {
		super(clothingType.getName(),
				clothingType.getNamePlural(),
				clothingType.getPathName(),
				clothingType.getAllAvailablePrimaryColours().contains(colour) ? colour : clothingType.getAllAvailablePrimaryColours().get(Util.random.nextInt(clothingType.getAllAvailablePrimaryColours().size())),
				clothingType.getRarity(),
				null);

		this.clothingType = clothingType;
		if(clothingType.getEffects()==null) {
			this.effects = new ArrayList<>();
		} else {
			this.effects = new ArrayList<>(clothingType.getEffects());
		}
		
		cummedIn = false;
		enchantmentKnown = true;
		
		this.secondaryColour = secondaryColour;
		this.tertiaryColour = tertiaryColour;

		displacedList = new ArrayList<>();

		if (effects.isEmpty() && allowRandomEnchantment && getClothingType().getRarity() == Rarity.COMMON) {
			int chance = Util.random.nextInt(100) + 1;
			
			List<TFModifier> attributeMods = new ArrayList<>(TFModifier.getClothingAttributeList());
			
			TFModifier rndMod = attributeMods.get(Util.random.nextInt(attributeMods.size()));
			attributeMods.remove(rndMod);
			TFModifier rndMod2 = attributeMods.get(Util.random.nextInt(attributeMods.size()));
			
			if (chance <= 25) { // Jinxed:
				
				effects.add(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SEALING, TFModifier.NONE, TFPotency.BOOST, 0));
				effects.add(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, rndMod, TFPotency.getRandomWeightedNegativePotency(), 0));
				if(chance <10) {
					effects.add(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, rndMod2, TFPotency.getRandomWeightedNegativePotency(), 0));
				}
				
				enchantmentKnown = false;
				
			} else if (chance >= 75) { // Enchanted:
				effects.add(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, rndMod, TFPotency.getRandomWeightedPositivePotency(), 0));
				if(chance > 90) {
					effects.add(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, rndMod2, TFPotency.getRandomWeightedPositivePotency(), 0));
				}
				enchantmentKnown = false;
			}

		}
	}
	
	public AbstractClothing(AbstractClothingType clothingType, Colour colour, Colour secondaryColour, Colour tertiaryColour, List<ItemEffect> effects) {
		super(clothingType.getName(),
				clothingType.getNamePlural(),
				clothingType.getPathName(),
				clothingType.getAllAvailablePrimaryColours().contains(colour) ? colour : clothingType.getAllAvailablePrimaryColours().get(Util.random.nextInt(clothingType.getAllAvailablePrimaryColours().size())),
				clothingType.getRarity(),
				null);

		this.clothingType = clothingType;
		this.effects = clothingType.getEffects();

		cummedIn = false;
		enchantmentKnown = true;

		this.secondaryColour = secondaryColour;
		this.tertiaryColour = tertiaryColour;
		
		displacedList = new ArrayList<>();

		this.effects = effects;

		enchantmentKnown = false;
	}
	
	@Override
	public boolean equals (Object o) {
		if(super.equals(o)){
			if(o instanceof AbstractClothing){
				if(((AbstractClothing)o).getClothingType().equals(getClothingType())
						&& ((AbstractClothing)o).getSecondaryColour()==secondaryColour
						&& ((AbstractClothing)o).getTertiaryColour()==tertiaryColour
						&& ((AbstractClothing)o).isSealed()==this.isSealed()
						&& ((AbstractClothing)o).isDirty()==cummedIn
						&& ((AbstractClothing)o).isEnchantmentKnown()==enchantmentKnown
						&& ((AbstractClothing)o).isBadEnchantment()==this.isBadEnchantment()
						&& ((AbstractClothing)o).getEffects().equals(this.getEffects())
						){
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
		if(getSecondaryColour()!=null) {
			result = 31 * result + getSecondaryColour().hashCode();
		}
		if(getTertiaryColour()!=null) {
			result = 31 * result + getTertiaryColour().hashCode();
		}
		result = 31 * result + (this.isSealed() ? 1 : 0);
		result = 31 * result + (cummedIn ? 1 : 0);
		result = 31 * result + (enchantmentKnown ? 1 : 0);
		result = 31 * result + (this.isBadEnchantment() ? 1 : 0);
		result = 31 * result + this.getEffects().hashCode();
		return result;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("clothing");
		parentElement.appendChild(element);
		
		CharacterUtils.addAttribute(doc, element, "id", this.getClothingType().getId());
		CharacterUtils.addAttribute(doc, element, "colour", this.getColour().toString());
		CharacterUtils.addAttribute(doc, element, "colourSecondary", this.getSecondaryColour().toString());
		CharacterUtils.addAttribute(doc, element, "colourTertiary", this.getTertiaryColour().toString());
		CharacterUtils.addAttribute(doc, element, "isDirty", String.valueOf(this.isDirty()));
		CharacterUtils.addAttribute(doc, element, "enchantmentKnown", String.valueOf(this.isEnchantmentKnown()));
		
		Element innerElement = doc.createElement("effects");
		element.appendChild(innerElement);
		
		for(ItemEffect ie : this.getEffects()) {
			ie.saveAsXML(innerElement, doc);
		}
		
		innerElement = doc.createElement("displacedList");
		element.appendChild(innerElement);
		for(DisplacementType dt : this.getDisplacedList()) {
			Element displacementType = doc.createElement("displacementType");
			innerElement.appendChild(displacementType);
			CharacterUtils.addAttribute(doc, displacementType, "value", dt.toString());
		}
		
		return element;
	}
	
	public static AbstractClothing loadFromXML(Element parentElement, Document doc) {
		AbstractClothing clothing = null;
		
		try {
			clothing = AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId(parentElement.getAttribute("id")), false);
		} catch(Exception ex) {
			System.err.println("Warning: An instance of AbstractClothing was unable to be imported. ("+parentElement.getAttribute("id")+")");
			return null;
		}
		
		if(clothing==null) {
			System.err.println("Warning: An instance of AbstractClothing was unable to be imported. ("+parentElement.getAttribute("id")+")");
			return null;
		}
		
		// Try to load colour:
		try {
			clothing.setColour(Colour.valueOf(parentElement.getAttribute("colour")));
			if(!parentElement.getAttribute("colourSecondary").isEmpty()) {
				Colour secColour = Colour.valueOf(parentElement.getAttribute("colourSecondary"));
				if(clothing.clothingType.getAllAvailableSecondaryColours().contains(secColour)) {
					clothing.setSecondaryColour(secColour);
				}
			}
			if(!parentElement.getAttribute("colourTertiary").isEmpty()) {
				Colour terColour = Colour.valueOf(parentElement.getAttribute("colourTertiary"));
				if(clothing.clothingType.getAllAvailableTertiaryColours().contains(terColour)) {
					clothing.setTertiaryColour(terColour);
				}
			}
		} catch(Exception ex) {
		}

		// Try to load core features:
		try {
			if(!parentElement.getAttribute("sealed").isEmpty()) {
				clothing.setSealed(Boolean.valueOf(parentElement.getAttribute("sealed")));
			}
			clothing.setDirty(Boolean.valueOf(parentElement.getAttribute("isDirty")));
			clothing.setEnchantmentKnown(Boolean.valueOf(parentElement.getAttribute("enchantmentKnown")));
		} catch(Exception ex) {
		}
		
		// Try to load attributes:
		
		if(parentElement.getElementsByTagName("attributeModifiers")!=null && parentElement.getElementsByTagName("attributeModifiers").getLength()>0) {
			if(clothing.getClothingType().getClothingSet()==null) {
				clothing.getEffects().clear();
				
				Element element = (Element)parentElement.getElementsByTagName("attributeModifiers").item(0);
				for(int i=0; i<element.getElementsByTagName("modifier").getLength(); i++){
					Element e = ((Element)element.getElementsByTagName("modifier").item(i));
					try {
						Attribute att = Attribute.valueOf(e.getAttribute("attribute"));
						int value = Integer.valueOf(e.getAttribute("value"));
						
						TFPotency pot = TFPotency.BOOST;
						if(value <= -5) {
							pot = TFPotency.MAJOR_DRAIN;
						} else if(value <= -3) {
							pot = TFPotency.DRAIN;
						} else if(value <= -1) {
							pot = TFPotency.MINOR_DRAIN;
						} else if(value <= 1) {
							pot = TFPotency.MINOR_BOOST;
						} else if(value <= 3) {
							pot = TFPotency.BOOST;
						} else {
							pot = TFPotency.MAJOR_BOOST;
						}
						
						for(TFModifier mod : TFModifier.getClothingAttributeList()) {
							if(mod.getAssociatedAttribute()==att) {
								clothing.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, mod, pot, 0));
								break;
							}
						}
						
					} catch(Exception ex) {
					}
				}
			}
			
		} else {
			try {
				clothing.getEffects().clear();
				
				Element element = (Element)parentElement.getElementsByTagName("effects").item(0);
				for(int i=0; i<element.getElementsByTagName("effect").getLength(); i++){
					Element e = ((Element)element.getElementsByTagName("effect").item(i));
					clothing.addEffect(ItemEffect.loadFromXML(e, doc));
				}
			} catch(Exception ex) {
			}
		}

		// Try to load displacements:
		try {
			clothing.displacedList = new ArrayList<>();
			Element displacementElement = (Element)parentElement.getElementsByTagName("displacedList").item(0);
			for(int i=0; i<displacementElement.getElementsByTagName("displacementType").getLength(); i++){
				Element e = ((Element)displacementElement.getElementsByTagName("displacementType").item(i));
				
				DisplacementType dt = DisplacementType.valueOf(e.getAttribute("value"));
				boolean displacementTypeFound = false;
				for (BlockedParts bp : clothing.getClothingType().getBlockedPartsList()) {
					if (bp.displacementType == dt)
						displacementTypeFound = true;
				}
				if(displacementTypeFound)
					clothing.displacedList.add(dt);
				else
					System.err.println("Warning: Invalid displacement");
			}
		} catch(Exception ex) {
		}
		
		return clothing;
	}
	
	public Colour getSecondaryColour() {
		return secondaryColour;
	}

	public void setSecondaryColour(Colour secondaryColour) {
		this.secondaryColour = secondaryColour;
	}

	public Colour getTertiaryColour() {
		return tertiaryColour;
	}

	public void setTertiaryColour(Colour tertiaryColour) {
		this.tertiaryColour = tertiaryColour;
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

	private static StringBuilder descriptionSB = new StringBuilder();

	public String getTypeDescription() {
		if(this.getClothingType().equals(ClothingType.NECK_BREEDER_COLLAR)) {
			return"A <span style='color:"+this.getColour().toWebHexString()+"; text-shadow: 0px 0px 4px "+this.getColour().getShades()[4]+";'>glowing "+this.getColour().getName()+"</span> leather collar,"
						+ " with bold metal lettering attached to the front spelling out the word 'BREEDER'.";
		} else {
			return this.getClothingType().getDescription();
		}
	}
	
	@Override
	public String getDescription() {
		descriptionSB.setLength(0);
		
		descriptionSB.append(
				"<p>"
					+ getTypeDescription()
				+ "</p>");
		
		// Physical resistance
		descriptionSB.append("<p>" + (getClothingType().isPlural() ? "They" : "It") + " provide" + (getClothingType().isPlural() ? "" : "s") + " <b>" + getClothingType().getPhysicalResistance() + "</b> <b style='color: "
				+ Attribute.RESISTANCE_PHYSICAL.getColour().toWebHexString() + ";'> " + Attribute.RESISTANCE_PHYSICAL.getName() + "</b>.</p>");

		if (enchantmentKnown) {
			if (!this.getEffects().isEmpty()) {
				descriptionSB.append("<p>Effects:");
				for (ItemEffect e : this.getEffects()) {
					if(e.getPrimaryModifier()!=TFModifier.CLOTHING_ATTRIBUTE) {
						for(String s : e.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())) {
							descriptionSB.append("</br>"+ s);
						}
					}
				}
				for(Entry<Attribute, Integer> entry : this.getAttributeModifiers().entrySet()) {
					descriptionSB.append("</br>"+ 
							(entry.getValue()<0
									?"[style.boldBad("+entry.getValue()+")] "
									:"[style.boldGood(+"+entry.getValue()+")] ")
							+ "<b style='color:"+entry.getKey().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(entry.getKey().getName())+"</b>");
				}
				descriptionSB.append("</p>");
			}
					
			descriptionSB.append("<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "have" : "has") + " a value of " + UtilText.formatAsMoney(getValue()) + ".");
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

	@Override
	public Rarity getRarity() {
		if(rarity==Rarity.LEGENDARY) {
			return rarity;
		}
		if(this.getClothingType().getClothingSet()!=null || rarity==Rarity.RARE) {
			return Rarity.EPIC;
		}
		
		if(this.isSealed()) {
			return Rarity.JINXED;
		}
		if(this.getEffects().size()>1) {
			return Rarity.RARE;
		}
		if(!this.getEffects().isEmpty()) {
			return Rarity.UNCOMMON;
		}
		
		return Rarity.COMMON;
	}
	
	@Override
	public int getValue() {
		float runningTotal = this.getClothingType().getBaseValue();

		if (colourShade == Colour.CLOTHING_PLATINUM) {
			runningTotal *= 2f;
			
		} else if (colourShade == Colour.CLOTHING_GOLD) {
			runningTotal *= 1.75f;
			
		} else if (colourShade == Colour.CLOTHING_ROSE_GOLD) {
			runningTotal *= 1.5f;
			
		} else if (colourShade == Colour.CLOTHING_SILVER) {
			runningTotal *= 1.25f;
		}
		
		if(rarity==Rarity.JINXED) {
			runningTotal *= 0.5;
		}
		
		float attributeBonuses = 0;//getModifiedDropoffValue
		if (attributeModifiers != null) {
			for (Integer i : attributeModifiers.values()) {
				attributeBonuses += i * 15;
			}
		}
		
		if (getClothingType().getClothingSet() != null) {
			if (getClothingType().getClothingSet().getAssociatedStatusEffect().getAttributeModifiers(Main.game.getPlayer()) != null) {
				for (Float f : getClothingType().getClothingSet().getAssociatedStatusEffect().getAttributeModifiers(Main.game.getPlayer()).values()) {
					attributeBonuses += f * 15;
				}
			}
		}

		attributeBonuses = Util.getModifiedDropoffValue(attributeBonuses, 500);
		
		runningTotal += Math.max(0, attributeBonuses);
		
		if (runningTotal < 1) {
			runningTotal = 1;
		}
		
		return (int) runningTotal;
	}
	
	@Override
	public int getPrice(float modifier) {
		if (!enchantmentKnown) {
			return 50;
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
	
	public String getName(boolean withDeterminer, boolean withRarityColour) {
		if (!enchantmentKnown) {
			return (withDeterminer ? (getClothingType().isPlural() ? getClothingType().getDeterminer() + " " : (Util.isVowel(getColour().getName().charAt(0)) ? "an " : "a ")) : " ")
					+ getColour().getName() + (withRarityColour ? (" <span style='color: " + Colour.RARITY_UNKNOWN.toWebHexString() + ";'>" + name + "</span>") : " "+name);
		} else {
			return (withDeterminer ? (getClothingType().isPlural() ? getClothingType().getDeterminer() + " " : (Util.isVowel(getColour().getName().charAt(0)) ? "an " : "a ")) : " ")
					+ getColour().getName() + (withRarityColour ? (" <span style='color: " + rarity.getColour().toWebHexString() + ";'>" + name + "</span>") : " "+name);
		}
	}

	/**
	 * @param withRarityColour
	 *            If true, the name will be coloured to its rarity.
	 * @return A string in the format "Blue cap of frostbite" or
	 *         "Gold circlet of anti-magic"
	 */
	public String getDisplayName(boolean withRarityColour) {
		return Util.capitaliseSentence(getColour().getName()) + " "
				+ (withRarityColour
					? (" <span style='color: " + (!this.isEnchantmentKnown()?Colour.RARITY_UNKNOWN:this.getRarity().getColour()).toWebHexString() + ";'>" + name + "</span>")
					: name)
				+(!this.getEffects().isEmpty() && this.isEnchantmentKnown() && this.getRarity()!=Rarity.LEGENDARY && this.getRarity()!=Rarity.EPIC
						? " "+getEnchantmentPostfix(withRarityColour, "b")
						: "");
	}

	@Override
	public String getSVGString() {
		return getClothingType().getSVGImage(colourShade, secondaryColour, tertiaryColour);
	}
	
	public String getSVGEquippedString(GameCharacter character) {
		return getClothingType().getSVGEquippedImage(character, colourShade, secondaryColour, tertiaryColour);
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
				if (this.isBadEnchantment()) {
					clothingOwner.incrementAttribute(Attribute.MAJOR_CORRUPTION, 1);
					pointlessSB.append(
							getClothingType().equipText(clothingOwner, clothingEquipper, rough, this, true)
							+ "<p style='text-align:center;'>"
									+ "<b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Jinx revealed:</b> "+getDisplayName(true));
					
					for(Entry<Attribute, Integer> att : attributeModifiers.entrySet()) {
						pointlessSB.append("</br><b>(" + att.getValue()+"</b> <b style='color:"+att.getKey().getColour().toWebHexString()+";'>"+ Util.capitaliseSentence(att.getKey().getName()) + "</b><b>)</b>");
					}
					
					pointlessSB.append("</br>"
							+ "<b>"+(clothingOwner.isPlayer()?"You gain":UtilText.parse(clothingOwner, "[npc.Name] gains"))
									+" +1</b> <b style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString()+ ";'>core</b> <b style='color:" + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + ";'>corruption</b> <b>from discovering their jinx...</b>"
							+ "</p>");
					
				} else {
					pointlessSB.append(
							getClothingType().equipText(clothingOwner, clothingEquipper, rough, this, true)
							+ "<p style='text-align:center;'>"
									+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Enchantment revealed:</b> "+getDisplayName(true));
					
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
	 * Returns a formatted description of if this clothing is sealed, cummedIn, too feminine/masculine and what slots it is blocking.
	 */
	public String clothingExtraInformation(GameCharacter equippedToCharacter) {

		if (equippedToCharacter == null) { // The clothing is not currently equipped by anyone:

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
					
					+ (Main.game.getPlayer().getClothingInSlot(this.getClothingType().getSlot())!=null && Main.game.getPlayer().getClothingInSlot(this.getClothingType().getSlot()).getClothingType().isDiscardedOnUnequip()
						?"[style.boldBad(Equipping this will cause the "+Main.game.getPlayer().getClothingInSlot(this.getClothingType().getSlot()).getName()+" you're already wearing to be discarded!)]"
						:"")

					+ (this.isSealed() && enchantmentKnown
							? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " will <b style='color: " + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + ";'>jinx</b> " + (getClothingType().isPlural() ? "themselves" : "itself") + " onto you!</p>" : "")

					+ (!enchantmentKnown ? "<p>You can either take " + (getClothingType().isPlural() ? "them" : "it") + " to a suitable vendor, or equip " + (getClothingType().isPlural() ? "them" : "it") + " now to identify the <b style='color: "
							+ Colour.RARITY_UNKNOWN.toWebHexString() + ";'>unknown enchantment</b>!</p>" : "")

					+ (cummedIn ? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "have" : "has") + " been <b style='color: " + Colour.CUMMED.toWebHexString() + ";'>covered in sexual fluids</b>!</p>" : "")

					+ (getClothingType().getFemininityMaximum() < Main.game.getPlayer().getFemininityValue()
							? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "are" : "is") + " <b style='color: " + Colour.MASCULINE.toWebHexString() + ";'>too masculine</b> for you.</p>" : "")

					+ (getClothingType().getFemininityMinimum() > Main.game.getPlayer().getFemininityValue()
							? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "are" : "is") + " <b style='color: " + Colour.FEMININE.toWebHexString() + ";'>too feminine</b> for you.</p>" : "")

					+ (incompatibleClothing.isEmpty() ? ""
							: "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "are" : "is") + " <b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>incompatible</b> with your "
									+ Util.stringsToStringList(incompatibleClothing, false) + ".</p>");

		} else if (equippedToCharacter.isPlayer()) { // Character is player:
			
			return (!getClothingType().getIncompatibleSlots().isEmpty() ? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "are" : "is") + " <b style='color: " + Colour.GENERIC_BAD.toWebHexString()
					+ ";'>blocking</b> your " + Util.inventorySlotsToStringList(getClothingType().getIncompatibleSlots()) + "!</p>" : "")

					+ (this.isSealed()
							? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "are" : "is") + " <b style='color: " + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + ";'>jinxed</b> and can't be removed!</p>"
							: this.getClothingType().isDiscardedOnUnequip()?"[style.boldBad(Removing your "+this.getName()+" will cause it to be discarded!)]":"")

					+ (cummedIn ? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "have" : "has") + " been <b style='color: " + Colour.CUMMED.toWebHexString() + ";'>covered in sexual fluids</b>!</p>" : "")

					+ (getClothingType().getFemininityMaximum() < equippedToCharacter.getFemininityValue()
							? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "are" : "is") + " <b style='color: " + Colour.MASCULINE.toWebHexString() + ";'>too masculine</b> for you.</p>" : "")

					+ (getClothingType().getFemininityMinimum() > equippedToCharacter.getFemininityValue()
							? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "are" : "is") + " <b style='color: " + Colour.FEMININE.toWebHexString() + ";'>too feminine</b> for you.</p>" : "")

					+ (!displacedList.isEmpty() ? "<p>" + (getClothingType().isPlural() ? "They" : "It") + " " + (getClothingType().isPlural() ? "have been" : "has been") + " <b style='color: " + Colour.FEMININE.toWebHexString() + ";'>"
							+ Util.displacementTypesToStringList(displacedList) + "</b>!</p>" : "");

		} else { // Character is an NPC:

			return UtilText.parse(equippedToCharacter,
					(!getClothingType().getIncompatibleSlots().isEmpty()
							? "<p>"
									+ (getClothingType().isPlural() ? "They " : "It ")+ (getClothingType().isPlural() ? "are" : "is") + " [style.boldBad(blocking)] [npc.her] "
									+ Util.inventorySlotsToStringList(getClothingType().getIncompatibleSlots()) + "!"
								+ "</p>"
							: "")
					
					+ (this.isSealed()
							? "<p>"
								+ (getClothingType().isPlural() ? "They " : "It ") + (getClothingType().isPlural() ? "are" : "is") + " [style.boldCorruption(jinxed)] and can't be removed!"
								+ "</p>"
							: this.getClothingType().isDiscardedOnUnequip()?"[style.boldBad(Removing [npc.name]'s "+this.getName()+" will cause it to be discarded!)]":"")

					+ (cummedIn
							? "<p>"
									+ (getClothingType().isPlural() ? "They " : "It ") + (getClothingType().isPlural() ? "have" : "has") + " been [style.boldDirty(covered in sexual fluids)]!"
								+ "</p>"
							: "")

					+ (getClothingType().getFemininityMaximum() < equippedToCharacter.getFemininityValue()
							? "<p>"
									+ (getClothingType().isPlural() ? "They " : "It ") + (getClothingType().isPlural() ? "are" : "is") + " [style.boldMasculine(too masculine)] for [npc.herHim]."
								+ "</p>"
							: "")

					+ (getClothingType().getFemininityMinimum() > equippedToCharacter.getFemininityValue()
							? "<p>"
									+ (getClothingType().isPlural() ? "They " : "It ") + (getClothingType().isPlural() ? "are" : "is") + " [style.boldFeminine(too feminine)] for [npc.herHim]."
								+ "</p>"
							: "")

					+ (!displacedList.isEmpty()
							? "<p>"
									+ (getClothingType().isPlural() ? "They " : "It ") + (getClothingType().isPlural() ? "have been" : "has been") 
										+ " <b style='color: " + Colour.GENERIC_BAD.toWebHexString() + ";'>"+ Util.displacementTypesToStringList(displacedList) + "</b>!"
								+ "</p>"
							: ""));
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

			if (this.isSealed() && enchantmentKnown) {
				descriptionsList.add("<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Jinxed</b>");
			}

			if (cummedIn) {
				descriptionsList.add("<b style='color: " + Colour.CUMMED.toWebHexString() + ";'>Dirty</b>");
			}

			if (getClothingType().getFemininityMaximum() < Main.game.getPlayer().getFemininityValue()) {
				descriptionsList.add("<b style='color: " + Colour.MASCULINE.toWebHexString() + ";'>Too masculine</b>");
			}

			if (getClothingType().getFemininityMinimum() > Main.game.getPlayer().getFemininityValue()) {
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

			if (this.isSealed() && enchantmentKnown) {
				descriptionsList.add("<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Jinxed</b>");
			}

			if (cummedIn) {
				descriptionsList.add("<b style='color: " + Colour.CUMMED.toWebHexString() + ";'>Dirty</b>");
			}

			if (getClothingType().getFemininityMaximum() < equippedToCharacter.getFemininityValue()) {
				descriptionsList.add("<b style='color: " + Colour.MASCULINE.toWebHexString() + ";'>Too masculine</b>");
			}

			if (getClothingType().getFemininityMinimum() > equippedToCharacter.getFemininityValue()) {
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
		
		if(owner!=null) {
			if (owner.getVaginaType() == VaginaType.NONE)
				coveredAreas.remove(CoverableArea.VAGINA);
			if (owner.getPenisType() == PenisType.NONE)
				coveredAreas.remove(CoverableArea.PENIS);
		}
		
		if (!coveredAreas.isEmpty())
			return preFix + Util.setToStringListCoverableArea(coveredAreas) + postFix;
		else
			return "";
	}

	public void removeBadEnchantments() {
		this.getEffects().removeIf(e -> e.getPrimaryModifier() == TFModifier.CLOTHING_ATTRIBUTE && e.getPotency().isNegative());
	}

	public boolean isSealed() {
		for(ItemEffect effect : this.getEffects()) {
			if(effect.getPrimaryModifier()==TFModifier.CLOTHING_SEALING) {
				return true;
			}
		}
		return false;
	}

	public void setSealed(boolean sealed) {
		if(sealed) {
			this.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SEALING, TFModifier.NONE, TFPotency.BOOST, 0));
		} else {
			this.getEffects().removeIf(e -> e.getPrimaryModifier() == TFModifier.CLOTHING_SEALING);
		}
	}

	public boolean isDirty() {
		return cummedIn;
	}

	public void setDirty(boolean cummedIn) {
		this.cummedIn = cummedIn;
		if(Main.game.getPlayer()!=null) {
			if(Main.game.getPlayer().getClothingCurrentlyEquipped().contains(this)) {
				Main.game.getPlayer().updateInventoryListeners();
			}
		}
	}

	public List<DisplacementType> getDisplacedList() {
		return displacedList;
	}
	
	public void clearDisplacementList() {
		displacedList.clear();
	}

	public boolean isEnchantmentKnown() {
		return enchantmentKnown;
	}

	private StringBuilder pointlessSB = new StringBuilder();
	public String setEnchantmentKnown(boolean enchantmentKnown) {
		pointlessSB.setLength(0);
		this.enchantmentKnown = enchantmentKnown;
		
		if(enchantmentKnown && !attributeModifiers.isEmpty()){
			if (isBadEnchantment()) {
				pointlessSB.append(
						"<p style='text-align:center;'>"
								+ "<b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Jinx revealed:</b> "+getDisplayName(true));
				
				for(Entry<Attribute, Integer> att : attributeModifiers.entrySet()) {
					pointlessSB.append("</br><b>(" + att.getValue()+"</b> <b style='color:"+att.getKey().getColour().toWebHexString()+";'>"+ Util.capitaliseSentence(att.getKey().getName()) + "</b><b>)</b>");
				}
				
				pointlessSB.append("</p>");
				
			} else {
				pointlessSB.append(
						"<p style='text-align:center;'>"
								+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Enchantment revealed:</b> "+getDisplayName(true));
				
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
		Attribute att = Attribute.MAJOR_PHYSIQUE;
		int max = 0;
		for(Entry<Attribute, Integer> entry : getAttributeModifiers().entrySet()) {
			if(entry.getValue() > max) {
				att = entry.getKey();
				max = entry.getValue();
			}
		}
		
		return att;
	}
	
	public String getEnchantmentPostfix(boolean coloured, String tag) {
		if(!this.getEffects().isEmpty()) {
			for(ItemEffect ie : this.getEffects()) {
				if(ie.getPrimaryModifier() == TFModifier.CLOTHING_ENSLAVEMENT) {
					return "of "+(coloured?"<"+tag+" style='color:"+TFModifier.CLOTHING_ENSLAVEMENT.getColour().toWebHexString()+";'>enslavement</"+tag+">":"enslavement");
					
				} else if(ie.getPrimaryModifier() == TFModifier.TF_MOD_FETISH_BEHAVIOUR || ie.getPrimaryModifier() == TFModifier.TF_MOD_FETISH_BODY_PART) {
					return "of "+(coloured?"<"+tag+" style='color:"+Colour.FETISH.toWebHexString()+";'>"+ie.getSecondaryModifier().getDescriptor()+"</"+tag+">":ie.getSecondaryModifier().getDescriptor());
					
				} else if(ie.getPrimaryModifier() == TFModifier.CLOTHING_ATTRIBUTE) {
					String name = (this.isBadEnchantment()?this.getCoreEnchantment().getNegativeEnchantment():this.getCoreEnchantment().getPositiveEnchantment());
					return "of "+(coloured?"<"+tag+" style='color:"+this.getCoreEnchantment().getColour().toWebHexString()+";'>"+name+"</"+tag+">":name);
					
				} else if(ie.getPrimaryModifier() == TFModifier.CLOTHING_SEALING) {
					return "of "+(coloured?"<"+tag+" style='color:"+Colour.SEALED.toWebHexString()+";'>sealing</"+tag+">":"sealing");
					
				} else {
					return "of "+(coloured?"<"+tag+" style='color:"+Colour.TRANSFORMATION_GENERIC.toWebHexString()+";'>transformation</"+tag+">":"transformation");
				}
			}
		}
		return "";
	}

	public boolean isBadEnchantment() {
		return this.getEffects().stream().anyMatch(e -> e.getPrimaryModifier() == TFModifier.CLOTHING_ATTRIBUTE && e.getPotency().isNegative());
	}

	public boolean isEnslavementClothing() {
		return this.getEffects().stream().anyMatch(e -> e.getPrimaryModifier() == TFModifier.CLOTHING_ENSLAVEMENT);
	}
	
	@Override
	public List<ItemEffect> getEffects() {
		return effects;
	}

	public void addEffect(ItemEffect effect) {
//		if(!this.getEffects().contains(effect)) {
			effects.add(effect);
//		}
	}
	
	@Override
	public Map<Attribute, Integer> getAttributeModifiers() {
		attributeModifiers.clear();
		
		for(ItemEffect ie : getEffects()) {
			if(ie.getPrimaryModifier() == TFModifier.CLOTHING_ATTRIBUTE) {
				if(attributeModifiers.containsKey(ie.getSecondaryModifier().getAssociatedAttribute())) {
					attributeModifiers.put(ie.getSecondaryModifier().getAssociatedAttribute(), attributeModifiers.get(ie.getSecondaryModifier().getAssociatedAttribute()) + ie.getPotency().getClothingBonusValue());
				} else {
					attributeModifiers.put(ie.getSecondaryModifier().getAssociatedAttribute(), ie.getPotency().getClothingBonusValue());
				}
			}
		}
		
		return attributeModifiers;
	}
	
	@Override
	public int getEnchantmentLimit() {
		return clothingType.getEnchantmentLimit();
	}
	
	@Override
	public AbstractItemEffectType getEnchantmentEffect() {
		return clothingType.getEnchantmentEffect();
	}
	
	@Override
	public AbstractCoreType getEnchantmentItemType(List<ItemEffect> effects) {
		return clothingType.getEnchantmentItemType(effects);
	}
	
	@Override
	public TFEssence getRelatedEssence() {
		return clothingType.getRelatedEssence();
	}
}
