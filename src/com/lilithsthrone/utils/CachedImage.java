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
	protected int width = 200, height = 200, percentageWidth = 35;

	/**
	 * Load an image from the given file path into a reusable string.
	 * @param f A File containing the path to a .jpg or .png image
	 * @throws IOException
	 */
	public void load(File f) throws IOException {
		// Load the image
		BufferedImage image = ImageIO.read(f);
		width = image.getWidth();
		height = image.getHeight();
		if(height == width) {
			percentageWidth = 45;
		} else if(height < width) {
			percentageWidth = 65;
		}

		// Convert to string
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		ImageIO.write(image, f.getName().endsWith(".jpg") ? "JPG" : "PNG", byteStream);
		imageString = "data:image/png;base64," + Base64.getEncoder().encodeToString(byteStream.toByteArray());
	}

	/**
	 * Retrieve the image in base 64 encoded string format. The string must start with 'data:image/png;base64'.
	 * @return The image as base64 string
	 */
	public String getImageString() {
		return imageString;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() { return height; }

	/**
	 * Retrieve the width percentage that the image is supposed to take on the character information page. The value is
	 * calculated using the width to height ratio.
	 * @return 35 if the image is vertical, 45 if it's squared and 65 if it's horizontal.
	 */
	public int getPercentageWidth() {
		return percentageWidth;
	}

	/**
	 * Calculate and retrieve the size of the image after fitting it inside a box with the given dimensions.
	 * @param maxWidth Width of the box to fit the image into
	 * @param maxHeight Height of the box to fit the image into
	 * @return An integer array with the format [width, height] containing the fitted dimensions.
	 */
	public int[] getAdjustedSize(int maxWidth, int maxHeight) {
		// Calculate ratio between desired and original dimensions
		float widthRatio = (float) maxWidth / (float) width;
		float heightRatio = (float) maxHeight / (float) height;
		// Use the one that produces the smaller image as scaling factor
		float scale = Math.min(widthRatio, heightRatio);
		return new int[]{(int)(width * scale), (int)(height * scale)};
	}
}
