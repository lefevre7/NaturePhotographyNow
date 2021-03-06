package com.example.jeremy.naturephotographynow.activity;

import android.content.Intent;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jeremy.naturephotographynow.R;


import java.io.IOException;
import java.util.Arrays;
import java.util.GregorianCalendar;

/**
 * Has a WebView of the events created by the Artist and passes them to the calendar on the user's
 * phone when the user types them in.
 */
public class EventsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        //loading the WebView of the Events page
        String url = "http://www.naturephotographynow.com/#/page/events/";
        WebView webview = (WebView) this.findViewById(R.id.webView2);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.loadUrl(url);

        final Toast toast3 = Toast.makeText(EventsActivity.this, "Each letter in " +
                        "DD, MM, and YYYY must all be used.",
                Toast.LENGTH_LONG);
        toast3.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_events, menu);
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

    /**
     * Takes the Title, Location, Description, Year, Month, and Day
     * from the user input to the user's calandar app
     * */
        public void handleLoadFilesClick(View view){
            Intent calIntent = new Intent(Intent.ACTION_INSERT);
            calIntent.setType("vnd.android.cursor.item/event");

            EditText editText1 = (EditText) findViewById(R.id.Title);
            EditText editText2 = (EditText) findViewById(R.id.Location);
            EditText editText3 = (EditText) findViewById(R.id.Description);
            EditText editText4 = (EditText) findViewById(R.id.Year);
            EditText editText5 = (EditText) findViewById(R.id.Month);
            EditText editText6 = (EditText) findViewById(R.id.Day);

            String title = editText1.getText().toString();
            String loc = editText2.getText().toString();
            String desc = editText3.getText().toString();

            try {
                String yr = editText4.getText().toString();
                String mo = editText5.getText().toString();
                String da = editText6.getText().toString();

                Integer yr1 = Integer.parseInt(yr);
                Integer mo1 = Integer.parseInt(mo) - 1;//month "0" is January, not "1"
                Integer da1 = Integer.parseInt(da);

                calIntent.putExtra(CalendarContract.Events.TITLE, title);
                calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, loc);
                calIntent.putExtra(CalendarContract.Events.DESCRIPTION, desc);

                GregorianCalendar calDate = new GregorianCalendar(yr1, mo1, da1);
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                        calDate.getTimeInMillis());
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                        calDate.getTimeInMillis());

                startActivity(calIntent);
            }
            catch (Exception e) {
                final Toast toast = Toast.makeText(EventsActivity.this, "DD, MM, and YYYY must be" +
                                " numbers", Toast.LENGTH_LONG);
                toast.show();
            }
    }
}