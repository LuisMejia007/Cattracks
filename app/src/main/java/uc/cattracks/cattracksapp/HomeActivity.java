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

    // PATHWAYS TO OTHER ACTIVITIES 
    Intent plan_trip_segue;
    Intent bus_alerts_segue;

    // USER INTERFACE ELEMENTS
    ImageButton navigation_button;   // Navigation menu structure 
    LinearLayout navigation_menu;    // Opens / closes navigation menu  
    ImageButton plan_trip_button;    // Opens trip planning activity 
    ImageButton bus_alerts_button;   // Opens bus alerts Twitter feed activity.


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

        // SETTING UP PATHWAYS
        plan_trip_segue = new Intent(this, LocationsList.class);
        bus_alerts_segue = new Intent(this, BusAlertsActivity);

        // SETTING UP USER INTERFACE ELEMENTS
        navigation_menu = findViewById(R.id.navigation_menu);
     
        navigation_button = findViewById(R.id.navigation_button);
        navigation_button.setOnClickListener(new View.OnClickListener() {
        	@Override
            public void onClick(View view) {
                animate_navigation_menu();
            }
        });

        plan_trip_button = findViewById(R.id.plan_trip_button);
        plan_trip_button.setOnClickListener(new View.OnClickListener() {
        	@Override
            public void onClick(View v) {
				animate_navigation_menu();
                startActivity(start_trip_segue);
            }
        });
     
       bus_alerts_button = findViewById(R.id.bus_alerts_button);
       bus_alerts_button.setOnClickListner(new View.OnClickListner() {
       		@Override 
            public void onClick(View v) {
				animate_navigation_menu();
				startActivity(bus_alerts_segue)l
			}
       });  
    }


	// USER INTERFACE FUNCTIONALITY 
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
