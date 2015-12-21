package com.acme.imagedownloader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Required;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Takes a URL string for a web page and returns strings for each of the images on that page.
 * An exception will be thrown if no images are found.
 */
public class WebPageToImagesSplitter {

    private UrlParser urlParser;

    public List<String> getImagesUrls(String url) throws IOException {
        List<String> imageUrls = new ArrayList<String>();
        Document doc = urlParser.parseDocumentFromUrl(url);
        Elements elements = doc.select("img");
        if(elements.isEmpty()){
            throw new IllegalArgumentException("No images found!");
        }
        for (Element element : elements) {
            String src = element.attr("abs:src");
            imageUrls.add(src);
        }
        System.out.println("Found " + imageUrls.size() + " images at " + url);
        return imageUrls;
    }

    @Required
    public void setUrlParser(UrlParser urlParser) {
        this.urlParser = urlParser;
    }
}
