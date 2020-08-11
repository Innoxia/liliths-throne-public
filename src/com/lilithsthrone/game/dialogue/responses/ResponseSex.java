package com.lilithsthrone.game.dialogue.responses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.game.sex.managers.universal.SMAllFours;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMLyingDown;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.69
 * @version 0.3.9.1
 * @author Innoxia
 */
public class ResponseSex extends Response {
	
	private boolean consensual;
	private boolean subHasEqualControl;
	private SexManagerInterface sexManager;
	private List<GameCharacter> dominantSpectators;
	private List<GameCharacter> submissiveSpectators;
	private DialogueNode postSexDialogue;
	private String sexStartDescription;
	private ResponseTag[] tags;
	
	public ResponseSex(String title,
			String tooltipText,
			boolean consensual,
			boolean subHasEqualControl,
			List<GameCharacter> dominants,
			List<GameCharacter> submissives,
			List<GameCharacter> dominantSpectators,
			List<GameCharacter> submissiveSpectators,
			DialogueNode postSexDialogue,
			String sexStartDescription,
			ResponseTag... tags) {
		this(title,
				tooltipText,
				null,
				null,
				null,
				null,
				null,
				null,
				consensual,
				subHasEqualControl,
				dominants,
				submissives,
				dominantSpectators,
				submissiveSpectators,
				postSexDialogue,
				sexStartDescription,
				tags);
	}
	
	/**
	 * Automatically generates the SexManager (which assigns positions), based on the submissives and dominants passed in.
	 * 
	 * @param title The action title.
	 * @param tooltipText The text displayed in the action's tooltip.
	 * @param fetishesForUnlock Fetishes that remove corruption increases from selecting this action.
	 * @param fetishesBlocking Fetishes that block this action from being selected.
	 * @param corruptionBypass The level of corruption required to choose this action without incurring corruption increases.
	 * @param perksRequired Perks that are required to unlock this action.
	 * @param femininityRequired If this action requires the player to be of a certain femininity.
	 * @param raceRequired If this action requires the player to be of a certain race.
	 * @param consensual If this sex scene is consensual or not.
	 * @param subHasEqualControl If the submissives are able to use actions that start penetrations.
	 * @param dominants The dominant characters to use in this sex scene.
	 * @param submissives The submissive characters to use in this sex scene.
	 * @param dominantSpectators The characters spectating this sex scene. (To be displayed in the dominant side of the UI.)
	 * @param submissiveSpectators The characters spectating this sex scene. (To be displayed in the submissive side of the UI.)
	 * @param postSexDialogue The DialogueNode to be returned once the sex scene ends.
	 */
	public ResponseSex(String title,
			String tooltipText,
			List<Fetish> fetishesForUnlock,
			List<Fetish> fetishesBlocking,
			CorruptionLevel corruptionBypass,
			List<AbstractPerk> perksRequired,
			Femininity femininityRequired,
			AbstractRace raceRequired,
			boolean consensual,
			boolean subHasEqualControl,
			List<GameCharacter> dominants,
			List<GameCharacter> submissives,
			List<GameCharacter> dominantSpectators,
			List<GameCharacter> submissiveSpectators,
			DialogueNode postSexDialogue,
			String sexStartDescription,
			ResponseTag... tags) {
		this(title,
				tooltipText,
				fetishesForUnlock,
				fetishesBlocking,
				corruptionBypass,
				perksRequired,
				femininityRequired,
				raceRequired,
				consensual,
				subHasEqualControl,
				null,
				dominantSpectators,
				submissiveSpectators,
				postSexDialogue,
				sexStartDescription);

		this.tags = tags;
		
		// If size difference with just two participants, return relevant standing position: 
		if(submissives.size()==1 && dominants.size()==1) {
			boolean sexManagerSet = false;
			for(ResponseTag tag : tags) {
				if(tag!=null) {
					switch(tag) {
						case PREFER_ORAL:
							generateOralPosition(submissives, dominants, dominantSpectators, submissiveSpectators, tags);
							sexManagerSet = true;
							break;
						case PREFER_MISSIONARY:
							generateMissionaryPosition(submissives, dominants, dominantSpectators, submissiveSpectators, tags);
							sexManagerSet = true;
							break;
						case PREFER_DOGGY:
							generateDoggyPosition(submissives, dominants, dominantSpectators, submissiveSpectators, tags);
							sexManagerSet = true;
							break;
						default:
							break;
					}
				}
			}
			
			if(!sexManagerSet) {
				this.sexManager = new SMStanding(
						Util.newHashMapOfValues(new Value<>(dominants.get(0), SexSlotStanding.STANDING_DOMINANT)),
						Util.newHashMapOfValues(new Value<>(submissives.get(0), SexSlotStanding.STANDING_SUBMISSIVE))) {
					@Override
					public SexPace getStartingSexPaceModifier(GameCharacter character) {
						if(character.isPlayer()) {
							for(ResponseTag tag : tags) {
								if(tag!=null) {
									switch(tag) {
										case START_PACE_PLAYER_DOM_GENTLE:
											return SexPace.DOM_GENTLE;
										case START_PACE_PLAYER_DOM_ROUGH:
											return SexPace.DOM_ROUGH;
										case START_PACE_PLAYER_SUB_RESISTING:
											return SexPace.SUB_RESISTING;
										case START_PACE_PLAYER_SUB_EAGER:
											return SexPace.SUB_EAGER;
										case PREFER_ORAL:
										case PREFER_MISSIONARY:
										case PREFER_DOGGY:
										case PREFER_COW_GIRL:
										case DISABLE_POSITIONING:
											break;
									}
								}
							}
						}
						return null;
					}
					@Override
					public boolean isPositionChangingAllowed(GameCharacter character) {
						if(Arrays.asList(tags).contains(ResponseTag.DISABLE_POSITIONING)) {
							return false;
						}
						return super.isPositionChangingAllowed(character);
					}
				};
			}
			
		// If group sex:
		} else {
			// Sort so penis is used in humping/sex:
			List<GameCharacter> sortedDominants = new ArrayList<>(dominants);
			sortedDominants.sort((e1, e2) -> e1.hasPenis()?e2.hasPenis()?0:-1:1);

			boolean sexManagerSet = false;
			
			for(ResponseTag tag : tags) {
				if(tag!=null) {
					switch(tag) {
						case PREFER_ORAL:
							generateOralPosition(submissives, sortedDominants, dominantSpectators, submissiveSpectators, tags);
							sexManagerSet = true;
							break;
						case PREFER_MISSIONARY:
							generateMissionaryPosition(submissives, sortedDominants, dominantSpectators, submissiveSpectators, tags);
							sexManagerSet = true;
							break;
						case PREFER_DOGGY:
							generateDoggyPosition(submissives, sortedDominants, dominantSpectators, submissiveSpectators, tags);
							sexManagerSet = true;
							break;
						default:
							break;
					}
				}
			}
			
			if(!sexManagerSet) {
				int penisCount = 0;
				for(GameCharacter dom : sortedDominants) {
					if(dom.hasPenis()) {
						penisCount++;
					}
				}
				
				if(sortedDominants.size()==1) {
					if(Math.random()>0.5) {
						generateMissionaryPosition(submissives, sortedDominants, dominantSpectators, submissiveSpectators, tags);
					} else {
						generateDoggyPosition(submissives, sortedDominants, dominantSpectators, submissiveSpectators, tags);
					}
					
				} else {
					switch(penisCount) {
						case 0:
						case 1:
							generateMissionaryPosition(submissives, sortedDominants, dominantSpectators, submissiveSpectators, tags);
							break;
						case 2:
							if(Math.random()>0.5) {
								generateMissionaryPosition(submissives, sortedDominants, dominantSpectators, submissiveSpectators, tags);
							} else {
								generateDoggyPosition(submissives, sortedDominants, dominantSpectators, submissiveSpectators, tags);
							}
							break;
						default:
							generateDoggyPosition(submissives, sortedDominants, dominantSpectators, submissiveSpectators, tags);
							break;
					}
				}
			}
		}
	}
	
	public ResponseSex(String title,
			String tooltipText,
			boolean consensual,
			boolean subHasEqualControl,
			SMGeneric sexManager,
			DialogueNode postSexDialogue,
			String sexStartDescription) {
		this(title, tooltipText, null, null, null, null, null, null, consensual, subHasEqualControl, sexManager, postSexDialogue, sexStartDescription);
	}
	
	public ResponseSex(String title,
			String tooltipText,
			List<Fetish> fetishesForUnlock,
			List<Fetish> fetishesBlocking,
			CorruptionLevel corruptionBypass,
			List<AbstractPerk> perksRequired,
			Femininity femininityRequired,
			AbstractRace raceRequired,
			boolean consensual,
			boolean subHasEqualControl,
			SMGeneric sexManager,
			DialogueNode postSexDialogue,
			String sexStartDescription) {
		this(title, tooltipText, fetishesForUnlock, fetishesBlocking, corruptionBypass, perksRequired, femininityRequired, raceRequired, consensual, subHasEqualControl, sexManager, null, null, postSexDialogue, sexStartDescription);
		this.dominantSpectators = sexManager.getDominantSpectators();
		this.submissiveSpectators = sexManager.getSubmissiveSpectators();
	}
	
	public ResponseSex(String title,
			String tooltipText,
			boolean consensual,
			boolean subHasEqualControl,
			SexManagerInterface sexManager,
			List<GameCharacter> dominantSpectators,
			List<GameCharacter> submissiveSpectators,
			DialogueNode postSexDialogue) {
		this(title, tooltipText, consensual, subHasEqualControl, sexManager, dominantSpectators, submissiveSpectators, postSexDialogue, "");
	}

	public ResponseSex(String title,
			String tooltipText,
			boolean consensual,
			boolean subHasEqualControl,
			SexManagerInterface sexManager,
			List<GameCharacter> dominantSpectators,
			List<GameCharacter> submissiveSpectators,
			DialogueNode postSexDialogue,
			String sexStartDescription) {
		this(title, tooltipText,
				null, null, null, null,
				null, null, consensual,
				subHasEqualControl, sexManager, dominantSpectators, submissiveSpectators, postSexDialogue, sexStartDescription);
	}
	
	public ResponseSex(String title,
			String tooltipText,
			List<Fetish> fetishesForUnlock,
			List<Fetish> fetishesBlocking,
			CorruptionLevel corruptionBypass,
			List<AbstractPerk> perksRequired,
			Femininity femininityRequired,
			AbstractRace raceRequired,
			boolean consensual,
			boolean subHasEqualControl,
			SexManagerInterface sexManager,
			List<GameCharacter> dominantSpectators,
			List<GameCharacter> submissiveSpectators,
			DialogueNode postSexDialogue,
			String sexStartDescription) {
		super(title, tooltipText, null,
				fetishesForUnlock, corruptionBypass,
				perksRequired, femininityRequired, raceRequired);
		
		this.consensual = consensual;
		this.subHasEqualControl = subHasEqualControl;
		
		this.sexManager = sexManager;

		if(dominantSpectators==null) {
			this.dominantSpectators = new ArrayList<>();
		} else {
			this.dominantSpectators = dominantSpectators;
			while(this.dominantSpectators.contains(null)) {
				this.dominantSpectators.remove(null);
			}
		}
		
		if(submissiveSpectators==null) {
			this.submissiveSpectators = new ArrayList<>();
		} else {
			this.submissiveSpectators = submissiveSpectators;
			while(this.submissiveSpectators.contains(null)) {
				this.submissiveSpectators.remove(null);
			}
		}
		
		this.postSexDialogue = postSexDialogue;
		this.sexStartDescription = sexStartDescription;
	}
	
	@Override
	public String getTooltipText() {
		if(sexManager.getStartingSexPaceModifier(Main.game.getPlayer())!=null) {
			StringBuilder sb = new StringBuilder(tooltipText);
			SexPace pace = sexManager.getStartingSexPaceModifier(Main.game.getPlayer());
			sb.append("<br/><i>Starts sex in the '<span style='color:"+pace.getColour().toWebHexString()+";'>"+pace.getName()+"</span>' pace.</i>");
			return sb.toString();
			
		} else if(tags!=null) {
			StringBuilder sb = new StringBuilder(tooltipText);
			SexPace pace = null;
			if(Arrays.asList(tags).contains(ResponseTag.START_PACE_PLAYER_DOM_GENTLE)) {
				pace = SexPace.DOM_GENTLE;
			} else if(Arrays.asList(tags).contains(ResponseTag.START_PACE_PLAYER_DOM_ROUGH)) {
				pace = SexPace.DOM_ROUGH;
			} else if(Arrays.asList(tags).contains(ResponseTag.START_PACE_PLAYER_SUB_EAGER)) {
				pace = SexPace.SUB_EAGER;
			} else if(Arrays.asList(tags).contains(ResponseTag.START_PACE_PLAYER_SUB_RESISTING)) {
				pace = SexPace.SUB_RESISTING;
			}
			if(pace!=null) {
				sb.append("<br/><i>Starts sex in the '<span style='color:"+pace.getColour().toWebHexString()+";'>"+pace.getName()+"</span>' pace.</i>");
			}
			return sb.toString();
		}
		return tooltipText;
	}
	
	@Override
	public boolean isSexHighlight() {
		return true;
	}
	
	@Override
	public Colour getHighlightColour() {
		if(isPlayerInDominantSlot()) {
			return PresetColour.GENERIC_SEX_AS_DOM;
		} else {
			return PresetColour.GENERIC_SEX;
		}
	}
	
	@Override
	public boolean disabledOnNullDialogue(){
		return false;
	}
	
	public boolean isPlayerInDominantSlot() {
		return (sexManager!=null && sexManager.isPlayerDom()) || dominantSpectators.contains(Main.game.getPlayer());
	}
	
	public List<InitialSexActionInformation> getInitialSexActions() {
		return new ArrayList<>();
	}
	
	public DialogueNode initSex() {
		return Main.sex.initialiseSex(consensual, subHasEqualControl, sexManager, dominantSpectators, submissiveSpectators, postSexDialogue, sexStartDescription, getInitialSexActions());
	}
	
	/**
	 * This method is applied after the initSex() method has been called. It will not affect the content of the scene, so should just be used for modifying sex or foreplay preferences, or other background data.
	 */
	public void postSexInitEffects() {
	}

	private void generateOralPosition(List<GameCharacter> submissives, List<GameCharacter> sortedDominants, List<GameCharacter> dominantSpectators, List<GameCharacter> submissiveSpectators, ResponseTag... tags) {
		
		Map<GameCharacter, SexSlot> subMap = new HashMap<>();
		Map<GameCharacter, SexSlot> domMap = new HashMap<>();
		
		List<SexSlot> subSlots = new ArrayList<>();
		subSlots.add(SexSlotStanding.PERFORMING_ORAL);
		subSlots.add(SexSlotStanding.PERFORMING_ORAL_TWO);
		subSlots.add(SexSlotStanding.PERFORMING_ORAL_THREE);
		if(sortedDominants.size()==1) {
			subSlots.add(SexSlotStanding.PERFORMING_ORAL_BEHIND);
		} else {
			subSlots.add(SexSlotStanding.PERFORMING_ORAL_FOUR);
		}

		List<SexSlot> domSlots = new ArrayList<>();
		domSlots.add(SexSlotStanding.STANDING_DOMINANT);
		domSlots.add(SexSlotStanding.STANDING_DOMINANT_TWO);
		domSlots.add(SexSlotStanding.STANDING_DOMINANT_THREE);
		domSlots.add(SexSlotStanding.STANDING_DOMINANT_FOUR);
		
		
		List<GameCharacter> finalSubmissiveSpectators = new ArrayList<>();
		if(submissiveSpectators!=null) {
			finalSubmissiveSpectators.addAll(submissiveSpectators);
		}
		for(int i=0; i<submissives.size(); i++) {
			if(i>=subSlots.size()) {
				finalSubmissiveSpectators.add(submissives.get(i));
			} else {
				subMap.put(submissives.get(i), subSlots.get(i));
			}
		}

		List<GameCharacter> finalDominantSpectators = new ArrayList<>();
		if(dominantSpectators!=null) {
			finalDominantSpectators.addAll(dominantSpectators);
		}
		for(int i=0; i<sortedDominants.size(); i++) {
			if(i>=subSlots.size()) {
				finalDominantSpectators.add(sortedDominants.get(i));
			} else {
				domMap.put(sortedDominants.get(i), domSlots.get(i));
			}
		}
		
		this.submissiveSpectators = finalSubmissiveSpectators;
		this.dominantSpectators = finalDominantSpectators;
		this.sexManager = new SexManagerDefault(SexPosition.STANDING, domMap, subMap) {
			@Override
			public SexPace getStartingSexPaceModifier(GameCharacter character) {
				if(character.isPlayer()) {
					for(ResponseTag tag : tags) {
						if(tag!=null) {
							switch(tag) {
								case START_PACE_PLAYER_DOM_GENTLE:
									return SexPace.DOM_GENTLE;
								case START_PACE_PLAYER_DOM_ROUGH:
									return SexPace.DOM_ROUGH;
								case START_PACE_PLAYER_SUB_RESISTING:
									return SexPace.SUB_RESISTING;
								case START_PACE_PLAYER_SUB_EAGER:
									return SexPace.SUB_EAGER;
								case PREFER_ORAL:
								case PREFER_MISSIONARY:
								case PREFER_DOGGY:
								case PREFER_COW_GIRL:
								case DISABLE_POSITIONING:
									break;
							}
						}
					}
				}
				return null;
			}
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				if(Arrays.asList(tags).contains(ResponseTag.DISABLE_POSITIONING)) {
					return false;
				}
				return super.isPositionChangingAllowed(character);
			}
		};
	
	}

	private void generateMissionaryPosition(List<GameCharacter> submissives, List<GameCharacter> sortedDominants, List<GameCharacter> dominantSpectators, List<GameCharacter> submissiveSpectators, ResponseTag... tags) {
		
		Map<GameCharacter, SexSlot> subMap = new HashMap<>();
		Map<GameCharacter, SexSlot> domMap = new HashMap<>();

		List<SexSlot> domSlots = new ArrayList<>();
		List<SexSlot> subSlots = new ArrayList<>();

		subSlots.add(SexSlotLyingDown.LYING_DOWN);
		subSlots.add(SexSlotLyingDown.LYING_DOWN_TWO);
		subSlots.add(SexSlotLyingDown.LYING_DOWN_THREE);
		subSlots.add(SexSlotLyingDown.LYING_DOWN_FOUR);
		
		if(submissives.size()==1) {
			domSlots.add(SexSlotLyingDown.MISSIONARY);
			domSlots.add(SexSlotLyingDown.FACE_SITTING);
			domSlots.add(SexSlotLyingDown.BESIDE);
			domSlots.add(SexSlotLyingDown.BESIDE_TWO);
			
		} else if(submissives.size()==2) {
			domSlots.add(SexSlotLyingDown.MISSIONARY);
			domSlots.add(SexSlotLyingDown.FACE_SITTING);
			domSlots.add(SexSlotLyingDown.MISSIONARY_TWO);
			domSlots.add(SexSlotLyingDown.FACE_SITTING_TWO);
			
		} else if(submissives.size()==3) {
			domSlots.add(SexSlotLyingDown.MISSIONARY);
			domSlots.add(SexSlotLyingDown.FACE_SITTING);
			domSlots.add(SexSlotLyingDown.MISSIONARY_TWO);
			domSlots.add(SexSlotLyingDown.MISSIONARY_THREE);
			
		} else {
			domSlots.add(SexSlotLyingDown.MISSIONARY);
			domSlots.add(SexSlotLyingDown.MISSIONARY_TWO);
			domSlots.add(SexSlotLyingDown.MISSIONARY_THREE);
			domSlots.add(SexSlotLyingDown.MISSIONARY_FOUR);
		}

		
		List<GameCharacter> finalSubmissiveSpectators = new ArrayList<>();
		if(submissiveSpectators!=null) {
			finalSubmissiveSpectators.addAll(submissiveSpectators);
		}
		for(int i=0; i<submissives.size(); i++) {
			if(i>=subSlots.size()) {
				finalSubmissiveSpectators.add(submissives.get(i));
			} else {
				subMap.put(submissives.get(i), subSlots.get(i));
			}
		}

		List<GameCharacter> finalDominantSpectators = new ArrayList<>();
		if(dominantSpectators!=null) {
			finalDominantSpectators.addAll(dominantSpectators);
		}
		for(int i=0; i<sortedDominants.size(); i++) {
			if(i>=subSlots.size()) {
				finalDominantSpectators.add(sortedDominants.get(i));
			} else {
				domMap.put(sortedDominants.get(i), domSlots.get(i));
			}
		}
		
		this.submissiveSpectators = finalSubmissiveSpectators;
		this.dominantSpectators = finalDominantSpectators;
		this.sexManager = new SMLyingDown(domMap, subMap) {
			@Override
			public SexPace getStartingSexPaceModifier(GameCharacter character) {
				if(character.isPlayer()) {
					for(ResponseTag tag : tags) {
						if(tag!=null) {
							switch(tag) {
								case START_PACE_PLAYER_DOM_GENTLE:
									return SexPace.DOM_GENTLE;
								case START_PACE_PLAYER_DOM_ROUGH:
									return SexPace.DOM_ROUGH;
								case START_PACE_PLAYER_SUB_RESISTING:
									return SexPace.SUB_RESISTING;
								case START_PACE_PLAYER_SUB_EAGER:
									return SexPace.SUB_EAGER;
								case PREFER_ORAL:
								case PREFER_MISSIONARY:
								case PREFER_DOGGY:
								case PREFER_COW_GIRL:
								case DISABLE_POSITIONING:
									break;
							}
						}
					}
				}
				return null;
			}
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				if(Arrays.asList(tags).contains(ResponseTag.DISABLE_POSITIONING)) {
					return false;
				}
				return super.isPositionChangingAllowed(character);
			}
		};
	}
	
	private void generateDoggyPosition(List<GameCharacter> submissives, List<GameCharacter> sortedDominants, List<GameCharacter> dominantSpectators, List<GameCharacter> submissiveSpectators, ResponseTag... tags) {
		
		Map<GameCharacter, SexSlot> subMap = new HashMap<>();
		Map<GameCharacter, SexSlot> domMap = new HashMap<>();

		List<SexSlot> domSlots = new ArrayList<>();
		List<SexSlot> subSlots = new ArrayList<>();

		subSlots.add(SexSlotAllFours.ALL_FOURS);
		subSlots.add(SexSlotAllFours.ALL_FOURS_TWO);
		subSlots.add(SexSlotAllFours.ALL_FOURS_THREE);
		subSlots.add(SexSlotAllFours.ALL_FOURS_FOUR);
		
		if(submissives.size()==1) {
			domSlots.add(SexSlotAllFours.BEHIND);
			domSlots.add(SexSlotAllFours.IN_FRONT);
			domSlots.add(SexSlotAllFours.IN_FRONT_TWO);
			domSlots.add(SexSlotAllFours.HUMPING);
			
		} else if(submissives.size()==2) {
			domSlots.add(SexSlotAllFours.BEHIND);
			domSlots.add(SexSlotAllFours.IN_FRONT);
			domSlots.add(SexSlotAllFours.BEHIND_TWO);
			domSlots.add(SexSlotAllFours.IN_FRONT_TWO);
			
		} else if(submissives.size()==3) {
			domSlots.add(SexSlotAllFours.BEHIND);
			domSlots.add(SexSlotAllFours.IN_FRONT);
			domSlots.add(SexSlotAllFours.BEHIND_TWO);
			domSlots.add(SexSlotAllFours.BEHIND_THREE);
			
		} else {
			domSlots.add(SexSlotAllFours.BEHIND);
			domSlots.add(SexSlotAllFours.BEHIND_TWO);
			domSlots.add(SexSlotAllFours.BEHIND_THREE);
			domSlots.add(SexSlotAllFours.BEHIND_FOUR);
		}

		
		List<GameCharacter> finalSubmissiveSpectators = new ArrayList<>();
		if(submissiveSpectators!=null) {
			finalSubmissiveSpectators.addAll(submissiveSpectators);
		}
		for(int i=0; i<submissives.size(); i++) {
			if(i>=subSlots.size()) {
				finalSubmissiveSpectators.add(submissives.get(i));
			} else {
				subMap.put(submissives.get(i), subSlots.get(i));
			}
		}

		List<GameCharacter> finalDominantSpectators = new ArrayList<>();
		if(dominantSpectators!=null) {
			finalDominantSpectators.addAll(dominantSpectators);
		}
		for(int i=0; i<sortedDominants.size(); i++) {
			if(i>=subSlots.size()) {
				finalDominantSpectators.add(sortedDominants.get(i));
			} else {
				domMap.put(sortedDominants.get(i), domSlots.get(i));
			}
		}
		
		this.submissiveSpectators = finalSubmissiveSpectators;
		this.dominantSpectators = finalDominantSpectators;
		this.sexManager = new SMAllFours(domMap, subMap) {
			@Override
			public SexPace getStartingSexPaceModifier(GameCharacter character) {
				if(character.isPlayer()) {
					for(ResponseTag tag : tags) {
						if(tag!=null) {
							switch(tag) {
								case START_PACE_PLAYER_DOM_GENTLE:
									return SexPace.DOM_GENTLE;
								case START_PACE_PLAYER_DOM_ROUGH:
									return SexPace.DOM_ROUGH;
								case START_PACE_PLAYER_SUB_RESISTING:
									return SexPace.SUB_RESISTING;
								case START_PACE_PLAYER_SUB_EAGER:
									return SexPace.SUB_EAGER;
								case PREFER_ORAL:
								case PREFER_MISSIONARY:
								case PREFER_DOGGY:
								case PREFER_COW_GIRL:
								case DISABLE_POSITIONING:
									break;
							}
						}
					}
				}
				return null;
			}
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				if(Arrays.asList(tags).contains(ResponseTag.DISABLE_POSITIONING)) {
					return false;
				}
				return super.isPositionChangingAllowed(character);
			}
		};
	}
	
}
