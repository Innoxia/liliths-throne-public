package com.base.game.character.effects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum PerkTree {

	// Physical:
	PHYSICAL_BRAWLER(new Perk[] { Perk.BRAWLER }),
	PHYSICAL_TANK(new Perk[] { Perk.TANK, Perk.TANK_2 }),
	PHYSICAL_ACCURATE(new Perk[] { Perk.ACCURATE }),
	PHYSICAL_INDEFATIGABLE(new Perk[] { Perk.INDEFATIGABLE }),

	// Arcane:
	SPELL_POWER(new Perk[] { Perk.SPELL_POWER_1, Perk.SPELL_POWER_2, Perk.SPELL_POWER_3 }),
	ARCANE_FIRE(new Perk[] { Perk.FIRE_ENHANCEMENT, Perk.FIRE_ENHANCEMENT_2 }),
	ARCANE_ICE(new Perk[] { Perk.COLD_ENHANCEMENT, Perk.COLD_ENHANCEMENT_2 }),
	ARCANE_POISON(new Perk[] { Perk.POISON_ENHANCEMENT, Perk.POISON_ENHANCEMENT_2 }),
	ARCANE_OBSERVANT(new Perk[] { Perk.OBSERVANT }),

	// Fitness:
	
	FITNESS_RUNNER(new Perk[] { Perk.RUNNER, Perk.RUNNER_2 }),
	FITNESS_FEMALE_SEDUCTION(new Perk[] { Perk.FEMALE_ATTRACTION }),
	FITNESS_MALE_SEDUCTION(new Perk[] { Perk.MALE_ATTRACTION }),
	NYMPHOMANIAC(new Perk[] { Perk.NYMPHOMANIAC }),
	FITNESS_SEDUCTION(new Perk[] { Perk.SEDUCTION, Perk.SEDUCTION_2, Perk.SEDUCTION_3 }),
	FITNESS_BARREN(new Perk[] { Perk.BARREN });
	
	// Calculating how to display perks in phone menu:
	private static Perk[][] perkGrid = new Perk[5][16];
	private static boolean[][] arrowGrid = new boolean[4][16];
	static {
		// First, order perk trees by their lowest level (first) perk:
		List<PerkTree> perkTreesList = new ArrayList<>();
		for (PerkTree pt : PerkTree.values())
			perkTreesList.add(pt);

		Collections.sort(perkTreesList, (a, b) -> a.getLinkedPerks()[0].getRequiredLevel().getLevel() - b.getLinkedPerks()[0].getRequiredLevel().getLevel());

		// Then, run through trees, assigning each tree's perks to a cell. At
		// this stage, also assign arrows to cells.
		// Cells are chosen based on category and level. (THERE CANNOT BE MORE
		// THAN 4 PERKS IN EACH LEVEL FOR EACH CATEGORY)
		int startingColumn = 0;
		int startingRow = 0;
		for (PerkTree pt : perkTreesList) {
			if (pt.getLinkedPerks()[0].getPerkCategory() == PerkCategory.PHYSICAL)
				startingColumn = 0;
			else if (pt.getLinkedPerks()[0].getPerkCategory() == PerkCategory.ARCANE)
				startingColumn = 5;
			else// if (pt.getLinkedPerks()[0].getPerkCategory() == PerkCategory.FITNESS)
				startingColumn = 10;
//			else
//				startingColumn = 12;

			if (pt.getLinkedPerks()[0].getRequiredLevel().getLevel() == 1)
				startingRow = 0;
			else if (pt.getLinkedPerks()[0].getRequiredLevel().getLevel() == 5)
				startingRow = 1;
			else if (pt.getLinkedPerks()[0].getRequiredLevel().getLevel() == 10)
				startingRow = 2;
			else if (pt.getLinkedPerks()[0].getRequiredLevel().getLevel() == 15)
				startingRow = 3;
			else
				startingRow = 4;

			while (perkGrid[startingRow][startingColumn] != null)
				startingColumn++;

			for (int i = 0; i < pt.getLinkedPerks().length; i++) {
				perkGrid[startingRow][startingColumn] = pt.getLinkedPerks()[i];
				if (pt.getLinkedPerks().length > i + 1)
					arrowGrid[startingRow][startingColumn] = true;
				startingRow++;
			}

		}
	}

	private Perk[] linkedPerks;

	private PerkTree(Perk[] linkedPerks) {
		this.linkedPerks = linkedPerks;
	}

	public Perk[] getLinkedPerks() {
		return linkedPerks;
	}

	public static Perk[][] getPerkGrid() {
		return perkGrid;
	}

	public static boolean[][] getArrowGrid() {
		return arrowGrid;
	}

}
