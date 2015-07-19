package com.example.jeremy.naturephotographynow.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jeremy.naturephotographynow.R;
import com.example.jeremy.naturephotographynow.gallery.VerticalAlbumDisplayer;
import com.example.jeremy.naturephotographynow.scraping.SiteScraper;

import java.util.List;

/**
 * Creates (will obtain) a list (that will be images, but right now is an example)
 * and puts them into an adapter to display
 */
public class GalleryActivity extends ActionBarActivity {

    /** A tag for logging purposes */
    public static final String GalTAG = "GalleryActivityTag";

    ListView listView;


    /**
     * Creates (will obtain) a list (that will be images, but right now is an example)
     * and puts them into an adapter to display
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get ListView object from xml
        setContentView(R.layout.activity_gallery);

        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView
        SiteScraper sm = SiteScraper.getInstance();
        List<String> objValues = new VerticalAlbumDisplayer(sm.getAlbum()).display();

        final String[] values;
        values = objValues.toArray(new String[objValues.size()]);


        ArrayAdapter <String> adapter = new ArrayAdapter <String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        /*// Assign adapter to ListView
        list.setAdapter(adapter);

        // ListView Item Click Listener
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {*/


            /**
             * When clicked on, the user is taken to an individual gallery.
             * @param parent passes in a reference to the parent
             * @param view passes in the view
             * @param position passes in the position of the list item
             * @param id passes in the id of the list item
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                /*// ListView Clicked item value
                String  itemValue    = (String) list.getItemAtPosition(position);*/

                /*
               //Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();
                //*/

                //instead of getting the activities through an html parser class,
                //they are specifically given.
                Uri uri;
                String selection = values[(int) id];
                Intent intent = new Intent(parent.getContext(), PictureActivity.class);
                Log.i("GalleryActivityTag", "Opening a GalleryActivity.");
                intent.putExtra("selection", selection);

                Log.i("GalleryActivityTag", "Trying to open PictureActivity.");
                try {
                    startActivity(intent);
                }
                catch(Exception e) {
                    Log.e("GalleryActivityTag", "PictureActivity has stopped");
                    e.printStackTrace();
                }

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gallery, menu);
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
