package com.acme.imagedownloader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

/**
 * Used to wrap a static method exposed by an external library parsing urls. This class can be mocked
 * to avoid having to override static method behaviour.
 */
public class UrlParser {

    public static final int TIMEOUT_MILLIS = 10000;

    /**
     * Parses a url string and returns a {@link Document}
     * @param url
     * @return
     * @throws IOException
     * @see <a href="http://jsoup.org">JSoup</a>
     */
    public Document parseDocumentFromUrl(String url) throws IOException{
        return Jsoup.parse(new URL(url), TIMEOUT_MILLIS);
    }
}
