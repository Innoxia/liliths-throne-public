package com.lilithsthrone.rendering;

/**
 * @since 0.2.2
 * @version 0.2.2
 * @author Innoxia
 */
public class ArtistWebsite {

	private String name;
	private String URL;
	
	public ArtistWebsite(String name, String URL) {
		this.name = name;
		this.URL = URL;
	}
	
	public String getName() {
		return name;
	}
	
	public String getURL() {
		return URL;
	}
}
