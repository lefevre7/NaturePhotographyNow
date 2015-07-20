package com.example.jeremy.naturephotographynow.gallery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Mezzo on 6/10/2015.
 * Displays a vertical Album
 */
public class VerticalAlbumDisplayer extends AlbumDisplayer {
    /** so that we can extend all of the existing parent class' methods and variables */
    public VerticalAlbumDisplayer(Album album) {
        super(album);
    }

    @Override
    public List<String> display() {
        List<String> toReturn = new ArrayList<String>();
        for(Gallery gallery : this.getAlbum().galleries.values()){
            toReturn.add(gallery.getName());
        }
        Collections.sort(toReturn);
        return toReturn;
    }
}
