package com.lilithsthrone.rendering;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 0.2.2
 * @version 0.2.2
 * @author Innoxia
 */
public class Artwork {
	
	private String name;
	private Artist artist;
	
	private int index;
	
	private List<String> clothedImages;
	private List<String> partialImages;
	private List<String> nakedImages;
	
	public Artwork(String name, Artist artist) {
		super();
		this.name = name;
		this.artist = artist;
		
		index = 0;
		
		this.clothedImages = new ArrayList<>();
		this.partialImages = new ArrayList<>();
		this.nakedImages = new ArrayList<>();
		
		int i=1;
		File f = new File("res/images/characters/"+artist.getFolderName()+"/"+name+"/clothed"+i+".png");
		
		while(f.exists()) {
			clothedImages.add(f.toURI().getPath().toString());
			i++;
			f = new File("res/images/characters/"+artist.getFolderName()+"/"+name+"/clothed"+i+".png");
		}
		
		i=1;
		f = new File("res/images/characters/"+artist.getFolderName()+"/"+name+"/partial"+i+".png");
		while(f.exists()) {
			partialImages.add(f.toURI().getPath().toString());
			i++;
			f = new File("res/images/characters/"+artist.getFolderName()+"/"+name+"/partial"+i+".png");
		}

		i=1;
		f = new File("res/images/characters/"+artist.getFolderName()+"/"+name+"/naked"+i+".png");
		while(f.exists()) {
			nakedImages.add(f.toURI().getPath().toString());
			i++;
			f = new File("res/images/characters/"+artist.getFolderName()+"/"+name+"/naked"+i+".png");
		}
		
	}

	public String getName() {
		return name;
	}

	public Artist getArtist() {
		return artist;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index % getTotalArtworkCount();
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
	
	public String getCurrentImage() {
		if(index < getClothedImages().size()) {
			return getClothedImages().get(index);
			
		} else if(index < getClothedImages().size() + getPartialImages().size()){
			return getPartialImages().get(index - getClothedImages().size());
			
		} else {
			return getNakedImages().get(index - getClothedImages().size() - getPartialImages().size());
		}
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
