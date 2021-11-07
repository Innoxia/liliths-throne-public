package com.lilithsthrone.game.character.npc.misc;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.time.Month;

public class ModNPC extends NPC {
    private String modIdOverride = "";
    private boolean trader = false;

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
        return trader;
    }

    @Override
    public void setStartingBody(boolean setPersona) {
    }

    @Override
    public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
        loadNPCVariablesFromXML(this, null, parentElement, doc, settings);

        // Add in the prefix if it's missing.
        final String id = getId();
        if ("NOT_SET".equals(id) || id == null) {
            setId("-1,"+ modIdOverride);
        }

        // Handle trading setup.
        final Node traderSetup = parentElement.getElementsByTagName("trader").item(0);
        if (traderSetup != null ) {
            trader = true;
        }
    }

    @Override
    public void changeFurryLevel() {
    }

    @Override
    public DialogueNode getEncounterDialogue() {
        return null;
    }

}
