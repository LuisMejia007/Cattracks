package uc.cattracks.cattracksapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerViewAccessibilityDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import uc.cattracks.cattracksapp.models.stops;
import uc.cattracks.cattracksapp.recycleview_adapters.StopsAdapter;

public class LocationsList extends AppCompatActivity {


    private RecyclerView stopLocationsRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    private List<stops> stopLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        // Adding our RecyclerView To Activity
        stopLocationsRecyclerView = (RecyclerView) findViewById(R.id.stopLocations);
        stopLocationsRecyclerView.setHasFixedSize(true);

        // Set Recycler View Layout
        recyclerViewLayoutManager = new LinearLayoutManager(this);
        stopLocationsRecyclerView.setLayoutManager(recyclerViewLayoutManager);

        // Set Up Recycler View Adapter To Showcase Stops
        stopLocations = HomeActivity.cattracksDatabase.daoAccess().getStops();
        adapter = new StopsAdapter(stopLocations);
        stopLocationsRecyclerView.setAdapter(adapter);

    }

}
