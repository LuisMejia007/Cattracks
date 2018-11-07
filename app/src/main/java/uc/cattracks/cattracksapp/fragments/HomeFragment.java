package uc.cattracks.cattracksapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import uc.cattracks.cattracksapp.R;
import uc.cattracks.cattracksapp.HomeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {


    private Button goToSchoolButton;
    private Spinner locationSpinner;
    private Spinner destinationSpinner;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);



        // Register goToSchoolButton with respective button id found in fragment_home.xml
        goToSchoolButton = view.findViewById(R.id.go_to_school_btn);
        locationSpinner = view.findViewById(R.id.locationSpinner);
        destinationSpinner = view.findViewById(R.id.destinationSpinner);


        // Options must be placed inside an ArrayAdapter to be used by spinners
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.
                                            createFromResource(getContext(), R.array.locationsDestinations,
                                                    android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // Registering the spinners to the adapter
        locationSpinner.setAdapter(adapter);
        destinationSpinner.setAdapter(adapter);

        // Register this button's onClickListener to this class's onClick method
        goToSchoolButton.setOnClickListener(this);


        // Spinner listening events
        locationSpinner.setOnItemSelectedListener(this);
        destinationSpinner.setOnItemSelectedListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {

        // Once button is clicked, we place this fragment to the backstack (to go back too) and add GetFromStopToSchool fragment
        HomeActivity.fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, new GetFromStopToSchool())
                .addToBackStack(null).commit();
    }


    // Spinner Selected Listener Methods -- Cannot delete these since they're abstract methods
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

        System.out.println("Clicked: " + parent.getItemAtPosition(position));

        if(parent.getId() == R.id.locationSpinner) {
            System.out.println("Location pressed: " + parent.getItemAtPosition(position));
        } else if (parent.getId() == R.id.destinationSpinner) {
            System.out.println("Destination pressed: " + parent.getItemAtPosition(position));
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
