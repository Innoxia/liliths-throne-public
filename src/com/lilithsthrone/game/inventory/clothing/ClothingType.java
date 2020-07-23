package com.lilithsthrone.game.inventory.clothing;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.controller.xmlParsing.XMLLoadException;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.SetBonus;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.ColourListPresets;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.84
 * @version 0.3.5.5
 * @author Innoxia
 */
public class ClothingType {
	
	private static String braEquipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
		return AbstractClothingType.getEquipDescriptions(clothingOwner, clothingRemover, rough,
				"You place the bra over your chest before fastening the straps at your back.",
				"You place the bra over [npc.namePos] chest before fastening the straps at [npc.her] back.",
				null,
				"[npc.Name] places the bra over [npc.her] chest before fastening the straps at [npc.her] back.",
				"[npc.Name] places the bra over your chest before fastening the straps at your back.",
				null, null, null);
	}

	private static String braUnequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
		return AbstractClothingType.getEquipDescriptions(clothingOwner, clothingRemover, rough,
				"You unclasp the bra and take it off.",
				"You unclasp [npc.namePos] bra and take it off.",
				null,
				"[npc.Name] unclasps [npc.her] bra and takes it off.",
				"[npc.Name] unclasps your bra and takes it off.",
				null, null, null);
	}
	
	private static String braDisplaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
		return AbstractClothingType.getEquipDescriptions(clothingOwner, clothingRemover, rough,
				"You slide your bra's straps off your shoulders before tugging it down to reveal your [pc.breasts+].",
				"You slide [npc.her] bra's straps off [npc.her] shoulders before tugging it down to reveal [npc.her] [npc.breasts+].",
				null,
				"[npc.Name] slides [npc.her] bra's straps off [npc.her] shoulders before tugging it down to reveal [npc.her] [npc.breasts+].",
				"[npc.Name] slides your bra's straps off your shoulders before tugging it down to reveal your [pc.breasts+].",
				null, null, null);
	}

	private static String braReplaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
		return AbstractClothingType.getEquipDescriptions(clothingOwner, clothingRemover, rough,
				"You pull your bra back up to cover your [pc.breasts].",
				"You slide [npc.her] bra back up to cover [npc.her] [npc.breasts].",
				null,
				"[npc.Name] pulls [npc.her] bra back up to cover [npc.her] [npc.breasts].",
				"[npc.Name] pulls your bra back up to cover your [pc.breasts].",
				null, null, null);
	}
	
	// Special:
	
	// MOUTH
	public static AbstractClothingType MOUTH_BANDANA = new AbstractClothingType(80,
			"a",
			false,
			"bandana",
			"bandanas",
			"A square piece of cloth, which can be folded and tied so as to cover the wearer's mouth.",
			0,
			null,
			InventorySlot.MOUTH,
			Rarity.COMMON,
			null,
			"clothing/mouth_bandana",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							null,
							null,
							null, null),
					new BlockedParts(
							DisplacementType.PULLS_DOWN,
							null,
							Util.newArrayListOfValues(CoverableArea.MOUTH),
							null,
							Util.newArrayListOfValues(
									InventorySlot.PIERCING_LIP,
									InventorySlot.PIERCING_TONGUE))),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)) {
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You position the bandana over your [pc.face], before reaching back and tying the ends together.",
					"You hold [npc.namePos] head still as you tie the bandana around [npc.her] [npc.face].",
					null,
					"[npc.Name] [npc.verb(position)] a bandana over [npc.her] [npc.face], before reaching back and tying the ends together.",
					"[npc.Name] holds your head still and ties a bandana around your [pc.face].",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You untie the ends of your bandana and take it off.",
					"You untie the ends of [npc.namePos] bandana and take it off.",
					null,
					"[npc.Name] unties the ends of [npc.her] bandana and takes it off.",
					"[npc.Name] unties the ends of your bandana and takes it off.",
					null, null, null);
		}
		
		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your bandana.",
					"You pull down [npc.namePos] bandana.",
					null,
					"[npc.Name] pulls down [npc.her] bandana.",
					"[npc.Name] pulls your bandana down.",
					null, null, null);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull your bandana back up.",
					"You pull [npc.namePos] bandana back up.",
					null,
					"[npc.Name] pulls [npc.her] bandana back up.",
					"[npc.Name] pulls your bandana back up.",
					null, null, null);
		}
	};

	// TORSO

	public static AbstractClothingType TORSO_OXFORD_SHIRT = new AbstractClothingType(150,
			"a",
			false,
			"long-sleeved shirt",
			"long-sleeved shirts",
			"A men's long-sleeved shirt.",
			0,
			Femininity.MASCULINE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"clothing/torso_oxfordShirt",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER),
							Util.newArrayListOfValues(CoverableArea.BACK),
							null, null),
					new BlockedParts(
							DisplacementType.UNBUTTONS,
							null,
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList())),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You put your [pc.arms] through the shirt's sleeves and button it up.",
					"You push [npc.namePos] [npc.arms] through the shirt's sleeves and button it up.",
					null,
					"[npc.Name] pushes [npc.her] [npc.arms] through the shirt's sleeves and buttons it up.",
					"[npc.Name] pushes your [pc.arms] through the shirt's sleeves and buttons it up.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unbutton your shirt and pull it off.",
					"You unbutton [npc.namePos] shirt and pull it off.",
					null,
					"[npc.Name] unbuttons [npc.her] shirt and pulls it off.",
					"[npc.Name] unbuttons your shirt and pulls it off.",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unbutton your shirt, leaving it hanging open.",
					"You unbutton [npc.namePos] shirt.",
					"You roughly pull open [npc.namePos] shirt, almost tearing the buttons off in the process.",
					"[npc.Name] unbuttons [npc.her] shirt, leaving it hanging open.",
					"[npc.Name] unbuttons your shirt.",
					"[npc.Name] roughly pulls open your shirt, almost tearing the buttons right off", null, null);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You button up your shirt.",
					"You button up [npc.namePos] shirt.",
					"You roughly button up [npc.namePos] shirt.",
					"[npc.Name] buttons up [npc.her] shirt.",
					"[npc.Name] buttons up your shirt.",
					"[npc.Name] roughly buttons up your shirt", null, null);
		}
	};
	
	public static AbstractClothingType TORSO_SHORT_SLEEVE_SHIRT = new AbstractClothingType(150,
			"a",
			false,
			"short-sleeved shirt",
			"short-sleeved shirts",
			"A unisex short-sleeved shirt.",
			0,
			null,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"clothing/torso_shortSleeveShirt",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER),
							Util.newArrayListOfValues(CoverableArea.BACK),
							null, null),
					new BlockedParts(
							DisplacementType.UNBUTTONS,
							null,
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList())),
			null,
			ColourListPresets.ALL,
			null,
			Util.newArrayListOfValues(PresetColour.CLOTHING_GREY_LIGHT),
			ColourListPresets.ALL,
			Util.newArrayListOfValues(PresetColour.CLOTHING_WHITE),
			ColourListPresets.ALL,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.FITS_ARM_WINGS)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You put your [pc.arms] through the shirt's sleeves and button it up.",
					"You push [npc.namePos] [npc.arms] through the shirt's sleeves and button it up.",
					null,
					"[npc.Name] pushes [npc.her] [npc.arms] through the shirt's sleeves and buttons it up.",
					"[npc.Name] pushes your [pc.arms] through the shirt's sleeves and buttons it up.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unbutton your shirt and pull it off.",
					"You unbutton [npc.namePos] shirt and pull it off.",
					null,
					"[npc.Name] unbuttons [npc.her] shirt and pulls it off.",
					"[npc.Name] unbuttons your shirt and pulls it off.",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unbutton your shirt, leaving it hanging open.",
					"You unbutton [npc.namePos] shirt.",
					"You roughly pull open [npc.namePos] shirt, almost tearing the buttons off in the process.",
					"[npc.Name] unbuttons [npc.her] shirt, leaving it hanging open.",
					"[npc.Name] unbuttons your shirt.",
					"[npc.Name] roughly pulls open your shirt, almost tearing the buttons right off", null, null);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You button up your shirt.",
					"You button up [npc.namePos] shirt.",
					"You roughly button up [npc.namePos] shirt.",
					"[npc.Name] buttons up [npc.her] shirt.",
					"[npc.Name] buttons up your shirt.",
					"[npc.Name] roughly buttons up your shirt", null, null);
		}
	};
	
	public static AbstractClothingType TORSO_OVER_HOODIE = new AbstractClothingType(300,
			"a",
			false,
			"hoodie",
			"hoodies",
			"A loose-fitting hoodie.",
			0,
			null,
			InventorySlot.TORSO_OVER,
			Rarity.COMMON,
			null,
			"clothing/torso_over_hoodie",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.HEAD),
							Util.newArrayListOfValues(CoverableArea.BACK),
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER), 
							null
							),
					new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_FULL_TORSO.getPresetInventorySlotList())),
			null,
			ColourListPresets.ALL, null,
			null, null,
			null, null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the hoodie.",
					"You guide [npc.namePos] [npc.arms] through the hoodie's sleeves as you pull it on [npc.herHim].",
					"You hold [npc.name] still as you roughly force the hoodie down over [npc.her] head, before pushing [npc.her] [npc.arms] through the sleeves.",
					"[npc.Name] pulls on the hoodie.",
					"[npc.Name] guides your [pc.arms] through the hoodie's sleeves as [npc.she] pulls it on you.",
					"[npc.Name] holds you still as [npc.she] roughly forces the hoodie down over your head, before pushing your [pc.arms] through the sleeves", null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your hoodie.",
					"You pull off [npc.namePos] hoodie.",
					"You grab hold of [npc.namePos] hoodie and roughly pull it off over [npc.her] head.",
					"[npc.Name] takes [npc.her] hoodie off.",
					"[npc.Name] pulls your hoodie off.",
					"[npc.Name] grabs hold of your hoodie and roughly pulls it off over your head", null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull your hoodie up to just below your chin, letting the elastic rim hold it in place.",
					"You pull up [npc.namePos] hoodie.",
					"You roughly yank up [npc.namePos] hoodie.",
					"[npc.Name] pulls [npc.her] hoodie up to just below [npc.her] chin, letting the elastic rim hold it in place.",
					"[npc.Name] pulls up your hoodie.",
					"[npc.Name] roughly yanks up your hoodie.", null, null);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull your hoodie back down, covering your torso.",
					"You pull down [npc.namePos] hoodie.",
					"You roughly pull down [npc.namePos] hoodie.",
					"[npc.Name] pulls [npc.her] hoodie back down to cover [npc.her] torso.",
					"[npc.Name] pulls down your hoodie.",
					"[npc.Name] roughly pulls down your hoodie.", null, null);
		}
	};

	public static AbstractClothingType TORSO_OVER_OPEN_CARDIGAN = new AbstractClothingType(350,
			"an",
			false,
			"open-front cardigan",
			"open-front cardigans",
			"A very feminine, open-front cardigan. It's made from a thin, woolly fabric.",
			0,
			Femininity.FEMININE,
			InventorySlot.TORSO_OVER,
			Rarity.COMMON,
			null,
			"clothing/torso_open_cardigan",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER),
							Util.newArrayListOfValues(CoverableArea.BACK),
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER),
							null)),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the cardigan.",
					"You guide [npc.namePos] [npc.arms] through the cardigan's sleeves as you pull it on [npc.herHim].",
					null,
					"[npc.Name] pulls on the cardigan.",
					"[npc.Name] guides your [pc.arms] through the cardigan's sleeves as [npc.she] pulls it on you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your cardigan.",
					"You pull off [npc.namePos] cardigan.",
					null,
					"[npc.Name] takes [npc.her] cardigan off.",
					"[npc.Name] pulls your cardigan off.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType TORSO_OVER_BLAZER = new AbstractClothingType(300,
			"a",
			false,
			"blazer",
			"blazers",
			"A smart, unisex blazer.",
			0,
			null,
			InventorySlot.TORSO_OVER,
			Rarity.COMMON,
			null,
			"clothing/torso_over_blazer",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER),
							Util.newArrayListOfValues(CoverableArea.BACK),
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER), 
							null),
					new BlockedParts(
							DisplacementType.UNBUTTONS,
							null,
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList())),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the blazer.",
					"You guide [npc.namePos] [npc.arms] through the blazer's sleeves as you pull it on [npc.herHim].",
					null,
					"[npc.Name] pulls on the blazer.",
					"[npc.Name] guides your [pc.arms] through the blazer's sleeves as [npc.she] pulls it on you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your blazer.",
					"You pull off [npc.namePos] blazer.",
					null,
					"[npc.Name] takes [npc.her] blazer off.",
					"[npc.Name] pulls your blazer off.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType TORSO_OVER_WOMENS_LEATHER_JACKET = new AbstractClothingType(600,
			"a",
			false,
			"women's leather jacket",
			"women's leather jackets",
			"A feminine leather jacket, able to be worn both open and, by means of a zip, closed.",
			2f,
			Femininity.FEMININE,
			InventorySlot.TORSO_OVER,
			Rarity.COMMON,
			null,
			"clothing/torso_over_womens_leather_jacket",

			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER),
							Util.newArrayListOfValues(CoverableArea.BACK),
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER), 
							null),
					new BlockedParts(
							DisplacementType.UNZIPS,
							null,
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_FULL_TORSO.getPresetInventorySlotList())),
			null,
			ColourListPresets.LEATHER,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the leather jacket.",
					"You guide [npc.namePos] [npc.arms] through the leather jacket's sleeves as you pull it on [npc.herHim].",
					null,
					"[npc.Name] pulls on the leather jacket.",
					"[npc.Name] guides your [pc.arms] through the leather jacket's sleeves as [npc.she] pulls it on you.",
					null, null, null);
		}
		
		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your leather jacket.",
					"You pull off [npc.namePos] leather jacket.",
					null,
					"[npc.Name] takes [npc.her] leather jacket off.",
					"[npc.Name] pulls your leather jacket off.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType TORSO_OVER_COAT_DRESS = new AbstractClothingType(2500,
			"a",
			false,
			"dress coat",
			"dress coats",
			"A type of dress coat, this garment does absolutely nothing to conceal the wearer's groin or rear end when worn.",
			0,
			Femininity.FEMININE,
			InventorySlot.TORSO_OVER,
			Rarity.COMMON,
			null,
			"clothing/torso_over_dress_coat",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER),
							Util.newArrayListOfValues(CoverableArea.BACK),
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER), 
							null
							),
					new BlockedParts(
							DisplacementType.UNBUTTONS,
							null,
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList())),
			null,
			ColourListPresets.ALL,
			null,
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the dress coat.",
					"You guide [npc.namePos] [npc.arms] through the dress coat's sleeves as you pull it on [npc.herHim].",
					null,
					"[npc.Name] pulls on the dress coat.",
					"[npc.Name] guides your [pc.arms] through the dress coat's sleeves as [npc.she] pulls it on you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your dress coat.",
					"You pull off [npc.namePos] dress coat.",
					null,
					"[npc.Name] takes [npc.her] dress coat off.",
					"[npc.Name] pulls your dress coat off.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType TORSO_OVER_CLOAK = new AbstractClothingType(500,
			"a",
			false,
			"hooded cloak",
			"hooded cloaks",
			"A floor-length cloak, complete with hood.",
			0,
			null,
			InventorySlot.TORSO_OVER,
			Rarity.COMMON,
			null,
			"clothing/torso_over_cloak",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							null,
							Util.newArrayListOfValues(CoverableArea.BACK),
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER), 
							null
							),
					new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							Util.newArrayListOfValues(
									ClothingAccess.GROIN,
									ClothingAccess.ANUS),
							Util.newArrayListOfValues(
									InventorySlot.ANKLE,
									InventorySlot.ANUS,
									InventorySlot.FINGER,
									InventorySlot.FOOT,
									InventorySlot.GROIN,
									InventorySlot.HAND,
									InventorySlot.HIPS,
									InventorySlot.LEG,
									InventorySlot.PENIS,
									InventorySlot.PIERCING_PENIS,
									InventorySlot.PIERCING_VAGINA,
									InventorySlot.SOCK,
									InventorySlot.TAIL,
									InventorySlot.VAGINA,
									InventorySlot.WRIST)),
					new BlockedParts(
							DisplacementType.SHIFTS_ASIDE,
							null,
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.PENIS,
									CoverableArea.VAGINA,
									CoverableArea.STOMACH),
							Util.newArrayListOfValues(
									ClothingAccess.GROIN,
									ClothingAccess.ANUS,
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							Util.newArrayListOfValues(
									InventorySlot.ANKLE,
									InventorySlot.ANUS,
									InventorySlot.CHEST,
									InventorySlot.FINGER,
									InventorySlot.FOOT,
									InventorySlot.GROIN,
									InventorySlot.HAIR,
									InventorySlot.HAND,
									InventorySlot.HEAD,
									InventorySlot.HIPS,
									InventorySlot.HORNS,
									InventorySlot.LEG,
//									InventorySlot.MOUTH,
									InventorySlot.NECK,
									InventorySlot.NIPPLE,
									InventorySlot.PENIS,
									InventorySlot.PIERCING_EAR,
//									InventorySlot.PIERCING_LIP,
									InventorySlot.PIERCING_NIPPLE,
//									InventorySlot.PIERCING_NOSE,
									InventorySlot.PIERCING_PENIS,
									InventorySlot.PIERCING_STOMACH,
//									InventorySlot.PIERCING_TONGUE,
									InventorySlot.PIERCING_VAGINA,
									InventorySlot.SOCK,
									InventorySlot.STOMACH,
									InventorySlot.TAIL,
									InventorySlot.TORSO_UNDER,
									InventorySlot.VAGINA,
									InventorySlot.WEAPON_MAIN_1,
									InventorySlot.WEAPON_MAIN_2,
									InventorySlot.WEAPON_MAIN_3,
									InventorySlot.WEAPON_OFFHAND_1,
									InventorySlot.WEAPON_OFFHAND_2,
									InventorySlot.WEAPON_OFFHAND_3,
									InventorySlot.WRIST))),
			null,
			ColourListPresets.LEATHER,
			ColourListPresets.ALL,
			ColourListPresets.ALL_METAL,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.FITS_ARM_WINGS)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the cloak.",
					"You pull the cloak onto [npc.name].",
					null,
					"[npc.Name] pulls on the cloak.",
					"[npc.Name] pulls the cloak onto you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your cloak.",
					"You pull off [npc.namePos] cloak.",
					null,
					"[npc.Name] takes [npc.her] cloak off.",
					"[npc.Name] pulls your cloak off.",
					null, null, null);
		}
	};
	
	
	public static AbstractClothingType TORSO_RIBBED_SWEATER = new AbstractClothingType(250,
			"a",
			false,
			"ribbed sweater",
			"ribbed sweaters",
			"A unisex ribbed sweater, made from some sort of woolly fabric.",
			0,
			null,
			InventorySlot.TORSO_OVER,
			Rarity.COMMON,
			null,
			"clothing/torso_ribbed_sweater",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
						Util.newArrayListOfValues(
								ClothingAccess.ARMS_UP_TO_SHOULDER,
								ClothingAccess.HEAD),
						null,
						Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER),
						null),
					new BlockedParts(DisplacementType.PULLS_UP,
						null,
						Util.newArrayListOfValues(
								CoverableArea.BREASTS,
								CoverableArea.NIPPLES,
								CoverableArea.STOMACH,
								CoverableArea.BACK),
						Util.newArrayListOfValues(
								ClothingAccess.CHEST,
								ClothingAccess.WAIST),
						PresetConcealmentLists.CONCEALED_FULL_TORSO.getPresetInventorySlotList())),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
	};

	public static AbstractClothingType TORSO_OVER_CHRISTMAS_SWEATER = new AbstractClothingType(100,
			"a",
			false,
			"festive sweater",
			"festive sweaters",
			"A festive sweater, made from some sort of woolly fabric and decorated with a series of incredibly tasteful and refined patterns.",
			0,
			null,
			InventorySlot.TORSO_OVER,
			Rarity.COMMON,
			null,
			"clothing/torso_over_christmas_sweater",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.HEAD),
							null,
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER), 
							null),
					new BlockedParts(DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH,
									CoverableArea.BACK),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_FULL_TORSO.getPresetInventorySlotList())),

			null,
			ColourListPresets.NOT_WHITE,
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.REINDEER_GIFT)){
	};
	
	public static AbstractClothingType TORSO_KEYHOLE_SWEATER = new AbstractClothingType(350,
			"a",
			false,
			"keyhole sweater",
			"keyhole sweaters",
			"A feminine sweater, with a section at the front cut out to reveal the wearer's cleavage.",
			0,
			Femininity.FEMININE,
			InventorySlot.TORSO_OVER,
			Rarity.COMMON,
			null,
			"clothing/torso_keyhole_sweater",
			null,

			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.HEAD),
							null,
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER), 
							null),
					new BlockedParts(DisplacementType.PULLS_DOWN,
							null,
							Util.newArrayListOfValues(CoverableArea.NIPPLES),
							Util.newArrayListOfValues(ClothingAccess.CHEST), 
							null),
					new BlockedParts(DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH,
									CoverableArea.BACK),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList())),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
	};
	
	public static AbstractClothingType TORSO_SLEEVELESS_TURTLENECK = new AbstractClothingType(350,
			"a",
			false,
			"sleeveless turtleneck",
			"sleeveless turtlenecks",
			"A feminine sleeveless sweater, with a high turtleneck.",
			0,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"clothing/torso_sleeveless_turtleneck",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.HEAD),
							null,
							null, null
							),
					new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH,
									CoverableArea.BACK),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList())),
			null,
			ColourListPresets.ALL,
			null,
			null, null, null, null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.FITS_ARM_WINGS)){
	};
	
	public static AbstractClothingType TORSO_KEYHOLE_CROPTOP = new AbstractClothingType(120,
			"a",
			false,
			"keyhole crop top",
			"keyhole crop tops",
			"A small, sleeveless crop top with a stylish cutout that reveals some cleavage.",
			0,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"clothing/torso_keyhole_croptop",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.HEAD),
							null,
							null, null),
					new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH,
									CoverableArea.BACK),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList())),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.FITS_ARM_WINGS)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the crop top.",
					"You guide [npc.namePos] [npc.arms] through the crop top's sleeves as you pull it down over [npc.her] head.",
					null,
					"[npc.Name] pulls on the crop top.",
					"[npc.Name] guides your [pc.arms] through the crop top's sleeves as [npc.she] pulls it down over your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off your crop top.",
					"You slide [npc.namePos] crop top up and over [npc.her] head.",
					null,
					"[npc.Name] pulls off [npc.her] crop top.",
					"[npc.Name] slides your crop top up and over your head.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType TORSO_SHORT_CROPTOP = new AbstractClothingType(100,
			"a",
			false,
			"short croptop",
			"short croptops",
			"A small, sleeveless croptop that leaves its wearer's stomach completely exposed.",
			0,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"clothing/torso_short_croptop",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.HEAD),
							null,
							null,
							null),
					new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES),
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO_STOMACH_VISIBLE.getPresetInventorySlotList())),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.FITS_ARM_WINGS)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the crop top.",
					"You guide [npc.namePos] [npc.arms] through the crop top's sleeves as you pull it down over [npc.her] head.",
					null,
					"[npc.Name] pulls on the crop top.",
					"[npc.Name] guides your [pc.arms] through the crop top's sleeves as [npc.she] pulls it down over your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off your crop top.",
					"You slide [npc.namePos] crop top up and over [npc.her] head.",
					null,
					"[npc.Name] pulls off [npc.her] crop top.",
					"[npc.Name] slides your crop top up and over your head.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType TORSO_FISHNET_TOP = new AbstractClothingType(100,
			"a",
			false,
			"fishnet top",
			"fishnet tops",
			"A small fishnet top that leaves its wearer's stomach completely exposed, while not doing much to conceal anything else, either.",
			0,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"clothing/torso_fishnet_top",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.HEAD),
							null,
							null, null),
					new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							null,
							Util.newArrayListOfValues(ClothingAccess.CHEST), 
							null)),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(
					ItemTag.TRANSPARENT,
					ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the fishnet top.",
					"You guide [npc.namePos] [npc.arms] through the fishnet top's sleeves as you pull it down over [npc.her] head.",
					null,
					"[npc.Name] pulls on the fishnet top.",
					"[npc.Name] guides your [pc.arms] through the fishnet top's sleeves as [npc.she] pulls it down over your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off your fishnet top.",
					"You slide [npc.namePos] fishnet top up and over [npc.her] head.",
					null,
					"[npc.Name] pulls off [npc.her] fishnet top.",
					"[npc.Name] slides your fishnet top up and over your head.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType TORSO_BLOUSE = new AbstractClothingType(400,
			"a",
			false,
			"blouse",
			"blouses",
			"A delicate blouse, made of very fine fabric.",
			0,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"clothing/torso_blouse",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.HEAD),
							null,
							null, null),
					new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH,
									CoverableArea.BACK),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList())),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the blouse.",
					"You guide [npc.namePos] [npc.arms] through the blouse's sleeves as you pull it down over [npc.her] head.",
					null,
					"[npc.Name] pulls on the blouse.",
					"[npc.Name] guides your [pc.arms] through the blouse's sleeves as [npc.she] pulls it down over your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off your blouse.",
					"You slide [npc.namePos] blouse up and over [npc.her] head.",
					null,
					"[npc.Name] pulls off [npc.her] blouse.",
					"[npc.Name] slides your blouse up and over your head.",
					null, null, null);
		}
	};
	

	public static AbstractClothingType TORSO_CAMITOP_STRAPS = new AbstractClothingType(200,
			"a",
			false,
			"cami top",
			"cami tops",
			"A short cami top with straps that loop over the wearer's shoulders. It's short enough that the wearer's stomach is left on display.",
			0,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"clothing/torso_cami_straps",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.HEAD),
							null,
							null, null),
					new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH,
									CoverableArea.BACK),
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO_STOMACH_VISIBLE.getPresetInventorySlotList())),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.FITS_ARM_WINGS)){
	};

	public static AbstractClothingType TORSO_SKATER_DRESS = new AbstractClothingType(250,
			"a",
			false,
			"skater dress",
			"skater dresses",
			"A sleeveless skater dress, held up by a pair of thin straps that loop over the wearer's shoulders.",
			0,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"clothing/torso_skater_dress",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.HEAD),
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH,
									CoverableArea.BACK),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList()),
					new BlockedParts(DisplacementType.PULLS_DOWN,
							null,
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES),
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO_STOMACH_VISIBLE.getPresetInventorySlotList()),
					new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							Util.newArrayListOfValues(
									ClothingAccess.GROIN,
									ClothingAccess.ANUS),
							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList())),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.DRESS, ItemTag.FITS_ARM_WINGS)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the skater dress, tidying the skirt down before moving the straps into a comfortable position on your shoulders.",
					"You pull the skater dress over [npc.namePos] head and down around [npc.her] torso, tidying the skirt before moving the straps to sit comfortably on [npc.her] shoulders.",
					null,
					"[npc.Name] pulls on the skater dress, tidying the skirt down before moving the straps into a comfortable position on [npc.her] shoulders.",
					"[npc.Name] pulls the skater dress over your head and down around your torso, tidying the skirt before moving the straps to sit comfortably on your shoulders.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull your skater dress up over your head and take it off.",
					"You pull [npc.namePos] skater dress up over [npc.her] head and take it off.",
					null,
					"[npc.Name] pulls [npc.her] skater dress up over [npc.her] head and takes it off.",
					"[npc.Name] pulls your skater dress up over your head and takes it off.",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			if(dt==DisplacementType.PULLS_UP) {
				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
						"You pull up the skirt of your skater dress.",
						"You pull up the skirt of [npc.namePos] skater dress.",
						null,
						"[npc.Name] pulls up the skirt of [npc.her] skater dress.",
						"[npc.Name] pulls up the skirt of your skater dress.",
						null, null, null);
			} else {
				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
						"You shrug off the shoulder straps of your skater dress, before tugging it down to reveal your chest.",
						"You slide the straps of [npc.namePos] skater dress down off [npc.her] shoulders, before tugging it down to reveal [npc.her] chest.",
						null,
						"[npc.Name] shrugs off the shoulder straps of [npc.her] skater dress, before tugging it down to reveal [npc.her] chest.",
						"[npc.Name] slides the straps of your skater dress down off your shoulders, before tugging it down to reveal your chest.",
						null, null, null);
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			if(dt==DisplacementType.PULLS_UP) {
				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
						"You pull your skater dress back down into its proper position.",
						"You pull [npc.namePos] skater dress back down into its proper position.",
						null,
						"[npc.Name] pulls [npc.her] skater dress back down into its proper position.",
						"[npc.Name] your skater dress back down into its proper position.",
						null, null, null);
			} else {
				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
						"You pull up the top of your skater dress, before placing the straps over your shoulders.",
						"You pull up the top of [npc.namePos] skater dress, before placing the straps over [npc.her] shoulders.",
						null,
						"[npc.Name] pulls up the top of [npc.her] skater dress, before placing the straps over [npc.her] shoulders.",
						"[npc.Name] pulls up the top of your skater dress, before placing the straps over your shoulders.",
						null, null, null);
			}
		}
	};
	
	public static AbstractClothingType TORSO_CORSET_DRESS = new AbstractClothingType(1250,
			"a",
			false,
			"corset dress",
			"corset dresses",
			"An overbust corset, which is tied up and tightened by a series of strings running up the front."
					+ " A long skirt is attached to the bottom rim, turning it into a dress.",
			0,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"clothing/torso_corset_dress",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.HEAD),
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH,
									CoverableArea.BACK),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList()),
					new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							Util.newArrayListOfValues(
									ClothingAccess.GROIN,
									ClothingAccess.ANUS),
							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList())),
			Util.newArrayListOfValues(
					InventorySlot.STOMACH,
					InventorySlot.CHEST),
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.DRESS, ItemTag.FITS_ARM_WINGS)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slip into the corset dress, before tightening the strings on the front.",
					"You guide [npc.name] into the corset dress, before tightening the strings on the front.",
					null,
					"[npc.Name] slips into the corset dress, before tightening the strings on the front.",
					"[npc.Name] guides you into the corset dress, before tightening the strings on the front.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You untie the corset dress's strings and take it off.",
					"You untie the strings on the front of [npc.namePos] corset dress, before taking it off.",
					null,
					"[npc.Name] unties [npc.her] corset dress's strings and takes it off.",
					"[npc.Name] unties the strings on the front of your corset dress, before taking it off.",
					null, null, null);
		}
	};

	public static AbstractClothingType TORSO_VIRGIN_KILLER_SWEATER = new AbstractClothingType(100,
			"a",
			false,
			"'Virgin-killer' sweater",
			"'Virgin-killer' sweaters",
			"A long, dress-like sweater, with a large portion cut out of the back.",
			0,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"clothing/torso_virgin_killer_sweater",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.HEAD),
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList()),
					new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							Util.newArrayListOfValues(
									ClothingAccess.GROIN,
									ClothingAccess.ANUS),
							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList())),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.FITS_ARM_WINGS)){
	};

	public static AbstractClothingType TORSO_SLIP_DRESS = new AbstractClothingType(800,
			"a",
			false,
			"slip dress",
			"slip dresses",
			"A long, silky, sleeveless dress.",
			0,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"clothing/torso_slip_dress",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.HEAD),
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH,
									CoverableArea.BACK),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList()),
					new BlockedParts(DisplacementType.PULLS_DOWN,
							null,
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES),
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO_STOMACH_VISIBLE.getPresetInventorySlotList()),
					new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							Util.newArrayListOfValues(
									ClothingAccess.GROIN,
									ClothingAccess.ANUS),
							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList())),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.DRESS, ItemTag.FITS_ARM_WINGS)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the slip dress and pull it up around your torso. Once in place, you reach back and zip yourself up.",
					"You guide the slip dress up around [npc.namePos] [npc.legs], before pulling it up around [npc.her] torso. Once it's in place, you zip [npc.herHim] up at the back.",
					null,
					"[npc.Name] steps into the slip dress, before pulling it up around [npc.her] torso. Once in place, [npc.she] reaches back to zip [npc.herself] up.",
					"[npc.Name] guides the slip dress up around your [pc.legs], before pulling it up around your torso. One it's in place, [npc.she] zips you up at the back.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unzip your slip dress and wriggle out of it as it drops to your feet.",
					"You unzip [npc.namePos] slip dress and pull it down off [npc.her] body and past [npc.her] feet.",
					null,
					"[npc.Name] unzips [npc.her] slip dress and wriggles out of it as it drops to [npc.her] feet.",
					"[npc.Name] unzips your slip dress and pulls it down your body and past your feet.",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			if(dt==DisplacementType.PULLS_UP) {
				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
						"You pull up the lower half of your slip dress.",
						"You pull up the lower half of [npc.namePos] slip dress.",
						null,
						"[npc.Name] pulls up the lower half of [npc.her] slip dress.",
						"[npc.Name] pulls up the lower half of your slip dress.",
						null, null, null);
			} else {
				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
						"You shrug off the shoulder straps of your slip dress, before tugging it down to reveal your chest.",
						"You slide the straps of [npc.namePos] slip dress down off [npc.her] shoulders, before tugging it down to reveal [npc.her] chest.",
						null,
						"[npc.Name] shrugs off the shoulder straps of [npc.her] slip dress, before tugging it down to reveal [npc.her] chest.",
						"[npc.Name] slides the straps of your slip dress down off your shoulders, before tugging it down to reveal your chest.",
						null, null, null);
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			if(dt==DisplacementType.PULLS_UP) {
				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
						"You pull your slip dress back down into its proper position.",
						"You pull [npc.namePos] slip dress back down into its proper position.",
						null,
						"[npc.Name] pulls [npc.her] slip dress back down into its proper position.",
						"[npc.Name] your slip dress back down into its proper position.",
						null, null, null);
			} else {
				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
						"You pull up the top of your slip dress, before placing the straps over your shoulders.",
						"You pull up the top of [npc.namePos] slip dress, before placing the straps over [npc.her] shoulders.",
						null,
						"[npc.Name] pulls up the top of [npc.her] slip dress, before placing the straps over [npc.her] shoulders.",
						"[npc.Name] pulls up the top of your slip dress, before placing the straps over your shoulders.",
						null, null, null);
			}
		}
	};

	public static AbstractClothingType TORSO_PLUNGE_DRESS = new AbstractClothingType(600,
			"a",
			false,
			"plunge dress",
			"plunge dresses",
			"An elegant dress with a plunging v-neckline, perfect for showing off its wearer's cleavage.",
			0,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"clothing/torso_plunge_dress",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER),
							Util.newArrayListOfValues(
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH,
									CoverableArea.BACK),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList()),
					new BlockedParts(DisplacementType.PULLS_DOWN,
							null,
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES),
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO_STOMACH_VISIBLE.getPresetInventorySlotList()),
					new BlockedParts(DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							Util.newArrayListOfValues(
									ClothingAccess.GROIN,
									ClothingAccess.ANUS),
							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList())),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.DRESS, ItemTag.FITS_ARM_WINGS)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the plunge dress and pull it up around your torso. Once in place, you reach back and zip yourself up.",
					"You guide the plunge dress up around [npc.namePos] [npc.legs], before pulling it up around [npc.her] torso. Once it's in place, you zip [npc.herHim] up at the back.",
					null,
					"[npc.Name] steps into the plunge dress, before pulling it up around [npc.her] torso. Once in place, [npc.she] reaches back to zip [npc.herself] up.",
					"[npc.Name] guides the plunge dress up around your [pc.legs], before pulling it up around your torso. One it's in place, [npc.she] zips you up at the back.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unzip your plunge dress and wriggle out of it as it drops to your feet.",
					"You unzip [npc.namePos] plunge dress and pull it down off [npc.her] body and past [npc.her] feet.",
					null,
					"[npc.Name] unzips [npc.her] plunge dress and wriggles out of it as it drops to [npc.her] feet.",
					"[npc.Name] unzips your plunge dress and pulls it down your body and past your feet.",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			if(dt==DisplacementType.PULLS_UP) {
				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
						"You pull up the lower half of your plunge dress.",
						"You pull up the lower half of [npc.namePos] plunge dress.",
						null,
						"[npc.Name] pulls up the lower half of [npc.her] plunge dress.",
						"[npc.Name] pulls up the lower half of your plunge dress.",
						null, null, null);
			} else {
				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
						"You shrug off the shoulder straps of your plunge dress, before tugging it down to reveal your chest.",
						"You slide the straps of [npc.namePos] plunge dress down off [npc.her] shoulders, before tugging it down to reveal [npc.her] chest.",
						null,
						"[npc.Name] shrugs off the shoulder straps of [npc.her] plunge dress, before tugging it down to reveal [npc.her] chest.",
						"[npc.Name] slides the straps of your plunge dress down off your shoulders, before tugging it down to reveal your chest.",
						null, null, null);
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			if(dt==DisplacementType.PULLS_UP) {
				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
						"You pull your plunge dress back down into its proper position.",
						"You pull [npc.namePos] plunge dress back down into its proper position.",
						null,
						"[npc.Name] pulls [npc.her] plunge dress back down into its proper position.",
						"[npc.Name] your plunge dress back down into its proper position.",
						null, null, null);
			} else {
				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
						"You pull up the top of your plunge dress, before placing the straps over your shoulders.",
						"You pull up the top of [npc.namePos] plunge dress, before placing the straps over [npc.her] shoulders.",
						null,
						"[npc.Name] pulls up the top of [npc.her] plunge dress, before placing the straps over [npc.her] shoulders.",
						"[npc.Name] pulls up the top of your plunge dress, before placing the straps over your shoulders.",
						null, null, null);
			}
		}
	};
	
	public static AbstractClothingType TORSO_LONG_SLEEVE_DRESS = new AbstractClothingType(400,
			"a",
			false,
			"long-sleeved dress",
			"long-sleeved dresses",
			"A long-sleeved bodycon dress with a high neck.",
			0,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"clothing/torso_long_sleeve_dress",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.HEAD),
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH,
									CoverableArea.BACK),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList()),
					new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							Util.newArrayListOfValues(
									ClothingAccess.GROIN,
									ClothingAccess.ANUS),
							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList())),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.DRESS)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the long-sleeved dress and pull it up around your torso. Once in place, you reach back and zip yourself up.",
					"You guide the long-sleeved dress up around [npc.namePos] [npc.legs], before pulling it up around [npc.her] torso. Once it's in place, you zip [npc.herHim] up at the back.",
					null,
					"[npc.Name] steps into the long-sleeved dress, before pulling it up around [npc.her] torso. Once in place, [npc.she] reaches back to zip [npc.herself] up.",
					"[npc.Name] guides the long-sleeved dress up around your [pc.legs], before pulling it up around your torso. One it's in place, [npc.she] zips you up at the back.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unzip your long-sleeved dress and wriggle out of it as it drops to your feet.",
					"You unzip [npc.namePos] long-sleeved dress and pull it down off [npc.her] body and past [npc.her] feet.",
					null,
					"[npc.Name] unzips [npc.her] long-sleeved dress and wriggles out of it as it drops to [npc.her] feet.",
					"[npc.Name] unzips your long-sleeved dress and pulls it down your body and past your feet.",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull up the lower half of your long-sleeved dress.",
					"You pull up the lower half of [npc.namePos] long-sleeved dress.",
					null,
					"[npc.Name] pulls up the lower half of [npc.her] long-sleeved dress.",
					"[npc.Name] pulls up the lower half of your long-sleeved dress.",
					null, null, null);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull your long-sleeved dress back down into its proper position.",
					"You pull [npc.namePos] long-sleeved dress back down into its proper position.",
					null,
					"[npc.Name] pulls [npc.her] long-sleeved dress back down into its proper position.",
					"[npc.Name] your long-sleeved dress back down into its proper position.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType TORSO_BODYCONZIP_DRESS = new AbstractClothingType(350,
			"a",
			false,
			"frontal-zip dress",
			"frontal-zip dresses",
			"A tight fitting bodycon dress with a zip that runs the entire way up the front.",
			0,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"clothing/torso_bodyconzip_dress",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER),
							Util.newArrayListOfValues(CoverableArea.BACK),
							null, null),
					new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							Util.newArrayListOfValues(
									ClothingAccess.GROIN,
									ClothingAccess.ANUS),
							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList()),
					new BlockedParts(
							DisplacementType.UNZIPS,
							null,
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.PENIS,
									CoverableArea.STOMACH,
									CoverableArea.VAGINA),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST,
									ClothingAccess.GROIN),
							PresetConcealmentLists.CONCEALED_DRESS_FRONT_FULL.getPresetInventorySlotList())),
			null,
			ColourListPresets.ALL,
			null,
			ColourListPresets.JUST_STEEL,
			ColourListPresets.ALL_METAL,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.DRESS, ItemTag.FITS_ARM_WINGS)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You put on the dress and zip yourself up.",
					"You guide [npc.name] into the frontal-zip dress and zip [npc.herHim] up.",
					null,
					"[npc.Name] puts on the frontal-zip dress and zips [npc.herself] up.",
					"[npc.Name] guides you into the frontal-zip dress and zips you up.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You fully unzip your frontal-zip dress and shrug it off.",
					"You fully unzip [npc.namePos] frontal-zip dress and pull it off.",
					null,
					"[npc.Name] fully unzips [npc.her] frontal-zip dress and shrugs it off.",
					"[npc.Name] fully unzips your frontal-zip dress and pulls it off.",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			switch(dt) {
				case PULLS_UP:
					return getEquipDescriptions(clothingOwner, clothingRemover, rough,
							"You pull up the bottom of your frontal-zip dress.",
							"You pull up the bottom of [npc.namePos] frontal-zip dress.",
							null,
							"[npc.Name] pulls up the bottom of [npc.her] frontal-zip dress.",
							"[npc.Name] pulls up the bottom of your frontal-zip dress.",
							null, null, null);
				case UNZIPS:
					return getEquipDescriptions(clothingOwner, clothingRemover, rough,
							"You fully unzip the front of your dress.",
							"You fully unzip the front of [npc.namePos] dress.",
							null,
							"[npc.Name] fully unzips the front of [npc.her] dress.",
							"[npc.Name] fully unzips the front of your dress.",
							null, null, null);
				default:
					return super.displaceText(clothingOwner, clothingRemover, slotToEquipInto, dt, rough);
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			switch(dt) {
				case PULLS_UP:
					return getEquipDescriptions(clothingOwner, clothingRemover, rough,
							"You pull the bottom of your frontal-zip dress back down.",
							"You pull the bottom of [npc.namePos] frontal-zip dress back down.",
							null,
							"[npc.Name] pulls the bottom of [npc.her] frontal-zip dress back down.",
							"[npc.Name] pulls the bottom of your frontal-zip dress back down.",
							null, null, null);
				case UNZIPS:
					return getEquipDescriptions(clothingOwner, clothingRemover, rough,
							"You zip up the front of your dress.",
							"You zip up the front of [npc.namePos] dress.",
							null,
							"[npc.Name] zips up the front of [npc.her] dress.",
							"[npc.Name] zips up the front of your dress.",
							null, null, null);
				default:
					return super.displaceText(clothingOwner, clothingRemover, slotToEquipInto, dt, rough);
			}
		}
	};

	// CHEST

	public static AbstractClothingType CHEST_SWIMSUIT = new AbstractClothingType(250,
			"a",
			false,
			"one-piece swimsuit",
			"one-piece swimsuits",
			"A sporty one-piece swimsuit.",
			0,
			Femininity.FEMININE,
			InventorySlot.CHEST,
			Rarity.COMMON,
			null,
			"clothing/chest_swimsuit",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.LEGS_UP_TO_GROIN,
									ClothingAccess.GROIN,
									ClothingAccess.WAIST,
									ClothingAccess.CHEST),
							Util.newArrayListOfValues(
									CoverableArea.STOMACH,
									CoverableArea.BACK),
							Util.newArrayListOfValues(ClothingAccess.WAIST),
							Util.newArrayListOfValues(InventorySlot.PIERCING_STOMACH)),
					new BlockedParts(DisplacementType.SHIFTS_ASIDE,
							Util.newArrayListOfValues(ClothingAccess.GROIN),
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							Util.newArrayListOfValues(
									ClothingAccess.GROIN,
									ClothingAccess.ANUS),
							Util.newArrayListOfValues(
									InventorySlot.VAGINA,
									InventorySlot.PENIS,
									InventorySlot.PIERCING_PENIS,
									InventorySlot.PIERCING_VAGINA)),
					new BlockedParts(DisplacementType.PULLS_DOWN,
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES),
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							Util.newArrayListOfValues(
									InventorySlot.NIPPLE,
									InventorySlot.PIERCING_NIPPLE))),
			Util.newArrayListOfValues(
					InventorySlot.GROIN,
					InventorySlot.STOMACH),
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the swimsuit and pull it up over your torso.",
					"You guide [npc.name] into the swimsuit and pull it up over [npc.her] torso.",
					null,
					"[npc.Name] steps into the swimsuit and pulls it up to cover [npc.her] torso.",
					"[npc.Name] guides you into the swimsuit and pulls it up over your torso.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide the swimsuit's straps down your [pc.arms] and tug it off your [pc.legs].",
					"You tug down [npc.namePos] swimsuit, before sliding it off [npc.her] [npc.legs].",
					null,
					"[npc.Name] slides the swimsuit's straps down [npc.her] [npc.arms] and tugs it off [npc.her] [npc.legs].",
					"[npc.Name] tugs your swimsuit down, before sliding it off your [pc.legs].",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			switch(dt) {
				case PULLS_DOWN:
					return getEquipDescriptions(clothingOwner, clothingRemover, rough,
							"You pull down the top half of your swimsuit.",
							"You pull down the top half of [npc.namePos] swimsuit.",
							null,
							"[npc.Name] pulls down the top half of [npc.her] swimsuit.",
							"[npc.Name] pulls down the top half of your swimsuit.",
							null, null, null);
				case SHIFTS_ASIDE:
					return getEquipDescriptions(clothingOwner, clothingRemover, rough,
							"You pull the lower part of your swimsuit to one side.",
							"You pull the lower part of [npc.namePos] swimsuit to one side.",
							null,
							"[npc.Name] pulls the lower part of [npc.her] swimsuit to one side.",
							"[npc.Name] pulls the lower part of your swimsuit to one side.",
							null, null, null);
				default:
					return super.displaceText(clothingOwner, clothingRemover, slotToEquipInto, dt, rough);
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			switch(dt) {
				case PULLS_DOWN:
					return getEquipDescriptions(clothingOwner, clothingRemover, rough,
							"You pull your swimsuit back up into its correct position.",
							"You pull [npc.namePos] swimsuit back up into its correct position.",
							null,
							"[npc.Name] pulls [npc.her] swimsuit up into its correct position.",
							"[npc.Name] pulls your swimsuit back up into its correct position.",
							null, null, null);
				case SHIFTS_ASIDE:
					return getEquipDescriptions(clothingOwner, clothingRemover, rough,
							"You pull the lower part of your swimsuit back into its correct position.",
							"You pull the lower part of [npc.namePos] swimsuit back into its correct position.",
							null,
							"[npc.Name] pulls the lower part of [npc.her] swimsuit back into its correct position.",
							"[npc.Name] pulls the lower part of your swimsuit back into its correct position.",
							null, null, null);
				default:
					return super.displaceText(clothingOwner, clothingRemover, slotToEquipInto, dt, rough);
			}
		}
	};

	public static AbstractClothingType CHEST_TUBE_TOP = new AbstractClothingType(80,
			"a",
			false,
			"tube top",
			"tube tops",
			"A tube-shaped strip of stretchy material, designed to fit snugly around the chest. It's small enough to leave the wearer's stomach completely exposed.",
			0,
			Femininity.FEMININE,
			InventorySlot.CHEST,
			Rarity.COMMON,
			null,
			"clothing/torso_tube_top",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.HEAD,
									ClothingAccess.CHEST),
							null,
							null,
							null),
					new BlockedParts(
							DisplacementType.PULLS_UP,
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES),
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO_STOMACH_VISIBLE.getPresetInventorySlotList())),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.FITS_ARM_WINGS)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the tube top.",
					"You guide [npc.namePos] [npc.arms] through the tube top's sleeves as you pull it down over [npc.her] head.",
					null,
					"[npc.Name] pulls on the tube top.",
					"[npc.Name] guides your [pc.arms] through the tube top's sleeves as [npc.she] pulls it down over your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off your tube top.",
					"You slide [npc.namePos] tube top up and over [npc.her] head.",
					null,
					"[npc.Name] pulls off [npc.her] tube top.",
					"[npc.Name] slides your tube top up and over your head.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType CHEST_CHEMISE = new AbstractClothingType(250,
			"a",
			false,
			"chemise",
			"chemises",
			"A silky, see-through chemise, of the sort usually worn as part of a sexy lingerie outfit. It isn't long enough to cover the wearer's groin.",
			0,
			Femininity.FEMININE,
			InventorySlot.CHEST,
			Rarity.COMMON,
			null,
			"clothing/chest_chemise",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.CHEST),
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH,
									CoverableArea.BACK),
							null,
							null) // It's see-through
					),
			null,
			ColourListPresets.LINGERIE,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the chemise.",
					"You pull the chemise onto [npc.name].",
					null,
					"[npc.Name] pulls on the chemise.",
					"[npc.Name] pulls the chemise onto you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off the chemise.",
					"You pull off [npc.namePos] chemise.",
					null,
					"[npc.Name] pulls [npc.her] chemise off.",
					"[npc.Name] pulls your chemise off.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType CHEST_PLUNGE_BRA = new AbstractClothingType(150,
			"a",
			false,
			"plunge bra",
			"plunge bras",
			"A low-cut bra that reveals a lot of the wearer's cleavage.",
			0,
			Femininity.FEMININE,
			InventorySlot.CHEST,
			Rarity.COMMON,
			null,
			"clothing/chest_plunge_bra",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.CHEST),
							null,
							null,
							null),
					new BlockedParts(DisplacementType.PULLS_DOWN,
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							Util.newArrayListOfValues(CoverableArea.NIPPLES),
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							PresetConcealmentLists.CONCEALED_BREASTS.getPresetInventorySlotList())
					),
			null,
			ColourListPresets.LINGERIE,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return braEquipText(clothingOwner, clothingRemover, slotToEquipInto, rough, clothing, applyEffects);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return braUnequipText(clothingOwner, clothingRemover, slotToEquipInto, rough, clothing, applyEffects);
		}
		
		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			return braDisplaceText(clothingOwner, clothingRemover, slotToEquipInto, dt, rough);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			return braReplaceText(clothingOwner, clothingRemover, slotToEquipInto, dt, rough);
		}
	};
	
	public static AbstractClothingType CHEST_OPEN_CUP_BRA = new AbstractClothingType(120,
			"an",
			false,
			"open cup bra",
			"open cup bras",
			"More of a series of straps than a proper bra, this piece of clothing fails to conceal any part of its wearer's breasts.",
			0,
			Femininity.FEMININE,
			InventorySlot.CHEST,
			Rarity.COMMON,
			null,
			"clothing/chest_open_cup_bra",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.CHEST),
							null,
							null,
							null)),
			null,
			ColourListPresets.LINGERIE,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(
					ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return braEquipText(clothingOwner, clothingRemover, slotToEquipInto, rough, clothing, applyEffects);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return braUnequipText(clothingOwner, clothingRemover, slotToEquipInto, rough, clothing, applyEffects);
		}
	};
	public static AbstractClothingType CHEST_SPORTS_BRA = new AbstractClothingType(150,
			"a",
			false,
			"sports bra",
			"sports bras",
			"An elastic sports bra that's designed to firmly support the wearer's breasts during exercise.",
			0,
			Femininity.FEMININE,
			InventorySlot.CHEST,
			Rarity.COMMON,
			null,
			"clothing/chest_sports_bra",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.CHEST),
							Util.newArrayListOfValues(CoverableArea.BACK),
							null, null),
					new BlockedParts(DisplacementType.PULLS_UP,
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES),
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							PresetConcealmentLists.CONCEALED_BREASTS.getPresetInventorySlotList())
					),
			null,
			ColourListPresets.LINGERIE,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull the sports bra down over your head, before securing it around your chest.",
					"You pull the sports bra down over [npc.namePos] head, before securing it around [npc.her] chest.",
					null,
					"[npc.Name] pulls the sports bra down over [npc.her] head, before securing it around [npc.her] chest.",
					"[npc.Name] pulls the sports bra down over your head, before securing it around your chest.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull the sports bra off over your head.",
					"You pull [npc.namePos] sports bra off over [npc.her] head.",
					null,
					"[npc.Name] pulls [npc.her] sports bra off over [npc.her] head.",
					"[npc.Name] pulls your sports bra off over your head.",
					null, null, null);
		}
	};
	public static AbstractClothingType CHEST_CROPTOP_BRA = new AbstractClothingType(100,
			"a",
			false,
			"croptop bra",
			"croptop bras",
			"A loose fitting bra which resembles a short croptop.",
			0,
			Femininity.FEMININE,
			InventorySlot.CHEST,
			Rarity.COMMON,
			null,
			"clothing/chest_croptop_bra",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.CHEST),
							null,
							null, null),
					new BlockedParts(DisplacementType.PULLS_UP,
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES),
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							PresetConcealmentLists.CONCEALED_BREASTS.getPresetInventorySlotList())),
			null,
			ColourListPresets.LINGERIE,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull the croptop bra down over your head, before securing it around your chest.",
					"You pull the croptop bra down over [npc.namePos] head, before securing it around [npc.her] chest.",
					null,
					"[npc.Name] pulls the croptop bra down over [npc.her] head, before securing it around [npc.her] chest.",
					"[npc.Name] pulls the croptop bra down over your head, before securing it around your chest.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull the croptop bra off over your head.",
					"You pull [npc.namePos] croptop bra off over [npc.her] head.",
					null,
					"[npc.Name] pulls [npc.her] croptop bra off over [npc.her] head.",
					"[npc.Name] pulls your croptop bra off over your head.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType CHEST_FULLCUP_BRA = new AbstractClothingType(150,
			"a",
			false,
			"fullcup bra",
			"fullcup bras",
			"A fullcup bra, it conceals a large portion of the wearer's breasts.",
			0,
			Femininity.FEMININE,
			InventorySlot.CHEST,
			Rarity.COMMON,
			null,
			"clothing/chest_fullcup_bra",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.CHEST),
							null,
							null,
							null),
					new BlockedParts(DisplacementType.PULLS_DOWN,
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							Util.newArrayListOfValues(CoverableArea.NIPPLES),
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							PresetConcealmentLists.CONCEALED_BREASTS.getPresetInventorySlotList())),
			null,
			ColourListPresets.LINGERIE,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return braEquipText(clothingOwner, clothingRemover, slotToEquipInto, rough, clothing, applyEffects);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return braUnequipText(clothingOwner, clothingRemover, slotToEquipInto, rough, clothing, applyEffects);
		}
		
		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			return braDisplaceText(clothingOwner, clothingRemover, slotToEquipInto, dt, rough);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			return braReplaceText(clothingOwner, clothingRemover, slotToEquipInto, dt, rough);
		}
	};

	public static AbstractClothingType CHEST_NURSING_BRA = new AbstractClothingType(250,
			"a",
			false,
			"nursing bra",
			"nursing bras",
			"This nursing bra provides additional support to lactating breasts, and has specially designed cups which can be opened to expose the wearer's nipples.",
			0,
			Femininity.FEMININE,
			InventorySlot.CHEST,
			Rarity.COMMON,
			null,
			"clothing/chest_nursing_bra",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.CHEST),
							null,
							null, null),
					new BlockedParts(DisplacementType.PULLS_DOWN,
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							Util.newArrayListOfValues(CoverableArea.NIPPLES),
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							PresetConcealmentLists.CONCEALED_BREASTS.getPresetInventorySlotList())
					),
			null,
			ColourListPresets.LINGERIE,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return braEquipText(clothingOwner, clothingRemover, slotToEquipInto, rough, clothing, applyEffects);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return braUnequipText(clothingOwner, clothingRemover, slotToEquipInto, rough, clothing, applyEffects);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unclip your nursing bra's cups and pull them down, exposing your [pc.nipples+].",
					"You unclip the cups on [npc.namePos] nursing bra and pull them down, exposing [npc.her] [npc.nipples+].",
					null,
					"[npc.Name] unclips [npc.her] nursing bra's cups and pulls them down, exposing [npc.her] [npc.nipples+].",
					"[npc.Name] unclips your nursing bra's cups and pulls them down, exposing your [pc.nipples+].",
					null, null, null);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You clip your nursing bra's cups back up to cover your [pc.nipples].",
					"You clip [npc.namePos] nursing bra's cups back up to cover [npc.her] [npc.nipples].",
					null,
					"[npc.Name] clips [npc.her] nursing bra's cups back up to cover [npc.her] [npc.nipples].",
					"[npc.Name] clips your nursing bra's cups back up to cover your [pc.nipples].",
					null, null, null);
		}
	};

	public static AbstractClothingType CHEST_STRIPED_BRA = new AbstractClothingType(150,
			"a",
			false,
			"striped bra",
			"striped bras",
			"A cotton bikini-style bra, designed to match with a pair of striped panties.",
			0,
			Femininity.FEMININE,
			InventorySlot.CHEST,
			Rarity.COMMON,
			null,
			"clothing/chest_striped_bra",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.CHEST),
							null,
							null, null),
					new BlockedParts(DisplacementType.PULLS_DOWN,
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							Util.newArrayListOfValues(CoverableArea.NIPPLES),
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							PresetConcealmentLists.CONCEALED_BREASTS.getPresetInventorySlotList())),
			null,
			ColourListPresets.NOT_WHITE,
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You place the bra over your chest before tying up the strings at your back.",
					"You place the bra over [npc.namePos] chest before tying up the strings at [npc.her] back.",
					null,
					"[npc.Name] places the bra over [npc.her] chest before tying up the strings at [npc.her] back.",
					"[npc.Name] places the bra over your chest before tying up the strings at your back.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You untie the bra's strings and take it off.",
					"You untie [npc.namePos] bra's strings and take it off.",
					null,
					"[npc.Name] unties [npc.her] bra's strings and takes it off.",
					"[npc.Name] unties your bra's strings and takes it off.",
					null, null, null);
		}
	};

	public static AbstractClothingType CHEST_BIKINI = new AbstractClothingType(120,
			"a",
			false,
			"bikini top",
			"bikini tops",
			"A triangle-shaped bikini top. It uses loose strings tied at the back to keep it in place.",
			0,
			Femininity.FEMININE,
			InventorySlot.CHEST,
			Rarity.COMMON,
			null,
			"clothing/chest_bikini",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.CHEST),
							null,
							null, null),
					new BlockedParts(DisplacementType.PULLS_UP,
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							Util.newArrayListOfValues(CoverableArea.NIPPLES),
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							PresetConcealmentLists.CONCEALED_BREASTS.getPresetInventorySlotList())
					),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You place the bikini top over your chest before tying up the strings at your back.",
					"You place the bikini top over [npc.namePos] chest before tying up the strings at [npc.her] back.",
					null,
					"[npc.Name] places the bikini top over [npc.her] chest before tying up the strings at [npc.her] back.",
					"[npc.Name] places the bikini top over your chest before tying up the strings at your back.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You untie the bikini top's strings and take it off.",
					"You untie [npc.namePos] bikini top's strings and take it off.",
					null,
					"[npc.Name] unties [npc.her] bikini top's strings and takes it off.",
					"[npc.Name] unties your bikini top's strings and takes it off.",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide your bikini top's straps off your shoulders before tugging it down to reveal your chest.",
					"You slide the straps of [npc.namePos] bikini top off [npc.her] shoulders before tugging it down to reveal [npc.her] chest.",
					null,
					"[npc.Name] slides [npc.her] bikini top's straps off [npc.her] shoulders before tugging it down to reveal [npc.her] chest.",
					"[npc.Name] slides your bikini top's straps off your shoulders before tugging it down to reveal your chest.",
					null, null, null);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull your bikini top back up to cover your chest.",
					"You pull [npc.namePos] bikini top back up to cover [npc.her] chest.",
					null,
					"[npc.Name] pulls [npc.her] bikini top back up to cover [npc.her] chest.",
					"[npc.Name] pulls your bikini top back up to cover your chest.",
					null, null, null);
		}
	};

	public static AbstractClothingType CHEST_SARASHI = new AbstractClothingType(200,
			"a",
			false,
			"chest sarashi",
			"chest sarashis",
			"A long strip of thick cotton, wrapped around the chest as an added layer of protection, or to flatten the wearer's breasts.",
			0,
			null,
			InventorySlot.CHEST,
			Rarity.COMMON,
			null,
			"clothing/chest_sarashi",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.BACK),
							null,
							PresetConcealmentLists.CONCEALED_BREASTS.getPresetInventorySlotList())),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You wrap the sarashi tightly around your chest.",
					"You wrap the sarashi tightly around [npc.namePos] chest.",
					null,
					"[npc.Name] wraps the sarashi tightly around [npc.her] chest.",
					"[npc.Name] wraps the sarashi tightly around your chest.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unwrap the sarashi and remove it from around your chest.",
					"You unwrap the sarashi from around [npc.namePos] chest.",
					null,
					"[npc.Name] unwraps the sarashi from around [npc.her] chest.",
					"[npc.Name] unwraps the sarashi from around your chest.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType NIPPLE_TAPE_CROSSES = new AbstractClothingType(10,
			"a pair of",
			true,
			"tape cross",
			"tape crosses",
			"A pair of crosses, made out of shiny electrician's tape."
					+ " They are only just large enough to fully cover a pair of nipples.",
			0,
			null,
			InventorySlot.NIPPLE,
			Rarity.COMMON,
			null,
			"clothing/chest_tapecrosses",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							Util.newArrayListOfValues(CoverableArea.NIPPLES),
							null,
							Util.newArrayListOfValues(InventorySlot.PIERCING_NIPPLE))),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(
					ItemTag.SEALS_NIPPLES,
					ItemTag.SOLD_BY_NYAN)){
		
		/* TODO:
			Replace with special item "roll of tape"
			10 uses, and can:
				tape mouth
				bind wrists
				bind feet
				tape nipples
				tape pussy
				tape asshole
		*/
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You stick the tape crosses over your [pc.nipples].",
					"You stick the tape crosses over [npc.namePos] [npc.nipples].",
					null,
					"[npc.Name] sticks the tape crosses over [npc.her] [npc.nipples].",
					"[npc.Name] sticks the tape crosses over your [pc.nipples].",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You wince as you peel the tape crosses off your [pc.nipples].",
					"[npc.Name] winces and lets out a little cry as you peel the tape crosses off [npc.her] [npc.nipples].",
					null,
					"[npc.Name] winces as [npc.she] peels the tape crosses off [npc.her] [npc.nipples].",
					"You wince and let out a little cry as [npc.name] peels the tape crosses off your [pc.nipples].",
					null, null, null);
		}
	};

	// STOMACH
	// TODO I got to here with the equip/unequip tidying work.

	public static AbstractClothingType STOMACH_LOWBACK_BODY = new AbstractClothingType(450,
			"a",
			false,
			"lowback bodysuit",
			"lowback bodysuits",
			"A bodysuit with a scooped low back and removable straps.",
			0,
			Femininity.FEMININE,
			InventorySlot.STOMACH,
			Rarity.COMMON,
			null,
			"clothing/stomach_lowback_body",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.LEGS_UP_TO_GROIN,
									ClothingAccess.GROIN,
									ClothingAccess.WAIST,
									ClothingAccess.CHEST),
							Util.newArrayListOfValues(
									CoverableArea.STOMACH,
									CoverableArea.BACK),
							Util.newArrayListOfValues(ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_STOMACH.getPresetInventorySlotList()),
					new BlockedParts(DisplacementType.SHIFTS_ASIDE, null,
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							Util.newArrayListOfValues(
									ClothingAccess.GROIN,
									ClothingAccess.ANUS),
							PresetConcealmentLists.CONCEALED_GENITALS.getPresetInventorySlotList()),
					new BlockedParts(DisplacementType.PULLS_DOWN,
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							Util.newArrayListOfValues(CoverableArea.NIPPLES),
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							PresetConcealmentLists.CONCEALED_BREASTS.getPresetInventorySlotList())),
			Util.newArrayListOfValues(
					InventorySlot.GROIN,
					InventorySlot.CHEST),
			ColourListPresets.LINGERIE,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the bodysuit and pull it up over your torso.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] steps into " + clothing.getName(true) + " and pulls it up to cover [npc.her] torso.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You slide the bodysuit's straps down your arms and tug it down off your legs.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner,
						"[npc.Name] slides the bodysuit's straps down [npc.her] arms and tugs it down off [npc.her] legs.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] tugs your bodysuit down, sliding it off your legs.";
				else
					return UtilText.parse(clothingOwner, "You tug down [npc.namePos] bodysuit, sliding it off [npc.her] legs.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			if (dt == DisplacementType.PULLS_DOWN) {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull down the top half of your bodysuit.";
				else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
					return UtilText.parse(clothingOwner, "[npc.Name] pulls down the top half of [npc.her] bodysuit.");
				else {
					if (clothingOwner.isPlayer())
						return "[npc.Name] pulls down the top half of your bodysuit.";
					else
						return UtilText.parse(clothingOwner, "You pull down the top half of [npc.namePos] bodysuit.");
				}
			} else {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull the lower part of your bodysuit to one side.";
				else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
					return UtilText.parse(clothingOwner, "[npc.Name] pulls the lower part of [npc.her] bodysuit to one side.");
				else {
					if (clothingOwner.isPlayer())
						return "[npc.Name] pulls the lower part of your bodysuit to one side.";
					else
						return UtilText.parse(clothingOwner, "You pull the lower part of [npc.namePos] bodysuit to one side.");
				}
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			if (dt == DisplacementType.PULLS_DOWN) {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull your bodysuit back up into its correct position.";
				else
					return UtilText.parse(clothingOwner, "[npc.Name] pulls [npc.her] bodysuit up into its correct position.");
			} else {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull the lower part of your bodysuit back into its correct position.";
				else
					return UtilText.parse(clothingOwner, "[npc.Name] pulls the lower part of [npc.her] bodysuit back into its correct position.");
			}
		}
	};
	public static AbstractClothingType STOMACH_UNDERBUST_CORSET = new AbstractClothingType(200,
			"an",
			false,
			"underbust corset",
			"underbust corsets",
			"A corset that keeps the wearer's stomach nicely shaped, while leaving their breasts totally exposed. It has a series of laces at the back that are used to tighten it.",
			0,
			Femininity.FEMININE,
			InventorySlot.STOMACH,
			Rarity.COMMON,
			null,
			"clothing/stomach_underbust_corset",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
						DisplacementType.REMOVE_OR_EQUIP,
						Util.newArrayListOfValues(ClothingAccess.WAIST),
						Util.newArrayListOfValues(
								CoverableArea.STOMACH,
								CoverableArea.BACK),
						Util.newArrayListOfValues(ClothingAccess.WAIST),
						PresetConcealmentLists.CONCEALED_STOMACH.getPresetInventorySlotList())),
			null,
			ColourListPresets.LINGERIE,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You place the corset around your stomach before tying the laces up at your back.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] places " + clothing.getName(true) + " around [npc.her] stomach before tying the laces up at [npc.her] back.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You untie the corset's laces and remove it from your stomach.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] unties [npc.her] corset's laces and removes it from [npc.her] stomach.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] unties your corset's laces and removes it from your stomach.";
				else
					return UtilText.parse(clothingOwner, "You untie [npc.namePos] corset's laces and remove it from [npc.her] stomach.");
			}
		}
	};
	public static AbstractClothingType STOMACH_OVERBUST_CORSET = new AbstractClothingType(500,
			"an",
			false,
			"overbust corset",
			"overbust corsets",
			"A corset that keeps the wearer's stomach nicely shaped, while also covering their breasts. It has a series of laces at the back that are used to tighten it.",
			0,
			Femininity.FEMININE,
			InventorySlot.STOMACH,
			Rarity.COMMON,
			null, "clothing/stomach_overbust_corset",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.WAIST,
									ClothingAccess.CHEST),
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH,
									CoverableArea.BACK),
							Util.newArrayListOfValues(
									ClothingAccess.WAIST,
									ClothingAccess.CHEST),
							Util.newArrayListOfValues(
									InventorySlot.NIPPLE,
									InventorySlot.PIERCING_NIPPLE,
									InventorySlot.PIERCING_STOMACH))),
			Util.newArrayListOfValues(InventorySlot.CHEST),
			ColourListPresets.LINGERIE,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You place the corset around your stomach before tying the laces up at your back.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] places " + clothing.getName(true) + " around [npc.her] stomach before tying the laces up at [npc.her] back.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You untie the corset's laces and remove it from your stomach.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] unties [npc.her] corset's laces and removes it from [npc.her] stomach.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] unties your corset's laces and removes it from your stomach.";
				else
					return UtilText.parse(clothingOwner, "You untie [npc.namePos] corset's laces and remove it from [npc.her] stomach.");
			}
		}
	};
	
	public static AbstractClothingType STOMACH_SARASHI = new AbstractClothingType(200,
			"a",
			false,
			"stomach sarashi",
			"stomach sarashis",
			"A long strip of thick cotton, wrapped around the stomach as an added layer of protection, or to gain a slim figure.",
			0,
			null,
			InventorySlot.STOMACH,
			Rarity.COMMON,
			null,
			"clothing/stomach_sarashi",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
						DisplacementType.REMOVE_OR_EQUIP,
						Util.newArrayListOfValues(ClothingAccess.WAIST),
						Util.newArrayListOfValues(
								CoverableArea.STOMACH,
								CoverableArea.BACK),
						Util.newArrayListOfValues(ClothingAccess.WAIST),
						PresetConcealmentLists.CONCEALED_STOMACH.getPresetInventorySlotList())),
			null,
			ColourListPresets.LINGERIE,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You wrap the sarashi around your stomach.";
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] wraps " + clothing.getName(true) + " around [npc.her] stomach.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You unwrap the sarashi remove it from your stomach.";
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] unwraps the sarashi from around [npc.her] stomach.");
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingRemover, "[npc.Name] unwraps your sarashi and removes it from around your stomach.");
				} else {
					return UtilText.parse(clothingOwner, "You unwrap [npc.namePos] sarashi and remove it from around [npc.her] stomach.");
				}
			}
		}
	};

	// WRIST

	public static AbstractClothingType WRIST_WOMENS_WATCH = new AbstractClothingType(1000,
			"a",
			false,
			"feminine watch",
			"feminine watches",
			"A feminine-looking watch. Like others of its kind, its primary purpose is to keep track of the time.",
			0,
			Femininity.FEMININE,
			InventorySlot.WRIST,
			Rarity.COMMON,
			null,
			"clothing/wrist_womens_watch",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.WRISTS),
							null,
							Util.newArrayListOfValues(ClothingAccess.WRISTS),
							null)),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You place the watch on your wrist and fasten the strap.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] places " + clothing.getName(true) + " on [npc.her] wrist and fastens the strap.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You unfasten your watch's strap and take it off.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] unfastens [npc.her] watch's strap and takes it off.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] unfastens your watch's strap and takes it off.";
				else
					return UtilText.parse(clothingOwner, "You unfasten the strap on [npc.namePos] watch and take it off.");
			}
		}
	};
	
	public static AbstractClothingType WRIST_MENS_WATCH = new AbstractClothingType(1200,
			"a",
			false,
			"masculine watch",
			"masculine watches",
			"A masculine-looking watch. Like others of its kind, its primary purpose is to keep track of the time.",
			0,
			Femininity.MASCULINE,
			InventorySlot.WRIST,
			Rarity.COMMON,
			null,
			"clothing/wrist_mens_watch",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.WRISTS),
							null,
							Util.newArrayListOfValues(ClothingAccess.WRISTS),
							null)),
			null,
			ColourListPresets.ALL_METAL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You place the watch on your wrist and fasten the strap.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] places " + clothing.getName(true) + " on [npc.her] wrist and fastens the strap.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You unfasten your watch's strap and take it off.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] unfastens [npc.her] watch's strap and takes it off.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] unfastens your watch's strap and takes it off.";
				else
					return UtilText.parse(clothingOwner, "You unfasten the strap on [npc.namePos] watch and take it off.");
			}
		}
	};
	
	public static AbstractClothingType WRIST_BANGLE = new AbstractClothingType(600,
			"a",
			false,
			"bangle",
			"bangles",
			"A simple metal bangle that fits comfortably around the wearer's wrist.",
			0,
			Femininity.FEMININE,
			InventorySlot.WRIST,
			Rarity.COMMON,
			null,
			"clothing/wrist_bangle",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.WRISTS),
							null,
							Util.newArrayListOfValues(ClothingAccess.WRISTS),
							null)),
			null,
			ColourListPresets.ALL_METAL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide the bangle onto your wrist.",
					"You slide the bangle onto [npc.namePos] wrist.",
					null,
					"[npc.Name] slides the bangle onto [npc.her] wrist.",
					"[npc.Name] slides the bangle onto your wrist.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide your bangle off of your wrist.",
					"You slide [npc.namePos] bangle off of [npc.her] wrist.",
					null,
					"[npc.Name] slides [npc.her] bangle off of [npc.her] wrist.",
					"[npc.Name] slides your bangle off of your wrist.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType WRIST_SUIT_CUFFS = new AbstractClothingType(100,
			"a pair of",
			true,
			"suit cuff",
			"suit cuffs",
			"A pair of suit cuffs, which are worn around the wrists.",
			0,
			null,
			InventorySlot.WRIST,
			Rarity.COMMON,
			null,
			"clothing/wrist_suit_cuffs",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.WRISTS),
							null,
							Util.newArrayListOfValues(ClothingAccess.WRISTS),
							null)),
			null,
			ColourListPresets.JUST_WHITE, ColourListPresets.ALL,
			ColourListPresets.JUST_BLACK, ColourListPresets.ALL,
			null,
			null, 
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You fasten the suit cuffs around your wrists.",
					"You fasten the suit cuffs around [npc.namePos] wrists.",
					null,
					"[npc.Name] fastens the suit cuffs around [npc.her] wrists.",
					"[npc.Name] fastens the suit cuffs around your wrists.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unfasten your suit cuffs and take them off.",
					"You unfasten [npc.namePos] suit cuffs and take them off.",
					null,
					"[npc.Name] unfastens [npc.her] suit cuffs and takes them off.",
					"[npc.Name] unfastens your suit cuffs and takes them off.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType WRIST_WRISTBANDS = new AbstractClothingType(100,
			"a pair of",
			true,
			"wristband",
			"wristbands",
			"A pair of wristbands, designed to worn around the wrists in order to absorb the wearer's sweat.",
			0,
			null,
			InventorySlot.WRIST,
			Rarity.COMMON,
			null,
			"clothing/wrist_sweatbands",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.WRISTS),
							null,
							Util.newArrayListOfValues(ClothingAccess.WRISTS),
							null)),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You fasten the wristband around your wrists.",
					"You fasten the wristband around [npc.namePos] wrists.",
					null,
					"[npc.Name] fastens the wristband around [npc.her] wrists.",
					"[npc.Name] fastens the wristband around your wrists.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unfasten your wristband and take them off.",
					"You unfasten [npc.namePos] wristband and take them off.",
					null,
					"[npc.Name] unfastens [npc.her] wristband and takes them off.",
					"[npc.Name] unfastens your wristband and takes them off.",
					null, null, null);
		}
	};

	// BELT
	
	public static AbstractClothingType HIPS_CONDOMS = new AbstractClothingType(20,
			"a",
			false,
			"condom belt",
			"condom belts",
			"A strip of strong elastic fabric that hugs the wearer's hips. It doesn't really serve much use, but you could always tie used condoms to it...",
			0,
			null,
			InventorySlot.HIPS,
			Rarity.COMMON,
			null,
			"clothing/belt_used_condoms",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							null,
							null,
							null,
							null)),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You step into the belt before pulling it up to your waist.";
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] steps into the belt before pulling it up to [npc.her] waist.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull down your belt and kick it off your [pc.feet].";
				
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] belt before kicking it off [npc.her] [npc.feet].");
				
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingRemover, "[npc.Name] pulls your belt down and slides it off your [pc.feet].");
				} else {
					return UtilText.parse(clothingOwner, "You pull [npc.namePos] belt down and slide it off [npc.her] [npc.feet].");
				}
			}
		}
	};
	
	public static AbstractClothingType HIPS_SUSPENDER_BELT = new AbstractClothingType(150,
			"a",
			false,
			"suspender belt",
			"suspender belts",
			"A circular band of soft, elastic nylon, to which are attached six straps; each of which is designed to hook onto a pair of stockings in order to keep them up.",
			0,
			Femininity.FEMININE,
			InventorySlot.HIPS,
			Rarity.COMMON,
			null,
			"clothing/hips_suspender_belt",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							null,
							null,
							null,
							null)),
			null,
			ColourListPresets.LINGERIE, ColourListPresets.ALL,
			ColourListPresets.JUST_STEEL, ColourListPresets.ALL_METAL,
			null,
			null, 
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the suspender belt before pulling it up to your waist.",
					"You get [npc.name] to step into the suspender belt before pulling it up to [npc.her] waist.",
					null,
					"[npc.Name] steps into the suspender belt before pulling it up to [npc.her] waist.",
					"[npc.Name] gets you to step into the suspender belt before pulling it up to your waist.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your suspender belt and take it off.",
					"You pull down [npc.namePos] suspender belt and take it off.",
					null,
					"[npc.Name] pulls down [npc.her] suspender belt and takes it off.",
					"[npc.Name] pulls down your suspender belt and takes it off.",
					null, null, null);
		}
	};
	

	// GROIN

	public static AbstractClothingType GROIN_PANTIES = new AbstractClothingType(100, //TODO
			"a pair of",
			true,
			"panties",
			"panties",
			"A pair of distinctly feminine panties, made from soft cotton.",
			0,
			Femininity.FEMININE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"clothing/groin_panties",
			null,

			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL,
									ClothingAccess.LEGS_UP_TO_GROIN,
									ClothingAccess.GROIN),
							null,
							null,
							null),
					new BlockedParts(DisplacementType.SHIFTS_ASIDE,
							Util.newArrayListOfValues(
									ClothingAccess.GROIN),
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							null,
							PresetConcealmentLists.CONCEALED_GENITALS.getPresetInventorySlotList())),
			null,
			ColourListPresets.LINGERIE,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the panties before pulling them up to cover your private parts.",
					"You pull the panties up [npc.namePos] [npc.legs] to cover [npc.her] private parts.",
					null,
					"[npc.Name] steps into the panties before pulling them up to cover [npc.her] private parts.",
					"[npc.Name] pulls the panties up your [pc.legs] to cover your private parts.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your panties and kick them off your [pc.feet].",
					"You pull down [npc.namePos] panties and slide them off [npc.her] [npc.feet].",
					null,
					"[npc.Name] pulls [npc.her] panties down and kicks them off [npc.her] [npc.feet].",
					"[npc.Name] pulls your panties down and slides them off your [pc.feet].",
					null, null, null);
		}
	};
	
	public static AbstractClothingType GROIN_SHIMAPAN = new AbstractClothingType(100,
			"a pair of",
			true,
			"striped panties",
			"striped panties",
			"A pair of distinctly feminine, striped panties, made from soft cotton. They are sometimes referred to as 'shima-pantsu', or 'shimapan' (striped panties).",
			0,
			Femininity.FEMININE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"clothing/groin_shimapan",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL,
									ClothingAccess.LEGS_UP_TO_GROIN,
									ClothingAccess.GROIN),
							null,
							null,
							null),
					new BlockedParts(DisplacementType.SHIFTS_ASIDE,
							Util.newArrayListOfValues(
									ClothingAccess.GROIN),
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							null,
							PresetConcealmentLists.CONCEALED_GENITALS.getPresetInventorySlotList())),
			null,
			ColourListPresets.NOT_WHITE,
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the panties before pulling them up to cover your private parts.",
					"You pull the panties up [npc.namePos] [npc.legs] to cover [npc.her] private parts.",
					null,
					"[npc.Name] steps into the panties before pulling them up to cover [npc.her] private parts.",
					"[npc.Name] pulls the panties up your [pc.legs] to cover your private parts.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your panties and kick them off your [pc.feet].",
					"You pull down [npc.namePos] panties and slide them off [npc.her] [npc.feet].",
					null,
					"[npc.Name] pulls [npc.her] panties down and kicks them off [npc.her] [npc.feet].",
					"[npc.Name] pulls your panties down and slides them off your [pc.feet].",
					null, null, null);
		}
	};
	
	public static AbstractClothingType GROIN_LACY_PANTIES = new AbstractClothingType(150,
			"a pair of",
			true,
			"lacy panties",
			"lacy panties",
			"A pair of distinctly feminine panties, made from soft cotton. This particular design has large sections of semi-transparent fabric, and is trimmed with lace.",
			0,
			Femininity.FEMININE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"clothing/groin_lacy_panties",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL,
									ClothingAccess.LEGS_UP_TO_GROIN,
									ClothingAccess.GROIN),
							null,
							null,
							null),
					new BlockedParts(DisplacementType.SHIFTS_ASIDE,
							Util.newArrayListOfValues(
									ClothingAccess.GROIN),
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							null,
							PresetConcealmentLists.CONCEALED_GENITALS.getPresetInventorySlotList())),
			null,
			ColourListPresets.LINGERIE,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the panties before pulling them up to cover your private parts.",
					"You pull the panties up [npc.namePos] [npc.legs] to cover [npc.her] private parts.",
					null,
					"[npc.Name] steps into the panties before pulling them up to cover [npc.her] private parts.",
					"[npc.Name] pulls the panties up your [pc.legs] to cover your private parts.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your panties and kick them off your [pc.feet].",
					"You pull down [npc.namePos] panties and slide them off [npc.her] [npc.feet].",
					null,
					"[npc.Name] pulls [npc.her] panties down and kicks them off [npc.her] [npc.feet].",
					"[npc.Name] pulls your panties down and slides them off your [pc.feet].",
					null, null, null);
		}
	};

	public static AbstractClothingType GROIN_VSTRING = new AbstractClothingType(200,
			"a pair of",
			true,
			"v-string panties",
			"v-string panties",
			"A pair of v-string panties made from thin cotton.",
			0,
			Femininity.FEMININE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"clothing/groin_vstring",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL,
									ClothingAccess.LEGS_UP_TO_GROIN,
									ClothingAccess.GROIN),
							null, null, null),
					new BlockedParts(
							DisplacementType.SHIFTS_ASIDE,
							Util.newArrayListOfValues(
									ClothingAccess.GROIN),
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							null,
							PresetConcealmentLists.CONCEALED_GENITALS.getPresetInventorySlotList())),
			null,
			ColourListPresets.LINGERIE,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the v-string panties before pulling them up to cover your private parts.",
					"You pull the v-string panties up [npc.namePos] [npc.legs] to cover [npc.her] private parts.",
					null,
					"[npc.Name] steps into the v-string panties before pulling them up to cover [npc.her] private parts.",
					"[npc.Name] pulls the v-string panties up your [pc.legs] to cover your private parts.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your v-string panties and kick them off your [pc.feet].",
					"You pull down [npc.namePos] v-string panties and slide them off [npc.her] [npc.feet].",
					null,
					"[npc.Name] pulls [npc.her] v-string panties down and kicks them off [npc.her] [npc.feet].",
					"[npc.Name] pulls your v-string panties down and slides them off your [pc.feet].",
					null, null, null);
		}
	};
	
	public static AbstractClothingType GROIN_THONG = new AbstractClothingType(200,
			"a",
			false,
			"thong",
			"thongs",
			"A thong with a decorative cross stitched into the front.",
			0,
			Femininity.FEMININE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"clothing/groin_thong",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL,
									ClothingAccess.LEGS_UP_TO_GROIN,
									ClothingAccess.GROIN),
							null,
							null,
							null),
					new BlockedParts(
							DisplacementType.SHIFTS_ASIDE,
							Util.newArrayListOfValues(
									ClothingAccess.GROIN),
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							null,
							PresetConcealmentLists.CONCEALED_GENITALS.getPresetInventorySlotList())),
			null,
			ColourListPresets.LINGERIE,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the thong before pulling them up to cover your private parts.",
					"You pull the thong up [npc.namePos] [npc.legs] to cover [npc.her] private parts.",
					null,
					"[npc.Name] steps into the thong before pulling them up to cover [npc.her] private parts.",
					"[npc.Name] pulls the thong up your [pc.legs] to cover your private parts.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your thong and kick them off your [pc.feet].",
					"You pull down [npc.namePos] thong and slide them off [npc.her] [npc.feet].",
					null,
					"[npc.Name] pulls [npc.her] thong down and kicks them off [npc.her] [npc.feet].",
					"[npc.Name] pulls your thong down and slides them off your [pc.feet].",
					null, null, null);
		}
	};
	
	public static AbstractClothingType GROIN_BIKINI = new AbstractClothingType(150,
			"a pair of",
			true,
			"bikini bottoms",
			"bikini bottoms",
			"The part of a bikini that covers the wearer's private parts.",
			0,
			Femininity.FEMININE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"clothing/groin_bikini",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL,
									ClothingAccess.LEGS_UP_TO_GROIN,
									
									ClothingAccess.GROIN),
							null,
							null,
							null),
					new BlockedParts(
							DisplacementType.SHIFTS_ASIDE,
							Util.newArrayListOfValues(
									ClothingAccess.GROIN),
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							null,
							PresetConcealmentLists.CONCEALED_GENITALS.getPresetInventorySlotList())),
			null,
			ColourListPresets.LINGERIE,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the bikini bottoms before pulling them up to cover your private parts.",
					"You pull the bikini bottoms up [npc.namePos] [npc.legs] to cover [npc.her] private parts.",
					null,
					"[npc.Name] steps into the bikini bottoms before pulling them up to cover [npc.her] private parts.",
					"[npc.Name] pulls the bikini bottoms up your [pc.legs] to cover your private parts.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your bikini bottoms and kick them off your [pc.feet].",
					"You pull down [npc.namePos] bikini bottoms and slide them off [npc.her] [npc.feet].",
					null,
					"[npc.Name] pulls [npc.her] bikini bottoms down and kicks them off [npc.her] [npc.feet].",
					"[npc.Name] pulls your bikini bottoms down and slides them off your [pc.feet].",
					null, null, null);
		}
	};
	
	public static AbstractClothingType GROIN_BOYSHORTS = new AbstractClothingType(150,
			"a pair of",
			true,
			"boyshorts",
			"boyshorts",
			"Despite their name, these underwear are intended to be worn by women.",
			0,
			Femininity.FEMININE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"clothing/groin_boyshorts",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL,
									ClothingAccess.LEGS_UP_TO_GROIN,
									
									ClothingAccess.GROIN),
							null,
							null,
							null),
					new BlockedParts(
							DisplacementType.SHIFTS_ASIDE,
							Util.newArrayListOfValues(
									ClothingAccess.GROIN),
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							null,
							PresetConcealmentLists.CONCEALED_GENITALS.getPresetInventorySlotList())),
			null,
			ColourListPresets.LINGERIE,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the boyshorts before pulling them up to cover your private parts.",
					"You pull the boyshorts up [npc.namePos] [npc.legs] to cover [npc.her] private parts.",
					null,
					"[npc.Name] steps into the boyshorts before pulling them up to cover [npc.her] private parts.",
					"[npc.Name] pulls the boyshorts up your [pc.legs] to cover your private parts.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your boyshorts and kick them off your [pc.feet].",
					"You pull down [npc.namePos] boyshorts and slide them off [npc.her] [npc.feet].",
					null,
					"[npc.Name] pulls [npc.her] boyshorts down and kicks them off [npc.her] [npc.feet].",
					"[npc.Name] pulls your boyshorts down and slides them off your [pc.feet].",
					null, null, null);
		}
	};
	
	public static AbstractClothingType GROIN_BRIEFS = new AbstractClothingType(100,
			"a pair of",
			true,
			"briefs",
			"briefs",
			"A pair of men's briefs.",
			0,
			Femininity.MASCULINE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"clothing/groin_briefs",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL,
									ClothingAccess.LEGS_UP_TO_GROIN,
									
									ClothingAccess.GROIN),
							null,
							null,
							null),
					new BlockedParts(DisplacementType.PULLS_DOWN,
							Util.newArrayListOfValues(
									ClothingAccess.GROIN),
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							null,
							PresetConcealmentLists.CONCEALED_GENITALS.getPresetInventorySlotList())),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the briefs before pulling them up to cover your private parts.",
					"You pull the briefs up [npc.namePos] [npc.legs] to cover [npc.her] private parts.",
					null,
					"[npc.Name] steps into the briefs before pulling them up to cover [npc.her] private parts.",
					"[npc.Name] pulls the briefs up your [pc.legs] to cover your private parts.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your briefs and kick them off your [pc.feet].",
					"You pull down [npc.namePos] briefs and slide them off [npc.her] [npc.feet].",
					null,
					"[npc.Name] pulls [npc.her] briefs down and kicks them off [npc.her] [npc.feet].",
					"[npc.Name] pulls your briefs down and slides them off your [pc.feet].",
					null, null, null);
		}
	};
	
	public static AbstractClothingType GROIN_BOXERS = new AbstractClothingType(100,
			"a pair of",
			true,
			"boxer shorts",
			"boxer shorts",
			"A pair of loose-fitting men's underwear.",
			0,
			Femininity.MASCULINE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"clothing/groin_boxers",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL,
									ClothingAccess.LEGS_UP_TO_GROIN,
									
									ClothingAccess.GROIN),
							null, null, null),
					new BlockedParts(DisplacementType.UNBUTTONS,
							Util.newArrayListOfValues(
									ClothingAccess.GROIN),
							Util.newArrayListOfValues(
									CoverableArea.PENIS),
							null,
							Util.newArrayListOfValues(
									InventorySlot.PENIS,
									InventorySlot.PIERCING_PENIS)),
					new BlockedParts(DisplacementType.PULLS_DOWN,
							Util.newArrayListOfValues(
									ClothingAccess.GROIN),
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							null,
							PresetConcealmentLists.CONCEALED_GENITALS.getPresetInventorySlotList())),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the boxers before pulling them up to cover your private parts.",
					"You pull the boxers up [npc.namePos] [npc.legs] to cover [npc.her] private parts.",
					null,
					"[npc.Name] steps into the boxers before pulling them up to cover [npc.her] private parts.",
					"[npc.Name] pulls the boxers up your [pc.legs] to cover your private parts.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your boxers and kick them off your [pc.feet].",
					"You pull down [npc.namePos] boxers and slide them off [npc.her] [npc.feet].",
					null,
					"[npc.Name] pulls [npc.her] boxers down and kicks them off [npc.her] [npc.feet].",
					"[npc.Name] pulls your boxers down and slides them off your [pc.feet].",
					null, null, null);
		}
	};
	
	public static AbstractClothingType GROIN_JOCKSTRAP = new AbstractClothingType(100,
			"a",
			false,
			"jockstrap",
			"jockstraps",
			"An undergarment that protects and supports the wearer's male genitalia, while leaving their ass exposed.",
			0,
			Femininity.MASCULINE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"clothing/groin_jockstrap",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL,
									ClothingAccess.LEGS_UP_TO_GROIN,
									ClothingAccess.GROIN),
							null, null, null),
					new BlockedParts(DisplacementType.SHIFTS_ASIDE,
							Util.newArrayListOfValues(ClothingAccess.GROIN),
							Util.newArrayListOfValues(
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							null,
							PresetConcealmentLists.CONCEALED_GENITALS.getPresetInventorySlotList())),
			null,
			ColourListPresets.ALL,
			null,
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the jockstrap before pulling them up to cover your private parts.",
					"You pull the jockstrap up [npc.namePos] [npc.legs] to cover [npc.her] private parts.",
					null,
					"[npc.Name] steps into the jockstrap before pulling them up to cover [npc.her] private parts.",
					"[npc.Name] pulls the jockstrap up your [pc.legs] to cover your private parts.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your jockstrap and kick them off your [pc.feet].",
					"You pull down [npc.namePos] jockstrap and slide them off [npc.her] [npc.feet].",
					null,
					"[npc.Name] pulls [npc.her] jockstrap down and kicks them off [npc.her] [npc.feet].",
					"[npc.Name] pulls your jockstrap down and slides them off your [pc.feet].",
					null, null, null);
		}
	};
	
	public static AbstractClothingType GROIN_BACKLESS_PANTIES = new AbstractClothingType(150,
			"a pair of",
			true,
			"backless panties",
			"backless panties",
			"A large piece of fabric is missing from the rear end of these panties, leaving their wearer's bottom completely exposed.",
			0,
			Femininity.FEMININE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"clothing/groin_backless_panties",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL,
									ClothingAccess.LEGS_UP_TO_GROIN,
									ClothingAccess.GROIN),
							null,
							null, null),
					new BlockedParts(DisplacementType.SHIFTS_ASIDE,
							Util.newArrayListOfValues(ClothingAccess.GROIN),
							Util.newArrayListOfValues(
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							null, 
							Util.newArrayListOfValues(
									InventorySlot.VAGINA,
									InventorySlot.PENIS,
									InventorySlot.PIERCING_PENIS,
									InventorySlot.PIERCING_VAGINA))),
			null,
			ColourListPresets.LINGERIE,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the panties before pulling them up to cover your private parts.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] steps into " + clothing.getName(true) + " before pulling them up to cover [npc.her] private parts.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your panties and kick them off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] panties, kicking them off [npc.her] feet.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls your panties down and slides them off your feet.";
				else
					return UtilText.parse(clothingOwner, "You pull [npc.namePos] panties down and slide them off [npc.her] feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You shift your panties to one side.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] shifts [npc.her] panties to one side.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] shifts your panties to one side.";
				else
					return UtilText.parse(clothingOwner, "You shift [npc.namePos] panties to one side.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You move your panties back into their proper place.";
			else
				return UtilText.parse(clothingOwner, "[npc.Name] moves [npc.her] panties back into their proper place.");
		}
	};
	
	public static AbstractClothingType GROIN_CROTCHLESS_PANTIES = new AbstractClothingType(250,
			"a pair of",
			true,
			"crotchless panties",
			"crotchless panties",
			"These panties have a strip of fabric missing in the most vital area, leaving their wearer's genitalia and asshole completely exposed.",
			0,
			Femininity.FEMININE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"clothing/groin_crotchless_panties",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL,
									ClothingAccess.LEGS_UP_TO_GROIN,
									
									ClothingAccess.GROIN),
							null,
							null,
							null)),
			null,
			ColourListPresets.LINGERIE,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the panties before pulling them up to cover your private parts.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] steps into " + clothing.getName(true) + " before pulling them up to cover [npc.her] private parts.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your panties and kick them off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] panties, kicking them off [npc.her] feet.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls your panties down and slides them off your feet.";
				else
					return UtilText.parse(clothingOwner, "You pull [npc.namePos] panties down and slide them off [npc.her] feet.");
			}
		}
	};
	
	public static AbstractClothingType GROIN_CROTCHLESS_THONG = new AbstractClothingType(250,
			"a",
			false,
			"crotchless thong",
			"crotchless thongs",
			"This thong has a strip of fabric missing in the most vital area, leaving their wearer's genitalia completely exposed."
					+ " The tiny string at the back does nothing to conceal the wearer's asshole, effectively leaving that exposed as well.",
			0,
			Femininity.FEMININE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"clothing/groin_crotchless_thong",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL,
									ClothingAccess.LEGS_UP_TO_GROIN,
									
									ClothingAccess.GROIN),
							null,
							null,
							null)),
			null,
			ColourListPresets.LINGERIE,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the thong, before pulling it up around your waist.",
					"You put the thong on [npc.name].",
					null,
					"[npc.Name] steps into the thong, before pulling it up around [npc.her] waist.",
					"[npc.Name] puts the thong on you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your thong.",
					"You take off [npc.namePos] thong.",
					null,
					"[npc.Name] takes [npc.her] thong off.",
					"[npc.Name] takes your thong off.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType GROIN_CROTCHLESS_BRIEFS = new AbstractClothingType(150,
			"a pair of",
			true,
			"crotchless briefs",
			"crotchless briefs",
			"A pair of men's briefs, with a section cut out at the front that leaves the wearer's genitalia completely exposed.",
			0,
			Femininity.MASCULINE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"clothing/groin_crotchless_briefs",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL,
									ClothingAccess.LEGS_UP_TO_GROIN,
									
									ClothingAccess.GROIN),
							null,
							null,
							null),
					new BlockedParts(
							DisplacementType.PULLS_DOWN,
							Util.newArrayListOfValues(
									ClothingAccess.GROIN),
							Util.newArrayListOfValues(CoverableArea.ANUS),
							null,
							Util.newArrayListOfValues(InventorySlot.ANUS))),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You step into the briefs before pulling them up to your waist.";
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] steps into " + clothing.getName(true) + " before pulling them up to cover [npc.her] waist.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull down your briefs and kick them off your feet.";
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] " + clothing.getName(true) + " before before kicking them off [npc.her] [npc.feet].");
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingRemover, "[npc.Name] pulls down your briefs before before sliding them off your [pc.feet].");
				} else {
					return UtilText.parse(clothingOwner, "You pull [npc.namePos] briefs down before before sliding them off [npc.her] [npc.feet].");
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You shift your briefs to one side.";
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] shifts [npc.her] briefs to one side.");
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingRemover, "[npc.Name] shifts your briefs to one side.");
				} else {
					return UtilText.parse(clothingOwner, "You shift [npc.namePos] briefs to one side.");
				}
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You move your briefs back into their proper place.";
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] moves [npc.her] briefs back into their proper place.");
			}
		}
	};

	// CLOTHING SETS:

	// MAID:
	public static AbstractClothingType MAID_HEADPIECE = new AbstractClothingType(600,
			"a",
			false,
			"Maid's headpiece",
			"Maid's headpieces",
			"A heavily stylised Maid's headpiece, it consists of a coloured headband with a decorative white lace attached on top.",
			0,
			Femininity.FEMININE,
			InventorySlot.HEAD,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_maid"),
			"clothing/maidHeadband",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.HEAD),
							null,
							null,
							null)),
			null,
			ColourListPresets.MAID,
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide the headpiece into place.",
					"You slide the headpiece onto [npc.namePos] head.",
					null,
					"[npc.Name] slides the headpiece into place.",
					"[npc.Name] slides the headpiece onto your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your headpiece.",
					"You pull off [npc.namePos] headpiece.",
					null,
					"[npc.Name] takes [npc.her] headpiece off.",
					"[npc.Name] pulls your headpiece off.",
					null, null, null);
		}

	};
	
	public static AbstractClothingType MAID_DRESS = new AbstractClothingType(2500, //TODO
			"a",
			false,
			"Maid's dress",
			"Maid's dresses",
			"A heavily stylised Maid's dress, it consists of a coloured one-piece dress with decorative white lace trimmings."
					+ " A small white apron is attached to the front, and is similarly trimmed in white lace.",
			0,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_maid"),
			"clothing/maidDress",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.HEAD),
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH,
									CoverableArea.BACK),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList()),
					new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							Util.newArrayListOfValues(
									ClothingAccess.GROIN,
									ClothingAccess.ANUS), 
							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList())),
			null,
			ColourListPresets.MAID,
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.DRESS, ItemTag.FITS_ARM_WINGS)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the dress and pull it up over your torso, zipping yourself up at the back before making sure the trimmings and apron are neatly arranged.",
					"You get [npc.name] to step into the dress, before pulling it up over [npc.her] torso. Zipping up at the back, you then make sure that the trimmings and apron are neatly arranged.",
					null,
					"[npc.Name] steps into the dress and pulls it up over [npc.her] torso, zipping [npc.herself] up at the back before making sure the trimmings and apron are neatly arranged.",
					"[npc.Name] gets you to step into the dress, before pulling it up over your torso. Zipping you up at the back, [npc.she] then makes sure that the trimmings and apron are neatly arranged.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You reach back and unzip your Maid's dress, pulling your arms out before sliding it down your body and stepping out.",
					"You unzip the back of [npc.namePos] Maid's dress, before pulling [npc.her] [npc.arms] free to allow the dress to fall to the floor.",
					null,
					"[npc.Name] reaches back and unzips [npc.her] Maid's dress, pulling [npc.her] arms out before sliding it down [npc.her] body and stepping out.",
					"[npc.Name] unzips the back of your Maid's dress, before pulling your arms free to allow the dress to fall to the floor.",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			if (dt == DisplacementType.PULLS_DOWN) {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You unzip the back of your dress and pull down the top half.";
				else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
					return UtilText.parse(clothingOwner, "[npc.Name] unzips the back of [npc.her] dress and pulls the top half down");
				else {
					if (clothingOwner.isPlayer())
						return "[npc.Name] unzips the back of your dress and pulls the top half down.";
					else
						return UtilText.parse(clothingOwner, "You unzip the back of [npc.namePos] dress and pull the top half down.");
				}
			} else {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull up your dress's skirt.";
				else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
					return UtilText.parse(clothingOwner, "[npc.Name] pulls up [npc.her] dress's skirt.");
				else {
					if (clothingOwner.isPlayer())
						return "[npc.Name] pulls up your dress's skirt.";
					else
						return UtilText.parse(clothingOwner, "You pull up the skirt of [npc.namePos] dress.");
				}
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			if (dt == DisplacementType.PULLS_DOWN) {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull your dress back up into the correct position and zip yourself up.";
				else
					return UtilText.parse(clothingOwner,
							"[npc.Name] pulls [npc.her] dress up into the correct position and zips [npc.herself] up.");
			} else {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull your dress's skirt back down.";
				else
					return UtilText.parse(clothingOwner, "[npc.Name] pulls [npc.her] dress's skirt back down.");
			}
		}
	};
	public static AbstractClothingType MAID_STOCKINGS = new AbstractClothingType(450,
			"a pair of",
			true,
			"Maid's stockings",
			"Maid's stockings",
			"A pair of cotton Maid's stockings, with a coloured bow near the top.",
			0,
			Femininity.FEMININE,
			InventorySlot.SOCK,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_maid"),
			"clothing/maidStockings",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.FEET,
									ClothingAccess.CALVES),
							null, null, null)),
			null,
			ColourListPresets.MAID,
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the stockings.",
					"You pull the stockings onto [npc.namePos] [npc.feet].",
					null,
					"[npc.Name] pulls on the stockings.",
					"[npc.Name] pulls the stockings onto your [pc.feet].",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off your stockings.",
					"You pull off [npc.namePos] stockings.",
					null,
					"[npc.Name] pulls off [npc.her] stockings.",
					"[npc.Name] pulls off your stockings.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType MAID_HEELS = new AbstractClothingType(800,
			"a pair of",
			true,
			"Maid's high heels",
			"Maid's high heels",
			"A pair of Maid's high heels, they are made of coloured leather with a small amount of white lace decoration.",
			0,
			Femininity.FEMININE,
			InventorySlot.FOOT,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_maid"),
			"clothing/maidHeels",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.FEET),
							Util.newArrayListOfValues(CoverableArea.FEET),
							Util.newArrayListOfValues(ClothingAccess.FEET),
							null)),
			null,
			ColourListPresets.MAID,
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			ColourListPresets.JUST_STEEL,
			ColourListPresets.ALL_METAL,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slip on the heels and buckle up the straps.",
					"You pull the heels onto [npc.namePos] [npc.feet] and buckle up the straps.",
					null,
					"[npc.Name] pulls on the heels and buckles up the straps.",
					"[npc.Name] pulls the heels onto your [pc.feet] and buckles up the straps.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unbuckle your heels and slip them off.",
					"You unbuckle [npc.namePos] heels and pull them off.",
					null,
					"[npc.Name] unbuckles [npc.her] heels and slips them off.",
					"[npc.Name] unbuckles your heels and pulls them off.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType MAID_SLEEVES = new AbstractClothingType(350,
			"a pair of",
			true,
			"Maid's sleeves",
			"Maid's sleeves",
			"A pair of Maid's sleeves that end just past the elbow. They are made of soft coloured fabric with white lace trimmings.",
			0,
			Femininity.FEMININE,
			InventorySlot.HAND,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_maid"),
			"clothing/maidGloves",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.WRISTS),
							null, null, null)),
			null,
			ColourListPresets.MAID,
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the sleeves.",
					"You pull the sleeves onto [npc.namePos] [npc.arms].",
					null,
					"[npc.Name] pulls on the sleeves.",
					"[npc.Name] pulls the sleeves onto your [pc.arms].",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off your sleeves.",
					"You pull off [npc.namePos] sleeves.",
					null,
					"[npc.Name] pulls [npc.her] sleeves off.",
					"[npc.Name] pulls your sleeves off.",
					null, null, null);
		}
	};

	// BDSM:
	
	
	public static AbstractClothingType BDSM_BALLGAG = new AbstractClothingType(150,
			"a",
			false,
			"ball gag",
			"ball gags",
			"A leather strap with a rubber ball fixed into one side.",
			0,
			null,
			InventorySlot.MOUTH,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_bdsm"),
			"clothing/bdsm_mouth_ballgag",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_DRAIN, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							null,
							Util.newArrayListOfValues(CoverableArea.MOUTH),
							null, null)),
			null,
			ColourListPresets.ALL,
			null,
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			ColourListPresets.JUST_STEEL,
			ColourListPresets.ALL_METAL,
			Util.newArrayListOfValues(
					ItemTag.MUFFLES_SPEECH,
					ItemTag.SOLD_BY_FINCH)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You open your mouth wide and slide in the ball gag, before reaching back and fastening the buckles to keep it in place.",
					"You push the ball gag into [npc.namePos] mouth, before reaching back and fastening the buckles to keep it in place.",
					null,
					"[npc.Name] opens [npc.her] mouth wide and slides in the ball gag, before reaching back and fastening the buckles to keep it in place.",
					"[npc.Name] pushes the ball gag into your mouth, before reaching back and fastening the buckles to keep it in place.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unfasten the ball gag's straps and take it out of your mouth.",
					"You unfasten the ball gag's straps and take it out of [npc.namePos] mouth.",
					null,
					"[npc.Name] unfastens the ball gag's straps and takes it out of [npc.her] mouth.",
					"[npc.Name] unfastens the ball gag's straps and takes it out of your mouth.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType BDSM_RINGGAG = new AbstractClothingType(200,
			"a",
			false,
			"ring gag",
			"ring gags",
			"A leather strap with a metal ring fixed into one side.",
			0,
			null,
			InventorySlot.MOUTH,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_bdsm"),
			"clothing/bdsm_mouth_ringgag",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_DRAIN, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							null,
							null,
							null, null)),
			null,
			ColourListPresets.ALL_WITH_METALS,
			null,
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			ColourListPresets.JUST_STEEL,
			ColourListPresets.ALL_METAL,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_FINCH)){
		

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You open your mouth wide and slide in the ring gag, before reaching back and fastening the buckles to keep it in place.",
					"You push the ring gag into [npc.namePos] mouth, before reaching back and fastening the buckles to keep it in place.",
					null,
					"[npc.Name] opens [npc.her] mouth wide and slides in the ring gag, before reaching back and fastening the buckles to keep it in place.",
					"[npc.Name] pushes the ring gag into your mouth, before reaching back and fastening the buckles to keep it in place.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unfasten the ring gag's straps and take it out of your mouth.",
					"You unfasten the ring gag's straps and take it out of [npc.namePos] mouth.",
					null,
					"[npc.Name] unfastens the ring gag's straps and takes it out of [npc.her] mouth.",
					"[npc.Name] unfastens the ring gag's straps and takes it out of your mouth.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType BDSM_CHOKER = new AbstractClothingType(350,
			"a",
			false,
			"tagged choker",
			"tagged chokers",
			"A leather choker with a loose metallic tag attached to the front.",
			0,
			null,
			InventorySlot.NECK,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_bdsm"),
			"clothing/bdsm_neck_choker",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_DRAIN, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							null,
							null,
							null, null)),
			null,
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			ColourListPresets.JUST_STEEL,
			ColourListPresets.ALL_METAL,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_FINCH)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You wrap the choker around your neck and fasten the buckle.",
					"You wrap the choker around [npc.namePos] neck and fasten the buckle.",
					null,
					"[npc.Name] wraps the choker around [npc.her] neck and fastens the buckle.",
					"[npc.Name] wraps the choker around your neck and fastens the buckle.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unfasten the choker's buckle and remove it from around your neck.",
					"You unfasten the choker's buckle and remove it from around [npc.namePos] neck.",
					null,
					"[npc.Name] unfastens the choker's buckle and removes it from around [npc.her] neck.",
					"[npc.Name] unfastens the choker's buckle and removes it from around your neck.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType BDSM_WRIST_RESTRAINTS = new AbstractClothingType(400,
			"a pair of",
			true,
			"wrist restraints",
			"wrist restraints",
			"A pair of leather wrist restraints, which are linked by a metal chain. The straps can be adjusted to fit even the thickest of limbs, which even enable them to be fastened around a harpy's wing-like arms.",
			0,
			null,
			InventorySlot.WRIST,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_bdsm"),
			"clothing/bdsm_wrist_restraints",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_DRAIN, 0)),
			Util.newArrayListOfValues(new BlockedParts(
					DisplacementType.REMOVE_OR_EQUIP,
					Util.newArrayListOfValues(ClothingAccess.WRISTS),
					null,
					Util.newArrayListOfValues(ClothingAccess.WRISTS), 
					null)),
			null,
			ColourListPresets.ALL,
			null,
			ColourListPresets.JUST_STEEL,
			ColourListPresets.ALL_METAL,
			null,
			null,
			Util.newArrayListOfValues(
					ItemTag.HINDERS_ARM_MOVEMENT,
					ItemTag.FITS_ARM_WINGS,
					ItemTag.SOLD_BY_FINCH,
					ItemTag.PROVIDES_KEY)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You buckle the restraints around your wrists.",
					"You buckle the restraints around [npc.namePos] wrists.",
					null,
					"[npc.Name] buckles the restraints around [npc.her] wrists.",
					"[npc.Name] buckles the restraints around your wrists.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unfasten the restraints and remove them from your wrists.",
					"You unfasten the restraints and remove them from [npc.namePos] wrists.",
					null,
					"[npc.Name] unfastens the restraints and removes them from [npc.her] wrists.",
					"[npc.Name] unfastens the restraints and removes them from your wrists.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType BDSM_SPREADER_BAR = new AbstractClothingType(450,
			"a",
			false,
			"spreader bar",
			"spreader bars",
			"A pair of ankle restraints, attached to one another by a long metal pole. When worn, the wearer's legs are forced open.",
			0,
			null,
			InventorySlot.ANKLE,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_bdsm"),
			"clothing/bdsm_ankle_spreaderbar",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_DRAIN, 0)),
			Util.newArrayListOfValues(new BlockedParts(
					DisplacementType.REMOVE_OR_EQUIP,
					Util.newArrayListOfValues(ClothingAccess.CALVES),
					null,
					Util.newArrayListOfValues(
							ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL,
							ClothingAccess.LEGS_UP_TO_GROIN,
							ClothingAccess.CALVES), 
					null)),
			null,
			ColourListPresets.ALL,
			null,
			ColourListPresets.JUST_STEEL,
			ColourListPresets.ALL_METAL,
			null,
			null,
			Util.newArrayListOfValues(
					ItemTag.SPREADS_FEET,
					ItemTag.HINDERS_LEG_MOVEMENT,
					ItemTag.SOLD_BY_FINCH,
					ItemTag.REVEALS_CONCEALABLE_SLOT,
					ItemTag.PROVIDES_KEY)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You wrap the spreader bar's restraints around your ankles and fasten the straps, leaving your [pc.legs] wide open.",
					"You wrap the spreader bar's restraints around [npc.namePos] ankles and fasten the straps, leaving [npc.her] [npc.legs] wide open.",
					null,
					"[npc.Name] wraps the spreader bar's restraints around [npc.her] ankles and fastens the straps, leaving [npc.her] [npc.legs] wide open.",
					"[npc.Name] wraps the spreader bar's restraints around your ankles and fastens the straps, leaving your [pc.legs] wide open.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unfasten the spreader bar's restraints and remove it from around your ankles.",
					"You unfasten the spreader bar's restraints and remove it from around [npc.namePos] ankles.",
					null,
					"[npc.Name] unfastens the spreader bar's restraints and removes it from around [npc.her] ankles.",
					"[npc.Name] unfastens the spreader bar's restraints and removes it from around your ankles.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType BDSM_CHASTITY_BELT = new AbstractClothingType(500,
			"a",
			false,
			"chastity belt",
			"chastity belts",
			"A chastity belt, designed to deny access to the wearer's vagina. A hole in the rear strap provides access to the wearer's asshole.",
			0,
			null,
			InventorySlot.GROIN,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_bdsm"),
			"clothing/bdsm_groin_chastity_belt",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_DRAIN, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.GROIN),
							Util.newArrayListOfValues(
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							null, null)),
			null,
			ColourListPresets.ALL,
			null,
			ColourListPresets.JUST_STEEL,
			ColourListPresets.ALL_METAL,
			ColourListPresets.JUST_GOLD,
			ColourListPresets.ALL_METAL,
			Util.newArrayListOfValues(
					ItemTag.SOLD_BY_FINCH,
					ItemTag.RIGID_MATERIAL,
					ItemTag.PROVIDES_KEY)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the chastity belt and clip the locks into place.",
					"You pull the chastity belt up around [npc.namePos] groin, before clipping the locks into place.",
					null,
					"[npc.Name] pulls on the chastity belt and clips the locks into place.",
					"[npc.Name] pulls the chastity belt up around your groin, before clipping the locks into place.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unfasten the chastity belt's locks and take it off.",
					"You unfasten the chastity belt's locks and take it off of [npc.NamePos] groin.",
					null,
					"[npc.Name] unfastens the chastity belt's locks and takes it off.",
					"[npc.Name] unfastens the chastity belt's locks and takes it off of your groin.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType BDSM_CHASTITY_BELT_FULL = new AbstractClothingType(750,
			"a",
			false,
			"full chastity belt",
			"full chastity belts",
			"A chastity belt, designed to deny access to both the wearer's vagina and anus.",
			0,
			null,
			InventorySlot.GROIN,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_bdsm"),
			"clothing/bdsm_groin_chastity_belt_full",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_DRAIN, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.GROIN),
							Util.newArrayListOfValues(
									CoverableArea.PENIS,
									CoverableArea.VAGINA,
									CoverableArea.ANUS),
							Util.newArrayListOfValues(
									ClothingAccess.GROIN,
									ClothingAccess.ANUS),
							null)),
			null,
			ColourListPresets.ALL,
			null,
			ColourListPresets.JUST_STEEL,
			ColourListPresets.ALL_METAL,
			ColourListPresets.JUST_GOLD,
			ColourListPresets.ALL_METAL,
			Util.newArrayListOfValues(
					ItemTag.SOLD_BY_FINCH,
					ItemTag.RIGID_MATERIAL,
					ItemTag.PROVIDES_KEY)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the chastity belt and clip the locks into place.",
					"You pull the chastity belt up around [npc.namePos] groin, before clipping the locks into place.",
					null,
					"[npc.Name] pulls on the chastity belt and clips the locks into place.",
					"[npc.Name] pulls the chastity belt up around your groin, before clipping the locks into place.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unfasten the chastity belt's locks and take it off.",
					"You unfasten the chastity belt's locks and take it off of [npc.NamePos] groin.",
					null,
					"[npc.Name] unfastens the chastity belt's locks and takes it off.",
					"[npc.Name] unfastens the chastity belt's locks and takes it off of your groin.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType BDSM_KARADA = new AbstractClothingType(400,
			"an",
			false,
			"arcane karada",
			"arcane karadas",
			"A length of rope that, thanks to a special enchantment, ties itself into a rope harness that loops around the wearer's neck, around their body, and under their crotch.",
			0,
			null,
			InventorySlot.STOMACH,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_bdsm"),
			"clothing/bdsm_stomach_karada",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_DRAIN, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									
									ClothingAccess.GROIN,
									ClothingAccess.ANUS,
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							null,
							null, 
							null)),
			null, //Util.newArrayListOfValues(InventorySlot.CHEST, InventorySlot.GROIN),
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_FINCH)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You hang the rope around your neck, and as you do so, it snakes and slides around your body with a mind of its own, forming a tight karada within a matter of seconds.",
					"You hang the rope around [npc.namePos] neck, watching as it snakes and slides around [npc.her] body with a mind of its own, forming a tight karada within a matter of seconds.",
					null,
					"[npc.Name] hangs the rope around [npc.her] neck, smiling as it snakes and slides around [npc.her] body with a mind of its own, forming a tight karada within a matter of seconds.",
					"[npc.Name] hangs the rope around your neck, smiling as it snakes and slides around your body with a mind of its own, forming a tight karada within a matter of seconds.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You untie the karada and take it off.",
					"You untie [npc.namePos] karada and take it off.",
					null,
					"[npc.Name] unties [npc.her] karada and takes it off.",
					"[npc.Name] unties your karada and takes it off.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType ENFORCER_SHIRT = new AbstractClothingType(1500,
			"a",
			false,
			"fancy-dress Enforcer's shirt",
			"fancy-dress Enforcer's shirts",
			"A fancy-dress version of an Enforcer's shirt, complete with an imitation stab-proof vest.",
			1f,
			null,
			InventorySlot.TORSO_UNDER,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_slutty_enforcer"),
			"clothing/enforcerShirt",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER),
							Util.newArrayListOfValues(CoverableArea.BACK),
							null,
							null),
					new BlockedParts(
							DisplacementType.UNBUTTONS,
							null,
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList())),
			null,
			Util.newArrayListOfValues(
					PresetColour.CLOTHING_BLUE,
					PresetColour.CLOTHING_BLACK,
					PresetColour.CLOTHING_PINK),
			ColourListPresets.ALL,
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			ColourListPresets.JUST_STEEL,
			ColourListPresets.ALL_METAL,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.FITS_ARM_WINGS)){
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the shirt and button it up.",
					"You put the shirt onto [npc.name] and button it up.",
					null,
					"[npc.Name] pulls on the shirt and buttons it up.",
					"[npc.Name] puts the shirt onto you and buttons it up.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unbutton your shirt and pull it off.",
					"You unbutton [npc.namePos] shirt and pull it off.",
					null,
					"[npc.Name] unbuttons [npc.her] shirt and pulls it off.",
					"[npc.Name] unbuttons your shirt and pull it off.",
					null, null, null);
		}
	};

	public static AbstractClothingType ENFORCER_SHORTS = new AbstractClothingType(600,
			"a pair of",
			true,
			"fancy-dress Enforcer's shorts",
			"fancy-dress Enforcer's shorts",
			"A pair of fancy-dress Enforcer's shorts, complete with a utility belt.",
			0,
			null,
			InventorySlot.LEG,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_slutty_enforcer"),
			"clothing/enforcerShorts",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(ClothingAccess.LEGS_UP_TO_GROIN),
									null,
									Util.newArrayListOfValues(ClothingAccess.LEGS_UP_TO_GROIN),
									null),
					new BlockedParts(
									DisplacementType.PULLS_DOWN,
									null,
									Util.newArrayListOfValues(
											CoverableArea.ANUS,
											CoverableArea.PENIS,
											CoverableArea.VAGINA),
									Util.newArrayListOfValues(
									ClothingAccess.GROIN,
									ClothingAccess.ANUS),
									PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList()),
					new BlockedParts(
									DisplacementType.UNZIPS,
									null,
									Util.newArrayListOfValues(CoverableArea.PENIS),
									Util.newArrayListOfValues(ClothingAccess.GROIN),
									PresetConcealmentLists.CONCEALED_UNZIPS_GROIN.getPresetInventorySlotList())),
			null,
			Util.newArrayListOfValues(
					PresetColour.CLOTHING_BLUE,
					PresetColour.CLOTHING_BLACK,
					PresetColour.CLOTHING_PINK),
			ColourListPresets.ALL,
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			ColourListPresets.JUST_STEEL,
			ColourListPresets.ALL_METAL,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the shorts before pulling them up to your waist.",
					"You pull the shorts all the way up [npc.namePos] [npc.legs] to [npc.her] waist.",
					null,
					"[npc.Name] steps into the shorts before pulling them up to [npc.her] waist.",
					"[npc.Name] pull the shorts all the way up your [pc.legs] to your waist.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your shorts and kick them off your [pc.feet].",
					"You pull [npc.namePos] shorts down and slide them off [npc.her] [npc.feet].",
					null,
					"[npc.Name] pulls [npc.her] shorts down and kicks them off [npc.her] [npc.feet].",
					"[npc.Name] pulls your shorts down and slides them off your [pc.feet].",
					null, null, null);
		}
	};
	
	public static AbstractClothingType ENFORCER_MINI_SKIRT = new AbstractClothingType(600,
			"a",
			false,
			"fancy-dress Enforcer's miniskirt",
			"fancy-dress Enforcer's miniskirts",
			"A fancy-dress Enforcer's skirt, complete with a utility belt, which is so short that it barely reaches down to mid-thigh.",
			0,
			Femininity.FEMININE,
			InventorySlot.LEG,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_slutty_enforcer"),
			"clothing/enforcer_miniskirt",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							Util.newArrayListOfValues(
									ClothingAccess.GROIN,
									ClothingAccess.ANUS),
							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList())),
			null,
			Util.newArrayListOfValues(
					PresetColour.CLOTHING_BLUE,
					PresetColour.CLOTHING_BLACK,
					PresetColour.CLOTHING_PINK),
			ColourListPresets.ALL,
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			ColourListPresets.JUST_STEEL,
			ColourListPresets.ALL_METAL,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the miniskirt before pulling it up to your waist.",
					"You slide the miniskirt up [npc.namePos] [npc.legs] to rest around [npc.her] waist.",
					null,
					"[npc.Name] steps into the miniskirt before pulling it up to [npc.her] waist.",
					"[npc.Name] slides the miniskirt up your [pc.legs] to rest around your waist.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your miniskirt and kick it off your [pc.feet].",
					"You pull [npc.namePos] miniskirt down and slide it off [npc.her] [npc.feet].",
					null,
					"[npc.Name] pulls down [npc.her] miniskirt before kicking it off [npc.her] [npc.feet].",
					"[npc.Name] pulls your miniskirt down and slides it off your [pc.feet].",
					null, null, null);
		}
	};

	
	public static AbstractClothingType MILK_MAID_TORSO_DRESS = new AbstractClothingType(1500,
			"a",
			false,
			"Milk Maid's dress",
			"Milk Maid's dresses",
			"A milk maid's dress; consisting of shirt, skirt, and corset. The shirt is made of a light, comfortable material, which gets very transparent when wet."
					+ " The corset, just like the skirt, is made of a soft fabric, and its strings can be pulled tight to flatter the wearer's figure and push their breasts slighter closer together.",
			0,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_milk_maid"),
			"clothing/milk_maid_dress",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.HEAD),
							Util.newArrayListOfValues(
									CoverableArea.STOMACH,
									CoverableArea.BACK),
							Util.newArrayListOfValues(ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList()),
					new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							Util.newArrayListOfValues(
									ClothingAccess.GROIN,
									ClothingAccess.ANUS),
							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList()),
					new BlockedParts(
							DisplacementType.PULLS_DOWN,
							null,
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES),
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							Util.newArrayListOfValues(
									InventorySlot.CHEST,
									InventorySlot.NIPPLE,
									InventorySlot.PIERCING_NIPPLE))),
			null,
			ColourListPresets.MILK_MAID,
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.DRESS, ItemTag.FITS_ARM_WINGS)){
	};
	
	public static AbstractClothingType MILK_MAID_HEADBAND = new AbstractClothingType(400,
			"a",
			false,
			"Milk Maid's headband",
			"Milk Maid's headbands",
			"A frilly headband, with lots of decorative lace attached to the top.",
			0,
			Femininity.FEMININE,
			InventorySlot.HEAD,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_milk_maid"),
			"clothing/milk_maid_headband",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.HEAD),
							null, null, null)),
			null,
			ColourListPresets.MILK_MAID,
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
			@Override
			public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
						"You pull on the headband.",
						"You push the headband onto [npc.namePos] head.",
						null,
						"[npc.Name] pulls the headband onto [npc.her] head.",
						"[npc.Name] pushes the headband onto your head.",
						null, null, null);
			}

			@Override
			public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
						"You take off your headband.",
						"You take off [npc.namePos] headband.",
						null,
						"[npc.Name] takes [npc.her] headband off.",
						"[npc.Name] takes your headband off.",
						null, null, null);
			}
	};
	
	public static AbstractClothingType MILK_MAID_KERCHIEF = new AbstractClothingType(300,
			"a",
			false,
			"kerchief",
			"kerchiefs",
			"A triangular piece of cloth that covers the top of the wearer's head.",
			0,
			null,
			InventorySlot.HEAD,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_milk_maid"),
			"clothing/milk_maid_kerchief",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.HEAD),
							null, null, null)),
			null,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You place the kerchief on your head, before tying up the strings beneath your chin.",
					"You place the kerchief on [npc.namePos] head, before tying up the strings beneath [npc.her] chin.",
					null,
					"[npc.Name] places the kerchief on [npc.her] head, before tying up the strings beneath [npc.her] chin.",
					"[npc.Name] places the kerchief on your head, before tying up the strings beneath your chin.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your kerchief.",
					"You take off [npc.namePos] kerchief.",
					null,
					"[npc.Name] takes [npc.her] kerchief off.",
					"[npc.Name] takes your kerchief off.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType MEGA_MILK = new AbstractClothingType(400,
			"a",
			false,
			"Mega Milk T-shirt",
			"Mega Milk T-shirts",
			"A T-shirt with the words 'Mega Milk' written on the front.",
			0,
			null,
			InventorySlot.TORSO_UNDER,
			Rarity.UNCOMMON,
			null,
			"clothing/torso_tshirt_megamilk",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.HEAD),
							null,
							null, null),
					new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH,
									CoverableArea.BACK),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList())),
			null,
			Util.newArrayListOfValues(
							PresetColour.CLOTHING_BLUE,
							PresetColour.CLOTHING_BLACK),
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the t-shirt.",
					"You pull the t-shirt onto [npc.name].",
					null,
					"[npc.Name] pulls on the t-shirt.",
					"[npc.Name] pulls the t-shirt down over your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your t-shirt.",
					"You take off [npc.namePos] t-shirt.",
					null,
					"[npc.Name] takes [npc.her] t-shirt off.",
					"[npc.Name] takes your t-shirt off.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType KIMONO_HAIR_KANZASHI = new AbstractClothingType(500,
			"a",
			false,
			"kanzashi",
			"kanzashi",
			"A traditional Japanese hair ornament, composed primarily of folded cloth flowers.",
			0,
			Femininity.FEMININE,
			InventorySlot.HAIR,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_geisha"),
			"clothing/kimono_hair_kanzashi",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0)),
			null,
			null,
			ColourListPresets.KIMONO,
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			ColourListPresets.KIMONO,
			ColourListPresets.ALL,
			Util.newArrayListOfValues(ItemTag.REINDEER_GIFT)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You tie up your [pc.hair(true)] and fasten the kanzashi in place.",
					"You tie up [npc.namePos] [npc.hair(true)] and fasten the kanzashi in place.",
					null,
					"[npc.Name] ties up [npc.her] [npc.hair(true)] and fastens the kanzashi in place.",
					"[npc.Name] ties up your [pc.hair(true)] and fastens the kanzashi in place.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your kanzashi.",
					"You take off [npc.namePos] kanzashi.",
					null,
					"[npc.Name] takes [npc.her] kanzashi off.",
					"[npc.Name] takes your kanzashi off.",
					null, null, null);
		}

	};
	
	public static AbstractClothingType KIMONO_DRESS = new AbstractClothingType(3000,
			"a",
			false,
			"kimono",
			"kimonos",
			"Primarily worn by the reclusive youko, these full-length robes are identical to their traditional Japanese namesake.",
			0,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_geisha"),
			"clothing/kimono_torso_kimono",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.HEAD),
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.BACK),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST), 
							null),
					new BlockedParts(
							DisplacementType.UNTIE,
							null,
							Util.newArrayListOfValues(
									CoverableArea.PENIS,
									CoverableArea.VAGINA,
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH),
							Util.newArrayListOfValues(
									
									ClothingAccess.GROIN,
									ClothingAccess.ANUS,
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_DRESS_FRONT_FULL.getPresetInventorySlotList()),
					new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							Util.newArrayListOfValues(
									ClothingAccess.GROIN,
									ClothingAccess.ANUS),
							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList())),
			null,
			ColourListPresets.KIMONO,
			ColourListPresets.ALL,
			ColourListPresets.KIMONO,
			ColourListPresets.ALL,
			ColourListPresets.KIMONO,
			ColourListPresets.ALL,
			Util.newArrayListOfValues(ItemTag.REINDEER_GIFT, ItemTag.DRESS, ItemTag.FITS_ARM_WINGS)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the kimono.",
					"You pull the kimono onto [npc.name].",
					null,
					"[npc.Name] pulls on the kimono.",
					"[npc.Name] pulls the kimono onto you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your kimono.",
					"You pull off [npc.namePos] kimono.",
					null,
					"[npc.Name] takes [npc.her] kimono off.",
					"[npc.Name] takes your kimono off.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType KIMONO_GETA = new AbstractClothingType(350,
			"a pair of",
			true,
			"geta",
			"geta",
			"These sandals have a slightly elevated wooden base, and are held onto the foot by means of a cloth thong.",
			0,
			Femininity.FEMININE,
			InventorySlot.FOOT,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_geisha"),
			"clothing/kimono_foot_geta",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.FEET),
							Util.newArrayListOfValues(CoverableArea.FEET),
							Util.newArrayListOfValues(ClothingAccess.FEET),
							null)),
			null,
			ColourListPresets.KIMONO,
			ColourListPresets.ALL,
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.REINDEER_GIFT)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slip your [pc.feet] into the geta.",
					"You push [npc.namePos] [npc.feet] into the geta.",
					"You force the geta onto [npc.namePos] [npc.feet].",
					"[npc.Name] slips [npc.her] [npc.feet] into the geta.",
					"[npc.Name] pushes your [pc.feet] into the geta.",
					"[npc.Name] forces the geta onto your [pc.feet].", null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide off your geta.",
					"You pull [npc.namePos] geta off.",
					"You grab [npc.namePos] [npc.feet] and pull [npc.her] geta off.",
					"[npc.Name] slides off [npc.her] geta.",
					"[npc.Name] pulls off your geta.",
					"[npc.Name] grabs your [pc.feet] and pulls your geta off.", null, null);
		}
	};
	
	public static AbstractClothingType KIMONO_MENS_KIMONO = new AbstractClothingType(1500,
			"a",
			false,
			"men's kimono",
			"men's kimonos",
			"Primarily worn by the reclusive youko, these full-length robes are identical to their traditional Japanese namesake.",
			0,
			Femininity.MASCULINE,
			InventorySlot.TORSO_UNDER,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_ronin"),
			"clothing/kimono_torso_mens_kimono",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_PHYSICAL, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.HEAD),
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.BACK),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST), 
							null),
					new BlockedParts(
							DisplacementType.UNTIE,
							null,
							Util.newArrayListOfValues(
									CoverableArea.PENIS,
									CoverableArea.VAGINA,
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH),
							Util.newArrayListOfValues(
									
									ClothingAccess.GROIN,
									ClothingAccess.ANUS,
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_DRESS_FRONT_FULL.getPresetInventorySlotList()),
					new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							Util.newArrayListOfValues(
									ClothingAccess.GROIN,
									ClothingAccess.ANUS),
							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList())),
			null,
			Util.newArrayListOfValues(
							PresetColour.CLOTHING_BLACK,
							PresetColour.CLOTHING_GREY,
							PresetColour.CLOTHING_BLUE),
			ColourListPresets.ALL,
			Util.newArrayListOfValues(
							PresetColour.CLOTHING_BLUE_LIGHT,
							PresetColour.CLOTHING_WHITE),
			ColourListPresets.ALL,
			ColourListPresets.JUST_GREY,
			ColourListPresets.ALL,
			Util.newArrayListOfValues(ItemTag.REINDEER_GIFT, ItemTag.DRESS, ItemTag.FITS_ARM_WINGS)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the kimono.",
					"You pull the kimono onto [npc.name].",
					null,
					"[npc.Name] pulls on the kimono.",
					"[npc.Name] pulls the kimono onto you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your kimono.",
					"You pull off [npc.namePos] kimono.",
					null,
					"[npc.Name] takes [npc.her] kimono off.",
					"[npc.Name] takes your kimono off.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType KIMONO_HAORI = new AbstractClothingType(750,
			"a",
			false,
			"men's haori",
			"men's haori",
			"A traditional thigh-length kimono-style jacket.",
			0,
			Femininity.MASCULINE,
			InventorySlot.TORSO_OVER,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_ronin"),
			"clothing/kimono_torso_over_haori",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_PHYSICAL, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER),
							Util.newArrayListOfValues(CoverableArea.BACK),
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER),
							null)),
			null,
			Util.newArrayListOfValues(
							PresetColour.CLOTHING_BLACK,
							PresetColour.CLOTHING_GREY,
							PresetColour.CLOTHING_BLUE),
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.REINDEER_GIFT, ItemTag.FITS_ARM_WINGS)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the haori.",
					"You guide [npc.namePos] [npc.arms] through the haori's sleeves as you pull it on [npc.herHim].",
					null,
					"[npc.Name] pulls on the haori.",
					"[npc.Name] guides your [pc.arms] through the haori's sleeves as [npc.she] pulls it on you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your haori.",
					"You pull off [npc.namePos] haori.",
					null,
					"[npc.Name] takes [npc.her] haori off.",
					"[npc.Name] pulls your haori off.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType KIMONO_MENS_GETA = new AbstractClothingType(350,
			"a pair of",
			true,
			"men's geta",
			"men's geta",
			"These sandals have a slightly elevated wooden base, and are held onto the foot by means of a cloth thong.",
			0,
			Femininity.MASCULINE,
			InventorySlot.FOOT,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_ronin"),
			"clothing/kimono_foot_mens_geta",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_PHYSICAL, TFPotency.BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.FEET),
							Util.newArrayListOfValues(CoverableArea.FEET),
							Util.newArrayListOfValues(ClothingAccess.FEET),
							null)),
			null,
			Util.newArrayListOfValues(
							PresetColour.CLOTHING_BLACK,
							PresetColour.CLOTHING_GREY,
							PresetColour.CLOTHING_BLUE),
			ColourListPresets.ALL,
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.REINDEER_GIFT)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slip your [pc.feet] into the geta.",
					"You push [npc.namePos] [npc.feet] into the geta.",
					"You force the geta onto [npc.namePos] [npc.feet].",
					"[npc.Name] slips [npc.her] [npc.feet] into the geta.",
					"[npc.Name] pushes your [pc.feet] into the geta.",
					"[npc.Name] forces the geta onto your [pc.feet].", null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide off your geta.",
					"You pull [npc.namePos] geta off.",
					"You grab [npc.namePos] [npc.feet] and pull [npc.her] geta off.",
					"[npc.Name] slides off [npc.her] geta.",
					"[npc.Name] pulls off your geta.",
					"[npc.Name] grabs your [pc.feet] and pulls your geta off.", null, null);
		}
	};
	
	
	public static AbstractClothingType JOLNIR_HAT = new AbstractClothingType(400,
			"a",
			false,
			"J&oacute;lnir's hat",
			"J&oacute;lnir's hats",
			"A hat made in the same style as that worn by the Yule figure.",
			0,
			null,
			InventorySlot.HEAD,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_jolnir"),
			"clothing/jolnir_head_hat",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_ICE, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.HEAD),
							null, null, null)),
			null,
			ColourListPresets.JUST_RED,
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.REINDEER_GIFT)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You put on J&oacute;lnir's hat.",
					"You place J&oacute;lnir's hat onto [npc.namePos] head.",
					null,
					"[npc.Name] puts on J&oacute;lnir's hat.",
					"[npc.Name] places J&oacute;lnir's hat onto your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off J&oacute;lnir's hat.",
					"You take off [npc.namePos] J&oacute;lnir's hat.",
					null,
					"[npc.Name] takes J&oacute;lnir's hat off.",
					"[npc.Name] takes your J&oacute;lnir's hat off.",
					null, null, null);
		}

	};
	
	public static AbstractClothingType JOLNIR_COAT = new AbstractClothingType(750,
			"a",
			false,
			"J&oacute;lnir's coat",
			"J&oacute;lnir's coats",
			"A coat similar to the one worn by the Yule figure.",
			0,
			null,
			InventorySlot.TORSO_OVER,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_jolnir"),
			"clothing/jolnir_torso_over_coat",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.SPELL_COST_MODIFIER, TFPotency.MAJOR_BOOST, 0), 
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_SPELLS, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER),
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH,
									CoverableArea.BACK),
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER),
							PresetConcealmentLists.CONCEALED_FULL_TORSO.getPresetInventorySlotList())),
			null,
			ColourListPresets.JUST_RED,
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			Util.newArrayListOfValues(ItemTag.REINDEER_GIFT)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the coat.",
					"You guide [npc.namePos] [npc.arms] through the coat's sleeves as you pull it on [npc.herHim].",
					null,
					"[npc.Name] pulls on the coat.",
					"[npc.Name] guides your [pc.arms] through the coat's sleeves as [npc.she] pulls it on you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your coat.",
					"You pull off [npc.namePos] coat.",
					null,
					"[npc.Name] takes [npc.her] coat off.",
					"[npc.Name] pulls your coat off.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType JOLNIR_DRESS = new AbstractClothingType(900,
			"a",
			false,
			"J&oacute;lnir's dress",
			"J&oacute;lnir's dresses",
			"A dress made to be in the same style as the clothing worn by the Yule figure.",
			0,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_jolnir"),
			"clothing/jolnir_torso_dress",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.SPELL_COST_MODIFIER, TFPotency.MAJOR_BOOST, 0), 
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_SPELLS, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.HEAD),
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH,
									CoverableArea.BACK),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList()),
					new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							Util.newArrayListOfValues(
									ClothingAccess.GROIN,
									ClothingAccess.ANUS),
							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList())),
			null,
			ColourListPresets.JUST_RED,
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			Util.newArrayListOfValues(ItemTag.REINDEER_GIFT, ItemTag.DRESS, ItemTag.FITS_ARM_WINGS)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the J&oacute;lnir's dress, tidying the skirt down before moving the straps into a comfortable position on your shoulders.",
					"You pull the J&oacute;lnir's dress over [npc.namePos] head and down around [npc.her] torso, tidying the skirt before moving the straps to sit comfortably on [npc.her] shoulders.",
					null,
					"[npc.Name] pulls on the J&oacute;lnir's dress, tidying the skirt down before moving the straps into a comfortable position on [npc.her] shoulders.",
					"[npc.Name] pulls the J&oacute;lnir's dress over your head and down around your torso, tidying the skirt before moving the straps to sit comfortably on your shoulders.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull your J&oacute;lnir's dress up over your head and take it off.",
					"You pull [npc.namePos] J&oacute;lnir's dress up over [npc.her] head and take it off.",
					null,
					"[npc.Name] pulls [npc.her] J&oacute;lnir's dress up over [npc.her] head and takes it off.",
					"[npc.Name] pulls your J&oacute;lnir's dress up over your head and takes it off.",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull up the skirt of your J&oacute;lnir's dress.",
					"You pull up the skirt of [npc.namePos] J&oacute;lnir's dress.",
					null,
					"[npc.Name] pulls up the skirt of [npc.her] J&oacute;lnir's dress.",
					"[npc.Name] pulls up the skirt of your J&oacute;lnir's dress.",
					null, null, null);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull your J&oacute;lnir's dress back down into its proper position.",
					"You pull [npc.namePos] J&oacute;lnir's dress back down into its proper position.",
					null,
					"[npc.Name] pulls [npc.her] J&oacute;lnir's dress back down into its proper position.",
					"[npc.Name] your J&oacute;lnir's dress back down into its proper position.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType JOLNIR_BOOTS = new AbstractClothingType(500,
			"a pair of",
			true,
			"J&oacute;lnir's boot",
			"J&oacute;lnir's boots",
			"A pair of boots, of the sort worn by the Yule figure.",
			0,
			Femininity.MASCULINE,
			InventorySlot.FOOT,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_jolnir"),
			"clothing/jolnir_foot_boots",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_ICE, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.FEET),
							Util.newArrayListOfValues(CoverableArea.FEET),
							Util.newArrayListOfValues(ClothingAccess.FEET),
							null)),
			null,
			ColourListPresets.JUST_RED,
			ColourListPresets.ALL,
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			Util.newArrayListOfValues(ItemTag.REINDEER_GIFT)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You push your [pc.feet] into the boots.",
					"You push [npc.namePos] [npc.feet] into the boots.",
					"You force the boots onto [npc.namePos] [npc.feet].",
					"[npc.Name] pushes [npc.her] [npc.feet] into the boots.",
					"[npc.Name] pushes your [pc.feet] into the boots.",
					"[npc.Name] forces the boots onto your [pc.feet].", null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off your boots.",
					"You pull [npc.namePos] boots off.",
					"You grab [npc.namePos] [npc.feet] and pull [npc.her] boots off.",
					"[npc.Name] pulls off [npc.her] boots.",
					"[npc.Name] pulls off your boots.",
					"[npc.Name] grabs your [pc.feet] and pulls your boots off.", null, null);
		}
	};
	
	public static AbstractClothingType JOLNIR_BOOTS_FEMININE = new AbstractClothingType(750,
			"a pair of",
			true,
			"J&oacute;lnir's heeled boot",
			"J&oacute;lnir's heeled boots",
			"A pair of boots, of the same style as those worn by the Yule figure.",
			0,
			Femininity.FEMININE,
			InventorySlot.FOOT,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_jolnir"),
			"clothing/jolnir_foot_boots_feminine",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_ICE, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.FEET),
							Util.newArrayListOfValues(CoverableArea.FEET),
							Util.newArrayListOfValues(ClothingAccess.FEET),
							null)),
			null,
			ColourListPresets.JUST_RED,
			ColourListPresets.ALL,
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			Util.newArrayListOfValues(ItemTag.REINDEER_GIFT)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You push your [pc.feet] into the boots.",
					"You push [npc.namePos] [npc.feet] into the boots.",
					"You force the boots onto [npc.namePos] [npc.feet].",
					"[npc.Name] pushes [npc.her] [npc.feet] into the boots.",
					"[npc.Name] pushes your [pc.feet] into the boots.",
					"[npc.Name] forces the boots onto your [pc.feet].", null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off your boots.",
					"You pull [npc.namePos] boots off.",
					"You grab [npc.namePos] [npc.feet] and pull [npc.her] boots off.",
					"[npc.Name] pulls off [npc.her] boots.",
					"[npc.Name] pulls off your boots.",
					"[npc.Name] grabs your [pc.feet] and pulls your boots off.", null, null);
		}
	};

	public static AbstractClothingType FINGER_LYSSIETHS_RING = new AbstractClothingType(50000,
			"",
			false,
			"Lyssieth's Signet Ring",
			"Lyssieth's Signet Rings",
			"Beautifully crafted, and encrusted with precious gemstones, Lyssieth's Signet Ring commands the respect and loyalty of any member of her household."
					+ " As expected of a symbol of a Lilin's authority, this item holds immense, albeit corruptive, power.",
			0,
			Femininity.ANDROGYNOUS,
			InventorySlot.FINGER,
			Rarity.QUEST,
			null,
			"items/lyssiethsRing",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_SPELLS, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_SPELLS, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_SPELLS, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.SPELL_COST_MODIFIER, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.SPELL_COST_MODIFIER, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.SPELL_COST_MODIFIER, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.CORRUPTION, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.CORRUPTION, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.CORRUPTION, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.CORRUPTION, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.CORRUPTION, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.FINGERS),
							null,
							null, null)),
			null,
			ColourListPresets.JUST_ROSE_GOLD,
			ColourListPresets.ALL_METAL,
			ColourListPresets.JUST_DARK_RED,
			ColourListPresets.ALL,
			ColourListPresets.JUST_ROSE_GOLD,
			ColourListPresets.ALL_METAL,
			null){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if(rough) {
				return UtilText.parse(clothingOwner, clothingRemover, "[npc.Name] roughly [npc.verb(force)] the signet ring onto [npc2.namePos] finger.");
			} else {
				if(clothingOwner.equals(clothingRemover)) {
					return UtilText.parse(clothingOwner, clothingRemover, "[npc.Name] [npc.verb(slide)] the signet ring onto [npc.her] finger.");
				} else {
					return UtilText.parse(clothingOwner, clothingRemover, "[npc.Name] [npc.verb(slide)] the signet ring onto [npc2.namePos] finger.");
				}
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if(rough) {
				return UtilText.parse(clothingOwner, clothingRemover, "[npc.Name] roughly [npc.verb(yank)] the signet ring off of [npc2.namePos] finger.");
			} else {
				if(clothingOwner.equals(clothingRemover)) {
					return UtilText.parse(clothingOwner, clothingRemover, "[npc.Name] [npc.verb(slide)] the signet ring off [npc.her] finger.");
				} else {
					return UtilText.parse(clothingOwner, clothingRemover, "[npc.Name] [npc.verb(slide)] the signet ring off [npc2.namePos] finger.");
				}
			}
		}
	};
	
	
	private static List<AbstractClothingType> allClothing;
	private static List<AbstractClothingType> moddedClothingList;
	
	private static List<InventorySlot> coreClothingSlots;
	private static List<InventorySlot> lingerieSlots;
	
	private static Map<InventorySlot, List<AbstractClothingType>> commonClothingMap;
	private static Map<InventorySlot, List<AbstractClothingType>> commonClothingMapFemale;
	private static Map<InventorySlot, List<AbstractClothingType>> commonClothingMapMale;
	private static Map<InventorySlot, List<AbstractClothingType>> commonClothingMapAndrogynous;
	private static Map<InventorySlot, List<AbstractClothingType>> commonClothingMapFemaleIncludingAndrogynous;
	private static Map<InventorySlot, List<AbstractClothingType>> commonClothingMapMaleIncludingAndrogynous;
	
	private static Map<Occupation, ArrayList<AbstractClothingType>> suitableFeminineClothing = new HashMap<>();
	
	private static Map<AbstractClothingType, String> clothingToIdMap = new HashMap<>();
	private static Map<String, AbstractClothingType> idToClothingMap = new HashMap<>();
	
	private static Map<String, String> oldIdConversionMap = new HashMap<>();
	
	public static AbstractClothingType getClothingTypeFromId(String id) {
//		System.out.print("ID: "+id);
		
		if(oldIdConversionMap.containsKey(id)) {
			id = oldIdConversionMap.get(id);
		}
		
		id = Util.getClosestStringMatch(id, idToClothingMap.keySet());
		
//		System.out.println("  set to: "+id);
		
		return idToClothingMap.get(id);
	}
	
	public static String getIdFromClothingType(AbstractClothingType clothingType) {
		return clothingToIdMap.get(clothingType);
	}

	public static Map<Occupation, ArrayList<AbstractClothingType>> getSuitableFeminineClothing() {
		return suitableFeminineClothing;
	}

	static {
		// Clothing set items:
		oldIdConversionMap.put("NECK_SNOWFLAKE_NECKLACE", "innoxia_elemental_snowflake_necklace");
		oldIdConversionMap.put("PIERCING_EAR_SNOW_FLAKES", "innoxia_elemental_piercing_ear_snowflakes");
		oldIdConversionMap.put("PIERCING_NOSE_SNOWFLAKE_STUD", "innoxia_elemental_piercing_nose_snowflake");
		
		oldIdConversionMap.put("NECK_SUN_NECKLACE", "innoxia_elemental_sun_necklace");
		oldIdConversionMap.put("PIERCING_EAR_SUN", "innoxia_elemental_piercing_ear_sun");
		oldIdConversionMap.put("PIERCING_NOSE_SUN_STUD", "innoxia_elemental_piercing_nose_sun");

		oldIdConversionMap.put("CATTLE_NECK_COWBELL_COLLAR", "innoxia_cattle_cowbell_collar");
		oldIdConversionMap.put("CATTLE_PIERCING_EAR_TAGS", "innoxia_cattle_piercing_ear_tag");
		oldIdConversionMap.put("CATTLE_PIERCING_NOSE_BOVINE_RING", "innoxia_cattle_piercing_nose_ring");

		oldIdConversionMap.put("SOCK_RAINBOW_STOCKINGS", "innoxia_rainbow_stockings");
		oldIdConversionMap.put("HAND_RAINBOW_FINGERLESS_GLOVES", "innoxia_rainbow_gloves");

		oldIdConversionMap.put("BDSM_CHASTITY_CAGE", "innoxia_bdsm_chastity_cage");
		oldIdConversionMap.put("BDSM_PENIS_STRAPON", "innoxia_bdsm_penis_strapon");
		oldIdConversionMap.put("innoxia_bdsmBracelets_wrist_bracelets", "innoxia_bdsm_wrist_bracelets");
		
		oldIdConversionMap.put("WITCH_HAT", "innoxia_witch_witch_hat");
		oldIdConversionMap.put("WITCH_DRESS", "innoxia_witch_witch_dress");
		oldIdConversionMap.put("WITCH_BOOTS", "innoxia_witch_witch_boots");
		oldIdConversionMap.put("WITCH_BOOTS_THIGH_HIGH", "innoxia_witch_witch_boots_thigh_high");

		oldIdConversionMap.put("EYES_SAFETY_GOGGLES", "innoxia_scientist_safety_goggles");
		oldIdConversionMap.put("SCIENTIST_EYES_SAFETY_GOGGLES", "innoxia_scientist_safety_goggles");
		oldIdConversionMap.put("SCIENTIST_TORSO_OVER_LAB_COAT", "innoxia_scientist_lab_coat");

		oldIdConversionMap.put("AMBERS_BITCH_CHOKER", "innoxia_neck_ambers_bitch_collar");
		
		
		// Standard items:
		oldIdConversionMap.put("kobolds_belt_leather_belt", "innoxia_hips_leather_belt");
		oldIdConversionMap.put("PENIS_CONDOM", "innoxia_penis_condom");
		
		oldIdConversionMap.put("ANKLE_BRACELET", "innoxia_ankle_anklet");
		oldIdConversionMap.put("ANKLE_SHIN_GUARDS", "innoxia_ankle_shin_guards");
		
		oldIdConversionMap.put("PIERCING_EAR_RING", "innoxia_piercing_ear_ring");
		oldIdConversionMap.put("PIERCING_EAR_BASIC_RING", "innoxia_piercing_ear_ring");
		oldIdConversionMap.put("PIERCING_EAR_HOOPS", "innoxia_piercing_ear_hoops");
		oldIdConversionMap.put("PIERCING_NOSE_BASIC_RING", "innoxia_piercing_nose_ring");
		oldIdConversionMap.put("PIERCING_LIP_RINGS", "innoxia_piercing_lip_double_ring");
		oldIdConversionMap.put("PIERCING_TONGUE_BAR", "innoxia_piercing_basic_barbell");
		oldIdConversionMap.put("PIERCING_NIPPLE_BARS", "innoxia_piercing_basic_barbell_pair");
		oldIdConversionMap.put("PIERCING_NAVEL_GEM", "innoxia_piercing_gemstone_barbell");
		oldIdConversionMap.put("PIERCING_VAGINA_BARBELL_RING", "innoxia_piercing_ringed_barbell");
		oldIdConversionMap.put("PIERCING_PENIS_RING", "innoxia_piercing_penis_ring");

		oldIdConversionMap.put("EYES_GLASSES", "innoxia_eye_glasses");
		oldIdConversionMap.put("EYES_AVIATORS", "innoxia_eye_aviators");
		oldIdConversionMap.put("EYES_PATCH", "innoxia_eye_patch");
		
		oldIdConversionMap.put("HEAD_CHEATERS_CIRCLET", "innoxia_head_circlet");
		oldIdConversionMap.put("HEAD_CIRCLET", "innoxia_head_circlet");
		oldIdConversionMap.put("HEAD_TIARA", "innoxia_head_tiara");
		oldIdConversionMap.put("HEAD_CAP", "innoxia_head_cap");
		oldIdConversionMap.put("HEAD_HEADBAND", "innoxia_head_headband");
		oldIdConversionMap.put("HEAD_HEADBAND_BOW", "innoxia_head_headband_bow");
		oldIdConversionMap.put("HEAD_SWEATBAND", "innoxia_head_sweatband");
		oldIdConversionMap.put("HEAD_COWBOY_HAT", "innoxia_head_cowboy_hat");
		oldIdConversionMap.put("HEAD_ANTLER_HEADBAND", "innoxia_head_antler_headband");
		oldIdConversionMap.put("HEAD_SLIME_QUEENS_TIARA", "innoxia_head_slime_queens_tiara");
		
		oldIdConversionMap.put("HAND_ELBOWLENGTH_GLOVES", "innoxia_hand_elbow_length_gloves");
		oldIdConversionMap.put("HAND_GLOVES", "innoxia_hand_gloves");
		oldIdConversionMap.put("HAND_FINGERLESS_GLOVES", "innoxia_hand_fingerless_gloves");
		oldIdConversionMap.put("HAND_WRAPS", "innoxia_hand_wraps");
		oldIdConversionMap.put("HAND_FISHNET_GLOVES", "innoxia_hand_fishnet_gloves");

		oldIdConversionMap.put("FINGER_RING", "innoxia_finger_ring");

		oldIdConversionMap.put("NECK_ANKH_NECKLACE", "innoxia_neck_ankh_necklace");
		oldIdConversionMap.put("NECK_BELL_COLLAR", "innoxia_neck_bell_collar");
		oldIdConversionMap.put("NECK_COLLAR_BOWTIE", "innoxia_neck_collar_bowtie");
		oldIdConversionMap.put("NECK_HEART_NECKLACE", "innoxia_neck_heart_necklace");
		oldIdConversionMap.put("NECK_SCARF", "innoxia_neck_scarf");
		oldIdConversionMap.put("NECK_TIE", "innoxia_neck_tie");
		oldIdConversionMap.put("NECK_BREEDER_COLLAR", "innoxia_neck_breeder_collar");
		oldIdConversionMap.put("NECK_SLAVE_COLLAR", "innoxia_bdsm_metal_collar");

		oldIdConversionMap.put("CHEST_LACY_PLUNGE_BRA", "innoxia_chest_lacy_plunge_bra");
		
		oldIdConversionMap.put("LEG_SKIRT", "innoxia_leg_skirt");
		oldIdConversionMap.put("LEG_PENCIL_SKIRT", "innoxia_leg_pencil_skirt");
		oldIdConversionMap.put("LEG_MINI_SKIRT", "innoxia_leg_mini_skirt");
		oldIdConversionMap.put("LEG_MICRO_SKIRT_PLEATED", "innoxia_leg_micro_skirt_pleated");
		oldIdConversionMap.put("LEG_MICRO_SKIRT_BELTED", "innoxia_leg_micro_skirt_belted");
		oldIdConversionMap.put("LEG_SHORTS", "innoxia_leg_shorts");
		oldIdConversionMap.put("LEG_BIKE_SHORTS", "innoxia_leg_bike_shorts");
		oldIdConversionMap.put("LEG_SPORT_SHORTS", "innoxia_leg_sport_shorts");
		oldIdConversionMap.put("LEG_HOTPANTS", "innoxia_leg_hotpants");
		oldIdConversionMap.put("LEG_TIGHT_TROUSERS", "innoxia_leg_tight_jeans");
		oldIdConversionMap.put("LEG_JEANS", "innoxia_leg_jeans");
		oldIdConversionMap.put("LEG_TROUSERS", "innoxia_leg_trousers");
		oldIdConversionMap.put("LEG_CARGO_TROUSERS", "innoxia_leg_cargo_trousers");
		oldIdConversionMap.put("LEG_YOGA_PANTS", "innoxia_leg_yoga_pants");
		oldIdConversionMap.put("LEG_ASSLESS_CHAPS", "innoxia_leg_assless_chaps");
		oldIdConversionMap.put("LEG_CROTCHLESS_CHAPS", "innoxia_leg_crotchless_chaps");
		
		oldIdConversionMap.put("FOOT_ANKLE_BOOTS", "innoxia_foot_ankle_boots");
		oldIdConversionMap.put("FOOT_CHELSEA_BOOTS", "innoxia_foot_chelsea_boots");
		oldIdConversionMap.put("FOOT_HEELS", "innoxia_foot_heels");
		oldIdConversionMap.put("FOOT_LOW_TOP_SKATER_SHOES", "innoxia_foot_low_top_skater_shoes");
		oldIdConversionMap.put("FOOT_MENS_SMART_SHOES", "innoxia_foot_mens_smart_shoes");
		oldIdConversionMap.put("FOOT_PLATFORM_BOOTS", "innoxia_foot_platform_boots");
		oldIdConversionMap.put("FOOT_STILETTO_HEELS", "innoxia_foot_stiletto_heels");
		oldIdConversionMap.put("FOOT_THIGH_HIGH_BOOTS", "innoxia_foot_thigh_high_boots");
		oldIdConversionMap.put("FOOT_TRAINERS", "innoxia_foot_trainers");
		oldIdConversionMap.put("FOOT_WORK_BOOTS", "innoxia_foot_work_boots");

		oldIdConversionMap.put("TORSO_TSHIRT", "innoxia_torso_tshirt");

		oldIdConversionMap.put("TORSO_OVER_SUIT_JACKET", "innoxia_torsoOver_suit_jacket");
		
		oldIdConversionMap.put("SOCK_SOCKS", "innoxia_sock_socks");
		oldIdConversionMap.put("SOCK_TRAINER_SOCKS", "innoxia_sock_trainer_socks");
		oldIdConversionMap.put("SOCK_KNEEHIGH_SOCKS", "innoxia_sock_kneehigh_socks");
		oldIdConversionMap.put("SOCK_STOCKINGS", "innoxia_sock_stockings");
		oldIdConversionMap.put("SOCK_THIGHHIGH_SOCKS", "innoxia_sock_thighhigh_socks");
		oldIdConversionMap.put("SOCK_THIGHHIGH_SOCKS_STRIPED", "innoxia_sock_thighhigh_socks_striped");
		oldIdConversionMap.put("SOCK_TIGHTS", "innoxia_sock_pantyhose");
		oldIdConversionMap.put("SOCK_FISHNET_STOCKINGS", "innoxia_sock_fishnets");
		oldIdConversionMap.put("SOCK_TOELESS_STRIPED_STOCKINGS", "innoxia_sock_toeless_striped_stockings");

		
		commonClothingMap = new EnumMap<>(InventorySlot.class);
		commonClothingMapFemale = new EnumMap<>(InventorySlot.class);
		commonClothingMapMale = new EnumMap<>(InventorySlot.class);
		commonClothingMapAndrogynous = new EnumMap<>(InventorySlot.class);
		commonClothingMapFemaleIncludingAndrogynous = new EnumMap<>(InventorySlot.class);
		commonClothingMapMaleIncludingAndrogynous = new EnumMap<>(InventorySlot.class);
		
		for(InventorySlot slot : InventorySlot.values()) {
			commonClothingMap.put(slot, new ArrayList<>());
			commonClothingMapFemale.put(slot, new ArrayList<>());
			commonClothingMapMale.put(slot, new ArrayList<>());
			commonClothingMapAndrogynous.put(slot, new ArrayList<>());
			commonClothingMapFemaleIncludingAndrogynous.put(slot, new ArrayList<>());
			commonClothingMapMaleIncludingAndrogynous.put(slot, new ArrayList<>());
		}
		
		coreClothingSlots = Util.newArrayListOfValues(InventorySlot.TORSO_UNDER, InventorySlot.LEG);
		lingerieSlots = Util.newArrayListOfValues(InventorySlot.CHEST, InventorySlot.GROIN, InventorySlot.STOMACH, InventorySlot.SOCK);

		allClothing = new ArrayList<>();
		
		// Load in modded clothing:
		moddedClothingList = new ArrayList<>();
		File dir = new File("res/mods");
		
		if (dir.exists() && dir.isDirectory()) {
			File[] modDirectoryListing = dir.listFiles();
			if (modDirectoryListing != null) {
				for (File modAuthorDirectory : modDirectoryListing) {
					File modAuthorClothingDirectory = new File(modAuthorDirectory.getAbsolutePath()+"/items/clothing");
					
					File[] clothingDirectoriesListing = modAuthorClothingDirectory.listFiles();
					if (clothingDirectoriesListing != null) {
						for (File clothingDirectory : clothingDirectoriesListing) {
							if (clothingDirectory.isDirectory()){
								File[] innerDirectoryListing = clothingDirectory.listFiles((path, filename) -> filename.endsWith(".xml"));
								if (innerDirectoryListing != null) {
									for (File innerChild : innerDirectoryListing) {
										try{
											String id = modAuthorDirectory.getName()+"_"+innerChild.getParentFile().getName()+"_"+innerChild.getName().split("\\.")[0];
											AbstractClothingType ct = new AbstractClothingType(innerChild, modAuthorDirectory.getName()) {};
											moddedClothingList.add(ct);
											clothingToIdMap.put(ct, id);
											idToClothingMap.put(id, ct);

											if(ct.getRarity()==Rarity.COMMON) {
												commonClothingMap.get(ct.getEquipSlots().get(0)).add(ct);
												
												if (ct.getFemininityRestriction() == Femininity.FEMININE) {
													commonClothingMapFemale.get(ct.getEquipSlots().get(0)).add(ct);
													commonClothingMapFemaleIncludingAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
													
												} else if (ct.getFemininityRestriction() == Femininity.ANDROGYNOUS || ct.getFemininityRestriction() == null) {
													commonClothingMapAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
													commonClothingMapFemaleIncludingAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
													commonClothingMapMaleIncludingAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
													
												} else if (ct.getFemininityRestriction() == Femininity.MASCULINE) {
													commonClothingMapMale.get(ct.getEquipSlots().get(0)).add(ct);
													commonClothingMapMaleIncludingAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
												}
											}
											
										} catch(XMLLoadException ex){ // we want to catch any errors here; we shouldn't want to load any mods that are invalid as that may cause severe bugs
											System.err.println(ex);
											System.out.println(ex); // temporary, I think mod loading failure should be displayed to player on screen
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		allClothing.addAll(moddedClothingList);
		
		
		// Add in external res clothing:
		
		dir = new File("res/clothing");
		
		if (dir.exists() && dir.isDirectory()) {
			File[] authorDirectoriesListing = dir.listFiles();
			if (authorDirectoriesListing != null) {
				for (File authorDirectory : authorDirectoriesListing) {
					if (authorDirectory.isDirectory()){
						for (File clothingDirectory : authorDirectory.listFiles()) {
							if (clothingDirectory.isDirectory()){
								File[] innerDirectoryListing = clothingDirectory.listFiles((path, filename) -> filename.endsWith(".xml"));
								if (innerDirectoryListing != null) {
									for (File innerChild : innerDirectoryListing) {
										try {
											String id = authorDirectory.getName()+"_"+innerChild.getParentFile().getName()+"_"+innerChild.getName().split("\\.")[0];
											AbstractClothingType ct = new AbstractClothingType(innerChild, authorDirectory.getName()) {};
											allClothing.add(ct);
											clothingToIdMap.put(ct, id);
											idToClothingMap.put(id, ct);

											if(ct.getRarity()==Rarity.COMMON) {
												commonClothingMap.get(ct.getEquipSlots().get(0)).add(ct);
												
												if (ct.getFemininityRestriction() == Femininity.FEMININE) {
													commonClothingMapFemale.get(ct.getEquipSlots().get(0)).add(ct);
													commonClothingMapFemaleIncludingAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
													
												} else if (ct.getFemininityRestriction() == Femininity.ANDROGYNOUS || ct.getFemininityRestriction() == null) {
													commonClothingMapAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
													commonClothingMapFemaleIncludingAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
													commonClothingMapMaleIncludingAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
													
												} else if (ct.getFemininityRestriction() == Femininity.MASCULINE) {
													commonClothingMapMale.get(ct.getEquipSlots().get(0)).add(ct);
													commonClothingMapMaleIncludingAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
												}
											}
											
										} catch(Exception ex) {
											System.err.println("Loading modded clothing failed at 'ClothingType' Code 2. File path: "+innerChild.getAbsolutePath());
											System.err.println("Actual exception: ");
											ex.printStackTrace(System.err);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		
		
		// Add in hard-coded clothing:
		
		Field[] fields = ClothingType.class.getFields();
		
		for(Field f : fields) {
			if (AbstractClothingType.class.isAssignableFrom(f.getType())) {
				AbstractClothingType ct;
				try {
					ct = ((AbstractClothingType) f.get(null));

					// I feel like this is stupid :thinking:
					clothingToIdMap.put(ct, f.getName());
					idToClothingMap.put(f.getName(), ct);
					
					allClothing.add(ct);
					
					// hmm... I don't know why this is here... TODO remove?
					if(ct.isCondom(ct.getEquipSlots().get(0))
//							|| ct==ClothingType.TORSO_OVER_CHRISTMAS_SWEATER
//							|| ct==ClothingType.HEAD_ANTLER_HEADBAND
//							|| ct==ClothingType.PIERCING_EAR_SNOW_FLAKES
//							|| ct==ClothingType.PIERCING_NOSE_SNOWFLAKE_STUD
//							|| ct==ClothingType.NECK_SNOWFLAKE_NECKLACE
							) {
						continue;
					}
					
					if(ct.getRarity()==Rarity.COMMON) {
						commonClothingMap.get(ct.getEquipSlots().get(0)).add(ct);
						
						if (ct.getFemininityRestriction() == Femininity.FEMININE) {
							commonClothingMapFemale.get(ct.getEquipSlots().get(0)).add(ct);
							commonClothingMapFemaleIncludingAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
							
						} else if (ct.getFemininityRestriction() == Femininity.ANDROGYNOUS || ct.getFemininityRestriction() == null) {
							commonClothingMapAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
							commonClothingMapFemaleIncludingAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
							commonClothingMapMaleIncludingAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
							
						} else if (ct.getFemininityRestriction() == Femininity.MASCULINE) {
							commonClothingMapMale.get(ct.getEquipSlots().get(0)).add(ct);
							commonClothingMapMaleIncludingAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
						}
					}
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
//  	    System.out.println(allClothing.size());
		//TODO shouldn't this be handled in outfit files?
		suitableFeminineClothing.put(Occupation.NPC_PROSTITUTE,
				Util.newArrayListOfValues(
						ClothingType.getClothingTypeFromId("innoxia_ankle_anklet"),
						ClothingType.getClothingTypeFromId("innoxia_chest_lacy_plunge_bra"),
						
						ClothingType.CHEST_OPEN_CUP_BRA,
						ClothingType.CHEST_PLUNGE_BRA,
						ClothingType.getClothingTypeFromId("innoxia_eye_aviators"),
						ClothingType.getClothingTypeFromId("innoxia_finger_ring"),
						ClothingType.getClothingTypeFromId("innoxia_foot_chelsea_boots"),
						ClothingType.getClothingTypeFromId("innoxia_foot_ankle_boots"),
						ClothingType.getClothingTypeFromId("innoxia_foot_heels"),
						ClothingType.getClothingTypeFromId("innoxia_foot_thigh_high_boots"),
						ClothingType.getClothingTypeFromId("innoxia_foot_stiletto_heels"),
						ClothingType.GROIN_BACKLESS_PANTIES,
						ClothingType.GROIN_CROTCHLESS_PANTIES,
						ClothingType.GROIN_CROTCHLESS_THONG,
						ClothingType.GROIN_LACY_PANTIES,
						ClothingType.GROIN_THONG,
						ClothingType.GROIN_VSTRING,
						ClothingType.getClothingTypeFromId("innoxia_hand_elbow_length_gloves"),
						ClothingType.getClothingTypeFromId("innoxia_head_headband"),
						ClothingType.getClothingTypeFromId("innoxia_head_headband_bow"),
						ClothingType.getClothingTypeFromId("innoxia_leg_crotchless_chaps"),
						ClothingType.getClothingTypeFromId("innoxia_leg_micro_skirt_belted"),
						ClothingType.getClothingTypeFromId("innoxia_leg_micro_skirt_pleated"),
						ClothingType.getClothingTypeFromId("innoxia_leg_mini_skirt"),
						ClothingType.getClothingTypeFromId("innoxia_leg_skirt"),
						ClothingType.getClothingTypeFromId("innoxia_neck_heart_necklace"),
						ClothingType.getClothingTypeFromId("innoxia_neck_ankh_necklace"),
						ClothingType.NIPPLE_TAPE_CROSSES,
						ClothingType.getClothingTypeFromId("innoxia_hand_fishnet_gloves"),
						ClothingType.getClothingTypeFromId("innoxia_sock_fishnets"),
						ClothingType.getClothingTypeFromId("innoxia_sock_pantyhose"),
						ClothingType.STOMACH_OVERBUST_CORSET,
						ClothingType.STOMACH_UNDERBUST_CORSET,
						ClothingType.TORSO_FISHNET_TOP,
						ClothingType.TORSO_KEYHOLE_CROPTOP,
						ClothingType.TORSO_SHORT_CROPTOP,
						ClothingType.WRIST_BANGLE,
						ClothingType.WRIST_WOMENS_WATCH,
						
						ClothingType.getClothingTypeFromId("innoxia_piercing_ear_ring"),
						ClothingType.getClothingTypeFromId("innoxia_piercing_lip_double_ring"),
						ClothingType.getClothingTypeFromId("innoxia_piercing_gemstone_barbell"),
						ClothingType.getClothingTypeFromId("innoxia_piercing_basic_barbell_pair"),
						ClothingType.getClothingTypeFromId("innoxia_piercing_nose_ring"),
						ClothingType.getClothingTypeFromId("innoxia_piercing_penis_ring"),
						ClothingType.getClothingTypeFromId("innoxia_piercing_basic_barbell"),
						ClothingType.getClothingTypeFromId("innoxia_piercing_ringed_barbell")));
	}
	
	public static List<AbstractClothingType> getAllClothing() {
		return allClothing;
	}

	public static List<AbstractClothingType> getModdedClothingList() {
		return moddedClothingList;
	}

	public static List<InventorySlot> getCoreClothingSlots() {
		return coreClothingSlots;
	}

	public static List<InventorySlot> getLingerieSlots() {
		return lingerieSlots;
	}
	
	public static Map<InventorySlot, List<AbstractClothingType>> getCommonClothingMap() {
		return commonClothingMap;
	}

	public static Map<InventorySlot, List<AbstractClothingType>> getCommonClothingMapFemale() {
		return commonClothingMapFemale;
	}

	public static Map<InventorySlot, List<AbstractClothingType>> getCommonClothingMapMale() {
		return commonClothingMapMale;
	}

	public static Map<InventorySlot, List<AbstractClothingType>> getCommonClothingMapAndrogynous() {
		return commonClothingMapAndrogynous;
	}
	
	public static Map<InventorySlot, List<AbstractClothingType>> getCommonClothingMapFemaleIncludingAndrogynous() {
		return commonClothingMapFemaleIncludingAndrogynous;
	}
	
	public static Map<InventorySlot, List<AbstractClothingType>> getCommonClothingMapMaleIncludingAndrogynous() {
		return commonClothingMapMaleIncludingAndrogynous;
	}

}
