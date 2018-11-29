package uc.cattracks.cattracksapp.recycleview_adapters;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.health.SystemHealthManager;
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
import uc.cattracks.cattracksapp.NeatTableActivity;
import uc.cattracks.cattracksapp.R;
import uc.cattracks.cattracksapp.StopSelectActivity;
import uc.cattracks.cattracksapp.models.stops;
import static java.security.AccessController.getContext;
import static uc.cattracks.cattracksapp.StopSelectActivity.busSelected;

// ********************** StopsAdapter Class Information *****************************\\
// A view holder is responsible for displaying a single within a view. In our case we are using CardViews
// So view holders will help display all information within the CardViews that will then be shown in our RecyclerList.
// Our view holders will be managed by our RecyclerView.Adapter. The adapter binds views to their data.
// Documentation: https://developer.android.com/guide/topics/ui/layout/recyclerview

public class TableStopSelectAdapter extends RecyclerView.Adapter <TableStopSelectAdapter.SelectStopViewHolder>
{
    private Context selectStopAdapterContext;
    private static List<stops> stopsList;
    public static Intent intent;

    public int count;
    public String locAbb = "";

    // Store what the user clicks into a list of strings that will be sent
    public List<String> userChosenStops = new ArrayList<>();
    public List<String> userClickedStopNames = new ArrayList<>();

    public class SelectStopViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView textView;
        TextView commentTextView;

        public SelectStopViewHolder(View itemView)
        {
            super(itemView);

            // Binding views from 'stops_card_view' using their respective ids to our StopsAdapter
            textView = itemView.findViewById(R.id.stopNameTextView);
            commentTextView = itemView.findViewById(R.id.stopCommentTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            // Click counter to keep track of how many stops the user has chosen, max 3
            count++;

            System.out.println("CARD VIEW 1 CLICK!: " + textView.getText().toString());

            // Show the user what they clicked on with a pop up
            Toast.makeText(selectStopAdapterContext, "Stop Selected: " + textView.getText().toString(), Toast.LENGTH_LONG).show();

            // Later used for jumping to next activity
            intent = new Intent(selectStopAdapterContext, NeatTableActivity.class);

            // Sending information from what the user clicked
            intent.putExtra("busSelected", busSelected);

            // Set the string name to stop name
            String stopName = textView.getText().toString();

            // Saving stop names to an array list to send to Neat Table Activity
            userClickedStopNames.add(stopName);

            //Find what the s_abb from the stop name (QUERY)
            locAbb = HomeActivity.cattracksDatabase.daoAccess().getStopAbbFromName(stopName);

            // Get locAbb & add to arrayList for sending to NeatTableActivity
            userChosenStops.add(locAbb);

            System.out.println("userChooseStops abb: "  + userChosenStops);

            //If 3 stops have been selected then let the next button show up
            if(count == 3)
            {
                System.out.println("You hit 3 !");

                // Make the 'next' button visible
                StopSelectActivity.confirmButton.setVisibility(View.VISIBLE);
            }

            // If Confirmation Button is clicked then Move To The Next Activity with all the intents stated above
            StopSelectActivity.confirmButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    intent.putExtra("Stop Selected: " , textView.getText().toString());

                    // Grab the users selected stop name array list & send to NeatTableActivity
                    intent.putStringArrayListExtra("userChosenStops", (ArrayList<String>) userChosenStops);

                    // Grab the stop names that were clicked to neat table act
                    intent.putStringArrayListExtra("userClickStopNames", (ArrayList<String>) userClickedStopNames);

                    //Jump to NeatTableActivity
                    selectStopAdapterContext.startActivity(intent);
                }
            });
        }
    }

    public TableStopSelectAdapter(Context c, List<stops> stopsList)
    {
        this.stopsList = stopsList;
        selectStopAdapterContext = c;
    }

    // What we're basically doing here is binding our ViewHolder to the stops_card_view (under res/layout).
    // We get our Adapter's context to then be placed in our layout inflater (which will grab our layout by its id and send it this view instance)
    // The 'view' instance will then hold the layout we made.
    // We'll then attach the 'view' instance to the view holder we made below.
    @NonNull
    @Override
    public TableStopSelectAdapter.SelectStopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.stops_card_view, parent, false);
        SelectStopViewHolder selectStopViewHolder = new SelectStopViewHolder(view);

        return selectStopViewHolder;
    }

    // This is where we do our final data-to-view holder binding. The arguments given are an instance of our ViewHolder
    // and the position of our list. We make a temporary instance of our stops class to get a stops object from our stopsList.
    // Then we set that stop's name to our holder's textView member.
    @Override
    public void onBindViewHolder(@NonNull SelectStopViewHolder holder, int position)
    {
        stops stop = stopsList.get(position);
        holder.textView.setText(stop.getS_name());
        holder.commentTextView.setText(stop.getComments());
    }

    // Assuming it gets the stop list size for creating of cards
    @Override
    public int getItemCount()
    {
        return stopsList.size();
    }
}
