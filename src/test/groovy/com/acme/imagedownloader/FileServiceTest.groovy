package com.acme.imagedownloader

import spock.lang.Specification


class FileServiceTest extends Specification{

    def "FileService methods work as expected"(){
        expect:
            new FileService().getFilenameFromFullUrl("http://www.acme.com/resources/cat.jpg")=="cat.jpg"
            new FileService().getFilenameWithoutSuffix("cat.jpg")=="cat"
            new FileService().getSuffixFromFilename("cat.jpg")=="jpg"
    }
}
