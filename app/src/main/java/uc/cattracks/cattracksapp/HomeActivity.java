package uc.cattracks.cattracksapp;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import uc.cattracks.cattracksapp.sqlAsset.*;

import uc.cattracks.cattracksapp.database.CattracksDatabase;



/*
 By: Luis Mejia
 Helpful videos/links that helped me get this much done:

 Room Tuts:
  https://www.youtube.com/watch?v=qTRTwSMgly8&frags=pl%2Cwn (Check out video #24 as well)

 Connecting pre-populated DB with Room:
  https://stackoverflow.com/questions/44263891/how-to-use-room-persistence-library-with-pre-populated-database

 Getting Started with Room:
  https://medium.freecodecamp.org/room-sqlite-beginner-tutorial-2e725e47bfab

*/

public class HomeActivity extends AppCompatActivity {
    // Will manage our fragments (basically can think of them as views)
    public static android.support.v4.app.FragmentManager fragmentManager;


    // Look @ line 79 for this object's use.
    public static CattracksDatabase cattracksDatabase;

    // Jump to select stop activity
    Intent open_select_stop;

    // User interface element for select stop
    ImageButton stop_select_button;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        /* This is where connect our Cattracks.db file to our CattracksDatabase class.
         * The CattracksDatabase class will then connect the DB to the daoAccess class (dao_interface/daoAccess)
         * From the daoAccess class you'll be able to do various SQL commands.
         * The 'allowMainThreadQueries' command allows us to make SQL queries from Android's main thread of execution ...
         *  ... probably not the most efficient way of doing things but we'll find a better way later.
         * */

        cattracksDatabase =  Room.databaseBuilder(getApplicationContext(), CattracksDatabase.class, "Cattracks.db")
                .openHelperFactory(new AssetSQLiteOpenHelperFactory())
                .allowMainThreadQueries().build();

        // Setup user interface 
        setupNavigationMenu();
    }




        // Setting up user interface elements
        // Slide menu (Linear layout)

    // USER INTERFACE FUNCTIONS
    public void setupNavigationMenu(){
    	// Setting up pathways to other activities 
        plan_trip_segue = new Intent(this, LocationsList.class);
        bus_updates_segue = new Intent(this, BusUpdatesActivity.class);
        start_map = new Intent(this, MapStopsActivity.class);
        open_select_stop = new Intent(this, ChooseTableActivity.class);
    	// Setting up user interface elements

        navigation_menu = findViewById(R.id.navigation_menu);

        navigation_button = findViewById(R.id.navigation_button);

        navigation_button.setOnClickListener((View v) -> {
            animate_navigation_menu();

        });


        // Set intent on LocationsList Activity
        plan_trip_button = findViewById(R.id.plan_trip_button);
        plan_trip_button.setOnClickListener((View v) -> {
            animate_navigation_menu();
            startActivity(plan_trip_segue);
        });


        // Set intent on BusUpdates Activity
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

        //Button has been clicked then jump to ChooseSelectActivity
        stop_select_button = findViewById(R.id.imageButton);
        stop_select_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(open_select_stop);
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
}
