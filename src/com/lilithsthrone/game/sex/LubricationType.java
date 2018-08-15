package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.6?
 * @version 0.2.9
 * @author Innoxia
 */
public enum LubricationType {
	
	SALIVA("saliva", "saliva", false),
	
	MILK("[npc.milk]", "milk", false),
	
	PRECUM("precum", "precum", false),
	
	CUM("[npc.cum]", "cum", false),
	
	GIRLCUM("girlcum", "girlcum", false),
	
	ANAL_LUBE("anal lubricant", "anal lubricant", false), // This is only present if the anus has been transformed to be 'wetter' than usual

	SLIME("slime", "slime", false),
	
	OTHER("lubrication", "lubrication", false);
	
	private String name;
	private String nullOwnerName;
	private boolean plural;
	
	private LubricationType(String name, String nullOwnerName, boolean plural){
		this.name = name;
		this.nullOwnerName = nullOwnerName;
		this.plural = plural;
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
}
