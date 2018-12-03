package uc.cattracks.cattracksapp.recycleview_adapters;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import uc.cattracks.cattracksapp.DestinationsListActivity;
import uc.cattracks.cattracksapp.HomeActivity;
import uc.cattracks.cattracksapp.LocationsList;
import uc.cattracks.cattracksapp.R;
import uc.cattracks.cattracksapp.models.stops;

import static java.security.AccessController.getContext;


// ********************** StopsAdapter Class Information *****************************\\
// A view holder is responsible for displaying a single within a view. In our case we are using CardViews
// So view holders will help display all information within the CardViews that will then be shown in our RecyclerList.
// Our view holders will be managed by our RecyclerView.Adapter. The adapter binds views to their data.
// Documentation: https://developer.android.com/guide/topics/ui/layout/recyclerview


public class StopsAdapter extends RecyclerView.Adapter <StopsAdapter.StopsViewHolder> {
    public boolean bus_stop_selected = false;    // Determines if user has selected a stop

    private Context stopAdapterContext;
    private static List<stops> stopsList;
    private LocationsList locationsListActivityReference;
    public static Intent intent;

    public class StopsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        TextView commentTextView;

        public StopsViewHolder(View itemView) {
            super(itemView);


            // Binding views from 'stops_card_view' using their respective ids to our StopsAdapter
            textView = itemView.findViewById(R.id.stopNameTextView);
            commentTextView = itemView.findViewById(R.id.stopCommentTextView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            bus_stop_selected = true;

            // Hiding navigation menu in Locations List Activity if applicable
            LocationsList.navigation_menu.setVisibility(View.INVISIBLE);

            intent = new Intent(stopAdapterContext, DestinationsListActivity.class);
            Toast.makeText(stopAdapterContext, "Stop Selected: " + textView.getText().toString(), Toast.LENGTH_LONG).show();

            LocationsList.confirmationButton.setVisibility(View.VISIBLE);

            // Make Confirmation Button Move To The Next Activity
            LocationsList.confirmationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent.putExtra("Stop Selected: " , textView.getText().toString());
                    stopAdapterContext.startActivity(intent);
                }
            });
        }
    }


    public StopsAdapter(Context c, List<stops> stopsList) {
        this.stopsList = stopsList;
        stopAdapterContext = c;
    }

    @NonNull
    @Override
    public StopsAdapter.StopsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // What we're basically doing here is binding our ViewHolder to the stops_card_view (under res/layout).
        // We get our Adapter's context to then be placed in our layout inflater (which will grab our layout by its id and send it this view instance)
        // The 'view' instance will then hold the layout we made.
        // We'll then attach the 'view' instance to the view holder we made below.

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.stops_card_view, parent, false);
        StopsViewHolder stopsViewHolder = new StopsViewHolder(view);

        return stopsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StopsViewHolder holder, int position) {

    // This is where we do our final data-to-view holder binding. The arguments given are an instance of our ViewHolder
    // and the position of our list. We make a temporary instance of our stops class to get a stops object from our stopsList.
    // Then we set that stop's name to our holder's textView member.

        stops stop = stopsList.get(position);
        holder.textView.setText(stop.getS_name());
        holder.commentTextView.setText(stop.getComments());

 //******** DO NOT DELETE THIS ! CAN BE USED FOR FINAL PAPER
//        Resources res = stopAdapterContext.getResources();
//        int id = R.drawable.mercedamtrak;
//        Bitmap bm =  BitmapFactory.decodeResource(res,id);
//        holder.imageView.setImageBitmap(bm);

    }

    @Override
    public int getItemCount() {
        return stopsList.size();
    }

    // Method for Search Filtering
    public void updateList(List<stops> filteredStopsList) {

        // Reset list that is shown inside the RecyclerView
        stopsList = new ArrayList<>();
        // Place all items inside filtered list to the list that the RecyclerView showcases
        stopsList.addAll(filteredStopsList);
        // Notify the change
        notifyDataSetChanged();

    }
}
