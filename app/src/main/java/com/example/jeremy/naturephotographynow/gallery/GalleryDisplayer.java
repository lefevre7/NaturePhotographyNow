package com.example.jeremy.naturephotographynow.gallery;

/**
 * Created by Mezzo on 6/10/2015.
 */
public abstract class GalleryDisplayer {
    private Gallery gallery;

    public GalleryDisplayer(Gallery gallery){
        this.gallery = gallery;
    }

    public abstract String[] display();

    public Gallery getGallery() {
        return gallery;
    }

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
    }
}
