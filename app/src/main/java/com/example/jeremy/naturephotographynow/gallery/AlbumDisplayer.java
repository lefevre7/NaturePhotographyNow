package com.example.jeremy.naturephotographynow.gallery;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeremy.naturephotographynow.MainActivity;

/**
 * Created by Mezzo on 6/10/2015.
 */
public abstract class AlbumDisplayer {
    private Album album;

    public AlbumDisplayer(Album album) {
        this.album = album;
    }

    public abstract String display();

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
