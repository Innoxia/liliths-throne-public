package com.lilithsthrone.game.character.npc.misc;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.controller.xmlParsing.XMLLoadException;
import com.lilithsthrone.controller.xmlParsing.XMLMissingTagException;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.ItemGeneration;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import org.w3c.dom.Document;

import java.time.Month;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ModNPC extends NPC {
    private String modIdOverride = "";
    private boolean trader = false;

    private final Set<String> modTagsForSale = new HashSet<>();
    private final Set<ItemTag> itemTagsForSale = new HashSet<>();

    private int itemCountForSale = 0;
    private int clothingCountForSale = 0;
    private int weaponCountForSale = 0;

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

    private void setupTraderInventory(final Element traderConfig) throws XMLLoadException {

        // Load ModTag values if there are any
        traderConfig.getOptionalFirstOf("modTagsForSale")
                .ifPresent(new Consumer<Element>() {
                    @Override
                    public void accept(Element element) {
                        modTagsForSale.addAll(element.getAllOf("tag").stream()
                                .map((tag) -> tag.getTextContent().trim())
                                .collect(Collectors.toSet()));
                    }
                });

        // Load ItemTag values if there are any
        traderConfig.getOptionalFirstOf("itemTagsForSale")
                .ifPresent(new Consumer<Element>() {
                    @Override
                    public void accept(Element element) {
                        itemTagsForSale.addAll(element.getAllOf("tag").stream()
                                .map((tag) -> ItemTag.valueOf(tag.getTextContent().trim()))
                                .collect(Collectors.toSet()));
                    }
                });

        try {
            final Element forSaleCount = traderConfig.getMandatoryFirstOf("forSaleCount");

            itemCountForSale = Integer.valueOf(forSaleCount.getAttribute("items"));
            clothingCountForSale = Integer.valueOf(forSaleCount.getAttribute("clothing"));
            weaponCountForSale = Integer.valueOf(forSaleCount.getAttribute("weapons"));

        } catch (XMLMissingTagException e) {
            System.err.println("Trader ModNPC missing forSaleCount");
            e.printStackTrace();
            throw new XMLLoadException(e);
        }

        // TODO: Add explicit items later.
    }

    /**
     * Updates the inventory of traders based on on rules in their config.
     */
    private void updateTraderInventory() {
        clearNonEquippedInventory(false);

        final ItemGeneration generation = Main.game.getItemGen();

        // Add items
        List<AbstractItemType> itemsToAdd = ItemType.getAllItems().stream()
                .filter((type) -> !Collections.disjoint(type.getItemTags(), itemTagsForSale) || !Collections.disjoint(type.getModTags(), modTagsForSale))
                .collect(Collectors.toList());
        Collections.shuffle(itemsToAdd);

        for (int i=0; i<itemCountForSale ; i++) {
            addItem(generation.generateItem(Util.randomItemFrom(itemsToAdd)));
        }

        // Add clothing
        List<AbstractClothingType> clothingToAdd = ClothingType.getAllClothing().stream()
                .filter((type) -> !Collections.disjoint(type.getDefaultItemTags(), itemTagsForSale) || !Collections.disjoint(type.getModTags(), modTagsForSale))
                .collect(Collectors.toList());
        Collections.shuffle(clothingToAdd);

        for (int i=0; i<clothingCountForSale; i++) {
            addClothing(generation.generateClothing(Util.randomItemFrom(clothingToAdd)), false);
        }

        // Add weapons
        List<AbstractWeaponType> weaponsToAdd = WeaponType.getAllWeapons().stream()
                .filter((type) -> !Collections.disjoint(type.getItemTags(), itemTagsForSale) || !Collections.disjoint(type.getModTags(), modTagsForSale))
                .collect(Collectors.toList());
        Collections.shuffle(weaponsToAdd);

        for (int i=0; i<weaponCountForSale; i++) {
            addWeapon(generation.generateWeapon(Util.randomItemFrom(weaponsToAdd)), false);
        }
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
