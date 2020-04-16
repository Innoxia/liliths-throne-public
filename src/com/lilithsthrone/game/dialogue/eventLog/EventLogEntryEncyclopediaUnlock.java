package com.lilithsthrone.game.dialogue.eventLog;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.BaseColour;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.85
 * @version 0.1.85
 * @author Innoxia
 */
public class EventLogEntryEncyclopediaUnlock extends EventLogEntry {

	
	public EventLogEntryEncyclopediaUnlock(String description, Colour highlightDescriptionColour) {
		super(Main.game.getMinutesPassed(), "Encyclopedia",  "<span style='color:"+highlightDescriptionColour.toWebHexString()+";'>"+Util.capitaliseSentence(description)+"</span>");
	}
	
	public EventLogEntryEncyclopediaUnlock(String description, BaseColour highlightDescriptionColour) {
		super(Main.game.getMinutesPassed(), "Encyclopedia",  "<span style='color:"+highlightDescriptionColour.toWebHexString()+";'>"+Util.capitaliseSentence(description)+"</span>");
	}
	
	@Override
	public String getFormattedEntry() {
		return "<span style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+";'>Encyclopedia</span>: "+description;
	}
	
	@Override
	public String getMainDialogueDescription() {
		return "<p style='text-align:center;'>"
				+ "<b style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+";'>New entry in your phone's encyclopedia</b>"
				+ "<br/>"
				+ description
			+ "</p>";
	}
	
}
