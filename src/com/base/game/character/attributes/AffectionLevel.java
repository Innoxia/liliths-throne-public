package com.base.game.character.attributes;

import com.base.game.character.GameCharacter;
import com.base.game.dialogue.utils.UtilText;
import com.base.utils.Colour;

/**
 * @since 0.1.78
 * @version 0.1.82
 * @author Innoxia
 */
public enum AffectionLevel {
	
	NEGATIVE_FIVE_LOATHE("loathe", -100, -90, Colour.AFFECTION_NEGATIVE_FIVE),
	
	NEGATIVE_FOUR_HATE("hate", -90, -70, Colour.AFFECTION_NEGATIVE_FOUR),
	
	NEGATIVE_THREE_STRONG_DISLIKE("strong dislike", -70, -50, Colour.AFFECTION_NEGATIVE_THREE),
	
	NEGATIVE_TWO_DISLIKE("dislike", -50, -30, Colour.AFFECTION_NEGATIVE_TWO),
	
	NEGATIVE_ONE_ANNOYED("annoyed", -30, -10, Colour.AFFECTION_NEGATIVE_ONE),

	ZERO_NEUTRAL("neutral", -10, 10, Colour.AFFECTION_NEUTRAL),

	POSITIVE_ONE_FRIENDLY("friendly", 10, 30, Colour.AFFECTION_POSITIVE_ONE),

	POSITIVE_TWO_LIKE("like", 30, 50, Colour.AFFECTION_POSITIVE_TWO),

	POSITIVE_THREE_CARING("caring", 50, 70, Colour.AFFECTION_POSITIVE_THREE),

	POSITIVE_FOUR_LOVE("love", 70, 90, Colour.AFFECTION_POSITIVE_FOUR),

	POSITIVE_FIVE_WORSHIP("worship", 90, 100, Colour.AFFECTION_POSITIVE_FIVE);
	
	
	private String name;
	private int minimumValue, maximumValue;
	private Colour colour;

	private AffectionLevel(String name, int minimumValue, int maximumValue, Colour colour) {
		this.name = name;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour = colour;
	}
	
	private static StringBuilder sb = new StringBuilder();
	public static String getDescription(GameCharacter character, GameCharacter target, AffectionLevel affectionLevel, boolean withColour) {
		sb.setLength(0);
		
		switch(affectionLevel) {
			case NEGATIVE_FIVE_LOATHE:
				if(withColour) {
					sb.append(UtilText.parse(character, "[npc.Name] <b style='color:"+affectionLevel.getColour().toWebHexString()+";'>loathes</b> "+(target.isPlayer()?"you":target.getName("the"))+"."));
				} else {
					sb.append(UtilText.parse(character, "[npc.Name] loathes "+(target.isPlayer()?"you":target.getName("the"))+"."));
				}
				break;
			case NEGATIVE_FOUR_HATE:
				if(withColour) {
					sb.append(UtilText.parse(character, "[npc.Name] <b style='color:"+affectionLevel.getColour().toWebHexString()+";'>hates</b> "+(target.isPlayer()?"you":target.getName("the"))+"."));
				} else {
					sb.append(UtilText.parse(character, "[npc.Name] hates "+(target.isPlayer()?"you":target.getName("the"))+"."));
				}
				break;
			case NEGATIVE_THREE_STRONG_DISLIKE:
				if(withColour) {
					sb.append(UtilText.parse(character, "[npc.Name] <b style='color:"+affectionLevel.getColour().toWebHexString()+";'>strongly dislikes</b> "+(target.isPlayer()?"you":target.getName("the"))+"."));
				} else {
					sb.append(UtilText.parse(character, "[npc.Name] strongly dislikes "+(target.isPlayer()?"you":target.getName("the"))+"."));
				}
				break;
			case NEGATIVE_TWO_DISLIKE:
				if(withColour) {
					sb.append(UtilText.parse(character, "[npc.Name] <b style='color:"+affectionLevel.getColour().toWebHexString()+";'>dislikes</b> "+(target.isPlayer()?"you":target.getName("the"))+"."));
				} else {
					sb.append(UtilText.parse(character, "[npc.Name] dislikes "+(target.isPlayer()?"you":target.getName("the"))+"."));
				}
				break;
			case NEGATIVE_ONE_ANNOYED:
				if(withColour) {
					sb.append(UtilText.parse(character, "[npc.Name] is <b style='color:"+affectionLevel.getColour().toWebHexString()+";'>annoyed</b> with "+(target.isPlayer()?"you":target.getName("the"))+"."));
				} else {
					sb.append(UtilText.parse(character, "[npc.Name] is annoyed with "+(target.isPlayer()?"you":target.getName("the"))+"."));
				}
				break;
			case ZERO_NEUTRAL:
				if(withColour) {
					sb.append(UtilText.parse(character, "[npc.Name] is <b style='color:"+affectionLevel.getColour().toWebHexString()+";'>indifferent</b> towards "+(target.isPlayer()?"you":target.getName("the"))+"."));
				} else {
					sb.append(UtilText.parse(character, "[npc.Name] is indifferent towards "+(target.isPlayer()?"you":target.getName("the"))+"."));
				}
				break;
			case POSITIVE_ONE_FRIENDLY:
				if(withColour) {
					sb.append(UtilText.parse(character, "[npc.Name] is <b style='color:"+affectionLevel.getColour().toWebHexString()+";'>friendly</b> towards "+(target.isPlayer()?"you":target.getName("the"))+"."));
				} else {
					sb.append(UtilText.parse(character, "[npc.Name] is friendly towards "+(target.isPlayer()?"you":target.getName("the"))+"."));
				}
				break;
			case POSITIVE_TWO_LIKE:
				if(withColour) {
					sb.append(UtilText.parse(character, "[npc.Name] <b style='color:"+affectionLevel.getColour().toWebHexString()+";'>likes</b> "+(target.isPlayer()?"you":target.getName("the"))+"."));
				} else {
					sb.append(UtilText.parse(character, "[npc.Name] likes "+(target.isPlayer()?"you":target.getName("the"))+"."));
				}
				break;
			case POSITIVE_THREE_CARING:
				if(withColour) {
					sb.append(UtilText.parse(character, "[npc.Name] <b style='color:"+affectionLevel.getColour().toWebHexString()+";'>cares about</b> "+(target.isPlayer()?"you":target.getName("the"))+"."));
				} else {
					sb.append(UtilText.parse(character, "[npc.Name] cares about "+(target.isPlayer()?"you":target.getName("the"))+"."));
				}
				break;
			case POSITIVE_FOUR_LOVE:
				if(withColour) {
					sb.append(UtilText.parse(character, "[npc.Name] <b style='color:"+affectionLevel.getColour().toWebHexString()+";'>loves</b> "+(target.isPlayer()?"you":target.getName("the"))+"."));
				} else {
					sb.append(UtilText.parse(character, "[npc.Name] loves "+(target.isPlayer()?"you":target.getName("the"))+"."));
				}
				break;
			case POSITIVE_FIVE_WORSHIP:
				if(withColour) {
					sb.append(UtilText.parse(character, "[npc.Name] <b style='color:"+affectionLevel.getColour().toWebHexString()+";'>worships</b> "+(target.isPlayer()?"you":target.getName("the"))+"."));
				} else {
					sb.append(UtilText.parse(character, "[npc.Name] worships "+(target.isPlayer()?"you":target.getName("the"))+"."));
				}
				break;
		}
		
		return sb.toString();
	}
	
	public String getName() {
		return name;
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

	public static AffectionLevel getAffectionLevelFromValue(float value){
		for(AffectionLevel al : AffectionLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue())
				return al;
		}
		return POSITIVE_FIVE_WORSHIP;
	}
}
