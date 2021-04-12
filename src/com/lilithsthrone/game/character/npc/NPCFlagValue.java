package com.lilithsthrone.game.character.npc;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 0.1.89
 * @version 0.4
 * @author Innoxia
 */
public enum NPCFlagValue {
	
	genericNPCBetrayedByPlayer,
	
	knowsPlayerGender,
	knowsPlayerDemon,
	introducedToPlayer,
	pendingClothingDressing,
	prostituteQuestioned,
	playerEscapedLastCombat,
	thinksPlayerEnforcer,
	
	flagSlaveBackground,
	flagSlaveSmallTalk,
	flagSlaveEncourage,
	flagSlaveHug,
	flagSlavePettings,
	flagSlaveInspect,
	flagSlaveSpanking,
	flagSlaveMolest,
	
	occupantTalkLife,
	occupantTalkJob,
	occupantTalkLilaya,
	occupantTalkSlaves,
	occupantHugged,
	occupantPet,
	
	occupantHasNewJob,
	
	flagOffspringIntroduced,
	flagOffspringApartmentIntroduced,
	flagOffspringFightApologyNeeded,
	flagOffspringRapeApologyNeeded,
	fightOffspringInApartment,
	
	elementalStayDirty;
	
	private static List<NPCFlagValue> slaveFlags = new ArrayList<>();
	private static List<NPCFlagValue> occupantFlags = new ArrayList<>();
	
	static {
		slaveFlags.add(flagSlaveBackground);
		slaveFlags.add(flagSlaveSmallTalk);
		slaveFlags.add(flagSlaveEncourage);
		slaveFlags.add(flagSlaveHug);
		slaveFlags.add(flagSlavePettings);
		slaveFlags.add(flagSlaveInspect);
		slaveFlags.add(flagSlaveSpanking);
		slaveFlags.add(flagSlaveMolest);
		
		occupantFlags.add(occupantTalkLife);
		occupantFlags.add(occupantTalkJob);
		occupantFlags.add(occupantTalkLilaya);
		occupantFlags.add(occupantTalkSlaves);
		occupantFlags.add(occupantHugged);
		occupantFlags.add(occupantPet);
	}

	public static List<NPCFlagValue> getSlaveFlags() {
		return slaveFlags;
	}

	public static List<NPCFlagValue> getOccupantFlags() {
		return occupantFlags;
	}
}
