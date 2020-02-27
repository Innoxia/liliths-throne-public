package com.lilithsthrone.game.combat;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.3.4.5
 * @version 0.3.4.5
 * @author Innoxia
 */
public enum CombatBehaviour {
	
	BALANCED("balanced",
			"Tell [npc.name] to use whatever combat moves [npc.she] feels are most appropriate.",
			"[npc.NameIsFull] using whichever combat move [npc.she] feels is most appropriate."),
	
	ATTACK("attack",
			"Tell [npc.name] to be more aggressive, and to favour main weapon attacks and offensive spells over any defensive abilities.",
			"Wherever possible, [npc.name] will choose to go on the offensive, preferring to use damaging attacks over defensive abilities."),
	
	DEFEND("defend",
			"Tell [npc.name] to be more defensive, and to favour abilities which prevent incoming damage.",
			"Wherever possible, [npc.name] will choose to go on the defensive, and will favour the usage of abilities which prevent incoming damage."),
	
	SEDUCE("seduce",
			"Tell [npc.name] to start seducing [npc.her] opponents instead of using damaging spells or weapon attacks.",
			"Wherever possible, [npc.name] will focus on seducing [npc.her] opponents instead of using damaging or defensive abilities."),
	
	SPELLS("spells",
			"Tell [npc.name] to focus on using all the spells which [npc.she] has at [npc.her] disposal.",
			"Wherever possible, [npc.name] will focus on using spells which [npc.she] has at [npc.her] disposal."),
	
	SUPPORT("support",
			"Tell [npc.name] to focus on using spells or other abilities which support [npc.her] allies.",
			"Wherever possible, [npc.name] will focus on using spells or other abilities which support [npc.her] allies.");

	String name;
	String orderDescription;
	String description;
	
	private CombatBehaviour(String name, String orderDescription, String description) {
		this.name = name;
		this.orderDescription = orderDescription;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getOrderDescription(GameCharacter character) {
		return UtilText.parse(character, orderDescription);
	}

	public String getDescription(GameCharacter character) {
		return UtilText.parse(character, description);
	}

}
