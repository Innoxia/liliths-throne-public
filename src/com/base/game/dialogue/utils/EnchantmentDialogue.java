package com.base.game.dialogue.utils;

import java.util.ArrayList;
import java.util.List;

import com.base.game.character.QuestLine;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.MapDisplay;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseEffectsOnly;
import com.base.game.inventory.AbstractCoreItem;
import com.base.game.inventory.Rarity;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.inventory.enchanting.EnchantingUtils;
import com.base.game.inventory.enchanting.TFEssence;
import com.base.game.inventory.enchanting.TFModifier;
import com.base.game.inventory.item.AbstractItem;
import com.base.game.inventory.item.ItemEffect;
import com.base.game.inventory.weapon.AbstractWeapon;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;

/**
 * @since 0.1.7
 * @version 0.1.8
 * @author Innoxia
 */
public class EnchantmentDialogue {
	
	private static StringBuilder inventorySB = new StringBuilder("");
	
	public static AbstractCoreItem ingredient = null, previousIngredient = null;
	
	public static List<ItemEffect> effects = new ArrayList<>(),  previousEffects = new ArrayList<>();
	
	public static TFModifier
			primaryMod = TFModifier.NONE,
			secondaryMod = TFModifier.NONE,
			previousPrimaryMod = TFModifier.NONE,
			previousSecondaryMod = TFModifier.NONE;
	
	private static String inventoryView() {
		inventorySB.setLength(0);
		
		// Display essences at top of page:
		inventorySB.append("<div class='enchanting-essence-title'>"
				+ "<div class='enchanting-text'>"
//				+ "Owned essences: "
				);
		for(TFEssence essence : TFEssence.values()) {
			inventorySB.append(
					"<div style='width:28px; display:inline-block; margin:0 4px 0 4px;'>"
						+ "<div class='essence-inline"
									+ (essence.getRarity() == Rarity.COMMON ? " common" : "")
									+ (essence.getRarity() == Rarity.UNCOMMON ? " uncommon" : "")
									+ (essence.getRarity() == Rarity.RARE ? " rare" : "")
									+ (essence.getRarity() == Rarity.EPIC ? " epic" : "")
									+ (essence.getRarity() == Rarity.LEGENDARY ? " legendary" : "")
									+ (essence.getRarity() == Rarity.JINXED ? " jinxed" : "") + "'>"
							+ essence.getSVGString()
							+ "<div class='overlay no-pointer' id='ESSENCE_"+essence.hashCode()+"'></div>"
						+ "</div>"
						+ " <div style='display:inline-block; height:16px; vertical-align: middle;'>"
							+ "<b style='color:"+essence.getColour()+";'>"+Main.game.getPlayer().getEssenceCount(essence)+"</b>"
						+ "</div>"
					+ "</div>"
					);
		}
		inventorySB.append("</div></div>");
		
		
		// Create main enchanting block:
		
		inventorySB.append("<div class='enchanting-essence-main'>");
		
			inventorySB.append("<div class='enchanting-essence-inner-left'>");
//				inventorySB.append("<div class='crafting-item-background empty'></div>");
				
				inventorySB.append("<div class='enchanting-ingredient"
						+ (ingredient.getRarity() == Rarity.COMMON ? " common" : "")
						+ (ingredient.getRarity() == Rarity.UNCOMMON ? " uncommon" : "")
						+ (ingredient.getRarity() == Rarity.RARE ? " rare" : "")
						+ (ingredient.getRarity() == Rarity.EPIC ? " epic" : "")
						+ (ingredient.getRarity() == Rarity.LEGENDARY ? " legendary" : "")
						+ (ingredient.getRarity() == Rarity.JINXED ? " jinxed" : "") + "'>"
						+ "<div class='enchanting-ingredient-content'>"+ingredient.getSVGString()+"</div>"
						+ "<div class='overlay' id='INGREDIENT_ENCHANTING'></div>"
						+ "<div class='enchanting-ingredient-count'><b>x" + Main.game.getPlayer().getItemCount((AbstractItem) ingredient)+ "</b></div>"
						+ "</div>");
				
				if(primaryMod != null) {
					inventorySB.append("<div class='enchanting-modifier"
							+ (primaryMod.getRarity() == Rarity.COMMON ? " common" : "")
							+ (primaryMod.getRarity() == Rarity.UNCOMMON ? " uncommon" : "")
							+ (primaryMod.getRarity() == Rarity.RARE ? " rare" : "")
							+ (primaryMod.getRarity() == Rarity.EPIC ? " epic" : "")
							+ (primaryMod.getRarity() == Rarity.LEGENDARY ? " legendary" : "")
							+ (primaryMod.getRarity() == Rarity.JINXED ? " jinxed" : "") + "'>"
							+ "<div class='enchanting-modifier-content'>"+primaryMod.getSVGString()+"</div>"
							+ "<div class='overlay' id='MOD_PRIMARY_ENCHANTING'></div>"
							+ "</div>");
					
				} else {
					inventorySB.append("<div class='item-background empty'>"
							+ "<div class='overlay' style='cursor:default;' id='MOD_PRIMARY_ENCHANTING'></div>"
							+ "</div>");
				}
				
				if(secondaryMod != null) {
					inventorySB.append("<div class='enchanting-modifier"
							+ (secondaryMod.getRarity() == Rarity.COMMON ? " common" : "")
							+ (secondaryMod.getRarity() == Rarity.UNCOMMON ? " uncommon" : "")
							+ (secondaryMod.getRarity() == Rarity.RARE ? " rare" : "")
							+ (secondaryMod.getRarity() == Rarity.EPIC ? " epic" : "")
							+ (secondaryMod.getRarity() == Rarity.LEGENDARY ? " legendary" : "")
							+ (secondaryMod.getRarity() == Rarity.JINXED ? " jinxed" : "") + "'>"
							+ "<div class='enchanting-modifier-content'>"+secondaryMod.getSVGString()+"</div>"
							+ "<div class='overlay' id='MOD_SECONDARY_ENCHANTING'></div>"
							+ "</div>");
					
				} else {
					inventorySB.append("<div class='item-background empty'>"
							+ "<div class='overlay' style='cursor:default;' id='MOD_SECONDARY_ENCHANTING'></div>"
							+ "</div>");
				}
				
				ItemEffect effect = new ItemEffect(ingredient.getEnchantmentEffect(), primaryMod, secondaryMod);
				
				inventorySB.append(
						"<div class='enchanting-text' style='text-align: center; display:block; margin:0 auto; padding:8px 0 8px 0;'>"
						+ "<b>Effect cost:</b></br>"
						+ "<div class='essence-inline"
									+ (ingredient.getRelatedEssence().getRarity() == Rarity.COMMON ? " common" : "")
									+ (ingredient.getRelatedEssence().getRarity() == Rarity.UNCOMMON ? " uncommon" : "")
									+ (ingredient.getRelatedEssence().getRarity() == Rarity.RARE ? " rare" : "")
									+ (ingredient.getRelatedEssence().getRarity() == Rarity.EPIC ? " epic" : "")
									+ (ingredient.getRelatedEssence().getRarity() == Rarity.LEGENDARY ? " legendary" : "")
									+ (ingredient.getRelatedEssence().getRarity() == Rarity.JINXED ? " jinxed" : "") + "'>"
							+ ingredient.getRelatedEssence().getSVGString()
							+ "<div class='overlay no-pointer' id='ESSENCE_COST_"+ingredient.getRelatedEssence().hashCode()+"'></div>"
						+ "</div>"
						+ " <div style='display:inline-block; vertical-align: middle;'>"
							+ "<b style='color:"+ingredient.getRelatedEssence().getColour()+";'>"+effect.getCost()+"</b>"
						+ "</div> "
						+ "</div>");

				inventorySB.append(
						"<div class='enchant-button-add' id='ENCHANT_ADD_BUTTON'>"
						+ "Add"
						+ "</div>");
				
			inventorySB.append("</div>");
			
			// Right panel:
			inventorySB.append("<div class='enchanting-essence-inner-right'>");
			
				// Primary mods:
				inventorySB.append("<div class='modifier-container'>");
				for (TFModifier tfMod : ingredient.getEnchantmentEffect().getPrimaryModifiers()) {
					inventorySB.append("<div class='modifier-icon"
							+ (tfMod.getRarity() == Rarity.COMMON ? " common" : "")
							+ (tfMod.getRarity() == Rarity.UNCOMMON ? " uncommon" : "")
							+ (tfMod.getRarity() == Rarity.RARE ? " rare" : "")
							+ (tfMod.getRarity() == Rarity.EPIC ? " epic" : "")
							+ (tfMod.getRarity() == Rarity.LEGENDARY ? " legendary" : "")
							+ (tfMod.getRarity() == Rarity.JINXED ? " jinxed" : "") + "'>"
							+ "<div class='modifier-icon-content'>"+tfMod.getSVGString()+"</div>"
							+ "<div class='overlay' id='MOD_PRIMARY_"+tfMod.hashCode()+"'></div>"
							+ "</div>");
				}
				
				for (int i = 20; i > ingredient.getEnchantmentEffect().getPrimaryModifiers().size(); i--) {
					inventorySB.append("<div class='modifier-icon empty'></div>");
				}
				inventorySB.append("</div>");
				
				// Secondary mods:
				inventorySB.append("<div class='modifier-container'>");
				for (TFModifier tfMod : ingredient.getEnchantmentEffect().getSecondaryModifiers(primaryMod)) {
					inventorySB.append("<div class='modifier-icon"
							+ (tfMod.getRarity() == Rarity.COMMON ? " common" : "")
							+ (tfMod.getRarity() == Rarity.UNCOMMON ? " uncommon" : "")
							+ (tfMod.getRarity() == Rarity.RARE ? " rare" : "")
							+ (tfMod.getRarity() == Rarity.EPIC ? " epic" : "")
							+ (tfMod.getRarity() == Rarity.LEGENDARY ? " legendary" : "")
							+ (tfMod.getRarity() == Rarity.JINXED ? " jinxed" : "") + "'>"
							+ "<div class='modifier-icon-content'>"+tfMod.getSVGString()+"</div>"
							+ "<div class='overlay' id='MOD_SECONDARY_"+tfMod.hashCode()+"'></div>"
							+ "</div>");
				}
				
				for (int i = 20; i > ingredient.getEnchantmentEffect().getSecondaryModifiers(primaryMod).size(); i--) {
					inventorySB.append("<div class='modifier-icon empty'></div>");
				}
				inventorySB.append("</div>");
				
				// Effect to be added:
				inventorySB.append(
						"<div class='enchanting-essence-effect'>"
							+ "<div class='enchanting-text'>");
				if(effect.getEffectsDescription()!=null) {
					inventorySB.append("<b style='color:"+ingredient.getRelatedEssence().getColour()+";'>Effect to be added:</b> ");
					for(String s : effect.getEffectsDescription()) {
						inventorySB.append("</br><b>"+Util.capitaliseSentence(s)+"</b>");
					}
				} else {
					inventorySB.append("<b>-</b>");
				}
				inventorySB.append("</div></div>");
				
			inventorySB.append("</div>");
		
		inventorySB.append("</div>");
		
		
		// Output:
		inventorySB.append("<div class='enchanting-essence-main'>");

			inventorySB.append("<div class='enchanting-essence-inner-left'>");
				inventorySB.append("<div class='enchanting-ingredient"
						+ (ingredient.getEnchantmentItemType().getRarity() == Rarity.COMMON ? " common" : "")
						+ (ingredient.getEnchantmentItemType().getRarity() == Rarity.UNCOMMON ? " uncommon" : "")
						+ (ingredient.getEnchantmentItemType().getRarity() == Rarity.RARE ? " rare" : "")
						+ (ingredient.getEnchantmentItemType().getRarity() == Rarity.EPIC ? " epic" : "")
						+ (ingredient.getEnchantmentItemType().getRarity() == Rarity.LEGENDARY ? " legendary" : "")
						+ (ingredient.getEnchantmentItemType().getRarity() == Rarity.JINXED ? " jinxed" : "") + "'>"
						+ "<div class='enchanting-ingredient-content'>"+EnchantingUtils.getSVGString(ingredient, effects)+"</div>"
						+ "<div class='overlay' id='OUTPUT_ENCHANTING'></div>"
						+ "</div>");
			inventorySB.append("</div>");
			
			inventorySB.append(
						"<div class='enchanting-essence-inner-right'>"
							+ "<div class='enchanting-text' style='text-align:center;'>"
								+ "<b>"+Util.capitaliseSentence(EnchantingUtils.getPotionName(ingredient, effects))+"</b> | Cost to craft: ");
			inventorySB.append("<div class='essence-inline"
								+ (ingredient.getRelatedEssence().getRarity() == Rarity.COMMON ? " common" : "")
								+ (ingredient.getRelatedEssence().getRarity() == Rarity.UNCOMMON ? " uncommon" : "")
								+ (ingredient.getRelatedEssence().getRarity() == Rarity.RARE ? " rare" : "")
								+ (ingredient.getRelatedEssence().getRarity() == Rarity.EPIC ? " epic" : "")
								+ (ingredient.getRelatedEssence().getRarity() == Rarity.LEGENDARY ? " legendary" : "")
								+ (ingredient.getRelatedEssence().getRarity() == Rarity.JINXED ? " jinxed" : "") + "'>"
						+ ingredient.getRelatedEssence().getSVGString()
						+ "<div class='overlay no-pointer' id='ESSENCE_COST_"+ingredient.getRelatedEssence().hashCode()+"'></div>"
					+ "</div>"
					+ " <span style='color:"+ingredient.getRelatedEssence().getColour()+";'>"+EnchantingUtils.getCost(ingredient, effects)+"</span>");
			inventorySB.append("</div>");
			
			// Effects:
			inventorySB.append("<div class='enchanting-text' style='text-align:center;padding-top:4px;'>");
			if(effects.isEmpty()) {
				inventorySB.append("<span style='color:"+Colour.TEXT_GREY+";'>No effects added</span>");
			} else {
				int i=0;
				for(ItemEffect ie : effects) {
					for(String s : ie.getEffectsDescription()) {
						if(i>0)
							inventorySB.append("</br>");
						inventorySB.append(Util.capitaliseSentence(s));
						i++;
					}
				}
			}
			inventorySB.append("</div>");
		
		inventorySB.append("</div>");
		

		return inventorySB.toString();
	}
	
	public static final DialogueNodeOld ENCHANTMENT_MENU = new DialogueNodeOld("Enchantments", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Enchanting";
		}

		@Override
		public String getHeaderContent() {

			return inventoryView();
		}

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Stop enchanting.", InventoryDialogue.ITEM_INVENTORY){
					@Override
					public void effects() {
						ingredient = null;
						primaryMod = TFModifier.NONE;
						secondaryMod = TFModifier.NONE;
					}
				};
				
			// Ingredients:
			} else if (index == 1) {
				
				if(ingredient.getEnchantmentEffect().getEffectsDescription(primaryMod, secondaryMod)==null) {
					return new Response("Add", "You cannot add an effect using these components!", null);
				}
				
				return new Response("Add", "Add the effect.", ENCHANTMENT_MENU){
					@Override
					public void effects() {
						effects.add(new ItemEffect(ingredient.getEnchantmentEffect(), primaryMod, secondaryMod));
					}
				};
				
			} else if (index == 2) {
				
				if(effects.isEmpty()) {
					return new Response("Undo", "There are no effects to undo!", null);
				}
				
				return new Response("Undo", "Remove the last effect you added.", ENCHANTMENT_MENU){
					@Override
					public void effects() {
						effects.remove(effects.size()-1);
					}
				};
				
			} else if (index == 4) {
				
				if(effects.isEmpty()) {
					return new Response("Craft", "You need to add at least one effect before you can craft something!", null);
					
				} else if(canAffordCost(ingredient, effects)) {
					return new ResponseEffectsOnly("Craft", "Craft '"+EnchantingUtils.getPotionName(ingredient, effects)+"'."){
						@Override
						public void effects() {
							craftItem(ingredient, effects);
							
							if(!Main.game.getPlayer().isSideQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY));
							}
							
							if(Main.game.getPlayer().hasItem((AbstractItem) previousIngredient)) {
								ingredient = previousIngredient;
								Main.game.setContent(new Response("", "", ENCHANTMENT_MENU));
							} else {
								Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
							}
							
						}
					};
					
				} else {
					return new Response("Craft", "You don't have enough essences to craft this!", null);
				}
				
			} else if (index == 5) {
				if(previousIngredient==ingredient && previousEffects!=null) {
					return new Response("Reload effects", "Reload the effects that you used in your last craft.", ENCHANTMENT_MENU){
						@Override
						public void effects() {
							effects.clear();
							for(ItemEffect ie : EnchantmentDialogue.previousEffects)
								effects.add(ie);
						}
					};
					
				} else {
					return new Response("Reload effects", "You'll need to craft something first before you can reload its effects!", null);
				}
				
			} else {
				return null;
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.INVENTORY;
		}
	};
	
	public static boolean canAffordCost(AbstractCoreItem ingredient, List<ItemEffect> itemEffects) {
		if(Main.game.getPlayer().getEssenceCount(ingredient.getRelatedEssence()) >= EnchantingUtils.getCost(ingredient, itemEffects)) {
			return true;
			
		} else {
			return false;
		}
	}
	
	public static void craftItem(AbstractCoreItem ingredient, List<ItemEffect> effects) {
		
		if(ingredient instanceof AbstractItem) {
			Main.game.getPlayer().removeItem((AbstractItem) ingredient);
			
		} else if(ingredient instanceof AbstractClothing) {
			Main.game.getPlayer().removeClothing((AbstractClothing) ingredient);
			
		} else if(ingredient instanceof AbstractWeapon) {
			Main.game.getPlayer().removeWeapon((AbstractWeapon) ingredient);
		}
		
		Main.game.getPlayer().incrementEssenceCount(ingredient.getRelatedEssence(), -EnchantingUtils.getCost(ingredient, effects));
		
		AbstractItem craftedItem = EnchantingUtils.craftItem(ingredient, effects);
		
		Main.game.getPlayer().addItem(craftedItem, false);
		
		previousIngredient = ingredient;
		previousPrimaryMod = primaryMod;
		previousSecondaryMod = secondaryMod;
		previousEffects.clear();
		for(ItemEffect ie : EnchantmentDialogue.effects)
			previousEffects.add(ie);
		
		EnchantmentDialogue.ingredient = null;
		EnchantmentDialogue.primaryMod = TFModifier.NONE;
		EnchantmentDialogue.secondaryMod = TFModifier.NONE;
		EnchantmentDialogue.effects.clear();
	}
	
}
