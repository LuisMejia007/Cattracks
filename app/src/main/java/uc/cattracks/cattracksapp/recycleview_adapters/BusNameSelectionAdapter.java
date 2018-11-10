package uc.cattracks.cattracksapp.recycleview_adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import uc.cattracks.cattracksapp.R;

public class BusNameSelectionAdapter extends RecyclerView.Adapter<BusNameSelectionAdapter.BusNameSelectionViewHolder> {


    private Context busNameSelectionContext;
    private List<String> busNames;


    public class BusNameSelectionViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public BusNameSelectionViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.availableBusesTextView);
        }
    }

    public BusNameSelectionAdapter(Context context, List<String> busNames){

        this.busNameSelectionContext = context;
        this.busNames=busNames;

    }


    @NonNull
    @Override
    public BusNameSelectionAdapter.BusNameSelectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        // Setting Our RecyclerView to have a horizontal layout

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
    }

    @Override
    public int getItemCount() {
        return this.busNames.size();
    }
}
