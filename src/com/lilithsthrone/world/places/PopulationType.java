package com.lilithsthrone.world.places;

import com.lilithsthrone.main.Main;

/**
 * @since 0.2.12
 * @version 0.3.7.5
 * @author Innoxia
 */
public enum PopulationType {

	PERSON("person", "people"),
	
	FAN("fan", "fans"),
	
	HARPY("harpy", "harpies") {
		@Override
		public String getName() {
			if(Main.game.isSillyModeEnabled()) {
				return "birb";
			}
			return "harpy";
		}
		@Override
		public String getNamePlural() {
			if(Main.game.isSillyModeEnabled()) {
				return "birbs";
			}
			return "harpies";
		}
	},
	
	CROWD("crowd", "crowds"),

	PRIVATE_SECURITY_GUARD("private security guard", "private security guards"),
	
	ENFORCER("Enforcer", "Enforcers"),

	CENTAUR_CARTS("centaur-pulled cart", "centaur-pulled carts"),
	
	SHOPPER("shopper", "shoppers"),
	
	DINER("diner", "diners"),

	VIP("VIP", "VIPs"),
	
	GUARD("guard", "guards"),

	MAID("maid", "maids"),

	CHEF("chef", "chefs"),

	SLAVE("slave", "slaves"),
	
	OFFICE_WORKER("office worker", "office workers"),
	
	RECEPTIONIST("receptionist", "receptionists"),

	GANG_MEMBER("gang member", "gang members");

	private String name;
	private String namePlural;
	
	private PopulationType(String name, String namePlural) {
		this.name = name;
		this.namePlural = namePlural;
	}

	public String getName() {
		return name;
	}

	public String getNamePlural() {
		return namePlural;
	}
}
