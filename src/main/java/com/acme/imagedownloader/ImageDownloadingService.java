package com.acme.imagedownloader;

import org.springframework.integration.annotation.Gateway;
import org.springframework.messaging.handler.annotation.Header;

/**
 * This interface will be implemented on the fly by Spring Integration.
 */
public interface ImageDownloadingService {

    @Gateway(requestChannel = "webPageInChannel", replyChannel = "imagesCountOutChannel")
    Integer downloadImagesFromWebPage(String url, @Header("downloadFolder") String downloadFolder);
}
