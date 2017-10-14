package com.lilithsthrone.game.slavery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.87
 * @version 0.1.87
 * @author Innoxia
 */
public enum SlaveJob {
	
	/*
		restaurant
			w&m
		bar
			sleazy (obedience not counted, but income is less)
			allow groping
		brothel
			anal
			oral
			vaginal
		public use
			anal
			oral
			vaginal
		milking
			breasts
			cock
			girlcum
	 */
	
	IDLE("Idle", "Idle", "Do not assign any job to this slave.", -100, 0, 0, 0, 0, 0, null, null),

	TEST_SUBJECT("Test Subject", "Test Subject", "Allow Lilaya to use this slave as a test subject for her experiments.", -100, -2f, 0, 10, 0, 0,
			Util.newArrayListOfValues(
					new ListValue<>(SlaveJobSettings.TEST_SUBJECT_ALLOW_TRANSFORMATIONS_FEMALE),
					new ListValue<>(SlaveJobSettings.TEST_SUBJECT_ALLOW_TRANSFORMATIONS_MALE)),
			null),
	
	LIBRARY("Librarian", "Librarian", "Assign this slave to work in Lilaya's library.", ObedienceLevel.NEGATIVE_TWO_UNRULY.getMinimumValue(), 0.25f, 0, 2, 1, 0, null, null),
	
	KITCHEN("Cook", "Cook", "Assign this slave to work in Lilaya's kitchen as a cook.", ObedienceLevel.NEGATIVE_ONE_DISOBEDIENT.getMinimumValue(), 0.25f, 0, 4, 1, 0, null, null),
	
	CLEANING("Maid", "Manservant", "Assign this slave to help keep the house clean, deal with visitors, and perform all sorts of menial housework.", ObedienceLevel.ZERO_FREE_WILLED.getMinimumValue(), 0.25f, 0, 6, 1, 0, null, null),
	
	LAB_ASSISTANT("Lab Assistant", "Lab Assistant", "Assign this slave to help Lilaya in her lab.", ObedienceLevel.POSITIVE_TWO_OBEDIENT.getMinimumValue(), 0.25f, 0, 8, 1, 0, null, null),
	
	;
	
	private String nameFeminine;
	private String nameMasculine;
	private String description;
	private int requiredObedience;
	private float obedienceGain;
	private float affectionGain;
	private int income;
	private float obedienceIncomeModifier;
	private float affectionIncomeModifier;
	private List<SlaveJobSettings> mutualSettings;
	private Map<String, List<SlaveJobSettings>> mutuallyExclusiveSettings;
	
	private SlaveJob(String nameFeminine, String nameMasculine, String description, int requiredObedience, float obedienceGain, float affectionGain, int income, float obedienceIncomeModifier, float affectionIncomeModifier, List<SlaveJobSettings> mutualSettings,
			Map<String, List<SlaveJobSettings>> mutuallyExclusiveSettings) {
		this.nameFeminine= nameFeminine;
		this.nameMasculine = nameMasculine;
		this.description = description;
		this.requiredObedience = requiredObedience;
		this.obedienceGain = obedienceGain;
		this.affectionGain = affectionGain;
		this.income = income;
		this.obedienceIncomeModifier = obedienceIncomeModifier;
		this.affectionIncomeModifier = affectionIncomeModifier;
		
		if(mutualSettings == null) {
			this.mutualSettings = new ArrayList<>();
		} else {
			this.mutualSettings = mutualSettings;
		}
		
		if(mutuallyExclusiveSettings == null) {
			this.mutuallyExclusiveSettings = new HashMap<>();
		} else {
			this.mutuallyExclusiveSettings = mutuallyExclusiveSettings;
		}
	}

	public String getNameFeminine() {
		return nameFeminine;
	}
	
	public String getNameMasculine() {
		return nameMasculine;
	}

	public String getDescription() {
		return description;
	}

	public int getRequiredObedience() {
		return requiredObedience;
	}

	public float getObedienceGain() {
		return obedienceGain;
	}

	public float getAffectionGain() {
		return affectionGain;
	}

	public int getIncome() {
		return income;
	}

	public float getObedienceIncomeModifier() {
		return obedienceIncomeModifier;
	}

	public float getAffectionIncomeModifier() {
		return affectionIncomeModifier;
	}

	public List<SlaveJobSettings> getMutualSettings() {
		return mutualSettings;
	}

	public Map<String, List<SlaveJobSettings>> getMutuallyExclusiveSettings() {
		return mutuallyExclusiveSettings;
	}
	
}
