package com.lilithsthrone.game.inventory.clothing;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.lilithsthrone.controller.xmlParsing.XMLLoadException;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractSetBonus;
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
 * @version 0.4.7.2
 * @author Innoxia
 */
public class ClothingType {
	
	//TODO
//	Replace tape crosses with special item "roll of tape"
//		10 uses, and can:
//			tape mouth
//			bind wrists
//			bind feet
//			tape nipples
//			tape pussy
//			tape asshole
	
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
									CoverableArea.BACK,
									CoverableArea.ARMPITS),
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
			ColourListPresets.JUST_STEEL,
			ColourListPresets.ALL_WITH_METALS,
			ColourListPresets.JUST_STEEL,
			ColourListPresets.ALL_WITH_METALS,
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
			ColourListPresets.ALL,
			ColourListPresets.JUST_STEEL,
			ColourListPresets.ALL_WITH_METALS,
			ColourListPresets.JUST_STEEL,
			ColourListPresets.ALL_WITH_METALS,
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
	

	// CLOTHING SETS:

	// MAID:
//	public static AbstractClothingType MAID_HEADPIECE = new AbstractClothingType(600,
//			"a",
//			false,
//			"Maid's headpiece",
//			"Maid's headpieces",
//			"A heavily stylised Maid's headpiece, it consists of a coloured headband with a decorative white lace attached on top.",
//			0,
//			Femininity.FEMININE,
//			InventorySlot.HEAD,
//			Rarity.EPIC,
//			SetBonus.getSetBonusFromId("innoxia_maid"),
//			"clothing/maidHeadband",
//			Util.newArrayListOfValues(
//					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.BOOST, 0)),
//			Util.newArrayListOfValues(
//					new BlockedParts(
//							DisplacementType.REMOVE_OR_EQUIP,
//							Util.newArrayListOfValues(ClothingAccess.HEAD),
//							null,
//							null,
//							null)),
//			null,
//			ColourListPresets.MAID,
//			ColourListPresets.ALL,
//			ColourListPresets.JUST_WHITE,
//			ColourListPresets.ALL,
//			null,
//			null,
//			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
//
//		@Override
//		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You slide the headpiece into place.",
//					"You slide the headpiece onto [npc.namePos] head.",
//					null,
//					"[npc.Name] slides the headpiece into place.",
//					"[npc.Name] slides the headpiece onto your head.",
//					null, null, null);
//		}
//
//		@Override
//		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You take off your headpiece.",
//					"You pull off [npc.namePos] headpiece.",
//					null,
//					"[npc.Name] takes [npc.her] headpiece off.",
//					"[npc.Name] pulls your headpiece off.",
//					null, null, null);
//		}
//
//	};
	
//	public static AbstractClothingType MAID_DRESS = new AbstractClothingType(2500,
//			"a",
//			false,
//			"Maid's dress",
//			"Maid's dresses",
//			"A heavily stylised Maid's dress, it consists of a coloured one-piece dress with decorative white lace trimmings."
//					+ " A small white apron is attached to the front, and is similarly trimmed in white lace.",
//			0,
//			Femininity.FEMININE,
//			InventorySlot.TORSO_UNDER,
//			Rarity.EPIC,
//			SetBonus.getSetBonusFromId("innoxia_maid"),
//			"clothing/maidDress",
//			Util.newArrayListOfValues(
//					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0)),
//			Util.newArrayListOfValues(
//					new BlockedParts(
//							DisplacementType.REMOVE_OR_EQUIP,
//							Util.newArrayListOfValues(
//									ClothingAccess.ARMS_UP_TO_SHOULDER,
//									ClothingAccess.HEAD),
//							Util.newArrayListOfValues(
//									CoverableArea.BREASTS,
//									CoverableArea.NIPPLES,
//									CoverableArea.STOMACH,
//									CoverableArea.BACK,
//									CoverableArea.ARMPITS),
//							Util.newArrayListOfValues(
//									ClothingAccess.CHEST,
//									ClothingAccess.WAIST),
//							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList()),
//					new BlockedParts(
//							DisplacementType.PULLS_UP,
//							null,
//							Util.newArrayListOfValues(
//									CoverableArea.ANUS,
//									CoverableArea.PENIS,
//									CoverableArea.VAGINA),
//							Util.newArrayListOfValues(
//									ClothingAccess.GROIN,
//									ClothingAccess.ANUS), 
//							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList())),
//			null,
//			ColourListPresets.MAID,
//			ColourListPresets.ALL,
//			ColourListPresets.JUST_WHITE,
//			ColourListPresets.ALL,
//			null,
//			null,
//			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.DRESS, ItemTag.FITS_ARM_WINGS)){
//
//		@Override
//		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You step into the dress and pull it up over your torso, zipping yourself up at the back before making sure the trimmings and apron are neatly arranged.",
//					"You get [npc.name] to step into the dress, before pulling it up over [npc.her] torso. Zipping up at the back, you then make sure that the trimmings and apron are neatly arranged.",
//					null,
//					"[npc.Name] steps into the dress and pulls it up over [npc.her] torso, zipping [npc.herself] up at the back before making sure the trimmings and apron are neatly arranged.",
//					"[npc.Name] gets you to step into the dress, before pulling it up over your torso. Zipping you up at the back, [npc.she] then makes sure that the trimmings and apron are neatly arranged.",
//					null, null, null);
//		}
//
//		@Override
//		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You reach back and unzip your Maid's dress, pulling your arms out before sliding it down your body and stepping out.",
//					"You unzip the back of [npc.namePos] Maid's dress, before pulling [npc.her] [npc.arms] free to allow the dress to fall to the floor.",
//					null,
//					"[npc.Name] reaches back and unzips [npc.her] Maid's dress, pulling [npc.her] arms out before sliding it down [npc.her] body and stepping out.",
//					"[npc.Name] unzips the back of your Maid's dress, before pulling your arms free to allow the dress to fall to the floor.",
//					null, null, null);
//		}
//
//		@Override
//		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
//			if (dt == DisplacementType.PULLS_DOWN) {
//				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
//					return "You unzip the back of your dress and pull down the top half.";
//				else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
//					return UtilText.parse(clothingOwner, "[npc.Name] unzips the back of [npc.her] dress and pulls the top half down");
//				else {
//					if (clothingOwner.isPlayer())
//						return "[npc.Name] unzips the back of your dress and pulls the top half down.";
//					else
//						return UtilText.parse(clothingOwner, "You unzip the back of [npc.namePos] dress and pull the top half down.");
//				}
//			} else {
//				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
//					return "You pull up your dress's skirt.";
//				else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
//					return UtilText.parse(clothingOwner, "[npc.Name] pulls up [npc.her] dress's skirt.");
//				else {
//					if (clothingOwner.isPlayer())
//						return "[npc.Name] pulls up your dress's skirt.";
//					else
//						return UtilText.parse(clothingOwner, "You pull up the skirt of [npc.namePos] dress.");
//				}
//			}
//		}
//
//		@Override
//		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
//			if (dt == DisplacementType.PULLS_DOWN) {
//				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
//					return "You pull your dress back up into the correct position and zip yourself up.";
//				else
//					return UtilText.parse(clothingOwner,
//							"[npc.Name] pulls [npc.her] dress up into the correct position and zips [npc.herself] up.");
//			} else {
//				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
//					return "You pull your dress's skirt back down.";
//				else
//					return UtilText.parse(clothingOwner, "[npc.Name] pulls [npc.her] dress's skirt back down.");
//			}
//		}
//	};
//	public static AbstractClothingType MAID_STOCKINGS = new AbstractClothingType(450,
//			"a pair of",
//			true,
//			"Maid's stockings",
//			"Maid's stockings",
//			"A pair of cotton Maid's stockings, with a coloured bow near the top.",
//			0,
//			Femininity.FEMININE,
//			InventorySlot.SOCK,
//			Rarity.EPIC,
//			SetBonus.getSetBonusFromId("innoxia_maid"),
//			"clothing/maidStockings",
//			Util.newArrayListOfValues(
//					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.BOOST, 0)),
//			Util.newArrayListOfValues(
//					new BlockedParts(
//							DisplacementType.REMOVE_OR_EQUIP,
//							Util.newArrayListOfValues(
//									ClothingAccess.FEET,
//									ClothingAccess.CALVES),
//							null, null, null)),
//			null,
//			ColourListPresets.MAID,
//			ColourListPresets.ALL,
//			ColourListPresets.JUST_WHITE,
//			ColourListPresets.ALL,
//			null,
//			null,
//			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
//
//		@Override
//		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You pull on the stockings.",
//					"You pull the stockings onto [npc.namePos] [npc.feet].",
//					null,
//					"[npc.Name] pulls on the stockings.",
//					"[npc.Name] pulls the stockings onto your [pc.feet].",
//					null, null, null);
//		}
//
//		@Override
//		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You pull off your stockings.",
//					"You pull off [npc.namePos] stockings.",
//					null,
//					"[npc.Name] pulls off [npc.her] stockings.",
//					"[npc.Name] pulls off your stockings.",
//					null, null, null);
//		}
//	};
	
//	public static AbstractClothingType MAID_HEELS = new AbstractClothingType(800,
//			"a pair of",
//			true,
//			"Maid's high heels",
//			"Maid's high heels",
//			"A pair of Maid's high heels, they are made of coloured leather with a small amount of white lace decoration.",
//			0,
//			Femininity.FEMININE,
//			InventorySlot.FOOT,
//			Rarity.EPIC,
//			SetBonus.getSetBonusFromId("innoxia_maid"),
//			"clothing/maidHeels",
//			Util.newArrayListOfValues(
//					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.BOOST, 0)),
//			Util.newArrayListOfValues(
//					new BlockedParts(
//							DisplacementType.REMOVE_OR_EQUIP,
//							Util.newArrayListOfValues(ClothingAccess.FEET),
//							Util.newArrayListOfValues(CoverableArea.FEET),
//							Util.newArrayListOfValues(ClothingAccess.FEET),
//							null)),
//			null,
//			ColourListPresets.MAID,
//			ColourListPresets.ALL,
//			ColourListPresets.JUST_WHITE,
//			ColourListPresets.ALL,
//			ColourListPresets.JUST_STEEL,
//			ColourListPresets.ALL_METAL,
//			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
//		
//		@Override
//		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You slip on the heels and buckle up the straps.",
//					"You pull the heels onto [npc.namePos] [npc.feet] and buckle up the straps.",
//					null,
//					"[npc.Name] pulls on the heels and buckles up the straps.",
//					"[npc.Name] pulls the heels onto your [pc.feet] and buckles up the straps.",
//					null, null, null);
//		}
//
//		@Override
//		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You unbuckle your heels and slip them off.",
//					"You unbuckle [npc.namePos] heels and pull them off.",
//					null,
//					"[npc.Name] unbuckles [npc.her] heels and slips them off.",
//					"[npc.Name] unbuckles your heels and pulls them off.",
//					null, null, null);
//		}
//	};
	
//	public static AbstractClothingType MAID_SLEEVES = new AbstractClothingType(350,
//			"a pair of",
//			true,
//			"Maid's sleeves",
//			"Maid's sleeves",
//			"A pair of Maid's sleeves that end just past the elbow. They are made of soft coloured fabric with white lace trimmings.",
//			0,
//			Femininity.FEMININE,
//			InventorySlot.HAND,
//			Rarity.EPIC,
//			SetBonus.getSetBonusFromId("innoxia_maid"),
//			"clothing/maidGloves",
//			Util.newArrayListOfValues(
//					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.BOOST, 0)),
//			Util.newArrayListOfValues(
//					new BlockedParts(
//							DisplacementType.REMOVE_OR_EQUIP,
//							Util.newArrayListOfValues(ClothingAccess.WRISTS),
//							null, null, null)),
//			null,
//			ColourListPresets.MAID,
//			ColourListPresets.ALL,
//			ColourListPresets.JUST_WHITE,
//			ColourListPresets.ALL,
//			null,
//			null,
//			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
//
//		@Override
//		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You pull on the sleeves.",
//					"You pull the sleeves onto [npc.namePos] [npc.arms].",
//					null,
//					"[npc.Name] pulls on the sleeves.",
//					"[npc.Name] pulls the sleeves onto your [pc.arms].",
//					null, null, null);
//		}
//
//		@Override
//		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You pull off your sleeves.",
//					"You pull off [npc.namePos] sleeves.",
//					null,
//					"[npc.Name] pulls [npc.her] sleeves off.",
//					"[npc.Name] pulls your sleeves off.",
//					null, null, null);
//		}
//	};

	// Enforcer:
	
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
							Util.newArrayListOfValues(
									CoverableArea.BACK,
									CoverableArea.ARMPITS),
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
		@Override
		public String getAuthorDescription() {
			return "A tag sewn to the inside of the dress reads 'Made by Blue999'";
		}
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
			public String getAuthorDescription() {
				return "A tag sewn to the inside of the headband reads 'Made by Blue999'";
			}
			
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
									CoverableArea.BACK,
									CoverableArea.ARMPITS),
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
									CoverableArea.BACK,
									CoverableArea.ARMPITS),
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
									CoverableArea.BACK,
									CoverableArea.ARMPITS),
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
			ColourListPresets.JUST_RED_DARK,
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
	private static Map<AbstractSetBonus, List<AbstractClothingType>> setClothing;
	
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
		return getClothingTypeFromId(id, null);
	}
	
	public static AbstractClothingType getClothingTypeFromId(String id, String slotHint) {
//		System.out.print("ID: "+id);
		
		if(oldIdConversionMap.containsKey(id)) {
			id = oldIdConversionMap.get(id);
		}
		
		Map<String, AbstractClothingType> choiceMap = idToClothingMap;
		if (slotHint!=null && !slotHint.isEmpty()) {
			try {
				InventorySlot slot = InventorySlot.valueOf(slotHint);
				
				// slotHint is present and valid, so filter the clothing map by items that can be equipped to that slot:
				choiceMap = idToClothingMap.entrySet().parallelStream()
						.filter(e -> e.getValue().getEquipSlots().contains(slot))
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			} catch (Exception ex) {
				String validSlots = InventorySlot.getClothingSlots().stream()
						.map(InventorySlot::toString).collect(Collectors.joining(", "));
				System.err.println("Warning: getClothingTypeFromId() invalid slot hint: "
						+ slotHint + ". Valid slots are: " + validSlots);
			}
		}
		
		id = Util.getClosestStringMatch(id, choiceMap.keySet());
		
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
		oldIdConversionMap.put("BDSM_BALLGAG", "innoxia_bdsm_ballgag");
		oldIdConversionMap.put("BDSM_RINGGAG", "innoxia_bdsm_ringgag");
		oldIdConversionMap.put("BDSM_SPREADER_BAR", "innoxia_bdsm_spreaderbar");
		oldIdConversionMap.put("BDSM_CHOKER", "innoxia_bdsm_choker");
		oldIdConversionMap.put("BDSM_WRIST_RESTRAINTS", "innoxia_bdsm_wrist_restraints");
		oldIdConversionMap.put("BDSM_CHASTITY_BELT", "innoxia_bdsm_chastity_belt");
		oldIdConversionMap.put("BDSM_CHASTITY_BELT_FULL", "innoxia_bdsm_chastity_belt_full");
		oldIdConversionMap.put("BDSM_KARADA", "innoxia_bdsm_karada");
		
		oldIdConversionMap.put("WITCH_HAT", "innoxia_witch_witch_hat");
		oldIdConversionMap.put("WITCH_DRESS", "innoxia_witch_witch_dress");
		oldIdConversionMap.put("WITCH_BOOTS", "innoxia_witch_witch_boots");
		oldIdConversionMap.put("WITCH_BOOTS_THIGH_HIGH", "innoxia_witch_witch_boots_thigh_high");

		oldIdConversionMap.put("EYES_SAFETY_GOGGLES", "innoxia_scientist_safety_goggles");
		oldIdConversionMap.put("SCIENTIST_EYES_SAFETY_GOGGLES", "innoxia_scientist_safety_goggles");
		oldIdConversionMap.put("SCIENTIST_TORSO_OVER_LAB_COAT", "innoxia_scientist_lab_coat");

		oldIdConversionMap.put("AMBERS_BITCH_CHOKER", "innoxia_neck_ambers_bitch_collar");

		oldIdConversionMap.put("KIMONO_HAIR_KANZASHI", "innoxia_japanese_kanzashi");
		oldIdConversionMap.put("KIMONO_DRESS", "innoxia_japanese_kimono");
		oldIdConversionMap.put("KIMONO_GETA", "innoxia_japanese_geta");
		oldIdConversionMap.put("KIMONO_MENS_KIMONO", "innoxia_japanese_mens_kimono");
		oldIdConversionMap.put("KIMONO_HAORI", "innoxia_japanese_haori");
		oldIdConversionMap.put("KIMONO_MENS_GETA", "innoxia_japanese_mens_geta");
		
		oldIdConversionMap.put("MAID_HEADPIECE", "innoxia_maid_headpiece");
		oldIdConversionMap.put("MAID_DRESS", "innoxia_maid_dress");
		oldIdConversionMap.put("MAID_STOCKINGS", "innoxia_maid_stockings");
		oldIdConversionMap.put("MAID_HEELS", "innoxia_maid_heels");
		oldIdConversionMap.put("MAID_SLEEVES", "innoxia_maid_sleeves");
		
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

		oldIdConversionMap.put("MOUTH_BANDANA", "innoxia_mouth_bandana");
		
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
		oldIdConversionMap.put("CHEST_BIKINI", "innoxia_chest_bikini");
		oldIdConversionMap.put("CHEST_CHEMISE", "innoxia_chest_chemise");
		oldIdConversionMap.put("CHEST_CROPTOP_BRA", "innoxia_chest_croptop_bra");
		oldIdConversionMap.put("CHEST_FULLCUP_BRA", "innoxia_chest_fullcup_bra");
		oldIdConversionMap.put("CHEST_PLUNGE_BRA", "innoxia_chest_plunge_bra");
		oldIdConversionMap.put("CHEST_NURSING_BRA", "innoxia_chest_nursing_bra");
		oldIdConversionMap.put("CHEST_OPEN_CUP_BRA", "innoxia_chest_open_cup_bra");
		oldIdConversionMap.put("CHEST_SARASHI", "innoxia_chest_sarashi");
		oldIdConversionMap.put("CHEST_SPORTS_BRA", "innoxia_chest_sports_bra");
		oldIdConversionMap.put("CHEST_STRIPED_BRA", "innoxia_chest_striped_bra");
		oldIdConversionMap.put("CHEST_SWIMSUIT", "innoxia_chest_swimsuit");
		oldIdConversionMap.put("CHEST_TUBE_TOP", "innoxia_chest_tube_top");

		oldIdConversionMap.put("NIPPLE_TAPE_CROSSES", "innoxia_nipple_tape_crosses");
		
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

		oldIdConversionMap.put("GROIN_LACY_PANTIES", "innoxia_groin_lacy_panties");
		oldIdConversionMap.put("GROIN_PANTIES", "innoxia_groin_panties");
		oldIdConversionMap.put("GROIN_SHIMAPAN", "innoxia_groin_shimapan");
		oldIdConversionMap.put("GROIN_VSTRING", "innoxia_groin_vstring");
		oldIdConversionMap.put("GROIN_THONG", "innoxia_groin_thong");
		oldIdConversionMap.put("GROIN_BIKINI", "innoxia_groin_bikini");
		oldIdConversionMap.put("GROIN_BOYSHORTS", "innoxia_groin_boyshorts");
		oldIdConversionMap.put("GROIN_BRIEFS", "innoxia_groin_briefs");
		oldIdConversionMap.put("GROIN_BOXERS", "innoxia_groin_boxers");
		oldIdConversionMap.put("GROIN_JOCKSTRAP", "innoxia_groin_jockstrap");
		oldIdConversionMap.put("GROIN_BACKLESS_PANTIES", "innoxia_groin_backless_panties");
		oldIdConversionMap.put("GROIN_CROTCHLESS_PANTIES", "innoxia_groin_crotchless_panties");
		oldIdConversionMap.put("GROIN_CROTCHLESS_THONG", "innoxia_groin_crotchless_thong");
		oldIdConversionMap.put("GROIN_CROTCHLESS_BRIEFS", "innoxia_groin_crotchless_briefs");
		
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
		oldIdConversionMap.put("TORSO_OXFORD_SHIRT", "innoxia_torso_long_sleeved_shirt");
		oldIdConversionMap.put("TORSO_SHORT_SLEEVE_SHIRT", "innoxia_torso_short_sleeved_shirt");
		oldIdConversionMap.put("TORSO_BLOUSE", "innoxia_torso_blouse");
		
		oldIdConversionMap.put("STOMACH_LOWBACK_BODY", "innoxia_stomach_lowback_body");
		oldIdConversionMap.put("STOMACH_UNDERBUST_CORSET", "innoxia_stomach_underbust_corset");
		oldIdConversionMap.put("STOMACH_OVERBUST_CORSET", "innoxia_stomach_overbust_corset");
		oldIdConversionMap.put("STOMACH_SARASHI", "innoxia_stomach_sarashi");
		
		oldIdConversionMap.put("TORSO_OVER_HOODIE", "innoxia_torsoOver_hoodie");
		oldIdConversionMap.put("TORSO_OVER_OPEN_CARDIGAN", "innoxia_torsoOver_open_front_cardigan");
		oldIdConversionMap.put("TORSO_OVER_BLAZER", "innoxia_torsoOver_blazer");
		oldIdConversionMap.put("TORSO_OVER_COAT_DRESS", "innoxia_torsoOver_dress_coat");
		oldIdConversionMap.put("TORSO_OVER_CLOAK", "innoxia_torsoOver_hooded_cloak");
		oldIdConversionMap.put("TORSO_RIBBED_SWEATER", "innoxia_torsoOver_ribbed_jumper");
		oldIdConversionMap.put("TORSO_OVER_CHRISTMAS_SWEATER", "innoxia_torsoOver_christmas_jumper");
		oldIdConversionMap.put("TORSO_KEYHOLE_SWEATER", "innoxia_torsoOver_keyhole_jumper");
		oldIdConversionMap.put("TORSO_OVER_SUIT_JACKET", "innoxia_torsoOver_suit_jacket");
		oldIdConversionMap.put("TORSO_OVER_WOMENS_LEATHER_JACKET", "innoxia_torsoOver_womens_leather_jacket");
		
		oldIdConversionMap.put("SOCK_SOCKS", "innoxia_sock_socks");
		oldIdConversionMap.put("SOCK_TRAINER_SOCKS", "innoxia_sock_trainer_socks");
		oldIdConversionMap.put("SOCK_KNEEHIGH_SOCKS", "innoxia_sock_kneehigh_socks");
		oldIdConversionMap.put("SOCK_STOCKINGS", "innoxia_sock_stockings");
		oldIdConversionMap.put("SOCK_THIGHHIGH_SOCKS", "innoxia_sock_thighhigh_socks");
		oldIdConversionMap.put("SOCK_THIGHHIGH_SOCKS_STRIPED", "innoxia_sock_thighhigh_socks_striped");
		oldIdConversionMap.put("SOCK_TIGHTS", "innoxia_sock_pantyhose");
		oldIdConversionMap.put("SOCK_FISHNET_STOCKINGS", "innoxia_sock_fishnets");
		oldIdConversionMap.put("SOCK_TOELESS_STRIPED_STOCKINGS", "innoxia_sock_toeless_striped_stockings");

		oldIdConversionMap.put("innoxia_insertableVibrator_insertable_vibrator", "innoxia_vagina_insertable_dildo");

		oldIdConversionMap.put("dsg_eep_servequipset_enfdjacket_pc", "dsg_eep_servequipset_enfdjacket");
		
		
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
		moddedClothingList = new ArrayList<>();
		

		// Modded clothing types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/items/clothing");
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try{
					String id = innerEntry.getKey();
					AbstractClothingType ct = new AbstractClothingType(innerEntry.getValue(), entry.getKey()) {};
					moddedClothingList.add(ct);
					clothingToIdMap.put(ct, id);
					idToClothingMap.put(id, ct);

					if(ct.getRarity()==Rarity.COMMON && !ct.getDefaultItemTags().contains(ItemTag.NO_RANDOM_SPAWN)) {
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
					System.err.println("Loading modded clothing failed at 'ClothingType'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					System.err.println(ex);
				}
			}
		}
		
		allClothing.addAll(moddedClothingList);
		
		
		// External res clothing types:

		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/clothing");
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					String id = innerEntry.getKey();
					AbstractClothingType ct = new AbstractClothingType(innerEntry.getValue(), entry.getKey()) {};
					allClothing.add(ct);
					clothingToIdMap.put(ct, id);
					idToClothingMap.put(id, ct);

					if(ct.getRarity()==Rarity.COMMON && !ct.getDefaultItemTags().contains(ItemTag.NO_RANDOM_SPAWN)) {
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
					System.err.println("Loading clothing failed at 'ClothingType'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
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
					
					if(ct.isDefaultSlotCondom()) {
						continue;
					}
					
					if(ct.getRarity()==Rarity.COMMON && !ct.getDefaultItemTags().contains(ItemTag.NO_RANDOM_SPAWN)) {
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

		setClothing = new HashMap<>();
		for(AbstractClothingType ct : allClothing) {
			if(ct.getClothingSet()!=null) {
				setClothing.putIfAbsent(ct.getClothingSet(), new ArrayList<>());
				setClothing.get(ct.getClothingSet()).add(ct);
			}
		}
		
//  	System.out.println(allClothing.size());
		
		//TODO shouldn't this be handled in outfit files?
		suitableFeminineClothing.put(Occupation.NPC_PROSTITUTE,
				Util.newArrayListOfValues(
						ClothingType.getClothingTypeFromId("innoxia_ankle_anklet"),
						ClothingType.getClothingTypeFromId("innoxia_chest_lacy_plunge_bra"),
						
						ClothingType.getClothingTypeFromId("innoxia_chest_open_cup_bra"),
						ClothingType.getClothingTypeFromId("innoxia_chest_plunge_bra"),
						ClothingType.getClothingTypeFromId("innoxia_eye_aviators"),
						ClothingType.getClothingTypeFromId("innoxia_finger_ring"),
						ClothingType.getClothingTypeFromId("innoxia_foot_chelsea_boots"),
						ClothingType.getClothingTypeFromId("innoxia_foot_ankle_boots"),
						ClothingType.getClothingTypeFromId("innoxia_foot_heels"),
						ClothingType.getClothingTypeFromId("innoxia_foot_thigh_high_boots"),
						ClothingType.getClothingTypeFromId("innoxia_foot_stiletto_heels"),
						ClothingType.getClothingTypeFromId("innoxia_groin_backless_panties"),
						ClothingType.getClothingTypeFromId("innoxia_groin_crotchless_panties"),
						ClothingType.getClothingTypeFromId("innoxia_groin_crotchless_thong"),
						ClothingType.getClothingTypeFromId("innoxia_groin_lacy_panties"),
						ClothingType.getClothingTypeFromId("innoxia_groin_thong"),
						ClothingType.getClothingTypeFromId("innoxia_groin_vstring"),
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
						ClothingType.getClothingTypeFromId("innoxia_nipple_tape_crosses"),
						ClothingType.getClothingTypeFromId("innoxia_hand_fishnet_gloves"),
						ClothingType.getClothingTypeFromId("innoxia_sock_fishnets"),
						ClothingType.getClothingTypeFromId("innoxia_sock_pantyhose"),
						ClothingType.getClothingTypeFromId("innoxia_stomach_overbust_corset"),
						ClothingType.getClothingTypeFromId("innoxia_stomach_underbust_corset"),
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
	
	public static List<AbstractClothingType> getAllClothingInSet(AbstractSetBonus setBonus) {
		return setClothing.get(setBonus);
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
