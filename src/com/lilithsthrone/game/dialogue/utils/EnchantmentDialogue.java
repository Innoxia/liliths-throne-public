package com.lilithsthrone.game.dialogue.utils;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.MapDisplay;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemEffect;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.7
 * @version 0.2.0
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
	
	public static TFPotency potency = TFPotency.MINOR_BOOST;
	
	public static int limit = 0;
	

	private static String inventoryView() {
		inventorySB.setLength(0);

		ItemEffect effect = new ItemEffect(ingredient.getEnchantmentEffect(), primaryMod, secondaryMod, potency, limit);
		
		// Primary mods:
		inventorySB.append("<div class='container-half-width' style='padding-bottom:0;'>");
		for (TFModifier tfMod : ingredient.getEnchantmentEffect().getPrimaryModifiers()) {
			inventorySB.append("<div class='modifier-icon " + tfMod.getRarity().getName() + "' style='width:11.5%;'>"
					+ "<div class='modifier-icon-content'>"+tfMod.getSVGString()+"</div>"
					+ "<div class='overlay' id='MOD_PRIMARY_"+tfMod.hashCode()+"'></div>"
					+ "</div>");
		}
		for (int i = 32; i > ingredient.getEnchantmentEffect().getPrimaryModifiers().size(); i--) {
			inventorySB.append("<div class='modifier-icon empty' style='width:11.5%;'></div>");
		}
		
		inventorySB.append("<div class='container-full-width'>"
				+ "<div class='container-half-width' style='width:78%; margin:0 1%; text-align:center; line-height:100vh;'>"
				+ "<h5 style='margin:0; padding:0;'>Primary Modifier</h5>"
				+ "</div>"
				+ "<div class='container-half-width' style='width:18%; margin:0 1%;'>");
		if(primaryMod != null) {
			inventorySB.append("<div class='modifier-icon " + primaryMod.getRarity().getName() + "' style='width:100%; margin:0;'>"
					+ "<div class='modifier-icon-content'>"+primaryMod.getSVGString()+"</div>"
					+ "<div class='overlay' id='MOD_PRIMARY_ENCHANTING'></div>"
					+ "</div>");
			
		} else {
			inventorySB.append("<div class='modifier-icon empty' style='width:30%; margin:0 1%;'>"
					+ "<div class='overlay' style='cursor:default;' id='MOD_PRIMARY_ENCHANTING'></div>"
					+ "</div>");
		}
		inventorySB.append("</div></div>");
		
		inventorySB.append("</div>");
		
		
		// Secondary mods:
		inventorySB.append("<div class='container-half-width' style='padding-bottom:0;'>");
		for (TFModifier tfMod : ingredient.getEnchantmentEffect().getSecondaryModifiers(primaryMod)) {
			inventorySB.append("<div class='modifier-icon " + tfMod.getRarity().getName() + "' style='width:11.5%;'>"
					+ "<div class='modifier-icon-content'>"+tfMod.getSVGString()+"</div>"
					+ "<div class='overlay' id='MOD_SECONDARY_"+tfMod.hashCode()+"'></div>"
					+ "</div>");
		}
		for (int i = 32; i > ingredient.getEnchantmentEffect().getSecondaryModifiers(primaryMod).size(); i--) {
			inventorySB.append("<div class='modifier-icon empty' style='width:11.5%;'></div>");
		}
		
		inventorySB.append("<div class='container-full-width'>"
				+ "<div class='container-half-width' style='width:18%; margin:0 1%;'>");
		if(secondaryMod != null) {
			inventorySB.append("<div class='modifier-icon " + secondaryMod.getRarity().getName() + "' style='width:100%; margin:0;'>"
					+ "<div class='modifier-icon-content'>"+secondaryMod.getSVGString()+"</div>"
					+ "<div class='overlay' id='MOD_SECONDARY_ENCHANTING'></div>"
					+ "</div>");
			
		} else {
			inventorySB.append("<div class='modifier-icon empty' style='width:30%; margin:0 1%;'>"
					+ "<div class='overlay' style='cursor:default;' id='MOD_SECONDARY_ENCHANTING'></div>"
					+ "</div>");
		}
		inventorySB.append("</div>"
				+ "<div class='container-half-width' style='width:78%; margin:0 1%; text-align:center; line-height:100vh;'>"
					+ "<h5 style='margin:0; padding:0;'>Secondary Modifier</h5>"
				+ "</div>"
				+ "</div>");
		
		inventorySB.append("</div>");

		
		// Potency:
		inventorySB.append("<div class='container-full-width' style='text-align:center; padding:8px 0; margin-top:0;'>");
		
		for(TFPotency potency : TFPotency.values()) {
			inventorySB.append("<div class='normal-button"+(ingredient.getEnchantmentEffect().getPotencyModifiers(primaryMod, secondaryMod).contains(potency)?"":" disabled")+(EnchantmentDialogue.potency==potency?" selected":"")+"' id='POTENCY_"+potency+"'"
					+ " style='"+(EnchantmentDialogue.potency==potency?"color:"+potency.getColour().toWebHexString()+";":"")+" margin:0 1%; width:14%;'>"+potency.getName()+"</div>");
		}
		
		inventorySB.append("</div>");

		// Limits:
		int ingredientLimit = ingredient.getEnchantmentEffect().getLimits(primaryMod, secondaryMod);
		if(ingredientLimit!=0) {
			inventorySB.append(
					"<div class='container-full-width' style='text-align:center; padding:8px 0; margin-top:0;'>"
						+ "<div style='float:left; width:14.6%; margin:0 1%; padding:0;'>"
							+ "<div class='normal-button"+(limit == 0?" disabled":"")+"' id='LIMIT_MINIMUM' style='width:100%;'>Limit Min.</div>"
						+ "</div>"
						+ "<div style='float:left; width:14.6%; margin:0 1%; padding:0;'>"
							+ "<div class='normal-button"+(limit == 0?" disabled":"")+"' id='LIMIT_DECREASE_LARGE' style='width:100%;'>Limit--</div>"
						+ "</div>"
						+ "<div style='float:left; width:14.6%; margin:0 1%; padding:0;'>"
							+ "<div class='normal-button"+(limit == 0?" disabled":"")+"' id='LIMIT_DECREASE' style='width:100%;'>Limit-</div>"
						+ "</div>"
						+ "<div style='float:left; width:14.6%; margin:0 1%; padding:0;'>"
							+ "<div class='normal-button"+(limit == ingredientLimit?" disabled":"")+"' id='LIMIT_INCREASE' style='width:100%;'>Limit+</div>"
						+ "</div>"
						+ "<div style='float:left; width:14.6%; margin:0 1%; padding:0;'>"
							+ "<div class='normal-button"+(limit == ingredientLimit?" disabled":"")+"' id='LIMIT_INCREASE_LARGE' style='width:100%;'>Limit++</div>"
						+ "</div>"
						+ "<div style='float:left; width:14.6%; margin:0 1%; padding:0;'>"
							+ "<div class='normal-button"+(limit == ingredientLimit?" disabled":"")+"' id='LIMIT_MAXIMUM' style='width:100%;'>Limit Max.</div>"
						+ "</div>"
					+ "</div>");
		}
		
		// Effect:
		inventorySB.append("<div class='container-full-width' style='text-align:center; padding:8px 0; margin-top:0;'>");

			inventorySB.append("<div class='container-half-width' style='width:28%; margin:0 1%;'>"
									+ "<b style='color:"+ingredient.getRelatedEssence().getColour().toWebHexString()+";'>Effect to be added:</b>"
								+ "</div>");
		
			inventorySB.append("<div class='container-half-width' style='width:48%; margin:0 1%;'>");
				int i=0;
				if(effect.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())!=null) {
					if(i>0) {
						inventorySB.append("</br>");
					}
					for(String s : effect.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())) {
						inventorySB.append("<b>"+Util.capitaliseSentence(s)+"</b>");
					}
					i++;
				} else {
					inventorySB.append("<b>-</b>");
				}
			inventorySB.append("</div>");
			

			inventorySB.append("<div class='container-half-width' style='width:18%; margin:0 1%;'>");
				if(effects.size() >= ingredient.getEnchantmentLimit()
						|| ingredient.getEnchantmentEffect().getEffectsDescription(primaryMod, secondaryMod, potency, limit, Main.game.getPlayer(), Main.game.getPlayer())==null
						|| ingredient.getEnchantmentEffect().getEffectsDescription(primaryMod, secondaryMod, potency, limit, Main.game.getPlayer(), Main.game.getPlayer()).isEmpty()) {
					inventorySB.append(
							"<div class='normal-button disabled' style='width:100%; margin:auto 0;'>"
							+ "<b>Add</b> | "
							+ UtilText.formatAsEssencesUncoloured(EnchantingUtils.getModifierEffectCost(ingredient, effect), "b", false)
							+ "<div class='overlay no-pointer' id='ENCHANT_ADD_BUTTON_DISABLED'></div>"
							+ "</div>");
					
				} else {
					inventorySB.append(
							"<div class='normal-button' style='width:100%; margin:auto 0;'>"
							+ "<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Add</b> | "
							+ UtilText.formatAsEssences(EnchantingUtils.getModifierEffectCost(ingredient, effect), "b", false)
							+ "<div class='overlay' id='ENCHANT_ADD_BUTTON'></div>"
							+ "</div>");
					
				}
			inventorySB.append("</div>");
		
		inventorySB.append("</div>");
		
		
		// Item crafting:
		inventorySB.append("<div class='container-full-width' style='text-align:center; padding:8px 0; margin-top:0;'>");
		
		int count = 1;
		if(ingredient instanceof AbstractItem) {
			count = Main.game.getPlayer().getItemCount((AbstractItem) ingredient);
		} else if(ingredient instanceof AbstractClothing) {
			count = Main.game.getPlayer().getClothingCount((AbstractClothing) ingredient);
		} else if(ingredient instanceof AbstractWeapon) {
			count = Main.game.getPlayer().getWeaponCount((AbstractWeapon) ingredient);
		}
		
			inventorySB.append("<div class='container-half-width' style='width:18%; margin:0 1%; text-align:center;'>");
				inventorySB.append("<b>Input</b>"
						+ "<div class='enchanting-ingredient " + ingredient.getRarity().getName() + "'>"
						+ "<div class='enchanting-ingredient-content'>"+ingredient.getSVGString()+"</div>"
						+ "<div class='overlay' id='INGREDIENT_ENCHANTING'></div>"
						+ "<div class='enchanting-ingredient-count'><b>x" + count+ "</b></div>"
						+ "</div>");
			inventorySB.append("</div>");

			// Effects:
			inventorySB.append("<div class='container-half-width' style='width:58%; margin:0 1%;'>");
				inventorySB.append("<b>Effects (</b>"
									+ (effects.size()>=ingredient.getEnchantmentLimit()?"<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>":"<b>")+""
											+ effects.size()+"/"+ingredient.getEnchantmentLimit()+"</b><b>)</b></br>"
								+ "<b>"+Util.capitaliseSentence(EnchantingUtils.getPotionName(ingredient, effects))+"</b> | Cost: "+ UtilText.formatAsEssences(EnchantingUtils.getCost(ingredient, effects), "b", false));
			
				if(effects.isEmpty()) {
					inventorySB.append("</br><span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No effects added</span>");
				} else {
					i=0;
					for(ItemEffect ie : effects) {
						for(String s : ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())) {
							inventorySB.append(
									"<div class='container-full-width' style='background:"+RenderingEngine.getEntryBackgroundColour(i%2==0)+"; width:98%; margin:0 1%; padding:2px;'>"
										+Util.capitaliseSentence(s)
										+(ingredient.getEffects().size()>i && ingredient.getEffects().get(i).equals(ie)
												?"<div class='normal-button' style='width:64px; height:22px; line-height:22px; font-size:16px; margin:0; padding:0 0 0 4px; float:right; text-align:left;'>"
														+ "<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>X</b> "
																+ UtilText.formatAsEssences(EnchantingUtils.getModifierEffectCost(ingredient, effect), "b", false)
																+ "<div class='overlay' id='DELETE_EFFECT_"+i+"'></div></div>"
												:"<div class='normal-button' id='DELETE_EFFECT_"+i+"'"
														+ " style='width:22px; height:22px; line-height:22px; font-size:16px; margin:0; padding:0; float:right; color:"+Colour.GENERIC_BAD.toWebHexString()+";'><b>X</b></div>")
									+"</div>");
							i++;
						}
					}
				}
			inventorySB.append("</div>");
			

			inventorySB.append("<div class='container-half-width' style='width:18%; margin:0 1%; text-align:center;'>");
				inventorySB.append("<b>Output</b>"
						+ "<div class='enchanting-ingredient " + ingredient.getEnchantmentItemType(effects).getRarity().getName() + "'>"
						+ "<div class='enchanting-ingredient-content'>"+EnchantingUtils.getSVGString(ingredient, effects)+"</div>"
						+ "<div class='overlay' id='OUTPUT_ENCHANTING'></div>"
						+ "</div>");
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
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Stop enchanting.", InventoryDialogue.ITEM_INVENTORY){
					@Override
					public DialogueNodeOld getNextDialogue() {
						if(ingredient instanceof AbstractItem) {
							return InventoryDialogue.ITEM_INVENTORY;
							
						} else if(ingredient instanceof AbstractClothing) {
							if(Main.game.getPlayer().getClothingCurrentlyEquipped().contains(ingredient)) {
								return InventoryDialogue.CLOTHING_EQUIPPED;
							} else {
								return InventoryDialogue.CLOTHING_INVENTORY;
							}
							
						} else {
							if(Main.game.getPlayer().getMainWeapon().equals(ingredient) || Main.game.getPlayer().getOffhandWeapon().equals(ingredient)) {
								return InventoryDialogue.WEAPON_EQUIPPED;
							} else {
								return InventoryDialogue.WEAPON_INVENTORY;
							}
						}
					}
					@Override
					public void effects() {
						Main.game.setResponseTab(1);
						EnchantmentDialogue.resetEnchantmentVariables();
					}
				};
				
			// Ingredients:
			} else if (index == 1) {
				if(effects.equals(ingredient.getEffects()) || (effects.isEmpty() && ingredient instanceof AbstractItem)) {
					return new Response("Craft", "You need to add at least one effect before you can craft something!", null);
					
				} else if(canAffordCost(ingredient, effects)) {
					return new ResponseEffectsOnly("Craft", "Craft '"+EnchantingUtils.getPotionName(ingredient, effects)+"'."){
						@Override
						public void effects() {
							craftItem(ingredient, effects);
							
							if((previousIngredient instanceof AbstractItem?Main.game.getPlayer().hasItem((AbstractItem) previousIngredient):true)
									&& (previousIngredient instanceof AbstractClothing?Main.game.getPlayer().hasClothing((AbstractClothing) previousIngredient):true)
									&& (previousIngredient instanceof AbstractWeapon?Main.game.getPlayer().hasWeapon((AbstractWeapon) previousIngredient):true)) {
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
		return Main.game.getPlayer().getEssenceCount(ingredient.getRelatedEssence()) >= EnchantingUtils.getCost(ingredient, itemEffects);
	}
	
	public static void craftItem(AbstractCoreItem ingredient, List<ItemEffect> effects) {
		
		if(ingredient instanceof AbstractItem) {
			Main.game.getPlayer().removeItem((AbstractItem) ingredient);
			AbstractItem craftedItem = EnchantingUtils.craftItem(ingredient, effects);
			Main.game.getPlayer().addItem(craftedItem, false, true);
			
		} else if(ingredient instanceof AbstractClothing) {
			Main.game.getPlayer().removeClothing((AbstractClothing) ingredient);
			AbstractClothing craftedClothing = EnchantingUtils.craftClothing(ingredient, effects);
			Main.game.getPlayer().addClothing(craftedClothing, false);
			
		} else if(ingredient instanceof AbstractWeapon) {
			Main.game.getPlayer().removeWeapon((AbstractWeapon) ingredient);
		}
		
		Main.game.getPlayer().incrementEssenceCount(ingredient.getRelatedEssence(), -EnchantingUtils.getCost(ingredient, effects), false);
		
		previousIngredient = ingredient;
		previousPrimaryMod = primaryMod;
		previousSecondaryMod = secondaryMod;
		previousEffects.clear();
		previousEffects.addAll(EnchantmentDialogue.effects);
		
		resetEnchantmentVariables();
		EnchantmentDialogue.effects.clear();
	}
	
	public static void resetEnchantmentVariables() {
		EnchantmentDialogue.ingredient = null;
		EnchantmentDialogue.primaryMod = TFModifier.NONE;
		EnchantmentDialogue.secondaryMod = TFModifier.NONE;
		EnchantmentDialogue.potency = TFPotency.MINOR_BOOST;
		EnchantmentDialogue.limit = 0;
	}
	
	public static void initModifiers(AbstractCoreItem ingredient) {
		EnchantmentDialogue.ingredient = ingredient;
		
		if(ingredient instanceof AbstractClothing) {
			EnchantmentDialogue.effects = new ArrayList<>(ingredient.getEffects());
		} else {
			EnchantmentDialogue.effects = new ArrayList<>();
		}
		
		if(!EnchantmentDialogue.ingredient.getEnchantmentEffect().getPrimaryModifiers().contains(EnchantmentDialogue.primaryMod)) {
			EnchantmentDialogue.primaryMod = EnchantmentDialogue.ingredient.getEnchantmentEffect().getPrimaryModifiers().get(0);
		}
		if(!EnchantmentDialogue.ingredient.getEnchantmentEffect().getSecondaryModifiers(EnchantmentDialogue.primaryMod).contains(EnchantmentDialogue.secondaryMod)) {
			EnchantmentDialogue.secondaryMod = EnchantmentDialogue.ingredient.getEnchantmentEffect().getSecondaryModifiers(EnchantmentDialogue.primaryMod).get(0);
		}
		if(!EnchantmentDialogue.ingredient.getEnchantmentEffect().getPotencyModifiers(EnchantmentDialogue.primaryMod, EnchantmentDialogue.secondaryMod).contains(EnchantmentDialogue.potency)) {
			EnchantmentDialogue.potency = TFPotency.MINOR_BOOST;
		}
		if(EnchantmentDialogue.limit <= EnchantmentDialogue.ingredient.getEnchantmentEffect().getLimits(EnchantmentDialogue.primaryMod, EnchantmentDialogue.secondaryMod)) {
			EnchantmentDialogue.limit = EnchantmentDialogue.ingredient.getEnchantmentEffect().getLimits(EnchantmentDialogue.primaryMod, EnchantmentDialogue.secondaryMod);
		}
	}
}
