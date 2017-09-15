package com.base.game.dialogue.eventLog;

import com.base.game.character.attributes.Attribute;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;

/**
 * @since 0.1.85
 * @version 0.1.85
 * @author Innoxia
 */
public class EventLogEntryAttributeChange extends EventLogEntry {

	private static final long serialVersionUID = 1L;
	
	private Attribute attribute;
	private float change;
	
	public EventLogEntryAttributeChange(Attribute attribute, float change, boolean isCore) {
		super(Main.game.getMinutesPassed(),
				(isCore
						?"<span style='color:"+attribute.getColour().toWebHexString()+";'>Core "+Util.capitaliseSentence(attribute.getName())+"</span>"
						:"<span style='color:"+attribute.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(attribute.getName())+"</span>"),
				(isCore
						?(change>0
								?"<span style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>+"+change+"</span>"
								:"<span style='color:"+Colour.GENERIC_TERRIBLE.toWebHexString()+";'>"+change+"</span>")
						:(change>0
								?"<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>+"+change+"</span>"
								:"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>"+change+"</span>")));
		this.attribute = attribute;
		this.change = change;
	}
	
	@Override
	public String getFormattedEntry() {
		return name+": "+description;
	}
	
	@Override
	public String getMainDialogueDescription() {
		return attribute.getAttributeChangeText(Main.game.getPlayer(), change);
	}
}
