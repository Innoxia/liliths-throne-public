package com.lilithsthrone.game.sex.positions.slots;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexActionInteractions;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.VariableInteractions;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PositioningMenu;
import com.lilithsthrone.main.Main;

/**
 * Slots which characters can occupy in sex positions.
 * 
 * @since 0.3.1
 * @version 0.3.4
 * @author Innoxia
 */
public class SexSlot {
	
	private String name;
	private String description;
	private String orgasmDescription;
	private boolean standing;
	private List<SexSlotTag> tags;
	
	public SexSlot(String name, String description, String orgasmDescription, boolean standing, SexSlotTag... tags) {
		this.name = name;
		this.description = description;
		this.orgasmDescription = orgasmDescription;
		this.standing = standing;
		this.tags = Arrays.asList(tags);
	}
	
	/**
	 * <b>Should only be used for basic slots!</b> This is due to the fact that the 'name' variable is set via a getName(null) call, and the 'standing' variable is set via a isStanding(null) call.
	 * @param slotToCopy
	 */
	public SexSlot(SexSlot slotToCopy) {
		this.name = slotToCopy.name;
		this.description = slotToCopy.description;
		this.orgasmDescription = slotToCopy.orgasmDescription;
		this.standing = slotToCopy.standing;
		this.tags = new ArrayList<>(slotToCopy.getTags());
	}

	/**
	 * @param target The person in this slot.
	 */
	public String getName(GameCharacter target) {
		return UtilText.parse(name);
	}

	/**
	 * @return The description for this slot. Should be unique to sex position so that player knows exactly which slot this is in any tooltips.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @return The description of the character in this slot orgasming. 'npc' is the parser tag used for the orgasming character, while 'npc2' is for the targeted character that they're interacting with.<br/>
	 * If orgasmDescription is null, getGenericOrgasmDescription() is returned instead.
	 */
	public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
		if(orgasmingCharacter.equals(targetedCharacter)) {
			return "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.";
		}
		if(orgasmDescription==null) {
			return getGenericOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
		return orgasmDescription;
	}
	
	/**
	 * @return A generic orgasm description, based on who the character in this slot is targeting. Will be returned if orgasmDescription is null.
	 */
	private String getGenericOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
		if(orgasmingCharacter.equals(targetedCharacter)) {
			return UtilText.parse(orgasmingCharacter, targetedCharacter,
					"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.");
		} else {
			return UtilText.parse(orgasmingCharacter, targetedCharacter,
					"Pressing [npc.herself] against [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.");
		}
	}

	/**
	 * @return A generic end of sex description, based on who the character in this slot is targeting. Should be overridden if a SexSlot needs a unique end-of-sex description.
	 */
	public String getGenericEndSexDescription(GameCharacter endingCharacter, GameCharacter targetedCharacter) {
		if(Main.game.isInSex() && Main.sex.isMasturbation()) {
			return UtilText.parse(endingCharacter, "Deciding that [npc.sheHas] had enough for now, [npc.name] [npc.verb(put)] an end to [npc.her] masturbation session and [npc.verb(prepare)] to continue on [npc.her] way.");
		}

		if(targetedCharacter.isAsleep()) {
			return UtilText.parse(endingCharacter, targetedCharacter,
					"With a satisfied sigh, [npc.name] [npc.verb(separate)] [npc.herself] from [npc2.name], making sure not to wake [npc2.herHim] up in the process.");
		}
		return UtilText.parse(endingCharacter, targetedCharacter,
				"With a satisfied sigh, [npc.name] [npc.verb(disentangle)] [npc.herself] from [npc2.namePos] clutches, before stating that [npc.sheHas] had enough for now.");
	}
	
	
	/**
	 * Used to check whether the position can use both feet or just one. (If standing, can only use one.)
	 * @param target The person in this slot.
	 * @return
	 */
	public boolean isStanding(GameCharacter target) {
		return standing;
	}
	
	public List<SexSlotTag> getTags() {
		return tags;
	}
	
	public boolean hasTag(SexSlotTag tag) {
		return tags.contains(tag);
	}
	
	protected GameCharacter getCharacterInSlot(SexSlot targetedSlot) {
		if(Main.game.getCurrentDialogueNode()==PositioningMenu.POSITIONING_MENU) {
			for(Entry<GameCharacter, SexSlot> e : PositioningMenu.positioningSlots.entrySet()) {
				if(e.getValue()==targetedSlot) {
					return e.getKey();
				}
			}
		}
		
		return Main.sex.getCharacterInPosition(targetedSlot);
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
