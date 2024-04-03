package com.lilithsthrone.game.sex.positions;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexActionInteractions;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionPresets;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * SexPositions determine what actions are available for each AbstractSexSlot.<br/><br/>
 * 
 * Each value holds a map, <i>slotTargets</i>, which maps AbstractSexSlots to a map of AbstractSexSlots, which in turn maps to positions available.
 *  By providing a character's position in sex, along with the position of the partner they're targeting, this map is used to fetch available actions.<br/><br/>
 *  
 *  <b>Example:</b><br/>
 *  <i>getSexInteractions(character1AbstractSexSlot, character2AbstractSexSlot)</i><br/>returns the <i>SexActionPresetPair</i> which holds all available actions.<br/><br/>
 *  
 *  If character1AbstractSexSlot is SexPositionSlot.DOGGY_ON_ALL_FOURS, and character2SexPositionSlot is SexPositionSlot.DOGGY_BEHIND, then the returned actions would be those that
 *   are available for the character on all fours, in relation to a character kneeling behind them.
 * 
 * @since 0.1.97
 * @version 0.3.4.5
 * @author Innoxia
 */
public abstract class AbstractSexPosition {

	private String name;
	private int maximumSlots;
	private boolean addStandardActions;
	
	private List<Class<?>> positioningClasses;
	private List<Class<?>> specialClasses;
	
	public static List<SexAreaOrifice> genericGroinForceCreampieAreas = Util.newArrayListOfValues(SexAreaOrifice.ANUS, SexAreaOrifice.VAGINA, SexAreaOrifice.URETHRA_VAGINA, SexAreaOrifice.URETHRA_PENIS, SexAreaOrifice.SPINNERET);
	public static List<SexAreaOrifice> genericFaceForceCreampieAreas = Util.newArrayListOfValues(SexAreaOrifice.MOUTH);
	
	public AbstractSexPosition(String name,
			int maximumSlots,
			boolean addStandardActions,
			List<Class<?>> positioningClasses,
			List<Class<?>> specialClasses) {
		this.name = name;
		this.maximumSlots = maximumSlots;
		this.addStandardActions = addStandardActions;
		this.positioningClasses = positioningClasses;
		this.specialClasses = specialClasses;
	}
	
	public String getName() {
		return UtilText.parse(name);
	}

	public boolean isAddStandardActions() {
		return addStandardActions;
	}

	public List<Class<?>> getPositioningClasses() {
		return positioningClasses;
	}

	public List<Class<?>> getSpecialClasses() {
		return specialClasses;
	}

	public abstract String getDescription(Map<GameCharacter, SexSlot> occupiedSlots);

	public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
		return new Value<Boolean, String>(true, "");
	}
	
	public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
		return new Value<Boolean, String>(true, "");
	}
	
	public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
		if(action.getActionType()==SexActionType.START_ONGOING
				|| action.getActionType()==SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED
				|| action.getActionType()==SexActionType.REQUIRES_NO_PENETRATION) {
			
			// Block penis+non-appendage-non-pussy actions if target's penis is already in use:
			try {
				// Trying to interact a penis with a character who is already using a penis:
				if(action.getSexAreaInteractions().containsKey(SexAreaPenetration.PENIS)
						&& Collections.disjoint(action.getSexAreaInteractions().values(), SexActionPresets.appendageAreas)) {
					boolean ongoingAllowedFound = false;
					for(SexAreaInterface sa : Main.sex.getOngoingSexAreas(target, SexAreaPenetration.PENIS, performer)) {
						if(!SexActionPresets.allowedInterPenetrationAreas.contains(sa)) {
							return true;
						} else if(sa==SexAreaOrifice.VAGINA) {
							ongoingAllowedFound = true;
						}
					}
					if(ongoingAllowedFound) {
						for(SexAreaInterface sa : action.getSexAreaInteractions().values()) {
							if(!SexActionPresets.allowedInterPenetrationAreas.contains(sa)) {
								return true;
							}
						}
					}
				}
			}catch(Exception ex) {}
			try {
				// Trying to interact a penis with a character who is already using a penis:
				if(action.getSexAreaInteractions().values().contains(SexAreaPenetration.PENIS)
						&& Collections.disjoint(action.getSexAreaInteractions().keySet(), SexActionPresets.appendageAreas)) {
					boolean ongoingAllowedFound = false;
					for(SexAreaInterface sa : Main.sex.getOngoingSexAreas(performer, SexAreaPenetration.PENIS, target)) {
						if(!SexActionPresets.allowedInterPenetrationAreas.contains(sa)) {
							return true;
						} else if(sa==SexAreaOrifice.VAGINA) {
							ongoingAllowedFound = true;
						}
					}
					if(ongoingAllowedFound) {
						for(SexAreaInterface sa : action.getSexAreaInteractions().keySet()) {
							if(!SexActionPresets.allowedInterPenetrationAreas.contains(sa)) {
								return true;
							}
						}
					}
				}
			} catch(Exception ex) {}
			
			
			// Block tribbing and thigh sex if ongoing penis/vagina or penis/anus penetration:
			Set<SexAreaOrifice> impossibleTribbingAreas = Util.newHashSetOfValues(SexAreaOrifice.ANUS, SexAreaOrifice.VAGINA, SexAreaOrifice.THIGHS);
			if(action.getSexAreaInteractions().containsKey(SexAreaPenetration.CLIT) && action.getSexAreaInteractions().values().contains(SexAreaPenetration.CLIT)
				&& ((Main.sex.getOngoingActionsMap(performer).containsKey(SexAreaPenetration.PENIS) && Main.sex.getOngoingActionsMap(performer).get(SexAreaPenetration.PENIS).values().stream().anyMatch((set)->!Collections.disjoint(set, impossibleTribbingAreas)))
					|| (Main.sex.getOngoingActionsMap(target).containsKey(SexAreaPenetration.PENIS) && Main.sex.getOngoingActionsMap(target).get(SexAreaPenetration.PENIS).values().stream().anyMatch((set)->!Collections.disjoint(set, impossibleTribbingAreas))))) {
				return true;
			}
			if(((action.getSexAreaInteractions().containsKey(SexAreaPenetration.PENIS) && !Collections.disjoint(action.getSexAreaInteractions().values(), impossibleTribbingAreas))
					|| (!Collections.disjoint(action.getSexAreaInteractions().keySet(), impossibleTribbingAreas) && action.getSexAreaInteractions().values().contains(SexAreaPenetration.PENIS)))
				&& ((Main.sex.getOngoingActionsMap(performer).containsKey(SexAreaPenetration.CLIT) && Main.sex.getOngoingActionsMap(performer).get(SexAreaPenetration.CLIT).values().stream().anyMatch((set)->set.contains(SexAreaPenetration.CLIT)))
					|| (Main.sex.getOngoingActionsMap(target).containsKey(SexAreaPenetration.CLIT) && Main.sex.getOngoingActionsMap(target).get(SexAreaPenetration.CLIT).values().stream().anyMatch((set)->set.contains(SexAreaPenetration.CLIT))))) {
				return true;
			}
			
			if(!(Main.sex.getSexPositionSlot(performer).hasTag(SexSlotTag.SIXTY_NINE) && Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.LYING_DOWN))
					&& !(Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.SIXTY_NINE) && Main.sex.getSexPositionSlot(performer).hasTag(SexSlotTag.LYING_DOWN))) {
				boolean ongoingGroinToGroin = false;
				boolean ongoingGroinToBreasts = false;
				boolean ongoingGroinToMouth = false;
				
				for(SexAreaInterface sArea : SexActionPresets.groinAreas) {
					// Groin-groin actions:
					if((Main.sex.getOngoingActionsMap(target).containsKey(sArea)
							&& Main.sex.getOngoingActionsMap(target).get(sArea).containsKey(performer)
							&& !Collections.disjoint(Main.sex.getOngoingActionsMap(target).get(sArea).get(performer), SexActionPresets.groinAreas))
						|| (Main.sex.getOngoingActionsMap(performer).containsKey(sArea)
							&& Main.sex.getOngoingActionsMap(performer).get(sArea).containsKey(target)
							&& !Collections.disjoint(Main.sex.getOngoingActionsMap(performer).get(sArea).get(target), SexActionPresets.groinAreas))) {
						ongoingGroinToGroin = true;
					}
					// Groin-breast actions:
					if((Main.sex.getOngoingActionsMap(target).containsKey(sArea)
							&& Main.sex.getOngoingActionsMap(target).get(sArea).containsKey(performer)
							&& !Collections.disjoint(Main.sex.getOngoingActionsMap(target).get(sArea).get(performer), SexActionPresets.breastAreas))
						|| (Main.sex.getOngoingActionsMap(performer).containsKey(sArea)
							&& Main.sex.getOngoingActionsMap(performer).get(sArea).containsKey(target)
							&& !Collections.disjoint(Main.sex.getOngoingActionsMap(performer).get(sArea).get(target), SexActionPresets.breastAreas))) {
						ongoingGroinToBreasts = true;
					}
					// Groin-mouth actions:
					if((Main.sex.getOngoingActionsMap(target).containsKey(sArea)
							&& Main.sex.getOngoingActionsMap(target).get(sArea).containsKey(performer)
							&& !Collections.disjoint(Main.sex.getOngoingActionsMap(target).get(sArea).get(performer), SexActionPresets.mouthAreas))
						|| (Main.sex.getOngoingActionsMap(performer).containsKey(sArea)
							&& Main.sex.getOngoingActionsMap(performer).get(sArea).containsKey(target)
							&& !Collections.disjoint(Main.sex.getOngoingActionsMap(performer).get(sArea).get(target), SexActionPresets.mouthAreas))) {
						ongoingGroinToMouth = true;
					}
				}
				
				// Block oral + groin actions if there is any groin-groin or groin-breast action going on:
				if((!Collections.disjoint(action.getSexAreaInteractions().keySet(), SexActionPresets.groinAreas) && !Collections.disjoint(action.getSexAreaInteractions().values(), SexActionPresets.mouthAreas))
						|| (!Collections.disjoint(action.getSexAreaInteractions().values(), SexActionPresets.groinAreas) && !Collections.disjoint(action.getSexAreaInteractions().keySet(), SexActionPresets.mouthAreas))) {
					return ongoingGroinToGroin || ongoingGroinToBreasts;
				}
				// Block groin + breast actions if there is any groin-mouth or groin-groin action going on:
				if((!Collections.disjoint(action.getSexAreaInteractions().keySet(), SexActionPresets.groinAreas) && !Collections.disjoint(action.getSexAreaInteractions().values(), SexActionPresets.breastAreas))
						|| (!Collections.disjoint(action.getSexAreaInteractions().values(), SexActionPresets.groinAreas) && !Collections.disjoint(action.getSexAreaInteractions().keySet(), SexActionPresets.breastAreas))) {
					return ongoingGroinToGroin || ongoingGroinToMouth;
				}
			}
		}
		
		return false;
	}
	
	public int getMaximumSlots() {
		return maximumSlots;
	}
//		Set<SexSlot> uniqueSlots = new HashSet<>();
//		
//		for(Entry<SexSlot, Map<SexSlot, SexActionInteractions>> e : getSlotTargets().entrySet()) {
//			uniqueSlots.add(e.getKey());
//			uniqueSlots.addAll(e.getValue().keySet());
//		}
//		
//		return uniqueSlots.size();
//	}

	public Set<SexSlot> getAllAvailableSexPositions() {
		Set<SexSlot> positions = new HashSet<>(getSlotTargets().keySet());
		
		getSlotTargets().entrySet().stream().forEach(e -> positions.addAll(e.getValue().keySet()));
		
		return positions;
	}

	/**
	 * Key is role position. Value is list of all slots that this slot can interact with.
	 */
	public abstract Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets();
	
	protected static Map<SexSlot, Map<SexSlot, SexActionInteractions>> generateSlotTargetsMap(List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> values) {
		Map<SexSlot, Map<SexSlot, SexActionInteractions>> returnMap = new HashMap<>();
		
		for(Value<SexSlot, Map<SexSlot, SexActionInteractions>> value : values) {
			returnMap.putIfAbsent(value.getKey(), new HashMap<>());
			
			for(Entry<SexSlot, SexActionInteractions> innerValue : value.getValue().entrySet()) {
				returnMap.get(value.getKey()).put(innerValue.getKey(), innerValue.getValue());
			}
		}
//		System.out.println("size: "+returnMap.size());
		return returnMap;
	}
	
	public SexActionInteractions getSexInteractions(SexSlot performer, SexSlot target) {
		if(getSlotTargets().containsKey(performer) && getSlotTargets().get(performer).containsKey(target)) {
			return getSlotTargets().get(performer).get(target);
		}
		
		// If the targeted sex position is not defined, allow cumming on floor:
		return new SexActionInteractions(null,
				Util.newArrayListOfValues(OrgasmCumTarget.FLOOR),
				Util.newArrayListOfValues(OrgasmCumTarget.FLOOR));
	}
	
	/**
	 * Override to set limitations on the amount of penetration types allowed in this position.
	 * For example, the 'Mating Press' position defines fingers(i.e. hands) as being bottomMax-topMax for bottom, and topMax-bottomMax for top, as the one on top is pinning down as many of the bottom's hands as they can.
	 * @return A map of penetration types, the value to which they are mapped representing the penetration count modifier. The value should always be a negative integer.
	 */
	public Map<SexAreaPenetration, Integer> getRestrictedPenetrationCounts(GameCharacter penetrator) {
		return null;
	}

	/**
	 * The underlying map of body parts to orifice lists which is used in the public method isForcedCreampieEnabled(). This is overridden in SexPositionTypes that need to define forced creampies.
	 * 
	 * @param cumTarget The character who is both receiving and forcing the creampie.
	 * @param cumProvider The one who is being forced to cum inside the cumTarget.
	 * @return A map containing keys of body parts, which then map to lists of orifices.
	 * The key represents the body part that can be used by the cumTarget in order to force the cumProvider to cum inside any of the orifices in the value list.
	 */
	protected Map<Class<? extends BodyPartInterface>, List<SexAreaOrifice>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
		return null;
	}
	
	/**
	 * Taking into account the AbstractSexSlot of the two characters specified, as well as the body part being used, this method returns a list of areas which the cumTarget can force the cumProvider to cum inside of.
	 * This is used in determining whether the 'leg-lock', 'tail-lock', 'tentacle-lock', or 'force creampie' actions (in the GenericOrgasms class) are available.
	 * 
	 * @param bodyPartUsed The body part which the cumTarget is using to force the creampie. Will almost certainly be either:<br/>
	 * <b>{@link Arm.class}:</b> Always requires at least two free arms, and for arms to not be restricted.<br/>
	 * <b>{@link Leg.class}:</b> Always requires at least two free legs, and for legs to not be restricted.<br/>
	 * <b>{@link Tail.class}:</b> Requires tail(s), and for them to be prehensile.<br/>
	 * <b>{@link Tentacle.class}:</b> Requires tentacle(s).<br/>
	 * <b>{@link Skin.class}:</b> Used to represent the full body being used.
	 * @param orifice The orifice into which the creampie is to be forced.
	 * @param cumTarget The character who is both receiving and forcing the creampie.
	 * @param cumProvider The one who is being forced to cum inside the cumTarget.
	 * @return True if the orifice can be forcibly creampied by the supplied body part.
	 */
	public boolean isForcedCreampieEnabled(Class<? extends BodyPartInterface> bodyPartUsed, SexAreaOrifice orifice, GameCharacter cumTarget, GameCharacter cumProvider) {
		if(getForcedCreampieMap(cumTarget, cumProvider)!=null && getForcedCreampieMap(cumTarget, cumProvider).containsKey(bodyPartUsed)) {
			return getForcedCreampieMap(cumTarget, cumProvider).get(bodyPartUsed).contains(orifice);
		}
		return false;
	}
	
	/**
	 * Default is false, but can be overridden to allow characters to perform autofellatio or autocunnilingus.
	 */
	public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
		return false;
	}
}
