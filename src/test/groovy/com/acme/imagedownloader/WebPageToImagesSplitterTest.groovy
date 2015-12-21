package com.acme.imagedownloader

import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import spock.lang.Specification


class WebPageToImagesSplitterTest extends Specification{

    WebPageToImagesSplitter splitter

    def setup(){
        splitter = new WebPageToImagesSplitter()
        splitter.setUrlParser(Mock(UrlParser))
    }

    def "A list of all images from the web page is returned"(){
        Document doc = Mock()
        Element element1 = Mock()
        Element element2 = Mock()
        Elements elements = new Elements([element1, element2])
        when:
            List<String> result = splitter.getImagesUrls("testurl")
        then:
            1 * splitter.urlParser.parseDocumentFromUrl("testurl") >> doc
            1 * doc.select("img") >> elements
            1 * element1.attr("abs:src") >> "image1url"
            1 * element2.attr("abs:src") >> "image2url"
            result == ["image1url", "image2url"]
    }

    def "A webpage with no images throws a IllegalArgumentException"(){
        Document doc = Mock()
        Elements elements = new Elements([])
        when:
            List<String> result = splitter.getImagesUrls("testurl")
        then:
            1 * splitter.urlParser.parseDocumentFromUrl("testurl") >> doc
            1 * doc.select("img") >> elements
            thrown(IllegalArgumentException)
    }
}
