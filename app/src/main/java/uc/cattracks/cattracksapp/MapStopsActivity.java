package uc.cattracks.cattracksapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import uc.cattracks.cattracksapp.constants.MapStops;
import uc.cattracks.cattracksapp.constants.mapStopsArraySource;
import uc.cattracks.cattracksapp.models.stops;
import uc.cattracks.cattracksapp.recycleview_adapters.MapStopsAdapter;

public class MapStopsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {


    // PATHWAYS TO OTHER ACTIVITIES
    Intent plan_trip_segue;
    Intent bus_updates_segue;
    Intent start_map;

    // USER INTERFACE ELEMENTS
    Toolbar toolbar;
    public static LinearLayout navigation_menu;    // Opens / closes navigation menu
    ImageButton navigation_button;   // Navigation menu structure
    ImageButton plan_trip_button;    // Opens trip planning activity
    ImageButton bus_alerts_button;   // Opens bus alerts Twitter feed activity.
    ImageButton map_button;          // Opens activity where users can select a stop to be showcased on a map (Google Maps)


    private MapStops[] stopLocations;
    private RecyclerView mapStopsRecyclerView;
    private RecyclerView.LayoutManager mapStopsRecyclerViewLayoutManager;
    private MapStopsAdapter mapsAdapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_stops_activity_layout);



        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        //stopLocations = HomeActivity.cattracksDatabase.daoAccess().getStops();
        stopLocations = mapStopsArraySource.mapStops;
        mapStopsRecyclerView = (RecyclerView) findViewById(R.id.map_stops_recyclerview);
        mapStopsRecyclerViewLayoutManager = new LinearLayoutManager(this);
        mapStopsRecyclerView.setLayoutManager(mapStopsRecyclerViewLayoutManager);

        // Setting up menu toolbar
        setupToolBar();
        setupNavigationMenu();

        mapsAdapter = new MapStopsAdapter(this, stopLocations);
        mapStopsRecyclerView.setAdapter(mapsAdapter);


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


        // Set intent on MapStopsActivity
        map_button = findViewById(R.id.map_button);
        map_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(start_map);
                animate_navigation_menu();
            }
        });

        plan_trip_button = findViewById(R.id.plan_trip_button);
        plan_trip_button.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View view){
                animate_navigation_menu();
                startActivity(plan_trip_segue);
            }
        });



        bus_alerts_button = findViewById(R.id.bus_updates_button);
        bus_alerts_button.setOnClickListener(new View.OnClickListener()  {
            @Override
             public void onClick(View view) {
                animate_navigation_menu();
                startActivity(bus_updates_segue);
            }

        });


    }


    public void animate_navigation_menu(){
        Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        // Hide / present navigation menu
        if (navigation_menu.getVisibility()==View.INVISIBLE) {
            navigation_menu.startAnimation(slideUp);
            navigation_menu.setVisibility(View.VISIBLE);



        } else {
            navigation_menu.startAnimation(slideDown);
            navigation_menu.setVisibility(View.INVISIBLE);

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
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        String userInput = newText.toLowerCase();
        List<MapStops> filteredList = new ArrayList<>();

        for(MapStops stop: stopLocations) {
            if(stop.getStopName().toLowerCase().contains(userInput)) {
                filteredList.add(stop);
            }
        }

        mapsAdapter.updateList(filteredList);
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
