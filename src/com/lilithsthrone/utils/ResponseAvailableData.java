package com.lilithsthrone.utils;

/**
 * @since 0.1.69
 * @version 0.1.69
 * @author Innoxia
 */
public class ResponseAvailableData {

	public boolean available;
	public String tooltipText;
	public float corruptionIncrease;

	public ResponseAvailableData(boolean available, String tooltipText, float corruptionIncrease) {
		this.available = available;
		this.tooltipText = tooltipText;
		this.corruptionIncrease = corruptionIncrease;
	}

}
