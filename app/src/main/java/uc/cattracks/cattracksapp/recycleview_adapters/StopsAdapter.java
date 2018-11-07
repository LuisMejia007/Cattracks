package uc.cattracks.cattracksapp.recycleview_adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import uc.cattracks.cattracksapp.R;
import uc.cattracks.cattracksapp.models.stops;


// ********************** StopsAdapter Class Information *****************************\\
// A view holder is responsible for displaying a single within a view. In our case we are using CardViews
// So view holders will help display all information within the CardViews that will then be shown in our RecyclerList.
// Our view holders will be managed by our RecyclerView.Adapter. The adapter binds views to their data.
// Documentation: https://developer.android.com/guide/topics/ui/layout/recyclerview


public class StopsAdapter extends RecyclerView.Adapter <StopsAdapter.StopsViewHolder> {


    private Context stopAdapterContext;
    private static List<stops> stopsList;


    public static class StopsViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public StopsViewHolder(View itemView) {
            super(itemView);


            // Binding views from 'stops_card_view' using their respective ids to our StopsAdapter
            imageView = itemView.findViewById(R.id.stopPicImageView);
            textView = itemView.findViewById(R.id.stopNameTextView);
        }
    }


    public StopsAdapter(List<stops> stopsList) {
        this.stopsList = stopsList;
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
    }

    @Override
    public int getItemCount() {
        return stopsList.size();
    }

}
