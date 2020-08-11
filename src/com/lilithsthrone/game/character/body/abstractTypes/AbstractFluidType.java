package com.lilithsthrone.game.character.body.abstractTypes;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.FluidTypeBase;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.8.2
 * @version 0.3.9.1
 * @author Innoxia
 */
public abstract class AbstractFluidType implements BodyPartTypeInterface {
	
	private FluidTypeBase baseFluidType;
	private FluidFlavour flavour;
	private AbstractRace race;
	
	private List<String> namesMasculine;
	private List<String> namesFeminine;
	
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	List<FluidModifier> defaultFluidModifiers;
	
	/**
	 * @param baseFluidType The base type of this fluid (milk, cum, or girlcum).
	 * @param flavour The default flavour for this fluid.
	 * @param race What race has this fluid type.
	 * @param names A list of singular names for this fluid type.
	 *  Pass in null to use generic names.
	 *  Empty values also use generic names.
	 *  Names ending in '-' are handled in a special manner by appending a generic fluid name to the end of it before returning.
	 * @param namesPlural A list of plural names for this fluid type.
	 *  Pass in null to use generic names.
	 *  Empty values also use generic names.
	 *  Names ending in '-' are handled in a special manner by appending a generic fluid name to the end of it before returning.
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this fluid type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this fluid type.
	 * @param defaultFluidModifiers Which modifiers this fluid naturally spawns with.
	 */
	public AbstractFluidType(
			FluidTypeBase baseFluidType,
			FluidFlavour flavour,
			AbstractRace race,
			List<String> namesMasculine,
			List<String> namesFeminine,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			List<FluidModifier> defaultFluidModifiers) {
		
		this.baseFluidType = baseFluidType;
		this.flavour = flavour;
		this.race = race;
		
		this.namesMasculine = namesMasculine;
		this.namesFeminine = namesFeminine;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		this.defaultFluidModifiers = defaultFluidModifiers;
	}
	
	
	@Override
	public String toString() {
		System.err.println("Warning! AbstractFluidType is calling toString()");
		return super.toString();
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		return "some";
	}

	@Override
	public boolean isDefaultPlural() {
		return false;
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		String name;
		
		if(gc==null || gc.isFeminine()) {
			if(namesFeminine==null) {
				return Util.randomItemFrom(baseFluidType.getNames());
			}
			name = Util.randomItemFrom(namesFeminine);
			
		} else {
			if(namesMasculine==null) {
				return Util.randomItemFrom(baseFluidType.getNames());
			}
			name = Util.randomItemFrom(namesMasculine);
		}
		
		if(name.isEmpty()) {
			return Util.randomItemFrom(baseFluidType.getNames());
		}
		if(name.endsWith("-")) {
			if(Math.random()<0.25f) { // 25% chance to return this '-' name.
				return name + Util.randomItemFrom(baseFluidType.getNames());
			} else {
				return Util.randomItemFrom(baseFluidType.getNames());
			}
		}
		return name;
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return getNameSingular(gc);
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		if(gc==null || gc.isFeminine()) {
			return Util.randomItemFrom(descriptorsFeminine);
		} else {
			return Util.randomItemFrom(descriptorsMasculine);
		}
	}

	@Override
	public BodyCoveringType getBodyCoveringType(Body body) {
		return baseFluidType.getCoveringType();
	}

	@Override
	public AbstractRace getRace() {
		return race;
	}
	
	public FluidTypeBase getBaseType() {
		return baseFluidType;
	}
	
	public FluidFlavour getFlavour() {
		return flavour;
	}
	
	public List<FluidModifier> getDefaultFluidModifiers() {
		return defaultFluidModifiers;
	}
}
