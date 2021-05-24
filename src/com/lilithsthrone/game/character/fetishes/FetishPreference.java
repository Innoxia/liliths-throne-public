package com.lilithsthrone.game.character.fetishes;

import com.lilithsthrone.main.Main;

import java.util.HashMap;
import java.util.Map;

public enum FetishPreference {
	ZERO_DISABLED("disabled", 0, 0, 0, 0, 0, "This fetish will not be appear unless special conditions are met."),
	ONE_HATE("hate", 1, 5, 3, 0, 0, "NPCs with this desire will only dislike or hate unless special conditions are met."),
	TWO_DISLIKE("dislike", 2, 3, 5, 2, 1, "NPCs with this desire will more likely dislike/hate but can still like/love it."),
	THREE_NEUTRAL("neutral", 3, 1, 1, 1, 1, "NPCs are more likely to have non-neutral desires."),
	FOUR_LIKE("like", 4, 1, 2, 5, 3, "NPCs with this desire will more likely like/love but can still dislike/hate it."),
	FIVE_LOVE("love", 5, 0, 0, 3, 5, "NPCs with this desire will only like or love.");
	
	private String name;
	private int value;
	private int hate;
	private int dislike;
	private int like;
	private int love;
	private String tooltip;
	
	private FetishPreference(String name, int value, int hate, int dislike, int like, int love, String tooltip) {
		this.name=name;
		this.value=value;
		this.hate=hate;
		this.dislike=dislike;
		this.like=like;
		this.love=love;
		this.tooltip=tooltip;
	}
	
	public static FetishPreference valueOf(Integer i) {
		for(FetishPreference f: FetishPreference.values()) {
			if(f.getValue() == i) {
				return f;
			}
		}
		return null;
	}
	
	public String getName() {
		return name;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getHate() {
		return hate;
	}
	
	public int getDislike() {
		return dislike;
	}
	
	public int getLike() {
		return like;
	}
	
	public int getLove() {
		return love;
	}

	public String getTooltip() {
		return tooltip;
	}
}
