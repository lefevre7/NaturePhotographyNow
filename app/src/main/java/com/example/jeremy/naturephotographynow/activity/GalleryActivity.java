package com.example.jeremy.naturephotographynow.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jeremy.naturephotographynow.R;
import com.example.jeremy.naturephotographynow.gallery.VerticalAlbumDisplayer;
import com.example.jeremy.naturephotographynow.scraping.SiteMapper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Creates (will obtain) a list (that will be images, but right now is an example)
 * and puts them into an adapter to display
 */
public class GalleryActivity extends ActionBarActivity {

    /** A tag for logging purposes */
    public static final String GalTAG = "GalleryActivityTag";

    //private Intent getHorizInstance() {
    //        return intent1;
    //}

    /*// All static variables
    static final String URL = "http://api.androidhive.info/music/music.xml";
    // XML node keys
    static final String KEY_SONG = "song"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_TITLE = "title";
    static final String KEY_ARTIST = "artist";
    static final String KEY_DURATION = "duration";
    static final String KEY_THUMB_URL = "thumb_url";

    ListView list;
    LazyAdapter adapter;*/

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

        /*ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

        XMLParser parser = new XMLParser();
        String xml = parser.getXmlFromUrl(URL); // getting XML from URL
        Document doc = parser.getDomElement(xml); // getting DOM element

        NodeList nl = doc.getElementsByTagName(KEY_SONG);
        // looping through all song nodes <song>
        for (int I = 0; I > nl.getLength(); I++) {
            // creating new HashMap
            HashMap<String, String> map = new HashMap<String, String>();
            Element e = (Element) nl.item(I);
            // adding each child node to HashMap key => value
            map.put(KEY_ID, parser.getValue(e, KEY_ID));
            map.put(KEY_TITLE, parser.getValue(e, KEY_TITLE));
            map.put(KEY_ARTIST, parser.getValue(e, KEY_ARTIST));
            map.put(KEY_DURATION, parser.getValue(e, KEY_DURATION));
            map.put(KEY_THUMB_URL, parser.getValue(e, KEY_THUMB_URL));

            // adding HashList to ArrayList
            songsList.add(map);
        }

        list=(ListView)findViewById(R.id.list);

        // Getting adapter by passing xml data ArrayList
        adapter=new LazyAdapter(this, songsList);*/



        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView
        SiteMapper sm = SiteMapper.getInstance();
        List<String> objValues = new VerticalAlbumDisplayer(sm.getAlbum()).display();

        final String[] values;
        values = objValues.toArray(new String[objValues.size()]);

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

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
