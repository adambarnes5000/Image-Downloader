package com.acme.imagedownloader

import spock.lang.Specification

import java.awt.image.BufferedImage


class ImageSaverTest extends Specification{

    ImageSaver imageSaver

    def setup(){
        imageSaver = new ImageSaver()
        imageSaver.setFileService(new FileService())
        imageSaver.setImageService(Mock(ImageService))
        imageSaver.setImageFormats(["png","jpg"])
        imageSaver.setImageWidths([100,220,320])
    }

    def "An image is saved in its original size/format, and then in all sizes and formats specified returning true"(){
        given:
            BufferedImage image = Mock()
            BufferedImage image100 = Mock()
            BufferedImage image220 = Mock()
            BufferedImage image320 = Mock()
        when:
            boolean result = imageSaver.saveImages(image, "downloadFolder", "filename.jpg")
        then:
            1 * imageSaver.imageService.resize(image, 100) >> image100
            1 * imageSaver.imageService.resize(image, 220) >> image220
            1 * imageSaver.imageService.resize(image, 320) >> image320

            1 * imageSaver.imageService.saveImage(image, "jpg", "downloadFolder/filename.jpg")

            1 * imageSaver.imageService.saveImage(image100, "jpg", "downloadFolder/filename_100.jpg")
            1 * imageSaver.imageService.saveImage(image220, "jpg", "downloadFolder/filename_220.jpg")
            1 * imageSaver.imageService.saveImage(image320, "jpg", "downloadFolder/filename_320.jpg")

            1 * imageSaver.imageService.saveImage(image100, "png", "downloadFolder/filename_100.png")
            1 * imageSaver.imageService.saveImage(image220, "png", "downloadFolder/filename_220.png")
            1 * imageSaver.imageService.saveImage(image320, "png", "downloadFolder/filename_320.png")
            result
    }

    def "If an exception is thrown in the saving process false is returned"(){
        given:
            BufferedImage image = Mock()
        when:
            boolean result = imageSaver.saveImages(image, "downloadFolder", "filename.jpg")
        then:
            1 * imageSaver.imageService.resize(image, 100) >> {throw new IllegalArgumentException("Something went wrong")}
            noExceptionThrown()
            !result
    }
}
