package com.acme.imagedownloader

import spock.lang.Specification


class UnchangedImageFilterTest extends Specification{

    UnchangedImageFilter filter

    def setup(){
        filter = new UnchangedImageFilter()
        filter.setFileService(Mock(FileService))
    }

    def "A more recent web version should not be filtered out ie should progress"(){
        when:
            boolean shouldProgress = filter.shouldProgress("http://adambarnes5000.weebly.com/uploads/5/6/7/5/56756781/9170030_orig.jpg","downloadFolder", "9170030_orig.jpg")
        then:
            1 * filter.fileService.getLastModifiedForFile("downloadFolder/9170030_orig.jpg") >> 1 //Jan 1st 1970
            shouldProgress
    }

    def "A less recent web version should be filtered out ie should not progress"(){
        when:
            boolean shouldProgress = filter.shouldProgress("http://adambarnes5000.weebly.com/uploads/5/6/7/5/56756781/9170030_orig.jpg","downloadFolder", "9170030_orig.jpg")
        then:
            1 * filter.fileService.getLastModifiedForFile("downloadFolder/9170030_orig.jpg") >> System.currentTimeMillis()
            !shouldProgress
    }

    def "If the age of a web image can not be found it should not be filtered"(){
        when:
            boolean shouldProgress = filter.shouldProgress("http://invalidurl.com/invalid.jpg","downloadFolder", "9170030_orig.jpg")
        then:
            1 * filter.fileService.getLastModifiedForFile("downloadFolder/9170030_orig.jpg") >> System.currentTimeMillis()
            shouldProgress
    }
}
