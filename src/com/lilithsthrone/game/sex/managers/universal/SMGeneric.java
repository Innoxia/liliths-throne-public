package com.lilithsthrone.game.sex.managers.universal;

import java.util.ArrayList;
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
		
		// This scene contains characters who are non-bipedal:
		if(nonBiped) {
			this.position = SexPositionOther.STANDING;
			SexSlot[] slotsDominant = new SexSlot[] {SexSlotOther.STANDING_DOMINANT, SexSlotOther.STANDING_DOMINANT_TWO};
			SexSlot[] slotsSubmissive = new SexSlot[] {SexSlotOther.STANDING_SUBMISSIVE, SexSlotOther.STANDING_SUBMISSIVE_TWO};
			setUpVariables(dominants, slotsDominant, submissives, slotsSubmissive);
			
		} else {
			this.position = SexPositionBipeds.STANDING;
			SexSlot[] slotsDominant = new SexSlot[] {SexSlotBipeds.STANDING_DOMINANT};
			SexSlot[] slotsSubmissive = new SexSlot[] {SexSlotBipeds.STANDING_SUBMISSIVE};
			setUpVariables(dominants, slotsDominant, submissives, slotsSubmissive);
		}
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
