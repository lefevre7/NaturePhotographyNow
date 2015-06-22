package com.example.jeremy.naturephotographynow.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.jeremy.naturephotographynow.R;

public class WebActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        WebView myWebView = (WebView) findViewById(R.id.webView);
        String urlToLoad = "";
        Intent intent = getIntent();

        switch(intent.getStringExtra("Menu Selection")){
            case "Blog":
                urlToLoad = "http://www.naturephotographynow.com/";
                Toast.makeText(WebActivity.this, "Click \"Blog\" on the top of the screen.", Toast.LENGTH_SHORT).show();
                break;
            case "Newsletter":
                urlToLoad = "http://www.naturephotographynow.com/#/page/news-letter/";
                break;
            case "Contact the Artist":
                urlToLoad = "http://www.naturephotographynow.com/#/page/contact-the-artist/";
                break;
            case "About the Artist":
                urlToLoad = "http://www.naturephotographynow.com/#/page/about-the-artist/";
                break;
            case "Artist's Resume":
                urlToLoad = "http://www.naturephotographynow.com/#/page/artists-resume/";
                break;
            case "Invest in Fine Art Photography":
                urlToLoad = "http://www.naturephotographynow.com/#/page/invest-in-fine-art-photography/";
                break;
            case "Our Services":
                urlToLoad = "http://www.naturephotographynow.com/#/page/our-services/";
                break;
            case "Download Doc":
                urlToLoad = "http://www.naturephotographynow.com/#/page/about-download-dock/";
                Toast.makeText(WebActivity.this, "Click \"Customer Service\" on the top of the screen," +
                        "then Download Doc on the dropdown menu.", Toast.LENGTH_SHORT).show();
                break;
            case "About Download Doc":
                urlToLoad = "http://www.naturephotographynow.com/#/page/about-download-dock/";
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
        myWebView.loadUrl(urlToLoad);
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
