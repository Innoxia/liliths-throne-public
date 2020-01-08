package com.lilithsthrone.rendering;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

/**
 * @since 0.3.0
 * @version 0.3.0
 * @author Addi
 */
public class CachedGif extends CachedImage {
    private String thumbnailString;

    @Override
    public boolean load(File f) {
        // Load the first frame of the image
        CachedImage firstFrame = new CachedImage();
        if (!firstFrame.load(f)) return false;
        thumbnailString = firstFrame.getThumbnailString();
        percentageWidth = firstFrame.getPercentageWidth();
        width = firstFrame.getWidth();
        height = firstFrame.getHeight();

        if (f.length() / 1024 > 10240) {
            // Animated image is too large, use the first frame instead
            imageString = firstFrame.getImageString();
            System.err.println("Warning: Animated image " + f.getName() + " is too large. Using first frame instead.");
        } else {
            // Load the animation
            try {
                imageString = "data:image/gif;base64,"
                        + Base64.getEncoder().encodeToString(Files.readAllBytes(f.toPath()));
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public String getThumbnailString() {
        return thumbnailString;
    }
}
