package com.example.foodordering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RestaurantUserActivity extends AppCompatActivity
{
    FirebaseAuth auth;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Restaurants");
    private EditText name, number, cuisine;
    private Switch status;
    private Button logoutBtn, menuSettingsBtn, updateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_user);
        cuisine = findViewById(R.id.cuisine);
        name = findViewById(R.id.rest_name);
        number = findViewById(R.id.number);
        status = findViewById(R.id.status);
        logoutBtn = findViewById(R.id.logout);
        updateBtn = findViewById(R.id.update_btn);
        menuSettingsBtn = findViewById(R.id.menu_settings_btn);

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String id = user.getUid();

        reference.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String, Object> hashMap = (HashMap<String, Object>)dataSnapshot.getValue();

                cuisine.setText(hashMap.get("cuisine").toString());
                name.setText(hashMap.get("name").toString());
                number.setText(hashMap.get("phone").toString());
                status.setChecked(Boolean.parseBoolean(hashMap.get("status").toString()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void update(View view)
    {
        FirebaseUser user = auth.getCurrentUser();
        assert user != null;
        String id = user.getUid();
        reference.child(id).child("cuisine").setValue(cuisine.getText().toString());
        reference.child(id).child("name").setValue(name.getText().toString());
        reference.child(id).child("phone").setValue(number.getText().toString());
        reference.child(id).child("status").setValue(status.isChecked());
    }
}
