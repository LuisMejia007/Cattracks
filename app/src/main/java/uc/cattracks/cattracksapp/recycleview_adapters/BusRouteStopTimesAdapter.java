package uc.cattracks.cattracksapp.recycleview_adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import uc.cattracks.cattracksapp.DisplayRouteRunTimesActivity;
import uc.cattracks.cattracksapp.R;

import static uc.cattracks.cattracksapp.DisplayRouteRunTimesActivity.busName;


public class BusRouteStopTimesAdapter extends RecyclerView.Adapter<BusRouteStopTimesAdapter.BusRouteStopViewHolder> {

    private static Context busRouteStopContext;
    private static LayoutInflater inflater;
    private static List<String> busTimes;

   public BusRouteStopTimesAdapter(Context c, List<String> busTimes) {

        busRouteStopContext = c;
        this.busTimes = busTimes;
        inflater = LayoutInflater.from(c);
    }


    @NonNull
    @Override
    public BusRouteStopTimesAdapter.BusRouteStopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view = inflater.inflate(R.layout.stop_times_grid_item_view, parent, false);
       return new BusRouteStopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusRouteStopTimesAdapter.BusRouteStopViewHolder holder, int position) {
        holder.busStopTimeTextView.setText(busTimes.get(position));

//        String bus = ((DisplayRouteRunTimesActivity)busRouteStopContext).busName;
//        holder.busName.setText((CharSequence) bus);
//        holder.location.setText(((DisplayRouteRunTimesActivity)busRouteStopContext).locationAbb);
//        holder.destination.setText(((DisplayRouteRunTimesActivity) busRouteStopContext).destinationAbb);
    }

    @Override
    public int getItemCount() {
        return busTimes.size();
    }




    public class BusRouteStopViewHolder extends RecyclerView.ViewHolder {

       TextView busStopTimeTextView;
       TextView busName;
       TextView location;
       TextView destination;

        public BusRouteStopViewHolder(View itemView) {
            super(itemView);

            busStopTimeTextView = itemView.findViewById(R.id.stop_text_view);
            busName = itemView.findViewById(R.id.busNameTextView);
            location = itemView.findViewById(R.id.locationTextView);
            destination = itemView.findViewById(R.id.destinationTextView);
        }
    }

}
