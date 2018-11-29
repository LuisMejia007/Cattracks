package uc.cattracks.cattracksapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private static List<String> c1BusTimes = new ArrayList<>();
    private static List<String> c2BusTimes = new ArrayList<>();
    private static List<String> e1BusTimes = new ArrayList<>();
    private static List<String> e2BusTimes = new ArrayList<>();
    private static List<String> hBusTimes = new ArrayList<>();
    private static List<String> hwBusTimes = new ArrayList<>();
    private static List<String> fcBusTimes = new ArrayList<>();
    private static List<String> gBusTimes = new ArrayList<>();

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
                adapter = new BusRouteStopTimesAdapter(this, c1BusTimes);
                stopTableRecyclerView.setAdapter(adapter);
                C1BusRunTimeTabDisp(userChosenStops);
                break;

            case "C2":
                adapter = new BusRouteStopTimesAdapter(this, c2BusTimes);
                stopTableRecyclerView.setAdapter(adapter);
                C2BusRunTimeTabDisp(userChosenStops);
                break;

            case "FC":
                adapter = new BusRouteStopTimesAdapter(this, fcBusTimes);
                stopTableRecyclerView.setAdapter(adapter);
                FastCatBusRunTimeTabDisp(userChosenStops);
                break;

            case "E1":
                adapter = new BusRouteStopTimesAdapter(this, e1BusTimes);
                stopTableRecyclerView.setAdapter(adapter);
                E1BusRunTimeTabDisp(userChosenStops);
                break;

            case "E2":
                adapter = new BusRouteStopTimesAdapter(this, e2BusTimes);
                stopTableRecyclerView.setAdapter(adapter);
                E2BusRunTimeTabDisp(userChosenStops);
                break;

            case "H":
                adapter = new BusRouteStopTimesAdapter(this, hBusTimes);
                stopTableRecyclerView.setAdapter(adapter);
                HBusRunTimeTabDisp(userChosenStops);
                break;

            case "HW":
                adapter = new BusRouteStopTimesAdapter(this, hwBusTimes);
                stopTableRecyclerView.setAdapter(adapter);
                HWBusRunTimeTabDisp(userChosenStops);
                break;

            case "G":
                adapter = new BusRouteStopTimesAdapter(this, gBusTimes);
                stopTableRecyclerView.setAdapter(adapter);
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
        System.out.println("SIZE *: " + userChosenStops.size());

        System.out.println("C1: " + userChosenStops);

        // Assigning the users chosen stops one by one (incrementing for loop)
        String stopStr1 = userChosenStops.get(0);
        String stopStr2 = userChosenStops.get(1);
        String stopStr3 = userChosenStops.get(2);

        // Grabbing the information
        List<C1> c1LocationStops1 = HomeActivity.cattracksDatabase.daoAccess().getC1TimesFromLocation(stopStr1);
        List<C1> c1LocationStops2 = HomeActivity.cattracksDatabase.daoAccess().getC1TimesFromLocation(stopStr2);
        List<C1> c1LocationStops3 = HomeActivity.cattracksDatabase.daoAccess().getC1TimesFromLocation(stopStr3);

        Iterator<C1> c1LocationIterator1 = c1LocationStops1.iterator();
        Iterator<C1> c1LocationIterator2 = c1LocationStops2.iterator();
        Iterator<C1> c1LocationIterator3 = c1LocationStops3.iterator();

        // Grabbing the information from
        while (c1LocationIterator1.hasNext() && c1LocationIterator2.hasNext() && c1LocationIterator3.hasNext())
        {
            C1 temp1 = c1LocationIterator1.next();
            C1 temp2 = c1LocationIterator2.next();
            C1 temp3 = c1LocationIterator3.next();

            c1BusTimes.add(temp1.getC1_run1());
            c1BusTimes.add(temp2.getC1_run1());
            c1BusTimes.add(temp3.getC1_run1());
            c1BusTimes.add(temp1.getC1_run2());
            c1BusTimes.add(temp2.getC1_run2());
            c1BusTimes.add(temp3.getC1_run2());
            c1BusTimes.add(temp1.getC1_run3());
            c1BusTimes.add(temp2.getC1_run3());
            c1BusTimes.add(temp3.getC1_run3());
            c1BusTimes.add(temp1.getC1_run4());
            c1BusTimes.add(temp2.getC1_run4());
            c1BusTimes.add(temp3.getC1_run4());
            c1BusTimes.add(temp1.getC1_run5());
            c1BusTimes.add(temp2.getC1_run5());
            c1BusTimes.add(temp3.getC1_run5());
            c1BusTimes.add(temp1.getC1_run6());
            c1BusTimes.add(temp2.getC1_run6());
            c1BusTimes.add(temp3.getC1_run6());
            c1BusTimes.add(temp1.getC1_run7());
            c1BusTimes.add(temp2.getC1_run7());
            c1BusTimes.add(temp3.getC1_run7());
            c1BusTimes.add(temp1.getC1_run8());
            c1BusTimes.add(temp2.getC1_run8());
            c1BusTimes.add(temp3.getC1_run8());
            c1BusTimes.add(temp1.getC1_run9());
            c1BusTimes.add(temp2.getC1_run9());
            c1BusTimes.add(temp3.getC1_run9());
            c1BusTimes.add(temp1.getC1_run10());
            c1BusTimes.add(temp2.getC1_run10());
            c1BusTimes.add(temp3.getC1_run10());
            c1BusTimes.add(temp1.getC1_run11());
            c1BusTimes.add(temp2.getC1_run11());
            c1BusTimes.add(temp3.getC1_run11());
            c1BusTimes.add(temp1.getC1_run12());
            c1BusTimes.add(temp2.getC1_run12());
            c1BusTimes.add(temp3.getC1_run12());
            c1BusTimes.add(temp1.getC1_run13());
            c1BusTimes.add(temp2.getC1_run13());
            c1BusTimes.add(temp3.getC1_run13());
            c1BusTimes.add(temp1.getC1_run14());
            c1BusTimes.add(temp2.getC1_run14());
            c1BusTimes.add(temp3.getC1_run14());
            c1BusTimes.add(temp1.getC1_run15());
            c1BusTimes.add(temp2.getC1_run15());
            c1BusTimes.add(temp3.getC1_run15());
            c1BusTimes.add(temp1.getC1_run16());
            c1BusTimes.add(temp2.getC1_run16());
            c1BusTimes.add(temp3.getC1_run16());
            c1BusTimes.add(temp1.getC1_run17());
            c1BusTimes.add(temp2.getC1_run17());
            c1BusTimes.add(temp3.getC1_run17());
            c1BusTimes.add(temp1.getC1_run18());
            c1BusTimes.add(temp2.getC1_run18());
            c1BusTimes.add(temp3.getC1_run18());
            c1BusTimes.add(temp1.getC1_run19());
            c1BusTimes.add(temp2.getC1_run19());
            c1BusTimes.add(temp3.getC1_run19());
            c1BusTimes.add(temp1.getC1_run20());
            c1BusTimes.add(temp2.getC1_run20());
            c1BusTimes.add(temp3.getC1_run20());
            c1BusTimes.add(temp1.getC1_run21());
            c1BusTimes.add(temp2.getC1_run21());
            c1BusTimes.add(temp3.getC1_run21());
            c1BusTimes.add(temp1.getC1_run22());
            c1BusTimes.add(temp2.getC1_run22());
            c1BusTimes.add(temp3.getC1_run22());
            c1BusTimes.add(temp1.getC1_run23());
            c1BusTimes.add(temp2.getC1_run23());
            c1BusTimes.add(temp3.getC1_run23());
        }

        //Clear out stops
        userChosenStops.clear();
    }

    public void C2BusRunTimeTabDisp(ArrayList<String>userChosenStops)
    {
        System.out.println("C2: " + userChosenStops);

        // Assigning the users chosen stops one by one (incrementing for loop)
        String stopStr1 = userChosenStops.get(0);
        String stopStr2 = userChosenStops.get(1);
        String stopStr3 = userChosenStops.get(2);

        // Grabbing the information from
        List<C2> c2LocationStops1 = HomeActivity.cattracksDatabase.daoAccess().getC2TimesFromLocation(stopStr1);
        List<C2> c2LocationStops2 = HomeActivity.cattracksDatabase.daoAccess().getC2TimesFromLocation(stopStr2);
        List<C2> c2LocationStops3 = HomeActivity.cattracksDatabase.daoAccess().getC2TimesFromLocation(stopStr3);

        Iterator<C2> c2LocationIterator1 = c2LocationStops1.iterator();
        Iterator<C2> c2LocationIterator2 = c2LocationStops2.iterator();
        Iterator<C2> c2LocationIterator3 = c2LocationStops3.iterator();

        while(c2LocationIterator1.hasNext() && c2LocationIterator2.hasNext() && c2LocationIterator3.hasNext())
        {
            C2 temp1 = c2LocationIterator1.next();
            C2 temp2 = c2LocationIterator2.next();
            C2 temp3 = c2LocationIterator3.next();

            c2BusTimes.add(temp1.getC2_run1());
            c2BusTimes.add(temp2.getC2_run1());
            c2BusTimes.add(temp3.getC2_run1());
            c2BusTimes.add(temp1.getC2_run2());
            c2BusTimes.add(temp2.getC2_run2());
            c2BusTimes.add(temp3.getC2_run2());
            c2BusTimes.add(temp1.getC2_run3());
            c2BusTimes.add(temp2.getC2_run3());
            c2BusTimes.add(temp3.getC2_run3());
            c2BusTimes.add(temp1.getC2_run4());
            c2BusTimes.add(temp2.getC2_run4());
            c2BusTimes.add(temp3.getC2_run4());
            c2BusTimes.add(temp1.getC2_run5());
            c2BusTimes.add(temp2.getC2_run5());
            c2BusTimes.add(temp3.getC2_run5());
            c2BusTimes.add(temp1.getC2_run6());
            c2BusTimes.add(temp2.getC2_run6());
            c2BusTimes.add(temp3.getC2_run6());
            c2BusTimes.add(temp1.getC2_run7());
            c2BusTimes.add(temp2.getC2_run7());
            c2BusTimes.add(temp3.getC2_run7());
            c2BusTimes.add(temp1.getC2_run8());
            c2BusTimes.add(temp2.getC2_run8());
            c2BusTimes.add(temp3.getC2_run8());
            c2BusTimes.add(temp1.getC2_run9());
            c2BusTimes.add(temp2.getC2_run9());
            c2BusTimes.add(temp3.getC2_run9());
            c2BusTimes.add(temp1.getC2_run10());
            c2BusTimes.add(temp2.getC2_run10());
            c2BusTimes.add(temp3.getC2_run10());
            c2BusTimes.add(temp1.getC2_run11());
            c2BusTimes.add(temp2.getC2_run11());
            c2BusTimes.add(temp3.getC2_run11());
            c2BusTimes.add(temp1.getC2_run12());
            c2BusTimes.add(temp2.getC2_run12());
            c2BusTimes.add(temp3.getC2_run12());
            c2BusTimes.add(temp1.getC2_run13());
            c2BusTimes.add(temp2.getC2_run13());
            c2BusTimes.add(temp3.getC2_run13());
            c2BusTimes.add(temp1.getC2_run14());
            c2BusTimes.add(temp2.getC2_run14());
            c2BusTimes.add(temp3.getC2_run14());
            c2BusTimes.add(temp1.getC2_run15());
            c2BusTimes.add(temp2.getC2_run15());
            c2BusTimes.add(temp3.getC2_run15());
            c2BusTimes.add(temp1.getC2_run16());
            c2BusTimes.add(temp2.getC2_run16());
            c2BusTimes.add(temp3.getC2_run16());
        }
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
    }

    public void E2BusRunTimeTabDisp(ArrayList<String>userChosenStops)
    {
        System.out.println("E2: " + userChosenStops);

        // Assigning the users chosen stops one by one (incrementing for loop)
        String stopStr1 = userChosenStops.get(0);
        String stopStr2 = userChosenStops.get(1);
        String stopStr3 = userChosenStops.get(2);

        // Grabbing the information from
        List<E2> e2LocationStops1 = HomeActivity.cattracksDatabase.daoAccess().getE2TimesFromLocation(stopStr1);
        List<E2> e2LocationStops2 = HomeActivity.cattracksDatabase.daoAccess().getE2TimesFromLocation(stopStr2);
        List<E2> e2LocationStops3 = HomeActivity.cattracksDatabase.daoAccess().getE2TimesFromLocation(stopStr3);

        Iterator<E2> e2LocationIterator1 = e2LocationStops1.iterator();
        Iterator<E2> e2LocationIterator2 = e2LocationStops2.iterator();
        Iterator<E2> e2LocationIterator3 = e2LocationStops3.iterator();

            while (e2LocationIterator1.hasNext() && e2LocationIterator2.hasNext() && e2LocationIterator3.hasNext())
            {
                E2 temp1 = e2LocationIterator1.next();
                E2 temp2 = e2LocationIterator2.next();
                E2 temp3 = e2LocationIterator3.next();

                e2BusTimes.add(temp1.getE2_run1());
                e2BusTimes.add(temp2.getE2_run1());
                e2BusTimes.add(temp3.getE2_run1());
                e2BusTimes.add(temp1.getE2_run2());
                e2BusTimes.add(temp2.getE2_run2());
                e2BusTimes.add(temp3.getE2_run2());
                e2BusTimes.add(temp1.getE2_run3());
                e2BusTimes.add(temp2.getE2_run3());
                e2BusTimes.add(temp3.getE2_run3());
                e2BusTimes.add(temp1.getE2_run4());
                e2BusTimes.add(temp2.getE2_run4());
                e2BusTimes.add(temp3.getE2_run4());
                e2BusTimes.add(temp1.getE2_run5());
                e2BusTimes.add(temp2.getE2_run5());
                e2BusTimes.add(temp3.getE2_run5());
                e2BusTimes.add(temp1.getE2_run6());
                e2BusTimes.add(temp2.getE2_run6());
                e2BusTimes.add(temp3.getE2_run6());
                e2BusTimes.add(temp1.getE2_run7());
                e2BusTimes.add(temp2.getE2_run7());
                e2BusTimes.add(temp3.getE2_run7());
                e2BusTimes.add(temp1.getE2_run8());
                e2BusTimes.add(temp2.getE2_run8());
                e2BusTimes.add(temp3.getE2_run8());
                e2BusTimes.add(temp1.getE2_run9());
                e2BusTimes.add(temp2.getE2_run9());
                e2BusTimes.add(temp3.getE2_run9());
                e2BusTimes.add(temp1.getE2_run10());
                e2BusTimes.add(temp2.getE2_run10());
                e2BusTimes.add(temp3.getE2_run10());
            }
    }

    public void HBusRunTimeTabDisp(ArrayList<String>userChosenStops)
    {
        System.out.println("H: " + userChosenStops);

        // Assigning the users chosen stops one by one (incrementing for loop)
        String stopStr1 = userChosenStops.get(0);
        String stopStr2 = userChosenStops.get(1);
        String stopStr3 = userChosenStops.get(2);

        // Grabbing the information from
        List<H> hLocationStops1 = HomeActivity.cattracksDatabase.daoAccess().getHTimesFromLocation(stopStr1);
        List<H> hLocationStops2 = HomeActivity.cattracksDatabase.daoAccess().getHTimesFromLocation(stopStr2);
        List<H> hLocationStops3 = HomeActivity.cattracksDatabase.daoAccess().getHTimesFromLocation(stopStr3);

        Iterator<H> hLocationIterator1 = hLocationStops1.iterator();
        Iterator<H> hLocationIterator2 = hLocationStops2.iterator();
        Iterator<H> hLocationIterator3 = hLocationStops3.iterator();

        while (hLocationIterator1.hasNext() && hLocationIterator2.hasNext() && hLocationIterator3.hasNext())
        {
            H temp1 = hLocationIterator1.next();
            H temp2 = hLocationIterator2.next();
            H temp3 = hLocationIterator3.next();

                hBusTimes.add(temp1.getH_run1());hBusTimes.add(temp2.getH_run1());hBusTimes.add(temp3.getH_run1());
                hBusTimes.add(temp1.getH_run2());hBusTimes.add(temp2.getH_run2());hBusTimes.add(temp3.getH_run2());
                hBusTimes.add(temp1.getH_run3());hBusTimes.add(temp2.getH_run3());hBusTimes.add(temp3.getH_run3());
                hBusTimes.add(temp1.getH_run4());hBusTimes.add(temp2.getH_run4());hBusTimes.add(temp3.getH_run4());
                hBusTimes.add(temp1.getH_run5());hBusTimes.add(temp2.getH_run5());hBusTimes.add(temp3.getH_run5());
                hBusTimes.add(temp1.getH_run6());hBusTimes.add(temp2.getH_run6());hBusTimes.add(temp3.getH_run6());
                hBusTimes.add(temp1.getH_run7());hBusTimes.add(temp2.getH_run7());hBusTimes.add(temp3.getH_run7());
                hBusTimes.add(temp1.getH_run8());hBusTimes.add(temp2.getH_run8());hBusTimes.add(temp3.getH_run8());
                hBusTimes.add(temp1.getH_run9());hBusTimes.add(temp2.getH_run9());hBusTimes.add(temp3.getH_run9());
                hBusTimes.add(temp1.getH_run10());hBusTimes.add(temp2.getH_run10());hBusTimes.add(temp3.getH_run10());
                hBusTimes.add(temp1.getH_run11());hBusTimes.add(temp2.getH_run11());hBusTimes.add(temp3.getH_run11());
                hBusTimes.add(temp1.getH_run12());hBusTimes.add(temp2.getH_run12());hBusTimes.add(temp3.getH_run12());
                hBusTimes.add(temp1.getH_run13());hBusTimes.add(temp2.getH_run13());hBusTimes.add(temp3.getH_run13());
                hBusTimes.add(temp1.getH_run14());hBusTimes.add(temp2.getH_run14());hBusTimes.add(temp3.getH_run14());
                hBusTimes.add(temp1.getH_run15());hBusTimes.add(temp2.getH_run15());hBusTimes.add(temp3.getH_run15());
                hBusTimes.add(temp1.getH_run16());hBusTimes.add(temp2.getH_run16());hBusTimes.add(temp3.getH_run16());
                hBusTimes.add(temp1.getH_run17());hBusTimes.add(temp2.getH_run17());hBusTimes.add(temp3.getH_run17());
                hBusTimes.add(temp1.getH_run18());hBusTimes.add(temp2.getH_run18());hBusTimes.add(temp3.getH_run18());
                hBusTimes.add(temp1.getH_run19());hBusTimes.add(temp2.getH_run19());hBusTimes.add(temp3.getH_run19());
                hBusTimes.add(temp1.getH_run20());hBusTimes.add(temp2.getH_run20());hBusTimes.add(temp3.getH_run20());
                hBusTimes.add(temp1.getH_run21());hBusTimes.add(temp2.getH_run21());hBusTimes.add(temp3.getH_run21());
                hBusTimes.add(temp1.getH_run22());hBusTimes.add(temp2.getH_run22());hBusTimes.add(temp3.getH_run22());
                hBusTimes.add(temp1.getH_run23());hBusTimes.add(temp2.getH_run23());hBusTimes.add(temp3.getH_run23());
                hBusTimes.add(temp1.getH_run24());hBusTimes.add(temp2.getH_run24());hBusTimes.add(temp3.getH_run24());
                hBusTimes.add(temp1.getH_run25());hBusTimes.add(temp2.getH_run25());hBusTimes.add(temp3.getH_run25());
                hBusTimes.add(temp1.getH_run26());hBusTimes.add(temp2.getH_run26());hBusTimes.add(temp3.getH_run26());
                hBusTimes.add(temp1.getH_run27());hBusTimes.add(temp2.getH_run27());hBusTimes.add(temp3.getH_run27());
                hBusTimes.add(temp1.getH_run28());hBusTimes.add(temp2.getH_run28());hBusTimes.add(temp3.getH_run28());
                hBusTimes.add(temp1.getH_run29());hBusTimes.add(temp2.getH_run29());hBusTimes.add(temp3.getH_run29());
                hBusTimes.add(temp1.getH_run30());hBusTimes.add(temp2.getH_run30());hBusTimes.add(temp3.getH_run30());
                hBusTimes.add(temp1.getH_run31());hBusTimes.add(temp2.getH_run31());hBusTimes.add(temp3.getH_run31());
                hBusTimes.add(temp1.getH_run32());hBusTimes.add(temp2.getH_run32());hBusTimes.add(temp3.getH_run32());
                hBusTimes.add(temp1.getH_run33());hBusTimes.add(temp2.getH_run33());hBusTimes.add(temp3.getH_run33());
                hBusTimes.add(temp1.getH_run34());hBusTimes.add(temp2.getH_run34());hBusTimes.add(temp3.getH_run34());
                hBusTimes.add(temp1.getH_run35());hBusTimes.add(temp2.getH_run35());hBusTimes.add(temp3.getH_run35());
                hBusTimes.add(temp1.getH_run36());hBusTimes.add(temp2.getH_run36());hBusTimes.add(temp3.getH_run36());
                hBusTimes.add(temp1.getH_run37());hBusTimes.add(temp2.getH_run37());hBusTimes.add(temp3.getH_run37());
                hBusTimes.add(temp1.getH_run38());hBusTimes.add(temp2.getH_run38());hBusTimes.add(temp3.getH_run38());
                hBusTimes.add(temp1.getH_run39());hBusTimes.add(temp2.getH_run39());hBusTimes.add(temp3.getH_run39());
                hBusTimes.add(temp1.getH_run40());hBusTimes.add(temp2.getH_run40());hBusTimes.add(temp3.getH_run40());
                hBusTimes.add(temp1.getH_run41());hBusTimes.add(temp2.getH_run41());hBusTimes.add(temp3.getH_run41());
                hBusTimes.add(temp1.getH_run42());hBusTimes.add(temp2.getH_run42());hBusTimes.add(temp3.getH_run42());
                hBusTimes.add(temp1.getH_run43());hBusTimes.add(temp2.getH_run43());hBusTimes.add(temp3.getH_run43());
                hBusTimes.add(temp1.getH_run44());hBusTimes.add(temp2.getH_run44());hBusTimes.add(temp3.getH_run44());
                hBusTimes.add(temp1.getH_run45());hBusTimes.add(temp2.getH_run45());hBusTimes.add(temp3.getH_run45());
                hBusTimes.add(temp1.getH_run46());hBusTimes.add(temp2.getH_run46());hBusTimes.add(temp3.getH_run46());
                hBusTimes.add(temp1.getH_run47());hBusTimes.add(temp2.getH_run47());hBusTimes.add(temp3.getH_run47());
                hBusTimes.add(temp1.getH_run48());hBusTimes.add(temp2.getH_run48());hBusTimes.add(temp3.getH_run48());
                hBusTimes.add(temp1.getH_run49());hBusTimes.add(temp2.getH_run49());hBusTimes.add(temp3.getH_run49());
                hBusTimes.add(temp1.getH_run50());hBusTimes.add(temp2.getH_run50());hBusTimes.add(temp3.getH_run50());
                hBusTimes.add(temp1.getH_run51());hBusTimes.add(temp2.getH_run51());hBusTimes.add(temp3.getH_run51());
                hBusTimes.add(temp1.getH_run52());hBusTimes.add(temp2.getH_run52());hBusTimes.add(temp3.getH_run52());
                hBusTimes.add(temp1.getH_run53());hBusTimes.add(temp2.getH_run53());hBusTimes.add(temp3.getH_run53());
                hBusTimes.add(temp1.getH_run54());hBusTimes.add(temp2.getH_run54());hBusTimes.add(temp3.getH_run54());
                hBusTimes.add(temp1.getH_run55());hBusTimes.add(temp2.getH_run55());hBusTimes.add(temp3.getH_run55());
                hBusTimes.add(temp1.getH_run56());hBusTimes.add(temp2.getH_run56());hBusTimes.add(temp3.getH_run56());
        }
    }

    public void HWBusRunTimeTabDisp(ArrayList<String>userChosenStops)
    {
        System.out.println("HW: " + userChosenStops);

        // Assigning the users chosen stops one by one (incrementing for loop)
        String stopStr1 = userChosenStops.get(0);
        String stopStr2 = userChosenStops.get(1);
        String stopStr3 = userChosenStops.get(2);

        // Grabbing the information from
        List<HW> hwLocationStops1 = HomeActivity.cattracksDatabase.daoAccess().getHWTimesFromLocation(stopStr1);
        List<HW> hwLocationStops2 = HomeActivity.cattracksDatabase.daoAccess().getHWTimesFromLocation(stopStr2);
        List<HW> hwLocationStops3 = HomeActivity.cattracksDatabase.daoAccess().getHWTimesFromLocation(stopStr3);

        Iterator<HW> hwLocationIterator1 = hwLocationStops1.iterator();
        Iterator<HW> hwLocationIterator2 = hwLocationStops2.iterator();
        Iterator<HW> hwLocationIterator3 = hwLocationStops3.iterator();

        while (hwLocationIterator1.hasNext() && hwLocationIterator2.hasNext() && hwLocationIterator3.hasNext())
        {
            HW temp1 = hwLocationIterator1.next();
            HW temp2 = hwLocationIterator2.next();
            HW temp3 = hwLocationIterator3.next();

                hwBusTimes.add(temp1.getHw_run1());hwBusTimes.add(temp2.getHw_run1());hwBusTimes.add(temp3.getHw_run1());
                hwBusTimes.add(temp1.getHw_run2());hwBusTimes.add(temp2.getHw_run2());hwBusTimes.add(temp3.getHw_run2());
                hwBusTimes.add(temp1.getHw_run3());hwBusTimes.add(temp2.getHw_run3());hwBusTimes.add(temp3.getHw_run3());
                hwBusTimes.add(temp1.getHw_run4());hwBusTimes.add(temp2.getHw_run4());hwBusTimes.add(temp3.getHw_run4());
                hwBusTimes.add(temp1.getHw_run5());hwBusTimes.add(temp2.getHw_run5());hwBusTimes.add(temp3.getHw_run5());
                hwBusTimes.add(temp1.getHw_run6());hwBusTimes.add(temp2.getHw_run6());hwBusTimes.add(temp3.getHw_run6());
                hwBusTimes.add(temp1.getHw_run7());hwBusTimes.add(temp2.getHw_run7());hwBusTimes.add(temp3.getHw_run7());
                hwBusTimes.add(temp1.getHw_run8());hwBusTimes.add(temp2.getHw_run8());hwBusTimes.add(temp3.getHw_run8());
                hwBusTimes.add(temp1.getHw_run9());hwBusTimes.add(temp2.getHw_run9());hwBusTimes.add(temp3.getHw_run9());
                hwBusTimes.add(temp1.getHw_run10());hwBusTimes.add(temp2.getHw_run10());hwBusTimes.add(temp3.getHw_run10());
                hwBusTimes.add(temp1.getHw_run11());hwBusTimes.add(temp2.getHw_run11());hwBusTimes.add(temp3.getHw_run11());
                hwBusTimes.add(temp1.getHw_run12());hwBusTimes.add(temp2.getHw_run12());hwBusTimes.add(temp3.getHw_run12());
                hwBusTimes.add(temp1.getHw_run13());hwBusTimes.add(temp2.getHw_run13());hwBusTimes.add(temp3.getHw_run13());
                hwBusTimes.add(temp1.getHw_run14());hwBusTimes.add(temp2.getHw_run14());hwBusTimes.add(temp3.getHw_run14());
                hwBusTimes.add(temp1.getHw_run15());hwBusTimes.add(temp2.getHw_run15());hwBusTimes.add(temp3.getHw_run15());
                hwBusTimes.add(temp1.getHw_run16());hwBusTimes.add(temp2.getHw_run16());hwBusTimes.add(temp3.getHw_run16());
                hwBusTimes.add(temp1.getHw_run17());hwBusTimes.add(temp2.getHw_run17());hwBusTimes.add(temp3.getHw_run17());
                hwBusTimes.add(temp1.getHw_run18());hwBusTimes.add(temp2.getHw_run18());hwBusTimes.add(temp3.getHw_run18());
                hwBusTimes.add(temp1.getHw_run19());hwBusTimes.add(temp2.getHw_run19());hwBusTimes.add(temp3.getHw_run19());
                hwBusTimes.add(temp1.getHw_run20());hwBusTimes.add(temp2.getHw_run20());hwBusTimes.add(temp3.getHw_run20());
        }
    }

    public void GBusRunTimeTabDisp(ArrayList<String>userChosenStops)
    {
        System.out.println("G: " + userChosenStops);

        // Assigning the users chosen stops one by one (incrementing for loop)
        String stopStr1 = userChosenStops.get(0);
        String stopStr2 = userChosenStops.get(1);
        String stopStr3 = userChosenStops.get(2);

        // Grabbing the information from
        List<G> gLocationStops1 = HomeActivity.cattracksDatabase.daoAccess().getGTimesFromLocation(stopStr1);
        List<G> gLocationStops2 = HomeActivity.cattracksDatabase.daoAccess().getGTimesFromLocation(stopStr2);
        List<G> gLocationStops3 = HomeActivity.cattracksDatabase.daoAccess().getGTimesFromLocation(stopStr3);

        Iterator<G> gLocationIterator1 = gLocationStops1.iterator();
        Iterator<G> gLocationIterator2 = gLocationStops2.iterator();
        Iterator<G> gLocationIterator3 = gLocationStops3.iterator();

        while (gLocationIterator1.hasNext() && gLocationIterator2.hasNext() && gLocationIterator3.hasNext())
        {
            G temp1 = gLocationIterator1.next();
            G temp2 = gLocationIterator2.next();
            G temp3 = gLocationIterator3.next();

                gBusTimes.add(temp1.getG_run1());gBusTimes.add(temp2.getG_run1());gBusTimes.add(temp3.getG_run1());
                gBusTimes.add(temp1.getG_run2());gBusTimes.add(temp2.getG_run2());gBusTimes.add(temp3.getG_run2());
                gBusTimes.add(temp1.getG_run3());gBusTimes.add(temp2.getG_run3());gBusTimes.add(temp3.getG_run3());
                gBusTimes.add(temp1.getG_run4());gBusTimes.add(temp2.getG_run4());gBusTimes.add(temp3.getG_run4());
                gBusTimes.add(temp1.getG_run5());gBusTimes.add(temp2.getG_run5());gBusTimes.add(temp3.getG_run5());
                gBusTimes.add(temp1.getG_run6());gBusTimes.add(temp2.getG_run6());gBusTimes.add(temp3.getG_run6());
                gBusTimes.add(temp1.getG_run7());gBusTimes.add(temp2.getG_run7());gBusTimes.add(temp3.getG_run7());
                gBusTimes.add(temp1.getG_run8());gBusTimes.add(temp2.getG_run8());gBusTimes.add(temp3.getG_run8());
                gBusTimes.add(temp1.getG_run9());gBusTimes.add(temp2.getG_run9());gBusTimes.add(temp3.getG_run9());
                gBusTimes.add(temp1.getG_run10());gBusTimes.add(temp2.getG_run10());gBusTimes.add(temp3.getG_run10());
                gBusTimes.add(temp1.getG_run11());gBusTimes.add(temp2.getG_run11());gBusTimes.add(temp3.getG_run11());
                gBusTimes.add(temp1.getG_run12());gBusTimes.add(temp2.getG_run12());gBusTimes.add(temp3.getG_run12());
                gBusTimes.add(temp1.getG_run13());gBusTimes.add(temp2.getG_run13());gBusTimes.add(temp3.getG_run13());
                gBusTimes.add(temp1.getG_run14());gBusTimes.add(temp2.getG_run14());gBusTimes.add(temp3.getG_run14());
        }

    }



    public void FastCatBusRunTimeTabDisp(ArrayList<String>userChosenStops)
    {
        System.out.println("FC: " + userChosenStops);

        // Assigning the users chosen stops one by one (incrementing for loop)
        String stopStr1 = userChosenStops.get(0);
        String stopStr2 = userChosenStops.get(1);
        String stopStr3 = userChosenStops.get(2);

        // Grabbing the information from
        List<FC> fcLocationStops1 = HomeActivity.cattracksDatabase.daoAccess().getFCTimesFromLocation(stopStr1);
        List<FC> fcLocationStops2 = HomeActivity.cattracksDatabase.daoAccess().getFCTimesFromLocation(stopStr2);
        List<FC> fcLocationStops3 = HomeActivity.cattracksDatabase.daoAccess().getFCTimesFromLocation(stopStr3);

        Iterator<FC> fcLocationIterator1 = fcLocationStops1.iterator();
        Iterator<FC> fcLocationIterator2 = fcLocationStops2.iterator();
        Iterator<FC> fcLocationIterator3 = fcLocationStops3.iterator();

        while (fcLocationIterator1.hasNext() && fcLocationIterator2.hasNext() && fcLocationIterator3.hasNext())
        {
            FC temp1 = fcLocationIterator1.next();
            FC temp2 = fcLocationIterator2.next();
            FC temp3 = fcLocationIterator3.next();

            fcBusTimes.add(temp1.getFc_run1());fcBusTimes.add(temp2.getFc_run1());fcBusTimes.add(temp3.getFc_run1());
            fcBusTimes.add(temp1.getFc_run2());fcBusTimes.add(temp2.getFc_run2());fcBusTimes.add(temp3.getFc_run2());
            fcBusTimes.add(temp1.getFc_run3());fcBusTimes.add(temp2.getFc_run3());fcBusTimes.add(temp3.getFc_run3());
            fcBusTimes.add(temp1.getFc_run4());fcBusTimes.add(temp2.getFc_run4());fcBusTimes.add(temp3.getFc_run4());
            fcBusTimes.add(temp1.getFc_run5());fcBusTimes.add(temp2.getFc_run5());fcBusTimes.add(temp3.getFc_run5());
            fcBusTimes.add(temp1.getFc_run6());fcBusTimes.add(temp2.getFc_run6());fcBusTimes.add(temp3.getFc_run6());
            fcBusTimes.add(temp1.getFc_run7());fcBusTimes.add(temp2.getFc_run7());fcBusTimes.add(temp3.getFc_run7());
            fcBusTimes.add(temp1.getFc_run8());fcBusTimes.add(temp2.getFc_run8());fcBusTimes.add(temp3.getFc_run8());
            fcBusTimes.add(temp1.getFc_run9());fcBusTimes.add(temp2.getFc_run9());fcBusTimes.add(temp3.getFc_run9());
            fcBusTimes.add(temp1.getFc_run10());fcBusTimes.add(temp2.getFc_run10());fcBusTimes.add(temp3.getFc_run10());
            fcBusTimes.add(temp1.getFc_run11());fcBusTimes.add(temp2.getFc_run11());fcBusTimes.add(temp3.getFc_run11());
            fcBusTimes.add(temp1.getFc_run12());fcBusTimes.add(temp2.getFc_run12());fcBusTimes.add(temp3.getFc_run12());
            fcBusTimes.add(temp1.getFc_run13());fcBusTimes.add(temp2.getFc_run13());fcBusTimes.add(temp3.getFc_run13());
            fcBusTimes.add(temp1.getFc_run14());fcBusTimes.add(temp2.getFc_run14());fcBusTimes.add(temp3.getFc_run14());
            fcBusTimes.add(temp1.getFc_run15());fcBusTimes.add(temp2.getFc_run15());fcBusTimes.add(temp3.getFc_run15());
        }
    }
}
