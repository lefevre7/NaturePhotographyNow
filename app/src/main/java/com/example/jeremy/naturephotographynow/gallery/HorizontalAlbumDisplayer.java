package com.example.jeremy.naturephotographynow.gallery;

/**
 * Created by Mezzo on 6/10/2015.
 */
public class HorizontalAlbumDisplayer extends AlbumDisplayer {
    public HorizontalAlbumDisplayer(Album album) { //so that we can extend all of the existing
        super(album);                              //parent class' methods and variables
    }

    @Override
    public String display() {
        String toReturn = "";
        for(Gallery gallery : this.getAlbum().galleries.values()){
            toReturn += gallery.getName() + " ";
        }
        return toReturn;
    }
}
