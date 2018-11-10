package uc.cattracks.cattracksapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import uc.cattracks.cattracksapp.models.Bus;

public class LocationToDestinationBusActivity extends AppCompatActivity {

   private static List<Bus> busesFromLocationToDestination;
   private static List<Integer> busIDs;
   private static List<String> userSelectedStopAbbreviations;
   private static HomeActivity homeActivity;

   // From stop names get stop abbreviations
    // From stop abbreviations get bus IDs
    // From bus IDs get bus names

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_to_destination_bus);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String location = extras.getString("Location");
        String destination= extras.getString("Destination");

        System.out.println("Location: " + location + " Destination: " + destination);
        //userSelectedStopAbbreviations =
    }
}
