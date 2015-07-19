package com.example.jeremy.naturephotographynow.gallery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mezzo on 6/10/2015.
 * Contains a list of Galleries.
 */
public class Album {
    Map<String, Gallery> galleries;
    AlbumDisplayer displayer;
    Thumbnail thumbnail;

    public Album(){
        galleries = new HashMap<String, Gallery>();
    }

    public void addGalleryToList(Gallery toAdd){
        galleries.put(toAdd.getName(), toAdd);
    }

    public Gallery getGalleryByName(String galleryName){
        return galleries.get(galleryName);
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
