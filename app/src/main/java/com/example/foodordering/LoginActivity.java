package com.example.foodordering;

import android.content.Intent;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {
    private EditText email, password;
    private Button loginBtn;
    FirebaseAuth auth;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Restaurants");
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        loginBtn = findViewById(R.id.login);

        auth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emails, passwords;
                emails = email.getText().toString();
                passwords = password.getText().toString();
                if(emails.isEmpty() || passwords.isEmpty())
                    Toast.makeText(LoginActivity.this, "Fields are Empty", Toast.LENGTH_SHORT).show();
                else
                    login(emails , passwords);
            }
        });
    }

    private void login(final String emails, String passwords)
    {
        auth.signInWithEmailAndPassword(emails,passwords).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser user = auth.getCurrentUser();
                    String id = user.getUid();
                    startActivity(new Intent(LoginActivity.this, RestaurantUserActivity.class));
                }
                else
                    Toast.makeText(LoginActivity.this, "Couldn't Sign In to the account", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
