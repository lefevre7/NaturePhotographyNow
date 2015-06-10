package com.example.jeremy.naturephotographynow.gallery;

import java.util.List;

/**
 * Created by Mezzo on 6/10/2015.
 */
public class Gallery {
    List<Picture> picArray;
    GalleryDisplayer displayer;

    public void populate(){

    }

    public GalleryDisplayer getDisplayer() {
        return displayer;
    }

    public void setDisplayer(GalleryDisplayer displayer) {
        this.displayer = displayer;
    }
}
