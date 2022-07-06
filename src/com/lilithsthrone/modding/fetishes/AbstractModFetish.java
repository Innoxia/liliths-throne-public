/**
 * 
 */
package com.lilithsthrone.modding.fetishes;

import java.util.HashMap;
import java.util.List;

import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.FetishExperience;
import com.lilithsthrone.modding.ModdingFrameworkError;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;

/**
 * @author nexis
 *
 */
public abstract class AbstractModFetish extends AbstractFetish {
	public AbstractModFetish(String id, int renderingPriority, String name, String shortDescriptor, String pathName,
			FetishExperience experienceGainFromSexAction, Colour colourShade,
			HashMap<AbstractAttribute, Integer> attributeModifiers, List<String> extraEffects,
			List<AbstractFetish> fetishesForAutomaticUnlock) {
		this(id, renderingPriority, name, shortDescriptor, pathName, experienceGainFromSexAction,
				Util.newArrayListOfValues(colourShade), attributeModifiers, extraEffects, fetishesForAutomaticUnlock);
	}

	public AbstractModFetish(String id, int renderingPriority, String name, String shortDescriptor, String pathName,
			FetishExperience experienceGainFromSexAction, List<Colour> colourShade,
			HashMap<AbstractAttribute, Integer> attributeModifiers, List<String> extraEffects,
			List<AbstractFetish> fetishesForAutomaticUnlock) {
		super(id, renderingPriority, name, shortDescriptor, pathName, experienceGainFromSexAction, colourShade,
				attributeModifiers, extraEffects, fetishesForAutomaticUnlock);
	}

	// Hide the other ctors.
	private AbstractModFetish(int renderingPriority, String name, String shortDescriptor, String pathName,
			FetishExperience experienceGainFromSexAction, Colour colourShade,
			HashMap<AbstractAttribute, Integer> attributeModifiers, List<String> extraEffects,
			List<AbstractFetish> fetishesForAutomaticUnlock) {
		super(renderingPriority, name, shortDescriptor, pathName, experienceGainFromSexAction, colourShade, attributeModifiers,
				extraEffects, fetishesForAutomaticUnlock);
		//throw new ModdingFrameworkError("Please use the AbstractModFetish constructors with IDs in them.");
	}
	private AbstractModFetish(int renderingPriority, String name, String shortDescriptor, String pathName,
			FetishExperience experienceGainFromSexAction, List<Colour> colourShade,
			HashMap<AbstractAttribute, Integer> attributeModifiers, List<String> extraEffects,
			List<AbstractFetish> fetishesForAutomaticUnlock) {
		super(renderingPriority, name, shortDescriptor, pathName, experienceGainFromSexAction, colourShade, attributeModifiers,
				extraEffects, fetishesForAutomaticUnlock);
		//throw new ModdingFrameworkError("Please use the AbstractModFetish constructors with IDs in them.");
	}

}
