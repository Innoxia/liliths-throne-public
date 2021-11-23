package com.lilithsthrone.game.character.npc.misc.modextra;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.utils.Util;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Contains the necessary data to generate a trader inventory based on an XML configuration.
 */
public class TraderForSale<T extends AbstractCoreType> {
    private final int count;

    private final Set<String> modTags;
    private final Set<ItemTag> itemTags;

    private final List<ItemEffect> itemEffects;

    /**
     *
     * @param count Number of Things in this category for sale.
     * @param modTags Mod Tags to use when selecting items to sell.
     * @param itemTags ItemTags to use when select items to sell.
     * @oaram itemEffects A list of possible itemEffects to add to the generated items.
     */
    private TraderForSale(final int count, final Set<String> modTags, final Set<ItemTag> itemTags, final List<ItemEffect> itemEffects) {
        this.count = count;
        this.modTags = modTags;
        this.itemTags = itemTags;
        this.itemEffects = itemEffects;
    }

    /**
     * Given ta list of all possible items, generate a stream of random saleable items to populate a Trader NPCs
     * store.
     *
     * @param allItems
     *
     * @return
     */
    public Stream<TraderGenerationParameters<T>> getItemForSale(final List<T> allItems) {
        List<T> itemsToAdd = allItems.stream()
                .filter((item) -> item.hasAnyTags(modTags, itemTags))
                .collect(Collectors.toList());
        Collections.shuffle(itemsToAdd);

        return Stream.generate(new Supplier<T>() {
            @Override
            public T get() {
                // TODO: Add Rarity handling here.
                return Util.randomItemFrom(itemsToAdd);
            }
        }).map((item) -> new TraderGenerationParameters<T>(item, itemEffects)).limit(count);
    }

    /**
     * Creates a TraderForSale instance from XML.
     *
     * @param forSaleConfig Internal Element that points to a *ForSale configuration.
     *
     * @return
     */
    public static <T extends AbstractCoreType> TraderForSale<T> loadFromXml(final Element forSaleConfig) {
        // Load ModTag values if there are any
        final Set<String> modTags = new HashSet<>();
        forSaleConfig.getOptionalFirstOf("modTags")
                .ifPresent(new Consumer<Element>() {
                    @Override
                    public void accept(Element element) {
                        modTags.addAll(element.getAllOf("tag").stream()
                                .map((tag) -> tag.getTextContent().trim())
                                .collect(Collectors.toSet()));
                    }
                });

        // Load ItemTag values if there are any
        final Set<ItemTag> itemTags = new HashSet<>();
        forSaleConfig.getOptionalFirstOf("itemTags")
                .ifPresent(new Consumer<Element>() {
                    @Override
                    public void accept(Element element) {
                        itemTags.addAll(element.getAllOf("tag").stream()
                                .map((tag) -> ItemTag.valueOf(tag.getTextContent().trim()))
                                .collect(Collectors.toSet()));
                    }
                });

        final List<ItemEffect> effects = new ArrayList<>();
        forSaleConfig.getOptionalFirstOf("effects")
                .map((entry) -> entry.getAllOf("effect").stream()
                        .map((effect) -> ItemEffect.loadFromXML(effect.getInnerElement(), effect.getDocument())
                        ).collect(Collectors.toList())
                ).ifPresent(effects::addAll);

        final int count = Integer.valueOf(forSaleConfig.getAttribute("count"));

        return new TraderForSale<T>(count, modTags, itemTags, effects);
    }
}
