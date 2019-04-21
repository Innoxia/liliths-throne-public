package com.lilithsthrone.utils;

/**
 * @since 0.1.82
 * @version 0.1.82
 * @author Innoxia
 */
public class CreditsSlot {

	private String name, tagLine;
	
	private int uncommonCount, rareCount, epicCount, legendaryCount;

	public CreditsSlot(String name, String tagLine, int uncommonCount, int rareCount, int epicCount, int legendaryCount) {
		super();
		this.name = name;
		this.tagLine = tagLine;
		this.uncommonCount = uncommonCount;
		this.rareCount = rareCount;
		this.epicCount = epicCount;
		this.legendaryCount = legendaryCount;
	}

	public String getName() {
		return name;
	}

	public String getTagLine() {
		return tagLine;
	}

	public int getUncommonCount() {
		return uncommonCount;
	}

	public int getRareCount() {
		return rareCount;
	}

	public int getEpicCount() {
		return epicCount;
	}

	public int getLegendaryCount() {
		return legendaryCount;
	}
	
}
