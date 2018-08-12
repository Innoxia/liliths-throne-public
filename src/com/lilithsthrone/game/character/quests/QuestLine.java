package com.lilithsthrone.game.character.quests;

import com.lilithsthrone.utils.TreeNode;

/**
 * @since 0.1.1
 * @version 0.2.10
 * @author Innoxia
 */
public enum QuestLine {

	// Main quests:

	MAIN("Lilith's Throne", "You have completed all main quest content in this version!",
			QuestType.MAIN,
			QuestTree.mainQuestTree),

	// Side quests:

//	SIDE_ITEM_DISCOVERY("Item Discovery", "You have found all the different items that are in this version!",
//			QuestType.SIDE,
//			Quest.SIDE_DISCOVER_ALL_ITEMS),
//
//	SIDE_RACE_DISCOVERY("Race Discovery", "You have found all the different races that are in this version!",
//			QuestType.SIDE,
//			Quest.SIDE_DISCOVER_ALL_RACES),

	SIDE_ENCHANTMENT_DISCOVERY("Enchantments, Essences, and Jinxes", "You now know how to create enchanted items and remove jinxes!",
			QuestType.SIDE,
			QuestTree.enchantmentTree),

	SIDE_FIRST_TIME_PREGNANCY("Knocked up", "With Lilaya's help, you managed to complete your first pregnancy. Perhaps the first of many...",
			QuestType.SIDE,
			QuestTree.pregnancyTree),

	SIDE_SLAVERY("Slaver", "Thanks to Lilaya's letter of recommendation, you managed to obtain a coveted Slaver License!",
			QuestType.SIDE,
			QuestTree.slaveryTree),

	SIDE_ACCOMMODATION("Bed & Board", "Lilaya happily gave you her permission to use the spare rooms to accommodate your friends and family, provided that you pay for the expenses that they incur...",
			QuestType.SIDE,
			QuestTree.accommodationTree),

	SIDE_HYPNO_WATCH("Arthur's Experiment", "You helped Arthur to complete his research into an orientation-changing Hypno-Watch, which is now in your possession!",
			QuestType.SIDE,
			QuestTree.hypnoWatchTree),
	
	RELATIONSHIP_NYAN_HELP("Supplier Issues", "You helped Nyan solve the problem she was having with her suppliers.",
			QuestType.RELATIONSHIP,
			QuestTree.nyanTree),

	SIDE_HARPY_PACIFICATION("Angry Harpies", "You managed to calm down all three of the harpy matriarchs, resulting in the Harpy Nests being safe to travel through!",
			QuestType.SIDE,
			QuestTree.angryHarpyTree),

	SIDE_SLIME_QUEEN("Slime Queen", "You dealt with the Slime Queen!",
			QuestType.SIDE,
			QuestTree.slimeQueenTree)
	
	;

	private String name, completedDescription;
	private QuestType type;
	private TreeNode<Quest> questTree;

	private QuestLine(String name, String completedDescription, QuestType type, TreeNode<Quest> questTree) {
		this.name = name;
		this.completedDescription = completedDescription;
		this.type = type;
		this.questTree = questTree;
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

	public TreeNode<Quest> getQuestTree() {
		return questTree;
	}

}
