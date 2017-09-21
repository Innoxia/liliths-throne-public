package com.base.game.character;

import java.util.List;

import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.character.race.Race;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;

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
					new ListValue<>(new NameTriplet("Will", "Wynne", "Willow"))));
	
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
		
		if(Math.random()>0.999f) {
			new ListValue<>(new NameTriplet("Innoxia", "Innoxia", "Innoxia"));// :3
		}
		
		return HUMAN.getNameTriplets().get(Util.random.nextInt(HUMAN.getNameTriplets().size()));
	}

	public List<NameTriplet> getNameTriplets() {
		return names;
	}
}
