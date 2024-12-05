package com.lilithsthrone.utils.comparators;

import com.lilithsthrone.game.character.npc.NPC;

public class SlaveRaceComparator extends BaseSlaveComparator {

	@Override
	public int compare(NPC a, NPC b) {
		return a.getRace().getName(a.getBody(), a.isFeral()).compareTo(b.getRace().getName(b.getBody(), b.isFeral()));
	}

}
