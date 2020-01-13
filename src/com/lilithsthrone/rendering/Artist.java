package com.lilithsthrone.rendering;
import java.util.List;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.2.2
 * @version 0.2.2
 * @author Innoxia
 */
public class Artist {
	
	private String name;
	private Colour colour;
	private String folderName;
	private List<ArtistWebsite> websites;
	
	public Artist(String name, Colour colour, String folderName, List<ArtistWebsite> websites) {
		this.name = name;
		this.colour = colour;
		this.folderName = folderName;
		this.websites = websites;
	}
	
	public String getName() {
		return name;
	}
	
	public Colour getColour() {
		return colour;
	}

	public String getFolderName() {
		return folderName;
	}
	
	public List<ArtistWebsite> getWebsites() {
		return websites;
	}
	
}
