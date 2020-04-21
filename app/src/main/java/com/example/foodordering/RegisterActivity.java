package com.example.foodordering;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity
{
    private EditText email, password, name, number;
    private Button registerBtn;
    FirebaseAuth auth;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Restaurants");
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.d("Present","registration started");
        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        name = findViewById(R.id.rest_name);
        number = findViewById(R.id.number);
        registerBtn = findViewById(R.id.register);

        auth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emails, passwords, names, numbers;
                emails = email.getText().toString();
                passwords = password.getText().toString();
                names = name.getText().toString();
                numbers = number.getText().toString();
                if(emails.isEmpty() || passwords.isEmpty() || names.isEmpty() || numbers.isEmpty())
                    Toast.makeText(RegisterActivity.this, "Fields are Empty", Toast.LENGTH_SHORT).show();
                else
                    register(emails , passwords, names, numbers);
            }
        });
    }

    private void register(final String emails, String passwords, final String names, final String numbers)
    {
        auth.createUserWithEmailAndPassword(emails,passwords).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser user = auth.getCurrentUser();
                    String id = user.getUid();
                    HashMap<String, Object>map = new HashMap<>();
                    map.put("name", names);
                    map.put("phone", numbers);
                    map.put("email",emails);
                    map.put("rID", id);
                    map.put("cuisine", "");
                    map.put("rating", 0);
                    map.put("status", true);
                    assert id != null;
                    reference.child(id).updateChildren(map);
                    startActivity(new Intent(RegisterActivity.this, RestaurantsActivity.class));
                }
                else
                    Toast.makeText(RegisterActivity.this, "Couldn't Create an Account", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
