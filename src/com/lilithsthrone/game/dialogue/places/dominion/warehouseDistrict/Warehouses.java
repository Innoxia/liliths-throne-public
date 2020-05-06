package com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict;

import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.7.5
 * @version 0.3.7.5
 * @author Innoxia
 */
public class Warehouses {
	
	public static final DialogueNode WAREHOUSE_DISTRICT = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/warehouses", "WAREHOUSE_DISTRICT"));
			
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR)) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "WAREHOUSE_DISTRICT_DOMINION_EXPRESS"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/warehouses", "WAREHOUSE_DISTRICT_NO_BUSINESS"));
			}
			
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR)) {
				if(index==1) {
					if(!Main.game.isExtendedWorkTime()) {
						return new Response("Dominion Express",
								"Dominion Express is currently closed to visiting members of the public. If you wanted to meet with Natalya again, you'll have to return here between the hours of [unit.time(6)]-[unit.time(22)].",
								null);
					}
					return new Response("Dominion Express",
							"Enter the main warehouse of Natalya's delivery company, Dominion Express.<br/>[style.italicsBad(Natalya's content is very rough in this version! I advise you to wait until v0.3.8 to play it!)]",
							DominionExpress.INITIAL_ENTRANCE) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_BAD;
						}
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.DOMINION_EXPRESS, PlaceType.DOMINION_EXPRESS_EXIT);
						}
					};
				}
			}
			return null;
		}
	};

}
