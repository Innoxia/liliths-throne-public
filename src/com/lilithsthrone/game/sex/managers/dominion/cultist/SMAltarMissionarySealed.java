package com.lilithsthrone.game.sex.managers.dominion.cultist;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.88
 * @version 0.1.97
 * @author Innoxia
 */
public class SMAltarMissionarySealed extends SexManagerDefault {

	public SMAltarMissionarySealed(Map<GameCharacter, SexPositionSlot> dominants, Map<GameCharacter, SexPositionSlot> submissives) {
		super(SexPositionType.MISSIONARY_ALTAR_SEALED_CULTIST,
				dominants,
				submissives);
	}

	@Override
	public boolean isPlayerCanRemoveOwnClothes(){
		return !((Cultist)Sex.getActivePartner()).isSealedSex() || Sex.isDom(Main.game.getPlayer());
	}
	
	@Override
	public boolean isPlayerCanRemovePartnersClothes(){
		return !((Cultist)Sex.getActivePartner()).isSealedSex() || Sex.isDom(Main.game.getPlayer());
	}
	
	@Override
	public boolean isPartnerCanRemoveOwnClothes(){
		return !((Cultist)Sex.getActivePartner()).isSealedSex() || Sex.isDom(Sex.getActivePartner());
	}
	
	@Override
	public boolean isPartnerCanRemovePlayersClothes(){
		return !((Cultist)Sex.getActivePartner()).isSealedSex() || Sex.isDom(Sex.getActivePartner());
	}
}
