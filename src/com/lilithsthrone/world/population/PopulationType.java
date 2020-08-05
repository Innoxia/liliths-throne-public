package com.lilithsthrone.world.population;

import com.lilithsthrone.main.Main;

/**
 * @since 0.2.12
 * @version 0.3.9
 * @author Innoxia
 */
public class PopulationType {

	public static AbstractPopulationType PERSON = new AbstractPopulationType("person", "people") {};
	
	public static AbstractPopulationType FAN = new AbstractPopulationType("fan", "fans") {};
	
	public static AbstractPopulationType HARPY = new AbstractPopulationType("harpy", "harpies") {
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
	};
	
	public static AbstractPopulationType CROWD = new AbstractPopulationType("crowd", "crowds") {};

	public static AbstractPopulationType PRIVATE_SECURITY_GUARD = new AbstractPopulationType("private security guard", "private security guards") {};
	
	public static AbstractPopulationType ENFORCER = new AbstractPopulationType("Enforcer", "Enforcers") {};

	public static AbstractPopulationType CENTAUR_CARTS = new AbstractPopulationType("centaur-pulled cart", "centaur-pulled carts") {};
	
	public static AbstractPopulationType SHOPPER = new AbstractPopulationType("shopper", "shoppers") {};
	
	public static AbstractPopulationType DINER = new AbstractPopulationType("diner", "diners") {};

	public static AbstractPopulationType VIP = new AbstractPopulationType("VIP", "VIPs") {};
	
	public static AbstractPopulationType GUARD = new AbstractPopulationType("guard", "guards") {};

	public static AbstractPopulationType MAID = new AbstractPopulationType("maid", "maids") {};

	public static AbstractPopulationType CHEF = new AbstractPopulationType("chef", "chefs") {};

	public static AbstractPopulationType SLAVE = new AbstractPopulationType("slave", "slaves") {};
	
	public static AbstractPopulationType OFFICE_WORKER = new AbstractPopulationType("office worker", "office workers") {};
	
	public static AbstractPopulationType CONSTRUCTION_WORKER = new AbstractPopulationType("construction worker", "construction workers") {};
	
	public static AbstractPopulationType RECEPTIONIST = new AbstractPopulationType("receptionist", "receptionists") {};

	public static AbstractPopulationType GANG_MEMBER = new AbstractPopulationType("gang member", "gang members") {};
}
