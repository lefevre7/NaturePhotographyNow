package com.example.jeremy.naturephotographynow.tests;

import android.test.InstrumentationTestCase;

import com.example.jeremy.naturephotographynow.gallery.Album;
import com.example.jeremy.naturephotographynow.gallery.Gallery;
import com.example.jeremy.naturephotographynow.gallery.HorizontalAlbumDisplayer;
import com.example.jeremy.naturephotographynow.gallery.Thumbnail;

/**
 * Created by Jeremy on 6/12/2015.
 */
public class AlbumHorizontalTest extends InstrumentationTestCase{

    public void testSetter(){

        Album album = new Album();
        Thumbnail testThumbnail = new Thumbnail();
        testThumbnail.setUrl("http://google.com/");
        album.setThumbnail(testThumbnail);
        assertEquals("http://google.com/", album.getThumbnail().getUrl());
    }

    public void testDisplay(){
        Album album = new Album();
        album.setDisplayer(new HorizontalAlbumDisplayer(album));
        Gallery gallery1 = new Gallery();
        gallery1.setName("Nevada");
        Gallery gallery2 = new Gallery();
        gallery2.setName("Utah");
        album.addGalleryToList(gallery1);
        album.addGalleryToList(gallery2);
        assertEquals("Nevada Utah ", album.getDisplayer().display());
    }

}
