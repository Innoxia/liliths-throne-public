package com.lilithsthrone.game.occupantManagement;

/**
 * @since 0.1.87
 * @version 0.2.5
 * @author Innoxia
 */
public enum SlavePermissionSetting {
	
	GENERAL_SILENCE("Silence", "Forbid this slave from talking.", false),
	GENERAL_CRAWLING("Crawling", "Forbid this slave from walking, forcing them to crawl around on all fours.", false),
	GENERAL_HOUSE_FREEDOM("House Freedom", "Grant this slave the freedom to walk around Lilaya's house in their free time.", false),
	GENERAL_OUTSIDE_FREEDOM("Outside Freedom", "Grant this slave the freedom to leave Lilaya's house in their free time.", false),
	
	SEX_MASTURBATE("Masturbation", "Allow this slave to masturbate.", false),
	SEX_INITIATE_SLAVES("Initiate Sex", "Allow this slave to initiate sex with any other slave that has the 'Sex Toy' permission enabled.", false),
	SEX_INITIATE_PLAYER("Use You", "Allow this slave to use you for sexual relief. This will allow them to initiate sex with you at any time.", false),
	SEX_RECEIVE_SLAVES("Sex Toy", "Allow this slave to be used for sexual relief by any of your slaves with the 'Initiate Sex' enabled.", false),
	SEX_IMPREGNATED("Breeding Bitch", "Allow this slave to be impregnated during sexual events with any other slave that has the 'Slave Stud' permission enabled.", false),
	SEX_IMPREGNATE("Slave Stud", "Allow this slave to impregnate any other slave that has the 'Breeding Bitch' permission enabled during sexual events.", false),
	
	PREGNANCY_PROMISCUITY_PILLS("Promiscuity Pills", "Keep this slave on Promiscuity Pills, ensuring that they won't get pregnant.", false),
	PREGNANCY_NO_PILLS("No Pills", "Don't give this slave any sort of fertility modification pills, resulting in a natural chance of them getting pregnant.", true),
	PREGNANCY_VIXENS_VIRILITY("Vixen's Virility", "Keep this slave on Vixen's Virility, greatly increasing the chances of them getting pregnant.", false),

	FOOD_DIET_EXTREME("One meal", "Severely limit the amount of food available to this slave, which will result in a large daily reduction to their body size.", false),
	FOOD_DIET("Two meals", "Restrict the amount of food available to this slave, which will result in a daily reduction to their body size.", false),
	FOOD_NORMAL("Three meals", "Give this slave the amount of food required to maintain their current body size.", true),
	FOOD_PLUS("Four meals", "Give this slave an extra meal every day, which will result in their body size increasing a little each day.", false),
	FOOD_LAVISH("Five meals", "Make an abundance of food available to this slave, which will result in a large daily increase to their body size.", false),

	EXERCISE_FORBIDDEN("Exertion Forbidden", "Forbid this slave from performing any strenuous activities, which will result in their muscles getting a lot smaller every day.", false),
	EXERCISE_REST("No Routine", "Do not give this slave any exercise routine, which will result in their muscles getting a little smaller every day.", false),
	EXERCISE_NORMAL("Maintain Muscle", "Set this slave to perform the right amount of exercise needed in order to maintain their muscles.", true),
	EXERCISE_TRAINING("Workout Routine", "Give this slave a workout routine, which will result in their muscles getting a little bigger every day.", false),
	EXERCISE_BODY_BUILDING("Bodybuilding", "Give this slave a strenuous exercise routine, which will result in their muscles getting a lot bigger every day.", false),
	
	CLEANLINESS_WASH_CLOTHES("Wash Clothing", "Tell this slave to keep their clothing washed and clean.", true),
	CLEANLINESS_WASH_BODY("Wash Body", "Tell this slave to keep their body washed and clean, which will keep their orifices free of creampies.", true)
	;
	
	private String name;
	private String description;
	private boolean defaultValue;
	
	private SlavePermissionSetting(String name, String description, boolean defaultValue) {
		this.name = name;
		this.description = description;
		this.defaultValue = defaultValue;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean isDefaultValue() {
		return defaultValue;
	}
	
}
