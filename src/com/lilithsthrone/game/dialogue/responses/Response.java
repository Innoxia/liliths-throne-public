package com.lilithsthrone.game.dialogue.responses;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.combat.moves.CombatMove;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.69
 * @version 0.3.4.5
 * @author Innoxia
 */
public class Response {
	
	public static final int DEFAULT_TIME_PASSED_VALUE = Integer.MIN_VALUE;
	
	protected String title;
	protected String tooltipText;
	protected DialogueNode nextDialogue;
	
	protected List<Fetish> fetishesRequired;
	protected CorruptionLevel corruptionBypass;
	private List<AbstractPerk> perksRequired;
	private Femininity femininityRequired;
	private Race raceRequired;

	private CombatMove combatMove;
	
	// Sex action variables:
	
	private SexActionType sexActionType;
	
	private GameCharacter characterPerformingSexAction;
	private List<SexAreaInterface> sexAreaAccessRequiredForPerformer;

	private GameCharacter characterTargetedForSexAction;
	private List<SexAreaInterface> sexAreaAccessRequiredForTargeted;
	
	public Response(String title,
			String tooltipText,
			DialogueNode nextDialogue) {
		
		this(title, tooltipText, nextDialogue,
				null, null,
				null, null, null);
	}
	
	public Response(String title,
			String tooltipText,
			DialogueNode nextDialogue,
			List<Fetish> fetishesForUnlock,
			CorruptionLevel corruptionBypass,
			List<AbstractPerk> perksRequired,
			Femininity femininityRequired,
			Race raceRequired) {
		
		this(title, tooltipText, nextDialogue,
				fetishesForUnlock, corruptionBypass,
				perksRequired, femininityRequired, raceRequired,
				null, null, null, null, null);
	}
	
	public Response(String title,
			String tooltipText,
			DialogueNode nextDialogue, 
			List<Fetish> fetishesForUnlock,
			CorruptionLevel corruptionBypass,
			List<AbstractPerk> perksRequired,
			Femininity femininityRequired,
			Race raceRequired,
			SexActionType sexActionType,
			GameCharacter characterPenetrating,
			Collection<SexAreaInterface> sexAreaAccessRequiredForPerformer,
			GameCharacter characterPenetrated,
			Collection<SexAreaInterface> sexAreaAccessRequiredForTargeted) {
		
		this.title = UtilText.parse(title);
		this.tooltipText = UtilText.parse(tooltipText);
		this.nextDialogue = nextDialogue;
		
		this.fetishesRequired = fetishesForUnlock;
		this.corruptionBypass = corruptionBypass;
		this.perksRequired = perksRequired;
		this.femininityRequired = femininityRequired;
		this.raceRequired = raceRequired;
		
		combatMove = null;
		
		// Sex action variables:
		
		this.sexActionType = sexActionType;
		
		this.sexAreaAccessRequiredForPerformer = new ArrayList<>();
		if(sexAreaAccessRequiredForPerformer!=null) {
			this.sexAreaAccessRequiredForPerformer.addAll(sexAreaAccessRequiredForPerformer);
		}

		this.sexAreaAccessRequiredForTargeted = new ArrayList<>();
		if(sexAreaAccessRequiredForTargeted!=null) {
			this.sexAreaAccessRequiredForTargeted.addAll(sexAreaAccessRequiredForTargeted);
		}

		this.characterPerformingSexAction=characterPenetrating;
		this.characterTargetedForSexAction=characterPenetrated;
	}

	public String getTitle() {
		return title;
	}

	public String getTooltipText() {
		return tooltipText;
	}

	public DialogueNode getNextDialogue() {
		if(isAvailable() || isAbleToBypass()) {
			return nextDialogue;
		} else {
			return null;
		}
	}

	/**
	 * When this returns a value other than DEFAULT_TIME_PASSED_VALUE, then it overrides the next DialogueNode's getSecondsPassed method, and is therefore used to determine how much time passes when selecting this Response.
	 * 
	 * @return The number of seconds that pass when choosing this response.
	 */
	public int getSecondsPassed() {
		return DEFAULT_TIME_PASSED_VALUE;
	}
	
	public boolean disabledOnNullDialogue(){
		return true;
	}
	
	public boolean isSexHighlight() {
		return false;
	}
	
	public boolean isSexPenetrationHighlight() {
		return false;
	}
	
	public boolean isSexPositioningHighlight() {
		return false;
	}
	
	public boolean isCombatHighlight() {
		return false;
	}
	
	public boolean isCorruptionHighlight() {
		return false;
	}
	
	public boolean isTradeHighlight() {
		return false;
	}
	
	public CombatMove getAssociatedCombatMove() {
		return combatMove;
	}
	
	public Colour getHighlightColour() {
		if(isSexHighlight()) {
			return PresetColour.GENERIC_SEX;
			
		} else if(isSexPenetrationHighlight()) {
			return PresetColour.GENERIC_SEX;
			
		} else if(isSexPositioningHighlight()) {
			return PresetColour.GENERIC_ARCANE;
			
		} else if(isCombatHighlight()) {
			return PresetColour.GENERIC_COMBAT;
			
		} else if(isCorruptionHighlight()) {
			return PresetColour.ATTRIBUTE_CORRUPTION;
			
		} else if(isTradeHighlight()) {
			return PresetColour.BASE_YELLOW_LIGHT;
			
		} else {
			return PresetColour.TEXT;
		}
	}
	
	public SexPace getSexPace() {
		return null;
	}
	
	public SexActionType getSexActionType() {
		return null;
	}

	/**
	 * @return true if this response is generated from a SexAction which applies a START_ONGOING action while the related body parts are already in use (thus switching them).
	 */
	public boolean isSexActionSwitch() {
		return false;
	}
	/**
	 * @return Typically null, unless this method is overridden in order to set special requirements related to the availability of a sex action of type START_ADDITIONAL_ONGOING.
	 *  The keys correspond descriptions of requirements, while the value is used to determine if this requirement is met.
	 */
	public Map<String, Boolean> getAdditionalOngoingAvailableMap() {
		return null;
	}
	
	/**
	 * @return true if all values in the getAdditionalOngoingAvailableMap() are true.
	 */
	private boolean isAvailableFromAdditionalOngoingAvailableMap() {
		return getAdditionalOngoingAvailableMap()!=null && !getAdditionalOngoingAvailableMap().values().contains(false);
	}

	public final void applyEffects() {
		effects();
	}

	public void effects() {
	}
	
	/**
	 * These are the effects to be applied after the endTurn() has been called. (So effects in here happen after time has passed in the new scene.)
	 * <br/><b>Note:</b> endTurn(0) is called after this, so that effects are refreshed based on what happens within this method.
	 * @return true if effects were applied.
	 */
	public boolean postEndTurnEffects() {
		return false;
	}
	
	/**
	 * @return true if this response has any related requirements in order for it to be selected.
	 */
	public boolean hasRequirements() {
		return fetishesRequired != null
				|| corruptionBypass != null
				|| perksRequired != null
				|| femininityRequired != null
				|| raceRequired != null
				|| sexActionType==SexActionType.SPEECH
				|| getAdditionalOngoingAvailableMap()!=null
				|| !sexAreaAccessRequiredForPerformer.isEmpty()
				|| !sexAreaAccessRequiredForTargeted.isEmpty();
	}
	
	/**
	 * @return true if this action has no requirements, or if all requirements are met.
	 */
	public boolean isAvailable(){
		if(!hasRequirements()) {
			return true;
		}
		if(sexActionType!=null && !Main.game.isBypassSexActions() && !isCorruptionWithinRange() && !isAvailableFromFetishes()) {
			return false;
		}
		
		boolean corruptionOrFetishReqs = false;
		if(sexActionType!=null) {
			corruptionOrFetishReqs = isCorruptionWithinRange() || isAvailableFromFetishes() || (corruptionBypass==null && fetishesRequired==null);
		} else {
			if(corruptionBypass==null) {
				corruptionOrFetishReqs = isAvailableFromFetishes() || fetishesRequired==null;
			} else {
				corruptionOrFetishReqs = isCorruptionWithinRange() || isAvailableFromFetishes();
			}
		}
		
		return corruptionOrFetishReqs
					&& !isBlockedFromPerks()
					&& isFemininityInRange()
					&& isRequiredRace()
					&& (sexActionType!=SexActionType.SPEECH || !Main.sex.isOngoingActionsBlockingSpeech(Main.game.getPlayer()))
					&& (isAvailableFromAdditionalOngoingAvailableMap() || (isPenetrationTypeAvailable() && isOrificeTypeAvailable()));
	}
	
	/**
	 * @return true if this action is not available from the requirements, and is instead available due to being able to bypass the corruption requirements.
	 */
	public boolean isAbleToBypass(){
		if(!isAvailable()
				&& (!Main.game.isInSex() || Main.game.isBypassSexActions())
				&& !isBlockedFromPerks()
				&& isFemininityInRange()
				&& isRequiredRace()
				&& !isAvailableFromFetishes()
				&& (isAvailableFromAdditionalOngoingAvailableMap() || (isPenetrationTypeAvailable() && isOrificeTypeAvailable()))) {
			if(!Main.game.isInSex() && corruptionBypass==null) { // Do not allow bypass out of sex if there is no corruption bypassing
				return false;
			}
			return !isCorruptionWithinRange();
		}
		
		return false;
	}

	private StringBuilder SB;
	public String getTooltipCorruptionBypassText() {
		SB = new StringBuilder();
		
		if(!isAvailable() && !isAbleToBypass()) {
			if(!Main.game.isBypassSexActions() && !isCorruptionWithinRange()) {
				SB.append("This action is blocked as [style.colourTerrible(you are not corrupt enough)] to think of performing it.");
				
			} else {
				SB.append("This action is blocked due to not meeting certain [style.colourBad(requirements)].");
			}
			
		} else {
			if(isAvailableFromFetishes()) {
				SB.append("Your [style.colourFetish(fetish)] bypasses this action's [style.colourCorruption(corruption)] requirements!");
				return SB.toString();
			}
			
			if(corruptionBypass != null) {
				if(isCorruptionWithinRange()) {
					SB.append("Your <span style='color:"+Main.game.getPlayer().getCorruptionLevel().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(Main.game.getPlayer().getCorruptionLevel().getName())+"</span>"
							+ " [style.colourCorruption(corruption)] has unlocked this action!");
				} else {
					SB.append("You will gain <b>+"+corruptionBypass.getCorruptionBypass()+"</b> [style.boldCorruption(corruption)], as you don't meet the [style.colourCorruption(corruption)] or [style.colourFetish(fetish)] requirements!");
				}
			} else {
				SB.append("This action cannot be unlocked with [style.colourCorruption(corruption)].");
			}
		}
		
		return SB.toString();
	}
	
	public String getAdditionalSexActionInformationText() {
		if(this.getSexActionType()==SexActionType.START_ADDITIONAL_ONGOING) {
			return "This action will cause you to [style.colourSex(join in)] with the related ongoing action.";
			
		} else if(isSwitchOngoingActionAvailable()) {
			return "This action will cause [style.colourCorruption(some ongoing actions to be stopped)] before starting the related ongoing action.";
		}
		return "";
	}
	
	private boolean isSwitchOngoingActionAvailable() {
		if(this.sexActionType ==SexActionType.START_ONGOING
				&& Main.sex.getCharacterPerformingAction().isPlayer()
				&& Main.sex.getSexControl(characterPerformingSexAction).getValue()>=SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS.getValue()) {
//			if(Main.sex.getCharactersHavingOngoingActionWith(characterTargetedForSexAction, this.sexAreaAccessRequiredForTargeted.get(0)).size()>1
//					|| (!Main.sex.getCharactersHavingOngoingActionWith(characterTargetedForSexAction, this.sexAreaAccessRequiredForTargeted.get(0)).contains(characterTargetedForSexAction)
//							&& !Main.sex.getCharactersHavingOngoingActionWith(characterTargetedForSexAction, this.sexAreaAccessRequiredForTargeted.get(0)).contains(Main.game.getPlayer()))) {
//				return false;
//			}
			List<GameCharacter> ongoingTargetedAreaCharacters = Main.sex.getCharactersHavingOngoingActionWith(characterTargetedForSexAction, this.sexAreaAccessRequiredForTargeted.get(0));
			List<GameCharacter> ongoingPerformingAreaCharacters = Main.sex.getCharactersHavingOngoingActionWith(characterPerformingSexAction, this.sexAreaAccessRequiredForPerformer.get(0));
			
			// If targeted area is having multiple ongoing actions, or non-self actions that do not involve the player do not allow switch:
			if(ongoingTargetedAreaCharacters.size()>1 || ongoingPerformingAreaCharacters.size()>1) {
				return false;
			}
			if(!ongoingTargetedAreaCharacters.isEmpty()
					&& !ongoingTargetedAreaCharacters.contains(characterTargetedForSexAction)
					&& !ongoingTargetedAreaCharacters.contains(Main.game.getPlayer())) {
				return false;
			}
			
			try {
				return !Main.sex.getOngoingActionsMap(characterPerformingSexAction).get(this.sexAreaAccessRequiredForPerformer.get(0)).get(characterTargetedForSexAction).contains(this.sexAreaAccessRequiredForTargeted.get(0));
			} catch(Exception ex) {
				return true;
			}
		} else {
			return false;
		}
	}
	
	public String getTooltipBlockingList(){
		SB = new StringBuilder();
		
		if(perksRequired!=null) {
			for(AbstractPerk p : perksRequired){
				if(Main.game.getPlayer().hasTrait(p, true)) {
					SB.append("<br/>"
							+"<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b>"
							+ " (<span style='color:"+PresetColour.PERK.toWebHexString()+";'>Perk</span>): "
							+Util.capitaliseSentence(p.getName(Main.game.getPlayer())));
				} else {
					SB.append("<br/>"
							+"<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Requirement</b>"
							+ " (<span style='color:"+PresetColour.PERK.toWebHexString()+";'>Perk</span>): "
							+Util.capitaliseSentence(p.getName(Main.game.getPlayer())));
				}
			}
		}
		
		if(femininityRequired!=null) {
			if(isFemininityInRange()) {
				SB.append("<br/>"
						+"<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b>"
						+ " (Femininity): "
						+ "<span style='color:"+femininityRequired.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(femininityRequired.getName(false))+"</span>");
			} else {
				SB.append("<br/>"
						+"<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Requirement</b>"
						+ " (Femininity): "
						+ "<span style='color:"+femininityRequired.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(femininityRequired.getName(false))+"</span>");
			}
		}
		
		if(raceRequired!=null) {
			if(isRequiredRace()) {
				SB.append("<br/>"
						+"<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b>"
						+ " (Race): "
						+"<span style='color:"+raceRequired.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(raceRequired.getName(false))+"</span>");
			} else {
				SB.append("<br/>"
						+"<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Requirement</b>"
						+ " (Race): "
						+"<span style='color:"+raceRequired.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(raceRequired.getName(false))+"</span>");
			}
		}
		
		if(sexActionType==SexActionType.SPEECH) {
			if(!Main.sex.isOngoingActionsBlockingSpeech(Main.game.getPlayer())) {
				SB.append("<br/>"
						+"<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b>"
						+ " (Speech): [style.colourMinorGood(Unblocked mouth)]");
			} else {
				SB.append("<br/>"
						+"<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Requirement</b>"
						+ " (Speech): [style.colourMinorBad(Unblocked mouth)]");
			}
		}
		
		if(getAdditionalOngoingAvailableMap()!=null) {
			for(Entry<String, Boolean> e : getAdditionalOngoingAvailableMap().entrySet()) {
				if(e.getValue()) {
					SB.append("<br/>[style.colourGood("+Util.capitaliseSentence(UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()), e.getKey()))+")]");
				} else {
					SB.append("<br/>[style.colourBad("+Util.capitaliseSentence(UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()), e.getKey()))+")]");
				}
			}
			
		} else {
			if(sexAreaAccessRequiredForPerformer!=null && characterPerformingSexAction!=null) {
				boolean penetrationAccess = true;
				for(SexAreaInterface sArea : this.sexAreaAccessRequiredForPerformer) {
					if(sArea!=null && (!characterPerformingSexAction.isSexAreaExposed(sArea) || (getSexActionType()==SexActionType.REQUIRES_NO_PENETRATION && characterPerformingSexAction.isCoverableAreaBlockedFromGroping(sArea.getRelatedCoverableArea())))) {
						penetrationAccess = false;
					}
				}
				boolean penetrationFree = true;
				if(!isSwitchOngoingActionAvailable()) {
					for(SexAreaInterface sArea : this.sexAreaAccessRequiredForPerformer) {
						if(sArea!=null && !sArea.isFree(characterPerformingSexAction)) {
							penetrationFree = false;
						}
					}
				}
				
				for(SexAreaInterface sArea : sexAreaAccessRequiredForPerformer) {
					if(sArea!=null) {
						String penetrationName = Util.capitaliseSentence(sArea.getName(characterPerformingSexAction));
						String accessText = (penetrationAccess?"access":"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>access</span>");
						String freeText = (penetrationFree?"free":"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>free</span>");
						String targetName = (characterPerformingSexAction.isPlayer()?"Your":UtilText.parse(characterPerformingSexAction, "[npc.Name]'s"));
						
						if(getSexActionType()==SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED
								|| getSexActionType()==SexActionType.START_ONGOING
								|| getSexActionType()==SexActionType.REQUIRES_NO_PENETRATION) {
							if(penetrationAccess && penetrationFree) {
								SB.append("<br/><b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b> ("+accessText+" & "+freeText+"): "+targetName+" "+ penetrationName);
							} else {
								SB.append("<br/><b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Requirement</b> ("+accessText+" & "+freeText+"): "+targetName+" "+ penetrationName);
							}
							
						} else if(getSexActionType()==SexActionType.REQUIRES_EXPOSED) {
							if(penetrationAccess) {
								SB.append("<br/><b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b> ("+accessText+"): "+targetName+" "+ penetrationName);
							} else {
								SB.append("<br/><b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Requirement</b> ("+accessText+"): "+targetName+" "+ penetrationName);
							}
							
						} else {
							if(penetrationAccess) {
								SB.append("<br/><b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b> ("+accessText+"): "+targetName+" "+ penetrationName);
							} else {
								SB.append("<br/><b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Requirement</b> ("+accessText+"): "+targetName+" "+ penetrationName);
							}
						}
					}
				}
			}
			
			if(sexAreaAccessRequiredForTargeted!=null && characterTargetedForSexAction!=null) {
				boolean orificeAccess = true;
				for(SexAreaInterface sArea : this.sexAreaAccessRequiredForTargeted) {
					if(sArea!=null && (!characterTargetedForSexAction.isSexAreaExposed(sArea) || (getSexActionType()==SexActionType.REQUIRES_NO_PENETRATION && characterTargetedForSexAction.isCoverableAreaBlockedFromGroping(sArea.getRelatedCoverableArea())))) {
						orificeAccess = false;
					}
				}
				boolean orificeFree = true;
				if(!isSwitchOngoingActionAvailable()) {
					for(SexAreaInterface sArea : this.sexAreaAccessRequiredForTargeted) {
						if(sArea!=null && !sArea.isFree(characterTargetedForSexAction)) {
							orificeFree = false;
						}
					}
				}
	
				for(SexAreaInterface sArea : sexAreaAccessRequiredForTargeted) {
					if(sArea!=null) {
						String orificeName = Util.capitaliseSentence(sArea.getName(characterTargetedForSexAction));
						String accessText = (orificeAccess?"access":"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>access</span>");
						String freeText = (orificeFree?"free":"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>free</span>");
						String targetName = (characterTargetedForSexAction.isPlayer()?"Your":UtilText.parse(characterTargetedForSexAction, "[npc.Name]'s"));
						
						if(getSexActionType()==SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED
								|| getSexActionType()==SexActionType.START_ONGOING
								|| getSexActionType()==SexActionType.REQUIRES_NO_PENETRATION) {
							if(orificeAccess && orificeFree) {
								SB.append("<br/><b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b> ("+accessText+" & "+freeText+"): "+targetName+" "+ orificeName);
							} else {
								SB.append("<br/><b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Requirement</b> ("+accessText+" & "+freeText+"): "+targetName+" "+ orificeName);
							}
							
						} else if(getSexActionType()==SexActionType.REQUIRES_EXPOSED) {
							if(orificeAccess) {
								SB.append("<br/><b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b> ("+accessText+"): "+targetName+" "+ orificeName);
							} else {
								SB.append("<br/><b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Requirement</b> ("+accessText+"): "+targetName+" "+ orificeName);
							}
							
						} else {
							if(orificeAccess) {
								SB.append("<br/><b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b> ("+accessText+"): "+targetName+" "+ orificeName);
							} else {
								SB.append("<br/><b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Requirement</b> ("+accessText+"): "+targetName+" "+ orificeName);
							}
						}
					}
				}
			}
		}
		
		return SB.toString();
	}
	
	public String getTooltipRequiredList(){
		SB = new StringBuilder();
		
		if(fetishesRequired!=null) {
			for(Fetish f : fetishesRequired){
				if(Main.game.getPlayer().hasFetish(f)) {
					SB.append("<br/>"
							+"[style.colourFetish(Associated Fetish)]"
							+ " (<span style='color:"+PresetColour.GENERIC_MINOR_GOOD.toWebHexString()+";'>owned</span>): "
							+ Util.capitaliseSentence(f.getName(Main.game.getPlayer())));
					
				} else {
					SB.append("<br/>"
							+"[style.colourFetish(Associated Fetish)]"
							+ " (<span style='color:"+PresetColour.GENERIC_MINOR_BAD.toWebHexString()+";'>not owned</span>): "
							+ Util.capitaliseSentence(f.getName(Main.game.getPlayer())));
				}
			}
		}
		
		if(corruptionBypass!=null) {
			if(isCorruptionWithinRange()) {
				SB.append("<br/>"
						+"[style.colourCorruption(Associated Corruption)]"
						+ " ([style.colourMinorGood(within range)]): "
						+ Util.capitaliseSentence(corruptionBypass.getName()));
			} else {
				SB.append("<br/>"
						+"[style.colourCorruption(Associated Corruption)]"
						+ (!Main.game.isBypassSexActions()
								?" ([style.colourTerrible(out of range)]): "
								:" ([style.colourMinorBad(out of range)]): ")
						+ Util.capitaliseSentence(corruptionBypass.getName()));
			}
		}
		
		return SB.toString();
	}
	
	public int lineHeight(){
		int lineHeight = 0;
		
		if(perksRequired!=null) {
			lineHeight+=perksRequired.size();
		}
		if(femininityRequired!=null) {
			lineHeight++;
		}
		if(raceRequired!=null) {
			lineHeight++;
		}
		if(sexActionType==SexActionType.SPEECH) {
			lineHeight++;
		}
		
		if(fetishesRequired!=null) {
			lineHeight+=fetishesRequired.size();
		}
		if(corruptionBypass!=null) {
			lineHeight++;
		}
		
		if(getAdditionalOngoingAvailableMap()!=null) {
			lineHeight+=getAdditionalOngoingAvailableMap().size();
		}
		if(sexAreaAccessRequiredForPerformer!=null) {
			lineHeight ++;
		}
		if(sexAreaAccessRequiredForTargeted!=null) {
			lineHeight ++;
		}
		
		return lineHeight;
	}

	public boolean isCorruptionWithinRange() {
		return corruptionBypass!=null && corruptionBypass.getMinimumValue() <= Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_CORRUPTION);
	}
	
	public boolean isAvailableFromFetishes() {
		if(fetishesRequired==null) {
			return false;
		}
		
		for (Fetish f : fetishesRequired) {
			if(Main.game.getPlayer().hasFetish(f)) {
				if(f==Fetish.FETISH_PURE_VIRGIN) {
					if(Main.game.getPlayer().hasStatusEffect(StatusEffect.FETISH_PURE_VIRGIN)) { // Virginity fetish only blocks if player is still a virgin.
						return true;
					}
				} else {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isBlockedFromPerks() {
		if(perksRequired==null) {
			return false;
		}
		for (AbstractPerk p : perksRequired) {
			if(!Main.game.getPlayer().hasPerkAnywhereInTree(p)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isFemininityInRange() {
		if(femininityRequired==null) {
			return true;
		}
		
		switch(femininityRequired){
			case ANDROGYNOUS:
				return Femininity.valueOf(Main.game.getPlayer().getFemininityValue()) == Femininity.ANDROGYNOUS;
			case FEMININE:
			case FEMININE_STRONG:
				return Main.game.getPlayer().getFemininityValue() >= femininityRequired.getMinimumFemininity();
			case MASCULINE:
			case MASCULINE_STRONG:
				return Main.game.getPlayer().getFemininityValue() <= femininityRequired.getMaximumFemininity();
			default:
				return true;
		}
	}
	
	public boolean isRequiredRace() {
		return raceRequired == null || Main.game.getPlayer().getRace() == raceRequired;
	}
	
	public boolean isPenetrationTypeAvailable() {
		if(sexAreaAccessRequiredForPerformer==null || sexAreaAccessRequiredForPerformer.isEmpty()) {
			return true;
		}
		
		// Don't care if exposed or not:
		if(getSexActionType()!=null) {
			switch(getSexActionType()){
				case REQUIRES_NO_PENETRATION:
					for(SexAreaInterface sArea : sexAreaAccessRequiredForPerformer) {
						if(sArea!=null && (!sArea.isFree(characterPerformingSexAction) || characterPerformingSexAction.isCoverableAreaBlockedFromGroping(sArea.getRelatedCoverableArea()))) {
							return false;
						}
					}
					return true;
				case START_ONGOING:
					// Allow characters who have control to switch from one ongoing penetration to another
					if(!isSwitchOngoingActionAvailable()) {
						for(SexAreaInterface sArea : sexAreaAccessRequiredForPerformer) {
							if(sArea!=null && !sArea.isFree(characterPerformingSexAction)) {
								return false;
							}
						}
					}
					break;
				default:
					break;
			}
		}
		
		// Check to make sure penetrationType is exposed:
		for(SexAreaInterface sArea : sexAreaAccessRequiredForPerformer) {
			if(!characterPerformingSexAction.isSexAreaExposed(sArea)) {
				return false;
			}
		}
		
		if(getSexActionType()!=null) {
			switch(getSexActionType()){
				case REQUIRES_NO_PENETRATION_AND_EXPOSED:
					for(SexAreaInterface sArea : sexAreaAccessRequiredForPerformer) {
						if(sArea!=null && !sArea.isFree(characterPerformingSexAction)) {
							return false;
						}
					}
					break;
				default:
					break;
			}
		}
		
		return true;
	}
	
	public boolean isOrificeTypeAvailable() {
		if(sexAreaAccessRequiredForTargeted==null || sexAreaAccessRequiredForTargeted.isEmpty()) {
			return true;
		}
		
		// Don't care if exposed or not:
		if(getSexActionType()!=null) {
			switch(getSexActionType()){
				case REQUIRES_NO_PENETRATION:
					for(SexAreaInterface sArea : sexAreaAccessRequiredForTargeted) {
						if(sArea!=null && (!sArea.isFree(characterTargetedForSexAction) || characterTargetedForSexAction.isCoverableAreaBlockedFromGroping(sArea.getRelatedCoverableArea()))) {
							return false;
						}
					}
					return true;
				case START_ONGOING:
					// Allow characters who have control to switch from one ongoing penetration to another
					if(!isSwitchOngoingActionAvailable()) {
						for(SexAreaInterface sArea : sexAreaAccessRequiredForTargeted) {
							if(sArea!=null && !sArea.isFree(characterTargetedForSexAction)) {
								return false;
							}
						}
					}
					break;
				default:
					break;
			}
		}
		
		// Check to make sure penetrationType is exposed:
		for(SexAreaInterface sArea : sexAreaAccessRequiredForTargeted) {
			if(!characterTargetedForSexAction.isSexAreaExposed(sArea)) {
				return false;
			}
		}
		
		if(getSexActionType()!=null) {
			switch(getSexActionType()){
				case REQUIRES_NO_PENETRATION_AND_EXPOSED:
					for(SexAreaInterface sArea : sexAreaAccessRequiredForTargeted) {
						if(sArea!=null && !sArea.isFree(characterTargetedForSexAction)) {
							return false;
						}
					}
					break;
				default:
					break;
			}
		}
		
		return true;
	}

	public List<Fetish> getFetishesForUnlock() {
		return fetishesRequired;
	}

	public CorruptionLevel getCorruptionNeeded() {
		return corruptionBypass;
	}

	public List<AbstractPerk> getPerksRequired() {
		return perksRequired;
	}

	public Femininity getFemininityRequired() {
		return femininityRequired;
	}

	public Race getRaceRequired() {
		return raceRequired;
	}

	public static Response getDisallowedSpittingResponse() {
		return getDisallowedSpittingResponse("Spit");
	}

	public static Response getDisallowedSpittingResponse(String desc) {
		return new Response(desc, "You are being forced to drink down the potion!<br/>[style.italicsBad(Rejection of TF potions is disabled in the gameplay options!)]", null);
	}
}
