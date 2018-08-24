package com.lilithsthrone.game.character.persona;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public enum Occupation {
	
	// Unique:
	
	ELEMENTAL(Perk.JOB_MISC, "elemental", "-", OccupationTag.HAS_PREREQUISITES),

	REINDEER_OVERSEER(Perk.JOB_MISC, "overseer", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_HARPY_MATRIARCH(Perk.JOB_MISC, "harpy matriarch", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_HARPY_FLOCK_MEMBER(Perk.JOB_MISC, "harpy flock member", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_ARCANE_RESEARCHER(Perk.JOB_MISC, "arcane researcher", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_HIGH_RANKING_ENFORCER(Perk.JOB_MISC, "Enforcer chief", "-", OccupationTag.HAS_PREREQUISITES),

	NPC_SLAVER_ADMIN(Perk.JOB_MISC, "slaver administration staff", "-", OccupationTag.HAS_PREREQUISITES),

	NPC_NIGHTCLUB_OWNER(Perk.JOB_MISC, "nightclub owner", "-", OccupationTag.HAS_PREREQUISITES),

	NPC_CLOTHING_STORE_OWNER(Perk.JOB_MISC, "clothing store owner", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_GYM_OWNER(Perk.JOB_MISC, "gym owner", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_STORE_OWNER(Perk.JOB_MISC, "store owner", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_CASINO_OWNER(Perk.JOB_MISC, "casino owner", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_EPONA(Perk.JOB_MISC, "pregnancy roulette manager", "-", OccupationTag.HAS_PREREQUISITES),

	NPC_SLIME_QUEEN(Perk.JOB_MISC, "slime queen", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_SLIME_QUEEN_GUARD(Perk.JOB_MISC, "slime queen's guard", "-", OccupationTag.HAS_PREREQUISITES),
	
	// NPC histories:

	NPC_UNEMPLOYED(Perk.JOB_UNEMPLOYED, "unemployed", "[npc.NameIsFull] unemployed."),
	
	// Sociable personality:

	NPC_SLAVE(Perk.JOB_SLAVE, "slave", "[npc.NameIsFull] a slave, and must carry out [npc.her] owner's wishes."),
	
	NPC_PROSTITUTE(Perk.JOB_PROSTITUTE, "prostitute", "[npc.NameIsFull] a prostitute, making a living by selling [npc.her] body.", OccupationTag.LOWLIFE),
	
	NPC_STRIPPER(Perk.JOB_MISC, "stripper", "[npc.Name] [npc.verb(work)] as a stripper.", OccupationTag.EVENING_SHIFT) {
		@Override
		public DayOfWeek getStartDay() {
			return DayOfWeek.TUESDAY;
		}
		@Override
		public DayOfWeek getEndDay() {
			return DayOfWeek.SATURDAY;
		}
	},
	
	NPC_BAR_TENDER(Perk.JOB_MISC, "barmaid", "[npc.Name] [npc.verb(work)] as a barmaid.", OccupationTag.EVENING_SHIFT) {
		@Override
		public boolean isAvailable(GameCharacter character) {
			return character.isFeminine();
		}
	},
	
	NPC_MASSAGE_THERAPIST(Perk.JOB_MISC, "massage therapist", "[npc.Name] [npc.verb(work)] at a spa as a massage therapist."),
	
	NPC_WAITRESS(Perk.JOB_MISC, "waitress", "[npc.Name] [npc.verb(work)] as a waitress in a restaurant.") {
		@Override
		public boolean isAvailable(GameCharacter character) {
			return character.isFeminine();
		}
	},
	
	NPC_BEAUTICIAN(Perk.JOB_MISC, "beautician", "[npc.Name] [npc.verb(work)] as a beautician in a spa."),
	
	NPC_MUSICIAN(Perk.JOB_MISC, "musician", "[npc.Name] [npc.verb(work)] as a musician.", OccupationTag.HAS_PREREQUISITES),
	
	NPC_FITNESS_INSTRUCTOR(Perk.JOB_MISC, "fitness instructor", "-", OccupationTag.HAS_PREREQUISITES),
	
	
	// Commanding personality:
	
	NPC_MUGGER(Perk.JOB_MUGGER, "mugger", "[npc.NameIsFull] a mugger, and [npc.verb(make)] a living by stealing other people's possessions.", OccupationTag.LOWLIFE),
	
	NPC_CONSTRUCTION_WORKER(Perk.JOB_MISC, "construction worker", "-"),

	NPC_BOUNCER(Perk.JOB_MISC, "bouncer", "-"),
	
	NPC_MECHANIC(Perk.JOB_MISC, "mechanic", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_TEACHER(Perk.JOB_MISC, "teacher", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_ENFORCER(Perk.JOB_MISC, "enforcer", "-"),
	
	// Analytical personality:

	NPC_LIBRARIAN(Perk.JOB_MISC, "librarian", "-"),
	
	NPC_UNIVERSITY_STUDENT(Perk.JOB_MISC, "university student", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_WRITER(Perk.JOB_MISC, "writer", "-"),
	
	NPC_ENGINEER(Perk.JOB_MISC, "engineer", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_ARCHITECT(Perk.JOB_MISC, "architect", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_DOCTOR(Perk.JOB_MISC, "doctor", "-", OccupationTag.HAS_PREREQUISITES),
	
	
	// Calm personality:

	NPC_MAID(Perk.JOB_MISC, "maid", "-") {
		@Override
		public boolean isAvailable(GameCharacter character) {
			return character.isFeminine();
		}
		@Override
		public DayOfWeek getStartDay() {
			return DayOfWeek.MONDAY;
		}
		@Override
		public DayOfWeek getEndDay() {
			return DayOfWeek.SATURDAY;
		}
	},

	NPC_BUTLER(Perk.JOB_MISC, "butler", "-") {
		@Override
		public boolean isAvailable(GameCharacter character) {
			return !character.isFeminine();
		}
		@Override
		public DayOfWeek getStartDay() {
			return DayOfWeek.MONDAY;
		}
		@Override
		public DayOfWeek getEndDay() {
			return DayOfWeek.SATURDAY;
		}
	},
	
	NPC_RECEPTIONIST(Perk.JOB_MISC, "receptionist", "-"),
	
	NPC_SHOP_ASSISTANT(Perk.JOB_MISC, "shop assistant", "-"),
	
	NPC_ARTIST(Perk.JOB_MISC, "artist", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_NURSE(Perk.JOB_MISC, "nurse", "-") {
		@Override
		public boolean isAvailable(GameCharacter character) {
			return character.isFeminine();
		}
		@Override
		public DayOfWeek getStartDay() {
			return DayOfWeek.MONDAY;
		}
		@Override
		public DayOfWeek getEndDay() {
			return DayOfWeek.SATURDAY;
		}
	},
	
	NPC_CHEF(Perk.JOB_MISC, "chef", "-"),
	
	NPC_ATHLETE(Perk.JOB_MISC, "athlete", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_MODEL(Perk.JOB_MISC, "model", "-"),
	
	
	
	// Player histories:

	UNEMPLOYED(Perk.JOB_UNEMPLOYED,
			"unemployed",
			"You've been out of work for a little while now.", OccupationTag.PLAYER_ONLY),
	
	OFFICE_WORKER(Perk.JOB_OFFICE_WORKER,
			"office worker",
			"You work in a local office, handling paperwork, answering phonecalls and emails, and generally doing a little bit of everything.", OccupationTag.PLAYER_ONLY),
	
	STUDENT(Perk.JOB_STUDENT,
			"student",
			"You're a student at the city's university, but you haven't quite decided what to take as your major just yet.", OccupationTag.PLAYER_ONLY),

	MUSICIAN(Perk.JOB_MUSICIAN,
			"musician",
			"You're a musician, and as well as being able to play a wide variety of instruments, you are also a very good singer.", OccupationTag.PLAYER_ONLY),
	
	TEACHER(Perk.JOB_TEACHER,
			"teacher",
			"You're a teacher, and have been working at a local school for a few years.", OccupationTag.PLAYER_ONLY),
	
	WRITER(Perk.JOB_WRITER,
			"writer",
			"You're a writer, and have been working on your latest novel for the last few months.", OccupationTag.PLAYER_ONLY),
	
	CHEF(Perk.JOB_CHEF,
			"chef",
			"You're the head chef at a local restaurant.", OccupationTag.PLAYER_ONLY),
	
	SOLDIER(Perk.JOB_SOLDIER,
			"soldier",
			"You're a soldier, and are currently making the most of your leave.", OccupationTag.PLAYER_ONLY),
	
	ATHLETE(Perk.JOB_ATHLETE,
			"athlete",
			"You're an athlete, and are currently training for your next big event.", OccupationTag.PLAYER_ONLY),
	
	MAID(Perk.JOB_MAID,
		"maid",
		"You're a maid, hired by a wealthy family to keep their mansion clean.", OccupationTag.PLAYER_ONLY) {
		@Override
		public boolean isAvailable(GameCharacter character) {
			return character.isFeminine();
		}
	},
	
	BUTLER(Perk.JOB_BUTLER,
			"butler",
			"You're a butler, hired by a wealthy family to oversee the maids and deal with any visitors.", OccupationTag.PLAYER_ONLY) {
		@Override
		public boolean isAvailable(GameCharacter character) {
			return !character.isFeminine();
		}
	};
	
	
	private static List<Occupation> historiesList;
	
	public static List<Occupation> getAvailableHistories(GameCharacter character) {
		historiesList = new ArrayList<>();

		for(Occupation history : Occupation.values()) {
			if(history.isAvailable(character) && (character.isPlayer()?history.isAvailableToPlayer():!history.isAvailableToPlayer())) {
				historiesList.add(history);
			}
		}
		
		return historiesList;
	}


	protected static boolean[] noWorkHours = new boolean[24];
	protected static boolean[] daylightWorkHours = new boolean[24];
	protected static boolean[] eveningWorkHours = new boolean[24];
	protected static boolean[] nightWorkHours = new boolean[24];
	
	static {
		for(int i=0; i<8; i++) {
			daylightWorkHours[9+i] = true;
			
			int hour = (18+i)%24;
			eveningWorkHours[hour] = true;
			
			hour = (21+i)%24;
			nightWorkHours[hour] = true;
		}
	}

	private String name;
	private String description;
	private Perk associatedPerk;
	private List<OccupationTag> occupationTags;

	private Occupation(Perk associatedPerk,
			String name,
			String description,
			OccupationTag... occupationTags) {
		
		this.associatedPerk = associatedPerk;
		this.name = name;
		this.description = description;
		
		this.occupationTags = new ArrayList<>();
		for(OccupationTag tag : occupationTags) {
			this.occupationTags.add(tag);
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
		return occupationTags.contains(OccupationTag.PLAYER_ONLY);
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
		return occupationTags.contains(OccupationTag.LOWLIFE);
	}

	public List<OccupationTag> getOccupationTags() {
		return occupationTags;
	}
	
	public boolean isAtWork(int hour) {
		return Main.game.getDateNow().getDayOfWeek().getValue()>=getStartDay().getValue()
				&& Main.game.getDateNow().getDayOfWeek().getValue()<=getEndDay().getValue()
				&& getWorkHours()[hour];
	}
	
	public boolean[] getWorkHours() {
		if(this.getOccupationTags().contains(OccupationTag.LOWLIFE)) {
			return noWorkHours;
		}
		if(this.getOccupationTags().contains(OccupationTag.EVENING_SHIFT)) {
			return eveningWorkHours;
		}
		if(this.getOccupationTags().contains(OccupationTag.NIGHT_SHIFT)) {
			return nightWorkHours;
		}
		return daylightWorkHours;
	}
	
	public int getWorkHourStart() {
		for(int i=0; i<24; i++) {
			int hour = (6+i)%24;
			if(getWorkHours()[hour]) {
				return 6+i;
			}
		}
		return 0;
	}
	
	public int getWorkHourEnd() {
		return (getWorkHourStart()+8)%24;
	}
	
	public DayOfWeek getStartDay() {
		return DayOfWeek.MONDAY;
	}

	public DayOfWeek getEndDay() {
		return DayOfWeek.FRIDAY;
	}
}
