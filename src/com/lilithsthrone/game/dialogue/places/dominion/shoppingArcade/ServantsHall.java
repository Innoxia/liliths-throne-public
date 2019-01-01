package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Recht;
import com.lilithsthrone.game.character.npc.dominion.Wallace;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.11
 * @version 0.2.11
 * @author Max Nobody
 */
public class ServantsHall {

	public static final DialogueNode EXTERIOR = new DialogueNode("Servants' Hall (Exterior)", "-", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/servantsHall", "SERVANTS_HALL_EXTERIOR");
		}
	
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.wallaceIntroduced)) {
					return new Response("Enter", "Step inside the Servants' Hall", SLAVE_RENTAL_ENTRY_REPEAT);
				} else {
					return new Response("Enter", "Step inside the Servants' Hall", SLAVE_RENTAL_ENTRY);
				}
			} else if (index == 6) {
				return new ResponseEffectsOnly("Arcade Entrance", "Fast travel to the entrance to the arcade."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), PlaceType.SHOPPING_ARCADE_ENTRANCE, true);
					}
				};
			} else {
				return null;
			}
		}
		
		@Override
		public String getAuthor() {
			return "Max Nobody";
		}
	};
	
	public static final DialogueNode SLAVE_RENTAL_ENTRY = new DialogueNode("Servants' Hall", "-", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/servantsHall", "SERVANTS_HALL_ENTRY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("I was curious...", "Answer you were just curious as to what kind of shop this was", SLAVE_RENTAL_INTRODUCTION) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.wallaceIntroduced);
						Main.game.getNpc(Recht.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE, true); // This is only temporary until I'll need him for my grand project involving drama, passion, player enslavement, and A LOT OF DRAMA!
					}
				};
			} else {
				return null;
			}
		}
		
		@Override
		public String getAuthor() {
			return "Max Nobody";
		}
	};
	
	public static final DialogueNode SLAVE_RENTAL_INTRODUCTION = new DialogueNode("Servants' Hall", "-", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/servantsHall", "SERVANTS_HALL_INTRODUCTION");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("What the...?", "That was strange.", SLAVE_RENTAL_POST_WTF);
			} else {
				return null;
			}
		}
		
		@Override
		public String getAuthor() {
			return "Max Nobody";
		}
	};
	
	public static final DialogueNode SLAVE_RENTAL_POST_WTF = new DialogueNode("Servants' Hall", "-", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/servantsHall", "SERVANTS_HALL_POST_WTF");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Rent a slave", "You'd like to see which of his slaves are avaiable for renting<br>Not yet implemented ;.;", null);
			} else if (index == 2) {
				return new Response("Freelance", "Begin training to become a freelancer slave<br>Not yet implemented ;.;", null);
			} else if (index == 3) {
				if (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.huntingExplained)) {
					return new Response("Hunting", "Take a hunting contract", SLAVE_RENTAL_HUNT_REPEAT);
				} else {
					return new Response("Hunting", "Take a hunting contract", SLAVE_RENTAL_HUNT);
				}
			} else {
				return null;
			}
		}
		
		@Override
		public String getAuthor() {
			return "Max Nobody";
		}
	};
	
	public static final DialogueNode SLAVE_RENTAL_ENTRY_REPEAT = new DialogueNode("Servants' Hall", "-", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/servantsHall", "SERVANTS_HALL_ENTRY_REPEAT") ;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Rent a slave", "You'd like to see which of his slaves are avaiable for renting<br>Not yet implemented ;.;", null);
			} else if (index == 2) {
					return new Response("Freelance", "Begin training to become a freelancer slave<br>Not yet implemented ;.;", null);
			} else if (index == 3) {
				if (Main.game.getPlayer().getActiveContract() != null) {
					return new Response("Contract", "About that contract you took...", SLAVE_RENTAL_CONTRACT);
				} else if (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.huntingExplained)) {
					return new Response("Hunting", "Take a hunting contract<br>Not yet implemented ;.;", SLAVE_RENTAL_HUNT_REPEAT);
				} else {
					return new Response("Hunting", "Take a hunting contract<br>Not yet implemented ;.;", SLAVE_RENTAL_HUNT);
				}
			} else {
				return null;
			}
		}

		@Override
		public String getAuthor() {
			return "Max Nobody";
		}
	};
	
	public static final DialogueNode SLAVE_RENTAL_HUNT = new DialogueNode("Servants' Hall", "-", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/servantsHall", "SERVANTS_HALL_HUNT_EXPLAIN");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Choose contract", "Choose a target to hunt", SLAVE_RENTAL_HUNT_CHOOSE);
			} else {
				return null;
			}
		}
		
		@Override
		public String getAuthor() {
			return "Max Nobody";
		}
	};
	
	public static final DialogueNode SLAVE_RENTAL_HUNT_REPEAT = new DialogueNode("Servants' Hall", "-", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/servantsHall", "SERVANTS_HALL_HUNT_REPEAT");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Choose contract", "Choose a target to hunt", SLAVE_RENTAL_HUNT_CHOOSE);
			} else {
				return null;
			}
		}
		
		@Override
		public String getAuthor() {
			return "Max Nobody";
		}
	};
	
	public static final DialogueNode SLAVE_RENTAL_HUNT_CHOOSE = new DialogueNode("Servants' Hall", "-", true) {
		
		@Override
		public String getContent() { 
			if (Main.game.getPlayer().getAvailableContracts() != null && Main.game.getPlayer().getAvailableContracts().size() == 5) {
				return Main.game.getPlayer().getAvailableContracts().get(0).getDescription(1)
						+ "<br><br>"
						+ Main.game.getPlayer().getAvailableContracts().get(1).getDescription(2)
						+ "<br><br>"
						+ Main.game.getPlayer().getAvailableContracts().get(2).getDescription(3)
						+ "<br><br>"
						+ Main.game.getPlayer().getAvailableContracts().get(3).getDescription(4)
						+ "<br><br>"
						+ Main.game.getPlayer().getAvailableContracts().get(4).getDescription(5);
			} else {
				return "Standby while I analyze the intelligence profile of Max Nobody...<br>Error! Not a number! Did the player enjoy this witticism?";
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Take first contract", "Take the first contract proposed", SLAVE_RENTAL_HUNT_CHOSEN) {
					@Override
					public void effects() {
						Main.game.getPlayer().setActiveContract(0);
					}
				};
			}
			if (index == 2) {
				return new Response("Take second contract", "Take the second contract proposed", SLAVE_RENTAL_HUNT_CHOSEN) {
					@Override
					public void effects() {
						Main.game.getPlayer().setActiveContract(1);
					}
				};
			}
			if (index == 3) {
				return new Response("Take third contract", "Take the third contract proposed", SLAVE_RENTAL_HUNT_CHOSEN) {
					@Override
					public void effects() {
						Main.game.getPlayer().setActiveContract(2);
					}
				};
			}
			if (index == 4) {
				return new Response("Take fourth contract", "Take the fourth contract proposed", SLAVE_RENTAL_HUNT_CHOSEN) {
					@Override
					public void effects() {
						Main.game.getPlayer().setActiveContract(3);
					}
				};
			}
			if (index == 5) {
				return new Response("Take fifth contract", "Take the fifth contract proposed", SLAVE_RENTAL_HUNT_CHOSEN) {
					@Override
					public void effects() {
						Main.game.getPlayer().setActiveContract(4);
					}
				};
			}
			if (index == 0) {
				return new Response("Back", "Don't take any contract", SLAVE_RENTAL_HUNT_REFUSE);
			}
			else {
				return null;
			}
		}
		
		@Override
		public String getAuthor() {
			return "Max Nobody";
		}
	};
	
	public static final DialogueNode SLAVE_RENTAL_HUNT_CHOSEN = new DialogueNode("Servants' Hall", "-", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/servantsHall", "SERVANTS_HALL_HUNT_CHOSEN");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "Leave the shop to complete your hunt", EXTERIOR);
			}
			else {
				return null;
			}
		}
		
		@Override
		public String getAuthor() {
			return "Max Nobody";
		}
	};
	
	public static final DialogueNode SLAVE_RENTAL_HUNT_REFUSE = new DialogueNode("Servants' Hall", "-", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/servantsHall", "SERVANTS_HALL_HUNT_REFUSE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "Leave the shop", EXTERIOR);
			}
			else {
				return null;
			}
		}
		
		@Override
		public String getAuthor() {
			return "Max Nobody";
		}
	};
	
	public static final DialogueNode SLAVE_RENTAL_CONTRACT = new DialogueNode("Servants' Hall", "-", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/servantsHall", "SERVANTS_HALL_HUNT_CONTRACT")+"<p>"+Main.game.getPlayer().getActiveContract().getDescription(0)+"</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getCompanions().size() != 0 && hasSlaves(Main.game.getPlayer().getCompanions())) {
					return new Response("This is my slave", "Give your companion to Wallace", SLAVE_RENTAL_HUNT_CHECK) {
						@Override
						public void effects() {
							Main.game.getPlayer().getCompanions().get(takeSlave(Main.game.getPlayer().getCompanions())).setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_SERVANTS_HALL);
						}
					};
				} else {
					return new Response("This is my slave", "You don't have any slave to give as companion...", null);
				}
			} else if (index == 2) {
				return new Response("Give up", "The contract is too hard...", null) {
					@Override
					public void effects() {
						Main.game.getPlayer().setActiveContract(null);
					}
				};
			} else if (index == 0) {
				return new Response("Leave", "Leave", SLAVE_RENTAL_ENTRY_REPEAT);
			} else {
				return null;
			}
		}
		
		@Override
		public String getAuthor() {
			return "Max Nobody";
		}
		
		public boolean hasSlaves(List<GameCharacter> companions) {
			if (companions.size() == 0) {
				return false;
			}
			for (int i = 0; i < companions.size(); i++) {
				if (companions.get(i).isSlave()) {
					if (companions.get(i).getOwner() == Main.game.getPlayer()) {
						return true;
					}
				}
			}
			return false;
		}
		
		public int takeSlave(List<GameCharacter> companions) {
			if (companions.size() == 0) {
				return 0;
			}
			for (int i = 0; i < companions.size(); i++) {
				if (companions.get(i).isSlave()) {
					if (companions.get(i).getOwner() == Main.game.getPlayer()) {
						return i;
					}
				}
			}
			return 0;
		}
	};
	
	public static final DialogueNode SLAVE_RENTAL_HUNT_CHECK = new DialogueNode("Servants' Hall", "-", true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append("<p>You approach the counter, where Wallace awaits you with a predatory smile.</p>"
					+ "<p>[pc.speech(Here's my catch)], you announce"
					+ "<p>[wallace.speech(Oh, nice, really nice! Eirwen!)]</p>"
					+ "<p>The fox-girl, as rigid as usual, step forwards</p>"
					+ "<p>[eirwen.speech(Yes, Master Wallace?)]</p>"
					+ "<p>[wallace.speech(Please check our \"gift\" from [pc.name] matches the contract. There, here's the list.)]</p>"
					+ "<p>[eirwen.speech(Alright, Master Wallace.)]</p>"
					+ "<p>The fox-girl approaches your catch and brutally catch it, before roughly dragging [npc"+ServantsHall.targetSlaveGiven()+".him] to the chains on the left wall. With an expert hand, she attaches [npc"+ServantsHall.targetSlaveGiven()+".name]'s limbs to the wall, and begins examining [npc"+ServantsHall.targetSlaveGiven()+".him] from every angle.</p>");
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getActiveContract().isMatchContract(Main.game.getNonCompanionCharactersPresent().get(targetSlaveGiven() - 1))) {
					return new Response("Next", "Let Eirwen check your slave", SLAVE_RENTAL_HUNT_VALID) {
						@Override
						public void effects() {
							Main.game.getPlayer().incrementMoney((int)(Main.game.getNonCompanionCharactersPresent().get(targetSlaveGiven() - 1).getValueAsSlave() * Main.game.getPlayer().getActiveContract().getValueMultiplier()));
							Main.game.getNpc(Wallace.class).addSlave(Main.game.getNonCompanionCharactersPresent().get(targetSlaveGiven() - 1));
							Main.game.getPlayer().setActiveContract(null);
						}
					};
				}
				return new Response("Next", "Let Eirwen check your slave", SLAVE_RENTAL_HUNT_INVALID);
			} else {
				return null;
			}
		}
		
		@Override
		public String getAuthor() {
			return "Max Nobody";
		}
	};
	
	public static final DialogueNode SLAVE_RENTAL_HUNT_VALID = new DialogueNode ("Servants' Hall", "-", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/servantsHall", "SERVANTS_HALL_HUNT_COMPLETE")+"<br><br>You gained " + Math.floor(Main.game.getNonCompanionCharactersPresent().get(targetSlaveGiven() - 1).getValueAsSlave() * Main.game.getPlayer().getActiveContract().getValueMultiplier()) + " flames";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response ("Leave", "You gained money! You can leave now", EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getNonCompanionCharactersPresent().get(targetSlaveGiven() - 1).setLocation(WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE, true); // Temporary until Wallace's basement with all his slaves are added
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SLAVE_RENTAL_HUNT_INVALID = new DialogueNode ("Servants' Hall", "-", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/servantsHall", "SERVANTS_HALL_HUNT_FAIL");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response ("Leave", "Dammit!", EXTERIOR);
			} else {
				return null;
			}
		}
	};
	
	private static int targetSlaveGiven() {
		int i = 1;
		for (NPC target : Main.game.getNonCompanionCharactersPresent()) {
			if (!target.isUnique() && target.isSlave() && target.getOwner() == Main.game.getPlayer()) {
				return i;
			}
			i++;
		}
		return 1;
	}
}
