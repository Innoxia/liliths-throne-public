package com.lilithsthrone.game.character.quests;

import com.lilithsthrone.utils.TreeNode;

/**
 * @since 0.1.99
 * @version 0.4.2
 * @author Innoxia
 */
public class QuestTree {

	public static TreeNode<AbstractQuest> mainQuestTree = new TreeNode<AbstractQuest>(Quest.MAIN_PROLOGUE);
	
	// Side quests:
	public static TreeNode<AbstractQuest> enchantmentTree = new TreeNode<AbstractQuest>(Quest.SIDE_ENCHANTMENTS_LILAYA_HELP);
	public static TreeNode<AbstractQuest> pregnancyTree = new TreeNode<AbstractQuest>(Quest.SIDE_PREGNANCY_CONSULT_LILAYA);
	public static TreeNode<AbstractQuest> incubationTree = new TreeNode<AbstractQuest>(Quest.SIDE_INCUBATION_WAITING);
	public static TreeNode<AbstractQuest> slaveryTree = new TreeNode<AbstractQuest>(Quest.SIDE_SLAVER_NEED_RECOMMENDATION);
	public static TreeNode<AbstractQuest> accommodationTree = new TreeNode<AbstractQuest>(Quest.SIDE_ACCOMMODATION_NEED_LILAYAS_PERMISSION);
	public static TreeNode<AbstractQuest> hypnoWatchTree = new TreeNode<AbstractQuest>(Quest.SIDE_HYPNO_WATCH_VICKY);
	public static TreeNode<AbstractQuest> arcaneLightningTree = new TreeNode<AbstractQuest>(Quest.LIGHTNING_SPELL_1_PAYMENT);
	public static TreeNode<AbstractQuest> angryHarpyTree = new TreeNode<AbstractQuest>(Quest.HARPY_PACIFICATION_ONE);
	public static TreeNode<AbstractQuest> slimeQueenTree = new TreeNode<AbstractQuest>(Quest.SLIME_QUEEN_ONE);
	public static TreeNode<AbstractQuest> teleportingTree = new TreeNode<AbstractQuest>(Quest.TELEPORTING_START);
	public static TreeNode<AbstractQuest> daddyTree = new TreeNode<AbstractQuest>(Quest.DADDY_START);
	public static TreeNode<AbstractQuest> buyingBraxTree = new TreeNode<AbstractQuest>(Quest.BUYING_BRAX_START);
	public static TreeNode<AbstractQuest> vengarTree = new TreeNode<AbstractQuest>(Quest.VENGAR_START);
	public static TreeNode<AbstractQuest> wesTree = new TreeNode<AbstractQuest>(Quest.WES_START);
	public static TreeNode<AbstractQuest> beerBarrelTree = new TreeNode<AbstractQuest>(Quest.OGLIX_BEER_BARRELS_1);
	public static TreeNode<AbstractQuest> lunexisEscapeTree = new TreeNode<AbstractQuest>(Quest.LUNEXIS_ESCAPE);
	public static TreeNode<AbstractQuest> dollFactoryTree = new TreeNode<AbstractQuest>(Quest.DOLL_FACTORY_1);
	
	// Relationship/romance quests:
	public static TreeNode<AbstractQuest> nyanTree = new TreeNode<AbstractQuest>(Quest.RELATIONSHIP_NYAN_1_STOCK_ISSUES);
	public static TreeNode<AbstractQuest> helenaTree = new TreeNode<AbstractQuest>(Quest.ROMANCE_HELENA_1_OFFER_HELP);
	public static TreeNode<AbstractQuest> natalyaTree = new TreeNode<AbstractQuest>(Quest.ROMANCE_NATALYA_1_INTERVIEW_START);
	public static TreeNode<AbstractQuest> monicaTree = new TreeNode<AbstractQuest>(Quest.ROMANCE_MONICA_1_TO_THE_FARM);
	
	// DSG's quests:
	public static TreeNode<AbstractQuest> eisekStallTree = new TreeNode<AbstractQuest>(Quest.EISEK_STALL_QUEST_STAGE_ONE);
	public static TreeNode<AbstractQuest> eisekMobTree = new TreeNode<AbstractQuest>(Quest.EISEK_MOB_QUEST_STAGE_ONE);
	public static TreeNode<AbstractQuest> eisekSillyModeTree = new TreeNode<AbstractQuest>(Quest.EISEK_SILLYMODE_QUEST_STAGE_ONE);
	
	public static TreeNode<AbstractQuest> rebelBaseTree = new TreeNode<AbstractQuest>(Quest.REBEL_BASE_EXPLORATION);
	public static TreeNode<AbstractQuest> rebelBaseFirebombTree = new TreeNode<AbstractQuest>(Quest.REBEL_BASE_FIREBOMBS_START);
	
	
	static {
		TreeNode<AbstractQuest> node1 = new TreeNode<AbstractQuest>(Quest.MAIN_1_A_LILAYAS_TESTS);
		mainQuestTree.addChild(node1);
		TreeNode<AbstractQuest> node2 = new TreeNode<AbstractQuest>(Quest.MAIN_1_B_DEMON_HOME);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.MAIN_1_C_WOLFS_DEN);
		node2.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.MAIN_1_D_SLAVERY);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.MAIN_1_E_REPORT_TO_HELENA);
		node2.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.MAIN_1_F_SCARLETTS_FATE);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.MAIN_1_G_SLAVERY);
		node2.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.MAIN_1_H_THE_GREAT_ESCAPE);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.MAIN_1_I_ARTHURS_TALE);
		node2.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.MAIN_2_A_INTO_THE_DEPTHS);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.MAIN_2_B_SIRENS_CALL);
		node2.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.MAIN_2_C_SIRENS_FALL);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.MAIN_2_D_MEETING_A_LILIN);
		node2.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.MAIN_3_ELIS);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.MAIN_3_B_MEETING_MERAXIS);
		node2.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.MAIN_3_C_MEETING_MINOTALLYS);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.MAIN_3_D_TO_THEMISCYRA);
		node2.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.MAIN_3_E_THEMISCYRA_ATTACK);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.MAIN_3_F_PREPARING_ELIS);
		node2.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.MAIN_3_G_SWORD_SCAPEGOAT);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.MAIN_3_H_SWORD_MISSION);
		node2.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.MAIN_3_I_ARION_REPORT);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.MAIN_3_J_TODO);
		node2.addChild(node1);

		enchantmentTree.addChild(new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE));

		node1 = new TreeNode<AbstractQuest>(Quest.SIDE_PREGNANCY_LILAYA_THE_MIDWIFE);
		pregnancyTree.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);

		node1 = new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE);
		incubationTree.addChild(node1);
		
		accommodationTree.addChild(new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE));
		
		node1 = new TreeNode<AbstractQuest>(Quest.SIDE_SLAVER_RECOMMENDATION_OBTAINED);
		slaveryTree.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);
		
		node1 = new TreeNode<AbstractQuest>(Quest.SIDE_HYPNO_WATCH_TEST_SUBJECT);
		hypnoWatchTree.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);

		node1 = new TreeNode<AbstractQuest>(Quest.LIGHTNING_SPELL_2_WAITING);
		arcaneLightningTree.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);
		
		node1 = new TreeNode<AbstractQuest>(Quest.HARPY_PACIFICATION_TWO);
		angryHarpyTree.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.HARPY_PACIFICATION_THREE);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.HARPY_PACIFICATION_REWARD);
		node2.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);
		
		
		// Slime queen:
		
		node1 = new TreeNode<AbstractQuest>(Quest.SLIME_QUEEN_TWO);
		slimeQueenTree.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.SLIME_QUEEN_THREE);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.SLIME_QUEEN_FOUR);
		node2.addChild(node1);
		
		TreeNode<AbstractQuest> nodeBranchA = new TreeNode<AbstractQuest>(Quest.SLIME_QUEEN_FIVE_FORCE);
		node1.addChild(nodeBranchA);
		TreeNode<AbstractQuest> nodeBranchB = new TreeNode<AbstractQuest>(Quest.SLIME_QUEEN_FIVE_CONVINCE);
		node1.addChild(nodeBranchB);
		TreeNode<AbstractQuest> nodeBranchC = new TreeNode<AbstractQuest>(Quest.SLIME_QUEEN_FIVE_SUBMIT);
		node1.addChild(nodeBranchC);

		node2 = new TreeNode<AbstractQuest>(Quest.SLIME_QUEEN_SIX_FORCE);
		nodeBranchA.addChild(node2);
		node2.addChild(new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE));
		
		node2 = new TreeNode<AbstractQuest>(Quest.SLIME_QUEEN_SIX_CONVINCE);
		nodeBranchB.addChild(node2);
		node2.addChild(new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE));
		
		node2 = new TreeNode<AbstractQuest>(Quest.SLIME_QUEEN_SIX_SUBMIT);
		nodeBranchC.addChild(node2);
		node2.addChild(new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE));
		
		
		// Teleporting:

		node1 = new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE);
		teleportingTree.addChild(node1);
		node1 = new TreeNode<AbstractQuest>(Quest.TELEPORTING_CAUGHT);
		teleportingTree.addChild(node1);
		node2.addChild(new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE));
		node1.addChild(node2);
		
		
		// Daddy:
		
		node1 = new TreeNode<AbstractQuest>(Quest.DADDY_MEETING);
		daddyTree.addChild(node1);
		nodeBranchA = new TreeNode<AbstractQuest>(Quest.DADDY_REFUSED);
		daddyTree.addChild(nodeBranchA);
		
		node2 = new TreeNode<AbstractQuest>(Quest.DADDY_ACCEPTED);
		node1.addChild(node2);
		nodeBranchA = new TreeNode<AbstractQuest>(Quest.DADDY_REFUSED_2);
		node1.addChild(nodeBranchA);
		
		node1 = new TreeNode<AbstractQuest>(Quest.DADDY_LILAYA_MEETING);
		node2.addChild(node1);
		
		node1.addChild(new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE));
		

		// Buying Brax:

		node1 = new TreeNode<AbstractQuest>(Quest.BUYING_BRAX_DELIVER_PERFUME);
		buyingBraxTree.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.BUYING_BRAX_LOLLIPOPS);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.BUYING_BRAX_DELIVER_LOLLIPOPS);
		node2.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.BUYING_BRAX_LIPSTICK);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.BUYING_BRAX_DELIVER_LIPSTICK);
		node2.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);
		
		
		// Dealing with Vengar:

		node1 = new TreeNode<AbstractQuest>(Quest.VENGAR_ONE);
		vengarTree.addChild(node1);

		nodeBranchA = new TreeNode<AbstractQuest>(Quest.VENGAR_TWO_CONFLICT);
		node1.addChild(nodeBranchA);
		nodeBranchB = new TreeNode<AbstractQuest>(Quest.VENGAR_TWO_COOPERATION);
		node1.addChild(nodeBranchB);
		nodeBranchC = new TreeNode<AbstractQuest>(Quest.VENGAR_TWO_ENFORCERS);
		node1.addChild(nodeBranchC);
		
		node2 = new TreeNode<AbstractQuest>(Quest.VENGAR_THREE_END);
		nodeBranchA.addChild(node2);
		node2.addChild(new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE));

		node2 = new TreeNode<AbstractQuest>(Quest.VENGAR_THREE_COOPERATION_END);
		nodeBranchB.addChild(node2);
		node2.addChild(new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE));

		node2 = new TreeNode<AbstractQuest>(Quest.VENGAR_THREE_END);
		nodeBranchC.addChild(node2);
		node2.addChild(new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE));
		
		
		// Rebel base:

		node1 = new TreeNode<AbstractQuest>(Quest.REBEL_BASE_HANDLE_REFUSED);
		rebelBaseTree.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.REBEL_BASE_PASSWORD_PART_ONE);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.REBEL_BASE_PASSWORD_PART_TWO);
		node2.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.REBEL_BASE_PASSWORD_COMPLETE);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.REBEL_BASE_EXPLORATION);
		node2.addChild(node1);

		nodeBranchA = new TreeNode<AbstractQuest>(Quest.REBEL_BASE_ESCAPE);
		node1.addChild(nodeBranchA);
		nodeBranchB = new TreeNode<AbstractQuest>(Quest.REBEL_BASE_FAILED);
		node1.addChild(nodeBranchB);
		node2 = new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE);
		nodeBranchA.addChild(node2);


		//Firebombs:

		node1 = new TreeNode<AbstractQuest>(Quest.REBEL_BASE_FIREBOMBS_START);
		rebelBaseFirebombTree.addChild(node1);
		nodeBranchA = new TreeNode<AbstractQuest>(Quest.REBEL_BASE_FIREBOMBS_FINISH);
		node1.addChild(nodeBranchA);
		nodeBranchB = new TreeNode<AbstractQuest>(Quest.REBEL_BASE_FIREBOMBS_FAILED);
		node1.addChild(nodeBranchB);
		node1 = new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE);
		nodeBranchA.addChild(node1);

		// Eisek stall quest

		node1 = new TreeNode<AbstractQuest>(Quest.EISEK_STALL_QUEST_STAGE_ONE);
		eisekStallTree.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.EISEK_STALL_QUEST_STAGE_TWO);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.EISEK_STALL_QUEST_STAGE_THREE);
		node2.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.EISEK_STALL_QUEST_STAGE_FOUR);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE);
		node2.addChild(node1);


		// Eisek mob quest

		node1 = new TreeNode<AbstractQuest>(Quest.EISEK_MOB_QUEST_STAGE_ONE);
		eisekMobTree.addChild(node1);
		nodeBranchA = new TreeNode<AbstractQuest>(Quest.EISEK_MOB_QUEST_STAGE_TWO);
		node1.addChild(nodeBranchA);
		nodeBranchB = new TreeNode<AbstractQuest>(Quest.EISEK_MOB_QUEST_STAGE_TWO_FAILED);
		node1.addChild(nodeBranchB);
		nodeBranchB.addChild(new TreeNode<AbstractQuest>(Quest.EISEK_MOB_QUEST_STAGE_THREE_FAILED));
		
		node1 = new TreeNode<AbstractQuest>(Quest.EISEK_MOB_QUEST_STAGE_THREE);
		nodeBranchA.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);
		
		// Eisek silly mode quest
		
		node1 = new TreeNode<AbstractQuest>(Quest.EISEK_SILLYMODE_QUEST_STAGE_ONE);
		eisekSillyModeTree.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.EISEK_SILLYMODE_QUEST_STAGE_TWO);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.EISEK_SILLYMODE_QUEST_STAGE_THREE);
		node2.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);
        
		// Wes quest:

		node1 = new TreeNode<AbstractQuest>(Quest.WES_1);
		wesTree.addChild(node1);

		node2 = new TreeNode<AbstractQuest>(Quest.WES_2);
		node1.addChild(node2);

		nodeBranchA = new TreeNode<AbstractQuest>(Quest.WES_3_WES);
		node2.addChild(nodeBranchA);
		nodeBranchA.addChild(new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE));

		nodeBranchA = new TreeNode<AbstractQuest>(Quest.WES_3_ELLE);
		node2.addChild(nodeBranchA);
		nodeBranchA.addChild(new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE));
		
		
		// Beer barrels for Oglix:
		
		node1 = new TreeNode<AbstractQuest>(Quest.OGLIX_BEER_BARRELS_2);
		beerBarrelTree.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);
		
		
		// Lunexis escape:
		
		node1 = new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE);
		lunexisEscapeTree.addChild(node1);
		
		
		// Doll factory:

		node1 = new TreeNode<AbstractQuest>(Quest.DOLL_FACTORY_2);
		dollFactoryTree.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.DOLL_FACTORY_3);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.DOLL_FACTORY_4);
		node2.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.DOLL_FACTORY_5);
		node1.addChild(node2);

		nodeBranchA = new TreeNode<AbstractQuest>(Quest.DOLL_FACTORY_6A);
		node2.addChild(nodeBranchA);
		nodeBranchA.addChild(new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE));

		nodeBranchA = new TreeNode<AbstractQuest>(Quest.DOLL_FACTORY_6B);
		node2.addChild(nodeBranchA);
		nodeBranchA.addChild(new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE));
		
		
		
		// Romance quests:

		
		node1 = new TreeNode<AbstractQuest>(Quest.RELATIONSHIP_NYAN_2_STOCK_ISSUES_AGREED_TO_HELP);
		nyanTree.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.RELATIONSHIP_NYAN_4_STOCK_ISSUES_SUPPLIERS_BEATEN);
		node2.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);
		
		
		node1 = new TreeNode<AbstractQuest>(Quest.ROMANCE_HELENA_2_PURCHASE_PAINT);
		helenaTree.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR);
		node2.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN);
		node2.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.ROMANCE_HELENA_6_ADVERTISING);
		node2.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.ROMANCE_HELENA_7_GRAND_OPENING_PREPARATION);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.ROMANCE_HELENA_8_FINISH);
		node2.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);
		

		node1 = new TreeNode<AbstractQuest>(Quest.ROMANCE_NATALYA_2_CONTRACT_SIGNED);
		natalyaTree.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.ROMANCE_NATALYA_3_TRAINING_1);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.ROMANCE_NATALYA_4_TRAINING_2);
		node2.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.ROMANCE_NATALYA_5_TRAINING_3);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE);
		node2.addChild(node1);

		
		node1 = new TreeNode<AbstractQuest>(Quest.ROMANCE_MONICA_2_UNREASONABLE_DEMAND);
		monicaTree.addChild(node1);
		node2 = new TreeNode<AbstractQuest>(Quest.ROMANCE_MONICA_3_THE_JOURNEY_HOME);
		node1.addChild(node2);
		node1 = new TreeNode<AbstractQuest>(Quest.SIDE_UTIL_COMPLETE);
		node2.addChild(node1);
		
	}
}
