package com.example.jeremy.naturephotographynow.gallery;

/**
 * Created by Mezzo on 6/10/2015.
 */
public class VerticalAlbumDisplayer extends AlbumDisplayer {
    public VerticalAlbumDisplayer(Album album) {
        super(album);
    }

    @Override
    public String display() {
        String toReturn = "";
        for(Gallery gallery : this.getAlbum().galleries){
            toReturn += gallery.getName() + "\n";
        }
        return toReturn;
    }
}
