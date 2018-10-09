package uc.cattracks.cattracksapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import uc.cattracks.cattracksapp.R;
import uc.cattracks.cattracksapp.HomeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {


    private Button goToSchoolButton;

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

        // Register this button's onClickListener to this class's onClick method
        goToSchoolButton.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {

        HomeActivity.fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, new GetUserBus())
                .addToBackStack(null).commit();
    }
}
