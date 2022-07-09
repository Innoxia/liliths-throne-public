package com.lilithsthrone.game.character.attributes;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.78
 * @version 0.4.4
 * @author Innoxia
 */
public enum AffectionLevel {
	
	/** -100 to -90*/
	NEGATIVE_FIVE_LOATHE("loathing", "loathes", -100, -90, PresetColour.AFFECTION_NEGATIVE_FIVE, true),

	/** -90 to -70*/
	NEGATIVE_FOUR_HATE("hatred", "hates", -90, -70, PresetColour.AFFECTION_NEGATIVE_FOUR, true),

	/** -70 to -50*/
	NEGATIVE_THREE_STRONG_DISLIKE("strong dislike", "strongly dislikes", -70, -50, PresetColour.AFFECTION_NEGATIVE_THREE, true),

	/** -50 to -30*/
	NEGATIVE_TWO_DISLIKE("dislike", "dislikes", -50, -30, PresetColour.AFFECTION_NEGATIVE_TWO, true),

	/** -30 to -10*/
	NEGATIVE_ONE_ANNOYED("annoyed", "is annoyed with", -30, -10, PresetColour.AFFECTION_NEGATIVE_ONE, true),

	/** -10 to 10*/
	ZERO_NEUTRAL("neutral", "neither likes nor dislikes", -10, 10, PresetColour.AFFECTION_NEUTRAL, true),

	/** 10 to 30*/
	POSITIVE_ONE_FRIENDLY("friendly", "is friendly towards", 10, 30, PresetColour.AFFECTION_POSITIVE_ONE, false),

	/** 30 to 50*/
	POSITIVE_TWO_LIKE("likes", "likes", 30, 50, PresetColour.AFFECTION_POSITIVE_TWO, false),

	/** 50 to 70*/
	POSITIVE_THREE_CARING("caring", "cares for", 50, 70, PresetColour.AFFECTION_POSITIVE_THREE, false),

	/** 70 to 90*/
	POSITIVE_FOUR_LOVE("love", "loves", 70, 90, PresetColour.AFFECTION_POSITIVE_FOUR, false),

	/** 90 to 100*/
	POSITIVE_FIVE_WORSHIP("adoring", "adores", 90, 100, PresetColour.AFFECTION_POSITIVE_FIVE, false);
	
	
	private String name;
	private String descriptor;
	private int minimumValue, maximumValue;
	private Colour colour;
	private boolean willFightPlayer;

	private AffectionLevel(String name, String descriptor, int minimumValue, int maximumValue, Colour colour, boolean willFightPlayer) {
		this.name = name;
		this.descriptor = descriptor;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour = colour;
		this.willFightPlayer = willFightPlayer;
	}
	
	/**
	 * In the format: "Rose loves Lilaya."
	 */
	public static String getDescription(GameCharacter character, GameCharacter target, boolean withColour) {
		StringBuilder sb = new StringBuilder();
		AffectionLevel affectionLevel = character.getAffectionLevel(target);
		
		switch(affectionLevel) {
			case NEGATIVE_FIVE_LOATHE:
				sb.append(UtilText.parse(character, target, "[npc.Name] "+applyColourWrapper("[npc.verb(loathe)]", affectionLevel, withColour)+" [npc2.name]."));
				break;
			case NEGATIVE_FOUR_HATE:
				sb.append(UtilText.parse(character, target, "[npc.Name] "+applyColourWrapper("[npc.verb(hate)]", affectionLevel, withColour)+" [npc2.name]."));
				break;
			case NEGATIVE_THREE_STRONG_DISLIKE:
				sb.append(UtilText.parse(character, target, "[npc.Name] strongly "+applyColourWrapper("[npc.verb(dislike)]", affectionLevel, withColour)+" [npc2.name]."));
				break;
			case NEGATIVE_TWO_DISLIKE:
				sb.append(UtilText.parse(character, target, "[npc.Name] "+applyColourWrapper("[npc.verb(dislike)]", affectionLevel, withColour)+" [npc2.name]."));
				break;
			case NEGATIVE_ONE_ANNOYED:
				sb.append(UtilText.parse(character, target, "[npc.Name] [npc.is] "+applyColourWrapper("annoyed", affectionLevel, withColour)+" with [npc2.name]."));
				break;
			case ZERO_NEUTRAL:
				sb.append(UtilText.parse(character, target, "[npc.Name] [npc.is] "+applyColourWrapper("indifferent", affectionLevel, withColour)+" towards [npc2.name]."));
				break;
			case POSITIVE_ONE_FRIENDLY:
				sb.append(UtilText.parse(character, target, "[npc.Name] [npc.is] "+applyColourWrapper("friendly", affectionLevel, withColour)+" towards [npc2.name]."));
				break;
			case POSITIVE_TWO_LIKE:
				sb.append(UtilText.parse(character, target, "[npc.Name] "+applyColourWrapper("[npc.verb(like)]", affectionLevel, withColour)+" [npc2.name]."));
				break;
			case POSITIVE_THREE_CARING:
				sb.append(UtilText.parse(character, target, "[npc.Name] "+applyColourWrapper("[npc.verb(care)]", affectionLevel, withColour)+" about [npc2.name]."));
				break;
			case POSITIVE_FOUR_LOVE:
				sb.append(UtilText.parse(character, target, "[npc.Name] "+applyColourWrapper("[npc.verb(love)]", affectionLevel, withColour)+" [npc2.name]."));
				break;
			case POSITIVE_FIVE_WORSHIP:
				sb.append(UtilText.parse(character, target, "[npc.Name] "+applyColourWrapper("[npc.verb(adore)]", affectionLevel, withColour)+" [npc2.name]."));
				break;
		}
		
		return sb.toString();
	}
	
	/**
	 * In the format: "Rose worships Lilaya, and is head-over-heels in love with her."
	 */
	public static String getAttitudeDescription(GameCharacter character, GameCharacter target, boolean withColour) {
		StringBuilder sb = new StringBuilder();
		AffectionLevel affectionLevel = character.getAffectionLevel(target);
		
		sb.append("<p style='text-align:center;'><i>");
		switch(affectionLevel) {
			case NEGATIVE_FIVE_LOATHE:
				sb.append("[npc.Name] [npc.verb(make)] it very clear from [npc.her] behaviour that [npc.she] utterly "+applyColourWrapper("[npc.verb(loathe)] [npc2.name]", affectionLevel, withColour)+".");
				break;
			case NEGATIVE_FOUR_HATE:
				sb.append("[npc.Name] [npc.verb(make)] it very clear from [npc.her] behaviour that [npc.she] "+applyColourWrapper("[npc.verb(hate)] [npc2.name]", affectionLevel, withColour)+".");
				break;
			case NEGATIVE_THREE_STRONG_DISLIKE:
				sb.append("It's obvious from [npc.her] attitude that [npc.name] "+applyColourWrapper("strongly [npc.verb(dislike)] [npc2.name]", affectionLevel, withColour)+".");
				break;
			case NEGATIVE_TWO_DISLIKE:
				sb.append("[npc.NameIsFull] quite clearly "+applyColourWrapper("[npc.verb(dislike)] [npc2.name]", affectionLevel, withColour)+".");
				break;
			case NEGATIVE_ONE_ANNOYED:
				sb.append("[npc.NameIsFull] clearly "+applyColourWrapper("annoyed", affectionLevel, withColour)+" with [npc2.name].");
				break;
			case ZERO_NEUTRAL:
				sb.append("[npc.NameIsFull] acting in an "+applyColourWrapper("indifferent", affectionLevel, withColour)+" manner towards [npc2.name].");
				break;
			case POSITIVE_ONE_FRIENDLY:
				if(character.isAttractedTo(target)) {
					sb.append("[npc.NameIsFull] acting in a "+applyColourWrapper("friendly, flirtatious", affectionLevel, withColour)+" manner towards [npc2.name].");
				} else {
					sb.append("[npc.NameIsFull] acting in a "+applyColourWrapper("friendly", affectionLevel, withColour)+" manner towards [npc2.name].");
				}
				break;
			case POSITIVE_TWO_LIKE:
				if(character.isAttractedTo(target)) {
					sb.append("[npc.Name] quite clearly "+applyColourWrapper("[npc.verb(like)] [npc2.name]", affectionLevel, withColour)+", and [npc.verb(see)] [npc2.herHim] as more than just a friend.");
				} else {
					sb.append("[npc.Name] quite clearly "+applyColourWrapper("[npc.verb(like)] [npc2.name]", affectionLevel, withColour)+", and [npc.verb(see)] [npc2.herHim] as a close friend.");
				}
				break;
			case POSITIVE_THREE_CARING:
				if(character.isAttractedTo(target)) {
					sb.append("[npc.Name] quite clearly "+applyColourWrapper("cares about [npc2.name] a lot", affectionLevel, withColour)+", and [npc.is] deeply attracted to [npc2.herHim].");
				} else {
					sb.append("[npc.Name] quite clearly "+applyColourWrapper("cares about [npc2.name] a lot", affectionLevel, withColour)+", and [npc.verb(consider)] [npc2.herHim] to be [npc.her] best friend.");
				}
				break;
			case POSITIVE_FOUR_LOVE:
				if(character.isAttractedTo(target)) {
					sb.append("It's obvious from the way that [npc.name] [npc.verb(look)] at [npc2.name] that [npc.she] "+applyColourWrapper("[npc.verb(love)] [npc2.herHim]", affectionLevel, withColour)+".");
				} else {
					sb.append("It's obvious from the way that [npc.name] [npc.verb(act)] that [npc.she] "+applyColourWrapper("[npc.verb(love)] [npc2.name]", affectionLevel, withColour)+" in a purely platonic manner.");
				}
				break;
			case POSITIVE_FIVE_WORSHIP:
				if(character.isAttractedTo(target)) {
					sb.append("[npc.Name] utterly "+applyColourWrapper("[npc.verb(adore)] [npc2.name]", affectionLevel, withColour)+", and [npc.is] head-over-heels in love with [npc2.herHim].");
				} else {
					sb.append("[npc.Name] utterly "+applyColourWrapper("[npc.verb(adore)] [npc2.name]", affectionLevel, withColour)+", and would do almost anything [npc2.she] asked of [npc.herHim].");
				}
				break;
		}
		sb.append("</i></p>");
		
		return UtilText.parse(character, target, sb.toString());
	}
	
	private static String applyColourWrapper(String input, AffectionLevel affection, boolean withColour) {
		if(!withColour) {
			return input;
		}
		return "<span style='color:"+affection.getColour().toWebHexString()+";'>"+input+"</span>";
	}
	
	public String getName() {
		return name;
	}

	/**
	 * To fit into a sentence such as:<br/>
	 * "Due to the fact that Kate "+getDescriptor()+" you..."
	 * @return
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public int getMinimumValue() {
		return minimumValue;
	}

	public int getMaximumValue() {
		return maximumValue;
	}
	
	public int getMedianValue() {
		return (minimumValue + maximumValue) / 2;
	}
	
	public Colour getColour() {
		return colour;
	}

	public boolean isWillFightPlayer() {
		return willFightPlayer;
	}

	public static AffectionLevel getAffectionLevelFromValue(float value){
		for(AffectionLevel al : AffectionLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue())
				return al;
		}
		return POSITIVE_FIVE_WORSHIP;
	}
	
	public boolean isLessThan(AffectionLevel levelToCompare) {
		return this.getMedianValue()<levelToCompare.getMedianValue();
	}
	
	public boolean isGreaterThan(AffectionLevel levelToCompare) {
		return this.getMedianValue()>levelToCompare.getMedianValue();
	}
}
