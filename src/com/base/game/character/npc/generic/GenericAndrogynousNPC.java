package com.base.game.character.npc.generic;

import com.base.game.character.NameTriplet;
import com.base.game.character.gender.Gender;
import com.base.game.character.npc.NPC;
import com.base.game.character.race.RaceStage;
import com.base.game.character.race.RacialBody;
import com.base.game.combat.Attack;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.responses.Response;
import com.base.game.inventory.CharacterInventory;
import com.base.world.WorldType;
import com.base.world.places.Jungle;

/**
 * @since 0.1.79
 * @version 0.1.79
 * @author Innoxia
 */
public class GenericAndrogynousNPC extends NPC {

	private static final long serialVersionUID = 1L;

	public GenericAndrogynousNPC() {
		super(new NameTriplet("Androgynous"), "Generic androgynous.",
				1, Gender.FUTANARI, RacialBody.HUMAN, RaceStage.HUMAN,
				new CharacterInventory(0), WorldType.JUNGLE, Jungle.JUNGLE_CLUB, true); //TODO move to null tile
		
		this.setFemininity(50);
	}

	@Override
	public void applyReset() {
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}

	@Override
	public void endSex(boolean applyEffects) {
	}
	
	@Override
	public String getCombatDescription() {
		return null;
	}
	@Override
	public String getAttackDescription(Attack attackType, boolean isHit) {
		return null;
	}
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		return null;
	}
	@Override
	public Attack attackType() {
		return null;
	}
	@Override
	public int getExperienceFromVictory() {
		return 0;
	}

}