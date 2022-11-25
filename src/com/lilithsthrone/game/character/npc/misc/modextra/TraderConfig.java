package com.lilithsthrone.game.character.npc.misc.modextra;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.npc.misc.ModNPC;
import com.lilithsthrone.game.inventory.ItemGeneration;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Tracks an XML based configuration for generating a traders inventory.
 */
public class TraderConfig {

    private final List<TraderForSale<AbstractItemType>> itemsForSale;
    private final List<TraderForSale<AbstractClothingType>> clothingForSale;
    private final List<TraderForSale<AbstractWeaponType>> weaponsForSale;

    /**
     * Construct a new TraderConfig object.
     *
     * @param itemsForSale    Items available in character's inventory
     * @param clothingForSale Clothing available in character's inventory
     * @param weaponsForSale  Weapons available in character's inventory
     */
    private TraderConfig(
            List<TraderForSale<AbstractItemType>> itemsForSale,
            List<TraderForSale<AbstractClothingType>> clothingForSale,
            List<TraderForSale<AbstractWeaponType>> weaponsForSale) {
        this.itemsForSale = itemsForSale;
        this.clothingForSale = clothingForSale;
        this.weaponsForSale = weaponsForSale;
    }

    /**
     * Update a traders inventory based on the defined item generation rules.
     *
     * @param npc The character to update.
     */
    public void updateTraderInventory(final ModNPC npc) {
        npc.clearNonEquippedInventory(false);

        final ItemGeneration generation = Main.game.getItemGen();

        // Add Items forSale
        itemsForSale.stream().flatMap(
                (forSale) -> forSale.getItemForSale(ItemType.getAllItems())
        ).forEach(
                (param) -> npc.addItem(generation.generateItem(param.getThingType()))
        );

        // Add clothing
        clothingForSale.stream().flatMap(
                (forSale) -> forSale.getItemForSale(ClothingType.getAllClothing())
        ).forEach(
                (param) -> npc.addClothing(generateClothing(param), false)
        );

        // Add weapons
        weaponsForSale.stream().flatMap(
                (forSale) -> forSale.getItemForSale(WeaponType.getAllWeapons())
        ).forEach(
                (param) -> npc.addWeapon(generation.generateWeapon(param.getThingType()), false)
        );
    }

    /**
     * A custom clothing generation method.
     *
     * @param clothingParams A Parameter object defining the rules for this clothing.
     *
     * @return
     */
    protected static AbstractClothing generateClothing(TraderGenerationParameters<AbstractClothingType> clothingParams) {
        // Always include the fixed effects.
        final List<ItemEffect> effects = new ArrayList<>(clothingParams.getItemEffects());

        // Add in random modifiers
        TFPotency potency = clothingParams.isPositiveEffects() ?
                TFPotency.getRandomWeightedPositivePotency() : TFPotency.getRandomWeightedNegativePotency();

        for (int i=0; i< clothingParams.getNumberOfEffects(); i++) {
            TFModifier rndMod = TFModifier.getClothingAttributeList().get(
                    Util.random.nextInt(TFModifier.getClothingAttributeList().size()));
            effects.add(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, rndMod, potency, 0));
        }

        // Allow Explicitly adding more enchantments.
        final AbstractClothing clothing = Main.game.getItemGen().generateClothing(
                clothingParams.getThingType(), clothingParams.isAllowDefaultRandom());

        effects.forEach((effect) -> clothing.addEffect(effect));

        return clothing;
    }

    /**
     * A static factory method for loading trader configs.
     *
     * @param element The xml element representing this config.
     */
    public static TraderConfig loadFromXML(final Element element) {
        // Handle trading setup.
        return element.getOptionalFirstOf("trader").map(
                TraderConfig::setupTraderInventory
        ).orElse(null);
    }

    private static TraderConfig setupTraderInventory(final Element element) {
        // Load ModTag values if there are any
        List<TraderForSale<AbstractItemType>> itemsForSale = streamToList(
                element.getAllOf("itemsForSale"),
                TraderForSale::loadFromXml
        );
        List<TraderForSale<AbstractClothingType>> clothingForSale = streamToList(
                element.getAllOf("clothingForSale"),
                TraderForSale::loadFromXml
        );
        List<TraderForSale<AbstractWeaponType>> weaponsForSale = streamToList(
                element.getAllOf("weaponsForSale"),
                TraderForSale::loadFromXml
        );

        return new TraderConfig(itemsForSale, clothingForSale, weaponsForSale);
    }

    /**
     * Apply a map operation to a generic list.
     * There's proabbly a better way to do this.
     *
     * @param elementList The list things to map.
     * @param mapFunction The function to apply.
     * @return
     */
    private static <T, T2> List<T> streamToList(List<T2> elementList, final Function<T2, T> mapFunction) {
        return elementList.stream().map(mapFunction).collect(Collectors.toList());
    }
}