package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.6?
 * @version 0.3.7
 * @author Innoxia
 */
public enum LubricationType {
	
	SALIVA("saliva", "saliva", false, Colour.BASE_BLUE_LIGHT),
	
	MILK("[npc.milk]", "milk", false, Colour.MILK),
	
	PRECUM("precum", "precum", false, Colour.CUM),
	
	CUM("[npc.cum]", "cum", false, Colour.CUM),
	
	GIRLCUM("girlcum", "girlcum", false, Colour.GIRLCUM),
	
	ANAL_LUBE("anal lubricant", "anal lubricant", false, Colour.BASE_BLUE_LIGHT), // This is only present if the anus has been transformed to be 'wetter' than usual

	SLIME("slime", "slime", false, Colour.RACE_SLIME),
	
	OTHER("lubrication", "lubrication", false, Colour.BASE_BLUE_LIGHT);
	
	private String name;
	private String nullOwnerName;
	private boolean plural;
	private Colour colour;
	
	private LubricationType(String name, String nullOwnerName, boolean plural, Colour colour){
		this.name = name;
		this.nullOwnerName = nullOwnerName;
		this.plural = plural;
		this.colour = colour;
	}
	
	public boolean isPlural() {
		return plural;
	}
	
	public String getName(GameCharacter owner) {
		if(owner==null) {
			return nullOwnerName;
		}
		return UtilText.parse(owner, name);
	};
	
	public Colour getColour() {
		return colour;
	}
}
