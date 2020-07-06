package com.lilithsthrone.game.occupantManagement.slave;

import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.87
 * @version 0.3.6.5
 * @author Innoxia
 */
public enum SlavePermissionSetting {

	// General/Misc.:
	
	GENERAL_SILENCE("Silence", "Forbid this slave from talking.", false),
	GENERAL_CRAWLING("Crawling", "Forbid this slave from walking, forcing them to crawl around on all fours.", false),
	GENERAL_HOUSE_FREEDOM("House Freedom", "Grant this slave the freedom to walk around Lilaya's house in their free time.", false),
	GENERAL_OUTSIDE_FREEDOM("Outside Freedom", "Grant this slave the freedom to leave Lilaya's house in their free time.", false),
	
	// Behaviour:
	BEHAVIOUR_WHOLESOME("Wholesome", "Get this slave to act in a loving and wholesome manner around you. [style.italics(This will only take effect if they like you or are obedient.)]", false),
	BEHAVIOUR_PROFESSIONAL("Professional", "Get this slave to act in a professional manner when interacting with you. [style.italics(This will only take effect if they like you or are obedient.)]", false),
	BEHAVIOUR_STANDARD("Standard", "Do not give this slave any instructions as to how they should act around you.", true),
	BEHAVIOUR_SEDUCTIVE("Seductive", "Get this slave to act in a refined, seductive manner when interacting with you. [style.italics(This will only take effect if they like you or are obedient.)]", false),
	BEHAVIOUR_SLUTTY("Slutty", "Get this slave to act in a trashy, slutty manner when interacting with you. [style.italics(This will only take effect if they like you or are obedient.)]", false),

	// Sex:
	
	SEX_MASTURBATE("Masturbation", "Allow this slave to masturbate.", false),
	SEX_INITIATE_SLAVES("Initiate Sex", "Allow this slave to initiate sex with any other slave that has the 'Sex Toy' permission enabled.", false),
	SEX_INITIATE_PLAYER("Use You", "Allow this slave to use you for sexual relief. This will allow them to initiate sex with you at any time.", false),
	SEX_RECEIVE_SLAVES("Sex Toy", "Allow this slave to be used for sexual relief by any of your slaves with the 'Initiate Sex' permission enabled.", false),
	SEX_IMPREGNATED("Breeding Bitch", "Allow this slave to be impregnated during sexual events with any other slave that has the 'Slave Stud' permission enabled.", false),
	SEX_IMPREGNATE("Slave Stud", "Allow this slave to impregnate any other slave that has the 'Breeding Bitch' permission enabled during sexual events.", false),

	// Pregnancy:
	
	PREGNANCY_PROMISCUITY_PILLS("", "", false) {
		@Override
		public String getName() {
			return Util.capitaliseSentence(ItemType.PROMISCUITY_PILL.getName(false));
		}
		@Override
		public String getDescription() {
			return UtilText.parse("Keep this slave on [#ITEM_PROMISCUITY_PILL.getNamePlural(false)], greatly reducing both their fertility and virility.");
		}
	},
	PREGNANCY_NO_PILLS("No Pills", "Don't give this slave any sort of fertility modification pills, resulting in a natural chance of them getting pregnant.", true),
	PREGNANCY_VIXENS_VIRILITY("", "", false) {
		@Override
		public String getName() {
			return Util.capitaliseSentence(ItemType.VIXENS_VIRILITY.getName(false));
		}
		@Override
		public String getDescription() {
			return UtilText.parse("Keep this slave on [#ITEM_VIXENS_VIRILITY.getNamePlural(false)], greatly increasing both their fertility and virility.");
		}
	},

	// Diet/Body size:
	
	FOOD_DIET_EXTREME(Util.capitaliseSentence(BodySize.ZERO_SKINNY.getName(false)),
			"Severely limit the amount of food available to this slave, which will result in a large daily change to their body size,"
					+ " eventually making them <b style='color:"+BodySize.ZERO_SKINNY.getColour().toWebHexString()+";'>"+BodySize.ZERO_SKINNY.getName(false)+"</b>.",
			false),
	
	FOOD_DIET(Util.capitaliseSentence(BodySize.ONE_SLENDER.getName(false)),
			"Restrict the amount of food available to this slave, which will result in a daily change to their body size,"
					+ " eventually making them <b style='color:"+BodySize.ONE_SLENDER.getColour().toWebHexString()+";'>"+BodySize.ONE_SLENDER.getName(false)+"</b>.",
			false),
	
	FOOD_NORMAL(Util.capitaliseSentence(BodySize.TWO_AVERAGE.getName(false)),
			"Give this slave a healthy amount of food, which will result in a daily change to their body size,"
					+ " eventually making them <b style='color:"+BodySize.TWO_AVERAGE.getColour().toWebHexString()+";'>"+BodySize.TWO_AVERAGE.getName(false)+"</b>.",
			true),
	
	FOOD_PLUS(Util.capitaliseSentence(BodySize.THREE_LARGE.getName(false)),
			"Give this slave an extra meal every day, which will result in a daily change to their body size,"
					+ " eventually making them <b style='color:"+BodySize.THREE_LARGE.getColour().toWebHexString()+";'>"+BodySize.THREE_LARGE.getName(false)+"</b>.",
			false),
	
	FOOD_LAVISH(Util.capitaliseSentence(BodySize.FOUR_HUGE.getName(false)),
			"Make an abundance of food available to this slave, which will result in a large daily change to their body size,"
					+ " eventually making them <b style='color:"+BodySize.FOUR_HUGE.getColour().toWebHexString()+";'>"+BodySize.FOUR_HUGE.getName(false)+"</b>.",
			false),

	// Exercise/Muscle:
	
	EXERCISE_FORBIDDEN(Util.capitaliseSentence(Muscle.ZERO_SOFT.getName(false)),
			"Forbid this slave from performing any strenuous activities, which will result in their muscles getting a lot smaller every day,"
					+ " eventually making them <b style='color:"+Muscle.ZERO_SOFT.getColour().toWebHexString()+";'>"+Muscle.ZERO_SOFT.getName(false)+"</b>.",
			false),
	
	EXERCISE_REST(Util.capitaliseSentence(Muscle.ONE_LIGHTLY_MUSCLED.getName(false)),
			"Do not give this slave any exercise routine, which will result in a daily change to their muscle mass,"
					+ " eventually making them <b style='color:"+Muscle.ONE_LIGHTLY_MUSCLED.getColour().toWebHexString()+";'>"+Muscle.ONE_LIGHTLY_MUSCLED.getName(false)+"</b>.",
			false),
	
	EXERCISE_NORMAL(Util.capitaliseSentence(Muscle.TWO_TONED.getName(false)),
			"Set this slave to perform a healthy amount of exercise, which will result in a daily change to their muscle mass,"
					+ " eventually making them <b style='color:"+Muscle.TWO_TONED.getColour().toWebHexString()+";'>"+Muscle.TWO_TONED.getName(false)+"</b>.",
			true),
	
	EXERCISE_TRAINING(Util.capitaliseSentence(Muscle.THREE_MUSCULAR.getName(false)),
			"Give this slave a workout routine, which will result in a daily change to their muscle mass,"
					+ " eventually making them <b style='color:"+Muscle.THREE_MUSCULAR.getColour().toWebHexString()+";'>"+Muscle.THREE_MUSCULAR.getName(false)+"</b>.",
			false),
	
	EXERCISE_BODY_BUILDING(Util.capitaliseSentence(Muscle.FOUR_RIPPED.getName(false)),
			"Give this slave a strenuous exercise routine, which will result in their muscles getting a lot bigger every day,"
					+ " eventually making them <b style='color:"+Muscle.FOUR_RIPPED.getColour().toWebHexString()+";'>"+Muscle.FOUR_RIPPED.getName(false)+"</b>.",
			false),
	
	// Claenliness:
	
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
