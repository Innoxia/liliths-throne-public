package com.lilithsthrone.game.character.race;

/**
 * @since 0.1.78
 * @version 0.1.87
 * @author Innoxia
 */
public enum FurryPreference {

	/**No furry parts at all. (NPCs will spawn as regular humans.)*/
	HUMAN("Disabled") {
		@Override
		public String getDescriptionFeminine(Race r) {
			return "Feminine "+r.getNamePlural()+" will be completed disabled in random encounters. If all feminine preferences are set to 'Disabled', random encounters will default to feminine humans.";
		}
		@Override
		public String getDescriptionMasculine(Race r) {
			return "Masculine "+r.getNamePlural()+" will be completed disabled in random encounters. If all masculine preferences are set to 'Disabled', random encounters will default to masculine humans.";
		}
	},
	
	/**NPCs will have randomised bodies, but will never have furry arms, legs, faces or skin.*/
	MINIMUM("Minimum") {
		@Override
		public String getDescriptionFeminine(Race r) {
			return "Feminine "+r.getNamePlural()+" will spawn with furry ears and eyes, and where applicable, furry tails, horns, antenna, and wings. They will <b>not</b> spawn with furry breasts or genitalia.";
		}

		@Override
		public String getDescriptionMasculine(Race r) {
			return "Masculine "+r.getNamePlural()+" will spawn with furry ears and eyes, and where applicable, furry tails, horns, antenna, and wings. They will <b>not</b> spawn with furry breasts or genitalia.";
		}
	},
	
	/**NPCs will have randomised bodies, but will never have furry faces or skin.*/
	REDUCED("Lesser") {
		@Override
		public String getDescriptionFeminine(Race r) {
			return "Feminine "+r.getNamePlural()+" will spawn with all of the furry parts that the 'Minimum' setting enables (ears, eyes, tails, horns, antenna, and wings)."
						+ " They also have the chance to spawn with furry breasts, genitalia, arms, and legs.";
		}

		@Override
		public String getDescriptionMasculine(Race r) {
			return "Masculine "+r.getNamePlural()+" will spawn with all of the furry parts that the 'Minimum' setting enables (ears, eyes, tails, horns, antenna, and wings)."
					+ " They also have the chance to spawn with furry breasts, genitalia, arms, and legs.";
		}
	},
	
	/**NPCs will have randomised bodies, with no body-part limitations.*/
	NORMAL("Greater") {
		@Override
		public String getDescriptionFeminine(Race r) {
			return "Feminine "+r.getNamePlural()+" will spawn with all of the furry parts that the 'Lesser' setting enables (ears, eyes, tails, horns, antenna, wings, breasts, genitalia, arms, and legs)."
					+ " They also have the chance to spawn with furry skin/fur and furry faces.";
		}

		@Override
		public String getDescriptionMasculine(Race r) {
			return "Masculine "+r.getNamePlural()+" will spawn with all of the furry parts that the 'Lesser' setting enables (ears, eyes, tails, horns, antenna, wings, breasts, genitalia, arms, and legs)."
					+ " They also have the chance to spawn with furry skin/fur and furry faces.";
		}
	},
	
	/**NPCs will have fully-furry bodies wherever possible.*/
	MAXIMUM("Maximum") {
		@Override
		public String getDescriptionFeminine(Race r) {
			return "Feminine "+r.getNamePlural()+" will <b>always</b> spawn with as many furry parts as is possible (ears, eyes, tails, horns, antenna, wings, breasts, genitalia, arms, legs, skin/fur, and face).";
		}

		@Override
		public String getDescriptionMasculine(Race r) {
			return "Masculine "+r.getNamePlural()+" will <b>always</b> spawn with as many furry parts as is possible (ears, eyes, tails, horns, antenna, wings, breasts, genitalia, arms, legs, skin/fur, and face).";
		}
	};
	
	private String name;
	private FurryPreference(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract String getDescriptionFeminine(Race r);
	public abstract String getDescriptionMasculine(Race r);
}
