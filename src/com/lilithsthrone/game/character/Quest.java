package com.lilithsthrone.game.character;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.0
 * @version 0.1.97
 * @author Innoxia
 */
public enum Quest {

	// Main quests:

	MAIN_PROLOGUE(0, QuestType.MAIN, 1, 5) {
		@Override
		public String getName() {
			return "Survive the evening";
		}

		@Override
		public String getDescription() {
			return "You promised your aunt Lily that you'd attend the opening of her museum's new exhibit. You need to survive the boredom of the evening ahead.";
		}

		@Override
		public String getCompletedDescription() {
			return "Your evening at the museum turned out to be far more eventful than you'd have liked." + " A mysterious demon named Lilith tricked you into being pulled through a magical portal and into a parallel universe."
					+ " After waking up in the middle of an unfamiliar street, you were saved from a dire situation by the half-demon '" + Main.game.getLilaya().getName() + "'."
					+ " She seems to be this universe's version of your aunt Lily, and, in return for agreeing to help her with her experiments, she's allowed you to stay at her home.";
		}
	},

	MAIN_1_A_LILAYAS_TESTS(1, QuestType.MAIN, 1, 10) {
		@Override
		public String getName() {
			return "Lilaya's tests";
		}

		@Override
		public String getDescription() {
			return "You can find Lilaya in her lab at any time, where she'll be ready to continue running her tests on you. Maybe she can find a way to send you back home?";
		}

		@Override
		public String getCompletedDescription() {
			return Main.game.getLilaya().getName() + " ran some more tests on you, but she's unable to progress with her research without the help of her old colleague, Arthur.";
		}
	},

	MAIN_1_B_DEMON_HOME(2, QuestType.MAIN, 1, 10) {
		@Override
		public String getName() {
			return "The search for Arthur; Demon Home";
		}

		@Override
		public String getDescription() {
			return "Lilaya has informed you that her old colleague, Arthur, would know more about the type of magic used in the portal."
					+ " However, she seems to have an intense dislike of him, and you've ended up being tasked to go and get him to apologise to Lilaya before she'll allow him to" + " come and work with her."
					+ " Arthur lives in a district of the city called 'Demon Home', so you can find him there.";
		}

		@Override
		public String getCompletedDescription() {
			return "When you arrived at Arthur's home, you found that Dominion's Enforcers had arrested him on suspicion of plotting against Lilith." + " After his arrest, he was taken to the Enforcer's HQ.";
		}
	},

	MAIN_1_C_WOLFS_DEN(3, QuestType.MAIN, 3, 20) {
		@Override
		public String getName() {
			return "The search for Arthur; The Wolf's Den";
		}

		@Override
		public String getDescription() {
			return "Arthur has been arrested by Dominion's Enforcers, and has been taken to the Enforcer's HQ." + " It looks like you'll have to inquire further there and find out a way to save Arthur.";
		}

		@Override
		public String getCompletedDescription() {
			return "You were forced into a fight with the Enforcer's Chief, Brax." + " Thankfully, you were able to beat him, but you then found out that Arthur has been sold into slavery!";
		}
	},

	MAIN_1_D_SLAVERY(4, QuestType.MAIN, 3, 10) {
		@Override
		public String getName() {
			return "The search for Arthur; Sold into Slavery";
		}

		@Override
		public String getDescription() {
			return "After defeating Brax, you found out that Arthur was sold into slavery to a trader called Scarlett."
					+ " You'll have to travel to Slaver Alley, find Scarlett, and find a way to free Arthur.";
		}

		@Override
		public String getCompletedDescription() {
			return "You found the harpy Scarlett in Slaver Alley, who turned out to be one of the most annoying people you've ever met.";
		}
	},
	
	MAIN_1_E_REPORT_TO_ALEXA(5, QuestType.MAIN, 3, 30) {
		@Override
		public String getName() {
			return "The search for Arthur; Find Alexa";
		}

		@Override
		public String getDescription() {
			return "After finding Scarlett in Slaver Alley, you discovered that she's no longer in possession of Arthur."
					+ " Before she'll tell you anything about it, she wants you to go the Harpy Nests and report to her matriarch, Alexa, that her business is a complete failure";
		}

		@Override
		public String getCompletedDescription() {
			return "You reported Scarlett's problems to her matriarch, Alexa."
					+ " She didn't seem to have much sympathy for Scarlett, and quickly flew off to go and talk to her in person.";
		}
	},
	
	MAIN_1_F_SCARLETTS_FATE(6, QuestType.MAIN, 3, 30) {
		@Override
		public String getName() {
			return "The search for Arthur; Scarlett's fate";
		}

		@Override
		public String getDescription() {
			return "You need to travel back to Scarlett's shop to find out what's become of her. Hopefully Alexa wasn't too hard on her, and she'll be willing to tell you what happened to Arthur now...";
		}

		@Override
		public String getCompletedDescription() {
			return "You travelled back to Scarlett's shop, only to discover that Alexa has enslaved her!";
		}
	},
	
	MAIN_1_G_SLAVERY(7, QuestType.MAIN, 3, 30) {
		@Override
		public String getName() {
			return "The search for Arthur; Slavery";
		}

		@Override
		public String getDescription() {
			return "Alexa is willing to sell Scarlett to you, which seems to be the only way you'll get the information you need."
					+ " You'll need to have a slaver license in order to buy Scarlett.";
		}

		@Override
		public String getCompletedDescription() {
			return "Alexa sold Scarlett to you, which allowed you to order Scarlett to tell you what happened to Arthur.";
		}
	},
	
	MAIN_1_H_THE_GREAT_ESCAPE(8, QuestType.MAIN, 10, 200) {
		@Override
		public String getName() {
			return "The search for Arthur; The Great Escape";
		}

		@Override
		public String getDescription() {
			return "It turns out that Arthur was sold to an extremely dangerous demon called Zaranix, who lives in Demon Home."
					+ " You'll need to travel to demon home and rescue Arthur!";
		}

		@Override
		public String getCompletedDescription() {
			return "After defeating Zaranix, you saved Arthur and brought him back to Lilaya's home.";
		}
	},
	
	MAIN_1_I_ARTHURS_TALE(9, QuestType.MAIN, 10, 30) {
		@Override
		public String getName() {
			return "The search for Arthur; Conclusion";
		}

		@Override
		public String getDescription() {
			return "Now that you've rescued Arthur from the clutches of Zaranix, you should travel back to Lilaya's home and get the full story of what happened from him.";
		}

		@Override
		public String getCompletedDescription() {
			return "Arthur explained how he was dabbling in the forbidden art of teleportation spells."
					+ " Through one of his agents, Zaranix found out about this, and had no difficulty in getting Arthur enslaved for treason."
					+ " Now that you've rescued him, he's keen to repay the favour by finding out how to send you back home.";
		}
	},
	
	MAIN_1_J_ARTHURS_ROOM(10, QuestType.MAIN, 10, 30) {
		@Override
		public String getName() {
			return "The search for Arthur; A room of his own";
		}

		@Override
		public String getDescription() {
			return "Lilaya really doesn't want Arthur in her lab, and has tasked you to help Rose find a suitable room for him to stay in.</br>"
					+ "<i>Go into one of the empty rooms in Lilaya's House, and through the room management window, upgrade it to 'Arthur's Room'.</i>";
		}

		@Override
		public String getCompletedDescription() {
			return "You located a suitable room for Arthur, and, with Rose's help, moved a significant amount of arcane instrumentation into his new lab-cum-bedroom.";
		}
	},
	
	
	MAIN_2_A_INTO_THE_DEPTHS(11, QuestType.MAIN, 12, 30) {
		@Override
		public String getName() {
			return "Into the depths; The world of Submission";
		}

		@Override
		public String getDescription() {
			return "Arthur was able to explain the mechanism by which you were transported into this new world, but he seemed to hold back on some of the details."
					+ " He said that he'd explain everything fully once he knew for certain what was going on, but in order for that to happen, he'll need to talk to one of the seven elder Lilin."
					+ " After much arguing, Lilaya agreed to convince her mother to help, but you'll have to be the one to deliver the message.</br>"
					+ "<i>Travel down into the undercity of Submission and seek an audience with Lilaya's mother, Lyssieth.</i>";
		}

		@Override
		public String getCompletedDescription() {
			return "Spoilers! :3"; //TODO
		}
	},

	// Side Quests:

	SIDE_DISCOVER_ALL_ITEMS(0, QuestType.SIDE, 1, 100) {
		@Override
		public String getName() {
			return "Completionist";
		}

		@Override
		public String getDescription() {
			return "There are a lot of magical items in this new world. You wonder if you can find them all...";
		}

		@Override
		public String getCompletedDescription() {
			return "You have discovered every item there is to find!";
		}
	},

	SIDE_DISCOVER_ALL_RACES(1, QuestType.SIDE, 1, 100) {
		@Override
		public String getName() {
			return "Completionist";
		}

		@Override
		public String getDescription() {
			return "There seem to be a lot of strange new races in this world. You wonder if you can discover them all...";
		}

		@Override
		public String getCompletedDescription() {
			return "You have discovered every race there is to find!.";
		}
	},
	
	// For when you discover a jinxed item:
	
	SIDE_JINXED_LILAYA_HELP(0, QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Lilaya knows everything!";
		}

		@Override
		public String getDescription() {
			return "After equipping a mysterious item of clothing, you discovered that not all arcane enchantments are beneficial."
					+ " You're sure that Lilaya will know how to remove your jinxed clothing, so perhaps you should go and ask her about it.";
		}

		@Override
		public String getCompletedDescription() {
			return "After scolding you for a little while, Lilaya informed you that you'll need use an enchanted weapon as a sacrifice in order to remove your jinxed clothing.";
		}
	},
	SIDE_JINX_REMOVE_JINX(1, QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Remove the jinxed clothing";
		}

		@Override
		public String getDescription() {
			return "You should try out your newfound knowledge of how to remove jinxed clothing.";
		}

		@Override
		public String getCompletedDescription() {
			return "You've managed to remove a jinx from an item of clothing! Maybe you shouldn't equip unidentified clothing in the future...";
		}
	},
	
	// For when you discover your first essence:
	
	SIDE_ENCHANTMENTS_LILAYA_HELP(0, QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Ask Lilaya for help";
		}

		@Override
		public String getDescription() {
			return "You recently felt a strange force entering your body, and although it doesn't seem to have had any obvious effect, you should probably have it checked out."
					+ " Lilaya is sure to know more, so perhaps you should go and talk to her about it.";
		}

		@Override
		public String getCompletedDescription() {
			return "Lilaya informed you that you're able to collect 'essences' from other people's arcane aura."
					+ " She seemed a little worried that you're able to do this, as apparently it's normally only Lilin who are able to gather essences in this fashion...";
		}
	},
//	SIDE_ENCHANTMENTS_ENCHANT_SOMETHING(1, QuestType.SIDE, 1, 10) {
//		@Override
//		public String getName() {
//			return "Enchant something";
//		}
//
//		@Override
//		public String getDescription() {
//			return "Lilaya informed you that essences are the vital component in infusing the arcane into objects in order to create enchanted items."
//					+ " You should try enchanting something! (Select an enchantable item in your inventory, then choose 'enchant'!)";
//		}
//
//		@Override
//		public String getCompletedDescription() {
//			return "You've managed to enchant an item!";
//		}
//	},

	// For the first time you get pregnant:
	
	SIDE_PREGNANCY_CONSULT_LILAYA(0, QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Lilaya knows best";
		}

		@Override
		public String getDescription() {
			return "You're pregnant... Oh crap, <b>you're pregnant</b>! Surely Lilaya will know what to do!";
		}

		@Override
		public String getCompletedDescription() {
			return "Lilaya managed to calm you down, and reassured you that pregnancy in this world isn't as big a deal as it was back home.";
		}
	},
	SIDE_PREGNANCY_LILAYA_THE_MIDWIFE(1, QuestType.SIDE, 1, 20) {
		@Override
		public String getName() {
			return "Lilaya the midwife";
		}

		@Override
		public String getDescription() {
			return "Lilaya said that she'd be able to help you give birth whenever you're ready. You're going to need to wait until your belly has finished growing, then you can go and see Lilaya to give birth.";
		}

		@Override
		public String getCompletedDescription() {
			return "Lilaya helped you give birth. She said that if ever you get pregnant again, she can always help out.";
		}
	},
	
	// Getting a Slaver License:
	
	SIDE_SLAVER_NEED_RECOMMENDATION(0, QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Letter of recommendation";
		}

		@Override
		public String getDescription() {
			return "After asking how to obtain a Slaver License at the Slavery Administration building, you discovered that you'll need a letter of recommendation first. Lilaya should be able to help with that.";
		}

		@Override
		public String getCompletedDescription() {
			return "Lilaya gave you a letter of recommendation, and what's more, she also offered to let you house your slaves in her mansion.";
		}
	},
	
	SIDE_SLAVER_RECOMMENDATION_OBTAINED(1, QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Present letter";
		}

		@Override
		public String getDescription() {
			return "Now that you've obtained a letter of recommendation from Lilaya, you should go back to the Slavery Administration building in Slaver Alley and present it to [finch.name].";
		}

		@Override
		public String getCompletedDescription() {
			return "You presented the letter of recommendation to [finch.name], and, after paying the 500 flame fee, you obtained a slaver license!";
		}
	},
	
	SIDE_HYPNO_WATCH_VICKY(0, QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Order at Arcane Arts";
		}

		@Override
		public String getDescription() {
			return "Arthur informed you that he was instructed by Zaranix to find an arcane method of changing a person's sexual orientation."
					+ " While he reassured you that he had no intention of ever using such an item himself, Arthur did express an interest in completing his research,"
						+ " and asked you to fetch a special order from the store 'Arcane Arts' in the Shopping Arcade.";
		}

		@Override
		public String getCompletedDescription() {
			return "You retrieved the package from Arcane Arts, and brought it back to Arthur.";
		}
	},
	
	SIDE_HYPNO_WATCH_TEST_SUBJECT(1, QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Test subject";
		}

		@Override
		public String getDescription() {
			return "After Lilaya followed Arthur's instructions to enchant the watch, she asked if it would be possible to test it on you...";
//					+ " You could either offer yourself, or, if you own any slaves, offer one of those to Arthur instead.";
		}

		@Override
		public String getCompletedDescription() {
			return "The Hypno-Watch appeared to work, although Lilaya stopped the test before it had a permanent effect."
					+ " She warned that it will have a strong corruptive effect upon the mind of the whoever is targeted, and disenchanted it for good measure, before handing it over to you.";
		}
	},
	
	
	// Angry Harpies:
	
	HARPY_PACIFICATION_ONE(0, QuestType.SIDE, 6, 25) {
		@Override
		public String getName() {
			return "Nests in chaos";
		}

		@Override
		public String getDescription() {
			return "The Enforcer informed you that the Harpy Nests are extremely dangerous at the moment."
					+ " Upon further questioning, you discovered that there's a hefty reward for the person who's able to calm the three main matriarchs down.";
		}

		@Override
		public String getCompletedDescription() {
			return "You've managed to assume control of one of the harpy nests!";
		}
	},
	HARPY_PACIFICATION_TWO(1, QuestType.SIDE, 6, 25) {
		@Override
		public String getName() {
			return "One down, two to go";
		}

		@Override
		public String getDescription() {
			return "You've managed to reign in one of the matriarchs, but there are still two more to go!";
		}

		@Override
		public String getCompletedDescription() {
			return "You've managed to assume control of two of the harpy nests!";
		}
	},
	HARPY_PACIFICATION_THREE(2, QuestType.SIDE, 6, 25) {
		@Override
		public String getName() {
			return "One matriarch left";
		}

		@Override
		public String getDescription() {
			return "You've managed to reign in two of the matriarchs, but there's still one more to go!";
		}

		@Override
		public String getCompletedDescription() {
			return "You've managed to assume control of all three of the major harpy nests!";
		}
	},
	HARPY_PACIFICATION_REWARD(3, QuestType.SIDE, 6, 50) {
		@Override
		public String getName() {
			return "Harpy "+(Main.game.getPlayer().isFeminine()?"queen":"king");
		}

		@Override
		public String getDescription() {
			return "Return to the Enforcer post to report your success.";
		}

		@Override
		public String getCompletedDescription() {
			return "After informing the Enforcers that you've pacified all three of the major harpy nests, they resumed their regular patrols, resulting in the Harpy Nests being safe to travel through!";
		}
	};

	private int sortingOrder, level, experienceReward;
	private QuestType questType;

	private Quest(int sortingOrder, QuestType questType, int level, int experienceReward) {
		this.sortingOrder = sortingOrder;

		this.questType = questType;

		this.level = level;
		this.experienceReward = experienceReward;
	}

	public static String getLevelAndExperienceHTML(Quest q, boolean active) {
		if (active) {
			if(q.getLevel() <= Main.game.getPlayer().getLevel() - 3) {
				return "<b class='quest-extra level' style='color:"+  Colour.GENERIC_GOOD + ";'>Level " + q.getLevel()+ "</b>"
						+ "<b class='quest-extra experience' style='color:" + Colour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + q.getExperienceReward() + " xp</b>";
				
			} else if (q.getLevel() >= Main.game.getPlayer().getLevel() + 3) {
				return "<b class='quest-extra level' style='color:"+  Colour.GENERIC_BAD + ";'>Level " + q.getLevel()+ "</b>"
						+ "<b class='quest-extra experience' style='color:" + Colour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + q.getExperienceReward() + " xp</b>";
				
			} else {
				return "<b class='quest-extra level'>Level " + q.getLevel()+ "</b>"
						+ "<b class='quest-extra experience' style='color:" + Colour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + q.getExperienceReward() + " xp</b>";
			}
			
		} else {
			return "<b class='quest-extra level' style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Level " + q.getLevel() + "</b>"
					+ "<b class='quest-extra experience' style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>" + q.getExperienceReward() + " xp</b>";
		}
	}

	public abstract String getName();

	public abstract String getDescription();

	public abstract String getCompletedDescription();

	public int getSortingOrder() {
		return sortingOrder;
	}

	public int getLevel() {
		return level;
	}

	public QuestType getQuestType() {
		return questType;
	}

	public int getExperienceReward() {
		return experienceReward;
	}

}
