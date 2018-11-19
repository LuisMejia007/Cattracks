package uc.cattracks.cattracksapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import uc.cattracks.cattracksapp.models.stops;
import android.support.v7.widget.RecyclerView;
import uc.cattracks.cattracksapp.recycleview_adapters.StopsAdapter;

/*
 * STOP SELECT ACTIVITY
 * This activity creates the buttons with the users particular bus stops & prepares to communicate
 * the selected stops to NeatTableActivity to create the landscape row display
 */
public class StopSelectActivity extends AppCompatActivity implements View.OnClickListener
{
    //Setting up CardView with use of Recycle View
    private RecyclerView stopSelectRecyclerView;
    private StopsAdapter adapter;
    private RecyclerView.LayoutManager recyclerStopViewLayoutManager;

    //Query that selects all stops from particular bus
    private static List<stops> selectedStops;

    //Confirming that the user is done choosing stops
    public static Button confirmStopButton;

    //When buttons are clicked, jump to the OpenTableActivity method with String name
    LinearLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_select);

        // Accessing bus selected information from ChooseTableAct
        Intent startingIntent = getIntent();

        // The users particular choice will be saved onto varible & used in switch case
        String busChoice = startingIntent.getStringExtra("busChoice");

        confirmStopButton = (Button) findViewById(R.id.confirmLocationSelectionButton);

        // Depending on what the user clicked on, grab particular database query & set to variable
        switch (busChoice)
        {
            case "C1":
                selectedStops = HomeActivity.cattracksDatabase.daoAccess().getC1StopNames();
                break;

            case "C2":
                selectedStops = HomeActivity.cattracksDatabase.daoAccess().getC2StopNames();
                break;

            case "FC":
                selectedStops = HomeActivity.cattracksDatabase.daoAccess().getFCStopNames();
                break;

            case "E1":
                selectedStops = HomeActivity.cattracksDatabase.daoAccess().getE1StopNames();
                break;

            case "E2":
                selectedStops = HomeActivity.cattracksDatabase.daoAccess().getE2StopNames();
                break;

            case "H":
                selectedStops = HomeActivity.cattracksDatabase.daoAccess().getHStopNames();
                break;

            case "HW":
                selectedStops = HomeActivity.cattracksDatabase.daoAccess().getHWStopNames();
                break;

            case "G":
                selectedStops = HomeActivity.cattracksDatabase.daoAccess().getGStopNames();
                break;
        }

        // Adding our RecyclerView To Activity
        stopSelectRecyclerView = (RecyclerView) findViewById(R.id.selectedStops);

        // Set Recycler View Layout
        recyclerStopViewLayoutManager = new LinearLayoutManager(this);
        stopSelectRecyclerView.setLayoutManager(recyclerStopViewLayoutManager);

        // Set Up Recycler View Adapter To Showcase Stops
        //selectedStops = HomeActivity.cattracksDatabase.daoAccess().getC1StopNames(); //Query For All Stops
        adapter = new StopsAdapter(this, selectedStops);
        stopSelectRecyclerView.setAdapter(adapter);
    }

    //When user clicks on particular stop(s), jump to next activity with info
    //Currently was used to check if on click listener works
    @Override
    public void onClick(View v)
    {
        String str = v.getTag().toString();
        if(str.equals("0"))
        {
            Toast.makeText(getApplicationContext(), "Click Button1", Toast.LENGTH_SHORT).show();
        }
        else if(str.equals("1"))
        {
            Toast.makeText(getApplicationContext(), "Click Button2", Toast.LENGTH_SHORT).show();
        }
    }
}