package com.acme.imagedownloader;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImageSaver {

    private List<Integer> imageWidths;
    private List<String> imageFormats;

    private FileService fileService;
    private ImageService imageService;

    public boolean saveImages(BufferedImage image, @Header("downloadFolder") String downloadFolder, @Header("filename") String filename) {
        try {
            System.out.println("Saving file " + filename);
            saveOriginalFormat(downloadFolder, filename, image);
            for (Integer imageWidth : imageWidths) {
                BufferedImage resizedImage = imageService.resize(image, imageWidth);
                for (String imageFormat : imageFormats) {
                    imageService.saveImage(resizedImage, imageFormat, getFilename(downloadFolder, filename, imageWidth, imageFormat));
                }
            }
            return true;
        }catch(Exception e){
            //TODO Tweak error handling so exception can be thrown and dealt with by Spring Integration
            e.printStackTrace();
            return false;
        }
    }

    private String getFilename(String downloadFolder, String filename, Integer imageWidth, String imageFormat) {
        String nameWithoutSuffix = fileService.getFilenameWithoutSuffix(filename);
        return downloadFolder + "/" + nameWithoutSuffix + "_" + imageWidth + "." + imageFormat;
    }

    private void saveOriginalFormat(String downloadFolder, String filename, BufferedImage image) throws IOException {
        String fullPath = downloadFolder + "/" + filename;
        String originalFormat = fileService.getSuffixFromFilename(filename);
        imageService.saveImage(image, originalFormat, fullPath);
    }

    @Required
    public void setImageFormats(List<String> imageFormats) {
        this.imageFormats = imageFormats;
    }

    @Required
    public void setImageWidths(List<Integer> imageWidths) {
        this.imageWidths = imageWidths;
    }

    @Required
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Required
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }
}
