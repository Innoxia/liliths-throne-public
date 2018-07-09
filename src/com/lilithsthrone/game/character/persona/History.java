package com.lilithsthrone.game.character.persona;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.2.8
 * @author Innoxia
 */
public enum History {
	
	// Unique:

	REINDEER_OVERSEER(false, false, null, "overseer", "-"),
	
	// NPC histories:

	UNEMPLOYED_NPC(false, false, Perk.JOB_UNEMPLOYED, "unemployed", "[npc.NameIsFull] unemployed."),
	
	// Sociable personality:
	
	NPC_PROSTITUTE(false, true, Perk.JOB_PROSTITUTE, "prostitute", "[npc.NameIsFull] a prostitute, making a living by selling [npc.her] body."),
	
	NPC_STRIPPER(false, false, Perk.JOB_MISC, "stripper", "[npc.Name] [npc.verb(work)] as a stripper."),
	
	NPC_BAR_TENDER(false, false, Perk.JOB_MISC, "barmaid", "[npc.Name] [npc.verb(work)] as a bar."),
	
	NPC_MASSAGE_THERAPIST(false, false, Perk.JOB_MISC, "massage therapist", "[npc.Name] [npc.verb(work)] at a spa as a massage therapist."),
	
	NPC_WAITRESS(false, false, Perk.JOB_MISC, "waitress", "[npc.Name] [npc.verb(work)] as a waitress in a restaurant."),
	
	NPC_BEAUTICIAN(false, false, Perk.JOB_MISC, "beautician", "[npc.Name] [npc.verb(work)] as a beautician in a spa."),
	
	NPC_MUSICIAN(false, false, Perk.JOB_MISC, "musician", "[npc.Name] [npc.verb(work)] as a musician."),
	
	NPC_FITNESS_INSTRUCTOR(false, false, Perk.JOB_MISC, "fitness instructor", "-"),
	
	
	// Commanding personality:
	
	NPC_MUGGER(false, true, Perk.JOB_MUGGER, "mugger", "[npc.NameIsFull] a mugger, and [npc.verb(make)] a living by stealing other people's possessions."),
	
	NPC_CONSTRUCTION_WORKER(false, false, Perk.JOB_MISC, "construction worker", "-"),
	
	NPC_MECHANIC(false, false, Perk.JOB_MISC, "mechanic", "-"),
	
	NPC_TEACHER(false, false, Perk.JOB_MISC, "teacher", "-"),
	
	NPC_ENFORCER(false, false, Perk.JOB_MISC, "enforcer", "-"),
	
//	NPC_HIGH_RANKING_ENFORCER(false, false, Perk.JOB_MISC, "enforcer chief", "-"),
	
	// Analytical personality:

//	CON_ARTIST(false, Perk.JOB_MISC, "con-artist", "-"),
	NPC_LIBRARIAN(false, false, Perk.JOB_MISC, "librarian", "-"),
	
	NPC_UNIVERSITY_STUDENT(false, false, Perk.JOB_MISC, "university student", "-"),
	
	NPC_WRITER(false, false, Perk.JOB_MISC, "writer", "-"),
	
	NPC_ENGINEER(false, false, Perk.JOB_MISC, "engineer", "-"),
	
	NPC_ARCHITECT(false, false, Perk.JOB_MISC, "architect", "-"),
	
	NPC_DOCTOR(false, false, Perk.JOB_MISC, "doctor", "-"),
	
	NPC_ARCANE_RESEARCHER(false, false, Perk.JOB_MISC, "arcane researcher", "-"),
	
	
	// Calm personality:

	NPC_MAID(false, false, Perk.JOB_MISC, "maid", "-"),
	
	NPC_RECEPTIONIST(false, false, Perk.JOB_MISC, "receptionist", "-"),
	
	NPC_SHOP_ASSISTANT(false, false, Perk.JOB_MISC, "shop assistant", "-"),
	
	NPC_ARTIST(false, false, Perk.JOB_MISC, "artist", "-"),
	
	NPC_NURSE(false, false, Perk.JOB_MISC, "nurse", "-"),
	
	NPC_CHEF(false, false, Perk.JOB_MISC, "chef", "-"),
	
	NPC_ATHLETE(false, false, Perk.JOB_MISC, "athlete", "-"),
	
	NPC_MODEL(false, false, Perk.JOB_MISC, "model", "-"),
	
	
	
	// Player histories:

	UNEMPLOYED(true,
			false,
			Perk.JOB_UNEMPLOYED,
			"unemployed", "You've been out of work for a little while now."),
	
	OFFICE_WORKER(true,
			false,
			Perk.JOB_OFFICE_WORKER,
			"office worker", "You work in a local office, handling paperwork, answering phonecalls and emails, and generally doing a little bit of everything."),
	
	STUDENT(true,
			false,
			Perk.JOB_STUDENT,
			"student", "You're a student at the city's university, but you haven't quite decided what to take as your major just yet."),

	MUSICIAN(true,
			false,
			Perk.JOB_MUSICIAN,
			"musician", "You're a musician, and as well as being able to play a wide variety of instruments, you are also a very good singer."),
	
	TEACHER(true,
			false,
			Perk.JOB_TEACHER,
			"teacher", "You're a teacher, and have been working at a local school for a few years."),
	
	WRITER(true,
			false,
			Perk.JOB_WRITER,
			"writer", "You're a writer, and have been working on your latest novel for the last few months."),
	
	CHEF(true,
			false,
			Perk.JOB_CHEF,
			"chef", "You're the head chef at a local restaurant."),
	
	SOLDIER(true,
			false,
			Perk.JOB_SOLDIER,
			"soldier", "You're a soldier, and are currently making the most of your leave."),
	
	ATHLETE(true,
			false,
			Perk.JOB_ATHLETE,
			"athlete", "You're an athlete, and are currently training for your next big event."),
	
	MAID(true,
		false,
		Perk.JOB_MAID,
		"maid", "You're a maid, hired by a wealthy family to keep their mansion clean.") {
		@Override
		public boolean isAvailable(GameCharacter character) {
			return character.isFeminine();
		}
	},
	
	BUTLER(true,
			false,
			Perk.JOB_BUTLER,
			"butler", "You're a butler, hired by a wealthy family to oversee the maids and deal with any visitors.") {
		@Override
		public boolean isAvailable(GameCharacter character) {
			return !character.isFeminine();
		}
	};
	
	
	

	private static List<History> historiesList;
	
	public static List<History> getAvailableHistories(GameCharacter character) {
		historiesList = new ArrayList<>();

		for(History history : History.values()) {
			if(history.isAvailable(character) && (character.isPlayer()?history.isAvailableToPlayer():history.isAvailableToPartner())) {
				historiesList.add(history);
			}
		}
		
		return historiesList;
	}


	private String name;
	private String description;
	private boolean playerStartingHistory;
	private Perk associatedPerk;
	private boolean lowlife;

	private History(boolean playerStartingHistory,
			boolean lowlife,
			Perk associatedPerk,
			String name,
			String description) {
		this.playerStartingHistory = playerStartingHistory;
		this.lowlife = lowlife;
		this.associatedPerk = associatedPerk;
		this.name = name;
		this.description = description;
	}
	
	public boolean isAvailable(GameCharacter character) {
		return true;
	}

	public void applyExtraEffects(GameCharacter character) {
	}

	public void revertExtraEffects(GameCharacter character) {
	}

	public boolean isAvailableToPlayer() {
		return playerStartingHistory;
	}
	
	public boolean isAvailableToPartner() {
		return !playerStartingHistory;
	}

	public Perk getAssociatedPerk() {
		return associatedPerk;
	}
	public String getName() {
		return name;
	}

	public String getDescription(GameCharacter character) {
		return UtilText.parse(character, description);
	}

	public boolean isLowlife() {
		return lowlife;
	}

}
