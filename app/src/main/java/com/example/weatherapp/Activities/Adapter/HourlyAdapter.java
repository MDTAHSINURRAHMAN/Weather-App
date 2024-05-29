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
import com.example.weatherapp.Activities.Domains.Hourly;
import com.example.weatherapp.R;

import java.util.ArrayList;

/**
 * Adapter class for displaying hourly weather data in a RecyclerView.
 */
public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.ViewHolder> {
    private ArrayList<Hourly> items;
    private Context context;

    /**
     * Constructor for HourlyAdapter.
     *
     * @param context the context for resource access and view inflation
     * @param items   the list of hourly weather data
     */
    public HourlyAdapter(Context context, ArrayList<Hourly> items) {
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
    public HourlyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_hourly, parent, false);
        return new ViewHolder(inflate);
    }

    /**
     * Binds the data to the views in the ViewHolder for the specified position.
     *
     * @param holder   the ViewHolder that should be updated
     * @param position the position of the item within the adapter's data set
     */
    @Override
    public void onBindViewHolder(@NonNull HourlyAdapter.ViewHolder holder, int position) {
        Hourly hourly = items.get(position);
        if (hourly != null) {
            holder.hourTxt.setText(hourly.getHour());
            holder.tempTxt.setText(hourly.getTemp() + "°");

            int drawableResourceId = holder.itemView.getResources().getIdentifier(hourly.getPicPath(), "drawable", holder.itemView.getContext().getPackageName());

            Glide.with(context)
                    .load(drawableResourceId)
                    .apply(new RequestOptions().error(R.drawable.cloudy)) // Set a default image in case of an error
                    .into(holder.pic);
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
     * ViewHolder class for holding the views for each hourly weather item.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hourTxt, tempTxt;
        ImageView pic;

        /**
         * Constructor for ViewHolder.
         *
         * @param itemView the view of the item
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hourTxt = itemView.findViewById(R.id.hourTxt);
            tempTxt = itemView.findViewById(R.id.tempTxt);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
