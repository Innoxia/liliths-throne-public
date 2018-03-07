package com.lilithsthrone.game.character;

import java.util.ArrayList;
import java.util.List;
import com.lilithsthrone.game.character.effects.Perk;

/**
 * @since 0.1.0
 * @version 0.2.1
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

	REINDEER_OVERSEER(false, true, null, "overseer", "-"),
	
	// Partner histories:

	UNEMPLOYED(true, true, Perk.JOB_UNEMPLOYED, "unemployed", "-"),
	
	// Sociable personality:
	
	PROSTITUTE(false, true, Perk.JOB_PROSTITUTE, "prostitute", "-"),
//	STRIPPER(false, true, "stripper", "-", null),
//	BAR_TENDER(false, true, "barmaid", "-", null),
//	MASSAGE_THERAPIST(false, true, "massage therapist", "-", null),
//	WAITRESS(false, true, "waitress", "-", null),
//	BEAUTICIAN(false, true, "beautician", "-", null),
//	MUSICIAN(false, true, "musician", "-", null),
//	FITNESS_INSTRUCTOR(false, true, "fitness instructor", "-", null),
	
	// Commanding personality:
	
	MUGGER(false, true, Perk.JOB_MUGGER, "mugger", "-"),
//	CONSTRUCTION_WORKER(false, true, "construction worker", "-", null),
//	MECHANIC(false, true, "mechanic", "-", null),
//	TEACHER(false, true, "teacher", "-", null),
//	ENFORCER(false, true, "enforcer", "-", null),
//	HIGH_RANKING_ENFORCER(false, true, "enforcer chief", "-", null),
	
	// Analytical personality:

//	CON_ARTIST(false, true, "con-artist", "-", null),
//	LIBRARIAN(false, true, "librarian", "-", null),
//	UNIVERSITY_STUDENT(false, true, "university student", "-", null),
//	WRITER(false, true, "writer", "-", null),
//	ENGINEER(false, true, "engineer", "-", null),
//	ARCHITECT(false, true, "architect", "-", null),
//	DOCTOR(false, true, "doctor", "-", null),
//	ARCANE_RESEARCHER(false, true, "arcane researcher", "-", null),
	
	// Calm personality:

//	MAID(false, true, "maid", "-", null),
//	RECEPTIONIST(false, true, "receptionist", "-", null),
//	SHOP_ASSISTANT(false, true, "shop assistant", "-", null),
//	ARTIST(false, true, "artist", "-", null),
//	NURSE(false, true, "nurse", "-", null),
//	CHEF(false, true, "chef", "-", null),
//	ATHLETE(false, true, "athlete", "-", null),
//	MODEL(false, true, "model", "-", null),
	
	
	
	// Player histories:
	
	// Salaryman/woman - inspired by your work ethic, slaves earn 25% more money
	OFFICE_WORKER(true,
			false,
			Perk.JOB_OFFICE_WORKER,
			"office worker",
			"You work in a local office, handling paperwork, answering phonecalls and emails, and generally doing a little bit of everything."),
	
	// Student discount - 25% discount in all shops
	STUDENT(true,
			false,
			Perk.JOB_STUDENT,
			"student",
			"You're a student at the city's university, but your haven't quite decided what to take as your major just yet."),

	// Arcane Composition - All utility spells have double length
	MUSICIAN(true,
			false,
			Perk.JOB_MUSICIAN,
			"musician",
			"You're a musician, and as well as being able to play a wide variety of instruments, you are also a very good singer."),
	
	// In Control - Slaves gain obedience 10% faster
	TEACHER(true,
			false,
			Perk.JOB_TEACHER,
			"teacher",
			"You're a teacher, and have been working at a local school for a few years."),
	
	// Meditations - You keep a diary and do your best to learn from past mistakes +25% xp gains
	WRITER(true,
			false,
			Perk.JOB_WRITER,
			"writer",
			"You're a writer, and have been working on your latest novel for the last few months."),
	
	// Fine Taste - Doubles all potion effects
	CHEF(true,
			false,
			Perk.JOB_CHEF,
			"chef",
			"You're the head chef at a local restaurant."),
	
	// Combat Drill - Your first attack in combat does triple damage. 
	SOLDIER(true,
			false,
			Perk.JOB_SOLDIER,
			"soldier",
			"You're a soldier, and are currently making the most of your leave."),
	
	// 10-second barrier - Always escape on first try
	ATHLETE(true,
			false,
			Perk.JOB_ATHLETE,
			"athlete",
			"You're an athlete, and are currently training for your next big event."),
	
	// Special maid outfit effects (make it OP as fuck)
	MAID(true,
		false,
		Perk.JOB_MAID,
		"maid",
		"You're a maid, hired by a wealthy family to keep their mansion clean.") {
		@Override
		public boolean isAvailable(GameCharacter character) {
			return character.isFeminine();
		}
	},
	
	// Special butler outfit effects (make it OP as fuck)
	BUTLER(true,
			false,
			Perk.JOB_BUTLER,
			"butler",
			"You're a butler, hired by a wealthy family to oversee the maids and deal with any visitors.") {
		@Override
		public boolean isAvailable(GameCharacter character) {
			return !character.isFeminine();
		}
	},
	
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
	private Perk associatedPerk;

	private History(boolean availableToPlayer, boolean availableToPartner, Perk associatedPerk, String name, String descriptionPlayer) {
		this.availableToPlayer = availableToPlayer;
		this.availableToPartner = availableToPartner;
		this.associatedPerk = associatedPerk;
		this.name = name;
		this.descriptionPlayer = descriptionPlayer;
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

	public Perk getAssociatedPerk() {
		return associatedPerk;
	}
	public String getName() {
		return name;
	}

	public String getDescriptionPlayer() {
		return descriptionPlayer;
	}

}
