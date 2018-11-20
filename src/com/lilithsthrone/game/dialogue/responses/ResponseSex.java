package com.lilithsthrone.game.dialogue.responses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.game.sex.managers.universal.SMDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMKneeling;
import com.lilithsthrone.game.sex.managers.universal.SMMissionary;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.69
 * @version 0.2.11
 * @author Innoxia
 */
public class ResponseSex extends Response {
	
	private boolean consensual;
	private boolean subHasEqualControl;
	private SexManagerInterface sexManager;
	private List<GameCharacter> dominantSpectators;
	private List<GameCharacter> submissiveSpectators;
	private DialogueNodeOld postSexDialogue;
	private String sexStartDescription;
	
	public ResponseSex(String title,
			String tooltipText,
			boolean consensual,
			boolean subHasEqualControl,
			List<GameCharacter> dominants,
			List<GameCharacter> submissives,
			List<GameCharacter> dominantSpectators,
			List<GameCharacter> submissiveSpectators,
			DialogueNodeOld postSexDialogue,
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
			List<Perk> perksRequired,
			Femininity femininityRequired,
			Race raceRequired,
			boolean consensual,
			boolean subHasEqualControl,
			List<GameCharacter> dominants,
			List<GameCharacter> submissives,
			List<GameCharacter> dominantSpectators,
			List<GameCharacter> submissiveSpectators,
			DialogueNodeOld postSexDialogue,
			String sexStartDescription, ResponseTag... tags) {
		this(title, tooltipText, fetishesForUnlock, fetishesBlocking, corruptionBypass, perksRequired, femininityRequired, raceRequired,
				consensual, subHasEqualControl, null, dominantSpectators, submissiveSpectators, postSexDialogue, sexStartDescription);

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
				if(Sex.isSizeDifference(Util.mergeLists(submissives, dominants))) {
					this.sexManager = new SexManagerDefault(
							SexPositionType.STANDING_SIZE_DIFFERENCE,
							Util.newHashMapOfValues(new Value<>(dominants.get(0), dominants.get(0).getHeightValue()>submissives.get(0).getHeightValue()?SexPositionSlot.STANDING_SD_TALLER:SexPositionSlot.STANDING_SD_SMALLER)),
							Util.newHashMapOfValues(new Value<>(submissives.get(0), submissives.get(0).getHeightValue()>dominants.get(0).getHeightValue()?SexPositionSlot.STANDING_SD_TALLER:SexPositionSlot.STANDING_SD_SMALLER))) {
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
											case START_PACE_PLAYER_SUB_RESIST:
												return SexPace.SUB_RESISTING;
											case START_PACE_PLAYER_SUB_EAGER:
												return SexPace.SUB_EAGER;
											case PREFER_ORAL:
											case PREFER_MISSIONARY:
											case PREFER_DOGGY:
												break;
										}
									}
								}
							}
							return null;
						}
						@Override
						public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
							if(ResponseSex.this.exposeAtStartOfSexMap()!=null) {
								return ResponseSex.this.exposeAtStartOfSexMap();
							}
							return super.exposeAtStartOfSexMap();
						}
					};
					
				} else {
					this.sexManager = new SMStanding(
							Util.newHashMapOfValues(new Value<>(dominants.get(0), SexPositionSlot.STANDING_DOMINANT)),
							Util.newHashMapOfValues(new Value<>(submissives.get(0), SexPositionSlot.STANDING_SUBMISSIVE))) {
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
											case START_PACE_PLAYER_SUB_RESIST:
												return SexPace.SUB_RESISTING;
											case START_PACE_PLAYER_SUB_EAGER:
												return SexPace.SUB_EAGER;
											case PREFER_ORAL:
											case PREFER_MISSIONARY:
											case PREFER_DOGGY:
												break;
										}
									}
								}
							}
							return null;
						}
						@Override
						public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
							if(ResponseSex.this.exposeAtStartOfSexMap()!=null) {
								return ResponseSex.this.exposeAtStartOfSexMap();
							}
							return super.exposeAtStartOfSexMap();
						}
					};
				}
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
			SexManagerInterface sexManager,
			List<GameCharacter> dominantSpectators,
			List<GameCharacter> submissiveSpectators,
			DialogueNodeOld postSexDialogue) {
		this(title, tooltipText, consensual, subHasEqualControl, sexManager, dominantSpectators, submissiveSpectators, postSexDialogue, "");
	}

	public ResponseSex(String title,
			String tooltipText,
			boolean consensual,
			boolean subHasEqualControl,
			SexManagerInterface sexManager,
			List<GameCharacter> dominantSpectators,
			List<GameCharacter> submissiveSpectators,
			DialogueNodeOld postSexDialogue,
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
			List<Perk> perksRequired,
			Femininity femininityRequired,
			Race raceRequired,
			boolean consensual,
			boolean subHasEqualControl,
			SexManagerInterface sexManager,
			List<GameCharacter> dominantSpectators,
			List<GameCharacter> submissiveSpectators,
			DialogueNodeOld postSexDialogue,
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
		}
		
		if(submissiveSpectators==null) {
			this.submissiveSpectators = new ArrayList<>();
		} else {
			this.submissiveSpectators = submissiveSpectators;
		}
		
		this.postSexDialogue = postSexDialogue;
		this.sexStartDescription = sexStartDescription;
	}
	
	@Override
	public boolean isSexHighlight() {
		return true;
	}
	
	@Override
	public boolean disabledOnNullDialogue(){
		return false;
	}
	
	/**
	 * Override for use in automatically-generated sex managers.
	 */
	public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
		return null;
	}
	
	public DialogueNodeOld initSex() {
		return Main.sexEngine.initialiseSex(consensual, subHasEqualControl, sexManager, dominantSpectators, submissiveSpectators, postSexDialogue, sexStartDescription);
	}

	private void generateOralPosition(List<GameCharacter> submissives, List<GameCharacter> sortedDominants, List<GameCharacter> dominantSpectators, List<GameCharacter> submissiveSpectators, ResponseTag... tags) {
		
		Map<GameCharacter, SexPositionSlot> subMap = new HashMap<>();
		Map<GameCharacter, SexPositionSlot> domMap = new HashMap<>();
		
		List<SexPositionSlot> subSlots = new ArrayList<>();
		subSlots.add(SexPositionSlot.KNEELING_PERFORMING_ORAL);
		if(sortedDominants.size()>1) {
			subSlots.add(SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND);
		}
		subSlots.add(SexPositionSlot.KNEELING_PERFORMING_ORAL_TWO);
		if(sortedDominants.size()>1) {
			subSlots.add(SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND_TWO);
		}
		subSlots.add(SexPositionSlot.KNEELING_PERFORMING_ORAL_THREE);
		if(sortedDominants.size()>1) {
			subSlots.add(SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND_THREE);
		}
		if(sortedDominants.size()==1) {
			subSlots.add(SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND);
			subSlots.add(SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND_TWO);
			subSlots.add(SexPositionSlot.KNEELING_PERFORMING_ORAL_SECOND_THREE);
		}

		List<SexPositionSlot> domSlots = new ArrayList<>();
		domSlots.add(SexPositionSlot.KNEELING_RECEIVING_ORAL);
		if(submissives.size()>1) {
			domSlots.add(SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND);
		}
		domSlots.add(SexPositionSlot.KNEELING_RECEIVING_ORAL_TWO);
		if(submissives.size()>1) {
			domSlots.add(SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND_TWO);
		}
		domSlots.add(SexPositionSlot.KNEELING_RECEIVING_ORAL_THREE);
		if(submissives.size()>1) {
			domSlots.add(SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND_THREE);
		}
		if(submissives.size()==1) {
			domSlots.add(SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND);
			domSlots.add(SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND_TWO);
			domSlots.add(SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND_THREE);
		}
		
		for(int i=0; i<submissives.size(); i++) {
			subMap.put(submissives.get(i), subSlots.get(i));
		}
		
		for(int i=0; i<sortedDominants.size(); i++) {
			domMap.put(sortedDominants.get(i), domSlots.get(i));
		}
		
		this.sexManager = new SMKneeling(domMap, subMap) {
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
								case START_PACE_PLAYER_SUB_RESIST:
									return SexPace.SUB_RESISTING;
								case START_PACE_PLAYER_SUB_EAGER:
									return SexPace.SUB_EAGER;
								case PREFER_ORAL:
								case PREFER_MISSIONARY:
								case PREFER_DOGGY:
									break;
							}
						}
					}
				}
				return null;
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				if(ResponseSex.this.exposeAtStartOfSexMap()!=null) {
					return ResponseSex.this.exposeAtStartOfSexMap();
				}
				return super.exposeAtStartOfSexMap();
			}
		};
	
	}

	private void generateMissionaryPosition(List<GameCharacter> submissives, List<GameCharacter> sortedDominants, List<GameCharacter> dominantSpectators, List<GameCharacter> submissiveSpectators, ResponseTag... tags) {
		
		Map<GameCharacter, SexPositionSlot> subMap = Util.newHashMapOfValues(new Value<>(submissives.get(0), SexPositionSlot.MISSIONARY_ON_BACK));
		Map<GameCharacter, SexPositionSlot> domMap = new HashMap<>();
		
		if(submissives.size()==1) {
			
			List<SexPositionSlot> domSlots = new ArrayList<>();
			domSlots.add(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS);
			domSlots.add(SexPositionSlot.MISSIONARY_FACE_SITTING);
			domSlots.add(SexPositionSlot.MISSIONARY_KNEELING_BESIDE);
			domSlots.add(SexPositionSlot.MISSIONARY_KNEELING_BESIDE_TWO);
			domSlots.add(SexPositionSlot.MISC_WATCHING);
			domSlots.add(SexPositionSlot.MISC_WATCHING);
			

			List<SexPositionSlot> addedSDSlots = new ArrayList<>();
			
			for(int i=1; i<sortedDominants.size(); i++) {
				if(sortedDominants.get(i).isSizeDifferenceShorterThan(submissives.get(0)) && !addedSDSlots.contains(SexPositionSlot.MISSIONARY_SD_HUMPING)) {
					domSlots.add(i, SexPositionSlot.MISSIONARY_SD_HUMPING);
					addedSDSlots.add(SexPositionSlot.MISSIONARY_SD_HUMPING);
				}
				if(i>1 && sortedDominants.get(i).hasPenis() && !addedSDSlots.contains(SexPositionSlot.MISSIONARY_SD_PAIZURI)) {
					domSlots.add(i, SexPositionSlot.MISSIONARY_SD_PAIZURI);
					addedSDSlots.add(SexPositionSlot.MISSIONARY_SD_PAIZURI);
				}
			}
			
			outerCheck:
			for(int i=0; i<domSlots.size() && i<sortedDominants.size();i++) {
				if(domSlots.get(i)==SexPositionSlot.MISSIONARY_FACE_SITTING) {
					if(sortedDominants.get(i).getFetishDesire(Fetish.FETISH_ORAL_RECEIVING).isNegative()) {
						for(int j=0; j<domSlots.size() && j<sortedDominants.size();j++) {
							if(j!=i && domSlots.get(j)!=SexPositionSlot.MISSIONARY_SD_HUMPING && !sortedDominants.get(j).getFetishDesire(Fetish.FETISH_ORAL_RECEIVING).isNegative()) {
								Collections.swap(sortedDominants, i, j);
								break outerCheck;
							}
						}
					}
				}
			}
			
			for(int i=0; i<sortedDominants.size(); i++) {
				domMap.put(sortedDominants.get(i), domSlots.get(i));
			}
			
		} else if(submissives.size()==2) {
			subMap.put(submissives.get(1), SexPositionSlot.MISSIONARY_ON_BACK_SECOND);

			for(GameCharacter character : sortedDominants) {
				if(!character.getFetishDesire(Fetish.FETISH_ORAL_RECEIVING).isPositive()) {
					if(!domMap.values().contains(SexPositionSlot.MISSIONARY_FACE_SITTING)) {
						domMap.put(character, SexPositionSlot.MISSIONARY_FACE_SITTING);
						
					} else if(!domMap.values().contains(SexPositionSlot.MISSIONARY_FACE_SITTING_SECOND)) {
						domMap.put(character, SexPositionSlot.MISSIONARY_FACE_SITTING_SECOND);
						
					}
				}
				if(domMap.containsKey(character)) {
					continue;
				}
				
				if(character.isSizeDifferenceShorterThan(submissives.get(0)) && !domMap.values().contains(SexPositionSlot.MISSIONARY_SD_HUMPING)) {
					domMap.put(character, SexPositionSlot.MISSIONARY_SD_HUMPING);
					
				} else if(character.isSizeDifferenceShorterThan(submissives.get(1)) && !domMap.values().contains(SexPositionSlot.MISSIONARY_SD_HUMPING_SECOND)) {
					domMap.put(character, SexPositionSlot.MISSIONARY_SD_HUMPING_SECOND);
					
				}
				
				if(character.hasPenis()) { // These should fill up first, due to sortedDominants ordering characters with a penis first:
					 if(!domMap.values().contains(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS)) {
						domMap.put(character, SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS);
						
					} else if(!domMap.values().contains(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS_SECOND)) {
						domMap.put(character, SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS_SECOND);
					}
				}
				if(domMap.containsKey(character)) {
					continue;
				}
				
				if(!domMap.values().contains(SexPositionSlot.MISSIONARY_KNEELING_BESIDE)) {
					domMap.put(character, SexPositionSlot.MISSIONARY_KNEELING_BESIDE);
					
				} else if(!domMap.values().contains(SexPositionSlot.MISSIONARY_KNEELING_BESIDE_TWO)) {
					domMap.put(character, SexPositionSlot.MISSIONARY_KNEELING_BESIDE_TWO);
					
				} else if(!domMap.values().contains(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS)) {
					domMap.put(character, SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS);
					
				} else if(!domMap.values().contains(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS_SECOND)) {
					domMap.put(character, SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS_SECOND);
					
				} else if(!domMap.values().contains(SexPositionSlot.MISSIONARY_KNEELING_BESIDE_TWO)) {
					domMap.put(character, SexPositionSlot.MISSIONARY_KNEELING_BESIDE_TWO);
					
				} else if(!domMap.values().contains(SexPositionSlot.MISSIONARY_KNEELING_BESIDE_SECOND_TWO)) {
					domMap.put(character, SexPositionSlot.MISSIONARY_KNEELING_BESIDE_SECOND_TWO);
				}
			}
			
		} else {
			domMap.put(sortedDominants.get(0), SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS);
			if(sortedDominants.size()>1) {
				domMap.put(sortedDominants.get(1), SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS_SECOND);
			}

			SexPositionSlot[] subSlots = new SexPositionSlot[] {SexPositionSlot.MISSIONARY_ON_BACK_SECOND, SexPositionSlot.MISSIONARY_ON_BACK_THIRD, SexPositionSlot.MISSIONARY_ON_BACK_FOURTH};

			for(int i=0; i<submissives.size()-1; i++) {
				subMap.put(submissives.get(i+1), subSlots[i]);
			}
		}
		
		this.sexManager = new SMMissionary(domMap, subMap) {
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
								case START_PACE_PLAYER_SUB_RESIST:
									return SexPace.SUB_RESISTING;
								case START_PACE_PLAYER_SUB_EAGER:
									return SexPace.SUB_EAGER;
								case PREFER_ORAL:
								case PREFER_MISSIONARY:
								case PREFER_DOGGY:
									break;
							}
						}
					}
				}
				return null;
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				if(ResponseSex.this.exposeAtStartOfSexMap()!=null) {
					return ResponseSex.this.exposeAtStartOfSexMap();
				}
				return super.exposeAtStartOfSexMap();
			}
		};
	}
	
	private void generateDoggyPosition(List<GameCharacter> submissives, List<GameCharacter> sortedDominants, List<GameCharacter> dominantSpectators, List<GameCharacter> submissiveSpectators, ResponseTag... tags) {
		
		Map<GameCharacter, SexPositionSlot> subMap = Util.newHashMapOfValues(new Value<>(submissives.get(0), SexPositionSlot.DOGGY_ON_ALL_FOURS));
		Map<GameCharacter, SexPositionSlot> domMap = new HashMap<>();
		
		if(submissives.size()==1) {
			
			List<SexPositionSlot> domSlots = new ArrayList<>();
			domSlots.add(SexPositionSlot.DOGGY_BEHIND);
			domSlots.add(SexPositionSlot.DOGGY_INFRONT);
			domSlots.add(SexPositionSlot.DOGGY_INFRONT_TWO);
			domSlots.add(SexPositionSlot.DOGGY_FEET);
			domSlots.add(SexPositionSlot.DOGGY_FEET_SECOND);
			domSlots.add(SexPositionSlot.MISC_WATCHING);

			List<SexPositionSlot> addedSDSlots = new ArrayList<>();
			
			for(int i=0; i<sortedDominants.size(); i++) {
				if(sortedDominants.get(i).isSizeDifferenceShorterThan(submissives.get(0)) && !addedSDSlots.contains(SexPositionSlot.DOGGY_SD_HUMPING)) {
					domSlots.add(i, SexPositionSlot.DOGGY_SD_HUMPING);
					addedSDSlots.add(SexPositionSlot.DOGGY_SD_HUMPING);
				}
				if(i>3 && sortedDominants.get(i).isSizeDifferenceShorterThan(submissives.get(0)) && !addedSDSlots.contains(SexPositionSlot.DOGGY_SD_UNDER)) {
					domSlots.add(i, SexPositionSlot.DOGGY_SD_UNDER);
					addedSDSlots.add(SexPositionSlot.DOGGY_SD_UNDER);
				}
			}
			
			for(int i=0; i<sortedDominants.size(); i++) {
				domMap.put(sortedDominants.get(i), domSlots.get(i));
			}
			
		} else if(submissives.size()==2) {
			subMap.put(submissives.get(1), SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND);

//			SexPositionSlot[] extraDomSlots = new SexPositionSlot[] {SexPositionSlot.DOGGY_INFRONT, SexPositionSlot.DOGGY_INFRONT_SECOND, SexPositionSlot.DOGGY_INFRONT_TWO, SexPositionSlot.DOGGY_INFRONT_SECOND_TWO};
			
			for(GameCharacter character : sortedDominants) {
				if(character.hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
					if(!domMap.values().contains(SexPositionSlot.DOGGY_INFRONT)) {
						domMap.put(character, SexPositionSlot.DOGGY_INFRONT);
						
					} else if(!domMap.values().contains(SexPositionSlot.DOGGY_INFRONT_SECOND)) {
						domMap.put(character, SexPositionSlot.DOGGY_INFRONT_SECOND);
						
					} else if(!domMap.values().contains(SexPositionSlot.DOGGY_INFRONT_SECOND)) {
						domMap.put(character, SexPositionSlot.DOGGY_INFRONT_SECOND);
						
					} else if(!domMap.values().contains(SexPositionSlot.DOGGY_INFRONT_SECOND_TWO)) {
						domMap.put(character, SexPositionSlot.DOGGY_INFRONT_SECOND_TWO);
					}
				}
				if(domMap.containsKey(character)) {
					continue;
				}
				
				if(character.hasPenis()) { // These should fill up first, due to sortedDominants ordering characters with a penis first:
					if(character.isSizeDifferenceShorterThan(submissives.get(0)) && !domMap.values().contains(SexPositionSlot.DOGGY_SD_HUMPING)) {
						domMap.put(character, SexPositionSlot.DOGGY_SD_HUMPING);
						
					} else if(character.isSizeDifferenceShorterThan(submissives.get(1)) && !domMap.values().contains(SexPositionSlot.DOGGY_SD_HUMPING_SECOND)) {
						domMap.put(character, SexPositionSlot.DOGGY_SD_HUMPING_SECOND);
						
					} else if(!domMap.values().contains(SexPositionSlot.DOGGY_BEHIND)) {
						domMap.put(character, SexPositionSlot.DOGGY_BEHIND);
						
					} else if(!domMap.values().contains(SexPositionSlot.DOGGY_BEHIND_SECOND)) {
						domMap.put(character, SexPositionSlot.DOGGY_BEHIND_SECOND);
					}
				}
				if(domMap.containsKey(character)) {
					continue;
				}
				
				if(!domMap.values().contains(SexPositionSlot.DOGGY_INFRONT)) {
					domMap.put(character, SexPositionSlot.DOGGY_INFRONT);
					
				} else if(!domMap.values().contains(SexPositionSlot.DOGGY_INFRONT_SECOND)) {
					domMap.put(character, SexPositionSlot.DOGGY_INFRONT_SECOND);
					
				} else if(!domMap.values().contains(SexPositionSlot.DOGGY_BEHIND)) {
					domMap.put(character, SexPositionSlot.DOGGY_BEHIND);
					
				} else if(!domMap.values().contains(SexPositionSlot.DOGGY_BEHIND_SECOND)) {
					domMap.put(character, SexPositionSlot.DOGGY_BEHIND_SECOND);
					
				} else if(!domMap.values().contains(SexPositionSlot.DOGGY_INFRONT_SECOND)) {
					domMap.put(character, SexPositionSlot.DOGGY_INFRONT_SECOND);
					
				} else if(!domMap.values().contains(SexPositionSlot.DOGGY_INFRONT_SECOND_TWO)) {
					domMap.put(character, SexPositionSlot.DOGGY_INFRONT_SECOND_TWO);
				}
				
			}
			
		} else {
			domMap.put(sortedDominants.get(0), SexPositionSlot.DOGGY_BEHIND);
			if(sortedDominants.size()>1) {
				domMap.put(sortedDominants.get(1), SexPositionSlot.DOGGY_BEHIND_SECOND);
			}

			SexPositionSlot[] subSlots = new SexPositionSlot[] {SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND, SexPositionSlot.DOGGY_ON_ALL_FOURS_THIRD, SexPositionSlot.DOGGY_ON_ALL_FOURS_FOURTH};

			for(int i=0; i<submissives.size()-1; i++) {
				subMap.put(submissives.get(i+1), subSlots[i]);
			}
		}
		
		this.sexManager = new SMDoggy(domMap, subMap) {
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
								case START_PACE_PLAYER_SUB_RESIST:
									return SexPace.SUB_RESISTING;
								case START_PACE_PLAYER_SUB_EAGER:
									return SexPace.SUB_EAGER;
								case PREFER_ORAL:
								case PREFER_MISSIONARY:
								case PREFER_DOGGY:
									break;
							}
						}
					}
				}
				return null;
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				if(ResponseSex.this.exposeAtStartOfSexMap()!=null) {
					return ResponseSex.this.exposeAtStartOfSexMap();
				}
				return super.exposeAtStartOfSexMap();
			}
		};
	}
	
}
