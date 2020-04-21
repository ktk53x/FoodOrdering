package com.example.foodordering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.foodordering.Adapters.RestaurantAdapter;
import com.example.foodordering.Models.Restaurant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RestaurantsActivity extends AppCompatActivity {
    private RecyclerView restaurantRecyclerView;
    ArrayList<Restaurant> restaurants;
    private RestaurantAdapter restaurantAdapter;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference db = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        restaurantRecyclerView = findViewById(R.id.recycler_view);
        restaurants = new ArrayList<>();
        restaurantAdapter = new RestaurantAdapter(restaurants);
        Log.d("Present","Coming till here");
        restaurants.clear();
        db.
                child("Restaurants").
                addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        restaurants.clear();
                        for(DataSnapshot postSnapshot : dataSnapshot.getChildren())
                        {
                            HashMap<String, Object> temp = (HashMap<String, Object>)postSnapshot.getValue();
                            Restaurant restaurant = new Restaurant();
                            restaurant.setName(temp.get("name").toString());
                            restaurant.setEmail(temp.get("email").toString());
                            restaurant.setPhone(Integer.parseInt(temp.get("phone").toString()));
                            restaurants.add(restaurant);
                        }
                        restaurantAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        restaurantRecyclerView.setAdapter(restaurantAdapter);
    }
}
