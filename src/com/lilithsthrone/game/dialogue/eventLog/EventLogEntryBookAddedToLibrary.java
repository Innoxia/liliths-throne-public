package com.lilithsthrone.game.dialogue.eventLog;

import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.85
 * @version 0.1.85
 * @author Innoxia
 */
public class EventLogEntryBookAddedToLibrary extends EventLogEntry {

	
	public EventLogEntryBookAddedToLibrary(AbstractItemType book) {
		super("Added to Library", "<span style='color:"+book.getRarity().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(book.getName(false))+"</span>");
	}
	
	@Override
	public String getFormattedEntry() {
		return "<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>"+name+"</span>: "+description;
	}
	
	@Override
	public String getMainDialogueDescription() {
		return "<p style='text-align:center;'>"
				+ "<b style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+";'>Book added to Lilaya's library</b>"
				+ "<br/>"
				+ description
			+ "</p>";
	}
}
