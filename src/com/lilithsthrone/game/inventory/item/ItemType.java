package com.lilithsthrone.game.inventory.item;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectTimer;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.84
 * @version 0.3.1
 * @author Innoxia
 */
public class ItemType {

	/*
	 * Common: Restore resources Rare: Apply status effect Epic: Transformation
	 * Legendary: Uniques
	 */

	// EPIC:

	// Lilith's consumables:
	// LILITHS_DESIRE("a bottle of", "it", "Lilith's Desire",
	// "A glass bottle, filled with bubbling pink liquid."
	// + " On the bottle's label, there is an image of Lilith's
	// perfectly-formed, heart-shaped ass."
	// + " Her thin, demonic tail twists round to playfully cover her asshole
	// and pussy.",
	// "potion", Colour.PINK,
	// true, 25, Rarity.EPIC,
	// TransformationEffect.SEXUAL_BOON_AVERAGE.getDescription()) {
	// @Override
	// protected String extraEffects(GameCharacter user, GameCharacter
	// target) {
	// return applyLilithsTransformation(user, target, this,
	// TransformationEffect.SEXUAL_BOON_AVERAGE);
	// }
	//
	// @Override
	// public int chanceToSucceedOnTargetUse(GameCharacter target) {
	// return 50;
	// }
	//
	// @Override
	// public String getUseDescription(GameCharacter user, GameCharacter target){
	// return "drink";
	// }
	//
	// @Override
	// public boolean isAbleToBeUsedInCombat() {
	// return false;
	// }
	//
	// @Override
	// public boolean isAbleToBeUsedInSex() {
	// return false;
	// }
	// },
	// LILITHS_OFFERING("a bottle of", "it", "Lilith's Offering",
	// "A glass bottle, filled with bubbling pink liquid."
	// + " On the bottle's label, there is an image of Lilith's
	// perfectly-formed, heart-shaped ass."
	// + " Her delicate hands are reaching down to pull apart her soft ass
	// cheeks, although her asshole and pussy are covered by her thin, demonic
	// tail.",
	// "potion", Colour.PINK,
	// true, 25, Rarity.EPIC,
	// TransformationEffect.SEXUAL_BOON_STRONG.getDescription()) {
	// @Override
	// protected String extraEffects(GameCharacter user, GameCharacter
	// target) {
	// return applyLilithsTransformation(user, target, this,
	// TransformationEffect.SEXUAL_BOON_STRONG);
	// }
	//
	// @Override
	// public int chanceToSucceedOnTargetUse(GameCharacter target) {
	// return 10;
	// }
	//
	// @Override
	// public String getUseDescription(GameCharacter user, GameCharacter target){
	// return "drink";
	// }
	//
	// @Override
	// public boolean isAbleToBeUsedInCombat() {
	// return false;
	// }
	//
	// @Override
	// public boolean isAbleToBeUsedInSex() {
	// return false;
	// }
	// },
	
	//
	// // Lilin's consumables:
	// // Lyxias' dream: Increases breast size and lactation, cum production
	// LYXIAS_DREAM("a vial of", "it", "Lyxias' dream", "A small glass vial,
	// filled with a thick, cream-coloured liquid. Engraved into the glass,
	// there is a finely-detailed image of the"
	// + " scantily-clad tentacle-girl, Lyxia. She is stuffing her tentacles
	// into the orifices of a group of cow-girls, violently milking their
	// swollen breasts while she fucks them.",
	// "potion", Colour.WHITE,
	// true, 25, Rarity.EPIC,
	// TransformationEffect.LYXIAS_DREAM.getDescription()) {
	// @Override
	// protected String extraEffects(GameCharacter user, GameCharacter
	// target) {
	// return applyLilithsTransformation(user, target, this,
	// TransformationEffect.LYXIAS_DREAM);
	// }
	//
	// @Override
	// public int chanceToSucceedOnTargetUse(GameCharacter target) {
	// return 10;
	// }
	//
	// @Override
	// public String getUseDescription(GameCharacter user, GameCharacter target){
	// return "drink";
	// }
	//
	// @Override
	// public boolean isAbleToBeUsedInCombat() {
	// return false;
	// }
	//
	// @Override
	// public boolean isAbleToBeUsedInSex() {
	// return false;
	// }
	// },
	// // Lunette's need: Increases capacity & wetness of everything, making
	// breasts & cock fuckable.
	// LUNETTES_NEED("a vial of", "it", "Lunette's need", "A small glass vial,
	// filled with an oily liquid. Engraved into the glass is an image of
	// Lunette; a muscular futa pegataur. She's"
	// + " laughing as she aggressively mounts a busty horse-girl. Lunette's
	// latest conquest is being pushed into the ground, her stomach visibly
	// distending from Lunette's gigantic horse-cock.",
	// "potion", Colour.WHITE,
	// true, 25, Rarity.EPIC,
	// TransformationEffect.LUNETTES_NEED.getDescription()) {
	// @Override
	// protected String extraEffects(GameCharacter user, GameCharacter
	// target) {
	// return applyLilithsTransformation(user, target, this,
	// TransformationEffect.LUNETTES_NEED);
	// }
	//
	// @Override
	// public int chanceToSucceedOnTargetUse(GameCharacter target) {
	// return 10;
	// }
	//
	// @Override
	// public String getUseDescription(GameCharacter user, GameCharacter target){
	// return "drink";
	// }
	//
	// @Override
	// public boolean isAbleToBeUsedInCombat() {
	// return false;
	// }
	//
	// @Override
	// public boolean isAbleToBeUsedInSex() {
	// return false;
	// }
	// },
	// // Lisophia's desire: Increases cock & ball size. Grows second cock.
	// Increases cum production.
	// LISOPHIAS_DESIRE("a vial of", "it", "Lisophia's desire", "A small glass
	// vial, filled with a bubbling blue liquid. Engraved into the glass, there
	// is an image of Lisophia; an exotic-looking"
	// + " Lamia. She's forcing a pair of stallion-sized reptilian cocks deep
	// into the pussy and asshole within her slit-like cloaca, her hands choking
	// her unfortunate partner as she aggressively rides him.",
	// "potion", Colour.BLUE,
	// true, 25, Rarity.EPIC,
	// TransformationEffect.LISOPHIAS_DESIRE.getDescription()) {
	// @Override
	// protected String extraEffects(GameCharacter user, GameCharacter
	// target) {
	// return applyLilithsTransformation(user, target, this,
	// TransformationEffect.LISOPHIAS_DESIRE);
	// }
	//
	// @Override
	// public int chanceToSucceedOnTargetUse(GameCharacter target) {
	// return 10;
	// }
	//
	// @Override
	// public String getUseDescription(GameCharacter user, GameCharacter target){
	// return "drink";
	// }
	//
	// @Override
	// public boolean isAbleToBeUsedInCombat() {
	// return false;
	// }
	//
	// @Override
	// public boolean isAbleToBeUsedInSex() {
	// return false;
	// }
	// },
	// // Lirecea's demand: Hyper-feminine, growing hips, breasts, tightens
	// pussy, makes wet, shrinks cocks.
	// LIRECEAS_DEMAND("a vial of", "it", "Lirecea's demand", "A small glass
	// vial, filled with a bubbling pink liquid. Engraved into the glass, the
	// gigantic octopus-girl, Lirecea,"
	// + " is looking down with a sinister smile on her face. Her tentacles are
	// violently penetrating the seven beautiful mermaids beneath her, while her
	// free tentacle is slipping inside her own needy pussy.",
	// "potion", Colour.PINK,
	// true, 25, Rarity.EPIC,
	// TransformationEffect.LIRECEAS_DEMAND.getDescription()) {
	// @Override
	// protected String extraEffects(GameCharacter user, GameCharacter
	// target) {
	// return applyLilithsTransformation(user, target, this,
	// TransformationEffect.LIRECEAS_DEMAND);
	// }
	//
	// @Override
	// public int chanceToSucceedOnTargetUse(GameCharacter target) {
	// return 10;
	// }
	//
	// @Override
	// public String getUseDescription(GameCharacter user, GameCharacter target){
	// return "drink";
	// }
	//
	// @Override
	// public boolean isAbleToBeUsedInCombat() {
	// return false;
	// }
	//
	// @Override
	// public boolean isAbleToBeUsedInSex() {
	// return false;
	// }
	// },
	
	private static String getGenericUseDescription(GameCharacter user, GameCharacter target, String playerUseSelf, String playerUsePartner, String partnerUseSelf, String partnerUsePlayer) {
		if (user!=null && user.isPlayer()) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "<p>"+playerUseSelf+"</p>";
					
				} else {
					return UtilText.parse(target, "<p>"+playerUsePartner+"</p>");
				}
			} else {
				return "";
			}
			
		} else {
			if(target!=null) {
				if(target.isPlayer()) {
					return UtilText.parse(user, "<p>"+partnerUsePlayer+"</p>");
					
				} else {
					return UtilText.parse(user, "<p>"+partnerUseSelf+"</p>");
				}
			} else {
				return "";
			}
		}
	}
	
	// Crafting:
	
	// Strength ingredients are beer-type alcohol:
	
	public static AbstractItemType STR_INGREDIENT_EQUINE_CIDER = new AbstractItemType(60,
			"a bottle of",
			false,
			"Equine Cider",
			"Equine Ciders",
			"The thick glass bottle of 'Equine Cider' appears to contain, much as its name would suggest, a generous helping of some sort of alcoholic cider."
				+ " On the label, there's an incredibly lewd illustration of a horse-boy slamming his massive cock deep into a girl's eager pussy.",
			"attributeHorseMorphDrink",
			Colour.ATTRIBUTE_PHYSIQUE,
			null,
			null,
			Rarity.UNCOMMON,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.STR_EQUINE_CIDER)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.ATTRIBUTE_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_PHYSIQUE;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return ItemType.POTION;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Unscrewing the lid, you bring the bottle of 'Equine Cider' to your lips before tilting your head back and quickly gulping down the golden liquid."
						+ " As the last few drops slide down your throat, you notice a faint, musky dryness permeating through the sweet liquid, which lingers for some time as a slightly unpleasant aftertaste.",
					"Unscrewing the lid, you bring the bottle of 'Equine Cider' to [npc.namePos] lips, before tilting [npc.her] head back and forcing [npc.herHim] to quickly gulp down the golden liquid.",
					"[npc.Name] pulls out a bottle of 'Equine Cider', and, after quickly unscrewing the cap, [npc.she] promptly downs the entire bottle.",
					"[npc.Name] pulls out a bottle of 'Equine Cider', and, after quickly unscrewing the cap, [npc.she] brings it to your lips before tilting your head back and forcing you to quickly gulp down the golden liquid."
						+ " As the last few drops slide down your throat, you notice a faint, musky dryness permeating through the sweet liquid, which lingers for some time as a slightly unpleasant aftertaste.");
		}
	};
	
	public static AbstractItemType STR_INGREDIENT_BUBBLE_MILK = new AbstractItemType(20,
			"a bottle of",
			false,
			"Bubble Milk",
			"Bubble Milks",
			"The thick glass bottle of 'Bubble Milk' appears to contain, much as its name would suggest, a generous helping of milk."
				+ " Looking through the glass, you see that there are little bubbles fizzing up in the liquid within, making this milk appear to be carbonated.",
			"attributeCowMorphDrink",
			Colour.ATTRIBUTE_PHYSIQUE,
			null,
			null,
			Rarity.UNCOMMON,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.STR_BUBBLE_MILK)), 
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.ATTRIBUTE_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_PHYSIQUE;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Unscrewing the cap, you bring the bottle of Bubble Milk up to your [pc.lips+], before tilting your head back and quickly gulping down the creamy liquid."
						+ " Despite its name and the appearance of being carbonated, the mellow taste lacks any sort of fizz, and, after draining the entire bottle, a soft, pleasant aftertaste lingers in your mouth.",
					"Unscrewing the cap, you bring the bottle of 'Bubble Milk' to [npc.namePos] lips, before tilting [npc.her] head back and forcing [npc.herHim] to quickly gulp down the creamy liquid.",
					"[npc.Name] pulls out a bottle of 'Bubble Milk', and, after quickly unscrewing the cap, [npc.she] promptly downs the entire bottle.",
					"[npc.Name] pulls out a bottle of 'Bubble Milk', and, after quickly unscrewing the cap, [npc.she] brings it to your lips before tilting your head back and forcing you to quickly gulp down the creamy liquid."
						+ " Despite its name and the appearance of being carbonated, the mellow taste lacks any sort of fizz, and, after draining the entire bottle, a soft, pleasant aftertaste lingers in your mouth.");
		}
	};
	
	public static AbstractItemType STR_INGREDIENT_WOLF_WHISKEY = new AbstractItemType(120,
			"a bottle of",
			false,
			"Wolf Whiskey",
			"Wolf Whiskies",
			"Filled with a strong, alcoholic whiskey, this glass bottle has a label on the front which depicts a greater wolf-boy having sex with a trio of female humans."
					+ " A slogan written above this reads: 'Wolf Whiskey; For a real alpha!'",
			"attributeWolfMorphDrink",
			Colour.ATTRIBUTE_PHYSIQUE,
			null,
			null,
			Rarity.UNCOMMON,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.STR_WOLF_WHISKEY)), 
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.ATTRIBUTE_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_PHYSIQUE;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Popping off the cap, you bring the bottle of 'Wolf Whiskey' up to your lips."
						+ " A thick, musky scent rises from the opening, and with a gulp, you start downing the liquid, discovering that the liquid's taste is almost identical to its pungent aroma.",
					"Popping off the cap, you bring the bottle of 'Wolf Whiskey' to [npc.namePos] lips, before tilting [npc.her] head back and forcing [npc.herHim] to quickly gulp down the alcoholic liquid.",
					"[npc.Name] pulls out a bottle of 'Wolf Whiskey', and, after quickly popping off the cap, [npc.she] promptly downs the entire bottle.",
					"[npc.Name] pulls out a bottle of 'Wolf Whiskey', and, after quickly unscrewing the cap, [npc.she] brings it to your lips before tilting your head back and forcing you to quickly gulp down the alcoholic liquid."
						+ " You soon discover that the musky, pungent aroma rising from the bottle's opening is almost identical to the whiskey's rather unpleasant taste.");
		}
	};
	
	public static AbstractItemType STR_INGREDIENT_SWAMP_WATER = new AbstractItemType(40,
			"a bottle of",
			false,
			"Swamp Water",
			"Swamp Waters",
			"A glass bottle of what appears to be some kind of moonshine."
				+ " A label on the front shows an alligator-boy biting the top off a bottle just like this one.",
			"attributeGatorMorphDrink",
			Colour.ATTRIBUTE_PHYSIQUE,
			null,
			null,
			Rarity.UNCOMMON,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.STR_SWAMP_WATER)), 
			Util.newArrayListOfValues(
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.ATTRIBUTE_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {

		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_PHYSIQUE;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Pulling out the stopper, you take a large swig of 'Swamp Water'."
						+ " Thankfully, the liquid within isn't a literal version of its label, and turns out to be a strong liquor, which burns your throat a little as you gulp it down."
						+ " The intense alcoholic taste is very different to anything you've ever tried before, and you can't help but greedily gulp down the entire bottle, leaving a strange, tangy aftertaste lingering on your tongue.",
					"Pulling out the stopper, you bring the bottle of 'Swamp Water' to [npc.namePos] lips, before tilting [npc.her] head back and forcing [npc.herHim] to quickly gulp down the alcoholic liquid.",
					"[npc.Name] pulls out a bottle of 'Swamp Water', and, after quickly pulling out the stopper, [npc.she] promptly gulps downs the entire bottle.",
					"[npc.Name] pulls out a bottle of 'Swamp Water', and, after quickly pulling out the stopper, [npc.she] brings it to your lips before tilting your head back and forcing you to quickly gulp down the contents."
						+ " Thankfully, the liquid within isn't a literal version of its label, and turns out to be a strong liquor, which burns your throat a little as you gulp it down."
						+ " The intense alcoholic taste is very different to anything you've ever tried before, and you can't help but greedily gulp down the entire bottle, leaving a strange, tangy aftertaste lingering on your tongue.");
		}
	};
	
	public static AbstractItemType STR_INGREDIENT_BLACK_RATS_RUM = new AbstractItemType(200,
			"a bottle of",
			false,
			"Black Rat's Rum",
			"Black Rat's Rums",
			"A glass bottle of 'Black Rat's Rum', filled with orange-coloured alcohol."
				+ " The label on the front shows an image of a black-furred rat-boy, wearing a thief's mask, pinning a rat-girl against a wall as he fucks her from behind.",
			"attributeRatMorphDrink",
			Colour.ATTRIBUTE_PHYSIQUE,
			null,
			null,
			Rarity.UNCOMMON,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.STR_BLACK_RATS_RUM)), 
			Util.newArrayListOfValues(
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.ATTRIBUTE_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_PHYSIQUE;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Unscrewing the lid, you bring the bottle of 'Black Rat's Rum' to your lips before tilting your head back and quickly gulping down the golden liquid."
						+ " As the last few drops slide down your throat, you notice a faint, musky dryness permeating through the alcoholic liquid, which lingers for some time as a slightly unpleasant aftertaste.",
					"Unscrewing the lid, you bring the bottle of 'Black Rat's Rum' to [npc.namePos] lips, before tilting [npc.her] head back and forcing [npc.herHim] to quickly gulp down the golden liquid.",
					"[npc.Name] pulls out a bottle of 'Black Rat's Rum', and, after quickly unscrewing the cap, [npc.she] promptly downs the entire bottle.",
					"[npc.Name] pulls out a bottle of 'Black Rat's Rum', and, after quickly unscrewing the cap, [npc.she] brings it to your lips before tilting your head back and forcing you to quickly gulp down the golden liquid."
						+ " As the last few drops slide down your throat, you notice a faint, musky dryness permeating through the alcoholic liquid, which lingers for some time as a slightly unpleasant aftertaste.");
		}
	};
	
	// Intelligence ingredients are cold non-alcoholic drinks:
	
	public static AbstractItemType INT_INGREDIENT_FELINE_FANCY = new AbstractItemType(150,
			"a bottle of",
			false,
			"Feline's Fancy",
			"Feline's Fancies",
			"A delicate glass bottle filled with a thick, cream-like liquid."
				+ " A label on the front shows a pair of cat-girls lovingly kissing one another, with the dominant partner slipping a hand down between her partner's legs.",
			"attributeCatMorphDrink",
			Colour.ATTRIBUTE_ARCANE,
			null,
			null,
			Rarity.UNCOMMON,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.INT_FELINE_FANCY)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.ATTRIBUTE_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_ARCANE;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Opening the bottle of 'Feline's Fancy', you eagerly bring it up to your waiting lips."
						+ " A rich, creamy smell rises from the opening, and as you greedily drink down the cool liquid, you're delighted to discover that it tastes every bit as good as its delicious aroma suggested it would.",
					"Unscrewing the cap, you bring the bottle of 'Feline's Fancy' to [npc.namePos] lips, before tilting [npc.her] head back and forcing [npc.herHim] to quickly gulp down the creamy liquid.",
					"[npc.Name] pulls out a bottle of 'Feline's Fancy', and, after quickly unscrewing the cap, [npc.she] promptly downs the entire bottle.",
					"[npc.Name] pulls out a bottle of 'Feline's Fancy', and, after quickly unscrewing the cap, [npc.she] brings it to your lips before tilting your head back and forcing you to quickly gulp down the contents."
						+ " A rich, creamy smell rises from the opening, and as you greedily drink down the cool liquid, you're delighted to discover that it tastes every bit as good as its delicious aroma suggested it would.");
		}
	};
	
	public static AbstractItemType INT_INGREDIENT_GRAPE_JUICE = new AbstractItemType(150,
			"a bottle of",
			false,
			"Vulpine's Vineyard",
			"Vulpine's Vineyards",
			"A delicate glass bottle filled with red wine."
				+ " A bunch of grapes is painted onto the front of the label, and on the bottom of the bottle itself, the image of a snickering fox-morph is burned into the glass.",
			"attributeFoxMorphDrink",
			Colour.ATTRIBUTE_ARCANE,
			null,
			null,
			Rarity.UNCOMMON,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.INT_GRAPE_JUICE)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.ATTRIBUTE_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_ARCANE;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Opening the bottle of 'Vulpine's Vineyard', you eagerly bring it up to your waiting lips."
						+ " The heady fragrance of rich red wine wafts from the neck of the newly opened bottle, soon joined by a rich, sweet taste, that lingers on your tongue well after you've had your fill.",
					"Removing the stopper from the bottle, you bring the bottle of 'Vulpine's Vineyard' to [npc.namePos] lips, before tilting [npc.her] head back and forcing [npc.herHim] to quickly gulp down the wine within.",
					"[npc.Name] pulls out a bottle of 'Vulpine's Vineyard', and, after removing the stopper, [npc.she] promptly downs the entire bottle.",
					"[npc.Name] pulls out a bottle of 'Vulpine's Vineyard', and, after removing the stopper, [npc.she] brings it to your lips before tilting your head back and forcing you to quickly gulp down the contents."
						+ " The heady fragrance of rich red wine wafts from the neck of the newly opened bottle, soon joined by a rich, sweet taste, that lingers on your tongue well after you've had your fill.");
		}
	};
	
	public static AbstractItemType INT_INGREDIENT_VANILLA_WATER = new AbstractItemType(10,
			"a bottle of",
			false,
			"Vanilla Water",
			"Vanilla Waters",
			"A plastic bottle filled with what appears to be nothing but water."
				+ " While there's no label on the bottle, there is a slight indentation in its surface, and, holding it up to the light to get a better look, you see that the impression spells the words 'Vanilla Water'.",
			"attributeHumanDrink",
			Colour.ATTRIBUTE_ARCANE,
			null,
			null,
			Rarity.UNCOMMON,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.INT_VANILLA_WATER)), 
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.BAT_CAVERNS_SPAWN,
					ItemTag.ATTRIBUTE_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_ARCANE;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"After first unscrewing the cap, you bring the plastic bottle of 'Vanilla Water' up to your [pc.mouth]."
						+ " A faint smell of vanilla informs you that this isn't any ordinary water, and as you tilt your head back and start drinking the cool liquid, the taste of vanilla overwhelms your senses.",
					"Unscrewing the cap, you bring the bottle of 'Vanilla Water' to [npc.namePos] lips, before tilting [npc.her] head back and forcing [npc.herHim] to quickly gulp down the liquid.",
					"[npc.Name] pulls out a bottle of 'Vanilla Water', and, after quickly unscrewing the cap, [npc.she] promptly downs the entire bottle.",
					"[npc.Name] pulls out a bottle of 'Vanilla Water', and, after quickly unscrewing the cap, [npc.she] brings it to your lips before tilting your head back and forcing you to quickly gulp down the contents."
						+ " A faint smell of vanilla informs you that this isn't any ordinary water, and as you tilt your head back and start drinking the cool liquid, the taste of vanilla overwhelms your senses.");
		}
	};
	
	// Fitness ingredients are energy drinks and coffee:
	
	public static AbstractItemType FIT_INGREDIENT_CANINE_CRUSH = new AbstractItemType(20,
			"a bottle of",
			false,
			"Canine Crush",
			"Canine Crushes",
			"A glass bottle of what looks to be some kind of beer."
				+ " A label on the front shows a dog-boy lining himself up behind a beautiful girl, who's down on all fours, presenting her naked, dripping pussy to the throbbing dog-cock behind her.",
			"attributeDogMorphDrink",
			Colour.ATTRIBUTE_PHYSIQUE,
			null,
			null,
			Rarity.UNCOMMON,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.FIT_CANINE_CRUSH)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.ATTRIBUTE_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_PHYSIQUE;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You pop off the cap and start drinking the bottle of 'Canine Crush'."
						+ " It doesn't taste anything like any other beer you've ever had, and it reminds you more of a sugary energy drink rather than any alcoholic beverage."
						+ " As the last few drops slide down your throat, a strange, musky aftertaste lingers on your tongue.",
					"Popping off the cap, you bring the bottle of 'Canine Crush' to [npc.namePos] lips, before tilting [npc.her] head back and forcing [npc.herHim] to quickly gulp down the liquid.",
					"[npc.Name] pulls out a bottle of 'Canine Crush', and, after quickly popping off the cap, [npc.she] promptly downs the entire bottle.",
					"[npc.Name] pulls out a bottle of 'Canine Crush', and, after quickly popping off the cap, [npc.she] brings it to your lips before tilting your head back and forcing you to quickly gulp down the contents."
						+ " It doesn't taste anything like any other beer you've ever had, and it reminds you more of a sugary energy drink rather than any alcoholic beverage."
							+ " As the last few drops slide down your throat, a strange, musky aftertaste lingers on your tongue.");
		}
	};
	
	public static AbstractItemType FIT_INGREDIENT_SQUIRREL_JAVA = new AbstractItemType(20,
			"a bottle of",
			false,
			"Squirrel Java",
			"Squirrel Javas",
			"A glass bottle of what looks to be some kind of coffee."
				+ " A label on the front shows a squirrel-girl fingering herself over the top of a bottle just like this one; her juices dripping down into the coffee to provide some extra cream.",
			"attributeSquirrelMorphDrink",
			Colour.ATTRIBUTE_PHYSIQUE,
			null,
			null,
			Rarity.UNCOMMON,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.FIT_SQUIRREL_JAVA)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.ATTRIBUTE_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_PHYSIQUE;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You unscrew the cap and start drinking the bottle of 'Squirrel Java'."
						+ " The taste is quite unlike that of any other coffee you've ever drunk, and it reminds you more of a sugary energy drink rather than any caffeinated beverage."
						+ " As the last few drops slide down your throat, a strange, sweet aftertaste lingers on your tongue.",
					"Unscrewing the cap, you bring the bottle of 'Squirrel Java' to [npc.namePos] lips, before tilting [npc.her] head back and forcing [npc.herHim] to quickly gulp down the liquid.",
					"[npc.Name] pulls out a bottle of 'Squirrel Java', and, after quickly unscrewing the cap, [npc.she] promptly downs the entire bottle.",
					"[npc.Name] pulls out a bottle of 'Squirrel Java', and, after quickly unscrewing the cap, [npc.she] brings it to your lips before tilting your head back and forcing you to quickly gulp down the contents."
						+ " The taste is quite unlike that of any other coffee you've ever drunk, and it reminds you more of a sugary energy drink rather than any caffeinated beverage."
						+ " As the last few drops slide down your throat, a strange, sweet aftertaste lingers on your tongue.");
		}
	};
	
	public static AbstractItemType INT_INGREDIENT_FRUIT_BAT_SQUASH = new AbstractItemType(15,
			"a",
			false,
			"Fruit Bat's Juice Box",
			"Fruit Bat's Juice Boxes",
			"A small cardboard carton, labelled as 'Fruit Bat's Juice Box'."
					+ " On one side of the carton, there's an image of a scantily-clad bat-girl squeezing the juice from all sorts of fruit over her breasts.",
			"attributeBatMorphDrink",
			Colour.ATTRIBUTE_ARCANE,
			null,
			null,
			Rarity.UNCOMMON,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.INT_FRUIT_BAT_SQUASH)),
			Util.newArrayListOfValues(
					ItemTag.BAT_CAVERNS_SPAWN,
					ItemTag.ATTRIBUTE_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_ARCANE;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Tearing the attached straw from the front of the orange-flavoured carton, you use it to pierce the little foil circle on the top, before wrapping your [pc.lips] around it and starting to drink."
						+ " The liquid that enters your mouth has a very strong orange flavour, and, after quickly finishing it, you drink down the other carton, finding that the taste of apples is every bit as intense as orange one was.",
					"Tearing the attached straw from the front of the orange-flavoured carton, you use it to pierce the little foil circle on the top, before bringing it to [npc.namePos] lips and forcing [npc.herHim] to quickly gulp down the liquid within."
						+ " You then do the same with the apple-flavoured one, smiling as [npc.she] gulps down every drop.",
					"[npc.Name] pulls out a pair of cartons of 'Fruit Bat's Squash', and, using the attached straws on each one, quickly gulps down the liquid within.",
					"[npc.Name] pulls out a pair of cartons of 'Fruit Bat's Squash', and, using the attached straws on each one, forces you to drink down the contents."
						+ " The first carton's contents has a very strong orange flavour, and, after quickly finishing it, [npc.name] gets you to drink down the other one, which proves to be apple-flavoured.");
		}
	};
	
	public static AbstractItemType FIT_INGREDIENT_EGG_NOG = new AbstractItemType(30,
			"a bottle of",
			false,
			"Rudolph's Egg nog",
			"Rudolph's Egg nogs",
			"A carton of 'Rudolph's Egg Nog'."
				+ " A label on the front shows the drink's namesake, a buff, stark-naked reindeer-boy, drinking a glass of this carton's contents while receiving oral sex from three enraptured reindeer-girls.",
			"attributeReindeerMorphDrink",
			Colour.ATTRIBUTE_PHYSIQUE,
			null,
			null,
			Rarity.UNCOMMON,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.FIT_EGG_NOG)),
			Util.newArrayListOfValues(
					ItemTag.REINDEER_GIFT,
					ItemTag.ATTRIBUTE_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_PHYSIQUE;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You open the carton and start drinking the bottle of 'Rudolph's Egg Nog'."
						+ " Although the creamy, sweet taste is similar to that of the egg nog you remember drinking in your old world,"
						+ " as you finish gulping down the last of the carton's contents, you find that a strange, slightly salty aftertaste lingers on your tongue.",
					"Opening the carton, you bring the bottle of 'Rudolph's Egg Nog' to [npc.namePos] lips, before tilting [npc.her] head back and forcing [npc.herHim] to quickly gulp down the liquid.",
					"[npc.Name] pulls out a carton of 'Rudolph's Egg Nog', and, after quickly opening it, [npc.she] promptly downs the entire bottle.",
					"[npc.Name] pulls out a carton of 'Rudolph's Egg Nog', and, after quickly opening it, [npc.she] brings it to your lips before tilting your head back and forcing you to quickly gulp down the contents."
						+ " Although the creamy, sweet taste is similar to that of the egg nog you remember drinking in your old world,"
						+ " as you finish gulping down the last of the carton's contents, you find that a strange, slightly salty aftertaste lingers on your tongue.");
		}
	};
	
	public static AbstractItemType SEX_INGREDIENT_HARPY_PERFUME = new AbstractItemType(250,
			"a bottle of",
			false,
			"Harpy Perfume",
			"Harpy Perfumes",
			"A glass bottle of what looks to be a kind of feminine perfume."
				+ " There's a stylised image of a harpy's wings on the front of the bottle.",
			"attributeHarpyPerfume",
			Colour.GENERIC_SEX,
			null,
			null,
			Rarity.UNCOMMON,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.SEX_HARPY_PERFUME)),
			Util.newArrayListOfValues(
					ItemTag.ATTRIBUTE_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_SEXUAL;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "spray";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You take in a deep breath of an intoxicating feminine scent as you spray a little squirt of the 'Harpy Perfume' onto your neck."
						+ " Looking down at the curiously now-empty bottle of perfume, you feel a bubbly wave of excitement running through you, and without thinking, you find yourself letting out a very girly giggle.",
					"You spray a little squirt of the 'Harpy Perfume' onto [npc.namePos] neck.",
					"[npc.Name] pulls out a bottle of 'Harpy Perfume', and, after quickly popping off the cap, [npc.she] promptly sprays a little squirt onto [npc.her] neck.",
					"[npc.Name] pulls out a bottle of 'Harpy Perfume', and, after quickly popping off the cap, [npc.she] sprays a little squirt onto your neck."
						+ " You instantly feel a bubbly wave of excitement running through you, and without thinking, you find yourself letting out a very girly giggle.");
		}
	};
	
	public static AbstractItemType SEX_INGREDIENT_SLIME_QUENCHER = new AbstractItemType(250,
			"a bottle of",
			false,
			"Slime Quencher",
			"Slime Quenchers",
			"A small glass bottle of luminescent, fizzy pop."
					+ " The label on the front reads 'Slime Quencher', and, to one side, there's a picture of a completely naked slime-girl pressing her breasts together and smiling at you.",
			"attributeSlimeDrink",
			Colour.GENERIC_SEX,
			null,
			null,
			Rarity.UNCOMMON,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.SEX_SLIME_DRINK)),
			Util.newArrayListOfValues(
					ItemTag.BAT_CAVERNS_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.ATTRIBUTE_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_SEXUAL;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You pop off the cap and start drinking the bottle of 'Slime Quencher'."
						+ " The recognisable taste of a sugary energy drink fills your mouth, and you greedily gulp down the all of the glowing liquid in a matter of seconds.",
					"Popping off the cap, you bring the bottle of 'Slime Quencher' to [npc.namePos] lips, before tilting [npc.her] head back and forcing [npc.herHim] to quickly gulp down the liquid.",
					"[npc.Name] pulls out a bottle of 'Slime Quencher', and, after quickly popping off the cap, [npc.she] promptly downs the entire bottle.",
					"[npc.Name] pulls out a bottle of 'Slime Quencher', and, after quickly popping off the cap, [npc.she] brings it to your lips before tilting your head back and forcing you to quickly gulp down the contents."
						+ " The recognisable taste of a sugary energy drink fills your mouth, and, with [npc.namePos] help, you greedily gulp down the all of the glowing liquid in a matter of seconds.");
		}
	};
	
	public static AbstractItemType SEX_INGREDIENT_BUNNY_JUICE = new AbstractItemType(250,
			"a bottle of",
			false,
			"Bunny Juice",
			"Bunny Juices",
			"A small plastic bottle of what appears to be some sort of carrot juice, labelled as 'Bunny Juice'."
					+ " On the label, there's a rather obscene image of a rabbit-girl stuffing a carrot-shaped dildo into her pussy.",
			"attributeRabbitMorphDrink",
			Colour.GENERIC_SEX,
			null,
			null,
			Rarity.UNCOMMON,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.SEX_RABBIT_MORPH_DRINK)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN, //TODO
					ItemTag.ATTRIBUTE_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_SEXUAL;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You unscrew the cap and start drinking the bottle of 'Bunny Juice'."
						+ " An intense taste of carrots instantly fills your mouth, and as you swallow the delicious liquid, you discover that it has an unusually sweet aftertaste.",
					"Unscrewing the cap, you bring the bottle of 'Bunny Juice' to [npc.namePos] lips, before tilting [npc.her] head back and forcing [npc.herHim] to quickly gulp down the orange liquid.",
					"[npc.Name] pulls out a bottle of 'Bunny Juice', and, after quickly unscrewing the cap, [npc.she] promptly downs the entire bottle.",
					"[npc.Name] pulls out a bottle of 'Bunny Juice', and, after quickly unscrewing the cap, [npc.she] brings it to your lips before tilting your head back and forcing you to quickly gulp down the contents."
						+ " An intense taste of carrots instantly fills your mouth, and as you swallow the delicious liquid, you discover that it has an unusually sweet aftertaste.");
		}
	};
	
	public static AbstractItemType SEX_INGREDIENT_MINCE_PIE = new AbstractItemType(10,
			"a",
			false,
			"mince pie",
			"mince pies",
			"A sweet pie, filled with a mixture of dried fruits and spices."
					+ " Curiously, the pie seems to remain permanently warm to the touch, revealing that an enchantment of some sort must have been placed on it...",
			"attributeNoRaceMincePie",
			Colour.GENERIC_SEX,
			null,
			null,
			Rarity.UNCOMMON,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.SEX_MINCE_PIE)),
			Util.newArrayListOfValues(
					ItemTag.REINDEER_GIFT,
					ItemTag.ATTRIBUTE_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_SEXUAL;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "eat";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You bring the enchanted mince pie up to your mouth, before taking an experimental bite."
						+ " The warm, spiced fruit filling is absolutely delicious, leading you to greedily wolf down the entire pie.",
					"You bring the enchanted mince pie up to [npc.namePos] mouth, before feeding it to [npc.herHim].",
					"[npc.Name] pulls out a mince pie, and promptly wolfs it down.",
					"[npc.Name] brings an enchanted mince pie up to your mouth, before starting to feed it to you."
						+ " The warm, spiced fruit filling is absolutely delicious, and you greedily wolf down the entire pie.");
		}
	};
	
	// Corruption ingredients are "mysterious liquids" (cum and milk...):
	
	public static AbstractItemType COR_INGREDIENT_LILITHS_GIFT = new AbstractItemType(1500,
			"a bottle of",
			false,
			"Lilith's Gift",
			"Lilith's Gifts",
			"A glass bottle, filled with bubbling pink liquid."
					+ " On the bottle's label, there is an image of Lilith's perfectly-formed, heart-shaped ass."
					+ " Her delicate hands are reaching down to pull apart her soft ass cheeks, fully exposing her asshole and pussy, both of which are dripping wet from excitement.",
			"attributeDemonDrink",
			Colour.ATTRIBUTE_CORRUPTION,
			null,
			null,
			Rarity.UNCOMMON,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.COR_LILITHS_GIFT)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.ATTRIBUTE_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_CORRUPTION;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"The moment you pull the stopper out from the top of the bottle of 'Lilith's Gift', you're filled with a desperate need to drink the bubbling pink liquid contained within."
							+ " Instantly, you bring the bottle to your lips and gulp it all down, suppressing your gag reflex as your senses are overwhelmed by how sickeningly sweet it is.",
					"You pull the stopper out from the top of the bottle of 'Lilith's Gift', before bringing it to [npc.namePos] lips and forcing [npc.herHim] to drink down the liquid within.",
					"[npc.Name] pulls out a bottle of 'Lilith's Gift', and, after quickly pulling out the stopper, [npc.she] promptly downs the entire bottle.",
					"[npc.Name] pulls out a bottle of 'Lilith's Gift', and, after quickly pulling out the stopper, [npc.she] brings it to your lips before tilting your head back and forcing you to quickly gulp down the contents."
						+ " You suppress your gag reflex as your senses are suddenly overwhelmed by the sickeningly-sweet liquid.");
		}
	};
	
	public static AbstractItemType COR_INGREDIENT_IMPISH_BREW = new AbstractItemType(10,
			"a bottle of",
			false,
			"Impish Brew",
			"Impish Brews",
			"A cracked and dirty glass bottle, filled with a creamy-yellow liquid."
					+ " There's no label, but someone's helpfully, albeit crudely, written 'Impish Brew' in black marker pen on one side."
					+ " You think you can guess what the thick, musky liquid is inside...",
			"attributeImpDrink",
			Colour.ATTRIBUTE_CORRUPTION,
			null,
			null,
			Rarity.UNCOMMON,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.COR_IMPISH_BREW)),
			Util.newArrayListOfValues(
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.ATTRIBUTE_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_CORRUPTION;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"As you unscrew the cap, your senses are immediately assaulted by the musky, potent smell of the liquid within."
							+ " Bringing the dirty bottle to your lips, you take a tentative sip, discovering that the drink isn't quite as bad you thought it would be.",
					"You unscrew the cap from the bottle 'Impish Brew', before bringing it to [npc.namePos] lips and forcing [npc.herHim] to drink down the liquid within.",
					"[npc.Name] pulls out a bottle of 'Impish Brew', and, after quickly unscrewing the cap, [npc.she] promptly downs the entire bottle.",
					"[npc.Name] pulls out a bottle of 'Impish Brew', and, after quickly after quickly unscrewing the cap, [npc.she] brings it to your lips before tilting your head back and forcing you to quickly gulp down the musky contents.");
		}
	};
	
	public static AbstractItemType FETISH_UNREFINED = new AbstractItemType(500,
			"a vial of",
			false,
			"Mystery Kink",
			"Mystery Kinks",
			"A delicate glass bottle, filled with a viscous, glowing-pink liquid."
					+ " From the label on one side reading 'Mystery Kink', it's quite safe to assume that this concoction carries a potent enchantment, which somehow influences the drinker's fetishes.",
			"fetishDrink",
			Colour.GENERIC_SEX,
			null,
			null,
			Rarity.EPIC,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.MYSTERY_KINK)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.BAT_CAVERNS_SPAWN,
					ItemTag.MISC_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {

		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.FETISH_ENHANCEMENT;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return FETISH_REFINED;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You pull the stopper out from the top of the bottle of 'Mystery Kink', before bringing it to your lips and gulping down the thick pink liquid that's contained within.",
					"You pull the stopper out from the top of the bottle of 'Mystery Kink', before bringing it to [npc.namePos] lips and forcing [npc.herHim] to drink down the liquid within.",
					"[npc.Name] pulls out a bottle of 'Mystery Kink', and, after quickly pulling out the stopper, [npc.she] promptly downs the entire bottle.",
					"[npc.Name] pulls out a bottle of 'Mystery Kink', and, after quickly pulling out the stopper,"
							+ " [npc.she] brings it to your lips before tilting your head back and forcing you to quickly gulp down the thick pink liquid that's contained within.");
		}
	};
	
	public static AbstractItemType FETISH_REFINED = new AbstractItemType(750,
			"a vial of",
			false,
			"Fetish Endowment",
			"Fetish Endowments",
			"A vial of bubbling pink liquid, which was refined from a bottle of 'Mystery Kink'."
					+ " Its potent enchantment is far more refined than that of the liquid it was distilled from, and is able to add or remove specific fetishes.",
			"fetishDrinkRefined",
			Colour.FETISH,
			null,
			null,
			Rarity.LEGENDARY,
			null,
			null,
			null) {


		@Override
		public boolean isFetishGiving() {
			return true;
		}
		
		@Override
		public boolean isAbleToBeUsedInSex() {
			return true;
		}

		@Override
		public boolean isAbleToBeUsedInCombat() {
			return true;
		}
		
		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You pull the stopper out from the top of the glass vial of 'Fetish Endowment', before bringing it to your lips and gulping down the sickly sweet liquid that's contained within.",
					"You pull the stopper out from the top of the glass vial of 'Fetish Endowment', before bringing it to [npc.namePos] lips and forcing [npc.herHim] to drink down the liquid within.",
					"[npc.Name] pulls out a glass vial of 'Fetish Endowment', and, after quickly pulling out the stopper, [npc.she] promptly downs the entire bottle.",
					"[npc.Name] pulls out a glass vial of 'Fetish Endowment', and, after quickly pulling out the stopper,"
							+ " [npc.she] brings it to your lips before tilting your head back and forcing you to quickly gulp down the sickly sweet liquid that's contained within.");
		}
	};
	
	public static AbstractItemType ADDICTION_REMOVAL = new AbstractItemType(750,
			"a bottle of",
			false,
			"Angel's Nectar",
			"Angel's Nectars",
			"A delicate crystal bottle, filled with a cool, blue liquid."
					+ " Engraved into one side are the words 'Angel's Nectar', although you're unsure if this fluid really does have anything to do with them...",
			"addictionRemoval",
			Colour.RACE_HUMAN,
			null,
			null,
			Rarity.LEGENDARY,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.ADDICTION_REMOVAL)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.BAT_CAVERNS_SPAWN,
					ItemTag.MISC_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public boolean isAbleToBeUsedInSex() {
			return true;
		}

		@Override
		public boolean isAbleToBeUsedInCombat() {
			return true;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ADDICTION_REMOVAL_REFINEMENT;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return ADDICTION_REMOVAL_REFINED;
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You pull the crystal stopper out from the top of the bottle of 'Angel's Nectar', before bringing it to your lips and gulping down the tasteless liquid that's contained within.",
					"You pull the crystal stopper out from the top of the bottle of 'Angel's Nectar', before bringing it to [npc.namePos] lips and forcing [npc.herHim] to drink down the liquid within.",
					"[npc.Name] pulls out a bottle of 'Angel's Nectar', and, after quickly pulling out the crystal stopper, [npc.she] promptly downs the entire bottle.",
					"[npc.Name] pulls out a bottle of 'Angel's Nectar', and, after quickly pulling out the crystal stopper,"
							+ " [npc.she] brings it to your lips before tilting your head back and forcing you to quickly gulp down the tasteless liquid that's contained within.");
		}
	};
	
	public static AbstractItemType ADDICTION_REMOVAL_REFINED = new AbstractItemType(1500,
			"a delicate vial of",
			false,
			"Angel's Purity",
			"Angel's Purities",
			"A vial of cool, light-blue liquid, which gives off a faint, steady glow."
					+ " Being a refined, and far more specialised form of 'Angel's Nectar', this liquid has lost its ability to remove addictions, and instead, is able to permanently lower the corruption of whoever drinks it...",
			"addictionRemovalRefined",
			Colour.RACE_HUMAN,
			null,
			null,
			Rarity.LEGENDARY,
			null,
			null,
			null) {

		@Override
		public boolean isAbleToBeUsedInSex() {
			return true;
		}

		@Override
		public boolean isAbleToBeUsedInCombat() {
			return true;
		}
		
		@Override
		public String getUseName() {
			return "drink";
		}
		
		@Override
		public int getValue(List<ItemEffect> effects) {
			int value = 0;
			if(effects!=null) {
				for(ItemEffect ie : effects) {
					switch(ie.getPotency()) {
						case BOOST:
							value += 1000;
							break;
						case MAJOR_BOOST:
							value += 1500;
							break;
						case MINOR_BOOST:
							value += 500;
							break;
						case MINOR_DRAIN:
						case DRAIN:
						case MAJOR_DRAIN:
							break;
					}
				}
			}
			return value;
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You pull the stopper out from the top of the glass vial of 'Angel's Purity', before bringing it to your lips and gulping down the cool, refreshing liquid which is contained within.",
					"You pull the stopper out from the top of the glass vial of 'Angel's Purity', before bringing it to [npc.namePos] lips and forcing [npc.herHim] to drink down the liquid within.",
					"[npc.Name] pulls out a glass vial of 'Angel's Purity', and, after quickly pulling out the stopper, [npc.she] promptly downs the entire bottle.",
					"[npc.Name] pulls out a glass vial of 'Angel's Purity', and, after quickly pulling out the stopper,"
							+ " [npc.she] brings it to your lips before tilting your head back and forcing you to quickly gulp down the cool, refreshing liquid which is contained within.");
		}
	};
	
	public static AbstractItemType MUSHROOM = new AbstractItemType(500,
			"a cluster of",
			true,
			"Glowing Mushroom",
			"Glowing Mushrooms",
			"Bioluminescent mushrooms such as these are commonly found growing in the Bat Caverns."
					+ " The slimes which call those caverns their home are particularly fond of consuming these mushrooms, which is what causes their bodies to glow.",
			"mushrooms",
			Colour.BASE_BLUE_LIGHT,
			null,
			null,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.MUSHROOMS)),
			Util.newArrayListOfValues(
					ItemTag.BAT_CAVERNS_SPAWN)) {


		@Override
		public boolean isAbleToBeUsedInSex() {
			return true;
		}

		@Override
		public boolean isAbleToBeUsedInCombat() {
			return true;
		}

		@Override
		public String getUseName() {
			return "eat";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You pop the small cluster of glowing mushrooms into your mouth, and as you chew and swallow them down, you discover that they have a refreshing, minty taste.",
					"You pop the small cluster of glowing mushrooms into [npc.namePos] mouth, before making sure that [npc.she] chews and swallows them down.",
					"[npc.Name] pops a small cluster of glowing mushrooms into [npc.her] mouth, before chewing and swallowing them down.",
					"[npc.Name] pops a small cluster of glowing mushrooms into your mouth, and as [npc.she] makes you chew and swallow them down, you discover that they have a refreshing, minty taste.");
		}
	};
	
	// Racial ingredients:
	

	public static AbstractItemType DEBUG_DEMON_POTION = new AbstractItemType(100_000_000,
			"a bottle of",
			false,
			"Innoxia's Gift",
			"Innoxia's Gifts",
			"Once thought to be lost forever, this bottle of bubbling pink liquid has made a surprise return, and can turn anyone who drinks it into a demon!"
					+ "<br/>[style.italicsMinorGood(While this is a debug-only item, it should be safe to use anywhere.)]",
			"raceDemonInnoxiasGift",
			Colour.ATTRIBUTE_CORRUPTION,
			null,
			null,
			Rarity.LEGENDARY,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.DEBUG_DEMON_POTION_EFFECT)),
			Util.newArrayListOfValues()) {

		@Override
		public boolean isTransformative() {
			return true;
		}
		
		@Override
		public boolean isAbleToBeUsedInSex() {
			return true;
		}

		@Override
		public boolean isAbleToBeUsedInCombat() {
			return true;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"The moment you pull the stopper out from the top of the bottle of 'Innoxia's Gift', you're filled with a desperate need to drink the bubbling pink liquid contained within."
							+ " Instantly, you bring the bottle to your lips and gulp it all down, suppressing your gag reflex as your senses are overwhelmed by how sickeningly sweet it is.",
					"You pull the stopper out from the top of the bottle of 'Innoxia's Gift', before bringing it to [npc.namePos] lips and forcing [npc.herHim] to drink down the liquid within.",
					"[npc.Name] pulls out a bottle of 'Innoxia's Gift', and, after quickly pulling out the stopper, [npc.she] promptly downs the entire bottle.",
					"[npc.Name] pulls out a bottle of 'Innoxia's Gift', and, after quickly pulling out the stopper, [npc.she] brings it to your lips before tilting your head back and forcing you to quickly gulp down the contents."
						+ " You suppress your gag reflex as your senses are suddenly overwhelmed by the sickeningly-sweet liquid.");
		}
	};
	
	public static AbstractItemType RACE_INGREDIENT_HUMAN = new AbstractItemType(1000,
			"a vial of",
			false,
			"Angel's Tears",
			"Angel's Tears",
			"A delicate glass vial full of a light turquoise liquid."
					+ " There's an image of a weeping angel engraved into the glass, and you see that her tears are falling into a vial just like this one.",
			"raceHumanAngelsTears",
			Colour.RACE_HUMAN,
			null,
			null,
			Rarity.RARE,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.RACE_ANGELS_TEARS)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.RACE_HUMAN;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return ELIXIR;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You pull out the little glass stopper and bring the vial of 'Angel's Tears' to your lips."
							+ " The faint scent of roses rises up from the opening, and you find yourself letting out a gentle sigh as you tilt back your head before drinking down the cool liquid.",
					"You pull out the little glass stopper and bring the vial of 'Angel's Tears' to [npc.namePos] lips, before forcing [npc.herHim] to drink down the liquid within.",
					"[npc.Name] pulls out a bottle of 'Angel's Tears', and, after quickly pulling out the stopper, [npc.she] promptly downs the entire bottle.",
					"[npc.Name] pulls out a bottle of 'Angel's Tears', and, after quickly pulling out the stopper, [npc.she] brings it to your lips before tilting your head back and forcing you to quickly gulp down the contents."
						+ " The faint scent of roses rises up from the opening, and you find yourself letting out a gentle sigh as you drink down the cool liquid.");
		}
	};
	
	public static AbstractItemType RACE_INGREDIENT_CAT_MORPH = new AbstractItemType(250,
			"a",
			false,
			"Kitty's Reward",
			"Kitty's Rewards",
			"A small, square food tin with a ring-pull lid."
					+ " A label on the side shows a greater cat-girl devouring a plate of what looks to be this can's contents; some sort of tinned salmon.",
			"raceCatMorphKittysReward",
			Colour.RACE_CAT_MORPH,
			null,
			null,
			Rarity.RARE,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.RACE_KITTYS_REWARD)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.RACE_CAT_MORPH;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return ELIXIR;
		}

		@Override
		public String getUseName() {
			return "eat";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You pull back the ring-pull and peel off the lid to the can of 'Kitty's Reward'."
						+ " A rich, fishy smell accompanies the sight of what looks to be tinned salmon, and you find yourself unable to resist the delicious-looking meat."
						+ " You quickly wolf down the can's contents, finding that it was as delicious as it looked.",
					"You pull out the can of 'Kitty's Reward', and, after pulling off the lid, force [npc.name] to eat the fishy contents.",
					"[npc.Name] pulls out a can of 'Kitty's Reward', and, after peeling off the lid, quickly devours the contents.",
					"[npc.Name] pulls out a can of 'Kitty's Reward', and, after peeling off the lid, [npc.she] forces you to eat the contents."
						+ " A rich, fishy smell accompanies the sight of what looks to be tinned salmon, and you soon find yourself greedily gulping down the delicious meat.");
		}
	};
	

	public static AbstractItemType RACE_INGREDIENT_COW_MORPH  = new AbstractItemType(250,
			"a pot of",
			false,
			"Bubble Cream",
			"Bubble Creams",
			"A small pot of yoghurt, with a black-and-white cow-pattern styled onto the lid."
					+ " A label on the side declares it to be 'Bubble Cream', which seems to be a little misleading, as there isn't any sort of bubbling going on in the creamy mixture contained within.",
			"raceCowMorphBubbleCream",
			Colour.RACE_COW_MORPH,
			null,
			null,
			Rarity.RARE,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.RACE_BUBBLE_CREAM)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.RACE_COW_MORPH;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return ELIXIR;
		}

		@Override
		public String getUseName() {
			return "eat";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You peel back the foil lid to reveal the pot's contents."
						+ " Despite this product being called 'Cream', it's actually a thick yoghurt that's contained within."
						+ " Detaching the tiny wooden spoon that was stuck to one side, you eagerly dig in to the creamy mixture,"
							+ " letting out satisfied little humming noises as you discover that it's quite possibly the most delicious yoghurt that you've ever tasted.",
					"You pull out the pot of 'Bubble Cream', and, after pulling off the lid, force [npc.name] to eat the contents.",
					"[npc.Name] pulls out a pot of 'Bubble Cream', and, after peeling off the lid, quickly devours the contents.",
					"[npc.Name] pulls out a pot of 'Bubble Cream', and, after peeling off the lid, [npc.she] forces you to eat the contents."
						+ " Despite this product being called 'Cream', it's actually a thick yoghurt that's contained within."
						+ " Detaching the tiny wooden spoon that was stuck to one side, you eagerly dig in to the creamy mixture,"
							+ " letting out satisfied little humming noises as you discover that it's quite possibly the most delicious yoghurt that you've ever tasted.");
		}
	};
	

	public static AbstractItemType RACE_INGREDIENT_SQUIRREL_MORPH = new AbstractItemType(250,
			"a bag of",
			false,
			"Round Nuts",
			"Round Nuts",
			"A small bag of round nuts. A label on one side shows a greater squirrel-girl stuffing a handful of nuts into her mouth.",
			"raceSquirrelMorphRoundNuts",
			Colour.RACE_SQUIRREL_MORPH,
			null,
			null,
			Rarity.RARE,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.RACE_ROUND_NUTS)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.RACE_SQUIRREL_MORPH;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return ELIXIR;
		}

		@Override
		public String getUseName() {
			return "eat";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You pull at the sides of one end of the bag, and open the package."
						+ " A rich, earthy smell accompanies the sight of the nuts inside, and you find yourself unable to resist the delicious-looking display."
						+ " You quickly wolf down the bag's contents, finding that the nuts are as delicious as they look.",
					"You pull out the bag of 'Round Nuts', and, after tearing it open, force [npc.name] to eat the contents.",
					"[npc.Name] pulls out a bag of 'Round Nuts', and, after tearing it open, quickly devours the contents.",
					"[npc.Name] pulls out a bag of 'Round Nuts', and, after tearing it open, [npc.she] forces you to eat the contents."
						+ " A rich, earthy smell accompanies the sight of the nuts inside, and you find yourself unable to resist the delicious-looking display."
						+ " You quickly wolf down the bag's contents, finding that the nuts are as delicious as they look.");
		}
	};
	
	public static AbstractItemType RACE_INGREDIENT_BAT_MORPH = new AbstractItemType(250,
			"a",
			false,
			"Fruit Bat's Salad",
			"Fruit Bat's Salads",
			"A little plastic pot, containing a delicious-looking fruit salad."
					+ " Printed into the film lid, there's a little picture of a bat-girl sitting cross-legged as she eats her way through a mountain of fruit.",
			"raceBatMorphFruitSalad",
			Colour.RACE_BAT_MORPH,
			null,
			null,
			Rarity.RARE,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.RACE_FRUIT_SALAD)),
			Util.newArrayListOfValues(
					ItemTag.BAT_CAVERNS_SPAWN,
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.RACE_BAT_MORPH;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return ELIXIR;
		}

		@Override
		public String getUseName() {
			return "eat";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Peeling off the film lid, you start eating the fruit salad."
						+ " The slices of orange, mango, and strawberry are all absolutely delicious, and it only takes you a moment to finish off the entire pot.",
					"Peeling off the film lid, you force [npc.name] to eat the 'Fruit Bat's Salad'.",
					"[npc.Name] pulls out a pot of 'Fruit Bat's Salad', and, after peeling off the lid, quickly wolfs down the contents.",
					"[npc.Name] pulls out a pot of 'Fruit Bat's Salad', and, after peeling off the lid, forces you to eat the contents."
							+ " The slices of orange, mango, and strawberry are all absolutely delicious, and it only takes you a moment to finish off the entire pot.");
		}
	};
	
	public static AbstractItemType RACE_INGREDIENT_RAT_MORPH = new AbstractItemType(250,
			"a",
			false,
			"Brown Rat's Burger",
			"Brown Rat's Burgers",
			"A double-cheeseburger, wrapped up in greaseproof paper."
					+ " On the wrapper, there's a picture of a brown-furred rat-boy greedily shoving one of these burgers into his mouth.",
			"raceRatMorphBurger",
			Colour.RACE_RAT_MORPH,
			null,
			null,
			Rarity.RARE,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.RACE_BURGER)),
			Util.newArrayListOfValues(
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.RACE_RAT_MORPH;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return ELIXIR;
		}

		@Override
		public String getUseName() {
			return "eat";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Unwrapping the paper covering, you quickly reveal the greasy cheeseburger within."
							+ " Bringing it up to your mouth, you take a big bite, and discover that it's absolutely delicious."
							+ " Encouraged by the taste, it only takes you a few moments to wolf down the meal.",
					"Unwrapping the paper covering, you quickly reveal the greasy cheeseburger within."
							+ " Bringing it up to [npc.namePos] mouth, you force [npc.herHim] to eat the entire burger.",
					"[npc.Name] pulls out a 'Brown Rat's Burger', and, after peeling off the wrapper, quickly wolfs down the contents.",
					"[npc.Name] pulls out a 'Brown Rat's Burger', and, after peeling off the wrapper, forces you to eat the contents."
							+ " You quickly discover that it's absolutely delicious, and it only takes you a moment to finish off the entire burger.");
		}
	};
	
	public static AbstractItemType RACE_INGREDIENT_RABBIT_MORPH = new AbstractItemType(250,
			"a",
			false,
			"Bunny Carrot-Cake",
			"Bunny Carrot-Cakes",
			"An individually-wrapped slice of frosted carrot cake, complete with a decorative icing carrot on the top."
					+ " On the wrapper, there's a very lewd image of a rabbit-girl being bred by a muscular rabbit-boy.",
			"raceRabbitMorphCarrotCake",
			Colour.RACE_RABBIT_MORPH,
			null,
			null,
			Rarity.RARE,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.RACE_CARROT_CAKE)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN, //TODO
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.RACE_RABBIT_MORPH;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return ELIXIR;
		}

		@Override
		public String getUseName() {
			return "eat";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Tearing off the little plastic wrapper, you pop the small slice of carrot cake into your mouth."
							+ " The taste is absolutely delicious, and as you swallow down the sweet mess, a delightfully carroty aftertaste lingers on your tongue.",
					"Unwrapping the little plastic wrapper, you pop the small slice of carrot cake into [npc.namePos] mouth, before making [npc.herHim] swallow it all down.",
					"[npc.Name] pulls out a 'Bunny Carrot-Cake', and, after tearing off the little plastic wrapper, quickly wolfs down the cake in one go.",
					"[npc.Name] pulls out a 'Bunny Carrot-Cake', and, after tearing off the little plastic wrapper, forces the small slice of cake into your mouth."
							+ " The taste is absolutely delicious, and as you swallow down the sweet mess, a delightfully carroty aftertaste lingers on your tongue.");
		}
	};
	
	public static AbstractItemType RACE_INGREDIENT_DOG_MORPH = new AbstractItemType(250,
			"a",
			false,
			"Canine Crunch",
			"Canine Crunches",
			"An individually-wrapped dog-biscuit in the shape of a bone."
					+ " It's obviously meant as a snack for dog-morphs, but is edible for all races.",
			"raceDogMorphCanineCrunch",
			Colour.RACE_DOG_MORPH,
			null,
			null,
			Rarity.RARE,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.RACE_CANINE_CRUNCH)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.RACE_DOG_MORPH;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return ELIXIR;
		}

		@Override
		public String getUseName() {
			return "eat";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You peel back the paper packaging and pop the 'Canine Crunch' into your mouth."
						+ " As you crunch down on the dry biscuit, you find that it's quite bland and salty.",
					"You pull out the 'Canine Crunch', and, after tearing off the packaging, force [npc.name] to eat it.",
					"[npc.Name] pulls out a 'Canine Crunch', and, quickly unwrapping the paper packaging, proceeds to wolf down the bone-shaped biscuit.",
					"[npc.Name] pulls out a 'Canine Crunch', and, after tearing off the paper packaging, [npc.she] forces you to eat it."
						+ " As you crunch down on the dry biscuit, you find that it's quite bland and salty.");
		}
	};
	
	public static AbstractItemType RACE_INGREDIENT_FOX_MORPH = new AbstractItemType(250,
			"a",
			false,
			"Chicken Pot Pie",
			"Chicken Pot Pies",
			"A tin containing a pie with a mix of vegetables and meat."
					+ " While plenty of omnivorous races enjoy the taste of these pies, they are a particular favourite of fox-morphs.",
			"raceFoxMorphPie",
			Colour.RACE_FOX_MORPH,
			null,
			null,
			Rarity.RARE,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.RACE_FOX_PIE)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.RACE_FOX_MORPH;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return ELIXIR;
		}

		@Override
		public String getUseName() {
			return "eat";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You bring the chicken pot pie up to your mouth, before taking a bite."
						+ " You find that the nutritious mix of vegetables and meat is easy to chew through, and you swiftly consume the contents of the small tin.",
					"You bring the chicken pot pie up to [npc.namePos] mouth, before feeding it to [npc.herHim].",
					"[npc.Name] pulls out a chicken pot pie, and promptly wolfs it down.",
					"[npc.Name] brings a chicken pot pie up to your mouth, before starting to feed it to you."
							+ " You find that the nutritious mix of vegetables and meat is easy to chew through, and you swiftly consume the contents of the small tin.");
		}
	};

	public static AbstractItemType RACE_INGREDIENT_HORSE_MORPH = new AbstractItemType(250,
			"a",
			false,
			"Sugar Carrot Cube",
			"Sugar Carrot Cubes",
			"A bright orange sugar cube, which smells of carrots."
					+ " From the equine icon on the wrapper, this is obviously meant as a snack for horse-morphs, but is edible for all races.",
			"raceHorseMorphSugarCarrotCube",
			Colour.RACE_HORSE_MORPH,
			null,
			null,
			Rarity.RARE,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.RACE_SUGAR_CARROT_CUBE)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.RACE_HORSE_MORPH;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return ELIXIR;
		}

		@Override
		public String getUseName() {
			return "eat";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You peel off the paper packaging and pop the 'Sugar Carrot Cube' into your mouth."
							+ " The strong taste of carrots instantly fills your mouth, but before you have any time to relish the flavour, you find that it's dissolved in your saliva, and you've gulped down the sugary mess.",
					"You pull out the 'Sugar Carrot Cube', and, after tearing off the packaging, force [npc.name] to eat it.",
					"[npc.Name] pulls out a 'Sugar Carrot Cube', and, quickly unwrapping the paper packaging, pops it into [npc.her] mouth and swallows it down.",
					"[npc.Name] pulls out a 'Sugar Carrot Cube', and, after tearing off the paper packaging, [npc.she] forces you to eat it."
						+ " The strong taste of carrots instantly fills your mouth, but before you have any time to relish the flavour, you find that it's dissolved in your saliva, and you've gulped down the sugary mess.");
		}
	};
	
	public static AbstractItemType RACE_INGREDIENT_REINDEER_MORPH = new AbstractItemType(250,
			"a",
			false,
			"Sugar Cookie",
			"Sugar Cookies",
			"An individually-wrapped, icing-and-sprinkles-topped sugar cookie, which looks, rather surprisingly, extremely normal.",
			"raceReindeerMorphSugarCookie",
			Colour.RACE_REINDEER_MORPH,
			null,
			null,
			Rarity.RARE,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.RACE_SUGAR_COOKIE)),
			Util.newArrayListOfValues(
					ItemTag.REINDEER_GIFT,
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.RACE_REINDEER_MORPH;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return ELIXIR;
		}

		@Override
		public String getUseName() {
			return "eat";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You unwrap the 'Sugar Cookie' from its protective packaging and pop it into your mouth."
						+ " Although it looked normal enough, you soon discover that the taste is anything but, being both extremely sweet and salty at the same time."
						+ " Despite the unusual combination of flavours, it's tasty enough, and you soon find yourself having eaten the whole cookie.",
					"You pull out the 'Sugar Cookie', and, after tearing off the packaging, force [npc.name] to eat it.",
					"[npc.Name] pulls out a 'Sugar Cookie', and, quickly unwrapping the paper packaging, pops it into [npc.her] mouth and swallows it down.",
					"[npc.Name] pulls out a 'Sugar Cookie', and, after tearing off the paper packaging, [npc.she] forces you to eat it."
						+ " Although it looked normal enough, you soon discover that the taste is anything but, being both extremely sweet and salty at the same time."
						+ " Despite the unusual combination of flavours, it's tasty enough, and you soon find yourself having eaten the whole cookie.");
		}
	};
	
	public static AbstractItemType RACE_INGREDIENT_ALLIGATOR_MORPH = new AbstractItemType(250,
			"a",
			false,
			"Gator's Gumbo",
			"Gator's Gumbo",
			"An iron bowl, complete with a sealable lid."
				+ " The contents take the form of a delicious-smelling variety of gumbo, containing meat, okra, and a variety of other mysterious vegetables.",
			"raceGatorMorphGatorsGumbo",
			Colour.RACE_ALLIGATOR_MORPH,
			null,
			null,
			Rarity.RARE,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.RACE_ALLIGATORS_GUMBO)),
			Util.newArrayListOfValues(
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.RACE_ALLIGATOR_MORPH;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return ELIXIR;
		}

		@Override
		public String getUseName() {
			return "eat";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You remove the lid from the bowl of 'Gator's Gumbo' and start eating the rich meal contained within."
							+ " The delicious, slightly spicy taste of seafood instantly fills your mouth, but you don't take any time to really relish the flavour,"
							+ " as you can't help but greedily gulp down the tangy mess and move on to your next mouthful.",
					"You pull out the bowl of 'Gator's Gumbo', and, after removing the lid, force [npc.name] to eat the contents.",
					"[npc.Name] pulls out a bowl of 'Gator's Gumbo', and, quickly removing the lid, wolfs down the rich meal contained within.",
					"[npc.Name] pulls out a bowl of 'Gator's Gumbo', and, after quickly removing the lid, [npc.she] forces you to eat it."
						+ " The delicious, slightly spicy taste of seafood instantly fills your mouth, but you don't take any time to really relish the flavour,"
							+ " as you can't help but greedily gulp down the tangy mess and move on to your next mouthful.");
		}
	};
	
	public static AbstractItemType RACE_INGREDIENT_WOLF_MORPH = new AbstractItemType(250,
			"a package of",
			false,
			"Meat and Marrow",
			"Meat and Marrows",
			"A package of 'Meat and Marrow', which consists of a slab of some sort of raw meat, wrapped in grease-proof paper and tied up with brown string.",
			"raceWolfMorphMeatAndMarrow",
			Colour.RACE_WOLF_MORPH,
			null,
			null,
			Rarity.RARE,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.RACE_MEAT_AND_MARROW)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.RACE_WOLF_MORPH;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return ELIXIR;
		}

		@Override
		public String getUseName() {
			return "eat";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You untie the brown string, and, peeling back the grease-proof paper, bring the now-exposed slab of meat to your mouth."
						+ " A rich, bloody smell rises to fill your nostrils, but instead of repulsing you, you find yourself drooling at the thought of eating the raw meat."
						+ " Without further thought, you greedily devour the dripping flesh, licking your fingers clean after rapidly finishing your impromptu meal.",
					"You pull out the package of 'Meat and Marrow', and, after peeling back the grease-proof paper, force [npc.name] to eat the contents.",
					"[npc.Name] pulls out a package of 'Meat and Marrow', and, tearing off the paper packaging, [npc.she] quickly devours the slab of raw meat that was stored within.",
					"[npc.Name] pulls out a package of 'Meat and Marrow', and, after quickly tearing off the paper packaging, [npc.she] forces you to eat it."
						+ " A rich, bloody smell rises to fill your nostrils, but instead of repulsing you, you find yourself drooling at the thought of eating the raw meat."
						+ " Without further thought, you greedily devour the dripping flesh, licking your fingers clean after rapidly finishing your impromptu meal.");
		}
	};
	
	public static AbstractItemType RACE_INGREDIENT_HARPY = new AbstractItemType(250,
			"a",
			false,
			"Bubblegum Lollipop",
			"Bubblegum Lollipops",
			"A bright pink lollipop, with a little ball of gum at its core."
				+ " Although it doesn't look out of the ordinary, it's somewhat unusual in the fact that it has an incredibly strong smell of bubblegum.",
			"raceHarpyLollipop",
			Colour.RACE_HARPY,
			null,
			null,
			Rarity.RARE,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.RACE_LOLLIPOP)),
			Util.newArrayListOfValues(
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public boolean isTransformative() {
			return true;
		}
		
		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.RACE_HARPY;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return ELIXIR;
		}

		@Override
		public String getUseName() {
			return "suck";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"The moment you push the bright pink lollipop past your lips, your taste buds are overwhelmed by the sweet, sugary flavour of bubblegum."
						+ " Before you know what you're doing, you're letting out soft little feminine moans,"
							+ " which soon turn into desperate whines as you find yourself unable to think about anything other than wildly sucking on the object in your mouth.",
					"You pull out the 'Bubblegum Lollipop', and force [npc.name] to suck on it.",
					"[npc.Name] pulls out a 'Bubblegum Lollipop', and quickly shoves it into [npc.her] mouth.",
					"[npc.Name] pulls out a 'Bubblegum Lollipop', and, after quickly tearing off the plastic wrapper, [npc.she] forces it past your lips."
						+ " Before you know what you're doing, you're letting out soft little feminine moans,"
							+ " which soon turn into desperate whines as you find yourself unable to think about anything other than wildly sucking on the object in your mouth.");
		}
	};
	
	public static AbstractItemType RACE_INGREDIENT_SLIME = new AbstractItemType(2500,
			"a",
			false,
			"Biojuice Canister",
			"Biojuice Canisters",
			"A canister of glowing pink liquid, which has a thick, slimy consistency."
					+ " The warning sign on the front makes it quite clear that drinking this would be a bad idea...",
			"raceSlimeBiojuice",
			Colour.RACE_SLIME,
			null,
			null,
			Rarity.LEGENDARY,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.RACE_BIOJUICE)),
			Util.newArrayListOfValues(
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.NOT_FOR_SALE)) {


		@Override
		public boolean isTransformative() {
			return true;
		}
		
		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.RACE_SLIME;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return ELIXIR;
		}

		@Override
		public boolean isAbleToBeUsed(GameCharacter target) {
			return (!Main.game.isInCombat() || target.isPlayer()) && !target.isPregnant();
		}
		
		@Override
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "This item cannot be used on pregnant people!";
		}
		
		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Ignoring the warning on the front, you unseal one end of the canister and bring it up to your [pc.lips]."
							+ " The glowing pink liquid within gives off a faintly sweet smell, and you tilt your head back and gulp it all down...",
					"You unseal one end of the canister and bring it up to [npc.namePos] [npc.lips]."
							+ " Tilting [npc.her] head back, you force [npc.herHim] to drink down all of the glowing pink liquid...",
					"[npc.Name] pulls out a canister of Biojuice, and, unsealing one end of the canister, [npc.she] brings it up to [npc.her] [npc.lips] and gulps it all down....",
					"[npc.Name] pulls out a canister of Biojuice, and, unsealing one end of the canister, [npc.she] brings it up to your [pc.lips]."
							+ " The glowing pink liquid within gives off a faintly sweet smell, and [npc.name] tilts your head back and forces you to gulp it all down....");
		}
	};
	
	// Essence bottles:
	
//	BOTTLED_ESSENCE_ANGEL(
//			null,
//			false,
//			"Bottled Angel's Essence",
//			"A small glass bottle, with a little cork stopper wedged firmly in the top."
//					+ " Inside, there's a swirling  ",
//			"bottledEssenceAngel",
//			Colour.RARITY_LEGENDARY,
//			50,
//			Rarity.LEGENDARY,
//			null,
//			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_ANGEL))) {
//
//		@Override
//		public String getUseName() {
//			return "suck";
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			if (target.isPlayer()) {
//				return "<p>"
//							+ ""
//						+ "</p>";
//				
//			} else {
//				return "<p>"
//						+ "(You shouldn't be seeing this. x_x)"
//					+ "</p>";
//			}
//		}
//	};
	
	private static String getEssenceAbsorptionText(Colour essenceColour, GameCharacter user, GameCharacter target) {
			if (user!=null && user.isPlayer()) {
				if(target!=null) {
					if(target.isPlayer()) {
						if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.essenceBottledDiscovered)) {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.essenceBottledDiscovered);

							if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
								return "<p>"
											+ "Pulling the cork stopper out from the top of the little bottle, you let out a gasp as the swirling light instantly darts out of its glass prison."
											+ " Giving you no time to react, the essence immediately shoots straight towards you, and with a little "+essenceColour.getName()+" flash, it hits your chest and disappears from sight."
											+ " You think that it would probably be best to go and ask Lilaya about what just happened..."
										+ "</p>"
										+(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)?Main.game.getPlayer().startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY):"");
							} else {
								return "<p>"
										+ "Pulling the cork stopper out from the top of the little bottle, you let out a gasp as the swirling light instantly darts out of its glass prison."
										+ " Giving you no time to react, the essence immediately shoots straight towards you, and with a little "+essenceColour.getName()+" flash, it hits your chest and disappears from sight."
										+ " You suddenly remember what Lilaya told you about absorbing essences, and breathe a sigh of relief..."
									+ "</p>";
							}
						}

						if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
							return "<p>"
										+ "Pulling the cork stopper out from the top of the little bottle, you release the arcane essence from its glass prison."
										+ " Drawn towards your powerful arcane aura, the essence immediately darts towards you, and with a little "+essenceColour.getName()+" flash, it disappears from sight."
										+ " You think that it would probably be best to go and ask Lilaya about what just happened..."
									+ "</p>";
						} else {
							return "<p>"
									+ "Pulling the cork stopper out from the top of the little bottle, you release the arcane essence from its glass prison."
									+ " Drawn towards your powerful arcane aura, the essence immediately darts towards you, and with a little "+essenceColour.getName()+" flash, it disappears from sight."
									+ " You feel a subtle change in your aura, letting you know that you've successfully absorbed the essence."
								+ "</p>";
						}
						
					} else {
						return UtilText.parse(target,
								"<p>"
									+ "Pulling the cork stopper out from the top of the little bottle, you release the arcane essence from its glass prison."
									+ " Drawn towards [npc.namePos] powerful arcane aura, the essence immediately darts towards [npc.herHim], and with a little "
										+essenceColour.getName()+" flash, it disappears from sight as it's absorbed into [npc.her] aura."
								+ "</p>");
					}
				} else {
					return "";
				}
				
			} else {
				if(target!=null) {
					if(target.isPlayer()) {
						if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.essenceBottledDiscovered)) {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.essenceBottledDiscovered);

							if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
								return UtilText.parse(user,
										"<p>"
											+ "Pulling the cork stopper out from the top of the little bottle, [npc.name] releases an arcane essence from its glass prison."
											+ " Giving you no time to react, the essence immediately shoots straight towards you, and with a little "+essenceColour.getName()+" flash, it hits your chest and disappears from sight."
											+ " You think that it would probably be best to go and ask Lilaya about what just happened..."
										+ "</p>"
										+(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)?Main.game.getPlayer().startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY):""));
							} else {
								return UtilText.parse(user,
										"<p>"
											+ "Pulling the cork stopper out from the top of the little bottle, [npc.name] releases an arcane essence from its glass prison."
											+ " Giving you no time to react, the essence immediately shoots straight towards you, and with a little "+essenceColour.getName()+" flash, it hits your chest and disappears from sight."
											+ " You suddenly remember what Lilaya told you about absorbing essences, and breathe a sigh of relief..."
										+ "</p>");
							}
						}

						if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
							return UtilText.parse(user,
									"<p>"
										+ "Pulling the cork stopper out from the top of the little bottle, [npc.name] releases an arcane essence from its glass prison."
										+ " Drawn towards your powerful arcane aura, the essence immediately darts towards you, and with a little "+essenceColour.getName()+" flash, it disappears from sight."
										+ " You think that it would probably be best to go and ask Lilaya about what just happened..."
									+ "</p>");
						} else {
							return UtilText.parse(user,
									"<p>"
										+ "Pulling the cork stopper out from the top of the little bottle, [npc.name] releases an arcane essence from its glass prison."
									+ " Drawn towards your powerful arcane aura, the essence immediately darts towards you, and with a little "+essenceColour.getName()+" flash, it disappears from sight."
									+ " You feel a subtle change in your aura, letting you know that you've successfully absorbed the essence."
								+ "</p>");
						}
						
					} else {
						return UtilText.parse(user, 
								"<p>"
									+ "Pulling the cork stopper out from the top of the little bottle, [npc.name] releases an arcane essence from its glass prison."
									+ " Drawn towards [npc.her] powerful arcane aura, the essence immediately darts towards [npc.name], and with a little "
										+essenceColour.getName()+" flash, it disappears from sight as it's absorbed into [npc.her] aura."
								+"</p>");
					}
				} else {
					return "";
				}
			}
		
		
	}
	
	public static AbstractItemType BOTTLED_ESSENCE_ARCANE = new AbstractItemType(40,
			null,
			false,
			"Bottled Arcane Essence",
			"Bottled Arcane Essences",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.GENERIC_ARCANE.getName()+" glow of an arcane essence flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceArcane",
			Colour.GENERIC_ARCANE,
			null,
			null,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_ARCANE)),
			Util.newArrayListOfValues(ItemTag.ESSENCE)) {


		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getEssenceAbsorptionText(Colour.GENERIC_ARCANE, user, target);
		}
		
		public boolean isAbleToBeUsed(GameCharacter target) {
			return target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.TWO_SMART.getMinimumValue() || target.isPlayer();
		}
		
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "Only people who are at least proficient in the use of the arcane are able to absorb essences!";
		}
	};
	
	public static AbstractItemType BOTTLED_ESSENCE_CAT_MORPH = new AbstractItemType(
			50,
			null,
			false,
			"Bottled Cat-morph Essence",
			"Bottled Cat-morph Essences",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.RACE_CAT_MORPH.getName()+" glow of an arcane essence, imbued with the energy of a cat-morph, flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceCatMorph",
			Colour.RACE_CAT_MORPH,
			null,
			null,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_CAT_MORPH)),
			Util.newArrayListOfValues(ItemTag.ESSENCE)) {


		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getEssenceAbsorptionText(Colour.RACE_CAT_MORPH, user, target);
		}
		
		public boolean isAbleToBeUsed(GameCharacter target) {
			return target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.TWO_SMART.getMinimumValue() || target.isPlayer();
		}
		
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "Only people who are at least proficient in the use of the arcane are able to absorb essences!";
		}
	};
	

	public static AbstractItemType BOTTLED_ESSENCE_COW_MORPH = new AbstractItemType(
			50,
			null,
			false,
			"Bottled Cow-morph Essence",
			"Bottled Cow-morph Essences",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.RACE_COW_MORPH.getName()+" glow of an arcane essence, imbued with the energy of a cow-morph, flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceCowMorph",
			Colour.RACE_COW_MORPH,
			null,
			null,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_COW_MORPH)),
			Util.newArrayListOfValues(ItemTag.ESSENCE)) {


		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getEssenceAbsorptionText(Colour.RACE_COW_MORPH, user, target);
		}
		
		public boolean isAbleToBeUsed(GameCharacter target) {
			return target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.TWO_SMART.getMinimumValue() || target.isPlayer();
		}
		
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "Only people who are at least proficient in the use of the arcane are able to absorb essences!";
		}
	};
	
	public static AbstractItemType BOTTLED_ESSENCE_DEMON = new AbstractItemType(
			75,
			null,
			false,
			"Bottled Demon Essence",
			"Bottled Demon Essences",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.RACE_DEMON.getName()+" glow of an arcane essence, imbued with the energy of a demon, flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceDemon",
			Colour.RACE_DEMON,
			null,
			null,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_DEMON)),
			Util.newArrayListOfValues(ItemTag.ESSENCE)) {


		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getEssenceAbsorptionText(Colour.RACE_DEMON, user, target);
		}
		
		public boolean isAbleToBeUsed(GameCharacter target) {
			return target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.TWO_SMART.getMinimumValue() || target.isPlayer();
		}
		
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "Only people who are at least proficient in the use of the arcane are able to absorb essences!";
		}
	};

	public static AbstractItemType BOTTLED_ESSENCE_IMP = new AbstractItemType(
			40,
			null,
			false,
			"Bottled Imp Essence",
			"Bottled Imp Essences",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.RACE_IMP.getName()+" glow of an arcane essence, imbued with the energy of an imp, flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceImp",
			Colour.RACE_IMP,
			null,
			null,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_IMP)),
			Util.newArrayListOfValues(ItemTag.ESSENCE)) {


		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getEssenceAbsorptionText(Colour.RACE_IMP, user, target);
		}
		
		public boolean isAbleToBeUsed(GameCharacter target) {
			return target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.TWO_SMART.getMinimumValue() || target.isPlayer();
		}
		
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "Only people who are at least proficient in the use of the arcane are able to absorb essences!";
		}
	};
	
	public static AbstractItemType BOTTLED_ESSENCE_ALLIGATOR_MORPH = new AbstractItemType(
			50,
			null,
			false,
			"Bottled Alligator-morph Essence",
			"Bottled Alligator-morph Essences",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.RACE_ALLIGATOR_MORPH.getName()+" glow of an alligator-morph essence flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceGatorMorph",
			Colour.RACE_ALLIGATOR_MORPH,
			null,
			null,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_ALLIGATOR_MORPH)),
			Util.newArrayListOfValues(ItemTag.ESSENCE)) {


		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getEssenceAbsorptionText(Colour.RACE_ALLIGATOR_MORPH, user, target);
		}
	};
	

	public static AbstractItemType BOTTLED_ESSENCE_SQUIRREL_MORPH = new AbstractItemType(
			50,
			null,
			false,
			"Bottled Squirrel-morph Essence",
			"Bottled Squirrel-morph Essences",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.RACE_SQUIRREL_MORPH.getName()+" glow of an arcane essence, imbued with the energy of a squirrel-morph, flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceSquirrelMorph",
			Colour.RACE_SQUIRREL_MORPH,
			null,
			null,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_SQUIRREL_MORPH)),
			Util.newArrayListOfValues(ItemTag.ESSENCE)) {


		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getEssenceAbsorptionText(Colour.RACE_SQUIRREL_MORPH, user, target);
		}
		
		public boolean isAbleToBeUsed(GameCharacter target) {
			return target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.TWO_SMART.getMinimumValue() || target.isPlayer();
		}
		
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "Only people who are at least proficient in the use of the arcane are able to absorb essences!";
		}
	};
	
	public static AbstractItemType BOTTLED_ESSENCE_RAT_MORPH = new AbstractItemType(
			50,
			null,
			false,
			"Bottled Rat-morph Essence",
			"Bottled Rat-morph Essences",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.RACE_RAT_MORPH.getName()+" glow of an arcane essence, imbued with the energy of a rat-morph, flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceRatMorph",
			Colour.RACE_RAT_MORPH,
			null,
			null,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_RAT_MORPH)),
			Util.newArrayListOfValues(ItemTag.ESSENCE)) {


		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getEssenceAbsorptionText(Colour.RACE_RAT_MORPH, user, target);
		}
		
		public boolean isAbleToBeUsed(GameCharacter target) {
			return target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.TWO_SMART.getMinimumValue() || target.isPlayer();
		}
		
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "Only people who are at least proficient in the use of the arcane are able to absorb essences!";
		}
	};
	
	public static AbstractItemType BOTTLED_ESSENCE_RABBIT_MORPH = new AbstractItemType(
			50,
			null,
			false,
			"Bottled Rabbit-morph Essence",
			"Bottled Rabbit-morph Essences",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.RACE_RABBIT_MORPH.getName()+" glow of an arcane essence, imbued with the energy of a rabbit-morph, flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceRabbitMorph",
			Colour.RACE_RABBIT_MORPH,
			null,
			null,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_RABBIT_MORPH)),
			Util.newArrayListOfValues(ItemTag.ESSENCE)) {


		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getEssenceAbsorptionText(Colour.RACE_RABBIT_MORPH, user, target);
		}
		
		public boolean isAbleToBeUsed(GameCharacter target) {
			return target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.TWO_SMART.getMinimumValue() || target.isPlayer();
		}
		
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "Only people who are at least proficient in the use of the arcane are able to absorb essences!";
		}
	};
	
	public static AbstractItemType BOTTLED_ESSENCE_BAT_MORPH = new AbstractItemType(
			50,
			null,
			false,
			"Bottled Bat-morph Essence",
			"Bottled Bat-morph Essences",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.RACE_BAT_MORPH.getName()+" glow of an arcane essence, imbued with the energy of a bat-morph, flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceBatMorph",
			Colour.RACE_BAT_MORPH,
			null,
			null,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_BAT_MORPH)),
			Util.newArrayListOfValues(ItemTag.ESSENCE)) {


		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getEssenceAbsorptionText(Colour.RACE_BAT_MORPH, user, target);
		}
		
		public boolean isAbleToBeUsed(GameCharacter target) {
			return target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.TWO_SMART.getMinimumValue() || target.isPlayer();
		}
		
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "Only people who are at least proficient in the use of the arcane are able to absorb essences!";
		}
	};
	
	public static AbstractItemType BOTTLED_ESSENCE_DOG_MORPH = new AbstractItemType(
			50,
			null,
			false,
			"Bottled Dog-morph Essence",
			"Bottled Dog-morph Essences",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.RACE_DOG_MORPH.getName()+" glow of an arcane essence, imbued with the energy of a dog-morph, flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceDogMorph",
			Colour.RACE_DOG_MORPH,
			null,
			null,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_DOG_MORPH)),
			Util.newArrayListOfValues(ItemTag.ESSENCE)) {


		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getEssenceAbsorptionText(Colour.RACE_DOG_MORPH, user, target);
		}
		
		public boolean isAbleToBeUsed(GameCharacter target) {
			return target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.TWO_SMART.getMinimumValue() || target.isPlayer();
		}
		
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "Only people who are at least proficient in the use of the arcane are able to absorb essences!";
		}
	};
	
	public static AbstractItemType BOTTLED_ESSENCE_HARPY = new AbstractItemType(
			50,
			null,
			false,
			"Bottled Harpy Essence",
			"Bottled Harpy Essences",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.RACE_HARPY.getName()+" glow of an arcane essence, imbued with the energy of a harpy, flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceHarpy",
			Colour.RACE_HARPY,
			null,
			null,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_HARPY)),
			Util.newArrayListOfValues(ItemTag.ESSENCE)) {


		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getEssenceAbsorptionText(Colour.RACE_HARPY, user, target);
		}
		
		public boolean isAbleToBeUsed(GameCharacter target) {
			return target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.TWO_SMART.getMinimumValue() || target.isPlayer();
		}
		
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "Only people who are at least proficient in the use of the arcane are able to absorb essences!";
		}
	};
	
	public static AbstractItemType BOTTLED_ESSENCE_HORSE_MORPH = new AbstractItemType(
			50,
			null,
			false,
			"Bottled Horse-morph Essence",
			"Bottled Horse-morph Essences",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.RACE_HORSE_MORPH.getName()+" glow of an arcane essence, imbued with the energy of a horse-morph, flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceHorseMorph",
			Colour.RACE_HORSE_MORPH,
			null,
			null,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_HORSE_MORPH)),
			Util.newArrayListOfValues(ItemTag.ESSENCE)) {


		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getEssenceAbsorptionText(Colour.RACE_HORSE_MORPH, user, target);
		}
		
		public boolean isAbleToBeUsed(GameCharacter target) {
			return target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.TWO_SMART.getMinimumValue() || target.isPlayer();
		}
		
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "Only people who are at least proficient in the use of the arcane are able to absorb essences!";
		}
	};
	
	public static AbstractItemType BOTTLED_ESSENCE_REINDEER_MORPH = new AbstractItemType(
			50,
			null,
			false,
			"Bottled Reindeer-morph Essence",
			"Bottled Reindeer-morph Essences",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.RACE_REINDEER_MORPH.getName()+" glow of an arcane essence, imbued with the energy of a reindeer-morph, flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceReindeerMorph",
			Colour.RACE_REINDEER_MORPH,
			null,
			null,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_REINDEER_MORPH)),
			Util.newArrayListOfValues(ItemTag.ESSENCE)) {


		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getEssenceAbsorptionText(Colour.RACE_REINDEER_MORPH, user, target);
		}
		
		public boolean isAbleToBeUsed(GameCharacter target) {
			return target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.TWO_SMART.getMinimumValue() || target.isPlayer();
		}
		
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "Only people who are at least proficient in the use of the arcane are able to absorb essences!";
		}
	};
	
	public static AbstractItemType BOTTLED_ESSENCE_HUMAN = new AbstractItemType(
			50,
			null,
			false,
			"Bottled Human Essence",
			"Bottled Human Essences",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.RACE_HUMAN.getName()+" glow of an arcane essence, imbued with the energy of a human, flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceHuman",
			Colour.RACE_HUMAN,
			null,
			null,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_HUMAN)),
			Util.newArrayListOfValues(ItemTag.ESSENCE)) {


		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getEssenceAbsorptionText(Colour.RACE_HUMAN, user, target);
		}
		
		public boolean isAbleToBeUsed(GameCharacter target) {
			return target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.TWO_SMART.getMinimumValue() || target.isPlayer();
		}
		
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "Only people who are at least proficient in the use of the arcane are able to absorb essences!";
		}
	};
	
	public static AbstractItemType BOTTLED_ESSENCE_WOLF_MORPH = new AbstractItemType(
			50,
			null,
			false,
			"Bottled Wolf-morph Essence",
			"Bottled Wolf-morph Essences",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.RACE_WOLF_MORPH.getName()+" glow of an arcane essence, imbued with the energy of a wolf-morph, flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceWolfMorph",
			Colour.RACE_WOLF_MORPH,
			null,
			null,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_WOLF_MORPH)),
			Util.newArrayListOfValues(ItemTag.ESSENCE)) {


		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getEssenceAbsorptionText(Colour.RACE_WOLF_MORPH, user, target);
		}
		
		public boolean isAbleToBeUsed(GameCharacter target) {
			return target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.TWO_SMART.getMinimumValue() || target.isPlayer();
		}
		
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "Only people who are at least proficient in the use of the arcane are able to absorb essences!";
		}
	};
	
	public static AbstractItemType BOTTLED_ESSENCE_FOX_MORPH = new AbstractItemType(
			50,
			null,
			false,
			"Bottled Fox-morph Essence",
			"Bottled Fox-morph Essences",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.RACE_FOX_MORPH.getName()+" glow of an arcane essence, imbued with the energy of a fox-morph, flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceFoxMorph",
			Colour.RACE_FOX_MORPH,
			null,
			null,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_FOX_MORPH)),
			Util.newArrayListOfValues(ItemTag.ESSENCE)) {


		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getEssenceAbsorptionText(Colour.RACE_FOX_MORPH, user, target);
		}
		
		public boolean isAbleToBeUsed(GameCharacter target) {
			return target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.TWO_SMART.getMinimumValue() || target.isPlayer();
		}
		
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "Only people who are at least proficient in the use of the arcane are able to absorb essences!";
		}
	};

	public static AbstractItemType BOTTLED_ESSENCE_SLIME = new AbstractItemType(
			50,
			null,
			false,
			"Bottled Slime Essence",
			"Bottled Slime Essences",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.RACE_SLIME.getName()+" glow of an arcane essence, imbued with the energy of a slime, flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceSlime",
			Colour.RACE_SLIME,
			null,
			null,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_SLIME)),
			Util.newArrayListOfValues(ItemTag.ESSENCE)) {


		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getEssenceAbsorptionText(Colour.RACE_SLIME, user, target);
		}
		
		public boolean isAbleToBeUsed(GameCharacter target) {
			return target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.TWO_SMART.getMinimumValue() || target.isPlayer();
		}
		
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "Only people who are at least proficient in the use of the arcane are able to absorb essences!";
		}
	};
	
	
	
	// Specials:
	
	public static AbstractItemType HARPY_MATRIARCH_BIMBO_LOLLIPOP = new AbstractItemType(1250,
			null,
			false,
			"[bimboHarpy.namePos] lollipop",
			"[bimboHarpy.namePos] lollipops",
			"A swirly lollipop that you got from the harpy matriarch [bimboHarpy.name]."
				+ " Although it doesn't look out of the ordinary, you're pretty sure that eating it would result in a potent transformation...",
			"bimboLollipop",
			Colour.RARITY_LEGENDARY,
			null,
			null,
			Rarity.LEGENDARY,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BIMBO_LOLLIPOP)),
			Util.newArrayListOfValues(
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.NOT_FOR_SALE)) {

		
		@Override
		public boolean isTransformative() {
			return true;
		}

		@Override
		public String getUseName() {
			return "suck";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Bringing the lollipop up to your [pc.lips+], you dart out your [pc.tongue] and give it a long, wet lick."
						+ " An intense, sweet flavour fills your mouth, quite unlike anything you've ever tasted before."
						+ " Before you know what you're doing, you're pressing your [pc.lips] up against the delicious candy, letting out little whining noises as you find yourself unable to stop sucking and licking it...",
					"Bringing the lollipop up to [npc.namePos] [npc.lips+], you smile as [npc.she] darts out [npc.her] [npc.tongue] to give it a long, wet lick."
						+ " The intoxicating taste quickly overwhelms [npc.her] senses, and [npc.she] eagerly presses [npc.her] [npc.lips] up against the delicious candy,"
						+ " letting out little whining noises as [npc.she] finds [npc.herself] unable to stop sucking and licking it...",
					"[npc.Name] produces a swirly lollipop and, after quickly pulling off the wrapper, starts licking and sucking it...",
					"[npc.Name] produces a swirly lollipop and, after quickly pulling off the wrapper, forces it against your [pc.lips] and into your mouth."
						+ " An intense, sweet flavour fills hits your tongue, quite unlike anything you've ever tasted before."
						+ " Before you know what you're doing, you're pressing your [pc.lips] up against the delicious candy, letting out little whining noises as you find yourself unable to stop sucking and licking it...");

		}
	};
	
	public static AbstractItemType HARPY_MATRIARCH_NYMPHO_LOLLIPOP = new AbstractItemType(1250,
			null,
			false,
			"[nymphoHarpy.namePos] lollipop",
			"[nymphoHarpy.namePos] lollipops",
			"A cock-shaped lollipop that you got from the harpy matriarch [nymphoHarpy.name]."
				+ " Although it looks to be made from regular candy, you're pretty sure that eating it would result in a potent transformation...",
			"nymphoLollipop",
			Colour.RARITY_LEGENDARY,
			null,
			null,
			Rarity.LEGENDARY,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.NYMPHO_LOLLIPOP)),
			Util.newArrayListOfValues(
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.NOT_FOR_SALE)) {

		
		@Override
		public boolean isTransformative() {
			return true;
		}

		@Override
		public String getUseName() {
			return "suck";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Bringing the lollipop up to your [pc.lips+], you dart out your [pc.tongue] and give it a long, wet lick."
						+ " An intense, sweet flavour fills your mouth, quite unlike anything you've ever tasted before."
						+ " Before you know what you're doing, you're pushing the delicious, cock-shaped candy into your mouth, letting out lewd moans as you find yourself unable to stop sucking and licking it...",
					"Bringing the lollipop up to [npc.namePos] [npc.lips+], you smile as [npc.she] darts out [npc.her] [npc.tongue] to give it a long, wet lick."
						+ " The intoxicating taste quickly overwhelms [npc.her] senses, and [npc.she] eagerly wraps [npc.her] [npc.lips] around the delicious, cock-shaped candy,"
							+ " letting out lewd moans as [npc.she] finds [npc.herself] unable to stop sucking and licking it...",
					"[npc.Name] produces a cock-shaped lollipop and, after quickly pulling off the wrapper, starts licking and sucking it...",
					"[npc.Name] produces a cock-shaped lollipop and, after quickly pulling off the wrapper, forces it against your [pc.lips] and into your mouth."
							+ " An intense, sweet flavour fills your mouth, quite unlike anything you've ever tasted before."
							+ " Before you know what you're doing, you're pushing the delicious, cock-shaped candy into your mouth, letting out lewd moans as you find yourself unable to stop sucking and licking it...");
		}
	};
	
	public static AbstractItemType HARPY_MATRIARCH_DOMINANT_PERFUME = new AbstractItemType(1250,
			null,
			false,
			"[dominantHarpy.namePos] perfume",
			"[dominantHarpy.namePos] perfumes",
			"A bottle of perfume that you got from the harpy matriarch [dominantHarpy.name]."
				+ " Although it looks to contain normal perfume, you're pretty sure that using it would result in a potent transformation...",
			"dominantPerfume",
			Colour.RARITY_LEGENDARY,
			null,
			null,
			Rarity.LEGENDARY,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.DOMINANT_PERFUME)),
			Util.newArrayListOfValues(
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.NOT_FOR_SALE)) {

		
		@Override
		public boolean isTransformative() {
			return true;
		}
		
		@Override
		public String getUseName() {
			return "spray";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Bringing the bottle of perfume up to your neck, you give it a little squirt."
							+ " Although only a small amount of liquid shoots out, the entire bottle's contents are instantly drained, leaving you holding an empty vessel."
							+ " As you look down at it in surprise, the strong, feminine scent rises up to overpower your senses,"
							+ " and you find yourself letting out a desperate moan as the nature of the perfume's powerful enchantment starts to make itself known...",
					"Bringing the bottle of perfume up to [npc.namePos] neck, you give it a little squirt."
							+ " Although only a small amount of liquid shoots out, the entire bottle's contents are instantly drained, leaving you holding an empty vessel."
							+ " As you look down at it in surprise, the strong, feminine scent rises up to overpower [npc.namePos] senses,"
							+ " and [npc.she] finds [npc.herself] letting out a desperate moan as the nature of the perfume's powerful enchantment starts to make itself known...",
					"[npc.Name] produces a bottle of perfume and, after quickly pulling off the cap, squirts some onto [npc.her] neck...",
					"[npc.Name] produces a bottle of perfume and, after quickly pulling off the cap, squirts some onto your neck."
						+ " Although only a small amount of liquid shoots out, the entire bottle's contents are instantly drained, leaving [npc.name] holding an empty vessel."
						+ " As you look down at it in surprise, the strong, feminine scent rises up to overpower your senses,"
							+ " and you find yourself letting out a desperate moan as the nature of the perfume's powerful enchantment starts to make itself known...");
			
		}
	};
	
	
	// Crafting outputs:
	
	public static AbstractItemType POTION = new AbstractItemType(750,
			"",
			false,
			"potion",
			"potions",
			"Created by infusing arcane essences with a consumable item, potions such as these can hold a huge number of restorative effects or performance enhancements."
					+ " As potion creation is limited to those with a high level of arcane proficiency, such as demons, they are quite rare, and fetch a high price.",
			"refined_potion_container",
			Colour.CLOTHING_PINK,
			null,
			null,
			Rarity.RARE,
			null,
			null, null) {


		@Override
		public boolean isTransformative() {
			return false;
		}
		
		@Override
		public boolean isAbleToBeUsedInSex() {
			return true;
		}

		@Override
		public boolean isAbleToBeUsedInCombat() {
			return true;
		}
		
		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"First removing the bottle's stopper, you then bring the potion up to your waiting lips."
						+ " A sweet smell rises from the opening, and, after gulping down the delicious liquid, you feel a strange tingling feeling spreading throughout your body as the potion's effects start to make themselves known...",
					"First removing the bottle's stopper, you then bring the potion up to [npc.namePos] waiting lips, before forcing [npc.herHim] to drink it all down.",
					"[npc.Name] pulls out a potion of some sort, and, after quickly removing the bottle's stopper, [npc.she] promptly gulps downs the contents.",
					"[npc.Name] brings the potion to your lips, before tilting your head back and forcing you to quickly gulp down the contents."
						+ " You feel a strange tingling feeling spreading throughout your body as the potion's effects start to make themselves known...");
		}
	};
	
	public static AbstractItemType ELIXIR = new AbstractItemType(1500,
			"",
			false,
			"elixir",
			"elixirs",
			"Created by infusing arcane essences with a consumable item, elixirs such as these can hold a huge number of transformative effects."
					+ " As elixir creation is limited to those with a high level of arcane proficiency, such as demons, they are quite rare, and fetch a high price.",
			"refined_elixir_container",
			Colour.CLOTHING_PINK,
			null,
			null,
			Rarity.EPIC,
			null,
			null, null) {

		
		@Override
		public boolean isTransformative() {
			return true;
		}
		
		@Override
		public boolean isAbleToBeUsedInSex() {
			return true;
		}

		@Override
		public boolean isAbleToBeUsedInCombat() {
			return true;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"First removing the bottle's stopper, you then bring the elixir up to your waiting lips."
						+ " A sweet smell rises from the opening, and, after gulping down the delicious liquid, you feel a strange tingling feeling spreading throughout your body as the elixir's effects start to make themselves known...",
					"First removing the bottle's stopper, you then bring the elixir up to [npc.namePos] waiting lips, before forcing [npc.herHim] to drink it all down.",
					"[npc.Name] pulls out an elixir of some sort, and, after quickly removing the stopper, [npc.she] promptly gulps downs the contents.",
					"[npc.Name] brings the elixir to your lips, before tilting your head back and forcing you to quickly gulp down the contents."
						+ " You feel a strange tingling feeling spreading throughout your body as the elixir's effects start to make themselves known...");
		}
	};
	
	
	// Non-TF:

	public static AbstractItemType DYE_BRUSH = new AbstractItemType(150,
			"a",
			false,
			"dye-brush",
			"dye-brushes",
			"A small, very ordinary-looking brush, of the sort used for fine detailing on canvas or models."
					+ " On closer inspection, you notice a very faint purple glow emanating from the brush's tip, revealing its true nature as an arcane-enchanted dye-brush.",
			"dyeBrush",
			Colour.CLOTHING_WHITE,
			null,
			null,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.DYE_BRUSH)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN)) {


		@Override
		public String getUseName() {
			return "use";
		}
		
		@Override
		public boolean isAbleToBeUsedInSex() {
			return false;
		}

		@Override
		public boolean isAbleToBeUsedInCombat() {
			return false;
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "<p>"
						+ "As you take hold of the Dye-brush, you see the purple glow around the tip growing in strength."
						+ " The closer you move it to a piece of clothing, the brighter the glow becomes, until suddenly, images of different colours start flashing through your mind."
						+ " As you touch the bristles to the piece of clothing, the Dye-brush instantly evaporates!"
						+ " You see that the arcane enchantment has dyed your piece of clothing the colour you wanted."
					+ "</p>";
		}

		@Override
		public boolean isAbleToBeUsedFromInventory() {
			return false;
		}
	};
	
	public static AbstractItemType REFORGE_HAMMER = new AbstractItemType(150,
			"a",
			false,
			"reforging hammer",
			"reforging hammers",
			"A small hammer, with a solid metal head and wooden shaft."
					+ " It has been imbued with a unique arcane enchantment, which has not only made it as light as a feather, but has also granted it the ability to instantly reforge any weapon.",
			"reforge_hammer",
			Colour.CLOTHING_WHITE,
			null,
			null,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.REFORGE_HAMMER)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN)) {


		@Override
		public String getUseName() {
			return "use";
		}
		
		@Override
		public boolean isAbleToBeUsedInSex() {
			return false;
		}

		@Override
		public boolean isAbleToBeUsedInCombat() {
			return false;
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "<p>"
						+ "As you take hold of the reforging hammer, you see the metal head start to emit a deep purple glow."
						+ " The closer you move it to a weapon, the brighter this glow becomes, until suddenly, images of different damage types start flashing through your mind."
						+ " As you touch the metal head  to the weapon of your choice, the reforge hammer instantly evaporates!"
						+ " You see that the arcane enchantment has reforged the weapon into the damage type you wanted."
					+ "</p>";
		}

		@Override
		public boolean isAbleToBeUsedFromInventory() {
			return false;
		}
	};

	public static AbstractItemType CONDOM_USED = new AbstractItemType(1,
			"a",
			false,
			"used condom",
			"used condoms",
			"A used condom, tied at the top and filled with someone's cum. You'd have to be pretty dirty-minded to think of a use for this... <b>(Currently not implemented...)</b>",
			"condomUsed",
			Colour.CLOTHING_WHITE,
			null,
			null,
			Rarity.COMMON,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.USED_CONDOM_DRINK)),
			Util.newArrayListOfValues(
					ItemTag.REMOVE_FROM_DEBUG_SPAWNER)) {
		

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Untying the top of the used condom, you bring it up to your lips and swallow the slimy contents.",
					"Untying the top of the used condom, you bring it up to [npc.namePos] [npc.lips], and force [npc.her] to swallow the slimy contents.",
					"Untying the top of the used condom, [npc.name] brings it up to [npc.her] [npc.lips], and swallows the slimy contents.",
					"Untying the top of the used condom, [npc.name] brings it up to your [pc.lips], and forces you to swallow the slimy contents.");
		}

		@Override
		public boolean isAbleToBeUsed(GameCharacter target) {
			return true;
		}

		@Override
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "You can't think of a use for this. Maybe it's best to throw it away...<br/>"
					+ "(You need have at least a <b style='color:"+CorruptionLevel.THREE_DIRTY.getColour().toWebHexString()+";'>"+CorruptionLevel.THREE_DIRTY.getName()+"</b> level of corruption to know how to use this!)";
		}

		@Override
		public boolean isAbleToBeUsedInCombat() {
			return false;
		}

		@Override
		public boolean isAbleToBeUsedInSex() {
			return false;
		}
	};
	
	public static AbstractItemType ORIENTATION_HYPNO_WATCH = new AbstractItemType(50000,
			"a",
			false,
			"Hypno-Watch",
			"Hypno-Watches",
			"A unique, incredibly-powerful arcane instrument. When enchanted, this Hypno-Watch has the ability to change a person's sexual orientation, at the cost of increasing their corruption...",
			"hypnoClockBase",
			Colour.ANDROGYNOUS,
			null,
			null,
			Rarity.QUEST,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.ORIENTATION_CHANGE)), null) {

		
		@Override
		public boolean isFetishGiving() {
			return true;
		}
		
		@Override
		public String getDeterminer() {
			return UtilText.generateSingularDeterminer(this.getName(false));
		}
		
		@Override
		public int getEnchantmentLimit() {
			return 1;
		}
		
		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ORIENTATION_CHANGE;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return ORIENTATION_HYPNO_WATCH;
		}
		
		@Override
		public String getUseName() {
			return "hypnotise";
		}
		
		@Override
		public String getUseTooltipDescription(GameCharacter user, GameCharacter target) {
			if(user.equals(target)) {
				return "Use the " + getName(false) + " to hypnotise yourself.";
			} else {
				return UtilText.parse(target, "Use the " + getName(false) + " to hypnotise [npc.name].");
			}
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Taking hold of the delicate chain, you start slowly swinging the Hypno-Watch back and forth, fixating your gaze on the swirling face as you allow the item's arcane power to seep into your mind...",
					"Taking hold of the delicate chain, you start slowly swinging the Hypno-Watch back and forth in front of [npc.namePos] face, and,"
							+ " just as you'd hoped, [npc.she] fixates [npc.her] gaze on the swirling face, allowing the item's arcane power to seep into [npc.her] mind...",
					"Taking hold of the delicate chain, [npc.name] starts slowly swinging the Hypno-Watch back and forth, fixating [npc.her] gaze on the swirling face as [npc.she] allows the item's arcane power to seep into [npc.her] mind...",
					"Taking hold of the delicate chain, [npc.name] starts slowly swinging the Hypno-Watch back and forth in front of your face,"
							+ " and you find yourself unable to do anything but fixate your gaze on the swirling face as the item's arcane power seeps into your mind...");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	
	public static AbstractItemType VIXENS_VIRILITY = new AbstractItemType(20,
			"a",
			false,
			"Vixen's Virility",
			"Vixen's Virilities",
			"A small pill, packaged in a little foil and plastic wrapper. On the front of the foil, there's a small stylised picture of a heavily pregnant girl, lying back and smiling as she strokes her swollen belly.",
			"vixensVirility",
			Colour.CLOTHING_PINK,
			null,
			null,
			Rarity.COMMON,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.VIXENS_VIRILITY)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.BAT_CAVERNS_SPAWN,
					ItemTag.ATTRIBUTE_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public String getUseName() {
			return "swallow";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Popping the little pink pill out of its foil wrapper, you quickly put it in your mouth and swallow it down.",
					"Popping the little pink pill out of its foil wrapper, you bring it up to [npc.namePos] [npc.lips], before forcing it into [npc.her] mouth and making sure that [npc.she] swallows it down.",
					"[npc.Name] pops a Vixen's Virility pill out of its little foil wrapper, before quickly placing it in [npc.her] mouth and swallowing it down.",
					"[npc.Name] pops a Vixen's Virility pill out of its little foil wrapper, before bringing it up to your [pc.lips], forcing it into your mouth, and making sure that you swallow it down.");
		}

	};
	
	public static AbstractItemType PROMISCUITY_PILL = new AbstractItemType(20,
			"a",
			false,
			"Promiscuity Pill",
			"Promiscuity Pills",
			"Commonly referred to as 'slut pills', this promiscuity pill is packaged in a foil and plastic wrapper."
					+ " On the front of the foil, there's a before-and-after picture of a girl's hungry pussy overflowing with cum."
					+ " The after image is of the girl showing off her flat stomach as she gives a thumbs up.",
			"vixensVirility",
			Colour.CLOTHING_BLUE,
			null,
			null,
			Rarity.COMMON,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.PROMISCUITY_PILL)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.BAT_CAVERNS_SPAWN,
					ItemTag.ATTRIBUTE_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public String getUseName() {
			return "swallow";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Popping the little blue pill out of its foil wrapper, you quickly put it in your mouth and swallow it down.",
					"Popping the little blue pill out of its foil wrapper, you bring it up to [npc.namePos] [npc.lips], before forcing it into [npc.her] mouth and making sure that [npc.she] swallows it down.",
					"[npc.Name] pops a Promiscuity pill out of its little foil wrapper, before quickly placing it in [npc.her] mouth and swallowing it down.",
					"[npc.Name] pops a Promiscuity pill out of its little foil wrapper, before bringing it up to your [pc.lips], forcing it into your mouth, and making sure that you swallow it down.");
		}
	};
	
	public static AbstractItemType MOO_MILKER_EMPTY = new AbstractItemType(50,
			"a",
			false,
			"Moo Milker",
			"Moo Milkers",
			"A manual cow-themed breast pump, capable of holding up to "+Units.fluid(1000)+" of liquid in the attached plastic beaker.",
			"breastPump",
			Colour.BASE_PURPLE_LIGHT,
			null,
			null,
			Rarity.COMMON,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.MOO_MILKER)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.BAT_CAVERNS_SPAWN,
					ItemTag.SOLD_BY_RALPH)) {

		
		@Override
		public String getUseName() {
			return "milk";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Bringing the Moo Milker up to your breast, you place the suction cup over your [pc.nipple], before starting to pump the lever up and down."
							+ " Your [pc.milk+] starts to squirt out into the attached beaker, and you can't help but let out a deeply satisfied sigh at the delightful sensation of milking yourself.",
					"Bringing the Moo Milker up to [npc.namePos] breast, you place the suction cup over [npc.her] [npc.nipple], before starting to pump the lever up and down."
							+ " [npc.Her] [npc.milk+] starts to squirt out into the attached beaker, and [npc.she] can't help but let out a deeply satisfied sigh at the delightful sensation of being milked.",
					"Bringing a Moo Milker up to [npc.her] breast, [npc.name] places the suction cup over [npc.her] [npc.nipple], before starting to pump the lever up and down."
							+ " [npc.Her] [npc.milk+] starts to squirt out into the attached beaker, and [npc.she] can't help but let out a deeply satisfied sigh at the delightful sensation of milking [npc.herself].",
					"Bringing a Moo Milker up to your breast, [npc.name] places the suction cup over your [pc.nipple], before starting to pump the lever up and down."
							+ " Your [pc.milk+] starts to squirt out into the attached beaker, and you can't help but let out a deeply satisfied sigh at the delightful sensation of being milked.");
		}

		@Override
		public boolean isAbleToBeUsed(GameCharacter target) {
			return target.isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true) && target.getBreastRawMilkStorageValue()>=5;
		}

		@Override
		public String getUnableToBeUsedDescription(GameCharacter target) {
			if(target.isPlayer()) {
				if(!target.isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
					return "You need to be able to access your nipples in order to use this!";
				} else {
					return "You need to have at least "+Units.fluid(5)+" of milk stored in your breasts in order to use this!";
				}
				
			} else {
				if(!target.isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
					return UtilText.parse(target, "You need to be able to access [npc.namePos] nipples in order to use this!");
				} else {
					return UtilText.parse(target, "[npc.Name] needs to have at least "+Units.fluid(5)+" of milk stored in [npc.her] breasts in order to use this!");
				}
			}
		}
	};
	
	public static AbstractItemType MOO_MILKER_FULL = new AbstractItemType(150,
			"a",
			false,
			"Filled Moo Milker",
			"Filled Moo Milkers",
			"A manual cow-themed breast pump."
					+ " The attached plastic beaker has been filled with milk, and, by unscrewing the pumping mechanism on top, you can gain access to the liquid at any time.",
			"breastPumpFilled",
			Colour.BASE_PURPLE_LIGHT,
			null,
			null,
			Rarity.COMMON,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.FILLED_MOO_MILKER_DRINK)),
			Util.newArrayListOfValues(
					ItemTag.REMOVE_FROM_DEBUG_SPAWNER)) {
		

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You unscrew the top of the breast pump, and, bringing it up to your lips, you gulp down the contents.",
					"You unscrew the top of the breast pump, and, bringing it up to [npc.namePos] [npc.lips], you force [npc.her] to gulp down the contents.",
					"Unscrewing the top of the breast pump, [npc.name] brings it up to [npc.her] [npc.lips], before swallowing down the contents.",
					"Unscrewing the top of the breast pump, [npc.name] brings it up to your [pc.lips], before forcing you to gulp down the contents.");
		}
	};
	
	public static AbstractItemType PREGNANCY_TEST = new AbstractItemType(100,
			"an",
			false,
			"Arcane Pregnancy Tester",
			"Arcane Pregnancy Testers",
			"A small plastic wand, no longer than 15cm, which has a digital readout embedded in the middle."
					+ " The small instruction leaflet that came with it says to 'swipe the tester over the target's stomach to find out who the father is!'",
			"pregnancy_test",
			Colour.CLOTHING_WHITE,
			Colour.GENERIC_ARCANE,
			null,
			Rarity.COMMON,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.PREGNANCY_TEST)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public String getUseName() {
			return "use";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You swipe the pregnancy tester over your stomach, waiting until you hear it beep before bringing it up to take a look at the readout.",
					"You swipe the pregnancy tester over [npc.namePos] stomach, waiting until you hear it beep before bringing it up to take a look at the readout.",
					"[npc.Name] swipes the pregnancy tester over [npc.her] stomach, waiting until [npc.she] hears it beep before bringing it up to take a look at the readout.",
					"[npc.Name] swipes the pregnancy tester over your stomach, waiting until [npc.she] hears it beep before bringing it up to take a look at the readout.");
		}
	};
	
	public static AbstractItemType MOTHERS_MILK = new AbstractItemType(100,
			"a bottle of",
			false,
			"Mother's Milk",
			"Mother's Milks",
			"A baby bottle filled with a rich, creamy milk."
			+ " On the side, a little sticker declares that this drink is able to speed up your pregnancy.",
			"mothers_milk",
			Colour.CLOTHING_WHITE,
			null,
			null,
			Rarity.COMMON,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.MOTHERS_MILK)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.MISC_TF_ITEM,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public String getUseName() {
			return "drink";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Bringing the bottle up to your [pc.lips], you take the teat-like opening into your mouth, before greedily starting to suckle down the creamy liquid within.",
					"Bringing the bottle up to [npc.namePos] [npc.lips], you push the teat-like opening into [npc.her] mouth, before forcing [npc.herHim] to suckle down the creamy liquid within.",
					"[npc.Name] produces a bottle of 'Mother's Milk', and, taking the teat-like opening into [npc.her] mouth, [npc.she] greedily starts to suckle down the creamy liquid within.",
					"[npc.Name] produces a bottle of 'Mother's Milk', and, pushing the teat-like opening into your mouth, [npc.she] forces you to suckle down the creamy liquid within.");
		}
	};
	
	public static AbstractItemType PRESENT = new AbstractItemType(250,
			"a",
			false,
			"Yuletide Gift",
			"Yuletide Gift",
			"A wrapped present, sold by one of the reindeer-morph overseers in Dominion. It contains a random item from their store, and can also be given as a gift to your offspring, slaves, or Lilaya.",
			"present",
			Colour.GENERIC_ARCANE,
			null,
			null,
			Rarity.RARE,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.PRESENT)), null) {

		
		@Override
		public boolean isAbleToBeUsed(GameCharacter target) {
			return !(target.isInventoryFull() && Main.game.getPlayerCell().getInventory().isInventoryFull());
		}

		@Override
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "There's no space in your inventory or on the ground for whatever item is contained within!";
		}
		
		@Override
		public String getUseName() {
			return "open";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You untie the ribbon and peel off the wrapping paper, before opening the box to discover what's inside...",
					"You force [npc.name] to untie the ribbon and peel off the wrapping paper, before getting [npc.herHim] to open the box to discover what's inside...",
					"[npc.Name] produces a present, and then proceeds to untie the ribbon and peel off the wrapping paper, before opening the box to discover what's inside...",
					"[npc.Name] produces a present, and then proceeds to make you untie the ribbon and peel off the wrapping paper, before getting you to open the box to discover what's inside...");
		}
	};
	
	public static AbstractItemType GIFT_ROSE = new AbstractItemType(
			100,
			null,
			false,
			"Rose",
			"Roses",
			"A bouquet filled with roses of many colours, it smells pleasant even from a distance."
				+ " [Ashley.speech(Just in case you're clueless to the point that you don't even know the favourite colour of your intended recipient, every natural colour is included here.)]",
			//				+ " If their favourite happens to be blue, tough luck; maybe you should try getting acquainted with another species of flower instead of going with what's safe.)] ",
			"giftRose",
			Colour.BASE_CRIMSON,
			Colour.BASE_GREEN_DARK,
			null,
			Rarity.UNCOMMON,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.GIFT)) {


		@Override
		public String getDescription() {
			return "A single, red rose."
					+ " You imagine that if it were any other colour, it would smell just as sweet.";
		}
		
		@Override
		public String getUseName() {
			return "smell";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You take a smell of the delicate perfume given off by the red rose.",
					"You get [npc.name] to take a smell of the delicate perfume given off by the red rose.",
					"[npc.Name] takes a smell of the delicate perfume given off by the red rose.",
					"[npc.Name] gets you to take a smell of the delicate perfume given off by the red rose.");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	
	public static AbstractItemType GIFT_ROSE_BOUQUET = new AbstractItemType(
			500,
			null,
			false,
			"Rose Bouquet",
			"Rose Bouquets",
			"A bouquet filled with roses of many colours, it smells pleasant even from a distance."
				+ " [Ashley.speech(Just in case you're clueless to the point that you don't even know the favourite colour of your intended recipient, every natural colour is included here.)]",
			//				+ " If their favourite happens to be blue, tough luck; maybe you should try getting acquainted with another species of flower instead of going with what's safe.)] ",
			"giftRoseBouquet",
			Colour.BASE_RED,
			Colour.BASE_ORANGE,
			Colour.BASE_YELLOW,
			Rarity.UNCOMMON,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.GIFT)) {


		@Override
		public String getDescription() {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP)) {
				return "A bouquet filled with roses of many colours, it smells pleasant even from a distance."
						+ " [Ashley.speech(Just in case you're clueless to the point that you don't even know the favourite colour of your intended recipient, every natural colour is included here.)]";
			} else {
				return "A bouquet filled with roses of many colours, it smells pleasant even from a distance.";
			}
		}
		
		@Override
		public String getUseName() {
			return "smell";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You take a smell of the delicate perfume given off by the rose bouquet.",
					"You get [npc.name] to take a smell of the delicate perfume given off by the rose bouquet.",
					"[npc.Name] takes a smell of the delicate perfume given off by the rose bouquet.",
					"[npc.Name] gets you to take a smell of the delicate perfume given off by the rose bouquet.");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	
	public static AbstractItemType GIFT_CHOCOLATES = new AbstractItemType(
			300,
			"a box of",
			true,
			"Chocolates",
			"Chocolates",
			"A box filled with various chocolates. [Ashley.speech(Generic, but tasty. Yeah, go ahead and pretend that you're buying this for someone other than yourself.)]",
			"giftChocolates",
			Colour.BASE_TAN,
			Colour.BASE_BROWN_DARK,
			Colour.BASE_YELLOW,
			Rarity.UNCOMMON,
			null,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.GIFT_CHOCOLATES)),
			Util.newArrayListOfValues(ItemTag.GIFT)) {


		@Override
		public String getDescription() {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP)) {
				return "A box filled with various chocolates. [Ashley.speech(Generic, but tasty. Yeah, go ahead and pretend that you're buying this for someone other than yourself.)]";
			} else {
				return "A box filled with various chocolates.";
			}
		}
		
		@Override
		public String getUseName() {
			return "eat";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You remove the lid from the box of chocolates, and help yourself to the contents.",
					"You remove the lid from the box of chocolates, and start feeding the contents to [npc.name].",
					"[npc.Name] removes the lid from the box of chocolates, and starts eating the contents.",
					"[npc.Name] removes the lid from the box of chocolates, and starts feeding the contents to you.");
		}
	};
	
	public static AbstractItemType GIFT_PERFUME = new AbstractItemType(
			300,
			"a bottle of",
			false,
			"Rose Perfume",
			"Rose Perfumes",
			"A small bottle of perfume."
					+ " [Ashley.speech(A generic scent that most people enjoy. Makes you more attractive to everyone, since nobody likes a stinker!)]",
			"giftPerfume",
			Colour.BASE_ROSE,
			Colour.BASE_PURPLE_LIGHT,
			null,
			Rarity.UNCOMMON,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.GIFT_PERFUME)),
			Util.newArrayListOfValues(ItemTag.GIFT)) {


		@Override
		public String getDescription() {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP)) {
				return "A small bottle of perfume."
						+ " [Ashley.speech(A generic scent that most people enjoy. Makes you more attractive to everyone, since nobody likes a stinker!)]";
			} else {
				return "A small bottle of perfume.";
			}
		}
		
		@Override
		public String getUseName() {
			return "spray";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You take in a deep breath of the rose-scented fragrance as you spray a little squirt of the 'Rose Perfume' onto your neck.",
					"You spray a little squirt of the 'Rose Perfume' onto [npc.namePos] neck.",
					"[npc.Name] pulls out a bottle of 'Rose Perfume', and, after lifting it to [npc.her] neck, [npc.she] promptly sprays a little squirt onto [npc.her] [npc.skin].",
					"[npc.Name] pulls out a bottle of 'Rose Perfume', and, after lifting it to your neck, [npc.she] sprays a little squirt onto your [pc.skin].");
		}
	};
	
	public static AbstractItemType GIFT_TEDDY_BEAR = new AbstractItemType(
			600,
			null,
			false,
			"Teddy Bear",
			"Teddy Bears",
			"A cute brown teddy bear, with the words 'Hug me!' sewed onto a little heart that it's holding."
				+ " [Ashley.speech(Warning, this is an inanimate object; it does not actually yearn for your affection and cannot protect you from monsters hiding under the bed!)]",
			"giftTeddyBear",
			Colour.BASE_TAN,
			null,
			null,
			Rarity.UNCOMMON,
			TFEssence.ARCANE,
			null,
			Util.newArrayListOfValues(ItemTag.GIFT)) {


		@Override
		public String getDescription() {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP)) {
				return "A cute brown teddy bear, with the words 'Hug me!' sewed onto a little heart that it's holding."
						+ " [Ashley.speech(Warning, this is an inanimate object; it does not actually yearn for your affection and cannot protect you from monsters hiding under the bed!)]";
			} else {
				return "A cute brown teddy bear, with the words 'Hug me!' sewed onto a little heart that it's holding.";
			}
		}
		
		@Override
		public String getUseName() {
			return "hug";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You hug the teddy bear. It's soft and fluffy...",
					"You get [npc.name] to hug the teddy bear. [npc.She] remarks on how soft and fluffy it is...",
					"[npc.Name] hugs the teddy bear. [npc.She] remarks on how soft and fluffy...",
					"[npc.Name] gets you to hug the teddy bear. It's soft and fluffy...");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	
	// Why did I make this?
	public static AbstractItemType EGGPLANT = new AbstractItemType(
			25,
			null,
			false,
			"Eggplant",
			"Eggplants",
			"A delicate, tropical perennial often cultivated as a tender or half-hardy annual in temperate climates. Also it kind of looks like a penis if you squint.",
			"eggplant",
			Colour.GENERIC_ARCANE,
			null,
			null,
			Rarity.LEGENDARY,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.EGGPLANT)), null) {

		

		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.EGGPLANT_POTION;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return EGGPLANT_POTION;
		}
		
		@Override
		public String getUseName() {
			return "eat";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You eat the eggplant. The bitter taste of disappointment overwhelms you.",
					"You force [npc.name] to eat the eggplant. The bitter taste of disappointment overwhelms you both.",
					"[npc.Name] produces an eggplant, and then proceeds to eat it. The bitter taste of disappointment overwhelms you both.",
					"[npc.Name] produces an eggplant, and then proceeds to force you to eat it. The bitter taste of disappointment overwhelms you both.");
		}
	};
	
	public static AbstractItemType EGGPLANT_POTION = new AbstractItemType(
			250,
			null,
			false,
			"Eggplant Potion",
			"Eggplant Potions",
			"A potion made from the bitter flesh of an eggplant. Just like the berry from which it was made, the bottle containing this potion sort of looks like a penis if you squint at it.",
			"eggplant_potion",
			Colour.GENERIC_ARCANE,
			null,
			null,
			Rarity.LEGENDARY,
			null,
			null,
			null) {


		@Override
		public boolean isTransformative() {
			return true;
		}
		
		@Override
		public boolean isAbleToBeUsedInSex() {
			return true;
		}

		@Override
		public boolean isAbleToBeUsedInCombat() {
			return true;
		}
		
		@Override
		public String getUseName() {
			return "drink";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You drink the eggplant potion. The rich, complex flavour is surprisingly delicious.",
					"You force [npc.name] to drink the eggplant potion. The rich, complex flavour is surprisingly delicious.",
					"[npc.Name] produces an eggplant potion, and then proceeds to drink it. The rich, complex flavour is surprisingly delicious.",
					"[npc.Name] produces an eggplant potion, and then proceeds to force you to drink it. The rich, complex flavour is surprisingly delicious.");
		}
	};


	public static AbstractItemType ARTHURS_PACKAGE = new AbstractItemType(0,
			"",
			false,
			"Arthur's Package",
			"Arthur's Packages",
			"A package that you collected from Arcane Arts. You need to deliver this to Arthur.",
			"arthursPackage",
			Colour.ANDROGYNOUS,
			null,
			null,
			Rarity.QUEST,
			null,
			null, null) {


		@Override
		public String getUseName() {
			return "inspect";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"The package is quite small, measuring roughly 20cm along each edge. It's constructed of brown cardboard, and sealed with packaging tape.",
					"The package is quite small, measuring roughly 20cm along each edge. It's constructed of brown cardboard, and sealed with packaging tape.",
					"The package is quite small, measuring roughly 20cm along each edge. It's constructed of brown cardboard, and sealed with packaging tape.",
					"The package is quite small, measuring roughly 20cm along each edge. It's constructed of brown cardboard, and sealed with packaging tape.");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};


	public static AbstractItemType IMP_FORTRESS_ARCANE_KEY = new AbstractItemType(0,
			"",
			false,
			"Fyrsia's Key",
			"Fyrsia's Keys",
			"An arcane key that you obtained from Fyrsia, the leader of an imp fortress in Submission. When used in combination with keys obtained from the other two fortresses, it will grant entry to the central citadel.",
			"impArcaneKey",
			Colour.CLOTHING_SILVER,
			Colour.GENERIC_ARCANE,
			null,
			Rarity.QUEST,
			null,
			null, null) {


		@Override
		public String getUseName() {
			return "inspect";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(user, "You feel a weak arcane pulse being given off by the key as you turn it over in your [npc.hands].");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	public static AbstractItemType IMP_FORTRESS_ARCANE_KEY_2 = new AbstractItemType(0,
			"",
			false,
			"Jhortrax's Key",
			"Jhortrax's Keys",
			"An arcane key that you obtained from Jhortrax, the leader of an imp fortress in Submission. When used in combination with keys obtained from the other two fortresses, it will grant entry to the central citadel.",
			"impArcaneKey2",
			Colour.CLOTHING_STEEL,
			Colour.GENERIC_ARCANE,
			null,
			Rarity.QUEST,
			null,
			null, null) {


		@Override
		public String getUseName() {
			return "inspect";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(user, "You feel a weak arcane pulse being given off by the key as you turn it over in your [npc.hands].");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	public static AbstractItemType IMP_FORTRESS_ARCANE_KEY_3 = new AbstractItemType(0,
			"",
			false,
			"Hyorlyss's Key",
			"Hyorlyss's Keys",
			"An arcane key that you obtained from Hyorlyss, the leader of an imp fortress in Submission. When used in combination with keys obtained from the other two fortresses, it will grant entry to the central citadel.",
			"impArcaneKey3",
			Colour.CLOTHING_GOLD,
			Colour.GENERIC_ARCANE,
			null,
			Rarity.QUEST,
			null,
			null, null) {


		@Override
		public String getUseName() {
			return "inspect";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(user, "You feel a weak arcane pulse being given off by the key as you turn it over in your [npc.hands].");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

	public static AbstractItemType LYSSIETHS_RING = new AbstractItemType(0,
			"",
			false,
			"Lyssieth's Trapped Signet Ring",
			"Lyssieth's Trapped Signet Rings",
			"Beautifully crafted from rose gold, and encrusted with precious gemstones, this ring has been enchanted to enslave whoever puts it on."
					+ " If you were able to trick 'The Dark Siren' into wearing it, you could earn an audience with Lyssieth without needing to fight.",
			"lyssiethsRing",
			Colour.CLOTHING_ROSE_GOLD,
			Colour.CLOTHING_RED_DARK,
			Colour.CLOTHING_ROSE_GOLD,
			Rarity.QUEST,
			null,
			null, null) {


		@Override
		public String getUseName() {
			return "inspect";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(user, "The ring feels warm to the touch, revealing the fact that it's carrying a potent enchantment. Maybe once all this is over, Lyssieth will allow you to keep it as a reward...");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

	
	public static AbstractItemType OFFSPRING_MAP = new AbstractItemType(50_000,
			"an",
			false,
			"arcane offspring map",
			"arcane offspring maps",
			"An arcane-enchanted map, obtained from Dominion's city hall, which is able to track the rough location of any of your offspring.",
			"offspring_map",
			Colour.BASE_BROWN,
			null,
			null,
			Rarity.QUEST,
			TFEssence.ARCANE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.OFFSPRING_MAP)), null) {
		@Override
		public String getUseName() {
			return "consult";
		}
		@Override
		public boolean isAbleToBeUsedInSex() {
			return false;
		}
		@Override
		public boolean isAbleToBeUsedInCombat() {
			return false;
		}
		@Override
		public boolean isAbleToBeUsed(GameCharacter target) {
			return target.isPlayer()
					&& (target.getLocationPlace().getPlaceType().getEncounterType()==Encounter.DOMINION_ALLEY
							|| target.getLocationPlace().getPlaceType().getEncounterType()==Encounter.DOMINION_CANAL
							|| target.getLocationPlace().getPlaceType().getEncounterType()==Encounter.HARPY_NEST_WALKWAYS
							|| target.getLocationPlace().getPlaceType().getEncounterType()==Encounter.SUBMISSION_TUNNELS)
					&& Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()).isEmpty();
		}
		@Override
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "You need to be in one of Dominion's alleyway or canal tiles, a Harpy Nest walkway tile, or a Submission tunnel tile, and with no character already present in that tile, in order to be able to use the map!";
		}
		@Override
		public String getUseTooltipDescription(GameCharacter user, GameCharacter target) {
			return "Consult the map to see if any of your offspring are in this area.";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "You consult the map...";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	
	
	// Standard non-racial transformatives:
	
//	MASOCHISTS_HEAVEN("a bottle of", "it", "Masochist's Heaven",
//			"A clear plastic bottle of Masochist's Heaven. A girl, lying back in the missionary position, is prominently featured on the label, screaming in delight as a huge cock painfully stretches out her tight, dry pussy.",
//			"potion", Colour.CLOTHING_WHITE, true, 25, Rarity.RARE, "Decreases orifice capacity, elasticity, and wetness.") {
//		
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the plastic cap and gulp down the bottle of <i>Masochist's Heaven</i>. The drink is quite bland, but a slight"
//						+ " citrus aftertaste lingers in your mouth as you swallow the last few drops. As you lower the empty bottle, your mouth and throat suddenly feel incredibly"
//						+ " dry, as though you haven't drunk anything for hours. Before you can think about getting another drink, the feeling quickly fades away, spreading a dry warmth throughout your entire body.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of <i>Masochist's Heaven</i>, unscrews the cap," + " and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.MASOCHISTS_HEAVEN.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//	GOING_BIG("a bottle of", "it", "Going Big",
//			"A clear plastic bottle of a drink branded as 'Going Big'. A girl, presenting herself doggy-style, is prominently featured on the label, crying out in delight as a gigantic cock easily stretches out her tight, wet pussy.",
//			"potion", Colour.CLOTHING_WHITE, true, 25, Rarity.RARE, "Decreases orifice capacity. Increases elasticity and wetness.") {
//		
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the plastic cap and gulp down the bottle of <i>Going Big</i>."
//						+ " Despite the fact that the liquid is clear, it has a very strong taste of apples, and after only a moment, you're licking the last few drops from your lips.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of <i>Going Big</i>, unscrews the cap, and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.GOING_BIG.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//	WET_KISS("a bottle of", "it", "Wet Kiss",
//			"A clear plastic bottle of the branded drink <i>Wet Kiss</i>, filled with a rose-coloured liquid. The label on the front is devoid"
//					+ " of any images, and instead simply displays the name <i>Wet Kiss</i>, along with some incomprehensible technical details of the drink's manufacturing process.",
//			"potion", Colour.CLOTHING_PINK_LIGHT, true, 25, Rarity.RARE, "Increases orifice wetness and capacity.") {
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the plastic cap and gulp down the bottle of <i>Wet Kiss</i>. The drink is quite bland, but a slight"
//						+ " aftertaste of cranberries lingers in your mouth as you swallow the last few drops. Within seconds, you feel a slimy wetness squirming in your stomach,"
//						+ " but before you have any time to worry, it quickly dissipates throughout your body.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of <i>Wet Kiss</i>, unscrews the cap," + " and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.WET_KISS.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//	
//	
//
//	BUBBLE_MILK("a bottle of", "it", "bubble-milk",
//			"A clear plastic bottle of bubble-milk. Despite its name, the milk doesn't physically bubble, but instead refers to the feeling"
//					+ " you get after drinking it. A busty greater cow-girl is prominently featured on the label, smiling as she milks her gigantic udder-tits into a metal bucket.",
//			"potion", Colour.CLOTHING_WHITE, true, 25, Rarity.RARE, "Increases breast size and lactation.") {
//		
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the plastic cap and gulp down the bottle of bubble-milk. It tastes just like regular milk, but as you"
//						+ " swallow the last few drops, a funny bubbling sensation starts to spread throughout your torso before settling in your chest.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of bubble-milk, unscrews the cap, and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.BUBBLE_MILK.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//	BUBBLE_CREAM("a bottle of", "it", "bubble-cream",
//			"A clear plastic bottle of bubble-cream. Just like bubble-milk, the cream doesn't physically bubble, but instead refers"
//					+ " to the feeling you get after drinking it. A greater cow-girl with three pairs of gigantic breasts is prominently featured on the label, smiling as she" + " milks her gigantic udder-tits into a metal bucket.",
//			"potion", Colour.CLOTHING_WHITE, true, 100, Rarity.EPIC, "Increases breast size, count, and lactation.") {
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the plastic cap and gulp down the bottle of bubble-cream. Its rich taste is exactly like that of regular"
//						+ " cream, but as you swallow the last few drops, a strong bubbling sensation starts to spread throughout your torso before settling in your chest.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of bubble-cream, unscrews the cap, and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.BUBBLE_CREAM.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//
//	THROBBING_GLOW("a bottle of", "it", "Throbbing Glow",
//			"A clear plastic bottle of the energy drink <i>Throbbing Glow</i>, filled with a bright blue liquid. A"
//					+ " well-endowed greater horse-boy is prominently featured on the label, stroking his gigantic member with one hand, while bringing a bottle of <i>Throbbing Glow</i>" + " to his lips with the other.",
//			"potion", Colour.CLOTHING_BLUE_LIGHT, true, 25, Rarity.RARE, "Increases penis and testicle size. Increases cum production.") {
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the plastic cap and gulp down the bottle of <i>Throbbing Glow</i>. It tastes a little sour, sort of like"
//						+ " a cheap, sugary energy drink. As the last few drops slide down your throat, you feel a throbbing, deep-seated heat take root in your groin.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of <i>Throbbing Glow</i>, unscrews the cap," + " and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.THROBBING_GLOW.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//
//	FLOWERS_WARMTH("a bottle of", "it", "Flower's Warmth",
//			"A clear plastic bottle of the energy drink <i>Flower's Warmth</i>, filled with a pale pink liquid. A"
//					+ " greater cat-girl is featured prominently on the label, leaning back in a chair as another greater cat-girl laps hungrily at her exposed pussy.",
//			"potion", Colour.CLOTHING_PINK_LIGHT, true, 25, Rarity.RARE, "Increases the body's feminine characteristics.") {
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the plastic cap and gulp down the bottle of <i>Flower's Warmth</i>. It tastes a little sour, sort of like"
//						+ " a cheap, sugary energy drink. As the last few drops slide down your throat, you feel a deep-seated heat start to spread through in your groin.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of <i>Flower's Warmth</i>, unscrews the cap," + " and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.FLOWERS_WARMTH.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//
//	
//
//	SCARLET_WHISPER("a bottle of", "it", "Scarlet Whisper",
//			"A delicate glass bottle of <i>Scarlet Whisper</i>, filled with a bright pink liquid. The label on the front displays"
//					+ " the name <i>Scarlet Whisper</i> in a delicate, feminine font. The rest of the label is covered in simple images of pale pink flowers and looping linework.",
//			"potion", Colour.CLOTHING_PINK, true, 25, Rarity.RARE, "Increases all feminine aspects.") {
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the metal cap and gulp down the bottle of <i>Scarlet Whisper</i>. The liquid has a delicate, sweet flavour,"
//						+ " which reminds you of strawberries and cream. As you finish the bottle, a wave of dizziness washes over you, filling your mind with a soft pink haze. Shaking"
//						+ " your head, the feeling somehow seems to sink down into your body, leaving you tingling all over.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of <i>Scarlet Whisper</i>, unscrews the cap," + " and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.SCARLET_WHISPER.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//	FLAMING_THUNDER("a bottle of", "it", "Flaming Thunder",
//			"A thick glass bottle of <i>Flaming Thunder</i>, filled with a deep blue liquid. The label on the front displays"
//					+ " the name <i>Flaming Thunder</i> in a bold, striking font. The rest of the label is covered in simple images of lightning and bold linework.",
//			"potion", Colour.CLOTHING_BLUE, true, 25, Rarity.RARE, "Increases all masculine aspects.") {
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the metal cap and gulp down the bottle of <i>Flaming Thunder</i>. The liquid has a strong flavour, and despite"
//						+ " its blue colouring, tastes very similar to lemonade. As you finish the bottle, a wave of dizziness washes over you, filling your mind with a strange blue haze."
//						+ " Shaking your head, the feeling somehow seems to sink down into your body, leaving you tingling all over.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of <i>Flaming Thunder</i>, unscrews the cap," + " and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.FLAMING_THUNDER.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//
	

	public static int getMooMilkerMaxMilk() {
		return 1000;
	}
	
	private static List<AbstractItemType> dominionAlleywayItems = new ArrayList<>();
	private static List<AbstractItemType> submissionTunnelItems = new ArrayList<>();
	private static List<AbstractItemType> batCavernItems = new ArrayList<>();
	private static List<AbstractItemType> essences = new ArrayList<>();
	private static List<AbstractItemType> allItems = new ArrayList<>();
	private static Map<Subspecies, String> subspeciesBookId = new HashMap<>();
	
	/**
	 * If you're looking for spell books, their id is:<br/>
	 * SPELL_BOOK_"+spell.toString()<br/>
	 * If you're looking for spell scrolls, their id is:<br/>
	 * "SPELL_SCROLL_"+spellSchool.toString()
	 */
	private static Map<AbstractItemType, String> itemToIdMap = new HashMap<>();

	/**
	 * If you're looking for spell books, their id is:<br/>
	 * SPELL_BOOK_"+spell.toString()<br/>
	 * If you're looking for spell scrolls, their id is:<br/>
	 * "SPELL_SCROLL_"+spellSchool.toString()
	 */
	private static Map<String, AbstractItemType> idToItemMap = new HashMap<>();
	
	public static AbstractItemType getSpellBookType(Spell s) {
		return idToItemMap.get("SPELL_BOOK_"+s);
	}
	
	public static AbstractItemType getSpellScrollType(SpellSchool school) {
		return idToItemMap.get("SPELL_SCROLL_"+school);
	}
	
	public static AbstractItemType getLoreBook(Subspecies subspecies) {
		return idToItemMap.get(subspeciesBookId.get(subspecies));
	}
	
	static{
		Field[] fields = ItemType.class.getFields();
		
		for(Field f : fields){
			
			if (AbstractItemType.class.isAssignableFrom(f.getType())) {
				
				AbstractItemType item;
				try {
					item = ((AbstractItemType) f.get(null));
					
					// I feel like this is stupid :thinking:
					itemToIdMap.put(item, f.getName());
					idToItemMap.put(f.getName(), item);
					
					allItems.add(item);
					
					if(item.getItemTags().contains(ItemTag.DOMINION_ALLEYWAY_SPAWN)) {
						dominionAlleywayItems.add(item);
					}
					
					if(item.getItemTags().contains(ItemTag.SUBMISSION_TUNNEL_SPAWN)) {
						submissionTunnelItems.add(item);
					} 
					
					if(item.getItemTags().contains(ItemTag.BAT_CAVERNS_SPAWN)) {
						batCavernItems.add(item);
					} 
					
					if(item.getItemTags().contains(ItemTag.ESSENCE)) {
						essences.add(item);
					}
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		for(Spell s : Spell.values()) {
			if(!s.isSpellBook()) {
				continue;
			}
			
			List<String> effectsString = Util.newArrayListOfValues(
					"[style.boldExcellent(Permanently)] gain the spell '<b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+"</b>'.");
			

			Subspecies subspecies = Subspecies.ELEMENTAL_ARCANE;
			if(s == Spell.ELEMENTAL_EARTH) {
				subspecies = Subspecies.ELEMENTAL_EARTH;
				
			} else if(s == Spell.ELEMENTAL_WATER) {
				subspecies = Subspecies.ELEMENTAL_WATER;
				
			} else if(s == Spell.ELEMENTAL_AIR) {
				subspecies = Subspecies.ELEMENTAL_AIR;
				
			} else if(s == Spell.ELEMENTAL_FIRE) {
				subspecies = Subspecies.ELEMENTAL_FIRE;
			}
			effectsString.add("Adds "+subspecies.getName(null)+" encyclopedia entry.");
			effectsString.add("[style.boldExcellent(+10)] <b style='color:"+s.getSpellSchool().getColour()+";'>"+subspecies.getDamageMultiplier().getName()+"</b>");
			
			
			AbstractItemEffectType effectType = new AbstractItemEffectType(effectsString, s.getSpellSchool().getColour()) {
				
				@Override
				public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
					boolean hasSpell = target.hasSpell(s);
					target.addSpell(s);
					
					String raceKnowledgeGained = "";
					if(target.isPlayer()) {
						if(s == Spell.ELEMENTAL_EARTH) {
							raceKnowledgeGained = getBookEffect(target, Subspecies.ELEMENTAL_EARTH, true);
							
						} else if(s == Spell.ELEMENTAL_WATER) {
							raceKnowledgeGained = getBookEffect(target, Subspecies.ELEMENTAL_WATER, true);
							
						} else if(s == Spell.ELEMENTAL_AIR) {
							raceKnowledgeGained = getBookEffect(target, Subspecies.ELEMENTAL_AIR, true);
							
						} else if(s == Spell.ELEMENTAL_FIRE) {
							raceKnowledgeGained = getBookEffect(target, Subspecies.ELEMENTAL_FIRE, true);
							
						} else if(s == Spell.ELEMENTAL_ARCANE) {
							raceKnowledgeGained = getBookEffect(target, Subspecies.ELEMENTAL_ARCANE, true);
							
						}
					}
					
					if(hasSpell) {
						if(target.isPlayer()) {
							return "<p style='text-align:center'>"
										+"<i><b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+":</b> "+s.getDescription()+"</i>"
									+"</p>"
									+ "<p>"
										+"Reading through the spell book again, you quickly discover that you've already learned all there is to know about the spell '"+s.getName()+"'."
										+ " Apart from some well-detailed diagrams of a demon casting this spell, there's nothing within the tome's pages to hold your attention,"
											+ " and you find yourself closing it after just a couple of minutes, having not learned anything new..."
									+ "</p>"
									+raceKnowledgeGained;
						} else {
							return "<p style='text-align:center'>"
										+"<i><b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+":</b> "+s.getDescription()+"</i>"
									+"</p>"
									+ "<p>"
										+ UtilText.parse(target,
												"Reading through the spell book again, [npc.name] quickly discovers that [npc.sheIs] already learned all there is to know about the spell '"+s.getName()+"'."
												+ " Apart from some well-detailed diagrams of a demon casting this spell, there's nothing within the tome's pages to hold [npc.her] attention,"
													+ " and [npc.she] closes it after just a couple of minutes, having not learned anything new...")
									+ "</p>";
						}
						
					} else {
						if(target.isPlayer()) {
							return "<p style='text-align:center'>"
										+"<i><b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+":</b> "+s.getDescription()+"</i>"
									+"</p>"
									+ "<p>"
										+ "As you read through the spell book, you discover that most of the pages are dedicated to helping the reader build up their arcane aura to the point where they'd be able to learn this spell."
										+ " Seeing as your aura is already extremely powerful, these passages are of no use to you, and you quickly flick through to the final chapters,"
											+ " where it's described exactly how to focus your aura into casting the spell '<i>"+s.getName()+"</i>'."
										+ " It doesn't take you long to get the general idea of what to do, and after completing the book's practice exercises, you feel confident that you'll be able to cast this spell whenever you'd like."
									+ "</p>"
									+ "<p style='text-align:center;'>"
										+ "You learn the spell <b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+"</b>!"
										+ "<br/><i>Having served its purpose, the spell book disappears in a flash of purple light!</i>"
										+ "<br/>[style.italicsExcellent(Spell book added to Lilaya's library!)]"
									+ "</p>"
									+raceKnowledgeGained;
							
						} else {
							return "<p style='text-align:center'>"
									+"<i><b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+":</b> "+s.getDescription()+"</i>"
								+"</p>"
								+ "<p>"
									+ UtilText.parse(target,
											"As [npc.name] reads through the spell book, [npc.she] discovers that most of the pages are dedicated to helping the reader build up their arcane aura to the point where they'd be able to learn this spell."
											+ " Seeing as [npc.her] aura is already powerful enough for this, these passages are of no use to [npc.herHim], and [npc.she] quickly flicks through to the final chapters,"
												+ " where it's described exactly how to focus [npc.her] aura into casting the spell '<i>"+s.getName()+"</i>'."
											+ " It doesn't take [npc.herHim] long to get the general idea of what to do, and after completing the book's practice exercises,"
												+ " [npc.she] feels confident that [npc.she]'ll be able to cast this spell whenever [npc.she]'d like.")
								+ "</p>"
								+ "<p style='text-align:center;'>"
									+ UtilText.parse(target, "[npc.Name] learns the spell <b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+"</b>!")
									+ "<br/><i>Having served its purpose, the spell book disappears in a flash of purple light!</i>"
								+ "</p>";
						}
					}
				}
			};
			
			ItemEffectType.addAbstractItemEffectToIds("EFFECT_SPELL_"+s, effectType);
			
			int value = 2500;
			switch(s) {
				// Tier 1:
				case ARCANE_AROUSAL:
				case ICE_SHARD:
				case POISON_VAPOURS:
				case FIREBALL:
				case SLAM:
					break;
					
				// Tier 2:
				case ARCANE_CLOUD:
				case FLASH:
				case RAIN_CLOUD:
				case TELEKENETIC_SHOWER:
				case TELEPATHIC_COMMUNICATION:
				case VACUUM:
					value = 5000;
					break;

				// Tier 3:
				case STONE_SHELL:
				case SOOTHING_WATERS:
				case PROTECTIVE_GUSTS:
				case CLOAK_OF_FLAMES:
				case CLEANSE:
				case STEAL:
					value = 10000;
					break;
					
				// Tier 4:
				case ELEMENTAL_AIR:
				case ELEMENTAL_ARCANE:
				case ELEMENTAL_EARTH:
				case ELEMENTAL_FIRE:
				case ELEMENTAL_WATER:
					value = 25000;
					break;
					
				// Tier 5:
				case LILITHS_COMMAND:
				case TELEPORT:
					value = 1000000;
					break;
					
				case WITCH_CHARM:
				case WITCH_SEAL:
				case DARK_SIREN_SIRENS_CALL:
					break;
			}
			
			AbstractItemType spellBook = new AbstractItemType(value,
					null,
					false,
					"Spellbook: "+s.getName(),
					"Spellbooks: "+s.getName(),
					"An arcane tome which contains detailed instructions on how to cast the spell '"+s.getName()+"'."
							+ " Reading this tome will permanently unlock the ability to cast this spell.",
					"spell_book",
					s.getSpellSchool().getColour(),
					null,
					null,
					Rarity.LEGENDARY,
					null,
					Util.newArrayListOfValues(new ItemEffect(effectType)),
					Util.newArrayListOfValues(ItemTag.SPELL_BOOK)) {
		
				@Override
				public String getSVGString() {
					return super.getSVGString()
							+"<div style='width:60%;height:60%;position:absolute;left:0;top:0;'>"
								+ s.getSVGString()
							+ "</div>";
				}
				
//				@Override
//				public boolean isConsumedOnUse() {
//					return false;
//				}
				
				@Override
				public boolean isAbleToBeUsed(GameCharacter target) {
					return (target.isPlayer() || target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.ONE_AVERAGE.getMinimumValue())
							&& !(target instanceof Elemental);
				}
		
				@Override
				public String getUnableToBeUsedDescription(GameCharacter target) {
					if(target.isPlayer()) {
						return "You already know how to cast this spell!";
						
					} else if(target instanceof Elemental) {
						return UtilText.parse(target, "[npc.Name], like all elementals, cannot learn spells from books."
								+ " Instead, [npc.she] will need to focus on improving [npc.her] understanding of the arcane in order to learn new spells."
								+ " (Elementals gain new spells via the perk tree.)");
						
					} else {
						return UtilText.parse(target, "[npc.Name] does not have enough arcane skill to know how to learn this spell! (Requires arcane to be 5 or greater.)");
					}
				}
				
				@Override
				public String getUseName() {
					return "read";
				}
				
				@Override
				public String getUseDescription(GameCharacter user, GameCharacter target) {
					return getGenericUseDescription(user, target,
							"Opening the spell book, you read its contents...",
							"Opening the spell book, you get [npc.name] to read its contents...",
							"[npc.Name] produces a spell book, which [npc.she] then starts to read...",
							"[npc.Name] produces a spell book, which [npc.she] then forces you to read...");
				}
				
				@Override
				public boolean isAbleToBeUsedInSex() {
					return false;
				}

				@Override
				public boolean isAbleToBeUsedInCombat() {
					return false;
				}
			};
			
			itemToIdMap.put(spellBook, "SPELL_BOOK_"+s);
			idToItemMap.put("SPELL_BOOK_"+s, spellBook);
			
			allItems.add(spellBook);
		}
		
		for(SpellSchool school : SpellSchool.values()) {
			
			AbstractItemEffectType effectType = new AbstractItemEffectType(Util.newArrayListOfValues(
							"[style.boldExcellent(+1)] to <span style='color:"+school.getColour().toWebHexString()+";'>"+school.getName()+"</span> upgrade points."),
							school.getColour()) {
						
						@Override
						public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
							target.incrementSpellUpgradePoints(school, 1);
							return "<p style='text-align:center;'>"
										+ (target.isPlayer()?"You gain":UtilText.parse(target, "[npc.Name] gains"))+" an upgrade point for the spell school <b style='color:"+school.getColour().toWebHexString()+";'>"+school.getName()+"</b>!<br/>"
										+ "<i>Having served its purpose, the scroll disappears in a flash of purple light!</i>"
									+ "</p>";
						}
					};

			ItemEffectType.addAbstractItemEffectToIds("EFFECT_SCROLL_SCHOOL_"+school, effectType);
			
			AbstractItemType scroll = new AbstractItemType(1000,
					null,
					false,
					"Scroll of "+Util.capitaliseSentence(school.getName()),
					"Scrolls of "+Util.capitaliseSentence(school.getName()),
					"An arcane scroll which, when read, imbues the reader with the power of the school of '"+Util.capitaliseSentence(school.getName())+"'.",
					"spell_scroll",
					school.getColour(),
					null,
					null,
					Rarity.EPIC,
					null,
					Util.newArrayListOfValues(new ItemEffect(effectType)),
					Util.newArrayListOfValues(ItemTag.SPELL_SCROLL)) {
				
				@Override
				public boolean isAbleToBeUsed(GameCharacter target) {
					return target.isPlayer() || target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.ONE_AVERAGE.getMinimumValue();
				}
		
				@Override
				public String getUnableToBeUsedDescription(GameCharacter target) {
					return UtilText.parse(target, "[npc.Name] does not have enough arcane skill to know how to absorb the power of this scroll! (Requires arcane to be 5 or greater.)");
				}
				
				@Override
				public String getUseName() {
					return "read";
				}
				
				@Override
				public String getUseDescription(GameCharacter user, GameCharacter target) {
					return getGenericUseDescription(user, target,
							"Unravelling the scroll, you read its contents...",
							"Unravelling the scroll, you get [npc.name] to read its contents...",
							"[npc.Name] produces a scroll, which [npc.she] then starts to read...",
							"[npc.Name] produces a scroll, which [npc.she] then forces you to read...");
				}
				
				@Override
				public boolean isAbleToBeUsedInSex() {
					return false;
				}

				@Override
				public boolean isAbleToBeUsedInCombat() {
					return false;
				}
			};
			
			itemToIdMap.put(scroll, "SPELL_SCROLL_"+school);
			idToItemMap.put("SPELL_SCROLL_"+school, scroll);
			
			allItems.add(scroll);
		}
		
		// Race books:
		
		Map<String, List<Subspecies>> subspeciesLoreMap = new HashMap<>();
		for(Subspecies sub : Subspecies.values()) {
			subspeciesLoreMap.putIfAbsent(sub.getAdvancedDescriptionId(), new ArrayList<>());
			subspeciesLoreMap.get(sub.getAdvancedDescriptionId()).add(sub);
		}
		
		// Add effects from here, as Subspecies and ItemEffectType are dependent on one another to be initialised.
		for(Subspecies sub : Subspecies.values()) {
			subspeciesLoreMap.putIfAbsent(sub.getAdvancedDescriptionId(), new ArrayList<>());
			subspeciesLoreMap.get(sub.getAdvancedDescriptionId()).add(sub);
		}
		
		for(Entry<String, List<Subspecies>> entry : subspeciesLoreMap.entrySet()) {
			Subspecies mainSubspecies = entry.getValue().contains(Subspecies.getMainSubspeciesOfRace(entry.getValue().get(0).getRace()))
											?Subspecies.getMainSubspeciesOfRace(entry.getValue().get(0).getRace())
											:entry.getValue().get(0);
			
			AbstractItemEffectType bookType = generateBookEffect(mainSubspecies);
			ItemEffectType.allEffectTypes.add(bookType);
			String id = "BOOK_READ_"+mainSubspecies.toString();
			ItemEffectType.itemEffectTypeToIdMap.put(bookType, id);
			ItemEffectType.idToItemEffectTypeMap.put(id, bookType);
		}
		
		for(Entry<String, List<Subspecies>> entry : subspeciesLoreMap.entrySet()) {
			Subspecies mainSubspecies = entry.getValue().contains(Subspecies.getMainSubspeciesOfRace(entry.getValue().get(0).getRace()))
											?Subspecies.getMainSubspeciesOfRace(entry.getValue().get(0).getRace())
											:entry.getValue().get(0);
			
			AbstractItemType loreBook = new AbstractItemType(250,
							null,
							false,
							mainSubspecies.getBookName(),
							mainSubspecies.getBookNamePlural(),
							"A book which contains advanced lore concerning "+mainSubspecies.getNamePlural(null)+".",
							"race_book",
							mainSubspecies.getColour(null),
							Colour.CLOTHING_GOLD,
							mainSubspecies.getColour(null),
							Rarity.LEGENDARY,
							null,
							Util.newArrayListOfValues(new ItemEffect(ItemEffectType.getBookEffectFromSubspecies(mainSubspecies))),
							Util.newArrayListOfValues(ItemTag.BOOK)) {

				@Override
				public String getSVGString() {
					int offset = 6;
					float left = (float) (30 + offset*Math.cos(Math.toRadians(60)));
					left = Math.round(left*100);
					left /=100;
					return super.getSVGString()
							+"<div style='width:40%;height:40%;position:absolute;left:"+left+"%;top:"+(30-offset)+"%; opacity:0.75; -webkit-transform: rotate(30deg);'>"
								+ mainSubspecies.getSVGStringNoBackground()
							+ "</div>";
				}
		
				@Override
				public boolean isConsumedOnUse() {
					return false;
				}
				
				@Override
				public String getUseName() {
					return "read";
				}
				
				@Override
				public String getUseDescription(GameCharacter user, GameCharacter target) {
					return getGenericUseDescription(user, target,
							"Opening the book, you read its contents...",
							"Opening the book, you force [npc.name] to read its contents...",
							"[npc.Name] produces a book, titled '"+getName(false)+"', which [npc.she] then starts to read...",
							"[npc.Name] produces a book, titled '"+getName(false)+"', which [npc.she] then forces you to read...");
				}
			};
			
			String id = "BOOK_"+mainSubspecies.toString();
					
			itemToIdMap.put(loreBook, id);
			idToItemMap.put(id, loreBook);
			
			for(Subspecies subspecies : entry.getValue()) {
				subspeciesBookId.put(subspecies, id);
			}
			
			allItems.add(loreBook);
		}
	}
	
	private static AbstractItemEffectType generateBookEffect(Subspecies subspecies) {
		return new AbstractItemEffectType(Util.newArrayListOfValues(
				"Adds "+subspecies.getName(null)+" encyclopedia entry.",
				"[style.boldExcellent(+10)] <b style='color:"+subspecies.getColour(null).toWebHexString()+";'>"+subspecies.getDamageMultiplier().getName()+"</b>"),
				subspecies.getColour(null)) {
			@Override
			public String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
				return getBookEffect(target, subspecies, true);
			}
		};
	}

	public static List<AbstractItemType> getDominionAlleywayItems() {
		return dominionAlleywayItems;
	}
	
	public static List<AbstractItemType> getSubmissionTunnelItems() {
		return submissionTunnelItems;
	}
	
	public static List<AbstractItemType> getBatCavernItems() {
		return batCavernItems;
	}
	public static List<AbstractItemType> getEssences() {
		return essences;
	}
	
	public static List<AbstractItemType> getAllItems() {
		return allItems;
	}
	
	public static Map<AbstractItemType, String> getItemToIdMap() {
		return itemToIdMap;
	}
	
	public static Map<String, AbstractItemType> getIdToItemMap() {
		return idToItemMap;
	}

}
