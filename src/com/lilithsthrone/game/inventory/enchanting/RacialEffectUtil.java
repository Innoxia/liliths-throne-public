package com.lilithsthrone.game.inventory.enchanting;

/**
 * @since 0.1.7?
 * @version 0.1.89
 * @author Innoxia
 */
public abstract class RacialEffectUtil {
	
	private String description;
	private int change;
	private String measurement;
	
	public RacialEffectUtil(String description, int change, String measurement) {
		this.description = description;
		this.change = change;
		this.measurement = measurement;
	}

	public String getDescription() {
		return description;
	}
	
	public int getChange() {
		return change;
	}
	
	public String getDetailedEffects() {
		return measurement;
	}
	
	public String getChangeDescription() {
		return change+measurement;
	}
	
	public String getDescriptionPlusChangeDescription() {
		return description+(measurement.isEmpty()?"":" ("+(change>0?"+":"")+change+measurement+".)");
	}
	
	public abstract String applyEffect();

}
