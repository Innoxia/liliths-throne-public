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
	
	public String getDescription(boolean includeDeterminer) {
		StringBuilder sb = new StringBuilder();
		
		switch(this.getDensity()) {
			case COUPLE:
				if(includeDeterminer) {
					sb.append("a ");
				}
				if(getType().isPlural()) {
					sb.append("tiny");
				} else {
					sb.append("couple of");
				}
				break;
			case DENSE:
				if(includeDeterminer) {
					sb.append("a ");
				}
				if(getType().isPlural()) {
					sb.append("large");
				} else {
					sb.append("large amount of");
				}
				break;
			case FEW:
				if(includeDeterminer) {
					sb.append("a ");
				}
				if(getType().isPlural()) {
					sb.append("small");
				} else {
					sb.append("few");
				}
				break;
			case NUMEROUS:
				if(getType().isPlural()) {
					if(includeDeterminer) {
						sb.append("a ");
					}
					sb.append("sizable");
				} else {
					sb.append("numerous");
				}
				break;
			case SEVERAL:
				if(getType().isPlural()) {
					if(includeDeterminer) {
						sb.append("a ");
					}
					sb.append("modestly-sized");
				} else {
					sb.append("several");
				}
				break;
			case SPARSE:
				if(includeDeterminer) {
					sb.append("a ");
				}
				if(getType().isPlural()) {
					sb.append("small");
				} else {
					sb.append("few");
				}
				break;
		}
		sb.append(" "+getType().getName());
		return sb.toString();
	}
	
}
