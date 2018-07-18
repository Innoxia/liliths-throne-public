package com.lilithsthrone.game.character.persona;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Colour;

/***
 * @since 0.2.4
 * @version 0.2.4
 * @author Innoxia
 */
public enum PersonalityTrait {

	/**This reflects the level of creativity, imagination, and curiosity in a person. It is an indicator of how willing a person is to try out new things or to take risks.<br/>
	 * Cautious -> Average -> Curious*/
	ADVENTUROUSNESS("Adventurousness",
			"This reflects the level of creativity, imagination, and curiosity in a person. It is an indicator of how willing a person is to try out new things or to take risks.",
			Colour.BASE_GOLD,
			"Cautious", "[npc.Name] is risk-averse, and unwilling to try out new things.",
			"Average", "[npc.Name] doesn't hold any particular aversion or willingness to try out new things.",
			"Curious", "[npc.Name] is willing to take risks in order to feed [npc.her] love of experiencing new things.",
			"Cautious", "You are risk-averse, and unwilling to try out new things.",
			"Average", "You don't hold any particular aversion or willingness to try out new things.",
			"Curious", "You are willing to take risks in order to feed your love of experiencing new things."),

	/**How compassionate, cooperative, and friendly someone is towards others. It is an indication of how trusting, helpful, and well-tempered someone is.<br/>
	 * Selfish -> Average -> Trusting*/
	AGREEABLENESS("Agreeableness",
			"How compassionate, cooperative, and friendly someone is towards others. It is an indication of how trusting, helpful, and well-tempered someone is.",
			Colour.BASE_GREEN,
			"Selfish", "[npc.Name] treats others in an unfriendly, hostile manner, and rather than showing any signs of wanting to cooperate, [npc.sheIs] far more likely to act in a competitive manner.",
			"Average", "[npc.Name] is friendly towards new people, but is also liable to drop any pretence of cooperation and compassion if that person turns out to be less-than-amicable.",
			"Trusting", "[npc.Name] is very trusting, and always tries [npc.her] best to be friendly towards other people.",
			"Selfish", "You treat others in an unfriendly, hostile manner, and rather than showing any signs of wanting to cooperate, you're far more likely to act in a competitive manner.",
			"Average", "You are friendly towards new people, but are also liable to drop any pretence of cooperation and compassion if that person turns out to be less-than-amicable.",
			"Trusting", "You are very trusting, and always try your best to be friendly towards other people."),
	
	/**How organised and willing to plan ahead a person is. It indicates the level of self-discipline a person has, and how seriously they treat their obligations to others.<br/>
	 * Careless -> Average -> Vigilant*/
	CONSCIENTIOUSNESS("Conscientiousness",
			"How organised and willing to plan ahead a person is. It indicates the level of self-discipline a person has, and how seriously they treat their obligations to others.",
			Colour.BASE_BLUE_LIGHT,
			"Careless", "[npc.Name] doesn't really care for plans or commitments. [npc.She] prefers to stay flexible, and will often spontaneously decide to do things.",
			"Average", "[npc.Name] will plan ahead when [npc.she] feels that it's the right thing to do, but will also sometimes make impulsive decisions.",
			"Vigilant", "[npc.Name] always plans ahead, and never rushes into anything without first weighing the pros and cons.",
			"Careless", "You don't really care for plans or commitments. You prefer to stay flexible, and will often spontaneously decide to do things.",
			"Average", "You will plan ahead when you feel that it's the right thing to do, but will also sometimes make impulsive decisions.",
			"Vigilant", "You always plan ahead, and never rush into anything without first weighing the pros and cons."),

	// I wasn't sure whether to spell this as 'Extroversion' or 'Extraversion'.
	/**How sociable and confident a person feels when in the company of others.<br/>
	 * Introverted -> Average -> Extroverted*/
	EXTROVERSION("Extroversion",
			"How sociable and confident a person feels when in the company of others.",
			Colour.BASE_PINK,
			"Introverted", "[npc.Name] is not very sociable, and feels awkward and shy when in the company of others.",
			"Average", "[npc.Name] enjoys the company of others, but [npc.she] doesn't seek to be the life of the party.",
			"Extroverted", "[npc.Name] loves being in social situations, and always seeks to be the life of the party.",
			"Introverted", "You are not very sociable, and feel awkward and shy when in the company of others.",
			"Average", "You enjoy the company of others, but you don't seek to be the life of the party.",
			"Extroverted", "You love being in social situations, and always seeks to be the life of the party."),

	/**How prone this character is to experiencing unpleasant emotions, such as anxiety, frustration, fear, and loneliness. Neurotic characters are often shy, lack emotional stability, and suffer from low control over their impulses.<br/>
	 * Confident -> Average -> Neurotic*/
	NEUROTICISM("Neuroticism",
			"How prone this character is to experiencing unpleasant emotions, such as anxiety, frustration, fear, and loneliness. Neurotic characters are often shy, lack emotional stability, and suffer from low control over their impulses.",
			Colour.BASE_PURPLE,
			"Confident", "[npc.Name] is very emotionally stable, and always feels calm and in control.",
			"Average", "[npc.Name] has [npc.her] emotions under control, for the most part.",
			"Neurotic", "[npc.Name] has a tendency to interpret situations in the worst possible manner, and often feels anxious, frustrated, and worried.",
			"Confident", "You are very emotionally stable, and always feels calm and in control.",
			"Average", "You have your emotions under control, for the most part.",
			"Neurotic", "You have a tendency to interpret situations in the worst possible manner, and often feel anxious, frustrated, and worried.");
	

	private String name;
	private String description;
	private Colour colour;
	
	private String nameLow;
	private String descriptionLow;
	private String nameAverage;
	private String descriptionAverage;
	private String nameHigh;
	private String descriptionHigh;

	private String nameLowPlayer;
	private String descriptionLowPlayer;
	private String nameAveragePlayer;
	private String descriptionAveragePlayer;
	private String nameHighPlayer;
	private String descriptionHighPlayer;
	
	private PersonalityTrait(String name, String description, Colour colour,
			String nameLow, String descriptionLow,
			String nameAverage, String descriptionAverage,
			String nameHigh, String descriptionHigh,
			String nameLowPlayer, String descriptionLowPlayer,
			String nameAveragePlayer, String descriptionAveragePlayer,
			String nameHighPlayer, String descriptionHighPlayer) {
		
		this.name = name;
		this.description = description;
		this.colour = colour;

		this.nameLow = nameLow;
		this.descriptionLow = descriptionLow;
		this.nameAverage = nameAverage;
		this.descriptionAverage = descriptionAverage;
		this.nameHigh = nameHigh;
		this.descriptionHigh = descriptionHigh;

		this.nameLowPlayer = nameLowPlayer;
		this.descriptionLowPlayer = descriptionLowPlayer;
		this.nameAveragePlayer = nameAveragePlayer;
		this.descriptionAveragePlayer = descriptionAveragePlayer;
		this.nameHighPlayer = nameHighPlayer;
		this.descriptionHighPlayer = descriptionHighPlayer;
	}

	public String getName() {
		return name;
	}
	
	public String getNameFromWeight(GameCharacter character, PersonalityWeight weight) {
		switch(weight) {
			case LOW:
				return this.getNameLow(character);
			case HIGH:
				return this.getNameHigh(character);
			default:
				return this.getNameAverage(character);
		}
	}

	public String getDescription() {
		return description;
	}
	
	public String getDescriptionFromWeight(GameCharacter character, PersonalityWeight weight) {
		switch(weight) {
			case LOW:
				return this.getDescriptionLow(character);
			case HIGH:
				return this.getDescriptionHigh(character);
			default:
				return this.getDescriptionAverage(character);
		}
	}
	
	public Colour getColour() {
		return colour;
	}
	
	public String getNameLow(GameCharacter character) {
		if(character.isPlayer()) {
			return nameLowPlayer;
		} else {
			return UtilText.parse(character, nameLow);
		}
	}

	public String getDescriptionLow(GameCharacter character) {
		if(character.isPlayer()) {
			return descriptionLowPlayer;
		} else {
			return UtilText.parse(character, descriptionLow);
		}
	}

	public String getNameAverage(GameCharacter character) {
		if(character.isPlayer()) {
			return nameAveragePlayer;
		} else {
			return UtilText.parse(character, nameAverage);
		}
	}

	public String getDescriptionAverage(GameCharacter character) {
		if(character.isPlayer()) {
			return descriptionAveragePlayer;
		} else {
			return UtilText.parse(character, descriptionAverage);
		}
	}

	public String getNameHigh(GameCharacter character) {
		if(character.isPlayer()) {
			return nameHighPlayer;
		} else {
			return UtilText.parse(character, nameHigh);
		}
	}

	public String getDescriptionHigh(GameCharacter character) {
		if(character.isPlayer()) {
			return descriptionHighPlayer;
		} else {
			return UtilText.parse(character, descriptionHigh);
		}
	}

}
