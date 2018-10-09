package uc.cattracks.cattracksapp.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textservice.TextInfo;
import android.widget.TextView;

import java.util.List;

import uc.cattracks.cattracksapp.HomeActivity;
import uc.cattracks.cattracksapp.R;
import uc.cattracks.cattracksapp.database.CattracksDatabase;
import uc.cattracks.cattracksapp.models.C1;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetUserBus extends Fragment {

    private TextView textInfo;

    public GetUserBus() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_get_user_bus, container, false);

        textInfo = view.findViewById(R.id.get_bus_info);



//       List<C1> buses = HomeActivity.cattracksDatabase.daoAccess().getStops("GRA", "SAAC");
        List<C1> buses = HomeActivity.cattracksDatabase.daoAccess().getC1();

        String busInfo = "";

        if(buses.isEmpty()) {
            System.out.println("Buses is empty");
        }

        for (C1 bus: buses) {

            int id = bus.getC1_id();
            String stop1 = bus.getC1_run1().toString();
            String stop2 = bus.getC1_run2().toString();
            String stop3 = bus.getC1_run3().toString();
            String stop4 = bus.getC1_run4().toString();
            String stop5 = bus.getC1_run5().toString();
            String stop6 = bus.getC1_run6().toString();
            String stop7 = bus.getC1_run7().toString();
            String stop8 = bus.getC1_run8().toString();

            busInfo = busInfo + "\n\n" + "Bus id: " + id + "\n Stop1: " + stop1 + "\nStop2: " + stop2;
        }

        busInfo += "Not Working";
        textInfo.setText(busInfo);
        return view;
    }

}
