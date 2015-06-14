package com.example.jeremy.naturephotographynow.tests;

import android.test.InstrumentationTestCase;

import com.example.jeremy.naturephotographynow.gallery.Album;
import com.example.jeremy.naturephotographynow.gallery.Gallery;
import com.example.jeremy.naturephotographynow.gallery.VerticalAlbumDisplayer;
import com.example.jeremy.naturephotographynow.gallery.Thumbnail;

/**
 * Created by Mezzo on 6/13/2015.
 */
public class AlbumVerticalTest extends InstrumentationTestCase{

    public void testSetter(){
        Album album = new Album();
        Thumbnail testThumbnail = new Thumbnail();
        testThumbnail.setUrl("http://google.com/");
        album.setThumbnail(testThumbnail);
        assertEquals("http://google.com/", album.getThumbnail().getUrl());
    }

    public void testDisplay(){
        Album album = new Album();
        album.setDisplayer(new VerticalAlbumDisplayer(album));
        Gallery gallery1 = new Gallery();
        gallery1.setName("Arizona");
        Gallery gallery2 = new Gallery();
        gallery2.setName("Colorado");
        album.addGalleryToList(gallery1);
        album.addGalleryToList(gallery2);
        assertEquals("Arizona\nColorado\n", album.getDisplayer().display());
    }

}
