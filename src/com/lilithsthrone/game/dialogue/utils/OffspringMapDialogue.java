package com.lilithsthrone.game.dialogue.utils;

import java.util.List;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;

/**
 * @since 0.3.2
 * @version 0.3.5.5
 * @author Innoxia
 */
public class OffspringMapDialogue {
	
	private static List<NPC> getOffspringList() {
		AbstractWorldType worldType = Main.game.getPlayer().getWorldLocation();
		AbstractPlaceType placeType = Main.game.getPlayer().getLocationPlace().getPlaceType();
		
		return Main.game.getOffspringNotSpawned(npc->
			npc.getSubspecies().isAbleToNaturallySpawnInLocation(worldType, placeType)
				&& (worldType==WorldType.HARPY_NEST
						?(npc.getHalfDemonSubspecies()==null || npc.getHalfDemonSubspecies().getRace()==Race.HARPY)
						:(npc.getHalfDemonSubspecies()==null || npc.getHalfDemonSubspecies().getRace()!=Race.HARPY))
				|| (Main.game.getPlayer().getLocationPlace().getPlaceType().getEncounterType()==Encounter.DOMINION_ALLEY
						&& (npc.getSubspecies()==Subspecies.ANGEL
							|| npc.getSubspecies()==Subspecies.FOX_ASCENDANT
							|| npc.getSubspecies()==Subspecies.FOX_ASCENDANT_ARCTIC
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
						+ "The arcane enchantment in the map causes a list of names of all your unmet offspring to be projected into the air in front of you, with corresponding dots showing their exact location on the physical map itself."
						+ (noOffspring
								? " It doesn't look like you have any unmet offspring to be found right now..."
								: " Luckily, it looks like one of your offspring is in this very area."
									+ " As you take a look at the key, you see just who it is...")
					+ "</p>"
					+ "<p style='text-align:center;'>");
			
				if(Main.game.getOffspringNotSpawned(npc->true).isEmpty()) {
					UtilText.nodeContentSB.append("[style.colourDisabled(No offspring available)]");
					
				} else {
					boolean foundAnyInArea = false;
					List<NPC> npcsToShow = Main.game.getOffspringNotSpawned(npc->getOffspringList().contains(npc));
					if(!npcsToShow.isEmpty()) {
						foundAnyInArea = true;
						UtilText.nodeContentSB.append("Offspring [style.colourMinorGood(possibly in this area)]:<br/>");
					}
					for(NPC npc : npcsToShow) {
						UtilText.nodeContentSB.append("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+(npc.isPlayer()?"You":Util.capitaliseSentence(npc.getName(true)))+"</span>");
						
						String unknownMotherName = "Unknown";
						if(npc.getMother()==null && !npc.getMotherName().equals("???")) {
							unknownMotherName = npc.getMotherName();
						}
						String unknownFatherName = "Unknown";
						if(npc.getFather()==null && !npc.getFatherName().equals("???")) {
							unknownFatherName = npc.getFatherName();
						}
						
						UtilText.nodeContentSB.append(" (<span style='color:"+npc.getSubspecies().getColour(npc).toWebHexString()+";'>"+Util.capitaliseSentence(npc.getSubspecies().getName(npc))+"</span>)"
								+ " Mother: "+(npc.getMother()==null?unknownMotherName:npc.getMother().getName(true))
								+ " Father: "+(npc.getFather()==null?unknownFatherName:npc.getFather().getName(true))
								+ "<br/>");
					}
					
					npcsToShow = Main.game.getOffspringNotSpawned(npc->!getOffspringList().contains(npc));
					if(!npcsToShow.isEmpty()) {
						if(foundAnyInArea) {
							UtilText.nodeContentSB.append("<br/>");
						}
						UtilText.nodeContentSB.append("Offspring [style.colourMinorBad(not in this area)]:<br/>");
					}
					for(NPC npc : npcsToShow) {
						UtilText.nodeContentSB.append("[style.colourDisabled("+(npc.isPlayer()?"You":Util.capitaliseSentence(npc.getName(true)))+")]");

						String unknownMotherName = "Unknown";
						if(npc.getMother()==null && !npc.getMotherName().equals("???")) {
							unknownMotherName = npc.getMotherName();
						}
						String unknownFatherName = "Unknown";
						if(npc.getFather()==null && !npc.getFatherName().equals("???")) {
							unknownFatherName = npc.getFatherName();
						}
						
						UtilText.nodeContentSB.append(" (<span style='color:"+npc.getSubspecies().getColour(npc).toWebHexString()+";'>"+Util.capitaliseSentence(npc.getSubspecies().getName(npc))+"</span>)"
								+ " Mother: "+(npc.getMother()==null?unknownMotherName:npc.getMother().getName(true))
								+ " Father: "+(npc.getFather()==null?unknownFatherName:npc.getFather().getName(true))
								+ "<br/>");
					}
				}
				
			UtilText.nodeContentSB.append("</p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			List<NPC> npcsToShow = Main.game.getOffspringNotSpawned(npc->getOffspringList().contains(npc));
			npcsToShow.addAll(Main.game.getOffspringNotSpawned(npc->!getOffspringList().contains(npc)));
			
			if (index == 0) {
				return new Response("Back", "Decide not to look for any of your offspring in this location after all.", Main.game.getDefaultDialogue(false));
				
			} else if(index-1 < npcsToShow.size()) {
				NPC offspring = npcsToShow.get(index-1);
				
				if(!getOffspringList().contains(offspring)) {
					return new Response(offspring.getName(true),
							UtilText.parse(offspring,"[npc.Name] cannot be found in this area, due to [npc.her] subspecies..."),
							null);
				}
				
				return new Response(offspring.getName(true),
						UtilText.parse(offspring, "With the help of the enchanted map, you know that you'll soon be able to find [npc.name] in this area..."),
						offspring.getEncounterDialogue()) {
					@Override
					public Colour getHighlightColour() {
						return offspring.getFemininity().getColour();
					}
					@Override
					public void effects() {
						Main.game.getOffspringSpawned().add(offspring);

						offspring.setLocation(Main.game.getPlayer(), true);
						
						offspring.equipClothing(EquipClothingSetting.getAllClothingSettings());
						
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
