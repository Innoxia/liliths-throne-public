package com.lilithsthrone.threading;

import com.lilithsthrone.game.character.npc.NPCRegistry;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author KeldonSlayer (DrZed)
 * @version 0.4.10.7
 * @since 0.4.7.6
 */
public class NPCThread extends Thread {
    /* Thread-Safe Boolean for accounting */
    private static final AtomicBoolean
            NPC_DOMINION_INITIALIZED = new AtomicBoolean(false), NPC_ENFORCER_INITIALIZED = new AtomicBoolean(false),
            NPC_SHOPPING_INITIALIZED = new AtomicBoolean(false), NPC_HARPY_NEST_INITIALIZED = new AtomicBoolean(false),
            NPC_MISC_INITIALIZED = new AtomicBoolean(false), NPC_ASSORTED_INITIALIZED = new AtomicBoolean(false),
            NPC_SPECIAL_LOCATION_INITIALIZED = new AtomicBoolean(false), NPC_WALLS_END_INITIALIZED = new AtomicBoolean(false),
            NPC_ELIS_INITIALIZED = new AtomicBoolean(false), NPC_FIELDS_INITIALIZED = new AtomicBoolean(false),
            NPC_FARMERS_MARKET_INITIALIZED = new AtomicBoolean(false), NPC_SUBMISSION_INITIALIZED = new AtomicBoolean(false);


    public NPCThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        /* Safety Check */
        switch (this.getName()) {
            case "initMiscNPCs":
                if (!NPC_MISC_INITIALIZED.get()) {
                    NPCRegistry.initMiscNPCs();
                    NPC_MISC_INITIALIZED.set(true);
                    try { 
                        join(); // merges the thread's memory with main thread
                    } catch (Exception ignored) {} // This should never trip
                }
                break;
            case "initDominionNPCs":
                if (!NPC_DOMINION_INITIALIZED.get()) {
                    NPCRegistry.initDominionNPCs();
                    NPC_DOMINION_INITIALIZED.set(true);
                    try {
                        join();
                    } catch (Exception ignored) {}
                }
                break;
            case "initEnforcerNPCs":
                if (!NPC_ENFORCER_INITIALIZED.get()) {
                    NPCRegistry.initEnforcerNPCs();
                    NPC_ENFORCER_INITIALIZED.set(true);
                    try {
                        join();
                    } catch (Exception ignored) {}
                }
                break;
            case "initShoppingNPCs":
                if (!NPC_SHOPPING_INITIALIZED.get()) {
                    NPCRegistry.initShoppingNPCs();
                    NPC_SHOPPING_INITIALIZED.set(true);
                    try {
                        join();
                    } catch (Exception ignored) {}
                }
                break;
            case "initHarpyNestNPCs":
                if (!NPC_HARPY_NEST_INITIALIZED.get()) {
                    NPCRegistry.initHarpyNestNPCs();
                    NPC_HARPY_NEST_INITIALIZED.set(true);
                    try {
                        join();
                    } catch (Exception ignored) {}
                }
                break;
            case "initAssortedNPCs":
                if (!NPC_ASSORTED_INITIALIZED.get()) {
                    NPCRegistry.initAssortedNPCs();
                    NPC_ASSORTED_INITIALIZED.set(true);
                    try {
                        join();
                    } catch (Exception ignored) {}
                }
                break;
            case "initSpecialLocationNPCs":
                if (!NPC_SPECIAL_LOCATION_INITIALIZED.get()) {
                    NPCRegistry.initSpecialLocationNPCs();
                    NPC_SPECIAL_LOCATION_INITIALIZED.set(true);
                    try {
                        join();
                    } catch (Exception ignored) {}
                }
                break;
            case "initFieldsNPCs":
                if (!NPC_FIELDS_INITIALIZED.get()) {
                    NPCRegistry.initFieldsNPCs();
                    NPC_FIELDS_INITIALIZED.set(true);
                    try {
                        join();
                    } catch (Exception ignored) {}
                }
                break;
            case "initElisNPCs":
                if (!NPC_ELIS_INITIALIZED.get()) {
                    NPCRegistry.initElisNPCs();
                    NPC_ELIS_INITIALIZED.set(true);
                    try {
                        join();
                    } catch (Exception ignored) {}
                }
                break;
            case "initFarmersMarketNPCs":
                if (!NPC_FARMERS_MARKET_INITIALIZED.get()) {
                    NPCRegistry.initFarmersMarketNPCs();
                    NPC_FARMERS_MARKET_INITIALIZED.set(true);
                    try {
                        join();
                    } catch (Exception ignored) {}
                }
                break;
            case "initWallsEndNPCs":
                if (!NPC_WALLS_END_INITIALIZED.get()) {
                    NPCRegistry.initWallsEndNPCs();
                    NPC_WALLS_END_INITIALIZED.set(true);
                    try {
                        join();
                    } catch (Exception ignored) {}
                }
                break;
            case "initSubmissionNPCs":
                if (!NPC_SUBMISSION_INITIALIZED.get()) {
                    NPCRegistry.initSubmissionNPCs();
                    NPC_SUBMISSION_INITIALIZED.set(true);
                    try {
                        join();
                    } catch (Exception ignored) {}
                }
                break;
        }
    }

    public static boolean npcsInitialized() {
        return NPC_MISC_INITIALIZED.get() && NPC_DOMINION_INITIALIZED.get() && NPC_ENFORCER_INITIALIZED.get()
                && NPC_SHOPPING_INITIALIZED.get() && NPC_HARPY_NEST_INITIALIZED.get() && NPC_ASSORTED_INITIALIZED.get()
                && NPC_SPECIAL_LOCATION_INITIALIZED.get() && NPC_FIELDS_INITIALIZED.get() && NPC_ELIS_INITIALIZED.get()
                && NPC_FARMERS_MARKET_INITIALIZED.get() && NPC_WALLS_END_INITIALIZED.get() && NPC_SUBMISSION_INITIALIZED.get();
    }

    public static void deInitialize() {
        NPC_MISC_INITIALIZED.set(false);
        NPC_DOMINION_INITIALIZED.set(false);
        NPC_ENFORCER_INITIALIZED.set(false);
        NPC_SHOPPING_INITIALIZED.set(false);
        NPC_HARPY_NEST_INITIALIZED.set(false);
        NPC_ASSORTED_INITIALIZED.set(false);
        NPC_SPECIAL_LOCATION_INITIALIZED.set(false);
        NPC_FIELDS_INITIALIZED.set(false);
        NPC_ELIS_INITIALIZED.set(false);
        NPC_FARMERS_MARKET_INITIALIZED.set(false);
        NPC_WALLS_END_INITIALIZED.set(false);
        NPC_SUBMISSION_INITIALIZED.set(false);
    }
}