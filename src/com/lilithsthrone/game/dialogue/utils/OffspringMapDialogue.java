package com.lilithsthrone.game.dialogue.utils;


import java.util.List;

import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.3.2
 * @version 0.3.2
 * @author Innoxia
 */
public class OffspringMapDialogue {
	
	private static List<NPC> getOffspringList() {
		WorldType worldType = Main.game.getPlayer().getWorldLocation();
		
		return Main.game.getOffspringNotSpawned(npc->
			npc.getSubspecies().getWorldLocations().keySet().contains(worldType)
			|| (Main.game.getPlayer().getLocationPlace().getPlaceType().getEncounterType()==Encounter.DOMINION_ALLEY
					&& (npc.getSubspecies()==Subspecies.ANGEL
						|| npc.getSubspecies()==Subspecies.FOX_ASCENDANT
						|| npc.getSubspecies()==Subspecies.FOX_ASCENDANT_FENNEC))
			|| (Main.game.getPlayer().getLocationPlace().getPlaceType().getEncounterType()==Encounter.DOMINION_CANAL
					&& (npc.getSubspecies()==Subspecies.ALLIGATOR_MORPH
						|| npc.getSubspecies()==Subspecies.SLIME
						|| npc.getSubspecies()==Subspecies.RAT_MORPH)));
	}
	
	
	public static final DialogueNode OFFSPRING_CHOICE = new DialogueNode("Offspring list", "-", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			boolean noOffspring = getOffspringList().isEmpty();
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "The arcane enchantment in the map causes a list of names of all your unmet offspring to be displayed, while corresponding dots show their exact location within Dominion."
						+ (noOffspring
								? " It doesn't look like you have any unmet offspring to be found right now..."
								: " Luckily, it looks like one of your offspring is in this very area."
									+ " As you take a look at the key, you see just who it is...")
					+ "</p>"
					+ "<p style='text-align:center;'>");
			
				if(Main.game.getOffspringNotSpawned(npc->true).isEmpty()) {
					UtilText.nodeContentSB.append("[style.colourDisabled(No offspring available)]");
					
				} else {
					for(NPC npc : Main.game.getOffspringNotSpawned(npc->true)) {
						if(!getOffspringList().contains(npc)) {
							UtilText.nodeContentSB.append("[style.colourDisabled("+(npc.isPlayer()?"You":Util.capitaliseSentence(npc.getName(true)))+")]"
									+ " ("+Util.capitaliseSentence(npc.getSubspecies().getName(npc))+")"
									+ " Mother: "+npc.getMother().getName(true)
									+ " Father: "+npc.getFather().getName(true)
									+ "<br/>");
							
						} else {
							UtilText.nodeContentSB.append("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+(npc.isPlayer()?"You":Util.capitaliseSentence(npc.getName(true)))+"</span>"
									+ " ("+Util.capitaliseSentence(npc.getSubspecies().getName(npc))+")"
									+ " Mother: "+npc.getMother().getName(true)
									+ " Father: "+npc.getFather().getName(true)
									+ "<br/>");
						}
					}
				}
				
			UtilText.nodeContentSB.append("</p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Decide not to look for any of your offspring in this location after all.", Main.game.getDefaultDialogueNoEncounter());
				
			} else if(index-1 < Main.game.getOffspringNotSpawned(npc->true).size()) {
				NPC offspring = Main.game.getOffspringNotSpawned(npc->true).get(index-1);
				
				if(!getOffspringList().contains(offspring)) {
					return new Response(offspring.getName(true),
							UtilText.parse(offspring,"[npc.Name] cannot be found in Dominion, due to [npc.her] subspecies..."),
							null);
				}
				
				return new Response(offspring.getName(true),
						"Search for this offspring in this area.",
						offspring.getEncounterDialogue()) {
					@Override
					public void effects() {
						Main.game.getOffspringSpawned().add(offspring);

						offspring.setLocation(Main.game.getPlayer(), true);

						Main.game.setActiveNPC(offspring);
						
						Main.game.getTextStartStringBuilder().append(
								"<p><i>"
									+ UtilText.parse(offspring,
										"Consulting the map, you soon find that your [npc.daughter], [npc.name], is living in this particular area of the alleyways."
										+ " Putting away your map, you start to search for [npc.herHim]...")
								+ "</i></p>");
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
