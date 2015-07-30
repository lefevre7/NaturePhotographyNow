package com.example.jeremy.naturephotographynow;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.jeremy.naturephotographynow.activity.EventsActivity;
import com.example.jeremy.naturephotographynow.activity.GalleryActivity;
import com.example.jeremy.naturephotographynow.scraping.SiteScraper;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

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
    protected int prog = 0;
    int error = -2;
    int e2 = 0;
    SiteScraper sm = SiteScraper.getInstance();
    /** A tag for logging purposes */
    public static final String MAINTAG = "MainActivityTag";

    /**
     * Sets the variables and Navigation Drawer
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //*
        try {
            new Thread(new Runnable() {
            public void run(){


                Log.i("Sitemapper", "Starting sitemap load");

                runOnUiThread(new Runnable() {
                    public void run() {
                        dispMessage1();
                    }
                });

               //*/
                  try {
                    if (sm.init("http://naturephotographynow.com/sitemap.xml") == -1){
                           runOnUiThread(new Runnable() {
                            public void run() {
                                dispMessage();
                            }
                        });

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            dispMessage();
                        }
                    });
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            dispMessage();
                        }
                    });
                } catch (SAXException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            dispMessage();
                        }
                    });
                }//*/

                Log.i("Sitemapper", "Sitemap loaded");
                runOnUiThread(new Runnable() {
                    public void run() {
                        dispMessage2();
                    }
                });
            }
        }).start();
        } catch (Exception e) {
            e.printStackTrace();
            runOnUiThread(new Runnable() {
                public void run() {
                    dispMessage();
                }
            });
        }

/*/
            runOnUiThread(new Runnable() {
                              public void run() {
                                  //while ((prog = sm.getProgress()) != .9) {
                                  if ((prog = sm.getProgress()) > .1 && prog < .15) {
                                  try {
                                      Log.i("Sitemapper", "Displaying Percentage");
                                      dispProg(prog * 100);
                                      //Thread.sleep(10000);
                                  } catch (InterruptedException e) {
                                      e.printStackTrace();
                                  }
                                  }
                              }
                          }
            );
        //*/



        //*/

        /* //didn't work
        class handleLoadFilesClickAsyncTask extends AsyncTask<ArrayList<String>, Integer, ArrayList<String>> {

            @Override
            protected ArrayList<String> doInBackground(ArrayList<String>... strings) {

                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                SiteScraper sm = SiteScraper.getInstance();
                int i = 0;
                try {
                    Log.i("Sitemapper", "Starting sitemap load");
                    sm.init("http://naturephotographynow.com/sitemap.xml");
                    publishProgress(sm.getProgress()*100);
                    Log.i("Sitemapper", "Sitemap loaded");
                } catch (Exception e) {
                    e.printStackTrace();
                    final Toast toast = Toast.makeText(MainActivity.this, "There is not a good " +
                            "connection with www.naturephotographynow.com. Please close the app " +
                            "and try again later.", Toast.LENGTH_LONG);
                    toast.show();
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... progress) {
                //update the progress
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setProgress(progress[0]);
            }
        }

        doInBackground(ArrayList<String>... strings);//*/

        initImageLoader(getApplicationContext());

        Log.i("MainActivityTag", "At on Create");

        setContentView(R.layout.activity_main);

        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public void dispProg(Integer k) throws InterruptedException {
        String k1 = k.toString();
        final Toast toast1 = Toast.makeText(MainActivity.this, "Downloading:" +
                " " + k1 + "%", Toast.LENGTH_LONG);
        toast1.show();
        Thread.sleep(10000);
    }

    public void dispMessage(){
        e2 = 1;
        final Toast toast = Toast.makeText(MainActivity.this, "There is not a good " +
                "connection with www.naturephotographynow.com. Please close the app " +
                "and try again later.", Toast.LENGTH_LONG);
        toast.show();
        /** Make the Toast show for 30 seconds */
        new CountDownTimer(30000, 1000) {
            /**
             * Shows the toast message for millisUntilFinished
             * @param millisUntilFinished long
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
        //System.exit(-1);
    }

    public void dispMessage1(){
        final Toast toast = Toast.makeText(MainActivity.this, "Downloading " +
                "Nature Photography Now Content." , Toast.LENGTH_LONG);
        toast.show();
        final Toast toast1 = Toast.makeText(MainActivity.this, "May take 10-20 minutes depending " +
                        "on connection.",
                Toast.LENGTH_LONG);
        toast1.show();
        final Toast toast2 = Toast.makeText(MainActivity.this, "App must also be running for the " +
                        "downloading to complete.",
                Toast.LENGTH_LONG);
        toast2.show();
        final Toast toast21 = Toast.makeText(MainActivity.this, "(So don't press the back button" +
                        " to go home--press the home button.)",
                Toast.LENGTH_LONG);
        toast21.show();
        final Toast toast3 = Toast.makeText(MainActivity.this, "Content in Galleries must also " +
                        "be completely downloaded in order to be clicked on.",
                Toast.LENGTH_LONG);
        toast3.show();
        /** Make the Toast show for 10 seconds */
        new CountDownTimer(10000, 1000) {
            /**
             * Shows the toast message for millisUntilFinished
             * @param millisUntilFinished long
             */
            public void onTick(long millisUntilFinished) {
                toast3.show();
            }

            /**
             * Shows the toast message until it's finished
             */
            public void onFinish() {
                toast3.show();
            }

        }.start();
    }

    public void dispMessage2(){
        if (e2 == 0) {
            final Toast toast = Toast.makeText(MainActivity.this, "Downloading " +
                    "Complete", Toast.LENGTH_LONG);
            toast.show();
            /** Make the Toast show for 20 seconds */
            new CountDownTimer(20000, 1000) {
                /**
                 * Shows the toast message for millisUntilFinished
                 *
                 * @param millisUntilFinished long
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
        }
    }



    private static void initImageLoader(Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024);
        config.tasksProcessingOrder(QueueProcessingType.LIFO);

        ImageLoader.getInstance().init(config.build());
    }

    /**
     * Creates a navigation list, adds items to it, and has a switch statement that starts a uri
     * or other activity.
     */
    private void addDrawerItems() {
        final String[] listArray = { "Galleries", "Events", "Blog", "Newsletter",
                "Contact the Artist", "About the Artist", "Artist's Resume",
                "Invest in Fine Art Photography", "Our Services", "About Download Doc",
                "Download Doc", "Product Information", "Workshops", "Books by the Artist",
                "Client Viewing", "Testimonials", "Guarantee", "Model Release", "Terms of Use",
                "Go to Website"};
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
                        final Toast toast = Toast.makeText(MainActivity.this,
                                "Click \"[Navigation Menu]\", then \"Blog\" on the " +
                                "top of the screen.", Toast.LENGTH_LONG);
                        toast.show();

                        /** Make the Toast show for 5 seconds */
                        new CountDownTimer(5000, 1000) {
                            /**
                             * Shows the toast message for millisUntilFinished
                             * @param millisUntilFinished long
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
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/contact-" +
                                "the-artist/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "About the Artist":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/about-the-" +
                                "artist/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "Artist's Resume":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/artists-" +
                                "resume/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "Invest in Fine Art Photography":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/invest-in-" +
                                "fine-art-photography/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "Our Services":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/our-services/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "Download Doc":
                        final Toast toast2 = Toast.makeText(MainActivity.this,
                                "Click  \"[Navigation Menu]\", then \"Download Doc\"" +
                                " on the top of the screen under \"Customer Service\", " +
                                        "on the dropdown menu.", Toast.LENGTH_LONG);
                        toast2.show();

                        /** Make the Toast show for 7 seconds */
                        new CountDownTimer(7000, 1000) {
                            /**
                             * Shows the toast message for millisUntilFinished
                             * @param millisUntilFinished long
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
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/about-" +
                                "download-dock/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "Product Information":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/product-" +
                                "information/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "Workshops":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/workshops/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "Books by the Artist":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/books-by-the-" +
                                "artist/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "Client Viewing":
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/client-" +
                                "viewing/");
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
                        uri = Uri.parse("http://www.naturephotographynow.com/#/page/model-" +
                                "release/");
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
                            Log.i("MainActivityTag", "Trying to sep into a web activity that is " +
                                    "not defined.");
                            //a different way to change to a web activity
                            String url = "http://www.naturephotographynow.com/";
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                            break;
                        } catch (Exception e) {
                            Log.e("MainActivityTag", "Could not go into web activity.", e);

                            Toast.makeText(MainActivity.this, "Error with this Web Activity, push " +
                                    "the back button", Toast.LENGTH_SHORT).show();

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
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Log.i("MainActivityTag", "At post create.");
        mDrawerToggle.syncState();
        String url = "http://www.naturephotographynow.com/";
        //*
        WebView webview1 = (WebView) this.findViewById(R.id.webView3);
        webview1.getSettings().setJavaScriptEnabled(true);
        webview1.getSettings().setLoadWithOverviewMode(true);
        webview1.getSettings().setUseWideViewPort(true);
        webview1.getSettings().setBuiltInZoomControls(true);
        webview1.loadUrl(url);
        //*/

        /*/
            //runOnUiThread(new Runnable() {
                              //public void run() {
                                  while ((prog = sm.getProgress()) != 1) {
                                  try {
                                      Log.i("Sitemapper", "Displaying Percentage");
                                      dispProg(prog * 100);
                                      //Thread.sleep(10000);
                                  } catch (InterruptedException e) {
                                      e.printStackTrace();
                                  }
                                  }
                              //}
                          //}
           // );
        //*/
    }

    /**
     * Change the Drawer when the configuration is changed.
     * @param newConfig Configuration
     */
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

    /**
     * @param item MenuItem
     * @return boolean
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