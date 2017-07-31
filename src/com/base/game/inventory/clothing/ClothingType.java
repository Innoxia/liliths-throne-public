package com.base.game.inventory.clothing;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.base.game.character.GameCharacter;
import com.base.game.character.attributes.Attribute;
import com.base.game.character.body.types.ArmType;
import com.base.game.character.body.types.LegType;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.character.race.Race;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.InventorySlot;
import com.base.game.inventory.Rarity;
import com.base.game.inventory.clothing.BlockedParts;
import com.base.main.Main;
import com.base.rendering.SVGImages;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;
import com.base.utils.Util.Value;

/**
 * @since 0.1.0
 * @version 0.1.78
 * @author Innoxia
 */
public enum ClothingType {

	// Common: Only give physical resistance
	// Rare: Have 1 effect
	// Epic: Have 1 effect and are part of a set
	// Legendary: Have 2 effects and are part of a set

	/*
	 * Set ideas: Princess Slave Latex
	 */

	// Special:
	HEAD_CHEATERS_CIRCLET("a", false, "cheater's circlet", "A thin band of metal that sits ontop of your head. On the inside, an engraved message reads: 'FOR INTERNAL TESTING ONLY!'", 1, null, InventorySlot.HEAD, Rarity.LEGENDARY, null,
			"head_circlet", Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.STRENGTH, -50), new Value<Attribute, Integer>(Attribute.INTELLIGENCE, -50), new Value<Attribute, Integer>(Attribute.FITNESS, -50)),

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.HEAD)), null, null))),

			null, // List<InventorySlot> incompatibleSlots

			Util.newArrayListOfValues(new ListValue<Colour>(com.base.utils.Colour.CLOTHING_GOLD))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return "You place the circlet on your head.";
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You remove the circlet from your head.";
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " roughly grabs your circlet and throws it to the floor.";
					else
						return "You roughly grab " + clothingOwner.getName("the") + "'s circlet and throw it to the floor.";
				} else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " takes the circlet off your head.";
					else
						return "You take " + clothingOwner.getName("the") + "'s circlet from " + clothingOwner.getGender().getPossessiveBeforeNoun() + " head.";
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	
	//PIERCINGS:
	PIERCING_EAR_BASIC_RING("a pair of", true, "earrings", "A pair of very basic earrings.",
			0, Femininity.FEMININE, InventorySlot.PIERCING_EAR, Rarity.COMMON, null, "piercing_ear_ring", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null))),

			null, // List<InventorySlot> incompatibleSlots

			Colour.allMetalColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return "You clip the earrings into place.";
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You unclip your earrings.";
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " unclips your earrings.";
				else
					return "You unclip " + clothingOwner.getName("the") + "'s earrings.";
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	PIERCING_NOSE_BASIC_RING("a", false, "nose ring", "A simple nose ring. The little disc found on one end rests inside the nostril, holding it in place, while the rest of the ring is on display.",
			0, null, InventorySlot.PIERCING_NOSE, Rarity.COMMON, null, "piercing_nose_ring", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null))),

			null, // List<InventorySlot> incompatibleSlots

			Colour.allMetalColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return "You slide the nose ring into place.";
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You slide the nose ring out.";
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " slides your nose ring out.";
				else
					return "You slide " + clothingOwner.getName("the") + "'s nose ring out.";
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	PIERCING_LIP_RINGS("a pair of", true, "lip rings", "A pair of thin, simple lip rings.",
			0, null, InventorySlot.PIERCING_LIP, Rarity.COMMON, null, "piercing_lip_double_ring", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null))),

			null, // List<InventorySlot> incompatibleSlots
			
			Colour.allMetalColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return "You slide the lip rings into place.";
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You slide the lip rings out.";
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " slides your lip rings out.";
				else
					return "You slide " + clothingOwner.getName("the") + "'s lip rings out.";
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	PIERCING_TONGUE_BAR("a", false, "tongue bar", "A bar that can be slid into place through a tongue piercing.",
			0, null, InventorySlot.PIERCING_TONGUE, Rarity.COMMON, null, "piercing_tongue_bar", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null))),

			null, // List<InventorySlot> incompatibleSlots
			
			Colour.allMetalColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return "You slide the tongue bar into place.";
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You slide the tongue bar out.";
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " slides your tongue bar out.";
				else
					return "You slide " + clothingOwner.getName("the") + "'s tongue bar out.";
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	PIERCING_NAVEL_GEM("a", false, "navel gem barbell", "A bar with a gemstone embedded on one end. It's designed to fit into a navel piercing.",
			0, Femininity.FEMININE, InventorySlot.PIERCING_STOMACH, Rarity.COMMON, null, "piercing_navel_basic", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null))),

			null, // List<InventorySlot> incompatibleSlots
			
			Colour.allMetalColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return "You slide the navel barbell into place.";
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You slide the navel barbell out.";
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " slides your navel barbell out.";
				else
					return "You slide " + clothingOwner.getName("the") + "'s navel barbell out.";
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	PIERCING_NIPPLE_BARS("a pair of", false, "nipple bars", "A pair of bars that are designed to fit into nipple piercings.",
			0, Femininity.FEMININE, InventorySlot.PIERCING_NIPPLE, Rarity.COMMON, null, "piercing_nipple_bars", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null))),

			null, // List<InventorySlot> incompatibleSlots
			
			Colour.allMetalColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return "You slide the nipple bars into place.";
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You slide the nipple bars out.";
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " slides your nipple bars out.";
				else
					return "You slide " + clothingOwner.getName("the") + "'s nipple bars out.";
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	//ow-ow-ow-ow-ow-ow I really don't like this ;_;
	PIERCING_VAGINA_BARBELL_RING("a", false, "ringed barbell", "A barbell with a freely-moving hinged ring on one end. It's designed for a clitoral hood or Christina piercing.",
			0, Femininity.FEMININE, InventorySlot.PIERCING_VAGINA, Rarity.COMMON, null, "piercing_vagina_barbell_ring", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null))),

			null, // List<InventorySlot> incompatibleSlots
			
			Colour.allMetalColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return "You slide the ringed barbell into place.";
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You slide the ringed barbell out.";
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " slides your ringed barbell out.";
				else
					return "You slide " + clothingOwner.getName("the") + "'s ringed barbell out.";
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	PIERCING_PENIS_RING("a", false, "piercing ring", "A ring that's designed to fit into piercings in male genitalia. A removable segment on one side allows the wearer to easily slide the ring into place.",
			0, null, InventorySlot.PIERCING_PENIS, Rarity.COMMON, null, "piercing_penis_ring", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null))),

			null, // List<InventorySlot> incompatibleSlots
			
			Colour.allMetalColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return "You pop out the removable segment and slide the ring into place.";
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pop out the removable segment and slide the ring out.";
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pops out the removable segment and slides the ring out.";
				else
					return "You pop out the removable segment and slide " + clothingOwner.getName("the") + "'s ring out.";
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},

	// HEAD
	HEAD_CIRCLET("a", false, "circlet", "A thin band of metal that sits ontop of your head.", 1, Femininity.FEMININE, InventorySlot.HEAD, Rarity.COMMON, null, "head_circlet", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.HEAD)), null, null))),

			null, // List<InventorySlot> incompatibleSlots

			Util.newArrayListOfValues(new ListValue<Colour>(com.base.utils.Colour.CLOTHING_COPPER), new ListValue<Colour>(com.base.utils.Colour.CLOTHING_SILVER), new ListValue<Colour>(com.base.utils.Colour.CLOTHING_GOLD))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You place the circlet on your head.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " places " + clothing.getName(true) + " on <her> head.");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " holds you by the chin and places " + clothing.getName(true) + " on your head.";
					else
						return UtilText.genderParsing(clothingOwner, "You firmly hold " + clothingOwner.getName("the") + "'s chin and place the circlet on <her> head.");
				} else {
					if (clothingOwner.isPlayer())
						return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " places " + clothing.getName(true) + " on your head.");
					else
						return UtilText.genderParsing(clothingOwner, "You place the circlet on " + clothingOwner.getName("the") + "'s head.");
				}
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You remove the circlet from your head.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " removes <her> circelt.");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " roughly grabs your circlet and throws it to the floor.";
					else
						return "You roughly grab " + clothingOwner.getName("the") + "'s circlet and throw it to the floor.";
				} else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " takes the circlet off your head.";
					else
						return UtilText.genderParsing(clothingOwner, "You take " + clothingOwner.getName("the") + "'s circlet from <her> head.");
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},

	HEAD_HEADBAND("a", false, "headband", "A plain headband, designed to keep your head pushed back out of your face.", 1, null, InventorySlot.HEAD, Rarity.COMMON, null, "head_headband", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null,
							null))),

			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull on the headband.";
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls the headband onto [npc.her] head.");
			else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingOwner, "[npc.Name] pulls the headband onto your head.");
				} else {
					return UtilText.parse(clothingOwner, "You pull the headband onto [npc.name]'s head.");
				}
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You take off your headband.";
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] takes off [npc.her] headband.");
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingOwner, "[npc.Name] pulls your headband off.");
				} else {
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s headband off.");
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	
	HEAD_HEADBAND_BOW("a", false, "bow headband", "A headband with a cute bow attached to the top, designed to keep your head pushed back out of your face.", 1, Femininity.FEMININE, InventorySlot.HEAD, Rarity.COMMON, null, "head_headband_bow", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null,
							null))),

			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull on the headband.";
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] pulls the headband onto [npc.her] head.");
			else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingOwner, "[npc.Name] pulls the headband onto your head.");
				} else {
					return UtilText.parse(clothingOwner, "You pull the headband onto [npc.name]'s head.");
				}
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You take off your headband.";
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] takes off [npc.her] headband.");
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingOwner, "[npc.Name] pulls your headband off.");
				} else {
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s headband off.");
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	
	HEAD_CAP("a", false, "cap", "A soft cap with a stiff peak that projects from the front.", 1, null, InventorySlot.HEAD, Rarity.COMMON, null, "head_cap", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.HEAD)), null, null))),

			null, // List<InventorySlot> incompatibleSlots

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the cap.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls " + clothing.getName(true) + " onto <her> head.");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " holds you by the chin and pulls " + clothing.getName(true) + " onto your head.";
					else
						return UtilText.genderParsing(clothingOwner, "You firmly hold " + clothingOwner.getName("the") + "'s chin and pull the cap onto <her> head.");
				} else {
					if (clothingOwner.isPlayer())
						return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls " + clothing.getName(true) + " onto your head.");
					else
						return UtilText.genderParsing(clothingOwner, "You pull the cap onto " + clothingOwner.getName("the") + "'s head.");
				}
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You take off your cap.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " takes off <her> cap.");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " grabs your cap and yanks it off.";
					else
						return "You grab " + clothingOwner.getName("the") + "'s cap and yank it off.";
				} else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls off your cap.";
					else
						return "You pull off " + clothingOwner.getName("the") + "'s cap.";
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},

	// EYES
	EYES_GLASSES("a pair of", true, "glasses", "A pair of thin-rimmed glasses.", 0, null, InventorySlot.EYES, Rarity.COMMON, null, "eye_glasses", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.EYES)), null, null))),

			null, // List<InventorySlot> incompatibleSlots

			Util.newArrayListOfValues(new ListValue<Colour>(Colour.CLOTHING_BLACK_STEEL), new ListValue<Colour>(Colour.CLOTHING_STEEL), new ListValue<Colour>(Colour.CLOTHING_SILVER), new ListValue<Colour>(Colour.CLOTHING_GOLD))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You put on the glasses.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " puts on " + clothing.getName(true) + ".");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " holds you by the chin and pushes " + clothing.getName(true) + " onto your face.";
					else
						return UtilText.genderParsing(clothingOwner, "You firmly hold " + clothingOwner.getName("the") + "'s chin and push the glasses onto <her> face.");
				} else {
					if (clothingOwner.isPlayer())
						return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " places " + clothing.getName(true) + " onto your face.");
					else
						return UtilText.genderParsing(clothingOwner, "You place the glasses onto " + clothingOwner.getName("the") + "'s face.");
				}
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You take off your glasses.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " takes off <her> glasses.");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " grabs your glasses and pulls them off.";
					else
						return "You grab " + clothingOwner.getName("the") + "'s glasses and pull them off.";
				} else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " takes your glasses off.";
					else
						return "You take off " + clothingOwner.getName("the") + "'s glasses.";
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	EYES_AVIATORS("a pair of", true, "aviators", "A pair of aviator sunglasses.", 0, null, InventorySlot.EYES, Rarity.COMMON, null, "eye_aviators", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.EYES)), null, null))),

			null, // List<InventorySlot> incompatibleSlots

			Util.newArrayListOfValues(new ListValue<Colour>(Colour.CLOTHING_BLACK_STEEL), new ListValue<Colour>(Colour.CLOTHING_STEEL), new ListValue<Colour>(Colour.CLOTHING_SILVER), new ListValue<Colour>(Colour.CLOTHING_GOLD))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You put on the aviators.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " puts on " + clothing.getName(true) + ".");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " holds you by the chin and pushes " + clothing.getName(true) + " onto your face.";
					else
						return UtilText.genderParsing(clothingOwner, "You firmly hold " + clothingOwner.getName("the") + "'s chin and push the aviators onto <her> face.");
				} else {
					if (clothingOwner.isPlayer())
						return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " places " + clothing.getName(true) + " onto your face.");
					else
						return UtilText.genderParsing(clothingOwner, "You place the aviators onto " + clothingOwner.getName("the") + "'s face.");
				}
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You take off your aviators.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " takes off <her> aviators.");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " grabs your aviators and pulls them off.";
					else
						return "You grab " + clothingOwner.getName("the") + "'s aviators and pull them off.";
				} else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " takes your aviators off.";
					else
						return "You take off " + clothingOwner.getName("the") + "'s aviators.";
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	EYES_SAFETY_GOGGLES("a pair of", true, "safety goggles", "A pair of safety goggles. They're the type worn by scientists when handling chemicals.", 0, null, InventorySlot.EYES, Rarity.UNCOMMON, null, "eye_safety_goggles", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.HEAD), new ListValue<ClothingAccess>(ClothingAccess.EYES)), null, null))),

			null, // List<InventorySlot> incompatibleSlots

			Util.newArrayListOfValues(new ListValue<Colour>(Colour.CLOTHING_BLACK))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You put on the safety goggles.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " puts on " + clothing.getName(true) + ".");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " holds you by the chin and pushes " + clothing.getName(true) + " onto your face, before looping the elastic strap over your head.";
					else
						return UtilText.genderParsing(clothingOwner,
								"You firmly hold " + clothingOwner.getName("the") + "'s chin and push the safety goggles onto <her> face," + " before looping the elastic strap over <her> head.");
				} else {
					if (clothingOwner.isPlayer())
						return UtilText.genderParsing(clothingOwner,
								Util.capitaliseSentence(clothingOwner.getName("the")) + " places " + clothing.getName(true) + " onto your face," + " before looping the elastic strap over your head.");
					else
						return UtilText.genderParsing(clothingOwner,
								"You place the safety goggles onto " + clothingOwner.getName("the") + "'s face," + " before looping the elastic strap over your head.");
				}
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You take off your safety goggles.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " takes off <her> safety goggles.");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " grabs your safety goggles and pulls them off.";
					else
						return "You grab " + clothingOwner.getName("the") + "'s safety goggles and pull them off.";
				} else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " takes your safety goggles off.";
					else
						return "You take off " + clothingOwner.getName("the") + "'s safety goggles.";
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your safety goggles up onto your forehead.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> safety goggles up onto <her> forehead.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls up your safety goggles.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull up " + clothingOwner.getName("the") + "'s safety goggles.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your safety goggles back down over your eyes.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> safety goggles back down over <her> eyes.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your safety goggles down over your eyes.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s safety goggles down over <her> eyes.");
			}
		}
	},

	// MOUTH
	// Only mouth clothing is for BDSM at the moment

	// NECK
	// Choker, Collar

	NECK_HEART_NECKLACE("a", false, "heart necklace", "A necklace with a little heart-shaped pendant.", 0, Femininity.FEMININE, InventorySlot.NECK, Rarity.COMMON, null, "neck_heartNecklace", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null))),

			null, // List<InventorySlot> incompatibleSlots

			Util.newArrayListOfValues(new ListValue<Colour>(com.base.utils.Colour.CLOTHING_COPPER), new ListValue<Colour>(com.base.utils.Colour.CLOTHING_SILVER), new ListValue<Colour>(com.base.utils.Colour.CLOTHING_GOLD))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You put on the necklace, clipping the chain together at the back of your neck.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " puts a " + clothing.getName(true) + " around <her> neck, reaching around" + " to fasten the clip at the back.");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " holds your head still and fastens " + clothing.getName(true) + " around your neck.";
					else
						return UtilText.genderParsing(clothingOwner, "You hold " + clothingOwner.getName("the") + "'s head still as you fasten the necklace around <her> neck.");
				} else {
					if (clothingOwner.isPlayer())
						return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " clips " + clothing.getName(true) + " around your neck.");
					else
						return UtilText.genderParsing(clothingOwner, "You fasten the necklace around " + clothingOwner.getName("the") + "'s neck.");
				}
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You unclip your necklace and take it off.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " unclips <her> necklace and takes it off.");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " grabs your necklace and yanks it from your neck.";
					else
						return UtilText.genderParsing(clothingOwner, "You grab " + clothingOwner.getName("the") + "'s necklace and yank it from <her> neck.");
				} else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " unclips your necklace and removes it.";
					else
						return "You unclip " + clothingOwner.getName("the") + "'s necklace and take it off.";
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	NECK_ANKH_NECKLACE("an", false, "ankh necklace", "A necklace with a cross-shaped pendant, which has a loop instead of the top arm.", 0, null, InventorySlot.NECK, Rarity.COMMON, null, "neck_ankhNecklace", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null))),

			null, // List<InventorySlot> incompatibleSlots

			Util.newArrayListOfValues(new ListValue<Colour>(com.base.utils.Colour.CLOTHING_COPPER), new ListValue<Colour>(com.base.utils.Colour.CLOTHING_SILVER), new ListValue<Colour>(com.base.utils.Colour.CLOTHING_GOLD))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You put on the necklace, clipping the chain together at the back of your neck.";
				
			}else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] puts an ankh necklace around [npc.her] neck, before reaching around to fasten the clip at the back.");
				
			}else {
				if (rough) {
					if (clothingOwner.isPlayer()) {
						return UtilText.parse(clothingOwner, "[npc.Name] holds your head still and fastens an ahnk necklace around your neck.");
					} else {
						return UtilText.parse(clothingOwner, "You hold [npc.name]'s head still as you fasten the necklace around [npc.her] neck.");
					}
				} else {
					if (clothingOwner.isPlayer()) {
						return UtilText.parse(clothingOwner, "[npc.Name] clips an ahnk necklace around your neck.");
					} else {
						return UtilText.parse(clothingOwner, "You fasten the ahnk necklace around [npc.name]'s neck.");
					}
				}
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You unclip your necklace and take it off.";
				
			}else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] unclips [npc.her] ankh necklace and takes it off.");
				
			}else {
				if (rough) {
					if (clothingOwner.isPlayer()) {
						return UtilText.parse(clothingOwner, "[npc.Name] grabs your necklace and yanks it from your neck.");
					} else {
						return UtilText.parse(clothingOwner, "You grab [npc.name]'s necklace and yank it from [npc.her] neck.");
					}
				} else {
					if (clothingOwner.isPlayer()) {
						return UtilText.parse(clothingOwner, "[npc.Name] unclips your ankh necklace and removes it.");
					} else {
						return UtilText.parse(clothingOwner, "You unclip [npc.name]'s necklace and take it off.");
					}
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	NECK_SLAVE_COLLAR("a", false, "slave collar", "A heavy metal collar, of the type worn by slaves. It carries a potent enchantment that suppresses the wearer's arcane aura and ability to resist direct commands.", // TODO
			0, null, InventorySlot.NECK, Rarity.EPIC, null, "neck_slave_collar",
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, -100), new Value<Attribute, Integer>(Attribute.RESISTANCE_MANA, -100), new Value<Attribute, Integer>(Attribute.SPELL_COST_MODIFIER, -100)),

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null))),

			null, // List<InventorySlot> incompatibleSlots

			Util.newArrayListOfValues(new ListValue<Colour>(com.base.utils.Colour.CLOTHING_COPPER), new ListValue<Colour>(com.base.utils.Colour.CLOTHING_SILVER), new ListValue<Colour>(com.base.utils.Colour.CLOTHING_GOLD))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {

			if(applyEffects) {
				clothing.setSealed(true);
			}
			
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You put on the slave collar, fastening the clasp at the back of your neck.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " puts a " + clothing.getName(true) + " around <her> neck, reaching around" + " to fasten the clasp at the back.");
			else {
				if (clothingOwner.isPlayer())
					return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " fastens " + clothing.getName(true) + " around your neck.");
				else
					return UtilText.genderParsing(clothingOwner, "You fasten the slave collar around " + clothingOwner.getName("the") + "'s neck.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You unfasten the slave collar and take it off.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " unfastens <her> slave collar and takes it off.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " unfastens your slave collar and removes it from around your neck.";
				else
					return UtilText.genderParsing(clothingOwner, "You unfasten " + clothingOwner.getName("the") + "'s slave collar and remove it from <her> neck.");

			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},

	// TORSO

	TORSO_OXFORD_SHIRT("a", false, "long-sleeved shirt", "A men's long-sleeved shirt.", 1, Femininity.MASCULINE, InventorySlot.TORSO, Rarity.COMMON, null, "torso_oxfordShirt", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)), null,
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)))),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.UNBUTTONS, null, Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.CHEST), new ListValue<ClothingAccess>(ClothingAccess.WAIST))))),

			null, // List<InventorySlot> incompatibleSlots

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You put your arms through the shirt's sleeves and button it up.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " puts on " + clothing.getName(true) + ", pushing <her> " + clothingOwner.getArmName() + " through the" + " sleeves before buttoning it up.");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " pushes your " + clothingOwner.getArmName() + " through " + clothing.getName(true) + "'s sleeves and hasilty buttons it up.";
					else
						return UtilText.genderParsing(clothingOwner,
								"You push " + clothingOwner.getName("the") + "'s " + clothingOwner.getArmName() + " through the shirt's sleeves and hastily button it up.");
				} else {
					if (clothingOwner.isPlayer())
						return UtilText.genderParsing(clothingOwner,
								Util.capitaliseSentence(clothingOwner.getName("the")) + " guides your " + clothingOwner.getArmName() + " through " + clothing.getName(true) + "'s sleeves" + " and buttons it up.");
					else
						return UtilText.genderParsing(clothingOwner,
								"You guide " + clothingOwner.getName("the") + "'s " + clothingOwner.getArmName() + " through the shirt's sleeves and button it up.");
				}
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You unbutton your shirt and pull it off.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " unbuttons <her> shirt and pulls it off.");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						if (clothing.getDisplacedList().contains(DisplacementType.UNBUTTONS))
							return Util.capitaliseSentence(clothingRemover.getName("the")) + " grabs your open shirt and yanks it down and off your " + clothingOwner.getArmName() + ".";
						else
							return Util.capitaliseSentence(clothingRemover.getName("the")) + " yanks your shirt up and over your head.";
					else if (clothing.getDisplacedList().contains(DisplacementType.UNBUTTONS))
						return UtilText.genderParsing(clothingOwner, "You grab " + clothingOwner.getName("the") + "'s open shirt and yank it down and off <her> " + clothingOwner.getArmName() + ".");
					else
						return UtilText.genderParsing(clothingOwner, "You yank " + clothingOwner.getName("the") + "'s shirt up and over <her> head.");
				} else {
					if (clothingOwner.isPlayer())
						if (clothing.getDisplacedList().contains(DisplacementType.UNBUTTONS))
							return Util.capitaliseSentence(clothingRemover.getName("the")) + " slides your " + clothingOwner.getArmName() + " out of your open shirt.";
						else
							return Util.capitaliseSentence(clothingRemover.getName("the")) + " unbuttons your shirt and slides your " + clothingOwner.getArmName() + " out of it.";
					else if (clothing.getDisplacedList().contains(DisplacementType.UNBUTTONS))
						return UtilText.genderParsing(clothingOwner, "You slide " + clothingOwner.getName("the") + "'s " + clothingOwner.getArmName() + " out of <her> open shirt.");
					else
						return UtilText.genderParsing(clothingOwner, "You unbutton " + clothingOwner.getName("the") + "'s shirt and slide <her> " + clothingOwner.getArmName() + " out of it.");
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You unbutton your shirt, leaving it hanging open.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " unbuttons <her> shirt, leaving it hanging open.");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " roughly pulls open your shirt, almost tearing the buttons right off.";
					else
						return UtilText.genderParsing(clothingOwner, "You roughly pull open " + clothingOwner.getName("the") + "'s shirt, almost tearing the buttons off in the process.");
				} else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " unbuttons your shirt.";
					else
						return UtilText.genderParsing(clothingOwner, "You unbutton " + clothingOwner.getName("the") + "'s shirt.");
				}
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You button up your shirt.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " buttons up <her> shirt.");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " hastily buttons up your shirt.";
					else
						return UtilText.genderParsing(clothingOwner, "You hastily button up " + clothingOwner.getName("the") + "'s shirt.");
				} else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " buttons up your shirt.";
					else
						return UtilText.genderParsing(clothingOwner, "You button up " + clothingOwner.getName("the") + "'s shirt.");
				}
			}
		}
	},
	TORSO_SHORT_SLEEVE_SHIRT("a", false, "short-sleeved shirt", "A unisex short-sleeved shirt.", 1, null, InventorySlot.TORSO, Rarity.COMMON, null, "torso_shortSleeveShirt", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)), null,
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)))),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.UNBUTTONS, null, Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.CHEST), new ListValue<ClothingAccess>(ClothingAccess.WAIST))))),

			null, // List<InventorySlot> incompatibleSlots

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You put your " + clothingOwner.getArmName() + " through the shirt's sleeves and button it up.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " puts on " + clothing.getName(true) + ", pushing <her> " + clothingOwner.getArmName() + " through the" + " sleeves before buttoning it up.");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " pushes your " + clothingOwner.getArmName() + " through " + clothing.getName(true) + "'s sleeves and hasilty buttons it up.";
					else
						return UtilText.genderParsing(clothingOwner,
								"You push " + clothingOwner.getName("the") + "'s " + clothingOwner.getArmName() + " through the shirt's sleeves and hastily button it up.");
				} else {
					if (clothingOwner.isPlayer())
						return UtilText.genderParsing(clothingOwner,
								Util.capitaliseSentence(clothingOwner.getName("the")) + " guides your " + clothingOwner.getArmName() + " through " + clothing.getName(true) + "'s sleeves" + " and buttons it up.");
					else
						return UtilText.genderParsing(clothingOwner,
								"You guide " + clothingOwner.getName("the") + "'s " + clothingOwner.getArmName() + " through the shirt's sleeves and button it up.");
				}
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You unbutton your shirt and pull it off.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " unbuttons <her> shirt and pulls it off.");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						if (clothing.getDisplacedList().contains(DisplacementType.UNBUTTONS))
							return Util.capitaliseSentence(clothingRemover.getName("the")) + " grabs your open shirt and yanks it down and off your " + clothingOwner.getArmName() + ".";
						else
							return Util.capitaliseSentence(clothingRemover.getName("the")) + " yanks your shirt up and over your head.";
					else if (clothing.getDisplacedList().contains(DisplacementType.UNBUTTONS))
						return UtilText.genderParsing(clothingOwner, "You grab " + clothingOwner.getName("the") + "'s open shirt and yank it down and off <her> " + clothingOwner.getArmName() + ".");
					else
						return UtilText.genderParsing(clothingOwner, "You yank " + clothingOwner.getName("the") + "'s shirt up and over <her> head.");
				} else {
					if (clothingOwner.isPlayer())
						if (clothing.getDisplacedList().contains(DisplacementType.UNBUTTONS))
							return Util.capitaliseSentence(clothingRemover.getName("the")) + " slides your " + clothingOwner.getArmName() + " out of your open shirt.";
						else
							return Util.capitaliseSentence(clothingRemover.getName("the")) + " unbuttons your shirt and slides your " + clothingOwner.getArmName() + " out of it.";
					else if (clothing.getDisplacedList().contains(DisplacementType.UNBUTTONS))
						return UtilText.genderParsing(clothingOwner, "You slide " + clothingOwner.getName("the") + "'s " + clothingOwner.getArmName() + " out of <her> open shirt.");
					else
						return UtilText.genderParsing(clothingOwner, "You unbutton " + clothingOwner.getName("the") + "'s shirt and slide <her> " + clothingOwner.getArmName() + " out of it.");
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You unbutton your shirt, leaving it hanging open.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " unbuttons <her> shirt, leaving it hanging open.");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " roughly pulls open your shirt, almost tearing the buttons right off.";
					else
						return UtilText.genderParsing(clothingOwner, "You roughly pull open " + clothingOwner.getName("the") + "'s shirt, almost tearing the buttons off in the process.");
				} else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " unbuttons your shirt.";
					else
						return UtilText.genderParsing(clothingOwner, "You unbutton " + clothingOwner.getName("the") + "'s shirt.");
				}
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You button up your shirt.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " buttons up <her> shirt.");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " hastily buttons up your shirt.";
					else
						return UtilText.genderParsing(clothingOwner, "You hastily button up " + clothingOwner.getName("the") + "'s shirt.");
				} else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " buttons up your shirt.";
					else
						return UtilText.genderParsing(clothingOwner, "You button up " + clothingOwner.getName("the") + "'s shirt.");
				}
			}
		}
	},
	TORSO_HOODIE("a", false, "hoodie", "A loose-fitting hoodie.", 1, null, InventorySlot.TORSO, Rarity.COMMON, null, "torso_hoodie", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER), new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)))),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.PULLS_UP, null, Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.CHEST), new ListValue<ClothingAccess>(ClothingAccess.WAIST))))),

			null, // List<InventorySlot> incompatibleSlots

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the hoodie.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls on " + clothing.getName(true) + ".");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " yanks " + clothing.getName(true) + " down over your head, pushing your " + clothingOwner.getArmName() + " through the sleeves.";
					else
						return UtilText.genderParsing(clothingOwner,
								"You yank the hoodie down over " + clothingOwner.getName("the") + "'s head, and push <her> " + clothingOwner.getArmName() + " through the sleeves.");
				} else {
					if (clothingOwner.isPlayer())
						return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " guides your " + clothingOwner.getArmName() + " through "
								+ clothing.getName(true) + "'s sleeves" + " as <she> pulls it down over your head.");
					else
						return UtilText.genderParsing(clothingOwner,
								"You guide " + clothingOwner.getName("the") + "'s " + clothingOwner.getArmName() + " through the hoodie's sleeves as you pull it down over <her> head.");
				}
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your hoodie.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls off <her> hoodie.");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						if (clothing.getDisplacedList().contains(DisplacementType.PULLS_UP))
							return Util.capitaliseSentence(clothingRemover.getName("the")) + " finishes pulling your hoodie up and over your head.";
						else
							return Util.capitaliseSentence(clothingRemover.getName("the")) + " yanks your hoodie up and over your head.";
					else if (clothing.getDisplacedList().contains(DisplacementType.PULLS_UP))
						return UtilText.genderParsing(clothingOwner, "You finish pulling " + clothingOwner.getName("the") + "'s hoodie up and over <her> head.");
					else
						return UtilText.genderParsing(clothingOwner, "You yank " + clothingOwner.getName("the") + "'s hoodie up and over <her> head.");
				} else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " slides your hoodie up and over your head.";
					else
						return UtilText.genderParsing(clothingOwner, "You slide " + clothingOwner.getName("the") + "'s hoodie up and over <her> head.");
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your hoodie up to just below your chin, its elastic rim holding it in place.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> hoodie up to just below <her> chin," + " its elastic rim holding it in place.");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " roughly yanks up your hoodie.";
					else
						return UtilText.genderParsing(clothingOwner, "You roughly yank up " + clothingOwner.getName("the") + "'s hoodie.");
				} else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls up your hoodie.";
					else
						return UtilText.genderParsing(clothingOwner, "You pull up " + clothingOwner.getName("the") + "'s hoodie.");
				}
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your hoodie back down, covering your torso.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> hoodie back down to cover <her> torso.");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " roughly pulls down your hoodie.";
					else
						return UtilText.genderParsing(clothingOwner, "You roughly pull down " + clothingOwner.getName("the") + "'s hoodie.");
				} else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls down your hoodie.";
					else
						return UtilText.genderParsing(clothingOwner, "You pull down " + clothingOwner.getName("the") + "'s hoodie.");
				}
			}
		}
	},
	
	TORSO_RIBBED_SWEATER("a", false, "ribbed sweater", "A unisex ribbed sweater, made from some sort of wooly fabric.", 1, null, InventorySlot.TORSO, Rarity.COMMON, null, "torso_ribbed_sweater", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
									new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)))),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST))))),

			null, // List<InventorySlot> incompatibleSlots

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the sweater.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls on " + clothing.getName(true) + ".");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " yanks " + clothing.getName(true) + " down over your head, pushing your " + clothingOwner.getArmName() + " through the sleeves.";
					else
						return UtilText.genderParsing(clothingOwner,
								"You yank the sweater down over " + clothingOwner.getName("the") + "'s head, and push <her> " + clothingOwner.getArmName() + " through the sleeves.");
				} else {
					if (clothingOwner.isPlayer())
						return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " guides your " + clothingOwner.getArmName() + " through "
								+ clothing.getName(true) + "'s sleeves" + " as <she> pulls it down over your head.");
					else
						return UtilText.genderParsing(clothingOwner,
								"You guide " + clothingOwner.getName("the") + "'s " + clothingOwner.getArmName() + " through the sweater's sleeves as you pull it down over <her> head.");
				}
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your sweater.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls off <her> sweater.");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						if (clothing.getDisplacedList().contains(DisplacementType.PULLS_UP))
							return Util.capitaliseSentence(clothingRemover.getName("the")) + " finishes pulling your sweater up and over your head.";
						else
							return Util.capitaliseSentence(clothingRemover.getName("the")) + " yanks your sweater up and over your head.";
					else if (clothing.getDisplacedList().contains(DisplacementType.PULLS_UP))
						return UtilText.genderParsing(clothingOwner, "You finish pulling " + clothingOwner.getName("the") + "'s sweater up and over <her> head.");
					else
						return UtilText.genderParsing(clothingOwner, "You yank " + clothingOwner.getName("the") + "'s sweater up and over <her> head.");
				} else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " slides your sweater up and over your head.";
					else
						return UtilText.genderParsing(clothingOwner, "You slide " + clothingOwner.getName("the") + "'s sweater up and over <her> head.");
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your sweater up to just below your chin, its elastic rim holding it in place.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> sweater up to just below <her> chin," + " its elastic rim holding it in place.");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " roughly yanks up your sweater.";
					else
						return UtilText.genderParsing(clothingOwner, "You roughly yank up " + clothingOwner.getName("the") + "'s sweater.");
				} else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls up your sweater.";
					else
						return UtilText.genderParsing(clothingOwner, "You pull up " + clothingOwner.getName("the") + "'s sweater.");
				}
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your sweater back down, covering your torso.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> sweater back down to cover <her> torso.");
			else {
				if (rough) {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " roughly pulls down your sweater.";
					else
						return UtilText.genderParsing(clothingOwner, "You roughly pull down " + clothingOwner.getName("the") + "'s sweater.");
				} else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls down your sweater.";
					else
						return UtilText.genderParsing(clothingOwner, "You pull down " + clothingOwner.getName("the") + "'s sweater.");
				}
			}
		}
	},
	
	TORSO_TSHIRT("a", false, "T-shirt", "A plain T-shirt that would look good on any figure.", 1, null, InventorySlot.TORSO, Rarity.COMMON, null, "torso_tshirt", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER), new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)))),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.PULLS_UP, null, Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.CHEST), new ListValue<ClothingAccess>(ClothingAccess.WAIST))))),

			null, // List<InventorySlot> incompatibleSlots

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the t-shirt.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls on " + clothing.getName(true) + ".");
			else {
				if (clothingOwner.isPlayer())
					return UtilText.genderParsing(clothingOwner,
							Util.capitaliseSentence(clothingOwner.getName("the")) + " guides your " + clothingOwner.getArmName() + " through " + clothing.getName(true) + "'s sleeves" + " as <she> pulls it down over your head.");
				else
					return UtilText.genderParsing(clothingOwner,
							"You guide " + clothingOwner.getName("the") + "'s " + clothingOwner.getArmName() + " through the t-shirt's sleeves as you pull it down over <her> head.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your t-shirt.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls off <her> t-shirt.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " slides your t-shirt up and over your head.";
				else
					return UtilText.genderParsing(clothingOwner, "You slide " + clothingOwner.getName("the") + "'s t-shirt up and over <her> head.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your t-shirt up to just below your chin, exposing your torso.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> t-shirt up to just below <her> chin," + " exposing <her> torso.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls up your t-shirt.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull up " + clothingOwner.getName("the") + "'s t-shirt.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your t-shirt back down, covering your torso.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> t-shirt back down to cover <her> torso.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls down your t-shirt.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull down " + clothingOwner.getName("the") + "'s t-shirt.");
			}
		}
	},
	TORSO_KEYHOLE_CROPTOP("a", false, "keyhole crop top", "A small, sleeveless crop top with a stylish cutout that reveals some cleavage.", 1, Femininity.FEMININE, InventorySlot.TORSO, Rarity.COMMON, null, "torso_keyhole_croptop", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER), new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)))),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.PULLS_UP, null, Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.CHEST), new ListValue<ClothingAccess>(ClothingAccess.WAIST))))),

			null, // List<InventorySlot> incompatibleSlots

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the crop top.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls on " + clothing.getName(true) + ".");
			else {
				if (clothingOwner.isPlayer())
					return UtilText.genderParsing(clothingOwner,
							Util.capitaliseSentence(clothingOwner.getName("the")) + " guides your " + clothingOwner.getArmName() + " through " + clothing.getName(true) + "'s sleeves" + " as <she> pulls it down over your head.");
				else
					return UtilText.genderParsing(clothingOwner,
							"You guide " + clothingOwner.getName("the") + "'s " + clothingOwner.getArmName() + " through the crop top's sleeves as you pull it down over <her> head.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your crop top.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls off <her> crop top.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " slides your crop top up and over your head.";
				else
					return UtilText.genderParsing(clothingOwner, "You slide " + clothingOwner.getName("the") + "'s crop top up and over <her> head.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your crop top up to just below your chin, exposing your torso.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> crop top up to just below <her> chin," + " exposing <her> torso.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls up your crop top.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull up " + clothingOwner.getName("the") + "'s crop top.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your crop top back down, covering your torso.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> crop top back down to cover <her> torso.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls down your crop top.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull down " + clothingOwner.getName("the") + "'s crop top.");
			}
		}
	},
	TORSO_SHORT_CROPTOP("a", false, "short croptop", "A small, sleeveless croptop that leaves its wearer's stomach completely exposed.", 1, Femininity.FEMININE, InventorySlot.TORSO, Rarity.COMMON, null, "torso_short_croptop", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER), new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)))),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_UP, null, Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.NIPPLES)), Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.CHEST))))),

			null, // List<InventorySlot> incompatibleSlots

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the crop top.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls on " + clothing.getName(true) + ".");
			else {
				if (clothingOwner.isPlayer())
					return UtilText.genderParsing(clothingOwner,
							Util.capitaliseSentence(clothingOwner.getName("the")) + " guides your " + clothingOwner.getArmName() + " through " + clothing.getName(true) + "'s sleeves" + " as <she> pulls it down over your head.");
				else
					return UtilText.genderParsing(clothingOwner,
							"You guide " + clothingOwner.getName("the") + "'s " + clothingOwner.getArmName() + " through the crop top's sleeves as you pull it down over <her> head.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your crop top.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls off <her> crop top.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " slides your crop top up and over your head.";
				else
					return UtilText.genderParsing(clothingOwner, "You slide " + clothingOwner.getName("the") + "'s crop top up and over <her> head.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your crop top up to just below your chin, exposing your chest.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> crop top up to just below <her> chin," + " exposing <her> chest.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls up your crop top.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull up " + clothingOwner.getName("the") + "'s crop top.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your crop top back down, covering your chest.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> crop top back down to cover <her> chest.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls down your crop top.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull down " + clothingOwner.getName("the") + "'s crop top.");
			}
		}
	},
	TORSO_FISHNET_TOP("a", false, "fishnet top", "A small fishnet top that leaves its wearer's stomach completely exposed, while not doing much to conceal anything else, either.",
			1, Femininity.FEMININE, InventorySlot.TORSO, Rarity.COMMON, null, "torso_fishnet_top", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
											new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
									null,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)))),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.PULLS_UP,
									null,
									null,
									Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.CHEST))))),

			null, // List<InventorySlot> incompatibleSlots

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the fishnet top.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls on " + clothing.getName(true) + ".");
			else {
				if (clothingOwner.isPlayer())
					return UtilText.genderParsing(clothingOwner,
							Util.capitaliseSentence(clothingOwner.getName("the")) + " guides your " + clothingOwner.getArmName() + " through " + clothing.getName(true) + "'s sleeves" + " as <she> pulls it down over your head.");
				else
					return UtilText.genderParsing(clothingOwner,
							"You guide " + clothingOwner.getName("the") + "'s " + clothingOwner.getArmName() + " through the fishnet top's sleeves as you pull it down over <her> head.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your crop top.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls off <her> fishnet top.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " slides your fishnet top up and over your head.";
				else
					return UtilText.genderParsing(clothingOwner, "You slide " + clothingOwner.getName("the") + "'s fishnet top up and over <her> head.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your fishnet top up to just below your chin, exposing your chest.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> fishnet top up to just below <her> chin," + " exposing <her> chest.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls up your fishnet top.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull up " + clothingOwner.getName("the") + "'s fishnet top.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your fishnet top back down, covering your chest.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> fishnet top back down to cover <her> chest.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls down your fishnet top.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull down " + clothingOwner.getName("the") + "'s fishnet top.");
			}
		}
	},
	TORSO_BLOUSE("a", false, "blouse", "A delicate blouse, made of very fine fabric.", 1, Femininity.FEMININE, InventorySlot.TORSO, Rarity.COMMON, null, "torso_blouse", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER), new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)))),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.PULLS_UP, null, Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.CHEST), new ListValue<ClothingAccess>(ClothingAccess.WAIST))))),

			null, // List<InventorySlot> incompatibleSlots

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the blouse.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls on " + clothing.getName(true) + ".");
			else {
				if (clothingOwner.isPlayer())
					return UtilText.genderParsing(clothingOwner,
							Util.capitaliseSentence(clothingOwner.getName("the")) + " guides your " + clothingOwner.getArmName() + " through " + clothing.getName(true) + "'s sleeves" + " as <she> pulls it down over your head.");
				else
					return UtilText.genderParsing(clothingOwner,
							"You guide " + clothingOwner.getName("the") + "'s " + clothingOwner.getArmName() + " through the blouse's sleeves as you pull it down over <her> head.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your blouse.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls off <her> blouse.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " slides your blouse up and over your head.";
				else
					return UtilText.genderParsing(clothingOwner, "You slide " + clothingOwner.getName("the") + "'s blouse up and over <her> head.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your blouse up to just below your chin, exposing your torso.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> blouse up to just below <her> chin," + " exposing <her> torso.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls up your blouse.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull up " + clothingOwner.getName("the") + "'s blouse.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your blouse back down, covering your torso.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> blouse back down to cover <her> torso.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls down your blouse.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull down " + clothingOwner.getName("the") + "'s blouse.");
			}
		}
	},
	TORSO_OPEN_CARDIGAN("an", false, "open-front cardigan", "A very feminine, open-front cardigan. It's made from a thin, wooly fabric.", 1, Femininity.FEMININE, InventorySlot.TORSO, Rarity.COMMON, null, "torso_open_cardigan", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)),
									null,
									Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER))
									))),

			null, // List<InventorySlot> incompatibleSlots

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the cardigan.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls on " + clothing.getName(true) + ".");
			else {
				if (clothingOwner.isPlayer())
					return UtilText.genderParsing(clothingOwner,
							Util.capitaliseSentence(clothingOwner.getName("the")) + " guides your " + clothingOwner.getArmName() + " through " + clothing.getName(true) + "'s sleeves as <she> pulls it on for you.");
				else
					return UtilText.genderParsing(clothingOwner,
							"You guide " + clothingOwner.getName("the") + "'s " + clothingOwner.getArmName() + " through the cardigan's sleeves as you pull it on for <herPro>.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your cardigan.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls off <her> cardigan.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " slides your cardigan off.";
				else
					return UtilText.genderParsing(clothingOwner, "You slide " + clothingOwner.getName("the") + "'s cardigan off.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	TORSO_CAMITOP_STRAPS("a", false, "cami top", "A short cami top with straps that loop over the wearer's shoulders. It's short enough that the wearer's stomach is left on display.", 1, Femininity.FEMININE, InventorySlot.TORSO, Rarity.COMMON, null,
			"torso_cami_straps", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER), new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							null, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)))),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_UP, null, Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.NIPPLES)), Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.CHEST))))),

			null, // List<InventorySlot> incompatibleSlots

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the cami top.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls on " + clothing.getName(true) + ".");
			else {
				if (clothingOwner.isPlayer())
					return UtilText.genderParsing(clothingOwner,
							Util.capitaliseSentence(clothingOwner.getName("the")) + " guides your " + clothingOwner.getArmName() + " through " + clothing.getName(true) + "'s sleeves" + " as <she> pulls it down over your head.");
				else
					return UtilText.genderParsing(clothingOwner,
							"You guide " + clothingOwner.getName("the") + "'s " + clothingOwner.getArmName() + " through the cami top's sleeves as you pull it down over <her> head.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your cami top.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls off <her> cami top.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " slides your cami top up and over your head.";
				else
					return UtilText.genderParsing(clothingOwner, "You slide " + clothingOwner.getName("the") + "'s cami top up and over <her> head.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your cami top up to just below your chin, exposing your chest.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> cami top up to just below <her> chin," + " exposing <her> chest.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls up your cami top.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull up " + clothingOwner.getName("the") + "'s cami top.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your cami top back down, covering your chest.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> cami top back down to cover <her> chest.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls down your cami top.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull down " + clothingOwner.getName("the") + "'s cami top.");
			}
		}
	},
	TORSO_SKATER_DRESS("a", false, "skater dress", "A sleeveless skater dress, held up by a pair of thin straps that loop over the wearer's shoulders.", 1, Femininity.FEMININE, InventorySlot.TORSO, Rarity.COMMON, null, "torso_skater_dress", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER), new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER), new ListValue<ClothingAccess>(ClothingAccess.CHEST), new ListValue<ClothingAccess>(ClothingAccess.WAIST)))),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.PULLS_UP, null,
							Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.ANUS), new ListValue<CoverableArea>(CoverableArea.PENIS), new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN))))),

			Util.newArrayListOfValues(new ListValue<InventorySlot>(InventorySlot.LEG)),

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the skater dress, tidying the skirt down before moving the straps into a comfortable position on your shoulders.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls on " + clothing.getName(true) + ", tidying the skirt down before moving the straps into a comfortable position on <her> shoulders.");
			else {
				if (clothingOwner.isPlayer())
					return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls " + clothing.getName(true)
							+ " over your head and down around your torso, tidying the skirt before moving the" + " straps to sit comfortably on your shoulders.");
				else
					return UtilText.genderParsing(clothingOwner,
							"You pull the skater dress over " + clothingOwner.getName("the") + "'s head and down around <her> torso, tidying the skirt before moving the" + " straps to sit comfortably on <her> shoulders.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your skater dress up over your head and take it off.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> skater dress up over <her> head and takes it off.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your skater dress up over your head and takes it off.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s skater dress up over <her> head and take it off.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (dt == DisplacementType.PULLS_DOWN) {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull down the top of your skater dress.";
				else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
					return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down the top of <her> skater dress.");
				else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls down the top of your skater dress.";
					else
						return UtilText.genderParsing(clothingOwner, "You pull down the top of " + clothingOwner.getName("the") + "'s skater dress.");
				}
			} else {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull up the skirt of your skater dress.";
				else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
					return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls up the skirt of <her> skater dress.");
				else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls up the skirt of your skater dress.";
					else
						return UtilText.genderParsing(clothingOwner, "You pull up the skirt of " + clothingOwner.getName("the") + "'s skater dress.");
				}
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (dt == DisplacementType.PULLS_DOWN) {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull your skater dress back up.";
				else
					return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls up <her> skater dress.");
			} else {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull your skater dress's skirt back down.";
				else
					return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> skater dress's skirt back down.");
			}
		}
	},
	TORSO_SLIP_DRESS("a", false, "slip dress", "A long, silky, sleeveless dress.", 1, Femininity.FEMININE, InventorySlot.TORSO, Rarity.COMMON, null, "torso_slip_dress", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)),
							Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER), new ListValue<ClothingAccess>(ClothingAccess.CHEST), new ListValue<ClothingAccess>(ClothingAccess.WAIST)))),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.PULLS_UP, null,
							Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.ANUS), new ListValue<CoverableArea>(CoverableArea.PENIS), new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN))))),

			Util.newArrayListOfValues(new ListValue<InventorySlot>(InventorySlot.LEG)),

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the slip dress and pull it up around your torso. Once in place, you reach back and zip yourself up.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " steps into " + clothing.getName(true) + ", pulling it up around <her> torso." + " Once in place, <she> reaches back to zip <herPro>self up.");
			else {
				if (clothingOwner.isPlayer())
					return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " guides " + clothing.getName(true) + " around your "
							+ clothingOwner.getLegName() + " before pulling it up around your torso. One it's in place, <she> zips it up at the back.");
				else
					return UtilText.genderParsing(clothingOwner,
							"You guide the slip dress up around " + clothingOwner.getName("the") + "'s " + clothingOwner.getLegName() + " before pulling it up around <her> torso. Once it's in place, you zip <herPro> up at the back.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You unzip your slip and wriggle out of it as it drops to your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " unzips <her> slip dress and wriggles out of it as it drops to <her> feet.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " unzips your slip dress and pulls it your body and past your feet.";
				else
					return UtilText.genderParsing(clothingOwner, "You unzip " + clothingOwner.getName("the") + "'s slip dress and pull it down off <her> body and past <her> feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull up your slip dress's skirt.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls up the lower half of <her> slip dress.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls up the lower half of your slip dress.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull up the lower half of " + clothingOwner.getName("the") + "'s slip dress.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your slip dress back down.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> slip dress back down.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your slip dress back down.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull back down " + clothingOwner.getName("the") + "'s slip dress.");
			}
		}
	},
	
	TORSO_PLUNGE_DRESS("a", false, "plunge dress", "An elegant dress with a plunging v-neckline, perfect for showing off its wearer's cleavage.", 1, Femininity.FEMININE, InventorySlot.TORSO, Rarity.COMMON, null, "torso_plunge_dress", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)),
							Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER), new ListValue<ClothingAccess>(ClothingAccess.CHEST), new ListValue<ClothingAccess>(ClothingAccess.WAIST)))),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.PULLS_UP, null,
							Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.ANUS), new ListValue<CoverableArea>(CoverableArea.PENIS), new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN))))),

			Util.newArrayListOfValues(new ListValue<InventorySlot>(InventorySlot.LEG)),

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the plunge dress and pull it up around your torso. Once in place, you reach back and zip yourself up.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " steps into " + clothing.getName(true) + ", pulling it up around <her> torso." + " Once in place, <she> reaches back to zip <herPro>self up.");
			else {
				if (clothingOwner.isPlayer())
					return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " guides " + clothing.getName(true) + " around your "
							+ clothingOwner.getLegName() + " before pulling it up around your torso. One it's in place, <she> zips it up at the back.");
				else
					return UtilText.genderParsing(clothingOwner,
							"You guide the plunge dress up around " + clothingOwner.getName("the") + "'s " + clothingOwner.getLegName() + " before pulling it up around <her> torso. Once it's in place, you zip <herPro> up at the back.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You unzip your plunge dress and wriggle out of it as it drops to your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " unzips <her> plunge dress and wriggles out of it as it drops to <her> feet.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " unzips your plunge dress and pulls it your body and past your feet.";
				else
					return UtilText.genderParsing(clothingOwner, "You unzip " + clothingOwner.getName("the") + "'s plunge dress and pull it down off <her> body and past <her> feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull up your plunge dress's skirt.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls up the lower half of <her> plunge dress.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls up the lower half of your plunge dress.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull up the lower half of " + clothingOwner.getName("the") + "'s plunge dress.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your plunge dress back down.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> plunge dress back down.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your plunge dress back down.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull back down " + clothingOwner.getName("the") + "'s plunge dress.");
			}
		}
	},
	
	TORSO_LONG_SLEEVE_DRESS("a", false, "long-sleeved dress", "A long-sleeved bodycon dress with a high neck.", 1, Femininity.FEMININE, InventorySlot.TORSO, Rarity.COMMON, null, "torso_long_sleeve_dress", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)),
							Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER), new ListValue<ClothingAccess>(ClothingAccess.CHEST), new ListValue<ClothingAccess>(ClothingAccess.WAIST)))),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.PULLS_UP, null,
							Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.ANUS), new ListValue<CoverableArea>(CoverableArea.PENIS), new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN))))),

			Util.newArrayListOfValues(new ListValue<InventorySlot>(InventorySlot.LEG)),

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You step into the dress and pull it up around your torso. Once in place, you reach back and zip yourself up.";
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] steps into " + clothing.getName(true) + ", before pulling it up around [npc.her] torso. Once in place, [npc.she] reaches back to zip [npc.herself] up.");
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingOwner, "[npc.Name] guides " + clothing.getName(true) + " up around your [pc.legs]. Once in place, [npc.she] reaches back to zip it up.");
				} else {
					return UtilText.parse(clothingOwner, "Your guide " + clothing.getName(true) + " up around [npc.name]'s [npc.legs]. Once in place, you reach back to zip [npc.herHim] up.");
				}
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You unzip your dress and wriggle out of it as it drops to your feet.";
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] unzips [npc.her] " + clothing.getName(true) + " and wriggles out of it.");
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingOwner, "[npc.Name] unzips your dress and pulls it down to the ground.");
				} else {
					return UtilText.parse(clothingOwner, "You unzip [npc.name]'s dress and pull it down to the ground.");
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull up the lower half of your dress.";
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] pulls up the lower half of [npc.her] dress.");
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingOwner, "[npc.Name] pulls up the lower half of your dress.");
				} else {
					return UtilText.parse(clothingOwner, "You pull up the lower half of [npc.name]'s dress.");
				}
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull the lower half of your dress back down.";
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] pulls the lower half of [npc.her] dress back down.");
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingOwner, "[npc.Name] pulls the lower half of your dress back down.");
				} else {
					return UtilText.parse(clothingOwner, "You pull the lower half of [npc.name]'s dress back down.");
				}
			}
		}
	},
	
	TORSO_BODYCONZIP_DRESS("a", false, "frontal-zip dress", "A tight fitting bodycon dress with a zip that runs the entire way up the front.", 1, Femininity.FEMININE, InventorySlot.TORSO, Rarity.COMMON, null, "torso_bodyconzip_dress", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)), null,
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)))),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.PULLS_UP, null,
							Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.ANUS), new ListValue<CoverableArea>(CoverableArea.PENIS), new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN)))),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.UNZIPS, null, Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.CHEST), new ListValue<ClothingAccess>(ClothingAccess.WAIST))))),

			Util.newArrayListOfValues(new ListValue<InventorySlot>(InventorySlot.LEG)),

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You put on the dress and zip yourself up.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " puts on " + clothing.getName(true) + " and zips <herPro>self up.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You fully unzip your bodycon dress and shrug it off.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " fully unzips <her> bodycon dress and shrugs it off.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " fully unzips your bodycon dress and pulls it off.";
				else
					return UtilText.genderParsing(clothingOwner, "You fully unzip " + clothingOwner.getName("the") + "'s bodycon dress and pull it off.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (dt == DisplacementType.UNZIPS) {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You unzip the top of your bodycon dress.";
				else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
					return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " unzips the top of <her> bodycon dress.");
				else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " unzips the top of your bodycon dress.";
					else
						return UtilText.genderParsing(clothingOwner, "You unzip the top of " + clothingOwner.getName("the") + "'s bodycon dress.");
				}
			} else {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull up the bottom of your bodycon dress.";
				else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
					return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls up the bottom of <her> bodycon dress.");
				else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls up the bottom of your bodycon dress.";
					else
						return UtilText.genderParsing(clothingOwner, "You pull up the bottom of " + clothingOwner.getName("the") + "'s bodycon dress.");
				}
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (dt == DisplacementType.UNZIPS) {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You zip up your bodycon dress.";
				else
					return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " zips up <her> bodycon dress.");
			} else {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull your bodycon dress back down.";
				else
					return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> bodycon dress back down.");
			}
		}
	},

	// CHEST

	CHEST_SWIMSUIT("a", false, "one-piece swimsuit", "A sporty one-piece swimsuit.", 1, Femininity.FEMININE, InventorySlot.CHEST, Rarity.COMMON, null, "chest_swimsuit", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST),
									new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
							null,
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.WAIST)))),
					
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.SHIFTS_ASIDE,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.ANUS),
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN)))),
					
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_DOWN,
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
							Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.CHEST))))),

			Util.newArrayListOfValues(new ListValue<InventorySlot>(InventorySlot.GROIN), new ListValue<InventorySlot>(InventorySlot.STOMACH)),

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the swimsuit and pull it up over your torso.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " steps into " + clothing.getName(true) + " and pulls it up to cover <her> torso.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You slide the swimsuit's straps down your arms and tug it down off your legs.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " slides the swimsuit's straps down <her> arms and tugs it down off <her> legs.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " tugs your swimsuit down, sliding it off your legs.";
				else
					return UtilText.genderParsing(clothingOwner, "You tug down " + clothingOwner.getName("the") + "'s swimsuit, sliding it off <her> legs.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (dt == DisplacementType.PULLS_DOWN) {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull down the top half of your swimsuit.";
				else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
					return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down the top half of <her> swimsuit.");
				else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls down the top half of your swimsuit.";
					else
						return UtilText.genderParsing(clothingOwner, "You pull down the top half of " + clothingOwner.getName("the") + "'s swimsuit.");
				}
			} else {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull the lower part of your swimsuit to one side.";
				else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
					return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls the lower part of <her> swimsuit to one side.");
				else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls the lower part of your swimsuit to one side.";
					else
						return UtilText.genderParsing(clothingOwner, "You pull the lower part of " + clothingOwner.getName("the") + "'s swimsuit to one side.");
				}
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (dt == DisplacementType.PULLS_DOWN) {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull your swimsuit back up into its correct position.";
				else
					return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> swimsuit up into its correct position.");
			} else {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull the lower part of your swimsuit back into its correct position.";
				else
					return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls the lower part of <her> swimsuit back into its correct position.");
			}
		}
	},
	CHEST_CHEMISE("a", false, "chemise", "A silky chemise, of the sort usually worn as part of a sexy-lingerie outfit.", 1, Femininity.FEMININE, InventorySlot.CHEST, Rarity.COMMON, null, "chest_chemise", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
									null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_UP,
									null,
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS),
											new ListValue<CoverableArea>(CoverableArea.PENIS),
											new ListValue<CoverableArea>(CoverableArea.VAGINA)),
									Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN))))),

			null,

			Colour.lingerieColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the chemise.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls on the chemise.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off the chemise.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls off <her> chemise.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls off your chemise.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull off " + clothingOwner.getName("the") + "'s chemise.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull up the bottom of your chemise.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls up the bottom of <her> chemise.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls up the bottom of your chemise.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull up the bottom of " + clothingOwner.getName("the") + "'s chemise.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull the bottom of your chemise back down.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls the bottom of <her> chemise back down.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls the bottom of your chemise back down.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull down the bottom of " + clothingOwner.getName("the") + "'s chemise.");
			}
		}
	},
	CHEST_PLUNGE_BRA("a", false, "plunge bra", "A low-cut bra that reveals a lot of the wearer's cleavage.", 1, Femininity.FEMININE, InventorySlot.CHEST, Rarity.COMMON, null, "chest_plunge_bra", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									null,
									null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_DOWN,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST))
									))
					),

			null,

			Colour.lingerieColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You place the bra over your chest before fastening the straps at your back.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " places " + clothing.getName(true) + " over <her> chest before fastening the clasp at <her> back.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You release the bra's clasp and remove it from your chest.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " unclasps <her> bra and removes it from <her> chest.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " unclasps your bra and pulls it off your chest.";
				else
					return UtilText.genderParsing(clothingOwner, "You unclasp " + clothingOwner.getName("the") + "'s bra and pull it off <her> chest.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You slide your bra's straps off your shoulders before tugging it down to reveal your chest.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " slides <her> bra's straps off <her> shoulders before tugging it down to reveal <her> chest.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " slides your bra's straps off your shoulders before tugging it down to reveal your chest.";
				else
					return UtilText.genderParsing(clothingOwner, "You slide the straps of " + clothingOwner.getName("the") + "'s bra off <her> shoulders before tugging it down to reveal <her> chest.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your bra back up to cover your chest.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> bra back up to cover <her> chest.");
		}
	},
	CHEST_OPEN_CUP_BRA("an",
			false,
			"open cup bra",
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
									null))
					),

			null,

			Colour.lingerieColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You place the bra over your chest before fastening the straps at your back.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " places " + clothing.getName(true) + " over <her> chest before fastening the clasp at <her> back.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You release the bra's clasp and remove it from your chest.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " unclasps <her> bra and removes it from <her> chest.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " unclasps your bra and pulls it off your chest.";
				else
					return UtilText.genderParsing(clothingOwner, "You unclasp " + clothingOwner.getName("the") + "'s bra and pull it off <her> chest.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	CHEST_SPORTS_BRA("a", false, "sports bra", "An elastic sports bra that's designed to firmly support the wearer's breasts during exercise.", 1, Femininity.FEMININE, InventorySlot.CHEST, Rarity.COMMON, null, "chest_sports_bra", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									null,
									null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_UP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST))
									))
					),

			null,

			Colour.lingerieColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull the sports bra down over your head before securing it around your chest.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls " + clothing.getName(true) + " down over <her> head before securing it around <her> chest.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull the sports bra off over your head.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> sports bra off over <her> head.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your sports bra off over your head.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s sports bra off over <her> head.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull up your sports bra.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls up <her> sports bra.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls up your sports bra.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull up " + clothingOwner.getName("the") + "'s sports bra.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your sports bra back down.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> sports bra back down.");
		}
	},
	CHEST_CROPTOP_BRA("a", false, "croptop bra", "A loose fitting bra which resembles a short croptop.", 1, Femininity.FEMININE, InventorySlot.CHEST, Rarity.COMMON, null, "chest_croptop_bra", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									null,
									null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_UP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST))
									))
					),

			null,

			Colour.lingerieColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull the croptop bra down over your head before securing it around your chest.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls " + clothing.getName(true) + " down over <her> head before securing it around <her> chest.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull the croptop bra off over your head.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> croptop bra off over <her> head.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your croptop bra off over your head.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s croptop bra off over <her> head.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull up your croptop bra.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls up <her> croptop bra.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls up your croptop bra.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull up " + clothingOwner.getName("the") + "'s croptop bra.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your croptop bra back down.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> croptop bra back down.");
		}
	},
	CHEST_FULLCUP_BRA("a", false, "fullcup bra", "A fullcup bra, it conceals a large portion of the wearer's breasts.", 1, Femininity.FEMININE, InventorySlot.CHEST, Rarity.COMMON, null, "chest_fullcup_bra", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									null,
									null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_DOWN,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST))
									))
					),

			null,

			Colour.lingerieColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You place the bra over your chest before fastening the straps at your back.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " places " + clothing.getName(true) + " over <her> chest before fastening the clasp at <her> back.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You release the bra's clasp and remove it from your chest.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " unclasps <her> bra and removes it from <her> chest.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " unclasps your bra and pulls it off your chest.";
				else
					return UtilText.genderParsing(clothingOwner, "You unclasp " + clothingOwner.getName("the") + "'s bra and pull it off <her> chest.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You slide your bra's straps off your shoulders before tugging it down to reveal your chest.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " slides <her> bra's straps off <her> shoulders before tugging it down to reveal <her> chest.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " slides your bra's straps off your shoulders before tugging it down to reveal your chest.";
				else
					return UtilText.genderParsing(clothingOwner, "You slide the straps of " + clothingOwner.getName("the") + "'s bra off <her> shoulders before tugging it down to reveal <her> chest.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your bra back up to cover your chest.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> bra back up to cover <her> chest.");
		}
	},
	CHEST_BIKINI("a", false, "bikini top", "A triangle-shaped bikini top. It uses loose strings tied at the back to keep it in place.", 1, Femininity.FEMININE, InventorySlot.CHEST, Rarity.COMMON, null, "chest_bikini", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									null,
									null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_UP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.CHEST))
									))
					),
			
			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You place the bikini top over your chest before tying the strings together at your back.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " places " + clothing.getName(true) + " over <her> chest before tying the strings together at <her> back.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You untie the biki top's strings and remove it from your chest.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " unties <her> biki top's strings and removes it from <her> chest.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " unties your biki top's strings and removes it from your chest.";
				else
					return UtilText.genderParsing(clothingOwner, "You untie " + clothingOwner.getName("the") + "'s biki top's strings and pull it off <her> chest.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You slide your bikini top's straps off your shoulders before tugging it down to reveal your chest.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " slides <her> bikini top's straps off <her> shoulders before tugging it down to reveal <her> chest.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " slides your bikini top's straps off your shoulders before tugging it down to reveal your chest.";
				else
					return UtilText.genderParsing(clothingOwner,
							"You slide the straps of " + clothingOwner.getName("the") + "'s bikini top off <her> shoulders before tugging it down to reveal <her> chest.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your bikini top back up to cover your chest.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> bikini top back up to cover <her> chest.");
		}
	},
	CHEST_TAPE_CROSSES("a pair of", false, "tape crosses", "A pair of crosses, made out of shiny electrician's tape. They are only just large enough to fully cover a pair of nipples.", 1, null, InventorySlot.CHEST, Rarity.COMMON, null,
			"chest_tapecrosses", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.CHEST)), Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.NIPPLES)), null))),

			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You stick the tape crosses over your nipples.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " sticks " + clothing.getName(true) + " over <her> nipples.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You wince as you peel the tape crosses off your nipples.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " winces as <she> peels the tape crosses off <her> nipples.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " peels the tape crosses off your nipples.";
				else
					return UtilText.genderParsing(clothingOwner, "You peel the tape crosses off " + clothingOwner.getName("the") + "'s nipples.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public boolean isBlocksFromSight() {
			return false;
		}
	},
	
	CHEST_SARASHI("a", false, "chest sarashi", "A long strip of thick cotton, wrapped around the chest as an added layer of protection, or to flatten the wearer's breasts.", 1, null, InventorySlot.CHEST, Rarity.COMMON, null,
			"chest_sarashi", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.CHEST)), Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.NIPPLES)), null))),

			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You wrap the sarashi around your chest.";
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] wraps " + clothing.getName(true) + " around [npc.her] chest.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You unwrap the sarashi and remove it from around your chest.";
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] unwraps the sarashi from around [npc.her] chest.");
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingOwner, "[npc.Name] unwraps the sarashi from around your chest.");
				} else {
					return UtilText.parse(clothingOwner, "You unwrap the sarashi from around [npc.name]'s chest.");
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public boolean isBlocksFromSight() {
			return false;
		}
	},

	// STOMACH

	STOMACH_LOWBACK_BODY("a", false, "lowback bodysuit", "A bodysuit with a scooped low back and removable straps.", 1, Femininity.FEMININE, InventorySlot.STOMACH, Rarity.COMMON, null, "stomach_lowback_body", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.WAIST),
									new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.WAIST)))),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.SHIFTS_ASIDE, null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.ANUS),
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)))),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_DOWN,
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.CHEST))))),

			Util.newArrayListOfValues(new ListValue<InventorySlot>(InventorySlot.GROIN), new ListValue<InventorySlot>(InventorySlot.CHEST)),

			Colour.lingerieColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the bodysuit and pull it up over your torso.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " steps into " + clothing.getName(true) + " and pulls it up to cover <her> torso.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You slide the bodysuit's straps down your arms and tug it down off your legs.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " slides the bodysuit's straps down <her> arms and tugs it down off <her> legs.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " tugs your bodysuit down, sliding it off your legs.";
				else
					return UtilText.genderParsing(clothingOwner, "You tug down " + clothingOwner.getName("the") + "'s bodysuit, sliding it off <her> legs.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (dt == DisplacementType.PULLS_DOWN) {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull down the top half of your bodysuit.";
				else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
					return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down the top half of <her> bodysuit.");
				else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls down the top half of your bodysuit.";
					else
						return UtilText.genderParsing(clothingOwner, "You pull down the top half of " + clothingOwner.getName("the") + "'s bodysuit.");
				}
			} else {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull the lower part of your bodysuit to one side.";
				else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
					return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls the lower part of <her> bodysuit to one side.");
				else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls the lower part of your bodysuit to one side.";
					else
						return UtilText.genderParsing(clothingOwner, "You pull the lower part of " + clothingOwner.getName("the") + "'s bodysuit to one side.");
				}
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (dt == DisplacementType.PULLS_DOWN) {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull your bodysuit back up into its correct position.";
				else
					return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> bodysuit up into its correct position.");
			} else {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull the lower part of your bodysuit back into its correct position.";
				else
					return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls the lower part of <her> bodysuit back into its correct position.");
			}
		}
	},
	STOMACH_UNDERBUST_CORSET("an", false, "underbust corset", "A corset that keeps the wearer's stomach nicely shaped, while leaving their breasts totally exposed. It has a series of laces at the back that are used to tighten it.", 1,
			Femininity.FEMININE, InventorySlot.STOMACH, Rarity.COMMON, null, "stomach_underbust_corset", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(
								DisplacementType.REMOVE_OR_EQUIP,
								Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
								null,
								Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.WAIST))))),

			null,

			Colour.lingerieColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You place the corset around your stomach before tying the laces up at your back.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " places " + clothing.getName(true) + " around <her> stomach before tying the laces up at <her> back.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You untie the corset's laces and remove it from your stomach.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " unties <her> corset's laces and removes it from <her> stomach.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " unties your corset's laces and removes it from your stomach.";
				else
					return UtilText.genderParsing(clothingOwner, "You untie " + clothingOwner.getName("the") + "'s corset's laces and remove it from <her> stomach.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	STOMACH_OVERBUST_CORSET("an", false, "overbust corset", "A corset that keeps the wearer's stomach nicely shaped, while also covering their breasts. It has a series of laces at the back that are used to tighten it.", 1, Femininity.FEMININE,
			InventorySlot.STOMACH, Rarity.COMMON, null, "stomach_overbust_corset", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.WAIST),
											new ListValue<ClothingAccess>(ClothingAccess.CHEST)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.WAIST),
											new ListValue<ClothingAccess>(ClothingAccess.CHEST))))),

			Util.newArrayListOfValues(new ListValue<InventorySlot>(InventorySlot.CHEST)),

			Colour.lingerieColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You place the corset around your stomach before tying the laces up at your back.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " places " + clothing.getName(true) + " around <her> stomach before tying the laces up at <her> back.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You untie the corset's laces and remove it from your stomach.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " unties <her> corset's laces and removes it from <her> stomach.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " unties your corset's laces and removes it from your stomach.";
				else
					return UtilText.genderParsing(clothingOwner, "You untie " + clothingOwner.getName("the") + "'s corset's laces and remove it from <her> stomach.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	
	STOMACH_SARASHI("a", false, "stomach sarashi", "A long strip of thick cotton, wrapped around the stomach as an added layer of protection, or to gain a slim figure.", 2,
			null, InventorySlot.STOMACH, Rarity.COMMON, null, "stomach_sarashi", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(
								DisplacementType.REMOVE_OR_EQUIP,
								Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.WAIST)),
								null,
								Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.WAIST))))),

			null,

			Colour.lingerieColours) {
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
				return "You unwrap teh sarashi remove it from your stomach.";
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] unwraps the sarashi from around [npc.her] stomach.");
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingOwner, "[npc.Name] unwraps your sarashi and removes it from around your stomach.");
				} else {
					return UtilText.parse(clothingOwner, "You unwrap [npc.name]'s sarashi and remove it from around [npc.her] stomach.");
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},

	// HAND
	HAND_GLOVES("a pair of", true, "gloves", "A pair of normal-looking gloves, they're designed to keep the wearer's hands warm.", 1, null, InventorySlot.HAND, Rarity.COMMON, null, "hand_gloves", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FINGERS)), null,
					Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FINGERS))))),

			Util.newArrayListOfValues(new ListValue<InventorySlot>(InventorySlot.FINGER)),

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the gloves and give your fingers an experimental wiggle.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls on " + clothing.getName(true) + " and gives <her> fingers an experimental wiggle.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your gloves.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls off <her> gloves.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your gloves off.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s gloves off.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	HAND_FINGERLESS_GLOVES("a pair of", true, "fingerless gloves", "A pair of fingerless gloves. They are the sort worn by cyclists and runners, and are made out of a thin, breathable material.", 1, null, InventorySlot.HAND, Rarity.COMMON, null,
			"hand_fingerless_gloves", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FINGERS)),
							null,
							null))),

			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the gloves and give your fingers an experimental wiggle.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls on " + clothing.getName(true) + " and gives <her> fingers an experimental wiggle.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your gloves.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls off <her> gloves.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your gloves off.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s gloves off.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	HAND_ELBOWLENGTH_GLOVES("a pair of", true, "elbow-length gloves", "A pair of elbow-length gloves, they're made from a thin, soft fabric.", 1, Femininity.FEMININE, InventorySlot.HAND, Rarity.COMMON, null, "hand_elbowlength_gloves", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FINGERS),
									new ListValue<ClothingAccess>(ClothingAccess.WRISTS)),
							null,
							null))),

			null,

			Util.newArrayListOfValues(new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_WHITE), new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_BLACK))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the gloves and give your fingers an experimental wiggle.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls on " + clothing.getName(true) + " and gives <her> fingers an experimental wiggle.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your gloves.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls off <her> gloves.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your gloves off.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s gloves off.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	HAND_FISHNET_GLOVES("a pair of", true, "fishnet gloves", "A pair of fishnet gloves.", 1, Femininity.FEMININE, InventorySlot.HAND, Rarity.COMMON, null, "hand_fishnet_gloves", null,//TODO

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.FINGERS),
											new ListValue<ClothingAccess>(ClothingAccess.WRISTS)),
									null,
									null))),

			null,

			Colour.allClothingColours) {
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
					return UtilText.parse(clothingOwner, "[npc.Name] pulls off your fishnet gloves.");
				} else {
					return UtilText.parse(clothingOwner, "You pull off [npc.name]'s fishnet gloves.");
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	HAND_RAINBOW_FINGERLESS_GLOVES("a pair of", true, "rainbow gloves", "A pair of brightly-coloured rainbow fingerless gloves.", 1, Femininity.FEMININE, InventorySlot.HAND, Rarity.EPIC, ClothingSet.RAINBOW, "hand_rainbow_fingerless_gloves", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FINGERS),
									new ListValue<ClothingAccess>(ClothingAccess.WRISTS)),
							null,
							null))),

			null,

			Util.newArrayListOfValues(new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_MULTICOLOURED))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the gloves and give your fingers an experimental wiggle.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls on " + clothing.getName(true) + " and gives <her> fingers an experimental wiggle.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your gloves.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls off <her> gloves.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your gloves off.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s gloves off.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},

	// WRIST

	WRIST_WOMENS_WATCH("a", false, "feminine watch", "A feminine-looking watch. Like others of its kind, its primary purpose is to keep track of the time.", 1, Femininity.FEMININE, InventorySlot.WRIST, Rarity.COMMON, null, "wrist_womens_watch", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.WRISTS)), null,
					Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.WRISTS))))),

			null,

			Util.newArrayListOfValues(new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_PINK_LIGHT), new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_BLUE_LIGHT),
					new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_WHITE), new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_BLACK))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You place the watch on your wrist and fasten the strap.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " places " + clothing.getName(true) + " on <her> wrist and fastens the strap.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You unfasten your watch's strap and take it off.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " unfastens <her> watch's strap and takes it off.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " unfastens your watch's strap and takes it off.";
				else
					return UtilText.genderParsing(clothingOwner, "You unfasten the strap on " + clothingOwner.getName("the") + "'s watch and take it off.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	WRIST_MENS_WATCH("a", false, "masculine watch", "A masculine-looking watch. Like others of its kind, its primary purpose is to keep track of the time.", 1, Femininity.MASCULINE, InventorySlot.WRIST, Rarity.COMMON, null, "wrist_mens_watch", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.WRISTS)), null,
					Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.WRISTS))))),

			null,

			Util.newArrayListOfValues(new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_BLACK_STEEL), new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_SILVER),
					new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_GOLD))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You place the watch on your wrist and fasten the strap.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " places " + clothing.getName(true) + " on <her> wrist and fastens the strap.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You unfasten your watch's strap and take it off.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " unfastens <her> watch's strap and takes it off.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " unfastens your watch's strap and takes it off.";
				else
					return UtilText.genderParsing(clothingOwner, "You unfasten the strap on " + clothingOwner.getName("the") + "'s watch and take it off.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	WRIST_BANGLE("a", false, "bangle", "A simple metal bangle that fits comfortably around the wearer's wrist.", 1, Femininity.FEMININE, InventorySlot.WRIST, Rarity.COMMON, null, "wrist_bangle", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.WRISTS)), null,
					Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.WRISTS))))),

			null,

			Util.newArrayListOfValues(new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_COPPER), new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_SILVER), new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_GOLD))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You slide the bangle onto your wrist.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " slides " + clothing.getName(true) + " onto <her> wrist.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You slide your bangle off your wrist.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " slides <her> bangle off <her> wrist.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " slides your bangle off your wrist.";
				else
					return UtilText.genderParsing(clothingOwner, "You slide " + clothingOwner.getName("the") + "'s bangle off <her> wrist.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},

	// FINGER

	FINGER_RING("a", false, "ring", "A plain band of metal that fits onto a person's finger.", 0, null, InventorySlot.FINGER, Rarity.COMMON, null, "finger_ring", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FINGERS)),
							null,
							null))),

			null,

			Util.newArrayListOfValues(new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_COPPER), new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_SILVER), new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_GOLD))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull the ring onto your finger.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls " + clothing.getName(true) + " onto <her> finger.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off the ring.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls off <her> ring.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls off your ring.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull off " + clothingOwner.getName("the") + "'s ring.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},

	// LEG

	LEG_SKIRT("a", false, "skirt", "A plain skirt, there's nothing especially interesting about it.", 1, Femininity.FEMININE, InventorySlot.LEG, Rarity.COMMON, null, "leg_skirt", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.PULLS_UP, null,
					Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.ANUS), new ListValue<CoverableArea>(CoverableArea.PENIS), new ListValue<CoverableArea>(CoverableArea.VAGINA)),
					Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN))))),

			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the skirt before pulling it up to your waist.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " steps into " + clothing.getName(true) + " before pulling it up to <her> waist.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your skirt and kick it off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down <her> skirt, kicking it off <her> feet.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your skirt down and slides it off your feet.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s skirt down and slide it off <her> feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull up your skirt.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls up <her> skirt.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your skirt up.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s skirt up.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your skirt back down.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> skirt back down.");
		}
	},
	LEG_PENCIL_SKIRT("a", false, "pencil skirt", "A pencil skirt that clings tightly to the wearer's hips and legs.", 1, Femininity.FEMININE, InventorySlot.LEG, Rarity.COMMON, null, "leg_pencil_skirt", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.PULLS_UP, null,
					Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.ANUS), new ListValue<CoverableArea>(CoverableArea.PENIS), new ListValue<CoverableArea>(CoverableArea.VAGINA)),
					Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN))))),

			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the skirt before pulling it up to your waist.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " steps into " + clothing.getName(true) + " before pulling it up to <her> waist.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your skirt and kick it off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down <her> skirt, kicking it off <her> feet.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your skirt down and slides it off your feet.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s skirt down and slide it off <her> feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull up your skirt.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls up <her> skirt.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your skirt up.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s skirt up.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your skirt back down.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> skirt back down.");
		}
	},
	LEG_MINI_SKIRT("a", false, "miniskirt", "A very short skirt that barely reaches down to mid-thigh.", 1, Femininity.FEMININE, InventorySlot.LEG, Rarity.COMMON, null, "leg_mini_skirt", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.PULLS_UP, null,
					Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.ANUS), new ListValue<CoverableArea>(CoverableArea.PENIS), new ListValue<CoverableArea>(CoverableArea.VAGINA)),
					Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN))))),

			null,

			Colour.allClothingColours) {
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You step into the miniskirt before pulling it up to your waist.";
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] steps into the miniskirt before pulling it up to [npc.her] waist.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull down your miniskirt and kick it off your [pc.feet].";
				
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] miniskirt before kicking it off [npc.her] [npc.feet].");
				
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingOwner, "[npc.Name] pulls your miniskirt down and slides it off your [pc.feet].");
				} else {
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s miniskirt down and slide it off [npc.her] [npc.feet].");
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull up your miniskirt.";
				
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] pulls up [npc.her] miniskirt.");
				
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingOwner, "[npc.Name] pulls up your miniskirt.");
				} else {
					return UtilText.parse(clothingOwner, "You pull up [npc.name]'s miniskirt.");
				}
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your miniskirt back down.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> miniskirt back down.");
		}
	},
	
	LEG_MICRO_SKIRT_PLEATED("a", false, "pleated microskirt", "An extremely tiny pleated skirt. It's so short that it does nothing to conceal its wearer's private parts",
			1, Femininity.FEMININE, InventorySlot.LEG, Rarity.COMMON, null, "leg_micro_skirt_pleated", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null))),

			null,

			Colour.allClothingColours) {
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
					return UtilText.parse(clothingOwner, "[npc.Name] pulls your microskirt down and slides it off your [pc.feet].");
				} else {
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s microskirt down and slide it off [npc.her] [npc.feet].");
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	
	LEG_MICRO_SKIRT_BELTED("a", false, "belted microskirt", "An extremely tiny skirt, with an accompanying belt. It's so short that it does nothing to conceal its wearer's private parts.",
			1, Femininity.FEMININE, InventorySlot.LEG, Rarity.COMMON, null, "leg_micro_skirt_belted", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, null, null, null))),

			null,

			Colour.allClothingColours) {
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
					return UtilText.parse(clothingOwner, "[npc.Name] pulls your microskirt down and slides it off your [pc.feet].");
				} else {
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s microskirt down and slide it off [npc.her] [npc.feet].");
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	
	LEG_SHORTS("a pair of", true, "shorts", "A pair of ordinary unisex shorts.", 1, null, InventorySlot.LEG, Rarity.COMMON, null, "leg_shorts", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)), null,
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)))),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.PULLS_DOWN, null,
							Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.ANUS), new ListValue<CoverableArea>(CoverableArea.PENIS), new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN))))),

			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You step into the shorts before pulling them up to your waist.";
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] steps into the shorts before pulling them up to [npc.her] waist.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull down your shorts and kick them off your feet.";
				
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] shorts, before kicking them off [npc.her] feet.");
				
			}else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingOwner, "[npc.Name] pulls your shorts down, before sliding them off your feet.");
				} else {
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s shorts down and slide them off [npc.her] feet.");
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull down your shorts.";
				
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] shorts.");
				
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingOwner, "[npc.Name] pulls your shorts down.");
				} else {
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s shorts down.");
				}
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull your shorts back up.";
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] pulls [npc.her] shorts back up.");
			}
		}
	},
	LEG_BIKE_SHORTS("a pair of", true, "bike shorts", "A pair of very tight lycra bike shorts (sometimes referred to as 'spats'). The elastic material hugs to the wearer's body, showing off their curves.",
			1, null, InventorySlot.LEG, Rarity.COMMON, null, "leg_bikeShorts", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)), null,
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)))),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.PULLS_DOWN, null,
							Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.ANUS), new ListValue<CoverableArea>(CoverableArea.PENIS), new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN))))),

			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You step into the bike shorts before pulling them up to your waist.";
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] steps into the bike shorts before pulling them up to [npc.her] waist.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull down your bike shorts and kick them off your feet.";
				
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] bike shorts, before kicking them off [npc.her] feet.");
				
			}else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingOwner, "[npc.Name] pulls your bike shorts down, before sliding them off your feet.");
				} else {
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s bike shorts down and slide them off [npc.her] feet.");
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull down your bike shorts.";
				
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] pulls down [npc.her] bike shorts.");
				
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingOwner, "[npc.Name] pulls your bike shorts down.");
				} else {
					return UtilText.parse(clothingOwner, "You pull [npc.name]'s bike shorts down.");
				}
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "You pull your bike shorts back up.";
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] pulls [npc.her] bike shorts back up.");
			}
		}
	},
	LEG_HOTPANTS("a pair of", true, "hotpants", "A pair of very small, very tight shorts, commonly referred to as hotpants.", 1, Femininity.FEMININE, InventorySlot.LEG, Rarity.COMMON, null, "leg_hotpants", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)), null,
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)))),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.PULLS_DOWN, null,
							Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.ANUS), new ListValue<CoverableArea>(CoverableArea.PENIS), new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN))))),

			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the hotpants before pulling them up to your waist.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " steps into " + clothing.getName(true) + " before pulling them up to <her> waist.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your hotpants and kick them off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down <her> hotpants, kicking them off <her> feet.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your hotpants down and slides them off your feet.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s hotpants down and slide them off <her> feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your hotpants.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down <her> hotpants.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your hotpants down.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s hotpants down.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your hotpants back up.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> hotpants back up.");
		}
	},
	LEG_TIGHT_TROUSERS("a pair of", true, "tight jeans", "A pair of tight jeans that hug the wearer's legs.", 1, null, InventorySlot.LEG, Rarity.COMMON, null, "leg_tight_jeans", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET), new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)),
							null, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)))),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.PULLS_DOWN, null,
							Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.ANUS), new ListValue<CoverableArea>(CoverableArea.PENIS), new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN))))),

			null,

			Util.newArrayListOfValues(new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_BLUE_LIGHT), new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_PINK_LIGHT),
					new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_WHITE), new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_BLACK))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the jeans before pulling them up to your waist.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " steps into " + clothing.getName(true) + " before pulling them up to <her> waist.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your jeans and kick them off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down <her> jeans, kicking them off <her> feet.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your jeans down and slides them off your feet.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s jeans down and slide them off <her> feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your jeans.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down <her> jeans.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your jeans down.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s jeans down.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your jeans back up.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> jeans back up.");
		}
	},
	LEG_TROUSERS("a pair of", true, "trousers", "A pair of men's trousers that hang loosely around the legs.", 1, Femininity.MASCULINE, InventorySlot.LEG, Rarity.COMMON, null, "leg_trousers", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET), new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)),
							null, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)))),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.PULLS_DOWN, null,
							Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.ANUS), new ListValue<CoverableArea>(CoverableArea.PENIS), new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN))))),

			null,

			Util.newArrayListOfValues(new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_WHITE), new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_BLACK))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the trousers before pulling them up to your waist.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " steps into " + clothing.getName(true) + " before pulling them up to <her> waist.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your trousers and kick them off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down <her> trousers, kicking them off <her> feet.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your trousers down and slides them off your feet.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s trousers down and slide them off <her> feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your trousers.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down <her> trousers.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your trousers down.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s trousers down.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your trousers back up.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> trousers back up.");
		}
	},
	LEG_YOGA_PANTS("a pair of", true, "yoga pants", "A pair of very tight yoga pants, made from a stretchable and thin fabric.", 1, Femininity.FEMININE, InventorySlot.LEG, Rarity.COMMON, null, "leg_yoga_pants", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.FEET),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)))),
					
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.PULLS_DOWN,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.ANUS),
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN))))),

			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the yoga pants before pulling them up to your waist.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " steps into " + clothing.getName(true) + " before pulling them up to <her> waist.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your yoga pants and kick them off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down <her> yoga pants, kicking them off <her> feet.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your yoga pants down and slides them off your feet.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s yoga pants down and slide them off <her> feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your yoga pants.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down <her> yoga pants.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your yoga pants down.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s yoga pants down.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your yoga pants back up.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> yoga pants back up.");
		}
	},
	LEG_ASSLESS_CHAPS("a pair of", true, "assless chaps", "These leather chaps are missing a large piece at the rear, which leaves the wearer's ass completely exposed.",
			1, null, InventorySlot.LEG, Rarity.COMMON, null, "leg_assless_chaps", null,

			Util.newArrayListOfValues(
					
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.FEET),
											new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)),
									null,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)))),
					
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.PULLS_DOWN,
							null,
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							null))),

			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the assless chaps before pulling them up to your waist.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " steps into " + clothing.getName(true) + " before pulling them up to <her> waist.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your assless chaps and kick them off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down <her> assless chaps, kicking them off <her> feet.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your assless chaps down and slides them off your feet.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s assless chaps down and slide them off <her> feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your assless chaps.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down <her> assless chaps.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your assless chaps down.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s assless chaps down.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your assless chaps back up.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> assless chaps back up.");
		}
	},
	
	LEG_CROTCHLESS_CHAPS("a pair of", true, "crotchless chaps", "These leather chaps are missing a large piece at the front and back, which leaves the wearer's ass and groin completely exposed.",
			1, null, InventorySlot.LEG, Rarity.COMMON, null, "leg_crotchless_chaps", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)),
							null,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN))))),

			null, // List<InventorySlot> incompatibleSlots

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the crotchless chaps before pulling them up to your waist.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " steps into " + clothing.getName(true) + " before pulling them up to <her> waist.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your crotchless chaps and kick them off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down <her> crotchless chaps, kicking them off <her> feet.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your crotchless chaps down and slides them off your feet.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s crotchless chaps down and slide them off <her> feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},

	// GROIN

	GROIN_PANTIES("a pair of", true, "panties", "Some underwear.", 1, Femininity.FEMININE, InventorySlot.GROIN, Rarity.COMMON, null, "groin_panties", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							null,
							null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.SHIFTS_ASIDE,
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.ANUS),
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							null))),

			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the panties before pulling them up to cover your private parts.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " steps into " + clothing.getName(true) + " before pulling them up to cover <her> private parts.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your panties and kick them off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down <her> panties, kicking them off <her> feet.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your panties down and slides them off your feet.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s panties down and slide them off <her> feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You shift your panties to one side.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " shifts <her> panties to one side.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " shifts your panties to one side.";
				else
					return UtilText.genderParsing(clothingOwner, "You shift " + clothingOwner.getName("the") + "'s panties to one side.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You move your panties back into their proper place.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " moves <her> panties back into their proper place.");
		}
	},
	GROIN_BACKLESS_PANTIES("a pair of",
			true,
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
							null)),
					new ListValue<BlockedParts>(
							new BlockedParts(DisplacementType.SHIFTS_ASIDE,
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							null))),

			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the panties before pulling them up to cover your private parts.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " steps into " + clothing.getName(true) + " before pulling them up to cover <her> private parts.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your panties and kick them off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down <her> panties, kicking them off <her> feet.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your panties down and slides them off your feet.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s panties down and slide them off <her> feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You shift your panties to one side.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " shifts <her> panties to one side.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " shifts your panties to one side.";
				else
					return UtilText.genderParsing(clothingOwner, "You shift " + clothingOwner.getName("the") + "'s panties to one side.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You move your panties back into their proper place.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " moves <her> panties back into their proper place.");
		}
	},
	GROIN_CROTCHLESS_PANTIES("a pair of",
			true,
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
							null, null))),

			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the panties before pulling them up to cover your private parts.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " steps into " + clothing.getName(true) + " before pulling them up to cover <her> private parts.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your panties and kick them off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down <her> panties, kicking them off <her> feet.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your panties down and slides them off your feet.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s panties down and slide them off <her> feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You shift your panties to one side.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " shifts <her> panties to one side.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " shifts your panties to one side.";
				else
					return UtilText.genderParsing(clothingOwner, "You shift " + clothingOwner.getName("the") + "'s panties to one side.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You move your panties back into their proper place.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " moves <her> panties back into their proper place.");
		}
	},
	GROIN_VSTRING("a pair of", true, "v-string panties", "A pair of v-string panties made from thin cotton.", 1, Femininity.FEMININE, InventorySlot.GROIN, Rarity.COMMON, null, "groin_vstring", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							null, null)),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.SHIFTS_ASIDE, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.ANUS), new ListValue<CoverableArea>(CoverableArea.PENIS), new ListValue<CoverableArea>(CoverableArea.VAGINA)), null))),

			null,

			Colour.lingerieColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the v-string panties before pulling them up to cover your private parts.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " steps into " + clothing.getName(true) + " before pulling them up to cover <her> private parts.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your v-string panties and kick them off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down <her> v-string panties, kicking them off <her> feet.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your v-string panties down and slides them off your feet.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s v-string panties down and slide them off <her> feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You shift your v-string panties to one side.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " shifts <her> v-string panties to one side.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " shifts your v-string panties to one side.";
				else
					return UtilText.genderParsing(clothingOwner, "You shift " + clothingOwner.getName("the") + "'s v-string panties to one side.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You move your v-string panties back into their proper place.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " moves <her> v-string panties back into their proper place.");
		}
	},
	GROIN_THONG("a", false, "thong", "A thong with a decorative cross stitched into the front.", 1, Femininity.FEMININE, InventorySlot.GROIN, Rarity.COMMON, null, "groin_thong", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							null,
							null)),
					new ListValue<BlockedParts>(new BlockedParts(
							DisplacementType.SHIFTS_ASIDE,
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							Util.newArrayListOfValues(
									new ListValue<CoverableArea>(CoverableArea.ANUS),
									new ListValue<CoverableArea>(CoverableArea.PENIS),
									new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							null))),

			null,

			Colour.lingerieColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the thong before pulling it up to cover your private parts.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " steps into " + clothing.getName(true) + " before pulling it up to cover <her> private parts.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your thong and kick it off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down <her> thong and kicks it off <her> feet.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your thong down and slides it off your feet.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s thong down and slide it off <her> feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You shift your thong to one side.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " shifts <her> thong to one side.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " shifts your thong to one side.";
				else
					return UtilText.genderParsing(clothingOwner, "You shift " + clothingOwner.getName("the") + "'s thong to one side.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You move your thong back into its proper place.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " moves <her> thong back into its proper place.");
		}
	},
	GROIN_BIKINI("a pair of", true, "bikini bottoms", "The part of a bikini that covers the wearer's private parts.", 1, Femininity.FEMININE, InventorySlot.GROIN, Rarity.COMMON, null, "groin_bikini", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							null, null)),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.SHIFTS_ASIDE, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.ANUS), new ListValue<CoverableArea>(CoverableArea.PENIS), new ListValue<CoverableArea>(CoverableArea.VAGINA)), null))),

			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the bikini bottoms before pulling them up to cover your private parts.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " steps into " + clothing.getName(true) + " before pulling them up to cover <her> private parts.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your bikini bottoms and kick them off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down <her> bikini bottoms, kicking them off <her> feet.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your bikini bottoms down and slides them off your feet.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s bikini bottoms down and slide them off <her> feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You shift your bikini bottoms to one side.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " shifts <her> bikini bottoms to one side.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " shifts your bikini bottoms to one side.";
				else
					return UtilText.genderParsing(clothingOwner, "You shift " + clothingOwner.getName("the") + "'s bikini bottoms to one side.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You move your bikini bottoms back into their proper place.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " moves <her> bikini bottoms back into their proper place.");
		}
	},
	GROIN_BOYSHORTS("a pair of", true, "boyshorts", "Despite their name, these underwear are intended to be worn by women.", 1, Femininity.FEMININE, InventorySlot.GROIN, Rarity.COMMON, null, "groin_boyshorts", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							null, null)),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.SHIFTS_ASIDE, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.ANUS), new ListValue<CoverableArea>(CoverableArea.PENIS), new ListValue<CoverableArea>(CoverableArea.VAGINA)), null))),

			null,

			Colour.lingerieColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the boyshorts before pulling them up to cover your private parts.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " steps into " + clothing.getName(true) + " before pulling them up to cover <her> private parts.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your boyshorts and kick them off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down <her> boyshorts, kicking them off <her> feet.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your boyshorts down and slides them off your feet.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s boyshorts down and slide them off <her> feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You shift your boyshorts to one side.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " shifts <her> boyshorts to one side.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " shifts your boyshorts to one side.";
				else
					return UtilText.genderParsing(clothingOwner, "You shift " + clothingOwner.getName("the") + "'s boyshorts to one side.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You move your boyshorts back into their proper place.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " moves <her> boyshorts back into their proper place.");
		}
	},
	GROIN_BRIEFS("a pair of", true, "briefs", "A pair of men's briefs.", 1, Femininity.MASCULINE, InventorySlot.GROIN, Rarity.COMMON, null, "groin_briefs", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							null, null)),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.SHIFTS_ASIDE, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.ANUS), new ListValue<CoverableArea>(CoverableArea.PENIS), new ListValue<CoverableArea>(CoverableArea.VAGINA)), null))),

			null,

			Colour.masculineColours) {
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
					return UtilText.parse(clothingOwner, "[npc.Name] pulls down your before before sliding them off your [pc.feet].");
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
					return UtilText.parse(clothingOwner, "[npc.Name] shifts your briefs to one side.");
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
	},
	GROIN_CROTCHLESS_BRIEFS("a pair of", true, "crotchless briefs", "A pair of men's briefs, with a section cut out at the front that leaves the wearer's genitalia completely exposed.",
			1, Femininity.MASCULINE, InventorySlot.GROIN, Rarity.COMMON, null, "groin_crotchless_briefs", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							null, null)),
					new ListValue<BlockedParts>(
							new BlockedParts(
									DisplacementType.SHIFTS_ASIDE,
									Util.newArrayListOfValues(
											new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
									Util.newArrayListOfValues(
											new ListValue<CoverableArea>(CoverableArea.ANUS)),
									null))),

			null,

			Colour.masculineColours) {
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
					return UtilText.parse(clothingOwner, "[npc.Name] pulls down your before before sliding them off your [pc.feet].");
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
					return UtilText.parse(clothingOwner, "[npc.Name] shifts your briefs to one side.");
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
	},
	GROIN_BOXERS("a pair of", true, "boxer shorts", "A pair of loose-fitting men's underwear.", 1, Femininity.MASCULINE, InventorySlot.GROIN, Rarity.COMMON, null, "groin_boxers", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL),
									new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN),
									new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							null, null)),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.SHIFTS_ASIDE, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.ANUS), new ListValue<CoverableArea>(CoverableArea.PENIS), new ListValue<CoverableArea>(CoverableArea.VAGINA)), null))),

			null,

			Colour.masculineColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the boxers before pulling them up to cover your private parts.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " steps into " + clothing.getName(true) + " before pulling them up to cover <her> private parts.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your boxers and kick them off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down <her> boxers, kicking them off <her> feet.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your boxers down and slides them off your feet.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s boxers down and slide them off <her> feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You shift your boxers to one side.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " shifts <her> boxers to one side.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " shifts your boxers to one side.";
				else
					return UtilText.genderParsing(clothingOwner, "You shift " + clothingOwner.getName("the") + "'s boxers to one side.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You move your boxers back into their proper place.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " moves <her> boxers back into their proper place.");
		}
	},

	// SOCK

	SOCK_RAINBOW_STOCKINGS("a pair of", true, "rainbow stockings", "A pair of brightly coloured rainbow stockings that reach up to mid-thigh.", 1, Femininity.FEMININE, InventorySlot.SOCK, Rarity.EPIC, ClothingSet.RAINBOW, "sock_rainbow_stockings",
			null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET), new ListValue<ClothingAccess>(ClothingAccess.CALVES)), null, null))),

			null,

			Util.newArrayListOfValues(new ListValue<Colour>(Colour.CLOTHING_MULTICOLOURED))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You push your feet into the rainbow stockings and pull them up to your thighs.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " pushes <her> feet into " + clothing.getName(true) + " and pulls them up to <her> thighs.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your rainbow stockings.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls off <her> rainbow stockings.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls off your rainbow stockings.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull off " + clothingOwner.getName("the") + "'s rainbow stockings.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	SOCK_SOCKS("a pair of", true, "socks", "A pair of standard socks, made from cotton and with an elastic band at the top.", 1, null, InventorySlot.SOCK, Rarity.COMMON, null, "sock_socks", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET)), null, null))),

			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the socks.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls on " + clothing.getName(true) + ".");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your socks.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls off <her> socks.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls off your socks.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull off " + clothingOwner.getName("the") + "'s socks.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	SOCK_KNEEHIGH_SOCKS("a pair of", true, "knee-high socks", "A pair of knee-high socks, made from cotton and with an elastic band at the top.", 1, Femininity.FEMININE, InventorySlot.SOCK, Rarity.COMMON, null, "sock_kneehigh_socks", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET), new ListValue<ClothingAccess>(ClothingAccess.CALVES)), null, null))),

			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the socks.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls on " + clothing.getName(true) + ".");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your socks.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls off <her> socks.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls off your socks.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull off " + clothingOwner.getName("the") + "'s socks.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	SOCK_TIGHTS("a pair of", true, "pantyhose", "A pair of pantyhose that reach up to the wearer's lower abdomen.", 1, Femininity.FEMININE, InventorySlot.SOCK, Rarity.COMMON, null, "sock_tights", null,

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET), new ListValue<ClothingAccess>(ClothingAccess.CALVES), new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)), null,
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN_LOW_LEVEL)))),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.PULLS_DOWN, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN)),
							Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.ANUS), new ListValue<CoverableArea>(CoverableArea.PENIS), new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN))))),

			null,

			Util.newArrayListOfValues(new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_WHITE), new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_BLACK))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You push your feet into the pantyhose and pull them up to your waist.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " pushes <her> feet into " + clothing.getName(true) + " and pulls them up to <her> waist.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your pantyhose.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls off <her> pantyhose.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls off your pantyhose.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull off " + clothingOwner.getName("the") + "'s pantyhose.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull the top part of your pantyhose down.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls the top part of <her> pantyhose down.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls the top part of your pantyhose down.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull the top part of " + clothingOwner.getName("the") + "'s pantyhose down.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your pantyhose back up to your waist.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> pantyhose back up to <her> waist.");
		}
	},
	SOCK_FISHNET_STOCKINGS("a pair of", true, "fishnet stockings", "A pair of fishnet stockings that reach up to the wearer's mid-thigh.", 1, Femininity.FEMININE, InventorySlot.SOCK, Rarity.COMMON, null, "sock_fishnets", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET), new ListValue<ClothingAccess>(ClothingAccess.CALVES)), null, null))),

			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the fishnet stockings.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls on " + clothing.getName(true) + ".");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your fishnet stockings.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls off <her> fishnet stockings.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls off your fishnet stockings.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull off " + clothingOwner.getName("the") + "'s fishnet stockings.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},

	// ANKLE

	ANKLE_BRACELET("an", false, "anklet", "A delicate ankle bracelet, it consists of a series of metallic orbs threaded onto a fine metal wire.", 1, Femininity.FEMININE, InventorySlot.ANKLE, Rarity.COMMON, null, "ankle_bracelet", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.CALVES)), null,
					Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.CALVES))))),

			null,

			Util.newArrayListOfValues(new ListValue<Colour>(Colour.CLOTHING_COPPER), new ListValue<Colour>(Colour.CLOTHING_SILVER), new ListValue<Colour>(Colour.CLOTHING_GOLD))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You fasten the anklet around your ankle.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " fastens " + clothing.getName(true) + " around <her> ankle.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You unfasten and remove your anklet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " unfastens and removes <her> anklet.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " unfastens and removes your anklet.";
				else
					return UtilText.genderParsing(clothingOwner, "You unfasten and remove " + clothingOwner.getName("the") + "'s anklet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	
	ANKLE_SHIN_GUARDS("a par of", true, "shin guards", "A pair of protective shin guards, designed to protect the lower half of the wearer's legs from physical blows.", 2, null, InventorySlot.ANKLE, Rarity.COMMON, null, "ankle_shin_guards", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.CALVES)), null,
					Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.CALVES))))),

			null,

			Colour.allClothingColours) {
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
					return UtilText.parse(clothingOwner, "[npc.Name] unfastens and removes your shin guards.");
				} else {
					return UtilText.parse(clothingOwner, "You unfasten and remove [npc.name]'s shin guards.");
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},

	// FOOT
	// Trainers
	// Smart shoes
	FOOT_HEELS("a pair of", true, "heels", "A pair of high heels.", 1, Femininity.FEMININE, InventorySlot.FOOT, Rarity.COMMON, null, "foot_heels", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET)), null, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET))))),

			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You push your feet into the heels.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pushes <her> feet into " + clothing.getName(true) + ".");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You slip off your heels.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " slips off <her> heels.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls off your heels.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull off " + clothingOwner.getName("the") + "'s heels.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	FOOT_ANKLE_BOOTS("a pair of", true, "ankle boots", "A pair of feminine ankle boots.", 1, Femininity.FEMININE, InventorySlot.FOOT, Rarity.COMMON, null, "foot_ankle_boots", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET)), null, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET))))),

			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You push your feet into the ankle boots and zip up the sides.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " pushes <her> feet into " + clothing.getName(true) + " and zips up the sides.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You unzip your ankle boots and pull them off.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " unzips <her> ankle boots and pulls them off.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls off your ankle boots.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull off " + clothingOwner.getName("the") + "'s ankle boots.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	FOOT_PLATFORM_BOOTS("a pair of", true, "platform boots", "A pair of platform boots.", 1, Femininity.FEMININE, InventorySlot.FOOT, Rarity.COMMON, null, "foot_platform_boots", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET)), null, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET))))),

			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You push your feet into the platform boots and zip up the sides.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " pushes <her> feet into " + clothing.getName(true) + " and zips up the sides.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You unzip your platform boots and pull them off.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " unzips <her> platform boots and pulls them off.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls off your platform boots.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull off " + clothingOwner.getName("the") + "'s platform boots.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	FOOT_THIGH_HIGH_BOOTS("a pair of", true, "thigh high boots", "A pair of thigh high boots.", 1, Femininity.FEMININE, InventorySlot.FOOT, Rarity.COMMON, null, "foot_thigh_high_boots", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET)), null, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET))))),

			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You push your feet into the boots and pull them up to your mid-thigh.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " pushes <her> feet into the boots and pulls them up to mid-thigh.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your thigh high boots.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls off <her> thigh high boots.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls off your thigh high boots.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull off " + clothingOwner.getName("the") + "'s thigh high boots.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	FOOT_MENS_SMART_SHOES("a pair of", true, "men's shoes", "A pair of smart men's shoes, suitable for all occasions.", 1, Femininity.MASCULINE, InventorySlot.FOOT, Rarity.COMMON, null, "foot_mens_smart_shoes", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET)), null, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET))))),

			null,

			Util.newArrayListOfValues(new ListValue<Colour>(Colour.CLOTHING_BLACK), new ListValue<Colour>(Colour.CLOTHING_WHITE))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You push your feet into the shoes and tie up the laces.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " pushes <her> feet into " + clothing.getName(true) + " and ties up the laces.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You untie the laces and pull your shoes off.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " unties <her> laces and pulls <her> shoes off.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls off your shoes.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull off " + clothingOwner.getName("the") + "'s shoes.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	FOOT_WORK_BOOTS("a pair of", true, "work boots", "A pair of heavy, steel-capped work boots.", 2, Femininity.MASCULINE, InventorySlot.FOOT, Rarity.COMMON, null, "foot_work_boots", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET)), null, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET))))),

			null,

			Util.newArrayListOfValues(
					new ListValue<Colour>(Colour.CLOTHING_BLACK),
					new ListValue<Colour>(Colour.CLOTHING_BROWN),
					new ListValue<Colour>(Colour.CLOTHING_TAN))) {
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
					return UtilText.parse(clothingOwner, "[npc.Name] pulls your boots off.");
				} else {
					return UtilText.parse(clothingOwner, "You pull off [npc.name]'s boots.");
				}
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	FOOT_TRAINERS("a pair of", true, "trainers", "A pair of unisex trainers, ideal for any kind of exercise.", 1, null, InventorySlot.FOOT, Rarity.COMMON, null, "foot_trainers", null,

			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET)), null, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET))))),

			null,

			Colour.allClothingColours) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You push your feet into the trainers and tie up the laces.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " pushes <her> feet into " + clothing.getName(true) + " and ties up the laces.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You untie the laces and pull your trainers off.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " unties <her> laces and pulls <her> trainers off.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls off your trainers.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull off " + clothingOwner.getName("the") + "'s trainers.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},

	// CLOTHING SETS:

	// MAID:
	MAID_HEADPIECE("a", false, "Maid's headpiece", "A heavily stylised Maid's headpiece, it consists of a coloured headband with a decorative white lace attached on top.", 1, Femininity.FEMININE, InventorySlot.HEAD, Rarity.EPIC, ClothingSet.MAID,
			"maidHeadband", Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_MANA, 2)),

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.HEAD)), null, null))),

			null,

			Util.newArrayListOfValues(new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_PINK_LIGHT), new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_BLACK))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You slide the headpiece onto your head.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " slides " + clothing.getName(true) + " onto <her> head.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You take your headpiece off.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " takes <her> headpiece off.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls off your headpiece.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull off " + clothingOwner.getName("the") + "'s headpiece.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

	},
	MAID_DRESS("a", false, "Maid's dress", "A heavily stylised Maid's dress, it consists of a coloured one-piece dress with decorative white lace trimmings." + " A small white apron is attached to the front, and is similarly trimmed in white lace.",
			1, Femininity.FEMININE, InventorySlot.TORSO, Rarity.EPIC, ClothingSet.MAID, "maidDress", Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_MANA, 6)),

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER), new ListValue<ClothingAccess>(ClothingAccess.HEAD)),
							Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER), new ListValue<ClothingAccess>(ClothingAccess.CHEST), new ListValue<ClothingAccess>(ClothingAccess.WAIST)))),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.PULLS_UP, null,
							Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.ANUS), new ListValue<CoverableArea>(CoverableArea.PENIS), new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN))))),

			Util.newArrayListOfValues(new ListValue<InventorySlot>(InventorySlot.LEG)),

			Util.newArrayListOfValues(new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_PINK_LIGHT), new ListValue<com.base.utils.Colour>(com.base.utils.Colour.CLOTHING_BLACK))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the dress and pull it up over your torso, zipping yourself up at the back before making sure the trimmings and apron are neatly arranged.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " steps into " + clothing.getName(true)
						+ " and pulls it up to cover <her> torso," + " zipping <herPro>self up at the back before making sure the trimmings and apron are neatly arranged.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You reach back and unzip your Maid's dress, pulling your arms out before sliding it down your body and stepping out.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " reaches back and unzips <her> Maid's dress, pulling <her> arms out before sliding it down <her> body and stepping out.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " unzips the back of your Maid's dress." + " Having done so, " + clothingRemover.getGender().getSecondPerson()
							+ " pulls your arms free to allow the dress to fall to the floor.";
				else
					return UtilText.genderParsing(clothingOwner, "You unzip the back of " + clothingOwner.getName("the") + "'s Maid's dress." + " Having done so, you pull "
							+ clothingOwner.getGender().getPossessiveBeforeNoun() + " arms free to allow the dress to fall to the floor.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (dt == DisplacementType.PULLS_DOWN) {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You unzip the back of your dress and pull down the top half.";
				else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
					return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " unzips the back of <her> dress and pulls the top half down");
				else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " unzips the back of your dress and pulls the top half down.";
					else
						return UtilText.genderParsing(clothingOwner, "You unzip the back of " + clothingOwner.getName("the") + "'s dress and pull the top half down.");
				}
			} else {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull up your dress's skirt.";
				else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
					return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls up <her> dress's skirt.");
				else {
					if (clothingOwner.isPlayer())
						return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls up your dress's skirt.";
					else
						return UtilText.genderParsing(clothingOwner, "You pull up the skirt of " + clothingOwner.getName("the") + "'s dress.");
				}
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (dt == DisplacementType.PULLS_DOWN) {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull your dress back up into the correct position and zip yourself up.";
				else
					return UtilText.genderParsing(clothingOwner,
							Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> dress up into the correct position and zips <herPro>self up.");
			} else {
				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
					return "You pull your dress's skirt back down.";
				else
					return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> dress's skirt back down.");
			}
		}
	},
	MAID_STOCKINGS("a pair of", true, "Maid's stockings", "A pair of cotton Maid's stockings, with a coloured bow near the top.", 1, Femininity.FEMININE, InventorySlot.SOCK, Rarity.EPIC, ClothingSet.MAID, "maidStockings",
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_MANA, 2)),

			Util.newArrayListOfValues(new ListValue<BlockedParts>(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET), new ListValue<ClothingAccess>(ClothingAccess.CALVES)), null, null))),

			null,

			Util.newArrayListOfValues(new ListValue<Colour>(Colour.CLOTHING_PINK_LIGHT), new ListValue<Colour>(Colour.CLOTHING_BLACK))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the stockings.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls on " + clothing.getName(true) + ".");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your stockings.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls off <her> stockings.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls off your stockings.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull off " + clothingOwner.getName("the") + "'s stockings.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	MAID_HEELS("a pair of", true, "Maid's high heels", "A pair of Maid's high heels, they are made of coloured leather with a small amount of white lace decoration.", 1, Femininity.FEMININE, InventorySlot.FOOT, Rarity.EPIC, ClothingSet.MAID,
			"maidHeels", Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_MANA, 2)),

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.FEET)), null, null))),

			null,

			Util.newArrayListOfValues(new ListValue<Colour>(Colour.CLOTHING_PINK_LIGHT), new ListValue<Colour>(Colour.CLOTHING_BLACK))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You slip on the heels and buckle up the straps.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " slips on " + clothing.getName(true) + " and buckles up the straps.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You unbuckle your heels and slip them off.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " unbuckles <her> heels and slips them off.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " unbuckles your heels and pulls them off.";
				else
					return UtilText.genderParsing(clothingOwner, "You unbuckle " + clothingOwner.getName("the") + "'s heels and pull them off.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	MAID_SLEEVES("a pair of", true, "Maid's sleeves", "A pair of Maid's sleeves that end just past the elbow. They are made of soft coloured fabric with white lace trimmings.", 1, Femininity.FEMININE, InventorySlot.HAND, Rarity.EPIC,
			ClothingSet.MAID, "maidGloves", Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_MANA, 2)),

			Util.newArrayListOfValues(new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.WRISTS)), null, null))),

			null,

			Util.newArrayListOfValues(new ListValue<Colour>(Colour.CLOTHING_PINK_LIGHT), new ListValue<Colour>(Colour.CLOTHING_BLACK))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the sleeves.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls on " + clothing.getName(true) + ".");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull off your sleeves.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls off <her> sleeves.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls off your sleeves.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull off " + clothingOwner.getName("the") + "'s sleeves.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},

	// // BDSM:
	// BDSM_BALLGAG("a", false, "ballgag", "A leather strap with a rubber ball
	// fixed into one side.",
	// 0, null,
	// InventorySlot.MOUTH, Rarity.EPIC, ClothingSet.BDSM,
	// "mouth_ballgag",
	// Utilities.newHashMapOfValues(new Value<Attribute,
	// Integer>(Attribute.FITNESS, -5)),
	//
	// Utilities.newHashMapOfValues(
	// new Value<DisplacementType, BlockedParts>(
	// DisplacementType.PULLS_OUT,
	// new BlockedParts(
	// Utilities.newArrayListOfValues(
	// new ListValue<CoverableArea>(CoverableArea.MOUTH)),
	// Utilities.newArrayListOfValues(
	// new ListValue<ClothingAccess>(ClothingAccess.MOUTH))
	// )
	// )
	// ),
	//
	// Utilities.newArrayListOfValues(
	// new ListValue<ClothingAccess>(ClothingAccess.MOUTH)
	// ),
	//
	// null,
	//
	// Utilities.newArrayListOfValues(
	// new ListValue<Colour>(com.base.utils.Colour.BLACK),
	// new ListValue<Colour>(com.base.utils.Colour.WHITE),
	// new ListValue<Colour>(com.base.utils.Colour.RED))) {
	// @Override
	// public String equipText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover, boolean rough, AbstractClothing
	// clothing, boolean applyEffects) {
	// if(clothingOwner.isPlayer() && clothingRemover.isPlayer())
	// return "You open your mouth wide and slide in the ballgag, fastening the
	// buckles to keep it in place.";
	// else
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// Utilities.capitaliseSentence(clothingOwner.getNameWithDeterminer("the"))+"
	// opens <her> mouth wide and inserts "+clothing.getName(true)+", fastening
	// the buckles to keep it in place.");
	// }
	// @Override
	// public String unequipText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover, boolean rough, AbstractClothing
	// clothing, boolean applyEffects){
	// if(clothingOwner.isPlayer() && clothingRemover.isPlayer())
	// return "You unfasten your ballgag's buckles and take it out of your
	// mouth.";
	// else if(!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// Utilities.capitaliseSentence(clothingOwner.getNameWithDeterminer("the"))+"
	// unfastens the buckles on <her> ballgag and takes it out of <her>
	// mouth.");
	// else{
	// if(clothingOwner.isPlayer())
	// return
	// Utilities.capitaliseSentence(clothingRemover.getNameWithDeterminer("the"))+"
	// unfastens the buckles of your ballgag before taking it out of your
	// mouth.";
	// else
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// "You unfasten the buckles on
	// "+clothingOwner.getNameWithDeterminer("the")+"'s ballgag before taking it
	// out of <her> mouth.");
	// }
	// }
	// @Override
	// public String displaceText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover,
	// DisplacementType dt, boolean rough) {
	// return null;
	// }
	// @Override
	// public String replaceText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover,
	// DisplacementType dt, boolean rough) {
	// return null;
	// }
	// },
	// BDSM_RINGGAG("a", false, "ring gag", "A leather strap with a metal ring
	// fixed into one side.",
	// 0, null,
	// InventorySlot.MOUTH, Rarity.EPIC, ClothingSet.BDSM,
	// "mouth_ringgag",
	// Utilities.newHashMapOfValues(new Value<Attribute,
	// Integer>(Attribute.FITNESS, -5)),
	//
	// Utilities.newHashMapOfValues(
	// new Value<DisplacementType, BlockedParts>(
	// DisplacementType.PULLS_OUT,
	// new BlockedParts(
	// Utilities.newArrayListOfValues(
	// new ListValue<CoverableArea>(CoverableArea.MOUTH)),
	// Utilities.newArrayListOfValues(
	// new ListValue<ClothingAccess>(ClothingAccess.MOUTH))
	// )
	// )
	// ),
	//
	// Utilities.newArrayListOfValues(
	// new ListValue<ClothingAccess>(ClothingAccess.MOUTH)
	// ),
	//
	// null,
	//
	// Utilities.newArrayListOfValues(
	// new ListValue<Colour>(com.base.utils.Colour.BLACK),
	// new ListValue<Colour>(com.base.utils.Colour.SILVER),
	// new ListValue<Colour>(com.base.utils.Colour.RED),
	// new ListValue<Colour>(com.base.utils.Colour.GOLD))) {
	// @Override
	// public String equipText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover, boolean rough, AbstractClothing
	// clothing, boolean applyEffects) {
	// if(clothingOwner.isPlayer() && clothingRemover.isPlayer())
	// return "You open your mouth wide and slide in the ring gag, fastening the
	// buckles to keep it in place.";
	// else
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// Utilities.capitaliseSentence(clothingOwner.getNameWithDeterminer("the"))+"
	// opens <her> mouth wide and inserts "+clothing.getName(true)+", fastening
	// the buckles to keep it in place.");
	// }
	// @Override
	// public String unequipText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover, boolean rough, AbstractClothing
	// clothing, boolean applyEffects){
	// if(clothingOwner.isPlayer() && clothingRemover.isPlayer())
	// return "You unfasten your ring gag's buckles and take it out of your
	// mouth.";
	// else if(!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// Utilities.capitaliseSentence(clothingOwner.getNameWithDeterminer("the"))+"
	// unfastens the buckles on <her> ring gag and takes it out of <her>
	// mouth.");
	// else{
	// if(clothingOwner.isPlayer())
	// return
	// Utilities.capitaliseSentence(clothingRemover.getNameWithDeterminer("the"))+"
	// unfastens the buckles of your ring gag before taking it out of your
	// mouth.";
	// else
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// "You unfasten the buckles on
	// "+clothingOwner.getNameWithDeterminer("the")+"'s ring gag before taking
	// it out of <her> mouth.");
	// }
	// }
	// @Override
	// public String displaceText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover,
	// DisplacementType dt, boolean rough) {
	// return null;
	// }
	// @Override
	// public String replaceText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover,
	// DisplacementType dt, boolean rough) {
	// return null;
	// }
	// },
	// BDSM_CHOKER("a", false, "choker", "A leather choker with a loose metallic
	// ring attached to the front.",
	// 0, null,
	// InventorySlot.NECK, Rarity.EPIC, ClothingSet.BDSM,
	// "neck_choker",
	// Utilities.newHashMapOfValues(new Value<Attribute,
	// Integer>(Attribute.FITNESS, -2)),
	//
	// null, // Map<DisplacementType, BlockedParts> blockedPartsMap
	// null,
	// null, // List<InventorySlot> incompatibleSlots
	//
	// Utilities.newArrayListOfValues(
	// new ListValue<Colour>(com.base.utils.Colour.BLACK),
	// new ListValue<Colour>(com.base.utils.Colour.SILVER),
	// new ListValue<Colour>(com.base.utils.Colour.RED),
	// new ListValue<Colour>(com.base.utils.Colour.GOLD))) {
	// @Override
	// public String equipText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover, boolean rough, AbstractClothing
	// clothing, boolean applyEffects) {
	// if(clothingOwner.isPlayer() && clothingRemover.isPlayer())
	// return "You wrap the choker around your neck and fasten the buckle.";
	// else
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// Utilities.capitaliseSentence(clothingOwner.getNameWithDeterminer("the"))+"
	// wraps "+clothing.getName(true)+" around <her> neck and fastens the
	// buckle.");
	// }
	// @Override
	// public String unequipText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover, boolean rough, AbstractClothing
	// clothing, boolean applyEffects){
	// if(clothingOwner.isPlayer() && clothingRemover.isPlayer())
	// return "You unfasten your choker's buckle and remove it from your neck.";
	// else if(!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// Utilities.capitaliseSentence(clothingOwner.getNameWithDeterminer("the"))+"
	// unfastens the buckle on <her> choker and removes it from <her> neck.");
	// else{
	// if(clothingOwner.isPlayer())
	// return
	// Utilities.capitaliseSentence(clothingRemover.getNameWithDeterminer("the"))+"
	// unfastens the buckle of your choker and removes it from your neck.";
	// else
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// "You unfasten the buckle on
	// "+clothingOwner.getNameWithDeterminer("the")+"'s choker before removing
	// it from <her> neck.");
	// }
	// }
	// @Override
	// public String displaceText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover,
	// DisplacementType dt, boolean rough) {
	// return null;
	// }
	// @Override
	// public String replaceText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover,
	// DisplacementType dt, boolean rough) {
	// return null;
	// }
	// },
	// BDSM_RESTRAINTS("a pair of", false, "wrist restraints", "A pair of
	// leather wrist restraints. Each restraint's buckle has a specially
	// designed lock, preventing the wearer from removing them.",
	// 1, null,
	// InventorySlot.WRIST, Rarity.EPIC, ClothingSet.BDSM,
	// "wrist_restraints",
	// null,
	//
	// Utilities.newHashMapOfValues(
	// new Value<DisplacementType, BlockedParts>(
	// DisplacementType.REMOVE_OR_EQUIP,
	// new BlockedParts(
	// null,
	// Utilities.newArrayListOfValues(
	// new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER),
	// new ListValue<ClothingAccess>(ClothingAccess.WRISTS))
	// )
	// )
	// ),
	//
	// Utilities.newArrayListOfValues(
	// new ListValue<ClothingAccess>(ClothingAccess.WRISTS)
	// ),
	//
	// null,
	//
	// Utilities.newArrayListOfValues(
	// new ListValue<com.base.utils.Colour>(com.base.utils.Colour.PINK_LIGHT),
	// new ListValue<com.base.utils.Colour>(com.base.utils.Colour.BLUE_LIGHT),
	// new ListValue<com.base.utils.Colour>(com.base.utils.Colour.BLACK),
	// new ListValue<com.base.utils.Colour>(com.base.utils.Colour.WHITE))) {
	// @Override
	// public String equipText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover, boolean rough, AbstractClothing
	// clothing, boolean applyEffects) {
	// if(clothingOwner.isPlayer() && clothingRemover.isPlayer())
	// return "You slide the restraints over your wrists and awkwardly fasten
	// the straps.";
	// else
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// Utilities.capitaliseSentence(clothingOwner.getNameWithDeterminer("the"))+"
	// slides "+clothing.getName(true)+" over <her> wrists and awkwardly fastens
	// the straps.");
	// }
	// @Override
	// public String unequipText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover, boolean rough, AbstractClothing
	// clothing, boolean applyEffects){
	// if(clothingOwner.isPlayer() && clothingRemover.isPlayer())
	// return "You unfasten your restraints and remove them.";
	// else if(!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// Utilities.capitaliseSentence(clothingOwner.getNameWithDeterminer("the"))+"
	// unfastens <her> restraints and removes them.");
	// else{
	// if(clothingOwner.isPlayer())
	// return
	// Utilities.capitaliseSentence(clothingRemover.getNameWithDeterminer("the"))+"
	// unfastens your restraints and removes them.";
	// else
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// "You unfasten "+clothingOwner.getNameWithDeterminer("the")+"'s restraints
	// and remove them.");
	// }
	// }
	// @Override
	// public String displaceText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover,
	// DisplacementType dt, boolean rough) {
	// return null;
	// }
	// @Override
	// public String replaceText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover,
	// DisplacementType dt, boolean rough) {
	// return null;
	// }
	// },
	// BDSM_SPREADER_BAR("a", true, "spreader bar", "A pair of ankle restraints,
	// attached to one another by a long metal pole. When worn, the wearer's
	// legs are forced open.",
	// 1, null,
	// InventorySlot.ANKLE, Rarity.EPIC, ClothingSet.BDSM,
	// "ankle_spreaderbar",
	// Utilities.newHashMapOfValues(new Value<Attribute,
	// Integer>(Attribute.FITNESS, -5)),
	//
	// Utilities.newHashMapOfValues(
	// new Value<DisplacementType, BlockedParts>(
	// DisplacementType.REMOVE_OR_EQUIP,
	// new BlockedParts(
	// null,
	// Utilities.newArrayListOfValues(
	// new ListValue<ClothingAccess>(ClothingAccess.CALVES),
	// new ListValue<ClothingAccess>(ClothingAccess.LEGS_BETWEEN))
	// )
	// )
	// ),
	//
	// Utilities.newArrayListOfValues(
	// new ListValue<ClothingAccess>(ClothingAccess.CALVES)
	// ),
	//
	// null,
	//
	// Utilities.newArrayListOfValues(
	// new ListValue<Colour>(Colour.PINK_LIGHT),
	// new ListValue<Colour>(Colour.BLUE_LIGHT),
	// new ListValue<Colour>(Colour.WHITE),
	// new ListValue<Colour>(Colour.BLACK))) {
	// @Override
	// public String equipText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover, boolean rough, AbstractClothing
	// clothing, boolean applyEffects) {
	// if(clothingOwner.isPlayer() && clothingRemover.isPlayer())
	// return "You wrap the spreader bar's restraints around your ankles and
	// fasten the straps, leaving your legs wide open.";
	// else
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// Utilities.capitaliseSentence(clothingOwner.getNameWithDeterminer("the"))+"
	// wraps "+clothing.getName(true)+" restraints around <her> ankles and
	// fastens the straps, leaving <her> legs wide open.");
	// }
	// @Override
	// public String unequipText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover, boolean rough, AbstractClothing
	// clothing, boolean applyEffects){
	// if(clothingOwner.isPlayer() && clothingRemover.isPlayer())
	// return "You unfasten your spreader bar's restraints and remove it.";
	// else if(!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// Utilities.capitaliseSentence(clothingOwner.getNameWithDeterminer("the"))+"
	// unfastens <her> spreader bar's restraints and removes it.");
	// else{
	// if(clothingOwner.isPlayer())
	// return
	// Utilities.capitaliseSentence(clothingRemover.getNameWithDeterminer("the"))+"
	// unfastens your spreader bar's restraints and removes it.";
	// else
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// "You unfasten the restraints on
	// "+clothingOwner.getNameWithDeterminer("the")+"'s spreader bar and remove
	// it.");
	// }
	// }
	// @Override
	// public String displaceText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover,
	// DisplacementType dt, boolean rough) {
	// return null;
	// }
	// @Override
	// public String replaceText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover,
	// DisplacementType dt, boolean rough) {
	// return null;
	// }
	// },
	// //TODO special locking
	// BDSM_CHASTITY_BELT("a", true, "chastity belt", "A metal chastity belt
	// that can be lcoked into place in order to deny access to the wearer's
	// vagina. A hole in the strap provides access to the wearer's asshole.",
	// 1, Femininity.FEMININE,
	// InventorySlot.GROIN, Rarity.EPIC, ClothingSet.BDSM,
	// "groin_chastity_belt",
	// null,
	//
	// Utilities.newHashMapOfValues(
	// new Value<DisplacementType, BlockedParts>(
	// DisplacementType.REMOVE_OR_EQUIP,
	// new BlockedParts(
	// Utilities.newArrayListOfValues(
	// new ListValue<CoverableArea>(CoverableArea.PENIS),
	// new ListValue<CoverableArea>(CoverableArea.VAGINA)),
	// Utilities.newArrayListOfValues(
	// new ListValue<ClothingAccess>(ClothingAccess.GROIN))
	// )
	// )
	// ),
	//
	// Utilities.newArrayListOfValues(
	// new ListValue<ClothingAccess>(ClothingAccess.LEGS_BETWEEN),
	// new ListValue<ClothingAccess>(ClothingAccess.GROIN)
	// ),
	//
	// null,
	//
	// Utilities.newArrayListOfValues(new
	// ListValue<com.base.utils.Colour>(com.base.utils.Colour.PINK_LIGHT),
	// new ListValue<com.base.utils.Colour>(com.base.utils.Colour.BLUE_LIGHT),
	// new ListValue<com.base.utils.Colour>(com.base.utils.Colour.BLACK),
	// new ListValue<com.base.utils.Colour>(com.base.utils.Colour.WHITE))) {
	// @Override
	// public String equipText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover, boolean rough, AbstractClothing
	// clothing, boolean applyEffects) {
	// if(clothingOwner.isPlayer() && clothingRemover.isPlayer())
	// return "You fasten the chastity belt around your crotch and slide the
	// lock shut.";
	// else
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// Utilities.capitaliseSentence(clothingOwner.getNameWithDeterminer("the"))+"
	// fastens "+clothing.getName(true)+" around <her> crotch and slides the
	// lock shut.");
	// }
	// @Override
	// public String unequipText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover, boolean rough, AbstractClothing
	// clothing, boolean applyEffects){
	// if(clothingOwner.isPlayer() && clothingRemover.isPlayer())
	// return "You unlock your chastity belt and slide it off.";
	// else if(!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// Utilities.capitaliseSentence(clothingOwner.getNameWithDeterminer("the"))+"
	// unlocks <her> chastity belt and slides it off.");
	// else{
	// if(clothingOwner.isPlayer())
	// return
	// Utilities.capitaliseSentence(clothingRemover.getNameWithDeterminer("the"))+"
	// unlocks your chastity belt and slides it off.";
	// else
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// "You unlock "+clothingOwner.getNameWithDeterminer("the")+"'s chastity
	// belt and slide it off.");
	// }
	// }
	// @Override
	// public String displaceText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover,
	// DisplacementType dt, boolean rough) {
	// return null;
	// }
	// @Override
	// public String replaceText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover,
	// DisplacementType dt, boolean rough) {
	// return null;
	// }
	// },
	// //TODO special locking
	// BDSM_CHASTITY_CAGE("a", false, "chastity cage", "A little cage designed
	// to imprison a cock. The small padlock on the top appears to be purely
	// decorative, but you get the feeling that there's"
	// + " still some way in which this cage can be locked.",
	// 1, Femininity.MASCULINE,
	// InventorySlot.GROIN, Rarity.EPIC, ClothingSet.BDSM,
	// "groin_chastityCage",
	// null,
	//
	// Utilities.newHashMapOfValues(
	// new Value<DisplacementType, BlockedParts>(
	// DisplacementType.REMOVE_OR_EQUIP,
	// new BlockedParts(
	// Utilities.newArrayListOfValues(
	// new ListValue<CoverableArea>(CoverableArea.PENIS)),
	// Utilities.newArrayListOfValues(
	// new ListValue<ClothingAccess>(ClothingAccess.GROIN))
	// )
	// )
	// ),
	//
	// Utilities.newArrayListOfValues(
	// new ListValue<ClothingAccess>(ClothingAccess.GROIN)
	// ),
	//
	// null,
	//
	// Utilities.newArrayListOfValues(new
	// ListValue<com.base.utils.Colour>(com.base.utils.Colour.PINK_LIGHT),
	// new ListValue<com.base.utils.Colour>(com.base.utils.Colour.BLUE_LIGHT),
	// new ListValue<com.base.utils.Colour>(com.base.utils.Colour.WHITE))) {
	// @Override
	// public String equipText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover, boolean rough, AbstractClothing
	// clothing, boolean applyEffects) {
	//// if(clothingOwner.getPenisType()==PenisType.NONE){
	//// clothingOwner.unequipClothingIntoInventory(CHASTITY_CAGE, true,
	// clothingRemover);
	//// }else{
	//// clothingOwner.sealedSlotsAddSlot(InventorySlot.GROIN);
	//// }
	//// if(Main.game.getPlayer().getSecondPenisType()==PenisType.NONE)
	// return "You clip one the chastity cage in place around your cock, and as
	// you do so, it briefly glows purple."
	// + " <b style='color:"+Colour.SEALED+";'>The chastity
	// cage has sealed itself onto your cock!</b>";
	//// else
	//// return "You clip the chastity cage in place around your main cock, and
	// as you do so, it briefly glows purple."
	//// + " A second chastity cage appears around your second cock, before they
	// both seal themselves in place."
	//// + " <b style='color:"+Colour.SEALED+";'>The chastity
	// cages have sealed themselves onto your cocks!</b>";
	// }
	//
	// @Override
	// public String unequipText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover, boolean rough, AbstractClothing
	// clothing, boolean applyEffects){
	// if(clothingOwner.isPlayer() && clothingRemover.isPlayer()){
	// if(Main.game.getPlayer().getPenisType()==PenisType.NONE)
	// return "Without a penis, the chastity cage doesn't have anything to hold
	// onto, and it falls off uselessly.";
	//
	// return "You unclip the chastity cage and pull it off.";
	// }else{
	// if(rough){
	// if(clothingOwner.isPlayer())
	// return
	// Utilities.capitaliseSentence(clothingRemover.getNameWithDeterminer("the"))+"
	// snaps the padlock from your chastity cage, and in doing so, breaks its"
	// + " seal. You gasp as "+clothingRemover.getGender().getSecondPerson()+"
	// pulls the little cage off, freeing your cock.";
	// else
	// return "You snap the padlock of
	// "+clothingOwner.getNameWithDeterminer("the")+"'s chastity cage, and in
	// doing so, break its seal."
	// +Utilities.capitaliseSentence(clothingOwner.getGender().getSecondPerson())+"
	// lets out a gasp as you pull the little cage off, freeing"
	// + clothingOwner.getGender().getPossessive()+" cock.";
	// }else{
	// if(clothingOwner.isPlayer())
	// return
	// Utilities.capitaliseSentence(clothingRemover.getNameWithDeterminer("the"))+"
	// carefully unclips the padlock from your chastity cage, and in doing so,"
	// + " breaks its seal. You gasp as
	// "+clothingRemover.getGender().getSecondPerson()+" pulls the little cage
	// off, freeing your cock.";
	// else
	// return "You carefully unclip the padlock of
	// "+clothingOwner.getNameWithDeterminer("the")+"'s chastity cage, and in
	// doing so, break its seal."
	// +Utilities.capitaliseSentence(clothingOwner.getGender().getSecondPerson())+"
	// lets out a gasp as you pull the little cage off, freeing"
	// + clothingOwner.getGender().getPossessive()+" cock.";
	// }
	// }
	// }
	// @Override
	// public String displaceText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover,
	// DisplacementType dt, boolean rough) {
	// return null;
	// }
	// @Override
	// public String replaceText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover,
	// DisplacementType dt, boolean rough) {
	// return null;
	// }
	//
	// @Override
	// public boolean isBlocksFromSight(){
	// return false;
	// }
	// },
	// BDSM_KARADA("a", false, "magic karada", "A length of rope that magically
	// forms a type of rope harness that loops around the wearer's neck, around
	// their body, and under their crotch."
	// + " The tight rope digs into, and blocks, the wearer's asshole and
	// vagina.",
	// 1, null,
	// InventorySlot.STOMACH, Rarity.EPIC, ClothingSet.BDSM,
	// "stomach_karada",
	// Utilities.newHashMapOfValues(new Value<Attribute,
	// Integer>(Attribute.FITNESS, -5)),
	//
	// Utilities.newHashMapOfValues(
	// new Value<DisplacementType, BlockedParts>(
	// DisplacementType.REMOVE_OR_EQUIP,
	// new BlockedParts(
	// null,
	// Utilities.newArrayListOfValues(
	// new ListValue<ClothingAccess>(ClothingAccess.GROIN),
	// new ListValue<ClothingAccess>(ClothingAccess.CHEST),
	// new ListValue<ClothingAccess>(ClothingAccess.WAIST))
	// )
	// )
	// ),
	//
	// Utilities.newArrayListOfValues(
	// new ListValue<ClothingAccess>(ClothingAccess.GROIN),
	// new ListValue<ClothingAccess>(ClothingAccess.WAIST),
	// new ListValue<ClothingAccess>(ClothingAccess.CHEST)
	// ),
	//
	// Utilities.newArrayListOfValues(new
	// ListValue<InventorySlot>(InventorySlot.GROIN),
	// new ListValue<InventorySlot>(InventorySlot.STOMACH),
	// new ListValue<InventorySlot>(InventorySlot.CHEST)),
	//
	// Utilities.newArrayListOfValues(
	// new ListValue<com.base.utils.Colour>(com.base.utils.Colour.RED),
	// new ListValue<com.base.utils.Colour>(com.base.utils.Colour.WHITE),
	// new ListValue<com.base.utils.Colour>(com.base.utils.Colour.BLACK))) {
	// @Override
	// public String equipText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover, boolean rough, AbstractClothing
	// clothing, boolean applyEffects) {
	// if(clothingOwner.isPlayer() && clothingRemover.isPlayer())
	// return "You hang the rope around your neck, and as you do so, it snakes
	// and slides around your body with a mind of its own, forming a tight
	// karada within a matter of seconds.";
	// else
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// Utilities.capitaliseSentence(clothingOwner.getNameWithDeterminer("the"))+"
	// hangs "+clothing.getName(true)+" around <her> neck, and as <she> does so,
	// it snakes and slides around <her> body with a mind of its own,"
	// + " forming a tight karada within a matter of seconds.");
	// }
	// @Override
	// public String unequipText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover, boolean rough, AbstractClothing
	// clothing, boolean applyEffects){
	// if(clothingOwner.isPlayer() && clothingRemover.isPlayer())
	// return "You tug at the karada while concentrating on removing it, and as
	// you do so, it unties itself back into a length of slack rope.";
	// else if(!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// Utilities.capitaliseSentence(clothingOwner.getNameWithDeterminer("the"))+"
	// tugs at <her> karada, and as <she> does so, it unties itself back into a
	// length of slack rope.");
	// else{
	// if(clothingOwner.isPlayer())
	// return
	// Utilities.capitaliseSentence(clothingRemover.getNameWithDeterminer("the"))+"
	// tugs at your karada, and as <she> does so, it unties itself back into a
	// length of slack rope.";
	// else
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// "You tug at "+clothingOwner.getNameWithDeterminer("the")+"'s karada, and
	// as you do so, it unties itself back into a length of slack rope.");
	// }
	// }
	// @Override
	// public String displaceText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover,
	// DisplacementType dt, boolean rough) {
	// return null;
	// }
	// @Override
	// public String replaceText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover,
	// DisplacementType dt, boolean rough) {
	// return null;
	// }
	// },

	// TODO All enforcer slots
	// ENFORCER_CAP("an", false, "Enforcer's cap", "An Enforcer's cap, a true
	// symbol of authority.",
	// 1, null,
	// InventorySlot.HEAD, Rarity.EPIC, ClothingSet.ENFORCER,
	// "enforcerCap",
	// Utilities.newHashMapOfValues(new Value<Attribute,
	// Integer>(Attribute.DAMAGE_PHYSICAL, 1)),
	//
	// null,
	// Utilities.newArrayListOfValues(new
	// ListValue<ClothingAccess>(ClothingAccess.HEAD)),
	// null,
	//
	// Utilities.newArrayListOfValues(new ListValue<Colour>(Colour.BLACK))) {
	// @Override
	// public String equipText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover, boolean rough, AbstractClothing
	// clothing, boolean applyEffects) {
	// if(clothingOwner.isPlayer() && clothingRemover.isPlayer())
	// return "You pull on the cap.";
	// else if(!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// Utilities.capitaliseSentence(clothingOwner.getNameWithDeterminer("the"))+"
	// pulls "+clothing.getName(true)+" onto <her> head.");
	// else{
	// if(rough){
	// if(clothingOwner.isPlayer())
	// return
	// Utilities.capitaliseSentence(clothingRemover.getNameWithDeterminer("the"))+"
	// holds you by the chin and pulls "
	// +clothing.getName(true)+" onto your head.";
	// else
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// "You firmly hold "+clothingOwner.getNameWithDeterminer("the")+"'s chin
	// and pull the cap onto <her> head.");
	// }else{
	// if(clothingOwner.isPlayer())
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// Utilities.capitaliseSentence(clothingOwner.getNameWithDeterminer("the"))+"
	// pulls "+clothing.getName(true)+" onto your head.");
	// else
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// "You pull the cap onto "+clothingOwner.getNameWithDeterminer("the")+"'s
	// head.");
	// }
	// }
	// }
	// @Override
	// public String unequipText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover, boolean rough, AbstractClothing
	// clothing, boolean applyEffects){
	// if(clothingOwner.isPlayer() && clothingRemover.isPlayer())
	// return "You take off your cap.";
	// else if(!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// Utilities.capitaliseSentence(clothingOwner.getNameWithDeterminer("the"))+"
	// takes off <her> cap.");
	// else{
	// if(rough){
	// if(clothingOwner.isPlayer())
	// return
	// Utilities.capitaliseSentence(clothingRemover.getNameWithDeterminer("the"))+"
	// grabs your cap and yanks it off.";
	// else
	// return "You grab "+clothingOwner.getNameWithDeterminer("the")+"'s cap and
	// yank it off.";
	// }else{
	// if(clothingOwner.isPlayer())
	// return
	// Utilities.capitaliseSentence(clothingRemover.getNameWithDeterminer("the"))+"
	// pulls off your cap.";
	// else
	// return "You pull off "+clothingOwner.getNameWithDeterminer("the")+"'s
	// cap.";
	// }
	// }
	// }
	// @Override
	// public String displaceText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
	// return null;
	// }
	// @Override
	// public String replaceText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
	// return null;
	// }
	// },
	// ENFORCER_GLOVES("a pair of", true, "Enforcer's gloves", "A pair of long
	// leather gloves, they reach up past the elbow.",
	// 1, null,
	// InventorySlot.HAND, Rarity.EPIC, ClothingSet.ENFORCER,
	// "enforcerGloves",
	// Utilities.newHashMapOfValues(new Value<Attribute,
	// Integer>(Attribute.DAMAGE_PHYSICAL, 1)),
	//
	// Utilities.newHashMapOfValues(
	// new Value<DisplacementType, BlockedParts>(
	// DisplacementType.REMOVE_OR_EQUIP,
	// new BlockedParts(
	// null,
	// Utilities.newArrayListOfValues(
	// new ListValue<ClothingAccess>(ClothingAccess.FINGERS))
	// )
	// )
	// ),
	//
	// Utilities.newArrayListOfValues(
	// new ListValue<ClothingAccess>(ClothingAccess.WRISTS)
	// ),
	//
	// Utilities.newArrayListOfValues(new
	// ListValue<InventorySlot>(InventorySlot.FINGER)),
	//
	// Utilities.newArrayListOfValues(new ListValue<Colour>(Colour.BLACK))) {
	// @Override
	// public String equipText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover, boolean rough, AbstractClothing
	// clothing, boolean applyEffects) {
	// if(clothingOwner.isPlayer() && clothingRemover.isPlayer())
	// return "You pull on the gloves and give your fingers an experimental
	// wiggle.";
	// else
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// Utilities.capitaliseSentence(clothingOwner.getNameWithDeterminer("the"))+"
	// pulls on "+clothing.getName(true)+" and gives <her> fingers an
	// experimental wiggle.");
	// }
	// @Override
	// public String unequipText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover, boolean rough, AbstractClothing
	// clothing, boolean applyEffects){
	// if(clothingOwner.isPlayer() && clothingRemover.isPlayer())
	// return "You pull off your gloves.";
	// else if(!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// Utilities.capitaliseSentence(clothingOwner.getNameWithDeterminer("the"))+"
	// pulls off <her> gloves.");
	// else{
	// if(clothingOwner.isPlayer())
	// return
	// Utilities.capitaliseSentence(clothingRemover.getNameWithDeterminer("the"))+"
	// pulls your gloves off.";
	// else
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// "You pull "+clothingOwner.getNameWithDeterminer("the")+"'s gloves off.");
	// }
	// }
	// @Override
	// public String displaceText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover,
	// DisplacementType dt, boolean rough) {
	// return null;
	// }
	// @Override
	// public String replaceText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover,
	// DisplacementType dt, boolean rough) {
	// return null;
	// }
	// },
	ENFORCER_SHIRT("an", false, "Enforcer's shirt", "An Enforcer's shirt, it comes with what appears to be a stab-proof vest.", 5, null, InventorySlot.TORSO, Rarity.EPIC, ClothingSet.ENFORCER, "enforcerShirt",
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 5),
					new Value<Attribute, Integer>(Attribute.STRENGTH, 5)),

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)), null,
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.ARMS_UP_TO_SHOULDER)))),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.UNBUTTONS, null, Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.NIPPLES)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.CHEST), new ListValue<ClothingAccess>(ClothingAccess.WAIST))))),

			null, // List<InventorySlot> incompatibleSlots

			Util.newArrayListOfValues(new ListValue<Colour>(Colour.CLOTHING_BLUE))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull on the shirt.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls on " + clothing.getName(true) + ".");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You take off your shirt.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " takes off <her> shirt.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your shirt off.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s shirt off.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			return null;
		}
	},
	ENFORCER_SHORTS("a pair of", true, "Enforcer's shorts", "A pair of shorts, they come with a utility belt.", 2, null, InventorySlot.LEG, Rarity.EPIC, ClothingSet.ENFORCER, "enforcerShorts",
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 5),
					new Value<Attribute, Integer>(Attribute.FITNESS, 5)),

			Util.newArrayListOfValues(
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.REMOVE_OR_EQUIP, Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)), null,
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.LEGS_UP_TO_GROIN)))),
					new ListValue<BlockedParts>(new BlockedParts(DisplacementType.PULLS_DOWN, null,
							Util.newArrayListOfValues(new ListValue<CoverableArea>(CoverableArea.ANUS), new ListValue<CoverableArea>(CoverableArea.PENIS), new ListValue<CoverableArea>(CoverableArea.VAGINA)),
							Util.newArrayListOfValues(new ListValue<ClothingAccess>(ClothingAccess.GROIN))))),

			null,

			Util.newArrayListOfValues(new ListValue<Colour>(Colour.CLOTHING_BLUE))) {
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You step into the shorts before pulling them up to your waist.";
			else
				return UtilText.genderParsing(clothingOwner,
						Util.capitaliseSentence(clothingOwner.getName("the")) + " steps into " + clothing.getName(true) + " before pulling them up to <her> waist.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your shorts and kick them off your feet.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down <her> shorts, kicking them off <her> feet.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your shorts down and slides them off your feet.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s shorts down and slide them off <her> feet.");
			}
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull down your shorts.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls down <her> shorts.");
			else {
				if (clothingOwner.isPlayer())
					return Util.capitaliseSentence(clothingRemover.getName("the")) + " pulls your shorts down.";
				else
					return UtilText.genderParsing(clothingOwner, "You pull " + clothingOwner.getName("the") + "'s shorts down.");
			}
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "You pull your shorts back up.";
			else
				return UtilText.genderParsing(clothingOwner, Util.capitaliseSentence(clothingOwner.getName("the")) + " pulls <her> shorts back up.");
		}
	};
	// ENFORCER_BOOTS("a pair of", true, "Enforcer's boots", "A pair of
	// thigh-high leather boots, they are made of black leather and are a part
	// of a Dominion enforcer's uniform.",
	// 1, null,
	// InventorySlot.FOOT, Rarity.EPIC, ClothingSet.ENFORCER,
	// "enforcerBoots",
	// Utilities.newHashMapOfValues(new Value<Attribute,
	// Integer>(Attribute.DAMAGE_PHYSICAL, 1)),
	//
	// Utilities.newHashMapOfValues(
	// new Value<DisplacementType, BlockedParts>(
	// DisplacementType.REMOVE_OR_EQUIP,
	// new BlockedParts(
	// null,
	// Utilities.newArrayListOfValues(
	// new ListValue<ClothingAccess>(ClothingAccess.FEET))
	// )
	// )
	// ),
	//
	// Utilities.newArrayListOfValues(
	// new ListValue<ClothingAccess>(ClothingAccess.FEET)
	// ),
	//
	// null,
	//
	// Utilities.newArrayListOfValues(new ListValue<Colour>(Colour.BLACK))) {
	// @Override
	// public String equipText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover, boolean rough, AbstractClothing
	// clothing, boolean applyEffects) {
	// if(clothingOwner.isPlayer() && clothingRemover.isPlayer())
	// return "You push your feet into the boots and fasten the buckle.";
	// else
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// Utilities.capitaliseSentence(clothingOwner.getNameWithDeterminer("the"))+"
	// pushes <her> feet into "+clothing.getName(true)+" and fastens the
	// buckle.");
	// }
	// @Override
	// public String unequipText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover, boolean rough, AbstractClothing
	// clothing, boolean applyEffects){
	// if(clothingOwner.isPlayer() && clothingRemover.isPlayer())
	// return "You unfasten the buckle on your boots and pull them off.";
	// else if(!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// Utilities.capitaliseSentence(clothingOwner.getNameWithDeterminer("the"))+"
	// unfastens <her> boot's buckle and pulls them off.");
	// else{
	// if(clothingOwner.isPlayer())
	// return
	// Utilities.capitaliseSentence(clothingRemover.getNameWithDeterminer("the"))+"
	// unfastens your boot's buckle and pulls them off.";
	// else
	// return
	// GenericSentence.parseTextForGenderReplacement(clothingOwner,
	// "You unfasten the buckle on
	// "+clothingOwner.getNameWithDeterminer("the")+"'s boots and pull them
	// off.");
	// }
	// }
	// @Override
	// public String displaceText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover,
	// DisplacementType dt, boolean rough) {
	// return null;
	// }
	// @Override
	// public String replaceText(GameCharacter clothingOwner,
	// GameCharacter clothingRemover,
	// DisplacementType dt, boolean rough) {
	// return null;
	// }
	// };

	private static List<ClothingType> commonClothing, commonFemaleClothing, commonMaleClothing, commonAndrogynousClothing,
										commonFemaleLingerie, commonMaleLingerie, commonAndrogynousLingerie,
										commonFemaleAccessories, commonMaleAccessories, commonAndrogynousAccessories,
										commonJewellery, commonFemaleJewellery, commonMaleJewellery, commonAndrogynousJewellery;
	
	private static List<InventorySlot> coreClothingSlots, lingerieSlots;
	
	private static Map<InventorySlot, List<ClothingType>>commonClothingMap,
															commonClothingMapFemale,
															commonClothingMapMale,
															commonClothingMapAndrogynous,
															commonClothingMapFemaleIncludingAndrogynous,
															commonClothingMapMaleIncludingAndrogynous;

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
		
		for(ClothingType ct : ClothingType.values()) {
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
		}
		
		coreClothingSlots = Util.newArrayListOfValues(new ListValue<>(InventorySlot.TORSO), new ListValue<>(InventorySlot.LEG));
		lingerieSlots = Util.newArrayListOfValues(new ListValue<>(InventorySlot.CHEST), new ListValue<>(InventorySlot.GROIN), new ListValue<>(InventorySlot.STOMACH), new ListValue<>(InventorySlot.SOCK));

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

		for (ClothingType c : ClothingType.values())
			if(!c.getSlot().isJewellery()){
				if (c.getRarity() == Rarity.COMMON) {
					commonClothing.add(c);
					if (c.getFemininityRestriction() == null) {
						if(coreClothingSlots.contains(c.getSlot())) {
							commonAndrogynousClothing.add(c);
						} else if(lingerieSlots.contains(c.getSlot())) {
							commonAndrogynousLingerie.add(c);
						} else {
							commonAndrogynousAccessories.add(c);
						}
						
					} else if (c.getFemininityRestriction() == Femininity.FEMININE) {
						if(coreClothingSlots.contains(c.getSlot())) {
							commonFemaleClothing.add(c);
						} else if(lingerieSlots.contains(c.getSlot())) {
							commonFemaleLingerie.add(c);
						} else {
							commonFemaleAccessories.add(c);
						}
						
					} else if (c.getFemininityRestriction() == Femininity.MASCULINE) {
						if(coreClothingSlots.contains(c.getSlot())) {
							commonMaleClothing.add(c);
						} else if(lingerieSlots.contains(c.getSlot())) {
							commonMaleLingerie.add(c);
						} else {
							commonMaleAccessories.add(c);
						}
					}
				}
			}else{
				if (c.getRarity() == Rarity.COMMON) {
					commonJewellery.add(c);
					if (c.getFemininityRestriction() == null)
						commonAndrogynousJewellery.add(c);
					else if (c.getFemininityRestriction() == Femininity.FEMININE)
						commonFemaleJewellery.add(c);
					else if (c.getFemininityRestriction() == Femininity.MASCULINE)
						commonMaleJewellery.add(c);
				}
			}

	}

	private String determiner, name, description, pathName;

	private boolean plural;
	private int physicalResistance, femininityMinimum, femininityMaximum;
	private Femininity femininityRestriction;
	private InventorySlot slot;

	// Access and block stuff:
	private List<BlockedParts> blockedPartsList;
	private List<InventorySlot> incompatibleSlots;

	private Map<Attribute, Integer> attributeModifiers;

	private Map<Colour, String> SVGStringMap;

	private ClothingSet clothingSet;
	private Rarity rarity;
	private List<Colour> availableColours;

	private List<DisplacementType> displacementTypesAvailableWithoutNONE;

	private ClothingType(String determiner, boolean plural, String name, String description, int physicalResistance, Femininity femininityRestriction, InventorySlot slot, Rarity rarity, ClothingSet clothingSet, String pathName,
			Map<Attribute, Integer> attributeModifiers,

			List<BlockedParts> blockedPartsList, List<InventorySlot> incompatibleSlots,

			List<Colour> availableColours) {

		this.determiner = determiner;
		this.plural = plural;
		this.name = name;
		this.description = description;

		this.physicalResistance = physicalResistance;

		this.femininityRestriction = femininityRestriction;

		if (femininityRestriction == null) {
			femininityMinimum = 0;
			femininityMaximum = 100;
		} else if (femininityRestriction == Femininity.FEMININE) {
			femininityMinimum = Femininity.ANDROGYNOUS.getMinimumFemininity();
			femininityMaximum = 100;
		} else if (femininityRestriction == Femininity.ANDROGYNOUS) {
			femininityMinimum = Femininity.ANDROGYNOUS.getMinimumFemininity();
			femininityMaximum = Femininity.ANDROGYNOUS.getMaximumFemininity();
		} else if (femininityRestriction == Femininity.MASCULINE) {
			femininityMinimum = 0;
			femininityMaximum = Femininity.ANDROGYNOUS.getMaximumFemininity();
		}

		this.slot = slot;
		this.rarity = rarity;
		this.clothingSet = clothingSet;

		this.pathName = pathName;

		// Attribute modifiers:
		if (attributeModifiers != null)
			this.attributeModifiers = attributeModifiers;
		else
			this.attributeModifiers = new EnumMap<>(Attribute.class);

		// Blocked Parts:
		if (blockedPartsList != null)
			this.blockedPartsList = blockedPartsList;
		else
			this.blockedPartsList = new ArrayList<>();

		// Incompatible slots:
		if (incompatibleSlots != null)
			this.incompatibleSlots = incompatibleSlots;
		else
			this.incompatibleSlots = new ArrayList<>();

		this.availableColours = new ArrayList<>();
		if (availableColours == null) {
			this.availableColours.add(Colour.CLOTHING_BLACK);
		} else {
			this.availableColours.addAll(availableColours);
		}

		SVGStringMap = new EnumMap<>(Colour.class);

		displacementTypesAvailableWithoutNONE = new ArrayList<>();
		for (BlockedParts bp : blockedPartsList)
			if (bp.displacementType != DisplacementType.REMOVE_OR_EQUIP)
				displacementTypesAvailableWithoutNONE.add(bp.displacementType);
		Collections.sort(displacementTypesAvailableWithoutNONE);
	}
	public static AbstractClothing generateClothing(ClothingType clothingType, Colour colourShade, boolean allowRandomEnchantment) {
		Colour c = colourShade;

		if (clothingType.getAvailableColours() != null) {
			if (!clothingType.getAvailableColours().contains(colourShade)) {
				c = clothingType.getAvailableColours().get(Util.random.nextInt(clothingType.getAvailableColours().size()));
			}
		}
		
		return new AbstractClothing(clothingType, c, allowRandomEnchantment) {
			private static final long serialVersionUID = 1L;
		};
	}

	/**
	 * Allows random enchantment. Uses random colour.
	 */
	public static AbstractClothing generateClothing(ClothingType clothingType) {
		return ClothingType.generateClothing(clothingType, clothingType.getAvailableColours().get(Util.random.nextInt(clothingType.getAvailableColours().size())), true);
	}

	/**
	 * Uses random colour.
	 */
	public static AbstractClothing generateClothing(ClothingType clothingType, boolean allowRandomEnchantment) {
		return ClothingType.generateClothing(clothingType, clothingType.getAvailableColours().get(Util.random.nextInt(clothingType.getAvailableColours().size())), allowRandomEnchantment);
	}
	
	/**
	 * Generates clothing with the provided enchantments.
	 */
	public static AbstractClothing generateClothing(ClothingType clothingType, Colour colour, Map<Attribute, Integer> enchantmentMap) {
		return new AbstractClothing(clothingType, colour, enchantmentMap) {
			private static final long serialVersionUID = 1L;
		};
	}
	/**
	 * Uses random colour.
	 */
	public static AbstractClothing generateClothing(ClothingType clothingType, Map<Attribute, Integer> enchantmentMap) {
		return ClothingType.generateClothing(clothingType, clothingType.getAvailableColours().get(Util.random.nextInt(clothingType.getAvailableColours().size())), enchantmentMap);
	}
	
	/**
	 * Generates clothing with a random enchantment.
	 */
	public static AbstractClothing generateClothingWithEnchantment(ClothingType clothingType, Colour colour) {
		Map<Attribute, Integer> enchantments = new EnumMap<>(Attribute.class);
		Attribute rndAtt = Attribute.attributeBonusesForEnchanting.get(Util.random.nextInt(Attribute.attributeBonusesForEnchanting.size()));
		
		enchantments.put(rndAtt, Util.random.nextInt(5)+1);
		
		return new AbstractClothing(clothingType, colour, enchantments) {
			private static final long serialVersionUID = 1L;
		};
	}
	/**
	 * Uses random colour.
	 */
	public static AbstractClothing generateClothingWithEnchantment(ClothingType clothingType) {
		return ClothingType.generateClothingWithEnchantment(clothingType, clothingType.getAvailableColours().get(Util.random.nextInt(clothingType.getAvailableColours().size())));
	}

	static Map<ClothingSet, List<ClothingType>> clothingSetMap = new EnumMap<>(ClothingSet.class);

	public static List<ClothingType> getClothingInSet(ClothingSet set) {
		if (clothingSetMap.get(set) != null)
			return clothingSetMap.get(set);

		List<ClothingType> setOfClothing = new ArrayList<>();

		for (ClothingType c : ClothingType.values())
			if (c.getClothingSet() == set)
				setOfClothing.add(c);

		clothingSetMap.put(set, setOfClothing);

		return setOfClothing;
	}

	public abstract String equipText(GameCharacter clothingOwner, GameCharacter clothingEquipper, boolean rough, AbstractClothing clothing, boolean applyEffects);

	public abstract String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, boolean rough, AbstractClothing clothing, boolean applyEffects);

	public abstract String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough);

	public abstract String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, DisplacementType dt, boolean rough);

	// /**
	// * Calculates if this clothing type can be worn by the supplied character.
	// Takes into account character's racial body parts.</br>
	// * e.g. Gloves cannot be worn with harpy wings, and shoes can't be worn
	// with horse legs.
	// * @return True if this clothing can be worn.
	// */
	// public boolean isCanBeWornBy(GameCharacter character){
	// if(character.getLegType()==LegType.HORSE_MORPH &&
	// slot==InventorySlot.FOOT)
	// return false;
	// else
	// return true;
	// }
	/**
	 * Calculates if the character cannot wear clothing in the provided slot due
	 * to his or her race. If a part of their body is preventing the clothing
	 * from being equipped, this method returns the race of that body part.</br>
	 * e.g. Horse legs block FOOT slot, so passing in a character who has horse
	 * legs and InventorySlot.FOOT will return Race.HORSE_MORPH.</br>
	 * Returns null if all is ok. (I don't care if you're not meant to return
	 * null as a check, I'M DOING IT ANYWAY)
	 * 
	 * @param character
	 * @param slot
	 * @return Race which is blocking this slot. Returns null if nothing is
	 *         blocking the slot.
	 */
	public static Race slotBlockedByRace(GameCharacter character, InventorySlot slot) {
		if (character == null) {
			return null;
		}
		
		if (character.getLegType() == LegType.HORSE_MORPH && slot == InventorySlot.FOOT) {
			return Race.HORSE_MORPH;
		}
		
		if (character.getLegType() == LegType.HARPY && slot == InventorySlot.FOOT) {
			return Race.HARPY;
		}
		
		if (character.getArmType() == ArmType.HARPY && (slot == InventorySlot.HAND || slot == InventorySlot.FINGER)) {
			return Race.HARPY;
		}
		
		return null;
	}

	/**
	 * Complimentary method to slotBlockedByRace(GameCharacter character,
	 * InventorySlot slot).
	 * 
	 * @param character
	 * @param slot
	 * @return A description of why this slot can't be used.
	 */
	public static String getCannotBeWornDescription(GameCharacter character, InventorySlot slot) {
		if (character.getLegType() == LegType.HORSE_MORPH && slot == InventorySlot.FOOT) {
			if(character.isPlayer())
				return "Your horse-like hooves prevent you from wearing footwear of any kind!";
			else
				return UtilText.parse(character,
						"[npc.Name]'s horse-like hooves prevent [npc.her] from wearing footwear of any kind!");
		}
		
		if (character.getLegType() == LegType.HARPY && slot == InventorySlot.FOOT) {
			if(character.isPlayer())
				return "Your bird-like talons prevent you from wearing footwear of any kind!";
			else
				return UtilText.parse(character,
						"[npc.Name]'s bird-like talons prevent [npc.her] from wearing footwear of any kind!");
		}
		
		if (character.getArmType() == ArmType.HARPY && slot == InventorySlot.HAND) {
			if(character.isPlayer())
				return "You can't fit anything onto your harpy wings!";
			else
				return UtilText.parse(character,
						"[npc.Name] can't fit anything onto [npc.her] harpy wings!");
		}
		
		if (character.getArmType() == ArmType.HARPY && slot == InventorySlot.FINGER) {
			if(character.isPlayer())
				return "You only have a single thumb in the middle of your harpy wings, so you can't wear anything that would require fingers!";
			else
				return UtilText.parse(character,
						"[npc.Name] doesn't have any fingers on [npc.her] harpy wings!");
		}
		
		return null;
	}

	/**
	 * @return True if this clothing is blocking the CoverableArea from sight.
	 */
	public boolean isBlocksFromSight() {
		return true;
	}

	// Getters & setters:

	public String getDeterminer() {
		return determiner;
	}

	public Boolean isPlural() {
		return plural;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getFemininityMinimum() {
		return femininityMinimum;
	}

	public int getFemininityMaximum() {
		return femininityMaximum;
	}

	public Femininity getFemininityRestriction() {
		return femininityRestriction;
	}

	public InventorySlot getSlot() {
		return slot;
	}

	public List<BlockedParts> getBlockedPartsList() {
		return blockedPartsList;
	}

	public List<InventorySlot> getIncompatibleSlots() {
		return incompatibleSlots;
	}

	// public Map<DisplacementType, BlockedParts> getBlockedPartsMap() {return
	// blockedPartsMap;}
	//
	//
	public List<DisplacementType> getBlockedPartsKeysAsListWithoutNONE() {
		return displacementTypesAvailableWithoutNONE;
	}
	//
	// //public Map<InventorySlot, Boolean> getNeedAccessWhenEquipping() {return
	// needAccessWhenEquipping;}
	//
	// public List<ClothingAccess> getClothingAccessRequired() {
	// return clothingAccessRequired;
	// }

	public String getPathName() {
		return pathName;
	}

	public int getzLayer() {
		return slot.getZLayer();
	}

	public ClothingSet getClothingSet() {
		return clothingSet;
	}

	public List<Colour> getAvailableColours() {
		return availableColours;
	}

	public Map<Colour, String> getSVGStringMap() {
		return SVGStringMap;
	}

	public String getSVGImage(Colour colour) {
		if (!availableColours.contains(colour))
			return "";

		if (SVGStringMap.containsKey(colour) && this != ClothingType.WRIST_WOMENS_WATCH && this != ClothingType.WRIST_MENS_WATCH)
			return SVGStringMap.get(colour);
		else {
			if (availableColours.contains(colour)) {
				try {
					InputStream is = this.getClass().getResourceAsStream("/com/base/res/clothing/" + pathName + ".svg");
					String s = Util.inputStreamToString(is);

					for (int i = 0; i <= 14; i++)
						s = s.replaceAll("linearGradient" + i, this + colour.toString() + "linearGradient" + i);
					s = s.replaceAll("#ff2a2a", colour.getShades()[0]);
					s = s.replaceAll("#ff5555", colour.getShades()[1]);
					s = s.replaceAll("#ff8080", colour.getShades()[2]);
					s = s.replaceAll("#ffaaaa", colour.getShades()[3]);
					s = s.replaceAll("#ffd5d5", colour.getShades()[4]);

					// Add minute and hour hands to women's and men's watches:
					s += (this == ClothingType.WRIST_WOMENS_WATCH ? "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;-webkit-transform: rotate(" + ((Main.game.getMinutesPassed() % (60 * 24)) / 2f) + "deg);'>"
							+ SVGImages.SVG_IMAGE_PROVIDER.getWomensWatchHourHand() + "</div>" + "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;-webkit-transform: rotate(" + (Main.game.getMinutesPassed() % (60)) * 6 + "deg);'>"
							+ SVGImages.SVG_IMAGE_PROVIDER.getWomensWatchMinuteHand() + "</div>" : "")
							+ (this == ClothingType.WRIST_MENS_WATCH ? "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;-webkit-transform: rotate(" + ((Main.game.getMinutesPassed() % (60 * 24)) / 2f) + "deg);'>"
									+ SVGImages.SVG_IMAGE_PROVIDER.getMensWatchHourHand() + "</div>" + "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;-webkit-transform: rotate(" + (Main.game.getMinutesPassed() % (60)) * 6
									+ "deg);'>" + SVGImages.SVG_IMAGE_PROVIDER.getMensWatchMinuteHand() + "</div>" : "");

					SVGStringMap.put(colour, s);

					is.close();

					return s;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return "";
	}

	public Rarity getRarity() {
		return rarity;
	}

	public Map<Attribute, Integer> getAttributeModifiers() {
		return attributeModifiers;
	}

	public int getPhysicalResistance() {
		return physicalResistance;
	}

	public void setPhysicalResistance(int physicalResistance) {
		this.physicalResistance = physicalResistance;
	}

	public static List<ClothingType> getCommonClothing() {
		return commonClothing;
	}

	public static List<ClothingType> getCommonFemaleClothing() {
		return commonFemaleClothing;
	}

	public static List<ClothingType> getCommonMaleClothing() {
		return commonMaleClothing;
	}

	public static List<ClothingType> getCommonAndrogynousClothing() {
		return commonAndrogynousClothing;
	}
	
	public static List<ClothingType> getCommonJewellery() {
		return commonJewellery;
	}

	public static List<ClothingType> getCommonFemaleJewellery() {
		return commonFemaleJewellery;
	}

	public static List<ClothingType> getCommonMaleJewellery() {
		return commonMaleJewellery;
	}

	public static List<ClothingType> getCommonAndrogynousJewellery() {
		return commonAndrogynousJewellery;
	}

	public static List<InventorySlot> getCoreClothingSlots() {
		return coreClothingSlots;
	}

	public static List<InventorySlot> getLingerieSlots() {
		return lingerieSlots;
	}

	public static List<ClothingType> getCommonFemaleLingerie() {
		return commonFemaleLingerie;
	}

	public static List<ClothingType> getCommonMaleLingerie() {
		return commonMaleLingerie;
	}

	public static List<ClothingType> getCommonAndrogynousLingerie() {
		return commonAndrogynousLingerie;
	}

	public static List<ClothingType> getCommonFemaleAccessories() {
		return commonFemaleAccessories;
	}

	public static List<ClothingType> getCommonMaleAccessories() {
		return commonMaleAccessories;
	}

	public static List<ClothingType> getCommonAndrogynousAccessories() {
		return commonAndrogynousAccessories;
	}

	public static Map<InventorySlot, List<ClothingType>> getCommonClothingMap() {
		return commonClothingMap;
	}

	public static Map<InventorySlot, List<ClothingType>> getCommonClothingMapFemale() {
		return commonClothingMapFemale;
	}

	public static Map<InventorySlot, List<ClothingType>> getCommonClothingMapMale() {
		return commonClothingMapMale;
	}

	public static Map<InventorySlot, List<ClothingType>> getCommonClothingMapAndrogynous() {
		return commonClothingMapAndrogynous;
	}
	
	public static Map<InventorySlot, List<ClothingType>> getCommonClothingMapFemaleIncludingAndrogynous() {
		return commonClothingMapFemaleIncludingAndrogynous;
	}
	
	public static Map<InventorySlot, List<ClothingType>> getCommonClothingMapMaleIncludingAndrogynous() {
		return commonClothingMapMaleIncludingAndrogynous;
	}

}
