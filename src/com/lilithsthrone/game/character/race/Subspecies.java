package com.lilithsthrone.game.character.race;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.91
 * @version 0.1.99
 * @author tukaima
 */
public enum Subspecies {

	// HUMAN:
	HUMAN("human",
			"humans",
			"man",
			"woman",
			"men",
			"women",
			Race.HUMAN,
			Colour.RACE_HUMAN,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical human.") {
		@Override
		public void applySpeciesChanges(Body body) {
			// TODO Auto-generated method stub

		}
	},

	// ANGEL:
	ANGEL("angel",
			"angels",
			"angel",
			"angel",
			"angel",
			"angel",
			Race.ANGEL,
			Colour.RACE_ANGEL,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical angel.") {
		@Override
		public void applySpeciesChanges(Body body) {
			// TODO Auto-generated method stub
			
		}
	},

	// DEMON:
	DEMON("demon",
			"demons",
			"incubus",
			"succubus",
			"incubi",
			"succubi",
			Race.DEMON,
			Colour.RACE_DEMON,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical demon.") {
		@Override
		public String getOffspringMaleName() {
			return "imps";
		}
		@Override
		public String getOffspringMaleNameSingular() {
			return "imp";
		}
		@Override
		public String getOffspringFemaleName() {
			return "imps";
		}
		@Override
		public String getOffspringFemaleNameSingular() {
			return "imp";
		}
		
		@Override
		public void applySpeciesChanges(Body body) {
			// TODO Auto-generated method stub
			
		}
	},
	//IMP(Race.IMP.getName(), Race.DEMON, RacialBody.IMP, SubspeciesPreference.THREE_AVERAGE,
	//		"A smaller variety of "+Race.DEMON.getName()),
	//IMP_ALPHA(Race.IMP_ALPHA.getName(), Race.DEMON, RacialBody.IMP_ALPHA, SubspeciesPreference.ONE_MINIMAL,
	//		"A more powerful form of the "+Race.IMP.getName()),
	//LILIN(Race.LILIN.getName(), Race.DEMON, RacialBody.LILIN, SubspeciesPreference.ONE_MINIMAL,
	//		"A typical "+Race.LILIN.getName()),

	// BOVINES:
	COW_MORPH("cow-morph",
			"cow-morphs",
			"cow-boy",
			"cow-girl",
			"cow-boys",
			"cow-girls",
			Race.COW_MORPH,
			Colour.RACE_COW_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical bipedal cow-morph.") {
		@Override
		public void applySpeciesChanges(Body body) {
			// TODO Auto-generated method stub
			
		}
	},
	//MINOTAUR(Race.MINOTAUR.getName(), Race.COW_MORPH, RacialBody.MINOTAUR, SubspeciesPreference.TWO_LOW,
	//		"An aggressive male-only variety of "+Race.COW_MORPH.getName()),
	
	// CANINES:
	DOG_MORPH("dog-morph",
			"dog-morphs",
			"dog-boy",
			"dog-girl",
			"dog-boys",
			"dog-girls",
			Race.DOG_MORPH,
			Colour.RACE_DOG_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical bipedal dog-morph.") {
				@Override
				public void applySpeciesChanges(Body body) {
					// TODO Auto-generated method stub
				}
			},
	
	DOG_MORPH_DOBERMANN("dobermann-morph",
			"dobermann-morphs",
			"dobermann",
			"dobermann",
			"dobermanns",
			"dobermanns",
			Race.DOG_MORPH,
			Colour.RACE_DOG_MORPH,
			SubspeciesPreference.TWO_AVERAGE,
			"A dog-morph which resembles an anthropomorphised dobermann.") {
		@Override
		public void applySpeciesChanges(Body body) {
			Colour secondaryColour = Colour.COVERING_BROWN;
			double rand = Math.random();
			if(rand<0.3f) {
				secondaryColour = Colour.COVERING_TAN;
			} else if(rand<0.6f) {
				secondaryColour = Colour.COVERING_BROWN_DARK;
			}
			body.getCoverings().put(BodyCoveringType.CANINE_FUR, new Covering(BodyCoveringType.CANINE_FUR, CoveringPattern.MARKED, CoveringModifier.SHORT, Colour.COVERING_BLACK, false, secondaryColour, false));
			body.getCoverings().put(BodyCoveringType.HAIR_CANINE_FUR, new Covering(BodyCoveringType.CANINE_FUR, CoveringPattern.NONE, Colour.COVERING_BLACK, false, secondaryColour, false));
			body.getCoverings().put(BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, Colour.SKIN_EBONY, false, Colour.SKIN_EBONY, false));
			body.updateCoverings(true, true, true, true);
			if(body.getEar().getType()==EarType.DOG_MORPH) {
				body.getEar().setType(null, EarType.DOG_MORPH_POINTED);
			}
			if(body.getTail().getType()==TailType.DOG_MORPH) {
				body.getTail().setType(null, TailType.DOG_MORPH_STUBBY);
			}
		}

		@Override
		public String getMapIcon(GameCharacter character) {
			return SVGImages.SVG_IMAGE_PROVIDER.getRaceDobermann();
		}

		@Override
		public String getHomeMapIcon(GameCharacter character) {
			return SVGImages.SVG_IMAGE_PROVIDER.getRaceDobermannDesaturated();
		}
	},
	
	WOLF_MORPH("wolf-morph",
			"wolf-morphs",
			"wolf-boy",
			"wolf-girl",
			"wolf-boys",
			"wolf-girls",
			Race.WOLF_MORPH,
			Colour.RACE_WOLF_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical bipedal wolf-morph.") {
		@Override
		public void applySpeciesChanges(Body body) {
			// TODO Auto-generated method stub
			
		}
	},
	
	//FOX_MORPH(Race.FOX_MORPH.getName(), Race.FOX_MORPH, RacialBody.FOX_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.FOX_MORPH.getName()),
	// Kitsune have a separate racialbody than normal foxes but for subspecies preference purposes they should probably be considered the same as FOX_MORPH.
	// Just refer to the preferences for FOX_MORPH when building RacialSelectors instead of the preferences for KITSUNE.
	//FOX_TAILED("Pipefox", Race.FOX_MORPH, RacialBody.FOX_MORPH, SubspeciesPreference.TWO_LOW,
	//		"A "+Race.FOX_MORPH.getName()+" with a serpentine lower body, devoid of legs"),
	//FOX_TAUR("Yegan", Race.FOX_MORPH, RacialBody.FOX_MORPH, SubspeciesPreference.TWO_LOW,
	//		"A "+Race.FOX_MORPH.getName()+" with a bestial lower body that walks on four legs"),

	// FELINES:
	CAT_MORPH("cat-morph",
			"cat-morphs",
			"cat-boy",
			"cat-girl",
			"cat-boys",
			"cat-girls",
			Race.CAT_MORPH,
			Colour.RACE_CAT_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical bipedal cat-morph.") {
		@Override
		public void applySpeciesChanges(Body body) {
			// TODO Auto-generated method stub
			
		}
	},

	// EQUINES:
	HORSE_MORPH("horse-morph",
			"horse-morphs",
			"horse-boy",
			"horse-girl",
			"horse-boys",
			"horse-girls",
			Race.HORSE_MORPH,
			Colour.RACE_HORSE_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical bipedal horse-morph.") {
		@Override
		public void applySpeciesChanges(Body body) {
			// TODO Auto-generated method stub
			
		}
	},

	REINDEER_MORPH("reindeer-morph",
			"reindeer-morphs",
			"reindeer-boy",
			"reindeer-girl",
			"reindeer-boys",
			"reindeer-girls",
			Race.REINDEER_MORPH,
			Colour.RACE_REINDEER_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical bipedal reindeer-morph.") {
		@Override
		public void applySpeciesChanges(Body body) {
			// TODO Auto-generated method stub
			
		}
	},
	
	//CENTAUR(Race.CENTAUR.getName(), Race.HORSE_MORPH, RacialBody.CENTAUR, SubspeciesPreference.TWO_LOW,
	//		"A "+Race.HORSE_MORPH.getName()+" with a bestial lower body that walks on four legs"),

	// REPTILE:
	ALLIGATOR_MORPH("alligator-morph",
			"alligator-morphs",
			"alligator-boy",
			"alligator-girl",
			"alligator-boys",
			"alligator-girls",
			Race.ALLIGATOR_MORPH,
			Colour.RACE_ALLIGATOR_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical bipedal alligator-morph.") {
		@Override
		public void applySpeciesChanges(Body body) {
			// TODO Auto-generated method stub
			
		}
	},
	//LIZARD_MORPH(Race.LIZARD_MORPH.getName(), Race.LIZARD_MORPH, RacialBody.LIZARD_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.LIZARD_MORPH.getName()),
	//LAMIA(Race.LAMIA.getName(), Race.LIZARD_MORPH, RacialBody.LAMIA, SubspeciesPreference.TWO_LOW,
	//		"A "+Race.LIZARD_MORPH.getName()+" with a serpentine lower body, devoid of legs"),
	
	// AQUATIC:
	//SHARK_MORPH(Race.SHARK_MORPH.getName(), Race.SHARK_MORPH, RacialBody.SHARK_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.SHARK_MORPH.getName()),
	//TIGER_SHARK(Race.TIGER_SHARK.getName(), Race.TIGER_SHARK, RacialBody.TIGER_SHARK, SubspeciesPreference.FIVE_ABUNDANT,
	//		"An extremely aggressive variety of "+Race.SHARK_MORPH.getName()),
	
	// INSECTS:
	//BEE_MORPH(Race.BEE_MORPH.getName(), Race.BEE_MORPH, RacialBody.BEE_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.BEE_MORPH.getName()),
	//ROYAL_BEE(Race.ROYAL_BEE.getName(), Race.BEE_MORPH, RacialBody.ROYAL_BEE, SubspeciesPreference.ZERO_NONE,
	//		"A bipedal "+Race.BEE_MORPH.getName()+" at the top of the bee-morph hierarchy"),
	//WASP_MORPH(Race.WASP_MORPH.getName(), Race.WASP_MORPH, RacialBody.WASP_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.WASP_MORPH.getName()),
	
	// ARACHNIDS:
	//SPIDER_MORPH(Race.SPIDER_MORPH.getName(), Race.SPIDER_MORPH, RacialBody.SPIDER_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.SPIDER_MORPH.getName()),
	//ARACHNE(Race.ARACHNE.getName(), Race.SPIDER_MORPH, RacialBody.ARACHNE, SubspeciesPreference.TWO_LOW,
	//		"A "+Race.SPIDER_MORPH.getName()+" with an arachnid lower body that walks on eight legs"),
			
	// DRAGONS:
	//DRAGON(Race.DRAGON.getName(), Race.DRAGON, RacialBody.DRAGON, SubspeciesPreference.FOUR_HIGH,
	//		"A typical bipedal "+Race.DRAGON.getName()),
	//DRAGON_FUR("Fur "+Race.DRAGON.getName(), Race.DRAGON, RacialBody.DRAGON_FUR, SubspeciesPreference.ZERO_NONE,
	//		"A "+Race.DRAGON.getName()+" with a thick coat of fur, rather than scales"),
	//WYVERN(Race.WYVERN.getName(), Race.DRAGON, RacialBody.WYVERN, SubspeciesPreference.ONE_MINIMAL,
	//		"A bipedal "+Race.DRAGON.getName()+" with arms that act as wings"),
	//WYRM(Race.WYRM.getName(), Race.DRAGON, RacialBody.WYRM, SubspeciesPreference.ONE_MINIMAL,
	//		"A "+Race.DRAGON.getName()+" with a serpentine lower body, devoid of legs"),
	
	// SLIMES:
	SLIME("slime",
			"slimes",
			"slime",
			"slime",
			"slimes",
			"slimes",
			Race.SLIME,
			Colour.RACE_SLIME,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical slime.") {
		@Override
		public void applySpeciesChanges(Body body) {
			// TODO Auto-generated method stub
			
		}
	},
//	//SLIME_QUEEN(Race.SLIME_QUEEN.getName(), Race.SLIME, RacialBody.SLIME_QUEEN, SubspeciesPreference.ONE_MINIMAL,
//	//		"A female-only variety of "+Race.SLIME.getName()+" which cannibalizes its lesser kin to increase its intelligence and body mass"),
//	
//	// GARGOYLES:
//	GARGOYLE(Race.GARGOYLE.getName(), Race.GARGOYLE, RacialBody.GARGOYLE, SubspeciesPreference.FIVE_ABUNDANT,
//			"A typical "+Race.GARGOYLE.getName()),
//	GARGOYLE_CAT(Race.CAT_MORPH.getName()+" "+Race.GARGOYLE.getName(), Race.GARGOYLE, RacialBody.GARGOYLE_CAT, SubspeciesPreference.TWO_LOW,
//			"A "+Race.GARGOYLE.getName()+" resembling a typical bipedal "+Race.CAT_MORPH.getName()),
//	GARGOYLE_DOG(Race.DOG_MORPH.getName()+" "+Race.GARGOYLE.getName(), Race.GARGOYLE, RacialBody.GARGOYLE_DOG, SubspeciesPreference.TWO_LOW,
//			"A "+Race.GARGOYLE.getName()+" resembling a typical bipedal "+Race.DOG_MORPH.getName()),
//	GARGOYLE_WOLF(Race.WOLF_MORPH.getName()+" "+Race.GARGOYLE.getName(), Race.GARGOYLE, RacialBody.GARGOYLE_WOLF, SubspeciesPreference.TWO_LOW,
//			"A "+Race.GARGOYLE.getName()+" resembling a typical bipedal "+Race.WOLF_MORPH.getName()),
//	GARGOYLE_HORSE(Race.HORSE_MORPH.getName()+" "+Race.GARGOYLE.getName(), Race.GARGOYLE, RacialBody.GARGOYLE_HORSE, SubspeciesPreference.TWO_LOW,
//			"A "+Race.GARGOYLE.getName()+" resembling a typical bipedal "+Race.HORSE_MORPH.getName()),

	// RODENTS:
	SQUIRREL_MORPH("squirrel-morph",
			"squirrel-morphs",
			"squirrel-boy",
			"squirrel-girl",
			"squirrel-boys",
			"squirrel-girls",
			Race.SQUIRREL_MORPH,
			Colour.RACE_SQUIRREL_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical bipedal squirrel-morph.") {
		@Override
		public void applySpeciesChanges(Body body) {
			// TODO Auto-generated method stub
			
		}
	},
	//MOUSE_MORPH(Race.MOUSE_MORPH.getName(), Race.MOUSE_MORPH, RacialBody.MOUSE_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.MOUSE_MORPH.getName()),
	//RAT_MORPH(Race.RAT_MORPH.getName(), Race.MOUSE_MORPH, RacialBody.RAT_MORPH, SubspeciesPreference.TWO_LOW,
	//		"An extremely aggressive variety of "+Race.MOUSE_MORPH.getName()),

	// AVIAN:
	HARPY("harpy",
			"harpies",
			"harpy",
			"harpy",
			"harpies",
			"harpies",
			Race.HARPY,
			Colour.RACE_HARPY,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical harpy.") {
		@Override
		public void applySpeciesChanges(Body body) {
			// TODO Auto-generated method stub
			
		}
	};
	//TENGU(Race.TENGU.getName(), Race.TENGU, RacialBody.TENGU, SubspeciesPreference.TWO_LOW,
	//		"A hermetic kind of "+Race.HARPY.getName());

	
	private String name, namePlural, singularMaleName, singularFemaleName, pluralMaleName, pluralFemaleName;
	private Race race;
	private Colour colour;
	private SubspeciesPreference subspeciesPreferenceDefault;
	private String description;

	private Subspecies(
			String name,
			String namePlural,
			String singularMaleName,
			String singularFemaleName,
			String pluralMaleName,
			String pluralFemaleName,
			Race race, Colour colour, SubspeciesPreference subspeciesPreferenceDefault, String description) {
		this.name = name;
		this.namePlural = namePlural;

		this.singularMaleName = singularMaleName;
		this.singularFemaleName = singularFemaleName;
		
		this.pluralMaleName = pluralMaleName;
		this.pluralFemaleName = pluralFemaleName;
		
		this.race = race;
		this.colour = colour;
		this.subspeciesPreferenceDefault = subspeciesPreferenceDefault;
		this.description = description;
	}

	public abstract void applySpeciesChanges(Body body);

	public static Subspecies getSubspeciesFromBody(Body body, Race race) {
		Subspecies subspecies = null;
		
		switch(body.getBodyMaterial()) {
			case FIRE:
				break;
			case FLESH:
				break;
			case ICE:
				break;
			case RUBBER:
				break;
			case SLIME:
				subspecies = Subspecies.SLIME;
		}
		
		if(subspecies==null) {
			switch(race) {
				case ALLIGATOR_MORPH:
					subspecies = Subspecies.ALLIGATOR_MORPH;
					break;
				case ANGEL:
					subspecies = Subspecies.ANGEL;
					break;
				case CAT_MORPH:
					subspecies = Subspecies.CAT_MORPH;
					break;
				case COW_MORPH:
					subspecies = Subspecies.COW_MORPH;
					break;
				case DEMON:
					subspecies = Subspecies.DEMON;
					break;
				case DOG_MORPH:
					subspecies = Subspecies.DOG_MORPH;
					if(body.getCoverings().get(BodyCoveringType.CANINE_FUR).getPrimaryColour()==Colour.COVERING_BLACK
						&& (body.getCoverings().get(BodyCoveringType.CANINE_FUR).getSecondaryColour()==Colour.COVERING_BROWN
								|| body.getCoverings().get(BodyCoveringType.CANINE_FUR).getSecondaryColour()==Colour.COVERING_BROWN_DARK
								|| body.getCoverings().get(BodyCoveringType.CANINE_FUR).getSecondaryColour()==Colour.COVERING_TAN)
						&& body.getCoverings().get(BodyCoveringType.CANINE_FUR).getPattern() == CoveringPattern.MARKED
						&& body.getCoverings().get(BodyCoveringType.CANINE_FUR).getModifier() == CoveringModifier.SHORT
//						&& body.getEar().getType()==EarType.DOG_MORPH_CROPPED
//						&& body.getTail().getType()==TailType.DOG_MORPH_DOCKED
						) {
						subspecies = Subspecies.DOG_MORPH_DOBERMANN;
					}
					break;
				case HARPY:
					subspecies = Subspecies.HARPY;
					break;
				case HORSE_MORPH:
					subspecies = Subspecies.HORSE_MORPH;
					break;
				case HUMAN:
					subspecies = Subspecies.HUMAN;
					break;
				case REINDEER_MORPH:
					subspecies = Subspecies.REINDEER_MORPH;
					break;
				case SQUIRREL_MORPH:
					subspecies = Subspecies.SQUIRREL_MORPH;
					break;
				case WOLF_MORPH:
					subspecies = Subspecies.WOLF_MORPH;
					break;
				case SLIME:
					subspecies = Subspecies.SLIME;
					break;
			}
		}
		
		return subspecies;
	}
	
	public String getName() {
		return name;
	}
	
	public String getNamePlural() {
		return namePlural;
	}
	
	public String getSingularMaleName() {
		return singularMaleName;
	}

	public String getSingularFemaleName() {
		return singularFemaleName;
	}
	
	public String getPluralMaleName() {
		return pluralMaleName;
	}

	public String getPluralFemaleName() {
		return pluralFemaleName;
	}

	public String getOffspringMaleName() {
		return pluralMaleName;
	}
	public String getOffspringMaleNameSingular() {
		return singularMaleName;
	}
	
	public String getOffspringFemaleName() {
		return pluralFemaleName;
	}
	public String getOffspringFemaleNameSingular() {
		return singularFemaleName;
	}
	
	public Race getRace() {
		return race;
	}
	
	public Colour getColour() {
		return colour;
	}
	
	public SubspeciesPreference getSubspeciesPreferenceDefault() {
		return subspeciesPreferenceDefault;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getMapIcon(GameCharacter character) {
		return getRace().getStatusEffect().getSVGString(character);
	}
	
	public String getHomeMapIcon(GameCharacter character) {
		return getRace().getStatusEffect().getSVGStringDesaturated(character);
	}
}