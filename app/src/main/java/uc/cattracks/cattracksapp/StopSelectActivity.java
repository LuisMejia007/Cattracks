package uc.cattracks.cattracksapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import uc.cattracks.cattracksapp.models.stops;

/*
 * STOP SELECT ACTIVITY
 * This activity creates the buttons with the users particular bus stops & prepares to communicate
 * the selected stops to NeatTableActivity to create the landscape row display
 */
public class StopSelectActivity extends AppCompatActivity implements View.OnClickListener
{
    //Query that selects all stops from particular bus
    private static List<stops> selectedStops;

    //When buttons are clicked, jump to the OpenTableActivity method with String name
    LinearLayout parent;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_select);

        //Accessing bus selected information from ChooseTableAct.
        //Not using yet, still trying
        Intent startingIntent = getIntent();
        String busChoice = startingIntent.getStringExtra("busChoice");


        //Figuring out the size of the stops to be used to create particular amount of buttons
        int btnDisplay = selectedStops.size();

        parent = (LinearLayout)findViewById(R.id.buttonParent);

        //Iterate through the length of the amt of stops & create buttons
        //Creating the
        for(int i = 0; i < btnDisplay; i++)
        {
            btn = new Button(StopSelectActivity.this);
            btn.setId(i + 1);
            //btn.setText(stopNa[i]);
            btn.setText(selectedStops.get(i).toString());
            btn.setTag(i);
            parent.addView(btn);
            btn.setOnClickListener(StopSelectActivity.this);
        }


        selectedStops = new ArrayList<>();

        System.out.println("HERE!");
        //ERROR HERE - fack sake -
        selectedStops = HomeActivity.cattracksDatabase.daoAccess().getC1StopNames(); //Query to get all C1 stops

        String[] selecArr = new String[selectedStops.size()];

        selecArr = selectedStops.toArray(selecArr);

        //Trying to test for input
        for(String s : selecArr)
        {
            Log.d(s, "something");
        }

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


/*
            switch (busChoice)
            {
                case "C1":
                    List <C1> selectedStops = HomeActivity.cattracksDatabase.daoAccess().getC1StopNames();
                    break;

                case "C2":
                    List <C2> selectedStops = HomeActivity.cattracksDatabase.daoAccess().getC2StopNames();
                    break;

                case "FC":
                    List <FC> selectedStops = HomeActivity.cattracksDatabase.daoAccess().getFCStopNames();
                    break;

                case "H":
                    List <H> selectedStops = HomeActivity.cattracksDatabase.daoAccess().getHStopNames();
                    break;

                case "HW":
                    List <HW> selectedStops = HomeActivity.cattracksDatabase.daoAccess().getHWStopNames();
                    break;

                case "G":
                    List <G> selectedStops = HomeActivity.cattracksDatabase.daoAccess().getGStopNames();
                    break;
            }
*/