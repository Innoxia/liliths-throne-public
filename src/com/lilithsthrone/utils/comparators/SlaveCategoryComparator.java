package com.lilithsthrone.utils.comparators;

import com.lilithsthrone.game.character.npc.NPC;

public class SlaveCategoryComparator extends BaseSlaveComparator {

	@Override
	public int compare(NPC a, NPC b) {
		return a.getSlaveCategory().compareTo(b.getSlaveCategory());
	}

}
