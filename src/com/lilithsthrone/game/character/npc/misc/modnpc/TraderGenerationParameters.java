package com.lilithsthrone.game.character.npc.misc.modnpc;

import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;

import java.util.List;

/**
 * Stores parameters that can be used by TraderForSale to output ItemType to generate.
 */
public class TraderGenerationParameters<T extends AbstractCoreType> {
    private T thingType;
    private List<ItemEffect> itemEffects;

    public TraderGenerationParameters(T thingType, List<ItemEffect> itemEffects) {
        this.thingType = thingType;
        this.itemEffects = itemEffects;
    }

    public T getThingType() {
        return thingType;
    }

    public List<ItemEffect> getItemEffects() {
        return itemEffects;
    }
}
