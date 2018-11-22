package com.lilithsthrone.game.occupantManagement;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.2.2
 * @version 0.2.2
 * @author Innoxia
 */
public enum SlaveEventTag {
	
	WASHED_BODY_ANAL_CREAMPIE("<span style='color:"+Colour.BASE_AQUA.toWebHexString()+";'>Cleaned Anal Creampie</span>"),
	
	WASHED_BODY_VAGINAL_CREAMPIE("<span style='color:"+Colour.BASE_AQUA.toWebHexString()+";'>Cleaned Pussy Creampie</span>"),
	
	WASHED_BODY_NIPPLE_CREAMPIE("<span style='color:"+Colour.BASE_AQUA.toWebHexString()+";'>Cleaned Nipple Creampie</span>"),

	WASHED_CLOTHES("<span style='color:"+Colour.BASE_AQUA.toWebHexString()+";'>Cleaned Clothes</span>"),

	// Muscle:
	
	DAILY_MUSCLE_LOSS_LARGE("[style.boldShrink(-5)] [style.boldMuscleZero(Muscle Size)]") {
		@Override
		public void applyEffects(GameCharacter character) {
			character.incrementMuscle(-5);
		}
	},
	
	DAILY_MUSCLE_LOSS("[style.boldShrink(-1)] [style.boldMuscleOne(Muscle Size)]") {
		@Override
		public void applyEffects(GameCharacter character) {
			character.incrementMuscle(-1);
		}
	},
	
	DAILY_MUSCLE_GAIN("[style.boldGrow(+1)] [style.boldMuscleThree(Muscle Size)]") {
		@Override
		public void applyEffects(GameCharacter character) {
			character.incrementMuscle(1);
		}
	},
	
	DAILY_MUSCLE_GAIN_LARGE("[style.boldGrow(+5)] [style.boldMuscleFour(Muscle Size)]") {
		@Override
		public void applyEffects(GameCharacter character) {
			character.incrementMuscle(5);
		}
	},
	
	// Body Size;
	
	DAILY_BODY_SIZE_LOSS_LARGE("[style.boldShrink(-5)] [style.boldBodySizeZero(Body Size)]") {
		@Override
		public void applyEffects(GameCharacter character) {
			character.incrementBodySize(-5);
		}
	},
	
	DAILY_BODY_SIZE_LOSS("[style.boldShrink(-1)] [style.boldBodySizeOne(Body Size)]") {
		@Override
		public void applyEffects(GameCharacter character) {
			character.incrementBodySize(-1);
		}
	},
	
	DAILY_BODY_SIZE_GAIN("[style.boldGrow(+1)] [style.boldBodySizeThree(Body Size)]") {
		@Override
		public void applyEffects(GameCharacter character) {
			character.incrementBodySize(1);
		}
	},
	
	DAILY_BODY_SIZE_GAIN_LARGE("[style.boldGrow(+5)] [style.boldBodySizeFour(Body Size)]") {
		@Override
		public void applyEffects(GameCharacter character) {
			character.incrementBodySize(5);
		}
	},
	
	// Jobs:

	JOB_CUM_SOLD("[npc.NamePos] [npc.cum] was sold!"),
	JOB_MILK_SOLD("[npc.NamePos] [npc.milk] was sold!"),
	JOB_GIRLCUM_SOLD("[npc.NamePos] [npc.girlcum] was sold!"),

	JOB_CUM_MILKED("[npc.NamePos] [npc.cum] was milked!"),
	JOB_MILK_MILKED("[npc.NamePos] [npc.milk] was milked!"),
	JOB_GIRLCUM_MILKED("[npc.NamePos] [npc.girlcum] was milked!"),

	
	JOB_LILAYA_INTRUSIVE_TESTING("Lilaya ran some rather intrusive tests on [npc.name]."),
	
	JOB_LILAYA_FEMININE_TF("Lilaya tested some very intrusive feminine transformations on [npc.name]."),
	
	JOB_LILAYA_MASCULINE_TF("Lilaya tested some very intrusive masculine transformations on [npc.name]."),

	JOB_STOCKS_USED("[npc.Name] was used by an unknown member of the public."),
	
	JOB_PROSTITUTE_USED("[npc.Name] was used by a client.")
	;
	
	private String description;
	
	private SlaveEventTag(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public void applyEffects(GameCharacter character) {
		// :3
	}
}
