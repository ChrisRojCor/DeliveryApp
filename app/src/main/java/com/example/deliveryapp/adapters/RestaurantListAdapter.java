package com.example.deliveryapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.deliveryapp.R;
import com.example.deliveryapp.model.RestaurantModel;

import java.util.List;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.myViewHolder> {

    private List<RestaurantModel> restaurantModelList;
    private RestaurantListClicklistener clickListener;

    public RestaurantListAdapter(List<RestaurantModel> restaurantModelList, RestaurantListClicklistener clickListener){
        this.restaurantModelList = restaurantModelList;
        this.clickListener = clickListener;
    }

    public void updateData(List<RestaurantModel> restaurantModelList){
        this.restaurantModelList = restaurantModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RestaurantListAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantListAdapter.myViewHolder holder, int position) {
        holder.restaurantName.setText(restaurantModelList.get(position).getName());
        holder.restaurantAddress.setText("Dirección: " + restaurantModelList.get(position).getAddress());
        holder.restaurantHours.setText("Horario: " + restaurantModelList.get(position).getHours().getTodaysHours());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(restaurantModelList.get(position));
            }
        });
        Glide.with(holder.thumbImage)
                .load(restaurantModelList.get(position).getImage())
                .into(holder.thumbImage);
    }

    @Override
    public int getItemCount() {
        return restaurantModelList.size();
    }

    static class myViewHolder extends RecyclerView.ViewHolder{
        TextView restaurantName;
        TextView restaurantAddress;
        TextView restaurantHours;
        ImageView thumbImage;

        public myViewHolder(View view){
            super(view);
            restaurantName = view.findViewById(R.id.restaurantName);
            restaurantAddress = view.findViewById(R.id.restaurantAddress);
            restaurantHours = view.findViewById(R.id.restaurantHours);
            thumbImage = view.findViewById(R.id.thumbImage);

        }
    }

    public interface RestaurantListClicklistener{
        public void onItemClick(RestaurantModel restaurantModel);
    }
}
