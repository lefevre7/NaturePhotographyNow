package com.example.jeremy.naturephotographynow;

//base code from https://github.com/treehouse/android-navigation-drawer-final/blob/master/app/src/main/java/com/teamtreehouse/oslist/MainActivity.java
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jeremy.naturephotographynow.activity.EventsActivity;
import com.example.jeremy.naturephotographynow.activity.GalleryActivity;
import com.example.jeremy.naturephotographynow.activity.MainActivity2;
import com.example.jeremy.naturephotographynow.activity.WebActivity;


public class MainActivity extends ActionBarActivity {

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    public static final String MAINTAG = "MainActivityTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    //might learn how to do more from: http://www.101apps.co.za/articles/drawer-navigation.html
    //or maybe: http://www.recursiverobot.com/post/59404388046/implementing-the-new-navigation-drawer-in-android
    private void addDrawerItems() {
        final String[] listArray = { "Gallery", "Events", "Blog", "Newsletter", "Contact the Artist", "About the Artist", "Artist's Resume",
                "Invest in Fine Art Photography", "Our Services", "About Download Doc", "Download Doc",
                "Product Information", "Workshops", "Books by the Artist", "Client Viewing", "Testimonials",
                "Guarantee", "Model Release", "Terms of Use"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selection = listArray[(int)id];
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri;
                Log.i("MainActivityTag", "Opening an Activity.");
                switch(selection){
                    case "Gallery":
                        //change to Gallery activity
                        intent = new Intent(parent.getContext(), GalleryActivity.class);
                        startActivity(intent);
                        break;

                    case "Events":
                        //change to blog activity
                        intent = new Intent(parent.getContext(), EventsActivity.class);
                        startActivity(intent);
                        break;
                    case "Blog":
                        //String url = "http://www.naturephotographynow.com/";
                        //Intent i = new Intent(Intent.ACTION_VIEW);
                        //i.setData(Uri.parse(url));
                        //startActivity(i);
                        System.out.println("Here\n\n");
                        Toast.makeText(MainActivity.this, "Click \"[Navigation Menu]\", then \"Blog\" on the " +
                                "top of the screen.", Toast.LENGTH_LONG).show();
                        uri = Uri.parse("http://www.naturephotographynow.com/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);


                        //urlToLoad = "http://www.naturephotographynow.com/";
                        //myWebView.loadUrl(urlToLoad);
                        break;
                    case "Newsletter":
                        System.out.println("Here2");
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/news-letter/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        //urlToLoad = "http://www.naturephotographynow.com/#/page/news-letter/";
                        //myWebView.loadUrl(urlToLoad);
                        break;
                    case "Contact the Artist":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/contact-the-artist/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        //urlToLoad = "http://www.naturephotographynow.com/#/page/contact-the-artist/";
                        //myWebView.loadUrl(urlToLoad);
                        break;
                    case "About the Artist":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/about-the-artist/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        //urlToLoad = "http://www.naturephotographynow.com/#/page/about-the-artist/";
                        //myWebView.loadUrl(urlToLoad);
                        break;
                    case "Artist's Resume":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/artists-resume/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        //urlToLoad = "http://www.naturephotographynow.com/#/page/artists-resume/";
                        //myWebView.loadUrl(urlToLoad);
                        break;
                    case "Invest in Fine Art Photography":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/invest-in-fine-art-photography/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        //urlToLoad = "http://www.naturephotographynow.com/#/page/invest-in-fine-art-photography/";
                        //myWebView.loadUrl(urlToLoad);
                        break;
                    case "Our Services":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/our-services/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        //urlToLoad = "http://www.naturephotographynow.com/#/page/our-services/";
                        //myWebView.loadUrl(urlToLoad);
                        break;
                    case "Download Doc":
                        Toast.makeText(MainActivity.this, "Click  \"[Navigation Menu]\", then \"Download Doc\"" +
                                " on the top of the screen under \"Customer Service\", on the dropdown menu.",
                                Toast.LENGTH_LONG).show();
                        uri = Uri.parse("http://www.naturephotographynow.com/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        //urlToLoad = "http://www.naturephotographynow.com/#/page/about-download-dock/";
                        //myWebView.loadUrl(urlToLoad);
                        break;
                    case "About Download Doc":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/about-download-dock/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        //urlToLoad = "http://www.naturephotographynow.com/#/page/about-download-dock/";
                        //myWebView.loadUrl(urlToLoad);
                        break;
                    case "Product Information":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/product-information/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        //urlToLoad = "http://www.naturephotographynow.com/#/page/product-information/";
                        break;
                    case "Workshops":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/workshops/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        //urlToLoad = "http://www.naturephotographynow.com/#/page/workshops/";
                        break;
                    case "Books by the Artist":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/books-by-the-artist/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        //urlToLoad = "http://www.naturephotographynow.com/#/page/books-by-the-artist/";
                        break;
                    case "Client Viewing":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/client-viewing/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        //urlToLoad = "http://www.naturephotographynow.com/#/page/client-viewing/";
                        break;
                    case "Testimonials":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/workshops/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        //urlToLoad = "http://www.naturephotographynow.com/#/page/testimonials/";
                        break;
                    case "Guarantee":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/guarantee/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        //urlToLoad = "http://www.naturephotographynow.com/#/page/guarantee/";
                        break;
                    case "Model Release":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/model-release/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        //urlToLoad = "http://www.naturephotographynow.com/#/page/model-release/";
                        break;
                    case "Terms of Use":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/terms-of-use/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        //urlToLoad = "http://www.naturephotographynow.com/#/page/terms-of-use/";
                        break;

                    default:
                        try {
                        Log.i("MainActivityTag", "Trying to sep into a web activity that is not defined.");
                        //change to webview activity and pass in selection
                            String url = "http://www.naturephotographynow.com/";
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        //intent = new Intent(parent.getContext(), WebActivity.class);
                        //intent.putExtra("MenuSelection", selection);
                        //startActivity(intent);
                        break;
                        }

                        catch(Exception e) {
                            Log.e("MainActivityTag", "Could not go into web activity.", e);

                            Toast.makeText(MainActivity.this, "Error with this Web Activity, push the back button", Toast.LENGTH_SHORT).show();

                        }
                }

                //Toast.makeText(MainActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}