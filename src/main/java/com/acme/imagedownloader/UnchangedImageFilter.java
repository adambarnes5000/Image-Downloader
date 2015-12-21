package com.acme.imagedownloader;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.messaging.handler.annotation.Header;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Return true (do not filter) if the version of the image on the web is newer than the
 * one on the filesystem, or no image exists on the filesystem
 */
public class UnchangedImageFilter {

    private FileService fileService;

    private static final int UNKNOWN=0;

    public boolean shouldProgress(String url, @Header("downloadFolder") String downloadFolder, @Header("filename") String filename) {
        long webLastModified = getWebLastModified(url);
        long fileLastModified = fileService.getLastModifiedForFile(downloadFolder + "/" + filename);
        boolean webVersionNewer =  webLastModified==UNKNOWN || webLastModified > fileLastModified;
        if(!webVersionNewer){
            System.out.println("Ignoring " + filename + " as local version is up to date");
        }
        return webVersionNewer;
    }

    private long getWebLastModified(String url){
        try {
            HttpURLConnection httpCon = (HttpURLConnection) new URL(url).openConnection();
            Long webLastModified = httpCon.getLastModified();
            httpCon.disconnect();
            return webLastModified;
        } catch (IOException e) {
            e.printStackTrace();
            return System.currentTimeMillis();
        }
    }

    @Required
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
