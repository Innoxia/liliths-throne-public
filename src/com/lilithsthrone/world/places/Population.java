package com.lilithsthrone.world.places;

import java.util.Map;

import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesSpawnRarity;

/**
 * @since 0.2.12
 * @version 0.3.7
 * @author Innoxia
 */
public class Population {
	
	private boolean pluralPopulation;
	private PopulationType type;
	private PopulationDensity density;
	private Map<Subspecies, SubspeciesSpawnRarity> species;
	
	public Population(boolean pluralPopulation, PopulationType type, PopulationDensity density, Map<Subspecies, SubspeciesSpawnRarity> species) {
		this.pluralPopulation = pluralPopulation;
		this.type = type;
		this.density = density;
		this.species = species;
	}
	
	public boolean isPluralPopulation() {
		return pluralPopulation;
	}

	public PopulationType getType() {
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
		
//		switch(this.getDensity()) {
//			case OCCASIONAL:
//				if(isPluralPopulation()) {
//					if(includeDeterminer) {
//						sb.append("a ");
//					}
//					sb.append("tiny");
//				} else {
//					if(includeDeterminer) {
//						sb.append("an ");
//					}
//					sb.append("occasional");
//				}
//				break;
//			case COUPLE:
//				if(includeDeterminer) {
//					sb.append("a ");
//				}
//				if(isPluralPopulation()) {
//					sb.append("tiny");
//				} else {
//					sb.append("couple of");
//				}
//				break;
//			case DENSE:
//				if(includeDeterminer) {
//					sb.append("a ");
//				}
//				if(isPluralPopulation()) {
//					sb.append("large");
//				} else {
//					sb.append("large amount of");
//				}
//				break;
//			case FEW:
//				if(includeDeterminer) {
//					sb.append("a ");
//				}
//				if(isPluralPopulation()) {
//					sb.append("small");
//				} else {
//					sb.append("few");
//				}
//				break;
//			case NUMEROUS:
//				if(isPluralPopulation()) {
//					if(includeDeterminer) {
//						sb.append("a ");
//					}
//					sb.append("sizable");
//				} else {
//					sb.append("numerous");
//				}
//				break;
//			case SEVERAL:
//				if(isPluralPopulation()) {
//					if(includeDeterminer) {
//						sb.append("a ");
//					}
//					sb.append("modestly-sized");
//				} else {
//					sb.append("several");
//				}
//				break;
//			case SPARSE:
//				if(includeDeterminer) {
//					sb.append("a ");
//				}
//				if(isPluralPopulation()) {
//					sb.append("small");
//				} else {
//					sb.append("few");
//				}
//				break;
//		}
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
