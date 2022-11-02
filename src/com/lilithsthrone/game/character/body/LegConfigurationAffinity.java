package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.body.valueEnums.Affinity;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import java.util.HashMap;

/**
 * Container class for the combination of the LegConfiguration and the Affinity.
 *
 * @since 0.4.6.6
 * @version 0.4.6.6
 * @author Stadler76
 */
public class LegConfigurationAffinity {
	private final LegConfiguration legConfiguration;
	private final Affinity affinity;

	public LegConfigurationAffinity(LegConfiguration legConfiguration, Affinity affinity) {
		this.legConfiguration = legConfiguration;
		this.affinity = affinity;
	}

	public LegConfiguration getLegConfiguration() {
		return legConfiguration;
	}

	public Affinity getAffinity() {
		return affinity;
	}

	public static HashMap<LegConfigurationAffinity, String> getFeralNamesMap(
			HashMap<LegConfigurationAffinity, String> list,
			LegConfiguration legConfiguration,
			String feralName) {
		for (Affinity affinity : Affinity.getAllAffinities()) {
			list.put(new LegConfigurationAffinity(legConfiguration, affinity), feralName);
		}
		return list;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof LegConfigurationAffinity) {
			return ((LegConfigurationAffinity) o).affinity == affinity &&
					((LegConfigurationAffinity) o).legConfiguration == legConfiguration;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hash = 17;
		hash = 31 * hash + legConfiguration.hashCode();
		hash = 31 * hash + affinity.hashCode();
		return hash;
	}

}
