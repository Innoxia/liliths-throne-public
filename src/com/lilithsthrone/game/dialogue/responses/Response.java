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
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.moves.AbstractCombatMove;
import com.lilithsthrone.game.dialogue.DialogueManager;
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
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.69
 * @version 0.3.9.1
 * @author Innoxia
 */
public class Response {
	
	public static final int DEFAULT_TIME_PASSED_VALUE = Integer.MIN_VALUE;
	
	protected String title;
	protected String tooltipText;
	protected DialogueNode nextDialogue;
	
	protected List<AbstractFetish> fetishesRequired;
	protected CorruptionLevel corruptionBypass;
	private List<AbstractPerk> perksRequired;
	private Femininity femininityRequired;
	private List<AbstractSubspecies> subspeciesRequired;

	private AbstractCombatMove combatMove;
	
	// Sex action variables:
	
	private SexActionType sexActionType;
	
	private GameCharacter characterPerformingSexAction;
	private List<SexAreaInterface> sexAreaAccessRequiredForPerformer;

	private GameCharacter characterTargetedForSexAction;
	private List<SexAreaInterface> sexAreaAccessRequiredForTargeted;

	protected boolean stripContent = false;
	protected boolean forceContinue = false; // Forces the next dialogue node to act as though isContinuesDialogue() is true
	
	// For use when loaded from external files
	
	protected boolean fromExternalFile = false;
	
	private String conditional;
	
	private String colourId;
	private String secondsPassedString;
	private boolean asMinutes;

	protected String nextDialogueId;

	protected String effectsString;
	
	private List<String> fetishesRequiredId;
	private String corruptionBypassId;
	private List<String> perksRequiredId;
	private String femininityRequiredId;
	private List<String> subspeciesRequiredId;
	
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
			List<AbstractFetish> fetishesForUnlock,
			CorruptionLevel corruptionBypass,
			List<AbstractPerk> perksRequired,
			Femininity femininityRequired,
			List<AbstractSubspecies> subspeciesRequired) {
		this(title, tooltipText, nextDialogue,
				fetishesForUnlock, corruptionBypass,
				perksRequired, femininityRequired, subspeciesRequired,
				null, null, null, null, null);
	}
	
	public Response(String title,
			String tooltipText,
			DialogueNode nextDialogue, 
			List<AbstractFetish> fetishesForUnlock,
			CorruptionLevel corruptionBypass,
			List<AbstractPerk> perksRequired,
			Femininity femininityRequired,
			List<AbstractSubspecies> subspeciesRequired,
			SexActionType sexActionType,
			GameCharacter characterPenetrating,
			Collection<SexAreaInterface> sexAreaAccessRequiredForPerformer,
			GameCharacter characterPenetrated,
			Collection<SexAreaInterface> sexAreaAccessRequiredForTargeted) {
		
		this.title = (title);
		this.tooltipText = (tooltipText);
		this.nextDialogue = nextDialogue;
		
		this.fetishesRequired = fetishesForUnlock;
		this.corruptionBypass = corruptionBypass;
		this.perksRequired = perksRequired;
		this.femininityRequired = femininityRequired;
		this.subspeciesRequired = subspeciesRequired;
		
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
		
		this.conditional = "";
	}
	
	public Response(String title,
			String tooltipText,
			String nextDialogueId,
			String secondsPassedString,
			boolean asMinutes,
			String colourId,
			String effectsString,
			List<String> fetishesForUnlockId,
			String corruptionBypassId,
			List<String> perksRequiredId,
			String femininityRequiredId,
			List<String> subspeciesRequiredId) {
		
		this.fromExternalFile = true;
		
		this.title = title;
		this.tooltipText = tooltipText;
		this.nextDialogueId = nextDialogueId;

		this.secondsPassedString = secondsPassedString;
		this.asMinutes = asMinutes;
		
		this.colourId = colourId;
		
		this.effectsString = effectsString;

		resetCalculatedVariables();
		
		this.fetishesRequiredId = fetishesForUnlockId;
		this.corruptionBypassId = corruptionBypassId;
		this.perksRequiredId = perksRequiredId;
		this.femininityRequiredId = femininityRequiredId;
		this.subspeciesRequiredId = subspeciesRequiredId;
		
		this.conditional = "";
		
		
		// Init other variables not used in this:
		
		this.sexAreaAccessRequiredForPerformer = new ArrayList<>();
		this.sexAreaAccessRequiredForTargeted = new ArrayList<>();
	}
	
	private void resetCalculatedVariables() {
		this.fetishesRequired = null;
		this.corruptionBypass = null;
		this.perksRequired = null;
		this.femininityRequired = null;
		this.subspeciesRequired = null;
	}

	public String getTitle() {
//		if(fromExternalFile) {
			return UtilText.parse(title).trim();
//		}
//		return title;
	}

	public String getTooltipText() {
//		if(fromExternalFile) {
			return UtilText.parse(tooltipText).trim();
//		}
//		return tooltipText;
	}

	public DialogueNode getNextDialogue() {
		if(isAvailable() || isAbleToBypass()) {
			if(getDefaultPlaceTypeForNextDialogue()!=null && !getDefaultPlaceTypeForNextDialogue().isEmpty()) {
//				System.out.println("getNextDialogue() place type return");
				return PlaceType.getPlaceTypeFromId(getDefaultPlaceTypeForNextDialogue()).getDialogue(false);
			}
			if(fromExternalFile && nextDialogueId!=null) {
				DialogueNode dn = DialogueManager.getDialogueFromId(UtilText.parse(nextDialogueId).trim());
//				System.out.println("getNextDialogue(): "+(dn==null?"null":dn.getId()));
				return dn;
			}
//			System.out.println("getNextDialogue() (no external): "+(nextDialogue==null?"null":nextDialogue.getId()));
			return nextDialogue;
			
		} else {
//			System.out.println("getNextDialogue(): null");
			return null;
		}
	}
	
	/**
	 * @return A non-empty String, representing a PlaceType id, if this Response should link to the default dialogue for that PlaceType in getNextDialogue()
	 */
	public String getDefaultPlaceTypeForNextDialogue() {
		return null;
	}

	/**
	 * When this returns a value other than DEFAULT_TIME_PASSED_VALUE, then it overrides the next DialogueNode's getSecondsPassed method, and is therefore used to determine how much time passes when selecting this Response.
	 * 
	 * @return The number of seconds that pass when choosing this response.
	 */
	public int getSecondsPassed() {
		if(fromExternalFile && secondsPassedString!=null && !secondsPassedString.isEmpty()) {
			return Integer.valueOf(UtilText.parse(secondsPassedString).trim()) * (asMinutes?60:1);
		}
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
	
	public AbstractCombatMove getAssociatedCombatMove() {
		return combatMove;
	}
	
	public Colour getHighlightColour() {
		if(fromExternalFile && colourId!=null && !colourId.isEmpty()) {
			if(colourId.startsWith("#")) {
				return new Colour(false, Util.newColour(colourId), Util.newColour(colourId), "");
			} else {
				String colourParsed = UtilText.parse(colourId).trim();
				if(!colourParsed.isEmpty()) {
					return PresetColour.getColourFromId(colourParsed);
				}
			}
		}
		
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

	/**
	 * @return true if the next dialogue node should not display its default content.
	 */
	public boolean isStripContent() {
		return stripContent;
	}
	
	public void setStripContent(boolean stripContent) {
		this.stripContent = stripContent;
	}
	
	/**
	 * @return true if the next dialogue node should behave as though isContinuesDialogue() returns true.
	 */
	public boolean isForceContinue() {
		return forceContinue;
	}
	
	public void setForceContinue(boolean forceContinue) {
		this.forceContinue = forceContinue;
	}
	
	public final void applyEffects() {
		effects();
	}

	public void effects() {
		if(fromExternalFile && effectsString!=null && !effectsString.isEmpty()) {
			UtilText.parse(effectsString);
			resetCalculatedVariables(); // Reset values here so that they are re-calculated if this response is encountered again
		}
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
		return getFetishesForUnlock() != null
				|| getCorruptionNeeded() != null
				|| getPerksRequired() != null
				|| getFemininityRequired() != null
				|| getSubspeciesRequired() != null
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
		if(sexActionType!=null && !Main.game.isBypassSexActionsEnabled() && !isCorruptionWithinRange() && !isAvailableFromFetishes()) {
			return false;
		}
		if(getConditional()!=null
				&& getConditional().isEmpty()
				&& !getConditional().equalsIgnoreCase("true")
				&& !isAvailableFromConditional()) {
			return false;
		}
		
		boolean corruptionOrFetishReqs = false;
		if(sexActionType!=null) {
			corruptionOrFetishReqs = isCorruptionWithinRange() || isAvailableFromFetishes() || (getCorruptionNeeded()==null && getFetishesForUnlock()==null);
		} else {
			if(getCorruptionNeeded()==null) {
				corruptionOrFetishReqs = isAvailableFromFetishes() || getFetishesForUnlock()==null;
			} else {
				corruptionOrFetishReqs = isCorruptionWithinRange() || isAvailableFromFetishes();
			}
		}
		
		return corruptionOrFetishReqs
					&& !isBlockedFromPerks()
					&& isFemininityInRange()
					&& isRequiredSubspecies()
					&& (sexActionType!=SexActionType.SPEECH || !Main.sex.isOngoingActionsBlockingSpeech(Main.game.getPlayer()))
					&& (isAvailableFromAdditionalOngoingAvailableMap() || (isPenetrationTypeAvailable() && isOrificeTypeAvailable()));
	}
	
	/**
	 * @return true if this action is not available from the requirements, and is instead available due to being able to bypass the corruption requirements.
	 */
	public boolean isAbleToBypass(){
		if(!isAvailable()
				&& (!Main.game.isInSex() || Main.game.isBypassSexActionsEnabled())
				&& !isBlockedFromPerks()
				&& isFemininityInRange()
				&& isRequiredSubspecies()
				&& !isAvailableFromFetishes()
				&& (isAvailableFromAdditionalOngoingAvailableMap() || (isPenetrationTypeAvailable() && isOrificeTypeAvailable()))) {
			if(!Main.game.isInSex() && getCorruptionNeeded()==null) { // Do not allow bypass out of sex if there is no corruption bypassing
				return false;
			}
			return ((Main.getProperties().bypassSexActions==2 && !isCorruptionWithinRange()) ||
					(Main.getProperties().bypassSexActions==1 && Main.game.isInSex() && isCorruptionWithinRange() && isActionCorrupting()) ||
					(Main.getProperties().bypassSexActions<=1 && !Main.game.isInSex()));
		}
		
		return false;
	}

	private StringBuilder SB;
	public String getTooltipCorruptionBypassText() {
		SB = new StringBuilder();
		
		if(!isAvailable() && !isAbleToBypass()) {
			if(!Main.game.isBypassSexActionsEnabled() && !isCorruptionWithinRange()) {
				SB.append("This action is blocked as [style.colourTerrible(you are not corrupt enough)] to think of performing it.");
				
			} else {
				SB.append("This action is blocked due to not meeting certain [style.colourBad(requirements)].");
			}
			
		} else {
			if(isAvailableFromFetishes()) {
				SB.append("Your [style.colourFetish(fetish)] bypasses this action's [style.colourCorruption(corruption)] requirements!");
				return SB.toString();
			}
			
			if(getCorruptionNeeded() != null) {
				if(!isActionCorrupting()) {
					SB.append("Your <span style='color:"+Main.game.getPlayer().getCorruptionLevel().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(Main.game.getPlayer().getCorruptionLevel().getName())+"</span>"
							+ " [style.colourCorruption(corruption)] has unlocked this action!");
				} else {
					SB.append("You will gain <b>+"+getCorruptionNeeded().getCorruptionBypass()+"</b> [style.boldCorruption(corruption)], as you don't meet the [style.colourCorruption(corruption)] or [style.colourFetish(fetish)] requirements!");
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
			
		} else if(isSexActionSwitch()) {
			return "This action will cause [style.colourCorruption(some ongoing actions to be stopped)] before starting the related ongoing action.";
		}
		return "";
	}
	
	private boolean isSwitchOngoingActionAvailable() {
		if(this.sexActionType==SexActionType.START_ONGOING
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
		
		if(getPerksRequired()!=null) {
			for(AbstractPerk p : getPerksRequired()){
				if(p.isEquippableTrait()
						?Main.game.getPlayer().hasTrait(p, true)
						:Main.game.getPlayer().hasPerkAnywhereInTree(p)) {
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
		
		if(getFemininityRequired()!=null) {
			if(isFemininityInRange()) {
				SB.append("<br/>"
						+"<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b>"
						+ " (Femininity): "
						+ "<span style='color:"+getFemininityRequired().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(getFemininityRequired().getName(false))+"</span>");
			} else {
				SB.append("<br/>"
						+"<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Requirement</b>"
						+ " (Femininity): "
						+ "<span style='color:"+getFemininityRequired().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(getFemininityRequired().getName(false))+"</span>");
			}
		}
		
		if(getSubspeciesRequired()!=null) {
			if(isRequiredSubspecies()) {
				SB.append("<br/><b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b>");

			} else {
				SB.append("<br/><b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Requirement</b>");
			}

			SB.append(" (Subspecies): ");
			for(AbstractSubspecies subspecies : getSubspeciesRequired()) {
				SB.append("<span style='color:"+subspecies.getColour(Main.game.getPlayer()).toWebHexString()+";'>"+Util.capitaliseSentence(subspecies.getName(Main.game.getPlayer().getBody()))+"</span>");
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
					if(sArea!=null
							&& (!characterPerformingSexAction.isSexAreaExposed(sArea)
								|| (getSexActionType()==SexActionType.REQUIRES_NO_PENETRATION && characterPerformingSexAction.isCoverableAreaBlockedFromGroping(sArea.getRelatedCoverableArea(characterPerformingSexAction))))) {
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
					if(sArea!=null
							&& (!characterTargetedForSexAction.isSexAreaExposed(sArea)
									|| (getSexActionType()==SexActionType.REQUIRES_NO_PENETRATION && characterTargetedForSexAction.isCoverableAreaBlockedFromGroping(sArea.getRelatedCoverableArea(characterTargetedForSexAction))))) {
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
		
		if(getFetishesForUnlock()!=null) {
			for(AbstractFetish f : getFetishesForUnlock()){
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
		
		if(getCorruptionNeeded()!=null) {
			if(isCorruptionWithinRange()) {
				SB.append("<br/>"
						+"[style.colourCorruption(Associated Corruption)]"
						+ (!isActionCorrupting()
							?" ([style.colourMinorGood(within range)]): "
							:" ([style.colourMinorBad(just out of range)]): ")
						+ Util.capitaliseSentence(getCorruptionNeeded().getName()));
			} else {
				SB.append("<br/>"
						+"[style.colourCorruption(Associated Corruption)]"
						+ (!Main.game.isBypassSexActionsEnabled()
								?" ([style.colourTerrible(out of range)]): "
								:" ([style.colourMinorBad(out of range)]): ")
						+ Util.capitaliseSentence(getCorruptionNeeded().getName()));
			}
		}
		
		return SB.toString();
	}
	
	public int lineHeight(){
		int lineHeight = 0;
		
		if(getPerksRequired()!=null) {
			lineHeight+=getPerksRequired().size();
		}
		if(getFemininityRequired()!=null) {
			lineHeight++;
		}
		if(getSubspeciesRequired()!=null) {
			lineHeight++;
		}
		if(sexActionType==SexActionType.SPEECH) {
			lineHeight++;
		}
		
		if(getFetishesForUnlock()!=null) {
			lineHeight+=getFetishesForUnlock().size();
		}
		if(getCorruptionNeeded()!=null) {
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
		if (Main.getProperties().bypassSexActions==1) {
			return getCorruptionNeeded()!=null && Main.game.getPlayer().getCorruptionLevel().isAbleToPerformCorruptiveAction(getCorruptionNeeded());
		}
		else {
			return getCorruptionNeeded()!=null && getCorruptionNeeded().getMinimumValue() <= Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_CORRUPTION);
		}
	}

	public boolean isActionCorrupting() {
		return getCorruptionNeeded()!=null && getCorruptionNeeded().getMinimumValue() > Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_CORRUPTION);
	}
	
	public boolean isAvailableFromFetishes() {
		if(getFetishesForUnlock()==null) {
			return false;
		}
		
		for (AbstractFetish f : getFetishesForUnlock()) {
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
		if(getPerksRequired()==null) {
			return false;
		}
		for(AbstractPerk p : getPerksRequired()) {
			if(p.isEquippableTrait()
					?Main.game.getPlayer().hasTrait(p, true)
					:Main.game.getPlayer().hasPerkAnywhereInTree(p)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isFemininityInRange() {
		if(getFemininityRequired()==null) {
			return true;
		}
		
		switch(getFemininityRequired()){
			case ANDROGYNOUS:
				return Femininity.valueOf(Main.game.getPlayer().getFemininityValue()) == Femininity.ANDROGYNOUS;
			case FEMININE:
			case FEMININE_STRONG:
				return Main.game.getPlayer().getFemininityValue() >= getFemininityRequired().getMinimumFemininity();
			case MASCULINE:
			case MASCULINE_STRONG:
				return Main.game.getPlayer().getFemininityValue() <= getFemininityRequired().getMaximumFemininity();
			default:
				return true;
		}
	}
	
	public boolean isRequiredSubspecies() {
		return getSubspeciesRequired() == null || getSubspeciesRequired().contains(Main.game.getPlayer().getSubspecies());
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
						if(sArea!=null && (!sArea.isFree(characterPerformingSexAction) || characterPerformingSexAction.isCoverableAreaBlockedFromGroping(sArea.getRelatedCoverableArea(characterPerformingSexAction)))) {
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
			if(sArea!=null && !characterPerformingSexAction.isSexAreaExposed(sArea)) {
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
						if(sArea!=null && (!sArea.isFree(characterTargetedForSexAction) || characterTargetedForSexAction.isCoverableAreaBlockedFromGroping(sArea.getRelatedCoverableArea(characterTargetedForSexAction)))) {
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
			if(sArea!=null && !characterTargetedForSexAction.isSexAreaExposed(sArea)) {
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

	public List<AbstractFetish> getFetishesForUnlock() {
		if(fromExternalFile && fetishesRequired==null && fetishesRequiredId!=null && !fetishesRequiredId.isEmpty()) {
			fetishesRequired = new ArrayList<>();
			for(String fetishId : fetishesRequiredId) {
				fetishesRequired.add(Fetish.getFetishFromId(UtilText.parse(fetishId).trim()));
			}
		}
		return fetishesRequired;
	}

	public CorruptionLevel getCorruptionNeeded() {
		if(fromExternalFile && corruptionBypass==null && corruptionBypassId!=null && !corruptionBypassId.isEmpty()) {
			corruptionBypass = CorruptionLevel.valueOf(UtilText.parse(corruptionBypassId).trim());
		}
		return corruptionBypass;
	}

	public List<AbstractPerk> getPerksRequired() {
		if(fromExternalFile && perksRequired==null && perksRequiredId!=null && !perksRequiredId.isEmpty()) {
			perksRequired = new ArrayList<>();
			for(String perkId : perksRequiredId) {
				perksRequired.add(Perk.getPerkFromId(UtilText.parse(perkId).trim()));
			}
		}
		return perksRequired;
	}

	public Femininity getFemininityRequired() {
		if(fromExternalFile && femininityRequired==null && femininityRequiredId!=null && !femininityRequiredId.isEmpty()) {
			femininityRequired = Femininity.valueOf(UtilText.parse(femininityRequiredId).trim());
		}
		return femininityRequired;
	}

	public List<AbstractSubspecies> getSubspeciesRequired() {
		if(fromExternalFile && subspeciesRequired==null && subspeciesRequiredId!=null && !subspeciesRequiredId.isEmpty()) {
			subspeciesRequired = new ArrayList<>();
			for(String subspeciesId : subspeciesRequiredId) {
				subspeciesRequired.add(Subspecies.getSubspeciesFromId(UtilText.parse(subspeciesId).trim()));
			}
		}
		return subspeciesRequired;
	}

	public static Response getDisallowedSpittingResponse() {
		return getDisallowedSpittingResponse("Spit");
	}

	public static Response getDisallowedSpittingResponse(String desc) {
		return new Response(desc, "You are being forced to drink down the potion!<br/>[style.italicsBad(Rejection of TF potions is disabled in the gameplay options!)]", null);
	}

	public String getConditional() {
		return conditional;
	}

	public void setConditional(String conditional) {
		this.conditional = conditional;
	}
	
	public boolean isAvailableFromConditional() {
		if(getConditional()!=null && !getConditional().isEmpty()) {
			return Boolean.valueOf(UtilText.parse(getConditional()).trim());
		}
		return true;
	}

	public String getColourId() {
		return colourId;
	}

	public void setColourId(String colourId) {
		this.colourId = colourId;
	}

	public String getSecondsPassedString() {
		return secondsPassedString;
	}

	public void setSecondsPassedString(String secondsPassedString) {
		this.secondsPassedString = secondsPassedString;
	}

	public String getEffectsString() {
		return effectsString;
	}

	public void setEffectsString(String effectsString) {
		this.effectsString = effectsString;
	}
}
