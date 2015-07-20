package com.example.jeremy.naturephotographynow.gallery;

/**
 * Created by Mezzo on 6/10/2015.
 * Displays a Horizontal Gallery
 */
public class HorizontalGalleryDisplayer extends GalleryDisplayer {

    /** So that we can extend all of the existing parent class' methods and variables */
    public HorizontalGalleryDisplayer(Gallery gallery) {
        super(gallery);
    }

    @Override
    public String[] display() {
        String toReturn = "";
        for(Picture picture : this.getGallery().picArray){
            toReturn += picture.getName() + " ";
        }
        return new String[] {toReturn};
    }
}
