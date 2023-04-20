package com.example.androidstudiotankgame;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;


public class PlayerName extends AppCompatActivity {
    ImageButton continueBtn;
    EditText usernameEditText;
    public static String username;
    FirebaseDatabase database;
    DatabaseReference dbReference;
    public static String user_uuid;

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
                username = usernameEditText.getText().toString().trim();
                database = FirebaseDatabase.getInstance();
                dbReference = database.getReference("Users");

                if(username.isEmpty()){
                    username = "Player" + String.valueOf(
                            new Random().ints(100000,
                                            999999)
                            .findFirst()
                            .getAsInt());
                }

                user_uuid = UUID.randomUUID().toString();

                pushPositionToDatabase();

                openGameModeChoiceActivity();
            }
        });

    }

    private void pushPositionToDatabase(){
        User user = new User(username, 0, 0);
        Map<String, String> map = new HashMap<>();
        map.put("name", user.getName());
        map.put("posX", user.getPosX());
        map.put("posY", user.getPosY());
        dbReference.child(user_uuid).setValue(map);
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