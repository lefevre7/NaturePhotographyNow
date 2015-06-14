package com.example.jeremy.naturephotographynow.tests;

import android.test.InstrumentationTestCase;

import com.example.jeremy.naturephotographynow.gallery.Gallery;
import com.example.jeremy.naturephotographynow.gallery.Picture;
import com.example.jeremy.naturephotographynow.gallery.Thumbnail;
import com.example.jeremy.naturephotographynow.gallery.HorizontalGalleryDisplayer;

/**
 * Created by Mezzo on 6/13/2015.
 */
public class GalleryHorizontalTest extends InstrumentationTestCase {

    public void testSetter(){
        Gallery gallery = new Gallery();
        Thumbnail testThumbnail = new Thumbnail();
        testThumbnail.setUrl("http://google.com/");
        gallery.setThumbnail(testThumbnail);
        assertEquals("http://google.com/", gallery.getThumbnail().getUrl());
    }

    public void testDisplay(){
        Gallery gallery = new Gallery();
        gallery.setDisplayer(new HorizontalGalleryDisplayer(gallery));
        gallery.populate();
        assertEquals("PictureOne PictureTwo ", gallery.getDisplayer().display());
    }

}
