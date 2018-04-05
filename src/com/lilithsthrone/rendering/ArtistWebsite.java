package com.lilithsthrone.rendering;

/**
 * @since 0.2.2
 * @version 0.2.2
 * @author Innoxia
 */
public class ArtistWebsite {

	private String name;
	private String URL;
	private String buttonId;
	
	public ArtistWebsite(String name, String URL, String buttonId) {
		this.name = name;
		this.URL = URL;
		this.buttonId = buttonId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getURL() {
		return URL;
	}
	
	public String getButtonId() {
		return buttonId;
	}
	
}
