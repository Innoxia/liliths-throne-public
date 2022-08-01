package com.lilithsthrone.utils.comparators;

import com.lilithsthrone.game.character.npc.NPC;

public class SlaveNameComparator extends BaseSlaveComparator {

	@Override
	public int compare(NPC a, NPC b) {
		return a.getName().compareTo(b.getName());
	}

}
