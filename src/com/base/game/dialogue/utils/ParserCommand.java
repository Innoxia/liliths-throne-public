package com.base.game.dialogue.utils;

import java.util.List;

import com.base.game.character.body.types.BodyPartType;
import com.base.utils.Colour;

public abstract class ParserCommand {
	private boolean allowsCapitalisation, allowPronoun;
	private String description, arguments;
	private List<String> tags;
	private BodyPartType relatedBodyPart;
	
	public ParserCommand(List<String> tags, boolean allowsCapitalisation, boolean allowPronoun, String arguments, String description) {
		this(tags, allowsCapitalisation, allowPronoun, arguments, description, BodyPartType.GENERIC);
	}
	
	public ParserCommand(List<String> tags, boolean allowsCapitalisation, boolean allowPronoun, String arguments, String description, BodyPartType relatedBodyPart) {
		this.tags=tags;
		this.allowsCapitalisation=allowsCapitalisation;
		this.allowPronoun=allowPronoun;
		this.description=description;
		this.arguments=arguments;
		this.relatedBodyPart=relatedBodyPart;
	}
	
	public boolean isAllowsCapitalisation() {
		return allowsCapitalisation;
	}
	
	public boolean isAllowsPronoun() {
		return allowPronoun;
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
	
	public BodyPartType getRelatedBodyPart() {
		return relatedBodyPart;
	}
	
	public String getArgumentExample() {
		return "Example argument";
	}

	public String getExampleBeforeParse(String target, String arguments) {
		return "[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT+";'>"+target+"</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT+";'>"+tags.get(0)+"</i>"
				+(arguments==""?"":"<i style='color:"+Colour.CLOTHING_YELLOW+";'>("+arguments+")</i>")+"]";
	}
	
	public abstract String parse(String command, String arguments, String target);
	
	/**
	 * Some methods might return a null or empty string for a descriptor. This method accounts for that, applying a descriptor if one is available and then returning the descriptor + name combination.
	 */
	protected static String applyDescriptor(String descriptor, String name) {
		if(descriptor==null)
			return name;
		
		return (descriptor.length() > 0 ? descriptor + " " : "") + name;
	}
	
	/**
	 * Some methods might return a null or empty string for a determiner. This method accounts for that, applying a special determiner if one is available and then returning the descriptor + name combination.
	 */
	protected static String applyDeterminer(String descriptor, String input) {
		if(descriptor==null)
			return input;
		
		return (descriptor.length() > 0 ? descriptor + " " : (UtilText.isVowel(input.charAt(0))?"an ":"a ")) + input;
	}
}
