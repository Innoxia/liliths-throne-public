package com.lilithsthrone.game.dialogue.places.submission.dicePoker;

import com.lilithsthrone.rendering.SVGImages;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public enum DiceFace {
	
	ONE(1, "one", "&#9856;") {
		@Override
		public String getSVGString() {
			return SVGImages.SVG_IMAGE_PROVIDER.getDice1();
		}
	},
	TWO(2, "two", "&#9857;") {
		@Override
		public String getSVGString() {
			return SVGImages.SVG_IMAGE_PROVIDER.getDice2();
		}
	},
	THREE(3, "three", "&#9858;") {
		@Override
		public String getSVGString() {
			return SVGImages.SVG_IMAGE_PROVIDER.getDice3();
		}
	},
	FOUR(4, "four", "&#9859;") {
		@Override
		public String getSVGString() {
			return SVGImages.SVG_IMAGE_PROVIDER.getDice4();
		}
	},
	FIVE(5, "five", "&#9860;") {
		@Override
		public String getSVGString() {
			return SVGImages.SVG_IMAGE_PROVIDER.getDice5();
		}
	},
	SIX(6, "six", "&#9861;") {
		@Override
		public String getSVGString() {
			return SVGImages.SVG_IMAGE_PROVIDER.getDice6();
		}
	};

	private int value;
	private String name;
	private String htmlDisplay;
	
	private DiceFace(int value, String name, String htmlDisplay) {
		this.value = value;
		this.name = name;
		this.htmlDisplay = htmlDisplay;
	}
	
	public abstract String getSVGString();
	
	public int getValue() {
		return value;
	}
	
	public String getName() {
		return name;
	}
	
	public String getHtmlDisplay() {
		return htmlDisplay;
	}
	
	public static DiceFace getFaceFromInt(int face) {
		for(DiceFace df : DiceFace.values()) {
			if(df.getValue()==face) {
				return df;
			}
		}
		System.err.println("There is no DiceFace with the value '"+face+"'...");
		return DiceFace.ONE;
	}
}
