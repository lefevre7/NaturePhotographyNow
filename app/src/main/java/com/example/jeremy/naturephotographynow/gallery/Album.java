package com.example.jeremy.naturephotographynow.gallery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mezzo on 6/10/2015.
 */
public class Album {
    List<Gallery> galleries;
    AlbumDisplayer displayer;
    Thumbnail thumbnail;

    public Album(){
        galleries = new ArrayList<Gallery>();
    }

    public void populate(){
        galleries.add(new Gallery());
    }

    public void addGalleryToList(Gallery toAdd){
        galleries.add(toAdd);
    }

    public AlbumDisplayer getDisplayer() {
        return displayer;
    }

    public void setDisplayer(AlbumDisplayer displayer) {
        this.displayer = displayer;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }
}
