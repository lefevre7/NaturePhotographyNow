package com.example.jeremy.naturephotographynow.gallery;

import java.util.List;

/**
 * Created by Mezzo on 6/10/2015.
 * Displays a Horizontal Album
 */
public class HorizontalAlbumDisplayer extends AlbumDisplayer {

    /** So that we can extend all of the existing parent class' methods and variables */
    public HorizontalAlbumDisplayer(Album album) {
        super(album);
    }

    @Override
    public List display() {
        String toReturn = "";
        for(Gallery gallery : this.getAlbum().galleries.values()){
            toReturn += gallery.getName() + " ";
        }
        return null;
    }
}
