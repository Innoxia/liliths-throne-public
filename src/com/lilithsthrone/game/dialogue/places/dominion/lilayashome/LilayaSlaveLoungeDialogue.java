package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.places.AbstractPlaceUpgrade;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.4.7.1
 * @version 0.4.7.1
 * @author Innoxia
 */
public class LilayaSlaveLoungeDialogue {

	public static final DialogueNode ROOM_SLAVE_LOUNGE = new DialogueNode("", "", false) {
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
						+ "<b style='color:"+PlaceUpgrade.LILAYA_SLAVE_LOUNGE.getColour().toWebHexString()+";'>"+PlaceUpgrade.LILAYA_SLAVE_LOUNGE.getName()+"</b><br/>"
						+ PlaceUpgrade.LILAYA_SLAVE_LOUNGE.getRoomDescription(Main.game.getPlayerCell())
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
				
			}  else if (index == 2) {
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
				
			}
			
			int indexPresentStart = 3;
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
}
