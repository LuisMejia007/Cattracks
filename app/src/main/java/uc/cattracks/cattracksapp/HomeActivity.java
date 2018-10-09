package uc.cattracks.cattracksapp;

import android.app.FragmentManager;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import uc.cattracks.cattracksapp.sqlAsset.*;

import uc.cattracks.cattracksapp.database.CattracksDatabase;
import uc.cattracks.cattracksapp.fragments.HomeFragment;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;

    // Will manage our fragments (basically can think of them as views)
    public static android.support.v4.app.FragmentManager fragmentManager;

    public static CattracksDatabase cattracksDatabase;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        mTextMessage = (TextView) findViewById(R.id.message);

        fragmentManager = getSupportFragmentManager();
        if(findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            fragmentManager.beginTransaction().add(R.id.fragment_container, new HomeFragment()).commit();
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);




       cattracksDatabase =  Room.databaseBuilder(getApplicationContext(), CattracksDatabase.class, "Cattracks.db")
               .openHelperFactory(new AssetSQLiteOpenHelperFactory())
               .allowMainThreadQueries().build();





    }

}
