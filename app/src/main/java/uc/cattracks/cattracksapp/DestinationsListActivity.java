package uc.cattracks.cattracksapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import uc.cattracks.cattracksapp.models.stops;

public class DestinationsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinations_list);


        List<stops> stopDestinations = new ArrayList<>();
        TextView textView = (TextView) findViewById(R.id.stop_destinations);


        String locationSelectedByUser = getIntent().getStringExtra("Stop Selected: ");

        stopDestinations = HomeActivity.cattracksDatabase.daoAccess().getFilteredDestinations(locationSelectedByUser);

        String text = "Stop Selected: " + getIntent().getStringExtra("Stop Selected: ");
        for(stops stop: stopDestinations) {

            text += "\n" + stop.getS_name();
            textView.setText(text);
        }
    }
}
