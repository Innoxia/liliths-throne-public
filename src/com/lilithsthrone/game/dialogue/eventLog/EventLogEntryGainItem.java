package com.lilithsthrone.game.dialogue.eventLog;

import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.85
 * @version 0.1.85
 * @author Innoxia
 */
public class EventLogEntryGainItem extends EventLogEntry {

	
	public EventLogEntryGainItem(AbstractCoreItem item) {
		super(Main.game.getMinutesPassed(),
				(item instanceof AbstractItem?"Gained Item":(item instanceof AbstractClothing?"Gained Clothing":"Gained Weapon")),
				"<span style='color:"+item.getRarity().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(item.getName())+"</span>");
	}
	
	@Override
	public String getFormattedEntry() {
		return "<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>"+name+"</span>: "+description;
	}
}
