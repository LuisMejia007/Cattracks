package uc.cattracks.cattracksapp;

import android.app.FragmentManager;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import uc.cattracks.cattracksapp.sqlAsset.*;

import uc.cattracks.cattracksapp.database.CattracksDatabase;
import uc.cattracks.cattracksapp.fragments.HomeFragment;



/*
 Helpful videos/links that helped me get this much done:

 Room Tuts:
  https://www.youtube.com/watch?v=qTRTwSMgly8&frags=pl%2Cwn (Check out video #24 as well)

 Connecting pre-populated DB with Room:
  https://stackoverflow.com/questions/44263891/how-to-use-room-persistence-library-with-pre-populated-database

 Getting Started with Room:
  https://medium.freecodecamp.org/room-sqlite-beginner-tutorial-2e725e47bfab

*/

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextMessage;

    // Will manage our fragments (basically can think of them as views)
    public static android.support.v4.app.FragmentManager fragmentManager;

    // Look @ line 79 for this object's use.
    public static CattracksDatabase cattracksDatabase;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.nav_home);
                    return true;
                case R.id.navigation_map:
                    mTextMessage.setText(R.string.nav_map);
                    return true;
                case R.id.navigation_time:
                    mTextMessage.setText(R.string.nav_time);
                    return true;
            }
            return false;
        }
    };


    private Button seeLocationsButton;


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



        mTextMessage = (TextView) findViewById(R.id.message);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        seeLocationsButton = findViewById(R.id.showLocationsBtn);
        seeLocationsButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this, LocationsList.class);
        startActivity(intent);

    }
}
