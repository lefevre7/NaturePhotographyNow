package com.example.jeremy.naturephotographynow.gallery;

import java.io.File;

/**
 * Created by Mezzo on 6/10/2015.
 */
public class Image {
    private String url;
    //&mezzo: for some reason BufferedImage won't import :/
    private File imageData;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public File getImageData() {
        return imageData;
    }

    public void setImageData(File imageData) {
        this.imageData = imageData;
    }

}
