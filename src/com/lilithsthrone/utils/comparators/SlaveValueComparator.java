package com.lilithsthrone.utils.comparators;

import com.lilithsthrone.game.character.npc.NPC;

public class SlaveValueComparator extends BaseSlaveComparator {

	@Override
	public int compare(NPC a, NPC b) {
		return a.getValueAsSlave(false) - b.getValueAsSlave(false);
	}

}
