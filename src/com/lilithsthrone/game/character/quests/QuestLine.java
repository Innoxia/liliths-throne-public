package com.lilithsthrone.game.character.quests;

import com.lilithsthrone.utils.Util;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestLine {

    // Main quests:

    public static AbstractQuestLine MAIN = new AbstractQuestLine("Lilith's Throne", "You have completed all main quest content in this version!",
            QuestType.MAIN,
            QuestTree.mainQuestTree);

    // Side quests:

//	public static QuestLine SIDE_ITEM_DISCOVERY = new QuestLine("Item Discovery", "You have found all the different items that are in this version!",
//			QuestType.SIDE,
//			Quest.SIDE_DISCOVER_ALL_ITEMS);
//
//	public static QuestLine SIDE_RACE_DISCOVERY = new QuestLine("Race Discovery", "You have found all the different races that are in this version!",
//			QuestType.SIDE,
//			Quest.SIDE_DISCOVER_ALL_RACES);

    public static AbstractQuestLine SIDE_ENCHANTMENT_DISCOVERY = new AbstractQuestLine("Essences and Enchantments", "You now know how to use essences in order to create and modify enchanted items!",
            QuestType.SIDE,
            QuestTree.enchantmentTree);

    public static AbstractQuestLine SIDE_FIRST_TIME_PREGNANCY = new AbstractQuestLine("Knocked Up", "With Lilaya's help, you managed to complete your first pregnancy. Perhaps the first of many...",
            QuestType.SIDE,
            QuestTree.pregnancyTree);

    public static AbstractQuestLine SIDE_FIRST_TIME_INCUBATION = new AbstractQuestLine("Egged", "You successfully incubated, laid, and hatched the eggs which were planted inside of you!",
            QuestType.SIDE,
            QuestTree.incubationTree);

    public static AbstractQuestLine SIDE_SLAVERY = new AbstractQuestLine("Slaver", "Thanks to Lilaya's letter of recommendation, you managed to obtain a coveted slaver license!",
            QuestType.SIDE,
            QuestTree.slaveryTree);

    public static AbstractQuestLine SIDE_ACCOMMODATION = new AbstractQuestLine("Bed & Board", "Lilaya happily gave you her permission to use the spare rooms to accommodate your friends and family, provided that you pay for the expenses that they incur...",
            QuestType.SIDE,
            QuestTree.accommodationTree);

    public static AbstractQuestLine SIDE_HYPNO_WATCH = new AbstractQuestLine("Arthur's Experiment", "You helped Arthur to complete his research into an orientation-changing Hypno-Watch, which is now in your possession!",
            QuestType.SIDE,
            QuestTree.hypnoWatchTree);

    public static AbstractQuestLine SIDE_ARCANE_LIGHTNING = new AbstractQuestLine("Arcane Lightning", "Arthur was able to extract the secrets of arcane lightning from the globe which you gave to him, allowing you to learn two incredibly powerful spells.",
            QuestType.SIDE,
            QuestTree.arcaneLightningTree);

    public static AbstractQuestLine SIDE_HARPY_PACIFICATION = new AbstractQuestLine("Angry Harpies", "You managed to calm down all three of the harpy matriarchs, resulting in the Harpy Nests being safe to travel through!",
            QuestType.SIDE,
            QuestTree.angryHarpyTree);

    public static AbstractQuestLine SIDE_SLIME_QUEEN = new AbstractQuestLine("Slime Queen", "You dealt with the Slime Queen!",
            QuestType.SIDE,
            QuestTree.slimeQueenTree);

    public static AbstractQuestLine SIDE_TELEPORTATION = new AbstractQuestLine("The Trouble with Teleporting", "After learning how to teleport, you managed to escape from the Enforcer warehouse.",
            QuestType.SIDE,
            QuestTree.teleportingTree);

    public static AbstractQuestLine SIDE_DADDY = new AbstractQuestLine("An Inquiring Incubus", "You dealt with the demon, [daddy.name], who was showing an interest in Lilaya.",
            QuestType.SIDE,
            QuestTree.daddyTree);

    public static AbstractQuestLine SIDE_BUYING_BRAX = new AbstractQuestLine("Acquiring a Wolf", "After she'd got you to perform a series of tedious tasks for her, Candi finally sold [brax.name] to you.",
            QuestType.SIDE,
            QuestTree.buyingBraxTree);

    public static AbstractQuestLine SIDE_VENGAR = new AbstractQuestLine("Vengar's Tyranny", "You dealt with Vengar and made sure that Axel doesn't have to worry about him again.",
            QuestType.SIDE,
            QuestTree.vengarTree);


    public static AbstractQuestLine SIDE_WES = new AbstractQuestLine("The Rogue Enforcer", "You were able to successfully deal with the Enforcer Quartermaster.",
            QuestType.SIDE,
            QuestTree.wesTree);

    public static AbstractQuestLine SIDE_REBEL_BASE = new AbstractQuestLine("Grave Robbing", "You managed to escape the abandoned rebel hideout.",
            QuestType.SIDE,
            QuestTree.rebelBaseTree);

    public static AbstractQuestLine SIDE_REBEL_BASE_FIREBOMBS = new AbstractQuestLine("Spicy Meatballs", "You've gotten yourself a steady supply of Arcane Firebombs. At the usual premium, of course.",
            QuestType.SIDE,
            QuestTree.rebelBaseFirebombTree);

    public static AbstractQuestLine SIDE_EISEK_STALL = new AbstractQuestLine("Fix 'Er Upper", "You've helped Eisek fix up his stall.",
            QuestType.SIDE,
            QuestTree.eisekStallTree);

    public static AbstractQuestLine SIDE_EISEK_MOB = new AbstractQuestLine("Mob Mentality", "You've convinced the mob that was hounding Eisek to leave him alone.",
            QuestType.SIDE,
            QuestTree.eisekMobTree);

    public static AbstractQuestLine SIDE_EISEK_SILLYMODE = new AbstractQuestLine("Dragon Enthusiasts", "You encountered a strange group of dragon obssessed weirdos and cleared their dungeon.",
            QuestType.SIDE,
            QuestTree.eisekSillyModeTree);

    public static AbstractQuestLine SIDE_OGLIX_BEER_BARRELS = new AbstractQuestLine("Beer Bitch Bonanza", "You secured more barrels for Oglix, allowing you to send four criminals from the nearby alleyways to her to become new beer-bitches!",
            QuestType.SIDE,
            QuestTree.beerBarrelTree);

    public static AbstractQuestLine SIDE_LUNEXIS_ESCAPE = new AbstractQuestLine("Serving Lunexis", "You obeyed your Mistress's orders and ensured that she was able to escape, thereby sealing your fate to become one of her personal cock-sleeves...",
            QuestType.SIDE,
            QuestTree.lunexisEscapeTree);

    public static AbstractQuestLine SIDE_DOLL_FACTORY = new AbstractQuestLine("Dealing With Dolls", "You uncovered the truth behind how the premium sex dolls for sale in 'Lovienne's Luxury' are created...",
            QuestType.SIDE,
            QuestTree.dollFactoryTree);

    // Romance quests:

    public static AbstractQuestLine RELATIONSHIP_NYAN_HELP = new AbstractQuestLine("Supplier Issues", "You helped Nyan solve the problem she was having with her suppliers.",
            QuestType.RELATIONSHIP,
            QuestTree.nyanTree);

    public static AbstractQuestLine ROMANCE_HELENA = new AbstractQuestLine("Her Highness's Helper", "You successfully completed every task which Helena gave to you, and as a reward, you can both order custom slaves from her and take her on a date each Friday evening.",
            QuestType.RELATIONSHIP,
            QuestTree.helenaTree);

    public static AbstractQuestLine ROMANCE_NATALYA = new AbstractQuestLine("Filly Training", "Having completed Mistress Natalya's training, you are now a qualified filly and are expected to sexually service Dominion Express's centaur slaves.",
            QuestType.RELATIONSHIP,
            QuestTree.natalyaTree);

    public static AbstractQuestLine ROMANCE_MONICA = new AbstractQuestLine("Monica's Milker", "You successfully retrieved Monica's personalised Moo Milker, and as a result she is very grateful to you.",
            QuestType.RELATIONSHIP,
            QuestTree.monicaTree);


    private static List<AbstractQuestLine> allQuestLines = new ArrayList<>();
    private static Map<AbstractQuestLine, String> questLineToIdMap = new HashMap<>();
    private static Map<String, AbstractQuestLine> idToQuestLineMap = new HashMap<>();

    public static List<AbstractQuestLine> getAllQuestLine() {
        return allQuestLines;
    }

    public static AbstractQuestLine getQuestLineFromId(String id) {
        id = id.replace("_questLine", "");
        id = Util.getClosestStringMatch(id, idToQuestLineMap.keySet());
        return idToQuestLineMap.get(id);
    }

    public static String getIdFromQuestLine(AbstractQuestLine placeType) {
        return questLineToIdMap.get(placeType);
    }

    static {
        // Modded quest lines and quest trees:
        Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/quests", "questLines", null);
        for (Map.Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
            for (Map.Entry<String, File> innerEntry : entry.getValue().entrySet())
            {
                try {
                    String id = innerEntry.getKey().replace("_questLines", "");
                    AbstractQuestLine questLine = new AbstractQuestLine(innerEntry.getValue(), entry.getKey(), id, true) {};
                    questLineToIdMap.put(questLine, id);
                    idToQuestLineMap.put(id, questLine);
                    allQuestLines.add(questLine);
                } catch(Exception ex) {
                    System.err.println("Loading modded quest line failed at 'QuestLine' for mod: "+innerEntry.getValue().getAbsolutePath());
                    System.err.println("Actual exception: ");
                    ex.printStackTrace(System.err);
                }
            }
        }

        // External res quest lines:
        Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/quests", "questLines", null);
        for (Map.Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
            for (Map.Entry<String, File> innerEntry : entry.getValue().entrySet())
            {
                try {
                    String id = innerEntry.getKey().replace("_questLines", "");
                    AbstractQuestLine questLine = new AbstractQuestLine(innerEntry.getValue(), entry.getKey(), id, false) {};
                    questLineToIdMap.put(questLine, id);
                    idToQuestLineMap.put(id, questLine);
                    allQuestLines.add(questLine);
                } catch(Exception ex) {
                    System.err.println("Loading quest line failed at 'QuestLine'. File path: "+innerEntry.getValue().getAbsolutePath());
                    System.err.println("Actual exception: ");
                    ex.printStackTrace(System.err);
                }
            }
        }

        // Hard-coded questLines above
        Field[] fields = QuestLine.class.getFields();
        for(Field f : fields){
            if(AbstractQuestLine.class.isAssignableFrom(f.getType())) {
                try {
                    AbstractQuestLine questLine = ((AbstractQuestLine) f.get(null));

                    String id = f.getName();
                    questLineToIdMap.put(questLine, id);
                    idToQuestLineMap.put(id, questLine);
                    allQuestLines.add(questLine);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
