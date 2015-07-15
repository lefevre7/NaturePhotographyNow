package com.example.jeremy.naturephotographynow.activity;

import android.content.Intent;
import android.content.res.Configuration;
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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Creates (will obtain) a list (that will be images, but right now is an example)
 * and puts them into an adapter to display
 */
public class GalleryActivity extends ActionBarActivity {

    /** A tag for logging purposes */
    public static final String GalTAG = "GalleryActivityTag";

    // All static variables
    static final String URL = "http://api.androidhive.info/music/music.xml";
    // XML node keys
    static final String KEY_SONG = "song"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_TITLE = "title";
    static final String KEY_ARTIST = "artist";
    static final String KEY_DURATION = "duration";
    static final String KEY_THUMB_URL = "thumb_url";

    ListView list;
    LazyAdapter adapter;

    //ListView listView;

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

        ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

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
        adapter=new LazyAdapter(this, songsList);

        /*

        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView
        String[] values = new String[] { "Stock",
                "Utah",
                "Arizona",
                "California",
                "Colorado",
                "Nevada",
                "Wyoming",
                "Black and White",
                "Appalachia",
                "Celestial Views",
                "Flora and Fauna",
                "Ghost Towns",
                "Seascapes",
                "Panoramic",
                "Nature's Patterns",
                "Temples",
                "Architechure",
                "Novelty",
                "Wildlife",
                "People"
        };

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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {*/
        // Assign adapter to ListView
        list.setAdapter(adapter);

        // ListView Item Click Listener
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            /**
             * When clicked on, the user is taken to an individual gallery.
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                /*// ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);*/

                // ListView Clicked item value
                String  itemValue    = (String) list.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();
                //public void handleButtonClick(View view) {
                    Intent intent = new Intent(parent.getContext(), PictureActivity.class);
                    /*EditText editText1 = (EditText) findViewById(R.id.Book);
                    EditText editText2 = (EditText) findViewById(R.id.Chapter);
                    EditText editText3 = (EditText) findViewById(R.id.Verse);

                    String book = editText1.getText().toString();
                    String chapter = editText2.getText().toString();
                    String verse = editText3.getText().toString();*/

                    intent.putExtra("theString", itemValue);

                    //intent.putExtra("theBook", book);
                    //intent.putExtra("theChapter", chapter);
                    //intent.putExtra("theVerse", verse);
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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen and changes the layout if the orientation changes
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
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
