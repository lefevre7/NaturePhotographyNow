package com.example.jeremy.naturephotographynow.gallery;

/**
 * Created by Mezzo on 6/10/2015.
 */
public class HorizontalGalleryDisplayer extends GalleryDisplayer {
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
