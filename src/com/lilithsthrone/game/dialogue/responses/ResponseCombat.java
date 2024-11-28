package com.lilithsthrone.game.dialogue.responses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.69
 * @version 0.4.4
 * @author Innoxia
 */
public class ResponseCombat extends Response {

	private List<NPC> allies;
	private NPC enemyLeader;
	private List<NPC> enemies;
	
	private Map<GameCharacter, String> openingDescriptions;
	
	// For use when response is loaded from external file:

	private List<String> alliesIds;
	private boolean addCompanionsToAllies;
	private boolean addElementalsToAllies;
	private String enemyLeaderId;
	private List<String> enemiesIds;
	
	private String nextDialoguePlayerVictoryId;
	private String nextDialoguePlayerDefeatId;

	private Map<String, String> openingDescriptionsUsingIds;
	private boolean escapeBlocked = false;
	private boolean submitBlocked = false;
	
	
	public ResponseCombat(String title, String tooltipText, NPC opponent) {
		super(title, tooltipText, null);
		this.allies = new ArrayList<>();
		for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
			this.allies.add((NPC) companion);
		}
		addElementalsToAllies = true;
		
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
		addElementalsToAllies = true;
		
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
		addElementalsToAllies = true;
		
		this.enemyLeader = enemyLeader;

		// Irbynx's note:
		// Assuming this function overload is used for very specific combat instances in mind. To add companions to equation, just pass them mixed in with the lists
		this.enemies = new ArrayList<>();
		if(!enemies.contains(enemyLeader)) {
			this.enemies.add(enemyLeader);
		}
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
		addElementalsToAllies = true;
		
		this.enemyLeader = enemyLeader;

		// Irbynx's note:
		// Assuming this function overload is used for very specific combat instances in mind. To add companions to equation, just pass them mixed in with the lists
		this.enemies = new ArrayList<>();
		if(!enemies.contains(enemyLeader)) {
			this.enemies.add(enemyLeader);
		}
		for(GameCharacter enemy : enemies) {
			this.enemies.add((NPC) enemy);
		}
		
		if(openingDescriptions!=null) {
			this.openingDescriptions = openingDescriptions;
		}
	}
	
	
	public ResponseCombat(String title,
			String tooltipText,
			List<String> alliesIds,
			boolean addCompanionsToAllies,
			boolean addElementalsToAllies,
			String enemyLeaderId,
			List<String> enemiesIds,
			Map<String, String> openingDescriptionsUsingIds,
			String effectsResponse,
			boolean escapeBlocked,
			boolean submitBlocked) {
		super(title, tooltipText, null);
		this.fromExternalFile = true;
		
		this.alliesIds = alliesIds;
		this.addCompanionsToAllies = addCompanionsToAllies;
		this.addElementalsToAllies = addElementalsToAllies;
		this.enemyLeaderId = enemyLeaderId;
		this.enemiesIds = enemiesIds;
		
		if(openingDescriptionsUsingIds!=null) {
			this.openingDescriptionsUsingIds = openingDescriptionsUsingIds;
		}
		
		this.effectsString = effectsResponse;
		
		this.escapeBlocked = escapeBlocked;
		this.submitBlocked = submitBlocked;
	}
	
	@Override
	public boolean isCombatHighlight() {
		return true;
	}

	public DialogueNode initCombat() {
		if(enemyLeaderId!=null && !enemyLeaderId.isEmpty()) {
			this.allies = new ArrayList<>();
			for(String allyId : alliesIds) {
				String id = UtilText.parse(allyId).trim();
				if(!id.isEmpty()) {
					this.allies.add((NPC) UtilText.findFirstCharacterFromParserTarget(id));
				}
			}
			if(addCompanionsToAllies) {
				for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
					this.allies.add((NPC) companion);
				}
			}
			
			this.enemyLeader = (NPC) UtilText.findFirstCharacterFromParserTarget(UtilText.parse(enemyLeaderId).trim());
			
			this.enemies = new ArrayList<>();
			for(String enemyId : enemiesIds) {
				String id = UtilText.parse(enemyId).trim();
				if(!id.isEmpty()) {
					this.enemies.add((NPC) UtilText.findFirstCharacterFromParserTarget(id));
				}
			}
			if(!enemies.contains(enemyLeader)) {
				enemies.add(enemyLeader);
			}
			
			this.openingDescriptions = new HashMap<>();
			for(Entry<String, String> entry : openingDescriptionsUsingIds.entrySet()) {
				openingDescriptions.put(UtilText.findFirstCharacterFromParserTarget(entry.getKey()), entry.getValue());
			}
			
			Main.combat.initialiseCombat(allies, addElementalsToAllies, enemyLeader, enemies, openingDescriptions, escapeBlocked, submitBlocked);
			Main.combat.setPlayerPostVictoryDialogue(DialogueManager.getDialogueFromId(UtilText.parse(nextDialoguePlayerVictoryId).trim()));
			Main.combat.setPlayerPostDefeatDialogue(DialogueManager.getDialogueFromId(UtilText.parse(nextDialoguePlayerDefeatId).trim()));
			
		} else {
			Main.combat.initialiseCombat(allies, addElementalsToAllies, enemyLeader, enemies, openingDescriptions);
		}
		
		return Main.combat.startCombat();
	}
	
	@Override
	public boolean disabledOnNullDialogue(){
		return false;
	}

	public List<String> getAlliesIds() {
		return alliesIds;
	}

	public void setAlliesIds(List<String> alliesIds) {
		this.alliesIds = alliesIds;
	}

	public String getEnemyLeaderId() {
		return enemyLeaderId;
	}

	public void setEnemyLeaderId(String enemyLeaderId) {
		this.enemyLeaderId = enemyLeaderId;
	}

	public List<String> getEnemiesIds() {
		return enemiesIds;
	}

	public void setEnemiesIds(List<String> enemiesIds) {
		this.enemiesIds = enemiesIds;
	}

	public String getNextDialoguePlayerVictoryId() {
		return nextDialoguePlayerVictoryId;
	}

	public void setNextDialoguePlayerVictoryId(String nextDialoguePlayerVictoryId) {
		this.nextDialoguePlayerVictoryId = nextDialoguePlayerVictoryId;
	}

	public String getNextDialoguePlayerDefeatId() {
		return nextDialoguePlayerDefeatId;
	}

	public void setNextDialoguePlayerDefeatId(String nextDialoguePlayerDefeatId) {
		this.nextDialoguePlayerDefeatId = nextDialoguePlayerDefeatId;
	}
}
