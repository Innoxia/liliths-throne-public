package com.lilithsthrone.game.dialogue.eventLog;

import java.io.Serializable;
import java.util.List;

import com.lilithsthrone.game.character.npc.NPC;

/**
 * @since 0.1.87
 * @version 0.1.87
 * @author Innoxia
 */
public class SlaveryEventLogEntry extends EventLogEntry implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String slaveName;
	private List<String> effects;
	
	public SlaveryEventLogEntry(long time, NPC slave, String name, String description, List<String> effects) {
		super(time, name, description);
		slaveName = "<b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>"+slave.getName()+"</b>";
		this.effects = effects;
	}

	public String getSlaveName() {
		return slaveName;
	}
	
	public List<String> getEffects() {
		return effects;
	}
}
