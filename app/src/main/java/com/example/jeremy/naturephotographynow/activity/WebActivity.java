package com.example.jeremy.naturephotographynow.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.jeremy.naturephotographynow.R;

public class WebActivity extends ActionBarActivity {
    public static final String WEBTAG = "WebActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        WebView myWebView = (WebView) findViewById(R.id.webView);
        String urlToLoad = "";
        Intent intent = getIntent();
        Log.i("WebActivity", "Opening WebActivity.");

        try {
            switch(String.valueOf(intent.getStringExtra("Menu Selection"))){
                case "Blog":
                    String url = "http://www.naturephotographynow.com/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                   // Uri uri = Uri.parse("http://www.naturephotographynow.com/");
                    //Intent intent2 = new Intent(Intent.ACTION_VIEW, uri);
                    //startActivity(intent2);

                    System.out.println("Here\n\n");
                    Toast.makeText(WebActivity.this, "Click \"Blog\" on the top of the screen.", Toast.LENGTH_LONG).show();
                    //urlToLoad = "http://www.naturephotographynow.com/";
                    //myWebView.loadUrl(urlToLoad);
                    break;
                case "Newsletter":
                    System.out.println("Here");
                    urlToLoad = "http://www.naturephotographynow.com/#/page/news-letter/";
                    myWebView.loadUrl(urlToLoad);
                    break;
                case "Contact the Artist":
                    urlToLoad = "http://www.naturephotographynow.com/#/page/contact-the-artist/";
                    myWebView.loadUrl(urlToLoad);
                    break;
                case "About the Artist":
                    urlToLoad = "http://www.naturephotographynow.com/#/page/about-the-artist/";
                    myWebView.loadUrl(urlToLoad);
                    break;
                case "Artist's Resume":
                    urlToLoad = "http://www.naturephotographynow.com/#/page/artists-resume/";
                    myWebView.loadUrl(urlToLoad);
                    break;
                case "Invest in Fine Art Photography":
                    urlToLoad = "http://www.naturephotographynow.com/#/page/invest-in-fine-art-photography/";
                    myWebView.loadUrl(urlToLoad);
                    break;
                case "Our Services":
                    urlToLoad = "http://www.naturephotographynow.com/#/page/our-services/";
                    myWebView.loadUrl(urlToLoad);
                    break;
                case "Download Doc":
                    Toast.makeText(WebActivity.this, "Click \"Customer Service\" on the top of the screen," +
                            "then Download Doc on the dropdown menu.", Toast.LENGTH_LONG).show();
                    urlToLoad = "http://www.naturephotographynow.com/#/page/about-download-dock/";
                    myWebView.loadUrl(urlToLoad);
                    break;
                case "About Download Doc":
                    urlToLoad = "http://www.naturephotographynow.com/#/page/about-download-dock/";
                    myWebView.loadUrl(urlToLoad);
                    break;
                case "Product Information":
                    urlToLoad = "http://www.naturephotographynow.com/#/page/product-information/";
                    break;
                case "Workshops":
                    urlToLoad = "http://www.naturephotographynow.com/#/page/workshops/";
                    break;
                case "Books by the Artist":
                    urlToLoad = "http://www.naturephotographynow.com/#/page/books-by-the-artist/";
                    break;
                case "Client Viewing":
                    urlToLoad = "http://www.naturephotographynow.com/#/page/client-viewing/";
                    break;
                case "Testimonials":
                    urlToLoad = "http://www.naturephotographynow.com/#/page/testimonials/";
                    break;
                case "Guarantee":
                    urlToLoad = "http://www.naturephotographynow.com/#/page/guarantee/";
                    break;
                case "Model Release":
                    urlToLoad = "http://www.naturephotographynow.com/#/page/model-release/";
                    break;
                case "Terms of Use":
                    urlToLoad = "http://www.naturephotographynow.com/#/page/terms-of-use/";
                    break;
            }
        }
        catch(Exception e) {
            Log.e("WebActivity", "Null error.", e);

            Toast.makeText(WebActivity.this, "Error with this Web Activity, push the back button", Toast.LENGTH_SHORT).show();

        }
        myWebView.loadUrl(urlToLoad);
        Log.i("WebActivity", "Loading the URL.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web, menu);
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
