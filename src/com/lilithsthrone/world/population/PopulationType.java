package com.lilithsthrone.world.population;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

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
	
	public static AbstractPopulationType SECURITY_GUARD = new AbstractPopulationType("security guard", "security guards") {};

	public static AbstractPopulationType MAID = new AbstractPopulationType("maid", "maids") {};

	public static AbstractPopulationType CHEF = new AbstractPopulationType("chef", "chefs") {};

	public static AbstractPopulationType SLAVE = new AbstractPopulationType("slave", "slaves") {};
	
	public static AbstractPopulationType OFFICE_WORKER = new AbstractPopulationType("office worker", "office workers") {};
	
	public static AbstractPopulationType TEXTILE_WORKER = new AbstractPopulationType("textile worker", "textile workers") {};
	
	public static AbstractPopulationType CONSTRUCTION_WORKER = new AbstractPopulationType("construction worker", "construction workers") {};
	
	public static AbstractPopulationType RECEPTIONIST = new AbstractPopulationType("receptionist", "receptionists") {};

	public static AbstractPopulationType GANG_MEMBER = new AbstractPopulationType("gang member", "gang members") {};

	public static AbstractPopulationType STALL_HOLDER = new AbstractPopulationType("stallholder", "stallholders") {};

	public static AbstractPopulationType MILKER = new AbstractPopulationType("milker", "milkers") {};
	
	public static AbstractPopulationType CASHIER = new AbstractPopulationType("cashier", "cashiers") {};
	
	public static AbstractPopulationType CLERK = new AbstractPopulationType("clerk", "clerks") {};
	
	public static AbstractPopulationType MASSEUSE = new AbstractPopulationType("masseuse", "masseuses") {};
	
	public static AbstractPopulationType AMAZON = new AbstractPopulationType("Amazon", "Amazons") {};
	
	public static AbstractPopulationType AMAZON_GUARD = new AbstractPopulationType("Amazon guard", "Amazon guards") {};
	
	public static AbstractPopulationType LUNETTE_DAUGTHER = new AbstractPopulationType("Lunette's daughter", "Lunette's daughters") {};
	
	public static AbstractPopulationType COCK_SLEEVE = new AbstractPopulationType("cock-sleeve", "cock-sleeves") {};
	
	
	private static List<AbstractPopulationType> allPopulationTypes = new ArrayList<>();
	private static Map<AbstractPopulationType, String> populationToIdMap = new HashMap<>();
	private static Map<String, AbstractPopulationType> idToPlaceMap = new HashMap<>();

	public static List<AbstractPopulationType> getAllPopulationTypes() {
		return allPopulationTypes;
	}
	
	public static boolean hasId(String id) {
		return idToPlaceMap.keySet().contains(id);
	}
	
	public static AbstractPopulationType getPopulationTypeFromId(String id) {
		id = Util.getClosestStringMatch(id, idToPlaceMap.keySet());
		return idToPlaceMap.get(id);
	}

	public static String getIdFromPopulationType(AbstractPopulationType populationType) {
		return populationToIdMap.get(populationType);
	}
	
	static {
		// Hard-coded population types (all those up above):
		
		Field[] fields = PopulationType.class.getFields();
		
		for(Field f : fields) {
			if(AbstractPopulationType.class.isAssignableFrom(f.getType())) {
				AbstractPopulationType populationType;
				try {
					populationType = ((AbstractPopulationType) f.get(null));

					populationToIdMap.put(populationType, f.getName());
					idToPlaceMap.put(f.getName(), populationType);
					allPopulationTypes.add(populationType);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
