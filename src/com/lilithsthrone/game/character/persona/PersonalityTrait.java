package com.lilithsthrone.game.character.persona;

import com.lilithsthrone.utils.Colour;

/***
 * @since 0.2.4
 * @version 0.2.4
 * @author Innoxia
 */
public enum PersonalityTrait {

	ADVENTUROUSNESS("Adventurousness",
			"This reflects the level of creativity, imagination, and curiosity in a person. It is an indicator of how willing a person is to try out new things or to take risks.",
			Colour.BASE_GOLD,
			"Cautious", "[npc.Name] is risk-averse, and unwilling to try out new things.",
			"Average", "[npc.Name] doesn't hold any particular aversion or willingness to try out new things.",
			"Curious", "[npc.Name] is willing to take risks in order to feed [npc.her] love of experiencing new things."),

	AGREEABLENESS("Agreeableness",
			"How compassionate, cooperative, and friendly someone is towards others. It is an indication of how trusting, helpful, and well-tempered someone is.",
			Colour.BASE_GREEN,
			"Selfish", "[npc.Name] treats others in an unfriendly, hostile manner, and rather than showing any signs of wanting to cooperate, [npc.she]'s far more likely to act in a competitive manner.",
			"Average", "[npc.Name] is friendly towards new people, but is also liable to drop any pretence of cooperation and compassion if that person turns out to be less-than-amicable.",
			"Trusting", "[npc.Name] is very trusting, and always tries [npc.her] best to be friendly towards other people."),
	
	CONSCIENTIOUSNESS("Conscientiousness",
			"How organised and willing to plan ahead a person is. It indicates the level of self-discipline a person has, and how seriously they treat their obligations to others.",
			Colour.BASE_BLUE_LIGHT,
			"Careless", "[npc.Name] doesn't really care for plans or commitments. [npc.She] prefers to stay flexible, and will often spontaneously decide to do things.",
			"Average", "[npc.Name] will plan ahead when [npc.she] feels that it's the right thing to do, but will also sometimes make impulsive decisions.",
			"Vigilant", "[npc.Name] always plans ahead, and never rushes into anything without first weighing the pros and cons."),

	// I wasn't sure whether to spell this as 'Extroversion' or 'Extraversion'.
	EXTROVERSION("Extroversion",
			"How sociable and confident a person feels when in the company of others.",
			Colour.BASE_PINK,
			"Introverted", "[npc.Name] is not very sociable, and feels awkward and shy when in the company of others.",
			"Average", "[npc.Name] enjoys the company of others, but [npc.she] doesn't seek to be life of the party.",
			"Extroverted", "[npc.Name] loves being in social situations, and always seeks to be the life of the party."),

	NEUROTICISM("Neuroticism",
			"How prone this character is to experiencing unpleasant emotions, such as anxiety, frustration, fear, and loneliness. Neurotic characters are often shy, lack emotional stability, and suffer from low control over their impulses.",
			Colour.BASE_PURPLE,
			"Confident", "[npc.Name] is very emotionally stable, and always feels calm and in control.",
			"Average", "[npc.Name] has [npc.her] emotions under control, for the most part.",
			"Neurotic", "[npc.Name] has a tendency to interpret situations in the worst possible manner, and often feels anxious, frustrated, and worried.");
	

	private String name;
	private String description;
	private Colour colour;
	
	private String nameLow;
	private String descriptionLow;
	private String nameAverage;
	private String descriptionAverage;
	private String nameHigh;
	private String descriptionHigh;
	
	private PersonalityTrait(String name, String description, Colour colour,
			String nameLow, String descriptionLow,
			String nameAverage, String descriptionAverage,
			String nameHigh, String descriptionHigh) {
		
		this.name = name;
		this.description = description;
		this.colour = colour;
		this.nameLow = nameLow;
		this.descriptionLow = descriptionLow;
		this.nameAverage = nameAverage;
		this.descriptionAverage = descriptionAverage;
		this.nameHigh = nameHigh;
		this.descriptionHigh = descriptionHigh;
	}

	public String getName() {
		return name;
	}
	
	public String getNameFromWeight(PersonalityWeight weight) {
		switch(weight) {
			case LOW:
				return this.getNameLow();
			case HIGH:
				return this.getNameHigh();
			default:
				return this.getNameAverage();
		}
	}

	public String getDescription() {
		return description;
	}
	
	public String getDescriptionFromWeight(PersonalityWeight weight) {
		switch(weight) {
			case LOW:
				return this.getDescriptionLow();
			case HIGH:
				return this.getDescriptionHigh();
			default:
				return this.getDescriptionAverage();
		}
	}
	
	public Colour getColour() {
		return colour;
	}
	
	public String getNameLow() {
		return nameLow;
	}

	public String getDescriptionLow() {
		return descriptionLow;
	}

	public String getNameAverage() {
		return nameAverage;
	}

	public String getDescriptionAverage() {
		return descriptionAverage;
	}

	public String getNameHigh() {
		return nameHigh;
	}

	public String getDescriptionHigh() {
		return descriptionHigh;
	}

}
