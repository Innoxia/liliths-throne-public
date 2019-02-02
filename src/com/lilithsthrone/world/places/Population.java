package com.lilithsthrone.world.places;

import java.util.Map;

import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesSpawnRarity;

/**
 * @since 0.2.12
 * @version 0.3.1
 * @author Innoxia
 */
public class Population {
	
	private PopulationType type;
	private PopulationDensity density;
	private Map<Subspecies, SubspeciesSpawnRarity> species;
	
	public Population(PopulationType type, PopulationDensity density, Map<Subspecies, SubspeciesSpawnRarity> species) {
		this.type = type;
		this.density = density;
		this.species = species;
	}

	public Map<Subspecies, SubspeciesSpawnRarity> getSpecies() {
		return species;
	}

	public PopulationType getType() {
		return type;
	}

	public PopulationDensity getDensity() {
		return density;
	}
	
}
