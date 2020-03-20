package com.example.foodordering.Adapters;

import android.service.dreams.DreamService;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodordering.Models.Restaurant;
import com.example.foodordering.R;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>
{
    private ArrayList<Restaurant> restaurants;

    public RestaurantAdapter(ArrayList<Restaurant> restaurants)
    {
        this.restaurants = restaurants;
    }
    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_restaurant_card, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position)
    {
        Restaurant restaurant = restaurants.get(position);
        holder.cuisineTextView.setText(restaurant.getCuisine());
        holder.ratingTextView.setText(restaurant.getRating().toString());
        holder.nameTextView.setText(restaurant.getName());
        Log.d("t", restaurant.getName());
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    static class RestaurantViewHolder extends RecyclerView.ViewHolder
    {
        TextView nameTextView, ratingTextView, cuisineTextView;
        View view;
        RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            ratingTextView = itemView.findViewById(R.id.rating_text_view);
            cuisineTextView = itemView.findViewById(R.id.cuisine_text_view);
            this.view = itemView;
        }
    }
}
