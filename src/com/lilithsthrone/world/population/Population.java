package com.lilithsthrone.world.population;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesSpawnRarity;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.12
 * @version 0.4
 * @author Innoxia
 */
public class Population {
	
	private boolean pluralPopulation;
	private AbstractPopulationType type;
	private PopulationDensity density;
	private Map<AbstractSubspecies, SubspeciesSpawnRarity> species; //TODO refactor this into a list of AbstractSubspecies as the SubspeciesSpawnRarity is never used
	
	// For use when loaded from external files:
	
	protected boolean inclusiveTimeRange = true;
	protected boolean usingDaylightHours = false;
	protected int dayStartOverride = -1;
	protected int dayEndOverride = -1;
	
	private String conditional;
	private String subspeciesWorldTypeId;
	private String subspeciesPlaceTypeId;
	private List<String> subspeciesIdToAdd;
	private List<String> subspeciesIdToRemove;
	
	public Population(boolean pluralPopulation, AbstractPopulationType type, PopulationDensity density, Map<AbstractSubspecies, SubspeciesSpawnRarity> species) {
		this.pluralPopulation = pluralPopulation;
		this.type = type;
		this.density = density;
		this.species = species;
		
		this.conditional = null; // Only used for external file populations, which use setConditional()
	}
	
	public boolean isPluralPopulation() {
		return pluralPopulation;
	}

	public AbstractPopulationType getType() {
		return type;
	}

	private PopulationDensity getDensity() {
		return density;
	}

	public Map<AbstractSubspecies, SubspeciesSpawnRarity> getSpecies() {
		if(species==null) {
			if(subspeciesWorldTypeId!=null && !subspeciesWorldTypeId.isEmpty()) {
				List<AbstractSubspecies> remove = new ArrayList<>();
				if(subspeciesIdToRemove!=null) {
					for(String id : subspeciesIdToRemove) {
						remove.add(Subspecies.getSubspeciesFromId(id));
					}
				}
				species = Subspecies.getWorldSpecies(WorldType.getWorldTypeFromId(subspeciesWorldTypeId), PlaceType.getPlaceTypeFromId(subspeciesPlaceTypeId), true, true, remove);
				
			} else if(subspeciesIdToAdd!=null && !subspeciesIdToAdd.isEmpty()) { 
				species = new HashMap<>();
				for(String id : subspeciesIdToAdd) {
					species.put(Subspecies.getSubspeciesFromId(id), SubspeciesSpawnRarity.TEN);
				}
				
			} else {
				species = new HashMap<>();
			}
		}
		return species;
	}

	public String getDescription(boolean includeDeterminer) {
		StringBuilder sb = new StringBuilder();
		
		if(includeDeterminer) {
			sb.append(getDensity().getName()+" ");
		}
		
		if(isPluralPopulation()) {
			sb.append(getType().getNamePlural());
		} else {
			sb.append(getType().getName());
		}
		
		return sb.toString();
	}
	
	public String getConditional() {
		return conditional;
	}

	public void setConditional(String conditional) {
		this.conditional = conditional;
	}

	public boolean isAvailableFromConditional() {
		return conditional==null || conditional.isEmpty() || Boolean.valueOf(UtilText.parse(conditional).trim());
	}
	
	public boolean isAvailableFromCurrentTime() {
		int dayStartMinutes = 0;
		int dayEndMinutes = 24*60;
		
		if(usingDaylightHours) {
			dayStartMinutes = Main.game.getSunriseTimeInMinutes();
			dayEndMinutes = Main.game.getSunsetTimeInMinutes();
			
		} else if(dayEndOverride!=-1 || dayStartOverride!=-1) {
			dayStartMinutes = dayStartOverride!=-1?dayStartOverride:Main.game.getSunriseTimeInMinutes();
			dayEndMinutes = dayEndOverride!=-1?dayEndOverride:Main.game.getSunsetTimeInMinutes();
		}
		
		if(inclusiveTimeRange) {
			return Main.game.getDayMinutes()>=dayStartMinutes && Main.game.getDayMinutes()<=dayEndMinutes;
		} else {
			return Main.game.getDayMinutes()<dayStartMinutes || Main.game.getDayMinutes()>dayEndMinutes;
		}
	}

	public void setSubspeciesWorldTypeId(String subspeciesWorldTypeId) {
		this.subspeciesWorldTypeId = subspeciesWorldTypeId;
	}

	public void setSubspeciesPlaceTypeId(String subspeciesPlaceTypeId) {
		this.subspeciesPlaceTypeId = subspeciesPlaceTypeId;
	}

	public void setSubspeciesIdToAdd(List<String> subspeciesIdToAdd) {
		this.subspeciesIdToAdd = subspeciesIdToAdd;
	}

	public void setSubspeciesIdToRemove(List<String> subspeciesIdToRemove) {
		this.subspeciesIdToRemove = subspeciesIdToRemove;
	}

	public boolean isInclusiveTimeRange() {
		return inclusiveTimeRange;
	}

	public void setInclusiveTimeRange(boolean inclusiveTimeRange) {
		this.inclusiveTimeRange = inclusiveTimeRange;
	}

	public boolean isUsingDaylightHours() {
		return usingDaylightHours;
	}

	public void setUsingDaylightHours(boolean usingDaylightHours) {
		this.usingDaylightHours = usingDaylightHours;
	}

	public int getDayStartOverride() {
		return dayStartOverride;
	}

	public void setDayStartOverride(int dayStartOverride) {
		this.dayStartOverride = dayStartOverride;
	}

	public int getDayEndOverride() {
		return dayEndOverride;
	}

	public void setDayEndOverride(int dayEndOverride) {
		this.dayEndOverride = dayEndOverride;
	}
	
}
