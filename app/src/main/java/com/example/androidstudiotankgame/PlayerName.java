package com.example.androidstudiotankgame;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;
import java.util.UUID;

public class PlayerName extends AppCompatActivity {
    ImageButton continueBtn;
    EditText usernameEditText;

    String username;
    FirebaseDatabase database;
    DatabaseReference dbReference;

    public PlayerName(){}


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name);

        //making fullscreen with no status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        continueBtn = findViewById(R.id.continueBtn);
        usernameEditText = findViewById(R.id.usernameEditText);



        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameEditText.getText().toString();
                database = FirebaseDatabase.getInstance();
                dbReference = database.getReference("Users");

                if(username.isEmpty()){
                    username = "Player" + String.valueOf(
                            new Random().ints(100000,
                                            999999)
                            .findFirst()
                            .getAsInt());
                }
                User user = new User(username);
                dbReference.child(username).setValue(user);

                openGameModeChoiceActivity();
            }
        });

    }

    public void openGameModeChoiceActivity(){
        Intent intent = new Intent(this, GameModeChoice.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public String getUsername() {
        return username;
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }

    public DatabaseReference getDbReference() {
        return dbReference;
    }
}