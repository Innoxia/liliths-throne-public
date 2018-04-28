package com.lilithsthrone.game.inventory.item;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.body.FluidMilk;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.84
 * @version 0.2.1
 * @author Innoxia
 */
public abstract class AbstractItemType extends AbstractCoreType implements Serializable {

	protected static final long serialVersionUID = 1L;
	
	private String determiner, name, namePlural, description, pathName;
	private boolean plural;
	private Colour colourPrimary;
	private Colour colourSecondary;
	private Colour colourTertiary;
	private int value;
	private Rarity rarity;
	protected String SVGString;
	private TFEssence relatedEssence;
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
			TFEssence relatedEssence,
			List<ItemEffect> effects,
			List<ItemTag> itemTags) {

		this.determiner = determiner;
		this.plural = plural;
		this.name = name;
		this.namePlural = namePlural;
		this.description = description;
		this.pathName = pathName;

		this.value = value;
		this.rarity = rarity;
		
		this.relatedEssence = relatedEssence;
		
		this.itemTags = new HashSet<>();
		if(itemTags!=null) {
			this.itemTags.addAll(itemTags);
		}
		
		if(effects==null) {
			this.effects = new ArrayList<>();
		} else {
			this.effects=effects;
		}

		if (colourPrimary == null) {
			this.colourPrimary = com.lilithsthrone.utils.Colour.CLOTHING_BLACK;
		} else {
			this.colourPrimary = colourPrimary;
		}
		if (colourSecondary == null) {
			this.colourSecondary = com.lilithsthrone.utils.Colour.CLOTHING_BLACK;
		} else {
			this.colourSecondary = colourSecondary;
		}
		if (colourTertiary == null) {
			this.colourTertiary = com.lilithsthrone.utils.Colour.CLOTHING_BLACK;
		} else {
			this.colourTertiary = colourTertiary;
		}
		
		// Set this item's file image:
		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/" + pathName + ".svg");
			String s = Util.inputStreamToString(is);

			SVGString = colourReplacement(this.getColourPrimary(), this.getColourSecondary(), this.getColourTertiary(), s);
			
			is.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private String colourReplacement(Colour colour, Colour colourSecondary, Colour colourTertiary, String inputString) {
		String s = inputString;
		for (int i = 0; i <= 14; i++) {
			s = s.replaceAll("linearGradient" + i, this.hashCode() + colour.toString() + (colourSecondary!=null?colourSecondary.toString():"") + (colourTertiary!=null?colourTertiary.toString():"") + "linearGradient" + i);
			s = s.replaceAll("innoGrad" + i, this.hashCode() + colour.toString() + (colourSecondary!=null?colourSecondary.toString():"") + (colourTertiary!=null?colourTertiary.toString():"") + "innoGrad" + i);
			
		}
		s = s.replaceAll("#ff2a2a", colour.getShades()[0]);
		s = s.replaceAll("#ff5555", colour.getShades()[1]);
		s = s.replaceAll("#ff8080", colour.getShades()[2]);
		s = s.replaceAll("#ffaaaa", colour.getShades()[3]);
		s = s.replaceAll("#ffd5d5", colour.getShades()[4]);
		
		if(colourSecondary!=null) {
			s = s.replaceAll("#ff7f2a", colourSecondary.getShades()[0]);
			s = s.replaceAll("#ff9955", colourSecondary.getShades()[1]);
			s = s.replaceAll("#ffb380", colourSecondary.getShades()[2]);
			s = s.replaceAll("#ffccaa", colourSecondary.getShades()[3]);
			s = s.replaceAll("#ffe6d5", colourSecondary.getShades()[4]);
		}
		
		if(colourTertiary!=null) {
			s = s.replaceAll("#ffd42a", colourTertiary.getShades()[0]);
			s = s.replaceAll("#ffdd55", colourTertiary.getShades()[1]);
			s = s.replaceAll("#ffe680", colourTertiary.getShades()[2]);
			s = s.replaceAll("#ffeeaa", colourTertiary.getShades()[3]);
			s = s.replaceAll("#fff6d5", colourTertiary.getShades()[4]);
		}
		
		return s;
	}
	
	@Override
	public boolean equals (Object o) { // I know it doesn't include everything, but this should be enough to check for equality.
		if(super.equals(o)){
			if(o instanceof AbstractItemType){
				if(((AbstractItemType)o).getName(false).equals(getName(false))
						&& ((AbstractItemType)o).getPathName().equals(getPathName())
						&& ((AbstractItemType)o).getRarity() == getRarity()
						&& ((AbstractItemType)o).getRelatedEssence() == getRelatedEssence()
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
		if(getRelatedEssence() != null)
			result = 31 * result + getRelatedEssence().hashCode();
		result = 31 * result + getEffects().hashCode();
		return result;
	}

	public static AbstractItem generateItem(AbstractItemType itemType) {
		return new AbstractItem(itemType) {
			private static final long serialVersionUID = 1L;
		};
	}
	
	public static AbstractItem generateFilledCondom(Colour colour, GameCharacter character, FluidCum cum) {
		return new AbstractFilledCondom(ItemType.CONDOM_USED, colour, character, cum, character.getPenisRawCumProductionValue()) {
			private static final long serialVersionUID = 1L;
		};
	}

	public static AbstractItem generateFilledBreastPump(Colour colour, GameCharacter character, FluidMilk milk, int quantity) {
		return new AbstractFilledBreastPump(ItemType.MOO_MILKER_FULL, colour, character, milk, quantity) {
			private static final long serialVersionUID = 1L;
		};
	}
	
	public String getId() {
		return ItemType.itemToIdMap.get(this);
	}
	
	public List<ItemEffect> getEffects() {
		return effects;
	}
	
	public boolean canBeSold() {
		return true;
	}
	
	// Enchantments:
	
	public int getEnchantmentLimit() {
		return 100;
	}
	
	public AbstractItemEffectType getEnchantmentEffect() {
		return null;
	}
	
	public TFEssence getRelatedEssence() {
		return relatedEssence;
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

	public String getDisplayName(boolean withRarityColour) {
		return Util.capitaliseSentence((determiner!=null?determiner:"") + (withRarityColour
				? (" <span style='color: " + rarity.getColour().toWebHexString() + ";'>" + (this.isPlural()?getNamePlural(false):getName(false)) + "</span>")
				: (this.isPlural()?getNamePlural(false):getName(false))));
	}

	public String getPathName() {
		return pathName;
	}

	public Colour getColourPrimary() {
		return colourPrimary;
	}
	
	public Colour getColourSecondary() {
		return colourSecondary;
	}
	
	public Colour getColourTertiary() {
		return colourTertiary;
	}

	public int getValue() {
		return value;
	}

	public String getSVGString() {
		return SVGString;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public String getUseName() {
		return "use";
	}
	
	public String getUseDescription(GameCharacter user, GameCharacter target) {
		return "<p>"
					+ "You use the item."
				+ "</p>";
	}

	public boolean isAbleToBeUsedFromInventory() {
		return true;
	}
	
	public boolean isAbleToBeUsed(GameCharacter target) {
		return !Main.game.isInCombat() || target.isPlayer();
	}
	
	public boolean isAbleToBeUsedInSex() {
		return true;
	}
	
	public boolean isAbleToBeUsedInCombat() {
		return true;
	}
	
	public boolean isConsumedOnUse() {
		return true;
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
	
	public String getUnableToBeUsedFromInventoryDescription() {
		return "This item cannot be used in this way!";
	}
	
	public String getUnableToBeUsedDescription(GameCharacter target) {
		return "This item cannot be used in this way!";
	}
	
	public String getDyeBrushEffects(AbstractClothing clothing, Colour colour) {
		return "<p>"
					+ "As you take hold of the Dye-brush, you see the purple glow around the tip growing in strength."
					+ " The closer you move it to your " + clothing.getName() + ", the brighter the glow becomes, until suddenly, images of different colours start flashing through your mind."
					+ " As you touch the bristles to the " + clothing.getName() + "'s surface, the Dye-brush instantly evaporates!"
					+ " You see that the arcane enchantment has dyed the " + clothing.getName() + " " + colour.getName() + "."
				+ "</p>";
	}

	public Set<ItemTag> getItemTags() {
		return itemTags;
	}
}
