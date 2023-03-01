package com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
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
			
			boolean businessFound = false;
			if(Main.game.getPlayer().hasItemType(ItemType.NATALYA_BUSINESS_CARD) || Main.game.getPlayer().hasItemType(ItemType.NATALYA_BUSINESS_CARD_STAMPED)) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "WAREHOUSE_DISTRICT_DOMINION_EXPRESS"));
				businessFound = true;
			}
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_1_STOCK_ISSUES)) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "WAREHOUSE_DISTRICT_KAYS_TEXTILES"));//TODO
				businessFound = true;
			}
			if(!businessFound){
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/warehouses", "WAREHOUSE_DISTRICT_NO_BUSINESS"));
			}
			
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			if((Main.game.getPlayer().hasItemType(ItemType.NATALYA_BUSINESS_CARD) || Main.game.getPlayer().hasItemType(ItemType.NATALYA_BUSINESS_CARD_STAMPED))
					&& !Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_NATALYA)) {
				if(!Main.game.isExtendedWorkTime() && !Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)) {
					responses.add(new Response("Dominion Express",
									"Dominion Express is currently closed to visiting members of the public. If you wanted to meet with Natalya again, you'll have to return here between the hours of [unit.time(6)]-[unit.time(22)].",
									null));
				}
				responses.add(new Response("Dominion Express",
								"Enter the main warehouse of Natalya's delivery company, Dominion Express.",
								DominionExpress.INITIAL_ENTRANCE) {
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.DOMINION_EXPRESS, PlaceType.DOMINION_EXPRESS_EXIT);
							}
						});
			}
			
			// Nyan quest:
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_1_STOCK_ISSUES)) {
				if(!Main.game.isExtendedWorkTime()) {
					responses.add(new Response(WorldType.TEXTILES_WAREHOUSE.getName(),
									"A sign near the entrance to the warehouse which houses '"+WorldType.TEXTILES_WAREHOUSE.getName()+"' declares that it's only open during the hours of [unit.time(6)]-[unit.time(22)]."
										+ " As such, you'll have to come back later if you wanted to head inside...",
									null));
					
				} else {
					responses.add(new Response(WorldType.TEXTILES_WAREHOUSE.getName(),
							"Enter the warehouse which houses the business '"+WorldType.TEXTILES_WAREHOUSE.getName()+"'.",
							KaysWarehouse.INITIAL_ENTRY) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.TEXTILES_WAREHOUSE, PlaceType.TEXTILE_WAREHOUSE_ENTRANCE);
//							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "INITIAL_ENTRY"));
						}
					});
				}
			}
			
			if(index>0 && index-1<responses.size()) {
				return responses.get(index-1);
			}
			
			return null;
		}
	};

}
