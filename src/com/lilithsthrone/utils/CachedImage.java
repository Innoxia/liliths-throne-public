package com.lilithsthrone.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

/**
 * @since 0.2.5.5
 * @version 0.2.5.5
 * @author Addi
 */
public class CachedImage {
	protected String imageString = "";
	protected int width = 200, percentageWidth = 35;

	/**
	 * Load an image from the given file path into a reusable string.
	 * @param f A File containing the path to a .jpg or .png image
	 * @throws IOException
	 */
	public void load(File f) throws IOException {
		// Load the image
		BufferedImage image = ImageIO.read(f);
		width = image.getWidth();
		if(image.getHeight() == width) {
			percentageWidth = 45;
		} else if(image.getHeight() < width) {
			percentageWidth = 65;
		}

		// Convert to string
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		ImageIO.write(image, f.getName().endsWith(".jpg") ? "JPG" : "PNG", byteStream);
		imageString = "data:image/png;base64," + Base64.getEncoder().encodeToString(byteStream.toByteArray());
	}

	public String getImageString() {
		return imageString;
	}

	public int getWidth() {
		return width;
	}

	public int getPercentageWidth() {
		return percentageWidth;
	}
}
