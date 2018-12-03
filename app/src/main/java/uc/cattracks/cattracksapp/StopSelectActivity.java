package uc.cattracks.cattracksapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import uc.cattracks.cattracksapp.models.stops;
import android.support.v7.widget.RecyclerView;
import uc.cattracks.cattracksapp.recycleview_adapters.TableStopSelectAdapter;

/*
 * STOP SELECT ACTIVITY
 * This activity creates the buttons with the users particular bus stops & prepares to communicate
 * the selected stops to NeatTableActivity to create the landscape row display
 */
public class StopSelectActivity extends AppCompatActivity implements SearchView.OnQueryTextListener
{
    //Setting up CardView with use of Recycle View
    private RecyclerView stopSelectRecyclerView;

    //private StopsAdapter adapter;
    private TableStopSelectAdapter adapter;
    private RecyclerView.LayoutManager recyclerStopViewLayoutManager;

    //Query that selects all stops from particular bus
    private static List<stops> selectedStops;

    // Array of strings saved from what the user clicks
    String[] stopSelected;

    // Store what the user clicks into a list of strings that will be sent
    private List<String> test;

    //Variable that is placed into busSelected
    public static String busSelected;

    //Confirming that the user is done choosing stops
    public static Button confirmButton;

    //When buttons are clicked, jump to the OpenTableActivity method with String name
    LinearLayout parent;

    // Jump to select stop activity
    Intent open_select_stop;

    // User interface element for select stop
    ImageButton stop_select_button;

    // PATHWAYS TO OTHER ACTIVITIES
    Intent plan_trip_segue;
    Intent bus_updates_segue;
    Intent start_map;


    // USER INTERFACE ELEMENTS
    Toolbar toolbar;
    ImageButton navigation_button;   // Navigation menu structure
    LinearLayout navigation_menu;    // Opens / closes navigation menu
    ImageButton plan_trip_button;    // Opens trip planning activity
    ImageButton bus_alerts_button;   // Opens bus alerts Twitter feed activity.
    ImageButton map_button;          // Opens activity where users can select a stop to be showcased on a map (Google Maps)

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_select);

        setupToolBar();
        setupNavigationMenu();

        // Accessing bus selected information from ChooseTableAct
        Intent startingIntent = getIntent();

        // The users particular choice will be saved onto varible & used in switch case
        String busChoice = startingIntent.getStringExtra("busChoice");

        confirmButton = (Button) findViewById(R.id.confirmSelButton);

        // Depending on what the user clicked on, grab particular database query & set to variable
        switch (busChoice)
        {
            case "C1":
                selectedStops = HomeActivity.cattracksDatabase.daoAccess().getC1StopNames();
                busSelected = "C1";
                break;

            case "C2":
                selectedStops = HomeActivity.cattracksDatabase.daoAccess().getC2StopNames();
                busSelected = "C2";
                break;

            case "FC":
                selectedStops = HomeActivity.cattracksDatabase.daoAccess().getFCStopNames();
                busSelected = "FC";
                break;

            case "E1":
                selectedStops = HomeActivity.cattracksDatabase.daoAccess().getE1StopNames();
                busSelected = "E1";
                break;

            case "E2":
                selectedStops = HomeActivity.cattracksDatabase.daoAccess().getE2StopNames();
                busSelected = "E2";
                break;

            case "H":
                selectedStops = HomeActivity.cattracksDatabase.daoAccess().getHStopNames();
                busSelected = "H";
                break;

            case "HW":
                selectedStops = HomeActivity.cattracksDatabase.daoAccess().getHWStopNames();
                busSelected = "HW";
                break;

            case "G":
                selectedStops = HomeActivity.cattracksDatabase.daoAccess().getGStopNames();
                busSelected = "G";
                break;
        }


        //CREATING CARD VIEW

        // Adding our RecyclerView To Activity
        stopSelectRecyclerView = (RecyclerView) findViewById(R.id.selectedStops);

        // Set Recycler View Layout
        recyclerStopViewLayoutManager = new LinearLayoutManager(this);
        stopSelectRecyclerView.setLayoutManager(recyclerStopViewLayoutManager);

        // Set Up Recycler View Adapter To Showcase Stops
        adapter = new TableStopSelectAdapter(this, selectedStops);
        stopSelectRecyclerView.setAdapter(adapter);
    }

    // USER INTERFACE FUNCTIONS
    public void setupNavigationMenu(){
        // Setting up pathways to other activities
        plan_trip_segue = new Intent(this, LocationsList.class);
        bus_updates_segue = new Intent(this, BusUpdatesActivity.class);
        start_map = new Intent(this, MapStopsActivity.class);
        open_select_stop = new Intent(this, ChooseTableActivity.class);
        // Setting up user interface elements

        navigation_menu = findViewById(R.id.navigation_menu);

        navigation_button = findViewById(R.id.navigation_button);

        navigation_button.setOnClickListener((View v) -> {
            animate_navigation_menu();

        });


        // Set intent on LocationsList Activity
        plan_trip_button = findViewById(R.id.plan_trip_button);
        plan_trip_button.setOnClickListener((View v) -> {
            animate_navigation_menu();
            startActivity(plan_trip_segue);
        });


        // Set intent on BusUpdates Activity
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

        //Button has been clicked then jump to ChooseSelectActivity
        stop_select_button = findViewById(R.id.imageButton);
        stop_select_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(open_select_stop);
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
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        String userInput = newText.toLowerCase();
        List<stops> filteredList = new ArrayList<>();

        for(stops stop: selectedStops) {
            if(stop.getS_name().toLowerCase().contains(userInput)) {
                filteredList.add(stop);
            }
        }

        adapter.updateList(filteredList);
        return true;
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