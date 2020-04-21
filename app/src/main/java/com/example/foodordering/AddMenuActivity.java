package com.example.foodordering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.foodordering.Models.Restaurant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class AddMenuActivity extends AppCompatActivity {
    FirebaseAuth auth;
    DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("RestaurantItems");
    DatabaseReference rest = FirebaseDatabase.getInstance().getReference().child("Restaurants");

    private EditText name, description, category, price;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        category = findViewById(R.id.category);
        price  = findViewById(R.id.price);
        add = findViewById(R.id.add_item);
        auth = FirebaseAuth.getInstance();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_s, description_s, category_s,price_s;
                final String id = reff.push().getKey();
                name_s = name.getText().toString();
                category_s = category.getText().toString();
                description_s = description.getText().toString();
                price_s = price.getText().toString();

                HashMap<String,Object> map = new HashMap<>();

                map.put("name",name_s);
                map.put("description", description_s);
                map.put("category",category_s);
                map.put("price",Float.parseFloat(price_s));
                map.put("iID",id);
                map.put("nonVeg", false);
                map.put("photoUrl", "");

                assert id != null;
                reff.child(id).updateChildren(map);

                FirebaseUser user = auth.getCurrentUser();
                String rid = user.getUid();
                rest = rest.child(rid);

                rest.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        HashMap<String, Object> temp = (HashMap<String, Object>)dataSnapshot.getValue();
                        ArrayList<UUID> list = (ArrayList<UUID>) temp.get("items");
                        list.add(UUID.fromString(id));
                        rest.child("items").setValue(list);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
