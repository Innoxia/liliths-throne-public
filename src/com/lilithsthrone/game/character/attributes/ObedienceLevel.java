package com.lilithsthrone.game.character.attributes;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.82
 * @version 0.1.82
 * @author Innoxia
 */
public enum ObedienceLevel {
	
	
	NEGATIVE_FIVE_REBELLIOUS("rebellious", -100, -90, Colour.AFFECTION_NEGATIVE_FIVE),
	
	NEGATIVE_FOUR_DEFIANT("defiant", -90, -70, Colour.AFFECTION_NEGATIVE_FOUR),
	
	NEGATIVE_THREE_STRONG_INSUBORDINATE("insubordinate", -70, -50, Colour.AFFECTION_NEGATIVE_THREE),
	
	NEGATIVE_TWO_UNRULY("unruly", -50, -30, Colour.AFFECTION_NEGATIVE_TWO),
	
	NEGATIVE_ONE_DISOBEDIENT("disobedient", -30, -10, Colour.AFFECTION_NEGATIVE_ONE),

	ZERO_FREE_WILLED("free-willed", -10, 10, Colour.AFFECTION_NEUTRAL),

	POSITIVE_ONE_AGREEABLE("agreeable", 10, 30, Colour.AFFECTION_POSITIVE_ONE),

	POSITIVE_TWO_OBEDIENT("obedient", 30, 50, Colour.AFFECTION_POSITIVE_TWO),

	POSITIVE_THREE_DISCIPLINED("disciplined", 50, 70, Colour.AFFECTION_POSITIVE_THREE),

	POSITIVE_FOUR_DUTIFUL("dutiful", 70, 90, Colour.AFFECTION_POSITIVE_FOUR),

	POSITIVE_FIVE_SUBSERVIENT("subservient", 90, 100, Colour.AFFECTION_POSITIVE_FIVE);
	
	
	private String name;
	private int minimumValue, maximumValue;
	private Colour colour;

	private ObedienceLevel(String name, int minimumValue, int maximumValue, Colour colour) {
		this.name = name;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour = colour;
	}
	
	private static StringBuilder sb = new StringBuilder();
	public static String getDescription(GameCharacter character, ObedienceLevel affectionLevel, boolean withColour, boolean longDescription) {
		sb.setLength(0);
		
		switch(affectionLevel) {
			case NEGATIVE_FIVE_REBELLIOUS:
				if(withColour) {
					sb.append(UtilText.parse(character, "[npc.Name] [npc.is] <span style='color:"+affectionLevel.getColour().toWebHexString()+";'>rebellious</span>"+(longDescription?", and outright [npc.verb(refuse)] to obey any command.":".")));
				} else {
					sb.append(UtilText.parse(character, "[npc.Name] [npc.is] rebellious"+(longDescription?", and outright [npc.verb(refuse)] to obey any command.":".")));
				}
				break;
			case NEGATIVE_FOUR_DEFIANT:
				if(withColour) {
					sb.append(UtilText.parse(character, "[npc.Name] [npc.is] <span style='color:"+affectionLevel.getColour().toWebHexString()+";'>defiant</span>"+(longDescription?", and only [npc.verb(obey)] commands when [npc.sheIs] physically forced to.":".")));
				} else {
					sb.append(UtilText.parse(character, "[npc.Name] [npc.is] defiant"+(longDescription?", and only [npc.verb(obey)] commands when [npc.sheIs] physically forced to.":".")));
				}
				break;
			case NEGATIVE_THREE_STRONG_INSUBORDINATE:
				if(withColour) {
					sb.append(UtilText.parse(character, "[npc.Name] [npc.is] <span style='color:"+affectionLevel.getColour().toWebHexString()+";'>insubordinate</span>"+(longDescription?", and sometimes [npc.verb(refuse)] to obey commands.":".")));
				} else {
					sb.append(UtilText.parse(character, "[npc.Name] [npc.is] insubordinate"+(longDescription?", and sometimes [npc.verb(refuse)] to obey commands.":".")));
				}
				break;
			case NEGATIVE_TWO_UNRULY:
				if(withColour) {
					sb.append(UtilText.parse(character, "[npc.Name] [npc.is] <span style='color:"+affectionLevel.getColour().toWebHexString()+";'>unruly</span>"
							+(longDescription?", and will refuse to obey commands if [npc.she] [npc.verb(think)] that [npc.she] can get away with it.":".")));
				} else {
					sb.append(UtilText.parse(character, "[npc.Name] [npc.is] unruly"+(longDescription?", and will refuse to obey commands if [npc.she] [npc.verb(think)] that [npc.she] can get away with it.":".")));
				}
				break;
			case NEGATIVE_ONE_DISOBEDIENT:
				if(withColour) {
					sb.append(UtilText.parse(character, "[npc.Name] [npc.is] <span style='color:"+affectionLevel.getColour().toWebHexString()+";'>disobedient</span>"+(longDescription?", and will often complain about having to follow orders.":".")));
				} else {
					sb.append(UtilText.parse(character, "[npc.Name] [npc.is] disobedient"+(longDescription?", and will often complain about having to follow orders.":".")));
				}
				break;
			case ZERO_FREE_WILLED:
				if(withColour) {
					sb.append(UtilText.parse(character, "[npc.Name] [npc.is] <span style='color:"+affectionLevel.getColour().toWebHexString()+";'>free-willed</span>"+(longDescription?", and will do whatever [npc.she] [npc.verb(want)].":".")));
				} else {
					sb.append(UtilText.parse(character, "[npc.Name] [npc.is] free-willed"+(longDescription?" and will do whatever [npc.she] [npc.verb(want)].":".")));
				}
				break;
			case POSITIVE_ONE_AGREEABLE:
				if(withColour) {
					sb.append(UtilText.parse(character, "[npc.Name] [npc.is] <span style='color:"+affectionLevel.getColour().toWebHexString()+";'>agreeable</span>"+(longDescription?", and will carry out most orders without complaint.":".")));
				} else {
					sb.append(UtilText.parse(character, "[npc.Name] [npc.is] agreeable"+(longDescription?", and will carry out most orders without complaint.":".")));
				}
				break;
			case POSITIVE_TWO_OBEDIENT:
				if(withColour) {
					sb.append(UtilText.parse(character, "[npc.Name] [npc.is] <span style='color:"+affectionLevel.getColour().toWebHexString()+";'>obedient</span>"+(longDescription?", and will do almost anything that [npc.sheIs] told.":".")));
				} else {
					sb.append(UtilText.parse(character, "[npc.Name] [npc.is] obedient"+(longDescription?", and will do almost anything that [npc.sheIs] told.":".")));
				}
				break;
			case POSITIVE_THREE_DISCIPLINED:
				if(withColour) {
					sb.append(UtilText.parse(character, "[npc.Name] [npc.is] <span style='color:"+affectionLevel.getColour().toWebHexString()+";'>disciplined</span>"+(longDescription?", and will carry out any order without complaint.":".")));
				} else {
					sb.append(UtilText.parse(character, "[npc.Name] [npc.is] disciplined"+(longDescription?", and will carry out any order without complaint.":".")));
				}
				break;
			case POSITIVE_FOUR_DUTIFUL:
				if(withColour) {
					sb.append(UtilText.parse(character, "[npc.Name] [npc.is] <span style='color:"+affectionLevel.getColour().toWebHexString()+";'>dutiful</span>"+(longDescription?", and will often go above and beyond whatever's asked of [npc.herHim].":".")));
				} else {
					sb.append(UtilText.parse(character, "[npc.Name] [npc.is] dutiful"+(longDescription?", and will often go above and beyond whatever's asked of [npc.herHim].":".")));
				}
				break;
			case POSITIVE_FIVE_SUBSERVIENT:
				if(withColour) {
					sb.append(UtilText.parse(character, "[npc.Name] [npc.is] <span style='color:"+affectionLevel.getColour().toWebHexString()+";'>subservient</span>"+(longDescription?", and will do absolutely anything that [npc.sheIs] ordered to do.":".")));
				} else {
					sb.append(UtilText.parse(character, "[npc.Name] [npc.is] subservient"+(longDescription?", and will do absolutely anything that [npc.sheIs] ordered to do.":".")));
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

	public static ObedienceLevel getObedienceLevelFromValue(float value){
		for(ObedienceLevel al : ObedienceLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue())
				return al;
		}
		return POSITIVE_FIVE_SUBSERVIENT;
	}
}
