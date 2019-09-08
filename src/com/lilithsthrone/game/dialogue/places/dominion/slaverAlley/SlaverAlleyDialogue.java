package com.lilithsthrone.game.dialogue.places.dominion.slaverAlley;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.dominion.DominionAlleywayAttacker;
import com.lilithsthrone.game.character.npc.dominion.Finch;
import com.lilithsthrone.game.character.npc.dominion.SlaveInStocks;
import com.lilithsthrone.game.character.npc.misc.GenericFemaleNPC;
import com.lilithsthrone.game.character.npc.misc.GenericMaleNPC;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.occupantManagement.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.SlavePermission;
import com.lilithsthrone.game.occupantManagement.SlavePermissionSetting;
import com.lilithsthrone.game.sex.managers.dominion.SMStocks;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStocks;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.4
 * @author Innoxia
 */
public class SlaverAlleyDialogue {

	public static void dailyReset() {
		
//		List<String> ids = Main.game.getNpc(Finch.class).getSlavesOwned();
//		for(String id : ids) {
//			NPC slaveToRemove = (NPC) Main.game.getNPCById(id);
//			if(!slaveToRemove.getLocationPlace().getPlaceType().equals(PlaceType.SLAVER_ALLEY_AUCTIONING_BLOCK)) {
//				Main.game.getNpc(Finch.class).removeSlave(slaveToRemove);
//				Main.game.banishNPC(slaveToRemove);
//			}
//		}
		for(String id : Main.game.getNpc(Finch.class).getSlavesOwned()) {
			if(Main.game.isCharacterExisting(id)) {
				Main.game.banishNPC(id);
			}
		}
		Main.game.getNpc(Finch.class).removeAllSlaves();
		
		// Female stall:
		Gender[] genders = new Gender[] {Gender.F_V_B_FEMALE, Gender.F_V_B_FEMALE, Gender.F_P_V_B_FUTANARI};
		for(int i=0; i<genders.length; i++) {
			NPC slave = new DominionAlleywayAttacker(genders[i], false, NPCGenerationFlag.NO_CLOTHING_EQUIP);
			slave.setHistory(Occupation.NPC_SLAVE);
			try {
				Main.game.addNPC(slave, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_STALL_FEMALES, true);
			slave.resetInventory(true);
			slave.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NECK_SLAVE_COLLAR, Colour.CLOTHING_GOLD, false), true, Main.game.getNpc(Finch.class));
			Main.game.getNpc(Finch.class).addSlave(slave);
			slave.setPlayerKnowsName(true);
			
			slave.addFetish(Fetish.FETISH_SUBMISSIVE);
			slave.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
			slave.addFetish(Fetish.FETISH_ORAL_GIVING);
			slave.setPersonalityTrait(PersonalityTrait.AGREEABLENESS, PersonalityWeight.HIGH);
			slave.setObedience(100);
		}

		// Male stall:
		genders = new Gender[] {Gender.M_P_MALE, Gender.M_P_MALE, Gender.M_P_MALE};
		for(int i=0; i<genders.length; i++) {
			NPC slave = new DominionAlleywayAttacker(genders[i], false, NPCGenerationFlag.NO_CLOTHING_EQUIP);
			slave.setHistory(Occupation.NPC_SLAVE);
			try {
				Main.game.addNPC(slave, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_STALL_MALES, true);
			slave.resetInventory(true);
			slave.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NECK_SLAVE_COLLAR, Colour.CLOTHING_BLACK_STEEL, false), true, Main.game.getNpc(Finch.class));
			Main.game.getNpc(Finch.class).addSlave(slave);
			slave.setPlayerKnowsName(true);
			
			slave.addFetish(Fetish.FETISH_DOMINANT);
			slave.addFetish(Fetish.FETISH_CUM_STUD);
			slave.setPersonalityTrait(PersonalityTrait.NEUROTICISM, PersonalityWeight.LOW);
			slave.setObedience(75);
		}

		// Anal stall:
		for(int i=0; i<3; i++) {
			NPC slave = new DominionAlleywayAttacker(Gender.getGenderFromUserPreferences(false, false), false, NPCGenerationFlag.NO_CLOTHING_EQUIP);
			slave.setHistory(Occupation.NPC_SLAVE);
			try {
				Main.game.addNPC(slave, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_STALL_ANAL, true);
			slave.resetInventory(true);
			slave.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NECK_SLAVE_COLLAR, Colour.CLOTHING_BLACK_STEEL, false), true, Main.game.getNpc(Finch.class));
			if(i==0) {
				slave.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId("innoxia_buttPlugs_butt_plug"), false), true, Main.game.getNpc(Finch.class));
			} else if(i==1) {
				slave.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId("innoxia_buttPlugs_butt_plug_jewel"), false), true, Main.game.getNpc(Finch.class));
			} else {
				slave.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId("innoxia_buttPlugs_butt_plug_heart"), false), true, Main.game.getNpc(Finch.class));
			}
			Main.game.getNpc(Finch.class).addSlave(slave);
			slave.setPlayerKnowsName(true);
			
			slave.setAssWetness(Util.randomItemFrom(Util.newArrayListOfValues(Wetness.FOUR_SLIMY, Wetness.FIVE_SLOPPY, Wetness.SIX_SOPPING_WET, Wetness.SEVEN_DROOLING)).getValue());
			slave.setAssBleached(true);
			slave.setAssCapacity(Util.random.nextInt(Capacity.ONE_EXTREMELY_TIGHT.getMaximumValue()), true);
			slave.setAssVirgin(false);
			Main.game.getPlayer().setKnowsCharacterArea(CoverableArea.ANUS, slave, true);
			
			slave.addFetish(Fetish.FETISH_ANAL_GIVING);
			slave.addFetish(Fetish.FETISH_ANAL_RECEIVING);
			slave.setObedience(75);
		}

		// Vaginal stall:
		for(int i=0; i<3; i++) {
			NPC slave = new DominionAlleywayAttacker(Gender.getGenderFromUserPreferences(true, false), false, NPCGenerationFlag.NO_CLOTHING_EQUIP);
			slave.setHistory(Occupation.NPC_SLAVE);
			try {
				Main.game.addNPC(slave, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_STALL_VAGINAL, true);
			slave.resetInventory(true);
			slave.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NECK_SLAVE_COLLAR, Colour.CLOTHING_BLACK_STEEL, false), true, Main.game.getNpc(Finch.class));
			Main.game.getNpc(Finch.class).addSlave(slave);
			slave.setPlayerKnowsName(true);
			
			slave.setVaginaWetness(Util.randomItemFrom(Util.newArrayListOfValues(Wetness.FOUR_SLIMY, Wetness.FIVE_SLOPPY, Wetness.SIX_SOPPING_WET, Wetness.SEVEN_DROOLING)).getValue());
			slave.setVaginaCapacity(Util.random.nextInt(Capacity.ONE_EXTREMELY_TIGHT.getMaximumValue()), true);
			slave.setVaginaVirgin(true);
			
			slave.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
			slave.addFetish(Fetish.FETISH_VAGINAL_GIVING);
			slave.setObedience(75);
		}

		// Oral stall:
		for(int i=0; i<3; i++) {
			NPC slave = new DominionAlleywayAttacker(Gender.getGenderFromUserPreferences(false, false), false, NPCGenerationFlag.NO_CLOTHING_EQUIP);
			slave.setHistory(Occupation.NPC_SLAVE);
			try {
				Main.game.addNPC(slave, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_STALL_ORAL, true);
			slave.resetInventory(true);
			slave.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NECK_SLAVE_COLLAR, Colour.CLOTHING_BLACK_STEEL, false), true, Main.game.getNpc(Finch.class));
			if(Math.random()<0.5f) {
				slave.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.BDSM_RINGGAG, false), true, Main.game.getNpc(Finch.class));
			}
			Main.game.getNpc(Finch.class).addSlave(slave);
			slave.setPlayerKnowsName(true);

			slave.setFaceWetness(Util.randomItemFrom(Util.newArrayListOfValues(Wetness.FOUR_SLIMY, Wetness.FIVE_SLOPPY, Wetness.SIX_SOPPING_WET, Wetness.SEVEN_DROOLING)).getValue());
			slave.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE.getMedianValue(), true);
			slave.setFaceElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
			slave.setLipSize(LipSize.FOUR_HUGE.getValue());
			slave.setFaceVirgin(false);

			slave.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			slave.addFetish(Fetish.FETISH_ORAL_GIVING);
			slave.setObedience(75);
		}
	}
	
	public static void stocksReset(){
		List<NPC> npcsToBanish = new ArrayList<>();
		for(NPC npc : Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS))) {
			if(npc instanceof SlaveInStocks) {
				npcsToBanish.add(npc);
			}
		}
		for(NPC npc : npcsToBanish) {
			Main.game.banishNPC(npc);
		}
		
		for(int i=0; i<4; i++) {
			SlaveInStocks slave = new SlaveInStocks(Gender.getGenderFromUserPreferences(false, false));
			try {
				Main.game.addNPC(slave, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(Math.random()>0.5f) {
				Main.game.getNpc(GenericFemaleNPC.class).addSlave(slave);
			} else {
				Main.game.getNpc(GenericMaleNPC.class).addSlave(slave);	
			}
			slave.removeSlavePermissionSetting(SlavePermission.CLEANLINESS, SlavePermissionSetting.CLEANLINESS_WASH_BODY);
			slave.removeSlavePermissionSetting(SlavePermission.CLEANLINESS, SlavePermissionSetting.CLEANLINESS_WASH_CLOTHES);
		}
	}
	
	
	public static final DialogueNode OUTSIDE = new DialogueNode("Slaver Alley", "-", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "As you navigate through the labyrinthine passageways of Dominion's back-alleys, you start to hear the faint murmur of voices somewhere before you."
						+ " Proceeding with caution, you slowly step around the next corner, and find yourself looking into a small, well-kept courtyard."
					+ "</p>"
					+ "<p>"
						+ "Clean, grey cobblestones line the floor, and in the middle of the little square, surrounded by wooden benches, a bubbling stream of water cascades out of a finely-crafted stone fountain."
						+ " On three of its sides, multiple narrow entrances link this area to the surrounding alleyways, but it's what's on the fourth that draws your attention."
						+ " A huge, open gateway, flanked by a pair of muscular horse-boys, has been built into the wall, and it's through this opening that the sound of a busy marketplace can be heard."
					+ "</p>"
					+ "<p>"
						+ "As you step closer, neither of the guards react to your presence, and you notice that their attention is focused solely on who's trying to leave the area beyond."
						+ " Crossing the courtyard, you see that the words 'Slaver Alley' have been cast into the dull iron framing of the gate, and on the walls to either side,"
							+ " promotional posters for different vendors have been plastered over the red bricks."
					+ "</p>"
					+ "<p>"
						+ "It looks as though any member of the public is free to come and go as they please."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Slaver Alley", "Step through the gate and enter Slaver Alley."){
					@Override
					public void effects() {
						Main.mainController.moveGameWorld(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_ENTRANCE, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode GATEWAY = new DialogueNode("Gateway", ".", false) {

		@Override
		public String getContent() {
			return "<p>"
						+ "You find yourself standing in the gateway that links Slaver Alley to the rest of Dominion."
						+ " On either side of you, horse-boy guards keep a watchful eye on anyone that passes through here; obviously on the lookout for runaway slaves."
					+ "</p>"
					+ "<p>"
						+ "A touristy-looking information board, looking quite out of place with its surroundings, has been fixed to a nearby wall, and upon reading it,"
							+ " you discover that a powerful arcane shield has been erected over the entire area, which prevents the effects of any on-going arcane storms from being felt by any of the marketplace's occupants."
						+ " True enough, as you glance up at the sky above, you see the faint pink crackle of the shield's arcane energy, reassuring you that you needn't worry about attacks from any horny customers."
					+ "</p>"
					+ "<p>"
						+ "There are only a couple of other pieces of information that are of any real relevance to you."
						+ " In large red writing at the top of the information board, you read that you must be in possession of a slaver license before being able to buy or sell slaves here."
						+ " Underneath, the sentence telling you that you can obtain a license from the Slaver Administration building is crossed out, and in scrawling writing, the words"
						+ " 'Slaver Licenses are not being issued' has been written beneath."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Leave", "Step back out into Dominion's alleyways."){
					@Override
					public void effects() {
						Main.mainController.moveGameWorld(WorldType.DOMINION, PlaceType.DOMINION_SLAVER_ALLEY, true);
					}
				};

			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ALLEYWAY = new DialogueNode("Alleyway", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "You find yourself walking down one of Slaver Alley's numerous interconnected passageways."
						+ " Lined with clean, grey cobblestone, and being almost as wide as a normal street, they're quite unlike the regular sort of passages that make up the rest of Dominion's back-alleys.");
			
			if (Main.game.isDayTime()) {
				UtilText.nodeContentSB.append(
							" Colourful canvas awnings have been unfurled from the sides of the surrounding buildings, and, looking up through the gap in the middle, you see a faint pink crackle;"
									+ " evidence of the shield that protects Slaver Alley from arcane storms."
						+ "</p>");
			} else {
				UtilText.nodeContentSB.append(
							" A series of arcane-powered lights cast a flickering glow over the area, and as you look up at the night sky, you see a faint pink crackle;"
									+ " evidence of the shield that protects Slaver Alley from arcane storms."
						+ "</p>");
			}
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "Although not as busy as Dominion's main streets, there are still a significant amount of people wandering about,"
							+ " and you often have to push your way through small crowds that inexplicably decide to loiter right in the middle of the path."
						+ " The close proximity of the surrounding buildings echoes and amplifies the crowd's inane chatter, making Slaver Alley feel more alive than any other part of the city you've wandered through."
					+ "</p>"
					+ "<p>"
						+ "The most striking feature of this area is the manner of goods that are sold here."
						+ " Down each passageway, multiple wooden stands have been erected, and on top each one, slaves of all different races and sexes are being publicly displayed."
						+ " You see that information boards have been attached to each one of these stands, giving you instructions as to which shop you need to visit in order to negotiate the purchase of the wares on display."
					+ "</p>"
					+ "<p>"
						+ "Glancing up at the slaves as you pass, you notice that the one thing they all have in common is that they're all wearing metal collars of some sort or another."
						+ " Apart from that peculiar accessory, the vast majority of them are completely naked, although you do see a few that have been dressed in skimpy pieces of highly-revealing clothing."
					+ "</p>");

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_FEMALE = new DialogueNode("A Woman's Touch", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_FEMALE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getPlayer().isHasSlaverLicense()) {
					return new Response("Slave Manager", "You don't have a slaver license, so can't buy or sell any slaves...", null);
				}
				return new Response("Slave Manager", "Enter the slave management screen.", MARKET_STALL_FEMALE) {
					@Override
					public DialogueNode getNextDialogue() {
						return OccupantManagementDialogue.getSlaveryManagementDialogue(Main.game.getNpc(Finch.class));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_MALE = new DialogueNode("Iron & Steel", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_MALE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getPlayer().isHasSlaverLicense()) {
					return new Response("Slave Manager", "You don't have a slaver license, so can't buy or sell any slaves...", null);
				}
				return new Response("Slave Manager", "Enter the slave management screen.", MARKET_STALL_FEMALE) {
					@Override
					public DialogueNode getNextDialogue() {
						return OccupantManagementDialogue.getSlaveryManagementDialogue(Main.game.getNpc(Finch.class));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_ANAL = new DialogueNode("The Rear Entrance", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_ANAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getPlayer().isHasSlaverLicense()) {
					return new Response("Slave Manager", "You don't have a slaver license, so can't buy or sell any slaves...", null);
				}
				return new Response("Slave Manager", "Enter the slave management screen.", MARKET_STALL_FEMALE) {
					@Override
					public DialogueNode getNextDialogue() {
						return OccupantManagementDialogue.getSlaveryManagementDialogue(Main.game.getNpc(Finch.class));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_VAGINAL = new DialogueNode("White Lilies", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_VAGINAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getPlayer().isHasSlaverLicense()) {
					return new Response("Slave Manager", "You don't have a slaver license, so can't buy or sell any slaves...", null);
				}
				return new Response("Slave Manager", "Enter the slave management screen.", MARKET_STALL_FEMALE) {
					@Override
					public DialogueNode getNextDialogue() {
						return OccupantManagementDialogue.getSlaveryManagementDialogue(Main.game.getNpc(Finch.class));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_ORAL = new DialogueNode("Viva Voce", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_ORAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getPlayer().isHasSlaverLicense()) {
					return new Response("Slave Manager", "You don't have a slaver license, so can't buy or sell any slaves...", null);
				}
				return new Response("Slave Manager", "Enter the slave management screen.", MARKET_STALL_FEMALE) {
					@Override
					public DialogueNode getNextDialogue() {
						return OccupantManagementDialogue.getSlaveryManagementDialogue(Main.game.getNpc(Finch.class));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_STATUE = new DialogueNode("Statue of the Fallen Angel", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.statueTruthRevealed)) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_STATUE_TRUTH");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_STATUE");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_EXCLUSIVE = new DialogueNode("Zaibatsu Exchange", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_EXCLUSIVE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_BULK = new DialogueNode("Royal Dominion Company", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_BULK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_CAFE = new DialogueNode("", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_CAFE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL = new DialogueNode("", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Deciding that you'd like to take a look inside one of the shops, you approach the entrance to the nearest building."
						+ " In order to display yet more slaves to passing customers, you see that a series of large glass windows have been installed into the walls before you."
						+ " As you walk up to the entrance, some of the slaves standing behind these windows glance your way; no doubt wondering if you'll be their next owner."
					+ "</p>"
					+ "<p>"
						+ "Pushing open the door, you step inside the shop, and find that the interior is surprisingly clean, well-lit, and airy."
						+ " Yet more slaves line the walls around you, and in the middle of the room, the shop's owner cries out a greeting from behind a circular wooden desk."
					+ "</p>"
					+ "<p>"
						+ "After browsing the wares for a moment, you decide that you've had enough."
						+ " After all, without a permit, you're unable to buy anything."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode AUCTION_BLOCK = new DialogueNode("Auctioning block", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return "<p>" // captured or slaves for public sale (seized assets)
						+ "As you walk towards Slaver Alley's central courtyard, the excited buzz of hundreds of voices grows louder and louder."
						+ " The source of this incessant din is hard to miss, and takes the form a huge, bustling crowd, which has surrounded a raised wooden platform."
						+ " Several noticeboards, elevated above the heads of the energetic mob by means of several-[unit.lSize]-high poles, declare this area to be for 'Public Auctions'."
					+ "</p>"
					+ "<p>"
						+ (Main.game.getPlayer().isHasSlaverLicense()
								?"Being in possession of a slaver's license, you wonder if you should approach the stage and participate in the next auction."
								:"The noticeboards also declare that you'd need a slaver license in order to participate in any auctions, so, not being in possession of one yourself, there'd be no point in approaching the stage.")
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isHasSlaverLicense()) {
					return new Response("Approach", "Approach the auction block.", AUCTION_BLOCK_LIST);
				} else {
					return new Response("Approach", "You don't have a slaver license, so you're unable to participate in any slave auctions.", null);
				}
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AUCTION_BLOCK_LIST = new DialogueNode("Auctioning block", ".", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>" // captured or slaves for public sale (seized assets)
						+ "You make your way through the pressing crowds and take your place on the outskirts of the main bidding area."
						+ " The information boards in this section seem to carry quite a lot more detail than those on the outskirts of the courtyard, and, upon reading one, you discover a little more about the slaves that are on offer here."
					+ "</p>"
					+ "<p>"
						+ " According to the notice board, the vast majority of slaves are bought and sold by through the stores that scattered throughout slaver alley, but in the cases of a slave owner's assets being seized,"
							+ " or if a slave owner somehow ends up being enslaved themselves, all slaves that they owned are put up for public auction."
						+ " It also states that some slaves willingly sell themselves in order to pay off their debts, and offering themselves up at a public auction is apparently a popular choice for those individuals."
					+ "</p>"
					+ "<p>"
						+ "Attached to the bottom of each of the noticeboards is a list of the slaves that are up next for the public auction."
						+ " After reading through the list of names, you wonder if you should stick around to place a bid on any of them..."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "<b>Upcoming Public Auctions</b>"
						+ "<div class='container-full-width' style='margin-bottom:0; text-align:center;'>"
							+ "<div style='width:40%; float:left; font-weight:bold; margin:0; padding:0;'>"
								+ "Slave"
							+ "</div>"
							+ "<div style='float:left; width:17%; font-weight:bold; margin:0; padding:0; text-align:center;'>"
								+ "<b style='color:"+Colour.OBEDIENCE.toWebHexString()+";'>Obedience</b>"
							+"</div>"
							+ "<div style='float:left; width:17%; font-weight:bold; margin:0; padding:0; text-align:center;'>"
								+ "<b style='color:"+Colour.CURRENCY_GOLD.toWebHexString()+";'>Value</b>"
							+"</div>"
							+ "<div style='float:left; width:17%; font-weight:bold; margin:0; padding:0; text-align:center;'>"
								+ "<b style='color:"+Colour.CURRENCY_GOLD.toWebHexString()+";'>Starting Bid</b>"
							+"</div>"
							+ "<div style='float:left; width:9%; font-weight:bold; margin:0; padding:0; text-align:center;'>"
								+ "Bid"
							+ "</div>"
						+ "</div>");
			
			List<NPC> charactersPresent = new ArrayList<>(Main.game.getCharactersPresent());
			charactersPresent.removeIf((npc) -> Main.game.getPlayer().getCompanions().contains(npc));
			
			Collections.sort(charactersPresent, (e1, e2) -> e1.getName(true).compareTo(e2.getName(true)));
			
			if(charactersPresent.isEmpty()) {
				UtilText.nodeContentSB.append(
						"<div class='container-full-width' style='margin-bottom:0; text-align:center;'>"
								+ "<b>There are no upcoming auctions...</b>"
						+ "</div>");
				
			} else {
				int i=0;
				for(NPC slave : charactersPresent){
					boolean alternateBackground = i%2==0;
					
					UtilText.nodeContentSB.append(UtilText.parse(slave,
							"<div class='container-full-width inner' style='margin-bottom:0;"+(alternateBackground?"background:"+Colour.BACKGROUND_ALT.toWebHexString()+";'":"'")+"'>"
								+ "<div style='width:40%; float:left; margin:0; padding:0; text-align:center;'>"
									+ "<b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>"+slave.getName(true)+"</b> - "
									+ "<span style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(slave.getGender().getName())+"</span> "
									+ "<span style='color:"+slave.getRace().getColour().toWebHexString()+";'>"
										+Util.capitaliseSentence((slave.isFeminine()?slave.getSubspecies().getSingularFemaleName(slave):slave.getSubspecies().getSingularMaleName(slave)))
									+"</span>"
								+ "</div>"
								+ "<div style='float:left; width:17%; margin:0; padding:0; text-align:center;'>"
									+ "<b style='color:"+slave.getObedience().getColour().toWebHexString()+";'>"+slave.getObedienceValue()+ "</b>"
								+"</div>"
								+ "<div style='float:left; width:17%; margin:0; padding:0; text-align:center;'>"
									+ UtilText.formatAsMoney(slave.getValueAsSlave(true), "span")
								+"</div>"
								+ "<div style='float:left; width:17%; margin:0; padding:0; text-align:center;'>"
									+ UtilText.formatAsMoney((int)(slave.getValueAsSlave(true)*0.5f), "span")
								+"</div>"
								+ "<div style='float:left; width:9%; font-weight:bold; margin:0; padding:0;'>"
									+ "<div id='"+slave.getId()+"_BID' class='square-button solo'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getTransactionBid()+"</div></div>"
								+ "</div>"
							+ "</div>"
							));
					i++;
				}
			}
			
			UtilText.nodeContentSB.append("</p>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Import", "View the character import screen.", AUCTION_IMPORT);
				
			} else if(index==0) {
				return new Response("Back", "Walk away from the auction block.", AUCTION_BLOCK);
				
			} else {
				return null;
			}
		}
	};
	
	public static void setupBidding(NPC slaveToBidOn) {
		biddingNPC = slaveToBidOn;
		biddingPrice = (int) (biddingNPC.getValueAsSlave(true)*0.5f);
		biddingRoundsTotal = Util.random.nextInt(3)+1;
		biddingRounds = 0;
		playerBidLeader = false;
		currentRivalBidder = SlaveAuctionBidder.generateNewSlaveAuctionBidder(biddingNPC);
	}
	
	public static final DialogueNode AUCTION_IMPORT = new DialogueNode("Auctioning block", ".", true) {

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();

			saveLoadSB.append(
					"<p>"
						+ "You can import any of your previously exported characters in order to add them to the auctioning list."
						+ " (Export any character in the game by viewing their character sheet and pressing the little export button in the top-right of the screen.)"
					+ "</p>"
					+ "<p>"
						+ "<table align='center'>");
			
			Main.getSlavesForImport().sort(Comparator.comparingLong(File::lastModified).reversed());
			
			for(File f : Main.getSlavesForImport()){
				saveLoadSB.append(getImportRow(f.getName()));
			}
			
			saveLoadSB.append("</table>"
					+ "</p>"
					+ "<p id='hiddenPField' style='display:none;'></p>");
			
			return saveLoadSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			
			if(index==0) {
				return new Response("Back", "Return to the previous screen.", AUCTION_BLOCK_LIST);
				
			} else {
				return null;
			}
		}
	};
	
	private static NPC biddingNPC = null;
	private static int biddingPrice = 0;
	private static int biddingRounds = 0;
	private static int biddingRoundsTotal = 1;
	private static boolean playerBidLeader = false;
	private static SlaveAuctionBidder currentRivalBidder = null;
	
	public static final DialogueNode AUCTION_BIDDING = new DialogueNode("Auctioning block", ".", true) {
		
		@Override
		public boolean isContinuesDialogue() {
			return biddingRounds!=0;
		}
		
		@Override
		public String getContent() {
			if(biddingRounds==0) {
				return UtilText.parse(biddingNPC,
						"<p>"
							+ "Deciding that you'd like to try and bid on [npc.name], you hang around the area until [npc.sheIs] up for auction."
							+ " Thankfully, you don't have to wait long, and soon enough you see the [npc.race], stark naked except for the slave collar around [npc.her] neck, being led up onto the platform."
						+ "</p>"
						+ "<p>"
							+ "Within moments, the bidding starts, and "+currentRivalBidder.getName(true)+" quickly matches the starting price, muttering, "
								+ UtilText.parseNPCSpeech(currentRivalBidder.getRandomBiddingComment(), (currentRivalBidder.getGender().isFeminine()?Femininity.FEMININE:Femininity.MASCULINE_STRONG))
						+ "</p>"
						+ "<p>"
							+ "<i>The current bid is "+UtilText.formatAsMoney(biddingPrice, "span")+", which means that you'll need to bid "+UtilText.formatAsMoney(biddingPrice+100, "span")+" to get in the lead for buying [npc.name].</i>"
						+ "</p>");
				
			} if(biddingRounds==biddingRoundsTotal) {
				if(playerBidLeader) {
					return UtilText.parse(biddingNPC,
							"<p>"
								+ "The "+currentRivalBidder.getName(false)+" backs out of the bidding, sighing, "
								+ UtilText.parseNPCSpeech(currentRivalBidder.getRandomFailedBiddingComment(), (currentRivalBidder.getGender().isFeminine()?Femininity.FEMININE:Femininity.MASCULINE_STRONG))
							+ "</p>"
							+ "<p>"
								+ "Nobody else in the crowd offers another bid, leaving the auctioneer to call out,"
								+ " [maleNPC.speech(Going once... Going twice... Sold! To the [pc.race] at the back!)]"
							+ "</p>"
							+ "<p>"
								+ "Walking towards the stage, you pay the auctioneer's assistant the amount that you bid, totalling "+UtilText.formatAsMoney(biddingPrice, "span")+"."
								+ " She informs you that your new slave will be ready for collection from the Slavery Administration building, before handing over the paperwork which proves your ownership of [npc.name]."
							+ "</p>");
				} else {
					return UtilText.parse(biddingNPC,
							"<p>"
								+ "You back out of the bidding, which allows the "+currentRivalBidder.getName(false)+" you were competing with to win the auction."
							+ "</p>"
							+ "<p>"
								+ "Nobody else in the crowd offers another bid, leaving the auctioneer to call out,"
								+ " [maleNPC.speech(Going once... Going twice... Sold! To the "+currentRivalBidder.getName(false)+" near the back!)]"
							+ "</p>"
							+"<p>"
								+ "As the "+currentRivalBidder.getName(false)+" walks towards the stage to finalise their purchase of [npc.name], you hear them mutter, "
								+ UtilText.parseNPCSpeech(currentRivalBidder.getRandomSuccessfulBiddingComment(), (currentRivalBidder.getGender().isFeminine()?Femininity.FEMININE:Femininity.MASCULINE_STRONG))
							+ "</p>");
				}
				
			} else {
				return UtilText.parse(biddingNPC,
						"<p>"
							+ "The "+currentRivalBidder.getName(false)+" continues to bid against someone else, taking [npc.namePos] asking price up to "+UtilText.formatAsMoney(biddingPrice, "span")+"."
						+ "</p>"
						+ "<p>"
							+ "<i>The current bid is "+UtilText.formatAsMoney(biddingPrice, "span")+", which means that you'll need to bid "+UtilText.formatAsMoney(biddingPrice+100, "span")+" to get in the lead for buying [npc.name].</i>"
						+ "</p>");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(biddingRounds==biddingRoundsTotal) {
				if(index==1) {
					if(playerBidLeader) {
						return new Response("Continue", UtilText.parse(biddingNPC, "You won the bidding! [npc.Name] is now ready for collection from Slavery Administration."), AUCTION_BLOCK) {
							@Override
							public void effects() {
							}
						};
					} else {
						return new Response(UtilText.parse(biddingNPC, "[npc.Name] sold"), "You didn't win the auction, but there's always next time, right?", AUCTION_BLOCK) {
							@Override
							public void effects() {
								Main.game.getNpc(Finch.class).removeSlave(biddingNPC);
								Main.game.banishNPC(biddingNPC);
							}
						};
					}
					
				} else {
					return null;
				}
			
			} else {
				if(index==1) {
					if(Main.game.getPlayer().getMoney()>=biddingPrice+100) {
						return new Response("Bid "+UtilText.formatAsMoney(biddingPrice+100, "span"), UtilText.parse(biddingNPC, "Place a bid of "+(biddingPrice+100)+" flames for [npc.name]."), AUCTION_BIDDING) {
							@Override
							public void effects() {
								biddingPrice += 100;
								playerBidLeader = true;
								increaseBid();
								if(biddingRounds==biddingRoundsTotal) {
									Main.game.getPlayer().addSlave(biddingNPC);
									biddingNPC.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
									Main.game.getPlayer().incrementMoney(-biddingPrice);
								}
							}
						};
					} else {
						return new Response("Bid "+UtilText.formatAsMoneyUncoloured(biddingPrice+100, "span"), "You can't afford a bid of "+(biddingPrice+100)+" flames, so you'll have to let this slave go to someone else.", null);
					}
					
				} else if(index==2) {
					return new Response("Stop bidding", UtilText.parse(biddingNPC, "Stop bidding, which will allow someone else to buy [npc.name]."), AUCTION_BIDDING) {
						@Override
						public void effects() {
							playerBidLeader = false;
							biddingRounds=biddingRoundsTotal;
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	private static void increaseBid() {
		biddingRounds++;
		if(biddingRounds!=biddingRoundsTotal) {
			biddingPrice = (int) (biddingPrice * (1+(0.8f*Math.random())));
			playerBidLeader = false;
		}
	}
	
	

	private static String getImportRow(String name) {
		String baseName = Util.getFileName(name);
		String identifier = Util.getFileIdentifier(name);
		
		return "<tr>"
				+ "<td style='min-width:200px;'>"
					+ baseName
				+ "</td>"
				+ "<td>"
					+ "<div class='saveLoadButton' id='import_slave_" + identifier + "' style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Import</div>"
				+ "</td>"
				+ "</tr>";
	}
	
	public static final DialogueNode PUBLIC_STOCKS = new DialogueNode("Public Stocks", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
						+ "The collection of twenty-or-so public stocks, positioned right in the middle of a wide courtyard, is the first thing anyone sees when entering Slaver Alley."
						+ " Used as a means of punishment for disobedient slaves, each one of the devices consists of a wooden frame, with holes for securing the occupant's arms and head."
						+ " A small sign positioned next to each one informs members of the public as to what kinds of use have been permitted by the slave's owner."
					+ "</p>"
					+ "<p>"
						+ "About half of the stocks in front of you are currently occupied, with most of the slaves on offer already being used by members of the public."
						+ " As you walk past, you see that a few of the occupants are currently available..."
					+ "</p>");

			List<String> sexAvailability = new ArrayList<>();

			List<NPC> charactersPresent = Main.game.getNonCompanionCharactersPresent();
			
			for(NPC npc : charactersPresent) {
				UtilText.nodeContentSB.append(UtilText.parse(npc, 
						"<p>"
							+ "[npc.Name]," + (npc.getOwner().isPlayer()?" <b style=color:"+Colour.GENERIC_ARCANE.toWebHexString()+";>who is your slave</b>, and is":"")
							+ " <span style='color:"+npc.getGender().getColour().toWebHexString()+";'>[npc.a_gender]</span>"
									+ " <span style='color:"+npc.getRace().getColour().toWebHexString()+";'>[npc.race]</span>, has been marked as available for"));
				
				sexAvailability.clear();
				if(npc.hasSlaveJobSetting(SlaveJobSetting.SEX_ORAL)) {
					sexAvailability.add(" <b style='color:"+Colour.BASE_PINK_LIGHT.toWebHexString()+";'>oral</b>");
				}
				if(npc.hasSlaveJobSetting(SlaveJobSetting.SEX_VAGINAL)) {
					sexAvailability.add(" <b style='color:"+Colour.BASE_PINK.toWebHexString()+";'>vaginal</b>");
				}
				if(npc.hasSlaveJobSetting(SlaveJobSetting.SEX_ANAL)) {
					sexAvailability.add(" <b style='color:"+Colour.BASE_PINK_DEEP.toWebHexString()+";'>anal</b>");
				}
				
				if(!sexAvailability.isEmpty()) {
					UtilText.nodeContentSB.append(
							Util.stringsToStringList(sexAvailability, false)
							+" use.</p>");
				} else {
					UtilText.nodeContentSB.append(
							" [style.boldBad(no penetration at all)].</p>");
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			List<NPC> charactersPresent = Main.game.getNonCompanionCharactersPresent();
			
			if(index==0) {
				return new Response("Complain", "You don't like the idea of slaves being publicly used. There appears to be an enforcer watching over the area, so perhaps you should go and complain to him... (Not yet implemented!)", null);
				
			} else if(index <= charactersPresent.size()) {
				GameCharacter slave = charactersPresent.get(index-1);
				boolean ownedByPlayer = slave.isSlave() && slave.getOwner().isPlayer();
				return new ResponseSex(
						"Use "+slave.getName(true),
						UtilText.parse(slave, "Walk up to [npc.name] and have some fun..."),
						false, false,
						new SMStocks(
								ownedByPlayer || slave.hasSlaveJobSetting(SlaveJobSetting.SEX_VAGINAL),
								ownedByPlayer || slave.hasSlaveJobSetting(SlaveJobSetting.SEX_ANAL),
								ownedByPlayer || slave.hasSlaveJobSetting(SlaveJobSetting.SEX_ORAL),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStocks.BEHIND_STOCKS)),
								Util.newHashMapOfValues(new Value<>(slave, SexSlotStocks.LOCKED_IN_STOCKS))),
						null,
						null,
						AFTER_STOCKS_SEX,
						"<p>"
							+ "Deciding that you'd like to have some fun with the [npc.race] in the stocks nearest to you, you walk up behind [npc.herHim]."
							+ " [npc.She] lets out a little [npc.moan] as [npc.she] hears you, and shifts [npc.her] [npc.hips+] in anticipation of what's about to happen..."
						+ "</p>") {
					@Override
					public void effects() {
						Main.game.setActiveNPC((NPC) slave);
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_STOCKS_SEX = new DialogueNode("Public Stocks", ".", true) {

		@Override
		public String getContent() {
			return "<p>"
						+ "Having had your fun with [npc.name], you step back, grinning as you hear [npc.herHim] let out [npc.a_moan+]."
						+ " A few people had stopped to watch you using the helpless [npc.race], and some of them compliment you on your performance before moving forwards to have a turn themselves..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue on your way.", PUBLIC_STOCKS);
			} else {
				return null;
			}
		}
	};
	
	
}
