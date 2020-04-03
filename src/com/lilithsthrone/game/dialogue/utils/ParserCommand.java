package com.lilithsthrone.game.dialogue.utils;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.BodyPartType;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.?
 * @version 0.3.3
 * @author Innoxia
 */
public abstract class ParserCommand {
	
	private boolean allowsCapitalisation;
	private boolean allowPronoun;
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
		return "[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>"+target+"</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>"+tags.get(0)+"</i>"
				+(arguments==""?"":"<i style='color:"+PresetColour.CLOTHING_YELLOW.toWebHexString()+";'>("+arguments+")</i>")+"]";
	}
	
	public abstract String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character);
	
	/**
	 * Some methods might return a null or empty string for a descriptor. This method accounts for that, applying a descriptor if one is available and then returning the descriptor + name combination.
	 */
	protected static String applyDescriptor(String descriptor, String name) {
		if(descriptor==null) {
			return name;
		}
		
		return (descriptor.length() > 0 ? descriptor + (descriptor.charAt(descriptor.length()-1)=='-'?"":" ") : "") + name;
	}
	
	/**
	 * Some methods might return a null or empty string for a determiner. This method accounts for that, applying a special determiner if one is available and then returning the descriptor + name combination.
	 */
	protected String applyDeterminer(String descriptor, String input) {
		if(descriptor==null)
			return input;
		
		return (descriptor.length() > 0 ? descriptor + " " : (UtilText.isVowel(input.charAt(0))?"an ":"a ")) + input;
	}
}
