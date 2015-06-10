package com.example.jeremy.naturephotographynow.gallery;

import java.util.List;

/**
 * Created by Mezzo on 6/10/2015.
 */
public class Album {
    List<Gallery> galleries;
    AlbumDisplayer displayer;

    public void populate(){

    }

    public void addGalleryToList(Gallery toAdd){

    }

    public AlbumDisplayer getDisplayer() {
        return displayer;
    }

    public void setDisplayer(AlbumDisplayer displayer) {
        this.displayer = displayer;
    }

}
