package uc.cattracks.cattracksapp.recycleview_adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import uc.cattracks.cattracksapp.R;
import uc.cattracks.cattracksapp.constants.MapStops;
import uc.cattracks.cattracksapp.models.stops;

public class MapStopsAdapter extends RecyclerView.Adapter<MapStopsAdapter.MapStopsViewHolder> {

    private Context mapAdapterContext;
    private static MapStops[] stopsList;


    public MapStopsAdapter (Context c, MapStops[] stops) {

        this.stopsList = stops;
        this.mapAdapterContext = c;
    }





    public class MapStopsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        TextView commentTextView;
        public MapStopsViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.stopNameTextView);
            commentTextView = itemView.findViewById(R.id.stopCommentTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            MapStops mapStop = stopsList[getAdapterPosition()];

            Toast.makeText(mapAdapterContext,"Clicked", Toast.LENGTH_LONG).show();
            // Create a Uri from an intent string. Use the result to create an Intent.

            // Street View Only
            Uri gmmIntentUri = Uri.parse("google.streetview:cbll=37.3526096,-120.4755968");

            // Google Maps
            Uri mapUri = Uri.parse("geo:37.3271018,-120.4690994?q=G+St+@+El+Portal+(northbound)");

            Uri superMapUri = Uri.parse("geo:" + mapStop.getStopLatitude() + "," + mapStop.getStopLongitude() +"?q=" + mapStop.getMapQuery());

            // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, superMapUri);
            // Make the Intent explicit by setting the Google Maps package
            mapIntent.setPackage("com.google.android.apps.maps");

            // Attempt to start an activity that can handle the Intent
            mapAdapterContext.startActivity(mapIntent);


            System.out.println("Clicked!");

        }
    }



    @NonNull
    @Override
    public MapStopsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // What we're basically doing here is binding our ViewHolder to the stops_card_view (under res/layout).
        // We get our Adapter's context to then be placed in our layout inflater (which will grab our layout by its id and send it this view instance)
        // The 'view' instance will then hold the layout we made.
        // We'll then attach the 'view' instance to the view holder we made below.

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.stops_card_view, parent, false);
        MapStopsAdapter.MapStopsViewHolder mapStopsViewHolder = new  MapStopsAdapter.MapStopsViewHolder(view);

        return mapStopsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MapStopsViewHolder holder, int position) {

        MapStops stop = stopsList[position];
        holder.textView.setText(stop.getStopName());
        holder.textView.setTextSize(23);
        holder.commentTextView.setText(" ");
    }

    @Override
    public int getItemCount() {
        return this.stopsList.length;
    }

    // Method for Search Filtering
    public void updateList(List<MapStops> filteredStopsList) {

        // Reset list that is shown inside the RecyclerView
        stopsList = new MapStops[filteredStopsList.size()];

        // Place all items inside filtered list to the list that the RecyclerView showcases
        for(int i = 0; i < filteredStopsList.size(); i++) {
            stopsList[i] = filteredStopsList.get(i);
        }
        // Notify the change
        notifyDataSetChanged();

    }


}
