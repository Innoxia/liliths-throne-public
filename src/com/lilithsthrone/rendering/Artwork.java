package com.lilithsthrone.rendering;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.2.2
 * @version 0.2.10
 * @author Innoxia
 */
public class Artwork {
	
	private Artist artist;
	
	private int index;
	
	private List<String> clothedImages;
	private List<String> partialImages;
	private List<String> nakedImages;
	
	public static List<Artist> allArtists;
	static {
		allArtists = new ArrayList<>();
		
		File dir = new File("res/images/characters");
		
		if(dir.exists()) {
			FilenameFilter textFilter = new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.toLowerCase().endsWith(".xml");
				}
			};
			
			for(File subFile : dir.listFiles(textFilter)) {
				if (subFile.exists()) {
					try {
						DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
						Document doc = dBuilder.parse(subFile);
						
						// Cast magic:
						doc.getDocumentElement().normalize();
						
						Element artistElement = (Element) doc.getElementsByTagName("artist").item(0);
						
						String artistName = artistElement.getAttribute("name");
						Colour colour = Colour.valueOf(artistElement.getAttribute("colour"));
						String folderName = artistElement.getAttribute("folderName");
								
						List<ArtistWebsite> websites = new ArrayList<>();
						
						NodeList nodes = artistElement.getElementsByTagName("website");
						for(int i=0; i < nodes.getLength(); i++){
							Element websiteNode = (Element) nodes.item(i);
							websites.add(new ArtistWebsite(websiteNode.getAttribute("title"), websiteNode.getAttribute("url")));
						}
						
						allArtists.add(new Artist(artistName, colour, folderName, websites));
						
					} catch(Exception ex) {
					}
				}
			}

			// Add artist template for custom art
			allArtists.add(new Artist("Custom", Colour.BASE_GREY, "custom", new ArrayList<>()));
		}
	}
	
	public Artwork(File folder, Artist artist) {
		this.artist = artist;

		index = 0;

		this.clothedImages = new ArrayList<>();
		this.partialImages = new ArrayList<>();
		this.nakedImages = new ArrayList<>();

		// Add all images to their respective lists
		for (File f : folder.listFiles((dir, name) -> name.endsWith(".jpg") || name.endsWith(".png"))) {
			if (f.getName().startsWith("partial"))
				partialImages.add(f.getAbsolutePath());
			else if (f.getName().startsWith("naked"))
				nakedImages.add(f.getAbsolutePath());
			else
				clothedImages.add(f.getAbsolutePath());
		}
	}

	public Artist getArtist() {
		return artist;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		index = index % getTotalArtworkCount();
		if(index < 0) {
			index = getTotalArtworkCount() + index;
		}
		this.index = index;
	}

	public void incrementIndex(int increment) {
		setIndex(this.index + increment);
	}

	public int getTotalArtworkCount() {
		return clothedImages.size() + partialImages.size() + nakedImages.size();
	}
	
	public boolean isCurrentImageClothed() {
		return index < getClothedImages().size();
	}
	
	public File getCurrentImage() {
		String path;
		if(index < getClothedImages().size()) {
			path = getClothedImages().get(index);
			
		} else if(index < getClothedImages().size() + getPartialImages().size()){
			path = getPartialImages().get(index - getClothedImages().size());
			
		} else {
			path = getNakedImages().get(index - getClothedImages().size() - getPartialImages().size());
		}

		if (path.isEmpty()) return null;
		return new File(path);
	}
	
	public List<String> getClothedImages() {
		return clothedImages;
	}

	public List<String> getPartialImages() {
		return partialImages;
	}

	public List<String> getNakedImages() {
		return nakedImages;
	}
	
}
