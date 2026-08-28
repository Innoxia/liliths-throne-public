package com.lilithsthrone.modding;

import com.lilithsthrone.controller.ModController;

/**
 * Used for old-style res/mods mods.
 * @author Anonymous-BCFED
 * @since FIXME
 */
public class LegacyModInfo extends ModInfo {
    @Override
    protected String getIdPrefix() {
        return this.authors[0];
    }

    @Override
    public boolean hasPlugin() {
        return false;
    }

    @Override
    public void loadPlugin(ModController mc) {
        return;
    }
}
