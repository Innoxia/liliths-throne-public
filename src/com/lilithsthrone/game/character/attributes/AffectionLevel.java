package com.lilithsthrone.game.character.attributes;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.78
 * @version 0.1.86
 * @author Innoxia
 */
public enum AffectionLevel {
	
	/** -100 to -90*/
	NEGATIVE_FIVE_LOATHE("loathing", "loathes", -100, -90, Colour.AFFECTION_NEGATIVE_FIVE),

	/** -90 to -70*/
	NEGATIVE_FOUR_HATE("hatred", "hates", -90, -70, Colour.AFFECTION_NEGATIVE_FOUR),

	/** -70 to -50*/
	NEGATIVE_THREE_STRONG_DISLIKE("strong dislike", "strongly dislikes", -70, -50, Colour.AFFECTION_NEGATIVE_THREE),

	/** -50 to -30*/
	NEGATIVE_TWO_DISLIKE("dislike", "dislikes", -50, -30, Colour.AFFECTION_NEGATIVE_TWO),

	/** -30 to -10*/
	NEGATIVE_ONE_ANNOYED("annoyed", "is annoyed with", -30, -10, Colour.AFFECTION_NEGATIVE_ONE),

	/** -10 to 10*/
	ZERO_NEUTRAL("neutral", "neither likes nor dislikes", -10, 10, Colour.AFFECTION_NEUTRAL),

	/** 10 to 30*/
	POSITIVE_ONE_FRIENDLY("friendly", "is friendly towards", 10, 30, Colour.AFFECTION_POSITIVE_ONE),

	/** 30 to 50*/
	POSITIVE_TWO_LIKE("likes", "likes", 30, 50, Colour.AFFECTION_POSITIVE_TWO),

	/** 50 to 70*/
	POSITIVE_THREE_CARING("caring", "cares for", 50, 70, Colour.AFFECTION_POSITIVE_THREE),

	/** 70 to 90*/
	POSITIVE_FOUR_LOVE("love", "loves", 70, 90, Colour.AFFECTION_POSITIVE_FOUR),

	/** 90 to 100*/
	POSITIVE_FIVE_WORSHIP("worshipping", "worships", 90, 100, Colour.AFFECTION_POSITIVE_FIVE);
	
	
	private String name;
	private String descriptor;
	private int minimumValue, maximumValue;
	private Colour colour;

	private AffectionLevel(String name, String descriptor, int minimumValue, int maximumValue, Colour colour) {
		this.name = name;
		this.descriptor = descriptor;
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

	/**
	 * To fit into a sentence such as:</br>
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

	public static AffectionLevel getAffectionLevelFromValue(float value){
		for(AffectionLevel al : AffectionLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue())
				return al;
		}
		return POSITIVE_FIVE_WORSHIP;
	}
}
