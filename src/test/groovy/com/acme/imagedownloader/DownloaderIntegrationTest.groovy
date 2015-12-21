package com.acme.imagedownloader

import spock.lang.Specification


class DownloaderIntegrationTest extends Specification{

    public static final String TEST_WEBSITE_URL = "http://www.adambarnes5000.weebly.com"
    public static final String TEST_DOWNLOADS_FOLDER = "test_downloads"

    private static final int VALID_IMAGES_COUNT = 3
    private static final int IMAGE_WIDTHS_COUNT = 3
    private static final int IMAGE_FORMATS_COUNT = 2


    def "All valid images from a website are downloaded"(){
        given:
            File downloads = new File(TEST_DOWNLOADS_FOLDER)
            emptyDirectoryAndThenDelete(downloads)
            String[] args = [TEST_WEBSITE_URL,downloads.getAbsolutePath()];
        when:
            Downloader.main(args);
        then:
            downloads.listFiles().size()==VALID_IMAGES_COUNT + (VALID_IMAGES_COUNT*IMAGE_WIDTHS_COUNT*IMAGE_FORMATS_COUNT)
    }

    def emptyDirectoryAndThenDelete(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return (path.delete());
    }

    def cleanupSpec() {
        emptyDirectoryAndThenDelete(new File(TEST_DOWNLOADS_FOLDER))
    }
}
