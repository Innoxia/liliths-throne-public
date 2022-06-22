package com.lilithsthrone.game.sex.managers.submission;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3
 * @version 0.3.5.5
 * @author Innoxia
 */
public class SMLilayaDemonTF extends SexManagerDefault {

	public SMLilayaDemonTF(AbstractSexPosition sexPositionType, Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(false,
				sexPositionType,
				dominants,
				submissives);
	}

	@Override
	public boolean isPartnerWantingToStopSex(GameCharacter partner) {
		if(partner instanceof Lyssieth) { // Lyssieth stops sex once everyone's orgasmed:
			for(GameCharacter character : Main.sex.getAllParticipants(false)) {
				if(Main.sex.getNumberOfOrgasms(character)==0 && character.isAbleToOrgasm()) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isPlayerAbleToStopSex() {
		return false;
	}
	
	@Override
	public boolean isEndSexAffectionChangeEnabled(GameCharacter character) {
		return false;
	}
	
	@Override
	public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
		return false;
	}
	
	@Override
	public boolean isPositionChangingAllowed(GameCharacter character) {
		return false;
	}

	@Override
	public boolean isCharacterStartNaked(GameCharacter character) {
		return true;
	}
	
	@Override
	public boolean isSelfTransformDisabled(GameCharacter character) {
		return true;
	}

	@Override
	public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip){
		return false;
	}
	
	@Override
	public SexControl getSexControl(GameCharacter character) { // Everyone can just continue with ongoing:
		return SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS;
	}
	
	@Override
	public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
		if(character.equals(Main.game.getNpc(Lyssieth.class))) {
			return OrgasmBehaviour.CREAMPIE;
		}
		return super.getCharacterOrgasmBehaviour(character);
	}
	
	@Override
	public SexPace getForcedSexPace(GameCharacter character) {
		if(!character.isPlayer()) {
			if(!Main.sex.isDom(character)) {
				return SexPace.SUB_EAGER;
			}
		}
		return null;
	}

	@Override
	public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap() {
		// Do not let Meraxis take her own virginity using her tail:
		return Util.newHashMapOfValues(new Value<>(Main.game.getNpc(DarkSiren.class), Util.newArrayListOfValues(SexAreaPenetration.TAIL)));
	}
}
