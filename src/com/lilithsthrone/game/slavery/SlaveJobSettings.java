package com.lilithsthrone.game.slavery;

/**
 * @since 0.1.87
 * @version 0.1.87
 * @author Innoxia
 */
public enum SlaveJobSettings {
	
	TEST_SUBJECT_ALLOW_TRANSFORMATIONS_FEMALE("Allow Female Transformations", "Allow Lilaya to perform female-related transformations on this slave.", 100),
	TEST_SUBJECT_ALLOW_TRANSFORMATIONS_MALE("Allow Male Transformations", "Allow Lilaya to perform male-related transformations on this slave.", 100);
	
	private String name;
	private String description;
	private float incomeBonus;
	
	private SlaveJobSettings(String name, String description, float incomeBonus) {
		this.name = name;
		this.description = description;
		this.incomeBonus = incomeBonus;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public float getIncomeBonus() {
		return incomeBonus;
	}
	
	public String getExtraEffectsDescription() {
		return null;
	}
	
	public String applyExtraEffects() {
		return null;
	}
	
}
