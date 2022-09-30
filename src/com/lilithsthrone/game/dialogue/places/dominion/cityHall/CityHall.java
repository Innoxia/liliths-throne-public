package com.lilithsthrone.game.dialogue.places.dominion.cityHall;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.OccupancyUtil;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public class CityHall {
	
	private static NPC lodger;
	
	private static String getImportRow(String name) {
		String baseName = Util.getFileName(name);
		String identifier = Util.getFileIdentifier(name);
		
		return "<tr>"
				+ "<td style='min-width:200px;'>"
					+ baseName
				+ "</td>"
				+ "<td>"
					+ "<div class='saveLoadButton' id='IMPORT_LODGER_" + identifier + "' style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Import</div>"
				+ "</td>"
				+ "</tr>";
	}

	public static void setupLodger(NPC lodger) {
		CityHall.lodger = lodger;
		Main.game.setActiveNPC(lodger);
	}
	
	public static final DialogueNode OUTSIDE = new DialogueNode("City Hall", "-", false) {

		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "OUTSIDE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getHourOfDay()>=9 && Main.game.getHourOfDay()<=16) {
					return new Response("Enter", "Dominion's city hall is currently open to the public, so you could head inside if you wanted to.", CITY_HALL_FOYER) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_ENTRANCE, false);
							
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "ENTRY"));
						}
					};
					
				} else {
					return new Response("Enter", "Dominion's city hall is currently closed to the public, so if you had any business to conduct, you'll have to return between the hours of nine in the morning, and four in the afternoon.", null);
				}
				
			}
			return null;
		}
	};
	
	public static final DialogueNode CITY_HALL_FOYER = new DialogueNode("Foyer", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_FOYER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Exit", "Head out through the revolving glass doors back into Dominion.", OUTSIDE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_CITY_HALL, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CITY_HALL_INFORMATION_DESK = new DialogueNode("Information Desk", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_INFORMATION_DESK");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Name change", "Ask one of the receptionists about changing your name.", CITY_HALL_INFORMATION_DESK_NAME_CHANGE);
				
			} else if(index == 2) {
				return new Response("Property", "Ask one of the receptionists about buying or renting property in Dominion.", CITY_HALL_INFORMATION_DESK_PROPERTY);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CITY_HALL_INFORMATION_DESK_NAME_CHANGE = new DialogueNode("Information Desk", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_INFORMATION_DESK_NAME_CHANGE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Name change", "You've just asked about changing your name.", null);
				
			} else if(index == 2) {
				return new Response("Property", "Ask one of the receptionists about buying or renting property in Dominion.", CITY_HALL_INFORMATION_DESK_PROPERTY);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CITY_HALL_INFORMATION_DESK_PROPERTY = new DialogueNode("Information Desk", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_INFORMATION_DESK_PROPERTY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Name change", "Ask one of the receptionists about changing your name.", CITY_HALL_INFORMATION_DESK_NAME_CHANGE);
				
			} else if(index == 2) {
				return new Response("Property", "You've just asked about buying or renting property in Dominion.", null);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CITY_HALL_CORRIDOR = new DialogueNode("Corridor", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 15;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_CORRIDOR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode CITY_HALL_WAITING_AREA = new DialogueNode("Waiting Room", "", false) {
		@Override
		public void applyPreParsingEffects() {
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_WAITING_AREA_LODGER_QUEST"));
				Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
				
			} else if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_WAITING_AREA_LODGER_QUEST"));
				
			} else if(!OccupancyUtil.isFreeRoomAvailableForOccupant()) {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_WAITING_AREA_LODGER_NO_ROOM"));
				
			} else {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_WAITING_AREA_LODGER_AVAILABLE"));
			}
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.cityHallLodgerBoardSeen, true);
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_WAITING_AREA");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
					return new Response("Lodgers", "You don't have Lilaya's permission to house guests in her mansion, so there's no point in looking at the notice board.", null);
				}
				if(!OccupancyUtil.isFreeRoomAvailableForOccupant()) {
					return new Response("Lodgers", "You don't have a vacant guest room in which a lodger could stay, so there's no point in looking at the notice board.", null);
				}
				return new Response("Lodgers", "Take a look at the notice board and see if there are any lodgers you'd like to offer a guest room to.", CITY_HALL_WAITING_AREA_LODGER_LIST);
			}
			return null;
		}
	};
	
	public static final DialogueNode CITY_HALL_WAITING_AREA_LODGER_LIST = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_WAITING_AREA_LODGER_LIST"));
			UtilText.nodeContentSB.append(
					"<p style='text-align:center;'>"
						//+ "<b>Seeking Lodging</b>"
						+ "<div class='container-full-width' style='margin-bottom:0; text-align:center;'>"
							+ "<div style='width:40%; float:left; font-weight:bold; margin:0; padding:0;'>"
								+ "Name"
							+ "</div>"
							+ "<div style='float:left; width:17%; font-weight:bold; margin:0; padding:0; text-align:center;'>"
								+ "<b style='color:"+PresetColour.ANDROGYNOUS.toWebHexString()+";'>Gender</b>"
							+"</div>"
							+ "<div style='float:left; width:17%; font-weight:bold; margin:0; padding:0; text-align:center;'>"
								+ "<b style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>Race</b>"
							+"</div>"
							+ "<div style='float:left; width:17%; font-weight:bold; margin:0; padding:0; text-align:center;'>"
								+ "<b style='color:"+PresetColour.BASE_BROWN.toWebHexString()+";'>Job</b>"
							+"</div>"
							+ "<div style='float:left; width:9%; font-weight:bold; margin:0; padding:0; text-align:center;'>"
								+ "Approach"
							+ "</div>"
						+ "</div>");
			
			List<NPC> charactersPresent = new ArrayList<>(Main.game.getCharactersPresent());
			charactersPresent.removeIf((npc) -> Main.game.getPlayer().getCompanions().contains(npc));
			
			Collections.sort(charactersPresent, (e1, e2) -> e1.getName(true).compareTo(e2.getName(true)));
			
			if(charactersPresent.isEmpty()) {
				UtilText.nodeContentSB.append(
						"<div class='container-full-width' style='margin-bottom:0; text-align:center;'>"
								+ "<b>There are no lodgers available...</b>"
						+ "</div>");
				
			} else {
				int i=0;
				for(NPC slave : charactersPresent){
					boolean alternateBackground = i%2==0;
					
					UtilText.nodeContentSB.append(UtilText.parse(slave,
							"<div class='container-full-width inner' style='margin-bottom:0;"+(alternateBackground?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"'")+"'>"
								+ "<div style='width:40%; float:left; margin:0; padding:0; text-align:center;'>"
									+ "<span style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>"+slave.getName(true)+"</span>"
								+ "</div>"
								+ "<div style='float:left; width:17%; margin:0; padding:0; text-align:center;'>"
									+ UtilText.parse(slave, "[npc.Gender(true)]")
								+"</div>"
								+ "<div style='float:left; width:17%; margin:0; padding:0; text-align:center;'>"
									+ "<span style='color:"+slave.getSubspecies().getColour(slave).toWebHexString()+";'>"+UtilText.parse(slave, "[npc.Race]")+"</span>"
								+"</div>"
								+ "<div style='float:left; width:17%; margin:0; padding:0; text-align:center;'>"
									+ Util.capitaliseSentence(slave.getOccupation().getName(slave))
								+"</div>"
								+ "<div style='float:left; width:9%; font-weight:bold; margin:0; padding:0;'>"
									+ "<div id='"+slave.getId()+"_LODGER' class='square-button solo'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getPeopleIcon()+"</div></div>"
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
				return new Response("Import", "View the character import screen.", LODGER_IMPORT);
				
			} else if(index==0) {
				return new Response("Back", "[pc.Step] away from the notice board.", CITY_HALL_WAITING_AREA);
			}
			return null;
		}
	};
	
	public static final DialogueNode LODGER_IMPORT = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();

			saveLoadSB.append(UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "LODGER_IMPORT_START"));
			saveLoadSB.append(
					"<p>"
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
				return new Response("Back", "Return to the previous screen.", CITY_HALL_WAITING_AREA_LODGER_LIST);
			}
			return null;
		}
	};

	public static final DialogueNode CITY_HALL_APPROACH_LODGER = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_APPROACH_LODGER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Offer room",
						"Tell [npc.name] that you'd like to offer [npc.herHim] a place to stay.",
						LODGER_GIVEN_ROOM) {
					@Override
					public void effects() {
						Cell c = OccupancyUtil.getFreeRoomForOccupant();
						lodger.setLocation(c.getType(), c.getLocation(), true);
						Main.game.getPlayer().setLocation(c.getType(), c.getLocation(), false);
						Main.game.getPlayer().addFriendlyOccupant(lodger);
						Main.game.getTextEndStringBuilder().append(lodger.setAffection(Main.game.getPlayer(), 25));
					}
				};
				
			} else if(index == 2) {
				return new Response("Leave",
						"Not liking your first impression of [npc.name], you decide against offering [npc.herHim] a place to stay...",
						LODGER_DENIED);
			}
			return null;
		}
	};

	public static final DialogueNode LODGER_DENIED = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "LODGER_DENIED"));
			Main.game.banishNPC(lodger);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Having left [npc.name] to get settled into [npc.her] new room, you continue with your plans for the day...", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode LODGER_GIVEN_ROOM = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "LODGER_GIVEN_ROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Having left [npc.name] to get settled into [npc.her] new room, you continue with your plans for the day...", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode CITY_HALL_OFFICE = new DialogueNode("Private Office", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_OFFICE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode CITY_HALL_STAIRS = new DialogueNode("Staircase", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_STAIRS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
}
