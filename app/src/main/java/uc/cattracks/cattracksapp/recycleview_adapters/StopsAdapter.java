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


    public StopsAdapter(Context stopAdapterContext, List<stops> stopsList) {
        this.stopAdapterContext = stopAdapterContext;
        this.stopsList = stopsList;
    }

    @NonNull
    @Override
    public StopsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // What we're basically doing here is binding our ViewHolder to the gotoschool_listlayout (under res/layout).
        // We get our Adapter's context to then be placed in our layout inflater (which will grab our layout by its id and send it this view instance)
        // The 'view' instance will then hold the layout we made.
        // We'll then attach the 'view' instance to the view holder we made below.
        LayoutInflater inflater = LayoutInflater.from(stopAdapterContext);
        View view = inflater.inflate(R.layout.gotoschool_listlayout, null);
        StopsViewHolder stopsViewHolder = new StopsViewHolder(view);

        return stopsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StopsViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static class StopsViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public StopsViewHolder(View itemView) {
            super(itemView);


            // Binding views from 'gotoschool_listlayout' using their respective ids to our StopsAdapter
            imageView = itemView.findViewById(R.id.stopPicImageView);
            textView = textView.findViewById(R.id.stopNameTextView);
        }
    }
}
