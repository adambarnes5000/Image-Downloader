package com.acme.imagedownloader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Downloads an image from a url string.
 */
public class ImageURLReader {

    public BufferedImage downloadImage(String url) throws IOException {
        return ImageIO.read(new URL(url));
    }
}
