package com.lilithsthrone.game.inventory.clothing;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.ItemEffect;
import com.lilithsthrone.game.inventory.item.ItemEffectType;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.ColourListPresets;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.84
 * @version 0.2.4
 * @author Innoxia
 */
public class ClothingType {
	
	private static List<InventorySlot> concealedPartialTorso = Util.newArrayListOfValues(
					new ListValue<InventorySlot>(InventorySlot.STOMACH),
					new ListValue<InventorySlot>(InventorySlot.CHEST),
					new ListValue<InventorySlot>(InventorySlot.NIPPLE),
					new ListValue<InventorySlot>(InventorySlot.PIERCING_NIPPLE),
					new ListValue<InventorySlot>(InventorySlot.PIERCING_STOMACH));

	private static List<InventorySlot> concealedPartialTorsoStomachVisible = Util.newArrayListOfValues(
					new ListValue<InventorySlot>(InventorySlot.CHEST),
					new ListValue<InventorySlot>(InventorySlot.NIPPLE),
					new ListValue<InventorySlot>(InventorySlot.PIERCING_NIPPLE));

	private static List<InventorySlot> concealedStomach = Util.newArrayListOfValues(
					new ListValue<InventorySlot>(InventorySlot.PIERCING_STOMACH));

	private static List<InventorySlot> concealedBreasts = Util.newArrayListOfValues(
					new ListValue<InventorySlot>(InventorySlot.NIPPLE),
					new ListValue<InventorySlot>(InventorySlot.PIERCING_NIPPLE));
	
	private static List<InventorySlot> concealedFullTorso = Util.newArrayListOfValues(
					new ListValue<InventorySlot>(InventorySlot.TORSO_UNDER),
					new ListValue<InventorySlot>(InventorySlot.STOMACH),
					new ListValue<InventorySlot>(InventorySlot.CHEST),
					new ListValue<InventorySlot>(InventorySlot.NIPPLE),
					new ListValue<InventorySlot>(InventorySlot.PIERCING_NIPPLE),
					new ListValue<InventorySlot>(InventorySlot.PIERCING_STOMACH));

	private static List<InventorySlot> concealedGenitals = Util.newArrayListOfValues(
					new ListValue<InventorySlot>(InventorySlot.VAGINA),
					new ListValue<InventorySlot>(InventorySlot.ANUS),
					new ListValue<InventorySlot>(InventorySlot.PENIS),
					new ListValue<InventorySlot>(InventorySlot.PIERCING_PENIS),
					new ListValue<InventorySlot>(InventorySlot.PIERCING_VAGINA));
	
	private static List<InventorySlot> concealedGroin = Util.newArrayListOfValues(
					new ListValue<InventorySlot>(InventorySlot.GROIN),
					new ListValue<InventorySlot>(InventorySlot.VAGINA),
					new ListValue<InventorySlot>(InventorySlot.ANUS),
					new ListValue<InventorySlot>(InventorySlot.PENIS),
					new ListValue<InventorySlot>(InventorySlot.PIERCING_PENIS),
					new ListValue<InventorySlot>(InventorySlot.PIERCING_VAGINA));
	
	private static List<InventorySlot> concealedDressFrontFull = Util.newArrayListOfValues(
			new ListValue<InventorySlot>(InventorySlot.STOMACH),
			new ListValue<InventorySlot>(InventorySlot.CHEST),
			new ListValue<InventorySlot>(InventorySlot.NIPPLE),
			new ListValue<InventorySlot>(InventorySlot.PIERCING_NIPPLE),
			new ListValue<InventorySlot>(InventorySlot.PIERCING_STOMACH),
			new ListValue<InventorySlot>(InventorySlot.GROIN),
			new ListValue<InventorySlot>(InventorySlot.VAGINA),
			new ListValue<InventorySlot>(InventorySlot.PENIS),
			new ListValue<InventorySlot>(InventorySlot.PIERCING_PENIS),
			new ListValue<InventorySlot>(InventorySlot.PIERCING_VAGINA));
	
	private static List<InventorySlot> concealedUnzipsGroin = Util.newArrayListOfValues(
			new ListValue<InventorySlot>(InventorySlot.GROIN),
			new ListValue<InventorySlot>(InventorySlot.PENIS),
			new ListValue<InventorySlot>(InventorySlot.PIERCING_PENIS));

	private static List<InventorySlot> concealedAnklesFromTrousers = Util.newArrayListOfValues(
			new ListValue<InventorySlot>(InventorySlot.ANKLE),
			new ListValue<InventorySlot>(InventorySlot.SOCK));
	
	private static String braEquipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
		return AbstractClothingType.getEquipDescriptions(clothingOwner, clothingRemover, rough,
				"You place the bra over your chest before fastening the straps at your back.",
				"You place the bra over [npc.name]'s chest before fastening the straps at [npc.her] back.",
				null,
				"[npc.Name] places the bra over [npc.her] chest before fastening the straps at [npc.her] back.",
				"[npc.Name] places the bra over your chest before fastening the straps at your back.",
				null, null, null);
	}

	private static String braUnequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
		return AbstractClothingType.getEquipDescriptions(clothingOwner, clothingRemover, rough,
				"You unclasp the bra and take it off.",
				"You unclasp [npc.name]'s bra and take it off.",
				null,
				"[npc.Name] unclasps [npc.her] bra and takes it off.",
				"[npc.Name] unclasps your bra and takes it off.",
				null, null, null);
	}
	
	private static String braDisplaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
		return AbstractClothingType.getEquipDescriptions(clothingOwner, clothingRemover, rough,
				"You slide your bra's straps off your shoulders before tugging it down to reveal your [pc.breasts+].",
				"You slide [npc.her] bra's straps off [npc.her] shoulders before tugging it down to reveal [npc.her] [npc.breasts+].",
				null,
				"[npc.Name] slides [npc.her] bra's straps off [npc.her] shoulders before tugging it down to reveal [npc.her] [npc.breasts+].",
				"[npc.Name] slides your bra's straps off your shoulders before tugging it down to reveal your [pc.breasts+].",
				null, null, null);
	}

	private static String braReplaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
		return AbstractClothingType.getEquipDescriptions(clothingOwner, clothingRemover, rough,
				"You pull your bra back up to cover your [pc.breasts].",
				"You slide [npc.her] bra back up to cover [npc.her] [npc.breasts].",
				null,
				"[npc.Name] pulls [npc.her] bra back up to cover [npc.her] [npc.breasts].",
				"[npc.Name] pulls your bra back up to cover your [pc.breasts].",
				null, null, null);
	}
	
	// Special:
	
	public static AbstractClothingType HEAD_CHEATERS_CIRCLET = new AbstractClothingType(1000,
			"a",
			false,
			"cheater's circlet",
			"cheater's circlets",
			"A thin band of metal that sits on top of your head. On the inside, an engraved message reads: 'FOR INTERNAL TESTING ONLY!'",
			1,
			null,
			InventorySlot.HEAD,
			Rarity.LEGENDARY,
			null,
			"head_circlet",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_DRAIN, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.INTELLIGENCE, TFPotency.MAJOR_DRAIN, 0))),
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null,
							null, null))),
			null,
			ColourListPresets.JUST_GOLD.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			null){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You place the circlet on your head.",
					"You place the circlet on [npc.name]'s head.",
					"You force the circlet onto [npc.name]'s head.",
					"[npc.Name] places the circlet on [npc.her] head.",
					"[npc.Name] places the circlet on your head.",
					"[npc.Name] forces the circlet onto your head.", null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your circlet.",
					"You take off [npc.name]'s circlet.",
					"You grab [npc.name]'s circlet and throw it to the floor.",
					"[npc.Name] takes off [npc.her] circlet.",
					"[npc.Name] takes off your circlet.",
					"[npc.Name] grabs your circlet and throws it to the floor.", null, null);
		}
	};
	
	// PIERCINGS:
	public static AbstractClothingType PIERCING_EAR_BASIC_RING = new AbstractClothingType(80,
			"a pair of",
			true,
			"earring",
			"earrings",
			"A pair of very basic earrings.",
			0,
			null,
			InventorySlot.PIERCING_EAR,
			Rarity.COMMON,
			null,
			"piercing_ear_ring",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null, null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_KATE))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You clip the earrings into place.",
					"You clip [npc.name]'s new earrings into place.",
					null,
					"[npc.Name] clips [npc.her] earrings into place.",
					"[npc.Name] clips your new earrings into place.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unclip your earrings.",
					"You unclip [npc.name]'s earrings.",
					null,
					"[npc.Name] unclips [npc.her] earrings.",
					"[npc.Name] unclips your earrings.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType PIERCING_EAR_HOOPS = new AbstractClothingType(100,
			"a pair of",
			true,
			"hoop earring",
			"hoop earrings",
			"A pair of basic hoop earrings. Each one is fashioned from a thin band of metal, which has been curled around to form a circle.",
			0,
			Femininity.FEMININE,
			InventorySlot.PIERCING_EAR,
			Rarity.COMMON,
			null,
			"piercing_ear_hoops",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null, null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			ColourListPresets.JUST_STEEL.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_KATE))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You clip the hoop earrings into place.",
					"You clip [npc.name]'s new hoop earrings into place.",
					null,
					"[npc.Name] clips [npc.her] hoop earrings into place.",
					"[npc.Name] clips your new hoop earrings into place.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unclip your hoop earrings.",
					"You unclip [npc.name]'s hoop earrings.",
					null,
					"[npc.Name] unclips [npc.her] hoop earrings.",
					"[npc.Name] unclips your hoop earrings.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType PIERCING_EAR_SNOW_FLAKES = new AbstractClothingType(150,
			"a pair of",
			true,
			"snowflake earring",
			"snowflake earrings",
			"A pair of snowflake earrings.",
			0,
			null,
			InventorySlot.PIERCING_EAR,
			Rarity.COMMON,
			null,
			"piercing_ear_snowflakes",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null, null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.REINDEER_GIFT))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You clip the earrings into place.",
					"You clip [npc.name]'s new earrings into place.",
					null,
					"[npc.Name] clips [npc.her] earrings into place.",
					"[npc.Name] clips your new earrings into place.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unclip your earrings.",
					"You unclip [npc.name]'s earrings.",
					null,
					"[npc.Name] unclips [npc.her] earrings.",
					"[npc.Name] unclips your earrings.",
					null, null, null);
		}
	};

	public static AbstractClothingType PIERCING_NOSE_BASIC_RING = new AbstractClothingType(50,

			"a",
			false,
			"nose ring",
			"nose rings",
			"A simple nose ring. The little disc found on one end rests inside the nostril, holding it in place, while the rest of the ring is on display.",
			0,
			null,
			InventorySlot.PIERCING_NOSE,
			Rarity.COMMON,
			null,
			"piercing_nose_ring",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null, null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_KATE))){
		

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide the nose ring into place.",
					"You slide [npc.name]'s new nose ring into place.",
					null,
					"[npc.Name] slides [npc.her] nose ring into place.",
					"[npc.Name] slides your new nose ring into place.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide the nose ring out.",
					"You slide [npc.name]'s nose ring out.",
					null,
					"[npc.Name] slides [npc.her] nose ring out.",
					"[npc.Name] slides your nose ring out.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType PIERCING_NOSE_SNOWFLAKE_STUD = new AbstractClothingType(50,
			"a",
			false,
			"snowflake nose stud",
			"snowflake nose studs",
			"A nose stud in the shape of a snowflake.",
			0,
			null,
			InventorySlot.PIERCING_NOSE,
			Rarity.COMMON,
			null,
			"piercing_nose_snowflake",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null, null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.REINDEER_GIFT))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide the nose stud into place.",
					"You slide [npc.name]'s new nose stud into place.",
					null,
					"[npc.Name] slides [npc.her] nose stud into place.",
					"[npc.Name] slides your new nose stud into place.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide the nose stud out.",
					"You slide [npc.name]'s nose stud out.",
					null,
					"[npc.Name] slides [npc.her] nose stud out.",
					"[npc.Name] slides your nose stud out.",
					null, null, null);
		}
	};
	

	public static AbstractClothingType PIERCING_LIP_RINGS = new AbstractClothingType(80,
			"a pair of",
			true,
			"lip ring",
			"lip rings",
			"A pair of thin, simple lip rings.",
			0,
			null,
			InventorySlot.PIERCING_LIP,
			Rarity.COMMON,
			null,
			"piercing_lip_double_ring",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null, null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_KATE))){
		

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide the lip rings into place.",
					"You slide [npc.name]'s new lip rings into place.",
					null,
					"[npc.Name] slides [npc.her] lip rings into place.",
					"[npc.Name] slides your new lip rings into place.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide the lip rings out.",
					"You slide [npc.name]'s lip rings out.",
					null,
					"[npc.Name] slides [npc.her] lip rings out.",
					"[npc.Name] slides your lip rings out.",
					null, null, null);
		}
	};

	public static AbstractClothingType PIERCING_TONGUE_BAR = new AbstractClothingType(60,
			"a",
			false,
			"tongue bar",
			"tongue bars",
			"A bar that can be slid into place through a tongue piercing.",
			0,
			null,
			InventorySlot.PIERCING_TONGUE,
			Rarity.COMMON,
			null,
			"piercing_tongue_bar",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null, null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_KATE))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide the tongue bar into place.",
					"You slide [npc.name]'s new tongue bar into place.",
					null,
					"[npc.Name] slides [npc.her] tongue bar into place.",
					"[npc.Name] slides your new tongue bar into place.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide the tongue bar out.",
					"You slide [npc.name]'s tongue bar out.",
					null,
					"[npc.Name] slides [npc.her] tongue bar out.",
					"[npc.Name] slides your tongue bar out.",
					null, null, null);
		}
	};

	public static AbstractClothingType PIERCING_NAVEL_GEM = new AbstractClothingType(400,
			"a",
			false,
			"navel gem barbell",
			"navel gem barbells",
			"A bar with a gemstone embedded on one end. It's designed to fit into a navel piercing.",
			0,
			Femininity.FEMININE,
			InventorySlot.PIERCING_STOMACH,
			Rarity.COMMON,
			null,
			"piercing_navel_basic",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null, null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_KATE))){
		

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide the navel barbell into place.",
					"You slide [npc.name]'s new navel barbell into place.",
					null,
					"[npc.Name] slides [npc.her] navel barbell into place.",
					"[npc.Name] slides your new navel barbell into place.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide the navel barbell out.",
					"You slide [npc.name]'s navel barbell out.",
					null,
					"[npc.Name] slides [npc.her] navel barbell out.",
					"[npc.Name] slides your navel barbell out.",
					null, null, null);
		}
	};

	public static AbstractClothingType PIERCING_NIPPLE_BARS = new AbstractClothingType(100,
			"a pair of",
			true,
			"nipple bar",
			"nipple bars",
			"A pair of bars that are designed to fit into nipple piercings.",
			0,
			null,
			InventorySlot.PIERCING_NIPPLE,
			Rarity.COMMON,
			null,
			"piercing_nipple_bars",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null, null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_KATE))){
		

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide the nipple bars into place.",
					"You slide [npc.name]'s new nipple bars into place.",
					null,
					"[npc.Name] slides [npc.her] nipple bars into place.",
					"[npc.Name] slides your new nipple bars into place.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide the nipple bars out.",
					"You slide [npc.name]'s nipple bars out.",
					null,
					"[npc.Name] slides [npc.her] nipple bars out.",
					"[npc.Name] slides your nipple bars out.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType PIERCING_VAGINA_BARBELL_RING = new AbstractClothingType(60,
			"a",
			false,
			"ringed barbell",
			"ringed barbells",
			"A barbell with a freely-moving hinged ring on one end. It's designed for a clitoral hood or Christina piercing.",
			0,
			null,
			InventorySlot.PIERCING_VAGINA,
			Rarity.COMMON,
			null,
			"piercing_vagina_barbell_ring",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							null,
							null,
							null, null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_KATE))){
		

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide the barbell into place.",
					"You slide [npc.name]'s new barbell into place.",
					null,
					"[npc.Name] slides [npc.her] barbell into place.",
					"[npc.Name] slides your new barbell into place.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide the barbell out.",
					"You slide [npc.name]'s barbell out.",
					null,
					"[npc.Name] slides [npc.her] barbell out.",
					"[npc.Name] slides your barbell out.",
					null, null, null);
		}
	};

	public static AbstractClothingType PIERCING_PENIS_RING = new AbstractClothingType(120,
			"a",
			false,
			"piercing ring",
			"piercing rings",
			"A ring that's designed to fit into piercings in male genitalia. A removable segment on one side allows the wearer to easily slide the ring into place.",
			0,
			null,
			InventorySlot.PIERCING_PENIS,
			Rarity.COMMON,
			null,
			"piercing_penis_ring",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null, null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_KATE))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pop out the removable segment and slide the piercing ring into place.",
					"You pop out the removable segment and slide [npc.name]'s new piercing ring into place.",
					null,
					"[npc.Name] pops out the removable segment and slides [npc.her] piercing ring into place.",
					"[npc.Name] pops out the removable segment and slides your new piercing ring into place.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pop out the removable segment and slide the piercing ring out.",
					"You pop out the removable segment and slide [npc.name]'s piercing ring out.",
					null,
					"[npc.Name] pops out the removable segment and slides [npc.her] piercing ring out.",
					"[npc.Name] pops out the removable segment and slides your piercing ring out.",
					null, null, null);
		}
	};
	
	// HEAD
	public static AbstractClothingType HEAD_CIRCLET = new AbstractClothingType(300,
			"a",
			false,
			"circlet",
			"circlets",
			"A thin band of metal that sits atop your head.",
			1,
			Femininity.FEMININE,
			InventorySlot.HEAD,
			Rarity.COMMON,
			null,
			"head_circlet",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.HEAD)), null, null, null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You place the circlet on your head.",
					"You place the circlet on [npc.name]'s head.",
					null,
					"[npc.Name] places the circlet on [npc.her] head.",
					"[npc.Name] places the circlet on your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your circlet.",
					"You take off [npc.name]'s circlet.",
					null,
					"[npc.Name] takes [npc.her] circlet off.",
					"[npc.Name] takes your circlet off.",
					null, null, null);
		}
	};

	public static AbstractClothingType HEAD_TIARA = new AbstractClothingType(800,
			"a",
			false,
			"tiara",
			"tiaras",
			"A delicate band of precious metal, formed into an ornamental crown, that sits on top of your head.",
			1,
			Femininity.FEMININE,
			InventorySlot.HEAD,
			Rarity.COMMON,
			null,
			"head_tiara",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.HEAD)), null, null, null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You place the tiara on your head.",
					"You place the tiara on [npc.name]'s head.",
					null,
					"[npc.Name] places the tiara on [npc.her] head.",
					"[npc.Name] places the tiara on your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your tiara.",
					"You take off [npc.name]'s tiara.",
					null,
					"[npc.Name] takes [npc.her] tiara off.",
					"[npc.Name] takes your tiara off.",
					null, null, null);
		}
	};

	public static AbstractClothingType HEAD_HEADBAND = new AbstractClothingType(50,
			"a",
			false,
			"headband",
			"headbands",
			"A plain headband, designed to keep your hair pushed back out of your face.",
			1,
			null,
			InventorySlot.HEAD,
			Rarity.COMMON,
			null,
			"head_headband",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.HEAD)), null, null, null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the headband.",
					"You push the headband onto [npc.name]'s head.",
					null,
					"[npc.Name] pulls the headband onto [npc.her] head.",
					"[npc.Name] pushes the headband onto your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your headband.",
					"You take off [npc.name]'s headband.",
					null,
					"[npc.Name] takes [npc.her] headband off.",
					"[npc.Name] takes your headband off.",
					null, null, null);
		}
	};

	public static AbstractClothingType HEAD_HEADBAND_BOW = new AbstractClothingType(60,
			"a",
			false,
			"bow headband",
			"bow headbands",
			"A headband with a cute bow attached to the top, designed to keep your hair pushed back out of your face.",
			1,
			Femininity.FEMININE,
			InventorySlot.HEAD,
			Rarity.COMMON,
			null,
			"head_headband_bow",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.HEAD)), null, null, null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null, 
			null, 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the headband.",
					"You push the headband onto [npc.name]'s head.",
					null,
					"[npc.Name] pulls the headband onto [npc.her] head.",
					"[npc.Name] pushes the headband onto your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your headband.",
					"You take off [npc.name]'s headband.",
					null,
					"[npc.Name] takes [npc.her] headband off.",
					"[npc.Name] takes your headband off.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType HEAD_SWEATBAND = new AbstractClothingType(50,
			"a",
			false,
			"headband",
			"headbands",
			"A plain sweatband, designed to be worn around the forehead both to keep the wearer's hair back as well as to absorb any sweat before it drips down into their eyes.",
			1,
			null,
			InventorySlot.HEAD,
			Rarity.COMMON,
			null,
			"head_sweatband",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.HEAD)), null, null, null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the sweatband.",
					"You push the sweatband onto [npc.name]'s head.",
					null,
					"[npc.Name] pulls the sweatband onto [npc.her] head.",
					"[npc.Name] pushes the sweatband onto your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your sweatband.",
					"You take off [npc.name]'s sweatband.",
					null,
					"[npc.Name] takes [npc.her] sweatband off.",
					"[npc.Name] takes your sweatband off.",
					null, null, null);
		}
	};

	public static AbstractClothingType HEAD_CAP = new AbstractClothingType(60,
			"a",
			false,
			"cap",
			"caps",
			"A soft cap with a stiff peak that projects from the front.",
			1,
			null,
			InventorySlot.HEAD,
			Rarity.COMMON,
			null,
			"head_cap",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.HAIR)),
							null,
									null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the cap.",
					"You push the cap onto [npc.name]'s head.",
					null,
					"[npc.Name] pulls the cap onto [npc.her] head.",
					"[npc.Name] pushes the cap onto your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your cap.",
					"You take off [npc.name]'s cap.",
					null,
					"[npc.Name] takes [npc.her] cap off.",
					"[npc.Name] takes your cap off.",
					null, null, null);
		}
	};

	public static AbstractClothingType HEAD_COWBOY_HAT = new AbstractClothingType(750,
			"a",
			false,
			"cowboy hat",
			"cowboy hats",
			"A high-crowned, wide-brimmed hat.",
			1,
			null,
			InventorySlot.HEAD,
			Rarity.COMMON,
			null,
			"head_cowboy_hat",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.HAIR)),
							null,
							null))),
			null,
			ColourListPresets.LEATHER.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the cowboy hat.",
					"You push the cowboy hat onto [npc.name]'s head.",
					null,
					"[npc.Name] pulls the cowboy hat onto [npc.her] head.",
					"[npc.Name] pushes the cowboy hat onto your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your cowboy hat.",
					"You take off [npc.name]'s cowboy hat.",
					null,
					"[npc.Name] takes [npc.her] cowboy hat off.",
					"[npc.Name] takes your cowboy hat off.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType HEAD_ANTLER_HEADBAND = new AbstractClothingType(60,
			"an",
			false,
			"antler headband",
			"antler headbands",
			"A thin band of metal that sits atop your head.",
			1,
			null,
			InventorySlot.HEAD,
			Rarity.COMMON,
			null,
			"head_antler_headband",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.HEAD)), null, null, null))),
			null,
			ColourListPresets.JUST_BROWN.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.REINDEER_GIFT))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the antler headband.",
					"You push the antler headband onto [npc.name]'s head.",
					null,
					"[npc.Name] pulls the antler headband onto [npc.her] head.",
					"[npc.Name] pushes the antler headband onto your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your antler headband.",
					"You take off [npc.name]'s antler headband.",
					null,
					"[npc.Name] takes [npc.her] antler headband off.",
					"[npc.Name] takes your antler headband off.",
					null, null, null);
		}
	};

	// EYES
	public static AbstractClothingType EYES_GLASSES = new AbstractClothingType(800,
			"a pair of",
			true,
			"glasses",
			"glasses",
			"A pair of thin-rimmed glasses.",
			0,
			null,
			InventorySlot.EYES,
			Rarity.COMMON,
			null,
			"eye_glasses",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.EYES)), null, null, null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You put on your glasses.",
					"You push the glasses onto [npc.name]'s face.",
					null,
					"[npc.Name] puts on [npc.her] glasses.",
					"[npc.Name] pushes the pair of glasses onto your face.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your glasses.",
					"You take off [npc.name]'s glasses.",
					"You grab [npc.name]'s glasses and roughly pull them off.", // Rude!
					"[npc.Name] takes [npc.her] glasses off.",
					"[npc.Name] takes your glasses off.",
					"[npc.Name] grabs your glasses and roughly pulls them off.", null, null);
		}
	};
	public static AbstractClothingType EYES_AVIATORS = new AbstractClothingType(1200,
			"a pair of",
			true,
			"aviators",
			"aviators",
			"A pair of aviator sunglasses.",
			0,
			null,
			InventorySlot.EYES,
			Rarity.COMMON,
			null,
			"eye_aviators",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.EYES)), null, null, null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
			@Override
			public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
						"You put on your aviators.",
						"You push the aviators onto [npc.name]'s face.",
						null,
						"[npc.Name] puts on [npc.her] aviators.",
						"[npc.Name] pushes the pair of aviators onto your face.",
						null, null, null);
			}

			@Override
			public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
						"You take off your aviators.",
						"You take off [npc.name]'s aviators.",
						"You grab [npc.name]'s aviators and roughly pull them off.", // Rude!
						"[npc.Name] takes [npc.her] aviators off.",
						"[npc.Name] takes your aviators off.",
						"[npc.Name] grabs your aviators and roughly pulls them off.", null, null);
			}
	};
	

	// MOUTH
	public static AbstractClothingType MOUTH_BANDANA = new AbstractClothingType(80,
			"a",
			false,
			"bandana",
			"bandanas",
			"A square piece of cloth, which can be folded and tied so as to cover the wearer's mouth.",
			1,
			null,
			InventorySlot.MOUTH,
			Rarity.COMMON,
			null,
			"mouth_bandana",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							null,
							null,
							null, null)),
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.PULLS_DOWN,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.MOUTH)),
							null,
							Util.newArrayListOfValues(
									new ListValue<InventorySlot>(InventorySlot.PIERCING_LIP),
									new ListValue<InventorySlot>(InventorySlot.PIERCING_TONGUE))))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))) {
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You position the bandana over your [pc.face], before reaching back and tying the ends together.",
					"You hold [npc.name]'s head still as you tie the bandana around [npc.her] [npc.face].",
					null,
					"[npc.Name] positions a bandana over [npc.her] [npc.face], before reaching back and tying the ends together.",
					"[npc.Name] holds your head still and ties a bandana around your [pc.face].",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You untie the ends of your bandana and take it off.",
					"You untie the ends of [npc.name]'s bandana and take it off.",
					null,
					"[npc.Name] unties the ends of [npc.her] bandana and takes it off.",
					"[npc.Name] unties the ends of your bandana and takes it off.",
					null, null, null);
		}
		
		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your bandana.",
					"You pull down [npc.name]'s bandana.",
					null,
					"[npc.Name] pulls down [npc.her] bandana.",
					"[npc.Name] pulls your bandana down.",
					null, null, null);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull your bandana back up.",
					"You pull [npc.name]'s bandana back up.",
					null,
					"[npc.Name] pulls [npc.her] bandana back up.",
					"[npc.Name] pulls your bandana back up.",
					null, null, null);
		}
	};

	// NECK
	// Choker, Collar

	public static AbstractClothingType NECK_HEART_NECKLACE = new AbstractClothingType(350,
			"a",
			false,
			"heart necklace",
			"heart necklaces",
			"A necklace with a little heart-shaped pendant.",
			0,
			Femininity.FEMININE,
			InventorySlot.NECK,
			Rarity.COMMON,
			null,
			"neck_heartNecklace",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null, null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You put on the necklace, clipping the chain together at the back of your neck.",
					"fasten the necklace around [npc.name]'s neck.",
					"You hold [npc.name]'s head still as you fasten the necklace around [npc.her] neck.",
					"[npc.Name] puts a necklace around [npc.her] neck, before reaching around to fasten the clip at the back.",
					"[npc.Name] clips a necklace around your neck.",
					"[npc.Name] holds your head still and fastens a necklace around your neck.", null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unclip your necklace and take it off.",
					"You unclip [npc.name]'s necklace and take it off.",
					"You grab [npc.name]'s necklace and yank it from [npc.her] neck.",
					"[npc.Name] unclips [npc.her] necklace and takes it off.",
					"[npc.Name] unclips your necklace and removes it.",
					"[npc.Name] grabs your necklace and yanks it from your neck.", null, null);
		}
	};
	
	public static AbstractClothingType NECK_SNOWFLAKE_NECKLACE = new AbstractClothingType(250,
			"a",
			false,
			"snowflake necklace",
			"snowflake necklaces",
			"A necklace with a little snowflake pendant.",
			0,
			null,
			InventorySlot.NECK,
			Rarity.COMMON,
			null,
			"neck_snowflake_necklace",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null, null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.REINDEER_GIFT))){
		

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You put on the necklace, clipping the chain together at the back of your neck.",
					"fasten the necklace around [npc.name]'s neck.",
					"You hold [npc.name]'s head still as you fasten the necklace around [npc.her] neck.",
					"[npc.Name] puts a necklace around [npc.her] neck, before reaching around to fasten the clip at the back.",
					"[npc.Name] clips a necklace around your neck.",
					"[npc.Name] holds your head still and fastens a necklace around your neck.", null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unclip your necklace and take it off.",
					"You unclip [npc.name]'s necklace and take it off.",
					"You grab [npc.name]'s necklace and yank it from [npc.her] neck.",
					"[npc.Name] unclips [npc.her] necklace and takes it off.",
					"[npc.Name] unclips your necklace and removes it.",
					"[npc.Name] grabs your necklace and yanks it from your neck.", null, null);
		}
	};

	public static AbstractClothingType NECK_ANKH_NECKLACE = new AbstractClothingType(250,
			"an",
			false,
			"ankh necklace",
			"ankh necklaces",
			"A necklace with a cross-shaped pendant, which has a loop instead of the top arm.",
			0,
			null,
			InventorySlot.NECK,
			Rarity.COMMON,
			null,
			"neck_ankhNecklace",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null, null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You put on the ankh necklace, before reaching back and clipping the chain together behind your neck.",
					"You place the ankh necklace around [npc.name]'s neck, before reaching back and clipping the chain together.",
					"You hold [npc.name]'s head still as you fasten the ankh necklace around [npc.her] neck.",
					"[npc.Name] puts on the ankh necklace, before reaching around and clipping the chain together behind [npc.her] neck.",
					"[npc.Name] places the ankh necklace around your neck, before reaching around and clipping the chain together.",
					"[npc.Name] holds your head still and fastens an ankh necklace around your neck.", null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unclip your ankh necklace and take it off.",
					"You unclip [npc.name]'s ankh necklace and take it off.",
					"You grab [npc.name]'s ankh necklace and yank it from [npc.her] neck.",
					"[npc.Name] unclips [npc.her] ankh necklace and takes it off.",
					"[npc.Name] unclips your ankh necklace and removes it.",
					"[npc.Name] grabs your ankh necklace and yanks it from your neck.", null, null);
		}
	};
	
	public static AbstractClothingType NECK_BELL_COLLAR  = new AbstractClothingType(450,
			"a",
			false,
			"bell collar",
			"bell collars",
			"A leather collar, with a little bell attached to the front.",
			0,
			null,
			InventorySlot.NECK,
			Rarity.COMMON,
			null,
			"neck_bell_collar",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null, null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You fasten the bell collar around your neck.",
					"You fasten the bell collar around [npc.name]'s neck.",
					null,
					"[npc.Name] fastens the bell collar around [npc.her] neck.",
					"[npc.Name] fastens the bell collar around your neck.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your bell collar.",
					"You take off [npc.name]'s bell collar.",
					null,
					"[npc.Name] takes [npc.her] bell collar off.",
					"[npc.Name] takes your bell collar off.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType NECK_SCARF = new AbstractClothingType(150,
			"a",
			false,
			"scarf",
			"scarfs",
			"A unisex scarf, made of a soft, woolly fabric.",
			1,
			null,
			InventorySlot.NECK,
			Rarity.COMMON,
			null,
			"neck_scarf",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null, null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You wrap the scarf around your neck.",
					"You wrap the scarf around [npc.name]'s neck.",
					null,
					"[npc.Name] wraps a scarf around [npc.her] neck.",
					"[npc.Name] wraps a scarf around your neck.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your scarf.",
					"You take off [npc.name]'s scarf.",
					null,
					"[npc.Name] takes [npc.her] scarf off.",
					"[npc.Name] takes your scarf off.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType NECK_TIE = new AbstractClothingType(100,
			"a",
			false,
			"tie",
			"tie",
			"A smart-looking tie. Although designed to be worn with a shirt, it could be worn with any outfit as a fashion statement.",
			1,
			null,
			InventorySlot.NECK,
			Rarity.COMMON,
			null,
			"neck_tie",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null, null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(
					ItemTag.SOLD_BY_NYAN))) {

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You fasten the tie around your neck.",
					"You fasten the tie around [npc.name]'s neck.",
					null,
					"[npc.Name] fastens the tie around [npc.her] neck.",
					"[npc.Name] fasten the tie around your neck.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your tie.",
					"You take off [npc.name]'s tie.",
					null,
					"[npc.Name] takes [npc.her] tie off.",
					"[npc.Name] takes your tie off.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType NECK_COLLAR_BOWTIE = new AbstractClothingType(150,
			"a",
			false,
			"collar bowtie",
			"collar bowties",
			"A shirt collar, with a little bowtie affixed to the front.",
			1,
			null,
			InventorySlot.NECK,
			Rarity.COMMON,
			null,
			"neck_collar_bowtie",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null, null))),
			null,
			ColourListPresets.JUST_BLACK.getPresetColourList(), ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_WHITE.getPresetColourList(), ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_BLACK.getPresetColourList(), ColourListPresets.ALL.getPresetColourList(), 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You fasten the collar bowtie around your neck.",
					"You fasten the collar bowtie around [npc.name]'s neck.",
					null,
					"[npc.Name] fastens the collar bowtie around [npc.her] neck.",
					"[npc.Name] fasten the collar bowtie around your neck.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your collar bowtie.",
					"You take off [npc.name]'s collar bowtie.",
					null,
					"[npc.Name] takes [npc.her] collar bowtie off.",
					"[npc.Name] takes your collar bowtie off.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType NECK_BREEDER_COLLAR = new AbstractClothingType(10000,
			"a",
			false,
			"breeder collar",
			"breeder collars",
			"A <span style='color:"+Colour.CLOTHING_PINK.toWebHexString()+"; text-shadow: 0px 0px 4px "+Colour.CLOTHING_PINK.getShades()[4]+";'>glowing pink</span> leather collar,"
					+ " with bold metal lettering attached to the front spelling out the word 'BREEDER'.",
			1,
			null,
			InventorySlot.NECK,
			Rarity.EPIC,
			null,
			"neck_breeder_collar",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.FERTILITY, TFPotency.MAJOR_BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.FERTILITY, TFPotency.MAJOR_BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.FERTILITY, TFPotency.MAJOR_BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.FERTILITY, TFPotency.MAJOR_BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.FERTILITY, TFPotency.MAJOR_BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ENSLAVEMENT, TFModifier.ARCANE_BOOST, TFPotency.MINOR_BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SEALING, TFModifier.ARCANE_BOOST, TFPotency.MINOR_BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_PREGNANCY, TFPotency.MAJOR_BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_IMPREGNATION, TFPotency.MAJOR_BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_BROODMOTHER, TFPotency.MAJOR_BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_SEEDER, TFPotency.MAJOR_BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, Wetness.SEVEN_DROOLING.getValue()))),
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null, null))),
			null,
			ColourListPresets.JUST_PINK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_STEEL.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			ColourListPresets.JUST_STEEL.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(), 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You can't help but let out a small, needy moan as you fasten the enchanted collar around your neck.",
					"A small, needy moan drifts out from [npc.name]'s mouth as you fasten the enchanted collar around [npc.her] neck.",
					null,
					"[npc.Name] can't help but let out a small, needy moan as [npc.she] fastens the collar around [npc.her] neck.",
					"You can't help but let out a small, needy moan as [npc.name] fastens the collar around your neck.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your collar.",
					"You take off [npc.name]'s collar.",
					null,
					"[npc.Name] takes [npc.her] collar off.",
					"[npc.Name] takes your collar off.",
					null, null, null);
		}
		
		@Override
		public int getEnchantmentLimit() {
			return 12;
		}
	};
	

	// TORSO

	public static AbstractClothingType TORSO_OXFORD_SHIRT = new AbstractClothingType(150,
			"a",
			false,
			"long-sleeved shirt",
			"long-sleeved shirts",
			"A men's long-sleeved shirt.",
			1,
			Femininity.MASCULINE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"torso_oxfordShirt",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BACK)),
							null, null)),
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.UNBUTTONS,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BREASTS),
									new ListValue<CoverableArea>(CoverableArea.NIPPLES),
									new ListValue<CoverableArea>(CoverableArea.STOMACH)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
							concealedPartialTorso))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You put your [pc.arms] through the shirt's sleeves and button it up.",
					"You push [npc.name]'s [npc.arms] through the shirt's sleeves and button it up.",
					null,
					"[npc.Name] pushes [npc.her] [npc.arms] through the shirt's sleeves and buttons it up.",
					"[npc.Name] pushes your [pc.arms] through the shirt's sleeves and buttons it up.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unbutton your shirt and pull it off.",
					"You unbutton [npc.name]'s shirt and pull it off.",
					null,
					"[npc.Name] unbuttons [npc.her] shirt and pulls it off.",
					"[npc.Name] unbuttons your shirt and pulls it off.",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unbutton your shirt, leaving it hanging open.",
					"You unbutton [npc.name]'s shirt.",
					"You roughly pull open [npc.name]'s shirt, almost tearing the buttons off in the process.",
					"[npc.Name] unbuttons [npc.her] shirt, leaving it hanging open.",
					"[npc.Name] unbuttons your shirt.",
					"[npc.Name] roughly pulls open your shirt, almost tearing the buttons right off", null, null);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You button up your shirt.",
					"You button up [npc.name]'s shirt.",
					"You roughly button up [npc.name]'s shirt.",
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
			1,
			null,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"torso_shortSleeveShirt",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BACK)),
							null, null)),
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.UNBUTTONS,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BREASTS),
									new ListValue<CoverableArea>(CoverableArea.NIPPLES),
									new ListValue<CoverableArea>(CoverableArea.STOMACH)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
							concealedPartialTorso))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You put your [pc.arms] through the shirt's sleeves and button it up.",
					"You push [npc.name]'s [npc.arms] through the shirt's sleeves and button it up.",
					null,
					"[npc.Name] pushes [npc.her] [npc.arms] through the shirt's sleeves and buttons it up.",
					"[npc.Name] pushes your [pc.arms] through the shirt's sleeves and buttons it up.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unbutton your shirt and pull it off.",
					"You unbuttons [npc.name]'s shirt and pull it off.",
					null,
					"[npc.Name] unbuttons [npc.her] shirt and pulls it off.",
					"[npc.Name] unbuttons your shirt and pulls it off.",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unbutton your shirt, leaving it hanging open.",
					"You unbutton [npc.name]'s shirt.",
					"You roughly pull open [npc.name]'s shirt, almost tearing the buttons off in the process.",
					"[npc.Name] unbuttons [npc.her] shirt, leaving it hanging open.",
					"[npc.Name] unbuttons your shirt.",
					"[npc.Name] roughly pulls open your shirt, almost tearing the buttons right off", null, null);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You button up your shirt.",
					"You button up [npc.name]'s shirt.",
					"You roughly button up [npc.name]'s shirt.",
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
			1,
			null,
			InventorySlot.TORSO_OVER,
			Rarity.COMMON,
			null,
			"torso_over_hoodie",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BACK)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)), null
							)),
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BREASTS),
									new ListValue<CoverableArea>(CoverableArea.NIPPLES),
									new ListValue<CoverableArea>(CoverableArea.STOMACH)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
							concealedFullTorso))),
			null,
			ColourListPresets.ALL.getPresetColourList(), null,
			null, null,
			null, null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the hoodie.",
					"You guide [npc.name]'s [npc.arms] through the hoodie's sleeves as you pull it on [npc.herHim].",
					"You hold [npc.name] still as you roughly force the hoodie down over [npc.her] head, before pushing [npc.her] [npc.arms] through the sleeves.",
					"[npc.Name] pulls on the hoodie.",
					"[npc.Name] guides your [pc.arms] through the hoodie's sleeves as [npc.she] pulls it on you.",
					"[npc.Name] holds you still as [npc.she] roughly forces the hoodie down over your head, before pushing your [pc.arms] through the sleeves", null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your hoodie.",
					"You pull off [npc.name]'s hoodie.",
					"You grab hold of [npc.name]'s hoodie and roughly pull it off over [npc.her] head.",
					"[npc.Name] takes [npc.her] hoodie off.",
					"[npc.Name] pulls your hoodie off.",
					"[npc.Name] grabs hold of your hoodie and roughly pulls it off over your head", null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull your hoodie up to just below your chin, letting the elastic rim hold it in place.",
					"You pull up [npc.name]'s hoodie.",
					"You roughly yank up [npc.name]'s hoodie.",
					"[npc.Name] pulls [npc.her] hoodie up to just below [npc.her] chin, letting the elastic rim hold it in place.",
					"[npc.Name] pulls up your hoodie.",
					"[npc.Name] roughly yanks up your hoodie.", null, null);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull your hoodie back down, covering your torso.",
					"You pull down [npc.name]'s hoodie.",
					"You roughly pull down [npc.name]'s hoodie.",
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
			1,
			Femininity.FEMININE,
			InventorySlot.TORSO_OVER,
			Rarity.COMMON,
			null,
			"torso_open_cardigan",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.BACK)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)),
									null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the cardigan.",
					"You guide [npc.name]'s [npc.arms] through the cardigan's sleeves as you pull it on [npc.herHim].",
					null,
					"[npc.Name] pulls on the cardigan.",
					"[npc.Name] guides your [pc.arms] through the cardigan's sleeves as [npc.she] pulls it on you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your cardigan.",
					"You pull off [npc.name]'s cardigan.",
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
			1,
			null,
			InventorySlot.TORSO_OVER,
			Rarity.COMMON,
			null,
			"torso_over_blazer",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BACK)),
							Util.newArrayListOfValues(
							new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)), null)),
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.UNBUTTONS,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BREASTS),
									new ListValue<CoverableArea>(CoverableArea.NIPPLES),
									new ListValue<CoverableArea>(CoverableArea.STOMACH)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
							concealedPartialTorso))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the blazer.",
					"You guide [npc.name]'s [npc.arms] through the blazer's sleeves as you pull it on [npc.herHim].",
					null,
					"[npc.Name] pulls on the blazer.",
					"[npc.Name] guides your [pc.arms] through the blazer's sleeves as [npc.she] pulls it on you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your blazer.",
					"You pull off [npc.name]'s blazer.",
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
			3,
			Femininity.FEMININE,
			InventorySlot.TORSO_OVER,
			Rarity.COMMON,
			null,
			"torso_over_womens_leather_jacket",

			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BACK)),
							Util.newArrayListOfValues(
							new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)), null)),
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.UNZIPS,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BREASTS),
									new ListValue<CoverableArea>(CoverableArea.NIPPLES),
									new ListValue<CoverableArea>(CoverableArea.STOMACH)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
							concealedFullTorso))),
			null,
			ColourListPresets.LEATHER.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the leather jacket.",
					"You guide [npc.name]'s [npc.arms] through the leather jacket's sleeves as you pull it on [npc.herHim].",
					null,
					"[npc.Name] pulls on the leather jacket.",
					"[npc.Name] guides your [pc.arms] through the leather jacket's sleeves as [npc.she] pulls it on you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your leather jacket.",
					"You pull off [npc.name]'s leather jacket.",
					null,
					"[npc.Name] takes [npc.her] leather jacket off.",
					"[npc.Name] pulls your leather jacket off.",
					null, null, null);
		}
	};

	public static AbstractClothingType TORSO_OVER_SUIT_JACKET = new AbstractClothingType(800,
			"a",
			false,
			"suit jacket",
			"suit jackets",
			"A smart suit jacket, which looks best when worn with a shirt and tie.",
			1,
			Femininity.MASCULINE,
			InventorySlot.TORSO_OVER,
			Rarity.COMMON,
			null,
			"torso_over_suit_jacket",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.BACK)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)), null
									)),
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.UNBUTTONS,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BREASTS),
									new ListValue<CoverableArea>(CoverableArea.NIPPLES),
									new ListValue<CoverableArea>(CoverableArea.STOMACH)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
							concealedPartialTorso))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the suit jacket.",
					"You guide [npc.name]'s [npc.arms] through the suit jacket's sleeves as you pull it on [npc.herHim].",
					null,
					"[npc.Name] pulls on the suit jacket.",
					"[npc.Name] guides your [pc.arms] through the suit jacket's sleeves as [npc.she] pulls it on you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your suit jacket.",
					"You pull off [npc.name]'s suit jacket.",
					null,
					"[npc.Name] takes [npc.her] suit jacket off.",
					"[npc.Name] pulls your suit jacket off.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType TORSO_OVER_COAT_DRESS = new AbstractClothingType(2500,
			"a",
			false,
			"dress coat",
			"dress coats",
			"A type of dress coat, this garment does absolutely nothing to conceal the wearer's groin or rear end when worn.",
			1,
			Femininity.FEMININE,
			InventorySlot.TORSO_OVER,
			Rarity.COMMON,
			null,
			"torso_over_dress_coat",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.BACK)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)), null
									)),
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.UNBUTTONS,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BREASTS),
									new ListValue<CoverableArea>(CoverableArea.NIPPLES),
									new ListValue<CoverableArea>(CoverableArea.STOMACH)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
							concealedPartialTorso))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the dress coat.",
					"You guide [npc.name]'s [npc.arms] through the dress coat's sleeves as you pull it on [npc.herHim].",
					null,
					"[npc.Name] pulls on the dress coat.",
					"[npc.Name] guides your [pc.arms] through the dress coat's sleeves as [npc.she] pulls it on you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your dress coat.",
					"You pull off [npc.name]'s dress coat.",
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
			1,
			null,
			InventorySlot.TORSO_OVER,
			Rarity.COMMON,
			null,
			"torso_over_cloak",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.BACK)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)), null
									)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_UP,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)), null)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.SHIFTS_ASIDE,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.BREASTS),
											new ListValue<CoverableArea>(CoverableArea.NIPPLES),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA),
											new ListValue<CoverableArea>(CoverableArea.STOMACH)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN),
											new ListValue<ClothingAccess>(ClothingAccess.CHEST),
											new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
									Util.newArrayListOfValues(
											new ListValue<InventorySlot>(InventorySlot.ANKLE),
											new ListValue<InventorySlot>(InventorySlot.ANUS),
											new ListValue<InventorySlot>(InventorySlot.CHEST),
											new ListValue<InventorySlot>(InventorySlot.FINGER),
											new ListValue<InventorySlot>(InventorySlot.FOOT),
											new ListValue<InventorySlot>(InventorySlot.GROIN),
											new ListValue<InventorySlot>(InventorySlot.HAIR),
											new ListValue<InventorySlot>(InventorySlot.HAND),
											new ListValue<InventorySlot>(InventorySlot.HEAD),
											new ListValue<InventorySlot>(InventorySlot.HIPS),
											new ListValue<InventorySlot>(InventorySlot.HORNS),
											new ListValue<InventorySlot>(InventorySlot.LEG),
											new ListValue<InventorySlot>(InventorySlot.MOUTH),
											new ListValue<InventorySlot>(InventorySlot.NECK),
											new ListValue<InventorySlot>(InventorySlot.NIPPLE),
											new ListValue<InventorySlot>(InventorySlot.PENIS),
											new ListValue<InventorySlot>(InventorySlot.PIERCING_EAR),
											new ListValue<InventorySlot>(InventorySlot.PIERCING_LIP),
											new ListValue<InventorySlot>(InventorySlot.PIERCING_NIPPLE),
											new ListValue<InventorySlot>(InventorySlot.PIERCING_NOSE),
											new ListValue<InventorySlot>(InventorySlot.PIERCING_PENIS),
											new ListValue<InventorySlot>(InventorySlot.PIERCING_STOMACH),
											new ListValue<InventorySlot>(InventorySlot.PIERCING_TONGUE),
											new ListValue<InventorySlot>(InventorySlot.PIERCING_VAGINA),
											new ListValue<InventorySlot>(InventorySlot.SOCK),
											new ListValue<InventorySlot>(InventorySlot.STOMACH),
											new ListValue<InventorySlot>(InventorySlot.TAIL),
											new ListValue<InventorySlot>(InventorySlot.TORSO_UNDER),
											new ListValue<InventorySlot>(InventorySlot.VAGINA),
											new ListValue<InventorySlot>(InventorySlot.WEAPON_MAIN),
											new ListValue<InventorySlot>(InventorySlot.WEAPON_OFFHAND),
											new ListValue<InventorySlot>(InventorySlot.WRIST))))),
			null,
			ColourListPresets.LEATHER.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the cloak.",
					"You pull the cloak onto [npc.name].",
					null,
					"[npc.Name] pulls on the cloak.",
					"[npc.Name] pulls the cloak onto you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your cloak.",
					"You pull off [npc.name]'s cloak.",
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
			1,
			null,
			InventorySlot.TORSO_OVER,
			Rarity.COMMON,
			null,
			"torso_ribbed_sweater",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)), null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BREASTS),
									new ListValue<CoverableArea>(CoverableArea.NIPPLES),
									new ListValue<CoverableArea>(CoverableArea.STOMACH),
									new ListValue<CoverableArea>(CoverableArea.BACK)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
							concealedFullTorso))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
	};

	public static AbstractClothingType TORSO_OVER_CHRISTMAS_SWEATER = new AbstractClothingType(100,
			"a",
			false,
			"festive sweater",
			"festive sweaters",
			"A festive sweater, made from some sort of woolly fabric and decorated with a series of incredibly tasteful and refined patterns.",
			1,
			null,
			InventorySlot.TORSO_OVER,
			Rarity.COMMON,
			null,
			"torso_over_christmas_sweater",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)), null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BREASTS),
									new ListValue<CoverableArea>(CoverableArea.NIPPLES),
									new ListValue<CoverableArea>(CoverableArea.STOMACH),
									new ListValue<CoverableArea>(CoverableArea.BACK)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
							concealedFullTorso))),

			null,
			ColourListPresets.NOT_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.REINDEER_GIFT))){
	};
	
	public static AbstractClothingType TORSO_KEYHOLE_SWEATER = new AbstractClothingType(350,
			"a",
			false,
			"keyhole sweater",
			"keyhole sweaters",
			"A feminine sweater, with a section at the front cut out to reveal the wearer's cleavage.",
			1,
			Femininity.FEMININE,
			InventorySlot.TORSO_OVER,
			Rarity.COMMON,
			null,
			"torso_keyhole_sweater",
			null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)), null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_DOWN,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST)), null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.NIPPLES),
									new ListValue<CoverableArea>(CoverableArea.STOMACH),
									new ListValue<CoverableArea>(CoverableArea.BACK)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
							concealedPartialTorso))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
	};
	
	public static AbstractClothingType TORSO_SLEEVELESS_TURTLENECK = new AbstractClothingType(350,
			"a",
			false,
			"sleeveless turtleneck",
			"sleeveless turtlenecks",
			"A feminine sleeveless sweater, with a high turtleneck.",
			1,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"torso_sleeveless_turtleneck",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null,
							null, null
							)),
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BREASTS),
									new ListValue<CoverableArea>(CoverableArea.NIPPLES),
									new ListValue<CoverableArea>(CoverableArea.STOMACH),
									new ListValue<CoverableArea>(CoverableArea.BACK)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
							concealedPartialTorso))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null, null, null, null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
	};
	
	public static AbstractClothingType TORSO_TSHIRT = new AbstractClothingType(80,
			"a",
			false,
			"T-shirt",
			"T-shirts",
			"A plain T-shirt that would look good on any figure.",
			1,
			null,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"torso_tshirt",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null,
							null, null
							)),
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BREASTS),
									new ListValue<CoverableArea>(CoverableArea.NIPPLES),
									new ListValue<CoverableArea>(CoverableArea.STOMACH),
									new ListValue<CoverableArea>(CoverableArea.BACK)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
							concealedPartialTorso))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the T-shirt.",
					"You guide [npc.name]'s [npc.arms] through the T-shirt's sleeves as you pull down over [npc.her] head.",
					null,
					"[npc.Name] pulls on the T-shirt.",
					"[npc.Name] guides your [pc.arms] through the T-shirt's sleeves as [npc.she] pulls it down over your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your T-shirt.",
					"You pull off [npc.name]'s T-shirt.",
					null,
					"[npc.Name] takes [npc.her] T-shirt off.",
					"[npc.Name] pulls your T-shirt off.",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull your t-shirt up to just below your chin, exposing your torso.",
					"You pull up [npc.name]'s t-shirt.",
					null,
					"[npc.Name] pulls [npc.her] t-shirt up to just below [npc.her] chin, exposing [npc.her] torso.",
					"[npc.Name] pulls up your t-shirt.",
					null, null, null);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull your t-shirt back down, covering your torso.",
					"You pull down [npc.name]'s t-shirt.",
					null,
					"[npc.Name] pulls [npc.her] t-shirt back down to cover [npc.her] torso.",
					"[npc.Name] pulls down your t-shirt.",
					null, null, null);
		}
	};
	public static AbstractClothingType TORSO_KEYHOLE_CROPTOP = new AbstractClothingType(120,
			"a",
			false,
			"keyhole crop top",
			"keyhole crop tops",
			"A small, sleeveless crop top with a stylish cutout that reveals some cleavage.",
			1,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"torso_keyhole_croptop",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null,
							null, null)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_UP,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.NIPPLES),
											new ListValue<CoverableArea>(CoverableArea.STOMACH),
											new ListValue<CoverableArea>(CoverableArea.BACK)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST),
											new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
									concealedPartialTorso))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the crop top.",
					"You guide [npc.name]'s [npc.arms] through the crop top's sleeves as you pull it down over [npc.her] head.",
					null,
					"[npc.Name] pulls on the crop top.",
					"[npc.Name] guides your [pc.arms] through the crop top's sleeves as [npc.she] pulls it down over your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off your crop top.",
					"You slide [npc.name]'s crop top up and over [npc.her] head.",
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
			1,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"torso_short_croptop",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null,
							null, null)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_UP,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.BREASTS),
											new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									concealedPartialTorsoStomachVisible))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the crop top.",
					"You guide [npc.name]'s [npc.arms] through the crop top's sleeves as you pull it down over [npc.her] head.",
					null,
					"[npc.Name] pulls on the crop top.",
					"[npc.Name] guides your [pc.arms] through the crop top's sleeves as [npc.she] pulls it down over your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off your crop top.",
					"You slide [npc.name]'s crop top up and over [npc.her] head.",
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
			1,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"torso_fishnet_top",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
											new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
									null,
									null, null)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_UP,
									null,
									null,
									Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.CHEST)), null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the fishnet top.",
					"You guide [npc.name]'s [npc.arms] through the fishnet top's sleeves as you pull it down over [npc.her] head.",
					null,
					"[npc.Name] pulls on the fishnet top.",
					"[npc.Name] guides your [pc.arms] through the fishnet top's sleeves as [npc.she] pulls it down over your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off your fishnet top.",
					"You slide [npc.name]'s fishnet top up and over [npc.her] head.",
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
			1,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"torso_blouse",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null,
							null, null)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_UP,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.BREASTS),
											new ListValue<CoverableArea>(CoverableArea.NIPPLES),
											new ListValue<CoverableArea>(CoverableArea.STOMACH),
											new ListValue<CoverableArea>(CoverableArea.BACK)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST),
											new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
									concealedPartialTorso))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the blouse.",
					"You guide [npc.name]'s [npc.arms] through the blouse's sleeves as you pull it down over [npc.her] head.",
					null,
					"[npc.Name] pulls on the blouse.",
					"[npc.Name] guides your [pc.arms] through the blouse's sleeves as [npc.she] pulls it down over your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off your blouse.",
					"You slide [npc.name]'s blouse up and over [npc.her] head.",
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
			1,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"torso_cami_straps",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null,
							null, null)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_UP,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.BREASTS),
											new ListValue<CoverableArea>(CoverableArea.NIPPLES),
											new ListValue<CoverableArea>(CoverableArea.STOMACH),
											new ListValue<CoverableArea>(CoverableArea.BACK)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									concealedPartialTorsoStomachVisible))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
	};

	public static AbstractClothingType TORSO_SKATER_DRESS = new AbstractClothingType(250,
			"a",
			false,
			"skater dress",
			"skater dresses",
			"A sleeveless skater dress, held up by a pair of thin straps that loop over the wearer's shoulders.",
			1,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"torso_skater_dress",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
											new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.BREASTS),
											new ListValue<CoverableArea>(CoverableArea.NIPPLES),
											new ListValue<CoverableArea>(CoverableArea.STOMACH),
											new ListValue<CoverableArea>(CoverableArea.BACK)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST),
											new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
									concealedPartialTorso)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_UP,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedGroin))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the skater dress, tidying the skirt down before moving the straps into a comfortable position on your shoulders.",
					"You pull the skater dress over [npc.name]'s head and down around [npc.her] torso, tidying the skirt before moving the straps to sit comfortably on [npc.her] shoulders.",
					null,
					"[npc.Name] pulls on the skater dress, tidying the skirt down before moving the straps into a comfortable position on [npc.her] shoulders.",
					"[npc.Name] pulls the skater dress over your head and down around your torso, tidying the skirt before moving the straps to sit comfortably on your shoulders.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull your skater dress up over your head and take it off.",
					"You pull [npc.name]'s skater dress up over [npc.her] head and take it off.",
					null,
					"[npc.Name] pulls [npc.her] skater dress up over [npc.her] head and takes it off.",
					"[npc.Name] pulls your skater dress up over your head and takes it off.",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull up the skirt of your skater dress.",
					"You pull up the skirt of [npc.name]'s skater dress.",
					null,
					"[npc.Name] pulls up the skirt of [npc.her] skater dress.",
					"[npc.Name] pulls up the skirt of your skater dress.",
					null, null, null);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull your skater dress back down into its proper position.",
					"You pull [npc.name]'s skater dress back down into its proper position.",
					null,
					"[npc.Name] pulls [npc.her] skater dress back down into its proper position.",
					"[npc.Name] your skater dress back down into its proper position.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType TORSO_CORSET_DRESS = new AbstractClothingType(1250,
			"a",
			false,
			"corset dress",
			"corset dresses",
			"An overbust corset, which is tied up and tightened by a series of strings running up the front."
					+ " A long skirt is attached to the bottom rim, turning it into a dress.",
			1,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"torso_corset_dress",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BREASTS),
									new ListValue<CoverableArea>(CoverableArea.NIPPLES),
									new ListValue<CoverableArea>(CoverableArea.STOMACH),
									new ListValue<CoverableArea>(CoverableArea.BACK)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
							concealedPartialTorso)),
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.ANUS),
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							concealedGroin))),
			Util.newArrayListOfValues(
					new ListValue<InventorySlot>(InventorySlot.STOMACH),
					new ListValue<InventorySlot>(InventorySlot.CHEST)),
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slip into the corset dress, before tightening the strings on the front.",
					"You guide [npc.name] into the corset dress, before tightening the strings on the front.",
					null,
					"[npc.Name] slips into the corset dress, before tightening the strings on the front.",
					"[npc.Name] guides you into the corset dress, before tightening the strings on the front.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You untie the corset dress's strings and take it off.",
					"You untie the strings on the front of [npc.name]'s corset dress, before taking it off.",
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
			1,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"torso_virgin_killer_sweater",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BREASTS),
									new ListValue<CoverableArea>(CoverableArea.NIPPLES),
									new ListValue<CoverableArea>(CoverableArea.STOMACH)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
							concealedPartialTorso)),
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.ANUS),
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							concealedGroin))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
	};

	public static AbstractClothingType TORSO_SLIP_DRESS = new AbstractClothingType(800,
			"a",
			false,
			"slip dress",
			"slip dresses",
			"A long, silky, sleeveless dress.",
			1,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"torso_slip_dress",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
											new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.BREASTS),
											new ListValue<CoverableArea>(CoverableArea.NIPPLES),
											new ListValue<CoverableArea>(CoverableArea.STOMACH),
											new ListValue<CoverableArea>(CoverableArea.BACK)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST),
											new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
									concealedPartialTorso)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_UP,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedGroin))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the slip dress and pull it up around your torso. Once in place, you reach back and zip yourself up.",
					"You guide the slip dress up around [npc.name]'s [npc.legs], before pulling it up around [npc.her] torso. Once it's in place, you zip [npc.herHim] up at the back.",
					null,
					"[npc.Name] steps into the slip dress, before pulling it up around [npc.her] torso. Once in place, [npc.she] reaches back to zip [npc.herself] up.",
					"[npc.Name] guides the slip dress up around your [pc.legs], before pulling it up around your torso. One it's in place, [npc.she] zips you up at the back.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unzip your slip dress and wriggle out of it as it drops to your feet.",
					"You unzip [npc.name]'s slip dress and pull it down off [npc.her] body and past [npc.her] feet.",
					null,
					"[npc.Name] unzips [npc.her] slip dress and wriggles out of it as it drops to [npc.her] feet.",
					"[npc.Name] unzips your slip dress and pulls it down your body and past your feet.",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull up the lower half of your slip dress.",
					"You pull up the lower half of [npc.name]'s slip dress.",
					null,
					"[npc.Name] pulls up the lower half of [npc.her] slip dress.",
					"[npc.Name] pulls up the lower half of your slip dress.",
					null, null, null);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull your slip dress back down into its proper position.",
					"You pull [npc.name]'s slip dress back down into its proper position.",
					null,
					"[npc.Name] pulls [npc.her] slip dress back down into its proper position.",
					"[npc.Name] your slip dress back down into its proper position.",
					null, null, null);
		}
	};

	public static AbstractClothingType TORSO_PLUNGE_DRESS = new AbstractClothingType(600,
			"a",
			false,
			"plunge dress",
			"plunge dresses",
			"An elegant dress with a plunging v-neckline, perfect for showing off its wearer's cleavage.",
			1,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"torso_plunge_dress",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.NIPPLES),
											new ListValue<CoverableArea>(CoverableArea.STOMACH),
											new ListValue<CoverableArea>(CoverableArea.BACK)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST),
											new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
									concealedPartialTorso)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_UP,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedGroin))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the plunge dress and pull it up around your torso. Once in place, you reach back and zip yourself up.",
					"You guide the plunge dress up around [npc.name]'s [npc.legs], before pulling it up around [npc.her] torso. Once it's in place, you zip [npc.herHim] up at the back.",
					null,
					"[npc.Name] steps into the plunge dress, before pulling it up around [npc.her] torso. Once in place, [npc.she] reaches back to zip [npc.herself] up.",
					"[npc.Name] guides the plunge dress up around your [pc.legs], before pulling it up around your torso. One it's in place, [npc.she] zips you up at the back.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unzip your plunge dress and wriggle out of it as it drops to your feet.",
					"You unzip [npc.name]'s plunge dress and pull it down off [npc.her] body and past [npc.her] feet.",
					null,
					"[npc.Name] unzips [npc.her] plunge dress and wriggles out of it as it drops to [npc.her] feet.",
					"[npc.Name] unzips your plunge dress and pulls it down your body and past your feet.",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull up the lower half of your plunge dress.",
					"You pull up the lower half of [npc.name]'s plunge dress.",
					null,
					"[npc.Name] pulls up the lower half of [npc.her] plunge dress.",
					"[npc.Name] pulls up the lower half of your plunge dress.",
					null, null, null);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull your plunge dress back down into its proper position.",
					"You pull [npc.name]'s plunge dress back down into its proper position.",
					null,
					"[npc.Name] pulls [npc.her] plunge dress back down into its proper position.",
					"[npc.Name] your plunge dress back down into its proper position.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType TORSO_LONG_SLEEVE_DRESS = new AbstractClothingType(400,
			"a",
			false,
			"long-sleeved dress",
			"long-sleeved dresses",
			"A long-sleeved bodycon dress with a high neck.",
			1,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"torso_long_sleeve_dress",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BREASTS),
									new ListValue<CoverableArea>(CoverableArea.NIPPLES),
									new ListValue<CoverableArea>(CoverableArea.STOMACH),
									new ListValue<CoverableArea>(CoverableArea.BACK)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
							concealedPartialTorso)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_UP,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedGroin))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the long-sleeved dress and pull it up around your torso. Once in place, you reach back and zip yourself up.",
					"You guide the long-sleeved dress up around [npc.name]'s [npc.legs], before pulling it up around [npc.her] torso. Once it's in place, you zip [npc.herHim] up at the back.",
					null,
					"[npc.Name] steps into the long-sleeved dress, before pulling it up around [npc.her] torso. Once in place, [npc.she] reaches back to zip [npc.herself] up.",
					"[npc.Name] guides the long-sleeved dress up around your [pc.legs], before pulling it up around your torso. One it's in place, [npc.she] zips you up at the back.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unzip your long-sleeved dress and wriggle out of it as it drops to your feet.",
					"You unzip [npc.name]'s long-sleeved dress and pull it down off [npc.her] body and past [npc.her] feet.",
					null,
					"[npc.Name] unzips [npc.her] long-sleeved dress and wriggles out of it as it drops to [npc.her] feet.",
					"[npc.Name] unzips your long-sleeved dress and pulls it down your body and past your feet.",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull up the lower half of your long-sleeved dress.",
					"You pull up the lower half of [npc.name]'s long-sleeved dress.",
					null,
					"[npc.Name] pulls up the lower half of [npc.her] long-sleeved dress.",
					"[npc.Name] pulls up the lower half of your long-sleeved dress.",
					null, null, null);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull your long-sleeved dress back down into its proper position.",
					"You pull [npc.name]'s long-sleeved dress back down into its proper position.",
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
			1,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.COMMON,
			null,
			"torso_bodyconzip_dress",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BACK)),
							null, null)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_UP,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedGroin)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.UNZIPS,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.BREASTS),
											new ListValue<CoverableArea>(CoverableArea.NIPPLES),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.STOMACH),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST),
											new ListValue<ClothingAccess>(ClothingAccess.WAIST),
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedDressFrontFull))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			ColourListPresets.JUST_STEEL.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You put on the dress and zip yourself up.",
					"You guide [npc.name] into the frontal-zip dress and zip [npc.herHim] up.",
					null,
					"[npc.Name] puts on the frontal-zip dress and zips [npc.herself] up.",
					"[npc.Name] guides you into the frontal-zip dress and zips you up.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You fully unzip your frontal-zip dress and shrug it off.",
					"You fully unzip [npc.name]'s frontal-zip dress and pull it off.",
					null,
					"[npc.Name] fully unzips [npc.her] frontal-zip dress and shrugs it off.",
					"[npc.Name] fully unzips your frontal-zip dress and pulls it off.",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			switch(dt) {
				case PULLS_UP:
					return getEquipDescriptions(clothingOwner, clothingRemover, rough,
							"You pull up the bottom of your frontal-zip dress.",
							"You pull up the bottom of [npc.name]'s frontal-zip dress.",
							null,
							"[npc.Name] pulls up the bottom of [npc.her] frontal-zip dress.",
							"[npc.Name] pulls up the bottom of your frontal-zip dress.",
							null, null, null);
				case UNZIPS:
					return getEquipDescriptions(clothingOwner, clothingRemover, rough,
							"You fully unzip the front of your dress.",
							"You fully unzip the front of [npc.name]'s dress.",
							null,
							"[npc.Name] fully unzips the front of [npc.her] dress.",
							"[npc.Name] fully unzips the front of your dress.",
							null, null, null);
				default:
					return super.displaceText(clothingOwner, clothingRemover, dt, rough);
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			switch(dt) {
				case PULLS_UP:
					return getEquipDescriptions(clothingOwner, clothingRemover, rough,
							"You pull the bottom of your frontal-zip dress back down.",
							"You pull the bottom of [npc.name]'s frontal-zip dress back down.",
							null,
							"[npc.Name] pulls the bottom of [npc.her] frontal-zip dress back down.",
							"[npc.Name] pulls the bottom of your frontal-zip dress back down.",
							null, null, null);
				case UNZIPS:
					return getEquipDescriptions(clothingOwner, clothingRemover, rough,
							"You zip up the front of your dress.",
							"You zip up the front of [npc.name]'s dress.",
							null,
							"[npc.Name] zips up the front of [npc.her] dress.",
							"[npc.Name] zips up the front of your dress.",
							null, null, null);
				default:
					return super.displaceText(clothingOwner, clothingRemover, dt, rough);
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
			1,
			Femininity.FEMININE,
			InventorySlot.CHEST,
			Rarity.COMMON,
			null,
			"chest_swimsuit",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST),
									new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.STOMACH),
									new ListValue<CoverableArea>(CoverableArea.BACK)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
							Util.newArrayListOfValues(
									new ListValue<InventorySlot>(InventorySlot.PIERCING_STOMACH)))),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.SHIFTS_ASIDE,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.ANUS),
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							null,
							Util.newArrayListOfValues(
									new ListValue<InventorySlot>(InventorySlot.VAGINA),
									new ListValue<InventorySlot>(InventorySlot.PENIS),
									new ListValue<InventorySlot>(InventorySlot.PIERCING_PENIS),
									new ListValue<InventorySlot>(InventorySlot.PIERCING_VAGINA)))),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_DOWN,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BREASTS),
									new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
							Util.newArrayListOfValues(
									new ListValue<InventorySlot>(InventorySlot.NIPPLE),
									new ListValue<InventorySlot>(InventorySlot.PIERCING_NIPPLE))))),
			Util.newArrayListOfValues(
					new ListValue<InventorySlot>(InventorySlot.GROIN),
					new ListValue<InventorySlot>(InventorySlot.STOMACH)),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the swimsuit and pull it up over your torso.",
					"You guide [npc.name] into the swimsuit and pull it up over [npc.her] torso.",
					null,
					"[npc.Name] steps into the swimsuit and pulls it up to cover [npc.her] torso.",
					"[npc.Name] guides you into the swimsuit and pulls it up over your torso.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide the swimsuit's straps down your [pc.arms] and tug it off your [pc.legs].",
					"You tug down [npc.name]'s swimsuit, before sliding it off [npc.her] [npc.legs].",
					null,
					"[npc.Name] slides the swimsuit's straps down [npc.her] [npc.arms] and tugs it off [npc.her] [npc.legs].",
					"[npc.Name] tugs your swimsuit down, before sliding it off your [pc.legs].",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			switch(dt) {
				case PULLS_DOWN:
					return getEquipDescriptions(clothingOwner, clothingRemover, rough,
							"You pull down the top half of your swimsuit.",
							"You pull down the top half of [npc.name]'s swimsuit.",
							null,
							"[npc.Name] pulls down the top half of [npc.her] swimsuit.",
							"[npc.Name] pulls down the top half of your swimsuit.",
							null, null, null);
				case SHIFTS_ASIDE:
					return getEquipDescriptions(clothingOwner, clothingRemover, rough,
							"You pull the lower part of your swimsuit to one side.",
							"You pull the lower part of [npc.name]'s swimsuit to one side.",
							null,
							"[npc.Name] pulls the lower part of [npc.her] swimsuit to one side.",
							"[npc.Name] pulls the lower part of your swimsuit to one side.",
							null, null, null);
				default:
					return super.displaceText(clothingOwner, clothingRemover, dt, rough);
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			switch(dt) {
				case PULLS_DOWN:
					return getEquipDescriptions(clothingOwner, clothingRemover, rough,
							"You pull your swimsuit back up into its correct position.",
							"You pull [npc.name]'s swimsuit back up into its correct position.",
							null,
							"[npc.Name] pulls [npc.her] swimsuit up into its correct position.",
							"[npc.Name] pulls your swimsuit back up into its correct position.",
							null, null, null);
				case SHIFTS_ASIDE:
					return getEquipDescriptions(clothingOwner, clothingRemover, rough,
							"You pull the lower part of your swimsuit back into its correct position.",
							"You pull the lower part of [npc.name]'s swimsuit back into its correct position.",
							null,
							"[npc.Name] pulls the lower part of [npc.her] swimsuit back into its correct position.",
							"[npc.Name] pulls the lower part of your swimsuit back into its correct position.",
							null, null, null);
				default:
					return super.displaceText(clothingOwner, clothingRemover, dt, rough);
			}
		}
	};
	
	public static AbstractClothingType CHEST_CHEMISE = new AbstractClothingType(250,
			"a",
			false,
			"chemise",
			"chemises",
			"A silky chemise, of the sort usually worn as part of a sexy lingerie outfit.",
			1,
			Femininity.FEMININE,
			InventorySlot.CHEST,
			Rarity.COMMON,
			null,
			"chest_chemise",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.BREASTS),
											new ListValue<CoverableArea>(CoverableArea.NIPPLES),
											new ListValue<CoverableArea>(CoverableArea.STOMACH),
											new ListValue<CoverableArea>(CoverableArea.BACK)),
									null,
									null)), // It's see-through
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_UP,
									Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									null,
									null)) // It's see-through
					),
			null,
			ColourListPresets.LINGERIE.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the chemise.",
					"You pull the chemise onto [npc.name].",
					null,
					"[npc.Name] pulls on the chemise.",
					"[npc.Name] pulls the chemise onto you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off the chemise.",
					"You pull off [npc.name]'s chemise.",
					null,
					"[npc.Name] pulls [npc.her] chemise off.",
					"[npc.Name] pulls your chemise off.",
					null, null, null);
		}
		
		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull up the bottom of your chemise.",
					"You pull up the bottom of [npc.name]'s chemise.",
					null,
					"[npc.Name] pulls up the bottom of [npc.her] chemise.",
					"[npc.Name] pulls up the bottom of your chemise.",
					null, null, null);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull the bottom of your chemise back down.",
					"You pull the bottom of [npc.name]'s chemise back down.",
					null,
					"[npc.Name] pulls the bottom of [npc.her] chemise back down.",
					"[npc.Name] pulls the bottom of your chemise back down.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType CHEST_PLUNGE_BRA = new AbstractClothingType(150,
			"a",
			false,
			"plunge bra",
			"plunge bras",
			"A low-cut bra that reveals a lot of the wearer's cleavage.",
			1,
			Femininity.FEMININE,
			InventorySlot.CHEST,
			Rarity.COMMON,
			null,
			"chest_plunge_bra",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									null,
									null,
									null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_DOWN,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									concealedBreasts))
					),
			null,
			ColourListPresets.LINGERIE.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return braEquipText(clothingOwner, clothingRemover, rough, clothing, applyEffects);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return braUnequipText(clothingOwner, clothingRemover, rough, clothing, applyEffects);
		}
		
		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return braDisplaceText(clothingOwner, clothingRemover, dt, rough);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return braReplaceText(clothingOwner, clothingRemover, dt, rough);
		}
	};
	
	public static AbstractClothingType CHEST_LACY_PLUNGE_BRA = new AbstractClothingType(200,
			"a",
			false,
			"lacy plunge bra",
			"lacy plunge bras",
			"A low-cut bra that reveals a lot of the wearer's cleavage. This particular design is made from a soft, thin fabric, and has a lot of lace trimmings.",
			1,
			Femininity.FEMININE,
			InventorySlot.CHEST,
			Rarity.COMMON,
			null,
			"chest_lacy_plunge_bra",
			null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									null,
									null,
									null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_DOWN,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									concealedBreasts))
					),
			null,
			ColourListPresets.LINGERIE.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return braEquipText(clothingOwner, clothingRemover, rough, clothing, applyEffects);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return braUnequipText(clothingOwner, clothingRemover, rough, clothing, applyEffects);
		}
		
		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return braDisplaceText(clothingOwner, clothingRemover, dt, rough);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return braReplaceText(clothingOwner, clothingRemover, dt, rough);
		}
	};
	
	public static AbstractClothingType CHEST_OPEN_CUP_BRA = new AbstractClothingType(120,
			"an",
			false,
			"open cup bra",
			"open cup bras",
			"More of a series of straps than a proper bra, this piece of clothing fails to conceal any part of its wearer's breasts.",
			1,
			Femininity.FEMININE,
			InventorySlot.CHEST,
			Rarity.COMMON,
			null,
			"chest_open_cup_bra",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									null,
									null,
									null))),
			null,
			ColourListPresets.LINGERIE.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return braEquipText(clothingOwner, clothingRemover, rough, clothing, applyEffects);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return braUnequipText(clothingOwner, clothingRemover, rough, clothing, applyEffects);
		}
	};
	public static AbstractClothingType CHEST_SPORTS_BRA = new AbstractClothingType(150,
			"a",
			false,
			"sports bra",
			"sports bras",
			"An elastic sports bra that's designed to firmly support the wearer's breasts during exercise.",
			1,
			Femininity.FEMININE,
			InventorySlot.CHEST,
			Rarity.COMMON,
			null,
			"chest_sports_bra",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.BACK)),
									null, null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_UP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.BREASTS),
											new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									concealedBreasts))
					),
			null,
			ColourListPresets.LINGERIE.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull the sports bra down over your head, before securing it around your chest.",
					"You pull the sports bra down over [npc.name]'s head, before securing it around [npc.her] chest.",
					null,
					"[npc.Name] pulls the sports bra down over [npc.her] head, before securing it around [npc.her] chest.",
					"[npc.Name] pulls the sports bra down over your head, before securing it around your chest.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull the sports bra off over your head.",
					"You pull [npc.name]'s sports bra off over [npc.her] head.",
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
			1,
			Femininity.FEMININE,
			InventorySlot.CHEST,
			Rarity.COMMON,
			null,
			"chest_croptop_bra",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									null,
									null, null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_UP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.BREASTS),
											new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									concealedBreasts))),
			null,
			ColourListPresets.LINGERIE.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull the croptop bra down over your head, before securing it around your chest.",
					"You pull the croptop bra down over [npc.name]'s head, before securing it around [npc.her] chest.",
					null,
					"[npc.Name] pulls the croptop bra down over [npc.her] head, before securing it around [npc.her] chest.",
					"[npc.Name] pulls the croptop bra down over your head, before securing it around your chest.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull the croptop bra off over your head.",
					"You pull [npc.name]'s croptop bra off over [npc.her] head.",
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
			1,
			Femininity.FEMININE,
			InventorySlot.CHEST,
			Rarity.COMMON,
			null,
			"chest_fullcup_bra",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									null,
									null,
									null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_DOWN,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									concealedBreasts))),
			null,
			ColourListPresets.LINGERIE.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return braEquipText(clothingOwner, clothingRemover, rough, clothing, applyEffects);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return braUnequipText(clothingOwner, clothingRemover, rough, clothing, applyEffects);
		}
		
		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return braDisplaceText(clothingOwner, clothingRemover, dt, rough);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return braReplaceText(clothingOwner, clothingRemover, dt, rough);
		}
	};

	public static AbstractClothingType CHEST_NURSING_BRA = new AbstractClothingType(250,
			"a",
			false,
			"nursing bra",
			"nursing bras",
			"This nursing bra provides additional support to lactating breasts, and has specially designed cups which can be opened to expose the wearer's nipples.",
			1,
			Femininity.FEMININE,
			InventorySlot.CHEST,
			Rarity.COMMON,
			null,
			"chest_nursing_bra",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									null,
									null, null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_DOWN,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									concealedBreasts))
					),
			null,
			ColourListPresets.LINGERIE.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return braEquipText(clothingOwner, clothingRemover, rough, clothing, applyEffects);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return braUnequipText(clothingOwner, clothingRemover, rough, clothing, applyEffects);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unclip your nursing bra's cups and pull them down, exposing your [pc.nipples+].",
					"You unclip the cups on [npc.name]'s nursing bra and pull them down, exposing [npc.her] [npc.nipples+].",
					null,
					"[npc.Name] unclips [npc.her] nursing bra's cups and pulls them down, exposing [npc.her] [npc.nipples+].",
					"[npc.Name] unclips your nursing bra's cups and pulls them down, exposing your [pc.nipples+].",
					null, null, null);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You clip your nursing bra's cups back up to cover your [pc.nipples].",
					"You clip [npc.name]'s nursing bra's cups back up to cover [npc.her] [npc.nipples].",
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
			1,
			Femininity.FEMININE,
			InventorySlot.CHEST,
			Rarity.COMMON,
			null,
			"chest_striped_bra",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									null,
									null, null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_DOWN,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									concealedBreasts))),
			null,
			ColourListPresets.NOT_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You place the bra over your chest before tying up the strings at your back.",
					"You place the bra over [npc.name]'s chest before tying up the strings at [npc.her] back.",
					null,
					"[npc.Name] places the bra over [npc.her] chest before tying up the strings at [npc.her] back.",
					"[npc.Name] places the bra over your chest before tying up the strings at your back.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You untie the bra's strings and take it off.",
					"You untie [npc.name]'s bra's strings and take it off.",
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
			1,
			Femininity.FEMININE,
			InventorySlot.CHEST,
			Rarity.COMMON,
			null,
			"chest_bikini",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									null,
									null, null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_UP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									concealedBreasts))
					),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You place the bikini top over your chest before tying up the strings at your back.",
					"You place the bikini top over [npc.name]'s chest before tying up the strings at [npc.her] back.",
					null,
					"[npc.Name] places the bikini top over [npc.her] chest before tying up the strings at [npc.her] back.",
					"[npc.Name] places the bikini top over your chest before tying up the strings at your back.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You untie the bikini top's strings and take it off.",
					"You untie [npc.name]'s bikini top's strings and take it off.",
					null,
					"[npc.Name] unties [npc.her] bikini top's strings and takes it off.",
					"[npc.Name] unties your bikini top's strings and takes it off.",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide your bikini top's straps off your shoulders before tugging it down to reveal your chest.",
					"You slide the straps of [npc.name]'s bikini top off [npc.her] shoulders before tugging it down to reveal [npc.her] chest.",
					null,
					"[npc.Name] slides [npc.her] bikini top's straps off [npc.her] shoulders before tugging it down to reveal [npc.her] chest.",
					"[npc.Name] slides your bikini top's straps off your shoulders before tugging it down to reveal your chest.",
					null, null, null);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull your bikini top back up to cover your chest.",
					"You pull [npc.name]'s bikini top back up to cover [npc.her] chest.",
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
			1,
			null,
			InventorySlot.CHEST,
			Rarity.COMMON,
			null,
			"chest_sarashi",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BREASTS),
									new ListValue<CoverableArea>(CoverableArea.NIPPLES),
									new ListValue<CoverableArea>(CoverableArea.BACK)),
							null,
							concealedBreasts))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You wrap the sarashi tightly around your chest.",
					"You wrap the sarashi tightly around [npc.name]'s chest.",
					null,
					"[npc.Name] wraps the sarashi tightly around [npc.her] chest.",
					"[npc.Name] wraps the sarashi tightly around your chest.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unwrap the sarashi and remove it from around your chest.",
					"You unwrap the sarashi from around [npc.name]'s chest.",
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
			"A pair of crosses, made out of shiny electrician's tape. They are only just large enough to fully cover a pair of nipples.",
			1,
			null,
			InventorySlot.NIPPLE,
			Rarity.COMMON,
			null,
			"chest_tapecrosses",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
							null,
							Util.newArrayListOfValues(
									new ListValue<InventorySlot>(InventorySlot.PIERCING_NIPPLE))))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
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
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You stick the tape crosses over your [pc.nipples].",
					"You stick the tape crosses over [npc.name]'s [npc.nipples].",
					null,
					"[npc.Name] sticks the tape crosses over [npc.her] [npc.nipples].",
					"[npc.Name] sticks the tape crosses over your [pc.nipples].",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
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
			1,
			Femininity.FEMININE,
			InventorySlot.STOMACH,
			Rarity.COMMON,
			null,
			"stomach_lowback_body",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST),
									new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.STOMACH),
									new ListValue<CoverableArea>(CoverableArea.BACK)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
							concealedStomach)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.SHIFTS_ASIDE, null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.ANUS),
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							concealedGenitals)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_DOWN,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
							concealedBreasts))),
			Util.newArrayListOfValues(
					new ListValue<InventorySlot>(InventorySlot.GROIN),
					new ListValue<InventorySlot>(InventorySlot.CHEST)),
			ColourListPresets.LINGERIE.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the bodysuit and pull it up over your torso.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] steps into " + clothing.getName(true) + " and pulls it up to cover [npc.her] torso.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You slide the bodysuit's straps down your arms and tug it down off your legs.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner,
						"[npc.Name] slides the bodysuit's straps down [npc.her] arms and tugs it down off [npc.her] legs.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] tugs your bodysuit down, sliding it off your legs.";
				else
					return UtilText.parse(clothingOwner, "You tug down [npc.name]'s bodysuit, sliding it off [npc.her] legs.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (dt == DisplacementType.PULLS_DOWN) {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull down the top half of your bodysuit.";
				else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
					return UtilText.parse(clothingOwner, "[npc.Name] pulls down the top half of [npc.her] bodysuit.");
				else {
					if (clothingOwner.isPlayer())
						return "[npc.Name] pulls down the top half of your bodysuit.";
					else
						return UtilText.parse(clothingOwner, "You pull down the top half of [npc.name]'s bodysuit.");
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
						return UtilText.parse(clothingOwner, "You pull the lower part of [npc.name]'s bodysuit to one side.");
				}
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
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
			1,
			Femininity.FEMININE,
			InventorySlot.STOMACH,
			Rarity.COMMON,
			null,
			"stomach_underbust_corset",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(
								DisplacementType.REMOVE_OR_EQUIP,
								Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
								Util.newArrayListOfValues(
										new ListValue<CoverableArea>(CoverableArea.STOMACH),
										new ListValue<CoverableArea>(CoverableArea.BACK)),
								Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
								concealedStomach))),
			null,
			ColourListPresets.LINGERIE.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You place the corset around your stomach before tying the laces up at your back.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] places " + clothing.getName(true) + " around [npc.her] stomach before tying the laces up at [npc.her] back.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You untie the corset's laces and remove it from your stomach.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] unties [npc.her] corset's laces and removes it from [npc.her] stomach.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] unties your corset's laces and removes it from your stomach.";
				else
					return UtilText.parse(clothingOwner, "You untie [npc.name]'s corset's laces and remove it from [npc.her] stomach.");
			}
		}
	};
	public static AbstractClothingType STOMACH_OVERBUST_CORSET = new AbstractClothingType(500,
			"an",
			false,
			"overbust corset",
			"overbust corsets",
			"A corset that keeps the wearer's stomach nicely shaped, while also covering their breasts. It has a series of laces at the back that are used to tighten it.",
			1,
			Femininity.FEMININE,
			InventorySlot.STOMACH,
			Rarity.COMMON,
			null, "stomach_overbust_corset",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.WAIST),
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.BREASTS),
											new ListValue<CoverableArea>(CoverableArea.NIPPLES),
											new ListValue<CoverableArea>(CoverableArea.STOMACH),
											new ListValue<CoverableArea>(CoverableArea.BACK)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.WAIST),
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									Util.newArrayListOfValues(
											new ListValue<InventorySlot>(InventorySlot.NIPPLE),
											new ListValue<InventorySlot>(InventorySlot.PIERCING_NIPPLE),
											new ListValue<InventorySlot>(InventorySlot.PIERCING_STOMACH))))),
			Util.newArrayListOfValues(
					new ListValue<InventorySlot>(InventorySlot.CHEST)),
			ColourListPresets.LINGERIE.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You place the corset around your stomach before tying the laces up at your back.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] places " + clothing.getName(true) + " around [npc.her] stomach before tying the laces up at [npc.her] back.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You untie the corset's laces and remove it from your stomach.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] unties [npc.her] corset's laces and removes it from [npc.her] stomach.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] unties your corset's laces and removes it from your stomach.";
				else
					return UtilText.parse(clothingOwner, "You untie [npc.name]'s corset's laces and remove it from [npc.her] stomach.");
			}
		}
	};
	
	public static AbstractClothingType STOMACH_SARASHI = new AbstractClothingType(200,
			"a",
			false,
			"stomach sarashi",
			"stomach sarashis",
			"A long strip of thick cotton, wrapped around the stomach as an added layer of protection, or to gain a slim figure.",
			2,
			null,
			InventorySlot.STOMACH,
			Rarity.COMMON,
			null,
			"stomach_sarashi",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(
								DisplacementType.REMOVE_OR_EQUIP,
								Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
								Util.newArrayListOfValues(
										new ListValue<CoverableArea>(CoverableArea.STOMACH),
										new ListValue<CoverableArea>(CoverableArea.BACK)),
								Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
								concealedStomach))),
			null,
			ColourListPresets.LINGERIE.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You wrap the sarashi around your stomach.";
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] wraps " + clothing.getName(true) + " around [npc.her] stomach.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You unwrap the sarashi remove it from your stomach.";
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] unwraps the sarashi from around [npc.her] stomach.");
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingRemover, "[npc.Name] unwraps your sarashi and removes it from around your stomach.");
				} else {
					return UtilText.parse(clothingOwner, "You unwrap [npc.name]'s sarashi and remove it from around [npc.her] stomach.");
				}
			}
		}
	};

	// HAND
	public static AbstractClothingType HAND_GLOVES = new AbstractClothingType(100,
			"a pair of",
			true,
			"glove",
			"gloves",
			"A pair of normal-looking gloves, they're designed to keep the wearer's hands warm.",
			1,
			null,
			InventorySlot.HAND,
			Rarity.COMMON,
			null,
			"hand_gloves",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FINGERS)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FINGERS)),
							Util.newArrayListOfValues(
									new ListValue<InventorySlot>(InventorySlot.FINGER))))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the gloves and give your fingers an experimental wiggle.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] pulls on " + clothing.getName(true) + " and gives [npc.her] fingers an experimental wiggle.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your gloves.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls off [npc.her] gloves.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls your gloves off.";
				else
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s gloves off.");
			}
		}
	};
	
	public static AbstractClothingType HAND_FINGERLESS_GLOVES = new AbstractClothingType(100,
			"a pair of",
			true,
			"fingerless glove",
			"fingerless gloves",
			"A pair of fingerless gloves. They are the sort worn by cyclists and runners, and are made out of a thin, breathable material.",
			1,
			null,
			InventorySlot.HAND,
			Rarity.COMMON,
			null,
			"hand_fingerless_gloves",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FINGERS)),
							null,
							null,
							null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the gloves and give your fingers an experimental wiggle.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] pulls on " + clothing.getName(true) + " and gives [npc.her] fingers an experimental wiggle.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your gloves.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls off [npc.her] gloves.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls your gloves off.";
				else
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s gloves off.");
			}
		}
	};
	public static AbstractClothingType HAND_ELBOWLENGTH_GLOVES = new AbstractClothingType(200,
			"a pair of",
			true,
			"elbow-length glove",
			"elbow-length gloves",
			"A pair of elbow-length gloves, they're made from a thin, soft fabric.",
			1,
			Femininity.FEMININE,
			InventorySlot.HAND,
			Rarity.COMMON,
			null,
			"hand_elbowlength_gloves",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FINGERS),
									new ListValue<ClothingAccess>(ClothingAccess.WRISTS)),
							null,
							null,
							Util.newArrayListOfValues(
									new ListValue<InventorySlot>(InventorySlot.FINGER))))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the gloves and give your fingers an experimental wiggle.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] pulls on " + clothing.getName(true) + " and gives [npc.her] fingers an experimental wiggle.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your gloves.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls off [npc.her] gloves.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls your gloves off.";
				else
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s gloves off.");
			}
		}
	};

	public static AbstractClothingType HAND_WRAPS = new AbstractClothingType(200,
			"a pair of",
			true,
			"arm wrap",
			"arm wraps",
			"A long strip of thick cotton, wrapped around the hands and forearms as an added layer of protection.",
			1,
			null,
			InventorySlot.HAND,
			Rarity.COMMON,
			null,
			"hand_wraps",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.FINGERS),
											new ListValue<ClothingAccess>(ClothingAccess.WRISTS)),
									null,
									null,
									null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You wrap the strip of thick cotton around your [pc.arms].",
					"You wrap the strip of thick cotton around [npc.name]'s [npc.arms].",
					null,
					"[npc.Name] wraps the strip of thick cotton around [npc.her] [npc.arms].",
					"[npc.Name] wraps the strip of thick cotton around your [pc.arms].",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unwrap the strip of thick cotton from around your [pc.arms].",
					"You unwrap the strip of thick cotton from around [npc.name]'s [npc.arms].",
					null,
					"[npc.Name] unwraps the strip of thick cotton from around [npc.her] [npc.arms].",
					"[npc.Name] unwraps the strip of thick cotton from around your [pc.arms].",
					null, null, null);
		}
	};
	
	public static AbstractClothingType HAND_FISHNET_GLOVES = new AbstractClothingType(100,
			"a pair of",
			true,
			"fishnet glove",
			"fishnet gloves",
			"A pair of fishnet gloves.",
			1,
			Femininity.FEMININE,
			InventorySlot.HAND,
			Rarity.COMMON,
			null,
			"hand_fishnet_gloves",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.FINGERS),
											new ListValue<ClothingAccess>(ClothingAccess.WRISTS)),
									null,
									null,
									null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull on the fishnet gloves.";
				
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] pulls on the fishnet gloves.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull off your fishnet gloves.";
				
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] pulls off [npc.her] fishnet gloves.");
			
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingRemover, "[npc.Name] pulls off your fishnet gloves.");
				} else {
					return UtilText.parse(clothingOwner, "You pull off [npc.name]'s fishnet gloves.");
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
			1,
			Femininity.FEMININE,
			InventorySlot.WRIST,
			Rarity.COMMON,
			null,
			"wrist_womens_watch",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.WRISTS)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.WRISTS)),
							null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You place the watch on your wrist and fasten the strap.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] places " + clothing.getName(true) + " on [npc.her] wrist and fastens the strap.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You unfasten your watch's strap and take it off.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] unfastens [npc.her] watch's strap and takes it off.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] unfastens your watch's strap and takes it off.";
				else
					return UtilText.parse(clothingOwner, "You unfasten the strap on [npc.name]'s watch and take it off.");
			}
		}
	};
	
	public static AbstractClothingType WRIST_MENS_WATCH = new AbstractClothingType(1200,
			"a",
			false,
			"masculine watch",
			"masculine watches",
			"A masculine-looking watch. Like others of its kind, its primary purpose is to keep track of the time.",
			1,
			Femininity.MASCULINE,
			InventorySlot.WRIST,
			Rarity.COMMON,
			null,
			"wrist_mens_watch",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.WRISTS)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.WRISTS)),
							null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You place the watch on your wrist and fasten the strap.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] places " + clothing.getName(true) + " on [npc.her] wrist and fastens the strap.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You unfasten your watch's strap and take it off.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] unfastens [npc.her] watch's strap and takes it off.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] unfastens your watch's strap and takes it off.";
				else
					return UtilText.parse(clothingOwner, "You unfasten the strap on [npc.name]'s watch and take it off.");
			}
		}
	};
	
	public static AbstractClothingType WRIST_BANGLE = new AbstractClothingType(600,
			"a",
			false,
			"bangle",
			"bangles",
			"A simple metal bangle that fits comfortably around the wearer's wrist.",
			1,
			Femininity.FEMININE,
			InventorySlot.WRIST,
			Rarity.COMMON,
			null,
			"wrist_bangle",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.WRISTS)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.WRISTS)),
							null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide the bangle onto your wrist.",
					"You slide the bangle onto [npc.name]'s wrist.",
					null,
					"[npc.Name] slides the bangle onto [npc.her] wrist.",
					"[npc.Name] slides the bangle onto your wrist.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide your bangle off of your wrist.",
					"You slide [npc.name]'s bangle off of [npc.her] wrist.",
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
			1,
			null,
			InventorySlot.WRIST,
			Rarity.COMMON,
			null,
			"wrist_suit_cuffs",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.WRISTS)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.WRISTS)),
							null))),
			null,
			ColourListPresets.JUST_WHITE.getPresetColourList(), ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_BLACK.getPresetColourList(), ColourListPresets.ALL.getPresetColourList(),
			null,
			null, 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You fasten the suit cuffs around your wrists.",
					"You fasten the suit cuffs around [npc.name]'s wrists.",
					null,
					"[npc.Name] fastens the suit cuffs around [npc.her] wrists.",
					"[npc.Name] fastens the suit cuffs around your wrists.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unfasten your suit cuffs and take them off.",
					"You unfasten [npc.name]'s suit cuffs and take them off.",
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
			1,
			null,
			InventorySlot.WRIST,
			Rarity.COMMON,
			null,
			"wrist_sweatbands",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.WRISTS)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.WRISTS)),
							null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You fasten the wristband around your wrists.",
					"You fasten the wristband around [npc.name]'s wrists.",
					null,
					"[npc.Name] fastens the wristband around [npc.her] wrists.",
					"[npc.Name] fastens the wristband around your wrists.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unfasten your wristband and take them off.",
					"You unfasten [npc.name]'s wristband and take them off.",
					null,
					"[npc.Name] unfastens [npc.her] wristband and takes them off.",
					"[npc.Name] unfastens your wristband and takes them off.",
					null, null, null);
		}
	};

	// FINGER

	public static AbstractClothingType FINGER_RING = new AbstractClothingType(250,
			"a",
			false,
			"ring",
			"rings",
			"A plain band of metal that fits onto a person's finger.",
			0,
			null,
			InventorySlot.FINGER,
			Rarity.COMMON,
			null,
			"finger_ring",

			null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FINGERS)),
							null,
							null, null))),

			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You slide the ring onto your finger.";
			else
				return UtilText.parse(clothingOwner, "[npc.Name] slides " + clothing.getName(true) + " onto [npc.her] finger.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off the ring.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls off [npc.her] ring.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls off your ring.";
				else
					return UtilText.parse(clothingOwner, "You pull off [npc.name]'s ring.");
			}
		}
	};

	// BELT
	
	public static AbstractClothingType HIPS_CONDOMS = new AbstractClothingType(20,
			"a",
			false,
			"condom belt",
			"condom belts",
			"A strip of strong elastic fabric that hugs the wearer's hips. You could use it to tie used condoms to...",
			0,
			null,
			InventorySlot.HIPS,
			Rarity.COMMON,
			null,
			"belt_used_condoms",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							null,
							null,
							null,
							null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You step into the belt before pulling it up to your waist.";
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] steps into the belt before pulling it up to [npc.her] waist.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull down your belt and kick it off your [pc.feet].";
				
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] belt before kicking it off [npc.her] [npc.feet].");
				
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingRemover, "[npc.Name] pulls your belt down and slides it off your [pc.feet].");
				} else {
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s belt down and slide it off [npc.her] [npc.feet].");
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
			1,
			Femininity.FEMININE,
			InventorySlot.HIPS,
			Rarity.COMMON,
			null,
			"hips_suspender_belt",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							null,
							null,
							null,
							null))),
			null,
			ColourListPresets.LINGERIE.getPresetColourList(), ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_STEEL.getPresetColourList(), ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null, 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the suspender belt before pulling it up to your waist.",
					"You get [npc.name] to step into the suspender belt before pulling it up to [npc.her] waist.",
					null,
					"[npc.Name] steps into the suspender belt before pulling it up to [npc.her] waist.",
					"[npc.Name] gets you to step into the suspender belt before pulling it up to your waist.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your suspender belt and take it off.",
					"You pull down [npc.name]'s suspender belt and take it off.",
					null,
					"[npc.Name] pulls down [npc.her] suspender belt and takes it off.",
					"[npc.Name] pulls down your suspender belt and takes it off.",
					null, null, null);
		}
	};
	
	// LEG

	public static AbstractClothingType LEG_SKIRT = new AbstractClothingType(150,
			"a",
			false,
			"skirt",
			"skirts",
			"A plain skirt, there's nothing especially interesting about it.",
			1,
			Femininity.FEMININE,
			InventorySlot.LEG,
			Rarity.COMMON,
			null,
			"leg_skirt",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.ANUS),
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							concealedGroin))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the skirt before pulling it up to your waist.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] steps into " + clothing.getName(true) + " before pulling it up to [npc.her] waist.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your skirt and kick it off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] skirt, kicking it off [npc.her] feet.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls your skirt down and slides it off your feet.";
				else
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s skirt down and slide it off [npc.her] feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull up your skirt.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls up [npc.her] skirt.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls your skirt up.";
				else
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s skirt up.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your skirt back down.";
			else
				return UtilText.parse(clothingOwner, "[npc.Name] pulls [npc.her] skirt back down.");
		}
	};
	
	public static AbstractClothingType LEG_PENCIL_SKIRT = new AbstractClothingType(250,
			"a",
			false,
			"pencil skirt",
			"pencil skirts",
			"A pencil skirt that clings tightly to the wearer's hips and legs.",
			1,
			Femininity.FEMININE,
			InventorySlot.LEG,
			Rarity.COMMON,
			null,
			"leg_pencil_skirt",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.ANUS),
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							concealedGroin))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the skirt before pulling it up to your waist.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] steps into " + clothing.getName(true) + " before pulling it up to [npc.her] waist.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your skirt and kick it off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] skirt, kicking it off [npc.her] feet.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls your skirt down and slides it off your feet.";
				else
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s skirt down and slide it off [npc.her] feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull up your skirt.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls up [npc.her] skirt.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls your skirt up.";
				else
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s skirt up.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your skirt back down.";
			else
				return UtilText.parse(clothingOwner, "[npc.Name] pulls [npc.her] skirt back down.");
		}
	};
	
	public static AbstractClothingType LEG_MINI_SKIRT = new AbstractClothingType(150,
			"a",
			false,
			"miniskirt",
			"miniskirts",
			"A very short skirt that barely reaches down to mid-thigh.",
			1,
			Femininity.FEMININE,
			InventorySlot.LEG,
			Rarity.COMMON,
			null,
			"leg_mini_skirt",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.ANUS),
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							concealedGroin))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the miniskirt before pulling it up to your waist.",
					"You slide the miniskirt up [npc.name]'s [npc.legs] to rest around [npc.her] waist.",
					null,
					"[npc.Name] steps into the miniskirt before pulling it up to [npc.her] waist.",
					"[npc.Name] slides the miniskirt up your [pc.legs] to rest around your waist.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your miniskirt and kick it off your [pc.feet].",
					"You pull [npc.name]'s miniskirt down and slide it off [npc.her] [npc.feet].",
					null,
					"[npc.Name] pulls down [npc.her] miniskirt before kicking it off [npc.her] [npc.feet].",
					"[npc.Name] pulls your miniskirt down and slides it off your [pc.feet].",
					null, null, null);
		}
	};

	public static AbstractClothingType LEG_MICRO_SKIRT_PLEATED = new AbstractClothingType(250,
			"a",
			false,
			"pleated microskirt",
			"pleated microskirts",
			"An extremely tiny pleated skirt. It's so short that it does nothing to conceal its wearer's private parts.",
			1,
			Femininity.FEMININE,
			InventorySlot.LEG,
			Rarity.COMMON,
			null,
			"leg_micro_skirt_pleated",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							null,
							null,
							null,
							null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You step into the microskirt before pulling it up to your waist.";
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] steps into the microskirt before pulling it up to [npc.her] waist.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull down your microskirt and kick it off your [pc.feet].";
				
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] microskirt before kicking it off [npc.her] [npc.feet].");
				
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingRemover, "[npc.Name] pulls your microskirt down and slides it off your [pc.feet].");
				} else {
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s microskirt down and slide it off [npc.her] [npc.feet].");
				}
			}
		}
	};
	
	public static AbstractClothingType LEG_MICRO_SKIRT_BELTED = new AbstractClothingType(100,
			"a",
			false,
			"belted microskirt",
			"belted microskirts",
			"An extremely tiny skirt, with an accompanying belt. It's so short that it does nothing to conceal its wearer's private parts.",
			1,
			Femininity.FEMININE,
			InventorySlot.LEG,
			Rarity.COMMON,
			null,
			"leg_micro_skirt_belted",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							null,
							null,
							null,
							null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You step into the microskirt before pulling it up to your waist.";
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] steps into the microskirt before pulling it up to [npc.her] waist.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull down your microskirt and kick it off your [pc.feet].";
				
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] microskirt before kicking it off [npc.her] [npc.feet].");
				
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingRemover, "[npc.Name] pulls your microskirt down and slides it off your [pc.feet].");
				} else {
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s microskirt down and slide it off [npc.her] [npc.feet].");
				}
			}
		}
	};

	public static AbstractClothingType LEG_SHORTS = new AbstractClothingType(150,
			"a pair of",
			true,
			"shorts",
			"shorts",
			"A pair of ordinary unisex shorts.",
			1,
			null,
			InventorySlot.LEG,
			Rarity.COMMON,
			null,
			"leg_shorts",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)), null)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_DOWN,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedGroin)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.UNZIPS,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.PENIS)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedUnzipsGroin))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
			
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the shorts before pulling them up to your waist.",
					"You pull the shorts up [npc.name]'s [npc.legs] and button them up around [npc.her] waist.",
					null,
					"[npc.Name] steps into the shorts before pulling them up to [npc.her] waist.",
					"[npc.Name] pulls the shorts up your [pc.legs] and buttons them up around your waist.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your shorts and kick them off your feet.",
					"You pull [npc.name]'s shorts down and slide them off [npc.her] feet.",
					null,
					"[npc.Name] pulls down [npc.her] shorts, before kicking them off [npc.her] feet.",
					"[npc.Name] pulls your shorts down, before sliding them off your feet.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType LEG_BIKE_SHORTS = new AbstractClothingType(350,
			"a pair of",
			true,
			"bike shorts",
			"bike shorts",
			"A pair of very tight lycra bike shorts (sometimes referred to as 'spats'). The elastic material hugs to the wearer's body, showing off their curves.",
			1,
			null,
			InventorySlot.LEG,
			Rarity.COMMON,
			null,
			"leg_bikeShorts",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)), null)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_DOWN,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedGroin))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the bike shorts before pulling them up to your waist.",
					"You pull the bike shorts up [npc.name]'s [npc.legs] to [npc.her] waist.",
					null,
					"[npc.Name] steps into the bike shorts before pulling them up to [npc.her] waist.",
					"[npc.Name] pulls the bike shorts up your [pc.legs] to your waist.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your bike shorts and kick them off your feet.",
					"You pull [npc.name]'s bike shorts down and slide them off [npc.her] feet.",
					null,
					"[npc.Name] pulls down [npc.her] bike shorts, before kicking them off [npc.her] feet.",
					"[npc.Name] pulls your bike shorts down, before sliding them off your feet.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType LEG_SPORT_SHORTS = new AbstractClothingType(150,
			"a pair of",
			true,
			"sport shorts",
			"sport shorts",
			"A pair of sporty unisex shorts, of the sort typically worn by people going to the gym or out exercising.",
			1,
			null,
			InventorySlot.LEG,
			Rarity.COMMON,
			null,
			"leg_sport_shorts",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)), null)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_DOWN,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedGroin))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
			
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the shorts before pulling them up to your waist.",
					"You pull the shorts up [npc.name]'s [npc.legs] and button them up around [npc.her] waist.",
					null,
					"[npc.Name] steps into the shorts before pulling them up to [npc.her] waist.",
					"[npc.Name] pulls the shorts up your [pc.legs] and buttons them up around your waist.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your shorts and kick them off your feet.",
					"You pull [npc.name]'s shorts down and slide them off [npc.her] feet.",
					null,
					"[npc.Name] pulls down [npc.her] shorts, before kicking them off [npc.her] feet.",
					"[npc.Name] pulls your shorts down, before sliding them off your feet.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType LEG_HOTPANTS = new AbstractClothingType(150,
			"a pair of",
			true,
			"hotpants",
			"hotpants",
			"A pair of very small, very tight shorts, commonly referred to as hotpants.",
			1,
			Femininity.FEMININE,
			InventorySlot.LEG,
			Rarity.COMMON,
			null,
			"leg_hotpants",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)), null)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_DOWN,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedGroin))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the hotpants before pulling them up to your waist.",
					"You pull the hotpants up [npc.name]'s [npc.legs] to [npc.her] waist.",
					null,
					"[npc.Name] steps into the hotpants before pulling them up to [npc.her] waist.",
					"[npc.Name] pulls the hotpants up your [pc.legs] to your waist.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your hotpants and kick them off your feet.",
					"You pull [npc.name]'s hotpants down and slide them off [npc.her] feet.",
					null,
					"[npc.Name] pulls down [npc.her] hotpants, before kicking them off [npc.her] feet.",
					"[npc.Name] pulls your hotpants down, before sliding them off your feet.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType LEG_TIGHT_TROUSERS = new AbstractClothingType(400,
			"a pair of",
			true,
			"tight jeans",
			"tight jeans",
			"A pair of tight jeans that hug the wearer's legs.",
			1,
			null,
			InventorySlot.LEG,
			Rarity.COMMON,
			null,
			"leg_tight_jeans",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.FEET),
											new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)),
									null,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)),
									concealedAnklesFromTrousers)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_DOWN,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedGroin)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.UNZIPS,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.PENIS)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedUnzipsGroin))),
			null,
			ColourListPresets.DENIM.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the tight jeans before pulling them up to your waist.",
					"You pull the tight jeans up [npc.name]'s [npc.legs] and button them up around [npc.her] waist.",
					null,
					"[npc.Name] steps into the tight jeans before pulling them up to [npc.her] waist.",
					"[npc.Name] pulls the tight jeans up your [pc.legs] and buttons them up around your waist.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your tight jeans and kick them off your feet.",
					"You pull [npc.name]'s tight jeans down and slide them off [npc.her] feet.",
					null,
					"[npc.Name] pulls down [npc.her] tight jeans, before kicking them off [npc.her] feet.",
					"[npc.Name] pulls your tight jeans down, before sliding them off your feet.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType LEG_JEANS = new AbstractClothingType(250,
			"a pair of",
			true,
			"jeans",
			"jeans",
			"A pair of unisex jeans.",
			1,
			null,
			InventorySlot.LEG,
			Rarity.COMMON,
			null,
			"leg_jeans",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)),
							concealedAnklesFromTrousers)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_DOWN,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedGroin)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.UNZIPS,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.PENIS)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedUnzipsGroin))),
			null,
			ColourListPresets.DENIM.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the jeans before pulling them up to your waist.",
					"You pull the jeans up [npc.name]'s [npc.legs] and button them up around [npc.her] waist.",
					null,
					"[npc.Name] steps into the jeans before pulling them up to [npc.her] waist.",
					"[npc.Name] pulls the jeans up your [pc.legs] and buttons them up around your waist.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your jeans and kick them off your feet.",
					"You pull [npc.name]'s jeans down and slide them off [npc.her] feet.",
					null,
					"[npc.Name] pulls down [npc.her] jeans, before kicking them off [npc.her] feet.",
					"[npc.Name] pulls your jeans down, before sliding them off your feet.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType LEG_TROUSERS = new AbstractClothingType(350,
			"a pair of",
			true,
			"trousers",
			"trousers",
			"A pair of men's trousers that hang loosely around the legs.",
			1,
			Femininity.MASCULINE,
			InventorySlot.LEG,
			Rarity.COMMON,
			null,
			"leg_trousers",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.FEET),
											new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)),
									null,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)),
									concealedAnklesFromTrousers)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_DOWN,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedGroin)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.UNZIPS,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.PENIS)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedUnzipsGroin))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the trousers before pulling them up to your waist.",
					"You pull the trousers up [npc.name]'s [npc.legs] and button them up around [npc.her] waist.",
					null,
					"[npc.Name] steps into the trousers before pulling them up to [npc.her] waist.",
					"[npc.Name] pulls the trousers up your [pc.legs] and buttons them up around your waist.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your trousers and kick them off your feet.",
					"You pull [npc.name]'s trousers down and slide them off [npc.her] feet.",
					null,
					"[npc.Name] pulls down [npc.her] trousers, before kicking them off [npc.her] feet.",
					"[npc.Name] pulls your trousers down, before sliding them off your feet.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType LEG_CARGO_TROUSERS = new AbstractClothingType(250,
			"a pair of",
			true,
			"cargo trousers",
			"cargo trousers",
			"A pair of men's baggy cargo trousers, made of a hard-wearing fabric.",
			1,
			Femininity.MASCULINE,
			InventorySlot.LEG,
			Rarity.COMMON,
			null,
			"leg_cargo_trousers",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)),
							concealedAnklesFromTrousers)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_DOWN,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedGroin)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.UNZIPS,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.PENIS)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedUnzipsGroin))),
			null,
			ColourListPresets.LEATHER.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the cargo trousers before pulling them up to your waist.",
					"You pull the cargo trousers up [npc.name]'s [npc.legs] and button them up around [npc.her] waist.",
					null,
					"[npc.Name] steps into the cargo trousers before pulling them up to [npc.her] waist.",
					"[npc.Name] pulls the cargo trousers up your [pc.legs] and buttons them up around your waist.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your cargo trousers and kick them off your feet.",
					"You pull [npc.name]'s cargo trousers down and slide them off [npc.her] feet.",
					null,
					"[npc.Name] pulls down [npc.her] cargo trousers, before kicking them off [npc.her] feet.",
					"[npc.Name] pulls your cargo trousers down, before sliding them off your feet.",
					null, null, null);
		}
	};

	public static AbstractClothingType LEG_YOGA_PANTS = new AbstractClothingType(150,
			"a pair of",
			true,
			"yoga pants",
			"yoga pants",
			"A pair of very tight yoga pants, made from a stretchable and thin fabric.",
			1,
			Femininity.FEMININE,
			InventorySlot.LEG,
			Rarity.COMMON,
			null,
			"leg_yoga_pants",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)),
							concealedAnklesFromTrousers)),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.PULLS_DOWN,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.ANUS),
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							concealedGroin))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the yoga pants before pulling them up to your waist.",
					"You pull the yoga pants up [npc.name]'s [npc.legs] and button them up around [npc.her] waist.",
					null,
					"[npc.Name] steps into the yoga pants before pulling them up to [npc.her] waist.",
					"[npc.Name] pulls the yoga pants up your [pc.legs] and buttons them up around your waist.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your yoga pants and kick them off your feet.",
					"You pull [npc.name]'s yoga pants down and slide them off [npc.her] feet.",
					null,
					"[npc.Name] pulls down [npc.her] yoga pants, before kicking them off [npc.her] feet.",
					"[npc.Name] pulls your yoga pants down, before sliding them off your feet.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType LEG_ASSLESS_CHAPS = new AbstractClothingType(750,
			"a pair of",
			true,
			"assless chaps",
			"assless chaps",
			"These leather chaps are missing a large piece at the rear, which leaves the wearer's ass completely exposed.",
			1,
			null,
			InventorySlot.LEG,
			Rarity.COMMON,
			null,
			"leg_assless_chaps",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.FEET),
											new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)),
									null,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)),
									concealedAnklesFromTrousers)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_DOWN,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							null,
							Util.newArrayListOfValues(
									new ListValue<InventorySlot>(InventorySlot.VAGINA),
									new ListValue<InventorySlot>(InventorySlot.PENIS),
									new ListValue<InventorySlot>(InventorySlot.PIERCING_PENIS),
									new ListValue<InventorySlot>(InventorySlot.PIERCING_VAGINA))))),

			null,
			ColourListPresets.LEATHER.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the assless chaps before pulling them up to your waist.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] steps into " + clothing.getName(true) + " before pulling them up to [npc.her] waist.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your assless chaps and kick them off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] assless chaps, kicking them off [npc.her] feet.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls your assless chaps down and slides them off your feet.";
				else
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s assless chaps down and slide them off [npc.her] feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your assless chaps.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] assless chaps.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls your assless chaps down.";
				else
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s assless chaps down.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your assless chaps back up.";
			else
				return UtilText.parse(clothingOwner, "[npc.Name] pulls [npc.her] assless chaps back up.");
		}
	};

	public static AbstractClothingType LEG_CROTCHLESS_CHAPS = new AbstractClothingType(750,
			"a pair of",
			true,
			"crotchless chaps",
			"crotchless chaps",
			"These leather chaps are missing a large piece at the front and back, which leaves the wearer's ass and groin completely exposed.",
			1,
			null,
			InventorySlot.LEG,
			Rarity.COMMON,
			null,
			"leg_crotchless_chaps",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)), 
							Util.newArrayListOfValues(
									new ListValue<InventorySlot>(InventorySlot.ANKLE),
									new ListValue<InventorySlot>(InventorySlot.SOCK),
									new ListValue<InventorySlot>(InventorySlot.ANUS))))),
			null,
			ColourListPresets.LEATHER.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the crotchless chaps before pulling them up to your waist.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] steps into " + clothing.getName(true) + " before pulling them up to [npc.her] waist.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your crotchless chaps and kick them off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] crotchless chaps, kicking them off [npc.her] feet.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls your crotchless chaps down and slides them off your feet.";
				else
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s crotchless chaps down and slide them off [npc.her] feet.");
			}
		}
	};

	// GROIN

	public static AbstractClothingType GROIN_PANTIES = new AbstractClothingType(100,
			"a pair of",
			true,
			"panties",
			"panties",
			"A pair of distinctly feminine panties, made from soft cotton.",
			1,
			Femininity.FEMININE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"groin_panties",
			null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							null,
							null, null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.SHIFTS_ASIDE,
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.ANUS),
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							null,
							concealedGenitals))),
			null,
			ColourListPresets.LINGERIE.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the panties before pulling them up to cover your private parts.",
					"You pull the panties up [npc.name]'s [npc.legs] to cover [npc.her] private parts.",
					null,
					"[npc.Name] steps into the panties before pulling them up to cover [npc.her] private parts.",
					"[npc.Name] pulls the panties up your [pc.legs] to cover your private parts.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your panties and kick them off your [pc.feet].",
					"You pull down [npc.name]'s panties and slide them off [npc.her] [npc.feet].",
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
			1,
			Femininity.FEMININE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"groin_shimapan",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							null,
							null, null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.SHIFTS_ASIDE,
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.ANUS),
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							null,
							concealedGenitals))),
			null,
			ColourListPresets.NOT_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the panties before pulling them up to cover your private parts.",
					"You pull the panties up [npc.name]'s [npc.legs] to cover [npc.her] private parts.",
					null,
					"[npc.Name] steps into the panties before pulling them up to cover [npc.her] private parts.",
					"[npc.Name] pulls the panties up your [pc.legs] to cover your private parts.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your panties and kick them off your [pc.feet].",
					"You pull down [npc.name]'s panties and slide them off [npc.her] [npc.feet].",
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
			1,
			Femininity.FEMININE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"groin_lacy_panties",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							null,
							null,
							null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.SHIFTS_ASIDE,
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.ANUS),
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							null,
							concealedGenitals))),
			null,
			ColourListPresets.LINGERIE.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the panties before pulling them up to cover your private parts.",
					"You pull the panties up [npc.name]'s [npc.legs] to cover [npc.her] private parts.",
					null,
					"[npc.Name] steps into the panties before pulling them up to cover [npc.her] private parts.",
					"[npc.Name] pulls the panties up your [pc.legs] to cover your private parts.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your panties and kick them off your [pc.feet].",
					"You pull down [npc.name]'s panties and slide them off [npc.her] [npc.feet].",
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
			1,
			Femininity.FEMININE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"groin_vstring",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							null, null, null)),
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.SHIFTS_ASIDE,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.ANUS),
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							null,
							concealedGenitals))),
			null,
			ColourListPresets.LINGERIE.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the v-string panties before pulling them up to cover your private parts.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] steps into " + clothing.getName(true) + " before pulling them up to cover [npc.her] private parts.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your v-string panties and kick them off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] v-string panties, kicking them off [npc.her] feet.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls your v-string panties down and slides them off your feet.";
				else
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s v-string panties down and slide them off [npc.her] feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You shift your v-string panties to one side.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] shifts [npc.her] v-string panties to one side.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] shifts your v-string panties to one side.";
				else
					return UtilText.parse(clothingOwner, "You shift [npc.name]'s v-string panties to one side.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You move your v-string panties back into their proper place.";
			else
				return UtilText.parse(clothingOwner, "[npc.Name] moves [npc.her] v-string panties back into their proper place.");
		}
	};
	public static AbstractClothingType GROIN_THONG = new AbstractClothingType(200,
			"a",
			false,
			"thong",
			"thongs",
			"A thong with a decorative cross stitched into the front.",
			1,
			Femininity.FEMININE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"groin_thong",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							null,
							null,
							null)),
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.SHIFTS_ASIDE,
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.ANUS),
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							null,
							concealedGenitals))),
			null,
			ColourListPresets.LINGERIE.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the thong before pulling it up to cover your private parts.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] steps into " + clothing.getName(true) + " before pulling it up to cover [npc.her] private parts.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your thong and kick it off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] thong and kicks it off [npc.her] feet.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls your thong down and slides it off your feet.";
				else
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s thong down and slide it off [npc.her] feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You shift your thong to one side.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] shifts [npc.her] thong to one side.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] shifts your thong to one side.";
				else
					return UtilText.parse(clothingOwner, "You shift [npc.name]'s thong to one side.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You move your thong back into its proper place.";
			else
				return UtilText.parse(clothingOwner, "[npc.Name] moves [npc.her] thong back into its proper place.");
		}
	};
	public static AbstractClothingType GROIN_BIKINI = new AbstractClothingType(150,
			"a pair of",
			true,
			"bikini bottoms",
			"bikini bottoms",
			"The part of a bikini that covers the wearer's private parts.",
			1,
			Femininity.FEMININE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"groin_bikini",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							null,
							null,
							null)),
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.SHIFTS_ASIDE,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.ANUS),
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							null,
							concealedGenitals))),
			null,
			ColourListPresets.LINGERIE.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the bikini bottoms before pulling them up to cover your private parts.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] steps into " + clothing.getName(true) + " before pulling them up to cover [npc.her] private parts.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your bikini bottoms and kick them off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] bikini bottoms, kicking them off [npc.her] feet.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls your bikini bottoms down and slides them off your feet.";
				else
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s bikini bottoms down and slide them off [npc.her] feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You shift your bikini bottoms to one side.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] shifts [npc.her] bikini bottoms to one side.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] shifts your bikini bottoms to one side.";
				else
					return UtilText.parse(clothingOwner, "You shift [npc.name]'s bikini bottoms to one side.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You move your bikini bottoms back into their proper place.";
			else
				return UtilText.parse(clothingOwner, "[npc.Name] moves [npc.her] bikini bottoms back into their proper place.");
		}
	};
	public static AbstractClothingType GROIN_BOYSHORTS = new AbstractClothingType(150,
			"a pair of",
			true,
			"boyshorts",
			"boyshorts",
			"Despite their name, these underwear are intended to be worn by women.",
			1,
			Femininity.FEMININE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"groin_boyshorts",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							null,
							null,
							null)),
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.SHIFTS_ASIDE,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.ANUS),
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							null,
							concealedGenitals))),
			null,
			ColourListPresets.LINGERIE.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the boyshorts before pulling them up to cover your private parts.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] steps into " + clothing.getName(true) + " before pulling them up to cover [npc.her] private parts.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your boyshorts and kick them off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] boyshorts, kicking them off [npc.her] feet.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls your boyshorts down and slides them off your feet.";
				else
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s boyshorts down and slide them off [npc.her] feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You shift your boyshorts to one side.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] shifts [npc.her] boyshorts to one side.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] shifts your boyshorts to one side.";
				else
					return UtilText.parse(clothingOwner, "You shift [npc.name]'s boyshorts to one side.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You move your boyshorts back into their proper place.";
			else
				return UtilText.parse(clothingOwner, "[npc.Name] moves [npc.her] boyshorts back into their proper place.");
		}
	};
	
	public static AbstractClothingType GROIN_BRIEFS = new AbstractClothingType(100,
			"a pair of",
			true,
			"briefs",
			"briefs",
			"A pair of men's briefs.",
			1,
			Femininity.MASCULINE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"groin_briefs",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							null,
							null,
							null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.SHIFTS_ASIDE,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.ANUS),
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							null,
							concealedGenitals))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You step into the briefs before pulling them up to your waist.";
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] steps into " + clothing.getName(true) + " before pulling them up to cover [npc.her] waist.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull down your briefs and kick them off your feet.";
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] " + clothing.getName(true) + " before before kicking them off [npc.her] [npc.feet].");
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingRemover, "[npc.Name] pulls down your briefs before sliding them off your [pc.feet].");
				} else {
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s briefs down before before sliding them off [npc.her] [npc.feet].");
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You shift your briefs to one side.";
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] shifts [npc.her] briefs to one side.");
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingRemover, "[npc.Name] shifts your briefs to one side.");
				} else {
					return UtilText.parse(clothingOwner, "You shift [npc.name]'s briefs to one side.");
				}
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You move your briefs back into their proper place.";
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] moves [npc.her] briefs back into their proper place.");
			}
		}
	};
	
	public static AbstractClothingType GROIN_BOXERS = new AbstractClothingType(100,
			"a pair of",
			true,
			"boxer shorts",
			"boxer shorts",
			"A pair of loose-fitting men's underwear.",
			1,
			Femininity.MASCULINE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"groin_boxers",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							null, null, null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.SHIFTS_ASIDE,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.ANUS),
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							null,
							concealedGenitals))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the boxers before pulling them up to cover your private parts.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] steps into " + clothing.getName(true) + " before pulling them up to cover [npc.her] private parts.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your boxers and kick them off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] boxers, kicking them off [npc.her] feet.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls your boxers down and slides them off your feet.";
				else
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s boxers down and slide them off [npc.her] feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You shift your boxers to one side.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] shifts [npc.her] boxers to one side.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] shifts your boxers to one side.";
				else
					return UtilText.parse(clothingOwner, "You shift [npc.name]'s boxers to one side.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You move your boxers back into their proper place.";
			else
				return UtilText.parse(clothingOwner, "[npc.Name] moves [npc.her] boxers back into their proper place.");
		}
	};
	
	public static AbstractClothingType GROIN_BACKLESS_PANTIES = new AbstractClothingType(150,
			"a pair of",
			true,
			"backless panties",
			"backless panties",
			"A large piece of fabric is missing from the rear end of these panties, leaving their wearer's bottom completely exposed.",
			1,
			Femininity.FEMININE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"groin_backless_panties",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							null,
							null, null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.SHIFTS_ASIDE,
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							null, 
							Util.newArrayListOfValues(
									new ListValue<InventorySlot>(InventorySlot.VAGINA),
									new ListValue<InventorySlot>(InventorySlot.PENIS),
									new ListValue<InventorySlot>(InventorySlot.PIERCING_PENIS),
									new ListValue<InventorySlot>(InventorySlot.PIERCING_VAGINA))))),
			null,
			ColourListPresets.LINGERIE.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the panties before pulling them up to cover your private parts.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] steps into " + clothing.getName(true) + " before pulling them up to cover [npc.her] private parts.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your panties and kick them off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] panties, kicking them off [npc.her] feet.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls your panties down and slides them off your feet.";
				else
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s panties down and slide them off [npc.her] feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You shift your panties to one side.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] shifts [npc.her] panties to one side.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] shifts your panties to one side.";
				else
					return UtilText.parse(clothingOwner, "You shift [npc.name]'s panties to one side.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
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
			1,
			Femininity.FEMININE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"groin_crotchless_panties",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							null,
							null,
							null))),
			null,
			ColourListPresets.LINGERIE.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the panties before pulling them up to cover your private parts.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] steps into " + clothing.getName(true) + " before pulling them up to cover [npc.her] private parts.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your panties and kick them off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] panties, kicking them off [npc.her] feet.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls your panties down and slides them off your feet.";
				else
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s panties down and slide them off [npc.her] feet.");
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
			1,
			Femininity.FEMININE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"groin_crotchless_thong",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							null,
							null,
							null))),
			null,
			ColourListPresets.LINGERIE.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the thong, before pulling it up around your waist.",
					"You put the thong on [npc.name].",
					null,
					"[npc.Name] steps into the thong, before pulling it up around [npc.her] waist.",
					"[npc.Name] puts the thong on you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your thong.",
					"You take off [npc.name]'s thong.",
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
			1,
			Femininity.MASCULINE,
			InventorySlot.GROIN,
			Rarity.COMMON,
			null,
			"groin_crotchless_briefs",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							null, null, null)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.SHIFTS_ASIDE,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS)),
									null,
									Util.newArrayListOfValues(
											new ListValue<InventorySlot>(InventorySlot.ANUS))))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You step into the briefs before pulling them up to your waist.";
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] steps into " + clothing.getName(true) + " before pulling them up to cover [npc.her] waist.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull down your briefs and kick them off your feet.";
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] " + clothing.getName(true) + " before before kicking them off [npc.her] [npc.feet].");
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingRemover, "[npc.Name] pulls down your briefs before before sliding them off your [pc.feet].");
				} else {
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s briefs down before before sliding them off [npc.her] [npc.feet].");
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You shift your briefs to one side.";
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] shifts [npc.her] briefs to one side.");
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingRemover, "[npc.Name] shifts your briefs to one side.");
				} else {
					return UtilText.parse(clothingOwner, "You shift [npc.name]'s briefs to one side.");
				}
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You move your briefs back into their proper place.";
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] moves [npc.her] briefs back into their proper place.");
			}
		}
	};

	// SOCK
	
	public static AbstractClothingType SOCK_SOCKS = new AbstractClothingType(50,
			"a pair of",
			true,
			"socks",
			"socks",
			"A pair of standard socks, made from cotton and with an elastic band at the top.",
			1,
			null,
			InventorySlot.SOCK,
			Rarity.COMMON,
			null,
			"sock_socks",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null,
							null,
							null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the socks.",
					"You pull the socks onto [npc.name]'s [npc.feet].",
					"You force the socks onto [npc.name]'s [npc.feet].",
					"[npc.Name] pulls on the socks.",
					"[npc.Name] pushes your [pc.feet] into the socks.",
					"[npc.Name] forces the socks onto your [pc.feet].", null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off your socks.",
					"You pull [npc.name]'s socks off.",
					"You grab [npc.name]'s [npc.feet] and pull [npc.her] socks off.",
					"[npc.Name] pulls off [npc.her] socks.",
					"[npc.Name] pulls off your socks.",
					"[npc.Name] grabs your [pc.feet] and pulls your socks off.", null, null);
		}
	};
	
	public static AbstractClothingType SOCK_TRAINER_SOCKS = new AbstractClothingType(50,
			"a pair of",
			true,
			"trainer socks",
			"trainer socks",
			"A pair of trainer socks, made from cotton and with an elastic band at the top. Their short size allows them to hide neatly inside any footwear.",
			1,
			null,
			InventorySlot.SOCK,
			Rarity.COMMON,
			null,
			"sock_trainer_socks",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null,
							null, null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the socks.",
					"You pull the socks onto [npc.name]'s [npc.feet].",
					"You force the socks onto [npc.name]'s [npc.feet].",
					"[npc.Name] pulls on the socks.",
					"[npc.Name] pushes your [pc.feet] into the socks.",
					"[npc.Name] forces the socks onto your [pc.feet].", null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off your socks.",
					"You pull [npc.name]'s socks off.",
					"You grab [npc.name]'s [npc.feet] and pull [npc.her] socks off.",
					"[npc.Name] pulls off [npc.her] socks.",
					"[npc.Name] pulls off your socks.",
					"[npc.Name] grabs your [pc.feet] and pulls your socks off.", null, null);
		}
	};
	
	public static AbstractClothingType SOCK_KNEEHIGH_SOCKS = new AbstractClothingType(100,
			"a pair of",
			true,
			"knee-high socks",
			"knee-high socks",
			"A pair of knee-high socks, made from cotton and with an elastic band at the top.",
			1,
			Femininity.FEMININE,
			InventorySlot.SOCK,
			Rarity.COMMON,
			null,
			"sock_kneehigh_socks",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET),
									new ListValue<ClothingAccess>(ClothingAccess.CALVES)),
							null,
							null,
							null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the socks.";
			else
				return UtilText.parse(clothingOwner, "[npc.Name] pulls on " + clothing.getName(true) + ".");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your socks.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls off [npc.her] socks.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls off your socks.";
				else
					return UtilText.parse(clothingOwner, "You pull off [npc.name]'s socks.");
			}
		}
	};

	public static AbstractClothingType SOCK_THIGHHIGH_SOCKS = new AbstractClothingType(150,
			"a pair of",
			true,
			"thigh-high socks",
			"thigh-high socks",
			"A pair of thigh-high socks, made from cotton and with an elastic band at the top.",
			1,
			Femininity.FEMININE,
			InventorySlot.SOCK,
			Rarity.COMMON,
			null,
			"sock_thighhigh_socks",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET),
									new ListValue<ClothingAccess>(ClothingAccess.CALVES)),
							null,
							null,
							null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You put on the socks and pull them up to your mid-thigh.",
					"You put the socks on [npc.name] and pull them up to [npc.her] mid-thigh.",
					null,
					"[npc.Name] puts on the socks and pulls them up to [npc.her] mid-thigh.",
					"[npc.Name] puts the socks on your [pc.feet] and pulls them up to your mid-thigh.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off your socks.",
					"You pull off [npc.name]'s socks.",
					null,
					"[npc.Name] pulls off [npc.her] socks.",
					"[npc.Name] pulls off your socks.",
					null, null, null);
		}
	};

	public static AbstractClothingType SOCK_STOCKINGS = new AbstractClothingType(80,
			"a pair of",
			true,
			"stocking",
			"stockings",
			"A pair of nylon stockings, which reach up to the wearer's upper-thigh.",
			1,
			Femininity.FEMININE,
			InventorySlot.SOCK,
			Rarity.COMMON,
			null,
			"sock_stockings",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET),
									new ListValue<ClothingAccess>(ClothingAccess.CALVES)),
							null,
							null,
							null))),
			null,
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You put on the stockings and pull them up to your upper-thigh.",
					"You put the stockings on [npc.name] and pull them up to [npc.her] upper-thigh.",
					null,
					"[npc.Name] puts on the stockings and pulls them up to [npc.her] upper-thigh.",
					"[npc.Name] puts the stockings on your [pc.feet] and pulls them up to your upper-thigh.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off your stockings.",
					"You pull off [npc.name]'s stockings.",
					null,
					"[npc.Name] pulls off [npc.her] stockings.",
					"[npc.Name] pulls off your stockings.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType SOCK_THIGHHIGH_SOCKS_STRIPED = new AbstractClothingType(150,
			"a pair of",
			true,
			"striped thigh-high socks",
			"striped thigh-high socks",
			"A pair of striped thigh-high socks, made from cotton and with an elastic band at the top.",
			1,
			Femininity.FEMININE,
			InventorySlot.SOCK,
			Rarity.COMMON,
			null,
			"sock_thighhigh_socks_striped",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET),
									new ListValue<ClothingAccess>(ClothingAccess.CALVES)),
							null,
							null, null))),
			null,
			ColourListPresets.NOT_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You put on the socks and pull them up to your mid-thigh.",
					"You put the socks on [npc.name] and pull them up to [npc.her] mid-thigh.",
					null,
					"[npc.Name] puts on the socks and pulls them up to [npc.her] mid-thigh.",
					"[npc.Name] puts the socks on your [pc.feet] and pulls them up to your mid-thigh.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off your socks.",
					"You pull off [npc.name]'s socks.",
					null,
					"[npc.Name] pulls off [npc.her] socks.",
					"[npc.Name] pulls off your socks.",
					null, null, null);
		}
	};

	public static AbstractClothingType SOCK_TIGHTS = new AbstractClothingType(100,
			"a pair of",
			true,
			"pantyhose",
			"pantyhose",
			"A pair of pantyhose that reach up to the wearer's lower abdomen.",
			1,
			Femininity.FEMININE,
			InventorySlot.SOCK,
			Rarity.COMMON,
			null,
			"sock_tights",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(new ListValue<ClothingAccess>(
											ClothingAccess.FEET),
											new ListValue<ClothingAccess>(
													ClothingAccess.CALVES),
											new ListValue<ClothingAccess>(
													ClothingAccess.LEGS_UP_TO_GROIN)),
									null,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL)),
									null)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_DOWN,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									null))),

			null,
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You push your feet into the pantyhose and pull them up to your waist.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] pushes [npc.her] feet into " + clothing.getName(true) + " and pulls them up to [npc.her] waist.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your pantyhose.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls off [npc.her] pantyhose.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls off your pantyhose.";
				else
					return UtilText.parse(clothingOwner, "You pull off [npc.name]'s pantyhose.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull the top part of your pantyhose down.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls the top part of [npc.her] pantyhose down.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls the top part of your pantyhose down.";
				else
					return UtilText.parse(clothingOwner, "You pull the top part of [npc.name]'s pantyhose down.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your pantyhose back up to your waist.";
			else
				return UtilText.parse(clothingOwner, "[npc.Name] pulls [npc.her] pantyhose back up to [npc.her] waist.");
		}
	};
	
	public static AbstractClothingType SOCK_FISHNET_STOCKINGS = new AbstractClothingType(150,
			"a pair of",
			true,
			"fishnet stockings",
			"fishnet stockings",
			"A pair of fishnet stockings that reach up to the wearer's mid-thigh.",
			1,
			Femininity.FEMININE,
			InventorySlot.SOCK,
			Rarity.COMMON,
			null,
			"sock_fishnets",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET),
									new ListValue<ClothingAccess>(ClothingAccess.CALVES)),
							null,
							null,
							null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the fishnet stockings.";
			else
				return UtilText.parse(clothingOwner, "[npc.Name] pulls on " + clothing.getName(true) + ".");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your fishnet stockings.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls off [npc.her] fishnet stockings.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls off your fishnet stockings.";
				else
					return UtilText.parse(clothingOwner, "You pull off [npc.name]'s fishnet stockings.");
			}
		}
	};

	// ANKLE

	public static AbstractClothingType ANKLE_BRACELET = new AbstractClothingType(300,
			"an",
			false,
			"anklet",
			"anklets",
			"A delicate ankle bracelet, it consists of a series of metallic orbs threaded onto a fine metal wire.",
			1,
			Femininity.FEMININE,
			InventorySlot.ANKLE,
			Rarity.COMMON,
			null,
			"ankle_bracelet",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CALVES)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CALVES)),
							null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You fasten the anklet around your ankle.";
			else
				return UtilText.parse(clothingOwner, "[npc.Name] fastens " + clothing.getName(true) + " around [npc.her] ankle.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You unfasten and remove your anklet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] unfastens and removes [npc.her] anklet.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] unfastens and removes your anklet.";
				else
					return UtilText.parse(clothingOwner, "You unfasten and remove [npc.name]'s anklet.");
			}
		}
	};

	public static AbstractClothingType ANKLE_SHIN_GUARDS = new AbstractClothingType(250,
			"a pair of",
			true,
			"shin guards",
			"shin guards",
			"A pair of protective shin guards, designed to protect the lower half of the wearer's legs from physical blows.",
			2,
			null,
			InventorySlot.ANKLE,
			Rarity.COMMON,
			null,
			"ankle_shin_guards",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CALVES)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CALVES)),
							null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You fasten the shin guards around your ankles.";
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] fastens " + clothing.getName(true) + " around [npc.her] ankles.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You unfasten and remove your shin guards.";
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] unfastens [npc.her] shin guards.");
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingRemover, "[npc.Name] unfastens and removes your shin guards.");
				} else {
					return UtilText.parse(clothingOwner, "You unfasten and remove [npc.name]'s shin guards.");
				}
			}
		}
	};

	// FOOT
	
	public static AbstractClothingType FOOT_HEELS = new AbstractClothingType(250,
			"a pair of",
			true,
			"heels",
			"heels",
			"A pair of high heels.",
			1,
			Femininity.FEMININE,
			InventorySlot.FOOT,
			Rarity.COMMON,
			null,
			"foot_heels",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You push your [pc.feet] into the heels.",
					"You push [npc.name]'s [npc.feet] into the heels.",
					"You force the heels onto [npc.name]'s [npc.feet].",
					"[npc.Name] pushes [npc.her] [npc.feet] into the heels.",
					"[npc.Name] pushes your [pc.feet] into the heels.",
					"[npc.Name] forces the heels onto your [pc.feet].", null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slip off your heels.",
					"You slip [npc.name]'s heels off.",
					"You grab [npc.name]'s [npc.feet] and pull [npc.her] heels off.",
					"[npc.Name] slips off [npc.her] heels.",
					"[npc.Name] slips off your heels.",
					"[npc.Name] grabs your [pc.feet] and pulls your heels off.", null, null);
		}
	};
	
	public static AbstractClothingType FOOT_STILETTO_HEELS = new AbstractClothingType(500,
			"a pair of",
			true,
			"stiletto heel",
			"stiletto heels",
			"A pair of shoes which have long, thin, high heels, commonly referred to as stiletto heels.",
			1,
			Femininity.FEMININE,
			InventorySlot.FOOT,
			Rarity.COMMON,
			null,
			"foot_stiletto_heels",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You push your [pc.feet] into the heels.",
					"You push [npc.name]'s [npc.feet] into the heels.",
					"You force the heels onto [npc.name]'s [npc.feet].",
					"[npc.Name] pushes [npc.her] [npc.feet] into the heels.",
					"[npc.Name] pushes your [pc.feet] into the heels.",
					"[npc.Name] forces the heels onto your [pc.feet].", null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slip off your heels.",
					"You slip [npc.name]'s heels off.",
					"You grab [npc.name]'s [npc.feet] and pull [npc.her] heels off.",
					"[npc.Name] slips off [npc.her] heels.",
					"[npc.Name] slips off your heels.",
					"[npc.Name] grabs your [pc.feet] and pulls your heels off.", null, null);
		}
	};
	
	public static AbstractClothingType FOOT_CHELSEA_BOOTS = new AbstractClothingType(250,
			"a pair of",
			true,
			"chelsea boots",
			"chelsea boots",
			"A pair of unisex, chelsea-style ankle boots.",
			1,
			null,
			InventorySlot.FOOT,
			Rarity.COMMON,
			null,
			"foot_chelsea_boots",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null))),
			null,
			ColourListPresets.LEATHER.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You push your feet into the ankle boots and zip up the sides.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] pushes [npc.her] feet into " + clothing.getName(true) + " and zips up the sides.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You unzip your ankle boots and pull them off.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] unzips [npc.her] ankle boots and pulls them off.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls off your ankle boots.";
				else
					return UtilText.parse(clothingOwner, "You pull off [npc.name]'s ankle boots.");
			}
		}
	};
	
	public static AbstractClothingType FOOT_ANKLE_BOOTS = new AbstractClothingType(250,
			"a pair of",
			true,
			"ankle boots",
			"ankle boots",
			"A pair of feminine ankle boots.",
			1,
			Femininity.FEMININE,
			InventorySlot.FOOT,
			Rarity.COMMON,
			null,
			"foot_ankle_boots",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null))),
			null,
			ColourListPresets.LEATHER.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You push your feet into the ankle boots and zip up the sides.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] pushes [npc.her] feet into " + clothing.getName(true) + " and zips up the sides.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You unzip your ankle boots and pull them off.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] unzips [npc.her] ankle boots and pulls them off.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls off your ankle boots.";
				else
					return UtilText.parse(clothingOwner, "You pull off [npc.name]'s ankle boots.");
			}
		}
	};

	public static AbstractClothingType FOOT_PLATFORM_BOOTS = new AbstractClothingType(300,
			"a pair of",
			true,
			"platform boots",
			"platform boots",
			"A pair of platform boots.",
			1,
			Femininity.FEMININE,
			InventorySlot.FOOT,
			Rarity.COMMON,
			null,
			"foot_platform_boots",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null))),
			null,
			ColourListPresets.LEATHER.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You push your feet into the platform boots and zip up the sides.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] pushes [npc.her] feet into " + clothing.getName(true) + " and zips up the sides.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You unzip your platform boots and pull them off.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] unzips [npc.her] platform boots and pulls them off.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls off your platform boots.";
				else
					return UtilText.parse(clothingOwner, "You pull off [npc.name]'s platform boots.");
			}
		}
	};
	
	public static AbstractClothingType FOOT_THIGH_HIGH_BOOTS = new AbstractClothingType(750,
			"a pair of",
			true,
			"thigh high boots",
			"thigh high boots",
			"A pair of thigh high boots.",
			1,
			Femininity.FEMININE,
			InventorySlot.FOOT,
			Rarity.COMMON,
			null,
			"foot_thigh_high_boots",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							Util.newArrayListOfValues(
									new ListValue<InventorySlot>(InventorySlot.ANKLE))))),
			null,
			ColourListPresets.LEATHER.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You push your feet into the boots and pull them up to your mid-thigh.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] pushes [npc.her] feet into the boots and pulls them up to mid-thigh.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your thigh high boots.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls off [npc.her] thigh high boots.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls off your thigh high boots.";
				else
					return UtilText.parse(clothingOwner, "You pull off [npc.name]'s thigh high boots.");
			}
		}
	};
	public static AbstractClothingType FOOT_MENS_SMART_SHOES = new AbstractClothingType(500,
			"a pair of",
			true,
			"men's shoes",
			"men's shoes",
			"A pair of smart men's shoes, suitable for all occasions.",
			1,
			Femininity.MASCULINE,
			InventorySlot.FOOT,
			Rarity.COMMON,
			null,
			"foot_mens_smart_shoes",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null))),
			null,
			ColourListPresets.LEATHER.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You push your feet into the shoes and tie up the laces.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] pushes [npc.her] feet into " + clothing.getName(true) + " and ties up the laces.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You untie the laces and pull your shoes off.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] unties [npc.her] laces and pulls [npc.her] shoes off.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls off your shoes.";
				else
					return UtilText.parse(clothingOwner, "You pull off [npc.name]'s shoes.");
			}
		}
	};
	public static AbstractClothingType FOOT_WORK_BOOTS = new AbstractClothingType(600,
			"a pair of",
			true,
			"work boots",
			"work boots",
			"A pair of heavy, steel-capped work boots.",
			2,
			Femininity.MASCULINE,
			InventorySlot.FOOT,
			Rarity.COMMON,
			null,
			"foot_work_boots",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null))),
			null,
			ColourListPresets.LEATHER.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You push your feet into the boots and tie up the laces.";
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] pushes [npc.her] feet into " + clothing.getName(true) + " and ties up the laces.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You untie the laces and pull your boots off.";
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] unties [npc.her] laces and pulls [npc.her] boots off.");
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingRemover, "[npc.Name] pulls your boots off.");
				} else {
					return UtilText.parse(clothingOwner, "You pull off [npc.name]'s boots.");
				}
			}
		}
	};
	
	public static AbstractClothingType FOOT_TRAINERS = new AbstractClothingType(
			500,
			"a pair of",
			true,
			"trainers",
			"trainers",
			"A pair of unisex trainers, ideal for any kind of exercise.",
			1,
			null,
			InventorySlot.FOOT,
			Rarity.COMMON,
			null,
			"foot_trainers",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)), null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You push your [pc.feet] into the trainers and tie up the laces.",
					"You push [npc.name]'s [npc.feet] into the trainers and tie up the laces.",
					"You force the trainers onto [npc.name]'s [npc.feet].",
					"[npc.Name] pushes [npc.her] [npc.feet] into the trainers and ties up the laces.",
					"[npc.Name] pushes your [pc.feet] into the trainers and ties up the laces.",
					"[npc.Name] forces the trainers onto your [pc.feet].", null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slip off your trainers.",
					"You slip [npc.name]'s trainers off.",
					"You grab [npc.name]'s [npc.feet] and pull [npc.her] trainers off.",
					"[npc.Name] slips off [npc.her] trainers.",
					"[npc.Name] slips off your trainers.",
					"[npc.Name] grabs your [pc.feet] and pulls your trainers off.", null, null);
		}
	};
	
	public static AbstractClothingType FOOT_LOW_TOP_SKATER_SHOES = new AbstractClothingType(350,
			"a pair of",
			true,
			"low-top skater shoe",
			"low-top skater shoes",
			"A pair of low-top skater shoes, suitable for casual wear.",
			1,
			null,
			InventorySlot.FOOT,
			Rarity.COMMON,
			null,
			"foot_low_top_skater_shoes",
			null,
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)), null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
			@Override
			public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
						"You push your [pc.feet] into the shoes and tie up the laces.",
						"You push [npc.name]'s [npc.feet] into the shoes and tie up the laces.",
						"You force the shoes onto [npc.name]'s [npc.feet].",
						"[npc.Name] pushes [npc.her] [npc.feet] into the shoes and ties up the laces.",
						"[npc.Name] pushes your [pc.feet] into the shoes and ties up the laces.",
						"[npc.Name] forces the shoes onto your [pc.feet].", null, null);
			}

			@Override
			public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
						"You slip off your shoes.",
						"You slip [npc.name]'s shoes off.",
						"You grab [npc.name]'s [npc.feet] and pull [npc.her] shoes off.",
						"[npc.Name] slips off [npc.her] shoes.",
						"[npc.Name] slips off your shoes.",
						"[npc.Name] grabs your [pc.feet] and pulls your shoes off.", null, null);
			}
	};
	
	public static AbstractClothingType PENIS_CONDOM = new AbstractClothingType(20,
			"a",
			false,
			"condom",
			"condoms",
			"A sheath-shaped rubbery barrier device, designed to fit over a penis and prevent any semen from entering the body of a sexual partner.",
			1,
			null,
			InventorySlot.PENIS,
			Rarity.COMMON,
			null,
			"penis_condom_unequipped",
			"penis_condom_equipped",
			null,
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							null,
							null, null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			null) {
		
		@Override
		public boolean isDiscardedOnUnequip() {
			return true;
		}

		@Override
		public boolean isAbleToBeEquippedDuringSex() {
			return true;
		}
		
		@Override
		public boolean isCanBeEquipped(GameCharacter clothingOwner) {
			if(clothingOwner.hasPenis()) {
				return true;
			} else {
				return false;
			}
		}
		
		@Override
		public String getCannotBeEquippedText(GameCharacter characterClothingOwner) {
			if(characterClothingOwner.isPlayer()) {
				return "You don't have a penis, so you can't wear a condom!";
			} else {
				return UtilText.parse(characterClothingOwner, "[npc.Name] doesn't have a penis, so can't wear a condom!");
			}
		}
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			
			if(applyEffects) {
				if(InventoryDialogue.getInventoryNPC()!=null) {
					return ((NPC) InventoryDialogue.getInventoryNPC()).getCondomEquipEffects(clothingRemover, clothingOwner, rough);
				}
			}
			
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You tear open the packet and roll the condom down the length of your [pc.penis].",
					"You tear open the packet and roll the condom down the length of [npc.name]'s [npc.penis].",
					"You tear open the packet and forcefully roll the condom down the length [npc.name]'s [npc.penis].",
					"[npc.Name] tears open the packet and rolls the condom down the length of [npc.her] [npc.penis].",
					"[npc.Name] tears open the packet and rolls the condom down the length of your [pc.penis].",
					"[npc.Name] tears open the packet and forcefully rolls the condom down the length of your [pc.penis].", null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slip off your condom and throw it away.",
					"You slip [npc.name]'s condom off and throw it away.",
					"You grab [npc.name]'s [npc.penis] and pull [npc.her] condom off, before throwing it away.",
					"[npc.Name] slips off [npc.her] condom and throws it away.",
					"[npc.Name] slips off your condom and throws it away.",
					"[npc.Name] grabs your [pc.penis] and pulls your condom off, before throwing it away.", null, null);
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
			1,
			Femininity.FEMININE,
			InventorySlot.HEAD,
			Rarity.EPIC,
			ClothingSet.MAID,
			"maidHeadband",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null,
							null,
							null))),
			null,
			ColourListPresets.MAID.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide the headpiece into place.",
					"You slide the headpiece onto [npc.name]'s head.",
					null,
					"[npc.Name] slides the headpiece into place.",
					"[npc.Name] slides the headpiece onto your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your headpiece.",
					"You pull off [npc.name]'s headpiece.",
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
			1,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.EPIC,
			ClothingSet.MAID,
			"maidDress",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0))),
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
						new BlockedParts(
								DisplacementType.REMOVE_OR_EQUIP,
								Util.newArrayListOfValues(
										new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
										new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
								Util.newArrayListOfValues(
										new ListValue<CoverableArea>(CoverableArea.BREASTS),
										new ListValue<CoverableArea>(CoverableArea.NIPPLES),
										new ListValue<CoverableArea>(CoverableArea.STOMACH),
										new ListValue<CoverableArea>(CoverableArea.BACK)),
								Util.newArrayListOfValues(
										new ListValue<ClothingAccess>(ClothingAccess.CHEST),
										new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
								concealedPartialTorso)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_UP,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)), 
									concealedGroin))),
			Util.newArrayListOfValues(new ListValue<InventorySlot>(InventorySlot.LEG)),
			ColourListPresets.MAID.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the dress and pull it up over your torso, zipping yourself up at the back before making sure the trimmings and apron are neatly arranged.",
					"You get [npc.name] to step into the dress, before pulling it up over [npc.her] torso. Zipping up at the back, you then make sure that the trimmings and apron are neatly arranged.",
					null,
					"[npc.Name] steps into the dress and pulls it up over [npc.her] torso, zipping [npc.herself] up at the back before making sure the trimmings and apron are neatly arranged.",
					"[npc.Name] gets you to step into the dress, before pulling it up over your torso. Zipping you up at the back, [npc.she] then makes sure that the trimmings and apron are neatly arranged.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You reach back and unzip your Maid's dress, pulling your arms out before sliding it down your body and stepping out.",
					"You unzip the back of [npc.name]'s Maid's dress, before pulling [npc.her] [npc.arms] free to allow the dress to fall to the floor.",
					null,
					"[npc.Name] reaches back and unzips [npc.her] Maid's dress, pulling [npc.her] arms out before sliding it down [npc.her] body and stepping out.",
					"[npc.Name] unzips the back of your Maid's dress, before pulling your arms free to allow the dress to fall to the floor.",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (dt == DisplacementType.PULLS_DOWN) {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You unzip the back of your dress and pull down the top half.";
				else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
					return UtilText.parse(clothingOwner, "[npc.Name] unzips the back of [npc.her] dress and pulls the top half down");
				else {
					if (clothingOwner.isPlayer())
						return "[npc.Name] unzips the back of your dress and pulls the top half down.";
					else
						return UtilText.parse(clothingOwner, "You unzip the back of [npc.name]'s dress and pull the top half down.");
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
						return UtilText.parse(clothingOwner, "You pull up the skirt of [npc.name]'s dress.");
				}
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
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
			1,
			Femininity.FEMININE,
			InventorySlot.SOCK,
			Rarity.EPIC,
			ClothingSet.MAID,
			"maidStockings",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET),
									new ListValue<ClothingAccess>(ClothingAccess.CALVES)),
							null, null, null))),
			null,
			ColourListPresets.MAID.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the stockings.",
					"You pull the stockings onto [npc.name]'s [npc.feet].",
					null,
					"[npc.Name] pulls on the stockings.",
					"[npc.Name] pulls the stockings onto your [pc.feet].",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off your stockings.",
					"You pull off [npc.name]'s stockings.",
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
			1,
			Femininity.FEMININE,
			InventorySlot.FOOT,
			Rarity.EPIC,
			ClothingSet.MAID,
			"maidHeels",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null, null, null))),
			null,
			ColourListPresets.MAID.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_STEEL.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slip on the heels and buckle up the straps.",
					"You pull the heels onto [npc.name]'s [npc.feet] and buckle up the straps.",
					null,
					"[npc.Name] pulls on the heels and buckles up the straps.",
					"[npc.Name] pulls the heels onto your [pc.feet] and buckles up the straps.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unbuckle your heels and slip them off.",
					"You unbuckle [npc.name]'s heels and pull them off.",
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
			1,
			Femininity.FEMININE,
			InventorySlot.HAND,
			Rarity.EPIC,
			ClothingSet.MAID,
			"maidGloves",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.WRISTS)),
							null, null, null))),
			null,
			ColourListPresets.MAID.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the sleeves.",
					"You pull the sleeves onto [npc.name]'s [npc.arms].",
					null,
					"[npc.Name] pulls on the sleeves.",
					"[npc.Name] pulls the sleeves onto your [pc.arms].",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off your sleeves.",
					"You pull off [npc.name]'s sleeves.",
					null,
					"[npc.Name] pulls [npc.her] sleeves off.",
					"[npc.Name] pulls your sleeves off.",
					null, null, null);
		}
	};

	// BDSM:
	
	public static AbstractClothingType NECK_SLAVE_COLLAR = new AbstractClothingType(2500,
			"a",
			false,
			"metal collar",
			"metal collars",
			"A heavy metal collar, of the type typically worn by slaves.",
			2,
			null,
			InventorySlot.NECK,
			Rarity.EPIC,
			ClothingSet.BDSM,
			"bdsm_neck_metal_collar",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ENSLAVEMENT, TFModifier.ARCANE_BOOST, TFPotency.BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SEALING, TFModifier.ARCANE_BOOST, TFPotency.BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_SPELLS, TFPotency.MAJOR_DRAIN, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_SPELLS, TFPotency.MAJOR_DRAIN, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_SPELLS, TFPotency.MAJOR_DRAIN, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_SPELLS, TFPotency.MAJOR_DRAIN, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_SPELLS, TFPotency.MAJOR_DRAIN, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_LUST, TFPotency.MAJOR_DRAIN, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_LUST, TFPotency.MAJOR_DRAIN, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_LUST, TFPotency.MAJOR_DRAIN, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_LUST, TFPotency.MAJOR_DRAIN, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_LUST, TFPotency.MAJOR_DRAIN, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.SPELL_COST_MODIFIER, TFPotency.MAJOR_DRAIN, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.SPELL_COST_MODIFIER, TFPotency.MAJOR_DRAIN, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.SPELL_COST_MODIFIER, TFPotency.MAJOR_DRAIN, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.SPELL_COST_MODIFIER, TFPotency.MAJOR_DRAIN, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.SPELL_COST_MODIFIER, TFPotency.MAJOR_DRAIN, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							null, null, null, null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			ColourListPresets.JUST_STEEL.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_FINCH))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You clasp the heavy metal collar around your neck.",
					"You clasp the heavy metal collar around [npc.name]'s neck.",
					null,
					"[npc.Name] clasps the heavy metal collar around [npc.her] neck.",
					"[npc.Name] clasps the heavy metal collar around your neck.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your metal collar.",
					"You take off [npc.name]'s metal collar.",
					null,
					"[npc.Name] takes [npc.her] metal collar off.",
					"[npc.Name] takes your metal collar off.",
					null, null, null);
		}
		
//		@Override
//		public int getEnchantmentLimit() {
//			return 17;
//		}
	};
	
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
			ClothingSet.BDSM,
			"bdsm_mouth_ballgag",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SEALING, TFModifier.ARCANE_BOOST, TFPotency.BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_DRAIN, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.MOUTH)),
							null, null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_STEEL.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_FINCH))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You open your mouth wide and slide in the ball gag, before reaching back and fastening the buckles to keep it in place.</br>"
							+ "[style.colourArcane(The ball gag glows with arcane energy as it reveals its jinx!)]",
					"You push the ball gag into [npc.name]'s mouth, before reaching back and fastening the buckles to keep it in place.</br>"
							+ "[style.colourArcane(The ball gag glows with arcane energy as it reveals its jinx!)]",
					null,
					"[npc.Name] opens [npc.her] mouth wide and slides in the ball gag, before reaching back and fastening the buckles to keep it in place.</br>"
							+ "[style.colourArcane(The ball gag glows with arcane energy as it reveals its jinx!)]",
					"[npc.Name] pushes the ball gag into your mouth, before reaching back and fastening the buckles to keep it in place.</br>"
							+ "[style.colourArcane(The ball gag glows with arcane energy as it reveals its jinx!)]",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unfasten the ball gag's straps and take it out of your mouth.",
					"You unfasten the ball gag's straps and take it out of [npc.name]'s mouth.",
					null,
					"[npc.Name] unfastens the ball gag's straps and takes it out of [npc.her] mouth.",
					"[npc.Name] unfastens the ball gag's straps and takes it out of your mouth.",
					null, null, null);
		}
		
		@Override
		public boolean isMufflesSpeech() {
			return true;
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
			ClothingSet.BDSM,
			"bdsm_mouth_ringgag",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SEALING, TFModifier.ARCANE_BOOST, TFPotency.BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_DRAIN, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							null,
							null,
							null, null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_STEEL.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_FINCH))){
		

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You open your mouth wide and slide in the ring gag, before reaching back and fastening the buckles to keep it in place.",
					"You push the ring gag into [npc.name]'s mouth, before reaching back and fastening the buckles to keep it in place.",
					null,
					"[npc.Name] opens [npc.her] mouth wide and slides in the ring gag, before reaching back and fastening the buckles to keep it in place.",
					"[npc.Name] pushes the ring gag into your mouth, before reaching back and fastening the buckles to keep it in place.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unfasten the ring gag's straps and take it out of your mouth.",
					"You unfasten the ring gag's straps and take it out of [npc.name]'s mouth.",
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
			ClothingSet.BDSM,
			"bdsm_neck_choker",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SEALING, TFModifier.ARCANE_BOOST, TFPotency.BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_DRAIN, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							null,
							null,
							null, null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_FINCH))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You wrap the choker around your neck and fasten the buckle.",
					"You wrap the choker around [npc.name]'s neck and fasten the buckle.",
					null,
					"[npc.Name] wraps the choker around [npc.her] neck and fastens the buckle.",
					"[npc.Name] wraps the choker around your neck and fastens the buckle.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unfasten the choker's buckle and remove it from around your neck.",
					"You unfasten the choker's buckle and remove it from around [npc.name]'s neck.",
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
			"A pair of leather wrist restraints.",
			0,
			null,
			InventorySlot.WRIST,
			Rarity.EPIC,
			ClothingSet.BDSM,
			"bdsm_wrist_restraints",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SEALING, TFModifier.ARCANE_BOOST, TFPotency.BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_DRAIN, 0))),
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(
					DisplacementType.REMOVE_OR_EQUIP,
					Util.newArrayListOfValues(
							new ListValue<ClothingAccess>(ClothingAccess.WRISTS)),
					null,
					Util.newArrayListOfValues(
							new ListValue<ClothingAccess>(ClothingAccess.WRISTS)), null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			ColourListPresets.JUST_STEEL.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_FINCH))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You buckle the restraints around your wrists.",
					"You buckle the restraints around [npc.name]'s wrists.",
					null,
					"[npc.Name] buckles the restraints around [npc.her] wrists.",
					"[npc.Name] buckles the restraints around your wrists.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unfasten the restraints and remove them from your wrists.",
					"You unfasten the restraints and remove them from [npc.name]'s wrists.",
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
			ClothingSet.BDSM,
			"bdsm_ankle_spreaderbar",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SEALING, TFModifier.ARCANE_BOOST, TFPotency.BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_DRAIN, 0))),
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(
					DisplacementType.REMOVE_OR_EQUIP,
					Util.newArrayListOfValues(
							new ListValue<>(ClothingAccess.CALVES)),
					null,
					Util.newArrayListOfValues(
							new ListValue<>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
							new ListValue<>(ClothingAccess.LEGS_UP_TO_GROIN),
							new ListValue<>(ClothingAccess.CALVES)), null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			ColourListPresets.JUST_STEEL.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_FINCH))){
		

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You wrap the spreader bar's restraints around your ankles and fasten the straps, leaving your [pc.legs] wide open.",
					"You wrap the spreader bar's restraints around [npc.name]'s ankles and fasten the straps, leaving [npc.her] [npc.legs] wide open.",
					null,
					"[npc.Name] wraps the spreader bar's restraints around [npc.her] ankles and fastens the straps, leaving [npc.her] [npc.legs] wide open.",
					"[npc.Name] wraps the spreader bar's restraints around your ankles and fastens the straps, leaving your [pc.legs] wide open.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unfasten the spreader bar's restraints and remove it from around your ankles.",
					"You unfasten the spreader bar's restraints and remove it from around [npc.name]'s ankles.",
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
			ClothingSet.BDSM,
			"bdsm_groin_chastity_belt",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SEALING, TFModifier.ARCANE_BOOST, TFPotency.BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_DRAIN, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							null, null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			ColourListPresets.JUST_STEEL.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			ColourListPresets.JUST_GOLD.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_FINCH))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the chastity belt and clip the locks into place.",
					"You pull the chastity belt up around [npc.name]'s groin, before clipping the locks into place.",
					null,
					"[npc.Name] pulls on the chastity belt and clips the locks into place.",
					"[npc.Name] pulls the chastity belt up around your groin, before clipping the locks into place.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unfasten the chastity belt's locks and take it off.",
					"You unfasten the chastity belt's locks and take it off of [npc.Name]'s groin.",
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
			ClothingSet.BDSM,
			"bdsm_groin_chastity_belt_full",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SEALING, TFModifier.ARCANE_BOOST, TFPotency.BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_DRAIN, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA),
									new ListValue<CoverableArea>(CoverableArea.ANUS)),
							null, null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			ColourListPresets.JUST_STEEL.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			ColourListPresets.JUST_GOLD.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_FINCH))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the chastity belt and clip the locks into place.",
					"You pull the chastity belt up around [npc.name]'s groin, before clipping the locks into place.",
					null,
					"[npc.Name] pulls on the chastity belt and clips the locks into place.",
					"[npc.Name] pulls the chastity belt up around your groin, before clipping the locks into place.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unfasten the chastity belt's locks and take it off.",
					"You unfasten the chastity belt's locks and take it off of [npc.Name]'s groin.",
					null,
					"[npc.Name] unfastens the chastity belt's locks and takes it off.",
					"[npc.Name] unfastens the chastity belt's locks and takes it off of your groin.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType BDSM_CHASTITY_CAGE = new AbstractClothingType(250,
			"a",
			false,
			"chastity cage",
			"chastity cages",
			"A little cage designed to imprison a cock.",
			0,
			null,
			InventorySlot.PENIS,
			Rarity.EPIC,
			ClothingSet.BDSM,
			"bdsm_groin_chastityCage",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SEALING, TFModifier.ARCANE_BOOST, TFPotency.BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_DRAIN, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.PENIS)),
							null, null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			ColourListPresets.JUST_GOLD.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			ColourListPresets.JUST_STEEL.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_FINCH))){

		@Override
		public boolean isCanBeEquipped(GameCharacter clothingOwner) {
			return clothingOwner.hasPenis();
		}	

		@Override
		public String getCannotBeEquippedText(GameCharacter clothingOwner) {
			if(clothingOwner.isPlayer()) {
				return "You don't have a penis, so you can't wear the chastity cage!";
				
			} else {
				return UtilText.parse(clothingOwner,
						"[npc.Name] doesn't have a penis, so [npc.she] can't wear the chastity cage!");
				
			}
		}
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide the chastity cage down over your [pc.cock] and clip it into place.",
					"You slide the chastity cage down over [npc.name]'s [npc.cock] and clip it into place.",
					null,
					"[npc.Name] slides the chastity cage down over [npc.her] [npc.cock] and clips it into place.",
					"[npc.Name] slides the chastity cage down over your [pc.cock] and clips it into place.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unclip the chastity cage and take it off.",
					"You unclip [npc.name]'s chastity cage and take it off.",
					null,
					"[npc.Name] unclips [npc.her] chastity cage and takes it off.",
					"[npc.Name] unclips your chastity cage and takes it off.",
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
			ClothingSet.BDSM,
			"bdsm_stomach_karada",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SEALING, TFModifier.ARCANE_BOOST, TFPotency.BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_DRAIN, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.CHEST),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
									new ListValue<ClothingAccess>(ClothingAccess.CHEST),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)), null))),
			null,
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_FINCH))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You hang the rope around your neck, and as you do so, it snakes and slides around your body with a mind of its own, forming a tight karada within a matter of seconds.",
					"You hang the rope around [npc.name]'s neck, watching as it snakes and slides around [npc.her] body with a mind of its own, forming a tight karada within a matter of seconds.",
					null,
					"[npc.Name] hangs the rope around [npc.her] neck, smiling as it snakes and slides around [npc.her] body with a mind of its own, forming a tight karada within a matter of seconds.",
					"[npc.Name] hangs the rope around your neck, smiling as it snakes and slides around your body with a mind of its own, forming a tight karada within a matter of seconds.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You untie the karada and take it off.",
					"You untie [npc.name]'s karada and take it off.",
					null,
					"[npc.Name] unties [npc.her] karada and takes it off.",
					"[npc.Name] unties your karada and takes it off.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType ENFORCER_SHIRT = new AbstractClothingType(1500,
			"an",
			false,
			"Enforcer's shirt",
			"Enforcer's shirts",
			"An Enforcer's shirt, it comes with what appears to be a stab-proof vest.",
			10,
			null,
			InventorySlot.TORSO_UNDER,
			Rarity.EPIC,
			ClothingSet.ENFORCER,
			"enforcerShirt",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_PHYSICAL, TFPotency.MAJOR_BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BACK)),
							null,
							null)),
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.UNBUTTONS,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BREASTS),
									new ListValue<CoverableArea>(CoverableArea.NIPPLES),
									new ListValue<CoverableArea>(CoverableArea.STOMACH)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
							concealedPartialTorso))),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.CLOTHING_BLUE),
					new ListValue<Colour>(Colour.CLOTHING_BLACK),
					new ListValue<Colour>(Colour.CLOTHING_PINK)),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_STEEL.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null){
		

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the shirt and button it up.",
					"You put the shirt onto [npc.name] and button it up.",
					null,
					"[npc.Name] pulls on the shirt and buttons it up.",
					"[npc.Name] puts the shirt onto you and buttons it up.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unbutton your shirt and pull it off.",
					"You unbutton [npc.name]'s shirt and pull it off.",
					null,
					"[npc.Name] unbuttons [npc.her] shirt and pulls it off.",
					"[npc.Name] unbuttons your shirt and pull it off.",
					null, null, null);
		}
	};

	public static AbstractClothingType ENFORCER_SHORTS = new AbstractClothingType(600,
			"a pair of",
			true,
			"Enforcer's shorts",
			"Enforcer's shorts",
			"A pair of shorts, they come with a utility belt.",
			2,
			null,
			InventorySlot.LEG,
			Rarity.EPIC,
			ClothingSet.ENFORCER,
			"enforcerShorts",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_PHYSICAL, TFPotency.MAJOR_BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)),
									null,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)),
									null)),
					new ListValue<BlockedParts>(new BlockedParts(
									DisplacementType.PULLS_DOWN,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedGroin)),
					new ListValue<BlockedParts>(new BlockedParts(
									DisplacementType.UNZIPS,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.PENIS)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedUnzipsGroin))),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.CLOTHING_BLUE),
					new ListValue<Colour>(Colour.CLOTHING_BLACK),
					new ListValue<Colour>(Colour.CLOTHING_PINK)),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_STEEL.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null) {
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the shorts before pulling them up to your waist.",
					"You pull the shorts all the way up [npc.name]'s [npc.legs] to [npc.her] waist.",
					null,
					"[npc.Name] steps into the shorts before pulling them up to [npc.her] waist.",
					"[npc.Name] pull the shorts all the way up your [pc.legs] to your waist.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your shorts and kick them off your [pc.feet].",
					"You pull [npc.name]'s shorts down and slide them off [npc.her] [npc.feet].",
					null,
					"[npc.Name] pulls [npc.her] shorts down and kicks them off [npc.her] [npc.feet].",
					"[npc.Name] pulls your shorts down and slides them off your [pc.feet].",
					null, null, null);
		}
	};
	
	public static AbstractClothingType ENFORCER_MINI_SKIRT = new AbstractClothingType(600,
			"an",
			false,
			"Enforcer's miniskirt",
			"Enforcer's miniskirts",
			"A very short skirt, equipped with a utility belt, which barely reaches down to mid-thigh.",
			1,
			Femininity.FEMININE,
			InventorySlot.LEG,
			Rarity.EPIC,
			ClothingSet.ENFORCER,
			"enforcer_miniskirt",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_PHYSICAL, TFPotency.MAJOR_BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.ANUS),
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							concealedGroin))),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.CLOTHING_BLUE),
					new ListValue<Colour>(Colour.CLOTHING_BLACK),
					new ListValue<Colour>(Colour.CLOTHING_PINK)),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_STEEL.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You step into the miniskirt before pulling it up to your waist.",
					"You slide the miniskirt up [npc.name]'s [npc.legs] to rest around [npc.her] waist.",
					null,
					"[npc.Name] steps into the miniskirt before pulling it up to [npc.her] waist.",
					"[npc.Name] slides the miniskirt up your [pc.legs] to rest around your waist.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull down your miniskirt and kick it off your [pc.feet].",
					"You pull [npc.name]'s miniskirt down and slide it off [npc.her] [npc.feet].",
					null,
					"[npc.Name] pulls down [npc.her] miniskirt before kicking it off [npc.her] [npc.feet].",
					"[npc.Name] pulls your miniskirt down and slides it off your [pc.feet].",
					null, null, null);
		}
	};
	
	public static AbstractClothingType CATTLE_PIERCING_EAR_TAGS = new AbstractClothingType(150,
			"an",
			false,
			"ear tag",
			"ear tags",
			"A bright yellow ear tag, made from plastic and used for identification of domestic animals.",
			0,
			null,
			InventorySlot.PIERCING_EAR,
			Rarity.EPIC,
			ClothingSet.CATTLE,
			"piercing_livestock_tags",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							null, null, null, null))),
			null,
			ColourListPresets.JUST_YELLOW.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_RED.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_FINCH))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You clip the tag into place.",
					"You clip [npc.name]'s new tag into place.",
					null,
					"[npc.Name] clips [npc.her] tag into place.",
					"[npc.Name] clips your new tag into place.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unclip your tag.",
					"You unclip [npc.name]'s tag.",
					null,
					"[npc.Name] unclips [npc.her] tag.",
					"[npc.Name] unclips your tag.",
					null, null, null);
		}
	};

	public static AbstractClothingType CATTLE_PIERCING_NOSE_BOVINE_RING = new AbstractClothingType(250,
			"a",
			false,
			"bovine nose ring",
			"bovine nose rings",
			"A large nose ring, similar to one worn by cows and bulls.",
			0,
			null,
			InventorySlot.PIERCING_NOSE,
			Rarity.EPIC,
			ClothingSet.CATTLE,
			"piercing_nose_cow_ring",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							null, null, null, null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_FINCH))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide the nose ring into place.",
					"You slide [npc.name]'s new nose ring into place.",
					null,
					"[npc.Name] slides [npc.her] nose ring into place.",
					"[npc.Name] slides your new nose ring into place.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide the nose ring out.",
					"You slide [npc.name]'s nose ring out.",
					null,
					"[npc.Name] slides [npc.her] nose ring out.",
					"[npc.Name] slides your nose ring out.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType CATTLE_NECK_COWBELL_COLLAR  = new AbstractClothingType(600,
			"a",
			false,
			"cowbell collar",
			"cowbell collars",
			"A leather collar, with a cowbell attached to the front.",
			0,
			null,
			InventorySlot.NECK,
			Rarity.EPIC,
			ClothingSet.CATTLE,
			"neck_cowbell_collar",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							null, null, null, null))),
			null,
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_STEEL.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_FINCH))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You put on the cowbell collar, before fastening the clasp at the back of your neck in order to hold it in place.",
					"You put the cowbell collar on [npc.name], before fastening the clasp at the back of [npc.her] neck in order to hold it in place.",
					null,
					"[npc.Name] puts on the cowbell collar, before fastening the clasp at the back of [npc.her] neck in order to hold it in place.",
					"[npc.Name] puts the cowbell collar on you, before fastening the clasp at the back of your neck in order to hold it in place.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unfasten the cowbell collar and take it off.",
					"You unfasten [npc.name]'s cowbell collar and remove it from [npc.her] neck.",
					null,
					"[npc.Name] unfastens [npc.her] cowbell collar and takes it off.",
					"[npc.Name] unfastens your cowbell collar and removes it from around your neck.",
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
			1,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.EPIC,
			ClothingSet.MILK_MAID,
			"milk_maid_dress",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.STOMACH),
									new ListValue<CoverableArea>(CoverableArea.BACK)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
							concealedPartialTorso)),
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.ANUS),
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							concealedGroin)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_DOWN,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.BREASTS),
											new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									Util.newArrayListOfValues(
											new ListValue<InventorySlot>(InventorySlot.CHEST),
											new ListValue<InventorySlot>(InventorySlot.NIPPLE),
											new ListValue<InventorySlot>(InventorySlot.PIERCING_NIPPLE))))),
			Util.newArrayListOfValues(new ListValue<InventorySlot>(InventorySlot.LEG)),
			ColourListPresets.MILK_MAID.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
	};
	
	public static AbstractClothingType MILK_MAID_HEADBAND = new AbstractClothingType(400,
			"a",
			false,
			"Milk Maid's headband",
			"Milk Maid's headbands",
			"A frilly headband, with lots of decorative lace attached to the top.",
			1,
			Femininity.FEMININE,
			InventorySlot.HEAD,
			Rarity.EPIC,
			ClothingSet.MILK_MAID,
			"milk_maid_headband",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null, null, null))),
			null,
			ColourListPresets.MILK_MAID.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
			@Override
			public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
						"You pull on the headband.",
						"You push the headband onto [npc.name]'s head.",
						null,
						"[npc.Name] pulls the headband onto [npc.her] head.",
						"[npc.Name] pushes the headband onto your head.",
						null, null, null);
			}

			@Override
			public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
						"You take off your headband.",
						"You take off [npc.name]'s headband.",
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
			1,
			null,
			InventorySlot.HEAD,
			Rarity.EPIC,
			ClothingSet.MILK_MAID,
			"milk_maid_kerchief",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null, null, null))),
			null,
			ColourListPresets.JUST_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You place the kerchief on your head, before tying up the strings beneath your chin.",
					"You place the kerchief on [npc.name]'s head, before tying up the strings beneath [npc.her] chin.",
					null,
					"[npc.Name] places the kerchief on [npc.her] head, before tying up the strings beneath [npc.her] chin.",
					"[npc.Name] places the kerchief on your head, before tying up the strings beneath your chin.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your kerchief.",
					"You take off [npc.name]'s kerchief.",
					null,
					"[npc.Name] takes [npc.her] kerchief off.",
					"[npc.Name] takes your kerchief off.",
					null, null, null);
		}
	};

	public static AbstractClothingType SOCK_RAINBOW_STOCKINGS = new AbstractClothingType(250,
			"a pair of",
			true,
			"rainbow stockings",
			"rainbow stockings",
			"A pair of brightly coloured rainbow stockings that reach up to mid-thigh.",
			1,
			Femininity.FEMININE,
			InventorySlot.SOCK,
			Rarity.EPIC,
			ClothingSet.RAINBOW,
			"sock_rainbow_stockings",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.BOOST, 0))),
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET),
									new ListValue<ClothingAccess>(ClothingAccess.CALVES)),
							null, null, null))),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.CLOTHING_MULTICOLOURED)),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You push your feet into the rainbow stockings and pull them up to your thighs.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] pushes [npc.her] feet into " + clothing.getName(true) + " and pulls them up to [npc.her] thighs.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your rainbow stockings.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls off [npc.her] rainbow stockings.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls off your rainbow stockings.";
				else
					return UtilText.parse(clothingOwner, "You pull off [npc.name]'s rainbow stockings.");
			}
		}
	};
	
	public static AbstractClothingType HAND_RAINBOW_FINGERLESS_GLOVES = new AbstractClothingType(250,
			"a pair of",
			true,
			"rainbow glove",
			"rainbow gloves",
			"A pair of brightly-coloured rainbow fingerless gloves.",
			1,
			Femininity.FEMININE,
			InventorySlot.HAND,
			Rarity.EPIC,
			ClothingSet.RAINBOW,
			"hand_rainbow_fingerless_gloves",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.BOOST, 0))),
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FINGERS),
									new ListValue<ClothingAccess>(ClothingAccess.WRISTS)),
							null,
							null, null))),
			null,
			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.CLOTHING_MULTICOLOURED)),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the gloves and give your fingers an experimental wiggle.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] pulls on " + clothing.getName(true) + " and gives [npc.her] fingers an experimental wiggle.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your gloves.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls off [npc.her] gloves.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls your gloves off.";
				else
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s gloves off.");
			}
		}
	};
	
	public static AbstractClothingType MEGA_MILK = new AbstractClothingType(400,
			"a",
			false,
			"Mega Milk T-shirt",
			"Mega Milk T-shirts",
			"A T-shirt with the words 'Mega Milk' written on the front.",
			1,
			null,
			InventorySlot.TORSO_UNDER,
			Rarity.UNCOMMON,
			null,
			"torso_tshirt_megamilk",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null,
							null, null)),
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BREASTS),
									new ListValue<CoverableArea>(CoverableArea.NIPPLES),
									new ListValue<CoverableArea>(CoverableArea.STOMACH),
									new ListValue<CoverableArea>(CoverableArea.BACK)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
							concealedPartialTorso))),
			null,
			Util.newArrayListOfValues(
							new ListValue<Colour>(Colour.CLOTHING_BLUE),
							new ListValue<Colour>(Colour.CLOTHING_BLACK)),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the t-shirt.",
					"You pull the t-shirt onto [npc.name].",
					null,
					"[npc.Name] pulls on the t-shirt.",
					"[npc.Name] pulls the t-shirt down over your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your t-shirt.",
					"You take off [npc.name]'s t-shirt.",
					null,
					"[npc.Name] takes [npc.her] t-shirt off.",
					"[npc.Name] takes your t-shirt off.",
					null, null, null);
		}
	};
	
	
	public static AbstractClothingType WITCH_HAT = new AbstractClothingType(
			1000,
			"a",
			false,
			"Witch's hat",
			"Witch's hats",
			"A witch's hat, with a pointy top and a wide brim.",
			1,
			Femininity.FEMININE,
			InventorySlot.HEAD,
			Rarity.EPIC,
			ClothingSet.WITCH,
			"witch_hat",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_SPELLS, TFPotency.MAJOR_BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null, null, null))),
			null,
			ColourListPresets.BLACK_OR_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_GOLD.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null, null){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You put on the witch's hat.",
					"You place the witch's hat onto [npc.name]'s head.",
					null,
					"[npc.Name] puts on the witch's hat.",
					"[npc.Name] places the witch's hat onto your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your witch's hat.",
					"You take off [npc.name]'s witch's hat.",
					null,
					"[npc.Name] takes [npc.her] witch's hat off.",
					"[npc.Name] takes your witch's hat off.",
					null, null, null);
		}

	};
	
	public static AbstractClothingType WITCH_DRESS = new AbstractClothingType(3000,
			"a",
			false,
			"Witch's dress",
			"Witch's dresses",
			"A witch's dress, made of a thin, soft fabric. It has a flared sleeves and a short skirt. A series of ribbons connect the collar to front of the dress, and when worn, they create a pentagram pattern.",
			1,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.EPIC,
			ClothingSet.WITCH,
			"witch_dress",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_SPELLS, TFPotency.MAJOR_BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.SPELL_COST_MODIFIER, TFPotency.MAJOR_BOOST, 0))),
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BREASTS),
									new ListValue<CoverableArea>(CoverableArea.NIPPLES),
									new ListValue<CoverableArea>(CoverableArea.STOMACH),
									new ListValue<CoverableArea>(CoverableArea.BACK)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
							concealedPartialTorso)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_UP,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedGroin))),
			Util.newArrayListOfValues(new ListValue<InventorySlot>(InventorySlot.LEG)),
			ColourListPresets.BLACK_OR_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_GOLD.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null, null){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the witch's dress.",
					"You pull the witch's dress onto [npc.name].",
					null,
					"[npc.Name] pulls on the witch's dress.",
					"[npc.Name] pulls the witch's dress onto you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your witch's dress.",
					"You pull off [npc.name]'s witch's dress.",
					null,
					"[npc.Name] takes [npc.her] witch's dress off.",
					"[npc.Name] takes your witch's dress off.",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull up the skirt of your witch's dress.";
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] pulls up the skirt of [npc.her] dress.");
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingOwner, "[npc.Name] pulls up the skirt of your dress.");
				} else {
					return UtilText.parse(clothingOwner, "You pull up the skirt of [npc.name]'s dress.");
				}
			}
			
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull your dress's skirt back down.";
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] pulls [npc.her] dress's skirt back down.");
			}
		}
	};
	
	public static AbstractClothingType WITCH_BOOTS = new AbstractClothingType(1500,
			"a pair of",
			true,
			"Witch's boots",
			"Witch's boots",
			"A pair of stylish boots, of the sort worn by a witch.",
			1,
			Femininity.FEMININE,
			InventorySlot.FOOT,
			Rarity.EPIC,
			ClothingSet.WITCH,
			"witch_boots",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.SPELL_COST_MODIFIER, TFPotency.MAJOR_BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null,
							null,
							null))),
			null,
			ColourListPresets.BLACK_OR_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_GOLD.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You push your [pc.feet] into the boots.",
					"You push [npc.name]'s [npc.feet] into the boots.",
					"You force the boots onto [npc.name]'s [npc.feet].",
					"[npc.Name] pushes [npc.her] [npc.feet] into the boots.",
					"[npc.Name] pushes your [pc.feet] into the boots.",
					"[npc.Name] forces the boots onto your [pc.feet].", null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off your boots.",
					"You pull [npc.name]'s boots off.",
					"You grab [npc.name]'s [npc.feet] and pull [npc.her] boots off.",
					"[npc.Name] pulls off [npc.her] boots.",
					"[npc.Name] pulls off your boots.",
					"[npc.Name] grabs your [pc.feet] and pulls your boots off.", null, null);
		}
	};
	
	public static AbstractClothingType WITCH_BOOTS_THIGH_HIGH = new AbstractClothingType(1750,
			"a pair of",
			true,
			"Witch's thigh-high boots",
			"Witch's thigh-high boots",
			"A pair of stylish thigh-high boots, of the sort worn by a witch.",
			1,
			Femininity.FEMININE,
			InventorySlot.FOOT,
			Rarity.EPIC,
			ClothingSet.WITCH,
			"witch_boots_thigh_high",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.SPELL_COST_MODIFIER, TFPotency.MAJOR_BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							Util.newArrayListOfValues(
									new ListValue<InventorySlot>(InventorySlot.ANKLE))))),
			null,
			ColourListPresets.BLACK_OR_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_GOLD.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You push your [pc.feet] into the boots.",
					"You push [npc.name]'s [npc.feet] into the boots.",
					"You force the boots onto [npc.name]'s [npc.feet].",
					"[npc.Name] pushes [npc.her] [npc.feet] into the boots.",
					"[npc.Name] pushes your [pc.feet] into the boots.",
					"[npc.Name] forces the boots onto your [pc.feet].", null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off your boots.",
					"You pull [npc.name]'s boots off.",
					"You grab [npc.name]'s [npc.feet] and pull [npc.her] boots off.",
					"[npc.Name] pulls off [npc.her] boots.",
					"[npc.Name] pulls off your boots.",
					"[npc.Name] grabs your [pc.feet] and pulls your boots off.", null, null);
		}
	};
	
	
	public static AbstractClothingType KIMONO_HAIR_KANZASHI = new AbstractClothingType(500,
			"a",
			false,
			"kanzashi",
			"kanzashi",
			"A traditional Japanese hair ornament, composed primarily of folded cloth flowers.",
			1,
			Femininity.FEMININE,
			InventorySlot.HAIR,
			Rarity.EPIC,
			ClothingSet.GEISHA,
			"kimono_hair_kanzashi",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0))),
			null,
			null,
			ColourListPresets.KIMONO.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.KIMONO.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			Util.newArrayListOfValues(new ListValue<>(ItemTag.REINDEER_GIFT))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You tie up your [pc.hair] and fasten the kanzashi in place.",
					"You tie up [npc.name]'s [npc.hair] and fasten the kanzashi in place.",
					null,
					"[npc.Name] ties up [npc.her] [npc.hair] and fastens the kanzashi in place.",
					"[npc.Name] ties up your [pc.hair] and fastens the kanzashi in place.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your kanzashi.",
					"You take off [npc.name]'s kanzashi.",
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
			"Primarily worn by the reclusive kitsunes, these full-length robes are identical to their traditional Japanese namesake.",
			1,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.EPIC,
			ClothingSet.GEISHA,
			"kimono_torso_kimono",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0))),
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BREASTS),
									new ListValue<CoverableArea>(CoverableArea.NIPPLES),
									new ListValue<CoverableArea>(CoverableArea.BACK)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)), null)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.UNTIE,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA),
											new ListValue<CoverableArea>(CoverableArea.BREASTS),
											new ListValue<CoverableArea>(CoverableArea.NIPPLES),
											new ListValue<CoverableArea>(CoverableArea.STOMACH)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN),
											new ListValue<ClothingAccess>(ClothingAccess.CHEST),
											new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
									concealedDressFrontFull)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_UP,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedGroin))),
			Util.newArrayListOfValues(
					new ListValue<InventorySlot>(InventorySlot.LEG)),
			ColourListPresets.KIMONO.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.KIMONO.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.KIMONO.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			Util.newArrayListOfValues(new ListValue<>(ItemTag.REINDEER_GIFT))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the kimono.",
					"You pull the kimono onto [npc.name].",
					null,
					"[npc.Name] pulls on the kimono.",
					"[npc.Name] pulls the kimono onto you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your kimono.",
					"You pull off [npc.name]'s kimono.",
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
			1,
			Femininity.FEMININE,
			InventorySlot.FOOT,
			Rarity.EPIC,
			ClothingSet.GEISHA,
			"kimono_foot_geta",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null, null, null))),
			null,
			ColourListPresets.KIMONO.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.REINDEER_GIFT))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slip your [pc.feet] into the geta.",
					"You push [npc.name]'s [npc.feet] into the geta.",
					"You force the geta onto [npc.name]'s [npc.feet].",
					"[npc.Name] slips [npc.her] [npc.feet] into the geta.",
					"[npc.Name] pushes your [pc.feet] into the geta.",
					"[npc.Name] forces the geta onto your [pc.feet].", null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide off your geta.",
					"You pull [npc.name]'s geta off.",
					"You grab [npc.name]'s [npc.feet] and pull [npc.her] geta off.",
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
			"Primarily worn by the reclusive kitsunes, these full-length robes are identical to their traditional Japanese namesake.",
			1,
			Femininity.MASCULINE,
			InventorySlot.TORSO_UNDER,
			Rarity.EPIC,
			ClothingSet.RONIN,
			"kimono_torso_mens_kimono",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_PHYSICAL, TFPotency.MAJOR_BOOST, 0))),
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BREASTS),
									new ListValue<CoverableArea>(CoverableArea.NIPPLES),
									new ListValue<CoverableArea>(CoverableArea.BACK)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)), null)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.UNTIE,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA),
											new ListValue<CoverableArea>(CoverableArea.BREASTS),
											new ListValue<CoverableArea>(CoverableArea.NIPPLES),
											new ListValue<CoverableArea>(CoverableArea.STOMACH)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN),
											new ListValue<ClothingAccess>(ClothingAccess.CHEST),
											new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
									concealedDressFrontFull)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_UP,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedGroin))),
			Util.newArrayListOfValues(new ListValue<InventorySlot>(InventorySlot.LEG)),
			Util.newArrayListOfValues(
							new ListValue<Colour>(Colour.CLOTHING_BLACK),
							new ListValue<Colour>(Colour.CLOTHING_GREY),
							new ListValue<Colour>(Colour.CLOTHING_BLUE)),
			ColourListPresets.ALL.getPresetColourList(),
			Util.newArrayListOfValues(
							new ListValue<Colour>(Colour.CLOTHING_BLUE_LIGHT),
							new ListValue<Colour>(Colour.CLOTHING_WHITE)),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_GREY.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			Util.newArrayListOfValues(new ListValue<>(ItemTag.REINDEER_GIFT))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the kimono.",
					"You pull the kimono onto [npc.name].",
					null,
					"[npc.Name] pulls on the kimono.",
					"[npc.Name] pulls the kimono onto you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your kimono.",
					"You pull off [npc.name]'s kimono.",
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
			1,
			Femininity.MASCULINE,
			InventorySlot.TORSO_OVER,
			Rarity.EPIC,
			ClothingSet.RONIN,
			"kimono_torso_over_haori",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_PHYSICAL, TFPotency.MAJOR_BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.BACK)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)),
									null))),
			null,
			Util.newArrayListOfValues(
							new ListValue<Colour>(Colour.CLOTHING_BLACK),
							new ListValue<Colour>(Colour.CLOTHING_GREY),
							new ListValue<Colour>(Colour.CLOTHING_BLUE)),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.REINDEER_GIFT))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the haori.",
					"You guide [npc.name]'s [npc.arms] through the haori's sleeves as you pull it on [npc.herHim].",
					null,
					"[npc.Name] pulls on the haori.",
					"[npc.Name] guides your [pc.arms] through the haori's sleeves as [npc.she] pulls it on you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your haori.",
					"You pull off [npc.name]'s haori.",
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
			1,
			Femininity.MASCULINE,
			InventorySlot.FOOT,
			Rarity.EPIC,
			ClothingSet.RONIN,
			"kimono_foot_mens_geta",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_PHYSICAL, TFPotency.BOOST, 0))),
			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET)), null, null, null))),
			null,
			Util.newArrayListOfValues(
							new ListValue<Colour>(Colour.CLOTHING_BLACK),
							new ListValue<Colour>(Colour.CLOTHING_GREY),
							new ListValue<Colour>(Colour.CLOTHING_BLUE)),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.REINDEER_GIFT))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slip your [pc.feet] into the geta.",
					"You push [npc.name]'s [npc.feet] into the geta.",
					"You force the geta onto [npc.name]'s [npc.feet].",
					"[npc.Name] slips [npc.her] [npc.feet] into the geta.",
					"[npc.Name] pushes your [pc.feet] into the geta.",
					"[npc.Name] forces the geta onto your [pc.feet].", null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You slide off your geta.",
					"You pull [npc.name]'s geta off.",
					"You grab [npc.name]'s [npc.feet] and pull [npc.her] geta off.",
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
			1,
			null,
			InventorySlot.HEAD,
			Rarity.EPIC,
			ClothingSet.JOLNIR,
			"jolnir_head_hat",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.CRITICAL_DAMAGE, TFPotency.MAJOR_BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null, null, null))),
			null,
			ColourListPresets.JUST_RED.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.REINDEER_GIFT))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You put on J&oacute;lnir's hat.",
					"You place J&oacute;lnir's hat onto [npc.name]'s head.",
					null,
					"[npc.Name] puts on J&oacute;lnir's hat.",
					"[npc.Name] places J&oacute;lnir's hat onto your head.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off J&oacute;lnir's hat.",
					"You take off [npc.name]'s J&oacute;lnir's hat.",
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
			5,
			null,
			InventorySlot.TORSO_OVER,
			Rarity.EPIC,
			ClothingSet.JOLNIR,
			"jolnir_torso_over_coat",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.SPELL_COST_MODIFIER, TFPotency.MAJOR_BOOST, 0)), 
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_SPELLS, TFPotency.MAJOR_BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)),
									null,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)),
									concealedFullTorso))),
			null,
			ColourListPresets.JUST_RED.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			Util.newArrayListOfValues(new ListValue<>(ItemTag.REINDEER_GIFT))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the coat.",
					"You guide [npc.name]'s [npc.arms] through the coat's sleeves as you pull it on [npc.herHim].",
					null,
					"[npc.Name] pulls on the coat.",
					"[npc.Name] guides your [pc.arms] through the coat's sleeves as [npc.she] pulls it on you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your coat.",
					"You pull off [npc.name]'s coat.",
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
			5,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.EPIC,
			ClothingSet.JOLNIR,
			"jolnir_torso_dress",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.SPELL_COST_MODIFIER, TFPotency.MAJOR_BOOST, 0)), 
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_SPELLS, TFPotency.MAJOR_BOOST, 0))),
			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.BREASTS),
									new ListValue<CoverableArea>(CoverableArea.NIPPLES),
									new ListValue<CoverableArea>(CoverableArea.STOMACH),
									new ListValue<CoverableArea>(CoverableArea.BACK)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
							concealedPartialTorso)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_UP,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedGroin))),
			Util.newArrayListOfValues(new ListValue<InventorySlot>(InventorySlot.LEG)),
			ColourListPresets.JUST_RED.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			Util.newArrayListOfValues(new ListValue<>(ItemTag.REINDEER_GIFT))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on J&oacute;lnir's dress.",
					"You pull J&oacute;lnir's dress onto [npc.name].",
					null,
					"[npc.Name] pulls on J&oacute;lnir's dress.",
					"[npc.Name] pulls J&oacute;lnir's dress onto you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off J&oacute;lnir's dress.",
					"You pull off [npc.name]'s J&oacute;lnir's dress.",
					null,
					"[npc.Name] takes J&oacute;lnir's dress off.",
					"[npc.Name] takes your J&oacute;lnir's dress off.",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull up the skirt of your dress.";
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] pulls up the skirt of [npc.her] dress.");
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingOwner, "[npc.Name] pulls up the skirt of your dress.");
				} else {
					return UtilText.parse(clothingOwner, "You pull up the skirt of [npc.name]'s dress.");
				}
			}
			
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull your dress's skirt back down.";
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] pulls [npc.her] dress's skirt back down.");
			}
		}
	};
	
	public static AbstractClothingType JOLNIR_BOOTS = new AbstractClothingType(500,
			"a pair of",
			true,
			"J&oacute;lnir's boot",
			"J&oacute;lnir's boots",
			"A pair of boots, of the sort worn by the Yule figure.",
			1,
			Femininity.MASCULINE,
			InventorySlot.FOOT,
			Rarity.EPIC,
			ClothingSet.JOLNIR,
			"jolnir_foot_boots",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.CRITICAL_CHANCE, TFPotency.MAJOR_BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null, null, null))),
			null,
			ColourListPresets.JUST_RED.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			Util.newArrayListOfValues(new ListValue<>(ItemTag.REINDEER_GIFT))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You push your [pc.feet] into the boots.",
					"You push [npc.name]'s [npc.feet] into the boots.",
					"You force the boots onto [npc.name]'s [npc.feet].",
					"[npc.Name] pushes [npc.her] [npc.feet] into the boots.",
					"[npc.Name] pushes your [pc.feet] into the boots.",
					"[npc.Name] forces the boots onto your [pc.feet].", null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off your boots.",
					"You pull [npc.name]'s boots off.",
					"You grab [npc.name]'s [npc.feet] and pull [npc.her] boots off.",
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
			1,
			Femininity.FEMININE,
			InventorySlot.FOOT,
			Rarity.EPIC,
			ClothingSet.JOLNIR,
			"jolnir_foot_boots_feminine",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.CRITICAL_CHANCE, TFPotency.MAJOR_BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET)),
							null, null, null))),
			null,
			ColourListPresets.JUST_RED.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			Util.newArrayListOfValues(new ListValue<>(ItemTag.REINDEER_GIFT))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You push your [pc.feet] into the boots.",
					"You push [npc.name]'s [npc.feet] into the boots.",
					"You force the boots onto [npc.name]'s [npc.feet].",
					"[npc.Name] pushes [npc.her] [npc.feet] into the boots.",
					"[npc.Name] pushes your [pc.feet] into the boots.",
					"[npc.Name] forces the boots onto your [pc.feet].", null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull off your boots.",
					"You pull [npc.name]'s boots off.",
					"You grab [npc.name]'s [npc.feet] and pull [npc.her] boots off.",
					"[npc.Name] pulls off [npc.her] boots.",
					"[npc.Name] pulls off your boots.",
					"[npc.Name] grabs your [pc.feet] and pulls your boots off.", null, null);
		}
	};
	
	
	public static AbstractClothingType SCIENTIST_TORSO_OVER_LAB_COAT = new AbstractClothingType(800,
			"a",
			false,
			"lab coat",
			"lab coats",
			"A knee-length cotton overcoat which helps protect the wearer against accidental chemical spills.",
			1,
			null,
			InventorySlot.TORSO_OVER,
			Rarity.EPIC,
			ClothingSet.SCIENTIST,
			"torso_over_lab_coat",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_FIRE, TFPotency.BOOST, 0)), 
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_ICE, TFPotency.BOOST, 0)), 
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_POISON, TFPotency.BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.BACK)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)), null
									)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_UP,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									concealedGroin)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.UNBUTTONS,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.BREASTS),
											new ListValue<CoverableArea>(CoverableArea.NIPPLES),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA),
											new ListValue<CoverableArea>(CoverableArea.STOMACH)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN),
											new ListValue<ClothingAccess>(ClothingAccess.CHEST),
											new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
									concealedDressFrontFull))),
			null,
			ColourListPresets.BLACK_OR_WHITE.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You pull on the lab coat.",
					"You guide [npc.name]'s [npc.arms] through the lab coat's sleeves as you pull it on [npc.herHim].",
					null,
					"[npc.Name] pulls on the lab coat.",
					"[npc.Name] guides your [pc.arms] through the lab coat's sleeves as [npc.she] pulls it on you.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You take off your lab coat.",
					"You pull off [npc.name]'s lab coat.",
					null,
					"[npc.Name] takes [npc.her] lab coat off.",
					"[npc.Name] pulls your lab coat off.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType SCIENTIST_EYES_SAFETY_GOGGLES = new AbstractClothingType(150,
			"a pair of",
			true,
			"safety goggles",
			"safety goggles",
			"A pair of safety goggles. They're the type worn by scientists when handling chemicals.",
			1,
			null,
			InventorySlot.EYES,
			Rarity.EPIC,
			ClothingSet.SCIENTIST,
			"eye_safety_goggles",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_FIRE, TFPotency.BOOST, 0)), 
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_ICE, TFPotency.BOOST, 0)), 
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_POISON, TFPotency.BOOST, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.HEAD),
											new ListValue<ClothingAccess>(ClothingAccess.EYES)),
									null,
									null, null))),
			null,
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(new ListValue<>(ItemTag.SOLD_BY_NYAN))){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You put on the safety goggles.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] puts on " + clothing.getName(true) + ".");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return "[npc.Name] holds you by the chin and pushes " + clothing.getName(true) + " onto your face, before looping the elastic strap over your head.";
					else
						return UtilText.parse(clothingOwner,
								"You firmly hold [npc.name]'s chin and push the safety goggles onto [npc.her] face," + " before looping the elastic strap over [npc.her] head.");
				} else {
					if (clothingOwner.isPlayer())
						return UtilText.parse(clothingOwner,
								"[npc.Name] places " + clothing.getName(true) + " onto your face," + " before looping the elastic strap over your head.");
					else
						return UtilText.parse(clothingOwner,
								"You place the safety goggles onto [npc.name]'s face," + " before looping the elastic strap over your head.");
				}
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You take off your safety goggles.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] takes off [npc.her] safety goggles.");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return "[npc.Name] grabs your safety goggles and pulls them off.";
					else
						return "You grab [npc.name]'s safety goggles and pull them off.";
				} else {
					if (clothingOwner.isPlayer())
						return "[npc.Name] takes your safety goggles off.";
					else
						return "You take off [npc.name]'s safety goggles.";
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your safety goggles up onto your forehead.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls [npc.her] safety goggles up onto [npc.her] forehead.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls up your safety goggles.";
				else
					return UtilText.parse(clothingOwner, "You pull up [npc.name]'s safety goggles.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your safety goggles back down over your eyes.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls [npc.her] safety goggles back down over [npc.her] eyes.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] pulls your safety goggles down over your eyes.";
				else
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s safety goggles down over [npc.her] eyes.");
			}
		}
	};
	
	public static AbstractClothingType AMBERS_BITCH_CHOKER = new AbstractClothingType(1250,
			"an",
			false,
			"'Amber's Bitch' collar",
			"'Amber's Bitch' collars",
			"A special collar that Mistress Amber clasped around your neck. The metal tag reads 'Amber's Bitch', and you feel a lot more submissive while wearing it...",
			0,
			null,
			InventorySlot.NECK,
			Rarity.LEGENDARY,
			null,
			"ambers_bitch_choker",
			Util.newArrayListOfValues(
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_SUBMISSIVE, TFPotency.MAJOR_BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SEALING, TFModifier.ARCANE_BOOST, TFPotency.MINOR_BOOST, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_DRAIN, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_LUST, TFPotency.MAJOR_DRAIN, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_LUST, TFPotency.MAJOR_DRAIN, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_LUST, TFPotency.MAJOR_DRAIN, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_LUST, TFPotency.MAJOR_DRAIN, 0)),
					new ListValue<>(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_LUST, TFPotency.MAJOR_DRAIN, 0))),
			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							null,
							null,
							null, null))),
			null,
			Util.newArrayListOfValues(new ListValue<>(Colour.CLOTHING_PINK_LIGHT)),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_STEEL.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			null,
			null, null){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You wrap the choker around your neck and fasten the buckle.",
					"You wrap the choker around [npc.name]'s neck and fasten the buckle.",
					null,
					"[npc.Name] wraps the choker around [npc.her] neck and fastens the buckle.",
					"[npc.Name] wraps the choker around your neck and fastens the buckle.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"You unfasten the choker's buckle and remove it from around your neck.",
					"You unfasten the choker's buckle and remove it from around [npc.name]'s neck.",
					null,
					"[npc.Name] unfastens the choker's buckle and removes it from around [npc.her] neck.",
					"[npc.Name] unfastens the choker's buckle and removes it from around your neck.",
					null, null, null);
		}
	};
	
	private static List<AbstractClothingType> allClothing, moddedClothingList,
										commonClothing, commonFemaleClothing, commonMaleClothing, commonAndrogynousClothing,
										commonFemaleLingerie, commonMaleLingerie, commonAndrogynousLingerie,
										commonFemaleAccessories, commonMaleAccessories, commonAndrogynousAccessories,
										commonJewellery, commonFemaleJewellery, commonMaleJewellery, commonAndrogynousJewellery;
	
	private static List<InventorySlot> coreClothingSlots, lingerieSlots;
	
	private static Map<InventorySlot, List<AbstractClothingType>>commonClothingMap,
															commonClothingMapFemale,
															commonClothingMapMale,
															commonClothingMapAndrogynous,
															commonClothingMapFemaleIncludingAndrogynous,
															commonClothingMapMaleIncludingAndrogynous;
	
	private static Map<AbstractClothingType, String> clothingToIdMap = new HashMap<>();
	private static Map<String, AbstractClothingType> idToClothingMap = new HashMap<>();
	
	public static AbstractClothingType getClothingTypeFromId(String id) {
		if(id.equals("EYES_SAFETY_GOGGLES")) {
			return ClothingType.SCIENTIST_EYES_SAFETY_GOGGLES;
		}
		return idToClothingMap.get(id);
	}
	
	public static String getIdFromClothingType(AbstractClothingType clothingType) {
		return clothingToIdMap.get(clothingType);
	}
	
//	public static AbstractClothingType TORSO_OVER_HOODIE_COPY = new AbstractClothingType(new File("res/items/clothing/rentalMommy/rental_mommy.xml")) {};
	
	static {
		
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
		
		coreClothingSlots = Util.newArrayListOfValues(new ListValue<>(InventorySlot.TORSO_UNDER), new ListValue<>(InventorySlot.LEG));
		lingerieSlots = Util.newArrayListOfValues(new ListValue<>(InventorySlot.CHEST), new ListValue<>(InventorySlot.GROIN), new ListValue<>(InventorySlot.STOMACH), new ListValue<>(InventorySlot.SOCK));

		allClothing = new ArrayList<>();
		commonClothing = new ArrayList<>();
		
		commonFemaleClothing = new ArrayList<>();
		commonFemaleLingerie = new ArrayList<>();
		commonFemaleAccessories = new ArrayList<>();
		
		commonMaleClothing = new ArrayList<>();
		commonMaleLingerie = new ArrayList<>();
		commonMaleAccessories = new ArrayList<>();
		
		commonAndrogynousClothing = new ArrayList<>();
		commonAndrogynousLingerie = new ArrayList<>();
		commonAndrogynousAccessories = new ArrayList<>();
		
		commonJewellery = new ArrayList<>();
		commonFemaleJewellery = new ArrayList<>();
		commonMaleJewellery = new ArrayList<>();
		commonAndrogynousJewellery = new ArrayList<>();
		
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
										AbstractClothingType ct = new AbstractClothingType(innerChild) {};
										moddedClothingList.add(ct);
//										System.out.println(modAuthorDirectory.getName()+"_"+innerChild.getParentFile().getName()+"_"+innerChild.getName().split("\\.")[0]);
										String id = modAuthorDirectory.getName()+"_"+innerChild.getParentFile().getName()+"_"+innerChild.getName().split("\\.")[0];
										clothingToIdMap.put(ct, id);
										idToClothingMap.put(id, ct);
									}
								}
							}
						}
					}
				}
			}
		}
		
		allClothing.addAll(moddedClothingList);
		
		Field[] fields = ClothingType.class.getFields();
		
		for(Field f : fields){
			
			if (AbstractClothingType.class.isAssignableFrom(f.getType())) {
				
				AbstractClothingType ct;
				try {
					ct = ((AbstractClothingType) f.get(null));

					// I feel like this is stupid :thinking:
					clothingToIdMap.put(ct, f.getName());
					idToClothingMap.put(f.getName(), ct);
					
					allClothing.add(ct);
					
					if(ct==ClothingType.PENIS_CONDOM
							|| ct==ClothingType.TORSO_OVER_CHRISTMAS_SWEATER
							|| ct==ClothingType.HEAD_ANTLER_HEADBAND
							|| ct==ClothingType.PIERCING_EAR_SNOW_FLAKES
							|| ct==ClothingType.PIERCING_NOSE_SNOWFLAKE_STUD
							|| ct==ClothingType.NECK_SNOWFLAKE_NECKLACE) {
						continue;
					}
					
					if(ct.getRarity()==Rarity.COMMON) {
						commonClothingMap.get(ct.getSlot()).add(ct);
						
						if (ct.getFemininityRestriction() == Femininity.FEMININE) {
							commonClothingMapFemale.get(ct.getSlot()).add(ct);
							commonClothingMapFemaleIncludingAndrogynous.get(ct.getSlot()).add(ct);
							
						} else if (ct.getFemininityRestriction() == Femininity.ANDROGYNOUS || ct.getFemininityRestriction() == null) {
							commonClothingMapAndrogynous.get(ct.getSlot()).add(ct);
							commonClothingMapFemaleIncludingAndrogynous.get(ct.getSlot()).add(ct);
							commonClothingMapMaleIncludingAndrogynous.get(ct.getSlot()).add(ct);
							
						} else if (ct.getFemininityRestriction() == Femininity.MASCULINE) {
							commonClothingMapMale.get(ct.getSlot()).add(ct);
							commonClothingMapMaleIncludingAndrogynous.get(ct.getSlot()).add(ct);
						}
					}
					
					if(!ct.getSlot().isJewellery()){
						if (ct.getRarity() == Rarity.COMMON) {
							commonClothing.add(ct);
							if (ct.getFemininityRestriction() == null) {
								if(coreClothingSlots.contains(ct.getSlot())) {
									commonAndrogynousClothing.add(ct);
								} else if(lingerieSlots.contains(ct.getSlot())) {
									commonAndrogynousLingerie.add(ct);
								} else {
									commonAndrogynousAccessories.add(ct);
								}
								
							} else if (ct.getFemininityRestriction() == Femininity.FEMININE) {
								if(coreClothingSlots.contains(ct.getSlot())) {
									commonFemaleClothing.add(ct);
								} else if(lingerieSlots.contains(ct.getSlot())) {
									commonFemaleLingerie.add(ct);
								} else {
									commonFemaleAccessories.add(ct);
								}
								
							} else if (ct.getFemininityRestriction() == Femininity.MASCULINE) {
								if(coreClothingSlots.contains(ct.getSlot())) {
									commonMaleClothing.add(ct);
								} else if(lingerieSlots.contains(ct.getSlot())) {
									commonMaleLingerie.add(ct);
								} else {
									commonMaleAccessories.add(ct);
								}
							}
						}
					}else{
						if (ct.getRarity() == Rarity.COMMON) {
							commonJewellery.add(ct);
							if (ct.getFemininityRestriction() == null)
								commonAndrogynousJewellery.add(ct);
							else if (ct.getFemininityRestriction() == Femininity.FEMININE)
								commonFemaleJewellery.add(ct);
							else if (ct.getFemininityRestriction() == Femininity.MASCULINE)
								commonMaleJewellery.add(ct);
						}
					}
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				
			}
		}
  	    
	}
	
	public static List<AbstractClothingType> getAllClothing() {
		return allClothing;
	}

	public static List<AbstractClothingType> getModdedClothingList() {
		return moddedClothingList;
	}

	public static List<AbstractClothingType> getCommonClothing() {
		return commonClothing;
	}

	public static List<AbstractClothingType> getCommonFemaleClothing() {
		return commonFemaleClothing;
	}

	public static List<AbstractClothingType> getCommonMaleClothing() {
		return commonMaleClothing;
	}

	public static List<AbstractClothingType> getCommonAndrogynousClothing() {
		return commonAndrogynousClothing;
	}
	
	public static List<AbstractClothingType> getCommonJewellery() {
		return commonJewellery;
	}

	public static List<AbstractClothingType> getCommonFemaleJewellery() {
		return commonFemaleJewellery;
	}

	public static List<AbstractClothingType> getCommonMaleJewellery() {
		return commonMaleJewellery;
	}

	public static List<AbstractClothingType> getCommonAndrogynousJewellery() {
		return commonAndrogynousJewellery;
	}

	public static List<InventorySlot> getCoreClothingSlots() {
		return coreClothingSlots;
	}

	public static List<InventorySlot> getLingerieSlots() {
		return lingerieSlots;
	}

	public static List<AbstractClothingType> getCommonFemaleLingerie() {
		return commonFemaleLingerie;
	}

	public static List<AbstractClothingType> getCommonMaleLingerie() {
		return commonMaleLingerie;
	}

	public static List<AbstractClothingType> getCommonAndrogynousLingerie() {
		return commonAndrogynousLingerie;
	}

	public static List<AbstractClothingType> getCommonFemaleAccessories() {
		return commonFemaleAccessories;
	}

	public static List<AbstractClothingType> getCommonMaleAccessories() {
		return commonMaleAccessories;
	}

	public static List<AbstractClothingType> getCommonAndrogynousAccessories() {
		return commonAndrogynousAccessories;
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
