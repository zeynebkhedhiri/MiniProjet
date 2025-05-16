package com.example.miniprojet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mUserDatabase;

    private TextView nameTextView, surnameTextView, emailTextView;
    private Button logoutButton, addTaskButton;
    private Button stressCheckButton;

    private Button questionnaireButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();

        // Initialize views
        nameTextView = findViewById(R.id.nameTextView);
        surnameTextView = findViewById(R.id.surnameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        logoutButton = findViewById(R.id.logoutButton);
        stressCheckButton = findViewById(R.id.stressCheckButton);  // use class field

        // Check if user is signed in
        if (mAuth.getCurrentUser() != null) {
            String uid = mAuth.getCurrentUser().getUid();
            mUserDatabase = FirebaseDatabase.getInstance().getReference("users").child(uid);


        } else {
            Toast.makeText(this, "No user signed in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;  // Stop further execution
        }

        // Logout button
        logoutButton.setOnClickListener(v -> logout());

        questionnaireButton = findViewById(R.id.questionnaireButton);
        questionnaireButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StressQuestionnaireActivity.class);
            startActivity(intent);
        });

        // Stress check button
        stressCheckButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, StressCheckActivity.class));
        });
    }


    private void displayUserInfo() {
        if (mUserDatabase == null) {
            Toast.makeText(this, "User database reference is null", Toast.LENGTH_SHORT).show();
            return;
        }

        mUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    nameTextView.setText(user.name != null ? user.name : "N/A");
                    surnameTextView.setText(user.surname != null ? user.surname : "N/A");
                    emailTextView.setText(user.email != null ? user.email : "N/A");
                } else {
                    Toast.makeText(MainActivity.this, "User data not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Error reading data", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void logout() {
        mAuth.signOut();
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    // User class for Firebase
    public static class User {
        public String name, surname, email;

        public User() {} // Required for Firebase

        public User(String name, String surname, String email) {
            this.name = name;
            this.surname = surname;
            this.email = email;
        }
    }
}
