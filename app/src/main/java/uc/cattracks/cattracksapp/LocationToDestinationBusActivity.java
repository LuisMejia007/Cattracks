package uc.cattracks.cattracksapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uc.cattracks.cattracksapp.models.Bus;
import uc.cattracks.cattracksapp.recycleview_adapters.BusNameSelectionAdapter;

public class LocationToDestinationBusActivity extends AppCompatActivity {
  // PATHWAYS TO OTHER ACTIVITIES
    Intent plan_trip_segue;
    Intent bus_updates_segue;
    Intent start_map;

    // USER INTERFACE ELEMENTS
    ImageButton navigation_button;   // Navigation menu structure
    LinearLayout navigation_menu;    // Opens / closes navigation menu
    ImageButton plan_trip_button;    // Opens trip planning activity
    ImageButton bus_alerts_button;   // Opens bus alerts Twitter feed activity.
    ImageButton map_button;          // Opens activity where users can select a stop to be showcased on a map (Google Maps)


   private static HomeActivity homeActivity;
   private static TextView welcomeTextView;
   private static RecyclerView availableBusesRecyclerView;
   private static RecyclerView.LayoutManager recyclerViewLayoutManager;
   private static BusNameSelectionAdapter busNameSelectionAdapter;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setting up toolbar menu
        setupToolBar();

        // Setting up navigation sliding menu
        setupNavigationMenu();

        setUpBusInformationWithRespectiveQueries();
        setUpRecylerViewAndAdapter();

    }


    // USER INTERFACE FUNCTIONS
    // Navigation Sliding Menu
    public void setupNavigationMenu(){
        // Setting up pathways to other activities
        plan_trip_segue = new Intent(this, LocationsList.class);
        bus_updates_segue = new Intent(this, BusUpdatesActivity.class);
        start_map = new Intent(this, MapStopsActivity.class);

        // Setting up user interface elements
        navigation_menu = findViewById(R.id.navigation_menu);


        plan_trip_button = findViewById(R.id.plan_trip_button);
        plan_trip_button.setOnClickListener((View v) -> {
            animate_navigation_menu();
            startActivity(plan_trip_segue);

        });


        bus_alerts_button = findViewById(R.id.bus_updates_button);
        bus_alerts_button.setOnClickListener((View v) -> {
            animate_navigation_menu();
            startActivity(bus_updates_segue);
        });


        // Set intent on MapStopsActivity
        map_button = findViewById(R.id.map_button);
        map_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(start_map);
                animate_navigation_menu();
            }
        });
    }



    public void animate_navigation_menu(){
        Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);


        if(navigation_menu.getVisibility()==View.INVISIBLE) {
            navigation_menu.startAnimation(slideUp);
            navigation_menu.setVisibility(View.VISIBLE);

        }else{
            navigation_menu.startAnimation(slideDown);
            navigation_menu.setVisibility(View.INVISIBLE);
        }
    }



    // Menu Toolbar
    public void setupToolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Setting up toolbar structure
        getMenuInflater().inflate(R.menu.toolbar_menu_2, menu);

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.navigation_button:
                animate_navigation_menu();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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



        welcomeTextView = findViewById(R.id.availableBusesTextView);
        locationAbb = HomeActivity.cattracksDatabase.daoAccess().getStopAbbFromName(locationName);
        destinationAbb = HomeActivity.cattracksDatabase.daoAccess().getStopAbbFromName(destinationName);

        locationAbb = locationAbb.replace("*", "");
        destinationAbb = destinationAbb.replace("*","");

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
        recyclerViewLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        availableBusesRecyclerView.setLayoutManager(recyclerViewLayoutManager);

        // Setting Up RecyclerView Adapter
        busNameSelectionAdapter = new BusNameSelectionAdapter(this, buses);
        availableBusesRecyclerView.setAdapter(busNameSelectionAdapter);

    }
}
