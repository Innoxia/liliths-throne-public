package com.lilithsthrone.game.slavery;

import com.lilithsthrone.game.character.GameCharacter;

/**
 * @since 0.2.2
 * @version 0.2.2
 * @author Innoxia
 */
public enum SlaveEvent {
	
	WASHED_BODY("Washed Body", "[npc.Name] had a wash and cleaned [npc.her] body.") {
		public void applyEffects(GameCharacter character) {
			character.washAllOrifices();
			character.cleanAllDirtySlots();
			character.calculateStatusEffects(0);
		}
	},
	
	WASHED_CLOTHES("Washed Clothes", "[npc.Name] washed [npc.her] clothes.") {
		public void applyEffects(GameCharacter character) {
			character.cleanAllDirtySlots();
			character.cleanAllClothing();
			character.calculateStatusEffects(0);
		}
	},
	
	DAILY_UPDATE("Daily Update", ""),

	SLAVE_SEX("[style.boldSex(Sex)]", "[npc.Name] had sex with another slave..."),
	
	// Jobs:

	JOB_CUM_MILKED("Cum Milked", "[npc.NamePos] [npc.cum+] was milked."),
	JOB_MILK_MILKED("Milked", "[npc.Name] was milked of [npc.her] [npc.milk+]."),
	JOB_GIRLCUM_MILKED("Girlcum Milked", "[npc.NamePos] [npc.girlcum+] was milked."),
	
	JOB_CLEANING("Cleaning Fun", "<i>Placeholder event.</i>"),
	
	JOB_COOKING("Cooking Fun", "<i>Placeholder event.</i>"),
	
	JOB_LAB_ASSISTANT("Lilaya Fun", "<i>Placeholder event.</i>"),
	
	JOB_LIBRARIAN("Librarian Fun", "<i>Placeholder event.</i>"),

	JOB_TEST_SUBJECT("Test Subject", "[npc.Name] was subjected to Lilaya's transformative experiments..."),
	
	JOB_PUBLIC_STOCKS("Locked in Stocks", "[npc.Name] was locked into the public stocks in Slaver Alley..."),
	
	JOB_PROSTITUTE("Prostitute", "[npc.Name] worked as a prostitute in Angel's Kiss."),

	JOB_IDLE("Resting", "<i>Placeholder event.</i>"),
	;
	
	private String name;
	private String description;
	
	private SlaveEvent(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	public void applyEffects(GameCharacter character) {
		// :3
	}
}
