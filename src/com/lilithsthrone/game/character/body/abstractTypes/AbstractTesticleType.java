package com.lilithsthrone.game.character.body.abstractTypes;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.8.8
 * @version 0.3.9.1
 * @author Innoxia
 */
public abstract class AbstractTesticleType implements BodyPartTypeInterface {

	private BodyCoveringType skinType;
	private AbstractRace race;
	private AbstractFluidType fluidType;
	private boolean internal;
	
	private List<String> names;
	private List<String> namesPlural;
	private List<String> descriptors;
	
	/**
	 * @param skinType What covers this testicle type (i.e skin/fur/feather type).
	 * @param race What race has this testicle type.
	 * @param fluidType The type of cum that these testicles produce.
	 * @param internal If these testicles are internal by default.
	 * @param names A list of singular names for this testicle type. Pass in null to use generic names.
	 * @param namesPlural A list of plural names for this testicle type. Pass in null to use generic names.
	 * @param descriptors The descriptors that can be used for this testicle type.
	 */
	public AbstractTesticleType(BodyCoveringType skinType,
			AbstractRace race,
			AbstractFluidType fluidType,
			boolean internal,
			List<String> names,
			List<String> namesPlural,
			List<String> descriptors) {
		
		this.skinType = skinType;
		this.race = race;
		this.fluidType = fluidType;
		this.internal = internal;
		
		this.names = names;
		this.namesPlural = namesPlural;
		this.descriptors = descriptors;
	}
	
	public AbstractTesticleType(BodyCoveringType skinType,
			AbstractRace race,
			AbstractFluidType fluidType,
			boolean internal) {
		this(skinType,
				race,
				fluidType,
				internal,
				null, null, null);
	}
	
	public AbstractFluidType getFluidType() {
		return fluidType;
	}

	public boolean isInternal() {
		return internal;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc.getTesticleCount()==2) {
			return "a pair of";
		} else if(gc.getTesticleCount()==3) {
			return "a trio of";
		} else {
			return Util.intToString(gc.getTesticleCount());
		}
	}

	@Override
	public boolean isDefaultPlural() {
		return true;
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		if(names==null) {
			return UtilText.returnStringAtRandom("ball", "testicle");
		}
		return Util.randomItemFrom(names);
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		if(namesPlural==null) {
			return UtilText.returnStringAtRandom("balls", "testicles");
		}
		return Util.randomItemFrom(namesPlural);
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		if(descriptors==null) {
			return "";
		}
		return Util.randomItemFrom(descriptors);
	}

	@Override
	/**
	 * <b>This should never be used - the covering of breasts is determined by the torso's covering!</b>
	 */
	public BodyCoveringType getBodyCoveringType(Body body) {
		if(body!=null) {
			return body.getTorso().getBodyCoveringType(body);
		}
		return skinType;
	}

	@Override
	public AbstractRace getRace() {
		return race;
	}
	
}
