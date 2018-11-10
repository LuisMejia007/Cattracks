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
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import uc.cattracks.cattracksapp.models.stops;
import uc.cattracks.cattracksapp.recycleview_adapters.DestinationsAdapter;
import uc.cattracks.cattracksapp.recycleview_adapters.StopsAdapter;

public class DestinationsListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, View.OnClickListener {

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


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        stopDestinations = new ArrayList<>();

        // Connect layout button to our button member confirmDestinationSelectionButton
        confirmDestinationSelectionButton = (Button) findViewById(R.id.confirmDestinationSelectionButton);
        TextView textView = (TextView) findViewById(R.id.stop_destinations);



        // Getting intent's data that was passed on from previous activity
         locationSelectedByUser = getIntent().getStringExtra("Stop Selected: ");
        // Intent's data will be used for database query
        stopDestinations = HomeActivity.cattracksDatabase.daoAccess().getFilteredDestinations(locationSelectedByUser);

        // Setting up Recycler View
        destinationsRecyclerView = (RecyclerView) findViewById(R.id.destinationLocations);
        destinationsRecyclerView.setHasFixedSize(true);

        // Set Recycler View Layout
        recyclerViewLayoutManager = new LinearLayoutManager(this);
        destinationsRecyclerView.setLayoutManager(recyclerViewLayoutManager);

        // Setting up adapter
        adapter = new DestinationsAdapter(this, stopDestinations);
        destinationsRecyclerView.setAdapter(adapter);

    }


    // Setting Up Search Filter
    // Helpful Tutorial on doing a search filter: https://www.youtube.com/watch?v=qzbvDJqXeJs&frags=pl%2Cwn
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Setting up top tool bar to contain the search filter
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        // Setting up the search filters functionality
        MenuItem menuItem = menu.findItem(R.id.stopLocationsSearchFilter);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
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
}
