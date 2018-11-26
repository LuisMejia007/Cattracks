package uc.cattracks.cattracksapp.recycleview_adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import uc.cattracks.cattracksapp.R;

public class TableBusStopRunTimesAdapter extends RecyclerView.Adapter<TableBusStopRunTimesAdapter.BusRouteStopViewHolder>
{
    private static Context busRouteStopContext;
    private static LayoutInflater inflater;
    private static List<String> busTimes;

    public class BusRouteStopViewHolder extends RecyclerView.ViewHolder
    {
        TextView busStopTimeTextView;

        public BusRouteStopViewHolder(View itemView)
        {
            super(itemView);

            busStopTimeTextView = itemView.findViewById(R.id.stop_text_view);
        }
    }

    public TableBusStopRunTimesAdapter(Context c, List<String> busTimes)
    {
        busRouteStopContext = c;
        this.busTimes = busTimes;
        inflater = LayoutInflater.from(c);
    }

    @NonNull
    @Override
    public TableBusStopRunTimesAdapter.BusRouteStopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.stop_times_grid_item_view, parent, false);
        return new BusRouteStopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableBusStopRunTimesAdapter.BusRouteStopViewHolder holder, int position)
    {
        holder.busStopTimeTextView.setText(busTimes.get(position));
        holder.busStopTimeTextView.setTextSize(20);
    }

    @Override
    public int getItemCount() {
        return busTimes.size();
    }
}
