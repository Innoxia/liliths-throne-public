package com.lilithsthrone.game.character.body.types;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3
 * @author Innoxia
 */
public class AssType {
	
	public static AbstractAssType HUMAN = new AbstractAssType(BodyCoveringType.HUMAN,
			Race.HUMAN,
			AnusType.HUMAN,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] now [npc.has] a [style.boldHuman(human)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] a human, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType ANGEL = new AbstractAssType(BodyCoveringType.ANGEL,
			Race.ANGEL,
			AnusType.ANGEL,
			null,
			null,
			Util.newArrayListOfValues("angelic"),
			Util.newArrayListOfValues("angelic", "perfect"),
			"[npc.She] now [npc.has] an [style.boldAngel(angelic)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] an angelic, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType DEMON_COMMON = new AbstractAssType(BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			AnusType.DEMON_COMMON,
			null,
			null,
			Util.newArrayListOfValues("demonic"),
			Util.newArrayListOfValues("demonic", "perfect"),
			"[npc.She] now [npc.has] a [style.boldDemon(demonic)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] a demonic, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType DOG_MORPH = new AbstractAssType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			AnusType.DOG_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] now [npc.has] a [style.boldDogMorph(canine)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] a canine, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType COW_MORPH = new AbstractAssType(BodyCoveringType.BOVINE_FUR,
			Race.COW_MORPH,
			AnusType.COW_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] now [npc.has] a [style.boldCowMorph(bovine)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] a bovine, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType SQUIRREL_MORPH = new AbstractAssType(BodyCoveringType.SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			AnusType.SQUIRREL_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] now [npc.has] a [style.boldSquirrelMorph(squirrel-like)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] a squirrel-like, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType RAT_MORPH = new AbstractAssType(BodyCoveringType.RAT_FUR,
			Race.RAT_MORPH,
			AnusType.RAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] now [npc.has] a [style.boldRatMorph(rat-like)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] a rat-like, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType RABBIT_MORPH = new AbstractAssType(BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			AnusType.RABBIT_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] now [npc.has] a [style.boldRabbitMorph(rabbit-like)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] a rabbit-like, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType BAT_MORPH = new AbstractAssType(BodyCoveringType.BAT_FUR,
			Race.BAT_MORPH,
			AnusType.BAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] now [npc.has] a [style.boldBatMorph(bat-like)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] a bat-like, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType ALLIGATOR_MORPH = new AbstractAssType(BodyCoveringType.ALLIGATOR_SCALES,
			Race.ALLIGATOR_MORPH,
			AnusType.ALLIGATOR_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] now [npc.has] an [style.boldGatorMorph(alligator-like)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] an alligator-like, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType WOLF_MORPH = new AbstractAssType(BodyCoveringType.LYCAN_FUR,
			Race.WOLF_MORPH,
			AnusType.WOLF_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] now [npc.has] a [style.boldWolfMorph(lupine)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] a lupine, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType FOX_MORPH = new AbstractAssType(BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			AnusType.FOX_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] now [npc.has] a [style.boldFoxMorph(vulpine)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] a vulpine, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType CAT_MORPH = new AbstractAssType(BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			AnusType.CAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] now [npc.has] a [style.boldCatMorph(feline)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] a feline, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType HORSE_MORPH = new AbstractAssType(BodyCoveringType.HORSE_HAIR,
			Race.HORSE_MORPH,
			AnusType.HORSE_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] now [npc.has] an [style.boldHorseMorph(equine)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] an equine, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType REINDEER_MORPH = new AbstractAssType(BodyCoveringType.REINDEER_FUR,
			Race.REINDEER_MORPH,
			AnusType.REINDEER_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] now [npc.has] a [style.boldReindeerMorph(reindeer-like)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] a reindeer-like, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType HARPY = new AbstractAssType(BodyCoveringType.FEATHERS,
			Race.HARPY,
			AnusType.HARPY,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] now [npc.has] an [style.boldHarpy(avian)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] an avian, [npc.anusFullDescription(true)]"){
	};
	
	
	private static List<AbstractAssType> allAssTypes;
	private static Map<AbstractAssType, String> assToIdMap = new HashMap<>();
	private static Map<String, AbstractAssType> idToAssMap = new HashMap<>();
	
	static {
		allAssTypes = new ArrayList<>();
		
		// Add in hard-coded ass types:
		Field[] fields = AssType.class.getFields();
		
		for(Field f : fields){
			if (AbstractAssType.class.isAssignableFrom(f.getType())) {
				
				AbstractAssType ct;
				try {
					ct = ((AbstractAssType) f.get(null));

					assToIdMap.put(ct, f.getName());
					idToAssMap.put(f.getName(), ct);
					
					allAssTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static AbstractAssType getAssTypeFromId(String id) {
		if(id.equals("IMP")) {
			return AssType.DEMON_COMMON;
		}
		
		id = Util.getClosestStringMatch(id, idToAssMap.keySet());
		return idToAssMap.get(id);
	}
	
	public static String getIdFromAssType(AbstractAssType assType) {
		return assToIdMap.get(assType);
	}
	
	public static List<AbstractAssType> getAllAssTypes() {
		return allAssTypes;
	}
	
	private static Map<Race, List<AbstractAssType>> typesMap = new HashMap<>();
	public static List<AbstractAssType> getAssTypes(Race r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractAssType> types = new ArrayList<>();
		for(AbstractAssType type : AssType.getAllAssTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}

}