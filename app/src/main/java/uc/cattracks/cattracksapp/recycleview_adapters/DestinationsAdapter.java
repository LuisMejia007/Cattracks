package uc.cattracks.cattracksapp.recycleview_adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uc.cattracks.cattracksapp.R;
import uc.cattracks.cattracksapp.models.stops;

public class DestinationsAdapter extends RecyclerView.Adapter <DestinationsAdapter.DestinationsViewHolder> {


    private Context destinationsAdapterContext;
    private List<stops> destinations;



    public static class DestinationsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public DestinationsViewHolder(View itemView) {
            super(itemView);

            // Binding views from 'stops_card_view' using their respective ids to our DestinationsAdapter
            imageView = itemView.findViewById(R.id.stopPicImageView);
            textView = itemView.findViewById(R.id.stopNameTextView);
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
