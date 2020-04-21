package com.example.foodordering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodordering.Models.Address;
import com.example.foodordering.Models.Restaurant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private EditText email, password, name, number;
    private Button register_btn;
    FirebaseAuth auth;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Restaurants");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.d("Present","registration started");
        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPwd);
        name = findViewById(R.id.txtName);
        number = findViewById(R.id.number);
        register_btn = findViewById(R.id.btnLogin);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emails, passwords, names, numbers;
                emails = email.getText().toString();
                passwords = password.getText().toString();
                names = name.getText().toString();
                numbers = number.getText().toString();
                if(emails.length()==0 || passwords.length() ==0 || names.length() ==0 || numbers.length() ==0)
                {
                    Toast.makeText(RegisterActivity.this, "Fields are Empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    register(emails , passwords, names, numbers);
                }
            }
        });
    }

    private void register(final String emails, String passwords, final String names, final String numbers) {

        auth.createUserWithEmailAndPassword(emails,passwords).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    String id = reference.push().getKey();
                    HashMap<String, Object>map = new HashMap<>();
                    map.put("name", names);
                    map.put("phone", numbers);
                    map.put("email",emails);
                    reference.child(id).updateChildren(map);
                    startActivity(new Intent(RegisterActivity.this, RestaurantsActivity.class));
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Could Create an Account", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
