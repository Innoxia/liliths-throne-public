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
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.places.Population;

/**
 * @since 0.1.0
 * @version 0.3.4
 * @author Innoxia
 */
public interface SexManagerInterface {

	public AbstractSexPosition getPosition();

	public void assignNPCTarget(GameCharacter targeter);
	
	public Map<GameCharacter, SexSlot> getDominants();
	public Map<GameCharacter, SexSlot> getSubmissives();
	
	
	public default SexPace getStartingSexPaceModifier(GameCharacter character) {
		return null;
	}
	
	/**
	 * @param character
	 * @return The SexPace that this character should be locked into for the duration of this sex scene.
	 */
	public default SexPace getForcedSexPace(GameCharacter character) {
		return null;
	}
	
	public default Set<GameCharacter> getCharactersSealed() {
		return new HashSet<>();
	}
	
	public default boolean isPlayerDom() {
		return getDominants().containsKey(Main.game.getPlayer());
	}
	
	/**
	 * @param sexActionPlayer The action that the player just took before the partner's turn.
	 * @return The action that the partner takes.
	 */
	public SexActionInterface getPartnerSexAction(SexActionInterface sexActionPlayer);
	
	
	public default String getStartSexDescription() {
		return "";
	}
	
	public default SexType getForeplayPreference(NPC character, GameCharacter targetedCharacter) {
		return character.getForeplayPreference(targetedCharacter);
	}
	
	public default SexType getMainSexPreference(NPC character, GameCharacter targetedCharacter) {
		return character.getMainSexPreference(targetedCharacter);
	}

	public default SexControl getSexControl(GameCharacter character) {
		if(Sex.isDom(character)) {
			return SexControl.FULL;
		} else {
			if(Sex.isSubHasEqualControl()) {
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

	public default List<AbstractSexPosition> getAllowedSexPositions() {
		List<AbstractSexPosition> positions = Util.newArrayListOfValues(
				SexPosition.AGAINST_WALL,
				SexPosition.ALL_FOURS,
				SexPosition.LYING_DOWN,
				SexPosition.STANDING);
		
		switch(Main.game.getPlayerCell().getType()) {
			case ANGELS_KISS_FIRST_FLOOR:
			case ANGELS_KISS_GROUND_FLOOR:
			case CITY_HALL:
			case DADDYS_APARTMENT:
			case ENFORCER_HQ:
			case GAMBLING_DEN:
			case LILAYAS_HOUSE_FIRST_FLOOR:
			case LILAYAS_HOUSE_GROUND_FLOOR:
			case LYSSIETH_PALACE:
			case MUSEUM:
			case MUSEUM_LOST:
			case SHOPPING_ARCADE:
			case SUPPLIER_DEN:
			case ZARANIX_HOUSE_FIRST_FLOOR:
			case ZARANIX_HOUSE_GROUND_FLOOR:
				positions.add(SexPosition.OVER_DESK);
				positions.add(SexPosition.SITTING);
				break;
			case BAT_CAVERNS:
			case DOMINION:
			case EMPTY:
			case HARPY_NEST:
			case IMP_FORTRESS_ALPHA:
			case IMP_FORTRESS_DEMON:
			case IMP_FORTRESS_FEMALES:
			case IMP_FORTRESS_MALES:
			case NIGHTLIFE_CLUB:
			case SLAVER_ALLEY:
			case SLIME_QUEENS_LAIR_FIRST_FLOOR:
			case SLIME_QUEENS_LAIR_GROUND_FLOOR:
			case SUBMISSION:
			case WORLD_MAP:
				break;
			
		}
		return positions;
	}
	
	public default boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
		return character.isPlayer() && isPositionChangingAllowed(character);
	}
	
	/**
	 * @return true by default. If returns false, no position-changing actions at all are available for the character passed in to the method.
	 */
	public default boolean isPositionChangingAllowed(GameCharacter character) {
		return character.isPlayer() || (Sex.getTotalParticipantCount(false)==2); // Only player is allowed to switch in multi-sex scenes
	}
	
	public default boolean isPlayerAbleToStopSex() {
		return Sex.isDom(Main.game.getPlayer()) || (Sex.getSexControl(Main.game.getPlayer())==SexControl.FULL && Sex.isConsensual());
	}
	
	public default boolean isEndSexAffectionChangeEnabled(GameCharacter character) {
		return true;
	}
	
	public default boolean isPartnerWantingToStopSex(GameCharacter partner) {
		boolean subsSatisfied = true;
		boolean domsSatisfied = true;
		boolean subsResisting = true;
		boolean subsDenied = true;
		
		// Do not skip orgasms at end of sex:
		if(Sex.getLastUsedPlayerAction().getActionType().isOrgasmOption()
				|| Sex.getLastUsedPlayerAction().getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM) {
			return false; 
		}
		
		// Do not allow player-owned slaves to end sex if the player is also a dom and is not a spectator:
		if(Sex.isDom(Main.game.getPlayer()) && !Sex.isSpectator(Main.game.getPlayer()) && Sex.getInitialSexManager().isPlayerAbleToStopSex() && partner.isSlave() && partner.getOwner().isPlayer()) {
			return false;
		}
		
		for(GameCharacter character : Sex.getDominantParticipants(false).keySet()) {
			if(Sex.getNumberOfOrgasms(character)<character.getOrgasmsBeforeSatisfied() && Sex.getSexPositionSlot(character)!=SexSlotGeneric.MISC_WATCHING) {
				domsSatisfied = false;
			}
		}
		
		for(GameCharacter character : Sex.getSubmissiveParticipants(false).keySet()) {
			if(Sex.getSexPace(character)!=SexPace.SUB_RESISTING && Sex.getSexPositionSlot(character)!=SexSlotGeneric.MISC_WATCHING) {
				subsResisting = false;
			}
			if(Sex.getNumberOfOrgasms(character)<character.getOrgasmsBeforeSatisfied() && Sex.getSexPositionSlot(character)!=SexSlotGeneric.MISC_WATCHING) {
				subsSatisfied = false;
			}
			if(Sex.getNumberOfDeniedOrgasms(character)==0) {
				subsDenied = false;
			}
		}
		
		if(Sex.isDom(partner) && (!Sex.isConsensual() || subsResisting || !Sex.isSubHasEqualControl() || (partner.getFetishDesire(Fetish.FETISH_DENIAL).isPositive() && subsDenied))) {
			if(Sex.getNumberOfOrgasms(partner)>partner.getOrgasmsBeforeSatisfied()*2) {
				return true;
			}
			return domsSatisfied;
			
		} else if(Sex.getSexControl(partner)!=SexControl.FULL) {
			return false;
			
		} else {
			return domsSatisfied && subsSatisfied;
		}
	}
	
	public default void initStartingLustAndArousal(GameCharacter character) {
		character.setLustNoText(50);
		character.setArousal(0);
		if(Sex.isDom(character)) {
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
		
		if(Main.getProperties().hasValue(PropertyValue.nonConContent)) {
			if(!character.isPlayer() && !Sex.isMasturbation()) {
				int attracted = 0;
				int unattracted = 0;
				for(GameCharacter target : (Sex.isDom(character)?Sex.getSubmissiveParticipants(false).keySet():Sex.getDominantParticipants(false).keySet())) {
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
	
	public default boolean isAbleToEquipSexClothing(GameCharacter character){
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
		// Is now handled in SexManager as of v0.3.3.8
//		if(clothing!=null
//				&& !Sex.isDom(character)
//				&& clothing.getClothingType().isSexToy()) {
//			return false;
//		}
		
//		if(character.isPlayer()) {
//			return true;
//		}
//		
//		return Sex.getSexControl(character)==SexControl.FULL;
		
		// The only thing that should limit this is overridden special conditions:
		return true;
	}
	
	public default boolean isItemUseAvailable() {
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
		
		for(Entry<GameCharacter, List<CoverableArea>> e : this.exposeAtStartOfSexMap().entrySet()) {
			map.get(false).put(e.getKey(), new HashMap<>());
			
			for(CoverableArea c : e.getValue()) {
				map.get(false).get(e.getKey()).put(c, null);
			}
		}
			
		return map;
	}
	
	public default Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
		return new HashMap<>();
	}
	
	public default List<InventorySlot> getSlotsConcealed(GameCharacter character) {
		return new ArrayList<>();
	}
	
	public default boolean isPartnerUsingForeplayActions() {
		return true;
	}
	
	/**
	 * @param character
	 * @return The OrgasmBehaviour for this character. Normally returns DEFAULT, but can also return CREAMPIE or PULL_OUT, in which case the character will ignore requests and treat associated orgasm actions as having a SexActionPriority of UNIQUE_MAX.
	 */
	public default OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
		return OrgasmBehaviour.DEFAULT;
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

	public default String getPublicSexStartingDescription() {
		List<Subspecies> speciesPresent = null;
		Population pop = Main.game.getPlayer().getLocationPlace().getPlaceType().getPopulation();
		if(pop!=null) {
			speciesPresent = new ArrayList<>(pop.getSpecies().keySet());
		}
		if(speciesPresent!=null && !speciesPresent.isEmpty()) {
			List<Race> racesPresent = new ArrayList<>();
			for(Subspecies species : speciesPresent) {
				if(!racesPresent.contains(species.getRace())) {
					racesPresent.add(species.getRace());
				}
			}
			Collections.shuffle(racesPresent);
			List<String> raceNames = new ArrayList<>();
			for(int i=0; i<racesPresent.size() && i<3;i++) {
				raceNames.add(Subspecies.getMainSubspeciesOfRace(racesPresent.get(i)).getNamePlural(null));
			}
			if(raceNames.size() < racesPresent.size()) {
				raceNames.add("many other races");
			}
			
			return "<p style='color:"+Colour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
					+ (Sex.isMasturbation()
							?"A crowd of "+Util.stringsToStringList(raceNames, false)+" quickly forms around you, eager to watch your erotic display..."
							:"A crowd of "+Util.stringsToStringList(raceNames, false)+" quickly forms around you and [npc.name], eager to watch your erotic display...")
					+ "</p>";
			
		} else {
			return "<p style='color:"+Colour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
					+ (Sex.isMasturbation()
							?""
							:"A crowd quickly forms around you and [npc.name], eager to watch your erotic display...")
					+ "</p>";
		}
	}
	
	public default String getRandomPublicSexDescription() {
		if(Sex.isMasturbation()) {
			return "<p style='color:"+Colour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
						+UtilText.parse(Sex.getActivePartner(),
								UtilText.returnStringAtRandom(
								"The crowd of onlookers laugh and cheer as they look on.",
								"You hear someone in the crowd wolf-whistling as they watch you masturbating.",
								"A pair of Enforcers shove their way through the crowd, but instead of putting a stop to your fun, they join the onlookers in laughing and commenting on your performance.",
								"You hear the crowd that's gathered to watch you commenting on your performance.",
								"Cheering and laughing, the crowd of onlookers watch as you continue masturbating.",
								"You glance across to see several members of the crowd touching themselves as they watch you go at it.",
								"The crowd cheers you on as you carry on masturbating in front of them.",
								"Several members of the crowd shout and cheer as you carry on masturbating in front of them."))
					+"</p>";
			
		} else {
			return "<p style='color:"+Colour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
						+UtilText.parse(Sex.getActivePartner(),
								UtilText.returnStringAtRandom(
								"The crowd of onlookers laugh and cheer as they look on.",
								"You hear someone in the crowd wolf-whistling as they watch you having sex.",
								"A pair of Enforcers shove their way through the crowd, but instead of putting a stop to your fun, they join the onlookers in laughing and commenting on your performance.",
								"You hear the crowd that's gathered to watch you commenting on your performance.",
								"You hear the crowd that's gathered to watch you commenting on [npc.namePos] performance.",
								"Cheering and laughing, the crowd of onlookers watch as you continue having sex with [npc.name].",
								"You glance across to see several members of the crowd touching themselves as they watch you and [npc.name] go at it.",
								"The crowd cheers you on as you and [npc.name] carry on having sex in front of them.",
								"The crowd laughs and cheers as you and [npc.name] carry on having sex in front of them.",
								"Several members of the crowd shout and cheer as you and [npc.name] carry on having sex in front of them.",
								"Several members of the crowd cheer you on as you and [npc.name] carry on having sex in front of them."))
					+"</p>";
		}
	}
	
	public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap();

	public default boolean isAreasBannedMapAppliedToSelfActions(GameCharacter character) {
		return getAreasBannedMap().containsKey(character);
	}
	
	// Revealing CoverableAreas:
	
	public default boolean isAppendStartingExposedDescriptions(GameCharacter character) {
		return true;
	}

	public default boolean isAppendStartingWetDescriptions() {
		return true;
	}
	
	
	// Player:
	public default String getAssRevealReaction(GameCharacter characterBeingRevealed, List<GameCharacter> charactersReacting, boolean locationSpecific) {
		String reaction = "";
		
		if(!Sex.isMasturbation()
				&& Sex.getSexPositionSlot(characterBeingRevealed)!=SexSlotGeneric.MISC_WATCHING
				&& Sex.getSexPositionSlot(charactersReacting.get(0))!=SexSlotGeneric.MISC_WATCHING) {
			reaction = (!characterBeingRevealed.isPlayer()
						?"<p>"
							+ UtilText.parse(characterBeingRevealed, characterBeingRevealed.getAssDescription(locationSpecific))
						+ "</p>"
						:"")
					+ charactersReacting.get(0).getAssRevealDescription(characterBeingRevealed, charactersReacting, locationSpecific);
		}

		for(GameCharacter character : charactersReacting) {
			characterBeingRevealed.setAreaKnownByCharacter(CoverableArea.ANUS, character, true);
		}
		return reaction;
	}

	public default String getVaginaRevealReaction(GameCharacter characterBeingRevealed, List<GameCharacter> charactersReacting) {
		String reaction = "";

		if(!Sex.isMasturbation()
				&& Sex.getSexPositionSlot(characterBeingRevealed)!=SexSlotGeneric.MISC_WATCHING
				&& Sex.getSexPositionSlot(charactersReacting.get(0))!=SexSlotGeneric.MISC_WATCHING) {
			reaction = (!characterBeingRevealed.isPlayer()
						?"<p>"
							+ UtilText.parse(characterBeingRevealed, characterBeingRevealed.getVaginaDescription())
						+ "</p>"
						:"")
					+ charactersReacting.get(0).getVaginaRevealDescription(characterBeingRevealed, charactersReacting);
		}

		for(GameCharacter character : charactersReacting) {
			characterBeingRevealed.setAreaKnownByCharacter(CoverableArea.VAGINA, character, true);
		}
		return reaction;
	}

	public default String getBreastsRevealReaction(GameCharacter characterBeingRevealed, List<GameCharacter> charactersReacting) {
		String reaction = "";

		if(!Sex.isMasturbation()
				&& Sex.getSexPositionSlot(characterBeingRevealed)!=SexSlotGeneric.MISC_WATCHING
				&& Sex.getSexPositionSlot(charactersReacting.get(0))!=SexSlotGeneric.MISC_WATCHING) {
			reaction = (!characterBeingRevealed.isPlayer()
						?"<p>"
							+ UtilText.parse(characterBeingRevealed, characterBeingRevealed.getBreastDescription())
						+ "</p>"
						:"")
					+ charactersReacting.get(0).getBreastsRevealDescription(characterBeingRevealed, charactersReacting);
		}

		for(GameCharacter character : charactersReacting) {
			characterBeingRevealed.setAreaKnownByCharacter(CoverableArea.BREASTS, character, true);
		}
		return reaction;
	}

	public default String getBreastsCrotchRevealReaction(GameCharacter characterBeingRevealed, List<GameCharacter> charactersReacting) {
		String reaction = "";

		if(!Sex.isMasturbation()
				&& Sex.getSexPositionSlot(characterBeingRevealed)!=SexSlotGeneric.MISC_WATCHING
				&& Sex.getSexPositionSlot(charactersReacting.get(0))!=SexSlotGeneric.MISC_WATCHING) {
			reaction = (!characterBeingRevealed.isPlayer()
						?"<p>"
							+ UtilText.parse(characterBeingRevealed, characterBeingRevealed.getBreastCrotchDescription())
						+ "</p>"
						:"")
					+ charactersReacting.get(0).getBreastsCrotchRevealDescription(characterBeingRevealed, charactersReacting);
		}

		for(GameCharacter character : charactersReacting) {
			characterBeingRevealed.setAreaKnownByCharacter(CoverableArea.BREASTS_CROTCH, character, true);
		}
		return reaction;
	}

	public default String getPenisRevealReaction(GameCharacter characterBeingRevealed, List<GameCharacter> charactersReacting) {
		String reaction = "";

		if(!Sex.isMasturbation()
				&& Sex.getSexPositionSlot(characterBeingRevealed)!=SexSlotGeneric.MISC_WATCHING
				&& Sex.getSexPositionSlot(charactersReacting.get(0))!=SexSlotGeneric.MISC_WATCHING) {
			reaction = (!characterBeingRevealed.isPlayer()
						?"<p>"
							+ UtilText.parse(characterBeingRevealed, characterBeingRevealed.getPenisDescription())
						+ "</p>"
						:"")
					+ charactersReacting.get(0).getPenisRevealDescription(characterBeingRevealed, charactersReacting);
		}
		
		for(GameCharacter character : charactersReacting) {
			characterBeingRevealed.setAreaKnownByCharacter(CoverableArea.PENIS, character, true);
		}
		return reaction;
	}

	public default String getMoundRevealReaction(GameCharacter characterBeingRevealed, List<GameCharacter> charactersReacting) {
		String reaction = "";

		if(!Sex.isMasturbation()
				&& Sex.getSexPositionSlot(characterBeingRevealed)!=SexSlotGeneric.MISC_WATCHING
				&& Sex.getSexPositionSlot(charactersReacting.get(0))!=SexSlotGeneric.MISC_WATCHING) {
			reaction = charactersReacting.get(0).getMoundRevealDescription(characterBeingRevealed, charactersReacting);
		}

		for(GameCharacter character : charactersReacting) {
			characterBeingRevealed.setAreaKnownByCharacter(CoverableArea.PENIS, character, true);
			characterBeingRevealed.setAreaKnownByCharacter(CoverableArea.VAGINA, character, true);
		}
		return reaction;
	}

}
