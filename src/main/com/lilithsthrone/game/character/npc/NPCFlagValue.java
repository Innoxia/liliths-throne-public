package com.lilithsthrone.game.character.npc;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 0.1.89
 * @version 0.2.10
 * @author Innoxia
 */
public enum NPCFlagValue {
	
	genericNPCBetrayedByPlayer,
	
	knowsPlayerGender,
	introducedToPlayer,
	pendingClothingDressing,
	pendingTransformationToGenderIdentity,
	
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
	
	occupantHasNewJob,
	
	flagOffspringIntroduced,
	flagOffspringApartmentIntroduced,
	flagOffspringFightApologyNeeded,
	flagOffspringRapeApologyNeeded,
	fightOffspringInApartment;
	
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
	}

	public static List<NPCFlagValue> getSlaveFlags() {
		return slaveFlags;
	}

	public static List<NPCFlagValue> getOccupantFlags() {
		return occupantFlags;
	}
}
