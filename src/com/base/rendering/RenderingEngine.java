package com.base.rendering;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.base.game.KeyboardAction;
import com.base.game.character.GameCharacter;
import com.base.game.character.attributes.ArousalLevel;
import com.base.game.character.attributes.Attribute;
import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.attributes.FitnessLevel;
import com.base.game.character.attributes.IntelligenceLevel;
import com.base.game.character.attributes.StrengthLevel;
import com.base.game.character.body.types.PenisType;
import com.base.game.character.body.types.VaginaType;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.character.effects.Fetish;
import com.base.game.character.effects.StatusEffect;
import com.base.game.character.npc.NPC;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.MapDisplay;
import com.base.game.inventory.InventorySlot;
import com.base.game.inventory.Rarity;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.sex.Sex;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.utils.Vector2i;
import com.base.world.places.GenericPlace;

/**
 * @since 0.1.0
 * @version 0.1.84
 * @author Innoxia
 */
public enum RenderingEngine {
	ENGINE;

	private GameCharacter charactersInventoryToRender;
	private static boolean zoomedIn = true, renderedDisabledMap = false;

	private RenderingEngine() {
	}

	private StringBuilder inventorySB = new StringBuilder();
	
	/**
	 * Rendering method for the bottom-left inventory screen.
	 */
	public void renderInventory() {

		if (Main.mainController.getWebEngineInventory() != null) {
			// if(charactersInventoryToRender==null){
			// charactersInventoryToRender=Main.game.getPlayer();
			// }

			// long t1 = System.nanoTime();

			//webEngine.setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewInventory_stylesheet.css").toExternalForm());

			inventorySB.setLength(0);

			AbstractClothing clothing;

			Set<InventorySlot> blockedSlots = new HashSet<>();
			
			if(charactersInventoryToRender==null)
				if(Main.game.getPlayer()!=null)
					charactersInventoryToRender=Main.game.getPlayer();
			
			if (charactersInventoryToRender != null) {
				for (AbstractClothing c : charactersInventoryToRender.getClothingCurrentlyEquipped())
					if (c.getClothingType().getIncompatibleSlots() != null)
						for (InventorySlot is : c.getClothingType().getIncompatibleSlots())
							blockedSlots.add(is);
			}

			inventorySB.append("<body style='margin: 0; padding: 0;'>");

			// EQUIPPED:
			inventorySB.append("<section class='equipped'>");
			// Draw each inventory slot:
			InventorySlot[] inventorySlots = { InventorySlot.EYES, InventorySlot.HEAD, InventorySlot.MOUTH, InventorySlot.NECK, InventorySlot.TORSO, InventorySlot.CHEST, InventorySlot.WRIST, InventorySlot.STOMACH, InventorySlot.HAND,
					InventorySlot.FINGER, InventorySlot.LEG, InventorySlot.GROIN, InventorySlot.ANKLE, InventorySlot.FOOT, InventorySlot.SOCK };
			int counter = 1;
			for (InventorySlot invSlot : inventorySlots) {
				if (counter == 1)
					inventorySB.append("<section class='display'>");

				if (charactersInventoryToRender != null)
					clothing = charactersInventoryToRender.getClothingInSlot(invSlot);
				else
					clothing = null;

				if (clothing != null) {
					// add to content:
					inventorySB.append(
							// If slot is sealed:
							"<div class='equipSlot"
									+ (clothing.getRarity() == Rarity.COMMON ? " common" : "")
									+ (clothing.getRarity() == Rarity.UNCOMMON ? " uncommon" : "")
									+ (clothing.getRarity() == Rarity.RARE ? " rare" : "")
									+ (clothing.getRarity() == Rarity.EPIC ? " epic" : "")
									+ (clothing.getRarity() == Rarity.LEGENDARY ? " legendary" : "")
									+ (clothing.getRarity() == Rarity.JINXED ? " jinxed" : "") + "'"
									+ (clothing.isSealed() ? "style='height:16vw; width:16vw; border-width:1vw; border-color:#" + Colour.SEALED.toWebHexString() + "; border-style:solid;'" : "") + ">"
									// If clothing is displaced:
									+ (!clothing.getDisplacedList().isEmpty() ? "<div class='displacedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getDisplacedIcon() + "</div>" : "")
									// If clothing is cummed in:
									+ (clothing.isDirty() ? "<div class='cummedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getCummedInIcon() + "</div>" : "")
									// If clothing is too masculine:
									+ (clothing.getClothingType().getFemininityMaximum() < charactersInventoryToRender.getFemininity() ? "<div class='femininityIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getMasculineWarningIcon() + "</div>" : "")
									// If clothing is too feminine:
									+ (clothing.getClothingType().getFemininityMinimum() > charactersInventoryToRender.getFemininity() ? "<div class='femininityIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getFeminineWarningIcon() + "</div>" : "")

									// Picture:
									+ clothing.getSVGString()

									+ "<div class='overlay' id='" + invSlot.toString() + "Slot'>" + "</div>" + "</div>");

				} else {
					// add to content:
					if (blockedSlots.contains(invSlot))
						inventorySB.append("<div class='equipSlot'><div class='overlay disabled' id='" + invSlot.toString() + "Slot'></div></div>");
					else if (invSlot.slotBlockedByRace(charactersInventoryToRender) != null)
						inventorySB.append("<div class='equipSlot'>"
										+"<div class='overlay disabled' id='" + invSlot.toString() + "Slot'></div>"
										+ "<div class='raceBlockIcon'>" + invSlot.slotBlockedByRace(charactersInventoryToRender).getStatusEffect().getSVGString(charactersInventoryToRender) + "</div>"
										+ "</div>");
					else
						inventorySB.append("<div class='equipSlot' id='" + invSlot.toString() + "Slot'></div>");
				}

				if (counter == 3) {
					inventorySB.append("</section>");
					counter = 1;
				} else
					counter++;
			}

			inventorySB.append("</section>");

			// Right panel:
			inventorySB.append("<section class='accessories'>");

			// Weapons:
			inventorySB.append("<section class='display weapons'>");
			// Main weapon:
			if (charactersInventoryToRender != null) {
				if (charactersInventoryToRender.getMainWeapon() != null) {
					inventorySB.append("<div class='equipSlot weapon"
							+ (charactersInventoryToRender.getMainWeapon().getRarity() == Rarity.COMMON ? " common" : "")
							+ (charactersInventoryToRender.getMainWeapon().getRarity() == Rarity.UNCOMMON ? " uncommon" : "")
							+ (charactersInventoryToRender.getMainWeapon().getRarity() == Rarity.RARE ? " rare" : "")
							+ (charactersInventoryToRender.getMainWeapon().getRarity() == Rarity.EPIC ? " epic" : "")
							+ (charactersInventoryToRender.getMainWeapon().getRarity() == Rarity.LEGENDARY ? " legendary" : "")
							+ (charactersInventoryToRender.getMainWeapon().getRarity() == Rarity.JINXED ? " jinxed" : "") + "'>"
							+ charactersInventoryToRender.getMainWeapon().getSVGString() + "<div class='overlay' id='" + InventorySlot.WEAPON_MAIN.toString()
							+ "Slot'></div>" + "</div>");
				} else {
					inventorySB.append("<div class='equipSlot weapon' id='" + InventorySlot.WEAPON_MAIN.toString() + "Slot'></div>");
				}
			} else {
				inventorySB.append("<div class='equipSlot weapon' id='" + InventorySlot.WEAPON_MAIN.toString() + "Slot'></div>");
			}
			// Offhand weapon:
			if (charactersInventoryToRender != null) {
				if (charactersInventoryToRender.getOffhandWeapon() != null) {
					inventorySB.append("<div class='equipSlot weapon"
							+ (charactersInventoryToRender.getOffhandWeapon().getRarity() == Rarity.COMMON ? " common" : "")
							+ (charactersInventoryToRender.getOffhandWeapon().getRarity() == Rarity.UNCOMMON ? " uncommon" : "")
							+ (charactersInventoryToRender.getOffhandWeapon().getRarity() == Rarity.RARE ? " rare" : "")
							+ (charactersInventoryToRender.getOffhandWeapon().getRarity() == Rarity.EPIC ? " epic" : "")
							+ (charactersInventoryToRender.getOffhandWeapon().getRarity() == Rarity.LEGENDARY ? " legendary" : "")
							+ (charactersInventoryToRender.getOffhandWeapon().getRarity() == Rarity.JINXED ? " jinxed" : "") + "'>"
							+ charactersInventoryToRender.getOffhandWeapon().getSVGString() + "<div class='overlay' id='"
							+ InventorySlot.WEAPON_OFFHAND.toString() + "Slot'></div>" + "</div>");
				} else {
					inventorySB.append("<div class='equipSlot weapon' id='" + InventorySlot.WEAPON_OFFHAND.toString() + "Slot'></div>");
				}
			} else {
				inventorySB.append("<div class='equipSlot weapon' id='" + InventorySlot.WEAPON_OFFHAND.toString() + "Slot'></div>");
			}
			inventorySB.append("</section>");

			inventorySB.append("<div class='weapon-spacer'></div>");

			// Piercings:
			inventorySB.append("<section class='display piercings'>");

			InventorySlot[] piercingSlots = {
					InventorySlot.PIERCING_EAR,
					InventorySlot.PIERCING_NOSE,
					InventorySlot.PIERCING_TONGUE,
					InventorySlot.PIERCING_LIP,
					InventorySlot.PIERCING_NIPPLE,
					InventorySlot.PIERCING_STOMACH,
					InventorySlot.PIERCING_VAGINA,
					InventorySlot.PIERCING_PENIS };
			for (InventorySlot invSlot : piercingSlots) {

				if (charactersInventoryToRender != null)
					clothing = charactersInventoryToRender.getClothingInSlot(invSlot);
				else
					clothing = null;

				if (clothing != null) {
					// add to content:
					inventorySB.append(
							// If slot is sealed:
							"<div class='equipSlot small "
									+ (clothing.getRarity() == Rarity.COMMON ? " common" : "")
									+ (clothing.getRarity() == Rarity.UNCOMMON ? " uncommon" : "")
									+ (clothing.getRarity() == Rarity.RARE ? " rare" : "")
									+ (clothing.getRarity() == Rarity.EPIC ? " epic" : "")
									+ (clothing.getRarity() == Rarity.LEGENDARY ? " legendary" : "")
									+ (clothing.getRarity() == Rarity.JINXED ? " jinxed" : "") + "'"
									+ (clothing.isSealed() ? "style='height:10vw;width:10vw;border-width:1vw;border-color:#" + Colour.SEALED.toWebHexString() + ";border-style:solid;'" : "") + ">"
									// If clothing is displaced:
									+ (!clothing.getDisplacedList().isEmpty() ? "<div class='displacedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getDisplacedIcon() + "</div>" : "")
									// If clothing is cummed in:
									+ (clothing.isDirty() ? "<div class='cummedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getCummedInIcon() + "</div>" : "")
									// If clothing is too masculine:
									+ (clothing.getClothingType().getFemininityMaximum() < charactersInventoryToRender.getFemininity() ? "<div class='femininityIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getMasculineWarningIcon() + "</div>" : "")
									// If clothing is too feminine:
									+ (clothing.getClothingType().getFemininityMinimum() > charactersInventoryToRender.getFemininity() ? "<div class='femininityIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getFeminineWarningIcon() + "</div>" : "")

									// Picture:
									+ clothing.getSVGString()

									+ "<div class='overlay' id='" + invSlot.toString() + "Slot'>" + "</div>" + "</div>");

				} else {
					// add to content:
					if (blockedSlots.contains(invSlot))
						inventorySB.append("<div class='equipSlot small'><div class='overlay disabled' id='" + invSlot.toString() + "Slot'>" + "</div></div>");
					else{
						switch(invSlot){
							case PIERCING_VAGINA:
								if(charactersInventoryToRender.getVaginaType()==VaginaType.NONE || !charactersInventoryToRender.isPiercedVagina())
									inventorySB.append("<div class='equipSlot small'><div class='overlay disabled' id='" + invSlot.toString() + "Slot'>" + "</div></div>");
								else
									inventorySB.append("<div class='equipSlot small' id='" + invSlot.toString() + "Slot'></div>");
								break;
							case PIERCING_EAR:
								if(!charactersInventoryToRender.isPiercedEar())
									inventorySB.append("<div class='equipSlot small'><div class='overlay disabled' id='" + invSlot.toString() + "Slot'>" + "</div></div>");
								else
									inventorySB.append("<div class='equipSlot small' id='" + invSlot.toString() + "Slot'></div>");
								break;
							case PIERCING_LIP:
								if(!charactersInventoryToRender.isPiercedLip())
									inventorySB.append("<div class='equipSlot small'><div class='overlay disabled' id='" + invSlot.toString() + "Slot'>" + "</div></div>");
								else
									inventorySB.append("<div class='equipSlot small' id='" + invSlot.toString() + "Slot'></div>");
								break;
							case PIERCING_NIPPLE:
								if(!charactersInventoryToRender.isPiercedNipple())
									inventorySB.append("<div class='equipSlot small'><div class='overlay disabled' id='" + invSlot.toString() + "Slot'>" + "</div></div>");
								else
									inventorySB.append("<div class='equipSlot small' id='" + invSlot.toString() + "Slot'></div>");
								break;
							case PIERCING_NOSE:
								if(!charactersInventoryToRender.isPiercedNose())
									inventorySB.append("<div class='equipSlot small'><div class='overlay disabled' id='" + invSlot.toString() + "Slot'>" + "</div></div>");
								else
									inventorySB.append("<div class='equipSlot small' id='" + invSlot.toString() + "Slot'></div>");
								break;
							case PIERCING_PENIS:
								if(charactersInventoryToRender.getPenisType()==PenisType.NONE || !charactersInventoryToRender.isPiercedPenis())
									inventorySB.append("<div class='equipSlot small'><div class='overlay disabled' id='" + invSlot.toString() + "Slot'>" + "</div></div>");
								else
									inventorySB.append("<div class='equipSlot small' id='" + invSlot.toString() + "Slot'></div>");
								break;
							case PIERCING_STOMACH:
								if(!charactersInventoryToRender.isPiercedNavel())
									inventorySB.append("<div class='equipSlot small'><div class='overlay disabled' id='" + invSlot.toString() + "Slot'>" + "</div></div>");
								else
									inventorySB.append("<div class='equipSlot small' id='" + invSlot.toString() + "Slot'></div>");
								break;
							case PIERCING_TONGUE:
								if(!charactersInventoryToRender.isPiercedTongue())
									inventorySB.append("<div class='equipSlot small'><div class='overlay disabled' id='" + invSlot.toString() + "Slot'>" + "</div></div>");
								else
									inventorySB.append("<div class='equipSlot small' id='" + invSlot.toString() + "Slot'></div>");
								break;
							default:
								break;
						}
					}
				}
			}
			
			inventorySB.append("</section>");
			
			inventorySB.append("<section class='display extra'>");
			inventorySB.append("<div class='equipSlot small' id='protectionSlot'>"
					+ (charactersInventoryToRender.isWearingCondom()?SVGImages.SVG_IMAGE_PROVIDER.getProtectionEnabled():SVGImages.SVG_IMAGE_PROVIDER.getProtectionDisabled())
					+ "</div>");
			inventorySB.append("<div class='equipSlot small' id='tattooSlot'>"
					+SVGImages.SVG_IMAGE_PROVIDER.getTattoo()
					+ "</div>");
			inventorySB.append("</section>");

			inventorySB.append("</section>");

			inventorySB.append("</body>");

			
			Main.mainController.setInventoryViewContent(inventorySB.toString());

			// System.out.println("Time to render inventory:
			// "+(System.nanoTime()-t1)/1000000000d);
		}

	}

	// DecimalFormat decimalFormatter = new DecimalFormat("#,###");
	private StringBuilder uiAttributeSB = new StringBuilder();

	private static float renderedStrengthValue = 0, renderedIntelligenceValue = 0, renderedFitnessValue = 0,
			renderedHealthValue = 0, renderedManaValue = 0, renderedStaminaValue = 0,
			renderedPlayerCorruptionValue = 0, renderedPartnerCorruptionValue = 0,
			renderedPlayerArousalValue = 0, renderedPartnerArousalValue = 0;

	private DialogueNodeOld renderedDialogueNode = null;

	public void renderAttributesPanel() {
		uiAttributeSB.setLength(0);

		// Top part: Name, experience.
		uiAttributeSB.append(
				"<body>"
					+ "<div class='attribute-container'>" 
						+ "<div class='full-width-container' style='margin:0;padding:0;'>"
							+ "<p class='player-name' style='color:"+ Femininity.valueOf(Main.game.getPlayer().getFemininity()).getColour().toWebHexString() + ";'>"
								+ (Main.game.getPlayer().getName().length() == 0 ? (Main.game.getPlayer().getFemininity() <= Femininity.MASCULINE.getMaximumFemininity() ? "Hero" : "Heroine") : Main.game.getPlayer().getName())
							+ "</p>"
							+ "<div class='overlay' id='EXTRA_ATTRIBUTES'></div>"
						+ "</div>"
	
						+ "<div class='full-width-container' style='margin:0;padding:0;'>"
							+ "<p style='text-align:center;'>"
								+ "<b>Level " + Main.game.getPlayer().getLevel()+ "</b> "
									+ (Main.game.getPlayer().getRaceStage().getName()!=""
										?"<b style='color:"+Main.game.getPlayer().getRaceStage().getColour().toWebHexString()+";'>" + Util.capitaliseSentence(Main.game.getPlayer().getRaceStage().getName())+"</b> ":"")
									+ "<b style='color:"+Main.game.getPlayer().getRace().getColour().toWebHexString()+";'>"
									+ (Main.game.getPlayer().isFeminine()?Util.capitaliseSentence(Main.game.getPlayer().getRace().getSingularFemaleName()):Util.capitaliseSentence(Main.game.getPlayer().getRace().getSingularMaleName()))
									+ "</b>"
							+"</p>"
							+ "<div class='barBackgroundExp'>"
							+ (Main.game.getPlayer().getLevel() != 20
								? "<div style=' mix-blend-mode: difference; width:" + (Main.game.getPlayer().getExperience() / (Main.game.getPlayer().getLevel() * 10f)) * 90 + "vw; height:2vw; background:" + Colour.CLOTHING_BLUE_LIGHT.toWebHexString()
										+ "; float:left; border-radius: 2;'></div>"
								: "<div style=' mix-blend-mode: difference; width:90vw; height:2vw; background:" + Colour.GENERIC_EXCELLENT.toWebHexString() + "; float:left; border-radius: 2;'></div>")
							+ "</div>"
							+ "<div class='overlay' id='PLAYER_" + Attribute.EXPERIENCE.getName() + "' style='cursor:pointer;'></div>"
						+ "</div>"
					+ "</div>");

		// Attribute display:
		// In sex:
		if(Main.game.isInSex()) {
			
			uiAttributeSB.append(
					"<div class='attribute-container'>"
						+ "<p style='text-align:center;padding:0;margin:0;'>"
							+ "<b style='color:"+Femininity.valueOf(Main.game.getPlayer().getFemininity()).getColour().toWebHexString()+";'>"+Main.game.getPlayer().getName()+"</b>"
							+ "</br>"
							+ "<b>"+(Sex.isPlayerDom()?"Dom":"Sub")+":</b> <b style='color:"+Sex.getSexPacePlayer().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(Sex.getSexPacePlayer().getName())+"</b> | <b>Orgasms:</b> "
								+(Sex.getNumberOfPlayerOrgasms()==0
									?"<b>0</b>"
									:"<b style='"+Colour.GENERIC_ARCANE.toWebHexString()+"'>"+Sex.getNumberOfPlayerOrgasms()+"</b>")
						+ "</p>"
			
						// Arousal:
						+ "<div class='full-width-container' style='margin:8 0 0 0; margin:0; padding:0;'>"
							+ "<div class='statusEffectBackground'>" + ArousalLevel.getArousalLevelFromValue(Main.game.getPlayer().getArousal()).getRelatedStatusEffect().getSVGString(Main.game.getPlayer()) + "</div>"
							+ "<div class='barBackgroundAtt'>"
								+ "<div style='width:" + Main.game.getPlayer().getArousal() * 0.65 + "vw; height:5vw; background:"
									+ ArousalLevel.getArousalLevelFromValue(Main.game.getPlayer().getArousal()).getColour().toWebHexString() + "; float:left; border-radius: 2;'></div>"
							+ "</div>"
							+ "<p style='text-align:center; margin:2vw 0 0 0; padding:0;color:"+ (renderedPlayerArousalValue < Main.game.getPlayer().getArousal()
									? (Colour.GENERIC_GOOD.toWebHexString())
									: (renderedPlayerArousalValue > Main.game.getPlayer().getArousal() ? (Colour.GENERIC_BAD.toWebHexString()) : "default"))+ ";'>"
									+ (int) Math.ceil(Main.game.getPlayer().getArousal())
							+ "</p>"
							+ "<div class='overlay' id='PLAYER_" + Attribute.AROUSAL.getName() + "'></div>"
						+ "</div>"
						
						// Corruption:
						+ "<div class='full-width-container' style='padding:0;'>"
							+ "<div class='statusEffectBackground'>"
								+ CorruptionLevel.getCorruptionLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer()) + "</div>"
							+ "<div class='barBackgroundAtt corruption'>"
								+ "<div style='width:" + Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION) * 0.65 + "vw; height:5vw; background:"
									+ Attribute.CORRUPTION.getColour().toWebHexString() + "; float:left; border-radius: 2;'></div>"
							+ "</div>"
							+ "<p style='text-align:center; margin:2vw 0 0 0; padding:0;color:" + (renderedPlayerCorruptionValue < Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION)
									? (Colour.GENERIC_GOOD.toWebHexString())
									: (renderedPlayerCorruptionValue > Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION) ? (Colour.GENERIC_BAD.toWebHexString()) : "default")) + ";'>"
									+ (int) Math.ceil(Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION))
							+ "</p>"
							+ "<div class='overlay' id='PLAYER_" + Attribute.CORRUPTION.getName() + "'></div>"
						+ "</div>");
							
			// Status effects:
//			uiAttributeSB.append("<p style='text-align:center;padding:0;margin:0;'><b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Sex Effects</b></p>");
			
			boolean hasStatusEffects = false;
			for (Fetish f : Main.game.getPlayer().getFetishes()) {
				uiAttributeSB.append(
						"<div class='statusEffectBackground'>"
								+ f.getSVGString()
								+ "<div class='overlay' id='FETISH_PLAYER_" + f + "'></div>"
						+ "</div>");
				hasStatusEffects = true;
			}
			
			for (StatusEffect se : Main.game.getPlayer().getStatusEffects()) {
				if (se.isSexEffect()) {
					uiAttributeSB.append(
							"<div class='statusEffectBackground'>"
									+ se.getSVGString(Main.game.getPlayer())
									+ "<div class='overlay' id='SE_PLAYER_" + se + "'></div>"
							+ "</div>");
					hasStatusEffects = true;
				}
			}
			if(!hasStatusEffects) {
				uiAttributeSB.append("<p style='text-align:center;padding:0;margin:0;height:12vw;'><b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No sex effects</b></p>");
			}
					
			uiAttributeSB.append("</div>"
							
					+ "<div class='attribute-container'>"
						+ "<div class='full-width-container' style='margin:0;padding:0;'>"
							+ "<p style='text-align:center;padding:0;margin:0;'><b style='color:"+Femininity.valueOf(Sex.getPartner().getFemininity()).getColour().toWebHexString()+";'>"
								+Util.capitaliseSentence(Sex.getPartner().getName())+"</b></p>"
							+ "<p style='text-align:center;'>"
								+ (Sex.getPartner().getRaceStage().getName()!=""
									?"<b style='color:"+Sex.getPartner().getRaceStage().getColour().toWebHexString()+";'>" + Util.capitaliseSentence(Sex.getPartner().getRaceStage().getName())+"</b> ":"")
								+ "<b style='color:"+Sex.getPartner().getRace().getColour().toWebHexString()+";'>"
								+ (Sex.getPartner().isFeminine()?Util.capitaliseSentence(Sex.getPartner().getRace().getSingularFemaleName()):Util.capitaliseSentence(Sex.getPartner().getRace().getSingularMaleName()))
								+ "</b>"
							+"</p>"
							+ "<div class='overlay' id='PARTNER_"+Attribute.EXPERIENCE.getName()+"'></div>"
						+ "</div>"
							
						+ "<p style='text-align:center;padding:0;margin:0;'>"
							+ "<b>"+(!Sex.isPlayerDom()?"Dom":"Sub")+":</b> <b style='color:"+Sex.getSexPacePartner().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(Sex.getSexPacePartner().getName())+"</b> | <b>Orgasms:</b> "
								+(Sex.getNumberOfPartnerOrgasms()==0
									?"<b>0</b>"
									:"<b style='"+Colour.GENERIC_ARCANE.toWebHexString()+"'>"+Sex.getNumberOfPartnerOrgasms()+"</b>")
						+ "</p>"
							
						// Partner arousal:
						+ "<div class='full-width-container' style='margin:8 0 0 0; margin:0; padding:0;'>"
							+ "<div class='statusEffectBackground'>" + ArousalLevel.getArousalLevelFromValue(Sex.getPartner().getArousal()).getRelatedStatusEffect().getSVGString(Sex.getPartner()) + "</div>"
							+ "<div class='barBackgroundAtt'>"
								+ "<div style='width:" + Sex.getPartner().getArousal() * 0.65 + "vw; height:5vw; background:"
									+ ArousalLevel.getArousalLevelFromValue(Sex.getPartner().getArousal()).getColour().toWebHexString() + "; float:left; border-radius: 2;'></div>"
							+ "</div>"
							+ "<p style='text-align:center; margin:2vw 0 0 0; padding:0;color:"+ (renderedPartnerArousalValue < Sex.getPartner().getArousal()
									? (Colour.GENERIC_GOOD.toWebHexString())
									: (renderedPartnerArousalValue > Sex.getPartner().getArousal() ? (Colour.GENERIC_BAD.toWebHexString()) : "default"))+ ";'>"
									+ (int) Math.ceil(Sex.getPartner().getArousal())
							+ "</p>"
							+ "<div class='overlay' id='PARTNER_" + Attribute.AROUSAL.getName() + "'></div>"
						+ "</div>"
						
						// Corruption:
						+ "<div class='full-width-container' style='padding:0;'>"
							+ "<div class='statusEffectBackground'>"
								+ CorruptionLevel.getCorruptionLevelFromValue(Sex.getPartner().getAttributeValue(Attribute.CORRUPTION)).getRelatedStatusEffect().getSVGString(Sex.getPartner()) + "</div>"
							+ "<div class='barBackgroundAtt corruption'>"
								+ "<div style='width:" + Sex.getPartner().getAttributeValue(Attribute.CORRUPTION) * 0.65 + "vw; height:5vw; background:"
									+ Attribute.CORRUPTION.getColour().toWebHexString() + "; float:left; border-radius: 2;'></div>"
							+ "</div>"
							+ "<p style='text-align:center; margin:2vw 0 0 0; padding:0;color:" + (renderedPartnerCorruptionValue < Sex.getPartner().getAttributeValue(Attribute.CORRUPTION)
									? (Colour.GENERIC_GOOD.toWebHexString())
									: (renderedPartnerCorruptionValue > Sex.getPartner().getAttributeValue(Attribute.CORRUPTION) ? (Colour.GENERIC_BAD.toWebHexString()) : "default")) + ";'>"
									+ (int) Math.ceil(Sex.getPartner().getAttributeValue(Attribute.CORRUPTION))
							+ "</p>"
							+ "<div class='overlay' id='PARTNER_" + Attribute.CORRUPTION.getName() + "'></div>"
						+ "</div>");
			
				// Status effects:
//				uiAttributeSB.append("<p style='text-align:center;padding:0;margin:0;'><b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Sex Effects</b></p>");
				
				hasStatusEffects = false;
				for (Fetish f : Sex.getPartner().getFetishes()) {
					uiAttributeSB.append(
							"<div class='statusEffectBackground'>"
									+ f.getSVGString()
									+ "<div class='overlay' id='FETISH_PARTNER_" + f + "'></div>"
							+ "</div>");
					hasStatusEffects = true;
				}
				
				for (StatusEffect se : Sex.getPartner().getStatusEffects()) {
					if (se.isSexEffect()) {
						uiAttributeSB.append(
								"<div class='statusEffectBackground'>"
										+ se.getSVGString(Sex.getPartner())
										+ "<div class='overlay' id='SE_PARTNER_" + se + "'></div>"
								+ "</div>");
						hasStatusEffects = true;
					}
				}
				if(!hasStatusEffects) {
					uiAttributeSB.append("<p style='text-align:center;padding:0;margin:0;height:12vw;'><b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No sex effects</b></p>");
				}
							
			uiAttributeSB.append("</div>");
			
			
		} else {
		
			if(Main.mainController.getWebViewAttributes().getHeight()>=470) {
				uiAttributeSB.append(
						"<div class='attribute-container'>" + "<p style='text-align:center;padding:0;margin:0;'><b>Attributes</b></p>"
				
							// Strength:
							+ "<div class='full-width-container' style='margin:8 0 0 0; margin:0; padding:0;'>"
								+ "<div class='statusEffectBackground'>"
									+ StrengthLevel.getStrengthLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer()) + "</div>"
								+ "<div class='barBackgroundAtt'>"
									+ "<div style='width:" + Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH) * 0.65 + "vw; height:5vw; background:" + Colour.GENERIC_ATTRIBUTE.toWebHexString() + "; float:left; border-radius: 2;'></div>"
								+ "</div>"
								+ "<p style='text-align:center; margin:2vw 0 0 0; padding:0;color:"+ (renderedStrengthValue < Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH)
										? (Colour.GENERIC_GOOD.toWebHexString())
										: (renderedStrengthValue > Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH) ? (Colour.GENERIC_BAD.toWebHexString()) : "default"))+ ";'>"
										+ (int) Math.ceil(Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH))
								+ "</p>"
								+ "<div class='overlay' id='PLAYER_" + Attribute.STRENGTH.getName() + "'></div>"
							+ "</div>"
		
							// Intelligence:
							+ "<div class='full-width-container' style='margin:0;padding:0;'>"
								+ "<div class='statusEffectBackground'>"
									+ IntelligenceLevel.getIntelligenceLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer()) + "</div>"
								+ "<div class='barBackgroundAtt'>"
									+ "<div style='width:" + Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE) * 0.65 + "vw; height:5vw; background:" + Colour.GENERIC_ATTRIBUTE.toWebHexString() + "; float:left; border-radius: 2;'></div>"
								+ "</div>"
								+ "<p style='text-align:center; margin:2vw 0 0 0; padding:0;color:" + (renderedIntelligenceValue < Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE)
										? (Colour.GENERIC_GOOD.toWebHexString())
										: (renderedIntelligenceValue > Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE) ? (Colour.GENERIC_BAD.toWebHexString()) : "default")) + ";'>"
										+ (int) Math.ceil(Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE))
								+ "</p>"
								+ "<div class='overlay' id='PLAYER_" + Attribute.INTELLIGENCE.getName() + "'></div>"
							+ "</div>"
									
							// Fitness:
							+ "<div class='full-width-container' style='margin:0;padding:0;'>"
								+ "<div class='statusEffectBackground'>"
									+ FitnessLevel.getFitnessLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.FITNESS)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer()) + "</div>"
								+ "<div class='barBackgroundAtt'>"
									+ "<div style='width:" + Main.game.getPlayer().getAttributeValue(Attribute.FITNESS) * 0.65 + "vw; height:5vw; background:" + Colour.GENERIC_ATTRIBUTE.toWebHexString() + "; float:left; border-radius: 2;'></div>"
								+ "</div>"
								+ "<p style='text-align:center; margin:2vw 0 0 0; padding:0;color:" + (renderedFitnessValue < Main.game.getPlayer().getAttributeValue(Attribute.FITNESS)
										? (Colour.GENERIC_GOOD.toWebHexString())
										: (renderedFitnessValue > Main.game.getPlayer().getAttributeValue(Attribute.FITNESS) ? (Colour.GENERIC_BAD.toWebHexString()) : "default")) + ";'>"
										+ (int) Math.ceil(Main.game.getPlayer().getAttributeValue(Attribute.FITNESS))
								+ "</p>"
								+ "<div class='overlay' id='PLAYER_" + Attribute.FITNESS.getName() + "'></div>"
							+ "</div>"
									
							// Corruption:
							+ "<div class='full-width-container' style='padding:0;'>"
								+ "<div class='statusEffectBackground'>"
									+ CorruptionLevel.getCorruptionLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer()) + "</div>"
								+ "<div class='barBackgroundAtt corruption'>"
									+ "<div style='width:" + Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION) * 0.65 + "vw; height:5vw; background:"
										+ Attribute.CORRUPTION.getColour().toWebHexString() + "; float:left; border-radius: 2;'></div>"
								+ "</div>"
								+ "<p style='text-align:center; margin:2vw 0 0 0; padding:0;color:" + (renderedPlayerCorruptionValue < Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION)
										? (Colour.GENERIC_GOOD.toWebHexString())
										: (renderedPlayerCorruptionValue > Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION) ? (Colour.GENERIC_BAD.toWebHexString()) : "default")) + ";'>"
										+ (int) Math.ceil(Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION))
								+ "</p>"
								+ "<div class='overlay' id='PLAYER_" + Attribute.CORRUPTION.getName() + "'></div>"
							+ "</div>"
								
						+ "</div>");
			} else {
				uiAttributeSB.append(
						"<div class='attribute-container'>"
						+ "<p style='text-align:center;padding:0;margin:0;'><b>Attributes</b></p>"
							+ "<div class='quarter-width-container'>"
							+ "<div class='imageIcon'>" + StrengthLevel.getStrengthLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer()) + "</div>"
								+ "<div style='text-align:center;height:30px;padding:0;margin:0;line-height:30px;'>"+ "<b style='color:" + Attribute.STRENGTH.getColour().toWebHexString() + ";'>"
									+ (int) Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH) + "</b>" + "</div>"
								+ "<div class='overlay' id='PLAYER_"+ Attribute.STRENGTH.getName() + "'></div>"
							+ "</div>"
	
							+ "<div class='quarter-width-container'>"
							+ "<div class='imageIcon'>" + IntelligenceLevel.getIntelligenceLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer()) + "</div>"
								+ "<div style='text-align:center;height:30px;padding:0;margin:0;line-height:30px;'>" + "<b style='color:" + Attribute.INTELLIGENCE.getColour().toWebHexString() + ";'>"
									+ (int) Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE) + "</b>" + "</div>"
								+ "<div class='overlay' id='PLAYER_" + Attribute.INTELLIGENCE.getName() + "'></div>"
							+ "</div>"
	
							+ "<div class='quarter-width-container'>"
							+ "<div class='imageIcon'>" + FitnessLevel.getFitnessLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.FITNESS)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer()) + "</div>"
								+ "<div style='text-align:center;height:30px;padding:0;margin:0;line-height:30px;'>" + "<b style='color:" + Attribute.FITNESS.getColour().toWebHexString() + ";'>"
									+ (int) Main.game.getPlayer().getAttributeValue(Attribute.FITNESS) + "</b>" + "</div>"
								+ "<div class='overlay' id='PLAYER_" + Attribute.FITNESS.getName() + "'></div>"
							+ "</div>"
	
							+ "<div class='quarter-width-container'>"
							+ "<div class='imageIcon'>" + CorruptionLevel.getCorruptionLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer()) + "</div>"
								+ "<div style='text-align:center;height:30px;padding:0;margin:0;line-height:30px;'>" + "<b style='color:"+ Attribute.CORRUPTION.getColour().toWebHexString() + ";'>"
									+ (int) Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION) + "</b>" + "</div>"
								+ "<div class='overlay' id='PLAYER_" + Attribute.CORRUPTION.getName() + "'></div>"
							+ "</div>"
						+ "</div>");
			}
			
			// Health, mana and experience:
			uiAttributeSB.append(
					"<div class='combat-attributes'>"
						+ "<p style='text-align:center;padding:0;margin:0;'><b>Combat</b></p>"
						
						+ "<div class='full-width-container' style='margin:0;padding:0;'>"
							+ "<div class='imageIcon'>" + Attribute.HEALTH_MAXIMUM.getSVGString() + "</div>" + "<div class='barBackgroundAtt'>" + "<div style='width:"
							+ (Main.game.getPlayer().getHealth() / Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM)) * 65 + "vw; height:5vw; background:" + Colour.ATTRIBUTE_HEALTH.toWebHexString()
							+ "; float:left; border-radius: 2;'></div>" + "</div>" + "<p style='text-align:center; margin:1 0 0 0; padding:0;color:"
							+ (renderedHealthValue < Main.game.getPlayer().getHealth() ? Colour.GENERIC_GOOD.toWebHexString() : renderedHealthValue > Main.game.getPlayer().getHealth() ? (Colour.GENERIC_BAD.toWebHexString()) : "default") + ";'>"
							+ (int) Math.ceil(Main.game.getPlayer().getHealth()) + "</p>" + "<div class='overlay' id='PLAYER_" + Attribute.HEALTH_MAXIMUM.getName() + "'></div>"
						+ "</div>"
	
						+ "<div class='full-width-container' style='margin:0;padding:0;'>"
							+ "<div class='imageIcon'>" + Attribute.MANA_MAXIMUM.getSVGString() + "</div>" + "<div class='barBackgroundAtt'>" + "<div style='width:"
							+ (Main.game.getPlayer().getMana() / Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM)) * 65 + "vw; height:5vw; background:" + Colour.ATTRIBUTE_MANA.toWebHexString()
							+ "; float:left; border-radius: 2;'></div>" + "</div>" + "<p style='text-align:center; margin:1 0 0 0; padding:0;color:"
							+ (renderedManaValue < Main.game.getPlayer().getMana() ? (Colour.GENERIC_GOOD.toWebHexString()) : (renderedManaValue > Main.game.getPlayer().getMana() ? (Colour.GENERIC_BAD.toWebHexString()) : "default")) + ";'>"
							+ (int) Math.ceil(Main.game.getPlayer().getMana()) + "</p>" + "<div class='overlay' id='PLAYER_" + Attribute.MANA_MAXIMUM.getName() + "'></div>"
						+ "</div>"
	
						+ "<div class='full-width-container' style='margin:0;padding:0;'>"
							+ "<div class='imageIcon'>" + Attribute.STAMINA_MAXIMUM.getSVGString() + "</div>" + "<div class='barBackgroundAtt'>"
							+ "<div style='width:" + (Main.game.getPlayer().getStaminaPercentage() * 65) + "vw; height:5vw; background:" + Colour.ATTRIBUTE_FITNESS.toWebHexString() + "; float:left; border-radius: 2;'></div>" + "</div>"
							+ "<p style='text-align:center; margin:1 0 0 0; padding:0;color:"
							+ (renderedStaminaValue < Main.game.getPlayer().getStamina() ? (Colour.GENERIC_GOOD.toWebHexString()) : (renderedStaminaValue > Main.game.getPlayer().getStamina() ? (Colour.GENERIC_BAD.toWebHexString()) : "default"))
							+ ";'>" + (int) Math.ceil(Main.game.getPlayer().getStamina()) + "</p>" + "<div class='overlay' id='PLAYER_" + Attribute.STAMINA_MAXIMUM.getName() + "'></div>"
						+ "</div>"
						
					+ "</div>");
			
			
			// Status effects:
			uiAttributeSB.append("<div class='perk-display'>"
									+ "<p style='text-align:center;padding:0;margin:0;'><b>Effects</b></p>");
			
			// Infinite duration:
			for (StatusEffect se : Main.game.getPlayer().getStatusEffects()) {
				if (!se.isCombatEffect() && Main.game.getPlayer().getStatusEffectDuration(se)==-1 && se.renderInEffectsPanel())
					uiAttributeSB.append(
							"<div class='statusEffectBackground" + (se.isCombatEffect() && !se.isBeneficial() ? " negativeCombat" : "") + (se.isCombatEffect() && se.isBeneficial() ? " positiveCombat" : "") + "'>"
									+ se.getSVGString(Main.game.getPlayer())
									+ "<div class='overlay' id='SE_PLAYER_" + se + "'></div>"
							+ "</div>");
			}
			// Timed:
			for (StatusEffect se : Main.game.getPlayer().getStatusEffects()) {
				if (!se.isCombatEffect() && Main.game.getPlayer().getStatusEffectDuration(se)!=-1 && se.renderInEffectsPanel()) {
					int timerHeight = (int) ((Main.game.getPlayer().getStatusEffectDuration(se)/(60*6f))*100);

					Colour timerColour = Colour.STATUS_EFFECT_TIME_HIGH;
					
					if(timerHeight>100) {
						timerHeight=100;
						timerColour = Colour.STATUS_EFFECT_TIME_OVERFLOW;
					} else if(timerHeight<15) {
						timerColour = Colour.STATUS_EFFECT_TIME_LOW;
					} else if (timerHeight<50) {
						timerColour = Colour.STATUS_EFFECT_TIME_MEDIUM;
					}
					
					uiAttributeSB.append(
							"<div class='statusEffectBackground" + (se.isCombatEffect() && !se.isBeneficial() ? " negativeCombat" : "") + (se.isCombatEffect() && se.isBeneficial() ? " positiveCombat" : "") + "'>"
							
									+ "<div style='float:left;width:12vw;'>"+se.getSVGString(Main.game.getPlayer())+"</div>"
									
									+"<div style='position:absolute; right:0; height:100%; width:2vw; background:#222; border-radius: 2;'>"
										+ "<div style='position:absolute; bottom:0; height:"+timerHeight+"%; width:2vw; background:"
										+ timerColour.toWebHexString() + "; float:left; border-radius: 2;'></div>"
									+ "</div>"
									
									+ "<div class='overlay' id='SE_PLAYER_" + se + "'></div>"
									
							+ "</div>");
				}
			}

			uiAttributeSB.append("</div>"

					+ "</body>");
			
		}
		
		
		

		if (Main.mainController != null)
			if (Main.game.getCurrentDialogueNode() != null) {
				if (!Main.game.getCurrentDialogueNode().isNoTextForContinuesDialogue() && renderedDialogueNode != Main.game.getCurrentDialogueNode()) {

					renderedHealthValue = Main.game.getPlayer().getHealth();
					renderedManaValue = Main.game.getPlayer().getMana();
					renderedStaminaValue = Main.game.getPlayer().getStamina();
					if (Main.game.isInSex()) {
						renderedPlayerArousalValue = Main.game.getPlayer().getArousal();
						renderedPartnerArousalValue = Sex.getPartner().getArousal();
						renderedPartnerCorruptionValue = Sex.getPartner().getAttributeValue(Attribute.CORRUPTION);
					}
					renderedStrengthValue = Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH);
					renderedIntelligenceValue = Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE);
					renderedFitnessValue = Main.game.getPlayer().getAttributeValue(Attribute.FITNESS);
					renderedPlayerCorruptionValue = Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION);
					

					renderedDialogueNode = Main.game.getCurrentDialogueNode();

				}
			}
		Main.mainController.setAttributePanelContent(uiAttributeSB.toString());
	}

	private StringBuilder mapSB = new StringBuilder();

	public String renderedHTMLMap() {

		mapSB.setLength(0);

		mapSB.append("<div class='map-container'>");

		int mapSize = zoomedIn ? 2 : 3;
		float unit = zoomedIn ? 4.5f : 3f, borderSizeReduction = 2.5f;

		Vector2i playerPosition = Main.game.getPlayer().getLocation();

		// It looks messy, but it works...
		for (int y = playerPosition.getY() + mapSize; y >= playerPosition.getY() - mapSize; y--) {
			for (int x = playerPosition.getX() - mapSize; x <= playerPosition.getX() + mapSize; x++) {
				if (x < Main.game.getActiveWorld().WORLD_WIDTH && x >= 0 && y < Main.game.getActiveWorld().WORLD_HEIGHT && y >= 0) {// If within  bounds of map:

					if (Main.game.getActiveWorld().getCell(x, y).isDiscovered() || Main.game.isDebugMode()) { // If the tile is discovered:

						if (Main.game.getActiveWorld().getCell(x, y).getPlace() == GenericPlace.IMPASSABLE) {
							mapSB.append("<div class='map-tile blank' style='width:" + (4 * unit) + "%; height:" + (4 * unit) + "%;'></div>");
						} else {

							// This is the "move North" tile:
							if (y == playerPosition.getY() + 1 && x == playerPosition.getX() && Main.game.getActiveWorld().getCell(x, y).getPlace() != GenericPlace.IMPASSABLE) {
								if(Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous()) {
									mapSB.append("<div class='map-tile movement dangerous' id='upButton' style='width:" + (4 * unit - borderSizeReduction) + "%; height:"+ (4 * unit - borderSizeReduction) + "%; border-width:1%;'>");
									
								} else {
									mapSB.append("<div class='map-tile movement' id='upButton' style='width:" + (4 * unit - borderSizeReduction) + "%; height:"+ (4 * unit - borderSizeReduction) + "%; border-width:1%; border-color:"+
												(Main.game.getPlayer().getFemininity()<=Femininity.MASCULINE.getMaximumFemininity()
														?Colour.MASCULINE_PLUS
														:(Main.game.getPlayer().getFemininity()<=Femininity.ANDROGYNOUS.getMaximumFemininity()
																?Colour.ANDROGYNOUS
																		:Colour.FEMININE_PLUS)).toWebHexString()+";'>");
								}
								
								// Put place icon onto tile:
								if (Main.game.getActiveWorld().getCell(x, y).getPlace().getSVGString() != null)
									mapSB.append("<div class='place-icon'>" + Main.game.getActiveWorld().getCell(x, y).getPlace().getSVGString() + "</div>");

								mapSB.append("<b class='hotkey-icon" + (Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous() ? " dangerous" : "") + "'>"
										+ (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.MOVE_NORTH) == null ? "" : Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.MOVE_NORTH).getFullName()) + "</b>");
								
								appendNPCIcon(x, y);
								appendItemsInAreaIcon(x, y);
								
								// Close the tile's div:
								mapSB.append("</div>");

								// This is the "move South" tile:
							} else if (y == playerPosition.getY() - 1 && x == playerPosition.getX() && Main.game.getActiveWorld().getCell(x, y).getPlace() != GenericPlace.IMPASSABLE) {
								if(Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous()) {
									mapSB.append("<div class='map-tile movement dangerous' id='downButton' style='width:" + (4 * unit - borderSizeReduction) + "%; height:"+ (4 * unit - borderSizeReduction) + "%; border-width:1%;'>");
									
								} else {
									mapSB.append("<div class='map-tile movement' id='downButton' style='width:" + (4 * unit - borderSizeReduction) + "%; height:"+ (4 * unit - borderSizeReduction) + "%; border-width:1%; border-color:"+
												(Main.game.getPlayer().getFemininity()<=Femininity.MASCULINE.getMaximumFemininity()
														?Colour.MASCULINE_PLUS
														:(Main.game.getPlayer().getFemininity()<=Femininity.ANDROGYNOUS.getMaximumFemininity()
																?Colour.ANDROGYNOUS
																		:Colour.FEMININE_PLUS)).toWebHexString()+";'>");
								}

								// Put place icon onto tile:
								if (Main.game.getActiveWorld().getCell(x, y).getPlace().getSVGString() != null)
									mapSB.append("<div class='place-icon'>" + Main.game.getActiveWorld().getCell(x, y).getPlace().getSVGString() + "</div>");

								mapSB.append("<b class='hotkey-icon" + (Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous() ? " dangerous" : "") + "'>"
										+ (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.MOVE_SOUTH) == null ? "" : Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.MOVE_SOUTH).getFullName()) + "</b>");

								appendNPCIcon(x, y);
								appendItemsInAreaIcon(x, y);
								
								// Close the tile's div:
								mapSB.append("</div>");

								// This is the "move West" tile:
							} else if (y == playerPosition.getY() && x == playerPosition.getX() - 1 && Main.game.getActiveWorld().getCell(x, y).getPlace() != GenericPlace.IMPASSABLE) {
								if(Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous()) {
									mapSB.append("<div class='map-tile movement dangerous' id='leftButton' style='width:" + (4 * unit - borderSizeReduction) + "%; height:"+ (4 * unit - borderSizeReduction) + "%; border-width:1%;'>");
									
								} else {
									mapSB.append("<div class='map-tile movement' id='leftButton' style='width:" + (4 * unit - borderSizeReduction) + "%; height:"+ (4 * unit - borderSizeReduction) + "%; border-width:1%; border-color:"+
												(Main.game.getPlayer().getFemininity()<=Femininity.MASCULINE.getMaximumFemininity()
														?Colour.MASCULINE_PLUS
														:(Main.game.getPlayer().getFemininity()<=Femininity.ANDROGYNOUS.getMaximumFemininity()
																?Colour.ANDROGYNOUS
																		:Colour.FEMININE_PLUS)).toWebHexString()+";'>");
								}

								// Put place icon onto tile:
								if (Main.game.getActiveWorld().getCell(x, y).getPlace().getSVGString() != null)
									mapSB.append("<div class='place-icon'>" + Main.game.getActiveWorld().getCell(x, y).getPlace().getSVGString() + "</div>");

								mapSB.append("<b class='hotkey-icon" + (Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous() ? " dangerous" : "") + "'>"
										+ (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.MOVE_WEST) == null ? "" : Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.MOVE_WEST).getFullName()) + "</b>");

								appendNPCIcon(x, y);
								appendItemsInAreaIcon(x, y);
								
								// Close the tile's div:
								mapSB.append("</div>");

								// This is the "move East" tile:
							} else if (y == playerPosition.getY() && x == playerPosition.getX() + 1 && Main.game.getActiveWorld().getCell(x, y).getPlace() != GenericPlace.IMPASSABLE) {
								if(Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous()) {
									mapSB.append("<div class='map-tile movement dangerous' id='rightButton' style='width:" + (4 * unit - borderSizeReduction) + "%; height:"+ (4 * unit - borderSizeReduction) + "%; border-width:1%;'>");
									
								} else {
									mapSB.append("<div class='map-tile movement' id='rightButton' style='width:" + (4 * unit - borderSizeReduction) + "%; height:"+ (4 * unit - borderSizeReduction) + "%; border-width:1%; border-color:"+
												(Main.game.getPlayer().getFemininity()<=Femininity.MASCULINE.getMaximumFemininity()
														?Colour.MASCULINE_PLUS
														:(Main.game.getPlayer().getFemininity()<=Femininity.ANDROGYNOUS.getMaximumFemininity()
																?Colour.ANDROGYNOUS
																		:Colour.FEMININE_PLUS)).toWebHexString()+";'>");
								}

								// Put place icon onto tile:
								if (Main.game.getActiveWorld().getCell(x, y).getPlace().getSVGString() != null)
									mapSB.append("<div class='place-icon'>" + Main.game.getActiveWorld().getCell(x, y).getPlace().getSVGString() + "</div>");

								mapSB.append("<b class='hotkey-icon" + (Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous() ? " dangerous" : "") + "'>"
										+ (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.MOVE_EAST) == null ? "" : Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.MOVE_EAST).getFullName()) + "</b>");

								appendNPCIcon(x, y);
								appendItemsInAreaIcon(x, y);
								
								// Close the tile's div:
								mapSB.append("</div>");

							} else {
								mapSB.append("<div class='map-tile" + (Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous() ? (y == playerPosition.getY() && x == playerPosition.getX() ? " player dangerous" : " dangerous")
										: (y == playerPosition.getY() && x == playerPosition.getX() ? " player" : "")) + "' style='width:" + (4 * unit) + "%; height:" + (4 * unit) + "%;'>");

								// Put place icon onto tile:
								if (Main.game.getActiveWorld().getCell(x, y).getPlace().getSVGString() != null) {
									if (y == playerPosition.getY() && x == playerPosition.getX()) {
										if (Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous()) {
											mapSB.append("<div class='place-icon' style='margin:7%;width:86%;height:86%;'>" + SVGImages.SVG_IMAGE_PROVIDER.getPlayerPlaceMapDangerousIcon() + "</div>");
											
										} else {
											if(Main.game.getPlayer().getFemininity()<=Femininity.MASCULINE.getMaximumFemininity()) {
												mapSB.append("<div class='place-icon' style='margin:7%;width:86%;height:86%;'>" + SVGImages.SVG_IMAGE_PROVIDER.getPlayerPlaceMapIconMasculine() + "</div>");
												
											} else if(Main.game.getPlayer().getFemininity()<=Femininity.ANDROGYNOUS.getMaximumFemininity()) {
												mapSB.append("<div class='place-icon' style='margin:7%;width:86%;height:86%;'>" + SVGImages.SVG_IMAGE_PROVIDER.getPlayerPlaceMapIconAndrogynous() + "</div>");
												
											} else{
												mapSB.append("<div class='place-icon' style='margin:7%;width:86%;height:86%;'>" + SVGImages.SVG_IMAGE_PROVIDER.getPlayerPlaceMapIconFeminine() + "</div>");
											}
										}
									}
									mapSB.append("<div class='place-icon' style='margin:18%;width:64%;height:64%;'>" + Main.game.getActiveWorld().getCell(x, y).getPlace().getSVGString() + "</div>");

								} else if (y == playerPosition.getY() && x == playerPosition.getX()) {

									if (Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous()) {
										mapSB.append("<div class='place-icon' style='margin:18%;width:64%;height:64%;'>" + SVGImages.SVG_IMAGE_PROVIDER.getPlayerMapDangerousIcon() + "</div>");
									} else {
										if(Main.game.getPlayer().getFemininity()<=Femininity.MASCULINE.getMaximumFemininity()) {
											mapSB.append("<div class='place-icon' style='margin:18%;width:64%;height:64%;'>" + SVGImages.SVG_IMAGE_PROVIDER.getPlayerMapIconMasculine() + "</div>");
											
										} else if(Main.game.getPlayer().getFemininity()<=Femininity.ANDROGYNOUS.getMaximumFemininity()) {
											mapSB.append("<div class='place-icon' style='margin:18%;width:64%;height:64%;'>" + SVGImages.SVG_IMAGE_PROVIDER.getPlayerMapIconAndrogynous() + "</div>");
											
										} else{
											mapSB.append("<div class='place-icon' style='margin:18%;width:64%;height:64%;'>" + SVGImages.SVG_IMAGE_PROVIDER.getPlayerMapIconFeminine() + "</div>");
										}
									}
								}
								
								appendNPCIcon(x, y);
								appendItemsInAreaIcon(x, y);

								// Close the tile's div:
								mapSB.append("</div>");
							}

						}
						
					} else {
						mapSB.append("<div class='map-tile blank' style='width:" + (4 * unit) + "%; height:" + (4 * unit) + "%;'></div>");
					}
					
				} else {
					mapSB.append("<div class='map-tile blank' style='width:" + (4 * unit) + "%; height:" + (4 * unit) + "%;'></div>");
				}
			}

		}
		mapSB.append("</div>");

		
		if (Main.game.getCurrentDialogueNode().isTravelDisabled()) {
			mapSB.append("<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.5; border-radius:5px;'></div>");
			renderedDisabledMap = true;
		} else {
			renderedDisabledMap = false;
		}

		return mapSB.toString();
	}
	
	private void appendNPCIcon(int x, int y) {
		List<String> mapIcons = new ArrayList<>();
		
		for(NPC npc : Main.game.getNPCList()) {
			if(npc.getLocation().equals(x, y) && npc.getWorldLocation()==Main.game.getActiveWorld().getWorldType()) {
				mapIcons.add(npc.getMapIcon());
			}
		}
		
		for(int i = mapIcons.size() ; i>0 ; i--) {
			mapSB.append("<div class='npc-icon' style='left:"+(((i-1)*6)+2)+"px;'>"+mapIcons.get(i-1)+"</div>");
		}
	}
	
	private void appendItemsInAreaIcon(int x, int y) {
		if(Main.game.getActiveWorld().getCell(x, y).getInventory().getInventorySlotsTaken() != 0) {
			mapSB.append("<div class='item-icon'>"+SVGImages.SVG_IMAGE_PROVIDER.getItemsOnFloorIcon()+"</div>");
		}
	}

	public void renderMapTitle() {
		MapDisplay display = Main.game.getCurrentDialogueNode().getMapDisplay();

		if ((Main.game.isInSex() || Main.game.isInCombat()) && display != MapDisplay.CHARACTERS_PRESENT)
			display = MapDisplay.INVENTORY;

		switch (display) {
			case NORMAL: case STATUS_EFFECT_MESSAGE:
				Main.mainController.getWebViewInventory().setVisible(false);
				Main.mainController.getWebViewMap().setVisible(true);
				Main.mainController.setMapTitleContent(weatherBar((Util.capitaliseSentence(Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getName()))));
				break;
	
			case INVENTORY:
				Main.mainController.getWebViewInventory().setVisible(true);
				Main.mainController.getWebViewMap().setVisible(false);
				Main.mainController.setMapTitleContent(
						weatherBar((RenderingEngine.ENGINE.getCharactersInventoryToRender().isPlayer()
								? "Your Equipped Items"
								: Util.capitaliseSentence(RenderingEngine.ENGINE.getCharactersInventoryToRender().getName()))));

				break;
	
			case PHONE:
				Main.mainController.getWebViewInventory().setVisible(false);
				Main.mainController.getWebViewMap().setVisible(true);
				Main.mainController.setMapTitleContent(weatherBar("Using your phone"));
				break;
	
			case OPTIONS:
				Main.mainController.getWebViewInventory().setVisible(false);
				Main.mainController.getWebViewMap().setVisible(true);
				Main.mainController.setMapTitleContent(weatherBar("Options menu"));
				break;
	
			case CHARACTERS_PRESENT:
				Main.mainController.getWebViewInventory().setVisible(true);
				Main.mainController.getWebViewMap().setVisible(false);
				Main.mainController.setMapTitleContent(
						weatherBar((RenderingEngine.ENGINE.getCharactersInventoryToRender() == null
								? "Examining characters"
								: Util.capitaliseSentence(RenderingEngine.ENGINE.getCharactersInventoryToRender().getName()))));
				break;
			default:
				break;
		}
	}

	private String weatherBar(String title) {
		return 
				"<body class='weatherBodyTitle'>"
//					+ "<div class='weather-image'>"
//						+ Main.game.getWeatherImage()
//						+ "<div class='overlay' id='weather'></div>"
//					+ "</div>"
					+ "<p style='text-align:center;margin:0;padding:0;width:75vw;float:left;'>"
					+ "<b>" 
					+ (Main.game.isDayTime() ? "Day " : "Night ") + Main.game.getDayNumber() + ", " + String.format("%02d", (Main.game.getMinutesPassed() % (24 * 60)) / 60) + ":" + String.format("%02d", (Main.game.getMinutesPassed() % (24 * 60)) % 60)
					+ "</b>"
					+ "</br>"
					+ title
					+ "</p>"
					+ "<p style='float:left;width:20vw;text-align:center;margin-top:8px;padding:0;'>"
						+ "<b style='color: " + com.base.utils.Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>" + Main.game.getPlayer().getMoney() + "</b>"
					+ "</p>"
				+ "</body>";
	}

	public void renderButtons() {
		Main.mainController.setButtonsContent(
				"<div class='quarterContainer'>"
					+ "<div class='button" + (!Main.game.getCurrentDialogueNode().isOptionsDisabled() ? "" : " disabled") + "' id='mainMenu'>"
						+ SVGImages.SVG_IMAGE_PROVIDER.getMenuIcon() + (!Main.game.getCurrentDialogueNode().isOptionsDisabled() ? "" : "<div class='disabledLayer'></div>")
					+ "</div>"
				+ "</div>"

				+ "<div class='quarterContainer'>"
					+ "<div class='button" + (
							Main.game.getPlayer().isMainQuestUpdated()
							|| Main.game.getPlayer().isSideQuestUpdated()
							|| Main.game.getPlayer().isRomanceQuestUpdated()
							|| Main.getProperties().isNewWeaponDiscovered()
							|| Main.getProperties().isNewClothingDiscovered()
							|| Main.getProperties().isNewItemDiscovered()
							|| Main.getProperties().isNewRaceDiscovered()
							|| Main.game.getPlayer().getPerkPoints()>0
							|| (Main.game.getPlayer().getLevelUpPoints()>0
									&& (Main.game.getPlayer().getBaseAttributeValue(Attribute.STRENGTH) + Main.game.getPlayer().getBaseAttributeValue(Attribute.INTELLIGENCE) + Main.game.getPlayer().getBaseAttributeValue(Attribute.FITNESS))<300)
								?" highlight"
								:"")
						+ (!Main.game.getCurrentDialogueNode().isOptionsDisabled() ? "" : " disabled") + "' id='journal'>" + SVGImages.SVG_IMAGE_PROVIDER.getJournalIcon()
						+ (!Main.game.getCurrentDialogueNode().isOptionsDisabled() ? "" : "<div class='disabledLayer'></div>")
					+ "</div>"
				+ "</div>"

				+ "<div class='quarterContainer'>"
					+ "<div class='button" + (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().getInventorySlotsTaken()>0 ? " highlight" : "")
					+ (Main.mainController.isInventoryDisabled() ? " disabled" : "") + "' id='inventory'>"
						+ ((Main.game.isInCombat() || Main.game.isInSex()) && Main.game.getCurrentDialogueNode().getMapDisplay() != MapDisplay.CHARACTERS_PRESENT
								? (RenderingEngine.ENGINE.getCharactersInventoryToRender().isPlayer() ? SVGImages.SVG_IMAGE_PROVIDER.getInventorySwitchIcon() : SVGImages.SVG_IMAGE_PROVIDER.getInventorySwitchOppositeIcon())
								: SVGImages.SVG_IMAGE_PROVIDER.getInventoryIcon())
						+ (Main.mainController.isInventoryDisabled() ? "<div class='disabledLayer'></div>" : "")
					+ "</div>"
				+ "</div>"

				+ "<div class='quarterContainer'>"
					+ "<div class='button" + (Main.game.getCharactersPresent().isEmpty() && Main.game.getCurrentDialogueNode().getMapDisplay() != MapDisplay.CHARACTERS_PRESENT ? " disabled" : "")
					+ "' id='charactersPresent'>" + SVGImages.SVG_IMAGE_PROVIDER.getPeopleIcon()
						+ (Main.game.getCharactersPresent().isEmpty() && Main.game.getCurrentDialogueNode().getMapDisplay() != MapDisplay.CHARACTERS_PRESENT ? "<div class='disabledLayer'></div>" : "")
					+ "</div>"
				+ "</div>"

				+ "<div class='quarterContainer'>"
					+ "<div class='button" + (Main.game.getCurrentDialogueNode().isTravelDisabled() ? " disabled" : "") + "' id='mapZoom'>"
						+ (RenderingEngine.isZoomedIn() ? SVGImages.SVG_IMAGE_PROVIDER.getZoomOutIcon() : SVGImages.SVG_IMAGE_PROVIDER.getZoomInIcon()) + (Main.game.getCurrentDialogueNode().isTravelDisabled() ? "<div class='disabledLayer'></div>" : "")
					+ "</div>"
				+ "</div>");
	}

	public GameCharacter getCharactersInventoryToRender() {
		return charactersInventoryToRender;
	}

	public void setCharactersInventoryToRender(GameCharacter charactersInventoryToRender) {
		this.charactersInventoryToRender = charactersInventoryToRender;
	}

	public static boolean isZoomedIn() {
		return zoomedIn;
	}

	public static void setZoomedIn(boolean zoomedIn) {
		RenderingEngine.zoomedIn = zoomedIn;
	}

	public static boolean isRenderedDisabledMap() {
		return renderedDisabledMap;
	}

	public static void setRenderedDisabledMap(boolean renderedDisabledMap) {
		RenderingEngine.renderedDisabledMap = renderedDisabledMap;
	}

}
