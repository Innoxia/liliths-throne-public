package com.lilithsthrone.game.sex.managers.universal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;
import com.lilithsthrone.game.sex.positions.SexPositionOther;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexSlotBipeds;
import com.lilithsthrone.game.sex.positions.SexSlotOther;

/**
 * A generic sex manager, meant to be used for initialising generic sex scenes. Will typically start in the 'standing' position.
 * 
 * @since 0.3.1
 * @version 0.3.1
 * @author Innoxia
 */
public class SMGeneric extends SexManagerDefault {

	List<GameCharacter> dominantSpectators;
	List<GameCharacter> submissiveSpectators;
	
	public SMGeneric(
			List<GameCharacter> dominants,
			List<GameCharacter> submissives,
			List<GameCharacter> dominantSpectators,
			List<GameCharacter> submissiveSpectators,
			ResponseTag... tags) {
		super(null, null, null);
		
		this.dominantSpectators = new ArrayList<>();
		if(dominantSpectators!=null) {
			this.dominantSpectators.addAll(dominantSpectators);
			this.dominantSpectators.removeIf(sp -> sp==null);
		}

		this.submissiveSpectators = new ArrayList<>();
		if(submissiveSpectators!=null) {
			this.submissiveSpectators.addAll(submissiveSpectators);
			this.submissiveSpectators.removeIf(sp -> sp==null);
		}
		
		boolean nonBiped=false;
		for(GameCharacter character : submissives) {
			if(character.getLegConfiguration()!=LegConfiguration.BIPEDAL) {
				nonBiped=true;
				break;
			}
		}
		if(!nonBiped) {
			for(GameCharacter character : dominants) {
				if(character.getLegConfiguration()!=LegConfiguration.BIPEDAL) {
					nonBiped=true;
					break;
				}
			}
		}
		
		SexSlot[] slotsDominant;
		SexSlot[] slotsSubmissive;
		if(nonBiped) { // This scene contains characters who are non-bipedal, so use the SexPositionOther classes:
			if(Arrays.asList(tags).contains(ResponseTag.PREFER_DOGGY)) {
				this.position = SexPositionOther.ALL_FOURS;
				if(submissives.size()==1) {
					slotsDominant = new SexSlot[] {SexSlotOther.ALL_FOURS_MOUNTING, SexSlotOther.IN_FRONT_OF_ALL_FOURS_TARGET};
				} else {
					slotsDominant = new SexSlot[] {SexSlotOther.ALL_FOURS_MOUNTING, SexSlotOther.ALL_FOURS_MOUNTING_TWO, SexSlotOther.IN_FRONT_OF_ALL_FOURS_TARGET, SexSlotOther.IN_FRONT_OF_ALL_FOURS_TARGET_TWO};
				}
				slotsSubmissive = new SexSlot[] {SexSlotOther.ALL_FOURS_FUCKED, SexSlotOther.ALL_FOURS_FUCKED_TWO};
				
			} else {
				this.position = SexPositionOther.STANDING;
				slotsDominant = new SexSlot[] {SexSlotOther.STANDING_DOMINANT, SexSlotOther.STANDING_DOMINANT_TWO};
				slotsSubmissive = new SexSlot[] {SexSlotOther.STANDING_SUBMISSIVE, SexSlotOther.STANDING_SUBMISSIVE_TWO};
			}
			
		} else {
			if(Arrays.asList(tags).contains(ResponseTag.PREFER_DOGGY)) {
				this.position = SexPositionBipeds.DOGGY_STYLE;
				if(submissives.size()==1) {
					slotsDominant = new SexSlot[] {SexSlotBipeds.DOGGY_BEHIND, SexSlotBipeds.DOGGY_INFRONT, SexSlotBipeds.DOGGY_INFRONT_TWO, SexSlotBipeds.DOGGY_FEET};
				} else {
					slotsDominant = new SexSlot[] {
							SexSlotBipeds.DOGGY_BEHIND, SexSlotBipeds.DOGGY_BEHIND_SECOND,
							SexSlotBipeds.DOGGY_INFRONT, SexSlotBipeds.DOGGY_INFRONT_SECOND,
							SexSlotBipeds.DOGGY_INFRONT_TWO, SexSlotBipeds.DOGGY_INFRONT_SECOND_TWO};
				}
				slotsSubmissive = new SexSlot[] {SexSlotBipeds.DOGGY_ON_ALL_FOURS, SexSlotBipeds.DOGGY_ON_ALL_FOURS_SECOND, SexSlotBipeds.DOGGY_ON_ALL_FOURS_THIRD, SexSlotBipeds.DOGGY_ON_ALL_FOURS_FOURTH};
				
			} else {
				this.position = SexPositionBipeds.STANDING;
				slotsDominant = new SexSlot[] {SexSlotBipeds.STANDING_DOMINANT};
				slotsSubmissive = new SexSlot[] {SexSlotBipeds.STANDING_SUBMISSIVE};
			}
		}
		setUpVariables(dominants, slotsDominant, submissives, slotsSubmissive);
	}
	
	private void setUpVariables(List<GameCharacter> dominants, SexSlot[] slotsDominant, List<GameCharacter> submissives, SexSlot[] slotsSubmissive) {
		Map<GameCharacter, SexSlot> newPositionDominants = new HashMap<>();
		Map<GameCharacter, SexSlot> newPositionSubmissives = new HashMap<>();
		
		int i = 0;
		for(GameCharacter character : dominants) { // Assign dominants to slots, and if there aren't enough slots, put dominants as dominant spectators:
			if(i<slotsDominant.length) {
				newPositionDominants.put(character, slotsDominant[i]);
			} else {
				this.dominantSpectators.add(character);
			}
			i++;
		}
		this.dominants = newPositionDominants;
		
		i = 0;
		for(GameCharacter character : submissives) { // Assign submissives to slots, and if there aren't enough slots, put submissives as submissive spectators:
			if(i<slotsSubmissive.length) {
				newPositionSubmissives.put(character, slotsSubmissive[i]);
			} else {
				this.submissiveSpectators.add(character);
			}
			i++;
		}
		this.submissives = newPositionSubmissives;
	}

	public List<GameCharacter> getDominantSpectators() {
		return dominantSpectators;
	}

	public List<GameCharacter> getSubmissiveSpectators() {
		return submissiveSpectators;
	}
	
}
