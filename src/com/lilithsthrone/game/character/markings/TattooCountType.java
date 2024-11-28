package com.lilithsthrone.game.character.markings;

import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public enum TattooCountType {

	NUMBERS("numbers") {
		@Override
		public String convertInt(int input) {
			return String.valueOf(input);
		}
	},
	TALLY("tally markings") {
		@Override
		public String convertInt(int input) {
			return Util.intToTally(input, 50);
		}
	},
	NUMERALS("Roman numerals") {
		@Override
		public String convertInt(int input) {
			return Util.intToNumerals(input);
		}
	},
	WRITTEN("written out") {
		@Override
		public String convertInt(int input) {
			return Util.capitaliseSentence(Util.intToString(input));
		}
	};
	
	private String name;

	private TattooCountType(String name) {
		this.name = name;
	}

	public abstract String convertInt(int input);
	
	public String getName() {
		return name;
	}
}
