package com.example.foodordering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class AddMenuActivity extends AppCompatActivity {
    FirebaseAuth auth;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Items");
    DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("Restaurant");
    DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("RestaurantItems");
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
                final String id = reference.push().getKey();
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

//                FirebaseUser user = auth.getCurrentUser();
//                assert user != null;
//                final String rid = user.getUid();
//                reference1.child(rid).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        HashMap<String, Object> hashMap = (HashMap<String, Object>) dataSnapshot.getValue();
//                        int item_count = (int) hashMap.get("item_count");
//                        reference1.child(rid).child("item_count").setValue(item_count+1);
//                        reference2.child(rid).child(Integer.toString(item_count)).setValue(id);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
                reference.child(id).updateChildren(map);
                startActivity(new Intent(AddMenuActivity.this,SetMenuActivity.class));

            }
        });
    }
}
