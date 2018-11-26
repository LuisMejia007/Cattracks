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
import uc.cattracks.cattracksapp.recycleview_adapters.TableStopSelectAdapter;

/*
 * STOP SELECT ACTIVITY
 * This activity creates the buttons with the users particular bus stops & prepares to communicate
 * the selected stops to NeatTableActivity to create the landscape row display
 */
public class StopSelectActivity extends AppCompatActivity //implements View.OnClickListener
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_select);

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
}