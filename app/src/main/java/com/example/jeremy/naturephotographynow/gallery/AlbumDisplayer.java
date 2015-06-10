package com.example.jeremy.naturephotographynow.gallery;

/**
 * Created by Mezzo on 6/10/2015.
 */
public abstract class AlbumDisplayer {
    private Album album;

    public AlbumDisplayer(Album album){
        this.album = album;
    }

    public abstract String display();
}
