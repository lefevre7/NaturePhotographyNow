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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Creates (will obtain) a list (that will be images, but right now is an example)
 * and puts them into an adapter to display
 */
public class HorizGalleryActivity extends ActionBarActivity {

    /** A tag for logging purposes */
    public static final String GalTAG = "GalleryActivityTag";

    //private Intent getPortInstance() {
    //    return intent1;
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
        final String[] values = new String[] { "Stock",
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
                "Architecture",
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

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();

                //instead of getting the activities through an html parser class,
                //they are specifically given.
                Uri uri;
                String selection = values[(int) id];
                Intent intent;
                Log.i("GalleryActivityTag", "Opening a GalleryActivity.");
                try {
                    switch (selection) {
                        case "Stock":
                            //change to Stock web page
                            uri = Uri.parse("http://naturephotographynow.com/gallery/stock-" +
                                    "gallery/wash-mon-2-x-1920/");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;

                        case "Utah":
                            //change to the Utah web page
                            uri = Uri.parse("http://www.naturephotographynow.com/#/gallery/" +
                                    "utah/autumn-2/");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;

                        case "Arizona":
                            //change to Arizona web page
                            uri = Uri.parse("http://www.naturephotographynow.com/#/gallery/" +
                                    "arizona/antelope-canyon-quad-image/");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;

                        case "California":
                            //change to the California web page
                            uri = Uri.parse("http://www.naturephotographynow.com/#/gallery/" +
                                    "california/3756-mono-lake-crop-x-1920/");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;
                        case "Colorado":
                            //change to Colorado web page
                            uri = Uri.parse("http://www.naturephotographynow.com/#/gallery/" +
                                    "colorado/alpine-tundra/");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;

                        case "Nevada":
                            //change to the Nevada web page
                            uri = Uri.parse("http://www.naturephotographynow.com/#/gallery/" +
                                    "nevada/a-stand-of-joshua-trees-at-red-rock/");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;

                        case "Wyoming":
                            //change to Wyoming web page
                            uri = Uri.parse("http://www.naturephotographynow.com/#/gallery/" +
                                    "wyoming/biscuit-basin-yellowstone/");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;

                        case "Black and White":
                            //change to the Black and White web page
                            uri = Uri.parse("http://www.naturephotographynow.com/#/gallery/" +
                                    "black-and-white/borax-wagon-2/");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;
                        case "Appalachia":
                            //change to Appalachia web page
                            uri = Uri.parse("http://www.naturephotographynow.com/#/gallery/" +
                                    "appalachia/appalachia-mile-post/");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;

                        case "Celestial Views":
                            //change to the Celestial Views web page
                            uri = Uri.parse("http://www.naturephotographynow.com/#/gallery/" +
                                    "celestial-views/crecent-moon/");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;

                        case "Flora and Fauna":
                            //change to Flora and Fauna web page
                            uri = Uri.parse("http://www.naturephotographynow.com/#/gallery/" +
                                    "flora-and-fauna/laceleaf-maple/");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;

                        case "Ghost Towns":
                            //change to the Ghost Towns web page
                            uri = Uri.parse("http://www.naturephotographynow.com/#/gallery/" +
                                    "ghost-towns/american-bottling-company/");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;
                        case "Architecture":
                            //change to Architecture web page
                            uri = Uri.parse("http://www.naturephotographynow.com/#/gallery/" +
                                    "architecture/autumn-under-ice/");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;

                        case "Novelty":
                            //change to the Novelty web page
                            uri = Uri.parse("http://www.naturephotographynow.com/#/gallery/" +
                                    "novelty/55-chevy-1/");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;

                        case "Wildlife":
                            //change to Wildlife web page
                            uri = Uri.parse("http://naturephotographynow.com/gallery/" +
                                    "wildlife/butterfly-2/");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;

                        case "Temples":
                            //change to the Temples web page
                            uri = Uri.parse("http://www.naturephotographynow.com/#/gallery/" +
                                    "temples/boundiful-temple-5/");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;
                        case "Seascapes":
                            //change to Seascapes web page
                            uri = Uri.parse("http://www.naturephotographynow.com/#/gallery/" +
                                    "seascapes/huntington-beach/");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;

                        case "Panoramic":
                            //change to the Panoramic web page
                            uri = Uri.parse("http://www.naturephotographynow.com/#/gallery/" +
                                    "panoramic/antelope-canyon-amber-panoramic/");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;

                        case "Nature's Patterns":
                            //change to Nature's Patterns web page
                            uri = Uri.parse("http://www.naturephotographynow.com/#/gallery/" +
                                    "natures-patterns/antelope-canyon-composite/");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;

                        case "People":
                            //change to the People web page
                            uri = Uri.parse("http://www.naturephotographynow.com/#/gallery/" +
                                    "people/coyote-buttes-gang/");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;
                    }
                }
                catch(Exception e) {
                    Log.e("GalleryActivityTag", "A GalleryActivity has stopped probably due " +
                            "to it's not being on the website anymore. This is the StackTrace:");
                    e.printStackTrace();
                    final Toast toast = Toast.makeText(HorizGalleryActivity.this, "Click " +
                            "\"[Navigation Menu]\", then scroll down to and click" +
                            " \"Galleries\" on the top of the screen.", Toast.LENGTH_LONG);
                    toast.show();

                    /** Make the Toast show for 5 seconds */
                    new CountDownTimer(5000, 1000) {
                        /**
                         * Shows the toast message for millisUntilFinished
                         * @param millisUntilFinished milliseconds until finished
                         */
                        public void onTick(long millisUntilFinished) {
                            toast.show();
                        }

                        /**
                         * Shows the toast message until it's finished
                         */
                        public void onFinish() {
                            toast.show();
                        }

                    }.start();

                    uri = Uri.parse("http://www.naturephotographynow.com/");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }

                //public void handleButtonClick(View view) {
                /*Intent intent = new Intent(parent.getContext(), PictureActivity.class);


                    EditText editText1 = (EditText) findViewById(R.id.Book);
                    EditText editText2 = (EditText) findViewById(R.id.Chapter);
                    EditText editText3 = (EditText) findViewById(R.id.Verse);

                    String book = editText1.getText().toString();
                    String chapter = editText2.getText().toString();
                    String verse = editText3.getText().toString();

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
                }*/

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
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "going to portrait", Toast.LENGTH_SHORT).show();
            //Intent inte = new Intent(getApplicationContext(), HorizGalleryActivity.class);
            //startActivity(inte);
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
