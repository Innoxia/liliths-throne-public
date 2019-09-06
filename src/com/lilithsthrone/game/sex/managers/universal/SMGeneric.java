package com.lilithsthrone.game.sex.managers.universal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;

/**
 * A generic sex manager, meant to be used for initialising generic sex scenes. Will typically start in the 'standing' position.
 * 
 * @since 0.3.1
 * @version 0.3.4
 * @author Innoxia
 */
public class SMGeneric extends SexManagerDefault {

	List<GameCharacter> dominantSpectators;
	List<GameCharacter> submissiveSpectators;
	List<ResponseTag> tags;
	
	public SMGeneric(
			List<GameCharacter> dominants,
			List<GameCharacter> submissives,
			List<GameCharacter> dominantSpectators,
			List<GameCharacter> submissiveSpectators,
			ResponseTag... tags) {
		this(dominants, submissives, dominantSpectators, submissiveSpectators, Arrays.asList(tags));
	}
	
	public SMGeneric(
			List<GameCharacter> dominants,
			List<GameCharacter> submissives,
			List<GameCharacter> dominantSpectators,
			List<GameCharacter> submissiveSpectators,
			List<ResponseTag> tags) {
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
		
		this.tags = tags;
		
		SexSlot[] slotsDominant;
		SexSlot[] slotsSubmissive;
		if(nonBiped) { // This scene contains characters who are non-bipedal, so use the SexPositionOther classes:
			if(tags.contains(ResponseTag.PREFER_DOGGY)) {
				this.position = SexPosition.ALL_FOURS;
				if(submissives.size()==1) {
					slotsDominant = new SexSlot[] {SexSlotAllFours.BEHIND, SexSlotAllFours.IN_FRONT};
				} else {
					slotsDominant = new SexSlot[] {SexSlotAllFours.BEHIND, SexSlotAllFours.BEHIND_TWO, SexSlotAllFours.IN_FRONT, SexSlotAllFours.IN_FRONT_TWO};
				}
				slotsSubmissive = new SexSlot[] {SexSlotAllFours.ALL_FOURS, SexSlotAllFours.ALL_FOURS_TWO, SexSlotAllFours.ALL_FOURS_THREE, SexSlotAllFours.ALL_FOURS_FOUR};
				
			} else if(tags.contains(ResponseTag.PREFER_ORAL)) {
				this.position = SexPosition.STANDING;
				slotsDominant = new SexSlot[] {SexSlotStanding.STANDING_DOMINANT, SexSlotStanding.STANDING_DOMINANT_TWO, SexSlotStanding.STANDING_DOMINANT_THREE, SexSlotStanding.STANDING_DOMINANT_FOUR};
				if(dominants.size()==1) {
					if(dominants.get(0).hasPenis() || !dominants.get(0).isTaur()) {
						slotsSubmissive = new SexSlot[] {SexSlotStanding.PERFORMING_ORAL, SexSlotStanding.PERFORMING_ORAL_BEHIND, SexSlotStanding.PERFORMING_ORAL_TWO, SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO};
					} else {
						slotsSubmissive = new SexSlot[] {SexSlotStanding.PERFORMING_ORAL_BEHIND, SexSlotStanding.PERFORMING_ORAL, SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO, SexSlotStanding.PERFORMING_ORAL_TWO};
					}
				} else {
					slotsSubmissive = new SexSlot[] {SexSlotStanding.PERFORMING_ORAL, SexSlotStanding.PERFORMING_ORAL_TWO, SexSlotStanding.PERFORMING_ORAL_THREE, SexSlotStanding.PERFORMING_ORAL_FOUR};
				}
				
			} else if(tags.contains(ResponseTag.PREFER_COW_GIRL)) {
				this.position = SexPosition.LYING_DOWN;
				if(submissives.size()==1) {
					slotsDominant = new SexSlot[] {SexSlotLyingDown.COWGIRL, SexSlotLyingDown.FACE_SITTING_REVERSE};
				} else {
					slotsDominant = new SexSlot[] {SexSlotLyingDown.COWGIRL, SexSlotLyingDown.COWGIRL_TWO, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.FACE_SITTING_REVERSE_TWO};
				}
				slotsSubmissive = new SexSlot[] {SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.LYING_DOWN_TWO};
				
			} else {
				this.position = SexPosition.STANDING;
				slotsDominant = new SexSlot[] {SexSlotStanding.STANDING_DOMINANT, SexSlotStanding.STANDING_DOMINANT_TWO, SexSlotStanding.STANDING_DOMINANT_THREE, SexSlotStanding.STANDING_DOMINANT_FOUR};
				slotsSubmissive = new SexSlot[] {SexSlotStanding.STANDING_SUBMISSIVE, SexSlotStanding.STANDING_SUBMISSIVE_TWO, SexSlotStanding.STANDING_SUBMISSIVE_THREE, SexSlotStanding.STANDING_SUBMISSIVE_FOUR};
			}
			
		} else {
			if(tags.contains(ResponseTag.PREFER_DOGGY)) {
				this.position = SexPosition.ALL_FOURS;
				if(submissives.size()==1) {
					slotsDominant = new SexSlot[] {SexSlotAllFours.BEHIND, SexSlotAllFours.IN_FRONT, SexSlotAllFours.IN_FRONT_TWO, SexSlotAllFours.USING_FEET};
				} else {
					slotsDominant = new SexSlot[] {
							SexSlotAllFours.BEHIND, SexSlotAllFours.BEHIND_TWO,
							SexSlotAllFours.IN_FRONT, SexSlotAllFours.IN_FRONT_TWO,
							SexSlotAllFours.IN_FRONT_THREE, SexSlotAllFours.IN_FRONT_FOUR};
				}
				slotsSubmissive = new SexSlot[] {SexSlotAllFours.ALL_FOURS, SexSlotAllFours.ALL_FOURS_TWO, SexSlotAllFours.ALL_FOURS_THREE, SexSlotAllFours.ALL_FOURS_FOUR};

			} else if(tags.contains(ResponseTag.PREFER_ORAL)) {
				this.position = SexPosition.STANDING;
				slotsDominant = new SexSlot[] {
						SexSlotStanding.STANDING_DOMINANT,
						SexSlotStanding.STANDING_DOMINANT_TWO,
						SexSlotStanding.STANDING_DOMINANT_THREE,
						SexSlotStanding.STANDING_DOMINANT_FOUR};
				if(dominants.size()==1) {
					slotsSubmissive = new SexSlot[] {SexSlotStanding.PERFORMING_ORAL, SexSlotStanding.PERFORMING_ORAL_TWO, SexSlotStanding.PERFORMING_ORAL_THREE, SexSlotStanding.PERFORMING_ORAL_BEHIND};
				} else {
					slotsSubmissive = new SexSlot[] {
							SexSlotStanding.PERFORMING_ORAL,
							SexSlotStanding.PERFORMING_ORAL_TWO,
							SexSlotStanding.PERFORMING_ORAL_THREE,
							SexSlotStanding.PERFORMING_ORAL_FOUR};
				}
			
			} else if(tags.contains(ResponseTag.PREFER_COW_GIRL)) {
				this.position = SexPosition.LYING_DOWN;
				if(submissives.size()==1) {
					slotsDominant = new SexSlot[] {SexSlotLyingDown.COWGIRL, SexSlotLyingDown.FACE_SITTING_REVERSE};
				} else {
					slotsDominant = new SexSlot[] {SexSlotLyingDown.COWGIRL, SexSlotLyingDown.COWGIRL_TWO, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.FACE_SITTING_REVERSE_TWO};
				}
				slotsSubmissive = new SexSlot[] {SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.LYING_DOWN_TWO, SexSlotLyingDown.LYING_DOWN_THREE, SexSlotLyingDown.LYING_DOWN_FOUR};
				
			} else {
				this.position = SexPosition.STANDING;
				slotsDominant = new SexSlot[] {SexSlotStanding.STANDING_DOMINANT, SexSlotStanding.STANDING_DOMINANT_TWO, SexSlotStanding.STANDING_DOMINANT_THREE, SexSlotStanding.STANDING_DOMINANT_FOUR};
				slotsSubmissive = new SexSlot[] {SexSlotStanding.STANDING_SUBMISSIVE, SexSlotStanding.STANDING_SUBMISSIVE_TWO, SexSlotStanding.STANDING_SUBMISSIVE_THREE, SexSlotStanding.STANDING_SUBMISSIVE_FOUR};
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
	
	@Override
	public SexPace getForcedSexPace(GameCharacter character) {
		for(ResponseTag tag : tags) {
			switch(tag) {
				case PREFER_DOGGY:
				case PREFER_MISSIONARY:
				case PREFER_ORAL:
				case PREFER_COW_GIRL:
				case DISABLE_POSITIONING:
					break;
				case START_PACE_PLAYER_DOM_GENTLE:
					if(character.isPlayer()) {
						return SexPace.DOM_GENTLE;
					}
					break;
				case START_PACE_PLAYER_DOM_ROUGH:
					if(character.isPlayer()) {
						return SexPace.DOM_ROUGH;
					}
					break;
				case START_PACE_PLAYER_SUB_EAGER:
					if(character.isPlayer()) {
						return SexPace.SUB_EAGER;
					}
					break;
				case START_PACE_PLAYER_SUB_RESISTING:
					if(character.isPlayer()) {
						return SexPace.SUB_RESISTING;
					}
					break;
			}
		}
		return super.getForcedSexPace(character);
	}
	
}
