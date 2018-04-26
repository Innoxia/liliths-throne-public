package com.lilithsthrone.game.combat;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.2.1
 * @version 0.2.4
 * @author Innoxia
 */
public enum SpellSchool {

	EARTH("earth",
			"<p>"
				+ "Spells in the school of Earth are focused on manipulating both solid objects and pure waves of force."
			+ "</p>"
			+ "<p>"
				+ "As with all schools of the arcane, the vast majority of Earth practitioners are demons, and can earn a considerable salary by using their telekenetic powers to aid with construction and heavy lifting."
				+ " Perhaps due to these lucrative applications, the school of Earth is the most widely-practised and popular of all the arcane schools."
			+ "</p>"
			+ "<p>"
				+ "A prerequisite to harnessing Earth spells is the ability to freely manipulate solid, non-organic matter."
				+ " Easily learned by anyone possessing a demon-strength aura, and outlined in the introduction of all Earth spell books, this ability allows the practitioner to change the colour and material of any object."
			+ "</p>",
			"Dye clothing without a dye-brush.",
			Colour.BASE_BROWN),

	WATER("water",
			"<p>"
				+ "Spells in the school of Water are focused on infusing liquids with arcane energy in order to manipulate their movement and temperature."
			+ "</p>"
			+ "<p>"
				+ "As with all schools of the arcane, the vast majority of Water practitioners are demons, and mainly use their spells to assist with the maintenance of waterways, and to repair and install plumbing."
				+ " Despite the lack of glamour, a student of Water can complete these tasks in a fraction of the time that it would take a regular person to do manually, allowing them to earn a considerable amount of money for their work."
			+ "</p>"
			+ "<p>"
				+ "Students of the school of Water are able to effortlessly manipulate all fluids, allowing them to enchant any fluid-related potions without needing to expend arcane essences."
			+ "</p>",
			"All fluid-related enchantments are free.",
			Colour.BASE_AQUA),
	
	AIR("air",
			"<p>"
				+ "The school of air focuses on spells that allow the caster to manipulate the temperature and movement of gases."
			+ "</p>"
			+ "<p>"
				+ "As with all schools of the arcane, the vast majority of Air practitioners are demons, but, with not many opportunities to use their spells in day-to-day business,"
					+ " their numbers are considerably lower than those of the schools of Earth and Water."
				+ " The only regular application of Air spells is to increase or decrease the temperature of rooms, allowing the occupants to escape the cold of winter or the heat of summer."
			+ "</p>"
			+ "<p>"
				+ "Students of the school of Air are able to effortlessly control the temperature of air around them, making sure that they're never too hot or too cold."
			+ "</p>",
			"Passive energy and arcane regeneration is doubled.",
			Colour.BASE_BLUE_LIGHT),
	
	FIRE("fire",
			"<p>"
				+ "The school of fire, much as its name would suggest, is purely focused on summoning arcane fire."
			+ "</p>"
			+ "<p>"
				+ "While arcane fire is employed in smelting and other heat-intensive industries, its many combat applications has given the school of Fire a rather poor reputation in demon society, which regards it as distasteful and crude."
				+ " Due to this, the only demons who choose to study the school of Fire are those who are interested in arcane research, those who are directly involved in heat-intensive industries, or those who expect to do a lot of fighting."
			+ "</p>"
			+ "<p>"
				+ "Students of the school of Fire are able to summon a floating ball of arcane fire at will, which allows them to travel through dark areas without need of a torch."
			+ "</p>",
			"Immune to 'Darkness' status effect.",
			Colour.BASE_ORANGE),

	ARCANE("arcane",
			"<p>"
				+ "Focused on harnessing the most pure form of arcane energy, the spells in the school of Arcane are concerned with either influencing a person's lust, or performing extremely powerful miscellaneous abilities."
			+ "</p>"
			+ "<p>"
				+ "As the only publicly-available spells are the ones associated with influencing lust, the school of Arcane is overlooked by most demons, as their physical charms are more than adequate on this front."
				+ " The vast majority of the students of the school of Arcane can be found in the ranks of the cult of Lilith, who view this school as the one favoured by Lilith herself."
			+ "</p>"
			+ "<p>"
				+ "Once a prospective student has a basic grasp of Arcane spells, they will find that they're able to feel the ebb and flow of the arcane currents woven throughout the world,"
					+ " and will be able to accurately predict when the next arcane storm will break."
			+ "</p>",
			"Know the exact time until the next arcane storm breaks.",
			Colour.GENERIC_ARCANE);
	
	
	private String name;
	private String description;
	private String passiveBuff;
	private Colour colour;
	
	private SpellSchool(String name, String description, String passiveBuff, Colour colour) {
		this.name = name;
		this.description = description;
		this.passiveBuff = passiveBuff;
		this.colour = colour;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getPassiveBuff() {
		return passiveBuff;
	}

	public Colour getColour() {
		return colour;
	}
	
}
