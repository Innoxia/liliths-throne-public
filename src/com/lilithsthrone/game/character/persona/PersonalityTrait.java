package com.lilithsthrone.game.character.persona;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/***
 * @since 0.2.4
 * @version 0.4.9
 * @author Innoxia
 */
public enum PersonalityTrait {
	
	// Core traits:
	
	CONFIDENT(false, PersonalityCategory.CORE, "confident", "[npc.NameIsFull] very assertive and sure of [npc.herself].", "", PresetColour.BASE_GREEN_LIME) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(SHY);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(CONFIDENT)
								?"[style.colourDisabled([npc.Name] [npc.isFull] already confident, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(feeling a lot more confident)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(CONFIDENT)
								?"[style.colourDisabled([npc.Name] already lacks confidence, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(feeling a lot less confident)]!")
						+ "</p>");
		}
	},
	
	SHY(false, PersonalityCategory.CORE, "shy", "[npc.NameIsFull] incredibly shy around other people, and [npc.verb(prefer)] to avoid conversation wherever possible.", "", PresetColour.BASE_YELLOW_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(CONFIDENT);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(SHY)
								?"[style.colourDisabled([npc.Name] [npc.isFull] already shy, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(feeling a lot shyer)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(SHY)
								?"[style.colourDisabled([npc.Name] already doesn't suffer from shyness, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(losing [npc.her] shyness)]!")
						+ "</p>");
		}
	},

	KIND(false, PersonalityCategory.CORE, "kind", "[npc.Name] always [npc.verb(try)] to be kind and considerate of others, sometimes even to the detriment of [npc.her] own happiness.", "", PresetColour.BASE_GREEN) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(SELFISH);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(KIND)
								?"[style.colourDisabled([npc.Name] [npc.isFull] already kind, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(feeling a lot kinder)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(KIND)
								?"[style.colourDisabled([npc.Name] already isn't very kind, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(losing [npc.her] kindness)]!")
						+ "</p>");
		}
	},
	
	SELFISH(false, PersonalityCategory.CORE, "selfish", "[npc.Name] always [npc.verb(put)] [npc.herself] first, and [npc.is] highly unlikely to do anything that doesn't directly benefit [npc.herHim].", "", PresetColour.BASE_RED) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(KIND);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(SELFISH)
								?"[style.colourDisabled([npc.Name] [npc.isFull] already selfish, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(feeling a lot more selfish)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(SELFISH)
								?"[style.colourDisabled([npc.Name] already isn't selfish, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(losing [npc.her] selfishness)]!")
						+ "</p>");
		}
	},

	NAIVE(false, PersonalityCategory.CORE, "naive", "Lacking in life experience and wisdom, [npc.name] [npc.has] absolutely no understanding of the harsh facts of reality.", "", PresetColour.BASE_PINK_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(CYNICAL);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(NAIVE)
								?"[style.colourDisabled([npc.Name] [npc.isFull] already naive, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(feeling a lot more naive)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(NAIVE)
								?"[style.colourDisabled([npc.Name] already isn't naive, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(losing [npc.her] naivety)]!")
						+ "</p>");
		}
	},
	
	CYNICAL(false, PersonalityCategory.CORE, "cynical", "[npc.NameIsFull] particularly distrustful of the intentions and motivations of other people.", "", PresetColour.BASE_RED_DARK) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(NAIVE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(CYNICAL)
								?"[style.colourDisabled([npc.Name] [npc.isFull] already cynical, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(feeling a lot more cynical)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(CYNICAL)
								?"[style.colourDisabled([npc.Name] already isn't cynical, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(losing [npc.her] cynicism)]!")
						+ "</p>");
		}
	},
	
	// Combat traits:
	
	BRAVE(false, PersonalityCategory.COMBAT, "brave", "[npc.Name] always [npc.verb(act)] in a courageous manner, and [npc.is] not one to shy away from a fight.", "", PresetColour.BASE_ORANGE) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(COWARDLY);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(BRAVE)
								?"[style.colourDisabled([npc.Name] [npc.isFull] already brave, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(feeling a lot braver)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(BRAVE)
								?"[style.colourDisabled([npc.Name] already isn't brave, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(losing [npc.her] bravery)]!")
						+ "</p>");
		}
	},
	
	COWARDLY(false, PersonalityCategory.COMBAT, "cowardly", "[npc.Name] [npc.verb(get)] scared very easily, and will prefer to run away from conflicts rather than try to resolve them directly.", "", PresetColour.BASE_RED_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(BRAVE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(COWARDLY)
								?"[style.colourDisabled([npc.Name] [npc.isFull] already cowardly, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(feeling a lot more cowardly)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(COWARDLY)
								?"[style.colourDisabled([npc.Name] already isn't cowardly, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(losing [npc.her] cowardice)]!")
						+ "</p>");
		}
	},

	// Sex traits:
	
	LEWD(false,
			PersonalityCategory.SEX,
			"lewd",
			"[npc.NameHasFull] an extensive knowledge of all things sexual, and [npc.is] always eager to talk about lewd things.",
			"", PresetColour.BASE_PINK) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(PRUDE, INNOCENT);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(LEWD)
								?"[style.colourDisabled([npc.Name] [npc.isFull] already lewd, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(feeling a lot lewder)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(LEWD)
								?"[style.colourDisabled([npc.Name] already isn't lewd, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(losing [npc.her] lewdity)]!")
						+ "</p>");
		}
	},
	
	INNOCENT(false,
			PersonalityCategory.SEX,
			"innocent",
			"[npc.Name] always [npc.verb(act)] embarrassed and innocent when talking about (or performing) sexual acts.",
			"", PresetColour.BASE_BLUE_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(LEWD, PRUDE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(INNOCENT)
								?"[style.colourDisabled([npc.Name] [npc.isFull] already innocent, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(feeling a lot more innocent)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(INNOCENT)
								?"[style.colourDisabled([npc.Name] already isn't innocent, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(losing [npc.her] innocence)]!")
						+ "</p>");
		}
	},
	
	PRUDE(false,
			PersonalityCategory.SEX,
			"prude",
			"[npc.Name] [npc.do] not like talking about sexual matters, and [npc.verb(refuse)] to even acknowledge that [npc.she] [npc.verb(know)] anything about such things.",
			"", PresetColour.BASE_BLUE_STEEL) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(LEWD, INNOCENT);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(INNOCENT)
								?"[style.colourDisabled([npc.Name] [npc.isFull] already prude, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(feeling a lot more prude)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(INNOCENT)
								?"[style.colourDisabled([npc.Name] already isn't prude, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(losing [npc.her] prudish mannerisms)]!")
						+ "</p>");
		}
	},

	// Speech traits:
	
	LISP(false,
			PersonalityCategory.SPEECH,
			"lisp",
			"[npc.Name] [npc.verb(speak)] with a lisp, pronouncing 's' and 'z' as 'th'.",
			"[style.italicsBad(All of [npc.namePos] in-game speech will be affected by this lisp!)]", PresetColour.BASE_PURPLE_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(MUTE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(LISP)
								?"[style.colourDisabled([npc.Name] already speaks with a lisp, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(speaking with a lisp)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(LISP)
								?"[style.colourDisabled([npc.Name] already [npc.do]n't speak with a lisp, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(able to speak without a lisp)]!")
						+ "</p>");
		}
	},

	STUTTER(false,
			PersonalityCategory.SPEECH,
			"stutter",
			"[npc.NameHasFull] a habit of stuttering and stumbling over [npc.her] words as [npc.she] [npc.verb(speak)].",
			"[style.italicsBad(All of [npc.namePos] in-game speech will be affected by this stutter!)]", PresetColour.BASE_PINK_SALMON) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(MUTE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(STUTTER)
								?"[style.colourDisabled([npc.Name] already speaks with a stutter, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(speaking with a stutter)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(STUTTER)
								?"[style.colourDisabled([npc.Name] already [npc.do]n't speak with a stutter, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(able to speak without a stutter)]!")
						+ "</p>");
		}
	},

	MUTE(true,
			PersonalityCategory.SPEECH,
			"mute",
			"[npc.NameIsFull] a mute, and while [npc.she] can make some lewd noises when in the grips of passion, [npc.sheIsFull] otherwise completely unable to speak.",
			"[style.italicsBad(All of [npc.namePos] in-game speech will be removed!)]", PresetColour.BASE_CRIMSON) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(LISP, STUTTER, SLOVENLY);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(STUTTER)
								?"[style.colourDisabled([npc.NameIsFull] already mute, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(unable to talk)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(STUTTER)
								?"[style.colourDisabled([npc.NameIsFull] already able to speak, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(able to talk)]!")
						+ "</p>");
		}
	},

	SLOVENLY(false,
			PersonalityCategory.SPEECH,
			"slovenly",
			"[npc.Name] [npc.verb(speak)] in a very slovenly manner; dropping syllables and with poor pronunciation, [npc.her] speech can often be very hard to understand.",
			"[style.italicsBad(All of [npc.namePos] in-game speech will be affected by this!)]", PresetColour.BASE_BROWN) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(MUTE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(SLOVENLY)
								?"[style.colourDisabled([npc.Name] already speaks in a slovenly manner, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(speaking in a slovenly manner)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(SLOVENLY)
								?"[style.colourDisabled([npc.Name] already [npc.do]n't speak in a slovenly manner, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(no longer speaking in a slovenly manner)]!")
						+ "</p>");
		}
	},;
	
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

	public abstract String getAdditionDescription(GameCharacter target);
	
	public abstract String getRemovalDescription(GameCharacter target);
	
	public boolean isSpecialRequirements() {
		return specialRequirements;
	}
}
