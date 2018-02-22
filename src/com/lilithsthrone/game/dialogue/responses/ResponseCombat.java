package com.lilithsthrone.game.dialogue.responses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;

/**
 * @since 0.1.69
 * @version 0.1.99
 * @author Innoxia
 */
public class ResponseCombat extends Response {

	private List<NPC> allies;
	private List<NPC> enemies;
	
	private Map<GameCharacter, String> openingDescriptions;
	
	public ResponseCombat(String title, String tooltipText, NPC opponent) {
		super(title, tooltipText, null);
		this.allies = new ArrayList<>();
		this.enemies = new ArrayList<>();
		this.enemies.add(opponent);
		
		this.openingDescriptions = new HashMap<>();
	}
	
	public ResponseCombat(String title, String tooltipText, NPC opponent, Map<GameCharacter, String> openingDescriptions) {
		super(title, tooltipText, null);
		this.allies = new ArrayList<>();
		this.enemies = new ArrayList<>();
		this.enemies.add(opponent);
		
		if(openingDescriptions!=null) {
			this.openingDescriptions = openingDescriptions;
		}
	}
	
	public ResponseCombat(String title, String tooltipText, List<NPC> allies, List<NPC> enemies, Map<GameCharacter, String> openingDescriptions) {
		super(title, tooltipText, null);
		this.allies = allies;
		this.enemies = enemies;
		
		if(openingDescriptions!=null) {
			this.openingDescriptions = openingDescriptions;
		}
	}

	@Override
	public boolean isCombatHighlight() {
		return true;
	}

	public DialogueNodeOld initCombat() {
		Combat.COMBAT.initialiseCombat(allies, enemies, openingDescriptions);
		return Combat.COMBAT.startCombat();
	}
	
	@Override
	public boolean disabledOnNullDialogue(){
		return false;
	}
}
