package com.lilithsthrone.game.inventory.enchanting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.markings.AbstractTattooType;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.dialogue.utils.EnchantmentDialogue;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.75
 * @version 0.2.6
 * @author Innoxia
 */
public class EnchantingUtils {
	
	public static final int FLAME_COST_MODIFER = 500;
	
	public static AbstractItem craftItem(AbstractCoreItem ingredient, List<ItemEffect> effects) {
		AbstractItem craftedItem = null;
		
		craftedItem = AbstractItemType.generateItem((AbstractItemType) ingredient.getEnchantmentItemType(effects));
		
		List<ItemEffect> effectsToBeAdded = new ArrayList<>();
		effectsToBeAdded.addAll(effects);
		
		craftedItem.setItemEffects(effectsToBeAdded);
		
		craftedItem.setName(EnchantmentDialogue.getOutputName());
		craftedItem.setColour(ingredient.getEnchantmentEffect().getColour());
		craftedItem.setSVGString(getSVGString(ingredient, effectsToBeAdded));
		
		return craftedItem;
	}
	
	public static AbstractClothing craftClothing(AbstractCoreItem ingredient, List<ItemEffect> effects) {
		AbstractClothing craftedClothing = null;

		List<ItemEffect> effectsToBeAdded = new ArrayList<>();
		effectsToBeAdded.addAll(effects);
		
		craftedClothing = AbstractClothingType.generateClothing(
				(AbstractClothingType) ingredient.getEnchantmentItemType(effects),
				ingredient.getColour(),
				((AbstractClothing)ingredient).getSecondaryColour(),
				((AbstractClothing)ingredient).getTertiaryColour(),
				effectsToBeAdded);
		
		craftedClothing.setPattern(((AbstractClothing)ingredient).getPattern());
		craftedClothing.setPatternColour(((AbstractClothing)ingredient).getPatternColour());
		craftedClothing.setPatternSecondaryColour(((AbstractClothing)ingredient).getPatternSecondaryColour());
		craftedClothing.setPatternTertiaryColour(((AbstractClothing)ingredient).getPatternTertiaryColour());
		
		craftedClothing.setName(EnchantmentDialogue.getOutputName());
		
		craftedClothing.setEnchantmentKnown(true);
		
		return craftedClothing;
	}
	
	public static void craftTattoo(AbstractCoreItem ingredient, List<ItemEffect> effects) {
		List<ItemEffect> effectsToBeAdded = new ArrayList<>(effects);
		((Tattoo)ingredient).setEffects(effectsToBeAdded);
		((Tattoo)ingredient).setName(EnchantmentDialogue.getOutputName());
	}
	
	public static AbstractWeapon craftWeapon(AbstractCoreItem ingredient, List<ItemEffect> effects) {
		AbstractWeapon craftedWeapon = null;

		List<ItemEffect> effectsToBeAdded = new ArrayList<>();
		effectsToBeAdded.addAll(effects);
		
		craftedWeapon = AbstractWeaponType.generateWeapon(
				(AbstractWeaponType) ingredient.getEnchantmentItemType(effects),
				((AbstractWeapon) ingredient).getDamageType(),
				ingredient.getColour(),
				((AbstractWeapon)ingredient).getSecondaryColour());
		
		craftedWeapon.setEffects(effectsToBeAdded);
		
		craftedWeapon.setName(EnchantmentDialogue.getOutputName());
		
		return craftedWeapon;
	}
	
	public static String getPotionName(AbstractCoreItem ingredient, List<ItemEffect> effects) {
		
		if(ingredient.getEnchantmentItemType(effects) instanceof AbstractClothingType
				|| ingredient.getEnchantmentItemType(effects) instanceof AbstractTattooType
				|| ingredient.getEnchantmentItemType(effects) instanceof AbstractWeaponType) {
			return Util.capitaliseSentence(ingredient.getName());
		}
		
		if(((AbstractItem)ingredient).getItemType().getId().equals(ItemType.ORIENTATION_HYPNO_WATCH.getId())) {
			if(effects.isEmpty() || effects.get(0).getPrimaryModifier()==TFModifier.REMOVAL) {
				return "Hypno-Watch";
			}
			if(effects.get(0).getPrimaryModifier()==TFModifier.ORIENTATION_GYNEPHILIC) {
				return "Gynephilic Hypno-Watch";
			} else if(effects.get(0).getPrimaryModifier()==TFModifier.ORIENTATION_AMBIPHILIC) {
				return "Ambiphilic Hypno-Watch";
			} else {
				return "Androphilic Hypno-Watch";
			}
		}
		
		String potionName = ((AbstractItemType) ingredient.getEnchantmentItemType(effects)).getName(false);
		String potionDescriptor = "";
		String potionSuffix = "";
		String potionPreSuffix = ""; // it was either PreSuffix or PrefixSuffix...
		
		if(ingredient!=null) {
			try {
				potionDescriptor = ingredient.getEffects().get(0).getItemEffectType().getPotionDescriptor();
			} catch(Exception ex) {
				// :3
				// Cat-face comments aren't helpful damn it!
				System.err.println("EnchantingUtils: getPotionName() error 1."); 
			}
		}
		
		String finalPotionName = ((potionDescriptor==null || potionDescriptor.isEmpty())?"":Util.capitaliseSentence(potionDescriptor)+" ") + potionName;
		
		for(ItemEffect ie : effects) {
			if(ie.getPrimaryModifier() != null && ie.getPrimaryModifier() != TFModifier.NONE) {
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
	
	
	
	private static Set<TFModifier> freePrimaryModifiers = Util.newHashSetOfValues(TFModifier.TF_MOD_WETNESS, TFModifier.TF_MILK, TFModifier.TF_CUM, TFModifier.TF_GIRLCUM);
	private static Set<TFModifier> freeSecondaryModifiers = Util.newHashSetOfValues(TFModifier.TF_MOD_WETNESS, TFModifier.TF_MOD_REGENERATION, TFModifier.TF_MOD_CUM_EXPULSION);
	
	private static boolean isEffectFreeForWaterSchool(ItemEffect effect) {
		return freePrimaryModifiers.contains(effect.getPrimaryModifier())
				|| freeSecondaryModifiers.contains(effect.getSecondaryModifier());
	}
	
	private static int applyDiscountsForPerksAndFetishes(AbstractCoreItem ingredient, int cost) {
		if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING) && ingredient instanceof AbstractItem) {
			cost/=2;
		}
		if(Main.game.getPlayer().hasPerkAnywhereInTree(Perk.CLOTHING_ENCHANTER) && ingredient instanceof AbstractClothing) {
			cost/=2;
		}
		return cost;
	}
	
	public static int getModifierEffectCost(AbstractCoreItem ingredient, ItemEffect effect) {
		if(!(ingredient instanceof Tattoo)
				&& Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.WATER)
				&& isEffectFreeForWaterSchool(effect)) {
			return 0;
		}
		
		return applyDiscountsForPerksAndFetishes(ingredient, effect.getCost());
	}
	
	public static int getCost(AbstractCoreItem ingredient, List<ItemEffect> effects) {
		Map<ItemEffect, Integer> effectCount = new HashMap<>();
		for(ItemEffect ie : effects) {
			effectCount.merge(ie, 1, Integer::sum);
		}
		for(ItemEffect ie : ingredient.getEffects()) {
			if(effects.contains(ie)) {
				effectCount.merge(ie, -1, Integer::sum);
			} else {
				effectCount.merge(ie, 1, Integer::sum);
			}
		}
		
		if (!(ingredient instanceof Tattoo) && Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.WATER)) {
			effectCount.keySet().removeIf(EnchantingUtils::isEffectFreeForWaterSchool);
		}
		
		int cost = 0;
		for(Entry<ItemEffect, Integer> entry : effectCount.entrySet()) {
			cost += entry.getKey().getCost() * Math.abs(entry.getValue());
		}
		
		return applyDiscountsForPerksAndFetishes(ingredient, cost);
	}
	
	public static String getSVGString(AbstractCoreItem ingredient, List<ItemEffect> effects) {
		
		if(ingredient.getEnchantmentItemType(effects) instanceof AbstractClothingType
				|| ingredient.getEnchantmentItemType(effects) instanceof AbstractTattooType
				|| ingredient.getEnchantmentItemType(effects) instanceof AbstractWeaponType) {
			return ingredient.getSVGString();
		}
		
		if(((AbstractItem)ingredient).getItemType().getId().equals(ItemType.ORIENTATION_HYPNO_WATCH.getId())) {
			if(effects.isEmpty() || effects.get(0).getPrimaryModifier()==TFModifier.REMOVAL) {
				return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchBase();
			}
			
			if(effects.get(0).getPrimaryModifier()==TFModifier.ORIENTATION_GYNEPHILIC) {
				return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchGynephilic();
				
			} else if(effects.get(0).getPrimaryModifier()==TFModifier.ORIENTATION_AMBIPHILIC) {
				return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchAmbiphilic();
				
			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchAndrophilic();
			}
		}
		
		StringBuilder SVGImageSB = new StringBuilder();
		
		SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getRefinedBackgroundMap().get(ingredient.getEnchantmentEffect().getColour())+"</div>");
		
		String s = ((AbstractItemType) ingredient.getEnchantmentItemType(effects)).getSVGString();
		
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
	
	public static String getImportedSVGString(AbstractCoreItem item, Colour importedColour, List<ItemEffect> effects) {

		if(((AbstractItem)item).getItemType().getId().equals(ItemType.ORIENTATION_HYPNO_WATCH.getId())) {
			if(effects.isEmpty() || effects.get(0).getPrimaryModifier()==TFModifier.REMOVAL) {
				return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchBase();
			}
			
			if(effects.get(0).getPrimaryModifier()==TFModifier.ORIENTATION_GYNEPHILIC) {
				return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchGynephilic();
				
			} else if(effects.get(0).getPrimaryModifier()==TFModifier.ORIENTATION_AMBIPHILIC) {
				return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchAmbiphilic();
				
			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchAndrophilic();
			}
		}
		
		StringBuilder SVGImageSB = new StringBuilder();
		
		String importedColourString = SVGImages.SVG_IMAGE_PROVIDER.getRefinedBackgroundMap().get(importedColour);
		if(importedColourString==null || importedColourString.isEmpty() || importedColourString.equals("null")) {
			importedColourString = SVGImages.SVG_IMAGE_PROVIDER.getRefinedBackgroundMap().get(effects.get(0).getItemEffectType().getColour());
		}
		
		SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+importedColourString+"</div>");
		
		String s = item.getSVGString();
		
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
