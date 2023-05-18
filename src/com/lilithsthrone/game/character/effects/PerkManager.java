package com.lilithsthrone.game.character.effects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Pathing;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * Singleton enforced by Enum. Because everyone loves Enums.
 * 
 * @since 0.1.99
 * @version 0.3.4
 * @author Innoxia
 */
public enum PerkManager {

	MANAGER;

	private StringBuilder treeSB = new StringBuilder();
	private StringBuilder lineSB = new StringBuilder();
	private StringBuilder entrySB = new StringBuilder();
	
	private Map<Integer, Map<PerkCategory, List<TreeEntry<PerkCategory, AbstractPerk>>>> perkTree;
	private Map<Integer, Map<PerkCategory, List<TreeEntry<PerkCategory, AbstractPerk>>>> elementalPerkTree;
	
	private List<TreeEntry<PerkCategory, AbstractPerk>> elementalStartingPerks = new ArrayList<>();
	private List<TreeEntry<PerkCategory, AbstractPerk>> standardStartingPerks = new ArrayList<>();
	
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
		
		TreeEntry<PerkCategory, AbstractPerk> connectorLeft, connectorMid, connectorRight;
		TreeEntry<PerkCategory, AbstractPerk> leftA, leftB, leftC;
		TreeEntry<PerkCategory, AbstractPerk> leftMidA, leftMidB, leftMidC;
		TreeEntry<PerkCategory, AbstractPerk> rightMidA;
		TreeEntry<PerkCategory, AbstractPerk> rightA, rightB, rightC;
		
		TreeEntry<PerkCategory, AbstractPerk> arcane1, arcane2, arcane3, arcane4;
		TreeEntry<PerkCategory, AbstractPerk> physical1, physical2, physical3, physical4;
		TreeEntry<PerkCategory, AbstractPerk> both1, both2, both3, both4;
		
		physical1 = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 1, Perk.PHYSICAL_BASE);
		standardStartingPerks.add(physical1);
		arcane1 = addPerkEntry(perkTree, PerkCategory.ARCANE, 1, Perk.ARCANE_BASE);
		standardStartingPerks.add(arcane1);
		both1 = addPerkEntry(perkTree, PerkCategory.LUST, 1, Perk.LEWD_KNOWLEDGE, physical1, arcane1);
		standardStartingPerks.add(both1);

		
		/* Physical Tree Section 1 */
		
		leftA = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 2, Perk.PHYSIQUE_BOOST, physical1);
		addPerkEntry(perkTree, PerkCategory.PHYSICAL, 2, Perk.OBSERVANT, physical1);

		leftC = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 2, Perk.ENCHANTMENT_STABILITY, physical1);
		

		leftB = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 3, Perk.PHYSICAL_DEFENCE, leftA);
		leftB = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 4, Perk.PHYSICAL_DEFENCE, leftB);
		leftMidA = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 3, Perk.ENERGY_BOOST, leftA);
		leftMidA = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 4, Perk.ENERGY_BOOST, leftMidA);
		rightA = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 3, Perk.PHYSICAL_DAMAGE, leftA);
		rightA = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 4, Perk.PHYSICAL_DAMAGE, rightA);
		
		leftC = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 3, Perk.ENCHANTMENT_STABILITY, leftC);
		addPerkEntry(perkTree, PerkCategory.PHYSICAL, 4, Perk.WEAPON_ENCHANTER, leftC);

		leftA = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 5, Perk.RUNNER_2, leftB, leftMidA);
		rightB = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 5, Perk.UNARMED_TRAINING);
		rightB = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 5, Perk.CRITICAL_BOOST, rightB);
		rightA = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 5, Perk.UNARMED_DAMAGE, rightA, rightB);

		connectorLeft = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 6, Perk.PHYSIQUE_BOOST_MAJOR, leftA, rightA);

		/* Physical Tree Section 2 */

		leftA = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 7, Perk.PHYSICAL_DAMAGE, connectorLeft);
		addPerkEntry(perkTree, PerkCategory.PHYSICAL, 7, Perk.UNARMED_DAMAGE, leftA);

		leftMidA = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 7, Perk.ENERGY_BOOST_DRAIN_DAMAGE);
		rightA = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 7, Perk.ENERGY_BOOST, connectorLeft);
		leftMidA.addLink(rightA);
		
		leftB = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 8, Perk.PHYSICAL_DAMAGE, leftA);
		addPerkEntry(perkTree, PerkCategory.PHYSICAL, 8, Perk.MELEE_DAMAGE, leftB);
		leftB = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 9, Perk.PHYSICAL_DAMAGE, leftB);
		addPerkEntry(perkTree, PerkCategory.PHYSICAL, 9, Perk.RANGED_DAMAGE, leftB);
		leftB = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 10, Perk.FEROCIOUS_WARRIOR, leftB);
		addPerkEntry(perkTree, PerkCategory.PHYSICAL, 10, Perk.BESERK, leftB);

		rightC = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 8, Perk.PHYSICAL_DEFENCE, rightA);
		rightC = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 9, Perk.PHYSICAL_DEFENCE, rightC);
		rightB = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 8, Perk.ENERGY_BOOST, rightA);
		rightB = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 9, Perk.ENERGY_BOOST, rightB);
		rightB = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 10, Perk.COMBAT_REGENERATION, rightB, rightC);

		leftA = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 11, Perk.MELEE_DAMAGE);
		connectorLeft = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 11, Perk.PHYSIQUE_BOOST_MAJOR, leftB, rightB);
		addPerkEntry(perkTree, PerkCategory.PHYSICAL, 11, Perk.RANGED_DAMAGE, connectorLeft);
		leftA.addLink(connectorLeft);

		connectorLeft = addPerkEntry(perkTree, PerkCategory.PHYSICAL, 12, Perk.ELEMENTAL_BOOST, connectorLeft);
		
		
		/* Lust Tree Section 1 */
		leftC = addPerkEntry(perkTree, PerkCategory.LUST, 2, Perk.VIRILITY_BOOST, both1);
		addPerkEntry(perkTree, PerkCategory.LUST, 2, Perk.FIRING_BLANKS, both1);
		leftC = addPerkEntry(perkTree, PerkCategory.LUST, 3, Perk.VIRILITY_MAJOR_BOOST, leftC);
		leftC = addPerkEntry(perkTree, PerkCategory.LUST, 4, Perk.FETISH_SEEDER, leftC);
		leftC = addPerkEntry(perkTree, PerkCategory.LUST, 5, Perk.VIRILITY_BOOST, leftC);
		addPerkEntry(perkTree, PerkCategory.LUST, 5, Perk.VIRILITY_MAJOR_BOOST, leftC);
		
		leftMidA = addPerkEntry(perkTree, PerkCategory.LUST, 2, Perk.SEDUCTION_BOOST, both1);
		
		leftMidB = addPerkEntry(perkTree, PerkCategory.LUST, 3, Perk.SEDUCTION_BOOST, leftMidA);
		addPerkEntry(perkTree, PerkCategory.LUST, 3, Perk.ORGASMIC_LEVEL_DRAIN, leftMidA);
		rightMidA = addPerkEntry(perkTree, PerkCategory.LUST, 3, Perk.SEDUCTION_DEFENCE_BOOST, leftMidA);

		leftMidB = addPerkEntry(perkTree, PerkCategory.LUST, 4, Perk.SEDUCTION_BOOST, leftMidB);
		connectorMid = addPerkEntry(perkTree, PerkCategory.LUST, 4, Perk.AHEGAO, leftMidB);
		rightMidA = addPerkEntry(perkTree, PerkCategory.LUST, 4, Perk.SEDUCTION_DEFENCE_BOOST, rightMidA);
		rightMidA.addLink(connectorMid);

//		leftA = addPerkEntry(perkTree, PerkCategory.LUST, 5, Perk.VIRILITY_BOOST);
		rightMidA = addPerkEntry(perkTree, PerkCategory.LUST, 5, Perk.CRITICAL_BOOST_LUST, leftMidB, rightMidA);
//		addPerkEntry(perkTree, PerkCategory.LUST, 5, Perk.FERTILITY_BOOST, rightMidA);

		addPerkEntry(perkTree, PerkCategory.LUST, 2, Perk.BARREN, both1);
		rightB = addPerkEntry(perkTree, PerkCategory.LUST, 2, Perk.FERTILITY_BOOST, both1);
		rightB = addPerkEntry(perkTree, PerkCategory.LUST, 3, Perk.FERTILITY_MAJOR_BOOST, rightB);
		rightB = addPerkEntry(perkTree, PerkCategory.LUST, 4, Perk.FETISH_BROODMOTHER, rightB);
		rightC = addPerkEntry(perkTree, PerkCategory.LUST, 5, Perk.FERTILITY_MAJOR_BOOST);
		addPerkEntry(perkTree, PerkCategory.LUST, 5, Perk.FERTILITY_BOOST, rightB, rightC);

		leftA = addPerkEntry(perkTree, PerkCategory.LUST, 6, Perk.MALE_ATTRACTION);
		connectorMid = addPerkEntry(perkTree, PerkCategory.LUST, 6, Perk.SEDUCTION_BOOST_MAJOR, rightMidA);
		addPerkEntry(perkTree, PerkCategory.LUST, 6, Perk.FEMALE_ATTRACTION, connectorMid);
		leftA.addLink(connectorMid);

		/* Lust Tree Section 2 */

		leftA = addPerkEntry(perkTree, PerkCategory.LUST, 7, Perk.SEDUCTION_BOOST, connectorMid);
		leftA = addPerkEntry(perkTree, PerkCategory.LUST, 8, Perk.SEDUCTION_BOOST, leftA);
		leftA = addPerkEntry(perkTree, PerkCategory.LUST, 9, Perk.SEDUCTION_BOOST, leftA);
		leftA = addPerkEntry(perkTree, PerkCategory.LUST, 10, Perk.LUSTPYRE, leftA);
		
		leftMidA = addPerkEntry(perkTree, PerkCategory.LUST, 7, Perk.SEDUCTION_BOOST_ALT, connectorMid);
		leftMidB = addPerkEntry(perkTree, PerkCategory.LUST, 8, Perk.CONVINCING_REQUESTS);
		leftMidA = addPerkEntry(perkTree, PerkCategory.LUST, 8, Perk.SEDUCTION_BOOST_MAJOR, leftMidA);
		leftMidB.addLink(leftMidA);
		addPerkEntry(perkTree, PerkCategory.LUST, 8, Perk.OBJECT_OF_DESIRE, leftMidA);
		leftMidA = addPerkEntry(perkTree, PerkCategory.LUST, 9, Perk.SEDUCTION_BOOST_ALT, leftMidA);
		addPerkEntry(perkTree, PerkCategory.LUST, 10, Perk.NYMPHOMANIAC, leftMidA);

		rightA = addPerkEntry(perkTree, PerkCategory.LUST, 7, Perk.SEDUCTION_DEFENCE_BOOST, connectorMid);
		rightA = addPerkEntry(perkTree, PerkCategory.LUST, 8, Perk.SEDUCTION_DEFENCE_BOOST, rightA);
		rightA = addPerkEntry(perkTree, PerkCategory.LUST, 9, Perk.SEDUCTION_DEFENCE_BOOST, rightA);
		rightA = addPerkEntry(perkTree, PerkCategory.LUST, 10, Perk.PURE_MIND, rightA); //TODO test mechanics
 
		connectorMid = addPerkEntry(perkTree, PerkCategory.LUST, 11, Perk.SEDUCTION_BOOST_MAJOR, leftA, rightA);

		connectorMid = addPerkEntry(perkTree, PerkCategory.LUST, 12, Perk.ELEMENTAL_BOOST_ALT, connectorMid);
		
		
		/* Arcane Tree Section 1 */

		rightB = addPerkEntry(perkTree, PerkCategory.ARCANE, 2, Perk.ENCHANTMENT_STABILITY_ALT, arcane1);
		rightB = addPerkEntry(perkTree, PerkCategory.ARCANE, 3, Perk.ENCHANTMENT_STABILITY_ALT, rightB);
		addPerkEntry(perkTree, PerkCategory.ARCANE, 4, Perk.CLOTHING_ENCHANTER, rightB);
		
		addPerkEntry(perkTree, PerkCategory.ARCANE, 2, Perk.ARCANE_CRITICALS, arcane1);
		rightA = addPerkEntry(perkTree, PerkCategory.ARCANE, 2, Perk.ARCANE_BOOST, arcane1);

		leftA = addPerkEntry(perkTree, PerkCategory.ARCANE, 3, Perk.SPELL_DAMAGE, rightA);
		leftA = addPerkEntry(perkTree, PerkCategory.ARCANE, 4, Perk.SPELL_DAMAGE, leftA);
		leftA = addPerkEntry(perkTree, PerkCategory.ARCANE, 5, Perk.ELEMENTAL_BOOST, leftA);
		leftB = addPerkEntry(perkTree, PerkCategory.ARCANE, 5, Perk.CRITICAL_BOOST_ARCANE, leftA);
		addPerkEntry(perkTree, PerkCategory.ARCANE, 5, Perk.CHUUNI, leftB);
		
		leftMidA = addPerkEntry(perkTree, PerkCategory.ARCANE, 3, Perk.AURA_BOOST, rightA);
		leftMidA = addPerkEntry(perkTree, PerkCategory.ARCANE, 4, Perk.AURA_BOOST, leftMidA);
		rightA = addPerkEntry(perkTree, PerkCategory.ARCANE, 3, Perk.SPELL_EFFICIENCY, rightA);
		rightA = addPerkEntry(perkTree, PerkCategory.ARCANE, 4, Perk.SPELL_EFFICIENCY, rightA);
		
		rightA = addPerkEntry(perkTree, PerkCategory.ARCANE, 5, Perk.ARCANE_COMBATANT, leftMidA, rightA);
		
		connectorRight = addPerkEntry(perkTree, PerkCategory.ARCANE, 6, Perk.ARCANE_BOOST_MAJOR, leftA, rightA);

		/* Arcane Tree Section 2 */
		
		leftA = addPerkEntry(perkTree, PerkCategory.ARCANE, 7, Perk.AURA_BOOST, connectorRight);
		addPerkEntry(perkTree, PerkCategory.ARCANE, 8, Perk.AURA_BOOST, leftA);

		leftMidA = addPerkEntry(perkTree, PerkCategory.ARCANE, 7, Perk.SPELL_EFFICIENCY, connectorRight);
		leftMidB = addPerkEntry(perkTree, PerkCategory.ARCANE, 8, Perk.SPELL_EFFICIENCY, leftMidA);
		leftMidC = addPerkEntry(perkTree, PerkCategory.ARCANE, 9, Perk.AURA_BOOST);
		leftMidB = addPerkEntry(perkTree, PerkCategory.ARCANE, 9, Perk.SPELL_EFFICIENCY, leftMidB);
		leftMidC.addLink(leftMidB);
		addPerkEntry(perkTree, PerkCategory.ARCANE, 10, Perk.SACRIFICIAL_SHIELDING, leftMidC);
		leftMidB = addPerkEntry(perkTree, PerkCategory.ARCANE, 10, Perk.SPELL_EFFICIENCY, leftMidB);

		rightMidA = addPerkEntry(perkTree, PerkCategory.ARCANE, 7, Perk.SPELL_DAMAGE, leftMidA);
		rightMidA = addPerkEntry(perkTree, PerkCategory.ARCANE, 8, Perk.SPELL_DAMAGE, rightMidA);
		rightMidA = addPerkEntry(perkTree, PerkCategory.ARCANE, 9, Perk.SPELL_DAMAGE_MAJOR, rightMidA);
		rightMidA = addPerkEntry(perkTree, PerkCategory.ARCANE, 10, Perk.ARCANE_VAMPYRISM, rightMidA);

		leftA = addPerkEntry(perkTree, PerkCategory.ARCANE, 11, Perk.ELEMENTAL_DEFENCE_BOOST);
		connectorRight = addPerkEntry(perkTree, PerkCategory.ARCANE, 11, Perk.ARCANE_BOOST_MAJOR, leftMidB);
		addPerkEntry(perkTree, PerkCategory.ARCANE, 11, Perk.ELEMENTAL_BOOST, connectorRight);
		leftA.addLink(connectorRight);

		connectorRight = addPerkEntry(perkTree, PerkCategory.ARCANE, 12, Perk.ELEMENTAL_BOOST_ALT_2, connectorRight);
		
		
		/* Connect trees at elemental damage */

		connectorLeft.addLink(connectorMid);
		connectorRight.addLink(connectorMid);
		
		
		// Elemental version of the perk tree:
		elementalPerkTree = new HashMap<>();
		
		// Initialise NPCPerkTree:
		for(int i = 0; i<ROWS; i++) {
			elementalPerkTree.put(i, new HashMap<>());
			for(PerkCategory category : PerkCategory.values()) {
				elementalPerkTree.get(i).put(category, new ArrayList<>());
			}
		}

		// Earth:
		physical1 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 0, Perk.ELEMENTAL_EARTH_SPELL_1);
		elementalStartingPerks.add(physical1);

		physical3 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 1, Perk.ELEMENTAL_EARTH_SPELL_1_1, physical1);
		physical4 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 2, Perk.ELEMENTAL_EARTH_SPELL_1_2, physical3);
		addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 3, Perk.ELEMENTAL_EARTH_SPELL_1_3, physical4);
		physical2 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 1, Perk.ELEMENTAL_EARTH_BOOST_MINOR, physical1);
		physical1 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 2, Perk.ELEMENTAL_EARTH_BOOST, physical2);
		physical2 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 3, Perk.ELEMENTAL_EARTH_BOOST_MAJOR, physical1);
		
		physical1 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 4, Perk.ELEMENTAL_EARTH_SPELL_2, physical2);

		physical3 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 5, Perk.ELEMENTAL_EARTH_SPELL_2_1, physical1);
		physical4 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 6, Perk.ELEMENTAL_EARTH_SPELL_2_2, physical3);
		addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 7, Perk.ELEMENTAL_EARTH_SPELL_2_3, physical4);
		physical2 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 5, Perk.ELEMENTAL_EARTH_BOOST_MINOR, physical1);
		physical1 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 6, Perk.ELEMENTAL_EARTH_BOOST, physical2);
		physical2 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 7, Perk.ELEMENTAL_EARTH_BOOST_MAJOR, physical1);
		
		physical1 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 8, Perk.ELEMENTAL_EARTH_SPELL_3, physical2);

		physical3 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 9, Perk.ELEMENTAL_EARTH_SPELL_3_1, physical1);
		physical4 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 10, Perk.ELEMENTAL_EARTH_SPELL_3_2, physical3);
		addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 11, Perk.ELEMENTAL_EARTH_SPELL_3_3, physical4);
		physical2 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 9, Perk.ELEMENTAL_EARTH_BOOST_MINOR, physical1);
		physical1 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 10, Perk.ELEMENTAL_EARTH_BOOST, physical2);
		physical2 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 11, Perk.ELEMENTAL_EARTH_BOOST_MAJOR, physical1);
		physical1 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 12, Perk.ELEMENTAL_EARTH_BOOST_ULTIMATE, physical2);

		// Water:
		physical1 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 0, Perk.ELEMENTAL_WATER_SPELL_1);
		elementalStartingPerks.add(physical1);

		physical3 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 1, Perk.ELEMENTAL_WATER_SPELL_1_1, physical1);
		physical4 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 2, Perk.ELEMENTAL_WATER_SPELL_1_2, physical3);
		addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 3, Perk.ELEMENTAL_WATER_SPELL_1_3, physical4);
		physical2 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 1, Perk.ELEMENTAL_WATER_BOOST_MINOR, physical1);
		physical1 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 2, Perk.ELEMENTAL_WATER_BOOST, physical2);
		physical2 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 3, Perk.ELEMENTAL_WATER_BOOST_MAJOR, physical1);
		
		physical1 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 4, Perk.ELEMENTAL_WATER_SPELL_2, physical2);

		physical3 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 5, Perk.ELEMENTAL_WATER_SPELL_2_1, physical1);
		physical4 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 6, Perk.ELEMENTAL_WATER_SPELL_2_2, physical3);
		addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 7, Perk.ELEMENTAL_WATER_SPELL_2_3, physical4);
		physical2 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 5, Perk.ELEMENTAL_WATER_BOOST_MINOR, physical1);
		physical1 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 6, Perk.ELEMENTAL_WATER_BOOST, physical2);
		physical2 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 7, Perk.ELEMENTAL_WATER_BOOST_MAJOR, physical1);
		
		physical1 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 8, Perk.ELEMENTAL_WATER_SPELL_3, physical2);

		physical3 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 9, Perk.ELEMENTAL_WATER_SPELL_3_1, physical1);
		physical4 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 10, Perk.ELEMENTAL_WATER_SPELL_3_2, physical3);
		addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 11, Perk.ELEMENTAL_WATER_SPELL_3_3, physical4);
		physical2 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 9, Perk.ELEMENTAL_WATER_BOOST_MINOR, physical1);
		physical1 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 10, Perk.ELEMENTAL_WATER_BOOST, physical2);
		physical2 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 11, Perk.ELEMENTAL_WATER_BOOST_MAJOR, physical1);
		physical1 = addPerkEntry(elementalPerkTree, PerkCategory.PHYSICAL, 12, Perk.ELEMENTAL_WATER_BOOST_ULTIMATE, physical2);
		
		// Arcane:
//		both1 = addPerkEntry(elementalPerkTree, PerkCategory.LUST, 0, Perk.ELEMENTAL_CORE);
//		elementalStartingPerks.add(both1);
		both1 = addPerkEntry(elementalPerkTree, PerkCategory.LUST, 0, Perk.ELEMENTAL_ARCANE_SPELL_1);
		elementalStartingPerks.add(both1);

		both3 = addPerkEntry(elementalPerkTree, PerkCategory.LUST, 1, Perk.ELEMENTAL_ARCANE_SPELL_1_1, both1);
		both4 = addPerkEntry(elementalPerkTree, PerkCategory.LUST, 2, Perk.ELEMENTAL_ARCANE_SPELL_1_2, both3);
		addPerkEntry(elementalPerkTree, PerkCategory.LUST, 3, Perk.ELEMENTAL_ARCANE_SPELL_1_3, both4);
		both2 = addPerkEntry(elementalPerkTree, PerkCategory.LUST, 1, Perk.ELEMENTAL_ARCANE_BOOST_MINOR, both1);
		both1 = addPerkEntry(elementalPerkTree, PerkCategory.LUST, 2, Perk.ELEMENTAL_ARCANE_BOOST, both2);
		both2 = addPerkEntry(elementalPerkTree, PerkCategory.LUST, 3, Perk.ELEMENTAL_ARCANE_BOOST_MAJOR, both1);
		
		both1 = addPerkEntry(elementalPerkTree, PerkCategory.LUST, 4, Perk.ELEMENTAL_ARCANE_SPELL_2, both2);

		both3 = addPerkEntry(elementalPerkTree, PerkCategory.LUST, 5, Perk.ELEMENTAL_ARCANE_SPELL_2_1, both1);
		both4 = addPerkEntry(elementalPerkTree, PerkCategory.LUST, 6, Perk.ELEMENTAL_ARCANE_SPELL_2_2, both3);
		addPerkEntry(elementalPerkTree, PerkCategory.LUST, 7, Perk.ELEMENTAL_ARCANE_SPELL_2_3, both4);
		both2 = addPerkEntry(elementalPerkTree, PerkCategory.LUST, 5, Perk.ELEMENTAL_ARCANE_BOOST_MINOR, both1);
		both1 = addPerkEntry(elementalPerkTree, PerkCategory.LUST, 6, Perk.ELEMENTAL_ARCANE_BOOST, both2);
		both2 = addPerkEntry(elementalPerkTree, PerkCategory.LUST, 7, Perk.ELEMENTAL_ARCANE_BOOST_MAJOR, both1);
		
		both1 = addPerkEntry(elementalPerkTree, PerkCategory.LUST, 8, Perk.ELEMENTAL_ARCANE_SPELL_3, both2);

		both3 = addPerkEntry(elementalPerkTree, PerkCategory.LUST, 9, Perk.ELEMENTAL_ARCANE_SPELL_3_1, both1);
		both4 = addPerkEntry(elementalPerkTree, PerkCategory.LUST, 10, Perk.ELEMENTAL_ARCANE_SPELL_3_2, both3);
		addPerkEntry(elementalPerkTree, PerkCategory.LUST, 11, Perk.ELEMENTAL_ARCANE_SPELL_3_3, both4);
		both2 = addPerkEntry(elementalPerkTree, PerkCategory.LUST, 9, Perk.ELEMENTAL_ARCANE_BOOST_MINOR, both1);
		both1 = addPerkEntry(elementalPerkTree, PerkCategory.LUST, 10, Perk.ELEMENTAL_ARCANE_BOOST, both2);
		both2 = addPerkEntry(elementalPerkTree, PerkCategory.LUST, 11, Perk.ELEMENTAL_ARCANE_BOOST_MAJOR, both1);
		both1 = addPerkEntry(elementalPerkTree, PerkCategory.LUST, 12, Perk.ELEMENTAL_ARCANE_BOOST_ULTIMATE, both2);
//		both1 = addPerkEntry(elementalPerkTree, PerkCategory.LUST, 0, Perk.ELEMENTAL_CORRUPTION);
//		elementalStartingPerks.add(both1);


		// Fire:
		arcane1 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 0, Perk.ELEMENTAL_FIRE_SPELL_1);
		elementalStartingPerks.add(arcane1);

		arcane3 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 1, Perk.ELEMENTAL_FIRE_SPELL_1_1, arcane1);
		arcane4 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 2, Perk.ELEMENTAL_FIRE_SPELL_1_2, arcane3);
		addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 3, Perk.ELEMENTAL_FIRE_SPELL_1_3, arcane4);
		arcane2 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 1, Perk.ELEMENTAL_FIRE_BOOST_MINOR, arcane1);
		arcane1 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 2, Perk.ELEMENTAL_FIRE_BOOST, arcane2);
		arcane2 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 3, Perk.ELEMENTAL_FIRE_BOOST_MAJOR, arcane1);
		
		arcane1 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 4, Perk.ELEMENTAL_FIRE_SPELL_2, arcane2);

		arcane3 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 5, Perk.ELEMENTAL_FIRE_SPELL_2_1, arcane1);
		arcane4 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 6, Perk.ELEMENTAL_FIRE_SPELL_2_2, arcane3);
		addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 7, Perk.ELEMENTAL_FIRE_SPELL_2_3, arcane4);
		arcane2 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 5, Perk.ELEMENTAL_FIRE_BOOST_MINOR, arcane1);
		arcane1 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 6, Perk.ELEMENTAL_FIRE_BOOST, arcane2);
		arcane2 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 7, Perk.ELEMENTAL_FIRE_BOOST_MAJOR, arcane1);
		
		arcane1 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 8, Perk.ELEMENTAL_FIRE_SPELL_3, arcane2);

		arcane3 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 9, Perk.ELEMENTAL_FIRE_SPELL_3_1, arcane1);
		arcane4 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 10, Perk.ELEMENTAL_FIRE_SPELL_3_2, arcane3);
		addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 11, Perk.ELEMENTAL_FIRE_SPELL_3_3, arcane4);
		arcane2 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 9, Perk.ELEMENTAL_FIRE_BOOST_MINOR, arcane1);
		arcane1 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 10, Perk.ELEMENTAL_FIRE_BOOST, arcane2);
		arcane2 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 11, Perk.ELEMENTAL_FIRE_BOOST_MAJOR, arcane1);
		arcane1 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 12, Perk.ELEMENTAL_FIRE_BOOST_ULTIMATE, arcane2);
		
		// Air:
		arcane1 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 0, Perk.ELEMENTAL_AIR_SPELL_1);
		elementalStartingPerks.add(arcane1);

		arcane3 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 1, Perk.ELEMENTAL_AIR_SPELL_1_1, arcane1);
		arcane4 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 2, Perk.ELEMENTAL_AIR_SPELL_1_2, arcane3);
		addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 3, Perk.ELEMENTAL_AIR_SPELL_1_3, arcane4);
		arcane2 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 1, Perk.ELEMENTAL_AIR_BOOST_MINOR, arcane1);
		arcane1 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 2, Perk.ELEMENTAL_AIR_BOOST, arcane2);
		arcane2 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 3, Perk.ELEMENTAL_AIR_BOOST_MAJOR, arcane1);
		
		arcane1 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 4, Perk.ELEMENTAL_AIR_SPELL_2, arcane2);

		arcane3 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 5, Perk.ELEMENTAL_AIR_SPELL_2_1, arcane1);
		arcane4 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 6, Perk.ELEMENTAL_AIR_SPELL_2_2, arcane3);
		addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 7, Perk.ELEMENTAL_AIR_SPELL_2_3, arcane4);
		arcane2 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 5, Perk.ELEMENTAL_AIR_BOOST_MINOR, arcane1);
		arcane1 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 6, Perk.ELEMENTAL_AIR_BOOST, arcane2);
		arcane2 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 7, Perk.ELEMENTAL_AIR_BOOST_MAJOR, arcane1);
		
		arcane1 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 8, Perk.ELEMENTAL_AIR_SPELL_3, arcane2);

		arcane3 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 9, Perk.ELEMENTAL_AIR_SPELL_3_1, arcane1);
		arcane4 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 10, Perk.ELEMENTAL_AIR_SPELL_3_2, arcane3);
		addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 11, Perk.ELEMENTAL_AIR_SPELL_3_3, arcane4);
		arcane2 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 9, Perk.ELEMENTAL_AIR_BOOST_MINOR, arcane1);
		arcane1 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 10, Perk.ELEMENTAL_AIR_BOOST, arcane2);
		arcane2 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 11, Perk.ELEMENTAL_AIR_BOOST_MAJOR, arcane1);
		arcane1 = addPerkEntry(elementalPerkTree, PerkCategory.ARCANE, 12, Perk.ELEMENTAL_AIR_BOOST_ULTIMATE, arcane2);
		
	}
	
	@SafeVarargs
	private static TreeEntry<PerkCategory, AbstractPerk> addPerkEntry(Map<Integer, Map<PerkCategory, List<TreeEntry<PerkCategory, AbstractPerk>>>> perkTree, PerkCategory category, int row, AbstractPerk perk, TreeEntry<PerkCategory, AbstractPerk>... links) {
		TreeEntry<PerkCategory, AbstractPerk> entry = new TreeEntry<PerkCategory, AbstractPerk>(category, row, perk);
		perkTree.get(row).get(category).add(entry);
		
		for(TreeEntry<PerkCategory, AbstractPerk> linkEntry : links) {
			entry.addLink(linkEntry);
		}
		
		return entry;
	}
	
	public boolean isPerkAnywhereInAvailableTree(AbstractPerk perk, GameCharacter character) {
		for(Map<PerkCategory, List<TreeEntry<PerkCategory, AbstractPerk>>> entry : getPerkTree(character).values()) {
			for(List<TreeEntry<PerkCategory, AbstractPerk>> treeListEntry : entry.values()) {
				for(TreeEntry<PerkCategory, AbstractPerk> treeEntry : treeListEntry) {
					if(treeEntry.getEntry()==perk) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public Map<Integer, Map<PerkCategory, List<TreeEntry<PerkCategory, AbstractPerk>>>> getPerkTree(GameCharacter character) {
		if(character.isElemental()) {
			return elementalPerkTree;
		} else {
			return perkTree;
		}
	}
	
	public static List<TreeEntry<PerkCategory, AbstractPerk>> getStartingPerks(GameCharacter character) {
		if(character.isElemental()) {
			return MANAGER.elementalStartingPerks;
		} else {
			return MANAGER.standardStartingPerks;
		}
	}
	
	public static int getInitialPerkCount(GameCharacter character) {
		return getStartingPerks(character).size();
	}

	public static int getInitialPerkCount(GameCharacter character, PerkCategory category) {
		int count = 0;
		for(TreeEntry<PerkCategory, AbstractPerk> entry : getStartingPerks(character)) {
			if(entry.getCategory()==category) {
				count++;
			}
		}
		return count;
	}
	
	public static void initialiseSpecialPerksUponCreation(GameCharacter character) {
		Random rnd = new Random((character.getId()).hashCode());

		// Add a unique background perk based on weighting:
		if(!character.isUnique() && !(character.isElemental()) && rnd.nextInt(100)<=50) {
			Map<PerkCategory, Integer> perkWeightingMap;
			
			if(character.getBody()!=null && character.getSubspecies()!=null) {
				perkWeightingMap = new HashMap<>(character.getSubspecies().getPerkWeighting(character));
			} else {
				perkWeightingMap = Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 1));
			}
			
			PerkCategory pc = Util.getRandomObjectFromWeightedMap(perkWeightingMap, rnd);
			
			Set<AbstractPerk> specialPerks = character.getSpecialPerks();
			for(AbstractPerk perk : new HashSet<>(specialPerks)) {
				if(perk.isBackgroundPerk()) {
					character.removeSpecialPerk(perk);
				}
			}
			
			switch(pc) {
				case ARCANE:
					character.addSpecialPerk(Perk.SPECIAL_ARCANE_TRAINING);
					break;
				case ARCANE_AIR:
					break;
				case ARCANE_FIRE:
					break;
				case JOB:
					break;
				case LUST:
					if(!character.getFetishDesire(Fetish.FETISH_PURE_VIRGIN).isPositive()
							&& (!character.hasPenisIgnoreDildo() || !character.isPenisVirgin())
							&& (!character.hasVagina() || !character.isVaginaVirgin())) {
						character.addSpecialPerk(Perk.SPECIAL_SLUT);
					} else {
						character.addSpecialPerk(Perk.SPECIAL_DIRTY_MINDED);
					}
					break;
				case PHYSICAL:
					if(Math.random()<0.5) {
						character.addSpecialPerk(Perk.SPECIAL_MARTIAL_BACKGROUND);
					} else {
						character.addSpecialPerk(Perk.SPECIAL_HEALTH_FANATIC);
					}
					break;
				case PHYSICAL_EARTH:
					break;
				case PHYSICAL_WATER:
					break;
			}
		}
	}

	public static Set<AbstractPerk> initialisePerks(GameCharacter character) {
		return initialisePerks(character, true, null, null);
	}
	
	public static Set<AbstractPerk> initialisePerks(GameCharacter character, boolean autoSelectPerks) {
		return initialisePerks(character, autoSelectPerks, null, null);
	}
	
	public static Set<AbstractPerk> initialisePerks(GameCharacter character, boolean autoSelectPerks, List<AbstractPerk> requiredPerks) {
		return initialisePerks(character, autoSelectPerks, requiredPerks, null);
	}

	public static Set<AbstractPerk> initialisePerks(GameCharacter character, List<AbstractPerk> requiredPerks, Map<PerkCategory, Integer> perkWeightingOverride) {
		return initialisePerks(character, true, requiredPerks, perkWeightingOverride);
	}
	
	/**
	 * @return A Set of perks which were added.
	 */
	public static Set<AbstractPerk> initialisePerks(GameCharacter character, boolean autoSelectPerks, List<AbstractPerk> requiredPerks, Map<PerkCategory, Integer> perkWeightingOverride) {
		Set<AbstractPerk> perksAdded = new HashSet<>();
		
		for(TreeEntry<PerkCategory, AbstractPerk> perk : getStartingPerks(character)) {
			if(character.addPerk(perk.getRow(), perk.getEntry())) {
				perksAdded.add(perk.getEntry());
			}
		}
		
		if(!character.isElemental()) { // Elementals always start with an empty perk tree
			if(!character.isPlayer() && autoSelectPerks) {
				// For each required perk, add it (along with all the perks on the path):
				if(requiredPerks!=null) {
					for(AbstractPerk requiredPerk : requiredPerks) {
						for(TreeEntry<PerkCategory, AbstractPerk> entry : Pathing.aStarPathingPerkTree(character, MANAGER.getFirstPerkEntry(character, requiredPerk))) {
							if(character.addPerk(entry.getRow(), entry.getEntry())) {
								perksAdded.add(entry.getEntry());
							}
							if(entry.getEntry().isEquippableTrait()) {
								character.addTrait(entry.getEntry());
							}
						}
					}
				}
				
				// Perks that should not be chosen outside of requirements:
				List<AbstractPerk> deniedPerks = new ArrayList<>();
				deniedPerks.add(Perk.OBSERVANT);
				deniedPerks.add(Perk.ORGASMIC_LEVEL_DRAIN);
				deniedPerks.add(Perk.CHUUNI);
				deniedPerks.add(Perk.BARREN);
				deniedPerks.add(Perk.FIRING_BLANKS);
//				deniedPerks.add(Perk.UNARMED_TRAINING);
				if(character.isUnique()) {
					deniedPerks.add(Perk.AHEGAO);
				}
				if(character.getEnchantmentPointsUsedTotal()<=character.getAttributeValue(Attribute.ENCHANTMENT_LIMIT) || !Main.game.isEnchantmentCapacityEnabled()) {
					deniedPerks.add(Perk.ENCHANTMENT_STABILITY);
					deniedPerks.add(Perk.ENCHANTMENT_STABILITY_ALT);
				}
				if(character.getSexualOrientation()==SexualOrientation.GYNEPHILIC) {
					deniedPerks.add(Perk.MALE_ATTRACTION);
				}
				if(character.getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
					deniedPerks.add(Perk.FEMALE_ATTRACTION);
				}
				if(!character.getFetishDesire(Fetish.FETISH_IMPREGNATION).isPositive()) {
					deniedPerks.add(Perk.VIRILITY_BOOST);
				}
				if(!character.getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive()) {
					deniedPerks.add(Perk.FERTILITY_BOOST);
				}
				
				// Add seed based on name so that it's always the same for uniques randomly generating perks:
				Random rnd = new Random((character.getId()).hashCode());
				
				Map<PerkCategory, Integer> perkWeightingMap;
				if(perkWeightingOverride!=null) {
					perkWeightingMap = new HashMap<>(perkWeightingOverride);
				} else if(character.getBody()!=null && character.getSubspecies()!=null) {
					perkWeightingMap = new HashMap<>(character.getSubspecies().getPerkWeighting(character));
				} else {
					perkWeightingMap = Util.newHashMapOfValues(
							new Value<>(PerkCategory.PHYSICAL, 1),
							new Value<>(PerkCategory.LUST, 1),
							new Value<>(PerkCategory.ARCANE, 1));
				}
				
				perkWeightingMap.entrySet().removeIf((entry)->entry.getValue()<=0);
				
				List<TreeEntry<PerkCategory, AbstractPerk>> traits = new ArrayList<>();
				while(character.getPerkPoints()>0) {
					PerkCategory category;
					category = Util.getRandomObjectFromWeightedMap(perkWeightingMap, rnd);
					Map<TreeEntry<PerkCategory, AbstractPerk>, Integer> weightedAvailabilityMap = new HashMap<>();
					
					for(Map<PerkCategory, List<TreeEntry<PerkCategory, AbstractPerk>>> map : MANAGER.perkTree.values()) {
						for(TreeEntry<PerkCategory, AbstractPerk> perkEntry : map.get(category)) {
							if(perkEntry.getRow()>0 && MANAGER.isPerkAvailable(character, perkEntry) && !deniedPerks.contains(perkEntry.getEntry())) {
								if((perkEntry.getEntry().equals(Perk.ENCHANTMENT_STABILITY) || perkEntry.getEntry().equals(Perk.ENCHANTMENT_STABILITY_ALT))
										&& character.getEnchantmentPointsUsedTotal()>character.getAttributeValue(Attribute.ENCHANTMENT_LIMIT)) {
									weightedAvailabilityMap.put(perkEntry, 1000); // If this character needs more enchantment stability, prioritise giving them the perks for it.
									
								} else {
									weightedAvailabilityMap.put(perkEntry, 1);
								}
							}
						}
					}
					
					if(weightedAvailabilityMap.isEmpty()) {
						perkWeightingMap.remove(category);
						if(perkWeightingMap.isEmpty()) {
							break;
						}
					} else {
						TreeEntry<PerkCategory, AbstractPerk> entryToAdd = Util.getRandomObjectFromWeightedMap(weightedAvailabilityMap, rnd);
						if(character.addPerk(entryToAdd.getRow(), entryToAdd.getEntry())) {
							perksAdded.add(entryToAdd.getEntry());
						}
						if(entryToAdd.getEntry().isEquippableTrait()) {
							traits.add(entryToAdd);
						}
					}
				}

				// Make sure higher level traits are selected:
				traits.sort((t1, t2) -> t1.getRow()>t2.getRow()?-1:(t1.getRow()<t2.getRow()?1:0));
				for(TreeEntry<PerkCategory, AbstractPerk> trait : traits) {
					character.addTrait(trait.getEntry());
				}
			}
		}
		
		return perksAdded;
	}
	
	public String getPerkTreeDisplay(GameCharacter character, boolean includePointsRemaining) {
		treeSB.setLength(0);
		

		treeSB.append("<div class='container-full-width' style='padding:8px; text-align:center;'>"
		+ "<h6 style='text-align:center;'>Active Traits</h6>");
		
		treeSB.append(
				"<div id='OCCUPATION_" + Perk.getIdFromPerk(character.getHistory().getAssociatedPerk())
						+ "' class='square-button small' style='width:8%; display:inline-block; float:none; border:2px solid " + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>"
					+ "<div class='square-button-content'>"+character.getHistory().getAssociatedPerk().getSVGString(character)+"</div>"
				+ "</div>");
		
		for(int i=0;i<GameCharacter.MAX_TRAITS;i++) {
			AbstractPerk p = null;
			if(i<character.getTraits().size()) {
				p = character.getTraits().get(i);
			}
			if(p!=null) {
				treeSB.append("<div id='TRAIT_" + Perk.getIdFromPerk(p) + "' class='square-button small' style='width:8%; display:inline-block; float:none; border:2px solid " + PresetColour.TRAIT.toWebHexString() + ";'>"
						+ "<div class='square-button-content'>"+p.getSVGString(character)+"</div>"
						+ "</div>");
				
			} else {
				treeSB.append("<div id='TRAIT_" + i + "' class='square-button small' style='display:inline-block; float:none;'></div>");
				
			}
		}
		
		treeSB.append("<div class='container-full-width' style='text-align:center;'>");
		if(includePointsRemaining) {
			treeSB.append("<h6 style='text-align:center;'>Perk Points Available: ");
			treeSB.append(character.getPerkPoints());
			StringBuilder extraPerkPoints = new StringBuilder();
			for(PerkCategory category : PerkCategory.values()) {
				int points = character.getAdditionalPerkCategoryPoints(category)-Math.max(0, character.getPerksInCategory(category)-PerkManager.getInitialPerkCount(character, category));
				if(points>0) {
					extraPerkPoints.append(" <span style='color:"+category.getColour().toWebHexString()+";'>"+points+"</span>");
				}
			}
			if(extraPerkPoints.length()>0) {
				treeSB.append("<br/>Category-specific points: "+extraPerkPoints.toString());
			}
			treeSB.append("</h6>");
		}
		
		if(character.getSpecialPerks().size()>0) {
			List<AbstractPerk> subspeciesKnowledgePerks = new ArrayList<>();
			// Non-subspecies knowledge perks:
			treeSB.append("<div class='container-full-width' style='width:100%; padding:0; margin:0;'>");
				for(AbstractPerk hiddenPerk : Perk.getHiddenPerks()) {
					if(character.hasPerkAnywhereInTree(hiddenPerk)) {
						if(Perk.getSubspeciesKnowledgePerks().contains(hiddenPerk)) {
							subspeciesKnowledgePerks.add(hiddenPerk);
						} else {
							treeSB.append(
									"<div id='HIDDEN_PERK_" + Perk.getIdFromPerk(hiddenPerk) + "' class='square-button round small'"
											+ " style='width:6%; display:inline-block; float:none; border-color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+"; cursor:default;'>"
									+ "<div class='square-button-content'>"+hiddenPerk.getSVGString(character)+"</div>"
									+ "</div>");
						}
					}
				}
			treeSB.append("</div>");
			
			// Subspecies knowledge perks:
			Collections.sort(subspeciesKnowledgePerks, (p1, p2)->p1.getName(character).compareTo(p2.getName(character)));
			treeSB.append("<div class='container-full-width' style='width:100%; padding:0; margin:0;'>");
				for(AbstractPerk hiddenPerk : subspeciesKnowledgePerks) {
					treeSB.append(
							"<div id='HIDDEN_PERK_" + Perk.getIdFromPerk(hiddenPerk) + "' class='square-button round small'"
									+ " style='width:6%; padding:0; left:12px; margin:0 0 0 -24px; display:inline-block; float:none; border-color:"+hiddenPerk.getColour().toWebHexString()+"; cursor:default;'>"
							+ "<div class='square-button-content'>"+hiddenPerk.getSVGString(character)+"</div>"
							+ "</div>");
				}
			treeSB.append("</div>");
		}
		
		for(int i = 0; i<ROWS; i++) {
			treeSB.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; display: -ms-flex; display: -webkit-flex; display: flex;'>");
			appendPerkColumn(character, i, PerkCategory.PHYSICAL);
			appendPerkColumn(character, i, PerkCategory.LUST);
			appendPerkColumn(character, i, PerkCategory.ARCANE);
			treeSB.append("</div>");
		}
		
		treeSB.append("</div>");
		
		return treeSB.toString();
	}
	
	private void appendPerkColumn(GameCharacter character, int row, PerkCategory category) {
		treeSB.append("<div class='container-full-width' style='width: 33.3%; padding:0; margin:0; flex:1;'>");
		
		List<TreeEntry<PerkCategory, AbstractPerk>> perkList = getPerkTree(character).get(row).get(category);
		int size = perkList.size();
		
		treeSB.append(getHorizontalLine(character, perkList));
		for(TreeEntry<PerkCategory, AbstractPerk> entry : perkList) {
			treeSB.append(getPerkEntry(character, entry, size));
		}
			
		treeSB.append("</div>");
	}
	

	private String getHorizontalLine(GameCharacter character, List<TreeEntry<PerkCategory, AbstractPerk>> perkList) {
		lineSB.setLength(0);
		
		for(TreeEntry<PerkCategory, AbstractPerk> entry : perkList) {
			float entryX = getX(character, entry.getRow(), entry);
			for(TreeEntry<PerkCategory, AbstractPerk> siblingEntry : entry.getSiblingLinks()) {
				float siblingX = getX(character, siblingEntry.getRow(), siblingEntry) + siblingDifference(entry.getCategory(), siblingEntry.getCategory());
				lineSB.append("<div style='width:100%; height:100%; padding:0; margin:0; top:0; left:0; position:absolute; pointer-events:none;'>"
						+ "<svg width='100%' height='100%'><line x1='"+siblingX+"%' y1='50%' x2='"+entryX+"%' y2='50%' stroke='"+getPerkLineSiblingColour(character, entry).toWebHexString()+"' stroke-width='2px'/></svg></div>");
			}
			for(TreeEntry<PerkCategory, AbstractPerk> parentEntry : entry.getParentLinks()) {
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
			case ARCANE_AIR:
			case ARCANE_FIRE:
				entryX = 300;
				break;
			case LUST:
				entryX = 200;
				break;
			case PHYSICAL:
			case PHYSICAL_WATER:
			case PHYSICAL_EARTH:
				entryX = 100;
				break;
			case JOB:
				break;
		}
		switch(siblingCategory) {
			case ARCANE:
			case ARCANE_AIR:
			case ARCANE_FIRE:
				siblingX = 300;
				break;
			case LUST:
				siblingX = 200;
				break;
			case PHYSICAL:
			case PHYSICAL_WATER:
			case PHYSICAL_EARTH:
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
	
	private float getX(GameCharacter character, int row, TreeEntry<PerkCategory, AbstractPerk> entry) {
		List<TreeEntry<PerkCategory, AbstractPerk>> list = getPerkTree(character).get(row).get(entry.getCategory());
		int size = list.size();
		float marginSize = getMargin(size);
		int column = list.indexOf(entry);
		
		return ((marginSize*(column)*2)+(column*16)+8+marginSize);
	}
	
	private String getPerkEntry(GameCharacter character, TreeEntry<PerkCategory, AbstractPerk> perkEntry, int size) {
		
		entrySB.setLength(0);
		
		boolean disabled = !isPerkOwned(character, perkEntry) && !isPerkAvailable(character, perkEntry);
		
		Colour borderColour = perkEntry.getCategory().getColour();
		switch(perkEntry.getEntry().getPerkCategory()) {
			case ARCANE:
			case JOB:
			case LUST:
			case PHYSICAL:
				break;
			case ARCANE_AIR:
			case ARCANE_FIRE:
			case PHYSICAL_EARTH:
			case PHYSICAL_WATER:
				borderColour = perkEntry.getEntry().getPerkCategory().getColour();
				break;
		}
		
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
										?"border-color:"+PresetColour.TRAIT.toWebHexString()+";"
										:"border-color:"+borderColour.toWebHexString()+";"
									:"")+"' id='"+perkEntry.getRow()+"_"+perkEntry.getCategory()+"_"+Perk.getIdFromPerk(perkEntry.getEntry())+"'>"
				+ "<div class='square-button-content'>"+perkEntry.getEntry().getSVGString(character)+"</div>"
				+ (disabled
					?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:rgba(0,0,0,0.8); "+(perkEntry.getEntry().isEquippableTrait()?"border-radius:5px;":" border-radius:50%;")+"'></div>"
					:!isPerkOwned(character, perkEntry)
						?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:rgba(0,0,0,0.6); "+(perkEntry.getEntry().isEquippableTrait()?"border-radius:5px;":" border-radius:50%;")+"'></div>"
						:"")
			+ "</div>");
		
		return entrySB.toString();
	}
	
	public boolean isPerkOwned(GameCharacter character, TreeEntry<PerkCategory, AbstractPerk> perkEntry) {
		return character.hasPerkInTree(perkEntry.getRow(), perkEntry.getEntry());
	}
	
	public boolean isPerkOwned(GameCharacter character, int row, AbstractPerk perk) {
		return character.hasPerkInTree(row, perk);
	}
	
	public boolean isPerkAvailable(GameCharacter character, TreeEntry<PerkCategory, AbstractPerk> perkEntry) {
		if(perkEntry.getEntry().isAlwaysAvailable()) {
			return true;
		}
		if(!isPerkOwned(character, perkEntry)) {
			for(TreeEntry<PerkCategory, AbstractPerk> linkedEntry : perkEntry.getLinks()) {
				if(perkEntry.getRow()>=linkedEntry.getRow() && character.hasPerkInTree(linkedEntry.getRow(), linkedEntry.getEntry())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isPerkAvailable(GameCharacter character, int row, AbstractPerk perk) {
		if(perk.isAlwaysAvailable()) {
			return true;
		}
		if(!isPerkOwned(character, row, perk)) {
			TreeEntry<PerkCategory, AbstractPerk> perkEntry = null;
			loopy:
			for(Entry<PerkCategory, List<TreeEntry<PerkCategory, AbstractPerk>>> entry : getPerkTree(character).get(row).entrySet()) {
				for(TreeEntry<PerkCategory, AbstractPerk> pe : entry.getValue()) {
					if(pe.getEntry()==perk) {
						perkEntry = pe;
						break loopy;
					}
				}
			}
			if(perkEntry==null) {
				return false;
			}
			for(TreeEntry<PerkCategory, AbstractPerk> linkedEntry : perkEntry.getLinks()) {
				if(perkEntry.getRow()>=linkedEntry.getRow() && character.hasPerkInTree(linkedEntry.getRow(), linkedEntry.getEntry())) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @param onlyCheckOwnedPerks true if you want to completely ignore all unselected perks.
	 * @return true if the character's perk at the specified row is at the end of a tree's branch.
	 */
	public boolean isPerkEndOfTreeBranch(GameCharacter character, int row, AbstractPerk perk, boolean onlyCheckOwnedPerks) {
		if(perk.isAlwaysAvailable()) {
			return true;
		}
		TreeEntry<PerkCategory, AbstractPerk> perkEntry = null;
		loopy:
		for(Entry<PerkCategory, List<TreeEntry<PerkCategory, AbstractPerk>>> entry : getPerkTree(character).get(row).entrySet()) {
			for(TreeEntry<PerkCategory, AbstractPerk> pe : entry.getValue()) {
				if(pe.getEntry()==perk) {
					perkEntry = pe;
					break loopy;
				}
			}
		}
		if(perkEntry==null) {
			System.err.println("PerkManager.isPerkEndOfTreeBranch() cannot find an entry for: "+character.getId()+", "+row+", "+perk.getName(character));
			return false;
		}
		
		if(perkEntry.getLinks().size()==1) {
			return true;
		}
		
		int siblingLinks = 0;
		for(TreeEntry<PerkCategory, AbstractPerk> linkedEntry : perkEntry.getLinks()) {
			if(!onlyCheckOwnedPerks || character.hasPerkInTree(linkedEntry.getRow(), linkedEntry.getEntry())) {
				if(perkEntry.getRow()<linkedEntry.getRow()) {
					return false;
				}
				if(linkedEntry.getLinks().size()==1) {
					return false;
				}
				if(perkEntry.getRow()==linkedEntry.getRow()) {
					siblingLinks++;
				}
			}
		}
		return siblingLinks<=1;
	}
	
	private Colour getPerkLineParentColour(GameCharacter character, TreeEntry<PerkCategory, AbstractPerk> entry) {
		boolean parentOwned = false;
		for(TreeEntry<PerkCategory, AbstractPerk> parent : entry.getParentLinks()) {
			if(character.hasPerkInTree(parent.getRow(), parent.getEntry())) {
				parentOwned = true;
				break;
			}
		}
		
		return isPerkOwned(character, entry) && parentOwned
				?(character.isElemental()?entry.getEntry().getColour():entry.getCategory().getColour())
				:isPerkAvailable(character, entry)
					?PresetColour.BASE_GREY
					:PresetColour.TEXT_GREY_DARK;
	}
	
	private Colour getPerkLineChildColour(GameCharacter character, TreeEntry<PerkCategory, AbstractPerk> entry) {
		boolean childOwned = false;
		boolean childAvailable = false;
		for(TreeEntry<PerkCategory, AbstractPerk> child : entry.getChildLinks()) {
			if(character.hasPerkInTree(child.getRow(), child.getEntry())) {
				childOwned = true;
			}
			if(isPerkOwned(character, entry) && isPerkAvailable(character, child)) {
				childAvailable = true;
			}
		}
		
		return isPerkOwned(character, entry) && childOwned
				?(character.isElemental()?entry.getEntry().getColour():entry.getCategory().getColour())
				:childAvailable
					?PresetColour.BASE_GREY
					:PresetColour.TEXT_GREY_DARK;
	}
	
	private Colour getPerkLineSiblingColour(GameCharacter character, TreeEntry<PerkCategory, AbstractPerk> entry) {
		boolean siblingOwned = false;
		boolean siblingAvailable = false;
		for(TreeEntry<PerkCategory, AbstractPerk> sibling : entry.getSiblingLinks()) {
			if(character.hasPerkInTree(sibling.getRow(), sibling.getEntry())) {
				siblingOwned = true;
			}
			if((isPerkAvailable(character, sibling) && isPerkOwned(character, entry)) || (isPerkAvailable(character, entry) && isPerkOwned(character, sibling))) {
				siblingAvailable = true;
			}
		}
		
		return isPerkOwned(character, entry) && siblingOwned
				?(character.isElemental()?entry.getEntry().getColour():entry.getCategory().getColour())
				:siblingAvailable
					?PresetColour.BASE_GREY
					:PresetColour.TEXT_GREY_DARK;
	}
	
	public TreeEntry<PerkCategory, AbstractPerk> getFirstPerkEntry(GameCharacter character, AbstractPerk perk) {
		for(int i=0; i<ROWS; i++) {
			for(PerkCategory category : PerkCategory.values()) {
				for(TreeEntry<PerkCategory, AbstractPerk> entry : getPerkTree(character).get(i).get(category)) {
					if(entry.getEntry()==perk) {
						return entry;
					}
				}
			}
		}
		return null;
	}
	
	public int getPerkRow(GameCharacter character, AbstractPerk perk) {
		for(int i=0; i<ROWS; i++) {
			for(PerkCategory category : PerkCategory.values()) {
				for(TreeEntry<PerkCategory, AbstractPerk> entry : getPerkTree(character).get(i).get(category)) {
					if(entry.getEntry()==perk) {
						return i;
					}
				}
			}
		}
		System.err.println("PerkManager.getPerkRow(): Could not find Perk in any row! "+perk.getName(character));
		return 0;
	}
}
