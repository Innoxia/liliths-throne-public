package com.lilithsthrone.game.character.body.abstractTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.8.9
 * @version 0.3.8.9
 * @author Innoxia
 */
public abstract class AbstractVaginaType implements BodyPartTypeInterface {
	
	// Maps the name to weighting for use in random selection:
	protected static final Map<String, Integer> BASE_NAMES_SINGULAR = Util.newHashMapOfValues(
			new Value<>("cherry", 2),
			new Value<>("cunt", 2),
			new Value<>("kitty", 1),
			new Value<>("pussy", 4),
			new Value<>("sex", 1),
			new Value<>("slit", 1),
			new Value<>("twat", 2));
	protected static final Map<String, Integer> BASE_NAMES_PLURAL = Util.newHashMapOfValues(
			new Value<>("cherries", 2),
			new Value<>("cunts", 2),
			new Value<>("kitties", 1),
			new Value<>("pussies", 4),
			new Value<>("sexes", 1),
			new Value<>("slits", 1),
			new Value<>("twats", 2));
	
	private BodyCoveringType skinType;
	private AbstractFluidType fluidType;
	private Race race;
	private boolean eggLayer;
	
	private List<String> names;
	private List<String> namesPlural;
	
	private List<String> descriptors;
	
	private String transformationDescription;
	private String bodyDescription;
	
	private List<OrificeModifier> defaultRacialOrificeModifiers;
	
	/**
	 * @param skinType What covers this vagina type.
	 * @param fluidType The type of girlcum that this vagina produces.
	 * @param race What race has this vagina type.
	 * @param eggLayer If this vagina lays eggs or not.
	 * @param names A list of singular names for this vagina type.
	 *  Pass in null to use generic names.
	 *  Empty values also use generic names.
	 *  Names ending in '-' are handled in a special manner by appending a generic vagina name to the end of it before returning.
	 * @param namesPlural A list of plural names for this vagina type.
	 *  Pass in null to use generic names.
	 *  Empty values also use generic names.
	 *  Names ending in '-' are handled in a special manner by appending a generic vagina name to the end of it before returning.
	 * @param descriptors The descriptors that can be used to this vagina type.
	 * @param transformationDescription A paragraph describing a character's vagina transforming into this vagina type. Parsing assumes that the character already has this vagina type and associated skin covering.
	 * @param bodyDescription A sentence or two to describe this vagina type, as seen in the character view screen. It should follow the same format as all of the other entries in the PenisType class.
	 * @param defaultRacialPenetrationModifiers Which modifiers this vagina naturally spawns with.
	 */
	public AbstractVaginaType(BodyCoveringType skinType,
			AbstractFluidType fluidType,
			Race race,
			boolean eggLayer,
			List<String> names,
			List<String> namesPlural,
			List<String> descriptors,
			String transformationDescription,
			String bodyDescription,
			List<OrificeModifier> defaultRacialOrificeModifiers) {
		this.skinType = skinType;
		this.fluidType = fluidType;
		this.race = race;
		this.eggLayer = eggLayer;
		
		this.names = names;
		this.namesPlural = namesPlural;
		
		this.descriptors = descriptors;
		
		this.transformationDescription = transformationDescription;
		this.bodyDescription = bodyDescription;

		if(defaultRacialOrificeModifiers==null) {
			this.defaultRacialOrificeModifiers = new ArrayList<>();
		} else {
			this.defaultRacialOrificeModifiers = defaultRacialOrificeModifiers;
		}
	}
	
	public AbstractVaginaType(BodyCoveringType skinType,
			AbstractFluidType fluidType,
			Race race,
			boolean eggLayer,
			String transformationDescription,
			String bodyDescription,
			List<OrificeModifier> defaultRacialOrificeModifiers) {
		this(skinType,
				fluidType,
				race,
				eggLayer,
				null, null,
				null,
				transformationDescription,
				bodyDescription,
				defaultRacialOrificeModifiers);
	}

	public AbstractFluidType getFluidType() {
		return fluidType;
	}
	
	public boolean isEggLayer() {
		return eggLayer;
	}

	public List<OrificeModifier> getDefaultRacialOrificeModifiers() {
		return defaultRacialOrificeModifiers;
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
		if(!gc.isVaginaVirgin()) {
			returnNames.remove("cherry");
		}
		
		if(names==null || names.isEmpty()) {
			name = Util.getRandomObjectFromWeightedMap(returnNames);
			
		} else {
			name = Util.randomItemFrom(names);
		}
		
		if(name.endsWith("-")) {
			if(Math.random()<0.25f) { // 25% chance to return this '-' name.
				return name + Util.getRandomObjectFromWeightedMap(returnNames);
			} else {
				return Util.getRandomObjectFromWeightedMap(returnNames);
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
		if(!gc.isVaginaVirgin()) {
			returnNames.remove("cherries");
		}
		
		if(namesPlural==null || namesPlural.isEmpty()) {
			name = Util.getRandomObjectFromWeightedMap(returnNames);
			
		} else {
			name = Util.randomItemFrom(namesPlural);
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
	 * This method is called immediately before and immediately after the target's vagina type is changed into into this type. When before, applicationAfterChangeApplied is false, and when after, applicationAfterChangeApplied is true.
	 * It is not called if owner is null.
	 */
	public String applyAdditionalTransformationEffects(GameCharacter owner, boolean applicationAfterChangeApplied) {
		return "";
	}
}
