package com.lilithsthrone.game.character.persona;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.2.4
 * @author Innoxia
 */
public enum Name {

	HUMAN(Util.newArrayListOfValues(
					new NameTriplet("Alexander", "Alex", "Alexandria"),
					new NameTriplet("Alexius", "Alex", "Alexia"),
					new NameTriplet("Alex", "Alex", "Alex"),
					new NameTriplet("Ash", "Ashe", "Ashley"),
					
					new NameTriplet("Bart", "Bailey", "Bridget"),
					new NameTriplet("Ben", "Beverly", "Bella"),
					
					new NameTriplet("Carl", "Casey", "Cadence"),
					new NameTriplet("Charlie", "Charlie", "Charlie"),
					
					new NameTriplet("Daniel", "Danny", "Dani"),
					new NameTriplet("Dale", "Devon", "Diana"),
					
					new NameTriplet("Edward", "Ed", "Elaine"),
					new NameTriplet("Eli", "Emery", "Evelyn"),
					
					new NameTriplet("Fred", "Frankie", "Felicity"),
					
					new NameTriplet("George", "Georgie", "Grace"),
					
					new NameTriplet("Harry", "Harley", "Hailey"),
					new NameTriplet("Hank", "Haiden", "Holly"),
					
					new NameTriplet("Ian", "Izzy", "Isabelle"),

					new NameTriplet("James", "Jackie", "Jasmine"),
					new NameTriplet("Jack", "Jay", "Jennifer"),
					new NameTriplet("Jean", "Jess", "Jessica"),

					new NameTriplet("Kevin", "Kel", "Katie"),
					new NameTriplet("Ken", "Kelly", "Katherine"),

					new NameTriplet("Lee", "Louie", "Lauren"),
					new NameTriplet("Leonard", "Linden", "Leah"),
					new NameTriplet("Len", "Lumi", "Laura"),
					
					new NameTriplet("Micheal", "Maddy", "Maria"),
					new NameTriplet("Mike", "Max", "Miranda"),
					
					new NameTriplet("Nicholas", "Nat", "Natalia"),
					
					new NameTriplet("Oliver", "Oli", "Olivia"),
					
					new NameTriplet("Peter", "Parker", "Paige"),
					new NameTriplet("Pete", "Phoenix", "Penelope"),
					
					new NameTriplet("Quentin", "Quinn", "Quinta"),
					
					new NameTriplet("Richard", "Robby", "Rachel"),
					
					new NameTriplet("Stanley", "Sam", "Stephanie"),
					new NameTriplet("Stan", "Sacha", "Summer"),
					
					new NameTriplet("Thomas", "Toni", "Tracy"),

					new NameTriplet("Ulysses", "Umber", "Ursula"),

					new NameTriplet("Vincent", "Val", "Violet"),
					new NameTriplet("Vinn", "Vic", "Vixen"),

					new NameTriplet("William", "Winter", "Whitney"),
					new NameTriplet("Will", "Wynne", "Willow"))),
	
	EQUINE(Util.newArrayListOfValues(
					new NameTriplet("Aqua", "Aqua", "Aqua"),
					
					new NameTriplet("Bramble", "Bramble", "Bramble"),

					new NameTriplet("Dasher", "Dasher", "Dasher"),
					new NameTriplet("Dazzle", "Dazzle", "Dazzle"),

					new NameTriplet("Flint", "Flint", "Flint"),
					
					new NameTriplet("Fleet", "Fleet", "Fleet"),
					
					new NameTriplet("Midnight", "Midnight", "Midnight"),
					new NameTriplet("Moonwind", "Moonwind", "Moonwind"),

					new NameTriplet("Nimbus", "Nimbus", "Nimbus"),

					new NameTriplet("Pearl", "Pearl", "Pearl"),
					new NameTriplet("Prixie", "Prixie", "Prixie"),
					
					new NameTriplet("Skyfeet", "Skyfeet", "Skyfeet"),
					new NameTriplet("Starr", "Starr", "Starr"),
					new NameTriplet("Spirit", "Spirit", "Spirit"),
					
					new NameTriplet("Thundermane", "Thundermane", "Thundermane"),
					new NameTriplet("Twilight", "Twilight", "Twilight"),
					
					new NameTriplet("Wildlight", "Wildlight", "Wildlight"))),
	
	// Similar to equine names
	REINDEER(Util.newArrayListOfValues(
			
			new NameTriplet("Dasher", "Dasher", "Dasher"),
			new NameTriplet("Dancer", "Dancer", "Dancer"),
			new NameTriplet("Prancer", "Prancer", "Prancer"),
			new NameTriplet("Vixen", "Vixen", "Vixen"),
			new NameTriplet("Comet", "Comet", "Comet"),
			new NameTriplet("Cupid", "Cupid", "Cupid"),
			new NameTriplet("Dunder", "Dunder", "Dunder"),
			new NameTriplet("Blixem", "Blixem", "Blixem"),
			
			new NameTriplet("Aqua", "Aqua", "Aqua"),
			
			new NameTriplet("Bramble", "Bramble", "Bramble"),

			new NameTriplet("Dasher", "Dasher", "Dasher"),
			new NameTriplet("Dazzle", "Dazzle", "Dazzle"),

			new NameTriplet("Flint", "Flint", "Flint"),
			
			new NameTriplet("Fleet", "Fleet", "Fleet"),
			
			new NameTriplet("Midnight", "Midnight", "Midnight"),
			new NameTriplet("Moonwind", "Moonwind", "Moonwind"),

			new NameTriplet("Nimbus", "Nimbus", "Nimbus"),

			new NameTriplet("Pearl", "Pearl", "Pearl"),
			new NameTriplet("Prixie", "Prixie", "Prixie"),
			
			new NameTriplet("Skyfeet", "Skyfeet", "Skyfeet"),
			new NameTriplet("Starr", "Starr", "Starr"),
			new NameTriplet("Spirit", "Spirit", "Spirit"),
			
			new NameTriplet("Thundermane", "Thundermane", "Thundermane"),
			new NameTriplet("Twilight", "Twilight", "Twilight"),
			
			new NameTriplet("Wildlight", "Wildlight", "Wildlight"))),
	
	// No offence if your name is on here... x_x
	PROSTITUTE(Util.newArrayListOfValues(
					new NameTriplet("Amber", "Amber", "Amber"),
					new NameTriplet("Autumn", "Autumn", "Autumn"),

					new NameTriplet("Bambi", "Bambi", "Bambi"),
					new NameTriplet("Ben", "Brandy", "Brandy"),
					new NameTriplet("Brian", "Brianna", "Brianna"),

					new NameTriplet("Carl", "Chloe", "Chloe"),
					new NameTriplet("Carl", "Claudia", "Claudia"),
					new NameTriplet("Carl", "Charlene", "Charlene"),
					new NameTriplet("Chad", "Chantelle", "Chantelle"),
					new NameTriplet("Carl", "Courtney", "Courtney"),
					new NameTriplet("Carl", "Cassandra", "Cassandra"),
					new NameTriplet("Chad", "Channing", "Channing"),
					new NameTriplet("Carl", "Crystal", "Crystal"),
					new NameTriplet("Casey", "Casey", "Casey"),

					new NameTriplet("Dom", "Dolly", "Dolly"),
					new NameTriplet("Devon", "Devon", "Devon"),
					new NameTriplet("Dale", "Dakota", "Dakota"),

					new NameTriplet("Emmett", "Emmalou", "Emmalou"), // Great Scott!
					
					new NameTriplet("Harry", "Heather", "Heather"),

					new NameTriplet("Jimmy", "Jenny", "Jenny"),
					new NameTriplet("Joe", "Jolene", "Jolene"),

					new NameTriplet("Kyle", "Kylie", "Kylie"),
					new NameTriplet("Karl", "Kendra", "Kendra"),
					new NameTriplet("Karl", "Krista", "Krista"),
					new NameTriplet("Kelsey", "Kelsey", "Kelsey"),

					new NameTriplet("Lawrence", "Lauren", "Lauren"),
					
					new NameTriplet("Mike", "Misty", "Misty"),
					new NameTriplet("Mike", "Melody", "Melody"),
					new NameTriplet("Mike", "Mindy", "Mindy"),
					
					new NameTriplet("Nikki", "Nikki", "Nikki"),
					new NameTriplet("Noel", "Noel", "Noel"),
					
					new NameTriplet("Ruby", "Ruby", "Ruby"),
					new NameTriplet("Reba", "Reba", "Reba"),

					new NameTriplet("Savannah", "Savannah", "Savannah"),
					new NameTriplet("Sam", "Samantha", "Samantha"),
					new NameTriplet("Sean", "Serena", "Serena"),
					new NameTriplet("Sierra", "Sierra", "Sierra"),
					new NameTriplet("Shelby", "Shelby", "Shelby"),
					new NameTriplet("Shawn", "Shawna", "Shawna"),

					new NameTriplet("Tim", "Trina", "Trina"),
					new NameTriplet("Tammy", "Tammy", "Tammy"),
					new NameTriplet("Tom", "Tara", "Tara"),
					new NameTriplet("Taylor", "Taylor", "Taylor"),
					
					new NameTriplet("Urleen", "Urleen", "Urleen"))),
	
	;
	
//	HUMAN_MALE(Race.HUMAN,
//			new String[] { "Alexander", "Al", "Alex", "Bartholomew", "Bart", "Brett", "Charles", "Carl", "Charlie", "Daniel", "Dan", "Dale", "Edward", "Ed", "Eli", "Fredrick", "Fred", "Frankie", "George", "Garry", "Gray", "Harry", "Hank", "Iago",
//					"Ian", "James", "Jack", "Jean", "Kevin", "Ken", "Kai", "Leonard", "Len", "Lee", "Micheal", "Mike", "Nicholas", "Nick", "Oliver", "Oli", "Peter", "Pete", "Quentin", "Quintin", "Richard", "Rick", "Stanley", "Stan", "Thomas", "Tom",
//					"Ulysses", "Ulfred", "Vincent", "Vinn", "William", "Will" }),
//
//	HUMAN_NEUTRAL(Race.HUMAN,
//			new String[] { "Ashley", "Alex", "Beverly", "Carol", "Danny", "Ed", "Fran", "Frankie", "Georgie", "Izzy", "Jackie", "Jay", "Jess", "Kel", "Louie", "Maddy", "Max", "Nat", "Nicky", "Oli", "Robby", "Sacha", "Sam", "Toni", "Tony", "Val",
//					"Vic" }),
//
//	HUMAN_FEMALE(Race.HUMAN,
//			new String[] { "Alice", "Alicia", "Alli", "Alex", "Amber", "Anna", "Audrey", "Bella", "Bridget", "Brooklyn", "Charlie", "Cadence", "Cathy", "Cyndi", "Dale", "Diana", "Dani", "Elaine", "Elizabeth", "Ellen", "Elli", "Emily", "Evelyn",
//					"Felicity", "Faye", "Grace", "Gia", "Hailey", "Harley", "Heidi", "Holly", "Isabelle", "Izzy", "Jackie", "Jane", "Jasmine", "Jennifer", "Jessica", "Jynx", "Kai", "Katie", "Katherine", "Katrina", "Kiara", "Kitty", "Laura", "Lauren",
//					"Leah", "Lexi", "Maria", "Mikah", "Miranda", "Missy", "Natalia", "Natasha", "Naomi", "Nicole", "Nikki", "Ola", "Olivia", "Onyx", "Paige", "Penelope", "Penny", "Quin", "Quinta", "Qi", "Raine", "Rachel", "Roxy", "Scarlett", "Stacy",
//					"Stephanie", "Summer", "Tracy", "Trixie", "Ursula", "Uma", "Val", "Violet", "Vixen", "Whitney", "Wendy", "Willow" }),
//	
//	HORSE_MORPH_MALE(Race.HORSE_MORPH,
//			new String[] { "Alex", "Colt", "Dale", "Franklin", "Jacob", "Marcus", "Sylvester" }),
//	
//	HORSE_MORPH_FEMALE(Race.HORSE_MORPH,
//			new String[] { "Alex", "Brooke", "Holly", "Moon", "Raine", "Sable", "Summer", "Vale" });
	
	private static final String[] surnames = new String[] {"Smith", "Jones", "Taylor", "Williams", "Brown", "Davies", "Evans", "Wilson", "Thomas", "Roberts", "Johnson", "Lewis", "Walker",
			"Robinson", "Wood", "Thompson", "White", "Watson", "Jackson", "Wright", "Green", "Harris", "Cooper", "King", "Lee", "Martin", "Clarke", "James", "Morgan", "Hughes", "Edwards",
			"Hill", "Moore", "Clark", "Harrison", "Scott", "Young", "Morris", "Hall", "Ward", "Turner", "Carter", "Phillips", "Mitchell", "Patel", "Adams", "Campbell", "Anderson", "Allen",
			"Cook", "Bailey", "Parker", "Miller", "Davis", "Murphy", "Price", "Bell", "Baker", "Griffiths", "Kelly", "Simpson", "Marshall", "Collins", "Bennett", "Cox", "Richardson",
			"Fox", "Gray", "Rose", "Chapman", "Hunt", "Robertson", "Shaw", "Reynolds", "Lloyd", "Ellis", "Richards", "Russell", "Wilkinson", "Khan", "Graham", "Stewart", "Reid", "Murray", 
			"Powell", "Palmer", "Holmes", "Rogers", "Stevens", "Walsh", "Hunter", "Thomson", "Matthews", "Ross", "Owen", "Mason", "Knight", "Kennedy", "Butler", "Saunders", "", "Cole", "Pearce",
			"Dean", "Foster", "Harvey", "Hudson", "Gibson", "Mills", "Berry", "Barnes", "Pearson", "Kaur", "Booth", "Dixon", "Grant", "Gordon", "Lane", "Harper", "Ali", "Hart", "Mcdonald", "Brooks",
			"Ryan", "Carr", "Macdonald", "Hamilton", "Johnston", "West", "Gill", "Dawson", "Armstrong", "Gardner", "Stone", "Andrews", "Williamson", "Barker", "George", "Fisher", "Cunningham", "Watts",
			"Webb", "Lawrence", "Bradley", "Jenkins", "Wells", "Chambers", "Spencer", "Poole", "Atkinson", "Lawson"};
	
	private List<NameTriplet> names;

	private Name(List<NameTriplet> names) {
		this.names = names;
	}
	
	public static String getRandomName(GameCharacter gc) {
		Name name = Name.HUMAN;
		
		switch(gc.getRace()) {
			case NONE:
			case ANGEL:
			case CAT_MORPH:
			case COW_MORPH:
			case DEMON:
			case FOX_MORPH:
			case DOG_MORPH:
			case ALLIGATOR_MORPH:
			case HARPY:
			case HUMAN:
			case WOLF_MORPH:
			case SQUIRREL_MORPH:
			case SLIME:
			case BAT_MORPH:
			case RAT_MORPH:
			case RABBIT_MORPH:
			case ELEMENTAL_AIR:
			case ELEMENTAL_ARCANE:
			case ELEMENTAL_EARTH:
			case ELEMENTAL_FIRE:
			case ELEMENTAL_WATER:
				break;
				
			case REINDEER_MORPH:
				name = Name.REINDEER;
				break;
			case HORSE_MORPH:
				name = Name.EQUINE;
				break;
		}
		
		if(gc.getFemininityValue() <= Femininity.MASCULINE.getMaximumFemininity()) {
			return name.getNameTriplets().get(Util.random.nextInt(name.getNameTriplets().size())).getMasculine();
			
		} else if (gc.getFemininityValue() <= Femininity.ANDROGYNOUS.getMaximumFemininity()) {
			return name.getNameTriplets().get(Util.random.nextInt(name.getNameTriplets().size())).getAndrogynous();
			
		} else {
			return name.getNameTriplets().get(Util.random.nextInt(name.getNameTriplets().size())).getFeminine();
		}
	}
	
	public static String getRandomSurname() {
		return surnames[Util.random.nextInt(surnames.length)];
	}
	
	public static NameTriplet getRandomTriplet(Race r) {
		Name name = Name.HUMAN;
		
		switch(r) {
			case NONE:
			case ANGEL:
			case CAT_MORPH:
			case COW_MORPH:
			case DOG_MORPH:
			case FOX_MORPH:
			case ALLIGATOR_MORPH:
			case HARPY:
			case HUMAN:
			case WOLF_MORPH:
			case SQUIRREL_MORPH:
			case SLIME:
			case BAT_MORPH:
			case RAT_MORPH:
			case RABBIT_MORPH:
			break;
			
			case DEMON:
			case ELEMENTAL_AIR:
			case ELEMENTAL_ARCANE:
			case ELEMENTAL_EARTH:
			case ELEMENTAL_FIRE:
			case ELEMENTAL_WATER:
				return getDemonName();
			case REINDEER_MORPH:
				name = Name.REINDEER;
				break;
			case HORSE_MORPH:
				name = Name.EQUINE;
				break;
		}
		
		return name.getNameTriplets().get(Util.random.nextInt(name.getNameTriplets().size()));
	}
	
	private static NameTriplet getDemonName() {
		String[] preixFem = new String[] {"Aella", "Bella", "Cae", "Deva", "Ella", "Fae", "Hela", "Isa", "Katha", "Loe", "Nysa", "Oella", "Rae", "Sytha", "Vixxa", "Wynna"};
		String[] preixMas = new String[] {"Ada", "Boro", "Foro", "Helio", "Kiri", "Zara"};
		
		String[] postfixFem = new String[] {"jyx", "ryth", "ney", "nix", "sys", "trix"};
		String[] postfixMas = new String[] {"jyx", "ryth", "ney", "nix", "sys", "trix"};
		
		String femName = preixFem[Util.random.nextInt(preixFem.length)] + postfixFem[Util.random.nextInt(postfixFem.length)];
		char startingChar = femName.charAt(0);

		String masName = preixMas[Util.random.nextInt(preixMas.length)] + postfixMas[Util.random.nextInt(postfixMas.length)];
		
		List<String> masculineNames = new ArrayList<>();
		for(String s : preixMas) {
			if(s.charAt(0) == startingChar) {
				masculineNames.add(s);
			}
		}
		if(!masculineNames.isEmpty()) {
			masName = masculineNames.get(Util.random.nextInt(masculineNames.size())) + postfixMas[Util.random.nextInt(postfixMas.length)];
		}
		
		return new NameTriplet(masName, femName, femName);
	}
	
	public static NameTriplet getRandomProstituteTriplet() {
		return PROSTITUTE.getNameTriplets().get(Util.random.nextInt(PROSTITUTE.getNameTriplets().size()));
	}

	public List<NameTriplet> getNameTriplets() {
		return names;
	}
}
