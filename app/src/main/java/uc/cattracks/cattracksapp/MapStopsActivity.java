package uc.cattracks.cattracksapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import uc.cattracks.cattracksapp.constants.MapStops;
import uc.cattracks.cattracksapp.constants.mapStopsArraySource;
import uc.cattracks.cattracksapp.models.stops;
import uc.cattracks.cattracksapp.recycleview_adapters.MapStopsAdapter;

public class MapStopsActivity extends AppCompatActivity {

    //private List<MapStops> stopLocations;
    private MapStops[] stopLocations;
    private RecyclerView mapStopsRecyclerView;
    private RecyclerView.LayoutManager mapStopsRecyclerViewLayoutManager;
    private MapStopsAdapter mapsAdapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_stops_activity_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //stopLocations = HomeActivity.cattracksDatabase.daoAccess().getStops();
        stopLocations = mapStopsArraySource.mapStops;
        mapStopsRecyclerView = (RecyclerView) findViewById(R.id.map_stops_recyclerview);
        mapStopsRecyclerViewLayoutManager = new LinearLayoutManager(this);
        mapStopsRecyclerView.setLayoutManager(mapStopsRecyclerViewLayoutManager);

        mapsAdapter = new MapStopsAdapter(this, stopLocations);
        mapStopsRecyclerView.setAdapter(mapsAdapter);

    }

//    // Setting Up Search Filter
//    // Helpful Tutorial on doing a search filter: https://www.youtube.com/watch?v=qzbvDJqXeJs&frags=pl%2Cwn
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        // Setting up top tool bar to contain the search filter
//        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
//
//        // Setting up the search filters functionality
//        MenuItem menuItem = menu.findItem(R.id.stopLocationsSearchFilter);
//        SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.setOnQueryTextListener(this);
//        return true;
//    }
//
//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//
//        String userInput = newText.toLowerCase();
//        List<stops> filteredList = new ArrayList<>();
//
//        for(stops stop: stopLocations) {
//            if(stop.getS_name().toLowerCase().contains(userInput)) {
//                filteredList.add(stop);
//            }
//        }
//
//        mapsAdapter.updateList(filteredList);
//        return true;
//    }
}
