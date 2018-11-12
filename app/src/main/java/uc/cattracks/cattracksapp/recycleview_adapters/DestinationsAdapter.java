package uc.cattracks.cattracksapp.recycleview_adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uc.cattracks.cattracksapp.DestinationsListActivity;
import uc.cattracks.cattracksapp.LocationToDestinationBusActivity;
import uc.cattracks.cattracksapp.R;
import uc.cattracks.cattracksapp.models.stops;

public class DestinationsAdapter extends RecyclerView.Adapter <DestinationsAdapter.DestinationsViewHolder> {


    private Context destinationsAdapterContext;
    private DestinationsListActivity destinationsListActivity;
    private List<stops> destinations;
    public static Intent intent;



    public static class DestinationsViewHolder extends RecyclerView.ViewHolder {
        //ImageView imageView;
        TextView textView;
        TextView commentTextView;

        public DestinationsViewHolder(View itemView) {
            super(itemView);

            // Binding views from 'stops_card_view' using their respective ids to our DestinationsAdapter
            //imageView = itemView.findViewById(R.id.stopPicImageView);
            textView = itemView.findViewById(R.id.stopNameTextView);
            commentTextView = itemView.findViewById(R.id.stopCommentTextView);
        }
    }

    public DestinationsAdapter(Context c, List<stops> destinations) {
        destinationsAdapterContext = c;
        this.destinations = destinations;
    }

    @NonNull
    @Override
    public DestinationsAdapter.DestinationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.stops_card_view, parent, false);
        DestinationsViewHolder destinationsViewHolder = new DestinationsAdapter.DestinationsViewHolder(view);

        return destinationsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationsViewHolder holder, int position) {

        stops destination = destinations.get(position);
        holder.textView.setText(destination.getS_name());
        holder.commentTextView.setText(destination.getComments());



        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("TEXT VIEW CLICKED " + destination.getS_name());
                Toast.makeText(destinationsAdapterContext, "Destination Selected: " + destination.getS_name(), Toast.LENGTH_LONG).show();

                destinationsListActivity.confirmDestinationSelectionButton.setVisibility(View.VISIBLE);
                destinationsListActivity.confirmDestinationSelectionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // Passing contents from previous Activity (ListLocations) and this Activity (DestinationsListActivity)
                        // These contents are bundled using an instance of the Bundle Class, and passed into a new intent.
                        // This intent will take us to the next Activity LocationToDestinationBusActivity
                        intent = new Intent(destinationsAdapterContext, LocationToDestinationBusActivity.class);
                        Bundle extras = new Bundle();
                        // Line underneath is extremely important since the only way to get intents from a previous Activity ...
                        // ... is to access the current Activity's intents. This can only be done by typecasting its context.
                        // Link to StackOverflow question/solution: https://stackoverflow.com/questions/19967506/the-type-getintent-is-undefined-for-adapter?rq=1
                        String location = ((DestinationsListActivity)destinationsAdapterContext).getIntent().getStringExtra("Stop Selected: ");
                        extras.putString("Location", location);
                        extras.putString("Destination", destination.getS_name());
                        intent.putExtras(extras);
                        destinationsAdapterContext.startActivity(intent);
                    }
                });
            }
        });

/*
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("TEXT VIEW CLICKED " + destination.getS_name());
                Toast.makeText(destinationsAdapterContext, "Destination Selected: " + destination.getS_name(), Toast.LENGTH_LONG).show();

                destinationsListActivity.confirmDestinationSelectionButton.setVisibility(View.VISIBLE);

                destinationsListActivity.confirmDestinationSelectionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // Passing contents from previous Activity (ListLocations) and this Activity (DestinationsListActivity)
                        // These contents are bundled using an instance of the Bundle Class, and passed into a new intent.
                        // This intent will take us to the next Activity LocationToDestinationBusActivity
                        intent = new Intent(destinationsAdapterContext, LocationToDestinationBusActivity.class);
                        Bundle extras = new Bundle();
                        // Line underneath is extremely important since the only way to get intents from a previous Activity ...
                        // ... is to access the current Activity's intents. This can only be done by typecasting its context.
                        // Link to StackOverflow question/solution: https://stackoverflow.com/questions/19967506/the-type-getintent-is-undefined-for-adapter?rq=1
                        String location = ((DestinationsListActivity)destinationsAdapterContext).getIntent().getStringExtra("Stop Selected: ");
                        extras.putString("Location", location);
                        extras.putString("Destination", destination.getS_name());
                        intent.putExtras(extras);
                        destinationsAdapterContext.startActivity(intent);
                    }
                });

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return this.destinations.size();
    }

    // Method for Search Filtering
    public void updateList(List<stops> filteredStopsList) {

        // Reset list that is shown inside the RecyclerView
        destinations = new ArrayList<>();
        // Place all items inside filtered list to the list that the RecyclerView showcases
        destinations.addAll(filteredStopsList);
        // Notify the change
        notifyDataSetChanged();

    }
}
