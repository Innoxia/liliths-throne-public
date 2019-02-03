package com.lilithsthrone.game.sex.positions;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexActionInteractions;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionPresets;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
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
 * @version 0.3.1
 * @author Innoxia
 */
public abstract class AbstractSexPosition {

	private String name;
	private boolean addStandardActions;
	
	/**Key is role position. Value is list of all slots that this slot can interact with.*/
	private Map<SexSlot, Map<SexSlot, SexActionInteractions>> slotTargets;
	
	private List<Class<?>> positioningClasses;
	private List<Class<?>> specialClasses;
	
	public static List<SexAreaOrifice> genericGroinForceCreampieAreas = Util.newArrayListOfValues(SexAreaOrifice.ANUS, SexAreaOrifice.VAGINA, SexAreaOrifice.URETHRA_VAGINA, SexAreaOrifice.URETHRA_PENIS);
	public static List<SexAreaOrifice> genericFaceForceCreampieAreas = Util.newArrayListOfValues(SexAreaOrifice.MOUTH);
	
	public AbstractSexPosition(String name,
			boolean addStandardActions,
			List<Class<?>> positioningClasses,
			List<Class<?>> specialClasses,
			Map<SexSlot, Map<SexSlot, SexActionInteractions>> slotTargets) {
		this.name = name;
		this.addStandardActions = addStandardActions;
		this.positioningClasses = positioningClasses;
		this.specialClasses = specialClasses;
		this.slotTargets = slotTargets;
	}
	
	public String getName() {
		return name;
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

	public abstract String getDescription();
	
	public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
		if(action.getActionType()==SexActionType.START_ONGOING) {
			// Block penis+non-appendage actions if target's penis is already in use:
			if(action.getSexAreaInteractions().containsKey(SexAreaPenetration.PENIS)
					&& Collections.disjoint(action.getSexAreaInteractions().values(), SexActionPresets.appendageAreas)
					&& Sex.getOngoingActionsMap(target).containsKey(SexAreaPenetration.PENIS)
					&& Sex.getOngoingActionsMap(target).get(SexAreaPenetration.PENIS).containsKey(performer)
					&& Collections.disjoint(Sex.getOngoingActionsMap(target).get(SexAreaPenetration.PENIS).get(performer), SexActionPresets.appendageAreas)) {
				return true;
			}
			if(action.getSexAreaInteractions().containsValue(SexAreaPenetration.PENIS)
					&& Collections.disjoint(action.getSexAreaInteractions().keySet(), SexActionPresets.appendageAreas)
					&& Sex.getOngoingActionsMap(performer).containsKey(SexAreaPenetration.PENIS)
					&& Sex.getOngoingActionsMap(performer).get(SexAreaPenetration.PENIS).containsKey(target)
					&& Collections.disjoint(Sex.getOngoingActionsMap(performer).get(SexAreaPenetration.PENIS).get(target), SexActionPresets.appendageAreas)) {
				return true;
			}
			
			// Block oral + groin actions if there is any groin-groin action going on:
			if(((!Collections.disjoint(action.getSexAreaInteractions().keySet(), SexActionPresets.groinAreas)
							&& !Collections.disjoint(action.getSexAreaInteractions().values(), SexActionPresets.mouthAreas))
						|| (!Collections.disjoint(action.getSexAreaInteractions().values(), SexActionPresets.groinAreas)
							&& !Collections.disjoint(action.getSexAreaInteractions().keySet(), SexActionPresets.mouthAreas)))) {
				for(SexAreaInterface sArea : SexActionPresets.groinAreas) {
					if((Sex.getOngoingActionsMap(target).containsKey(sArea)
							&& Sex.getOngoingActionsMap(target).get(sArea).containsKey(performer)
							&& !Collections.disjoint(Sex.getOngoingActionsMap(target).get(sArea).get(performer), SexActionPresets.groinAreas))
						|| (Sex.getOngoingActionsMap(performer).containsKey(sArea)
							&& Sex.getOngoingActionsMap(performer).get(sArea).containsKey(target)
							&& !Collections.disjoint(Sex.getOngoingActionsMap(performer).get(sArea).get(target), SexActionPresets.groinAreas))) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public int getMaximumSlots() {
		return getSlotTargets().size();
	}

	public Set<SexSlot> getAllAvailableSexPositions() {
		return getSlotTargets().keySet();
	}

	public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
		return slotTargets;
	}
	
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
		return new SexActionInteractions(null, Util.newArrayListOfValues(OrgasmCumTarget.FLOOR));
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
	 * @param cumProvidor The one who is being forced to cum inside the cumTarget.
	 * @return A map containing keys of body parts, which then map to lists of orifices.
	 * The key represents the body part that can be used by the cumTarget in order to force the cumcumProvidor to cum inside any of the orifices in the value list.
	 */
	protected Map<Class<? extends BodyPartInterface>, List<SexAreaOrifice>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvidor) {
		return null;
	}
	
	/**
	 * Taking into account the AbstractSexSlot of the two characters specified, as well as the body part being used, this method returns a list of areas which the cumTarget can force the cumProvidor to cum inside of.
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
	 * @param cumProvidor The one who is being forced to cum inside the cumTarget.
	 * @return True if the orifice can be forcibly creampied by the supplied body part.
	 */
	public boolean isForcedCreampieEnabled(Class<? extends BodyPartInterface> bodyPartUsed, SexAreaOrifice orifice, GameCharacter cumTarget, GameCharacter cumProvidor) {
		if(getForcedCreampieMap(cumTarget, cumProvidor)!=null && getForcedCreampieMap(cumTarget, cumProvidor).containsKey(bodyPartUsed)) {
			return getForcedCreampieMap(cumTarget, cumProvidor).get(bodyPartUsed).contains(orifice);
		}
		return false;
	}
}
