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
	
	/**NPCs will spawn with furry ears and eyes, and where applicable, furry tails, horns, antenna, and wings. They will <b>not</b> spawn with furry breasts or genitalia.*/
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
	
	/**NPCs will spawn with all of the furry parts that the 'Minimum' setting enables (ears, eyes, tails, horns, antenna, and wings). They also have the chance to spawn with furry breasts, genitalia, arms, and legs.*/
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
	
	/**NPCs will spawn with all of the furry parts that the 'Minimum' setting enables (ears, eyes, tails, horns, antenna, and wings). They also have the chance to spawn with furry breasts, genitalia, arms, legs, skin/fur, and faces.*/
	NORMAL("Greater") {
		@Override
		public String getDescriptionFeminine(Race r) {
			return "Feminine "+r.getNamePlural()+" will spawn with all of the furry parts that the 'Minimum' setting enables (ears, eyes, tails, horns, antenna, and wings)."
					+ " They also have the chance to spawn with furry breasts, genitalia, arms, legs, skin/fur, and faces.";
		}

		@Override
		public String getDescriptionMasculine(Race r) {
			return "Masculine "+r.getNamePlural()+" will spawn with all of the furry parts that the 'Minimum' setting enables (ears, eyes, tails, horns, antenna, and wings)."
					+ " They also have the chance to spawn with furry breasts, genitalia, arms, legs, skin/fur, and faces.";
		}
	},
	
	/**NPCs will <b>always</b> spawn with as many furry parts as is possible (ears, eyes, tails, horns, antenna, wings, breasts, genitalia, arms, legs, skin/fur, and face).*/
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
