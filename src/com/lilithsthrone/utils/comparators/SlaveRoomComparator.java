package com.lilithsthrone.utils.comparators;

import com.lilithsthrone.game.character.npc.NPC;

public class SlaveRoomComparator extends BaseSlaveComparator {

	@Override
	public int compare(NPC a, NPC b) {
		return a.getCell().getPlaceName().compareTo(b.getCell().getPlaceName());
	}

}
