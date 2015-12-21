package com.acme.imagedownloader;

import java.io.File;

public class FileService {

    public String getFilenameFromFullUrl(String url){
        return url.substring(url.lastIndexOf("/")+1,url.length());
    }

    public Long getLastModifiedForFile(String fullPath){
        File file = new File(fullPath);
        return file.lastModified();
    }

    public String getFilenameWithoutSuffix(String filename){
        return filename.substring(0,filename.lastIndexOf("."));
    }

    public String getSuffixFromFilename(String filename){
        return filename.substring(filename.lastIndexOf(".") + 1, filename.length());
    }
}
