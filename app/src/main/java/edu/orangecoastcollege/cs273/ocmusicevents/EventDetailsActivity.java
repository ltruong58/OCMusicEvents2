package edu.orangecoastcollege.cs273.ocmusicevents;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class EventDetailsActivity extends AppCompatActivity {

    private TextView eventTitleTextView;
    private TextView eventDateDayTextView;
    private TextView eventTimeTextView;
    private TextView eventLocationTextView;
    private TextView eventAddress1TextView;
    private TextView eventAddress2TextView;

    private ImageView eventImageView;

    // In order to use AssetManager, need to know Context;
    private Context context = (Context) this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        eventTitleTextView = (TextView) findViewById(R.id.eventTitleTextView);
        eventDateDayTextView = (TextView) findViewById(R.id.eventDateDayTextView);
        eventTimeTextView = (TextView) findViewById(R.id.eventTimeTextView);
        eventLocationTextView = (TextView) findViewById(R.id.eventLocationTextView);
        eventAddress1TextView = (TextView) findViewById(R.id.eventAddress1TextView);
        eventAddress2TextView = (TextView) findViewById(R.id.eventAddress2TextView);

        eventImageView = (ImageView) findViewById(R.id.eventImageView) ;

        Intent intentFromEventListActivity = getIntent();

        String titleText = intentFromEventListActivity.getStringExtra("Title");
        String dateDayText = intentFromEventListActivity.getStringExtra("DateDay");
        String timeText = intentFromEventListActivity.getStringExtra("Time");
        String locationText = intentFromEventListActivity.getStringExtra("Location");
        String address1Text = intentFromEventListActivity.getStringExtra("Address1");
        String address2Text = intentFromEventListActivity.getStringExtra("Address2");


        String imageName = intentFromEventListActivity.getStringExtra("ImageName");

        eventTitleTextView.setText(titleText);
        eventDateDayTextView.setText(dateDayText);
        eventTimeTextView.setText(timeText);
        eventLocationTextView.setText(locationText);
        eventAddress1TextView.setText(address1Text);
        eventAddress2TextView.setText(address2Text);


        // Load the image from Assets folder
        AssetManager am = context.getAssets();

        // Try to load image file
        try {
            InputStream stream = am.open(imageName);
            Drawable image = Drawable.createFromStream(stream, titleText);
            eventImageView.setImageDrawable(image);
        }
        catch (IOException ex) {
            Log.e("", "" + imageName + ex);
        }
    }
}
