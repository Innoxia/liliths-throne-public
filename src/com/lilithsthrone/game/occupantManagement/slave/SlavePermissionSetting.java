package com.lilithsthrone.game.occupantManagement.slave;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.87
 * @version 0.3.9.2
 * @author Innoxia
 */
public enum SlavePermissionSetting {

	// General/Misc.:
	
	GENERAL_SILENCE(false, "Silence", "Forbid this slave from talking."),
	GENERAL_CRAWLING(false, "Crawling", "Forbid this slave from walking, forcing them to crawl around on all fours."),
	GENERAL_HOUSE_FREEDOM(false, "House Freedom", "Grant this slave the freedom to walk around Lilaya's house in their free time."),
	GENERAL_OUTSIDE_FREEDOM(false, "Outside Freedom", "Grant this slave the freedom to leave Lilaya's house in their free time."),
	
	
	// Behaviour:
	
	BEHAVIOUR_WHOLESOME(false, "Wholesome", "Get this slave to act in a loving and wholesome manner around you. [style.italics(This will only take effect if they like you or are obedient.)]"),
	BEHAVIOUR_PROFESSIONAL(false, "Professional", "Get this slave to act in a professional manner when interacting with you. [style.italics(This will only take effect if they like you or are obedient.)]"),
	BEHAVIOUR_STANDARD(true, "Standard", "Do not give this slave any instructions as to how they should act around you."),
	BEHAVIOUR_SEDUCTIVE(false, "Seductive", "Get this slave to act in a refined, seductive manner when interacting with you. [style.italics(This will only take effect if they like you or are obedient.)]"),
	BEHAVIOUR_SLUTTY(false, "Slutty", "Get this slave to act in a trashy, slutty manner when interacting with you. [style.italics(This will only take effect if they like you or are obedient.)]"),

	
	// Sex:
	
	SEX_MASTURBATE(false, "Masturbation", "Allow this slave to masturbate."),
	SEX_INITIATE_SLAVES(false, "Initiate Sex", "Allow this slave to initiate sex with any other slave that has the 'Sex Toy' permission enabled."),
	SEX_INITIATE_PLAYER(false, "Use You", "Allow this slave to use you for sexual relief. This will allow them to initiate sex with you at any time."),
	SEX_RECEIVE_SLAVES(false, "Sex Toy", "Allow this slave to be used for sexual relief by any of your slaves with the 'Initiate Sex' permission enabled."),
	SEX_SAVE_VIRGINITY(true, "Save Virginity", "Do not let any other slaves take this slave's virginity during sex."),
	SEX_IMPREGNATED(false, "Breeding Bitch", "Allow this slave to be impregnated during sexual events with any other slave that has the 'Slave Stud' permission enabled.") {
		@Override
		public boolean isAvailableForCharacter(GameCharacter character) {
			return !character.isDoll();
		}	
	},
	SEX_IMPREGNATE(false, "Slave Stud", "Allow this slave to impregnate any other slave that has the 'Breeding Bitch' permission enabled during sexual events.") {
		@Override
		public boolean isAvailableForCharacter(GameCharacter character) {
			return !character.isDoll();
		}	
	},

	
	// Pills:
	
	PILLS_PROMISCUITY_PILLS(false, "", "") {
		@Override
		public String getName() {
			return Util.capitaliseSentence(ItemType.getItemTypeFromId("innoxia_pills_sterility").getName(false));
		}
		@Override
		public String getDescription() {
			return UtilText.parse("Keep this slave on [#ITEM_innoxia_pills_sterility.getNamePlural(false)], greatly reducing both their fertility and virility.");
		}
	},
	
	PILLS_NO_PILLS(true, "No Pills", "Don't give this slave any sort of fertility modification pills, resulting in a natural chance of them getting pregnant."),
	
	PILLS_VIXENS_VIRILITY(false, "", "") {
		@Override
		public String getName() {
			return Util.capitaliseSentence(ItemType.getItemTypeFromId("innoxia_pills_fertility").getName(false));
		}
		@Override
		public String getDescription() {
			return UtilText.parse("Keep this slave on [#ITEM_innoxia_pills_fertility.getNamePlural(false)], greatly increasing both their fertility and virility.");
		}
	},

	PILLS_BROODMOTHER(false, "", "") {
		@Override
		public String getName() {
			return Util.capitaliseSentence(ItemType.getItemTypeFromId("innoxia_pills_broodmother").getName(false));
		}
		@Override
		public String getDescription() {
			return UtilText.parse("Keep this slave on [#ITEM_innoxia_pills_broodmother.getNamePlural(false)], massively increasing both their fertility and virility and [style.colourExcellent(doubling)] how many offspring they conceive.");
		}
	},
	
	
	// Pregnancy:

	PREGNANCY_MOTHERS_MILK(false, "Mother's Milk", "Order this slave to regularly consume Mother's Milk while pregnant, which will result in them completing their pregnancy within a few hours of becoming visibly pregnant."),
	PREGNANCY_ALLOW_BIRTHING(true, "Allow Birthing", "Allow this slave to go to Lilaya's birthing room to give birth whenever they need to."),
	PREGNANCY_ALLOW_EGG_LAYING(true, "Allow Egg Laying", "Allow this slave to go to Lilaya's birthing room to lay any eggs which have been implanted in them whenever they need to."),
	
	
	// Diet/Body size:
	
	FOOD_DIET_EXTREME(false,
			Util.capitaliseSentence(BodySize.ZERO_SKINNY.getName(false)),
			"Severely limit the amount of food available to this slave, which will result in a large daily change to their body size,"
					+ " eventually making them <b style='color:"+BodySize.ZERO_SKINNY.getColour().toWebHexString()+";'>"+BodySize.ZERO_SKINNY.getName(false)+"</b>."),
	
	FOOD_DIET(false,
			Util.capitaliseSentence(BodySize.ONE_SLENDER.getName(false)),
			"Restrict the amount of food available to this slave, which will result in a daily change to their body size,"
					+ " eventually making them <b style='color:"+BodySize.ONE_SLENDER.getColour().toWebHexString()+";'>"+BodySize.ONE_SLENDER.getName(false)+"</b>."),
	
	FOOD_NORMAL(true,
			Util.capitaliseSentence(BodySize.TWO_AVERAGE.getName(false)),
			"Give this slave a healthy amount of food, which will result in a daily change to their body size,"
					+ " eventually making them <b style='color:"+BodySize.TWO_AVERAGE.getColour().toWebHexString()+";'>"+BodySize.TWO_AVERAGE.getName(false)+"</b>."),
	
	FOOD_PLUS(false,
			Util.capitaliseSentence(BodySize.THREE_LARGE.getName(false)),
			"Give this slave an extra meal every day, which will result in a daily change to their body size,"
					+ " eventually making them <b style='color:"+BodySize.THREE_LARGE.getColour().toWebHexString()+";'>"+BodySize.THREE_LARGE.getName(false)+"</b>."),
	
	FOOD_LAVISH(false,
			Util.capitaliseSentence(BodySize.FOUR_HUGE.getName(false)),
			"Make an abundance of food available to this slave, which will result in a large daily change to their body size,"
					+ " eventually making them <b style='color:"+BodySize.FOUR_HUGE.getColour().toWebHexString()+";'>"+BodySize.FOUR_HUGE.getName(false)+"</b>."),

	
	// Exercise/Muscle:
	
	EXERCISE_FORBIDDEN(false,
			Util.capitaliseSentence(Muscle.ZERO_SOFT.getName(false)),
			"Forbid this slave from performing any strenuous activities, which will result in their muscles getting a lot smaller every day,"
					+ " eventually making them <b style='color:"+Muscle.ZERO_SOFT.getColour().toWebHexString()+";'>"+Muscle.ZERO_SOFT.getName(false)+"</b>."),
	
	EXERCISE_REST(false,
			Util.capitaliseSentence(Muscle.ONE_LIGHTLY_MUSCLED.getName(false)),
			"Do not give this slave any exercise routine, which will result in a daily change to their muscle mass,"
					+ " eventually making them <b style='color:"+Muscle.ONE_LIGHTLY_MUSCLED.getColour().toWebHexString()+";'>"+Muscle.ONE_LIGHTLY_MUSCLED.getName(false)+"</b>."),
	
	EXERCISE_NORMAL(true,
			Util.capitaliseSentence(Muscle.TWO_TONED.getName(false)),
			"Set this slave to perform a healthy amount of exercise, which will result in a daily change to their muscle mass,"
					+ " eventually making them <b style='color:"+Muscle.TWO_TONED.getColour().toWebHexString()+";'>"+Muscle.TWO_TONED.getName(false)+"</b>."),
	
	EXERCISE_TRAINING(false,
			Util.capitaliseSentence(Muscle.THREE_MUSCULAR.getName(false)),
			"Give this slave a workout routine, which will result in a daily change to their muscle mass,"
					+ " eventually making them <b style='color:"+Muscle.THREE_MUSCULAR.getColour().toWebHexString()+";'>"+Muscle.THREE_MUSCULAR.getName(false)+"</b>."),
	
	EXERCISE_BODY_BUILDING(false,
			Util.capitaliseSentence(Muscle.FOUR_RIPPED.getName(false)),
			"Give this slave a strenuous exercise routine, which will result in their muscles getting a lot bigger every day,"
					+ " eventually making them <b style='color:"+Muscle.FOUR_RIPPED.getColour().toWebHexString()+";'>"+Muscle.FOUR_RIPPED.getName(false)+"</b>."),
	
	
	// Claenliness:
	
	CLEANLINESS_WASH_CLOTHES(true, "Wash Clothing", "Tell this slave to keep their clothing washed and clean."),
	CLEANLINESS_WASH_BODY(true, "Wash Body", "Tell this slave to keep their body washed and clean, which will keep their orifices free of creampies."),
	
	
	// Sleeping:
	SLEEPING_DEFAULT(true, "Sleep Whenever", "Tell this slave to sleep whenever they like, which will be during the night for diurnal races and during the day for nocturnal races."),
	SLEEPING_NIGHT(false, "Sleep At Night", "Tell this slave to sleep during the night. This will have neither a positive nor negative effect on them."),
	SLEEPING_DAY(false, "Sleep During Day", "Tell this slave to sleep during the day. This will have neither a positive nor negative effect on them.")
	
	;
	
	private String name;
	private String description;
	private boolean defaultValue;
	
	private SlavePermissionSetting(boolean defaultValue, String name, String description) {
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
	
	public boolean isAvailableForCharacter(GameCharacter character) {
		return true;
	}
	
}
