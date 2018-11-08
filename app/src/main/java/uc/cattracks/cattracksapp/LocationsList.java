package uc.cattracks.cattracksapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerViewAccessibilityDelegate;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import uc.cattracks.cattracksapp.models.stops;
import uc.cattracks.cattracksapp.recycleview_adapters.StopsAdapter;



public class LocationsList extends AppCompatActivity implements SearchView.OnQueryTextListener {


    private RecyclerView stopLocationsRecyclerView;
    private StopsAdapter adapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    private List<stops> stopLocations;
    public static Button confirmationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        // Reference Confirmation Button
        confirmationButton = (Button) findViewById(R.id.confirmLocationSelectionButton);

        // Adding our RecyclerView To Activity
        stopLocationsRecyclerView = (RecyclerView) findViewById(R.id.stopLocations);
        stopLocationsRecyclerView.setHasFixedSize(true);

        // Set Recycler View Layout
        recyclerViewLayoutManager = new LinearLayoutManager(this);
        stopLocationsRecyclerView.setLayoutManager(recyclerViewLayoutManager);

        // Set Up Recycler View Adapter To Showcase Stops
        stopLocations = HomeActivity.cattracksDatabase.daoAccess().getStops();
        adapter = new StopsAdapter(this, stopLocations);
        stopLocationsRecyclerView.setAdapter(adapter);

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

        for(stops stop: stopLocations) {
            if(stop.getS_name().toLowerCase().contains(userInput)) {
                filteredList.add(stop);
            }
        }

        adapter.updateList(filteredList);
        return true;
    }

}
