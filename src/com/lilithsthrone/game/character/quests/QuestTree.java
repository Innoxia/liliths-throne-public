package com.lilithsthrone.game.character.quests;

import com.lilithsthrone.utils.TreeNode;

/**
 * @since 0.1.99
 * @version 0.4.2
 * @author Innoxia
 */
public class QuestTree {

	public static TreeNode<Quest> mainQuestTree = new TreeNode<Quest>(Quest.MAIN_PROLOGUE);
	
	public static TreeNode<Quest> enchantmentTree = new TreeNode<Quest>(Quest.SIDE_ENCHANTMENTS_LILAYA_HELP);
	public static TreeNode<Quest> pregnancyTree = new TreeNode<Quest>(Quest.SIDE_PREGNANCY_CONSULT_LILAYA);
	public static TreeNode<Quest> incubationTree = new TreeNode<Quest>(Quest.SIDE_INCUBATION_WAITING);
	public static TreeNode<Quest> slaveryTree = new TreeNode<Quest>(Quest.SIDE_SLAVER_NEED_RECOMMENDATION);
	public static TreeNode<Quest> accommodationTree = new TreeNode<Quest>(Quest.SIDE_ACCOMMODATION_NEED_LILAYAS_PERMISSION);
	public static TreeNode<Quest> hypnoWatchTree = new TreeNode<Quest>(Quest.SIDE_HYPNO_WATCH_VICKY);
	public static TreeNode<Quest> arcaneLightningTree = new TreeNode<Quest>(Quest.LIGHTNING_SPELL_1_PAYMENT);
	public static TreeNode<Quest> angryHarpyTree = new TreeNode<Quest>(Quest.HARPY_PACIFICATION_ONE);
	public static TreeNode<Quest> slimeQueenTree = new TreeNode<Quest>(Quest.SLIME_QUEEN_ONE);
	public static TreeNode<Quest> teleportingTree = new TreeNode<Quest>(Quest.TELEPORTING_START);
	public static TreeNode<Quest> daddyTree = new TreeNode<Quest>(Quest.DADDY_START);
	public static TreeNode<Quest> buyingBraxTree = new TreeNode<Quest>(Quest.BUYING_BRAX_START);
	public static TreeNode<Quest> vengarTree = new TreeNode<Quest>(Quest.VENGAR_START);
	public static TreeNode<Quest> wesTree = new TreeNode<Quest>(Quest.WES_START);
	public static TreeNode<Quest> beerBarrelTree = new TreeNode<Quest>(Quest.OGLIX_BEER_BARRELS_1);
	public static TreeNode<Quest> lunexisEscapeTree = new TreeNode<Quest>(Quest.LUNEXIS_ESCAPE);
	
	public static TreeNode<Quest> nyanTree = new TreeNode<Quest>(Quest.RELATIONSHIP_NYAN_1_STOCK_ISSUES);
	public static TreeNode<Quest> helenaTree = new TreeNode<Quest>(Quest.ROMANCE_HELENA_1_OFFER_HELP);
	public static TreeNode<Quest> natalyaTree = new TreeNode<Quest>(Quest.ROMANCE_NATALYA_1_INTERVIEW_START);
	public static TreeNode<Quest> monicaTree = new TreeNode<Quest>(Quest.ROMANCE_MONICA_1_TO_THE_FARM);
	public static TreeNode<Quest> eisekStallTree = new TreeNode<Quest>(Quest.EISEK_STALL_QUEST_STAGE_ONE);
	public static TreeNode<Quest> eisekMobTree = new TreeNode<Quest>(Quest.EISEK_MOB_QUEST_STAGE_ONE);
	public static TreeNode<Quest> eisekSillyModeTree = new TreeNode<Quest>(Quest.EISEK_SILLYMODE_QUEST_STAGE_ONE);
	
	public static TreeNode<Quest> rebelBaseTree = new TreeNode<Quest>(Quest.REBEL_BASE_EXPLORATION);
	public static TreeNode<Quest> rebelBaseFirebombTree = new TreeNode<Quest>(Quest.REBEL_BASE_FIREBOMBS_START);
	
	
	static {
		TreeNode<Quest> node1 = new TreeNode<Quest>(Quest.MAIN_1_A_LILAYAS_TESTS);
		mainQuestTree.addChild(node1);
		TreeNode<Quest> node2 = new TreeNode<Quest>(Quest.MAIN_1_B_DEMON_HOME);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.MAIN_1_C_WOLFS_DEN);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.MAIN_1_D_SLAVERY);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.MAIN_1_E_REPORT_TO_HELENA);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.MAIN_1_F_SCARLETTS_FATE);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.MAIN_1_G_SLAVERY);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.MAIN_1_H_THE_GREAT_ESCAPE);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.MAIN_1_I_ARTHURS_TALE);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.MAIN_2_A_INTO_THE_DEPTHS);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.MAIN_2_B_SIRENS_CALL);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.MAIN_2_C_SIRENS_FALL);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.MAIN_2_D_MEETING_A_LILIN);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.MAIN_3_ELIS);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.MAIN_3_B_MEETING_MERAXIS);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.MAIN_3_C_MEETING_MINOTALLYS);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.MAIN_3_D_TO_THEMISCYRA);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.MAIN_3_E_THEMISCYRA_ATTACK);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.MAIN_3_F_PREPARING_ELIS);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.MAIN_3_G_TODO);
		node1.addChild(node2);

		enchantmentTree.addChild(new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE));

		node1 = new TreeNode<Quest>(Quest.SIDE_PREGNANCY_LILAYA_THE_MIDWIFE);
		pregnancyTree.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);

		node1 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		incubationTree.addChild(node1);
		
		accommodationTree.addChild(new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE));
		
		node1 = new TreeNode<Quest>(Quest.SIDE_SLAVER_RECOMMENDATION_OBTAINED);
		slaveryTree.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);
		
		node1 = new TreeNode<Quest>(Quest.SIDE_HYPNO_WATCH_TEST_SUBJECT);
		hypnoWatchTree.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);

		node1 = new TreeNode<Quest>(Quest.LIGHTNING_SPELL_2_WAITING);
		arcaneLightningTree.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);
		
		node1 = new TreeNode<Quest>(Quest.HARPY_PACIFICATION_TWO);
		angryHarpyTree.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.HARPY_PACIFICATION_THREE);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.HARPY_PACIFICATION_REWARD);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);
		
		
		// Slime queen:
		
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
		
		
		// Teleporting:

		node1 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		teleportingTree.addChild(node1);
		node1 = new TreeNode<Quest>(Quest.TELEPORTING_CAUGHT);
		teleportingTree.addChild(node1);
		node2.addChild(new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE));
		node1.addChild(node2);
		
		
		// Daddy:
		
		node1 = new TreeNode<Quest>(Quest.DADDY_MEETING);
		daddyTree.addChild(node1);
		nodeBranchA = new TreeNode<Quest>(Quest.DADDY_REFUSED);
		daddyTree.addChild(nodeBranchA);
		
		node2 = new TreeNode<Quest>(Quest.DADDY_ACCEPTED);
		node1.addChild(node2);
		nodeBranchA = new TreeNode<Quest>(Quest.DADDY_REFUSED_2);
		node1.addChild(nodeBranchA);
		
		node1 = new TreeNode<Quest>(Quest.DADDY_LILAYA_MEETING);
		node2.addChild(node1);
		
		node1.addChild(new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE));
		

		// Buying Brax:

		node1 = new TreeNode<Quest>(Quest.BUYING_BRAX_DELIVER_PERFUME);
		buyingBraxTree.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.BUYING_BRAX_LOLLIPOPS);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.BUYING_BRAX_DELIVER_LOLLIPOPS);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.BUYING_BRAX_LIPSTICK);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.BUYING_BRAX_DELIVER_LIPSTICK);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);
		
		
		// Dealing with Vengar:

		node1 = new TreeNode<Quest>(Quest.VENGAR_ONE);
		vengarTree.addChild(node1);

		nodeBranchA = new TreeNode<Quest>(Quest.VENGAR_TWO_CONFLICT);
		node1.addChild(nodeBranchA);
		nodeBranchB = new TreeNode<Quest>(Quest.VENGAR_TWO_COOPERATION);
		node1.addChild(nodeBranchB);
		nodeBranchC = new TreeNode<Quest>(Quest.VENGAR_TWO_ENFORCERS);
		node1.addChild(nodeBranchC);
		
		node2 = new TreeNode<Quest>(Quest.VENGAR_THREE_END);
		nodeBranchA.addChild(node2);
		node2.addChild(new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE));

		node2 = new TreeNode<Quest>(Quest.VENGAR_THREE_COOPERATION_END);
		nodeBranchB.addChild(node2);
		node2.addChild(new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE));

		node2 = new TreeNode<Quest>(Quest.VENGAR_THREE_END);
		nodeBranchC.addChild(node2);
		node2.addChild(new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE));
		
		
		// Rebel base:

		node1 = new TreeNode<Quest>(Quest.REBEL_BASE_HANDLE_REFUSED);
		rebelBaseTree.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.REBEL_BASE_PASSWORD_PART_ONE);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.REBEL_BASE_PASSWORD_PART_TWO);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.REBEL_BASE_PASSWORD_COMPLETE);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.REBEL_BASE_EXPLORATION);
		node2.addChild(node1);

		nodeBranchA = new TreeNode<Quest>(Quest.REBEL_BASE_ESCAPE);
		node1.addChild(nodeBranchA);
		nodeBranchB = new TreeNode<Quest>(Quest.REBEL_BASE_FAILED);
		node1.addChild(nodeBranchB);
		node2 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		nodeBranchA.addChild(node2);


		//Firebombs:

		node1 = new TreeNode<Quest>(Quest.REBEL_BASE_FIREBOMBS_START);
		rebelBaseFirebombTree.addChild(node1);
		nodeBranchA = new TreeNode<Quest>(Quest.REBEL_BASE_FIREBOMBS_FINISH);
		node1.addChild(nodeBranchA);
		nodeBranchB = new TreeNode<Quest>(Quest.REBEL_BASE_FIREBOMBS_FAILED);
		node1.addChild(nodeBranchB);
		node1 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		nodeBranchA.addChild(node1);

		// Eisek stall quest

		node1 = new TreeNode<Quest>(Quest.EISEK_STALL_QUEST_STAGE_ONE);
		eisekStallTree.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.EISEK_STALL_QUEST_STAGE_TWO);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.EISEK_STALL_QUEST_STAGE_THREE);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.EISEK_STALL_QUEST_STAGE_FOUR);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		node2.addChild(node1);


		// Eisek mob quest

		node1 = new TreeNode<Quest>(Quest.EISEK_MOB_QUEST_STAGE_ONE);
		eisekMobTree.addChild(node1);
		nodeBranchA = new TreeNode<Quest>(Quest.EISEK_MOB_QUEST_STAGE_TWO);
		node1.addChild(nodeBranchA);
		nodeBranchB = new TreeNode<Quest>(Quest.EISEK_MOB_QUEST_STAGE_TWO_FAILED);
		node1.addChild(nodeBranchB);
		nodeBranchB.addChild(new TreeNode<Quest>(Quest.EISEK_MOB_QUEST_STAGE_THREE_FAILED));
		
		node1 = new TreeNode<Quest>(Quest.EISEK_MOB_QUEST_STAGE_THREE);
		nodeBranchA.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);
		
		// Eisek silly mode quest
		
		node1 = new TreeNode<Quest>(Quest.EISEK_SILLYMODE_QUEST_STAGE_ONE);
		eisekSillyModeTree.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.EISEK_SILLYMODE_QUEST_STAGE_TWO);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.EISEK_SILLYMODE_QUEST_STAGE_THREE);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);
        
		// Wes quest:

		node1 = new TreeNode<Quest>(Quest.WES_1);
		wesTree.addChild(node1);

		node2 = new TreeNode<Quest>(Quest.WES_2);
		node1.addChild(node2);

		nodeBranchA = new TreeNode<Quest>(Quest.WES_3_WES);
		node2.addChild(nodeBranchA);
		nodeBranchA.addChild(new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE));

		nodeBranchA = new TreeNode<Quest>(Quest.WES_3_ELLE);
		node2.addChild(nodeBranchA);
		nodeBranchA.addChild(new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE));
		
		
		// Beer barrels for Oglix:
		
		node1 = new TreeNode<Quest>(Quest.OGLIX_BEER_BARRELS_2);
		beerBarrelTree.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);
		
		
		// Lunexis escape:
		
		node1 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		lunexisEscapeTree.addChild(node1);
		
		
		
		// Romance quests:

		
		node1 = new TreeNode<Quest>(Quest.RELATIONSHIP_NYAN_2_STOCK_ISSUES_AGREED_TO_HELP);
		nyanTree.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.RELATIONSHIP_NYAN_4_STOCK_ISSUES_SUPPLIERS_BEATEN);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);
		
		
		node1 = new TreeNode<Quest>(Quest.ROMANCE_HELENA_2_PURCHASE_PAINT);
		helenaTree.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.ROMANCE_HELENA_6_ADVERTISING);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.ROMANCE_HELENA_7_GRAND_OPENING_PREPARATION);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.ROMANCE_HELENA_8_FINISH);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);
		

		node1 = new TreeNode<Quest>(Quest.ROMANCE_NATALYA_2_CONTRACT_SIGNED);
		natalyaTree.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.ROMANCE_NATALYA_3_TRAINING_1);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.ROMANCE_NATALYA_4_TRAINING_2);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.ROMANCE_NATALYA_5_TRAINING_3);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		node2.addChild(node1);

		
		node1 = new TreeNode<Quest>(Quest.ROMANCE_MONICA_2_UNREASONABLE_DEMAND);
		monicaTree.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.ROMANCE_MONICA_3_THE_JOURNEY_HOME);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		node2.addChild(node1);
		
	}
}
