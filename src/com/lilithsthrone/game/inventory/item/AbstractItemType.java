package com.lilithsthrone.game.inventory.item;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.controller.xmlParsing.XMLLoadException;
import com.lilithsthrone.controller.xmlParsing.XMLMissingTagException;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.84
 * @version 0.3.9.2
 * @author Innoxia
 */
public abstract class AbstractItemType extends AbstractCoreType {
	
	private String determiner;
	private String name;
	private String namePlural;
	private String description;
	private String useDescriptor;
	private String authorDescription;

	private boolean sexUse;
	private boolean combatUseAllies;
	private boolean combatUseEnemies;
	private boolean consumedOnUse;
	
	private Rarity rarity;
	
	private int value;
	
	private boolean plural;
	private boolean mod;
	private boolean fromExternalFile;

	private String pathName;
	private List<Colour> colourShades;
	
	protected String SVGString;
	protected List<String> effectTooltipLines;
	protected String specialEffect;

	protected List<String> useDescriptionsSelf;
	protected List<String> useDescriptionsOther;
	
	protected List<ItemEffect> effects;
	protected Set<ItemTag> itemTags;

	public AbstractItemType(
			int value,
			String determiner,
			boolean plural,
			String name,
			String namePlural,
			String description,
			String pathName,
			Colour colourPrimary,
			Colour colourSecondary,
			Colour colourTertiary,
			Rarity rarity,
			List<ItemEffect> effects,
			List<ItemTag> itemTags) {
		this.determiner = determiner;
		this.plural = plural;
		this.mod = false;
		this.fromExternalFile = false;
		this.name = name;
		this.namePlural = namePlural;
		this.description = description;
		this.useDescriptor = "use";
		this.pathName = pathName==null?"":pathName;
		this.authorDescription = "";

		this.sexUse = true;
		this.combatUseAllies = true;
		this.combatUseEnemies = false;
		this.consumedOnUse = true;
		
		this.value = value;
		this.rarity = rarity;
		
		this.itemTags = new HashSet<>();
		if(itemTags!=null) {
			this.itemTags.addAll(itemTags);
		}
		
		this.effectTooltipLines = new ArrayList<>();
		
		this.useDescriptionsSelf = Util.newArrayListOfValues("[npc.Name] [npc.verb(use)] the item.");
		this.useDescriptionsOther = Util.newArrayListOfValues("[npc.Name] [npc.verb(use)] the item on [npc2.name].");
		
		specialEffect = "";
		
		if(effects==null) {
			this.effects = new ArrayList<>();
		} else {
			this.effects=effects;
		}
		
		this.colourShades = new ArrayList<>();
		
		if (colourPrimary == null) {
			this.colourShades.add(PresetColour.CLOTHING_BLACK);
		} else {
			this.colourShades.add(colourPrimary);
		}
		if (colourSecondary == null) {
			this.colourShades.add(PresetColour.CLOTHING_BLACK);
		} else {
			this.colourShades.add(colourSecondary);
		}
		if (colourTertiary == null) {
			this.colourShades.add(PresetColour.CLOTHING_BLACK);
		} else {
			this.colourShades.add(colourTertiary);
		}
		
		SVGString = null;
	}

	public AbstractItemType(File itemXMLFile, String author, boolean mod) throws XMLLoadException { // Be sure to catch this exception correctly - if it's thrown mod is invalid and should not be continued to load
		boolean debug = false;
		
		try{
			Element itemElement = Element.getDocumentRootElement(itemXMLFile); // Loads the document and returns the root element - in item mods it's <item>
			Element coreAttributes = null;
			try {
				coreAttributes = itemElement.getMandatoryFirstOf("coreAtributes");
			} catch (XMLMissingTagException ex) {
				coreAttributes = itemElement.getMandatoryFirstOf("coreAttributes");
			}

			if(debug) {
				System.out.println("1");
			}
			
			this.mod = mod;
			this.fromExternalFile = true;

			if(coreAttributes.getOptionalFirstOf("authorTag").isPresent()) {
				this.authorDescription = coreAttributes.getMandatoryFirstOf("authorTag").getTextContent();
			} else if(!author.equalsIgnoreCase("innoxia")){
				this.authorDescription = "A tag discreetly attached to the "+(plural?namePlural:name)+" informs you that "+(plural?"they were":"it was")+" made by a certain '"+Util.capitaliseSentence(author)+"'.";
			} else {
				this.authorDescription = "";
			}
			
			this.name = coreAttributes.getMandatoryFirstOf("name").getTextContent();
			this.namePlural = coreAttributes.getMandatoryFirstOf("namePlural").getTextContent();
			this.description = coreAttributes.getMandatoryFirstOf("description").getTextContent();
			this.useDescriptor = coreAttributes.getMandatoryFirstOf("useDescriptor").getTextContent();
			this.plural = Boolean.valueOf(coreAttributes.getMandatoryFirstOf("namePlural").getAttribute("pluralByDefault"));
			this.value = Integer.valueOf(coreAttributes.getMandatoryFirstOf("value").getTextContent());
			this.rarity = Rarity.valueOf(coreAttributes.getMandatoryFirstOf("rarity").getTextContent());
			
			this.sexUse = Boolean.valueOf(coreAttributes.getMandatoryFirstOf("sexUse").getTextContent());
			this.combatUseAllies = Boolean.valueOf(coreAttributes.getMandatoryFirstOf("combatUseAllies").getTextContent());
			this.combatUseEnemies = Boolean.valueOf(coreAttributes.getMandatoryFirstOf("combatUseEnemies").getTextContent());
			this.consumedOnUse = Boolean.valueOf(coreAttributes.getMandatoryFirstOf("consumedOnUse").getTextContent());
			
			this.pathName = itemXMLFile.getParentFile().getAbsolutePath() + "/"+ coreAttributes.getMandatoryFirstOf("imageName").getTextContent();
			SVGString = null;
			
			Colour colourShade = PresetColour.getColourFromId(coreAttributes.getMandatoryFirstOf("colourPrimary").getTextContent());
			Colour colourShadeSecondary = null;
			if(coreAttributes.getOptionalFirstOf("colourSecondary").isPresent() && !coreAttributes.getMandatoryFirstOf("colourSecondary").getTextContent().isEmpty()) {
				colourShadeSecondary = PresetColour.getColourFromId(coreAttributes.getMandatoryFirstOf("colourSecondary").getTextContent());
			}
			Colour colourShadeTertiary = null;
			if(coreAttributes.getOptionalFirstOf("colourTertiary").isPresent() && !coreAttributes.getMandatoryFirstOf("colourTertiary").getTextContent().isEmpty()) {
				colourShadeTertiary = PresetColour.getColourFromId(coreAttributes.getMandatoryFirstOf("colourTertiary").getTextContent());
			}
			this.colourShades = Util.newArrayListOfValues(colourShade, colourShadeSecondary, colourShadeTertiary);
			
			
			if(debug) {
				System.out.println("2");
			}

			this.effects = new ArrayList<>();

			this.specialEffect = coreAttributes.getMandatoryFirstOf("applyEffects").getTextContent();
			
			this.effectTooltipLines = new ArrayList<>();
			if(coreAttributes.getOptionalFirstOf("effectTooltipLines").isPresent()) {
				for(Element e : coreAttributes.getMandatoryFirstOf("effectTooltipLines").getAllOf("line")) {
					effectTooltipLines.add(e.getTextContent());
				}
			}
			
			this.itemTags = new HashSet<>(Util.toEnumList(coreAttributes.getMandatoryFirstOf("itemTags").getAllOf("tag"), ItemTag.class));
			
			if(debug) {
				System.out.println("3");
			}
			
			if(itemElement.getOptionalFirstOf("useDescriptions").isPresent()) {
				this.useDescriptionsSelf = itemElement
						.getMandatoryFirstOf("useDescriptions")
						.getAllOf("selfUse").stream()
						.map(o -> o.getTextContent())
						.collect(Collectors.toList());
				if(useDescriptionsSelf.isEmpty()) {
					this.useDescriptionsSelf = Util.newArrayListOfValues("[npc.Name] [npc.verb(use)] the item.");
				}
				
				this.useDescriptionsOther = itemElement
						.getMandatoryFirstOf("useDescriptions")
						.getAllOf("otherUse").stream()
						.map(o -> o.getTextContent())
						.collect(Collectors.toList());
				if(useDescriptionsOther.isEmpty()) {
					this.useDescriptionsOther = Util.newArrayListOfValues("[npc.Name] [npc.verb(use)] the item on [npc2.name].");
				}
				
			} else {
				this.useDescriptionsSelf = Util.newArrayListOfValues("[npc.Name] [npc.verb(use)] the item.");
				this.useDescriptionsOther = Util.newArrayListOfValues("[npc.Name] [npc.verb(use)] the item on [npc2.name].");
			}
			
		}
		catch(XMLMissingTagException ex){
			throw new XMLLoadException(ex, itemXMLFile);
		}
		catch(Exception e){
			System.out.println(e);
			throw new XMLLoadException(e, itemXMLFile);
		}
	}
	
	@Override
	public boolean equals(Object o) { // I know it doesn't include everything, but this should be enough to check for equality.
		if(super.equals(o)){
			if(o instanceof AbstractItemType){
				if(((AbstractItemType)o).getName(false).equals(getName(false))
						&& ((AbstractItemType)o).getPathName().equals(getPathName())
						&& ((AbstractItemType)o).getRarity() == getRarity()
						&& ((AbstractItemType)o).getEffects().equals(getEffects())
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
		result = 31 * result + getName(false).hashCode();
		result = 31 * result + getPathName().hashCode();
		result = 31 * result + getRarity().hashCode();
		result = 31 * result + getEffects().hashCode();
		return result;
	}
	
	public String getId() {
		return ItemType.getItemToIdMap().get(this);
	}

	public boolean isMod() {
		return mod;
	}
	
	public boolean isFromExternalFile() {
		return fromExternalFile;
	}

	public String getAuthorDescription() {
		return authorDescription;
	}
	
	public List<ItemEffect> getEffects() {
		return effects;
	}
	
	public String getSpecialEffect() {
		return specialEffect;
	}

	public boolean isAbleToBeSold() {
		return getRarity()!=Rarity.QUEST;
	}
	
	public boolean isAbleToBeDropped() {
		return getRarity()!=Rarity.QUEST;
	}
	
	// Enchantments:
	
	public int getEnchantmentLimit() {
		return 100;
	}
	
	public AbstractItemEffectType getEnchantmentEffect() {
		return null;
	}
	
	public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
		return null;
	}
	
	// Getters & setters:

	public String getDeterminer() {
		return (determiner!=null?determiner:"");
	}

	public boolean isPlural() {
		return plural;
	}

	public String getName(boolean displayName) {
		if(displayName) {
			return Util.capitaliseSentence((determiner!=null?determiner:"") + " <span style='color: " + rarity.getColour().toWebHexString() + ";'>" + (this.isPlural()?namePlural:name) + "</span>");
		} else {
			return (this.isPlural()?namePlural:name);
		}
	}
	
	public String getNamePlural(boolean displayName) {
		if(displayName) {
			return Util.capitaliseSentence((determiner!=null?determiner:"") + " <span style='color: " + rarity.getColour().toWebHexString() + ";'>" + namePlural + "</span>");
		} else {
			return namePlural;
		}
	}

	public String getDescription() {
		return description;
	}

	public List<String> getEffectTooltipLines() {
		List<String> parsed = new ArrayList<>();
		for(String s : effectTooltipLines) {
			parsed.add(UtilText.parse(s));
		}
		return parsed;
	}

	public String getDisplayName(boolean withRarityColour) {
		return Util.capitaliseSentence((determiner!=null?determiner:"") + (withRarityColour
				? (" <span style='color: " + rarity.getColour().toWebHexString() + ";'>" + (this.isPlural()?getNamePlural(false):getName(false)) + "</span>")
				: (this.isPlural()?getNamePlural(false):getName(false))));
	}

	public String getPathName() {
		return pathName;
	}

	public Colour getColour() {
		return colourShades.get(0);
	}

	public List<Colour> getColourShades() {
		return colourShades;
	}

	public int getValue(List<ItemEffect> effects) {
		return value;
	}

	public String getSVGString() {
		if(SVGString==null) {
			if(pathName!=null && !pathName.isEmpty()) {
				try {
					if(isFromExternalFile()) {
						List<String> lines = Files.readAllLines(Paths.get(pathName));
						StringBuilder sb = new StringBuilder();
						for(String line : lines) {
							sb.append(line);
						}
						SVGString = sb.toString();
						SVGString = SvgUtil.colourReplacement(this.getId(), colourShades, null, SVGString);
						
					} else {
						InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/" + pathName + ".svg");
						if(is==null) {
							System.err.println("Error! AbstractItemType icon file does not exist (Trying to read from '"+pathName+"')!");
						}
						String s = Util.inputStreamToString(is);
						SVGString = SvgUtil.colourReplacement(this.getId(), colourShades, null, s);
						is.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else {
				SVGString = "";
			}
		}
		return SVGString;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public String getUseName() {
		return useDescriptor;
	}
	
	public String getUseTooltipDescription(GameCharacter user, GameCharacter target) {
		if(user==null || target==null || user.equals(target)) {
			return Util.capitaliseSentence(getUseName()) + " the " + getName(false) + ".";
		} else {
			return UtilText.parse(target, "Get [npc.name] to " + getUseName() + " the " + getName(false) + ".");
		}
	}
	
	public String getUseDescription(GameCharacter user, GameCharacter target) {
		if(user.equals(target)) {
			return "<p>"
						+ UtilText.parse(user, target, Util.randomItemFrom(useDescriptionsSelf))
					+ "</p>";
		} else {
			return "<p>"
					+ UtilText.parse(user, target, Util.randomItemFrom(useDescriptionsOther))
				+ "</p>";
		}
	}

	public boolean isAbleToBeUsedFromInventory() {
		return true;
	}
	
	public String getUnableToBeUsedFromInventoryDescription() {
		return "This item cannot be used in this way!";
	}
	
	public boolean isAbleToBeUsed(GameCharacter target) {
		return true;
	}
	
	public String getUnableToBeUsedDescription(GameCharacter target) {
		return "This item cannot be used in this way!";
	}
	
	public boolean isAbleToBeUsedInSex() {
		return sexUse;
	}
	
	public boolean isAbleToBeUsedInCombatAllies() {
		return combatUseAllies;
	}
	
	public boolean isAbleToBeUsedInCombatEnemies() {
		return combatUseEnemies;
	}
	
	public boolean isConsumedOnUse() {
		return consumedOnUse;
	}
	
	public boolean isTransformative() {
		return false;
	}
	
	public boolean isGift() {
		return false;
	}
	
	public boolean isFetishGiving() {
		return false;
	}

	public Set<ItemTag> getItemTags() {
		return itemTags;
	}

}
