package com.base.game.dialogue.eventLog;

import com.base.main.Main;
import com.base.utils.BaseColour;
import com.base.utils.Colour;
import com.base.utils.Util;

/**
 * @since 0.1.85
 * @version 0.1.85
 * @author Innoxia
 */
public class EventLogEntryEncyclopediaUnlock extends EventLogEntry {

	private static final long serialVersionUID = 1L;
	
	public EventLogEntryEncyclopediaUnlock(String description, Colour highlightDescriptionColour) {
		super(Main.game.getMinutesPassed(), "Encyclopedia",  "<span style='color:"+highlightDescriptionColour.toWebHexString()+";'>"+Util.capitaliseSentence(description)+"</span>");
	}
	
	public EventLogEntryEncyclopediaUnlock(String description, BaseColour highlightDescriptionColour) {
		super(Main.game.getMinutesPassed(), "Encyclopedia",  "<span style='color:"+highlightDescriptionColour.toWebHexString()+";'>"+Util.capitaliseSentence(description)+"</span>");
	}
	
	@Override
	public String getFormattedEntry() {
		return "<span style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Encyclopedia</span>: "+description;
	}
	
	@Override
	public String getMainDialogueDescription() {
		return "<p style='text-align:center;'>"
				+ "<b style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>New entry in your phone's encyclopedia</b>"
				+ "</br>"
				+ description
			+ "</p>";
	}
	
}
