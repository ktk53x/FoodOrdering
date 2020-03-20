package com.example.foodordering.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodordering.Models.Item;
import com.example.foodordering.R;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>
{
    private ArrayList<Item> items;

    public ItemAdapter(ArrayList<Item> items)
    {
        this.items = items;
    }
    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_item_card, parent, false);
        return new ItemAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position)
    {
        Item item = items.get(position);
        holder.priceTextView.setText(item.getPrice().toString());
        holder.categoryTextView.setText(item.getCategory());
        holder.nameTextView.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder
    {
        TextView nameTextView, categoryTextView, priceTextView;
        View view;
        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            categoryTextView = itemView.findViewById(R.id.category_text_view);
            priceTextView = itemView.findViewById(R.id.price_text_view);
            this.view = itemView;
        }
    }
}
