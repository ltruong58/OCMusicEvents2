package edu.orangecoastcollege.cs273.ocmusicevents;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class EventListActivity extends ListActivity {

    private ListView eventsListView;
    private Context context = this;
    private ArrayList<MusicEvent> allMusicEvents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_event_list);
        try {
            allMusicEvents = JSONLoader.loadJSONFromAsset(context);
        } catch (IOException e) {
            Log.e("Oc Music Events", "Error loading Json data." + e.getMessage());
        }
        eventsListView = (ListView) findViewById(R.id.eventListView);
        setListAdapter(new MusicEventAdapter(context, R.layout.music_event_list_item_layout, allMusicEvents));

        //setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MusicEvent.titles));

    }


    @Override
    protected void onListItemClick(ListView l, View v, int pos, long id)
    {


        MusicEvent clickedEvent = allMusicEvents.get(pos);
        String title = clickedEvent.getTitle();
        String date = clickedEvent.getDate();
        String day = clickedEvent.getDay();
        String time = clickedEvent.getTime();
        String location = clickedEvent.getLocation();
        String address1 = clickedEvent.getAddress1();
        String address2 = clickedEvent.getAddress2();
        String imageName = clickedEvent.getImageName();

        Intent detailsIntent = new Intent(this, EventDetailsActivity.class);

        detailsIntent.putExtra("Title", title);
        detailsIntent.putExtra("DateDay", date + " - " + day);
        detailsIntent.putExtra("Time", time);
        detailsIntent.putExtra("Location", location);
        detailsIntent.putExtra("Address1", address1);
        detailsIntent.putExtra("Address2", address2);
        detailsIntent.putExtra("ImageName", imageName);

        startActivity(detailsIntent);
    }
}
