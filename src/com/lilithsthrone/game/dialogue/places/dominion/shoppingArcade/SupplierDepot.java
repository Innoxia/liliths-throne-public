package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMDoggy;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
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
		Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_NYAN_HELP, Quest.SIDE_NYAN_STOCK_ISSUES_SUPPLIERS_BEATEN));
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.supplierDepotDoorUnlocked, false);
	}
	
	public static final DialogueNodeOld EXTERIOR = new DialogueNodeOld("Supplier Depot (Exterior)", "-", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_NYAN_HELP)) {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "EXTERIOR_OPEN");
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_NYAN_HELP) == Quest.SIDE_NYAN_STOCK_ISSUES_SUPPLIERS_BEATEN) {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "EXTERIOR_REFURBISHING");
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_NYAN_HELP) == Quest.SIDE_NYAN_STOCK_ISSUES_AGREED_TO_HELP) {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "EXTERIOR_CLOSED_NYAN_INFO");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "EXTERIOR_CLOSED");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1 && Main.game.getPlayer().hasQuest(QuestLine.SIDE_NYAN_HELP)) {
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_NYAN_HELP) == Quest.SIDE_NYAN_STOCK_ISSUES_SUPPLIERS_BEATEN) {
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
	
	public static final DialogueNodeOld SUPPLIER_DEPOT_RECEPTION = new DialogueNodeOld("Reception Area", "-", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_NYAN_HELP)) {
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
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_NYAN_HELP)) {
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
	
	public static final DialogueNodeOld SUPPLIER_DEPOT_RECEPTION_UNLOCKING = new DialogueNodeOld("Reception Area", "-", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "RECEPTION_EMPTY_STAFF_DOOR_UNLOCKING");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SUPPLIER_DEPOT_RECEPTION.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld SUPPLIER_DEPOT_POPULATED_UNLOCKING = new DialogueNodeOld("Reception Area", "-", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "RECEPTION_POPULATED_STAFF_DOOR_UNLOCKING");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SUPPLIER_DEPOT_RECEPTION.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNodeOld SUPPLIER_DEPOT_CORRIDOR = new DialogueNodeOld("Corridor", "-", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 1;
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
	
	public static final DialogueNodeOld SUPPLIER_DEPOT_STORAGE_ROOM = new DialogueNodeOld("Storage Room", "-", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 1;
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
							
							Main.game.getTextEndStringBuilder().append(
									UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "STORAGE_ROOM_SEARCHING")
									+ Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.getCommonClothing().get(Util.random.nextInt(ClothingType.getCommonClothing().size())), false), false)
									+ Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.getCommonClothing().get(Util.random.nextInt(ClothingType.getCommonClothing().size())), false), false)
									+ (Math.random()>0.5?Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.getCommonClothing().get(Util.random.nextInt(ClothingType.getCommonClothing().size())), false), false):"")
									+ (Math.random()>0.5?Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.getCommonClothing().get(Util.random.nextInt(ClothingType.getCommonClothing().size())), false), false):""));
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SUPPLIER_DEPOT_STORAGE_ROOM_SEARCH = new DialogueNodeOld("Storage Room", "-", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 5;
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
	
	public static final DialogueNodeOld SUPPLIER_DEPOT_OFFICE = new DialogueNodeOld("Office", "-", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.SET_ENFORCER)) {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_ENFORCER_REACTION");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().hasStatusEffect(StatusEffect.SET_ENFORCER)) {
					return new Response("Convince", "It'd be better to try and play on the fact that Wolfgang has mistaken you for an enforcer...", null);
				} else {
					return new Response("Convince", "Try and convince Wolfgang and Karl to let the other suppliers return.", SUPPLIER_DEPOT_OFFICE_CONVINCE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersTriedConvincing, true);
						}
					};
				}
				
			} else if (index == 2) {
				if(Main.game.getPlayer().hasStatusEffect(StatusEffect.SET_ENFORCER)) {
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
						Util.newArrayListOfValues(
								new ListValue<>(Main.game.getSupplierLeader()),
								new ListValue<>(Main.game.getSupplierPartner())),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), "[pc.speech(You both need to agree to let the other suppliers back,)]"
										+ " you declare, readying yourself for a fight,"
										+ " [pc.speech(but I know that people like you only respect force, so I'm left with no choice but to do this!)]"),
								new Value<>(Main.game.getSupplierLeader(), "[wolfgang.speech(Hah!)] Wolfgang shouts, [wolfgang.speech(If it's a fight you want, we'll give you one!)]"),
								new Value<>(Main.game.getSupplierPartner(), "[karl.speech(You're gonna pay for this, bitch!)] Karl snarls.")));
				
			} else {
				return null;
			}
		}
		
		@Override
		public boolean isInventoryDisabled() {
			return true;
		}
	};
	
	public static final DialogueNodeOld SUPPLIER_DEPOT_OFFICE_REPEAT = new DialogueNodeOld("Office", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_NYAN_HELP)) {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_PACIFIED");
			}
			
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.SET_ENFORCER)) {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_REPEAT_ENFORCER_REACTION");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_REPEAT");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_NYAN_HELP)) {
				if (index == 1) {
					return new ResponseSex("Fuck Them",
							UtilText.parse(Main.game.getSupplierLeader(), Main.game.getSupplierPartner(), "Push Wolfgang and Karl down side-by-side in the doggy-style position, ready to have some fun with them..."),
							Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT)), null, null, null, null, null,
							true, false,
							new SMDoggy(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getSupplierLeader(), SexPositionSlot.DOGGY_ON_ALL_FOURS),
											new Value<>(Main.game.getSupplierPartner(), SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND))),
							AFTER_SEX_WILLING_DOMMED_THEM,
							UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_PACIFIED_FUCK_THEM"));
					
				} else if (index == 2) {
					return new ResponseSex("Get Fucked",
							UtilText.parse(Main.game.getSupplierLeader(), Main.game.getSupplierPartner(), "Allow Wolfgang and Karl to spitroast you..."),
							null, null, null, null, null, null,
							true, false,
							new SMDoggy(
									Util.newHashMapOfValues(
											new Value<>(Main.game.getSupplierLeader(), SexPositionSlot.DOGGY_BEHIND),
											new Value<>(Main.game.getSupplierPartner(), SexPositionSlot.DOGGY_INFRONT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
							AFTER_SEX_WILLING,
							UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_PACIFIED_SUB_FUCKED"));
					
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
	
	public static final DialogueNodeOld SUPPLIER_DEPOT_OFFICE_CONVINCE = new DialogueNodeOld("Office", "-", true, true) {
		private static final long serialVersionUID = 1L;

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
				if(Main.game.getPlayer().hasStatusEffect(StatusEffect.SET_ENFORCER)) {
					return new Response("Enforcer Bluff", "Convince the suppliers that you're an enforcer, and that they should do as you say.", SUPPLIER_DEPOT_OFFICE_ENFORCER_BLUFF) {
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
						Util.newArrayListOfValues(
								new ListValue<>(Main.game.getSupplierLeader()),
								new ListValue<>(Main.game.getSupplierPartner())),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), "[pc.speech(There's no way I'm agreeing to that,)]"
										+ " you declare, readying yourself for a fight,"
										+ " [pc.speech(so I'm left with no choice but to do this!)]"),
								new Value<>(Main.game.getSupplierLeader(), "[wolfgang.speech(Hah!)] Wolfgang shouts, [wolfgang.speech(If it's a fight you want, we'll give you one!)]"),
								new Value<>(Main.game.getSupplierPartner(), "[karl.speech(You're gonna pay for this, bitch!)] Karl snarls.")));
				
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
						UtilText.parse(Main.game.getSupplierLeader(), Main.game.getSupplierPartner(), "Offer your body to Wolfgang and Karl in order to avoid a fight..."),
						null, null, null, null, null, null,
						true, false,
						new SMDoggy(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getSupplierLeader(), SexPositionSlot.DOGGY_BEHIND),
										new Value<>(Main.game.getSupplierPartner(), SexPositionSlot.DOGGY_INFRONT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
						AFTER_SEX_FUCKED,
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_OFFER_BODY"));
				
			} else {
				return null;
			}
		}

		@Override
		public boolean isInventoryDisabled() {
			return true;
		}
	};
	
	public static final DialogueNodeOld SUPPLIER_DEPOT_OFFICE_PAID_OFF = new DialogueNodeOld("Office", "-", true, true) {
		private static final long serialVersionUID = 1L;

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
	
	public static final DialogueNodeOld SUPPLIER_DEPOT_OFFICE_ENFORCER_BLUFF = new DialogueNodeOld("Office", "-", true) {
		private static final long serialVersionUID = 1L;

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
						UtilText.parse(Main.game.getSupplierLeader(), Main.game.getSupplierPartner(), "You've always fantasised about being fucked by two strong men while wearing a uniform..."),
						null, null, null, null, null, null,
						true, true,
						new SMDoggy(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getSupplierLeader(), SexPositionSlot.DOGGY_BEHIND),
										new Value<>(Main.game.getSupplierPartner(), SexPositionSlot.DOGGY_INFRONT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
						AFTER_SEX_WILLING,
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_ENFORCER_THANKS"));
				
			} else {
				return null;
			}
		}

		@Override
		public boolean isInventoryDisabled() {
			return true;
		}
	};
	
	public static final DialogueNodeOld SUPPLIER_DEPOT_OFFICE_COMBAT_PLAYER_VICTORY = new DialogueNodeOld("Office", "-", true) {
		private static final long serialVersionUID = 1L;

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
						UtilText.parse(Main.game.getSupplierLeader(), Main.game.getSupplierPartner(), "Push Wolfgang and Karl down side-by-side in the doggy-style position, ready to have some fun with them..."),
						null, null, null, null, null, null,
						false, false,
						new SMDoggy(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getSupplierLeader(), SexPositionSlot.DOGGY_ON_ALL_FOURS),
										new Value<>(Main.game.getSupplierPartner(), SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND))),
						AFTER_SEX_WILLING_DOMMED_THEM,
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_VICTORY_FUCK_THEM"));
				
			} else if (index == 3) {
				return new ResponseSex("Get Fucked",
						UtilText.parse(Main.game.getSupplierLeader(), Main.game.getSupplierPartner(), "Allow Wolfgang and Karl to spitroast you..."),
						null, null, null, null, null, null,
						true, true,
						new SMDoggy(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getSupplierLeader(), SexPositionSlot.DOGGY_BEHIND),
										new Value<>(Main.game.getSupplierPartner(), SexPositionSlot.DOGGY_INFRONT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
						AFTER_SEX_WILLING,
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_VICTORY_SUB_FUCKED"));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SUPPLIER_DEPOT_OFFICE_COMBAT_PLAYER_LOSS = new DialogueNodeOld("Office", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/suppliersDepot", "OFFICE_DEFEAT");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Fucked",
						UtilText.parse(Main.game.getSupplierLeader(), Main.game.getSupplierPartner(), "You're in no state to continue fighting..."),
						null, null, null, null, null, null,
						false, false,
						new SMDoggy(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getSupplierLeader(), SexPositionSlot.DOGGY_BEHIND),
										new Value<>(Main.game.getSupplierPartner(), SexPositionSlot.DOGGY_INFRONT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
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
	
	public static final DialogueNodeOld AFTER_SEX_WILLING_DOMMED_THEM = new DialogueNodeOld("Office", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_NYAN_HELP)) {
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
	
	public static final DialogueNodeOld AFTER_SEX_WILLING = new DialogueNodeOld("Office", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_NYAN_HELP)) {
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
	
	public static final DialogueNodeOld AFTER_SEX_FUCKED = new DialogueNodeOld("Office", "-", true) {
		private static final long serialVersionUID = 1L;

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
