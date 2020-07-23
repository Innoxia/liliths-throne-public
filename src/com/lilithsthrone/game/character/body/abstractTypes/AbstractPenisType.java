package com.lilithsthrone.game.character.body.abstractTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.8.9
 * @version 0.3.8.9
 * @author Innoxia
 */
public abstract class AbstractPenisType implements BodyPartTypeInterface {
	
	// Maps the name to weighting for use in random selection:
	protected static final Map<String, Integer> BASE_NAMES_SINGULAR = Util.newHashMapOfValues(new Value<>("cock", 3), new Value<>("dick", 2), new Value<>("shaft", 1));
	protected static final Map<String, Integer> BASE_NAMES_PLURAL = Util.newHashMapOfValues(new Value<>("cocks", 3), new Value<>("dicks", 2), new Value<>("shafts", 1));
	
	private BodyCoveringType skinType;
	private Race race;
	private AbstractTesticleType testicleType;
	
	private List<String> namesFeminine;
	private List<String> namesPluralFeminine;
	private List<String> namesMasculine;
	private List<String> namesPluralMasculine;
	
	private List<String> descriptors;
	
	private String transformationDescription;
	private String bodyDescription;
	
	private List<PenetrationModifier> defaultRacialPenetrationModifiers;
	
	/**
	 * @param skinType What covers this penis type.
	 * @param race What race has this penis type.
	 * @param testicleType The type of testicles that this penis has.
	 * @param namesFeminine A list of singular feminine names for this penis type.
	 *  Pass in null to use generic names.
	 *  Empty values also use generic names.
	 *  Names ending in '-' are handled in a special manner by appending a generic penis name to the end of it before returning.
	 * @param namesPluralFeminine A list of plural feminine names for this penis type.
	 *  Pass in null to use generic names.
	 *  Empty values also use generic names.
	 *  Names ending in '-' are handled in a special manner by appending a generic penis name to the end of it before returning.
	 * @param namesMasculine A list of singular masculine names for this penis type. (Special effects are the same as namesFeminine.)
	 * @param namesPluralMasculine A list of plural masculine names for this penis type. (Special effects are the same as namesPluralFeminine.)
	 * @param descriptors The descriptors that can be used to this penis type.
	 * @param transformationDescription A paragraph describing a character's penis transforming into this penis type. Parsing assumes that the character already has this penis type and associated skin covering.
	 * @param bodyDescription A sentence or two to describe this penis type, as seen in the character view screen. It should follow the same format as all of the other entries in the PenisType class.
	 * @param defaultRacialPenetrationModifiers Which modifiers this penis naturally spawns with.
	 */
	public AbstractPenisType(BodyCoveringType skinType,
			Race race,
			AbstractTesticleType testicleType,
			List<String> namesFeminine,
			List<String> namesPluralFeminine,
			List<String> namesMasculine,
			List<String> namesPluralMasculine,
			List<String> descriptors,
			String transformationDescription,
			String bodyDescription,
			List<PenetrationModifier> defaultRacialPenetrationModifiers) {
		this.skinType = skinType;
		this.race = race;
		this.testicleType = testicleType;
		
		this.namesFeminine = namesFeminine;
		this.namesPluralFeminine = namesPluralFeminine;
		this.namesMasculine = namesMasculine;
		this.namesPluralMasculine = namesPluralMasculine;
		
		this.descriptors = descriptors;
		
		this.transformationDescription = transformationDescription;
		this.bodyDescription = bodyDescription;
		
		if(defaultRacialPenetrationModifiers==null) {
			this.defaultRacialPenetrationModifiers = new ArrayList<>();
		} else {
			this.defaultRacialPenetrationModifiers = defaultRacialPenetrationModifiers;
		}
	}
	
	public AbstractPenisType(BodyCoveringType skinType,
			Race race,
			AbstractTesticleType testicleType,
			String transformationDescription,
			String bodyDescription,
			List<PenetrationModifier> defaultRacialPenetrationModifiers) {
		this(skinType,
				race,
				testicleType,
				null, null,
				null, null,
				null,
				transformationDescription,
				bodyDescription,
				defaultRacialPenetrationModifiers);
	}
	
	public AbstractTesticleType getTesticleType() {
		return testicleType;
	}

	public List<PenetrationModifier> getDefaultRacialPenetrationModifiers() {
		return defaultRacialPenetrationModifiers;
	}
	
	public String getPenisHeadName(GameCharacter gc) {
		return UtilText.returnStringAtRandom("head", "tip");
	}
	
	public String getPenisHeadDescriptor(GameCharacter gc) {
		for(PenetrationModifier mod : PenetrationModifier.values()) {
			if(gc.getPenisModifiers().contains(mod)) {
				switch(mod) {
					case BLUNT:
						return UtilText.returnStringAtRandom("blunt");
					case FLARED:
						return UtilText.returnStringAtRandom("wide", "flared", "flat");
					case TAPERED:
						return UtilText.returnStringAtRandom("tapered", "pointed");
					case KNOTTED:
					case PREHENSILE:
					case RIBBED:
					case SHEATHED:
					case BARBED:
					case TENTACLED:
					case VEINY:
						break;
				}
			}
		}
		return "";
	}
	
	public String getCumName(GameCharacter gc) {
		return this.getTesticleType().getFluidType().getName(gc);
	}
	
	public String getCumDescriptor(GameCharacter gc) {
		return this.getTesticleType().getFluidType().getDescriptor(gc);
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		return "";
	}

	@Override
	public boolean isDefaultPlural() {
		return false;
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		String name;
		Map<String, Integer> returnNames = new HashMap<>(BASE_NAMES_SINGULAR);
		if(UtilText.isInSpeech()) {
			returnNames.remove("shaft");
		}
		
		if(gc.isFeminine()) {
			if(namesFeminine==null || namesFeminine.isEmpty()) {
				name = Util.getRandomObjectFromWeightedMap(returnNames);
				
			} else {
				name = Util.randomItemFrom(namesFeminine);
			}
		} else {
			if(namesMasculine==null || namesMasculine.isEmpty()) {
				name = Util.getRandomObjectFromWeightedMap(returnNames);
				
			} else {
				name = Util.randomItemFrom(namesMasculine);
			}
		}
		
		if(name.endsWith("-")) {
			if(Math.random()<0.25f) { // 25% chance to return this '-' name.
				return name + Util.getRandomObjectFromWeightedMap(returnNames);
			} else {
				return name + Util.getRandomObjectFromWeightedMap(returnNames);
			}
		}
		if(name.isEmpty()) {
			return Util.getRandomObjectFromWeightedMap(returnNames);
		}
		
		return name;
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		String name;
		Map<String, Integer> returnNames = new HashMap<>(BASE_NAMES_PLURAL);
		if(UtilText.isInSpeech()) {
			returnNames.remove("shafts");
		}
		
		if(gc.isFeminine()) {
			if(namesPluralFeminine==null || namesPluralFeminine.isEmpty()) {
				name = Util.getRandomObjectFromWeightedMap(returnNames);
				
			} else {
				name = Util.randomItemFrom(namesPluralFeminine);
			}
		} else {
			if(namesPluralMasculine==null || namesPluralMasculine.isEmpty()) {
				name = Util.getRandomObjectFromWeightedMap(returnNames);
				
			} else {
				name = Util.randomItemFrom(namesPluralMasculine);
			}
		}
		
		if(name.endsWith("-")) {
			if(Math.random()<0.25f) { // 25% chance to return this '-' name.
				return name + Util.getRandomObjectFromWeightedMap(returnNames);
			} else {
				return name + Util.getRandomObjectFromWeightedMap(returnNames);
			}
		}
		if(name.isEmpty()) {
			return Util.getRandomObjectFromWeightedMap(returnNames);
		}
		
		return name;
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		if(descriptors!=null) {
			return Util.randomItemFrom(descriptors);
		}
		return "";
	}
	
	@Override
	public BodyCoveringType getBodyCoveringType(Body body) {
		return skinType;
	}

	@Override
	public Race getRace() {
		return race;
	}

//	@Override
	public String getBodyDescription(GameCharacter owner) {
		return UtilText.parse(owner, bodyDescription);
	}
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, transformationDescription);
	}
	
	/**
	 * This method is called immediately before and immediately after the target's penis type is changed into into this type. When before, applicationAfterChangeApplied is false, and when after, applicationAfterChangeApplied is true.
	 * It is not called if owner is null.
	 */
	public String applyAdditionalTransformationEffects(GameCharacter owner, boolean applicationAfterChangeApplied) {
		return "";
	}
}
