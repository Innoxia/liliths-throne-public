package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.world.places.AbstractPlaceUpgrade;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.4.7.1
 * @version 0.4.7.1
 * @author Innoxia
 */
public class LilayaDiningHallDialogue {

	private static GameCharacter waiter;
	
	private static SexManagerDefault getDessertSexManager(AbstractSexPosition position,
			Map<GameCharacter, SexSlot> domSlots,
			Map<GameCharacter, SexSlot> subSlots,
			Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap) {
		return new SexManagerDefault(true,
				position,
				domSlots,
				subSlots) {
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				return exposeAtStartOfSexMap;
			}
			@Override
			public String getDeskName() {
				return "table";
			}
		};
	}
	
	public static final DialogueNode ROOM_DINING_HALL = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(LilayaHomeGeneric.getBaseRoomDescription());
			
			sb.append("<p>"
						+ "<b style='color:"+PlaceUpgrade.LILAYA_DINING_HALL.getColour().toWebHexString()+";'>"+PlaceUpgrade.LILAYA_DINING_HALL.getName()+"</b><br/>"
						+ PlaceUpgrade.LILAYA_DINING_HALL.getRoomDescription(Main.game.getPlayerCell())
					+ "</p>");
			
			for(AbstractPlaceUpgrade up : Main.game.getPlayerCell().getPlace().getPlaceUpgrades()) {
				if(!up.isCoreRoomUpgrade()) {
					sb.append("<p>"
								+ "<b style='color:"+up.getColour().toWebHexString()+";'>"+up.getName()+"</b><br/>"
								+ up.getRoomDescription(Main.game.getPlayerCell())
							+ "</p>");
				}
			}
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			
			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			List<NPC> slavesAssignedToRoom = new ArrayList<>();
			slavesAssignedToRoom.addAll(charactersPresent);
			
			if(index==0) {
				return null;
				
			} else if (index == 1) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("Manage room", "Enter the management screen for this particular room.", OccupantManagementDialogue.ROOM_UPGRADES) {
						@Override
						public void effects() {
							OccupantManagementDialogue.cellToInspect = Main.game.getPlayerCell();
						}
					};
				} else {
					return new Response("Manage room", "You need a slaver license or permission from Lilaya to house your friends or dolls in order to access this menu!",  null);
				}
				
			} else if (index == 2) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("Manage people", "Enter the management screen for your slaves and friendly occupants.", OccupantManagementDialogue.getSlaveryRoomListDialogue(null, null)) {
						@Override
						public void effects() {
							CompanionManagement.initManagement(Main.game.getDefaultDialogue(), 0, null);
						}
					};
				} else {
					return new Response("Manage people", "You need a slaver license or permission from Lilaya to house your friends or dolls in order to access this menu!",  null);
				}
				
			} else if (index == 3) {
				if(Main.game.getOccupancyUtil().getCharactersCurrentlyAtJob(SlaveJob.KITCHEN).isEmpty()) {
					return new Response("Have a meal", "You need at least one slave working as a cook to be able to order a meal...",  null);
					
				} else if(LilayaHomeGeneric.getSlavesAndOccupantsPresent().isEmpty()) {
					return new Response("Have a meal", "You need at least one slave working in this dining hall as a waiter to be able to order a meal...",  null);
					
				} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Have a meal", "You need to be able to access your mouth to be able to order a meal...",  null);
					
				} else {
					return new Response("Order a meal", "Sit down at the table and order a meal.", ROOM_DINING_HALL_ORDER_MEAL);
				}
				
			}
			
			int indexPresentStart = 4;
			if(index-indexPresentStart<slavesAssignedToRoom.size()) {
				NPC character = slavesAssignedToRoom.get(index-indexPresentStart);
				if(charactersPresent.contains(character) || (character.getHomeCell().equals(Main.game.getPlayerCell()) && Main.game.getPlayer().getCompanions().contains(character))) {
					return LilayaHomeGeneric.interactWithNPC(character);
					
				} else {
					return new Response(UtilText.parse(character, "[npc.Name]"), UtilText.parse(character, "Although this is [npc.namePos] room, [npc.sheIs] out at work at the moment."), null);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode ROOM_DINING_HALL_ORDER_MEAL = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/diningHall", "ROOM_DINING_HALL_ORDER_MEAL", LilayaHomeGeneric.getSlavesAndOccupantsPresent().get(0));
		}
		@Override
		public Response getResponse(int responseTab, int index) {

			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			List<NPC> slavesAssignedToRoom = new ArrayList<>();
			slavesAssignedToRoom.addAll(charactersPresent);
			
			if (index == 0) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("Nevermind", "Change your mind and decide against having a meal after all.", ROOM_DINING_HALL);
				}
				
			}
			int indexPresentStart = 1;
			if(index-indexPresentStart<slavesAssignedToRoom.size()) {
				NPC character = slavesAssignedToRoom.get(index-indexPresentStart);
				if(charactersPresent.contains(character) || (character.getHomeCell().equals(Main.game.getPlayerCell()) && Main.game.getPlayer().getCompanions().contains(character))) {
					return new Response(
							UtilText.parse(character, "[npc.Name]"),
							UtilText.parse(character, "Call over [npc.name] and tell [npc.herHim] that [npc.she]'ll be your "+(character.isFeminine()?"waitress":"waiter")+", before ordering a meal."),
							ROOM_DINING_HALL_RECEIVE_MEAL) {
						@Override
						public Colour getHighlightColour() {
							return character.getFemininity().getColour();
						}
						@Override
						public void effects() {
							waiter = character;
							Main.game.getPlayer().applyFoodConsumed(ROOM_DINING_HALL_RECEIVE_MEAL.getSecondsPassed()/60);
							Main.game.getPlayer().applyDrinkConsumed(ROOM_DINING_HALL_RECEIVE_MEAL.getSecondsPassed()/60);
						}
					};
				}
			}
			
			return null;
		}
	};

	public static final DialogueNode ROOM_DINING_HALL_RECEIVE_MEAL = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/diningHall", "ROOM_DINING_HALL_RECEIVE_MEAL", waiter);
		}
		@Override
		public Response getResponse(int responseTab, int index) {

			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			List<NPC> slavesAssignedToRoom = new ArrayList<>();
			slavesAssignedToRoom.addAll(charactersPresent);
			
			if (index == 1) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("Finished", "Having had your meal.", ROOM_DINING_HALL);
				}
				
			} else if(index==2) {
				if(!waiter.hasPenis()) {
					return new Response("Dessert (blowjob)", UtilText.parse(waiter, "As [npc.name] doesn't have a penis, you can't enjoy it as a dessert..."), null);
					
				} else if(!waiter.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					return new Response("Dessert (blowjob)", UtilText.parse(waiter, "[npc.Name] isn't able to access [npc.her] cock, so you can't enjoy it as a dessert..."), null);
					
				} else {
					return new ResponseSex("Dessert (blowjob)", UtilText.parse(waiter, "Tell [npc.name] to go to the kitchen and have [npc.her] cock turned into a dessert, before returning here so that you can suck it."), 
							true, false,
							getDessertSexManager(
									waiter.isTaur()
										?SexPosition.SITTING
										:SexPosition.OVER_DESK,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), waiter.isTaur()?SexSlotSitting.SITTING:SexSlotDesk.PERFORMING_ORAL)),
									Util.newHashMapOfValues(new Value<>(waiter, waiter.isTaur()?SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL:SexSlotDesk.OVER_DESK_ON_BACK)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)),
											new Value<>(waiter, Util.newArrayListOfValues(CoverableArea.PENIS)))),
							SlaveDialogue.getDominantSpectators(),
							SlaveDialogue.getSubmissiveSpectators(),
							AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/diningHall", "ROOM_DINING_HALL_RECEIVE_MEAL_DESSERT_COCK", waiter)) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), waiter, PenisMouth.GIVING_BLOWJOB_START, false, true));
						}
					};
				}
				
			} else if(index==3) {
				if(!waiter.hasVagina()) {
					return new Response("Dessert (cunnilingus)", UtilText.parse(waiter, "As [npc.name] doesn't have a pussy, you can't enjoy it as a dessert..."), null);
					
				} else if(!waiter.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					return new Response("Dessert (cunnilingus)", UtilText.parse(waiter, "[npc.Name] isn't able to access [npc.her] pussy, so you can't enjoy it as a dessert..."), null);
					
				} else {
					return new ResponseSex("Dessert (cunnilingus)", UtilText.parse(waiter, "Tell [npc.name] to go to the kitchen and have [npc.her] pussy turned into a dessert, before returning here so that you can eat it."), 
							true, false,
							getDessertSexManager(
									waiter.isTaur()
										?SexPosition.SITTING
										:SexPosition.OVER_DESK,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), waiter.isTaur()?SexSlotSitting.SITTING:SexSlotDesk.PERFORMING_ORAL)),
									Util.newHashMapOfValues(new Value<>(waiter, waiter.isTaur()?SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL:SexSlotDesk.OVER_DESK_ON_BACK)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)),
											new Value<>(waiter, Util.newArrayListOfValues(CoverableArea.VAGINA)))),
							SlaveDialogue.getDominantSpectators(),
							SlaveDialogue.getSubmissiveSpectators(),
							AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/diningHall", "ROOM_DINING_HALL_RECEIVE_MEAL_DESSERT_PUSSY", waiter)) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), waiter, TongueVagina.CUNNILINGUS_START, false, true));
						}
					};
				}
				
			} else if(index==4 && Main.game.isAnalContentEnabled()) {
				if(!waiter.isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
					return new Response("Dessert (anilingus)", UtilText.parse(waiter, "[npc.Name] isn't able to access [npc.her] asshole, so you can't enjoy it as a dessert..."), null);
					
				} else {
					return new ResponseSex("Dessert (anilingus)", UtilText.parse(waiter, "Tell [npc.name] to go to the kitchen and have [npc.her] ass turned into a dessert, before returning here so that you can eat it."), 
							true, false,
							getDessertSexManager(
									waiter.isTaur()
										?SexPosition.SITTING
										:SexPosition.OVER_DESK,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), waiter.isTaur()?SexSlotSitting.SITTING:SexSlotDesk.PERFORMING_ORAL)),
									Util.newHashMapOfValues(new Value<>(waiter, waiter.isTaur()?SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL:SexSlotDesk.OVER_DESK_ON_FRONT)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)),
											new Value<>(waiter, Util.newArrayListOfValues(CoverableArea.ANUS)))),
							SlaveDialogue.getDominantSpectators(),
							SlaveDialogue.getSubmissiveSpectators(),
							AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/diningHall", "ROOM_DINING_HALL_RECEIVE_MEAL_DESSERT_ASS", waiter)) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), waiter, TongueAnus.ANILINGUS_START, false, true));
						}
					};
				}
			}
			
			return null;
		}
	};

	public static final DialogueNode AFTER_SEX = new DialogueNode("Step back", "", true) {
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave [npc.name] to recover.";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/diningHall", "ROOM_DINING_HALL_AFTER_SEX", waiter);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Decide what to do next.", ROOM_DINING_HALL);
				
			} else {
				return null;
			}
		}
	};
}
