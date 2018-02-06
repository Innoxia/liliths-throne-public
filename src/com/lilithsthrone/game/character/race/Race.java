package com.lilithsthrone.game.character.race;

import java.util.List;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.0
 * @version 0.1.87
 * @author Innoxia
 */
public enum Race {

	// HUMAN:
	HUMAN("human", "humans",
			
			"man",
			"woman",
			
			"men",
			"women",

			"<p>"
				+ "Humans are the least numerous of all the races."
				+ " Although some people appear to be human on first glance, a fluffy pair of ears, a swishing tail, or a concealed bestial cock will often end up betraying their status as a morph of one race or another."
				+ " Humans have wildly varying personalities, body types, and sexual preferences."
			+ "</p>",
			
			
			"<p>"
				+ "Demons, following the orders of the Lilin, often encourage other races to treat humans as inferior beings."
				+ " Rumours of Lilith's intense dislike of humans are commonplace, which would explain the fact that humans are banned from serving in any position of power within Dominion."
				+ " This is partially the reason why humans are so rare; those that are born human often transform themselves to avoid persecution."
			+ "</p>"
			+ "<p>"
				+ "A defining feature of humans is that they are typically unable to harness the arcane, which often leads to further ridicule from the other races."
			+ "</p>"
			+ "<p>"
				+ "Human offspring will be a mixture of more humans and offspring of their partner's race."
			+ "</p>",


			Colour.RACE_HUMAN,
			Genus.HUMAN,
			Disposition.CIVILIZED,
			StatusEffect.PURE_HUMAN,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.MAIN)),
			true,
			0.5f,
			1,
			1,
			Attribute.DAMAGE_HUMAN,
			Attribute.RESISTANCE_HUMAN,
			null,
			null),

	// ANGEL:
	ANGEL("angel", "angels",
			"angel",
			"angel",
			"angel",
			"angel",
			
			"<p>"
				+ "Angels look pretty much exactly how most people imagine them."
				+ " Their bodies closely resemble that of a human's, with the exception of having a gigantic pair of white-feathered wings growing from their backs."
				+ " Their skin, while similar to that of a human's, gives off a pale golden radiance at all times."
			+ "</p>",
			
			"<p>"
				+ "Although they fit the stereotypical appearance of an angel, these ones certainly don't act like their traditional counterparts."
				+ " Boastful, loud, and incredibly vulgar, angels often make up stories about how powerful they are, despite the fact that they're actually only quite averagely-skilled when it comes to harnessing the arcane."
			+ "</p>"
			+ "<p>"
				+ "To an even greater extent than demons, angels are completely obsessed with sex."
				+ " Their nymphomania is so severe that it's almost impossible for an angel to be able to go more than an hour without masturbating or seeking out sex."
			+ "</p>"
			+ "<p>"
				+ "An angel's offspring will be a mixture of more angels and offspring of their partner's race."
			+ "</p>",

			Colour.CLOTHING_WHITE,
			Genus.CELESTIAL,
			Disposition.CIVILIZED,
			StatusEffect.PURE_HUMAN,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.MAIN), new ListValue<Attack>(Attack.SPELL)),
			false,
			0.25f,
			1,
			1,
			Attribute.DAMAGE_ANGEL,
			Attribute.RESISTANCE_ANGEL,
			null,
			null),

	// DEMON:
	DEMON("demon", "demons",
			"incubus",
			"succubus",
			"incubi",
			"succubi",

			"<p>"
				+ "Not only are demons real, but they also conform to their stereotypical image."
				+ " A pair of horns, bat-like wings, and a spaded tail, amongst other features, all mark a person as a demon."
				+ " Demons are able to shift their bodies into any form they like, although the vast majority of them prefer to remain as females."
			+ "</p>",
			
			"<p>"
				+ "Demons are created in one of two ways."
				+ " They are either born from a Lilin being impregnated by a demon, or they are created when a Lilin corrupts another race into becoming a demon."
				+ " If a Lilin is impregnated by a non-demon, which is an extremely rare occurrence, their offspring will be half-demons, who are the lowest on the demonic social ladder."
			+ "</p>"
			+ "<p>"
				+ "Despite their alarming appearance, demons do not seem to be predisposed towards evil."
				+ " In fact, the only personality trait that sets them apart from a regular human is that they're obsessed with sex."
				+ " This is a result of their incredibly potent arcane aura, which, while being able to shield them from an arcane storm's effects, as well as granting them access to powerful arcane spells, causes them to crave sexual pleasure."
			+ "</p>"
			+ "<p>"
				+ "A demon's offspring will be a mixture of imps and offspring of their partner's race."
			+ "</p>",


			Colour.RACE_DEMON,
			Genus.CELESTIAL,
			Disposition.CIVILIZED,
			StatusEffect.DEMON,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.SEDUCTION), new ListValue<Attack>(Attack.SPELL)),
			false,
			0f,
			2,
			3,
			Attribute.DAMAGE_DEMON,
			Attribute.RESISTANCE_DEMON,
			null,
			null){
		
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
	},

	// BOVINES:
	COW_MORPH("cow-morph", "cow-morphs",
			"cow-boy",
			"cow-girl",
			"cow-boys",
			"cow-girls",

			"<p>"
				+ "Although cow-morphs can often be found in Dominion, they're far more common and numerous out in the Foloi Fields, where their primary occupation is working in the many farms that litter the landscape."
				+ " The females of this race, called cow-girls, tend to be quite docile and submissive, which is in stark contrast to their male counterparts (cow-boys), who are typically very dominant and territorial."
			+ "</p>",
			
			"<p>"
				+ "Cow-girls are well known for having gigantic breasts and heavy lactation, and, due to this, are often the target of unwanted attention."
				+ " It's commonplace for a cow-girl to have her breasts regularly milked, and the Foloi Fields are littered with milking sheds suited for this purpose."
			+ "</p>"
			+ "<p>"
				+ "Cow-girls have strong herding instincts, and will naturally seek out a cow-boy to submit to."
				+ " As a result of this, harems are a common part of cow-morph society, with some of the stronger and more dominant cow-boys leading herds of up to fifty females."
				+ " Cow-boys are usually the owners of the many farms out in the Foloi Fields, and will assign their herd to be responsible for tending to their crops, as well as for providing milk for the farm's dairy."
			+ "</p>"
			+ "<p>"
				+ "A cow-morph's offspring will be a mixture of more cow-morphs and offspring of their partner's race."
			+ "</p>",

			Colour.RACE_COW_MORPH,
			Genus.BOVINE,
			Disposition.CIVILIZED,
			StatusEffect.COW_MORPH,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.SPECIAL_ATTACK), new ListValue<Attack>(Attack.MAIN), new ListValue<Attack>(Attack.SEDUCTION)),
			true,
			0.5f,
			1,
			1,
			Attribute.DAMAGE_COW_MORPH,
			Attribute.RESISTANCE_COW_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL),

	// CANINES:
	DOG_MORPH("dog-morph", "dog-morphs",
			"dog-boy",
			"dog-girl",
			"dog-boys",
			"dog-girls",

			"<p>"
				+ "Dog-morphs are a very common race."
				+ " Easily identified by their wagging tails, fluffy ears, and energetic mannerisms, dog-morphs can be found almost anywhere."
			+ "</p>",
			
			"<p>"
				+ "Dog-morphs are typically highly excitable and incredibly sociable, which often causes them to approach strangers in an attempt to make new friends."
				+ " Although such friendly behaviour is common, dog-morphs still have varied personalities, much like humans, and not all of them are as gregarious as their reputation would have you expect."
			+ "</p>"
			+ "<p>"
				+ "Dog-morphs, like the other common races, get heavily affected by arcane storms."
				+ " Being exposed to arcane thunder will fill dog-boys with the desire to assert their dominance over anyone they meet, while dog-girls will go into heat."
			+ "</p>"
			+ "<p>"
				+ "A dog-morph's offspring will be a mixture of dog-morphs and offspring of their partner's race."
			+ "</p>",

			Colour.RACE_DOG_MORPH,
			Genus.CANINE,
			Disposition.CIVILIZED,
			StatusEffect.DOG_MORPH,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.SPECIAL_ATTACK), new ListValue<Attack>(Attack.MAIN), new ListValue<Attack>(Attack.SEDUCTION)),
			true,
			0.5f,
			1,
			2,
			Attribute.DAMAGE_DOG_MORPH,
			Attribute.RESISTANCE_DOG_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL),

	WOLF_MORPH(
			"wolf-morph", "wolf-morphs",
			
			"wolf-boy",
			"wolf-girl",
			
			"wolf-boys",
			"wolf-girls",

			"<p>"
				+ "Wolf-morphs are a common anthropomorphic race that can be found almost anywhere."
				+ " Possessing a shaggy tail, upright ears, and hungry wolf-like eyes, wolf-morphs can sometimes be mistaken for a dog-morph at first glance."
			+ "</p>",
			
			"<p>"
				+ "Unlike dog-morphs, however, wolf-morphs are extremely unpredictable, and although they sometimes share the same energetic mannerisms as dog-morphs, they often focus their energy on trying to assert their dominance."
				+ " Wolf-morphs typically have short tempers and a primal instinct to prey on the weak."
				+ " As a result, many people try to avoid encounters with wolf-morphs, finding them to be more trouble than they're worth."
			+ "</p>"
			+ "<p>"
				+ "Wolf-morphs, like the other common races, get heavily affected by arcane storms."
				+ " Being exposed to arcane thunder will fill wolf-boys with the overwhelming desire to assert their dominance over anyone they meet, while wolf-girls will go into heat."
			+ "</p>"
			+ "<p>"
				+ "A wolf-morph's offspring will be a mixture of wolf-morphs and offspring of their partner's race."
			+ "</p>",
			
			Colour.RACE_WOLF_MORPH,
			Genus.CANINE,
			Disposition.SAVAGE,
			StatusEffect.WOLF_MORPH,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.SPECIAL_ATTACK), new ListValue<Attack>(Attack.MAIN)),
			true,
			0.5f,
			1,
			2,
			Attribute.DAMAGE_WOLF_MORPH,
			Attribute.RESISTANCE_WOLF_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL),

	// FELINES:
	CAT_MORPH("cat-morph", "cat-morphs",
			
			"cat-boy",
			"cat-girl",
			
			"cat-boys",
			"cat-girls",

			"<p>"
				+ "Cat-morphs are one of the more common anthropomorphic races found in this world."
				+ " A sleek tail, upright ears, and cat-like eyes identify a cat-morph."
			+ "</p>",
			
			"<p>"
				+ "Like all the other common races, a cat-morph's personality can vary wildly from person to person, although they do have some typical characteristics."
				+ " Cat-morphs are usually quite delicate in their mannerisms, and although they often try to hide it, they can get extremely curious about things they haven't encountered before."
				+ " Most cat-morphs are very feminine, and it's quite common for cat-boys to err more towards androgyny than masculinity."
			+ "</p>"
			+ "<p>"
				+ "Cat-morphs, like the other common races, get heavily affected by arcane storms."
				+ " Being exposed to arcane thunder will make cat-boys seek out a partner to mate with, while cat-girls will go into heat."
			+ "</p>"
			+ "<p>"
				+ "A cat-morph's offspring will be a mixture of cat-morphs and offspring of their partner's race."
			+ "</p>",

			

			Colour.RACE_CAT_MORPH,
			Genus.FELINE,
			Disposition.CIVILIZED,
			StatusEffect.CAT_MORPH,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.SPECIAL_ATTACK), new ListValue<Attack>(Attack.MAIN), new ListValue<Attack>(Attack.SEDUCTION)),
			true,
			0.5f,
			1,
			2,
			Attribute.DAMAGE_CAT_MORPH,
			Attribute.RESISTANCE_CAT_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL),

	// EQUINE:
	HORSE_MORPH(
			"horse-morph", "horse-morphs",
			
			"horse-boy",
			"horse-girl",
			
			"horse-boys",
			"horse-girls",

			"<p>"
				+ "Horse-morphs are one of the more common anthropomorphic races found in this world."
				+ " A pair of horse-like ears and a swishing tail are usually enough to identify a horse-morph."
			+ "</p>",
			
			"<p>"
				+ "Horse-morphs have the reputation of being all brawn and no brains."
				+ " There is some truth behind this characterisation, as most horse-morphs are quite dull-witted, although they are usually far stronger than the other common races."
				+ " Horse-morphs are usually very haughty, and take great offence at any perceived sleight against them."
			+ "</p>"
			+ "<p>"
				+ "Horse-morphs, like the other common races, get heavily affected by arcane storms."
				+ " Being exposed to arcane thunder will make horse-boys enter a potent rut, which, when combined with their great strength, makes them a dangerous person to meet in the middle of an arcane storm."
				+ " Horse-girls will react to arcane thunder by going into heat, and will force themselves on anyone they might come across."
			+ "</p>"
			+ "<p>"
				+ "A horse-morph's offspring will be a mixture of horse-morphs and offspring of their partner's race."
			+ "</p>",

			Colour.RACE_HORSE_MORPH,
			Genus.EQUINE,
			Disposition.CIVILIZED,
			StatusEffect.HORSE_MORPH,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.MAIN)),
			true,
			0.5f,
			1,
			1,
			Attribute.DAMAGE_HORSE_MORPH,
			Attribute.RESISTANCE_HORSE_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL),

	
	 REINDEER_MORPH(
			 "reindeer-morph", "reindeer-morphs",
				
			"reindeer-boy",
			"reindeer-girl",
			
			"reindeer-boys",
			"reindeer-girls",

			"<p>"
				+ "Reindeer-morphs are one of the rarer anthropomorphic races found in this world, and are almost exclusively found in the snowy tundra area far from Dominion."
				+ " A large pair of multiple-branching antlers are usually enough to identify a reindeer-morph, the rounded shape of which is noticeably different from the sharper antlers of the more common deer-morphs."
			+ "</p>",
				
			"<p>"
				+ "Reindeer-morphs are, for most of the year, an extremely rare sight to see outside of their traditional tundra homeland."
				+ " During the winter months, however, large groups of reindeer-morphs travel to Dominion, where they work to keep the streets shovelled clear of snow."
				+ " These reindeer work-gangs usually operate under a dominant male or female reindeer-morph, although on occasion some groups can be found to be working for an overseer of a different race."
			+ "</p>"
			+ "<p>"
				+ "Reindeer-morphs, like all non-demonic races, get heavily affected by arcane storms."
				+ " Being exposed to arcane thunder will make reindeer-boys enter a potent rut, which, when combined with their strength and stamina, makes them very dangerous to meet in the middle of an arcane storm."
				+ " Reindeer-girls will react to arcane thunder by going into heat, and will force themselves on anyone they might come across."
			+ "</p>"
			+ "<p>"
				+ "A reindeer-morph's offspring will be a mixture of reindeer-morphs and offspring of their partner's race."
			+ "</p>",
		 
	  Colour.RACE_REINDEER_MORPH,
			Genus.RANGIFERINE,
			Disposition.CIVILIZED,
			StatusEffect.REINDEER_MORPH,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.SPECIAL_ATTACK), new ListValue<Attack>(Attack.MAIN)),
			true,
			0.5f,
			1,
			2,
			Attribute.DAMAGE_REINDEER_MORPH,
			Attribute.RESISTANCE_REINDEER_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL),
			

	SQUIRREL_MORPH(
			"squirrel-morph", "squirrel-morphs",
			
			"squirrel-boy",
			"squirrel-girl",
			
			"squirrel-boys",
			"squirrel-girls",

			"<p>"
				+ "Squirrel-morphs are one of the more common anthropomorphic races found in this world."
				+ " Although they're mostly found in the wooded areas of the Foloi Fields, squirrel-morphs are also a regular sight in Dominion."
				+ " Their long fluffy tails and small round ears are usually enough to identify a squirrel-morph."
			+ "</p>",
			
			"<p>"
				+ "Squirrel-morphs have a reputation for their stunning agility."
				+ " They are excellent climbers and can scale even the most sheer of walls by launching themselves at it, even from a standstill."
				+ " Although their personalities can vary greatly, most squirrel-morphs tend to be a bit skittish most of the time."
			+ "</p>"
			+ "<p>"
				+ "Squirrel-morphs, like the other common races, get heavily affected by arcane storms."
				+ " Being exposed to arcane thunder will make squirrel-boys enter a potent rut, which, when combined with their great speed, makes them a dangerous foe to encounter during an arcane storm."
				+ " Squirrel-girls will react to arcane thunder by going into heat, and will force themselves on anyone they might come across."
			+ "</p>"
			+ "<p>"
				+ "Squirrel-morphs will give birth to one or two of their own kind, in much the same way that a human pregnancy works."
			+ "</p>",

			Colour.RACE_SQUIRREL_MORPH,
			Genus.RODENT,
			Disposition.CIVILIZED,
			StatusEffect.SQUIRREL_MORPH,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.SPECIAL_ATTACK), new ListValue<Attack>(Attack.MAIN)),
			true,
			0.25f,
			1,
			2,
			Attribute.DAMAGE_SQUIRREL_MORPH,
			Attribute.RESISTANCE_SQUIRREL_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL),
	
	ALLIGATOR_MORPH(
			"alligator-morph",
			"alligator-morphs",
			
			"alligator-boy",
			"alligator-girl",
			
			"alligator-boys",
			"alligator-girls",

			"<p>"
				+ "Alligator-morphs are one of the more common races of this world."
				+ " They're almost exclusively found in the watery undercity of Submission, making them one of the rarer races to be seen walking the streets of Dominion, in spite of their significant population."
				+ " Their large reptilian tails and alligator-like snouts make alligator-morphs very easy to identify."
			+ "</p>",
			
			"<p>"
				+ "Alligator-morphs are usually quite laid-back and easy-going, and prefer to live down in Submission to get away from the busy hustle-and-bustle of Dominion's streets."
				+ " They also love swimming, and are responsible for having converted huge parts of Submission's tunnels into slow-flowing waterways."
				+ " Being very similar in looks to the hyper-aggressive crocodile-morphs found in other parts of the world, alligator-morphs have, very unfairly, gained a slight reputation for being aggressive trouble-makers."
				+ " In contrast to how most of the population see them, alligator-morph's personalities vary greatly between individuals, and are no more or less aggressive than any of the common morphs that roam Dominion's streets."
			+ "</p>"
			+ "<p>"
				+ "Alligator-morphs, like all non-demonic races, get heavily affected by arcane storms."
				+ " Being exposed to arcane thunder will make alligator-morphs enter a potent mating frenzy, which, when combined with their great strength, makes them a dangerous foe to encounter during an arcane storm."
				+ " This is yet another reason for their self-imposed exile to Submission, as they're able to escape the effects of arcane storms down there."
			+ "</p>"
			+ "<p>"
				+ "Alligator-morph pregnancies are a mix between mammalian and reptilian reproduction."
				+ " They are impregnated in much the same way as a human, but instead of giving birth to live young, a clutch of eggs will grow inside an alligator-girl's womb once she's been fertilised."
				+ " Pregnancy advances just like that of other common races, but when it comes time to give birth, the alligator-girl will seek out a safe place to lay her eggs."
				+ " After incubating her clutch for for approximately eighteen to twenty-four hours, the alligator-morph's young will hatch, with the resulting offspring being a mixture of alligator-morphs and offspring of their partner's race."
			+ "</p>",

			Colour.RACE_ALLIGATOR_MORPH,
			Genus.REPTILE,
			Disposition.NEUTRAL,
			StatusEffect.ALLIGATOR_MORPH,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.SPECIAL_ATTACK), new ListValue<Attack>(Attack.MAIN)),
			true,
			0.5f,
			1,
			4,
			Attribute.DAMAGE_ALLIGATOR_MORPH,
			Attribute.RESISTANCE_ALLIGATOR_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL),

//	// SLIME:
//	SLIME("slime", "slimes",
//			
//			"slime",
//			"slime",
//			
//			"slimes",
//			"slimes",
//			
//			// TODO Not accurate!
//			"<p>"
//				+ "Slimes are a humanoid race, able to morph their bodies into different shapes and sizes."
//				+ " Slimes are tolerated in Dominion, as they aren't aggressive and don't cause too many problems."
//				+ " They are typically found near bodies of water, as they require a steady source of liquid to stay healthy."
//				+ " Slimes can change their gender at will, though the vast majority choose to take on a female form, and seeing a male slime is a very rare occurrence."
//			+ "</p>",
//
//			"<p>"
//				+ "Slimes are quite stupid, and usually only care about obtaining other species' cum and milk."
//				+ " They will change their bodies to become as attractive as possible in order to attract a mate."
//				+ " Once a suitable mate has been found, slimes will be extremely eager to please them in any way they can, so they can feast on their sexual fluids."
//				+ " If they attract a female partner, they will encourage them to drink lactation-inducing liquids in order to get a full meal."
//			+ "</p>"
//
//			+ "<p>"
//				+ "Slimes can come in all shapes and sizes, as they are capable of morphing their bodies."
//				+ " Doing so requires some time and effort, so slimes usually stick to one appearance."
//				+ " Most slimes, however, will take on the form of a beautiful woman with massive breasts, as they prefer attracting male partners."
//				+ " If they attract an unwilling partner, slimes have no qualms about fighting, as they can't be hurt by any physical attacks."
//				+ " Their soft bodies are unable to inflict much damage on others though, so they will often resort to seducing their opponents into submission."
//			+ "</p>",
//
//			Colour.CLOTHING_WHITE,
//			Genus.SLIME,
//			Disposition.NEUTRAL,
//			StatusEffect.SLIME,
//			Util.newArrayListOfValues(new ListValue<Attack>(Attack.SEDUCTION)),
//			true,
//			0.5f,
//			1,
//			1,
//			Attribute.DAMAGE_HUMAN,
//			Attribute.RESISTANCE_HUMAN,
//			null,
//			null),

	// AVIAN:
	HARPY("harpy", "harpies",
			
			"harpy",
			"harpy",
			
			"harpies",
			"harpies",
			
			"<p>"
				+ "Harpies have human bodies and faces, but in place of arms, they have huge, feathered wings."
				+ " Their legs are similarly bird-like in appearance, and are covered in leathery, bird-like skin, with talons in place of feet."
				+ " In stark contrast to most mythological tales, harpies are exceptionally beautiful, and their feathers come in all sorts of brightly coloured varieties."
			+ "</p>",
			
			"<p>"
				+ "Although harpies live in Dominion, they're almost never seen walking down at street level."
				+ " Instead, most of the buildings in Dominion have had their rooves converted into 'harpy nests'."
				+ " These nests are large communal living spaces, where harpies in the same flock will live together."
				+ " Each nest will be controlled by the most attractive harpy in the flock, who is commonly referred to as the flock's matriarch."
			+ "</p>"
			+ "<p>"
				+ "Harpy society is both matriarchal and very insular, and can seem quite alarming to an outside observer."
				+ " All harpy society is based on femininity and beauty, and the more attractive a harpy is, the higher they are on the social ladder."
				+ " Any harpies that are not part of a flock will be mercilessly bulled and taunted by members of other flocks until they submit to the protection of a matriarch."
			+ "</p>"
			+ "<p>"
				+ "Each matriarch will have their own way for a harpy to show submission, but some of the more common demands involve public humiliation, or having to offer themselves as sexual relief for the rest of the flock."
				+ " Once the matriarch decides that their display of submission has been acceptable, the harpy will be accepted into the flock,"
					+ " although they will often continue to be treated as a servant or slave by any harpies that are more attractive than they are."
			+ "</p>"
			+ "<p>"
				+ "Most harpies, no matter if they're male or female, are gynephilic, with the odd exception being ambiphilic."
				+ " Due to this fact, male harpies do their best to look as feminine as possible, in order to appear attractive to the harpy females."
				+ " Despite their best efforts, however, male harpies are never as feminine as the females, which causes them to be treated as the lowest of the low, and will be mercilessly bullied by all females in the flock."
				+ " Harpy males seem to think that this arrangement is worthwhile, as if they do a good job of obeying their mistress's commands, they will sometimes be offered sex as a reward."
			+ "</p>"
			+ "<p>"
				+ "All harpies, both male and female, have a well-deserved reputation for being narcissists, and are only interested in being more attractive than the other harpies in their flock."
				+ " They will take extreme offence at being called ugly, and can quickly be driven into a blind rage by anyone doing so."
			+ "</p>"
			+ "<p>"
				+ "Harpy pregnancies are a mix between mammalian and avian reproduction."
				+ " They are impregnated in much the same way as a human, but instead of giving birth to live young, a clutch of eggs will grow inside a harpy's womb once she's been fertilised."
				+ " Pregnancy advances just like that of other common races, but when it comes time to give birth, the harpy will seek out a safe place to lay her eggs."
				+ " After laying is complete, the mother becomes fiercely protective of her clutch, and will refuse to sleep while she incubates her eggs for approximately eighteen to twenty-four hours."
				+ " Once the eggs finally hatch, the mother will usually collapse from exhaustion."
				+ " The resulting offspring will be a mixture of harpies and offspring of their partner's race."
			+ "</p>",
			
			Colour.RACE_HARPY,
			Genus.AVIAN,
			Disposition.NEUTRAL,
			StatusEffect.HARPY,
			Util.newArrayListOfValues(new ListValue<Attack>(Attack.SPECIAL_ATTACK), new ListValue<Attack>(Attack.SEDUCTION)),
			true,
			0.5f,
			3,
			4,
			Attribute.DAMAGE_HARPY,
			Attribute.RESISTANCE_HARPY,
			null,
			null);

	/*
	 * // INSECTS: BEE_MORPH("bee-morph",
	 * 
	 * "<p>Bee-morphs are one of the many demi-human races now populating the world."
	 * +
	 * " The vast majority of bee-morphs live outside of society in huge hives, consisting of up to 200 individuals."
	 * +
	 * " Each hive is governed by a single King or Queen bee, who commands the complete loyalty of the hive's members."
	 * +
	 * " The hive is constructed and guarded by the hive's bee-boys, who will aggressively attack anyone who strays too close.</p>"
	 * 
	 * +
	 * "<p>Bee-girls are responsible for gathering nectar, which they do by fucking the many phallic demonic plants."
	 * +
	 * " Their body then transforms the nectar into honey-milk, and as it does so, the bee-girl grows incredibly horny."
	 * +
	 * " When her abdomen is swollen with honey-milk, she will return to her hive to be fucked, secreting the sweet liquid from her breasts and pussy.</p>"
	 * 
	 * +
	 * "<p>Bee-morphs are only aggressive when defending their hive, and will try to avoid contact with strangers wherever possible."
	 * +
	 * " If forced into a fight, they will not hesitate to use the powerful stinger found on the end of their abdomen, the venom of which"
	 * +
	 * " will drive a person into a state of uncontrollable lust. After victory, a female bee-morph will"
	 * +
	 * " use the opportunity to deposit her eggs in the attacker, using an ovipositor located in their abdomen.</p>"
	 * ,
	 * 
	 * "Bee-boys are very rarely seen away from their hive.",
	 * 
	 * "Bee-girls can commonly be found gathering nectar near their hive.",
	 * 
	 * RacialBody.BEE_MORPH, Genus.INSECT, Disposition.NEUTRAL,
	 * StatusEffect.BEE_MORPH, Utilities.newArrayListOfValues(new
	 * ListValue<Attack>(Attack.SPECIAL_ATTACK), new
	 * ListValue<Attack>(Attack.MELEE), new
	 * ListValue<Attack>(Attack.SEDUCTION)), false), ROYAL_BEE("royal bee",
	 * 
	 * "<p>Royal bees are a humanoid monster race, and are referred to as Queen or King bees."
	 * +
	 * " A Royal bee can be found leading a colony of bee-morphs, and they almost never leave the safety of their hive."
	 * +
	 * " Their responsibility is to keep the hive functioning, and they command the complete loyalty and devotion of their colony."
	 * +
	 * " They spend most of their time seeing to the needs of the hive's bee-girls, and along with the bee-boys, fuck and pleasure the bee-girls in order to collect their honey-milk."
	 * +
	 * " Royal bees are able to convert honey-milk into royal jelly in their abdomens, which they are able to excrete as a thick, sticky cum.</p>"
	 * 
	 * +
	 * "<p>Royal bees are created by transforming a bee-morph through use of Royal jelly."
	 * +
	 * " When a hive grows too large for one Royal bee to manage, a bee-morph is chosen to be transformed into a new Queen or King bee."
	 * +
	 * " The hive's current Queen or King will mate with the chosen bee-morph, forcing them to drink their Royal jelly."
	 * +
	 * " This causes the chosen bee-morph to transform into a new Royal bee, who will then leave the hive, taking about a third of the colony's members with them.</p>"
	 * 
	 * +
	 * "<p>Royal bees almost never venture out of their hives, but will attack anyone that manages to break through the colony's bee-boy guards."
	 * +
	 * " Those that are foolish enough to enter an active bee hive are never heard from again. It has been speculated that such intruders are forcefully"
	 * + " transformed into another member of the hive.</p>",
	 * 
	 * "King bees, like all royal bees, will never leave their hive.",
	 * 
	 * "Queen bees, like all royal bees, will never leave their hive.",
	 * 
	 * RacialBody.ROYAL_BEE, Genus.INSECT, Disposition.SAVAGE,
	 * StatusEffect.ROYAL_BEE, Utilities.newArrayListOfValues(new
	 * ListValue<Attack>(Attack.SPECIAL_ATTACK), new
	 * ListValue<Attack>(Attack.MELEE), new
	 * ListValue<Attack>(Attack.SEDUCTION)), false),
	 */

	/*
	 * TIGER_MORPH("tiger-morph",
	 * 
	 * "<p>Tiger-morphs are a dangerous humanoid monster race, primarily found in the demonic jungle."
	 * +
	 * " Tiger-morphs are extremely aggressive, and as a result have been driven out of Dominion's society."
	 * +
	 * " They spend most of their lives as solitary hunters, and roam their territory looking for individuals to prey upon."
	 * +
	 * " Once a suitable target is found, the tiger-morph will stalk them, remaining concealed until the perfect moment to pounce."
	 * +
	 * " Any unfortunate person falling victim to a tiger-morph will not only be robbed, but will often also be subjected to extremely dominant and humiliating rape.</p>"
	 * 
	 * +
	 * "<p>Tiger-morphs are always willing to assert their dominance through sex, but females will often prefer to force their prey to pleasure them orally,"
	 * +
	 * " refusing to let them penetrate her. When a magic storm hits, tiger-morphs become extremely promiscuous, and will go on a tireless lust-fuelled rampage,"
	 * + " the females now mating with whatever they can find.</p>",
	 * 
	 * "Tiger-boys will humiliate and rape their prey.",
	 * 
	 * "Tiger-girls will force their prey to pleasure them orally.",
	 * 
	 * RacialBody.TIGER_MORPH, Genus.FELINE, Disposition.SAVAGE,
	 * StatusEffect.TIGER_MORPH, Utilities.newArrayListOfValues(new
	 * ListValue<Attack>(Attack.SPECIAL_ATTACK), new
	 * ListValue<Attack>(Attack.MELEE), new
	 * ListValue<Attack>(Attack.SEDUCTION)), true),
	 */

	/*
	 * CENTAUR("centaur",
	 * 
	 * "<p>Centaurs are an unpredictable monster race that is rarely seen in Dominion."
	 * +
	 * " Centaurs are usually quite civilized, but they can get extremely territorial, and as a result they don't feel comfortable living in Dominion."
	 * +
	 * " Outside of Dominion, centaurs group up to form herds of up to fifty individuals, although they are rarely all together at once,"
	 * + " instead wandering their territory in search of intruders.</p>"
	 * 
	 * +
	 * "<p>There will usually only be a handful of males in the herd, who are responsible for taking decisions on behalf of the entire herd."
	 * +
	 * " All the females are part of their harem, and are happy to submit to being bred whenever the males feel like using them."
	 * +
	 * " Centaurs consider horse-morphs as having failed to reach their full potential, and each race strongly dislikes the other.</p>"
	 * 
	 * +
	 * "<p>Centaurs are very aggressive towards intruders, and will attack anyone they consider to be encroaching on their territory."
	 * +
	 * " When outside of their territory, they are quite civilized, and will prefer to avoid conflict where possible.</p>"
	 * 
	 * +
	 * "<p>Male centaurs will try and impregnate anything they can catch on their territory, while the females will only want to breed with their herd's leaders."
	 * +
	 * " Centaurs get extremely affected by magic-storms, and females will sink into an extremely powerful heat, desperately seeking anyone and anything to fuck"
	 * +
	 * " her pregnant. Similarly, males will enter a powerful rut, and while under the storm's effects, their only concern is breeding as many people"
	 * + " as they can catch.</p>",
	 * 
	 * "Male centaurs are extremely dominant, and will attack anyone on their territory."
	 * ,
	 * 
	 * "Female centaurs are slightly more forgiving than their male counterparts, but will not hesitate to fight any intruders."
	 * ,
	 * 
	 * RacialBody.CENTAUR, Genus.EQUINE, Disposition.UNPREDICTABLE,
	 * StatusEffect.CENTAUR, Utilities.newArrayListOfValues(new
	 * ListValue<Attack>(Attack.SPECIAL_ATTACK), new
	 * ListValue<Attack>(Attack.MELEE)), true),
	 */

	/*
	 * SLIME_QUEEN("slime queen",
	 * 
	 * "<p>A slime queen is a dangerous monster, having lost themselves to the desire for more cum."
	 * +
	 * " Slime queens will seek to absorb other slimes into their bodies, breaking their minds and forcing them to become a part of her."
	 * +
	 * " Due to this threat, normal slimes will group together to drive off slime queens, and as a result, slime queens are typically found isolated near bodies of water they have claimed as their home."
	 * +
	 * " Whereas normal slimes are quite stupid, the slime queen is able to harness the intelligence of all the slimes she has absorbed, making her a formidable threat."
	 * +
	 * "A slime queen will wander from her home to hunt down any individual she can find."
	 * +
	 * " Once she has found a victim, she will attempt to smother and seduce her prey and then feed off of them."
	 * +
	 * " Slime queens represent a threat to all races, and as a result Lilith's enforcers have driven them out of Dominion.</p>"
	 * 
	 * +
	 * "<p>A slime queen looks like a group of slimes all huddling together, with a particularly attractive individual at the centre."
	 * +
	 * " This is an illusion however, and a closer inspection will reveal all the slimes are linked together into one slime queen."
	 * +
	 * " Each outer slime will take on the appearance of the slime that the queen absorbed, although none are permitted to be more attractive than the queen herself.</p>"
	 * ,
	 * 
	 * "Slime queens are only ever female in appearance.",
	 * 
	 * "Slime queens are only ever female in appearance.",
	 * 
	 * RacialBody.SLIME_QUEEN, Genus.SLIME, Disposition.SAVAGE,
	 * StatusEffect.SLIME_QUEEN, Utilities.newArrayListOfValues(new
	 * ListValue<Attack>(Attack.SPECIAL_ATTACK), new
	 * ListValue<Attack>(Attack.SEDUCTION)), false),
	 */

	/*
	 * TENGU("tengu", INACCURATE
	 * "<p>Tengus are a female-only humanoid monster race." +
	 * " Unlike other monster races, tengus have not been driven out of Dominion society, but instead actively choose to live in solitude."
	 * +
	 * " They are, without exception, extremely intelligent, and have an excellent grasp of magic."
	 * +
	 * " They make their nests as high as they can, and try to avoid contact with all other races."
	 * +
	 * " When they are forced to interact with others, tengus display remarkable understanding and kindness, and will almost always avoid conflict where possible.</p>"
	 * ,
	 * 
	 * "Tengu males look like females, but are less attractive and have a small cock."
	 * ,
	 * 
	 * "Tengu females are extremely attractive and only really care about themselves."
	 * ,
	 * 
	 * RacialBody.TENGU, Genus.AVIAN, Disposition.UNPREDICTABLE,
	 * StatusEffect.TENGU, Utilities.newArrayListOfValues(new
	 * ListValue<Attack>(Attack.SPECIAL_ATTACK), new
	 * ListValue<Attack>(Attack.SEDUCTION)), false);
	 */

	// TODO:
	/*
	 * NOT DONE
	 * -------------------------------------------------------------------------
	 * ------------------------------- // AQUATIC: SHARK_MORPH("shark morph",
	 * "",
	 * 
	 * "Shark morphs are a humanoid race most commonly found in the city of Dominion."
	 * +
	 * " Shark morphs are typically very dominant, but can control their aggression."
	 * +
	 * " They like hanging out around bodies of water, as there is almost nothing a shark morph likes more than swimming."
	 * +
	 * " When a shark morph sees something they like, they will do anything it takes to get it."
	 * +
	 * " Male shark morphs will attempt to build a harem of females, having to beat each female he wants to claim in a fight in order to show his dominance."
	 * ,
	 * 
	 * "Shark morphs are humanoid, with a human body and face." +
	 * " Their legs and arms are covered in smooth grey skin, ending at the thigh and upper arm."
	 * +
	 * " A huge shark-like tail grows from above their ass, allowing them to swim at incredible speeds."
	 * + " All shark morphs are incredibly fit from all the swimming they do."
	 * + " Shark morphs have sharp pointed teeth that they can retract at will."
	 * +
	 * " Females typically have very small breasts, streamlining them for swimming."
	 * + " Males have a shark-like cock.",
	 * 
	 * "Shark morphs are easy to provoke, and are always eager to fight." +
	 * " They typically do not know any magic, and will prefer fighting using brute force."
	 * ,
	 * 
	 * "Female shark morphs will typically be part of a harem, being claimed by a strong male."
	 * +
	 * " Females in a harem will only mate with their leader, unless defeated in combat by another individual."
	 * +
	 * " Each shark morph pregnancy will result in up to three shark morphs, which will rapidly reach full maturity and leave to make their own way in Dominion."
	 * ,
	 * 
	 * RacialBody.SHARK_MORPH, Genus.AQUATIC, Disposition.CIVILIZED,
	 * StatusEffect.SHARK_MORPH, false, false), TIGER_SHARK("tiger shark", "",
	 * 
	 * "Tiger sharks are a humanoid monster race." +
	 * " Tiger sharks are extremely dominant and aggressive, and will attack almost anything on sight."
	 * + " As a result, Tiger sharks have been driven out of Dominion society."
	 * +
	 * " Like shark morphs, they like hanging out around bodies of water, as they love swimming."
	 * +
	 * " They will sometimes lurk just beneath the water, waiting for a clueless passer-by to ambush."
	 * +
	 * " If a harem of hark morphs are foolish enough to stray from the safety of Dominion, a male Tiger shark will seek to defeat their leader and mate with all the harem's females."
	 * +
	 * " The Tiger shark will also typically forcefully transform the harem's male leader into a female shark morph before breeding her."
	 * ,
	 * 
	 * "Tiger sharks are humanoid monsters, with smooth shark-like skin covering their entire bodies."
	 * +
	 * " Their stomach and breasts are a creamy-white, while the rest of their skin is grey with dark-grey stripes, like a tiger."
	 * +
	 * " A huge shark-like tail grows from above their ass, allowing them to swim at incredible speeds."
	 * +
	 * " They have an anthropomorphic shark-like face, with sharp pointed teeth that they can retract at will."
	 * +
	 * " Females typically have very small breasts, streamlining them for swimming."
	 * + " Males have a huge shark-like cock.",
	 * 
	 * "Tiger sharks will attack almost anything they see." +
	 * " They typically do not know any magic, and will prefer fighting using brute force."
	 * ,
	 * 
	 * "Female Tiger sharks will seek to breed with any strong male they can catch."
	 * + " Males will likewise seek to impregnate any female they see." +
	 * " Each Tiger shark pregnancy will result in up to three Tiger sharks, which will rapidly reach full maturity and leave to find a body of water to claim as their own."
	 * ,
	 * 
	 * RacialBody.TIGER_SHARK, Genus.AQUATIC, Disposition.SAVAGE,
	 * StatusEffect.TIGER_SHARK, true, false);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * MOUSE_MORPH("mouse morph",
	 * 
	 * "Mouse-morphs are one of the most common races inhabiting Dominion." +
	 * "Usually found in colonies numbering 10-20 individuals, mouse-morphs typically inhabit basements or burrows that they have dug out themselves."
	 * +
	 * "Mouse-morphs do everything as a cohesive unit with their colony, be it eating, exploring or fucking."
	 * +
	 * " Mouse-morphs are typically quite shy, preferring to avoid any sort of interaction with strangers that isn't completely necessary."
	 * +
	 * "A colony of mouse-morphs will have no clear leader, but work together as a whole to get things done."
	 * ,
	 * 
	 * "The average mouse-morph is about 5' tall, and possesses youthful features and a lithe frame."
	 * +
	 * " Like the other morphs inhabiting Dominion, their face and body remain human-looking."
	 * +
	 * "A pair of mouse-like ears and a thin tail are the extremities that betray their race on first glance."
	 * +
	 * "The front teeth of most mouse-morphs are slightly larger than an average human's, although not by much."
	 * +
	 * "Their arms and legs are very delicate, especially on females, and have a soft layer of fur running up to their elbows and inner-thighs."
	 * +
	 * "The hands and feet are humanoid, but with little claws on the end of each digit, and the undersides have pads."
	 * ,
	 * 
	 * "Mouse-morphs are very timid, and will usually try to run from combat when able."
	 * + "They are completely unable to harness any demon magic." +
	 * "If forced to fight, a mouse-morph will usually rely on presenting themselves as a submissive sex-toy, hoping to fog the enemies' mind with lust."
	 * ,
	 * 
	 * "Mouse-morphs, along with rat-morphs, are the most prolific breeders in Dominion."
	 * +
	 * "Colonies of mouse-morphs are constantly engaged in sexual intercourse, trying to impregnate their friends."
	 * +
	 * "If an individual finds a partner outside of their colony, they will be expected to bring them back to the colony so everyone can try and breed their new partner."
	 * ,
	 * 
	 * RacialBody.MOUSE_MORPH, Genus.RODENT, Disposition.CIVILIZED,
	 * StatusEffect.MOUSE_MORPH),
	 * 
	 * RAT_MORPH("rat morph",
	 * 
	 * "Rat-morphs are the larger and more aggressive cousins of the mouse-morph."
	 * +
	 * " Found primarily in the undercity of Submission, a rat-morph is a rare sight on the surface."
	 * +
	 * " Due to their corrupted and bestial nature, rat-morphs are considered one of the dangerous races."
	 * +
	 * " Despite this, they are the weakest of the dangerous races, and are only a threat when hunting in groups."
	 * +
	 * "Rat-morphs are aggressive, territorial and cunning, and all they ever seem to think about is breeding."
	 * +
	 * " Rat-morphs have a tendency to form gangs around a strong leader, and will submit to their commands for as long as they remain strong."
	 * +
	 * " The surface dwellers of Dominion do not tolerate their presence, and as such, rat-morphs are found almost exclusively in the undercity of Submission."
	 * +
	 * " Occasionally, a gang of rat-morphs will venture to the surface, looking for isolated individuals to overwhelm and rape."
	 * +
	 * " These unlucky victims will be claimed by the leader first, with the rest of the gang soon to follow."
	 * ,
	 * 
	 * "A rat-morph usually stands at around 6\", with gang leaders typically being slightly taller."
	 * +
	 * " Rat-morphs are covered in a layer of hair, which thins out on their stomach and chest."
	 * +
	 * " Their feet and hands, although humanoid, end in little claws, and their palms and soles have soft pads."
	 * +
	 * " They possess a thick rat-like tail and their faces and ears are similarly morphed into the look of a rat."
	 * + " Females have three pairs of breasts." +
	 * " The average male cock size is 10\".",
	 * 
	 * "Alone, a rat-morph is cowardly, and will likely try to avoid combat." +
	 * " If backed into a corner, however, even a solo rat-morph will fight ferociously."
	 * +
	 * " Most of the time, rat-morphs will be with their gang, and will happily seek out individuals of any species to overwhelm and rape."
	 * +
	 * " Rat-morphs cannot use magic, and will instead rely on physical attacks."
	 * ,
	 * 
	 * "Rat-morphs, along with mouse-morphs, are the most prolific breeders." +
	 * "Gangs of rat-morphs are constantly engaged in sexual intercourse, trying to impregnate each other."
	 * +
	 * "Once they grow tired of fucking one another, the gang will seek out other rat-morph gangs to fight and fuck, and will sometimes even venture to the surface to breed an unlucky citizen."
	 * ,
	 * 
	 * RacialBody.RAT_MORPH, Genus.RODENT, Disposition.UNPREDICTABLE,
	 * StatusEffect.RAT_MORPH),
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // They bully bee-girls and drink their honey-milk WASP_MORPH(
	 * "wasp morph",
	 * 
	 * "Wasp morphs are aggressive monsters that inhabit the jungle." +
	 * " Unlike bee morphs, wasp morphs are exclusively male, except for the colonies' leader, which is female."
	 * +
	 * " Wasp morphs are regarded as annoying pests by every other race, and as such are driven out of Dominion and into the jungle."
	 * +
	 * " Each wasp morph is part of a colony, which will consist of up to fifty individuals, all led by a female queen."
	 * +
	 * " Wasp morphs will typically attack others on sight, but will sometimes avoid individuals that look too strong to fight."
	 * ,
	 * 
	 * "Wasp morphs are humanoid monsters, covered in yellow and black chitin."
	 * +
	 * " They have a humanoid face, but have large insect-like eyes and mandibles on either side of their otherwise normal mouth."
	 * +
	 * " The most notable feature of a wasp morph is the huge yellow-and-black abdomen sprouting from above their ass."
	 * +
	 * " A pair of antennae grow from underneath their hair, which is invariably a shiny yellow and black."
	 * + " Wasp morphs have a shiny black penis, typically 12\" long.",
	 * 
	 * "Like bee morphs, wasp morphs are unable to use magic, though the queen is able to use some spells."
	 * +
	 * " Wasp morphs will always use physical attacks to attempt to beat down their opponents."
	 * +
	 * " Their abdomen gives them the ability to deliver a powerful sting, which delivers a pain-inducing venom to their victims."
	 * ,
	 * 
	 * "Wasp morphs reproduce by willing being filled with eggs from their queen by use of her ovipositor."
	 * +
	 * " The queen fertilises her eggs by selecting members of the colony to fuck."
	 * +
	 * " If anyone is foolish enough to trespass into the colonies' hive, the queen will invariably forcefully transform the trespasser into a wasp morph drone."
	 * ,
	 * 
	 * RacialBody.WASP_MORPH, Genus.INSECT, Disposition.SAVAGE,
	 * StatusEffect.WASP_MORPH),
	 * 
	 * 
	 * 
	 * IMP("imp",
	 * 
	 * "Imps are a small, mischievous, tribal race, found primarily in the subterranean city of Submission."
	 * +
	 * " Despite their short stature, Imps are birthed fully mature, and inherit the calibre of intelligence from their mother."
	 * +
	 * " Imps are exclusively male, and form tribes based on their birthing heritage."
	 * +
	 * " Each tribe is led by an Alpha Imp, who organises his tribe in order to fight other Imp tribes."
	 * +
	 * " Once an Imp defeats another in combat, the victor will rape the loser, using demonic magic to absorb them into their cocks."
	 * +
	 * " In this way, it is easy to see the most formidable fighters, as they will have gigantic cocks."
	 * +
	 * " When Imp tribes have no other Imps to fight, they will venture to the surface, looking for individuals to ambush."
	 * +
	 * " When they have found and overwhelmed a victim, they will forcefully transform them into a bimbo slut, before taking them back to their lair to use as a sex slave."
	 * +
	 * " Due to this activity, they are outlawed from setting foot in Dominion, being forced to live in the undercity of Submission."
	 * ,
	 * 
	 * "Imps are humanoids, and standing at roughly 2\" tall, they resemble a smaller version of the demons."
	 * +
	 * " Skin colour varies from Imp to Imp, with the weakest and smallest-endowed being a light lilac, turning to a dark shade of red as they absorb more Imps."
	 * +
	 * " Imps have a humanoid face, with a pair of small horns breaking out of their forehead."
	 * +
	 * " A small, spaded tail grows from above their ass, and a pair of wings grow from their upper back, allowing them to fly with some effort."
	 * +
	 * " Imps have a demonic penis, the size of which depends on how many other Imps they have absorbed."
	 * ,
	 * 
	 * "Individual Imps will try and avoid fighting other creatures, but when in groups of two or more, will grow bold enough to attack isolated individuals."
	 * +
	 * " Imps are capable of using demonic magic, though their usage of spells is typically limited to absorbing other Imps and casting minor arousal spells."
	 * +
	 * " Imps are very weak, and when fighting anything other than Imps, will rely on seduction and spells."
	 * ,
	 * 
	 * "Imps are birthed after a demon impregnates their partner when in Incubus form."
	 * +
	 * " Imps are unable to impregnate others, and instead only use their cocks for absorbing other Imps and for personal pleasure."
	 * +
	 * " The cum of an Imp is extremely corruptive, and Imps are able to break the mind of individuals by repeatedly raping them."
	 * +
	 * " Such unfortunate creatures are then turned into a tribe's willing sex slave, being used over and over by their new owners."
	 * ,
	 * 
	 * RacialBody.IMP, Genus.DEMON, Disposition.NEUTRAL, StatusEffect.IMP),
	 * IMP_ALPHA("alpha imp", "Alpha Imps are the leaders of each Imp tribe." +
	 * " Although physically the same size as a regular Imp, the Alpha Imp will have the largest cock in his tribe, showing that he has defeated the most Imps."
	 * +
	 * " The Alpha Imp organises his tribe to fight other Imp tribes, and when there are none to fight, will lead his tribe to the surface, looking for individuals to ambush."
	 * +
	 * " When the Alpha's tribe has found and overwhelmed a victim, he will get the best position in the gang rape that follows."
	 * +
	 * " The Alpha's cum has the strongest corruptive taint of his tribe, and by cumming in the vagina or ass of the victim, he will ensure that their mind is quickly broken."
	 * +
	 * " The Alpha will then enslave the broken person, giving them a new name and taking them back to their lair as a sex slave."
	 * +
	 * " Thankfully for the residents of Dominion, this is a very rare occurrence, as the punishment for an Imp setting foot in Dominion is being transformed into a statue by the nearest demon."
	 * ,
	 * 
	 * "An Alpha Imp is exactly the same as a regular Imp, albeit with a larger cock."
	 * +
	 * " Skin colour varies from Imp to Imp, but an Alpha Imp's skin tends to be dark red."
	 * +
	 * " Imps have a humanoid face, with a pair of small horns breaking out of their forehead."
	 * +
	 * " A small, spaded tail grows from above their ass, and a pair of wings grow from their upper back, allowing them to fly with some effort."
	 * ,
	 * 
	 * "The Alpha Imp is seldom seen isolated from his tribe, and as a result is always bold enough to attack isolated individuals."
	 * +
	 * " The Alpha is capable of using  more powerful magic than a standard Imp, being able to absorb other Imps and cast moderate arousal spells."
	 * +
	 * " The Alpha Imps is still very weak, and when fighting anything other than Imps, will rely on seduction and spells."
	 * ,
	 * 
	 * "An Alpha Imps is created once an Imp in a certain tribe has grow their cock to be larger than the current Alpha."
	 * +
	 * " That Imp will then challenge the Alpha to combat, with the loser being absorbed into the winning Alpha's cock."
	 * +
	 * " Alpha Imps are unable to impregnate others, and instead only use their cocks for absorbing other Imps and for personal pleasure."
	 * +
	 * " The cum of an Alpha Imp is even more corruptive than a regular Imp's, and an Alpha is sometimes even able to break an individual's mind by themselves."
	 * +
	 * " Such unfortunate creatures are then turned into a tribe's willing sex slave, being used over and over by their new owners."
	 * ,
	 * 
	 * RacialBody.IMP, Genus.DEMON, Disposition.SAVAGE, StatusEffect.IMP),
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * LIZARD_MORPH("lizard morph",
	 * 
	 * "Lizard morphs are a humanoid race most commonly found in the city of Dominion."
	 * +
	 * " Lizard morphs are typically very laid back, and are one of the most docile races of Dominion."
	 * +
	 * " They enjoy heat, and will spend a large portion of the day sunbathing."
	 * ,
	 * 
	 * "Lizard morphs are humanoid, with a human body and face." +
	 * " Their legs and arms are covered in scaly skin, ending at the thigh and upper arm."
	 * + " A large lizard-like tail grows from above their ass." +
	 * " Females typically have small breasts." +
	 * " Males have a lizard-like cock.",
	 * 
	 * "Lizard morphs are slow to anger, but will defend themselves from an attacker."
	 * +
	 * " They typically do not know any magic, and will prefer fighting using brute force or seduction."
	 * ,
	 * 
	 * " Lizard morphs breed by laying eggs, that can be fertilised by any race."
	 * +
	 * " Once the eggs hatch, the new Lizard morphs will rapidly reach full maturity and leave to make their own way in Dominion."
	 * ,
	 * 
	 * RacialBody.LIZARD_MORPH, Genus.REPTILE, Disposition.CIVILIZED,
	 * StatusEffect.LIZARD_MORPH), LAMIA("lamia",
	 * 
	 * "Lamias are a monster found everywhere except Dominion." +
	 * " Lamias are typically very solitary, and the only time they gather is for reproduction."
	 * +
	 * " They are very dangerous and aggressive, and will attack travellers unprovoked."
	 * +
	 * " Lamias are known for using hypnotism to force their prey to do whatever they want."
	 * + " Due to this behaviour, Lamias have been forced out of Dominion.",
	 * 
	 * "Lamias are humanoid/animal monsters, having a normal-looking Lizard morph upper body, but instead of legs and a tail they possess the lower body of a snake."
	 * ,
	 * 
	 * "Lamias are very aggressive, and will attack anyone they consider to be encroaching on their territory."
	 * +
	 * " They typically know some spells related to mind control, and will prefer to use these and seduction rather than brute force."
	 * ,
	 * 
	 * "Female Lamias will use their prey to breed with, forcing them to cum on demand by using hypnotism."
	 * + " Males will try to impregnate anyone they catch.",
	 * 
	 * RacialBody.LAMIA, Genus.REPTILE, Disposition.CIVILIZED,
	 * StatusEffect.LAMIA),
	 * 
	 * 
	 * 
	 * FOX_MORPH("fox morph",
	 * 
	 * "Vulpines are a humanoid race most commonly found in the city of Dominion."
	 * +
	 * " Vulpines are very cunning and mischievous, and love nothing more than playing practical jokes on others."
	 * +
	 * " They often seem insensitive to other's feeling because of this, though to them it's just a bit of fun."
	 * +
	 * " Vulpines make friends easily, and are one of the most common races in Dominion."
	 * ,
	 * 
	 * "Vulpines are humanoid, with a human body and face." +
	 * " Their legs and arms are covered in dark orange fur, ending at the thigh and upper arm."
	 * + " A large fox-like bushy tail grows from above their ass." +
	 * " Vulpines have a pair of fox-like ears." +
	 * " Females typically have three pairs of average sized breasts." +
	 * " Males have a dog-like cock.",
	 * 
	 * "Vulpines are not aggressive, but will defend themselves when attacked."
	 * +
	 * " They typically know a small amount of magic, which they primarily use to play tricks on others."
	 * +
	 * "When forced into confrontation, Vulpines will typically use magic or seduction."
	 * ,
	 * 
	 * " Vulpines will breed with any other race." +
	 * " A Vulpine pregnancy results in up to 4 Vulpines, which will rapidly reach full maturity and leave to make their own way in Dominion."
	 * ,
	 * 
	 * RacialBody.VULPINE, Genus.VULPINE, Disposition.CIVILIZED,
	 * StatusEffect.FOX_MORPH), KITSUNE("kitsune",
	 * 
	 * "Kitsunes are an exclusively female humanoid monster race found everywhere."
	 * +
	 * " Like Vulpines, Kitsunes are very cunning and mischievous, and love nothing more than playing practical jokes on others."
	 * +
	 * " They have a strong mastery of illusion-based magic, which they use to play pranks on others."
	 * +
	 * " They are more aggressive than Vulpines, and their practical jokes will typically go too far."
	 * +
	 * " Due to this, there have been many attempts to force Kitsunes out of Dominion, but die to their strong illusion magic, all efforts have so far been in vain."
	 * ,
	 * 
	 * "Vulpines are humanoid monsters, covered in fur that ranges from dark orange to pure white."
	 * +
	 * " They have digitigrade legs, and their hands and feet have leathery pads, with each digit ending in little claws."
	 * +
	 * " Kitsunes can have up to nine fox-like bushy tails growing from above their ass."
	 * +
	 * " The number of tails they have is typically a good indication of their mastery of illusion magic."
	 * +
	 * " Their faces are of an anthropomorphic fox-like appearance, and they have a pair of fox-like ears."
	 * + " Kitsunes have three pairs of large breasts.",
	 * 
	 * "Kitsunes are not too aggressive, but will sometimes decide to attack individuals."
	 * +
	 * " They know a large amount amount of magic, which is limited to illusion-based spells."
	 * + "When fighting, Kitsunes will typically use magic or seduction.",
	 * 
	 * " Kitsunes will only rarely seek to breed with other races, instead inflicting orgasm-denial on their defeated foes."
	 * +
	 * " A Kitsune pregnancy results in up to 4 Kitsunes, which will rapidly reach full maturity and leave to make their own way."
	 * ,
	 * 
	 * RacialBody.KITSUNE, Genus.VULPINE, Disposition.NEUTRAL,
	 * StatusEffect.KITSUNE),
	 * 
	 * 
	 * 
	 * COW_MORPH("cow morph",
	 * 
	 * "Bovines are a humanoid race most commonly found in the city of Dominion."
	 * +
	 * " Bovines are quite stupid and the females are very submissive, and will happily do whatever their friends tell them to do."
	 * +
	 * " They are mostly known for their females having gigantic breasts and heavy lactation."
	 * +
	 * " Due to this, they are often the target of unwanted attention from the other races."
	 * +
	 * " Bovines love to have their breasts milked, and prefer their sexual partners to pay attention to their breasts."
	 * ,
	 * 
	 * "Bovines are humanoid, with a human body and face." +
	 * " Their legs and arms are covered in a fine layer of fur, ending at the thigh and upper arm."
	 * + " Their feet are shaped into hooves." +
	 * " A cow-like tail grows from above their ass." +
	 * " They have a pair of cow-like ears and a pair of horns, with males having considerable larger horns than females."
	 * +
	 * " Females typically have three pairs of gigantic breasts, with multiple nipples on each."
	 * + " Males have a horse-like cock.",
	 * 
	 * "Female bovines are extremely passive, and will usually submit when attacked."
	 * + " Males, however, will defend themselves." +
	 * " They are completely unable to use magic." +
	 * "Males will use physical attacks in combat, and if a female decides to fight, she will use seduction."
	 * ,
	 * 
	 * " Bovines will breed with any other race." +
	 * " A Bovine pregnancy results in a pair of Bovines, which will rapidly reach full maturity and leave to make their own way in Dominion."
	 * ,
	 * 
	 * RacialBody.BOVINE, Genus.BOVINE, Disposition.CIVILIZED,
	 * StatusEffect.COW_MORPH),
	 * 
	 * MINOTAUR("minotaur",
	 * 
	 * "Minotaurs are an exclusively male humanoid monster race." +
	 * " Minotaurs are extremely dominant and aggressive, and will attack almost anything on sight."
	 * + " As a result, Minotaurs have been driven out of Dominion society." +
	 * " A minotaur will usually have a herd of submissive Bovines following him everywhere."
	 * +
	 * " Minotaurs show a special interest in free Bovines, and after raping them, will force them to join his herd."
	 * +
	 * " The minotaur will then forcefully transform the Bovine into a hyper-breasted submissive fuck toy."
	 * ,
	 * 
	 * "Minotaurs are humanoid monsters, with a fine layer of fur covering their entire bodies."
	 * + " Their feet are shaped into hooves and their legs are digitigrade." +
	 * " A cow-like tail grows from above their ass." +
	 * " They have a pair of cow-like ears and a pair of huge horns." +
	 * " They have anthropomorphic cow-like faces." +
	 * " They have a gigantic horse-like cock.",
	 * 
	 * "Minotaurs will attack almost anything they see." +
	 * " They are unable to learn magic, and will always fight using brute force."
	 * ,
	 * 
	 * "Minotaurs will breed with anything they can catch." +
	 * " Each Minotaur pregnancy will result in a single Minotaur, which will rapidly reach full maturity and leave to find a herd of his own."
	 * ,
	 * 
	 * RacialBody.MINOTAUR, Genus.BOVINE, Disposition.UNPREDICTABLE,
	 * StatusEffect.MINOTAUR),
	 * 
	 * 
	 * 
	 * SPIDER_MORPH("spider morph",
	 * 
	 * "Spider morphs are a humanoid race most commonly found in the city of Dominion."
	 * +
	 * " Spider morphs are a sly, tricky race, and have come close the being driven out of Dominion on several occasions due to their predatory ways."
	 * + " They like to spin webs and trick people into them." +
	 * " Once caught, they will inject their victims with venom, causing them to become filled with lust."
	 * + " They will then engage in bondage sex using their webs.",
	 * 
	 * "Spider morphs are humanoid, with a human body and face." +
	 * " Their legs and arms are covered in a black chitin, ending at the thigh and upper arm."
	 * +
	 * " The most notable feature of a spider morph is the huge spider-like abdomen sprouting from above their ass."
	 * +
	 * " They have a smaller pair of eyes above their normal eyes, which are typically jet black."
	 * ,
	 * 
	 * "Spider morphs are unable to use magic." +
	 * " Spider morphs will typically vary between physical attacks and attempting to seduce their opponents."
	 * +
	 * " Their spider-like abdomen gives them the ability to shoot webs at their opponents."
	 * +
	 * " They also have fangs that inject a lust-inducting venom into their prey."
	 * ,
	 * 
	 * "Spider morphs reproduce by depositing eggs in a target host by use of an ovipositor."
	 * +
	 * " They will not lay their eggs in anyone without permission, but after being injected with venom, not many partners refuse."
	 * ,
	 * 
	 * RacialBody.SPIDER_MORPH, Genus.ARACHNID, Disposition.CIVILIZED,
	 * StatusEffect.SPIDER_MORPH),
	 * 
	 * ARACHNE("arachne",
	 * 
	 * "Arachne are a monster race found in the jungle and ruins." +
	 * " Arachne are very aggressive and territorial, and as a result have been driven out of Dominion's society."
	 * +
	 * " They lurk in dark places in the jungle and ruins, waiting for passersby to ensnare in their webs."
	 * + " After catching their prey, Arachne will use them for breeding.",
	 * 
	 * "Arachne are humanoid/animal monsters, having a normal-looking spider morph upper body, but instead of legs they possess the body of a spider."
	 * ,
	 * 
	 * "Arachne are very aggressive, and will prey on anyone passing near their web."
	 * +
	 * " Arachne will know some spells, and will prefer fighting using magic and seduction."
	 * ,
	 * 
	 * "Male Arachne will fuck anything they catch, and will keep females bound in their webs for days, continually breeding them before growing bored and dumping them in the wilderness."
	 * +
	 * " Females will deposit their eggs in whoever they catch, using their prey's cum to fertilise them beforehand."
	 * ,
	 * 
	 * RacialBody.ARACHNE, Genus.ARACHNID, Disposition.SAVAGE,
	 * StatusEffect.ARACHNE),
	 * 
	 * 
	 * WYVERN("wyvern",
	 * 
	 * "Wyverns are a humanoid race most commonly found in the city of Dominion."
	 * +
	 * " Wyverns are very arrogant, and will not tolerate any sleights against them."
	 * +
	 * " They resent being treated in a submissive manner, and will always seek to take charge of a situation."
	 * ,
	 * 
	 * "Wyverns are humanoid, with a human body and face." +
	 * " The scaly arms of a Wyvern extend into large, leathery wings, giving them the ability to fly."
	 * +
	 * " Their legs are also scaly, starting at mid thigh, and their feet have claws instead of toes."
	 * +
	 * " Wyverns have a crest instead of hair, and they have a long, lizard-like tail."
	 * +
	 * " Scale colour differs from Wyvern to Wyvern, but is usually a shade of red or green."
	 * + " Females have small breasts." + " Males have a lizard-like cock.",
	 * 
	 * "Wyverns are not overly aggressive, but will take action against anyone who insults them."
	 * +
	 * " They typically know  small amount of magic, and will prefer fighting using brute force and spells."
	 * ,
	 * 
	 * "Wyverns breed by laying eggs that can be fertilised by any race." +
	 * " They will hate-fuck anyone they defeat in combat, with females refusing to become fertilised by what they consider inferior races."
	 * +
	 * " Once fertilised eggs hatch, the new Wyverns will rapidly reach full maturity and leave to make their own way in Dominion."
	 * ,
	 * 
	 * RacialBody.WYVERN, Genus.DRAGON, Disposition.CIVILIZED,
	 * StatusEffect.WYVERN),
	 * 
	 * DRAGON("dragon",
	 * 
	 * "Dragons are a humanoid monster race most commonly found in the ruins." +
	 * " Dragons are extremely proud and arrogant, and will not tolerate any sleights against them."
	 * +
	 * " They are extremely dominant, and will always seek to break their foes into submission."
	 * +
	 * " Dragons are considered the single most dangerous race, as they are extremely strong, tough and intelligent."
	 * +
	 * " They inhabit buildings in the ruins, and will seek to rob travellers and accumulate a hoard of wealth."
	 * +
	 * " Dragons will take a person prisoner, using them to breed and turning them into their submissive partner."
	 * +
	 * " They will quickly replace their partner if they manage to capture a more attractive or more important individual."
	 * ,
	 * 
	 * "Dragons are humanoid monsters, covered in hard scales." +
	 * " They have a huge pair of scaly and leathery wings on their back, giving them the ability to fly."
	 * + " Their feet and hands have claws on the end of their digits." +
	 * " Dragons have a crest instead of hair, and they have a long, lizard-like tail."
	 * +
	 * " Scale colour differs from Dragon to Dragon, but is usually a shade of red or green."
	 * + " Females have huge breasts." +
	 * " Males have a gigantic lizard-like cock.",
	 * 
	 * "Dragons are extremely aggressive, and will attack anything that comes close to their lair."
	 * +
	 * " They typically know a large amount of magic, and will prefer fighting using brute force and spells."
	 * ,
	 * 
	 * "Dragons breed by laying eggs that can be fertilised by any race." +
	 * " They will hate-fuck anyone they defeat in combat, with females refusing to become fertilised by what they consider inferior races."
	 * +
	 * " Once fertilised eggs hatch, the new Dragons will rapidly reach full maturity and leave to make their own way in the ruins."
	 * ,
	 * 
	 * RacialBody.DRAGON, Genus.DRAGON, Disposition.UNPREDICTABLE,
	 * StatusEffect.DRAGON),
	 * 
	 * 
	 * DEMON_COMMON("demon",
	 * 
	 * "Demons are a humanoid race found everywhere." +
	 * " Demons possesses the ability to switch their gender at will, but most of the time they will take on a female form."
	 * +
	 * " When male, they are referred to as an Incubus, and while female, a Succubus."
	 * +
	 * " Demons are not born, but are instead created by a Lilin by corrupting individuals using powerful demonic magic."
	 * ,
	 * 
	 * "Demons are humanoid, with a human-like face and body." +
	 * " Their skin will range in colour from dark red to a light lilac." +
	 * " Demons can change the appearance of their bodies at will, but they will commonly have one or two pairs of horns and a thin spaded tail."
	 * +
	 * " When in succubus form, many demons like to morph their legs into black leather reaching up to their thighs, forming their feet into high heels."
	 * +
	 * " They often give themselves up to three pairs of breasts, each pair being at least a DD cup."
	 * +
	 * " When in Incubus form, a demon will take on a muscular masculine form, giving themselves a gigantic demonic cock."
	 * +
	 * " Some demons prefer to never leave their Succubus form, and when ready to impregnate someone, will simply grow a demonic cock above their pussy."
	 * ,
	 * 
	 * "Demons have the ability to cast powerful spells, although the most potent demonic magic can only be harnessed by the more powerful version of a demon, the Lilin."
	 * +
	 * " When in combat, a demon will use physical attacks, seduction and spells in order to defeat their foe."
	 * ,
	 * 
	 * "A demon will take on their Succubus form and seek out males of any species to breed with."
	 * +
	 * " Once they have found and seduced a suitable mate, they will present themselves to be fucked, using demonic magic to force their partner to repeatedly orgasm."
	 * +
	 * " After milking their victim of cum, they will move on to their next conquest.</br>"
	 * +
	 * "The cum that they gather is transformed within their bodies into their own highly corruptive and addictive seed."
	 * +
	 * " Once they have gathered enough cum, they will transform into their Incubus form and seek out a suitable female to breed."
	 * +
	 * " Having located a suitable victim, the demon will fuck them, filling them with their corruptive spooge."
	 * +
	 * " Such a union will more often than not result in the female birthing a litter of imps a few days later."
	 * ,
	 * 
	 * RacialBody.DEMON, Genus.DEMON, Disposition.NEUTRAL, StatusEffect.DEMON),
	 * 
	 * LILIN("lilin",
	 * 
	 * "Lilin are a humanoid race found everywhere." +
	 * " Lilin are physically indistinguishable from demons, as both species can alter their bodies at will."
	 * +
	 * " The main difference between the two is that Lilin are able to use far more powerful magic than regular demons, and they have the ability to create new demons."
	 * +
	 * " Their corruptive aura is also far more potent, and any creature not highly resistant to the dark passion will surrender to their will almost instantly."
	 * +
	 * " Lilin are created by Lilith's own hand, being corrupted into her deviant minions."
	 * ,
	 * 
	 * "Lilin are humanoid, with a human-like face and body." +
	 * " Their skin will range in colour from dark red to a light lilac." +
	 * " Lilin can change the appearance of their bodies at will, but they will commonly have one or two pairs of horns and a thin spaded tail."
	 * +
	 * " When in succubus form, many Lilin like to morph their legs into black leather reaching up to their thighs, forming their feet into high heels."
	 * +
	 * " They often give themselves up to three pairs of breasts, each pair being at least a DD cup."
	 * +
	 * " When in Incubus form, a Lilin will take on a muscular masculine form, giving themselves a gigantic demonic cock."
	 * +
	 * " Some Lilin prefer to never leave their Succubus form, and when ready to impregnate someone, will simply grow a demonic cock above their pussy."
	 * ,
	 * 
	 * "Lilin have the ability to cast extremely powerful spells, being able to corrupt people into demons."
	 * +
	 * " When in combat, a Lilin will mostly use seduction and spells in order to defeat their foe."
	 * ,
	 * 
	 * "A Lilin will take on their Succubus form and seek out males of any species to breed with."
	 * +
	 * " Once they have found and seduced a suitable mate, they will present themselves to be fucked, using demonic magic to force their partner to repeatedly orgasm."
	 * +
	 * " After milking their victim of cum, they will move on to their next conquest.</br>"
	 * +
	 * "The cum that they gather is transformed within their bodies into their own highly corruptive and addictive seed."
	 * +
	 * " Once they have gathered enough cum, they will transform into their Incubus form and seek out a suitable female to breed."
	 * +
	 * " Having located a suitable victim, the Lilin will fuck them, filling them with their corruptive spooge."
	 * +
	 * " Such a union will more often than not result in the female birthing a litter of imps a few days later.</br>"
	 * +
	 * "Lilin are also able to create new demons by force-feeding them their corruptive cum over the course of a few days."
	 * ,
	 * 
	 * RacialBody.DEMON, Genus.DEMON, Disposition.UNPREDICTABLE,
	 * StatusEffect.LILIN);
	 */

	private String name, namePlural, singularMaleName, singularFemaleName, pluralMaleName, pluralFemaleName, basicDescription, advancedDescription;
	private Colour colour;
	private Genus genus;
	private Disposition disposition;
	private StatusEffect statusEffect;
	private List<Attack> preferredAttacks;
	private boolean vulnerableToLilithsLustStorm;
	private int numberOfOffspringLow, numberOfOffspringHigh;
	private float chanceForMaleOffspring;
	private Attribute damageMultiplier, resistanceMultiplier;
	private FurryPreference defaultFemininePreference, defaultMasculinePreference;
	
	private Race(
			String name,
			String namePlural,
			String singularMaleName,
			String singularFemaleName,
			String pluralMaleName,
			String pluralFemaleName,

			String basicDescription,
			String advancedDescription,

			Colour colour,
			Genus genus,
			Disposition disposition,
			StatusEffect statusEffect,
			List<Attack> preferredAttacks,
			boolean vulnerableToLilithsLustStorm,
			
			float chanceForMaleOffspring,
			int numberOfOffspringLow, int numberOfOffspringHigh,
			
			Attribute damageMultiplier,
			Attribute resistanceMultiplier,
			
			FurryPreference defaultFemininePreference,
			FurryPreference defaultMasculinePreference) {
		this.name = name;
		this.namePlural = namePlural;

		this.singularMaleName = singularMaleName;
		this.singularFemaleName = singularFemaleName;
		
		this.pluralMaleName = pluralMaleName;
		this.pluralFemaleName = pluralFemaleName;

		this.basicDescription = basicDescription;
		this.advancedDescription = advancedDescription;

		this.colour = colour;
		this.genus = genus;
		this.disposition = disposition;
		this.statusEffect = statusEffect;

		this.preferredAttacks = preferredAttacks;

		this.vulnerableToLilithsLustStorm = vulnerableToLilithsLustStorm;

		this.chanceForMaleOffspring=chanceForMaleOffspring;
		
		this.numberOfOffspringLow = numberOfOffspringLow;
		this.numberOfOffspringHigh = numberOfOffspringHigh;
		
		this.damageMultiplier = damageMultiplier;
		this.resistanceMultiplier = resistanceMultiplier;
		
		this.defaultFemininePreference = defaultFemininePreference;
		this.defaultMasculinePreference = defaultMasculinePreference;
	}

	public String getName() {
		return name;
	}
	
	public String getNamePlural() {
		return namePlural;
	}

	public String getBasicDescription() {
		return basicDescription;
	}

	public String getAdvancedDescription() {
		return advancedDescription;
	}

	public Genus getGenus() {
		return genus;
	}

	public Disposition getDisposition() {
		return disposition;
	}

	public StatusEffect getStatusEffect() {
		return statusEffect;
	}

	public List<Attack> getPreferredAttacks() {
		return preferredAttacks;
	}

	public boolean isVulnerableToLilithsLustStorm() {
		return vulnerableToLilithsLustStorm;
	}

	public int getNumberOfOffspringLow() {
		return numberOfOffspringLow;
	}

	public int getNumberOfOffspringHigh() {
		return numberOfOffspringHigh;
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
	
	public Colour getColour() {
		return colour;
	}
	
	public boolean isAffectedByFurryPreference() {
		return defaultFemininePreference != null && defaultMasculinePreference!=null;
	}
	
	// Offspring names:
	
	public float getChanceForMaleOffspring() {
		return chanceForMaleOffspring;
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

	public Attribute getDamageMultiplier() {
		return damageMultiplier;
	}

	public Attribute getResistanceMultiplier() {
		return resistanceMultiplier;
	}

	public FurryPreference getDefaultFemininePreference() {
		return defaultFemininePreference;
	}

	public FurryPreference getDefaultMasculinePreference() {
		return defaultMasculinePreference;
	}

}
