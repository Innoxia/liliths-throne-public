package com.lilithsthrone.game.character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.0
 * @version 0.1.86
 * @author Innoxia
 */
public enum History {
	
	/*
	 * Sociable:
	Prostitute
	Stripper
	Barmaid/tender
	Massage therapist
	Waitress
	Receptionist
	Beautician
	Musician/singer
	Fitness trainer
	
Dominant:
	Mugger
	Gang leader
	Construction worker
	Mechanic
	Teacher
	Enforcer (low rank)
	Enforcer (high rank)
	
Compliant:
	Con-artist
	Librarian
	University student
	Writer
	Engineer
	Architect
	Lawyer
	Doctor
	Arcane researcher
	
Calm:
	Loafer
	Maid/Butler
	Receptionist
	Shop assistant
	Painter
	Nurse
	Chef
	Athlete
	Model
	 */
	
	// Unique:

	REINDEER_OVERSEER(false, true, "overseer", "-", null),
	
	// Partner histories:

	UNEMPLOYED(false, true, "unemployed", "-", null),
	
	// Sociable personality:
	
	PROSTITUTE(false, true, "prostitute", "-", null),
	STRIPPER(false, true, "stripper", "-", null),
	BAR_TENDER(false, true, "barmaid", "-", null),
	MASSAGE_THERAPIST(false, true, "massage therapist", "-", null),
	WAITRESS(false, true, "waitress", "-", null),
	BEAUTICIAN(false, true, "beautician", "-", null),
//	MUSICIAN(false, true, "musician", "-", null),
	FITNESS_INSTRUCTOR(false, true, "fitness instructor", "-", null),
	
	// Commanding personality:
	
	MUGGER(false, true, "mugger", "-", null),
	CONSTRUCTION_WORKER(false, true, "construction worker", "-", null),
	MECHANIC(false, true, "mechanic", "-", null),
//	TEACHER(false, true, "teacher", "-", null),
	ENFORCER(false, true, "enforcer", "-", null),
	HIGH_RANKING_ENFORCER(false, true, "enforcer chief", "-", null),
	
	// Analytical personality:

	CON_ARTIST(false, true, "con-artist", "-", null),
	LIBRARIAN(false, true, "librarian", "-", null),
	UNIVERSITY_STUDENT(false, true, "university student", "-", null),
//	WRITER(false, true, "writer", "-", null),
//	ENGINEER(false, true, "engineer", "-", null),
//	ARCHITECT(false, true, "architect", "-", null),
	DOCTOR(false, true, "doctor", "-", null),
	ARCANE_RESEARCHER(false, true, "arcane researcher", "-", null),
	
	// Calm personality:

//	MAID(false, true, "maid", "-", null),
	RECEPTIONIST(false, true, "receptionist", "-", null),
	SHOP_ASSISTANT(false, true, "shop assistant", "-", null),
	ARTIST(false, true, "artist", "-", null),
	NURSE(false, true, "nurse", "-", null),
//	CHEF(false, true, "chef", "-", null),
//	ATHLETE(false, true, "athlete", "-", null),
	MODEL(false, true, "model", "-", null),
	
	
	
	// Player histories:
	NONE(false,
			false,
			"None",
			"You shouldn't be seeing this. x_x",
			null),
	
	OFFICE_WORKER(true,
			false,
			"office worker",
			"You work in a local office, handling paperwork, answering phonecalls and emails, and generally doing a little bit of everything.",
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.STRENGTH, 2),
					new Value<Attribute, Integer>(Attribute.INTELLIGENCE, 2),
					new Value<Attribute, Integer>(Attribute.FITNESS, 2))),
	
	STUDENT(true,
			false,
			"student",
			"You're a student at the city's university as well, although you're in your first year, and haven't quite decided what to take as your major yet.",
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.INTELLIGENCE, 5))),

	MUSICIAN(true,
			false,
			"musician",
			"You're a musician, and as well as being able to play a wide variety of instruments, you are also a very good singer.",
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.INTELLIGENCE, 3),
					new Value<Attribute, Integer>(Attribute.FITNESS, 3))),
	
	TEACHER(true,
			false,
			"teacher",
			"You're a teacher, and have been working at a local school for a few years.",
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.INTELLIGENCE, 5),
					new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, 10))),
	
	WRITER(true,
			false,
			"writer",
			"You're a writer, and have been working on your latest novel for the last few months.",
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.INTELLIGENCE, 5),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_MANA, 10))),
	
	CHEF(true,
			false,
			"chef",
			"You're the head chef at a local restaurant.",
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.FITNESS, 2),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_STAMINA, 10),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_FIRE, 10),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_POISON, 10))),
	
	SOLDIER(true,
			false,
			"soldier",
			"You're a soldier, and are currently making the most of your leave.",
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.STRENGTH, 5),
					new Value<Attribute, Integer>(Attribute.FITNESS, 5),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_PHYSICAL, 10),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_MANA, 10),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_STAMINA, 10),
					new Value<Attribute, Integer>(Attribute.HEALTH_MAXIMUM, 20))),

	ATHLETE(true,
			false,
			"athlete",
			"You're an athlete, and are currently training for your next big event.",
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.STRENGTH, 5),
					new Value<Attribute, Integer>(Attribute.FITNESS, 10),
					new Value<Attribute, Integer>(Attribute.STAMINA_MAXIMUM, 30))),
	
	MAID(true,
			false,
			"maid",
			"You're a maid, hired by a wealthy family to keep their mansion clean.",
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.FITNESS, 3),
					new Value<Attribute, Integer>(Attribute.STAMINA_MAXIMUM, 10))) {
		@Override
		public boolean isAvailable(GameCharacter character) {
			return character.isFeminine();
		}
	},
	
	BUTLER(true,
			false,
			"butler",
			"You're a butler, hired by a wealthy family to oversee the maids and deal with any visitors.",
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.FITNESS, 3),
					new Value<Attribute, Integer>(Attribute.STAMINA_MAXIMUM, 10))) {
		@Override
		public boolean isAvailable(GameCharacter character) {
			return !character.isFeminine();
		}
	},
	
	
	
//	// Good:
//	STRONG(true, false, "Strong", "You work out at the gym almost every day. You're stronger than an average person.", Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.STRENGTH, 5))),
//
//	STUDIOUS(true, false, "Intelligent", "You spend a lot of time reading and studying. You're more intelligent than an average person.", Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.INTELLIGENCE, 5))),
//
//	HEALTHY(true, false, "Healthy", "You make sure to stick to a very healthy diet and go out running every day. As a result, you're fitter than an average person.", Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.FITNESS, 5))),
//
//	INNOCENT(true, false, "Innocent", "People don't <i>really</i> have sex before marriage, right?!"
//			+ " But I suppose if they only do it once, it's ok, as you can't get pregnant from the first time!", Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.CORRUPTION, -5))),
//	
//	// Bad:
//	WEAK(true, false, "Weak", "You've got a small frame and puny muscles. You're weaker than an average person.", Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.STRENGTH, -5))),
//
//	BIRD_BRAIN(true, false, "Bird brain", "You sometimes forget what you were doing halfway throu- Ooh a penny! You're less intelligent than an average person.", Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.INTELLIGENCE, -5))),
//
//	UNFIT(true, false, "Unfit", "You've never done any exercise in your life, leaving you quite out of shape. You are less fit than an average person.", Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.FITNESS, -5))),
//
//	// Other:
//	TOWN_BIKE(true, false, "Slut", "You've lost count of the amount of guys (and sometimes girls) that you've slept with. You have a lot of experience with flirting and seducing people." + " <span style='color:" + Colour.GENERIC_SEX.toWebHexString()
//			+ ";'>You start the game having already lost your virginity.</span>", Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_MANA, 5))) {
//		@Override
//		public void applyExtraEffects(GameCharacter character) {
//			character.setVaginaVirgin(false);
//			character.setAssVirgin(false);
//			character.setFaceVirgin(false);
//
//			if (character.isPlayer()) {
//				character.setVirginityLoss(new SexType(PenetrationType.PENIS, OrificeType.MOUTH), "your first boyfriend in the park");
//				character.setSexCount(new SexType(PenetrationType.PENIS, OrificeType.MOUTH), 130 + Util.random.nextInt(50));
//				character.setCumCount(new SexType(PenetrationType.PENIS, OrificeType.MOUTH), 60 + Util.random.nextInt(40));
//
//
//				character.setVirginityLoss(new SexType(PenetrationType.TONGUE, OrificeType.VAGINA), "your first girlfriend as she lay back on her bed");
//				character.setSexCount(new SexType(PenetrationType.TONGUE, OrificeType.VAGINA), 5 + Util.random.nextInt(20));
//				
//
//				character.setVirginityLoss(new SexType(PenetrationType.TONGUE, OrificeType.VAGINA), "your first girlfriend after you did the same for her");
//				character.setSexCount(new SexType(PenetrationType.TONGUE, OrificeType.VAGINA), 5 + Util.random.nextInt(15));
//				
//
//				character.setVirginityLoss(new SexType(PenetrationType.PENIS, OrificeType.VAGINA), "some guy in a club's toilet cubicle");
//				character.setSexCount(new SexType(PenetrationType.PENIS, OrificeType.VAGINA), 60 + Util.random.nextInt(30));
//				character.setCumCount(new SexType(PenetrationType.PENIS, OrificeType.VAGINA), 20 + Util.random.nextInt(20));
//				
//
//				character.setVirginityLoss(new SexType(PenetrationType.PENIS, OrificeType.ANUS), "some guy in your first threesome");
//				character.setSexCount(new SexType(PenetrationType.PENIS, OrificeType.ANUS), 5 + Util.random.nextInt(10));
//				character.setCumCount(new SexType(PenetrationType.PENIS, OrificeType.ANUS), 2 + Util.random.nextInt(5));
//			}
//
//			character.setAssCapacity(Capacity.THREE_SLIGHTLY_LOOSE.getMedianValue());
//			character.setVaginaCapacity(Capacity.FOUR_LOOSE.getMedianValue());
//		}
//
//		@Override
//		public void revertExtraEffects(GameCharacter character) {
//			character.setVaginaVirgin(true);
//			character.setAssVirgin(true);
//			character.setFaceVirgin(true);
//
//			if (character.isPlayer()) {
//				character.setVirginityLoss(new SexType(PenetrationType.PENIS, OrificeType.MOUTH), "");
//				character.setSexCount(new SexType(PenetrationType.PENIS, OrificeType.MOUTH), 0);
//				character.setCumCount(new SexType(PenetrationType.PENIS, OrificeType.MOUTH), 0);
//
//
//				character.setVirginityLoss(new SexType(PenetrationType.TONGUE, OrificeType.VAGINA), "");
//				character.setSexCount(new SexType(PenetrationType.TONGUE, OrificeType.VAGINA), 0);
//				
//
//				character.setVirginityLoss(new SexType(PenetrationType.TONGUE, OrificeType.VAGINA), "");
//				character.setSexCount(new SexType(PenetrationType.TONGUE, OrificeType.VAGINA), 0);
//				
//
//				character.setVirginityLoss(new SexType(PenetrationType.PENIS, OrificeType.VAGINA), "");
//				character.setSexCount(new SexType(PenetrationType.PENIS, OrificeType.VAGINA), 0);
//				character.setCumCount(new SexType(PenetrationType.PENIS, OrificeType.VAGINA), 0);
//				
//
//				character.setVirginityLoss(new SexType(PenetrationType.PENIS, OrificeType.ANUS), "");
//				character.setSexCount(new SexType(PenetrationType.PENIS, OrificeType.ANUS), 0);
//				character.setCumCount(new SexType(PenetrationType.PENIS, OrificeType.ANUS), 0);
//			}
//
//			character.setAssCapacity(Capacity.ZERO_IMPENETRABLE.getMedianValue());
//			character.setVaginaCapacity(Capacity.ONE_EXTREMELY_TIGHT.getMedianValue());
//
//		}
//
//		@Override
//		public boolean isAvailable(GameCharacter player) {
//			return player.getGender() == Gender.F_V_B_FEMALE;
//		}
//	}
	;

	private static List<History> historiesList;
	
	public static List<History> getAvailableHistories(GameCharacter character) {
		historiesList = new ArrayList<>();

		for(History history : History.values()) {
			if(history.isAvailable(character) && (character.isPlayer()?history.isAvailableToPlayer():true) && (!character.isPlayer()?history.isAvailableToPartner():true)) {
				historiesList.add(history);
			}
		}
		
		return historiesList;
	}


	private String name, descriptionPlayer;
	private boolean availableToPlayer, availableToPartner;
	// Attributes modified by this Trait:
	private HashMap<Attribute, Integer> attributeModifiers;

	private History(boolean availableToPlayer, boolean availableToPartner, String name, String descriptionPlayer, HashMap<Attribute, Integer> attributeModifiers) {
		this.availableToPlayer = availableToPlayer;
		this.availableToPartner = availableToPartner;
		this.name = name;
		this.descriptionPlayer = descriptionPlayer;
		
		if(attributeModifiers == null) {
			this.attributeModifiers = Util.newHashMapOfValues();
		} else {
			this.attributeModifiers = attributeModifiers;
		}
	}
	
	public boolean isAvailable(GameCharacter character) {
		return true;
	}

	public void applyExtraEffects(GameCharacter character) {
	}

	public void revertExtraEffects(GameCharacter character) {
	}

	public boolean isAvailableToPlayer() {
		return availableToPlayer;
	}
	public boolean isAvailableToPartner() {
		return availableToPartner;
	}
	public String getName() {
		return name;
	}

	public String getDescriptionPlayer() {
		return descriptionPlayer;
	}

	private StringBuilder descriptionSB;

	public String getModifiersAsStringList() {
		descriptionSB = new StringBuilder();
		int i = 0;
		if (attributeModifiers != null)
			for (Entry<Attribute, Integer> e : attributeModifiers.entrySet()) {
				if (i != 0)
					descriptionSB.append("</br>");
				descriptionSB.append("<b>" + (e.getValue() > 0 ? "+" : "") + e.getValue() + "</b> <b style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>core</b> " + "<b style='color: " + e.getKey().getColour().toWebHexString() + ";'>"
						+ Util.capitaliseSentence(e.getKey().getName()) + "</b>");
				i++;
			}
		return descriptionSB.toString();
	}

	public HashMap<Attribute, Integer> getAttributeModifiers() {
		return attributeModifiers;
	}
}
