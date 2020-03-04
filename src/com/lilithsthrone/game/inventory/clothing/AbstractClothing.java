package com.lilithsthrone.game.inventory.clothing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Penis;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
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
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Pattern;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.0
 * @version 0.3.6
 * @author Innoxia
 */
public abstract class AbstractClothing extends AbstractCoreItem implements XMLSaving {

	private InventorySlot slotEquippedTo;
	
	private AbstractClothingType clothingType;
	protected List<ItemEffect> effects;
	
	private Colour secondaryColour;
	private Colour tertiaryColour;
	private boolean dirty;
	private boolean enchantmentKnown;
	private boolean unlocked;
	private List<DisplacementType> displacedList;
	
	private String pattern; // name of the pattern. 
	private Colour patternColour;
	private Colour patternSecondaryColour;
	private Colour patternTertiaryColour;
	
	public AbstractClothing(AbstractClothingType clothingType, Colour colour, Colour secondaryColour, Colour tertiaryColour, boolean allowRandomEnchantment) {
		super(clothingType.getName(),
				clothingType.getNamePlural(),
				clothingType.getPathName(),
				clothingType.getAllAvailablePrimaryColours().contains(colour) ? colour : Util.randomItemFrom(clothingType.getAllAvailablePrimaryColours()),
				clothingType.getRarity(),
				null);

		this.slotEquippedTo = null;
		
		this.clothingType = clothingType;
		if(clothingType.getEffects()==null) {
			this.effects = new ArrayList<>();
		} else {
			this.effects = new ArrayList<>(clothingType.getEffects());
		}
		
		dirty = false;
		enchantmentKnown = true;
		unlocked = false;
		
		this.secondaryColour = secondaryColour;
		this.tertiaryColour = tertiaryColour;

		handlePatternCreation();

		displacedList = new ArrayList<>();

		if(effects.isEmpty() && allowRandomEnchantment && getClothingType().getRarity() == Rarity.COMMON) {
			int chance = Util.random.nextInt(100) + 1;
			
			List<TFModifier> attributeMods = new ArrayList<>(TFModifier.getClothingAttributeList());
			
			TFModifier rndMod = attributeMods.get(Util.random.nextInt(attributeMods.size()));
			attributeMods.remove(rndMod);
			TFModifier rndMod2 = attributeMods.get(Util.random.nextInt(attributeMods.size()));
			
			if(chance <= 20) { // Jinxed:
				
				if(chance <= 1) {
					effects.add(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MAJOR_DRAIN, 0));
				} else if(chance <= 4) {
					effects.add(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.DRAIN, 0));
				} else if(chance <= 10) {
					effects.add(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MINOR_DRAIN, 0));
				} else {
					effects.add(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MINOR_BOOST, 0));
				}
				
				effects.add(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, rndMod, TFPotency.getRandomWeightedNegativePotency(), 0));
				if(chance <10) {
					effects.add(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, rndMod2, TFPotency.getRandomWeightedNegativePotency(), 0));
				}
				
				enchantmentKnown = false;
				
			} else if(chance >= 80) { // Enchanted:
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

		this.slotEquippedTo = null;
		
		this.clothingType = clothingType;

		dirty = false;
		enchantmentKnown = true;
		unlocked = false;

		this.secondaryColour = secondaryColour;
		this.tertiaryColour = tertiaryColour;
		
		handlePatternCreation();
		
		displacedList = new ArrayList<>();
		if(effects!=null) {
			this.effects = new ArrayList<>(effects);
		} else {
			this.effects = new ArrayList<>();
		}

		enchantmentKnown = false;
	}

	public AbstractClothing(AbstractClothing clothing) {
		this(clothing.getClothingType(), clothing.getColour(), clothing.getSecondaryColour(), clothing.getTertiaryColour(), clothing.getEffects());
		
		this.setEnchantmentKnown(null, clothing.isEnchantmentKnown());
		
		this.setPattern(clothing.getPattern());
		this.setPatternColour(clothing.getPatternColour());
		this.setPatternSecondaryColour(clothing.getPatternSecondaryColour());
		this.setPatternTertiaryColour(clothing.getPatternTertiaryColour());
		
		this.displacedList = new ArrayList<>(clothing.getDisplacedList());
		
		this.dirty = clothing.isDirty();

		this.slotEquippedTo = clothing.getSlotEquippedTo();
		this.unlocked = clothing.isUnlocked();
		
		if(!clothing.name.isEmpty()) {
			this.setName(clothing.name);
		}
	}
	
	
	private void handlePatternCreation() {
		if(Math.random()<clothingType.getPatternChance()) {
			pattern = Util.randomItemFrom(clothingType.getDefaultPatterns()).getName();
			patternColour = Util.randomItemFrom(clothingType.getAvailablePatternPrimaryColours());
			
			List<Colour> secondariesExclusive = new ArrayList<>(clothingType.getAvailablePatternSecondaryColours());
			if(secondariesExclusive.size()>1) {
				secondariesExclusive.remove(patternColour);
			}
			patternSecondaryColour = Util.randomItemFrom(secondariesExclusive);

			List<Colour> tertiariesExclusive = new ArrayList<>(clothingType.getAvailablePatternTertiaryColours());
			if(secondariesExclusive.size()>2) {
				secondariesExclusive.remove(patternColour);
				secondariesExclusive.remove(patternSecondaryColour);
			}
			patternTertiaryColour = Util.randomItemFrom(tertiariesExclusive);
			
		} else {
			pattern = "none";
			patternColour = null;
			patternSecondaryColour = null;
			patternTertiaryColour = null;
		}
	}
	
	public String getId() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(ClothingType.getIdFromClothingType(this.getClothingType()));
		sb.append(this.getColour().toString());
		sb.append(this.getSecondaryColour()!=null?this.getSecondaryColour().toString():"n");
		sb.append(this.getTertiaryColour()!=null?this.getTertiaryColour().toString():"n");
		sb.append(this.getPattern()=="none"
				?"nnn"
				:(this.getPatternColour()==null?"n":this.getPatternColour().toString()
						+ (this.getPatternSecondaryColour()==null?"n":this.getPatternSecondaryColour().toString())
						+ (this.getPatternTertiaryColour()==null?"n":this.getPatternTertiaryColour().toString())));
		sb.append(this.isSealed()?"s":"n");
		sb.append(this.isDirty()?"d":"n");
		sb.append(this.isEnchantmentKnown()?"e":"n");
		
		for(ItemEffect ie : this.getEffects()) {
			sb.append(ie.getId());
		}
		
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if(super.equals(o)){
			if(o instanceof AbstractClothing){
				if(((AbstractClothing)o).getClothingType().equals(getClothingType())
						&& ((AbstractClothing)o).getSecondaryColour()==getSecondaryColour()
						&& ((AbstractClothing)o).getTertiaryColour()==getTertiaryColour()
						&& ((AbstractClothing)o).getPattern().equals(getPattern())
						&& (this.getPattern()!="none"
							?((AbstractClothing)o).getPatternColour()==this.getPatternColour()
								&& ((AbstractClothing)o).getPatternSecondaryColour()==this.getPatternSecondaryColour()
								&& ((AbstractClothing)o).getPatternTertiaryColour()==this.getPatternTertiaryColour()
							:true)
						&& ((AbstractClothing)o).isSealed()==this.isSealed()
						&& ((AbstractClothing)o).isDirty()==this.isDirty()
						&& ((AbstractClothing)o).isEnchantmentKnown()==this.isEnchantmentKnown()
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
		result = 31 * result + getPattern().hashCode();
		if(this.getPattern()!="none") {
			if(this.getPatternColour()!=null) {
				result = 31 * result + getPatternColour().hashCode();
			}
			if(getPatternSecondaryColour()!=null) {
				result = 31 * result + getPatternSecondaryColour().hashCode();
			}
			if(getPatternTertiaryColour()!=null) {
				result = 31 * result + getPatternTertiaryColour().hashCode();
			}
		}
		result = 31 * result + (this.isSealed() ? 1 : 0);
		result = 31 * result + (this.isDirty() ? 1 : 0);
		result = 31 * result + (this.isEnchantmentKnown() ? 1 : 0);
		result = 31 * result + (this.isBadEnchantment() ? 1 : 0);
		result = 31 * result + this.getEffects().hashCode();
		return result;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("clothing");
		parentElement.appendChild(element);
		
		CharacterUtils.addAttribute(doc, element, "id", this.getClothingType().getId());
		CharacterUtils.addAttribute(doc, element, "name", name);
		if(slotEquippedTo!=null) {
			CharacterUtils.addAttribute(doc, element, "slotEquippedTo", slotEquippedTo.toString());
		}
		if(this.getColour()!=AbstractClothingType.DEFAULT_COLOUR_VALUE) {
			CharacterUtils.addAttribute(doc, element, "colour", this.getColour().toString());
		}
		if(this.getSecondaryColour()!=AbstractClothingType.DEFAULT_COLOUR_VALUE) {
			CharacterUtils.addAttribute(doc, element, "colourSecondary", this.getSecondaryColour().toString());
		}
		if(this.getTertiaryColour()!=AbstractClothingType.DEFAULT_COLOUR_VALUE) {
			CharacterUtils.addAttribute(doc, element, "colourTertiary", this.getTertiaryColour().toString());
		}
		
		if(!this.getPattern().equals("none")) {
			if(this.getPatternColour()!=AbstractClothingType.DEFAULT_COLOUR_VALUE) {
				CharacterUtils.addAttribute(doc, element, "patternColour", this.getPatternColour().toString());
			}
			if(this.getPatternSecondaryColour()!=AbstractClothingType.DEFAULT_COLOUR_VALUE) {
				CharacterUtils.addAttribute(doc, element, "patternColourSecondary", this.getPatternSecondaryColour().toString());
			}
			if(this.getPatternTertiaryColour()!=AbstractClothingType.DEFAULT_COLOUR_VALUE) {
				CharacterUtils.addAttribute(doc, element, "patternColourTertiary", this.getPatternTertiaryColour().toString());
			}
			CharacterUtils.addAttribute(doc, element, "pattern", this.getPattern());
		}
		
		CharacterUtils.addAttribute(doc, element, "isDirty", String.valueOf(this.isDirty()));
		CharacterUtils.addAttribute(doc, element, "enchantmentKnown", String.valueOf(this.isEnchantmentKnown()));
		
		if(!this.getEffects().isEmpty()) {
			Element innerElement = doc.createElement("effects");
			element.appendChild(innerElement);
			
			for(ItemEffect ie : this.getEffects()) {
				ie.saveAsXML(innerElement, doc);
			}
		}

		if(!this.getDisplacedList().isEmpty()) {
			Element innerElement = doc.createElement("displacedList");
			element.appendChild(innerElement);
			for(DisplacementType dt : this.getDisplacedList()) {
				Element displacementType = doc.createElement("displacementType");
				innerElement.appendChild(displacementType);
				CharacterUtils.addAttribute(doc, displacementType, "value", dt.toString());
			}
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
		

		if(!parentElement.getAttribute("name").isEmpty()) {
			clothing.setName(parentElement.getAttribute("name"));
		}

		if(!parentElement.getAttribute("slotEquippedTo").isEmpty()) {
			clothing.setSlotEquippedTo(InventorySlot.valueOf(parentElement.getAttribute("slotEquippedTo")));
		}
//		else { // If this is pre-version 0.3.3.9, set slot to default:
//			clothing.setSlotEquippedTo(clothing.getClothingType().getEquipSlots().get(0));
//		}
		
		
		// Try to load colours:
		if((clothing.getClothingType().equals(ClothingType.getClothingTypeFromId("BDSM_CHOKER")) && Main.isVersionOlderThan(Game.loadingVersion, "0.2.12.6"))
				|| (clothing.getClothingType().equals(ClothingType.getClothingTypeFromId("innoxia_ankle_shin_guards")) && Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.6"))
				|| (clothing.getClothingType().equals(ClothingType.getClothingTypeFromId("FOOT_TRAINERS")) && Main.isVersionOlderThan(Game.loadingVersion, "0.3.1.2"))
				|| (clothing.getClothingType().equals(ClothingType.getClothingTypeFromId("innoxia_sock_toeless_striped_stockings")) && Main.isVersionOlderThan(Game.loadingVersion, "0.3.2"))) {
			try {
				clothing.setColour(Colour.valueOf(parentElement.getAttribute("colourSecondary")));
				clothing.setSecondaryColour(Colour.valueOf(parentElement.getAttribute("colour")));
			} catch(Exception ex) {
			}
			
		} else if(clothing.getClothingType().equals(ClothingType.getClothingTypeFromId("FOOT_LOW_TOP_SKATER_SHOES")) && Main.isVersionOlderThan(Game.loadingVersion, "0.3.1.2")){
			try {
				clothing.setSecondaryColour(Colour.CLOTHING_WHITE);
				if(!parentElement.getAttribute("colour").isEmpty()) {
					clothing.setColour(Colour.valueOf(parentElement.getAttribute("colour")));
				} else {
					clothing.setColour(AbstractClothingType.DEFAULT_COLOUR_VALUE);
				}
			} catch(Exception ex) {
			}
			
		} else {
			try {
				if(!parentElement.getAttribute("colour").isEmpty()) {
					clothing.setColour(Colour.valueOf(parentElement.getAttribute("colour")));
				} else {
					clothing.setColour(AbstractClothingType.DEFAULT_COLOUR_VALUE);
				}
			} catch(Exception ex) {
			}
			
			try {
				if(!parentElement.getAttribute("colourSecondary").isEmpty()) {
					Colour secColour = Colour.valueOf(parentElement.getAttribute("colourSecondary"));
					if(clothing.getClothingType().getAllAvailableSecondaryColours().contains(secColour)) {
						clothing.setSecondaryColour(secColour);
					}
				} else {
					if(!clothing.getClothingType().getAvailableSecondaryColours().isEmpty()) {
						clothing.setSecondaryColour(clothing.getClothingType().getAvailableSecondaryColours().get(0));
						
					} else if(!clothing.getClothingType().getAvailableSecondaryDyeColours().isEmpty()) {
						clothing.setSecondaryColour(clothing.getClothingType().getAvailableSecondaryDyeColours().get(0));
						
					} else {
						clothing.setSecondaryColour(AbstractClothingType.DEFAULT_COLOUR_VALUE);
					}
				}
			} catch(Exception ex) {
			}
		}
		try {
			if(!parentElement.getAttribute("colourTertiary").isEmpty()) {
				Colour terColour = Colour.valueOf(parentElement.getAttribute("colourTertiary"));
				if(clothing.getClothingType().getAllAvailableTertiaryColours().contains(terColour)) {
					clothing.setTertiaryColour(terColour);
				}
			} else {
				if(!clothing.getClothingType().getAvailableTertiaryColours().isEmpty()) {
					clothing.setTertiaryColour(clothing.getClothingType().getAvailableTertiaryColours().get(0));
					
				} else if(!clothing.getClothingType().getAvailableTertiaryDyeColours().isEmpty()) {
					clothing.setTertiaryColour(clothing.getClothingType().getAvailableTertiaryDyeColours().get(0));
					
				} else {
					clothing.setTertiaryColour(AbstractClothingType.DEFAULT_COLOUR_VALUE);
				}
			}
		} catch(Exception ex) {
		}

		// Try to load patterns:
		try {
			if(!parentElement.getAttribute("pattern").isEmpty()) {
				String pat = parentElement.getAttribute("pattern");
				clothing.setPattern(pat);
			} else {
				clothing.setPattern("none");
			}
			
			if(!parentElement.getAttribute("patternColour").isEmpty()) {
				Colour colour = Colour.valueOf(parentElement.getAttribute("patternColour"));
				clothing.setPatternColour(colour);
			} else {
				clothing.setPatternColour(AbstractClothingType.DEFAULT_COLOUR_VALUE);
			}
			
			if(!parentElement.getAttribute("patternColourSecondary").isEmpty()) {
				Colour secColour = Colour.valueOf(parentElement.getAttribute("patternColourSecondary"));
				clothing.setPatternSecondaryColour(secColour);
			} else {
				clothing.setPatternSecondaryColour(AbstractClothingType.DEFAULT_COLOUR_VALUE);
			}
			
			if(!parentElement.getAttribute("patternColourTertiary").isEmpty()) {
				Colour terColour = Colour.valueOf(parentElement.getAttribute("patternColourTertiary"));
				clothing.setPatternTertiaryColour(terColour);
			} else {
				clothing.setPatternTertiaryColour(AbstractClothingType.DEFAULT_COLOUR_VALUE);
			}
			
		} catch(Exception ex) {
		}

		// Try to load core features:
		try {
			if(!parentElement.getAttribute("sealed").isEmpty()) {
				clothing.setSealed(Boolean.valueOf(parentElement.getAttribute("sealed")));
			}
			clothing.setDirty(null, Boolean.valueOf(parentElement.getAttribute("isDirty")));
			clothing.setEnchantmentKnown(null, Boolean.valueOf(parentElement.getAttribute("enchantmentKnown")));
		} catch(Exception ex) {
		}
		
		// Try to load attributes:
		if(!Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.5") || !clothing.getClothingType().isCondom(clothing.getClothingType().getEquipSlots().get(0))) { // Do not load condom effects from versions prior to 0.3.0.5
			if(parentElement.getElementsByTagName("attributeModifiers")!=null && parentElement.getElementsByTagName("attributeModifiers").getLength()>0) {
				if(clothing.getClothingType().getClothingSet()==null) {
					clothing.getEffects().clear();
					
					Element element = (Element)parentElement.getElementsByTagName("attributeModifiers").item(0);
					NodeList modifierElements = element.getElementsByTagName("modifier");
					for(int i = 0; i < modifierElements.getLength(); i++){
						Element e = ((Element)modifierElements.item(i));
						try {
							Attribute att = Attribute.getAttributeFromId(e.getAttribute("attribute"));
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
							
							for(TFModifier mod : TFModifier.getClothingMajorAttributeList()) {
								if(mod.getAssociatedAttribute()==att) {
									clothing.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, mod, pot, 0));
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
					if(element!=null) {
						NodeList effectElements = element.getElementsByTagName("effect");
						for(int i=0; i<effectElements.getLength(); i++){
							Element e = ((Element)effectElements.item(i));
							ItemEffect ie = ItemEffect.loadFromXML(e, doc);
							if(ie!=null) {
								clothing.addEffect(ie);
							}
						}
					}
				} catch(Exception ex) {
				}
			}
		} else {
			clothing.setEnchantmentKnown(null, true);
		}
		
		// Try to load displacements:
		try {
			clothing.displacedList = new ArrayList<>();
			Element displacementElement = (Element)parentElement.getElementsByTagName("displacedList").item(0);
			if(displacementElement!=null) {
				NodeList displacementTypeElements = displacementElement.getElementsByTagName("displacementType");
				for(int i = 0; i < displacementTypeElements.getLength(); i++){
					Element e = ((Element)displacementTypeElements.item(i));
					
					DisplacementType dt = DisplacementType.valueOf(e.getAttribute("value"));
					boolean displacementTypeFound = false;
					for (BlockedParts bp : clothing.getClothingType().getBlockedPartsMap(null, clothing.getSlotEquippedTo())) {
						if(bp.displacementType == dt) {
							displacementTypeFound = true;
						}
					}
					if(displacementTypeFound) {
						clothing.displacedList.add(dt);
					} else {
						System.err.println("Warning: Invalid displacement");
					}
				}
			}
		} catch(Exception ex) {
		}
		
		return clothing;
	}
	
	public Colour getSecondaryColour() {
		if(secondaryColour==null) {
			return AbstractClothingType.DEFAULT_COLOUR_VALUE;
		}
		return secondaryColour;
	}

	public void setSecondaryColour(Colour secondaryColour) {
		this.secondaryColour = secondaryColour;
	}

	public Colour getTertiaryColour() {
		if(tertiaryColour==null) {
			return AbstractClothingType.DEFAULT_COLOUR_VALUE;
		}
		return tertiaryColour;
	}

	public void setTertiaryColour(Colour tertiaryColour) {
		this.tertiaryColour = tertiaryColour;
	}
	
	/**
	 * Returns the name of a pattern that the clothing has.
	 * @return
	 */
	public String getPattern() {
		if(pattern == null) {
			return "none";
		}
		return pattern;
	}
	
	/**
	 * Changes pattern to specified one. Will not render that pattern if it doesn't exist or the item doesn't support it anyway.
	 * @param pattern
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public Colour getPatternColour() {
		if(patternColour==null) {
			return AbstractClothingType.DEFAULT_COLOUR_VALUE;
		}
		return patternColour;
	}

	public Colour getPatternSecondaryColour() {
		if(patternSecondaryColour==null) {
			return AbstractClothingType.DEFAULT_COLOUR_VALUE;
		}
		return patternSecondaryColour;
	}

	public Colour getPatternTertiaryColour() {
		if(patternTertiaryColour==null) {
			return AbstractClothingType.DEFAULT_COLOUR_VALUE;
		}
		return patternTertiaryColour;
	}

	public void setPatternColour(Colour patternColour) {
		this.patternColour = patternColour;
	}

	public void setPatternSecondaryColour(Colour patternSecondaryColour) {
		this.patternSecondaryColour = patternSecondaryColour;
	}

	public void setPatternTertiaryColour(Colour patternTertiaryColour) {
		this.patternTertiaryColour = patternTertiaryColour;
	}

	private static StringBuilder descriptionSB = new StringBuilder();

	public String getTypeDescription() {
		return this.getClothingType().getDescription();
	}
	
	@Override
	public String getDescription() {
		descriptionSB.setLength(0);
		
		descriptionSB.append(
				"<p>"
					+ getTypeDescription()
				+ "</p>");
		
		// Physical resistance
		if(getClothingType().getPhysicalResistance()>0) {
			descriptionSB.append("<p>"
							+ (getClothingType().isPlural()
									? "They are armoured, and provide "
									: "It is armoured, and provides ")
								+ " <b>" + getClothingType().getPhysicalResistance() + "</b> [style.colourResPhysical(" + Attribute.RESISTANCE_PHYSICAL.getName() + ")]."
							+ "</p>");
		}
		if(enchantmentKnown) {
			if(!this.getEffects().isEmpty()) {
				descriptionSB.append("<p>Effects:");
				for (ItemEffect e : this.getEffects()) {
					if(e.getPrimaryModifier()!=TFModifier.CLOTHING_ATTRIBUTE
							&& e.getPrimaryModifier()!=TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
						for(String s : e.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())) {
							descriptionSB.append("<br/>"+ s);
						}
					}
				}
				for(Entry<Attribute, Integer> entry : this.getAttributeModifiers().entrySet()) {
					descriptionSB.append("<br/>"+ 
							(entry.getValue()<0
									?"[style.boldBad("+entry.getValue()+")] "
									:"[style.boldGood(+"+entry.getValue()+")] ")
							+ "<b style='color:"+entry.getKey().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(entry.getKey().getName())+"</b>");
				}
				descriptionSB.append("</p>");
			}
					
			descriptionSB.append("<p>" + (getClothingType().isPlural() ? "They have" : "It has") + " a value of " + UtilText.formatAsMoney(getValue()) + ".");
		} else {
			descriptionSB.append("<p>" + (getClothingType().isPlural() ? "They have" : "It has") + " an <b>unknown value</b>!");
		}
		
		descriptionSB.append("</p>");

		if(getClothingType().getClothingSet() != null) {
			descriptionSB.append("<p>" + (getClothingType().isPlural() ? "They are" : "It is") + " part of the <b style='color:" + Colour.RARITY_EPIC.toWebHexString() + ";'>"
					+ getClothingType().getClothingSet().getName() + "</b> set." + "</p>");
		}

		return descriptionSB.toString();
	}

	public AbstractClothingType getClothingType() {
		return clothingType;
	}

	public InventorySlot getSlotEquippedTo() {
		return slotEquippedTo;
	}

	public void setSlotEquippedTo(InventorySlot slotEquippedTo) {
		this.slotEquippedTo = slotEquippedTo;
	}

	public boolean isCanBeEquipped(GameCharacter clothingOwner, InventorySlot slot) {
		return this.getClothingType().isAbleToBeBeEquipped(clothingOwner, slot).getKey();
	}

	public String getCannotBeEquippedText(GameCharacter clothingOwner, InventorySlot slot) {
		return UtilText.parse(clothingOwner, this.getClothingType().isAbleToBeBeEquipped(clothingOwner, slot).getValue());
	}
	
	@Override
	public Rarity getRarity() {
		if(this.isCondom(this.getClothingType().getEquipSlots().get(0))) {
			if(!this.getEffects().isEmpty() && this.getEffects().get(0).getPotency().isNegative()) {
				return Rarity.JINXED;
			} else {
				return rarity;
			}
		}
		
		if(rarity==Rarity.LEGENDARY || rarity==Rarity.QUEST) {
			return rarity;
		}
		if(this.getClothingType().getClothingSet()!=null || rarity==Rarity.EPIC) {
			return Rarity.EPIC;
		}
		
		if(this.isSealed() || this.isBadEnchantment()) {
			return Rarity.JINXED;
		}
		if(rarity==Rarity.COMMON) {
			if(this.getEffects().size()>1) {
				return Rarity.RARE;
			}
			if(!this.getEffects().isEmpty()) {
				return Rarity.UNCOMMON;
			}
			
			return Rarity.COMMON;
		}
		
		return rarity;
	}
	
	@Override
	public int getValue() {
		float runningTotal = this.getClothingType().getBaseValue();

		if(colourShade == Colour.CLOTHING_PLATINUM) {
			runningTotal *= 2f;
			
		} else if(colourShade == Colour.CLOTHING_GOLD) {
			runningTotal *= 1.75f;
			
		} else if(colourShade == Colour.CLOTHING_ROSE_GOLD) {
			runningTotal *= 1.5f;
			
		} else if(colourShade == Colour.CLOTHING_SILVER) {
			runningTotal *= 1.25f;
		}
		
		if(rarity==Rarity.JINXED) {
			runningTotal *= 0.5;
		}
		
		float attributeBonuses = 0;//getModifiedDropoffValue
		if(attributeModifiers != null) {
			for (Integer i : attributeModifiers.values()) {
				attributeBonuses += i * 15;
			}
		}
		
		if(getClothingType().getClothingSet() != null) {
			if(getClothingType().getClothingSet().getAssociatedStatusEffect().getAttributeModifiers(Main.game.getPlayer()) != null) {
				for (Float f : getClothingType().getClothingSet().getAssociatedStatusEffect().getAttributeModifiers(Main.game.getPlayer()).values()) {
					attributeBonuses += f * 15;
				}
			}
		}

		attributeBonuses = Util.getModifiedDropoffValue(attributeBonuses, 500);
		
		runningTotal += Math.max(0, attributeBonuses);
		
		if(runningTotal < 1) {
			runningTotal = 1;
		}
		
		return (int) runningTotal;
	}
	
	@Override
	public int getPrice(float modifier) {
		if(!enchantmentKnown) {
			return 50;
		}
		return super.getPrice(modifier);
	}
	
	@Override
	public String getName() {
		return !this.getEffects().isEmpty() || !name.isEmpty()
					?name
					:this.getClothingType().getName();
	}
	
	private String getColourName() {
		if(this.getClothingType().isColourDerivedFromPattern() && this.getPattern()!="none") {
			return this.getPatternColour().getName();
		}
		return getColour().getName();
	}
	
	/**
	 * @param withDeterminer
	 *            True if you want the determiner to prefix the name
	 * @return A string in the format "blue shirt" or "a blue shirt"
	 */
	public String getName(boolean withDeterminer) {
		return (withDeterminer
				? (getClothingType().isPlural()
						? getClothingType().getDeterminer()
						: UtilText.generateSingularDeterminer(getColourName()))
				: "")
				+" "+ getColourName() + " " + getName();
	}
	
	public String getName(boolean withDeterminer, boolean withRarityColour) {
		if(!enchantmentKnown) {
			return (withDeterminer
						? (getClothingType().isPlural()
								? getClothingType().getDeterminer()
								: UtilText.generateSingularDeterminer(getColourName()))
						: "")
					+ " "+getColourName()
					+ (withRarityColour
							? (" <span style='color: " + Colour.RARITY_UNKNOWN.toWebHexString() + ";'>" + getName() + "</span>")
							: " "+getName());
		} else {
			return (withDeterminer
					? (getClothingType().isPlural()
							? getClothingType().getDeterminer()
							: UtilText.generateSingularDeterminer(getColourName()))
					: "")
					+ " "+getColourName()
					+ (withRarityColour
							? (" <span style='color: " + this.getRarity().getColour().toWebHexString() + ";'>" + getName() + "</span>")
							: " "+getName());
		}
	}

	/**
	 * @param withRarityColour
	 *            If true, the name will be coloured to its rarity.
	 * @return A string in the format "Blue cap of frostbite" or
	 *         "Gold circlet of anti-magic"
	 */
	public String getDisplayName(boolean withRarityColour) {
		if(!this.getName().replaceAll("\u00A0"," ").equalsIgnoreCase(this.getClothingType().getName().replaceAll("\u00A0"," "))) { // If this item has a custom name, just display that:
			return (withRarityColour
					? (" <span style='color: " + (!this.isEnchantmentKnown()?Colour.RARITY_UNKNOWN:this.getRarity().getColour()).toWebHexString() + ";'>" + getName() + "</span>")
					: getName());
		}
		
		Colour c = !this.isEnchantmentKnown()?Colour.RARITY_UNKNOWN:this.getRarity().getColour();
		return Util.capitaliseSentence(getColourName())
				+ (!this.getPattern().equalsIgnoreCase("none")?Pattern.getPattern(this.getPattern()).getNiceName():"")
				+ (withRarityColour
					? (" <span style='color: " + c.toWebHexString() + "; "+(this.isVibrator()?"text-shadow: 2px 2px "+c.getShades()[0]+";":"")+"'>" + (this.isVibrator()?"vibrating ":"")+getName() + "</span>")
					: (this.isVibrator()?UtilText.applyVibration("vibrating "+getName(), c):getName()))
				+(!this.getEffects().isEmpty() && this.isEnchantmentKnown() && this.getRarity()!=Rarity.QUEST && this.getRarity()!=Rarity.LEGENDARY && this.getRarity()!=Rarity.EPIC
						? " "+getEnchantmentPostfix(withRarityColour, "span")
						: "");
	}

	@Override
	public String getSVGString() {
		InventorySlot slotEquippedTo = this.getSlotEquippedTo();
		if(slotEquippedTo==null) {
			slotEquippedTo = this.getClothingType().getEquipSlots().get(0);
		}
		return getClothingType().getSVGImage(slotEquippedTo, getColour(), getSecondaryColour(), getTertiaryColour(), pattern, getPatternColour(), getPatternSecondaryColour(), getPatternTertiaryColour());
	}
	
	public String getSVGEquippedString(GameCharacter character) {
		InventorySlot slotEquippedTo = this.getSlotEquippedTo();
		if(slotEquippedTo==null) {
			slotEquippedTo = this.getClothingType().getEquipSlots().get(0);
		}
		return getClothingType().getSVGEquippedImage(character, slotEquippedTo, getColour(), getSecondaryColour(), getTertiaryColour(), pattern, getPatternColour(), getPatternSecondaryColour(), getPatternTertiaryColour());
	}

	/**
	 * Applies any extra effects this clothing causes when being equipped. To be called <b>immediately after</b> equipping clothing.
	 * 
	 * @return A description of this clothing being equipped.
	 */
	public String onEquipApplyEffects(GameCharacter clothingOwner, GameCharacter clothingEquipper, boolean rough) {
		StringBuilder sb = new StringBuilder(); 
		
		if(!enchantmentKnown) {
			this.setEnchantmentKnown(clothingOwner, true);
			
			if(this.isBadEnchantment()) {
				sb.append(getClothingType().equipText(clothingOwner, clothingEquipper, this.getSlotEquippedTo(), rough, this, true));
				
				clothingOwner.incrementAttribute(Attribute.MAJOR_CORRUPTION, 1);
				sb.append("<p style='text-align:center;'>"
								+ "<b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Jinx revealed:</b> "+getDisplayName(true));
				
				for(Entry<Attribute, Integer> att : attributeModifiers.entrySet()) {
					sb.append("<br/><b>(" + att.getValue()+"</b> <b style='color:"+att.getKey().getColour().toWebHexString()+";'>"+ Util.capitaliseSentence(att.getKey().getName()) + "</b><b>)</b>");
				}
				
				sb.append("<br/>"
						+ "<b>"+(clothingOwner.isPlayer()?"You gain":UtilText.parse(clothingOwner, "[npc.Name] gains"))
								+" +1</b> <b style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString()+ ";'>core</b> <b style='color:" + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + ";'>corruption</b> <b>from discovering their jinx...</b>"
						+ "</p>");
				
			} else {
				sb.append(getClothingType().equipText(clothingOwner, clothingEquipper, this.getSlotEquippedTo(), rough, this, true));
				
				sb.append("<p style='text-align:center;'>"
								+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Enchantment revealed:</b> "+getDisplayName(true));
				
				for(Entry<Attribute, Integer> att : attributeModifiers.entrySet()) {
					sb.append("<br/><b>(+" + att.getValue()+"</b> <b style='color:"+att.getKey().getColour().toWebHexString()+";'>"+ Util.capitaliseSentence(att.getKey().getName()) + "</b><b>)</b>");
				}
				
				sb.append("</p>");
			}
			
		} else {
			sb.append(getClothingType().equipText(clothingOwner, clothingEquipper, this.getSlotEquippedTo(), rough, this, true));
		}
		
		if(this.getClothingType().getItemTags(this.getSlotEquippedTo()).contains(ItemTag.DILDO_SELF)) {
			int length = this.getClothingType().getPenetrationSelfLength();
			PenisLength penisLength = PenisLength.getPenisLengthFromInt(length);
			PenetrationGirth girth = PenetrationGirth.getGirthFromInt(this.getClothingType().getPenetrationSelfGirth());
			float diameter = Penis.getGenericDiameter(length, girth);
			
			boolean lubed = true;
			boolean plural = this.getClothingType().isPlural();
			
			sb.append("<p style='text-align:center;'>");
			
			String formattedName = "<span style='color:"+girth.getColour().toWebHexString()+";'>"+girth.getName()+"</span>,"
					+ " <span style='color:"+penisLength.getColour().toWebHexString()+";'>[style.sizeShort("+length+")]</span> "+this.getClothingType().getName();
			
			if(this.getSlotEquippedTo()==InventorySlot.VAGINA) {
				if(clothingOwner.hasHymen()) {
					sb.append(UtilText.parse(clothingOwner,
							"As the "+formattedName+" "+(plural?"push":"pushes")+" inside of [npc.namePos] [npc.pussy], "+(plural?"they":"it")+" [style.colourTerrible("+(plural?"tear":"tears")+" [npc.her] hymen)]!"));
					
					if(clothingOwner.hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
						sb.append("<br/>");
						if(clothingOwner.isVaginaVirgin()) {
							sb.append(UtilText.parse(clothingOwner,
									"Although [npc.her] pussy can no longer be considered completely 'pure', [npc.nameIsFull] still considered to be a virgin, as [npc.sheHasFull] never been penetrated by another person before..."));
						} else {
							sb.append(UtilText.parse(clothingOwner,
									"Having already had sex with someone in the past, the loss of [npc.namePos] hymen causes [npc.herHim] to now consider [npc.herself] a [style.colourTerrible(broken virgin)]!"));
						}
					}
				}
				
				lubed = clothingOwner.getLust() >= clothingOwner.getVaginaWetness().getArousalNeededToGetAssWet();
				//Size:
				if(Main.game.isPenetrationLimitationsEnabled()) {
					if(clothingOwner.hasHymen()) {
						sb.append("<br/>");
					}
					if(length<=clothingOwner.getVaginaMaximumPenetrationDepthComfortable() || clothingOwner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
						sb.append(UtilText.parse(clothingOwner,
								"The full length of the "+formattedName+" [style.colourMinorGood(comfortably fits)] inside of [npc.namePos] [npc.pussy]!"));
						
					} else {
						if(clothingOwner.hasFetish(Fetish.FETISH_MASOCHIST)) {
							sb.append(UtilText.parse(clothingOwner,
									"The "+formattedName+" is [style.colourBad(too long)] to fit comfortably inside of [npc.namePos] [npc.pussy], but as [npc.sheIs] a masochist, [style.colourMinorGood([npc.she] [npc.do]n't mind the discomfort)]!"));
						} else {
							sb.append(UtilText.parse(clothingOwner,
									"The "+formattedName+" is [style.colourBad(too long)] to fit comfortably inside of [npc.namePos] [npc.pussy], and is causing [npc.herHim] [style.colourBad(discomfort)]!"));
						}
					}
				}
				// Girth:
				if(Capacity.isPenetrationDiameterTooBig(clothingOwner.getVaginaElasticity(), clothingOwner.getVaginaStretchedCapacity(), diameter, lubed)) {
					if(Main.game.isPenetrationLimitationsEnabled()) {
						sb.append("<br/>");
					}
					sb.append(UtilText.parse(clothingOwner,
							(plural?"Their":"Its")+" <span style='color:"+girth.getColour().toWebHexString()+";'>[style.sizeShort("+diameter+")]</span>"
									+ " diameter is [style.colourMinorBad(too wide)] for [npc.namePos] [npc.pussy], and is [style.colourBad(stretching)] [npc.herHim] out!"));
				}
				
				clothingOwner.setHymen(false);
				
			} else if(this.getSlotEquippedTo()==InventorySlot.ANUS) {
				lubed = clothingOwner.getLust() >= clothingOwner.getAssWetness().getArousalNeededToGetAssWet();
				//Size:
				if(Main.game.isPenetrationLimitationsEnabled()) {
					if(length<=clothingOwner.getAssMaximumPenetrationDepthComfortable() || clothingOwner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
						sb.append(UtilText.parse(clothingOwner,
								"The full length of the "+formattedName+" [style.colourMinorGood(comfortably fits)] inside of [npc.namePos] [npc.asshole]!"));
						
					} else {
						if(clothingOwner.hasFetish(Fetish.FETISH_MASOCHIST)) {
							sb.append(UtilText.parse(clothingOwner,
									"The "+formattedName+" is [style.colourBad(too long)] to fit comfortably inside of [npc.namePos] [npc.asshole], but as [npc.sheIs] a masochist, [style.colourGood([npc.she] [npc.do]n't mind the discomfort)]!"));
						} else {
							sb.append(UtilText.parse(clothingOwner,
									"The "+formattedName+" is [style.colourBad(too long)] to fit comfortably inside of [npc.namePos] [npc.asshole], and is causing [npc.herHim] [style.colourBad(discomfort)]!"));
						}
					}
				}
				// Girth:
				if(Capacity.isPenetrationDiameterTooBig(clothingOwner.getAssElasticity(), clothingOwner.getAssStretchedCapacity(), diameter, lubed)) {
					if(Main.game.isPenetrationLimitationsEnabled()) {
						sb.append("<br/>");
					}
					sb.append(UtilText.parse(clothingOwner,
							(plural?"Their":"Its")+" <span style='color:"+girth.getColour().toWebHexString()+";'>[style.sizeShort("+diameter+")]</span>"
									+ " diameter is [style.colourMinorBad(too wide)] for [npc.namePos] [npc.asshole], and is [style.colourBad(stretching)] [npc.herHim] out!"));
				}
				
			} else if(this.getSlotEquippedTo()==InventorySlot.NIPPLE) {
				lubed = clothingOwner.getBreastRawStoredMilkValue()>0;
				//Size:
				if(Main.game.isPenetrationLimitationsEnabled()) {
					if(length<=clothingOwner.getNippleMaximumPenetrationDepthComfortable() || clothingOwner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
						sb.append(UtilText.parse(clothingOwner,
								"The full length of the "+formattedName+" [style.colourMinorGood(comfortably fits)] inside of [npc.namePos] [npc.nipple]!"));
						
					} else {
						if(clothingOwner.hasFetish(Fetish.FETISH_MASOCHIST)) {
							sb.append(UtilText.parse(clothingOwner,
									"The "+formattedName+" is [style.colourBad(too long)] to fit comfortably inside of [npc.namePos] [npc.nipple], but as [npc.sheIs] a masochist, [style.colourGood([npc.she] [npc.do]n't mind the discomfort)]!"));
						} else {
							sb.append(UtilText.parse(clothingOwner,
									"The "+formattedName+" is [style.colourBad(too long)] to fit comfortably inside of [npc.namePos] [npc.nipple], and is causing [npc.herHim] [style.colourBad(discomfort)]!"));
						}
					}
				}
				// Girth:
				if(Capacity.isPenetrationDiameterTooBig(clothingOwner.getNippleElasticity(), clothingOwner.getNippleStretchedCapacity(), diameter, lubed)) {
					if(Main.game.isPenetrationLimitationsEnabled()) {
						sb.append("<br/>");
					}
					sb.append(UtilText.parse(clothingOwner,
							(plural?"Their":"Its")+" <span style='color:"+girth.getColour().toWebHexString()+";'>[style.sizeShort("+diameter+")]</span>"
									+ " diameter is [style.colourMinorBad(too wide)] for [npc.namePos] [npc.nipple], and is [style.colourBad(stretching)] [npc.herHim] out!"));
				}
				
			} else if(this.getSlotEquippedTo()==InventorySlot.MOUTH) {
				lubed = true;
				//Size:
				if(Main.game.isPenetrationLimitationsEnabled()) {
					if(length<=clothingOwner.getFaceMaximumPenetrationDepthComfortable() || clothingOwner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
						sb.append(UtilText.parse(clothingOwner,
								"The full length of the "+formattedName+" [style.colourMinorGood(comfortably fits)] down [npc.namePos] throat!"));
						
					} else {
						if(clothingOwner.hasFetish(Fetish.FETISH_MASOCHIST)) {
							sb.append(UtilText.parse(clothingOwner,
									"The "+formattedName+" is [style.colourBad(too long)] to fit comfortably down [npc.namePos] throat, but as [npc.sheIs] a masochist, [style.colourGood([npc.she] [npc.do]n't mind the discomfort)]!"));
						} else {
							sb.append(UtilText.parse(clothingOwner,
									"The "+formattedName+" is [style.colourBad(too long)] to fit comfortably down [npc.namePos] throat, and is causing [npc.herHim] [style.colourBad(discomfort)]!"));
						}
					}
				}
				// Girth:
				if(Capacity.isPenetrationDiameterTooBig(clothingOwner.getFaceElasticity(), clothingOwner.getFaceStretchedCapacity(), diameter, lubed)) {
					if(Main.game.isPenetrationLimitationsEnabled()) {
						sb.append("<br/>");
					}
					sb.append(UtilText.parse(clothingOwner,
							(plural?"Their":"Its")+" <span style='color:"+girth.getColour().toWebHexString()+";'>[style.sizeShort("+diameter+")]</span>"
									+ " diameter is [style.colourMinorBad(too wide)] for [npc.namePos] throat, and is [style.colourBad(stretching)] [npc.herHim] out!"));
				}
			}
			
			sb.append("</p>");
		}
		if(this.getClothingType().getItemTags(this.getSlotEquippedTo()).contains(ItemTag.DILDO_OTHER)) {
			int length = this.getClothingType().getPenetrationOtherLength();
			PenisLength penisLength = PenisLength.getPenisLengthFromInt(length);
			PenetrationGirth girth = PenetrationGirth.getGirthFromInt(this.getClothingType().getPenetrationOtherGirth());
			sb.append("<p style='text-align:center;'>");
				sb.append(UtilText.parse(clothingOwner,
						"[npc.NameIsFull] now able to use [npc.her] <span style='color:"+girth.getColour().toWebHexString()+";'>"+girth.getName()+"</span>,"
								+ " <span style='color:"+penisLength.getColour().toWebHexString()+";'>[style.sizeShort("+length+")]</span> "+this.getClothingType().getName()+" as a [style.colourSex(penetrative object during sex)]!"));
			sb.append("</p>");
		}
		
		//TODO append orifice text
		
		return sb.toString();
	}

	/**
	 * @return A description of this clothing being equipped. To be called <b>immediately after</b> equipping clothing.
	 */
	public String onEquipText(GameCharacter clothingOwner, GameCharacter clothingEquipper, boolean rough) {
		return getClothingType().equipText(clothingOwner, clothingEquipper, this.getSlotEquippedTo(), rough, this, false);
	}

	/**
	 * Applies any extra effects this clothing causes when being unequipped. To be called <b>immediately before</b> actually unequipping.
	 * 
	 * @return A description of this clothing being unequipped.
	 */
	public String onUnequipApplyEffects(GameCharacter clothingOwner, GameCharacter clothingEquipper, boolean rough) {
		return getClothingType().unequipText(clothingOwner, clothingEquipper, this.getSlotEquippedTo(), rough, this, true);
	}

	/**
	 * @return A description of this clothing being unequipped. To be called <b>immediately before</b> actually unequipping.
	 */
	public String onUnequipText(GameCharacter clothingOwner, GameCharacter clothingEquipper, boolean rough) {
		return getClothingType().unequipText(clothingOwner, clothingEquipper, this.getSlotEquippedTo(), rough, this, false);
	}

	private static List<String> incompatibleClothing = new ArrayList<>();

//	/**
//	 * Returns a formatted description of if this clothing is sealed, cummedIn, too feminine/masculine and what slots it is blocking.
//	 */
//	public String clothingExtraInformation(GameCharacter equippedToCharacter, InventorySlot slotToBeEquippedTo) {
//		StringBuilder extraInformationSB = new StringBuilder();
//		
//		if(equippedToCharacter == null) { // The clothing is not currently equipped by anyone:
//			incompatibleClothing.clear();
//			if(!getClothingType().getIncompatibleSlots(null, slotToBeEquippedTo).isEmpty()) {
//				for (InventorySlot invSlot : getClothingType().getIncompatibleSlots(null, slotToBeEquippedTo))
//					if(Main.game.getPlayer().getClothingInSlot(invSlot) != null)
//						incompatibleClothing.add(Main.game.getPlayer().getClothingInSlot(invSlot).getClothingType().getName());
//			}
//			for (AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped())
//				for (InventorySlot invSlot : c.getClothingType().getIncompatibleSlots(null, c.getSlotEquippedTo()))
//					if(slotToBeEquippedTo == invSlot)
//						incompatibleClothing.add(c.getClothingType().getName());
//			
//			if(!getClothingType().getIncompatibleSlots(null, slotToBeEquippedTo).isEmpty()) {
//				extraInformationSB.append("Equipping "+(getClothingType().isPlural()?"them":"it")+" will [style.boldBad(block)] your "+ Util.inventorySlotsToStringList(getClothingType().getIncompatibleSlots(null, slotToBeEquippedTo))+".<br/>");
//			}
//			
//			if(Main.game.getPlayer().getClothingInSlot(slotToBeEquippedTo)!=null && Main.game.getPlayer().getClothingInSlot(slotToBeEquippedTo).getClothingType().isDiscardedOnUnequip(slotToBeEquippedTo)) {
//				extraInformationSB.append("[style.boldBad(Equipping this will cause the "+Main.game.getPlayer().getClothingInSlot(slotToBeEquippedTo).getName()+" you're already wearing to be discarded!)]<br/>");
//			}
//			
//			if(this.isSealed() && enchantmentKnown) {
//				extraInformationSB.append((getClothingType().isPlural() ? "They" : "It") + " will [style.boldJinx(jinx)] " + (getClothingType().isPlural() ? "themselves" : "itself") + " onto you!<br/>");
//			}
//			
//			if(!enchantmentKnown) {
//				extraInformationSB.append("You can either take " + (getClothingType().isPlural() ? "them" : "it") + " to a suitable vendor, or equip " + (getClothingType().isPlural() ? "them" : "it") + " now to identify the"
//						+ " <b style='color: "+ Colour.RARITY_UNKNOWN.toWebHexString() + ";'>unknown enchantment</b>!<br/>");
//			}
//			
//		} else {
//			if(slotToBeEquippedTo!=null) {
//				if(!getClothingType().getIncompatibleSlots(equippedToCharacter, slotToBeEquippedTo).isEmpty()) {
//					extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " [style.boldBad(blocking)] [npc.her] "
//							+ Util.inventorySlotsToStringList(getClothingType().getIncompatibleSlots(equippedToCharacter, slotToBeEquippedTo)) + "!<br/>");
//				}
//			}
//			
//			if(this.isSealed()) {
//				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " [style.boldCorruption(sealed)] and can't be removed!<br/>");
//				
//			} else if(this.getClothingType().isDiscardedOnUnequip(this.getSlotEquippedTo())) {
//				extraInformationSB.append("[style.boldBad(Removing [npc.namePos] "+this.getName()+" will cause "+(getClothingType().isPlural() ? "them" : "it")+" to be discarded!)]<br/>");
//			}
//		}
//
//		if(dirty) {
//			extraInformationSB.append((getClothingType().isPlural() ? "They have" : "It has") + " been <b style='color: " + Colour.CUM.toWebHexString() + ";'>covered in sexual fluids</b>!<br/>");
//		}
//		
//		if(equippedToCharacter == null) {
//			if(getClothingType().getFemininityMaximum() < Main.game.getPlayer().getFemininityValue()) {
//				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " <b style='color: " + Colour.MASCULINE.toWebHexString() + ";'>too masculine</b> for [npc.herHim].<br/>");
//			}
//
//			if(getClothingType().getFemininityMinimum() > Main.game.getPlayer().getFemininityValue()) {
//				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " <b style='color: " + Colour.FEMININE.toWebHexString() + ";'>too feminine</b> for [npc.herHim].<br/>");
//			}
//			if(!incompatibleClothing.isEmpty()) {
//				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " <b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>incompatible</b> with your "
//						+ Util.stringsToStringList(incompatibleClothing, false) + ".<br/>");
//			}
//			
//		} else {
//			if(getClothingType().getFemininityMaximum() < equippedToCharacter.getFemininityValue()) {
//				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " <b style='color: " + Colour.MASCULINE.toWebHexString() + ";'>too masculine</b> for [npc.herHim].<br/>");
//			}
//
//			if(getClothingType().getFemininityMinimum() > equippedToCharacter.getFemininityValue()) {
//				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " <b style='color: " + Colour.FEMININE.toWebHexString() + ";'>too feminine</b> for [npc.herHim].<br/>");
//			}
//			if(!displacedList.isEmpty()) {
//				extraInformationSB.append((getClothingType().isPlural() ? "They have been" : "It has been") 
//						+ " <b style='color: " + Colour.GENERIC_BAD.toWebHexString() + ";'>"+ Util.displacementTypesToStringList(displacedList) + "</b>!<br/>");
//			}
//		}
//
////		List<ItemTag> universalTags = new ArrayList<>();
////		for(int i=0; i<this.getClothingType().getEquipSlots().size();i++) {
////			if(i==0) {
////				universalTags.addAll(this.getClothingType().getItemTags(this.getClothingType().getEquipSlots().get(0)));
////				
////			} else {
////				List<ItemTag> tags = this.getClothingType().getItemTags(this.getClothingType().getEquipSlots().get(i));
////				universalTags.removeIf((it) -> !tags.contains(it));
////			}
////		}
////		List<ItemTag> tagsToBeDescribed= new ArrayList<>(universalTags);
////		if(slotToBeEquippedTo==null) {
////			tagsToBeDescribed = new ArrayList<>(universalTags);
////			
////		} else {
////			tagsToBeDescribed = new ArrayList<>(this.getClothingType().getItemTags(slotToBeEquippedTo));
////			tagsToBeDescribed.removeAll(universalTags);
////		}
////		for(ItemTag tag : tagsToBeDescribed) {
////			if(tag.getClothingTooltipAdditions()!=null) {
////				for(String description : tag.getClothingTooltipAdditions()) {
////					if(tag==ItemTag.DILDO_SELF) {
////						extraInformationSB.append(description
////								+ ": Length: "+Units.size(this.getClothingType().getPenetrationSelfLength())
////								+ " Diameter: "+Units.size(Penis.getGenericDiameter(
////																this.getClothingType().getPenetrationSelfLength(),
////																PenetrationGirth.getGirthFromInt(this.getClothingType().getPenetrationSelfGirth()),
////																this.getClothingType().getPenetrationSelfModifiers())));
////						
////					} else if(tag==ItemTag.DILDO_OTHER) {
////						extraInformationSB.append(description
////								+ ": Length: "+Units.size(this.getClothingType().getPenetrationOtherLength())
////								+ " Diameter: "+Units.size(Penis.getGenericDiameter(
////																this.getClothingType().getPenetrationOtherLength(),
////																PenetrationGirth.getGirthFromInt(this.getClothingType().getPenetrationOtherGirth()),
////																this.getClothingType().getPenetrationOtherModifiers())));
////						
////					} else if(tag==ItemTag.ONAHOLE_SELF) {//TODO requires testing
////						OrificeElasticity elasticity = OrificeElasticity.getElasticityFromInt(this.getClothingType().getOrificeSelfElasticity());
////						OrificePlasticity plasticity = OrificePlasticity.getElasticityFromInt(this.getClothingType().getOrificeSelfPlasticity());
////						Wetness wetness = Wetness.valueOf(this.getClothingType().getOrificeSelfWetness());
////						extraInformationSB.append(description
////								+ ": Capacity: "+Units.size(this.getClothingType().getPenetrationOtherLength())
////								+ " Depth: "+Units.size(this.getClothingType().getOrificeSelfDepth()));
////						extraInformationSB.append(
////								"Elasticity: <span style='color:"+elasticity.getColour().toWebHexString()+";'>"+elasticity.getDescriptor()+"</span>"
////								+ " Plasticity: <span style='color:"+plasticity.getColour().toWebHexString()+";'>"+plasticity.getDescriptor()+"</span>"
////								+ " Wetness: <span style='color:"+wetness.getColour().toWebHexString()+";'>"+wetness.getDescriptor()+"</span>"
////								);
////						
////					} else if(tag==ItemTag.ONAHOLE_OTHER) {//TODO requires testing
////						OrificeElasticity elasticity = OrificeElasticity.getElasticityFromInt(this.getClothingType().getOrificeOtherElasticity());
////						OrificePlasticity plasticity = OrificePlasticity.getElasticityFromInt(this.getClothingType().getOrificeOtherPlasticity());
////						Wetness wetness = Wetness.valueOf(this.getClothingType().getOrificeOtherWetness());
////						extraInformationSB.append(description
////								+ ": Capacity: "+Units.size(this.getClothingType().getPenetrationOtherLength())
////								+ " Depth: "+Units.size(this.getClothingType().getOrificeOtherDepth())
////								+ " Elasticity: <span style='color:"+elasticity.getColour().toWebHexString()+";'>"+elasticity.getDescriptor()+"</span>"
////								+ " Plasticity: <span style='color:"+plasticity.getColour().toWebHexString()+";'>"+plasticity.getDescriptor()+"</span>"
////								+ " Wetness: <span style='color:"+wetness.getColour().toWebHexString()+";'>"+wetness.getDescriptor()+"</span>"
////								);
////						
////					} else {
////						extraInformationSB.append(description);
////					}
////					extraInformationSB.append("<br/>");
////				}
////			}
////		}
//
//		if(extraInformationSB.length()==0) {
//			return "";
//		}
//		
//		return "<p>"
//					+UtilText.parse(equippedToCharacter==null?Main.game.getPlayer():equippedToCharacter,
//						extraInformationSB.toString().substring(0, extraInformationSB.length()-5))
//				+"</p>";
//		
//	}
	
	public String getDisplacementBlockingDescriptions(GameCharacter equippedToCharacter){
		descriptionSB = new StringBuilder("<p><b>Displacement types:</b>");
		for(BlockedParts bp : getClothingType().getBlockedPartsMap(equippedToCharacter, this.getSlotEquippedTo())){
			descriptionSB.append("<br/><b>"+Util.capitaliseSentence(bp.displacementType.getDescription())+":</b> ");
			if(bp.displacementType==DisplacementType.REMOVE_OR_EQUIP) {
				if(equippedToCharacter.isAbleToUnequip(this, false, equippedToCharacter)) {
					descriptionSB.append("<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Available</b>");
				} else {
					descriptionSB.append("<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Blocked</b> by "+equippedToCharacter.getBlockingClothing().getName()+"");
				}
			} else {
				if(equippedToCharacter.isAbleToBeDisplaced(this, bp.displacementType, false, false, equippedToCharacter)) {
					descriptionSB.append("<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Available</b>");
				} else {
					descriptionSB.append("<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Blocked</b> by "+equippedToCharacter.getBlockingClothing().getName()+"");
				}
			}
		}
		descriptionSB.append("</p>");
		
		return descriptionSB.toString();
	}

	/**
	 * null should be passed as the argument for 'slotToBeEquippedTo' in order to return non-slot-specific descriptions.
	 * 
	 * @param equippedToCharacter The character this clothing is equipped to.
	 * @param slotToBeEquippedTo The slot for which this clothing's effects effects are to be described.
	 * @param verbose true if you want a lengthy description of each effect.
	 * @return A List of Strings describing extra features of this ClothingType.
	 */
	public List<String> getExtraDescriptions(GameCharacter equippedToCharacter, InventorySlot slotToBeEquippedTo, boolean verbose) {
		List<String> descriptionsList = new ArrayList<>();
		
		boolean plural = this.getClothingType().isPlural();
		
		if(slotToBeEquippedTo==null) {
			if(this.isSealed() && enchantmentKnown) {
				if(verbose) {
					descriptionsList.add((plural?"They have":"It has")+" been enchanted so as to [style.boldSealed(seal "+(plural?"themselves":"itself")+")] onto the wearer!");
				} else {
					descriptionsList.add("[style.boldSealed(Sealed)]");
				}
			}
			if(dirty) {
				if(verbose) {
					descriptionsList.add((plural?"They have":"It has")+" been [style.boldDirty(dirtied)] by sexual fluids!");
				} else {
					descriptionsList.add("[style.boldDirty(Dirty)]");
				}
			}
		}

		if(equippedToCharacter==null) { // The clothing is not currently equipped by anyone:
			incompatibleClothing.clear();
			
			if(slotToBeEquippedTo!=null) {
				if(!getClothingType().getIncompatibleSlots(null, slotToBeEquippedTo).isEmpty()) {
					for (InventorySlot invSlot : getClothingType().getIncompatibleSlots(null, slotToBeEquippedTo)) {
						if(Main.game.getPlayer().getClothingInSlot(invSlot) != null) {
							incompatibleClothing.add(Main.game.getPlayer().getClothingInSlot(invSlot).getClothingType().getName());
						}
					}
				}
				for(AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
					for (InventorySlot invSlot : c.getClothingType().getIncompatibleSlots(null, c.getSlotEquippedTo())) {
						if(slotToBeEquippedTo == invSlot) {
							incompatibleClothing.add(c.getClothingType().getName());
						}
					}
				}
				
				List<InventorySlot> incompSlots = getClothingType().getIncompatibleSlots(null, slotToBeEquippedTo);
				if(!incompSlots.isEmpty()) {
					if(verbose) {
						descriptionsList.add((plural?"They [style.boldBad(block":"It [style.boldBad(blocks")+")] the "+Util.inventorySlotsToStringList(incompSlots)+" slot"+(incompSlots.size()==1?"s":"")+"!");
					} else {
						for(InventorySlot slot : incompSlots) {
							descriptionsList.add("[style.boldBad(Blocks " + Util.capitaliseSentence(slot.getName()) + ")]");
						}
					}
				}
			}
			if(slotToBeEquippedTo==null) {
				if(getClothingType().getFemininityMaximum()<Main.game.getPlayer().getFemininityValue() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_CROSS_DRESSER)) {
					if(verbose) {
						descriptionsList.add((plural?"They are":"It is")+" [style.boldMasculine(too masculine)] for you to wear without feeling embarrassed!");
					} else {
						descriptionsList.add("[style.boldMasculine(Too masculine)]");
					}
				}
				if(getClothingType().getFemininityMinimum()>Main.game.getPlayer().getFemininityValue() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_CROSS_DRESSER)) {
					if(verbose) {
						descriptionsList.add((plural?"They are":"It is")+" [style.boldFeminine(Too feminine)] for you to wear without feeling embarrassed!");
					} else {
						descriptionsList.add("[style.boldFeminine(Too feminine)]");
					}
				}
				if(!incompatibleClothing.isEmpty()) {
					if(verbose) {
						descriptionsList.add((plural?"They are":"It is")+" [style.boldBad(incompatible with)] your "+Util.stringsToStringList(incompatibleClothing, false)+"!");
					} else {
						descriptionsList.add("[style.boldBad(Incompatible with:)]");
						descriptionsList.addAll(incompatibleClothing);
					}
				}
			}

		} else { // Being worn:
			if(slotToBeEquippedTo==null) {
				if(getClothingType().getFemininityMaximum()<equippedToCharacter.getFemininityValue() && !equippedToCharacter.hasFetish(Fetish.FETISH_CROSS_DRESSER)) {
					if(verbose) {
						descriptionsList.add(UtilText.parse(equippedToCharacter, (plural?"They are":"It is")+" [style.boldMasculine(too masculine)] for [npc.name] to wear without feeling embarrassed!"));
					} else {
						descriptionsList.add("[style.boldMasculine(Too masculine)]");
					}
				}
				if(getClothingType().getFemininityMinimum() > equippedToCharacter.getFemininityValue() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_CROSS_DRESSER)) {
					if(verbose) {
						descriptionsList.add(UtilText.parse(equippedToCharacter, (plural?"They are":"It is")+" [style.boldFeminine(too feminine)] for [npc.name] to wear without feeling embarrassed!"));
					} else {
						descriptionsList.add("[style.boldFeminine(Too feminine)]");
					}
				}
			}
			if(slotToBeEquippedTo!=null) {
				List<InventorySlot> incompSlots = getClothingType().getIncompatibleSlots(equippedToCharacter, slotToBeEquippedTo);
				if(!incompSlots.isEmpty()) {
					if(verbose) {
						descriptionsList.add((plural?"They [style.boldBad(block":"It [style.boldBad(blocks")+")] the "+Util.inventorySlotsToStringList(incompSlots)+" slot"+(incompSlots.size()==1?"s":"")+"!");
					} else {
						for(InventorySlot slot : incompSlots) {
							descriptionsList.add("[style.boldBad(Blocks " + Util.capitaliseSentence(slot.getName()) + ")]");
						}
					}
				}
			}
			if(slotToBeEquippedTo==null) {
				if(!displacedList.isEmpty()) {
					if(verbose) {
						descriptionsList.add((plural?"They have":"It has")+" been [style.boldDisplaced("+Util.displacementTypesToStringList(displacedList)+")]!");
					} else {
						for(DisplacementType dt : displacedList) {
							descriptionsList.add("[style.boldDisplaced(" + Util.capitaliseSentence(dt.getDescriptionPast()) + ")]");
						}
					}
				}
			}
		}
		
		List<ItemTag> universalTags = new ArrayList<>();
		for(int i=0; i<this.getClothingType().getEquipSlots().size();i++) {
			if(i==0) {
				universalTags.addAll(this.getClothingType().getItemTags(this.getClothingType().getEquipSlots().get(0)));
				
			} else {
				List<ItemTag> tags = this.getClothingType().getItemTags(this.getClothingType().getEquipSlots().get(i));
				universalTags.removeIf((it) -> !tags.contains(it));
			}
		}
		
		List<ItemTag> tagsToBeDescribed;
		if(slotToBeEquippedTo==null) {
			tagsToBeDescribed = new ArrayList<>(universalTags);
			
		} else {
			tagsToBeDescribed = new ArrayList<>(this.getClothingType().getItemTags(slotToBeEquippedTo));
			tagsToBeDescribed.removeIf((it) -> universalTags.contains(it) && it!=ItemTag.DILDO_SELF);
		}
		
		for(ItemTag tag : tagsToBeDescribed) {
			if(tag.getClothingTooltipAdditions()!=null) {
				for(String description : tag.getClothingTooltipAdditions()) {
					if(tag==ItemTag.DILDO_SELF) {
						int length = this.getClothingType().getPenetrationSelfLength();
						float diameter = Penis.getGenericDiameter(
								this.getClothingType().getPenetrationSelfLength(),
								PenetrationGirth.getGirthFromInt(this.getClothingType().getPenetrationSelfGirth()),
								this.getClothingType().getPenetrationSelfModifiers());
						
						PenisLength pl = PenisLength.getPenisLengthFromInt(length);
						Capacity cap = Capacity.getCapacityFromValue(diameter);
						
						if(slotToBeEquippedTo==null) {
							descriptionsList.add(description
									+ ": Length: <span style='color:"+pl.getColour().toWebHexString()+";'>"+Units.size(length)+"</span>"
									+ " Diameter: <span style='color:"+cap.getColour().toWebHexString()+";'>"+Units.size(diameter)+"</span>");
						}
						
						boolean lubed = false;
						if(slotToBeEquippedTo!=null) {
							String startString = plural?"They are":"It is";
							if(equippedToCharacter==null) {
								switch(slotToBeEquippedTo) {
									case ANUS:
										if(Main.game.isPenetrationLimitationsEnabled() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
											if(length>Main.game.getPlayer().getAssMaximumPenetrationDepthComfortable()) {
												if(verbose) {
													descriptionsList.add(startString+" [style.colourBad(too long)] to be able to fit comfortably into your ass!");
												} else {
													descriptionsList.add("[style.colourTerrible(Too long)] for comfortable insertion");
												}
											}
										}
										lubed = Main.game.getPlayer().getLust() >= Main.game.getPlayer().getAssWetness().getArousalNeededToGetAssWet();
										if(Capacity.isPenetrationDiameterTooBig(Main.game.getPlayer().getAssElasticity(), Main.game.getPlayer().getAssStretchedCapacity(), diameter, lubed)) {
											if(verbose) {
												descriptionsList.add(startString+" [style.colourBad(too thick)] for your [pc.assCapacity] asshole, and would stretch it out if inserted!");
											} else {
												descriptionsList.add("[style.colourBad(Too thick)], will cause [style.colourBad(stretching)]");
											}
										}
										break;
									case MOUTH:
										if(Main.game.isPenetrationLimitationsEnabled() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
											if(length>Main.game.getPlayer().getFaceMaximumPenetrationDepthComfortable()) {
												if(verbose) {
													descriptionsList.add(startString+" [style.colourBad(too long)] to be able to fit comfortably down your throat!");
												} else {
													descriptionsList.add("[style.colourTerrible(Too long)] for comfortable insertion");
												}
											}
										}
										lubed = true;
										if(Capacity.isPenetrationDiameterTooBig(Main.game.getPlayer().getFaceElasticity(), Main.game.getPlayer().getFaceStretchedCapacity(), diameter, lubed)) {
											if(verbose) {
												descriptionsList.add(startString+" [style.colourBad(too thick)] for your throat, and would stretch it out if inserted!");
											} else {
												descriptionsList.add("[style.colourBad(Too thick)], will cause [style.colourBad(stretching)]");
											}
										}
										break;
									case NIPPLE:
										if(Main.game.isPenetrationLimitationsEnabled() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
											if(length>Main.game.getPlayer().getNippleMaximumPenetrationDepthComfortable()) {
												if(verbose) {
													descriptionsList.add(startString+" [style.colourBad(too long)] to be able to fit comfortably into your fuckable nipples!");
												} else {
													descriptionsList.add("[style.colourTerrible(Too long)] for comfortable insertion");
												}
											}
										}
										lubed = Main.game.getPlayer().getBreastRawStoredMilkValue()>0;
										if(Capacity.isPenetrationDiameterTooBig(Main.game.getPlayer().getNippleElasticity(), Main.game.getPlayer().getNippleStretchedCapacity(), diameter, lubed)) {
											if(verbose) {
												descriptionsList.add(startString+" [style.colourBad(too thick)] for your [pc.breastCapacity] nipples, and would stretch them out if inserted!");
											} else {
												descriptionsList.add("[style.colourBad(Too thick)], will cause [style.colourBad(stretching)]");
											}
										}
										break;
									case VAGINA:
										if(verbose) {
											descriptionsList.add((plural?"They":"It")+" will [style.colourTerrible(tear the hymen)] of any pussy "+(plural?"they are":"it is")+" inserted into!");
										} else {
											descriptionsList.add("[style.colourTerrible(Tears hymen)] of virgin pussies");
										}
										if(Main.game.isPenetrationLimitationsEnabled() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
											if(Main.game.getPlayer().hasVagina() && length>Main.game.getPlayer().getVaginaMaximumPenetrationDepthComfortable()) {
												if(verbose) {
													descriptionsList.add(startString+" [style.colourBad(too long)] to be able to fit comfortably into your pussy!");
												} else {
													descriptionsList.add("[style.colourTerrible(Too long)] for comfortable insertion");
												}
											}
										}
										lubed = Main.game.getPlayer().getLust() >= Main.game.getPlayer().getVaginaWetness().getArousalNeededToGetAssWet();
										if(Capacity.isPenetrationDiameterTooBig(Main.game.getPlayer().getVaginaElasticity(), Main.game.getPlayer().getVaginaStretchedCapacity(), diameter, lubed)) {
											if(verbose) {
												descriptionsList.add(startString+" [style.colourBad(too thick)] for your [pc.pussyCapacity] pussy, and would stretch it out if inserted!");
											} else {
												descriptionsList.add("[style.colourBad(Too thick)], will cause [style.colourBad(stretching)]");
											}
										}
										break;
									default:
										break;
								}
								
							} else {
								String discomfort = "[style.colourBad(discomfort)]";
								if(equippedToCharacter.hasFetish(Fetish.FETISH_MASOCHIST)) {
									discomfort = "[style.colourMinorGood(masochistic pleasure)]";
								}
								switch(slotToBeEquippedTo) {
									case ANUS:
										if(Main.game.isPenetrationLimitationsEnabled() && !equippedToCharacter.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
											if(length>equippedToCharacter.getAssMaximumPenetrationDepthComfortable()) {
												if(verbose) {
													descriptionsList.add(UtilText.parse(equippedToCharacter,
															startString+" [style.colourBad(too deep)] in [npc.namePos] ass, and "+(plural?"are":"is")+" causing [npc.herHim] "+discomfort+"!"));
												} else {
													if(equippedToCharacter.hasFetish(Fetish.FETISH_MASOCHIST)) {
														descriptionsList.add("[style.colourTerrible(Too deep)], giving [style.colourMinorGood(masochistic pleasure)]");
													} else {
														descriptionsList.add("[style.colourTerrible(Too deep)], causing [style.colourBad(discomfort)]");
													}
												}
											}
										}
										lubed = equippedToCharacter.getLust() >= equippedToCharacter.getAssWetness().getArousalNeededToGetAssWet();
										if(Capacity.isPenetrationDiameterTooBig(equippedToCharacter.getAssElasticity(), equippedToCharacter.getAssStretchedCapacity(), diameter, lubed)) {
											if(verbose) {
												descriptionsList.add(UtilText.parse(equippedToCharacter,
														startString+" [style.colourBad(too thick)] for [npc.namePos] [npc.assCapacity] asshole, and "+(plural?"are":"is")+" [style.colourBad(stretching)] it out!"));
											} else {
												descriptionsList.add("[style.colourBad(Too thick)], causing [style.colourBad(asshole to stretch)]");
											}
										}
										break;
									case MOUTH:
										if(Main.game.isPenetrationLimitationsEnabled() && !equippedToCharacter.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
											if(length>equippedToCharacter.getFaceMaximumPenetrationDepthComfortable()) {
												if(verbose) {
													descriptionsList.add(UtilText.parse(equippedToCharacter,
															startString+" [style.colourBad(too deep)] down [npc.namePos] throat, and "+(plural?"are":"is")+" causing [npc.herHim] "+discomfort+"!"));
												} else {
													if(equippedToCharacter.hasFetish(Fetish.FETISH_MASOCHIST)) {
														descriptionsList.add("[style.colourTerrible(Too deep)], giving [style.colourMinorGood(masochistic pleasure)]");
													} else {
														descriptionsList.add("[style.colourTerrible(Too deep)], causing [style.colourBad(discomfort)]");
													}
												}
											}
										}
										lubed = true;
										if(Capacity.isPenetrationDiameterTooBig(equippedToCharacter.getFaceElasticity(), equippedToCharacter.getFaceStretchedCapacity(), diameter, lubed)) {
											if(verbose) {
												descriptionsList.add(UtilText.parse(equippedToCharacter,
														startString+" [style.colourBad(too thick)] for [npc.namePos] throat, and "+(plural?"are":"is")+" [style.colourBad(stretching)] it out!"));
											} else {
												descriptionsList.add("[style.colourBad(Too thick)], causing [style.colourBad(throat to stretch)]");
											}
										}
										break;
									case NIPPLE:
										if(Main.game.isPenetrationLimitationsEnabled() && !equippedToCharacter.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
											if(length>equippedToCharacter.getNippleMaximumPenetrationDepthComfortable()) {
												if(verbose) {
													descriptionsList.add(UtilText.parse(equippedToCharacter,
															startString+" [style.colourBad(too deep)] in [npc.namePos] fuckable nipples, and "+(plural?"are":"is")+" causing [npc.herHim] "+discomfort+"!"));
												} else {
													if(equippedToCharacter.hasFetish(Fetish.FETISH_MASOCHIST)) {
														descriptionsList.add("[style.colourTerrible(Too deep)], giving [style.colourMinorGood(masochistic pleasure)]");
													} else {
														descriptionsList.add("[style.colourTerrible(Too deep)], causing [style.colourBad(discomfort)]");
													}
												}
											}
										}
										lubed = equippedToCharacter.getBreastRawStoredMilkValue()>0;
										if(Capacity.isPenetrationDiameterTooBig(equippedToCharacter.getNippleElasticity(), equippedToCharacter.getNippleStretchedCapacity(), diameter, lubed)) {
											if(verbose) {
												descriptionsList.add(UtilText.parse(equippedToCharacter,
														startString+" [style.colourBad(too thick)] for [npc.namePos] [npc.breastCapacity] nipples, and "+(plural?"are":"is")+" [style.colourBad(stretching)] them out!"));
											} else {
												descriptionsList.add("[style.colourBad(Too thick)], causing [style.colourBad(nipples to stretch)]");
											}
										}
										break;
									case VAGINA:
										if(Main.game.isPenetrationLimitationsEnabled() && !equippedToCharacter.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
											if(equippedToCharacter.hasVagina() && length>equippedToCharacter.getVaginaMaximumPenetrationDepthComfortable()) {
												if(verbose) {
													descriptionsList.add(UtilText.parse(equippedToCharacter,
															startString+" [style.colourBad(too deep)] in [npc.namePos] pussy, and "+(plural?"are":"is")+" causing [npc.herHim] "+discomfort+"!"));
												} else {
													if(equippedToCharacter.hasFetish(Fetish.FETISH_MASOCHIST)) {
														descriptionsList.add("[style.colourTerrible(Too deep)], giving [style.colourMinorGood(masochistic pleasure)]");
													} else {
														descriptionsList.add("[style.colourTerrible(Too deep)], causing [style.colourBad(discomfort)]");
													}
												}
											}
										}
										lubed = equippedToCharacter.getLust() >= equippedToCharacter.getVaginaWetness().getArousalNeededToGetAssWet();
										if(Capacity.isPenetrationDiameterTooBig(equippedToCharacter.getVaginaElasticity(), equippedToCharacter.getVaginaStretchedCapacity(), diameter, lubed)) {
											if(verbose) {
												descriptionsList.add(UtilText.parse(equippedToCharacter,
														startString+" [style.colourBad(too thick)] for [npc.namePos] [npc.pussyCapacity] pussy, and "+(plural?"are":"is")+" [style.colourBad(stretching)] it out!"));
											} else {
												descriptionsList.add("[style.colourBad(Too thick)], causing [style.colourBad(pussy to stretch)]");
											}
										}
										break;
									default:
										break;
								}
							}
						}
						
					} else if(tag==ItemTag.DILDO_OTHER) {
						int length = this.getClothingType().getPenetrationOtherLength();
						float diameter = Penis.getGenericDiameter(
								this.getClothingType().getPenetrationOtherLength(),
								PenetrationGirth.getGirthFromInt(this.getClothingType().getPenetrationOtherGirth()),
								this.getClothingType().getPenetrationOtherModifiers());
								
						PenisLength pl = PenisLength.getPenisLengthFromInt(length);
						Capacity cap = Capacity.getCapacityFromValue(diameter);
						
						descriptionsList.add(description
								+ ": Length: <span style='color:"+pl.getColour().toWebHexString()+";'>"+Units.size(length)+"</span>"
								+ " Diameter: <span style='color:"+cap.getColour().toWebHexString()+";'>"+Units.size(diameter)+"</span>");
						
					} else if(tag==ItemTag.ONAHOLE_SELF) {//TODO requires testing
						OrificeElasticity elasticity = OrificeElasticity.getElasticityFromInt(this.getClothingType().getOrificeSelfElasticity());
						OrificePlasticity plasticity = OrificePlasticity.getElasticityFromInt(this.getClothingType().getOrificeSelfPlasticity());
						Wetness wetness = Wetness.valueOf(this.getClothingType().getOrificeSelfWetness());
						descriptionsList.add(description
								+ ": Capacity: "+Units.size(this.getClothingType().getPenetrationOtherLength())
								+ " Depth: "+Units.size(this.getClothingType().getOrificeSelfDepth()));
						descriptionsList.add(
								"Elasticity: <span style='color:"+elasticity.getColour().toWebHexString()+";'>"+elasticity.getDescriptor()+"</span>"
								+ " Plasticity: <span style='color:"+plasticity.getColour().toWebHexString()+";'>"+plasticity.getDescriptor()+"</span>"
								+ " Wetness: <span style='color:"+wetness.getColour().toWebHexString()+";'>"+wetness.getDescriptor()+"</span>"
								);
						
					} else if(tag==ItemTag.ONAHOLE_OTHER) {//TODO requires testing
						OrificeElasticity elasticity = OrificeElasticity.getElasticityFromInt(this.getClothingType().getOrificeOtherElasticity());
						OrificePlasticity plasticity = OrificePlasticity.getElasticityFromInt(this.getClothingType().getOrificeOtherPlasticity());
						Wetness wetness = Wetness.valueOf(this.getClothingType().getOrificeOtherWetness());
						descriptionsList.add(description
								+ ": Capacity: "+Units.size(this.getClothingType().getPenetrationOtherLength())
								+ " Depth: "+Units.size(this.getClothingType().getOrificeOtherDepth()));
						descriptionsList.add(
								"Elasticity: <span style='color:"+elasticity.getColour().toWebHexString()+";'>"+elasticity.getDescriptor()+"</span>"
								+ " Plasticity: <span style='color:"+plasticity.getColour().toWebHexString()+";'>"+plasticity.getDescriptor()+"</span>"
								+ " Wetness: <span style='color:"+wetness.getColour().toWebHexString()+";'>"+wetness.getDescriptor()+"</span>"
								);
						
					} else {
						descriptionsList.add(description);
					}
				}
			}
		}
		

		return descriptionsList;
	}

	/**
	 * @return A list of blocked body parts. e.g. "Penis, Anus and Vagina" or "Nipples"
	 */
	public String getClothingBlockingDescription(DisplacementType dt, GameCharacter owner, InventorySlot slotToBeEquippedTo, String preFix, String postFix) {
		Set<CoverableArea> coveredAreas = new HashSet<>();// EnumSet.noneOf(CoverableArea.class);

		if(dt == null) {
			for (BlockedParts bp : this.getClothingType().getBlockedPartsMap(owner, slotToBeEquippedTo)) {
				if(!this.getDisplacedList().contains(bp.displacementType)) {
					coveredAreas.addAll(bp.blockedBodyParts);
				}
			}
		} else {
			for (BlockedParts bp : this.getClothingType().getBlockedPartsMap(owner, slotToBeEquippedTo)) {
				if(bp.displacementType == dt) {
					coveredAreas.addAll(bp.blockedBodyParts);
				}
			}
		}
		
		if(owner!=null) {
			if(owner.getVaginaType() == VaginaType.NONE)
				coveredAreas.remove(CoverableArea.VAGINA);
			if(owner.getPenisType() == PenisType.NONE)
				coveredAreas.remove(CoverableArea.PENIS);
		}
		
		if(!coveredAreas.isEmpty())
			return preFix + Util.setToStringListCoverableArea(coveredAreas) + postFix;
		else
			return "";
	}

	public void removeBadEnchantments() {
		this.getEffects().removeIf(e -> (e.getPrimaryModifier() == TFModifier.CLOTHING_ATTRIBUTE || e.getPrimaryModifier() == TFModifier.CLOTHING_MAJOR_ATTRIBUTE) && e.getPotency().isNegative());
	}

	public boolean isSealed() {
		if(this.isUnlocked()) {
			return false;
		}
		for(ItemEffect effect : this.getEffects()) {
			if(effect!=null && effect.getSecondaryModifier()==TFModifier.CLOTHING_SEALING) {
				return true;
			} else if(effect==null) {
				System.err.println("AbstractClothing.isSealed() for "+this.getName()+" is encountering a null ItemEffect!");
			}
		}
		return false;
	}

	/**
	 * <b>Warning:</b> If this clothing is not equipped, and is held in a character's inventory, this method will cause the Map of AbstractClothing in the character's inventory to break.
	 */
	public void setSealed(boolean sealed) {
		if(sealed) {
			this.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MINOR_BOOST, 0));
		} else {
			setUnlocked(true);
//			this.getEffects().removeIf(e -> e.getSecondaryModifier() == TFModifier.CLOTHING_SEALING);
		}
	}

	public void setUnlocked(boolean unlocked) {
		this.unlocked = unlocked;
	}
	
	public boolean isUnlocked() {
		return unlocked;
	}

	public int getJinxRemovalCost() {
		for(ItemEffect effect : this.getEffects()) {
			if(effect.getSecondaryModifier()==TFModifier.CLOTHING_SEALING) {
				switch(effect.getPotency()) {
					case BOOST:
						break;
					case DRAIN:
						return ItemEffect.SEALED_COST_DRAIN;
					case MAJOR_BOOST:
						break;
					case MAJOR_DRAIN:
						return ItemEffect.SEALED_COST_MAJOR_DRAIN;
					case MINOR_BOOST:
						return ItemEffect.SEALED_COST_MINOR_BOOST;
					case MINOR_DRAIN:
						return ItemEffect.SEALED_COST_MINOR_DRAIN;
				}
			}
		}
		return ItemEffect.SEALED_COST_MINOR_BOOST;
	}

	public TFPotency getVibratorIntensity() {
		for(ItemEffect effect : this.getEffects()) {
			if(effect!=null && effect.getSecondaryModifier()==TFModifier.CLOTHING_VIBRATION) {
				return effect.getPotency();
				
			} else if(effect==null) {
				System.err.println("AbstractClothing.getVibratorIntensity() for "+this.getName()+" is encountering a null ItemEffect!");
			}
		}
		return null;
	}
	
	public boolean isVibrator() {
		return getVibratorIntensity()!=null;
	}
	
	public boolean isDirty() {
		return dirty;
	}

	public void setDirty(GameCharacter owner, boolean dirty) {
		if(owner!=null) {
			if(owner.getClothingCurrentlyEquipped().contains(this)) {
//				System.out.println("1");
				AbstractClothing c = new AbstractClothing(this) {};
				owner.forceUnequipClothingIntoVoid(owner, this);
				c.dirty = dirty;
				owner.equipClothingOverride(c, c.getSlotEquippedTo(), false, false);
				
			} else if(owner.removeClothing(this)) {
//				System.out.println("2");
				AbstractClothing c = new AbstractClothing(this) {};
				c.dirty = dirty;
				owner.addClothing(c, false);
//				enchantmentRemovedClothing = c;
				
			} else {
//				System.out.println("3");
				this.dirty = dirty;
			}
		} else {
//			System.out.println("4");
			this.dirty = dirty;
		}
		
//		if(Main.game.getPlayer()!=null) {
//			if(Main.game.getPlayer().getClothingCurrentlyEquipped().contains(this)) {
//				Main.game.getPlayer().updateInventoryListeners();
//			}
//		}
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
	public static AbstractClothing enchantmentRemovedClothing;
	public String setEnchantmentKnown(GameCharacter owner, boolean enchantmentKnown) {
		pointlessSB.setLength(0);
		
		if(owner!=null) {
			if(owner.removeClothing(this)) {
				AbstractClothing c = new AbstractClothing(this) {};
				c.enchantmentKnown = enchantmentKnown;
				owner.addClothing(c, false);
				enchantmentRemovedClothing = c;
			} else {
				this.enchantmentKnown = enchantmentKnown;
			}
		} else {
			this.enchantmentKnown = enchantmentKnown;
		}
		
		if(enchantmentKnown && !attributeModifiers.isEmpty()){
			if(isBadEnchantment()) {
				pointlessSB.append(
						"<p style='text-align:center;'>"
								+ "<b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Jinx revealed:</b><br/>"
										+ "<b>"+Util.capitaliseSentence(getDisplayName(true))+"</b>");
				
			} else {
				pointlessSB.append(
						"<p style='text-align:center;'>"
								+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Enchantment revealed:</b><br/>"
										+ "<b>"+Util.capitaliseSentence(getDisplayName(true))+"</b>");
			}
			
			for(ItemEffect ie : this.getEffects()) {
				for(String s : ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())) {
					pointlessSB.append("<br/>"+s);
				}
			}
			pointlessSB.append("</p>");
			
		} else {
			return "";
		}
		
		return pointlessSB.toString();
	}

	public Attribute getCoreEnchantment() {
		Attribute att = null;
		int max = 0;
		for(Entry<Attribute, Integer> entry : getAttributeModifiers().entrySet()) {
			att = entry.getKey();
			if(Math.abs(entry.getValue()) > max) {
				att = entry.getKey();
				max = Math.abs(entry.getValue());
			}
		}
		if(att==null) {
			return Attribute.MAJOR_PHYSIQUE;
		}
		return att;
	}
	
	public String getEnchantmentPostfix(boolean coloured, String tag) {
		if(!this.getEffects().isEmpty() && !this.isCondom(this.getClothingType().getEquipSlots().get(0))) {
			for(ItemEffect ie : this.getEffects()) {
				if(ie.getSecondaryModifier() == TFModifier.CLOTHING_ENSLAVEMENT) {
					return "of "+(coloured?"<"+tag+" style='color:"+TFModifier.CLOTHING_ENSLAVEMENT.getColour().toWebHexString()+";'>enslavement</"+tag+">":"enslavement");
					
				} else if(ie.getSecondaryModifier() == TFModifier.CLOTHING_SERVITUDE) {
					return "of "+(coloured?"<"+tag+" style='color:"+TFModifier.CLOTHING_SERVITUDE.getColour().toWebHexString()+";'>servitude</"+tag+">":"servitude");
					
				} else if(ie.getPrimaryModifier() == TFModifier.TF_MOD_FETISH_BEHAVIOUR || ie.getPrimaryModifier() == TFModifier.TF_MOD_FETISH_BODY_PART) {
					return "of "+(coloured?"<"+tag+" style='color:"+Colour.FETISH.toWebHexString()+";'>"+ie.getSecondaryModifier().getDescriptor()+"</"+tag+">":ie.getSecondaryModifier().getDescriptor());
					
				} else if(ie.getPrimaryModifier() == TFModifier.CLOTHING_ATTRIBUTE || ie.getPrimaryModifier() == TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
					String name = (this.isBadEnchantment()?this.getCoreEnchantment().getNegativeEnchantment():this.getCoreEnchantment().getPositiveEnchantment());
					return "of "+(coloured?"<"+tag+" style='color:"+this.getCoreEnchantment().getColour().toWebHexString()+";'>"+name+"</"+tag+">":name);
					
				} else if(ie.getSecondaryModifier() == TFModifier.CLOTHING_SEALING) {
					return "of "+(coloured?"<"+tag+" style='color:"+Colour.SEALED.toWebHexString()+";'>sealing</"+tag+">":"sealing");
					
				} else if(ie.getSecondaryModifier() != TFModifier.CLOTHING_VIBRATION) {
					return "of "+(coloured?"<"+tag+" style='color:"+Colour.TRANSFORMATION_GENERIC.toWebHexString()+";'>transformation</"+tag+">":"transformation");
				}
			}
		}
		return "";
	}

	public boolean isBadEnchantment() {
		return this.getEffects().stream().mapToInt(e -> (
				((e.getPrimaryModifier() == TFModifier.CLOTHING_ATTRIBUTE || e.getPrimaryModifier() == TFModifier.CLOTHING_MAJOR_ATTRIBUTE))
					?e.getPotency().getClothingBonusValue()*(e.getSecondaryModifier()==TFModifier.CORRUPTION?-1:1)
					:0)
				+ (e.getSecondaryModifier()==TFModifier.CLOTHING_SEALING?-10:0)
				+ (e.getSecondaryModifier()==TFModifier.CLOTHING_SERVITUDE?-10:0)
			).sum()<0;
	}

	public boolean isEnslavementClothing() {
		return this.getEffects().stream().anyMatch(e -> e.getSecondaryModifier() == TFModifier.CLOTHING_ENSLAVEMENT);
	}

	public boolean isSelfTransformationInhibiting() {
		return this.getEffects().stream().anyMatch(e -> e.getSecondaryModifier() == TFModifier.CLOTHING_SERVITUDE);
	}

	public boolean isJinxRemovalInhibiting() {
		return this.getEffects().stream().anyMatch(e -> e.getSecondaryModifier() == TFModifier.CLOTHING_SERVITUDE);
	}
	
	@Override
	public List<ItemEffect> getEffects() {
		return effects;
	}
	
	/**
	 * <b>Do not call when equipped to someone!</b> (It will not update the wearer's attributes.)
	 */
	public void addEffect(ItemEffect effect) {
		effects.add(effect);
	}

	/**
	 * <b>Do not call when equipped to someone!</b> (It will not update the wearer's attributes.)
	 */
	public void removeEffect(ItemEffect effect) {
		effects.remove(effect);
	}

	/**
	 * <b>Do not call when equipped to someone!</b> (It will not update the wearer's attributes.)
	 */
	public void clearEffects() {
		effects.clear();
	}
	
	@Override
	public Map<Attribute, Integer> getAttributeModifiers() {
		attributeModifiers.clear();
		
		for(ItemEffect ie : getEffects()) {
			if(ie.getPrimaryModifier() == TFModifier.CLOTHING_ATTRIBUTE || ie.getPrimaryModifier() == TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
				attributeModifiers.merge(ie.getSecondaryModifier().getAssociatedAttribute(), ie.getPotency().getClothingBonusValue(), Integer::sum);
			}
		}
		
		return attributeModifiers;
	}
	
	/**
	 * @return An integer value of the 'enchantment capacity cost' for this particular piece of clothing. Does not count negative attribute values, and values of Corruption are reversed (so reducing corruption costs enchantment stability).
	 */
	public int getEnchantmentCapacityCost() {
		Map<Attribute, Integer> noCorruption = new HashMap<>();
		attributeModifiers.entrySet().stream().filter(ent -> ent.getKey()!=Attribute.FERTILITY && ent.getKey()!=Attribute.VIRILITY).forEach(ent -> noCorruption.put(ent.getKey(), ent.getValue()*(ent.getKey()==Attribute.MAJOR_CORRUPTION?-1:1)));
		return noCorruption.values().stream().reduce(0, (a, b) -> a + Math.max(0, b));
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
	
	public boolean isCondom(InventorySlot slotEquippedTo) {
		return this.getClothingType().getItemTags(slotEquippedTo).contains(ItemTag.CONDOM);
	}
	
	public boolean isCondom() {
		if(this.getSlotEquippedTo()==null) {
			return this.getClothingType().getItemTags(this.getClothingType().getEquipSlots().get(0)).contains(ItemTag.CONDOM);
		}
		return this.getClothingType().getItemTags(slotEquippedTo).contains(ItemTag.CONDOM);
	}
	
	public ItemEffect getCondomEffect() {
		for(ItemEffect ie : this.getEffects()) {
			if(ie.getPrimaryModifier()==TFModifier.CLOTHING_CONDOM) {
				return ie;
			}
		}
		return null;
	}
	
	@Override
	public Set<ItemTag> getItemTags() {
		if(this.getSlotEquippedTo()==null) {
			return new HashSet<>(this.getClothingType().getDefaultItemTags());
		}
		return new HashSet<>(this.getClothingType().getItemTags(this.getSlotEquippedTo()));
	}
}
