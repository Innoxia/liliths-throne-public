package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.SupplierLeader;
import com.lilithsthrone.game.character.npc.dominion.SupplierPartner;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.99
 * @version 0.1.99
 * @author Innoxia
 */
public class SupplierDepot {
	
	public static void applySuppliersBeatenEffects() {
		Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_STOCK_ISSUES_SUPPLIERS_BEATEN));
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.supplierDepotDoorUnlocked, false);
	}
	
	public static final DialogueNode EXTERIOR = new DialogueNode("Supplier Depot (Exterior)", "-", false) {

		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "EXTERIOR_OPEN");
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.RELATIONSHIP_NYAN_HELP) == Quest.RELATIONSHIP_NYAN_STOCK_ISSUES_SUPPLIERS_BEATEN) {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "EXTERIOR_REFURBISHING");
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.RELATIONSHIP_NYAN_HELP) == Quest.RELATIONSHIP_NYAN_STOCK_ISSUES_AGREED_TO_HELP) {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "EXTERIOR_CLOSED_NYAN_INFO");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "EXTERIOR_CLOSED");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1 && Main.game.getPlayer().hasQuest(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				if(Main.game.getPlayer().getQuest(QuestLine.RELATIONSHIP_NYAN_HELP) == Quest.RELATIONSHIP_NYAN_STOCK_ISSUES_SUPPLIERS_BEATEN) {
					return new Response("Enter", "The door is firmly locked while the suppliers are moving back in.", null);
					
				} else {
					return new Response("Enter", "Push open the door and enter the Depot.", SUPPLIER_DEPOT_RECEPTION) {
						@Override
						public void effects() {
							Main.mainController.moveGameWorld(WorldType.SUPPLIER_DEN, PlaceType.SUPPLIER_DEPOT_ENTRANCE, true);
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SUPPLIER_DEPOT_RECEPTION = new DialogueNode("Reception Area", "-", false) {

		@Override
		public int getSecondsPassed() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				return 60;
			} else {
				return 30;
			}
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "RECEPTION_POPULATED"));
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.supplierDepotDoorUnlocked)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "RECEPTION_POPULATED_STAFF_DOOR"));
				}
				return UtilText.nodeContentSB.toString();
			}
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "RECEPTION_EMPTY"));
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.supplierDepotDoorUnlocked)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "RECEPTION_EMPTY_STAFF_DOOR"));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				if (index == 1) {
					return new ResponseEffectsOnly("Exit", "Decide to leave the Depot for now. You can always come back at another time.") {
						@Override
						public void effects() {
							Main.mainController.moveGameWorld(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_SUPPLIER_DEPOT, true);
						}
					};
					
				} else if (index == 2 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.supplierDepotDoorUnlocked)) {
					return new Response("Staff Door", "Ask the receptionist if you can go into the back room to see [wolfgang.name] again.", SUPPLIER_DEPOT_POPULATED_UNLOCKING) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.supplierDepotDoorUnlocked, true);
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new ResponseEffectsOnly("Exit", "Decide to leave the Depot for now. You can always come back at another time.") {
						@Override
						public void effects() {
							Main.mainController.moveGameWorld(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_SUPPLIER_DEPOT, true);
						}
					};
					
				} else if (index == 2 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.supplierDepotDoorUnlocked)) {
					return new Response("Staff Door", "Try and open the door marked 'Staff Only'.", SUPPLIER_DEPOT_RECEPTION_UNLOCKING) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.supplierDepotDoorUnlocked, true);
						}
					};
					
				} else {
					return null;
				}
			}
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.supplierDepotDoorUnlocked);
		}
	};
	
	public static final DialogueNode SUPPLIER_DEPOT_RECEPTION_UNLOCKING = new DialogueNode("Reception Area", "-", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "RECEPTION_EMPTY_STAFF_DOOR_UNLOCKING");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SUPPLIER_DEPOT_RECEPTION.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SUPPLIER_DEPOT_POPULATED_UNLOCKING = new DialogueNode("Reception Area", "-", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "RECEPTION_POPULATED_STAFF_DOOR_UNLOCKING");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SUPPLIER_DEPOT_RECEPTION.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNode SUPPLIER_DEPOT_CORRIDOR = new DialogueNode("Corridor", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 20;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "CORRIDOR");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode SUPPLIER_DEPOT_STORAGE_ROOM = new DialogueNode("Storage Room", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "STORAGE_ROOM"));
			
			if(Main.game.getDialogueFlags().supplierStorageRoomsChecked.contains(Main.game.getPlayer().getLocation())) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "STORAGE_ROOM_SEARCHED"));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getDialogueFlags().supplierStorageRoomsChecked.contains(Main.game.getPlayer().getLocation())) {
					return new Response("Search Crates", "You've already searched this room!", null);
				} else {
					return new Response("Search Crates", "Search through the crates to try and find something valuable.", SUPPLIER_DEPOT_STORAGE_ROOM_SEARCH) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().supplierStorageRoomsChecked.add(Main.game.getPlayer().getLocation());
							
							List<AbstractClothingType> clothingToGenerate = new ArrayList<>(ClothingType.getAllClothing());
							clothingToGenerate.removeIf((clothing) -> !clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_NYAN));
							
							Main.game.getTextEndStringBuilder().append(
									UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "STORAGE_ROOM_SEARCHING")
									+ Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(Util.randomItemFrom(clothingToGenerate), false), false)
									+ Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(Util.randomItemFrom(clothingToGenerate), false), false)
									+ (Math.random()>0.5?Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(Util.randomItemFrom(clothingToGenerate), false), false):"")
									+ (Math.random()>0.5?Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(Util.randomItemFrom(clothingToGenerate), false), false):""));
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SUPPLIER_DEPOT_STORAGE_ROOM_SEARCH = new DialogueNode("Storage Room", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "STORAGE_ROOM"));
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Search Crates", "You've already searched this room!", null);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SUPPLIER_DEPOT_OFFICE = new DialogueNode("Office", "-", true) {
		
		@Override
		public String getContent() {
			if(Main.game.getPlayer().hasAnyEnforcerStatusEffect()) {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_ENFORCER_REACTION");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().hasAnyEnforcerStatusEffect()) {
					return new Response("Convince", "It'd be better to try and play on the fact that Wolfgang has mistaken you for an Enforcer...", null);
				} else {
					return new Response("Convince", "Try and convince Wolfgang and Karl to let the other suppliers return.", SUPPLIER_DEPOT_OFFICE_CONVINCE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersTriedConvincing, true);
						}
					};
				}
				
			} else if (index == 2) {
				if(Main.game.getPlayer().hasAnyEnforcerStatusEffect()) {
					return new Response("Enforcer Bluff", "Use this opportunity to convince Wolfgang and Karl to let the other suppliers return.", SUPPLIER_DEPOT_OFFICE_ENFORCER_BLUFF) {
						@Override
						public void effects() {
							applySuppliersBeatenEffects();
						}
					};
					
				} else {
					return new Response("Enforcer Bluff", "You'd need to be wearing an Enforcer's uniform in order to attempt this!", null);
				}
				
			} else if (index == 3) {
				return new ResponseCombat("Fight", "Immediately launch into combat!",
						Main.game.getNpc(SupplierLeader.class),
						Util.newArrayListOfValues(
								Main.game.getNpc(SupplierLeader.class),
								Main.game.getNpc(SupplierPartner.class)),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), "[pc.speech(You both need to agree to let the other suppliers back,)]"
										+ " you declare, readying yourself for a fight,"
										+ " [pc.speech(but I know that people like you only respect force, so I'm left with no choice but to do this!)]"),
								new Value<>(Main.game.getNpc(SupplierLeader.class), "[wolfgang.speech(Hah!)] Wolfgang shouts. [wolfgang.speech(If it's a fight you want, we'll give you one!)]"),
								new Value<>(Main.game.getNpc(SupplierPartner.class), "[karl.speech(You're gonna pay for this, bitch!)] Karl snarls.")));
				
			} else {
				return null;
			}
		}
		
		@Override
		public boolean isInventoryDisabled() {
			return true;
		}
	};
	
	public static final DialogueNode SUPPLIER_DEPOT_OFFICE_REPEAT = new DialogueNode("Office", "-", true) {

		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_PACIFIED");
			}
			
			if(Main.game.getPlayer().hasAnyEnforcerStatusEffect()) {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_REPEAT_ENFORCER_REACTION");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_REPEAT");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				if (index == 1) {
					return new ResponseSex("Fuck Them",
							UtilText.parse(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class), "Push Wolfgang and Karl down side-by-side in the doggy-style position, ready to have some fun with them..."),
							Util.newArrayListOfValues(Fetish.FETISH_DOMINANT), null, null, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class)),
									null,
									null,
									ResponseTag.PREFER_DOGGY),
							AFTER_SEX_WILLING_DOMMED_THEM,
							UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_PACIFIED_FUCK_THEM"));
					
				} else if (index == 2) {
					return new ResponseSex("Get Fucked",
							UtilText.parse(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class), "Allow Wolfgang and Karl to spitroast you..."),
							null, null, null, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class)),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.PREFER_DOGGY), AFTER_SEX_WILLING, UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_PACIFIED_SUB_FUCKED"));
					
				} else if (index == 0) {
					return new ResponseEffectsOnly("Leave", "Let the pair know that you were just checking up on them, before heading back outside into the Shopping Arcade once again.") {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_PACIFIED_LEAVE"));
							Main.mainController.moveGameWorld(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_SUPPLIER_DEPOT, true);
						}
					};
				} else {
					return null;
				}
			}
			
			return SUPPLIER_DEPOT_OFFICE.getResponse(responseTab, index);
		}

		@Override
		public boolean isInventoryDisabled() {
			return true;
		}
	};
	
	public static final DialogueNode SUPPLIER_DEPOT_OFFICE_CONVINCE = new DialogueNode("Office", "-", true, true) {

		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.suppliersEncountered) && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.suppliersTriedConvincing)) {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_CONVINCE_THEM_REPEAT");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_CONVINCE_THEM");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Convince", "You are already trying to convince them!", null);
				
			} else if (index == 2) {
				if(Main.game.getPlayer().hasAnyEnforcerStatusEffect()) {
					return new Response("Enforcer Bluff", "Convince the suppliers that you're an Enforcer, and that they should do as you say.", SUPPLIER_DEPOT_OFFICE_ENFORCER_BLUFF) {
						@Override
						public void effects() {
							applySuppliersBeatenEffects();
						}
					};
					
				} else {
					return new Response("Enforcer Bluff", "You'd need to be wearing an Enforcer's uniform in order to attempt this!", null);
				}
				
			} else if (index == 3) {
				return new ResponseCombat("Fight", "It looks as though you're left with no choice but to fight!",
						Main.game.getNpc(SupplierLeader.class),
						Util.newArrayListOfValues(
								Main.game.getNpc(SupplierLeader.class),
								Main.game.getNpc(SupplierPartner.class)),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), "[pc.speech(There's no way I'm agreeing to that,)]"
										+ " you declare, readying yourself for a fight,"
										+ " [pc.speech(so I'm left with no choice but to do this!)]"),
								new Value<>(Main.game.getNpc(SupplierLeader.class), "[wolfgang.speech(Hah!)] Wolfgang shouts. [wolfgang.speech(If it's a fight you want, we'll give you one!)]"),
								new Value<>(Main.game.getNpc(SupplierPartner.class), "[karl.speech(You're gonna pay for this, bitch!)] Karl snarls.")));
				
			} else if (index == 4) {
				if(Main.game.getPlayer().getMoney()>=500) {
					return new Response("Pay ("+UtilText.formatAsMoney(500, "span")+")", "Agree to pay Wolfgang so that he'll let you go without a fight.", SUPPLIER_DEPOT_OFFICE_PAID_OFF) {
						@Override
						public void effects() {
							Main.game.getPlayer().incrementMoney(-500);
						}
					};
				} else {
					return new Response("Pay ("+UtilText.formatAsMoneyUncoloured(500, "span")+")", "", null);
				}
				
			} else if (index == 5) {
				return new ResponseSex("Offer Body",
						UtilText.parse(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class), "Offer your body to Wolfgang and Karl in order to avoid a fight..."),
						null, null, null, null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.PREFER_DOGGY), AFTER_SEX_WILLING, UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_OFFER_BODY"));
				
			} else {
				return null;
			}
		}

		@Override
		public boolean isInventoryDisabled() {
			return true;
		}
	};
	
	public static final DialogueNode SUPPLIER_DEPOT_OFFICE_PAID_OFF = new DialogueNode("Office", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_PAID_OFF");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Arcade", "You find yourself back outside in the Shopping Arcade once again.") {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, true);
						Main.mainController.moveGameWorld(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_SUPPLIER_DEPOT, true);
					}
				};
			} else {
				return null;
			}
		}

		@Override
		public boolean isInventoryDisabled() {
			return true;
		}
	};
	
	public static final DialogueNode SUPPLIER_DEPOT_OFFICE_ENFORCER_BLUFF = new DialogueNode("Office", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_ENFORCER_BLUFF");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Exit", "Tidy yourself up and leave the Depot.") {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, true);
						Main.mainController.moveGameWorld(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_SUPPLIER_DEPOT, true);
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Exit."
								+ "</p>");
					}
				};
				
			} else if (index == 2) {
				return new ResponseSex("\"Thank\" them",
						UtilText.parse(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class), "You've always fantasised about being fucked by two strong men while wearing a uniform..."),
						null, null, null, null, null, null,
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.PREFER_DOGGY), AFTER_SEX_WILLING, UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_ENFORCER_THANKS"));
				
			} else {
				return null;
			}
		}

		@Override
		public boolean isInventoryDisabled() {
			return true;
		}
	};
	
	public static final DialogueNode SUPPLIER_DEPOT_OFFICE_COMBAT_PLAYER_VICTORY = new DialogueNode("Office", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_VICTORY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Exit", "Tidy yourself up and leave the supplier's den.") {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, true);
						Main.mainController.moveGameWorld(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_SUPPLIER_DEPOT, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_VICTORY_LEAVE"));
					}
				};
				
			} else if (index == 2) {
				return new ResponseSex("Fuck Them",
						UtilText.parse(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class), "Push Wolfgang and Karl down side-by-side in the doggy-style position, ready to have some fun with them..."),
						null, null, null, null, null, null,
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class)),
								null,
								null,
								ResponseTag.PREFER_DOGGY),
						AFTER_SEX_WILLING_DOMMED_THEM,
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_VICTORY_FUCK_THEM"));
				
			} else if (index == 3) {
				return new ResponseSex("Get Fucked",
						UtilText.parse(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class), "Allow Wolfgang and Karl to spitroast you..."),
						null, null, null, null, null, null,
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.PREFER_DOGGY), AFTER_SEX_WILLING, UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_VICTORY_SUB_FUCKED"));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SUPPLIER_DEPOT_OFFICE_COMBAT_PLAYER_LOSS = new DialogueNode("Office", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_DEFEAT");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Fucked",
						UtilText.parse(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class), "You're in no state to continue fighting..."),
						null, null, null, null, null, null,
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.PREFER_DOGGY),
						AFTER_SEX_FUCKED,
						"");
				
			} else {
				return null;
			}
		}

		@Override
		public boolean isInventoryDisabled() {
			return true;
		}
	};
	
	public static final DialogueNode AFTER_SEX_WILLING_DOMMED_THEM = new DialogueNode("Office", "-", true) {

		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "AFTER_SEX_WILLING_DOMMED_THEM_PACIFIED");
			}

			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "AFTER_SEX_WILLING_DOMMED_THEM");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Exit", "Head back out into the Shopping Arcade.") {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, true);
						Main.mainController.moveGameWorld(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_SUPPLIER_DEPOT, true);
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_WILLING = new DialogueNode("Office", "-", true) {

		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "AFTER_SEX_WILLING_PACIFIED");
			}
			
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "AFTER_SEX_WILLING");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Exit", "Head back out into the Shopping Arcade.") {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, true);
						Main.mainController.moveGameWorld(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_SUPPLIER_DEPOT, true);
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_FUCKED = new DialogueNode("Office", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "AFTER_SEX_FUCKED");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Exit", "Head back out into the Shopping Arcade.") {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, true);
						Main.mainController.moveGameWorld(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_SUPPLIER_DEPOT, true);
					}
				};
			} else {
				return null;
			}
		}
	};
	
}
