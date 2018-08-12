package com.lilithsthrone.rendering;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

/**
 * @since 0.2.5.5
 * @version 0.2.9
 * @author Addi
 */
public class CachedImage {
	protected String imageString = "";
	protected int width = 200, height = 200, percentageWidth = 35;

	/**
	 * Load an image from the given file path into a reusable string.
	 * @param f A File containing the path to a .jpg or .png image
	 * @return True if the image was successfully loaded, false otherwise
	 */
	public boolean load(File f) {
		try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream()) {
			// Load the image
			BufferedImage image = ImageIO.read(f);
			width = image.getWidth();
			height = image.getHeight();
			if(height == width) {
				percentageWidth = 45;
			} else if(height < width) {
				percentageWidth = 65;
			}

			// Resize image
			int[] targetSize = getAdjustedSize(600, 600);
			image = scaleDown(image, targetSize[0], targetSize[1]);
			width = image.getWidth();
			height = image.getHeight();

			// Convert to string
			ImageIO.setUseCache(false);
			ImageIO.write(image, "PNG", byteStream);
			imageString = "data:image/png;base64," + Base64.getEncoder().encodeToString(byteStream.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
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

	/**
	 * Scales down the given image using incremental, bilinear, anti-aliased sampling. Each step may only decrease the
	 * size by a factor of two, increasing the quality but taking additional time to complete. The parameters represent
	 * final values and do not respect the aspect ratio of the original image.
	 * @param original The BufferedImage to scale
	 * @param targetWidth The targeted width, ignoring aspect ratio
	 * @param targetHeight The targeted height, ignoring aspect ratio
	 * @return
	 */
	public static BufferedImage scaleDown(BufferedImage original, int targetWidth, int targetHeight) {
		int width = original.getWidth();
		int height = original.getHeight();
		BufferedImage rv = original;

		while (width > targetWidth || height > targetHeight) {
			// Determine target dimensions while never decreasing size by more than half
			if (width > targetWidth)
				width = Math.max(width / 2, targetWidth);

			if (height > targetHeight)
				height = Math.max(height / 2, targetHeight);

			// Setup render target
			BufferedImage step = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D canvas = step.createGraphics();
			canvas.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			canvas.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			canvas.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			// Render onto canvas
			canvas.drawImage(original, 0, 0, width, height, null);
			canvas.dispose();

			rv = step;
		}

		return rv;
	}
}
