package uc.cattracks.cattracksapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

public class DisplayRouteRunTimesActivity extends AppCompatActivity {
    // PATHWAYS TO OTHER ACTIVITIES
    Intent plan_trip_segue;
    Intent bus_updates_segue;
    Intent start_map;

    // USER INTERFACE ELEMENTS
    ImageButton navigation_button;   // Navigation menu structure
    LinearLayout navigation_menu;    // Opens / closes navigation menu
    ImageButton plan_trip_button;    // Opens trip planning activity
    ImageButton bus_alerts_button;   // Opens bus alerts Twitter feed activity.
    ImageButton map_button;          // Opens activity where users can select a stop to be showcased on a map (Google Maps)


    private static List<String> c1BusTimes = new ArrayList<>();
    private static List<String> c2BusTimes= new ArrayList<>();
    private static List<String> e1BusTimes= new ArrayList<>();
    private static List<String> e2BusTimes= new ArrayList<>();
    private static List<String> fcBusTimes= new ArrayList<>();
    private static List<String> gBusTimes= new ArrayList<>();
    private static List<String> hBusTimes= new ArrayList<>();
    private static List<String> hwBusTimes= new ArrayList<>();
    private static List<String> busToAdapter = new ArrayList<>();

    public static Intent intent;
    public static Bundle extras;
    public static String busName;
    public static String locationAbb;
    public static String destinationAbb;
    public static String locationName;
    public static String destinationName;
    public static String busAbb;


    private static RecyclerView busStopTimesRecyclerView;
    private static BusRouteStopTimesAdapter adapter;
    private TextView bus_name;
    private TextView location_name;
    private TextView destination_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_route_run_times);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setting up toolbar menu
        setupToolBar();

        // Setting up navigation sliding menu
        setupNavigationMenu();

        String temp = "";
        busName = "";
        locationAbb = "";
        destinationAbb = "";

        getAllIntentInformation();


        busStopTimesRecyclerView = findViewById(R.id.routesRecyclerView);
        busStopTimesRecyclerView.setLayoutManager(new GridLayoutManager(this,2 ));
//        adapter = new BusRouteStopTimesAdapter(this, busToAdapter);
//        busStopTimesRecyclerView.setAdapter(adapter);

        executeQueriesBasedOnIntentInformation();
        bus_name = findViewById(R.id.bus_name_text_view);
        location_name = findViewById(R.id.location_text_view);
        destination_name = findViewById(R.id.destination_text_view);

        if (busName =="FC") {
            bus_name.setText("FastCat");
        } else {
            bus_name.setText(busName);
        }

        location_name.setText(locationName);
        destination_name.setText(destinationName);


    }



    // USER INTERFACE FUNCTIONS
    // Navigation Sliding Menu
    public void setupNavigationMenu(){
        // Setting up pathways to other activities
        plan_trip_segue = new Intent(this, LocationsList.class);
        bus_updates_segue = new Intent(this, BusUpdatesActivity.class);
        start_map = new Intent(this, MapStopsActivity.class);

        // Setting up user interface elements
        navigation_menu = findViewById(R.id.navigation_menu);


        plan_trip_button = findViewById(R.id.plan_trip_button);
        plan_trip_button.setOnClickListener((View v) -> {
            animate_navigation_menu();
            startActivity(plan_trip_segue);

        });


        bus_alerts_button = findViewById(R.id.bus_updates_button);
        bus_alerts_button.setOnClickListener((View v) -> {
            animate_navigation_menu();
            startActivity(bus_updates_segue);
        });


        // Set intent on MapStopsActivity
        map_button = findViewById(R.id.map_button);
        map_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(start_map);
                animate_navigation_menu();
            }
        });
    }



    public void animate_navigation_menu(){
        Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);


        if(navigation_menu.getVisibility()==View.INVISIBLE) {
            navigation_menu.startAnimation(slideUp);
            navigation_menu.setVisibility(View.VISIBLE);

        }else{
            navigation_menu.startAnimation(slideDown);
            navigation_menu.setVisibility(View.INVISIBLE);
        }
    }



    // Menu Toolbar
    public void setupToolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Setting up toolbar structure
        getMenuInflater().inflate(R.menu.toolbar_menu_2, menu);

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.navigation_button:
                animate_navigation_menu();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void getAllIntentInformation() {
        intent = getIntent();
        extras = intent.getExtras();
        busName = extras.getString("Bus");
        locationAbb = extras.getString("locationAbb");
        destinationAbb = extras.getString("destinationAbb");
        locationName = extras.getString("Location");
        destinationName = extras.getString("Destination");

    }

    public void executeQueriesBasedOnIntentInformation(){

        switch(busName) {
            case "C1":
                combineC1Stops();
                break;
            case "C2":
                combineC2Stops();
                break;
            case "E1":
                combineE1Stops();
                break;
            case "E2":
                combineE2Stops();
                break;
            case "FastCat":
                combineFastCatStops();
                break;
            case "G":
                combineGStops();
                break;
            case "H":
                combineHStops();
                break;
            case "HW":
                combineHWStops();
                break;
                default:
                    break;
        }
    }


    public void combineC1Stops() {
        List<C1> c1LocationStops = HomeActivity.cattracksDatabase.daoAccess().getC1TimesFromLocation(locationAbb);
        List<C1> c1DestinationStops = HomeActivity.cattracksDatabase.daoAccess().getC1TimesToDestination(destinationAbb);

        Iterator<C1> c1LocationIterator = c1LocationStops.iterator();
        Iterator<C1> c1DestinationIterator = c1DestinationStops.iterator();

        c1BusTimes.clear();
        while(c1LocationIterator.hasNext() && c1DestinationIterator.hasNext()) {

            C1 temp = c1LocationIterator.next(); C1 temp2 = c1DestinationIterator.next();



            c1BusTimes.add(temp.getC1_run1()); c1BusTimes.add(temp2.getC1_run1());
            c1BusTimes.add(temp.getC1_run2()); c1BusTimes.add(temp2.getC1_run2());
            c1BusTimes.add(temp.getC1_run3()); c1BusTimes.add(temp2.getC1_run3());
            c1BusTimes.add(temp.getC1_run4()); c1BusTimes.add(temp2.getC1_run4());
            c1BusTimes.add(temp.getC1_run5()); c1BusTimes.add(temp2.getC1_run5());
            c1BusTimes.add(temp.getC1_run6()); c1BusTimes.add(temp2.getC1_run6());
            c1BusTimes.add(temp.getC1_run7()); c1BusTimes.add(temp2.getC1_run7());
            c1BusTimes.add(temp.getC1_run8()); c1BusTimes.add(temp2.getC1_run8());
            c1BusTimes.add(temp.getC1_run9()); c1BusTimes.add(temp2.getC1_run9());
            c1BusTimes.add(temp.getC1_run10()); c1BusTimes.add(temp2.getC1_run10());
            c1BusTimes.add(temp.getC1_run11()); c1BusTimes.add(temp2.getC1_run11());
            c1BusTimes.add(temp.getC1_run12()); c1BusTimes.add(temp2.getC1_run12());
            c1BusTimes.add(temp.getC1_run13()); c1BusTimes.add(temp2.getC1_run13());
            c1BusTimes.add(temp.getC1_run14()); c1BusTimes.add(temp2.getC1_run14());
            c1BusTimes.add(temp.getC1_run15()); c1BusTimes.add(temp2.getC1_run15());
            c1BusTimes.add(temp.getC1_run16()); c1BusTimes.add(temp2.getC1_run16());
            c1BusTimes.add(temp.getC1_run17()); c1BusTimes.add(temp2.getC1_run17());
            c1BusTimes.add(temp.getC1_run18()); c1BusTimes.add(temp2.getC1_run18());
            c1BusTimes.add(temp.getC1_run19()); c1BusTimes.add(temp2.getC1_run19());
            c1BusTimes.add(temp.getC1_run20()); c1BusTimes.add(temp2.getC1_run20());
            c1BusTimes.add(temp.getC1_run21()); c1BusTimes.add(temp2.getC1_run21());
            c1BusTimes.add(temp.getC1_run22()); c1BusTimes.add(temp2.getC1_run22());
            c1BusTimes.add(temp.getC1_run23()); c1BusTimes.add(temp2.getC1_run24());

        }


        setUpAdapter(c1BusTimes);
        for(String c1: c1BusTimes) {
            System.out.println(c1 + " "
            );
        }
    }


    public void combineC2Stops() {
        List<C2> c2LocationStops = HomeActivity.cattracksDatabase.daoAccess().getC2TimesFromLocation(locationAbb);
        List<C2> c2DestinationStops = HomeActivity.cattracksDatabase.daoAccess().getC2TimesToDestination(destinationAbb);

        Iterator<C2> c2LocationIterator = c2LocationStops.iterator();
        Iterator<C2> c2DestinationIterator = c2DestinationStops.iterator();

        c2BusTimes.clear();
        while(c2LocationIterator.hasNext() && c2DestinationIterator.hasNext()) {

            C2 temp = c2LocationIterator.next(); C2 temp2 = c2DestinationIterator.next();
            c2BusTimes.add(temp.getC2_run1()); c2BusTimes.add(temp2.getC2_run1());
            c2BusTimes.add(temp.getC2_run2()); c2BusTimes.add(temp2.getC2_run2());
            c2BusTimes.add(temp.getC2_run3()); c2BusTimes.add(temp2.getC2_run3());
            c2BusTimes.add(temp.getC2_run4()); c2BusTimes.add(temp2.getC2_run4());
            c2BusTimes.add(temp.getC2_run5()); c2BusTimes.add(temp2.getC2_run5());
            c2BusTimes.add(temp.getC2_run6()); c2BusTimes.add(temp2.getC2_run6());
            c2BusTimes.add(temp.getC2_run7()); c2BusTimes.add(temp2.getC2_run7());
            c2BusTimes.add(temp.getC2_run8()); c2BusTimes.add(temp2.getC2_run8());
            c2BusTimes.add(temp.getC2_run9()); c2BusTimes.add(temp2.getC2_run9());
            c2BusTimes.add(temp.getC2_run10()); c2BusTimes.add(temp2.getC2_run10());
            c2BusTimes.add(temp.getC2_run11()); c2BusTimes.add(temp2.getC2_run11());
            c2BusTimes.add(temp.getC2_run12()); c2BusTimes.add(temp2.getC2_run12());
            c2BusTimes.add(temp.getC2_run13()); c2BusTimes.add(temp2.getC2_run13());
            c2BusTimes.add(temp.getC2_run14()); c2BusTimes.add(temp2.getC2_run14());
            c2BusTimes.add(temp.getC2_run15()); c2BusTimes.add(temp2.getC2_run15());
            c2BusTimes.add(temp.getC2_run16()); c2BusTimes.add(temp2.getC2_run16());
        }


        setUpAdapter(c2BusTimes);
    }

    public void combineE1Stops() {
        List<E1> e1LocationStops = HomeActivity.cattracksDatabase.daoAccess().getE1TimesFromLocation(locationAbb);
        List<E1> e1DestinationStops = HomeActivity.cattracksDatabase.daoAccess().getE1TimesToDestination(destinationAbb);

        Iterator<E1> e1LocationIterator = e1LocationStops.iterator();
        Iterator<E1> e1DestinationIterator = e1DestinationStops.iterator();


        e1BusTimes.clear();
        while(e1LocationIterator.hasNext() && e1DestinationIterator.hasNext()) {

            E1 temp = e1LocationIterator.next(); E1 temp2 = e1DestinationIterator.next();

            e1BusTimes.add(temp.getE1_run1()); e1BusTimes.add(temp2.getE1_run1());
            e1BusTimes.add(temp.getE1_run2()); e1BusTimes.add(temp2.getE1_run2());
            e1BusTimes.add(temp.getE1_run3()); e1BusTimes.add(temp2.getE1_run3());
            e1BusTimes.add(temp.getE1_run4()); e1BusTimes.add(temp2.getE1_run4());
            e1BusTimes.add(temp.getE1_run5()); e1BusTimes.add(temp2.getE1_run5());
            e1BusTimes.add(temp.getE1_run6()); e1BusTimes.add(temp2.getE1_run6());
            e1BusTimes.add(temp.getE1_run7()); e1BusTimes.add(temp2.getE1_run7());
            e1BusTimes.add(temp.getE1_run8()); e1BusTimes.add(temp2.getE1_run8());
            e1BusTimes.add(temp.getE1_run9()); e1BusTimes.add(temp2.getE1_run9());
        }


        setUpAdapter(e1BusTimes);
        for(String e1: e1BusTimes) {
            System.out.println(e1 + " "
            );
        }

    }

    public void combineE2Stops() {
        List<E2> e2LocationStops = HomeActivity.cattracksDatabase.daoAccess().getE2TimesFromLocation(locationAbb);
        List<E2> e2DestinationStops = HomeActivity.cattracksDatabase.daoAccess().getE2TimesToDestination(destinationAbb);

        Iterator<E2> e2LocationIterator = e2LocationStops.iterator();
        Iterator<E2> e2DestinationIterator = e2DestinationStops.iterator();


        e2BusTimes.clear();
        while(e2LocationIterator.hasNext() && e2DestinationIterator.hasNext()) {

            E2 temp = e2LocationIterator.next(); E2 temp2 = e2DestinationIterator.next();

            e2BusTimes.add(temp.getE2_run1()); e2BusTimes.add(temp2.getE2_run1());
            e2BusTimes.add(temp.getE2_run2()); e2BusTimes.add(temp2.getE2_run2());
            e2BusTimes.add(temp.getE2_run3()); e2BusTimes.add(temp2.getE2_run3());
            e2BusTimes.add(temp.getE2_run4()); e2BusTimes.add(temp2.getE2_run4());
            e2BusTimes.add(temp.getE2_run5()); e2BusTimes.add(temp2.getE2_run5());
            e2BusTimes.add(temp.getE2_run6()); e2BusTimes.add(temp2.getE2_run6());
            e2BusTimes.add(temp.getE2_run7()); e2BusTimes.add(temp2.getE2_run7());
            e2BusTimes.add(temp.getE2_run8()); e2BusTimes.add(temp2.getE2_run8());
            e2BusTimes.add(temp.getE2_run9()); e2BusTimes.add(temp2.getE2_run9());
            e2BusTimes.add(temp.getE2_run10()); e2BusTimes.add(temp2.getE2_run10());
        }

        setUpAdapter(e2BusTimes);
        for(String e2: e2BusTimes) {
            System.out.println(e2 + " "
            );
        }
    }

    public void combineFastCatStops() {

        System.out.println("Location: " + locationAbb + " Destination: " + destinationAbb);
        List<FC> fastCatLocationStops = HomeActivity.cattracksDatabase.daoAccess().getFCTimesFromLocation(locationAbb);
        List<FC> fastCatDestinationStops = HomeActivity.cattracksDatabase.daoAccess().getFCTimesToDestination(destinationAbb);
        Iterator<FC> fastCatLocationIterator = fastCatLocationStops.iterator();
        Iterator<FC> fastCatDestinationIterator = fastCatDestinationStops.iterator();


        fcBusTimes.clear();
        while(fastCatLocationIterator.hasNext() && fastCatDestinationIterator.hasNext()) {

            FC temp = fastCatLocationIterator.next(); FC temp2 = fastCatDestinationIterator.next();
            fcBusTimes.add(temp.getFc_run1()); fcBusTimes.add(temp2.getFc_run1());
            fcBusTimes.add(temp.getFc_run2()); fcBusTimes.add(temp2.getFc_run2());
            fcBusTimes.add(temp.getFc_run3()); fcBusTimes.add(temp2.getFc_run3());
            fcBusTimes.add(temp.getFc_run4()); fcBusTimes.add(temp2.getFc_run4());
            fcBusTimes.add(temp.getFc_run5()); fcBusTimes.add(temp2.getFc_run5());
            fcBusTimes.add(temp.getFc_run6()); fcBusTimes.add(temp2.getFc_run6());
            fcBusTimes.add(temp.getFc_run7()); fcBusTimes.add(temp2.getFc_run7());
            fcBusTimes.add(temp.getFc_run8()); fcBusTimes.add(temp2.getFc_run9());
            fcBusTimes.add(temp.getFc_run9()); fcBusTimes.add(temp2.getFc_run9());
            fcBusTimes.add(temp.getFc_run10()); fcBusTimes.add(temp2.getFc_run10());
            fcBusTimes.add(temp.getFc_run11()); fcBusTimes.add(temp2.getFc_run11());
            fcBusTimes.add(temp.getFc_run12()); fcBusTimes.add(temp2.getFc_run12());
            fcBusTimes.add(temp.getFc_run13()); fcBusTimes.add(temp2.getFc_run13());
            fcBusTimes.add(temp.getFc_run14()); fcBusTimes.add(temp2.getFc_run14());
            fcBusTimes.add(temp.getFc_run15()); fcBusTimes.add(temp2.getFc_run15());
        }

        setUpAdapter(fcBusTimes);
        for(String fC: fcBusTimes) {
            System.out.println(fC + " "
            );
        }
    }

    public void combineGStops() {
        List<G> gLocationStops = HomeActivity.cattracksDatabase.daoAccess().getGTimesFromLocation(locationAbb);
        List<G> gDestinationStops = HomeActivity.cattracksDatabase.daoAccess().getGTimesToDestination(destinationAbb);

        Iterator<G> gLocationIterator = gLocationStops.iterator();
        Iterator<G> gDestinationIterator = gDestinationStops.iterator();


        gBusTimes.clear();
        while(gLocationIterator.hasNext() && gDestinationIterator.hasNext()) {


            G temp = gLocationIterator.next(); G temp2 = gDestinationIterator.next();
            gBusTimes.add(temp.getG_run1()); gBusTimes.add(temp2.getG_run1());
            gBusTimes.add(temp.getG_run2()); gBusTimes.add(temp2.getG_run2());
            gBusTimes.add(temp.getG_run3()); gBusTimes.add(temp2.getG_run3());
            gBusTimes.add(temp.getG_run4()); gBusTimes.add(temp2.getG_run4());
            gBusTimes.add(temp.getG_run5()); gBusTimes.add(temp2.getG_run5());
            gBusTimes.add(temp.getG_run6()); gBusTimes.add(temp2.getG_run6());
            gBusTimes.add(temp.getG_run7()); gBusTimes.add(temp2.getG_run7());
            gBusTimes.add(temp.getG_run8()); gBusTimes.add(temp2.getG_run8());
            gBusTimes.add(temp.getG_run9()); gBusTimes.add(temp2.getG_run9());
            gBusTimes.add(temp.getG_run10()); gBusTimes.add(temp2.getG_run10());
            gBusTimes.add(temp.getG_run11()); gBusTimes.add(temp2.getG_run11());
            gBusTimes.add(temp.getG_run12()); gBusTimes.add(temp2.getG_run12());
            gBusTimes.add(temp.getG_run13()); gBusTimes.add(temp2.getG_run13());
            gBusTimes.add(temp.getG_run14()); gBusTimes.add(temp2.getG_run14());
        }

        setUpAdapter(gBusTimes);
        for(String g: gBusTimes) {
            System.out.println(g + " "
            );
        }
    }


    public void combineHStops() {
        List<H> hLocationStops = HomeActivity.cattracksDatabase.daoAccess().getHTimesFromLocation(locationAbb);
        List<H> hDestinationStops = HomeActivity.cattracksDatabase.daoAccess().getHTimesToDestination(destinationAbb);

        Iterator<H> hLocationIterator = hLocationStops.iterator();
        Iterator<H> hDestinationIterator = hDestinationStops.iterator();


        hBusTimes.clear();
        while(hLocationIterator.hasNext() && hDestinationIterator.hasNext()) {

            H temp = hLocationIterator.next(); H temp2 = hDestinationIterator.next();

            hBusTimes.add(temp.getH_run1()); hBusTimes.add(temp2.getH_run1());
            hBusTimes.add(temp.getH_run2()); hBusTimes.add(temp2.getH_run2());
            hBusTimes.add(temp.getH_run3()); hBusTimes.add(temp2.getH_run3());
            hBusTimes.add(temp.getH_run4()); hBusTimes.add(temp2.getH_run4());
            hBusTimes.add(temp.getH_run5()); hBusTimes.add(temp2.getH_run5());
            hBusTimes.add(temp.getH_run6()); hBusTimes.add(temp2.getH_run6());
            hBusTimes.add(temp.getH_run7()); hBusTimes.add(temp2.getH_run7());
            hBusTimes.add(temp.getH_run8()); hBusTimes.add(temp2.getH_run8());
            hBusTimes.add(temp.getH_run9()); hBusTimes.add(temp2.getH_run9());
            hBusTimes.add(temp.getH_run10()); hBusTimes.add(temp2.getH_run10());
            hBusTimes.add(temp.getH_run11()); hBusTimes.add(temp2.getH_run11());
            hBusTimes.add(temp.getH_run12()); hBusTimes.add(temp2.getH_run12());
            hBusTimes.add(temp.getH_run13()); hBusTimes.add(temp2.getH_run13());
            hBusTimes.add(temp.getH_run14()); hBusTimes.add(temp2.getH_run14());
            hBusTimes.add(temp.getH_run15()); hBusTimes.add(temp2.getH_run15());
            hBusTimes.add(temp.getH_run16()); hBusTimes.add(temp2.getH_run16());
            hBusTimes.add(temp.getH_run17()); hBusTimes.add(temp2.getH_run17());
            hBusTimes.add(temp.getH_run18()); hBusTimes.add(temp2.getH_run18());
            hBusTimes.add(temp.getH_run19()); hBusTimes.add(temp2.getH_run19());
            hBusTimes.add(temp.getH_run20()); hBusTimes.add(temp2.getH_run20());

            hBusTimes.add(temp.getH_run21()); hBusTimes.add(temp2.getH_run21());
            hBusTimes.add(temp.getH_run22()); hBusTimes.add(temp2.getH_run22());
            hBusTimes.add(temp.getH_run23()); hBusTimes.add(temp2.getH_run23());
            hBusTimes.add(temp.getH_run24()); hBusTimes.add(temp2.getH_run24());
            hBusTimes.add(temp.getH_run25()); hBusTimes.add(temp2.getH_run25());
            hBusTimes.add(temp.getH_run26()); hBusTimes.add(temp2.getH_run26());
            hBusTimes.add(temp.getH_run27()); hBusTimes.add(temp2.getH_run27());
            hBusTimes.add(temp.getH_run28()); hBusTimes.add(temp2.getH_run28());
            hBusTimes.add(temp.getH_run29()); hBusTimes.add(temp2.getH_run29());
            hBusTimes.add(temp.getH_run30()); hBusTimes.add(temp2.getH_run30());
            hBusTimes.add(temp.getH_run31()); hBusTimes.add(temp2.getH_run31());
            hBusTimes.add(temp.getH_run32()); hBusTimes.add(temp2.getH_run32());
            hBusTimes.add(temp.getH_run33()); hBusTimes.add(temp2.getH_run33());
            hBusTimes.add(temp.getH_run34()); hBusTimes.add(temp2.getH_run34());
            hBusTimes.add(temp.getH_run35()); hBusTimes.add(temp2.getH_run35());
            hBusTimes.add(temp.getH_run36()); hBusTimes.add(temp2.getH_run36());
            hBusTimes.add(temp.getH_run37()); hBusTimes.add(temp2.getH_run37());
            hBusTimes.add(temp.getH_run38()); hBusTimes.add(temp2.getH_run38());
            hBusTimes.add(temp.getH_run39()); hBusTimes.add(temp2.getH_run39());
            hBusTimes.add(temp.getH_run40()); hBusTimes.add(temp2.getH_run40());


            hBusTimes.add(temp.getH_run41()); hBusTimes.add(temp2.getH_run41());
            hBusTimes.add(temp.getH_run42()); hBusTimes.add(temp2.getH_run42());
            hBusTimes.add(temp.getH_run43()); hBusTimes.add(temp2.getH_run43());
            hBusTimes.add(temp.getH_run44()); hBusTimes.add(temp2.getH_run44());
            hBusTimes.add(temp.getH_run45()); hBusTimes.add(temp2.getH_run45());
            hBusTimes.add(temp.getH_run46()); hBusTimes.add(temp2.getH_run46());
            hBusTimes.add(temp.getH_run47()); hBusTimes.add(temp2.getH_run47());
            hBusTimes.add(temp.getH_run48()); hBusTimes.add(temp2.getH_run48());
            hBusTimes.add(temp.getH_run49()); hBusTimes.add(temp2.getH_run49());
            hBusTimes.add(temp.getH_run50()); hBusTimes.add(temp2.getH_run50());
            hBusTimes.add(temp.getH_run51()); hBusTimes.add(temp2.getH_run51());
            hBusTimes.add(temp.getH_run52()); hBusTimes.add(temp2.getH_run52());
            hBusTimes.add(temp.getH_run53()); hBusTimes.add(temp2.getH_run53());
            hBusTimes.add(temp.getH_run54()); hBusTimes.add(temp2.getH_run54());
            hBusTimes.add(temp.getH_run55()); hBusTimes.add(temp2.getH_run55());
            hBusTimes.add(temp.getH_run56()); hBusTimes.add(temp2.getH_run56());
        }


        setUpAdapter(hBusTimes);
        for(String h: hBusTimes) {
            System.out.println(h + " "
            );
        }

    }


    public void combineHWStops() {
        List<HW> hWLocationStops = HomeActivity.cattracksDatabase.daoAccess().getHWTimesFromLocation(locationAbb);
        List<HW> hWDestinationStops = HomeActivity.cattracksDatabase.daoAccess().getHWTimesToDestination(destinationAbb);

        Iterator<HW> hWLocationIterator = hWLocationStops.iterator();
        Iterator<HW> hWDestinationIterator = hWDestinationStops.iterator();


        hwBusTimes.clear();
        while(hWLocationIterator.hasNext() && hWDestinationIterator.hasNext()) {

            HW temp = hWLocationIterator.next(); HW temp2 = hWDestinationIterator.next();

            hwBusTimes.add(temp.getHw_run1()); hwBusTimes.add(temp2.getHw_run1());
            hwBusTimes.add(temp.getHw_run2()); hwBusTimes.add(temp2.getHw_run2());
            hwBusTimes.add(temp.getHw_run3()); hwBusTimes.add(temp2.getHw_run3());
            hwBusTimes.add(temp.getHw_run4()); hwBusTimes.add(temp2.getHw_run4());
            hwBusTimes.add(temp.getHw_run5()); hwBusTimes.add(temp2.getHw_run5());
            hwBusTimes.add(temp.getHw_run6()); hwBusTimes.add(temp2.getHw_run6());
            hwBusTimes.add(temp.getHw_run7()); hwBusTimes.add(temp2.getHw_run7());
            hwBusTimes.add(temp.getHw_run8()); hwBusTimes.add(temp2.getHw_run8());
            hwBusTimes.add(temp.getHw_run9()); hwBusTimes.add(temp2.getHw_run9());
            hwBusTimes.add(temp.getHw_run10()); hwBusTimes.add(temp2.getHw_run10());
            hwBusTimes.add(temp.getHw_run11()); hwBusTimes.add(temp2.getHw_run11());
            hwBusTimes.add(temp.getHw_run12()); hwBusTimes.add(temp2.getHw_run12());
            hwBusTimes.add(temp.getHw_run13()); hwBusTimes.add(temp2.getHw_run13());
            hwBusTimes.add(temp.getHw_run14()); hwBusTimes.add(temp2.getHw_run14());
            hwBusTimes.add(temp.getHw_run15()); hwBusTimes.add(temp2.getHw_run15());
            hwBusTimes.add(temp.getHw_run16()); hwBusTimes.add(temp2.getHw_run16());
            hwBusTimes.add(temp.getHw_run17()); hwBusTimes.add(temp2.getHw_run17());
            hwBusTimes.add(temp.getHw_run18()); hwBusTimes.add(temp2.getHw_run18());
            hwBusTimes.add(temp.getHw_run19()); hwBusTimes.add(temp2.getHw_run19());
            hwBusTimes.add(temp.getHw_run20()); hwBusTimes.add(temp2.getHw_run20());
        }


        setUpAdapter(hwBusTimes);
        for(String hw: hwBusTimes) {
            System.out.println(hw + " "
            );
        }
    }



    public void setUpAdapter(List<String> busRoutes) {

        adapter = new BusRouteStopTimesAdapter(this, busRoutes);
        busStopTimesRecyclerView.setAdapter(adapter);
    }

}
