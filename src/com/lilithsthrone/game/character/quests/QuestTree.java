package com.lilithsthrone.game.character.quests;

import com.lilithsthrone.utils.TreeNode;

/**
 * @since 0.1.99
 * @version 0.2.10
 * @author Innoxia
 */
public class QuestTree {

	public static TreeNode<Quest> mainQuestTree = new TreeNode<Quest>(Quest.MAIN_PROLOGUE);
	public static TreeNode<Quest> enchantmentTree = new TreeNode<Quest>(Quest.SIDE_ENCHANTMENTS_LILAYA_HELP);
	public static TreeNode<Quest> pregnancyTree = new TreeNode<Quest>(Quest.SIDE_PREGNANCY_CONSULT_LILAYA);
	public static TreeNode<Quest> slaveryTree = new TreeNode<Quest>(Quest.SIDE_SLAVER_NEED_RECOMMENDATION);
	public static TreeNode<Quest> accommodationTree = new TreeNode<Quest>(Quest.SIDE_ACCOMMODATION_NEED_LILAYAS_PERMISSION);
	public static TreeNode<Quest> hypnoWatchTree = new TreeNode<Quest>(Quest.SIDE_HYPNO_WATCH_VICKY);
	public static TreeNode<Quest> nyanTree = new TreeNode<Quest>(Quest.RELATIONSHIP_NYAN_STOCK_ISSUES);
	public static TreeNode<Quest> angryHarpyTree = new TreeNode<Quest>(Quest.HARPY_PACIFICATION_ONE);
	public static TreeNode<Quest> slimeQueenTree = new TreeNode<Quest>(Quest.SLIME_QUEEN_ONE);
	
	static {
		TreeNode<Quest> node1 = new TreeNode<Quest>(Quest.MAIN_1_A_LILAYAS_TESTS);
		mainQuestTree.addChild(node1);
		TreeNode<Quest> node2 = new TreeNode<Quest>(Quest.MAIN_1_B_DEMON_HOME);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.MAIN_1_C_WOLFS_DEN);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.MAIN_1_D_SLAVERY);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.MAIN_1_E_REPORT_TO_ALEXA);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.MAIN_1_F_SCARLETTS_FATE);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.MAIN_1_G_SLAVERY);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.MAIN_1_H_THE_GREAT_ESCAPE);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.MAIN_1_I_ARTHURS_TALE);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.MAIN_1_J_ARTHURS_ROOM);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.MAIN_2_A_INTO_THE_DEPTHS);
		node2.addChild(node1);

		enchantmentTree.addChild(new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE));

		node1 = new TreeNode<Quest>(Quest.SIDE_PREGNANCY_LILAYA_THE_MIDWIFE);
		pregnancyTree.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);

		accommodationTree.addChild(new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE));
		
		node1 = new TreeNode<Quest>(Quest.SIDE_SLAVER_RECOMMENDATION_OBTAINED);
		slaveryTree.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);
		
		node1 = new TreeNode<Quest>(Quest.SIDE_HYPNO_WATCH_TEST_SUBJECT);
		hypnoWatchTree.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);
		
		node1 = new TreeNode<Quest>(Quest.RELATIONSHIP_NYAN_STOCK_ISSUES_AGREED_TO_HELP);
		nyanTree.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.RELATIONSHIP_NYAN_STOCK_ISSUES_SUPPLIERS_BEATEN);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		node2.addChild(node1);
		
		node1 = new TreeNode<Quest>(Quest.HARPY_PACIFICATION_TWO);
		angryHarpyTree.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.HARPY_PACIFICATION_THREE);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.HARPY_PACIFICATION_REWARD);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);
		

		node1 = new TreeNode<Quest>(Quest.SLIME_QUEEN_TWO);
		slimeQueenTree.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.SLIME_QUEEN_THREE);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.SLIME_QUEEN_FOUR);
		node2.addChild(node1);
		
		
		TreeNode<Quest> nodeBranchA = new TreeNode<Quest>(Quest.SLIME_QUEEN_FIVE_FORCE);
		node1.addChild(nodeBranchA);
		TreeNode<Quest> nodeBranchB = new TreeNode<Quest>(Quest.SLIME_QUEEN_FIVE_CONVINCE);
		node1.addChild(nodeBranchB);
		TreeNode<Quest> nodeBranchC = new TreeNode<Quest>(Quest.SLIME_QUEEN_FIVE_SUBMIT);
		node1.addChild(nodeBranchC);

		node2 = new TreeNode<Quest>(Quest.SLIME_QUEEN_SIX_FORCE);
		nodeBranchA.addChild(node2);
		node2.addChild(new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE));
		
		node2 = new TreeNode<Quest>(Quest.SLIME_QUEEN_SIX_CONVINCE);
		nodeBranchB.addChild(node2);
		node2.addChild(new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE));
		
		node2 = new TreeNode<Quest>(Quest.SLIME_QUEEN_SIX_SUBMIT);
		nodeBranchC.addChild(node2);
		node2.addChild(new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE));
	}
}
