package com.example.jeremy.naturephotographynow.gallery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mezzo on 6/10/2015.
 */
public class Gallery {
    List<Picture> picArray;
    GalleryDisplayer displayer;
    String name;
    Thumbnail thumbnail;

    public Gallery(){
         picArray = new ArrayList<Picture>();
    }

    public void populate(){
        Picture picture1 = new Picture();
        picture1.setName("PictureOne");
        Picture picture2 = new Picture();
        picture2.setName("PictureTwo");
        picArray.add(picture1);
        picArray.add(picture2);
    }

    public GalleryDisplayer getDisplayer() {
        return displayer;
    }

    public void setDisplayer(GalleryDisplayer displayer) {
        this.displayer = displayer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }
}
