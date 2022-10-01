package com.lilithsthrone.game.character.quests;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.fields.Aurokaris;
import com.lilithsthrone.game.character.npc.fields.Lunexis;
import com.lilithsthrone.game.character.npc.fields.Ursa;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ.BraxOffice;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Lab;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpCitadelDialogue;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.4.4.1
 * @author Innoxia
 */
public enum Quest {
	
	
	// Main quests:

	MAIN_PROLOGUE(QuestType.MAIN, 1, 5) {
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
			return "Your evening at the museum turned out to be far more eventful than you'd have liked."
					+ " A mysterious demon named Lilith tricked you into being pulled through a magical portal and into a parallel universe."
					+ " After waking up in the middle of an unfamiliar street, you were saved from a dire situation by the half-demon 'Lilaya'."
					+ " She seems to be this universe's version of your aunt Lily, and, in return for agreeing to help her with her experiments, she's allowed you to stay at her home.";
		}
	},

	MAIN_1_A_LILAYAS_TESTS(QuestType.MAIN, 1, 10) {
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
			return "Lilaya ran some more tests on you, but she's unable to progress with her research without the help of her old colleague, Arthur.";
		}
		@Override
		public void applySkipQuestEffects() {
			((Arthur) Main.game.getNpc(Arthur.class)).generateNewTile();
		}
	},

	MAIN_1_B_DEMON_HOME(QuestType.MAIN, 1, 10) {
		@Override
		public String getName() {
			return "The search for Arthur; Demon Home";
		}
		@Override
		public String getDescription() {
			return "Lilaya has informed you that her old colleague, Arthur, would know more about the type of magic used in the portal."
					+ " However, she seems to have an intense dislike of him, and you've ended up being tasked to go and get him to apologise to Lilaya before she'll allow him to" + " come and work with her."
					+ " Arthur lives in an apartment building called 'Sawlty Towers', in the district of the city known as 'Demon Home', so you can find him there.";
		}
		@Override
		public String getCompletedDescription() {
			return "When you arrived at Arthur's home, you found that Dominion's Enforcers had arrested him on suspicion of plotting against Lilith." + " After his arrest, he was taken to the Enforcer's HQ.";
		}
		@Override
		public void applySkipQuestEffects() {
			// No effects applied
		}
	},

	MAIN_1_C_WOLFS_DEN(QuestType.MAIN, 3, 20) {
		@Override
		public String getName() {
			return "The search for Arthur; The Wolf's Den";
		}
		@Override
		public String getDescription() {
			return "Arthur has been arrested by Dominion's Enforcers, and has been taken to the Enforcer's HQ."
					+ " It looks like you'll have to inquire further there and find out a way to save Arthur.";
		}
		@Override
		public String getCompletedDescription() {
			return "You were forced to confront the Enforcer Inspector, Brax."
					+ " Thankfully, you were able to deal with him, but you then found out that Arthur has been sold into slavery!";
		}
		@Override
		public void applySkipQuestEffects() {
			BraxOffice.setBraxsPostQuestStatus(false);
			BraxOffice.givePlayerEnforcerUniform(null);
		}
	},

	MAIN_1_D_SLAVERY(QuestType.MAIN, 3, 10) {
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
			return "You found the harpy Scarlett in Slaver Alley, who turned out to be one of the rudest people you've ever met.";
		}
		@Override
		public void applySkipQuestEffects() {
			// No effects applied
		}
	},
	
	MAIN_1_E_REPORT_TO_HELENA(QuestType.MAIN, 3, 30) {
		@Override
		public String getName() {
			return "The search for Arthur; Find Helena";
		}
		@Override
		public String getDescription() {
			return "After finding Scarlett in Slaver Alley, you discovered that she's no longer in possession of Arthur."
					+ " Before she'll tell you anything about it, she wants you to go the Harpy Nests and report to her matriarch, Helena, that her business is a complete failure";
		}
		@Override
		public String getCompletedDescription() {
			return "You reported Scarlett's problems to her matriarch, Helena."
					+ " She didn't seem to have much sympathy for Scarlett, and quickly flew off to go and talk to her in person.";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
			Main.game.getNpc(Helena.class).addSlave(Main.game.getNpc(Scarlett.class));
		}
	},
	
	MAIN_1_F_SCARLETTS_FATE(QuestType.MAIN, 3, 30) {
		@Override
		public String getName() {
			return "The search for Arthur; Scarlett's fate";
		}
		@Override
		public String getDescription() {
			return "You need to travel back to Scarlett's shop to find out what's become of her. Hopefully Helena wasn't too hard on her, and she'll be willing to tell you what happened to Arthur now...";
		}
		@Override
		public String getCompletedDescription() {
			return "You travelled back to Scarlett's shop, only to discover that Helena has enslaved her!";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getNpc(Helena.class).addSlave(Main.game.getNpc(Scarlett.class));
			Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.POSITIVE_TWO_OBEDIENT.getMedianValue());
			Main.game.getNpc(Scarlett.class).resetInventory(true);
			AbstractClothing collar = Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_BLACK_STEEL, false);
			collar.setSealed(true);
			Main.game.getNpc(Scarlett.class).equipClothingFromNowhere(collar, true, Main.game.getNpc(Helena.class));
			Main.game.getNpc(Scarlett.class).equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_ballgag", PresetColour.CLOTHING_PINK, false), true, Main.game.getNpc(Helena.class));
		}
	},
	
	MAIN_1_G_SLAVERY(QuestType.MAIN, 3, 30) {
		@Override
		public String getName() {
			return "The search for Arthur; Slavery";
		}
		@Override
		public String getDescription() {
			return "Helena is willing to sell Scarlett to you, which seems to be the only way you'll get the information you need."
					+ " You'll need to have a slaver license in order to buy Scarlett.";
		}
		@Override
		public String getCompletedDescription() {
			return "Helena sold Scarlett to you, which allowed you to order Scarlett to tell you what happened to Arthur.";
		}
		@Override
		public void applySkipQuestEffects() {
			AbstractClothing ballgag = Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.MOUTH);
			if (ballgag != null) {
				ballgag.setSealed(false);
				Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(ballgag, true, Main.game.getNpc(Helena.class));
			}
			
			// Complete slavery side quest:
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_SLAVERY)) {
				Main.game.getPlayer().startQuest(QuestLine.SIDE_SLAVERY);
			}
			List<Quest> slaverSkipQuests = Util.newArrayListOfValues(
					Quest.SIDE_SLAVER_NEED_RECOMMENDATION,
					Quest.SIDE_SLAVER_RECOMMENDATION_OBTAINED,
					Quest.SIDE_UTIL_COMPLETE);
			for(int i=0; i<slaverSkipQuests.size()-1; i++) {
				Quest q = slaverSkipQuests.get(i);
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_SLAVERY)==q) {
					q.applySkipQuestEffects();
					Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLAVERY, slaverSkipQuests.get(i+1));
				}
			}
			
			Main.game.getNpc(Scarlett.class).setAffection(Main.game.getNpc(Helena.class), AffectionLevel.NEGATIVE_FIVE_LOATHE.getMedianValue());
			Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.NEGATIVE_FOUR_DEFIANT.getMedianValue());
			Main.game.getNpc(Scarlett.class).setAffection(Main.game.getPlayer(), AffectionLevel.NEGATIVE_FIVE_LOATHE.getMedianValue());
			Main.game.getPlayer().addSlave(Main.game.getNpc(Scarlett.class));
			
			Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
			
			((Zaranix) Main.game.getNpc(Zaranix.class)).generateNewTile();
		}
	},
	
	MAIN_1_H_THE_GREAT_ESCAPE(QuestType.MAIN, 10, 200) {
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
		@Override
		public void applySkipQuestEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, true);
			Main.game.getNpc(Arthur.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
		}
	},
	
	MAIN_1_I_ARTHURS_TALE(QuestType.MAIN, 1, 30) {
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
		@Override
		public void applySkipQuestEffects() {
			Cell arthurRoomCell = Lab.addArthurRoom();
			Main.game.getNpc(Arthur.class).setLocation(arthurRoomCell, true);
		}
	},
	
	// This quest is no longer used, but is left here for old version support
	MAIN_1_J_ARTHURS_ROOM(QuestType.MAIN, 1, 30) {
		@Override
		public String getName() {
			return "The search for Arthur; A room of his own";
		}
		@Override
		public String getDescription() {
			return "Lilaya really doesn't want Arthur in her lab, and has tasked you to help Rose find a suitable room for him to stay in.<br/>"
					+ "<i>Go into one of the empty rooms in Lilaya's House, and through the room management window, upgrade it to 'Arthur's Room'.</i>";
		}
		@Override
		public String getCompletedDescription() {
			return "You located a suitable room for Arthur, and, with Rose's help, moved a significant amount of arcane instrumentation into his new lab-cum-bedroom.";
		}
	},
	
	
	MAIN_2_A_INTO_THE_DEPTHS(QuestType.MAIN, 1, 10) {
		@Override
		public String getName() {
			return "Into Submission";
		}
		@Override
		public String getDescription() {
			return "Arthur was able to explain the mechanism by which you were transported into this new world, but he seemed to hold back on some of the details."
					+ " He said that he'd explain everything fully once he knew for certain what was going on, but in order for that to happen, he'll need to talk to one of the seven elder Lilin."
					+ " After much arguing, Lilaya agreed to convince her mother to help, but you'll have to be the one to deliver the message.<br/>"
					+ "<i>Travel down into the undercity of Submission and seek an audience with Lilaya's mother, Lyssieth.</i>";
		}
		@Override
		public String getCompletedDescription() {
			return "Acting on Arthur's advice, you ventured down into Submission and discovered the location of Lyssieth's palace.";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.LYSSIETHS_RING), false);
		}
	},
	
	MAIN_2_B_SIRENS_CALL(QuestType.MAIN, 25, 300) {
		@Override
		public String getName() {
			return "The Siren's Call";
		}
		@Override
		public String getDescription() {
			return "The guards at the gate to Lyssieth's palace told you that she's not receiving any visitors at the moment."
					+ " The only way to gain an audience with her would be to take care of her troublesome daughter; 'The Dark Siren'."
					+ " She's currently living in a stone fortress in one of the central tunnels here in Submission, from which she sends out gangs of imps to terrorise innocent citizens.</br>"
					+ "If you're able to enslave her, either through combat or trickery, you'll have earned an audience with Lyssieth.";
		}
		@Override
		public String getCompletedDescription() {
			return "You managed to enslave Lyssieth's troublesome daughter, and so earned an audience with her!";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.LYSSIETHS_RING));
			ImpCitadelDialogue.clearFortress(false);
			// Set tunnels to be cleared manually, as they haven't been cleared when skipping quests:
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaDefeated, true);
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
				if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_ALPHA)) {
					character.returnToHome();
				}
			}
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesDefeated, true);
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
				if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_FEMALES)) {
					character.returnToHome();
				}
			}
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesDefeated, true);
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
				if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_MALES)) {
					character.returnToHome();
				}
			}
		}
	},
	
	MAIN_2_C_SIRENS_FALL(QuestType.MAIN, 1, 10) {
		@Override
		public String getName() {
			return "The Siren's Fall";
		}

		@Override
		public String getDescription() {
			return "Return to Lyssieth's palace and report to the guards that you've enslaved 'The Dark Siren'."
					+ " This should be enough to grant you an audience with Lyssieth.";
		}

		@Override
		public String getCompletedDescription() {
			return "For enslaving 'The Dark Siren', the guards at the gate to Lyssieth's palace gave you permission to enter and seek out an audience with her.";
		}
		@Override
		public void applySkipQuestEffects() {
			if(Main.game.getPlayer().hasItemType(ItemType.LYSSIETHS_RING)) {
				Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.LYSSIETHS_RING));
			}
			if(!Main.game.getPlayer().hasClothingType(ClothingType.FINGER_LYSSIETHS_RING, true)) {
				Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing(ClothingType.FINGER_LYSSIETHS_RING), false);
			}
		}
	},
	
	MAIN_2_D_MEETING_A_LILIN(QuestType.MAIN, 1, 100) {
		@Override
		public String getName() {
			return "Meeting A Lilin";
		}

		@Override
		public String getDescription() {
			return "Travel to Lyssieth's throne room and try to finally get some answers about why you're here and how you can return to your old world.";
		}

		@Override
		public String getCompletedDescription() {
			return "Lyssieth revealed that this world is in fact your own, and that Lilith transformed it into a different reality when she was released from the mirror.";
		}
		@Override
		public void applySkipQuestEffects() {
			((DarkSiren)Main.game.getNpc(DarkSiren.class)).postDefeatReset();
			AbstractItemEffectType.getBookEffect(Main.game.getPlayer(), Subspecies.LILIN, null, false);
			Main.game.getNpc(Lyssieth.class).incrementAffection(Main.game.getPlayer(), 25);
			Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 10);
			Main.game.getNpc(DarkSiren.class).incrementAffection(Main.game.getPlayer(), 10);
			Main.game.getNpc(Arthur.class).incrementAffection(Main.game.getPlayer(), 10);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.firstReactionLiberate, true);
			if(Main.game.getNpc(DarkSiren.class).getAffection(Main.game.getPlayer())<0) {
				Main.game.getNpc(DarkSiren.class).setAffection(Main.game.getPlayer(), 0);
			}
		}
	},
	
	MAIN_3_ELIS(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "Destination Elis";
		}

		@Override
		public String getDescription() {
			return "Lyssieth told you that in order to defeat the elder lilin pegataur, Lunette, you'll need to obtain the help of Minotallys; the lilin who rules the town of Elis."
					+ " Meraxis additionally mentioned enlisting the aid of the youko, but that can wait until after you've arrived in Elis itself.";
		}
		@Override
		public String getCompletedDescription() {
			return "Lyssieth told you that in order to defeat the elder lilin pegataur, Lunette, you'll need to obtain the help of Minotallys; the lilin who rules the town of Elis."
					+ " To this end, you left Dominion for the first time...";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_B_MEETING_MERAXIS(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "To The Red Dragon";
		}

		@Override
		public String getDescription() {
			return "As you left Dominion, you were approached by Meraxis, who told you to meet her in the tavern 'The Red Dragon' at Elis, which is apparently near to the town's eastern gate."
					+ " Meraxis also said that she'll set up a meeting with Minotallys when you arrive, and that she'll have secured accommodation for you.";
		}

		@Override
		public String getCompletedDescription() {
			return "You met Meraxis in 'The Red Dragon' tavern, where she's managed to secure accommodation for you in the form of a rented room on the tavern's first floor.";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_C_MEETING_MINOTALLYS(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "Meeting Minotallys";
		}

		@Override
		public String getDescription() {
			return "Meraxis has arranged for you to meet with Minotallys concerning the threat of Lunette to the town of Elis."
					+ " Tell Meraxis that you're ready for the meeting at any time between [units.time(9)]-[units.time(18)].";
		}

		@Override
		public String getCompletedDescription() {
			return "You and Meraxis travelled to Elis's town hall to meet Minotallys, where you also met with her personal assistant, Arion."
					+ " Minotallys is in a state of denial about the current state of affairs in the Foloi Fields, and stated that she would only consider taking action if the town of Themiscyra was somehow being threatened.";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_D_TO_THEMISCYRA(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "To Themiscyra";
		}

		@Override
		public String getDescription() {
			return "You agreed to travel to Themiscyra with Meraxis and find out whether or not the town is being threatened by Lunette's army.";
		}

		@Override
		public String getCompletedDescription() {
			return "You travelled to Themiscyra with Meraxis, but upon reaching the town, you discovered that it was being destroyed by Lunette's army!";
		}
		@Override
		public void applySkipQuestEffects() {
			if(Main.game.getWorlds().get(WorldType.WORLD_MAP).getCell(PlaceType.getPlaceTypeFromId("innoxia_fields_themiscyra"))==null) {
				Main.game.getWorlds().get(WorldType.WORLD_MAP).getCell(11, 32).getPlace().setPlaceType(PlaceType.getPlaceTypeFromId("innoxia_fields_themiscyra"));
			}
		}
	},
	
	MAIN_3_E_THEMISCYRA_ATTACK(QuestType.MAIN, 1, 250) {
		@Override
		public String getName() {
			return "Save the Queen";
		}
		@Override
		public String getDescription() {
			return "Separated from Meraxis, you've teamed up with an Amazon cow-girl named Aurokaris."
					+ " You need to travel through Themiscyra and find both Meraxis and Ursa, the queen of the Amazons, who should be at the palace.";
		}
		@Override
		public String getCompletedDescription() {
			return "Alongside Aurokaris, you travelled through Themiscyra and found both Meraxis and Ursa at the plaza before the palace."
					+ " After encountering Lunexis, the leader of Lunette's army, Meraxis teleported the five of you back to Elis's town hall, where Minotallys was finally convinced that Lunette is a threat to Elis.";
		}
		@Override
		public void applySkipQuestEffects() {
			if(Main.game.getWorlds().get(WorldType.getWorldTypeFromId("innoxia_fields_elis_town")).getCell(PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_amazon_camp"))==null) {
				Main.game.getWorlds().get(WorldType.getWorldTypeFromId("innoxia_fields_elis_town")).getCell(10, 20).getPlace().setPlaceType(PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_amazon_camp"));
			}
			Main.game.getNpc(Ursa.class).setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_town"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_amazon_camp"), true);
			Main.game.getNpc(Aurokaris.class).setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_town"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_amazon_camp"), true);
			Main.game.getNpc(Lunexis.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, true);
		}
	},
	
	MAIN_3_F_PREPARING_ELIS(QuestType.MAIN, 1, 25) {//TODO
		@Override
		public String getName() {
			return "Defence of Elis";
		}

		@Override
		public String getDescription() {
			return "[style.italicsMinorBad(This is where the main quest currently ends, but more main quest content will be coming in future updates!)]<br/>"
					+ "With Lunette planning to attack Elis at some point in the near future, you've been tasked by Minotallys with helping get the town's defences in order."
					+ " You need to travel to the Enforcer station in Elis and ask for their assistance.";
		}

		@Override
		public String getCompletedDescription() {
			return "-";
		}
	},
	
	MAIN_3_G_TODO(QuestType.MAIN, 1, 25) {//TODO
		@Override
		public String getName() {
			return "";
		}

		@Override
		public String getDescription() {
			return "";
		}

		@Override
		public String getCompletedDescription() {
			return "-";
		}
	},
	

	// Side Quests:

	SIDE_UTIL_COMPLETE(QuestType.SIDE, 1, 0) {
		@Override
		public String getName() {
			return "Quest Complete!";
		}

		@Override
		public String getDescription() {
			return "Quest complete!";
		}

		@Override
		public String getCompletedDescription() {
			return "Quest complete!";
		}
	},
	
	SIDE_DISCOVER_ALL_ITEMS(QuestType.SIDE, 1, 100) {
		@Override
		public String getName() {
			return "Completionist";
		}

		@Override
		public String getDescription() {
			return "There are a lot of curious items in this new world. You wonder if you can find them all...";
		}

		@Override
		public String getCompletedDescription() {
			return "You have discovered every item there is to find!";
		}
	},

	SIDE_DISCOVER_ALL_RACES(QuestType.SIDE, 1, 100) {
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
	
	
	// For when you discover your first essence:
	
	SIDE_ENCHANTMENTS_LILAYA_HELP(QuestType.SIDE, 1, 10) {
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

	// For the first time you get pregnant:
	
	SIDE_PREGNANCY_CONSULT_LILAYA(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Lilaya knows best";
		}

		@Override
		public String getDescription() {
			return "There's no way... You're pregnant? You're <b>pregnant</b>! Surely Lilaya will know what to do?!";
		}

		@Override
		public String getCompletedDescription() {
			return "Lilaya managed to calm you down, and reassured you that pregnancy in this world isn't as big a deal as it was back home.";
		}
	},
	
	SIDE_PREGNANCY_LILAYA_THE_MIDWIFE(QuestType.SIDE, 1, 20) {
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
	
	// When getting eggs implanted in you for the first time:
	
	SIDE_INCUBATION_WAITING(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Living Incubator";
		}
		@Override
		public String getDescription() {
			return "There's no doubt about it; you've had a clutch of eggs implanted inside of your body! There's nothing else for you to do except wait for them to mature and then lay them...";
		}
		@Override
		public String getCompletedDescription() {
			return "You waited for the eggs implanted inside of your body to mature, and then successfully laid and hatched them!";
		}
	},
	
//	SIDE_INCUBATION_LILAYA_HELP(QuestType.SIDE, 1, 20) {
//		@Override
//		public String getName() {
//			return "Egg-laying assistance";
//		}
//		@Override
//		public String getDescription() {
//			return "Lilaya said that she'd be able to help you lay your eggs whenever you're ready. You're going to need to wait until they're ready to be hatched, then you can go and see Lilaya to lay them.";
//		}
//		@Override
//		public String getCompletedDescription() {
//			return "Lilaya helped you to lay your eggs. She said that if ever you get implanted with eggs again, she can always help out.";
//		}
//	},
	
	// Getting a slaver license:
	
	SIDE_SLAVER_NEED_RECOMMENDATION(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Letter of recommendation";
		}

		@Override
		public String getDescription() {
			return "After asking how to obtain a slaver license at the Slavery Administration building, you discovered that you'll need a letter of recommendation first. Lilaya should be able to help with that.";
		}

		@Override
		public String getCompletedDescription() {
			return "Lilaya gave you a letter of recommendation, and what's more, she also offered to let you house your slaves in her mansion.";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getDialogueFlags().values.add(DialogueFlagValue.finchIntroduced);
		}
	},
	
	SIDE_SLAVER_RECOMMENDATION_OBTAINED(QuestType.SIDE, 1, 10) {
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
			return "You presented the letter of recommendation to [finch.name], and after paying the fee, you obtained a slaver license!";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.SLAVER_LICENSE), false));
		}
	},
	
	// Accommodation:
	
	SIDE_ACCOMMODATION_NEED_LILAYAS_PERMISSION(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Lilaya the Landlady";
		}

		@Override
		public String getDescription() {
			return "Lilaya's mansion is full of empty rooms which could be put to use as guest accommodation. You should ask her if you could use them to house your friends and family.";
		}

		@Override
		public String getCompletedDescription() {
			return "Lilaya gave you permission to use the empty rooms to house your friends and family, on the condition that you pay for the expenses that are incurred.";
		}
	},
	
	// Other:
	
	SIDE_HYPNO_WATCH_VICKY(QuestType.SIDE, 1, 10) {
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
	
	SIDE_HYPNO_WATCH_TEST_SUBJECT(QuestType.SIDE, 1, 10) {
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
	
	
	LIGHTNING_SPELL_1_PAYMENT(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "The Price Of Power";
		}
		@Override
		public String getDescription() {
			return "After showing Arthur the arcane lightning globe which you found in the Enforcer warehouse, you were told that it would be possible to learn the secrets of the lightning spells within it."
					+ " Although the resulting spells will be more powerful than what the globe is capable of,"
						+ " Arthur has explained to you that such an extraction will not only require an immense amount of arcane essences, but will also permanently remove the globe's innate spells."
					+ "<br/>When you're willing and able to do so, give Arthur the arcane lightning globe and let him extract 500 arcane essences from your aura.";
		}
		@Override
		public String getCompletedDescription() {
			return "You gave Arthur the arcane lightning globe which you found in the Enforcer warehouse, and also let him extract 500 arcane essences from your aura."
					+ " In exchange, you've been promised that a powerful arcane lightning spell shall soon be yours.";
		}
	},
	
	LIGHTNING_SPELL_2_WAITING(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Lightning Extraction";
		}
		@Override
		public String getDescription() {
			return "Arthur informed you that it will take some time to extract the secrets of the arcane lightning from within the globe."
					+ " You'll have to return to him after two weeks to find out what the results of his research are...";
		}
		@Override
		public String getCompletedDescription() {
			return "Arthur excitedly informed you that he'd not only been able to extract the secrets of two arcane lightning spells from the globe,"
					+ " but that he'd also been able to transfer its remaining power into a smaller crystal, which he's had fitted onto a ring for you.";
		}
	},
	
	
	
	// Angry Harpies:
	
	HARPY_PACIFICATION_ONE(QuestType.SIDE, 6, 25) {
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
	HARPY_PACIFICATION_TWO(QuestType.SIDE, 6, 25) {
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
	HARPY_PACIFICATION_THREE(QuestType.SIDE, 6, 25) {
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
	HARPY_PACIFICATION_REWARD(QuestType.SIDE, 6, 50) {
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
	},
	
	
	
	// Slime Queen:
	
	SLIME_QUEEN_ONE(QuestType.SIDE, 10, 25) {
		@Override
		public String getName() {
			return "Troublesome Slimes";
		}

		@Override
		public String getDescription() {
			return "When you first arrived in Submission, an Enforcer informed you of an ongoing situation in the tunnels."
					+ " Apparently, there are an increasing number of Slimes who are attacking innocent travellers and transforming them into more Slimes."
					+ " If you're able to offer any information on where these aggressive Slimes are coming from, you could earn a one-thousand flame reward.";
		}

		@Override
		public String getCompletedDescription() {
			return "One of the Slimes that you encountered in the tunnels told you that they'd been given orders to transform people by a certain 'Slime Queen'.";
		}
	},
	
	SLIME_QUEEN_TWO(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Report Back";
		}

		@Override
		public String getDescription() {
			return "You should report back to one of Submission's Enforcer Posts with this information regarding a 'Slime Queen'.";
		}

		@Override
		public String getCompletedDescription() {
			return "The Enforcer that you reported to told you that they'd heard rumours of a Slime Queen before, but no trace of any such thing has ever been found in Submission."
					+ " They suggested that you could take a look down in the Bat Caverns, and offered you a further twenty-thousand flames if you could find this Queen and put an end to her scheming.";
		}
	},
	
	SLIME_QUEEN_THREE(QuestType.SIDE, 15, 25) {
		@Override
		public String getName() {
			return "Finding the Slime Queen";
		}

		@Override
		public String getDescription() {
			return "Travel down into the Bat Caverns and search for the rumoured Slime Queen.";
		}

		@Override
		public String getCompletedDescription() {
			return "In the middle of Slime Lake, you discovered the Slime Queen's lair!";
		}
	},
	
	SLIME_QUEEN_FOUR(QuestType.SIDE, 20, 50) {
		@Override
		public String getName() {
			return "Confront the Queen";
		}

		@Override
		public String getDescription() {
			return "Travel up the tower and find the Slime Queen.";
		}

		@Override
		public String getCompletedDescription() {
			return "You found the Slime Queen at the top of the tower.";
		}
	},
	
	SLIME_QUEEN_FIVE_SUBMIT(QuestType.SIDE, 1, 25) {
		@Override
		public String getName() {
			return "Help the Queen";
		}

		@Override
		public String getDescription() {
			return "You decided to help the Slime Queen with her plans of transforming Submission's population into slimes.";
		}

		@Override
		public String getCompletedDescription() {
			return "You decided to help the Slime Queen with her plans, and agreed to trick the Enforcers into believing that she's no longer a threat!";
		}
	},
	
	SLIME_QUEEN_SIX_SUBMIT(QuestType.SIDE, 1, 200) {
		@Override
		public String getName() {
			return "Final Report";
		}

		@Override
		public String getDescription() {
			return "Report back to Claire and tell her that the Slime Queen will no longer be a problem.";
		}

		@Override
		public String getCompletedDescription() {
			return "You told Claire that the Slime Queen is no longer a threat, and received the twenty-thousand flame reward."
				+ " With your Queen now safe from Enforcer investigation, it's only a matter of time before all of Submission is a Slime paradise!";
		}
	},
	
	SLIME_QUEEN_FIVE_CONVINCE(QuestType.SIDE, 1, 25) {
		@Override
		public String getName() {
			return "Convince the Queen";
		}

		@Override
		public String getDescription() {
			return "You decide to convince the Slime Queen to abandon her plans.";
		}

		@Override
		public String getCompletedDescription() {
			return "You convinced Catherine to abandon her plan of transforming everyone in Submission into slimes.";
		}
	},
	
	SLIME_QUEEN_SIX_CONVINCE(QuestType.SIDE, 1, 200) {
		@Override
		public String getName() {
			return "Final Report";
		}

		@Override
		public String getDescription() {
			return "Report back to Claire and tell her that the Slime Queen will no longer be a problem.";
		}

		@Override
		public String getCompletedDescription() {
			return "You told Claire that the Slime Queen is no longer a threat, and received the twenty-thousand flame reward.";
		}
	},
	
	SLIME_QUEEN_FIVE_FORCE(QuestType.SIDE, 1, 25) {
		@Override
		public String getName() {
			return "Force the Queen";
		}

		@Override
		public String getDescription() {
			return "Force the Slime Queen to abandon her plans.";
		}

		@Override
		public String getCompletedDescription() {
			return "You forced Catherine to abandon her plan of transforming everyone in Submission into slimes.";
		}
	},
	
	SLIME_QUEEN_SIX_FORCE(QuestType.SIDE, 1, 200) {
		@Override
		public String getName() {
			return "Final Report";
		}

		@Override
		public String getDescription() {
			return "Report back to Claire and tell her that the Slime Queen will no longer be a problem.";
		}

		@Override
		public String getCompletedDescription() {
			return "You told Claire that the Slime Queen is no longer a threat, and received the twenty-thousand flame reward.";
		}
	},
	
	
	// Teleporting:
	
	TELEPORTING_START(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Escape from the warehouse";
		}

		@Override
		public String getDescription() {
			return "Having accidentally teleported into the 'SWORD' Enforcer division's storage warehouse, both you and Claire now need to avoid detection and make good your escape.";
		}

		@Override
		public String getCompletedDescription() {
			return "You and Claire managed to escape from SWORD's warehouse.";
		}
	},

	TELEPORTING_CAUGHT(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Endure the stocks";
		}

		@Override
		public String getDescription() {
			if(Main.game.isNonConEnabled()) {
				return "Having been defeated by the Enforcers inside SWORD's warehouse, you've been sentenced to be locked into the public use stocks at slaver alley. Endure this ordeal until Claire comes to rescue you...";
			} else {
				return "Having been defeated by the Enforcers inside SWORD's warehouse, you've been locked into the cells at Enforcer Headquarters. You'll have to wait until Claire comes to rescue you...";
			}
		}

		@Override
		public String getCompletedDescription() {
			if(Main.game.isNonConEnabled()) {
				return "Having been defeated by the Enforcers inside SWORD's warehouse, you had to endure being locked into the public use stocks at slaver alley for several hours before Claire turned up to release you.";
			} else {
				return "Having been defeated by the Enforcers inside SWORD's warehouse, you had to endure being locked into the cells at Enforcer Headquarters for a couple of hours before Claire turned up to release you.";
			}
		}
	},
	
	
	// Daddy:
	
	DADDY_START(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Meeting [daddy.name]";
		}

		@Override
		public String getDescription() {
			return "Rose has asked you to pay [daddy.name] a visit in order to convince [daddy.herHim] to leave Lilaya in peace."
					+ " ([daddy.He] is only available at [daddy.his] apartment in Demon Home between "+Units.time(LocalTime.of(18, 00))+" and "+Units.time(LocalTime.of(21, 00))+".)";
		}

		@Override
		public String getCompletedDescription() {
			return "At Rose's behest, you met with [daddy.name] at [daddy.her] apartment in Demon Home.";
		}
	},
	
	DADDY_MEETING(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Dinner with [daddy.name]";
		}

		@Override
		public String getDescription() {
			return "[daddy.Name] is insisting upon explaining [daddy.her] motives over dinner. You're going to have to either accept [daddy.her] offer, or bluntly refuse [daddy.herHim] and insist that [daddy.she] leave Lilaya alone.";
		}

		@Override
		public String getCompletedDescription() {
			return "You gave [daddy.name] your answer in response to [daddy.her] request to take you out for dinner.";
		}
	},
	
	DADDY_REFUSED(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "[daddy.Name] denied";
		}

		@Override
		public String getDescription() {
			return "You told [daddy.name] that you aren't at all interested in going out for a meal with [daddy.herHim], and that [daddy.sheIs] never to bother Lilaya again.";
		}

		@Override
		public String getCompletedDescription() {
			return "You told [daddy.name] that you aren't at all interested in going out for a meal with [daddy.herHim], and that [daddy.sheIs] never to bother Lilaya again.";
		}
	},
	
	DADDY_REFUSED_2(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "[daddy.Name] denied";
		}

		@Override
		public String getDescription() {
			return "You told [daddy.name] that you aren't interested in convincing Lilaya to meet with [daddy.herHim], and that [daddy.she] should never bother your [lilaya.relation(pc)] again.";
		}

		@Override
		public String getCompletedDescription() {
			return "You told [daddy.name] that you aren't interested in convincing Lilaya to meet with [daddy.herHim], and that [daddy.she] should never bother your [lilaya.relation(pc)] again.";
		}
	},
	
	DADDY_ACCEPTED(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Convincing Lilaya";
		}

		@Override
		public String getDescription() {
			return "You agreed to convince Lilaya to meet with [daddy.name] for dinner, and to then help persuade her to ask Lyssieth to meet with [daddy.herHim].";
		}

		@Override
		public String getCompletedDescription() {
			return "You convinced Lilaya to meet with [daddy.name] for dinner, on the condition that you go with her. ";
		}
	},
	
	DADDY_LILAYA_MEETING(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Lilaya's date with [daddy.name]";
		}

		@Override
		public String getDescription() {
			return "Lilaya has agreed to go out for dinner with [daddy.name], so now you just need to accompany her and make sure the evening goes smoothly.";
		}

		@Override
		public String getCompletedDescription() {
			return "You went with Lilaya to meet [daddy.name] for dinner, and while she had bad news for [daddy.herHim] regarding Lyssieth's romantic preferences, she did seem to like [daddy.herHim] well enough...";
		}
	},
	
	
	// Buying Brax:
	
	BUYING_BRAX_START(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Perfume collection";
		}

		@Override
		public String getDescription() {
			return "Candi said that she'd consider selling [brax.name] to you, but before she gives you a definite answer, she wants you to go and fetch her order of perfume from the shop 'Succubi's Secrets' in the shopping arcade.";
		}

		@Override
		public String getCompletedDescription() {
			return "You both paid for and collected Candi's order of perfume from Succubi's Secrets.";
		}
	},
	
	BUYING_BRAX_DELIVER_PERFUME(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Perfume delivery";
		}

		@Override
		public String getDescription() {
			return "Now that you've collected the bottles of perfume, you need to deliver them to Candi back at the Enforcer headquarters.";
		}

		@Override
		public String getCompletedDescription() {
			return "You delivered Candi's bottles of perfume to her.";
		}
	},
	
	BUYING_BRAX_LOLLIPOPS(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Lollipop contraband";
		}

		@Override
		public String getDescription() {
			return "Candi said she'd be willing to sell Brax to you, but she needs to think of how much [brax.sheIs] worth."
					+ " She said that she'll have a price for you after you've fetched a box of contraband lollipops from the Harpy Nests' Enforcer checkpoint.";
		}

		@Override
		public String getCompletedDescription() {
			return "You retrieved the lollipops from the Harpy Nests' Enforcer checkpoint.";
		}
	},
	
	BUYING_BRAX_DELIVER_LOLLIPOPS(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Lollipops for Candi";
		}

		@Override
		public String getDescription() {
			return "Now that the box of contraband lollipops are in your possession, you need to return them to Candi back at the Enforcer headquarters.";
		}

		@Override
		public String getCompletedDescription() {
			return "You delivered the box of contraband lollipops to Candi, who seemed to pay no attention to the warnings stamped all over it.";
		}
	},
	
	BUYING_BRAX_LIPSTICK(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "A wolf's weight in lipstick";
		}

		@Override
		public String getDescription() {
			return "Candi said that Brax is far too precious to be sold for mere flames, but she'd be willing to give [brax.herHim] to you in exchange for something just as valuable; a box of limited-edition lipsticks, branded as 'A Hundred Kisses'."
					+ " Apparently, Candi has discovered the whereabouts of one of the only boxes left for sale, which is at a store in the shopping arcade called 'Ralph's Snacks'.";
		}

		@Override
		public String getCompletedDescription() {
			return "You retrieved the box of 'A Hundred Kisses' from Ralph.";
		}
	},
	
	BUYING_BRAX_DELIVER_LIPSTICK(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "At the wolf's door";
		}

		@Override
		public String getDescription() {
			return "Now that you've secured the box of 'A Hundred Kisses', all that's left to do is deliver it to Candi in exchange for ownership of [brax.name].";
		}

		@Override
		public String getCompletedDescription() {
			return "You handed over the box of 'A Hundred Kisses' to Candi, and finally gained your prize; ownership of [brax.name].";
		}
	},

	
	// Vengar:
	
	VENGAR_START(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Rat Warrens";
		}
		@Override
		public String getDescription() {
			return "You agreed to help Axel deal with Vengar, who's apparently the leader of Submission's largest and most dangerous gang. You could head directly to his hideout, the 'Rat Warrens', or first go and ask Claire for help.";
		}
		@Override
		public String getCompletedDescription() {
			return "By using the password Axel gave to you, you were able to gain entry to Vengar's hideout; the Rat Warrens.";
		}
	},
	
	VENGAR_ONE(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Meeting Vengar";
		}
		@Override
		public String getDescription() {
			return "In order to find Vengar, you need to locate the main hall and be there between the hours of "+Units.time(LocalDateTime.of(1, 1, 1, 6, 0))+" and "+Units.time(LocalDateTime.of(1, 1, 1, 22, 0))+".";
		}
		@Override
		public String getCompletedDescription() {
			return "You found Vengar sitting on a throne in the main hall, and after approaching him, you were given the choice to either join his gang, or be set upon by his rat-girl bodyguards.";
		}
	},
	
	VENGAR_TWO_CONFLICT(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Conflict";
		}
		@Override
		public String getDescription() {
			return "Having chosen to challenge Vengar, you now need to defeat him in combat in order to assert dominance over his gang.";
		}
		@Override
		public String getCompletedDescription() {
			return "You managed to defeat Vengar, but before you could take any further action, SWORD started their raid on the Rat Warrens.";
		}
	},
	
	VENGAR_TWO_COOPERATION(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Axel's submission";
		}
		@Override
		public String getDescription() {
			return "Vengar revealed that he's been wanting to focus on his legitimate rum distillery business for some time, but he can't just leave Axel alone without losing respect from his gang."
					+ " In exchange for an end to the extortion, you agreed to persuade Axel to come and show his submission to Vengar.";
		}
		@Override
		public String getCompletedDescription() {
			return "You managed to convince Axel to go to the Rat Warrens and show his submission to Vengar."
					+ " Accompanying him there, you were able to provide some input and influence what happened to the alligator-boy.";
		}
	},
	
	VENGAR_TWO_ENFORCERS(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Raid";
		}
		@Override
		public String getDescription() {
			return "Having confirmed that Vengar is in the Rat Warrens, you've activated the resonance stone, signalling to the waiting SWORD Enforcers to start their raid.";
		}
		@Override
		public String getCompletedDescription() {
			return "The SWORD Enforcers successfully raided the Rat Warrens, and were able to apprehend Vengar.";
		}
	},
	
	VENGAR_THREE_COOPERATION_END(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Vengar's End";
		}
		@Override
		public String getDescription() {
			return "Now that [axel.name] has shown [axel.her] submission to Vengar, all that's left to do is head back to the Gambling Den...";
		}
		@Override
		public String getCompletedDescription() {
			return "After [axel.name] had shown [axel.her] submission to Vengar, a group of SWORD Enforcers turned up to raid the Rat Warrens and arrest the rat-boy!";
		}
	},

	VENGAR_THREE_END(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Return to Axel";
		}
		@Override
		public String getDescription() {
			return "Now that Vengar has been dealt with, you need to return to Axel and let him know what happened.";
		}
		@Override
		public String getCompletedDescription() {
			return "You returned to Axel and told him what the situation will be from now on.";
		}
	},
	
	VENGAR_OPTIONAL_CLAIRE(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Claire's Help";
		}
		@Override
		public String getDescription() {
			return "Deciding that it would be best to let Claire know of the situation, you asked her if there was anything the Enforcers could do to help."
					+ " Apparently, a SWORD team is already prepared to raid the Rat Warrens, but they need to know Vengar is inside before launching their assault."
					+ " Claire gave you a resonance stone to activate if you want them to back you up once inside.";
		}
		@Override
		public String getCompletedDescription() {
			return "Deciding that it would be best to let Claire know of the situation, you asked her if there was anything the Enforcers could do to help."
					+ " Apparently, a SWORD team is already prepared to raid the Rat Warrens, but they need to know Vengar is inside before launching their assault."
					+ " Claire gave you a resonance stone to activate if you want them to back you up once inside.";
		}
	},

	// Wes:

	WES_FAIL(QuestType.SIDE, 1, 0) {
		@Override
		public String getName() {
			return "Opportunity Missed";
		}
		@Override
		public String getDescription() {
			return "After you'd told Wesley that you had no intention of helping him with his investigation, the fox-boy disappeared, and you can be sure that he'll never try to seek out your help again...";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},
	
	WES_START(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Help Needed";
		}
		@Override
		public String getDescription() {
			return "While travelling through Dominion, you were approached by a mysterious undercover SWORD Enforcer who asked for your help."
					+ " He'd like you to meet him outside of the Shopping Arcade's antiques shop between [units.time(13)]-[units.time(14)].";
		}
		@Override
		public String getCompletedDescription() {
			return "You agreed to help Wesley investigate his superior officer.";
		}
	},

	WES_1(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Finding Elle";
		}
		@Override
		public String getDescription() {
			return "Wes told you that his superior officer, [elle.name] (or 'Elle' for short), takes night vision goggles with her while taking suspiciously long lunches on Wednesdays, and always comes back with wet shoes."
					+ " You need to find her and record any incriminating evidence on the arcane recording device Wes gave to you..."
					+ "<br/>She takes [style.colourOrange(unusually long lunch breaks)]."
					+ "<br/>She only does this on a [style.colourOrange(Wednesday)]."
					+ "<br/>She takes a set of [style.colourOrange(night vision goggles)] with her."
					+ "<br/>Afterwards, her [style.colourOrange(shoes are often wet)] and sometimes have trace amounts of some kind of [style.colourOrange(glowing residue on them)]."
					+ "<br/>[style.italicsMinorGood(For a hint on where to go, ask Lilaya about it.)]";
		}
		@Override
		public String getCompletedDescription() {
			return "Having deduced that Elle was doing her shady business down in the Bat Caverns, you managed to collect evidence of her selling weapons to a dangerous criminal gang.";
		}
	},

	WES_2(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Using the Evidence";
		}
		@Override
		public String getDescription() {
			return "With your arcane recording device now containing incriminating evidence of Elle's corruption, you're left with two possible choices of what to do with it."
					+ " You could hand it in as an anonymous tip to either Claire or Candi, or if you wanted to betray Wes and side with Elle,"
						+ " you could wait outside the Enforcer HQ between [units.time(16)]-[units.time(18)] and reveal everything to the [elle.race] as she leaves work.";
		}
		@Override
		public String getCompletedDescription() {
			return "You made use of the evidence which you collected in order to bring an end to this whole business with Wes and Elle.";
		}
	},

	WES_3_WES(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Sided with Wes";
		}
		@Override
		public String getDescription() {
			return "You decided to do as Wes asked and hand in the incriminating evidence as an anonymous tip to the Enforcers."
					+ " You should wait at least one week for things to be settled, then ask to see Wes up in the Enforcer HQ between [units.time(9)]-[units.time(17)].";
		}
		@Override
		public String getCompletedDescription() {
			return "You sided with Wes, and after anonymously handing in the incriminating evidence, you met the fox-boy in the Enforcer HQ to discover that he's been promoted to take Elle's job."
					+ " With his new authority, he's granted you access to the Enforcer HQ's requisitions area as a reward for your help."
					+ " You can also now freely visit Wes in the Enforcer HQ between the hours of [units.time(9)]-[units.time(17)].";
		}
	},

	WES_3_ELLE(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Sided with Elle";
		}
		@Override
		public String getDescription() {
			return "You decided to betray Wes and reveal everything to Elle."
					+ " Thankful for your unexpected support, the [elle.race] promised to reward you if you return to the Enforcer HQ after at least a week has passed...";
		}
		@Override
		public String getCompletedDescription() {
			return "You sided with Elle, and after returning to meet with her at the Enforcer HQ, you discovered that Wes has been enslaved."
					+ " Thankful for your support, the [elle.race] has granted you access to the Enforcer HQ's requisitions area."
					+ " You can also now freely visit Elle (and Wes) in the Enforcer HQ between the hours of [units.time(9)]-[units.time(17)].";
		}
	},

	
	// Rebel Base for HLF Quest
	
	REBEL_BASE_HANDLE_REFUSED(QuestType.SIDE,
			15,
			5) {
		@Override
		public String getName() {
			return "Hit The Lever!";
		}
		@Override
		public String getDescription() {
			return "You found a strange handle in the Bat Caverns. Who knows what it's for or what it does.";
		}
		@Override
		public String getCompletedDescription() {
			return "You pulled the handle against your better judement.";
		}
	},

	REBEL_BASE_PASSWORD_PART_ONE(QuestType.SIDE,
			15,
			5) {
		@Override
		public String getName() {
			return "Pull Handle, Receive Mystery";
		}
		@Override
		public String getDescription() {
			return "The strange handle asked for some kind of password that you don't have. Perhaps a bit of searching in the vicinity will reveal some clues.";
		}
		@Override
		public String getCompletedDescription() {
			return "You found half of a journal page that mentioned that the password is two words. You could only decipher one word, the other got torn away.";
		}
	},

	REBEL_BASE_PASSWORD_PART_TWO(QuestType.SIDE,
			15,
			5) {
		@Override
		public String getName() {
			return "Fill In The Blank";
		}
		@Override
		public String getDescription() {
			return "The other half of the password must be on the other half of the journal page. Perhaps it can still be found nearby.";
		}
		@Override
		public String getCompletedDescription() {
			return "You found both halves of the password, together they make the phrase 'RUAT CAELUM'";
		}
	},

	REBEL_BASE_PASSWORD_COMPLETE(QuestType.SIDE,
			15,
			5) {
		@Override
		public String getName() {
			return "Open Says Me";
		}
		@Override
		public String getDescription() {
			return "With the completed password, you can now give the handle's challenge another try.";
		}
		@Override
		public String getCompletedDescription() {
			return "You discovered that the handle was actually attached to a door that led to a hidden cave splitting off from the Bat Caverns.";
		}
	},

	REBEL_BASE_EXPLORATION(QuestType.SIDE,
			15,
			5) {
		@Override
		public String getName() {
			return "Going Spelunking";
		}
		@Override
		public String getDescription() {
			return "No telling what the hidden cave is for or where it goes. Perhaps you can find some answers inside.";
		}
		@Override
		public String getCompletedDescription() {
			return "You found out that the hidden cave was a hideout for a long gone rebel group. From the looks of things, they didn't win.";
		}
	},

	REBEL_BASE_ESCAPE(QuestType.SIDE,
			15,
			100) {
		@Override
		public String getName() {
			return "Getting Away With It";
		}
		@Override
		public String getDescription() {
			return "Time to make your escape, preferably before this cave collapses in on itself.";
		}
		@Override
		public String getCompletedDescription() {
			return "You managed to get out in one piece. Whatever else that cave held is now buried forever.";
		}
	},

	REBEL_BASE_FAILED(QuestType.SIDE,
			15,
			0) {
		@Override
		public String getName() {
			return "Cold Feet";
		}
		@Override
		public String getDescription() {
			return "You managed to get out in one piece, but whatever secrets that cave held are now buried forever.";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},

	REBEL_BASE_FIREBOMBS_START(QuestType.SIDE,
			1,
			5) {
		@Override
		public String getName() {
			return "Breaking Bombs";
		}
		@Override
		public String getDescription() {
			return "The firebombs you got from the mysterious cave could come in handy in a fight. You'll need to find someone who can either make or acquire more.";
		}
		@Override
		public String getCompletedDescription() {
			return "Roxy has agreed to look into getting more firebombs for you.";
		}
	},

	REBEL_BASE_FIREBOMBS_FINISH(QuestType.SIDE,
			1,
			5) {
		@Override
		public String getName() {
			return "Fire From The Rats";
		}
		@Override
		public String getDescription() {
			return "It will take Roxy two days to get a new supply of firebombs going. You should return to her then.";
		}
		@Override
		public String getCompletedDescription() {
			return "Somehow, Roxy didn't swindle you and you've gotten yourself a supply of firebombs.";
		}
	},

	REBEL_BASE_FIREBOMBS_FAILED(QuestType.SIDE,
			1,
			0) {
		@Override
		public String getName() {
			return "Roxy's Reluctance";
		}
		@Override
		public String getDescription() {
			return "Without an example to give to Roxy, she either couldn't understand or didn't want to be bothered with trying to replicate the firebombs you found...";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},

	
	// Fetching beer barrels for Oglix:
	
	OGLIX_BEER_BARRELS_1(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Kheiron's Barrels";
		}
		@Override
		public String getDescription() {
			return "Having agreed to help Oglix expand her selection of beer-bitches, you've been tasked with travelling to the tavern, 'The Centaur's Sword', and asking the owner, Kheiron, for any spare barrels he might have."
					+ " If he refuses to help, Golix instructed you to tell the centaur that 'Golix says to be a good horsie'.";
		}
		@Override
		public String getCompletedDescription() {
			return "Thanks to the special phrase 'Golix says to be a good horsie', you managed to convince Kheiron to send four spare barrels to Oglix's tavern.";
		}
	},
	
	OGLIX_BEER_BARRELS_2(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Good Horsie Report";
		}
		@Override
		public String getDescription() {
			return "Having secured Kheiron's assistance, you now need to return to Oglix and inform her that Kheiron chose to be 'a good horsie' for Golix.";
		}
		@Override
		public String getCompletedDescription() {
			return "You returned to Oglix and informed her of your success."
					+ " Having now secured four additional barrels in which to lock new beer-bitches, Oglix asked you to supply promising candidates from the nearby alleyways' criminal population."
					+ " Additionally, she told you to sneak around the back of her tavern between [units.time(6)]-[units.time(7)] if you wanted to see what the phrase 'Golix says to be a good horsie' meant...";
		}
	},

	
	// Helping Lunexis to escape:
	
	LUNEXIS_ESCAPE(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Free Lunexis";
		}
		@Override
		public String getDescription() {
			return "Having surrendered to Lunexis and pledged to be her obedient cock-sleeve, you've been ordered by your new Mistress to assist her in escaping from captivity."
					+ " Wanting to get her revenge on the one who teleported her to Elis, the centauress has devised a plan where you're to convince Meraxis to teleport the three of you back to Themiscyra."
					+ " Once there, your Mistress will reward you by keeping you as one of her personal cock-sleeve slaves...";
		}
		@Override
		public String getCompletedDescription() {
			return "You convinced Meraxis to teleport herself, along with you and Lunexis, back to Themiscyra."
					+ " Once there, your deception was made clear, and although she tried to fight, Meraxis was soon subdued and used by your Mistress to win back the wavering loyalty of her centauress army.";
		}
	},

	LUNEXIS_ESCAPE_FAILED(QuestType.SIDE, 1, 0) {
		@Override
		public String getName() {
			return "Lunexis Betrayed";
		}
		@Override
		public String getDescription() {
			return "Deciding to reveal everything to Meraxis, you betrayed Lunexis, and were banned from having any further contact with the demonic centauress.";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},
	
	// Romance quests:

	RELATIONSHIP_NYAN_1_STOCK_ISSUES(QuestType.RELATIONSHIP, 1, 0) {
		@Override
		public String getName() {
			return "Helping Nyan";
		}
		@Override
		public String getDescription() {
			return "Nyan explained that she's unable to sell any enchanted clothing due to the fact that her wholesale supplier has suddenly stopped communicating with her."
					+ " Apparently, this supplier used to be on good terms with Nyan, and his uncharacteristic behaviour has caused the nervous cat-girl to suspect something terrible has happened to him.<br/>"
					+ "Perhaps you could offer Nyan your help by finding out what's happened to this supplier?";
		}
		@Override
		public String getCompletedDescription() {
			return "You offered Nyan your help in finding out what's happened to her wholesale enchanted clothing supplier.";
		}
	},
	
	RELATIONSHIP_NYAN_2_STOCK_ISSUES_AGREED_TO_HELP(QuestType.RELATIONSHIP, 1, 25) {
		@Override
		public String getName() {
			return "Saving Kay";
		}
		@Override
		public String getDescription() {
			return "Nyan told you that her supplier, [kay.nameFull], has his business set up in Dominion's warehouse district."
					+ " You'll need to travel to the north-west of the city, find Kay's warehouse, and discover why he's suddenly cut off all communication with Nyan.";
		}
		@Override
		public String getCompletedDescription() {
			return "You not only found Kay's warehouse, but were also able to discover that his business has been effectively seized by a pair of bullying bounty hunters.";
		}
	},
	
	RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS(QuestType.RELATIONSHIP, 10, 100) {
		@Override
		public String getName() {
			return "Bounty Hunter Bullies";
		}
		@Override
		public String getDescription() {
			return "Wolfgang and Karl, a pair of bounty hunters hired by Kay to protect his warehouse, have turned on their employer and effectively seized full control of his business."
					+ " One way or another, you're going to have to convince these dobermann bullies to leave Kay alone...";
		}
		@Override
		public String getCompletedDescription() {
			return "By dealing with Wolfgang and Karl and sending them skulking off back to Slaver Alley's 'Bounty Hunter Lodge', you've both saved Kay's business and ensured that Nyan once again has access to a stock of enchanted clothing."
					+ " Offering you his eternal thanks, Kay said that you're welcome to pay him a visit whenever you like.";
		}
	},
	
	RELATIONSHIP_NYAN_4_STOCK_ISSUES_SUPPLIERS_BEATEN(QuestType.RELATIONSHIP, 1, 25) {
		@Override
		public String getName() {
			return "Nyan's Reward";
		}
		@Override
		public String getDescription() {
			return "Now that you've saved Kay's business, you should return to Nyan and inform her of what's happened.";
		}
		@Override
		public String getCompletedDescription() {
			return "A very happy Nyan paid you your promised reward, and also offered to give you a lifetime 25% discount in her store."
					+ " She also let slip that she's single, in a clumsy attempt to try and hit on you...";
		}
	},
	
	
	
	ROMANCE_HELENA_FAILED(QuestType.RELATIONSHIP, 1, 0) {
		@Override
		public String getName() {
			return "Furious Matriarch";
		}

		@Override
		public String getDescription() {
			return "After you refused to sell Scarlett to Helena, the haughty harpy matriarch gave up on her plans to run a slavery business and stormed off back to her nest."
					+ " With the way she ruthlessly insulted you before leaving, you can be sure that she's never going to want to see you again...";
		}

		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},
	
	ROMANCE_HELENA_1_OFFER_HELP(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Offer to help";
		}

		@Override
		public String getDescription() {
			return "After asking Helena about her business, you discovered that she's barely managing to keep the place running."
					+ " After expressing her desire to make some improvements to her shop, Helena revealed that she simply doesn't have the time nor inclination to do the work herself."
					+ " Perhaps you could offer her your help?";
		}

		@Override
		public String getCompletedDescription() {
			return "You offered to help Helena make some improvements to her shop.";
		}
	},

	ROMANCE_HELENA_2_PURCHASE_PAINT(QuestType.RELATIONSHIP, 1, 25) {
		@Override
		public String getName() {
			return "Purchase Paint";
		}

		@Override
		public String getDescription() {
			return "Helena revealed that the first thing she wants done is to have the entire exterior of her shop repainted."
					+ " Having provided you with no money for expenses, the harpy is expecting you to travel to 'Argus's DIY Depot' and buy a can of 'Purple-star' branded golden paint."
					+ " Once you've bought it, you need to return to Helena."
					+ "<br/><i>('Argus's DIY Depot' can be found a fair way to the south of Slaver Alley, next to the canal.)</i>";
		}

		@Override
		public String getCompletedDescription() {
			return "You purchased the required paint from 'Argus's DIY Depot' and returned to Helena.";
		}
	},

	ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "Exterior Decorator (1/3)";
		}

		@Override
		public String getDescription() {
			return "Having purchased the golden paint, you returned to Helena only to have her demand that you get started on repainting the exterior of her shop as soon as possible...";
		}

		@Override
		public String getCompletedDescription() {
			return "You stripped off all of the old paint from the front of Helena's store.";
		}
	},

	ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "Exterior Decorator (2/3)";
		}

		@Override
		public String getDescription() {
			return "You need to return to Helena's store during opening hours to see what your next task will be...";
		}

		@Override
		public String getCompletedDescription() {
			return "You repainted the entire frontage of Helena's store, and additionally received a delivery of furniture from a succutaur named 'Natalya'.";
		}
	},

	ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "Exterior Decorator (3/3)";
		}

		@Override
		public String getDescription() {
			return "You need to return to Helena's store during opening hours to see what your next task will be...";
		}

		@Override
		public String getCompletedDescription() {
			return "Under the harpy matriarch's supervision, you painted the words 'Helena's Boutique' in golden lettering above the door to her store.";
		}
	},

	ROMANCE_HELENA_4_SCARLETTS_RETURN(QuestType.RELATIONSHIP, 1, 100) {
		@Override
		public String getName() {
			return "Scarlett's Return";
		}

		@Override
		public String getDescription() {
			boolean slave = Main.game.getNpc(Scarlett.class).isSlave() || Main.game.getNpc(Scarlett.class).getHomeWorldLocation()==WorldType.EMPTY;
			boolean playerOwner = Main.game.getNpc(Scarlett.class).isSlave() && Main.game.getNpc(Scarlett.class).getOwner().isPlayer();
			return "Helena revealed to you her plan to rebrand her slave shop as a place where clients could order custom slaves."
					+ " The person she has in mind to train these custom slaves is her old etiquette coach, who apparently is none other than Scarlett's sister."
					+ (slave
						?" The condition she's given Helena is that she free her unruly sister from slavery, promise never to enslave her again, and then keep her employed..."
							+ "<br/>"
							+(playerOwner
								?"You're going to have to bring Scarlett to Helena and sell her back to her..."
								:"You're going to have to find Scarlett and purchase her from whoever is her new owner. According to Helena, she's apparently been purchased by the owner of an antique shop somewhere in the Shopping Arcade.")
						:" The condition she's given Helena is that she keep her unruly sister employed and promise never to enslave her again..."
							+ "<br/>"
							+ "You're going to have to go up to Helena's nest, find Scarlett, and then tell her to return to Helena...");
		}
		
		@Override
		public String getCompletedDescription() {
			return "According to the wishes of Helena's old etiquette coach, Scarlett will from now on be working as the harpy matriarch's personal assistant.";
		}
	},

	ROMANCE_HELENA_5_SCARLETT_TRAINER(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Harpy Helper";
		}

		@Override
		public String getDescription() {
			return "The two harpies left early to head off and visit Scarlett's sister. You need to return to Helena's store during opening hours to see what your next task will be...";
		}

		@Override
		public String getCompletedDescription() {
			return "You returned to Helena's store to discover that everything is set up and ready for the harpy matriarch to start accepting customers. Before that, however, you need to help her with a couple mroe things...";
		}
	},

	ROMANCE_HELENA_6_ADVERTISING(QuestType.RELATIONSHIP, 1, 15) {
		@Override
		public String getName() {
			return "Advertising";
		}

		@Override
		public String getDescription() {
			return "Having been given half a dozen enchanted posters showing off Helena's beauty, your task is to put them up at the entrance of Slaver Alley in order to help advertise her store.";
		}

		@Override
		public String getCompletedDescription() {
			return "You put up posters advertising 'Helena's Boutique' at the entrance to Slaver Alley.";
		}
	},

	ROMANCE_HELENA_7_GRAND_OPENING_PREPARATION(QuestType.RELATIONSHIP, 1, 15) {
		@Override
		public String getName() {
			return "Preparing for the Grand Opening";
		}

		@Override
		public String getDescription() {
			return "After putting up the posters, Scarlett appeared and lead you back to Helena's shop."
					+ " Your new task is to get things ready for tomorrow's grand opening, which means working through the night...";
		}

		@Override
		public String getCompletedDescription() {
			return "You and Scarlett finished the preparations for the store's grand opening.";
		}
	},

	ROMANCE_HELENA_8_FINISH(QuestType.RELATIONSHIP, 1, 100) {
		@Override
		public String getName() {
			return "Preparing Drinks";
		}

		@Override
		public String getDescription() {
			return "Not wanting Scarlett to cause any trouble during the grand opening, Helena has tasked the two of you with staying in the back room and making drinks for the guests.";
		}

		@Override
		public String getCompletedDescription() {
			return "You and Scarlett stayed in the back room making drinks until the grand opening was over."
					+ "  Finally showing some appreciation for your efforts, Helena told you that she'd be willing to let you take her on a date as your reward...";
		}
	},
	
	

	ROMANCE_NATALYA_FAILED_INTERVIEW(QuestType.RELATIONSHIP, 1, 0) {
		@Override
		public String getName() {
			return "Interview Failed";
		}
		@Override
		public String getDescription() {
			return "Having refused to do as Natalya asked during her interview, you were thrown out of Dominion Express and told never to return...";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},

	ROMANCE_NATALYA_FAILED_CONTRACT(QuestType.RELATIONSHIP, 1, 0) {
		@Override
		public String getName() {
			return "Contract Refused";
		}
		@Override
		public String getDescription() {
			return "Having refused to sign the contract which Natalya offered to you, you were thrown out of Dominion Express and told never to return...";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},
	
	ROMANCE_NATALYA_1_INTERVIEW_START(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Interviewed";
		}
		@Override
		public String getDescription() {
			return "Natalya, the Stable Mistress at the company Dominion Express, has offered you the opportunity to be interviewed for the position of 'filly'.";
		}
		@Override
		public String getCompletedDescription() {
			return "You accepted Natalya's offer of an interview for the position of 'filly' at Dominion Express.";
		}
	},

	ROMANCE_NATALYA_2_CONTRACT_SIGNED(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Natalya's Filly";
		}
		@Override
		public String getDescription() {
			return "Having accepted the offer of an interview for the position of 'filly', you now need to successfully pass it and sign the contract.";
		}
		@Override
		public String getCompletedDescription() {
			return "You successfully passed Natalya's interview, and after you'd signed the contract, you were told that you now need to be transformed into [style.a_shemale] taur.";
		}
	},
	
	ROMANCE_NATALYA_3_TRAINING_1(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Filly Training";
		}
		@Override
		public String getDescription() {
			return "You were told by Natalya that the first part of your training will involve repeating your oral performance on one of the centaur slaves.";
		}
		@Override
		public String getCompletedDescription() {
			return "After being transformed into [style.a_shemale] taur, you began your filly training by sucking the cock of one of Dominion Express's more unruly centaur slaves.";
		}
	},

	ROMANCE_NATALYA_4_TRAINING_2(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "More Training";
		}
		@Override
		public String getDescription() {
			return "Once again, you were told by Natalya to return the following day to continue your training, which will involve learning to love giving rimjobs.";
		}
		@Override
		public String getCompletedDescription() {
			return "The second stage of your training involved wearing colourful lipstick and performing anilingus on Mistress Natalya.";
		}
	},

	ROMANCE_NATALYA_5_TRAINING_3(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Final Training";
		}
		@Override
		public String getDescription() {
			return "Natalya instructed you to return the following day to finish your training, which will involve giving a rimjob to a centaur slave, and being mounted and anally fucked by both Natalya and the slave whose ass you service.";
		}
		@Override
		public String getCompletedDescription() {
			return "After performing anilingus on a centaur slave and then being mounted and anally fucked by them, Natalya declared that your filly training is complete.";
		}
	},

	ROMANCE_MONICA_1_TO_THE_FARM(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "To The Farm";
		}
		@Override
		public String getDescription() {
			return "After you'd offered to help recover her personalised breast pump, Monica told you that it could be found at her old workplace; a farm located to the North-East of Elis named 'Evelyx's Dairy'."
					+ " You'll have to go to this farm and ask for Monica's breast pump...";
		}
		@Override
		public String getCompletedDescription() {
			return "Having found the farm which is Monica's old workplace, you asked for the cow-girl's personalised breast pump, and were granted a meeting with the farm's owner...";
		}
	},

	ROMANCE_MONICA_2_UNREASONABLE_DEMAND(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "An Unreasonable Demand";
		}
		@Override
		public String getDescription() {
			return "You managed to get a meeting with the farm's owner, Evelyx, who takes the form of an arrogant, greedy succubus."
					+ " Although admitting that the breast pump was worthless, she's demanding that you either give her a huge sum of flames, or sign a suspicious contract, in exchange for it...";
		}
		@Override
		public String getCompletedDescription() {
			return "You managed to obtain Monica's personalised breast pump from Evelyx.";
		}
	},

	ROMANCE_MONICA_3_THE_JOURNEY_HOME(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "The Journey Home";
		}
		@Override
		public String getDescription() {
			return "Now that Monica's personalised breast pump is in your possession, all that's left to do is to return it to its rightful owner.";
		}
		@Override
		public String getCompletedDescription() {
			return "You returned Monica's personalised breast pump to her, much to her surprise and delight.";
		}
	},
	;

	private int level, experienceReward;
	private QuestType questType;

	private Quest(QuestType questType, int level, int experienceReward) {
		this.questType = questType;

		this.level = level;
		this.experienceReward = experienceReward;
	}

	public abstract String getName();

	public abstract String getDescription();

	public abstract String getCompletedDescription();
	
	public void applySkipQuestEffects() {	
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
	
	public static Quest getQuestFromId(String quest) {
		if(quest.equalsIgnoreCase("MAIN_3_A_FINDING_THE_YOUKO")) {
			return Quest.MAIN_3_ELIS;
		}
		if(quest.equalsIgnoreCase("MAIN_3_D_TO_THEMISCRYA")) {
			return Quest.MAIN_3_D_TO_THEMISCYRA;
		}
		
		return Quest.valueOf(quest);
	}

}
