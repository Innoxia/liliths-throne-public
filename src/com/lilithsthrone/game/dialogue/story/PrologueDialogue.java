package com.lilithsthrone.game.dialogue.story;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public class PrologueDialogue {

	private static boolean femalePrologueNPC() {
		return CharacterCreation.femalePrologueNPC();
	}
	
	public static final DialogueNodeOld INTRO = new DialogueNodeOld("In the Museum", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(femalePrologueNPC()) {
				return UtilText.parseFromXMLFile("misc/prologue", "INTRO_FEMALE");
				
			} else {
				return UtilText.parseFromXMLFile("misc/prologue", "INTRO_MALE");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Agree", "Overwhelmed with arousal, you decide to agree to go and have some fun.", INTRO_EMPTY_ROOM);
			} else if (index == 2) {
				return new Response("Say No", "You don't think it's a good idea to sneak off and have sex when you're supposed to be here to see your aunt Lily. Say no.", INTRO_NO);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld INTRO_EMPTY_ROOM = new DialogueNodeOld("In the Museum", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(femalePrologueNPC()) {
				return UtilText.parseFromXMLFile("misc/prologue", "INTRO_EMPTY_ROOM_FEMALE");
				
			} else {
				return UtilText.parseFromXMLFile("misc/prologue", "INTRO_EMPTY_ROOM_MALE");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(femalePrologueNPC()) {
					return new ResponseSex("Sex", "Give in to your lust and start having sex with [prologueFemale.name]...",
							null, null, null,
							null, null, null,
							true, true,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPrologueFemale(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							null,
							AFTER_SEX,
							(Main.game.getPlayer().hasPenis()
								?UtilText.parseFromXMLFile("misc/prologue", "INTRO_EMPTY_ROOM_SEX_FEMALE_AS_MALE_START")
								:UtilText.parseFromXMLFile("misc/prologue", "INTRO_EMPTY_ROOM_SEX_FEMALE_AS_FEMALE_START"))
							+ UtilText.parseFromXMLFile("misc/prologue", "SEX_CLOTHING_MANAGEMENT_TIP")) {
						@Override
						public void effects() {
							if(Main.game.getPlayer().hasPenis()) {
								Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.PENIS_CONDOM, Colour.CLOTHING_WHITE, false), false);
							}
						}
					};
					
				} else {
					return new ResponseSex("Sex", "Give in to your lust and start having sex with [prologueMale.name]...",
							null, null, null,
							null, null, null,
							true, true,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPrologueMale(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							null,
							AFTER_SEX,
							UtilText.parseFromXMLFile("misc/prologue", "INTRO_EMPTY_ROOM_SEX_MALE_START") + UtilText.parseFromXMLFile("misc/prologue", "SEX_CLOTHING_MANAGEMENT_TIP")) {
						@Override
						public void effects() {
							if(Main.game.getPlayer().hasPenis()) {
								Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.PENIS_CONDOM, Colour.CLOTHING_WHITE, false), false);
							}
							Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.PENIS_CONDOM, Colour.CLOTHING_WHITE, false), false);
						}
					};
				}
				
			} else if (index == 2) {
				return new Response("Second Thoughts", "Decide that this is a bad idea after all, and put an end to this.", INTRO_SECOND_THOUGHTS);
				
			} else {
				return null;
			}
		}
	};
	

	public static final DialogueNodeOld AFTER_SEX = new DialogueNodeOld("In the Museum", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(femalePrologueNPC()) {
				if(Sex.getNumberOfOrgasms(Main.game.getPrologueFemale())>0) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "AFTER_SEX_FEMALE_SATISFIED"));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "AFTER_SEX_FEMALE_NOT_SATISFIED"));
				}
				
			} else {
				if(Sex.getNumberOfOrgasms(Main.game.getPrologueMale())>0) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "AFTER_SEX_MALE_SATISFIED"));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "AFTER_SEX_MALE_NOT_SATISFIED"));
				}
			}

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "AFTER_SEX"));
			
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Search", "Go and search the museum for Arthur.", INTRO_2) {
					@Override
					public void effects() {
						CharacterCreation.moveNPCOutOfPlayerTile();
					}
				};
			} else {
				return null;
			}
		}
	};
	
	
	public static final DialogueNodeOld INTRO_SECOND_THOUGHTS = new DialogueNodeOld("In the Museum", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(femalePrologueNPC()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "INTRO_SECOND_THOUGHTS_FEMALE"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "INTRO_SECOND_THOUGHTS_MALE"));
			}

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "INTRO_SECOND_THOUGHTS"));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Search", "Go and search the museum for Arthur.", INTRO_2) {
					@Override
					public void effects() {
						CharacterCreation.moveNPCOutOfPlayerTile();
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld INTRO_NO = new DialogueNodeOld("In the Museum", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(femalePrologueNPC()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "INTRO_NO_FEMALE"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "INTRO_NO_MALE"));
			}
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "INTRO_NO"));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Search", "Go and search the museum for Arthur.", INTRO_2);
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_2 = new DialogueNodeOld("In the Museum", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_2");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Agree", "Go and look behind the mirror to find out who's there.", INTRO_3A);
			} else if (index == 2) {
				return new Response("Nope", "This is the most obvious trap you've ever seen.", INTRO_3B);
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_3A = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_3A");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("The horror!", "Aaaa!", INTRO_4);
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_3B = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_3B");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("The horror!", "Aaaa!", INTRO_4);
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_4 = new DialogueNodeOld("The horror!", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_4");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Panic", "Now would be a good time to panic.", INTRO_5);
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_5 = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_5");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Wake up", "You slowly start to regain consciousness.", INTRO_NEW_WORLD_1){
					@Override
					public void effects() {
						
						Main.game.setWeather(Weather.MAGIC_STORM, 300);

						Main.game.setRenderMap(true);
						
						MainController.updateUI();
						
						Main.game.setActiveWorld(
								Main.game.getWorlds().get(WorldType.DOMINION),
								PlaceType.DOMINION_AUNTS_HOME,
								false);
					}
				};
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_NEW_WORLD_1 = new DialogueNodeOld("A new world", "", true, false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_1");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Struggle", "Try to struggle out of their grip.", INTRO_NEW_WORLD_1_STRUGGLE);
				
			} else if (index == 2) {
				return new Response("Furries?! Yes!",
						"Furries are real?! You <b>love</b> furries!<br/>"
						+ "<b>This will set all of your starting furry preferences to </b><b style='color:"+ RaceStage.GREATER.getColour().toWebHexString()+ ";'>"+FurryPreference.MAXIMUM.getName()+"</b><b>."
						+ " This can be changed at any time from the options menu.</b>", 
						INTRO_NEW_WORLD_1_BY_THE_POWER_OF_LOVING_FURRIES){
					@Override
					public void effects(){
						for(Subspecies r : Subspecies.values()) {
							Main.getProperties().setFeminineFurryPreference(r, FurryPreference.MAXIMUM);
							Main.getProperties().setMasculineFurryPreference(r, FurryPreference.MAXIMUM);
						}
						Main.saveProperties();
					}
				};
				
			} else if (index == 3) {
				return new Response("Furries?! No!",
						"Why are furries real?! You <b>hate</b> furries! Channel your rage and try to break free.<br/>"
						+ "<b>This will set all of your starting furry preferences to </b><b style='color:"+ RaceStage.HUMAN.getColour().toWebHexString()+ ";'>"+FurryPreference.HUMAN.getName()+"</b><b>."
						+ " This can be changed at any time from the options menu.</b>", 
						INTRO_NEW_WORLD_1_BY_THE_POWER_OF_HATING_FURRIES){
					@Override
					public void effects(){
						for(Subspecies r : Subspecies.values()) {
							Main.getProperties().setFeminineFurryPreference(r, FurryPreference.HUMAN);
							Main.getProperties().setMasculineFurryPreference(r, FurryPreference.HUMAN);
						}
						Main.saveProperties();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	public static final DialogueNodeOld INTRO_NEW_WORLD_1_STRUGGLE = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_1_STRUGGLE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_1_STRUGGLE_END"));
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Someone's come to save you!", INTRO_NEW_WORLD_2){
					@Override
					public void effects() {
						Main.game.getLilaya().setLocation(WorldType.DOMINION, PlaceType.DOMINION_AUNTS_HOME, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld INTRO_NEW_WORLD_1_BY_THE_POWER_OF_HATING_FURRIES = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_1_BY_THE_POWER_OF_HATING_FURRIES"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_1_STRUGGLE_END"));
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Someone's come to save you!", INTRO_NEW_WORLD_2){
					@Override
					public void effects() {
						Main.game.getLilaya().setLocation(WorldType.DOMINION, PlaceType.DOMINION_AUNTS_HOME, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld INTRO_NEW_WORLD_1_BY_THE_POWER_OF_LOVING_FURRIES = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_1_BY_THE_POWER_OF_LOVING_FURRIES"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_1_STRUGGLE_END"));
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Someone's come to save you!", INTRO_NEW_WORLD_2){
					@Override
					public void effects() {
						Main.game.getLilaya().setLocation(WorldType.DOMINION, PlaceType.DOMINION_AUNTS_HOME, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld INTRO_NEW_WORLD_2 = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_2");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Explain", "Quickly explain to Lily what happened back at the museum.", INTRO_NEW_WORLD_2_A);
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_NEW_WORLD_2_A = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_2_A");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Follow", "Follow Lily as she leads you back to her house.", INTRO_NEW_WORLD_3){
					@Override
					public void effects() {
						Main.game.getRose().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, false);
						Main.game.getLilaya().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, false);
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_NEW_WORLD_3 = new DialogueNodeOld("Lilaya's Home", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_3");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("To the lab", "Follow Lilaya to her lab.", INTRO_NEW_WORLD_4){
					@Override
					public void effects() {
						Main.game.getRose().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
						Main.game.getLilaya().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_NEW_WORLD_4 = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_4");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Blinded", "The pink flash was so bright that you're left temporarily blinded!", INTRO_NEW_WORLD_5){
					@Override
					public void effects() {
						// Remove clothing:
						List<AbstractClothing> tempList = new ArrayList<>();
						tempList.addAll(Main.game.getPlayer().getClothingCurrentlyEquipped());

						for (AbstractClothing c : tempList) {
							Main.game.getPlayer().unequipClothingOntoFloor(c, true, Main.game.getPlayer());
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_NEW_WORLD_5 = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_5");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("I'm a demon?!", "Lilaya keeps using the word 'Demon' to describe your 'aura'. You're starting to worry that something must have changed deep inside of you...", INTRO_NEW_WORLD_6){
					@Override
					public void effects() {
						// Equip clothing:
						List<AbstractClothing> tempList = new ArrayList<>();
						tempList.addAll(Main.game.getPlayerCell().getInventory().getAllClothingInInventory());

						for (AbstractClothing c : tempList) {
							if(!c.getClothingType().equals(ClothingType.SCIENTIST_EYES_SAFETY_GOGGLES)) {
								Main.game.getPlayer().equipClothingFromGround(c, true, Main.game.getPlayer());
							}
						}
						
						Main.game.getPlayer().equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.MELEE_CHAOS_RARE, DamageType.FIRE));
						
						Main.game.clearTextStartStringBuilder();
						Main.game.clearTextEndStringBuilder();
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_NEW_WORLD_6 = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_6");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Magic!", "Thanks to your powerful aura, you can harness the arcane!", INTRO_NEW_WORLD_7){
					@Override
					public String getTitle() {
						if (!Main.game.getPlayer().isFeminine())
							return "You're a wizard!";
						else
							return "You're a witch!";
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_NEW_WORLD_7 = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_7");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Your room", "You follow Rose as she leads you up to your new room.", INTRO_NEW_WORLD_8){
					@Override
					public void effects() {
						Main.game.getRose().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, false);
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_NEW_WORLD_8 = new DialogueNodeOld("Your room", "You follow Rose as she leads you up to your new room.", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_8");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Knocking", "Rose said she'd be back in about half an hour, so that must be her knocking at your door.", INTRO_NEW_WORLD_9){
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(5000);
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS));
						AbstractItem spellBook = AbstractItemType.generateItem(ItemType.getSpellBookType(Spell.ICE_SHARD));
						Main.game.getPlayerCell().getInventory().addItem(spellBook);
						Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>[style.boldExcellent("+spellBook.getName()+")] added to your room's storage!</p>");
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_NEW_WORLD_9 = new DialogueNodeOld("Knocking", "Rose said she'd be back in about half an hour, so that must be her knocking at your door.", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_9");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Freedom!", "Decide what you want to do next.", RoomPlayer.ROOM){
					@Override
					public void effects() {
						Main.game.setPrologueFinished(true);
						Main.game.getRose().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
						Main.saveGame("AutoSave_"+Main.game.getPlayer().getName(), true);
					}
				};
				
			} else {
				return null;
			}
		}
	};

}
