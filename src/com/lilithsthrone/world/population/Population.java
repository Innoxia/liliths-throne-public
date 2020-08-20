package com.lilithsthrone.world.population;

import java.util.Map;

import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesSpawnRarity;

/**
 * @since 0.2.12
 * @version 0.3.7.7
 * @author Innoxia
 */
public class Population {
	
	private boolean pluralPopulation;
	private AbstractPopulationType type;
	private PopulationDensity density;
	private Map<Subspecies, SubspeciesSpawnRarity> species;
	
	public Population(boolean pluralPopulation, AbstractPopulationType type, PopulationDensity density, Map<Subspecies, SubspeciesSpawnRarity> species) {
		this.pluralPopulation = pluralPopulation;
		this.type = type;
		this.density = density;
		this.species = species;
	}
	
	public boolean isPluralPopulation() {
		return pluralPopulation;
	}

	public AbstractPopulationType getType() {
		return type;
	}

	public PopulationDensity getDensity() {
		return density;
	}

	public Map<Subspecies, SubspeciesSpawnRarity> getSpecies() {
		return species;
	}

	public String getDescription(boolean includeDeterminer) {
		StringBuilder sb = new StringBuilder();
		
		if(includeDeterminer) {
			sb.append(density.getName()+" ");
		}
		
		if(isPluralPopulation()) {
			sb.append(getType().getNamePlural());
		} else {
			sb.append(getType().getName());
		}
		
		return sb.toString();
	}
	
}
