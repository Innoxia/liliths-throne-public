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
	
	CONFIDENT(false, PersonalityCategory.CORE, "confident", "[npc.NameIsFull] very assertive and sure of [npc.herself].", "", Colour.BASE_GREEN_LIME) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(SHY);
		}
	},
	
	SHY(false, PersonalityCategory.CORE, "shy", "[npc.NameIsFull] incredibly shy around other people, and [npc.verb(prefer)] to avoid conversation wherever possible.", "", Colour.BASE_YELLOW_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(CONFIDENT);
		}
	},

	KIND(false, PersonalityCategory.CORE, "kind", "[npc.Name] always [npc.verb(try)] to be kind and considerate of others, sometimes even to the detriment of [npc.her] own happiness.", "", Colour.BASE_GREEN) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(SELFISH);
		}
	},
	
	SELFISH(false, PersonalityCategory.CORE, "selfish", "[npc.Name] always [npc.verb(put)] [npc.herself] first, and [npc.is] highly unlikely to do anything that doesn't directly benefit [npc.herHim].", "", Colour.BASE_RED) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(KIND);
		}
	},
	
	// Combat traits:
	
	BRAVE(false, PersonalityCategory.COMBAT, "brave", "[npc.Name] always [npc.verb(act)] in a courageous manner, and [npc.is] not one to shy away from a fight.", "", Colour.BASE_ORANGE) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(COWARDLY);
		}
	},
	
	COWARDLY(false, PersonalityCategory.COMBAT, "cowardly", "[npc.Name] [npc.verb(get)] scared very easily, and will prefer to run away from conflicts rather than try to resolve them directly.", "", Colour.BASE_RED_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(BRAVE);
		}
	},

	// Sex traits:
	
	LEWD(false,
			PersonalityCategory.SEX,
			"lewd",
			"[npc.NameHasFull] an extensive knowledge of all things sexual, and [npc.is] always eager to talk about lewd things.",
			"", Colour.BASE_PINK) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(PRUDE, INNOCENT);
		}
	},
	
	INNOCENT(false,
			PersonalityCategory.SEX,
			"innocent",
			"[npc.Name] always [npc.verb(act)] embarrassed and innocent when talking about (or performing) sexual acts.",
			"", Colour.BASE_BLUE_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(LEWD, PRUDE);
		}
	},
	
	PRUDE(false,
			PersonalityCategory.SEX,
			"prude",
			"[npc.Name] [npc.do] not like talking about sexual matters, and [npc.verb(refuse)] to even acknowledge that [npc.she] [npc.verb(know)] anything about such things.",
			"", Colour.BASE_BLUE_STEEL) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(LEWD, INNOCENT);
		}
	},

	// Speech traits:
	
	LISP(false,
			PersonalityCategory.SPEECH,
			"lisp",
			"[npc.Name] [npc.verb(speak)] with a lisp, pronouncing 's' and 'z' as 'th'.",
			"[style.italicsBad(All of [npc.namePos] in-game speech will be affected by this lisp!)]", Colour.BASE_PURPLE_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(MUTE);
		}
	},

	STUTTER(false,
			PersonalityCategory.SPEECH,
			"stutter",
			"[npc.NameHasFull] a habit of stuttering and stumbling over [npc.her] words as [npc.she] [npc.verb(speak)].",
			"[style.italicsBad(All of [npc.namePos] in-game speech will be affected by this stutter!)]", Colour.BASE_PINK_SALMON) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(MUTE);
		}
	},

	MUTE(true,
			PersonalityCategory.SPEECH,
			"mute",
			"[npc.NameIsFull] a mute, and while [npc.she] can make some lewd noises when in the grips of passion, [npc.sheIsFull] otherwise completely unable to speak.",
			"[style.italicsBad(All of [npc.namePos] in-game speech will be removed!)]", Colour.BASE_CRIMSON) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(LISP, STUTTER);
		}
	};
	
	private boolean specialRequirements;
	private PersonalityCategory personalityCategory;
	private String name;
	private String description;
	private String gameplayInformation;
	private Colour colour;
	
	private PersonalityTrait(boolean specialRequirements, PersonalityCategory personalityCategory, String name, String description, String gameplayInformation, Colour colour) {
		this.specialRequirements = specialRequirements;
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

	public boolean isSpecialRequirements() {
		return specialRequirements;
	}
}
