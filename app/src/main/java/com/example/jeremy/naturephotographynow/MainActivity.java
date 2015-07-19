package com.example.jeremy.naturephotographynow;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jeremy.naturephotographynow.activity.EventsActivity;
import com.example.jeremy.naturephotographynow.activity.GalleryActivity;
import com.example.jeremy.naturephotographynow.scraping.SiteMapper;

/**
 * On startup, this activity creates a Navigation Drawer with a list of items,
 * which, when clicked on, brings the user to a new activity.
 */
public class MainActivity extends ActionBarActivity {

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    int orient = 0;
    /** A tag for logging purposes */
    public static final String MAINTAG = "MainActivityTag";
    //Bundle savedInstanceState;

    /**
     * Sets the variables and Navigation Drawer
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(new Runnable() {
            public void run(){
                SiteMapper sm = SiteMapper.getInstance();
                try {
                    Log.i("Sitemapper", "Starting sitemap load");
                    sm.loadSitemap("http://naturephotographynow.com/sitemap.xml");
                    Log.i("Sitemapper", "Sitemap loaded");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        int orientation = getScreenOrientation();
        Log.i("MainActivityTag", "At on Create");
        //if (orientation==1)        // 1 for Configuration.ORIENTATION_PORTRAIT
        //{                          // 2 for Configuration.ORIENTATION_LANDSCAPE
            //your code             // 0 for Configuration.ORIENTATION_SQUARE
            //Log.i("MainActivityTag", "Portrait");
            setContentView(R.layout.activity_main);
        //}
        //else {
            //Log.i("MainActivityTag", "Landscape");
            //setContentView(R.layout.activity_main2);
        //}

        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public int getScreenOrientation()
    {
        Display getOrient = getWindowManager().getDefaultDisplay();
        int orientation = Configuration.ORIENTATION_UNDEFINED;
        if(getOrient.getWidth()==getOrient.getHeight()){
            orientation = Configuration.ORIENTATION_SQUARE;
        } else{
            if(getOrient.getWidth() < getOrient.getHeight()){
                orientation = Configuration.ORIENTATION_PORTRAIT;
            }else {
                orientation = Configuration.ORIENTATION_LANDSCAPE;
            }
        }
        return orientation;
    }
    /**
     * Creates a navigation list, adds items to it, and has a switch statement that starts a uri
     * or other activity.
     */
    private void addDrawerItems() {
        final String[] listArray = { "Galleries", "Events", "Blog", "Newsletter", "Contact the Artist", "About the Artist", "Artist's Resume",
                "Invest in Fine Art Photography", "Our Services", "About Download Doc", "Download Doc",
                "Product Information", "Workshops", "Books by the Artist", "Client Viewing", "Testimonials",
                "Guarantee", "Model Release", "Terms of Use", "Go to Website"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * When the user clicks on an activity, s/he is taken to it.
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selection = listArray[(int) id];
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri;
                Log.i("MainActivityTag", "Opening an Activity.");
                switch (selection) {
                    case "Galleries":
                        //change to Gallery activity
                        intent = new Intent(parent.getContext(), GalleryActivity.class);
                        startActivity(intent);
                        break;

                    case "Events":
                        //change to Events activity
                        intent = new Intent(parent.getContext(), EventsActivity.class);
                        startActivity(intent);
                        break;
                    case "Blog":
                        //change to the "Blog" web page
                        final Toast toast = Toast.makeText(MainActivity.this, "Click \"[Navigation Menu]\", then \"Blog\" on the " +
                                "top of the screen.", Toast.LENGTH_LONG);
                        toast.show();

                        /** Make the Toast show for 5 seconds */
                        new CountDownTimer(5000, 1000) {
                            /**
                             * Shows the toast message for millisUntilFinished
                             * @param millisUntilFinished
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
                        break;
                    case "Newsletter":
                        //change to the "Newsletter" web page
                        System.out.println("Here2");
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/news-letter/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "Contact the Artist":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/contact-the-artist/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "About the Artist":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/about-the-artist/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "Artist's Resume":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/artists-resume/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "Invest in Fine Art Photography":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/invest-in-fine-art-photography/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "Our Services":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/our-services/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "Download Doc":
                        final Toast toast2 = Toast.makeText(MainActivity.this, "Click  \"[Navigation Menu]\", then \"Download Doc\"" +
                                " on the top of the screen under \"Customer Service\", on the dropdown menu.", Toast.LENGTH_LONG);
                        toast2.show();

                        /** Make the Toast show for 7 seconds */
                        new CountDownTimer(7000, 1000) {
                            /**
                             * Shows the toast message for millisUntilFinished
                             * @param millisUntilFinished
                             */
                            public void onTick(long millisUntilFinished) {
                                toast2.show();
                            }

                            /**
                             * Shows the toast message until it's finished
                             */
                            public void onFinish() {
                                toast2.show();
                            }

                        }.start();

                        uri = Uri.parse("http://www.naturephotographynow.com/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "About Download Doc":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/about-download-dock/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "Product Information":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/product-information/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "Workshops":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/workshops/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "Books by the Artist":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/books-by-the-artist/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "Client Viewing":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/client-viewing/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "Testimonials":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/workshops/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "Guarantee":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/guarantee/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "Model Release":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/model-release/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "Terms of Use":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/terms-of-use/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;

                    default:
                        try {
                            Log.i("MainActivityTag", "Trying to sep into a web activity that is not defined.");
                            //a different way to change to a web activity
                            String url = "http://www.naturephotographynow.com/";
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                            break;
                        } catch (Exception e) {
                            Log.e("MainActivityTag", "Could not go into web activity.", e);

                            Toast.makeText(MainActivity.this, "Error with this Web Activity, push the back button", Toast.LENGTH_SHORT).show();

                        }
                }
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

    /**
     * Sync the toggle state after onRestoreInstanceState has happened.
     * @param savedInstanceState
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
        String url = "http://www.naturephotographynow.com/";
        /*
        WebView webview1 = (WebView) this.findViewById(R.id.webView3);
        webview1.getSettings().setJavaScriptEnabled(true);
        webview1.getSettings().setLoadWithOverviewMode(true);
        webview1.getSettings().setUseWideViewPort(true);
        webview1.getSettings().setBuiltInZoomControls(true);
        webview1.loadUrl(url);
        //*/
    }

    /**
     * Change the Drawer when the configuration is changed.
     * @param newConfig
     */
    //@Override
    //public void onConfigurationChanged(Configuration newConfig) {
    //    super.onConfigurationChanged(newConfig);
    //    mDrawerToggle.onConfigurationChanged(newConfig);
    //}

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen and changes the layout if the orientation changes
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
            orient = 1;
            //onCreate(this.savedInstanceState);
            //setContentView(R.layout.activity_main);
            //mDrawerList = (ListView)findViewById(R.id.navList);
            //mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
            //mActivityTitle = getTitle().toString();

            //addDrawerItems();
            //setupDrawer();

            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setHomeButtonEnabled(true);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
            orient = 0;
            //onCreate(savedInstanceState);
            //setContentView(R.layout.activity_main2);
            //mDrawerList = (ListView)findViewById(R.id.navList);
            //mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
            //mActivityTitle = getTitle().toString();

            //addDrawerItems();
            //setupDrawer();

            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    /**
     *
     * @param item
     * @return true if an item on the drawer is selected
     */
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