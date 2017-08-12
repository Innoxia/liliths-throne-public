package com.base.game.inventory.item;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.base.game.character.GameCharacter;
import com.base.game.character.QuestLine;
import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.body.types.PenisType;
import com.base.game.character.race.Race;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.Rarity;
import com.base.game.inventory.clothing.ClothingType;
import com.base.game.inventory.clothing.CoverableArea;
import com.base.game.inventory.enchanting.TFEssence;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;

/**
 * @since 0.1.0
 * @version 0.1.8
 * @author Innoxia
 */
public enum ItemType {

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
	// Lunette; a muscular futa pegatuar. She's"
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
	
	
	
	// Crafting:
	
	// Strength ingredients are beer-type alcohol:
	
	STR_INGREDIENT_EQUINE_CIDER(
			"a bottle of",
			false,
			"Equine Cider",
			"The thick glass bottle of 'Equine Cider' appears to contain, much as its name would suggest, a generous helping of some sort of alcoholic cider."
				+ " On the label, there's an incredibly lewd illustration of a horse-boy slamming his massive cock deep into a girl's eager pussy.",
			"attributeHorseMorphDrink",
			Colour.ATTRIBUTE_STRENGTH,
			25,
			Rarity.UNCOMMON,
			TFEssence.HORSE_MORPH,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.STR_EQUINE_CIDER, null, null)))) {

		@Override
		public ItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_STRENGTH;
		}

		@Override
		public ItemType getEnchantmentItemType() {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user == Main.game.getPlayer())
				return "<p>"
							+ "Unscrewing the lid, you bring the bottle of 'Equine Cider' to your lips before tilting your head back and quickly gulping down the golden liquid."
							+ " As the last few drops slide down your throat, you notice a faint, musky dryness permeating through the sweet liquid, which lingers for some time as a slightly unpleasant aftertaste."
						+ "</p>";
			else
				return UtilText.parse(user,
						"<p>"
								+ "[npc.Name] pulls out a bottle of 'Equine Cider', and, after quickly unscrewing the cap, [npc.she] promptly downs the entire bottle."
						+ "</p>");
		}
	},
	
	STR_INGREDIENT_WOLF_WHISKEY("a bottle of",
			false,
			"Wolf Whiskey",
			"Although labelled as a whiskey, the liquid inside the glass bottle looks more like a thick cream."
					+ " The label on the front shows a greater wolf-boy ejaculating into a bottle just like this one, making it quite clear what this 'whiskey' really is.",
			"attributeWolfMorphDrink",
			Colour.ATTRIBUTE_STRENGTH,
			25,
			Rarity.UNCOMMON,
			TFEssence.WOLF_MORPH,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.STR_WOLF_WHISKEY, null, null)))) {

		@Override
		public ItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_STRENGTH;
		}

		@Override
		public ItemType getEnchantmentItemType() {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user == Main.game.getPlayer())
				return "<p>"
							+ "Popping off the cap, you bring the bottle of 'Wolf Whiskey' up to your lips."
							+ " A thick, musky scent rises from the opening, and with a gulp, you start downing the liquid, discovering that the liquid's taste is almost identical to its pungent aroma."
						+ "</p>";
			else
				return UtilText.parse(user,
						"<p>"
								+ "[npc.Name] pulls out a bottle of 'Wolf Whiskey', and, after quickly popping off the cap, [npc.she] promptly downs the entire bottle."
						+ "</p>");
		}
	},
	
	// Intelligence ingredients are cold non-alcoholic drinks:
	
	INT_INGREDIENT_FELINE_FANCY(
			"a bottle of",
			false,
			"Feline's Fancy",
			"A delicate glass bottle filled with a thick, cream-like liquid."
				+ " A label on the front shows a pair of cat-girls lovingly kissing one another, with the dominant partner slipping a hand down between her partner's thighs.",
			"attributeCatMorphDrink",
			Colour.ATTRIBUTE_INTELLIGENCE,
			25,
			Rarity.UNCOMMON,
			TFEssence.CAT_MORPH,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.INT_FELINE_FANCY, null, null)))) {

		@Override
		public ItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_INTELLIGENCE;
		}

		@Override
		public ItemType getEnchantmentItemType() {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user == Main.game.getPlayer() && target == Main.game.getPlayer()) {
				return "<p>"
							+ "Opening the bottle, you eagerly bring it up to your waiting lips."
							+ " A rich, creamy smell rises from the opening, and as you greedily drink down the cool liquid, you're delighted to discover that it tastes every bit as good as its delicious aroma suggested it would."
						+ "</p>";
				
			} else {
				return UtilText.parse(user,
						"<p>"
							+ "[npc.Name] pulls out a bottle of 'Feline's Fancy', and, after quickly unscrewing the cap, [npc.she] promptly downs the entire bottle."
						+ "</p>");
			}
		}
	},
	
	INT_INGREDIENT_VANILLA_WATER(
			"a bottle of",
			false,
			"Vanilla Water",
			"A plastic bottle filled with what appears to be nothing but water."
				+ " While there's no label on the bottle, there is a slight indentation in its surface, and, holding it up to the light to get a better look, you see that the impression spells the words 'Vanilla Water'.",
			"attributeHumanDrink",
			Colour.ATTRIBUTE_INTELLIGENCE,
			25,
			Rarity.UNCOMMON,
			TFEssence.HUMAN,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.INT_VANILLA_WATER, null, null)))) {

		@Override
		public ItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_INTELLIGENCE;
		}

		@Override
		public ItemType getEnchantmentItemType() {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user == Main.game.getPlayer() && target == Main.game.getPlayer()) {
				return "<p>"
							+ "After first unscrewing the cap, you bring the plastic bottle up to your mouth."
							+ " A faint smell of vanilla informs you that this isn't any ordinary water, and as you tilt your head back and start drinking the cool liquid, the taste of vanilla overwhelmes your senses."
						+ "</p>";
				
			} else {
				return UtilText.parse(user,
						"<p>"
							+ "[npc.Name] pulls out a bottle of 'Vanilla Water', and, after quickly unscrewing the cap, [npc.she] promptly downs the entire bottle."
						+ "</p>");
			}
		}
	},
	
	// Fitness ingredients are energy drinks and coffee:
	
	FIT_INGREDIENT_CANINE_CRUSH(
			"a bottle of",
			false,
			"Canine Crush",
			"A glass bottle of what looks to be some kind of beer."
				+ " A label on the front shows a dog-boy lining himself up behind a beautiful girl, who's down on all fours, presenting her naked, dripping pussy to the throbbing dog-cock behind her.",
			"attributeDogMorphDrink",
			Colour.ATTRIBUTE_FITNESS,
			25,
			Rarity.UNCOMMON,
			TFEssence.DOG_MORPH,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.FIT_CANINE_CRUSH, null, null)))) {

		@Override
		public ItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_FITNESS;
		}

		@Override
		public ItemType getEnchantmentItemType() {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user == Main.game.getPlayer() && target == Main.game.getPlayer()) {
				return "<p>"
							+ "You pop off the cap and start drinking the bottle of 'Canine Crush'."
							+ " It doesn't taste anything like any other beer you've ever drank, and it reminds you more of a sugary energy drink rather than any alcoholic beverage."
							+ " As the last few drops slide down your throat, a strange, musky aftertaste lingers on your tongue."
						+ "</p>";
				
			} else {
				return UtilText.parse(target,
						"<p>"
							+ "[npc.Name] pulls out a bottle of 'Canine Crush', and, after quickly popping off the cap, [npc.she] promptly downs the entire bottle."
						+ "</p>");
			}
		}
	},
	
	FIT_INGREDIENT_SQUIRREL_JAVA(
			"a bottle of",
			false,
			"Squirrel Java",
			"A glass bottle of what looks to be some kind of coffee."
				+ " A label on the front shows a squirrel-girl lining herself up over a bottle, she seems to be fingering herself into the bottle to provide some cream for the coffee.",
			"attributeSquirrelMorphDrink",
			Colour.ATTRIBUTE_FITNESS,
			25,
			Rarity.UNCOMMON,
			TFEssence.SQUIRREL_MORPH,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.FIT_SQUIRREL_JAVA, null, null)))) {

		@Override
		public ItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_FITNESS;
		}

		@Override
		public ItemType getEnchantmentItemType() {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user == Main.game.getPlayer() && target == Main.game.getPlayer()) {
				return "<p>"
							+ "You pop off the cap and start drinking the bottle of 'Squirrel Java'."
							+ " It doesn't taste anything like any other coffee you've ever drank, and it reminds you more of a sugary energy drink rather than any caffinated beverage."
							+ " As the last few drops slide down your throat, a strange, sweet aftertaste lingers on your tongue."
						+ "</p>";
				
			} else {
				return UtilText.parse(target,
						"<p>"
							+ "[npc.Name] pulls out a bottle of 'Squirrel Java', and, after quickly popping off the cap, [npc.she] promptly downs the entire bottle."
						+ "</p>");
			}
		}
	},
	
	SEX_INGREDIENT_HARPY_PERFUME(
			"a bottle of",
			false,
			"Harpy Perfume",
			"A glass bottle of what looks to be a kind of feminine perfume."
				+ " There's a stylised image of a harpy's wings on the front of the bottle.",
			"attributeHarpyPerfume",
			Colour.GENERIC_SEX,
			25,
			Rarity.UNCOMMON,
			TFEssence.HARPY,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.SEX_HARPY_PERFUME, null, null)))) {

		@Override
		public ItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_SEXUAL;
		}

		@Override
		public ItemType getEnchantmentItemType() {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "spray";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user == Main.game.getPlayer() && target == Main.game.getPlayer()) {
				return "<p>"
							+ "You take in a deep breath of an intoxicating feminine scent as you spray a little squirt of the 'Harpy Perfume' onto your neck."
							+ " Looking down at the curiously now-empty bottle of perfume, you feel a bubbly wave of excitement running through you, and without thinking, you find yourself letting out a very girly giggle."
						+ "</p>";
				
			} else {
				return UtilText.parse(target,
						"<p>"
							+ "[npc.Name] pulls out a bottle of 'Harpy Perfume', and, after quickly popping off the cap, [npc.she] promptly sprays a little squirt onto [npc.her] neck."
						+ "</p>");
			}
		}
	},
	
	// Corruption ingredients are "mysterious liquids" (cum and milk...):
	
	COR_INGREDIENT_LILITHS_GIFT("a bottle of",
			false,
			"Lilith's Gift",
			"A glass bottle, filled with bubbling pink liquid."
					+ " On the bottle's label, there is an image of Lilith's perfectly-formed, heart-shaped ass."
					+ " Her delicate hands are reaching down to pull apart her soft ass cheeks, fully exposing her asshole and pussy, both of which are dripping wet from excitement.",
			"attributeDemonDrink",
			Colour.ATTRIBUTE_CORRUPTION,
			50,
			Rarity.UNCOMMON,
			TFEssence.DEMON,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.COR_LILITHS_GIFT, null, null)))) {

		@Override
		public ItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ATTRIBUTE_CORRUPTION;
		}

		@Override
		public ItemType getEnchantmentItemType() {
			return POTION;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user == Main.game.getPlayer())
				return "<p>"
							+ "The moment you pull the stopper out from the top of the bottle of 'Lilith's Gift', you're filled with a desperate need to drink the bubbling pink liquid contained within."
							+ " Instantly, you bring the bottle to your lips and gulp it all down, suppressing your gag reflex as your senses are overwhelmed by how sickeningly sweet it is."
						+ "</p>";
			else
				return UtilText.parse(user,
						"<p>"
								+ "[npc.Name] pulls out a bottle of 'Lilith's Gift', and, after quickly pulling out the stopper, [npc.she] promptly downs the entire bottle."
						+ "</p>");
		}
	},
	
	// Racial ingredients:
	
	RACE_INGREDIENT_DEMON(
			"a bottle of",
			false,
			"Innoxia's Gift",
			"A glass bottle, filled with bubbling golden liquid."
					+ " Someone's stuck a crude little sticker to one side of the bottle, and as you look closer, you see that it reads: 'Temporary item! Demon TFs don't work like this!'",
			"raceDemonInnoxiasGift",
			Colour.ATTRIBUTE_CORRUPTION,
			500,
			Rarity.LEGENDARY,
			TFEssence.DEMON,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.RACE_INNOXIAS_GIFT, null, null)))) {

		@Override
		public ItemEffectType getEnchantmentEffect() {
			return ItemEffectType.RACE_DEMON;
		}

		@Override
		public ItemType getEnchantmentItemType() {
			return ELIXIR;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user == Main.game.getPlayer())
				return "<p>"
							+ "The moment you pull the stopper out from the top of the bottle of 'Innoxia's Gift', you're filled with a desperate need to drink the bubbling golden liquid contained within."
							+ " Instantly, you bring the bottle to your lips and gulp it all down, suppressing your gag reflex as your senses are overwhelmed by how sickeningly sweet it is."
						+ "</p>";
			else
				return UtilText.parse(user,
						"<p>"
							+ "[npc.Name] pulls out a bottle of 'Innoxia's Gift', and, after quickly pulling out the stopper, [npc.she] promptly downs the entire bottle."
							+ " <i>How did [npc.she] get this?!</i>"
						+ "</p>");
		}
	},
	
	RACE_INGREDIENT_HUMAN(
			"a vial of",
			false,
			"angel's tears",
			"A delicate glass vial full of a light turquoise liquid."
					+ " There's an image of a weeping angel engraved into the glass, and you see that her tears are falling into a vial just like this one.",
			"raceHumanAngelsTears",
			Colour.RACE_HUMAN,
			75,
			Rarity.RARE,
			TFEssence.HUMAN,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.RACE_ANGELS_TEARS, null, null)))) {

		@Override
		public ItemEffectType getEnchantmentEffect() {
			return ItemEffectType.RACE_HUMAN;
		}

		@Override
		public ItemType getEnchantmentItemType() {
			return ELIXIR;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user == Main.game.getPlayer() && target == Main.game.getPlayer()) {
				return "<p>"
							+ "You pull out the little glass stopper and bring the vial to your lips."
							+ " The faint scent of roses rises up from the opening, and you find yourself letting out a gentle sigh as you tilt back your head before drinking down the cool liquid."
						+ "</p>";
				
			} else {
				return UtilText.parse(target,
						"<p>"
						+ "[npc.Name] pulls out a vial of 'Angel's Tears', and, pulling out the little glass stopper, quickly gulps down the pale turquoise liquid contained within."
						+ "</p>");
			}
		}
	},
	
	RACE_INGREDIENT_CAT_MORPH(
			"a",
			false,
			"Kitty's Reward",
			"A small, square food tin with a ring-pull lid."
					+ " A label on the side shows a greater cat-girl devouring a plate of what looks to be this can's contents; some sort of tinned salmon.",
			"raceCatMorphKittysReward",
			Colour.RACE_CAT_MORPH,
			40,
			Rarity.RARE,
			TFEssence.CAT_MORPH,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.RACE_KITTYS_REWARD, null, null)))) {

		@Override
		public ItemEffectType getEnchantmentEffect() {
			return ItemEffectType.RACE_CAT_MORPH;
		}

		@Override
		public ItemType getEnchantmentItemType() {
			return ELIXIR;
		}

		@Override
		public String getUseName() {
			return "eat";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user == Main.game.getPlayer() && target == Main.game.getPlayer()) {
				return "<p>"
							+ "You pull back the ring-pull and peel off the can's lid."
							+ " A rich, fishy smell accompanies the sight of what looks to be tinned salmon, and you find yourself unable to resist the delicious-looking meat."
							+ " You quickly wolf down the can's contents, finding that it was as delicious as it looked."
						+ "</p>";
				
			} else {
				return UtilText.parse(target,
						"<p>"
						+ "[npc.Name] pulls out a can of 'Kitty's Reward', and, peeling off the lid, quickly devours the contents."
						+ "</p>");
			}
		}
	},
	
	RACE_INGREDIENT_DOG_MORPH(
			"a",
			false,
			"Canine Crunch",
			"An individually-wrapped biscuit in the shape of a bone.",
			"raceDogMorphCanineCrunch",
			Colour.RACE_DOG_MORPH,
			40,
			Rarity.RARE,
			TFEssence.DOG_MORPH,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.RACE_CANINE_CRUNCH, null, null)))) {

		@Override
		public ItemEffectType getEnchantmentEffect() {
			return ItemEffectType.RACE_DOG_MORPH;
		}

		@Override
		public ItemType getEnchantmentItemType() {
			return ELIXIR;
		}

		@Override
		public String getUseName() {
			return "eat";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user == Main.game.getPlayer() && target == Main.game.getPlayer()) {
				return "<p>"
						+ "You peel back the paper packaging and pop the Canine Crunch into your mouth."
						+ " As you crunch down on the dry biscuit, you find that it's quite bland and salty."
						+ "</p>";
				
			} else {
				return UtilText.parse(target,
						"<p>"
						+ "[npc.Name] pulls out a Canine Crunch, and, quickly unwrapping the paper packaging, proceeds to wolf down the bone-shaped biscuit."
						+ "</p>");
			}
		}
	},

	RACE_INGREDIENT_HORSE_MORPH(
			"a",
			false,
			"Sugar Carrot Cube",
			"An individually-wrapped sugar cube, which, except for the fact that it's bright orange and smells of carrots, appears to be identical to every other sugar cube you've seen.",
			"raceHorseMorphSugarCarrotCube",
			Colour.RACE_HORSE_MORPH,
			40,
			Rarity.RARE,
			TFEssence.HORSE_MORPH,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.RACE_SUGAR_CARROT_CUBE, null, null)))) {

		@Override
		public ItemEffectType getEnchantmentEffect() {
			return ItemEffectType.RACE_HORSE_MORPH;
		}

		@Override
		public ItemType getEnchantmentItemType() {
			return ELIXIR;
		}

		@Override
		public String getUseName() {
			return "eat";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user == Main.game.getPlayer() && target == Main.game.getPlayer()) {
				return "<p>"
							+ "You peel off the paper packaging and pop the Sugar Carrot Cube into your mouth."
							+ " The strong taste of carrots instantly fills your mouth, but before you have any time to relish the flavour, you find that it's dissolved in your saliva, and you've gulped down the sugary mess."
						+ "</p>";
				
			} else {
				return UtilText.parse(target,
						"<p>"
						+ "[npc.Name] pulls out a Sugar Carrot Cube, and, quickly peeling off the paper packaging, pops it into [npc.her] mouth and swallows it down."
						+ "</p>");
			}
		}
	},
	
	RACE_INGREDIENT_WOLF_MORPH(
			"a package of",
			false,
			"Meat and Marrow",
			"A package of 'Meat and Marrow', which consists of a slab of some sort of raw meat, wrapped in grease-proof paper and tied up with brown string.",
			"raceWolfMorphMeatAndMarrow",
			Colour.RACE_WOLF_MORPH,
			40,
			Rarity.RARE,
			TFEssence.WOLF_MORPH,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.RACE_MEAT_AND_MARROW, null, null)))) {

		@Override
		public ItemEffectType getEnchantmentEffect() {
			return ItemEffectType.RACE_WOLF_MORPH;
		}

		@Override
		public ItemType getEnchantmentItemType() {
			return ELIXIR;
		}

		@Override
		public String getUseName() {
			return "eat";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user == Main.game.getPlayer() && target == Main.game.getPlayer()) {
				return "<p>"
						+ "You untie the brown string, and, peeling back the grease-proof paper, bring the now-exposed slab of meat to your mouth."
						+ " A rich, bloody smell rises to fill your nostrils, but instead of repulsing you, you find yourself drooling at the thought of eating the raw meat."
						+ " Without further thought, you greedily devour the dripping flesh, licking your fingers clean after rapidly finishing your impromptu meal."
						+ "</p>";
				
			} else {
				return UtilText.parse(target,
						"<p>"
						+ "[npc.Name] pulls out a package of 'Meat & Marrow', and, tearing off the paper packaging, quickly devours the slab of raw meat that was stored within."
						+ "</p>");
			}
		}
	},
	
	RACE_INGREDIENT_HARPY(
			"a",
			false,
			"bubblegum lollipop",
			"A bright pink lollipop, with a little ball of gum at its core."
				+ " Although it doesn't look out of the ordinary, it's somewhat unusual in the fact that it has an incredibly strong smell of bubblegum.",
			"raceHarpyLolipop",
			Colour.RACE_HARPY,
			60,
			Rarity.RARE,
			TFEssence.HARPY,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.RACE_LOLIPOP, null, null)))) {

		@Override
		public ItemEffectType getEnchantmentEffect() {
			return ItemEffectType.RACE_HARPY;
		}

		@Override
		public ItemType getEnchantmentItemType() {
			return ELIXIR;
		}

		@Override
		public String getUseName() {
			return "suck";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user == Main.game.getPlayer() && target == Main.game.getPlayer()) {
				return "<p>"
						+ "The moment you push the bright pink lollipop past your lips, your taste buds are overwhelmed by the sweet, sugary flavour of bubblegum."
						+ " Before you know what you're doing, you're letting out soft little feminine moans, which soon turn into desperate whines as you find yourself unable to think about anything other than wildly sucking on the object in your mouth."
						+ "</p>";
				
			} else {
				return UtilText.parse(target,
						"<p>"
						+ "[npc.Name] pulls out a bright pink lollipop, and quickly shoves it into [npc.her] mouth."
						+ "</p>");
			}
		}
	},
	
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
//			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_ANGEL, null, null)))) {
//
//		@Override
//		public String getUseName() {
//			return "suck";
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			if (user.isPlayer() && target.isPlayer()) {
//				return "<p>"
//							+ ""
//						+ "</p>";
//				
//			} else {
//				return "<p>"
//						+ "(You shouldn't be seeing this. x_x)"//TODO
//					+ "</p>";
//			}
//		}
//	},
	
	BOTTLED_ESSENCE_ARCANE(
			null,
			false,
			"Bottled Arcane Essence",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.GENERIC_ARCANE.getName()+" glow of an arcane essence flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceArcane",
			Colour.GENERIC_ARCANE,
			50,
			Rarity.LEGENDARY,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_ARCANE, null, null)))) {

		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user.isPlayer() && target.isPlayer()) {
				return "<p>"
						+ "Pulling the cork stopper out from the top of the little bottle, you release the arcane essence from its glass prison."
						+ " Drawn towards your powerful arcane aura, the essence immediately darts towards you, and with a little "+Colour.GENERIC_ARCANE.getName()+" flash, it disappears from sight."
						+ " You feel a subtle change in your aura, letting you know that you've successfully absorbed the essence."
					+ "</p>";
				
			} else {
				return "<p>"
						+ "(You shouldn't be seeing this. x_x)"//TODO
					+ "</p>";
			}
		}
	},
	
	BOTTLED_ESSENCE_CAT_MORPH(
			null,
			false,
			"Bottled Cat-morph Essence",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.RACE_CAT_MORPH.getName()+" glow of a cat-morph essence flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceCatMorph",
			Colour.RACE_CAT_MORPH,
			10,
			Rarity.COMMON,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_CAT_MORPH, null, null)))) {

		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user.isPlayer() && target.isPlayer()) {
				return "<p>"
							+ "Pulling the cork stopper out from the top of the little bottle, you release the cat-morph essence from its glass prison."
							+ " Drawn towards your powerful arcane aura, the essence immediately darts towards you, and with a little "+Colour.RACE_CAT_MORPH.getName()+" flash, it disappears from sight."
							+ " You feel a subtle change in your aura, letting you know that you've successfully absorbed the essence."
						+ "</p>";
				
			} else {
				return "<p>"
						+ "(You shouldn't be seeing this. x_x)"//TODO
					+ "</p>";
			}
		}
	},
	
	BOTTLED_ESSENCE_DEMON(
			null,
			false,
			"Bottled Demon Essence",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.RACE_DEMON.getName()+" glow of a demon essence flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceDemon",
			Colour.RACE_DEMON,
			40,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_DEMON, null, null)))) {

		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user.isPlayer() && target.isPlayer()) {
				return "<p>"
							+ "Pulling the cork stopper out from the top of the little bottle, you release the demon essence from its glass prison."
							+ " Drawn towards your powerful arcane aura, the essence immediately darts towards you, and with a little "+Colour.RACE_DEMON.getName()+" flash, it disappears from sight."
							+ " You feel a subtle change in your aura, letting you know that you've successfully absorbed the essence."
						+ "</p>";
				
			} else {
				return "<p>"
						+ "(You shouldn't be seeing this. x_x)"//TODO
					+ "</p>";
			}
		}
	},
	
	BOTTLED_ESSENCE_DOG_MORPH(
			null,
			false,
			"Bottled Dog-morph Essence",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.RACE_DOG_MORPH.getName()+" glow of a dog-morph essence flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceDogMorph",
			Colour.RACE_DOG_MORPH,
			10,
			Rarity.COMMON,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_DOG_MORPH, null, null)))) {

		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user.isPlayer() && target.isPlayer()) {
				return "<p>"
							+ "Pulling the cork stopper out from the top of the little bottle, you release the dog-morph essence from its glass prison."
							+ " Drawn towards your powerful arcane aura, the essence immediately darts towards you, and with a little "+Colour.RACE_DOG_MORPH.getName()+" flash, it disappears from sight."
							+ " You feel a subtle change in your aura, letting you know that you've successfully absorbed the essence."
						+ "</p>";
				
			} else {
				return "<p>"
						+ "(You shouldn't be seeing this. x_x)"//TODO
					+ "</p>";
			}
		}
	},
	
	BOTTLED_ESSENCE_HARPY(
			null,
			false,
			"Bottled Harpy Essence",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.RACE_HARPY.getName()+" glow of a harpy essence flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceHarpy",
			Colour.RACE_HARPY,
			20,
			Rarity.UNCOMMON,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_HARPY, null, null)))) {

		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user.isPlayer() && target.isPlayer()) {
				return "<p>"
							+ "Pulling the cork stopper out from the top of the little bottle, you release the harpy essence from its glass prison."
							+ " Drawn towards your powerful arcane aura, the essence immediately darts towards you, and with a little "+Colour.RACE_HARPY.getName()+" flash, it disappears from sight."
							+ " You feel a subtle change in your aura, letting you know that you've successfully absorbed the essence."
						+ "</p>";
				
			} else {
				return "<p>"
						+ "(You shouldn't be seeing this. x_x)"//TODO
					+ "</p>";
			}
		}
	},
	
	BOTTLED_ESSENCE_HORSE_MORPH(
			null,
			false,
			"Bottled Horse-morph Essence",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.RACE_HORSE_MORPH.getName()+" glow of a horse-morph essence flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceHorseMorph",
			Colour.RACE_HORSE_MORPH,
			10,
			Rarity.COMMON,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_HORSE_MORPH, null, null)))) {

		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user.isPlayer() && target.isPlayer()) {
				return "<p>"
							+ "Pulling the cork stopper out from the top of the little bottle, you release the horse-morph essence from its glass prison."
							+ " Drawn towards your powerful arcane aura, the essence immediately darts towards you, and with a little "+Colour.RACE_HORSE_MORPH.getName()+" flash, it disappears from sight."
							+ " You feel a subtle change in your aura, letting you know that you've successfully absorbed the essence."
						+ "</p>";
				
			} else {
				return "<p>"
						+ "(You shouldn't be seeing this. x_x)"//TODO
					+ "</p>";
			}
		}
	},
	
	BOTTLED_ESSENCE_HUMAN(
			null,
			false,
			"Bottled Human Essence",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.RACE_HUMAN.getName()+" glow of a human essence flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceHuman",
			Colour.RACE_HUMAN,
			10,
			Rarity.COMMON,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_HUMAN, null, null)))) {

		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user.isPlayer() && target.isPlayer()) {
				return "<p>"
							+ "Pulling the cork stopper out from the top of the little bottle, you release the human essence from its glass prison."
							+ " Drawn towards your powerful arcane aura, the essence immediately darts towards you, and with a little "+Colour.RACE_HUMAN.getName()+" flash, it disappears from sight."
							+ " You feel a subtle change in your aura, letting you know that you've successfully absorbed the essence."
						+ "</p>";
				
			} else {
				return "<p>"
						+ "(You shouldn't be seeing this. x_x)"//TODO
					+ "</p>";
			}
		}
	},
	
	BOTTLED_ESSENCE_WOLF_MORPH(
			null,
			false,
			"Bottled Wolf-morph Essence",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+Colour.RACE_WOLF_MORPH.getName()+" glow of a wolf-morph essence flickers and swirls about in a mesmerising, cyclical pattern.",
			"bottledEssenceWolfMorph",
			Colour.RACE_WOLF_MORPH,
			10,
			Rarity.COMMON,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_WOLF_MORPH, null, null)))) {

		@Override
		public String getUseName() {
			return "absorb";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user.isPlayer() && target.isPlayer()) {
				return "<p>"
							+ "Pulling the cork stopper out from the top of the little bottle, you release the wolf-morph essence from its glass prison."
							+ " Drawn towards your powerful arcane aura, the essence immediately darts towards you, and with a little "+Colour.RACE_WOLF_MORPH.getName()+" flash, it disappears from sight."
							+ " You feel a subtle change in your aura, letting you know that you've successfully absorbed the essence."
						+ "</p>";
				
			} else {
				return "<p>"
						+ "(You shouldn't be seeing this. x_x)"//TODO
					+ "</p>";
			}
		}
	},
	
	
	
	// Specials:
	
	HARPY_MARTRIARCH_BIMBO_LOLLIPOP(
			null,
			false,
			"[bimboHarpy.name]'s lollipop",
			"A swirly lollipop that you got from the harpy matriarch [bimboHarpy.name]."
				+ " Although it doesn't look out of the ordinary, you're pretty sure that eating it would result in a potent transformation...",
			"bimboLolipop",
			Colour.RARITY_LEGENDARY,
			500,
			Rarity.LEGENDARY,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.BIMBO_LOLLIPOP, null, null)))) {

		@Override
		public String getUseName() {
			return "suck";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user == Main.game.getPlayer() && target == Main.game.getPlayer()) {
				return "<p>"
							+ "Bringing the lollipop up to your [pc.lips+], you dart out your [pc.tongue] and give it a long, wet lick."
							+ " An intense, sweet flavour fills your mouth, quite unlike anything you've ever tasted before."
							+ " Before you know what you're doing, you're pressing your [pc.lips] up against the delicious candy, letting out little whining noises as you find yourself unable to stop sucking and licking it..."
						+ "</p>";
				
			} else {
				return "<p>"
						+ "You use the lollipop! (You shouldn't be seeing this. x_x)"//TODO
					+ "</p>";
			}
		}
	},
	
	HARPY_MARTRIARCH_NYMPHO_LOLLIPOP(
			null,
			false,
			"[nymphoHarpy.name]'s lollipop",
			"A cock-shaped lollipop that you got from the harpy matriarch [nymphoHarpy.name]."
				+ " Although it looks to be made from regular candy, you're pretty sure that eating it would result in a potent transformation...",
			"nymphoLolipop",
			Colour.RARITY_LEGENDARY,
			500,
			Rarity.LEGENDARY,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.NYMPHO_LOLLIPOP, null, null)))) {

		@Override
		public String getUseName() {
			return "suck";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user == Main.game.getPlayer() && target == Main.game.getPlayer()) {
				return "<p>"
							+ "Bringing the lollipop up to your [pc.lips+], you dart out your [pc.tongue] and give it a long, wet lick."
							+ " An intense, sweet flavour fills your mouth, quite unlike anything you've ever tasted before."
							+ " Before you know what you're doing, you're pushing the delicious, cock-shaped candy into your mouth, letting out lewd moans as you find yourself unable to stop sucking and licking it..."
						+ "</p>";
				
			} else {
				return "<p>"
						+ "You use the lollipop! (You shouldn't be seeing this. x_x)"//TODO
					+ "</p>";
			}
		}
	},
	
	HARPY_MARTRIARCH_DOMINANT_PERFUME(
			null,
			false,
			"[dominantHarpy.name]'s perfume",
			"A bottle of perfume that you got from the harpy matriarch [nymphoHarpy.name]."
				+ " Although it looks to contain normal perfume, you're pretty sure that using it would result in a potent transformation...",
			"dominantPerfume",
			Colour.RARITY_LEGENDARY,
			500,
			Rarity.LEGENDARY,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.DOMINANT_PERFUME, null, null)))) {

		@Override
		public String getUseName() {
			return "spray";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user == Main.game.getPlayer() && target == Main.game.getPlayer()) {
				return "<p>"
							+ "Bringing the bottle of perfume up to your neck, you give it a little squirt."
							+ " Although only a small amount of liquid shoots out, the entire bottle's contents are instantly drained, leaving you holding an empty bottle."
							+ " As you look down at it in surprise, the strong, feminine scent rises up to overpower your senses,"
							+ " and you find yourself letting out a desperate moan as the perfume's powerful enchantment starts to make itself known..."
						+ "</p>";
				
			} else {
				return "<p>"
						+ "You use the perfume! (You shouldn't be seeing this. x_x)"//TODO
					+ "</p>";
			}
		}
	},
	
	// Crafting outputs:
	
//	DRAUGHT("",
//			false,
//			"potion",
//			"Refined potion.",
//			"refined_draught_container",
//			Colour.CLOTHING_PINK,
//			25,
//			Rarity.RARE,
//			null,
//			null) {
//
//		@Override
//		public ItemEffectType getEnchantmentEffect() {
//			return null;
//		}
//
//		@Override
//		public ItemType getEnchantmentItemType() {
//			return null;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return true;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return true;
//		}
//
//		@Override
//		public String getUseName() {
//			return "drink";
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			if (user == Main.game.getPlayer() && target == Main.game.getPlayer()) {
//				return "<p>"
//						+ "You pull out the bottle's little stopper and drink the potion."
//						+ "</p>";
//				
//			} else {
//				return UtilText.parse(target,
//						"<p>"
//						+ "[npc.Name] drinks the potion."
//						+ "</p>");
//			}
//		}
//	},
	
	POTION("",
			false,
			"potion",
			"Refined potion.",
			"refined_potion_container",
			Colour.CLOTHING_PINK,
			25,
			Rarity.RARE,
			null,
			null) {

		@Override
		public ItemEffectType getEnchantmentEffect() {
			return null;
		}

		@Override
		public ItemType getEnchantmentItemType() {
			return null;
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
			if (user == Main.game.getPlayer() && target == Main.game.getPlayer()) {
				return "<p>"
						+ "You drink the potion."
						+ "</p>";
				
			} else {
				return UtilText.parse(target,
						"<p>"
						+ "[npc.Name] drinks the potion."
						+ "</p>");
			}
		}
	},
	
	ELIXIR("",
			false,
			"elixir",
			"Refined elixir.",
			"refined_elixir_container",
			Colour.CLOTHING_PINK,
			25,
			Rarity.EPIC,
			null,
			null) {

		@Override
		public ItemEffectType getEnchantmentEffect() {
			return null;
		}

		@Override
		public ItemType getEnchantmentItemType() {
			return null;
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
			if (user == Main.game.getPlayer() && target == Main.game.getPlayer()) {
				return "<p>"
						+ "You drink the potion."
						+ "</p>";
				
			} else {
				return UtilText.parse(target,
						"<p>"
						+ "[npc.Name] drinks the potion."
						+ "</p>");
			}
		}
	},
	
	
	// Non-TF:

	DYE_BRUSH("a",
			false,
			"dye-brush",
			"A small, very ordinary-looking brush, of the sort used for fine detailing on canvas or models."
					+ " On closer inspection, you notice a very faint purple glow emanating from the brush's tip, revealing its true nature as an arcane-enchanted dye-brush.",
			"dyeBrush",
			Colour.CLOTHING_WHITE,
			50,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.DYE_BRUSH, null, null)))) {

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
	},

	CONDOM("a",
			false,
			"condom",
			"A condom, wrapped in a square piece of foil. The brand name 'Stallion' is clearly displayed in bold red lettering, and a small description on the other side informs you that, due to an arcane enchantment, 'one-size fits all'.",
			"condom",
			Colour.CLOTHING_WHITE,
			5,
			Rarity.COMMON,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.APPLY_CONDOM, null, null)))) {

		@Override
		public String getUseName() {
			return "use";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			// This has a special description in sex (I think :s), so don't worry about giving it a good description.
			return "<p>"
					+ "You put the condom on."
					+ "</p>"; 
		}

		@Override
		public boolean isAbleToBeUsed(GameCharacter target) {
			if(target.getPenisType()!=PenisType.NONE && target.isCoverableAreaExposed(CoverableArea.PENIS) && !target.isWearingCondom())
				return true;
			else
				return false;
		}

		@Override
		public String getUnableToBeUsedDescription(GameCharacter target) {
			if(target.getPenisType()==PenisType.NONE){
				if(target.isPlayer())
					return "You can't use a condom (in the way that it's meant to be used) without a penis!";
				else
					return "[npc.Name] can't use a condom as [npc.she] doesn't have a penis!";
				
			}else if(!target.isCoverableAreaExposed(CoverableArea.PENIS)){
				if(target.isPlayer())
					return "You'll need to get access to your penis before you can use a condom on it!";
				else
					return "You'll need to get access to [npc.name]'s [npc.cock] before you can get [npc.herHim] to use a condom!";
				
			}else if(target.isWearingCondom()){
				if(target.isPlayer())
					return "You're already wearing a condom!";
				else
					return target.getName("the")+" is already wearing a condom!";
					
			}else
				return "This item cannot be used in this way!";
		}

		@Override
		public boolean isAbleToBeUsedInCombat() {
			return false;
		}

		@Override
		public boolean isAbleToBeUsedFromInventory() {
			return false;
		}
	},

	CONDOM_USED("a",
			false,
			"used condom",
			"A used condom, tied at the top and filled with someone's cum. You'd have to be pretty dirty-minded to think of a use for this... <b>(Currently not implemented...)</b>",
			"condomUsed",
			Colour.CLOTHING_WHITE,
			0,
			Rarity.COMMON,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.USED_CONDOM_DRINK, null, null)))) {
		
		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "Untying the top of the used condom, you bring it up to your lips and swallow its slimy contents.";
		}

		@Override
		public boolean isAbleToBeUsed(GameCharacter target) {
			return true;
		}

		@Override
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "You can't think of a use for this. Maybe it's best to throw it away...</br>"
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
	},

	VIXENS_VIRILITY("a",
			false,
			"Vixen's Virility",
			"A small pill, packaged in a little foil and plastic wrapper. On the front of the foil, there's a small stylised picture of a heavily pregnant girl, lying back and smiling as she strokes her swollen belly.",
			"vixensVirility",
			Colour.CLOTHING_PINK,
			5,
			Rarity.COMMON,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.VIXENS_VIRILITY, null, null)))) {

		@Override
		public String getUseName() {
			return "swallow";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user == Main.game.getPlayer())
				return "<p>"
							+ "Popping the little pink pill out of its foil wrapper, you quickly put it in your mouth and swallow it down."
						+ "</p>";
			else
				return UtilText.parse(user,
						"<p>"
							+ "[npc.Name] pops a <i>Vixen's Virility</i> pill out of its little foil wrapper, before quickly placing it in [npc.her] mouth and swallowing it down."
						+ "</p>");
		}

	},
	
	PROMISCUITY_PILL("a",
			false,
			"Promiscuity Pill",
			"A small pill, packaged in a little foil and plastic wrapper."
					+ " On the front of the foil, there's a before-and-after picture of a line of faceless men waiting their turn to ejaculate into a willing girl's hungry pussy."
					+ " The after image is of the girl showing off her flat stomach as she gives a thumbs up.",
			"vixensVirility",
			Colour.CLOTHING_BLUE,
			5,
			Rarity.COMMON,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.PROMISCUITY_PILL, null, null)))) {

		@Override
		public String getUseName() {
			return "swallow";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user == Main.game.getPlayer())
				return "<p>"
							+ "Popping the little blue pill out of its foil wrapper, you quickly put it in your mouth and swallow it down."
						+ "</p>";
			else
				return UtilText.parse(user,
						"<p>"
							+ "[npc.Name] pops a <i>Promiscuity Pill</i> pill out of its little foil wrapper, before quickly placing it in [npc.her] mouth and swallowing it down."
						+ "</p>");
		}
	},
	
	MOTHERS_MILK("a bottle of",
			false,
			"Mother's Milk",
			"A baby bottle filled with a rich, creamy milk."
			+ " On the side, a little sticker declares that this drink is able to speed up your pregnancy.",
			"mothers_milk",
			Colour.CLOTHING_WHITE,
			5,
			Rarity.COMMON,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.MOTHERS_MILK, null, null)))) {

		@Override
		public String getUseName() {
			return "drink";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user == Main.game.getPlayer())
				return "<p>"
							+ "Bringing the bottle up to your [pc.lips], you take the teat-like opening into your mouth, before greedily starting to suckle down the creamy liquid within."
						+ "</p>";
			else
				return UtilText.parse(user,
						"<p>"
							+ "[npc.Name] produces a bottle of <i>Mother's Milk</i>, and, taking the teat-like opening into [npc.her] mouth, [npc.she] greedily starts to suckle down the creamy liquid within."
						+ "</p>");
		}
	},
	
	BOOK_CAT_MORPH(
			null,
			false,
			"Curious kitties",
			"A book that details cat-morph society.",
			"book_race_cat_morph",
			Colour.RACE_CAT_MORPH,
			10,
			Rarity.LEGENDARY,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.BOOK_READ_CAT_MORPH, null, null)))) {

		@Override
		public boolean isAbleToBeUsed(GameCharacter target) {
			if(!Main.game.getPlayer().getRacesAdvancedKnowledge().contains(Race.CAT_MORPH)) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "You've already added this book to Lilaya's library! It would be best to just sell it...";
		}
		
		@Override
		public String getUseName() {
			return "read";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "<p>"
						+ "Opening the book, you read its contents..."
					+ "</p>";
		}
	},
	
	BOOK_DEMON(
			null,
			false,
			"Demonic origins",
			"A book about demons and where they come from.",
			"book_race_demon",
			Colour.RACE_DEMON,
			10,
			Rarity.LEGENDARY,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.BOOK_READ_DEMON, null, null)))) {

		@Override
		public boolean isAbleToBeUsed(GameCharacter target) {
			if(!Main.game.getPlayer().getRacesAdvancedKnowledge().contains(Race.DEMON)) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "You've already added this book to Lilaya's library! It would be best to just sell it...";
		}
		
		@Override
		public boolean canBeSold() {
			return false;
		}
		
		@Override
		public String getUseName() {
			return "read";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "<p>"
						+ "Opening the book, you read its contents..."
					+ "</p>";
		}
	},
	
	BOOK_DOG_MORPH(
			null,
			false,
			"Canine culture",
			"A book about dog-morphs and their culture.",
			"book_race_dog_morph",
			Colour.RACE_DOG_MORPH,
			10,
			Rarity.LEGENDARY,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.BOOK_READ_DOG_MORPH, null, null)))) {

		@Override
		public boolean isAbleToBeUsed(GameCharacter target) {
			if(!Main.game.getPlayer().getRacesAdvancedKnowledge().contains(Race.DOG_MORPH)) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "You've already added this book to Lilaya's library! It would be best to just sell it...";
		}
		
		@Override
		public boolean canBeSold() {
			return false;
		}
		
		@Override
		public String getUseName() {
			return "read";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "<p>"
						+ "Opening the book, you read its contents..."
					+ "</p>";
		}
	},
	
	BOOK_HARPY(
			null,
			false,
			"All about Harpies",
			"A book all about harpies, detailing their society and place within Dominion.",
			"book_race_harpy",
			Colour.RACE_HARPY,
			10,
			Rarity.LEGENDARY,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.BOOK_READ_HARPY, null, null)))) {
		
		@Override
		public boolean isAbleToBeUsed(GameCharacter target) {
			if(!Main.game.getPlayer().getRacesAdvancedKnowledge().contains(Race.HARPY)) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "You've already added this book to Lilaya's library! It would be best to just sell it...";
		}
		
		@Override
		public boolean canBeSold() {
			return false;
		}
		
		@Override
		public String getUseName() {
			return "read";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "<p>"
						+ "Opening the book, you read its contents..."
					+ "</p>";
		}
	},
	
	BOOK_HORSE_MORPH(
			null,
			false,
			"Equine Encyclopedia",
			"A book all about horse-morphs.",
			"book_race_horse_morph",
			Colour.RACE_HORSE_MORPH,
			10,
			Rarity.LEGENDARY,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.BOOK_READ_HORSE_MORPH, null, null)))) {
		
		@Override
		public boolean isAbleToBeUsed(GameCharacter target) {
			if(!Main.game.getPlayer().getRacesAdvancedKnowledge().contains(Race.HORSE_MORPH)) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "You've already added this book to Lilaya's library! It would be best to just sell it...";
		}
		
		@Override
		public boolean canBeSold() {
			return false;
		}
		
		@Override
		public String getUseName() {
			return "read";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "<p>"
						+ "Opening the book, you read its contents..."
					+ "</p>";
		}
	},
	
	BOOK_HUMAN(
			null,
			false,
			"Concerning Humans",
			"A book about humans and their place within Dominion society.",
			"book_race_human",
			Colour.RACE_HUMAN,
			10,
			Rarity.LEGENDARY,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.BOOK_READ_HUMAN, null, null)))) {

		@Override
		public boolean isAbleToBeUsed(GameCharacter target) {
			if(!Main.game.getPlayer().getRacesAdvancedKnowledge().contains(Race.HUMAN)) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "You've already added this book to Lilaya's library! It would be best to just sell it...";
		}
		
		@Override
		public boolean canBeSold() {
			return false;
		}
		
		@Override
		public String getUseName() {
			return "read";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "<p>"
						+ "Opening the book, you read its contents..."
					+ "</p>";
		}
	},
	
	BOOK_WOLF_MORPH(
			null,
			false,
			"Prowling Lupines",
			"A book all about wolf-morphs.",
			"book_race_wolf_morph",
			Colour.RACE_WOLF_MORPH,
			10,
			Rarity.LEGENDARY,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.BOOK_READ_WOLF_MORPH, null, null)))) {

		@Override
		public boolean isAbleToBeUsed(GameCharacter target) {
			if(!Main.game.getPlayer().getRacesAdvancedKnowledge().contains(Race.WOLF_MORPH)) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "You've already added this book to Lilaya's library! It would be best to just sell it...";
		}
		
		@Override
		public boolean canBeSold() {
			return false;
		}
		
		@Override
		public String getUseName() {
			return "read";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "<p>"
						+ "Opening the book, you read its contents..."
					+ "</p>";
		}
	},
	
	// Why did I make this?
	EGGPLANT(
			null,
			false,
			"Eggplant",
			"A delicate, tropical perennial often cultivated as a tender or half-hardy annual in temperate climates. Also it kind of looks like a penis if you squint.",
			"eggplant",
			Colour.GENERIC_ARCANE,
			10,
			Rarity.LEGENDARY,
			null,
			Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ItemEffectType.EGGPLANT, null, null)))) {

		@Override
		public boolean canBeSold() {
			return false;
		}
		
		@Override
		public String getUseName() {
			return "eat";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if (user == Main.game.getPlayer() && target == Main.game.getPlayer()) {
				return "<p>"
							+ "You eat the eggplant."
						+ "</p>";
				
			} else {
				return UtilText.parse(user,
						"<p>"
							+ "[npc.Name] eats an eggplant."
						+ "</p>");
			}
		}
	};
	
	
	// Standard non-racial transformatives:
	
//	MASOCHISTS_HEAVEN("a bottle of", "it", "Masochist's Heaven",
//			"A clear plastic bottle of Masochist's Heaven. A girl, lying back in the missionary position, is prominently featured on the label, screaming in delight as a huge cock painfully stretches out her tight, dry pussy.",
//			"potion", Colour.CLOTHING_WHITE, true, 25, Rarity.RARE, "Decreases orifice capacity, elasticity, and wetness.") {
//		
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user == Main.game.getPlayer() && target == Main.game.getPlayer())
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
//	},
//	GOING_BIG("a bottle of", "it", "Going Big",
//			"A clear plastic bottle of a drink branded as 'Going Big'. A girl, presenting herself doggy-style, is prominently featured on the label, crying out in delight as a gigantic cock easily stretches out her tight, wet pussy.",
//			"potion", Colour.CLOTHING_WHITE, true, 25, Rarity.RARE, "Decreases orifice capacity. Increases elasticity and wetness.") {
//		
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user == Main.game.getPlayer() && target == Main.game.getPlayer())
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
//	},
//	WET_KISS("a bottle of", "it", "Wet Kiss",
//			"A clear plastic bottle of the branded drink <i>Wet Kiss</i>, filled with a rose-coloured liquid. The label on the front is devoid"
//					+ " of any images, and instead simply displays the name <i>Wet Kiss</i>, along with some incomprehensible technical details of the drink's manufacturing process.",
//			"potion", Colour.CLOTHING_PINK_LIGHT, true, 25, Rarity.RARE, "Increases orifice wetness and capacity.") {
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user == Main.game.getPlayer() && target == Main.game.getPlayer())
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
//	},
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
//			if (user == Main.game.getPlayer() && target == Main.game.getPlayer())
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
//	},
//	BUBBLE_CREAM("a bottle of", "it", "bubble-cream",
//			"A clear plastic bottle of bubble-cream. Just like bubble-milk, the cream doesn't physically bubble, but instead refers"
//					+ " to the feeling you get after drinking it. A greater cow-girl with three pairs of gigantic breasts is prominently featured on the label, smiling as she" + " milks her gigantic udder-tits into a metal bucket.",
//			"potion", Colour.CLOTHING_WHITE, true, 100, Rarity.EPIC, "Increases breast size, count, and lactation.") {
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user == Main.game.getPlayer() && target == Main.game.getPlayer())
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
//	},
//
//	THROBBING_GLOW("a bottle of", "it", "Throbbing Glow",
//			"A clear plastic bottle of the energy drink <i>Throbbing Glow</i>, filled with a bright blue liquid. A"
//					+ " well-endowed greater horse-boy is prominently featured on the label, stroking his gigantic member with one hand, while bringing a bottle of <i>Thobbing Glow</i>" + " to his lips with the other.",
//			"potion", Colour.CLOTHING_BLUE_LIGHT, true, 25, Rarity.RARE, "Increases penis and testicle size. Increases cum production.") {
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user == Main.game.getPlayer() && target == Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the plastic cap and gulp down the bottle of <i>Thobbing Glow</i>. It tastes a little sour, sort of like"
//						+ " a cheap, sugary energy drink. As the last few drops slide down your throat, you feel a throbbing, deep-seated heat take root in your groin.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of <i>Thobbing Glow</i>, unscrews the cap," + " and gulps it all down.</p>");
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
//	},
//
//	FLOWERS_WARMTH("a bottle of", "it", "Flower's Warmth",
//			"A clear plastic bottle of the energy drink <i>Flower's Warmth</i>, filled with a pale pink liquid. A"
//					+ " greater cat-girl is featured prominently on the label, leaning back in a chair as another greater cat-girl laps hungrily at her exposed pussy.",
//			"potion", Colour.CLOTHING_PINK_LIGHT, true, 25, Rarity.RARE, "Increases the body's feminine characteristics.") {
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user == Main.game.getPlayer() && target == Main.game.getPlayer())
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
//	},
//
//	
//
//	SCARLET_WHISPER("a bottle of", "it", "Scarlet Whisper",
//			"A delicate glass bottle of <i>Scarlet Whisper</i>, filled with a bright pink liquid. The label on the front displays"
//					+ " the name <i>Scarlet Whisper</i> in a delicate, feminine font. The rest of the label is covered in simple images of pale pink flowers and looping linework.",
//			"potion", Colour.CLOTHING_PINK, true, 25, Rarity.RARE, "Increases all feminine aspects.") {
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user == Main.game.getPlayer() && target == Main.game.getPlayer())
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
//	},
//	FLAMING_THUNDER("a bottle of", "it", "Flaming Thunder",
//			"A thick glass bottle of <i>Flaming Thunder</i>, filled with a deep blue liquid. The label on the front displays"
//					+ " the name <i>Flaming Thunder</i> in a bold, striking font. The rest of the label is covered in simple images of lightning and bold linework.",
//			"potion", Colour.CLOTHING_BLUE, true, 25, Rarity.RARE, "Increases all masculine aspects.") {
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user == Main.game.getPlayer() && target == Main.game.getPlayer())
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
//	},
//
	
	public static List<ItemType> availableItems = new ArrayList<>();
	
	static{
		for(ItemType t : ItemType.values()){
			if(t!=ItemType.POTION && t!=ItemType.ELIXIR
					&& t!=ItemType.HARPY_MARTRIARCH_BIMBO_LOLLIPOP && t!=ItemType.HARPY_MARTRIARCH_DOMINANT_PERFUME && t!=ItemType.HARPY_MARTRIARCH_NYMPHO_LOLLIPOP
					&& t!=ItemType.BOOK_CAT_MORPH && t!=ItemType.BOOK_DEMON && t!=ItemType.BOOK_DOG_MORPH && t!=ItemType.BOOK_HARPY && t!=ItemType.BOOK_HORSE_MORPH && t!=ItemType.BOOK_HUMAN && t!=ItemType.BOOK_WOLF_MORPH
					&& t!=ItemType.BOTTLED_ESSENCE_ARCANE && t!=ItemType.BOTTLED_ESSENCE_CAT_MORPH && t!=ItemType.BOTTLED_ESSENCE_DEMON
					&& t!=ItemType.BOTTLED_ESSENCE_DOG_MORPH && t!=ItemType.BOTTLED_ESSENCE_HARPY && t!=ItemType.BOTTLED_ESSENCE_HORSE_MORPH
					&& t!=ItemType.BOTTLED_ESSENCE_HUMAN && t!=ItemType.BOTTLED_ESSENCE_WOLF_MORPH
					&& t!=ItemType.EGGPLANT) {
				availableItems.add(t);
			}
			
			if(Main.game.isStarted()) {
				if(Main.game.getPlayer().hasSideQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
					if(t==ItemType.BOTTLED_ESSENCE_ARCANE || t==ItemType.BOTTLED_ESSENCE_CAT_MORPH || t==ItemType.BOTTLED_ESSENCE_DEMON
							|| t==ItemType.BOTTLED_ESSENCE_DOG_MORPH || t==ItemType.BOTTLED_ESSENCE_HARPY || t==ItemType.BOTTLED_ESSENCE_HORSE_MORPH
							|| t==ItemType.BOTTLED_ESSENCE_HUMAN || t==ItemType.BOTTLED_ESSENCE_WOLF_MORPH) {
						availableItems.add(t);
					}
				}
			}
		}
	}
	

	private String determiner, name, description, pathName;
	private boolean plural;
	private Colour colourShade;
	private int value;
	private Rarity rarity;
	protected String SVGString;
	private TFEssence relatedEssence;
	protected List<ItemEffect> effects;

	private ItemType(
			String determiner,
			boolean plural,
			String name,
			String description,
			String pathName,
			Colour colourShade,
			int value,
			Rarity rarity,
			TFEssence relatedEssence,
			List<ItemEffect> effects) {

		this.determiner = determiner;
		this.plural = plural;
		this.name = name;
		this.description = description;
		this.pathName = pathName;

		this.value = value;
		this.rarity = rarity;
		
		this.relatedEssence = relatedEssence;
		
		if(effects==null) {
			this.effects = new ArrayList<>();
		} else {
			this.effects=effects;
		}

		if (colourShade == null) {
			this.colourShade = com.base.utils.Colour.CLOTHING_BLACK;
		} else {
			this.colourShade = colourShade;
		}
		
		// Set this item's file image:
		try {
			InputStream is = this.getClass().getResourceAsStream("/com/base/res/items/" + pathName + ".svg");
			String s = Util.inputStreamToString(is);

			s = s.replaceAll("#ff2a2a", this.colourShade.getShades()[0]);
			s = s.replaceAll("#ff5555", this.colourShade.getShades()[1]);
			s = s.replaceAll("#ff8080", this.colourShade.getShades()[2]);
			s = s.replaceAll("#ffaaaa", this.colourShade.getShades()[3]);
			s = s.replaceAll("#ffd5d5", this.colourShade.getShades()[4]);
			SVGString = s;

			is.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static AbstractItem generateItem(ItemType itemType) {
		return new AbstractItem(itemType) {
			private static final long serialVersionUID = 1L;
		};
	}
	
	public List<ItemEffect> getEffects() {
		return effects;
	}
	
	public boolean canBeSold() {
		return true;
	}
	
	// Enchantments:
	
	public ItemEffectType getEnchantmentEffect() {
		return null;
	}
	
	public TFEssence getRelatedEssence() {
		return relatedEssence;
	}
	
	public ItemType getEnchantmentItemType() {
		return null;
	}
	
	// Getters & setters:

	public String getDeterminer() {
		return (determiner!=null?determiner:"");
	}

	public boolean isPlural() {
		return plural;
	}

	public String getName(boolean displayName) {
		if(displayName) {
			return Util.capitaliseSentence((determiner!=null?determiner:"") + " <span style='color: " + rarity.getColour().toWebHexString() + ";'>" + name + "</span>");
		} else {
			return name;
		}
	}

	public String getDescription() {
		return description;
	}

	public String getDisplayName(boolean withRarityColour) {
		return Util.capitaliseSentence((determiner!=null?determiner:"") + (withRarityColour ? (" <span style='color: " + rarity.getColour().toWebHexString() + ";'>" + name + "</span>") : name));
	}

	public String getPathName() {
		return pathName;
	}

	public Colour getColour() {
		return colourShade;
	}

	public int getValue() {
		return value;
	}

	public String getSVGString() {
		return SVGString;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public String getUseName() {
		return "use";
	}
	
	public String getUseDescription(GameCharacter user, GameCharacter target) {
		return "<p>"
					+ "You use the item."
				+ "</p>";
	}

	public boolean isAbleToBeUsedFromInventory() {
		return true;
	}
	public boolean isAbleToBeUsed(GameCharacter target) {
		if(Main.game.isInCombat() && !target.isPlayer())
			return false;
		else
			return true;
	}
	public boolean isAbleToBeUsedInSex() {
		return true;
	}
	public boolean isAbleToBeUsedInCombat() {
		return true;
	}
	
	public boolean isConsumedOnUse() {
		return true;
	}
	
	public String getUnableToBeUsedFromInventoryDescription() {
		return "This item cannot be used in this way!";
	}
	
	public String getUnableToBeUsedDescription(GameCharacter target) {
		return "This item cannot be used in this way!";
	}
	
	public String getDyeBrushEffects(ClothingType clothingType, Colour colour) {
		return "<p>"
					+ "As you take hold of the Dye-brush, you see the purple glow around the tip growing in strength."
					+ " The closer you move it to your " + clothingType.getName()
					+ ", the brighter the glow becomes, until suddenly, images of different colours start flashing through your mind."
					+ " As you touch the bristles to the " + clothingType.getName() + "'s surface, the Dye-brush instantly evaporates!"
					+ " You see that the arcane enchantment has dyed the " + clothingType.getName() + " " + colour.getName() + "."
				+ "</p>";
	}

}
