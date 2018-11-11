package uc.cattracks.cattracksapp.recycleview_adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import uc.cattracks.cattracksapp.DisplayRouteRunTimesActivity;
import uc.cattracks.cattracksapp.LocationToDestinationBusActivity;
import uc.cattracks.cattracksapp.R;

public class BusNameSelectionAdapter extends RecyclerView.Adapter<BusNameSelectionAdapter.BusNameSelectionViewHolder> {


    private Context busNameSelectionContext;
    private List<String> busNames;
    public static Intent intent;
    private static LocationToDestinationBusActivity locationToDestinationBusActivity;


    public class BusNameSelectionViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        Button busSelectionButton;

        public BusNameSelectionViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.availableBusesTextView);
            busSelectionButton = itemView.findViewById(R.id.availableBusesCardViewButton);
        }
    }

    public BusNameSelectionAdapter(Context context, List<String> busNames){

        this.busNameSelectionContext = context;
        this.busNames=busNames;
        locationToDestinationBusActivity = new LocationToDestinationBusActivity();

    }


    @NonNull
    @Override
    public BusNameSelectionAdapter.BusNameSelectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        // Connecting our 'available_bus_card_view' layout as a view in our RecyclerView's view holder
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.available_bus_card_view, parent, false );
        BusNameSelectionViewHolder busNameSelectionViewHolder = new BusNameSelectionAdapter.BusNameSelectionViewHolder(view);

        return busNameSelectionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BusNameSelectionAdapter.BusNameSelectionViewHolder holder, int position) {

        String busName = busNames.get(position);
        holder.textView.setText(busName);

        holder.busSelectionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                // Placing all information of the card view that the user selected into our intent.
                // Our intent will become DisplayRouteRunTimesActivity
                intent = new Intent(busNameSelectionContext, DisplayRouteRunTimesActivity.class);
                Bundle extras = new Bundle();
                String busSelected = busName;
                String locationAbb = ((LocationToDestinationBusActivity)busNameSelectionContext).locationAbb;
                String destinationAbb = ((LocationToDestinationBusActivity)busNameSelectionContext).destinationAbb;
                extras.putString("Bus", busSelected);
                extras.putString("locationAbb", locationAbb);
                extras.putString("destinationAbb", destinationAbb);
                intent.putExtras(extras);
                busNameSelectionContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.busNames.size();
    }
}
