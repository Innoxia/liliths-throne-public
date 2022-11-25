package com.lilithsthrone.game.character.npc.misc.modextra;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.utils.Util;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Contains the necessary data to generate a trader inventory based on an XML configuration.
 */
public class TraderForSale<T extends AbstractCoreType> {
    private final int count;

    private final int minRandomEffects;
    private final int maxRandomEffects;
    private final boolean positiveEffects;
    private final boolean allowDefaultRandom;

    private final Set<String> modTags;
    private final Set<ItemTag> itemTags;

    private final List<ItemEffect> itemEffects;

    /**
     * @param count    Number of Things in this category for sale.
     * @param minRandomEffects A minimum number of random effects to apply.
     * @param maxRandomEffects A maximum number of random effects to apply.
     * @param positiveEffects If true, use postive effects, otherwise use negative effects.
     * @param allowDefaultRandom Set allowRandomEnchantment on clothing.
     * @param modTags  Mod Tags to use when selecting items to sell.
     * @param itemTags ItemTags to use when select items to sell.
     * @oaram itemEffects A list of possible itemEffects to add to the generated items.
     */
    protected TraderForSale(
            final int count,
            final int minRandomEffects,
            final int maxRandomEffects,
            final boolean positiveEffects,
            final boolean allowDefaultRandom,
            final Set<String> modTags,
            final Set<ItemTag> itemTags,
            final List<ItemEffect> itemEffects) {

        this.count = count;
        this.minRandomEffects = minRandomEffects;
        this.maxRandomEffects = maxRandomEffects;
        this.positiveEffects = positiveEffects;
        this.allowDefaultRandom = allowDefaultRandom;
        this.modTags = modTags;
        this.itemTags = itemTags;
        this.itemEffects = itemEffects;
    }

    public boolean isPositiveEffects() {
        return positiveEffects;
    }

    public List<ItemEffect> getItemEffects() {
        return itemEffects;
    }

    public boolean isAllowDefaultRandom() {
        return allowDefaultRandom;
    }

    /**
     * Given ta list of all possible items, generate a stream of random saleable items to populate a Trader NPCs
     * store.
     *
     * @param allItems
     * @return
     */
    public Stream<TraderGenerationParameters<T>> getItemForSale(final List<T> allItems) {
        final int range = maxRandomEffects - minRandomEffects;

        List<T> itemsToAdd = allItems.stream()
                .filter((item) -> item.hasAnyTags(modTags, itemTags))
                .collect(Collectors.toList());
        Collections.shuffle(itemsToAdd);

        return Stream.generate(() -> Util.randomItemFrom(itemsToAdd))
                .map((item) -> new TraderGenerationParameters<T>(
                        item,
                        this,
                        minRandomEffects + (range > 0 ? Util.random.nextInt(range) : 0))
                ).limit(count);
    }

    /**
     * Creates a TraderForSale instance from XML.
     *
     * @param forSaleConfig Internal Element that points to a *ForSale configuration.
     * @return
     */
    public static <T extends AbstractCoreType> TraderForSale<T> loadFromXml(final Element forSaleConfig) {

        // Load ModTag values if there are any
        final Set<String> modTags = new HashSet<>();
        forSaleConfig.getOptionalFirstOf("modTags")
                .ifPresent((element) -> {
                    modTags.addAll(element.getAllOf("tag").stream()
                            .map((tag) -> tag.getTextContent().trim())
                            .collect(Collectors.toSet()));
                });

        // Load ItemTag values if there are any
        final Set<ItemTag> itemTags = new HashSet<>();
        forSaleConfig.getOptionalFirstOf("itemTags")
                .ifPresent((element) -> {
                    itemTags.addAll(element.getAllOf("tag").stream()
                            .map((tag) -> ItemTag.valueOf(tag.getTextContent().trim()))
                            .collect(Collectors.toSet()));
                });

        final List<ItemEffect> effects = new ArrayList<>();
        final Optional<Element> effectsConfig = forSaleConfig.getOptionalFirstOf("effects");

        final int minEffects = parseValue(effectsConfig, "minRandom", 0, Integer::valueOf);
        final int maxEffects = parseValue(effectsConfig, "maxRandom", 0, Integer::valueOf);;
        final boolean positive = parseValue(effectsConfig, "positive", true, Boolean::valueOf);
        final boolean allowDefaultRandom = parseValue(
                effectsConfig, "allowDefaultRandom", false, Boolean::valueOf);

        // Load fixed effects
        effectsConfig.map((entry) -> entry.getAllOf("effect").stream()
                .map((effect) -> ItemEffect.loadFromXML(effect.getInnerElement(), effect.getDocument())
                ).collect(Collectors.toList())
        ).ifPresent(effects::addAll);

        final int count = Integer.valueOf(forSaleConfig.getAttribute("count"));

        return new TraderForSale<T>(count, minEffects, maxEffects, positive, allowDefaultRandom, modTags, itemTags, effects);
    }

    /**
     * Parse an attribute to a given value.
     *
     * @param element An Xml element to retrieve an attribute off of.
     * @param attr The attribute to retrieve
     * @param def The default value to use if the attribute is not found.
     * @param parser A parser function to use.
     *
     * @return
     */
    private static <T> T parseValue(Optional<Element> element, final String attr, T def, Function<String, T> parser) {
        final String value = element.map((e) -> e.getAttribute(attr)).orElse("");

        if (value == "") {
            return def;
        }

        return parser.apply(value);
    }
}
