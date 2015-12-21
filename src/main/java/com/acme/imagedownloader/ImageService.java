package com.acme.imagedownloader;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

/**
 * Used to wrap some static methods exposed by external libraries for manipulation of images. This class can be mocked
 * to avoid having to override static method behaviour.
 */
public class ImageService {

    /**
     * Saves an image in the specified format under filename, uses {@link ImageIO write( RenderedImage , String , File)}
     * @param image
     * @param format
     * @param filename
     * @throws IOException
     */
    public void saveImage(BufferedImage image, String format, String filename) throws IOException {
        ImageIO.write(image, format, new File(filename));
    }

    /**
     * Resizes an image to the supplied width while presrving width/height ratio.
     * Uses the Scalr library
     * @param imageIn
     * @param width
     * @return
     * @see <a href="http://www.thebuzzmedia.com/software/imgscalr-java-image-scaling-library/">Scalr</a>
     */
    public BufferedImage resize(BufferedImage imageIn, int width) {
        return Scalr.resize(imageIn, width);
    }
}
