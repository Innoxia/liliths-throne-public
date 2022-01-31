package com.lilithsthrone.game.dialogue.eventLog;

import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.85
 * @version 0.4
 * @author Innoxia
 */
public class EventLogEntryAttributeChange extends EventLogEntry {
	
	private AbstractAttribute attribute;
	private float change;
	
	public EventLogEntryAttributeChange(AbstractAttribute attribute, float change, boolean isCore) {
		super((isCore
						?"<span style='color:"+attribute.getColour().toWebHexString()+";'>Core "+Util.capitaliseSentence(attribute.getName())+"</span>"
						:"<span style='color:"+attribute.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(attribute.getName())+"</span>"),
				(isCore
						?(change>0
								?"<span style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+";'>+"+change+"</span>"
								:"<span style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>"+change+"</span>")
						:(change>0
								?"<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>+"+change+"</span>"
								:"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>"+change+"</span>")));
		this.attribute = attribute;
		this.change = change;
	}
	
	@Override
	public String getFormattedEntry() {
		return name+": "+description;
	}
	
	@Override
	public String getMainDialogueDescription() {
		return Main.game.getPlayer().getAttributeChangeText(attribute, change);
	}
}
