package com.lilithsthrone.game.dialogue.utils;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.MapDisplay;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemEffect;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.7
 * @version 0.1.97
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
		
		
		// Create main enchanting block:
		
		inventorySB.append("<div class='enchanting-essence-main'>");
		
			inventorySB.append("<div class='enchanting-essence-inner-left'>");
//				inventorySB.append("<div class='crafting-item-background empty'></div>");
				
				inventorySB.append("<div class='enchanting-ingredient " + ingredient.getRarity().getName() + "'>"
						+ "<div class='enchanting-ingredient-content'>"+ingredient.getSVGString()+"</div>"
						+ "<div class='overlay' id='INGREDIENT_ENCHANTING'></div>"
						+ "<div class='enchanting-ingredient-count'><b>x" + Main.game.getPlayer().getItemCount((AbstractItem) ingredient)+ "</b></div>"
						+ "</div>");
				
				if(primaryMod != null) {
					inventorySB.append("<div class='enchanting-modifier " + primaryMod.getRarity().getName() + "'>"
							+ "<div class='enchanting-modifier-content'>"+primaryMod.getSVGString()+"</div>"
							+ "<div class='overlay' id='MOD_PRIMARY_ENCHANTING'></div>"
							+ "</div>");
					
				} else {
					inventorySB.append("<div class='item-background empty'>"
							+ "<div class='overlay' style='cursor:default;' id='MOD_PRIMARY_ENCHANTING'></div>"
							+ "</div>");
				}
				
				if(secondaryMod != null) {
					inventorySB.append("<div class='enchanting-modifier " + secondaryMod.getRarity().getName() + "'>"
							+ "<div class='enchanting-modifier-content'>"+secondaryMod.getSVGString()+"</div>"
							+ "<div class='overlay' id='MOD_SECONDARY_ENCHANTING'></div>"
							+ "</div>");
					
				} else {
					inventorySB.append("<div class='item-background empty'>"
							+ "<div class='overlay' style='cursor:default;' id='MOD_SECONDARY_ENCHANTING'></div>"
							+ "</div>");
				}
				
				ItemEffect effect = new ItemEffect(ingredient.getEnchantmentEffect(), primaryMod, secondaryMod, potency, limit);
				
				inventorySB.append(
						"<div class='enchanting-text' style='text-align: center; display:block; margin:0 auto; padding:8px 0 8px 0;'>"
							+ "<b style='color:"+potency.getColour().toWebHexString()+";'>"+potency.getName()+"</b>"
							+ "</br></br>"
							+ "<b>Cost:</b> "
							+ UtilText.formatAsEssences(effect.getCost(), "b", false)
						+ "</div>");

				if(effects.size() >= ingredient.getEnchantmentLimit()
						|| ingredient.getEnchantmentEffect().getEffectsDescription(primaryMod, secondaryMod, potency, limit, Main.game.getPlayer(), Main.game.getPlayer())==null
						|| ingredient.getEnchantmentEffect().getEffectsDescription(primaryMod, secondaryMod, potency, limit, Main.game.getPlayer(), Main.game.getPlayer()).isEmpty()) {
					inventorySB.append(
							"<div class='enchant-button-add disabled'>"
							+ "Add"
							+ "</div>");
					
				} else {
					inventorySB.append(
							"<div class='enchant-button-add' id='ENCHANT_ADD_BUTTON'>"
							+ "Add"
							+ "</div>");
					
				}
				
				inventorySB.append(
						"<div class='enchanting-text' style='text-align: center; display:block; margin:0 auto; padding:8px 0 8px 0;'>"
								+ "</br>"
								+ "<b>You have:</b> "
								+ UtilText.formatAsEssences(Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE), "b", false)
							+ "</div>");
				
			inventorySB.append("</div>");
			
			// Right panel:
			inventorySB.append("<div class='enchanting-essence-inner-right'>");
			
				// Primary mods:
				inventorySB.append("<div class='modifier-container'>");
				for (TFModifier tfMod : ingredient.getEnchantmentEffect().getPrimaryModifiers()) {
					inventorySB.append("<div class='modifier-icon " + tfMod.getRarity().getName() + "'>"
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
					inventorySB.append("<div class='modifier-icon " + tfMod.getRarity().getName() + "'>"
							+ "<div class='modifier-icon-content'>"+tfMod.getSVGString()+"</div>"
							+ "<div class='overlay' id='MOD_SECONDARY_"+tfMod.hashCode()+"'></div>"
							+ "</div>");
				}
				
				for (int i = 30; i > ingredient.getEnchantmentEffect().getSecondaryModifiers(primaryMod).size(); i--) {
					inventorySB.append("<div class='modifier-icon empty'></div>");
				}
				inventorySB.append("</div>");
				
				// Effect to be added:
				inventorySB.append(
						"<div class='enchanting-essence-effect'>"
							+ "<div class='enchanting-text'>");
				if(effect.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())!=null) {
					inventorySB.append("<b style='color:"+ingredient.getRelatedEssence().getColour().toWebHexString()+";'>Effect to be added:</b> ");
					for(String s : effect.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())) {
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
				inventorySB.append("<div class='enchanting-ingredient " + ((AbstractItemType) ingredient.getEnchantmentItemType(effects)).getRarity().getName() + "'>"
						+ "<div class='enchanting-ingredient-content'>"+EnchantingUtils.getSVGString(ingredient, effects)+"</div>"
						+ "<div class='overlay' id='OUTPUT_ENCHANTING'></div>"
						+ "</div>");
			inventorySB.append("</div>");
			
			inventorySB.append(
						"<div class='enchanting-essence-inner-right'>"
							+ "<div class='enchanting-text' style='text-align:center;'>"
								+ "<b>Effects (</b>"
									+ (effects.size()>=ingredient.getEnchantmentLimit()?"<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>":"<b>")+""
											+ effects.size()+"/"+ingredient.getEnchantmentLimit()+"</b><b>)</b></br>"
								+ "<b>"+Util.capitaliseSentence(EnchantingUtils.getPotionName(ingredient, effects))+"</b> | Cost to craft: "+ UtilText.formatAsEssences(EnchantingUtils.getCost(ingredient, effects), "b", false)
							+"</div>");
			
			// Effects:
			inventorySB.append("<div class='enchanting-text' style='text-align:center;padding-top:4px;'>");
			if(effects.isEmpty()) {
				inventorySB.append("<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No effects added</span>");
			} else {
				int i=0;
				for(ItemEffect ie : effects) {
					for(String s : ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())) {
						if(i>0)
							inventorySB.append("</br>");
						inventorySB.append(Util.capitaliseSentence(s));
						i++;
					}
				}
			}
			inventorySB.append("</div>");
		
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
					public void effects() {
						Main.game.setResponseTab(1);
						EnchantmentDialogue.resetEnchantmentVariables();
					}
				};
				
			// Ingredients:
			} else if (index == 1) {
				
				if(effects.size() >= ingredient.getEnchantmentLimit()) {
					return new Response("Add", "You cannot add any more effects!", null);
				}
				
				if(ingredient.getEnchantmentEffect().getEffectsDescription(primaryMod, secondaryMod, potency, limit, Main.game.getPlayer(), Main.game.getPlayer())==null
						|| ingredient.getEnchantmentEffect().getEffectsDescription(primaryMod, secondaryMod, potency, limit, Main.game.getPlayer(), Main.game.getPlayer()).isEmpty()) {
					return new Response("Add", "You cannot add an effect using these components!", null);
				}
				
				return new Response("Add", "Add the effect.", ENCHANTMENT_MENU){
					@Override
					public void effects() {
						effects.add(new ItemEffect(ingredient.getEnchantmentEffect(), primaryMod, secondaryMod, potency, limit));
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
							
							if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
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
							effects.addAll(EnchantmentDialogue.previousEffects);
						}
					};
					
				} else {
					return new Response("Reload effects", "You'll need to craft something first before you can reload its effects!", null);
				}
				
			} else if (index == 6) {
				if(ingredient.getEnchantmentEffect().getPotencyModifiers(primaryMod, secondaryMod).contains(getPreviousPotency(potency))) {
					return new Response("Reduce Potency", "Reduce the potency of the current effect.", ENCHANTMENT_MENU){
						@Override
						public void effects() {
							decrementPotency();
						}
					};
					
				} else {
					return new Response("Reduce Potency", "You can't reduce the potency!", null);
				}
				
			} else if (index == 7) {
				if(ingredient.getEnchantmentEffect().getPotencyModifiers(primaryMod, secondaryMod).contains(getNextPotency(potency))) {
					return new Response("Increase Potency", "Increase the potency of the current effect.", ENCHANTMENT_MENU){
						@Override
						public void effects() {
							incrementPotency();
						}
					};
					
				} else {
					return new Response("Increase Potency", "You can't increase the potency!", null);
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
	
	private static void incrementPotency() {
		TFPotency newPotency = getNextPotency(potency);
		if(newPotency!=null) {
			potency = newPotency;
		}
	}
	
	private static void decrementPotency() {
		TFPotency newPotency = getPreviousPotency(potency);
		if(newPotency!=null) {
			potency = newPotency;
		}
	}
	
	private static TFPotency getNextPotency(TFPotency potency) {
		switch(potency) {
			case MAJOR_DRAIN:
				return TFPotency.DRAIN;
			case DRAIN:
				return TFPotency.MINOR_DRAIN;
			case MINOR_DRAIN:
				return TFPotency.MINOR_BOOST;
			case MINOR_BOOST:
				return TFPotency.BOOST;
			case BOOST:
				return TFPotency.MAJOR_BOOST;
			case MAJOR_BOOST:
				return null;
		}
		return null;
	}
	
	private static TFPotency getPreviousPotency(TFPotency potency) {
		switch(potency) {
			case MAJOR_DRAIN:
				return null;
			case DRAIN:
				return TFPotency.MAJOR_DRAIN;
			case MINOR_DRAIN:
				return TFPotency.DRAIN;
			case MINOR_BOOST:
				return TFPotency.MINOR_DRAIN;
			case BOOST:
				return TFPotency.MINOR_BOOST;
			case MAJOR_BOOST:
				return TFPotency.BOOST;
		}
		return null;
	}
}
