package com.base.game.dialogue.eventLog;

import com.base.game.inventory.AbstractCoreItem;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.inventory.item.AbstractItem;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;

/**
 * @since 0.1.85
 * @version 0.1.85
 * @author Innoxia
 */
public class EventLogEntryGainItem extends EventLogEntry {

	private static final long serialVersionUID = 1L;
	
	public EventLogEntryGainItem(AbstractCoreItem item) {
		super(Main.game.getMinutesPassed(),
				(item instanceof AbstractItem?"Gained Item":(item instanceof AbstractClothing?"Gained Clothing":"Gained Weapon")),
				"<span style='color:"+item.getRarity().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(item.getName())+"</span>");
	}
	
	@Override
	public String getFormattedEntry() {
		return "<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>"+name+"</span>: "+description;
	}
}
