package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTorsoType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.3.8.8
 * @author Innoxia
 */
public class TorsoType {
	
	public static AbstractTorsoType HUMAN = new AbstractTorsoType(BodyCoveringType.HUMAN,
			Race.HUMAN,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"After just a few moments, the transformation comes to an end, and [npc.she] [npc.verb(let)] out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with human skin."
				+ "<br/>[npc.Name] now [npc.has] [style.boldHuman(human)], [npc.skinFullDescription].",
			"[npc.Her] torso has [npc.a_femininity(true)] appearance, and is [npc.materialCompositionDescriptor] [npc.skinFullDescription(true)].") {
	};

	public static AbstractTorsoType DEMON_COMMON = new AbstractTorsoType(BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"After just a few moments, the transformation comes to an end, and [npc.she] [npc.verb(let)] out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with"
					+ "#IF(npc.isShortStature())"
						+ " impish"
					+ "#ELSE"
						+ " demonic"
					+ "#ENDIF"
					+ " skin."
				+ " It's far smoother than regular human skin, and the colour tones all over [npc.her] body have become perfectly balanced in order to help show off [npc.her] figure."
				+ "<br/>[npc.Name] now [npc.has]"
				+ "#IF(npc.isShortStature())"
					+ " [style.boldImp(impish)]"
				+ "#ELSE"
					+ " [style.boldDemon(demonic)]"
				+ "#ENDIF"
				+ ", [npc.skinFullDescription].",
			"[npc.Her] torso has [npc.a_femininity(true)] appearance, and is [npc.materialCompositionDescriptor] [npc.skinFullDescription(true)].") {
	};

	public static AbstractTorsoType ANGEL = new AbstractTorsoType(BodyCoveringType.ANGEL,
			Race.ANGEL,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"After just a few moments, the transformation comes to an end, and [npc.she] [npc.verb(let)] out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with angelic skin."
				+ " It's far smoother than regular human skin, and the colour tones all over [npc.her] body have become perfectly balanced in order to help show off [npc.her] figure."
				+ "<br/>[npc.Name] now [npc.has] [style.boldAngel(angelic)], [npc.skinFullDescription].",
			"[npc.Her] torso has [npc.a_femininity(true)] appearance, and is [npc.materialCompositionDescriptor] [npc.skinFullDescription(true)].") {
	};

	public static AbstractTorsoType COW_MORPH = new AbstractTorsoType(BodyCoveringType.BOVINE_FUR,
			Race.COW_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"After just a few moments, the transformation comes to an end, and [npc.she] [npc.verb(let)] out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with short, cow-like hair."
				+ " [npc.Her] new hair looks very sleek and helps to show off [npc.her] figure, although it's a little coarse to the touch."
				+ "<br/>[npc.Name] now [npc.has] [style.boldCowMorph(bovine)], [npc.skinFullDescription].",
			"[npc.Her] torso has [npc.a_femininity(true)] appearance, and is [npc.materialCompositionDescriptor] [npc.skinFullDescription(true)].") {
	};

	public static AbstractTorsoType DOG_MORPH = new AbstractTorsoType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"After just a few moments, the transformation comes to an end, and [npc.she] [npc.verb(let)] out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with dog-like fur."
				+ " [npc.Her] new fur follows the lines of [npc.her] figure and is quite smooth and pleasant to touch."
				+ "<br/>[npc.Name] now [npc.has] [style.boldDogMorph(canine)], [npc.skinFullDescription].",
			"[npc.Her] torso has [npc.a_femininity(true)] appearance, and is [npc.materialCompositionDescriptor] [npc.skinFullDescription(true)].") {
	};

	public static AbstractTorsoType WOLF_MORPH = new AbstractTorsoType(BodyCoveringType.LYCAN_FUR,
			Race.WOLF_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"After just a few moments, the transformation comes to an end, and [npc.she] [npc.verb(let)] out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with wolf-like fur."
				+ " [npc.Her] new fur is a little shaggy around [npc.her] joints and is quite densely packed."
				+ "<br/>[npc.Name] now [npc.has] [style.boldWolfMorph(lupine)], [npc.skinFullDescription].",
			"[npc.Her] torso has [npc.a_femininity(true)] appearance, and is [npc.materialCompositionDescriptor] [npc.skinFullDescription(true)].") {
	};
	
	public static AbstractTorsoType FOX_MORPH = new AbstractTorsoType(BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"After just a few moments, the transformation comes to an end, and [npc.she] [npc.verb(let)] out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with fox-like fur."
				+ " [npc.Her] new fur is a little shaggy around [npc.her] joints and is quite densely packed."
				+ "</br>[npc.Name] now [npc.has] [style.boldFoxMorph(vulpine)], [npc.skinFullDescription].",
			"[npc.Her] torso has [npc.a_femininity(true)] appearance, and is [npc.materialCompositionDescriptor] [npc.skinFullDescription(true)].") {
	};

	public static AbstractTorsoType CAT_MORPH = new AbstractTorsoType(BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"After just a few moments, the transformation comes to an end, and [npc.she] [npc.verb(let)] out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with cat-like fur."
				+ " [npc.Her] new fur follows the lines of [npc.her] figure and is extremely smooth and soft."
				+ "<br/>[npc.Name] now [npc.has] [style.boldCatMorph(feline)], [npc.skinFullDescription].",
			"[npc.Her] torso has [npc.a_femininity(true)] appearance, and is [npc.materialCompositionDescriptor] [npc.skinFullDescription(true)].") {
	};

	public static AbstractTorsoType SQUIRREL_MORPH = new AbstractTorsoType(BodyCoveringType.SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"After just a few moments, the transformation comes to an end, and [npc.she] [npc.verb(let)] out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with squirrel-like fur."
				+ " [npc.Her] new fur follows the lines of [npc.her] figure and is extremely smooth and soft."
				+ "<br/>[npc.Name] now [npc.has] [style.boldSquirrelMorph(squirrel-like)], [npc.skinFullDescription].",
			"[npc.Her] torso has [npc.a_femininity(true)] appearance, and is [npc.materialCompositionDescriptor] [npc.skinFullDescription(true)].") {
	};

	public static AbstractTorsoType RAT_MORPH = new AbstractTorsoType(BodyCoveringType.RAT_FUR,
			Race.RAT_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"After just a few moments, the transformation comes to an end, and [npc.she] [npc.verb(let)] out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with rat-like fur."
				+ " [npc.Her] new fur follows the lines of [npc.her] figure and is a little coarse to the touch."
				+ "<br/>[npc.Name] now [npc.has] [style.boldRatMorph(rat-like)], [npc.skinFullDescription].",
			"[npc.Her] torso has [npc.a_femininity(true)] appearance, and is [npc.materialCompositionDescriptor] [npc.skinFullDescription(true)].") {
	};

	public static AbstractTorsoType RABBIT_MORPH = new AbstractTorsoType(BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"After just a few moments, the transformation comes to an end, and [npc.she] [npc.verb(let)] out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with rabbit-like fur."
				+ " [npc.Her] new fur follows the lines of [npc.her] figure and is extremely smooth and soft."
				+ "<br/>[npc.Name] now [npc.has] [style.boldRabbitMorph(rabbit-like)], [npc.skinFullDescription].",
			"[npc.Her] torso has [npc.a_femininity(true)] appearance, and is [npc.materialCompositionDescriptor] [npc.skinFullDescription(true)].") {
	};

	public static AbstractTorsoType BAT_MORPH = new AbstractTorsoType(BodyCoveringType.BAT_FUR,
			Race.BAT_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"After just a few moments, the transformation comes to an end, and [npc.she] [npc.verb(let)] out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with bat-like fur."
				+ " [npc.Her] new fur follows the lines of [npc.her] figure and is quite smooth and pleasant to touch."
				+ "<br/>[npc.Name] now [npc.has] [style.boldBatMorph(bat-like)], [npc.skinFullDescription].",
			"[npc.Her] torso has [npc.a_femininity(true)] appearance, and is [npc.materialCompositionDescriptor] [npc.skinFullDescription(true)].") {
	};

	public static AbstractTorsoType ALLIGATOR_MORPH = new AbstractTorsoType(BodyCoveringType.ALLIGATOR_SCALES,
			Race.ALLIGATOR_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"After just a few moments, the transformation comes to an end, and [npc.she] [npc.verb(let)] out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with tough, overlapping scales."
				+ " [npc.Her] new scales follow the lines of [npc.her] figure, and, while being quite hard to the touch, are also very smooth when rubbed in the right direction."
				+ "<br/>[npc.Name] now [npc.has] [style.boldGatorMorph(reptile)], [npc.skinFullDescription].",
			"[npc.Her] torso has [npc.a_femininity(true)] appearance, and is [npc.materialCompositionDescriptor] [npc.skinFullDescription(true)].") {
		@Override
		public boolean isDefaultPlural(GameCharacter gc) {
			return true;
		}
	};

	public static AbstractTorsoType HORSE_MORPH = new AbstractTorsoType(BodyCoveringType.HORSE_HAIR,
			Race.HORSE_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"After just a few moments, the transformation comes to an end, and [npc.she] [npc.verb(let)] out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with short, horse-like hair."
				+ " [npc.Her] new hair looks very sleek, and helps to show off [npc.her] figure, although it's a little coarse to the touch."
				+ "<br/>[npc.Name] now [npc.has] [style.boldHorseMorph(equine)], [npc.skinFullDescription].",
			"[npc.Her] torso has [npc.a_femininity(true)] appearance, and is [npc.materialCompositionDescriptor] [npc.skinFullDescription(true)].") {
	};

	public static AbstractTorsoType REINDEER_MORPH = new AbstractTorsoType(BodyCoveringType.REINDEER_FUR,
			Race.REINDEER_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"After just a few moments, the transformation comes to an end, and [npc.she] [npc.verb(let)] out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with short, reindeer-like hair."
				+ " [npc.Her] new fur looks very sleek, and helps to show off [npc.her] figure, although it's a little coarse to the touch."
				+ "<br/>[npc.Name] now [npc.has] [style.boldReindeerMorph(reindeer)], [npc.skinFullDescription].",
			"[npc.Her] torso has [npc.a_femininity(true)] appearance, and is [npc.materialCompositionDescriptor] [npc.skinFullDescription(true)].") {
	};

	public static AbstractTorsoType HARPY = new AbstractTorsoType(BodyCoveringType.FEATHERS,
			Race.HARPY,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"After just a few moments, the transformation comes to an end, and [npc.she] [npc.verb(let)] out a deep sigh as the itching finally stops, leaving [npc.her] torso covered with beautiful, overlapping feathers."
				+ " [npc.Her] new feathers follow the lines of [npc.her] figure, and are extremely smooth and soft to the touch."
				+ "<br/>[npc.Name] now [npc.has] [style.boldHarpy(avian)], [npc.skinFullDescription].",
			"[npc.Her] torso has [npc.a_femininity(true)] appearance, and is [npc.materialCompositionDescriptor] [npc.skinFullDescription(true)].") {
		@Override
		public boolean isDefaultPlural(GameCharacter gc) {
			return true;
		}
	};
	
	
	private static final List<AbstractTorsoType> allTorsoTypes;
	private static final Map<AbstractTorsoType, String> torsoToIdMap = new HashMap<>();
	private static final Map<String, AbstractTorsoType> idToTorsoMap = new HashMap<>();
	
	static {
		allTorsoTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					if(Element.getDocumentRootElement(innerEntry.getValue()).getTagName().equals("torso")) {
						AbstractTorsoType type = new AbstractTorsoType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allTorsoTypes.add(type);
						torsoToIdMap.put(type, id);
						idToTorsoMap.put(id, type);
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		
		// External res types:
		
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					if(Element.getDocumentRootElement(innerEntry.getValue()).getTagName().equals("torso")) {
						AbstractTorsoType type = new AbstractTorsoType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allTorsoTypes.add(type);
						torsoToIdMap.put(type, id);
						idToTorsoMap.put(id, type);
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		
		// Add in hard-coded torso types:
		Field[] fields = TorsoType.class.getFields();
		for(Field f : fields){
			if (AbstractTorsoType.class.isAssignableFrom(f.getType())) {
				AbstractTorsoType ct;
				try {
					ct = ((AbstractTorsoType) f.get(null));
					torsoToIdMap.put(ct, f.getName());
					idToTorsoMap.put(f.getName(), ct);
					allTorsoTypes.add(ct);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		allTorsoTypes.sort((t1, t2)->
				t1.getRace() == Race.NONE
						?-1
						:(t2.getRace() == Race.NONE
						?1
						:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractTorsoType getTorsoTypeFromId(String id) {
		switch (id) {
			case "IMP":
				return TorsoType.DEMON_COMMON;
			case "CANINE_FUR":
				return TorsoType.DOG_MORPH;
			case "LYCAN_FUR":
			case "LYCAN":
				return TorsoType.WOLF_MORPH;
			case "FELINE_FUR":
				return TorsoType.CAT_MORPH;
			case "SQUIRREL_FUR":
				return TorsoType.SQUIRREL_MORPH;
			case "HORSE_HAIR":
				return TorsoType.HORSE_MORPH;
			case "FEATHERS":
				return TorsoType.HARPY;
		}
		id = Util.getClosestStringMatch(id, idToTorsoMap.keySet());
		return idToTorsoMap.get(id);
	}
	
	public static String getIdFromTorsoType(AbstractTorsoType torsoType) {
		return torsoToIdMap.get(torsoType);
	}
	
	public static List<AbstractTorsoType> getAllTorsoTypes() {
		return allTorsoTypes;
	}
	
	private static final Map<AbstractRace, List<AbstractTorsoType>> typesMap = new HashMap<>();
	
	public static List<AbstractTorsoType> getTorsoTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractTorsoType> types = new ArrayList<>();
		for(AbstractTorsoType type : TorsoType.getAllTorsoTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}