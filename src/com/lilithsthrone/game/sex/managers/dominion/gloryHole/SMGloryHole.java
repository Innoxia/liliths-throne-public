package com.lilithsthrone.game.sex.managers.dominion.gloryHole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPositionUnique;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.places.Population;

/**
 * @since 0.2.9
 * @version 0.3.4
 * @author Innoxia
 */
public class SMGloryHole extends SexManagerDefault {

	public SMGloryHole(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPositionUnique.GLORY_HOLE,
				dominants,
				submissives);
	}
	
	@Override
	public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
		return false;
	}

	@Override
	public boolean isPositionChangingAllowed(GameCharacter character) {
		return Sex.isDom(character);
	}

	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
		return false;
	}

	@Override
	public boolean isPlayerAbleToStopSex() {
		return true;
	}
	
	@Override
	public List<InventorySlot> getSlotsConcealed(GameCharacter character) {
		List<InventorySlot> concealedSlots = new ArrayList<>();
		
		if(Sex.getSexPositionSlot(character).equals(SexSlotUnique.GLORY_HOLE_KNEELING)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			concealedSlots.remove(InventorySlot.MOUTH);
			
		} else if(Sex.getSexPositionSlot(character).equals(SexSlotUnique.GLORY_HOLE_FUCKED)
				|| Sex.getSexPositionSlot(character).equals(SexSlotUnique.GLORY_HOLE_FUCKING)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			concealedSlots.remove(InventorySlot.VAGINA);
			concealedSlots.remove(InventorySlot.ANUS);
			concealedSlots.remove(InventorySlot.GROIN);
			concealedSlots.remove(InventorySlot.LEG);
			
		} else if(Sex.getSexPositionSlot(character).equals(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)
				||Sex.getSexPositionSlot(character).equals(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			concealedSlots.remove(InventorySlot.PENIS);
			concealedSlots.remove(InventorySlot.VAGINA);
			concealedSlots.remove(InventorySlot.GROIN);
			concealedSlots.remove(InventorySlot.LEG);
			
		}
		
		return concealedSlots;
	}

	@Override
	public String getPublicSexStartingDescription() {
		return "<p style='color:"+Colour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
					+ "As you let out [pc.a_moan+], several of the people in the toilets turn around to see what's happening."
					+ " Seeing the door to your stall left wide open, and with you about to get started on servicing the cocks before you, a few of them step up and prepare to watch..."
				+ "</p>";
	}

	@Override
	public String getRandomPublicSexDescription() {
		Population pop = Main.game.getPlayer().getLocationPlace().getPlaceType().getPopulation();
		if(pop!=null && !pop.getSpecies().isEmpty()) {
			Subspecies subspecies = Util.randomItemFrom(new ArrayList<>(Main.game.getPlayerCell().getPlace().getPlaceType().getPopulation().getSpecies().keySet()));
			
			return "<p style='color:"+Colour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
						+ UtilText.returnStringAtRandom(
							"The people who've gathered to watch your lewd display laugh and cheer as they look on.",
							"You hear someone in the crowd wolf-whistling as they watch you servicing the glory holes.",
							"A pair of zebra-boy bouncers shove their way through the crowd, but instead of putting a stop to your fun, they join in with the crowd in laughing and commenting on your performance.",
							"You hear the crowd that's gathered to watch you commenting on your performance.",
							"Cheering and laughing, the crowd of onlookers watch as you continue servicing the cocks before you.",
							"You glance across to see several members of the crowd touching themselves as they watch you service the glory holes.",
							"The crowd cheers you on as you carry on servicing the glory holes.",
							"The crowd laughs and cheers as you carry on acting like a desperate slut in front of them.",
							"Several members of the crowd shout and cheer as you carry on servicing the glory holes.",
							Util.capitaliseSentence(UtilText.generateSingularDeterminer(subspecies.getSingularFemaleName(null)))+" "+subspecies.getSingularFemaleName(null)+" steps forwards,"
									+ " stroking her exposed cock as she suddenly cums all over the floor in front of you.",
							Util.capitaliseSentence(UtilText.generateSingularDeterminer(subspecies.getSingularMaleName(null)))+" "+subspecies.getSingularMaleName(null)+" steps forwards,"
									+ " stroking his exposed cock as he suddenly cums all over the floor in front of you.")
					+"</p>";
		}
		
		return "";
	}
}
