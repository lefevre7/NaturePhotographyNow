package com.example.jeremy.naturephotographynow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.jeremy.naturephotographynow.R;
import com.example.jeremy.naturephotographynow.gallery.Album;
import com.example.jeremy.naturephotographynow.gallery.Gallery;
import com.example.jeremy.naturephotographynow.scraping.SiteScraper;

/**
 * Creates the GridView for an individual gallery
 * */
public class PictureActivity extends AppCompatActivity {

    /** A tag for logging purposes */
    public static final String PICTAG = "PictureActivityTag";
    /**
     * Creates the GridView for an individual gallery
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictureactivity);

        Intent intent = getIntent();

        String itemValue = intent.getStringExtra("selection");

        Album album = SiteScraper.getInstance().getAlbum();
        final Gallery g = album.getGalleryByName(itemValue);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this.getBaseContext(), g));

        Log.i("PictureActivityTag", "Opening PictureActivity.");
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                /*Toast.makeText(PictureActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();*/
                Intent intent = new Intent(parent.getContext(), IndividualPicture.class);
                intent.putExtra("imgurl", g.get(position).getPageUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Intent intent = getIntent();

        String itemValue = intent.getStringExtra("selection");

        // Set the text view as the activity layout
        //setContentView(textView);


        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pictureactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
