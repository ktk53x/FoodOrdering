package com.example.foodordering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.foodordering.Adapters.ItemAdapter;
import com.example.foodordering.Adapters.RestaurantAdapter;
import com.example.foodordering.Models.Item;
import com.example.foodordering.Models.Restaurant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemsActivity extends AppCompatActivity {
    private RecyclerView itemRecyclerView;
    ArrayList<Item> items;
    private ItemAdapter itemAdapter;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference db = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        itemRecyclerView = findViewById(R.id.recycler_view);
        items = new ArrayList<>();
        itemAdapter = new ItemAdapter(items);
        items.clear();
        db.
                child("Items").
                addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        items.clear();
                        for(DataSnapshot postSnapshot : dataSnapshot.getChildren())
                        {
                            HashMap<String, Object> temp = (HashMap<String, Object>)postSnapshot.getValue();
                            Item item = new Item();
                            item.setName(temp.get("name").toString());
                            item.setCategory(temp.get("category").toString());
                            item.setPrice(Float.parseFloat(temp.get("price").toString()));
                            items.add(item);
                        }
                        itemAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemRecyclerView.setAdapter(itemAdapter);
    }
}
