package com.lilithsthrone.game.character.persona;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.0
 * @version 0.2.4
 * @author Innoxia
 */
public enum Name {

	HUMAN(Util.newArrayListOfValues(
					new ListValue<>(new NameTriplet("Alexander", "Alex", "Alexandria")),
					new ListValue<>(new NameTriplet("Alexius", "Alex", "Alexia")),
					new ListValue<>(new NameTriplet("Alex", "Alex", "Alex")),
					new ListValue<>(new NameTriplet("Ash", "Ashe", "Ashley")),
					
					new ListValue<>(new NameTriplet("Bart", "Bailey", "Bridget")),
					new ListValue<>(new NameTriplet("Ben", "Beverly", "Bella")),
					
					new ListValue<>(new NameTriplet("Carl", "Casey", "Cadence")),
					new ListValue<>(new NameTriplet("Charlie", "Charlie", "Charlie")),
					
					new ListValue<>(new NameTriplet("Daniel", "Danny", "Dani")),
					new ListValue<>(new NameTriplet("Dale", "Devon", "Diana")),
					
					new ListValue<>(new NameTriplet("Edward", "Ed", "Elaine")),
					new ListValue<>(new NameTriplet("Eli", "Emery", "Evelyn")),
					
					new ListValue<>(new NameTriplet("Fred", "Frankie", "Felicity")),
					
					new ListValue<>(new NameTriplet("George", "Georgie", "Grace")),
					
					new ListValue<>(new NameTriplet("Harry", "Harley", "Hailey")),
					new ListValue<>(new NameTriplet("Hank", "Haiden", "Holly")),
					
					new ListValue<>(new NameTriplet("Ian", "Izzy", "Isabelle")),

					new ListValue<>(new NameTriplet("James", "Jackie", "Jasmine")),
					new ListValue<>(new NameTriplet("Jack", "Jay", "Jennifer")),
					new ListValue<>(new NameTriplet("Jean", "Jess", "Jessica")),

					new ListValue<>(new NameTriplet("Kevin", "Kel", "Katie")),
					new ListValue<>(new NameTriplet("Ken", "Kelly", "Katherine")),

					new ListValue<>(new NameTriplet("Lee", "Louie", "Lauren")),
					new ListValue<>(new NameTriplet("Leonard", "Linden", "Leah")),
					new ListValue<>(new NameTriplet("Len", "Lumi", "Laura")),
					
					new ListValue<>(new NameTriplet("Micheal", "Maddy", "Maria")),
					new ListValue<>(new NameTriplet("Mike", "Max", "Miranda")),
					
					new ListValue<>(new NameTriplet("Nicholas", "Nat", "Natalia")),
					
					new ListValue<>(new NameTriplet("Oliver", "Oli", "Olivia")),
					
					new ListValue<>(new NameTriplet("Peter", "Parker", "Paige")),
					new ListValue<>(new NameTriplet("Pete", "Phoenix", "Penelope")),
					
					new ListValue<>(new NameTriplet("Quentin", "Quinn", "Quinta")),
					
					new ListValue<>(new NameTriplet("Richard", "Robby", "Rachel")),
					
					new ListValue<>(new NameTriplet("Stanley", "Sam", "Stephanie")),
					new ListValue<>(new NameTriplet("Stan", "Sacha", "Summer")),
					
					new ListValue<>(new NameTriplet("Thomas", "Toni", "Tracy")),

					new ListValue<>(new NameTriplet("Ulysses", "Umber", "Ursula")),

					new ListValue<>(new NameTriplet("Vincent", "Val", "Violet")),
					new ListValue<>(new NameTriplet("Vinn", "Vic", "Vixen")),

					new ListValue<>(new NameTriplet("William", "Winter", "Whitney")),
					new ListValue<>(new NameTriplet("Will", "Wynne", "Willow")))),
	
	EQUINE(Util.newArrayListOfValues(
					new ListValue<>(new NameTriplet("Aqua", "Aqua", "Aqua")),
					
					new ListValue<>(new NameTriplet("Bramble", "Bramble", "Bramble")),

					new ListValue<>(new NameTriplet("Dasher", "Dasher", "Dasher")),
					new ListValue<>(new NameTriplet("Dazzle", "Dazzle", "Dazzle")),

					new ListValue<>(new NameTriplet("Flint", "Flint", "Flint")),
					
					new ListValue<>(new NameTriplet("Fleet", "Fleet", "Fleet")),
					
					new ListValue<>(new NameTriplet("Midnight", "Midnight", "Midnight")),
					new ListValue<>(new NameTriplet("Moonwind", "Moonwind", "Moonwind")),

					new ListValue<>(new NameTriplet("Nimbus", "Nimbus", "Nimbus")),

					new ListValue<>(new NameTriplet("Pearl", "Pearl", "Pearl")),
					new ListValue<>(new NameTriplet("Prixie", "Prixie", "Prixie")),
					
					new ListValue<>(new NameTriplet("Skyfeet", "Skyfeet", "Skyfeet")),
					new ListValue<>(new NameTriplet("Starr", "Starr", "Starr")),
					new ListValue<>(new NameTriplet("Spirit", "Spirit", "Spirit")),
					
					new ListValue<>(new NameTriplet("Thundermane", "Thundermane", "Thundermane")),
					new ListValue<>(new NameTriplet("Twilight", "Twilight", "Twilight")),
					
					new ListValue<>(new NameTriplet("Wildlight", "Wildlight", "Wildlight")))),
	
	// Similar to equine names
	REINDEER(Util.newArrayListOfValues(
			
			new ListValue<>(new NameTriplet("Dasher", "Dasher", "Dasher")),
			new ListValue<>(new NameTriplet("Dancer", "Dancer", "Dancer")),
			new ListValue<>(new NameTriplet("Prancer", "Prancer", "Prancer")),
			new ListValue<>(new NameTriplet("Vixen", "Vixen", "Vixen")),
			new ListValue<>(new NameTriplet("Comet", "Comet", "Comet")),
			new ListValue<>(new NameTriplet("Cupid", "Cupid", "Cupid")),
			new ListValue<>(new NameTriplet("Dunder", "Dunder", "Dunder")),
			new ListValue<>(new NameTriplet("Blixem", "Blixem", "Blixem")),
			
			new ListValue<>(new NameTriplet("Aqua", "Aqua", "Aqua")),
			
			new ListValue<>(new NameTriplet("Bramble", "Bramble", "Bramble")),

			new ListValue<>(new NameTriplet("Dasher", "Dasher", "Dasher")),
			new ListValue<>(new NameTriplet("Dazzle", "Dazzle", "Dazzle")),

			new ListValue<>(new NameTriplet("Flint", "Flint", "Flint")),
			
			new ListValue<>(new NameTriplet("Fleet", "Fleet", "Fleet")),
			
			new ListValue<>(new NameTriplet("Midnight", "Midnight", "Midnight")),
			new ListValue<>(new NameTriplet("Moonwind", "Moonwind", "Moonwind")),

			new ListValue<>(new NameTriplet("Nimbus", "Nimbus", "Nimbus")),

			new ListValue<>(new NameTriplet("Pearl", "Pearl", "Pearl")),
			new ListValue<>(new NameTriplet("Prixie", "Prixie", "Prixie")),
			
			new ListValue<>(new NameTriplet("Skyfeet", "Skyfeet", "Skyfeet")),
			new ListValue<>(new NameTriplet("Starr", "Starr", "Starr")),
			new ListValue<>(new NameTriplet("Spirit", "Spirit", "Spirit")),
			
			new ListValue<>(new NameTriplet("Thundermane", "Thundermane", "Thundermane")),
			new ListValue<>(new NameTriplet("Twilight", "Twilight", "Twilight")),
			
			new ListValue<>(new NameTriplet("Wildlight", "Wildlight", "Wildlight")))),
	
	// No offence if your name is on here... x_x
	PROSTITUTE(Util.newArrayListOfValues(
					new ListValue<>(new NameTriplet("Amber", "Amber", "Amber")),
					new ListValue<>(new NameTriplet("Autumn", "Autumn", "Autumn")),

					new ListValue<>(new NameTriplet("Bambi", "Bambi", "Bambi")),
					new ListValue<>(new NameTriplet("Ben", "Brandy", "Brandy")),
					new ListValue<>(new NameTriplet("Brian", "Brianna", "Brianna")),

					new ListValue<>(new NameTriplet("Carl", "Chloe", "Chloe")),
					new ListValue<>(new NameTriplet("Carl", "Claudia", "Claudia")),
					new ListValue<>(new NameTriplet("Carl", "Charlene", "Charlene")),
					new ListValue<>(new NameTriplet("Chad", "Chantelle", "Chantelle")),
					new ListValue<>(new NameTriplet("Carl", "Courtney", "Courtney")),
					new ListValue<>(new NameTriplet("Carl", "Cassandra", "Cassandra")),
					new ListValue<>(new NameTriplet("Chad", "Channing", "Channing")),
					new ListValue<>(new NameTriplet("Carl", "Crystal", "Crystal")),
					new ListValue<>(new NameTriplet("Casey", "Casey", "Casey")),

					new ListValue<>(new NameTriplet("Dom", "Dolly", "Dolly")),
					new ListValue<>(new NameTriplet("Devon", "Devon", "Devon")),
					new ListValue<>(new NameTriplet("Dale", "Dakota", "Dakota")),

					new ListValue<>(new NameTriplet("Emmett", "Emmalou", "Emmalou")), // Great Scott!
					
					new ListValue<>(new NameTriplet("Harry", "Heather", "Heather")),

					new ListValue<>(new NameTriplet("Jimmy", "Jenny", "Jenny")),
					new ListValue<>(new NameTriplet("Joe", "Jolene", "Jolene")),

					new ListValue<>(new NameTriplet("Kyle", "Kylie", "Kylie")),
					new ListValue<>(new NameTriplet("Karl", "Kendra", "Kendra")),
					new ListValue<>(new NameTriplet("Karl", "Krista", "Krista")),
					new ListValue<>(new NameTriplet("Kelsey", "Kelsey", "Kelsey")),

					new ListValue<>(new NameTriplet("Lawrence", "Lauren", "Lauren")),
					
					new ListValue<>(new NameTriplet("Mike", "Misty", "Misty")),
					new ListValue<>(new NameTriplet("Mike", "Melody", "Melody")),
					new ListValue<>(new NameTriplet("Mike", "Mindy", "Mindy")),
					
					new ListValue<>(new NameTriplet("Nikki", "Nikki", "Nikki")),
					new ListValue<>(new NameTriplet("Noel", "Noel", "Noel")),
					
					new ListValue<>(new NameTriplet("Ruby", "Ruby", "Ruby")),
					new ListValue<>(new NameTriplet("Reba", "Reba", "Reba")),

					new ListValue<>(new NameTriplet("Savannah", "Savannah", "Savannah")),
					new ListValue<>(new NameTriplet("Sam", "Samantha", "Samantha")),
					new ListValue<>(new NameTriplet("Sean", "Serena", "Serena")),
					new ListValue<>(new NameTriplet("Sierra", "Sierra", "Sierra")),
					new ListValue<>(new NameTriplet("Shelby", "Shelby", "Shelby")),
					new ListValue<>(new NameTriplet("Shawn", "Shawna", "Shawna")),

					new ListValue<>(new NameTriplet("Tim", "Trina", "Trina")),
					new ListValue<>(new NameTriplet("Tammy", "Tammy", "Tammy")),
					new ListValue<>(new NameTriplet("Tom", "Tara", "Tara")),
					new ListValue<>(new NameTriplet("Taylor", "Taylor", "Taylor")),
					
					new ListValue<>(new NameTriplet("Urleen", "Urleen", "Urleen")))),
	
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
			case ANGEL:
			case CAT_MORPH:
			case COW_MORPH:
			case DEMON:
			case IMP:
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
			case ANGEL:
			case CAT_MORPH:
			case COW_MORPH:
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
			break;
			
			case DEMON:
			case IMP:
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
