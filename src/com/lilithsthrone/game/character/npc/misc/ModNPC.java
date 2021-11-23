package com.lilithsthrone.game.character.npc.misc;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.controller.xmlParsing.XMLLoadException;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.modextra.TraderConfig;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import org.w3c.dom.Document;

import java.time.Month;

public class ModNPC extends NPC {
    private String modIdOverride = "";

    private TraderConfig traderConfig;

    public ModNPC() {
        this(false);
    }

    public ModNPC(boolean isImported) {
        super(isImported, new NameTriplet("Mod"), "Mod", "-",
                18, Month.JUNE, 10,
                1, Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.HUMAN,
                new CharacterInventory(0), WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
    }

    public void setModIdOverride(final String modIdOverride) {
        this.modIdOverride = modIdOverride;
    }

    @Override
    public boolean isUnique() {
        return true;
    }

    @Override
    public boolean isTrader() {
        return traderConfig != null;
    }

    @Override
    public void setStartingBody(boolean setPersona) {
    }

    @Override
    public void loadFromXML(org.w3c.dom.Element parentElement, Document doc, CharacterImportSetting... settings) {
        loadNPCVariablesFromXML(this, null, parentElement, doc, settings);

        // Add in the prefix if it's missing.
        final String id = getId();
        if ("NOT_SET".equals(id) || id == null) {
            setId("-1,"+ modIdOverride);
        }

        try {
            final Element xml = Element.getElement(parentElement, "", doc);

            traderConfig = TraderConfig.loadFromXML(xml);
        } catch (XMLLoadException e) {
            System.err.println("Unable to load ModNPC: " + this.getName() + "[" + this.getId() + "]");
            e.printStackTrace();
        }
    }

    /**
     * Update some values that should not be saved as part of the game from the raw
     * npc code.
     *
     * @param npc
     */
    public void updateFromOrig(ModNPC npc) {
        this.traderConfig = npc.traderConfig;

        this.buyModifier = npc.buyModifier;
        this.sellModifier = npc.sellModifier;
        this.addedToContacts = npc.addedToContacts;
    }

    @Override
    public void sortInventory() {
        // Make sure that a trader's inventory is updated on first open.
        // We only want to do this if the character's inventory is effectively empty.
        if (isTrader()
                && inventory.getTotalItemCount() == 0
                && inventory.getTotalClothingCount()  == 0
                && inventory.getTotalItemCount() == 0) {
            traderConfig.updateTraderInventory(this);
        }
        super.sortInventory();
    }

    @Override
    public void changeFurryLevel() {
    }

    @Override
    public DialogueNode getEncounterDialogue() {
        return null;
    }

    @Override
    public void dailyUpdate() {

        // Only rewrite the inventory if the player is a trader
        if (isTrader()) {
            traderConfig.updateTraderInventory(this);
        }
    }
}
