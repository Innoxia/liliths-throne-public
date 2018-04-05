package com.lilithsthrone.rendering;

import java.util.List;

import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.2.2
 * @version 0.2.2
 * @author Innoxia
 */
public enum Artist {

	JAM("Jam",
		Colour.BASE_CRIMSON,
		"jam",
		Util.newArrayListOfValues(
			new ListValue<>(new ArtistWebsite("Jam's SFW Blog", "http://jamdrawers.tumblr.com/", "JAM_SFW_BLOG")),
			new ListValue<>(new ArtistWebsite("Jam's NSFW Blog", "http://ass-jam.tumblr.com/", "JAM_NSFW_BLOG"))));
	
	private String name;
	private Colour colour;
	private String folderName;
	private List<ArtistWebsite> websites;
	
	private Artist(String name, Colour colour, String folderName, List<ArtistWebsite> websites) {
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
