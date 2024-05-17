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

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.ViewHolder> {
    private ArrayList<Hourly> items;
    private Context context;

    public HourlyAdapter(Context context, ArrayList<Hourly> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public HourlyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_hourly, parent, false);
        return new ViewHolder(inflate);
    }

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

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hourTxt, tempTxt;
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hourTxt = itemView.findViewById(R.id.hourTxt);
            tempTxt = itemView.findViewById(R.id.tempTxt);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
