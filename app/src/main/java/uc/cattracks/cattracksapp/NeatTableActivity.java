package uc.cattracks.cattracksapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import uc.cattracks.cattracksapp.models.C1;
import uc.cattracks.cattracksapp.models.C2;
import uc.cattracks.cattracksapp.models.E1;
import uc.cattracks.cattracksapp.models.E2;
import uc.cattracks.cattracksapp.models.FC;
import uc.cattracks.cattracksapp.models.G;
import uc.cattracks.cattracksapp.models.H;
import uc.cattracks.cattracksapp.models.HW;
import uc.cattracks.cattracksapp.recycleview_adapters.BusRouteStopTimesAdapter;

//This activity displays the stop times in landscape mode
public class NeatTableActivity extends AppCompatActivity
{
    private static int SCREEN_HEIGHT;
    private static int SCREEN_WIDTH;

    // Outer list that grabs the run times from each particular
    private static List<List<String>> dynaList = new ArrayList<>();

    // Inner list that grabs the stops chosen by the user
    private static List<String> c1BusTimesA = new ArrayList<>();
    private static List<String> c1BusTimesB = new ArrayList<>();
    private static List<String> c1BusTimesC = new ArrayList<>();
    private static List<String> c2BusTimesA= new ArrayList<>();
    private static List<String> c2BusTimesB = new ArrayList<>();
    private static List<String> c2BusTimesC = new ArrayList<>();
    private static List<String> e1BusTimes = new ArrayList<>();
    private static List<String> e2BusTimesA = new ArrayList<>();
    private static List<String> e2BusTimesB = new ArrayList<>();
    private static List<String> e2BusTimesC = new ArrayList<>();
    private static List<String> hBusTimesA = new ArrayList<>();
    private static List<String> hBusTimesB = new ArrayList<>();
    private static List<String> hBusTimesC = new ArrayList<>();
    private static List<String> hwBusTimesA = new ArrayList<>();
    private static List<String> hwBusTimesB = new ArrayList<>();
    private static List<String> hwBusTimesC = new ArrayList<>();
    private static List<String> fcBusTimesA = new ArrayList<>();
    private static List<String> fcBusTimesB = new ArrayList<>();
    private static List<String> fcBusTimesC = new ArrayList<>();
    private static List<String> gBusTimesA = new ArrayList<>();
    private static List<String> gBusTimesB = new ArrayList<>();
    private static List<String> gBusTimesC = new ArrayList<>();

    private static List<String> busToAdapter = new ArrayList<>();

    // Accessing recycle view for listing of 3 stop run times
    private static RecyclerView stopTableRecyclerView;
    private static BusRouteStopTimesAdapter adapter;

    private TextView bus_name;
    private TextView stop1_name;
    private TextView stop2_name;
    private TextView stop3_name;

    String stopStr;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neat_table);

        // Make sure there is nothing saved in our dynamic list. Clean sleat everytime
        dynaList.clear();

        // Make it look cute
        getScreenDimension();

        // Get the data and send it to the grid layout (max 3 for each stop)
        stopTableRecyclerView = findViewById(R.id.routesRecyclerView);
        stop1_name = findViewById(R.id.stop1_text_view);
        stop2_name = findViewById(R.id.stop2_text_view);
        stop3_name = findViewById(R.id.stop3_text_view);
        bus_name = findViewById(R.id.bus_name_text_view);
        stopTableRecyclerView.setLayoutManager(new GridLayoutManager(this,3 ));

        // Get info & prepare for table creation
        GetMeMyIntents();

        adapter = new BusRouteStopTimesAdapter(this, e1BusTimes);
        stopTableRecyclerView.setAdapter(adapter);
    }

    public void GetMeMyIntents()
    {
        // Accessing bus selected information from ChooseTableAct
        Intent startingIntent = getIntent();

        // The users particular choice will be saved onto varible & used in switch case
        String busSelected = startingIntent.getStringExtra("busSelected");

        // The users particular choice will be saved onto varible & used in switch case
        ArrayList<String> userChosenStops = startingIntent.getStringArrayListExtra("userChosenStops");

        // Grab bus stop names
        ArrayList<String> userClickStopNames = startingIntent.getStringArrayListExtra("userClickStopNames");

        bus_name.setText(busSelected);
        stop1_name.setText(userClickStopNames.get(0));
        stop2_name.setText(userClickStopNames.get(1));
        stop3_name.setText(userClickStopNames.get(2));

        // Depending on what the user clicked on, grab particular database query method & send ArrayList
        switch (busSelected)
        {
            case "C1":
                C1BusRunTimeTabDisp(userChosenStops);
                break;

            case "C2":
                C2BusRunTimeTabDisp(userChosenStops);
                break;

            case "FC":
                FastCatBusRunTimeTabDisp(userChosenStops);
                break;

            case "E1":
                E1BusRunTimeTabDisp(userChosenStops);
                break;

            case "E2":
                E2BusRunTimeTabDisp(userChosenStops);
                break;

            case "H":
                HBusRunTimeTabDisp(userChosenStops);
                break;

            case "HW":
                HWBusRunTimeTabDisp(userChosenStops);
                break;

            case "G":
                GBusRunTimeTabDisp(userChosenStops);
                break;
        }
    }

    //Makes sure everything looks great in any screen size
    private void getScreenDimension()
    {
        //getAppCon is an interface to global info about app environment
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        assert wm != null;
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        SCREEN_WIDTH= size.x;
        SCREEN_HEIGHT = size.y;
    }

    /*
     * BusRunTimeTabDisp - the following functions are grabbing all the runtimes for particular stops
     * Sucky part is we gotta manually grab runtime data for each stop location. For this to be done
     * we use list of lists which will iterate once the run time list has been saved for a stop
     */
    public void C1BusRunTimeTabDisp(ArrayList<String>userChosenStops)
    {
        System.out.println("C1: " + userChosenStops);

        System.out.println("stop list size:" + userChosenStops.size());

        for(int i = 0; i < userChosenStops.size(); i++)
        {
            // Assigning the users chosen stops one by one (incrementing for loop)
            stopStr = userChosenStops.get(i);

            // Grabbing the information from
            List<C1> c1LocationStops = HomeActivity.cattracksDatabase.daoAccess().getC1TimesFromLocation(stopStr);

            Iterator<C1> c1LocationIterator = c1LocationStops.iterator();

            C1 temp = c1LocationIterator.next();

            if(i == 0)
            {
                c1BusTimesA.add(temp.getC1_run1());
                c1BusTimesA.add(temp.getC1_run2());
                c1BusTimesA.add(temp.getC1_run3());
                c1BusTimesA.add(temp.getC1_run4());
                c1BusTimesA.add(temp.getC1_run5());
                c1BusTimesA.add(temp.getC1_run6());
                c1BusTimesA.add(temp.getC1_run7());
                c1BusTimesA.add(temp.getC1_run8());
                c1BusTimesA.add(temp.getC1_run9());
                c1BusTimesA.add(temp.getC1_run10());
                c1BusTimesA.add(temp.getC1_run11());
                c1BusTimesA.add(temp.getC1_run12());
                c1BusTimesA.add(temp.getC1_run13());
                c1BusTimesA.add(temp.getC1_run14());
                c1BusTimesA.add(temp.getC1_run15());
                c1BusTimesA.add(temp.getC1_run16());
                c1BusTimesA.add(temp.getC1_run17());
                c1BusTimesA.add(temp.getC1_run18());
                c1BusTimesA.add(temp.getC1_run19());
                c1BusTimesA.add(temp.getC1_run20());
                c1BusTimesA.add(temp.getC1_run21());
                c1BusTimesA.add(temp.getC1_run22());
                c1BusTimesA.add(temp.getC1_run23());

                // Incrementing through our list and adding it to another list
                dynaList.add(c1BusTimesA);
            }

            else if (i == 1)
            {
                c1BusTimesB.add(temp.getC1_run1());
                c1BusTimesB.add(temp.getC1_run2());
                c1BusTimesB.add(temp.getC1_run3());
                c1BusTimesB.add(temp.getC1_run4());
                c1BusTimesB.add(temp.getC1_run5());
                c1BusTimesB.add(temp.getC1_run6());
                c1BusTimesB.add(temp.getC1_run7());
                c1BusTimesB.add(temp.getC1_run8());
                c1BusTimesB.add(temp.getC1_run9());
                c1BusTimesB.add(temp.getC1_run10());
                c1BusTimesB.add(temp.getC1_run11());
                c1BusTimesB.add(temp.getC1_run12());
                c1BusTimesB.add(temp.getC1_run13());
                c1BusTimesB.add(temp.getC1_run14());
                c1BusTimesB.add(temp.getC1_run15());
                c1BusTimesB.add(temp.getC1_run16());
                c1BusTimesB.add(temp.getC1_run17());
                c1BusTimesB.add(temp.getC1_run18());
                c1BusTimesB.add(temp.getC1_run19());
                c1BusTimesB.add(temp.getC1_run20());
                c1BusTimesB.add(temp.getC1_run21());
                c1BusTimesB.add(temp.getC1_run22());
                c1BusTimesB.add(temp.getC1_run23());

                // Incrementing through our list and adding it to another list
                dynaList.add(c1BusTimesB);
            }

            else if (i == 2)
            {
                c1BusTimesC.add(temp.getC1_run1());
                c1BusTimesC.add(temp.getC1_run2());
                c1BusTimesC.add(temp.getC1_run3());
                c1BusTimesC.add(temp.getC1_run4());
                c1BusTimesC.add(temp.getC1_run5());
                c1BusTimesC.add(temp.getC1_run6());
                c1BusTimesC.add(temp.getC1_run7());
                c1BusTimesC.add(temp.getC1_run8());
                c1BusTimesC.add(temp.getC1_run9());
                c1BusTimesC.add(temp.getC1_run10());
                c1BusTimesC.add(temp.getC1_run11());
                c1BusTimesC.add(temp.getC1_run12());
                c1BusTimesC.add(temp.getC1_run13());
                c1BusTimesC.add(temp.getC1_run14());
                c1BusTimesC.add(temp.getC1_run15());
                c1BusTimesC.add(temp.getC1_run16());
                c1BusTimesC.add(temp.getC1_run17());
                c1BusTimesC.add(temp.getC1_run18());
                c1BusTimesC.add(temp.getC1_run19());
                c1BusTimesC.add(temp.getC1_run20());
                c1BusTimesC.add(temp.getC1_run21());
                c1BusTimesC.add(temp.getC1_run22());
                c1BusTimesC.add(temp.getC1_run23());

                // Incrementing through our list and adding it to another list
                dynaList.add(c1BusTimesC);
            }
        }

        System.out.println("C1a: " + dynaList.get(0));
        System.out.println("C1b: " + dynaList.get(1));
        System.out.println("C1c: " + dynaList.get(2));
    }

    public void C2BusRunTimeTabDisp(ArrayList<String>userChosenStops)
    {
        System.out.println("C2: " + userChosenStops);

        System.out.println("stop list size:" + userChosenStops.size());

        for(int i = 0; i < userChosenStops.size(); i++)
        {
            // Assigning the users chosen stops one by one (incrementing for loop)
            stopStr = userChosenStops.get(i);

            // Grabbing the information from
            List<C2> c2LocationStops = HomeActivity.cattracksDatabase.daoAccess().getC2TimesFromLocation(stopStr);

            Iterator<C2> c2LocationIterator = c2LocationStops.iterator();

            C2 temp = c2LocationIterator.next();

            if(i == 0)
            {
                c2BusTimesA.add(temp.getC2_run1());
                c2BusTimesA.add(temp.getC2_run2());
                c2BusTimesA.add(temp.getC2_run3());
                c2BusTimesA.add(temp.getC2_run4());
                c2BusTimesA.add(temp.getC2_run5());
                c2BusTimesA.add(temp.getC2_run6());
                c2BusTimesA.add(temp.getC2_run7());
                c2BusTimesA.add(temp.getC2_run8());
                c2BusTimesA.add(temp.getC2_run9());
                c2BusTimesA.add(temp.getC2_run10());
                c2BusTimesA.add(temp.getC2_run11());
                c2BusTimesA.add(temp.getC2_run12());
                c2BusTimesA.add(temp.getC2_run13());
                c2BusTimesA.add(temp.getC2_run14());
                c2BusTimesA.add(temp.getC2_run15());
                c2BusTimesA.add(temp.getC2_run16());

                // Incrementing through our list and adding it to another list
                dynaList.add(c2BusTimesA);
            }

            else if (i == 1)
            {
                c2BusTimesB.add(temp.getC2_run1());
                c2BusTimesB.add(temp.getC2_run2());
                c2BusTimesB.add(temp.getC2_run3());
                c2BusTimesB.add(temp.getC2_run4());
                c2BusTimesB.add(temp.getC2_run5());
                c2BusTimesB.add(temp.getC2_run6());
                c2BusTimesB.add(temp.getC2_run7());
                c2BusTimesB.add(temp.getC2_run8());
                c2BusTimesB.add(temp.getC2_run9());
                c2BusTimesB.add(temp.getC2_run10());
                c2BusTimesB.add(temp.getC2_run11());
                c2BusTimesB.add(temp.getC2_run12());
                c2BusTimesB.add(temp.getC2_run13());
                c2BusTimesB.add(temp.getC2_run14());
                c2BusTimesB.add(temp.getC2_run15());
                c2BusTimesB.add(temp.getC2_run16());

                // Incrementing through our list and adding it to another list
                dynaList.add(c2BusTimesB);
            }

            else if (i == 2)
            {
                c2BusTimesC.add(temp.getC2_run1());
                c2BusTimesC.add(temp.getC2_run2());
                c2BusTimesC.add(temp.getC2_run3());
                c2BusTimesC.add(temp.getC2_run4());
                c2BusTimesC.add(temp.getC2_run5());
                c2BusTimesC.add(temp.getC2_run6());
                c2BusTimesC.add(temp.getC2_run7());
                c2BusTimesC.add(temp.getC2_run8());
                c2BusTimesC.add(temp.getC2_run9());
                c2BusTimesC.add(temp.getC2_run10());
                c2BusTimesC.add(temp.getC2_run11());
                c2BusTimesC.add(temp.getC2_run12());
                c2BusTimesC.add(temp.getC2_run13());
                c2BusTimesC.add(temp.getC2_run14());
                c2BusTimesC.add(temp.getC2_run15());
                c2BusTimesC.add(temp.getC2_run16());

                // Incrementing through our list and adding it to another list
                dynaList.add(c2BusTimesC);
            }
        }

        System.out.println("C2a: " + dynaList.get(0));
        System.out.println("C2b: " + dynaList.get(1));
        System.out.println("C2c: " + dynaList.get(2));
    }

    public void E1BusRunTimeTabDisp(ArrayList<String>userChosenStops)
    {
        System.out.println("E1: " + userChosenStops);

        System.out.println("stop list size:" + userChosenStops.size());

            // Assigning the users chosen stops one by one (incrementing for loop)
            String stopStr1 = userChosenStops.get(0);
            String stopStr2 = userChosenStops.get(1);
            String stopStr3 = userChosenStops.get(2);

            // Grabbing the information from
            List<E1> e1LocationStops1 = HomeActivity.cattracksDatabase.daoAccess().getE1TimesFromLocation(stopStr1);
            List<E1> e1LocationStops2 = HomeActivity.cattracksDatabase.daoAccess().getE1TimesFromLocation(stopStr2);
            List<E1> e1LocationStops3 = HomeActivity.cattracksDatabase.daoAccess().getE1TimesFromLocation(stopStr3);

            Iterator<E1> e1LocationIterator1 = e1LocationStops1.iterator();
            Iterator<E1> e1LocationIterator2 = e1LocationStops2.iterator();
            Iterator<E1> e1LocationIterator3 = e1LocationStops3.iterator();

            while(e1LocationIterator1.hasNext() && e1LocationIterator2.hasNext() && e1LocationIterator3.hasNext())
            {
                E1 temp1 = e1LocationIterator1.next();
                E1 temp2 = e1LocationIterator2.next();
                E1 temp3 = e1LocationIterator3.next();

                e1BusTimes.add(temp1.getE1_run1());
                e1BusTimes.add(temp2.getE1_run1());
                e1BusTimes.add(temp3.getE1_run1());
                e1BusTimes.add(temp1.getE1_run2());
                e1BusTimes.add(temp2.getE1_run2());
                e1BusTimes.add(temp3.getE1_run2());
                e1BusTimes.add(temp1.getE1_run3());
                e1BusTimes.add(temp2.getE1_run3());
                e1BusTimes.add(temp3.getE1_run3());
                e1BusTimes.add(temp1.getE1_run4());
                e1BusTimes.add(temp2.getE1_run4());
                e1BusTimes.add(temp3.getE1_run4());
                e1BusTimes.add(temp1.getE1_run5());
                e1BusTimes.add(temp2.getE1_run5());
                e1BusTimes.add(temp3.getE1_run5());
                e1BusTimes.add(temp1.getE1_run6());
                e1BusTimes.add(temp2.getE1_run6());
                e1BusTimes.add(temp3.getE1_run6());
                e1BusTimes.add(temp1.getE1_run7());
                e1BusTimes.add(temp2.getE1_run7());
                e1BusTimes.add(temp3.getE1_run7());
                e1BusTimes.add(temp1.getE1_run8());
                e1BusTimes.add(temp2.getE1_run8());
                e1BusTimes.add(temp3.getE1_run8());
                e1BusTimes.add(temp1.getE1_run9());
                e1BusTimes.add(temp2.getE1_run9());
                e1BusTimes.add(temp3.getE1_run9());
            }
                // Incrementing through our list and adding it to another list

    }

    public void E2BusRunTimeTabDisp(ArrayList<String>userChosenStops)
    {
        System.out.println("E2: " + userChosenStops);

        System.out.println("stop list size:" + userChosenStops.size());

        for(int i = 0; i < userChosenStops.size(); i++)
        {
            // Assigning the users chosen stops one by one (incrementing for loop)
            stopStr = userChosenStops.get(i);

            // Grabbing the information from
            List<E2> e2LocationStops = HomeActivity.cattracksDatabase.daoAccess().getE2TimesFromLocation(stopStr);

            Iterator<E2> e2LocationIterator = e2LocationStops.iterator();

            E2 temp = e2LocationIterator.next();

            if(i == 0)
            {
                e2BusTimesA.add(temp.getE2_run1());
                e2BusTimesA.add(temp.getE2_run2());
                e2BusTimesA.add(temp.getE2_run3());
                e2BusTimesA.add(temp.getE2_run4());
                e2BusTimesA.add(temp.getE2_run5());
                e2BusTimesA.add(temp.getE2_run6());
                e2BusTimesA.add(temp.getE2_run7());
                e2BusTimesA.add(temp.getE2_run8());
                e2BusTimesA.add(temp.getE2_run9());
                e2BusTimesA.add(temp.getE2_run10());

                // Incrementing through our list and adding it to another list
                dynaList.add(e2BusTimesA);
            }

            else if (i == 1)
            {
                e2BusTimesB.add(temp.getE2_run1());
                e2BusTimesB.add(temp.getE2_run2());
                e2BusTimesB.add(temp.getE2_run3());
                e2BusTimesB.add(temp.getE2_run4());
                e2BusTimesB.add(temp.getE2_run5());
                e2BusTimesB.add(temp.getE2_run6());
                e2BusTimesB.add(temp.getE2_run7());
                e2BusTimesB.add(temp.getE2_run8());
                e2BusTimesB.add(temp.getE2_run9());
                e2BusTimesB.add(temp.getE2_run10());

                // Incrementing through our list and adding it to another list
                dynaList.add(e2BusTimesB);
            }

            else if (i == 2)
            {
                e2BusTimesC.add(temp.getE2_run1());
                e2BusTimesC.add(temp.getE2_run2());
                e2BusTimesC.add(temp.getE2_run3());
                e2BusTimesC.add(temp.getE2_run4());
                e2BusTimesC.add(temp.getE2_run5());
                e2BusTimesC.add(temp.getE2_run6());
                e2BusTimesC.add(temp.getE2_run7());
                e2BusTimesC.add(temp.getE2_run8());
                e2BusTimesC.add(temp.getE2_run9());
                e2BusTimesC.add(temp.getE2_run10());

                // Incrementing through our list and adding it to another list
                dynaList.add(e2BusTimesC);
            }
        }

        System.out.println("E2a: " + dynaList.get(0));
        System.out.println("E2b: " + dynaList.get(1));
        System.out.println("E2c: " + dynaList.get(2));
    }

    public void HBusRunTimeTabDisp(ArrayList<String>userChosenStops)
    {

        System.out.println("H: " + userChosenStops);

        System.out.println("stop list size:" + userChosenStops.size());

        for(int i = 0; i < userChosenStops.size(); i++)
        {
            // Assigning the users chosen stops one by one (incrementing for loop)
            stopStr = userChosenStops.get(i);

            // Grabbing the information from
            List<H> hLocationStops = HomeActivity.cattracksDatabase.daoAccess().getHTimesFromLocation(stopStr);

            Iterator<H> hLocationIterator = hLocationStops.iterator();

            H temp = hLocationIterator.next();

            if(i == 0)
            {
                hBusTimesA.add(temp.getH_run1());
                hBusTimesA.add(temp.getH_run2());
                hBusTimesA.add(temp.getH_run3());
                hBusTimesA.add(temp.getH_run4());
                hBusTimesA.add(temp.getH_run5());
                hBusTimesA.add(temp.getH_run6());
                hBusTimesA.add(temp.getH_run7());
                hBusTimesA.add(temp.getH_run8());
                hBusTimesA.add(temp.getH_run9());
                hBusTimesA.add(temp.getH_run10());
                hBusTimesA.add(temp.getH_run11());
                hBusTimesA.add(temp.getH_run12());
                hBusTimesA.add(temp.getH_run13());
                hBusTimesA.add(temp.getH_run14());
                hBusTimesA.add(temp.getH_run15());
                hBusTimesA.add(temp.getH_run16());
                hBusTimesA.add(temp.getH_run17());
                hBusTimesA.add(temp.getH_run18());
                hBusTimesA.add(temp.getH_run19());
                hBusTimesA.add(temp.getH_run20());
                hBusTimesA.add(temp.getH_run21());
                hBusTimesA.add(temp.getH_run22());
                hBusTimesA.add(temp.getH_run23());
                hBusTimesA.add(temp.getH_run24());
                hBusTimesA.add(temp.getH_run25());
                hBusTimesA.add(temp.getH_run26());
                hBusTimesA.add(temp.getH_run27());
                hBusTimesA.add(temp.getH_run28());
                hBusTimesA.add(temp.getH_run29());
                hBusTimesA.add(temp.getH_run30());
                hBusTimesA.add(temp.getH_run31());
                hBusTimesA.add(temp.getH_run32());
                hBusTimesA.add(temp.getH_run33());
                hBusTimesA.add(temp.getH_run34());
                hBusTimesA.add(temp.getH_run35());
                hBusTimesA.add(temp.getH_run36());
                hBusTimesA.add(temp.getH_run37());
                hBusTimesA.add(temp.getH_run38());
                hBusTimesA.add(temp.getH_run39());
                hBusTimesA.add(temp.getH_run40());
                hBusTimesA.add(temp.getH_run41());
                hBusTimesA.add(temp.getH_run42());
                hBusTimesA.add(temp.getH_run43());
                hBusTimesA.add(temp.getH_run44());
                hBusTimesA.add(temp.getH_run45());
                hBusTimesA.add(temp.getH_run46());
                hBusTimesA.add(temp.getH_run47());
                hBusTimesA.add(temp.getH_run48());
                hBusTimesA.add(temp.getH_run49());
                hBusTimesA.add(temp.getH_run50());
                hBusTimesA.add(temp.getH_run51());
                hBusTimesA.add(temp.getH_run52());
                hBusTimesA.add(temp.getH_run53());
                hBusTimesA.add(temp.getH_run54());
                hBusTimesA.add(temp.getH_run55());
                hBusTimesA.add(temp.getH_run56());

                // Incrementing through our list and adding it to another list
                dynaList.add(hBusTimesA);
            }

            else if (i == 1)
            {
                hBusTimesB.add(temp.getH_run1());
                hBusTimesB.add(temp.getH_run2());
                hBusTimesB.add(temp.getH_run3());
                hBusTimesB.add(temp.getH_run4());
                hBusTimesB.add(temp.getH_run5());
                hBusTimesB.add(temp.getH_run6());
                hBusTimesB.add(temp.getH_run7());
                hBusTimesB.add(temp.getH_run8());
                hBusTimesB.add(temp.getH_run9());
                hBusTimesB.add(temp.getH_run10());
                hBusTimesB.add(temp.getH_run11());
                hBusTimesB.add(temp.getH_run12());
                hBusTimesB.add(temp.getH_run13());
                hBusTimesB.add(temp.getH_run14());
                hBusTimesB.add(temp.getH_run15());
                hBusTimesB.add(temp.getH_run16());
                hBusTimesB.add(temp.getH_run17());
                hBusTimesB.add(temp.getH_run18());
                hBusTimesB.add(temp.getH_run19());
                hBusTimesB.add(temp.getH_run20());
                hBusTimesB.add(temp.getH_run21());
                hBusTimesB.add(temp.getH_run22());
                hBusTimesB.add(temp.getH_run23());
                hBusTimesB.add(temp.getH_run24());
                hBusTimesB.add(temp.getH_run25());
                hBusTimesB.add(temp.getH_run26());
                hBusTimesB.add(temp.getH_run27());
                hBusTimesB.add(temp.getH_run28());
                hBusTimesB.add(temp.getH_run29());
                hBusTimesB.add(temp.getH_run30());
                hBusTimesB.add(temp.getH_run31());
                hBusTimesB.add(temp.getH_run32());
                hBusTimesB.add(temp.getH_run33());
                hBusTimesB.add(temp.getH_run34());
                hBusTimesB.add(temp.getH_run35());
                hBusTimesB.add(temp.getH_run36());
                hBusTimesB.add(temp.getH_run37());
                hBusTimesB.add(temp.getH_run38());
                hBusTimesB.add(temp.getH_run39());
                hBusTimesB.add(temp.getH_run40());
                hBusTimesB.add(temp.getH_run41());
                hBusTimesB.add(temp.getH_run42());
                hBusTimesB.add(temp.getH_run43());
                hBusTimesB.add(temp.getH_run44());
                hBusTimesB.add(temp.getH_run45());
                hBusTimesB.add(temp.getH_run46());
                hBusTimesB.add(temp.getH_run47());
                hBusTimesB.add(temp.getH_run48());
                hBusTimesB.add(temp.getH_run49());
                hBusTimesB.add(temp.getH_run50());
                hBusTimesB.add(temp.getH_run51());
                hBusTimesB.add(temp.getH_run52());
                hBusTimesB.add(temp.getH_run53());
                hBusTimesB.add(temp.getH_run54());
                hBusTimesB.add(temp.getH_run55());
                hBusTimesB.add(temp.getH_run56());

                // Incrementing through our list and adding it to another list
                dynaList.add(hBusTimesB);
            }

            else if (i == 2)
            {
                hBusTimesC.add(temp.getH_run1());
                hBusTimesC.add(temp.getH_run2());
                hBusTimesC.add(temp.getH_run3());
                hBusTimesC.add(temp.getH_run4());
                hBusTimesC.add(temp.getH_run5());
                hBusTimesC.add(temp.getH_run6());
                hBusTimesC.add(temp.getH_run7());
                hBusTimesC.add(temp.getH_run8());
                hBusTimesC.add(temp.getH_run9());
                hBusTimesC.add(temp.getH_run10());
                hBusTimesC.add(temp.getH_run11());
                hBusTimesC.add(temp.getH_run12());
                hBusTimesC.add(temp.getH_run13());
                hBusTimesC.add(temp.getH_run14());
                hBusTimesC.add(temp.getH_run15());
                hBusTimesC.add(temp.getH_run16());
                hBusTimesC.add(temp.getH_run17());
                hBusTimesC.add(temp.getH_run18());
                hBusTimesC.add(temp.getH_run19());
                hBusTimesC.add(temp.getH_run20());
                hBusTimesC.add(temp.getH_run21());
                hBusTimesC.add(temp.getH_run22());
                hBusTimesC.add(temp.getH_run23());
                hBusTimesC.add(temp.getH_run24());
                hBusTimesC.add(temp.getH_run25());
                hBusTimesC.add(temp.getH_run26());
                hBusTimesC.add(temp.getH_run27());
                hBusTimesC.add(temp.getH_run28());
                hBusTimesC.add(temp.getH_run29());
                hBusTimesC.add(temp.getH_run30());
                hBusTimesC.add(temp.getH_run31());
                hBusTimesC.add(temp.getH_run32());
                hBusTimesC.add(temp.getH_run33());
                hBusTimesC.add(temp.getH_run34());
                hBusTimesC.add(temp.getH_run35());
                hBusTimesC.add(temp.getH_run36());
                hBusTimesC.add(temp.getH_run37());
                hBusTimesC.add(temp.getH_run38());
                hBusTimesC.add(temp.getH_run39());
                hBusTimesC.add(temp.getH_run40());
                hBusTimesC.add(temp.getH_run41());
                hBusTimesC.add(temp.getH_run42());
                hBusTimesC.add(temp.getH_run43());
                hBusTimesC.add(temp.getH_run44());
                hBusTimesC.add(temp.getH_run45());
                hBusTimesC.add(temp.getH_run46());
                hBusTimesC.add(temp.getH_run47());
                hBusTimesC.add(temp.getH_run48());
                hBusTimesC.add(temp.getH_run49());
                hBusTimesC.add(temp.getH_run50());
                hBusTimesC.add(temp.getH_run51());
                hBusTimesC.add(temp.getH_run52());
                hBusTimesC.add(temp.getH_run53());
                hBusTimesC.add(temp.getH_run54());
                hBusTimesC.add(temp.getH_run55());
                hBusTimesC.add(temp.getH_run56());

                // Incrementing through our list and adding it to another list
                dynaList.add(hBusTimesC);
            }
        }
        System.out.println("Ha: " + dynaList.get(0));
        System.out.println("Hb: " + dynaList.get(1));
        System.out.println("HC: " + dynaList.get(2));
    }

    public void HWBusRunTimeTabDisp(ArrayList<String>userChosenStops)
    {
        System.out.println("HW: " + userChosenStops);

        System.out.println("stop list size:" + userChosenStops.size());

        for(int i = 0; i < userChosenStops.size(); i++)
        {
            // Assigning the users chosen stops one by one (incrementing for loop)
            stopStr = userChosenStops.get(i);

            // Grabbing the information from
            List<HW> hwLocationStops = HomeActivity.cattracksDatabase.daoAccess().getHWTimesFromLocation(stopStr);

            Iterator<HW> hwLocationIterator = hwLocationStops.iterator();

            HW temp = hwLocationIterator.next();

            if(i == 0)
            {
                hwBusTimesA.add(temp.getHw_run1());
                hwBusTimesA.add(temp.getHw_run2());
                hwBusTimesA.add(temp.getHw_run3());
                hwBusTimesA.add(temp.getHw_run4());
                hwBusTimesA.add(temp.getHw_run5());
                hwBusTimesA.add(temp.getHw_run6());
                hwBusTimesA.add(temp.getHw_run7());
                hwBusTimesA.add(temp.getHw_run8());
                hwBusTimesA.add(temp.getHw_run9());
                hwBusTimesA.add(temp.getHw_run10());
                hwBusTimesA.add(temp.getHw_run11());
                hwBusTimesA.add(temp.getHw_run12());
                hwBusTimesA.add(temp.getHw_run13());
                hwBusTimesA.add(temp.getHw_run14());
                hwBusTimesA.add(temp.getHw_run15());
                hwBusTimesA.add(temp.getHw_run16());
                hwBusTimesA.add(temp.getHw_run17());
                hwBusTimesA.add(temp.getHw_run18());
                hwBusTimesA.add(temp.getHw_run19());
                hwBusTimesA.add(temp.getHw_run20());

                // Incrementing through our list and adding it to another list
                dynaList.add(hwBusTimesA);
            }

            else if (i == 1)
            {
                hwBusTimesB.add(temp.getHw_run1());
                hwBusTimesB.add(temp.getHw_run2());
                hwBusTimesB.add(temp.getHw_run3());
                hwBusTimesB.add(temp.getHw_run4());
                hwBusTimesB.add(temp.getHw_run5());
                hwBusTimesB.add(temp.getHw_run6());
                hwBusTimesB.add(temp.getHw_run7());
                hwBusTimesB.add(temp.getHw_run8());
                hwBusTimesB.add(temp.getHw_run9());
                hwBusTimesB.add(temp.getHw_run10());
                hwBusTimesB.add(temp.getHw_run11());
                hwBusTimesB.add(temp.getHw_run12());
                hwBusTimesB.add(temp.getHw_run13());
                hwBusTimesB.add(temp.getHw_run14());
                hwBusTimesB.add(temp.getHw_run15());
                hwBusTimesB.add(temp.getHw_run16());
                hwBusTimesB.add(temp.getHw_run17());
                hwBusTimesB.add(temp.getHw_run18());
                hwBusTimesB.add(temp.getHw_run19());
                hwBusTimesB.add(temp.getHw_run20());

                // Incrementing through our list and adding it to another list
                dynaList.add(hwBusTimesB);
            }

            else if (i == 2)
            {
                hwBusTimesC.add(temp.getHw_run1());
                hwBusTimesC.add(temp.getHw_run2());
                hwBusTimesC.add(temp.getHw_run3());
                hwBusTimesC.add(temp.getHw_run4());
                hwBusTimesC.add(temp.getHw_run5());
                hwBusTimesC.add(temp.getHw_run6());
                hwBusTimesC.add(temp.getHw_run7());
                hwBusTimesC.add(temp.getHw_run8());
                hwBusTimesC.add(temp.getHw_run9());
                hwBusTimesC.add(temp.getHw_run10());
                hwBusTimesC.add(temp.getHw_run11());
                hwBusTimesC.add(temp.getHw_run12());
                hwBusTimesC.add(temp.getHw_run13());
                hwBusTimesC.add(temp.getHw_run14());
                hwBusTimesC.add(temp.getHw_run15());
                hwBusTimesC.add(temp.getHw_run16());
                hwBusTimesC.add(temp.getHw_run17());
                hwBusTimesC.add(temp.getHw_run18());
                hwBusTimesC.add(temp.getHw_run19());
                hwBusTimesC.add(temp.getHw_run20());

                // Incrementing through our list and adding it to another list
                dynaList.add(hwBusTimesC);
            }
        }

        System.out.println("HWa: " + dynaList.get(0));
        System.out.println("HWb: " + dynaList.get(1));
        System.out.println("HWc: " + dynaList.get(2));
    }

    public void GBusRunTimeTabDisp(ArrayList<String>userChosenStops)
    {
        System.out.println("G: " + userChosenStops);

        System.out.println("stop list size:" + userChosenStops.size());

        for(int i = 0; i < userChosenStops.size(); i++)
        {
            // Assigning the users chosen stops one by one (incrementing for loop)
            stopStr = userChosenStops.get(i);

            // Grabbing the information from
            List<G> gLocationStops = HomeActivity.cattracksDatabase.daoAccess().getGTimesFromLocation(stopStr);

            Iterator<G> gLocationIterator = gLocationStops.iterator();

            G temp = gLocationIterator.next();

            if(i == 0)
            {
                gBusTimesA.add(temp.getG_run1());
                gBusTimesA.add(temp.getG_run2());
                gBusTimesA.add(temp.getG_run3());
                gBusTimesA.add(temp.getG_run4());
                gBusTimesA.add(temp.getG_run5());
                gBusTimesA.add(temp.getG_run6());
                gBusTimesA.add(temp.getG_run7());
                gBusTimesA.add(temp.getG_run8());
                gBusTimesA.add(temp.getG_run9());
                gBusTimesA.add(temp.getG_run10());
                gBusTimesA.add(temp.getG_run11());
                gBusTimesA.add(temp.getG_run12());
                gBusTimesA.add(temp.getG_run13());
                gBusTimesA.add(temp.getG_run14());

                // Incrementing through our list and adding it to another list
                dynaList.add(gBusTimesA);
            }

            else if (i == 1)
            {
                gBusTimesB.add(temp.getG_run1());
                gBusTimesB.add(temp.getG_run2());
                gBusTimesB.add(temp.getG_run3());
                gBusTimesB.add(temp.getG_run4());
                gBusTimesB.add(temp.getG_run5());
                gBusTimesB.add(temp.getG_run6());
                gBusTimesB.add(temp.getG_run7());
                gBusTimesB.add(temp.getG_run8());
                gBusTimesB.add(temp.getG_run9());
                gBusTimesB.add(temp.getG_run10());
                gBusTimesB.add(temp.getG_run11());
                gBusTimesB.add(temp.getG_run12());
                gBusTimesB.add(temp.getG_run13());
                gBusTimesB.add(temp.getG_run14());

                // Incrementing through our list and adding it to another list
                dynaList.add(gBusTimesB);
            }

            else if (i == 2)
            {
                gBusTimesC.add(temp.getG_run1());
                gBusTimesC.add(temp.getG_run2());
                gBusTimesC.add(temp.getG_run3());
                gBusTimesC.add(temp.getG_run4());
                gBusTimesC.add(temp.getG_run5());
                gBusTimesC.add(temp.getG_run6());
                gBusTimesC.add(temp.getG_run7());
                gBusTimesC.add(temp.getG_run8());
                gBusTimesC.add(temp.getG_run9());
                gBusTimesC.add(temp.getG_run10());
                gBusTimesC.add(temp.getG_run11());
                gBusTimesC.add(temp.getG_run12());
                gBusTimesC.add(temp.getG_run13());
                gBusTimesC.add(temp.getG_run14());

                // Incrementing through our list and adding it to another list
                dynaList.add(gBusTimesC);
            }
        }

        System.out.println("Ga: " + dynaList.get(0));
        System.out.println("Gb: " + dynaList.get(1));
        System.out.println("Gc: " + dynaList.get(2));
    }



    public void FastCatBusRunTimeTabDisp(ArrayList<String>userChosenStops)
    {
        System.out.println("FC: " + userChosenStops);

        System.out.println("stop list size:" + userChosenStops.size());

        for(int i = 0; i < userChosenStops.size(); i++)
        {
            // Assigning the users chosen stops one by one (incrementing for loop)
            stopStr = userChosenStops.get(i);

            // Grabbing the information from
            List<FC> fcLocationStops = HomeActivity.cattracksDatabase.daoAccess().getFCTimesFromLocation(stopStr);

            Iterator<FC> fcLocationIterator = fcLocationStops.iterator();

            FC temp = fcLocationIterator.next();

            if(i == 0)
            {
                fcBusTimesA.add(temp.getFc_run1());
                fcBusTimesA.add(temp.getFc_run2());
                fcBusTimesA.add(temp.getFc_run3());
                fcBusTimesA.add(temp.getFc_run4());
                fcBusTimesA.add(temp.getFc_run5());
                fcBusTimesA.add(temp.getFc_run6());
                fcBusTimesA.add(temp.getFc_run7());
                fcBusTimesA.add(temp.getFc_run8());
                fcBusTimesA.add(temp.getFc_run9());
                fcBusTimesA.add(temp.getFc_run10());
                fcBusTimesA.add(temp.getFc_run11());
                fcBusTimesA.add(temp.getFc_run12());
                fcBusTimesA.add(temp.getFc_run13());
                fcBusTimesA.add(temp.getFc_run14());
                fcBusTimesA.add(temp.getFc_run15());

                // Incrementing through our list and adding it to another list
                dynaList.add(fcBusTimesA);
            }

            else if (i == 1)
            {
                fcBusTimesB.add(temp.getFc_run1());
                fcBusTimesB.add(temp.getFc_run2());
                fcBusTimesB.add(temp.getFc_run3());
                fcBusTimesB.add(temp.getFc_run4());
                fcBusTimesB.add(temp.getFc_run5());
                fcBusTimesB.add(temp.getFc_run6());
                fcBusTimesB.add(temp.getFc_run7());
                fcBusTimesB.add(temp.getFc_run8());
                fcBusTimesB.add(temp.getFc_run9());
                fcBusTimesB.add(temp.getFc_run10());
                fcBusTimesB.add(temp.getFc_run11());
                fcBusTimesB.add(temp.getFc_run12());
                fcBusTimesB.add(temp.getFc_run13());
                fcBusTimesB.add(temp.getFc_run14());
                fcBusTimesB.add(temp.getFc_run15());

                // Incrementing through our list and adding it to another list
                dynaList.add(fcBusTimesB);
            }

            else if (i == 2)
            {
                fcBusTimesC.add(temp.getFc_run1());
                fcBusTimesC.add(temp.getFc_run2());
                fcBusTimesC.add(temp.getFc_run3());
                fcBusTimesC.add(temp.getFc_run4());
                fcBusTimesC.add(temp.getFc_run5());
                fcBusTimesC.add(temp.getFc_run6());
                fcBusTimesC.add(temp.getFc_run7());
                fcBusTimesC.add(temp.getFc_run8());
                fcBusTimesC.add(temp.getFc_run9());
                fcBusTimesC.add(temp.getFc_run10());
                fcBusTimesC.add(temp.getFc_run11());
                fcBusTimesC.add(temp.getFc_run12());
                fcBusTimesC.add(temp.getFc_run13());
                fcBusTimesC.add(temp.getFc_run14());
                fcBusTimesC.add(temp.getFc_run15());

                // Incrementing through our list and adding it to another list
                dynaList.add(fcBusTimesC);
            }
        }

        System.out.println("FCa: " + dynaList.get(0));
        System.out.println("FCb: " + dynaList.get(1));
        System.out.println("FCc: " + dynaList.get(2));
    }
}
