package com.example.jeremy.naturephotographynow.gallery;

/**
 * Created by Mezzo on 6/10/2015.
 */
public class HorizontalAlbumDisplayer extends AlbumDisplayer {
    public HorizontalAlbumDisplayer(Album album) {
        super(album);
    }

    @Override
    public String display() {
        String toReturn = "";
        for(Gallery gallery : this.getAlbum().galleries){
            toReturn += gallery.getName() + " ";
        }
        return toReturn;
    }
}
