package com.lilithsthrone.game.inventory.clothing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Penis;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.types.FootType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.ColourReplacement;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemGeneration;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Pattern;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.4.1
 * @author Innoxia
 */
public abstract class AbstractClothing extends AbstractCoreItem implements XMLSaving {

	private final AbstractClothingType clothingType;
	
	private InventorySlot slotEquippedTo;
	
	protected List<ItemEffect> effects;
	
	private String pattern; // name of the pattern. 
	private List<Colour> patternColours;

	private Map<String, String> stickers; // Mapping StickerCategory id to Sticker id
	
	private boolean dirty;
	private boolean enchantmentKnown;
	private boolean unlocked;
	
	private List<DisplacementType> displacedList;
	
	public AbstractClothing(AbstractClothingType clothingType, List<Colour> colours, boolean allowRandomEnchantment) {
		super(clothingType.getName(),
				clothingType.getNamePlural(),
				clothingType.getPathName(),
				colours.isEmpty()?ColourReplacement.DEFAULT_COLOUR_VALUE:colours.get(0),
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

		this.colours = new ArrayList<>(colours);
		if(colours.size()<clothingType.getColourReplacements().size()) {
			for(int i=colours.size(); i<clothingType.getColourReplacements().size(); i++) {
				this.setColour(i, clothingType.getColourReplacements().get(i).getFirstOfDefaultColours());
			}
		}
		
		handlePatternCreation();
		
		handleStickerCreation();

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
		this(clothingType, Util.newArrayListOfValues(colour, secondaryColour, tertiaryColour), effects);
	}
	
	public AbstractClothing(AbstractClothingType clothingType, List<Colour> colours, List<ItemEffect> effects) {
		super(clothingType.getName(),
				clothingType.getNamePlural(),
				clothingType.getPathName(),
				colours.isEmpty()?ColourReplacement.DEFAULT_COLOUR_VALUE:colours.get(0),
				clothingType.getRarity(),
				null);
		
		this.slotEquippedTo = null;
		
		this.clothingType = clothingType;

		dirty = false;
		enchantmentKnown = true;
		unlocked = false;

		this.colours = new ArrayList<>(colours);
		if(colours.size()<clothingType.getColourReplacements().size()) {
			for(int i=colours.size(); i<clothingType.getColourReplacements().size(); i++) {
				this.setColour(i, clothingType.getColourReplacements().get(i).getFirstOfDefaultColours());
			}
		}
		
		handlePatternCreation();
		
		handleStickerCreation();
		
		displacedList = new ArrayList<>();
		if(effects!=null) {
			this.effects = new ArrayList<>(effects);
			enchantmentKnown = false;
			
		} else {
			this.effects = new ArrayList<>();
		}
	}

	public AbstractClothing(AbstractClothing clothing) {
		this(clothing.getClothingType(), clothing.getColours(), clothing.getEffects());
		
		this.setEnchantmentKnown(null, clothing.isEnchantmentKnown());
		
		this.setPattern(clothing.getPattern());
		this.setPatternColours(clothing.getPatternColours());
		
		this.setStickers(clothing.getStickers());
		
		this.displacedList = new ArrayList<>(clothing.getDisplacedList());
		
		this.dirty = clothing.isDirty();

		this.slotEquippedTo = clothing.getSlotEquippedTo();
		this.unlocked = clothing.isUnlocked();
		
		if(!clothing.name.isEmpty()) {
			this.setName(clothing.name);
		}
	}
	
	
	private void handlePatternCreation() {
		patternColours = new ArrayList<>();
		
		if(Math.random()<clothingType.getPatternChance()) {
			pattern = Util.randomItemFrom(clothingType.getDefaultPatterns()).getName();
			
		} else {
			pattern = "none";
		}
		
		for(ColourReplacement cr : clothingType.getPatternColourReplacements()) {
			patternColours.add(cr.getRandomOfDefaultColours());
		}
	}
	
	private void handleStickerCreation() {
		stickers = new HashMap<>();
		
		for(Entry<StickerCategory, List<Sticker>> entry : this.getClothingType().getStickers().entrySet()) {
			if(!stickers.containsKey(entry.getKey().getId())) {
				List<Sticker> availableStickers = new ArrayList<>();
				for(Sticker s : entry.getValue()) {
					if(s.isDefaultSticker()) {
						availableStickers.add(s);
					}
				}
				if(availableStickers.isEmpty() && !entry.getValue().isEmpty()) {
					stickers.put(entry.getKey().getId(), entry.getValue().get(0).getId());
				} else {
					stickers.put(entry.getKey().getId(), Util.randomItemFrom(availableStickers).getId());
				}
			}
		}
	}
	
	public String getId() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(ClothingType.getIdFromClothingType(this.getClothingType()));
		for(Colour colour : this.getColours()) {
			sb.append(colour.getId());
		}

		sb.append(this.getPattern());
		for(Colour colour : this.getPatternColours()) {
			sb.append(colour.getId());
		}
		
		sb.append(this.isSealed()?"s":"n");
		sb.append(this.isDirty()?"d":"n");
		sb.append(this.isEnchantmentKnown()?"e":"n");
		sb.append(this.isBadEnchantment()?"b":"n");
		sb.append(this.getSlotEquippedTo());
		
		for(ItemEffect ie : this.getEffects()) {
			sb.append(ie.getId());
		}
		
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if(super.equals(o)){
			if(o instanceof AbstractClothing){
				return ((AbstractClothing) o).getClothingType().equals(getClothingType())
						&& ((AbstractClothing) o).getColours().equals(getColours())
						&& ((AbstractClothing) o).getPattern().equals(getPattern())
						&& (this.getPattern().equals("none") || ((AbstractClothing) o).getPatternColours().equals(getPatternColours()))
						&& ((AbstractClothing) o).isSealed() == this.isSealed()
						&& ((AbstractClothing) o).isDirty() == this.isDirty()
						&& ((AbstractClothing) o).isEnchantmentKnown() == this.isEnchantmentKnown()
						&& ((AbstractClothing) o).isBadEnchantment() == this.isBadEnchantment()
						&& ((AbstractClothing) o).getEffects().equals(this.getEffects())
						&& ((AbstractClothing) o).getSlotEquippedTo() == this.getSlotEquippedTo();
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + getClothingType().hashCode();
		result = 31 * result + getColours().hashCode();
		result = 31 * result + getPattern().hashCode();
		if(!this.getPattern().equals("none")) {
			result = 31 * result + getPatternColours().hashCode();
		}
		result = 31 * result + (this.isSealed() ? 1 : 0);
		result = 31 * result + (this.isDirty() ? 1 : 0);
		result = 31 * result + (this.isEnchantmentKnown() ? 1 : 0);
		result = 31 * result + (this.isBadEnchantment() ? 1 : 0);
		result = 31 * result + this.getEffects().hashCode();
		if(this.getSlotEquippedTo()!=null) {
			result = 31 * result + this.getSlotEquippedTo().hashCode();
		}
		return result;
	}
	
	public boolean saveAsXML(Element parentElement) {
		Element clothingElement = parentElement.addElement("clothing");
		clothingElement.addAttribute("id", clothingType.getId());
		clothingElement.addAttribute("name", name);
		clothingElement.addAttribute("dirty", String.valueOf(dirty));
		clothingElement.addAttribute("enchantmentKnown", String.valueOf(enchantmentKnown));
		clothingElement.addAttribute("unlocked", String.valueOf(unlocked));
		if(slotEquippedTo!=null) {
			clothingElement.addAttribute("slotEquippedTo", slotEquippedTo.toString());
		}

		if(!colours.isEmpty()) {
			Element coloursElement = clothingElement.addElement("colours");
			for(int i=0; i<getColours().size(); i++) {
				Element colourElement = coloursElement.addElement("colour");
				colourElement.addAttribute("pos", String.valueOf(i));
				colourElement.addAttribute("id", getColour(i).getId());
			}
		}
		
		if(!pattern.equals("none")) {
			Element patternElement = clothingElement.addElement("pattern", pattern);
			for(int i=0; i<getPatternColours().size(); i++) {
				Element colourElement = patternElement.addElement("colour");
				colourElement.addAttribute("pos", String.valueOf(i));
				colourElement.addAttribute("id", getColour(i).getId());
			}
		}
		
		if(!stickers.isEmpty()) {
			Element stickersElement = clothingElement.addElement("stickers");
			for(Entry<String, String> entry : stickers.entrySet()) {
				Element stickerElement = stickersElement.addElement("sticker");
				stickerElement.addAttribute("category", String.valueOf(entry.getKey()));
				stickerElement.addAttribute("id", entry.getValue());
			}
		}
		
		if(!effects.isEmpty()) {
			Element effectsElement = clothingElement.addElement("effects");
			for(ItemEffect ie : effects) {
				ie.saveAsXML(effectsElement.addElement("item"));
			}
		}

		if(!displacedList.isEmpty()) {
			Element displacedListElement = clothingElement.addElement("displacedList");
			for(DisplacementType dt : displacedList) {
				displacedListElement.addElement("displacementType", dt.toString());
			}
		}
		
		return true;
	}
	
	public static AbstractClothing loadFromXML(Element parentElement) {
		try {
			// Create clothing object and load core
			Element clothingElement = parentElement.getMandatoryFirstOf("clothing");
			
			AbstractClothing clothing = ItemGeneration.generateClothing(ClothingType.getClothingTypeFromId(clothingElement.getAttribute("id"), clothingElement.getAttribute("slotEquippedTo")), false);
			clothing.setName(clothingElement.getAttribute("name"));
			clothing.dirty = Boolean.parseBoolean(clothingElement.getAttribute("dirty"));
			clothing.enchantmentKnown = Boolean.parseBoolean(clothingElement.getAttribute("enchantmentKnown"));
			clothing.unlocked = Boolean.parseBoolean(clothingElement.getAttribute("unlocked"));
			
			// Colours
			if(clothingElement.getOptionalFirstOf("colours").isPresent()) {
				for (Element colourElement : clothingElement.getMandatoryFirstOf("colours").getAllOf("colour")) {
					clothing.setColour(Integer.parseInt(colourElement.getAttribute("pos")),
							PresetColour.getColourFromId(colourElement.getAttribute("id")));
				}
			}
			
			// Pattern
			if(clothingElement.getOptionalFirstOf("pattern").isPresent()) {
				Element patternElement = parentElement.getMandatoryFirstOf("pattern");
				clothing.setPattern(patternElement.getValue());
				for (Element colourElement : patternElement.getAllOf("colour")) {
					clothing.setPatternColour(Integer.parseInt(colourElement.getAttribute("pos")),
							PresetColour.getColourFromId(colourElement.getAttribute("id")));
				}
			}
			
			// Stickers
			if(clothingElement.getOptionalFirstOf("stickers").isPresent()) {
				for (Element stickerElement : clothingElement.getMandatoryFirstOf("stickers").getAllOf("sticker")) {
					clothing.setSticker(stickerElement.getAttribute("category"), stickerElement.getAttribute("id"));
				}
			}
			
			// Effects
			if(clothingElement.getOptionalFirstOf("effects").isPresent()) {
				for (Element itemElement : clothingElement.getMandatoryFirstOf("effects").getAllOf("item")) {
					clothing.addEffect(ItemEffect.loadFromXML(itemElement));
				}
			}
			
			// Displaced List
			if(clothingElement.getOptionalFirstOf("displacedList").isPresent()) {
				for (Element displacementTypeElement : clothingElement.getMandatoryFirstOf("displacedList").getAllOf("displacementType")) {
					clothing.displacedList.add(DisplacementType.valueOf(displacementTypeElement.getValue()));
				}
			}
			return clothing;
		} catch (Exception ex) {
			System.out.println("Failed to load AbstractClothing");
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Returns the name of a pattern that the clothing has.
	 * @return String
	 */
	public String getPattern() {
		if(pattern == null) {
			return "none";
		}
		return pattern;
	}
	
	/**
	 * Changes pattern to specified one. Will not render that pattern if it doesn't exist or the item doesn't support it anyway.
	 * @param pattern Pattern type
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public Colour getPatternColour(int index) {
		try {
			return patternColours.get(index);
		} catch(Exception ex) {
			return null;
		}
	}
	
	public List<Colour> getPatternColours() {
		return patternColours;
	}

	public void setPatternColours(List<Colour> patternColours) {
		this.patternColours = new ArrayList<>(patternColours);
	}
	
	public void setPatternColour(int index, Colour colour) {
		patternColours.remove(index);
		patternColours.add(index, colour);
	}
	
	public void setSticker(StickerCategory stickerCategory, Sticker sticker) {
		stickers.put(stickerCategory.getId(), sticker.getId());
	}

	public void setSticker(String stickerCategoryId, String stickerId) {
		stickers.put(stickerCategoryId, stickerId);
	}

	public void removeSticker(StickerCategory stickerCategory) {
		stickers.remove(stickerCategory.getId());
	}

	public void removeSticker(String stickerCategoryId) {
		stickers.remove(stickerCategoryId);
	}
	
	public Map<String, String> getStickers() {
		return stickers;
	}

	public Map<StickerCategory, Sticker> getStickersAsObjects() {
		Map<StickerCategory, Sticker> stickersAsObjects = new HashMap<>();
		
		for(Entry<StickerCategory, List<Sticker>> typeStickers : this.getClothingType().getStickers().entrySet()) {
			if(getStickers().containsKey(typeStickers.getKey().getId())) {
				for(Sticker typeSticker : typeStickers.getValue()) {
					if(getStickers().get(typeStickers.getKey().getId()).equals(typeSticker.getId())) {
						stickersAsObjects.put(typeStickers.getKey(), typeSticker);
						break;
					}
				}
			}
		}
		
		return stickersAsObjects;
	}
	
	public void setStickers(Map<String, String> stickers) {
		this.stickers = new HashMap<>(stickers);
	}
	
	public void setStickersAsObjects(Map<StickerCategory, Sticker> stickers) {
		this.stickers = new HashMap<>();
		for(Entry<StickerCategory, Sticker> entry : stickers.entrySet()) {
			this.stickers.put(entry.getKey().getId(), entry.getValue().getId());
		}
	}

	private static StringBuilder descriptionSB = new StringBuilder();

	public String getTypeDescription() {
		StringBuilder description = new StringBuilder(this.getClothingType().getDescription());
		
		Map<StickerCategory, Sticker> stickersAsObjects = this.getStickersAsObjects();
		List<Sticker> orderedStickers = new ArrayList<>(stickersAsObjects.values());
		orderedStickers.sort(Comparator.comparingInt(Sticker::getDescriptionPriority));
		for(Sticker st : orderedStickers) {
			if(st.isDescriptionFullReplacement()) {
				description = new StringBuilder(st.getDescription());
				break;
			}
			description.append(st.getDescription());
		}
		
		return description.toString();
	}
	
	@Override
	public String getDescription() {
		descriptionSB.setLength(0);
		
		descriptionSB.append(
				"<p>"
					+ getTypeDescription()
					+ "<br/>"
					+ (enchantmentKnown
							?(getClothingType().isPlural()?"They have":"It has")+" a value of: "+UtilText.formatAsMoney(getValue())
							:(getClothingType().isPlural()?"They have":"It has")+" an <b>unknown value</b>!")
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
				for(Entry<AbstractAttribute, Integer> entry : this.getAttributeModifiers().entrySet()) {
					descriptionSB.append("<br/><b>"+entry.getKey().getFormattedValue(entry.getValue())+"</b>");
				}
				descriptionSB.append("</p>");
			}
		}

		if(getClothingType().getClothingSet() != null) {
			descriptionSB.append("<p>" + (getClothingType().isPlural() ? "They are" : "It is") + " part of the <b style='color:" + PresetColour.RARITY_EPIC.toWebHexString() + ";'>"
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
		return this.isAbleToBeEquipped(clothingOwner, slot).getKey();
	}

	public String getCannotBeEquippedText(GameCharacter clothingOwner, InventorySlot slot) {
		return UtilText.parse(clothingOwner, this.isAbleToBeEquipped(clothingOwner, slot).getValue());
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
		float modifier = 1;

		if(!enchantmentKnown) {
			modifier -= 0.5f;
		}
		
		if(this.getRarity()==Rarity.JINXED) {
			modifier -= 0.25f;
		}
		
		if(getColour(0)==PresetColour.CLOTHING_PLATINUM) {
			modifier += 0.2f;
			
		} else if(getColour(0)==PresetColour.CLOTHING_GOLD) {
			modifier += 0.15f;
			
		} else if(getColour(0)==PresetColour.CLOTHING_ROSE_GOLD) {
			modifier += 0.1f;
			
		} else if(getColour(0)==PresetColour.CLOTHING_SILVER) {
			modifier += 0.05f;
		}
		
		for(ItemEffect e : this.getEffects()) {
			if(e.getPrimaryModifier()==TFModifier.CLOTHING_ATTRIBUTE) {
				modifier += e.getPotency().getClothingBonusValue()*0.05f;
				
			} else if(e.getPrimaryModifier()==TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
				modifier += e.getPotency().getClothingBonusValue()*0.1f;
				
			} else {
				modifier += e.getPotency().getValue()*0.025f;
			}
		}
		
		if(getClothingType().getClothingSet()!=null) {
			modifier += 1;
		}
		
		modifier = Math.max(0.25f, modifier);
		
		return Math.max(1, (int)(this.getClothingType().getBaseValue() * modifier));
	}
	
	@Override
	public int getPrice(float modifier) {
		return super.getPrice(modifier);
	}
	
	public String getBaseName() {
		return name;
	}
	
	@Override
	public String getName() {
		String parsedName = this.getClothingType().getName();
		
		if(!this.getEffects().isEmpty() || !name.isEmpty()) {
			parsedName = name;
		}
		
		Map<StickerCategory, Sticker> stickersAsObjects = this.getStickersAsObjects();
		List<Sticker> orderedStickers = new ArrayList<>(stickersAsObjects.values());
		
		orderedStickers.sort(Comparator.comparingInt(Sticker::getNamePrefixPriority));
		StringBuilder prefix = new StringBuilder();
		for(Sticker st : orderedStickers) {
			if(!st.getNamePrefix().isEmpty()) {
				prefix.append(st.getNamePrefix()).append(" ");
			}
		}
		
		orderedStickers.sort(Comparator.comparingInt(Sticker::getNamePostfixPriority));
		StringBuilder postfix = new StringBuilder();
		for(Sticker st : orderedStickers) {
			if(!st.getNamePostfix().isEmpty()) {
				postfix.append(" ").append(st.getNamePostfix());
			}
		}
		
		return prefix + parsedName + postfix;
	}
	
	public String getColourName() {
		try {
			if(this.getClothingType().isColourDerivedFromPattern() && !this.getPattern().equals("none")) {
				return this.getPatternColour(0).getName();
			}
			return getColour(0).getName();
		} catch(Exception ex) {
			System.err.println("Warning: AbstractClothing.getColourName() returning null!");
			return "";
		}
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
							: UtilText.generateSingularDeterminer(
								getClothingType().isAppendColourName()
									?getColourName()
									:getName()))
						+" "
					: "")
				+ (getClothingType().isAppendColourName()
					?getColourName()+" "
					:"")
				+ getName();
	}
	
	public String getName(boolean withDeterminer, boolean withRarityColour) {
		if(!enchantmentKnown) {
			return (withDeterminer
						? (getClothingType().isPlural()
								? getClothingType().getDeterminer()
								: UtilText.generateSingularDeterminer(
									getClothingType().isAppendColourName()
										?getColourName()
										:getName()))
							+" "
						: "")
					+ (getClothingType().isAppendColourName()
							?getColourName()+" "
							:"")
					+ (withRarityColour
							? (" <span style='color: " + PresetColour.RARITY_UNKNOWN.toWebHexString() + ";'>" + getName() + "</span>")
							: " "+getName());
		} else {
			return (withDeterminer
					? (getClothingType().isPlural()
							? getClothingType().getDeterminer()
							: UtilText.generateSingularDeterminer(
								getClothingType().isAppendColourName()
									?getColourName()
									:getName()))
						+" "
					: "")
					+ (getClothingType().isAppendColourName()
							?getColourName()+" "
							:"")
					+ (withRarityColour
							? (" <span style='color: " + this.getRarity().getColour().toWebHexString() + ";'>" + getName() + "</span>")
							: " "+getName());
		}
	}

	/**
	 * @param withRarityColour If true, the name will be coloured to its rarity.
	 * @return A string in the format "Blue cap of frostbite" or "Gold circlet of anti-magic"
	 */
	@Override
	public String getDisplayName(boolean withRarityColour) {
		return getDisplayName(withRarityColour, true);
	}

	/**
	 * @param withRarityColour If true, the name will be coloured to its rarity.
	 * @param withEnchantmentPostFix If true, an automatically-generated enchantment postfix will be appended to the name's end.
	 * @return A string in the format "Blue cap of frostbite" or "Gold circlet of anti-magic"
	 */
	public String getDisplayName(boolean withRarityColour, boolean withEnchantmentPostFix) {
		if(!this.name.replaceAll("\u00A0"," ").equalsIgnoreCase(this.getClothingType().getName().replaceAll("\u00A0"," "))) { // If this item has a custom name, just display that:
//			System.out.println(this.name+ " | "+this.getClothingType().getName());
			return (withRarityColour
					? (" <span style='color: " + (!this.isEnchantmentKnown()?PresetColour.RARITY_UNKNOWN:this.getRarity().getColour()).toWebHexString() + ";'>" + getName() + "</span>")
					: getName());
		}
		
		Colour c = !this.isEnchantmentKnown()?PresetColour.RARITY_UNKNOWN:this.getRarity().getColour();
		return Util.capitaliseSentence(
				(getClothingType().isAppendColourName()
					?
							getColourName()
					:"")
				+ (!this.getPattern().equalsIgnoreCase("none")?" "+Pattern.getPattern(this.getPattern()).getNiceName():"")
				+ (withRarityColour
					? (" <span style='color: " + c.toWebHexString() + "; "+(this.isVibrator()?"text-shadow: 2px 2px "+c.getShades()[0]+";":"")+"'>" + (this.isVibrator()?"vibrating ":"")+getName() + "</span>")
					: (this.isVibrator()?UtilText.applyVibration(" vibrating "+getName(), c):getName()))
				+ ((withEnchantmentPostFix && !this.getEffects().isEmpty() && this.isEnchantmentKnown() && this.getRarity()!=Rarity.QUEST && this.getRarity()!=Rarity.LEGENDARY && this.getRarity()!=Rarity.EPIC)
						? " "+getEnchantmentPostfix(withRarityColour, "span")
						: "")
				);
	}

	@Override
	public String getSVGString() {
		InventorySlot slotEquippedTo = this.getSlotEquippedTo();
		if(slotEquippedTo==null) {
			slotEquippedTo = this.getClothingType().getEquipSlots().get(0);
		}
		return getClothingType().getSVGImage(slotEquippedTo, getColours(), pattern, getPatternColours(), getStickers());
	}
	
	public String getSVGEquippedString(GameCharacter character) {
		InventorySlot slotEquippedTo = this.getSlotEquippedTo();
		if(slotEquippedTo==null) {
			slotEquippedTo = this.getClothingType().getEquipSlots().get(0);
		}
		return getClothingType().getSVGEquippedImage(character, slotEquippedTo, getColours(), pattern, getPatternColours(), getStickers());
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

			sb.append(getClothingType().equipText(clothingOwner, clothingEquipper, this.getSlotEquippedTo(), rough, this, true));
			
			if(this.isBadEnchantment()) {
				sb.append("<p style='text-align:center;'>"
								+ "<b style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Negative Enchantment Revealed:</b><br/>"+getDisplayName(true));
				
			} else {
				sb.append("<p style='text-align:center;'>"
								+ "<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Enchantment Revealed:</b><br/>"+getDisplayName(true));
			}

			for(Entry<AbstractAttribute, Integer> att : attributeModifiers.entrySet()) {
				sb.append("<br/>"+att.getKey().getFormattedValue(att.getValue()));
			}
			
			sb.append("</p>");
			
		} else {
			sb.append(getClothingType().equipText(clothingOwner, clothingEquipper, this.getSlotEquippedTo(), rough, this, true));
		}
		
		if(this.getItemTags().contains(ItemTag.DILDO_SELF)) {
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
								"The full length of the "+formattedName+" [style.colourMinorGood(comfortably fits)] inside of [npc.namePos] [npc.nipple(true)]!"));
						
					} else {
						if(clothingOwner.hasFetish(Fetish.FETISH_MASOCHIST)) {
							sb.append(UtilText.parse(clothingOwner,
									"The "+formattedName+" is [style.colourBad(too long)] to fit comfortably inside of [npc.namePos] [npc.nipple(true)], but as [npc.sheIs] a masochist, [style.colourGood([npc.she] [npc.do]n't mind the discomfort)]!"));
						} else {
							sb.append(UtilText.parse(clothingOwner,
									"The "+formattedName+" is [style.colourBad(too long)] to fit comfortably inside of [npc.namePos] [npc.nipple(true)], and is causing [npc.herHim] [style.colourBad(discomfort)]!"));
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
									+ " diameter is [style.colourMinorBad(too wide)] for [npc.namePos] [npc.nipple(true)], and is [style.colourBad(stretching)] [npc.herHim] out!"));
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
		if(this.getItemTags().contains(ItemTag.DILDO_OTHER)) {
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

	private static final List<String> incompatibleClothing = new ArrayList<>();
	
	public String getDisplacementBlockingDescriptions(GameCharacter equippedToCharacter){
		descriptionSB = new StringBuilder("<p><b>Displacement types:</b>");
		for(BlockedParts bp : getBlockedPartsMap(equippedToCharacter, this.getSlotEquippedTo())){
			descriptionSB.append("<br/><b>"+Util.capitaliseSentence(bp.displacementType.getDescription())+":</b> ");
			if(bp.displacementType==DisplacementType.REMOVE_OR_EQUIP) {
				if(equippedToCharacter.isAbleToUnequip(this, false, equippedToCharacter)) {
					descriptionSB.append("<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Available</b>");
				} else {
					descriptionSB.append("<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Blocked</b> by "+equippedToCharacter.getBlockingClothing().getName()+"");
				}
			} else {
				if(equippedToCharacter.isAbleToBeDisplaced(this, bp.displacementType, false, false, equippedToCharacter)) {
					descriptionSB.append("<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Available</b>");
				} else {
					descriptionSB.append("<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Blocked</b> by "+equippedToCharacter.getBlockingClothing().getName()+"");
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
				if(!getIncompatibleSlots(null, slotToBeEquippedTo).isEmpty()) {
					for (InventorySlot invSlot : getIncompatibleSlots(null, slotToBeEquippedTo)) {
						if(Main.game.getPlayer().getClothingInSlot(invSlot) != null) {
							incompatibleClothing.add(Main.game.getPlayer().getClothingInSlot(invSlot).getClothingType().getName());
						}
					}
				}
				for(AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
					for (InventorySlot invSlot : c.getIncompatibleSlots(null, c.getSlotEquippedTo())) {
						if(slotToBeEquippedTo == invSlot) {
							incompatibleClothing.add(c.getClothingType().getName());
						}
					}
				}
				
				List<InventorySlot> incompSlots = getIncompatibleSlots(null, slotToBeEquippedTo);
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
				List<InventorySlot> incompSlots = getIncompatibleSlots(equippedToCharacter, slotToBeEquippedTo);
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
		
		Set<ItemTag> universalTags = new HashSet<>(this.getItemTags());
		for(int i=0; i<this.getClothingType().getEquipSlots().size();i++) {
			Set<ItemTag> tags = this.getItemTags(this.getClothingType().getEquipSlots().get(i));
			universalTags.removeIf((it) -> !tags.contains(it));
		}
		
		Set<ItemTag> tagsToBeDescribed;
		if(slotToBeEquippedTo==null) {
			tagsToBeDescribed = new HashSet<>(universalTags);
			
		} else {
			tagsToBeDescribed = new HashSet<>(this.getItemTags(slotToBeEquippedTo));
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
			for (BlockedParts bp : this.getBlockedPartsMap(owner, slotToBeEquippedTo)) {
				if(!this.getDisplacedList().contains(bp.displacementType)) {
					coveredAreas.addAll(bp.blockedBodyParts);
				}
			}
		} else {
			for (BlockedParts bp : this.getBlockedPartsMap(owner, slotToBeEquippedTo)) {
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

	public int getJinxRemovalCost(GameCharacter remover, boolean selfUnseal) {
		int cost = ItemEffect.SEALED_COST_MINOR_BOOST;
		
		for(ItemEffect effect : this.getEffects()) {
			if(effect.getSecondaryModifier()==TFModifier.CLOTHING_SEALING) {
				switch(effect.getPotency()) {
					case BOOST:
					case MAJOR_BOOST:
						break;
					case DRAIN:
						cost = ItemEffect.SEALED_COST_DRAIN;
						break;
					case MAJOR_DRAIN:
						cost = ItemEffect.SEALED_COST_MAJOR_DRAIN;
						break;
					case MINOR_BOOST:
						cost = ItemEffect.SEALED_COST_MINOR_BOOST;
						break;
					case MINOR_DRAIN:
						cost = ItemEffect.SEALED_COST_MINOR_DRAIN;
						break;
				}
			}
		}
		if(remover.hasFetish(Fetish.FETISH_BONDAGE_VICTIM) && selfUnseal) {
			cost *= 5;
		}
		return cost;
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
	
	/**
	 * If this clothing returns true for <i>isMilkingEquipment()</i>, then it will not be dirtied by this method.
	 */
	public void setDirty(GameCharacter owner, boolean dirty) {
		if(dirty && this.isMilkingEquipment()) {
			return;
		}
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

	public static AbstractClothing enchantmentRemovedClothing;
	public String setEnchantmentKnown(GameCharacter owner, boolean enchantmentKnown) {
		StringBuilder sb = new StringBuilder();
		
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
				sb.append(
						"<p style='text-align:center;'>"
								+ "<b style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Negative Enchantment Revealed:</b><br/>"
										+ "<b>"+Util.capitaliseSentence(getDisplayName(true))+"</b>");
				
			} else {
				sb.append(
						"<p style='text-align:center;'>"
								+ "<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Enchantment Revealed:</b><br/>"
										+ "<b>"+Util.capitaliseSentence(getDisplayName(true))+"</b>");
			}
			
			for(ItemEffect ie : this.getEffects()) {
				for(String s : ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())) {
					sb.append("<br/>"+s);
				}
			}
			sb.append("</p>");
			
		} else {
			return "";
		}
		
		return sb.toString();
	}

	public AbstractAttribute getCoreEnchantment() {
		AbstractAttribute att = null;
		int max = 0;
		for(Entry<AbstractAttribute, Integer> entry : getAttributeModifiers().entrySet()) {
			att = entry.getKey();
			if(Math.abs(entry.getValue()) > max) {
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
					
				} else if(ie.getSecondaryModifier() == TFModifier.CLOTHING_ORGASM_PREVENTION) {
					return "of "+(coloured?"<"+tag+" style='color:"+TFModifier.CLOTHING_ORGASM_PREVENTION.getColour().toWebHexString()+";'>orgasm denial</"+tag+">":"orgasm denial");
					
				} else if(ie.getPrimaryModifier() == TFModifier.TF_MOD_FETISH_BEHAVIOUR || ie.getPrimaryModifier() == TFModifier.TF_MOD_FETISH_BODY_PART) {
					return "of "+(coloured?"<"+tag+" style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+ie.getSecondaryModifier().getDescriptor()+"</"+tag+">":ie.getSecondaryModifier().getDescriptor());
					
				} else if(ie.getPrimaryModifier() == TFModifier.CLOTHING_ATTRIBUTE || ie.getPrimaryModifier() == TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
					String name = (this.isBadEnchantment()&&this.getCoreEnchantment()!=Attribute.MAJOR_CORRUPTION?this.getCoreEnchantment().getNegativeEnchantment():this.getCoreEnchantment().getPositiveEnchantment());
					return "of "+(coloured?"<"+tag+" style='color:"+this.getCoreEnchantment().getColour().toWebHexString()+";'>"+name+"</"+tag+">":name);
					
				} else if(ie.getSecondaryModifier() == TFModifier.CLOTHING_SEALING) {
					return "of "+(coloured?"<"+tag+" style='color:"+PresetColour.SEALED.toWebHexString()+";'>sealing</"+tag+">":"sealing");
					
				} else if(ie.getSecondaryModifier() != TFModifier.CLOTHING_VIBRATION) {
					return "of "+(coloured?"<"+tag+" style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>transformation</"+tag+">":"transformation");
				}
			}
		}
		return "";
	}

	public boolean isBadEnchantment() {
		return this.getEffects().stream().mapToInt(e ->
		(
				((e.getPrimaryModifier() == TFModifier.CLOTHING_ATTRIBUTE || e.getPrimaryModifier() == TFModifier.CLOTHING_MAJOR_ATTRIBUTE))
					?e.getPotency().getClothingBonusValue()*(e.getSecondaryModifier()==TFModifier.CORRUPTION?-1:1)
					:0)
				+
				(e.getSecondaryModifier()==TFModifier.CLOTHING_SEALING?-10:0)
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

	/**
	 * @return A Value whose key is true if this clothing can be equipped during sex. If false, the Value's value is a description of why it cannot be equipped
	 */
	public Value<Boolean, String> isAbleToBeEquippedDuringSex(InventorySlot slotEquippedTo) {
		if(getItemTags(slotEquippedTo).contains(ItemTag.ENABLE_SEX_EQUIP)) {
			if(isEnslavementClothing()) {
				return new Value<>(false, "Clothing with enslavement enchantments cannot be equipped during sex!");
			}
			return new Value<>(true, "");
		}
		return new Value<>(false, "This item of clothing cannot be equipped during sex!");
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
	public Map<AbstractAttribute, Integer> getAttributeModifiers() {
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
		Map<AbstractAttribute, Integer> noCorruption = new HashMap<>();
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
	
	public ItemEffect getCondomEffect() {
		for(ItemEffect ie : this.getEffects()) {
			if(ie.getPrimaryModifier()==TFModifier.CLOTHING_CONDOM) {
				return ie;
			}
		}
		return null;
	}
	
	public boolean isMilkingEquipment() {
		return this.getItemTags().contains(ItemTag.MILKING_EQUIPMENT);
	}

	public Set<ItemTag> getItemTags(InventorySlot slot) {
		Set<ItemTag> clothingTags;
		
		if(slot==null) {
			clothingTags = new HashSet<>(this.getClothingType().getDefaultItemTags());
		} else {
			clothingTags = new HashSet<>(this.getClothingType().getItemTags(slot));
		}

		Map<StickerCategory, Sticker> stickersAsObjects = this.getStickersAsObjects();
		for(Sticker st : stickersAsObjects.values()) {
			clothingTags.addAll(st.getTagsApplied());
			st.getTagsRemoved().forEach(clothingTags::remove);
		}
		
		return clothingTags;
	}
	
	@Override
	public Set<ItemTag> getItemTags() {
		Set<ItemTag> clothingTags;
		
		if(this.getSlotEquippedTo()==null) {
			clothingTags = new HashSet<>(this.getClothingType().getDefaultItemTags());
		} else {
			clothingTags = new HashSet<>(this.getClothingType().getItemTags(this.getSlotEquippedTo()));
		}

		Map<StickerCategory, Sticker> stickersAsObjects = this.getStickersAsObjects();
		for(Sticker st : stickersAsObjects.values()) {
			clothingTags.addAll(st.getTagsApplied());
			st.getTagsRemoved().forEach(clothingTags::remove);
		}
		
		return clothingTags;
	}
	
	
	// Clothing methods which rely upon ItemTags:
	
	public boolean isCondom(InventorySlot slotEquippedTo) {
		return this.getItemTags(slotEquippedTo).contains(ItemTag.CONDOM);
	}
	
	public boolean isCondom() {
		return this.getItemTags().contains(ItemTag.CONDOM);
	}
	
	public boolean isSexToy(InventorySlot slotEquippedTo) {
		for(ItemTag tag : this.getItemTags(slotEquippedTo)) {
			if(tag.isSexToy()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isTransparent(InventorySlot slotEquippedTo) {
		return getItemTags(slotEquippedTo).contains(ItemTag.TRANSPARENT);
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
	
	public boolean isDiscardedOnUnequip(InventorySlot slotEquippedTo) {
		return getItemTags(slotEquippedTo).contains(ItemTag.DISCARDED_WHEN_UNEQUIPPED);
	}
	
	public Value<Boolean, String> isAbleToBeEquipped(GameCharacter clothingOwner, InventorySlot slot) {
		BodyPartClothingBlock block = slot.getBodyPartClothingBlock(clothingOwner);
		Set<ItemTag> tags = this.getItemTags(slot);
		
		boolean plural = this.getClothingType().isPlural();
		
		if(!this.getClothingType().getEquipSlots().contains(slot)) {
			return new Value<>(false, UtilText.parse("[style.colourBad(The "+this.getName()+" cannot be equipped into this slot!)]"));
		}
		if(block!=null && Collections.disjoint(block.getRequiredTags(), tags)) {
			return new Value<>(false, UtilText.parse("[style.colourBad(" + UtilText.parse(clothingOwner, block.getDescription()) + ")]"));
		}
		
		if(tags.contains(ItemTag.FITS_TAUR_BODY) && clothingOwner.getLegConfiguration()!=LegConfiguration.QUADRUPEDAL) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(plural?"are":"is")+" only suitable for taur bodies, and as such, [npc.name] cannot wear "+(plural?"them":"it")+"."));
		}
		if(tags.contains(ItemTag.FITS_LONG_TAIL_BODY) && clothingOwner.getLegConfiguration()!=LegConfiguration.TAIL_LONG) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(plural?"are":"is")+" only suitable for long-tailed bodies, and as such, [npc.name] cannot wear "+(plural?"them":"it")+"."));
		}
		if(tags.contains(ItemTag.FITS_TAIL_BODY) && clothingOwner.getLegConfiguration()!=LegConfiguration.TAIL) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(plural?"are":"is")+" only suitable for tailed bodies, and as such, [npc.name] cannot wear "+(plural?"them":"it")+"."));
		}
		if(tags.contains(ItemTag.FITS_ARACHNID_BODY) && clothingOwner.getLegConfiguration()!=LegConfiguration.ARACHNID) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(plural?"are":"is")+" only suitable for arachnid bodies, and as such, [npc.name] cannot wear "+(plural?"them":"it")+"."));
		}
		if(tags.contains(ItemTag.FITS_CEPHALOPOD_BODY) && clothingOwner.getLegConfiguration()!=LegConfiguration.CEPHALOPOD) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(plural?"are":"is")+" only suitable for cephalopod bodies, and as such, [npc.name] cannot wear "+(plural?"them":"it")+"."));
		}
		if(tags.contains(ItemTag.FITS_AVIAN_BODY) && clothingOwner.getLegConfiguration()!=LegConfiguration.AVIAN) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(plural?"are":"is")+" only suitable for avian bodies, and as such, [npc.name] cannot wear "+(plural?"them":"it")+"."));
		}

		if(tags.contains(ItemTag.ONLY_FITS_FERAL_ALL_BODY) && !clothingOwner.isFeral()) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(plural?"are":"is")+" only suitable for feral bodies, and as such, [npc.name] cannot wear "+(plural?"them":"it")+"."));
		}
		if(tags.contains(ItemTag.ONLY_FITS_FERAL_QUADRUPED_BODY) && (!clothingOwner.isFeral() || clothingOwner.getLegConfiguration()!=LegConfiguration.QUADRUPEDAL)) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(plural?"are":"is")+" only suitable for feral quadrupedal bodies, and as such, [npc.name] cannot wear "+(plural?"them":"it")+"."));
		}
		if(tags.contains(ItemTag.ONLY_FITS_FERAL_ARACHNID_BODY) && (!clothingOwner.isFeral() || clothingOwner.getLegConfiguration()!=LegConfiguration.ARACHNID)) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(plural?"are":"is")+" only suitable for feral arachnid bodies, and as such, [npc.name] cannot wear "+(plural?"them":"it")+"."));
		}
		if(tags.contains(ItemTag.ONLY_FITS_FERAL_AVIAN_BODY) && (!clothingOwner.isFeral() || clothingOwner.getLegConfiguration()!=LegConfiguration.AVIAN)) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(plural?"are":"is")+" only suitable for feral avian bodies, and as such, [npc.name] cannot wear "+(plural?"them":"it")+"."));
		}
		if(tags.contains(ItemTag.ONLY_FITS_FERAL_CEPHALOPOD_BODY) && (!clothingOwner.isFeral() || clothingOwner.getLegConfiguration()!=LegConfiguration.CEPHALOPOD)) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(plural?"are":"is")+" only suitable for feral cephalopod bodies, and as such, [npc.name] cannot wear "+(plural?"them":"it")+"."));
		}
		if(tags.contains(ItemTag.ONLY_FITS_FERAL_TAIL_BODY) && (!clothingOwner.isFeral() || clothingOwner.getLegConfiguration()!=LegConfiguration.TAIL)) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(plural?"are":"is")+" only suitable for feral tailed bodies, and as such, [npc.name] cannot wear "+(plural?"them":"it")+"."));
		}
		if(tags.contains(ItemTag.ONLY_FITS_FERAL_LONG_TAIL_BODY) && (!clothingOwner.isFeral() || clothingOwner.getLegConfiguration()!=LegConfiguration.TAIL_LONG)) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(plural?"are":"is")+" only suitable for feral long-tailed bodies, and as such, [npc.name] cannot wear "+(plural?"them":"it")+"."));
		}
		
		if(tags.contains(ItemTag.FITS_ARM_WINGS_EXCLUSIVE) && !clothingOwner.getArmTypeTags().contains(BodyPartTag.ARM_WINGS)) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(plural?"are":"is")+" only suitable for arm-wings, and as such, [npc.name] cannot wear "+(plural?"them":"it")+"."));
		}
		if(tags.contains(ItemTag.FITS_FEATHERED_ARM_WINGS_EXCLUSIVE) && !clothingOwner.getArmTypeTags().contains(BodyPartTag.ARM_WINGS_FEATHERED)) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(plural?"are":"is")+" only suitable for feathered arm-wings, and as such, [npc.name] cannot wear "+(plural?"them":"it")+"."));
		}
		if(tags.contains(ItemTag.FITS_LEATHERY_ARM_WINGS_EXCLUSIVE) && !clothingOwner.getArmTypeTags().contains(BodyPartTag.ARM_WINGS_LEATHERY)) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(plural?"are":"is")+" only suitable for leathery arm-wings, and as such, [npc.name] cannot wear "+(plural?"them":"it")+"."));
		}
		if(tags.contains(ItemTag.FITS_HOOFS_EXCLUSIVE) && clothingOwner.getLegType().getFootType()!=FootType.HOOFS) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(plural?"are":"is")+" only suitable for hoofs, and as such, [npc.name] cannot wear "+(plural?"them":"it")+"."));
		}
		if(tags.contains(ItemTag.FITS_TALONS_EXCLUSIVE) && clothingOwner.getLegType().getFootType()!=FootType.TALONS) {
			return new Value<>(false, UtilText.parse(clothingOwner,"The "+this.getName()+" "+(plural?"are":"is")+" only suitable for talons, and as such, [npc.name] cannot wear "+(plural?"them":"it")+"."));
		}
		if(clothingOwner.hasPenisIgnoreDildo() && tags.contains(ItemTag.REQUIRES_NO_PENIS)) {
			return new Value<>(false, UtilText.parse(clothingOwner, "[npc.NameHasFull] a penis, which is blocking [npc.herHim] from wearing the "+this.getName()+"!"));
		}
		if(clothingOwner.hasDildo() && tags.contains(ItemTag.REQUIRES_NO_PENIS)) {
			return new Value<>(false, UtilText.parse(clothingOwner, "[npc.NameHasFull] equipped a dildo, which is blocking [npc.herHim] from wearing the "+this.getName()+"!"));
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

	public List<BlockedParts> getBlockedPartsMap(GameCharacter character, InventorySlot slotEquippedTo) {
		Set<ItemTag> tags = this.getItemTags(slotEquippedTo);
		
		if(character!=null) {
			boolean replaceCrotchBoobAccess = false;
			boolean replaceGroinAccess = false;
			switch(character.getLegConfiguration()) {
				case BIPEDAL:
				case TAIL:
				case TAIL_LONG:
				case CEPHALOPOD:
				case WINGED_BIPED:
					// These are all in such a position that normal clothing conceals as normal
					break;
				case ARACHNID:
					if(!tags.contains(ItemTag.FITS_ARACHNID_BODY)) { // Arachnid-specific clothing is configured to be correct.
						// Arachnid crotch boobs are on the front, so that conceals as normal. Genitalia are not concealed.
						replaceGroinAccess = true;
					}
					break;
				case AVIAN:
					if(!tags.contains(ItemTag.FITS_AVIAN_BODY)) { // Avian-specific clothing is configured to be correct.
						// Avian crotch boobs are on the front, so that conceals as normal. Genitalia are not concealed.
						replaceGroinAccess = true;
					}
					break;
				case QUADRUPEDAL:
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
				for(BlockedParts blockedparts : this.clothingType.blockedPartsMap.get(slotEquippedTo)) {
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
		return clothingType.blockedPartsMap.get(slotEquippedTo);
	}
	
	public boolean isConcealsSlot(GameCharacter character, InventorySlot slotEquippedTo, InventorySlot slotToCheck) {
		for(BlockedParts blockedPart : this.getBlockedPartsMap(character, slotEquippedTo)) {
			if(blockedPart.concealedSlots.contains(slotToCheck) && !this.getItemTags(slotEquippedTo).contains(ItemTag.TRANSPARENT)) {
				return true;
			}
		}
		return false;
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
				case WINGED_BIPED:
					// These are all in such a position that normal clothing conceals as normal
					break;
				case ARACHNID:
					if(!this.getItemTags(slotEquippedTo).contains(ItemTag.FITS_ARACHNID_BODY)) { // Arachnid-specific clothing is configured to be correct.
						replace = true;
					}
					break;
				case AVIAN:
					if(!this.getItemTags(slotEquippedTo).contains(ItemTag.FITS_AVIAN_BODY)) { // Avian-specific clothing is configured to be correct.
						replace = true;
					}
					break;
				case QUADRUPEDAL:
					if(!this.getItemTags(slotEquippedTo).contains(ItemTag.FITS_TAUR_BODY)) { // Taur-specific clothing is configured to be correct.
						replace = true;
					}
					break;
			}
			if(replace) {
				List<InventorySlot> modifiedIncompatibleSlots = new ArrayList<>(clothingType.incompatibleSlotsMap.get(slotEquippedTo));
				
				if(InventorySlot.getHumanoidSlots().contains(slotEquippedTo)) {
					modifiedIncompatibleSlots.removeIf(slot -> !InventorySlot.getHumanoidSlots().contains(slot));
				} else {
					modifiedIncompatibleSlots.removeIf(slot -> InventorySlot.getHumanoidSlots().contains(slot));
				}
				
				return modifiedIncompatibleSlots;
			}
		}
		return clothingType.incompatibleSlotsMap.get(slotEquippedTo);
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
				case WINGED_BIPED:
					// These are all in such a position that normal clothing conceals as normal
					break;
				case ARACHNID:
					if(!this.getItemTags(slotEquippedTo).contains(ItemTag.FITS_ARACHNID_BODY)) { // Arachnid-specific clothing is configured to be correct.
						// Arachnid crotch boobs are on the front, so that conceals as normal. Genitalia are not concealed.
						replaceGroinAccess = true;
					}
					break;
				case AVIAN:
					if(!this.getItemTags(slotEquippedTo).contains(ItemTag.FITS_AVIAN_BODY)) { // Avian-specific clothing is configured to be correct.
						// Avian crotch boobs are on the front, so that conceals as normal. Genitalia are not concealed.
						replaceGroinAccess = true;
					}
					break;
				case QUADRUPEDAL:
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
				for(BlockedParts blockedparts : clothingType.blockedPartsMap.get(slotEquippedTo)) {
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
		return clothingType.displacementTypesAvailableWithoutNONE.get(slotEquippedTo);
	}
	
}
