package com.lilithsthrone.game.character.persona;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/***
 * @since 0.2.4
 * @version 0.3.5
 * @author Innoxia
 */
public enum PersonalityTrait {
	
	// Core traits:
	
	CONFIDENT(PersonalityCategory.CORE, "confident", "[npc.NameIsFull] very assertive and sure of [npc.herself].", "", Colour.BASE_GREEN_LIME) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(SHY);
		}
	},
	
	SHY(PersonalityCategory.CORE, "shy", "[npc.NameIsFull] incredibly shy around other people, and [npc.verb(prefer)] to avoid conversation wherever possible.", "", Colour.BASE_YELLOW_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(CONFIDENT);
		}
	},

	KIND(PersonalityCategory.CORE, "kind", "[npc.Name] always [npc.verb(try)] to be kind and considerate of others, sometimes even to the detriment of [npc.her] own happiness.", "", Colour.BASE_GREEN) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(SELFISH);
		}
	},
	
	SELFISH(PersonalityCategory.CORE, "selfish", "[npc.Name] always [npc.verb(put)] [npc.herself] first, and [npc.is] highly unlikely to do anything that doesn't directly benefit [npc.herHim].", "", Colour.BASE_RED) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(KIND);
		}
	},
	
	// Combat traits:
	
	BRAVE(PersonalityCategory.COMBAT, "brave", "[npc.Name] always [npc.verb(act)] in a courageous manner, and [npc.is] not one to shy away from a fight.", "", Colour.BASE_ORANGE) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(COWARDLY);
		}
	},
	
	COWARDLY(PersonalityCategory.COMBAT, "cowardly", "[npc.Name] [npc.verb(get)] scared very easily, and will prefer to run away from conflicts rather than try to resolve them directly.", "", Colour.BASE_RED_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(BRAVE);
		}
	},

	// Sex traits:
	
	LEWD(PersonalityCategory.SEX,
			"lewd",
			"[npc.Name] [npc.verb(seem)] to always have sex on [npc.her] mind, and often [npc.verb(display)] very crude and licentious behaviour.",
			"",
			Colour.BASE_PINK) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(PRUDE, INNOCENT);
		}
	},
	
	INNOCENT(PersonalityCategory.SEX,
			"innocent",
			"[npc.Name] [npc.verb(get)] overwhelmed when faced with lewd situations, and [npc.is] not really sure what [npc.sheIs] meant to do while having sex.",
			"",
			Colour.BASE_BLUE_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(LEWD, PRUDE);
		}
	},
	
	PRUDE(PersonalityCategory.SEX,
			"prude",
			"[npc.Name] [npc.verb(act)] shocked or disinterested whenever talk turns to matters of sex.",
			"",
			Colour.BASE_BLUE_STEEL) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(LEWD, INNOCENT);
		}
	},

	// Speech traits:
	//TODO
//	BIMBO(PersonalityCategory.SPEECH, "bimbo", "[npc.Name] both acts and speaks like an air-headed bimbo.", Colour.BASE_PINK_DEEP) {
//		@Override
//		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
//			return Util.newArrayListOfValues(BRO);
//		}
//	},
//	
//	BRO(PersonalityCategory.SPEECH, "bro", "[npc.Name] acts, and speaks, like an air-headed surfer bro.", Colour.BASE_BLUE) {
//		@Override
//		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
//			return Util.newArrayListOfValues(BIMBO);
//		}
//	},

	LISP(PersonalityCategory.SPEECH,
			"lisp",
			"[npc.Name] [npc.verb(speak)] with a lisp, pronouncing 's' and 'z' as 'th'.",
			"[style.italicsBad(All of [npc.namePos] in-game speech will be affected by this lisp!)]",
			Colour.BASE_PURPLE_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return new ArrayList<>();
		}
	},

	STUTTER(PersonalityCategory.SPEECH,
			"stutter",
			"[npc.Name] has a habit of stuttering and stumbling over [npc.her] words as [npc.she] [npc.verb(speak)].",
			"[style.italicsBad(All of [npc.namePos] in-game speech will be affected by this stutter!)]",
			Colour.BASE_VIOLET) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return new ArrayList<>();
		}
	};

	private PersonalityCategory personalityCategory;
	private String name;
	private String description;
	private String gameplayInformation;
	private Colour colour;
	
	private PersonalityTrait(PersonalityCategory personalityCategory, String name, String description, String gameplayInformation, Colour colour) {
		this.personalityCategory = personalityCategory;
		this.name = name;
		this.description = description;
		this.gameplayInformation = gameplayInformation;
		this.colour = colour;
	}
	
	public PersonalityCategory getPersonalityCategory() {
		return personalityCategory;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription(GameCharacter character, boolean withGameplayInformation, boolean withMutuallyExclusiveInformation) {
		StringBuilder sb = new StringBuilder();

		sb.append(UtilText.parse(character, description));
		
		if(withGameplayInformation) {
			if(gameplayInformation!=null && !gameplayInformation.isEmpty()) {
				sb.append("<br/>"+UtilText.parse(character, gameplayInformation));
			} else {
				sb.append("<br/>[style.italicsDisabled(No current in-game effects...)]");
			}
		}
		
		if(withMutuallyExclusiveInformation) {
			if(!this.getMutuallyExclusiveSettings().isEmpty()) {
				sb.append("<br/>[style.colourBad(Mutually exclusive)] with ");
				List<String> names = new ArrayList<>();
				for(PersonalityTrait trait : this.getMutuallyExclusiveSettings()) {
					names.add("<span style='color:"+trait.getColour().toWebHexString()+";'>"+trait.getName()+"</span>");
				}
				sb.append(Util.stringsToStringList(names, false)+"!");
			}
		}
		
		return sb.toString();
	}
	
	public Colour getColour() {
		return colour;
	}

	public abstract List<PersonalityTrait> getMutuallyExclusiveSettings();
}
