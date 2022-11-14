package com.lilithsthrone.game.character.quests;

import com.lilithsthrone.utils.TreeNode;

/**
 * @since 0.1.1
 * @version 0.4
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

	SIDE_ENCHANTMENT_DISCOVERY("Essences and Enchantments", "You now know how to use essences in order to create and modify enchanted items!",
			QuestType.SIDE,
			QuestTree.enchantmentTree),

	SIDE_FIRST_TIME_PREGNANCY("Knocked Up", "With Lilaya's help, you managed to complete your first pregnancy. Perhaps the first of many...",
			QuestType.SIDE,
			QuestTree.pregnancyTree),

	SIDE_FIRST_TIME_INCUBATION("Egged", "You successfully incubated, laid, and hatched the eggs which were planted inside of you!",
			QuestType.SIDE,
			QuestTree.incubationTree),

	SIDE_SLAVERY("Slaver", "Thanks to Lilaya's letter of recommendation, you managed to obtain a coveted slaver license!",
			QuestType.SIDE,
			QuestTree.slaveryTree),

	SIDE_ACCOMMODATION("Bed & Board", "Lilaya happily gave you her permission to use the spare rooms to accommodate your friends and family, provided that you pay for the expenses that they incur...",
			QuestType.SIDE,
			QuestTree.accommodationTree),

	SIDE_HYPNO_WATCH("Arthur's Experiment", "You helped Arthur to complete his research into an orientation-changing Hypno-Watch, which is now in your possession!",
			QuestType.SIDE,
			QuestTree.hypnoWatchTree),

	SIDE_ARCANE_LIGHTNING("Arcane Lightning", "Arthur was able to extract the secrets of arcane lightning from the globe which you gave to him, allowing you to learn two incredibly powerful spells.",
			QuestType.SIDE,
			QuestTree.arcaneLightningTree),
	
	SIDE_HARPY_PACIFICATION("Angry Harpies", "You managed to calm down all three of the harpy matriarchs, resulting in the Harpy Nests being safe to travel through!",
			QuestType.SIDE,
			QuestTree.angryHarpyTree),

	SIDE_SLIME_QUEEN("Slime Queen", "You dealt with the Slime Queen!",
			QuestType.SIDE,
			QuestTree.slimeQueenTree),

	SIDE_TELEPORTATION("The Trouble with Teleporting", "After learning how to teleport, you managed to escape from the Enforcer warehouse.",
			QuestType.SIDE,
			QuestTree.teleportingTree),

	SIDE_DADDY("An Inquiring Incubus", "You dealt with the demon, [daddy.name], who was showing an interest in Lilaya.",
			QuestType.SIDE,
			QuestTree.daddyTree),

	SIDE_BUYING_BRAX("Acquiring a Wolf", "After she'd got you to perform a series of tedious tasks for her, Candi finally sold [brax.name] to you.",
			QuestType.SIDE,
			QuestTree.buyingBraxTree),

	SIDE_VENGAR("Vengar's Tyranny", "You dealt with Vengar and made sure that Axel doesn't have to worry about him again.",
			QuestType.SIDE,
			QuestTree.vengarTree),


	SIDE_WES("The Rogue Enforcer", "You were able to successfully deal with the Enforcer Quartermaster.",
			QuestType.SIDE,
			QuestTree.wesTree),
        
    SIDE_REBEL_BASE("Grave Robbing", "You managed to escape the abandoned rebel hideout.",
            QuestType.SIDE,
            QuestTree.rebelBaseTree),
    
    SIDE_REBEL_BASE_FIREBOMBS("Spicy Meatballs", "You've gotten yourself a steady supply of Arcane Firebombs. At the usual premium, of course.",
            QuestType.SIDE,
            QuestTree.rebelBaseFirebombTree),

	SIDE_OGLIX_BEER_BARRELS("Beer Bitch Bonanza", "You secured more barrels for Oglix, allowing you to send four criminals from the nearby alleyways to her to become new beer-bitches!",
			QuestType.SIDE,
			QuestTree.beerBarrelTree),

	SIDE_LUNEXIS_ESCAPE("Serving Lunexis", "You obeyed your Mistress's orders and ensured that she was able to escape, thereby sealing your fate to become one of her personal cock-sleeves...",
			QuestType.SIDE,
			QuestTree.lunexisEscapeTree),
	
	// Romance quests:
	
	RELATIONSHIP_NYAN_HELP("Supplier Issues", "You helped Nyan solve the problem she was having with her suppliers.",
			QuestType.RELATIONSHIP,
			QuestTree.nyanTree),

	ROMANCE_HELENA("Her Highness's Helper", "You successfully completed every task which Helena gave to you, and as a reward, you can both order custom slaves from her and take her on a date each Friday evening.",
			QuestType.RELATIONSHIP,
			QuestTree.helenaTree),

	ROMANCE_NATALYA("Filly Training", "Having completed Mistress Natalya's training, you are now a qualified filly and are expected to sexually service Dominion Express's centaur slaves.",
			QuestType.RELATIONSHIP,
			QuestTree.natalyaTree),

	ROMANCE_MONICA("Monica's Milker", "You successfully retrieved Monica's personalised Moo Milker, and as a result she is very grateful to you.",
			QuestType.RELATIONSHIP,
			QuestTree.monicaTree),
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
