package uc.cattracks.cattracksapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import uc.cattracks.cattracksapp.HomeActivity;
import uc.cattracks.cattracksapp.R;
import uc.cattracks.cattracksapp.models.stops;
import uc.cattracks.cattracksapp.recycleview_adapters.StopsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetFromStopToSchool extends Fragment {

    private TextView textInfo;
    RecyclerView stopToSchoolRecyclerView;
    StopsAdapter stopsAdapter;
    List<stops> stops;

    public GetFromStopToSchool() {
        // Required empty public constructor
    }

    /* Home Activity has the cattracksDatabase object, which is an instance of our Cattracks Database class.
     * Cattracks Database class is found under the database folder.
     * You can see from the cattracksDatabase object we're accessing daoAccess().
     * CattracksDatabase is an abstract class, which only serves as an interface for the daoAccess class.
     * The daoAccess class (under dao_interface) is what contains the SQL query functions for our app.
     * It also serves as an interface for the Room framework (which holds the connection between our DB and Android).
     */



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Quick fix in adding a RecyclerView into a Fragment: https://stackoverflow.com/questions/26621060/display-a-recyclerview-in-fragment
        // Apparently Views have not already been created by the time you call findViewById.
        // So we had to set our rootView first then add our Recycler view later.
        View rootView = inflater.inflate(R.layout.stop_locations_recyclerview, container, false);
        stopToSchoolRecyclerView = (RecyclerView) rootView.findViewById(R.id.stopLocations);

        System.out.println("Context: " + getContext());
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        stopToSchoolRecyclerView.setLayoutManager(layoutManager);

        // Get All Distinct Bus Stops that take you to school
        stops = HomeActivity.cattracksDatabase.daoAccess().getStops();

        stops stop = new stops();
        stop.setS_name("Bellevue");
        stops stop1 = new stops();
        stop1.setS_name("Target");
        stops stop2 = new stops();
        stop2.setS_name("Granville");
        stops stop3 = new stops();
        stop3.setS_name("Moraga");
        stops stop4 = new stops();
        stop4.setS_name("Castle");
        stops stop5 = new stops();
        stop5.setS_name("Detroit");
        stops.add(stop);
        stops.add(stop1);
        stops.add(stop2);
        stops.add(stop3);
        stops.add(stop4);
        stops.add(stop5);


        stopsAdapter = new StopsAdapter(stops);

        System.out.println("Context: " + getContext());
        System.out.println("Adapter: " + stopsAdapter);
        stopToSchoolRecyclerView.setAdapter(stopsAdapter);

        return stopToSchoolRecyclerView;
    }

}



/* This was a test-version of the stops we wanted to display. If you would like to see this version just paste
*  this inside the onCreateView method....
*
*
*
*
*
*         // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_get_user_bus, container, false);

        // - Setting up a text view to showcase bus info
        textInfo = view.findViewById(R.id.get_bus_info);



         Home Activity has the cattracksDatabase object, which is an instance of our Cattracks Database class.
          Cattracks Database class is found under the database folder.
          You can see from the cattracksDatabase object we're accessing daoAccess().
          CattracksDatabase is an abstract class, which only serves as an interface for the daoAccess class.
          The daoAccess class (under dao_interface) is what contains the SQL query functions for our app.
          It also serves as an interface for the Room framework (which holds the connection between our DB and Android).



    // - As a test, we're only getting buses that go to SAAC from GRA. Only C1 goes there.
    List<C1> buses = HomeActivity.cattracksDatabase.daoAccess().getStops("GRA", "SAAC");

    // - Uncomment this if you want to see all C1 departure times. This was used as a test.
    //List<C1> buses = HomeActivity.cattracksDatabase.daoAccess().getC1();

    String busInfo = "";

        if(buses.isEmpty()) {
                System.out.println("Buses is empty");
                }

                for (C1 bus: buses) {

                int id = bus.getC1_id();
                String abbr = bus.getC1_abb();
                String stop1 = bus.getC1_run1().toString();
                String stop2 = bus.getC1_run2().toString();
                String stop3 = bus.getC1_run3().toString();
                String stop4 = bus.getC1_run4().toString();
                String stop5 = bus.getC1_run5().toString();
                String stop6 = bus.getC1_run6().toString();
                String stop7 = bus.getC1_run7().toString();
                String stop8 = bus.getC1_run8().toString();

                busInfo = busInfo + "\n\n" + "Bus id: " + id + "\n Abbr: " + abbr + "\n Stop1: " + stop1 + "\nStop2: " + stop2
                + "\n Stop 3: " + stop3 + "\n Stop 4: " + stop4 + "\n Stop 5: " + stop5 + "\n Stop 6: " + stop6
                + "\n Stop 7: " + stop7 + "\n Stop 8: " + stop8;
                }

                // - "Not working" will display by itself if 'buses' did not retrieve anything from our query.
                busInfo += "Not Working";

                // - Set All bus information into our text view to showcase all info retrieved from query.
                textInfo.setText(busInfo);
*
*
*
* */