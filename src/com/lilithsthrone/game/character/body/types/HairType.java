package com.lilithsthrone.game.character.body.types;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractHairType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.3.9.1
 * @author Innoxia
 */
public class HairType {

	public static AbstractHairType HUMAN = new AbstractHairType(BodyCoveringType.HAIR_HUMAN,
			Race.HUMAN,
			true,
			"human",
			"hair",
			"hairs",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues("soft", "feminine"),
			"The transformation only lasts a matter of moments, leaving [npc.herHim] with human-like hair.<br/>"
					+ "[npc.Name] now [npc.has] [npc.hairColour], [style.boldHuman(human hair)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], human hair") {
	};

	public static AbstractHairType ANGEL = new AbstractHairType(BodyCoveringType.HAIR_ANGEL,
			Race.ANGEL,
			true,
			"angelic",
			"hair",
			"hairs",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues("silken", "soft", "feminine"),
			"The transformation only lasts a matter of moments, leaving [npc.herHim] with silken, angelic hair.<br/>"
					+ "[npc.Name] now [npc.has] [npc.hairColour], [style.boldAngel(angelic hair)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], angelic hair") {
	};
	
	public static AbstractHairType DEMON = new AbstractHairType(BodyCoveringType.HAIR_DEMON,
			Race.DEMON,
			true,
			"demonic",
			"hair",
			"hairs",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues("silken", "soft", "feminine"),
			"#IF(npc.isShortStature())"
				+ "The transformation only lasts a matter of moments, leaving [npc.herHim] with silky, impish hair.<br/>"
					+ "[npc.Name] now [npc.has] [npc.hairColour] [style.boldImp(impish hair)]."
			+ "#ELSE"
				+ "The transformation only lasts a matter of moments, leaving [npc.herHim] with silken, demonic hair.<br/>"
					+ "[npc.Name] now [npc.has] [npc.hairColour], [style.boldDemon(demonic hair)]."
			+ "#ENDIF",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], #IF(npc.isShortStature())impish#ELSEdemonic#ENDIF hair") {
	};

	public static AbstractHairType DOG_MORPH = new AbstractHairType(BodyCoveringType.HAIR_CANINE_FUR,
			Race.DOG_MORPH,
			true,
			"dog",
			"hair",
			"hairs",
			Util.newArrayListOfValues("furry", "fur-like"),
			Util.newArrayListOfValues("furry", "fur-like"),
			"The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.<br/>"
				+ "[npc.Name] now [npc.has] [npc.hairColour] [style.boldDogMorph(dog hair)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], dog-like hair") {
	};
	
	public static AbstractHairType WOLF_MORPH = new AbstractHairType(BodyCoveringType.HAIR_LYCAN_FUR, //TODO rename
			Race.WOLF_MORPH,
			true,
			"wolf",
			"hair",
			"hairs",
			Util.newArrayListOfValues("furry", "fur-like"),
			Util.newArrayListOfValues("furry", "fur-like"),
			"The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.<br/>"
				+ "[npc.Name] now [npc.has] [npc.hairColour] [style.boldWolfMorph(wolf hair)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], wolf-like hair") {
	};

	public static AbstractHairType FOX_MORPH = new AbstractHairType(BodyCoveringType.HAIR_FOX_FUR,
			Race.FOX_MORPH,
			true,
			"fox",
			"hair",
			"hairs",
			Util.newArrayListOfValues("furry", "fur-like"),
			Util.newArrayListOfValues("furry", "fur-like"),
			"The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.<br/>"
				+ "[npc.Name] now [npc.has] [npc.hairColour] [style.boldFoxMorph(fox hair)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], fox-like hair") {
	};

	public static AbstractHairType CAT_MORPH = new AbstractHairType(BodyCoveringType.HAIR_FELINE_FUR, //TODO change to cat
			Race.CAT_MORPH,
			true,
			"cat",
			"hair",
			"hairs",
			Util.newArrayListOfValues("furry", "fur-like"),
			Util.newArrayListOfValues("furry", "fur-like"),
			"The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.<br/>"
				+ "[npc.Name] now [npc.has] [npc.hairColour] [style.boldCatMorph(cat hair)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], cat-like hair") {
	};

	//TODO should be PANTHER
	public static AbstractHairType CAT_MORPH_SIDEFLUFF = new AbstractHairType(BodyCoveringType.HAIR_FELINE_FUR,
			Race.CAT_MORPH,
			true,
			"cat (sidefluff)",
			"hair",
			"hairs",
			Util.newArrayListOfValues("furry", "fur-like"),
			Util.newArrayListOfValues("furry", "fur-like"),
			"The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.<br/>"
				+ "[npc.Name] now [npc.has] [npc.hairColour] [style.boldCatMorph(cat hair)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], cat-like hair, complete with soft, fuzzy fur on the sides of [npc.her] face") {
	};

	public static AbstractHairType COW_MORPH = new AbstractHairType(BodyCoveringType.HAIR_BOVINE_FUR, //TODO change to cow
			Race.COW_MORPH,
			true,
			"cow",
			"hair",
			"hairs",
			Util.newArrayListOfValues("furry", "fur-like"),
			Util.newArrayListOfValues("furry", "fur-like"),
			"The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.<br/>"
				+ "[npc.Name] now [npc.has] [npc.hairColour] [style.boldCowMorph(cow hair)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], cow-like hair") {
	};

	public static AbstractHairType ALLIGATOR_MORPH = new AbstractHairType(BodyCoveringType.HAIR_SCALES_ALLIGATOR, //TODO change to hair
			Race.ALLIGATOR_MORPH,
			true,
			"alligator",
			"hair",
			"hairs",
			Util.newArrayListOfValues("coarse"),
			Util.newArrayListOfValues("coarse"),
			"The transformation only lasts a matter of moments, leaving [npc.herHim] with coarse hair.<br/>"
				+ "[npc.Name] now [npc.has] [npc.hairColour] [style.boldAlligatorMorph(alligator hair)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], coarse alligator hair") {
	};

	public static AbstractHairType SQUIRREL_MORPH = new AbstractHairType(BodyCoveringType.HAIR_SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			true,
			"squirrel",
			"hair",
			"hairs",
			Util.newArrayListOfValues("furry", "fur-like"),
			Util.newArrayListOfValues("furry", "fur-like"),
			"The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.<br/>"
				+ "[npc.Name] now [npc.has] [npc.hairColour] [style.boldSquirrelMorph(squirrel hair)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], squirrel-like hair") {
	};

	public static AbstractHairType RAT_MORPH = new AbstractHairType(BodyCoveringType.HAIR_RAT_FUR,
			Race.RAT_MORPH,
			true,
			"rat",
			"hair",
			"hairs",
			Util.newArrayListOfValues("furry", "fur-like"),
			Util.newArrayListOfValues("furry", "fur-like"),
			"The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.<br/>"
				+ "[npc.Name] now [npc.has] [npc.hairColour] [style.boldRatMorph(rat hair)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], rat-like hair") {
	};

	public static AbstractHairType RABBIT_MORPH = new AbstractHairType(BodyCoveringType.HAIR_RABBIT_FUR,
			Race.RABBIT_MORPH,
			true,
			"rabbit",
			"hair",
			"hairs",
			Util.newArrayListOfValues("furry", "fur-like"),
			Util.newArrayListOfValues("furry", "fur-like"),
			"The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.<br/>"
				+ "[npc.Name] now [npc.has] [npc.hairColour] [style.boldRabbitMorph(rabbit hair)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], rabbit-like hair") {
	};

	public static AbstractHairType BAT_MORPH = new AbstractHairType(BodyCoveringType.HAIR_BAT_FUR,
			Race.BAT_MORPH,
			true,
			"bat",
			"hair",
			"hairs",
			Util.newArrayListOfValues("furry", "fur-like"),
			Util.newArrayListOfValues("furry", "fur-like"),
			"The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.<br/>"
				+ "[npc.Name] now [npc.has] [npc.hairColour] [style.boldBatMorph(bat hair)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], bat-like hair") {
	};

	public static AbstractHairType HORSE_MORPH = new AbstractHairType(BodyCoveringType.HAIR_HORSE_HAIR,
			Race.HORSE_MORPH,
			true,
			"horse",
			"hair",
			"hairs",
			Util.newArrayListOfValues("furry", "fur-like"),
			Util.newArrayListOfValues("furry", "fur-like"),
			"The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.<br/>"
				+ "[npc.Name] now [npc.has] [npc.hairColour] [style.boldHorseMorph(horse hair)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], horse-like hair") {
	};

	public static AbstractHairType REINDEER_MORPH = new AbstractHairType(BodyCoveringType.HAIR_REINDEER_FUR,
			Race.REINDEER_MORPH,
			true,
			"reindeer",
			"hair",
			"hairs",
			Util.newArrayListOfValues("furry", "fur-like"),
			Util.newArrayListOfValues("furry", "fur-like"),
			"The transformation only lasts a matter of moments, leaving [npc.herHim] with fur-like hair.<br/>"
				+ "[npc.Name] now [npc.has] [npc.hairColour] [style.boldReindeerMorph(reindeer hair)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], reindeer-like hair") {
	};

	public static AbstractHairType HARPY = new AbstractHairType(BodyCoveringType.HAIR_HARPY,
			Race.HARPY,
			true,
			"harpy",
			"head-feather",
			"head-feathers",
			Util.newArrayListOfValues("beautiful", "bird-like"),
			Util.newArrayListOfValues("beautiful", "bird-like"),
			"The transformation only lasts a matter of moments, leaving [npc.herHim] with a plume of feathers in place of hair.<br/>"
				+ "[npc.Name] now [npc.has] [npc.hairColour], bird-like [style.boldHarpy(harpy feathers)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], bird-like harpy feathers") {
		@Override
		public boolean isDefaultPlural() {
			return true;
		}
	};

	private static List<AbstractHairType> allHairTypes;
	private static Map<AbstractHairType, String> hairToIdMap = new HashMap<>();
	private static Map<String, AbstractHairType> idToHairMap = new HashMap<>();
	
	static {
		allHairTypes = new ArrayList<>();
		
		// Add in hard-coded hair types:
		Field[] fields = HairType.class.getFields();
		
		for(Field f : fields){
			if (AbstractHairType.class.isAssignableFrom(f.getType())) {
				
				AbstractHairType ct;
				try {
					ct = ((AbstractHairType) f.get(null));

					hairToIdMap.put(ct, f.getName());
					idToHairMap.put(f.getName(), ct);
					
					allHairTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		Collections.sort(allHairTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractHairType getHairTypeFromId(String id) {
		if(id.equals("IMP") || id.equals("DEMON_COMMON")) {
			return HairType.DEMON;
		}
		if(id.equals("LYCAN")) {
			return HairType.WOLF_MORPH;
		}
		id = Util.getClosestStringMatch(id, idToHairMap.keySet());
		return idToHairMap.get(id);
	}
	
	public static String getIdFromHairType(AbstractHairType hairType) {
		return hairToIdMap.get(hairType);
	}
	
	public static List<AbstractHairType> getAllHairTypes() {
		return allHairTypes;
	}
	
	private static Map<AbstractRace, List<AbstractHairType>> typesMap = new HashMap<>();
	
	public static List<AbstractHairType> getHairTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractHairType> types = new ArrayList<>();
		for(AbstractHairType type : HairType.getAllHairTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}