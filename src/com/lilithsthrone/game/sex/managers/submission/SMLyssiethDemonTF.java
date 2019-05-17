package com.lilithsthrone.game.sex.managers.submission;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3
 * @version 0.3.1
 * @author Innoxia
 */
public class SMLyssiethDemonTF extends SexManagerDefault {

	public SMLyssiethDemonTF(AbstractSexPosition sexPositionType, Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(sexPositionType,
				dominants,
				submissives);
	}

	@Override
	public boolean isPartnerWantingToStopSex(GameCharacter partner) {
		return Sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))>=3;
	}
	
	@Override
	public boolean isPlayerAbleToStopSex() {
		return false;
	}
	
	@Override
	public boolean isPlayerAbleToSwapPositions() {
		return false;
	}
	
	@Override
	public boolean isPositionChangingAllowed(GameCharacter character) {
		return false;
	}

	@Override
	public boolean isPlayerStartNaked() {
		return true;
	}

	@Override
	public boolean isPartnerStartNaked() {
		return true;
	}
	
	@Override
	public boolean isSelfTransformDisabled(GameCharacter character) {
		return true;
	}

	@Override
	public boolean isAbleToEquipSexClothing(GameCharacter character){
		return false;
	}
	
	@Override
	public SexControl getSexControl(GameCharacter character) {
		if(character.isPlayer()) {
			return SexControl.ONGOING_ONLY;
		} else {
			return SexControl.FULL;
		}
	}
	
	@Override
	public SexPace getForcedSexPace(GameCharacter character) {
		if(!character.isPlayer()) {
			if(!Sex.isDom(character)) {
				return SexPace.SUB_EAGER;
			}
			return SexPace.DOM_NORMAL;
		}
		return null;
	}
	
	@Override
	public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap() {
		// Limit finger during giving the player head, so that she can use hug-lock and prevent the player from pulling out:
		if(Sex.getContactingSexAreas(Main.game.getNpc(Lyssieth.class), SexAreaOrifice.MOUTH, Main.game.getPlayer()).contains(SexAreaPenetration.PENIS)) {
			return Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), Util.newArrayListOfValues(SexAreaPenetration.FINGER)));
		}
		// Limit tail during doggy style, so that she can use tail-lock and prevent the player from pulling out:
		if(Sex.getContactingSexAreas(Main.game.getNpc(Lyssieth.class), SexAreaOrifice.VAGINA, Main.game.getPlayer()).contains(SexAreaPenetration.PENIS)
				&& Sex.getPosition()==SexPositionBipeds.DOGGY_STYLE) {
			return Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), Util.newArrayListOfValues(SexAreaPenetration.TAIL)));
		}
		return super.getAreasBannedMap();
	}
	
}
