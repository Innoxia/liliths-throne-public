package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.h2.util.StringUtils;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.83
 * @version 0.2.11
 * @author Innoxia
 */
public enum EyeType implements BodyPartTypeInterface {
	HUMAN(
			BodyCoveringType.EYE_HUMAN,
			Race.HUMAN,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"",
			(Boolean isPc) -> {
				return (isPc ? EyeTransformationText.PC_START + EyeTransformationText.PC_TO_HUMAN
						: EyeTransformationText.NPC_START + EyeTransformationText.NPC_TO_HUMAN) + EyeTransformationText.FINISH;
				}
		),

	ANGEL(
			BodyCoveringType.EYE_ANGEL,
			Race.ANGEL,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"angelic",
			(Boolean isPc) -> {
				return (isPc ? EyeTransformationText.PC_START : EyeTransformationText.NPC_START) + EyeTransformationText.FINISH;
				}
		),

	COW_MORPH(
			BodyCoveringType.EYE_COW_MORPH,
			Race.COW_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"cow-like",
			(Boolean isPc) -> {
				return (isPc ? EyeTransformationText.PC_START + EyeTransformationText.PC_TO_COW
						: EyeTransformationText.NPC_START + EyeTransformationText.NPC_TO_COW) + EyeTransformationText.FINISH;
				}
		),

	DEMON_COMMON(
			BodyCoveringType.EYE_DEMON_COMMON,
			Race.DEMON,
			1,
			EyeShape.ROUND,
			EyeShape.VERTICAL,
			"demonic",
			(Boolean isShortStature) -> {
				return (isShortStature ? EyeTransformationText.NPC_START + EyeTransformationText.NPC_TO_IMP
						: EyeTransformationText.NPC_START + EyeTransformationText.NPC_TO_DEMON) + EyeTransformationText.FINISH;
				}
		),

	DOG_MORPH(
			BodyCoveringType.EYE_DOG_MORPH,
			Race.DOG_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"dog-like",
			(Boolean isPc) -> {
				return (isPc ? EyeTransformationText.PC_START + EyeTransformationText.PC_TO_DOG
						: EyeTransformationText.NPC_START + EyeTransformationText.NPC_TO_DOG) + EyeTransformationText.FINISH;
				}
		),

	LYCAN(
			BodyCoveringType.EYE_LYCAN,
			Race.WOLF_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"wolf-like",
			(Boolean isPc) -> {
				return (isPc ? EyeTransformationText.PC_START + EyeTransformationText.PC_TO_LYCAN
						: EyeTransformationText.NPC_START + EyeTransformationText.NPC_TO_LYCAN) + EyeTransformationText.FINISH;
				}
		),
	
	FOX_MORPH(
			BodyCoveringType.EYE_FOX_MORPH,
			Race.FOX_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.VERTICAL,
			"fox-like",
			(Boolean isPc) -> {
				return (isPc ? EyeTransformationText.PC_START + EyeTransformationText.PC_TO_FOX
						: EyeTransformationText.NPC_START + EyeTransformationText.NPC_TO_FOX) + EyeTransformationText.FINISH;
				}
		),

	CAT_MORPH(
			BodyCoveringType.EYE_FELINE,
			Race.CAT_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.VERTICAL,
			"cat-like",
			(Boolean isPc) -> {
				return (isPc ? EyeTransformationText.PC_START + EyeTransformationText.PC_TO_CAT
						: EyeTransformationText.NPC_START + EyeTransformationText.NPC_TO_CAT) + EyeTransformationText.FINISH;}
		),

	SQUIRREL_MORPH(
			BodyCoveringType.EYE_SQUIRREL,
			Race.SQUIRREL_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"squirrel-like",
			(Boolean isPc) -> {
				return (isPc ? EyeTransformationText.PC_START + EyeTransformationText.PC_TO_SQUIRREL
						: EyeTransformationText.NPC_START + EyeTransformationText.NPC_TO_SQUIRREL) + EyeTransformationText.FINISH;}
		),

	RAT_MORPH(
			BodyCoveringType.EYE_RAT,
			Race.RAT_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"rat-like",
			(Boolean isPc) -> {
				return (isPc ? EyeTransformationText.PC_START + EyeTransformationText.PC_TO_RAT
						: EyeTransformationText.NPC_START + EyeTransformationText.NPC_TO_RAT) + EyeTransformationText.FINISH;
				}
		),

	RABBIT_MORPH(
			BodyCoveringType.EYE_RABBIT,
			Race.RABBIT_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"rabbit-like",
			(Boolean isPc) -> {
				return (isPc ? EyeTransformationText.PC_START + EyeTransformationText.PC_TO_RABBIT
						: EyeTransformationText.NPC_START + EyeTransformationText.NPC_TO_RABBIT) + EyeTransformationText.FINISH;
				}
		),
	
	BAT_MORPH(
			BodyCoveringType.EYE_BAT,
			Race.BAT_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"bat-like",
			(Boolean isPc) -> {
				return (isPc ? EyeTransformationText.PC_START + EyeTransformationText.PC_TO_BAT
						: EyeTransformationText.NPC_START + EyeTransformationText.NPC_TO_BAT) + EyeTransformationText.FINISH;
				}
		),

	ALLIGATOR_MORPH(
			BodyCoveringType.EYE_ALLIGATOR_MORPH,
			Race.ALLIGATOR_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.VERTICAL,
			"reptile-like",
			(Boolean isPc) -> {
				return (isPc ? EyeTransformationText.PC_START + EyeTransformationText.PC_TO_ALLIGATOR
						: EyeTransformationText.NPC_START + EyeTransformationText.NPC_TO_ALLIGATOR) + EyeTransformationText.FINISH;
				}
		),

	HORSE_MORPH(
			BodyCoveringType.EYE_HORSE_MORPH,
			Race.HORSE_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.HORIZONTAL,
			"horse-like",
			(Boolean isPc) -> {
				return (isPc ? EyeTransformationText.PC_START + EyeTransformationText.PC_TO_HORSE
						: EyeTransformationText.NPC_START + EyeTransformationText.NPC_TO_HORSE) + EyeTransformationText.FINISH;
				}
		),

	REINDEER_MORPH(
			BodyCoveringType.EYE_REINDEER_MORPH,
			Race.REINDEER_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.HORIZONTAL,
			"reindeer-like",
			(Boolean isPc) -> {
				return (isPc ? EyeTransformationText.PC_START + EyeTransformationText.PC_TO_REINDEER
						: EyeTransformationText.NPC_START + EyeTransformationText.NPC_TO_REINDEER) + EyeTransformationText.FINISH;
				}
		),

	HARPY(
			BodyCoveringType.EYE_HARPY,
			Race.HARPY,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"bird-like",
			(Boolean isPc) -> {
				return (isPc ? EyeTransformationText.PC_START + EyeTransformationText.PC_TO_HARPY
						: EyeTransformationText.NPC_START + EyeTransformationText.NPC_TO_HARPY) + EyeTransformationText.FINISH;
				}
		);

	private BodyCoveringType coveringType;
	private Race race;
	private int eyePairs;
	private EyeShape irisShape;
	private EyeShape pupilShape;
	private String descriptor;
	private Function<Boolean, String> transformationText;

	private EyeType(
			BodyCoveringType coveringType,
			Race race,
			int eyePairs,
			EyeShape irisShape,
			EyeShape pupilShape,
			String descriptor, 
			Function<Boolean, String> transformationText) {
		this.coveringType = coveringType;
		this.race = race;
		this.eyePairs = eyePairs;
		this.irisShape = irisShape;
		this.pupilShape = pupilShape;
		this.descriptor = descriptor;
		this.transformationText = transformationText;
	}
	
	public Race getRace() {
		return this.race;
	}
	
	public int getEyePairs() {
		return this.eyePairs;
	}
	
	public EyeShape getIrisShape() {
		return this.irisShape;
	}
	
	public EyeShape getPupilShape() {
		return this.pupilShape;
	}
	
	public Function<Boolean, String> getTransformationText() {
		return this.transformationText;
	}

	/**
	 * Use instead of <i>valueOf()</i>.
	 */
	public static EyeType getTypeFromString(String value) {
		if(value.equals("IMP")) {
			value = "DEMON_COMMON";
		}
		return valueOf(value);
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return "a pair of";
	}

	@Override
	public boolean isDefaultPlural() {
		return true;
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		return "eye";
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return "eyes";
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		return UtilText.returnStringAtRandom(StringUtils.arraySplit(this.descriptor, ',', true));
	}

	@Override
	public BodyCoveringType getBodyCoveringType(Body body) {
		return coveringType;
	}

	private static Map<Race, List<EyeType>> typesMap = new HashMap<>();
	public static List<EyeType> getEyeTypes(Race r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<EyeType> types = new ArrayList<>();
		for(EyeType type : EyeType.values()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
	
	
}

final class EyeTransformationText {
	
	static final String PC_START = "<p>"
			+ "Your [pc.eyes] suddenly grow hot and itchy, and you instinctively scrunch them up tight as you reach up to rub at them.";
	
	static final String NPC_START = "<p>"
			+ "[npc.NamePos] [npc.eyes] suddenly grow hot and itchy, and [npc.she] instinctively scrunches them up tight as [npc.she] reaches up to rub at them.";
	
	static final String PC_TO_HUMAN = " By the time you hesitantly open them again, they've changed into human eyes, with normally-proportioned irises and pupils."
			+ "<br/>"
			+ "You now have [style.boldHuman(human eyes)]";
	static final String NPC_TO_HUMAN = " By the time [npc.she] hesitantly opens them again, they've changed into human eyes, with normally-proportioned irises and pupils."
			+ "<br/>"
			+ "[npc.Name] now has [style.boldHuman(human eyes)]";
	
	static final String NPC_TO_DEMON = " By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into demonic eyes, with vertical pupils and large irises."
			+ "<br/>"
			+ "[npc.Name] now [npc.has] [style.boldDemon(demonic eyes)]";
	
	static final String NPC_TO_IMP = " By the time [npc.she] hesitantly [npc.verb(open)] them again, they've changed into impish eyes, with vertical pupils and large irises."
			+ "<br/>"
			+ "[npc.Name] now [npc.has] [style.boldImp(impish eyes)]";
	
	static final String PC_TO_COW = " By the time you hesitantly open them again, they've changed into cow-like eyes, with larger-than-average pupils and irises."
			+ "<br/>"
			+ "You now have [style.boldCowMorph(cow-like eyes)]";
	
	static final String NPC_TO_COW = " By the time [npc.she] hesitantly opens them again, they've changed into cow-like eyes, with larger-than-average pupils and irises."
			+ "<br/>"
			+ "[npc.Name] now has [style.boldCowMorph(cow-like eyes)]";
	
	static final String PC_TO_DOG = " By the time you hesitantly open them again, they've changed into dog-like eyes, with larger-than-average pupils and irises."
			+ "<br/>"
			+ "You now have [style.boldDogMorph(dog-like eyes)]";
	
	static final String NPC_TO_DOG = " By the time [npc.she] hesitantly opens them again, they've changed into dog-like eyes, with larger-than-average pupils and irises." 
			+ "<br/>"
			+ "[npc.Name] now has [style.boldDogMorph(dog-like eyes)]";
	
	static final String PC_TO_FOX = " By the time you hesitantly open them again, they've changed into fox-like eyes, with larger-than-average pupils and irises."
			+ "</br>"
			+ "You now have [style.boldFoxMorph(fox-like eyes)]";
	
	static final String NPC_TO_FOX = " By the time [npc.she] hesitantly opens them again, they've changed into fox-like eyes, with larger-than-average pupils and irises."
			+ "</br>"
			+ "[npc.Name] now has [style.boldFoxMorph(fox-like eyes)]";
	
	static final String PC_TO_LYCAN = " By the time you hesitantly open them again, they've changed into wolf-like eyes, with larger-than-average pupils and irises."
			+ "<br/>"
			+ "You now have [style.boldWolfMorph(wolf-like eyes)]";
	
	static final String NPC_TO_LYCAN = " By the time [npc.she] hesitantly opens them again, they've changed into wolf-like eyes, with larger-than-average pupils and irises."
			+ "<br/>"
			+ "[npc.Name] now has [style.boldWolfMorph(wolf-like eyes)]";
	
	static final String PC_TO_CAT = " By the time you hesitantly open them again, they've changed into cat-like eyes, with smaller-than-average pupils and large irises." 
			+ "<br/>"
			+ "You now have [style.boldCatMorph(cat-like eyes)]";
			
	static final String NPC_TO_CAT = " By the time [npc.she] hesitantly opens them again, they've changed into cat-like eyes, with smaller-than-average pupils and large irises." 
			+ "<br/>"
			+ "[npc.Name] now has [style.boldCatMorph(cat-like eyes)]";
	
	static final String PC_TO_SQUIRREL = " By the time you hesitantly open them again, they've changed into squirrel-like eyes, with larger-than-average pupils and small irises."
			+ "<br/>"
			+ "You now have [style.boldSquirrelMorph(squirrel-like eyes)]";
			
	static final String NPC_TO_SQUIRREL = " By the time [npc.she] hesitantly opens them again, they've changed into squirrel-like eyes, with larger-than-average pupils and small irises."
			+ "<br/>"
			+ "[npc.Name] now has [style.boldSquirrelMorph(squirrel-like eyes)]";
	
	static final String PC_TO_RAT = " By the time you hesitantly open them again, they've changed into rat-like eyes, with larger-than-average pupils and small irises."
			+ "<br/>"
			+ "You now have [style.boldRatMorph(rat-like eyes)]";
	
	static final String NPC_TO_RAT = " By the time [npc.she] hesitantly opens them again, they've changed into rat-like eyes, with larger-than-average pupils and small irises."
			+ "<br/>"
			+ "[npc.Name] now has [style.boldRatMorph(rat-like eyes)]";
	
	static final String PC_TO_RABBIT = " By the time you hesitantly open them again, they've changed into rabbit-like eyes, with larger-than-average pupils and small irises." 
			+ "<br/>"
			+ "You now have [style.boldRabbitMorph(rabbit-like eyes)]";
			
	static final String NPC_TO_RABBIT = " By the time [npc.she] hesitantly opens them again, they've changed into rabbit-like eyes, with larger-than-average pupils and small irises."
			+ "<br/>"
			+ "[npc.Name] now has [style.boldRabbitMorph(rabbit-like eyes)]";
	
	static final String PC_TO_BAT = " By the time you hesitantly open them again, they've changed into bat-like eyes, with larger-than-average pupils and small irises."
			+ "<br/>"
			+ "You now have [style.boldBatMorph(bat-like eyes)]";
	
	static final String NPC_TO_BAT = " By the time [npc.she] hesitantly opens them again, they've changed into bat-like eyes, with larger-than-average pupils and small irises."
			+ "<br/>"
			+ "[npc.Name] now has [style.boldBatMorph(bat-like eyes)]";
	
	static final String PC_TO_ALLIGATOR = " By the time you hesitantly open them again, they've changed into alligator-like eyes, with larger-than-average pupils and small irises."
			+ "<br/>"
			+ "You now have [style.boldGatorMorph(alligator-like eyes)]";
	
	static final String NPC_TO_ALLIGATOR = " By the time [npc.she] hesitantly opens them again, they've changed into alligator-like eyes, with larger-than-average pupils and small irises."
			+ "<br/>"
			+ "[npc.Name] now has [style.boldGatorMorph(alligator-like eyes)]";
	
	static final String PC_TO_HORSE = " By the time you hesitantly open them again, they've changed into horse-like eyes, with larger-than-average pupils and irises."
			+ "<br/>"
			+ "You now have [style.boldHorseMorph(horse-like eyes)]";
	
	static final String NPC_TO_HORSE = " By the time [npc.she] hesitantly opens them again, they've changed into horse-like eyes, with larger-than-average pupils and irises."
			+ "<br/>"
			+ "[npc.Name] now has [style.boldHorseMorph(horse-like eyes)]";
	
	static final String PC_TO_REINDEER = " By the time you hesitantly open them again, they've changed into reindeer-like eyes, with larger-than-average pupils and irises."
			+ "<br/>"
			+ "You now have [style.boldReindeerMorph(reindeer-like eyes)]";
	
	static final String NPC_TO_REINDEER = " By the time [npc.she] hesitantly opens them again, they've changed into reindeer-like eyes, with larger-than-average pupils and irises."
			+ "<br/>"
			+ "[npc.Name] now has [style.boldReindeerMorph(reindeer-like eyes)]";
	
	static final String PC_TO_HARPY = " By the time you hesitantly open them again, they've changed into bird-like eyes, with larger-than-average pupils and small irises."
			+ "<br/>"
			+ "You now have [style.boldHarpy(harpy eyes)]";
	
	static final String NPC_TO_HARPY = " By the time [npc.she] hesitantly opens them again, they've changed into bird-like eyes, with larger-than-average pupils and small irises."
			+ "<br/>"
			+ "[npc.Name] now has [style.boldHarpy(harpy eyes)]";
	
	static final String FINISH = ", with [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] and [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)]."
			+ "</p>";
}