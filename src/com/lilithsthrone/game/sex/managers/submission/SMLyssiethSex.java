package com.lilithsthrone.game.sex.managers.submission;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.main.Main;

/**
 * @since 0.3
 * @version 0.3.4
 * @author Innoxia
 */
public class SMLyssiethSex extends SexManagerDefault {

	public SMLyssiethSex(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPosition.OVER_DESK,
				dominants,
				submissives);
	}

	@Override
	public boolean isPartnerWantingToStopSex(GameCharacter partner) {
		if(!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN)) {
			return false; // Lyssieth orgasm ends sex, so this is fine
		}
		return super.isPartnerWantingToStopSex(partner);
	}
	
	@Override
	public boolean isPlayerAbleToStopSex() {
		return Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN);
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
	public SexPace getForcedSexPace(GameCharacter character) {
		if(!character.isPlayer()) {
			if(Main.game.getPlayer().getRace()==Race.HUMAN) {
				if(Sex.isDom(character)) {
					return SexPace.DOM_NORMAL;
				} else {
					return SexPace.SUB_EAGER;
				}
				
			} else {
				if(Sex.isDom(character)) {
					return SexPace.DOM_NORMAL;
				} else {
					return SexPace.SUB_NORMAL;
				}
			}
		}
		return null;
	}
}
