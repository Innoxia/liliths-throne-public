package com.lilithsthrone.game.sex.positions;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.SexActionInteractions;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexType;

/**
 * Slots which characters can occupy in sex positions.
 * 
 * @since 0.3.1
 * @version 0.3.1
 * @author Innoxia
 */
public class SexSlot {
	
	private String name;
	private String description;
	private String orgasmDescription;
	private boolean standing;
	
	public SexSlot(String name, String description, String orgasmDescription, boolean standing) {
		this.name = name;
		this.description = description;
		this.orgasmDescription = orgasmDescription;
		this.standing = standing;
	}
	
	/**
	 * <b>Should only be used for basic slots!</b> This is due to the fact that the 'name' variable is set via a getName(null) call, and the 'standing' variable is set via a isStanding(null) call.
	 * @param slotToCopy
	 */
	public SexSlot(SexSlot slotToCopy) {
		this.name = slotToCopy.getName(null);
		this.description = slotToCopy.getDescription();
		this.orgasmDescription = slotToCopy.getOrgasmDescription();
		this.standing = slotToCopy.isStanding(null);
	}

	/**
	 * @param target The person in this slot.
	 */
	public String getName(GameCharacter target) {
		return name;
	}

	/**
	 * @param description The description for this slot. Should be unique to sex position so that player knows exactly which slot this is in any tooltips.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * return The description of the character in this slot orgasming. 'npc' is the parser tag used for the orgasming character, while 'npc2' is for the targeted character that they're interacting with.
	 */
	public String getOrgasmDescription() {
		return orgasmDescription;
	}

	/**
	 * Used to check whether the position can use both feet or just one. (If standing, can only use one.)
	 * @param target The person in this slot.
	 * @return
	 */
	public boolean isStanding(GameCharacter target) {
		return standing;
	}
	
	public boolean isMeetsPreferenceCriteria(GameCharacter character, AbstractSexPosition position, SexSlot targetedSlot, SexType preference) {
		VariableInteractions.setCharacterForPositionTesting(character);
		
		if(position.getSlotTargets().containsKey(this)) {
			if(targetedSlot==null) { // If no targeted slot, check all available slots:
				for(SexActionInteractions value : position.getSlotTargets().get(this).values()) {
					for(Entry<SexAreaInterface, List<SexAreaInterface>> interaction : value.getInteractions().entrySet()) {
						if(interaction.getKey()==preference.getPerformingSexArea() && interaction.getValue().contains(preference.getTargetedSexArea())) {
							return true;
						}
					}
				}
				
			} else if(position.getSlotTargets().get(this).containsKey(targetedSlot)) {
				for(Entry<SexAreaInterface, List<SexAreaInterface>> interaction : position.getSlotTargets().get(this).get(targetedSlot).getInteractions().entrySet()) {
					if(interaction.getKey()==preference.getPerformingSexArea() && interaction.getValue().contains(preference.getTargetedSexArea())) {
						return true;
					}
				}
			}
		}
		
		 // Check for the opposite interactions (i.e. the targeted slot interacting with this slot):
		
		if(targetedSlot==null) { // If no targeted slot, check all available slots:
			for(Entry<SexSlot, Map<SexSlot, SexActionInteractions>> value : position.getSlotTargets().entrySet()) {
				for(Entry<SexSlot, SexActionInteractions> entry : value.getValue().entrySet()) {
					if(entry.getKey()==this) {
						for(Entry<SexAreaInterface, List<SexAreaInterface>> interaction : entry.getValue().getInteractions().entrySet()) {
							if(interaction.getKey()==preference.getTargetedSexArea() && interaction.getValue().contains(preference.getPerformingSexArea())) {
								return true;
							}
						}
					}
				}
			}
			
		} else if(position.getSlotTargets().containsKey(targetedSlot)) {
			for(Entry<SexSlot, SexActionInteractions> entry : position.getSlotTargets().get(targetedSlot).entrySet()) {
				if(entry.getKey()==this) {
					for(Entry<SexAreaInterface, List<SexAreaInterface>> interaction : entry.getValue().getInteractions().entrySet()) {
						if(interaction.getKey()==preference.getTargetedSexArea() && interaction.getValue().contains(preference.getPerformingSexArea())) {
							return true;
						}
					}
				}
			}
		}
		
		return false;
	};

	// What was this for?
//	@Override
//	public boolean equals(Object o) {
//		if(o instanceof SexSlot){
//			if(((SexSlot)o).getDescription().equals(getDescription())){
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	@Override
//	public int hashCode() {
//		int result = 0;
//		result = 31 * result + getDescription().hashCode();
//		return result;
//	}
	
}
