package com.lilithsthrone.game.occupantManagement.slaveEvent;

import com.lilithsthrone.game.character.GameCharacter;

/**
 * @since 0.2.2
 * @version 0.4
 * @author Innoxia
 */
public enum SlaveEvent {
	
	WASHED_BODY(SlaveEventType.MISCELLANEOUS, "Washed Body", "[npc.Name] had a wash and cleaned [npc.her] body.") {
		public void applyEffects(GameCharacter character) {
			character.washAllOrifices(true);
			character.cleanAllDirtySlots(true);
			character.calculateStatusEffects(0);
		}
	},
	
	WASHED_CLOTHES(SlaveEventType.MISCELLANEOUS, "Washed Clothes", "[npc.Name] washed [npc.her] clothes.") {
		public void applyEffects(GameCharacter character) {
			character.cleanAllDirtySlots(false); //TODO should they be cleaning slots?
			character.cleanAllClothing(true, false);
			character.calculateStatusEffects(0);
		}
	},
	
	DAILY_UPDATE(SlaveEventType.MISCELLANEOUS, "Daily Update", ""),

	SLAVE_SEX(SlaveEventType.SEX, "[style.boldSex(Sex)]", "[npc.Name] had sex with another slave..."),
	
	SLAVE_BONDING(SlaveEventType.BONDING, "[style.boldAffection(Bonding)]", "[npc.Name] spent some time bonding with another slave..."),
	
	GAVE_BIRTH(SlaveEventType.SEX, "[style.boldExcellent(Gave birth)]", "Lilaya helped [npc.name] to give birth."),

	GAVE_BIRTH_INCUBATION(SlaveEventType.SEX, "[style.boldExcellent(Laid eggs)]", "Lilaya helped [npc.name] to lay the eggs [npc.she] was incubating."),
	
	
	// Jobs:

//	JOB_CUM_MILKED("Cum Milked", "[npc.NamePos] [npc.cum+] was milked."),
	JOB_MILK_MILKED(SlaveEventType.JOB, "Milked", ""),
//	JOB_MILK_CROTCH_MILKED("Udders Milked", "[npc.Name] was milked of [npc.her] [npc.crotchMilk+]."),
//	JOB_GIRLCUM_MILKED("Girlcum Milked", "[npc.NamePos] [npc.girlcum+] was milked."),
	
	//TODO
//	JOB_CLEANING("Cleaning Fun", "<i>Placeholder event.</i>"),
//	
//	JOB_COOKING("Cooking Fun", "<i>Placeholder event.</i>"),
//	
//	JOB_LAB_ASSISTANT("Lilaya Fun", "<i>Placeholder event.</i>"),
//	
//	JOB_LIBRARIAN("Librarian Fun", "<i>Placeholder event.</i>"),
//
//	JOB_OFFICE("Office Fun", "<i>Placeholder event.</i>"),
	
	JOB_TEST_SUBJECT(SlaveEventType.JOB, "Test Subject", "[npc.Name] was subjected to Lilaya's transformative experiments..."),
	
	JOB_PUBLIC_STOCKS(SlaveEventType.JOB, "Locked in Stocks", "[npc.Name] was locked into the public stocks in Slaver Alley..."),
	
	JOB_PROSTITUTE(SlaveEventType.JOB, "Prostitute", "[npc.Name] worked as a prostitute in Angel's Kiss."),

//	JOB_IDLE("Resting", "<i>Placeholder event.</i>"),
	;
	
	private SlaveEventType type;
	private String name;
	private String description;
	
	private SlaveEvent(SlaveEventType type, String name, String description) {
		this.type = type;
		this.name = name;
		this.description = description;
	}

	public SlaveEventType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	// For use in overriding.
	public void applyEffects(GameCharacter character) {
	}
}
