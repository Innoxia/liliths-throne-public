package com.lilithsthrone.game.character.npc;

import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.dominion.Amber;
import com.lilithsthrone.game.character.npc.dominion.Angel;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Ashley;
import com.lilithsthrone.game.character.npc.dominion.Brax;
import com.lilithsthrone.game.character.npc.dominion.Bunny;
import com.lilithsthrone.game.character.npc.dominion.Callie;
import com.lilithsthrone.game.character.npc.dominion.CandiReceptionist;
import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Elle;
import com.lilithsthrone.game.character.npc.dominion.Felicia;
import com.lilithsthrone.game.character.npc.dominion.Fiammetta;
import com.lilithsthrone.game.character.npc.dominion.Finch;
import com.lilithsthrone.game.character.npc.dominion.Hannah;
import com.lilithsthrone.game.character.npc.dominion.HarpyBimbo;
import com.lilithsthrone.game.character.npc.dominion.HarpyBimboCompanion;
import com.lilithsthrone.game.character.npc.dominion.HarpyDominant;
import com.lilithsthrone.game.character.npc.dominion.HarpyDominantCompanion;
import com.lilithsthrone.game.character.npc.dominion.HarpyNympho;
import com.lilithsthrone.game.character.npc.dominion.HarpyNymphoCompanion;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Jules;
import com.lilithsthrone.game.character.npc.dominion.Kalahari;
import com.lilithsthrone.game.character.npc.dominion.Kate;
import com.lilithsthrone.game.character.npc.dominion.Kay;
import com.lilithsthrone.game.character.npc.dominion.Kruger;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Loppy;
import com.lilithsthrone.game.character.npc.dominion.Lovienne;
import com.lilithsthrone.game.character.npc.dominion.Lumi;
import com.lilithsthrone.game.character.npc.dominion.Natalya;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.npc.dominion.NyanMum;
import com.lilithsthrone.game.character.npc.dominion.Pazu;
import com.lilithsthrone.game.character.npc.dominion.Pix;
import com.lilithsthrone.game.character.npc.dominion.Ralph;
import com.lilithsthrone.game.character.npc.dominion.RentalMommy;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.npc.dominion.Saellatrix;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.npc.dominion.Sean;
import com.lilithsthrone.game.character.npc.dominion.SupplierLeader;
import com.lilithsthrone.game.character.npc.dominion.SupplierPartner;
import com.lilithsthrone.game.character.npc.dominion.TestNPC;
import com.lilithsthrone.game.character.npc.dominion.Vanessa;
import com.lilithsthrone.game.character.npc.dominion.Vicky;
import com.lilithsthrone.game.character.npc.dominion.Wes;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKatherine;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKelly;
import com.lilithsthrone.game.character.npc.fields.Angelixx;
import com.lilithsthrone.game.character.npc.fields.Arion;
import com.lilithsthrone.game.character.npc.fields.Astrapi;
import com.lilithsthrone.game.character.npc.fields.Aurokaris;
import com.lilithsthrone.game.character.npc.fields.Belle;
import com.lilithsthrone.game.character.npc.fields.Ceridwen;
import com.lilithsthrone.game.character.npc.fields.Dale;
import com.lilithsthrone.game.character.npc.fields.Daphne;
import com.lilithsthrone.game.character.npc.fields.Eisek;
import com.lilithsthrone.game.character.npc.fields.Evelyx;
import com.lilithsthrone.game.character.npc.fields.Fae;
import com.lilithsthrone.game.character.npc.fields.Farah;
import com.lilithsthrone.game.character.npc.fields.Flash;
import com.lilithsthrone.game.character.npc.fields.Ghost;
import com.lilithsthrone.game.character.npc.fields.Golix;
import com.lilithsthrone.game.character.npc.fields.Hale;
import com.lilithsthrone.game.character.npc.fields.Hammer;
import com.lilithsthrone.game.character.npc.fields.HeadlessHorseman;
import com.lilithsthrone.game.character.npc.fields.Heather;
import com.lilithsthrone.game.character.npc.fields.Imsu;
import com.lilithsthrone.game.character.npc.fields.Jess;
import com.lilithsthrone.game.character.npc.fields.Kazik;
import com.lilithsthrone.game.character.npc.fields.Kheiron;
import com.lilithsthrone.game.character.npc.fields.Lunette;
import com.lilithsthrone.game.character.npc.fields.Lunexis;
import com.lilithsthrone.game.character.npc.fields.Minotallys;
import com.lilithsthrone.game.character.npc.fields.Monica;
import com.lilithsthrone.game.character.npc.fields.Moreno;
import com.lilithsthrone.game.character.npc.fields.Nir;
import com.lilithsthrone.game.character.npc.fields.Nizhoni;
import com.lilithsthrone.game.character.npc.fields.Oglix;
import com.lilithsthrone.game.character.npc.fields.Penelope;
import com.lilithsthrone.game.character.npc.fields.Silvia;
import com.lilithsthrone.game.character.npc.fields.Sleip;
import com.lilithsthrone.game.character.npc.fields.Sterope;
import com.lilithsthrone.game.character.npc.fields.Ursa;
import com.lilithsthrone.game.character.npc.fields.Vronti;
import com.lilithsthrone.game.character.npc.fields.Wynter;
import com.lilithsthrone.game.character.npc.fields.Yui;
import com.lilithsthrone.game.character.npc.fields.Ziva;
import com.lilithsthrone.game.character.npc.misc.GenericAndrogynousNPC;
import com.lilithsthrone.game.character.npc.misc.GenericFemaleNPC;
import com.lilithsthrone.game.character.npc.misc.GenericMaleNPC;
import com.lilithsthrone.game.character.npc.misc.GenericTrader;
import com.lilithsthrone.game.character.npc.misc.PrologueFemale;
import com.lilithsthrone.game.character.npc.misc.PrologueMale;
import com.lilithsthrone.game.character.npc.submission.Axel;
import com.lilithsthrone.game.character.npc.submission.Claire;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.Elizabeth;
import com.lilithsthrone.game.character.npc.submission.Epona;
import com.lilithsthrone.game.character.npc.submission.FortressAlphaLeader;
import com.lilithsthrone.game.character.npc.submission.FortressFemalesLeader;
import com.lilithsthrone.game.character.npc.submission.FortressMalesLeader;
import com.lilithsthrone.game.character.npc.submission.HazmatRat;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.npc.submission.Murk;
import com.lilithsthrone.game.character.npc.submission.Roxy;
import com.lilithsthrone.game.character.npc.submission.Shadow;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.character.npc.submission.SlimeGuardFire;
import com.lilithsthrone.game.character.npc.submission.SlimeGuardIce;
import com.lilithsthrone.game.character.npc.submission.SlimeQueen;
import com.lilithsthrone.game.character.npc.submission.SlimeRoyalGuard;
import com.lilithsthrone.game.character.npc.submission.Takahashi;
import com.lilithsthrone.game.character.npc.submission.Vengar;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.threading.NPCThread;
import com.lilithsthrone.threading.PreInitializationThread;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

import java.util.Set;

import static com.lilithsthrone.game.Game.loadingVersion;

/**
 * @since 0.4.10.7
 * @version 0.4.10.7
 * @author Innoxia, KeldonSlayer (DrZed)
 */
public class NPCRegistry {
    // The Time Option allows testing the speed difference, enable threading is an option to compare using the previous time option
    private static final boolean TIME_TESTING = false, ENABLE_NPC_THREADING = true, TIME_TEST_INDIVIDUAL_NPCS = false;


    public static void initUniqueNPCs() {
        if (ENABLE_NPC_THREADING) {
            initUniqueNPCsThreaded();
            return;
        }
        long timeStarted = System.nanoTime();

        initMiscNPCs();
        initDominionNPCs();
        initEnforcerNPCs();
        initShoppingNPCs();
        initHarpyNestNPCs();
        initAssortedNPCs();
        initSpecialLocationNPCs();
        initFieldsNPCs();
        initElisNPCs();
        initFarmersMarketNPCs();
        initWallsEndNPCs();
        initSubmissionNPCs();

        initRelations(Main.game.getNPCMap().keySet());

        if (TIME_TESTING)
            PreInitializationThread.logTime("initUniqueNPCs took", System.nanoTime() - timeStarted);
    }

    /* "Arbitrarily" separated NPC groupings */
    private static void initUniqueNPCsThreaded() {
        long waits = 0, timeStarted = System.nanoTime();
        new NPCThread("initMiscNPCs").start();
        new NPCThread("initDominionNPCs").start();
        new NPCThread("initEnforcerNPCs").start();
        new NPCThread("initShoppingNPCs").start();
        new NPCThread("initHarpyNestNPCs").start();
        new NPCThread("initAssortedNPCs").start();
        new NPCThread("initSpecialLocationNPCs").start();
        new NPCThread("initFieldsNPCs").start();
        new NPCThread("initElisNPCs").start();
        new NPCThread("initFarmersMarketNPCs").start();
        new NPCThread("initWallsEndNPCs").start();
        new NPCThread("initSubmissionNPCs").start();

        if (TIME_TESTING)
            while (!NPCThread.npcsInitialized()) {
                waits++;
            }

        initRelations(Main.game.getNPCMap().keySet());
        if (TIME_TESTING)
            PreInitializationThread.logTime("Threading took [ " + waits + " ] wait cycles! or ", System.nanoTime() - timeStarted);
    }

    public static void initMiscNPCs() {
        long timeStarted = System.nanoTime();
        // Misc.:
        addIfMissingSafely(GenericMaleNPC.class);
        addIfMissingSafely(GenericFemaleNPC.class);
        addIfMissingSafely(GenericAndrogynousNPC.class);
        addIfMissingSafely(PrologueMale.class);
        addIfMissingSafely(PrologueFemale.class);
        addIfMissingSafely(GenericTrader.class);
        addIfMissingSafely(TestNPC.class);

        // Contributors:
        addIfMissingSafely(Lumi.class);
        addIfMissingSafely(Pazu.class);
        addIfMissingSafely(Ashley.class);
        addIfMissingSafely(Callie.class);

        if (TIME_TESTING)
            PreInitializationThread.logTime("initMiscNPCs took", System.nanoTime() - timeStarted);
    }

    /*
     *          Dominion
     */
    public static void initDominionNPCs() {
        long timeStarted = System.nanoTime();
        // Story:
        addIfMissingSafely(Rose.class);
        addIfMissingSafely(Lilaya.class);
        addIfMissingSafely(Arthur.class);

        // Angel's kiss:
        addIfMissingSafely(Angel.class);
        addIfMissingSafely(Bunny.class);
        addIfMissingSafely(Loppy.class);

        // Nightclub:
        addIfMissingSafely(Jules.class);
        addIfMissingSafely(Kruger.class);
        addIfMissingSafely(Kalahari.class);


        if (TIME_TESTING)
            PreInitializationThread.logTime("initDominionNPCs took", System.nanoTime() - timeStarted);
    }

    public static void initEnforcerNPCs() {
        long timeStarted = System.nanoTime();
        // Enforcers:
        addIfMissingSafely(Brax.class);
        addIfMissingSafely(CandiReceptionist.class);
        addIfMissingSafely(Wes.class);
        addIfMissingSafely(Elle.class);

        // Enforcer station:
        addIfMissingSafely(Sterope.class);
        addIfMissingSafely(Hammer.class);
        addIfMissingSafely(Ghost.class);
        addIfMissingSafely(Angelixx.class);
        addIfMissingSafely(Sleip.class);
        addIfMissingSafely(Nir.class);

        if (TIME_TESTING)
            PreInitializationThread.logTime("initEnforcerNPCs took", System.nanoTime() - timeStarted);
    }

    public static void initHarpyNestNPCs() {
        long timeStarted = System.nanoTime();
        // Harpy nests:
        addIfMissingSafely(Scarlett.class);
        addIfMissingSafely(Helena.class);
        addIfMissingSafely(HarpyBimbo.class);
        addIfMissingSafely(HarpyBimboCompanion.class);
        addIfMissingSafely(HarpyDominant.class);
        addIfMissingSafely(HarpyDominantCompanion.class);
        addIfMissingSafely(HarpyNympho.class);
        addIfMissingSafely(HarpyNymphoCompanion.class);

        if (TIME_TESTING)
            PreInitializationThread.logTime("initHarpyNestNPCs took", System.nanoTime() - timeStarted);
    }

    public static void initAssortedNPCs() {
        long timeStarted = System.nanoTime();
        // City hall:
        addIfMissingSafely(Vanessa.class);

        // Dominion Express:
        addIfMissingSafely(Natalya.class);

        // Slaver alley:
        addIfMissingSafely(Finch.class);
        addIfMissingSafely(Sean.class);


        // Rental mommy;
        addIfMissingSafely(RentalMommy.class);

        // 'Daddy':
        addIfMissingSafely(Daddy.class);



        // Lovienne's Luxuries:
        addIfMissingSafely(Saellatrix.class);
        addIfMissingSafely(Fiammetta.class);

        // Headless horseman:
        addIfMissingSafely(HeadlessHorseman.class);

        // Elder lilin:
        addIfMissingSafely(Lunette.class);
        addIfMissingSafely(Lovienne.class);

        if (TIME_TESTING)
            PreInitializationThread.logTime("initAssortedNPCs took", System.nanoTime() - timeStarted);
    }


    public static void initSubmissionNPCs() {
        long timeStarted = System.nanoTime();
        // Submission:

        // Story
        addIfMissingSafely(Lyssieth.class);
        addIfMissingSafely(Elizabeth.class);
        addIfMissingSafely(Takahashi.class);
        addIfMissingSafely(DarkSiren.class);


        // Gambling den:
        addIfMissingSafely(Roxy.class);
        addIfMissingSafely(Axel.class);
        addIfMissingSafely(Epona.class);

        // Rat Warrens:
        addIfMissingSafely(Vengar.class);
        addIfMissingSafely(Shadow.class);
        addIfMissingSafely(Silence.class);
        addIfMissingSafely(Murk.class);

        // Hazmat Rat:
        addIfMissingSafely(HazmatRat.class);

        if (TIME_TESTING)
            PreInitializationThread.logTime("initSubmissionNPCs took", System.nanoTime() - timeStarted);
    }

    public static void initSpecialLocationNPCs() {
        long timeStarted = System.nanoTime();
        // Sawlty Towers (Arthur/Felicia's apartment building):
        addIfMissingSafely(Felicia.class);

        // Zaranix's home:
        addIfMissingSafely(Zaranix.class);
        addIfMissingSafely(Amber.class);
        addIfMissingSafely(ZaranixMaidKatherine.class);
        addIfMissingSafely(ZaranixMaidKelly.class);

        // Fortress
        addIfMissingSafely(Claire.class);
        addIfMissingSafely(FortressAlphaLeader.class);
        addIfMissingSafely(FortressFemalesLeader.class);
        addIfMissingSafely(FortressMalesLeader.class);

        // Slime queen:
        addIfMissingSafely(SlimeQueen.class);
        addIfMissingSafely(SlimeGuardIce.class);
        addIfMissingSafely(SlimeGuardFire.class);
        addIfMissingSafely(SlimeRoyalGuard.class);

        if (TIME_TESTING)
            PreInitializationThread.logTime("initSpecialLocationNPCs took", System.nanoTime() - timeStarted);
    }

    public static void initFarmersMarketNPCs() {
        long timeStarted = System.nanoTime();
        // Farmer's Market:
        addIfMissingSafely(Fae.class);
        addIfMissingSafely(Silvia.class);
        addIfMissingSafely(Kazik.class);
        addIfMissingSafely(Yui.class);
        addIfMissingSafely(Nizhoni.class);
        addIfMissingSafely(Moreno.class);
        addIfMissingSafely(Heather.class);
        addIfMissingSafely(Ziva.class);
        addIfMissingSafely(Eisek.class);

        if (TIME_TESTING)
            PreInitializationThread.logTime("initFarmersMarketNPCs took", System.nanoTime() - timeStarted);
    }

    public static void initWallsEndNPCs() {
        long timeStarted = System.nanoTime();
        // Wall's End:
        addIfMissingSafely(Monica.class);
        addIfMissingSafely(Ceridwen.class);
        addIfMissingSafely(Imsu.class);
        addIfMissingSafely(Hale.class);
        addIfMissingSafely(Penelope.class);
        addIfMissingSafely(Belle.class);
        addIfMissingSafely(Daphne.class);
        addIfMissingSafely(Farah.class);

        if (TIME_TESTING)
            PreInitializationThread.logTime("initWallsEndNPCs took", System.nanoTime() - timeStarted);
    }

    public static void initShoppingNPCs() {
        long timeStarted = System.nanoTime();
        // Shopping Promenade:
        addIfMissingSafely(Ralph.class);
        addIfMissingSafely(Nyan.class);
        addIfMissingSafely(NyanMum.class);
        addIfMissingSafely(Vicky.class);
        addIfMissingSafely(Pix.class);
        addIfMissingSafely(Hannah.class);
        addIfMissingSafely(Kate.class);
        addIfMissingSafely(SupplierLeader.class);
        addIfMissingSafely(SupplierPartner.class);
        addIfMissingSafely(Kay.class);

        if (TIME_TESTING)
            PreInitializationThread.logTime("initShoppingNPCs took", System.nanoTime() - timeStarted);
    }

    /*
     *          Elis
     */
    public static void initElisNPCs() {
        long timeStarted = System.nanoTime();
        // The Red Dragon:
        addIfMissingSafely(Flash.class);
        addIfMissingSafely(Jess.class);

        // Astrapi/Vronti/Kheiron:
        addIfMissingSafely(Astrapi.class);
        addIfMissingSafely(Vronti.class);
        addIfMissingSafely(Kheiron.class);

        // Minotallys/Arion
        addIfMissingSafely(Arion.class);
        addIfMissingSafely(Minotallys.class);

        if (TIME_TESTING)
            PreInitializationThread.logTime("initElisNPCs took", System.nanoTime() - timeStarted);
    }

    public static void initFieldsNPCs() {
        long timeStarted = System.nanoTime();
        // Evelyx's Dairy:
        addIfMissingSafely(Evelyx.class);
        addIfMissingSafely(Dale.class);

        // Themiscyra:
        addIfMissingSafely(Lunexis.class);
        addIfMissingSafely(Ursa.class);
        addIfMissingSafely(Aurokaris.class);

        // The Crossed Blades:
        addIfMissingSafely(Oglix.class);
        addIfMissingSafely(Golix.class, Gender.F_P_B_SHEMALE, Main.game.getNpc(Oglix.class));
        addIfMissingSafely(Wynter.class);

        if (TIME_TESTING)
            PreInitializationThread.logTime("initFieldsNPCs took", System.nanoTime() - timeStarted);
    }


    /**
     * Handles setting up Relationships after registering all the npcs
    * */
    public static void initRelations(Set<String> addedNpcs) {

        if(addedNpcs.contains(Main.game.getUniqueNPCId(Lilaya.class))) {
            Main.game.getNpc(Lilaya.class).setAffection(Main.game.getPlayer(), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
            Main.game.getNpc(Lilaya.class).setAffection(Main.game.getNpc(Rose.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
            Main.game.getNpc(Lilaya.class).addSlave(Main.game.getNpc(Rose.class));

            Main.game.getNpc(Lilaya.class).setAffection(Main.game.getNpc(Lyssieth.class), -60);
            Main.game.getNpc(Lilaya.class).setAffection(Main.game.getNpc(DarkSiren.class), 15);
            Main.game.getNpc(Lilaya.class).setMother(Main.game.getNpc(Lyssieth.class));
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Rose.class))) {
            Main.game.getNpc(Rose.class).setAffection(Main.game.getPlayer(), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
            Main.game.getNpc(Rose.class).setAffection(Main.game.getNpc(Lilaya.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
            Main.game.getNpc(Rose.class).setObedience(ObedienceLevel.POSITIVE_FIVE_SUBSERVIENT.getMedianValue());
            Main.game.getNpc(Rose.class).setAffection(Main.game.getNpc(Lyssieth.class), -40);
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(DarkSiren.class))) {
            Main.game.getNpc(DarkSiren.class).setAffection(Main.game.getNpc(Lyssieth.class), -25);
            Main.game.getNpc(DarkSiren.class).setAffection(Main.game.getNpc(Lilaya.class), 35);
            Main.game.getNpc(DarkSiren.class).setMother(Main.game.getNpc(Lyssieth.class));
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Elizabeth.class))) {
            Main.game.getNpc(Elizabeth.class).setMother(Main.game.getNpc(Lyssieth.class));
            Main.game.getNpc(Elizabeth.class).setAffection(Main.game.getNpc(Lyssieth.class), 100);
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Lyssieth.class))) {
            Main.game.getNpc(Lyssieth.class).setAffection(Main.game.getNpc(Lilaya.class), 100);
            Main.game.getNpc(Lyssieth.class).setAffection(Main.game.getNpc(DarkSiren.class), 50);
            Main.game.getNpc(Lyssieth.class).setAffection(Main.game.getNpc(Elizabeth.class), 75);
            Main.game.getNpc(Lyssieth.class).setAffection(Main.game.getNpc(Rose.class), -80);
        }

        if(addedNpcs.contains(Main.game.getUniqueNPCId(Brax.class))) {
            Main.game.getNpc(Brax.class).setAffection(Main.game.getNpc(CandiReceptionist.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(CandiReceptionist.class))) {
            Main.game.getNpc(CandiReceptionist.class).setAffection(Main.game.getNpc(Brax.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
        }

        if(addedNpcs.contains(Main.game.getUniqueNPCId(Wes.class))) {
            Main.game.getNpc(Wes.class).setAffection(Main.game.getNpc(Elle.class), AffectionLevel.NEGATIVE_THREE_STRONG_DISLIKE.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Elle.class))) {
            Main.game.getNpc(Elle.class).setAffection(Main.game.getNpc(Wes.class), AffectionLevel.NEGATIVE_ONE_ANNOYED.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Pix.class)) || addedNpcs.contains(Main.game.getUniqueNPCId(Hannah.class))) {
            Main.game.getNpc(Pix.class).setAffection(Main.game.getNpc(Hannah.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
            Main.game.getNpc(Hannah.class).setAffection(Main.game.getNpc(Pix.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
        }

        if(addedNpcs.contains(Main.game.getUniqueNPCId(Nyan.class)) || addedNpcs.contains(Main.game.getUniqueNPCId(NyanMum.class))) {
            Main.game.getNpc(Nyan.class).setAffection(Main.game.getNpc(NyanMum.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
            Main.game.getNpc(NyanMum.class).setAffection(Main.game.getNpc(Nyan.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());

            Main.game.getNpc(Nyan.class).setAffection(Main.game.getNpc(Ashley.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
            Main.game.getNpc(Ashley.class).setAffection(Main.game.getNpc(Nyan.class), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(SupplierLeader.class))) {
            Main.game.getNpc(SupplierLeader.class).setAffection(Main.game.getNpc(SupplierPartner.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
            Main.game.getNpc(SupplierPartner.class).setAffection(Main.game.getNpc(SupplierLeader.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
        }

        if(addedNpcs.contains(Main.game.getUniqueNPCId(Kay.class))) {
            Main.game.getNpc(Nyan.class).setAffection(Main.game.getNpc(Kay.class), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
            Main.game.getNpc(Kay.class).setAffection(Main.game.getNpc(Nyan.class), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Scarlett.class))) {
            Main.game.getNpc(Scarlett.class).setAffection(Main.game.getPlayer(), AffectionLevel.NEGATIVE_TWO_DISLIKE.getMedianValue());
            Main.game.getNpc(Scarlett.class).setAffection(Main.game.getNpc(Helena.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
            if(Main.game.getPlayer().hasQuest(QuestLine.MAIN) && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE)) {
                Main.game.getNpc(Scarlett.class).setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST);
            }
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Helena.class))) {
            Main.game.getNpc(Helena.class).setAffection(Main.game.getNpc(Scarlett.class), AffectionLevel.NEGATIVE_FOUR_HATE.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(HarpyBimbo.class))) {
            Main.game.getNpc(HarpyBimbo.class).setAffection(Main.game.getNpc(HarpyBimboCompanion.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(HarpyBimboCompanion.class))) {
            Main.game.getNpc(HarpyBimboCompanion.class).setAffection(Main.game.getNpc(HarpyBimbo.class), AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(HarpyDominant.class))) {
            Main.game.getNpc(HarpyDominant.class).setAffection(Main.game.getNpc(HarpyDominantCompanion.class), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(HarpyDominantCompanion.class))) {
            Main.game.getNpc(HarpyDominantCompanion.class).setAffection(Main.game.getNpc(HarpyDominant.class), AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(HarpyNympho.class))) {
            Main.game.getNpc(HarpyNympho.class).setAffection(Main.game.getNpc(HarpyNymphoCompanion.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(HarpyNymphoCompanion.class))) {
            Main.game.getNpc(HarpyNymphoCompanion.class).setAffection(Main.game.getNpc(HarpyNympho.class), AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Sean.class))) {
            Main.game.getNpc(Brax.class).setPetName(Main.game.getNpc(Sean.class), Main.game.getNpc(Sean.class).getName(false));
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Daddy.class))) {
            Main.game.getNpc(Rose.class).setAffection(Main.game.getNpc(Daddy.class), -50);
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Felicia.class))) {
            Main.game.getNpc(Felicia.class).setAffection(Main.game.getNpc(Arthur.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Fiammetta.class))) {
            Main.game.getNpc(Fiammetta.class).setAffection(Main.game.getNpc(Saellatrix.class), -100);
            Main.game.getNpc(Saellatrix.class).setAffection(Main.game.getNpc(Fiammetta.class), -50);
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Zaranix.class))) {
            Main.game.getNpc(Zaranix.class).setAffection(Main.game.getNpc(ZaranixMaidKatherine.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
            Main.game.getNpc(Zaranix.class).setAffection(Main.game.getNpc(ZaranixMaidKelly.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
            Main.game.getNpc(Zaranix.class).setAffection(Main.game.getNpc(Amber.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Amber.class))) {
            Main.game.getNpc(Amber.class).setAffection(Main.game.getNpc(Zaranix.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
            Main.game.getNpc(Amber.class).setAffection(Main.game.getNpc(ZaranixMaidKelly.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
            Main.game.getNpc(Amber.class).setAffection(Main.game.getNpc(ZaranixMaidKatherine.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(ZaranixMaidKatherine.class))) {
            Main.game.getNpc(ZaranixMaidKatherine.class).setAffection(Main.game.getNpc(Zaranix.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
            Main.game.getNpc(ZaranixMaidKatherine.class).setAffection(Main.game.getNpc(ZaranixMaidKelly.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
            Main.game.getNpc(ZaranixMaidKatherine.class).setAffection(Main.game.getNpc(Amber.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(ZaranixMaidKelly.class))) {
            Main.game.getNpc(ZaranixMaidKelly.class).setAffection(Main.game.getNpc(Zaranix.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
            Main.game.getNpc(ZaranixMaidKelly.class).setAffection(Main.game.getNpc(ZaranixMaidKatherine.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
            Main.game.getNpc(ZaranixMaidKelly.class).setAffection(Main.game.getNpc(Amber.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Vengar.class))) {
            Main.game.getNpc(Vengar.class).setAffection(Main.game.getNpc(Shadow.class), 50);
            Main.game.getNpc(Vengar.class).setAffection(Main.game.getNpc(Silence.class), 50);
            Main.game.getNpc(Shadow.class).setAffection(Main.game.getNpc(Vengar.class), -10);
            Main.game.getNpc(Shadow.class).setAffection(Main.game.getNpc(Silence.class), 80);
            Main.game.getNpc(Silence.class).setAffection(Main.game.getNpc(Vengar.class), 20);
            Main.game.getNpc(Silence.class).setAffection(Main.game.getNpc(Shadow.class), 100);
        }
        if(Main.isVersionOlderThan(loadingVersion, "0.3.5.6")) {
            Main.game.getNpc(Roxy.class).setAffection(Main.game.getNpc(Vengar.class), -80);
            Main.game.getNpc(Vengar.class).setAffection(Main.game.getNpc(Roxy.class), 50);
        }
        if(Main.isVersionOlderThan(loadingVersion, "0.3.5.9")) {
            Main.game.getNpc(Silence.class).setAffection(Main.game.getNpc(Shadow.class), 100);
            Main.game.getNpc(Silence.class).getAffectionMap().remove(Main.game.getNpc(Silence.class).getId());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Flash.class)) && addedNpcs.contains(Main.game.getUniqueNPCId(Jess.class))) {
            Main.game.getNpc(Jess.class).setAffection(Main.game.getNpc(Flash.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
            Main.game.getNpc(Flash.class).setAffection(Main.game.getNpc(Jess.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Astrapi.class)) || addedNpcs.contains(Main.game.getUniqueNPCId(Vronti.class)) || addedNpcs.contains(Main.game.getUniqueNPCId(Kheiron.class))) {
            Main.game.getNpc(Astrapi.class).setAffection(Main.game.getNpc(Vronti.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
            Main.game.getNpc(Astrapi.class).setAffection(Main.game.getNpc(Kheiron.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());

            Main.game.getNpc(Vronti.class).setAffection(Main.game.getNpc(Astrapi.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
            Main.game.getNpc(Vronti.class).setAffection(Main.game.getNpc(Kheiron.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());

            Main.game.getNpc(Kheiron.class).setAffection(Main.game.getNpc(Astrapi.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
            Main.game.getNpc(Kheiron.class).setAffection(Main.game.getNpc(Vronti.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());

            Main.game.getNpc(Astrapi.class).setFather(Main.game.getNpc(Kheiron.class));
            Main.game.getNpc(Vronti.class).setFather(Main.game.getNpc(Kheiron.class));
        }

        if(addedNpcs.contains(Main.game.getUniqueNPCId(Arion.class)) || addedNpcs.contains(Main.game.getUniqueNPCId(Minotallys.class))) {
            Main.game.getNpc(Arion.class).setAffection(Main.game.getNpc(Minotallys.class), AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
            Main.game.getNpc(Minotallys.class).setAffection(Main.game.getNpc(Arion.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Fae.class)) || addedNpcs.contains(Main.game.getUniqueNPCId(Silvia.class))) {
            Main.game.getNpc(Silvia.class).setAffection(Main.game.getNpc(Fae.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
            Main.game.getNpc(Fae.class).setAffection(Main.game.getNpc(Silvia.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Imsu.class)) || addedNpcs.contains(Main.game.getUniqueNPCId(Hale.class))) {
            Main.game.getNpc(Imsu.class).setAffection(Main.game.getNpc(Hale.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
            Main.game.getNpc(Hale.class).setAffection(Main.game.getNpc(Imsu.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Penelope.class)) || addedNpcs.contains(Main.game.getUniqueNPCId(Pix.class)) || Main.isVersionOlderThan(loadingVersion, "0.4.2.7")) {
            Main.game.getNpc(Penelope.class).setAffection(Main.game.getNpc(Pix.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
            Main.game.getNpc(Pix.class).setAffection(Main.game.getNpc(Penelope.class), AffectionLevel.NEGATIVE_ONE_ANNOYED.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Oglix.class))) {
            Main.game.getNpc(Oglix.class).setAffection(Main.game.getNpc(Kheiron.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
            Main.game.getNpc(Kheiron.class).setAffection(Main.game.getNpc(Oglix.class), AffectionLevel.NEGATIVE_THREE_STRONG_DISLIKE.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Golix.class))) {
            ((Oglix)Main.game.getNpc(Oglix.class)).createElemental(); // inits the summoner ID
            Main.game.getNpc(Kheiron.class).setAffection(Main.game.getNpc(Golix.class), AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
            Main.game.getNpc(Golix.class).setAffection(Main.game.getNpc(Kheiron.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Hammer.class))) {
            Main.game.getNpc(Hammer.class).setAffection(Main.game.getNpc(Ghost.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
            Main.game.getNpc(Ghost.class).setAffection(Main.game.getNpc(Hammer.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Kalahari.class))) {
            Main.game.getNpc(Kalahari.class).setFather(Main.game.getNpc(Kruger.class));
            Main.game.getNpc(Kalahari.class).setAffection(Main.game.getNpc(Kruger.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Kruger.class))) {
            Main.game.getNpc(Kruger.class).setAffection(Main.game.getNpc(Kalahari.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
        }

        if(addedNpcs.contains(Main.game.getUniqueNPCId(Evelyx.class)) || addedNpcs.contains(Main.game.getUniqueNPCId(Dale.class))) {
            Main.game.getNpc(Evelyx.class).setAffection(Main.game.getNpc(Dale.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
            Main.game.getNpc(Dale.class).setAffection(Main.game.getNpc(Evelyx.class), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
        }

        if(addedNpcs.contains(Main.game.getUniqueNPCId(Angelixx.class)) || addedNpcs.contains(Main.game.getUniqueNPCId(Saellatrix.class))) {
            Main.game.getNpc(Angelixx.class).setAffection(Main.game.getNpc(Sleip.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
            Main.game.getNpc(Angelixx.class).setAffection(Main.game.getNpc(Nir.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());

            Main.game.getNpc(Saellatrix.class).setAffection(Main.game.getNpc(Angelixx.class), AffectionLevel.NEGATIVE_THREE_STRONG_DISLIKE.getMedianValue());
            Main.game.getNpc(Angelixx.class).setAffection(Main.game.getNpc(Saellatrix.class), AffectionLevel.NEGATIVE_TWO_DISLIKE.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Saellatrix.class)) || Main.isVersionOlderThan(loadingVersion, "0.4.9.12")) {
            Main.game.getNpc(Saellatrix.class).setAffection(Main.game.getNpc(Lilaya.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
            Main.game.getNpc(Lilaya.class).setAffection(Main.game.getNpc(Saellatrix.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Sleip.class))) {
            Main.game.getNpc(Sleip.class).setMother(Main.game.getNpc(Angelixx.class));
            Main.game.getNpc(Sleip.class).setAffection(Main.game.getNpc(Angelixx.class), AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
            Main.game.getNpc(Sleip.class).setAffection(Main.game.getNpc(Nir.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Nir.class))) {
            Main.game.getNpc(Nir.class).setMother(Main.game.getNpc(Angelixx.class));
            Main.game.getNpc(Nir.class).setAffection(Main.game.getNpc(Angelixx.class), AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
            Main.game.getNpc(Nir.class).setAffection(Main.game.getNpc(Sleip.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Lunette.class))) {
            Main.game.getNpc(Lunexis.class).setMother(Main.game.getNpc(Lunette.class));
            Main.game.getNpc(Lunexis.class).setAffection(Main.game.getNpc(Lunette.class), 100);
        }

        if(addedNpcs.contains(Main.game.getUniqueNPCId(Lovienne.class))) {
            Main.game.getNpc(Angelixx.class).setMother(Main.game.getNpc(Lovienne.class));
            Main.game.getNpc(Angelixx.class).setAffection(Main.game.getNpc(Lovienne.class), 100);
            Main.game.getNpc(Saellatrix.class).setMother(Main.game.getNpc(Lovienne.class));
            Main.game.getNpc(Saellatrix.class).setAffection(Main.game.getNpc(Lovienne.class), 100);
        }
    }


    /**
     *  if npc isn't in NPCMap it adds it using it's constructor
     * @param npc The class of the NPC to be added
     **/
    public static void addIfMissing(Class<? extends NPC> npc) throws Exception {
        if (!Main.game.getNPCMap().containsKey(Main.game.getUniqueNPCId(npc))) {
            Main.game.addNPC(npc.getConstructor().newInstance(), false);
        }
    }

    /**
     *  if npc isn't in NPCMap it adds it using it's constructor
     * @param npc The class of the NPC to be added
     * @param gender The Gender of the NPC
     * @param owner The Instance of the NPC's owner
     **/
    public static void addIfMissing(Class<? extends NPC> npc, Gender gender, NPC owner) throws Exception {
        if (!Main.game.getNPCMap().containsKey(Main.game.getUniqueNPCId(npc))) {
            Main.game.addNPC(npc.getConstructor(Gender.class, NPC.class, boolean.class).newInstance(gender, owner, false), false);
        }
    }

    /**
     * This wraps the function in a try/catch for cleanliness
     * @param npc the npc in question to be added
     * */
    public static void addIfMissingSafely(Class<? extends NPC> npc) {
        long nanoTime = System.nanoTime();
        try {
            addIfMissing(npc);
        } catch (Exception p) {
            System.err.println(p.getMessage());
        }
        if (TIME_TEST_INDIVIDUAL_NPCS) {
            PreInitializationThread.logTime("NPC " + npc.getSimpleName() + " initialized in", System.nanoTime() - nanoTime);
        }
    }
    /**
     * This wraps the function in a try/catch for cleanliness, sub-npc version
     * @param npc the npc in question to be added
     * @param gender gender of sub-npc
     * @param owner owner of sub-npc
     * */
    public static void addIfMissingSafely(Class<? extends NPC> npc, Gender gender, NPC owner) {
        long nanoTime = System.nanoTime();
        try {
            addIfMissing(npc, gender, owner);
        } catch (Exception p) {
            System.err.println(p.getMessage());
        }
        if (TIME_TEST_INDIVIDUAL_NPCS) {
            PreInitializationThread.logTime("NPC " + npc.getSimpleName() + " initialized in", System.nanoTime() - nanoTime);
        }
    }
}