package com.lilithsthrone.game.character.npc.misc.modextra;

import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;

import java.util.List;

/**
 * Stores parameters that can be used by TraderForSale to output ItemType to generate.
 */
public class TraderGenerationParameters<T extends AbstractCoreType> {
    private final T thingType;
    private final TraderForSale<T> sourceForSale;
    private final int numberOfEffects;

    public TraderGenerationParameters(final T thingType, final TraderForSale<T> sourceForSale, int numberOfEffects) {
        this.thingType = thingType;
        this.sourceForSale = sourceForSale;
        this.numberOfEffects = numberOfEffects;
    }

    public T getThingType() {
        return thingType;
    }

    public List<ItemEffect> getItemEffects() {
        return sourceForSale.getItemEffects();
    }

    public boolean isPositiveEffects() {
        return sourceForSale.isPositiveEffects();
    }

    public boolean isAllowDefaultRandom() {
        return sourceForSale.isAllowDefaultRandom();
    }

    public int getNumberOfEffects() {
        return numberOfEffects;
    }
}
