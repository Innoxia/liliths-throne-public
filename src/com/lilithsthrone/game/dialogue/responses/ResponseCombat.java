package com.lilithsthrone.game.dialogue.responses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.69
 * @version 0.2.4
 * @author Innoxia
 */
public class ResponseCombat extends Response {

	private List<NPC> allies;
	private NPC enemyLeader;
	private List<NPC> enemies;
	
	private Map<GameCharacter, String> openingDescriptions;
	
	public ResponseCombat(String title, String tooltipText, NPC opponent) {
		super(title, tooltipText, null);
		this.allies = new ArrayList<>();
		for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
			this.allies.add((NPC) companion);
		}
		
		this.enemyLeader = opponent;
		
		this.enemies = new ArrayList<>();
		this.enemies.add(opponent);
		for(GameCharacter companion : opponent.getCompanions()) {
			this.enemies.add((NPC) companion);
		}
		
		this.openingDescriptions = new HashMap<>();
	}
	
	public ResponseCombat(String title, String tooltipText, NPC opponent, Map<GameCharacter, String> openingDescriptions) {
		super(title, tooltipText, null);
		this.allies = new ArrayList<>();
		for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
			this.allies.add((NPC) companion);
		}
		
		this.enemyLeader = opponent;
		
		this.enemies = new ArrayList<>();
		this.enemies.add(opponent);
		for(GameCharacter companion : opponent.getCompanions()) {
			this.enemies.add((NPC) companion);
		}
		
		if(openingDescriptions!=null) {
			this.openingDescriptions = openingDescriptions;
		}
	}
	
	public ResponseCombat(String title, String tooltipText, NPC enemyLeader, List<GameCharacter> enemies, Map<GameCharacter, String> openingDescriptions) {
		super(title, tooltipText, null);
		this.allies = new ArrayList<>();
		for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
			this.allies.add((NPC) companion);
		}
		
		this.enemyLeader = enemyLeader;

		// Irbynx's note:
		// Assuming this function overload is used for very specific combat instances in mind. To add companions to equation, just pass them mixed in with the lists
		this.enemies = new ArrayList<>();
		for(GameCharacter enemy : enemies) {
			this.enemies.add((NPC) enemy);
		}
		
		if(openingDescriptions!=null) {
			this.openingDescriptions = openingDescriptions;
		}
	}
	
	public ResponseCombat(String title, String tooltipText, List<GameCharacter> allies, NPC enemyLeader, List<GameCharacter> enemies, Map<GameCharacter, String> openingDescriptions) {
		super(title, tooltipText, null);
		this.allies = new ArrayList<>();
		if(allies!=null) {
			for(GameCharacter companion : allies) {
				this.allies.add((NPC) companion);
			}
		}
		
		this.enemyLeader = enemyLeader;

		// Irbynx's note:
		// Assuming this function overload is used for very specific combat instances in mind. To add companions to equation, just pass them mixed in with the lists
		this.enemies = new ArrayList<>();
		for(GameCharacter enemy : enemies) {
			this.enemies.add((NPC) enemy);
		}
		
		if(openingDescriptions!=null) {
			this.openingDescriptions = openingDescriptions;
		}
	}

	@Override
	public boolean isCombatHighlight() {
		return true;
	}

	public DialogueNode initCombat() {
		Combat.COMBAT.initialiseCombat(allies, enemyLeader, enemies, openingDescriptions);
		return Combat.COMBAT.startCombat();
	}
	
	@Override
	public boolean disabledOnNullDialogue(){
		return false;
	}
}
