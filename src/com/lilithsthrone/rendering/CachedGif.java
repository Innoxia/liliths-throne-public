package com.lilithsthrone.rendering;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
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
        try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream()) {
            // Load the first frame of the image
            BufferedImage firstFrame = ImageIO.read(f);
            updatePercentageWidth(firstFrame);
            width = firstFrame.getWidth();
            height = firstFrame.getHeight();

            // Resize the frame
            int[] targetSize = getAdjustedSize(300, 300);
            firstFrame = scaleDown(firstFrame, targetSize[0], targetSize[1]);

            // Convert to string
            ImageIO.setUseCache(false);
            ImageIO.write(firstFrame, "PNG", byteStream);
            thumbnailString = "data:image/png;base64," + Base64.getEncoder().encodeToString(byteStream.toByteArray());

            // Load the animation
            imageString = "data:image/gif;base64," + Base64.getEncoder().encodeToString(Files.readAllBytes(f.toPath()));

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String getThumbnailString() {
        return thumbnailString;
    }
}
