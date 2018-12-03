package uc.cattracks.cattracksapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class BusUpdatesActivity extends AppCompatActivity {
    // PATHWAYS TO OTHER ACTIVITIES
    Intent plan_trip_segue;
    Intent bus_updates_segue;
    Intent start_map;

    // USER INTERFACE ELEMENTS
    WebView webView;

    ImageButton navigation_button;   // Navigation menu structure
    LinearLayout navigation_menu;    // Opens / closes navigation menu
    ImageButton plan_trip_button;    // Opens trip planning activity
    ImageButton bus_alerts_button;   // Opens bus alerts Twitter feed activity.
    ImageButton map_button;          // Opens activity where users can select a stop to be showcased on a map (Google Maps)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_updates);

        // Setup Twitter feed
        setupTwitterFeed();

        // Setting up toolbar menu
        setupToolBar();

        // Setting up navigation sliding menu
        setupNavigationMenu();
    }



    // USER INTERFACE FUNCTIONS
    // Navigation Sliding Menu
    public void setupTwitterFeed(){
        setContentView(R.layout.activity_bus_updates);
        webView = findViewById(R.id.twitterFeed);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://twitter.com/ucmcommute");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }



    // Twitter Web View Functionality:
    // Allows users navigate to previous web pages
    // Will cause application to close if there are no more previous web pages
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }



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
}
