package com.base.game.character;

/**
 * @since 0.1.1
 * @version 0.1.83
 * @author Innoxia
 */
public enum QuestLine {

	// Main quests:

	MAIN("Lilith's Throne", "You have completed all main quest content in this version!", QuestType.MAIN,
			Quest.MAIN_PROLOGUE,
			Quest.MAIN_1_A_LILAYAS_TESTS,
			Quest.MAIN_1_B_DEMON_HOME,
			Quest.MAIN_1_C_WOLFS_DEN,
			Quest.MAIN_1_D_SLAVERY,
			Quest.MAIN_1_E_REPORT_TO_ALEXA,
			Quest.MAIN_1_F_SCARLETTS_FATE,
			Quest.MAIN_1_G_SLAVERY,
			Quest.MAIN_1_H_THE_GREAT_ESCAPE),

	// Side quests:

	SIDE_ITEM_DISCOVERY("Item Discovery", "You have found all the different items that are in this version!", QuestType.SIDE, Quest.SIDE_DISCOVER_ALL_ITEMS),

	SIDE_RACE_DISCOVERY("Race Discovery", "You have found all the different races that are in this version!", QuestType.SIDE, Quest.SIDE_DISCOVER_ALL_RACES),

	SIDE_JINXED_CLOTHING("Getting jinxed", "Lilaya told you how to use demonstones to remove jinxed clothing.", QuestType.SIDE, Quest.SIDE_JINXED_LILAYA_HELP, Quest.SIDE_JINX_REMOVE_JINX),

	SIDE_ENCHANTMENT_DISCOVERY("Enchantments & Essences", "You now know how to create enchanted items!", QuestType.SIDE, Quest.SIDE_ENCHANTMENTS_LILAYA_HELP, Quest.SIDE_ENCHANTMENTS_ENCHANT_SOMETHING),

	SIDE_FIRST_TIME_PREGNANCY("Knocked up", "With Lilaya's help, you managed to complete your first pregnancy. Perhaps the first of many...", QuestType.SIDE, Quest.SIDE_PREGNANCY_CONSULT_LILAYA, Quest.SIDE_PREGNANCY_LILAYA_THE_MIDWIFE),


	SIDE_HARPY_PACIFICATION("Angry harpies", "You managed to calm down all three of the harpy matriarchs, resulting in the Harpy Nests being safe to travel through!", QuestType.SIDE,
			Quest.HARPY_PACIFICATION_ONE,
			Quest.HARPY_PACIFICATION_TWO,
			Quest.HARPY_PACIFICATION_THREE,
			Quest.HARPY_PACIFICATION_REWARD);

	private String name, completedDescription;
	private QuestType type;
	private Quest[] questArray;

	private QuestLine(String name, String completedDescription, QuestType type, Quest... questArray) {
		this.name = name;
		this.completedDescription = completedDescription;
		this.type = type;
		this.questArray = questArray;
	}

	public String getName() {
		return name;
	}

	public String getCompletedDescription() {
		return completedDescription;
	}

	public QuestType getType() {
		return type;
	}

	public boolean isCompleted(int progress) {
		return progress >= questArray.length;
	}

	public Quest[] getQuestArray() {
		return questArray;
	}

}
