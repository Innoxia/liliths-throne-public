package com.lilithsthrone.game.dialogue.utils;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntry;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.LoadedEnchantment;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.7
 * @version 0.3.5.1
 * @author Innoxia
 */
public class EnchantmentDialogue {
	
	private static StringBuilder inventorySB = new StringBuilder("");
	
	private static InventoryInteraction interactionInit;
	
	private static AbstractCoreItem ingredient = null;
	private static AbstractCoreItem previousIngredient = null;
	
	private static List<ItemEffect> effects = new ArrayList<>();
	private static List<ItemEffect> previousEffects = new ArrayList<>();
	
	private static InventorySlot tattooSlot;
	private static GameCharacter tattooBearer;
	
	private static TFModifier primaryMod = TFModifier.NONE;
	private static TFModifier secondaryMod = TFModifier.NONE;
	public static TFModifier previousPrimaryMod = TFModifier.NONE;
	public static TFModifier previousSecondaryMod = TFModifier.NONE;
	
	private static TFPotency potency = TFPotency.MINOR_BOOST;
	
	private static int limit = 0;
	
	private static Map<String, LoadedEnchantment> loadedEnchantmentsMap;
	
	private static String outputName = "";
	
	private static boolean isEquipped = false;
	private static GameCharacter isEquippedTo = null;
	private static InventorySlot isEquippedIn = null;
	
	private static String inventoryView() {
		inventorySB.setLength(0);
		
		ItemEffect effect = getCurrentEffect();
		
		int displaySlots = Math.max(32, 8*(int)Math.ceil(Math.max(ingredient.getEnchantmentEffect().getPrimaryModifiers().size(), ingredient.getEnchantmentEffect().getSecondaryModifiers(ingredient, primaryMod).size())/8f));
		
		// Primary mods:
		inventorySB.append("<div class='container-half-width' style='padding-bottom:0;'>");
		for (TFModifier tfMod : ingredient.getEnchantmentEffect().getPrimaryModifiers()) {
			inventorySB.append("<div class='modifier-icon' style='width:11.5%; background-color:"+tfMod.getRarity().getBackgroundColour().toWebHexString()+";'>"
					+ "<div class='modifier-icon-content'>"+tfMod.getSVGString()+"</div>"
					+ "<div class='overlay' id='MOD_PRIMARY_"+tfMod.hashCode()+"'></div>"
					+ "</div>");
		}
		for (int i = displaySlots; i > ingredient.getEnchantmentEffect().getPrimaryModifiers().size(); i--) {
			inventorySB.append("<div class='modifier-icon empty' style='width:11.5%;'></div>");
		}
		
		inventorySB.append("<div class='container-full-width'>"
				+ "<div class='container-half-width' style='width:78%; margin:0 1%; text-align:center; line-height:100vh;'>"
				+ "<h5 style='margin:0; padding:0;'>Primary Modifier</h5>"
				+ "</div>"
				+ "<div class='container-half-width' style='width:18%; margin:0 1%;'>");
		if(primaryMod != null) {
			inventorySB.append("<div class='modifier-icon' style='width:100%; margin:0;background-color:"+primaryMod.getRarity().getBackgroundColour().toWebHexString()+";'>"
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
		for (TFModifier tfMod : ingredient.getEnchantmentEffect().getSecondaryModifiers(ingredient, primaryMod)) {
			inventorySB.append("<div class='modifier-icon' style='width:11.5%; background-color:"+tfMod.getRarity().getBackgroundColour().toWebHexString()+";'>"
					+ "<div class='modifier-icon-content'>"+tfMod.getSVGString()+"</div>"
					+ "<div class='overlay' id='MOD_SECONDARY_"+tfMod.hashCode()+"'></div>"
					+ "</div>");
		}
		for (int i = displaySlots; i > ingredient.getEnchantmentEffect().getSecondaryModifiers(ingredient, primaryMod).size(); i--) {
			inventorySB.append("<div class='modifier-icon empty' style='width:11.5%;'></div>");
		}
		
		inventorySB.append("<div class='container-full-width'>"
				+ "<div class='container-half-width' style='width:18%; margin:0 1%;'>");
		if(secondaryMod != null) {
			inventorySB.append("<div class='modifier-icon' style='width:100%; margin:0; background-color:"+secondaryMod.getRarity().getBackgroundColour().toWebHexString()+";'>"
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
									+ "<b style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>Effect to be added:</b>"
								+ "</div>");
		
			inventorySB.append("<div class='container-half-width' style='width:48%; margin:0 1%;'>");
				if(effect.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())!=null) {
					int i=0;
					for(String s : effect.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())) {
						if(i>0) {
							inventorySB.append("<br/>");
						}
						inventorySB.append("<b>"+Util.capitaliseSentence(s)+"</b>");
						i++;
					}
				} else {
					inventorySB.append("<b>-</b>");
				}

				// Append enchantment capacity cost for weapons/clothing/tattoos
				if(Main.game.isEnchantmentCapacityEnabled()) {
					if((ingredient instanceof AbstractClothing)
							|| (ingredient instanceof AbstractWeapon)
							|| (ingredient instanceof Tattoo)) {
						
						
						if(effect.getItemEffectType()==ItemEffectType.CLOTHING
								|| effect.getItemEffectType()==ItemEffectType.WEAPON
								|| effect.getItemEffectType()==ItemEffectType.TATTOO) {
							if(effect.getPrimaryModifier()==TFModifier.CLOTHING_ATTRIBUTE || effect.getPrimaryModifier()==TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
								int cost = Math.max(0, effect.getPotency().getClothingBonusValue());
								if(effect.getSecondaryModifier()==TFModifier.FERTILITY
										|| effect.getSecondaryModifier()==TFModifier.VIRILITY) {
									cost = 0;
								} else if(effect.getSecondaryModifier()==TFModifier.CORRUPTION) {
									if(effect.getPotency().isNegative()) {
										cost = Math.abs(effect.getPotency().getClothingBonusValue());
									} else {
										cost = 0;
									}
								}
								inventorySB.append("<br/>"
										+ (cost>0
												?"[style.colourEnchantment("+Util.capitaliseSentence(Attribute.ENCHANTMENT_LIMIT.getName())+" cost)]: [style.boldBad("+cost+")]"
												:Util.capitaliseSentence(Attribute.ENCHANTMENT_LIMIT.getName())+" cost: [style.boldDisabled(0)]"));
							}
						}
					}
				}
			inventorySB.append("</div>");
			

			inventorySB.append("<div class='container-half-width' style='width:18%; margin:0 1%;'>");
				if(effects.size() >= ingredient.getEnchantmentLimit()
						|| ingredient.getEnchantmentEffect().getEffectsDescription(primaryMod, secondaryMod, potency, limit, Main.game.getPlayer(), Main.game.getPlayer())==null
						|| ingredient.getEnchantmentEffect().getEffectsDescription(primaryMod, secondaryMod, potency, limit, Main.game.getPlayer(), Main.game.getPlayer()).isEmpty()
						|| getEnchantmentEffectBlockedReason(effect)!=null) {
					inventorySB.append(
							"<div class='normal-button disabled' style='width:100%; margin:auto 0;'>"
							+ "<b>Add</b> | "
							+ (ingredient instanceof Tattoo
									?UtilText.formatAsMoneyUncoloured(EnchantingUtils.getModifierEffectCost(true, ingredient, effect)*EnchantingUtils.FLAME_COST_MODIFER, "b")
									:UtilText.formatAsEssencesUncoloured(EnchantingUtils.getModifierEffectCost(true, ingredient, effect), "b", false))
							+ "<div class='overlay no-pointer' id='ENCHANT_ADD_BUTTON_DISABLED'></div>"
							+ "</div>");
					
				} else {
					inventorySB.append(
							"<div class='normal-button' style='width:100%; margin:auto 0;'>"
							+ "<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Add</b> | "
									+ (ingredient instanceof Tattoo
											?UtilText.formatAsMoney(EnchantingUtils.getModifierEffectCost(true, ingredient, effect)*EnchantingUtils.FLAME_COST_MODIFER, "b")
											:UtilText.formatAsEssences(EnchantingUtils.getModifierEffectCost(true, ingredient, effect), "b", false))
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
						+ "<div class='enchanting-ingredient' style='background-color:"+ingredient.getRarity().getBackgroundColour().toWebHexString()+";'>"
						+ "<div class='enchanting-ingredient-content'>"+ingredient.getSVGString()+"</div>"
						+ "<div class='overlay' id='INGREDIENT_ENCHANTING'  style='cursor:default;'></div>"
						+ "<div class='enchanting-ingredient-count'><b>x" + count+ "</b></div>"
						+ "</div>");
			inventorySB.append("</div>");

			// Effects:
			inventorySB.append("<div class='container-half-width' style='width:58%; margin:0 1%;'>");
				inventorySB.append("<b>Effects (</b>"
									+ (effects.size()>=ingredient.getEnchantmentLimit()?"<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>":"<b>")+""
											+ effects.size()+"/"+ingredient.getEnchantmentLimit()+"</b><b>)</b> | Cost: "
												+ (ingredient instanceof Tattoo
														?UtilText.formatAsMoney(EnchantingUtils.getCost(ingredient, effects)*EnchantingUtils.FLAME_COST_MODIFER, "b")
														:UtilText.formatAsEssences(EnchantingUtils.getCost(ingredient, effects), "b", false))
												+"<br/>"
											+"<form style='padding:0; margin:0 0 4px 0; text-align:center;'><input type='text' id='output_name' value='" +UtilText.parseForHTMLDisplay(outputName)+"' style='padding:0;margin:0;width:80%;'></form>"
								);
			
				if(effects.isEmpty()) {
					inventorySB.append("<br/><span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>No effects added</span>");
				} else {
					int cost = 0;
					
					for(int it=0; it<effects.size(); it++) {
						ItemEffect ie = effects.get(it);
						
						if(ie.getItemEffectType()==ItemEffectType.CLOTHING
								|| ie.getItemEffectType()==ItemEffectType.WEAPON
								|| ie.getItemEffectType()==ItemEffectType.TATTOO) {
							if(ie.getPrimaryModifier()==TFModifier.CLOTHING_ATTRIBUTE
									|| ie.getPrimaryModifier()==TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
								if(ie.getSecondaryModifier()==TFModifier.FERTILITY
										|| ie.getSecondaryModifier()==TFModifier.VIRILITY) {
									cost += 0;
								} else if(ie.getSecondaryModifier()==TFModifier.CORRUPTION) {
									if(ie.getPotency().isNegative()) {
										cost += Math.abs(ie.getPotency().getClothingBonusValue());
									}
								} else {
									cost += Math.max(0, ie.getPotency().getClothingBonusValue());
								}
							}
						}
						
						int i=0;
						for(String s : ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())) {
							inventorySB.append(
									"<div class='container-full-width'"
											+ " style='background:"+RenderingEngine.getEntryBackgroundColour(it%2==0)+"; width:98%; margin:0 1%; padding:"+(i==0?"2px":"2px "+(ingredient.getEffects().contains(ie)?"64px":"22px")+" 2px 2px")+";'>"
										+Util.capitaliseSentence(s));
							if(i==0) {
								inventorySB.append(
									(ingredient.getEffects().contains(ie)
										?"<div class='normal-button' style='width:auto; min-width:64px; height:22px; line-height:22px; font-size:16px; margin:0; padding:0 0 0 4px; float:right; text-align:left;'>"
												+ "<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>X</b> "
												+ (ingredient instanceof Tattoo
														?UtilText.formatAsMoney(EnchantingUtils.getModifierEffectCost(false, ingredient, ie)*EnchantingUtils.FLAME_COST_MODIFER, "b")
														:UtilText.formatAsEssences(EnchantingUtils.getModifierEffectCost(false, ingredient, ie), "b", false))
												+ "<div class='overlay' id='DELETE_EFFECT_"+it+"'></div>"
											+ "</div>"
										:"<div class='normal-button' id='DELETE_EFFECT_"+it+"' style='width:22px; height:22px; line-height:22px; font-size:16px; margin:0; padding:0; float:right; color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>"
											+ "<b>X</b>"
										+ "</div>"));
							}
							inventorySB.append("</div>");
							i++;
						}
					}
					
					if(Main.game.isEnchantmentCapacityEnabled()) {
						if((ingredient instanceof AbstractClothing)
								|| (ingredient instanceof AbstractWeapon)
								|| (ingredient instanceof Tattoo)) {
							inventorySB.append("<br/>"
									+ (cost>0
											?"[style.colourEnchantment("+Util.capitaliseSentence(Attribute.ENCHANTMENT_LIMIT.getName())+" cost)]: [style.boldBad("+cost+")]"
											:Util.capitaliseSentence(Attribute.ENCHANTMENT_LIMIT.getName())+" cost: [style.boldDisabled(0)]"));
						}
					}
				}
			inventorySB.append("</div>");
			
			
			AbstractCoreItem preview = null;
			if(EnchantmentDialogue.getIngredient() instanceof AbstractItem) {
				preview = EnchantingUtils.craftItem(EnchantmentDialogue.getIngredient(), EnchantmentDialogue.getEffects());

			} else if(EnchantmentDialogue.getIngredient() instanceof AbstractClothing) {
				preview = EnchantingUtils.craftClothing(EnchantmentDialogue.getIngredient(), EnchantmentDialogue.getEffects());

			} else if(EnchantmentDialogue.getIngredient() instanceof AbstractWeapon) {
				preview = EnchantingUtils.craftWeapon(EnchantmentDialogue.getIngredient(), EnchantmentDialogue.getEffects());

			}  else if(EnchantmentDialogue.getIngredient() instanceof Tattoo) {
				preview = EnchantingUtils.craftTattoo(EnchantmentDialogue.getIngredient(), EnchantmentDialogue.getEffects());
			}
			
			if(preview!=null) {
				inventorySB.append("<div class='container-half-width' style='width:18%; margin:0 1%; text-align:center;'>");
					inventorySB.append("<b>Output</b>");
					inventorySB.append("<div class='enchanting-ingredient' style='background-color:"+preview.getRarity().getBackgroundColour().toWebHexString()+";'>");
						inventorySB.append("<div class='enchanting-ingredient-content'>"+preview.getSVGString()+"</div>");
						inventorySB.append("<div class='overlay' id='OUTPUT_ENCHANTING' style='cursor:default;'></div>");
					inventorySB.append("</div>");
				inventorySB.append("</div>");
			}
		inventorySB.append("</div>");
		inventorySB.append("<p id='hiddenPField' style='display:none;'></p>");
		
		
		return inventorySB.toString();
	}

	public static DialogueNode getEnchantmentMenu(AbstractCoreItem item) {
		return getEnchantmentMenu(item, null, null);
	}
	
	public static DialogueNode getEnchantmentMenu(AbstractCoreItem item, GameCharacter tattooBearer, InventorySlot tattooSlot) {
		interactionInit = InventoryDialogue.getNPCInventoryInteraction();
		
		EnchantmentDialogue.effects.clear();
		EnchantmentDialogue.resetEnchantmentVariables();
		EnchantmentDialogue.initModifiers(item, tattooBearer, tattooSlot);
		
		EnchantmentDialogue.setOutputName(EnchantingUtils.getPotionName(item, effects));
		
		return ENCHANTMENT_MENU;
	}
	
	/**
	 * Use getEnchantmentMenu() to get this DialogueNodeOld when initially opening the Enchantment menu, as it resets all variables for you.
	 */
	public static final DialogueNode ENCHANTMENT_MENU = new DialogueNode("Enchantments", "", true) {
		@Override
		public void applyPreParsingEffects() {
			InventoryDialogue.setNPCInventoryInteraction(InventoryInteraction.FULL_MANAGEMENT);
			if(tattooBearer instanceof NPC) {
				InventoryDialogue.setInventoryNPC((NPC) tattooBearer);
			}
		}
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
				return new Response("Back", "Stop enchanting.", InventoryDialogue.ITEM_INVENTORY) {
					@Override
					public DialogueNode getNextDialogue() {
						if(ingredient instanceof AbstractItem) {
							if(InventoryDialogue.getItem()==null) {
								InventoryDialogue.setItem((AbstractItem) ingredient);
							}
							return InventoryDialogue.ITEM_INVENTORY;
							
						} else if(ingredient instanceof AbstractClothing) {
							if(InventoryDialogue.getClothing()==null) {
								InventoryDialogue.setClothing((AbstractClothing) ingredient);
							}
							if(Main.game.getPlayer().getClothingCurrentlyEquipped().contains(ingredient)) {
								return InventoryDialogue.CLOTHING_EQUIPPED;
							} else {
								return InventoryDialogue.CLOTHING_INVENTORY;
							}
							
						} else if(ingredient instanceof AbstractWeapon){
							if(InventoryDialogue.getWeapon()==null) {
								InventoryDialogue.setWeapon(isEquippedIn, (AbstractWeapon) ingredient);
							}
							if(Main.game.getPlayer().hasWeaponEquipped((AbstractWeapon) ingredient)) {
								return InventoryDialogue.WEAPON_EQUIPPED;
							} else {
								return InventoryDialogue.WEAPON_INVENTORY;
							}
							
						} else if(ingredient instanceof Tattoo){
							if(BodyChanging.getTarget().isPlayer()) {
								if(Main.game.getPlayer().getLocationPlaceType()==PlaceType.SHOPPING_ARCADE_KATES_SHOP) {
									return SuccubisSecrets.SHOP_BEAUTY_SALON_TATTOOS;
								} else {
									return CosmeticsDialogue.BEAUTICIAN_TATTOOS;
								}
							} else {
								return CompanionManagement.SLAVE_MANAGEMENT_TATTOOS;
							}
							
						} else {
							throw new IllegalStateException("If it's not an item, not clothing, and not a weapon, then what?");
						}
					}
					@Override
					public void effects() {
						Main.game.setResponseTab(1);
						EnchantmentDialogue.resetEnchantmentVariables();
						InventoryDialogue.setNPCInventoryInteraction(interactionInit);
					}
				};
				
			// Ingredients:
			} else if (index == 1) {
				int price = EnchantingUtils.getCost(ingredient, effects)*EnchantingUtils.FLAME_COST_MODIFER;
				
				if((effects.equals(ingredient.getEffects())
						|| (effects.isEmpty() && ingredient instanceof AbstractItem))
//						 && outputName.equals(ingredient.getName())
						 ) {
					return new Response("Craft", "You need to add at least one effect before you can craft something!", null);
					
				} else if(canAffordCost(ingredient, effects)) {
					return new ResponseEffectsOnly((ingredient instanceof Tattoo
																?"Enchant ("+UtilText.formatAsMoney(price, "span")+")"
																:"Craft"),
													((ingredient instanceof Tattoo)
																	?"Enchant this tattoo with the specified effects. This will cost "+UtilText.formatAsMoney(price, "span")+"."
																	:"Craft '"+EnchantingUtils.getPotionName(ingredient, effects)+"'. This will cost [style.boldArcane("+EnchantingUtils.getCost(ingredient, effects)+")] arcane essences.")){
						@Override
						public void effects() {
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
							EnchantmentDialogue.setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
							
							craftAndApplyFullInventoryEffects(ingredient, effects);
							
							if((previousIngredient instanceof AbstractItem && Main.game.getPlayer().hasItem((AbstractItem) previousIngredient))
									|| (previousIngredient instanceof AbstractClothing && Main.game.getPlayer().hasClothing((AbstractClothing) previousIngredient))
									|| (previousIngredient instanceof AbstractWeapon && Main.game.getPlayer().hasWeapon((AbstractWeapon) previousIngredient))) {
								ingredient = previousIngredient;
								effects = new ArrayList<>(previousEffects);
								Main.game.setContent(new Response("", "", ENCHANTMENT_MENU));
								
							} else if(previousIngredient instanceof Tattoo) {
								if(BodyChanging.getTarget().isPlayer()) {
									if(Main.game.getPlayer().getLocationPlaceType()==PlaceType.SHOPPING_ARCADE_KATES_SHOP) {
										Main.game.setContent(new Response("", "", SuccubisSecrets.SHOP_BEAUTY_SALON_TATTOOS));
									} else {
										Main.game.setContent(new Response("", "", CosmeticsDialogue.BEAUTICIAN_TATTOOS));
									}
								} else {
									Main.game.setContent(new Response("", "", CompanionManagement.SLAVE_MANAGEMENT_TATTOOS));
								}
								
							} else {
								Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU) {
									@Override
									public void effects() {
										Main.game.setResponseTab(0);
										EnchantmentDialogue.resetEnchantmentVariables();
										InventoryDialogue.resetItems();
										InventoryDialogue.setNPCInventoryInteraction(interactionInit);
									}
								});
							}
							
						}
					};
					
				} else {
					return new Response(
							(ingredient instanceof Tattoo
									?"Enchant ("+UtilText.formatAsMoneyUncoloured(price, "span")+")"
									:"Craft"),
							"You can't afford to craft this right now!", null);
				}

			// Save/load
			} else if (index == 2) {
				return new Response("Save/Load", "Save/Load enchantment recipes.", ENCHANTMENT_SAVE_LOAD) {
					@Override
					public void effects() {
						Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
						EnchantmentDialogue.setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
						initSaveLoadMenu();
					}
				};
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	
	public static boolean canAffordCost(AbstractCoreItem ingredient, List<ItemEffect> itemEffects) {
		if(ingredient instanceof Tattoo) {
			return Main.game.getPlayer().getMoney()  >= EnchantingUtils.getCost(ingredient, itemEffects)*EnchantingUtils.FLAME_COST_MODIFER;
		}
		return Main.game.getPlayer().getEssenceCount() >= EnchantingUtils.getCost(ingredient, itemEffects);
	}

	public static AbstractCoreItem craftAndApplyFullInventoryEffects(AbstractCoreItem ingredient, List<ItemEffect> effects) {
		return craftAndApplyFullInventoryEffects(ingredient, effects, true);
	}
	
	public static AbstractCoreItem craftAndApplyFullInventoryEffects(AbstractCoreItem ingredient, List<ItemEffect> effects, boolean applyCost) {
		if(ingredient instanceof AbstractItem) {
			Main.game.getPlayer().removeItem((AbstractItem) ingredient);
			AbstractItem craftedItem = EnchantingUtils.craftItem(ingredient, effects);
			Main.game.getPlayer().addItem(craftedItem, false);
			Main.game.addEvent(new EventLogEntry("[style.colourExcellent(Item Enchanted)]", Util.capitaliseSentence(craftedItem.getName(false, true))), false);
			finaliseCrafting(ingredient, effects, applyCost);
			return craftedItem;
			
		} else if(ingredient instanceof AbstractClothing) {
			Main.game.getPlayer().removeClothing((AbstractClothing) ingredient);
			AbstractClothing craftedClothing = EnchantingUtils.craftClothing(ingredient, effects);
			Main.game.getPlayer().addClothing(craftedClothing, false);
			Main.game.addEvent(new EventLogEntry("[style.colourExcellent(Clothing Enchanted)]", Util.capitaliseSentence(craftedClothing.getName(false, true))), false);
			finaliseCrafting(ingredient, effects, applyCost);
			return craftedClothing;
			
		} else if(ingredient instanceof AbstractWeapon) {
			Main.game.getPlayer().removeWeapon((AbstractWeapon) ingredient);
			AbstractWeapon craftedWeapon = EnchantingUtils.craftWeapon(ingredient, effects);
			Main.game.getPlayer().addWeapon(craftedWeapon, false);
			Main.game.addEvent(new EventLogEntry("[style.colourExcellent(Weapon Enchanted)]", Util.capitaliseSentence(craftedWeapon.getName(false, true))), false);
			finaliseCrafting(ingredient, effects, applyCost);
			return craftedWeapon;
			
		} else if(ingredient instanceof Tattoo) {
			if(applyCost) {
				Main.game.getPlayer().incrementMoney(-EnchantingUtils.getCost(ingredient, effects)*EnchantingUtils.FLAME_COST_MODIFER);
			}
			Tattoo tattoo;
			if (EnchantmentDialogue.isEquipped) {
				EnchantmentDialogue.isEquippedTo.removeTattoo(EnchantmentDialogue.isEquippedIn);
				tattoo = EnchantingUtils.craftTattoo(ingredient, effects);
				EnchantmentDialogue.isEquippedTo.addTattoo(EnchantmentDialogue.isEquippedIn, tattoo);
			} else {
				System.err.println("craftAndApplyFullInventoryEffects() error: Tattoo is not equipped?");
				tattoo = EnchantingUtils.craftTattoo(ingredient, effects);
			}
			Main.game.addEvent(new EventLogEntry("[style.colourExcellent(Tattoo Enchanted)]", Util.capitaliseSentence(((Tattoo)ingredient).getName())), false);
			finaliseCrafting(ingredient, effects, applyCost);
			return tattoo;
		}
		
		return null;
	}
	
	private static void finaliseCrafting(AbstractCoreItem ingredient, List<ItemEffect> effects, boolean applyCost) {
		if(!(ingredient instanceof Tattoo) && applyCost) {
			Main.game.getPlayer().incrementEssenceCount(-EnchantingUtils.getCost(ingredient, effects), false);
		}
		
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
		EnchantmentDialogue.tattooBearer = null;
		EnchantmentDialogue.tattooSlot = null;
		EnchantmentDialogue.isEquipped = false;
		EnchantmentDialogue.isEquippedIn = null;
		EnchantmentDialogue.isEquippedTo = null;
	}
	
	public static void resetNonTattooEnchantmentVariables() {
		if(!(ingredient instanceof Tattoo)) {
			EnchantmentDialogue.ingredient = null;
			EnchantmentDialogue.tattooBearer = null;
			EnchantmentDialogue.tattooSlot = null;
		}
		EnchantmentDialogue.primaryMod = TFModifier.NONE;
		EnchantmentDialogue.secondaryMod = TFModifier.NONE;
		EnchantmentDialogue.potency = TFPotency.MINOR_BOOST;
		EnchantmentDialogue.limit = 0;
	}
	

	public static void initModifiers(AbstractCoreItem ingredient) {
		initModifiers(ingredient, EnchantmentDialogue.tattooBearer, EnchantmentDialogue.tattooSlot);
	}
	
	public static void initModifiers(AbstractCoreItem ingredient, GameCharacter tattooBearer, InventorySlot tattooSlot) {
		EnchantmentDialogue.ingredient = ingredient;
		EnchantmentDialogue.tattooBearer = tattooBearer;
		EnchantmentDialogue.tattooSlot = tattooSlot;
		
		if(ingredient instanceof AbstractClothing
				|| ingredient instanceof Tattoo
				|| ingredient instanceof AbstractWeapon) {
			effects = new ArrayList<>(ingredient.getEffects());
			
			if (ingredient instanceof Tattoo && tattooBearer.getTattooInSlot(tattooSlot) == ingredient) {
				EnchantmentDialogue.isEquipped = true;
				EnchantmentDialogue.isEquippedIn = tattooSlot;
				EnchantmentDialogue.isEquippedTo = tattooBearer;
			}
			
		} else {
			EnchantmentDialogue.effects = new ArrayList<>();
		}
		
		if(!EnchantmentDialogue.ingredient.getEnchantmentEffect().getPrimaryModifiers().contains(EnchantmentDialogue.primaryMod)) {
			EnchantmentDialogue.primaryMod = EnchantmentDialogue.ingredient.getEnchantmentEffect().getPrimaryModifiers().get(0);
		}
		if(!EnchantmentDialogue.ingredient.getEnchantmentEffect().getSecondaryModifiers(EnchantmentDialogue.ingredient, EnchantmentDialogue.primaryMod).contains(EnchantmentDialogue.secondaryMod)) {
			EnchantmentDialogue.secondaryMod = EnchantmentDialogue.ingredient.getEnchantmentEffect().getSecondaryModifiers(EnchantmentDialogue.ingredient, EnchantmentDialogue.primaryMod).get(0);
		}
		if(!EnchantmentDialogue.ingredient.getEnchantmentEffect().getPotencyModifiers(EnchantmentDialogue.primaryMod, EnchantmentDialogue.secondaryMod).contains(EnchantmentDialogue.potency)) {
			EnchantmentDialogue.potency = TFPotency.MINOR_BOOST;
		}
		if(EnchantmentDialogue.limit <= EnchantmentDialogue.ingredient.getEnchantmentEffect().getLimits(EnchantmentDialogue.primaryMod, EnchantmentDialogue.secondaryMod)) {
			EnchantmentDialogue.limit = EnchantmentDialogue.ingredient.getEnchantmentEffect().getLimits(EnchantmentDialogue.primaryMod, EnchantmentDialogue.secondaryMod);
		}
	}

	public static void initSaveLoadMenu() {
		loadedEnchantmentsMap = new HashMap<>();
		
		for(File f : getSavedEnchants()) {
			try {
				String name = Util.getFileIdentifier(f);
				LoadedEnchantment loadedEnchant = loadEnchant(name);
				if(ingredient instanceof Tattoo?loadedEnchant.getTattooType()!=null:loadedEnchant.getTattooType()==null) {
					loadedEnchantmentsMap.put(name, loadedEnchant);
				}
			} catch(Exception ex) {
			}
		}
	}
	
	public static String loadConfirmationName = "", overwriteConfirmationName = "", deleteConfirmationName = "";
	public static final DialogueNode ENCHANTMENT_SAVE_LOAD = new DialogueNode("Save enchantment files", "", true) {

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();
			
			saveLoadSB.append(
					"<div class='container-full-width' style='padding:0; margin:0 0 8px 0;'>"
							+ "Only standard characters (letters and numbers) will work for save file names."
							+ "<br/>Hover over each item's icon to see the effects to be saved/loaded."
							+ "<br/>If a name is [style.colourBad(red)], then you don't have a suitable item in your inventory, and cannot load that effect."
							+ "<br/>You can only save/overwrite saves if your enchantment has at least one effect added."
					+ "</div>"
					+ "<div class='container-full-width' style='padding:0; margin:0;'>"
						+ "<div class='container-full-width' style='width:calc(75% - 16px); text-align:center; background:transparent;'>"
							+ "Name"
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px); text-align:center; background:transparent;'>"
							+ "Save | Load | Delete"
						+ "</div>"
					+ "</div>");

			int i=0;
			
			saveLoadSB.append(getSaveLoadRow(null, null, i%2==0));
			i++;
			
			for(Entry<String, LoadedEnchantment> entry : loadedEnchantmentsMap.entrySet()){
				saveLoadSB.append(getSaveLoadRow(entry.getKey(), entry.getValue(), i%2==0));
				i++;
			}
			
			saveLoadSB.append("<p id='hiddenPField' style='display:none;'></p>");
			
			return saveLoadSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Confirmations: ",
						"Toggle confirmations being shown when you click to load, overwrite, or delete a saved enchantment."
							+ " When turned on, it will take two clicks to apply any button press."
							+ " When turned off, it will only take one click.",
						ENCHANTMENT_SAVE_LOAD) {
					@Override
					public String getTitle() {
						return "Confirmations: "+(Main.getProperties().hasValue(PropertyValue.overwriteWarning)
								?"<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>ON</span>"
								:"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>OFF</span>");
					}
					
					@Override
					public void effects() {
						loadConfirmationName = "";
						overwriteConfirmationName = "";
						deleteConfirmationName = "";
						Main.getProperties().setValue(PropertyValue.overwriteWarning, !Main.getProperties().hasValue(PropertyValue.overwriteWarning));
						Main.getProperties().savePropertiesAsXML();
					}
				};

			} else if (index == 0) {
				return new Response("Back", "Back to the enchantment menu.", ENCHANTMENT_MENU);

			} else {
				return null;
			}
		}
	};
	
	public static List<File> getSavedEnchants() {
		List<File> filesList = new ArrayList<>();
		
		File dir = new File("data/enchantments");
		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, name) -> name.endsWith(".xml"));
			if (directoryListing != null) {
				filesList.addAll(Arrays.asList(directoryListing));
			}
		}

		filesList.sort(Comparator.comparing(File::getName).reversed());
		
		return filesList;
	}

	private static String getSaveLoadRow(String baseName, LoadedEnchantment loadedEnchantment, boolean altColour) {
		
		if(loadedEnchantment!=null){
			String fileName = (baseName+".xml");
			
			boolean suitableItemAvailable = loadedEnchantment.isSuitableItemAvailable();
			
			return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:#222;":"")+" position:relative;'>"
						
						+ "<div class='container-full-width' style='width:calc(75% - 16px); background:transparent;'>"
						
							+ "<div class='container-full-width' style='width:10%; margin:0; padding:0; background:transparent; position:relative; float:left;'>"
								+"<div class='inventoryImage' style='width:100%;'>"
									+ "<div class='inventoryImage-content'>"
										+ loadedEnchantment.getSVGString()
									+ "</div>"
									+ "<div class='overlay no-pointer' id='LOADED_ENCHANTMENT_" + baseName + "'></div>"
								+ "</div>"
							+ "</div>"
						
							+ "<div style='width:calc(90% - 8px); padding:0; margin:0 0 0 8px; position:relative; float:left;'>"
								+ "<h6 style='margin:0; padding:2px;'>"+(!suitableItemAvailable?"[style.boldBad("+loadedEnchantment.getName()+")]":loadedEnchantment.getName())+"</h6>"
								+ "<p style='margin:0; padding:2px;'>[style.colourDisabled(data/enchantments/)]"+baseName+"[style.colourDisabled(.xml)]</p>"
							+"</div>"
							
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px);text-align:center; background:transparent;'>"
							+ (Main.game.isStarted() && !Main.game.isInCombat() && !Main.game.isInSex() && !EnchantmentDialogue.getEffects().isEmpty()
									?(fileName.equals(overwriteConfirmationName)
										?"<div class='square-button saveIcon' id='overwrite_saved_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveConfirm()+"</div></div>"
										:"<div class='square-button saveIcon' id='overwrite_saved_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskOverwrite()+"</div></div>")
									:"<div class='square-button saveIcon disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveDisabled()+"</div></div>")
							
							+ (suitableItemAvailable
									? (fileName.equals(loadConfirmationName)
										?"<div class='square-button saveIcon' id='load_saved_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoadConfirm()+"</div></div>"
										:"<div class='square-button saveIcon' id='load_saved_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoad()+"</div></div>")
									:"<div class='square-button saveIcon disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoadDisabled()+"</div></div>")
	
	
							+ (fileName.equals(deleteConfirmationName)
								?"<div class='square-button saveIcon' id='delete_saved_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDeleteConfirm()+"</div></div>"
								:"<div class='square-button saveIcon' id='delete_saved_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDelete()+"</div></div>")
						+ "</div>"
					+ "</div>";
			
		} else {
			if(EnchantmentDialogue.getEffects().isEmpty()) {
				return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:#222;":"")+"'>"
						
							+ "<div class='container-full-width' style='width:calc(75% - 16px); background:transparent; text-align:center;'>"
								+"[style.colourDisabled(Cannot save an enchantment that has no effects added!)]"
							+ "</div>"
								
							+ "<div class='container-full-width' style='width:calc(25% - 16px); text-align:center; background:transparent;'>"
								+ "<div class='square-button saveIcon disabled' style='float:left;'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveDisabled()+"</div></div>"
							+ "</div>"
						+ "</div>";
				
			} else {
				String svgString = "";
				if(EnchantmentDialogue.getIngredient() instanceof AbstractItem) {
					svgString = ((AbstractItem)EnchantmentDialogue.getIngredient()).getSVGString();
					
				} else if(EnchantmentDialogue.getIngredient() instanceof AbstractClothing) {
					svgString = ((AbstractClothing)EnchantmentDialogue.getIngredient()).getSVGString();
					
				} else if(EnchantmentDialogue.getIngredient() instanceof AbstractWeapon) {
					svgString = ((AbstractWeapon)EnchantmentDialogue.getIngredient()).getSVGString();
					
				} else if(EnchantmentDialogue.getIngredient() instanceof Tattoo) {
					svgString = ((Tattoo)EnchantmentDialogue.getIngredient()).getSVGString();
				}
				
				return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:#222;":"")+"'>"
						
							+ "<div class='container-full-width' style='width:calc(75% - 16px); background:transparent;'>"
						
								+ "<div class='container-full-width' style='width:10%; margin:0; padding:0; background:transparent; position:relative; float:left;'>"
									+"<div class='inventoryImage' style='width:100%;'>"
										+ "<div class='inventoryImage-content'>"
											+ svgString
										+ "</div>"
										+ "<div class='overlay no-pointer' id='LOADED_ENCHANTMENT_CURRENT'></div>"
									+ "</div>"
								+ "</div>"
							
								+ "<div style='width:calc(90% - 8px); padding:0; margin:0 0 0 8px; position:relative; float:left;'>"
									+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='new_save_name' placeholder='Enter File Name' style='padding:0;margin:0;width:100%;'></form>"
								+"</div>"
								
							+ "</div>"
						
							+ "<div class='container-full-width' style='width:calc(25% - 16px); text-align:center; background:transparent;'>"
								+ "<div class='square-button saveIcon' id='new_saved' style='float:left;'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSave()+"</div></div>"
							+ "</div>"
						+ "</div>";
			}
				
		}
	}

	public static void saveEnchant(String name, boolean allowOverwrite) {
		name = Main.checkFileName(name);
		if(name.isEmpty()) {
			return;
		}
		
		File dir = new File("data/");
		dir.mkdir();

		dir = new File("data/enchantments");
		dir.mkdir();

		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, filename) -> filename.endsWith(".xml"));
			if (directoryListing != null) {
				for (File child : directoryListing) {
					if (child.getName().equals(name+".xml")){
						if(!allowOverwrite) {
							Main.game.flashMessage(PresetColour.GENERIC_BAD, "Name already exists!");
							return;
						}
					}
				}
			}
		}

		try {
			// Starting stuff:
			Document doc = Main.getDocBuilder().newDocument();
			
			Element enchantment = doc.createElement("enchantment");
			doc.appendChild(enchantment);

			if(EnchantmentDialogue.getIngredient() instanceof AbstractItem) {
				Element itemTypeElement = doc.createElement("itemType");
				enchantment.appendChild(itemTypeElement);
				itemTypeElement.setTextContent(((AbstractItem)EnchantmentDialogue.getIngredient()).getItemType().getId());
				
			} else if(EnchantmentDialogue.getIngredient() instanceof AbstractClothing) {
				Element itemTypeElement = doc.createElement("clothingType");
				enchantment.appendChild(itemTypeElement);
				itemTypeElement.setTextContent(((AbstractClothing)EnchantmentDialogue.getIngredient()).getClothingType().getId());
				
			} else if(EnchantmentDialogue.getIngredient() instanceof AbstractWeapon) {
				Element itemTypeElement = doc.createElement("weaponType");
				enchantment.appendChild(itemTypeElement);
				itemTypeElement.setTextContent(((AbstractWeapon)EnchantmentDialogue.getIngredient()).getWeaponType().getId());
				
			} else if(EnchantmentDialogue.getIngredient() instanceof Tattoo) {
				Element itemTypeElement = doc.createElement("tattooType");
				enchantment.appendChild(itemTypeElement);
				itemTypeElement.setTextContent(((Tattoo)EnchantmentDialogue.getIngredient()).getType().getId());
			}
			
			Element nameElement = doc.createElement("name");
			enchantment.appendChild(nameElement);
			nameElement.appendChild(doc.createCDATASection(EnchantmentDialogue.getOutputName()));
			
			Element itemEffects = doc.createElement("itemEffects");
			enchantment.appendChild(itemEffects);
			
			for(ItemEffect effect : effects) {
				effect.saveAsXML(itemEffects, doc);
			}
			
			// Ending stuff:
			
			Transformer transformer1 = Main.transformerFactory.newTransformer();
			transformer1.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();

			transformer1.transform(new DOMSource(doc), new StreamResult(writer));
			
			// Save this xml:
			Transformer transformer = Main.transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			
			String saveLocation = "data/enchantments/"+name+".xml";
			StreamResult result = new StreamResult(saveLocation);
			
			transformer.transform(source, result);
			
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
		Main.game.setContent(new Response("Save", "", EnchantmentDialogue.ENCHANTMENT_MENU));
	}

	public static LoadedEnchantment loadEnchant(String name) {
		if (isLoadEnchantAvailable(name)) {
			File file = new File("data/enchantments/"+name+".xml");

			if (file.exists()) {
				try {
					Document doc = Main.getDocBuilder().parse(file);
					
					// Cast magic:
					doc.getDocumentElement().normalize();
					
					String importedName = ((Element) doc.getElementsByTagName("name").item(0)).getTextContent();
					
					Element enchantment = (Element) doc.getElementsByTagName("enchantment").item(0);
					Element itemEffects = (Element) enchantment.getElementsByTagName("itemEffects").item(0);
					List<ItemEffect> effectsToBeAdded = new ArrayList<>();
					for(int i=0; i<itemEffects.getElementsByTagName("effect").getLength(); i++) {
						Element e = ((Element)itemEffects.getElementsByTagName("effect").item(i));
						ItemEffect itemEffect = ItemEffect.loadFromXML(e, doc);
						if(itemEffect!=null) {
							effectsToBeAdded.add(itemEffect);
						}
					}
					
					if(doc.getElementsByTagName("itemType").item(0)!=null) {
						return new LoadedEnchantment(importedName, ItemType.getItemTypeFromId(doc.getElementsByTagName("itemType").item(0).getTextContent()), effectsToBeAdded);
						
					} else if(doc.getElementsByTagName("clothingType").item(0)!=null) {
						return new LoadedEnchantment(importedName, ClothingType.getClothingTypeFromId(doc.getElementsByTagName("clothingType").item(0).getTextContent()), effectsToBeAdded);
						
					} else if(doc.getElementsByTagName("weaponType").item(0)!=null) {
						return new LoadedEnchantment(importedName, WeaponType.getWeaponTypeFromId(doc.getElementsByTagName("weaponType").item(0).getTextContent()), effectsToBeAdded);
						
					} else if(doc.getElementsByTagName("tattooType").item(0)!=null) {
						return new LoadedEnchantment(importedName, TattooType.getTattooTypeFromId(doc.getElementsByTagName("tattooType").item(0).getTextContent()), effectsToBeAdded);
					}
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	public static boolean isLoadEnchantAvailable(String name) {
		File file = new File("data/enchantments/"+name+".xml");

		if(!file.exists()) {
			return false;
		}
		
		return true;
	}

	public static void deleteEnchant(String name) {
		File file = new File("data/enchantments/"+name+".xml");

		if (file.exists()) {
			try {
				file.delete();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		} else {
			Main.game.flashMessage(PresetColour.GENERIC_BAD, "File not found...");
		}
	}

	public static AbstractCoreItem getIngredient() {
		return ingredient;
	}

	public static void setIngredient(AbstractCoreItem ingredient) {
		EnchantmentDialogue.ingredient = ingredient;
	}

	public static AbstractCoreItem getPreviousIngredient() {
		return previousIngredient;
	}

	public static void setPreviousIngredient(AbstractCoreItem previousIngredient) {
		EnchantmentDialogue.previousIngredient = previousIngredient;
	}

	public static ItemEffect getCurrentEffect() {
		return new ItemEffect(ingredient.getEnchantmentEffect(), primaryMod, secondaryMod, potency, limit);
	}
	
	public static List<ItemEffect> getEffects() {
		return effects;
	}
	
	public static String getEnchantmentEffectBlockedReason(ItemEffect effect) {
		if(ingredient instanceof AbstractClothing) {
			if(effect.getSecondaryModifier()==TFModifier.CLOTHING_VIBRATION) {
				for(ItemEffect ie : effects) {
					if(ie.getSecondaryModifier()==TFModifier.CLOTHING_VIBRATION) {
						return "Only one 'vibration' effect can be added to clothing!";
					}
				}
			}
		}
		return null;
	}
	
	public static boolean addEffect(ItemEffect effect) {
		boolean defaultName = EnchantingUtils.getPotionName(EnchantmentDialogue.getIngredient(), EnchantmentDialogue.getEffects()).equalsIgnoreCase(EnchantmentDialogue.getOutputName());
		
		boolean added = false;
		
//		!(ingredient instanceof Tattoo) || 
		if(effects.size()<ingredient.getEnchantmentLimit()) {
			added = getEffects().add(effect);
			
			if(added) {
				if(defaultName) {
					EnchantmentDialogue.setOutputName(EnchantingUtils.getPotionName(EnchantmentDialogue.getIngredient(), EnchantmentDialogue.getEffects()));
				} else {
					if(Main.game.getCurrentDialogueNode().equals(EnchantmentDialogue.ENCHANTMENT_MENU)) {
						Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
						EnchantmentDialogue.setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
					}
				}
			}
		}
		
		return added;
	}
	
	public static boolean removeEffect(int index) {
		boolean defaultName = EnchantingUtils.getPotionName(EnchantmentDialogue.getIngredient(), EnchantmentDialogue.getEffects()).equalsIgnoreCase(EnchantmentDialogue.getOutputName());
		getEffects().remove(index);
		
		if(defaultName) {
			EnchantmentDialogue.setOutputName(EnchantingUtils.getPotionName(EnchantmentDialogue.getIngredient(), EnchantmentDialogue.getEffects()));
		} else {
			if(Main.game.getCurrentDialogueNode().equals(EnchantmentDialogue.ENCHANTMENT_MENU)) {
				Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
				EnchantmentDialogue.setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
			}
		}
		
		return true;
	}
	
	public static boolean removeEffect(ItemEffect effect) {
		boolean defaultName = EnchantingUtils.getPotionName(EnchantmentDialogue.getIngredient(), EnchantmentDialogue.getEffects()).equalsIgnoreCase(EnchantmentDialogue.getOutputName());
		boolean removed = getEffects().remove(effect);
		
		if(removed) {
			if(defaultName) {
				EnchantmentDialogue.setOutputName(EnchantingUtils.getPotionName(EnchantmentDialogue.getIngredient(), EnchantmentDialogue.getEffects()));
			} else {
				if(Main.game.getCurrentDialogueNode().equals(EnchantmentDialogue.ENCHANTMENT_MENU)) {
					Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
					EnchantmentDialogue.setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
				}
			}
		}
		
		return removed;
	}

	public static TFModifier getPrimaryMod() {
		return primaryMod;
	}

	public static void setPrimaryMod(TFModifier primaryMod) {
		EnchantmentDialogue.primaryMod = primaryMod;
		if(Main.game.getCurrentDialogueNode().equals(EnchantmentDialogue.ENCHANTMENT_MENU)) {
			Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
			EnchantmentDialogue.setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
		}
	}

	public static TFModifier getSecondaryMod() {
		return secondaryMod;
	}

	public static void setSecondaryMod(TFModifier secondaryMod) {
		EnchantmentDialogue.secondaryMod = secondaryMod;
		if(Main.game.getCurrentDialogueNode().equals(EnchantmentDialogue.ENCHANTMENT_MENU)) {
			Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
			EnchantmentDialogue.setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
		}
	}

	public static TFPotency getPotency() {
		return potency;
	}

	public static void setPotency(TFPotency potency) {
		EnchantmentDialogue.potency = potency;
		if(Main.game.getCurrentDialogueNode().equals(EnchantmentDialogue.ENCHANTMENT_MENU)) {
			Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
			EnchantmentDialogue.setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
		}
	}

	public static int getLimit() {
		return limit;
	}

	public static void setLimit(int limit) {
		EnchantmentDialogue.limit = limit;
		if(Main.game.getCurrentDialogueNode().equals(EnchantmentDialogue.ENCHANTMENT_MENU)) {
			Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
			EnchantmentDialogue.setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
		}
	}

	public static String getOutputName() {
		return outputName;
	}

	public static void setOutputName(String outputName) {
		EnchantmentDialogue.outputName = outputName;
	}

	public static Map<String, LoadedEnchantment> getLoadedEnchantmentsMap() {
		return loadedEnchantmentsMap;
	}

	public static InventorySlot getTattooSlot() {
		return tattooSlot;
	}

	public static GameCharacter getTattooBearer() {
		return tattooBearer;
	}
}
