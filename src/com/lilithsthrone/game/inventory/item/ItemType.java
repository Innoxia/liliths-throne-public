package com.lilithsthrone.game.inventory.item;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectTimer;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.84
 * @version 0.4.0
 * @author Innoxia
 */
public class ItemType {
	
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
	
	public static AbstractItemType FETISH_UNREFINED = new AbstractItemType(500,
			"a vial of",
			false,
			"Mystery Kink",
			"Mystery Kinks",
			"A delicate glass bottle, filled with a viscous, glowing-pink liquid."
					+ " From the label on one side reading 'Mystery Kink', it's quite safe to assume that this concoction carries a potent enchantment, which somehow influences the drinker's fetishes.",
			"fetishDrink",
			PresetColour.GENERIC_SEX,
			null,
			null,
			Rarity.EPIC,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.MYSTERY_KINK)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.BAT_CAVERNS_SPAWN,
					ItemTag.MISC_TF_ITEM,
					ItemTag.SOLD_BY_RALPH,
					ItemTag.DRINK)) {
		@Override
		public boolean isFetishGiving() {
			return true;
		}
		
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
			PresetColour.FETISH,
			null,
			null,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(ItemTag.DRINK)) {
		@Override
		public boolean isFetishGiving() {
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
			PresetColour.RACE_HUMAN,
			null,
			null,
			Rarity.LEGENDARY,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.ADDICTION_REMOVAL)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.BAT_CAVERNS_SPAWN,
					ItemTag.SOLD_BY_RALPH,
					ItemTag.DRINK_QUALITY)) {
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
					+ " Being a refined form of 'Angel's Nectar', this liquid has lost its ability to remove addictions, but is instead able to permanently lower the corruption of whoever drinks it...",
			"addictionRemovalRefined",
			PresetColour.RACE_HUMAN,
			null,
			null,
			Rarity.LEGENDARY,
			null,
			Util.newArrayListOfValues(ItemTag.DRINK_QUALITY)) {
		@Override
		public String getUseName() {
			return "drink";
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
			PresetColour.BASE_BLUE_LIGHT,
			null,
			null,
			Rarity.EPIC,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.MUSHROOMS)),
			Util.newArrayListOfValues(
					ItemTag.BAT_CAVERNS_SPAWN,
					ItemTag.CONTRABAND_LIGHT)) {
		@Override
		public boolean isTransformative() {
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
	
	public static AbstractItemType BOTTLED_ESSENCE_ARCANE = new AbstractItemType(
			40,
			null,
			false,
			"Bottled Arcane Essence",
			"Bottled Arcane Essences",
			"A small glass bottle, with a little cork stopper wedged firmly in the top."
					+ " Inside, the swirling "+PresetColour.GENERIC_ARCANE.getName()+" glow of an arcane essence flickers and swirls about in a mesmerising, cyclical pattern.",
			null,
			PresetColour.GENERIC_ARCANE,
			null,
			null,
			Rarity.EPIC,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_ARCANE)),
			Util.newArrayListOfValues(ItemTag.ESSENCE)) {
		@Override
		public String getUseName() {
			return "absorb";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getEssenceAbsorptionText(PresetColour.GENERIC_ARCANE, user, target);
		}
		@Override
		public String getSVGString() {
			return getEssenceSvg(Subspecies.LILIN);
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
			PresetColour.RARITY_LEGENDARY,
			null,
			null,
			Rarity.LEGENDARY,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BIMBO_LOLLIPOP)),
			Util.newArrayListOfValues(
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.NOT_FOR_SALE,
					ItemTag.FOOD)) {

		
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
			PresetColour.RARITY_LEGENDARY,
			null,
			null,
			Rarity.LEGENDARY,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.NYMPHO_LOLLIPOP)),
			Util.newArrayListOfValues(
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.NOT_FOR_SALE,
					ItemTag.FOOD)) {

		
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
			PresetColour.RARITY_LEGENDARY,
			null,
			null,
			Rarity.LEGENDARY,
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
	
	public static AbstractItemType POTION = new AbstractItemType(500,
			"",
			false,
			"potion",
			"potions",
			"Created by infusing arcane essences with a consumable item, potions such as these can hold a huge number of restorative effects or performance enhancements."
					+ " As potion creation is limited to those with a high level of arcane proficiency, such as demons, they are quite rare, and fetch a high price.",
			"refined_potion_container",
			PresetColour.CLOTHING_PINK,
			null,
			null,
			Rarity.RARE,
			null,
			null) {
		@Override
		public boolean isTransformative() {
			return false;
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
	
	public static AbstractItemType ELIXIR = new AbstractItemType(750,
			"",
			false,
			"elixir",
			"elixirs",
			"Created by infusing arcane essences with a consumable item, elixirs such as these can hold a huge number of transformative effects."
					+ " As elixir creation is limited to those with a high level of arcane proficiency, such as demons, they are quite rare, and fetch a high price.",
			"refined_elixir_container",
			PresetColour.CLOTHING_PINK,
			null,
			null,
			Rarity.EPIC,
			null,
			null) {
		@Override
		public boolean isTransformative() {
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
			PresetColour.CLOTHING_WHITE,
			null,
			null,
			Rarity.EPIC,
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
		public boolean isAbleToBeUsedInCombatAllies() {
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
			PresetColour.CLOTHING_WHITE,
			null,
			null,
			Rarity.EPIC,
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
		public boolean isAbleToBeUsedInCombatAllies() {
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
	
	public static AbstractItemType CONDOM_USED_WEBBING = new AbstractItemType(1,
			"a",
			false,
			"used condom-webbing",
			"used condom-webbings",
			"A used condom-like sheath of spider's webbing, tied at the top and filled with someone's cum. While most people would simply throw this away, those with a particularly dirty mind might find a use for it...",
			"condomUsedWebbing",
			PresetColour.CLOTHING_WHITE,
			null,
			null,
			Rarity.COMMON,
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
					"Untying the top of the used condom-webbing, you bring it up to your lips and swallow the slimy contents.",
					"Untying the top of the used condom-webbing, you bring it up to [npc.namePos] [npc.lips], and force [npc.herHim] to swallow the slimy contents.",
					"Untying the top of the used condom-webbing, [npc.name] brings it up to [npc.her] [npc.lips], and swallows the slimy contents.",
					"Untying the top of the used condom-webbing, [npc.name] brings it up to your [pc.lips], and forces you to swallow the slimy contents.");
		}
		@Override
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "You can't think of a use for this. Maybe it's best to throw it away...<br/>"
					+ "(You need have at least a <b style='color:"+CorruptionLevel.THREE_DIRTY.getColour().toWebHexString()+";'>"+CorruptionLevel.THREE_DIRTY.getName()+"</b> level of corruption to know how to use this!)";
		}
		@Override
		public boolean isAbleToBeUsedInCombatAllies() {
			return false;
		}
		@Override
		public boolean isAbleToBeUsedInSex() {
			return false;
		}
	};

	public static AbstractItemType CONDOM_USED = new AbstractItemType(1,
			"a",
			false,
			"used condom",
			"used condoms",
			"A used condom, tied at the top and filled with someone's cum. While most people would simply throw this away, those with a particularly dirty mind might find a use for it...",
			"condomUsed",
			PresetColour.CLOTHING_WHITE,
			null,
			null,
			Rarity.COMMON,
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
					"Untying the top of the used condom, you bring it up to [npc.namePos] [npc.lips], and force [npc.herHim] to swallow the slimy contents.",
					"Untying the top of the used condom, [npc.name] brings it up to [npc.her] [npc.lips], and swallows the slimy contents.",
					"Untying the top of the used condom, [npc.name] brings it up to your [pc.lips], and forces you to swallow the slimy contents.");
		}
		@Override
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "You can't think of a use for this. Maybe it's best to throw it away...<br/>"
					+ "(You need have at least a <b style='color:"+CorruptionLevel.THREE_DIRTY.getColour().toWebHexString()+";'>"+CorruptionLevel.THREE_DIRTY.getName()+"</b> level of corruption to know how to use this!)";
		}
		@Override
		public boolean isAbleToBeUsedInCombatAllies() {
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
			PresetColour.ANDROGYNOUS,
			null,
			null,
			Rarity.QUEST,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.ORIENTATION_CHANGE)),
			null) {

		
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
	
//	public static AbstractItemType VIXENS_VIRILITY = new AbstractItemType(20,
//			"a",
//			false,
//			"breeder pill",
//			"breeder pills",
//			"A small, purple pill, individually packaged in a foil and plastic wrapper."
//				+ " While the text printed on the foil identifies this pill as an 'Orally-Administered Reproduction Enhancer', it's colloquially known as a 'breeder pill', and temporarily boosts both fertility and virility when ingested.",
//			"pill",
//			PresetColour.CLOTHING_PINK,
//			null,
//			null,
//			Rarity.COMMON,
//			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.VIXENS_VIRILITY)),
//			Util.newArrayListOfValues(
//					ItemTag.DOMINION_ALLEYWAY_SPAWN,
//					ItemTag.SUBMISSION_TUNNEL_SPAWN,
//					ItemTag.BAT_CAVERNS_SPAWN,
//					ItemTag.ATTRIBUTE_TF_ITEM,
//					ItemTag.SOLD_BY_RALPH)) {
//		@Override
//		public String getUseName() {
//			return "swallow";
//		}
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return getGenericUseDescription(user, target,
//					"Popping the little purple pill out of its foil wrapper, you quickly put it in your mouth and swallow it down.",
//					"Popping the little purple pill out of its foil wrapper, you bring it up to [npc.namePos] [npc.lips], before forcing it into [npc.her] mouth and making sure that [npc.she] swallows it down.",
//					"[npc.Name] pops a breeder pill out of its little foil wrapper, before quickly placing it in [npc.her] mouth and swallowing it down.",
//					"[npc.Name] pops a breeder pill out of its little foil wrapper, before bringing it up to your [pc.lips], forcing it into your mouth, and making sure that you swallow it down.");
//		}
//	};
//	
//	public static AbstractItemType PROMISCUITY_PILL = new AbstractItemType(20,
//			"a",
//			false,
//			"sterility pill",
//			"sterility pills",
//			"A small, blue pill, individually packaged in a foil and plastic wrapper."
//				+ " While the text printed on the foil identifies this pill as an 'Orally-Administered Reproduction Inhibitor',"
//					+ " it's colloquially known as either a 'sterility pill' or 'slut pill', and temporarily reduces both fertility and virility when ingested.",
//			"pill",
//			PresetColour.CLOTHING_BLUE,
//			null,
//			null,
//			Rarity.COMMON,
//			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.PROMISCUITY_PILL)),
//			Util.newArrayListOfValues(
//					ItemTag.DOMINION_ALLEYWAY_SPAWN,
//					ItemTag.SUBMISSION_TUNNEL_SPAWN,
//					ItemTag.BAT_CAVERNS_SPAWN,
//					ItemTag.ATTRIBUTE_TF_ITEM,
//					ItemTag.SOLD_BY_RALPH)) {
//		@Override
//		public String getUseName() {
//			return "swallow";
//		}
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return getGenericUseDescription(user, target,
//					"Popping the little blue pill out of its foil wrapper, you quickly put it in your mouth and swallow it down.",
//					"Popping the little blue pill out of its foil wrapper, you bring it up to [npc.namePos] [npc.lips], before forcing it into [npc.her] mouth and making sure that [npc.she] swallows it down.",
//					"[npc.Name] pops a sterility pill out of its little foil wrapper, before quickly placing it in [npc.her] mouth and swallowing it down.",
//					"[npc.Name] pops a sterility pill out of its little foil wrapper, before bringing it up to your [pc.lips], forcing it into your mouth, and making sure that you swallow it down.");
//		}
//	};
	
	public static AbstractItemType MOO_MILKER_EMPTY = new AbstractItemType(50,
			"a",
			false,
			"Moo Milker",
			"Moo Milkers",
			"A manual cow-themed breast pump, capable of holding up to "+Units.fluid(1000)+" of liquid in the attached plastic beaker.",
			"breastPump",
			PresetColour.BASE_PURPLE_LIGHT,
			null,
			null,
			Rarity.COMMON,
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
					"Bringing the Moo Milker up to your breast, you place the suction cup over your [pc.nipple(true)], before starting to pump the lever up and down."
							+ " Your [pc.milk+] starts to squirt out into the attached beaker, and you can't help but let out a deeply satisfied sigh at the delightful sensation of milking yourself.",
					"Bringing the Moo Milker up to [npc.namePos] breast, you place the suction cup over [npc.her] [npc.nipple(true)], before starting to pump the lever up and down."
							+ " [npc.Her] [npc.milk+] starts to squirt out into the attached beaker, and [npc.she] can't help but let out a deeply satisfied sigh at the delightful sensation of being milked.",
					"Bringing a Moo Milker up to [npc.her] breast, [npc.name] places the suction cup over [npc.her] [npc.nipple(true)], before starting to pump the lever up and down."
							+ " [npc.Her] [npc.milk+] starts to squirt out into the attached beaker, and [npc.she] can't help but let out a deeply satisfied sigh at the delightful sensation of milking [npc.herself].",
					"Bringing a Moo Milker up to your breast, [npc.name] places the suction cup over your [pc.nipple(true)], before starting to pump the lever up and down."
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
			PresetColour.BASE_PURPLE_LIGHT,
			null,
			null,
			Rarity.COMMON,
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
					"You unscrew the top of the breast pump, and, bringing it up to [npc.namePos] [npc.lips], you force [npc.herHim] to gulp down the contents.",
					"Unscrewing the top of the breast pump, [npc.name] brings it up to [npc.her] [npc.lips], before swallowing down the contents.",
					"Unscrewing the top of the breast pump, [npc.name] brings it up to your [pc.lips], before forcing you to gulp down the contents.");
		}
	};
	
	public static AbstractItemType PREGNANCY_TEST = new AbstractItemType(100,
			"an",
			false,
			"Arcane Pregnancy Tester",
			"Arcane Pregnancy Testers",
			"A small plastic wand, no longer than "+Units.size(15)+", which has a digital readout embedded in the middle."
					+ " The small instruction leaflet that came with it says to 'swipe the tester over the target's stomach to find out who the father is!'",
			"pregnancy_test",
			PresetColour.CLOTHING_WHITE,
			PresetColour.GENERIC_ARCANE,
			null,
			Rarity.COMMON,
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
					+ " On the side, a little sticker declares that this drink is able to rapidly advance pregnancies and egg incubations.",
			"mothers_milk",
			PresetColour.CLOTHING_WHITE,
			null,
			null,
			Rarity.COMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.MOTHERS_MILK)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.SOLD_BY_RALPH,
					ItemTag.DRINK)) {


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
	
	public static AbstractItemType REJUVENATION_POTION = new AbstractItemType(1_000,
			"a bottle of",
			false,
			"rejuvenation potion",
			"rejuvenation potions",
			"A decorative glass bottle with an ornate bronze-and-glass stopper."
					+ " It's filled with a purple liquid, and a little informative sticker on the underside of the container informs you that it's a '<i>rejuvenating potion, guaranteed to restore and revitalise over-used orifices</i>'.",
			"rejuvenation_potion",
			PresetColour.CLOTHING_PURPLE,
			PresetColour.CLOTHING_BRONZE,
			PresetColour.CLOTHING_BLUE_GREY,
			Rarity.COMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.REJUVENATION_POTION)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.SOLD_BY_RALPH,
					ItemTag.DRINK)) {
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
	
	public static AbstractItemType CIGARETTE_PACK = new AbstractItemType(350,
			"a pack of",
			true,
			"Starr Cigarette",
			"Starr Cigarettes",
			"An unopened, purple-and-white cardboard pack which contains twenty 'Starr Cigarettes'."
					+ " According to the information printed on the back of the box, these cigarettes are both 'enhanced with aura-fortifying supplements' and 'guaranteed to make you look cool'.",
			"cigaretteBox",
			PresetColour.CLOTHING_PURPLE_DARK,
			PresetColour.CLOTHING_GOLD,
			null,
			Rarity.COMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.CIGARETTE_PACK)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.SOLD_BY_RALPH)) {

		@Override
		public String getUseName() {
			return "open";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"By opening the top of the pack, you gain access to the twenty cigarettes stored within.",
					"By opening the top of the pack, you gain access to the twenty cigarettes stored within, and then proceed to give them to [npc.name].",
					"By opening the top of the pack, [npc.name] gains access to the twenty cigarettes stored within.",
					"By opening the top of the pack, [npc.name] gains access to the twenty cigarettes stored within, and then proceeds to give them to you.");
		}
	};
	
	public static AbstractItemType CIGARETTE = new AbstractItemType(20,
			"a",
			false,
			"Starr Cigarette",
			"Starr Cigarettes",
			"A rolled up paper cylinder, fitted with a sponge-like filter, and packed with a combination of dried plant matter and aura-boosting supplements."
					+ " It's been enchanted with a very weak fire spell, so that when placed in someone's mouth, it will self-ignite.",
			"cigarette",
			PresetColour.CLOTHING_ORANGE,
			PresetColour.CLOTHING_BRASS,
			PresetColour.CLOTHING_WHITE,
			Rarity.COMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.CIGARETTE)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.SOLD_BY_RALPH)) {

		@Override
		public String getUseName() {
			return "smoke";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You lift the cigarette to your [pc.lips] and put the end in your mouth, at which point the fire enchantment auto-ignites the opposite end."
							+ " Breathing in, you inhale the smoke that's produced from the burning plant matter, before exhaling and creating a small white cloud in front of your face.",
					"You lift the cigarette to [npc.namePos] [npc.lips] and put the end in [npc.her] mouth, at which point the fire enchantment auto-ignites the opposite end."
							+ " Breathing in, [npc.she] inhales the smoke that's produced from the burning plant matter, before exhaling and creating a small white cloud in front of [npc.her] face.",
					"[npc.NamePos] lifts the cigarette to [npc.her] [npc.lips] and puts the end in [npc.her] mouth, at which point the fire enchantment auto-ignites the opposite end."
							+ " Breathing in, [npc.she] inhales the smoke that's produced from the burning plant matter, before exhaling and creating a small white cloud in front of [npc.her] face.",
					"[npc.NamePos] lifts the cigarette to your [pc.lips] and puts the end in your mouth, at which point the fire enchantment auto-ignites the opposite end."
							+ " Breathing in, you inhale the smoke that's produced from the burning plant matter, before exhaling and creating a small white cloud in front of your face.");
		}
	};

	public static AbstractItemType MAKEUP_SET = new AbstractItemType(5000,
			"an",
			false,
			"arcane makeup set",
			"arcane makeup set",
			"A highly sought-after compact makeup set."
				+ " The cosmetics contained within have been enchanted in such a way as to enable the user to change their colour at will."
				+ " Even more impressively, no matter how much they're used, the makeup never expires.",
			"makeupSet",
			PresetColour.CLOTHING_BLACK,
			PresetColour.CLOTHING_BLACK,
			PresetColour.CLOTHING_BLACK,
			Rarity.EPIC,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.MAKEUP_SET)),
			Util.newArrayListOfValues(
					ItemTag.SOLD_BY_RALPH,
					ItemTag.SOLD_BY_KATE)) {
		@Override
		public boolean isAbleToBeUsedInSex() {
			return false;
		}
		@Override
		public boolean isAbleToBeUsedInCombatAllies() {
			return false;
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
		@Override
		public String getUseName() {
			return "use";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "";
		}
		@Override
		public boolean isAbleToBeUsed(GameCharacter target) {
			return super.isAbleToBeUsed(target) && target.isAbleToWearMakeup();
		}
		@Override
		public String getUnableToBeUsedDescription(GameCharacter target) {
			if(!target.isAbleToWearMakeup()) {
				return UtilText.parse(target, "<i>As [npc.namePos] body is made of "+BodyChanging.getTarget().getBodyMaterial().getName()+", [npc.sheIsFull] [style.colourBad(unable to wear any makeup)]!</i>");
			}
			return "This item cannot be used in this way!";
		}
	};
	
	public static AbstractItemType PRESENT = new AbstractItemType(250,
			"a",
			false,
			"Yuletide Gift",
			"Yuletide Gift",
			"A wrapped present, sold by one of the reindeer-morph overseers in Dominion. It contains a random item from their store, and can also be given as a gift to your offspring, slaves, or Lilaya.",
			"present",
			PresetColour.GENERIC_ARCANE,
			null,
			null,
			Rarity.RARE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.PRESENT)),
			null) {

		
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
			PresetColour.BASE_RED,
			PresetColour.BASE_ORANGE,
			PresetColour.BASE_YELLOW,
			Rarity.UNCOMMON,
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
			PresetColour.BASE_TAN,
			PresetColour.BASE_BROWN_DARK,
			PresetColour.BASE_YELLOW,
			Rarity.UNCOMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.GIFT_CHOCOLATES)),
			Util.newArrayListOfValues(
					ItemTag.GIFT,
					ItemTag.FOOD_QUALITY)) {


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
			PresetColour.BASE_ROSE,
			PresetColour.BASE_PURPLE_LIGHT,
			null,
			Rarity.UNCOMMON,
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
			PresetColour.BASE_TAN,
			null,
			null,
			Rarity.UNCOMMON,
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
			PresetColour.GENERIC_ARCANE,
			null,
			null,
			Rarity.LEGENDARY,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.EGGPLANT)),
			Util.newArrayListOfValues(ItemTag.FOOD)) {

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
			PresetColour.GENERIC_ARCANE,
			null,
			null,
			Rarity.LEGENDARY,
			null,
			Util.newArrayListOfValues(ItemTag.DRINK)) {
		@Override
		public boolean isTransformative() {
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
			PresetColour.ANDROGYNOUS,
			null,
			null,
			Rarity.QUEST,
			null,
			null) {


		@Override
		public String getUseName() {
			return "inspect";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"The package is quite small, measuring roughly "+Units.size(20)+" along each edge. It's constructed of brown cardboard, and sealed with packaging tape.",
					"The package is quite small, measuring roughly "+Units.size(20)+" along each edge. It's constructed of brown cardboard, and sealed with packaging tape.",
					"The package is quite small, measuring roughly "+Units.size(20)+" along each edge. It's constructed of brown cardboard, and sealed with packaging tape.",
					"The package is quite small, measuring roughly "+Units.size(20)+" along each edge. It's constructed of brown cardboard, and sealed with packaging tape.");
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
			PresetColour.CLOTHING_SILVER,
			PresetColour.GENERIC_ARCANE,
			null,
			Rarity.QUEST,
			null,
			null) {


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
			PresetColour.CLOTHING_STEEL,
			PresetColour.GENERIC_ARCANE,
			null,
			Rarity.QUEST,
			null,
			null) {


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
			PresetColour.CLOTHING_GOLD,
			PresetColour.GENERIC_ARCANE,
			null,
			Rarity.QUEST,
			null,
			null) {


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
			PresetColour.CLOTHING_ROSE_GOLD,
			PresetColour.CLOTHING_RED_DARK,
			PresetColour.CLOTHING_ROSE_GOLD,
			Rarity.QUEST,
			null,
			null) {


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
			"An arcane-enchanted map, obtained from Dominion's city hall, which is able to track the rough location of any of your nearby offspring.",
			"offspring_map",
			PresetColour.BASE_BROWN,
			null,
			null,
			Rarity.QUEST,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.OFFSPRING_MAP)),
			null) {
		@Override
		public String getUseName() {
			return "consult";
		}
		@Override
		public boolean isAbleToBeUsedInSex() {
			return false;
		}
		@Override
		public boolean isAbleToBeUsedInCombatAllies() {
			return false;
		}
		@Override
		public boolean isAbleToBeUsed(GameCharacter target) {
			return target.isPlayer()
					&& Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()).size()==0
					&& ((Util.newArrayListOfValues(
							Encounter.DOMINION_ALLEY,
							Encounter.DOMINION_CANAL,
							Encounter.HARPY_NEST_WALKWAYS,
							Encounter.SUBMISSION_TUNNELS,
							Encounter.BAT_CAVERN,
							Encounter.getEncounterFromId("innoxia_elis_alleyway")
						).contains(target.getLocationPlace().getPlaceType().getEncounterType()))
						|| Main.game.getPlayer().getLocationPlaceType()==PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_alley"));
		}
		@Override
		public String getUnableToBeUsedDescription(GameCharacter target) {
			return "In order to be able to use the map, you need to be in a vacant tile of one of the following types: Dominion alleyways; Dominion canals; Harpy Nest walkways; Submission tunnels; Bat Caverns; Elis alleyways.";
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
	
	public static AbstractItemType CANDI_PERFUMES = new AbstractItemType(500,
			"",
			true,
			"Candi's Perfume",
			"Candi's Perfumes",
			"A couple of bottles of perfume which you collected from Kate at Succubi's Secrets. You need to deliver these to Candi back at the Enforcer Headquarters.",
			"candiPerfumes",
			PresetColour.BASE_ROSE,
			PresetColour.BASE_PURPLE_LIGHT,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "inspect";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "There's nothing really out of the ordinary about these bottles of perfume. It would be best to deliver them to Candi as soon as possible.";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

	public static AbstractItemType CANDI_CONTRABAND = new AbstractItemType(1000,
			"",
			false,
			"Box of Contraband Lollipops",
			"Boxes of Contraband Lollipops",
			"A box full of contraband lollipops, seized by the Enforcers up in the Harpy Nests, and very much desired by Candi.",
			"contrabandBox",
			PresetColour.BASE_PINK_DEEP,
			null,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "inspect";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "This box has been marked with several large red crosses, making it clear that the contents are in some way dangerous."
					+ " The Enforcer who handed this box over to you said that the lollipops contained within are some form of permanent aphrodisiac, and that the box should be immediately locked away in safe storage.";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

	public static AbstractItemType CANDI_HUNDRED_KISSES = new AbstractItemType(50000,
			"",
			false,
			"'A Hundred Kisses'",
			"'A Hundred Kisses'",
			"A limited-edition box containing a hundred differently-coloured lipsticks, produced by one of the most exclusive and upmarket cosmetics companies in all of Dominion.",
			"candiHundredKisses",
			PresetColour.BASE_PINK_DEEP,
			null,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "inspect";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "It's obvious from even a casual glance at this stylish box that it contains nothing other than the best lipsticks money can buy.";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

	public static AbstractItemType RESONANCE_STONE = new AbstractItemType(0,
			"",
			false,
			"Claire's Resonance Stone",
			"Claire's Resonance Stones",
			"A small, polished sphere, which has a single groove running around its entire circumference."
					+ " Normally prohibited to citizens, Claire has given you this one so that you can send a signal to the SWORD Enforcers stationed near the Rat Warrens.",
			"resonanceStone",
			PresetColour.CLOTHING_PURPLE_VERY_DARK,
			null,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "inspect";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "The resonance stone is made out of some kind of highly-polished stone, and by twisting its two halves in opposite directions, can be activated to send a signal to any other stones which are linked to it."
					+ " Claire has told you that this one will trigger SWORD Enforcers to begin their raid on the Rat Warrens, and should only be used once you've confirmed that Vengar is present.";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

	public static AbstractItemType PAINT_CAN_PREMIUM = new AbstractItemType(1_500,
			"a can of",
			false,
			"'Purple-star' golden paint",
			"'Purple-star' golden paint",
			"A can of golden paint, branded with the premium-grade 'Purple-star' logo, which you purchased from 'Argus's DIY Depot'."
					+ " Hopefully Helena will appreciate how much this cost...",
			"paint_can",
			PresetColour.CLOTHING_GOLD,
			PresetColour.CLOTHING_PURPLE_DARK,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "inspect";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "Unless they're a true paint connoisseur, nobody would ever believe how much you paid for this can of premium-grade 'Purple-star' paint...";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
		@Override
		public List<AbstractCoreType> getAdditionalDiscoveryTypes() {
			return Util.newArrayListOfValues(PAINT_CAN);
		}
	};

	public static AbstractItemType PAINT_CAN = new AbstractItemType(250,
			"a can of",
			false,
			"'Bronze-star' golden paint",
			"'Bronze-star' golden paint",
			"A can of golden paint, branded with the standard-grade 'Bronze-star' logo, which you purchased from 'Argus's DIY Depot'."
					+ " Hopefully Helena won't be disappointed with this...",
			"paint_can",
			PresetColour.CLOTHING_GOLD,
			PresetColour.CLOTHING_BRONZE,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "inspect";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "Although it's not the premium-grade 'Purple-star' can of paint which Helena specifically requested, you're sure that this will be good enough to get the job done...";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
		@Override
		public List<AbstractCoreType> getAdditionalDiscoveryTypes() {
			return Util.newArrayListOfValues(PAINT_CAN_PREMIUM);
		}
	};

	public static AbstractItemType ROLLED_UP_POSTERS = new AbstractItemType(0,
			"half a dozen",
			true,
			"rolled-up enchanted poster",
			"rolled-up enchanted posters",
			"Half a dozen rolled-up posters, given to you by Helena with the order to paste them onto the walls near the entrance to Slaver Alley."
					+ " Each one displays an enchanted, moving image of the beautiful harpy striking a seductive pose while wearing a skimpy bikini.",
			"rolled_up_posters",
			PresetColour.CLOTHING_DESATURATED_BROWN,
			null,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "inspect";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "Although they're meant to be advertising 'Helena's Boutique', there's no mention of the harpy's store on any of these posters."
					+ " Instead, each one displays an enchanted, moving image of Helena striking a seductive pose while wearing a skimpy bikini."
					+ " The only text to be seen is at the bottom of each poster, and simply reads 'Helena' in fancy, cursive writing.";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

//	public static AbstractItemType BUSINESS_CARDS = new AbstractItemType(0,
//			"a",
//			false,
//			"pack of business cards",
//			"packs of business cards",
//			"A pack of Helena's business cards, given to you by Scarlett along with the instructions to hand them out at the auction block in Slaver Alley."
//					+ " The pack itself has a fancy heart-shaped cut-out in the middle of its protective sleeve.",
//			"business_card_box_1",
//			PresetColour.CLOTHING_GOLD,
//			null,
//			null,
//			Rarity.QUEST,
//			null,
//			null,
//			null) {
//		@Override
//		public String getUseName() {
//			return "inspect";
//		}
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "Carefully taking them out of their protective packaging, you see that Helena's business cards are very well designed, and clearly display both her name and the address of her store in Slaver Alley.";
//		}
//		@Override
//		public boolean isConsumedOnUse() {
//			return false;
//		}
//	};

	public static AbstractItemType NATALYA_BUSINESS_CARD = new AbstractItemType(0,
			"",
			false,
			"Natalya's Business Card",
			"Natalya's Business Cards",
			"A business card given to you by Natalya, the dominant succutaur who holds the position of 'Stable Mistress' at the delivery company 'Dominion Express'."
					+ " The address printed on the card directs you to Dominion's Warehouse District.",
			"natalya_business_card",
			PresetColour.CLOTHING_WHITE,
			null,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "inspect";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "Natalya's business card clearly displays her name and title of 'Stable Mistress'."
					+ " The address of the company at which she works, 'Dominion Express', is also clearly displayed as being in Dominion's Warehouse District.";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

	public static AbstractItemType NATALYA_BUSINESS_CARD_STAMPED = new AbstractItemType(0,
			"",
			false,
			"Natalya's Business Card (Stamped)",
			"Natalya's Business Cards (Stamped)",
			"A business card given to you by Natalya, the dominant succutaur who holds the position of 'Stable Mistress' at the delivery company 'Dominion Express'."
					+ " The address printed on the card directs you to Dominion's Warehouse District.",
			"natalya_business_card_stamped",
			PresetColour.CLOTHING_WHITE,
			null,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "inspect";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "Natalya's business card clearly displays her name and title of 'Stable Mistress'."
					+ " The address of the company at which she works, 'Dominion Express', is also clearly displayed as being in Dominion's Warehouse District."
					+ " This particular card has been stamped by Natalya herself, and grants you instant access to Dominion Express's warehouse.";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

	public static AbstractItemType SLAVER_LICENSE = new AbstractItemType(5000,
			"",
			false,
			"Slaver license",
			"Slaver licenses",
			"An official document declaring that you're legally entitled to own, purchase, sell, and even capture slaves."
					+ " Although slaver licenses are extremely difficult to obtain, they only really have any value to their rightful owner...",
			"slaver_license",
			PresetColour.CLOTHING_WHITE,
			null,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "inspect";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "This slaver license consists of a single piece of thick, high-quality paper."
					+ " It has your name clearly printed near to the top, and has been signed by Finch.";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	
	// Standard non-racial transformatives:
	
//	MASOCHISTS_HEAVEN("a bottle of", "it", "Masochist's Heaven",
//			"A clear plastic bottle of Masochist's Heaven. A girl, lying back in the missionary position, is prominently featured on the label, screaming in delight as a huge cock painfully stretches out her tight, dry pussy.",
//			"potion", PresetColour.CLOTHING_WHITE, true, 25, Rarity.RARE, "Decreases orifice capacity, elasticity, and wetness.") {
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
//			"potion", PresetColour.CLOTHING_WHITE, true, 25, Rarity.RARE, "Decreases orifice capacity. Increases elasticity and wetness.") {
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
//			"potion", PresetColour.CLOTHING_PINK_LIGHT, true, 25, Rarity.RARE, "Increases orifice wetness and capacity.") {
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
//			"potion", PresetColour.CLOTHING_WHITE, true, 25, Rarity.RARE, "Increases breast size and lactation.") {
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
//			"potion", PresetColour.CLOTHING_WHITE, true, 100, Rarity.EPIC, "Increases breast size, count, and lactation.") {
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
//			"potion", PresetColour.CLOTHING_BLUE_LIGHT, true, 25, Rarity.RARE, "Increases penis and testicle size. Increases cum production.") {
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
//			"potion", PresetColour.CLOTHING_PINK_LIGHT, true, 25, Rarity.RARE, "Increases the body's feminine characteristics.") {
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
//			"potion", PresetColour.CLOTHING_PINK, true, 25, Rarity.RARE, "Increases all feminine aspects.") {
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
//			"potion", PresetColour.CLOTHING_BLUE, true, 25, Rarity.RARE, "Increases all masculine aspects.") {
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
	private static List<AbstractItemType> elisAlleywayItems = new ArrayList<>();
	
	private static List<AbstractItemType> essences = new ArrayList<>();
	private static List<AbstractItemType> allItems = new ArrayList<>();
	private static List<AbstractItemType> moddedItems = new ArrayList<>();
	private static Map<AbstractSubspecies, String> subspeciesBookId = new HashMap<>();
	
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
	

	public static AbstractItemType getItemTypeFromId(String id) {
		
		if(id.equalsIgnoreCase("PROMISCUITY_PILL")) {
			id = "innoxia_pills_sterility";
			
		} else if(id.equalsIgnoreCase("VIXENS_VIRILITY")) {
			id = "innoxia_pills_fertility";
		}
		
		// Attribute-related liquids were moved out into external res folder in v0.4:
		if(id.equalsIgnoreCase("STR_INGREDIENT_EQUINE_CIDER")) {
			id = "innoxia_race_horse_equine_cider";
		} else if(id.equalsIgnoreCase("STR_INGREDIENT_BUBBLE_MILK")) {
			id = "innoxia_race_cow_bubble_milk";
		} else if(id.equalsIgnoreCase("STR_INGREDIENT_WOLF_WHISKEY")) {
			id = "innoxia_race_wolf_wolf_whiskey";
		} else if(id.equalsIgnoreCase("STR_INGREDIENT_SWAMP_WATER")) {
			id = "innoxia_race_alligator_swamp_water";
		} else if(id.equalsIgnoreCase("STR_INGREDIENT_BLACK_RATS_RUM")) {
			id = "innoxia_race_rat_black_rats_rum";
		} else if(id.equalsIgnoreCase("INT_INGREDIENT_FELINE_FANCY")) {
			id = "innoxia_race_cat_felines_fancy";
		} else if(id.equalsIgnoreCase("INT_INGREDIENT_GRAPE_JUICE")) {
			id = "innoxia_race_fox_vulpines_vineyard";
		} else if(id.equalsIgnoreCase("INT_INGREDIENT_VANILLA_WATER")) {
			id = "innoxia_race_human_vanilla_water";
		} else if(id.equalsIgnoreCase("INT_INGREDIENT_FRUIT_BAT_SQUASH")) {
			id = "innoxia_race_bat_fruit_bats_juice_box";
		} else if(id.equalsIgnoreCase("FIT_INGREDIENT_CANINE_CRUSH")) {
			id = "innoxia_race_dog_canine_crush";
		} else if(id.equalsIgnoreCase("FIT_INGREDIENT_SQUIRREL_JAVA")) {
			id = "innoxia_race_squirrel_squirrel_java";
		} else if(id.equalsIgnoreCase("FIT_INGREDIENT_EGG_NOG")) {
			id = "innoxia_race_reindeer_rudolphs_egg_nog";
		} else if(id.equalsIgnoreCase("SEX_INGREDIENT_HARPY_PERFUME")) {
			id = "innoxia_race_harpy_harpy_perfume";
		} else if(id.equalsIgnoreCase("SEX_INGREDIENT_SLIME_QUENCHER")) {
			id = "innoxia_race_slime_slime_quencher";
		} else if(id.equalsIgnoreCase("SEX_INGREDIENT_BUNNY_JUICE")) {
			id = "innoxia_race_rabbit_bunny_juice";
		} else if(id.equalsIgnoreCase("SEX_INGREDIENT_MINCE_PIE")) {
			id = "innoxia_race_none_mince_pie";
		} else if(id.equalsIgnoreCase("COR_INGREDIENT_ANGELS_TEARS")) {
			id = "innoxia_race_angel_angels_tears";
		} else if(id.equalsIgnoreCase("COR_INGREDIENT_LILITHS_GIFT")) {
			id = "innoxia_race_demon_liliths_gift";
		} else if(id.equalsIgnoreCase("COR_INGREDIENT_IMPISH_BREW")) {
			id = "innoxia_race_imp_impish_brew";
		} else if(id.equalsIgnoreCase("DEBUG_YOUKO_POTION")) {
			id = "innoxia_cheat_inno_chans_gift";
		} else if(id.equalsIgnoreCase("FEMININE_BURGER")) {
			id = "innoxia_cheat_unlikely_whammer";
		}
		
		// Racial-transformative consumables were also moved into external res folder in v0.4:
		
		if(id.equalsIgnoreCase("DEBUG_DEMON_POTION")) {
			id = "innoxia_race_demon_innoxias_gift";
		} else if(id.equalsIgnoreCase("RACE_ANGELS_TEARS") || id.equalsIgnoreCase("RACE_INGREDIENT_HUMAN")) {
			id = "innoxia_race_human_bread_roll";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_CAT_MORPH")) {
			id = "innoxia_race_cat_kittys_reward";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_DOG_MORPH")) {
			id = "innoxia_race_dog_canine_crunch";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_COW_MORPH")) {
			id = "innoxia_race_cow_bubble_cream";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_SQUIRREL_MORPH")) {
			id = "innoxia_race_squirrel_round_nuts";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_BAT_MORPH")) {
			id = "innoxia_race_bat_fruit_bats_salad";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_RAT_MORPH")) {
			id = "innoxia_race_rat_brown_rats_burger";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_RABBIT_MORPH")) {
			id = "innoxia_race_rabbit_bunny_carrot_cake";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_FOX_MORPH")) {
			id = "innoxia_race_fox_chicken_pot_pie";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_HORSE_MORPH")) {
			id = "innoxia_race_horse_sugar_carrot_cube";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_REINDEER_MORPH")) {
			id = "innoxia_race_reindeer_sugar_cookie";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_ALLIGATOR_MORPH")) {
			id = "innoxia_race_alligator_gators_gumbo";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_WOLF_MORPH")) {
			id = "innoxia_race_wolf_meat_and_marrow";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_HARPY")) {
			id = "innoxia_race_harpy_bubblegum_lollipop";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_SLIME")) {
			id = "innoxia_race_slime_biojuice_canister";
		}
		
		id = Util.getClosestStringMatch(id, idToItemMap.keySet());
		return idToItemMap.get(id);
	}
	
	public static String getIdFromItemType(AbstractItemType itemType) {
		return itemToIdMap.get(itemType);
	}
	
	public static AbstractItemType getSpellBookType(Spell s) {
		return idToItemMap.get("SPELL_BOOK_"+s);
	}
	
	public static AbstractItemType getSpellScrollType(SpellSchool school) {
		return idToItemMap.get("SPELL_SCROLL_"+school);
	}
	
	public static AbstractItemType getLoreBook(AbstractSubspecies subspecies) {
		return idToItemMap.get(subspeciesBookId.get(subspecies));
	}
	
	static{
		
		// Modded item types:

		moddedItems = new ArrayList<>();
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/items/items");
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					String id = innerEntry.getKey();
					AbstractItemType ct = new AbstractItemType(innerEntry.getValue(), entry.getKey(), true) {};
					moddedItems.add(ct);
					itemToIdMap.put(ct, id);
					idToItemMap.put(id, ct);
				} catch(Exception ex) {
					System.err.println("Loading modded item failed at 'ItemType'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		allItems.addAll(moddedItems);
		
		// External res item types:

		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/items");
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					String id = innerEntry.getKey();
					AbstractItemType ct = new AbstractItemType(innerEntry.getValue(), entry.getKey(), false) {};
					allItems.add(ct);
					itemToIdMap.put(ct, id);
					idToItemMap.put(id, ct);
//					System.out.println("IT: "+innerEntry.getKey());
				} catch(Exception ex) {
					System.err.println("Loading item failed at 'ItemType'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		for(AbstractItemType it : allItems) {
			it.getSVGString(); // Initialise all SVGStrings so that initialisation methods do not conflict with one another in other places in the code.
		}
		
		Field[] fields = ItemType.class.getFields();
		
		for(Field f : fields){
			
			if (AbstractItemType.class.isAssignableFrom(f.getType())) {
				
				AbstractItemType item;
				try {
					item = ((AbstractItemType) f.get(null));
					
					itemToIdMap.put(item, f.getName());
					idToItemMap.put(f.getName(), item);
					
					allItems.add(item);
					
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
			
			if(s == Spell.ELEMENTAL_EARTH) {
				effectsString.add("Adds "+Subspecies.ELEMENTAL_EARTH.getName(null)+" encyclopedia entry.");
				
			} else if(s == Spell.ELEMENTAL_WATER) {
				effectsString.add("Adds "+Subspecies.ELEMENTAL_WATER.getName(null)+" encyclopedia entry.");
				
			} else if(s == Spell.ELEMENTAL_AIR) {
				effectsString.add("Adds "+Subspecies.ELEMENTAL_AIR.getName(null)+" encyclopedia entry.");
				
			} else if(s == Spell.ELEMENTAL_FIRE) {
				effectsString.add("Adds "+Subspecies.ELEMENTAL_FIRE.getName(null)+" encyclopedia entry.");
				
			} else if(s == Spell.ELEMENTAL_ARCANE) {
				effectsString.add("Adds "+Subspecies.ELEMENTAL_ARCANE.getName(null)+" encyclopedia entry.");
			}
//			effectsString.add("[style.boldExcellent(+5 "+Attribute.DAMAGE_ELEMENTAL.getName()+")]");
			
			
			AbstractItemEffectType effectType = new AbstractItemEffectType(effectsString, s.getSpellSchool().getColour()) {
				
				@Override
				public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
					boolean hasSpell = target.hasSpell(s);
					target.addSpell(s);
					
					String raceKnowledgeGained = "";
					if(target.isPlayer()) {
						if(s == Spell.ELEMENTAL_EARTH) {
							raceKnowledgeGained = getBookEffect(target, Subspecies.ELEMENTAL_EARTH, null, true);
							
						} else if(s == Spell.ELEMENTAL_WATER) {
							raceKnowledgeGained = getBookEffect(target, Subspecies.ELEMENTAL_WATER, null, true);
							
						} else if(s == Spell.ELEMENTAL_AIR) {
							raceKnowledgeGained = getBookEffect(target, Subspecies.ELEMENTAL_AIR, null, true);
							
						} else if(s == Spell.ELEMENTAL_FIRE) {
							raceKnowledgeGained = getBookEffect(target, Subspecies.ELEMENTAL_FIRE, null, true);
							
						} else if(s == Spell.ELEMENTAL_ARCANE) {
							raceKnowledgeGained = getBookEffect(target, Subspecies.ELEMENTAL_ARCANE, null, true);
						}
					}
					
					if(hasSpell) {
						if(target.isPlayer()) {
							return "<p style='text-align:center'>"
										+"<i><b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+":</b> "+s.getDescription(target)+"</i>"
									+"</p>"
									+ "<p>"
										+"Reading through the spell book again, you quickly discover that you've already learned all there is to know about the spell '"+s.getName()+"'."
										+ " Apart from some well-detailed diagrams of a demon casting this spell, there's nothing within the tome's pages to hold your attention,"
											+ " and you find yourself closing it after just a couple of minutes, having not learned anything new..."
									+ "</p>"
									+raceKnowledgeGained;
						} else {
							return "<p style='text-align:center'>"
										+"<i><b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+":</b> "+s.getDescription(target)+"</i>"
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
										+"<i><b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+":</b> "+s.getDescription(target)+"</i>"
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
									+"<i><b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+":</b> "+s.getDescription(target)+"</i>"
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
				case LIGHTNING_SPHERE_DISCHARGE:
				case LIGHTNING_SPHERE_OVERCHARGE:
				case ARCANE_CHAIN_LIGHTNING:
				case ARCANE_LIGHTNING_SUPERBOLT:
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
					Util.newArrayListOfValues(new ItemEffect(effectType)),
					Util.newArrayListOfValues(ItemTag.SPELL_BOOK)) {
		
				@Override
				public String getSVGString() {
					return super.getSVGString()
							+"<div style='width:60%;height:60%;position:absolute;left:0;top:0;'>"
								+ s.getSVGString()
							+ "</div>";
				}
				@Override
				public boolean isAbleToBeUsed(GameCharacter target) {
					return (target.isPlayer() || target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.ONE_AVERAGE.getMinimumValue())
							&& !(target.isElemental());
				}
				@Override
				public String getUnableToBeUsedDescription(GameCharacter target) {
					if(target.isPlayer()) {
						return "You already know how to cast this spell!";
						
					} else if(target.isElemental()) {
						return UtilText.parse(target, "[npc.Name], like all elementals, cannot learn spells from books."
								+ " Instead, [npc.she] will need to focus on improving [npc.her] understanding of the arcane in order to learn new spells."
								+ " (Elementals gain new spells via the perk tree.)");
						
					} else {
						return UtilText.parse(target, "[npc.Name] does not have enough arcane skill to know how to learn this spell! (Requires arcane to be at least "+IntelligenceLevel.ONE_AVERAGE.getMinimumValue()+".)");
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
				public boolean isAbleToBeUsedInCombatAllies() {
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
						public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
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
					Util.newArrayListOfValues(new ItemEffect(effectType)),
					Util.newArrayListOfValues(ItemTag.SPELL_SCROLL)) {
				@Override
				public boolean isAbleToBeUsed(GameCharacter target) {
					return (target.isPlayer() || target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.ONE_AVERAGE.getMinimumValue())
							&& !target.isElemental();
				}
				@Override
				public String getUnableToBeUsedDescription(GameCharacter target) {
					if(target.isElemental()) {
						return "Elementals cannot make use of scrolls, and instead must improve their spells via their perks!";
					}
					return UtilText.parse(target, "[npc.Name] does not have enough arcane skill to know how to absorb the power of this scroll! (Requires arcane to be at least "+IntelligenceLevel.ONE_AVERAGE.getMinimumValue()+".)");
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
				public boolean isAbleToBeUsedInCombatAllies() {
					return false;
				}
			};
			
			itemToIdMap.put(scroll, "SPELL_SCROLL_"+school);
			idToItemMap.put("SPELL_SCROLL_"+school, scroll);
			
			allItems.add(scroll);
		}
		
		// Race books:
		
		Map<String, List<AbstractSubspecies>> subspeciesLoreMap = new HashMap<>();
		for(AbstractSubspecies sub : Subspecies.getAllSubspecies()) {
			subspeciesLoreMap.putIfAbsent(sub.getAdvancedDescriptionId(), new ArrayList<>());
			subspeciesLoreMap.get(sub.getAdvancedDescriptionId()).add(sub);
		}
		
		// Add effects from here, as Subspecies and ItemEffectType are dependent on one another to be initialised.
		for(AbstractSubspecies sub : Subspecies.getAllSubspecies()) {
			subspeciesLoreMap.putIfAbsent(sub.getAdvancedDescriptionId(), new ArrayList<>());
			subspeciesLoreMap.get(sub.getAdvancedDescriptionId()).add(sub);
		}
		
		for(Entry<String, List<AbstractSubspecies>> entry : subspeciesLoreMap.entrySet()) {
			AbstractSubspecies mainSubspecies = entry.getValue().contains(AbstractSubspecies.getMainSubspeciesOfRace(entry.getValue().get(0).getRace()))
											?AbstractSubspecies.getMainSubspeciesOfRace(entry.getValue().get(0).getRace())
											:entry.getValue().get(0);
			
			AbstractItemType loreBook = new AbstractItemType(250,
							null,
							false,
							mainSubspecies.getBookName(),
							mainSubspecies.getBookNamePlural(),
							"A book which contains advanced lore concerning "+mainSubspecies.getNamePlural(null)+".",
							"race_book",
							mainSubspecies.getColour(null),
							PresetColour.CLOTHING_GOLD,
							mainSubspecies.getColour(null),
							Rarity.RARE,
							null,
							Util.newArrayListOfValues(ItemTag.BOOK)) {
				@Override
				public String getDescription() {
					return super.getDescription()
							+(mainSubspecies.getBookAuthor().isEmpty()?"":" The book's author is identified as '"+mainSubspecies.getBookAuthor()+"'.");
				}
				@Override
				public List<ItemEffect> getEffects() {
					AbstractSubspecies mainSubspecies = entry.getValue().contains(AbstractSubspecies.getMainSubspeciesOfRace(entry.getValue().get(0).getRace()))
							?AbstractSubspecies.getMainSubspeciesOfRace(entry.getValue().get(0).getRace())
							:entry.getValue().get(0);
					String id = "BOOK_READ_"+Subspecies.getIdFromSubspecies(mainSubspecies);
					
					if(!ItemEffectType.idToItemEffectTypeMap.containsKey(id)) {
						AbstractItemEffectType bookType = generateBookEffect(mainSubspecies, entry.getValue());
						ItemEffectType.allEffectTypes.add(bookType);
						ItemEffectType.itemEffectTypeToIdMap.put(bookType, id);
						ItemEffectType.idToItemEffectTypeMap.put(id, bookType);
					}
					
					return Util.newArrayListOfValues(new ItemEffect(ItemEffectType.getBookEffectFromSubspecies(mainSubspecies)));
				}
				@Override
				public String getSVGString() {
					int offset = 6;
					float left = (float) (30 + offset*Math.cos(Math.toRadians(60)));
					left = Math.round(left*100);
					left /=100;
					return super.getSVGString()
							+"<div style='width:40%;height:40%;position:absolute;left:"+left+"%;top:"+(30-offset)+"%; opacity:0.75; -webkit-transform: rotate(30deg);'>"
								+ mainSubspecies.getBookSVGString()
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
			
			String id = "BOOK_"+Subspecies.getIdFromSubspecies(mainSubspecies);
					
			itemToIdMap.put(loreBook, id);
			idToItemMap.put(id, loreBook);
			
			for(AbstractSubspecies subspecies : entry.getValue()) {
				subspeciesBookId.put(subspecies, id);
			}
			
			allItems.add(loreBook);

			// Essences
			if(mainSubspecies!=Subspecies.CENTAUR) { // a CENTAUR essence is identical to a HORSE_MORPH essence

				int override = mainSubspecies.getSubspeciesOverridePriority();
				String raceName = (override>0?mainSubspecies.getFeralName(null):mainSubspecies.getRace().getName(true));
				String raceNamePlural = (override>0?mainSubspecies.getFeralNamePlural(null):mainSubspecies.getRace().getNamePlural(true));

				AbstractStatusEffect statusEffect = new AbstractStatusEffect(80,
						(mainSubspecies.getRace()==Race.ANGEL
							?"angelic"
							:(mainSubspecies.getRace()==Race.DEMON && override <= 2
								?"impish"
								:(mainSubspecies.getRace()==Race.DEMON && override >= 5 && override <= 20
									?"demonic"
									:(mainSubspecies==Subspecies.LILIN
										?"lilin"
										:(mainSubspecies==Subspecies.ELDER_LILIN
											?"elder lilin"
											:raceName.toLowerCase())))))
							+ " intuition",
						null,
						mainSubspecies.getColour(null),
						true,
						Util.newHashMapOfValues(new Util.Value<>(Attribute.MAJOR_PHYSIQUE, 2f),
								new Util.Value<>(
									mainSubspecies.getRace()==Race.DEMON && override <= 2
										?Attribute.DAMAGE_IMP
										:(mainSubspecies==Subspecies.LILIN
											?Attribute.DAMAGE_LILIN
											:mainSubspecies==Subspecies.ELDER_LILIN
												?Attribute.DAMAGE_ELDER_LILIN
												:Attribute.getRacialDamageAttribute(mainSubspecies.getRace())),
									25f)),
						null) {
					@Override
					public String getDescription(GameCharacter target) {
						if(target == null) {
							return "";
						}
						return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.nameIsFull] able to accurately predict how "
								+ raceNamePlural.toLowerCase() +" will behave.");
					}
					@Override
					public String getSVGString(GameCharacter owner) {
						return getEssenceEffectSvg(mainSubspecies);
					}
				};

				String effect_id = "COMBAT_BONUS_"+Subspecies.getIdFromSubspecies(mainSubspecies).toUpperCase();

				StatusEffect.statusEffectToIdMap.put(statusEffect, effect_id);
				StatusEffect.idToStatusEffectMap.put(effect_id, statusEffect);
				StatusEffect.allStatusEffects.add(statusEffect);

				AbstractItemEffectType effectType = new AbstractItemEffectType(Util.newArrayListOfValues(
						"[style.boldGood(+1)] [style.boldArcane(Arcane essence)]"),
						mainSubspecies.getColour(null)) {
					@Override
					public Map<AbstractStatusEffect, Integer> getAppliedStatusEffects() {
						return Util.newHashMapOfValues(new Value<>(statusEffect, 60*4*60));
					}
//					@Override
//					public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
//						List<String> list = super.getEffectsDescription(primaryModifier, secondaryModifier, potency, limit, user, target);
//						list.add("Applies <i style='color:"+statusEffect.getColour().toWebHexString()+";'>'"+Util.capitaliseSentence(statusEffect.getName(target))+"'</i>:");
//						for(Entry<AbstractAttribute, Float> entry : statusEffect.getAttributeModifiers(target).entrySet()) {
//							list.add("<i>"+entry.getKey().getFormattedValue(entry.getValue())+"</i>");
//						}
//						return list;
//					}
					@Override
					public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
						target.incrementEssenceCount(1, false);
//						target.addStatusEffect(statusEffect, 60*4*60);
						return UtilText.parse(target,
								"<p style='text-align:center;'>"
									+ "[npc.NameHasFull] absorbed [style.boldGood(+1)] [style.boldArcane(arcane essence)], and [npc.is] also temporarily far more effective at fighting "
									+ "<b style='color:"+mainSubspecies.getColour(null).toWebHexString()+";'>" + raceNamePlural +"</b>!"
								+ "</p>");
					}
				};

				ItemEffectType.addAbstractItemEffectToIds("BOTTLED_ESSENCE_"+Subspecies.getIdFromSubspecies(mainSubspecies).toUpperCase(), effectType);

				AbstractItemType essence = new AbstractItemType(
						Math.min(Math.max((mainSubspecies.getBaseSlaveValue(null) / 250), 40), 10000), // i.e. 48 flames for cat-morphs, minimum 40 flames, 10000 maximum
						null,
						false,
						"Bottled " + Util.capitaliseSentence(raceName) + " Essence",
						"Bottled " + Util.capitaliseSentence(raceName) + " Essences",
						"A small glass bottle, with a little cork stopper wedged firmly in the top."
								+ " Inside, the swirling "+mainSubspecies.getColour(null).getName().toLowerCase() + " glow of an arcane essence,"
								+ " imbued with the energy of "+UtilText.generateSingularDeterminer(raceName) + " " + raceName
								+ ", flickers and swirls about in a mesmerising, cyclical pattern.",
						null,
						mainSubspecies.getColour(null),
						null,
						null,
						Rarity.EPIC,
						Util.newArrayListOfValues(new ItemEffect(effectType)),
						(((mainSubspecies.getRace()==Race.DEMON && mainSubspecies.getSubspeciesOverridePriority()>5) || mainSubspecies.getRace()==Race.ANGEL) // Demon+ (and Angels) are contraband
								?Util.newArrayListOfValues(ItemTag.ESSENCE, ItemTag.CONTRABAND_HEAVY)
								:Util.newArrayListOfValues(ItemTag.ESSENCE))) {
						
					@Override
					public String getUseName() {
						return "absorb";
					}
					@Override
					public String getUseDescription(GameCharacter user, GameCharacter target) {
						return getEssenceAbsorptionText(mainSubspecies.getColour(null), user, target);
					}
					@Override
					public String getSVGString() {
						return getEssenceSvg(mainSubspecies);
					}

				};

				String essence_id = "BOTTLED_ESSENCE_"+Subspecies.getIdFromSubspecies(mainSubspecies).toUpperCase();

				itemToIdMap.put(essence, essence_id);
				idToItemMap.put(essence_id, essence);

				allItems.add(essence);
				essences.add(essence);
			}

		}
		
		// Add items to spawn lists:
		for(AbstractItemType item : allItems) {
			if(item.getItemTags().contains(ItemTag.DOMINION_ALLEYWAY_SPAWN) || item.getItemTags().contains(ItemTag.ALL_AREAS_SPAWN)) {
				dominionAlleywayItems.add(item);
			}
			if(item.getItemTags().contains(ItemTag.SUBMISSION_TUNNEL_SPAWN) || item.getItemTags().contains(ItemTag.ALL_AREAS_SPAWN)) {
				submissionTunnelItems.add(item);
			}
			if(item.getItemTags().contains(ItemTag.BAT_CAVERNS_SPAWN) || item.getItemTags().contains(ItemTag.ALL_AREAS_SPAWN)) {
				batCavernItems.add(item);
			}
			if(item.getItemTags().contains(ItemTag.ELIS_ALLEYWAY_SPAWN) || item.getItemTags().contains(ItemTag.ALL_AREAS_SPAWN)) {
				elisAlleywayItems.add(item);
			}
		}
	}
	
	private static AbstractItemEffectType generateBookEffect(AbstractSubspecies mainSubspecies, List<AbstractSubspecies> additionalUnlockSubspecies) {
		return new AbstractItemEffectType(Util.newArrayListOfValues(
				"Adds "+mainSubspecies.getName(null)+" encyclopedia entry and reveals racial status effect attributes",
				"[style.boldExcellent(+10)] <b style='color:"+mainSubspecies.getColour(null).toWebHexString()+";'>"+mainSubspecies.getDamageMultiplier().getName()+"</b>"),
				mainSubspecies.getColour(null)) {
			@Override
			public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
				return getBookEffect(target, mainSubspecies, additionalUnlockSubspecies, true);
			}
		};
	}
	
	private static Map<AbstractSubspecies, String> essenceMap = new HashMap<>();
	
	private static String getEssenceSvg(AbstractSubspecies subspecies) {
		if(essenceMap.containsKey(subspecies)) {
			return essenceMap.get(subspecies);
		}
		String background = "";
		String bottle = "";
		Colour colour = subspecies.getColour(null);
		try {
			InputStream is = ItemType.class.getResourceAsStream("/com/lilithsthrone/res/items/essenceBackground.svg");
			String s = Util.inputStreamToString(is);
			
			background = SvgUtil.colourReplacement(subspecies.getName(null), colour, s);
			is.close();
			
			is = ItemType.class.getResourceAsStream("/com/lilithsthrone/res/items/essenceBottle.svg");
			s = Util.inputStreamToString(is);
			bottle = SvgUtil.colourReplacement(subspecies.getName(null), colour, s);
			is.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String subspeciesIcon = subspecies.getSVGStringNoBackground();
		subspeciesIcon = subspeciesIcon.replaceAll("fill=\"#(.*?)\"", "fill=\""+colour.getShades()[1]+"\"");
//		subspeciesIcon = subspeciesIcon.replaceAll(colour.getShades()[1], colour.getShades()[0]);
//		subspeciesIcon = subspeciesIcon.replaceAll(colour.getShades()[2], colour.getShades()[1]);
//		subspeciesIcon = subspeciesIcon.replaceAll(colour.getShades()[3], colour.getShades()[2]);
//		subspeciesIcon = subspeciesIcon.replaceAll(colour.getShades()[4], colour.getShades()[3]);
		subspeciesIcon = subspeciesIcon.replaceAll("stroke=\"#(.*?)\"", "stroke=\""+colour.getShades()[1]+"\"");
		
		String finalImage = "<div style='width:80%;height:80%;position:absolute;left:10%;top:10%;'>"
								+ background
							+ "</div>"
							+"<div style='width:70%;height:70%;position:absolute;left:15%;top:20%;'>"
								+ subspeciesIcon
							+ "</div>"
							+ "<div style='width:60%;height:60%;position:absolute;left:20%;top:25%;'>"
								+ bottle
							+ "</div>";
		
		essenceMap.put(subspecies, finalImage);
		
		return finalImage;
	}

	private static String getEssenceEffectSvg(AbstractSubspecies subspecies) {
		String background = "";
		Colour colour = subspecies.getColour(null);
		try {
			InputStream is = ItemType.class.getResourceAsStream("/com/lilithsthrone/res/items/essenceBackground.svg");
			String s = Util.inputStreamToString(is);

			background = SvgUtil.colourReplacement(subspecies.getName(null), colour, s);
			is.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String subspeciesIcon = subspecies.getSVGStringNoBackground();
		subspeciesIcon = subspeciesIcon.replaceAll("fill=\"#(.*?)\"", "fill=\""+colour.getShades()[1]+"\"");
		subspeciesIcon = subspeciesIcon.replaceAll("stroke=\"#(.*?)\"", "stroke=\""+colour.getShades()[1]+"\"");

		String finalImage = "<div style='width:80%;height:80%;position:absolute;left:10%;top:10%;'>"
				+ background
				+ "</div>"
				+"<div style='width:70%;height:70%;position:absolute;left:15%;top:20%;'>"
				+ subspeciesIcon
				+ "</div>";

		return finalImage;
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
	
	public static List<AbstractItemType> getElisAlleywayItems() {
		return elisAlleywayItems;
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
