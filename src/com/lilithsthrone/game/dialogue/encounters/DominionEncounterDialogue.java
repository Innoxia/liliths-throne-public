package com.lilithsthrone.game.dialogue.encounters;

import java.time.Month;

import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.7.3
 * @author Innoxia
 */
public class DominionEncounterDialogue {

	private static boolean isCanal() {
		return Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.DOMINION_CANAL || Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.DOMINION_CANAL_END;
	}
	
	public static final DialogueNode ALLEY_FIND_ITEM = new DialogueNode("Abandoned package", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(AbstractEncounter.getRandomItem().getDisplayName(true), true);
			if(isCanal()) {
				return UtilText.parseFromXMLFile("encounters/dominion/generic", "CANAL_FIND_PACKAGE");
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/generic", "ALLEY_FIND_PACKAGE");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Take", "Add the " + AbstractEncounter.getRandomItem().getName() + " to your inventory.", Main.game.getDefaultDialogue(false)){
					@Override
					public void effects() {
						if(AbstractEncounter.getRandomItem() instanceof AbstractWeapon) {
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addWeapon((AbstractWeapon) AbstractEncounter.getRandomItem(), true));
							
						} else if(AbstractEncounter.getRandomItem() instanceof AbstractClothing) {
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addClothing((AbstractClothing) AbstractEncounter.getRandomItem(), true));
							
						} else if(AbstractEncounter.getRandomItem() instanceof AbstractItem) {
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addItem((AbstractItem) AbstractEncounter.getRandomItem(), true, true));
						}
					}
				};
				
			} else if (index == 2) {
				return new Response("Leave", "Leave the " + AbstractEncounter.getRandomItem().getName() + " on the floor.", Main.game.getDefaultDialogue(false));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NESTS_FIND_ITEM = new DialogueNode("Dropped item", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(AbstractEncounter.getRandomItem().getDisplayName(true), true);
			UtilText.addSpecialParsingString(AbstractEncounter.getRandomItem().getName(), false);
			return UtilText.parseFromXMLFile("encounters/dominion/generic", "HARPY_NESTS_FIND_ITEM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Take", "Add the " + AbstractEncounter.getRandomItem().getName() + " to your inventory.", Main.game.getDefaultDialogue(false)){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addItem((AbstractItem) AbstractEncounter.getRandomItem(), true, true));
					}
				};
				
			} else if (index == 2) {
				return new Response("Leave", "Leave the " + AbstractEncounter.getRandomItem().getName() + " on the floor.", Main.game.getDefaultDialogue(false));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode DOMINION_STREET_FIND_HAPPINESS = new DialogueNode("Finding Happiness", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/generic", "DOMINION_STREET_FIND_HAPPINESS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Continue on your way.", Main.game.getDefaultDialogue(false));
				
			} else {
				return null;
			}
		}
	};


	public static final DialogueNode DOMINION_STREET_PILL_HANDOUT = new DialogueNode("", "", true) {
		@Override
		public String getLabel() {
			if(Main.game.getDateNow().getMonth()==Month.MAY) {
				return "Mother's Week Gift";
			} else {
				return "Father's Week Gift";
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDateNow().getMonth()==Month.MAY) { // Mother's day:
				String name = Util.randomItemFrom(Subspecies.getWorldSpecies(WorldType.DOMINION, PlaceType.DOMINION_STREET, false).keySet()).getSingularFemaleName(null);
				String litter = Util.randomItemFrom(new String[] {"twins", "triplets", "quadruplets", "quintuplets", "sextuplets"});
				UtilText.addSpecialParsingString(name, true);
				UtilText.addSpecialParsingString(litter, false);

				return UtilText.parseFromXMLFile("encounters/dominion/generic", "DOMINION_STREET_PILL_HANDOUT_MOTHER");
				
			} else { // Father's day:
				String name = Util.randomItemFrom(Subspecies.getWorldSpecies(WorldType.DOMINION, PlaceType.DOMINION_STREET, false).keySet()).getSingularMaleName(null);
				UtilText.addSpecialParsingString(name, true);

				return UtilText.parseFromXMLFile("encounters/dominion/generic", "DOMINION_STREET_PILL_HANDOUT_FATHER");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Continue on your way.", Main.game.getDefaultDialogue(false));
				
			} else {
				return null;
			}
		}
	};
}
