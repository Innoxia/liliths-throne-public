package com.lilithsthrone.game.sex.managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.population.Population;
import com.lilithsthrone.world.population.PopulationType;

/**
 * @since 0.1.0
 * @version 0.3.9.1
 * @author Innoxia
 */
public interface SexManagerInterface {

	public default String getSexTitle() {
		return (!Main.sex.isConsensual() && Main.getProperties().hasValue(PropertyValue.nonConContent)?"Non-consensual ":"")
				+(Main.sex.isPublicSex()?"Public ":"")
				+(getPosition().getName().isEmpty()
						?(Main.sex.getAllParticipants(false).size()>1?"Sex":"Masturbation")
						:(Main.sex.getAllParticipants(false).size()>1?"Sex: ":"Masturbation: ")+getPosition().getName());
	}
	
	public AbstractSexPosition getPosition();

	public void assignNPCTarget(GameCharacter targeter);
	
	public Map<GameCharacter, SexSlot> getDominants();
	public Map<GameCharacter, SexSlot> getSubmissives();
	
	public boolean isAbleToSkipSexScene();

	/**
	 * @return The SexPace that this character should have at the start of this sex scene.
	 *  Unlike the <i>getForcedSexPace(character)</i> method, this method does <b>not</b> lock the character into the specified sex pace for the duration of this sex scene.
	 */
	public default SexPace getStartingSexPaceModifier(GameCharacter character) {
		return null;
	}
	
	/**
	 * @return The SexPace that this character should be locked into for the duration of this sex scene.
	 */
	public default SexPace getForcedSexPace(GameCharacter character) {
		return null;
	}
	
	/**
	 * @return Maps ImmobilisationType -> character who applied the immobilisation -> characters immobilised in this manner
	 */
	public default Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
		return new HashMap<>();
	}
	
	public default boolean isPlayerDom() {
		return getDominants().containsKey(Main.game.getPlayer());
	}
	
	/**
	 * @param partner The character who is deciding what action to use.
	 * @param sexActionPlayer The action that the player just took before the partner's turn.
	 * @return The action that the partner takes.
	 */
	public SexActionInterface getPartnerSexAction(NPC partner, SexActionInterface sexActionPlayer);
	
	public default boolean isSadisticActionsAllowed() {
		return true;
	}

	public default boolean isLovingActionsAllowed() {
		return true;
	}
	
	public default String getStartSexDescription() {
		return "";
	}

	/**Maps: character who is lubricated -> Map of areas -> Map of owner of lubrication -> lubrications*/
	public default Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
		return null;
	}
	
	/**
	 * @return true if this sex scene takes place in a shower, bath, or other place where running water makes it impossible for areas to be dirtied. When true, dirtied InventorySlots and clothing will be instantly cleaned.
	 */
	public default boolean isWashingScene() {
		return false;
	}

	/**
	 * Being hidden restricts the character's ability to end sex, and they can only use 'self' sex actions.<br/>
	 * <b>This should only ever be applied to spectators, as it makes no sense otherwise.</b>
	 * @return true if the character is considered to be out of sight and hidden in this sex scene.
	 */
	public default boolean isHidden(GameCharacter character) {
		return false;
	}

	/**
	 * @return The name of the desk over which sex will be taking place during this manager's sex scene. Leave as null or an empty String if not used.
	 */
	public default String getDeskName() {
		return null;
	}
	
	/**
	 * @return The name of the wall against which sex will be taking place during this manager's sex scene. Leave as null or an empty String if not used.
	 */
	public default String getWallName() {
		return null;
	}
	
	public default SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
		return character.getForeplayPreference(targetedCharacter);
	}
	
	public default SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
		return character.getMainSexPreference(targetedCharacter);
	}

	public default SexControl getSexControl(GameCharacter character) {
		if(isHidden(character)) {
			return SexControl.SELF;
		}
		if(Main.sex.isCharacterImmobilised(character)) {
			return SexControl.NONE;
		}
		if(Main.sex.isDom(character)) {
			return SexControl.FULL;
		} else {
			if(Main.sex.isSubHasEqualControl()) {
				return SexControl.FULL;
			} else {
				if(character.isPlayer()) {
					return SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS;
				} else {
					return SexControl.ONGOING_ONLY;
				}
			}
		}
	}
	
	public default String applyEndSexEffects() {
		return "";
	}
	
	/**
	 * @param character The character to check to see if their self-transform menu is disabled.
	 * @return True if self-transform is disabled during this sex scene.
	 */
	public default boolean isSelfTransformDisabled(GameCharacter character) {
		return false;
	}
	
	public default boolean isRapePlayBannedAtStart(GameCharacter character) {
		return !Main.getProperties().hasValue(PropertyValue.rapePlayAtSexStart);
	}
	
	public default boolean isSlotAvailable(GameCharacter character, SexSlot slot) {
		if(character.isAsleep()) {
			return slot==Main.sex.getSexPositionSlot(character); // If asleep, do not allow changing out of current slot
		}
		if(slot.hasTag(SexSlotTag.MATING_PRESS) && Main.sex.getAllParticipants(false).stream().anyMatch(c->c.isAsleep())) {
			return false; // DO not allow mating press if characters are sleeping
		}
		return true;
	}
	
	public default List<AbstractSexPosition> getAllowedSexPositions() {
		if(Main.sex.getAllParticipants(false).stream().anyMatch(c->c.isAsleep())) {
			return Util.newArrayListOfValues(Main.sex.getPosition()); // If asleep, do not allow changing out of current position
		}
		
		List<AbstractSexPosition> positions = Util.newArrayListOfValues(
				SexPosition.ALL_FOURS,
				SexPosition.LYING_DOWN,
				SexPosition.STANDING);

		if(Main.game.getPlayerCell().isWallsPresent()) {
			positions.add(SexPosition.AGAINST_WALL);
		}
		
		if(Main.game.getPlayerCell().isFurniturePresent()) {
			positions.add(SexPosition.OVER_DESK);
			positions.add(SexPosition.SITTING);
		}
		
		return positions;
	}
	
	public default boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
		return character.isPlayer()
				&& isPositionChangingAllowed(character)
				&& Main.sex.getInitialSexManager().isSlotAvailable(character, Main.sex.getSexPositionSlot(target))
				&& Main.sex.getInitialSexManager().isSlotAvailable(target, Main.sex.getSexPositionSlot(character));
	}
	
	/**
	 * @return true by default. If returns false, no position-changing actions at all are available for the character passed in to the method.
	 */
	public default boolean isPositionChangingAllowed(GameCharacter character) {
		return character.isPlayer() || (Main.sex.getTotalParticipantCount(false)==2); // Only player is allowed to switch in multi-sex scenes
	}

	public default boolean isPlayerAbleToStopSex() {
		return isCharacterAbleToStopSex(Main.game.getPlayer());
	}
	
	public default boolean isCharacterAbleToStopSex(GameCharacter character) {
		return Main.sex.isDom(character)
				|| (Main.sex.getSexControl(character)==SexControl.FULL && Main.sex.isConsensual())
				|| (Main.sex.isSpectator(character) && character.isPlayer() && Main.sex.getInitialSexManager().isHidden(character)); // If player is a hidden spectator they can 'stop' sex, which applies quick sex effects
	}
	
	public default boolean isEndSexAffectionChangeEnabled(GameCharacter character) {
		return true;
	}
	
	public default boolean isPartnerWantingToStopSex(GameCharacter partner) {
		boolean subsSatisfied = true;
		boolean domsSatisfied = true;
		boolean subsResisting = true;
		boolean subsDenied = true;
		boolean subsStillInForeplay = true;
		
		if(!isCharacterAbleToStopSex(partner)) {
			return false;
		}
		
		// Do not skip orgasms at end of sex:
		if(Main.sex.getLastUsedPlayerAction().getActionType().isOrgasmOption()
				|| Main.sex.getLastUsedPlayerAction().getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM) {
			return false; 
		}
		
		// Do not allow player-owned slaves to end sex if the player is also a dom and is not a spectator:
		if(Main.sex.isDom(Main.game.getPlayer())
				&& !Main.sex.isSpectator(Main.game.getPlayer())
				&& Main.sex.getInitialSexManager().isCharacterAbleToStopSex(Main.game.getPlayer())
				&& partner.isSlave()
				&& partner.getOwner().isPlayer()) {
			return false;
		}
		
		for(GameCharacter character : Main.sex.getDominantParticipants(false).keySet()) {
			if(!Main.sex.isSatisfiedFromOrgasms(character, true) && Main.sex.getSexPositionSlot(character)!=SexSlotGeneric.MISC_WATCHING) {
				domsSatisfied = false;
			}
		}
		
		for(GameCharacter character : Main.sex.getSubmissiveParticipants(false).keySet()) {
			if(Main.sex.getSexPace(character)!=SexPace.SUB_RESISTING && Main.sex.getSexPositionSlot(character)!=SexSlotGeneric.MISC_WATCHING) {
				subsResisting = false;
			}
			if(!Main.sex.isSatisfiedFromOrgasms(character, true) && Main.sex.getSexPositionSlot(character)!=SexSlotGeneric.MISC_WATCHING) {
				subsSatisfied = false;
			}
			if(Main.sex.getNumberOfDeniedOrgasms(character)==0) {
				subsDenied = false;
			}
			if(!Main.sex.isInForeplay(character)) {
				subsStillInForeplay = false;
			}
		}
		
		boolean gettingBored = Main.sex.getNumberOfOrgasms(partner)>partner.getOrgasmsBeforeSatisfied()+1;
		
		if(Main.sex.isDom(partner)
				&& (!Main.sex.isConsensual()
						|| subsResisting
						|| (subsStillInForeplay && gettingBored)
						|| (partner.getFetishDesire(Fetish.FETISH_DENIAL).isPositive() && subsDenied))) {
			if(gettingBored) {
				return true;
			}
			return domsSatisfied;
			
		} else if(Main.sex.getSexControl(partner)!=SexControl.FULL) {
			return false;
			
		} else {
			return domsSatisfied && subsSatisfied;
		}
	}
	
	public default void initStartingLustAndArousal(GameCharacter character) {
		float startingLust = character.getLust();
		
		character.setLustNoText(50);
		character.setArousal(0);
		if(Main.sex.isDom(character)) {
			if(character.hasFetish(Fetish.FETISH_DOMINANT)) {
				character.setLustNoText(85);
				character.setArousal(10);
			} else if(character.hasFetish(Fetish.FETISH_SUBMISSIVE)) {
				character.setLustNoText(10);
			}
		} else {
			if(character.hasFetish(Fetish.FETISH_SUBMISSIVE)) {
				character.setLustNoText(85);
				character.setArousal(10);
			}
		}
		if(character.getLust()<startingLust) {
			character.setLustNoText(startingLust);
			character.setArousal(startingLust*0.15f);
		}
		
		if(Main.getProperties().hasValue(PropertyValue.nonConContent)) {
			if(!character.isPlayer() && !Main.sex.isMasturbation()) {
				int attracted = 0;
				int unattracted = 0;
				for(GameCharacter target : (Main.sex.isDom(character)?Main.sex.getSubmissiveParticipants(false).keySet():Main.sex.getDominantParticipants(false).keySet())) {
					if(character.isAttractedTo(target)) {
						attracted++;
					} else {
						unattracted++;
					}
				}
				if(attracted==0) {
					character.setLustNoText(0); // If they aren't attracted to anyone, start resisting
				} else if(unattracted>0) {
					character.setLustNoText(character.getLust()/2); // If they are attracted to some, but not all, halve starting lust
				}
			}
		}
	}
	
	public default boolean isItemUseAvailable() {
		return true;
	}
	
	public default boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
		return true;
	}
	
	public default boolean isAbleToRemoveSelfClothing(GameCharacter character){
		return true;
	}
	
	/**
	 * @param character The character whose permission is to be tested.
	 * @param clothing The clothing to test. Can be null for a generic permissions check.
	 * @return true if this character is able to remove other people's clothing in sex.
	 */
	public default boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
		// The only thing that should limit this is overridden special conditions:
		return true;
	}

	public default boolean isAbleToRemoveClothingSeals(GameCharacter character){
		return true;
	}
	
	public default boolean isCharacterStartNaked(GameCharacter character) {
		return false;
	}
	
	/**
	 * @return A mapping of characters to the areas which they should have exposed at the start of sex.
	 *  The initial Boolean is to determine if clothing is to be removed (true), or displaced (false).
	 *  The inner map's key is which area is to be exposed, while the value (a list of InventorySlots) corresponds to what slots should not be touched while exposing this area.
	 *  To see how it's used, reference GameCharacter's displaceClothingForAccess() method.
	 */
	public default Map<Boolean, Map<GameCharacter, Map<CoverableArea, List<InventorySlot>>>> exposeAtStartOfSexMapExtendedInformation() {
		Map<Boolean, Map<GameCharacter, Map<CoverableArea, List<InventorySlot>>>> map = new HashMap<>();
		
		map.put(false, new HashMap<>());
		map.put(true, new HashMap<>());
		
		for(Entry<GameCharacter, List<CoverableArea>> e : this.exposeAtStartOfSexMap().entrySet()) {
			map.get(isExposeAtStartOfSexMapRemoval(e.getKey())).put(e.getKey(), new HashMap<>());
			
			for(CoverableArea c : e.getValue()) {
				map.get(isExposeAtStartOfSexMapRemoval(e.getKey())).get(e.getKey()).put(c, null);
			}
		}
		
		return map;
	}
	
	public default Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
		return new HashMap<>();
	}
	
	/**
	 * @return true if this character should prefer to remove their clothes in the clothing removal setup to this sex scene. (Returns false by default.)
	 */
	public default boolean isExposeAtStartOfSexMapRemoval(GameCharacter character) {
		return false;
	}
	
	public default List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
		if((performer.equals(target) || Main.sex.isConsensual())
				&& (target.hasBreasts() || target.isFeminine())
				&& (!target.isFeral() || target.getFeralAttributes().isBreastsPresent())) {
			return Util.newArrayListOfValues(CoverableArea.NIPPLES);
		}
		
		return new ArrayList<>();
	}
	
	public default List<InventorySlot> getSlotsConcealed(GameCharacter characterBeingExposed, GameCharacter characterViewing) {
		return new ArrayList<>();
	}
	
	public default boolean isPartnerUsingForeplayActions() {
		return true;
	}

	public default boolean isForceCreampieAllowed(GameCharacter characterOrgasming, GameCharacter characterRecevingCreampie) {
		return true;
	}
	
	/**
	 * @return The OrgasmBehaviour for this character.
	 *  Normally returns DEFAULT, but can also return CREAMPIE or PULL_OUT, in which case the character will ignore requests and treat associated orgasm actions as having a SexActionPriority of UNIQUE_MAX.
	 */
	public default OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
		return OrgasmBehaviour.DEFAULT;
	}

	/**
	 * @return The OrgasmBehaviour for this character when reacting to the characterOrgasming orgasming in the characterPenetrated.
	 *  Normally returns DEFAULT, but can also return NO_ENCOURAGE, CREAMPIE, or PULL_OUT, in which case the character will use GENERIC_PREPARATION_PREPARE, GENERIC_PREPARATION_ENCOURAGE_CREAMPIE, or GENERIC_PREPARATION_ENCOURAGE_PULL_OUT, respectively.
	 */
	public default OrgasmEncourageBehaviour getCharacterOrgasmEncourageBehaviour(GameCharacter character, GameCharacter characterOrgasming, GameCharacter characterPenetrated) {
		return OrgasmEncourageBehaviour.DEFAULT;
	}
	
	/**
	 * @return The OrgasmCumTarget for when this character is orgasming in an interaction with the target.
	 * <br/>Determines where they want to cum <b>only if they choose to pull out</b>, with a return of null signifying that there are no special targeting priorities.
	 * <br/><b>Note:</b> This also limits the player's orgasm actions.
	 */
	public default OrgasmCumTarget getCharacterPullOutOrgasmCumTarget(GameCharacter character, GameCharacter target) {
		if(character.isPlayer()) {
			return null;
		}
		List<OrgasmCumTarget> orgasmTargets = new ArrayList<>();
		for(Entry<GameCharacter, OrgasmCumTarget> entry : Main.sex.getCharactersRequestingPullout().entrySet()) {
			if(entry.getValue()!=null && (character.isPlayer() || !((NPC)character).getSexBehaviourDeniesRequests(entry.getKey()))) {
				orgasmTargets.add(entry.getValue());
			}
		}
		if(!orgasmTargets.isEmpty()) {
			return Util.randomItemFrom(orgasmTargets);
		}
		return null;
	}
	
	public default boolean isPublicSex() {
		for(GameCharacter character : this.getDominants().keySet()) {
			if(character.getLocationPlace().isPopulated()) {
				return true;
			}
		}
		for(GameCharacter character : this.getSubmissives().keySet()) {
			if(character.getLocationPlace().isPopulated()) {
				return true;
			}
		}
		return false;
	}
	
	public default String applyPublicSexFormatting(String text) {
		return "<p style='color:"+PresetColour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
					+ text
				+ "</p>";
	}
	
	public default String getPublicSexStartingDescription() {
		Set<AbstractSubspecies> subspeciesSet = new HashSet<>();
		for(Population pop : Main.game.getPlayer().getLocationPlace().getPlaceType().getPopulation()) {
			subspeciesSet.addAll(pop.getSpecies().keySet());
		}
		if(!subspeciesSet.isEmpty()) {
			List<AbstractRace> racesPresent = new ArrayList<>();
			for(AbstractSubspecies species : subspeciesSet) {
				if(!racesPresent.contains(species.getRace())) {
					racesPresent.add(species.getRace());
				}
			}
			Collections.shuffle(racesPresent);
			List<String> raceNames = new ArrayList<>();
			for(int i=0; i<racesPresent.size() && i<3;i++) {
				raceNames.add(AbstractSubspecies.getMainSubspeciesOfRace(racesPresent.get(i)).getNamePlural(null));
			}
			if(raceNames.size() < racesPresent.size()) {
				raceNames.add("many other races");
			}
			
			return applyPublicSexFormatting(
						Main.sex.isMasturbation()
							?"A crowd of "+Util.stringsToStringList(raceNames, false)+" quickly forms around you, eager to watch your erotic display..."
							:"A crowd of "+Util.stringsToStringList(raceNames, false)+" quickly forms around you and [npc.name], eager to watch your erotic display...");
			
		} else {
			return applyPublicSexFormatting(
						Main.sex.isMasturbation()
							?""
							:"A crowd quickly forms around you and [npc.name], eager to watch your erotic display...");
		}
	}
	
	public default String getRandomPublicSexDescription() {
		boolean enforcersPresent = false;
		for(Population pop : Main.game.getPlayer().getLocationPlace().getPlaceType().getPopulation()) {
			if(pop.getType()==PopulationType.ENFORCER) {
				enforcersPresent = true;
				break;
			}
		}
		if(Main.sex.isMasturbation()) {
			return applyPublicSexFormatting(
						UtilText.returnStringAtRandom(
							"The crowd of onlookers laugh and cheer as they look on.",
							"You hear someone in the crowd wolf-whistling as they watch you masturbating.",
							enforcersPresent
								?"A pair of Enforcers shove their way through the crowd, but instead of putting a stop to your fun, they join the onlookers in laughing and commenting on your performance."
								:"",
							"You hear the crowd that's gathered to watch you commenting on your performance.",
							"Cheering and laughing, the crowd of onlookers watch as you continue masturbating.",
							"You glance across to see several members of the crowd touching themselves as they watch you go at it.",
							"The crowd cheers you on as you carry on masturbating in front of them.",
							"Several members of the crowd shout and cheer as you carry on masturbating in front of them."));
			
		} else if(Main.sex.getAllParticipants(false).contains(Main.game.getPlayer())) {
			return applyPublicSexFormatting(
					UtilText.parse(Main.sex.getTargetedPartner(Main.game.getPlayer()),
							UtilText.returnStringAtRandom(
								"The crowd of onlookers laugh and cheer as they look on.",
								"You hear someone in the crowd wolf-whistling as they watch you having sex.",
								enforcersPresent
									?"A pair of Enforcers shove their way through the crowd, but instead of putting a stop to your fun, they join the onlookers in laughing and commenting on your performance."
									:"",
								"You hear the crowd that's gathered to watch you commenting on your performance.",
								"You hear the crowd that's gathered to watch you commenting on [npc.namePos] performance.",
								"Cheering and laughing, the crowd of onlookers watch as you continue having sex with [npc.name].",
								"You glance across to see several members of the crowd touching themselves as they watch you and [npc.name] go at it.",
								"The crowd cheers you on as you and [npc.name] carry on having sex in front of them.",
								"The crowd laughs and cheers as you and [npc.name] carry on having sex in front of them.",
								"Several members of the crowd shout and cheer as you and [npc.name] carry on having sex in front of them.",
								"Several members of the crowd cheer you on as you and [npc.name] carry on having sex in front of them.")));
			
		} else {
			GameCharacter target = Util.randomItemFrom(Main.sex.getDominantParticipants(false).keySet());
			return applyPublicSexFormatting(
					UtilText.parse(target, Main.sex.getTargetedPartner(target),
						UtilText.returnStringAtRandom(
							"The crowd of onlookers laugh and cheer as they look on.",
							"You hear someone in the crowd wolf-whistling as they watch [npc.name] fucking [npc2.name].",
							enforcersPresent
								?"A pair of Enforcers shove their way through the crowd, but instead of putting a stop to the fun, they join the onlookers in laughing and commenting on your performance."
								:"",
							"You hear the crowd that's gathered to watch [npc.name] commenting on [npc.her] performance.",
							"You hear the crowd that's gathered to watch [npc2.name] commenting on [npc2.her] performance.",
							"Cheering and laughing, the crowd of onlookers watch as [npc.name] continues having sex with [npc2.name].",
							"You glance across to see several members of the crowd touching themselves as they watch [npc.name] and [npc2.name] go at it.",
							"The crowd cheers [npc.name] on as [npc.she] carries on having sex in front of them.",
							"The crowd laughs and cheers as [npc.name] and [npc2.name] carry on having sex in front of them.",
							"Several members of the crowd shout and cheer as [npc.name] and [npc2.name] carry on having sex in front of them.",
							"Several members of the crowd cheer [npc.name] on as [npc.she] carries on fucking [npc2.name] in front of them.")));
		}
	}

	public Map<GameCharacter, List<SexType>> getSexTypesBannedMap();
	
	public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap();

	public default boolean isAreasBannedMapAppliedToSelfActions(GameCharacter character) {
		return getAreasBannedMap()!=null && getAreasBannedMap().containsKey(character);
	}
	
	// Revealing CoverableAreas:
	
	public default boolean isAppendStartingExposedDescriptions(GameCharacter character) {
		return true;
	}

	/**
	 * @return false by default, as the 'X was already lubricated by Y' messages are extremely annoying in multiple-partner sex scenes...
	 */
	public default boolean isAppendStartingWetDescriptions() {
		return false;
	}

	public default boolean isCharactersReactingToExposedAreas() {
		return true;
	}
	
	// Player:
	public default String getAssRevealReaction(GameCharacter characterBeingRevealed, List<GameCharacter> charactersReacting, boolean locationSpecific) {
		StringBuilder reaction = new StringBuilder();
		
		if(!Main.sex.isMasturbation()
				&& Main.sex.getSexPositionSlot(characterBeingRevealed)!=SexSlotGeneric.MISC_WATCHING
				&& Main.sex.getSexPositionSlot(charactersReacting.get(0))!=SexSlotGeneric.MISC_WATCHING) {
			if(!characterBeingRevealed.isPlayer()) {
				reaction.append("<p>"
							+ UtilText.parse(characterBeingRevealed, characterBeingRevealed.getAssDescription(locationSpecific))
						+ "</p>");
			}
			if(isCharactersReactingToExposedAreas()) {
				for(GameCharacter reactor : charactersReacting) {
					if(!reactor.equals(characterBeingRevealed) && !characterBeingRevealed.getInventorySlotsConcealed(reactor).containsKey(InventorySlot.ANUS)) {
						reaction.append(reactor.getAssRevealDescription(characterBeingRevealed, reactor, locationSpecific));
					}
				}
			}
		}

		for(GameCharacter character : charactersReacting) {
			characterBeingRevealed.setAreaKnownByCharacter(CoverableArea.ANUS, character, true);
		}
		return reaction.toString();
	}

	public default String getVaginaRevealReaction(GameCharacter characterBeingRevealed, List<GameCharacter> charactersReacting) {
		StringBuilder reaction = new StringBuilder();

		if(!Main.sex.isMasturbation()
				&& Main.sex.getSexPositionSlot(characterBeingRevealed)!=SexSlotGeneric.MISC_WATCHING
				&& Main.sex.getSexPositionSlot(charactersReacting.get(0))!=SexSlotGeneric.MISC_WATCHING) {
			if(!characterBeingRevealed.isPlayer()) {
				reaction.append("<p>"
							+ UtilText.parse(characterBeingRevealed, characterBeingRevealed.getVaginaDescription())
						+ "</p>");
			}
			if(isCharactersReactingToExposedAreas()) {
				for(GameCharacter reactor : charactersReacting) {
					if(!reactor.equals(characterBeingRevealed) && !characterBeingRevealed.getInventorySlotsConcealed(reactor).containsKey(InventorySlot.VAGINA)) {
						reaction.append(reactor.getVaginaRevealDescription(characterBeingRevealed, reactor));
					}
				}
			}
		}

		for(GameCharacter character : charactersReacting) {
			characterBeingRevealed.setAreaKnownByCharacter(CoverableArea.VAGINA, character, true);
		}
		return reaction.toString();
	}

	public default String getBreastsRevealReaction(GameCharacter characterBeingRevealed, List<GameCharacter> charactersReacting) {
		StringBuilder reaction = new StringBuilder();

		if(!Main.sex.isMasturbation()
				&& Main.sex.getSexPositionSlot(characterBeingRevealed)!=SexSlotGeneric.MISC_WATCHING
				&& Main.sex.getSexPositionSlot(charactersReacting.get(0))!=SexSlotGeneric.MISC_WATCHING) {
			if(!characterBeingRevealed.isPlayer()) {
				reaction.append("<p>"
							+ UtilText.parse(characterBeingRevealed, characterBeingRevealed.getBreastDescription())
						+ "</p>");
			}
			if(isCharactersReactingToExposedAreas()) {
				for(GameCharacter reactor : charactersReacting) {
					if(!reactor.equals(characterBeingRevealed) && !characterBeingRevealed.getInventorySlotsConcealed(reactor).containsKey(InventorySlot.NIPPLE)) {
						reaction.append(reactor.getBreastsRevealDescription(characterBeingRevealed, reactor));
					}
				}
			}
		}

		for(GameCharacter character : charactersReacting) {
			characterBeingRevealed.setAreaKnownByCharacter(CoverableArea.BREASTS, character, true);
		}
		return reaction.toString();
	}

	public default String getBreastsCrotchRevealReaction(GameCharacter characterBeingRevealed, List<GameCharacter> charactersReacting) {
		StringBuilder reaction = new StringBuilder();

		if(!Main.sex.isMasturbation()
				&& Main.sex.getSexPositionSlot(characterBeingRevealed)!=SexSlotGeneric.MISC_WATCHING
				&& Main.sex.getSexPositionSlot(charactersReacting.get(0))!=SexSlotGeneric.MISC_WATCHING) {
			if(!characterBeingRevealed.isPlayer()) {
				reaction.append("<p>"
							+ UtilText.parse(characterBeingRevealed, characterBeingRevealed.getBreastCrotchDescription())
						+ "</p>");
			}
			if(isCharactersReactingToExposedAreas()) {
				for(GameCharacter reactor : charactersReacting) {
					if(!reactor.equals(characterBeingRevealed) && !characterBeingRevealed.getInventorySlotsConcealed(reactor).containsKey(InventorySlot.STOMACH)) {
						reaction.append(reactor.getBreastsCrotchRevealDescription(characterBeingRevealed, reactor));
					}
				}
			}
		}

		for(GameCharacter character : charactersReacting) {
			characterBeingRevealed.setAreaKnownByCharacter(CoverableArea.BREASTS_CROTCH, character, true);
		}
		return reaction.toString();
	}

	public default String getPenisRevealReaction(GameCharacter characterBeingRevealed, List<GameCharacter> charactersReacting) {
		StringBuilder reaction = new StringBuilder();

		if(!Main.sex.isMasturbation()
				&& Main.sex.getSexPositionSlot(characterBeingRevealed)!=SexSlotGeneric.MISC_WATCHING
				&& Main.sex.getSexPositionSlot(charactersReacting.get(0))!=SexSlotGeneric.MISC_WATCHING) {
			if(!characterBeingRevealed.isPlayer()) {
				reaction.append("<p>"
							+ UtilText.parse(characterBeingRevealed, characterBeingRevealed.getPenisDescription())
						+ "</p>");
			}
			if(isCharactersReactingToExposedAreas()) {
				for(GameCharacter reactor : charactersReacting) {
					if(!reactor.equals(characterBeingRevealed) && !characterBeingRevealed.getInventorySlotsConcealed(reactor).containsKey(InventorySlot.PENIS)) {
						reaction.append(reactor.getPenisRevealDescription(characterBeingRevealed, reactor));
					}
				}
			}
		}
		
		for(GameCharacter character : charactersReacting) {
			characterBeingRevealed.setAreaKnownByCharacter(CoverableArea.PENIS, character, true);
		}
		return reaction.toString();
	}

	public default String getMoundRevealReaction(GameCharacter characterBeingRevealed, List<GameCharacter> charactersReacting) {
		StringBuilder reaction = new StringBuilder();

		if(!Main.sex.isMasturbation()
				&& Main.sex.getSexPositionSlot(characterBeingRevealed)!=SexSlotGeneric.MISC_WATCHING
				&& Main.sex.getSexPositionSlot(charactersReacting.get(0))!=SexSlotGeneric.MISC_WATCHING) {
			if(isCharactersReactingToExposedAreas()) {
				for(GameCharacter reactor : charactersReacting) {
					if(!reactor.equals(characterBeingRevealed) && !characterBeingRevealed.getInventorySlotsConcealed(reactor).containsKey(InventorySlot.VAGINA)) {
						reaction.append(reactor.getMoundRevealDescription(characterBeingRevealed, reactor));
					}
				}
			}
		}

		for(GameCharacter character : charactersReacting) {
			characterBeingRevealed.setAreaKnownByCharacter(CoverableArea.PENIS, character, true);
			characterBeingRevealed.setAreaKnownByCharacter(CoverableArea.VAGINA, character, true);
		}
		return reaction.toString();
	}
	
	// Only used in external managers:
	
	public default String getDirtyTalk(GameCharacter character) {
		return character.getDirtyTalk();
	}
	
	public default String getRoughTalk(GameCharacter character) {
		return character.getRoughTalk();
	}

	public default String getLovingTalk(GameCharacter character) {
		return character.getLovingTalk();
	}

	public default String getLovingResponseTalk(GameCharacter character) {
		return character.getLovingResponseTalk();
	}
	
	public default String getSubmissiveTalk(GameCharacter character) {
		return character.getSubmissiveTalk();
	}
	
	public default GameCharacter getPreferredSexTarget(NPC character) {
		return character.getPreferredSexTarget();
	}
	
	public default List<SexActionInterface> getUniqueSexClasses(GameCharacter character) {
		return new ArrayList<>();
	}
	
	public default List<OrgasmCumTarget> getBannedOrgasmCumTargets(GameCharacter character, GameCharacter target) {
		return new ArrayList<>();
	}
}
