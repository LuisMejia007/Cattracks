package uc.cattracks.cattracksapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import uc.cattracks.cattracksapp.models.stops;
import uc.cattracks.cattracksapp.recycleview_adapters.DestinationsAdapter;

public class DestinationsListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, View.OnClickListener {
    // PATHWAYS TO OTHER ACTIVITIES
    Intent plan_trip_segue;
    Intent bus_updates_segue;


    // USER INTERFACE ELEMENTS
    Toolbar toolbar;
    public static LinearLayout navigation_menu;    // Opens / closes navigation menu
    ImageButton navigation_button;   // Navigation menu structure
    ImageButton plan_trip_button;    // Opens trip planning activity
    ImageButton bus_alerts_button;   // Opens bus alerts Twitter feed activity.


    private RecyclerView destinationsRecyclerView;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    private DestinationsAdapter adapter;
    private List<stops> stopDestinations;
    public static Button confirmDestinationSelectionButton;
    private static String locationSelectedByUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinations_list);

        // Setting up menu toolbar 
        setupToolBar();

        // Setting up navigation sliding menu 
        setupNavigationMenu();


        stopDestinations = new ArrayList<>();

        // Connect layout button to our button member confirmDestinationSelectionButton
        confirmDestinationSelectionButton = findViewById(R.id.confirmDestinationSelectionButton);
        TextView textView = findViewById(R.id.stop_destinations);



        // Getting intent's data that was passed on from previous activity
        locationSelectedByUser = getIntent().getStringExtra("Stop Selected: ");
        // Intent's data will be used for database query
        stopDestinations = HomeActivity.cattracksDatabase.daoAccess().getFilteredDestinations(locationSelectedByUser);

        // Setting up Recycler View
        destinationsRecyclerView = findViewById(R.id.destinationLocations);
        destinationsRecyclerView.setHasFixedSize(true);

        // Set Recycler View Layout
        recyclerViewLayoutManager = new LinearLayoutManager(this);
        destinationsRecyclerView.setLayoutManager(recyclerViewLayoutManager);

        // Setting up adapter
        adapter = new DestinationsAdapter(this, stopDestinations);
        destinationsRecyclerView.setAdapter(adapter);

    }



   @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        String userInput = newText.toLowerCase();
        List<stops> filteredList = new ArrayList<>();

        for(stops stop: stopDestinations) {
            if(stop.getS_name().toLowerCase().contains(userInput)) {
                filteredList.add(stop);
            }
        }
        adapter.updateList(filteredList);
        return true;
    }

    @Override
    public void onClick(View view) {

       Intent intent = new Intent(this, LocationToDestinationBusActivity.class);
       startActivity(intent);
    }


    // USER INTERFACE FUNCTIONS
    // Navigation Sliding Menu
    public void setupNavigationMenu(){
        // Setting up pathways to other activities
        plan_trip_segue = new Intent(this, LocationsList.class);
        bus_updates_segue = new Intent(this, BusUpdatesActivity.class);


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
    }


    public void animate_navigation_menu(){
        Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        // Hide / present navigation menu
        if (navigation_menu.getVisibility()==View.INVISIBLE) {
            navigation_menu.startAnimation(slideUp);
            navigation_menu.setVisibility(View.VISIBLE);

            // Hide confirmation menu
            confirmDestinationSelectionButton.setVisibility(View.INVISIBLE);

        } else {
            navigation_menu.startAnimation(slideDown);
            navigation_menu.setVisibility(View.INVISIBLE);

            // Present confirmation button if applicable
            if (adapter.bus_stop_selected) {
                confirmDestinationSelectionButton.setVisibility(View.VISIBLE);
            }
        }
    }



    // Menu Toolbar
    public void setupToolBar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Setting up toolbar structure
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        // Setting up the search filters functionality
        MenuItem menuItem = menu.findItem(R.id.stopLocationsSearchFilter);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setOnSearchClickListener((View) -> {
            // Hide navigation menu if applicable
            navigation_menu.setVisibility(android.view.View.INVISIBLE);
        });

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.navigation_button:
                toolbar.collapseActionView();
                animate_navigation_menu();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
