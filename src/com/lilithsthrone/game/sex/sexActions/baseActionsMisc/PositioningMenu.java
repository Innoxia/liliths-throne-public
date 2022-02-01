package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMilkingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStocks;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.SexActionUtility;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * Contains actions related to the positioning menu.
 * 
 * @since 0.3.4
 * @version 0.3.4
 * @author Innoxia
 */
public class PositioningMenu {
	
	private static AbstractSexPosition position;
	public static Map<GameCharacter, SexSlot> positioningSlots;
	private static GameCharacter targetedCharacter;
	private static List<SexSlot> availableSlots;
	
	private static StringBuilder positioningSB = new StringBuilder();
	
	public static void setNewSexManager() {
		Map<GameCharacter, SexSlot> dominants = new HashMap<>();
		Map<GameCharacter, SexSlot> submissives = new HashMap<>();
		List<GameCharacter> dominantSpectators = new ArrayList<>();
		List<GameCharacter> submissiveSpectators = new ArrayList<>();
		
		for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
//			System.out.println(e.getKey().getName()+", "+e.getValue().getName(e.getKey()));
			if(Main.sex.isDom(e.getKey()) || Main.sex.getDominantSpectators().contains(e.getKey())) {
				if(e.getValue()==SexSlotGeneric.MISC_WATCHING) {
					dominantSpectators.add(e.getKey());
				} else {
					dominants.put(e.getKey(), e.getValue());
				}
			} else {
				if(e.getValue()==SexSlotGeneric.MISC_WATCHING) {
					submissiveSpectators.add(e.getKey());
				} else {
					submissives.put(e.getKey(), e.getValue());
				}
			}
		}
		
		Main.sex.setSexManager(
				new SexManagerDefault(
						position,
						dominants,
						submissives){
				},
				dominantSpectators,
				submissiveSpectators);
		
		Main.sex.setPositionRequest(null);
	}
	
	@SuppressWarnings("fallthrough")
	private static List<SexSlot> getAvailableSlots(GameCharacter character) {
		List<SexSlot> slotsOne = new ArrayList<>();
		List<SexSlot> slotsTwo = new ArrayList<>();
		List<SexSlot> slotsThree = new ArrayList<>();
		List<SexSlot> slotsFour = new ArrayList<>();
		List<SexSlot> slotsFive = new ArrayList<>();
		List<SexSlot> slotsSix = new ArrayList<>();
		List<SexSlot> slotsSeven = new ArrayList<>();
		List<SexSlot> slotsEight = new ArrayList<>();
		List<SexSlot> slotsNine = new ArrayList<>();
		List<SexSlot> slotsTen = new ArrayList<>();
		List<SexSlot> slotsEleven = new ArrayList<>();
		List<SexSlot> slotsTwelve = new ArrayList<>();
		
		Map<GameCharacter, SexSlot> doms = Main.sex.getDominantParticipants(true);
		Map<GameCharacter, SexSlot> subs = Main.sex.getSubmissiveParticipants(true);
		
		if(position==SexPosition.STANDING) {
			int sizeSlots = (doms.size()+subs.size())-1;
			switch(sizeSlots) {
				case 4: case 5: case 6: case 7: case 8:
					slotsOne.add(SexSlotStanding.STANDING_DOMINANT_FOUR);
					slotsTwo.add(SexSlotStanding.STANDING_SUBMISSIVE_FOUR);
					slotsThree.add(SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_FOUR);
					slotsFour.add(SexSlotStanding.PERFORMING_ORAL_FOUR);
					slotsFive.add(SexSlotStanding.PERFORMING_ORAL_BEHIND_FOUR);
				case 3: 
					slotsOne.add(SexSlotStanding.STANDING_DOMINANT_THREE);
					slotsTwo.add(SexSlotStanding.STANDING_SUBMISSIVE_THREE);
					slotsThree.add(SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_THREE);
					slotsFour.add(SexSlotStanding.PERFORMING_ORAL_THREE);
					slotsFive.add(SexSlotStanding.PERFORMING_ORAL_BEHIND_THREE);
				case 2: 
					slotsOne.add(SexSlotStanding.STANDING_DOMINANT_TWO);
					slotsTwo.add(SexSlotStanding.STANDING_SUBMISSIVE_TWO);
					slotsThree.add(SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_TWO);
					slotsFour.add(SexSlotStanding.PERFORMING_ORAL_TWO);
					slotsFive.add(SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO);
				default:
					slotsOne.add(SexSlotStanding.STANDING_DOMINANT);
					slotsTwo.add(SexSlotStanding.STANDING_SUBMISSIVE);
					slotsThree.add(SexSlotStanding.STANDING_SUBMISSIVE_BEHIND);
					slotsFour.add(SexSlotStanding.PERFORMING_ORAL);
					slotsFive.add(SexSlotStanding.PERFORMING_ORAL_BEHIND);
			}
		}
		
		if(position==SexPosition.AGAINST_WALL) {
			int sizeSlots = (doms.size()+subs.size())-1;
			switch(sizeSlots) {
				case 4: case 5: case 6: case 7: case 8:
					slotsOne.add(SexSlotAgainstWall.STANDING_WALL_FOUR);
					slotsTwo.add(SexSlotAgainstWall.FACE_TO_WALL_FOUR);
					slotsThree.add(SexSlotAgainstWall.BACK_TO_WALL_FOUR);
					slotsFour.add(SexSlotAgainstWall.PERFORMING_ORAL_WALL_FOUR);
				case 3: 
					slotsOne.add(SexSlotAgainstWall.STANDING_WALL_THREE);
					slotsTwo.add(SexSlotAgainstWall.FACE_TO_WALL_THREE);
					slotsThree.add(SexSlotAgainstWall.BACK_TO_WALL_THREE);
					slotsFour.add(SexSlotAgainstWall.PERFORMING_ORAL_WALL_THREE);
				case 2: 
					slotsOne.add(SexSlotAgainstWall.STANDING_WALL_TWO);
					slotsTwo.add(SexSlotAgainstWall.FACE_TO_WALL_TWO);
					slotsThree.add(SexSlotAgainstWall.BACK_TO_WALL_TWO);
					slotsFour.add(SexSlotAgainstWall.PERFORMING_ORAL_WALL_TWO);
				default:
					slotsOne.add(SexSlotAgainstWall.STANDING_WALL);
					slotsTwo.add(SexSlotAgainstWall.FACE_TO_WALL);
					slotsThree.add(SexSlotAgainstWall.BACK_TO_WALL);
					slotsFour.add(SexSlotAgainstWall.PERFORMING_ORAL_WALL);
			}
		}
		
		if(position==SexPosition.OVER_DESK) {
			int sizeSlots = (doms.size()+subs.size())-1;
			switch(sizeSlots) {
				case 4: case 5: case 6: case 7: case 8:
					slotsOne.add(SexSlotDesk.BETWEEN_LEGS_FOUR);
					slotsTwo.add(SexSlotDesk.HUMPING_FOUR);
					slotsTwo.add(SexSlotDesk.PERFORMING_ORAL_FOUR);
					slotsThree.add(SexSlotDesk.RECEIVING_ORAL_FOUR);
					slotsFour.add(SexSlotDesk.OVER_DESK_ON_BACK_FOUR);
					slotsFive.add(SexSlotDesk.OVER_DESK_ON_FRONT_FOUR);
				case 3: 
					slotsOne.add(SexSlotDesk.BETWEEN_LEGS_THREE);
					slotsTwo.add(SexSlotDesk.HUMPING_THREE);
					slotsTwo.add(SexSlotDesk.PERFORMING_ORAL_THREE);
					slotsThree.add(SexSlotDesk.RECEIVING_ORAL_THREE);
					slotsFour.add(SexSlotDesk.OVER_DESK_ON_BACK_THREE);
					slotsFive.add(SexSlotDesk.OVER_DESK_ON_FRONT_THREE);
				case 2: 
					slotsOne.add(SexSlotDesk.BETWEEN_LEGS_TWO);
					slotsTwo.add(SexSlotDesk.HUMPING_TWO);
					slotsTwo.add(SexSlotDesk.PERFORMING_ORAL_TWO);
					slotsThree.add(SexSlotDesk.RECEIVING_ORAL_TWO);
					slotsFour.add(SexSlotDesk.OVER_DESK_ON_BACK_TWO);
					slotsFive.add(SexSlotDesk.OVER_DESK_ON_FRONT_TWO);
				default:
					slotsOne.add(SexSlotDesk.BETWEEN_LEGS);
					slotsTwo.add(SexSlotDesk.HUMPING);
					slotsTwo.add(SexSlotDesk.PERFORMING_ORAL);
					slotsThree.add(SexSlotDesk.RECEIVING_ORAL);
					slotsFour.add(SexSlotDesk.OVER_DESK_ON_BACK);
					slotsFive.add(SexSlotDesk.OVER_DESK_ON_FRONT);
			}
		}
		
		if(position==SexPosition.STOCKS) {
			int sizeSlots = (doms.size()+subs.size())-1;
			switch(sizeSlots) {
				case 4: case 5: case 6: case 7: case 8:
					slotsOne.add(SexSlotStocks.BEHIND_STOCKS_FOUR);
					slotsOne.add(SexSlotStocks.BENEATH_STOCKS_FOUR);
					slotsTwo.add(SexSlotStocks.RECEIVING_ORAL_FOUR);
					slotsThree.add(SexSlotStocks.PERFORMING_ORAL_FOUR);
					slotsFour.add(SexSlotStocks.LOCKED_IN_STOCKS_FOUR);
				case 3: 
					slotsOne.add(SexSlotStocks.BEHIND_STOCKS_THREE);
					slotsOne.add(SexSlotStocks.BENEATH_STOCKS_THREE);
					slotsTwo.add(SexSlotStocks.RECEIVING_ORAL_THREE);
					slotsThree.add(SexSlotStocks.PERFORMING_ORAL_THREE);
					slotsFour.add(SexSlotStocks.LOCKED_IN_STOCKS_THREE);
				case 2: 
					slotsOne.add(SexSlotStocks.BEHIND_STOCKS_TWO);
					slotsOne.add(SexSlotStocks.BENEATH_STOCKS_TWO);
					slotsTwo.add(SexSlotStocks.RECEIVING_ORAL_TWO);
					slotsThree.add(SexSlotStocks.PERFORMING_ORAL_TWO);
					slotsFour.add(SexSlotStocks.LOCKED_IN_STOCKS_TWO);
				default:
					slotsOne.add(SexSlotStocks.BEHIND_STOCKS);
					slotsOne.add(SexSlotStocks.BENEATH_STOCKS);
					slotsTwo.add(SexSlotStocks.RECEIVING_ORAL);
					slotsThree.add(SexSlotStocks.PERFORMING_ORAL);
					slotsFour.add(SexSlotStocks.LOCKED_IN_STOCKS);
			}
		}
		
		if(position==SexPosition.MILKING_STALL) {
			int sizeSlots = (doms.size()+subs.size())-1;
			switch(sizeSlots) {
				case 4: case 5: case 6: case 7: case 8:
					slotsOne.add(SexSlotMilkingStall.BEHIND_MILKING_STALL_FOUR);
					slotsOne.add(SexSlotMilkingStall.BENEATH_MILKING_STALL_FOUR);
					slotsTwo.add(SexSlotMilkingStall.RECEIVING_ORAL_FOUR);
					slotsThree.add(SexSlotMilkingStall.PERFORMING_ORAL_FOUR);
					slotsFour.add(SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_FOUR);
				case 3: 
					slotsOne.add(SexSlotMilkingStall.BEHIND_MILKING_STALL_THREE);
					slotsOne.add(SexSlotMilkingStall.BENEATH_MILKING_STALL_THREE);
					slotsTwo.add(SexSlotMilkingStall.RECEIVING_ORAL_THREE);
					slotsThree.add(SexSlotMilkingStall.PERFORMING_ORAL_THREE);
					slotsFour.add(SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_THREE);
				case 2: 
					slotsOne.add(SexSlotMilkingStall.BEHIND_MILKING_STALL_TWO);
					slotsOne.add(SexSlotMilkingStall.BENEATH_MILKING_STALL_TWO);
					slotsTwo.add(SexSlotMilkingStall.RECEIVING_ORAL_TWO);
					slotsThree.add(SexSlotMilkingStall.PERFORMING_ORAL_TWO);
					slotsFour.add(SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_TWO);
				default:
					slotsOne.add(SexSlotMilkingStall.BEHIND_MILKING_STALL);
					slotsOne.add(SexSlotMilkingStall.BENEATH_MILKING_STALL);
					slotsTwo.add(SexSlotMilkingStall.RECEIVING_ORAL);
					slotsThree.add(SexSlotMilkingStall.PERFORMING_ORAL);
					slotsFour.add(SexSlotMilkingStall.LOCKED_IN_MILKING_STALL);
			}
		}
		
		if(position==SexPosition.SITTING) {
			int sizeSlots = (doms.size()+subs.size())-1;
			switch(sizeSlots) {
				case 4: case 5: case 6: case 7: case 8:
					slotsOne.add(SexSlotSitting.SITTING_FOUR);
					slotsTwo.add(SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR);
					slotsThree.add(SexSlotSitting.SITTING_IN_LAP_FOUR);
					slotsFour.add(SexSlotSitting.PERFORMING_ORAL_FOUR);
					slotsFive.add(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_FOUR);
				case 3: 
					slotsOne.add(SexSlotSitting.SITTING_THREE);
					slotsTwo.add(SexSlotSitting.SITTING_BETWEEN_LEGS_THREE);
					slotsThree.add(SexSlotSitting.SITTING_IN_LAP_THREE);
					slotsFour.add(SexSlotSitting.PERFORMING_ORAL_THREE);
					slotsFive.add(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_THREE);
				case 2: 
					slotsOne.add(SexSlotSitting.SITTING_TWO);
					slotsTwo.add(SexSlotSitting.SITTING_BETWEEN_LEGS_TWO);
					slotsThree.add(SexSlotSitting.SITTING_IN_LAP_TWO);
					slotsFour.add(SexSlotSitting.PERFORMING_ORAL_TWO);
					slotsFive.add(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_TWO);
				default:
					slotsOne.add(SexSlotSitting.SITTING);
					slotsTwo.add(SexSlotSitting.SITTING_BETWEEN_LEGS);
					slotsThree.add(SexSlotSitting.SITTING_IN_LAP);
					slotsFour.add(SexSlotSitting.PERFORMING_ORAL);
					slotsFive.add(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL);
			}
		}
		
		if(position==SexPosition.ALL_FOURS) {
			int sizeSlots = (doms.size()+subs.size())-1;
			switch(sizeSlots) {
				case 4: case 5: case 6: case 7: case 8:
					slotsOne.add(SexSlotAllFours.ALL_FOURS_FOUR);
					slotsTwo.add(SexSlotAllFours.BEHIND_FOUR);
					slotsThree.add(SexSlotAllFours.BEHIND_ORAL_FOUR);
					slotsFour.add(SexSlotAllFours.HUMPING_FOUR);
					slotsFive.add(SexSlotAllFours.USING_FEET_FOUR);
					slotsSix.add(SexSlotAllFours.IN_FRONT_FOUR);
					slotsSeven.add(SexSlotAllFours.IN_FRONT_ANAL_FOUR);
				case 3: 
					slotsOne.add(SexSlotAllFours.ALL_FOURS_THREE);
					slotsTwo.add(SexSlotAllFours.BEHIND_THREE);
					slotsThree.add(SexSlotAllFours.BEHIND_ORAL_THREE);
					slotsFour.add(SexSlotAllFours.HUMPING_THREE);
					slotsFive.add(SexSlotAllFours.USING_FEET_THREE);
					slotsSix.add(SexSlotAllFours.IN_FRONT_THREE);
					slotsSeven.add(SexSlotAllFours.IN_FRONT_ANAL_THREE);
				case 2:
					slotsOne.add(SexSlotAllFours.ALL_FOURS_TWO);
					slotsTwo.add(SexSlotAllFours.BEHIND_TWO);
					slotsThree.add(SexSlotAllFours.BEHIND_ORAL_TWO);
					slotsFour.add(SexSlotAllFours.HUMPING_TWO);
					slotsFive.add(SexSlotAllFours.USING_FEET_TWO);
					slotsSix.add(SexSlotAllFours.IN_FRONT_TWO);
					slotsSeven.add(SexSlotAllFours.IN_FRONT_ANAL_TWO);
				default:
					slotsOne.add(SexSlotAllFours.ALL_FOURS);
					slotsTwo.add(SexSlotAllFours.BEHIND);
					slotsThree.add(SexSlotAllFours.BEHIND_ORAL);
					slotsFour.add(SexSlotAllFours.HUMPING);
					slotsFive.add(SexSlotAllFours.USING_FEET);
					slotsSix.add(SexSlotAllFours.IN_FRONT);
					slotsSeven.add(SexSlotAllFours.IN_FRONT_ANAL);
			}
		}
		
		if(position==SexPosition.LYING_DOWN) {
			int sizeSlots = (doms.size()+subs.size())-1;
			switch(sizeSlots) {
				case 4: case 5: case 6: case 7: case 8:
					slotsOne.add(SexSlotLyingDown.LYING_DOWN_FOUR);
					slotsTwo.add(SexSlotLyingDown.COWGIRL_FOUR);
					slotsThree.add(SexSlotLyingDown.COWGIRL_REVERSE_FOUR);
					slotsFour.add(SexSlotLyingDown.FACE_SITTING_FOUR);
					slotsFive.add(SexSlotLyingDown.FACE_SITTING_REVERSE_FOUR);
					slotsSix.add(SexSlotLyingDown.LAP_PILLOW_FOUR);
					slotsSeven.add(SexSlotLyingDown.MATING_PRESS_FOUR);
					slotsEight.add(SexSlotLyingDown.MISSIONARY_FOUR);
					slotsNine.add(SexSlotLyingDown.SCISSORING_FOUR);
					slotsTen.add(SexSlotLyingDown.SIXTY_NINE_FOUR);
					slotsEleven.add(SexSlotLyingDown.BESIDE_FOUR);
					slotsTwelve.add(SexSlotLyingDown.MISSIONARY_ORAL_FOUR);
				case 3: 
					slotsOne.add(SexSlotLyingDown.LYING_DOWN_THREE);
					slotsTwo.add(SexSlotLyingDown.COWGIRL_THREE);
					slotsThree.add(SexSlotLyingDown.COWGIRL_REVERSE_THREE);
					slotsFour.add(SexSlotLyingDown.FACE_SITTING_THREE);
					slotsFive.add(SexSlotLyingDown.FACE_SITTING_REVERSE_THREE);
					slotsSix.add(SexSlotLyingDown.LAP_PILLOW_THREE);
					slotsSeven.add(SexSlotLyingDown.MATING_PRESS_THREE);
					slotsEight.add(SexSlotLyingDown.MISSIONARY_THREE);
					slotsNine.add(SexSlotLyingDown.SCISSORING_THREE);
					slotsTen.add(SexSlotLyingDown.SIXTY_NINE_THREE);
					slotsEleven.add(SexSlotLyingDown.BESIDE_THREE);
					slotsTwelve.add(SexSlotLyingDown.MISSIONARY_ORAL_THREE);
				case 2:
					slotsOne.add(SexSlotLyingDown.LYING_DOWN_TWO);
					slotsTwo.add(SexSlotLyingDown.COWGIRL_TWO);
					slotsThree.add(SexSlotLyingDown.COWGIRL_REVERSE_TWO);
					slotsFour.add(SexSlotLyingDown.FACE_SITTING_TWO);
					slotsFive.add(SexSlotLyingDown.FACE_SITTING_REVERSE_TWO);
					slotsSix.add(SexSlotLyingDown.LAP_PILLOW_TWO);
					slotsSeven.add(SexSlotLyingDown.MATING_PRESS_TWO);
					slotsEight.add(SexSlotLyingDown.MISSIONARY_TWO);
					slotsNine.add(SexSlotLyingDown.SCISSORING_TWO);
					slotsTen.add(SexSlotLyingDown.SIXTY_NINE_TWO);
					slotsEleven.add(SexSlotLyingDown.BESIDE_TWO);
					slotsTwelve.add(SexSlotLyingDown.MISSIONARY_ORAL_TWO);
				default:
					slotsOne.add(SexSlotLyingDown.LYING_DOWN);
					slotsTwo.add(SexSlotLyingDown.COWGIRL);
					slotsThree.add(SexSlotLyingDown.COWGIRL_REVERSE);
					slotsFour.add(SexSlotLyingDown.FACE_SITTING);
					slotsFive.add(SexSlotLyingDown.FACE_SITTING_REVERSE);
					slotsSix.add(SexSlotLyingDown.LAP_PILLOW);
					slotsSeven.add(SexSlotLyingDown.MATING_PRESS);
					slotsEight.add(SexSlotLyingDown.MISSIONARY);
					slotsNine.add(SexSlotLyingDown.SCISSORING);
					slotsTen.add(SexSlotLyingDown.SIXTY_NINE);
					slotsEleven.add(SexSlotLyingDown.BESIDE);
					slotsTwelve.add(SexSlotLyingDown.MISSIONARY_ORAL);
			}
		}

		Collections.reverse(slotsOne);
		Collections.reverse(slotsTwo);
		Collections.reverse(slotsThree);
		Collections.reverse(slotsFour);
		Collections.reverse(slotsFive);
		Collections.reverse(slotsSix);
		Collections.reverse(slotsSeven);
		Collections.reverse(slotsEight);
		Collections.reverse(slotsNine);
		Collections.reverse(slotsTen);
		Collections.reverse(slotsEleven);
		Collections.reverse(slotsTwelve);
		
		List<SexSlot> slots = new ArrayList<>(slotsOne);
		slots.addAll(slotsTwo);
		slots.addAll(slotsThree);
		slots.addAll(slotsFour);
		slots.addAll(slotsFive);
		slots.addAll(slotsSix);
		slots.addAll(slotsSeven);
		slots.addAll(slotsEight);
		slots.addAll(slotsNine);
		slots.addAll(slotsTen);
		slots.addAll(slotsEleven);
		slots.addAll(slotsTwelve);

		slots.add(SexSlotGeneric.MISC_WATCHING);
		
		slots.removeIf(s -> !Main.sex.getInitialSexManager().isSlotAvailable(character, s));
		
		return slots;
	}
	
	private static Value<Boolean, String> isSlotsAcceptable() {
		if(!Main.sex.isMasturbation()) {
			boolean domsAcceptable = false;
			for(GameCharacter c : Main.sex.getDominantParticipants(true).keySet()) {
				if(positioningSlots.get(c)!=SexSlotGeneric.MISC_WATCHING) {
					domsAcceptable = true;
					break;
				}
			}
			boolean subsAcceptable = false;
			for(GameCharacter c : Main.sex.getSubmissiveParticipants(true).keySet()) {
				if(positioningSlots.get(c)!=SexSlotGeneric.MISC_WATCHING) {
					subsAcceptable = true;
					break;
				}
			}
			if(!(domsAcceptable && subsAcceptable)) {
				return new Value<Boolean, String>(false, "You need to have at least one dominant and one submissive character in non-spectator slots.");
			}
		}

		return position.isAcceptablePosition(positioningSlots);
	}
	
	private static String getDominantsDiv() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-half-width'>");
			sb.append("<p style='text-align:center;'>");
			if(Main.sex.getDominantParticipants(true).size()>0) {
				sb.append("[style.boldDominant(Dominants)]</b>");
					for(GameCharacter c : Main.sex.getDominantParticipants(true).keySet()) {
						sb.append(UtilText.parse(c,
								"<br/><b style='color:"+c.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b><b>:</b> "
										+(positioningSlots.get(c)==SexSlotGeneric.MISC_WATCHING
											?"[style.colourDisabled("+Util.capitaliseSentence(positioningSlots.get(c).getDescription())+")]"
											:Util.capitaliseSentence(positioningSlots.get(c).getDescription()))));
					}
			}
			sb.append("</p>");
		sb.append("</div>");
		
		return sb.toString();
	}
	
	private static String getSubmissivesDiv() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-half-width'>");
			sb.append("<p style='text-align:center;'>");
			if(Main.sex.getSubmissiveParticipants(true).size()>0) {
				sb.append("[style.boldSubmissive(Submissives)]</b>");
					for(GameCharacter c : Main.sex.getSubmissiveParticipants(true).keySet()) {
						sb.append(UtilText.parse(c,
								"<br/><b style='color:"+c.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b><b>:</b> "
										+(positioningSlots.get(c)==SexSlotGeneric.MISC_WATCHING
											?"[style.colourDisabled("+Util.capitaliseSentence(positioningSlots.get(c).getDescription())+")]"
											:Util.capitaliseSentence(positioningSlots.get(c).getDescription()))));
					}
			}
			sb.append("</p>");
		sb.append("</div>");
		
		return sb.toString();
	}
	
	private static String performAcceptableSlotsCheck() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p style='text-align:center;'>");
		List<GameCharacter> charactersToMoveToSpectators = new ArrayList<>();
		for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
			if(!e.getKey().equals(targetedCharacter) && !position.isSlotUnlocked(e.getKey(), e.getValue(), positioningSlots).getKey()) {
				charactersToMoveToSpectators.add(e.getKey());
				sb.append(
						"[style.colourBad("+UtilText.parse(e.getKey(),
								"[npc.NamePos] selected position slot, '"+Util.capitaliseSentence(e.getValue().getDescription())+"', is now unavailable, and as such, [npc.sheHasFull] been moved to a spectator slot!")
						+")]");
			}
		}
		for(GameCharacter c : charactersToMoveToSpectators) {
			positioningSlots.put(c, SexSlotGeneric.MISC_WATCHING);
		}
		sb.append("</p>");
		
		return sb.toString();
	}
	
	public static final DialogueNode POSITIONING_MENU = new DialogueNode("Positioning Selection", "", true) {
		@Override
		public String getLabel() {
			return "Positioning Selection: "+position.getName();
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.isDom(Main.game.getPlayer())) {
				UtilText.nodeContentSB.append(getDominantsDiv());
				UtilText.nodeContentSB.append(getSubmissivesDiv());
				
			} else {
				UtilText.nodeContentSB.append(getSubmissivesDiv());
				UtilText.nodeContentSB.append(getDominantsDiv());
			}
			
			Value<Boolean, String> acceptablePosition = position.isAcceptablePosition(positioningSlots);
			if(!acceptablePosition.getKey()) {
				UtilText.nodeContentSB.append(
						"<p style='text-align:center;'>"
								+ "[style.boldBad(Invalid position)]<b>:</b><br/>"
								+"[style.italicsMinorBad("+acceptablePosition.getValue()+")]"
						+ "</p>");
				
			} else {
				Value<Boolean, String> acceptableValue = isSlotsAcceptable();
				if(!acceptableValue.getKey()) {
					UtilText.nodeContentSB.append(
							"<p style='text-align:center;'>"
									+ "[style.boldBad(Invalid position)]<b>:</b><br/>"
									+"[style.italicsMinorBad("+acceptableValue.getValue()+")]"
							+ "</p>");
				} else {
					UtilText.nodeContentSB.append(
							"<p style='text-align:center;'>"
									+ "<b>Projected outcome:</b><br/>"
									+"<i>"+position.getDescription(positioningSlots)+"</i>"
							+ "</p>");
				}
			}
			
			// TODO help text
			
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 0) {
				return new ResponseEffectsOnly("Back", "Decide against changing position."){
					@Override
					public void effects() {
						Main.game.restoreSavedContent(false);
					}
				};
				
			} else if(index==1) {
				Value<Boolean, String> acceptableValue = isSlotsAcceptable();
				if(!acceptableValue.getKey()) {
					return new Response("Accept", acceptableValue.getValue(), null);
				}
				return new ResponseEffectsOnly("Accept", "Move into the selected position."){
					@Override
					public boolean isSexPositioningHighlight() {
						return true;
					}
					@Override
					public void effects(){
						positioningSB.setLength(0);
						
						Main.mainController.openPhone();
						
						positioningSB.append(
								"Wanting to switch into a new position, "
									+(Main.sex.isMasturbation()
										?"you move around until you've assumed the posture you had in mind..."
										:(Main.sex.getAllParticipants(false).size()==2
											?UtilText.parse(Main.sex.getTargetedPartner(Main.game.getPlayer()), "you manoeuvre [npc.name] into [npc.her] new place")
											:"you manoeuvre your partners into their new places")
										+", before assuming the posture you had in mind..."));

						SexActionUtility.POSITION_SELECTION.applyEffects();
						Main.sex.endSexTurn(SexActionUtility.POSITION_SELECTION);
						Main.game.setContent(new Response("", "", Main.sex.SEX_DIALOGUE));
//						Main.sex.endSexTurn(PlayerTalk.DIRTY_TALK);
//						Main.game.setContent(SexActionUtility.POSITION_SELECTION.toResponse());
//						
					}
				};
				
			} else if(index==2) {
				return new Response(
						"Target: <b style='color:"+targetedCharacter.getFemininity().getColour().toWebHexString()+";'>"+UtilText.parse(targetedCharacter, "[npc.Name]")+"</b>",
						"This is the character you're currently choosing a slot for.",
						POSITIONING_MENU){
					@Override
					public void effects() {
						List<GameCharacter> characters = Main.sex.getAllParticipants(true);
						
						for(int i=0; i<characters.size(); i++) {
							if(characters.get(i).equals(targetedCharacter)) {
								if(i==characters.size()-1) {
									targetedCharacter = characters.get(0);
									break;
								} else {
									targetedCharacter = characters.get(i+1);
									break;
								}
							}
						}
						
						availableSlots = getAvailableSlots(targetedCharacter);
					}
				};
				
			} else if(index-3<availableSlots.size()) {
				SexSlot slot = availableSlots.get(index-3);
				
				GameCharacter characterInSlot = null;
				for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
					if(e.getValue().equals(slot)) {
						characterInSlot = e.getKey();
						break;
					}
				}
				
				if(targetedCharacter.equals(characterInSlot)) {
					return new Response(Util.capitaliseSentence(slot.getDescription()), UtilText.parse(targetedCharacter, "[npc.NameIsFull] already in this slot..."), null);
				}
				
				if(!position.isSlotUnlocked(targetedCharacter, slot, positioningSlots).getKey()) {
					return new Response(Util.capitaliseSentence(slot.getDescription()), position.isSlotUnlocked(targetedCharacter, slot, positioningSlots).getValue(), null);
				}

				if(!Main.sex.getInitialSexManager().isSlotAvailable(targetedCharacter, slot)) {
					return new Response(Util.capitaliseSentence(slot.getDescription()), UtilText.parse(targetedCharacter, "[npc.Name] cannot access this slot in this sex scene..."), null);
				}
				
				if(characterInSlot!=null && !Main.sex.getInitialSexManager().isSlotAvailable(characterInSlot, Main.sex.getSexPositionSlot(targetedCharacter))) {
					return new Response(Util.capitaliseSentence(slot.getDescription()),
							UtilText.parse(characterInSlot, targetedCharacter, "[npc.Name] cannot access [npc2.namePos] slot in this sex scene, so [npc2.name] cannot swap with [npc.herHim]..."),
							null);
				}
				
				return new Response(
						characterInSlot!=null && slot!=SexSlotGeneric.MISC_WATCHING
							?"<span style='color:"+characterInSlot.getFemininity().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(slot.getDescription())+"</span>"
							:Util.capitaliseSentence(slot.getDescription()),
						characterInSlot!=null && slot!=SexSlotGeneric.MISC_WATCHING
							?UtilText.parse(targetedCharacter, characterInSlot,
									"[npc2.NameIsFull] already assigned to this slot, so by selecting [npc.name] to take this slot, you will swap the two of them around."
									+ " (Resulting in [npc2.name] being in the '"+positioningSlots.get(targetedCharacter).getDescription()+"' slot.)")
							:UtilText.parse(targetedCharacter, "Assign [npc.name] to this slot."),
						POSITIONING_MENU){
					@Override
					public void effects() {
						if(slot!=SexSlotGeneric.MISC_WATCHING) {
							GameCharacter characterToSwap = null;
							for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
								if(e.getValue().equals(slot)) {
									characterToSwap = e.getKey();
									break;
								}
							}
							if(characterToSwap!=null) {
								positioningSlots.put(characterToSwap, positioningSlots.get(targetedCharacter));
							}
						}
						positioningSlots.put(targetedCharacter, slot);
						
						// If change a slot and other slots become unavailable, move them to spectator with warning at bottom:
						Main.game.getTextEndStringBuilder().append(performAcceptableSlotsCheck());
					}
				};
				
			}  else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final SexAction OPEN_MENU_STANDING = new SexAction(
			SexActionType.POSITIONING_MENU,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isPositionMenuChangingAllowed(Main.game.getPlayer())
					&& (Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL || Main.sex.getCharacterPerformingAction().hasTraitActivated(Perk.CONVINCING_REQUESTS))
					&& Main.sex.getInitialSexManager().getAllowedSexPositions().contains(SexPosition.STANDING);
		}
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(SexPosition.STANDING.getName());
		}

		@Override
		public String getActionDescription() {
			return "Open the positioning menu for all sex positions related to the '"+Util.capitaliseSentence(SexPosition.STANDING.getName())+"' core position.";
		}

		@Override
		public String getDescription() {
			return "";
		}

		@Override
		public void applyEffects() {
			position = SexPosition.STANDING;
			SexSlot[] domSlots = new SexSlot[] {SexSlotStanding.STANDING_DOMINANT, SexSlotStanding.STANDING_DOMINANT_TWO, SexSlotStanding.STANDING_DOMINANT_THREE, SexSlotStanding.STANDING_DOMINANT_FOUR};
			SexSlot[] subSlots = new SexSlot[] {SexSlotStanding.STANDING_SUBMISSIVE, SexSlotStanding.STANDING_SUBMISSIVE_TWO, SexSlotStanding.STANDING_SUBMISSIVE_THREE, SexSlotStanding.STANDING_SUBMISSIVE_FOUR};
			List<GameCharacter> doms = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
			List<GameCharacter> subs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
			
			if(Main.sex.getSexManager().getPosition()==SexPosition.STANDING) {
				positioningSlots = new HashMap<>(Main.sex.getAllOccupiedSlots(true));
				
			} else {
				positioningSlots = new HashMap<>();
				for(int i=0; i<doms.size(); i++) {
					positioningSlots.put(doms.get(i), domSlots[i]);
				}
				for(int i=0; i<subs.size(); i++) {
					positioningSlots.put(subs.get(i), subSlots[i]);
				}
				
				for(GameCharacter c : Main.sex.getDominantSpectators()) {
					positioningSlots.put(c, SexSlotGeneric.MISC_WATCHING);
				}
				for(GameCharacter c : Main.sex.getSubmissiveSpectators()) {
					positioningSlots.put(c, SexSlotGeneric.MISC_WATCHING);
				}
			}
			
			targetedCharacter = Main.game.getPlayer();//Main.sex.getCharacterTargetedForSexAction(this);
			availableSlots = getAvailableSlots(targetedCharacter);
			
			performAcceptableSlotsCheck();
			
			Main.mainController.openPhone(POSITIONING_MENU);
		}
	};
	
	public static final SexAction OPEN_MENU_AGAINST_WALL = new SexAction(
			SexActionType.POSITIONING_MENU,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isPositionMenuChangingAllowed(Main.game.getPlayer())
					&& (Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL || Main.sex.getCharacterPerformingAction().hasTraitActivated(Perk.CONVINCING_REQUESTS))
					&& Main.sex.getInitialSexManager().getAllowedSexPositions().contains(SexPosition.AGAINST_WALL);
		}
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(SexPosition.AGAINST_WALL.getName());
		}

		@Override
		public String getActionDescription() {
			return "Open the positioning menu for all sex positions related to the '"+Util.capitaliseSentence(SexPosition.AGAINST_WALL.getName())+"' core position.";
		}

		@Override
		public String getDescription() {
			return "";
		}

		@Override
		public void applyEffects() {
			position = SexPosition.AGAINST_WALL;
			SexSlot[] domSlots = new SexSlot[] {SexSlotAgainstWall.STANDING_WALL, SexSlotAgainstWall.STANDING_WALL_TWO, SexSlotAgainstWall.STANDING_WALL_THREE, SexSlotAgainstWall.STANDING_WALL_FOUR};
			SexSlot[] subSlots = new SexSlot[] {SexSlotAgainstWall.FACE_TO_WALL, SexSlotAgainstWall.FACE_TO_WALL_TWO, SexSlotAgainstWall.FACE_TO_WALL_THREE, SexSlotAgainstWall.FACE_TO_WALL_FOUR};
			List<GameCharacter> doms = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
			List<GameCharacter> subs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
			
			if(Main.sex.getSexManager().getPosition()==SexPosition.AGAINST_WALL) {
				positioningSlots = new HashMap<>(Main.sex.getAllOccupiedSlots(true));
				
			} else {
				positioningSlots = new HashMap<>();
				for(int i=0; i<doms.size(); i++) {
					positioningSlots.put(doms.get(i), domSlots[i]);
				}
				for(int i=0; i<subs.size(); i++) {
					positioningSlots.put(subs.get(i), subSlots[i]);
				}
				
				for(GameCharacter c : Main.sex.getDominantSpectators()) {
					positioningSlots.put(c, SexSlotGeneric.MISC_WATCHING);
				}
				for(GameCharacter c : Main.sex.getSubmissiveSpectators()) {
					positioningSlots.put(c, SexSlotGeneric.MISC_WATCHING);
				}
			}

			targetedCharacter = Main.game.getPlayer();//Main.sex.getCharacterTargetedForSexAction(this);
			availableSlots = getAvailableSlots(targetedCharacter);
			
			performAcceptableSlotsCheck();
			
			Main.mainController.openPhone(POSITIONING_MENU);
		}
	};
	
	public static final SexAction OPEN_MENU_SITTING = new SexAction(
			SexActionType.POSITIONING_MENU,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			int taurs = 0;
			for(GameCharacter participant : Main.sex.getAllParticipants(true)) {
				if(participant.isTaur()) {
					taurs++;
				}
			}
			return Main.sex.getAllParticipants(true).size()!=taurs // Need at least one non-taur
					&& taurs<=4 // Cannot have more than 4 taurs
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isPositionMenuChangingAllowed(Main.game.getPlayer())
					&& (Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL || Main.sex.getCharacterPerformingAction().hasTraitActivated(Perk.CONVINCING_REQUESTS))
					&& Main.sex.getInitialSexManager().getAllowedSexPositions().contains(SexPosition.SITTING);
		}
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(SexPosition.SITTING.getName());
		}

		@Override
		public String getActionDescription() {
			return "Open the positioning menu for all sex positions related to the '"+Util.capitaliseSentence(SexPosition.SITTING.getName())+"' core position.";
		}

		@Override
		public String getDescription() {
			return "";
		}

		@Override
		public void applyEffects() {
			position = SexPosition.SITTING;
			SexSlot[] domSlots = new SexSlot[] {SexSlotSitting.SITTING, SexSlotSitting.SITTING_TWO, SexSlotSitting.SITTING_THREE, SexSlotSitting.SITTING_FOUR};
			SexSlot[] subSlots = new SexSlot[] {SexSlotSitting.SITTING_IN_LAP, SexSlotSitting.SITTING_IN_LAP_TWO, SexSlotSitting.SITTING_IN_LAP_THREE, SexSlotSitting.SITTING_IN_LAP_FOUR,
					SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.PERFORMING_ORAL_TWO, SexSlotSitting.PERFORMING_ORAL_THREE, SexSlotSitting.PERFORMING_ORAL_FOUR};
			
			List<GameCharacter> doms = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
			List<GameCharacter> subs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
			
			if(Main.sex.getSexManager().getPosition()==SexPosition.SITTING) {
				positioningSlots = new HashMap<>(Main.sex.getAllOccupiedSlots(true));
				
			} else {
				positioningSlots = new HashMap<>();
				int sittingCount = 0;
				int inLapCount = 0;
				
				for(int i=0; i<doms.size(); i++) {
					if(!doms.get(i).isTaur()) {
						positioningSlots.put(doms.get(i), domSlots[sittingCount]);
						sittingCount++;
					} else {
						positioningSlots.put(doms.get(i), subSlots[inLapCount]);
						inLapCount++;
					}
				}
				for(int i=0; i<subs.size(); i++) {
					if(sittingCount==0 && !subs.get(i).isTaur()) {
						positioningSlots.put(subs.get(i), domSlots[sittingCount]);
						sittingCount++;
						
					} else {
						positioningSlots.put(subs.get(i), subSlots[inLapCount]);
						inLapCount++;
					}
				}
				
				for(GameCharacter c : Main.sex.getDominantSpectators()) {
					positioningSlots.put(c, SexSlotGeneric.MISC_WATCHING);
				}
				for(GameCharacter c : Main.sex.getSubmissiveSpectators()) {
					positioningSlots.put(c, SexSlotGeneric.MISC_WATCHING);
				}
			}

			targetedCharacter = Main.game.getPlayer();//Main.sex.getCharacterTargetedForSexAction(this);
			availableSlots = getAvailableSlots(targetedCharacter);
			
			performAcceptableSlotsCheck();
			
			Main.mainController.openPhone(POSITIONING_MENU);
		}
	};
	
	public static final SexAction OPEN_MENU_OVER_DESK = new SexAction(
			SexActionType.POSITIONING_MENU,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isPositionMenuChangingAllowed(Main.game.getPlayer())
					&& (Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL || Main.sex.getCharacterPerformingAction().hasTraitActivated(Perk.CONVINCING_REQUESTS))
					&& Main.sex.getInitialSexManager().getAllowedSexPositions().contains(SexPosition.OVER_DESK);
		}
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(SexPosition.OVER_DESK.getName());
		}

		@Override
		public String getActionDescription() {
			return "Open the positioning menu for all sex positions related to the '"+Util.capitaliseSentence(SexPosition.OVER_DESK.getName())+"' core position.";
		}

		@Override
		public String getDescription() {
			return "";
		}

		@Override
		public void applyEffects() {
			position = SexPosition.OVER_DESK;
			SexSlot[] domSlots = new SexSlot[] {SexSlotDesk.BETWEEN_LEGS, SexSlotDesk.BETWEEN_LEGS_TWO, SexSlotDesk.BETWEEN_LEGS_THREE, SexSlotDesk.BETWEEN_LEGS_FOUR};
			SexSlot[] subSlots = new SexSlot[] {SexSlotDesk.OVER_DESK_ON_FRONT, SexSlotDesk.OVER_DESK_ON_FRONT_TWO, SexSlotDesk.OVER_DESK_ON_FRONT_THREE, SexSlotDesk.OVER_DESK_ON_FRONT_FOUR};
			List<GameCharacter> doms = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
			List<GameCharacter> subs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
			
			if(Main.sex.getSexManager().getPosition()==SexPosition.OVER_DESK) {
				positioningSlots = new HashMap<>(Main.sex.getAllOccupiedSlots(true));
				
			} else {
				positioningSlots = new HashMap<>();
				for(int i=0; i<doms.size(); i++) {
					positioningSlots.put(doms.get(i), domSlots[i]);
				}
				for(int i=0; i<subs.size(); i++) {
					positioningSlots.put(subs.get(i), subSlots[i]);
				}
				
				for(GameCharacter c : Main.sex.getDominantSpectators()) {
					positioningSlots.put(c, SexSlotGeneric.MISC_WATCHING);
				}
				for(GameCharacter c : Main.sex.getSubmissiveSpectators()) {
					positioningSlots.put(c, SexSlotGeneric.MISC_WATCHING);
				}
			}

			targetedCharacter = Main.game.getPlayer();//Main.sex.getCharacterTargetedForSexAction(this);
			availableSlots = getAvailableSlots(targetedCharacter);
			
			performAcceptableSlotsCheck();
			
			Main.mainController.openPhone(POSITIONING_MENU);
		}
	};
	
	public static final SexAction OPEN_MENU_ALL_FOURS = new SexAction(
			SexActionType.POSITIONING_MENU,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isPositionMenuChangingAllowed(Main.game.getPlayer())
					&& (Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL || Main.sex.getCharacterPerformingAction().hasTraitActivated(Perk.CONVINCING_REQUESTS))
					&& Main.sex.getInitialSexManager().getAllowedSexPositions().contains(SexPosition.ALL_FOURS);
		}
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(SexPosition.ALL_FOURS.getName());
		}

		@Override
		public String getActionDescription() {
			return "Open the positioning menu for all sex positions related to the '"+Util.capitaliseSentence(SexPosition.ALL_FOURS.getName())+"' core position.";
		}

		@Override
		public String getDescription() {
			return "";
		}

		@Override
		public void applyEffects() {
			position = SexPosition.ALL_FOURS;
			SexSlot[] domSlots = new SexSlot[] {SexSlotAllFours.BEHIND, SexSlotAllFours.BEHIND_TWO, SexSlotAllFours.BEHIND_THREE, SexSlotAllFours.BEHIND_FOUR};
			SexSlot[] subSlots = new SexSlot[] {SexSlotAllFours.ALL_FOURS, SexSlotAllFours.ALL_FOURS_TWO, SexSlotAllFours.ALL_FOURS_THREE, SexSlotAllFours.ALL_FOURS_FOUR};
			List<GameCharacter> doms = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
			List<GameCharacter> subs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
			
			if(Main.sex.getSexManager().getPosition()==SexPosition.ALL_FOURS) {
				positioningSlots = new HashMap<>(Main.sex.getAllOccupiedSlots(true));
				
			} else {
				positioningSlots = new HashMap<>();
				for(int i=0; i<doms.size(); i++) {
					positioningSlots.put(doms.get(i), domSlots[i]);
				}
				for(int i=0; i<subs.size(); i++) {
					positioningSlots.put(subs.get(i), subSlots[i]);
				}
				
				for(GameCharacter c : Main.sex.getDominantSpectators()) {
					positioningSlots.put(c, SexSlotGeneric.MISC_WATCHING);
				}
				for(GameCharacter c : Main.sex.getSubmissiveSpectators()) {
					positioningSlots.put(c, SexSlotGeneric.MISC_WATCHING);
				}
			}

			targetedCharacter = Main.game.getPlayer();//Main.sex.getCharacterTargetedForSexAction(this);
			availableSlots = getAvailableSlots(targetedCharacter);
			
			performAcceptableSlotsCheck();
			
			Main.mainController.openPhone(POSITIONING_MENU);
		}
	};
	
	public static final SexAction OPEN_MENU_LYING_DOWN = new SexAction(
			SexActionType.POSITIONING_MENU,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isPositionMenuChangingAllowed(Main.game.getPlayer())
					&& (Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL || Main.sex.getCharacterPerformingAction().hasTraitActivated(Perk.CONVINCING_REQUESTS))
					&& Main.sex.getInitialSexManager().getAllowedSexPositions().contains(SexPosition.LYING_DOWN);
		}
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(SexPosition.LYING_DOWN.getName());
		}

		@Override
		public String getActionDescription() {
			return "Open the positioning menu for all sex positions related to the '"+Util.capitaliseSentence(SexPosition.LYING_DOWN.getName())+"' core position.";
		}

		@Override
		public String getDescription() {
			return "";
		}

		@Override
		public void applyEffects() {
			position = SexPosition.LYING_DOWN;
			SexSlot[] domSlots = new SexSlot[] {SexSlotLyingDown.MISSIONARY, SexSlotLyingDown.MISSIONARY_TWO, SexSlotLyingDown.MISSIONARY_THREE, SexSlotLyingDown.MISSIONARY_FOUR};
			SexSlot[] subSlots = new SexSlot[] {SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.LYING_DOWN_TWO, SexSlotLyingDown.LYING_DOWN_THREE, SexSlotLyingDown.LYING_DOWN_FOUR};
			List<GameCharacter> doms = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
			List<GameCharacter> subs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
			
			if(Main.sex.getSexManager().getPosition()==SexPosition.LYING_DOWN) {
				positioningSlots = new HashMap<>(Main.sex.getAllOccupiedSlots(true));
				
			} else {
				positioningSlots = new HashMap<>();
				for(int i=0; i<doms.size(); i++) {
					positioningSlots.put(doms.get(i), domSlots[i]);
				}
				for(int i=0; i<subs.size(); i++) {
					positioningSlots.put(subs.get(i), subSlots[i]);
				}
				
				for(GameCharacter c : Main.sex.getDominantSpectators()) {
					positioningSlots.put(c, SexSlotGeneric.MISC_WATCHING);
				}
				for(GameCharacter c : Main.sex.getSubmissiveSpectators()) {
					positioningSlots.put(c, SexSlotGeneric.MISC_WATCHING);
				}
			}

			targetedCharacter = Main.game.getPlayer();//Main.sex.getCharacterTargetedForSexAction(this);
			availableSlots = getAvailableSlots(targetedCharacter);
			
			performAcceptableSlotsCheck();
			
			Main.mainController.openPhone(POSITIONING_MENU);
		}
	};
	
	public static final SexAction OPEN_MENU_STOCKS = new SexAction(
			SexActionType.POSITIONING_MENU,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isPositionMenuChangingAllowed(Main.game.getPlayer())
					&& (Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL || Main.sex.getCharacterPerformingAction().hasTraitActivated(Perk.CONVINCING_REQUESTS))
					&& Main.sex.getInitialSexManager().getAllowedSexPositions().contains(SexPosition.STOCKS);
		}
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(SexPosition.STOCKS.getName());
		}

		@Override
		public String getActionDescription() {
			return "Open the positioning menu for all sex positions related to the '"+Util.capitaliseSentence(SexPosition.STOCKS.getName())+"' core position.";
		}

		@Override
		public String getDescription() {
			return "";
		}

		@Override
		public void applyEffects() {
			position = SexPosition.STOCKS;
			SexSlot[] domSlots = new SexSlot[] {SexSlotStocks.BEHIND_STOCKS, SexSlotStocks.BEHIND_STOCKS_TWO, SexSlotStocks.BEHIND_STOCKS_THREE, SexSlotStocks.BEHIND_STOCKS_FOUR};
			SexSlot[] subSlots = new SexSlot[] {SexSlotStocks.LOCKED_IN_STOCKS, SexSlotStocks.LOCKED_IN_STOCKS_TWO, SexSlotStocks.LOCKED_IN_STOCKS_THREE, SexSlotStocks.LOCKED_IN_STOCKS_FOUR};
			List<GameCharacter> doms = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
			List<GameCharacter> subs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
			
			if(Main.sex.getSexManager().getPosition()==SexPosition.STOCKS) {
				positioningSlots = new HashMap<>(Main.sex.getAllOccupiedSlots(true));
				
			} else {
				positioningSlots = new HashMap<>();
				for(int i=0; i<doms.size(); i++) {
					positioningSlots.put(doms.get(i), domSlots[i]);
				}
				for(int i=0; i<subs.size(); i++) {
					positioningSlots.put(subs.get(i), subSlots[i]);
				}
				
				for(GameCharacter c : Main.sex.getDominantSpectators()) {
					positioningSlots.put(c, SexSlotGeneric.MISC_WATCHING);
				}
				for(GameCharacter c : Main.sex.getSubmissiveSpectators()) {
					positioningSlots.put(c, SexSlotGeneric.MISC_WATCHING);
				}
			}

			targetedCharacter = Main.game.getPlayer();//Main.sex.getCharacterTargetedForSexAction(this);
			availableSlots = getAvailableSlots(targetedCharacter);
			
			performAcceptableSlotsCheck();
			
			Main.mainController.openPhone(POSITIONING_MENU);
		}
	};

	public static final SexAction OPEN_MENU_MILKING_STALL = new SexAction(
			SexActionType.POSITIONING_MENU,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isPositionMenuChangingAllowed(Main.game.getPlayer())
					&& (Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL || Main.sex.getCharacterPerformingAction().hasTraitActivated(Perk.CONVINCING_REQUESTS))
					&& Main.sex.getInitialSexManager().getAllowedSexPositions().contains(SexPosition.MILKING_STALL);
		}
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(SexPosition.MILKING_STALL.getName());
		}

		@Override
		public String getActionDescription() {
			return "Open the positioning menu for all sex positions related to the '"+Util.capitaliseSentence(SexPosition.MILKING_STALL.getName())+"' core position.";
		}

		@Override
		public String getDescription() {
			return "";
		}

		@Override
		public void applyEffects() {
			position = SexPosition.MILKING_STALL;
			SexSlot[] domSlots = new SexSlot[] {SexSlotMilkingStall.BEHIND_MILKING_STALL, SexSlotMilkingStall.BEHIND_MILKING_STALL_TWO, SexSlotMilkingStall.BEHIND_MILKING_STALL_THREE, SexSlotMilkingStall.BEHIND_MILKING_STALL_FOUR};
			SexSlot[] subSlots = new SexSlot[] {SexSlotMilkingStall.LOCKED_IN_MILKING_STALL, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_TWO, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_THREE, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_FOUR};
			List<GameCharacter> doms = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
			List<GameCharacter> subs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
			
			if(Main.sex.getSexManager().getPosition()==SexPosition.MILKING_STALL) {
				positioningSlots = new HashMap<>(Main.sex.getAllOccupiedSlots(true));
				
			} else {
				positioningSlots = new HashMap<>();
				for(int i=0; i<doms.size(); i++) {
					positioningSlots.put(doms.get(i), domSlots[i]);
				}
				for(int i=0; i<subs.size(); i++) {
					positioningSlots.put(subs.get(i), subSlots[i]);
				}
				
				for(GameCharacter c : Main.sex.getDominantSpectators()) {
					positioningSlots.put(c, SexSlotGeneric.MISC_WATCHING);
				}
				for(GameCharacter c : Main.sex.getSubmissiveSpectators()) {
					positioningSlots.put(c, SexSlotGeneric.MISC_WATCHING);
				}
			}

			targetedCharacter = Main.game.getPlayer();//Main.sex.getCharacterTargetedForSexAction(this);
			availableSlots = getAvailableSlots(targetedCharacter);
			
			performAcceptableSlotsCheck();
			
			Main.mainController.openPhone(POSITIONING_MENU);
		}
	};
}
