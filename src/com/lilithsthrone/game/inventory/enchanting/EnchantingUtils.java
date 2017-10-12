package com.lilithsthrone.game.inventory.enchanting;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemEffect;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.75
 * @version 0.1.84
 * @author Innoxia
 */
public class EnchantingUtils {

	public static AbstractItem craftItem(AbstractCoreItem ingredient, List<ItemEffect> effects) {

		AbstractItem craftedItem = null;
		
		craftedItem = AbstractItemType.generateItem((AbstractItemType) ingredient.getEnchantmentItemType());
		
		List<ItemEffect> effectsToBeAdded = new ArrayList<>();
		for(ItemEffect ie : effects)
			effectsToBeAdded.add(ie);
		
		craftedItem.setItemEffects(effectsToBeAdded);

		craftedItem.setName(getPotionName(ingredient, effectsToBeAdded));
					
		craftedItem.setSVGString(getSVGString(ingredient, effectsToBeAdded));
		
		return craftedItem;
	}
	
	public static String getPotionName(AbstractCoreItem ingredient, List<ItemEffect> effects) {
		
		String potionName = ((AbstractItemType) ingredient.getEnchantmentItemType()).getName(false), potionDescriptor = "", potionSuffix = "", potionPreSuffix = ""; // it was either PreSuffix or PrefixSuffix...
		
		switch(ingredient.getEnchantmentEffect()) {
			case ATTRIBUTE_CORRUPTION:
				potionDescriptor = "viscous ";
				break;
			case ATTRIBUTE_FITNESS:
				potionDescriptor = "bubbling ";
				break;
			case ATTRIBUTE_INTELLIGENCE:
				potionDescriptor = "soothing ";
				break;
			case ATTRIBUTE_STRENGTH:
				potionDescriptor = "vivid ";
				break;
			case ATTRIBUTE_SEXUAL:
				potionDescriptor = "aphrodisiac ";
				break;
			case RACE_CAT_MORPH:
				potionDescriptor = "feline ";
				break;
			case RACE_DOG_MORPH:
				potionDescriptor = "canine ";
				break;
			case RACE_HORSE_MORPH:
				potionDescriptor = "equine ";
				break;
			case RACE_WOLF_MORPH:
				potionDescriptor = "lupine ";
				break;
			case RACE_HARPY:
				potionDescriptor = "avian ";
				break;
			case RACE_HUMAN:
				potionDescriptor = "human ";
				break;
			case RACE_DEMON:
				potionDescriptor = "demonic ";
				break;
			case RACE_COW_MORPH:
				potionDescriptor = "bovine ";
				break;
			case RACE_SQUIRREL_MORPH:
				potionDescriptor = "squirrel ";
				break;
			default:
				break;
		}

		String finalPotionName = potionDescriptor + potionName;
		
		for(ItemEffect ie : effects) {
			if(ie.getPrimaryModifier() != TFModifier.NONE) {
				potionSuffix = ie.getPrimaryModifier().getDescriptor();
				
				if(ie.getSecondaryModifier() != TFModifier.NONE) {
					potionPreSuffix = ie.getSecondaryModifier().getDescriptor();
				}
				
				if(potionSuffix!="") {
					if(potionPreSuffix!="") {
						if(ie.getSecondaryModifier().isSoloDescriptor())
							finalPotionName += " of "+potionPreSuffix;
						else
							finalPotionName += " of "+potionPreSuffix+" "+potionSuffix;
					} else {
						finalPotionName += " of "+potionSuffix;
					}
				}
				
				break;
			}
		}
		
		return Util.capitaliseSentence(finalPotionName);
	}
	
	public static int getCost(AbstractCoreItem ingredient, List<ItemEffect> effects) {
		
		int cost = 0;
		
		for(ItemEffect ie : effects) {
			cost+=ie.getCost();
		}
		
		if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING)) {
			cost/=2;
		}
		
		return cost;
	}
	
	public static String getSVGString(AbstractCoreItem ingredient, List<ItemEffect> effects) {
		
		StringBuilder SVGImageSB = new StringBuilder();
		
		SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getRefinedBackgroundMap().get(ingredient.getEnchantmentEffect().getColour())+"</div>");
		
		String s = ((AbstractItemType) ingredient.getEnchantmentItemType()).getSVGString();
		
		Colour colour = Colour.CLOTHING_BLUE_LIGHT;
		
		for(ItemEffect ie : effects) {
			if(ie.getPrimaryModifier() != null && ie.getPrimaryModifier() != TFModifier.NONE) {
				colour = ie.getPrimaryModifier().getColour();
				
				break;
			}
		}
		
		s = s.replaceAll("#ff2a2a", colour.getShades()[0]);
		s = s.replaceAll("#ff5555", colour.getShades()[1]);
		s = s.replaceAll("#ff8080", colour.getShades()[2]);
		s = s.replaceAll("#ffaaaa", colour.getShades()[3]);
		s = s.replaceAll("#ffd5d5", colour.getShades()[4]);
		SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+s+"</div>");
		
		for(ItemEffect ie : effects) {
			if(ie.getSecondaryModifier() != null && ie.getSecondaryModifier() != TFModifier.NONE) {
				SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getRefinedSwirlsMap().get(ie.getSecondaryModifier().getColour())+"</div>");
				
				break;
			}
		}
		
		return SVGImageSB.toString();
	}
}
