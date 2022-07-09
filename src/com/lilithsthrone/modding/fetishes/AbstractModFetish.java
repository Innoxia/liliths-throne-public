/**
 * 
 */
package com.lilithsthrone.modding.fetishes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.FetishExperience;
import com.lilithsthrone.modding.PluginUtils;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;

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
		super(renderingPriority, name, shortDescriptor, pathName, experienceGainFromSexAction, colourShade,
				attributeModifiers, extraEffects, fetishesForAutomaticUnlock);
		// throw new ModdingFrameworkError("Please use the AbstractModFetish
		// constructors with IDs in them.");
	}

	private AbstractModFetish(int renderingPriority, String name, String shortDescriptor, String pathName,
			FetishExperience experienceGainFromSexAction, List<Colour> colourShade,
			HashMap<AbstractAttribute, Integer> attributeModifiers, List<String> extraEffects,
			List<AbstractFetish> fetishesForAutomaticUnlock) {
		super(renderingPriority, name, shortDescriptor, pathName, experienceGainFromSexAction, colourShade,
				attributeModifiers, extraEffects, fetishesForAutomaticUnlock);
		// throw new ModdingFrameworkError("Please use the AbstractModFetish
		// constructors with IDs in them.");
	}

	
	@Override
	protected InputStream getInputStreamForSVG() {
		String realFilePath = PluginUtils.GetModPath(getClass())
				.resolve("fetishes")
				.resolve(pathName + ".svg")
				.toString();
		File f = new File(realFilePath);
		if(!f.isFile()) {
			System.err.println("Error! Fetish icon file does not exist (Trying to read from '"+realFilePath+"')!");
		}
		else if(!f.canRead()) {
			System.err.println("Error! Fetish icon file is not readable (Trying to read from '"+realFilePath+"')!");
		}
		
		System.err.println("Reading "+realFilePath);
		
		try {
			return new FileInputStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
