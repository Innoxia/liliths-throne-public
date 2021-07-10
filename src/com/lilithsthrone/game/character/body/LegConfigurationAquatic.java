package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.utils.Util;
import java.util.HashMap;

/**
 * Container class for the combination of the LegConfiguration and the boolean aquatic.
 *
 * @since 0.3.14
 * @version 0.3.14
 * @author Stadler76
 */
public class LegConfigurationAquatic {
	private final LegConfiguration legConfiguration;
	private final boolean aquatic;

	public LegConfigurationAquatic(LegConfiguration legConfiguration, boolean aquatic) {
		this.legConfiguration = legConfiguration;
		this.aquatic = aquatic;
	}

	public LegConfiguration getLegConfiguration() {
		return legConfiguration;
	}

	public boolean isAquatic() {
		return aquatic;
	}

	public static HashMap<LegConfigurationAquatic, String> getFeralNamesMap(HashMap<LegConfigurationAquatic, String> list, LegConfiguration legConfiguration, String feralName) {
		for (boolean isAquatic : Util.newArrayListOfValues(false, true)) {
			list.put(new LegConfigurationAquatic(legConfiguration, isAquatic), feralName);
		}
		return list;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof LegConfigurationAquatic) {
			if (((LegConfigurationAquatic) o).aquatic == this.aquatic &&
				((LegConfigurationAquatic) o).legConfiguration == this.legConfiguration) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hash = 17;
		hash = 31 * hash + this.legConfiguration.hashCode();
		hash = 31 * hash + (this.aquatic ? 1 : 0);
		return hash;
	}
}
