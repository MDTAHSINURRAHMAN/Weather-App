package com.example.weatherapp.Activities.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.weatherapp.Activities.Domains.TommorowDomain;
import com.example.weatherapp.R;

import java.util.ArrayList;

/**
 * Adapter class for displaying tomorrow's weather data in a RecyclerView.
 */
public class TommorowAdapter extends RecyclerView.Adapter<TommorowAdapter.ViewHolder> {
    private ArrayList<TommorowDomain> items;
    private Context context;

    /**
     * Constructor for TommorowAdapter.
     *
     * @param context the context for resource access and view inflation
     * @param items   the list of tomorrow's weather data
     */
    public TommorowAdapter(Context context, ArrayList<TommorowDomain> items) {
        this.context = context;
        this.items = items;
    }

    /**
     * Creates a new ViewHolder for the RecyclerView.
     *
     * @param parent   the parent ViewGroup into which the new view will be added
     * @param viewType the view type of the new view
     * @return a new ViewHolder instance
     */
    @NonNull
    @Override
    public TommorowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_tommorow, parent, false);
        return new ViewHolder(inflate);
    }

    /**
     * Binds the data to the views in the ViewHolder for the specified position.
     *
     * @param holder   the ViewHolder that should be updated
     * @param position the position of the item within the adapter's data set
     */
    @Override
    public void onBindViewHolder(@NonNull TommorowAdapter.ViewHolder holder, int position) {
        TommorowDomain item = items.get(position);
        holder.dayTxt.setText(item.getDay());
        holder.statusTxt.setText(item.getStatus());
        holder.highTxt.setText(String.valueOf(item.getHighTemp())); // Convert int to String
        holder.lowTxt.setText(String.valueOf(item.getLowTemp())); // Convert int to String

        // Load image with Glide
        try {
            int drawableResourceId = context.getResources().getIdentifier(item.getPicPath(), "drawable", context.getPackageName());
            Glide.with(context)
                    .load(drawableResourceId)
                    .apply(new RequestOptions().error(R.drawable.cloudy)) // Set a default image in case of an error
                    .into(holder.pic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return the total number of items
     */
    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    /**
     * ViewHolder class for holding the views for each tomorrow weather item.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dayTxt, statusTxt, lowTxt, highTxt;
        ImageView pic;

        /**
         * Constructor for ViewHolder.
         *
         * @param itemView the view of the item
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayTxt = itemView.findViewById(R.id.dayTxt);
            statusTxt = itemView.findViewById(R.id.statusTxt);
            lowTxt = itemView.findViewById(R.id.lowTxt);
            highTxt = itemView.findViewById(R.id.highTxt);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
