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
import com.lilithsthrone.game.combat.spells.SpellSchool;
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
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.75
 * @version 0.3.6.9
 * @author Innoxia
 */
public class EnchantingUtils {
	
	public static final int FLAME_COST_MODIFER = 500;
	
	public static AbstractItem craftItem(AbstractCoreItem ingredient, List<ItemEffect> effects) {
		AbstractItem craftedItem = null;
		
		craftedItem = Main.game.getItemGen().generateItem((AbstractItemType) ingredient.getEnchantmentItemType(effects));
		
		List<ItemEffect> effectsToBeAdded = new ArrayList<>();
		effectsToBeAdded.addAll(effects);
		
		craftedItem.setItemEffects(effectsToBeAdded);

		if(!EnchantmentDialogue.getOutputName().equals(ingredient.getName())) {
			craftedItem.setName(EnchantmentDialogue.getOutputName());
		}
		craftedItem.setColour(0, ingredient.getEnchantmentEffect().getColour());
		craftedItem.setSVGString(getSVGString(ingredient, effectsToBeAdded));
		
		return craftedItem;
	}
	
	public static AbstractClothing craftClothing(AbstractCoreItem ingredient, List<ItemEffect> effects) {
		AbstractClothing craftedClothing = null;

		List<ItemEffect> effectsToBeAdded = new ArrayList<>(effects);
		
		craftedClothing = Main.game.getItemGen().generateClothing(
				(AbstractClothingType) ingredient.getEnchantmentItemType(effects),
				((AbstractClothing)ingredient).getColours(),
				effectsToBeAdded);
		
		craftedClothing.setPattern(((AbstractClothing)ingredient).getPattern());
		craftedClothing.setPatternColours(((AbstractClothing)ingredient).getPatternColours());
		
		if(!EnchantmentDialogue.getOutputName().equals(ingredient.getName())) {
			craftedClothing.setName(EnchantmentDialogue.getOutputName());
		}
		
		craftedClothing.setEnchantmentKnown(null, true);
		
		craftedClothing.setStickers(((AbstractClothing)ingredient).getStickers());

//		System.out.println("Has clothing: "+Main.game.getPlayer().hasClothing(craftedClothing));
//		for(char c : EnchantmentDialogue.getOutputName().toCharArray()) {
//			System.out.print("["+c+","+(int)c+"]");
//		}
		
		return craftedClothing;
	}
	
	public static Tattoo craftTattoo(AbstractCoreItem ingredient, List<ItemEffect> effects) {
		List<ItemEffect> effectsToBeAdded = new ArrayList<>(effects);
		
		Tattoo newTattoo = new Tattoo(((Tattoo)ingredient).getType(),
				((Tattoo)ingredient).getPrimaryColour(),
				((Tattoo)ingredient).getSecondaryColour(),
				((Tattoo)ingredient).getTertiaryColour(),
				((Tattoo)ingredient).isGlowing(),
				((Tattoo)ingredient).getWriting(),
				((Tattoo)ingredient).getCounter());
		
		newTattoo.setEffects(effectsToBeAdded);

		if(!EnchantmentDialogue.getOutputName().equals(ingredient.getName())) {
			newTattoo.setName(EnchantmentDialogue.getOutputName());
		}
		
		return newTattoo;
	}
	
	public static AbstractWeapon craftWeapon(AbstractCoreItem ingredient, List<ItemEffect> effects) {
		AbstractWeapon craftedWeapon = null;

		List<ItemEffect> effectsToBeAdded = new ArrayList<>();
		effectsToBeAdded.addAll(effects);
		
		craftedWeapon = Main.game.getItemGen().generateWeapon(
				(AbstractWeaponType) ingredient.getEnchantmentItemType(effects),
				((AbstractWeapon) ingredient).getDamageType(),
				((AbstractWeapon)ingredient).getColours());
		
		craftedWeapon.setEffects(effectsToBeAdded);

		if(!EnchantmentDialogue.getOutputName().equals(ingredient.getName())) {
			craftedWeapon.setName(EnchantmentDialogue.getOutputName());
		}
		
		return craftedWeapon;
	}
	
	public static String getPotionName(AbstractCoreItem ingredient, List<ItemEffect> effects) {
		
		if(ingredient.getEnchantmentItemType(effects) instanceof AbstractClothingType
				|| ingredient.getEnchantmentItemType(effects) instanceof AbstractTattooType
				|| ingredient.getEnchantmentItemType(effects) instanceof AbstractWeaponType) {
			return ingredient.getName();
		}
		
		if(((AbstractItem)ingredient).getItemType().getId().equals(ItemType.ORIENTATION_HYPNO_WATCH.getId())) {
			if(effects.isEmpty() || effects.get(0).getPrimaryModifier()==TFModifier.REMOVAL) {
				return "Hypno-Watch";
			}
			if(effects.get(0).getPrimaryModifier()==TFModifier.ORIENTATION_GYNEPHILIC) {
				return "Gynephilic Hypno-Watch";
				
			} else if(effects.get(0).getPrimaryModifier()==TFModifier.ORIENTATION_AMBIPHILIC) {
				return "Ambiphilic Hypno-Watch";
				
			} else if(effects.get(0).getPrimaryModifier()==TFModifier.ORIENTATION_ANDROPHILIC) {
				return "Androphilic Hypno-Watch";
				
			} else {
				return "Speech-modifying Hypno-Watch";
			}
		}
		
		String potionName = ((AbstractItemType) ingredient.getEnchantmentItemType(effects)).getName(false);
		String potionDescriptor = "";
		String potionSuffix = "";
		String potionPreSuffix = "";
		
		if(ingredient!=null) {
			if(!ingredient.getEffects().isEmpty()) {
				try {
					potionDescriptor = ingredient.getEffects().get(0).getItemEffectType().getPotionDescriptor();
				} catch(Exception ex) {
					System.err.println("EnchantingUtils: getPotionName() error 1."); 
				}
			} else if(ingredient instanceof AbstractItem) {
				potionDescriptor = ((AbstractItem)ingredient).getItemType().getPotionDescriptor();
			}
		}
		
		String finalPotionName = ((potionDescriptor==null || potionDescriptor.isEmpty())?"":Util.capitaliseSentence(potionDescriptor)+" ") + potionName;
		
		for(ItemEffect ie : effects) {
			if(ie.getPrimaryModifier() != null && ie.getPrimaryModifier() != TFModifier.NONE) {
				potionSuffix = ie.getPrimaryModifier().getDescriptor();
				
				if(ie.getSecondaryModifier() != TFModifier.NONE) {
					if(ie.getSecondaryModifier()==TFModifier.ARCANE_BOOST && ie.getPotency().isNegative()) {
						potionPreSuffix = "drained";
					} else {
						potionPreSuffix = ie.getSecondaryModifier().getDescriptor();
					}
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
	
	
	
	private static Set<TFModifier> freePrimaryModifiers = Util.newHashSetOfValues(TFModifier.TF_MOD_WETNESS, TFModifier.TF_MILK, TFModifier.TF_MILK_CROTCH, TFModifier.TF_CUM, TFModifier.TF_GIRLCUM);
	private static Set<TFModifier> freeSecondaryModifiers = Util.newHashSetOfValues(TFModifier.TF_MOD_WETNESS, TFModifier.TF_MOD_REGENERATION, TFModifier.TF_MOD_CUM_EXPULSION);
	
	private static boolean isEffectFreeForWaterSchool(ItemEffect effect) {
		return freePrimaryModifiers.contains(effect.getPrimaryModifier())
				|| freeSecondaryModifiers.contains(effect.getSecondaryModifier());
	}

	private static boolean isEffectFreeForBondageApplier(ItemEffect effect) {
		return Main.game.getPlayer().hasFetish(Fetish.FETISH_BONDAGE_APPLIER)
				&& (effect.getSecondaryModifier()==TFModifier.CLOTHING_SEALING || effect.getSecondaryModifier()==TFModifier.CLOTHING_ENSLAVEMENT || effect.getSecondaryModifier()==TFModifier.CLOTHING_SERVITUDE);
	}
	
	private static boolean isEffectFreeForRemovingPositiveAttribute(ItemEffect effect) {
		if(isEffectFreeForBondageApplier(effect)) {
			return true;
		}
		if(effect.getPrimaryModifier()==TFModifier.CLOTHING_ATTRIBUTE || effect.getPrimaryModifier()==TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
			return !effect.getPotency().isNegative();
		}
		if(TFModifier.getTFRacialBodyPartsList().contains(effect.getPrimaryModifier())) {
			return true;
		}
		return false;
	}
	
	private static int applyDiscountsForPerksAndFetishes(AbstractCoreItem ingredient, int cost) {
		if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING) && ingredient instanceof AbstractItem) {
			cost/=2;
		}
		if(Main.game.getPlayer().hasPerkAnywhereInTree(Perk.CLOTHING_ENCHANTER) && ingredient instanceof AbstractClothing) {
			cost/=2;
		}
		if(Main.game.getPlayer().hasPerkAnywhereInTree(Perk.WEAPON_ENCHANTER) && ingredient instanceof AbstractWeapon) {
			cost/=2;
		}
		return cost;
	}
	
	public static int getModifierEffectCost(boolean addingEffect, AbstractCoreItem ingredient, ItemEffect effect) {
		if(!(ingredient instanceof Tattoo)
				&& Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.WATER)
				&& isEffectFreeForWaterSchool(effect)) {
			return 0;
		}
		
		if(!addingEffect && isEffectFreeForRemovingPositiveAttribute(effect)) {
			return 0;
		}
		
		int cost = effect.getCost();
		
		if(addingEffect && isEffectFreeForBondageApplier(effect)) {
			return 0;
		}
		
		return applyDiscountsForPerksAndFetishes(ingredient, cost);
	}
	
	public static int getCost(AbstractCoreItem ingredient, List<ItemEffect> effects) {
		Map<ItemEffect, Integer> effectCount = new HashMap<>();
		for(ItemEffect ie : effects) {
			effectCount.merge(ie, 1, Integer::sum);
		}
		for(ItemEffect ie : ingredient.getEffects()) {
			if(effects.contains(ie)) {
				if(effectCount.get(ie)>0 || !isEffectFreeForRemovingPositiveAttribute(ie)) {
					effectCount.merge(ie, -1, Integer::sum);
				}
			} else {
				if(!isEffectFreeForRemovingPositiveAttribute(ie)) {
					effectCount.merge(ie, 1, Integer::sum);
				}
			}
		}
		
		if (!(ingredient instanceof Tattoo) && Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.WATER)) {
			effectCount.keySet().removeIf(EnchantingUtils::isEffectFreeForWaterSchool);
		}

		if (!(ingredient instanceof Tattoo)) {
			effectCount.keySet().removeIf(EnchantingUtils::isEffectFreeForBondageApplier);
		}
		
		int cost = 0;
		for(Entry<ItemEffect, Integer> entry : effectCount.entrySet()) {
			int costIncrement = entry.getKey().getCost() * Math.abs(entry.getValue());
			
			if(entry.getKey().getSecondaryModifier()==TFModifier.CLOTHING_SEALING) {
				switch(entry.getKey().getPotency()) {
					case MAJOR_BOOST:
						costIncrement*=4;
						break;
					case BOOST:
						costIncrement*=2;
						break;
					default:
						break;
				}
			}
			
			cost += costIncrement;
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
			if(effects.isEmpty()
					|| effects.get(0).getPrimaryModifier()==null
					|| effects.get(0).getPrimaryModifier()==TFModifier.REMOVAL) {
				return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchBase();
			}
			
			if(effects.get(0).getPrimaryModifier()==TFModifier.ORIENTATION_GYNEPHILIC) {
				return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchGynephilic();
				
			} else if(effects.get(0).getPrimaryModifier()==TFModifier.ORIENTATION_AMBIPHILIC) {
				return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchAmbiphilic();
				
			} else if(effects.get(0).getPrimaryModifier()==TFModifier.ORIENTATION_ANDROPHILIC) {
				return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchAndrophilic();

			} else if(effects.get(0).getPrimaryModifier()==TFModifier.PERSONALITY_TRAIT_SPEECH_LISP
					|| effects.get(0).getPrimaryModifier()==TFModifier.PERSONALITY_TRAIT_SPEECH_STUTTER
					|| effects.get(0).getPrimaryModifier()==TFModifier.PERSONALITY_TRAIT_SPEECH_SLOVENLY) {
				if(effects.get(0).getPotency().isNegative()) {
					return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchSpeechRemove();
				} else {
					return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchSpeechAdd();
				}
			}
			
		}
		
		StringBuilder SVGImageSB = new StringBuilder();
		
		SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getRefinedBackgroundMap().get(ingredient.getEnchantmentEffect().getColour())+"</div>");
		
		String s = ((AbstractItemType) ingredient.getEnchantmentItemType(effects)).getSVGString();
		
		Colour colour = PresetColour.CLOTHING_BLUE_LIGHT;
		
		for(ItemEffect ie : effects) {
			if(ie.getPrimaryModifier() != null && ie.getPrimaryModifier() != TFModifier.NONE) {
				colour = ie.getPrimaryModifier().getColour();
				
				break;
			}
		}

		s = SvgUtil.colourReplacement(((AbstractItem)ingredient).getItemType().getId(), colour, null, null, s);
		
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
			if(effects.isEmpty()
					|| effects.get(0).getPrimaryModifier()==null
					|| effects.get(0).getPrimaryModifier()==TFModifier.REMOVAL) {
				return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchBase();
			}
			
			if(effects.get(0).getPrimaryModifier()==TFModifier.ORIENTATION_GYNEPHILIC) {
				return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchGynephilic();
				
			} else if(effects.get(0).getPrimaryModifier()==TFModifier.ORIENTATION_AMBIPHILIC) {
				return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchAmbiphilic();
				
			} else if(effects.get(0).getPrimaryModifier()==TFModifier.ORIENTATION_ANDROPHILIC) {
				return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchAndrophilic();
				
			} else if(effects.get(0).getPrimaryModifier()==TFModifier.PERSONALITY_TRAIT_SPEECH_LISP
					|| effects.get(0).getPrimaryModifier()==TFModifier.PERSONALITY_TRAIT_SPEECH_STUTTER
					|| effects.get(0).getPrimaryModifier()==TFModifier.PERSONALITY_TRAIT_SPEECH_SLOVENLY) {
				if(effects.get(0).getPotency().isNegative()) {
					return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchSpeechRemove();
				} else {
					return SVGImages.SVG_IMAGE_PROVIDER.getHypnoWatchSpeechAdd();
				}
			}
		}
		
		StringBuilder SVGImageSB = new StringBuilder();
		
		String importedColourString = SVGImages.SVG_IMAGE_PROVIDER.getRefinedBackgroundMap().get(importedColour);
		if(importedColourString==null || importedColourString.isEmpty() || importedColourString.equals("null")) {
			importedColourString = SVGImages.SVG_IMAGE_PROVIDER.getRefinedBackgroundMap().get(effects.get(0).getItemEffectType().getColour());
		}
		
		SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+importedColourString+"</div>");
		
		String s = item.getSVGString();
		
		Colour colour = PresetColour.CLOTHING_BLUE_LIGHT;
		
		for(ItemEffect ie : effects) {
			if(ie.getPrimaryModifier() != null && ie.getPrimaryModifier() != TFModifier.NONE) {
				colour = ie.getPrimaryModifier().getColour();
				
				break;
			}
		}
		
		s = s.replaceAll("#ff2a2a", colour.getShades()[0]);
		s = s.replaceAll("#ff5555|#f55", colour.getShades()[1]);
		s = s.replaceAll("#ff8080", colour.getShades()[2]);
		s = s.replaceAll("#ffaaaa|#faa", colour.getShades()[3]);
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
