package com.acme.imagedownloader;

import org.springframework.messaging.handler.annotation.Header;

import java.awt.image.BufferedImage;

public class ImageSizeFilter {

    public static final int MIN_HEIGHT = 10;
    public static final int MIN_WIDTH = 10;

    /**
     * Returns true (do not filter) if an image is of the correct size.
     * @param image
     * @param filename
     * @return
     */
    public boolean shouldProgress(BufferedImage image, @Header("filename") String filename){
        boolean validSize = image.getHeight() > MIN_HEIGHT && image.getWidth() > MIN_WIDTH;
        if (!validSize){
            System.out.println("Ignoring " + filename + " as it does not meet size requirements");
        }
        return validSize;
    }
}
