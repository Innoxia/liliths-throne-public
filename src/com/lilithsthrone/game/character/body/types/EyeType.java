package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractEyeType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.3.7
 * @author Innoxia
 */
public class EyeType {

	public static AbstractEyeType HUMAN = new AbstractEyeType(BodyCoveringType.EYE_HUMAN,
			Race.HUMAN,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"human",
			"eye",
			"eyes",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			" By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into human eyes, with normally-proportioned irises and pupils."
				+ "<br/>"
				+ "[npc.Name] now [npc.has] [style.boldHuman(human eyes)] with [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] and [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] normal, human eyes, with [npc.irisShape], [npc.irisColour(true)] irises, [npc.pupilShape], [npc.pupilColour(true)] pupils, and [npc.scleraColour(true)] sclerae.") {
	};

	public static AbstractEyeType ANGEL = new AbstractEyeType(BodyCoveringType.EYE_ANGEL,
			Race.ANGEL,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"angel",
			"eye",
			"eyes",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			" By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into angelic eyes, with normally-proportioned irises and pupils."
				+ "<br/>"
				+ "[npc.Name] now [npc.has] [style.boldAngel(angelic eyes)] with [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] and [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] angelic eyes, with [npc.irisShape], [npc.irisColour(true)] irises, [npc.pupilShape], [npc.pupilColour(true)] pupils, and [npc.scleraColour(true)] sclerae.") {
	};

	public static AbstractEyeType DEMON_COMMON = new AbstractEyeType(BodyCoveringType.EYE_DEMON_COMMON,
			Race.DEMON,
			1,
			EyeShape.ROUND,
			EyeShape.VERTICAL,
			"demonic",
			"eye",
			"eyes",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"#IF(npc.isShortStature())"
				+ "By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into impish eyes, with vertical pupils and large irises."
				+ "<br/>"
				+ "[npc.Name] now [npc.has] [style.boldImp(impish eyes)] with [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] and [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)]."
			+ "#ELSE"
				+ "By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into demonic eyes, with vertical pupils and large irises."
				+ "<br/>"
				+ "[npc.Name] now [npc.has] [style.boldDemon(demonic eyes)] with [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] and [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)]."
			+ "#ENDIF",
			"[npc.SheHasFull] [npc.eyePairs] #IF(npc.isShortStature())impish#ELSEdemonic#ENDIF eyes, with [npc.irisShape], [npc.irisColour(true)] irises, [npc.pupilShape], [npc.pupilColour(true)] pupils, and [npc.scleraColour(true)] sclerae.") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType CAT_MORPH = new AbstractEyeType(BodyCoveringType.EYE_FELINE,
			Race.CAT_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.VERTICAL,
			"cat",
			"eye",
			"eyes",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into cat-like eyes, with large irises and vertical pupils."
					+ "<br/>"
					+ "[npc.Name] now [npc.has] [style.boldCatMorph(cat-like eyes)] with [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] and [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] cat-like eyes, the irises and pupils of which are larger than a regular human's."
				+ " They have [npc.irisShape], [npc.irisColour(true)] irises, [npc.pupilShape], [npc.pupilColour(true)] pupils, and [npc.scleraColour(true)] sclerae.") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType COW_MORPH = new AbstractEyeType(BodyCoveringType.EYE_COW_MORPH,
			Race.COW_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.HORIZONTAL,
			"cow",
			"eye",
			"eyes",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into cow-like eyes, with large pupils and horizontal irises."
				+ "<br/>"
				+ "[npc.Name] now [npc.has] [style.boldCowMorph(cow-like eyes)] with [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] and [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] cow-like eyes, the irises and pupils of which are larger than a regular human's."
			+ " They have [npc.irisShape], [npc.irisColour(true)] irises, [npc.pupilShape], [npc.pupilColour(true)] pupils, and [npc.scleraColour(true)] sclerae.") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType DOG_MORPH = new AbstractEyeType(BodyCoveringType.EYE_DOG_MORPH,
			Race.DOG_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"dog",
			"eye",
			"eyes",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into dog-like eyes, with large pupils and irises."
				+ "<br/>"
				+ "[npc.Name] now [npc.has] [style.boldDogMorph(dog-like eyes)] with [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] and [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] dog-like eyes, the irises and pupils of which are larger than a regular human's."
				+ " They have [npc.irisShape], [npc.irisColour(true)] irises, [npc.pupilShape], [npc.pupilColour(true)] pupils, and [npc.scleraColour(true)] sclerae.") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType FOX_MORPH = new AbstractEyeType(BodyCoveringType.EYE_FOX_MORPH,
			Race.FOX_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.VERTICAL,
			"fox",
			"eye",
			"eyes",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into fox-like eyes, with large irises and vertical pupils."
				+ "<br/>"
				+ "[npc.Name] now [npc.has] [style.boldFoxMorph(fox-like eyes)] with [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] and [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] fox-like eyes, the irises and pupils of which are larger than a regular human's."
				+ " They have [npc.irisShape], [npc.irisColour(true)] irises, [npc.pupilShape], [npc.pupilColour(true)] pupils, and [npc.scleraColour(true)] sclerae.") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType WOLF_MORPH = new AbstractEyeType(BodyCoveringType.EYE_LYCAN,
			Race.WOLF_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"wolf",
			"eye",
			"eyes",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into wolf-like eyes, with large irises and pupils."
				+ "<br/>"
				+ "[npc.Name] now [npc.has] [style.boldWolfMorph(wolf-like eyes)] with [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] and [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] wolf-like eyes, the irises and pupils of which are larger than a regular human's."
				+ " They have [npc.irisShape], [npc.irisColour(true)] irises, [npc.pupilShape], [npc.pupilColour(true)] pupils, and [npc.scleraColour(true)] sclerae.") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType SQUIRREL_MORPH = new AbstractEyeType(BodyCoveringType.EYE_SQUIRREL,
			Race.SQUIRREL_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"squirrel",
			"eye",
			"eyes",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into squirrel-like eyes, with large irises and pupils."
				+ "<br/>"
				+ "[npc.Name] now [npc.has] [style.boldSquirrelMorph(squirrel-like eyes)] with [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] and [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] squirrel-like eyes, the irises and pupils of which are larger than a regular human's."
				+ " They have [npc.irisShape], [npc.irisColour(true)] irises, [npc.pupilShape], [npc.pupilColour(true)] pupils, and [npc.scleraColour(true)] sclerae.") {
	};

	public static AbstractEyeType RAT_MORPH = new AbstractEyeType(BodyCoveringType.EYE_RAT,
			Race.RAT_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"rat",
			"eye",
			"eyes",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into rat-like eyes, with large irises and pupils."
				+ "<br/>"
				+ "[npc.Name] now [npc.has] [style.boldRatMorph(rat-like eyes)] with [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] and [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] rat-like eyes, the irises and pupils of which are larger than a regular human's."
				+ " They have [npc.irisShape], [npc.irisColour(true)] irises, [npc.pupilShape], [npc.pupilColour(true)] pupils, and [npc.scleraColour(true)] sclerae.") {
	};

	public static AbstractEyeType RABBIT_MORPH = new AbstractEyeType(BodyCoveringType.EYE_RABBIT,
			Race.RABBIT_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"rabbit",
			"eye",
			"eyes",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into rabbit-like eyes, with large irises and pupils."
				+ "<br/>"
				+ "[npc.Name] now [npc.has] [style.boldRabbitMorph(rabbit-like eyes)] with [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] and [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] rabbit-like eyes, the irises and pupils of which are larger than a regular human's."
				+ " They have [npc.irisShape], [npc.irisColour(true)] irises, [npc.pupilShape], [npc.pupilColour(true)] pupils, and [npc.scleraColour(true)] sclerae.") {
	};

	public static AbstractEyeType BAT_MORPH = new AbstractEyeType(BodyCoveringType.EYE_BAT,
			Race.BAT_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"bat",
			"eye",
			"eyes",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into bat-like eyes, with large irises and pupils."
				+ "<br/>"
				+ "[npc.Name] now [npc.has] [style.boldBatMorph(bat-like eyes)] with [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] and [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] bat-like eyes, the irises and pupils of which are larger than a regular human's."
				+ " They have [npc.irisShape], [npc.irisColour(true)] irises, [npc.pupilShape], [npc.pupilColour(true)] pupils, and [npc.scleraColour(true)] sclerae.") {
	};

	public static AbstractEyeType ALLIGATOR_MORPH = new AbstractEyeType(BodyCoveringType.EYE_ALLIGATOR_MORPH,
			Race.ALLIGATOR_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.VERTICAL,
			"alligator",
			"eye",
			"eyes",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into alligator-like eyes, with large irises and vertical pupils."
				+ "<br/>"
				+ "[npc.Name] now [npc.has] [style.boldAlligatorMorph(alligator-like eyes)] with [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] and [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] alligator-like eyes, the irises and pupils of which are larger than a regular human's."
				+ " They have [npc.irisShape], [npc.irisColour(true)] irises, [npc.pupilShape], [npc.pupilColour(true)] pupils, and [npc.scleraColour(true)] sclerae.") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType HORSE_MORPH = new AbstractEyeType(BodyCoveringType.EYE_HORSE_MORPH,
			Race.HORSE_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.HORIZONTAL,
			"horse",
			"eye",
			"eyes",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into horse-like eyes, with large irises and horizontal pupils."
				+ "<br/>"
				+ "[npc.Name] now [npc.has] [style.boldHorseMorph(horse-like eyes)] with [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] and [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] horse-like eyes, the irises and pupils of which are larger than a regular human's."
				+ " They have [npc.irisShape], [npc.irisColour(true)] irises, [npc.pupilShape], [npc.pupilColour(true)] pupils, and [npc.scleraColour(true)] sclerae.") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType REINDEER_MORPH = new AbstractEyeType(BodyCoveringType.EYE_REINDEER_MORPH,
			Race.REINDEER_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.HORIZONTAL,
			"reindeer",
			"eye",
			"eyes",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into reindeer-like eyes, with large irises and horizontal pupils."
				+ "<br/>"
				+ "[npc.Name] now [npc.has] [style.boldReindeerMorph(reindeer-like eyes)] with [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] and [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] reindeer-like eyes, the irises and pupils of which are larger than a regular human's."
				+ " They have [npc.irisShape], [npc.irisColour(true)] irises, [npc.pupilShape], [npc.pupilColour(true)] pupils, and [npc.scleraColour(true)] sclerae.") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType HARPY = new AbstractEyeType(BodyCoveringType.EYE_HARPY,
			Race.HARPY,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"harpy",
			"eye",
			"eyes",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into bird-like eyes, with large irises and pupils."
				+ "<br/>"
				+ "[npc.Name] now [npc.has] [style.boldHarpy(bird-like eyes)] with [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] and [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] bird-like eyes, the irises and pupils of which are larger than a regular human's."
				+ " They have [npc.irisShape], [npc.irisColour(true)] irises, [npc.pupilShape], [npc.pupilColour(true)] pupils, and [npc.scleraColour(true)] sclerae.") {
	};
	

	private static List<AbstractEyeType> allEyeTypes;
	private static Map<AbstractEyeType, String> eyeToIdMap = new HashMap<>();
	private static Map<String, AbstractEyeType> idToEyeMap = new HashMap<>();
	
	static {
		allEyeTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("eye")) {
					try {
						AbstractEyeType type = new AbstractEyeType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allEyeTypes.add(type);
						eyeToIdMap.put(type, id);
						idToEyeMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// External res types:
		
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("eye")) {
					try {
						AbstractEyeType type = new AbstractEyeType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allEyeTypes.add(type);
						eyeToIdMap.put(type, id);
						idToEyeMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded eye types:
		
		Field[] fields = EyeType.class.getFields();
		
		for(Field f : fields){
			if (AbstractEyeType.class.isAssignableFrom(f.getType())) {
				
				AbstractEyeType ct;
				try {
					ct = ((AbstractEyeType) f.get(null));

					eyeToIdMap.put(ct, f.getName());
					idToEyeMap.put(f.getName(), ct);
					
					allEyeTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allEyeTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractEyeType getEyeTypeFromId(String id) {
		if(id.equals("IMP")) {
			return EyeType.DEMON_COMMON;
		}
		if(id.equals("LYCAN")) {
			return EyeType.WOLF_MORPH;
		}
		id = Util.getClosestStringMatch(id, idToEyeMap.keySet());
		return idToEyeMap.get(id);
	}
	
	public static String getIdFromEyeType(AbstractEyeType eyeType) {
		return eyeToIdMap.get(eyeType);
	}
	
	public static List<AbstractEyeType> getAllEyeTypes() {
		return allEyeTypes;
	}
	
	private static Map<AbstractRace, List<AbstractEyeType>> typesMap = new HashMap<>();
	
	public static List<AbstractEyeType> getEyeTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractEyeType> types = new ArrayList<>();
		for(AbstractEyeType type : EyeType.getAllEyeTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
	
}