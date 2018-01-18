package com.lilithsthrone.game.dialogue.utils;

import java.util.List;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.?
 * @version 0.1.?
 * @author Innoxia
 */
public abstract class ParserConditionalCommand {
	private String description, arguments;
	private List<String> tags;
	
	public ParserConditionalCommand(List<String> tags, String arguments, String description) {
		this.tags=tags;
		this.description=description;
		this.arguments=arguments;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getArguments() {
		return arguments;
	}

	public List<String> getTags() {
		return tags;
	}
	
	public String getArgumentExample() {
		return "Example argument";
	}

	public String getExampleBeforeParse(String target, String arguments) {
		return "[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>"+target+"</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>"+tags.get(0)+"</i>"
				+(arguments==""?"":"<i style='color:"+Colour.CLOTHING_YELLOW.toWebHexString()+";'>("+arguments+")</i>")+"]";
	}
	
	public abstract boolean process(String command, String arguments, String target);
	
}
