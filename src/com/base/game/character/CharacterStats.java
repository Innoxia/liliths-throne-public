package com.base.game.character;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.base.game.character.attributes.AffectionLevel;
import com.base.game.sex.SexType;

/**
 * @since 0.1.??
 * @version 0.1.78
 * @author Innoxia
 */
public class CharacterStats implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Combat stats:
	private int foughtPlayerCount, lostCombatCount, wonCombatCount;
	
	// Relationship stats:
	private Map<GameCharacter, Integer> relationships;
	
	// Sex stats:
	private int sexConsensualCount, sexAsSubCount, sexAsDomCount;
	private Map<SexType, Integer> sexCountMap;
	private Map<SexType, Integer> cumCountMap;
	private Map<SexType, String> virginityLossMap;
	private Map<GameCharacter, Map<SexType, Integer>> sexPartnerMap;
	
	public CharacterStats() {
		// Stats:
		foughtPlayerCount=0;
		lostCombatCount=0;
		wonCombatCount=0;

		// Relationship stats:
		relationships = new HashMap<GameCharacter,Integer>();
		
		// Sex Stats:
		sexConsensualCount=0;
		sexAsSubCount=0;
		sexAsDomCount=0;
		
		sexPartnerMap = new HashMap<GameCharacter, Map<SexType, Integer>>();
		
		sexCountMap = new HashMap<>();
		cumCountMap = new HashMap<>();
		virginityLossMap = new HashMap<>();
	}
	
	public Map<SexType, Integer> getSexPartnerStats(GameCharacter c) {
		return sexPartnerMap.get(c);
	}
	
	public Map<GameCharacter, Map<SexType, Integer>> getSexPartners() {
		return sexPartnerMap;
	}
	
	public void addSexPartner(GameCharacter partner, SexType sexType) {
		if(this.sexPartnerMap.get(partner) == null) {
			Map<SexType, Integer> n = new HashMap<>();
			this.sexPartnerMap.put(partner, n);
		}
		
		Map <SexType, Integer> sp = this.sexPartnerMap.get(partner);
		if(sp.get(sexType) == null) {
			sp.put(sexType, 0);
		}
		
		sp.put(sexType, sp.get(sexType) + 1);
	}


	public int getFoughtPlayerCount() {
		return foughtPlayerCount;
	}
	public void setFoughtPlayerCount(int count) {
		foughtPlayerCount = count;
	}

	public int getLostCombatCount() {
		return lostCombatCount;
	}
	public void setLostCombatCount(int count) {
		lostCombatCount = count;
	}

	public int getWonCombatCount() {
		return wonCombatCount;
	}
	public void setWonCombatCount(int count) {
		wonCombatCount = count;
	}

	// Relationships:
	public int getRelationshipAffection(GameCharacter character) {
		if(relationships.containsKey(character)) {
			return relationships.get(character);
		} else {
			return 0;
		}
	}
	
	public AffectionLevel getRelationshipAffectionLevel(GameCharacter character) {
		if(relationships.containsKey(character)) {
			return AffectionLevel.getAffectionLevelFromValue(relationships.get(character));
		} else {
			return AffectionLevel.getAffectionLevelFromValue(0);
		}
	}
	
	public void addRelationship(GameCharacter character, int affection) {
		relationships.put(character, affection);
	}
	
	public void setRelationshipAffection(GameCharacter character, int affection) {
		relationships.put(character, affection);
	}
	
	public void incrementRelationshipAffection(GameCharacter character, int affectionIncrement) {
		setRelationshipAffection(character, getRelationshipAffection(character) + affectionIncrement);
	}
	
	// Sex stats:
	public int getSexConsensualCount() {
		return sexConsensualCount;
	}
	public void setSexConsensualCount(int count) {
		sexConsensualCount = count;
	}

	public int getSexAsSubCount() {
		return sexAsSubCount;
	}
	public void setSexAsSubCount(int count) {
		sexAsSubCount = count;
	}

	public int getSexAsDomCount() {
		return sexAsDomCount;
	}
	public void setSexAsDomCount(int count) {
		sexAsDomCount = count;
	}

	public int getTotalTimesHadSex() {
		return getSexAsDomCount() + getSexAsSubCount() + getSexConsensualCount();
	}
	
	// Sex:
	public void incrementSexCount(SexType sexType) {
		if(sexCountMap.get(sexType)==null) {
			sexCountMap.put(sexType, 1);
		} else {
			sexCountMap.put(sexType, sexCountMap.get(sexType) + 1);
		}
	}
	public void setSexCount(SexType sexType, int integer) {
		if(sexCountMap.get(sexType)==null) {
			sexCountMap.put(sexType, integer);
		} else {
			sexCountMap.put(sexType, integer);
		}
	}
	public int getSexCount(SexType sexType) {
		if(sexCountMap.get(sexType)==null) {
			return 0;
		} else {
			return sexCountMap.get(sexType);
		}
	}

	// Cum:
	public void incrementCumCount(SexType sexType) {
		if(cumCountMap.get(sexType)==null) {
			cumCountMap.put(sexType, 1);
		} else {
			cumCountMap.put(sexType, cumCountMap.get(sexType) + 1);
		}
	}
	public void setCumCount(SexType sexType, int integer) {
		if(cumCountMap.get(sexType)==null) {
			cumCountMap.put(sexType, integer);
		} else {
			cumCountMap.put(sexType, integer);
		}
	}
	public int getCumCount(SexType sexType) {
		if(cumCountMap.get(sexType)==null) {
			return 0;
		} else {
			return cumCountMap.get(sexType);
		}
	}

	// Virginity:
	public void setVirginityLoss(SexType sexType, String description) {
		virginityLossMap.put(sexType, description);
	}
	
	public String getVirginityLoss(SexType sexType) {
		return virginityLossMap.get(sexType);
	}
}
