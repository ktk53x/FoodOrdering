package com.example.foodordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.foodordering.Models.Address;
import com.example.foodordering.Models.Item;
import com.example.foodordering.Models.Restaurant;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
//    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//    DatabaseReference db = firebaseDatabase.getReference();
    private Button login, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

//        Intent intent = new Intent(this, ItemsActivity.class);
//        startActivity(intent);
//        ArrayList<String> items = new ArrayList<>();
//        items.add("7a289a2b-19cb-4912-9127-a82f09ebc666");
//        items.add("a646f54e-dbfa-4aa9-af91-8044ea4a027e");
//        db
//                .child("RestaurantItems")
//                .child("3752d1ab-8507-4058-a413-f4aa98fe6d28")
//                .setValue(items);
//        items.clear();
//        items.add("cbf08d20-fda3-426b-b65c-e1165733bbeb");
//        db
//                .child("RestaurantItems")
//                .child("427abdb3-a417-44ae-b37c-e74faaf94ddf")
//                .setValue(items);

//        Item item1 = new Item();
//        item1.setiID(UUID.randomUUID().toString());
//        item1.setCategory("Main Course");
//        item1.setName("Biryani");
//        item1.setPrice(150f);
//        db
//                .child("Items")
//                .child(item1.getiID())
//                .setValue(item1);

//        Restaurant restaurant1 = new Restaurant();
//        restaurant1.setCuisine("South Indian");
//        restaurant1.setEmail("abc@gmail.com");
//        restaurant1.setName("My restaurant");
//        restaurant1.setPhone(892983942);
//        restaurant1.setRating(5);
//        restaurant1.setStatus(true);
//        restaurant1.setrID(UUID.randomUUID().toString());
//        db
//                .child("Restaurants")
//                .child(restaurant1.getrID().toString())
//                .setValue(restaurant1);
    }
}
