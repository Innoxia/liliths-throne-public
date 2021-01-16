package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import java.util.Objects;

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

	public LegConfigurationAquatic(LegConfiguration legConfiguration) {
		this.legConfiguration = legConfiguration;
		this.aquatic = false;
	}

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

	@Override
	public boolean equals(Object o) {
		if (o instanceof LegConfigurationAquatic) {
			if (((LegConfigurationAquatic) o).isAquatic() == isAquatic() &&
				((LegConfigurationAquatic) o).getLegConfiguration() == getLegConfiguration()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 89 * hash + Objects.hashCode(this.legConfiguration);
		hash = 89 * hash + (this.aquatic ? 1 : 0);
		return hash;
	}
}
