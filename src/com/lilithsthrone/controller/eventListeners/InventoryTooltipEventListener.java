package com.lilithsthrone.controller.eventListeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import com.lilithsthrone.controller.TooltipUpdateThread;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.utils.EnchantmentDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ShopTransaction;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemEffect;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * Shows the tooltip at the given element's position.
 * 
 * @since 0.1.0
 * @version 0.1.85
 * @author Innoxia
 */
public class InventoryTooltipEventListener implements EventListener {
	private GameCharacter owner, equippedToCharacter;
	private AbstractCoreItem coreItem;
	private AbstractItem item;
	private AbstractItemType genericItem;
	private AbstractWeapon weapon;
	private AbstractWeaponType genericWeapon;
	private DamageType dt;
	private AbstractClothing clothing;
	private Colour colour;
	private AbstractClothingType genericClothing;
	private InventorySlot invSlot;
	private TFModifier enchantmentModifier;
	private TFEssence essence;

	private static StringBuilder tooltipSB;
	static {
		tooltipSB = new StringBuilder();
	}

	@Override
	public void handleEvent(Event event) {
		if (item != null || (coreItem instanceof AbstractItem)) {
			
			if(coreItem != null)
				item = (AbstractItem) coreItem;
			
			int yIncrease = 0;
			if (InventoryDialogue.getInventoryNPC() != null && InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {
				yIncrease += 2;
			}
			
			if(item.getItemEffects().size()>0) {
				for(ItemEffect ie : item.getItemEffects()) {
					yIncrease += ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).size();
				}
			}
			
			Main.mainController.setTooltipSize(360, 290 + (20 * yIncrease));

			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>" + Util.capitaliseSentence(item.getDisplayName(true)) + "</div>");

			if(item.getItemEffects().size()>0) {
				tooltipSB.append("<div class='subTitle'>");
				int ieCount=0;
				for(ItemEffect ie : item.getItemEffects()) {
					if(ieCount>0)
						tooltipSB.append("</br>");
					ieCount++;
					for(int i=0; i<ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).size(); i++) {
						if(i!=0)
							tooltipSB.append("</br>");
						tooltipSB.append(ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).get(i));
					}
				}
				tooltipSB.append("</div>");
			}

			tooltipSB.append("<div class='subTitle-half'>"
					+ (item.isConsumedOnUse() ? "<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Consumed on use</span>" : "<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Infinite uses</span>") + "</div>"
					+ "<div class='subTitle-half'>"
					+ (item.isAbleToBeUsedInCombat()
							? (item.isAbleToBeUsedInSex()
									?"<span style='color:" + Colour.GENERIC_COMBAT.toWebHexString() + ";'>Combat</span> & <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Sex</span> use"
									:"<span style='color:" + Colour.GENERIC_COMBAT.toWebHexString() + ";'>Combat</span> use")
							: (item.isAbleToBeUsedInSex()
									?"<span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Sex</span> use"
									:"Standard use"))
					+ "</div>"

					+ "<div class='description' style='height:104px'>" + UtilText.parse(item.getDescription()) + "</div>"
					
					
					+ "<div class='subTitle'>" + UtilText.formatAsMoney(item.getValue()) + "</div>");

			if (InventoryDialogue.getInventoryNPC() != null && InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {
				if (owner.isPlayer()) {
					if (InventoryDialogue.getInventoryNPC().willBuy(item))
						tooltipSB.append("<div class='subTitle'>" + InventoryDialogue.getInventoryNPC().getName("The") + " offers " + UtilText.formatAsMoney(item.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier())) + "</div>");
					else
						tooltipSB.append(
								"<div class='subTitle'>" + "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>" + InventoryDialogue.getInventoryNPC().getName("The") + " will not buy this</span>" + "</div>");
				} else {
					if (InventoryDialogue.isBuyback()) {
						tooltipSB.append("<div class='subTitle'>" + InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney(getBuybackPriceFor(item)) + "</div>");
					} else {
						tooltipSB.append("<div class='subTitle'>" + InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney(item.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier())) + "</div>");
					}
					
				}
			}

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (weapon != null || (coreItem instanceof AbstractWeapon)) {
			if(coreItem != null)
				weapon = (AbstractWeapon) coreItem;

			weaponTooltip(weapon);

		} else if (clothing != null || (coreItem instanceof AbstractClothing)) {
			if(coreItem != null)
				clothing = (AbstractClothing) coreItem;
			
			clothingTooltip(clothing);

		} else if (genericItem != null) {
			Main.mainController.setTooltipSize(360, 60);
			Main.mainController.setTooltipContent(UtilText.parse("<div class='title'>" + Util.capitaliseSentence(genericItem.getName(false)) + "</div>"));
			
		} else if (genericClothing != null) {

			Main.mainController.setTooltipSize(360, 446);

			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title' style='color:" + genericClothing.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(genericClothing.getName()) + "</div>"

					+ "<div class='subTitle'>" + Util.capitaliseSentence(colour.getName()) + "</div>"

					+ "<div class='picture full' style='position:relative;'>" + genericClothing.getSVGImage(colour) + "</div>");

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (genericWeapon != null) {

			Main.mainController.setTooltipSize(360, 446);

			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title' style='color:" + genericWeapon.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(genericWeapon.getName()) + "</div>"

					+ "<div class='subTitle'>" + Util.capitaliseSentence(dt.getName()) + "</div>"

					+ "<div class='picture full'>" + genericWeapon.getSVGStringMap().get(dt) + "</div>");

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (invSlot != null) {
			if (invSlot == InventorySlot.WEAPON_MAIN) {

				if (equippedToCharacter != null) {
					if (equippedToCharacter.getMainWeapon() == null) {
						Main.mainController.setTooltipSize(360, 60);
						Main.mainController.setTooltipContent("<div class='title'>Main Weapon</div>");

					} else {
						weaponTooltip(equippedToCharacter.getMainWeapon());
					}
				} else {
					Main.mainController.setTooltipSize(360, 60);
					Main.mainController.setTooltipContent("<div class='title'>Main Weapon</div>");
				}

			} else if (invSlot == InventorySlot.WEAPON_OFFHAND) {

				if (equippedToCharacter != null) {
					if (equippedToCharacter.getOffhandWeapon() == null) {
						Main.mainController.setTooltipSize(360, 60);
						Main.mainController.setTooltipContent("<div class='title'>Offhand Weapon</div>");

					} else {
						weaponTooltip(equippedToCharacter.getOffhandWeapon());
					}
				} else {
					Main.mainController.setTooltipSize(360, 60);
					Main.mainController.setTooltipContent("<div class='title'>Offhand Weapon</div>");
				}

			} else {

				if (equippedToCharacter != null) {
					if (equippedToCharacter.getClothingInSlot(invSlot) == null) {
						
						List<String> clothingBlockingThisSlot = new ArrayList<>();
						for (AbstractClothing c : equippedToCharacter.getClothingCurrentlyEquipped())
							if (c.getClothingType().getIncompatibleSlots().contains(invSlot))
								clothingBlockingThisSlot.add(c.getName());

						if (!clothingBlockingThisSlot.isEmpty()) {
							setBlockedTooltipContent("This slot is currently <b style='color:" + Colour.SEALED.toWebHexString() + ";'>blocked</b> by your " + Util.stringsToStringList(clothingBlockingThisSlot, false) + ".");
							
						} else if (invSlot.slotBlockedByRace(equippedToCharacter) != null) {
							setBlockedTooltipContent(invSlot.getCannotBeWornDescription(equippedToCharacter));
							
						} else {
							
							boolean piercingBlocked=false;
							
							switch(invSlot){
								case PIERCING_VAGINA:
									if(equippedToCharacter.getVaginaType()==VaginaType.NONE) {
										setBlockedTooltipContent("You don't have a vagina.");
										piercingBlocked=true;
									} else if(!equippedToCharacter.isPiercedVagina()) {
										setBlockedTooltipContent("Your vagina has not been pierced.");
										piercingBlocked=true;
									}
									break;
								case PIERCING_EAR:
									if(!equippedToCharacter.isPiercedEar()) {
										setBlockedTooltipContent("Your ears have not been pierced.");
										piercingBlocked=true;
									}
									break;
								case PIERCING_LIP:
									if(!equippedToCharacter.isPiercedLip()) {
										setBlockedTooltipContent("Your lips have not been pierced.");
										piercingBlocked=true;
									}
									break;
								case PIERCING_NIPPLE:
									if(!equippedToCharacter.isPiercedNipple()) {
										setBlockedTooltipContent("Your nipples have not been pierced.");
										piercingBlocked=true;
									}
									break;
								case PIERCING_NOSE:
									if(!equippedToCharacter.isPiercedNose()) {
										setBlockedTooltipContent("Your nose has not been pierced.");
										piercingBlocked=true;
									}
									break;
								case PIERCING_PENIS:
									if(equippedToCharacter.getPenisType()==PenisType.NONE) {
										setBlockedTooltipContent("You don't have a penis.");
										piercingBlocked=true;
									} else if(!equippedToCharacter.isPiercedPenis()) {
										setBlockedTooltipContent("Your penis has not been pierced.");
										piercingBlocked=true;
									}
									break;
								case PIERCING_STOMACH:
									if(!equippedToCharacter.isPiercedNavel()) {
										setBlockedTooltipContent("Your navel has not been pierced.");
										piercingBlocked=true;
									}
									break;
								case PIERCING_TONGUE:
									if(!equippedToCharacter.isPiercedTongue()) {
										setBlockedTooltipContent("Your tongue has not been pierced.");
										piercingBlocked=true;
									}
									break;
								default:
									break;
							}
							
							if(!piercingBlocked){
								Main.mainController.setTooltipSize(360, 60);
								Main.mainController.setTooltipContent(UtilText.parse("<div class='title'>" + Util.capitaliseSentence(invSlot.getName()) + "</div>"));
							}
						}

					} else {
						clothingTooltip(equippedToCharacter.getClothingInSlot(invSlot));
					}
				} else {
					Main.mainController.setTooltipSize(360, 60);
					Main.mainController.setTooltipContent(UtilText.parse("<div class='title'>" + Util.capitaliseSentence(invSlot.getName()) + "</div>"));
				}
			}
			
		} else if (enchantmentModifier != null) {
			Main.mainController.setTooltipSize(360, 174);
			Main.mainController.setTooltipContent(UtilText.parse(
					"<div class='title' style='color:"+enchantmentModifier.getRarity().getColour().toWebHexString()+";'>"
							+ Util.capitaliseSentence(enchantmentModifier.getName())
					+ "</div>"
					+ "<div class='description' style='height:48px'>"
					+ UtilText.parse(enchantmentModifier.getDescription())
					+ "</div>"
					+ "<div class='subTitle'>"
							+ "<b style='color:"+EnchantmentDialogue.ingredient.getRelatedEssence().getColour().toWebHexString() + ";'>+"
								+ enchantmentModifier.getValue()+" "+EnchantmentDialogue.ingredient.getRelatedEssence().getName()+"</b> essence cost"
					+ "</div>"));
		
		} else if (essence != null) {
			Main.mainController.setTooltipSize(360, 60);
			Main.mainController.setTooltipContent(UtilText.parse("<div class='title'><b style='color:"+essence.getColour().toWebHexString()+";'>" + Util.capitaliseSentence(essence.getName()) + "</b> essence</div>"));
		
		}  else {
			return;
		}

		(new Thread(new TooltipUpdateThread())).start();
		// Main.mainController.getTooltip().show(Main.primaryStage);
	}

	private void setBlockedTooltipContent(String description){
		Main.mainController.setTooltipSize(360, 164);
		Main.mainController.setTooltipContent(UtilText.parse(
				"<div class='title'>" + Util.capitaliseSentence(invSlot.getName()) + ": <span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Blocked!</span></div>"
				+ "<div class='description'>" + UtilText.parse(description) + "</div>"));
	}
	
	
	public InventoryTooltipEventListener setCoreItem(AbstractCoreItem coreItem, GameCharacter owner, GameCharacter equippedToCharacter) {
		resetVariables();
		this.coreItem = coreItem;
		this.equippedToCharacter = equippedToCharacter;
		this.owner = owner;
		return this;
	}
	
	public InventoryTooltipEventListener setItem(AbstractItem item, GameCharacter owner, GameCharacter equippedToCharacter) {
		resetVariables();
		this.item = item;
		this.equippedToCharacter = equippedToCharacter;
		this.owner = owner;
		return this;
	}
	
	public InventoryTooltipEventListener setGenericItem(AbstractItemType genericItem) {
		resetVariables();
		this.genericItem = genericItem;
		return this;
	}

	public InventoryTooltipEventListener setClothing(AbstractClothing clothing, GameCharacter owner, GameCharacter equippedToCharacter) {
		resetVariables();
		this.clothing = clothing;
		this.equippedToCharacter = equippedToCharacter;
		this.owner = owner;
		return this;
	}

	public InventoryTooltipEventListener setGenericClothing(AbstractClothingType genericClothing, Colour colour) {
		resetVariables();
		this.genericClothing = genericClothing;
		this.colour = colour;
		return this;
	}

	public InventoryTooltipEventListener setGenericWeapon(AbstractWeaponType genericWeapon, DamageType dt) {
		resetVariables();
		this.genericWeapon = genericWeapon;
		this.dt = dt;
		return this;
	}

	public InventoryTooltipEventListener setWeapon(AbstractWeapon weapon, GameCharacter equippedToCharacter) {
		resetVariables();
		this.weapon = weapon;
		this.equippedToCharacter = equippedToCharacter;
		this.owner = equippedToCharacter;
		return this;
	}

	public InventoryTooltipEventListener setInventorySlot(InventorySlot invSlot, GameCharacter equippedToCharacter) {
		resetVariables();
		this.invSlot = invSlot;
		this.equippedToCharacter = equippedToCharacter;
		this.owner = equippedToCharacter;
		return this;
	}
	
	public InventoryTooltipEventListener setTFModifier(TFModifier enchantmentModifier) {
		resetVariables();
		this.enchantmentModifier = enchantmentModifier;
		return this;
	}
	
	public InventoryTooltipEventListener setEssence(TFEssence essence) {
		resetVariables();
		this.essence = essence;
		return this;
	}

	private void resetVariables() {
		owner = null;
		equippedToCharacter = null;
		coreItem = null;
		item = null;
		genericItem = null;
		weapon = null;
		genericWeapon = null;
		dt = null;
		clothing = null;
		colour = null;
		genericClothing = null;
		invSlot = null;
		enchantmentModifier = null;
		essence = null;
	}

	private void weaponTooltip(AbstractWeapon absWep) {
		int yIncrease = 0;
		if (absWep.getAttributeModifiers().size() > absWep.getSpells().size())
			yIncrease = absWep.getAttributeModifiers().size();
		else
			yIncrease = absWep.getSpells().size() - 1;

		if (InventoryDialogue.getInventoryNPC() != null && InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {
			yIncrease += 2;
		}

		Main.mainController.setTooltipSize(360, 336 + (yIncrease * 20));

		// Core information:
		tooltipSB.setLength(0);
		tooltipSB.append("<div class='title'>" + Util.capitaliseSentence(absWep.getDisplayName(true)) + "</div>"

				+ "<div class='subTitle-half'>" + Util.capitaliseSentence(absWep.getDamageType().getName()) + " damage</b></br>"
				+ (absWep.getWeaponType().getSlot() == InventorySlot.WEAPON_MAIN ? "Main Weapon" : "Offhand Weapon") + "</div>" + "<div class='subTitle-half'>");

		if (equippedToCharacter != null) {
			tooltipSB.append("<b>" + Attack.getMinimumDamage(owner, null, absWep.getWeaponType().getSlot() == InventorySlot.WEAPON_MAIN ? Attack.MAIN : Attack.OFFHAND, absWep)
						+ " - " + Attack.getMaximumDamage(owner, null, absWep.getWeaponType().getSlot() == InventorySlot.WEAPON_MAIN ? Attack.MAIN : Attack.OFFHAND, absWep) + "</b>"
						+ "</br><b style='color:" + absWep.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(absWep.getDamageType().getName()) + "</b> damage");
		} else {
			tooltipSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), null, absWep.getWeaponType().getSlot() == InventorySlot.WEAPON_MAIN ? Attack.MAIN : Attack.OFFHAND, absWep)
					+ " - " + Attack.getMaximumDamage(Main.game.getPlayer(), null, absWep.getWeaponType().getSlot() == InventorySlot.WEAPON_MAIN ? Attack.MAIN : Attack.OFFHAND, absWep) + "</b>"
					+ "</br><b style='color:" + absWep.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>"
					+ Util.capitaliseSentence(absWep.getDamageType().getName()) + "</b> damage");
		}
		
		tooltipSB.append("</div>"

				+ "<div class='description'>" + UtilText.parse(absWep.getWeaponType().getDescription()) + "</div>");

		// Bonus attributes and spells granted:
		tooltipSB.append("<div class='subTitle-half'>");
		if (absWep.getAttributeModifiers().isEmpty())
			tooltipSB.append("<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>No bonuses</span>");
		else {
			tooltipSB.append("<b>" + Util.capitaliseSentence(absWep.getCoreEnchantment().getPositiveEnchantment()) + "</b>");
			for (Entry<Attribute, Integer> e : absWep.getAttributeModifiers().entrySet())
				tooltipSB.append("</br>+" + e.getValue() + " <span style='color:" + e.getKey().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(e.getKey().getAbbreviatedName()) + "</span>");
		}
		tooltipSB.append("</div>");
		tooltipSB.append("<div class='subTitle-half'>");
		if (absWep.getSpells().isEmpty())
			tooltipSB.append("<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>No spells</span>");
		else {
			tooltipSB.append("<b>Spells</b>");
			for (Spell s : absWep.getSpells())
				tooltipSB.append("</br><span style='color:" + s.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(s.getName()) + "</span>");
		}
		tooltipSB.append("</div>");

		// Value:
		tooltipSB.append("<div class='subTitle'>" + UtilText.formatAsMoney(absWep.getValue()) + "</div>");

		if (InventoryDialogue.getInventoryNPC() != null && InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {
			if (owner!=null && owner.isPlayer()) {
				if (InventoryDialogue.getInventoryNPC().willBuy(absWep))
					tooltipSB.append("<div class='subTitle'>" + InventoryDialogue.getInventoryNPC().getName("The") + " offers " + UtilText.formatAsMoney(absWep.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier())) + "</div>");
				else
					tooltipSB.append("<div class='subTitle'>" + "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>" + InventoryDialogue.getInventoryNPC().getName("The") + " will not buy this</span>" + "</div>");
			} else {
				if (InventoryDialogue.isBuyback()) {
					tooltipSB.append("<div class='subTitle'>" + InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney(getBuybackPriceFor(absWep)) + "</div>");
				} else {
					tooltipSB.append("<div class='subTitle'>" + InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney (absWep.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier())) + "</div>");
				}
				
			}
		}

		Main.mainController.setTooltipContent(tooltipSB.toString());
	}

	private void clothingTooltip(AbstractClothing absClothing) {
		int yIncrease = 0;

		if (!absClothing.getAttributeModifiers().isEmpty() && absClothing.isEnchantmentKnown()) {
			if (absClothing.getAttributeModifiers().size() >= absClothing.getExtraDescriptions(equippedToCharacter).size())
				yIncrease = absClothing.getAttributeModifiers().size();
			else
				yIncrease = absClothing.getExtraDescriptions(equippedToCharacter).size() - 1;
		} else {
			if (absClothing.getExtraDescriptions(equippedToCharacter).size() >= 1)
				yIncrease = absClothing.getExtraDescriptions(equippedToCharacter).size() - 1;
		}

		if (InventoryDialogue.getInventoryNPC() != null && InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {
			yIncrease += 2;
		}
		
		Main.mainController.setTooltipSize(360, 320 + (yIncrease * 20));

		// Core information:
		tooltipSB.setLength(0);
		tooltipSB.append("<div class='title'>" + Util.capitaliseSentence(absClothing.getDisplayName(true)) + "</div>"

				+ "<div class='subTitle-half'>" + Util.capitaliseSentence(absClothing.getClothingType().getSlot().getName()) + "</div>"

				+ "<div class='subTitle-half'>"
				+ (absClothing.getClothingType().getClothingSet() == null ? "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Not part of a set</span>" : absClothing.getClothingType().getClothingSet().getName() + " set") + "</div>"

				+ "<div class='description'>" + UtilText.parse(absClothing.getClothingType().getDescription()) + "</div>");

		// Bonus attributes and information:
		tooltipSB.append("<div class='subTitle-half'>");

		if (!absClothing.getAttributeModifiers().isEmpty()) {// Display enchantments:
			if (!absClothing.isEnchantmentKnown())
				tooltipSB.append("<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Unidentified!</span>");
			else {
				tooltipSB.append("<b>" + Util.capitaliseSentence(absClothing.isBadEnchantment() ? absClothing.getCoreEnchantment().getNegativeEnchantment() : absClothing.getCoreEnchantment().getPositiveEnchantment()) + "</b>");
				for (Entry<Attribute, Integer> e : absClothing.getAttributeModifiers().entrySet())
					tooltipSB.append("</br>" + (e.getValue() < 0 ? "<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>" : "<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>+") + e.getValue()
							+ "</span> <span style='color:" + e.getKey().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(e.getKey().getAbbreviatedName()) + "</span>");
			}
		} else {
			tooltipSB.append("<b>Ordinary</b>");
		}
		// Display resistance:
		tooltipSB.append(absClothing.getClothingType().getPhysicalResistance() == 0
				? "</br><span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>0 "+ Attribute.RESISTANCE_PHYSICAL.getAbbreviatedName() +"</span>"
				: "</br><span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>+" + absClothing.getClothingType().getPhysicalResistance() + "</span>" + " <span style='color:" + Attribute.RESISTANCE_PHYSICAL.getColour().toWebHexString()
						+ ";'>" + Attribute.RESISTANCE_PHYSICAL.getAbbreviatedName() + "</span>");

		tooltipSB.append("</div>");

		tooltipSB.append("<div class='subTitle-half'>");
		if (absClothing.getExtraDescriptions(equippedToCharacter).isEmpty())
			tooltipSB.append("<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>No Status</span>");
		else {
			tooltipSB.append("<b>Status</b>");
			for (String s : absClothing.getExtraDescriptions(equippedToCharacter))
				tooltipSB.append("</br>" + s);
		}
		tooltipSB.append("</div>");

		// Value:
		tooltipSB.append("<div class='subTitle'>" + (absClothing.isEnchantmentKnown() ? UtilText.formatAsMoney(absClothing.getValue()) : "?") + "</div>");

		if (InventoryDialogue.getInventoryNPC() != null && InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {
			if (owner.isPlayer()) {
				if (InventoryDialogue.getInventoryNPC().willBuy(absClothing))
					tooltipSB.append("<div class='subTitle'>" + InventoryDialogue.getInventoryNPC().getName("The") + " offers " + UtilText.formatAsMoney(absClothing.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier())) + "</div>");
				else
					tooltipSB.append("<div class='subTitle'>" + "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>" + InventoryDialogue.getInventoryNPC().getName("The") + " will not buy this</span>" + "</div>");
			} else {
				if (InventoryDialogue.isBuyback()) {
					tooltipSB.append("<div class='subTitle'>" + InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney( + getBuybackPriceFor(absClothing)) + "</div>");
				} else {
					tooltipSB.append("<div class='subTitle'>" + InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney(absClothing.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier())) + "</div>");
				}
			}
		}

		Main.mainController.setTooltipContent(tooltipSB.toString());
	}
	
	private int getBuybackPriceFor(AbstractCoreItem item) {
	    for (ShopTransaction s : Main.game.getPlayer().getBuybackStack()) {
	        if (s.getAbstractItemSold() == item) {
	            return s.getPrice();
	        }
	    }
	    throw new IllegalArgumentException("That's not a buyback item");
	}
}
