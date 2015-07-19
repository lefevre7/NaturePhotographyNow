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
    String url;
    Thumbnail thumbnail;

    public Gallery(){
         picArray = new ArrayList<Picture>();
    }

    public GalleryDisplayer getDisplayer() {
        return displayer;
    }

    public void addPicture(Picture picture){
        picArray.add(picture);
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
