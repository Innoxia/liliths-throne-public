package com.lilithsthrone.game.character.attributes;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.82
 * @version 0.1.82
 * @author Innoxia
 */
public enum ObedienceLevel {
	
	
	NEGATIVE_FIVE_REBELLIOUS("rebellious", -100, -90, PresetColour.AFFECTION_NEGATIVE_FIVE),
	
	NEGATIVE_FOUR_DEFIANT("defiant", -90, -70, PresetColour.AFFECTION_NEGATIVE_FOUR),
	
	NEGATIVE_THREE_STRONG_INSUBORDINATE("insubordinate", -70, -50, PresetColour.AFFECTION_NEGATIVE_THREE),
	
	NEGATIVE_TWO_UNRULY("unruly", -50, -30, PresetColour.AFFECTION_NEGATIVE_TWO),
	
	NEGATIVE_ONE_DISOBEDIENT("disobedient", -30, -10, PresetColour.AFFECTION_NEGATIVE_ONE),

	ZERO_FREE_WILLED("free-willed", -10, 10, PresetColour.AFFECTION_NEUTRAL),

	POSITIVE_ONE_AGREEABLE("agreeable", 10, 30, PresetColour.AFFECTION_POSITIVE_ONE),

	POSITIVE_TWO_OBEDIENT("obedient", 30, 50, PresetColour.AFFECTION_POSITIVE_TWO),

	POSITIVE_THREE_DISCIPLINED("disciplined", 50, 70, PresetColour.AFFECTION_POSITIVE_THREE),

	POSITIVE_FOUR_DUTIFUL("dutiful", 70, 90, PresetColour.AFFECTION_POSITIVE_FOUR),

	POSITIVE_FIVE_SUBSERVIENT("subservient", 90, 100, PresetColour.AFFECTION_POSITIVE_FIVE);
	
	
	private String name;
	private int minimumValue, maximumValue;
	private Colour colour;

	private ObedienceLevel(String name, int minimumValue, int maximumValue, Colour colour) {
		this.name = name;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour = colour;
	}
	
	private static String getObedienceName(ObedienceLevel affectionLevel, boolean withColour) {
		if(withColour) {
			return "<span style='color:"+affectionLevel.getColour().toWebHexString()+";'>"+affectionLevel.getName()+"</span>";
		} else {
			return affectionLevel.getName();
		}
	}
	
	public static String getDescription(GameCharacter character, ObedienceLevel affectionLevel, boolean withColour, boolean longDescription) {
		StringBuilder sb = new StringBuilder();
		
		String obedienceName = getObedienceName(affectionLevel, withColour);
		
		switch(affectionLevel) {
			case NEGATIVE_FIVE_REBELLIOUS:
				sb.append(UtilText.parse(character, "[npc.Name] [npc.is] "+obedienceName+(longDescription?", and outright [npc.verb(refuse)] to obey any command.":".")));
				break;
			case NEGATIVE_FOUR_DEFIANT:
				sb.append(UtilText.parse(character, "[npc.Name] [npc.is] "+obedienceName+(longDescription?", and only [npc.verb(obey)] commands when "+(character.isRaceConcealed()?"they are":"[npc.sheIs]")+" physically forced to.":".")));
				break;
			case NEGATIVE_THREE_STRONG_INSUBORDINATE:
				sb.append(UtilText.parse(character, "[npc.Name] [npc.is] "+obedienceName+(longDescription?", and sometimes [npc.verb(refuse)] to obey commands.":".")));
				break;
			case NEGATIVE_TWO_UNRULY:
				sb.append(UtilText.parse(character, "[npc.Name] [npc.is] "+obedienceName+(longDescription?", and will refuse to obey commands if"
						+(character.isRaceConcealed()?" they think that they":" [npc.she] [npc.verb(think)] that [npc.she]")
						+ " can get away with it.":".")));
				break;
			case NEGATIVE_ONE_DISOBEDIENT:
				sb.append(UtilText.parse(character, "[npc.Name] [npc.is] "+obedienceName+(longDescription?", and will often complain about having to follow orders.":".")));
				break;
			case ZERO_FREE_WILLED:
				sb.append(UtilText.parse(character, "[npc.Name] [npc.is] "+obedienceName+(longDescription?" and will do whatever "+(character.isRaceConcealed()?"they want.":"[npc.she] [npc.verb(want)]."):".")));
				break;
			case POSITIVE_ONE_AGREEABLE:
				sb.append(UtilText.parse(character, "[npc.Name] [npc.is] "+obedienceName+(longDescription?", and will carry out most orders without complaint.":".")));
				break;
			case POSITIVE_TWO_OBEDIENT:
				sb.append(UtilText.parse(character, "[npc.Name] [npc.is] "+obedienceName+(longDescription?", and will do almost anything that "+(character.isRaceConcealed()?"they are":"[npc.sheIs]")+" told.":".")));
				break;
			case POSITIVE_THREE_DISCIPLINED:
				sb.append(UtilText.parse(character, "[npc.Name] [npc.is] "+obedienceName+(longDescription?", and will carry out any order without complaint.":".")));
				break;
			case POSITIVE_FOUR_DUTIFUL:
				sb.append(UtilText.parse(character, "[npc.Name] [npc.is] "+obedienceName+(longDescription?", and will often go above and beyond whatever's asked of [npc.herHim].":".")));
				break;
			case POSITIVE_FIVE_SUBSERVIENT:
				sb.append(UtilText.parse(character, "[npc.Name] [npc.is] "+obedienceName+(longDescription?", and will do absolutely anything that "+(character.isRaceConcealed()?"they are":"[npc.sheIs]")+" ordered to do.":".")));
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
