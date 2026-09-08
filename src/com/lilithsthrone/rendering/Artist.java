package com.lilithsthrone.rendering;

import java.io.File;
import java.util.List;

import com.lilithsthrone.utils.colours.Colour;

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
	
	public int getArtworkCount() {
		int artworkCount = 0;

		File f = new File("res/images/characters/");
		if(f.exists() && f.isDirectory()) {
//			System.out.println("### "+this.folderName);
			for(String npcFolderName : f.list()) {
				// Don't count multi-boob and pregnant variations, and only count one Lilaya variation (LilayaPale):
				if(!npcFolderName.contains("MultiBoob")
						&& !npcFolderName.contains("Pregnant")
						&& !npcFolderName.contains("LilayaDark")
						&& !npcFolderName.contains("LilayaEbony")
						&& !npcFolderName.contains("LilayaLight")
						&& !npcFolderName.contains("LilayaOlive")) {
					File npcFolder = new File(f, npcFolderName);
					if(npcFolder.exists() && npcFolder.isDirectory()) {
						for(String artistFolderName : npcFolder.list()) {
							if(artistFolderName.equals(this.folderName)) {
								artworkCount++;
//								System.out.println(npcFolderName);
							}
						}
					}
				}
			}
		}
		
		return artworkCount;
	}
	
}
