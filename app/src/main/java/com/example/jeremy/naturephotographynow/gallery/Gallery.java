package com.example.jeremy.naturephotographynow.gallery;

import java.util.List;

/**
 * Created by Mezzo on 6/10/2015.
 */
public class Gallery {
    List<Picture> picArray;
    GalleryDisplayer displayer;
    private Thumbnail thumbnail;

    public void populate(){

    }

    public GalleryDisplayer getDisplayer() {
        return displayer;
    }

    public void setDisplayer(GalleryDisplayer displayer) {
        this.displayer = displayer;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }
}
