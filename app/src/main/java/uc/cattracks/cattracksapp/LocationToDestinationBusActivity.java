package uc.cattracks.cattracksapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uc.cattracks.cattracksapp.models.Bus;
import uc.cattracks.cattracksapp.recycleview_adapters.BusNameSelectionAdapter;

public class LocationToDestinationBusActivity extends AppCompatActivity {


   private static HomeActivity homeActivity;
   private static TextView welcomeTextView;
   private static RecyclerView availableBusesRecyclerView;
   private static RecyclerView.LayoutManager recyclerViewLayoutManager;
   private static BusNameSelectionAdapter busNameSelectionAdapter;

   private static List<String> stopAbbreviations;
   private static List<String> buses;
   private static List<Bus> busesFromLocationToDestination;
   private static List<Integer> busIDs;
   private static List<String> userSelectedStopAbbreviations;


   public String locationAbb = "";
   public String destinationAbb = "";
   public String locationName = "";
   public String destinationName = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_to_destination_bus);


        setUpBusInformationWithRespectiveQueries();
        setUpRecylerViewAndAdapter();

    }

    public void setUpBusInformationWithRespectiveQueries() {

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        locationName = extras.getString("Location");
        destinationName = extras.getString("Destination");
        buses = new ArrayList<>();

        // From stop names get stop abbreviations
        // From stop abbreviations get bus IDs
        // From bus IDs get bus names

        stopAbbreviations = HomeActivity.cattracksDatabase.daoAccess().getStopAbbsFromNames(locationName, destinationName);



        welcomeTextView = findViewById(R.id.availableBusesTextView);

        for(String stopAbb: stopAbbreviations) {
            System.out.println(stopAbb + "\n");
        }

        locationAbb = stopAbbreviations.get(0).replace("*", "");
        destinationAbb = stopAbbreviations.get(1).replace("*","");

        busIDs = HomeActivity.cattracksDatabase.daoAccess().getBusIDsFromStopAbbs(locationAbb, destinationAbb);


        for(Integer busID: busIDs) {
            buses.add(HomeActivity.cattracksDatabase.daoAccess().getBusNameFromBusID(busID));
        }

        for(String bus : buses) {
            System.out.println(bus);
        }


        if(buses.size() > 1) {
            welcomeTextView.setText(buses.size() + " Buses From " + locationName + " To " + destinationName);
        } else if (buses.size() == 1) {
            welcomeTextView.setText(buses.size() + " Bus From " + locationName + " To " + destinationName);
        } else {
            welcomeTextView.setText("No Buses Available For Selected Route");
        }

    }

    public void setUpRecylerViewAndAdapter() {

        // Setting Up RecyclerView
        availableBusesRecyclerView = findViewById(R.id.availableBusesRecyclerView);
        availableBusesRecyclerView.setHasFixedSize(true);

        // Setting Up RecyclerView Layout Manager with a Horizontal layout
        recyclerViewLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        availableBusesRecyclerView.setLayoutManager(recyclerViewLayoutManager);

        // Setting Up RecyclerView Adapter
        busNameSelectionAdapter = new BusNameSelectionAdapter(this, buses);
        availableBusesRecyclerView.setAdapter(busNameSelectionAdapter);

    }
}
