package com.lilithsthrone.game.inventory.item;

import java.util.Map;

/**
 * @since 0.4
 * @version 0.4.0
 * @author Innoxia
 */
public class SvgInformation {

	private int zLayer;
	private String pathName;
	private int imageSize;
	private int imageRotation;
	private Map<String, String> replacements;
	
	public SvgInformation(int zLayer, String pathName, int imageSize, int imageRotation, Map<String, String> replacements) {
		this.zLayer = zLayer;
		this.pathName = pathName;
		this.imageSize = imageSize;
		this.imageRotation = imageRotation;
		this.replacements = replacements;
	}

	public int getZLayer() {
		return zLayer;
	}
	
	public String getPathName() {
		return pathName;
	}

	public int getImageSize() {
		return imageSize;
	}

	public int getImageRotation() {
		return imageRotation;
	}

	public Map<String, String> getReplacements() {
		return replacements;
	}
	
}
