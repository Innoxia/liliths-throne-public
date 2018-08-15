package com.lilithsthrone.game.character.effects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.utils.Colour;

/**
 * Singleton enforced by Enum. Because everyone loves Enums.
 * 
 * @since 0.1.99
 * @version 0.2.10
 * @author Innoxia
 */
public enum PerkManager {

	MANAGER;

	private StringBuilder treeSB = new StringBuilder();
	private StringBuilder lineSB = new StringBuilder();
	private StringBuilder entrySB = new StringBuilder();
	private Map<Integer, Map<PerkCategory, List<TreeEntry<PerkCategory, Perk>>>> perkTree;
	private Map<Integer, Map<PerkCategory, List<TreeEntry<PerkCategory, Perk>>>> NPCPerkTree;
	public static final int ROWS = 20;
	
	private PerkManager() {
		perkTree = new HashMap<>();
		
		// Initialise perkTree:
		for(int i = 0; i<ROWS; i++) {
			perkTree.put(i, new HashMap<>());
			for(PerkCategory category : PerkCategory.values()) {
				perkTree.get(i).put(category, new ArrayList<>());
			}
		}

		addPerkEntry(perkTree, PerkCategory.BOTH, 0, Perk.FIRING_BLANKS);
		addPerkEntry(perkTree, PerkCategory.BOTH, 0, Perk.BARREN);
		addPerkEntry(perkTree, PerkCategory.BOTH, 0, Perk.FETISH_SEEDER);
		addPerkEntry(perkTree, PerkCategory.BOTH, 0, Perk.FETISH_BROODMOTHER);
		addPerkEntry(perkTree, PerkCategory.ARCANE, 0, Perk.CLOTHING_ENCHANTER);
		
		TreeEntry<PerkCategory, Perk> arcane1, arcane2, arcane3, arcane4, arcane5, arcane6;
		TreeEntry<PerkCategory, Perk> physical1, physical2, physical3, physical4, physical5, physical6;
		TreeEntry<PerkCategory, Perk> both1, both2, both3, both4, both5, both6;
		
		physical1 = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 1, Perk.PHYSICAL_BASE);
		arcane1 = addPerkEntry(perkTree, PerkCategory.ARCANE, 1, Perk.ARCANE_BASE);
		
		both1 = addPerkEntry(perkTree, PerkCategory.BOTH, 1, Perk.SEDUCTION_1, physical1, arcane1);

		both2 = addPerkEntry(perkTree, PerkCategory.BOTH, 2, Perk.FEMALE_ATTRACTION);
		both3 = addPerkEntry(perkTree, PerkCategory.BOTH, 2, Perk.SEDUCTION_3, both1, both2);
		both1 = addPerkEntry(perkTree, PerkCategory.BOTH, 2, Perk.MALE_ATTRACTION, both3);

		both4 = addPerkEntry(perkTree, PerkCategory.BOTH, 3, Perk.SEDUCTION_5, both2);
		both5 = addPerkEntry(perkTree, PerkCategory.BOTH, 3, Perk.NYMPHOMANIAC, both4);
		both6 = addPerkEntry(perkTree, PerkCategory.BOTH, 3, Perk.SEDUCTION_5_B, both5, both1);

		
		//Physical:
		physical2 = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 2, Perk.PHYSIQUE_5, physical1);
		addPerkEntry(perkTree, PerkCategory.PHYSICAL, 2, Perk.OBSERVANT, physical1);
		
		physical1 = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 3, Perk.BRAWLER, physical2);
		physical3 = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 3, Perk.RUNNER, physical2);
		physical4 = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 3, Perk.PHYSIQUE_1, physical3);
		addPerkEntry(perkTree, PerkCategory.PHYSICAL, 3, Perk.RUNNER_2, physical4);

		physical2 = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 4, Perk.PHYSICAL_DAMAGE_5, physical1);
		physical5 = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 4, Perk.PHYSICAL_RESISTANCE_5, physical3);
		physical6 = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 4, Perk.PHYSIQUE_3, physical4);
		addPerkEntry(perkTree, PerkCategory.PHYSICAL, 4, Perk.PHYSIQUE_5, physical6);
		
		physical1 = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 5, Perk.PHYSIQUE_5, physical2, physical5);

		// Arcane:
		addPerkEntry(perkTree, PerkCategory.ARCANE, 2, Perk.ARCANE_CRITICALS, arcane1);
		arcane2 = addPerkEntry(perkTree, PerkCategory.ARCANE, 2, Perk.ARCANE_5, arcane1);

		arcane3 = addPerkEntry(perkTree, PerkCategory.ARCANE, 3, Perk.AURA_RESILIENCE_2);
		arcane4 = addPerkEntry(perkTree, PerkCategory.ARCANE, 3, Perk.ARCANE_1, arcane3);
		arcane5 = addPerkEntry(perkTree, PerkCategory.ARCANE, 3, Perk.AURA_RESILIENCE, arcane2, arcane4);
		arcane6 = addPerkEntry(perkTree, PerkCategory.ARCANE, 3, Perk.ARCANE_COMBATANT, arcane2);

		arcane1 = addPerkEntry(perkTree, PerkCategory.ARCANE, 4, Perk.ARCANE_5);
		arcane2 = addPerkEntry(perkTree, PerkCategory.ARCANE, 4, Perk.ARCANE_3, arcane1, arcane4);
		arcane3 = addPerkEntry(perkTree, PerkCategory.ARCANE, 4, Perk.SPELL_EFFICIENCY_5, arcane5);
		arcane2 = addPerkEntry(perkTree, PerkCategory.ARCANE, 4, Perk.SPELL_DAMAGE_5, arcane6);
		
		arcane1 = addPerkEntry(perkTree, PerkCategory.ARCANE, 5, Perk.ARCANE_5, arcane3, arcane2);
		
		// Elementalist tree:
		
		both1 = addPerkEntry(perkTree, PerkCategory.BOTH, 5, Perk.ELEMENTALIST_5, physical1, arcane1);
		both2 = addPerkEntry(perkTree, PerkCategory.BOTH, 6, Perk.FIRE_ENHANCEMENT, both1);
		both3 = addPerkEntry(perkTree, PerkCategory.BOTH, 6, Perk.COLD_ENHANCEMENT, both1);
		both4 = addPerkEntry(perkTree, PerkCategory.BOTH, 6, Perk.POISON_ENHANCEMENT, both1);
		both5 = addPerkEntry(perkTree, PerkCategory.BOTH, 7, Perk.FIRE_ENHANCEMENT_2, both2);
		both6 = addPerkEntry(perkTree, PerkCategory.BOTH, 7, Perk.COLD_ENHANCEMENT_2, both3);
		both1 = addPerkEntry(perkTree, PerkCategory.BOTH, 7, Perk.POISON_ENHANCEMENT_2, both4);
		both2 = addPerkEntry(perkTree, PerkCategory.BOTH, 8, Perk.ELEMENTALIST_5, both5, both6, both1);
		addPerkEntry(perkTree, PerkCategory.BOTH, 9, Perk.ELEMENTALIST_5, both2);
		

		physical2 = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 6, Perk.PHYSIQUE_5, physical1);
		physical3 = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 7, Perk.PHYSIQUE_5, physical2);
		physical2 = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 8, Perk.PHYSIQUE_5, physical3);
		physical3 = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 9, Perk.PHYSIQUE_5, physical2);
		physical2 = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 10, Perk.PHYSIQUE_5, physical3);
		physical3 = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 11, Perk.PHYSIQUE_5, physical2);

		physical2 = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 6, Perk.ENERGY_BOOST_10, physical1);
		physical3 = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 7, Perk.ENERGY_BOOST_10, physical2);
		physical2 = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 8, Perk.ENERGY_BOOST_10, physical3);
		physical3 = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 9, Perk.ENERGY_BOOST_10, physical2);
		physical2 = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 10, Perk.ENERGY_BOOST_10, physical3);
		physical3 = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 11, Perk.ENERGY_BOOST_10, physical2);
		

		arcane2 = addPerkEntry(perkTree, PerkCategory.ARCANE, 6, Perk.AURA_BOOST_10, arcane1);
		arcane3 = addPerkEntry(perkTree, PerkCategory.ARCANE, 7, Perk.AURA_BOOST_10, arcane2);
		arcane2 = addPerkEntry(perkTree, PerkCategory.ARCANE, 8, Perk.AURA_BOOST_10, arcane3);
		arcane3 = addPerkEntry(perkTree, PerkCategory.ARCANE, 9, Perk.AURA_BOOST_10, arcane2);
		arcane2 = addPerkEntry(perkTree, PerkCategory.ARCANE, 10, Perk.AURA_BOOST_10, arcane3);
		arcane3 = addPerkEntry(perkTree, PerkCategory.ARCANE, 11, Perk.AURA_BOOST_10, arcane2);
		
		arcane2 = addPerkEntry(perkTree, PerkCategory.ARCANE, 6, Perk.ARCANE_5, arcane1);
		arcane3 = addPerkEntry(perkTree, PerkCategory.ARCANE, 7, Perk.ARCANE_5, arcane2);
		arcane2 = addPerkEntry(perkTree, PerkCategory.ARCANE, 8, Perk.ARCANE_5, arcane3);
		arcane3 = addPerkEntry(perkTree, PerkCategory.ARCANE, 9, Perk.ARCANE_5, arcane2);
		arcane2 = addPerkEntry(perkTree, PerkCategory.ARCANE, 10, Perk.ARCANE_5, arcane3);
		arcane3 = addPerkEntry(perkTree, PerkCategory.ARCANE, 11, Perk.ARCANE_5, arcane2);
		
		
		// NPC version of the perk tree:
		NPCPerkTree = new HashMap<>();
		
		// Initialise NPCPerkTree:
		for(int i = 0; i<ROWS; i++) {
			NPCPerkTree.put(i, new HashMap<>());
			for(PerkCategory category : PerkCategory.values()) {
				NPCPerkTree.get(i).put(category, new ArrayList<>());
			}
		}
		
		addPerkEntry(NPCPerkTree, PerkCategory.BOTH, 0, Perk.FIRING_BLANKS);
		addPerkEntry(NPCPerkTree, PerkCategory.BOTH, 0, Perk.BARREN);
		addPerkEntry(NPCPerkTree, PerkCategory.BOTH, 0, Perk.FETISH_SEEDER);
		addPerkEntry(NPCPerkTree, PerkCategory.BOTH, 0, Perk.FETISH_BROODMOTHER);
		addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 0, Perk.CLOTHING_ENCHANTER);
		
		physical1 = addPerkEntry(NPCPerkTree, PerkCategory.PHYSICAL, 1, Perk.PHYSICAL_BASE);
		arcane1 = addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 1, Perk.ARCANE_BASE_NPC);
		
		both1 = addPerkEntry(NPCPerkTree, PerkCategory.BOTH, 1, Perk.SEDUCTION_1, physical1, arcane1);

		both2 = addPerkEntry(NPCPerkTree, PerkCategory.BOTH, 2, Perk.FEMALE_ATTRACTION);
		both3 = addPerkEntry(NPCPerkTree, PerkCategory.BOTH, 2, Perk.SEDUCTION_3, both1, both2);
		both1 = addPerkEntry(NPCPerkTree, PerkCategory.BOTH, 2, Perk.MALE_ATTRACTION, both3);

		both4 = addPerkEntry(NPCPerkTree, PerkCategory.BOTH, 3, Perk.SEDUCTION_5, both2);
		both5 = addPerkEntry(NPCPerkTree, PerkCategory.BOTH, 3, Perk.NYMPHOMANIAC, both4);
		both6 = addPerkEntry(NPCPerkTree, PerkCategory.BOTH, 3, Perk.SEDUCTION_5_B, both5, both1);

		
		//Physical:
		physical2 = addPerkEntry(NPCPerkTree, PerkCategory.PHYSICAL, 2, Perk.PHYSIQUE_5, physical1);
		addPerkEntry(NPCPerkTree, PerkCategory.PHYSICAL, 2, Perk.OBSERVANT, physical1);
		
		physical1 = addPerkEntry(NPCPerkTree, PerkCategory.PHYSICAL, 3, Perk.BRAWLER, physical2);
		physical3 = addPerkEntry(NPCPerkTree, PerkCategory.PHYSICAL, 3, Perk.RUNNER, physical2);
		physical4 = addPerkEntry(NPCPerkTree, PerkCategory.PHYSICAL, 3, Perk.PHYSIQUE_1, physical3);
		addPerkEntry(NPCPerkTree, PerkCategory.PHYSICAL, 3, Perk.RUNNER_2, physical4);

		physical2 = addPerkEntry(NPCPerkTree, PerkCategory.PHYSICAL, 4, Perk.PHYSICAL_DAMAGE_5, physical1);
		physical5 = addPerkEntry(NPCPerkTree, PerkCategory.PHYSICAL, 4, Perk.PHYSICAL_RESISTANCE_5, physical3);
		physical6 = addPerkEntry(NPCPerkTree, PerkCategory.PHYSICAL, 4, Perk.PHYSIQUE_3, physical4);
		addPerkEntry(NPCPerkTree, PerkCategory.PHYSICAL, 4, Perk.PHYSIQUE_5, physical6);
		
		physical1 = addPerkEntry(NPCPerkTree, PerkCategory.PHYSICAL, 5, Perk.PHYSIQUE_5, physical2, physical5);

		// Arcane:
		addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 2, Perk.ARCANE_CRITICALS, arcane1);
		arcane2 = addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 2, Perk.ARCANE_5, arcane1);

		arcane3 = addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 3, Perk.AURA_RESILIENCE_2);
		arcane4 = addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 3, Perk.ARCANE_1, arcane3);
		arcane5 = addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 3, Perk.AURA_RESILIENCE, arcane2, arcane4);
		arcane6 = addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 3, Perk.ARCANE_COMBATANT, arcane2);

		arcane1 = addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 4, Perk.ARCANE_5);
		arcane2 = addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 4, Perk.ARCANE_3, arcane1, arcane4);
		arcane3 = addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 4, Perk.SPELL_EFFICIENCY_5, arcane5);
		arcane2 = addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 4, Perk.SPELL_DAMAGE_5, arcane6);
		
		arcane1 = addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 5, Perk.ARCANE_5, arcane3, arcane2);
		
		// Elementalist tree:
		
		both1 = addPerkEntry(NPCPerkTree, PerkCategory.BOTH, 5, Perk.ELEMENTALIST_5, physical1, arcane1);
		both2 = addPerkEntry(NPCPerkTree, PerkCategory.BOTH, 6, Perk.FIRE_ENHANCEMENT, both1);
		both3 = addPerkEntry(NPCPerkTree, PerkCategory.BOTH, 6, Perk.COLD_ENHANCEMENT, both1);
		both4 = addPerkEntry(NPCPerkTree, PerkCategory.BOTH, 6, Perk.POISON_ENHANCEMENT, both1);
		both5 = addPerkEntry(NPCPerkTree, PerkCategory.BOTH, 7, Perk.FIRE_ENHANCEMENT_2, both2);
		both6 = addPerkEntry(NPCPerkTree, PerkCategory.BOTH, 7, Perk.COLD_ENHANCEMENT_2, both3);
		both1 = addPerkEntry(NPCPerkTree, PerkCategory.BOTH, 7, Perk.POISON_ENHANCEMENT_2, both4);
		both2 = addPerkEntry(NPCPerkTree, PerkCategory.BOTH, 8, Perk.ELEMENTALIST_5, both5, both6, both1);
		addPerkEntry(NPCPerkTree, PerkCategory.BOTH, 9, Perk.ELEMENTALIST_5, both2);
		

		physical2 = addPerkEntry(NPCPerkTree, PerkCategory.PHYSICAL, 6, Perk.PHYSIQUE_5, physical1);
		physical3 = addPerkEntry(NPCPerkTree, PerkCategory.PHYSICAL, 7, Perk.PHYSIQUE_5, physical2);
		physical2 = addPerkEntry(NPCPerkTree, PerkCategory.PHYSICAL, 8, Perk.PHYSIQUE_5, physical3);
		physical3 = addPerkEntry(NPCPerkTree, PerkCategory.PHYSICAL, 9, Perk.PHYSIQUE_5, physical2);
		physical2 = addPerkEntry(NPCPerkTree, PerkCategory.PHYSICAL, 10, Perk.PHYSIQUE_5, physical3);
		physical3 = addPerkEntry(NPCPerkTree, PerkCategory.PHYSICAL, 11, Perk.PHYSIQUE_5, physical2);

		physical2 = addPerkEntry(NPCPerkTree, PerkCategory.PHYSICAL, 6, Perk.ENERGY_BOOST_10, physical1);
		physical3 = addPerkEntry(NPCPerkTree, PerkCategory.PHYSICAL, 7, Perk.ENERGY_BOOST_10, physical2);
		physical2 = addPerkEntry(NPCPerkTree, PerkCategory.PHYSICAL, 8, Perk.ENERGY_BOOST_10, physical3);
		physical3 = addPerkEntry(NPCPerkTree, PerkCategory.PHYSICAL, 9, Perk.ENERGY_BOOST_10, physical2);
		physical2 = addPerkEntry(NPCPerkTree, PerkCategory.PHYSICAL, 10, Perk.ENERGY_BOOST_10, physical3);
		physical3 = addPerkEntry(NPCPerkTree, PerkCategory.PHYSICAL, 11, Perk.ENERGY_BOOST_10, physical2);
		

		arcane2 = addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 6, Perk.AURA_BOOST_10, arcane1);
		arcane3 = addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 7, Perk.AURA_BOOST_10, arcane2);
		arcane2 = addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 8, Perk.AURA_BOOST_10, arcane3);
		arcane3 = addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 9, Perk.AURA_BOOST_10, arcane2);
		arcane2 = addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 10, Perk.AURA_BOOST_10, arcane3);
		arcane3 = addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 11, Perk.AURA_BOOST_10, arcane2);
		
		arcane2 = addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 6, Perk.ARCANE_5, arcane1);
		arcane3 = addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 7, Perk.ARCANE_5, arcane2);
		arcane2 = addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 8, Perk.ARCANE_5, arcane3);
		arcane3 = addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 9, Perk.ARCANE_5, arcane2);
		arcane2 = addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 10, Perk.ARCANE_5, arcane3);
		arcane3 = addPerkEntry(NPCPerkTree, PerkCategory.ARCANE, 11, Perk.ARCANE_5, arcane2);
		
		
	}
	
	@SafeVarargs
	private static TreeEntry<PerkCategory, Perk> addPerkEntry(Map<Integer, Map<PerkCategory, List<TreeEntry<PerkCategory, Perk>>>> perkTree, PerkCategory category, int row, Perk perk, TreeEntry<PerkCategory, Perk>... links) {
		TreeEntry<PerkCategory, Perk> entry = new TreeEntry<PerkCategory, Perk>(category, row, perk);
		perkTree.get(row).get(category).add(entry);
		
		for(TreeEntry<PerkCategory, Perk> linkEntry : links) {
			entry.addLink(linkEntry);
		}
		
		return entry;
	}
	
	public Map<Integer, Map<PerkCategory, List<TreeEntry<PerkCategory, Perk>>>> getPerkTree(GameCharacter character) {
		if(character.isPlayer()) {
			return perkTree;
		} else {
			return NPCPerkTree;
		}
	}
	
	public static void initialisePerks(GameCharacter character) {
		if(character.isPlayer()) {
			character.addPerk(Perk.PHYSICAL_BASE);
			character.addPerk(Perk.ARCANE_BASE);	
			
		} else {
			character.addPerk(Perk.PHYSICAL_BASE);
			character.addPerk(Perk.ARCANE_BASE_NPC);
		}
	}
	
	public String getPerkTreeDisplay(GameCharacter character) {
		treeSB.setLength(0);
		
		treeSB.append("<div class='container-full-width'>"
				+ "<h6 style='text-align:center;'>Perk Points Available: "+character.getPerkPoints()+"</h6>");
		
		for(int i = 0; i<ROWS; i++) {
			treeSB.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; display: -ms-flex; display: -webkit-flex; display: flex;'>");
			appendPerkColumn(character, i, PerkCategory.PHYSICAL);
			appendPerkColumn(character, i, PerkCategory.BOTH);
			appendPerkColumn(character, i, PerkCategory.ARCANE);
			treeSB.append("</div>");
		}
		
		treeSB.append("</div>");
		
		return treeSB.toString();
	}
	
	private void appendPerkColumn(GameCharacter character, int row, PerkCategory category) {
		treeSB.append("<div class='container-full-width' style='width: 33.3%; padding:0; margin:0; flex:1;'>");
		
		List<TreeEntry<PerkCategory, Perk>> perkList = getPerkTree(character).get(row).get(category);
		int size = perkList.size();
		
		treeSB.append(getHorizontalLine(character, perkList));
		for(TreeEntry<PerkCategory, Perk> entry : perkList) {
			treeSB.append(getPerkEntry(character, entry, size));
		}
			
		treeSB.append("</div>");
	}
	

	private String getHorizontalLine(GameCharacter character, List<TreeEntry<PerkCategory, Perk>> perkList) {
		lineSB.setLength(0);
		
		for(TreeEntry<PerkCategory, Perk> entry : perkList) {
			float entryX = getX(character, entry.getRow(), entry);
			for(TreeEntry<PerkCategory, Perk> siblingEntry : entry.getSiblingLinks()) {
				float siblingX = getX(character, siblingEntry.getRow(), siblingEntry) + siblingDifference(entry.getCategory(), siblingEntry.getCategory());
				lineSB.append("<div style='width:100%; height:100%; padding:0; margin:0; top:0; left:0; position:absolute; pointer-events:none;'>"
						+ "<svg width='100%' height='100%'><line x1='"+siblingX+"%' y1='50%' x2='"+entryX+"%' y2='50%' stroke='"+getPerkLineSiblingColour(character, entry).toWebHexString()+"' stroke-width='2px'/></svg></div>");
			}
			for(TreeEntry<PerkCategory, Perk> parentEntry : entry.getParentLinks()) {
				float parentX = getX(character, parentEntry.getRow(), parentEntry);
				String colour = getPerkLineParentColour(character, entry).toWebHexString();
						
				if(Math.abs(entryX-parentX)>0.01f) {
					lineSB.append("<div style='width:100%; padding:0; margin:0; top:0; left:0; position:absolute; pointer-events: none;'>"
							+ "<svg style='padding:0; margin:0;' width='100%'><line x1='"+entryX+"%' y1='0' x2='"+parentX+"%' y2='0' stroke='"+colour+"' stroke-width='4px'/></svg></div>");
				}
			}
		}
		
		return lineSB.toString();
	}
	
	private float siblingDifference(PerkCategory entryCategory, PerkCategory siblingCategory) {
		float entryX = 0;
		float siblingX = 0;
		
		switch(entryCategory) {
			case ARCANE:
				entryX = 300;
				break;
			case BOTH:
				entryX = 200;
				break;
			case PHYSICAL:
				entryX = 100;
				break;
			case JOB:
				break;
		}
		switch(siblingCategory) {
			case ARCANE:
				siblingX = 300;
				break;
			case BOTH:
				siblingX = 200;
				break;
			case PHYSICAL:
				siblingX = 100;
				break;
			case JOB:
				break;
		}
		
		return siblingX-entryX;
	}
	
	private float getMargin(int size) {
		return (100-(size*16))/(size*2f);
	}
	
	private float getX(GameCharacter character, int row, TreeEntry<PerkCategory, Perk> entry) {
		List<TreeEntry<PerkCategory, Perk>> list = getPerkTree(character).get(row).get(entry.getCategory());
		int size = list.size();
		float marginSize = getMargin(size);
		int column = list.indexOf(entry);
		
		return ((marginSize*(column)*2)+(column*16)+8+marginSize);
	}
	
	private String getPerkEntry(GameCharacter character, TreeEntry<PerkCategory, Perk> perkEntry, int size) {
		
		entrySB.setLength(0);
		
		boolean disabled = !isPerkOwned(character, perkEntry) && !isPerkAvailable(character, perkEntry);
		
		// Append up/down lines:
		float entryX = getX(character, perkEntry.getRow(), perkEntry);
		if(!perkEntry.getParentLinks().isEmpty()) {
			entrySB.append("<div style='width:100%; height:100%; padding:0; margin:0; top:0; left:0; position:absolute; pointer-events:none;'>"
					+ "<svg width='100%' height='100%'><line x1='"+entryX+"%' y1='0%' x2='"+entryX+"%' y2='50%' stroke='"+getPerkLineParentColour(character, perkEntry).toWebHexString()+"' stroke-width='2px'/></svg></div>");
		}
		if(!perkEntry.getChildLinks().isEmpty()) {
			entrySB.append("<div style='width:100%; height:100%; padding:0; margin:0; top:0; left:0; position:absolute; pointer-events:none;'>"
					+ "<svg width='100%' height='100%'><line x1='"+entryX+"%' y1='100%' x2='"+entryX+"%' y2='50%' stroke='"+getPerkLineChildColour(character, perkEntry).toWebHexString()+"' stroke-width='2px'/></svg></div>");
		}
		
		entrySB.append("<div class='square-button"+(perkEntry.getEntry().isEquippableTrait()?"":" round")+(disabled?" disabled":"")+"' style='width:16%; margin:16px "+getMargin(size)+"%; "+
							(isPerkOwned(character, perkEntry)
									?character.hasTraitActivated(perkEntry.getEntry())
										?"border-color:"+Colour.TRAIT.toWebHexString()+";"
										:"border-color:"+perkEntry.getCategory().getColour().toWebHexString()+";"
									:"")+"' id='"+perkEntry.getRow()+"_"+perkEntry.getEntry()+"'>"
				+ "<div class='square-button-content'>"+perkEntry.getEntry().getSVGString()+"</div>"
				+ (disabled
					?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.8; "+(perkEntry.getEntry().isEquippableTrait()?"border-radius:5px;":" border-radius:50%;")+"'></div>"
					:!isPerkOwned(character, perkEntry)
						?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.6; "+(perkEntry.getEntry().isEquippableTrait()?"border-radius:5px;":" border-radius:50%;")+"'></div>"
						:"")
			+ "</div>");
		
		return entrySB.toString();
	}
	
	public boolean isPerkOwned(GameCharacter character, TreeEntry<PerkCategory, Perk> perkEntry) {
		return character.hasPerkInTree(perkEntry.getRow(), perkEntry.getEntry());
	}
	
	public boolean isPerkOwned(GameCharacter character, int row, Perk perk) {
		return character.hasPerkInTree(row, perk);
	}
	
	public boolean isPerkAvailable(GameCharacter character, TreeEntry<PerkCategory, Perk> perkEntry) {
		if(perkEntry.getEntry().isAlwaysAvailable()) {
			return true;
		}
		if(!isPerkOwned(character, perkEntry)) {
			for(TreeEntry<PerkCategory, Perk> linkedEntry : perkEntry.getLinks()) {
				if(character.hasPerkInTree(linkedEntry.getRow(), linkedEntry.getEntry())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isPerkAvailable(GameCharacter character, int row, Perk perk) {
		if(perk.isAlwaysAvailable()) {
			return true;
		}
		if(!isPerkOwned(character, row, perk)) {
			TreeEntry<PerkCategory, Perk> perkEntry = null;
			loopy:
			for(Entry<PerkCategory, List<TreeEntry<PerkCategory, Perk>>> entry : getPerkTree(character).get(row).entrySet()) {
				for(TreeEntry<PerkCategory, Perk> pe : entry.getValue()) {
					if(pe.getEntry()==perk) {
						perkEntry = pe;
						break loopy;
					}
				}
			}
			if(perkEntry==null) {
				return false;
			}
			for(TreeEntry<PerkCategory, Perk> linkedEntry : perkEntry.getLinks()) {
				if(character.hasPerkInTree(linkedEntry.getRow(), linkedEntry.getEntry())) {
					return true;
				}
			}
		}
		return false;
	}
	
	private Colour getPerkLineParentColour(GameCharacter character, TreeEntry<PerkCategory, Perk> entry) {
		boolean parentOwned = false;
		for(TreeEntry<PerkCategory, Perk> parent : entry.getParentLinks()) {
			if(character.hasPerkInTree(parent.getRow(), parent.getEntry())) {
				parentOwned = true;
				break;
			}
		}
		
		return isPerkOwned(character, entry) && parentOwned
				?entry.getCategory().getColour()
				:isPerkAvailable(character, entry)
					?Colour.BASE_GREY
					:Colour.TEXT_GREY_DARK;
	}
	
	private Colour getPerkLineChildColour(GameCharacter character, TreeEntry<PerkCategory, Perk> entry) {
		boolean childOwned = false;
		boolean childAvailable = false;
		for(TreeEntry<PerkCategory, Perk> child : entry.getChildLinks()) {
			if(character.hasPerkInTree(child.getRow(), child.getEntry())) {
				childOwned = true;
			}
			if(isPerkAvailable(character, child)) {
				childAvailable = true;
			}
		}
		
		return isPerkOwned(character, entry) && childOwned
				?entry.getCategory().getColour()
				:childAvailable
					?Colour.BASE_GREY
					:Colour.TEXT_GREY_DARK;
	}
	
	private Colour getPerkLineSiblingColour(GameCharacter character, TreeEntry<PerkCategory, Perk> entry) {
		boolean siblingOwned = false;
		boolean siblingAvailable = false;
		for(TreeEntry<PerkCategory, Perk> sibling : entry.getSiblingLinks()) {
			if(character.hasPerkInTree(sibling.getRow(), sibling.getEntry())) {
				siblingOwned = true;
			}
			if((isPerkAvailable(character, sibling) && isPerkOwned(character, entry)) || (isPerkAvailable(character, entry) && isPerkOwned(character, sibling))) {
				siblingAvailable = true;
			}
		}
		
		return isPerkOwned(character, entry) && siblingOwned
				?entry.getCategory().getColour()
				:siblingAvailable
					?Colour.BASE_GREY
					:Colour.TEXT_GREY_DARK;
	}
	
	public int getPerkRow(GameCharacter character, Perk perk) {
		for(int i=0; i<ROWS; i++) {
			for(PerkCategory category : PerkCategory.values()) {
				for(TreeEntry<PerkCategory, Perk> entry : getPerkTree(character).get(i).get(category)) {
					if(entry.getEntry()==perk) {
						return i;
					}
				}
			}
		}
		System.err.println("PerkManager.getPerkRow(): Could not find Perk in any row! "+perk);
		return 0;
	}
}
