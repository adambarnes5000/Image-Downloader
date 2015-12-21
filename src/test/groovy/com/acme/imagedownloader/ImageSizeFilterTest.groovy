package com.acme.imagedownloader

import spock.lang.Specification

import java.awt.image.BufferedImage


class ImageSizeFilterTest extends Specification{


    def "A large enough image should not be filtered out ie should progress"(){
        given:
            BufferedImage image = Mock()
        when:
            boolean shouldProgress = new ImageSizeFilter().shouldProgress(image, "filename.jpg")
        then:
            1 * image.getHeight() >> 1000
            1 * image.getWidth() >> 1000
            shouldProgress
    }

    def "A too narrow image should be filtered out ie should not progress"(){
        given:
            BufferedImage image = Mock()
        when:
            boolean shouldProgress = new ImageSizeFilter().shouldProgress(image, "filename.jpg")
        then:
            _ * image.getHeight() >> 1000
            1 * image.getWidth() >> 1
            !shouldProgress
    }

    def "A too short image should be filtered out ie should not progress"(){
        given:
            BufferedImage image = Mock()
        when:
            boolean shouldProgress = new ImageSizeFilter().shouldProgress(image, "filename.jpg")
        then:
            1 * image.getHeight() >> 1
            _ * image.getWidth() >> 1000
            !shouldProgress
    }

}
