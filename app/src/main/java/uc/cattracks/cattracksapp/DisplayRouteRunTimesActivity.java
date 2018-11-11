package uc.cattracks.cattracksapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class DisplayRouteRunTimesActivity extends AppCompatActivity {

    private static List<String> c1BusTimes = new ArrayList<>();
    private static List<String> c2BusTimes= new ArrayList<>();
    private static List<String> e1BusTimes= new ArrayList<>();
    private static List<String> e2BusTimes= new ArrayList<>();
    private static List<String> fcBusTimes= new ArrayList<>();
    private static List<String> gBusTimes= new ArrayList<>();
    private static List<String> hBusTimes= new ArrayList<>();
    private static List<String> hwBusTimes= new ArrayList<>();

    public static Intent intent;
    public static Bundle extras;
    public static String busName;
    public static String locationAbb;
    public static String destinationAbb;
    public static String busAbb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_route_run_times);


        getAllIntentInformation();


        TextView textView = findViewById(R.id.busNameTextView);
        textView.setText("Bus: " + busName + " Location: " + locationAbb + " Destination: " + destinationAbb);


        executeQueriesBasedOnIntentInformation();

        String temp = "";

    }


    public void getAllIntentInformation() {
        intent = getIntent();
        extras = intent.getExtras();
        busName = extras.getString("Bus");
        locationAbb = extras.getString("locationAbb");
        destinationAbb = extras.getString("destinationAbb");
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
            case "FC":
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


//        while(c2LocationIterator.hasNext() && c2DestinationIterator.hasNext()) {
//
//            c2BusTimes.add(c2LocationIterator.next());
//            c2BusTimes.add(c2DestinationIterator.next());
//        }
    }

    public void combineE1Stops() {
        List<E1> e1LocationStops = HomeActivity.cattracksDatabase.daoAccess().getE1TimesFromLocation(locationAbb);
        List<E1> e1DestinationStops = HomeActivity.cattracksDatabase.daoAccess().getE1TimesToDestination(destinationAbb);

        Iterator<E1> e1LocationIterator = e1LocationStops.iterator();
        Iterator<E1> e1DestinationIterator = e1DestinationStops.iterator();


//        while(e1LocationIterator.hasNext() && e1DestinationIterator.hasNext()) {
//
//            e1BusTimes.add(e1LocationIterator.next());
//            e1BusTimes.add(e1DestinationIterator.next());
//        }
    }

    public void combineE2Stops() {
        List<E2> e2LocationStops = HomeActivity.cattracksDatabase.daoAccess().getE2TimesFromLocation(locationAbb);
        List<E2> e2DestinationStops = HomeActivity.cattracksDatabase.daoAccess().getE2TimesToDestination(destinationAbb);

        Iterator<E2> e2LocationIterator = e2LocationStops.iterator();
        Iterator<E2> e2DestinationIterator = e2DestinationStops.iterator();


//        while(e2LocationIterator.hasNext() && e2DestinationIterator.hasNext()) {
//
//            e2BusTimes.add(e2LocationIterator.next());
//            e2BusTimes.add(e2DestinationIterator.next());
//        }
    }

    public void combineFastCatStops() {

        List<FC> fastCatLocationStops = HomeActivity.cattracksDatabase.daoAccess().getFCTimesFromLocation(locationAbb);
        List<FC> fastCatDestinationStops = HomeActivity.cattracksDatabase.daoAccess().getFCTimesToDestination(destinationAbb);
        Iterator<FC> fastCatLocationIterator = fastCatLocationStops.iterator();
        Iterator<FC> fastCatDestinationIterator = fastCatDestinationStops.iterator();

//
//        while(fastCatLocationIterator.hasNext() && fastCatDestinationIterator.hasNext()) {
//
//            // Combine
//            fcBusTimes.add(fastCatLocationIterator.next());
//            fcBusTimes.add(fastCatDestinationIterator.next());
//        }
    }

    public void combineGStops() {
        List<G> gLocationStops = HomeActivity.cattracksDatabase.daoAccess().getGTimesFromLocation(locationAbb);
        List<G> gDestinationStops = HomeActivity.cattracksDatabase.daoAccess().getGTimesToDestination(destinationAbb);

        Iterator<G> gLocationIterator = gLocationStops.iterator();
        Iterator<G> gDestinationIterator = gDestinationStops.iterator();

//
//        while(gLocationIterator.hasNext() && gDestinationIterator.hasNext()) {
//
//            gBusTimes.add(gLocationIterator.next());
//            gBusTimes.add(gDestinationIterator.next());
//        }
    }


    public void combineHStops() {
        List<H> hLocationStops = HomeActivity.cattracksDatabase.daoAccess().getHTimesFromLocation(locationAbb);
        List<H> hDestinationStops = HomeActivity.cattracksDatabase.daoAccess().getHTimesToDestination(destinationAbb);

        Iterator<H> hLocationIterator = hLocationStops.iterator();
        Iterator<H> hDestinationIterator = hDestinationStops.iterator();


//        while(hLocationIterator.hasNext() && hDestinationIterator.hasNext()) {
//
//            hBusTimes.add(hLocationIterator.next());
//            hBusTimes.add(hDestinationIterator.next());
//        }
    }


    public void combineHWStops() {
        List<HW> hWLocationStops = HomeActivity.cattracksDatabase.daoAccess().getHWTimesFromLocation(locationAbb);
        List<HW> hWDestinationStops = HomeActivity.cattracksDatabase.daoAccess().getHWTimesToDestination(destinationAbb);

        Iterator<HW> hWLocationIterator = hWLocationStops.iterator();
        Iterator<HW> hWDestinationIterator = hWDestinationStops.iterator();


//        while(hWLocationIterator.hasNext() && hWDestinationIterator.hasNext()) {
//
//            hwBusTimes.add(hWLocationIterator.next());
//            hwBusTimes.add(hWDestinationIterator.next());
//        }
    }

}
