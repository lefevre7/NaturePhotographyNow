package com.example.jeremy.naturephotographynow.gallery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mezzo on 6/10/2015.
 */
public class VerticalGalleryDisplayer extends GalleryDisplayer {
    public VerticalGalleryDisplayer(Gallery gallery) {
        super(gallery);
    }

    @Override
    public String[] display() {
        List<String> pictureURLS = new ArrayList<String>();
        for(Picture picture : getGallery().picArray){
            pictureURLS.add(picture.getUrl());
        }
        String[] toReturn = new String[pictureURLS.size()];
        toReturn = pictureURLS.toArray(toReturn);
        return toReturn;
    }
}
