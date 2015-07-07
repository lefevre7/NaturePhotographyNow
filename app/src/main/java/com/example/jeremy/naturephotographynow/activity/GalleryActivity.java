package com.example.jeremy.naturephotographynow.activity;

import android.content.Intent;
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
        String[] values = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
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

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

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
