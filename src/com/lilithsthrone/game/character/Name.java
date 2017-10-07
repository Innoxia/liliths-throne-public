package com.lilithsthrone.game.character;

import java.util.List;

import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.0
 * @version 0.1.75
 * @author Innoxia
 */
public enum Name {

	HUMAN(Race.HUMAN,
			Util.newArrayListOfValues(
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
	
	// No offence if your name is on here... x_x
	PROSTITUTE(Race.HUMAN,
			Util.newArrayListOfValues(
					new ListValue<>(new NameTriplet("Brandy", "Brandy", "Brandy")),
					new ListValue<>(new NameTriplet("Heather", "Heather", "Heather")),
					new ListValue<>(new NameTriplet("Channing", "Channing", "Channing")),
					new ListValue<>(new NameTriplet("Brianna", "Brianna", "Brianna")),
					new ListValue<>(new NameTriplet("Amber", "Amber", "Amber")),
					new ListValue<>(new NameTriplet("Serena", "Serena", "Serena")),
					new ListValue<>(new NameTriplet("Melody", "Melody", "Melody")),
					new ListValue<>(new NameTriplet("Dakota", "Dakota", "Dakota")),
					new ListValue<>(new NameTriplet("Sierra", "Sierra", "Sierra")),
					new ListValue<>(new NameTriplet("Bambi", "Bambi", "Bambi")),
					new ListValue<>(new NameTriplet("Crystal", "Crystal", "Crystal")),
					new ListValue<>(new NameTriplet("Samantha", "Samantha", "Samantha")),
					new ListValue<>(new NameTriplet("Autumn", "Autumn", "Autumn")),
					new ListValue<>(new NameTriplet("Ruby", "Ruby", "Ruby")),
					new ListValue<>(new NameTriplet("Taylor", "Taylor", "Taylor")),
					new ListValue<>(new NameTriplet("Tara", "Tara", "Tara")),
					new ListValue<>(new NameTriplet("Tammy", "Tammy", "Tammy")),
					new ListValue<>(new NameTriplet("Lauren", "Lauren", "Lauren")),
					new ListValue<>(new NameTriplet("Charlene", "Charlene", "Charlene")),
					new ListValue<>(new NameTriplet("Chantelle", "Chantelle", "Chantelle")),
					new ListValue<>(new NameTriplet("Courtney", "Courtney", "Courtney")),
					new ListValue<>(new NameTriplet("Misty", "Misty", "Misty")),
					new ListValue<>(new NameTriplet("Jenny", "Jenny", "Jenny")),
					new ListValue<>(new NameTriplet("Krista", "Krista", "Krista")),
					new ListValue<>(new NameTriplet("Mindy", "Mindy", "Mindy")),
					new ListValue<>(new NameTriplet("Noel", "Noel", "Noel")),
					new ListValue<>(new NameTriplet("Shelby", "Shelby", "Shelby")),
					new ListValue<>(new NameTriplet("Trina", "Trina", "Trina")),
					new ListValue<>(new NameTriplet("Reba", "Reba", "Reba")),
					new ListValue<>(new NameTriplet("Cassandra", "Cassandra", "Cassandra")),
					new ListValue<>(new NameTriplet("Nikki", "Nikki", "Nikki")),
					new ListValue<>(new NameTriplet("Kelsey", "Kelsey", "Kelsey")),
					new ListValue<>(new NameTriplet("Shawna", "Shawna", "Shawna")),
					new ListValue<>(new NameTriplet("Jolene", "Jolene", "Jolene")),
					new ListValue<>(new NameTriplet("Urleen", "Urleen", "Urleen")),
					new ListValue<>(new NameTriplet("Claudia", "Claudia", "Claudia")),
					new ListValue<>(new NameTriplet("Savannah", "Savannah", "Savannah")),
					new ListValue<>(new NameTriplet("Casey", "Casey", "Casey")),
					new ListValue<>(new NameTriplet("Dolly", "Dolly", "Dolly")),
					new ListValue<>(new NameTriplet("Kendra", "Kendra", "Kendra")),
					new ListValue<>(new NameTriplet("Kylie", "Kylie", "Kylie")),
					new ListValue<>(new NameTriplet("Chloe", "Chloe", "Chloe")),
					new ListValue<>(new NameTriplet("Devon", "Devon", "Devon")),
					new ListValue<>(new NameTriplet("Emmalou", "Emmalou", "Emmalou"))));
	
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

	private Name(Race race, List<NameTriplet> names) {
		this.names = names;
	}
	
	public static String getRandomName(GameCharacter gc) {
		switch(gc.getRace()) {
			case ANGEL:
				break;
			case CAT_MORPH:
				break;
			case COW_MORPH:
				break;
			case DEMON:
				break;
			case DOG_MORPH:
				break;
			case HARPY:
				break;
			case HORSE_MORPH:
				break;
			case HUMAN:
				break;
			case SLIME:
				break;
			case WOLF_MORPH:
				break;
			case SQUIRREL_MORPH:
				break;
		}
		
		if(gc.getFemininityValue() <= Femininity.MASCULINE.getMaximumFemininity()) {
			return HUMAN.getNameTriplets().get(Util.random.nextInt(HUMAN.getNameTriplets().size())).getMasculine();
			
		} else if (gc.getFemininityValue() <= Femininity.ANDROGYNOUS.getMaximumFemininity()) {
			return HUMAN.getNameTriplets().get(Util.random.nextInt(HUMAN.getNameTriplets().size())).getAndrogynous();
			
		} else {
			return HUMAN.getNameTriplets().get(Util.random.nextInt(HUMAN.getNameTriplets().size())).getFeminine();
		}
	}
	
	public static String getRandomSurname() {
		return surnames[Util.random.nextInt(surnames.length)];
	}
	
	public static NameTriplet getRandomTriplet(Race r) {
		switch(r) {
			case ANGEL:
				break;
			case CAT_MORPH:
				break;
			case COW_MORPH:
				break;
			case DEMON:
				break;
			case DOG_MORPH:
				break;
			case HARPY:
				break;
			case HORSE_MORPH:
				break;
			case HUMAN:
				break;
			case SLIME:
				break;
			case WOLF_MORPH:
				break;
			case SQUIRREL_MORPH:
				break;
		}
		
		return HUMAN.getNameTriplets().get(Util.random.nextInt(HUMAN.getNameTriplets().size()));
	}
	
	public static NameTriplet getRandomProstituteTriplet() {
		return PROSTITUTE.getNameTriplets().get(Util.random.nextInt(PROSTITUTE.getNameTriplets().size()));
	}

	public List<NameTriplet> getNameTriplets() {
		return names;
	}
}
