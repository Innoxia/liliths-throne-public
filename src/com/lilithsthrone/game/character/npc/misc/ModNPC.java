package com.lilithsthrone.game.character.npc.misc;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.controller.xmlParsing.XMLLoadException;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.modnpc.TraderForSale;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.ItemGeneration;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import org.w3c.dom.Document;

import java.time.Month;
import java.util.List;
import java.util.Optional;

public class ModNPC extends NPC {
    private String modIdOverride = "";
    private boolean trader = false;

    private Optional<TraderForSale<AbstractItemType>> itemsForSale = Optional.empty();
    private Optional<TraderForSale<AbstractClothingType>> clothingForSale = Optional.empty();
    private Optional<TraderForSale<AbstractWeaponType>> weaponsForSale = Optional.empty();

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
    public void loadFromXML(org.w3c.dom.Element parentElement, Document doc, CharacterImportSetting... settings) {
        loadNPCVariablesFromXML(this, null, parentElement, doc, settings);

        // Add in the prefix if it's missing.
        final String id = getId();
        if ("NOT_SET".equals(id) || id == null) {
            setId("-1,"+ modIdOverride);
        }

        try {
            final Element xml = Element.getElement(parentElement, "", doc);

            // Handle trading setup.
            final Optional<Element> traderConfig = xml.getOptionalFirstOf("trader");
            if (traderConfig.isPresent()) {
                trader = true;
                setupTraderInventory(traderConfig.get());
            }
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
        trader = npc.trader;
        itemsForSale = npc.itemsForSale;
        clothingForSale = npc.clothingForSale;
        weaponsForSale = npc.weaponsForSale;
    }

    private void setupTraderInventory(final Element traderConfig) throws XMLLoadException {

        // Load ModTag values if there are any
        itemsForSale = traderConfig.getOptionalFirstOf("itemsForSale").map(
                TraderForSale::loadFromXml
        );
        clothingForSale = traderConfig.getOptionalFirstOf("clothingForSale").map(
                TraderForSale::loadFromXml
        );
        weaponsForSale = traderConfig.getOptionalFirstOf("weaponsForSale").map(
                TraderForSale::loadFromXml
        );

        // TODO: Add explicit items later.
    }

    /**
     * Updates the inventory of traders based on on rules in their config.
     */
    private void updateTraderInventory() {
        clearNonEquippedInventory(false);

        final ItemGeneration generation = Main.game.getItemGen();

        // Add Items forSale
        itemsForSale.ifPresent((forSale) -> forSale.getItemForSale(ItemType.getAllItems()).forEach(
                (param) -> addItem(generation.generateItem(param.getThingType()))
        ));

        // Add clothing
        clothingForSale.ifPresent((forSale) -> forSale.getItemForSale(ClothingType.getAllClothing()).forEach(
                (param) -> addClothing(generation.generateClothing(
                        param.getThingType(), (List<Colour>) null, param.getItemEffects()), false)
        ));

        // Add weapons
        weaponsForSale.ifPresent((forSale) -> forSale.getItemForSale(WeaponType.getAllWeapons()).forEach(
                (param) -> addWeapon(generation.generateWeapon(param.getThingType()), false)
        ));
    }

    @Override
    public void sortInventory() {
        // Make sure that a trader's inventory is updated on first open.
        // We only want to do this if the character's inventory is effectively empty.
        if (isTrader()
                && inventory.getTotalItemCount() == 0
                && inventory.getTotalClothingCount()  == 0
                && inventory.getTotalItemCount() == 0) {
            updateTraderInventory();
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
            updateTraderInventory();
        }
    }
}
