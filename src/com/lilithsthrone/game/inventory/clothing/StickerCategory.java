package com.lilithsthrone.game.inventory.clothing;

/**
 * @since 0.3.9.5
 * @version 0.3.9.5
 * @author Innoxia
 */
public class StickerCategory {

	private String id;
	private String name;
	private int priority;
	
	public StickerCategory(String id, String name, int priority) {
		this.id = id.replaceAll("'", "").replaceAll("\"", "").toLowerCase();
		this.name = name;
		this.priority = priority;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getPriority() {
		return priority;
	}
}
