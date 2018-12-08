package com.lilithsthrone.world.places;

import java.util.List;

import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.12
 * @version 0.2.12
 * @author Innoxia
 */
public class Population {
	
	private PopulationType type;
	private PopulationDensity density;
	private List<Subspecies> species;
	
	public Population(PopulationType type, PopulationDensity density, Subspecies... species) {
		this.type = type;
		this.density = density;
		this.species = Util.newArrayListOfValues(species);
	}
	
	public Population(PopulationType type, PopulationDensity density, List<Subspecies> species) {
		this.type = type;
		this.density = density;
		this.species = species;
	}

	public List<Subspecies> getSpecies() {
		return species;
	}

	public PopulationType getType() {
		return type;
	}

	public PopulationDensity getDensity() {
		return density;
	}
	
}
